"""
コンテンツハッシュの永続化・差分検知

各URLの本文ハッシュ(sha256)を state.json に保存し、
前回実行時のハッシュと比較してカテゴリ単位の変更有無を判定する。
生成済みGoogleドキュメントのIDもここに永続化する
(category_map.yamlはコメント付きの人手管理ファイルのため、
 パイプラインからの自動書き戻しは行わない)。

state.json の想定シェイプ:
    {
      "categories": {
        "<category_name>": {
          "urls": {
            "<url>": "<sha256 hex>",
            ...
          },
          "google_doc_id": "<Google DocsのdocumentId>",
          "last_synced": "2026-07-16T00:00:00Z"
        }
      }
    }
"""

from __future__ import annotations

import hashlib
import json
from datetime import datetime, timezone
from pathlib import Path

STATE_PATH = Path("state.json")


def _hash_body(body_text: str) -> str:
    return hashlib.sha256(body_text.encode("utf-8")).hexdigest()


def _page_hashes(pages: list) -> dict[str, str]:
    return {page.url: _hash_body(page.body_text) for page in pages}


def load_state(path: Path = STATE_PATH) -> dict:
    """state.json を読み込む。存在しない場合は空状態を返す。"""
    if not path.exists():
        return {"categories": {}}
    with path.open(encoding="utf-8") as f:
        data = json.load(f)
    data.setdefault("categories", {})
    return data


def save_state(state: dict, path: Path = STATE_PATH) -> None:
    """state.json に書き込む。"""
    with path.open("w", encoding="utf-8") as f:
        json.dump(state, f, ensure_ascii=False, indent=2, sort_keys=True)
        f.write("\n")


def has_changed(category: str, pages: list, path: Path = STATE_PATH) -> bool:
    """カテゴリ内のページ群が前回ハッシュと異なるか判定する。

    URLの追加・削除、および本文の変更のいずれも変更ありとみなす。
    """
    state = load_state(path)
    previous = state["categories"].get(category, {}).get("urls", {})
    current = _page_hashes(pages)
    return previous != current


def get_doc_id(category: str, path: Path = STATE_PATH) -> str | None:
    """カテゴリに紐づく既存のGoogleドキュメントIDを返す。未作成ならNone。"""
    state = load_state(path)
    return state["categories"].get(category, {}).get("google_doc_id")


def update_state(
    category: str,
    pages: list,
    doc_id: str | None = None,
    path: Path = STATE_PATH,
) -> None:
    """カテゴリのハッシュ群(と任意でdoc_id)を state.json に反映する。"""
    state = load_state(path)
    entry = state["categories"].setdefault(category, {})
    entry["urls"] = _page_hashes(pages)
    entry["last_synced"] = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    if doc_id:
        entry["google_doc_id"] = doc_id
    save_state(state, path)
