#!/usr/bin/env python3
"""
Send a daily email digest of new Zscaler technical articles via Gmail SMTP.
Articles are formatted as bullet-point summaries.

Required environment variables:
  GMAIL_APP_PASSWORD - Gmail app password
  NOTIFY_EMAIL_FROM  - Gmail address to send from
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
URL_LOOKUP_FILE = SCRIPT_DIR.parent / "data" / "url-lookup.json"

RECIPIENT = "ciderred1239@gmail.com"
SENDER = os.environ.get("NOTIFY_EMAIL_FROM", RECIPIENT)
GMAIL_APP_PASSWORD = os.environ.get("GMAIL_APP_PASSWORD", "")
DASHBOARD_URL = os.environ.get(
    "DASHBOARD_URL", "https://boiledtomato.github.io/for_claude/"
)
MAX_IN_EMAIL = 5

CATEGORY_LABEL = {
    "product_update": "製品アップデート",
    "tech_blog": "技術ブログ・脅威情報",
    "industry_news": "業界ニュース",
}
CATEGORY_ICON = {
    "product_update": "🔧",
    "tech_blog": "🔬",
    "industry_news": "📰",
}
TAG_COLOR = {
    "ZIA": "#0369a1",
    "ZPA": "#5b21b6",
    "ZDX": "#065f46",
}
TAG_BG = {
    "ZIA": "#e0f2fe",
    "ZPA": "#ede9fe",
    "ZDX": "#d1fae5",
}


def load_url_lookup() -> dict:
    try:
        return json.loads(URL_LOOKUP_FILE.read_text())
    except Exception:
        return {}


def build_url_lookup_html(data: dict) -> str:
    records = data.get("records", {})
    if not records:
        return ""
    last_updated = data.get("last_updated", "")[:10]

    by_cat: dict[str, list] = {}
    for domain, info in records.items():
        cat = info.get("cloudAppCat", "Other")
        by_cat.setdefault(cat, []).append((domain, info))

    RISK_COLOR = {"low": "#059669", "medium": "#d97706"}
    RISK_LABEL = {"low": "✓ 許可", "medium": "⚠ 注意"}

    rows_html = ""
    for cat in sorted(by_cat):
        entries = sorted(by_cat[cat], key=lambda x: x[0])
        rows_html += f"""
    <tr style="background:#f8fafc;">
      <td colspan="4" style="padding:6px 10px;font-size:11px;font-weight:700;
                              color:#475569;text-transform:uppercase;letter-spacing:.05em;">
        {esc(cat)} ({len(entries)}件)
      </td>
    </tr>"""
        for domain, info in entries:
            risk = info.get("risk", "low")
            color = RISK_COLOR.get(risk, "#64748b")
            label = RISK_LABEL.get(risk, risk)
            app = info.get("cloudApp", "")
            url_cat = info.get("urlCat", "")
            rows_html += f"""
    <tr style="border-bottom:1px solid #f1f5f9;">
      <td style="padding:5px 10px;font-size:12px;color:#1e293b;font-weight:600;">{esc(domain)}</td>
      <td style="padding:5px 10px;font-size:12px;color:#64748b;">{esc(app)}</td>
      <td style="padding:5px 10px;font-size:12px;color:#64748b;">{esc(url_cat)}</td>
      <td style="padding:5px 10px;font-size:12px;color:{color};font-weight:700;">{label}</td>
    </tr>"""

    return f"""
<div style="margin-top:28px;">
  <div style="font-size:14px;font-weight:700;color:#0f172a;margin-bottom:12px;
              padding-bottom:8px;border-bottom:2px solid #e2e8f0;">
    🔍 URL Lookup DB — {len(records)}ドメイン（データソース: VirusTotal / 更新: {esc(last_updated)}）
  </div>
  <div style="overflow-x:auto;">
  <table style="width:100%;border-collapse:collapse;font-size:12px;">
    <thead>
      <tr style="background:#0f2457;color:#fff;">
        <th style="padding:7px 10px;text-align:left;">ドメイン</th>
        <th style="padding:7px 10px;text-align:left;">クラウドアプリ</th>
        <th style="padding:7px 10px;text-align:left;">URLカテゴリ</th>
        <th style="padding:7px 10px;text-align:left;">判定</th>
      </tr>
    </thead>
    <tbody>{rows_html}
    </tbody>
  </table>
  </div>
</div>"""


def build_url_lookup_text(data: dict) -> str:
    records = data.get("records", {})
    if not records:
        return ""
    last_updated = data.get("last_updated", "")[:10]
    lines = [
        "",
        "=" * 50,
        f"URL Lookup DB — {len(records)}ドメイン（VirusTotal / {last_updated}）",
        "=" * 50,
    ]
    by_cat: dict[str, list] = {}
    for domain, info in records.items():
        cat = info.get("cloudAppCat", "Other")
        by_cat.setdefault(cat, []).append((domain, info))
    for cat in sorted(by_cat):
        lines.append(f"\n【{cat}】")
        for domain, info in sorted(by_cat[cat], key=lambda x: x[0]):
            risk = "✓" if info.get("risk") == "low" else "⚠"
            lines.append(f"  {risk} {domain}  ({info.get('cloudApp','')} / {info.get('urlCat','')})")
    return "\n".join(lines)


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
        print(f"[ERROR] articles.json 読み込み失敗: {e}")
        return []


def get_new_articles(articles: list[dict], since: str) -> list[dict]:
    if not since:
        return articles[:MAX_IN_EMAIL]
    return [a for a in articles if a.get("date", "") > since]


def esc(s: str) -> str:
    return (s or "").replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace('"', "&quot;")


def render_article_html(a: dict) -> str:
    cat = a.get("category", "industry_news")
    icon = CATEGORY_ICON.get(cat, "📰")
    label = CATEGORY_LABEL.get(cat, cat)
    date_str = a.get("date", "")[:10]

    tags_html = ""
    for t in (a.get("tags") or []):
        bg = TAG_BG.get(t, "#f1f5f9")
        color = TAG_COLOR.get(t, "#334155")
        tags_html += (
            f'<span style="background:{bg};color:{color};font-size:11px;'
            f'font-weight:700;padding:2px 8px;border-radius:4px;margin-right:4px;">'
            f'{esc(t)}</span>'
        )

    bullets = a.get("bullets") or []
    if not bullets and a.get("summary"):
        bullets = [a["summary"][:200]]

    bullets_html = ""
    if bullets:
        items = "".join(
            f'<li style="margin-bottom:4px;color:#475569;">{esc(b)}</li>'
            for b in bullets
        )
        bullets_html = (
            f'<ul style="margin:8px 0 8px 0;padding-left:18px;'
            f'font-size:13px;line-height:1.6;">{items}</ul>'
        )

    return f"""
<div style="border:1px solid #e2e8f0;border-radius:10px;padding:18px;
            margin-bottom:14px;background:#fafbfc;">
  <div style="margin-bottom:8px;">
    <span style="font-size:10px;font-weight:700;text-transform:uppercase;
                 letter-spacing:.06em;background:#f1f5f9;color:#64748b;
                 padding:3px 8px;border-radius:4px;">
      {icon} {esc(label)}
    </span>
    <span style="font-size:12px;color:#94a3b8;margin-left:8px;">
      {esc(a.get('source',''))} · {esc(date_str)}
    </span>
  </div>
  <div style="font-size:15px;font-weight:700;line-height:1.4;margin-bottom:6px;">
    <a href="{esc(a['url'])}" style="color:#0069C2;text-decoration:none;">
      {esc(a['title'])}
    </a>
  </div>
  {bullets_html}
  <div style="margin-top:8px;">{tags_html}</div>
</div>"""


def build_html(articles: list[dict], url_lookup: dict | None = None) -> str:
    today = datetime.now(timezone.utc).strftime("%Y年%m月%d日")
    body = "".join(render_article_html(a) for a in articles)
    count = len(articles)
    url_section = build_url_lookup_html(url_lookup) if url_lookup else ""
    return f"""<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body style="font-family:-apple-system,BlinkMacSystemFont,'Segoe UI','Noto Sans JP',sans-serif;
             background:#f1f5f9;margin:0;padding:20px;color:#1e293b;">
<div style="max-width:660px;margin:0 auto;background:#fff;border-radius:12px;
            overflow:hidden;box-shadow:0 4px 24px rgba(0,0,0,.08);">
  <div style="background:linear-gradient(135deg,#0c1a36 0%,#0f2457 100%);
              padding:28px 24px;text-align:center;">
    <div style="color:#38bdf8;font-size:22px;font-weight:700;margin:0;">
      Zscaler 技術情報ダイジェスト
    </div>
    <div style="color:#94a3b8;font-size:13px;margin-top:6px;">{today} — {count}件の新着技術記事</div>
  </div>
  <div style="padding:24px;">
    <p style="font-size:14px;color:#64748b;margin-bottom:20px;line-height:1.6;">
      以下は本日の新着Zscaler技術記事です。各記事のポイントを箇条書きでまとめています。
    </p>
    {body}
    <a href="{DASHBOARD_URL}"
       style="display:block;text-align:center;background:#0069C2;color:#fff;
              text-decoration:none;padding:13px 24px;border-radius:9px;
              font-weight:700;font-size:14px;margin-top:8px;">
      ダッシュボードで全件確認する →
    </a>
    {url_section}
  </div>
  <div style="background:#f8fafc;padding:14px 24px;text-align:center;
              font-size:12px;color:#94a3b8;border-top:1px solid #e2e8f0;">
    Zscaler学習ダッシュボード — 毎朝09:00 JST 自動配信
  </div>
</div>
</body>
</html>"""


def build_text(articles: list[dict], url_lookup: dict | None = None) -> str:
    today = datetime.now(timezone.utc).strftime("%Y-%m-%d")
    lines = [
        f"Zscaler 技術情報ダイジェスト — {today}",
        f"新着技術記事 {len(articles)}件",
        "=" * 50,
    ]
    for i, a in enumerate(articles, 1):
        cat = CATEGORY_LABEL.get(a.get("category", ""), "")
        icon = CATEGORY_ICON.get(a.get("category", ""), "")
        tags = " ".join(f"[{t}]" for t in (a.get("tags") or []))
        lines.append(f"\n{i}. {icon} {cat}  {tags}")
        lines.append(f"   {a['title']}")
        lines.append(f"   {a.get('source','')} · {a.get('date','')[:10]}")
        for b in (a.get("bullets") or [])[:3]:
            lines.append(f"   • {b}")
        lines.append(f"   → {a['url']}")
    lines += ["", "=" * 50, f"ダッシュボード: {DASHBOARD_URL}"]
    if url_lookup:
        lines.append(build_url_lookup_text(url_lookup))
    return "\n".join(lines)


def send(articles: list[dict], url_lookup: dict | None = None) -> bool:
    if not GMAIL_APP_PASSWORD:
        print("[ERROR] GMAIL_APP_PASSWORD が設定されていません。")
        print("  GitHub: Settings → Secrets → GMAIL_APP_PASSWORD")
        return False

    today = datetime.now(timezone.utc).strftime("%Y年%m月%d日")
    subject = f"[Zscaler技術情報] {today} — {len(articles)}件の新着記事 + URL Lookup DB"

    msg = MIMEMultipart("alternative")
    msg["Subject"] = subject
    msg["From"] = SENDER
    msg["To"] = RECIPIENT
    msg.attach(MIMEText(build_text(articles, url_lookup), "plain", "utf-8"))
    msg.attach(MIMEText(build_html(articles, url_lookup), "html", "utf-8"))

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
        print("[ERROR] Gmail認証失敗。アプリパスワードを確認してください。")
        return False
    except Exception as e:
        print(f"[ERROR] 送信失敗: {e}")
        return False


def main():
    print(f"送信先: {RECIPIENT} / 送信元: {SENDER}")

    articles = load_articles()
    print(f"記事数: {len(articles)}")
    if not articles:
        print("[SKIP] 記事なし。fetch_articles.py を先に実行してください。")
        return

    last_notified = load_last_notified()
    print(f"前回通知: {last_notified or '(初回)'}")

    new = get_new_articles(articles, last_notified)[:MAX_IN_EMAIL]
    if not new:
        print("[SKIP] 新着記事なし。")
        return

    url_lookup = load_url_lookup()
    print(f"URL Lookup DB: {len(url_lookup.get('records', {}))}件")

    print(f"{len(new)}件の新着記事を送信します…")
    ok = send(new, url_lookup)

    if ok:
        now = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
        save_last_notified(now)
        print(f"完了。last_notified → {now}")
    else:
        print("[FAIL] 送信失敗。", file=sys.stderr)
        sys.exit(1)


if __name__ == "__main__":
    main()
