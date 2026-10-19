# NotebookLM 用 Zscaler ヘルプドキュメント

`help.zscaler.com` の全記事（sitemap.xml 掲載分）を機能カテゴリごとに
Markdown へまとめたものです。NotebookLM に読み込ませる用途を想定しています。

- 生成: `scripts/build_help_docs.py`
- 更新: `.github/workflows/notebooklm-weekly.yml`（毎週月曜 09:00 JST）
- 記事数: **4,171**
- ファイル数: **34** / 総語数: **4,116,255**
- 最終更新: 2026-10-19T08:07:35Z

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
| `zia/zia_part1.md` | ZIA — Internet & SaaS | 170,771 | 1,168 KB |
| `zia/zia_part2.md` | ZIA — Internet & SaaS | 168,967 | 1,158 KB |
| `zia/zia_part3.md` | ZIA — Internet & SaaS | 169,179 | 1,171 KB |
| `zia/zia_part4.md` | ZIA — Internet & SaaS | 165,534 | 1,172 KB |
| `zia/zia_part5.md` | ZIA — Internet & SaaS | 172,577 | 1,166 KB |
| `zia/zia_part6.md` | ZIA — Internet & SaaS | 169,798 | 1,138 KB |
| `zia/zia_part7.md` | ZIA — Internet & SaaS | 173,126 | 1,153 KB |
| `zia/zia_part8.md` | ZIA — Internet & SaaS | 21,518 | 150 KB |
| *(ZIA — Internet & SaaS 記事数: 876)* | | | |
| `zpa/zpa_part1.md` | ZPA — Private Access | 163,681 | 1,163 KB |
| `zpa/zpa_part2.md` | ZPA — Private Access | 160,550 | 1,162 KB |
| `zpa/zpa_part3.md` | ZPA — Private Access | 156,971 | 1,168 KB |
| `zpa/zpa_part4.md` | ZPA — Private Access | 49,724 | 335 KB |
| *(ZPA — Private Access 記事数: 570)* | | | |
| `zdx/zdx_part1.md` | ZDX — Digital Experience Monitoring | 115,383 | 790 KB |
| *(ZDX — Digital Experience Monitoring 記事数: 128)* | | | |
| `zcc/zcc_part1.md` | ZCC — Zscaler Client Connector | 155,071 | 1,165 KB |
| `zcc/zcc_part2.md` | ZCC — Zscaler Client Connector | 45,958 | 332 KB |
| *(ZCC — Zscaler Client Connector 記事数: 263)* | | | |
| `api/api_part1.md` | API / SDK | 145,634 | 1,156 KB |
| `api/api_part2.md` | API / SDK | 140,421 | 1,165 KB |
| `api/api_part3.md` | API / SDK | 173,195 | 1,094 KB |
| *(API / SDK 記事数: 295)* | | | |
| `deception/deception_part1.md` | Zscaler Deception | 162,147 | 1,170 KB |
| `deception/deception_part2.md` | Zscaler Deception | 13,680 | 99 KB |
| *(Zscaler Deception 記事数: 324)* | | | |
| `data_security/data_security_part1.md` | Data Security — DSPM | 169,995 | 1,168 KB |
| `data_security/data_security_part2.md` | Data Security — DSPM | 15,426 | 109 KB |
| *(Data Security — DSPM 記事数: 201)* | | | |
| `exposure_mgmt/exposure_mgmt_part1.md` | Risk & Exposure Management | 171,774 | 1,170 KB |
| `exposure_mgmt/exposure_mgmt_part2.md` | Risk & Exposure Management | 143,100 | 1,013 KB |
| *(Risk & Exposure Management 記事数: 385)* | | | |
| `branch/branch_part1.md` | Branch / Cellular / Cloud Connector | 152,642 | 1,160 KB |
| `branch/branch_part2.md` | Branch / Cellular / Cloud Connector | 86,095 | 601 KB |
| *(Branch / Cellular / Cloud Connector 記事数: 273)* | | | |
| `partners/partners_part1.md` | Technology Partners | 28,297 | 272 KB |
| *(Technology Partners 記事数: 146)* | | | |
| `platform/platform_part1.md` | Unified Platform / Admin / Logs | 160,625 | 1,154 KB |
| `platform/platform_part2.md` | Unified Platform / Admin / Logs | 116,093 | 816 KB |
| *(Unified Platform / Admin / Logs 記事数: 396)* | | | |
| `ai_security/ai_security_part1.md` | AI Security | 67,651 | 455 KB |
| *(AI Security 記事数: 88)* | | | |
| `browser/browser_part1.md` | Zero Trust Browser | 21,341 | 170 KB |
| *(Zero Trust Browser 記事数: 54)* | | | |
| `operations/operations_part1.md` | Deployment / Operations / Terms | 166,993 | 1,146 KB |
| `operations/operations_part2.md` | Deployment / Operations / Terms | 112,098 | 809 KB |
| *(Deployment / Operations / Terms 記事数: 154)* | | | |
| `other/other_part1.md` | Other | 10,240 | 70 KB |
| *(Other 記事数: 18)* | | | |

## 注意

- 本ディレクトリは Zscaler の著作物の複製です。社内・個人利用の範囲で扱い、
  再配布しないでください。GitHub Pages の配信対象からは除外しています。
- 各記事は `<!-- ZS-ARTICLE {...} -->` マーカーで区切られています。差分更新が
  このマーカーを利用するため、ファイルを手で編集しないでください。
