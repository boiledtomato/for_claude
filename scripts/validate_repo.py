"""リポジトリの静的検証。PR チェックとローカルの両方から実行する。

外部サイトには一切アクセスしない。構文と形式だけを見る:

  1. .github/workflows/*.yml が YAML として読め、トリガーと jobs を持つこと
  2. 各ステップの run: ブロックが bash として構文的に正しいこと
  3. scripts/*.py がコンパイルできること
  4. data/*.json が JSON として読めること

生成物 (notebooklm_docs/, community_docs/) の中身は見ない。数十MBあるうえ、
内容の正しさはビルドスクリプト側の責務なので、ここで検証しても意味がない。

使い方:
    pip install pyyaml
    python scripts/validate_repo.py
"""

import json
import py_compile
import re
import subprocess
import sys
import tempfile
from pathlib import Path

import yaml

WORKFLOW_DIR = Path(".github/workflows")
SCRIPTS_DIR = Path("scripts")
DATA_DIR = Path("data")

# run: の中の ${{ ... }} は bash から見ると構文エラーになるので、
# 構文チェックの前に無害な単語へ置き換える
GH_EXPR = re.compile(r"\$\{\{.*?\}\}", re.DOTALL)

failures: list[str] = []


def record(label: str, ok: bool, detail: str = "") -> None:
    mark = "ok  " if ok else "FAIL"
    line = f"  [{mark}] {label}"
    if not ok and detail:
        line += f"\n         {detail}"
    print(line)
    if not ok:
        failures.append(label)


def triggers(doc: dict):
    """PyYAML は `on:` を真偽値 True として読むため、両方を見る。"""
    return doc.get("on", doc.get(True))


def check_workflows() -> dict[Path, dict]:
    print("== ワークフロー YAML ==")
    parsed: dict[Path, dict] = {}
    files = sorted(WORKFLOW_DIR.glob("*.yml")) + sorted(WORKFLOW_DIR.glob("*.yaml"))
    if not files:
        record(str(WORKFLOW_DIR), False, "ワークフローが1つも見つかりません")
        return parsed
    for path in files:
        try:
            doc = yaml.safe_load(path.read_text(encoding="utf-8"))
            if not isinstance(doc, dict):
                raise ValueError("トップレベルがマッピングではありません")
            if not triggers(doc):
                raise ValueError("トリガー (on:) がありません")
            if not doc.get("jobs"):
                raise ValueError("jobs がありません")
            parsed[path] = doc
            record(str(path), True)
        except Exception as e:
            record(str(path), False, f"{type(e).__name__}: {e}")
    return parsed


def check_run_blocks(parsed: dict[Path, dict]) -> None:
    print("\n== run: ブロックの bash 構文 ==")
    found = False
    for path, doc in parsed.items():
        for job_name, job in (doc.get("jobs") or {}).items():
            if not isinstance(job, dict):
                continue
            for i, step in enumerate(job.get("steps") or []):
                if not isinstance(step, dict) or "run" not in step:
                    continue
                found = True
                name = step.get("name") or f"step {i + 1}"
                label = f"{path.name} :: {job_name} :: {name}"
                script = GH_EXPR.sub("GH_EXPR", str(step["run"]))
                shell = step.get("shell", job.get("defaults", {}).get("run", {}).get("shell", "bash"))
                if not str(shell).startswith("bash") and str(shell) != "sh":
                    record(label, True)  # bash 以外は検証対象外
                    continue
                proc = subprocess.run(
                    ["bash", "-n"], input=script, text=True, capture_output=True
                )
                record(label, proc.returncode == 0, proc.stderr.strip()[:400])
    if not found:
        print("  (run: ブロックなし)")


def check_python() -> None:
    print("\n== Python のコンパイル ==")
    files = sorted(SCRIPTS_DIR.rglob("*.py"))
    if not files:
        print("  (対象なし)")
        return
    for path in files:
        try:
            with tempfile.NamedTemporaryFile(suffix=".pyc", delete=True) as tmp:
                py_compile.compile(str(path), cfile=tmp.name, doraise=True)
            record(str(path), True)
        except py_compile.PyCompileError as e:
            record(str(path), False, str(e).strip()[:400])


def check_json() -> None:
    print("\n== JSON の読み込み ==")
    files = sorted(DATA_DIR.glob("*.json"))
    if not files:
        print("  (対象なし)")
        return
    for path in files:
        try:
            with path.open(encoding="utf-8") as f:
                json.load(f)
            size = path.stat().st_size
            record(f"{path}  ({size / 1024:,.0f} KB)", True)
        except Exception as e:
            record(str(path), False, f"{type(e).__name__}: {e}")


def main() -> int:
    parsed = check_workflows()
    check_run_blocks(parsed)
    check_python()
    check_json()

    print()
    if failures:
        print(f"検証失敗: {len(failures)} 件")
        for f in failures:
            print(f"  - {f}")
        return 1
    print("すべての検証を通過しました。")
    return 0


if __name__ == "__main__":
    sys.exit(main())
