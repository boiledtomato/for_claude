# Zscaler Private Access (ZPA) 完全技術ガイド

> 対象バージョン: ZPA 最新安定版準拠  
> 最終更新: 2026-06-12

---

## 目次

1. [ZPA とは・ゼロトラストアーキテクチャ](#1-zpa-とはゼロトラストアーキテクチャ)
2. [App Connector](#2-app-connector)
3. [Connector Group と Server Group](#3-connector-group-と-server-group)
4. [Application Segment](#4-application-segment)
5. [DNS の動作と Synthetic IP](#5-dns-の動作と-synthetic-ip)
6. [アクセスポリシー設計](#6-アクセスポリシー設計)
7. [IdP 連携（SAML / SCIM）](#7-idp-連携saml--scim)
8. [デバイスポスチャー](#8-デバイスポスチャー)
9. [マシントンネル](#9-マシントンネル)
10. [Browser Access](#10-browser-access)
11. [Privileged Remote Access（PRA）](#11-privileged-remote-accesspra)
12. [ログ・モニタリング・SIEM 連携](#12-ログモニタリングsiem-連携)
13. [ZPA API と自動化](#13-zpa-api-と自動化)
14. [ZPA と ZIA の統合](#14-zpa-と-zia-の統合)
15. [よくある障害・FAQ](#15-よくある障害faq)
16. [展開ベストプラクティス](#16-展開ベストプラクティス)

---

## 1. ZPA とは・ゼロトラストアーキテクチャ

### 1.1 ZPA の概要

**Zscaler Private Access（ZPA）** は、クラウドネイティブなゼロトラストネットワークアクセス（ZTNA）ソリューションです。従来の VPN を置き換え、ユーザーがネットワーク全体ではなく**必要なアプリケーションのみ**にアクセスできるようにします。

**ゼロトラストの原則：**
- **Never Trust, Always Verify**（常に検証、決して信頼しない）
- **Least Privilege Access**（最小権限アクセス）
- **Assume Breach**（侵害前提の設計）

### 1.2 従来型 VPN との比較

| 項目 | 従来型 VPN | ZPA |
|------|-----------|-----|
| アクセス粒度 | ネットワーク全体 | アプリケーション単位 |
| ユーザー認証 | 接続時に 1 回 | アプリアクセスごとに評価 |
| デバイス検証 | なし / 限定的 | デバイスポスチャー評価 |
| 横展開リスク | 高（ネットワーク内移動可能） | 低（アプリのみ到達可能） |
| パフォーマンス | データセンター経由（遅延増大） | 最寄りの PoP 経由 |
| スケーラビリティ | VPN 集約ポイントがボトルネック | クラウドスケール |
| 管理コスト | 機器管理・ライセンス管理が複雑 | クラウドサービスで簡素化 |
| リモートアクセス | 全トラフィックが VPN 経由 | アプリ単位でトンネル確立 |

### 1.3 ZPA コンポーネント全体像

```
┌─────────────────────────────────────────────┐
│              ZPA クラウド（ブローカー）           │
│  ・ポリシー評価                               │
│  ・ユーザー認証（IdP 連携）                    │
│  ・Microtunnel の仲介                         │
└──────────┬──────────────────┬───────────────┘
           │                  │
    ユーザー側               アプリ側
           │                  │
┌──────────▼──────┐  ┌────────▼──────────────┐
│  ZCC / Browser  │  │    App Connector       │
│  Access         │  │  （社内 DC / クラウド）  │
└─────────────────┘  └────────────────────────┘
           │                  │
      エンドユーザー      内部アプリ
    （どこにいても OK）   （ポート開放不要）
```

### 1.4 Microtunnel（mTLS）の仕組み

ZPA は VPN のような「ネットワークトンネル」ではなく、アプリケーション単位の「Microtunnel」を使用します。

```
特徴:
  ・相互 TLS（mTLS）による双方向認証
  ・アウトバウンドのみ（App Connector のポート開放不要）
  ・アプリアクセスごとにトンネルを確立・破棄
  ・ユーザーはアプリ以外のリソースには到達不可
  ・App Connector がアプリサーバーに「代理接続」する形式
```

---

## 2. App Connector

### 2.1 役割と動作原理

App Connector は社内ネットワーク（DC・クラウド）に配置される軽量エージェントです。

```
重要な特徴:
  ・アウトバウンドのみの通信（ファイアウォールの穴開け不要）
  ・ZPA クラウドへのコントロールプレーン接続
  ・ユーザーのトラフィックを代理転送（リバースプロキシ方式）
  ・アプリサーバーの IP アドレスをインターネットに公開しない
```

### 2.2 デプロイ手順

**VMware ESXi（OVA）：**

```bash
# 1. ZPA 管理コンソールからプロビジョニングキーを取得
#    ZPA Console → Connectors → Add Connector → VMware vSphere

# 2. OVA をダウンロードして vSphere にデプロイ
# 3. VM のカスタマイズ画面でプロビジョニングキーを入力
# 4. ネットワーク設定（IP / GW / DNS）を設定
# 5. VM を起動、ZPA コンソールでオンラインを確認
```

**AWS EC2 での AMI デプロイ：**

```bash
# AWS CloudFormation テンプレート例
AWSTemplateFormatVersion: '2010-09-09'
Resources:
  ZPAConnector:
    Type: AWS::EC2::Instance
    Properties:
      ImageId: ami-xxxxxxxxx  # ZPA Connector AMI ID
      InstanceType: t3.medium
      SubnetId: subnet-xxxxxxxx
      SecurityGroupIds:
        - !Ref ZPAConnectorSG
      UserData:
        Fn::Base64: !Sub |
          #!/bin/bash
          echo "PROVISIONING_KEY=<your-key>" >> /etc/zpa/connector.conf
          systemctl start zpa-connector
  
  ZPAConnectorSG:
    Type: AWS::EC2::SecurityGroup
    Properties:
      GroupDescription: ZPA Connector Security Group
      # アウトバウンドのみ許可（インバウンドは不要）
      SecurityGroupEgress:
        - IpProtocol: tcp
          FromPort: 443
          ToPort: 443
          CidrIp: 0.0.0.0/0
```

**Linux（DEB / RPM）：**

```bash
# Ubuntu / Debian
wget https://connector.zscaler.net/zpa-connector.deb
sudo dpkg -i zpa-connector.deb

# プロビジョニングキーを設定
sudo bash -c 'echo "PROVISIONING_KEY=<your-key>" > /etc/zpa/provisioning.conf'

# サービス起動
sudo systemctl enable zpa-connector
sudo systemctl start zpa-connector

# ステータス確認
sudo systemctl status zpa-connector
tail -f /opt/zscaler/var/log/connector.log
```

**Docker コンテナ：**

```bash
docker run -d \
  --name zpa-connector \
  --cap-add NET_ADMIN \
  -e PROVISIONING_KEY="<your-provisioning-key>" \
  --restart unless-stopped \
  zscaler/zpa-connector:latest
```

### 2.3 サイジングガイドライン

| サイズ | vCPU | RAM | 帯域（推奨） | 同時セッション（目安） |
|--------|------|-----|------------|---------------------|
| Micro | 2 | 4 GB | 100 Mbps | 〜500 |
| Small | 4 | 8 GB | 500 Mbps | 〜2,000 |
| Medium | 8 | 16 GB | 1 Gbps | 〜5,000 |
| Large | 16 | 32 GB | 2 Gbps | 〜10,000 |
| XL | 32 | 64 GB | 5 Gbps+ | 〜25,000 |

> **注意**: 同時セッション数はアプリの接続特性（長寿命/短命）によって大きく変わります。実際の負荷測定に基づいてサイジングしてください。

### 2.4 HA（高可用性）構成

```
推奨構成:
  ・同一 Connector Group に最低 2 台の Connector を配置
  ・可能であれば別々の物理ホスト / 可用性ゾーンに配置
  ・ZPA クラウドが自動でロードバランシング + フェイルオーバー

例: 東京 DC の HA 構成
  Connector Group: Tokyo-DC
    ├─ zpa-connector-01（ESXi ホスト A）
    └─ zpa-connector-02（ESXi ホスト B）
```

### 2.5 必要なアウトバウンド通信要件

| 宛先 FQDN | ポート | 用途 |
|-----------|--------|------|
| *.broker.zscaler.net | TCP 443 | ZPA クラウドへのコントロール接続 |
| *.zscalerone.net | TCP 443 | テナントごとのエンドポイント |
| *.zscaler.net | TCP 443 | 共通エンドポイント |
| *.private.zscaler.com | TCP 443 | API / 管理通信 |

### 2.6 障害対処

**Connector がオフラインになる：**

```bash
# ログ確認
tail -100 /opt/zscaler/var/log/connector.log

# よくある原因と対処:
# 1. DNS 解決失敗 → /etc/resolv.conf を確認
# 2. HTTPS 443 がブロックされている → FW ルールを確認
# 3. プロビジョニングキーの期限切れ → 新しいキーを発行
# 4. 時刻同期のずれ → ntpdate -s pool.ntp.org

# サービス再起動
sudo systemctl restart zpa-connector
```

---

## 3. Connector Group と Server Group

### 3.1 概念と設計指針

```
Connector Group（コネクターグループ）:
  ・複数の App Connector を論理的にグループ化
  ・同一サイト / リージョンの Connector をまとめる
  ・Server Group から参照される

Server Group（サーバーグループ）:
  ・App Segment と Connector Group の「橋渡し」
  ・どの Connector でどのアプリに接続するかを定義
  ・Dynamic Discovery または Static で サーバーを管理
```

### 3.2 地理的分散設計例

```
日本国内の典型的な設計:

Connector Group: Tokyo-Primary
  └─ App Connector: zpa-conn-tokyo-01, zpa-conn-tokyo-02
  └─ 接続先: 東京 DC のすべての内部アプリ

Connector Group: Osaka-DR
  └─ App Connector: zpa-conn-osaka-01, zpa-conn-osaka-02
  └─ 接続先: 大阪 DR の内部アプリ（フェイルオーバー用）

Connector Group: AWS-ap-northeast-1
  └─ App Connector: zpa-conn-aws-01, zpa-conn-aws-02（EC2）
  └─ 接続先: AWS 上のアプリケーション（ERP / CRM 等）
```

### 3.3 Dynamic Server Discovery

```
Static vs Dynamic の比較:

Static Discovery:
  ・App Segment に IP アドレスを直接登録
  ・サーバー増減のたびに手動更新が必要
  ・小規模・固定的な環境向け

Dynamic Discovery（DNS ベース）:
  ・App Segment に FQDN または ワイルドカードを登録
  ・App Connector がリアルタイムで DNS 解決
  ・クラウド環境や IP が変わる環境に適している
  ・設定例: *.app.corp.local → DNS が IP を返す
```

---

## 4. Application Segment

### 4.1 設計原則

```
良い設計（推奨）:
  アプリケーション単位で細かく分割
  例:
    App Segment: HR-System（hr.corp.local:443）
    App Segment: ERP-SAP（sap.corp.local:8443）
    App Segment: DevOps-Git（gitlab.corp.local:443,22）

悪い設計（非推奨）:
  すべての内部アプリを 1 つの Segment にまとめる
  例:
    App Segment: All-Internal（*.corp.local:1-65535）
  → 最小権限の原則に反する
  → ポリシー制御の粒度が粗くなる
```

### 4.2 FQDN / IP / ワイルドカードの使い分け

| 方式 | 例 | 使い分け |
|------|-----|---------|
| FQDN | `app.corp.local` | 単一アプリ、推奨 |
| IP アドレス | `192.168.1.100` | FQDN がない場合 |
| ワイルドカード | `*.hr.corp.local` | サブドメイン多数の場合 |
| IP レンジ | `192.168.1.0/24` | サーバー群をまとめる場合（非推奨） |

### 4.3 Health Reporting の設定

| 設定 | 動作 | 推奨用途 |
|------|------|---------|
| **Continuous** | 定期的に接続テスト | 重要なアプリ |
| **On Access** | ユーザーがアクセスした時のみテスト | 一般アプリ |
| **None** | ヘルスチェックなし | レガシーアプリ / UDP |

---

## 5. DNS の動作と Synthetic IP

### 5.1 ZPA の DNS 解決フロー

ZPA では、App Segment に登録されたドメインへの DNS クエリが特別な方法で処理されます。

```
通常の DNS 解決（ZPA なし）:
  クライアント → DNS サーバー → 実際の IP アドレスを返す

ZPA 経由の DNS 解決:
  クライアント（ZCC）
    → ZPA ブローカーに DNS クエリを転送
    → ZPA が「Synthetic IP」（100.64.x.x）を返す
    → クライアントは 100.64.x.x に接続
    → ZCC が ZPA トンネル経由で App Connector に転送
    → App Connector がアプリの実 IP に接続
```

### 5.2 Synthetic IP（100.64.x.x）とは

```
Synthetic IP:
  ・IANA が CGNAT 用途に予約した 100.64.0.0/10 の範囲を使用
  ・ZPA が App Segment に登録されたドメインに割り当てる仮の IP
  ・実際のアプリ IP は ZPA 内部でのみ使用（外部に公開されない）
  ・ZCC が入っていないデバイスからは 100.64.x.x に到達できない
    → セキュリティ強化効果
```

### 5.3 パブリック URL に Synthetic IP が割り当てられる問題

**※ Zscaler Community で最も多く投稿される質問の一つです。**

**症状：**
```
パブリックな URL（例: api.github.com）にアクセスしようとすると
DNS が 100.64.x.x を返し、接続できない
```

**原因：**
```
ZPA の App Segment にパブリックドメインが含まれている場合、
ZPA はそのドメインを「内部アプリ」と判断して Synthetic IP を割り当ててしまう。

よくあるパターン:
  App Segment に *.github.com を登録
  → github.com だけでなく api.github.com にも Synthetic IP が割り当てられる

  ワイルドカード *.corp.com を登録
  → 意図しないサブドメインまでカバーしてしまう
```

**診断手順：**
```bash
# 1. ZCC が動いている状態で DNS 解決を確認
nslookup api.github.com
# → 100.64.x.x が返ってきたら ZPA が原因

# 2. ZCC を無効化して同じコマンドを実行
# → 正常な IP が返ってきたら ZPA の設定の問題

# 3. ZPA 管理コンソールで App Segment を検索
#    対象ドメインが含まれる App Segment を探す
```

**対処法：**
```
方法 1: App Segment から当該ドメインを除外
  → App Segment の FQDN リストから不要なワイルドカードを削除
  → *.corp.local のみにして *.github.com は削除

方法 2: Bypass ルールを追加
  ZPA Console → Policy → Bypass Rules
  → パブリックドメインを ZPA バイパスに設定

方法 3: App Segment の粒度を見直す
  → ワイルドカード *.corp.com より
    app1.corp.com, app2.corp.com と明示的に指定する方が安全
```

### 5.4 Split DNS の設計

```
推奨設計:
  内部 DNS（corp.local ドメイン）:
    → ZPA 経由（App Connector の DNS で解決）
    → App Segment に corp.local を登録

  外部 DNS（パブリックドメイン）:
    → ZIA 経由または直接インターネット
    → ZPA の App Segment に含めない

ZCC の DNS 設定:
  ZPA 経由にするドメイン: corp.local, internal.corp.com
  それ以外: 通常の DNS（8.8.8.8 等）
```

---

## 6. アクセスポリシー設計

### 6.1 ポリシー評価順序

ZPA のアクセスポリシーは**優先度（Order）の数値が小さいほど先に評価**されます。最初にマッチしたルールが適用され、以降のルールは評価されません。

```
例:
  Order 1: IT 管理者 → 全アプリ許可
  Order 2: 管理デバイスのみ許可
  Order 3: HR グループ → HR アプリ許可
  Order 99: その他 → 拒否（デフォルト）

IT 管理者が非管理デバイスからアクセスした場合:
  → Order 1 にマッチ → 全アプリ許可（Order 2 は評価されない）
```

### 6.2 ポリシー条件の種類

**ユーザー・グループ条件：**
```
IdP から同期されたユーザーまたはグループを条件に設定
  例: グループ = "HR_Department" OR "HR_Managers"
  例: メールドメイン = "@corp.com"
```

**SAML 属性条件：**
```
IdP から送られる SAML アサーションの属性を条件に使用
  例: department = "Engineering"
  例: costCenter = "IT-001"
  例: employeeType = "FullTime"

これにより、グループでは表現できない細かい条件が設定可能
```

**デバイスポスチャー条件：**
```
デバイスの状態を評価してアクセス可否を判断
  例: OS バージョン >= Windows 11
  例: CrowdStrike ZTA スコア >= 80
  例: 管理デバイス（証明書あり）のみ
```

**スケジュール条件：**
```
時間帯・曜日でアクセスを制限
  例: 外部委託は平日 9:00〜18:00 のみ許可
  例: 特定システムへのアクセスは業務時間内のみ
```

### 6.3 ポリシー設計例

```
# 最小権限の設計例

ポリシー 1: IT管理者フルアクセス
  条件: グループ = "IT_Admins"
  アプリ: すべての App Segment
  アクション: Allow
  Order: 1

ポリシー 2: HR システムアクセス（管理デバイスのみ）
  条件:
    グループ = "HR_Department"
    AND デバイスポスチャー = 管理デバイス
    AND OS バージョン >= Windows 10 20H2
  アプリ: HR-System App Segment
  アクション: Allow
  Order: 10

ポリシー 3: 外部委託の限定アクセス
  条件:
    グループ = "External_Contractors"
    AND スケジュール = 平日 9:00〜18:00
  アプリ: Supplier-Portal App Segment のみ
  アクション: Allow
  Order: 20

ポリシー 99: デフォルト拒否（明示的）
  条件: すべて
  アプリ: すべて
  アクション: Deny
  Order: 99
```

---

## 7. IdP 連携（SAML / SCIM）

### 7.1 Azure AD との SAML 連携

**Azure AD 側：**

```
1. Azure ポータル → エンタープライズアプリケーション
   → 新しいアプリケーション → ギャラリーから「Zscaler Private Access」を追加

2. シングルサインオン → SAML を選択

3. 基本 SAML 構成:
   識別子（エンティティ ID）: https://samlsp.private.zscaler.com/auth/metadata
   応答 URL:  https://samlsp.private.zscaler.com/auth/login
   サインオン URL: https://samlsp.private.zscaler.com

4. 属性とクレームの設定:
   Unique User Identifier: user.mail
   Additional Claims:
     名前: department  値: user.department
     名前: jobTitle    値: user.jobtitle
     名前: groups      値: user.groups（グループ ID）

5. フェデレーションメタデータ XML をダウンロード
```

**ZPA 管理コンソール側：**

```
Administration → IdP Configuration → Add IdP

Name: Azure AD
Status: Enabled
SAML Metadata: [ダウンロードした XML をアップロード]
Group Attribute: groups
User Attribute: emailaddress
Domain: corp.com
```

### 7.2 SCIM による自動同期

```
Azure AD → エンタープライズアプリ → ZPA → プロビジョニング
プロビジョニングモード: 自動

管理者資格情報:
  テナント URL: https://scim.private.zscaler.com/scim
  シークレットトークン: ZPA コンソールの SCIM Token

マッピング設定:
  Users:
    userPrincipalName → userName
    mail → emails[type eq "work"].value
    displayName → displayName
    department → urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department
  Groups:
    displayName → displayName
    members → members

同期間隔: 40 分
```

### 7.3 マルチ IdP 構成

```
ユースケース:
  本社社員: Azure AD で認証
  子会社社員: Okta で認証
  外部委託: 別の Azure AD テナントで認証

設定:
  ZPA Console → IdP Configuration → 複数の IdP を登録
  各 IdP に異なるドメインを割り当て:
    IdP1 (Azure AD): corp.com
    IdP2 (Okta): subsidiary.co.jp
    IdP3 (Azure AD B2B): contractor.external.com
```

---

## 8. デバイスポスチャー

### 8.1 評価項目一覧

| チェック項目 | 内容 | 設定例 |
|------------|------|--------|
| OS バージョン | 最低 OS バージョン要件 | Windows 11 22H2 以上 |
| ZCC バージョン | ZCC の最低バージョン | 4.3.0 以上 |
| アンチウイルス | AV の有無・更新状態 | CrowdStrike 有効、定義 7 日以内 |
| ディスク暗号化 | BitLocker / FileVault | 有効必須 |
| クライアント証明書 | MDM 発行証明書の有無 | Intune 発行の証明書が存在する |
| ドメイン参加 | AD ドメイン参加状態 | corp.local に参加済み |
| Azure AD Join | Azure AD 参加状態 | corp.com に Join 済み |
| MDM 登録 | Intune / Jamf 登録状態 | Intune に登録済み |
| ファイアウォール | OS ファイアウォール | 有効必須 |

### 8.2 Posture Profile の作成

```
ZPA Console → Device Posture → Posture Profiles → Add

プロファイル名: 高セキュリティ（社内機密アクセス用）
条件（すべて満たすこと）:
  ✅ OS: Windows 11 以上 または macOS 13 以上
  ✅ ZCC: 4.3.0 以上
  ✅ ディスク暗号化: 有効
  ✅ CrowdStrike: アクティブ + ZTA スコア 80 以上
  ✅ クライアント証明書: corp-mdm-ca.corp.local 発行

プロファイル名: 標準（一般業務アプリ用）
条件:
  ✅ OS: Windows 10 以上 または macOS 12 以上
  ✅ ZCC: 4.0.0 以上
  ✅ ドメイン参加 または Azure AD Join
```

### 8.3 CrowdStrike ZTA スコア連携

```
ZTA（Zero Trust Assessment）スコアは CrowdStrike Falcon が算出する
デバイスのセキュリティ評価スコア（0〜100）です。

設定手順:
1. ZPA Console → Device Posture → Third-Party Signals
   → CrowdStrike を有効化

2. CrowdStrike Falcon コンソール → API Clients & Keys
   → Client ID と Client Secret を ZPA に設定

3. Posture Profile でスコア閾値を設定:
   → ZTA Score >= 80（高セキュリティ要件）
   → ZTA Score >= 60（標準要件）

スコアの意味:
  90〜100: 非常に安全
  70〜89:  安全（推奨閾値）
  50〜69:  注意が必要
  0〜49:   問題あり（アクセス拒否推奨）
```

### 8.4 ポスチャーチェック失敗時の体験

```
失敗した場合のユーザー向けメッセージ設定:
ZPA Console → Device Posture → User Notification

メッセージ例:
  「アクセスが拒否されました。
  以下の条件を満たしていない可能性があります:
  - BitLocker（ディスク暗号化）が無効です
  - OS が古いバージョンです（Windows 10 以上が必要）
  - アンチウイルスが最新状態ではありません
  
  IT ヘルプデスク（ext.1234）にお問い合わせください。」

デバッグ（確認方法）:
  ZCC → About → Device Posture
  → 各項目の評価結果（Pass / Fail）を確認
```

---

## 9. マシントンネル

### 9.1 必要性とユースケース

```
マシントンネルが必要なケース:

1. Active Directory ログイン前の通信
   → Windows ログイン画面でドメイン認証を行うために
     DC（Domain Controller）への通信が必要

2. グループポリシー（GPO）の適用
   → ログイン時に SYSVOL / NETLOGON へのアクセスが必要

3. SCCM / Intune のチェックイン
   → OS 起動後すぐに管理サーバーへのチェックイン

4. 証明書の自動更新
   → NDES / SCEP サーバーへのアクセス
```

### 9.2 設定手順

```
ZPA Console → Client Forwarding Policies → Machine Tunnels → Add

Step 1: Machine Credential（機械証明書の設定）
  証明書発行元: 社内 CA または MDM（Intune / Jamf）
  ZPA に登録: Connectors → Machine Credentials → 信頼された CA を追加

Step 2: Machine Tunnel ポリシーの作成
  名前: マシントンネル - AD 認証
  認証方法: Machine Certificate
  適用対象: すべての管理デバイス

Step 3: マシントンネル用 App Segment の作成
  名前: AD-Infrastructure
  FQDN: *.corp.local（または DC の IP アドレス）
  ポート: 88（Kerberos）, 389（LDAP）, 445（SMB）, 3268（GC）
  Server Group: Tokyo-Primary

Step 4: マシントンネル専用アクセスポリシー
  条件: クライアントタイプ = Machine Tunnel
  アプリ: AD-Infrastructure App Segment
  アクション: Allow
```

---

## 10. Browser Access

### 10.1 動作原理

```
Browser Access の仕組み:
  ZCC なしで内部アプリにアクセスできる仕組み

  ユーザー（ブラウザ）
    → ZPA のクラウドプロキシ URL にアクセス
       例: https://app.corp.local.zpa.zscaler.com
    → ZPA クラウドが SAML 認証を要求
    → 認証後、ZPA クラウドが App Connector 経由でアプリに接続
    → ブラウザにコンテンツを返す（ZPA がリバースプロキシとして機能）
```

### 10.2 対応プロトコルと制限

| プロトコル | Browser Access 対応 | 備考 |
|-----------|-------------------|------|
| HTTPS | ✅ フル対応 | 最も安定 |
| HTTP | ✅ HTTPS に昇格 | 自動 HTTPS 化 |
| SSH | ✅ Web ターミナル | ブラウザ内 SSH クライアント |
| RDP | ✅ Web RDP | HTML5 RDP クライアント |
| VNC | ✅ Web VNC | HTML5 VNC クライアント |
| SMB / CIFS | ❌ 非対応 | ZCC が必要 |
| カスタム TCP | ❌ 非対応 | ZCC が必要 |

### 10.3 設定例（外部パートナー向け）

```
ユースケース: 外部ベンダーが ZCC なしで社内 Web アプリにアクセス

App Segment:
  名前: Partner-Portal-BrowserAccess
  FQDN: partner-portal.corp.local
  ポート: 443
  App Type: Browser Access

アクセスポリシー:
  名前: 外部パートナー Browser Access
  条件:
    IdP = 外部パートナー IdP
    クライアントタイプ = Browser Access
  アプリ: Partner-Portal-BrowserAccess
  アクション: Allow

外部パートナーがアクセスする URL:
  https://partner-portal.corp.local.<ZPA テナント>.zpa.zscaler.com
```

---

## 11. Privileged Remote Access（PRA）

### 11.1 概要

ZPA PRA は IT 管理者や外部ベンダーが**特権アクセス管理（PAM）**を必要とするシステムに安全にアクセスするための機能です。

```
主な機能:
  ・セッション録画（監査・コンプライアンス）
  ・Credential Vault（パスワードの自動入力・管理）
  ・Just-in-Time アクセス（必要な時だけ許可）
  ・承認ワークフロー
  ・セッション終了後のアクセス自動停止
```

### 11.2 ユースケース

```
シナリオ 1: 外部ベンダーへのサーバーアクセス
  → ベンダーにパスワードを渡さず、PRA Vault から自動入力
  → セッションを録画して後から監査可能

シナリオ 2: 本番サーバーへの緊急アクセス
  → 通常はアクセス禁止のサーバーに、承認を得てアクセス
  → アクセス理由をログに記録
  → セッション終了後にアクセス権を自動失効

シナリオ 3: 定期メンテナンス
  → 決まった時間帯のみアクセスを許可するスケジュール設定
  → 作業完了後のセッション録画を管理者がレビュー
```

---

## 12. ログ・モニタリング・SIEM 連携

### 12.1 ZPA ログの種類

| ログ種別 | 内容 | 確認場所 |
|---------|------|---------|
| User Activity | ユーザーのアクセス成功・失敗・ポリシー評価結果 | ZPA Console → Analytics |
| Connector Status | App Connector の状態変化・エラー | ZPA Console → Connectors |
| Audit Log | 管理者による設定変更 | ZPA Console → Administration |

### 12.2 Log Streaming Service（LSS）設定

```
ZPA Console → Administration → Log Streaming Service → Add LSS

設定項目:
  名前: ZPA-to-Splunk
  ソース: ZPA クラウド
  タイプ: User Activity / Connector Status
  送信先: Splunk HEC
    URL: https://splunk.corp.com:8088/services/collector
    Token: <HEC Token>
    フォーマット: JSON

フィードバック:
  送信間隔: リアルタイム（デフォルト）
  バッファ: 最大 1 分
```

### 12.3 Splunk 分析クエリ例

```
# ZPA 接続失敗の分析
index=zpa sourcetype=zpa_user_activity status=fail
| stats count by user, app, reason
| sort -count
| head 20

# ポリシー拒否の分析
index=zpa sourcetype=zpa_user_activity action=DENY
| timechart span=1h count by user
| sort -count

# App Connector 障害の検知
index=zpa sourcetype=zpa_connector_status status=offline
| table _time, connector_name, group_name, reason
| sort -_time

# 非管理デバイスからのアクセス試行
index=zpa sourcetype=zpa_user_activity posture_check_fail=true
| stats count by user, device_name, os_version
| sort -count
```

---

## 13. ZPA API と自動化

### 13.1 OAuth2 認証

```python
import requests

# ZPA OAuth2 トークン取得（Client Credentials フロー）
def get_zpa_token(client_id: str, client_secret: str, customer_id: str) -> str:
    resp = requests.post(
        f"https://config.private.zscaler.com/signin",
        json={
            "client_id": client_id,
            "client_secret": client_secret
        },
        headers={"Content-Type": "application/json"}
    )
    resp.raise_for_status()
    return resp.json()["token"]

token = get_zpa_token(
    client_id="your_client_id",
    client_secret="your_client_secret",
    customer_id="your_customer_id"
)
headers = {
    "Authorization": f"Bearer {token}",
    "Content-Type": "application/json"
}
BASE = "https://config.private.zscaler.com/mgmtconfig/v1/admin"
CUST = f"{BASE}/customers/your_customer_id"
```

### 13.2 App Segment 一括作成スクリプト

```python
# アプリ一覧 CSV から App Segment を一括作成
import csv

def create_app_segment(headers, customer_id, name, fqdn, port, connector_group_id):
    payload = {
        "name": name,
        "enabled": True,
        "domainNames": [fqdn],
        "tcpPortRanges": [{"from": str(port), "to": str(port)}],
        "serverGroups": [{"id": connector_group_id}],
        "segmentGroupId": "your_segment_group_id",
        "healthCheckType": "DEFAULT",
        "healthReporting": "ON_ACCESS",
    }
    resp = requests.post(f"{CUST}/application", json=payload, headers=headers)
    if resp.status_code == 200:
        print(f"✅ 作成: {name} ({fqdn}:{port})")
    else:
        print(f"❌ エラー: {name} - {resp.json()}")

# CSV フォーマット: name,fqdn,port
with open("apps.csv") as f:
    reader = csv.DictReader(f)
    for row in reader:
        create_app_segment(
            headers=headers,
            customer_id="your_customer_id",
            name=row["name"],
            fqdn=row["fqdn"],
            port=int(row["port"]),
            connector_group_id="your_connector_group_id"
        )
```

### 13.3 Terraform による ZPA 設定管理

```hcl
# Terraform Provider for ZPA
terraform {
  required_providers {
    zpa = {
      source  = "zscaler/zpa"
      version = "~> 3.0"
    }
  }
}

provider "zpa" {
  client_id      = var.zpa_client_id
  client_secret  = var.zpa_client_secret
  customer_id    = var.zpa_customer_id
  cloud          = "production"
}

# Connector Group
resource "zpa_app_connector_group" "tokyo_dc" {
  name                     = "Tokyo-DC"
  description              = "東京データセンター"
  enabled                  = true
  country_code             = "JP"
  location                 = "Tokyo, Japan"
  upgrade_day              = "SUNDAY"
  upgrade_time_in_secs     = "66600"  # 18:30 JST
  override_version_profile = true
  version_profile_id       = "2"  # Default
}

# App Segment
resource "zpa_application_segment" "hr_system" {
  name              = "HR-System"
  description       = "人事システム"
  enabled           = true
  health_reporting  = "ON_ACCESS"
  bypass_type       = "NEVER"
  tcp_port_range {
    from = "443"
    to   = "443"
  }
  domain_names      = ["hr.corp.local"]
  segment_group_id  = zpa_segment_group.internal_apps.id
  server_groups {
    id = zpa_server_group.tokyo.id
  }
}

# アクセスポリシー
resource "zpa_policy_access_rule" "hr_access" {
  name          = "HR部門 - 人事システムアクセス"
  description   = "HR グループは HR システムに管理デバイスからアクセス可"
  action        = "ALLOW"
  rule_order    = "10"

  conditions {
    operator = "AND"
    operands {
      object_type = "APP"
      lhs         = "id"
      rhs         = zpa_application_segment.hr_system.id
    }
    operands {
      object_type = "SAML"
      lhs         = zpa_idp_controller.azure_ad.id
      rhs         = "HR_Department"  # SAML グループ属性の値
      idp_id      = zpa_idp_controller.azure_ad.id
    }
  }
}
```

---

## 14. ZPA と ZIA の統合

### 14.1 ZPA トラフィックの ZIA インスペクション

```
設定の目的:
  ZPA 経由でプライベートアプリにアクセスするトラフィックを
  ZIA のセキュリティ機能（DLP / マルウェアスキャン等）でインスペクションする

設定手順:
  ZPA Console → Client Forwarding Policies → Forwarding Profile
  → ZPA アプリへのトラフィックを ZIA 経由にルーティング

  ZIA で必要な設定:
  → ZPA のソース IP を許可するロケーション設定
  → SSL インスペクションポリシーの適用
```

### 14.2 SSL インスペクションの共存

```
注意点:
  ZPA は mTLS（相互 TLS）を使用するため、
  ZIA の SSL インスペクションと競合する場合がある

対処:
  ZIA の SSL バイパスルールに ZPA の通信を追加:
  → 宛先: *.zscalerone.net, *.private.zscaler.com
  → アクション: Bypass
```

---

## 15. よくある障害・FAQ

### 15.1 App Connector がオフラインになる

```
原因 1: アウトバウンド TCP 443 がブロックされている
  対処: FW ルールで *.broker.zscaler.net へのアウトバウンドを許可

原因 2: DNS 解決が失敗している
  対処: nslookup <connector-fqdn> でテスト
        /etc/resolv.conf の DNS サーバー設定を確認

原因 3: プロビジョニングキーの期限切れ
  対処: ZPA コンソールで新しいキーを発行して再設定

原因 4: 時刻同期のずれ（TLS 証明書検証に失敗）
  対処: sudo ntpdate -s pool.ntp.org
        timedatectl status で時刻確認

原因 5: Certificate の期限切れ
  対処: /opt/zscaler/var/log/connector.log で証明書エラーを確認
        ZPA コンソールで Connector を再プロビジョニング
```

### 15.2 ZPA 経由でアプリに接続できない

```
診断フロー:
1. ZCC が認証されているか確認
   → ZCC アイコン → ステータスが「接続済み」か

2. App Segment の設定を確認
   → FQDN とポートが正確に設定されているか
   → ワイルドカードが意図通りに機能しているか

3. アクセスポリシーの確認
   → ユーザーが正しいグループに属しているか
   → デバイスポスチャー要件を満たしているか
   → ZPA Console → Analytics でポリシー評価結果を確認

4. App Connector から内部アプリへの接続確認
   → App Connector サーバーから telnet <app-ip> <port> でテスト
   → FW が Connector からアプリへの通信を許可しているか

5. ZPA ログ（User Activity）でエラー詳細を確認
   → reason フィールドで具体的な失敗理由を確認
```

### 15.3 SAML 認証ループが発生する

```
確認手順:
1. ブラウザの SAML トレーサー拡張機能をインストール
   → SAML-tracer（Firefox / Chrome）

2. ZPA へのアクセス時の SAML レスポンスを確認:
   → Status Code が "urn:oasis:names:tc:SAML:2.0:status:Success" か
   → Subject の NameID（メールアドレス）が正しいか
   → 有効期限（NotOnOrAfter）が現在時刻より後か

3. 時刻同期の確認（最も多い原因）
   → SAML は ± 5 分のずれが許容範囲
   → date コマンドで現在時刻を確認
   → NTP 設定を確認

4. ZPA コンソールの IdP 設定を確認
   → メタデータが最新か（IdP のメタデータ URL から再取得）
   → 属性マッピングが正しいか
```

### 15.4 Browser Access で 502/503 エラー

```
原因と対処:
1. App Connector が停止している
   → ZPA コンソール → Connectors でステータス確認
   → systemctl restart zpa-connector

2. App Connector からアプリへの接続が失敗
   → Connector からアプリへの telnet テスト
   → ファイアウォールルール確認

3. Browser Access ドメインの DNS 設定
   → *.zpa.zscaler.com が ZPA クラウドに解決されているか
   → DNS ログで確認

4. SSL 証明書の問題
   → ZPA コンソールで App Segment の証明書設定を確認
```

---

## 16. 展開ベストプラクティス

### 16.1 導入前準備チェックリスト

```
アプリケーション棚卸し:
  □ 社内アプリの FQDN / IP / ポートを一覧化
  □ ユーザーグループとアプリの対応関係を整理
  □ 外部パートナーがアクセスするアプリを特定
  □ 特権アクセスが必要なシステムを特定（PRA 対象）

ネットワーク設計:
  □ App Connector の設置場所を決定
  □ App Connector のアウトバウンド通信を FW で許可
  □ App Connector から各アプリサーバーへの通信を FW で許可
  □ DNS 設計（Split DNS / 内部ドメインの解決）

IdP 設計:
  □ SAML 連携する IdP を決定（Azure AD / Okta）
  □ グループ構造の整理（ZPA ポリシーに使うグループ）
  □ SCIM 同期の設計

デバイスポスチャー設計:
  □ MDM（Intune / Jamf）との連携確認
  □ デバイス証明書の配布設計
  □ ポスチャー要件の設定（セキュリティ要件との整合）
```

### 16.2 VPN からの段階移行計画

```
Phase 1: 準備（2〜4 週間）
  - App Connector のデプロイと動作確認
  - IdP 連携の設定
  - アプリセグメントの作成（全アプリ）
  - アクセスポリシーの設計・テスト

Phase 2: パイロット（2〜4 週間）
  - IT チーム（10〜20 名）が ZPA に切り替え
  - ZCC デプロイ（MDM 経由）
  - 業務アプリへのアクセスを ZPA で実施・検証
  - DNS Synthetic IP 問題等の調整

Phase 3: 段階展開（4〜8 週間）
  - 部門ごとに VPN → ZPA に切り替え
  - ユーザー向けの案内・FAQ 作成
  - ヘルプデスクへの教育
  - VPN と ZPA の並行稼働期間を設ける

Phase 4: VPN 廃止
  - 全ユーザーの ZPA 移行を確認
  - VPN クライアントのアンインストール
  - VPN 機器・ライセンスのフェードアウト
  - 設定のドキュメント化・保管
```

### 16.3 定期レビューチェックリスト

```
月次:
  □ App Connector の稼働率・ヘルスチェック
  □ ZPA 接続失敗件数の傾向確認
  □ ポリシー拒否件数の確認（非正規アクセス試行）

四半期:
  □ 未使用 App Segment の棚卸し・削除
  □ 退職者のアカウント削除確認
  □ App Connector のバージョン更新
  □ IdP のメタデータ更新
  □ デバイスポスチャー要件の見直し（OS バージョン等）
  □ アクセスポリシーの最小権限見直し
```

---

## 参考リンク

- ZPA Help Center: https://help.zscaler.com/zpa
- ZPA API ドキュメント: https://help.zscaler.com/zpa/api
- Terraform Provider for ZPA: https://registry.terraform.io/providers/zscaler/zpa
- Zscaler Community（ZPA）: https://community.zscaler.com/s/topic/ZPA
- ZPA + CrowdStrike 連携: https://help.zscaler.com/zpa/device-posture/crowdstrike
