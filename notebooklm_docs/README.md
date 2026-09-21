# NotebookLM 用 Zscaler ヘルプドキュメント

`help.zscaler.com` の全記事（sitemap.xml 掲載分）を機能カテゴリごとに
Markdown へまとめたものです。NotebookLM に読み込ませる用途を想定しています。

- 生成: `scripts/build_help_docs.py`
- 更新: `.github/workflows/notebooklm-weekly.yml`（毎週月曜 09:00 JST）
- 記事数: **4,112**
- ファイル数: **33** / 総語数: **4,040,516**
- 最終更新: 2026-09-21T22:17:41Z

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
| `zia/zia_part1.md` | ZIA — Internet & SaaS | 170,613 | 1,169 KB |
| `zia/zia_part2.md` | ZIA — Internet & SaaS | 171,164 | 1,168 KB |
| `zia/zia_part3.md` | ZIA — Internet & SaaS | 169,259 | 1,171 KB |
| `zia/zia_part4.md` | ZIA — Internet & SaaS | 162,478 | 1,141 KB |
| `zia/zia_part5.md` | ZIA — Internet & SaaS | 173,238 | 1,166 KB |
| `zia/zia_part6.md` | ZIA — Internet & SaaS | 175,770 | 1,169 KB |
| `zia/zia_part7.md` | ZIA — Internet & SaaS | 165,717 | 1,119 KB |
| *(ZIA — Internet & SaaS 記事数: 852)* | | | |
| `zpa/zpa_part1.md` | ZPA — Private Access | 165,262 | 1,170 KB |
| `zpa/zpa_part2.md` | ZPA — Private Access | 156,953 | 1,136 KB |
| `zpa/zpa_part3.md` | ZPA — Private Access | 157,548 | 1,167 KB |
| `zpa/zpa_part4.md` | ZPA — Private Access | 46,212 | 311 KB |
| *(ZPA — Private Access 記事数: 557)* | | | |
| `zdx/zdx_part1.md` | ZDX — Digital Experience Monitoring | 115,835 | 791 KB |
| *(ZDX — Digital Experience Monitoring 記事数: 131)* | | | |
| `zcc/zcc_part1.md` | ZCC — Zscaler Client Connector | 156,435 | 1,170 KB |
| `zcc/zcc_part2.md` | ZCC — Zscaler Client Connector | 39,045 | 280 KB |
| *(ZCC — Zscaler Client Connector 記事数: 255)* | | | |
| `api/api_part1.md` | API / SDK | 148,066 | 1,167 KB |
| `api/api_part2.md` | API / SDK | 137,085 | 1,171 KB |
| `api/api_part3.md` | API / SDK | 144,942 | 880 KB |
| *(API / SDK 記事数: 277)* | | | |
| `deception/deception_part1.md` | Zscaler Deception | 162,186 | 1,169 KB |
| `deception/deception_part2.md` | Zscaler Deception | 13,025 | 94 KB |
| *(Zscaler Deception 記事数: 323)* | | | |
| `data_security/data_security_part1.md` | Data Security — DSPM | 170,964 | 1,169 KB |
| `data_security/data_security_part2.md` | Data Security — DSPM | 14,706 | 101 KB |
| *(Data Security — DSPM 記事数: 203)* | | | |
| `exposure_mgmt/exposure_mgmt_part1.md` | Risk & Exposure Management | 170,274 | 1,167 KB |
| `exposure_mgmt/exposure_mgmt_part2.md` | Risk & Exposure Management | 121,330 | 861 KB |
| *(Risk & Exposure Management 記事数: 362)* | | | |
| `branch/branch_part1.md` | Branch / Cellular / Cloud Connector | 152,651 | 1,157 KB |
| `branch/branch_part2.md` | Branch / Cellular / Cloud Connector | 83,001 | 579 KB |
| *(Branch / Cellular / Cloud Connector 記事数: 270)* | | | |
| `partners/partners_part1.md` | Technology Partners | 28,233 | 271 KB |
| *(Technology Partners 記事数: 146)* | | | |
| `platform/platform_part1.md` | Unified Platform / Admin / Logs | 164,079 | 1,172 KB |
| `platform/platform_part2.md` | Unified Platform / Admin / Logs | 131,623 | 913 KB |
| *(Unified Platform / Admin / Logs 記事数: 428)* | | | |
| `ai_security/ai_security_part1.md` | AI Security | 60,524 | 407 KB |
| *(AI Security 記事数: 83)* | | | |
| `browser/browser_part1.md` | Zero Trust Browser | 20,577 | 163 KB |
| *(Zero Trust Browser 記事数: 53)* | | | |
| `operations/operations_part1.md` | Deployment / Operations / Terms | 169,833 | 1,163 KB |
| `operations/operations_part2.md` | Deployment / Operations / Terms | 111,499 | 805 KB |
| *(Deployment / Operations / Terms 記事数: 154)* | | | |
| `other/other_part1.md` | Other | 10,389 | 70 KB |
| *(Other 記事数: 18)* | | | |

## 注意

- 本ディレクトリは Zscaler の著作物の複製です。社内・個人利用の範囲で扱い、
  再配布しないでください。GitHub Pages の配信対象からは除外しています。
- 各記事は `<!-- ZS-ARTICLE {...} -->` マーカーで区切られています。差分更新が
  このマーカーを利用するため、ファイルを手で編集しないでください。
