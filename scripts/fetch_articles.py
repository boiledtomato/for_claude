#!/usr/bin/env python3
"""
Fetch Zscaler-related articles from RSS feeds and save to data/articles.json.
"""

import json
import hashlib
import re
from datetime import datetime, timezone
from pathlib import Path

import feedparser
import requests

SCRIPT_DIR = Path(__file__).parent
DATA_FILE = SCRIPT_DIR.parent / "data" / "articles.json"

MAX_ARTICLES = 200
MAX_SUMMARY_LEN = 400

# Feeds where ALL articles are Zscaler content
ZSCALER_FEEDS = [
    {
        "url": "https://www.zscaler.com/blogs/product-insights/feed",
        "source": "Zscaler Product Blog",
        "category": "product_update",
        "default_tags": ["ZIA", "ZPA"],
    },
    {
        "url": "https://www.zscaler.com/blogs/security-research/feed",
        "source": "Zscaler ThreatLabz",
        "category": "tech_blog",
        "default_tags": [],
    },
    {
        "url": "https://www.zscaler.com/feed",
        "source": "Zscaler",
        "category": "industry_news",
        "default_tags": [],
    },
]

# General security feeds — filter for Zscaler keywords
GENERAL_FEEDS = [
    {
        "url": "https://feeds.feedburner.com/TheHackersNews",
        "source": "The Hacker News",
        "category": "industry_news",
    },
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
        "url": "https://www.darkreading.com/rss.xml",
        "source": "Dark Reading",
        "category": "industry_news",
    },
    {
        "url": "https://isc.sans.edu/rssfeed_full.xml",
        "source": "SANS ISC",
        "category": "tech_blog",
    },
    {
        "url": "https://www.bleepingcomputer.com/feed/",
        "source": "Bleeping Computer",
        "category": "industry_news",
    },
]

ZSCALER_KEYWORDS = [
    "zscaler", "zia", "zpa", "zdx", "zscalerone", "ztna",
    "sase", "zero trust network access", "secure access service edge",
    "cloud proxy", "ssl inspection", "casb", "cloud firewall",
]

PRODUCT_TAGS = {
    "ZIA": ["zia", "internet access", "web gateway", "swg", "url filtering",
             "cloud firewall", "sandbox", "dlp", "casb", "ssl inspection"],
    "ZPA": ["zpa", "private access", "ztna", "zero trust network access",
             "app connector", "application connector", "remote access"],
    "ZDX": ["zdx", "digital experience", "end user experience",
             "performance monitoring"],
}


def _article_id(url: str) -> str:
    return hashlib.sha256(url.encode()).hexdigest()[:16]


def _clean_html(text: str) -> str:
    text = re.sub(r"<[^>]+>", "", text or "")
    text = re.sub(r"\s+", " ", text).strip()
    return text[:MAX_SUMMARY_LEN]


def _parse_date(entry) -> str:
    for attr in ("published_parsed", "updated_parsed"):
        t = getattr(entry, attr, None)
        if t:
            try:
                dt = datetime(*t[:6], tzinfo=timezone.utc)
                return dt.strftime("%Y-%m-%dT%H:%M:%SZ")
            except Exception:
                pass
    return datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")


def _detect_tags(text: str) -> list[str]:
    lower = text.lower()
    tags = []
    for tag, keywords in PRODUCT_TAGS.items():
        if any(kw in lower for kw in keywords):
            tags.append(tag)
    return tags


def _is_zscaler_related(title: str, summary: str) -> bool:
    combined = (title + " " + summary).lower()
    return any(kw in combined for kw in ZSCALER_KEYWORDS)


def fetch_feed(url: str, timeout: int = 15) -> feedparser.FeedParserDict | None:
    try:
        headers = {"User-Agent": "Mozilla/5.0 (compatible; ZscalerDashboard/1.0)"}
        resp = requests.get(url, headers=headers, timeout=timeout)
        resp.raise_for_status()
        return feedparser.parse(resp.content)
    except Exception as e:
        print(f"  [SKIP] {url}: {e}")
        return None


def process_zscaler_feed(cfg: dict) -> list[dict]:
    print(f"Fetching Zscaler feed: {cfg['source']}")
    feed = fetch_feed(cfg["url"])
    if not feed or not feed.entries:
        return []

    articles = []
    for entry in feed.entries:
        title = entry.get("title", "").strip()
        link = entry.get("link", "").strip()
        if not title or not link:
            continue
        summary = _clean_html(
            entry.get("summary", "") or entry.get("description", "")
        )
        date = _parse_date(entry)
        combined = title + " " + summary
        tags = _detect_tags(combined) or cfg.get("default_tags", [])
        articles.append({
            "id": _article_id(link),
            "title": title,
            "url": link,
            "summary": summary,
            "date": date,
            "source": cfg["source"],
            "category": cfg["category"],
            "tags": tags,
        })
    print(f"  → {len(articles)} articles")
    return articles


def process_general_feed(cfg: dict) -> list[dict]:
    print(f"Fetching general feed: {cfg['source']}")
    feed = fetch_feed(cfg["url"])
    if not feed or not feed.entries:
        return []

    articles = []
    for entry in feed.entries:
        title = entry.get("title", "").strip()
        link = entry.get("link", "").strip()
        if not title or not link:
            continue
        summary = _clean_html(
            entry.get("summary", "") or entry.get("description", "")
        )
        if not _is_zscaler_related(title, summary):
            continue
        date = _parse_date(entry)
        combined = title + " " + summary
        tags = _detect_tags(combined)
        articles.append({
            "id": _article_id(link),
            "title": title,
            "url": link,
            "summary": summary,
            "date": date,
            "source": cfg["source"],
            "category": cfg["category"],
            "tags": tags,
        })
    print(f"  → {len(articles)} Zscaler-related articles")
    return articles


def main():
    all_articles: list[dict] = []
    seen_ids: set[str] = set()

    for cfg in ZSCALER_FEEDS:
        for a in process_zscaler_feed(cfg):
            if a["id"] not in seen_ids:
                all_articles.append(a)
                seen_ids.add(a["id"])

    for cfg in GENERAL_FEEDS:
        for a in process_general_feed(cfg):
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
    print(f"\nSaved {len(all_articles)} articles to {DATA_FILE}")


if __name__ == "__main__":
    main()
