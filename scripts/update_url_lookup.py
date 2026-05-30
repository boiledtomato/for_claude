#!/usr/bin/env python3
"""
URL Lookup データ自動更新スクリプト
毎日 JST 0:00 (UTC 15:00) に GitHub Actions から実行される。

VirusTotal 無料 API を使用してドメインの URL カテゴリを取得し、
Zscaler カテゴリ体系にマッピングして data/url-lookup.json に保存する。

必須環境変数:
  VIRUSTOTAL_API_KEY — VirusTotal 無料 API キー
                       https://www.virustotal.com/gui/join-us で取得
"""

import os
import sys
import json
import time
import pathlib
import logging
import requests
from datetime import datetime, timezone

logging.basicConfig(level=logging.INFO, format="%(levelname)s %(message)s")
log = logging.getLogger(__name__)

VT_API_KEY     = os.environ.get("VIRUSTOTAL_API_KEY", "")
VT_BASE        = "https://www.virustotal.com/api/v3"
BATCH_SIZE     = 4   # VT 無料枠: 4 req/min → 4 件まとめて送信
BATCH_SLEEP    = 65  # バッチ間の待機秒数（60 秒 + 余裕）
NUM_GROUPS     = 5   # ドメインを 5 グループに分割 → 1 日あたり約 30 件

ROOT   = pathlib.Path(__file__).parent.parent
OUTPUT = ROOT / "data" / "url-lookup.json"

# ── 監視対象ドメインリスト ─────────────────────────────────────────────────────
# (domain, cloud_app_name, cloud_app_category)
DOMAINS = [
    # Web Search
    ("google.com",           "Google Search",         "Web Search"),
    ("google.co.jp",         "Google Search",         "Web Search"),
    ("bing.com",             "Microsoft Bing",        "Web Search"),
    ("yahoo.com",            "Yahoo Search",          "Web Search"),
    ("yahoo.co.jp",          "Yahoo! JAPAN",          "Web Search"),
    ("duckduckgo.com",       "DuckDuckGo",            "Web Search"),
    ("baidu.com",            "Baidu",                 "Web Search"),
    ("perplexity.ai",        "Perplexity AI",         "AI/ML Services"),
    # Google Workspace
    ("gmail.com",            "Gmail",                 "Email"),
    ("drive.google.com",     "Google Drive",          "File Storage/Sharing"),
    ("docs.google.com",      "Google Docs",           "Office Productivity"),
    ("sheets.google.com",    "Google Sheets",         "Office Productivity"),
    ("slides.google.com",    "Google Slides",         "Office Productivity"),
    ("meet.google.com",      "Google Meet",           "Video Conferencing"),
    ("calendar.google.com",  "Google Calendar",       "Office Productivity"),
    # Microsoft 365
    ("office.com",           "Microsoft 365",         "Office Productivity"),
    ("microsoft.com",        "Microsoft",             "Technology"),
    ("outlook.com",          "Microsoft Outlook",     "Email"),
    ("sharepoint.com",       "Microsoft SharePoint",  "File Storage/Sharing"),
    ("onedrive.live.com",    "Microsoft OneDrive",    "File Storage/Sharing"),
    ("teams.microsoft.com",  "Microsoft Teams",       "Collaboration"),
    ("skype.com",            "Skype",                 "Communication"),
    ("azure.microsoft.com",  "Microsoft Azure",       "Cloud Infrastructure"),
    # Collaboration & Messaging
    ("slack.com",            "Slack",                 "Collaboration"),
    ("zoom.us",              "Zoom",                  "Video Conferencing"),
    ("webex.com",            "Cisco Webex",           "Video Conferencing"),
    ("gotomeeting.com",      "GoTo Meeting",          "Video Conferencing"),
    ("discord.com",          "Discord",               "Collaboration"),
    ("whatsapp.com",         "WhatsApp",              "Communication"),
    ("telegram.org",         "Telegram",              "Communication"),
    ("signal.org",           "Signal",                "Communication"),
    ("line.me",              "LINE",                  "Communication"),
    ("miro.com",             "Miro",                  "Collaboration"),
    # Social Networking
    ("facebook.com",         "Facebook",              "Social Media"),
    ("instagram.com",        "Instagram",             "Social Media"),
    ("twitter.com",          "Twitter/X",             "Social Media"),
    ("x.com",                "Twitter/X",             "Social Media"),
    ("linkedin.com",         "LinkedIn",              "Social Media"),
    ("tiktok.com",           "TikTok",                "Social Media"),
    ("snapchat.com",         "Snapchat",              "Social Media"),
    ("pinterest.com",        "Pinterest",             "Social Media"),
    ("reddit.com",           "Reddit",                "Social Media"),
    ("nicovideo.jp",         "Niconico",              "Video Streaming"),
    ("note.com",             "note",                  "Social Media"),
    ("ameblo.jp",            "Ameba Blog",            "Social Media"),
    # Streaming Media
    ("youtube.com",          "YouTube",               "Video Streaming"),
    ("netflix.com",          "Netflix",               "Video Streaming"),
    ("hulu.com",             "Hulu",                  "Video Streaming"),
    ("disneyplus.com",       "Disney+",               "Video Streaming"),
    ("primevideo.com",       "Amazon Prime Video",    "Video Streaming"),
    ("twitch.tv",            "Twitch",                "Video Streaming"),
    ("vimeo.com",            "Vimeo",                 "Video Streaming"),
    ("spotify.com",          "Spotify",               "Video Streaming"),
    ("dazn.com",             "DAZN",                  "Video Streaming"),
    ("abema.tv",             "ABEMA",                 "Video Streaming"),
    # File Storage
    ("dropbox.com",          "Dropbox",               "File Storage/Sharing"),
    ("box.com",              "Box",                   "File Storage/Sharing"),
    ("icloud.com",           "iCloud",                "File Storage/Sharing"),
    ("wetransfer.com",       "WeTransfer",            "File Storage/Sharing"),
    ("mega.nz",              "MEGA",                  "File Storage/Sharing"),
    # E-commerce
    ("amazon.com",           "Amazon",                "E-commerce"),
    ("amazon.co.jp",         "Amazon Japan",          "E-commerce"),
    ("ebay.com",             "eBay",                  "E-commerce"),
    ("shopify.com",          "Shopify",               "E-commerce"),
    ("rakuten.co.jp",        "楽天市場",              "E-commerce"),
    ("mercari.com",          "Mercari",               "E-commerce"),
    ("alibaba.com",          "Alibaba",               "E-commerce"),
    ("aliexpress.com",       "AliExpress",            "E-commerce"),
    # News & Media
    ("cnn.com",              "CNN",                   "News"),
    ("bbc.com",              "BBC",                   "News"),
    ("reuters.com",          "Reuters",               "News"),
    ("nytimes.com",          "New York Times",        "News"),
    ("bloomberg.com",        "Bloomberg",             "News"),
    ("techcrunch.com",       "TechCrunch",            "News"),
    ("wired.com",            "WIRED",                 "News"),
    ("nikkei.com",           "日本経済新聞",          "News"),
    ("asahi.com",            "朝日新聞",              "News"),
    ("yomiuri.co.jp",        "読売新聞",              "News"),
    ("mainichi.jp",          "毎日新聞",              "News"),
    ("nhk.or.jp",            "NHK",                   "News"),
    ("nhk.jp",               "NHK",                   "News"),
    ("itmedia.co.jp",        "ITmedia",               "News"),
    ("gigazine.net",         "GIGAZINE",              "News"),
    # Finance
    ("paypal.com",           "PayPal",                "Finance"),
    ("stripe.com",           "Stripe",                "Finance"),
    ("coinbase.com",         "Coinbase",              "Finance"),
    ("binance.com",          "Binance",               "Finance"),
    ("moneyforward.com",     "Money Forward",         "Finance"),
    ("freee.co.jp",          "freee",                 "Finance"),
    # Developer Tools & Cloud
    ("github.com",           "GitHub",                "Developer Tools"),
    ("gitlab.com",           "GitLab",                "Developer Tools"),
    ("bitbucket.org",        "Bitbucket",             "Developer Tools"),
    ("stackoverflow.com",    "Stack Overflow",        "Developer Tools"),
    ("amazonaws.com",        "AWS",                   "Cloud Infrastructure"),
    ("cloud.google.com",     "Google Cloud",          "Cloud Infrastructure"),
    ("cloudflare.com",       "Cloudflare",            "Cloud Infrastructure"),
    ("vercel.com",           "Vercel",                "Developer Tools"),
    ("netlify.com",          "Netlify",               "Developer Tools"),
    ("digitalocean.com",     "DigitalOcean",          "Cloud Infrastructure"),
    ("docker.com",           "Docker Hub",            "Developer Tools"),
    ("figma.com",            "Figma",                 "Developer Tools"),
    ("canva.com",            "Canva",                 "Office Productivity"),
    # CRM / Enterprise
    ("salesforce.com",       "Salesforce",            "CRM"),
    ("hubspot.com",          "HubSpot",               "CRM"),
    ("zendesk.com",          "Zendesk",               "ITSM/Project Management"),
    ("servicenow.com",       "ServiceNow",            "ITSM/Project Management"),
    ("workday.com",          "Workday",               "HR/HCM"),
    ("sap.com",              "SAP",                   "ERP/Finance"),
    ("oracle.com",           "Oracle",                "ERP/Finance"),
    # Project Management
    ("atlassian.com",        "Atlassian",             "ITSM/Project Management"),
    ("jira.com",             "Jira",                  "ITSM/Project Management"),
    ("trello.com",           "Trello",                "ITSM/Project Management"),
    ("asana.com",            "Asana",                 "ITSM/Project Management"),
    ("monday.com",           "Monday.com",            "ITSM/Project Management"),
    ("notion.so",            "Notion",                "Office Productivity"),
    # AI / LLM
    ("openai.com",           "OpenAI",                "AI/ML Services"),
    ("chatgpt.com",          "ChatGPT",               "AI/ML Services"),
    ("claude.ai",            "Claude (Anthropic)",    "AI/ML Services"),
    ("anthropic.com",        "Anthropic",             "AI/ML Services"),
    ("huggingface.co",       "Hugging Face",          "AI/ML Services"),
    # Security
    ("zscaler.com",          "Zscaler",               "Security"),
    ("zscalerone.net",       "Zscaler",               "Security"),
    ("zscloud.net",          "Zscaler",               "Security"),
    ("crowdstrike.com",      "CrowdStrike",           "Security"),
    ("paloaltonetworks.com", "Palo Alto Networks",    "Security"),
    ("okta.com",             "Okta",                  "Security"),
    # Gaming
    ("steampowered.com",     "Steam",                 "Gaming"),
    ("epicgames.com",        "Epic Games",            "Gaming"),
    ("roblox.com",           "Roblox",                "Gaming"),
    ("playstation.com",      "PlayStation Network",   "Gaming"),
    ("xbox.com",             "Xbox Network",          "Gaming"),
    ("nintendo.com",         "Nintendo",              "Gaming"),
    # Education
    ("coursera.org",         "Coursera",              "Training"),
    ("udemy.com",            "Udemy",                 "Training"),
    ("edx.org",              "edX",                   "Training"),
    ("khanacademy.org",      "Khan Academy",          "Training"),
    ("wikipedia.org",        "Wikipedia",             "Reference"),
    ("pluralsight.com",      "Pluralsight",           "Training"),
    # Travel
    ("booking.com",          "Booking.com",           "Travel"),
    ("expedia.com",          "Expedia",               "Travel"),
    ("airbnb.com",           "Airbnb",                "Travel"),
    ("tripadvisor.com",      "TripAdvisor",           "Travel"),
    ("jal.co.jp",            "JAL",                   "Travel"),
    ("ana.co.jp",            "ANA",                   "Travel"),
    # Misc
    ("apple.com",            "Apple",                 "Technology"),
    ("medium.com",           "Medium",                "Social Media"),
    ("substack.com",         "Substack",              "Social Media"),
    ("wordpress.com",        "WordPress.com",         "Web Hosting"),
    ("pixiv.net",            "pixiv",                 "Entertainment"),
    ("pastebin.com",         "Pastebin",              "File Storage/Sharing"),
]

# ── ベンダーカテゴリ → Zscaler カテゴリ マッピング ─────────────────────────────
# VirusTotal が返す各ベンダー (Zscaler, Forcepoint, Webroot 等) のカテゴリを
# Zscaler の公式カテゴリ体系に正規化する
CATEGORY_MAP: dict[str, str] = {
    "social networking":         "Social Networking",
    "social network":            "Social Networking",
    "social media":              "Social Networking",
    "social network services":   "Social Networking",
    "search engines":            "Web Search",
    "search engine":             "Web Search",
    "internet portals":          "Web Search",
    "internet search engines":   "Web Search",
    "streaming media":           "Streaming Media",
    "video streaming":           "Streaming Media",
    "audio and video streaming": "Streaming Media",
    "music":                     "Streaming Media",
    "radio":                     "Streaming Media",
    "online shopping":           "Online Shopping",
    "shopping":                  "Online Shopping",
    "e-commerce":                "Online Shopping",
    "auctions":                  "Online Shopping",
    "business":                  "Business and Economy",
    "business applications":     "Business and Economy",
    "business/economy":          "Business and Economy",
    "information technology":    "Computer and Internet Info",
    "computer and internet info":"Computer and Internet Info",
    "computer and internet security": "Computer and Internet Info",
    "internet services":         "Computer and Internet Info",
    "technology":                "Computer and Internet Info",
    "software":                  "Software Downloads",
    "news":                      "News and Media",
    "news and media":            "News and Media",
    "online news":               "News and Media",
    "portal sites":              "News and Media",
    "financial services":        "Finance",
    "finance":                   "Finance",
    "banking":                   "Finance",
    "online banking":            "Finance",
    "investment and finance":    "Finance",
    "cryptocurrency":            "Finance",
    "games":                     "Games",
    "online games":              "Games",
    "gaming":                    "Games",
    "video games":               "Games",
    "education":                 "Education",
    "educational institutions":  "Education",
    "reference":                 "Education",
    "health":                    "Health",
    "health and medicine":       "Health",
    "healthcare":                "Health",
    "travel":                    "Travel",
    "travel and tourism":        "Travel",
    "hotels and travel":         "Travel",
    "file storage":              "Online Storage and Backup",
    "online storage and backup": "Online Storage and Backup",
    "cloud storage":             "Online Storage and Backup",
    "email":                     "Web-based Email",
    "webmail":                   "Web-based Email",
    "web based email":           "Web-based Email",
    "instant messaging":         "Instant Messaging",
    "web conferencing":          "Web Conferencing",
    "conferencing":              "Web Conferencing",
    "malware":                   "Malicious Sites",
    "malicious":                 "Malicious Sites",
    "phishing":                  "Phishing",
    "proxy":                     "Anonymous Proxy/Anonymizer",
    "anonymizer":                "Anonymous Proxy/Anonymizer",
    "anonymous proxy":           "Anonymous Proxy/Anonymizer",
    "adult content":             "Adult Material",
    "pornography":               "Adult Material",
    "adult":                     "Adult Material",
    "gambling":                  "Gambling",
    "cdn":                       "CDN",
    "content delivery network":  "CDN",
    "blogs":                     "Personal Sites and Blogs",
    "personal sites and blogs":  "Personal Sites and Blogs",
    "personal web sites":        "Personal Sites and Blogs",
    "web advertisements":        "Web Ads/Analytics",
    "advertising":               "Web Ads/Analytics",
    "analytics":                 "Web Ads/Analytics",
    "government":                "Government",
    "sports":                    "Sports",
    "entertainment":             "Entertainment",
    "arts":                      "Arts",
    "web hosting":               "Web Hosting",
    "recreation":                "Recreation and Hobbies",
}

# カテゴリ別デフォルトリスク
RISK_BY_CATEGORY: dict[str, str] = {
    "Malicious Sites":             "high",
    "Phishing":                    "high",
    "Adult Material":              "high",
    "Gambling":                    "high",
    "Anonymous Proxy/Anonymizer":  "high",
    "Social Networking":           "medium",
    "Streaming Media":             "medium",
    "Games":                       "medium",
}


def vt_query(domain: str) -> dict:
    """VirusTotal API からドメイン情報を取得する。"""
    headers = {"x-apikey": VT_API_KEY}
    url = f"{VT_BASE}/domains/{domain}"
    try:
        r = requests.get(url, headers=headers, timeout=20)
        if r.status_code == 200:
            return r.json()
        if r.status_code == 404:
            log.warning(f"[NOT FOUND] {domain}")
        elif r.status_code == 429:
            log.warning("[RATE LIMIT] 60 秒待機...")
            time.sleep(60)
            r2 = requests.get(url, headers=headers, timeout=20)
            if r2.status_code == 200:
                return r2.json()
        else:
            log.warning(f"[HTTP {r.status_code}] {domain}: {r.text[:100]}")
    except requests.RequestException as e:
        log.error(f"[ERROR] {domain}: {e}")
    return {}


def extract_url_category(vt_data: dict) -> tuple[str, str]:
    """
    VirusTotal のベンダーカテゴリから Zscaler カテゴリを導出する。
    優先順: Zscaler > Forcepoint ThreatSeeker > Webroot > Sophos > 他
    """
    attrs = vt_data.get("data", {}).get("attributes", {})
    cats  = attrs.get("categories", {})

    priority_vendors = [
        "Zscaler",
        "Forcepoint ThreatSeeker",
        "Webroot",
        "Sophos",
        "BitDefender",
        "CMEMC",
        "Dr.Web",
        "Symantec Website Security",
    ]

    raw = ""
    for vendor in priority_vendors:
        if vendor in cats:
            raw = cats[vendor].strip()
            log.info(f"  [{vendor}] → {raw!r}")
            break

    if not raw and cats:
        vendor, raw = next(iter(cats.items()))
        log.info(f"  [{vendor}] → {raw!r}")

    raw_lower = raw.lower()
    zcat = CATEGORY_MAP.get(raw_lower, "")
    if not zcat:
        for k, v in CATEGORY_MAP.items():
            if k in raw_lower:
                zcat = v
                break

    return zcat or "Miscellaneous", raw


def assess_risk(vt_data: dict, url_cat: str) -> tuple[str, str]:
    """VT の検出結果とカテゴリからリスクと推奨アクションを算出する。"""
    attrs     = vt_data.get("data", {}).get("attributes", {})
    stats     = attrs.get("last_analysis_stats", {})
    malicious = stats.get("malicious", 0)
    suspicious = stats.get("suspicious", 0)

    if malicious >= 3:
        return "high", "block"
    if malicious >= 1 or suspicious >= 2:
        return "medium", "caution"

    # VT でクリーンでもカテゴリによるリスク
    risk = RISK_BY_CATEGORY.get(url_cat, "low")
    action_map = {"high": "caution", "medium": "caution", "low": "allow"}
    return risk, action_map.get(risk, "allow")


def already_updated_today(entry: dict) -> bool:
    today = datetime.now(timezone.utc).date().isoformat()
    return entry.get("updated", "").startswith(today)


def main() -> None:
    force_all = "--all" in sys.argv

    if not VT_API_KEY:
        log.error("環境変数 VIRUSTOTAL_API_KEY が未設定です。処理を中止します。")
        log.error("取得先: https://www.virustotal.com/gui/join-us")
        raise SystemExit(1)

    # 既存データ読み込み
    existing: dict = {}
    if OUTPUT.exists():
        try:
            existing = json.loads(OUTPUT.read_text(encoding="utf-8")).get("records", {})
            log.info(f"既存レコード {len(existing)} 件をロード")
        except Exception as e:
            log.warning(f"既存データ読み込み失敗: {e}")

    records = dict(existing)

    # 5日ローテーション: 本日の曜日グループのみ処理（約30件/日）
    if force_all:
        targets = list(DOMAINS)
        log.info(f"--all フラグ: 全 {len(targets)} ドメインを処理")
    else:
        day_group = datetime.now(timezone.utc).timetuple().tm_yday % NUM_GROUPS
        targets = [d for i, d in enumerate(DOMAINS) if i % NUM_GROUPS == day_group]
        log.info(f"本日のグループ {day_group}: {len(targets)} ドメインを処理")

    queried = 0
    skipped = 0
    batch_count = 0

    for domain, cloud_app, cloud_app_cat in targets:
        # 同日内に更新済みならスキップ
        if domain in records and already_updated_today(records[domain]):
            log.info(f"[SKIP] {domain} (本日更新済み)")
            skipped += 1
            continue

        # BATCH_SIZE 件ごとにスリープ（初回バッチは待機不要）
        if batch_count > 0 and batch_count % BATCH_SIZE == 0:
            log.info(f"  バッチ完了 ({batch_count} 件) — {BATCH_SLEEP} 秒待機...")
            time.sleep(BATCH_SLEEP)

        log.info(f"[LOOKUP] {domain}")
        vt = vt_query(domain)

        if vt:
            url_cat, raw_cat = extract_url_category(vt)
            risk, action     = assess_risk(vt, url_cat)
            source           = "virustotal"
        else:
            # VT 取得失敗時は既存データを維持
            prev = records.get(domain, {})
            url_cat  = prev.get("urlCat",  "Miscellaneous")
            raw_cat  = prev.get("rawCategory", "")
            risk     = prev.get("risk",    "unknown")
            action   = prev.get("action",  "caution")
            source   = "fallback"

        records[domain] = {
            "urlCat":      url_cat,
            "cloudApp":    cloud_app,
            "cloudAppCat": cloud_app_cat,
            "risk":        risk,
            "action":      action,
            "source":      source,
            "rawCategory": raw_cat,
            "updated":     datetime.now(timezone.utc).isoformat(),
        }

        queried += 1
        batch_count += 1

    OUTPUT.parent.mkdir(parents=True, exist_ok=True)
    output = {
        "last_updated": datetime.now(timezone.utc).isoformat(),
        "total":        len(records),
        "records":      records,
    }
    OUTPUT.write_text(json.dumps(output, ensure_ascii=False, indent=2), encoding="utf-8")
    log.info(f"完了: 更新 {queried} 件 / スキップ {skipped} 件 → {OUTPUT}")


if __name__ == "__main__":
    main()
