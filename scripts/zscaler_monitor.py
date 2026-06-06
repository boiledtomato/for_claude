"""
Zscaler Release Notes Monitor
- Scrapes https://help.zscaler.com/zia/release-upgrade-summary-2026
- Creates per-update .md files
- Uploads to OneDrive (Obsidian vault)
- Sends summary email via Gmail
"""

import os
import re
import sys
import smtplib
import hashlib
import json
from datetime import datetime
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pathlib import Path

import requests
from bs4 import BeautifulSoup
from playwright.sync_api import sync_playwright

# ── Config ────────────────────────────────────────────────────────────────────
TARGET_URL = "https://help.zscaler.com/zia/release-upgrade-summary-2026"
OBSIDIAN_ONEDRIVE_PATH = "drive_folder/Obsidian/my_repository/00_Fleeting_notes"
OUTPUT_DIR = Path("output/fleeting_notes")

GMAIL_ADDRESS      = os.environ["GMAIL_ADDRESS"]
GMAIL_APP_PASSWORD = os.environ["GMAIL_APP_PASSWORD"]
RECIPIENT_EMAIL    = os.environ.get("RECIPIENT_EMAIL", GMAIL_ADDRESS)

ONEDRIVE_CLIENT_ID     = os.environ["ONEDRIVE_CLIENT_ID"]
ONEDRIVE_CLIENT_SECRET = os.environ["ONEDRIVE_CLIENT_SECRET"]
ONEDRIVE_REFRESH_TOKEN = os.environ["ONEDRIVE_REFRESH_TOKEN"]

MONTH_FILTER = os.environ.get("MONTH_FILTER", "May")  # e.g. "May", "June"


# ── Page Fetch ────────────────────────────────────────────────────────────────
def fetch_page_html(url: str) -> str:
    with sync_playwright() as p:
        browser = p.chromium.launch(args=["--no-sandbox"])
        page = browser.new_page()
        page.goto(url, wait_until="networkidle", timeout=60000)
        # Extra wait for JS-heavy pages
        page.wait_for_timeout(3000)
        html = page.content()
        browser.close()
    return html


# ── Parser ────────────────────────────────────────────────────────────────────
class UpdateItem:
    def __init__(self, title: str, date_str: str, section: str, content_html: str):
        self.title = title
        self.date_str = date_str
        self.section = section
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
        return f"Zscaler_{self.date_str}_{name[:80]}.md"


def _html_to_md(soup) -> str:
    """Simple HTML → Markdown conversion."""
    lines = []
    for el in soup.find_all(["h1", "h2", "h3", "h4", "p", "li", "ul", "ol"]):
        tag = el.name
        text = el.get_text(separator=" ", strip=True)
        if not text:
            continue
        if tag in ("h1", "h2"):
            lines.append(f"\n## {text}\n")
        elif tag in ("h3", "h4"):
            lines.append(f"\n### {text}\n")
        elif tag == "li":
            lines.append(f"- {text}")
        elif tag == "p":
            lines.append(f"\n{text}\n")
    return "\n".join(lines)


def parse_updates(html: str, month_filter: str) -> list[UpdateItem]:
    """
    Parse the Zscaler release notes page.
    Looks for date headers (e.g. 'May 2026', 'May 15, 2026') and
    collects content sections beneath them.
    """
    soup = BeautifulSoup(html, "html.parser")
    items: list[UpdateItem] = []

    # Find all heading elements that might be date/release headers
    headings = soup.find_all(re.compile(r"^h[1-4]$"))

    target_month = month_filter.lower()
    in_target = False
    current_date = ""
    current_section = ""
    current_content_els = []

    def flush(date, section, els):
        if not els:
            return
        combined = "".join(str(e) for e in els)
        title = f"{section}" if section else f"Release {date}"
        items.append(UpdateItem(title, date, section, combined))

    for heading in headings:
        text = heading.get_text(strip=True)

        # Detect month header (e.g. "May 2026" or "May 15, 2026")
        month_match = re.search(
            r"(January|February|March|April|May|June|July|August|"
            r"September|October|November|December)\s+\d+,?\s*\d{4}",
            text, re.IGNORECASE
        )
        # Also catch bare month + year
        month_year_match = re.search(
            r"(January|February|March|April|May|June|July|August|"
            r"September|October|November|December)\s+\d{4}",
            text, re.IGNORECASE
        )

        match = month_match or month_year_match
        if match:
            # Save previous section
            flush(current_date, current_section, current_content_els)
            current_content_els = []

            raw_date = match.group(0)
            in_target = target_month in raw_date.lower()
            current_date = raw_date
            current_section = text
            continue

        # Sub-sections within target month
        if in_target:
            flush(current_date, current_section, current_content_els)
            current_content_els = []
            current_section = text

            # Collect sibling content until next heading
            for sib in heading.find_next_siblings():
                if sib.name and re.match(r"^h[1-4]$", sib.name):
                    break
                current_content_els.append(sib)

    # Final flush
    flush(current_date, current_section, current_content_els)

    return items


def fallback_parse(html: str, month_filter: str) -> list[UpdateItem]:
    """
    Fallback: search for any <section>, <article>, or <div> blocks
    containing month_filter text.
    """
    soup = BeautifulSoup(html, "html.parser")
    items = []
    target = month_filter.lower()

    for block in soup.find_all(["section", "article", "div"], class_=True):
        text = block.get_text(separator=" ", strip=True)
        if target not in text.lower():
            continue
        if len(text) < 100:
            continue
        title = (block.find("h2") or block.find("h3") or block.find("h4") or block.find("h1"))
        title_text = title.get_text(strip=True) if title else f"{month_filter} Update"
        items.append(UpdateItem(title_text, month_filter + " 2026", month_filter, str(block)))

    return items


# ── File Output ───────────────────────────────────────────────────────────────
def write_md_files(items: list[UpdateItem]) -> list[Path]:
    OUTPUT_DIR.mkdir(parents=True, exist_ok=True)
    paths = []
    for item in items:
        path = OUTPUT_DIR / item.safe_filename()
        path.write_text(item.to_markdown(), encoding="utf-8")
        print(f"[WRITE] {path}")
        paths.append(path)
    return paths


# ── OneDrive Upload ───────────────────────────────────────────────────────────
def get_onedrive_token() -> str:
    resp = requests.post(
        "https://login.microsoftonline.com/common/oauth2/v2.0/token",
        data={
            "grant_type": "refresh_token",
            "client_id": ONEDRIVE_CLIENT_ID,
            "client_secret": ONEDRIVE_CLIENT_SECRET,
            "refresh_token": ONEDRIVE_REFRESH_TOKEN,
            "scope": "Files.ReadWrite offline_access",
        },
        timeout=30,
    )
    resp.raise_for_status()
    data = resp.json()
    # Save updated refresh token if rotated
    new_rt = data.get("refresh_token")
    if new_rt and new_rt != ONEDRIVE_REFRESH_TOKEN:
        print("[INFO] Refresh token rotated — update ONEDRIVE_REFRESH_TOKEN secret")
    return data["access_token"]


def upload_to_onedrive(file_path: Path, access_token: str) -> bool:
    remote_path = f"{OBSIDIAN_ONEDRIVE_PATH}/{file_path.name}"
    url = f"https://graph.microsoft.com/v1.0/me/drive/root:/{remote_path}:/content"
    headers = {
        "Authorization": f"Bearer {access_token}",
        "Content-Type": "text/plain; charset=utf-8",
    }
    resp = requests.put(url, headers=headers, data=file_path.read_bytes(), timeout=30)
    if resp.status_code in (200, 201):
        print(f"[UPLOAD] {file_path.name} → OneDrive OK")
        return True
    else:
        print(f"[ERROR] Upload failed {resp.status_code}: {resp.text[:200]}")
        return False


# ── Email ─────────────────────────────────────────────────────────────────────
def send_email(items: list[UpdateItem], file_paths: list[Path]):
    if not items:
        subject = f"[Zscaler Monitor] {MONTH_FILTER} 2026 — 更新なし"
        body_text = "今月のZscalerアップデートは見つかりませんでした。"
        body_html = f"<p>{body_text}</p>"
    else:
        subject = f"[Zscaler Monitor] ZIA {MONTH_FILTER} 2026 アップデート ({len(items)}件)"
        items_html = "".join(
            f"<li><strong>{i.title}</strong><br>{i.date_str} / {i.section}</li>"
            for i in items
        )
        files_html = "".join(f"<li>{p.name}</li>" for p in file_paths)
        body_html = f"""
<html><body>
<h2>Zscaler ZIA リリースノート — {MONTH_FILTER} 2026</h2>
<p>{len(items)}件のアップデートを検出しました。</p>
<h3>アップデート一覧</h3>
<ul>{items_html}</ul>
<h3>作成されたObsidianノート</h3>
<ul>{files_html}</ul>
<hr>
<p style="color:gray;font-size:12px">
  Source: <a href="{TARGET_URL}">{TARGET_URL}</a><br>
  Generated: {datetime.utcnow().strftime('%Y-%m-%d %H:%M UTC')}
</p>
</body></html>"""
        body_text = f"{len(items)}件のアップデート: " + ", ".join(i.title for i in items)

    msg = MIMEMultipart("alternative")
    msg["Subject"] = subject
    msg["From"] = GMAIL_ADDRESS
    msg["To"] = RECIPIENT_EMAIL
    msg.attach(MIMEText(body_text, "plain", "utf-8"))
    msg.attach(MIMEText(body_html, "html", "utf-8"))

    with smtplib.SMTP_SSL("smtp.gmail.com", 465) as server:
        server.login(GMAIL_ADDRESS, GMAIL_APP_PASSWORD)
        server.sendmail(GMAIL_ADDRESS, RECIPIENT_EMAIL, msg.as_string())
    print(f"[EMAIL] Sent to {RECIPIENT_EMAIL}: {subject}")


# ── Main ──────────────────────────────────────────────────────────────────────
def main():
    print(f"[START] Fetching {TARGET_URL}")
    html = fetch_page_html(TARGET_URL)

    print(f"[PARSE] Filtering for '{MONTH_FILTER}'")
    items = parse_updates(html, MONTH_FILTER)

    if not items:
        print("[WARN] Primary parser found nothing — trying fallback")
        items = fallback_parse(html, MONTH_FILTER)

    print(f"[INFO] Found {len(items)} update item(s)")

    file_paths = write_md_files(items)

    if file_paths:
        print("[ONEDRIVE] Uploading...")
        token = get_onedrive_token()
        for fp in file_paths:
            upload_to_onedrive(fp, token)
    else:
        print("[SKIP] No files to upload")

    print("[EMAIL] Sending summary...")
    send_email(items, file_paths)

    print("[DONE]")
    return 0 if items else 1


if __name__ == "__main__":
    sys.exit(main())
