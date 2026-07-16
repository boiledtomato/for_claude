"""
Zscaler Help Center ページスクレイパー

単一URLを取得し、メインコンテンツ(title, body_text)を抽出する。
readability-lxml でナビゲーション/フッター等を除去し、本文のみを残す。
robots.txt と crawl-delay を尊重し、リクエスト間にレート制限を挟む。

TODO(次のステップで実装):
    - robots.txt の取得・パース (urllib.robotparser) と crawl-delay の反映
    - readability-lxml による本文抽出
    - last_modified 疑似情報の推定 (更新日表記のスクレイピング等)
    - リトライ/バックオフ

使い方 (実装後):
    pip install requests readability-lxml
"""

from __future__ import annotations

from dataclasses import dataclass

DEFAULT_TIMEOUT = 20
DEFAULT_DELAY = 1.0
USER_AGENT = "Mozilla/5.0 (compatible; ZscalerHelpSync/1.0; +https://github.com/boiledtomato/for_claude)"


@dataclass
class ScrapedPage:
    url: str
    title: str
    body_text: str
    last_modified: str | None  # 疑似情報。取得できない場合は None


def is_allowed_by_robots(url: str) -> bool:
    """robots.txt に照らして取得可否を判定する。"""
    raise NotImplementedError


def crawl_delay_for(url: str) -> float:
    """robots.txt の Crawl-delay を返す。指定がなければ DEFAULT_DELAY。"""
    raise NotImplementedError


def scrape_page(url: str) -> ScrapedPage | None:
    """1URLを取得し ScrapedPage を返す。取得/抽出に失敗した場合は None。"""
    raise NotImplementedError


def resolve_category_urls(categories: dict) -> dict[str, list[str]]:
    """category_map.yaml の url_prefixes を実URL群に解決する。

    sitemap.xml (https://help.zscaler.com/sitemap.xml) を取得し、
    各URLのパスを category_map の url_prefixes と前方一致で照合する。
    どのprefixにもマッチしないURLは "other" に割り当てる。
    """
    raise NotImplementedError
