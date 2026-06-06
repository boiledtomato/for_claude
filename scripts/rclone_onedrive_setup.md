# rclone × OneDrive セットアップ手順

Azure登録不要で OneDrive に接続するための1回限りの手順です。

---

## 1. rclone をインストール（Windows）

```powershell
winget install Rclone.Rclone
```

またはこちらからダウンロード: https://rclone.org/downloads/

---

## 2. rclone で OneDrive を設定

```powershell
rclone config
```

対話形式で以下を選択:

```
n  → New remote
名前: onedrive          ← 必ずこの名前にしてください
storage: onedrive       ← 番号で選択（"Microsoft OneDrive"）
client_id:              ← 空欄のままEnter（rclone組み込みアプリを使用）
client_secret:          ← 空欄のままEnter
region: global          ← デフォルトでEnter
Edit advanced config? n
Use auto config? y      ← ブラウザが開くのでMicrosoftアカウントでサインイン
drive_type: personal    ← "OneDrive Personal" を選択
root_folder_id:         ← 空欄のままEnter
y                       ← 設定を確認して保存
q                       ← 終了
```

---

## 3. 設定ファイルの内容を確認

```powershell
rclone config show onedrive
```

以下のような出力が得られます:

```ini
[onedrive]
type = onedrive
token = {"access_token":"eyJ...","token_type":"Bearer","refresh_token":"0.A...","expiry":"2026-06-07T..."}
drive_id = b!abc123...
drive_type = personal
```

---

## 4. GitHub Secrets に登録

上記の出力 **全体**（`[onedrive]` から最終行まで）をコピーして、  
GitHub リポジトリ → **Settings** → **Secrets and variables** → **Actions** → **New repository secret** で登録:

| Secret名 | 値 |
|---|---|
| `RCLONE_CONFIG` | 手順3でコピーした設定全体 |

---

## 5. 動作確認（任意）

```powershell
rclone lsd onedrive:drive_folder/Obsidian/my_repository/00_Fleeting_notes
```

フォルダが表示されれば接続成功です。

---

## その他の GitHub Secrets

| Secret名 | 値 |
|---|---|
| `GMAIL_ADDRESS` | Gmailアドレス（送信元・宛先共通） |
| `GMAIL_APP_PASSWORD` | Googleアカウント → セキュリティ → アプリパスワード で作成済みのもの |
| `RECIPIENT_EMAIL` | `ciderred1239@gmail.com` |
