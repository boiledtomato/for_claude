"""
Zscaler Help Center ページスクレイパー

help.zscaler.com はJavaScriptレンダリングのSPAのため、生HTMLを直接
取得しても本文が得られない（"please enable JS" のプレースホルダーのみ）。
そのため Playwright + headless Chromium でレンダリングしたHTMLを取得し、
readability-lxml でナビゲーション/フッター等を除去して本文のみを抽出する。
（本リポジトリの scripts/zscaler_monitor.py と同じ、この対象サイトに対して
実績のあるレンダリング方式）

カテゴリによっては対象URLが数百件に及ぶため、1ページずつ直列に処理すると
現実的な時間で終わらない（実測: ZIA単体862ページ、直列で約1〜1.5時間）。
そのため playwright.async_api + asyncio で複数ページを並列レンダリングする。
crawl-delayは「リクエスト開始間隔」としてグローバルに1本のディスパッチャで
尊重し、並列数(MAX_CONCURRENCY)はレンダリング待ち時間の重なりを利用して
スループットを上げる目的にとどめる（サーバーへの同時接続数の目安として
妥当な範囲に抑える）。

使い方:
    pip install playwright requests readability-lxml lxml
    playwright install chromium
"""

from __future__ import annotations

import asyncio
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

# ページ読み込み後、SPAのハイドレーションが落ち着くまでの追加待機時間(ms)
RENDER_SETTLE_MS = 2000
PAGE_GOTO_TIMEOUT_MS = 30_000

# 同時にレンダリングするページ数の上限（サーバー負荷とスループットのバランス）
MAX_CONCURRENCY = 5

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
    """レンダリング済みHTML中の更新日時らしき値を推定する（疑似情報）。"""
    for pattern in _LAST_MODIFIED_PATTERNS:
        m = pattern.search(html_text)
        if m:
            return m.group(1)
    return None


def _extract_page(url: str, html: str, header_last_modified: str | None) -> ScrapedPage | None:
    """レンダリング済みHTMLから本文を抽出する（同期・CPU処理）。"""
    try:
        doc = Document(html)
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
        last_modified=header_last_modified or _extract_last_modified(html),
    )


class _RateLimiter:
    """複数ワーカーが共有する、リクエスト開始間隔を保証するディスパッチャ。"""

    def __init__(self, delay: float) -> None:
        self._delay = delay
        self._lock = asyncio.Lock()
        self._last_dispatch = 0.0

    async def wait_turn(self) -> None:
        async with self._lock:
            loop = asyncio.get_event_loop()
            now = loop.time()
            wait = self._last_dispatch + self._delay - now
            if wait > 0:
                await asyncio.sleep(wait)
            self._last_dispatch = loop.time()


async def _scrape_one(context, url: str, limiter: _RateLimiter, semaphore: asyncio.Semaphore) -> ScrapedPage | None:
    if not is_allowed_by_robots(url):
        print(f"  [SKIP] {url} — robots.txtにより許可されていません")
        return None

    async with semaphore:
        await limiter.wait_turn()

        page = await context.new_page()
        try:
            response = await page.goto(url, wait_until="networkidle", timeout=PAGE_GOTO_TIMEOUT_MS)
            await page.wait_for_timeout(RENDER_SETTLE_MS)
            html = await page.content()
            header_last_modified = response.headers.get("last-modified") if response else None
        except Exception as e:
            print(f"  [SKIP] {url} — レンダリング失敗: {e}")
            return None
        finally:
            await page.close()

    return _extract_page(url, html, header_last_modified)


async def _scrape_pages_async(urls: list[str]) -> list[ScrapedPage]:
    from playwright.async_api import async_playwright

    if not urls:
        return []

    delay = crawl_delay_for(urls[0])
    limiter = _RateLimiter(delay)
    semaphore = asyncio.Semaphore(MAX_CONCURRENCY)

    async with async_playwright() as p:
        browser = await p.chromium.launch(args=["--no-sandbox"])
        try:
            context = await browser.new_context()
            tasks = [_scrape_one(context, url, limiter, semaphore) for url in urls]
            results = await asyncio.gather(*tasks)
        finally:
            await browser.close()

    return [r for r in results if r is not None]


def scrape_pages(urls: list[str]) -> list[ScrapedPage]:
    """複数URLを並列にスクレイピングする（メインのエントリポイント）。

    クロールの礼儀としてリクエスト開始間隔は crawl-delay に従うが、
    レンダリング待ち時間はMAX_CONCURRENCY件まで並列で重ねることで
    直列処理よりスループットを上げる。
    """
    return asyncio.run(_scrape_pages_async(urls))


def scrape_page(url: str) -> ScrapedPage | None:
    """1URLだけを取得する簡易版（テスト・デバッグ用途）。"""
    pages = scrape_pages([url])
    return pages[0] if pages else None


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
