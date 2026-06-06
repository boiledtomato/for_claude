"""
Zscaler Release Notes Monitor
- Scrapes https://help.zscaler.com/zia/release-upgrade-summary-2026
- 機能・項目ごとに個別 .md ファイルを生成
- 既送信済み記事は除外（重複防止）
- OneDrive (000_Fleeting-Notes) にアップロード
- Gmail で差分のみ通知
"""

import os
import re
import sys
import json
import smtplib
import hashlib
import subprocess
from datetime import datetime
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pathlib import Path

from bs4 import BeautifulSoup, NavigableString
from playwright.sync_api import sync_playwright

# ── Config ────────────────────────────────────────────────────────────────────
TARGET_URL   = "https://help.zscaler.com/zia/release-upgrade-summary-2026"
RCLONE_DEST  = "onedrive:drive_folder/Obsidian/my_repository/000_Fleeting-Notes"
OUTPUT_DIR   = Path("output/fleeting_notes")
SEEN_FILE    = Path("data/zscaler_seen.json")   # 既送信IDを追跡

GMAIL_ADDRESS      = "ciderred1239@gmail.com"
GMAIL_APP_PASSWORD = os.environ["GMAIL_APP_PASSWORD"]
RECIPIENT_EMAIL    = os.environ["NOTIFY_EMAIL_FROM"]
MONTH_FILTER       = os.environ.get("MONTH_FILTER", "May")

MONTH_JP = {
    "January":"1月","February":"2月","March":"3月","April":"4月",
    "May":"5月","June":"6月","July":"7月","August":"8月",
    "September":"9月","October":"10月","November":"11月","December":"12月",
}

MONTH_PAT = re.compile(
    r"(January|February|March|April|May|June|July|August|"
    r"September|October|November|December)"
    r"[\s,]*\d{1,2}[,\s]*\d{4}",
    re.IGNORECASE,
)


# ── Deduplication ─────────────────────────────────────────────────────────────
def load_seen() -> set:
    if SEEN_FILE.exists():
        return set(json.loads(SEEN_FILE.read_text("utf-8")))
    return set()


def save_seen(seen: set):
    SEEN_FILE.parent.mkdir(parents=True, exist_ok=True)
    SEEN_FILE.write_text(json.dumps(sorted(seen), indent=2, ensure_ascii=False), "utf-8")


def article_id(date_str: str, title: str) -> str:
    raw = f"{date_str}::{title}".lower()
    return hashlib.sha256(raw.encode()).hexdigest()[:16]


# ── Page Fetch ────────────────────────────────────────────────────────────────
def fetch_page_html(url: str) -> str:
    with sync_playwright() as p:
        browser = p.chromium.launch(args=["--no-sandbox"])
        page = browser.new_page()
        page.goto(url, wait_until="networkidle", timeout=60000)
        page.wait_for_timeout(5000)
        html = page.content()
        browser.close()
    # デバッグ用にHTMLを保存
    Path("output").mkdir(exist_ok=True)
    Path("output/debug_page.html").write_text(html, encoding="utf-8")
    print(f"[DEBUG] HTML saved ({len(html)} chars)")
    return html


# ── Data Model ────────────────────────────────────────────────────────────────
class UpdateItem:
    def __init__(self, title: str, date_str: str, feature: str, body_md: str):
        self.title    = title
        self.date_str = date_str
        self.feature  = feature
        self.body_md  = body_md
        self.id       = article_id(date_str, title)

    def to_markdown(self) -> str:
        month_jp = MONTH_JP.get(MONTH_FILTER, MONTH_FILTER)
        return (
            f"---\n"
            f"source: Zscaler ZIA Release Notes 2026\n"
            f"date: {self.date_str}\n"
            f"feature: {self.feature}\n"
            f"tags: [zscaler, zia, release-notes, {month_jp}]\n"
            f"created: {datetime.utcnow().strftime('%Y-%m-%d')}\n"
            f"article_id: {self.id}\n"
            f"---\n\n"
            f"# {self.title}\n\n"
            f"**リリース日:** {self.date_str}  \n"
            f"**機能カテゴリ:** {self.feature}\n\n"
            f"---\n\n"
            f"{self.body_md}\n"
        )

    def safe_filename(self) -> str:
        date_slug = re.sub(r"[^\w]", "_", self.date_str)
        feat_slug = re.sub(r"[^\w]", "_", self.feature[:40])
        return f"Zscaler_ZIA_{date_slug}_{feat_slug}.md"


# ── HTML → Markdown ───────────────────────────────────────────────────────────
def element_to_md(el) -> str:
    """Recursively convert a BeautifulSoup element to Markdown."""
    if isinstance(el, NavigableString):
        return str(el)

    tag = el.name
    if tag is None:
        return ""

    children = "".join(element_to_md(c) for c in el.children).strip()

    if tag in ("h1",):          return f"\n# {children}\n"
    if tag in ("h2",):          return f"\n## {children}\n"
    if tag in ("h3",):          return f"\n### {children}\n"
    if tag in ("h4", "h5"):     return f"\n#### {children}\n"
    if tag == "p":              return f"\n{children}\n"
    if tag == "strong":         return f"**{children}**"
    if tag == "em":             return f"*{children}*"
    if tag == "code":           return f"`{children}`"
    if tag == "a":              return children
    if tag == "li":             return f"- {children}\n"
    if tag in ("ul", "ol"):     return f"\n{children}\n"
    if tag == "br":             return "\n"
    if tag in ("div", "section", "article", "span", "td", "th"):
        return f"\n{children}\n" if children else ""
    if tag == "tr":             return f"{children}\n"
    if tag == "table":          return f"\n{children}\n"
    return children


# ── Parser ────────────────────────────────────────────────────────────────────
def parse_updates(html: str, month_filter: str) -> list[UpdateItem]:
    soup = BeautifulSoup(html, "html.parser")
    target = month_filter.lower()
    items: list[UpdateItem] = []

    # ── Strategy 1: heading-based walk ────────────────────────────────────────
    all_headings = soup.find_all(re.compile(r"^h[1-5]$"))

    in_target_month = False
    current_date    = ""
    current_feature = ""
    pending: list   = []

    def flush():
        nonlocal pending
        if not (in_target_month and pending and current_feature):
            pending = []
            return
        body = "\n".join(element_to_md(e) for e in pending)
        body = re.sub(r"\n{3,}", "\n\n", body).strip()
        if len(body) < 30:
            pending = []
            return
        title = current_feature if current_feature else f"Update {current_date}"
        items.append(UpdateItem(title, current_date, current_feature, body))
        pending = []

    for h in all_headings:
        text = h.get_text(" ", strip=True)
        m = MONTH_PAT.search(text)
        if m:
            flush()
            in_target_month = target in m.group(0).lower()
            current_date    = m.group(0).strip()
            current_feature = text
            continue

        if in_target_month:
            flush()
            current_feature = text
            for sib in h.find_next_siblings():
                if sib.name and re.match(r"^h[1-5]$", sib.name):
                    break
                pending.append(sib)

    flush()

    # ── Strategy 2: text search blocks ────────────────────────────────────────
    if not items:
        print("[DEBUG] Strategy 1 found nothing — trying strategy 2")
        for block in soup.find_all(True):
            text = block.get_text(" ", strip=True)
            if target not in text.lower():
                continue
            if len(text) < 200 or len(text) > 50000:
                continue
            if block.name not in ("section", "article", "div", "main"):
                continue

            # 内部のサブセクションを機能ごとに分割
            sub_heads = block.find_all(re.compile(r"^h[2-5]$"))
            if sub_heads:
                for sh in sub_heads:
                    feature = sh.get_text(" ", strip=True)
                    sub_content = []
                    for sib in sh.find_next_siblings():
                        if sib.name and re.match(r"^h[2-5]$", sib.name):
                            break
                        sub_content.append(sib)
                    if sub_content:
                        body = "\n".join(element_to_md(e) for e in sub_content)
                        body = re.sub(r"\n{3,}", "\n\n", body).strip()
                        if len(body) > 30:
                            items.append(UpdateItem(
                                feature, f"{month_filter} 2026", feature, body
                            ))
                if items:
                    break
            else:
                body = element_to_md(block)
                body = re.sub(r"\n{3,}", "\n\n", body).strip()
                heading = block.find(re.compile(r"^h[1-5]$"))
                title = heading.get_text(strip=True) if heading else f"{month_filter} 2026 Update"
                items.append(UpdateItem(title, f"{month_filter} 2026", title, body))
                break

    return items


# ── File Output ───────────────────────────────────────────────────────────────
def write_md_files(items: list[UpdateItem]) -> list[Path]:
    OUTPUT_DIR.mkdir(parents=True, exist_ok=True)
    paths = []
    for item in items:
        path = OUTPUT_DIR / item.safe_filename()
        path.write_text(item.to_markdown(), encoding="utf-8")
        print(f"[WRITE] {path.name}")
        paths.append(path)
    return paths


# ── rclone Upload ─────────────────────────────────────────────────────────────
def upload_via_rclone(src_dir: Path) -> bool:
    result = subprocess.run(
        ["rclone", "copy", str(src_dir), RCLONE_DEST,
         "--transfers", "4", "--log-level", "INFO"],
        capture_output=True, text=True,
    )
    if result.returncode == 0:
        print(f"[RCLONE] Upload OK → {RCLONE_DEST}")
        return True
    print(f"[RCLONE ERROR] {result.stderr[:400]}")
    return False


# ── Email ─────────────────────────────────────────────────────────────────────
def send_email(new_items: list[UpdateItem], file_paths: list[Path]):
    if not new_items:
        subject   = f"[Zscaler Monitor] {MONTH_FILTER} 2026 — 新規更新なし"
        body_html = "<p>新しいZscalerアップデートはありませんでした。</p>"
        body_text = "新規更新なし"
    else:
        subject = (
            f"[Zscaler Monitor] ZIA {MONTH_FILTER} 2026 "
            f"新規アップデート ({len(new_items)}件)"
        )
        rows = "".join(
            f"<tr><td style='padding:4px 12px'>{i.date_str}</td>"
            f"<td style='padding:4px 12px'><strong>{i.feature}</strong></td>"
            f"<td style='padding:4px 12px'>{i.title}</td></tr>"
            for i in new_items
        )
        files_li = "".join(f"<li><code>{p.name}</code></li>" for p in file_paths)
        body_html = f"""
<html><body style="font-family:sans-serif;line-height:1.6">
<h2>🔔 Zscaler ZIA リリースノート — {MONTH_FILTER} 2026</h2>
<p><strong>{len(new_items)}件</strong>の新規アップデートを検出しました。</p>
<table border="1" cellspacing="0" cellpadding="0"
       style="border-collapse:collapse;margin:16px 0">
  <thead><tr style="background:#e8f0fe">
    <th style="padding:6px 12px">リリース日</th>
    <th style="padding:6px 12px">機能カテゴリ</th>
    <th style="padding:6px 12px">タイトル</th>
  </tr></thead>
  <tbody>{rows}</tbody>
</table>
<h3>📝 作成された Obsidian ノート</h3>
<ul>{files_li}</ul>
<p style="color:#555;font-size:12px">
  配備先: <code>000_Fleeting-Notes/</code><br>
  Source: <a href="{TARGET_URL}">{TARGET_URL}</a><br>
  Generated: {datetime.utcnow().strftime('%Y-%m-%d %H:%M UTC')}
</p>
</body></html>"""
        body_text = "\n".join(f"- {i.date_str} [{i.feature}]: {i.title}" for i in new_items)

    msg = MIMEMultipart("alternative")
    msg["Subject"] = subject
    msg["From"]    = GMAIL_ADDRESS
    msg["To"]      = RECIPIENT_EMAIL
    msg.attach(MIMEText(body_text, "plain", "utf-8"))
    msg.attach(MIMEText(body_html, "html",  "utf-8"))

    with smtplib.SMTP_SSL("smtp.gmail.com", 465) as server:
        server.login(GMAIL_ADDRESS, GMAIL_APP_PASSWORD)
        server.sendmail(GMAIL_ADDRESS, RECIPIENT_EMAIL, msg.as_string())
    print(f"[EMAIL] Sent → {RECIPIENT_EMAIL}: {subject}")


# ── Main ──────────────────────────────────────────────────────────────────────
def main():
    seen = load_seen()
    print(f"[INFO] {len(seen)} articles already seen")

    print(f"[START] Fetching {TARGET_URL}")
    html = fetch_page_html(TARGET_URL)

    print(f"[PARSE] Filtering for '{MONTH_FILTER}'")
    all_items = parse_updates(html, MONTH_FILTER)
    print(f"[INFO] {len(all_items)} item(s) found on page")

    # 新規のみ抽出
    new_items = [i for i in all_items if i.id not in seen]
    print(f"[INFO] {len(new_items)} new item(s) (not previously sent)")

    file_paths = write_md_files(new_items)

    if file_paths:
        print("[RCLONE] Uploading to OneDrive...")
        upload_via_rclone(OUTPUT_DIR)

    print("[EMAIL] Sending summary...")
    send_email(new_items, file_paths)

    # 送信済みIDを保存
    seen.update(i.id for i in new_items)
    save_seen(seen)
    print(f"[SEEN] Saved {len(seen)} total seen articles")

    print("[DONE]")
    return 0


if __name__ == "__main__":
    sys.exit(main())
