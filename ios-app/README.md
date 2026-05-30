# Zscaler Hub — iOS App

Zscaler製品（ZIA・ZPA・ZDX）の統合ナレッジアプリ。公式ドキュメントを一元化し、AI検索・設定シミュレーター・履歴ログを提供します。

## 機能概要

| タブ | 機能 |
|---|---|
| 🔍 検索 | Claude AIによるZscaler技術の日本語Q&A |
| 📖 ドキュメント | 35件超のトピックをカテゴリ別に閲覧（オフライン対応） |
| ⚙️ シミュレーター | ZIA/ZPAの設定を仮定した机上検証 |
| 📋 ログ | 検索・シミュレーション履歴の永続保存 |
| ⚙️ 設定 | Anthropic APIキー管理 |

## 技術スタック

- **言語**: Swift 5.9
- **UI**: SwiftUI
- **データ**: SwiftData（iOS 17+）
- **AI**: Claude API（claude-opus-4-8）
- **最小iOS**: 17.0

## プロジェクト構成

```
ZscalerHub/
├── ZscalerHubApp.swift          # アプリエントリーポイント
├── MainTabView.swift            # タブナビゲーション
├── Models/
│   ├── SearchLogEntry.swift     # 検索ログ（SwiftData）
│   ├── SimulationLogEntry.swift # シミュレーションログ（SwiftData）
│   ├── KnowledgeTopic.swift     # ドキュメントトピックモデル
│   └── SimulationModels.swift   # シミュレーション用データ型
├── Services/
│   ├── AnthropicService.swift   # Claude API クライアント
│   ├── KnowledgeBaseService.swift # ローカルKBサービス
│   └── SimulationEngine.swift   # ポリシー評価エンジン
├── ViewModels/
│   ├── SearchViewModel.swift    # 検索画面のロジック
│   └── SimulatorViewModel.swift # シミュレーター画面のロジック
├── Views/
│   ├── Search/SearchView.swift  # AI検索画面
│   ├── Documentation/           # ドキュメントブラウザ
│   ├── Simulator/SimulatorView.swift # シミュレーター画面
│   ├── Logs/LogView.swift       # ログ閲覧画面
│   └── Settings/SettingsView.swift # 設定画面
└── Resources/
    └── knowledge_base.json      # 埋め込みZscalerナレッジベース（35トピック）
```

## Xcode セットアップ手順

1. Xcode 15.0 以上（macOS）を用意する
2. `File → New → Project` → iOS → App を選択
3. Product Name: `ZscalerHub`, Interface: SwiftUI, Storage: SwiftData
4. 生成されたデフォルトファイルを削除し、このディレクトリのすべての Swift ファイルをドラッグ&ドロップ
5. `knowledge_base.json` を `Resources/` グループに追加（Target membership を確認）
6. Deployment Target を iOS 17.0 に設定
7. ビルド & Run（実機またはシミュレーター）

## APIキー設定

1. [Anthropic Console](https://console.anthropic.com) でAPIキーを取得
2. アプリ内「設定」タブにAPIキーを入力して保存
3. AI検索機能が有効になる

## シミュレーター機能の説明

### ZIA URLアクセス検証

実際の環境を構築せずに、以下を仮定した机上検証が可能です：

- **URL**: アクセスしたいURL
- **ユーザーグループ**: IT管理者 / 一般ユーザー / 役員 / 外部委託
- **ロケーション**: 本社 / 支社 / リモート
- **時刻**: アクセスする時刻

設定したポリシーを順番に評価し、**どのルールにマッチして最終的に許可/ブロックされるか**をステップごとに表示します。

### ZPA アプリアクセス検証

- **アプリFQDN**: アクセス先のアプリケーション
- **ユーザーグループ・デバイスポスチャー**
- 設定したアクセスポリシーに基づいて評価

## ナレッジベース カバレッジ

| カテゴリ | トピック例 |
|---|---|
| ZIA（14件） | URL/SSLインスペクション・DLP・CASB・サンドボックス・認証・管理ポータル |
| ZPA（6件） | App Connector・アプリセグメント・アクセスポリシー・IdP連携・マシントンネル |
| ZDX（3件） | プローブ・アラート・概要 |
| アーキテクチャ（3件） | クラウド基盤・GRE/IPSec・ゼロトラスト |
| ベストプラクティス（3件） | 展開・SSL運用・ポリシー管理 |

## App Store 公開に必要な追加作業

- [ ] App アイコン（1024×1024 px）の作成
- [ ] スクリーンショット（iPhone / iPad 各サイズ）
- [ ] プライバシーポリシーURL
- [ ] App Store Connect での App ID 登録
- [ ] プロビジョニングプロファイルの設定
- [ ] APIキーの安全な保管（本番環境ではKeychain使用を推奨）
- [ ] `PrivacyInfo.xcprivacy`（プライバシーマニフェスト）の追加

## ライセンス

社内利用目的。Anthropic利用規約およびZscaler利用規約に従って使用してください。
