"""
Zscaler 週次リリースノート ダイジェスト

help.zscaler.com の全サービスのリリースノート (Release Upgrade Summary /
各種 Release Summary) から、直近 7 日間 (土曜〜金曜, JST) に展開された記事を
集め、サービスごとに

  - <service>.md   … 原文 (HTML→Markdown) をそのまままとめたもの
  - <service>.html … 日本語訳 (Claude API) + 要約 + 記事一覧

を生成し、Gmail で自分宛てに送信する。

データの取り方:
  リリースノートのページは SPA で、本文 HTML はほぼ空。記事は
  /zapi/fetch-data の body.release_notes に「クラウド (または OS) → 展開日 →
  記事」の構造で入っている。クラウドごとに展開日が違うため、ページごとに
  全クラウドを `applicable_category=<id>` で取得し、記事 id で突き合わせる。

  対象ページは sitemap.xml の `*release*summary-<year>` から毎回自動で拾うので、
  Zscaler が新しい製品のリリースノートを追加しても設定変更は不要。

取りこぼし対策:
  記事は展開日より後に掲載されることがある (金曜展開分が土曜に載る等)。
  data/release_digest_seen.json に既知の記事 id を記録し、7 日の窓より前の
  展開日でも「初めて見る記事」は「遅れて掲載された記事」として拾う。

使い方:
    pip install requests beautifulsoup4 lxml anthropic

    # 生成のみ (メール送信なし・状態ファイル更新なし)
    python scripts/weekly_release_digest.py --no-email --no-save-state

    # 対象週を指定 (終了日 = 金曜日)
    python scripts/weekly_release_digest.py --end-date 2026-10-02

環境変数:
    GMAIL_APP_PASSWORD  Gmail のアプリパスワード (送信時に必須)
    NOTIFY_EMAIL_TO     宛先 (省略時は送信元と同じ)
    ANTHROPIC_API_KEY   あれば Claude で HTML を日本語訳する (タイトル・要約・本文の全文訳)。
                        無ければ HTML は原文のまま
"""

import argparse
import html
import json
import os
import re
import smtplib
import sys
import time
import xml.etree.ElementTree as ET
from datetime import date, datetime, timedelta, timezone
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pathlib import Path

import requests

sys.path.insert(0, str(Path(__file__).resolve().parent))
from build_help_docs import html_to_md  # noqa: E402

# ── 設定 ─────────────────────────────────────────────────────────────────────
BASE_URL = "https://help.zscaler.com"
SITEMAP_URL = f"{BASE_URL}/sitemap.xml"
FETCH_URL = f"{BASE_URL}/zapi/fetch-data"

JST = timezone(timedelta(hours=9))
WINDOW_DAYS = 7
REQUEST_DELAY = 0.3

OUTPUT_ROOT = Path("output/release_digest")
SEEN_FILE = Path("data/release_digest_seen.json")
HELP_INDEX_FILE = Path("data/help_docs_index.json")

GMAIL_ADDRESS = "ciderred1239@gmail.com"

CLAUDE_MODEL = "claude-opus-5-5"

# release-upgrade-summary-2026, client-connector-app-release-summary-2026 等
SUMMARY_PATH_RE = re.compile(r"^/([a-z0-9-]+)/[a-z0-9-]*release-(?:upgrade-)?summary-(\d{4})$")

# サービスの表示名。ここに無いものは API の product_type をそのまま使う。
SERVICE_NAMES = {
    "zia": "ZIA (Internet & SaaS)",
    "zpa": "ZPA (Private Access)",
    "zdx": "ZDX (Digital Experience)",
    "zscaler-client-connector": "ZCC (Client Connector)",
    "cloud-branch-connector": "Cloud & Branch Connector",
    "zero-trust-branch": "Zero Trust Branch",
    "authentication-service": "Authentication Service (ZIdentity)",
    "deception": "Deception",
    "identity-protection": "Identity Protection",
    "dspm": "DSPM",
    "risk360": "Risk360",
    "uvm": "Unified Vulnerability Management",
    "aem": "Asset Exposure Management",
    "easm": "External Attack Surface Management",
    "workflow-automation": "Workflow Automation",
    "agentic-soc": "Agentic SOC",
    "ai-asset-mgmt": "AI Asset Management",
    "secure-ai-users": "Secure Access to AI Apps",
    "secure-ai-apps-infra": "Secure AI Apps & Infrastructure",
    "zscaler-cellular": "Zscaler Cellular",
    "breach-predictor": "Breach Predictor",
    "business-insights": "Business Insights",
}

# 主要サービスを先頭に並べる
SERVICE_ORDER = ["zia", "zpa", "zdx", "zscaler-client-connector"]

STATUS_JA = {
    "available": "一般提供",
    "limited": "限定提供",
    "deprecated": "廃止",
    "eol": "サポート終了",
}


# ── 日付 ─────────────────────────────────────────────────────────────────────
def week_window(end: date | None) -> tuple[date, date]:
    """終了日 (金曜) と開始日 (前の土曜) を返す。

    end を省略すると「JST の今日以前で直近の金曜」。スケジュール実行が遅れて
    土曜にずれ込んでも、対象週は変わらない。
    """
    if end is None:
        today = datetime.now(JST).date()
        end = today - timedelta(days=(today.weekday() - 4) % 7)
    return end - timedelta(days=WINDOW_DAYS - 1), end


# ── 取得 ─────────────────────────────────────────────────────────────────────
def _get(url: str, params: dict | None = None, attempts: int = 3) -> requests.Response:
    last: Exception | None = None
    for i in range(attempts):
        try:
            resp = requests.get(url, params=params, timeout=45,
                                headers={"User-Agent": "zscaler-weekly-digest/1.0"})
            resp.raise_for_status()
            return resp
        except Exception as e:  # noqa: BLE001
            last = e
            time.sleep(2 ** (i + 1))
    raise RuntimeError(f"{url} — {last}")


def discover_pages(years: set[int]) -> list[str]:
    """sitemap からリリースノートページを列挙する。失敗時はローカルの記事索引で代用。"""
    paths: set[str] = set()
    try:
        root = ET.fromstring(_get(SITEMAP_URL).content)
        ns = {"s": "http://www.sitemaps.org/schemas/sitemap/0.9"}
        for loc in root.iterfind(".//s:loc", ns):
            paths.add((loc.text or "").strip().replace(BASE_URL, ""))
    except Exception as e:  # noqa: BLE001
        print(f"[WARN] sitemap 取得失敗、{HELP_INDEX_FILE} で代用: {e}")
        if HELP_INDEX_FILE.exists():
            idx = json.loads(HELP_INDEX_FILE.read_text("utf-8"))
            paths.update(idx.get("articles", idx).keys())
    found = []
    for p in paths:
        m = SUMMARY_PATH_RE.match(p)
        if m and int(m.group(2)) in years:
            found.append(p)
    return sorted(found)


def _j(value):
    """release_notes の値は dict のことも JSON 文字列のこともある。"""
    if isinstance(value, str):
        try:
            return json.loads(value)
        except ValueError:
            return value
    return value


def fetch_release_notes(path: str, category: str | None = None) -> dict:
    params = {
        "url_alias": path, "view_type": "full", "cloud": "null",
        "domain": "help.zscaler.com", "language": "en", "_format": "json",
    }
    if category:
        params["applicable_category"] = category
    time.sleep(REQUEST_DELAY)
    data = _get(FETCH_URL, params).json().get("data", {})
    body = data.get("body", {}) or {}
    rn = body.get("release_notes") or {}
    return {
        "page_title": body.get("title", ""),
        "product_type": body.get("product_type", ""),
        "categories": _j(rn.get("mainCategories")) or {},
        "category_order": _j(rn.get("mainCategoriesOrder")) or [],
        "selected": str(_j(rn.get("selected_category")) or ""),
        "category_label": _j(rn.get("mainCategoryLabel")) or "",
        "entries": _j(rn.get("entries")) or {},
    }


def iter_entries(entries: dict):
    """entries[date][kind][status] = [{version, title, entries: [...]}] を平らにする。"""
    for d, day in entries.items():
        if not isinstance(day, dict):
            continue
        for kind, by_status in day.items():
            if not isinstance(by_status, dict):
                continue
            for status, groups in by_status.items():
                for group in groups or []:
                    for e in group.get("entries") or []:
                        if e.get("id"):
                            yield d, kind, status, group, e


def collect_page(path: str) -> tuple[dict, dict[str, dict]]:
    """1ページ分を全クラウド/OS ぶん取得し、記事 id → 記事 の dict を返す。"""
    first = fetch_release_notes(path)
    meta = {"path": path, "page_title": first["page_title"],
            "product_type": first["product_type"], "category_label": first["category_label"]}
    cats = first["categories"]
    order = [str(c) for c in first["category_order"]] or list(cats)
    items: dict[str, dict] = {}

    for cat_id in order or [""]:
        rn = first if (not cat_id or cat_id == first["selected"]) else fetch_release_notes(path, cat_id)
        cat_name = cats.get(cat_id, cat_id) or "-"
        for d, kind, status, group, e in iter_entries(rn["entries"]):
            item = items.setdefault(e["id"], {
                "id": e["id"],
                "title": html.unescape(e.get("title", "")).strip(),
                "description": e.get("description") or "",
                "extra": e.get("summary") or "",
                "kind": kind,
                "status": status,
                "status_label": group.get("title", ""),
                "version": group.get("version", ""),
                "deployments": [],
            })
            item["deployments"].append({"category": cat_name, "category_id": cat_id, "date": d})
    return meta, items


# ── 状態 ─────────────────────────────────────────────────────────────────────
def load_seen() -> set[str] | None:
    if not SEEN_FILE.exists():
        return None
    return set(json.loads(SEEN_FILE.read_text("utf-8")).get("ids", []))


def save_seen(ids: set[str], min_year: int) -> None:
    # 2年より前のページの id は再登場しないので捨てる
    keep = sorted(i for i in ids if int(i.split("#")[0][-4:]) >= min_year)
    SEEN_FILE.parent.mkdir(parents=True, exist_ok=True)
    SEEN_FILE.write_text(json.dumps({
        "updated": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
        "ids": keep,
    }, indent=1, ensure_ascii=False) + "\n", "utf-8")


# ── 整形 ─────────────────────────────────────────────────────────────────────
def absolutize(html_text: str) -> str:
    # 画像本体は取得しないので「See image.」リンクは落とす
    html_text = re.sub(r'<a[^>]*class="image-icon"[^>]*>.*?</a>', "", html_text, flags=re.S)
    return re.sub(r'(href|src)="/(?!/)', rf'\1="{BASE_URL}/', html_text)


def plain_text(html_text: str) -> str:
    t = re.sub(r"<[^>]+>", " ", html_text)
    return re.sub(r"\s+", " ", html.unescape(t)).strip()


def deep_link(path: str, item: dict, window: tuple[date, date] | None = None) -> str:
    deps = sorted(item["deployments"], key=lambda x: x["date"])
    if window:
        inside = [d for d in deps if window[0].isoformat() <= d["date"] <= window[1].isoformat()]
        deps = inside or deps
    dep = deps[-1]
    q = f"deployment_date={dep['date']}&id={item['id']}"
    if dep["category"] and dep["category"] != "-":
        q = f"applicable_category={dep['category']}&" + q
    return f"{BASE_URL}{path}?{q}"


def status_ja(item: dict) -> str:
    base = STATUS_JA.get(item["status"], item["status"])
    return f"{base} ({item['status_label']})" if item["status_label"] else base


def deployments_text(item: dict, window: tuple[date, date] | None = None) -> str:
    parts = []
    for dep in sorted(item["deployments"], key=lambda x: (x["date"], x["category"])):
        mark = ""
        if window and window[0].isoformat() <= dep["date"] <= window[1].isoformat():
            mark = "★"
        parts.append(f"{mark}{dep['category']} {dep['date']}")
    return ", ".join(parts)


def service_name(stem: str, metas: list[dict]) -> str:
    if stem in SERVICE_NAMES:
        return SERVICE_NAMES[stem]
    return next((m["product_type"] for m in metas if m["product_type"]), stem)


def render_md(stem: str, svc: dict, window: tuple[date, date]) -> str:
    start, end = window
    lines = [
        f"# {svc['name']} — Zscaler 週次アップデート ({start} 〜 {end})",
        "",
        f"- 取得日時: {datetime.now(JST):%Y-%m-%d %H:%M} JST",
        f"- 記事数: {len(svc['items'])} 件 (うち遅れて掲載 {sum(1 for i in svc['items'] if i['late'])} 件)",
        "- 出典: " + ", ".join(f"[{m['page_title']}]({BASE_URL}{m['path']})" for m in svc["pages"]),
        "- 展開先の ★ は対象週内の展開",
        "",
    ]
    for page in svc["pages"]:
        page_items = [i for i in svc["items"] if i["path"] == page["path"]]
        if not page_items:
            continue
        lines += [f"## {page['page_title']}", ""]
        for it in page_items:
            lines += [f"### {it['title']}", ""]
            lines.append(f"- 区分: {status_ja(it)}")
            if it["version"]:
                lines.append(f"- バージョン: {it['version']}")
            label = "OS" if "OS" in page["category_label"] else "クラウド"
            lines.append(f"- 展開 ({label}): {deployments_text(it, window)}")
            if it["late"]:
                lines.append("- ※ 前週以前の展開日で、今週新たに掲載された記事")
            lines.append(f"- 原文: {deep_link(it['path'], it, window)}")
            lines.append("")
            body = html_to_md(absolutize(it["description"])) if it["description"] else ""
            if body:
                lines += [body, ""]
            if it["extra"]:
                lines += [f"> {plain_text(it['extra'])}", ""]
    return "\n".join(lines).rstrip() + "\n"


# ── 要約・翻訳 ───────────────────────────────────────────────────────────────
# 記事ごとに日本語タイトル・要約・本文の全文訳を作る。ZCC の不具合修正一覧の
# ように1記事が長い週もあるので、記事を TRANSLATE_CHUNK_CHARS ごとに分けて
# 依頼し、応答はストリーミングで受ける (長い出力で HTTP タイムアウトしないように)。
TRANSLATE_CHUNK_CHARS = 12_000

ITEMS_SCHEMA = {
    "type": "object",
    "properties": {
        "items": {
            "type": "array",
            "items": {
                "type": "object",
                "properties": {
                    "id": {"type": "string"},
                    "title_ja": {"type": "string"},
                    "summary_ja": {"type": "string"},
                    "translation_ja": {"type": "string"},
                },
                "required": ["id", "title_ja", "summary_ja", "translation_ja"],
                "additionalProperties": False,
            },
        },
    },
    "required": ["items"],
    "additionalProperties": False,
}

OVERVIEW_SCHEMA = {
    "type": "object",
    "properties": {"overview": {"type": "string"}},
    "required": ["overview"],
    "additionalProperties": False,
}

TRANSLATE_SYSTEM = (
    "あなたは Zscaler 製品の運用担当者向けに、公式リリースノートを日本語に訳すアシスタントです。"
    "入力は記事ごとの JSON で、本文 (markdown) は Markdown です。記事ごとに次を返してください。\n"
    "- title_ja: 記事タイトルの自然な日本語訳\n"
    "- summary_ja: 何が変わり、運用上どう影響するかを 1〜2 文で\n"
    "- translation_ja: 本文の全文訳。省略・要約・意訳による情報の追加はしないこと。"
    "Markdown の構造 (段落、- による箇条書きと字下げ、[テキスト](URL) のリンク、**強調**) を保ち、"
    "URL は変更しないこと。\n"
    "製品名・機能名・設定画面のパス (例: Administration > Role Management)・UI のボタン名・"
    "バージョン番号は原文の英語表記のまま残してください。id は入力の id をそのまま返してください。"
)

OVERVIEW_SYSTEM = (
    "あなたは Zscaler 製品の運用担当者向けに、週次のリリースノートをまとめるアシスタントです。"
    "与えられた記事の日本語タイトルと要約から、そのサービスの今週の変更点の全体像を"
    "日本語 2〜4 文で書いてください。入力にない情報は書かないこと。"
)


def item_markdown(it: dict) -> str:
    """記事本文を Markdown にする (翻訳の入力と、原文表示の両方に使う)。"""
    body = html_to_md(absolutize(it["description"])) if it["description"] else ""
    if it["extra"]:
        body += ("\n\n" if body else "") + plain_text(it["extra"])
    return body


def fallback_summary(svc: dict, reason: str = "ANTHROPIC_API_KEY 未設定") -> dict:
    items = {}
    for it in svc["items"]:
        text = plain_text(it["description"])
        first = re.split(r"(?<=[.!?])\s", text, maxsplit=1)[0] if text else ""
        items[it["id"]] = {"title_ja": it["title"], "summary_ja": first, "translation_ja": ""}
    return {"overview": "", "items": items, "source": f"原文のまま ({reason})"}


def _claude_json(client, system: str, prompt: str, schema: dict) -> tuple[dict, str]:
    with client.beta.messages.stream(
        model=CLAUDE_MODEL,
        max_tokens=64000,
        betas=["server-side-fallback-2026-07-01"],
        fallbacks="default",
        system=system,
        output_config={"effort": "medium",
                       "format": {"type": "json_schema", "schema": schema}},
        messages=[{"role": "user", "content": prompt}],
    ) as stream:
        response = stream.get_final_message()
    if response.stop_reason in ("refusal", "max_tokens"):
        raise RuntimeError(f"stop_reason={response.stop_reason}")
    text = next(b.text for b in response.content if b.type == "text")
    return json.loads(text), response.model


def chunk_items(items: list[dict]) -> list[list[dict]]:
    chunks: list[list[dict]] = [[]]
    size = 0
    for it in items:
        n = len(it["title"]) + len(it["markdown"])
        if chunks[-1] and size + n > TRANSLATE_CHUNK_CHARS:
            chunks.append([])
            size = 0
        chunks[-1].append(it)
        size += n
    return chunks


def claude_summary(client, svc: dict) -> dict:
    result = fallback_summary(svc, "翻訳に失敗した記事は原文のまま")
    translated = 0
    model = CLAUDE_MODEL
    payload = [{
        "id": it["id"],
        "title": it["title"],
        "status": it["status_label"],
        "version": it["version"],
        "markdown": item_markdown(it),
    } for it in svc["items"]]

    for chunk in chunk_items(payload):
        prompt = (f"サービス: {svc['name']}\n"
                  "以下は Zscaler 公式リリースノートの記事です (JSON)。\n\n"
                  + json.dumps(chunk, ensure_ascii=False, indent=1))
        try:
            data, model = _claude_json(client, TRANSLATE_SYSTEM, prompt, ITEMS_SCHEMA)
        except Exception as e:  # noqa: BLE001
            # 1チャンクの失敗で全体を諦めない。その記事だけ原文のまま残す
            print(f"  [WARN] {svc['name']}: {len(chunk)} 件の翻訳に失敗 — {e}")
            continue
        wanted = {c["id"] for c in chunk}
        for i in data["items"]:
            if i["id"] in wanted:
                result["items"][i["id"]] = i
                translated += 1

    if translated:
        digest = [{"title_ja": result["items"][it["id"]]["title_ja"],
                   "summary_ja": result["items"][it["id"]]["summary_ja"]} for it in svc["items"]]
        try:
            data, _ = _claude_json(
                client, OVERVIEW_SYSTEM,
                f"サービス: {svc['name']}\n\n" + json.dumps(digest, ensure_ascii=False, indent=1),
                OVERVIEW_SCHEMA)
            result["overview"] = data["overview"]
        except Exception as e:  # noqa: BLE001
            print(f"  [WARN] {svc['name']}: 概要の生成に失敗 — {e}")

    total = len(svc["items"])
    result["source"] = (f"Claude ({model}) で日本語訳" if translated == total
                        else f"Claude ({model}) で日本語訳 {translated}/{total} 件、残りは原文のまま")
    return result


def summarize_all(services: dict[str, dict]) -> None:
    client = None
    if os.environ.get("ANTHROPIC_API_KEY"):
        try:
            import anthropic
            client = anthropic.Anthropic()
        except Exception as e:  # noqa: BLE001
            print(f"[WARN] anthropic SDK を使えません: {e}")
    for stem, svc in services.items():
        if not svc["items"]:
            continue
        if client is None:
            svc["summary"] = fallback_summary(svc)
            continue
        print(f"[TRANSLATE] {svc['name']}: {len(svc['items'])} 件")
        svc["summary"] = claude_summary(client, svc)


# ── HTML ─────────────────────────────────────────────────────────────────────
HTML_STYLE = """
:root{--bg:#f6f8fb;--card:#fff;--text:#1b2430;--muted:#5b6778;--line:#e1e6ee;
--accent:#0b63ce;--chip:#eef4fd;--warn:#a35b00;--warnbg:#fff4e0}
@media (prefers-color-scheme: dark){:root{--bg:#0f141b;--card:#18202a;--text:#e6ebf2;
--muted:#9aa6b6;--line:#2a3442;--accent:#5aa2ff;--chip:#1e2b3c;--warn:#ffb85c;--warnbg:#3a2a10}}
*{box-sizing:border-box}body{overflow-wrap:anywhere;margin:0;background:var(--bg);color:var(--text);
font:15px/1.7 -apple-system,BlinkMacSystemFont,"Hiragino Sans","Noto Sans JP","Segoe UI",sans-serif}
main{max-width:880px;margin:0 auto;padding:24px 16px 48px}
h1{font-size:1.45rem;margin:0 0 4px}h2{font-size:1.1rem;margin:32px 0 12px;
padding-bottom:6px;border-bottom:1px solid var(--line)}
.meta{color:var(--muted);font-size:.85rem}
.overview{background:var(--card);border:1px solid var(--line);border-left:4px solid var(--accent);
border-radius:8px;padding:14px 16px;margin:18px 0}
.item{background:var(--card);border:1px solid var(--line);border-radius:8px;padding:14px 16px;margin:12px 0}
.item h3{font-size:1rem;margin:0 0 2px}.orig{color:var(--muted);font-size:.85rem;margin:0 0 8px}
.chips{display:flex;flex-wrap:wrap;gap:6px;margin:8px 0 0}
.chip{background:var(--chip);border-radius:999px;padding:1px 10px;font-size:.78rem;color:var(--muted)}
.chip.in{color:var(--accent);font-weight:600}
.late{display:inline-block;background:var(--warnbg);color:var(--warn);border-radius:4px;
padding:0 8px;font-size:.78rem;margin-left:6px}
.status{display:inline-block;border:1px solid var(--line);border-radius:4px;padding:0 6px;
margin-right:6px;font-size:.75rem}
.summary{font-weight:600}
.body{border-top:1px dashed var(--line);margin-top:10px;padding-top:6px}
.body ul{margin:4px 0;padding-left:1.3em}.body li{margin:2px 0}
.body pre{margin:0;font-size:.8rem;overflow-x:auto}
.en{color:var(--muted);font-size:.9rem}.note{font-size:.78rem}
details summary{cursor:pointer;color:var(--muted);font-size:.85rem;margin-top:8px}
code{background:var(--chip);border-radius:3px;padding:0 4px}
a{color:var(--accent)}p{margin:6px 0}
"""


def _inline_html(text: str) -> str:
    """Markdown の行内要素 (リンク・強調・コード) だけを HTML にする。それ以外はエスケープ。"""
    out, pos = [], 0
    for m in re.finditer(r"\[([^\]]+)\]\((https?://[^)\s]+)\)|\*\*(.+?)\*\*|`([^`]+)`", text):
        out.append(html.escape(text[pos:m.start()]))
        if m.group(1):
            out.append(f"<a href='{html.escape(m.group(2))}'>{html.escape(m.group(1))}</a>")
        elif m.group(3):
            out.append(f"<b>{html.escape(m.group(3))}</b>")
        else:
            out.append(f"<code>{html.escape(m.group(4))}</code>")
        pos = m.end()
    out.append(html.escape(text[pos:]))
    return "".join(out)


def md_to_html(md: str) -> str:
    """html_to_md / 翻訳結果の Markdown を表示用 HTML にする (段落・入れ子の箇条書き・表は等幅)。"""
    out: list[str] = []
    depth = 0          # 開いている <ul> の数
    para: list[str] = []

    def flush_para():
        if para:
            out.append("<p>" + "<br>".join(_inline_html(x) for x in para) + "</p>")
            para.clear()

    def close_lists(to: int = 0):
        nonlocal depth
        while depth > to:
            out.append("</li></ul>")
            depth -= 1

    for raw in md.splitlines():
        line = raw.rstrip()
        m = re.match(r"^(\s*)(?:[-*+]|\d+\.)\s+(.*)$", line)
        if m:
            flush_para()
            level = len(m.group(1).replace("\t", "  ")) // 2 + 1
            if level > depth:
                while depth < level:
                    out.append("<ul><li>")
                    depth += 1
            else:
                close_lists(level)
                out.append("</li><li>")
            out.append(_inline_html(m.group(2)))
            continue
        if not line.strip():
            flush_para()
            close_lists()
            continue
        h = re.match(r"^(#{1,6})\s+(.*)$", line)
        if h:
            flush_para()
            close_lists()
            out.append(f"<p><b>{_inline_html(h.group(2))}</b></p>")
            continue
        if line.lstrip().startswith("|"):
            flush_para()
            close_lists()
            out.append(f"<pre>{html.escape(line)}</pre>")
            continue
        if depth:
            out.append("<br>" + _inline_html(line.strip()))
        else:
            para.append(line.strip())
    flush_para()
    close_lists()
    return "".join(out).replace("<ul><li></li><li>", "<ul><li>")


def page_title_ja(title: str) -> str:
    return (title.replace("Release Upgrade Summary", "リリース・アップグレード概要")
                 .replace("Release Summary", "リリース概要"))


def render_html(svc: dict, window: tuple[date, date]) -> str:
    start, end = window
    s = svc["summary"]
    esc = html.escape
    out = [
        "<!doctype html><html lang='ja'><head><meta charset='utf-8'>",
        "<meta name='viewport' content='width=device-width,initial-scale=1'>",
        f"<title>{esc(svc['name'])} 週次アップデート {start}〜{end}</title>",
        f"<style>{HTML_STYLE}</style></head><body><main>",
        f"<h1>{esc(svc['name'])}</h1>",
        f"<div class='meta'>対象週 {start} 〜 {end} ・ {len(svc['items'])} 件 ・ {esc(s['source'])}</div>",
    ]
    if s["overview"]:
        out.append(f"<div class='overview'>{esc(s['overview'])}</div>")

    groups: dict[str, list[dict]] = {}
    for it in svc["items"]:
        groups.setdefault(it["page_title"], []).append(it)
    for label, items in groups.items():
        out.append(f"<h2>{esc(page_title_ja(label))} ({len(items)} 件)</h2>")
        for it in items:
            si = s["items"].get(it["id"], {})
            title_ja = si.get("title_ja") or it["title"]
            late = "<span class='late'>遅れて掲載</span>" if it["late"] else ""
            ver = f" ・ v{esc(it['version'])}" if it["version"] else ""
            chips = []
            for dep in sorted(it["deployments"], key=lambda x: (x["date"], x["category"])):
                inside = start.isoformat() <= dep["date"] <= end.isoformat()
                chips.append(f"<span class='chip{' in' if inside else ''}'>"
                             f"{esc(dep['category'])} {esc(dep['date'])}</span>")
            out += [
                "<div class='item'>",
                f"<h3>{esc(title_ja)}{late}</h3>",
                f"<p class='orig'><span class='status'>{esc(STATUS_JA.get(it['status'], it['status']))}</span>"
                f"{esc(it['title'])}{ver}</p>",
                f"<p class='summary'>{esc(si.get('summary_ja', ''))}</p>",
                f"<div class='chips'>{''.join(chips)}</div>",
            ]
            original = md_to_html(item_markdown(it))
            if si.get("translation_ja"):
                out.append(f"<div class='body'>{md_to_html(si['translation_ja'])}</div>")
                if original:
                    out.append(f"<details><summary>原文 (English)</summary>"
                               f"<div class='body en'>{original}</div></details>")
            elif original:
                # 翻訳できなかった記事は原文をそのまま見せる
                out.append(f"<div class='body en'><p class='note'>日本語訳なし (原文)</p>{original}</div>")
            out += [
                f"<p><a href='{esc(deep_link(it['path'], it, window))}'>help.zscaler.com で開く</a></p>",
                "</div>",
            ]
    out.append("</main></body></html>")
    return "\n".join(out)


def render_email(services: dict[str, dict], window: tuple[date, date],
                 checked: list[str], failures: list[str]) -> str:
    start, end = window
    esc = html.escape
    total = sum(len(s["items"]) for s in services.values())
    rows = []
    for stem, svc in services.items():
        if not svc["items"]:
            continue
        ov = svc["summary"]["overview"] or "、".join(
            svc["summary"]["items"].get(i["id"], {}).get("title_ja", i["title"]) for i in svc["items"][:5])
        rows.append(
            f"<tr><td style='padding:8px;border-bottom:1px solid #e1e6ee;white-space:nowrap;vertical-align:top'>"
            f"<b>{esc(svc['name'])}</b><br><span style='color:#5b6778'>{len(svc['items'])} 件</span></td>"
            f"<td style='padding:8px;border-bottom:1px solid #e1e6ee'>{esc(ov)}</td></tr>")
    quiet = [svc["name"] for svc in services.values() if not svc["items"]]
    parts = [
        "<div style='font-family:sans-serif;font-size:14px;line-height:1.6;color:#1b2430'>",
        f"<h2 style='margin:0 0 4px'>Zscaler 週次アップデート</h2>",
        f"<p style='margin:0 0 12px;color:#5b6778'>対象週 {start} 〜 {end} (JST) ・ 合計 {total} 件</p>",
    ]
    if failures:
        parts.append(
            "<p style='background:#fff4e0;color:#a35b00;padding:8px 12px;border-radius:6px'>"
            "<b>取得に失敗したページがあります。</b>以下は今回確認できていません:<br>"
            + "<br>".join(esc(f) for f in failures) + "</p>")
    if rows:
        parts.append("<table style='border-collapse:collapse;width:100%'>" + "".join(rows) + "</table>")
        parts.append("<p>サービスごとの要約は添付の <b>.html</b>、原文は <b>.md</b> を開いてください。</p>")
    else:
        parts.append("<p>今週の対象期間に展開されたアップデート記事はありませんでした。</p>")
    if quiet:
        parts.append(f"<p style='color:#5b6778;font-size:12px'>更新なし: {esc('、'.join(quiet))}</p>")
    parts.append(f"<p style='color:#5b6778;font-size:12px'>確認したページ ({len(checked)}): "
                 + esc(", ".join(checked)) + "</p></div>")
    return "\n".join(parts)


def send_email(subject: str, body_html: str, attachments: list[Path]) -> None:
    password = os.environ.get("GMAIL_APP_PASSWORD")
    if not password:
        raise RuntimeError("GMAIL_APP_PASSWORD が設定されていません")
    to = os.environ.get("NOTIFY_EMAIL_TO") or GMAIL_ADDRESS
    msg = MIMEMultipart("mixed")
    msg["Subject"] = subject
    msg["From"] = f"Zscaler Weekly Digest <{GMAIL_ADDRESS}>"
    msg["To"] = to
    msg.attach(MIMEText(body_html, "html", "utf-8"))
    for p in attachments:
        sub = "html" if p.suffix == ".html" else "markdown"
        part = MIMEText(p.read_text("utf-8"), sub, "utf-8")
        part.add_header("Content-Disposition", "attachment", filename=p.name)
        msg.attach(part)
    with smtplib.SMTP_SSL("smtp.gmail.com", 465) as server:
        server.login(GMAIL_ADDRESS, password)
        server.sendmail(GMAIL_ADDRESS, [to], msg.as_string())
    print(f"[MAIL] {to} へ送信 (添付 {len(attachments)} 件)")


# ── main ─────────────────────────────────────────────────────────────────────
def main() -> int:
    ap = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    ap.add_argument("--end-date", help="対象週の最終日 YYYY-MM-DD (既定: JST で直近の金曜)")
    ap.add_argument("--no-email", action="store_true", help="メールを送らない")
    ap.add_argument("--no-save-state", action="store_true", help="既知記事の記録を更新しない")
    ap.add_argument("--no-late", action="store_true", help="遅れて掲載された記事を含めない")
    args = ap.parse_args()

    window = week_window(date.fromisoformat(args.end_date) if args.end_date else None)
    start, end = window
    print(f"[START] 対象週 {start} 〜 {end}")

    pages = discover_pages({start.year, end.year})
    if not pages:
        print("[ERROR] リリースノートのページが1件も見つかりません")
        return 1
    print(f"[PAGES] {len(pages)} ページ")

    seen = load_seen()
    first_run = seen is None
    seen = seen or set()
    all_ids: set[str] = set()
    failures: list[str] = []
    services: dict[str, dict] = {}

    for path in pages:
        stem = SUMMARY_PATH_RE.match(path).group(1)
        try:
            meta, items = collect_page(path)
        except Exception as e:  # noqa: BLE001
            print(f"  [SKIP] {path} — {e}")
            failures.append(path)
            continue
        svc = services.setdefault(stem, {"pages": [], "items": []})
        svc["pages"].append(meta)
        hits = 0
        for item in items.values():
            key = f"{path}#{item['id']}"
            all_ids.add(key)
            in_window = any(start.isoformat() <= d["date"] <= end.isoformat() for d in item["deployments"])
            late = (not in_window and not first_run and not args.no_late and key not in seen
                    and max(d["date"] for d in item["deployments"]) < start.isoformat())
            if in_window or late:
                item.update(path=path, page_title=meta["page_title"], late=late)
                svc["items"].append(item)
                hits += 1
        print(f"  {path}: {len(items)} 件中 {hits} 件")

    for stem, svc in services.items():
        svc["name"] = service_name(stem, svc["pages"])
        # 対象週の記事を新しい順、その後に遅れて掲載の記事
        svc["items"].sort(key=lambda i: (not i["late"], max(d["date"] for d in i["deployments"])),
                          reverse=True)

    ordered = sorted(services, key=lambda s: (SERVICE_ORDER.index(s) if s in SERVICE_ORDER else 99,
                                              services[s]["name"].lower()))
    services = {s: services[s] for s in ordered}

    summarize_all(services)

    out_dir = OUTPUT_ROOT / end.isoformat()
    out_dir.mkdir(parents=True, exist_ok=True)
    attachments: list[Path] = []
    for stem, svc in services.items():
        if not svc["items"]:
            continue
        md_path = out_dir / f"{stem}_{end:%Y%m%d}.md"
        html_path = out_dir / f"{stem}_{end:%Y%m%d}.html"
        md_path.write_text(render_md(stem, svc, window), "utf-8")
        html_path.write_text(render_html(svc, window), "utf-8")
        attachments += [html_path, md_path]
        print(f"[WRITE] {svc['name']}: {len(svc['items'])} 件 → {html_path.name}, {md_path.name}")

    body = render_email(services, window, pages, failures)
    (out_dir / "email.html").write_text(body, "utf-8")
    total = sum(len(s["items"]) for s in services.values())
    subject = f"[Zscaler] 週次アップデート {start:%m/%d}〜{end:%m/%d} ({total}件)"

    if not args.no_email:
        send_email(subject, body, attachments)
    else:
        print(f"[MAIL] 送信スキップ: {subject}")

    if not args.no_save_state:
        save_seen(seen | all_ids, min_year=end.year - 1)

    if first_run:
        print("[INFO] 既知記事の記録が無いため、遅れて掲載の判定は行っていません")
    print(f"[DONE] {total} 件 / 失敗 {len(failures)} ページ")
    # 全滅は失敗扱い。一部失敗はメール本文で知らせる
    return 1 if failures and len(failures) == len(pages) else 0


if __name__ == "__main__":
    sys.exit(main())
