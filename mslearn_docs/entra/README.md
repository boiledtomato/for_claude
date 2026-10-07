# NotebookLM 用 Microsoft Learn — Microsoft Entra

`https://learn.microsoft.com/ja-jp/entra/` 配下の全ページ (sitemap 掲載分) を、機能カテゴリごとの
Markdown にまとめたものです。

- 生成: `scripts/build_mslearn_docs.py --docset entra`
- 更新: `.github/workflows/mslearn-monthly.yml` (毎月 1 日。全ページを再確認し、結果をメールで通知)
- NotebookLM ノートブック: `MSLearn_entra`
- ページ数: **20** / ファイル数: **2**
- 最終確認: 2026-10-07T00:42:33Z

## ファイル一覧

| ファイル | カテゴリ | ページ数 | 文字数 |
|---|---|---|---|
| `mslearn_entra_developer_part1.md` | 開発者向け (Identity Platform・MSAL・Microsoft.Identity.Web) | 19 | 104,917 |
| `mslearn_entra_fundamentals_part1.md` | 基礎・アーキテクチャ・標準・その他 | 1 | 4,818 |

## 注意

- 本文は Microsoft Learn の内容の複製です (多くは CC BY 4.0、日本語版は機械翻訳を含む)。
  出典 URL を各ページの `Source:` 行に残しています。GitHub Pages の配信対象からは除外しています。
- 各ページは `<!-- MSL-PAGE {...} -->` マーカーで区切られています。月次更新が
  このマーカーを使うため、ファイルを手で編集しないでください。
