"""
help.zscaler.com 全ドキュメント → NotebookLM 用 Markdown 生成 / 週次更新スクリプト

help.zscaler.com は React SPA のため、HTML を直接スクレイピングしても本文は
取得できない（全URLが同一の約4.7KBのシェルを返す）。本スクリプトはSPAが内部で
利用している JSON API を直接叩く。

  記事本文  : /zapi/fetch-data?url_alias=<path>&view_type=full&...&_format=json
  更新記事  : /zapi/bulletins?product_id=&limit=...&_format=json
              (トップページの "New & Improved Articles" と同一のデータ)
  記事一覧  : /sitemap.xml  (約4,200件、lastmod付き)

出力は機能カテゴリごとの Markdown で、NotebookLM の1ソースあたりの上限
(50万語) に収まるサイズで分割される。

使い方:
    pip install requests beautifulsoup4 lxml

    # 初回・全記事を取得して再構築（数十分かかる）
    python scripts/build_help_docs.py --full

    # 週次の差分更新（sitemapのlastmod + New & Improved Articles を見て変更分のみ取得）
    python scripts/build_help_docs.py

    # カテゴリを絞る
    python scripts/build_help_docs.py --full --categories zia zpa api

出力:
    notebooklm_docs/<category>/<category>_partN.md   ← NotebookLMにアップロードする
    notebooklm_docs/README.md                        ← ファイル一覧・語数
    data/help_docs_index.json                        ← 記事ごとの更新状態
    data/help_bulletins.json                         ← New & Improved Articles の記録
"""

import argparse
import hashlib
import json
import re
import sys
import time
import xml.etree.ElementTree as ET
from concurrent.futures import ThreadPoolExecutor
from datetime import datetime, timezone
from pathlib import Path
from urllib.parse import quote, urljoin

import requests
from bs4 import BeautifulSoup, NavigableString, Tag

# ── 設定 ─────────────────────────────────────────────────────────────────────
BASE_URL = "https://help.zscaler.com"
SITEMAP_URL = f"{BASE_URL}/sitemap.xml"
ZAPI = f"{BASE_URL}/zapi"

OUTPUT_DIR = Path("notebooklm_docs")
INDEX_FILE = Path("data/help_docs_index.json")
BULLETINS_FILE = Path("data/help_bulletins.json")

# NotebookLM の 1ソース上限は 50万語。1.2M文字 ≒ 18万語で安全側に寄せる。
MAX_CHARS_PER_PART = 1_200_000

DEFAULT_WORKERS = 5
DEFAULT_DELAY = 0.2

# 記事ブロックの区切り。差分更新時にこのマーカーを頼りに既存ファイルを分解する。
BLOCK_START = "<!-- ZS-ARTICLE {meta} -->"
BLOCK_END = "<!-- /ZS-ARTICLE -->"
BLOCK_RE = re.compile(
    r"<!-- ZS-ARTICLE (?P<meta>\{.*?\}) -->\n(?P<body>.*?)\n<!-- /ZS-ARTICLE -->",
    re.DOTALL,
)

# カテゴリ定義: stem → (表示名, URLの先頭パス)
# sitemap の全4,246件がいずれかに入る（該当なしは "other"）
CATEGORIES: dict[str, tuple[str, list[str]]] = {
    "zia": ("ZIA — Internet & SaaS", ["zia"]),
    "zpa": ("ZPA — Private Access", ["zpa"]),
    "zdx": ("ZDX — Digital Experience Monitoring", ["zdx"]),
    "zcc": ("ZCC — Zscaler Client Connector", ["zscaler-client-connector", "client-connector"]),
    "api": ("API / SDK", ["legacy-apis", "zsdk"]),
    "deception": ("Zscaler Deception", ["deception"]),
    "data_security": ("Data Security — DSPM", ["dspm"]),
    "exposure_mgmt": (
        "Risk & Exposure Management",
        ["uvm", "ai-asset-mgmt", "aem", "easm", "risk360", "breach-predictor",
         "identity-protection"],
    ),
    "branch": (
        "Branch / Cellular / Cloud Connector",
        ["cloud-branch-connector", "zero-trust-branch", "zscaler-cellular"],
    ),
    "partners": ("Technology Partners", ["zscaler-technology-partners"]),
    "soc_workbench": ("SOC Workbench", ["soc-workbench"]),
    "platform": (
        "Unified Platform / Admin / Logs",
        ["unified", "business-insights", "authentication-service",
         "multi-tenant-portal", "logs-fair-use"],
    ),
    "ai_security": ("AI Security", ["secure-ai-apps-infra", "secure-ai-users"]),
    "browser": ("Zero Trust Browser", ["zero-trust-browser"]),
    "operations": (
        "Deployment / Operations / Terms",
        ["troubleshooting-runbooks", "zscaler-deployments-operations",
         "product-usage-terms", "workflow-automation"],
    ),
}
OTHER = "other"

SESSION = requests.Session()
SESSION.headers.update({
    "User-Agent": "Mozilla/5.0 (compatible; zscaler-help-docs-builder/1.0)",
    "Accept": "application/json",
})


# ── HTML → Markdown ──────────────────────────────────────────────────────────

_INLINE_TAGS = {"strong", "b", "em", "i", "code", "a", "span", "sup", "sub", "br"}


def _inline_md(node) -> str:
    """インライン要素を Markdown 文字列に変換する。"""
    if isinstance(node, NavigableString):
        return re.sub(r"\s+", " ", str(node))
    if not isinstance(node, Tag):
        return ""

    name = node.name
    inner = "".join(_inline_md(c) for c in node.children)

    if name == "br":
        return "\n"
    if name in ("strong", "b"):
        text = inner.strip()
        return f"**{text}**" if text else ""
    if name in ("em", "i"):
        text = inner.strip()
        return f"*{text}*" if text else ""
    if name == "code":
        text = inner.strip()
        return f"`{text}`" if text else ""
    if name == "a":
        text = inner.strip()
        href = node.get("href", "")
        if not text:
            return ""
        if not href or href.startswith("#"):
            return text
        return f"[{text}]({urljoin(BASE_URL, href)})"
    if name == "img":
        alt = (node.get("alt") or "").strip()
        return f"[Image: {alt}]" if alt else ""
    if name in ("ul", "ol"):
        # テーブルセルやリスト項目の中に入れ子になったリストは "; " で連結する
        items = [_inline_md(li).strip() for li in node.find_all("li", recursive=False)]
        return "; ".join(i for i in items if i)
    if name in ("p", "div", "section", "dd", "dt"):
        # ブロック要素をインラインに落とし込むときは単語が連結しないよう空白を足す
        return inner + " " if inner.strip() else ""
    return inner


def _table_md(table: Tag) -> str:
    """<table> を GFM のパイプ表に変換する。"""
    rows: list[list[str]] = []
    for tr in table.find_all("tr"):
        cells = tr.find_all(["th", "td"])
        if not cells:
            continue
        rows.append([
            _inline_md(c).replace("\n", " ").replace("|", r"\|").strip()
            for c in cells
        ])
    if not rows:
        return ""

    width = max(len(r) for r in rows)
    rows = [r + [""] * (width - len(r)) for r in rows]

    # ヘッダ行が無いテーブルは1行目をヘッダとして扱う
    header, body = rows[0], rows[1:]
    lines = ["| " + " | ".join(header) + " |",
             "| " + " | ".join(["---"] * width) + " |"]
    lines += ["| " + " | ".join(r) + " |" for r in body]
    return "\n".join(lines)


def _list_md(lst: Tag, depth: int = 0) -> str:
    """<ul>/<ol> を入れ子を保ったまま変換する。"""
    ordered = lst.name == "ol"
    out: list[str] = []
    idx = 1
    for li in lst.find_all("li", recursive=False):
        # 直下のインライン内容と、入れ子のブロックを分ける
        nested: list[Tag] = []
        inline_parts: list[str] = []
        for child in li.children:
            if isinstance(child, Tag) and child.name in ("ul", "ol"):
                nested.append(child)
            elif isinstance(child, Tag) and child.name == "table":
                nested.append(child)
            else:
                inline_parts.append(_inline_md(child))

        text = re.sub(r"\s+", " ", "".join(inline_parts)).strip()
        marker = f"{idx}." if ordered else "-"
        indent = "  " * depth
        if text:
            out.append(f"{indent}{marker} {text}")
            idx += 1
        for n in nested:
            if n.name == "table":
                tbl = _table_md(n)
                if tbl:
                    out.append("\n".join(f"{indent}  {ln}" for ln in tbl.split("\n")))
            else:
                sub = _list_md(n, depth + 1)
                if sub:
                    out.append(sub)
    return "\n".join(out)


def _block_md(node, out: list[str]) -> None:
    """ブロック要素を再帰的に走査して Markdown 行を out に積む。"""
    if isinstance(node, NavigableString):
        text = re.sub(r"\s+", " ", str(node)).strip()
        if text:
            out.append(text)
        return
    if not isinstance(node, Tag):
        return

    name = node.name

    if name in ("script", "style", "noscript"):
        return

    if name in ("h1", "h2", "h3", "h4", "h5", "h6"):
        text = _inline_md(node).strip()
        if text:
            # 記事タイトルを h1 に使うため、本文の見出しは1段下げる
            level = min(int(name[1]) + 1, 6)
            out.append(f"{'#' * level} {text}")
        return

    if name == "p":
        text = _inline_md(node).strip()
        if text:
            out.append(text)
        return

    if name in ("ul", "ol"):
        text = _list_md(node)
        if text:
            out.append(text)
        return

    if name == "table":
        text = _table_md(node)
        if text:
            out.append(text)
        return

    if name in ("pre",):
        text = node.get_text("\n", strip=True)
        if text:
            out.append(f"```\n{text}\n```")
        return

    if name == "blockquote":
        sub: list[str] = []
        for c in node.children:
            _block_md(c, sub)
        text = "\n".join(sub).strip()
        if text:
            out.append("\n".join(f"> {ln}" for ln in text.split("\n")))
        return

    if name == "hr":
        return

    if name in _INLINE_TAGS or name == "img":
        text = _inline_md(node).strip()
        if text:
            out.append(text)
        return

    # div / section / dl / その他コンテナは中身を辿る
    for c in node.children:
        _block_md(c, out)


def html_to_md(html: str) -> str:
    soup = BeautifulSoup(html or "", "lxml")
    out: list[str] = []
    for c in soup.children:
        _block_md(c, out)

    md = "\n\n".join(p for p in (s.strip() for s in out) if p)
    md = re.sub(r"\n{3,}", "\n\n", md)
    return md.strip()


# ── sitemap / API ────────────────────────────────────────────────────────────

def fetch_sitemap() -> dict[str, str]:
    """{url_path: lastmod} を返す。"""
    print(f"[sitemap] {SITEMAP_URL}")
    resp = _get_with_retry(SITEMAP_URL, timeout=60)
    xml_clean = re.sub(r'\sxmlns(:\w+)?="[^"]+"', "", resp.text)
    root = ET.fromstring(xml_clean)

    result: dict[str, str] = {}
    for url_el in root.iter("url"):
        loc_el = url_el.find("loc")
        if loc_el is None or not loc_el.text:
            continue
        path = loc_el.text.strip().replace(BASE_URL, "")
        if not path or path == "/":
            continue
        lastmod_el = url_el.find("lastmod")
        result[path] = (lastmod_el.text or "").strip() if lastmod_el is not None else ""

    print(f"[sitemap] {len(result)} 記事URL")
    return result


def fetch_bulletins(limit: int = 1000) -> list[dict]:
    """トップページの New & Improved Articles を取得する。"""
    url = (f"{ZAPI}/bulletins?product_id=&limit={limit}"
           f"&domain=help.zscaler.com&language=en&_format=json")
    try:
        resp = _get_with_retry(url, timeout=60)
        data = resp.json().get("data", {})
        products = {p["tid"]: p["name"] for p in data.get("products", [])}
        items = []
        for item in data.get("content", []):
            items.append({
                "title": item.get("title", ""),
                "url": item.get("url", ""),
                "product": products.get(item.get("tid", ""), ""),
            })
        print(f"[bulletins] New & Improved Articles: {len(items)} 件")
        return items
    except Exception as e:
        print(f"[bulletins] [SKIP] 取得失敗: {e}")
        return []


def _get_with_retry(url: str, attempts: int = 3, timeout: int = 45) -> requests.Response:
    """5xx やネットワークエラーは一時的なものが多いので指数バックオフで再試行する。"""
    last: Exception | None = None
    for i in range(attempts):
        try:
            resp = SESSION.get(url, timeout=timeout)
            resp.raise_for_status()
            return resp
        except Exception as e:
            last = e
            if i < attempts - 1:
                time.sleep(2 ** i)
    raise last  # type: ignore[misc]


def _desc_block(text: str) -> str:
    """OpenAPI の description（生HTMLが混ざる）をブロック Markdown にする。"""
    text = (text or "").strip()
    if not text:
        return ""
    return html_to_md(text) if "<" in text else text


def _desc_cell(text: str) -> str:
    """OpenAPI の description を表のセルに収まる1行に落とす。"""
    text = (text or "").strip()
    if not text:
        return ""
    if "<" in text:
        text = _inline_md(BeautifulSoup(text, "lxml"))
    return re.sub(r"\s+", " ", text).replace("|", r"\|").strip()


def _schema_summary(schema: dict, depth: int = 0) -> str:
    """OpenAPI スキーマを 1行の型表記にする。"""
    if not isinstance(schema, dict):
        return ""
    if "$ref" in schema:
        return schema["$ref"].rsplit("/", 1)[-1]
    t = schema.get("type", "")
    if t == "array":
        return f"array<{_schema_summary(schema.get('items', {}), depth + 1)}>"
    fmt = schema.get("format")
    if schema.get("enum"):
        return f"{t or 'string'} (enum: {', '.join(str(e) for e in schema['enum'][:8])})"
    if fmt and fmt != t:
        return f"{t}({fmt})"
    return t or "object"


def openapi_to_md(spec: dict) -> str:
    """OpenAPI (Swagger) 仕様を Markdown に変換する。"""
    out: list[str] = []
    info = spec.get("info", {})
    if info.get("description"):
        out.append(_desc_block(info["description"]))

    servers = [s.get("url", "") for s in spec.get("servers", []) if s.get("url")]
    if servers:
        out.append("**Servers:** " + ", ".join(f"`{s}`" for s in servers))

    for route, methods in (spec.get("paths") or {}).items():
        if not isinstance(methods, dict):
            continue
        for method, op in methods.items():
            if method.lower() not in ("get", "post", "put", "patch", "delete", "head"):
                continue
            if not isinstance(op, dict):
                continue

            out.append(f"### `{method.upper()} {route}`")
            if op.get("summary"):
                out.append(f"**{op['summary'].strip()}**")
            if op.get("description"):
                out.append(_desc_block(op["description"]))
            if op.get("operationId"):
                out.append(f"- Operation ID: `{op['operationId']}`")

            params = [p for p in (op.get("parameters") or []) if isinstance(p, dict)]
            if params:
                rows = ["| Name | In | Required | Type | Description |",
                        "| --- | --- | --- | --- | --- |"]
                for p in params:
                    desc = _desc_cell(p.get("description"))
                    rows.append(
                        f"| `{p.get('name', '')}` | {p.get('in', '')} | "
                        f"{'yes' if p.get('required') else 'no'} | "
                        f"{_schema_summary(p.get('schema', {}))} | {desc} |"
                    )
                out.append("**Parameters:**")
                out.append("\n".join(rows))

            body_spec = op.get("requestBody")
            if isinstance(body_spec, dict):
                types = list((body_spec.get("content") or {}).keys())
                schemas = [
                    _schema_summary(c.get("schema", {}))
                    for c in (body_spec.get("content") or {}).values()
                    if isinstance(c, dict)
                ]
                line = "**Request body:** " + ", ".join(f"`{t}`" for t in types)
                if any(schemas):
                    line += " → " + ", ".join(s for s in schemas if s)
                out.append(line)

            responses = op.get("responses")
            if isinstance(responses, dict) and responses:
                rows = ["| Code | Description |", "| --- | --- |"]
                for code, resp in responses.items():
                    desc = ""
                    if isinstance(resp, dict):
                        desc = _desc_cell(resp.get("description"))
                    rows.append(f"| {code} | {desc} |")
                out.append("**Responses:**")
                out.append("\n".join(rows))

    schemas = (spec.get("components") or {}).get("schemas") or {}
    if schemas:
        out.append("### Schemas")
        for name, schema in schemas.items():
            if not isinstance(schema, dict):
                continue
            props = schema.get("properties") or {}
            required = set(schema.get("required") or [])
            if not props:
                out.append(f"- `{name}`: {_schema_summary(schema)}")
                continue
            rows = [f"**`{name}`**", "", "| Property | Type | Required |", "| --- | --- | --- |"]
            for prop, ps in props.items():
                rows.append(
                    f"| `{prop}` | {_schema_summary(ps if isinstance(ps, dict) else {})} | "
                    f"{'yes' if prop in required else 'no'} |"
                )
            out.append("\n".join(rows))

    return "\n\n".join(out).strip()


def fetch_api_spec(file_url: str, delay: float) -> str:
    """Swagger 形式の API リファレンス記事に紐づく OpenAPI JSON を取得して変換する。"""
    if delay:
        time.sleep(delay)
    try:
        resp = _get_with_retry(urljoin(BASE_URL, file_url))
        return openapi_to_md(resp.json())
    except Exception as e:
        print(f"  [SKIP] API spec {file_url[:80]} — {e}")
        return ""


def fetch_article(path: str, delay: float) -> dict | None:
    """記事1件を JSON API から取得して正規化した dict を返す。"""
    if delay:
        time.sleep(delay)
    url = (f"{ZAPI}/fetch-data?url_alias={quote(path)}&view_type=full&cloud=null"
           f"&domain=help.zscaler.com&applicable_category=&applicable_version="
           f"&applicable_parent_version=&applicable_product=&keyword="
           f"&language=en&_format=json")
    try:
        resp = _get_with_retry(url)
        data = resp.json().get("data", {})
        info = data.get("info", {})
        body = data.get("body", {})

        # 移転した記事は status 301 を返すが本文は入っているので受け入れる
        if info.get("status") not in (200, 301):
            return None

        content = body.get("content") or ""
        if content:
            md = html_to_md(content)
        elif body.get("file_url"):
            # view_type=swagger_article のAPIリファレンスは OpenAPI JSON が別ファイル
            md = fetch_api_spec(body["file_url"], delay)
        else:
            # 一覧・ランディングページなど本文を持たないページ
            return None
        if not md:
            return None

        crumbs = [c.get("title", "") for c in body.get("breadcrumbs", []) if c.get("title")]
        return {
            "path": path,
            "title": body.get("title") or info.get("metatags", {}).get("title", path),
            "product": body.get("product_type", ""),
            "breadcrumbs": " > ".join(crumbs),
            "description": info.get("metatags", {}).get("description", "").strip(),
            "nid": body.get("nid", ""),
            "body_md": md,
        }
    except Exception as e:
        print(f"  [SKIP] {path} — {e}")
        return None


def fetch_many(paths: list[str], workers: int, delay: float) -> dict[str, dict]:
    results: dict[str, dict] = {}
    total = len(paths)
    done = 0
    with ThreadPoolExecutor(max_workers=workers) as ex:
        for art in ex.map(lambda p: fetch_article(p, delay), paths):
            done += 1
            if art:
                results[art["path"]] = art
            if done % 100 == 0 or done == total:
                print(f"  {done}/{total} 取得完了 (成功 {len(results)})")
    return results


# ── カテゴリ分類 ──────────────────────────────────────────────────────────────

_PREFIX_TO_CATEGORY: dict[str, str] = {}
for _stem, (_name, _prefixes) in CATEGORIES.items():
    for _p in _prefixes:
        _PREFIX_TO_CATEGORY[_p] = _stem


def categorize(path: str) -> str:
    head = path.lstrip("/").split("/", 1)[0]
    return _PREFIX_TO_CATEGORY.get(head, OTHER)


def category_name(stem: str) -> str:
    if stem == OTHER:
        return "Other"
    return CATEGORIES[stem][0]


# ── 記事ブロックの組み立て / 分解 ─────────────────────────────────────────────

def render_block(art: dict, lastmod: str) -> str:
    """1記事分の Markdown ブロック（マーカー付き）を作る。"""
    meta = json.dumps(
        {"url": art["path"], "lastmod": lastmod, "nid": art["nid"]},
        ensure_ascii=False, separators=(",", ":"),
    )
    header = [f"## {art['title']}", "", f"- Source: {BASE_URL}{art['path']}"]
    if art["product"]:
        header.append(f"- Product: {art['product']}")
    if art["breadcrumbs"]:
        header.append(f"- Path: {art['breadcrumbs']}")
    if lastmod:
        header.append(f"- Last modified: {lastmod}")
    if art["description"]:
        header.append(f"- Summary: {art['description']}")

    body = "\n".join(header) + "\n\n" + art["body_md"]
    return f"{BLOCK_START.format(meta=meta)}\n{body}\n{BLOCK_END}"


def parse_existing(stem: str) -> dict[str, str]:
    """既存の part ファイル群を分解して {url_path: block} を返す。"""
    blocks: dict[str, str] = {}
    for path in sorted((OUTPUT_DIR / stem).glob(f"{stem}_part*.md"), key=_part_number):
        text = path.read_text(encoding="utf-8")
        for m in BLOCK_RE.finditer(text):
            try:
                meta = json.loads(m.group("meta"))
            except json.JSONDecodeError:
                continue
            url = meta.get("url")
            if url:
                blocks[url] = m.group(0)
    return blocks


def write_parts(stem: str, blocks: dict[str, str]) -> list[Path]:
    """{url_path: block} を part ファイルに詰め直して書き出す。"""
    out_dir = OUTPUT_DIR / stem
    out_dir.mkdir(parents=True, exist_ok=True)

    # 既存 part を消してから書き直す（記事削除・件数減少に追従するため）
    for old in out_dir.glob(f"{stem}_part*.md"):
        old.unlink()

    display = category_name(stem)
    written: list[Path] = []
    part = 1
    buf: list[str] = []
    size = 0

    def flush() -> None:
        nonlocal buf, size, part
        if not buf:
            return
        path = out_dir / f"{stem}_part{part}.md"
        head = (f"# Zscaler Help — {display} (part {part})\n\n"
                f"Source: {BASE_URL} / help.zscaler.com\n"
                f"Generated: {datetime.now(timezone.utc).strftime('%Y-%m-%d %H:%M UTC')}\n"
                f"Articles in this file: {len(buf)}\n\n---\n\n")
        path.write_text(head + "\n\n---\n\n".join(buf) + "\n", encoding="utf-8")
        written.append(path)
        print(f"  → {path}  ({len(head) + size:,} 文字 / {len(buf)} 記事)")
        part += 1
        buf = []
        size = 0

    for url in sorted(blocks):
        block = blocks[url]
        if buf and size + len(block) > MAX_CHARS_PER_PART:
            flush()
        buf.append(block)
        size += len(block) + 8

    flush()
    return written


# ── インデックス ──────────────────────────────────────────────────────────────

def load_index() -> dict:
    if INDEX_FILE.exists():
        try:
            return json.loads(INDEX_FILE.read_text(encoding="utf-8"))
        except json.JSONDecodeError:
            print("[index] 破損しているため作り直します")
    return {"generated_at": "", "articles": {}}


def save_index(index: dict) -> None:
    INDEX_FILE.parent.mkdir(parents=True, exist_ok=True)
    index["generated_at"] = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    INDEX_FILE.write_text(
        json.dumps(index, ensure_ascii=False, indent=1, sort_keys=True) + "\n",
        encoding="utf-8",
    )


def block_hash(block: str) -> str:
    return hashlib.sha256(block.encode("utf-8")).hexdigest()[:16]


# ── README ───────────────────────────────────────────────────────────────────

def _part_number(path: Path) -> int:
    """part10 が part2 より前に並ばないよう、番号で並べ替えるためのキー。"""
    m = re.search(r"_part(\d+)\.md$", path.name)
    return int(m.group(1)) if m else 0


def write_readme(index: dict) -> None:
    rows: list[str] = []
    total_files = 0
    total_words = 0

    for stem in list(CATEGORIES) + [OTHER]:
        parts = sorted((OUTPUT_DIR / stem).glob(f"{stem}_part*.md"), key=_part_number)
        if not parts:
            continue
        count = sum(1 for a in index["articles"].values() if a["category"] == stem)
        for p in parts:
            text = p.read_text(encoding="utf-8")
            words = len(text.split())
            total_files += 1
            total_words += words
            rows.append(
                f"| `{p.relative_to(OUTPUT_DIR)}` | {category_name(stem)} | "
                f"{words:,} | {p.stat().st_size // 1024:,} KB |"
            )
        rows.append(f"| *({category_name(stem)} 記事数: {count})* | | | |")

    body = f"""# NotebookLM 用 Zscaler ヘルプドキュメント

`help.zscaler.com` の全記事（sitemap.xml 掲載分）を機能カテゴリごとに
Markdown へまとめたものです。NotebookLM に読み込ませる用途を想定しています。

- 生成: `scripts/build_help_docs.py`
- 更新: `.github/workflows/notebooklm-weekly.yml`（毎週月曜 09:00 JST）
- 記事数: **{len(index['articles']):,}**
- ファイル数: **{total_files}** / 総語数: **{total_words:,}**
- 最終更新: {index.get('generated_at', '')}

## NotebookLM への読み込み手順

1. NotebookLM で新規ノートブックを作成する
2. 「ソースを追加」→「ファイルをアップロード」
3. 下表の `.md` ファイルを選択する（カテゴリ単位でも全件でも可）

NotebookLM の制限は「1ノートブックあたりのソース数」と「1ソースあたり
50万語」です。本スクリプトは 1ファイル約18万語で分割するため、どのファイルも
単体で1ソースに収まります。ソース数を節約したい場合は、必要なカテゴリだけを
アップロードしてください。

## ファイル一覧

| ファイル | カテゴリ | 語数 | サイズ |
|---|---|---|---|
{chr(10).join(rows)}

## 注意

- 本ディレクトリは Zscaler の著作物の複製です。社内・個人利用の範囲で扱い、
  再配布しないでください。GitHub Pages の配信対象からは除外しています。
- 各記事は `<!-- ZS-ARTICLE {{...}} -->` マーカーで区切られています。差分更新が
  このマーカーを利用するため、ファイルを手で編集しないでください。
"""
    (OUTPUT_DIR / "README.md").write_text(body, encoding="utf-8")


# ── メイン ───────────────────────────────────────────────────────────────────

def main() -> int:
    ap = argparse.ArgumentParser(
        description="help.zscaler.com → NotebookLM 用 Markdown 生成/更新")
    ap.add_argument("--full", action="store_true",
                    help="全記事を再取得して作り直す（初回用）")
    ap.add_argument("--categories", nargs="*", metavar="STEM",
                    help=f"対象カテゴリ (既定: 全て) 選択肢: {', '.join(list(CATEGORIES) + [OTHER])}")
    ap.add_argument("--workers", type=int, default=DEFAULT_WORKERS)
    ap.add_argument("--delay", type=float, default=DEFAULT_DELAY)
    ap.add_argument("--limit", type=int, default=0,
                    help="取得する記事数の上限（動作確認用）")
    args = ap.parse_args()

    start = time.time()

    try:
        sitemap = fetch_sitemap()
    except Exception as e:
        print(f"[ERROR] sitemap 取得に失敗: {e}", file=sys.stderr)
        return 1

    bulletins = fetch_bulletins()
    bulletin_paths = {b["url"] for b in bulletins if b["url"]}

    index = load_index()
    known: dict[str, dict] = index["articles"]

    targets = set(args.categories) if args.categories else None
    if targets:
        unknown = targets - set(CATEGORIES) - {OTHER}
        if unknown:
            print(f"[ERROR] 未知のカテゴリ: {', '.join(sorted(unknown))}", file=sys.stderr)
            return 1

    # ── 取得対象を決める ──────────────────────────────────────────────────
    to_fetch: list[str] = []
    for path, lastmod in sitemap.items():
        stem = categorize(path)
        if targets and stem not in targets:
            continue
        if args.full:
            to_fetch.append(path)
            continue
        prev = known.get(path)
        if prev is None:
            to_fetch.append(path)                       # 新規記事
        elif lastmod and prev.get("lastmod") != lastmod:
            to_fetch.append(path)                       # sitemap の lastmod が変化
        elif path in bulletin_paths and not prev.get("in_bulletins"):
            to_fetch.append(path)                       # New & Improved に新規掲載

    # sitemap から消えた記事は削除対象
    removed = [
        p for p in known
        if p not in sitemap and not (targets and categorize(p) not in targets)
    ]

    if args.limit:
        to_fetch = to_fetch[:args.limit]

    print(f"\n取得対象: {len(to_fetch)} 記事  /  削除: {len(removed)} 記事")
    if not to_fetch and not removed:
        print("更新はありません。")
        # bulletins の記録だけ更新しておく
        save_bulletins(bulletins)
        return 0

    fetched = fetch_many(to_fetch, args.workers, args.delay) if to_fetch else {}
    failed = [p for p in to_fetch if p not in fetched]
    if failed:
        print(f"[WARN] {len(failed)} 記事は取得できませんでした（404 等）")

    # ── カテゴリごとに part ファイルを書き直す ──────────────────────────────
    touched: set[str] = set()
    for path in list(fetched) + removed:
        touched.add(categorize(path))
    if targets:
        touched &= targets

    print(f"\n更新するカテゴリ: {', '.join(sorted(touched)) or '(なし)'}")

    # 書き出したカテゴリで実際に part ファイルに入った記事。index との差を後で整える。
    written_paths: set[str] = set()

    for stem in sorted(touched):
        blocks = {} if args.full else parse_existing(stem)

        # --full 以外では、未取得の記事は既存ブロックをそのまま維持する
        for path, art in fetched.items():
            if categorize(path) != stem:
                continue
            blocks[path] = render_block(art, sitemap.get(path, ""))
        for path in removed:
            if categorize(path) == stem:
                blocks.pop(path, None)

        if not blocks:
            print(f"[{stem}] 記事なし — スキップ")
            continue

        print(f"[{stem}] {category_name(stem)}: {len(blocks)} 記事")
        write_parts(stem, blocks)
        written_paths |= set(blocks)

    # ── インデックス更新 ──────────────────────────────────────────────────
    if args.full:
        # --full で対象にしたカテゴリのエントリを作り直す
        known = {
            p: v for p, v in known.items()
            if targets and categorize(p) not in targets
        }
    for path in removed:
        known.pop(path, None)
    for path, art in fetched.items():
        block = render_block(art, sitemap.get(path, ""))
        known[path] = {
            "title": art["title"],
            "category": categorize(path),
            "lastmod": sitemap.get(path, ""),
            "nid": art["nid"],
            "hash": block_hash(block),
            "in_bulletins": path in bulletin_paths,
        }
    # 今回取得しなかった記事も bulletins 掲載状態は反映する
    for path, meta in known.items():
        if path not in fetched:
            meta["in_bulletins"] = path in bulletin_paths

    # part ファイルから消えている記事は index からも落とし、次回の差分実行で
    # 取り直されるようにする（ファイルを手で消した／壊した場合の自己修復）
    orphans = [
        p for p, meta in known.items()
        if meta["category"] in touched and p not in written_paths
    ]
    for path in orphans:
        known.pop(path, None)
    if orphans:
        print(f"[index] part ファイルに存在しない {len(orphans)} 件を index から削除")

    index["articles"] = known
    save_index(index)
    save_bulletins(bulletins)
    write_readme(index)

    # ── サマリ ────────────────────────────────────────────────────────────
    elapsed = time.time() - start
    print(f"\n{'=' * 60}")
    print(f"完了  経過 {elapsed / 60:.1f} 分")
    print(f"総記事数: {len(known):,}")
    print(f"新規/更新: {len(fetched)}  削除: {len(removed)}  失敗: {len(failed)}")

    if fetched:
        print("\n更新された記事 (先頭30件):")
        for path in sorted(fetched)[:30]:
            print(f"  [{categorize(path)}] {fetched[path]['title']} — {path}")

    write_step_summary(fetched, removed, failed, known)
    return 0


def save_bulletins(bulletins: list[dict]) -> None:
    """New & Improved Articles の現在のスナップショットを保存する。"""
    if not bulletins:
        return
    BULLETINS_FILE.parent.mkdir(parents=True, exist_ok=True)
    payload = {
        "checked_at": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
        "total": len(bulletins),
        "articles": sorted(bulletins, key=lambda b: b["url"]),
    }
    BULLETINS_FILE.write_text(
        json.dumps(payload, ensure_ascii=False, indent=1) + "\n", encoding="utf-8")


def write_step_summary(fetched: dict, removed: list, failed: list, known: dict) -> None:
    """GitHub Actions のジョブサマリに結果を書く（ローカル実行時は何もしない）。"""
    import os
    summary_path = os.environ.get("GITHUB_STEP_SUMMARY")
    if not summary_path:
        return

    lines = [
        "## Zscaler ヘルプドキュメント週次更新", "",
        f"- 総記事数: **{len(known):,}**",
        f"- 新規/更新: **{len(fetched)}**",
        f"- 削除: **{len(removed)}**",
        f"- 取得失敗: **{len(failed)}**", "",
    ]
    if fetched:
        lines += ["### 更新された記事", "",
                  "| カテゴリ | タイトル | URL |", "|---|---|---|"]
        for path in sorted(fetched):
            art = fetched[path]
            lines.append(f"| {categorize(path)} | {art['title']} | "
                         f"[{path}]({BASE_URL}{path}) |")
        lines.append("")
        lines.append("> NotebookLM 側は自動更新されません。"
                     "`notebooklm_docs/` の該当ファイルを再アップロードしてください。")

    with open(summary_path, "a", encoding="utf-8") as f:
        f.write("\n".join(lines) + "\n")


if __name__ == "__main__":
    sys.exit(main())
