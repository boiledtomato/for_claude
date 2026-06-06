"""
Zscaler Release Notes Monitor
- Scrapes https://help.zscaler.com/zia/release-upgrade-summary-2026
- Creates per-update .md files
- Uploads to OneDrive via rclone (no Azure registration required)
- Sends summary email via Gmail SMTP
"""

import os
import re
import sys
import smtplib
import subprocess
from datetime import datetime
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pathlib import Path

from bs4 import BeautifulSoup
from playwright.sync_api import sync_playwright

# ── Config ────────────────────────────────────────────────────────────────────
TARGET_URL = "https://help.zscaler.com/zia/release-upgrade-summary-2026"
# rclone remote name + path inside OneDrive
RCLONE_DEST = "onedrive:drive_folder/Obsidian/my_repository/00_Fleeting_notes"
OUTPUT_DIR  = Path("output/fleeting_notes")

GMAIL_ADDRESS      = "ciderred1239@gmail.com"
GMAIL_APP_PASSWORD = os.environ["GMAIL_APP_PASSWORD"]
RECIPIENT_EMAIL    = os.environ["NOTIFY_EMAIL_FROM"]
MONTH_FILTER       = os.environ.get("MONTH_FILTER", "May")


# ── Page Fetch ────────────────────────────────────────────────────────────────
def fetch_page_html(url: str) -> str:
    with sync_playwright() as p:
        browser = p.chromium.launch(args=["--no-sandbox"])
        page = browser.new_page()
        page.goto(url, wait_until="networkidle", timeout=60000)
        page.wait_for_timeout(3000)
        html = page.content()
        browser.close()
    return html


# ── Data Model ────────────────────────────────────────────────────────────────
class UpdateItem:
    def __init__(self, title: str, date_str: str, section: str, content_html: str):
        self.title       = title
        self.date_str    = date_str
        self.section     = section
        self.content_html = content_html

    def to_markdown(self) -> str:
        soup = BeautifulSoup(self.content_html, "html.parser")
        body = _html_to_md(soup)
        return (
            f"---\n"
            f"source: Zscaler ZIA Release Notes 2026\n"
            f"date: {self.date_str}\n"
            f"section: {self.section}\n"
            f"tags: [zscaler, zia, release-notes, fleeting]\n"
            f"created: {datetime.utcnow().strftime('%Y-%m-%d')}\n"
            f"---\n\n"
            f"# {self.title}\n\n"
            f"**リリース日:** {self.date_str}  \n"
            f"**セクション:** {self.section}\n\n"
            f"## 内容\n\n"
            f"{body}\n"
        )

    def safe_filename(self) -> str:
        name = re.sub(r"[^\w\s\-]", "", self.title)
        name = re.sub(r"\s+", "_", name.strip())
        date_slug = self.date_str.replace(" ", "_").replace(",", "")
        return f"Zscaler_{date_slug}_{name[:80]}.md"


def _html_to_md(soup) -> str:
    lines = []
    for el in soup.find_all(["h1", "h2", "h3", "h4", "p", "li"]):
        text = el.get_text(separator=" ", strip=True)
        if not text:
            continue
        tag = el.name
        if tag in ("h1", "h2"):
            lines.append(f"\n## {text}\n")
        elif tag in ("h3", "h4"):
            lines.append(f"\n### {text}\n")
        elif tag == "li":
            lines.append(f"- {text}")
        elif tag == "p":
            lines.append(f"\n{text}\n")
    return "\n".join(lines)


# ── Parser ────────────────────────────────────────────────────────────────────
def parse_updates(html: str, month_filter: str) -> list[UpdateItem]:
    """
    Walks heading elements looking for date/month headers, then collects
    sub-sections within the target month as individual UpdateItems.
    """
    soup = BeautifulSoup(html, "html.parser")
    items: list[UpdateItem] = []
    target = month_filter.lower()

    headings = soup.find_all(re.compile(r"^h[1-4]$"))

    in_target      = False
    current_date   = ""
    current_section = ""
    pending_sibs: list = []

    month_pat = re.compile(
        r"(January|February|March|April|May|June|July|August|"
        r"September|October|November|December)"
        r"[\s,\d]+\d{4}", re.IGNORECASE
    )

    def flush():
        if pending_sibs and in_target:
            combined = "".join(str(e) for e in pending_sibs)
            title = current_section or f"Release {current_date}"
            items.append(UpdateItem(title, current_date, current_section, combined))
        pending_sibs.clear()

    for heading in headings:
        text = heading.get_text(strip=True)
        m = month_pat.search(text)
        if m:
            flush()
            in_target      = target in m.group(0).lower()
            current_date   = m.group(0).strip()
            current_section = text
            continue

        if in_target:
            flush()
            current_section = text
            for sib in heading.find_next_siblings():
                if sib.name and re.match(r"^h[1-4]$", sib.name):
                    break
                pending_sibs.append(sib)

    flush()
    return items


def fallback_parse(html: str, month_filter: str) -> list[UpdateItem]:
    """Broad fallback: grab any large block containing the target month."""
    soup   = BeautifulSoup(html, "html.parser")
    items  = []
    target = month_filter.lower()

    for block in soup.find_all(["section", "article", "div"]):
        text = block.get_text(separator=" ", strip=True)
        if target not in text.lower() or len(text) < 150:
            continue
        heading = block.find(re.compile(r"^h[1-4]$"))
        title   = heading.get_text(strip=True) if heading else f"{month_filter} Update"
        items.append(UpdateItem(title, f"{month_filter} 2026", month_filter, str(block)))
        break  # one big block is enough for fallback

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
    """Copy OUTPUT_DIR contents to OneDrive using rclone."""
    cmd = [
        "rclone", "copy",
        str(src_dir),
        RCLONE_DEST,
        "--transfers", "4",
        "--log-level", "INFO",
    ]
    result = subprocess.run(cmd, capture_output=True, text=True)
    if result.returncode == 0:
        print(f"[RCLONE] Upload OK → {RCLONE_DEST}")
        return True
    else:
        print(f"[RCLONE ERROR] {result.stderr[:400]}")
        return False


# ── Email ─────────────────────────────────────────────────────────────────────
def send_email(items: list[UpdateItem], file_paths: list[Path]):
    if not items:
        subject  = f"[Zscaler Monitor] {MONTH_FILTER} 2026 — 更新なし"
        body_html = "<p>今月のZscalerアップデートは見つかりませんでした。</p>"
        body_text = "更新なし"
    else:
        subject = (
            f"[Zscaler Monitor] ZIA {MONTH_FILTER} 2026 "
            f"アップデート ({len(items)}件)"
        )
        rows = "".join(
            f"<tr><td style='padding:4px 12px'>{i.date_str}</td>"
            f"<td style='padding:4px 12px'>{i.title}</td></tr>"
            for i in items
        )
        files_li = "".join(f"<li><code>{p.name}</code></li>" for p in file_paths)
        body_html = f"""
<html><body style="font-family:sans-serif;line-height:1.6">
<h2>🔔 Zscaler ZIA リリースノート — {MONTH_FILTER} 2026</h2>
<p><strong>{len(items)}件</strong>のアップデートを検出しました。</p>
<table border="1" cellspacing="0" cellpadding="0"
       style="border-collapse:collapse;margin:16px 0">
  <thead><tr style="background:#e8f0fe">
    <th style="padding:6px 12px">リリース日</th>
    <th style="padding:6px 12px">タイトル</th>
  </tr></thead>
  <tbody>{rows}</tbody>
</table>
<h3>📝 作成された Obsidian ノート</h3>
<ul>{files_li}</ul>
<p style="color:#555;font-size:12px">
  配備先: <code>OneDrive/drive_folder/Obsidian/my_repository/00_Fleeting_notes/</code><br>
  Source: <a href="{TARGET_URL}">{TARGET_URL}</a><br>
  Generated: {datetime.utcnow().strftime('%Y-%m-%d %H:%M UTC')}
</p>
</body></html>"""
        body_text = "\n".join(f"- {i.date_str}: {i.title}" for i in items)

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
    print(f"[START] Fetching {TARGET_URL}")
    html = fetch_page_html(TARGET_URL)

    print(f"[PARSE] Filtering for '{MONTH_FILTER}'")
    items = parse_updates(html, MONTH_FILTER)

    if not items:
        print("[WARN] Primary parser found nothing — trying fallback")
        items = fallback_parse(html, MONTH_FILTER)

    print(f"[INFO] {len(items)} update item(s) found")

    file_paths = write_md_files(items)

    if file_paths:
        print("[RCLONE] Uploading to OneDrive...")
        upload_via_rclone(OUTPUT_DIR)
    else:
        print("[SKIP] No files to upload")

    print("[EMAIL] Sending summary...")
    send_email(items, file_paths)

    print("[DONE]")
    return 0


if __name__ == "__main__":
    sys.exit(main())
