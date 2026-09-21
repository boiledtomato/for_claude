# NotebookLM 用 Zscaler ヘルプドキュメント

`help.zscaler.com` の全記事（sitemap.xml 掲載分）を機能カテゴリごとに
Markdown へまとめたものです。NotebookLM に読み込ませる用途を想定しています。

- 生成: `scripts/build_help_docs.py`
- 更新: `.github/workflows/notebooklm-weekly.yml`（毎週月曜 09:00 JST）
- 記事数: **4,152**
- ファイル数: **34** / 総語数: **4,060,342**
- 最終更新: 2026-09-14T03:38:47Z

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
| `zia/zia_part1.md` | ZIA — Internet & SaaS | 170,473 | 1,169 KB |
| `zia/zia_part2.md` | ZIA — Internet & SaaS | 172,319 | 1,172 KB |
| `zia/zia_part3.md` | ZIA — Internet & SaaS | 167,750 | 1,163 KB |
| `zia/zia_part4.md` | ZIA — Internet & SaaS | 168,600 | 1,172 KB |
| `zia/zia_part5.md` | ZIA — Internet & SaaS | 172,852 | 1,172 KB |
| `zia/zia_part6.md` | ZIA — Internet & SaaS | 175,550 | 1,170 KB |
| `zia/zia_part7.md` | ZIA — Internet & SaaS | 153,186 | 1,031 KB |
| *(ZIA — Internet & SaaS 記事数: 853)* | | | |
| `zpa/zpa_part1.md` | ZPA — Private Access | 164,339 | 1,163 KB |
| `zpa/zpa_part2.md` | ZPA — Private Access | 161,337 | 1,165 KB |
| `zpa/zpa_part3.md` | ZPA — Private Access | 156,863 | 1,158 KB |
| `zpa/zpa_part4.md` | ZPA — Private Access | 32,634 | 219 KB |
| *(ZPA — Private Access 記事数: 548)* | | | |
| `zdx/zdx_part1.md` | ZDX — Digital Experience Monitoring | 117,197 | 799 KB |
| *(ZDX — Digital Experience Monitoring 記事数: 131)* | | | |
| `zcc/zcc_part1.md` | ZCC — Zscaler Client Connector | 156,174 | 1,165 KB |
| `zcc/zcc_part2.md` | ZCC — Zscaler Client Connector | 39,026 | 280 KB |
| *(ZCC — Zscaler Client Connector 記事数: 255)* | | | |
| `api/api_part1.md` | API / SDK | 148,066 | 1,167 KB |
| `api/api_part2.md` | API / SDK | 137,085 | 1,171 KB |
| `api/api_part3.md` | API / SDK | 184,920 | 1,168 KB |
| *(API / SDK 記事数: 338)* | | | |
| `deception/deception_part1.md` | Zscaler Deception | 162,555 | 1,170 KB |
| `deception/deception_part2.md` | Zscaler Deception | 11,930 | 88 KB |
| *(Zscaler Deception 記事数: 322)* | | | |
| `data_security/data_security_part1.md` | Data Security — DSPM | 170,799 | 1,166 KB |
| `data_security/data_security_part2.md` | Data Security — DSPM | 11,348 | 79 KB |
| *(Data Security — DSPM 記事数: 201)* | | | |
| `exposure_mgmt/exposure_mgmt_part1.md` | Risk & Exposure Management | 170,603 | 1,165 KB |
| `exposure_mgmt/exposure_mgmt_part2.md` | Risk & Exposure Management | 122,938 | 871 KB |
| *(Risk & Exposure Management 記事数: 360)* | | | |
| `branch/branch_part1.md` | Branch / Cellular / Cloud Connector | 152,654 | 1,157 KB |
| `branch/branch_part2.md` | Branch / Cellular / Cloud Connector | 83,299 | 576 KB |
| *(Branch / Cellular / Cloud Connector 記事数: 271)* | | | |
| `partners/partners_part1.md` | Technology Partners | 28,238 | 271 KB |
| *(Technology Partners 記事数: 146)* | | | |
| `soc_workbench/soc_workbench_part1.md` | SOC Workbench | 10,520 | 72 KB |
| *(SOC Workbench 記事数: 0)* | | | |
| `platform/platform_part1.md` | Unified Platform / Admin / Logs | 165,109 | 1,168 KB |
| `platform/platform_part2.md` | Unified Platform / Admin / Logs | 123,492 | 839 KB |
| *(Unified Platform / Admin / Logs 記事数: 422)* | | | |
| `ai_security/ai_security_part1.md` | AI Security | 58,866 | 396 KB |
| *(AI Security 記事数: 80)* | | | |
| `browser/browser_part1.md` | Zero Trust Browser | 20,594 | 164 KB |
| *(Zero Trust Browser 記事数: 53)* | | | |
| `operations/operations_part1.md` | Deployment / Operations / Terms | 167,181 | 1,143 KB |
| `operations/operations_part2.md` | Deployment / Operations / Terms | 111,456 | 804 KB |
| *(Deployment / Operations / Terms 記事数: 154)* | | | |
| `other/other_part1.md` | Other | 10,389 | 70 KB |
| *(Other 記事数: 18)* | | | |

## 注意

- 本ディレクトリは Zscaler の著作物の複製です。社内・個人利用の範囲で扱い、
  再配布しないでください。GitHub Pages の配信対象からは除外しています。
- 各記事は `<!-- ZS-ARTICLE {...} -->` マーカーで区切られています。差分更新が
  このマーカーを利用するため、ファイルを手で編集しないでください。
