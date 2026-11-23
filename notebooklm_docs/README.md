# NotebookLM 用 Zscaler ヘルプドキュメント

`help.zscaler.com` の全記事（sitemap.xml 掲載分）を機能カテゴリごとに
Markdown へまとめたものです。NotebookLM に読み込ませる用途を想定しています。

- 生成: `scripts/build_help_docs.py`
- 更新: `.github/workflows/notebooklm-weekly.yml`（毎週月曜 09:00 JST）
- 記事数: **4,231**
- ファイル数: **34** / 総語数: **4,185,349**
- 最終更新: 2026-11-23T11:12:18Z

## NotebookLM への読み込み

### 自動（推奨）

```bash
python scripts/sync_notebooklm.py --dry-run   # 差分の確認
python scripts/sync_notebooklm.py             # 実行
```

`Zscaler_help_docs` ノートブックに対し、内容が変わったファイルのソースだけを
差し替えます。初回の認証設定は `docs/notebooklm-setup.md` を参照してください。
GitHub Actions の Secret を登録すれば週次で自動同期されます。

### 手動

1. NotebookLM で新規ノートブックを作成する
2. 「ソースを追加」→「ファイルをアップロード」
3. 下表の `.md` ファイルを選択する（カテゴリ単位でも全件でも可）

NotebookLM の制限は「1ノートブックあたりのソース数」と「1ソースあたり
50万語」です。本スクリプトは 1ファイル約18万語で分割するため、どのファイルも
単体で1ソースに収まります。ソース数を節約したい場合は、必要なカテゴリだけを
アップロードしてください。

## ファイル一覧

| ファイル | カテゴリ | 語数 | サイズ |
|---|---|---|---|
| `zia/zia_part1.md` | ZIA — Internet & SaaS | 170,607 | 1,169 KB |
| `zia/zia_part2.md` | ZIA — Internet & SaaS | 168,640 | 1,165 KB |
| `zia/zia_part3.md` | ZIA — Internet & SaaS | 169,080 | 1,171 KB |
| `zia/zia_part4.md` | ZIA — Internet & SaaS | 164,762 | 1,170 KB |
| `zia/zia_part5.md` | ZIA — Internet & SaaS | 172,954 | 1,171 KB |
| `zia/zia_part6.md` | ZIA — Internet & SaaS | 165,503 | 1,110 KB |
| `zia/zia_part7.md` | ZIA — Internet & SaaS | 136,379 | 915 KB |
| `zia/zia_part8.md` | ZIA — Internet & SaaS | 65,941 | 441 KB |
| *(ZIA — Internet & SaaS 記事数: 883)* | | | |
| `zpa/zpa_part1.md` | ZPA — Private Access | 161,913 | 1,154 KB |
| `zpa/zpa_part2.md` | ZPA — Private Access | 160,579 | 1,164 KB |
| `zpa/zpa_part3.md` | ZPA — Private Access | 156,386 | 1,169 KB |
| `zpa/zpa_part4.md` | ZPA — Private Access | 45,728 | 307 KB |
| *(ZPA — Private Access 記事数: 519)* | | | |
| `zdx/zdx_part1.md` | ZDX — Digital Experience Monitoring | 111,862 | 765 KB |
| *(ZDX — Digital Experience Monitoring 記事数: 124)* | | | |
| `zcc/zcc_part1.md` | ZCC — Zscaler Client Connector | 155,580 | 1,171 KB |
| `zcc/zcc_part2.md` | ZCC — Zscaler Client Connector | 55,115 | 397 KB |
| *(ZCC — Zscaler Client Connector 記事数: 267)* | | | |
| `api/api_part1.md` | API / SDK | 147,812 | 1,167 KB |
| `api/api_part2.md` | API / SDK | 140,421 | 1,165 KB |
| `api/api_part3.md` | API / SDK | 183,668 | 1,156 KB |
| *(API / SDK 記事数: 300)* | | | |
| `deception/deception_part1.md` | Zscaler Deception | 161,127 | 1,164 KB |
| `deception/deception_part2.md` | Zscaler Deception | 16,475 | 118 KB |
| *(Zscaler Deception 記事数: 325)* | | | |
| `data_security/data_security_part1.md` | Data Security — DSPM | 170,360 | 1,171 KB |
| `data_security/data_security_part2.md` | Data Security — DSPM | 14,657 | 103 KB |
| *(Data Security — DSPM 記事数: 201)* | | | |
| `exposure_mgmt/exposure_mgmt_part1.md` | Risk & Exposure Management | 171,628 | 1,169 KB |
| `exposure_mgmt/exposure_mgmt_part2.md` | Risk & Exposure Management | 155,537 | 1,101 KB |
| *(Risk & Exposure Management 記事数: 398)* | | | |
| `branch/branch_part1.md` | Branch / Cellular / Cloud Connector | 71,215 | 496 KB |
| *(Branch / Cellular / Cloud Connector 記事数: 94)* | | | |
| `partners/partners_part1.md` | Technology Partners | 28,047 | 270 KB |
| *(Technology Partners 記事数: 146)* | | | |
| `platform/platform_part1.md` | Unified Platform / Admin / Logs | 162,619 | 1,171 KB |
| `platform/platform_part2.md` | Unified Platform / Admin / Logs | 117,523 | 831 KB |
| *(Unified Platform / Admin / Logs 記事数: 396)* | | | |
| `ai_security/ai_security_part1.md` | AI Security | 71,235 | 479 KB |
| *(AI Security 記事数: 92)* | | | |
| `browser/browser_part1.md` | Zero Trust Browser | 27,378 | 215 KB |
| *(Zero Trust Browser 記事数: 65)* | | | |
| `operations/operations_part1.md` | Deployment / Operations / Terms | 167,475 | 1,149 KB |
| `operations/operations_part2.md` | Deployment / Operations / Terms | 114,432 | 824 KB |
| *(Deployment / Operations / Terms 記事数: 155)* | | | |
| `other/other_part1.md` | Other | 151,150 | 1,165 KB |
| `other/other_part2.md` | Other | 51,561 | 355 KB |
| *(Other 記事数: 266)* | | | |

## 注意

- 本ディレクトリは Zscaler の著作物の複製です。社内・個人利用の範囲で扱い、
  再配布しないでください。GitHub Pages の配信対象からは除外しています。
- 各記事は `<!-- ZS-ARTICLE {...} -->` マーカーで区切られています。差分更新が
  このマーカーを利用するため、ファイルを手で編集しないでください。
