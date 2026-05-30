#!/usr/bin/env python3
"""
Dynamically fetch and update the Zscaler knowledge base.

Sources (in priority order):
1. Zscaler public help pages (help.zscaler.com)
2. Zscaler product / ThreatLabz blog posts
3. Curated static fallback topics

Output: data/knowledge_base.json
"""

import hashlib
import json
import re
import time
from datetime import datetime, timezone
from pathlib import Path

import requests
from bs4 import BeautifulSoup

SCRIPT_DIR = Path(__file__).parent
DATA_FILE = SCRIPT_DIR.parent / "data" / "knowledge_base.json"
STATIC_FILE = SCRIPT_DIR.parent / "ios-app" / "ZscalerHub" / "Resources" / "knowledge_base.json"

HEADERS = {
    "User-Agent": (
        "Mozilla/5.0 (compatible; ZscalerKnowledgeBot/1.0; "
        "+https://github.com/boiledtomato/for_claude)"
    ),
    "Accept-Language": "ja,en-US;q=0.9,en;q=0.8",
}
TIMEOUT = 20
REQUEST_DELAY = 1.5  # seconds between requests — be polite


# ---------------------------------------------------------------------------
# Help-page targets: (url, category, subcategory, tags)
# ---------------------------------------------------------------------------
HELP_PAGES = [
    # ZIA
    (
        "https://help.zscaler.com/zia/about-url-filtering",
        "ZIA", "コンテンツフィルタリング",
        ["URLフィルタリング", "カテゴリ", "ポリシー", "ZIA"],
    ),
    (
        "https://help.zscaler.com/zia/about-ssl-inspection",
        "ZIA", "トラフィック検査",
        ["SSLインスペクション", "HTTPS", "証明書", "ZIA"],
    ),
    (
        "https://help.zscaler.com/zia/about-cloud-firewall",
        "ZIA", "ネットワークセキュリティ",
        ["ファイアウォール", "DNS", "L4-L7", "ZIA"],
    ),
    (
        "https://help.zscaler.com/zia/about-data-loss-prevention",
        "ZIA", "データ保護",
        ["DLP", "データ保護", "個人情報", "ZIA"],
    ),
    (
        "https://help.zscaler.com/zia/about-cloud-sandboxing",
        "ZIA", "マルウェア防御",
        ["サンドボックス", "マルウェア", "ゼロデイ", "ZIA"],
    ),
    (
        "https://help.zscaler.com/zia/about-bandwidth-control",
        "ZIA", "QoS・パフォーマンス",
        ["帯域制御", "QoS", "ストリーミング", "ZIA"],
    ),
    # ZPA
    (
        "https://help.zscaler.com/zpa/about-zscaler-private-access",
        "ZPA", "基本概念",
        ["ZPA", "ZTNA", "ゼロトラスト", "概要"],
    ),
    (
        "https://help.zscaler.com/zpa/configuring-app-connectors",
        "ZPA", "インフラコンポーネント",
        ["App Connector", "コネクター", "ZPA"],
    ),
    (
        "https://help.zscaler.com/zpa/about-application-segments",
        "ZPA", "アクセス設計",
        ["アプリセグメント", "FQDN", "ポート", "ZPA"],
    ),
    (
        "https://help.zscaler.com/zpa/about-access-policy",
        "ZPA", "ポリシー管理",
        ["アクセスポリシー", "IdP", "デバイスポスチャー", "ZPA"],
    ),
    # ZDX
    (
        "https://help.zscaler.com/zdx/about-zdx",
        "ZDX", "基本概念",
        ["ZDX", "デジタル体験", "パフォーマンス監視", "概要"],
    ),
    (
        "https://help.zscaler.com/zdx/about-web-probes",
        "ZDX", "監視設定",
        ["ZDX", "Web Probe", "レイテンシ", "監視"],
    ),
]

# Blog feed for dynamic articles → additional enrichment topics
BLOG_FEEDS = [
    "https://www.zscaler.com/blogs/product-insights/feed",
    "https://www.zscaler.com/blogs/security-research/feed",
]


# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def _slug(url: str) -> str:
    return hashlib.sha256(url.encode()).hexdigest()[:12]


def _clean(text: str, max_len: int = 2000) -> str:
    text = re.sub(r"\s+", " ", text or "").strip()
    return text[:max_len]


def _fetch(url: str) -> requests.Response | None:
    try:
        r = requests.get(url, headers=HEADERS, timeout=TIMEOUT)
        r.raise_for_status()
        return r
    except Exception as e:
        print(f"  [SKIP] {url}: {e}")
        return None


# ---------------------------------------------------------------------------
# Help-page scraper
# ---------------------------------------------------------------------------

def scrape_help_page(url: str, category: str, subcategory: str, tags: list[str]) -> dict | None:
    resp = _fetch(url)
    if not resp:
        return None
    time.sleep(REQUEST_DELAY)

    soup = BeautifulSoup(resp.text, "html.parser")

    # Title
    title_tag = (
        soup.find("h1")
        or soup.find("title")
    )
    title = _clean(title_tag.get_text(), 120) if title_tag else url.split("/")[-1]

    # Try common content selectors
    selectors = [
        "article", ".article-body", ".content-body",
        "main", "#content", ".topic-body", ".help-content",
    ]
    body_tag = None
    for sel in selectors:
        body_tag = soup.select_one(sel)
        if body_tag:
            break
    if not body_tag:
        body_tag = soup.body

    # Remove nav / header / footer / script noise
    for noise in (body_tag or soup).find_all(
        ["nav", "header", "footer", "script", "style", "aside", "form"]
    ):
        noise.decompose()

    raw = _clean((body_tag or soup).get_text(separator="\n"), 3000)
    if not raw or len(raw) < 100:
        print(f"  [SKIP] {url}: content too short after cleaning")
        return None

    # Reject JS-rendered pages that returned fallback error text
    if "javascript has been disabled" in raw.lower() or "enable js to make" in raw.lower():
        print(f"  [SKIP] {url}: JS-rendered page, no static content available")
        return None

    # Derive a short summary (first meaningful paragraph)
    lines = [l.strip() for l in raw.split("\n") if len(l.strip()) > 40]
    summary = lines[0][:300] if lines else raw[:300]

    print(f"  [OK] {title[:60]} ({category})")
    return {
        "id": _slug(url),
        "title": title,
        "category": category,
        "subcategory": subcategory,
        "summary": summary,
        "content": raw,
        "tags": tags,
        "relatedTopicIds": [],
        "configExample": None,
        "source_url": url,
        "fetched_at": datetime.now(timezone.utc).isoformat(),
    }


# ---------------------------------------------------------------------------
# Blog-based enrichment (pull recent posts as lightweight topics)
# ---------------------------------------------------------------------------

def fetch_blog_topics() -> list[dict]:
    import feedparser  # installed in workflow alongside requests

    topics: list[dict] = []
    for feed_url in BLOG_FEEDS:
        resp = _fetch(feed_url)
        if not resp:
            continue
        feed = feedparser.parse(resp.content)
        for entry in feed.entries[:5]:
            link = entry.get("link", "")
            title = _clean(entry.get("title", ""), 120)
            summary_raw = entry.get("summary", "") or entry.get("description", "")
            summary = _clean(re.sub(r"<[^>]+>", "", summary_raw), 400)
            if not title or not link or not summary:
                continue
            topics.append({
                "id": _slug(link),
                "title": title,
                "category": "ZIA",  # most product blog posts are ZIA/general
                "subcategory": "最新情報",
                "summary": summary,
                "content": summary,
                "tags": ["ブログ", "最新情報", "Zscaler"],
                "relatedTopicIds": [],
                "configExample": None,
                "source_url": link,
                "fetched_at": datetime.now(timezone.utc).isoformat(),
            })
        time.sleep(REQUEST_DELAY)
    return topics


# ---------------------------------------------------------------------------
# Load static fallback
# ---------------------------------------------------------------------------

def load_static_fallback() -> list[dict]:
    if STATIC_FILE.exists():
        try:
            data = json.loads(STATIC_FILE.read_text(encoding="utf-8"))
            topics = data.get("topics", [])
            print(f"  [Fallback] Loaded {len(topics)} static topics from ios-app KB")
            return topics
        except Exception as e:
            print(f"  [Fallback] Could not read {STATIC_FILE}: {e}")
    return []


# ---------------------------------------------------------------------------
# Merge: deduplicate by id, prefer freshly fetched
# ---------------------------------------------------------------------------

def merge(dynamic: list[dict], fallback: list[dict]) -> list[dict]:
    seen: set[str] = set()
    merged: list[dict] = []
    for t in dynamic + fallback:
        tid = t.get("id", "")
        if tid and tid not in seen:
            seen.add(tid)
            merged.append(t)
    return merged


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    print("=== Zscaler Knowledge Base Fetcher ===")
    dynamic_topics: list[dict] = []

    # 1. Scrape help pages
    print("\n[1/3] Scraping help.zscaler.com pages...")
    for url, category, subcategory, tags in HELP_PAGES:
        topic = scrape_help_page(url, category, subcategory, tags)
        if topic:
            dynamic_topics.append(topic)

    # 2. Pull recent blog posts as supplemental topics
    print("\n[2/3] Fetching blog feed topics...")
    try:
        blog_topics = fetch_blog_topics()
        print(f"  → {len(blog_topics)} blog topics")
        dynamic_topics.extend(blog_topics)
    except ImportError:
        print("  [SKIP] feedparser not available for blog fetch")

    # 3. Merge with static fallback (static fills any gaps)
    print("\n[3/3] Merging with static fallback...")
    fallback = load_static_fallback()
    all_topics = merge(dynamic_topics, fallback)

    result = {
        "version": "1.0",
        "last_updated": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
        "total": len(all_topics),
        "topics": all_topics,
    }

    DATA_FILE.parent.mkdir(parents=True, exist_ok=True)
    DATA_FILE.write_text(
        json.dumps(result, ensure_ascii=False, indent=2), encoding="utf-8"
    )
    print(f"\n✓ Saved {len(all_topics)} topics to {DATA_FILE}")
    print(f"  Dynamic (scraped): {len(dynamic_topics)}")
    print(f"  From static fallback: {len(all_topics) - len(dynamic_topics)}")


if __name__ == "__main__":
    main()
