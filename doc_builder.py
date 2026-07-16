"""
カテゴリ内ページの集約 → Markdown/プレーンテキスト変換

scraper.ScrapedPage のリストを1つのドキュメント本文にまとめる。
NotebookLMの1ソースあたりの上限(50万語)を超えないことをチェックする
関数もここに置く。

TODO(次のステップで実装):
    - build_category_document(): 見出し(URL/タイトル)付きで本文を連結
    - check_length_limit(): 語数(または文字数)が上限を超えたら
      ValueError等で明示的に失敗させる (カテゴリ分割は将来対応)
"""

from __future__ import annotations

# NotebookLMの1ソースあたりの上限（目安: 50万語）
MAX_WORDS_PER_SOURCE = 500_000


def build_category_document(display_name: str, pages: list) -> str:
    """カテゴリ内の全ページを1つのMarkdown文字列に集約する。"""
    raise NotImplementedError


def check_length_limit(category: str, content: str) -> None:
    """content の語数が MAX_WORDS_PER_SOURCE を超えていないか検証する。

    超過している場合は ValueError を送出する。
    """
    raise NotImplementedError
