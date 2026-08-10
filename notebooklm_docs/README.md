# NotebookLM 用 Zscaler ヘルプドキュメント

`help.zscaler.com` の全記事（sitemap.xml 掲載分）を機能カテゴリごとに
Markdown へまとめたものです。NotebookLM に読み込ませる用途を想定しています。

- 生成: `scripts/build_help_docs.py`
- 更新: `.github/workflows/notebooklm-weekly.yml`（毎週月曜 09:00 JST）
- 記事数: **4,175**
- ファイル数: **34** / 総語数: **4,059,321**
- 最終更新: 2026-08-10T01:47:41Z

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
| `zia/zia_part1.md` | ZIA — Internet & SaaS | 169,142 | 1,160 KB |
| `zia/zia_part2.md` | ZIA — Internet & SaaS | 172,098 | 1,170 KB |
| `zia/zia_part3.md` | ZIA — Internet & SaaS | 169,080 | 1,171 KB |
| `zia/zia_part4.md` | ZIA — Internet & SaaS | 168,412 | 1,171 KB |
| `zia/zia_part5.md` | ZIA — Internet & SaaS | 172,402 | 1,168 KB |
| `zia/zia_part6.md` | ZIA — Internet & SaaS | 174,466 | 1,164 KB |
| `zia/zia_part7.md` | ZIA — Internet & SaaS | 153,764 | 1,036 KB |
| *(ZIA — Internet & SaaS 記事数: 856)* | | | |
| `zpa/zpa_part1.md` | ZPA — Private Access | 164,493 | 1,166 KB |
| `zpa/zpa_part2.md` | ZPA — Private Access | 162,580 | 1,170 KB |
| `zpa/zpa_part3.md` | ZPA — Private Access | 157,757 | 1,153 KB |
| `zpa/zpa_part4.md` | ZPA — Private Access | 16,101 | 110 KB |
| *(ZPA — Private Access 記事数: 534)* | | | |
| `zdx/zdx_part1.md` | ZDX — Digital Experience Monitoring | 114,929 | 784 KB |
| *(ZDX — Digital Experience Monitoring 記事数: 130)* | | | |
| `zcc/zcc_part1.md` | ZCC — Zscaler Client Connector | 155,923 | 1,163 KB |
| `zcc/zcc_part2.md` | ZCC — Zscaler Client Connector | 38,909 | 279 KB |
| *(ZCC — Zscaler Client Connector 記事数: 255)* | | | |
| `api/api_part1.md` | API / SDK | 146,944 | 1,156 KB |
| `api/api_part2.md` | API / SDK | 136,697 | 1,169 KB |
| `api/api_part3.md` | API / SDK | 180,111 | 1,140 KB |
| *(API / SDK 記事数: 335)* | | | |
| `deception/deception_part1.md` | Zscaler Deception | 162,113 | 1,168 KB |
| `deception/deception_part2.md` | Zscaler Deception | 12,357 | 90 KB |
| *(Zscaler Deception 記事数: 322)* | | | |
| `data_security/data_security_part1.md` | Data Security — DSPM | 171,032 | 1,168 KB |
| `data_security/data_security_part2.md` | Data Security — DSPM | 5,435 | 37 KB |
| *(Data Security — DSPM 記事数: 196)* | | | |
| `exposure_mgmt/exposure_mgmt_part1.md` | Risk & Exposure Management | 172,606 | 1,170 KB |
| `exposure_mgmt/exposure_mgmt_part2.md` | Risk & Exposure Management | 165,359 | 1,164 KB |
| `exposure_mgmt/exposure_mgmt_part3.md` | Risk & Exposure Management | 6,403 | 43 KB |
| *(Risk & Exposure Management 記事数: 420)* | | | |
| `branch/branch_part1.md` | Branch / Cellular / Cloud Connector | 155,056 | 1,171 KB |
| `branch/branch_part2.md` | Branch / Cellular / Cloud Connector | 77,565 | 536 KB |
| *(Branch / Cellular / Cloud Connector 記事数: 268)* | | | |
| `partners/partners_part1.md` | Technology Partners | 28,246 | 271 KB |
| *(Technology Partners 記事数: 146)* | | | |
| `soc_workbench/soc_workbench_part1.md` | SOC Workbench | 43,000 | 303 KB |
| *(SOC Workbench 記事数: 61)* | | | |
| `platform/platform_part1.md` | Unified Platform / Admin / Logs | 165,199 | 1,167 KB |
| `platform/platform_part2.md` | Unified Platform / Admin / Logs | 95,788 | 643 KB |
| *(Unified Platform / Admin / Logs 記事数: 378)* | | | |
| `ai_security/ai_security_part1.md` | AI Security | 48,534 | 325 KB |
| *(AI Security 記事数: 70)* | | | |
| `browser/browser_part1.md` | Zero Trust Browser | 20,040 | 157 KB |
| *(Zero Trust Browser 記事数: 52)* | | | |
| `operations/operations_part1.md` | Deployment / Operations / Terms | 166,374 | 1,133 KB |
| `operations/operations_part2.md` | Deployment / Operations / Terms | 110,406 | 793 KB |
| *(Deployment / Operations / Terms 記事数: 152)* | | | |

## 注意

- 本ディレクトリは Zscaler の著作物の複製です。社内・個人利用の範囲で扱い、
  再配布しないでください。GitHub Pages の配信対象からは除外しています。
- 各記事は `<!-- ZS-ARTICLE {...} -->` マーカーで区切られています。差分更新が
  このマーカーを利用するため、ファイルを手で編集しないでください。
