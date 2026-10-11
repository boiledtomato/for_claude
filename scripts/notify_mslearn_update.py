"""
Microsoft Learn 月次更新の結果をメールで通知する (mslearn-monthly.yml の最終ステップ)

**どんな結果でも必ず 1 通送る。** 変更なし・ビルド失敗・NotebookLM 同期失敗の
いずれでも送り、「メールが来ない = 何も変わっていない」と誤解させない。
ビルドが途中で落ちて report.json が無い場合も、その旨を書いて送る。

    python scripts/notify_mslearn_update.py --docset entra \
        --build-outcome success --sync-outcome success --run-url https://github.com/...

環境変数:
    GMAIL_APP_PASSWORD  Gmail のアプリパスワード (必須。無ければ失敗終了)
    NOTIFY_EMAIL_TO     宛先 (既定: ciderred1239@gmail.com)
"""

import argparse
import html
import json
import os
import smtplib
import sys
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pathlib import Path

GMAIL_ADDRESS = "ciderred1239@gmail.com"
REPORT_ROOT = Path("output/mslearn")
# 本文に載せる件数。全件は添付の changes.md に入れる
MAX_LISTED = 100

OUTCOME_JA = {
    "success": "成功", "failure": "失敗", "cancelled": "中断", "skipped": "スキップ", "": "未実行",
}


def esc(s) -> str:
    return html.escape(str(s or ""))


def load_json(path: Path) -> dict | None:
    try:
        return json.loads(path.read_text(encoding="utf-8"))
    except (OSError, json.JSONDecodeError):
        return None


def page_list(items: list[dict], extra=lambda it: "") -> str:
    if not items:
        return "<p style='color:#666'>なし</p>"
    rows = "".join(
        f"<li><a href='{esc(it['url'])}'>{esc(it['title'])}</a>"
        f" <span style='color:#666'>— {esc(it['category_name'])}{esc(extra(it))}</span></li>"
        for it in items[:MAX_LISTED])
    more = (f"<p style='color:#666'>ほか {len(items) - MAX_LISTED} 件は添付の changes.md を参照</p>"
            if len(items) > MAX_LISTED else "")
    return f"<ul>{rows}</ul>{more}"


def build(docset: str, report: dict | None, sync: dict | None, build_outcome: str,
          sync_outcome: str, run_url: str, notebook: str) -> tuple[str, str]:
    name = (report or {}).get("docset_name") or docset
    sync_ja = OUTCOME_JA.get(sync_outcome, sync_outcome)

    if not report or report.get("fatal"):
        reason = (report or {}).get("fatal") or "ビルドが途中で終了し、結果ファイルがありません。"
        subject = f"[MS Learn → NotebookLM] {name}: 更新処理が失敗しました"
        body = (f"<h2>{esc(name)} — 月次更新に失敗しました</h2>"
                f"<p style='color:#b00020'><b>{esc(reason)}</b></p>"
                f"<p>リポジトリ内の Markdown と NotebookLM は前回のまま変更していません。"
                f"ログを確認し、Actions から再実行してください。</p>"
                f"<p>ビルド: {esc(OUTCOME_JA.get(build_outcome, build_outcome))} / "
                f"NotebookLM 同期: {esc(sync_ja)}</p>"
                f"<p><a href='{esc(run_url)}'>実行ログを開く</a></p>")
        return subject, body

    n_new, n_upd = len(report["new"]), len(report["updated"])
    n_del, n_fail = len(report["removed"]), len(report["failed"])
    month = report["checked_at"][:10]
    if report.get("initial"):
        headline = f"初回登録: {report['total_pages']:,} ページ"
    elif n_new or n_upd or n_del:
        headline = f"新規 {n_new} / 更新 {n_upd} / 削除 {n_del}"
    else:
        headline = "変更なし"
    warn = []
    if n_fail:
        warn.append(f"取得失敗 {n_fail}")
    if build_outcome not in ("success", ""):
        warn.append("ビルド / GitHub 保存の異常終了")
    if sync_outcome not in ("success", "skipped", ""):
        warn.append("NotebookLM 同期失敗")
    subject = (f"[MS Learn → NotebookLM] {name} {month} — {headline}"
               + (f" ⚠ {' / '.join(warn)}" if warn else ""))

    # NotebookLM 同期の結果
    if sync:
        s = sync
        sync_html = (f"<p>ノートブック <b>{esc(s['notebook'])}</b>{' (dry-run)' if s.get('dry_run') else ''}: "
                     f"追加 <b>{s['added']}</b> / 差し替え <b>{s['updated']}</b> / 削除 <b>{s['deleted']}</b>"
                     f" / 変更なし {s['skipped']}</p>")
        if s.get("failures"):
            sync_html += ("<p style='color:#b00020'>失敗したファイル: "
                          + esc(", ".join(s["failures"][:20])) + "</p>")
    elif sync_outcome == "skipped" or not sync_outcome:
        sync_html = (f"<p>NotebookLM への同期は行っていません "
                     f"(<code>NOTEBOOKLM_STORAGE_STATE_JSON</code> 未設定、または skip 指定)。"
                     f"<code>mslearn_docs/{esc(docset)}/</code> の変更ファイルを "
                     f"<b>{esc(notebook)}</b> ノートブックへ手動でアップロードしてください。</p>")
    else:
        sync_html = (f"<p style='color:#b00020'><b>NotebookLM 同期: {esc(sync_ja)}</b> — "
                     f"Markdown は GitHub に保存済みです。認証切れの場合は "
                     f"<code>docs/notebooklm-setup.md</code> の手順で Secret を更新してください。</p>")

    cats = "".join(
        f"<tr><td>{esc(c['name'])}</td><td style='text-align:right'>{c['pages']:,}</td>"
        f"<td style='text-align:right'>{c['files']}</td></tr>" for c in report["categories"])

    parts = [
        f"<h2>Microsoft Learn — {esc(name)} ({esc(report['locale'])}) 月次更新 {esc(month)}</h2>",
        f"<p style='font-size:1.1em'><b>{esc(headline)}</b></p>",
        "<table cellpadding='4' style='border-collapse:collapse'>"
        f"<tr><td>sitemap 掲載ページ</td><td><b>{report.get('sitemap_pages', 0):,}</b></td></tr>"
        f"<tr><td>保存ページ</td><td><b>{report['total_pages']:,}</b></td></tr>"
        f"<tr><td>新規</td><td><b>{n_new}</b></td></tr>"
        f"<tr><td>更新 (本文が変化)</td><td><b>{n_upd}</b></td></tr>"
        f"<tr><td>削除 (sitemap から消えた)</td><td><b>{n_del}</b></td></tr>"
        f"<tr><td>取得失敗 (前回の内容を保持)</td><td><b>{n_fail}</b></td></tr>"
        f"<tr><td>内容が変わった Markdown ファイル</td><td><b>{report.get('files_changed', 0)}</b>"
        f" / {report.get('total_files', 0)}</td></tr>"
        "</table>",
        "<h3>NotebookLM</h3>", sync_html,
    ]
    if report.get("limit"):
        parts.append(f"<p style='color:#b36b00'>動作確認モード: 先頭 {report['limit']} ページのみ取得しました。</p>")
    if report.get("initial"):
        parts.append("<p>初回のため全ページが新規です。ページ一覧は添付の changes.md を参照してください。</p>")
    else:
        parts += ["<h3>新規ページ</h3>", page_list(report["new"]),
                  "<h3>更新されたページ</h3>",
                  page_list(report["updated"], lambda it: f" (+{it['plus']} / -{it['minus']} 行)")]
    parts += ["<h3>削除されたページ</h3>", page_list(report["removed"])]
    if n_fail:
        parts += ["<h3>取得に失敗したページ</h3>",
                  page_list(report["failed"], lambda it: f" — {it['error']}")]
    parts += ["<h3>カテゴリ別</h3>",
              "<table border='1' cellpadding='4' style='border-collapse:collapse'>"
              "<tr><th>カテゴリ</th><th>ページ</th><th>ファイル</th></tr>" + cats + "</table>",
              f"<p><a href='{esc(run_url)}'>実行ログを開く</a></p>"]
    return subject, "\n".join(parts)


def send(subject: str, body_html: str, attachments: list[Path]) -> None:
    password = os.environ.get("GMAIL_APP_PASSWORD")
    if not password:
        raise RuntimeError("GMAIL_APP_PASSWORD が設定されていません")
    to = os.environ.get("NOTIFY_EMAIL_TO") or GMAIL_ADDRESS
    msg = MIMEMultipart("mixed")
    msg["Subject"] = subject
    msg["From"] = f"MS Learn NotebookLM <{GMAIL_ADDRESS}>"
    msg["To"] = to
    msg.attach(MIMEText(body_html, "html", "utf-8"))
    for p in attachments:
        part = MIMEText(p.read_text("utf-8"), "markdown", "utf-8")
        part.add_header("Content-Disposition", "attachment", filename=p.name)
        msg.attach(part)
    with smtplib.SMTP_SSL("smtp.gmail.com", 465) as server:
        server.login(GMAIL_ADDRESS, password)
        server.sendmail(GMAIL_ADDRESS, [to], msg.as_string())
    print(f"[MAIL] {to} へ送信: {subject}")


def main() -> int:
    ap = argparse.ArgumentParser(description="Microsoft Learn 月次更新の結果をメール通知する")
    ap.add_argument("--docset", default="entra")
    ap.add_argument("--build-outcome", default="")
    ap.add_argument("--sync-outcome", default="")
    ap.add_argument("--run-url", default="")
    ap.add_argument("--notebook-title", default="")
    ap.add_argument("--dry-run", action="store_true", help="送信せず本文を表示する")
    args = ap.parse_args()

    report_dir = REPORT_ROOT / args.docset
    report = load_json(report_dir / "report.json")
    sync = load_json(report_dir / "sync.json")
    notebook = args.notebook_title or f"MSLearn_{args.docset}"
    subject, body = build(args.docset, report, sync, args.build_outcome,
                          args.sync_outcome, args.run_url, notebook)
    attachments = [p for p in [report_dir / "changes.md"] if p.exists() and report]

    if args.dry_run:
        print(subject)
        print(body)
        return 0
    send(subject, body, attachments)
    return 0


if __name__ == "__main__":
    sys.exit(main())
