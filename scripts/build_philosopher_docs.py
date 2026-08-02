#!/usr/bin/env python3
"""data/philosophers.json から哲学者リソース集の Markdown を生成する。

出力先は philosopher_docs/<時代フォルダ>/<哲学者名>.md で、1ファイル1名。
Google Drive 側もこの構成をそのまま写す（時代=フォルダ、哲学者=ファイル）。

  python scripts/build_philosopher_docs.py             # Markdown を生成
  python scripts/build_philosopher_docs.py --check     # 全URLの死活確認だけ
  python scripts/build_philosopher_docs.py --check --write-status
"""

from __future__ import annotations

import argparse
import json
import sys
import urllib.error
import urllib.request
from datetime import datetime, timezone
from pathlib import Path
from typing import Any

ROOT = Path(__file__).resolve().parent.parent
DATA_FILE = ROOT / "data" / "philosophers.json"
STATUS_FILE = ROOT / "data" / "philosopher_links_status.json"
OUT_DIR = ROOT / "philosopher_docs"

TIMEOUT = 30
USER_AGENT = "Mozilla/5.0 (compatible; philosopher-docs-linkcheck/1.0)"


def load_data() -> dict[str, Any]:
    return json.loads(DATA_FILE.read_text(encoding="utf-8"))


def safe_filename(name: str) -> str:
    for ch in '/\\:*?"<>|':
        name = name.replace(ch, "_")
    return name


def render_philosopher(p: dict[str, Any], era: dict[str, Any]) -> str:
    lines: list[str] = []
    lines.append(f"# {p['name_ja']}（{p['name_en']}）")
    lines.append("")
    lines.append(f"- **時代区分**: {era['label']}")
    lines.append(f"- **生没年**: {p['life']}")
    lines.append(f"- **地域**: {p['region']}")
    lines.append(f"- **キーワード**: {' / '.join(p['keywords'])}")
    lines.append("")
    lines.append("## 悩みへの接続ポイント")
    lines.append("")
    for t in p["themes"]:
        lines.append(f"- {t}")
    lines.append("")

    for kind, heading in (
        ("一次資料", "## 一次資料（本人のテキスト）"),
        ("解説", "## 解説・研究（専門家によるもの）"),
        ("参考", "## その他の参考リンク"),
    ):
        links = [l for l in p["links"] if l["kind"] == kind]
        if not links:
            continue
        lines.append(heading)
        lines.append("")
        lines.append("| 資料 | 形式 | 言語 | URL | メモ |")
        lines.append("|---|---|---|---|---|")
        for l in links:
            note = l.get("note", "") or "—"
            lines.append(
                f"| {l['title']} | {l['format']} | {l['lang']} | <{l['url']}> | {note} |"
            )
        lines.append("")

    if p.get("copyright_note"):
        lines.append("## 著作権についての注意")
        lines.append("")
        lines.append(p["copyright_note"])
        lines.append("")

    lines.append("---")
    lines.append("")
    lines.append(
        "_このファイルは `data/philosophers.json` から自動生成されています。"
        "編集する場合は JSON 側を直してください。_"
    )
    lines.append("")
    return "\n".join(lines)


def render_index(data: dict[str, Any]) -> str:
    lines: list[str] = []
    lines.append(f"# {data['title']}")
    lines.append("")
    lines.append(data["description"])
    lines.append("")
    total = sum(len(e["philosophers"]) for e in data["eras"])
    lines.append(
        f"- 収録: **{total}名** / {len(data['eras'])}時代"
        f"（最終更新: {datetime.now(timezone.utc).strftime('%Y-%m-%d')}）"
    )
    lines.append("- 1哲学者＝1ファイル、時代ごとにフォルダを分けています。")
    lines.append("")
    for era in data["eras"]:
        lines.append(f"## {era['id']} — {era['label']}")
        lines.append("")
        lines.append("| 哲学者 | 生没年 | キーワード | ファイル |")
        lines.append("|---|---|---|---|")
        for p in era["philosophers"]:
            fn = f"{safe_filename(p['name_ja'])}.md"
            kw = " / ".join(p["keywords"][:3])
            lines.append(
                f"| {p['name_ja']}（{p['name_en']}） | {p['life']} | {kw} | "
                f"[{fn}]({era['id']}/{fn}) |"
            )
        lines.append("")
    return "\n".join(lines)


def build(data: dict[str, Any]) -> int:
    written = 0
    for era in data["eras"]:
        era_dir = OUT_DIR / era["id"]
        era_dir.mkdir(parents=True, exist_ok=True)
        for p in era["philosophers"]:
            path = era_dir / f"{safe_filename(p['name_ja'])}.md"
            path.write_text(render_philosopher(p, era), encoding="utf-8")
            written += 1
    (OUT_DIR / "README.md").write_text(render_index(data), encoding="utf-8")
    return written


def check_url(url: str) -> tuple[int, str]:
    req = urllib.request.Request(url, headers={"User-Agent": USER_AGENT})
    try:
        with urllib.request.urlopen(req, timeout=TIMEOUT) as res:
            return res.status, res.headers.get("Content-Type", "")
    except urllib.error.HTTPError as exc:
        return exc.code, ""
    except Exception as exc:  # ネットワーク断・TLS 等はまとめて扱う
        return 0, str(exc)[:80]


def check_links(data: dict[str, Any], write_status: bool) -> int:
    results: list[dict[str, Any]] = []
    broken = 0
    for era in data["eras"]:
        for p in era["philosophers"]:
            for l in p["links"]:
                status, info = check_url(l["url"])
                if status != 200:
                    # 大きなPDFやbot対策で一時的に 500/202/429 が返ることがある
                    status, info = check_url(l["url"])
                # 202 は Cloudflare 等のbot判定を挟むサイトが返す（実体はブラウザで取得可）
                ok = status in (200, 202)
                if not ok:
                    broken += 1
                    print(f"[NG {status}] {p['name_ja']} / {l['title']}\n         {l['url']}")
                results.append(
                    {
                        "era": era["id"],
                        "philosopher": p["name_ja"],
                        "title": l["title"],
                        "url": l["url"],
                        "status": status,
                        "info": info,
                        "ok": ok,
                    }
                )
    print(f"\n確認 {len(results)} 件 / 異常 {broken} 件")
    if write_status:
        STATUS_FILE.write_text(
            json.dumps(
                {
                    "checked_at": datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ"),
                    "total": len(results),
                    "broken": broken,
                    "links": results,
                },
                ensure_ascii=False,
                indent=2,
            )
            + "\n",
            encoding="utf-8",
        )
        print(f"→ {STATUS_FILE.relative_to(ROOT)} に記録しました")
    return broken


def main() -> int:
    ap = argparse.ArgumentParser(description=__doc__)
    ap.add_argument("--check", action="store_true", help="URLの死活確認のみ行う")
    ap.add_argument("--write-status", action="store_true", help="確認結果をJSONに残す")
    args = ap.parse_args()

    data = load_data()

    if args.check:
        return 1 if check_links(data, args.write_status) else 0

    written = build(data)
    print(f"{written} ファイルを {OUT_DIR.relative_to(ROOT)}/ に生成しました")
    return 0


if __name__ == "__main__":
    sys.exit(main())
