# NotebookLM 同期のセットアップ

`notebooklm_docs/` の Markdown を NotebookLM に自動反映させるための初回設定手順です。
この設定をしなくても週次のドキュメント更新自体は動きます（同期ステップがスキップされ、
ジョブサマリに手動アップロードの案内が出るだけです）。

## 前提

NotebookLM に一般向けの公開 API はありません。ここでは非公式ライブラリ
[`notebooklm-py`](https://github.com/teng-lin/notebooklm-py) を使います。これは Google の
**非公開内部 API** を叩くため、Google 側の変更で予告なく動かなくなる可能性があります。
その場合は `notebooklm_docs/` のファイルを手動でアップロードすれば復旧できます。

> **重要**: 手順で作る `storage_state.json` は Google の**セッション Cookie そのもの**で、
> 実質そのアカウントの全権限に相当します。個人の常用アカウントではなく、
> **このノートブック専用の Google アカウント**を用意することを強く推奨します。

## 1. ローカルで認証情報を作る

```bash
pip install "notebooklm-py[browser]"

# ブラウザが開いて Google ログイン。ログイン検出後に自動保存される
notebooklm login

# 上がうまく動かない場合は、既にログイン済みのブラウザの Cookie を読む
notebooklm login --browser-cookies chrome
```

`--browser-cookies` は `chrome` / `firefox` / `brave` / `edge` / `safari` などを指定できます。
Chromium 系で複数プロファイルを使っている場合は `chrome::Profile 1` の形で指定します。

## 2. 認証できているか確認する

```bash
notebooklm auth check      # 認証状態の診断
notebooklm doctor          # プロファイル設定・認証・移行状況をまとめて確認
notebooklm list            # ノートブック一覧が出れば成功
```

## 3. ローカルで同期を試す

```bash
pip install requests beautifulsoup4 lxml

# 何が追加/更新/削除されるかだけ表示（実際には変更しない）
python scripts/sync_notebooklm.py --dry-run

# 実行
python scripts/sync_notebooklm.py
```

初回は `Zscaler_help_docs` という名前のノートブックが作られ、
`notebooklm_docs/*/*_part*.md` が全てソースとして登録されます。
2回目以降は**内容が変わったファイルだけ**が差し替えられます。

特定カテゴリだけ試したい場合:

```bash
python scripts/sync_notebooklm.py --only zdx --dry-run
```

## 4. GitHub Actions に登録する

`storage_state.json` の中身を `NOTEBOOKLM_STORAGE_STATE_JSON` という名前の
リポジトリ Secret に登録します。

```bash
# ファイルの場所を確認
python -c "from notebooklm.paths import get_storage_path; print(get_storage_path())"
# 例: /home/you/.notebooklm/profiles/default/storage_state.json

# gh CLI で登録する場合
gh secret set NOTEBOOKLM_STORAGE_STATE_JSON \
  < "$(python -c 'from notebooklm.paths import get_storage_path; print(get_storage_path())')"
```

GitHub の UI から登録する場合は、Settings → Secrets and variables → Actions →
New repository secret で、ファイルの中身をそのまま貼り付けます。

## 5. ワークフローで動作確認する

Actions → **NotebookLM Docs Weekly Update** → Run workflow で、まず

- `mode`: `incremental`
- `sync`: `dry-run`

で実行し、ジョブサマリに意図した追加/更新/削除が出ることを確認してください。
問題なければ `sync`: `enabled` で実行します。

以降は**毎週月曜 09:00 JST** に自動実行されます。

## トラブルシューティング

| 症状 | 対処 |
|---|---|
| `NotebookLM の認証情報が見つかりません` | 手順1をやり直す。CI なら Secret が未登録 |
| `NotebookLM の認証が無効です` | Cookie の期限切れ。`notebooklm login` で作り直し、Secret を再登録する |
| `NotebookLM 側の処理に失敗しました` | 非公式ライブラリが Google 側の変更で壊れた可能性。`pip install -U notebooklm-py` を試し、直らなければ手動アップロードに切り替える |
| 同期ステップが実行されない | `NOTEBOOKLM_STORAGE_STATE_JSON` Secret が未設定。設定するまでスキップされる |
| ノートブックを作り直したい | NotebookLM 側で削除し、`data/notebooklm_sync_state.json` も削除してから再実行する |

## 認証情報を失効させたい場合

Google アカウントの[セキュリティ設定](https://myaccount.google.com/security)から
セッションを失効させ、GitHub の Secret を削除してください。ローカルは
`notebooklm auth logout` で消せます。
