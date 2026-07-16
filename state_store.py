"""
コンテンツハッシュの永続化・差分検知

各URLの本文ハッシュ(sha256)を state.json に保存し、
前回実行時のハッシュと比較してカテゴリ単位の変更有無を判定する。

state.json の想定シェイプ:
    {
      "categories": {
        "<category_name>": {
          "urls": {
            "<url>": "<sha256 hex>",
            ...
          },
          "last_synced": "2026-07-16T00:00:00Z"
        }
      }
    }

TODO(次のステップで実装):
    - state.json の読み込み/書き込み (存在しない場合は空状態から開始)
    - has_changed(): カテゴリ内の全URLハッシュを前回分と比較
      (URL追加/削除も変更とみなす)
    - update_state(): 新しいハッシュ群で state.json を更新
"""

from __future__ import annotations

import hashlib
from pathlib import Path

STATE_PATH = Path("state.json")


def _hash_body(body_text: str) -> str:
    return hashlib.sha256(body_text.encode("utf-8")).hexdigest()


def load_state(path: Path = STATE_PATH) -> dict:
    """state.json を読み込む。存在しない場合は空状態を返す。"""
    raise NotImplementedError


def save_state(state: dict, path: Path = STATE_PATH) -> None:
    """state.json に書き込む。"""
    raise NotImplementedError


def has_changed(category: str, pages: list) -> bool:
    """カテゴリ内のページ群が前回ハッシュと異なるか判定する。"""
    raise NotImplementedError


def update_state(category: str, pages: list) -> None:
    """カテゴリのハッシュ群を state.json に反映する。"""
    raise NotImplementedError
