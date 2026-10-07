"""
Microsoft Learn (learn.microsoft.com) → NotebookLM 用 Markdown 生成 / 月次更新スクリプト

build_help_docs.py (help.zscaler.com) と同じ考え方で、ドキュメントセット
(例: Microsoft Entra) の全ページを機能カテゴリごとの Markdown にまとめる。

取得方法:
  ページ一覧 : /_sitemaps/sitemapindex.xml から <docset>_<locale>_N.xml を見つけて読む
              (Entra の ja-jp は約 4,800 URL)
  ページ本文 : <ページURL>?accept=text/markdown
              Microsoft Learn が公式に返す Markdown (front matter + 本文)。
              HTML をスクレイピングするより崩れが少ない。

月次実行では **全ページを毎回取り直し**、本文のハッシュで新規 / 更新 / 削除を判定する。
sitemap の lastmod はビルド日で動くことがあり、逆に本文 (機械翻訳の差し替えなど) が
lastmod 据え置きで変わることもあるため、lastmod には頼らない。
4,800 ページで 10〜20 分程度なので、月 1 回なら全件確認で問題ない。

使い方:
    pip install requests

    python scripts/build_mslearn_docs.py                     # entra (ja-jp) を全件確認
    python scripts/build_mslearn_docs.py --docset entra --limit 20   # 動作確認

出力:
    mslearn_docs/<docset>/<category>/mslearn_<docset>_<category>_partN.md  ← NotebookLM のソース
    mslearn_docs/<docset>/README.md                                       ← ファイル一覧
    data/mslearn_<docset>_index.json                                      ← ページごとの状態
    output/mslearn/<docset>/report.json, changes.md                       ← メール通知用 (gitignore)
"""

import argparse
import difflib
import hashlib
import json
import re
import sys
import threading
import time
import xml.etree.ElementTree as ET
from concurrent.futures import ThreadPoolExecutor
from datetime import datetime, timezone
from pathlib import Path
from urllib.parse import urljoin

import requests

# ── 設定 ─────────────────────────────────────────────────────────────────────

BASE_URL = "https://learn.microsoft.com"
SITEMAP_INDEX_URL = f"{BASE_URL}/_sitemaps/sitemapindex.xml"
DEFAULT_LOCALE = "ja-jp"

DOCS_ROOT = Path("mslearn_docs")
DATA_DIR = Path("data")
REPORT_ROOT = Path("output/mslearn")

# 1 part の上限文字数。help docs (英語) は 120 万文字だが、日本語は空白で区切られず
# NotebookLM の「1 ソース 50 万語」の数え方が読めないため、1 文字 = 1 語と数えられても
# 収まる 50 万文字にしている。Entra (ja-jp) 全体でおよそ 83 ソースになる。
MAX_CHARS_PER_PART = 500_000

DEFAULT_WORKERS = 4
DEFAULT_DELAY = 0.2

# sitemap の件数が記録済みページ数のこの割合を下回ったら、sitemap 側の異常とみなして
# 何も書き換えずに止める。取りこぼした sitemap を信じると、大量のページを「削除」して
# しまうため。
MIN_SITEMAP_RATIO = 0.5

BLOCK_START = "<!-- MSL-PAGE {meta} -->"
BLOCK_END = "<!-- /MSL-PAGE -->"
BLOCK_RE = re.compile(
    r"<!-- MSL-PAGE (?P<meta>\{.*?\}) -->\n.*?\n<!-- /MSL-PAGE -->", re.DOTALL)

OTHER = "other"

# ドキュメントセットごとの設定。categories は (stem, 表示名, [パス接頭辞]) の並びで、
# 上から順に最初に一致したものに入る。接頭辞は "<docset>/" 以降のパス。
# ここに無いドキュメントセットは、パスの第 1 階層をそのままカテゴリにする。
DOCSETS: dict[str, dict] = {
    "entra": {
        "name": "Microsoft Entra",
        "root": "fundamentals",
        "categories": [
            ("conditional_access", "条件付きアクセス", ["identity/conditional-access"]),
            ("authentication", "認証 (MFA・パスワードレス・SSPR)", ["identity/authentication"]),
            ("hybrid", "ハイブリッド ID (Connect / Cloud Sync)", ["identity/hybrid"]),
            ("saas_apps", "SaaS アプリ連携チュートリアル", ["identity/saas-apps"]),
            ("applications", "エンタープライズアプリ・プロビジョニング・アプリプロキシ",
             ["identity/enterprise-apps", "identity/app-provisioning", "identity/app-proxy"]),
            ("monitoring", "監視・正常性 (ログ / レポート)", ["identity/monitoring-health"]),
            ("domain_services", "Microsoft Entra Domain Services", ["identity/domain-services"]),
            ("identity_core", "ユーザー・デバイス・ロール・マネージド ID",
             ["identity/"]),
            ("id_protection", "ID 保護 (Identity Protection)", ["id-protection"]),
            ("id_governance", "ID ガバナンス (PIM・アクセスレビュー等)", ["id-governance"]),
            ("global_secure_access", "Global Secure Access (Internet / Private Access)",
             ["global-secure-access"]),
            ("developer", "開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web)",
             ["identity-platform", "msal", "msidweb", "workload-id", "agent-id"]),
            ("external_id", "External ID・Verified ID", ["external-id", "verified-id"]),
            ("fundamentals", "基礎・アーキテクチャ・標準・その他",
             ["fundamentals", "architecture", "standards", "backup", "security-copilot"]),
        ],
    },
}

USER_AGENT = "Mozilla/5.0 (compatible; mslearn-notebooklm-builder/1.0)"

_local = threading.local()


def _session() -> requests.Session:
    """スレッドごとに Session を持つ (requests.Session はスレッドセーフではない)。"""
    s = getattr(_local, "session", None)
    if s is None:
        s = requests.Session()
        s.headers.update({"User-Agent": USER_AGENT})
        _local.session = s
    return s


def _get(url: str, params: dict | None = None, attempts: int = 4,
         timeout: int = 45) -> requests.Response:
    """429/5xx は待って再試行する。404 などはそのまま返す。"""
    last: Exception | None = None
    for i in range(attempts):
        try:
            resp = _session().get(url, params=params, timeout=timeout)
            if resp.status_code == 429 or resp.status_code >= 500:
                wait = resp.headers.get("Retry-After")
                delay = float(wait) if wait and wait.isdigit() else 2 ** (i + 1)
                last = RuntimeError(f"HTTP {resp.status_code}")
                time.sleep(min(delay, 60))
                continue
            return resp
        except requests.RequestException as e:
            last = e
            time.sleep(2 ** (i + 1))
    raise RuntimeError(f"{url}: {last}")


# ── docset / カテゴリ ────────────────────────────────────────────────────────

def docset_name(docset: str) -> str:
    return DOCSETS.get(docset, {}).get("name") or docset


def categorize(docset: str, path: str) -> str:
    """path は "entra/identity/conditional-access/overview" の形 (ロケールなし)。"""
    rel = path.split("/", 1)[1] if "/" in path else ""
    cfg = DOCSETS.get(docset)
    if cfg and not rel and cfg.get("root"):
        return cfg["root"]  # ドキュメントセットのトップページ (ハブ)
    if cfg:
        for stem, _name, prefixes in cfg["categories"]:
            for p in prefixes:
                if rel == p.rstrip("/") or rel.startswith(p if p.endswith("/") else p + "/"):
                    return stem
        return OTHER
    first = rel.split("/", 1)[0]
    stem = re.sub(r"[^a-z0-9]+", "_", first.lower()).strip("_")
    return stem or OTHER


def category_name(docset: str, stem: str) -> str:
    cfg = DOCSETS.get(docset)
    if cfg:
        for s, name, _ in cfg["categories"]:
            if s == stem:
                return name
    return "その他" if stem == OTHER else stem


def category_order(docset: str, stems) -> list[str]:
    cfg = DOCSETS.get(docset)
    known = [s for s, _, _ in cfg["categories"]] if cfg else []
    rest = sorted(s for s in stems if s not in known and s != OTHER)
    order = [s for s in known if s in stems] + rest
    if OTHER in stems:
        order.append(OTHER)
    return order


# ── sitemap ──────────────────────────────────────────────────────────────────

def _xml_root(text: str) -> ET.Element:
    text = text.lstrip("﻿")
    text = re.sub(r'\sxmlns(:\w+)?="[^"]+"', "", text)
    # xhtml:link などの接頭辞付き要素は名前空間宣言を外すと解析できないので消す
    text = re.sub(r"<xhtml:link[^>]*/>", "", text)
    text = re.sub(r"<video:video>.*?</video:video>", "", text, flags=re.DOTALL)
    return ET.fromstring(text)


def fetch_sitemap(docset: str, locale: str) -> dict[str, str]:
    """{path: lastmod} を返す。path は "<docset>/..." (先頭の /<locale>/ を除いたもの)。"""
    print(f"[sitemap] {SITEMAP_INDEX_URL}")
    resp = _get(SITEMAP_INDEX_URL, timeout=90)
    resp.raise_for_status()
    pat = re.compile(rf"/_sitemaps/{re.escape(docset)}_{re.escape(locale)}_\d+\.xml$")
    maps = [el.text.strip() for el in _xml_root(resp.content.decode("utf-8-sig")).iter("loc")
            if el.text and pat.search(el.text.strip())]
    if not maps:
        raise RuntimeError(f"sitemap が見つかりません: {docset}_{locale}_N.xml")

    prefix = f"{BASE_URL}/{locale}/"
    result: dict[str, str] = {}
    for url in sorted(maps):
        r = _get(url, timeout=120)
        r.raise_for_status()
        n = 0
        for url_el in _xml_root(r.content.decode("utf-8-sig")).iter("url"):
            loc = url_el.find("loc")
            if loc is None or not loc.text or not loc.text.startswith(prefix):
                continue
            path = loc.text.strip()[len(prefix):].rstrip("/")
            if not path:
                continue
            lm = url_el.find("lastmod")
            result[path] = (lm.text or "").strip() if lm is not None else ""
            n += 1
        print(f"[sitemap] {url.rsplit('/', 1)[-1]}: {n} URL")
    print(f"[sitemap] 合計 {len(result)} ページ")
    return result


# ── Markdown 整形 ────────────────────────────────────────────────────────────

_FM_LINE = re.compile(r"^([A-Za-z0-9_.]+):\s*(.*)$")
_IMG_LINK = re.compile(r"\[!\[((?:\\.|[^\]\\])*)\]\([^)]*\)\]\([^)]*\)")
_IMG = re.compile(r"!\[((?:\\.|[^\]\\])*)\]\([^)]*\)")
_LINK = re.compile(r"(?<!!)\[((?:\\.|[^\]\\])*)\]\(([^)\s]+)((?:\s+\"[^\"]*\")?)\)")
_FENCE = re.compile(r"^\s*(```|~~~)")


def split_front_matter(text: str) -> tuple[dict, str]:
    """先頭の --- ... --- を読み、トップレベルのスカラー値だけを dict にする。"""
    text = text.lstrip("﻿")
    if not text.startswith("---\n"):
        return {}, text
    end = text.find("\n---\n", 4)
    if end < 0:
        return {}, text
    meta: dict[str, str] = {}
    for line in text[4:end].split("\n"):
        m = _FM_LINE.match(line)
        if not m:
            continue
        v = m.group(2).strip()
        if len(v) >= 2 and v[0] == v[-1] == "'":
            v = v[1:-1].replace("''", "'")
        elif len(v) >= 2 and v[0] == v[-1] == '"':
            v = v[1:-1]
        meta[m.group(1)] = v
    return meta, text[end + 5:]


def clean_title(title: str) -> str:
    return re.sub(r"\s*\|\s*Microsoft Learn\s*$", "", title or "").strip()


def _alt(text: str) -> str:
    return re.sub(r"\\(.)", r"\1", text).strip()


def _clean_href(href: str) -> str:
    """&amp; を戻し、目次の表示切替用クエリ (toc= / bc=) を外す。リンク先は同じで、
    NotebookLM にとっては文字数を食うだけのノイズなので。"""
    href = href.replace("&amp;", "&")
    if "?" not in href:
        return href
    url, _, rest = href.partition("?")
    query, hash_, frag = rest.partition("#")
    kept = [q for q in query.split("&") if q and not q.startswith(("toc=", "bc="))]
    return url + ("?" + "&".join(kept) if kept else "") + hash_ + frag


def convert_body(body: str, page_url: str) -> str:
    """Learn の Markdown を NotebookLM 向けに整える。

    - 先頭の H1 (タイトル) を外し、見出しを 1 段下げる (ページ見出しを ## に使うため)
    - 画像は NotebookLM が読めないので代替テキストだけ残す
    - 相対リンクを絶対 URL にする (引用元を辿れるように)
    コードブロックの中は触らない。
    """
    out: list[str] = []
    in_fence = False
    dropped_h1 = False
    for line in body.split("\n"):
        if _FENCE.match(line):
            in_fence = not in_fence
            out.append(line)
            continue
        if in_fence:
            out.append(line)
            continue
        if not dropped_h1 and line.startswith("# "):
            dropped_h1 = True
            continue
        m = re.match(r"^(#{1,5}) ", line)
        if m:
            line = "#" + line
        line = _IMG_LINK.sub(lambda mm: f"[Image: {_alt(mm.group(1))}]" if _alt(mm.group(1)) else "", line)
        line = _IMG.sub(lambda mm: f"[Image: {_alt(mm.group(1))}]" if _alt(mm.group(1)) else "", line)

        def _abs(mm: re.Match) -> str:
            href = mm.group(2)
            if href.startswith("#") or re.match(r"^[a-z][a-z0-9+.-]*:", href, re.I):
                return mm.group(0)
            return f"[{mm.group(1)}]({_clean_href(urljoin(page_url, href))}{mm.group(3)})"
        line = _LINK.sub(_abs, line)
        out.append(line.rstrip())

    md = "\n".join(out).strip()
    return re.sub(r"\n{3,}", "\n\n", md)


# ── ページ取得 ────────────────────────────────────────────────────────────────

def page_url(locale: str, path: str) -> str:
    return f"{BASE_URL}/{locale}/{path}"


def fetch_page(locale: str, path: str, delay: float) -> dict:
    """{ok, title, block 用の各項目} か {ok: False, error} を返す。"""
    url = page_url(locale, path)
    try:
        resp = _get(url, params={"accept": "text/markdown"})
    except Exception as e:
        return {"ok": False, "error": str(e)}
    finally:
        time.sleep(delay)
    if resp.status_code != 200:
        return {"ok": False, "error": f"HTTP {resp.status_code}"}
    ctype = resp.headers.get("Content-Type", "")
    if "markdown" not in ctype:
        return {"ok": False, "error": f"Markdown が返らない (Content-Type: {ctype})"}
    resp.encoding = "utf-8"
    # CRLF を含むページがある。read_text() は改行を \n に揃えて読むため、残すと
    # 書き出した part ファイルと毎回食い違い、変化が無くても「更新」扱いになる
    text = resp.text.replace("\r\n", "\n").replace("\r", "\n")
    meta, body = split_front_matter(text)
    # 相対リンクの基準。canonicalUrl が無ければ要求した URL を使う
    base = meta.get("canonicalUrl") or url
    md = convert_body(body, base)
    if not md.strip():
        return {"ok": False, "error": "本文が空"}
    return {
        "ok": True,
        "title": clean_title(meta.get("title", "")) or path,
        "description": meta.get("description", ""),
        "ms_date": (meta.get("ms.date") or "")[:10],
        "service": " / ".join(v for v in (meta.get("ms.service"), meta.get("ms.subservice")) if v),
        "body_md": md,
    }


def fetch_many(locale: str, paths: list[str], workers: int, delay: float) -> dict[str, dict]:
    results: dict[str, dict] = {}
    done = 0
    t0 = time.time()
    with ThreadPoolExecutor(max_workers=workers) as ex:
        futs = {p: ex.submit(fetch_page, locale, p, delay) for p in paths}
        for p, fut in futs.items():
            results[p] = fut.result()
            done += 1
            if done % 200 == 0 or done == len(paths):
                bad = sum(1 for r in results.values() if not r["ok"])
                print(f"  {done}/{len(paths)} 取得 (失敗 {bad}, {time.time() - t0:.0f} 秒)")
    return results


# ── ブロック / part ファイル ─────────────────────────────────────────────────

def render_block(locale: str, path: str, page: dict) -> str:
    """1 ページ分のブロック。sitemap の lastmod や updated_at のような、本文が
    変わらなくても動く値は入れない (入れると毎月全ファイルが再アップロードになる)。"""
    meta = json.dumps({"url": path}, ensure_ascii=False, separators=(",", ":"))
    header = [f"## {page['title']}", "", f"- Source: {page_url(locale, path)}"]
    if page["service"]:
        header.append(f"- Service: {page['service']}")
    if page["ms_date"]:
        header.append(f"- Article date: {page['ms_date']}")
    if page["description"]:
        header.append(f"- Summary: {page['description']}")
    body = "\n".join(header) + "\n\n" + page["body_md"]
    return f"{BLOCK_START.format(meta=meta)}\n{body}\n{BLOCK_END}"


def block_hash(block: str) -> str:
    return hashlib.sha256(block.encode("utf-8")).hexdigest()[:16]


def block_title(block: str) -> str:
    m = re.search(r"^## (.+)$", block, re.M)
    return m.group(1).strip() if m else ""


def part_name(docset: str, stem: str, n: int) -> str:
    # NotebookLM はファイル名をソース名にして照合するため、他のノートブック
    # (zia_part1.md / community_zia_part1.md) と衝突しない接頭辞を付ける
    return f"mslearn_{docset}_{stem}_part{n}.md"


def _part_number(path: Path) -> int:
    m = re.search(r"_part(\d+)\.md$", path.name)
    return int(m.group(1)) if m else 0


def parse_existing(out_root: Path) -> dict[str, str]:
    """docset 配下の全 part ファイルを分解して {path: block} を返す。"""
    blocks: dict[str, str] = {}
    for f in sorted(out_root.glob("*/mslearn_*_part*.md")):
        for m in BLOCK_RE.finditer(f.read_text(encoding="utf-8")):
            try:
                url = json.loads(m.group("meta")).get("url")
            except json.JSONDecodeError:
                continue
            if url:
                blocks[url] = m.group(0)
    return blocks


def write_category(docset: str, locale: str, out_root: Path, stem: str,
                   blocks: dict[str, str]) -> tuple[list[Path], int]:
    """カテゴリの part ファイルを書き出す。内容が同じファイルは書き換えない
    (タイムスタンプも入れない) ので、変化の無いソースは同期で再アップロードされない。
    戻り値は (書き出した part の一覧, 実際に内容が変わったファイル数)。"""
    out_dir = out_root / stem
    out_dir.mkdir(parents=True, exist_ok=True)
    display = category_name(docset, stem)

    parts: list[list[str]] = [[]]
    size = 0
    for path in sorted(blocks):
        b = blocks[path]
        if parts[-1] and size + len(b) > MAX_CHARS_PER_PART:
            parts.append([])
            size = 0
        parts[-1].append(b)
        size += len(b) + 8

    written: list[Path] = []
    changed = 0
    for i, buf in enumerate(parts, 1):
        if not buf:
            continue
        p = out_dir / part_name(docset, stem, i)
        head = (f"# Microsoft Learn — {docset_name(docset)} / {display} (part {i})\n\n"
                f"Source: {BASE_URL}/{locale}/{docset}/\n"
                f"Pages in this file: {len(buf)}\n\n---\n\n")
        text = head + "\n\n---\n\n".join(buf) + "\n"
        if not p.exists() or p.read_text(encoding="utf-8") != text:
            p.write_text(text, encoding="utf-8")
            changed += 1
        written.append(p)

    keep = {p.name for p in written}
    for old in out_dir.glob("mslearn_*_part*.md"):
        if old.name not in keep:
            old.unlink()
            changed += 1
    return written, changed


# ── インデックス ──────────────────────────────────────────────────────────────

def index_path(docset: str) -> Path:
    return DATA_DIR / f"mslearn_{docset}_index.json"


def load_index(docset: str) -> dict:
    p = index_path(docset)
    if p.exists():
        try:
            return json.loads(p.read_text(encoding="utf-8"))
        except json.JSONDecodeError:
            print("[index] 破損しているため作り直します")
    return {"pages": {}}


def save_index(docset: str, index: dict) -> None:
    p = index_path(docset)
    p.parent.mkdir(parents=True, exist_ok=True)
    p.write_text(json.dumps(index, ensure_ascii=False, indent=1, sort_keys=True) + "\n",
                 encoding="utf-8")


# ── README ───────────────────────────────────────────────────────────────────

def write_readme(docset: str, locale: str, out_root: Path, stats: list[dict],
                 total_pages: int, checked_at: str) -> None:
    rows = []
    for s in stats:
        for p in s["files"]:
            rows.append(f"| `{p['name']}` | {s['name']} | {p['pages']:,} | {p['chars']:,} |")
    body = f"""# NotebookLM 用 Microsoft Learn — {docset_name(docset)}

`{BASE_URL}/{locale}/{docset}/` 配下の全ページ (sitemap 掲載分) を、機能カテゴリごとの
Markdown にまとめたものです。

- 生成: `scripts/build_mslearn_docs.py --docset {docset}`
- 更新: `.github/workflows/mslearn-monthly.yml` (毎月 1 日。全ページを再確認し、結果をメールで通知)
- NotebookLM ノートブック: `MSLearn_{docset}`
- ページ数: **{total_pages:,}** / ファイル数: **{sum(len(s['files']) for s in stats)}**
- 最終確認: {checked_at}

## ファイル一覧

| ファイル | カテゴリ | ページ数 | 文字数 |
|---|---|---|---|
{chr(10).join(rows)}

## 注意

- 本文は Microsoft Learn の内容の複製です (多くは CC BY 4.0、日本語版は機械翻訳を含む)。
  出典 URL を各ページの `Source:` 行に残しています。GitHub Pages の配信対象からは除外しています。
- 各ページは `<!-- MSL-PAGE {{...}} -->` マーカーで区切られています。月次更新が
  このマーカーを使うため、ファイルを手で編集しないでください。
"""
    (out_root / "README.md").write_text(body, encoding="utf-8")


# ── 差分 / レポート ──────────────────────────────────────────────────────────

def diff_stat(old: str, new: str) -> tuple[int, int]:
    plus = minus = 0
    for line in difflib.unified_diff(old.split("\n"), new.split("\n"), lineterm="", n=0):
        if line.startswith("+") and not line.startswith("+++"):
            plus += 1
        elif line.startswith("-") and not line.startswith("---"):
            minus += 1
    return plus, minus


def write_report(report: dict, report_dir: Path) -> None:
    report_dir.mkdir(parents=True, exist_ok=True)
    (report_dir / "report.json").write_text(
        json.dumps(report, ensure_ascii=False, indent=1) + "\n", encoding="utf-8")

    lines = [f"# Microsoft Learn — {report['docset_name']} 更新一覧 ({report['checked_at'][:10]})", ""]
    if report.get("fatal"):
        lines += [f"**エラー: {report['fatal']}**", ""]

    def section(title: str, items: list[dict], extra=lambda it: "") -> None:
        lines.append(f"## {title} ({len(items)})")
        lines.append("")
        for it in items:
            lines.append(f"- [{it['title']}]({it['url']}) — {it['category_name']}{extra(it)}")
        lines.append("")

    section("新規", report["new"])
    section("更新", report["updated"], lambda it: f" (+{it['plus']} / -{it['minus']} 行)")
    section("削除 (sitemap から消えたページ)", report["removed"])
    section("取得失敗 (前回の内容を保持)", report["failed"], lambda it: f" — {it['error']}")
    (report_dir / "changes.md").write_text("\n".join(lines), encoding="utf-8")


# ── メイン ───────────────────────────────────────────────────────────────────

def run(args) -> int:
    docset, locale = args.docset, args.locale
    out_root = DOCS_ROOT / docset
    report_dir = REPORT_ROOT / docset
    started = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    index = load_index(docset)
    known: dict[str, dict] = index.get("pages") or {}
    # --limit の動作確認だけで作られた index しか無いときも、全件を取る最初の実行は初回扱い
    initial = not known or (bool(index.get("partial")) and not args.limit)

    report: dict = {
        "docset": docset, "docset_name": docset_name(docset), "locale": locale,
        "checked_at": started, "initial": initial, "limit": args.limit,
        "new": [], "updated": [], "removed": [], "failed": [],
        "categories": [], "total_pages": 0, "files_changed": 0, "fatal": "",
    }

    def item(path: str, title: str, **kw) -> dict:
        cat = categorize(docset, path)
        return {"path": path, "url": page_url(locale, path), "title": title or path,
                "category": cat, "category_name": category_name(docset, cat), **kw}

    try:
        sitemap = fetch_sitemap(docset, locale)
    except Exception as e:
        report["fatal"] = f"sitemap の取得に失敗: {e}"
        print(f"[ERROR] {report['fatal']}", file=sys.stderr)
        write_report(report, report_dir)
        return 1

    if known and len(sitemap) < len(known) * MIN_SITEMAP_RATIO:
        report["fatal"] = (f"sitemap が {len(sitemap)} 件しかなく、記録済み {len(known)} 件の "
                           f"{MIN_SITEMAP_RATIO:.0%} 未満です。sitemap 側の異常とみなし、"
                           f"何も変更せずに中止しました。")
        print(f"[ERROR] {report['fatal']}", file=sys.stderr)
        write_report(report, report_dir)
        return 1

    existing = parse_existing(out_root)
    targets = sorted(sitemap)
    if args.limit:
        targets = targets[:args.limit]
    print(f"[fetch] {len(targets)} ページを取得します (workers={args.workers})")
    fetched = fetch_many(locale, targets, args.workers, args.delay)

    blocks: dict[str, str] = {}
    pages: dict[str, dict] = {}
    for path in sorted(sitemap):
        res = fetched.get(path)
        prev = known.get(path) or {}
        old_block = existing.get(path)
        if res is None:
            # --limit で今回取得しなかったページは前回のまま
            if old_block:
                blocks[path] = old_block
                pages[path] = {**prev, "lastmod": sitemap[path],
                               "category": categorize(docset, path)}
            continue
        if not res["ok"]:
            report["failed"].append(item(path, prev.get("title") or block_title(old_block or ""),
                                         error=res["error"]))
            if old_block:
                blocks[path] = old_block
                pages[path] = {**prev, "lastmod": sitemap[path],
                               "category": categorize(docset, path)}
            continue

        block = render_block(locale, path, res)
        h = block_hash(block)
        blocks[path] = block
        entry = {
            "title": res["title"], "category": categorize(docset, path),
            "lastmod": sitemap[path], "ms_date": res["ms_date"], "hash": h,
            "first_seen": prev.get("first_seen") or started[:10],
            "last_changed": prev.get("last_changed") or started[:10],
        }
        if not old_block:
            entry["last_changed"] = started[:10]
            report["new"].append(item(path, res["title"]))
        elif block_hash(old_block) != h:
            entry["last_changed"] = started[:10]
            plus, minus = diff_stat(old_block, block)
            report["updated"].append(item(path, res["title"], plus=plus, minus=minus))
        pages[path] = entry

    # sitemap から消えたページ (part ファイルか index に残っているもの)
    for path in sorted((set(existing) | set(known)) - set(sitemap)):
        title = (known.get(path) or {}).get("title") or block_title(existing.get(path, ""))
        report["removed"].append(item(path, title))

    # ── part ファイルを書き出す ──
    by_cat: dict[str, dict[str, str]] = {}
    for path, b in blocks.items():
        by_cat.setdefault(categorize(docset, path), {})[path] = b

    files_changed = 0
    stats: list[dict] = []
    for stem in category_order(docset, by_cat):
        written, changed = write_category(docset, locale, out_root, stem, by_cat[stem])
        files_changed += changed
        stats.append({
            "stem": stem, "name": category_name(docset, stem), "pages": len(by_cat[stem]),
            "files": [{"name": p.name, "pages": p.read_text(encoding="utf-8").count("<!-- MSL-PAGE "),
                       "chars": len(p.read_text(encoding="utf-8"))} for p in written],
        })
    # 中身が無くなったカテゴリのディレクトリを片付ける
    if out_root.is_dir():
        for d in out_root.iterdir():
            if d.is_dir() and d.name not in by_cat:
                for f in d.glob("mslearn_*_part*.md"):
                    f.unlink()
                    files_changed += 1
                if not any(d.iterdir()):
                    d.rmdir()

    write_readme(docset, locale, out_root, stats, len(blocks), started)
    save_index(docset, {"docset": docset, "locale": locale, "checked_at": started,
                        "partial": bool(args.limit) and (initial or bool(index.get("partial"))),
                        "pages": pages})

    report.update({
        "total_pages": len(blocks), "files_changed": files_changed,
        "sitemap_pages": len(sitemap),
        "categories": [{"stem": s["stem"], "name": s["name"], "pages": s["pages"],
                        "files": len(s["files"])} for s in stats],
        "total_files": sum(len(s["files"]) for s in stats),
        "finished_at": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
    })
    write_report(report, report_dir)
    write_step_summary(report)

    print(f"\n[done] ページ {len(blocks):,} / 新規 {len(report['new'])} / 更新 {len(report['updated'])}"
          f" / 削除 {len(report['removed'])} / 取得失敗 {len(report['failed'])}"
          f" / 変更ファイル {files_changed}")
    # 個別ページの失敗は前回の内容で補えるので止めないが、大半が失敗するのは異常
    if targets and len(report["failed"]) > len(targets) * 0.2:
        print("[ERROR] 取得失敗が 2 割を超えました", file=sys.stderr)
        return 1
    return 0


def write_step_summary(report: dict) -> None:
    import os
    p = os.environ.get("GITHUB_STEP_SUMMARY")
    if not p:
        return
    lines = [f"## Microsoft Learn — {report['docset_name']}", "",
             f"- ページ数: **{report['total_pages']:,}** / ファイル: {report.get('total_files', 0)}",
             f"- 新規 **{len(report['new'])}** / 更新 **{len(report['updated'])}** / "
             f"削除 **{len(report['removed'])}** / 取得失敗 **{len(report['failed'])}**", ""]
    with open(p, "a", encoding="utf-8") as f:
        f.write("\n".join(lines) + "\n")


def main() -> int:
    global MAX_CHARS_PER_PART
    ap = argparse.ArgumentParser(description="Microsoft Learn → NotebookLM 用 Markdown 生成/更新")
    ap.add_argument("--docset", default="entra",
                    help=f"ドキュメントセット (sitemap 名。既定: entra / 設定済み: {', '.join(DOCSETS)})")
    ap.add_argument("--locale", default=DEFAULT_LOCALE, help="既定: ja-jp")
    ap.add_argument("--workers", type=int, default=DEFAULT_WORKERS)
    ap.add_argument("--delay", type=float, default=DEFAULT_DELAY)
    ap.add_argument("--limit", type=int, default=0, help="取得ページ数の上限 (動作確認用)")
    ap.add_argument("--max-chars", type=int, default=MAX_CHARS_PER_PART,
                    help=f"1 part の上限文字数 (既定: {MAX_CHARS_PER_PART:,})")
    args = ap.parse_args()
    MAX_CHARS_PER_PART = args.max_chars
    return run(args)


if __name__ == "__main__":
    sys.exit(main())
