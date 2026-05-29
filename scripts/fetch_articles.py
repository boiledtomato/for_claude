#!/usr/bin/env python3
"""
Fetch Zscaler technical articles from RSS feeds and save to data/articles.json.
Filters out financial/stock news and focuses on technical content only.
"""

import hashlib
import json
import re
import xml.etree.ElementTree as ET
from datetime import datetime, timezone
from email.utils import parsedate_to_datetime
from pathlib import Path

import requests

SCRIPT_DIR = Path(__file__).parent
DATA_FILE = SCRIPT_DIR.parent / "data" / "articles.json"

MAX_ARTICLES = 200
MAX_SUMMARY_LEN = 400

# Google News RSS — Zscaler 技術情報に絞ったクエリ
GOOGLE_NEWS_FEEDS = [
    {
        "url": "https://news.google.com/rss/search?q=Zscaler+ZIA+OR+ZPA+OR+ZDX+OR+ThreatLabz&hl=en-US&gl=US&ceid=US:en",
        "label": "Google News (ZIA/ZPA/ZDX/ThreatLabz)",
    },
    {
        "url": "https://news.google.com/rss/search?q=Zscaler+%22zero+trust%22+OR+%22SASE%22+OR+%22ZTNA%22&hl=en-US&gl=US&ceid=US:en",
        "label": "Google News (Zero Trust/SASE/ZTNA)",
    },
    {
        "url": "https://news.google.com/rss/search?q=Zscaler+security+OR+threat+OR+vulnerability&hl=en-US&gl=US&ceid=US:en",
        "label": "Google News (Security Research)",
    },
    {
        "url": "https://news.google.com/rss/search?q=Zscaler+%E3%82%BB%E3%82%AD%E3%83%A5%E3%83%AA%E3%83%86%E3%82%A3+OR+%E3%82%BC%E3%83%AD%E3%83%88%E3%83%A9%E3%82%B9%E3%83%88&hl=ja&gl=JP&ceid=JP:ja",
        "label": "Google News (日本語・技術)",
    },
]

SUPPLEMENTAL_FEEDS = [
    {
        "url": "https://helpnetsecurity.com/feed/",
        "source": "Help Net Security",
        "category": "industry_news",
    },
    {
        "url": "https://www.securityweek.com/feed/",
        "source": "SecurityWeek",
        "category": "industry_news",
    },
    {
        "url": "https://www.bleepingcomputer.com/feed/",
        "source": "Bleeping Computer",
        "category": "industry_news",
    },
    {
        "url": "https://feeds.feedburner.com/TheHackersNews",
        "source": "The Hacker News",
        "category": "industry_news",
    },
]

# 技術キーワード（これを含む記事のみ採用）
TECH_KEYWORDS = [
    "zscaler", "ztna", "threatlabz", "zscalerone",
    "zero trust network access", "secure access service edge",
    "zscaler private access", "zscaler internet access",
    "zscaler digital experience", "ssl inspection", "cloud proxy",
]

# 除外キーワード（株・財務・IR関連は不要）
EXCLUDE_KEYWORDS = [
    "stock", "shares", "stock price", "earnings", "revenue",
    "analyst", "downgrade", "upgrade", "price target", "market cap",
    "investor", "nasdaq", "quarterly", "fiscal year", "wall street",
    "hedge fund", "buy rating", "sell rating", "hold rating",
    "zs stock", "short seller", "ipo", "valuation", "market share",
    "financial results", "guidance", "forecast", "deceleration",
    "growth reckoning", "strong quarter", "beats expectations",
    "misses expectations", "profit", "loss", "subscription revenue",
    "annual recurring", "billings", "operating margin", "diluted eps",
    "year-over-year", "q1 ", "q2 ", "q3 ", "q4 ", "fy2", "fy 2",
    "株価", "決算", "収益", "アナリスト", "投資家", "時価総額",
    "四半期", "業績", "売上", "利益", "増収", "減収",
]

PRODUCT_TAGS = {
    "ZIA": ["zscaler internet access", " zia ", "web gateway", "swg",
            "url filtering", "cloud firewall", "sandbox zscaler",
            "dlp zscaler", "casb zscaler", "ssl inspection zscaler"],
    "ZPA": ["zscaler private access", " zpa ", "ztna",
            "zero trust network access", "app connector",
            "application connector"],
    "ZDX": [" zdx ", "zscaler digital experience",
            "digital experience monitoring"],
}


def _article_id(url: str) -> str:
    return hashlib.sha256(url.encode()).hexdigest()[:16]


def _clean_html(text: str) -> str:
    text = re.sub(r"<[^>]+>", "", text or "")
    for entity, repl in [("&amp;", "&"), ("&lt;", "<"), ("&gt;", ">"),
                          ("&nbsp;", " "), ("&#39;", "'"), ("&quot;", '"')]:
        text = text.replace(entity, repl)
    text = re.sub(r"\s+", " ", text).strip()
    return text[:MAX_SUMMARY_LEN]


def _to_bullets(title: str, summary: str) -> list[str]:
    """サマリーテキストを箇条書きリストに変換する。
    サマリーがタイトルの繰り返しに過ぎない場合は空を返す。"""
    if not summary:
        return []
    # Google Newsのsummaryはタイトルと同一のことが多い → その場合はスキップ
    if summary.lower().startswith(title.lower()[:40]):
        return []
    sentences = re.split(r"[。\.;]\s*", summary)
    bullets = []
    for s in sentences:
        s = s.strip()
        if len(s) > 25:
            bullets.append(s)
        if len(bullets) >= 3:
            break
    return bullets


def _parse_date(date_str: str) -> str:
    if not date_str:
        return datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    try:
        dt = parsedate_to_datetime(date_str.strip())
        return dt.astimezone(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    except Exception:
        pass
    for fmt in ("%Y-%m-%dT%H:%M:%S%z", "%Y-%m-%dT%H:%M:%SZ"):
        try:
            dt = datetime.strptime(date_str.strip(), fmt)
            return dt.astimezone(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
        except ValueError:
            pass
    return datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")


def _detect_tags(text: str) -> list[str]:
    lower = " " + text.lower() + " "
    return [tag for tag, kws in PRODUCT_TAGS.items()
            if any(kw in lower for kw in kws)]


def _is_technical(title: str, summary: str) -> bool:
    """技術関連記事かどうか判定する。"""
    combined = (title + " " + summary).lower()
    return any(kw in combined for kw in TECH_KEYWORDS)


def _is_financial(title: str, summary: str) -> bool:
    """株・財務記事を除外する。"""
    combined = (title + " " + summary).lower()
    return any(kw in combined for kw in EXCLUDE_KEYWORDS)


def _categorize(title: str, summary: str) -> str:
    combined = (title + " " + summary).lower()
    if any(kw in combined for kw in [
        "release", "update", "new feature", "launch", "announce",
        "product", "platform", "feature", "capability",
    ]):
        return "product_update"
    if any(kw in combined for kw in [
        "threat", "malware", "phishing", "ransomware", "research",
        "vulnerability", "exploit", "cve", "attack", "threatlabz",
        "botnet", "backdoor", "spyware",
    ]):
        return "tech_blog"
    return "industry_news"


def _strip_source_suffix(title: str) -> str:
    return re.sub(r"\s+-\s+[^-]{2,60}$", "", title).strip()


def fetch_xml(url: str, timeout: int = 20) -> bytes | None:
    try:
        headers = {"User-Agent": "Mozilla/5.0 (compatible; ZscalerDashboard/1.0)"}
        resp = requests.get(url, headers=headers, timeout=timeout)
        resp.raise_for_status()
        return resp.content
    except Exception as e:
        print(f"  [SKIP] {url[:70]}: {e}")
        return None


def parse_google_news(xml_bytes: bytes) -> list[dict]:
    try:
        root = ET.fromstring(xml_bytes)
    except ET.ParseError as e:
        print(f"  [XML error] {e}")
        return []
    items = []
    for item in root.findall(".//item"):
        t = item.find("title")
        l = item.find("link")
        p = item.find("pubDate")
        s = item.find("source")
        d = item.find("description")
        title = _strip_source_suffix((t.text or "").strip() if t is not None else "")
        link = (l.text or "").strip() if l is not None else ""
        if title and link:
            items.append({
                "title": title,
                "link": link,
                "date": (p.text or "").strip() if p is not None else "",
                "source": (s.text or "").strip() if s is not None else "",
                "summary": (d.text or "").strip() if d is not None else "",
            })
    return items


def parse_rss(xml_bytes: bytes) -> list[dict]:
    try:
        root = ET.fromstring(xml_bytes)
    except ET.ParseError:
        return []
    items = []
    for item in root.findall(".//item"):
        t = item.find("title")
        l = item.find("link")
        d = item.find("description")
        p = item.find("pubDate")
        title = (t.text or "").strip() if t is not None else ""
        link = (l.text or "").strip() if l is not None else ""
        if title and link:
            items.append({
                "title": title,
                "link": link,
                "date": (p.text or "").strip() if p is not None else "",
                "summary": (d.text or "").strip() if d is not None else "",
                "source": "",
            })
    return items


def build_article(entry: dict, source_override: str = "") -> dict | None:
    summary = _clean_html(entry["summary"])
    title = entry["title"]

    if not _is_technical(title, summary):
        return None
    if _is_financial(title, summary):
        return None

    combined = title + " " + summary
    return {
        "id": _article_id(entry["link"]),
        "title": title,
        "url": entry["link"],
        "summary": summary,
        "bullets": _to_bullets(title, summary),
        "date": _parse_date(entry["date"]),
        "source": source_override or entry.get("source") or "Google News",
        "category": _categorize(title, summary),
        "tags": _detect_tags(combined),
    }


def fetch_google_news_feeds() -> list[dict]:
    articles = []
    seen_titles: set[str] = set()
    for cfg in GOOGLE_NEWS_FEEDS:
        print(f"Fetching {cfg['label']} …")
        xml_bytes = fetch_xml(cfg["url"])
        if not xml_bytes:
            continue
        count = 0
        for entry in parse_google_news(xml_bytes):
            norm = entry["title"].lower()
            if norm in seen_titles:
                continue
            seen_titles.add(norm)
            a = build_article(entry)
            if a and a["id"] not in {x["id"] for x in articles}:
                articles.append(a)
                count += 1
        print(f"  → {count} 件")
    return articles


def fetch_supplemental_feeds() -> list[dict]:
    articles = []
    existing_ids: set[str] = set()
    for cfg in SUPPLEMENTAL_FEEDS:
        print(f"Fetching {cfg['source']} …")
        xml_bytes = fetch_xml(cfg["url"])
        if not xml_bytes:
            continue
        count = 0
        for entry in parse_rss(xml_bytes):
            a = build_article(entry, source_override=cfg["source"])
            if a and a["id"] not in existing_ids:
                a["category"] = cfg["category"]
                articles.append(a)
                existing_ids.add(a["id"])
                count += 1
        print(f"  → {count} 件")
    return articles


def main():
    all_articles: list[dict] = []
    seen_ids: set[str] = set()

    for a in fetch_google_news_feeds():
        if a["id"] not in seen_ids:
            all_articles.append(a)
            seen_ids.add(a["id"])

    for a in fetch_supplemental_feeds():
        if a["id"] not in seen_ids:
            all_articles.append(a)
            seen_ids.add(a["id"])

    all_articles.sort(key=lambda a: a["date"], reverse=True)
    all_articles = all_articles[:MAX_ARTICLES]

    result = {
        "last_updated": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
        "total": len(all_articles),
        "articles": all_articles,
    }

    DATA_FILE.parent.mkdir(parents=True, exist_ok=True)
    DATA_FILE.write_text(json.dumps(result, ensure_ascii=False, indent=2))
    print(f"\n合計 {len(all_articles)} 件の技術記事を保存 → {DATA_FILE}")


if __name__ == "__main__":
    main()
