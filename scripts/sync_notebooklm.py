"""
notebooklm_docs/ の Markdown を NotebookLM のノートブックへ同期するスクリプト

方針: **ノートブックは1つ固定**し、**内容が変わったファイルのソースだけ差し替える**。
毎回新しいノートブックを作らないので、チャット履歴・保存メモ・カスタム指示が
残り、アップロード量も変更分だけで済む。

NotebookLM に公開 API はないため、非公式ライブラリ notebooklm-py
(https://github.com/teng-lin/notebooklm-py) を使う。これは Google の
**非公開内部 API** を叩くので、Google 側の変更で予告なく壊り得る。壊れた場合は
`notebooklm_docs/` の該当ファイルを手でアップロードすれば復旧できる。

使い方:
    pip install notebooklm-py

    # 初回: ローカルでブラウザログインし、認証情報を作る
    notebooklm login                       # または notebooklm login --browser-cookies chrome

    # 同期（何が起きるかだけ見る）
    python scripts/sync_notebooklm.py --dry-run

    # 同期実行
    python scripts/sync_notebooklm.py

環境変数:
    NOTEBOOKLM_STORAGE_STATE  storage_state.json のパス。CI では Secret から
                              書き出したファイルを指す。未設定ならライブラリ既定の
                              プロファイル (~/.notebooklm/...) を使う。
    NOTEBOOKLM_NOTEBOOK_TITLE 同期先ノートブックのタイトル (既定: Zscaler_help_docs)

状態ファイル:
    data/notebooklm_sync_state.json
        ノートブックIDと、ファイルごとの sha256 / source_id を記録する。
        次回はこのハッシュと比較して、変わったファイルだけを差し替える。

追記モード (--mode append):
    週次リリースノートのように「毎回新しいファイルが増えていく」ドキュメント用。
    ローカルに無いファイルのソースは消さず (過去の週は残す)、ソース数が
    --max-sources を超えるときだけ、このスクリプトが登録したソースを
    古い順 (added_at) に削除して空きを作る。

    python scripts/sync_notebooklm.py --mode append \
        --docs-dir output/release_digest/2026-10-02 --glob "*.md" \
        --state-file data/release_notes_notebooklm_sync_state.json \
        --notebook-title Zscaler_release_notes --max-sources 90
"""

import argparse
import asyncio
import fnmatch
import hashlib
import json
import os
import re
import sys
from datetime import datetime, timezone
from pathlib import Path

# 既定は help.zscaler.com のドキュメントセット。--docs-dir / --state-file で
# 別のドキュメントセット（例: community_docs → 別ノートブック）にも向けられる。
# 状態ファイルはドキュメントセットごとに必ず分けること。共有すると、片方の実行が
# もう片方のソースを「ローカルに存在しない＝削除対象」と判定してしまう。
DOCS_DIR = Path("notebooklm_docs")
STATE_FILE = Path("data/notebooklm_sync_state.json")
DEFAULT_NOTEBOOK_TITLE = "Zscaler_help_docs"
DOCS_GLOB = "*/*_part*.md"
DEFAULT_MAX_SOURCES = 90
# アカウントの上限が取れたときに残しておく余白 (手動追加用)
LIMIT_HEADROOM = 10


# ── ローカル側 ────────────────────────────────────────────────────────────────

def sha256_file(path: Path) -> str:
    h = hashlib.sha256()
    with path.open("rb") as f:
        for chunk in iter(lambda: f.read(1 << 20), b""):
            h.update(chunk)
    return h.hexdigest()


def collect_local(only: set[str] | None) -> dict[str, dict]:
    """{ファイル名: {path, rel, sha256}} を返す。ファイル名がソース名になる。"""
    found: dict[str, dict] = {}
    for path in sorted(DOCS_DIR.glob(DOCS_GLOB)):
        category = path.parent.name
        if only and category not in only:
            continue
        found[path.name] = {
            "path": path,
            "rel": str(path.relative_to(DOCS_DIR)),
            "category": category,
            "sha256": sha256_file(path),
        }
    return found


def load_state() -> dict:
    if STATE_FILE.exists():
        try:
            return json.loads(STATE_FILE.read_text(encoding="utf-8"))
        except json.JSONDecodeError:
            print("[state] 破損しているため作り直します")
    return {"synced_at": "", "notebook": {}, "sources": {}}


def save_state(state: dict) -> None:
    STATE_FILE.parent.mkdir(parents=True, exist_ok=True)
    state["synced_at"] = datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")
    STATE_FILE.write_text(
        json.dumps(state, ensure_ascii=False, indent=1, sort_keys=True) + "\n",
        encoding="utf-8",
    )


# ── NotebookLM 側 ────────────────────────────────────────────────────────────

def source_key(title: str) -> str:
    """ソースのタイトルからローカルのファイル名に対応するキーを作る。

    add_file はファイル名をそのままタイトルにするが、拡張子の扱いが変わっても
    追随できるよう `.md` の有無を吸収する。
    """
    title = (title or "").strip()
    return title if title.endswith(".md") else f"{title}.md"


def utc_now() -> str:
    return datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")


def plan_prune(recorded: dict[str, dict], remote_by_name: dict[str, object],
               local: dict[str, dict], max_sources: int) -> list[str]:
    """追記モードで、ソース数を max_sources 以下に保つために消すソース名を返す。

    数えるのはノートブック上の全ソース (手動追加分も含む) だが、消すのは
    このスクリプトが登録した記録のあるものだけ。今回アップロードするファイルは
    対象にしない。古さはファイル名の日付 (YYYYMMDD)、無ければ added_at (初回登録日時) で決める。
    """
    incoming = [n for n in local if n not in remote_by_name]
    excess = len(remote_by_name) + len(incoming) - max_sources
    if excess <= 0:
        return []
    def age(name: str) -> tuple[str, str, str]:
        # ファイル名に週の日付 (zia_20261002.md) があればそれを優先する。
        # 1回の実行で登録したソースは added_at が同秒になり、名前順で並ぶと
        # 新しい週の zcc_ が古い週の zia_ より先に消えてしまうため。
        m = re.search(r"(\d{8})", name)
        added = recorded[name].get("added_at") or ""
        return (m.group(1) if m else added[:10].replace("-", ""), added, name)

    candidates = sorted(
        (n for n in recorded if n in remote_by_name and n not in local), key=age)
    return candidates[:excess]


def check_auth_available(storage_path: str | None) -> bool:
    """認証ファイルの有無を先に確かめ、無ければ手順を示して落とす。"""
    from notebooklm.paths import get_storage_path

    path = Path(storage_path) if storage_path else get_storage_path()
    if path.exists():
        return True

    print(
        f"[ERROR] NotebookLM の認証情報が見つかりません: {path}\n"
        f"  ローカル: `notebooklm login`（または `notebooklm login --browser-cookies chrome`）\n"
        f"  CI      : Secret から storage_state.json を書き出し、そのパスを\n"
        f"            環境変数 NOTEBOOKLM_STORAGE_STATE に設定してください。",
        file=sys.stderr,
    )
    return False


async def resolve_notebook(client, title: str, state: dict, dry_run: bool):
    """固定ノートブックを取得する。無ければ作る。"""
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

    local = collect_local(set(args.only) if args.only else None)
    if not local and args.mode == "append":
        # 追記モードは「今週は更新なし」でファイルが無いのが正常
        print(f"[append] {DOCS_DIR} に追加するファイルはありません")
        return 0
    if not local:
        print(f"[ERROR] {DOCS_DIR} に *_part*.md が見つかりません。"
              f"先にビルドスクリプト (build_help_docs.py / build_community_docs.py) "
              f"を実行してください。", file=sys.stderr)
        return 1

    print(f"ローカルの対象ファイル: {len(local)} 件 (mode={args.mode})")
    state = load_state()
    recorded: dict[str, dict] = state.get("sources") or {}
    title = args.notebook_title

    storage_path = os.environ.get("NOTEBOOKLM_STORAGE_STATE")
    if not check_auth_available(storage_path):
        return 1

    async with NotebookLMClient.from_storage(path=storage_path) as client:
        max_sources = args.max_sources
        try:
            # notebooklm-py 0.8 に get_account_tier は無い。tier は limits に含まれる
            limits = await client.settings.get_account_limits()
            print(f"[account] tier={getattr(limits, 'tier', None)} "
                  f"source_limit={getattr(limits, 'source_limit', None)}")
            source_limit = getattr(limits, "source_limit", None)
            # プランの上限が指定値より厳しければ、上限から余白を引いた値に下げる
            if source_limit and source_limit - LIMIT_HEADROOM < max_sources:
                max_sources = max(1, source_limit - LIMIT_HEADROOM)
                print(f"[account] ソース上限 {source_limit} に合わせ、"
                      f"保持数を {max_sources} に下げます")
        except Exception as e:
            print(f"[account] 上限情報は取得できませんでした: {e}")

        nb = await resolve_notebook(client, title, state, args.dry_run)
        if nb is None:
            # dry-run でノートブック未作成 — 全ファイルが新規追加対象
            for name in sorted(local):
                print(f"  [ADD] {local[name]['rel']}")
            print(f"\n(dry-run) 追加 {len(local)} / 更新 0 / 削除 0")
            return 0

        remote = await client.sources.list(nb.id)
        remote_by_name: dict[str, object] = {}
        for src in remote:
            remote_by_name[source_key(src.title)] = src
        print(f"[sources] ノートブック側の既存ソース: {len(remote)} 件")

        added = updated = deleted = skipped = 0
        failures: list[str] = []

        if args.mode == "append":
            # ノートブック側で手動削除されたソースの記録は捨てる (記録が膨らむだけ)
            for name in [n for n in recorded if n not in remote_by_name and n not in local]:
                recorded.pop(name)

            # --glob に合わなくなった登録済みソース (ファイル名の付け方を変える前の
            # もの) は、もう更新されない旧形式なので削除する。手動追加分は対象外。
            stale = sorted(n for n in recorded
                           if n in remote_by_name and n not in local
                           and not fnmatch.fnmatch(n, Path(DOCS_GLOB).name))
            for name in stale:
                print(f"  [DELETE] {name} (旧形式: --glob {DOCS_GLOB} に一致しない)")
                if args.dry_run:
                    deleted += 1
                    continue
                try:
                    await client.sources.delete(nb.id, remote_by_name[name].id)  # type: ignore[attr-defined]
                    recorded.pop(name, None)
                    remote_by_name.pop(name, None)
                    deleted += 1
                except Exception as e:
                    print(f"    [FAIL] {name} の削除 — {e}")
                    failures.append(name)

            # ── 上限に近づいたら、このスクリプトが登録した古いソースから消す ──
            prune = plan_prune(recorded, remote_by_name, local, max_sources)
            incoming = sum(1 for n in local if n not in remote_by_name)
            print(f"[sources] 保持上限 {max_sources} / 既存 {len(remote_by_name)} + 追加 {incoming}"
                  f" → 古い順に {len(prune)} 件削除")
            for name in prune:
                print(f"  [PRUNE] {name} (登録 {recorded[name].get('added_at', '?')})")
                if args.dry_run:
                    deleted += 1
                    continue
                try:
                    await client.sources.delete(nb.id, remote_by_name[name].id)  # type: ignore[attr-defined]
                    recorded.pop(name, None)
                    remote_by_name.pop(name, None)
                    deleted += 1
                except Exception as e:
                    print(f"    [FAIL] {name} の削除 — {e}")
                    failures.append(name)
            # 実行時は削除済みのソースが remote_by_name から外れている
            after = len(remote_by_name) + incoming - (deleted if args.dry_run else 0)
            if after > max_sources:
                print(f"[WARN] 削除できるソースが足りず、上限 {max_sources} を超えます"
                      f" (手動追加のソースは削除しません)")

        # ── ローカルにあるファイルを反映 ──────────────────────────────────
        for name in sorted(local):
            info = local[name]
            src = remote_by_name.get(name)
            prev = recorded.get(name) or {}

            if src is not None and prev.get("sha256") == info["sha256"]:
                skipped += 1
                continue

            action = "UPDATE" if src is not None else "ADD"
            print(f"  [{action}] {info['rel']}")
            if args.dry_run:
                if src is not None:
                    updated += 1
                else:
                    added += 1
                continue

            try:
                if src is not None:
                    await client.sources.delete(nb.id, src.id)
                new_src = await client.sources.add_file(
                    nb.id, info["path"], wait=True, wait_timeout=args.wait_timeout,
                )
                recorded[name] = {
                    "sha256": info["sha256"],
                    "source_id": new_src.id,
                    "rel": info["rel"],
                    # 差し替えても初回登録日時を保つ (追記モードの「古い順」の基準)
                    "added_at": prev.get("added_at") or utc_now(),
                }
                if src is not None:
                    updated += 1
                else:
                    added += 1
            except Exception as e:
                print(f"    [FAIL] {info['rel']} — {e}")
                failures.append(info["rel"])
                # 差し替え途中で失敗した記録は消し、次回やり直せるようにする
                recorded.pop(name, None)

        # ── ローカルから消えた part ファイルのソースを削除 ────────────────
        # 削除するのは「このスクリプトが登録した」と記録にあるソースだけ。
        # 手動で追加されたソースや他用途のソースには触らない（既存のノートブックに
        # 向けても中身を消してしまわないようにするため）。
        # --only 指定時は対象カテゴリしか見ていないので削除自体を行わない。
        # 追記モードでは過去の週のソースを残すので、この削除は行わない。
        if not args.only and args.mode == "mirror":
            foreign: list[str] = []
            for name, src in sorted(remote_by_name.items()):
                if name in local:
                    continue
                if name not in recorded:
                    # 表示は NotebookLM 上の実際のタイトルを使う（name は .md を補ったキー）
                    foreign.append(getattr(src, "title", name))
                    continue
                print(f"  [DELETE] {name} (ローカルに対応ファイルなし)")
                if args.dry_run:
                    deleted += 1
                    continue
                try:
                    await client.sources.delete(nb.id, src.id)  # type: ignore[attr-defined]
                    recorded.pop(name, None)
                    deleted += 1
                except Exception as e:
                    print(f"    [FAIL] {name} の削除 — {e}")
                    failures.append(name)

            if foreign:
                print(f"  [KEEP] このスクリプト管理外のソース {len(foreign)} 件は"
                      f"そのまま残します: {', '.join(foreign[:5])}"
                      f"{' ...' if len(foreign) > 5 else ''}")

        if not args.dry_run:
            state["notebook"] = {"id": nb.id, "title": nb.title}
            state["sources"] = recorded
            save_state(state)

    prefix = "(dry-run) " if args.dry_run else ""
    print(f"\n{prefix}追加 {added} / 更新 {updated} / 削除 {deleted} / 変更なし {skipped}")
    if failures:
        print(f"[WARN] {len(failures)} 件で失敗しました: {', '.join(failures[:10])}")

    write_step_summary(added, updated, deleted, skipped, failures, title, args.dry_run)
    return 1 if failures else 0


def write_step_summary(added, updated, deleted, skipped, failures, title, dry_run) -> None:
    summary_path = os.environ.get("GITHUB_STEP_SUMMARY")
    if not summary_path:
        return
    lines = [
        f"## NotebookLM 同期{' (dry-run)' if dry_run else ''}", "",
        f"- ノートブック: **{title}**",
        f"- 追加: **{added}** / 更新: **{updated}** / 削除: **{deleted}** / 変更なし: {skipped}",
    ]
    if failures:
        lines.append(f"- 失敗: **{len(failures)}**")
        lines.append("")
        lines += [f"  - `{f}`" for f in failures[:20]]
        lines.append("")
        lines.append(f"> 失敗したファイルは `{DOCS_DIR}/` から手動でアップロードできます。")
    with open(summary_path, "a", encoding="utf-8") as f:
        f.write("\n".join(lines) + "\n")


def main() -> int:
    global DOCS_DIR, STATE_FILE, DOCS_GLOB

    ap = argparse.ArgumentParser(
        description="生成済み Markdown を固定ノートブックへ差分同期する")
    ap.add_argument("--notebook-title",
                    default=os.environ.get("NOTEBOOKLM_NOTEBOOK_TITLE",
                                           DEFAULT_NOTEBOOK_TITLE),
                    help=f"同期先ノートブックのタイトル (既定: {DEFAULT_NOTEBOOK_TITLE})")
    ap.add_argument("--only", nargs="*", metavar="CATEGORY",
                    help="指定カテゴリのみ同期する (例: zia api)。"
                         "指定時は不要ソースの削除を行わない")
    ap.add_argument("--dry-run", action="store_true",
                    help="実際には変更せず、追加/更新/削除の対象だけ表示する")
    ap.add_argument("--wait-timeout", type=float, default=600.0,
                    help="1ソースの取り込み完了を待つ秒数 (既定: 600)")
    ap.add_argument("--docs-dir",
                    default=os.environ.get("NOTEBOOKLM_DOCS_DIR", str(DOCS_DIR)),
                    help=f"同期元ディレクトリ (既定: {DOCS_DIR})")
    ap.add_argument("--state-file",
                    default=os.environ.get("NOTEBOOKLM_STATE_FILE", str(STATE_FILE)),
                    help=f"同期状態ファイル (既定: {STATE_FILE})。"
                         f"ドキュメントセットごとに必ず分けること")
    ap.add_argument("--glob", default=DOCS_GLOB,
                    help=f"同期元ディレクトリ内の対象ファイル (既定: {DOCS_GLOB})")
    ap.add_argument("--mode", choices=["mirror", "append"], default="mirror",
                    help="mirror: ローカルと同じ状態にする (既定) / "
                         "append: ローカルに無いソースも残し、上限で古い順に削除")
    ap.add_argument("--max-sources", type=int,
                    default=int(os.environ.get("NOTEBOOKLM_MAX_SOURCES", DEFAULT_MAX_SOURCES)),
                    help=f"append 時にノートブックに保持するソース数の上限 "
                         f"(既定: {DEFAULT_MAX_SOURCES})")
    args = ap.parse_args()

    DOCS_DIR = Path(args.docs_dir)
    STATE_FILE = Path(args.state_file)
    DOCS_GLOB = args.glob
    print(f"[config] docs={DOCS_DIR} glob={DOCS_GLOB} state={STATE_FILE} "
          f"notebook={args.notebook_title}")

    try:
        return asyncio.run(sync(args))
    except KeyboardInterrupt:
        return 130
    except Exception as e:
        # notebooklm-py は Google の非公開APIを叩くため、認証切れや内部API変更で
        # ここに落ちてくることがある。原因と復旧手段を示して終わる。
        from notebooklm import AuthError, NotebookLMError

        if isinstance(e, AuthError):
            print(f"[ERROR] NotebookLM の認証が無効です: {e}\n"
                  f"        `notebooklm login` で認証情報を作り直してください。",
                  file=sys.stderr)
        elif isinstance(e, NotebookLMError):
            print(f"[ERROR] NotebookLM 側の処理に失敗しました: {type(e).__name__}: {e}\n"
                  f"        notebooklm-py は非公式ライブラリのため、Google 側の変更で"
                  f"動かなくなることがあります。\n"
                  f"        その場合は notebooklm_docs/ のファイルを手動で"
                  f"アップロードしてください。", file=sys.stderr)
        else:
            raise
        return 1


if __name__ == "__main__":
    sys.exit(main())
