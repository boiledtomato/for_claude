"""
カテゴリ内ページの集約 → Markdown/プレーンテキスト変換

scraper.ScrapedPage のリストを1つのドキュメント本文にまとめる。
NotebookLMの1ソースあたりの上限(目安: 50万語)を超えないことを
チェックする関数もここに置く。
"""

from __future__ import annotations

# NotebookLMの1ソースあたりの上限（目安: 50万語）
MAX_WORDS_PER_SOURCE = 500_000


def build_category_document(display_name: str, pages: list) -> str:
    """カテゴリ内の全ページを1つのMarkdown文字列に集約する。"""
    lines = [f"# {display_name}", "", f"（{len(pages)} ページを収録）", ""]

    for page in pages:
        lines.append("---")
        lines.append("")
        lines.append(f"## {page.title or page.url}")
        lines.append(f"URL: {page.url}")
        if page.last_modified:
            lines.append(f"Last-Modified: {page.last_modified}")
        lines.append("")
        lines.append(page.body_text)
        lines.append("")

    return "\n".join(lines)


def _word_count(content: str) -> int:
    # NotebookLMの上限は英語の語数ベースの目安のため、空白区切りで概算する
    # （日本語のような非分かち書き言語では文字数より粗い近似になる点に留意）。
    return len(content.split())


def check_length_limit(category: str, content: str) -> None:
    """content の語数が MAX_WORDS_PER_SOURCE を超えていないか検証する。

    超過している場合は ValueError を送出する。
    """
    word_count = _word_count(content)
    if word_count > MAX_WORDS_PER_SOURCE:
        raise ValueError(
            f"{category}: 語数が上限を超えています "
            f"({word_count:,} > {MAX_WORDS_PER_SOURCE:,})。"
            "category_map.yaml でカテゴリをより細かく分割してください。"
        )
