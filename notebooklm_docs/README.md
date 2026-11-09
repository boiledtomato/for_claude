# NotebookLM 用 Zscaler ヘルプドキュメント

`help.zscaler.com` の全記事（sitemap.xml 掲載分）を機能カテゴリごとに
Markdown へまとめたものです。NotebookLM に読み込ませる用途を想定しています。

- 生成: `scripts/build_help_docs.py`
- 更新: `.github/workflows/notebooklm-weekly.yml`（毎週月曜 09:00 JST）
- 記事数: **4,191**
- ファイル数: **34** / 総語数: **4,127,527**
- 最終更新: 2026-11-09T10:16:30Z

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
| `zia/zia_part1.md` | ZIA — Internet & SaaS | 171,082 | 1,170 KB |
| `zia/zia_part2.md` | ZIA — Internet & SaaS | 169,585 | 1,167 KB |
| `zia/zia_part3.md` | ZIA — Internet & SaaS | 167,896 | 1,169 KB |
| `zia/zia_part4.md` | ZIA — Internet & SaaS | 165,431 | 1,169 KB |
| `zia/zia_part5.md` | ZIA — Internet & SaaS | 169,686 | 1,151 KB |
| `zia/zia_part6.md` | ZIA — Internet & SaaS | 175,377 | 1,169 KB |
| `zia/zia_part7.md` | ZIA — Internet & SaaS | 164,155 | 1,103 KB |
| `zia/zia_part8.md` | ZIA — Internet & SaaS | 21,544 | 150 KB |
| *(ZIA — Internet & SaaS 記事数: 881)* | | | |
| `zpa/zpa_part1.md` | ZPA — Private Access | 162,253 | 1,157 KB |
| `zpa/zpa_part2.md` | ZPA — Private Access | 157,759 | 1,141 KB |
| `zpa/zpa_part3.md` | ZPA — Private Access | 153,962 | 1,154 KB |
| `zpa/zpa_part4.md` | ZPA — Private Access | 45,038 | 301 KB |
| *(ZPA — Private Access 記事数: 514)* | | | |
| `zdx/zdx_part1.md` | ZDX — Digital Experience Monitoring | 110,183 | 754 KB |
| *(ZDX — Digital Experience Monitoring 記事数: 122)* | | | |
| `zcc/zcc_part1.md` | ZCC — Zscaler Client Connector | 154,448 | 1,159 KB |
| `zcc/zcc_part2.md` | ZCC — Zscaler Client Connector | 50,560 | 366 KB |
| *(ZCC — Zscaler Client Connector 記事数: 264)* | | | |
| `api/api_part1.md` | API / SDK | 147,799 | 1,167 KB |
| `api/api_part2.md` | API / SDK | 140,421 | 1,165 KB |
| `api/api_part3.md` | API / SDK | 175,554 | 1,108 KB |
| *(API / SDK 記事数: 299)* | | | |
| `deception/deception_part1.md` | Zscaler Deception | 162,036 | 1,169 KB |
| `deception/deception_part2.md` | Zscaler Deception | 13,686 | 99 KB |
| *(Zscaler Deception 記事数: 324)* | | | |
| `data_security/data_security_part1.md` | Data Security — DSPM | 170,548 | 1,172 KB |
| `data_security/data_security_part2.md` | Data Security — DSPM | 14,685 | 103 KB |
| *(Data Security — DSPM 記事数: 201)* | | | |
| `exposure_mgmt/exposure_mgmt_part1.md` | Risk & Exposure Management | 170,191 | 1,160 KB |
| `exposure_mgmt/exposure_mgmt_part2.md` | Risk & Exposure Management | 148,808 | 1,054 KB |
| *(Risk & Exposure Management 記事数: 391)* | | | |
| `branch/branch_part1.md` | Branch / Cellular / Cloud Connector | 69,891 | 488 KB |
| *(Branch / Cellular / Cloud Connector 記事数: 93)* | | | |
| `partners/partners_part1.md` | Technology Partners | 28,205 | 271 KB |
| *(Technology Partners 記事数: 146)* | | | |
| `platform/platform_part1.md` | Unified Platform / Admin / Logs | 160,606 | 1,156 KB |
| `platform/platform_part2.md` | Unified Platform / Admin / Logs | 111,414 | 790 KB |
| *(Unified Platform / Admin / Logs 記事数: 390)* | | | |
| `ai_security/ai_security_part1.md` | AI Security | 71,231 | 479 KB |
| *(AI Security 記事数: 92)* | | | |
| `browser/browser_part1.md` | Zero Trust Browser | 22,270 | 176 KB |
| *(Zero Trust Browser 記事数: 56)* | | | |
| `operations/operations_part1.md` | Deployment / Operations / Terms | 166,985 | 1,146 KB |
| `operations/operations_part2.md` | Deployment / Operations / Terms | 112,542 | 811 KB |
| *(Deployment / Operations / Terms 記事数: 154)* | | | |
| `other/other_part1.md` | Other | 151,330 | 1,166 KB |
| `other/other_part2.md` | Other | 50,366 | 347 KB |
| *(Other 記事数: 264)* | | | |

## 注意

- 本ディレクトリは Zscaler の著作物の複製です。社内・個人利用の範囲で扱い、
  再配布しないでください。GitHub Pages の配信対象からは除外しています。
- 各記事は `<!-- ZS-ARTICLE {...} -->` マーカーで区切られています。差分更新が
  このマーカーを利用するため、ファイルを手で編集しないでください。
