#!/usr/bin/env python3
"""
Send a daily email digest of new Zscaler articles via Gmail SMTP.

Required environment variables:
  GMAIL_APP_PASSWORD - Gmail app password (Google Account → Security → App passwords)
  NOTIFY_EMAIL_FROM  - Gmail address to send from (default: ciderred1239@gmail.com)
"""

import json
import os
import smtplib
import ssl
import sys
from datetime import datetime, timezone
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pathlib import Path

SCRIPT_DIR = Path(__file__).parent
DATA_FILE = SCRIPT_DIR.parent / "data" / "articles.json"
NOTIFIED_FILE = SCRIPT_DIR.parent / "data" / "last_notified.json"

RECIPIENT = "ciderred1239@gmail.com"
SENDER = os.environ.get("NOTIFY_EMAIL_FROM", RECIPIENT)
GMAIL_APP_PASSWORD = os.environ.get("GMAIL_APP_PASSWORD", "")
DASHBOARD_URL = os.environ.get(
    "DASHBOARD_URL", "https://boiledtomato.github.io/for_claude/"
)
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
        return json.loads(NOTIFIED_FILE.read_text()).get("last_notified_at", "")
    except Exception:
        return ""


def save_last_notified(ts: str) -> None:
    NOTIFIED_FILE.parent.mkdir(parents=True, exist_ok=True)
    NOTIFIED_FILE.write_text(json.dumps({"last_notified_at": ts}, indent=2))


def load_articles() -> list[dict]:
    try:
        return json.loads(DATA_FILE.read_text()).get("articles", [])
    except Exception as e:
        print(f"[ERROR] articles.json の読み込み失敗: {e}")
        return []


def get_new_articles(articles: list[dict], since: str) -> list[dict]:
    if not since:
        return articles[:MAX_IN_EMAIL]
    return [a for a in articles if a.get("date", "") > since]


def esc(s: str) -> str:
    return (
        (s or "")
        .replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace('"', "&quot;")
    )


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
    return f"""
    <div class="article">
      <span class="article-category {cat_css}">{esc(cat_label)}</span>
      <div class="article-title"><a href="{esc(a['url'])}">{esc(a['title'])}</a></div>
      <div class="article-meta">{esc(a['source'])} &nbsp;·&nbsp; {esc(a.get('date','')[:10])}</div>
      {summary_html}
      <div class="tags">{tags_html}</div>
    </div>"""


def build_html(articles: list[dict]) -> str:
    today = datetime.now(timezone.utc).strftime("%Y年%m月%d日")
    articles_html = "".join(render_article(a) for a in articles)
    count = len(articles)
    more_note = (
        '<p style="color:#64748b;font-size:13px;margin-top:8px;">'
        "他にも新着記事があります。ダッシュボードで全件確認できます。</p>"
        if count >= MAX_IN_EMAIL else ""
    )
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
  .hdr h1{{color:#38bdf8;margin:0;font-size:22px}}
  .hdr p{{color:#94a3b8;margin:6px 0 0;font-size:13px}}
  .bdy{{padding:24px}}
  .intro{{font-size:15px;color:#334155;margin-bottom:20px;line-height:1.6}}
  .article{{border:1px solid #e2e8f0;border-radius:10px;padding:18px;margin-bottom:14px;background:#fafbfc}}
  .article-category{{font-size:10px;font-weight:700;text-transform:uppercase;letter-spacing:.06em;
                      padding:3px 9px;border-radius:4px;display:inline-block;margin-bottom:10px}}
  .cat-product{{background:#fff3e0;color:#c2410c}}
  .cat-tech{{background:#dcfce7;color:#166534}}
  .cat-news{{background:#f3e8ff;color:#7e22ce}}
  .article-title{{font-size:15px;font-weight:600;margin-bottom:6px;line-height:1.4}}
  .article-title a{{color:#0069C2;text-decoration:none}}
  .article-meta{{font-size:12px;color:#64748b;margin-bottom:10px}}
  .article-summary{{font-size:13px;color:#475569;line-height:1.65;margin-bottom:10px}}
  .tags{{display:flex;flex-wrap:wrap;gap:4px}}
  .tag{{font-size:11px;font-weight:600;padding:2px 9px;border-radius:4px}}
  .tag-zia{{background:#e0f2fe;color:#0369a1}}
  .tag-zpa{{background:#ede9fe;color:#5b21b6}}
  .tag-zdx{{background:#d1fae5;color:#065f46}}
  .btn{{display:block;text-align:center;background:#0069C2;color:#fff;text-decoration:none;
        padding:13px 24px;border-radius:9px;font-weight:700;font-size:14px;margin-top:22px}}
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
  <div class="ftr">このメールはZscaler学習ダッシュボードから自動送信されています。</div>
</div>
</body>
</html>"""


def build_text(articles: list[dict]) -> str:
    today = datetime.now(timezone.utc).strftime("%Y-%m-%d")
    lines = [f"Zscaler 学習ダッシュボード — {today}", f"{len(articles)}件の新着記事\n"]
    for i, a in enumerate(articles, 1):
        lines.append(
            f"{i}. [{CATEGORY_LABEL.get(a['category'], a['category'])}] {a['title']}"
        )
        if a.get("summary"):
            lines.append(f"   {a['summary'][:120]}…")
        lines.append(f"   {a['url']}\n")
    lines.append(f"ダッシュボード: {DASHBOARD_URL}")
    return "\n".join(lines)


def send(articles: list[dict]) -> bool:
    if not GMAIL_APP_PASSWORD:
        print("[ERROR] GMAIL_APP_PASSWORD が設定されていません。")
        print("  設定手順:")
        print("  1. Googleアカウント → セキュリティ → 2段階認証を有効化")
        print("  2. 「アプリパスワード」を発行（16文字）")
        print("  3. GitHub の Settings → Secrets → GMAIL_APP_PASSWORD に登録")
        return False

    today = datetime.now(timezone.utc).strftime("%Y年%m月%d日")
    subject = f"[Zscaler] 本日の新着記事 {today} ({len(articles)}件)"

    msg = MIMEMultipart("alternative")
    msg["Subject"] = subject
    msg["From"] = SENDER
    msg["To"] = RECIPIENT
    msg.attach(MIMEText(build_text(articles), "plain", "utf-8"))
    msg.attach(MIMEText(build_html(articles), "html", "utf-8"))

    try:
        context = ssl.create_default_context()
        with smtplib.SMTP("smtp.gmail.com", 587) as server:
            server.ehlo()
            server.starttls(context=context)
            server.login(SENDER, GMAIL_APP_PASSWORD)
            server.sendmail(SENDER, RECIPIENT, msg.as_bytes())
        print("メール送信完了")
        return True
    except smtplib.SMTPAuthenticationError:
        print("[ERROR] 認証失敗。アプリパスワードが正しいか確認してください。")
        print("  ※ 通常のGmailパスワードは使えません。アプリパスワードが必要です。")
        return False
    except Exception as e:
        print(f"[ERROR] 送信失敗: {e}")
        return False


def main():
    print(f"送信先: {RECIPIENT}")
    print(f"送信元: {SENDER}")

    articles = load_articles()
    print(f"記事数: {len(articles)}")
    if not articles:
        print("[SKIP] 記事なし。fetch_articles.py を先に実行してください。")
        return

    last_notified = load_last_notified()
    print(f"前回通知: {last_notified or '(初回)'}")

    new = get_new_articles(articles, last_notified)[:MAX_IN_EMAIL]
    if not new:
        print("[SKIP] 前回通知以降の新着記事なし。")
        return

    print(f"{len(new)}件の新着記事をメール送信します…")
    ok = send(new)

    if ok:
        now = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
        save_last_notified(now)
        print(f"送信完了。last_notified を {now} に更新しました。")
    else:
        print("[FAIL] メール送信に失敗しました。", file=sys.stderr)
        sys.exit(1)


if __name__ == "__main__":
    main()
