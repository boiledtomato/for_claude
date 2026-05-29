#!/usr/bin/env python3
"""
Send a daily email digest of new Zscaler articles via SendGrid.

Required environment variables:
  SENDGRID_API_KEY  - SendGrid API key (set in GitHub Secrets)
  NOTIFY_EMAIL_FROM - Verified sender address in SendGrid (default: same as TO)
"""

import json
import os
import sys
from datetime import datetime, timezone
from pathlib import Path

SCRIPT_DIR = Path(__file__).parent
DATA_FILE = SCRIPT_DIR.parent / "data" / "articles.json"
NOTIFIED_FILE = SCRIPT_DIR.parent / "data" / "last_notified.json"

RECIPIENT = "ciderred1239@gmail.com"
SENDER = os.environ.get("NOTIFY_EMAIL_FROM", RECIPIENT)
SENDGRID_API_KEY = os.environ.get("SENDGRID_API_KEY", "")
DASHBOARD_URL = os.environ.get("DASHBOARD_URL", "https://boiledtomato.github.io/for_claude/")
MAX_IN_EMAIL = 5

CATEGORY_LABEL = {
    "product_update": "製品アップデート",
    "tech_blog": "技術ブログ",
    "industry_news": "業界ニュース",
}
CATEGORY_CSS = {
    "product_update": "cat-product",
    "tech_blog": "cat-tech",
    "industry_news": "cat-news",
}
TAG_CSS = {"ZIA": "tag-zia", "ZPA": "tag-zpa", "ZDX": "tag-zdx"}


def load_last_notified() -> str:
    try:
        data = json.loads(NOTIFIED_FILE.read_text())
        return data.get("last_notified_at", "")
    except Exception:
        return ""


def save_last_notified(ts: str) -> None:
    NOTIFIED_FILE.parent.mkdir(parents=True, exist_ok=True)
    NOTIFIED_FILE.write_text(json.dumps({"last_notified_at": ts}, indent=2))


def load_articles() -> list[dict]:
    try:
        data = json.loads(DATA_FILE.read_text())
        return data.get("articles", [])
    except Exception:
        return []


def new_articles(articles: list[dict], since: str) -> list[dict]:
    if not since:
        return articles[:MAX_IN_EMAIL]
    return [a for a in articles if a.get("date", "") > since]


def esc(s: str) -> str:
    return (s or "").replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace('"', "&quot;")


def render_article(a: dict) -> str:
    cat_label = CATEGORY_LABEL.get(a["category"], a["category"])
    cat_css = CATEGORY_CSS.get(a["category"], "cat-news")
    tags_html = "".join(
        f'<span class="tag {TAG_CSS.get(t, "")}">{esc(t)}</span>'
        for t in (a.get("tags") or [])
    )
    summary_html = (
        f'<div class="article-summary">{esc(a["summary"])}</div>'
        if a.get("summary") else ""
    )
    date_str = a.get("date", "")[:10]
    return f"""
    <div class="article">
      <span class="article-category {cat_css}">{esc(cat_label)}</span>
      <div class="article-title"><a href="{esc(a['url'])}">{esc(a['title'])}</a></div>
      <div class="article-meta">{esc(a['source'])} &nbsp;·&nbsp; {esc(date_str)}</div>
      {summary_html}
      <div class="tags">{tags_html}</div>
    </div>"""


def build_html(articles: list[dict]) -> str:
    today = datetime.now(timezone.utc).strftime("%Y年%-m月%-d日")
    articles_html = "".join(render_article(a) for a in articles)
    count = len(articles)
    more_note = ""
    if count >= MAX_IN_EMAIL:
        more_note = f'<p style="color:#64748b;font-size:13px;">他にも新着記事があります。ダッシュボードで全件確認できます。</p>'
    return f"""<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<style>
  body{{font-family:-apple-system,BlinkMacSystemFont,'Segoe UI','Noto Sans JP',sans-serif;
       background:#f1f5f9;margin:0;padding:20px;color:#1e293b}}
  .wrap{{max-width:600px;margin:0 auto;background:#fff;border-radius:12px;overflow:hidden;
         box-shadow:0 4px 24px rgba(0,0,0,.08)}}
  .hdr{{background:linear-gradient(135deg,#0c1a36 0%,#0f2457 100%);padding:28px 24px;text-align:center}}
  .hdr h1{{color:#38bdf8;margin:0;font-size:22px;letter-spacing:-.3px}}
  .hdr p{{color:#94a3b8;margin:6px 0 0;font-size:13px}}
  .bdy{{padding:24px}}
  .intro{{font-size:15px;color:#334155;margin-bottom:20px;line-height:1.6}}
  .intro strong{{color:#0f172a}}
  .article{{border:1px solid #e2e8f0;border-radius:10px;padding:18px;margin-bottom:14px;
             background:#fafbfc}}
  .article-category{{font-size:10px;font-weight:700;text-transform:uppercase;
                      letter-spacing:.06em;padding:3px 9px;border-radius:4px;
                      display:inline-block;margin-bottom:10px}}
  .cat-product{{background:#fff3e0;color:#c2410c}}
  .cat-tech{{background:#dcfce7;color:#166534}}
  .cat-news{{background:#f3e8ff;color:#7e22ce}}
  .article-title{{font-size:15px;font-weight:600;margin-bottom:6px;line-height:1.4}}
  .article-title a{{color:#0069C2;text-decoration:none}}
  .article-title a:hover{{text-decoration:underline}}
  .article-meta{{font-size:12px;color:#64748b;margin-bottom:10px}}
  .article-summary{{font-size:13px;color:#475569;line-height:1.65;margin-bottom:10px}}
  .tags{{display:flex;flex-wrap:wrap;gap:4px}}
  .tag{{font-size:11px;font-weight:600;padding:2px 9px;border-radius:4px}}
  .tag-zia{{background:#e0f2fe;color:#0369a1}}
  .tag-zpa{{background:#ede9fe;color:#5b21b6}}
  .tag-zdx{{background:#d1fae5;color:#065f46}}
  .btn{{display:block;text-align:center;background:#0069C2;color:#fff;text-decoration:none;
        padding:13px 24px;border-radius:9px;font-weight:700;font-size:14px;margin-top:22px;
        letter-spacing:.2px}}
  .ftr{{background:#f8fafc;padding:16px 24px;text-align:center;font-size:12px;
        color:#94a3b8;border-top:1px solid #e2e8f0}}
</style>
</head>
<body>
<div class="wrap">
  <div class="hdr">
    <h1>Zscaler 学習ダッシュボード</h1>
    <p>本日の新着情報 — {today}</p>
  </div>
  <div class="bdy">
    <p class="intro">本日は <strong>{count}件</strong> の新着Zscaler記事があります。</p>
    {articles_html}
    {more_note}
    <a class="btn" href="{DASHBOARD_URL}">ダッシュボードで全件確認する →</a>
  </div>
  <div class="ftr">
    このメールはZscaler学習ダッシュボードから自動送信されています。
  </div>
</div>
</body>
</html>"""


def build_text(articles: list[dict]) -> str:
    today = datetime.now(timezone.utc).strftime("%Y-%m-%d")
    lines = [f"Zscaler 学習ダッシュボード — {today}", f"{len(articles)}件の新着記事\n"]
    for i, a in enumerate(articles, 1):
        lines.append(f"{i}. [{CATEGORY_LABEL.get(a['category'], a['category'])}] {a['title']}")
        if a.get("summary"):
            lines.append(f"   {a['summary'][:120]}…")
        lines.append(f"   {a['url']}\n")
    lines.append(f"ダッシュボード: {DASHBOARD_URL}")
    return "\n".join(lines)


def send(articles: list[dict]) -> bool:
    try:
        import sendgrid
        from sendgrid.helpers.mail import Mail, Content, To
    except ImportError:
        print("[ERROR] sendgrid package not installed. Run: pip install sendgrid")
        return False

    if not SENDGRID_API_KEY:
        print("[ERROR] SENDGRID_API_KEY environment variable is not set.")
        return False

    today = datetime.now(timezone.utc).strftime("%Y年%-m月%-d日")
    subject = f"[Zscaler] 本日の新着記事 {today} ({len(articles)}件)"

    message = Mail(
        from_email=SENDER,
        to_emails=RECIPIENT,
        subject=subject,
        html_content=build_html(articles),
        plain_text_content=build_text(articles),
    )

    sg = sendgrid.SendGridAPIClient(api_key=SENDGRID_API_KEY)
    resp = sg.send(message)
    print(f"SendGrid response: {resp.status_code}")
    return resp.status_code in (200, 202)


def main():
    articles = load_articles()
    if not articles:
        print("No articles found. Skipping notification.")
        return

    last_notified = load_last_notified()
    print(f"Last notified: {last_notified or '(never)'}")

    new = new_articles(articles, last_notified)[:MAX_IN_EMAIL]
    if not new:
        print("No new articles since last notification. Skipping.")
        return

    print(f"Sending notification for {len(new)} new articles…")
    ok = send(new)
    if ok:
        now = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
        save_last_notified(now)
        print(f"Notification sent. Updated last_notified to {now}")
    else:
        print("Failed to send notification.", file=sys.stderr)
        sys.exit(1)


if __name__ == "__main__":
    main()
