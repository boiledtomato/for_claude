# NotebookLM 用 Zscaler ヘルプドキュメント

`help.zscaler.com` の全記事（sitemap.xml 掲載分）を機能カテゴリごとに
Markdown へまとめたものです。NotebookLM に読み込ませる用途を想定しています。

- 生成: `scripts/build_help_docs.py`
- 更新: `.github/workflows/notebooklm-weekly.yml`（毎週月曜 09:00 JST）
- 記事数: **4,208**
- ファイル数: **34** / 総語数: **4,146,872**
- 最終更新: 2026-11-16T10:10:55Z

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
| `zia/zia_part1.md` | ZIA — Internet & SaaS | 171,007 | 1,170 KB |
| `zia/zia_part2.md` | ZIA — Internet & SaaS | 169,625 | 1,169 KB |
| `zia/zia_part3.md` | ZIA — Internet & SaaS | 168,356 | 1,171 KB |
| `zia/zia_part4.md` | ZIA — Internet & SaaS | 163,280 | 1,158 KB |
| `zia/zia_part5.md` | ZIA — Internet & SaaS | 171,562 | 1,162 KB |
| `zia/zia_part6.md` | ZIA — Internet & SaaS | 165,362 | 1,110 KB |
| `zia/zia_part7.md` | ZIA — Internet & SaaS | 173,545 | 1,158 KB |
| `zia/zia_part8.md` | ZIA — Internet & SaaS | 25,520 | 177 KB |
| *(ZIA — Internet & SaaS 記事数: 885)* | | | |
| `zpa/zpa_part1.md` | ZPA — Private Access | 162,902 | 1,161 KB |
| `zpa/zpa_part2.md` | ZPA — Private Access | 158,933 | 1,149 KB |
| `zpa/zpa_part3.md` | ZPA — Private Access | 154,138 | 1,155 KB |
| `zpa/zpa_part4.md` | ZPA — Private Access | 45,044 | 301 KB |
| *(ZPA — Private Access 記事数: 517)* | | | |
| `zdx/zdx_part1.md` | ZDX — Digital Experience Monitoring | 111,572 | 763 KB |
| *(ZDX — Digital Experience Monitoring 記事数: 124)* | | | |
| `zcc/zcc_part1.md` | ZCC — Zscaler Client Connector | 155,146 | 1,168 KB |
| `zcc/zcc_part2.md` | ZCC — Zscaler Client Connector | 53,886 | 389 KB |
| *(ZCC — Zscaler Client Connector 記事数: 267)* | | | |
| `api/api_part1.md` | API / SDK | 147,799 | 1,167 KB |
| `api/api_part2.md` | API / SDK | 140,421 | 1,165 KB |
| `api/api_part3.md` | API / SDK | 175,554 | 1,108 KB |
| *(API / SDK 記事数: 299)* | | | |
| `deception/deception_part1.md` | Zscaler Deception | 162,098 | 1,170 KB |
| `deception/deception_part2.md` | Zscaler Deception | 13,689 | 99 KB |
| *(Zscaler Deception 記事数: 324)* | | | |
| `data_security/data_security_part1.md` | Data Security — DSPM | 170,464 | 1,171 KB |
| `data_security/data_security_part2.md` | Data Security — DSPM | 14,675 | 103 KB |
| *(Data Security — DSPM 記事数: 201)* | | | |
| `exposure_mgmt/exposure_mgmt_part1.md` | Risk & Exposure Management | 170,264 | 1,161 KB |
| `exposure_mgmt/exposure_mgmt_part2.md` | Risk & Exposure Management | 149,255 | 1,058 KB |
| *(Risk & Exposure Management 記事数: 391)* | | | |
| `branch/branch_part1.md` | Branch / Cellular / Cloud Connector | 69,891 | 488 KB |
| *(Branch / Cellular / Cloud Connector 記事数: 93)* | | | |
| `partners/partners_part1.md` | Technology Partners | 28,119 | 270 KB |
| *(Technology Partners 記事数: 146)* | | | |
| `platform/platform_part1.md` | Unified Platform / Admin / Logs | 162,114 | 1,168 KB |
| `platform/platform_part2.md` | Unified Platform / Admin / Logs | 116,826 | 826 KB |
| *(Unified Platform / Admin / Logs 記事数: 393)* | | | |
| `ai_security/ai_security_part1.md` | AI Security | 71,235 | 479 KB |
| *(AI Security 記事数: 92)* | | | |
| `browser/browser_part1.md` | Zero Trust Browser | 23,017 | 182 KB |
| *(Zero Trust Browser 記事数: 58)* | | | |
| `operations/operations_part1.md` | Deployment / Operations / Terms | 166,973 | 1,146 KB |
| `operations/operations_part2.md` | Deployment / Operations / Terms | 112,530 | 811 KB |
| *(Deployment / Operations / Terms 記事数: 154)* | | | |
| `other/other_part1.md` | Other | 151,291 | 1,166 KB |
| `other/other_part2.md` | Other | 50,779 | 350 KB |
| *(Other 記事数: 264)* | | | |

## 注意

- 本ディレクトリは Zscaler の著作物の複製です。社内・個人利用の範囲で扱い、
  再配布しないでください。GitHub Pages の配信対象からは除外しています。
- 各記事は `<!-- ZS-ARTICLE {...} -->` マーカーで区切られています。差分更新が
  このマーカーを利用するため、ファイルを手で編集しないでください。
