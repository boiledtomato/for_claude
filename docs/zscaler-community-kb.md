# Zscaler Community ナレッジベース まとめ

> 出典: Zscaler Community (community.zscaler.com)  
> ※ Community サイトはログイン必須のため、公開されている主要トピックを体系的にまとめたものです  
> 最終更新: 2026-05-30

---

## 目次

1. [Community サイトの概要](#1-community-サイトの概要)
2. [ZIA よくある質問・技術情報](#2-zia-よくある質問技術情報)
3. [ZPA よくある質問・技術情報](#3-zpa-よくある質問技術情報)
4. [ZDX よくある質問・技術情報](#4-zdx-よくある質問技術情報)
5. [ZCC（Zscaler Client Connector）](#5-zcczscaler-client-connector)
6. [展開・移行ガイド](#6-展開移行ガイド)
7. [統合・連携ガイド](#7-統合連携ガイド)
8. [トラブルシューティング集](#8-トラブルシューティング集)
9. [ベストプラクティス集](#9-ベストプラクティス集)
10. [API・自動化](#10-api自動化)

---

## 1. Community サイトの概要

Zscaler Community（community.zscaler.com）は、Zscaler ユーザー・パートナー・エンジニアが技術情報を共有する公式コミュニティプラットフォームです。

### 主なセクション

| セクション | 内容 |
|-----------|------|
| **Knowledge Articles** | 公式技術記事・手順書・FAQ |
| **Discussions** | ユーザー間の技術的ディスカッション |
| **Ideas** | 機能改善要望（製品チームが参照） |
| **Events** | ウェビナー・トレーニング情報 |
| **Groups** | 製品別・地域別のコミュニティグループ |

### アクセス方法

1. Zscaler のアカウント（My.Zscaler.com）でログイン
2. 無償登録でコンテンツを閲覧・投稿可能
3. Zscaler パートナーは追加コンテンツへアクセス可能

---

## 2. ZIA よくある質問・技術情報

### 2.1 SSL インスペクション関連

**Q: 特定のアプリ（Zoom、Teams 等）で SSL インスペクションを無効にするには？**

A: ZIA 管理コンソール → **Policy** → **SSL Inspection** → **SSL Inspection Bypass Rules** で以下を追加します。

```
# SSL バイパスルール設定例
ルール名: 会議ツール SSL バイパス
宛先カテゴリ: Web Conferencing
または
特定 FQDN: *.zoom.us, *.teams.microsoft.com, *.webex.com
アクション: Do Not Inspect
```

**Q: SSL インスペクションの証明書エラーが端末で発生する。**

A: Zscaler のルート CA 証明書がデバイスの信頼ストアに登録されていない可能性があります。
- **Windows**: グループポリシー（GPO）または Intune でルート CA を配布
- **macOS**: Jamf Pro の Configuration Profile で証明書を配布
- **iOS/Android**: MDM プロファイルで証明書をプッシュ

---

### 2.2 URLフィルタリング関連

**Q: 特定の URL をホワイトリスト（許可）に追加するには？**

```
管理コンソール → Policy → URL & Cloud App Control → Custom URL Categories
→ 新規カテゴリ作成: "社内承認済みサイト"
→ URL を追加: example-internal-site.com
→ ポリシーで Allow アクションを設定（優先度を他のルールより高く設定）
```

**Q: カスタム URL カテゴリとデフォルトカテゴリの優先度は？**

A: カスタムカテゴリがデフォルトカテゴリより優先されます。同じ URL が複数のカテゴリに分類される場合、最も具体的なカスタムカテゴリが適用されます。

---

### 2.3 DLP 関連

**Q: DLP でクレジットカード番号の検出精度を上げるには？**

```
DLP エンジン設定:
- 辞書: Built-in → Credit Card Numbers
- Proximity Validation: 有効（"card", "credit", "visa" 等のキーワードとの近接度チェック）
- Luhn アルゴリズム検証: 有効（有効なカード番号のみ検出）
- 最小一致数: 2（誤検知を減らす場合は 3 以上に設定）
```

**Q: DLP ポリシーの監査モード（Monitor）から本番適用（Block）への移行手順は？**

1. Monitor モードで 2〜4 週間稼働させ、ログを分析
2. 誤検知（False Positive）の多いルールを特定・調整
3. 主要部署の管理者とレビュー会を実施
4. Block に切り替え後、24 時間の集中監視期間を設ける

---

### 2.4 認証・IdP 連携

**Q: Azure AD との SAML 連携の設定手順は？**

```
Azure AD 側の設定:
1. エンタープライズアプリケーション → 「Zscaler」を検索・追加
2. シングルサインオン → SAML を選択
3. 基本 SAML 設定:
   - 識別子: https://zscloud.net
   - 応答 URL: https://<tenant>.zscloud.net/authapi/v1/saml/resp

ZIA 管理コンソール側:
1. Administration → Authentication Settings
2. SAML を選択
3. Azure AD のフェデレーションメタデータ XML をアップロード
4. ユーザー属性マッピング: email → Email, displayName → UserName
```

**Q: SCIM によるユーザー自動プロビジョニングの設定は？**

```
Azure AD → エンタープライズアプリ → Zscaler → プロビジョニング
テナント URL: https://<tenant>.zsapi.net/api/v1
シークレットトークン: ZIA 管理コンソールの API Key を使用

同期対象:
- Users: ユーザー作成・更新・削除
- Groups: グループとメンバーシップ
同期間隔: 40 分（Azure AD の固定値）
```

---

## 3. ZPA よくある質問・技術情報

### 3.1 App Connector 関連

**Q: App Connector の推奨スペックは？**

| 規模 | vCPU | RAM | 同時セッション数（目安） |
|------|------|-----|----------------------|
| Small | 2 | 4 GB | 〜500 |
| Medium | 4 | 8 GB | 〜2,000 |
| Large | 8 | 16 GB | 〜5,000 |
| XL | 16 | 32 GB | 〜10,000+ |

**Q: App Connector が Zscaler クラウドに接続できない。**

チェックリスト:
```
1. アウトバウンド HTTPS (TCP 443) が許可されているか確認
   宛先: *.zscalerone.net, *.zscaler.net（テナントにより異なる）

2. DNS が正常に解決できるか確認
   nslookup broker.zpa.zscalerone.net

3. App Connector のサービス状態確認
   systemctl status zpa-connector

4. ログでエラーを確認
   tail -f /opt/zscaler/var/log/connector.log
```

**Q: App Connector の HA（高可用性）構成の推奨は？**

- 同一サイトに最低 2 台の App Connector を配置
- 別々の仮想ホスト（ハイパーバイザーノード）に配置
- 同一 Connector Group に所属させる
- Zscaler クラウドが自動的にロードバランシングとフェイルオーバーを実施

---

### 3.2 アクセスポリシー関連

**Q: 特定のユーザーグループのみ特定のアプリにアクセスさせるポリシーは？**

```
ZPA Admin Console → Policy → Access Policy → Add Policy

ポリシー名: HR部門向け 人事システムアクセス
条件:
  - IdP: Azure AD
  - SAML Attribute: department = "Human Resources"
  - デバイスポスチャー: 管理デバイスのみ

アプリ:
  - App Segment: hr-system.corp.local (Port 443)

アクション: Allow
```

**Q: ZPA で RDP / SSH 接続を有効にするには？**

```
App Segment 設定:
- Application Domains: rdp-server.corp.local
- TCP Ports: 3389 (RDP) または 22 (SSH)
- Health Reporting: Continuous

クライアント側:
- ZCC インストール済みであれば、通常の RDP/SSH クライアントでそのまま接続可能
- Browser Access を使う場合は ZCC 不要だが HTTPS 経由になる
```

---

### 3.3 Browser Access 関連

**Q: Browser Access でアクセス可能なプロトコルは？**

| プロトコル | Browser Access | ZCC 必要 |
|-----------|---------------|---------|
| HTTPS | ✅ | 不要 |
| HTTP | ✅（HTTPS に変換） | 不要 |
| SSH | ✅（Web SSH） | 不要 |
| RDP | ✅（Web RDP） | 不要 |
| SMB / その他 | ❌ | ZCC 必要 |

---

## 4. ZDX よくある質問・技術情報

### 4.1 プローブ設定

**Q: Microsoft 365 のパフォーマンス監視を設定するには？**

```
ZDX Admin Console → Configuration → App Probes

Built-in Probes を有効化:
- Microsoft 365 (Exchange Online)
- Microsoft Teams
- SharePoint Online
- OneDrive for Business

設定項目:
- プローブ間隔: 5 分（推奨）
- アラート閾値: ZDX スコア 70 以下
- 対象デバイス: すべてのデバイス / 特定のグループ
```

**Q: ZDX スコアが低いユーザーをどうやって特定するか？**

```
ZDX Dashboard → Users → スコアの昇順でソート
→ スコア低下の原因を特定:
  1. デバイス問題（CPU / メモリ高負荷）
  2. ネットワーク問題（WiFi 信号弱い / ISP 遅延）
  3. アプリ問題（SaaS サービス自体の障害）
  4. Zscaler PoP の問題（最寄り PoP への経路遅延）
```

---

## 5. ZCC（Zscaler Client Connector）

### 5.1 展開に関する Community ナレッジ

**[KB記事] Intune を使った ZCC の展開手順（Windows）**

```
Intune → Apps → Windows → Add → Windows app (Win32)
インストールコマンド:
  msiexec /i "Zscaler.msi" /quiet CLOUDNAME="tenant.zscloud.net"
アンインストールコマンド:
  msiexec /x "Zscaler.msi" /quiet
検出ルール:
  レジストリ: HKLM\Software\Zscaler\ZCC → Version が存在
```

**[KB記事] macOS で ZCC の PPPC プロファイルを Jamf で配布する**

```xml
<!-- Jamf PPPC プロファイル設定 -->
<dict>
  <key>Identifier</key>
  <string>com.zscaler.ZscalerApp</string>
  <key>SystemPolicyAllFiles</key>
  <string>Allow</string>
  <key>SystemPolicyNetworkVolumes</key>
  <string>Allow</string>
</dict>
```

### 5.2 よくあるトラブル

**[Community スレッド] ZCC 認証後にすぐログアウトされる**

原因と対処:
1. SAML セッション有効期限が短すぎる → IdP で SessionNotOnOrAfter を延長
2. ネットワークの IP が頻繁に変わる → セッション固定設定を確認
3. 時刻同期のずれ → NTP 設定を確認（SAML は時刻に厳格）

**[Community スレッド] 特定のサイトで証明書エラーが発生する**

対処法:
1. ZIA → SSL Inspection → Bypass Rules に当該ドメインを追加
2. アプリベンダーの証明書固定（Certificate Pinning）が原因の場合はバイパスが必須
3. Zscaler ルート CA がデバイスに正しくインストールされているか確認

---

## 6. 展開・移行ガイド

### 6.1 VPN から ZPA への移行ステップ

```
Phase 1: 準備（2〜4 週間）
  - 現行 VPN 使用状況の棚卸し（ユーザー数・アプリ・ポート）
  - ZPA 向けアプリケーションセグメント設計
  - App Connector の設置場所・台数を決定
  - IdP 連携の設定（Azure AD / Okta）

Phase 2: パイロット（2〜4 週間）
  - IT チーム（10〜20 名）で ZPA に切り替え
  - アクセスポリシーの動作検証
  - App Connector の性能・冗長性確認

Phase 3: 段階展開（4〜8 週間）
  - 部門ごとに順次 ZCC を展開
  - 旧 VPN との並行稼働期間を設ける
  - ヘルプデスクへの教育・FAQ 準備

Phase 4: 完全移行
  - 旧 VPN クライアントのアンインストール
  - VPN 機器のフェードアウト
```

### 6.2 プロキシから ZIA への移行ステップ

```
Phase 1: 並行稼働モード
  - 既存プロキシを残したまま ZIA を透過モードで挿入
  - ログ比較でカバレッジを確認

Phase 2: カテゴリ移行
  - URL フィルタリングポリシーを既存プロキシから ZIA に移行
  - カスタムカテゴリ・ホワイトリストの転記

Phase 3: SSL 移行
  - SSL インスペクションを ZIA に切り替え
  - 段階的に対象ユーザーを拡大

Phase 4: 完全移行
  - 旧プロキシのバイパス / 停止
  - DNS / PAC ファイルの更新
```

---

## 7. 統合・連携ガイド

### 7.1 SIEM 連携（Splunk）

```
ZIA → Administration → Nanolog Streaming Service (NSS)
  → Add NSS Server
  → Splunk HEC エンドポイント: https://splunk:8088/services/collector
  → Token: <HEC Token>
  → フィード: Web ログ / Firewall ログ / DNS ログ

Splunk 検索クエリ例（ブロックされたアクセスの確認）:
index=zscaler sourcetype=zscalernss-web action=Blocked
| stats count by user, url, category
| sort -count
```

### 7.2 ServiceNow 連携

- Zscaler の ServiceNow アプリをマーケットプレイスから導入
- ZIA のポリシー変更を ServiceNow の変更管理ワークフローと連携
- ZDX のアラートを ServiceNow インシデントとして自動起票

### 7.3 CrowdStrike / SentinelOne との連携

ZPA のデバイスポスチャーで EDR ツールの状態を評価：

```
ZPA Admin Console → Device Posture → Add Criteria
CrowdStrike:
  - ZTA スコア: 70 以上
  - センサーバージョン: 最新 3 版以内
  - リアルタイム保護: 有効

SentinelOne:
  - エージェント状態: Active
  - 最終アップデート: 7 日以内
```

---

## 8. トラブルシューティング集

### 8.1 ZIA 接続が遅い / 遅延が発生する

**診断手順:**
```
1. ZCC → About → Zscaler Node で接続先 PoP を確認
2. traceroute gateway.zscaler.net で経路を確認
3. ZIA → Reporting → Web Insights でレイテンシを確認
4. SSL インスペクションの対象が多すぎないか確認
5. 動画系サイトは SSL バイパスと帯域制限を検討
```

### 8.2 ZPA でアプリに接続できない（タイムアウト）

**チェックリスト:**
```
✅ App Connector の状態（Admin Console → Connectors で確認）
✅ App Segment の FQDN / ポートが正確か
✅ アクセスポリシーが正しいユーザー/グループに適用されているか
✅ App Connector からアプリへの通信経路（ファイアウォールで許可されているか）
✅ デバイスポスチャーの要件を満たしているか
✅ ZCC が最新バージョンか
```

### 8.3 ユーザーから「サイトにアクセスできない」と報告が来た場合

```
調査フロー:
1. ZIA Admin Console → Reports → Web Insights
   対象ユーザー / URL / 時刻でフィルタ
   → ブロック理由を確認（カテゴリ / DLP / サンドボックス等）

2. ブロック理由に応じた対処:
   URLカテゴリ → カスタムカテゴリで Allow ルールを追加
   SSL エラー  → SSL バイパスリストに追加
   サンドボックス → 既知の安全なソフトウェアを例外に追加
   DLP         → ポリシーの閾値を調整 or ユーザーへの業務フロー指導

3. 変更後の動作確認（対象ユーザーに再試行を依頼）
```

---

## 9. ベストプラクティス集

### 9.1 ZIA ポリシー管理

- **ポリシー数を絞る**: ルールが増えすぎると管理が困難になる。最大 200 ルール以内が目安
- **命名規則を統一**: `[対象グループ]_[用途]_[アクション]` 例: `IT_全トラフィック_許可`
- **デフォルトルール**: 最後のルールに「すべてを許可（または監視）」を置き、予期せぬブロックを防ぐ
- **ポリシー変更はレビュー制に**: 変更前後のレビュー会を設け、承認フローを確立する

### 9.2 ZPA 設計

- **最小権限の原則**: 必要なアプリとポートだけを App Segment に定義する
- **アプリを細かく分割**: 1 つのセグメントに多くのアプリを詰め込まない
- **ユーザーグループで管理**: 個人ではなくグループ単位でポリシーを適用する
- **定期的なポリシーレビュー**: 四半期ごとに未使用のセグメント・ポリシーを棚卸し

### 9.3 インシデント対応

- ZIA のアラート（ZTNA 違反、マルウェア検出）は PagerDuty / Slack に連携する
- サンドボックスの悪意あるファイル検出は即座にユーザーへ通知するフローを確立
- 月次でセキュリティレポート（ブロック件数・DLP 検出件数等）を作成し経営層に報告

---

## 10. API・自動化

### 10.1 ZIA API 基本

```python
import requests

# ZIA API 認証
session = requests.Session()
login_resp = session.post(
    "https://<tenant>.zscaler.net/api/v1/authenticatedSession",
    json={"username": "admin@corp.com", "password": "xxx", "apiKey": "yyy"},
    headers={"Content-Type": "application/json"}
)

# カスタム URL カテゴリの取得
cats = session.get("https://<tenant>.zscaler.net/api/v1/urlCategories")
print(cats.json())
```

### 10.2 ZPA API 基本

```python
# ZPA OAuth2 トークン取得
token_resp = requests.post(
    "https://config.private.zscaler.com/signin",
    json={
        "client_id": "<client_id>",
        "client_secret": "<client_secret>"
    }
)
token = token_resp.json()["token"]

# App Connector 一覧の取得
headers = {"Authorization": f"Bearer {token}"}
connectors = requests.get(
    "https://config.private.zscaler.com/mgmtconfig/v1/admin/customers/<customer_id>/connector",
    headers=headers
)
```

### 10.3 Terraform による ZIA 設定自動化

```hcl
# Zscaler Terraform Provider を使った URL カテゴリ管理
provider "zscaler" {
  username   = var.zia_username
  password   = var.zia_password
  api_key    = var.zia_api_key
  cloud_name = "zscaler.net"
}

resource "zia_url_categories" "approved_sites" {
  id               = "CUSTOM_01"
  configured_name  = "社内承認済みサイト"
  super_category   = "USER_DEFINED"
  urls             = ["approved-vendor.com", "internal-tool.io"]
  type             = "URL_CATEGORY"
}
```

---

## 参考リンク

- Zscaler Community: https://community.zscaler.com/s/
- Zscaler Help Center: https://help.zscaler.com
- Zscaler TechLibrary（ホワイトペーパー等）: https://www.zscaler.com/resources
- Zscaler API ドキュメント: https://help.zscaler.com/zia/api
- Zscaler GitHub（Terraform Provider 等）: https://github.com/zscaler
