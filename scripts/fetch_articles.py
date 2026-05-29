#!/usr/bin/env python3
"""
Fetch Zscaler-related articles from RSS feeds and save to data/articles.json.
Uses only standard library + requests (no feedparser dependency).
"""

import hashlib
import json
import re
import xml.etree.ElementTree as ET
from datetime import datetime, timezone
from email.utils import parsedate_to_datetime
from pathlib import Path
from urllib.parse import unquote

import requests

SCRIPT_DIR = Path(__file__).parent
DATA_FILE = SCRIPT_DIR.parent / "data" / "articles.json"

MAX_ARTICLES = 200
MAX_SUMMARY_LEN = 400

# Google News RSS (most reliable source for Zscaler news)
GOOGLE_NEWS_FEEDS = [
    {
        "url": "https://news.google.com/rss/search?q=Zscaler&hl=en-US&gl=US&ceid=US:en",
        "label": "Google News (Zscaler general)",
    },
    {
        "url": "https://news.google.com/rss/search?q=Zscaler+ZIA+OR+ZPA+OR+ZDX&hl=en-US&gl=US&ceid=US:en",
        "label": "Google News (ZIA/ZPA/ZDX)",
    },
    {
        "url": "https://news.google.com/rss/search?q=Zscaler&hl=ja&gl=JP&ceid=JP:ja",
        "label": "Google News (日本語)",
    },
    {
        "url": "https://news.google.com/rss/search?q=Zscaler+ThreatLabz+OR+%22threat+research%22&hl=en-US&gl=US&ceid=US:en",
        "label": "Google News (ThreatLabz)",
    },
]

# Additional security RSS feeds (filtered for Zscaler keywords)
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

ZSCALER_KEYWORDS = [
    "zscaler", "ztna", "zscalerone",
    "zero trust network access", "secure access service edge",
    "cloud proxy", "ssl inspection", "zscaler private access",
    "zscaler internet access", "threatlabz",
]

# Stricter product-specific keywords
PRODUCT_TAGS = {
    "ZIA": ["zscaler internet access", " zia ", "web gateway", "swg",
            "url filtering", "cloud firewall", "sandbox zscaler", "dlp zscaler",
            "casb zscaler", "ssl inspection zscaler"],
    "ZPA": ["zscaler private access", " zpa ", "ztna", "zero trust network access",
            "app connector", "application connector"],
    "ZDX": [" zdx ", "zscaler digital experience", "digital experience monitoring"],
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
    return [tag for tag, kws in PRODUCT_TAGS.items() if any(kw in lower for kw in kws)]


def _is_zscaler_related(title: str, summary: str) -> bool:
    combined = (title + " " + summary).lower()
    return any(kw in combined for kw in ZSCALER_KEYWORDS)


def _strip_source_suffix(title: str) -> str:
    """Remove ' - Source Name' suffix Google News appends to titles."""
    return re.sub(r"\s+-\s+[^-]{2,60}$", "", title).strip()


def _categorize(title: str, summary: str) -> str:
    combined = (title + " " + summary).lower()
    if any(kw in combined for kw in [
        "release", "update", "new feature", "launch", "announce",
        "product", "platform", "zia", "zpa", "zdx", "zscaler one",
    ]):
        return "product_update"
    if any(kw in combined for kw in [
        "threat", "malware", "phishing", "ransomware", "research",
        "vulnerability", "exploit", "cve", "attack", "threatlabz",
    ]):
        return "tech_blog"
    return "industry_news"


def fetch_xml(url: str, timeout: int = 20) -> bytes | None:
    try:
        headers = {"User-Agent": "Mozilla/5.0 (compatible; ZscalerDashboard/1.0)"}
        resp = requests.get(url, headers=headers, timeout=timeout)
        resp.raise_for_status()
        return resp.content
    except Exception as e:
        print(f"  [SKIP] {url[:70]}: {e}")
        return None


def parse_google_news_feed(xml_bytes: bytes) -> list[dict]:
    """Parse Google News RSS items."""
    try:
        root = ET.fromstring(xml_bytes)
    except ET.ParseError as e:
        print(f"  [XML error] {e}")
        return []

    items = []
    for item in root.findall(".//item"):
        title_el = item.find("title")
        link_el = item.find("link")
        pub_el = item.find("pubDate")
        source_el = item.find("source")
        desc_el = item.find("description")

        title = (title_el.text or "").strip() if title_el is not None else ""
        link = (link_el.text or "").strip() if link_el is not None else ""
        pub_date = (pub_el.text or "").strip() if pub_el is not None else ""
        source = (source_el.text or "").strip() if source_el is not None else ""
        summary = (desc_el.text or "").strip() if desc_el is not None else ""

        title = _strip_source_suffix(title)
        items.append({
            "title": title,
            "link": link,
            "date": pub_date,
            "source": source,
            "summary": summary,
        })
    return items


def parse_rss_feed(xml_bytes: bytes) -> list[dict]:
    """Parse standard RSS 2.0 feed."""
    try:
        root = ET.fromstring(xml_bytes)
    except ET.ParseError as e:
        print(f"  [XML error] {e}")
        return []

    items = []
    for item in root.findall(".//item"):
        t = item.find("title")
        l = item.find("link")
        d = item.find("description")
        p = item.find("pubDate")

        title = (t.text or "").strip() if t is not None else ""
        link = (l.text or "").strip() if l is not None else ""
        summary = (d.text or "").strip() if d is not None else ""
        pub_date = (p.text or "").strip() if p is not None else ""

        if title and link:
            items.append({"title": title, "link": link, "date": pub_date, "summary": summary})
    return items


def fetch_google_news(feeds: list[dict]) -> list[dict]:
    articles = []
    seen_titles: set[str] = set()

    for cfg in feeds:
        print(f"Fetching {cfg['label']} …")
        xml_bytes = fetch_xml(cfg["url"])
        if not xml_bytes:
            continue

        items = parse_google_news_feed(xml_bytes)
        count = 0
        for item in items:
            if not item["title"] or not item["link"]:
                continue
            norm_title = item["title"].lower()
            if norm_title in seen_titles:
                continue
            seen_titles.add(norm_title)

            summary = _clean_html(item["summary"])
            combined = item["title"] + " " + summary
            tags = _detect_tags(combined)
            category = _categorize(item["title"], summary)
            articles.append({
                "id": _article_id(item["link"]),
                "title": item["title"],
                "url": item["link"],
                "summary": summary,
                "date": _parse_date(item["date"]),
                "source": item["source"] or "Google News",
                "category": category,
                "tags": tags,
            })
            count += 1
        print(f"  → {count} articles")
    return articles


def fetch_supplemental(feeds: list[dict]) -> list[dict]:
    articles = []
    for cfg in feeds:
        print(f"Fetching {cfg['source']} …")
        xml_bytes = fetch_xml(cfg["url"])
        if not xml_bytes:
            continue

        count = 0
        for item in parse_rss_feed(xml_bytes):
            summary = _clean_html(item["summary"])
            if not _is_zscaler_related(item["title"], summary):
                continue
            tags = _detect_tags(item["title"] + " " + summary)
            articles.append({
                "id": _article_id(item["link"]),
                "title": item["title"],
                "url": item["link"],
                "summary": summary,
                "date": _parse_date(item["date"]),
                "source": cfg["source"],
                "category": cfg["category"],
                "tags": tags,
            })
            count += 1
        print(f"  → {count} Zscaler articles")
    return articles


def main():
    all_articles: list[dict] = []
    seen_ids: set[str] = set()

    for a in fetch_google_news(GOOGLE_NEWS_FEEDS):
        if a["id"] not in seen_ids:
            all_articles.append(a)
            seen_ids.add(a["id"])

    for a in fetch_supplemental(SUPPLEMENTAL_FEEDS):
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
    print(f"\n合計 {len(all_articles)} 件の記事を保存しました → {DATA_FILE}")


if __name__ == "__main__":
    main()
