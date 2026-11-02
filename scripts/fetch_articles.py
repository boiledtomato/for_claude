#!/usr/bin/env python3
"""
Fetch Zscaler-related articles from RSS feeds and save to data/articles.json.
"""

import json
import hashlib
import sys
import re
from datetime import datetime, timezone
from pathlib import Path

import feedparser
import requests

SCRIPT_DIR = Path(__file__).parent
DATA_FILE = SCRIPT_DIR.parent / "data" / "articles.json"

MAX_ARTICLES = 200
MAX_SUMMARY_LEN = 400

# Zscaler's own blog.
#
# There is no RSS feed any more. /blogs/*/feed, /feed, /rss.xml and six other
# conventional paths all return 404 — the site was rebuilt on Next.js and the
# feeds went with it. The page advertises no <link rel="alternate"> either.
# The old URLs had been 404ing since at least 2026-05 without anyone noticing,
# because fetch_feed() swallows errors and the run still succeeded.
#
# Instead the listing page's embedded __NEXT_DATA__ is read. It carries the
# latest post of each blog section with richer metadata than the feed ever had:
# title, publish timestamp, path, subtitle, author, products and topics.
# Only ~8 posts are exposed (one per section), so main() merges each run into
# the articles already stored rather than rebuilding the file from scratch.
ZSCALER_BLOG_URL = "https://www.zscaler.com/blogs"

# First path segment of /blogs/<section>/… → (source label, category, fallback tags)
BLOG_SECTIONS = {
    "product-insights": ("Zscaler Product Blog", "product_update", ["ZIA", "ZPA"]),
    "security-research": ("Zscaler ThreatLabz", "tech_blog", []),
    "cybersecurity-best-practices": ("Zscaler Best Practices", "tech_blog", []),
    "cxo-insights": ("Zscaler CXO Insights", "tech_blog", []),
    "customer-stories": ("Zscaler Customer Stories", "industry_news", []),
    "company-news": ("Zscaler News", "industry_news", []),
    "partner": ("Zscaler Partners", "industry_news", []),
    "zscaler-life": ("Zscaler Life", "industry_news", []),
}
DEFAULT_BLOG_SECTION = ("Zscaler Blog", "industry_news", [])

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


def _next_data(html: str) -> dict | None:
    m = re.search(
        r'<script id="__NEXT_DATA__"[^>]*>(.*?)</script>', html, re.DOTALL
    )
    if not m:
        return None
    try:
        return json.loads(m.group(1))
    except json.JSONDecodeError:
        return None


def _collect_posts(node, found: dict) -> None:
    """__NEXT_DATA__ のどこに置かれていても記事オブジェクトを拾う。

    Next.js のページデータは構造がビルドごとに変わるため、決め打ちのパスでは
    なく「title と publishedAt と path を持つ辞書」を再帰的に探す。同じ記事が
    複数箇所に現れるので path で一意化する。
    """
    if isinstance(node, dict):
        if "title" in node and "publishedAt" in node and "path" in node:
            found.setdefault(node["path"], node)
        for v in node.values():
            _collect_posts(v, found)
    elif isinstance(node, list):
        for v in node:
            _collect_posts(v, found)


def _term_names(post: dict, key: str) -> list[str]:
    return [t.get("name", "") for t in (post.get(key) or []) if isinstance(t, dict)]


def fetch_zscaler_blog(timeout: int = 20) -> list[dict]:
    """Zscaler 公式ブログの最新記事を __NEXT_DATA__ から取得する。"""
    print(f"Fetching Zscaler blog: {ZSCALER_BLOG_URL}")
    try:
        headers = {"User-Agent": "Mozilla/5.0 (compatible; ZscalerDashboard/1.0)"}
        resp = requests.get(ZSCALER_BLOG_URL, headers=headers, timeout=timeout)
        resp.raise_for_status()
    except Exception as e:
        print(f"  [SKIP] {ZSCALER_BLOG_URL}: {e}")
        return []

    data = _next_data(resp.text)
    if data is None:
        print("  [SKIP] __NEXT_DATA__ が見つかりません（ページ構成が変わった可能性）")
        return []

    found: dict[str, dict] = {}
    _collect_posts(data, found)

    articles = []
    for path, post in found.items():
        title = (post.get("title") or "").strip()
        if not title or not path.startswith("/blogs/"):
            continue
        url = "https://www.zscaler.com" + path
        segment = path.split("/")[2] if len(path.split("/")) > 2 else ""
        source, category, fallback_tags = BLOG_SECTIONS.get(
            segment, DEFAULT_BLOG_SECTION
        )
        summary = _clean_html(post.get("fieldSubtitle") or "")

        ts = (post.get("publishedAt") or {}).get("timestamp")
        if isinstance(ts, (int, float)):
            date = datetime.fromtimestamp(ts, timezone.utc).strftime(
                "%Y-%m-%dT%H:%M:%SZ"
            )
        else:
            date = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")

        # products / topics は Zscaler 自身が付けた分類なので、本文の keyword 推測
        # より信頼できる。タグ検出にはこれらも混ぜる。
        terms = " ".join(_term_names(post, "products") + _term_names(post, "topics"))
        tags = _detect_tags(f"{title} {summary} {terms}") or fallback_tags

        articles.append({
            "id": _article_id(url),
            "title": title,
            "url": url,
            "summary": summary,
            "date": date,
            "source": source,
            "category": category,
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


def load_existing() -> list[dict]:
    """すでに保存済みの記事を読む。

    ブログは最新 8 件ほどしか露出せず、第三者フィードも直近分しか返さないため、
    毎回ゼロから作り直すとダッシュボードは常に数件しか並ばない。過去の取得分を
    引き継いで MAX_ARTICLES まで貯める。
    """
    if not DATA_FILE.exists():
        return []
    try:
        return json.loads(DATA_FILE.read_text(encoding="utf-8")).get("articles") or []
    except (json.JSONDecodeError, OSError) as e:
        print(f"[WARN] 既存の {DATA_FILE.name} を読めませんでした: {e}")
        return []


def main() -> int:
    zscaler_articles = fetch_zscaler_blog()

    general_articles: list[dict] = []
    for cfg in GENERAL_FEEDS:
        general_articles.extend(process_general_feed(cfg))

    # 公式ソースが 0 件なら取得経路が壊れている。黙って成功させると、以前のように
    # 何か月も空のダッシュボードを配信し続けることになる。
    if not zscaler_articles:
        print(
            "::error::Zscaler 公式ブログから 1 件も取得できませんでした。"
            f"{ZSCALER_BLOG_URL} の構成が変わった可能性があります。"
        )
        return 1

    all_articles: list[dict] = []
    seen_ids: set[str] = set()
    for a in zscaler_articles + general_articles + load_existing():
        if a.get("id") and a["id"] not in seen_ids:
            all_articles.append(a)
            seen_ids.add(a["id"])

    before = len(load_existing())
    all_articles.sort(key=lambda a: a["date"], reverse=True)
    all_articles = all_articles[:MAX_ARTICLES]

    result = {
        "last_updated": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
        "total": len(all_articles),
        "articles": all_articles,
    }

    DATA_FILE.parent.mkdir(parents=True, exist_ok=True)
    DATA_FILE.write_text(json.dumps(result, ensure_ascii=False, indent=2))
    print(
        f"\nSaved {len(all_articles)} articles to {DATA_FILE} "
        f"(既存 {before} 件 → 新規 {len(all_articles) - before} 件増)"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
