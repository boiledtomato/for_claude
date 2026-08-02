#!/usr/bin/env python3
"""data/philosophers.json の全URLを NotebookLM のノートブックにソースとして登録する。

哲学者リソース集は「リンク集」なので、Markdown をアップロードするのではなく
リンク先そのものをウェブソースとして NotebookLM に読ませる。

  python scripts/sync_philosopher_notebooklm.py --dry-run   # 追加予定を表示するだけ
  python scripts/sync_philosopher_notebooklm.py             # 実際に登録
  python scripts/sync_philosopher_notebooklm.py --eras 01_紀元前 02_1-5世紀_古代ローマ
  python scripts/sync_philosopher_notebooklm.py --write-url-list  # 手動貼り付け用リスト出力

認証は sync_notebooklm.py と同じ Playwright の storage_state を使う。
NOTEBOOKLM_STORAGE_STATE にパスを設定するか、ローカルで `notebooklm login` 済みであること。

**ソース数の上限に注意** — 無料アカウントは1ノートブックあたり50ソース。
全127件を1つに入れるには Pro が必要で、無料なら --eras で時代ごとに
ノートブックを分ける（--notebook-title も一緒に変える）。
"""

from __future__ import annotations

import argparse
import asyncio
import json
import os
import sys
from datetime import datetime, timezone
from pathlib import Path
from typing import Any

ROOT = Path(__file__).resolve().parent.parent
DATA_FILE = ROOT / "data" / "philosophers.json"
STATE_FILE = ROOT / "data" / "philosopher_notebooklm_sync_state.json"
URL_LIST_FILE = ROOT / "data" / "philosopher_urls.txt"

DEFAULT_TITLE = "哲学者リソース"
ADD_DELAY = 1.0  # 連続投入で弾かれないための間隔（秒）


def load_data() -> dict[str, Any]:
    return json.loads(DATA_FILE.read_text(encoding="utf-8"))


def collect_urls(data: dict[str, Any], eras: set[str] | None) -> list[dict[str, str]]:
    """URLを重複なしで集める。同じPDFを複数の哲学者が参照する場合は先勝ち。"""
    seen: set[str] = set()
    out: list[dict[str, str]] = []
    for era in data["eras"]:
        if eras and era["id"] not in eras:
            continue
        for p in era["philosophers"]:
            for l in p["links"]:
                if l["url"] in seen:
                    continue
                seen.add(l["url"])
                out.append(
                    {
                        "url": l["url"],
                        "title": l["title"],
                        "philosopher": p["name_ja"],
                        "era": era["id"],
                        "kind": l["kind"],
                    }
                )
    return out


def write_url_list(entries: list[dict[str, str]]) -> None:
    """NotebookLM の「ウェブサイト」ソース追加欄にまとめて貼れる形式で書き出す。"""
    lines = [
        "# NotebookLM 手動投入用のURL一覧",
        "# NotebookLM →「ソースを追加」→「ウェブサイト」に、# 以外の行をまとめて貼り付ける",
        f"# 生成: {datetime.now(timezone.utc).strftime('%Y-%m-%d')} / {len(entries)} 件",
        "",
    ]
    current_era = None
    for e in entries:
        if e["era"] != current_era:
            current_era = e["era"]
            lines.append(f"\n# ── {current_era} ──")
        lines.append(e["url"])
    URL_LIST_FILE.write_text("\n".join(lines) + "\n", encoding="utf-8")
    print(f"{len(entries)} 件のURLを {URL_LIST_FILE.relative_to(ROOT)} に書き出しました")


def load_state(path: Path) -> dict[str, Any]:
    if path.exists():
        try:
            return json.loads(path.read_text(encoding="utf-8"))
        except Exception as exc:  # 壊れていても実行は続ける
            print(f"[SKIP] 状態ファイルを読めませんでした: {exc}")
    return {}


def save_state(path: Path, state: dict[str, Any]) -> None:
    path.write_text(
        json.dumps(state, ensure_ascii=False, indent=2) + "\n", encoding="utf-8"
    )


def check_auth_available(storage_path: str | None) -> bool:
    from notebooklm.paths import get_storage_path

    p = Path(storage_path) if storage_path else get_storage_path()
    if p.exists():
        return True
    print(
        f"[ERROR] NotebookLM の認証情報が見つかりません: {p}\n"
        f"  ローカル: `notebooklm login`（または `notebooklm login --browser-cookies chrome`）\n"
        f"  CI      : Secret から storage_state.json を書き出し、そのパスを\n"
        f"            環境変数 NOTEBOOKLM_STORAGE_STATE に設定してください。",
        file=sys.stderr,
    )
    return False


async def resolve_notebook(client, title: str, state: dict, dry_run: bool):
    known_id = (state.get("notebook") or {}).get("id")
    if known_id:
        nb = await client.notebooks.get_or_none(known_id)
        if nb is not None:
            print(f"[notebook] 既存を使用: {nb.title} ({nb.id})")
            return nb

    for nb in await client.notebooks.list():
        if nb.title == title:
            print(f"[notebook] タイトル一致で発見: {nb.title} ({nb.id})")
            return nb

    if dry_run:
        print(f"[notebook] (dry-run) 新規作成する: {title}")
        return None

    nb = await client.notebooks.create(title)
    print(f"[notebook] 新規作成: {nb.title} ({nb.id})")
    return nb


async def sync(args) -> int:
    from notebooklm import NotebookLMClient

    data = load_data()
    entries = collect_urls(data, set(args.eras) if args.eras else None)
    if args.limit:
        entries = entries[: args.limit]
    print(f"投入対象: {len(entries)} 件のURL")

    state_file = Path(args.state_file)
    state = load_state(state_file)

    storage_path = os.environ.get("NOTEBOOKLM_STORAGE_STATE")
    if not check_auth_available(storage_path):
        return 1

    async with NotebookLMClient.from_storage(path=storage_path) as client:
        limit_hint = None
        try:
            tier = await client.settings.get_account_tier()
            limits = await client.settings.get_account_limits()
            limit_hint = getattr(limits, "sources_per_notebook", None)
            print(f"[account] tier={tier} limits={limits}")
        except Exception as exc:
            print(f"[account] 上限情報は取得できませんでした: {exc}")

        if limit_hint and len(entries) > limit_hint:
            print(
                f"[WARN] ソース数 {len(entries)} がノートブックの上限 {limit_hint} を超えています。"
                f"--eras で時代ごとに分けるか、Pro へのアップグレードが必要です。"
            )

        nb = await resolve_notebook(client, args.notebook_title, state, args.dry_run)
        if nb is None:
            for e in entries:
                print(f"  [ADD] {e['philosopher']} / {e['title']}\n        {e['url']}")
            print(f"\n(dry-run) 追加 {len(entries)} 件")
            return 0

        remote = await client.sources.list(nb.id)
        existing_urls = {getattr(s, "url", None) for s in remote}
        print(f"[sources] 既存ソース: {len(remote)} 件")

        added = skipped = 0
        failures: list[str] = []
        recorded: dict[str, Any] = state.get("sources") or {}

        for e in entries:
            if e["url"] in existing_urls:
                skipped += 1
                continue
            if args.dry_run:
                print(f"  [ADD] {e['philosopher']} / {e['title']}")
                added += 1
                continue
            try:
                src = await client.sources.add_url(nb.id, e["url"], wait=False)
                recorded[e["url"]] = {
                    "source_id": getattr(src, "id", None),
                    "title": e["title"],
                    "philosopher": e["philosopher"],
                    "era": e["era"],
                }
                added += 1
                print(f"  [ADD] {e['philosopher']} / {e['title']}")
            except Exception as exc:  # 1件の失敗で全体を止めない
                failures.append(f"{e['url']} — {exc}")
                print(f"  [SKIP] {e['url']}\n         {exc}")
            await asyncio.sleep(ADD_DELAY)

        if not args.dry_run:
            state["notebook"] = {"id": nb.id, "title": nb.title}
            state["sources"] = recorded
            state["synced_at"] = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
            save_state(state_file, state)

        print(f"\n追加 {added} / 既存でスキップ {skipped} / 失敗 {len(failures)}")
        if failures:
            print("失敗した URL:")
            for f in failures:
                print(f"  - {f}")
        return 0


def main() -> int:
    ap = argparse.ArgumentParser(description=__doc__)
    ap.add_argument("--notebook-title", default=os.environ.get(
        "PHILOSOPHER_NOTEBOOK_TITLE", DEFAULT_TITLE))
    ap.add_argument("--state-file", default=str(STATE_FILE))
    ap.add_argument("--eras", nargs="*", help="対象の時代フォルダID（例: 01_紀元前）")
    ap.add_argument("--limit", type=int, help="先頭N件だけ投入（動作確認用）")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--write-url-list", action="store_true",
                    help="手動貼り付け用のURL一覧を書き出して終了する")
    args = ap.parse_args()

    if args.write_url_list:
        data = load_data()
        write_url_list(collect_urls(data, set(args.eras) if args.eras else None))
        return 0

    return asyncio.run(sync(args))


if __name__ == "__main__":
    sys.exit(main())
