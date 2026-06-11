# Zscaler Client Connector (ZCC) 完全ガイド

> 対象バージョン: ZCC 4.x 系（最新安定版準拠）  
> 最終更新: 2026-05-30

---

## 目次

1. [ZCC とは](#1-zcc-とは)
2. [アーキテクチャ概要](#2-アーキテクチャ概要)
3. [対応プラットフォーム](#3-対応プラットフォーム)
4. [トンネルモード](#4-トンネルモード)
5. [インストール方法](#5-インストール方法)
6. [転送プロファイルとアプリプロファイル](#6-転送プロファイルとアプリプロファイル)
7. [認証方式](#7-認証方式)
8. [ZPA との連携（マシントンネル / ユーザートンネル）](#8-zpa-との連携)
9. [ZDX エージェント機能](#9-zdx-エージェント機能)
10. [ポリシー設定](#10-ポリシー設定)
11. [MDM/UEM を使った大規模展開](#11-mdmuem-を使った大規模展開)
12. [トラブルシューティング](#12-トラブルシューティング)
13. [ログ収集と診断ツール](#13-ログ収集と診断ツール)
14. [バージョン管理とアップデート](#14-バージョン管理とアップデート)
15. [よくある質問（FAQ）](#15-よくある質問faq)

---

## 1. ZCC とは

**Zscaler Client Connector（ZCC）** は、エンドポイントデバイス（PC・スマートフォン・タブレット）にインストールされる軽量エージェントです。デバイスからのすべてのインターネットトラフィックおよびプライベートアプリケーションへのアクセスを Zscaler クラウドに転送します。

### 主な役割

| 機能 | 説明 |
|------|------|
| ZIA 転送 | HTTP/HTTPS/その他トラフィックを Zscaler Internet Access へ転送 |
| ZPA 接続 | プライベートアプリへのゼロトラストアクセスを確立 |
| ZDX 監視 | エンドポイントのデジタル体験データを収集・送信 |
| デバイスポスチャー | デバイスのセキュリティ状態（OS バージョン、AV 状態等）を評価 |

### 従来型 VPN との違い

| 項目 | 従来型 VPN | ZCC + ZPA |
|------|-----------|-----------|
| アクセス範囲 | ネットワーク全体へのアクセス（過剰権限） | アプリケーション単位の最小権限 |
| 認証 | IP ベースのトンネル | ユーザー・デバイス・アプリの三者認証 |
| パフォーマンス | データセンター経由で遅延増大 | 最寄りの Zscaler PoP へ直接接続 |
| 可視性 | トラフィック検査なし | フルインライン検査（DLP・マルウェア対策等） |
| スケール | VPN 集約ポイントがボトルネック | クラウドスケールで自動拡張 |

---

## 2. アーキテクチャ概要

```
[ エンドポイント ]
       │  ZCC が起動してトラフィックをインターセプト
       │
       ▼
[ ZCC エージェント ]
  ├─ ZIA トンネル（Z-Tunnel 2.0）──▶ Zscaler PoP ──▶ インターネット
  ├─ ZPA トンネル（mTLS）──────────▶ ZPA クラウド ──▶ App Connector ──▶ 内部アプリ
  └─ ZDX データ収集 ──────────────▶ ZDX クラウド（パフォーマンス分析）
```

### 接続フロー（ZIA）

1. ZCC がトラフィックを検知し、転送プロファイルに基づき Zscaler PoP へ送信
2. Zscaler クラウドで URL フィルタリング・SSL インスペクション・マルウェアスキャンを実施
3. 許可されたトラフィックのみインターネットへ転送

### 接続フロー（ZPA）

1. ユーザーが内部 FQDN（例: `app.corp.local`）にアクセス
2. ZCC が ZPA ブローカーへ接続リクエストを送信
3. ZPA がアクセスポリシーを評価（ユーザー・グループ・デバイスポスチャー）
4. 許可された場合、最寄りの App Connector 経由でアプリへ接続確立

---

## 3. 対応プラットフォーム

| OS | バージョン | 備考 |
|----|----------|------|
| Windows | 10（1803以降）、11 | x64 のみ（ARM は一部制限あり） |
| macOS | 12 Monterey 以降 | Apple Silicon (M1/M2/M3) 対応 |
| iOS / iPadOS | 16 以降 | App Store 経由 |
| Android | 8.0（Oreo）以降 | Google Play / EMM 経由 |
| Linux | Ubuntu 20.04+、CentOS 7+、RHEL 7+ | CLI ベース |
| ChromeOS | Chrome Enterprise 対応 | Android アプリまたは拡張機能 |

> **注意**: Linux 版は ZIA 転送のみ対応（ZPA はサポート外の場合あり）。最新の対応状況は Zscaler リリースノートを確認してください。

---

## 4. トンネルモード

ZCC は複数のトンネルモードをサポートし、ネットワーク環境や要件に応じて選択します。

### 4.1 Z-Tunnel 1.0

- **プロトコル**: UDP（DTLS）
- **対象トラフィック**: HTTP/HTTPS のみ
- **用途**: レガシー環境や特定のネットワーク制約がある場合
- **制限**: 非 HTTP トラフィックは転送されない

### 4.2 Z-Tunnel 2.0（推奨）

- **プロトコル**: DTLS（UDP 443）/ TLS（TCP 443）フォールバック
- **対象トラフィック**: すべての TCP/UDP トラフィック（L4〜L7）
- **メリット**: 
  - DNS クエリの転送が可能（DNSoH セキュリティ）
  - 非 HTTP アプリケーション（カスタムポート等）も保護
  - クラウドファイアウォール機能をフル活用

### 4.3 Strict Enforcement モード

- トンネルが確立されない限り、インターネット・内部アプリへのアクセスを完全ブロック
- セキュリティポリシーの回避を防止
- リモートワーク環境での強制適用に有効

### 4.4 分割トンネル（Split Tunnel）

```
# 分割トンネル設定例（管理コンソール）
分割トンネル: 有効
Zscaler 経由: 10.0.0.0/8 以外のすべての宛先
ローカル直接: 192.168.0.0/16（社内プリンター等）
バイパス: 特定の FQDN（例: internal-legacy.corp.local）
```

---

## 5. インストール方法

### 5.1 Windows（手動インストール）

```powershell
# MSI ダウンロード後、サイレントインストール
msiexec /i "Zscaler-windows-x64-<version>.msi" /quiet /norestart

# 事前設定（クラウド URL を埋め込む場合）
msiexec /i "Zscaler-windows-x64-<version>.msi" /quiet CLOUDNAME="company.zscaler.com"
```

### 5.2 macOS（手動インストール）

```bash
# PKG ファイルをダウンロードして実行
sudo installer -pkg Zscaler-osx-<version>.pkg -target /

# クラウド URL を指定してインストール
sudo installer -pkg Zscaler-osx-<version>.pkg -target / \
  -applyChoiceChangesXML zscaler_config.xml
```

### 5.3 Linux（DEB / RPM）

```bash
# Ubuntu / Debian
sudo dpkg -i zscaler_<version>_amd64.deb
sudo systemctl enable zscaler
sudo systemctl start zscaler

# CentOS / RHEL
sudo rpm -ivh zscaler-<version>.x86_64.rpm
sudo systemctl enable zscaler
sudo systemctl start zscaler
```

---

## 6. 転送プロファイルとアプリプロファイル

### 6.1 転送プロファイル（Forwarding Profile）

転送プロファイルでは、どのトラフィックを Zscaler 経由にするかを定義します。

| 設定項目 | 説明 |
|---------|------|
| PAC ファイル | プロキシ自動設定（HTTP/HTTPS 転送の細かい制御） |
| Z-Tunnel 2.0 | すべての TCP/UDP トラフィックを転送 |
| バイパスリスト | ローカルや特定 FQDN を Zscaler 経由外にする |
| 信頼されたネットワーク | 社内 LAN 接続時に ZCC を無効化するオプション |

```
# 転送プロファイル - 信頼ネットワーク設定例
信頼ネットワーク検出: 有効
検出方法: DNS サフィックス（corp.local）
信頼ネットワーク内の動作: ZIA 無効（ZPA は有効のまま）
```

### 6.2 アプリプロファイル（App Profile）

アプリプロファイルは ZCC の動作・UI・セキュリティ設定を制御します。

| 設定 | 説明 |
|------|------|
| ZCC の無効化をブロック | ユーザーがエージェントを停止できないよう制限 |
| パスワード保護 | エージェント設定変更にパスワードを要求 |
| 自動更新 | ZCC のバージョン自動更新の有効化 |
| ログレベル | デバッグ・情報・警告等のログ出力設定 |
| 通知設定 | ブロック時のユーザー通知カスタマイズ |

---

## 7. 認証方式

### 7.1 SAML SSO（推奨）

最もよく使われる認証方式。ブラウザベースの SAML フローで IdP（Azure AD、Okta、Google Workspace 等）と連携します。

```
認証フロー:
1. ZCC 起動 → ブラウザで IdP 認証画面を表示
2. ユーザーが IdP（Azure AD 等）で認証
3. SAML アサーション → Zscaler クラウドで検証
4. 認証成功 → ZCC がトンネルを確立
```

### 7.2 Kerberos 認証

Active Directory 環境でシームレスなシングルサインオンを実現します。ユーザーが Windows にログイン済みの場合、追加認証なしで ZCC が認証されます。

### 7.3 デバイス証明書認証

MDM で配布したクライアント証明書を使い、デバイスレベルの認証を実施します。ZPA のマシントンネルで特に有効です。

```
設定要件:
- PKI または MDM が発行したクライアント証明書
- Zscaler 管理コンソールで信頼されたルート CA を登録
- ZPA のデバイスポスチャールールで証明書の有無を検証
```

---

## 8. ZPA との連携

### 8.1 ユーザートンネル

ユーザーが ZCC でログインした後に確立されるトンネルです。ZPA のアクセスポリシーはユーザー・グループ・デバイスポスチャーに基づいて評価されます。

```
接続フロー:
ZCC（ユーザーログイン済み）→ ZPA ブローカー → アクセスポリシー評価
→ 許可 → App Connector → 内部アプリ（SSH / RDP / Web 等）
```

### 8.2 マシントンネル（Machine Tunnel）

ユーザーログイン前（OS 起動直後）に確立されるトンネルです。以下のユースケースで必要です。

| ユースケース | 説明 |
|------------|------|
| Active Directory ドメイン参加 | ログイン前に DC への通信が必要 |
| GPO の適用 | グループポリシーをログイン前に取得 |
| SCCM / Intune | 管理エージェントのチェックイン |
| MDM エンロール | デバイス管理コマンドの受信 |

```
# マシントンネル設定要件
- ZCC に機械証明書を配布（MDM 経由）
- ZPA の Machine Tunnel 設定を有効化
- App Segment でマシン用アプリを定義
- Service Account でマシン認証用 IdP を設定
```

---

## 9. ZDX エージェント機能

ZCC は ZDX エージェントとしても機能し、デジタル体験監視データを収集します。

### 収集データ

| データ種別 | 内容 |
|----------|------|
| デバイス性能 | CPU 使用率、メモリ、ディスク I/O |
| ネットワーク | レイテンシ、パケットロス、WiFi/有線 |
| Webプローブ | 特定 URL への応答時間、HTTP ステータス |
| アプリプローブ | M365、Zoom、Webex 等の SaaS パフォーマンス |
| WiFi 情報 | SSID、RSSI（信号強度）、チャンネル |

### ZDX スコア

0〜100 のスコアでエンドユーザーの体験品質を表現します。

- **90〜100**: 良好
- **70〜89**: 注意
- **0〜69**: 問題あり（アラート発報対象）

---

## 10. ポリシー設定

### 10.1 デバイスポスチャーチェック

ZPA のアクセスポリシーで使用可能なデバイスポスチャー評価項目：

| チェック項目 | 説明 |
|------------|------|
| OS バージョン | 最低 OS バージョンを要件として設定 |
| ZCC バージョン | 古いバージョンの ZCC をブロック |
| アンチウイルス | AV ソフトの有無・最終更新日 |
| ディスク暗号化 | BitLocker / FileVault の有効化確認 |
| クライアント証明書 | MDM 発行証明書の有無 |
| ドメイン参加 | AD ドメイン参加状態の確認 |

### 10.2 ユーザーによる操作制限

```
# アプリプロファイルでの制限設定
ZCC の一時停止を禁止: 有効
  └─ パスワード保護: 管理者パスワードを設定
ZCC のアンインストールを禁止: 有効
ログアウトを禁止: 有効（高セキュリティ環境向け）
```

---

## 11. MDM/UEM を使った大規模展開

### 11.1 Microsoft Intune（Windows）

```xml
<!-- Intune Win32 アプリとして配布 -->
インストールコマンド:
msiexec /i "Zscaler-windows-x64-4.x.msi" /quiet /norestart
CLOUDNAME="tenant.zscloud.net"
USERDOMAIN="corp.com"

検出ルール:
レジストリ: HKLM\SOFTWARE\Zscaler\ZCC
値: Version
```

### 11.2 Jamf Pro（macOS）

```xml
<!-- Jamf Policy スクリプト -->
#!/bin/bash
CLOUD_NAME="tenant.zscloud.net"
installer -pkg /tmp/Zscaler-osx.pkg -target /
/Library/Application\ Support/Zscaler/ZscalerService \
  --cloudname $CLOUD_NAME
```

### 11.3 VMware Workspace ONE / MobileIron

- App Catalog 経由で iOS / Android に配布
- Managed App Config で CLOUDNAME、USERDOMAIN を事前設定
- デバイス証明書の自動配布と ZPA マシントンネル連携が可能

### 11.4 展開チェックリスト

- [ ] クラウド URL（`*.zscloud.net` 等）の確認
- [ ] PAC ファイルまたは Z-Tunnel 2.0 の選択
- [ ] IdP（Azure AD / Okta）との SAML 連携設定
- [ ] デバイス証明書の配布（ZPA 利用時）
- [ ] ファイアウォール / プロキシのバイパス設定（Zscaler PoP IP）
- [ ] パイロット展開（50〜100 ユーザー）でのテスト
- [ ] ロールバック手順の準備

---

## 12. トラブルシューティング

### 12.1 接続できない / トンネルが確立されない

| 症状 | 原因 | 対処法 |
|------|------|--------|
| 認証ループが発生 | SAML IdP の設定誤り | ZCC ログで SAML レスポンスを確認 |
| トンネルが切断される | UDP ポート 443 ブロック | TCP フォールバック（TLS）が有効か確認 |
| 特定サイトにアクセスできない | SSL インスペクションの影響 | SSL バイパスリストに追加 |
| ZPA アプリに接続できない | App Connector が停止 | Connector のヘルス状態を確認 |
| ログイン画面が表示されない | キャプティブポータル干渉 | キャプティブポータル検出を有効化 |

### 12.2 パフォーマンスが低下する

1. **最寄りの PoP を確認**: `ZCC > About > Zscaler Node` で接続先 PoP を確認
2. **Z-Tunnel バージョン確認**: Z-Tunnel 2.0 が有効か確認
3. **分割トンネル設定**: 大容量の内部トラフィックはバイパスを検討
4. **SSL インスペクション**: 動画会議ツール（Zoom 等）は SSL バイパスリストに追加

### 12.3 証明書エラー

```
# 証明書インストールの確認（Windows）
certmgr.msc を開く
信頼されたルート証明機関 → Zscaler Root CA が存在するか確認

# macOS
security find-certificate -a -c "Zscaler" /Library/Keychains/System.keychain
```

### 12.4 デバイスポスチャーチェック失敗

1. ZPA コンソール → **Device Posture** でルール詳細を確認
2. ZCC の **About** 画面でポスチャー評価結果を確認
3. OS バージョン、AV 状態、証明書の各項目を個別に検証

---

## 13. ログ収集と診断ツール

### 13.1 ログファイルの場所

| OS | パス |
|----|------|
| Windows | `C:\ProgramData\Zscaler\ZCC\` |
| macOS | `/Library/Application Support/Zscaler/ZCC/` |
| Linux | `/var/log/zscaler/` |

### 13.2 ログの収集（サポート提出用）

```
# ZCC のログパッケージを収集
Windows: ZCC トレイアイコン → 右クリック → 「Collect Logs」
macOS:   Zscaler アイコン → 「Collect Logs」
Linux:   sudo /opt/zscaler/bin/zcc-support.sh
```

### 13.3 主要なログファイル

| ファイル | 内容 |
|---------|------|
| `ZscalerService.log` | メインサービスログ（接続・認証・エラー） |
| `ZscalerTunnel.log` | トンネル確立・切断の詳細 |
| `ZscalerApp.log` | UI とユーザー操作のログ |
| `ZscalerDiag.log` | 診断情報（PoP 接続先等） |

### 13.4 コマンドラインツール

```bash
# Linux での ZCC 状態確認
sudo systemctl status zscaler

# ZCC デーモンの再起動
sudo systemctl restart zscaler

# 接続状態の確認
/opt/zscaler/bin/zcc status

# PoP 接続テスト
curl -v https://gateway.zscaler.net/ip
```

---

## 14. バージョン管理とアップデート

### 14.1 自動アップデート設定

ZCC の自動アップデートはアプリプロファイルで制御します。

```
アプリプロファイル設定:
自動アップデート: 有効
アップデートチャンネル: Stable（推奨）/ Beta（先行テスト用）
アップデートのスケジュール: 業務時間外（例: 23:00〜05:00）
ユーザーへの通知: 有効（再起動が必要な場合）
```

### 14.2 バージョン固定（ロールバック）

問題発生時は特定バージョンへの固定が可能です。管理コンソールの **App Profile** → **ZCC Version** で指定します。

### 14.3 段階的ロールアウト

大規模組織では以下の段階的展開を推奨します：

1. **Phase 1**: IT チーム（10〜20 名）で新バージョンを検証
2. **Phase 2**: 拠点ごとにパイロット展開（全体の 10%）
3. **Phase 3**: 残り全ユーザーへ一括展開

---

## 15. よくある質問（FAQ）

**Q: ZCC をインストールしないとインターネットにアクセスできなくなりますか？**  
A: Strict Enforcement モードを有効にした場合はその通りです。未設定の場合、ZCC なしでも直接インターネットへアクセス可能ですが、セキュリティポリシーが適用されません。

**Q: VPN と ZCC を共存できますか？**  
A: 一部の VPN クライアントと共存可能ですが、トラフィックの優先度や経路が競合することがあります。事前に検証が必要です。ZPA を使えば VPN が不要になるため、段階的な移行を推奨します。

**Q: ZCC が停止した場合、インターネットにはアクセスできますか？**  
A: アプリプロファイルの設定によります。Strict Enforcement が有効な場合はアクセス不可、無効な場合は保護なしで直接アクセスとなります。

**Q: プロキシ環境下で ZCC は動作しますか？**  
A: 動作しますが、ZCC 自身が Zscaler PoP への通信に既存のプロキシを経由する設定が必要です。アプリプロファイルで「ファイアウォール経由の接続」を設定してください。

**Q: ZCC ログの保存期間はどのくらいですか？**  
A: デフォルトではローカルに最大 7 日間のログが保存されます。Nanolog Streaming Service（NSS）を設定することで SIEM への転送・長期保存が可能です。

**Q: macOS で ZCC をインストールしたら「システム拡張機能を承認」と表示された。**  
A: macOS のセキュリティ仕様で、初回インストール時にカーネル拡張またはシステム拡張の承認が必要です。MDM を使った展開では、PPPC（Privacy Preferences Policy Control）プロファイルを事前配布して承認をスキップできます。

---

## 参考リンク

- Zscaler Help Center: https://help.zscaler.com/zia/zscaler-client-connector
- ZCC リリースノート: https://help.zscaler.com/client-connector/release-summary
- ZPA + ZCC 連携ガイド: https://help.zscaler.com/zpa/configuring-zpa-client
- デバイスポスチャーガイド: https://help.zscaler.com/zpa/device-posture
