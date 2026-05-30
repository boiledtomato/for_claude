#!/usr/bin/env python3
"""
Fetch the latest technical articles directly from Zscaler's official website.
Uses Zscaler's Next.js internal API — no RSS, no Google News.
"""

import hashlib
import json
import re
from concurrent.futures import ThreadPoolExecutor, as_completed
from datetime import datetime, timezone
from pathlib import Path

import requests

SCRIPT_DIR = Path(__file__).parent
DATA_FILE = SCRIPT_DIR.parent / "data" / "articles.json"

HEADERS = {
    "User-Agent": (
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) "
        "AppleWebKit/537.36 (KHTML, like Gecko) "
        "Chrome/120.0.0.0 Safari/537.36"
    )
}

# サイトマップからサンプリングする候補数
CANDIDATE_LIMIT = 80
# 最終的に保存する記事数
MAX_ARTICLES = 20

CATEGORY_MAP = {
    "product-insights": "product_update",
    "security-research": "tech_blog",
    "industry-trends": "industry_news",
}

PRODUCT_TAGS = {
    "ZIA": ["zia", "internet access", "web gateway", "swg", "url filtering",
            "cloud firewall", "sandbox", "dlp", "casb", "ssl inspection"],
    "ZPA": ["zpa", "private access", "ztna", "zero trust network access",
            "app connector", "application connector"],
    "ZDX": ["zdx", "digital experience", "end user experience"],
}


# ── ユーティリティ ──────────────────────────────────────────────

def _strip_html(html: str) -> str:
    text = re.sub(r"<[^>]+>", " ", html or "")
    for entity, repl in [("&amp;", "&"), ("&lt;", "<"), ("&gt;", ">"),
                          ("&nbsp;", " "), ("&#39;", "'"), ("&quot;", '"')]:
        text = text.replace(entity, repl)
    return re.sub(r"\s+", " ", text).strip()


def _to_bullets(body_html: str) -> list[str]:
    """HTML本文から箇条書き用の要点リストを生成する。"""
    text = _strip_html(body_html)
    # 文区切りで分割
    sentences = re.split(r"(?<=[。\.\!\?])\s+", text)
    bullets = []
    for s in sentences:
        s = s.strip()
        # ノイズ除外: Disclaimer / 短すぎる / URLのみ
        if len(s) < 40:
            continue
        if "disclaimer" in s.lower() or s.startswith("http"):
            continue
        bullets.append(s[:180])
        if len(bullets) >= 3:
            break
    return bullets


def _detect_tags(text: str) -> list[str]:
    lower = " " + text.lower() + " "
    return [tag for tag, kws in PRODUCT_TAGS.items()
            if any(kw in lower for kw in kws)]


def _article_id(url: str) -> str:
    return hashlib.sha256(url.encode()).hexdigest()[:16]


def _unix_to_iso(ts) -> str:
    try:
        return datetime.fromtimestamp(int(ts), tz=timezone.utc).strftime(
            "%Y-%m-%dT%H:%M:%SZ"
        )
    except Exception:
        return datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")


# ── Zscaler Next.js API ────────────────────────────────────────

def get_build_id() -> str:
    print("build ID を取得中…")
    r = requests.get(
        "https://www.zscaler.com/blogs/product-insights",
        headers=HEADERS,
        timeout=20,
    )
    match = re.search(r'"buildId":"([^"]+)"', r.text)
    if not match:
        raise RuntimeError("build ID が見つかりません")
    build_id = match.group(1)
    print(f"  build ID: {build_id}")
    return build_id


def get_candidate_urls() -> list[str]:
    """sitemapから対象ブログURLを取得し、最新記事を優先するよう並び替える。"""
    print("sitemapからURLを取得中…")
    r = requests.get(
        "https://www.zscaler.com/sitemap.xml", headers=HEADERS, timeout=20
    )
    all_urls = re.findall(
        r"<loc>(https://www\.zscaler\.com/blogs/[^<]+)</loc>", r.text
    )
    target = [
        u for u in all_urls
        if "/product-insights/" in u or "/security-research/" in u
    ]
    # 年付きスラッグ（2024-, 2025-, 2026-）を優先
    recent = [u for u in target if re.search(r"/202[4-9]-", u)]
    others = [u for u in target if u not in set(recent)]
    candidates = (recent + others)[:CANDIDATE_LIMIT]
    print(f"  候補 {len(candidates)} 件 (優先: {len(recent)} 件)")
    return candidates


def fetch_article(url: str, build_id: str) -> dict | None:
    """1件の記事をZscaler Next.js APIから取得して整形する。"""
    slug = url.replace("https://www.zscaler.com/", "")
    api_url = f"https://www.zscaler.com/_next/data/{build_id}/{slug}.json"
    try:
        r = requests.get(api_url, headers=HEADERS, timeout=15)
        if r.status_code != 200:
            return None
        data = r.json().get("pageProps", {})
        paragraphs = data.get("paragraph", [])

        title = None
        created_ts = None
        main_category_slug = None
        body_html = ""

        for p in paragraphs:
            typename = p.get("__typename", "")

            # タイトル・日付・カテゴリ
            if typename == "ParagraphBlogPostHeaderModule":
                post = p.get("post") or {}
                title = post.get("title", "")
                created_ts = post.get("created")
                cat = post.get("mainCategory") or {}
                main_category_slug = cat.get("slug", "") if isinstance(cat, dict) else ""

            # 本文（コンテナ内のWYSIWYGを結合）
            elif typename == "ParagraphBlogContainer":
                for rp in p.get("fieldReusableParagraphs") or []:
                    fb = rp.get("fieldBody") or {}
                    if isinstance(fb, dict):
                        body_html += fb.get("value", "")

            # トップレベルWYSIWYG
            elif typename == "ParagraphWysiwygModule":
                fb = p.get("fieldBody") or {}
                if isinstance(fb, dict):
                    val = fb.get("value", "")
                    if "disclaimer" not in val.lower():
                        body_html += val

        if not title or not created_ts:
            return None

        # カテゴリをURLから補完
        for key, cat in CATEGORY_MAP.items():
            if f"/{key}/" in url:
                category = cat
                break
        else:
            category = CATEGORY_MAP.get(main_category_slug, "industry_news")

        combined = title + " " + _strip_html(body_html)
        bullets = _to_bullets(body_html)

        return {
            "id": _article_id(url),
            "title": title.strip(),
            "url": url,
            "summary": _strip_html(body_html)[:400],
            "bullets": bullets,
            "date": _unix_to_iso(created_ts),
            "created_ts": int(created_ts),
            "source": "Zscaler 公式ブログ",
            "category": category,
            "tags": _detect_tags(combined),
        }
    except Exception as e:
        return None


# ── メイン ─────────────────────────────────────────────────────

def main():
    build_id = get_build_id()
    candidates = get_candidate_urls()

    print(f"\n{len(candidates)} 件の記事データを並列取得中…")
    articles: list[dict] = []
    with ThreadPoolExecutor(max_workers=8) as executor:
        futures = {executor.submit(fetch_article, url, build_id): url
                   for url in candidates}
        for future in as_completed(futures):
            result = future.result()
            if result:
                articles.append(result)

    # 日付降順ソート → 最新 MAX_ARTICLES 件
    articles.sort(key=lambda a: a["created_ts"], reverse=True)
    articles = articles[:MAX_ARTICLES]

    # created_ts は保存不要
    for a in articles:
        a.pop("created_ts", None)

    result = {
        "last_updated": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
        "total": len(articles),
        "articles": articles,
    }

    DATA_FILE.parent.mkdir(parents=True, exist_ok=True)
    DATA_FILE.write_text(json.dumps(result, ensure_ascii=False, indent=2))
    print(f"\n✓ {len(articles)} 件の公式記事を保存 → {DATA_FILE}")
    for a in articles[:5]:
        print(f"  [{a['category']}] {a['title'][:60]}")
        for b in a["bullets"][:2]:
            print(f"    • {b[:80]}")


if __name__ == "__main__":
    main()
