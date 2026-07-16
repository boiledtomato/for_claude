"""
Zscaler Help Center → NotebookLM (Google Docs 経由) 週次同期パイプライン

help.zscaler.com 配下のページをカテゴリ単位でスクレイピングし、
前回実行時からコンテンツに変更のあったカテゴリのみ Google ドキュメントを
再生成・更新する。NotebookLM 側は Google Drive の自動同期機能で
このドキュメント群を読み込む想定のため、本スクリプトは Drive への
書き込みまでを担当する。

処理フロー:
    1. config/category_map.yaml を読み込み、カテゴリ定義を確定
    2. scraper.py でカテゴリごとの対象URLを解決し、各ページを取得
    3. state_store.py で前回ハッシュと比較し、変更のあったカテゴリを特定
    4. 変更のあったカテゴリのみ doc_builder.py で本文を集約・上限チェック
    5. gdocs_writer.py で Google ドキュメントを作成/更新
    6. changelog.md に変更ログを追記

使い方:
    pip install pyyaml requests readability-lxml \
                google-api-python-client google-auth google-auth-httplib2
    python main.py                   # 全カテゴリを同期
    python main.py --categories zia zpa   # 特定カテゴリのみ
    python main.py --force           # ハッシュ比較を無視して強制再生成

注意:
    scraper.py / state_store.py / doc_builder.py / gdocs_writer.py は
    現時点では骨組み(関数シグネチャのみ)であり、実行すると
    NotImplementedError で停止する。各モジュールを後続タスクで実装する。
"""

from __future__ import annotations

import argparse
import sys
from datetime import datetime, timezone
from pathlib import Path

import yaml

import doc_builder
import gdocs_writer
import scraper
import state_store

CATEGORY_MAP_PATH = Path("config/category_map.yaml")
CHANGELOG_PATH = Path("changelog.md")
MAX_CATEGORIES = 100


def load_category_map(path: Path = CATEGORY_MAP_PATH) -> dict:
    """category_map.yaml を読み込み、カテゴリ名→設定のdictを返す。"""
    with path.open(encoding="utf-8") as f:
        data = yaml.safe_load(f) or {}
    categories = data.get("categories", {})
    if len(categories) > MAX_CATEGORIES:
        raise ValueError(
            f"カテゴリ数が上限({MAX_CATEGORIES})を超えています: {len(categories)}"
        )
    return categories


def sync_category(name: str, display_name: str, urls: list[str], force: bool) -> bool:
    """1カテゴリ分の同期処理。ドキュメントを更新した場合は True を返す。"""
    pages = [p for p in (scraper.scrape_page(url) for url in urls) if p is not None]
    if not pages:
        print(f"[skip] {name}: 取得できたページがありません")
        return False

    if not force and not state_store.has_changed(name, pages):
        print(f"[skip] {name}: 変更なし")
        return False

    content = doc_builder.build_category_document(display_name, pages)
    doc_builder.check_length_limit(name, content)

    doc_id = gdocs_writer.upsert_document(name, display_name, content)

    state_store.update_state(name, pages)
    append_changelog(name, display_name, doc_id, len(pages))
    print(f"[update] {name}: {len(pages)} ページを反映 (doc_id={doc_id})")
    return True


def append_changelog(category: str, display_name: str, doc_id: str, page_count: int) -> None:
    """changelog.md に1行追記する。"""
    timestamp = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    line = (
        f"- {timestamp} — **{display_name}** ({category}) を更新 "
        f"— {page_count} ページ — doc_id: `{doc_id}`\n"
    )
    with CHANGELOG_PATH.open("a", encoding="utf-8") as f:
        f.write(line)


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Zscaler Help Center → NotebookLM Google Docs 同期パイプライン"
    )
    parser.add_argument(
        "--categories", nargs="*",
        help="対象カテゴリ名を絞り込む（省略時は category_map.yaml の全カテゴリ）",
    )
    parser.add_argument(
        "--force", action="store_true",
        help="ハッシュ比較を無視し、全対象カテゴリを強制的に再生成する",
    )
    return parser.parse_args()


def main() -> None:
    args = parse_args()

    try:
        categories = load_category_map()
    except (FileNotFoundError, ValueError) as e:
        print(f"[ERROR] category_map.yaml の読み込みに失敗しました: {e}", file=sys.stderr)
        sys.exit(1)

    if args.categories:
        categories = {k: v for k, v in categories.items() if k in args.categories}
        if not categories:
            print("[ERROR] 指定されたカテゴリが category_map.yaml に見つかりません", file=sys.stderr)
            sys.exit(1)

    print(f"対象カテゴリ: {list(categories.keys())}")

    category_urls = scraper.resolve_category_urls(categories)

    updated = 0
    failed = 0
    for name, cfg in categories.items():
        urls = category_urls.get(name, [])
        if not urls:
            print(f"[skip] {name}: 対象URLなし")
            continue
        try:
            if sync_category(name, cfg["display_name"], urls, args.force):
                updated += 1
        except Exception as e:
            failed += 1
            print(f"[ERROR] {name}: 同期失敗 — {e}", file=sys.stderr)

    print(f"\n完了: {updated}/{len(categories)} カテゴリを更新（失敗: {failed}）")
    if failed:
        sys.exit(1)


if __name__ == "__main__":
    main()
