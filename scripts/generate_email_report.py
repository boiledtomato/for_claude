#!/usr/bin/env python3
"""
URL Lookup 更新レポートメール本文を生成するスクリプト。
GitHub Actions の send-mail ステップから呼び出される。
"""

import json
import pathlib
import sys
from datetime import datetime, timezone, timedelta

ROOT        = pathlib.Path(__file__).parent.parent
REPORT_PATH = ROOT / "data" / "url-lookup-report.json"

RISK_JA = {"high": "🔴 高", "medium": "🟡 中", "low": "🟢 低", "unknown": "⚪ 不明"}
ACTION_JA = {"block": "ブロック", "caution": "要注意", "allow": "許可", "caution": "要注意"}
SOURCE_JA = {"virustotal": "VirusTotal", "fallback": "既存データ (取得失敗)"}


def jst(iso: str) -> str:
    try:
        dt = datetime.fromisoformat(iso.replace("Z", "+00:00"))
        jst_dt = dt.astimezone(timezone(timedelta(hours=9)))
        return jst_dt.strftime("%Y-%m-%d %H:%M JST")
    except Exception:
        return iso


def main() -> None:
    if not REPORT_PATH.exists():
        print("レポートファイルが見つかりません。", file=sys.stderr)
        sys.exit(1)

    r = json.loads(REPORT_PATH.read_text(encoding="utf-8"))

    run_at    = jst(r.get("run_at", ""))
    queried   = r.get("queried", 0)
    skipped   = r.get("skipped", 0)
    errors    = r.get("errors", [])
    updated   = r.get("updated", [])
    day_group = r.get("day_group", "?")
    total     = r.get("total_domains", "?")
    force_all = r.get("force_all", False)

    group_label = "全件 (--all)" if force_all else f"グループ {day_group}/5"

    lines = [
        f"URL Lookup データ更新レポート",
        f"{'=' * 50}",
        f"実行日時  : {run_at}",
        f"処理範囲  : {group_label}  (全 {total} ドメイン中)",
        f"更新件数  : {queried} 件",
        f"スキップ  : {skipped} 件 (本日更新済み)",
        f"取得失敗  : {len(errors)} 件",
        "",
    ]

    if errors:
        lines.append("【取得失敗ドメイン】")
        for d in errors:
            lines.append(f"  ✗ {d}  (前回データを維持)")
        lines.append("")

    if updated:
        lines.append("【更新ドメイン一覧】")
        lines.append(f"  {'ドメイン':<30} {'URLカテゴリ':<30} {'クラウドアプリ':<25} {'リスク':<10} {'データソース'}")
        lines.append(f"  {'-'*120}")
        for e in updated:
            risk   = RISK_JA.get(e.get("risk", ""), e.get("risk", ""))
            source = SOURCE_JA.get(e.get("source", ""), e.get("source", ""))
            lines.append(
                f"  {e['domain']:<30} {e['urlCat']:<30} {e['cloudApp']:<25} {risk:<10} {source}"
            )
    else:
        lines.append("更新されたドメインはありません。")

    lines += [
        "",
        "─" * 50,
        "このメールは GitHub Actions により自動送信されました。",
        "Zscaler Learning Dashboard — URL Lookup 自動更新",
    ]

    print("\n".join(lines))


if __name__ == "__main__":
    main()
