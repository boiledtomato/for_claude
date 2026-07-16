"""
Zscaler Help Center ページスクレイパー

単一URLを取得し、メインコンテンツ(title, body_text)を抽出する。
readability-lxml でナビゲーション/フッター等を除去し、本文のみを残す。
robots.txt と crawl-delay を尊重し、リクエスト間にレート制限を挟む。

使い方:
    pip install requests readability-lxml lxml
"""

from __future__ import annotations

import re
import time
import urllib.robotparser as robotparser
import xml.etree.ElementTree as ET
from dataclasses import dataclass
from urllib.parse import urlparse

import requests
from lxml import html as lxml_html
from readability import Document

DEFAULT_TIMEOUT = 20
DEFAULT_DELAY = 1.0
USER_AGENT = "Mozilla/5.0 (compatible; ZscalerHelpSync/1.0; +https://github.com/boiledtomato/for_claude)"

SITEMAP_URL = "https://help.zscaler.com/sitemap.xml"

SESSION = requests.Session()
SESSION.headers.update({"User-Agent": USER_AGENT})

# ホスト単位でrobots.txtをキャッシュ（毎リクエストで取りに行かない）
_robots_cache: dict[str, robotparser.RobotFileParser] = {}

# 本文中から更新日時らしき値を拾うための簡易パターン（疑似情報。取得できなければNone）
_LAST_MODIFIED_PATTERNS = [
    re.compile(r'"dateModified"\s*:\s*"([^"]+)"'),
    re.compile(r'"datePublished"\s*:\s*"([^"]+)"'),
]


@dataclass
class ScrapedPage:
    url: str
    title: str
    body_text: str
    last_modified: str | None  # 疑似情報。取得できない場合は None


def _get_robots_parser(url: str) -> robotparser.RobotFileParser:
    parsed = urlparse(url)
    origin = f"{parsed.scheme}://{parsed.netloc}"
    if origin not in _robots_cache:
        rp = robotparser.RobotFileParser()
        rp.set_url(f"{origin}/robots.txt")
        try:
            rp.read()
        except Exception:
            pass  # robots.txt取得失敗時は「許可」扱い（デフォルトの動作に委ねる）
        _robots_cache[origin] = rp
    return _robots_cache[origin]


def is_allowed_by_robots(url: str) -> bool:
    """robots.txt に照らして取得可否を判定する。"""
    rp = _get_robots_parser(url)
    return rp.can_fetch(USER_AGENT, url)


def crawl_delay_for(url: str) -> float:
    """robots.txt の Crawl-delay を返す。指定がなければ DEFAULT_DELAY。"""
    rp = _get_robots_parser(url)
    delay = rp.crawl_delay(USER_AGENT)
    return float(delay) if delay else DEFAULT_DELAY


def _extract_last_modified(html_text: str) -> str | None:
    """本文中の更新日時らしき文字列を推定する（疑似情報）。取得できなければNone。"""
    for pattern in _LAST_MODIFIED_PATTERNS:
        m = pattern.search(html_text)
        if m:
            return m.group(1)
    return None


def scrape_page(url: str) -> ScrapedPage | None:
    """1URLを取得し ScrapedPage を返す。取得/抽出に失敗した場合は None。"""
    if not is_allowed_by_robots(url):
        print(f"  [SKIP] {url} — robots.txtにより許可されていません")
        return None

    time.sleep(crawl_delay_for(url))

    try:
        resp = SESSION.get(url, timeout=DEFAULT_TIMEOUT)
        resp.raise_for_status()
    except Exception as e:
        print(f"  [SKIP] {url} — {e}")
        return None

    try:
        doc = Document(resp.text)
        title = doc.short_title().strip()
        body_text = lxml_html.fromstring(doc.summary()).text_content()
        body_text = re.sub(r"[ \t]+", " ", body_text)
        body_text = re.sub(r"\n{3,}", "\n\n", body_text).strip()
    except Exception as e:
        print(f"  [SKIP] {url} — 本文抽出失敗: {e}")
        return None

    if not body_text:
        print(f"  [SKIP] {url} — 本文が空です")
        return None

    return ScrapedPage(
        url=url,
        title=title,
        body_text=body_text,
        last_modified=_extract_last_modified(resp.text),
    )


def _fetch_sitemap_urls(sitemap_url: str = SITEMAP_URL) -> list[str]:
    resp = SESSION.get(sitemap_url, timeout=30)
    resp.raise_for_status()
    xml_clean = re.sub(r' xmlns="[^"]+"', "", resp.text)
    root = ET.fromstring(xml_clean)
    return [loc.text.strip() for loc in root.iter("loc") if loc.text]


def resolve_category_urls(categories: dict) -> dict[str, list[str]]:
    """category_map.yaml の url_prefixes を実URL群に解決する。

    sitemap.xml を取得し、各URLのパスを category_map の url_prefixes と
    前方一致で照合する。category_map.yaml内で先に出現したカテゴリが優先され、
    どのprefixにもマッチしないURLは "other" に割り当てる。
    """
    all_urls = _fetch_sitemap_urls()

    buckets: dict[str, list[str]] = {name: [] for name in categories}
    buckets.setdefault("other", [])

    for url in all_urls:
        path = url.replace("https://help.zscaler.com", "").lstrip("/")
        matched = None
        for name, cfg in categories.items():
            prefixes = cfg.get("url_prefixes") or []
            if any(path == p or path.startswith(p + "/") for p in prefixes):
                matched = name
                break
        buckets.setdefault(matched or "other", []).append(url)

    return {name: urls for name, urls in buckets.items() if urls}
