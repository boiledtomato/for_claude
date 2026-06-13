# Zscaler Internet Access (ZIA) 完全技術ガイド

> 対象バージョン: ZIA 最新安定版準拠  
> 最終更新: 2026-06-12

---

## 目次

1. [ZIA とは・アーキテクチャ](#1-zia-とはアーキテクチャ)
2. [ロケーションと転送設定](#2-ロケーションと転送設定)
3. [認証](#3-認証)
4. [URL フィルタリング](#4-url-フィルタリング)
5. [SSL インスペクション](#5-ssl-インスペクション)
6. [クラウドファイアウォール](#6-クラウドファイアウォール)
7. [DLP（データ損失防止）](#7-dlpデータ損失防止)
8. [クラウドサンドボックス](#8-クラウドサンドボックス)
9. [CASB](#9-casb)
10. [帯域制御・QoS](#10-帯域制御qos)
11. [DNS セキュリティ](#11-dns-セキュリティ)
12. [ログ・レポート・SIEM 連携](#12-ログレポートsiem-連携)
13. [管理者権限・テナント管理](#13-管理者権限テナント管理)
14. [ZIA API と自動化](#14-zia-api-と自動化)
15. [よくある障害・FAQ](#15-よくある障害faq)
16. [展開ベストプラクティス](#16-展開ベストプラクティス)

---

## 1. ZIA とは・アーキテクチャ

### 1.1 ZIA の概要

**Zscaler Internet Access（ZIA）** は、クラウドネイティブなセキュアウェブゲートウェイ（SWG）です。すべてのインターネット向けトラフィックを Zscaler のグローバルクラウドを経由させることで、場所を問わずエンタープライズレベルのセキュリティを適用します。

主な機能：

| 機能カテゴリ | 内容 |
|------------|------|
| URL フィルタリング | 200+ カテゴリによる Web アクセス制御 |
| SSL インスペクション | HTTPS トラフィックの復号・検査 |
| クラウドファイアウォール | L4〜L7 のネットワーク制御 |
| DLP | データ漏洩防止（ファイル・コンテンツ検査） |
| クラウドサンドボックス | ゼロデイマルウェアの動的解析 |
| CASB | SaaS アプリの可視化と制御 |
| DNS セキュリティ | 悪意ある DNS クエリのブロック |

### 1.2 Zero Trust Exchange との関係

ZIA は Zscaler Zero Trust Exchange（ZTE）プラットフォームの一部です。ZTE は世界 150+ か所の PoP（Point of Presence）で構成され、ユーザーのトラフィックを最寄りの PoP に誘導してインライン検査を行います。

```
[ ユーザー / デバイス ]
        │
        ▼
[ 最寄りの Zscaler PoP ]
  ├─ 認証・ユーザー識別
  ├─ URL フィルタリング
  ├─ SSL インスペクション（復号）
  ├─ クラウドファイアウォール
  ├─ DLP エンジン
  ├─ サンドボックス（ファイル解析）
  └─ CASB
        │
        ▼
[ インターネット / SaaS ]
```

### 1.3 インライン検査の処理順序

ZIA では以下の順序でトラフィックが処理されます：

1. **認証** — ユーザー・デバイスの識別
2. **URL フィルタリング** — カテゴリ・ポリシー評価
3. **SSL インスペクション** — HTTPS の復号判断
4. **クラウドファイアウォール** — L4/L7 ルール評価
5. **DLP** — データ内容の検査
6. **クラウドサンドボックス** — ファイルの動的解析
7. **CASB** — SaaS アクティビティ制御

---

## 2. ロケーションと転送設定

### 2.1 ロケーションの概念

ZIA では「ロケーション」を使ってオフィスや拠点ごとの設定を管理します。

| 種別 | 説明 |
|------|------|
| ロケーション | 拠点単位の設定（例：東京本社） |
| サブロケーション | ロケーション内のセグメント（例：開発部門フロア） |

### 2.2 GRE トンネル設定

GRE トンネルはルーターから Zscaler PoP へトラフィックを転送する方法です。

```
# Cisco IOS - GRE トンネル設定例
interface Tunnel0
 description Zscaler PoP Primary
 ip address 10.0.0.1 255.255.255.252
 tunnel source GigabitEthernet0/0
 tunnel destination <Zscaler PoP IP>
 ip mtu 1400
 ip tcp adjust-mss 1360

! ポリシーベースルーティングでHTTP/HTTPSをトンネルへ
ip access-list extended ZIA_TRAFFIC
 permit tcp any any eq 80
 permit tcp any any eq 443

route-map ZIA_PBR permit 10
 match ip address ZIA_TRAFFIC
 set interface Tunnel0

interface GigabitEthernet0/1
 description LAN Interface
 ip policy route-map ZIA_PBR
```

```
# バックアップ GRE トンネル（冗長化）
interface Tunnel1
 description Zscaler PoP Secondary
 ip address 10.0.0.5 255.255.255.252
 tunnel source GigabitEthernet0/0
 tunnel destination <Zscaler PoP Secondary IP>
 ip mtu 1400
```

### 2.3 IPSec トンネル設定

```
# Cisco IOS - IPSec 設定例
crypto isakmp policy 10
 encr aes 256
 hash sha256
 authentication pre-share
 group 14
 lifetime 86400

crypto isakmp key <PSK> address <Zscaler PoP IP>

crypto ipsec transform-set ZIA_TRANSFORM esp-aes 256 esp-sha256-hmac
 mode tunnel

crypto map ZIA_MAP 10 ipsec-isakmp
 set peer <Zscaler PoP IP>
 set transform-set ZIA_TRANSFORM
 match address ZIA_TRAFFIC

interface GigabitEthernet0/0
 crypto map ZIA_MAP
```

### 2.4 PAC ファイルによる転送

```javascript
// PAC ファイルサンプル（ZIA 経由設定）
function FindProxyForURL(url, host) {
    // ローカルアドレスはバイパス
    if (isPlainHostName(host) ||
        shExpMatch(host, "*.corp.local") ||
        isInNet(host, "10.0.0.0", "255.0.0.0") ||
        isInNet(host, "192.168.0.0", "255.255.0.0")) {
        return "DIRECT";
    }
    
    // Zscaler 管理コンソールはバイパス
    if (shExpMatch(host, "*.zscaler.net") ||
        shExpMatch(host, "*.zscloud.net")) {
        return "DIRECT";
    }
    
    // それ以外は Zscaler 経由
    return "PROXY gateway.zscaler.net:80; PROXY gateway.zscaler.net:8080; DIRECT";
}
```

### 2.5 Surrogate IP とユーザー識別

IP 認証環境では、同一 IP から複数ユーザーのトラフィックが来る場合があります。Surrogate IP を使うと、Cookie ベースでユーザーを識別し、個人レベルのポリシー適用が可能です。

| 識別方式 | 仕組み | 用途 |
|---------|-------|------|
| IP ベース | 送信元 IP でユーザー識別 | 固定 IP の拠点 |
| Surrogate IP | Cookie でユーザー識別 | 共有 IP / DHCP 環境 |
| SAML | IdP 認証でユーザー識別 | ZCC / ブラウザ認証 |
| Kerberos | AD チケットでユーザー識別 | Active Directory 環境 |

---

## 3. 認証

### 3.1 認証方式の比較

| 方式 | 特徴 | 推奨環境 |
|------|------|---------|
| SAML SSO | IdP 連携、MFA 対応、ZCC/ブラウザ対応 | クラウドファースト環境 |
| Kerberos | AD 統合、SSO、透過認証 | AD 環境のオンプレ拠点 |
| LDAP | AD/LDAP への直接認証 | レガシー環境 |
| IP ベース | 認証不要、IP でユーザー識別 | サーバー・IoT・共有端末 |
| ZCC 証明書 | デバイス証明書ベース | MDM 管理デバイス |

### 3.2 Azure AD SAML 設定手順

**Azure AD 側の設定：**

1. Azure ポータル → **エンタープライズアプリケーション** → **新しいアプリケーション**
2. ギャラリーで「Zscaler」を検索して追加
3. **シングルサインオン** → **SAML** を選択
4. 基本 SAML 構成を設定：

```
識別子（エンティティ ID）: https://zscloud.net
応答 URL（ACS URL）: https://<テナント>.zscloud.net/authapi/v1/saml/resp
サインオン URL: https://<テナント>.zscloud.net
```

5. 属性・クレームを設定：

| クレーム名 | 値 |
|-----------|-----|
| emailaddress | user.mail |
| displayname | user.displayname |
| memberOf | user.groups |

6. フェデレーションメタデータ XML をダウンロード

**ZIA 管理コンソール側の設定：**

```
Administration → Authentication → SAML
→ IdP Metadata をアップロード
→ User Attribute: email
→ Group Attribute: memberOf
→ 設定を保存
```

### 3.3 Okta SAML 設定手順

1. Okta 管理コンソール → **Applications** → **Browse App Catalog**
2. 「Zscaler」を検索して Add
3. **Sign On** タブで SAML 設定：

```
Single Sign On URL: https://<テナント>.zscloud.net/authapi/v1/saml/resp
Audience URI: https://zscloud.net
Name ID Format: EmailAddress
Application Username: Email
```

4. Attribute Statements を追加：
   - `memberOf` → `user.groups`
   - `displayName` → `user.displayName`

### 3.4 SCIM によるユーザープロビジョニング

```
# Azure AD SCIM 設定
Azure AD → エンタープライズアプリ → Zscaler → プロビジョニング

テナント URL: https://<テナント>.zsapi.net/api/v1/scim
シークレットトークン: ZIA 管理コンソールの SCIM Token

同期対象:
  Users → ユーザーの作成・更新・削除
  Groups → グループとメンバーシップ
同期間隔: 40 分（Azure AD 固定）
```

---

## 4. URL フィルタリング

### 4.1 URL カテゴリの仕組み

ZIA は 200 以上の URL カテゴリを持ち、機械学習によって URL をリアルタイムで分類します。

主要カテゴリ例：

| カテゴリグループ | カテゴリ例 |
|--------------|-----------|
| セキュリティ | Malware, Phishing, Botnet C&C, Hacking |
| 不適切コンテンツ | Adult Content, Gambling, Violence |
| ソーシャル | Social Networking, Streaming Media |
| ビジネス | Business Applications, Cloud Storage |
| その他 | Uncategorized |

### 4.2 カスタム URL カテゴリ

```
ZIA 管理コンソール → Policy → URL & Cloud App Control → Custom URL Categories
→ Add Custom Category

設定項目:
  名前: 社内承認済みSaaS
  種別: Allow List
  URLs:
    - approved-vendor.com
    - *.internal-tool.io
    - app.business-partner.co.jp
```

### 4.3 ポリシーアクション詳細

| アクション | 動作 | 利用シーン |
|-----------|------|----------|
| **Allow** | アクセス許可 | 業務に必要なサイト |
| **Block** | アクセス拒否（ブロックページ表示） | マルウェア・不適切サイト |
| **Caution** | 警告ページ表示（ユーザーが続行可能） | SNS・動画サイト |
| **Isolate** | Browser Isolation 経由でアクセス | リスクのある未知サイト |
| **Override** | 管理者パスワードで上書き許可 | 一時的なアクセス許可 |

### 4.4 ポリシー評価順序

ZIA のポリシーは**上から順番に評価**され、最初にマッチしたルールが適用されます。

```
優先度 1: セキュリティ系カテゴリブロック（最優先）
優先度 2: 役員・管理者フルアクセス
優先度 3: 業務時間内の SNS 制限
優先度 4: 外部委託の制限
優先度 5: 一般ユーザーのデフォルトポリシー
優先度 99: デフォルト許可（最後尾）
```

### 4.5 時間帯ベースのポリシー

```
ポリシー名: 業務時間外 SNS 許可
条件:
  カテゴリ: Social Networking
  時間帯: 18:00 〜 23:59
  曜日: 月〜金
アクション: Allow

ポリシー名: 週末 SNS 許可
条件:
  カテゴリ: Social Networking
  曜日: 土・日
アクション: Allow
```

---

## 5. SSL インスペクション

### 5.1 SSL/TLS インスペクションの仕組み

ZIA は中間者（Man-in-the-Middle）方式で HTTPS トラフィックを復号・検査します。

```
[ クライアント ] ←──── Zscaler ルート CA で署名した証明書
        │
        ▼
[ Zscaler PoP ] ── SSL ターミネーション・検査
        │
        ▼
[ 宛先サーバー ] ←──── 本来の SSL 証明書
```

Zscaler が独自の証明書をリアルタイムで生成するため、**クライアントの信頼ストアに Zscaler ルート CA を事前登録**する必要があります。

### 5.2 Zscaler ルート CA の配布

**Windows（グループポリシー）：**

```
グループポリシー管理エディター
→ コンピューターの構成 → Windows の設定 → セキュリティの設定
→ 公開キーのポリシー → 信頼されたルート証明機関
→ Zscaler ルート CA（.crt）をインポート
```

**macOS（Jamf Pro）：**

```
Jamf Pro → Configuration Profiles → Add
→ Certificate ペイロードを追加
→ Zscaler ルート CA（.pem）をアップロード
→ System キーチェーンにインストール
→ 対象デバイスにプッシュ
```

**iOS/Android（MDM）：**

```
Intune / Jamf Now / MobileIron:
  デバイス構成プロファイルで信頼済み証明書ペイロードを配布
  → System キーチェーン（iOS）/ ユーザー証明書ストア（Android）へ自動インストール
```

### 5.3 SSL バイパスルールの設計

証明書ピンニングや業務上の理由で SSL インスペクションを除外するルールを設計します。

```
バイパスカテゴリの推奨例（ZIA 管理コンソール）:
Policy → SSL Inspection → SSL Inspection Bypass Rules

ルール1: セキュリティ製品バイパス
  宛先: *.crowdstrike.com, *.sentinelone.net, *.cylance.com
  理由: AV/EDR は証明書ピンニングを使用

ルール2: 会議ツールバイパス
  宛先: *.zoom.us, *.teams.microsoft.com, *.webex.com
  理由: パフォーマンス最適化 + ピンニング

ルール3: OS アップデートバイパス
  宛先: *.windowsupdate.com, *.apple.com, *.googleapis.com
  理由: OS 更新の安定性確保

ルール4: Financial / Healthcare バイパス（必要に応じて）
  宛先: *.e-tax.nta.go.jp, 医療システムの FQDN
  理由: 法令・コンプライアンス要件
```

### 5.4 SSL インスペクション 推奨バイパスリスト

| サービス | ドメイン | バイパス理由 |
|---------|---------|------------|
| Zoom | *.zoom.us | 証明書ピンニング + パフォーマンス |
| Microsoft Teams | *.teams.microsoft.com | 証明書ピンニング |
| Google Meet | *.meet.google.com | パフォーマンス |
| CrowdStrike | *.crowdstrike.com | セキュリティ製品 |
| SentinelOne | *.sentinelone.net | セキュリティ製品 |
| Windows Update | *.windowsupdate.com | OS 更新 |
| Apple Update | *.apple.com, *.mzstatic.com | OS 更新 |
| AWS S3 | *.s3.amazonaws.com | 証明書ピンニング |

### 5.5 SSL インスペクション トラブルシューティング

**症状：特定のアプリで「証明書エラー」が表示される**

```
診断手順:
1. ブラウザで対象サイトを開き証明書を確認
   → 発行者が「Zscaler Inc」なら ZIA が検査している
   → 「本来のサーバー証明書」なら ZIA はバイパス済み

2. ZIA ログで対象 URL の SSL アクションを確認
   Reporting → Web Insights
   → フィルタ: URL = 対象ドメイン
   → SSL Action 列: Inspected / Bypassed / Certificate Pinning Error

3. Certificate Pinning Error の場合:
   → SSL Bypass Rules に当該ドメインを追加して解決

4. ルート CA が配布されていない場合:
   → MDM/GPO で Zscaler ルート CA を再配布
   → certmgr.msc (Windows) または Keychain (macOS) で確認
```

---

## 6. クラウドファイアウォール

### 6.1 クラウドファイアウォールの概要

ZIA のクラウドファイアウォールは L4〜L7 のトラフィック制御を行います。

```
制御対象:
  ├─ TCP/UDP ポート制御
  ├─ DNS フィルタリング
  ├─ ICMP 制御
  ├─ アプリケーション識別（L7）
  └─ 地域ベースブロック（GeoIP）
```

### 6.2 ファイアウォールルールの構成要素

| 要素 | 説明 | 例 |
|------|------|-----|
| 送信元 | ユーザー / グループ / ロケーション | IT 管理者グループ |
| 宛先 | IP アドレス / FQDN / カテゴリ | 10.0.0.0/8, *.example.com |
| プロトコル | TCP / UDP / ICMP / その他 | TCP:443 |
| アプリケーション | L7 で識別されたアプリ | BitTorrent, TeamViewer |
| アクション | Allow / Block / Drop | Block |

### 6.3 DNS フィルタリング

```
DNS ポリシー設定:
  ├─ マルウェアドメイン: Block（デフォルト有効）
  ├─ フィッシングドメイン: Block（デフォルト有効）
  ├─ ボットネット C&C: Block（デフォルト有効）
  ├─ DNS over HTTPS: Block（プロキシ回避防止）
  └─ カスタム DNS ブロックリスト: 独自ドメインを追加可能
```

### 6.4 非 HTTP トラフィックの制御

Z-Tunnel 2.0 を使用すると、HTTP 以外のトラフィックも ZIA で制御できます。

```
例：カスタムポートアプリのブロック
ファイアウォールルール:
  名前: 不正リモートアクセスブロック
  プロトコル: TCP
  宛先ポート: 4444, 5555, 6666  # よく使われる RAT ポート
  アクション: Block

例：BitTorrent ブロック
  アプリケーション: BitTorrent
  アクション: Block（UDP を含むすべてのポート）
```

---

## 7. DLP（データ損失防止）

### 7.1 DLP エンジンの動作原理

ZIA の DLP はインラインで以下を検査します：

- **HTTP/HTTPS アップロード**（フォーム送信・ファイル添付）
- **クラウドストレージ**（OneDrive・Dropbox・Box 等へのアップロード）
- **メール添付ファイル**（Web メール経由）
- **SaaS アプリへの投稿**（Slack・Teams 等）

### 7.2 組み込み辞書

| 辞書名 | 検出内容 | 活性化推奨度 |
|--------|---------|------------|
| Credit Card Numbers | クレジットカード番号（Luhn 検証） | ★★★ 必須 |
| Japan Individual Number | マイナンバー（12 桁） | ★★★ 必須 |
| Japan Passport | 日本のパスポート番号 | ★★☆ 推奨 |
| Japan Bank Account | 銀行口座番号 | ★★☆ 推奨 |
| Social Security Numbers (US) | 米国 SSN | ★☆☆ 必要に応じて |
| HIPAA PHI | 医療情報（米国） | ★☆☆ 医療業界向け |
| Source Code | ソースコード検出 | ★★☆ IT 企業向け |

### 7.3 カスタム辞書の作成

```
ZIA 管理コンソール → Policy → DLP → DLP Dictionaries → Add

設定例：社内機密キーワード辞書
名前: 社内機密キーワード
フレーズ（完全一致）:
  - "極秘"
  - "社外秘"
  - "CONFIDENTIAL"
  - "取締役会議事録"
  - "M&A 検討"

パターン（正規表現）:
  - 社員番号: \b[A-Z]\d{6}\b
  - 製品コード: \bPRD-\d{4}-[A-Z]{2}\b
```

### 7.4 DLP ポリシーの設定

```
DLP ポリシー設定例：クレジットカード情報の外部送信ブロック
名前: クレカ番号外部送信禁止
辞書: Credit Card Numbers（5件以上一致）
宛先: 社外（Internal Network を除く）
ファイルタイプ: すべて
アクション: Block
通知: ユーザーへのアラート + 管理者へのメール
証拠保全: スナップショット保存（30日）
```

### 7.5 DLP アクション一覧

| アクション | 動作 |
|-----------|------|
| **Monitor** | 検出のみ（通信は継続）、ログに記録 |
| **Warn** | ユーザーに警告、確認後に続行可能 |
| **Block** | 通信を完全ブロック、ブロックページ表示 |
| **Quarantine** | ファイルを隔離、管理者が審査後に解放 |
| **Allow** | DLP の例外（許可リスト） |

### 7.6 誤検知の調整

```
誤検知が多い場合のチューニング:

1. Proximity Validation の有効化
   クレジットカード辞書: "card", "credit", "visa" 等のキーワードとの
   近接チェック（前後 100 文字以内）を有効にする

2. 最小一致数の調整
   デフォルト: 1件 → 誤検知が多い場合は 3〜5 件に変更

3. Luhn アルゴリズム検証
   有効にすることで有効なカード番号のみ検出（デフォルト有効）

4. ホワイトリスト登録
   DLP → Exceptions に特定のユーザー・グループ・URL を追加
```

---

## 8. クラウドサンドボックス

### 8.1 サンドボックスの動作フロー

```
ダウンロードファイル検知
        │
        ▼
ファイルハッシュ確認（既知の判定結果があれば即時返答）
        │ 未知ファイルの場合
        ▼
Zscaler クラウドサンドボックスへ送信
        │
        ▼
仮想環境で実行・動作解析（数秒〜数十秒）
        │
        ├─ Benign（安全）→ ユーザーへファイル配信
        ├─ Suspicious（要注意）→ 警告付きで配信 or 一時保留
        └─ Malicious（危険）→ ブロック・隔離・アラート発報
```

### 8.2 対応ファイルタイプ

| カテゴリ | ファイル形式 |
|---------|------------|
| 実行ファイル | .exe, .dll, .sys, .msi |
| Office ドキュメント | .docx, .xlsx, .pptx, .doc, .xls |
| PDF | .pdf |
| スクリプト | .ps1, .vbs, .js, .bat, .sh |
| アーカイブ | .zip, .rar, .7z（展開して内部検査） |
| APK | .apk（Android アプリ） |

### 8.3 ハッシュホワイトリストの登録

```
社内開発ツールなど既知の安全なファイルをホワイトリストに登録：

ZIA 管理コンソール → Policy → Sandboxing → File Hash Blocklist/Allowlist
→ Add Hash

方法:
  SHA-256 ハッシュを直接入力
  または CSV でまとめてインポート

# ファイルの SHA-256 確認方法
Windows: certutil -hashfile <file> SHA256
Linux:   sha256sum <file>
macOS:   shasum -a 256 <file>
```

### 8.4 サンドボックスレポートの読み方

```
レポートの主要セクション:
1. 概要
   - 最終判定（Benign / Suspicious / Malicious）
   - リスクスコア（0〜100）
   - ファイル情報（ハッシュ / ファイルタイプ / サイズ）

2. 動作分析
   - ファイルシステム操作（作成・変更・削除）
   - レジストリ変更（Windows）
   - ネットワーク通信（C&C 通信の有無）
   - プロセス生成（子プロセスの起動）

3. 検出シグネチャ
   - MITRE ATT&CK フレームワークへのマッピング
   - 検出されたマルウェアファミリー名

4. 推奨アクション
   - デバイス隔離の要否
   - フォレンジック調査の推奨事項
```

---

## 9. CASB

### 9.1 インライン CASB の概要

ZIA のインライン CASB は、SaaS アプリへのアクセスをリアルタイムで制御します。

```
主要機能:
  ├─ Shadow IT 検出（未申請 SaaS の可視化）
  ├─ テナント制限（個人アカウントと業務アカウントの分離）
  ├─ アクティビティ制御（アップロード・ダウンロード・共有の制御）
  └─ データ保護（DLP との連携）
```

### 9.2 テナント制限（Tenant Restrictions）

業務用 Microsoft 365 以外の Microsoft アカウント（個人・他社）へのアクセスをブロックします。

```
Microsoft 365 テナント制限:
  対象ヘッダー: Restrict-Access-To-Tenants
  設定値: <許可するテナント ID>
  適用方法: ZIA が HTTPS ヘッダーにインジェクト

Google Workspace テナント制限:
  対象ヘッダー: X-GoogApps-Allowed-Domains
  設定値: company.com（許可するドメイン）
```

### 9.3 Microsoft 365 の詳細制御

```
制御可能なアクティビティ:
  SharePoint: アップロード・ダウンロード・外部共有
  OneDrive: 個人デバイスへの同期
  Exchange: 外部メール転送の制限
  Teams: 外部テナントとの会話
  
設定例: 外部共有のブロック
  App: SharePoint
  アクティビティ: Share
  宛先: External
  アクション: Block
```

---

## 10. 帯域制御・QoS

### 10.1 帯域クラスの設定

```
ZIA 管理コンソール → Policy → Bandwidth Control → Bandwidth Classes

クラス例:
  Critical（最高優先）: ビデオ会議（Zoom, Teams）, VoIP
  High:                 業務アプリ（ERP, CRM）
  Medium:               一般 Web ブラウジング
  Low:                  ストリーミング動画（YouTube 等）
  Background:           OS アップデート, バックアップ

帯域制限ポリシー例:
  名前: ストリーミング制限
  対象カテゴリ: Streaming Media
  帯域クラス: Low
  最大帯域: 5 Mbps/ユーザー
  適用時間: 業務時間（9:00〜18:00）
```

---

## 11. DNS セキュリティ

### 11.1 DNS フィルタリングの仕組み

ZIA は DNS クエリをインターセプトし、以下を検査します：

```
DNS クエリの評価フロー:
  クエリ → ブラックリスト（マルウェア/フィッシング）照合
         → カテゴリ分類
         → ポリシー評価
         → 許可: 通常の DNS 解決
         → ブロック: NXDOMAIN または ブロックページ IP を返答
```

### 11.2 DNS トンネリング検知

```
検知の仕組み:
  - 異常に長い DNS クエリ名（通常 253 文字以内を超えるもの）
  - 高頻度の TXT / NULL レコードクエリ
  - エントロピーの高いサブドメイン名（ランダム文字列）
  - 特定ドメインへの異常な問い合わせ頻度

対処: 自動ブロック + アラート発報
```

### 11.3 DNS over HTTPS / DoT の制御

```
DNS over HTTPS (DoH) のブロック:
  → よく使われる DoH エンドポイントをブロック
    (cloudflare-dns.com, dns.google, etc.)
  → TCP/UDP 853 (DNS over TLS) もブロック
  → 理由: DNS フィルタリングを回避される可能性を防ぐ
```

---

## 12. ログ・レポート・SIEM 連携

### 12.1 Web Insights（リアルタイムログ）

```
ZIA 管理コンソール → Analytics → Web Insights

主要フィールド:
  timestamp    : アクセス時刻
  user         : ユーザー名 / メールアドレス
  srcip        : 送信元 IP
  url          : アクセス URL
  urlcategory  : URL カテゴリ
  action       : Allow / Block / Caution
  reason       : ブロック理由
  sslinspected : SSL 検査の有無
  filemd5      : ダウンロードファイルのハッシュ
  dlpdict      : 一致した DLP 辞書名
  location     : ロケーション名
  department   : 部署名（IdP から取得）
```

### 12.2 NSS（Nanolog Streaming Service）設定

NSS は ZIA のログをリアルタイムで外部 SIEM に転送するサービスです。

```
NSS の種類:
  Cloud NSS: Zscaler クラウドから直接 SIEM へ転送（推奨）
  VM NSS:    自社ネットワーク内に NSS VM を配置

Cloud NSS の設定:
  ZIA → Administration → Nanolog Streaming Service
  → Add NSS Server
  Type: Cloud NSS
  SIEM: Splunk / Microsoft Sentinel / QRadar 等を選択
  Endpoint: https://<SIEM-HEC-endpoint>
  Token: <HEC Token>
  フィード: Web ログ, Firewall ログ, DNS ログを選択
```

### 12.3 Splunk 連携と活用クエリ

```
# Splunk - ZIA ブロックイベントの分析
index=zscaler sourcetype=zscalernss-web action=Blocked
| stats count by user, urlcategory, reason
| sort -count
| head 20

# 特定ユーザーのブラウジング履歴
index=zscaler sourcetype=zscalernss-web user="user@corp.com"
| table _time, url, action, urlcategory, sslinspected
| sort -_time

# DLP 検出イベント
index=zscaler sourcetype=zscalernss-web dlpdict!=""
| stats count by user, dlpdict, url
| sort -count

# マルウェア検出
index=zscaler sourcetype=zscalernss-web urlcategory=Malware OR urlcategory=Phishing
| table _time, user, url, action
| sort -_time
```

### 12.4 Microsoft Sentinel 連携

```
Sentinel データコネクタ:
  Microsoft Sentinel → Data Connectors → Zscaler Internet Access
  → NSS を使って Log Analytics Workspace に転送

KQL クエリ例（ブロックイベント分析）:
CommonSecurityLog
| where DeviceVendor == "Zscaler"
| where Activity == "Blocked"
| summarize count() by DestinationHostName, DeviceCustomString2
| sort by count_ desc
| take 20
```

---

## 13. 管理者権限・テナント管理

### 13.1 管理者ロール一覧

| ロール | 権限 |
|--------|------|
| Super Admin | 全機能へのフルアクセス、他管理者の管理 |
| Admin | ポリシー・設定の変更（管理者管理を除く） |
| Auditor | 読み取り専用（ログ・レポート閲覧） |
| Report Admin | レポート・分析画面のみアクセス |
| カスタムロール | 特定機能のみ許可するロールを定義可能 |

### 13.2 監査ログの確認

```
ZIA 管理コンソール → Administration → Audit Logs

確認できる操作:
  - ポリシーの作成・変更・削除
  - 管理者ログイン・ログアウト
  - 設定変更（認証・証明書・NSS 等）
  - API キーの生成・削除

保持期間: 最大 6 ヶ月
エクスポート: CSV 形式でダウンロード可能
```

---

## 14. ZIA API と自動化

### 14.1 API 認証

```python
import requests

# ZIA API セッション認証
session = requests.Session()
base_url = "https://zsapi.zscaler.net/api/v1"

# ログイン
login_resp = session.post(
    f"{base_url}/authenticatedSession",
    json={
        "username": "admin@corp.com",
        "password": "your_password",
        "apiKey": "your_api_key"
    },
    headers={"Content-Type": "application/json"}
)
print(f"Login: {login_resp.status_code}")
```

### 14.2 URL カテゴリの一括管理

```python
# カスタム URL カテゴリへの URL 一括追加
def add_urls_to_category(session, category_id, new_urls):
    # 既存カテゴリを取得
    cat = session.get(f"{base_url}/urlCategories/{category_id}").json()
    
    # URL を追加
    cat["urls"] = list(set(cat.get("urls", []) + new_urls))
    
    # 更新
    resp = session.put(
        f"{base_url}/urlCategories/{category_id}",
        json=cat,
        headers={"Content-Type": "application/json"}
    )
    return resp.status_code

# 使用例
urls_to_add = ["new-approved-site.com", "partner-portal.co.jp"]
add_urls_to_category(session, "CUSTOM_CATEGORY_01", urls_to_add)
```

### 14.3 Terraform による ZIA 設定管理

```hcl
# Terraform Provider for ZIA
terraform {
  required_providers {
    zia = {
      source  = "zscaler/zia"
      version = "~> 3.0"
    }
  }
}

provider "zia" {
  username   = var.zia_username
  password   = var.zia_password
  api_key    = var.zia_api_key
  cloud_name = "zscaler.net"
}

# カスタム URL カテゴリ
resource "zia_url_categories" "approved_sites" {
  configured_name = "社内承認済みサイト"
  super_category  = "USER_DEFINED"
  type            = "URL_CATEGORY"
  urls = [
    "approved-vendor.com",
    "business-partner.co.jp"
  ]
}

# URL フィルタリングポリシー
resource "zia_url_filtering_rules" "allow_approved" {
  name     = "承認済みサイト許可"
  order    = 1
  state    = "ENABLED"
  action   = "ALLOW"
  url_categories = [zia_url_categories.approved_sites.id]
}
```

---

## 15. よくある障害・FAQ

### 15.1 特定サイトにアクセスできない

```
診断手順:
1. ZIA Web Insights でブロック理由を確認
   → URL カテゴリ → カスタムカテゴリで Allow ルール追加
   → DLP 検出 → 対象ユーザー・コンテンツを確認
   → Sandbox ブロック → ハッシュをホワイトリストに追加

2. ポリシー優先度を確認
   → より高い優先度のブロックルールが当たっていないか
   → ユーザーが正しいグループに属しているか

3. SSL インスペクションの影響
   → 証明書エラーが出ている場合はバイパスリストに追加
```

### 15.2 認証ループが止まらない

```
原因と対処:
1. SAML レスポンスの属性不一致
   → IdP の SAML 設定で email 属性が正しくマッピングされているか確認
   → ZIA 側のユーザー属性マッピングと一致しているか確認

2. セッション Cookie の問題
   → ブラウザの Cookie をクリアして再試行
   → シークレットモードで動作確認

3. 時刻同期のずれ
   → SAML は時刻に厳格（前後 5 分以内）
   → NTP 設定を確認：ntpq -p

4. SAML トレースで確認
   → ブラウザの SAML トレーサー拡張機能でレスポンスを確認
   → StatusCode が "Success" になっているか
```

### 15.3 パフォーマンスが低下する

```
確認手順:
1. 接続先 PoP の確認
   ZCC → About → Zscaler Node で接続先を確認
   → 最寄りでない PoP に接続している場合は設定を確認

2. SSL インスペクション対象の見直し
   → 大容量トラフィック（動画等）はバイパスを検討

3. 帯域制御の確認
   → 特定カテゴリに帯域制限が設定されていないか

4. GRE/IPSec トンネルの MTU 確認
   → MTU 不整合によるフラグメンテーションが原因のことも
   → MTU: 1400, MSS: 1360 が推奨値
```

### 15.4 DLP の誤検知が多い

```
チューニング手順:
1. 誤検知の多い辞書を特定
   → Web Insights で DLP 検出ログをエクスポート
   → dlpdict 別に件数を集計

2. Proximity Validation を有効化
   → 前後のキーワードとの共起を要件にする

3. 最小一致数を増やす
   → 1件 → 3〜5件に変更で誤検知を大幅削減

4. 特定ユーザー・URL を例外設定
   → DLP Exceptions にホワイトリスト登録
```

### 15.5 GRE/IPSec トンネルが頻繁にダウンする

```
確認事項:
1. IKE キープアライブの設定
   crypto isakmp keepalive 30 5 on-demand

2. Dead Peer Detection（DPD）の設定
   crypto isakmp profile ZIA_PROFILE
    keepalive 30 retry 5

3. NAT Traversal の設定（NAT 配下の場合）
   crypto isakmp nat keepalive 30

4. Zscaler 側のトンネルヘルス確認
   ZIA → Network → VPN Credentials → トンネル状態
```

---

## 16. 展開ベストプラクティス

### 16.1 推奨展開フェーズ

```
Phase 1: 計画・設計（2〜4 週間）
  - 現行プロキシ設定の棚卸し
  - ユーザーグループとポリシーの設計
  - SSL バイパスリストの初期版作成
  - IdP 連携の設計（Azure AD / Okta）
  - PoC 環境の構築

Phase 2: パイロット（2〜4 週間）
  - IT チーム（10〜20 名）に ZIA を適用
  - URL フィルタリング・SSL インスペクションの動作確認
  - 誤検知・ブロックの調整
  - パフォーマンス測定

Phase 3: 段階展開（4〜8 週間）
  - 部門ごとに順次展開（全体の 20% → 50% → 100%）
  - ユーザー向けの周知・FAQ 作成
  - ヘルプデスクへの教育

Phase 4: 完全移行
  - 旧プロキシのバイパス・停止
  - PAC ファイルの最終切り替え
  - 設定のドキュメント化
```

### 16.2 ポリシー設計の原則

```
1. 最小権限の原則
   → デフォルトは「すべて許可」ではなく「未分類は監視」から開始

2. 命名規則の統一
   → [対象グループ]_[カテゴリ]_[アクション]_[番号]
   → 例: 一般ユーザー_SNS_注意_001

3. 優先度設計
   → セキュリティ系ブロック: 最高優先（1〜10）
   → 管理者例外: 中高優先（11〜20）
   → 一般ポリシー: 中優先（21〜90）
   → デフォルト: 最低優先（99〜）

4. 変更管理
   → ポリシー変更は業務時間外に実施
   → 変更前後のバックアップを取得（エクスポート）
   → ロールバック手順を事前に確認
```

### 16.3 定期レビューチェックリスト

```
月次レビュー:
  □ ブロック件数の傾向確認（急増・急減がないか）
  □ DLP 検出件数と誤検知率の確認
  □ Sandbox マルウェア検出件数の確認
  □ SSL バイパスリストの適切性確認

四半期レビュー:
  □ 未使用ポリシーの棚卸し・削除
  □ カスタム URL カテゴリの更新
  □ 管理者アカウントの棚卸し（退職者の確認）
  □ SSL 証明書の有効期限確認
  □ NSS / SIEM 連携の動作確認
  □ ZCC バージョンのアップデート状況確認
```

---

## 参考リンク

- ZIA Help Center: https://help.zscaler.com/zia
- ZIA API ドキュメント: https://help.zscaler.com/zia/api
- Zscaler Community（ZIA）: https://community.zscaler.com/s/topic/ZIA
- Terraform Provider for ZIA: https://registry.terraform.io/providers/zscaler/zia
- Zscaler ThreatLabz ブログ: https://www.zscaler.com/blogs/security-research
