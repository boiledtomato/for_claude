# Microsoft Learn — Microsoft Entra / ハイブリッド ID (Connect / Cloud Sync) (part 3)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 49

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-pta-quick-start"} -->
## Microsoft Entra パススルー認証 - クイックスタート - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start
- Service: entra-id / hybrid-connect
- Article date: 2025-09-09
- Summary: この記事では、Microsoft Entra パススルー認証の使用を開始する方法について説明します。

### Microsoft Entra パススルー認証をデプロイする

Microsoft Entra パススルー認証を使うと、ユーザーは同じパスワードを使って、オンプレミスのアプリケーションとクラウドベースのアプリケーションの両方にサインインできます。 パススルー認証では、オンプレミスの Active Directory に対してパスワードを直接検証することで、ユーザーをサインインします。

重要

AD FS (またはその他のフェデレーション テクノロジ) からパススルー認証に移行する場合は、 [アプリケーションを Microsoft Entra ID に移行するためのリソースを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources)表示します。

メモ

Azure Government クラウドでパススルー認証をデプロイする場合は、Azure Government の [ハイブリッド ID に関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-government-cloud)を参照してください。

テナントでパススルー認証をデプロイするには、次の手順を実行します。

### 手順 1:前提条件を確認する

次の前提条件が満たされていることを確認します。

重要

セキュリティの観点から、管理者は PTA エージェントを実行しているサーバーをドメイン コントローラーとして扱う必要があります。 PTA エージェント サーバーは、「[攻撃に対してドメイン コントローラーをセキュリティで保護する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/securing-domain-controllers-against-attack)」で説明されている内容に沿って強化する必要があります。

#### Microsoft Entra 管理センターで

1. Microsoft Entra テナントで、クラウド専用ハイブリッド ID 管理者アカウントまたはハイブリッド ID 管理者アカウントを作成します。 その方法を採用すると、オンプレミス サービスが利用できなくなったとき、テナントの構成を管理できます。 [クラウド専用のハイブリッド ID 管理者アカウントを追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users)方法について確認してください。 テナントからロックアウトされないようにするには、この手順を必ず完了する必要があります。
2. Microsoft Entra テナントに 1 つ以上の[カスタム ドメイン名](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)を追加します。 ユーザーは、このドメイン名のいずれかを使用してサインインできます。

#### オンプレミスの環境の場合

1. Microsoft Entra Connect を実行するために Windows Server 2025、Windows Server 2022、Windows Server 2019、または Windows Server 2016 を実行するサーバーを特定します。 まだ有効になっていない場合は、[サーバーで TLS 1.2 を有効](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#enable-tls-12-for-azure-ad-connect)にします。 このサーバーを、パスワードの検証が必要なユーザーと同じ Active Directory フォレストに追加します。 Windows Server Core バージョンでの Pass-Through 認証エージェントのインストールはサポートされていないことに注意してください。
2. [最新バージョンの Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) を、前の手順で特定したサーバーにインストールします。 Microsoft Entra Connect が既に実行されている場合は、そのバージョンがサポートされていることを確認します。

    メモ

    Microsoft Entra Connect のバージョン 1.1.557.0、1.1.558.0、1.1.561.0、1.1.614.0 には、パスワード ハッシュ同期に関連する問題があります。 パスワード ハッシュ同期をパススルー認証と組み合わせて使用 "*しない*" 場合については、[Microsoft Entra Connect のリリース ノート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history)をご覧ください。
3. スタンドアロン認証エージェントを実行できる、TLS 1.2 が有効になっている Windows Server 2025、Windows Server 2022、Windows Server 2019、または Windows Server 2016 を実行する別のサーバーを特定します。 これらの追加のサーバーは、サインイン要求の高可用性を確保するために必要です。 これらのサーバーを、パスワードの検証が必要なユーザーと同じ Active Directory フォレストに追加します。

    重要

    運用環境では、テナントで少なくとも 3 つの認証エージェントを実行することをお勧めします。 テナントごとに 40 個の認証エージェントのシステム制限があります。 また、ベスト プラクティスとして、認証エージェントを実行するすべてのサーバーは Tier 0 システムとして扱うようにしてください ([リファレンス](https://learn.microsoft.com/ja-jp/windows-server/identity/securing-privileged-access/securing-privileged-access-reference-material)を参照)。
4. サーバーと Microsoft Entra ID の間にファイアウォールがある場合は、次の項目を構成します。

    - 認証エージェントが次のポートを介して Microsoft Entra ID に "送信" 要求を行うことができるようにします。

        | ポート番号 | 用途 |
        | --- | --- |
        | **80** | TLS/SSL 証明書を検証する際に証明書失効リスト (CRL) をダウンロードします |
        | **443** | サービスを使用したすべての送信方向の通信を処理する |
        | **8080** (省略可能) | ポート 443 が使用できない場合、認証エージェントはポート 8080 経由で 10 分ごとに状態を報告します。 この状態は、[Microsoft Entra 管理センター](https://entra.microsoft.com)に表示されます。 ポート 8080 は、ユーザー サインインには *使用されません*。 |

        ご利用のファイアウォールが送信元ユーザーに応じて規則を適用している場合は、ネットワーク サービスとして実行されている Windows サービスを送信元とするトラフィックに対してこれらのポートを開放します。
    - ファイアウォールまたはプロキシで DNS エントリを許可リストに追加できる場合は、**\*.msappproxy.net** および **\*.servicebus.windows.net** への接続を追加します。 そうでない場合は、毎週更新される [Azure データセンターの IP 範囲](https://www.microsoft.com/en-us/download/details.aspx?id=56519)へのアクセスを許可します。
    - Azure パススルー エージェントと Azure エンドポイントの間の送信 TLS 通信で、すべての形式のインライン検査と終了を回避します。
    - 発信 HTTP プロキシを使用している場合は、この URL (autologon.microsoftazuread-sso.com) が許可されたリストに登録されていることをご確認ください。 ワイルドカードは受け入れられない場合があるため、この URL を明示的に指定してください。
    - 認証エージェントは初回の登録のために **login.windows.net** と **login.microsoftonline.com** にアクセスする必要があるため、 これらの URL にもファイアウォールを開きます。
    - 証明書の検証の場合は、URL (**crl3.digicert.com:80**、**crl4.digicert.com:80**、**ocsp.digicert.com:80**、**www.d-trust.net:80**、**root-c3-ca2-2009.ocsp.d-trust.net:80**、**crl.microsoft.com:80**、**oneocsp.microsoft.com:80**、**ocsp.msocsp.com:80**) のブロックが解除されます。 これらの URL は他の Microsoft 製品での証明書の検証に使用されるため、これらの URL のブロックが既に解除されている可能性があります。

#### Azure Government クラウドの前提条件

手順 2 に従って Microsoft Entra Connect を使用したパススルー認証を有効にする前に、[Microsoft Entra 管理センター](https://entra.microsoft.com)から PTA エージェントの最新リリースをダウンロードしてください。 エージェントを確実にバージョン **1.5.1742.0** 以降にする必要があります。 エージェントを確認するには、[認証エージェントのアップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-upgrade-preview-authentication-agents)に関する記事を参照してください。

エージェントの最新リリースをダウンロードしたら、次の手順に進んで Microsoft Entra Connect を使用したパススルー認証を構成します。

### 手順 2:機能を有効にする

[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) を使用したパススルー認証を有効にします。

重要

Microsoft Entra Connect のプライマリ サーバーまたはステージング サーバーでパススルー認証を有効にできます。 プライマリ サーバーから有効にすることを強くお勧めします。 今後 Microsoft Entra Connect ステージング サーバーを設定する場合は、引き続きサインイン オプションとして [パススルー認証] を選択する **必要があります** 。別のオプションを選択すると、テナントのパススルー認証が **無効** になり、プライマリ サーバーの設定がオーバーライドされます。

Microsoft Entra Connect を初めてインストールする場合は、[カスタム インストール パス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom)を選択します。 **[ユーザー サインイン]** ページで、**サインオン方式**として **[パススルー認証]** を選択します。 正常に完了すると、Microsoft Entra Connect と同じサーバーにパススルー認証エージェントがインストールされます。 また、テナントでパススルー認証機能が有効になります。

[Image: Microsoft Entra Connect: ユーザー サインイン]

[高速インストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-express) パスまたは[カスタム インストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom) パスを使って Microsoft Entra Connect を既にインストールしている場合は、Microsoft Entra Connect で **[ユーザー サインインの変更]** タスクを選択してから **[次へ]** を選択します。 次に、サインイン方式として **[パススルー認証]** を選択します。 正常に完了すると、Microsoft Entra Connect と同じサーバーにパススルー認証エージェントがインストールされ、テナントで機能が有効になります。

[Image: Microsoft Entra Connect: ユーザー サインインの変更]

重要

パススルー認証はテナント レベルの機能です。 有効にすると、テナントに含まれる "*すべての*" マネージド ドメインのユーザー サインインに影響を及ぼします。 Active Directory フェデレーション サービス (AD FS) からパススルー認証に切り替える場合は、12 時間以上経ってから AD FS インフラストラクチャをシャットダウンする必要があります。 これは、移行中もユーザーが Exchange ActiveSync にサインインできるようにするための措置です。 AD FS からパススルー認証への移行の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources)で公開されているデプロイ計画をご覧ください。

### 手順 3:機能をテストする

この手順に従って、パススルー認証の有効化を正しく行ったことを確認します。

1. テナントのハイブリッド ID 管理者の資格情報を使って、[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Microsoft Entra ID]** を選択します。
3. **[Microsoft Entra Connect]** を選択します。
4. **[パススルー認証]** 機能が **[有効]** と表示されていることを確認します。
5. **[パススルー認証]** を選択します。 **[パススルー認証]** ウィンドウには、認証エージェントがインストールされているサーバーが一覧表示されます。

    [Image: Microsoft Entra 管理センターの [Microsoft Entra Connect] ウィンドウを示すスクリーンショット。]

    [Image: Microsoft Entra 管理センター: [パススルー認証] ペインを示すスクリーンショット。]

この段階で、テナントに含まれるすべてのマネージド ドメインのユーザーが、パススルー認証を使用してサインインできます。 ただし、フェデレーション ドメインのユーザーは引き続き、AD FS または既に構成済みのその他のフェデレーション プロバイダーを使用してサインインします。 ドメインをフェデレーションから管理対象に変換すると、そのドメインのすべてのユーザーが、パススルー認証を使用したサインインを自動的に開始します。 パススルー認証機能は、クラウドのみのユーザーには影響しません。

### 手順 4:高可用性を確保する

運用環境にパススルー認証をデプロイする場合は、追加のスタンドアロン認証エージェントをインストールする必要があります。 これらの認証エージェントは、Microsoft Entra Connect を実行しているサーバー "*以外*" のサーバーにインストールします。 この設定により、ユーザー サインイン要求の高可用性が確保されます。

重要

運用環境では、テナントで少なくとも 3 つの認証エージェントを実行することをお勧めします。 テナントごとに 40 個の認証エージェントのシステム制限があります。 また、ベスト プラクティスとして、認証エージェントを実行するすべてのサーバーは Tier 0 システムとして扱うようにしてください ([リファレンス](https://learn.microsoft.com/ja-jp/windows-server/identity/securing-privileged-access/securing-privileged-access-reference-material)を参照)。

複数のパススルー認証エージェントをインストールすると高可用性が確保されますが、認証エージェント間の確定的な負荷分散は提供されません。 テナントに必要な認証エージェントの数を決定するには、テナントで発生することが予想されるサインイン要求のピーク時と平均の負荷を検討します。 ベンチマークとして、1 つの認証エージェントでは、標準的な 4 コア CPU、16 GB RAM サーバー上で 1 秒あたり 300 - 400 の認証を処理できます。

ネットワーク トラフィックを見積もるには、サイズ設定に関する次のガイダンスに従ってください。

- 各要求のペイロード サイズは、(0.5K + 1K \* num\_of\_agents) バイトです。つまり、Microsoft Entra Connect から認証エージェントへのデータ量に相当します。 ここで "num\_of\_agents" は、テナントに登録されている認証エージェントの数を示します。
- 各応答のペイロード サイズは 1K バイトです。つまり、認証エージェントから Microsoft Entra ID へのデータ量に相当します。

ほとんどのお客様の場合、高可用性と大容量を確保するには、合計 3 つの認証エージェントがあれば十分です。 サインインの待機時間を向上させるために、認証エージェントは、ドメイン コントローラーの近くにインストールする必要があります。

最初に、次の手順に従って、認証エージェント ソフトウェアをダウンロードします。

1. 最新バージョン (1.5.193.0 以降) の認証エージェントをダウンロードするには、テナントのハイブリッド ID 管理者の資格情報で [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[Microsoft Entra ID]** を選択します。
3. **[Microsoft Entra Connect**] を選択し、[**同期の接続**] を選択してから **[パススルー認証**] を選択し、次に [**ダウンロード**] を選択します。
4. [ **同意する条件とダウンロード** ] ボタンを選択します。

    [Image: Microsoft Entra 管理センター: 認証エージェントのダウンロード ボタンを示すスクリーンショット。]

メモ

認証エージェント ソフトウェアは[ここ](https://aka.ms/getauthagent)から直接ダウンロードすることもできます。 認証エージェントをインストールする "[前](https://aka.ms/authagenteula)" に*サービス使用条件*を確認して同意してください。

スタンドアロン認証エージェントをデプロイする方法は 2 つあります。

1 つ目は、ダウンロードした認証エージェントの実行可能ファイルを実行し、プロンプトに従ってテナントのハイブリッド ID 管理者資格情報を提供するという対話的な方法です。

2 つ目は、自動デプロイ スクリプトを作成して実行できます。 これは、一度に複数の認証エージェントをデプロイするときや、ユーザー インターフェイスが有効になっていない、またはリモート デスクトップでアクセスできない Windows サーバーに認証エージェントをインストールするときに便利です。 この方法を使用する手順を以下に示します。

1. 次のコマンドを実行して、認証エージェント `AADConnectAuthAgentSetup.exe REGISTERCONNECTOR="false" /q` をインストールしてください。
2. 認証エージェントは、PowerShell を使用して Microsoft のサービスに登録できます。 テナントのハイブリッド ID 管理者のユーザー名とパスワードを格納する PowerShell 資格情報オブジェクト `$cred` を作成します。 `<username>` と `<password>` を置き換えて、次のコマンドを実行します。

```powershell
$User = "<username>"
$PlainPassword = '<password>'
$SecurePassword = $PlainPassword | ConvertTo-SecureString -AsPlainText -Force
$cred = New-Object -TypeName System.Management.Automation.PSCredential -ArgumentList $User, $SecurePassword
```

1. **C:\Program Files\Microsoft Azure AD Connect 認証エージェント**に移動し、作成済みの `$cred` オブジェクトを使用して次のスクリプトを実行します。

```powershell
RegisterConnector.ps1 -modulePath "C:\Program Files\Microsoft Azure AD Connect Authentication Agent\Modules\" -moduleName "PassthroughAuthPSModule" -Authenticationmode Credentials -Usercredentials $cred -Feature PassthroughAuthentication
```

重要

認証エージェントが仮想マシンにインストールされている場合、仮想マシンを複製して別の認証エージェントを設定することはできません。 この方法は**サポートされていません**。

### 手順 5:スマート ロックアウト機能を構成する

スマート ロックアウトは、組織のユーザーのパスワードを推測したり、ブルート フォース方法を使用して侵入しようとする悪意のあるユーザーのロックアウトを支援します。 Microsoft Entra ID でのスマート ロックアウトの設定と、オンプレミスの Active Directory での適切なロックアウトの設定の両方または一方を構成することにより、攻撃を Active Directory に到達する前に排除できます。 テナントにスマート ロックアウトの設定を構成してユーザー アカウントを保護する方法の詳細については、[こちらの記事](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-pta-security-deep-dive"} -->
## Microsoft Entra パススルー認証のセキュリティの深掘り - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-security-deep-dive
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra パススルー認証によってオンプレミス アカウントを保護する方法について説明します。

この記事では、Microsoft Entra パススルー認証のしくみについてより詳しく説明します。 ここでは、機能のセキュリティ面について重点的に説明します。 この記事は、セキュリティおよび IT 管理者、コンプライアンスおよびセキュリティの最高責任者、あらゆる規模の組織や企業で IT セキュリティとコンプライアンスを担当するその他の IT プロフェッショナルを対象としています。

ここで扱うトピックは次のとおりです。

- 認証エージェントのインストールおよび登録方法に関する詳細な技術情報。
- ユーザー サインイン時のパスワードの暗号化に関する詳細な技術情報。
- オンプレミスの認証エージェントと Microsoft Entra ID 間のチャネルのセキュリティ。
- 認証エージェントの運用上のセキュリティを維持する方法についての詳細な技術情報。

### パススルー認証の主要なセキュリティ機能

パススルー認証には、次の主要なセキュリティ機能があります。

- この機能は、テナント間でのサインイン要求を分離する、セキュリティで保護されたマルチテナント アーキテクチャ上に構築されています。
- オンプレミス パスワードが何らかの形でクラウドに保存されることはありません。
- パスワード検証要求のリッスンおよび応答を行うオンプレミスの認証エージェントは、ネットワーク内からの送信接続のみを行います。 これらの認証エージェントを境界ネットワーク (*DMZ*、"非武装地帯"、"スクリーン サブネット" とも呼ばれます) にインストールする必要はありません。 ベスト プラクティスとして、認証エージェントを実行するすべてのサーバーは Tier 0 システムとして扱うようにしてください ([リファレンス](https://learn.microsoft.com/ja-jp/windows-server/identity/securing-privileged-access/securing-privileged-access-reference-material)を参照)。
- 認証エージェントから Microsoft Entra ID への送信通信で使用されるのは、標準ポート (ポート 80 とポート 443) のみです。 ファイアウォールで受信ポートを開く必要はありません。
- 認証済みのすべての送信通信でポート 443 が使用されます。
- ポート 80 が使用されるのは、証明書失効リスト (CRL) をダウンロードして、この機能で使用される証明書が失効していないことを確認する場合のみです。
- ネットワーク要件の完全な一覧については、「[Microsoft Entra パススルー認証: クイック スタート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start#step-1-check-the-prerequisites)」をご覧ください。
- ユーザーがサインイン時に指定するパスワードは、Windows Server Active Directory (Windows Server AD) に対する検証でオンプレミスの認証エージェントに受け入れられる前に、クラウドで暗号化されます。
- Microsoft Entra ID とオンプレミスの認証エージェント間の HTTPS チャネルは、相互認証を使用して保護されます。
- パススルー認証では、[Microsoft Entra 条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) (多要素認証 (MFA) を含む) と[レガシ認証のブロック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-conditions)、[フィルター処理によるブルート フォース パスワード攻撃の除外](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)により、作業を中断せずに、ユーザー アカウントを保護します。

### パススルー認証に関連するコンポーネント

Microsoft Entra ID の運用、サービス、データのセキュリティに関する一般的な詳細については、[トラスト センター](https://azure.microsoft.com/support/trust-center/)のページをご覧ください。 ユーザー サインインにパススルー認証を使用する場合には、次のコンポーネントが関連します。

- **Microsoft Entra セキュリティ トークン サービス (Microsoft Entra STS)**: サインイン要求を処理し、必要に応じてユーザーのブラウザー、クライアント、またはサービスにセキュリティ トークンを発行するステートレスな STS。
- **Azure Service Bus**:エンタープライズ メッセージングと中継通信を使用するクラウド対応通信を提供し、オンプレミスのソリューションをクラウドに接続するのに役立ちます。
- **Microsoft Entra Connect 認証エージェント**: パスワード検証要求のリッスンと応答を行うオンプレミス コンポーネント。
- **Azure SQL Database**: メタデータや暗号化キーを含む、テナントの認証エージェントに関する情報が保持されています。
- **Windows Server AD**: ユーザー アカウントとそのパスワードが格納されるオンプレミスの Active Directory。

### 認証エージェントのインストールと登録

次のいずれかのアクションを実行すると、認証エージェントがインストールされ、Microsoft Entra ID に登録されます。

- [Microsoft Entra Connect を使用してパススルー認証を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start#step-2-enable-the-feature)
- [認証エージェントを追加してサインイン要求の高可用性を確保する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start#step-4-ensure-high-availability)

認証エージェントを運用させるには、次の 3 つの主なフェーズが必要です。

- インストール
- 登録
- 初期化

次のセクションでは、これらのフェーズを詳細に説明します。

#### 認証エージェントのインストール

(Microsoft Entra Connect またはスタンドアロン インスタンスを使用して) オンプレミス サーバーに認証エージェントをインストールできるのは、ハイブリッド ID 管理者のアカウントのみです。

インストールでは、次の 2 つの新しい項目が、**[コントロール パネル]**&gt;**[プログラム]**&gt;**[プログラムと機能]** の一覧に追加されます。

- 認証エージェント アプリケーション自体。 このアプリケーションは [NetworkService](https://learn.microsoft.com/ja-jp/windows/win32/services/networkservice-account) 権限で実行されます。
- 認証エージェントの自動更新で使用されるアップデーター アプリケーション。 このアプリケーションは [LocalSystem](https://learn.microsoft.com/ja-jp/windows/win32/services/localsystem-account) 権限で実行されます。

重要

セキュリティの観点から、管理者はパススルー認証エージェントを実行しているサーバーをドメイン コントローラーとして扱う必要があります。 パススルー認証エージェント エージェント サーバーは、「[攻撃に対してドメイン コントローラーをセキュリティで保護する](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/securing-domain-controllers-against-attack)」で説明されているように強化する必要があります。

#### 認証エージェントの登録

認証エージェントは、インストール後に自身を Microsoft Entra ID に登録します。 Microsoft Entra ID は、セキュリティで保護された Microsoft Entra ID との通信で使用できる一意のデジタル ID 証明書を各認証エージェントに割り当てます。

登録手順では、認証エージェントとテナントとのバインドも行います。 その後、Microsoft Entra ID は、この特定の認証エージェントのみがテナントのパスワード検証要求を処理する権限があることを認識できます。 この手順は、登録する新しい認証エージェントごとに繰り返されます。

認証エージェントは、次の手順を使用して、Microsoft Entra ID に自身を登録します。

[Image: Azure AD への認証エージェントの登録を示す図。.]

1. Microsoft Entra はまず、ハイブリッド ID 管理者が自分の資格情報を使用して Microsoft Entra ID にサインインすることを要求します。 認証エージェントはサインイン時、ユーザーに代わって使用可能なアクセス トークンを取得します。
2. 次に、認証エージェントは公開キーと秘密キーのキー ペアを生成します。

    - このキー ペアは、標準の RSA 2048 ビットの暗号化を使用して生成されます。
    - 秘密キーは、認証エージェントが存在するオンプレミス サーバーに保持されます。
3. 認証エージェントは要求に含まれる以下のコンポーネントを使用して、HTTPS 経由で Microsoft Entra ID に登録要求を行います。

    - エージェントが取得したアクセス トークン。
    - 生成された公開キー。
    - 証明書署名要求 (*CSR* または*証明書要求*)。 この要求により、Microsoft Entra ID を証明機関 (CA) として使用して、デジタル ID 証明書を申請します。
4. Microsoft Entra ID は登録要求内のアクセス トークンを検証し、要求がハイブリッド ID 管理者からのものであることを確認します。
5. 次に、Microsoft Entra ID はデジタル ID 証明書に署名して、認証エージェントに送信します。

    - Microsoft Entra ID 内のルート CA は証明書の署名に使用されます。

    注

    この CA は、Windows の信頼されたルート証明機関ストアには *存在しません*。

    - この CA はパススルー認証機能でのみ使用されます。 この CA は、認証エージェントを登録するときの CSR への署名でのみ使用されます。
    - 他の Microsoft Entra サービスはこの CA を使用しません。
    - 証明書の件名 ("識別名" または *DN* とも呼ばれます) はテナント ID に設定されます。 この DN はテナントを一意に識別する GUID です。 この DN により、証明書の範囲がテナントのみでの使用に限定されます。
6. Microsoft Entra ID では、Azure SQL Database 内のデータベースに認証エージェントの公開キーが格納されます。 Microsoft Entra ID のみがデータベースにアクセスできます。
7. 発行された証明書は、オンプレミス サーバーの Windows 証明書ストア ([CERT_SYSTEM_STORE_LOCAL_MACHINE](https://learn.microsoft.com/ja-jp/windows/win32/seccrypto/system-store-locations#CERT_SYSTEM_STORE_LOCAL_MACHINE) などの場所) に格納されます。 この証明書は認証エージェントとアップデーター アプリケーションの両方で使用されます。

#### 認証エージェントの初期化

認証エージェントの開始時 (登録後の初回開始時またはサーバーの再起動時) に、パスワード検証要求の受け入れを開始できるように、Microsoft Entra サービスと安全に通信する方法が必要になります。

[Image: 認証エージェントの初期化を示す図。]

認証エージェントを初期化する方法を以下に示します。

1. 認証エージェントは、Microsoft Entra ID に送信ブートストラップ要求を行います。 この要求は、ポート 443 で、相互に認証された HTTPS チャネルを介して行われます。 この要求では、認証エージェントの登録中に発行された証明書と同じ証明書が使用されます。
2. Microsoft Entra ID は、テナント ID で識別される、テナントに固有の Service Bus キューにアクセス キーを提供することで、この要求に応答します。
3. 認証エージェントは、キューへの (ポート 443 を介した) 永続的な送信 HTTPS 接続を行います。

これで、認証エージェントがパスワード検証要求を取得して処理する準備ができました。

テナントに複数の認証エージェントが登録されている場合は、初期化の手順で、各エージェントが同じ Service Bus キューに接続されていることが確認されます。

### パススルー認証でのサインイン要求の処理方法

次の図は、パススルー認証でユーザー サインイン要求がどのように処理されるかを示しています。

[Image: パススルー認証でのユーザーのサインイン要求の処理方法を示す図。]

パススルー認証では、次のようにユーザーのサインイン要求が処理されます。

1. ユーザーが [Outlook Web アプリ](https://outlook.office365.com/owa)などのアプリケーションへのアクセスを試みます。
2. ユーザーがまだサインインしていない場合は、アプリケーションでブラウザーが Microsoft Entra のサインイン ページにリダイレクトされます。
3. Microsoft Entra STS サービスが**ユーザー サインイン** ページで応答します。
4. ユーザーが **[ユーザー サインイン]** ページにユーザー名を入力し、**[次へ]** ボタンを選択します。
5. ユーザーが **[ユーザー サインイン]** ページにパスワードを入力し、**[サインイン]** ボタンを選択します。
6. ユーザー名とパスワードが HTTPS POST 要求で Microsoft Entra STS に送信されます。
7. Microsoft Entra STS が、テナントで登録されたすべての認証エージェント用の公開キーを Azure SQL Database から取得し、このキーを使用してパスワードを暗号化します。 テナントで登録された認証エージェントごとに 1 つの暗号化されたパスワード値が生成されます。
8. Microsoft Entra STS が、ユーザー名と暗号化されたパスワードの値で構成されるパスワード検証要求をテナントに固有の Service Bus キューに配置します。
9. 初期化された認証エージェントは Service Bus キューに永続的に接続されるため、使用可能な認証エージェントのいずれかがパスワード検証要求を取得します。
10. 認証エージェントは識別子を使用して、公開キーに固有の暗号化されたパスワード値を検索します。 秘密キーを使用して公開キーの暗号化を解除します。
11. 認証エージェントが、[Win32 LogonUser API](https://learn.microsoft.com/ja-jp/windows/win32/api/winbase/nf-winbase-logonusera) (`dwLogonType` パラメーターは `LOGON32_LOGON_NETWORK`に設定) を使用して、Windows Server AD に対してユーザー名とパスワードを検証します。
    - この API は、フェデレーション サインイン シナリオでユーザーのサインイン時に Active Directory フェデレーション サービス (AD FS) によって使用されるものと同じ API です。
    - この API は、Windows Server の標準的な解決プロセスに従ってドメイン コントローラーを検索します。
12. 認証エージェントが Windows Server AD から結果 (成功、ユーザー名またはパスワードが正しくない、パスワードの期限が切れているなど) を受け取ります。

注

認証エージェントがサインイン プロセスの間に失敗した場合は、サインイン要求全体が破棄されます。 あるオンプレミス認証エージェントから別のオンプレミス認証エージェントにサインイン要求が渡されることはありません。 これらのエージェントはクラウドのみと通信し、相互には通信しません。

1. 認証エージェントは、ポート 443 を介して相互認証された送信 HTTPS チャネル経由で Microsoft Entra STS に結果を戻します。 相互認証では、登録時に認証エージェントに対して発行された証明書を使用します。
2. Microsoft Entra STS は、この結果がテナントの特定のサインイン要求と関連していることを確認します。
3. Microsoft Entra STS は、構成どおりにサインインの手順を続行します。 たとえば、パスワードの検証が成功した場合、ユーザーは MFA のためにチャレンジされるか、アプリケーションにリダイレクトされることがあります。

### 認証エージェントの運用上のセキュリティ

パススルー認証で運用上のセキュリティが維持されるように、Microsoft Entra ID は証明書エージェントの証明書を定期的に更新します。 Microsoft Entra ID によって更新がトリガーされます。 これらの更新は、認証エージェント自体が管理しているわけではありません。

[Image: パススルー認証での運用セキュリティのしくみを示す図。]

認証エージェントが Microsoft Entra ID との信頼関係を更新するには、以下を行います。

1. 認証エージェントは数時間ごとに Microsoft Entra を ping して、証明書を更新する時期であるかどうかを確認します。 証明書は有効期限が切れる 30 日前に更新されます。 この確認は、登録時に発行されたものと同じ証明書を使用して、相互認証された HTTPS チャネル経由で行われます。
2. サービスで更新の時期であることが示された場合、認証エージェントは公開キーと秘密キーの新しいキー ペアを生成します。
    - これらのキーは、標準の RSA 2,048 ビットの暗号化を使用して生成されます。
    - 秘密キーはオンプレミス サーバーの外部に移動されることはありません。
3. 次に、認証エージェントは、HTTPS 経由で Microsoft Entra ID に証明書更新要求を行います。 要求には、次のコンポーネントが含まれています。
    - Windows 証明書ストアの CERT\_SYSTEM\_STORE\_LOCAL\_MACHINE の場所から取得した既存の証明書。
    - 手順 2 で生成した公開キー。
    - CSR。 この要求により、Microsoft Entra ID を CA として使用して、新しいデジタル ID 証明書を申請します。
4. Microsoft Entra ID は、証明書更新要求の既存の証明書を検証します。 次に、要求がテナントに登録されている認証エージェントからのものであることを確認します。
5. 既存の証明書がまだ有効である場合、Microsoft Entra ID は新しいデジタル ID 証明書に署名し、新しい証明書を認証エージェントに戻します。
6. 既存の証明書の有効期限が切れている場合、Microsoft Entra ID は登録されている認証エージェントのテナントの一覧から認証エージェントを削除します。 その後、ハイブリッド ID 管理者は手動で新しい認証エージェントをインストールして登録する必要があります。
    - Microsoft Entra ID ルート CA を使用して、証明書に署名します。
    - 証明書の DN を、テナントを一意に識別する GUID であるテナント ID に設定します。 この DN により、証明書の範囲がテナントのみに限定されます。
7. Microsoft Entra ID では、それのみがアクセス可能な Azure SQL Database 内のデータベースに認証エージェントの公開キーが格納されます。 また、認証エージェントに関連付けられた古い公開キーを無効にします。
8. その後、新しい証明書 (手順 5 で発行されたもの) はサーバーの Windows 証明書ストア ([CERT_SYSTEM_STORE_CURRENT_USER](https://learn.microsoft.com/ja-jp/windows/win32/seccrypto/system-store-locations#CERT_SYSTEM_STORE_CURRENT_USER) などの場所) に格納されます。

信頼の更新手順は (ハイブリッド ID 管理者の関与なしに) 非対話形式で行われるため、認証エージェントは CERT\_SYSTEM\_STORE\_LOCAL\_MACHINE の場所の既存の証明書を更新するためのアクセス権を持たなくなります。

注

この手順で、CERT\_SYSTEM\_STORE\_LOCAL\_MACHINE の場所から証明書自体が削除されるわけではありません。 9. この時点から、新しい証明書が認証に使用されます。 以降の証明書更新のたびに、CERT\_SYSTEM\_STORE\_LOCAL\_MACHINE の場所にある証明書が置き換えられます。

### 認証エージェントの自動更新

新しいバージョンが (バグ修正またはパフォーマンスの強化が行われて) リリースされると、アップデーター アプリケーションによって認証エージェントが自動的に更新されます。 アップデーター アプリケーションでは、テナントに対するパスワード検証要求は処理されません。

Microsoft Entra ID は、新しいバージョンのソフトウェアを、署名済みの Windows インストーラー パッケージ (MSI) としてホストします。 MSI への署名は、ダイジェスト アルゴリズムに SHA-256 を指定した [Microsoft Authenticode](https://learn.microsoft.com/ja-jp/previous-versions/windows/internet-explorer/ie-developer/platform-apis/ms537359%28v=vs.85%29) を使用することによって行われます。

[Image: 認証エージェントの自動更新方法を示す図。]

認証エージェントの自動更新を行うには、以下を実行します。

1. アップデーター アプリケーションは 1 時間ごとに Microsoft Entra を ping して、使用可能な新しいバージョンの認証エージェントがあるかどうかを確認します。 この確認は、登録時に発行されたものと同じ証明書を使用して、相互認証された HTTPS チャネル経由で行われます。 認証エージェントとアップデーターは、サーバーに格納されている証明書を共有します。
2. 新しいバージョンが使用可能な場合、Microsoft Entra ID はアップデーターに署名済みの MSI を戻します。
3. アップデーターは、MSI が Microsoft によって署名されていることを確認します。
4. アップデーターは MSI を実行します。 このプロセスでは、Updater アプリケーションは次の処理を行います。

注

アップデーターは[ローカル システム](https://learn.microsoft.com/ja-jp/windows/win32/services/localsystem-account)権限で実行されます。

1. 認証エージェント サービスを停止します。
2. サーバーに新しいバージョンの認証エージェントをインストールします。
3. 認証エージェント サービスを再起動します。

注

テナントに複数の認証エージェントが登録されている場合、Microsoft Entra ID がそれらの証明書を同時に更新することはありません。 代わりに、Microsoft Entra ID では一度に 1 つずつ証明書が更新され、サインイン要求の高可用性が保証されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-pta-upgrade-preview-authentication-agents"} -->
## Microsoft Entra Connect - パススルー認証 - 認証エージェントのアップグレード - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-upgrade-preview-authentication-agents
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra パススルー認証の構成をアップグレードする方法について説明します。

### 概要

この記事は、プレビューで Microsoft Entra のパススルー認証を使っているお客様に向けたものです。 認証エージェント ソフトウェアは最近アップグレード (およびブランド名を変更) されました。 ユーザーは、オンプレミスのサーバーにインストールされているプレビューの認証エージェントを "*手作業で*" アップグレードする必要があります。 この手作業によるアップグレードは、1 回限りの操作です。 認証エージェントの将来の更新はすべて自動で行われます。 次のような理由でアップグレードを行う必要があります。

- Authentication Agents のプレビュー バージョンでは、これ以上のセキュリティ修正またはバグ修正は受け取りません。
- Authentication Agents のプレビュー バージョンは、高可用性のために追加のサーバーにインストールできません。

### 認証エージェントのバージョンを確認する

#### ステップ 1: 認証エージェントがインストールされている場所を確認する

以下の手順に従って、認証エージェントがインストールされている場所を確認します。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Entra Connect**&gt;**Connect sync** に移動します。
3. **[パススルー認証]** を選択します。 このブレードには、認証エージェントがインストールされているサーバーが一覧表示されます。

[Image: Microsoft Entra 管理センター - [パススルー認証] ブレード]

#### ステップ 2: 認証エージェントのバージョンを確認する

前のステップでわかった各サーバーで、認証エージェントのバージョンを確認するには、次の手順のようにします。

1. オンプレミスのサーバーで **[コントロール パネル] -&gt; [プログラム] -&gt; [プログラムと機能]** に移動します。
2. 「**Microsoft Entra Connect Authentication Agent**」 のエントリがある場合、そのサーバーでは何もする必要がありません。
3. "**Microsoft Entra プライベート ネットワーク コネクタ**" のエントリがある場合は、そのサーバーを手作業でアップグレードする必要があります。

[Image: 認証エージェントのプレビュー バージョン]

### アップグレードを開始する前に従うベスト プラクティス

アップグレードの前に、次のことを行っておく必要があります。

1. **クラウド専用のハイブリッド ID 管理者アカウントを作成する**: パススルー認証エージェントが正常に動作していない緊急の状況で使うクラウド専用のハイブリッド ID 管理者アカウントを用意せずにアップグレードを行わないでください。 [Microsoft Entra ID の緊急アクセス用アカウント](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)について説明します。 このステップは非常に重要で、これにより、テナントからロックアウトされないようにします。
2. **高可用性を確保する**: まだ行っていない場合、[こちらの説明](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start#step-4-ensure-high-availability)に従って、サインイン要求に高可用性を提供するための 2 番目のスタンドアロン認証エージェントをインストールします。

### Microsoft Entra Connect サーバーの認証エージェントをアップグレードする

Microsoft Entra Connect をアップグレードしてから、同じサーバー上の認証エージェントをアップグレードする必要があります。 プライマリとステージングの両方の Microsoft Entra Connect サーバーで、以下の手順を行ってください。

1. **Microsoft Entra Connect のアップグレード**: こちらの[記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version)に従って、最新の Microsoft Entra Connect バージョンにアップグレードしてください。
2. **プレビュー バージョンの認証エージェントをアンインストールする**: [この PowerShell スクリプト](https://aka.ms/rmpreviewagent)をダウンロードし、サーバーで管理者として実行します。
3. **最新バージョンの認証エージェント (バージョン 1.5.2482.0 以降) をダウンロードする**: 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。 **Entra ID**&gt;**Entra Connect**&gt;**Connect sync** に移動します。

**[パススルー認証] -&gt; [エージェントのダウンロード]** を選びます。 [サービスの条項](https://aka.ms/authagenteula)を受け入れ、最新バージョンの認証エージェントをダウンロードします。 認証エージェントは[ここ](https://aka.ms/getauthagent)からダウンロードすることもできます。 4. **最新バージョンの認証エージェントをインストールする**: ステップ 3 でダウンロードした実行可能ファイルを実行します。 テナントのハイブリッド ID 管理者の資格情報を求められたら、入力します。 5. **最新バージョンがインストールされたことを確認する**: 前述したように **[コントロール パネル] -&gt; [プログラム] -&gt; [プログラムと機能]** に移動し、「**Microsoft Entra Connect 認証 エージェント**」 のエントリがあることをご確認ください。

注

[ハイブリッド ID 管理者](https://entra.microsoft.com)以上の権限で [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)の [パススルー認証] ブレードを確認した場合。 上記の手順を完了すると、各サーバーに 2 つの認証エージェント エントリが表示されます。一方のエントリは認証エージェントが "**アクティブ**"、もう一方は "**非アクティブ**" と示されます。 これは "*予期されること*" です。 **非アクティブ**のエントリは、数日後に自動的に削除されます。

### 他のサーバーの認証エージェントをアップグレードする

以下の手順を行い、(Microsoft Entra Connect がインストールされていない) 他のサーバーで認証エージェントをアップグレードしてください:

1. **プレビュー バージョンの認証エージェントをアンインストールする**: [この PowerShell スクリプト](https://aka.ms/rmpreviewagent)をダウンロードし、サーバーで管理者として実行します。
2. **最新バージョン (1.5.2482.0 以降) の認証エージェントをダウンロードする**: テナントのハイブリッド ID 管理者資格情報を使い、少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com) として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) にサインインします。 **[Microsoft Entra ID ] -&gt; [Microsoft Entra Connect] -&gt; [パススルー認証] -&gt; [エージェントのダウンロード]** の順に選択してください。 サービスの条項に同意し、最新バージョンをダウンロードします。
3. **最新バージョンの認証エージェントをインストールする**: ステップ 2 でダウンロードした実行可能ファイルを実行します。 テナントのハイブリッド ID 管理者の資格情報を求められたら、入力します。
4. **最新バージョンがインストールされたことを確認する**: 前述したように **[コントロール パネル] -&gt; [プログラム] -&gt; [プログラムと機能]** に移動し、**[Microsoft Entra Connect 認証 エージェント]**というエントリがあることをご確認ください。

注

上記の手順を完了した後に、少なくとも[ハイブリッド管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)の [パススルー認証] ブレードを確認すると、各サーバーに 2 つの認証エージェント エントリが表示されます。一方のエントリは認証エージェントが 「**アクティブ**」、もう一方は 「**非アクティブ**」 と示されます。 これは "*予期されること*" です。 **非アクティブ**のエントリは、数日後に自動的に削除されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-pta-user-privacy"} -->
## ユーザー プライバシーと Microsoft Entra パススルー認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-user-privacy
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra パススルー認証と GDPR コンプライアンスについて説明しています。

注

この記事は、デバイスまたはサービスから個人データを削除する手順について説明しており、GDPR の下で義務を果たすために使用できます。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

### 概要

Microsoft Entra パススルー認証では、個人データを含めることができる次の種類のログが作成されます。

- Microsoft Entra Connect トレース ログ ファイル。
- 認証エージェント トレース ログ ファイル。
- Windows イベント ログ ファイル。

次の 2 つの方法でパススルー認証のユーザー プライバシーを強化します:

1. 要請を受けた際、個人のデータを抽出し、その個人のデータを環境から削除する
2. 48時間以上データを保存しないようにしてください。

実装や保守がより簡単なので、2 番目の方法を強くお勧めします。 以下に、ログの種類ごとの手順を示します。

#### Microsoft Entra Connect トレース ログ ファイルを削除する

Microsoft Entra Connect のインストールまたはアップグレード、あるいはパススルー認証の構成の変更の 48 時間以内に、**%ProgramData%\AADConnect** フォルダーの内容を確認し、このフォルダーのトレース ログ コンテンツ (**trace-\*.log** ファイル) を削除します。このアクションによって GDPR の対象となるデータが作成される可能性があるためです。

Von Bedeutung

このフォルダー内にある **PersistedState.xml** ファイルは削除しないでください。このファイルは Microsoft Entra Connect の以前のインストールの状態を保持するために使用され、さらには、アップグレードのインストールが完了された場合にも使用されるためです。 このファイルが個人に関するデータを含むことはないため、絶対に削除しないでください。

これらのトレース ログ ファイルの確認と削除には Windows エクスプ ローラーを使用することもできますし、次のような PowerShell スクリプトを使用して、必要なアクションを実行することもできます。

```
$Files = ((Get-Item -Path "$env:programdata\aadconnect\trace-*.log").VersionInfo).FileName 
 
Foreach ($file in $Files) { 
    {Remove-Item -Path $File -Force} 
}
```

拡張子が ".PS1" のファイルにスクリプトを保存します。 必要に応じて、このスクリプトを実行してください。

関連する Microsoft Entra Connect の GDPR 要件の詳細については、[こちらの記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-user-privacy)をご覧ください。

#### 認証エージェントのイベント ログを削除する

この製品では、**Windows イベント ログ**が作成されることもあります。 詳細については、[こちらの記事](https://learn.microsoft.com/ja-jp/windows/win32/wes/windows-event-log)をご覧ください。

パススルー認証エージェントに関するログを表示するには、サーバーで**イベント ビューアー** アプリケーションを開き、**アプリケーションとサービス ログ\Microsoft\AzureAdConnect\AuthenticationAgent\Admin** の下を調べます。

#### 認証エージェント トレース ログ ファイルを削除する

**%ProgramData%\Microsoft\Azure AD Connect Authentication Agent\Trace** の内容を定期的に確認して、48 時間ごとにこのフォルダーの内容を削除する必要があります。

Von Bedeutung

認証エージェント サービスが実行中の場合は、フォルダー内の現在のログ ファイルを削除できません。 サービスを停止してから、再試行してください。 ユーザーのサインイン エラーを回避するには、[高可用性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start#step-4-ensure-high-availability)に対応するパススルー認証を既に構成している必要があります。

これらのファイルの確認と削除には Windows エクスプ ローラーを使用することもできますし、次のようなスクリプトを使用して、必要なアクションを実行することもできます。

```powershell
$Files = ((Get-ChildItem -Path "$env:programdata\microsoft\azure ad connect authentication agent\trace" -Recurse).VersionInfo).FileName 
 
Foreach ($file in $files) { 
    {Remove-Item -Path $File -Force} 
}
```

このスクリプトを 48 時間ごとに実行するようにスケジュールするには、次の手順に従います。

1. 拡張子が ".PS1" のファイルにスクリプトを保存します。
2. **コントロール パネル**を開き、**[システムとセキュリティ]** をクリックします。
3. **[管理ツール]** で、**[タスクのスケジュール]** をクリックします。
4. **タスク スケジューラ**で、**[Task Schedule Library](タスク スケジュール ライブラリ)** を右クリックし、**[基本タスクの作成...]** をクリックします
5. 新しいタスクの名前を入力し、**[次へ]** をクリックします。
6. **タスク トリガー**として **[毎日]** を選択し、**[次へ]** をクリックします。
7. 繰り返しを [2 日] に設定し、**[次へ]** をクリックします。
8. アクションとして **[プログラムを起動する]** を選択し、**[次へ]** をクリックします。
9. プログラム/スクリプトのボックスに「**PowerShell**」と入力し、**[引数の追加 (オプション)]** というラベルの付いたボックスに、先ほど作成したスクリプトへの完全なパスを入力して、**[次へ]** をクリックします。
10. 次の画面に、作成しようとしているタスクの概要が表示されます。 値を確認し、**[完了]** をクリックしてタスクを作成します。

#### ドメイン コントローラー ログに関する注意事項

監査ログが有効になっている場合、この製品では、お使いのドメイン コント ローラーのセキュリティ ログを生成できます。 監査ポリシーの構成に関する詳細については、[こちらの記事](https://learn.microsoft.com/ja-jp/previous-versions/tn-archive/dd277403%28v=technet.10%29)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-selective-password-hash-synchronization"} -->
## Microsoft Entra Connect の選択的なパスワード ハッシュ同期 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-selective-password-hash-synchronization
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra Connect で使用する選択的パスワード ハッシュ同期を設定および構成する方法について説明します。

[パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)は、ハイブリッド ID を実現するために使用されるサインイン方法の 1 つです。 Microsoft Entra Connect では、オンプレミスの Active Directory インスタンスからクラウドベースの Microsoft Entra インスタンスに、ユーザーのパスワードのハッシュを同期します。 既定では、設定が完了すると、同期しているすべてのユーザーに対してパスワード ハッシュ同期が行われます。

パスワード ハッシュを Microsoft Entra ID に同期しないようにユーザーのサブセットを除外する場合は、この記事のガイド付き手順を使用して、選択的なパスワード ハッシュ同期を構成できます。

重要

公式に文書化されている構成やアクションを除き、Microsoft では Microsoft Entra Connect 同期の変更や操作はサポートされていません。 これらの構成またはアクションは、Microsoft Entra Connect Sync の一貫性のない状態またはサポートされていない状態になる可能性があります。その結果、Microsoft はこのような展開に効率的なテクニカル サポートを提供する機能を保証できません。

### 実装について検討する

構成管理作業を軽減するために、まずパスワード ハッシュ同期から除外するユーザー オブジェクトの数を考慮する必要があります。 相互に排他的な次のシナリオが要件に合っていることを確認し、適切な構成オプションを選択します。

- **除外**するユーザーの数が、**含める**ユーザー数より**小さい**場合は、こちらのセクションの手順に従います。
- **除外**するユーザーの数が、**含める**ユーザー数より**大きい**場合は、こちらのセクションの手順に従います。

重要

いずれかの構成オプションを選択すると、変更を適用するために必要な初期同期 (完全同期) が、次の同期サイクルで自動的に実行されます。

重要

選択的なパスワード ハッシュ同期を構成すると、パスワード ライトバックに直接影響します。 Microsoft Entra ID で開始されたパスワードの変更またはパスワードのリセットは、ユーザーがパスワード ハッシュ同期の対象になっている場合にのみ、オンプレミスの Active Directory に書き戻されます。

重要

選択的なパスワード ハッシュ同期は、Microsoft Entra Connect 1.6.2.4 以降でサポートされています。 それより前のバージョンを使用している場合は、最新バージョンにアップグレードします。

#### AdminDescription 属性

どちらのシナリオも、ユーザーの adminDescription 属性を特定の値に設定することに依存しています。 これにより、規則が適用され、選択的な PHS が機能するようになります。

| シナリオ | adminDescription 値 |
| --- | --- |
| 除外するユーザーが含めるユーザーよりも少ない | PHSFiltered |
| 除外するユーザーが含めるユーザーよりも多い | PHSIncluded |

この属性は、以下のいずれかの方法で、手動で設定できます。

- Active Directory ユーザーとコンピューター UI を使用
- `Set-ADUser` PowerShell コマンドレットの使用 詳細については、「[Set-ADUser](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-aduser)」を参照してください。

#### 同期スケジューラを無効にする

どちらのシナリオを開始する場合も、同期規則を変更している間、同期スケジューラを無効にしておく必要があります。

1. Windows PowerShell を起動し、入力します。

    `Set-ADSyncScheduler -SyncCycleEnabled $false`
2. 次のコマンドレットを実行して、スケジューラが無効になっていることを確認します。

    `Get-ADSyncScheduler`

スケジューラの詳細については、Microsoft Entra Connect 同期スケジューラを参照してください。

### 除外するユーザーが含めるユーザーよりも少ない

次のセクションでは、**除外**するユーザーの数が、**含める**ユーザー数より**小さい**場合に、選択的なパスワード ハッシュ同期を有効にする方法について説明します。

重要

先に進む前に、前述のように同期スケジューラが無効になっていることを確認します。

- **In from AD – User AccountEnabled** の編集可能なコピーを作成し、**パスワード ハッシュ同期を未選択にできるようにする**オプションを選択します。そして、スコープ フィルターを定義します。
- 既定の **In from AD – User AccountEnabled** の編集可能なコピーをもう 1 つ作成し、**パスワード ハッシュ同期を選択できるようにする**オプションを選択します。そして、スコープ フィルターを定義します。
- 同期スケジューラを再度有効にする
- パスワード ハッシュ同期で許可するユーザーに対してスコープ属性として定義されていた Active Directory の属性値を設定します。

重要

選択的なパスワード ハッシュ同期を構成するために指定された手順は、Active Directory で属性 **adminDescription** に値 **PHSFiltered**が設定されているユーザー オブジェクトにのみ影響します。 この属性が設定されていない場合、または値が PHSFiltered以外の値である場合、これらのルールはユーザー オブジェクトに適用されません。

#### 必要な同期規則を構成します。

1. 同期規則エディターを起動し、フィルター **[パスワード同期]** を **[オン]** にし、 **[規則の種類]** を **[標準]** に設定します。 [Image: 同期規則エディターを起動する]
2. 選択的なパスワード ハッシュ同期を構成する Active Directory フォレスト コネクタで、規則 **[In from AD - User AccountEnabled]** を選択し、**[編集]** を選択します。 次のダイアログ ボックスで **[はい]** を選択して、元の規則の編集可能なコピーを作成します。 [Image: 規則の選択]
3. 最初のルールでは、パスワード ハッシュ同期が無効になります。新しいカスタム規則に、「**In from AD - User AccountEnabled - Filter Users from PHS**」という名前を指定します。 優先順位の値を 100 未満の数値に変更します (たとえば、**90** または使用している環境で使用可能な最小値)。 **[パスワード同期を有効にする]** チェックボックスと **[無効]** チェックボックスがオフになっていることを確認します。 次に、を選択します。 [Image: 受信を編集する]
4. **スコープ フィルター**で、**[句の追加]**を選択します。 [属性] 列で **[adminDescription]** 、[演算子] 列で **[EQUAL]** を選択し、値として「**PHSFiltered**」と入力します。 [Image: スコープ フィルター]
5. 他の変更は必要ありません。 **[保存]** を選択できるように、**[結合規則]** と **[変換]** は既定のコピーされた設定のままにする必要があります。 警告ダイアログ ボックス **[OK]** を選択して、コネクタの次の同期サイクルで完全同期を実行するように通知します。 [Image: 規則の保存]
6. 次に、パスワード ハッシュ同期を有効にする別のカスタム規則を作成します。 選択的なパスワード ハッシュ同期を構成する Active Directory フォレスト コネクタで、既定の規則 **[In from AD - User AccountEnabled]** をもう一度選択し、**[編集]** を選択します。 次のダイアログ ボックスで **[はい]** を選択して、元の規則の編集可能なコピーを作成します。 [Image: カスタム規則]
7. 新しいカスタム規則に、「**In from AD - User AccountEnabled - Users included for PHS**」という名前を指定します。 優先順位の値を、前に作成した規則より小さい数値に変更します (この例では、**89**)。 **[パスワード同期を有効にする]** チェックボックスがオンで、 **[無効]** チェックボックスがオフになっていることを確認します。 次に、を選択します。[Image: 新しい規則の編集]
8. **スコープ フィルター**で、**[句の追加]**を選択します。 [属性] 列で **[adminDescription]** 、[演算子] 列で **[NOTEQUAL]** を選択し、値として「**PHSFiltered**」と入力します。 [Image: スコープ規則]
9. 他の変更は必要ありません。 **[保存]** を選択できるように、**[結合規則]** と **[変換]** は既定のコピーされた設定のままにする必要があります。 警告ダイアログ ボックス **[OK]** を選択して、コネクタの次の同期サイクルで完全同期を実行するように通知します。 [Image: 結合規則]
10. 規則の作成を確認します。 **[パスワード同期]** が**オン**で、**[規則の種類]** が **[標準]** のフィルターを削除します。 先ほど作成した新しい規則が両方とも表示されます。 [Image: 規則の確認]

#### 同期スケジューラを再度有効にする:

必要な同期規則を構成する手順を完了したら、次の手順で同期スケジューラを再度有効にします。

1. Windows PowerShell で次を実行します。

    `set-adsyncscheduler -synccycleenabled:$true`
2. 次を実行して、正常に有効にされたことを確認します。

    `get-adsyncscheduler`

スケジューラの詳細については、Microsoft Entra Connect 同期スケジューラを参照してください。

#### ユーザーの **adminDescription** 属性の編集:

すべての構成が完了したら、Active Directory でパスワード ハッシュ同期から**除外**するすべてのユーザーの属性 **adminDescription** を編集し、スコープ フィルターで使用される文字列「**PHSFiltered**」を追加する必要があります。

[Image: 属性を編集する]

次の PowerShell コマンドを使用して、ユーザーの **adminDescription** 属性を編集することもできます。

`set-adusermyuser-replace@{adminDescription="PHSFiltered"}`

### 除外するユーザーが含めるユーザーよりも多い

次のセクションでは、**除外**するユーザーの数が、**含める**ユーザー数より**大きい**場合に、選択的なパスワード ハッシュ同期を有効にする方法について説明します。

重要

既に説明したように、続行する前に、同期スケジューラが無効になっていることを確認してください。

実行するアクションの概要を次に示します。

- **In from AD – User AccountEnabled** の編集可能なコピーを作成し、**パスワード ハッシュ同期を未選択にできるようにする**オプションを選択します。そして、スコープ フィルターを定義します。
- 既定の **In from AD – User AccountEnabled** の編集可能なコピーをもう 1 つ作成し、**パスワード ハッシュ同期を選択できるようにする**オプションを選択します。そして、スコープ フィルターを定義します。
- 同期スケジューラを再度有効にする
- パスワード ハッシュ同期で許可するユーザーに対してスコープ属性として定義されていた Active Directory の属性値を設定します。

重要

選択的なパスワード ハッシュ同期を構成するために指定した手順は、active Directory に設定 **adminDescription** 属性を持つユーザー オブジェクト **PHSIncluded**の値を持つユーザー オブジェクトにのみ影響します。 この属性が設定されていない場合、または値が PHSIncluded以外の値である場合、これらのルールはユーザー オブジェクトに適用されません。

#### 必要な同期規則を構成します。

1. 同期規則エディターを起動し、フィルター **[パスワード同期]** を **[オン]** にし、**[規則の種類]** を **[標準]** に設定します。 [Image: 規則の種類]
2. 選択的なパスワード ハッシュ同期を構成する Active Directory フォレストで、規則 **[In from AD – User AccountEnabled]** を選んで、**[編集]** を選びます。 次のダイアログ ボックスで **[はい]** を選択して、元の規則の編集可能なコピーを作成します。 [Image: AD からの入力]
3. 最初のルールでは、パスワード ハッシュ同期が無効になります。新しいカスタム規則に、「**In from AD - User AccountEnabled - Filter Users from PHS**」という名前を指定します。 優先順位の値を 100 未満の数値に変更します (たとえば、**90** または使用している環境で使用可能な最小値)。 **[パスワード同期を有効にする]** チェックボックスと **[無効]** チェックボックスがオフになっていることを確認します。 次に、を選択します。 [Image: 優先順位の設定]
4. **スコープ フィルター**で、**[句の追加]**を選択します。 [属性] 列で **[adminDescription]** 、[演算子] 列で **[NOTEQUAL]** を選択し、値として「**PHSIncluded**」と入力します。 [Image: 句の追加]
5. 他の変更は必要ありません。 **[保存]** を選択できるように、**[結合規則]** と **[変換]** は既定のコピーされた設定のままにする必要があります。 警告ダイアログ ボックス **[OK]** を選択して、コネクタの次の同期サイクルで完全同期を実行するように通知します。 [Image: 変換]
6. 次に、パスワード ハッシュ同期を有効にする別のカスタム規則を作成します。 選択的なパスワード ハッシュ同期を構成する Active Directory フォレスト コネクタで、既定の規則 **[In from AD - User AccountEnabled]** をもう一度選択し、**[編集]** を選択します。 次のダイアログ ボックスで **[はい]** を選択して、元の規則の編集可能なコピーを作成します。 [Image: ユーザーアカウントが有効になっています]
7. 新しいカスタム規則に、「**In from AD - User AccountEnabled - Users included for PHS**」という名前を指定します。 優先順位の値を、前に作成した規則より小さい数値に変更します (この例では、**89**)。 **[パスワード同期を有効にする]** チェックボックスがオンで、 **[無効]** チェックボックスがオフになっていることを確認します。 次に、を選択します。 [Image: パスワード同期を有効にする]
8. **スコープ フィルター**で、**[句の追加]**を選択します。 [属性] 列で **[adminDescription]** 、[演算子] 列で **[EQUAL]** を選択し、値として「**PHSIncluded**」と入力します。 [Image: PHSIncluded]
9. 他の変更は必要ありません。 **[保存]** を選択できるように、**[結合規則]** と **[変換]** は既定のコピーされた設定のままにする必要があります。 警告ダイアログ ボックス **[OK]** を選択して、コネクタの次の同期サイクルで完全同期を実行するように通知します。 [Image: 今すぐ保存]
10. 規則の作成を確認します。 **[パスワード同期]** が**オン**で、**[規則の種類]** が **[標準]** のフィルターを削除します。 先ほど作成した新しい規則が両方とも表示されます。 [Image: 同期オン]

#### 同期スケジューラを再度有効にする:

必要な同期規則を構成する手順を完了したら、次の手順で同期スケジューラを再度有効にします。

1. Windows PowerShell で次を実行します。

    `set-adsyncscheduler-synccycleenabled$true`
2. 次を実行して、正常に有効にされたことを確認します。

    `get-adsyncscheduler`

スケジューラの詳細については、Microsoft Entra Connect 同期スケジューラを参照してください。

#### ユーザーの **adminDescription** 属性の編集:

すべての構成が完了したら、Active Directory でパスワード ハッシュ同期に**含める**すべてのユーザーの属性 **adminDescription** を編集し、スコープ フィルターで使用される文字列「**PHSIncluded**」を追加する必要があります。

[Image: 属性の編集]

次の PowerShell コマンドを使用して、ユーザーの **adminDescription** 属性を編集することもできます。

`Set-ADUser myuser -Replace @{adminDescription="PHSIncluded"}`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-single-object-sync"} -->
## Microsoft Entra Connect の単一オブジェクト同期 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-single-object-sync
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: トラブルシューティングのために、1 つのオブジェクトを Active Directory から Microsoft Entra ID に同期する方法について説明します。

Microsoft Entra Connect 単一オブジェクト同期ツールは、Active Directory から Microsoft Entra ID に個々のオブジェクトを同期するために使用できる PowerShell コマンドレットです。 生成されたレポートを使用して、オブジェクトごとの同期の問題の調査とトラブルシューティングを行うことができます。

手記

このツールでは、Active Directory から Microsoft Entra ID への同期がサポートされています。 Microsoft Entra ID から Active Directory への同期はサポートされていません。

このツールでは、オブジェクト変更の追加と更新の同期がサポートされています。 オブジェクト変更削除の同期はサポートされていません。

### しくみ

単一オブジェクト同期ツールでは、インポートするソース コネクタとパーティションを検索するために、入力として Active Directory の識別名が必要です。 変更が Microsoft Entra ID にエクスポートされます。 このツールは、**provisioningObjectSummary** リソースの種類と同様の JSON 出力を生成します。

単一オブジェクト同期ツールは、次の手順を実行します。

1. 同期スコープ内のオブジェクトの (ソース) ドメイン (Active Directory コネクタとパーティション) かどうかを判断します。
2. 同期スコープ内のオブジェクトの (ターゲット) ドメイン (Microsoft Entra コネクタとパーティション) かどうかを判断します。
3. 同期スコープ内のオブジェクトの組織単位かどうかを判断します。
4. コネクタ アカウントの資格情報を使用してオブジェクトにアクセスできるかどうかを判断します。
5. 同期スコープにオブジェクトの種類があるかどうかを判断します。
6. グループ フィルター処理が有効になっている場合、オブジェクトが同期スコープ内にあるかどうかを確認します。
7. Active Directory から Active Directory コネクタ スペースにオブジェクトをインポートします。
8. Microsoft Entra ID から Microsoft Entra コネクタ スペースにオブジェクトをインポートします。
9. Active Directory コネクタ スペースからオブジェクトを同期します。
10. Microsoft Entra コネクタ スペースから Microsoft Entra ID にオブジェクトをエクスポートします。

このツールでは、JSON 出力に加えて、同期操作のすべての詳細を含む HTML レポートが生成されます。 HTML レポートは、**C:\ProgramData\AADConnect\ADSyncObjectDiagnostics\ ADSyncSingleObjectSyncResult-&lt;日付&gt;.htm**にあります。 この HTML レポートは、サポート チームと共有して、必要に応じてさらにトラブルシューティングを行うことができます。

HTML レポートには次のものが含まれます。

| タブ | 説明 |
| --- | --- |
| ステップス | オブジェクトを同期するために実行する手順の概要を示します。 各手順には、トラブルシューティングの詳細が含まれています。 インポート、同期、およびエクスポートの手順には、名前などの追加の属性情報が含まれています。複数値、型、値、値の追加、値の削除、操作、同期規則、マッピングの種類、データ ソースです。 |
| トラブルシューティングと推奨事項 | エラー コードと理由を提供します。 エラー情報は、エラーが発生した場合にのみ使用できます。 |
| 変更されたプロパティ | 古い値と新しい値が表示されます。 古い値がない場合、または新しい値が削除された場合、そのセルは空白になります。 複数値の属性の場合、カウントが表示されます。 属性名は、[ステップ] タブへのリンクです。Microsoft Entra Connector Space から Microsoft Entra ID にオブジェクトをエクスポートします。属性情報には、複数値、型、値、値の追加、値の削除、操作、同期規則、マッピングの種類、データ ソースなどの属性の詳細が含まれます。 |
| 概要 | 発生した内容の概要と、ソース システムとターゲット システム内のオブジェクトの識別子について説明します。 |

### 前提 条件

単一オブジェクト同期ツールを使用するには、次のコマンドを使用する必要があります。

- Microsoft Entra Connect 以降の 2021 年 3 月リリース ([1.6.4.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#1640))。
- [PowerShell 5.0](https://learn.microsoft.com/ja-jp/powershell/scripting/windows-powershell/whats-new/what-s-new-in-windows-powershell-50)

#### 単一オブジェクト同期ツールを実行する

単一オブジェクト同期ツールを実行するには、次の手順に従います。

1. [管理者として実行] オプションを使用して、Microsoft Entra Connect サーバーで新しい Windows PowerShell セッションを開きます。
2. [実行ポリシーの](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.security/set-executionpolicy) を RemoteSigned または Unrestricted に設定します。
3. 同期操作が実行されていないことを確認した後、同期スケジューラを無効にします。

    `Set-ADSyncScheduler -SyncCycleEnabled $false`
4. AdSync 診断モジュールをインポートする

    `Import-module -Name "C:\Program Files\Microsoft Azure AD Sync\Bin\ADSyncDiagnostics\ADSyncDiagnostics.psm1"`
5. 単一オブジェクト同期コマンドレットを呼び出します。

    `Invoke-ADSyncSingleObjectSync -DistinguishedName "CN=testobject,OU=corp,DC=contoso,DC=com" | Out-File -FilePath ".\output.json"`
6. 同期スケジューラを再度有効にします。

    `Set-ADSyncScheduler -SyncCycleEnabled $true`

| 単一オブジェクト同期入力パラメーター | 説明 |
| --- | --- |
| DistinguishedName | これは必須の文字列パラメーターです。 同期とトラブルシューティングが必要な Active Directory オブジェクトの識別名です。 |
| StagingMode | これは省略可能なスイッチ パラメーターです。 このパラメーターは、Microsoft Entra ID への変更のエクスポートを防ぐために使用できます。**注**: コマンドレットは同期操作をコミットします。 **注**: Microsoft Entra Connect ステージング サーバーは、変更を Microsoft Entra ID にエクスポートしません。 |
| NoHtmlReport（HTMLレポートなし） | これは省略可能なスイッチ パラメーターです。 このパラメーターは、HTML レポートの生成を防ぐために使用できます。 |

### 単一オブジェクト同期のスロットリング

単一オブジェクト同期ツール **は** オブジェクト同期に関する問題の調査とトラブルシューティングを目的としています。 スケジューラによって実行される同期サイクルを置き換えることは想定されて**いません**。 Microsoft Entra ID からのインポートと Microsoft Entra ID へのエクスポートには、調整制限が適用されます。 調整制限に達した場合は、5 分後に再試行してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sso"} -->
## Microsoft Entra Connect: シームレス シングル サインオン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、Microsoft Entra シームレス シングル サインオンと、この機能によって企業ネットワーク内の企業のデスクトップ ユーザーに真のシングル サインオンを提供する方法について説明します。

### Microsoft Entra シームレス シングル サインオンとは?

Microsoft Entra シームレス シングル サインオン (Microsoft Entra シームレス SSO) では、ユーザーが企業ネットワークに接続される会社のデバイスを使用するときに、自動的にサインインを行います。 この機能を有効にすると、ユーザーは Microsoft Entra ID にサインインするためにパスワードを入力する必要がなくなり、通常はユーザー名の入力も不要です。 この機能により、追加のオンプレミス コンポーネントを必要とせずに、ユーザーはクラウド ベースのアプリケーションに簡単にアクセスできるようになります。

シームレス SSO は、サインインの方法として、 [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)または[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)のどちらとも組み合わせることができます。 シームレス SSO は、Active Directory フェデレーション サービス (ADFS) には適用でき *ません*。

[Image: シームレスなシングル サインオン]

### プライマリ更新トークンを介した SSO とシームレス SSO

Windows 10、Windows Server 2016、およびそれ以降のバージョンでは、プライマリ更新トークン (PRT) を介して SSO を使用することをお勧めします。 Windows 7 と Windows 8.1 の場合、シームレス SSO を使用することをお勧めします。 シームレス SSO では、ユーザーのデバイスがドメインに参加している必要がありますが、Windows 10 の [Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)や [Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)では使用されません。 [プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) に基づいて、Microsoft Entra 参加済み、Microsoft Entra ハイブリッド参加済み、Microsoft Entra 登録済みデバイスの SSO が機能します

Microsoft Entra ハイブリッド参加済み、Microsoft Entra 参加済み、または個人登録済みのデバイスに対して PRT を介した SSO が機能するのは、[職場または学校アカウントを追加] を使用してデバイスが Azure AD に登録された後になります。 PRT を使用した Windows 10 での SSO のしくみについて詳しくは、「[プライマリ更新トークン (PRT) と Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)」を参照してください

### 主な利点

- *優れたユーザー エクスペリエンス*
    - ユーザーはオンプレミスとクラウドベースの両方のアプリケーションに自動的にサインインします。
    - ユーザーはパスワードを何度も入力する必要がありません。
- *デプロイと管理が容易*
    - オンプレミスでは、この機能の動作のために追加のコンポーネントは不要です。
    - [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)または[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)の、どちらのクラウド認証方法でも機能します。
    - グループ ポリシーを使用して一部またはすべてのユーザーに展開できます。
    - AD FS インフラストラクチャを使用することなく、Windows 10 以外のデバイスを Microsoft Entra ID に登録できます。 この機能では、バージョン 2.1 以降の [workplace-join クライアント](https://www.microsoft.com/download/details.aspx?id=53554)を使用する必要があります。

### 機能概要

- サインイン ユーザー名には、オンプレミスの既定のユーザー名 (`userPrincipalName`) または Microsoft Entra Connect (`Alternate ID`) で構成された別の属性を指定できます。 シームレス SSO は Kerberos チケットの `securityIdentifier` 要求を使用して Microsoft Entra ID で対応するユーザー オブジェクトを検索するので、どちらを使用しても問題ありません。
- シームレス SSO は便宜的な機能です。 これが何らかの理由で失敗した場合、ユーザーのサインイン エクスペリエンスは通常の動作に戻ります。つまり、ユーザーはサインイン ページでパスワードを入力する必要があります。
- アプリケーション (たとえば、`https://myapps.microsoft.com/contoso.com`) が Microsoft Entra サインイン要求で `domain_hint` (OpenID Connect) パラメーターや `whr` (SAML) パラメーター (テナントを識別する)、または `login_hint` ユーザーを識別するパラメーター を転送する場合、ユーザーはユーザー名やパスワードを入力することなく自動的にサインインします。
- アプリケーション (たとえば、`https://contoso.sharepoint.com`) がサインイン要求を、Microsoft Entra ID のエンドポイント (つまり、`https://login.microsoftonline.com/contoso.com/<..>`) ではなく、Microsoft Entra ID のテナントとして設定されているエンドポイント (つまり、`https://login.microsoftonline.com/<tenant_ID>/<..>` または `https://login.microsoftonline.com/common/<...>`) に送信する場合、ユーザーにはサイレント サインオン エクスペリエンスも提供されます。
- サインアウトがサポートされています。 そのため、ユーザーは、シームレス SSO を使用して自動的にサインインするのではなく、サインインに別の Microsoft Entra アカウントを使用することを選択できます。
- Microsoft 365 Win32 クライアント (Outlook、Word、Excel など) のバージョン 16.0.8730.xxxx 以降は、非インタラクティブ フローを使用することでサポートされます。 OneDrive の場合は、サイレント サインオン エクスペリエンスのために、[OneDrive サイレント構成機能](https://techcommunity.microsoft.com/t5/Microsoft-OneDrive-Blog/Previews-for-Silent-Sync-Account-Configuration-and-Bandwidth/ba-p/120894) をアクティブにする必要があります。
- この機能は Microsoft Entra コネクトで有効にできます。
- 無料の機能であるため、Microsoft Entra ID の有料版は必要ありません。
- この機能は、Web ブラウザー ベースのクライアントと、Kerberos 認証に対応したプラットフォームおよびブラウザーで[最新の認証](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/modern-auth-for-office-2013-and-2016)をサポートしている Office クライアントでサポートされています。

| OS\ブラウザー | Internet Explorer | Microsoft Edge\*\*\*\* | グーグルクローム | Mozilla Firefox | Safari |
| --- | --- | --- | --- | --- | --- |
| Windows 10/11 | はい\* | はい | はい | あり\*\*\* | 該当なし |
| Windows 8.1 | はい\* | はい\*\*\*\* | はい | あり\*\*\* | 該当なし |
| Windows 8 | はい\* | 該当なし | はい | あり\*\*\* | 該当なし |
| Windows Server 2012 R2 以降 | はい\*\* | はい\*\*\*\* | はい | あり\*\*\* | 該当なし |
| Mac OS X | 該当なし | 該当なし | あり\*\*\* | あり\*\*\* | あり\*\*\* |

注

Microsoft Edge レガシはサポートされなくなりました

\*Internet Explorer バージョン 11 以降が必要です。 ([2021 年 8 月 17 日以降、Microsoft 365 のアプリとサービスは Internet Explorer 11 をサポートしなくなります](https://techcommunity.microsoft.com/t5/microsoft-365-blog/microsoft-365-apps-say-farewell-to-internet-explorer-11-and/ba-p/1591666)。)

\*\*Internet Explorer バージョン 11 以降が必要です。 拡張保護モードを無効にします。

\*\*\*[別途構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start#browser-considerations)が必要です。

\*\*\*\*Chromium に基づく Microsoft Edge
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sso-faq"} -->
## Microsoft Entra Connect - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-faq
- Service: entra-id / hybrid-connect
- Article date: 2026-09-15
- Summary: Microsoft Entra シームレス シングル サインオンに関してよく寄せられる質問への回答を示します。

この記事では、Microsoft Entra シームレス シングル サインオン (シームレス SSO) に関してよく寄せられる質問に回答します。 最新のコンテンツを常にチェックしてください。

### シームレス SSO で使用できるサインイン方法

シームレス SSO は、サインインの方法として、 [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)または[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)のどちらとも組み合わせることができます。 ただし、この機能は、Active Directory フェデレーション サービス (AD FS) で使用できません。

### シームレス SSO は無料の機能ですか。

シームレス SSO は無料の機能です。この機能を使用するために Microsoft Entra ID の有料エディションは不要です。

### シームレス SSO は Microsoft Azure Germany クラウドおよび Microsoft Azure Government クラウドで使用できますか。

シームレス SSO は [Azure Government クラウド](https://www.microsoft.com/de-de/microsoft-cloud) で使用できます。 詳細については、「[Azure Government のハイブリッド ID に関する考慮事項](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-government-cloud)」を参照してください。

### どのアプリケーションがシームレス SSO の "domain\_hint" または "login\_hint" パラメーター機能を利用しますか。

次の表に、これらのパラメーターを Microsoft Entra ID に送信できるアプリケーションの一覧を示します。 このアクションにより、シームレス SSO を使用したサイレント サインオン エクスペリエンスがユーザーに提供されます。

| アプリケーション名 | アプリケーションの URL |
| --- | --- |
| アクセスパネル | https://myapps.microsoft.com/contoso.com |
| Web 上の Outlook | https://outlook.office365.com/contoso.com |
| Office 365 ポータル | https://portal.office.com?domain\_hint=contoso.com、https://www.office.com?domain\_hint=contoso.com |

また、アプリケーションからのサインイン要求が、共通の Microsoft Entra エンドポイント (https://login.microsoftonline.com/contoso.com/&lt;...&gt;) 宛てではなく、テナントとして設定された Microsoft Entra エンドポイント (https://login.microsoftonline.com/&lt;..&gt; または &lt;tenant\_ID&gt;/https://login.microsoftonline.com/common/&lt;..&gt;) 宛てに送信される場合も、サイレント サインオン エクスペリエンスはユーザーに提供されます。 次の表に、これらの種類のサインイン要求を行うアプリケーションの一覧を示します。

| アプリケーション名 | アプリケーションの URL |
| --- | --- |
| SharePoint オンライン | https://contoso.sharepoint.com |
| [Microsoft Entra 管理センター](https://entra.microsoft.com) | https://portal.azure.com/contoso.com |

上記の表では、ご利用のテナントに対応する正しいアプリケーション URL にアクセスするために、"contoso.com" をご利用のドメイン名に置き換えてください。

他のアプリケーションでサイレント サインオン エクスペリエンスを使用する場合は、フィードバック セクションからお知らせください。

### シームレス SSO では、"userPrincipalName" ではなく、ユーザー名として "代替 ID" がサポートされていますか。

はい。 `Alternate ID`で示されているように、Microsoft Entra Connect で構成されている場合、シームレス SSO はユーザー名として  をサポートしています。 すべての Microsoft 365 アプリケーションで `Alternate ID` をサポートしているわけではありません。 サポートの説明については、それぞれのアプリケーションのドキュメントを参照してください。

### Microsoft Entra Join とシームレス SSO のシングル サインオン エクスペリエンスの違いは何ですか?

[Microsoft Entra join](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview) は、Microsoft Entra ID に登録されているデバイスのユーザーに SSO を提供します。 そのデバイスは、必ずしもドメインに参加する必要があるとは限りません。 SSO は、Kerberos ではなく、"*プライマリ更新トークン*" (*PRT*) を使用して提供されます。 Windows 10 デバイスで、最適なユーザー エクスペリエンスが実現します。 SSO は、Microsoft Edge ブラウザーで自動的に実行されます。 ブラウザー拡張機能を使用することで Chrome でも動作します。

お客様のテナントでは、Microsoft Entra join とシームレス SSO を使用できます。 この 2 つは補完的な機能です。 両方の機能が有効な場合は、Microsoft Entra Join がシームレス SSO に優先します。

### Microsoft Entra ID に、AD FS を使用せず非 Windows 10 デバイスを登録したいです。 代わりにシームレス SSO を使用できますか。

はい、このシナリオでは[ワークプレース ジョイン クライアント](https://www.microsoft.com/download/details.aspx?id=53554)のバージョン 2.1 以降が必要です。

### "AZUREADSSOACC" コンピューター アカウントの Kerberos 復号化キーをロールオーバーするにはどうすればよいですか。

オンプレミスの AD フォレストで作成した `AZUREADSSO` コンピューター アカウント (Microsoft Entra ID を表します) の Kerberos 解読キーを頻繁にロールオーバーすることが重要です。

重要

`Update-AzureADSSOForest` コマンドレットを使用して、少なくとも **30 日** ごとに Kerberos 復号化キーをロールオーバーすることを強くお勧めします。 `Update-AzureADSSOForest` コマンドレットを使う場合は、1 つのフォレストで  コマンドを複数回実行しないでください。`Update-AzureADSSOForest` そうでない場合、ユーザーの Kerberos チケットの有効期限が切れ、オンプレミスの Active Directory によって再発行されるまで、この機能は動作しなくなります。

Microsoft Entra Connect を実行しているオンプレミス サーバーで、以下の手順を実行します。

注

この手順には、ドメイン管理者とハイブリッド ID 管理者の資格情報が必要です。 ドメイン管理者ではなく、ドメイン管理者によってアクセス許可が割り当てられた場合は、`Update-AzureADSSOForest -OnPremCredentials $creds -PreserveCustomPermissionsOnDesktopSsoAccount` を呼び出す必要があります

**ステップ 1. シームレス SSO が有効になっている AD フォレストのリストの取得**

1. `%ProgramFiles%\Microsoft Azure Active Directory Connect` フォルダーに移動します。
2. 次のコマンドを使用して ADSync PowerShell モジュールをインポートします: `Import-Module "$env:ProgramFiles\Microsoft Azure AD Sync\Bin\ADSync\ADSync.psd1"`。
3. 以下のコマンドを使用して、Seamless SSO PowerShell モジュールをインポートします。`Import-Module .\AzureADSSO.psd1`
4. PowerShell を管理者として実行します。 PowerShell で、`New-AzureADSSOAuthenticationContext` を呼び出します。 このコマンドでは、テナントのハイブリッド ID 管理者の資格情報を入力するポップアップが表示されます。
5. `Get-AzureADSSOStatus | ConvertFrom-Json` を呼び出します。 このコマンドでは、この機能が有効になっている AD フォレストの一覧 ("ドメイン" リストを参照) が提供されます。

**ステップ 2. Kerberos 解読キーが設定された各 AD フォレストでキーを更新する**

1. `$creds = Get-Credential` を呼び出します。 メッセージが表示されたら、目的の AD フォレストのドメイン管理者の資格情報を入力します。

注

ドメイン管理者の資格情報ユーザー名は、SAM アカウント名の形式 (contoso\johndoe または contoso.com\johndoe) で入力する必要があります。 Microsoft はユーザー名のドメイン部分を使用して、DNS を使用してドメイン管理者のドメイン コントローラーを検索します。

注

使用するドメイン管理者アカウントは、保護されているユーザー グループのメンバーであってはなりません。 そうであった場合は、操作が失敗します。

1. `Update-AzureADSSOForest -OnPremCredentials $creds` を呼び出します。 このコマンドは、この特定の AD フォレスト内で `AZUREADSSO` コンピューター アカウントの Kerberos 復号化キーを更新し、Microsoft Entra ID 内でこのキーを更新します。
2. 機能が有効に設定されている AD フォレストごとに、上記の手順を繰り返します。

注

Microsoft Entra Connect 以外のフォレストを更新する場合は、グローバル カタログ サーバー (TCP 3268 および TCP 3269) への接続が可能であることを確認してください。

重要

Microsoft Entra Connect をステージング モードで実行しているサーバーでは、これを実行する必要はありません。

### シームレス SSO はどのように無効にできますか。

**ステップ 1. お使いのテナントで機能を無効にする**

**オプション A: Microsoft Entra Connect を使用して無効にする**

1. Microsoft Entra Connect を実行し、**[Change user sign-in page](ユーザー サインイン ページの変更)** を選択して **[次へ]** をクリックします。
2. **[シングル サインオンを有効にする]** オプションのチェック マークをオフにします。 ウィザードの手順を続行します。

ウィザードを完了すると、お使いのテナントでは Seamless SSO は無効になります。 ただし、画面に次のようなメッセージが表示されます。

「シングル サインオンは現在無効ですが、クリーンアップを完了するために実行できるその他の手動手順があります。 [詳細をご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sso#step-3-disable-seamless-sso-for-each-active-directory-forest-where-youve-set-up-the-feature)」

クリーンアップ プロセスを完了するには、Microsoft Entra Connect を実行しているオンプレミス サーバーで手順 2 と手順 3 を実行します。

**オプション B: PowerShell を使用して無効にする**

Microsoft Entra Connect を実行しているオンプレミス サーバーで以下の手順を実行します。

1. `%ProgramFiles%\Microsoft Azure Active Directory Connect` フォルダーに移動します。
2. 次のコマンドを使用して ADSync PowerShell モジュールをインポートします: `Import-Module "$env:ProgramFiles\Microsoft Azure AD Sync\Bin\ADSync\ADSync.psd1"`。
3. 以下のコマンドを使用して、Seamless SSO PowerShell モジュールをインポートします。`Import-Module .\AzureADSSO.psd1`
4. PowerShell を管理者として実行します。 PowerShell で、`New-AzureADSSOAuthenticationContext` を呼び出します。 このコマンドでは、テナントのハイブリッド ID 管理者の資格情報を入力するポップアップが表示されます。
5. `Enable-AzureADSSO -Enable $false` を呼び出します。

この時点では、シームレス SSO は無効ですが、シームレス SSO を有効に戻したい場合のために、ドメインでは構成されたままです。 ドメインに対するシームレス SSO の構成を完全に削除するには、上記の手順 5 を完了した後で、`Disable-AzureADSSOForest -DomainFqdn <fqdn>` のコマンドレットを呼び出します。

重要

PowerShell を使用してシームレス SSO を無効にした場合、Microsoft Entra Connect の状態は変更されません。 シームレス SSO は、**[ユーザー サインインの変更]** ページに有効と表示されます。

注

Microsoft Entra Connect Sync サーバーがない場合は、サーバーをダウンロードして最初のインストールを実行できます。 これによってサーバーの設定は行われませんが、SSO を無効にするために必要なファイルがアンパックされます。 MSI のインストールが完了したら、Microsoft Entra Connect ウィザードを閉じて、PowerShell を使用してシームレス SSO を無効にする手順を実行します。

**ステップ 2. シームレス SSO が有効になっている AD フォレストのリストの取得**

Microsoft Entra Connect を使用してシームレス SSO を無効にした場合は、以下の手順 1 から手順 4 を実行します。 代わりに PowerShell を使用してシームレス SSO を無効にした場合は、タスク 5 に進みます。

1. `%ProgramFiles%\Microsoft Azure Active Directory Connect` フォルダーに移動します。
2. 次のコマンドを使用して ADSync PowerShell モジュールをインポートします: `Import-Module "$env:ProgramFiles\Microsoft Azure AD Sync\Bin\ADSync\ADSync.psd1"`。
3. 以下のコマンドを使用して、Seamless SSO PowerShell モジュールをインポートします。`Import-Module .\AzureADSSO.psd1`
4. PowerShell を管理者として実行します。 PowerShell で、`New-AzureADSSOAuthenticationContext` を呼び出します。 このコマンドでは、テナントのハイブリッド ID 管理者の資格情報を入力するポップアップが表示されます。
5. `Get-AzureADSSOStatus | ConvertFrom-Json` を呼び出します。 このコマンドを実行すると、この機能が有効になっている AD フォレストの一覧 ([ドメイン] リストを参照) が表示されます。

**手順 3. 表示されている各 AD フォレストから `AZUREADSSO` コンピューター アカウントを手動で削除します。**
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sso-how-it-works"} -->
## Microsoft Entra Connect: シームレス シングル サインオン - しくみ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-how-it-works
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra シームレス シングル サインオン機能のしくみについて説明します。

この記事では、Microsoft Entra シームレス シングル サインオン (シームレス SSO) 機能の技術的なしくみについて説明します。

### シームレス SSO のしくみ

このセクションは、3 つの部分に分かれています。

1. シームレス SSO 機能の設定。
2. Web ブラウザーでの 1 人のユーザーのシングル サインイン トランザクションのシームレス SSO での動作。
3. ネイティブ クライアントでの 1 人のユーザーのシングル サインイン トランザクションのシームレス SSO での動作。

#### 設定のしくみ

シームレス SSO は、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start)に示されているように、Microsoft Entra Connect を使用して有効にできます。 この機能を有効にすると、次の手順が発生します。

- (Microsoft Entra Connect を使用して) Microsoft Entra ID と同期する各 AD フォレストのオンプレミス Active Directory (AD) に、コンピューター アカウント (`AZUREADSSOACC`) が作成されます。
- さらに、Microsoft Entra サインイン プロセス中に使用するために、多数の Kerberos サービス プリンシパル名 (SPN) が作成されます。
- コンピューター アカウントの Kerberos の復号化キーは、Microsoft Entra ID と安全に共有されます。 複数の AD フォレストがある場合は、各コンピューター アカウントに、固有の Kerberos 復号化キーが割り当てられます。

重要

`AZUREADSSOACC` コンピューター アカウントは、セキュリティ上の理由から強固に保護する必要があります。 ドメイン管理者だけがこのコンピューター アカウントを管理できるようにしてください。 コンピューター アカウント上で Kerberos 委任が無効になっていること、および Active Directory 内の他のどのアカウントにも、`AZUREADSSOACC` コンピューター アカウント上の委任のアクセス許可がないことを確認してください。 このコンピューター アカウントは、不注意で削除されるおそれがなく、ドメイン管理者のみがアクセスできる組織単位 (OU) に格納してください。 このコンピューター アカウントの Kerberos の復号化キーも機密として扱う必要があります。 少なくとも 30 日ごとに、 コンピューター アカウントの `AZUREADSSOACC`ことを強くお勧めします。

重要

シームレス SSO では、 `AES256_HMAC_SHA1`、 `AES128_HMAC_SHA1`、 `RC4_HMAC_MD5`の Kerberos 暗号化の種類がサポートされます。 セキュリティを強化するために、Microsoftでは、RC4 ではなく `AzureADSSOAcc$` または別の AES ベースの暗号化の種類を使用するように、`AES256_HMAC_SHA1` アカウントを構成することをお勧めします。 構成された暗号化の種類は、Active Directory Domain Services (AD DS) のアカウントの `msDS-SupportedEncryptionTypes` 属性に格納されます。

July 2026 Windows Server 更新プログラム以降、AD DS の既定の Kerberos 暗号化の種類は RC4 から AES-256 に変更されます。 RC4 を引き続き使用している組織では、この更新プログラムの適用後に認証またはシームレス SSO の問題が発生する可能性があります。 SSO アクセスが中断されないようにするために、Microsoftはできるだけ早く `AzureADSSOAcc$` アカウントを AES-256 に移行することをお勧めします。

`AzureADSSOAcc$` アカウントが現在、`RC4_HMAC_MD5`を使用するように構成されており、AES ベースの暗号化の種類に切り替える予定の場合は、`AzureADSSOAcc$`で説明されているように、最初に アカウントの Kerberos 復号化キーをロールオーバーする必要があります。 暗号化の種類を変更する前にキーをロールオーバーしないと、シームレス SSO が正しく機能しなくなる可能性があります。

このセットアップが完了すると、シームレス SSO は、統合 Windows 認証 (IWA) を使用するその他のサインインと同様に機能します。

#### Web ブラウザーでのシームレス SSO によるサインインのしくみ

Web ブラウザーでのサインインのフローは次のとおりです。

1. ユーザーは、web のアプリケーション（たとえば、outlook Web アプリケーション - https://outlook.office365.com/owa/） に、 企業ネットワーク内のドメインに参加している、会社のデバイスからアクセスしようとします。
2. ユーザーがまだサインインしていない場合、ユーザーは Microsoft Entra サインイン ページにリダイレクトされます。
3. ユーザーが、Microsoft Entra サインイン ページにユーザー名を入力します。

    注

    [一部のアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-faq)では、手順 2. と 3. はスキップされます。
4. Microsoft Entra ID がバックグラウンドで JavaScript を使用し、401 認証エラーを通じて Kerberos チケットを提供するようブラウザーに要求します。
5. ブラウザーはこれを受けて、(Microsoft Entra ID を表す) `AZUREADSSOACC` コンピューター アカウント用のチケットを Active Directory に要求します。
6. Active Directory がコンピューター アカウントを検索し、コンピューター アカウントのシークレットで暗号化された Kerberos チケットをブラウザーに返します。
7. ブラウザーは、Active Directory から取得した Kerberos チケットを Microsoft Entra ID に転送します。
8. Microsoft Entra ID が、以前に共有していたキーを使用して Kerberos チケット (会社のデバイスにサインインしているユーザーの ID を含む) を解読します。
9. 評価後、Microsoft Entra ID はトークンをアプリケーションに返すか、多要素認証などの追加の証明を実行するようにユーザーに要求します。
10. ユーザーのサインインが成功すると、アプリケーションにアクセスできるようになります。

次の図に、すべてのコンポーネントと必要な手順を示します。

[Image: シームレス シングル サインオン - Web アプリのフロー]

シームレス SSO は状況に応じて機能します。 つまり、失敗した場合、サインイン エクスペリエンスは通常の動作にフォールバックします。 その場合、ユーザーは自分のパスワードを入力してサインインする必要があります。

#### ネイティブ クライアントでのシームレス SSO によるサインインのしくみ

ネイティブ クライアントでのサインインのフローは次のとおりです。

1. ユーザーは、(Outlook クライアントなどの) ネイティブ アプリケーションに企業ネットワーク内のドメインに参加している会社のデバイスからアクセスします。
2. ユーザーがまだサインインしていない場合、ネイティブ アプリケーションはデバイスの Windows セッションからユーザーのユーザー名を取得します。
3. アプリが、Microsoft Entra ID にユーザー名を送信し、テナントの WS-Trust MEX エンドポイントを取得します。 この WS-Trust エンドポイントはシームレス SSO 機能によってのみ使用され、Microsoft Entra ID に対する WS-Trust プロトコルの一般的な実装ではありません。
4. 次にアプリは、統合認証エンドポイントが使用可能かどうかを確認するために、WS-Trust MEX エンドポイントにクエリを実行します。 統合認証エンドポイントは、シームレス SSO 機能によって排他的に使用されます。
5. 手順 4. が成功した場合は、Kerberos チャレンジが発行されます。
6. アプリが Kerberos チケットを取得できる場合は、Microsoft Entra の統合認証エンドポイントにそのチケットを転送します。
7. Microsoft Entra ID が、Kerberos チケットを解読して検証します。
8. Microsoft Entra ID は、ユーザーをサインインさせ、アプリに SAML トークンを発行します。
9. アプリは、受け取った SAML トークンを Microsoft Entra ID の OAuth2 トークン エンドポイントに送信します。
10. Microsoft Entra ID は SAML トークンを検証し、アクセス トークン、指定されたリソースの更新トークン、および ID トークンをアプリに発行します。
11. ユーザーは、アプリのリソースにアクセスできます。

次の図に、すべてのコンポーネントと必要な手順を示します。

[Image: シームレス シングル サインオン - ネイティブ アプリ フロー]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sso-quick-start"} -->
## クイック スタート: Microsoft Entra シームレス シングル サインオン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect を使用して、Microsoft Entra シームレス シングル サインオンを開始する方法を学習します。

Microsoft Entra シームレス シングル サインオン (シームレス SSO) を利用することで、ユーザーが企業ネットワークに接続している会社のデスクトップを使用している場合、自動でサインインできます。 シームレス SSO により、ユーザーは、他のオンプレミス コンポーネントを使用しなくても、クラウド ベースのアプリケーションに簡単にアクセスできるようになります。

Microsoft Entra Connect を使用して Microsoft Entra ID のシームレス SSO をデプロイするには、以下のセクションで述べられている手順を実行します。

### 前提条件を確認する

次の前提条件が満たされていることを確認します。

- **Microsoft Entra Connect サーバーを設定している**: サインイン方法として[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)を使用する場合、他に確認すべき前提条件はありません。 サインイン方法として[パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)を使用し、Microsoft Entra Connect と Microsoft Entra ID の間にファイアウォールがある場合には、次のようにしてください。

    - Microsoft Entra Connect のバージョンは 1.1.644.0 以降を使用します。
    - ファイアウォールまたはプロキシで許可している場合は、`*.msappproxy.net` の URL に対するポート 443 での接続を許可リストに追加します。 プロキシ構成でワイルドカードの代わりに特定の URL が必要な場合は、`tenantid.registration.msappproxy.net` を構成できます。ここで、`tenantid` は、機能を構成しているテナントの GUID です。 組織で URL ベースのプロキシの例外が許可されない場合は、代わりに [Azure データセンターの IP 範囲](https://www.microsoft.com/download/details.aspx?id=41653)へのアクセスを許可できます。これは毎週更新されます。 この前提条件は、シームレス SSO 機能を有効にした場合にのみ適用されます。 直接ユーザー サインインには必要ありません。

        注

        - Microsoft Entra Connect のバージョン 1.1.557.0、1.1.558.0、1.1.561.0、1.1.614.0 には、パスワード ハッシュ同期に関連する問題があります。パススルー認証と併せて、パスワード ハッシュ同期を使用する予定が*ない*場合は、[Microsoft Entra Connect のリリース ノート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history)で詳細を確認してください。
- **サポートされている Microsoft Entra Connect トポロジを使用する**: Microsoft Entra Connect で[サポートされているトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-topologies)のいずれかを使用するようにします。

    注

    シームレス SSO では、複数のオンプレミス Windows Server Active Directory (Windows Server AD) フォレストがサポートされます (フォレスト間に Windows Server AD 信頼があるかどうかは問いません)。
- **ドメイン管理者の資格情報がセットアップされている**: 次の各 Windows Server AD フォレストについて、ドメイン管理者の資格情報が必要です。

    - Microsoft Entra Connect を使用して、Microsoft Entra ID と同期します。
    - シームレス SSO を有効にするユーザーが含まれている。
- **先進認証を有効にする**: この機能を使用するには、テナントで[先進認証](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/modern-auth-for-office-2013-and-2016)を有効にする必要があります。
- **Microsoft 365 クライアントの最新バージョンを使用する**: Microsoft 365 クライアントで (たとえば、Outlook、Word、または Excel で) サイレント サインオン エクスペリエンスを実現するには、ユーザーがバージョン 16.0.8730.xxxx 以降を使用している必要があります。

注

送信 HTTP プロキシがある場合は、URL `autologon.microsoftazuread-sso.com` が許可リストに含まれていることを確認します。 ワイルドカードは受け入れられない場合があるため、この URL を明示的に指定してください。

### 機能を有効にする

[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/whatis-hybrid-identity) を使用して、シームレス SSO を有効にします。

注

Microsoft Entra Connect が要件を満たしていない場合は、[PowerShell を使用してシームレス SSO を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sso#manual-reset-of-the-feature)ことができます。 このオプションは、Windows Server AD フォレストごとに複数のドメインがあり、シームレス SSO を有効にするドメインを絞り込む場合に使用します。

*初めて Microsoft Entra Connect をインストールする*場合は、[カスタム インストール パス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom)を選択します。 **[ユーザー サインイン]** ページで、**[シングル サインオンを有効にする]** チェック ボックスをオンにします。

[Image: Microsoft Entra Connect の [ユーザー サインイン] ページで [シングル サインオンを有効にする] が選択されているスクリーンショット。]

注

このオプションは、選択されているサインオンの方法が**パスワード ハッシュ同期**または**パススルー認証**である場合にのみ選択できます。

*Microsoft Entra Connect を既にインストールしている*場合は、**[追加のタスク]** で **[ユーザー サインインの変更]** を選択して **[次へ]** を選択します。 Microsoft Entra Connect バージョン 1.1.880.0 以降を使用している場合は、既定で **[シングル サインオンを有効にする]** オプションが選択されています。 それ以前のバージョンの Microsoft Entra Connect を使用している場合は、**[シングル サインオンを有効にする]** オプションを選択します。

[Image: [ユーザー サインインの変更] が選択された [追加のタスク] ページを示すスクリーンショット。]

**[シングル サインオンを有効にする]** ページまで、ウィザードの手順を続行します。 次の各 Windows Server AD フォレストのドメイン管理者の資格情報を入力します。

- Microsoft Entra Connect を使用して、Microsoft Entra ID と同期します。
- シームレス SSO を有効にするユーザーが含まれている。

ウィザードを完了すると、シームレスな SSO がテナントで有効になります。

注

ドメイン管理者の資格情報は、Microsoft Entra Connect や Microsoft Entra ID には保存されません。 機能を有効にするためにのみ使用されます。

シームレス SSO が正しく有効化されたことを確認するには:

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Entra Connect**&gt;**Connect sync** に移動します。
3. **[シームレス シングル サインオン]** が **[有効]** に設定されていることを確認します。

[Image: 管理者ポータルの [Microsoft Entra Connect] ペインが表示されているスクリーンショット。]

重要

シームレス SSO では、オンプレミスの Windows Server AD ディレクトリ内の各 Windows Server AD フォレストに `AZUREADSSOACC` という名前のコンピューター アカウントが作成されます。 `AZUREADSSOACC` コンピューター アカウントは、セキュリティ上の理由から強固に保護する必要があります。 ドメイン管理者アカウントだけにこのコンピューター アカウントの管理を許可するようにしてください。 コンピューター アカウント上で Kerberos 委任が無効になっていること、および Windows Server AD 内の他のどのアカウントにも、`AZUREADSSOACC` コンピューター アカウント上の委任のアクセス許可がないことを確認してください。 コンピューター アカウントを組織単位に保存して、偶発的な削除から保護し、ドメイン管理者のみがアクセスできるようにします。

注

オンプレミス環境内で Pass-the-Hash および Credential Theft Mitigation アーキテクチャを使用している場合は、適切な変更を行って `AZUREADSSOACC` コンピューター アカウントが最終的に検疫コンテナー内にないことを確認します。

### 機能をロールアウトする

次のセクションの指示に従って、シームレス SSO をユーザーに徐々にロールアウトできます。 まず、Windows Server AD のグループ ポリシーを使用して、全ユーザーまたは選択したユーザーのイントラネット ゾーン設定に次の Microsoft Entra の URL を追加します。

`https://autologon.microsoftazuread-sso.com`

グループ ポリシーを使用して、**[スクリプトを介したステータス バーの更新を許可する]** というイントラネット ゾーン ポリシー設定を有効にする必要もあります。

注

以下の手順は、Windows 上の Internet Explorer、Microsoft Edge、Google Chrome (信頼済みサイト URL のセットを Google Chrome と Internet Explorer で共有する場合) でのみ機能します。 Mozilla Firefox および macOS 上の Google Chrome をセットアップする方法について確認します。

#### ユーザーのイントラネット ゾーン設定の変更が必要な理由

既定では、ブラウザーによって特定の URL から適切なゾーン (インターネットまたはイントラネット) が自動的に判断されます。 たとえば、`http://contoso/` は*イントラネット* ゾーンにマップされる一方で、`http://intranet.contoso.com/` は*インターネット* ゾーンにマップされます (URL にピリオドが含まれているため)。 Microsoft Entra の URL と同様に、クラウド エンドポイントの URL をブラウザーのイントラネット ゾーンに明示的に追加しなければ、ブラウザーからクラウド エンドポイントに Kerberos チケットが送信されることはありません。

ユーザーのイントラネット ゾーン設定は 2 通りの方法で変更できます。

| オプション | 管理者の考慮事項 | ユーザー エクスペリエンス |
| --- | --- | --- |
| グループ ポリシー | 管理者はイントラネット ゾーン設定の編集を禁止します | ユーザーは自分の設定を変更できません |
| グループ ポリシーの基本設定 | 管理者はイントラネット ゾーン設定の編集を許可します | ユーザーは自分の設定を変更できます |

#### グループ ポリシーの詳細な手順

1. グループ ポリシー管理エディター ツールを開きます。
2. 一部またはすべてのユーザーに適用されるグループ ポリシーを編集します。 この例では、**既定のドメイン ポリシー**を使用します。
3. **[ユーザーの構成]**&gt;**[ポリシー]**&gt;**[管理用テンプレート]**&gt;**[Windows コンポーネント]**&gt;**[Internet Explorer]**&gt;**[インターネット コントロール パネル]**&gt;**[セキュリティ ページ]** の順に移動します。 **[サイトとゾーンの割り当て一覧]** を選択します。

    [Image: [サイトとゾーンの割り当て一覧] が選択された [セキュリティ] ページを示すスクリーンショット。]
4. ポリシーを有効にしてから、ダイアログに次の値を入力します。

    - **[値の名前]**: Kerberos チケットの転送先となる Microsoft Entra の URL。
    - **[値]** (データ): **1** (イントラネット ゾーンを示す)

        結果は次の例のようになります。

        値の名前: `https://autologon.microsoftazuread-sso.com`

        値 (データ):1

    注

    一部のユーザーにシームレス SSO を使用させない場合 (ユーザー共有キオスクでサインインする場合など) は、前述の値を **4** に設定します。 この操作により、Microsoft Entra の URL が制限付きゾーンに追加され、そのユーザーのシームレス SSO は常に失敗するようになります。
5. **[OK]** を選択してから、もう一度 **[OK]** を選択します。

    [Image: ゾーン割り当てが選択された [コンテンツの表示] ウィンドウを示すスクリーンショット。]
6. **[ユーザーの構成]**&gt;**[ポリシー]**&gt;**[管理用テンプレート]**&gt;**[Windows コンポーネント]**&gt;**[Internet Explorer]**&gt;**[インターネット コントロール パネル]**&gt;**[セキュリティ ページ]**&gt;**[イントラネット ゾーン]** の順に移動します。 **[スクリプトを介したステータス バーの更新を許可する]** を選択します。

    [Image: [スクリプトを介したステータス バーの更新を許可する] が選択された [イントラネット ゾーン] ページを示すスクリーンショット。]
7. ポリシー設定を有効にしてから、 **[OK]** を選択します。

    [Image: ポリシー設定が有効化された [スクリプトを介したステータス バーの更新を許可する] ウィンドウを示すスクリーンショット。]

#### グループ ポリシーの基本設定の詳しい手順

1. グループ ポリシー管理エディター ツールを開きます。
2. 一部またはすべてのユーザーに適用されるグループ ポリシーを編集します。 この例では、**既定のドメイン ポリシー**を使用します。
3. **[ユーザーの構成]**&gt;**[基本設定]**&gt;**[Windows 設定]**&gt;**[レジストリ]**&gt;**[新規]**&gt;**[レジストリ項目]** の順に移動します。

    [Image: [レジストリ] および [レジストリ項目] が選択されていることを示すスクリーンショット。]
4. 以下の値を示されているとおりに入力または選択して **[OK]** を選択します。

    - **キー パス**: Software\Microsoft\Windows\CurrentVersion\Internet Settings\ZoneMap\Domains\microsoftazuread-sso.com\autologon
    - **値の名前**: https
    - **値の型**: REG\_DWORD
    - **値のデータ**: 00000001

        [Image: [新しいレジストリのプロパティ] ウィンドウを示すスクリーンショット。]

        [Image: レジストリ エディターで新しい値が列挙された状態を示すスクリーンショット。]

#### ブラウザーの考慮事項

以降のセクションでは、さまざまな種類のブラウザーに固有のシームレス SSO について説明します。

##### Mozilla Firefox (すべてのプラットフォーム)

お使いの環境で [認証](https://github.com/mozilla/policy-templates/blob/master/README.md#authentication)ポリシー設定を使用している場合は、Microsoft Entra の URL (`https://autologon.microsoftazuread-sso.com`) を **SPNEGO** セクションに追加するようにします。 **PrivateBrowsing** オプションを **true** に設定して、プライベート ブラウズ モードでシームレス SSO を許可することもできます。

##### Safari (macOS)

macOS を実行しているコンピューターが Windows Server AD に参加していることを確認します。

macOS デバイスを Windows Server AD に参加させる手順については、この記事では説明しません。

##### Chromium に基づく Microsoft Edge (すべてのプラットフォーム)

お使いの環境で [AuthNegotiateDelegateAllowlist](https://learn.microsoft.com/ja-jp/DeployEdge/microsoft-edge-policies#authnegotiatedelegateallowlist) または [AuthServerAllowlist](https://learn.microsoft.com/ja-jp/DeployEdge/microsoft-edge-policies#authserverallowlist) ポリシー設定をオーバーライドしている場合は、これらのポリシー設定にも Microsoft Entra の URL (`https://autologon.microsoftazuread-sso.com`) を追加するようにします。

##### Chromium に基づく Microsoft Edge (macOS および他の非 Windows プラットフォーム)

macOS および他の Windows 以外のプラットフォームの Chromium 版 Microsoft Edge の場合、統合認証用の Microsoft Entra URL を許可リストに追加する方法については、[Chromium 版 Microsoft Edge のポリシー リスト](https://learn.microsoft.com/ja-jp/DeployEdge/microsoft-edge-policies#authserverallowlist)を参照してください。

##### Google Chrome (すべてのプラットフォーム)

お使いの環境で [AuthNegotiateDelegateAllowlist](https://chromeenterprise.google/policies/#AuthNegotiateDelegateAllowlist) または [AuthServerAllowlist](https://chromeenterprise.google/policies/#AuthServerAllowlist) ポリシー設定をオーバーライドしている場合は、これらのポリシー設定にも Microsoft Entra の URL (`https://autologon.microsoftazuread-sso.com`) を追加するようにします。

##### macOS

サードパーティの Active Directory グループ ポリシーの拡張機能を使用して、Microsoft Entra の URL を macOS の Firefox および Google Chrome のユーザーにロールアウトする場合については、この記事では扱いません。

##### ブラウザーの既知の制限事項

拡張保護モードで実行されている場合、シームレス SSO は Internet Explorer ブラウザーでも機能しません。 シームレス SSO では、Chromium に基づく Microsoft Edge の次期バージョンがサポートされています。仕様により InPrivate とゲスト モードで機能します。 Microsoft Edge (レガシ) はサポートされなくなりました。

対応するドキュメントに基づいて、InPrivate またはゲスト ユーザー用に `AmbientAuthenticationInPrivateModesEnabled` を構成することが必要な場合があります。

- [Microsoft Edge Chromium](https://learn.microsoft.com/ja-jp/DeployEdge/microsoft-edge-policies#ambientauthenticationinprivatemodesenabled)
- [Google Chrome](https://chromeenterprise.google/policies/?policy=AmbientAuthenticationInPrivateModesEnabled)

### シームレス SSO をテストする

特定のユーザーについてこの機能をテストするには、次の条件がすべて満たされていることを確認してください。

- ユーザーが会社のデバイスでサインインしている。
- デバイスが Windows Server AD ドメインに参加している。 デバイスは *Microsoft Entra に参加*している必要は[ありません](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)。
- デバイスが、企業のワイヤードまたはワイヤレス ネットワーク上や、VPN 接続などのリモート アクセス接続を介してドメイン コントローラーに直接接続している。
- グループ ポリシーを使用して、このユーザーに機能がロールアウトされている。

ユーザーがユーザー名を入力するがパスワードは入力しないシナリオをテストするには、次の手順に従います。

- [https://myapps.microsoft.com](https://myapps.microsoft.com/) にサインインします。 必ずブラウザーのキャッシュをクリアするか、サポートされているいずれかのブラウザーのプライベート モードでの新しいプライベート ブラウザー セッションを使用してください。

ユーザーがユーザー名またはパスワードを入力する必要がないシナリオをテストするには、次のいずれかの手順を使用します。

- `https://myapps.microsoft.com/contoso.onmicrosoft.com` にサインインします。 必ずブラウザーのキャッシュをクリアするか、サポートされているいずれかのブラウザーのプライベート モードでの新しいプライベート ブラウザー セッションを使用してください。 `contoso` を、実際のテナント名に置き換えます。
- 新しいプライベート ブラウザー セッションで `https://myapps.microsoft.com/contoso.com` にサインインします。 `contoso.com` を、自分のテナントで検証されたドメイン (フェデレーション ドメインではなく) に置き換えます。

### キーをロール オーバーする

この機能を有効にするとき、Microsoft Entra Connect によって、シームレス SSO を有効にしたすべての Windows Server AD フォレストでコンピューター アカウント (Microsoft Entra ID を表します) が作成されます。 詳細については、「[Microsoft Entra のシームレス シングル サインオン: 技術的な詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-how-it-works)」を参照してください。

重要

コンピューター アカウントの Kerberos 解読キーが漏洩した場合、それを利用し、すべての同期ユーザーに対して Kerberos チケットが生成されます。 悪意のあるアクターが、Microsoft Entra のサインインを偽装し、ユーザーを危険にさらす可能性があります。 定期的に (または、少なくとも 30 日ごとに) Kerberos の解読キーをロールオーバーすることを強くお勧めします。

キーのロールオーバー方法の詳細については、「[Microsoft Entra のシームレス シングル サインオン: よく寄せられる質問](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-faq#how-can-i-roll-over-the-kerberos-decryption-key-of-the--azureadsso--computer-account-)」を参照してください。

重要

この機能を有効にした後に、"*直ちに*" この手順を実行する必要はありません。 少なくとも 30 日に 1 回は、Kerberos 暗号化の解除キーをロールオーバーしてください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sso-user-privacy"} -->
## ユーザー プライバシーと Microsoft Entra のシームレス シングル サインオン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-user-privacy
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra のシームレス SSO と GDPR コンプライアンスについて説明します。

注

この記事は、デバイスまたはサービスから個人データを削除する手順について説明しており、GDPR の下で義務を果たすために使用できます。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

### 概要

Microsoft Entra のシームレス SSO では、個人データが含まれる場合がある次の種類のログが作成されます。

- Microsoft Entra Connect トレース ログ ファイル。

次の 2 つの方法でシームレス SSO のユーザー プライバシーを強化します。

1. 要請を受けた際、個人のデータを抽出し、その個人のデータを環境から削除する
2. 48時間以上データを保存しないようにしてください。

実装や保守がより簡単なので、2 番目の方法を強くお勧めします。 ログの種類ごとの以下の手順を確認してください。

#### Microsoft Entra Connect トレース ログ ファイルを削除する

**%ProgramData%\AADConnect** フォルダーの内容を確認して、Microsoft Entra Connect のインストールまたはアップグレード、あるいはシームレス SSO の構成の変更から 48 時間以内のこのフォルダーのトレース ログ コンテンツ (**trace-\*.log** ファイル) を削除します。このアクションによって GDPR が適用されるデータが作成されるためです。

重要

このフォルダー内にある **PersistedState.xml** ファイルは削除しないでください。このファイルは Microsoft Entra Connect の以前のインストールの状態を保持するために使用され、さらには、アップグレードのインストールが完了された場合にも使用されるためです。 このファイルが個人に関するデータを含むことはないため、絶対に削除しないでください。

これらのトレース ログ ファイルの確認と削除には Windows エクスプ ローラーを使用することもできますし、次のような PowerShell スクリプトを使用して、必要なアクションを実行することもできます。

```powershell
$Files = ((Get-Item -Path "$env:programdata\aadconnect\trace-*.log").VersionInfo).FileName 
 
Foreach ($file in $Files) { 
    {Remove-Item -Path $File -Force} 
}
```

拡張子が ".PS1" のファイルにスクリプトを保存します。 必要に応じて、このスクリプトを実行してください。

関連する Microsoft Entra Connect の GDPR 要件の詳細については、[こちらの記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-user-privacy)をご覧ください。

#### ドメイン コントローラー ログに関する注意事項

監査ログが有効になっている場合、この製品では、お使いのドメイン コント ローラーのセキュリティ ログを生成できます。 監査ポリシーの構成に関する詳細については、[こちらの記事](https://learn.microsoft.com/ja-jp/previous-versions/tn-archive/dd277403%28v=technet.10%29)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-staged-rollout"} -->
## Microsoft Entra Connect: 段階的ロールアウトを使用したクラウド認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout
- Service: entra-id / hybrid-connect
- Article date: 2026-09-15
- Summary: この記事では、段階的なロールアウトを使用して、フェデレーション認証からクラウド認証に移行する方法について説明します。

### 概要

段階的ロールアウト (SRO) は、フェデレーション ドメインを持つ組織の一時的なテスト メカニズムとして意図されており、ドメイン全体をフェデレーション [からマネージドに移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication#convert-domains-from-federated-to-managed)前に、ユーザーのグループでクラウド認証をテストできます。 これらの機能には、Microsoft Entra 多要素認証、条件付きアクセス、漏洩した資格情報の Identity Protection、ID ガバナンスなどがあります。 この方法では、ドメインをフェデレーションからマネージドに完全に移行する前に、機能とユーザー エクスペリエンスを検証できます。

段階的ロールアウトを開始する前に、次の条件が 1 つ以上当てはまる場合の影響を考慮する必要があります。

- 現在、オンプレミスの Multi-Factor Authentication Server を使用しています。
- 認証にスマート カードを使用しています。
- お使いのサーバーは、特定のフェデレーション専用機能を提供しています。
- サードパーティのフェデレーション ソリューションから管理サービスに移行しようとしています。

この機能を試す前に、適切な認証方法の選択に関するガイドを確認するようお勧めします。 詳細については、「メソッドの比較」の表を参照して、[Microsoft Entra ハイブリッド ID ソリューションの適切な認証方法を選択する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn#comparing-methods) を参照してください。

この機能の概要については、次の「段階的なロールアウトとは?」の動画をご覧ください。

注意

段階的なロールアウトは、永続的な構成として設計 **されていません** 。 組織は、段階的なロールアウト テスト中に、フォールバックとしてフェデレーション ID プロバイダー (IdP) を維持する必要があります。 フェデレーション IdP を設定せずにマネージド認証に移行した後も段階的なロールアウトを引き続き使用すると、予期しない認証エラーやユーザー エクスペリエンスの低下につながる可能性があります。 スムーズな移行を確実に行うために、テストが成功したら [、マネージド認証へのドメイン の切り替えを](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication#convert-domains-from-federated-to-managed) 完了することをお勧めします。

### 段階的ロールアウトを使用するためのベスト プラクティス

- 段階的なロールアウトは、ドメイン全体の変更前にクラウド認証の動作を検証するためにパイロット グループにのみ使用します。
- 認証のフォールバック パスを確保するために、SRO テスト中にフェデレーション IdP を維持します。
- マネージド認証への明確な移行計画がない限り、すべてのユーザーを段階的なロールアウトに配置しないでください。
- テスト中に認証フローを監視して、異常を早期に検出します。
- テストが成功したら、マネージド認証へのタイムリーな切り越しを計画します。

### 前提条件

- [フェデレーション ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed)を持つ Microsoft Entra テナントがあること。
- 次のいずれかのオプションの移行を決定していること。

    - **パスワード ハッシュ同期 (sync)**。 詳細については、[パスワード ハッシュ同期の概要](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)ページを参照してください。
    - **パススルー認証**。 詳細については、「[パススルー認証とは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)」を参照してください
    - **Microsoft Entra 証明書ベースの認証 (CBA) 設定**。 詳細については、「[Microsoft Entra の証明書ベースの認証の概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)」を参照してください

    いずれのオプションでも、サイレント サインインできるよう、シングル サインオン (SSO) を有効にすることをお勧めします。 Windows 7 または 8.1 のドメイン参加デバイスの場合、シームレス SSO の使用をお勧めします。 詳細については、[シームレス SSO の概要](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)ページを参照してください。 Windows 10、Windows Server 2016 以降のバージョンでは、 [プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token) による SSO を使用します。 [Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)の場合、[Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)または[個人登録済みデバイスでは](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration)、職場または学校アカウントの追加が使用されます。
- クラウド認証に移行するユーザーに必要なすべての適切なテナント ブランドポリシーと条件付きアクセス ポリシーを構成する必要があります。
- フェデレーション認証からクラウド認証に移行した場合は、DirSync 設定 `synchronizeUpnForManagedUsersEnabled` が `true` に設定されていることを確認する必要があります。それ以外の場合、Microsoft Entra ID では、マネージド認証を使用するライセンスユーザー アカウントの UPN または代替ログイン ID への同期の更新は許可されません。 詳細については、「[Microsoft Entra Connect Sync サービスの機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-features)」を参照してください。
- Microsoft Entra 多要素認証を使用する予定の場合は、 [統合された登録](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-registration-mfa-sspr-combined)を有効にすることをお勧めします。 これにより、ユーザーはセルフサービス パスワード リセット (SSPR) と多要素認証の両方に対して認証方法を 1 回登録できます。 注: ステージングされたロールアウト中に MyProfile ページを使用してパスワードのリセットやパスワードの変更のために SSPR を使用するときは、Microsoft Entra Connect は新しいパスワード ハッシュを同期する必要があり、これにはリセット後最大 2 分かかることがあります。
- 段階的なロールアウト機能を使用するには、テナントのハイブリッド ID 管理者である必要があります。
- 特定の AD フォレストで *シームレス SSO* を有効にするには、ドメイン管理者である必要があります。
- Microsoft Entra ID または Microsoft Entra 参加を展開する場合、Windows 10 1903 Update にアップグレードする必要があります。

### サポートされるシナリオ

段階的なロールアウトでは、次のシナリオがサポートされています。 この機能は、次の場合にのみ機能します：

- Microsoft Entra Connect を使用して Microsoft Entra ID にプロビジョニングされたユーザー。 「クラウドのみ」のユーザーには適用されません。
- ブラウザーおよび *最新の認証* クライアント上でのユーザー サインイン トラフィック。 レガシ認証を使用するアプリケーションまたはクラウド サービスは、フェデレーション認証のフローにフォールバックします。 レガシ認証の例としては、最新の認証が無効になっている Exchange オンラインや、最新の認証をサポートしていない Outlook 2010 があります。
- グループ内のユーザーの数に制限はありません。 ただし、機能ごとに最大 10 グループ、パスワード ハッシュ同期、パススルー認証、シームレス SSO にそれぞれ 10 グループを使用できます。
- Windows 10 バージョン 1903 以降の、フェデレーション サーバーへの通信経路を使用しない Windows 10 ハイブリッド参加または Microsoft Entra 参加のプライマリ更新トークンの取得 (ユーザーの UPN がルーティング可能であり、ドメイン サフィックスが Microsoft Entra ID で検証されている場合)。
- Windows 10 バージョン 1909 以降では、段階的なロールアウトでオートパイロットの登録がサポートされています。

### サポートされていないシナリオ

次のシナリオは、段階的なロールアウトではサポートされていません。

- POP3 や SMTP などのレガシ認証はサポートされていません。
- セキュリティ グループに対して段階的なロールアウトが有効になっている場合、オンプレミス ドメインへの書き戻しを伴うセルフサービス パスワード リセット (SSPR) はサポートされません。 場合によっては機能しますが、段階的ロールアウトが有効になっている場合、SSPR が一貫して動作することを保証することはできません。
- 特定のアプリケーションは、認証中に「domain\_hint」クエリ パラメーターを Microsoft Entra ID に送信します。 これらのフローは続行され、段階的なロールアウトが有効になっているユーザーは、認証にフェデレーションを引き続き使用します。
- 管理者は、セキュリティ グループを使用してクラウド認証をロールアウトできます。 オンプレミスの Active Directory セキュリティグループを使用しているときに、同期の待機時間を回避するには、クラウド セキュリティ グループを使用するようお勧めします。 次の条件が適用されます：

    - 機能ごとに最大 10 個のグループを使用できます。 つまり、*パスワードハッシュ同期*、*パススルー認証*、*シームレス SSO* に対して、それぞれ 10 個のグループを使用できます。
    - 入れ子になったグループはサポートされていません。
    - 段階的なロールアウトでは、動的グループはサポートされていません。
    - グループ内に連絡先オブジェクトがあると、グループの追加がブロックされます。
- 段階的なロールアウトでセキュリティ グループを初めて追加するときは、UX タイムアウトを回避するために 200 ユーザーに制限されます。グループを追加した後は、必要に応じて、さらに多くのユーザーをそこに直接追加できます。
- ユーザーがパスワード ハッシュ同期 (PHS) を使用して段階的ロールアウトを行っている間、既定ではパスワードの有効期限は適用されません。 パスワードの有効期限は、"CloudPasswordPolicyForPasswordSyncedUsersEnabled" を有効にすることで適用できます。 "CloudPasswordPolicyForPasswordSyncedUsersEnabled" が有効な場合、パスワードの有効期限ポリシーは、オンプレミスでパスワードが設定されたときから 90 日間に設定され、これをカスタマイズするオプションはありません。 ユーザーが段階的ロールアウト中の場合、プログラムによる PasswordPolicies 属性の更新はサポートされていません。 'CloudPasswordPolicyForPasswordSyncedUsersEnabled' を設定する方法については、「[パスワードの有効期限ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#cloudpasswordpolicyforpasswordsyncedusersenabled)」を参照してください。
- 1903 より前のバージョンの Windows 10 のための、Windows 10 ハイブリッド参加または Microsoft Entra 参加のプライマリ更新トークンの取得。 このシナリオは、ユーザーのサインインが段階的なロールアウトのスコープ内にある場合でも、フェデレーション サーバーの WS-Trust エンドポイントにフォールバックします。
- すべてのバージョンの Windows 10 ハイブリッド参加または Microsoft Entra 参加のプライマリ更新トークンの取得 (ユーザーのオンプレミス UPN がルーティング可能でない場合)。 このシナリオは、段階的なロールアウトのモードでは WS-Trust エンドポイントにフォールバックしますが、段階的な移行が完了し、ユーザーのサインオンがフェデレーション サーバーに依存しなくなったときに機能しなくなります。
- Windows 10 バージョン 1903 以降で非永続的な VDI を設定している場合は、フェデレーション ドメインにとどまる必要があります。 非永続的な VDI では、マネージド ドメインへの移行はサポートされていません。 詳細については、[「デバイス ID とデスクトップの仮想化」](https://learn.microsoft.com/ja-jp/entra/identity/devices/howto-device-identity-virtual-desktop-infrastructure)を参照してください。
- 登録機関またはスマート カード ユーザーとして機能しているフェデレーション サーバー経由で発行された証明書と共に Windows Hello for Business のハイブリッド証明書信頼を使用している場合、このシナリオは段階的なロールアウトではサポートされません。

    注意

    Microsoft Entra Connect または PowerShell を使用して、フェデレーション認証からクラウド認証への最終カットオーバーを行う必要があります。 段階的なロールアウトによって、ドメインがフェデレーションからマネージドに切り替えられることはありません。 ドメインの切り取りについて詳しくは、「 [ドメインをフェデレーションからマネージドに変換する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication#convert-domains-from-federated-to-managed)」をご覧ください。

### 段階的なロールアウトの使用を開始する

段階的なロールアウトを使用して*パスワード ハッシュ同期*サインインをテストするには、次のセクションの作業前の指示に従ってください。

使用する PowerShell コマンドレットの詳細については、「[Microsoft Entra ID 2.0 プレビュー](https://learn.microsoft.com/ja-jp/powershell/module/azuread/?view=azureadps-2.0-preview&preserve-view=true#staged_rollout)」を参照してください。

### パスワード ハッシュ同期の事前作業

1. Microsoft Entra Connect の *[オプション機能]* ページから、[パスワード ハッシュ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#optional-features)同期を有効にします。

    [Image: Microsoft Entra Connect の「オプション機能」ページのスクリーンショット]
2. すべてのユーザーのパスワード ハッシュが、Microsoft Entra ID に同期されるように、完全な *パスワード ハッシュ同期*サイクルが実行されていることを確認します。 *パスワードハッシュ同期*の状態を確認するには、[Microsoft Entra Connect Sync によるパスワードハッシュ同期のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-password-hash-synchronization) の PowerShell 診断を使用できます。

    [Image: Microsoft Entra Connect のトラブルシューティング ログのスクリーンショット]

段階的なロールアウトを使用して*パススルー認証*サインインをテストする場合は、次のセクションの前の作業前の手順に従って有効にします。

### パススルー認証の事前作業

1. *パススルー認証*エージェントを実行する、Windows Server 2012 R2 以降を実行しているサーバーを特定します。

    Microsoft Entra Connect サーバーは選択**しないでください**。 そのサーバーがドメインに参加していて、選択したユーザーを Active Directory で認証し、送信ポートや URL で Microsoft Entra ID と通信できることを確認します。 詳細については、クイックスタートの「ステップ1：前提条件を確認する」[のセクションを確認します: Microsoft Entra シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start)。
2. [Microsoft Entra Connect 認証エージェントをダウンロード](https://aka.ms/getauthagent)して、サーバーにインストールします。
3. [高可用性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start)を有効にするには、他のサーバーに追加の認証エージェントをインストールします。
4. [スマート ロックアウトの設定](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout)が適切に構成されているか確認します。 そうすることで、ユーザーのオンプレミスの Active Directory Domain Services アカウントが悪意のある攻撃者によってロックアウトされないようにすることができます。

段階的なロールアウトのために選択するサインイン方法 (パスワード ハッシュ同期またはパススルー認証) には関係なく、シームレス SSO を有効にすることをお勧めします。*シームレス SSO* を有効にするには、次のセクションの作業前の手順に従います。

### シームレス SSO の事前作業

PowerShell を使用して、Active Directory Domain Services フォレストで*シームレス SSO* を有効にします。 複数の Active Directory フォレストがある場合は、各フォレストに対して個別に有効にします。 シームレス SSO は、段階的なロールアウトのために選択されているユーザーに対してのみトリガーされます。 既存のフェデレーション設定には影響しません。

次のタスクに従って、*シームレス SSO* を有効にします。

1. Microsoft Entra Connect サーバーにサインインします。
2. `%ProgramFiles%\Microsoft Azure Active Directory Connect` フォルダーに移動します。
3. ADSync PowerShell モジュールをインポートします。

    ```powershell
    Import-Module "$env:ProgramFiles\Microsoft Azure AD Sync\Bin\ADSync\ADSync.psd1"
    ```
4. *シームレス SSO* PowerShell モジュールをインポートします。

    ```powershell
    Import-Module .\AzureADSSO.psd1
    ```
5. PowerShell を管理者として実行します。 PowerShell で、`New-AzureADSSOAuthenticationContext` を呼び出します。 このコマンドを実行すると、テナントのハイブリッド ID 管理者資格情報を入力できるペインが開きます。
6. `Get-AzureADSSOStatus | ConvertFrom-Json` を呼び出します。 このコマンドは、この機能が有効になっている Active Directory フォレストの一覧 (「ドメイン」リストを参照) を表示します。 これは、既定ではテナント レベルで false に設定されています。

    [Image: PowerShell の出力例]
7. `$creds = Get-Credential` を呼び出します。 プロンプトが表示されたら、目的の Active Directory フォレストのドメイン管理者の資格情報を入力します。
8. `Enable-AzureADSSOForest -OnPremCredentials $creds` を呼び出します。 このコマンドは、*シームレス SSO* に必要なこの特定の Active Directory フォレスト用のオンプレミスのドメイン コントローラーから AZUREADSSOACC コンピューターのアカウントを作成します。
9. *シームレス SSO* を使用するには、URL がイントラネット ゾーンに含まれている必要があります。 グループポリシーを使用して、これらの Url をデプロイする方法については、[クイックスタート: Microsoft Entra シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start#step-3-roll-out-the-feature)。
10. 完全なチュートリアルについては、[シームレス SSO](https://aka.ms/SeamlessSSODPDownload) 用の*デプロイ計画* をダウンロードすることもできます。

### 段階的なロールアウトを有効にする

特定の機能 (*パススルー認証*、*パスワードハッシュ同期*、または *シームレスSSO*) をグループ内の選択したユーザーセットにロールアウトするには、次のセクションの手順に従います。

#### テナントの特定の機能の段階的なロールアウトを有効にする

次のオプションをロールアウトできます。

- **パスワード ハッシュ同期** + **シームレス SSO**
- **パススルー認証** + **シームレス SSO**
- **サポート対象外** - **パスワード ハッシュ同期** + **パススルー認証** + **シームレス SSO**
- **証明書ベースの認証の設定**
- **Azure Multifactor Authentication**

段階的ロールアウトを構成するには、こちらの手順に従います。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Entra Connect**&gt;**Connect sync** に移動します。
3. *[Microsoft Entra Connect]* ページの *[クラウド認証の段階的なロールアウト]* で、**[マネージド ユーザー サインインの段階的ロールアウトを有効にする]** リンクを選択します。
4. *[段階的ロールアウト機能を有効にする]* ページで、有効にするオプション ([パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)、[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)、[シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)、または[証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/certificate-based-authentication-federation-get-started)) を選択します。 たとえば、**パスワード ハッシュ同期**と**シームレス シングル サインオン**を有効にする場合、両方のコントロールを **[オン]** に切り替えます。
5. 選択した機能にグループを追加します。 たとえば、"パススルー認証" と "シームレス SSO" です。 タイムアウトを回避するには、最初に、セキュリティ グループに含まれるメンバーが 200 人以下であることを確認してください。

    注意

    グループ内のメンバーは、段階的ロールアウトに対して自動的に有効になります。 段階的ロールアウトでは、入れ子になったグループと動的メンバーシップ グループはサポートされていません。 新しいグループを追加するとき、グループ内のユーザー (新しいグループ 1 つにつき最大 200 ユーザー) が、即座にマネージド認証を使用するように更新されます。 グループを編集する (ユーザーを追加または削除する) 場合、変更が有効になるまでに最大 24 時間かかることがあります。 シームレス SSO は、ユーザーがシームレス SSO グループ内にあり、PTA または PHS グループのいずれかにも含まれている場合にのみ適用されます。

### 段階的ロールアウトの移行中のユーザー認証の動作

#### 追加のフェデレーション サインインまたはマネージド サインインが必要なシナリオ

1. **ユーザーが段階的ロールアウトに追加されました。** ユーザーが段階的ロールアウト グループに追加されたとき、または所属するグループが段階的ロールアウトに対して有効になっている場合、認証エクスペリエンスはフェデレーションから管理にすぐに切り替わりません。 ユーザーは、既存のフェデレーション認証方法を使用して、追加の対話型サインインを 1 つ完了する必要があります。 このサインイン後、Microsoft Entraはユーザーの状態を更新し、後続のサインインにマネージド認証を適用します。
2. **ユーザーが段階的ロールアウトから削除されました。** ユーザーが段階的ロールアウト グループから削除されたとき、またはユーザーのグループが段階的ロールアウトから削除された場合、ユーザーは引き続きマネージド認証を使用します。 ユーザーは、Microsoft Entraを使用して、追加の対話型サインインを 1 つ完了する必要があります。 このサインイン後、Microsoft Entraはユーザーをフェデレーション認証に切り替え、その後のサインインはフェデレーション ID プロバイダーにリダイレクトされます。
3. **Microsoft Entra ID 保護 の修復イベント** セルフサービス パスワード リセット (SSPR)、リスク修復、リスクの無視など、特定のアカウントの回復とMicrosoft Entra ID 保護[修復アクション](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-remediate-unblock#how-risk-remediation-works)は、ユーザーの段階的ロールアウト状態をリセットできます。 その結果、ユーザーは次のサインイン時にフェデレーション ID プロバイダーにリダイレクトされる可能性があります。 ユーザーは、既存のフェデレーション認証方法を使用して、追加の対話型サインインを 1 つ完了する必要があります。 このサインイン後、Microsoft Entraは後続のサインインのためにマネージド認証を再確立します。

#### フェデレーション サインインを 1 つ追加しないようにする回避策

ユーザーが段階的ロールアウトに新しく追加されると、段階的ロールアウトが有効になる前に、フェデレーション ID プロバイダーを介して 1 つの追加認証を実行することが必要になる場合があります。 この動作を回避するには、ユーザーの初期サインイン エクスペリエンス中に一時アクセス パス (TAP) を使用します。

##### 推奨される回避策

Microsoft Entraは、ユーザーをフェデレーション ID プロバイダーにリダイレクトする前に TAP を評価するため、管理者は段階的ロールアウトに追加した直後にユーザーに TAP を発行できます。

推奨されるフローは次のとおりです。

1. 段階的ロールアウト グループにユーザーを追加します。
2. ユーザーの一時アクセス パス (TAP) を生成します。
3. ユーザーに TAP を使用してMicrosoft Entraにサインインさせます。
4. サインインが成功すると、ユーザーは段階的ロールアウトの一部として認識され、次のことができます。
    - Microsoft Authenticatorやパスキーなど、その他の認証方法を登録します。
    - 利用可能な既存の Microsoft Entra 認証方法を使用します。

##### Benefits

最初のサインインに TAP を使用すると、次の方法でスムーズにオンボードできます。

- 追加のフェデレーション認証手順を回避する。
- ユーザーがパスワードを使用して従来のフェデレーション サインイン エクスペリエンスにフォールバックする必要がないようにします。
- フェデレーション認証からマネージド認証への移行中の摩擦を軽減します。

注意

この回避策は、新しくオンボードされた段階的ロールアウト ユーザーを対象としており、移行戦略の一部として使用して、Microsoft Entraマネージド認証へのシームレスな移行を提供できます。

### 監査

段階的なロールアウトで実行するさまざまなアクションの監査イベントが有効になっています。

- パスワード ハッシュ同期、パススルー認証、またはシームレス SSO に対して段階的なロールアウトを有効にするときの監査イベント。

    注意

    段階的なロールアウトを使用してシームレス SSO を有効したときに監査イベントが記録されます。

    [Image: [機能のロールアウトポリシーの作成] ウィンドウ - [アクティビティ] タブ]

    [Image: [機能のロールアウトポリシーの作成] ウィンドウ - [変更されたプロパ ティ] タブ]
- *パスワード ハッシュ同期*、*パススルー認証*、*シームレス SSO* に対してグループを追加したときの監査イベント。

    注意

    段階的なロールアウトのためにグループがパスワード ハッシュ同期に追加されたときに監査イベントが記録されます。

    [Image: [機能ロールアウトへのグループの追加] ペイン - [アクティビティ] タブ]

    [Image: [機能ロールアウトへのグループの追加] ペイン - [変更されたプロパティ] タブ]
- グループに追加されたユーザーが段階的なロールアウトに対して有効になったときの監査イベント。

    [Image: [機能ロールアウトへのユーザーの追加] ペイン - [アクティビティ] タブ]

    [Image: [機能ロールアウトへのユーザーの追加] ペイン - [ターゲット] タブ]

### 検証

*パスワード ハッシュ同期*または *パススルー認証* を使用したサインイン (ユーザー名とパスワードによるサインイン) をテストするには、次のタスクを実行します。

1. エクストラネットで、プライベート ブラウザー セッションの [\[アプリ\] ページ](https://myapps.microsoft.com)に移動し、段階的なロールアウトのために選択されているユーザー アカウントの UserPrincipalName (UPN) を入力します。

    段階的なロールアウトの対象となっているユーザーは、フェデレーション ログインページにリダイレクトされません。 代わりに、Microsoft Entra テナントブランドのサインインページでサインインするように求められます。
2. UserPrincipalName でフィルター処理して、[Microsoft Entra サインイン アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)にサインインが正常に表示されていることを確認します。

*シームレス SSO* を使用したサインインをテストするには、次のようにします:

1. イントラネットで、ブラウザー セッションを使用して [\[アプリ\] ページ](https://myapps.microsoft.com)に移動したあと、段階的ロールアウトのために選択されているユーザー アカウントの UserPrincipalName (UPN) を入力します。

    そのユーザーが*シームレス SSO* の段階的ロールアウトの対象になっている場合は、「サインインしようとしています...」というメッセージが表示されてから、サイレントにサインインが実行されます。
2. UserPrincipalName でフィルター処理して、[Microsoft Entra サインイン アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)にサインインが正常に表示されていることを確認します。

    選択された段階的なロールアウトのユーザーに対して Active Directory フェデレーション サービス (AD FS) で引き続き実行されているユーザーのサインインを追跡するには、「[AD FS のトラブルシューティング - イベントとログ](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/troubleshooting/ad-fs-tshoot-logging#types-of-events)」の手順に従います。 サード パーティのフェデレーション プロバイダーで、これを確認する方法については、ベンダーのドキュメントを確認してください。

    注意

    ユーザーが PHS で段階的なロールアウトを使用しているときにパスワードを変更すると、同期時間のため、有効になるまでに最大 2 分かかることがあります。 ユーザーがパスワードを変更した後、ヘルプデスクへの問い合わせを避けるため、あらかじめ期待することを説明してください。

### 監視

[Microsoft Entra 管理センター](https://entra.microsoft.com)の新しいハイブリッド認証ブックを使用して、段階的ロールアウトで追加または削除されたユーザーやグループに加え、段階的ロールアウト中のユーザーのサインインを監視できます。

[Image: ハイブリッド認証ブック]

### 段階的なロールアウトからユーザーを削除する

ユーザーをグループから削除すると、そのユーザーの段階的なロールアウトが無効になります。 段階的なロールアウト機能を無効にするには、そのコントロールを **[オフ]** に戻します。

重要

証明書ベースの認証の段階的ロールアウトでグループからユーザーを削除する場合(ユーザーが証明書を使用して Windows デバイスにサインインしている場合)、Entra ID で証明書ベースの認証方法を有効にしたままにしておくことをお勧めします。 ユーザーは、段階的なロールアウトから削除した後も、Windows にサインインし、フェデレーション ID プロバイダーを使用してプライマリ更新トークンを更新できる期間、証明書ベースの認証を有効にしておく必要があります。

### よく寄せられる質問

**Q:この機能を運用環境で使用できますか？**

A:はい。この機能は運用テナントで使用できますが、まずテスト テナントでこの機能を試すようお勧めします。

**Q:この機能を使用して、一部のユーザーがフェデレーション認証を使用し、その他のユーザーがクラウド認証を使用するという永続的な "共存" を維持することはできますか？**

A:いいえ。この機能は、クラウド認証をテストする目的で設計されています。 少数のユーザー グループのテストが成功したら、クラウド認証に移行する必要があります。 予期しない認証フローにつながる可能性があるため、永続的な混在状態は、推奨しません。

**Q: PowerShell を使用して段階的なロールアウトを実行できますか?**

A:はい。 PowerShell を使用して段階的なロールアウトを実行する方法については、「[Microsoft Entra ID プレビュー」](https://learn.microsoft.com/ja-jp/powershell/module/azuread/?view=azureadps-2.0-preview&preserve-view=true#staged_rollout)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-best-practices-changing-default-configuration"} -->
## Microsoft Entra Connect Sync: 既定構成を変更する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-best-practices-changing-default-configuration
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect Sync の既定の構成を変更するためのベスト プラクティスを紹介します。

このトピックの目的は、Microsoft Entra Connect Sync に対する、サポートされている変更とサポートされていない変更について説明することです。

Microsoft Entra Connect によって作成される構成は、オンプレミスの Active Directory と Microsoft Entra ID を同期するほとんどの環境で "そのまま" 動作します。 ただし、場合によっては、特定のニーズや要件を満たすために構成にいくつかの変更を適用する必要があります。

### サービス アカウントに対する変更

Microsoft Entra Connect Sync は、インストール ウィザードによって作成されたサービス アカウントで実行されます。 このサービス アカウントには、同期によって使用されるデータベースの暗号化キーが保持されます。127 文字の長いパスワードで作成され、パスワードの有効期限が切れないように設定されています。

Warnung

ADSync サービス アカウントのパスワードを変更またはリセットした場合、暗号化キーを破棄して ADSync サービス アカウントのパスワードを再初期化するまで、同期サービスは正しく開始されません。 これを行うには、「[ADSync サービス アカウントのパスワードの変更](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-serviceacct-pass)」を参照してください。

### スケジューラに対する変更

ビルド 1.1 (2016 年 2 月) のリリース以降では、既定の 30 分の同期サイクルとは異なる同期サイクルで [スケジューラ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler) を構成できます。

### 同期規則に対する変更

インストール ウィザードには、最も一般的なシナリオに対応できる構成が用意されています。 構成を変更する必要がある場合は、サポートされている構成となるように、これらの規則に従う必要があります。

Warnung

既定の同期規則に変更を加えた場合、次に Microsoft Entra Connect が更新されるときにこれらの変更が上書きされ、予期しない望ましくない同期結果が発生する可能性があります。

- 既定の "直接" 属性フローが自分の所属している組織に適さない場合は、 [属性フローを変更](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration#other-common-attribute-flow-changes) できます。
- [属性をフローしない](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration#do-not-flow-an-attribute) ように設定したうえで、Microsoft Entra ID に既にある属性値を削除する必要がある場合は、このシナリオ用に規則を作成する必要があります。
- 不要な同期規則は削除するのではなく無効 にしてください。 削除した規則は、アップグレード時に再作成されます。
- 標準の規則を変更するには、元の規則のコピーを作成し、標準の規則を無効にする必要があります。 同期規則エディターが役に立ちます。
- 同期規則エディターを使用して、カスタムの同期規則をエクスポートします。 このエディターには、ディザスター リカバリー シナリオにおいて同期規則を簡単に再作成をするために使用できる PowerShell スクリプトが用意されています。

Warnung

標準の同期規則には拇印があります。 これらの規則に変更を加えた場合、拇印が一致しなくなります。 その後、Microsoft Entra Connect の新しいリリースを適用しようとすると、問題が発生する可能性があります。 この記事で説明されている方法でのみ変更を加えます。

#### 不要な同期規則は削除するのではなく無効

標準の同期規則を削除しないでください。 これは、次のアップグレード中に再作成されます。

場合によっては、インストール ウィザードによって、トポロジで動作しない構成が生成されます。 たとえば、アカウント リソース フォレスト トポロジがあるが、Exchange スキーマを使用してアカウント フォレスト内のスキーマを拡張した場合、Exchange のルールはアカウント フォレストとリソース フォレストに対して作成されます。 この場合、Exchange 用の同期規則を無効にする必要があります。

[Image: 無効な同期規則]

前の図では、インストール ウィザードでアカウント フォレストに古い Exchange 2003 スキーマが見つかりました。 このスキーマ拡張が追加されてから、リソース フォレストが Fabrikam の環境に導入されました。 古い Exchange 実装の属性が同期されないようにするには、図のように同期規則を無効にする必要があります。

#### 標準の規則の変更

標準の規則を変更する必要があるのは、結合規則を変更しなければならない場合のみです。 属性フローを変更する必要がある場合は、標準の規則よりも優先順位が高い同期規則を作成してください。 実際に複製する必要がある規則は **In from AD - User Join** のみです。 他のすべての規則は、優先順位が高い規則でオーバーライドできます。

標準の規則を変更する必要がある場合は、標準の規則のコピーを作成し、元の規則を無効にする必要があります。 そして、コピーした規則を変更します。 同期規則エディターは、この手順で役立ちます。 標準の規則を開くと、次のダイアログ ボックスが表示されます。[Image: 標準の規則の警告]

**[はい]** を選択して規則のコピーを作成します。 複製した規則を開きます。[Image: コピーした規則]

このコピーした規則のスコープ、結合、変換について必要な変更を加えます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-change-addsacct-pass"} -->
## Microsoft Entra Connect Sync: AD DS アカウントのパスワードの変更 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-addsacct-pass
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックドキュメントでは、AD DS アカウントのパスワードを変更した後に Microsoft Entra Connect を更新する方法について説明します。

AD DS コネクタ アカウントは、Microsoft Entra Connect がオンプレミスの Active Directory と通信するために使用するユーザー アカウントを指します。 AD で AD DS コネクタ アカウントのパスワードを変更する場合は、Microsoft Entra Connect 同期サービスを新しいパスワードで更新する必要があります。 それ以外の場合、同期はオンプレミスの Active Directory と正しく同期できなくなり、次のエラーが発生します。

- Synchronization Service Manager では、オンプレミス AD を使用したインポート操作またはエクスポート操作が失敗し、**の開始資格情報なし** エラーが発生します。
- Windows イベント ビューアーでは、アプリケーション イベント ログに **イベント ID 6000** のエラーが含まれており、"資格情報が無効であるため、管理エージェント "contoso.com" を実行できませんでした" **メッセージが**。

### AD DS コネクタ アカウントの新しいパスワードで同期サービスを更新する方法

同期サービスを新しいパスワードで更新するには:

1. Synchronization Service Manager (START → Synchronization Service) を起動します。 [Image: 同期サービス マネージャー]
2. [**コネクタ**] タブに移動します。
3. パスワードが変更された AD DS コネクタ アカウントに対応する **AD Connector** を選択します。
4. [**アクション]**の中で、[**プロパティ]**を選択します。
5. ポップアップ ダイアログで、[**Active Directory フォレストに接続]**: を選択します。
6. **パスワード** ボックスに、AD DS コネクターアカウントの新しいパスワードを入力してください。
7. [OK]クリックして新しいパスワードを保存し、ポップアップ ダイアログを閉じます。
8. Windows サービス コントロール マネージャーで、**Microsoft Entra ID Sync** サービスを再起動します。 これは、古いパスワードへの参照がメモリ キャッシュから確実に削除されるようにするためです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-change-serviceacct-pass"} -->
## Microsoft Entra Connect 同期: ADSync サービス アカウントの変更 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-serviceacct-pass
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、暗号化キーの詳細と、パスワードの変更後にこのキーを破棄する方法について説明します。

ADSync サービス アカウントのパスワードを変更すると、暗号化キーを破棄し、ADSync サービス アカウントのパスワードを再初期化するまで、同期サービスは正常に開始しなくなります。

重要

2017 年 3 月以前のバージョンのビルドで Connect を使用した場合、Windows は、セキュリティ上の理由から暗号化キーを破棄するため、サービス アカウントのパスワードをリセットする必要はありません。 アカウントを他のアカウントに変更するには、Microsoft Entra Connect を再インストールする必要があります。 2017 年 4 月以降のビルドにアップグレードする場合、サービス アカウントのパスワードを変更することはできますが、使用されるアカウントを変更することはできません。

Microsoft Entra Connect は同期サービスの一部として、暗号化キーを使用して AD DS コネクタ アカウントと ADSync サービス アカウントのパスワードを保存します。 これらのアカウントは、データベースへの保存前に暗号化されます。

暗号化キーは、[Windows データ保護 (DPAPI)](https://learn.microsoft.com/ja-jp/previous-versions/ms995355%28v=msdn.10%29) を使用して保護されています。 DPAPI では、**ADSync サービス アカウント**を使用して暗号化キーを保護します。

サービス アカウントのパスワードを変更する必要がある場合は、「ADSync サービス アカウントの暗号化キーの破棄」の手順に従って変更します。 この手順は、なんらかの理由で暗号化キーを破棄する必要がある場合にも使用してください。

### パスワードの変更により生じる問題

サービス アカウントのパスワードを変更する場合、2 つの手順を完了する必要があります。

最初に、Windows サービス コントロール マネージャーでパスワードを変更する必要があります。 この問題が解決されるまで、次の問題が発生します。

- Windows サービス コントロール マネージャーで同期サービスを開始しようとすると、"**ローカル コンピューターの Microsoft Azure AD Sync サービスを開始できませんでした**"というエラーが表示されます。 **エラー 1069:ログオンに失敗したため、サービスを開始できませんでした**" というエラーが表示されます。
- Windows イベント ビューアーでは、システム イベント ログに**イベント ID 7038** のエラーと、"**現在構成されているパスワードでは、次のエラーにより ADSync サービスにログオンできませんでした:ユーザー名またはパスワードが正しくありません**" というメッセージが記録されます。

次に、特定の条件下では、パスワードを更新すると同期サービスで DPAPI を使用して暗号化キーを取得できなくなります。 暗号化キーがないと、同期サービスは、オンプレミスの AD および Microsoft Entra ID と同期するために必要なパスワードの暗号化を解除できません。 次のようなエラーが表示されます。

- Windows サービス コントロール マネージャーで同期サービスを開始しようとすると、暗号化キーを取得できないため、"**ローカル コンピューターで Microsoft Entra ID Sync を開始できませんでした。詳細情報はシステム イベント ログを参照してください。これが Microsoft 以外のサービスである場合は、サービス ベンダーに問い合わせてください。その際、サービス固有のエラー コードが -21451857952 であることを伝えてください**" というエラーが表示され失敗します。
- Windows イベント ビューアーでは、アプリケーション イベント ログに**イベント ID 6028** のエラーと、"サーバー暗号化キーにアクセスできませんでした" というエラー メッセージが記録されます。

これらのエラーが表示されないようにするために、パスワードの変更時には「ADSync サービス アカウントの暗号化キーの破棄」の手順に従ってください。

### ADSync サービス アカウントの暗号化キーの破棄

重要

次の手順は、ビルド 1.1.443.0 以前の Microsoft Entra Connect にのみ適用されます。 これは、新しいバージョンの Microsoft Entra Connect では使用できません。AD 同期サービス アカウントのパスワードを変更すると、暗号化キーの破棄は Microsoft Entra Connect 自体によって処理されるため、新しいバージョンでは次の手順は必要ありません。

暗号化キーを破棄するには、次の手順を実行します。

#### 暗号化キーを破棄する必要がある場合の対処方法

暗号化キーを破棄する必要がある場合は、次の手順に従って破棄を行います。

1. 同期サービスを停止する
2. 既存の暗号化キーを破棄する
3. AD DS コネクタ アカウントのパスワードを入力する
4. ADSync サービス アカウントのパスワードを再初期化する
5. 同期サービスを開始する

##### 同期サービスを停止する

まず、Windows サービス コントロール マネージャーでサービスを停止できます。 サービスを停止する場合は、そのサービスが実行されていないことを確認してください。 サービスが実行されている場合は、完了するまで待ってから停止します。

1. Windows サービス コントロール マネージャーにアクセスします ([スタート]、[サービス] の順に移動します)。
2. **[Microsoft Entra ID Sync](Microsoft Entra ID 同期)** を選択して [停止] をクリックします。

##### 既存の暗号化キーを破棄する

新しい暗号化キーを作成できるように、既存の暗号化キーを破棄します。

1. 管理者として Microsoft Entra Connect サーバーにサインインします。
2. 新しい PowerShell セッションを開始します。
3. `'$env:ProgramFiles\Microsoft Azure AD Sync\bin\'` フォルダーに移動します。
4. `./miiskmu.exe /a` コマンドを実行します

[Image: コマンドを実行した後の PowerShell を示すスクリーンショット。]

##### AD DS コネクタ アカウントのパスワードを入力する

データベース内に保存されている既存のパスワードの暗号化を解除できなくなるため、同期サービスに AD DS コネクタ アカウントのパスワードを入力する必要があります。 同期サービスでは、新しい暗号化キーを使用してこのパスワードを暗号化します。

1. Synchronization Service Manager を起動します ([スタート]、[同期サービス] の順に移動します)。 [Image: 同期サービス マネージャー]
2. **[コネクタ]** タブに移動します。
3. オンプレミス AD に対応する **AD コネクタ** を選択します。 AD コネクタが複数ある場合は、各コネクタについて次の手順を繰り返します。
4. **[アクション]** の **[プロパティ]** を選択します。
5. ポップアップ ダイアログで、 **[Connect to Active Directory Forest] \(Active Directory フォレストに接続)** を選択します。
6. AD DS アカウントのパスワードを **[パスワード]** テキストボックスに入力します。 パスワードがわからない場合は、この手順を実行する前に既知の値に設定する必要があります。
7. **[OK]** をクリックして新しいパスワードを保存し、ポップアップ ダイアログを閉じます。 [Image: [プロパティ] ウィンドウの [Connect to Active Directory Forest](Active Directory フォレストに接続) ページを示すスクリーンショット。]

##### Entra ID Connector アカウントのパスワードを再初期化する

同期サービスに Microsoft Entra サービス アカウントのパスワードを直接指定することはできません。 代わりに、**Add-ADSyncAADServiceAccount** コマンドレットを使用して、Microsoft Entra サービス アカウントを再初期化する必要があります。 このコマンドレットによりアカウントのパスワードがリセットされ、同期サービスで利用できるようになります。

1. Microsoft Entra Connect 同期サーバーにサインインし、PowerShell を開きます。
2. Microsoft Entra グローバル管理者の資格情報を指定するには、`$credential = Get-Credential` を実行します。
3. コマンドレット `Add-ADSyncAADServiceAccount -AADCredential $credential` を実行します。

    コマンドレットが成功すると、PowerShell コマンド プロンプトが表示されます。

このコマンドレットは、Microsoft Entra ID と同期エンジンの両方で、サービス アカウントのパスワードをリセットして更新します。

##### 同期サービスを開始する

これで、同期サービスで必要な暗号化キーとすべてのパスワードにアクセスできるようになったので、Windows サービス コントロール マネージャーでサービスを再起動します。

1. Windows サービス コントロール マネージャーにアクセスします ([スタート]、[サービス] の順に移動します)。
2. **[Microsoft Entra ID Sync](Microsoft Entra ID 同期)** を選択して [再起動] をクリックします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration"} -->
## Microsoft Entra Connect Sync: 既定の構成に変更を加える - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect Sync で構成を変更する方法について説明します。

この記事の目的は、Microsoft Entra Connect Sync の既定の構成を変更する方法について説明することです。いくつかの一般的なシナリオの手順を示します。 この知識があれば、独自のビジネス ルールに基づいて独自の構成を簡単に変更できる必要があります。

Warnung

既定の同期規則に変更を加えた場合、これらの変更は次回 Microsoft Entra Connect が更新されると上書きされ、予想外で、望ましくない同期結果が発生する可能性があります。

既定の最初の同期規則には拇印があります。 これらの規則に変更を加えた場合、拇印が一致しなくなります。 その後、Microsoft Entra Connect の新しいリリースを適用しようとすると、問題が発生する可能性があります。 変更する場合は、この記事の方法に従ってください。

### 同期規則エディター

同期規則エディターは、既定の構成を表示および変更するために使用されます。 これは、**Microsoft Entra Connect** グループの **[スタート**] メニューにあります。[Image: 同期規則エディターの [スタート] メニュー]

このエディターを開くと、既定の標準の規則が表示されます。

[Image: 同期規則エディター]

#### エディターでのナビゲーション

エディターの上部にあるドロップダウンを使用すると、特定のルールをすばやく見つけることができます。 たとえば、属性 proxyAddresses が含まれているルールを表示する場合は、ドロップダウンを次のように変更できます。[Image: SRE フィルター処理] フィルター処理をリセットして新しい構成を読み込むには、キーボードの F5 キーを押します。

右上には、[ **新しいルールの追加]** ボタンがあります。 このボタンを使用して、独自のカスタム ルールを作成します。

下部には、選択した同期規則に対して動作するためのボタンがあります。 **編集** と **削除** は、期待する操作を行います。 **エクスポート** では、同期規則を再作成するための PowerShell スクリプトが生成されます。 この手順では、あるサーバーから別のサーバーに同期規則を移動できます。

### 最初のカスタム 規則を作成する

最も一般的な変更は属性フローです。 ソース ディレクトリ内のデータは、Microsoft Entra ID と同じではない可能性があります。 このセクションの例では、ユーザーの指定された名前が常に *適切なケース*であることを確認します。

#### スケジューラを無効にする

[スケジューラ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler)は、既定で 30 分ごとに実行されます。 変更を加え、新しいルールのトラブルシューティングを行っている間は、開始されていないことを確認します。 スケジューラを一時的に無効にするには、PowerShell を起動し、 `Set-ADSyncScheduler -SyncCycleEnabled $false`を実行します。

[Image: スケジューラを無効にする]

#### ルールを作成する

1. [ **新しいルールの追加]** をクリックします。
2. [ **説明** ] ページで、次のように入力します。[Image: 受信規則のフィルタリング]
    - **名前**: 規則にわかりやすい名前を付けます。
    - **説明**: ルールの目的を他のユーザーが理解できるように、いくつかの明確化を行います。
    - **接続システム**: オブジェクトが見つかるシステムです。 この場合は、 **Active Directory コネクタ**を選択します。
    - **接続システム/メタバース オブジェクトの種類**: **ユーザー** と **ユーザー**をそれぞれ選択します。
    - **リンクの種類**: この値を **[結合**] に変更します。
    - **優先順位**: システム内で一意の値を指定します。 数値が小さい場合は、優先順位が高いことを示します。
    - **タグ**: これは空のままにします。 このボックスに値が設定されているのは、Microsoft の規定外のルールのみです。
3. **[スコープ フィルター**] ページで、「**givenName ISNOTNULL**」と入力します。[Image: 受信規則のスコープ フィルター] このセクションは、ルールを適用するオブジェクトを定義するために使用します。 空のままにすると、ルールはすべてのユーザー オブジェクトに適用されます。 ただし、これには会議室、サービス アカウント、およびその他のユーザー以外のユーザー オブジェクトが含まれます。
4. [ **結合ルール** ] ページで、フィールドを空のままにします。
5. [変換] ページで **FlowType** を **Expression** に変更します。 **[ターゲット属性] で**、**givenName** を選択します。 **ソース**に**「PCase([givenName])」**と入力します。 [Image: 受信規則の変換] 同期エンジンでは、関数名と属性の名前の両方で大文字と小文字が区別されます。 間違ったことを入力すると、ルールを追加するときに警告が表示されます。 保存して続行することはできますが、ルールを再度開いて修正する必要があります。
6. **[追加]** をクリックして規則を保存します。

新しいカスタム規則は、システム内の他の同期規則と共に表示されます。

#### 変更を確認する

この新しい変更により、期待どおりに動作し、エラーが発生していないことを確認する必要があります。 オブジェクトの数に応じて、この手順を実行する方法は 2 つあります。

- すべてのオブジェクトで完全同期を実行します。
- 1 つのオブジェクトでプレビューと完全同期を実行します。

**[スタート]** メニューから**同期サービス**を開きます。 このセクションの手順はすべて、このツールに含まれます。

**すべてのオブジェクトの完全同期**

1. 上部 **にある [コネクタ** ] を選択します。 前のセクションで変更したコネクタ (この場合は Active Directory Domain Services) を特定し、それを選択します。
2. **[アクション] で**、[実行] を選択**します**。
3. [ **完全同期**] を選択し、[ **OK] を選択します**。 [Image: 完全同期] オブジェクトはメタバースで更新されるようになりました。 メタバース内のオブジェクトを調べることで、変更を確認します。

**1 つのオブジェクトでのプレビューと完全同期**

1. 上部 **にある [コネクタ** ] を選択します。 前のセクションで変更したコネクタ (この場合は Active Directory Domain Services) を特定し、それを選択します。
2. **[Search Connector Space (コネクタ スペースの検索)]** を選択します。
3. 変更をテストするために使用するオブジェクトを検索するには、 **Scope** を使用します。 オブジェクトを選択し、[ **プレビュー**] をクリックします。
4. 新しい画面で、[ **プレビューのコミット**] を選択します。[Image: コミット プレビュー] これで、変更はメタバースにコミットされます。

**メタバース内のオブジェクトを表示する**

1. いくつかのサンプル オブジェクトを選択して、値が予期され、ルールが適用されていることを確認します。
2. 上部から **[メタバース検索** ] を選択します。 関連するオブジェクトを検索するために必要なフィルターを追加します。
3. 検索結果からオブジェクトを開きます。 属性値を確認し、[ **同期ルール** ] 列で、ルールが想定どおりに適用されていることを確認します。[Image: メタバース検索]

#### スケジューラを有効にする

すべてが想定どおりの場合は、スケジューラをもう一度有効にすることができます。 PowerShell から、 `Set-ADSyncScheduler -SyncCycleEnabled $true`を実行します。

### その他の一般的な属性フローの変更

前のセクションでは、属性フローを変更する方法について説明しました。 このセクションでは、いくつかの追加の例を示します。 同期規則を作成する手順は省略されていますが、前のセクションの完全な手順を確認できます。

#### 既定値以外の属性を使用する

この Fabrikam シナリオでは、特定の名前、姓、表示名にローカル アルファベットが使用されるフォレストがあります。 これらの属性のラテン文字表現は、拡張属性にあります。 Microsoft Entra ID と Microsoft 365 でグローバル アドレス一覧を作成する場合、組織は代わりにこれらの属性を使用したいと考えています。

既定の構成では、ローカル フォレストのオブジェクトは次のようになります。[Image: 属性フロー 1]

他の属性フローを使用してルールを作成するには、次の操作を行います。

1. **[スタート**] メニューから**同期規則エディター**を開きます。
2. 左側で **[受信]** を選択したまま、 **[新しいルールの追加]** ボタンをクリックします。
3. ルールに名前と説明を付けます。 オンプレミスの Active Directory インスタンスと関連するオブジェクトの種類を選択します。 **[リンクの種類]** で **[結合]** を選択します。 **[優先順位**] で、別のルールで使用されていない数値を選択します。 標準の規則は 100 から始まるので、この例では 50 を使用できます。 [Image: 属性フロー 2]
4. **スコープ フィルターは**空のままにします。 (つまり、フォレスト内のすべてのユーザー オブジェクトに適用する必要があります)。
5. **参加ルール**は空のままにします。 (つまり、既定の規則で結合を処理します)。
6. **変換で**、次のフローを作成します。[Image: 属性フロー 3]
7. **[追加]** をクリックして規則を保存します。
8. **Synchronization Service Manager** に移動します。 [ **コネクタ**] で、ルールを追加したコネクタを選択します。 [ **実行**] を選択し、[ **完全同期**] を選択します。 完全同期では、現在のルールを使用してすべてのオブジェクトが再計算されます。

これは、このカスタム ルールを使用した同じオブジェクトの結果です。[Image: 属性フロー 4]

#### 属性の長さ

文字列属性は既定でインデックスを作成でき、最大長は 448 文字です。 さらに多く含まれる可能性のある文字列属性を使用する場合は、属性フローに以下を含めるようにしてください。`attributeName`&lt;- `Left([attributeName],448)`。

#### userPrincipalSuffix の変更

Active Directory の userPrincipalName 属性は、ユーザーによって常に認識されるとは限らず、サインイン ID として適していない可能性があります。 Microsoft Entra Connect 同期インストール ウィザードでは、 *メール*などの別の属性を選択できます。 ただし、場合によっては、属性を計算する必要があります。

たとえば、Contoso 社には、運用環境用とテスト用の 2 つの Microsoft Entra ディレクトリがあります。 テスト テナントのユーザーは、サインイン ID で別のサフィックスを使用する必要があります。`Word([userPrincipalName],1,"@") & "@contosotest.com"`。

この式では、最初の @ 記号 (Word) より前のすべてを取り、固定文字列と連結します。

#### 複数値属性を単一値に変換する

Active Directory の一部の属性は、Active Directory ユーザーとコンピューターでは単一値のように見えますが、スキーマでは複数の値を持ちます。 description 属性の例を次に示します。`description`&lt;- `IIF(IsNullOrEmpty([description]),NULL,Left(Trim(Item([description],1)),448))`。

この式では、属性に値がある場合は、属性の最初の項目 (*Item) を*取得し、先頭と末尾のスペース (*Trim*) を削除してから、最初の 448 文字 (*左*) を文字列に保持します。

#### 属性をフローしない

このセクションのシナリオの背景については、「 [属性フロー プロセスの制御」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-declarative-provisioning#control-the-attribute-flow-process)参照してください。

属性をフローしない方法は 2 つあります。 1 つ目は、インストール ウィザードを使用して [選択した属性を削除することです](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#azure-ad-app-and-attribute-filtering)。 このオプションは、以前に属性を同期したことがない場合に機能します。 ただし、この属性の同期を開始し、後でこの機能を使用して削除した場合、同期エンジンは属性の管理を停止し、既存の値は Microsoft Entra ID に残ります。

属性の値を削除し、将来フローしないようにする場合は、カスタム ルールを作成する必要があります。

この Fabrikam シナリオでは、クラウドに同期する属性の一部が存在してはならないことに気付きました。 これらの属性が Microsoft Entra ID から削除されていることを確認します。[Image: 不適切な拡張属性]

1. 新しい受信同期規則を作成し、説明を設定します。 [Image: 説明]
2. **FlowType** のための**式**と**Source** のための **AuthoritativeNull** を使用して属性フローを作成します。 リテラル **の AuthoritativeNull** は、優先順位の低い同期規則が値の設定を試みる場合でも、メタバースで値を空にする必要があることを示します。 [Image: 拡張属性の変換]
3. 同期規則を保存します。 **同期サービス**を起動し、コネクタを見つけ、[**実行**] を選択して、[**完全同期**] を選択します。 この手順では、すべての属性フローが再計算されます。
4. コネクタ スペースを検索して、意図した変更がエクスポートされることを確認します。 [Image: 段階的な削除]

### PowerShell を使用してルールを作成する

変更が少ない場合は、同期規則エディターを使用しても問題なく動作します。 多くの変更を行う必要がある場合は、PowerShell を使用することをお勧めします。 一部の高度な機能は、PowerShell でのみ使用できます。

#### 既定の規則に対応する PowerShell スクリプトを取得する

既定の規則を作成した PowerShell スクリプトを確認するには、同期規則エディターで規則を選択し、 **[エクスポート]** をクリックします。 このアクションにより、ルールを作成した PowerShell スクリプトが提供されます。

#### 高度な優先順位

既定の同期規則の優先順位の値は 100 から始まります。 フォレストが多数あり、多くのカスタム変更を行う必要がある場合は、99 の同期規則では不十分な場合があります。

既存のルールの前に追加のルールを挿入するように同期エンジンに指示できます。 この動作を取得するには、次の手順に従います。

1. Synchronization Rules Editor で最初の既定の同期規則 (**In from AD-User Join**) をマークし、 **[エクスポート]** を選択します。 SR 識別子の値をコピーします。[Image: 変更前の PowerShell]
2. 新しい同期規則を作成します。 同期規則エディターを使用して作成できます。 規則を PowerShell スクリプトにエクスポートします。
3. プロパティ **PrecedenceBefore**に、既定規則の識別子の値を挿入します。 優先順位を **0** に設定**します**。 識別子属性が一意であり、別の規則の GUID を再利用していないことを確認します。 また、 **ImmutableTag** プロパティが設定されていないことも確認します。 このプロパティは、既定の規則に対してのみ設定する必要があります。
4. PowerShell スクリプトを保存して実行します。 その結果、カスタム ルールに優先順位値 100 が割り当てられ、その他すべての既定のルールがインクリメントされます。[Image: 変更後の PowerShell]

必要に応じて、同じ **PrecedenceBefore** 値を使用して、多くのカスタム同期規則を作成できます。

### UserType の同期を有効にする

Microsoft Entra Connect では、バージョン 1.1.524.0 以降の User オブジェクトの **UserType** 属性の同期がサポートされています。 具体的には、次の変更が導入されました。

- Microsoft Entra Connector のオブジェクト型 **User** のスキーマは、文字列型で単一値の UserType 属性を含むように拡張されます。
- メタバース内のオブジェクト型 **Person** のスキーマが拡張され、UserType 属性が含まれます。この属性は文字列型であり、単一値です。

既定では、UserType 属性は、オンプレミスの Active Directory に対応する UserType 属性がないため、同期が有効になっていません。 同期を手動で有効にする必要があります。 これを行う前に、Microsoft Entra ID によって適用される次の動作を書き留めておく必要があります。

- Microsoft Entra のみ、UserType 属性に対して **Member** と **Guest** の 2 つの値を受け入れます。
- Microsoft Entra Connect で UserType 属性が同期に対して有効になっていない場合、ディレクトリ同期によって作成された Microsoft Entra ユーザーの UserType 属性は **Member** に設定されます。
- バージョン 1.5.30.0 より前の Microsoft Entra ID では、既存の Microsoft Entra ユーザーの UserType 属性を Microsoft Entra Connect で変更することは許可されませんでした。 以前のバージョンでは、Microsoft Entra ユーザーの作成時にのみ設定でき、 [PowerShell を使用して変更できました](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)。

UserType 属性の同期を有効にする前に、まず、オンプレミスの Active Directory から属性を派生する方法を決定する必要があります。 最も一般的な方法を次に示します。

- ソース属性として使用する未使用のオンプレミス AD 属性 (extensionAttribute1 など) を指定します。 指定されたオンプレミス AD 属性は、 **型文字列**で、単一値で、 **値 Member** または **Guest** を含む必要があります。

    この方法を選択した場合は、UserType 属性の同期を有効にする前に、指定された属性に、Microsoft Entra ID に同期されているオンプレミス Active Directory 内のすべての既存のユーザー オブジェクトに対して正しい値が設定されていることを確認する必要があります。
- または、他のプロパティから UserType 属性の値を派生させることができます。 たとえば、オンプレミスの AD userPrincipalName 属性がドメイン 部分 *@partners.fabrikam123.org* で終わる場合は、すべてのユーザーを**ゲスト**として同期する必要があります。

    前述のように、以前のバージョンの Microsoft Entra Connect では、既存の Microsoft Entra ユーザーの UserType 属性を Microsoft Entra Connect で変更することはできません。 そのため、決定したロジックが、テナント内のすべての既存の Microsoft Entra ユーザーに対して UserType 属性が既に構成されている方法と一致していることを確認する必要があります。

UserType 属性の同期を有効にする手順は、次のように要約できます。

1. 同期スケジューラを無効にし、進行中の同期がないことを確認します。
2. オンプレミスの AD コネクタ スキーマにソース属性を追加します。
3. UserType を Microsoft Entra Connector スキーマに追加します。
4. オンプレミスの Active Directory から属性値をフローする受信同期規則を作成します。
5. 属性値を Microsoft Entra ID にフローする送信同期規則を作成します。
6. 完全同期サイクルを実行します。
7. 同期スケジューラを有効にします。

注

このセクションの残りの部分では、これらの手順について説明します。 これらは、単一フォレスト トポロジとカスタム同期規則を使用しない Microsoft Entra 展開のコンテキストで説明されています。 マルチフォレスト トポロジ、カスタム同期規則が構成されている場合、またはステージング サーバーがある場合は、それに応じて手順を調整する必要があります。

#### 手順 1: 同期スケジューラを無効にし、進行中の同期がないことを確認する

意図しない変更を Microsoft Entra ID にエクスポートしないようにするには、同期規則の更新中に同期が行われないようにします。 組み込みの同期スケジューラを無効にするには:

1. Microsoft Entra Connect サーバーで PowerShell セッションを開始します。
2. コマンドレット `Set-ADSyncScheduler -SyncCycleEnabled $false`を実行して、スケジュールされた同期を無効にします。
3. 同期サービスの **開始**&gt;**同期サービス**に移動して、同期サービス マネージャーを開きます。
4. [ **操作** ] タブに移動し、 *進行中*の状態の操作がないことを確認します。

#### 手順 2: オンプレミスの AD コネクタ スキーマにソース属性を追加する

すべての Microsoft Entra 属性がオンプレミスの AD コネクタ スペースにインポートされるわけではありません。 インポートされた属性の一覧にソース属性を追加するには:

1. Synchronization Service Manager の [**コネクタ**] タブに移動します。
2. オンプレミスの AD コネクタを右クリックし、[プロパティ] を選択 **します**。
3. ポップアップ ダイアログ ボックスで、**[属性の選択]** タブに移動します。
4. 属性リストでソース属性がチェックされていることを確認します。
5. **[OK]** をクリックして保存します。 [Image: オンプレミス AD コネクタ スキーマにソース属性を追加する]

#### 手順 3: Microsoft Entra Connector スキーマに UserType 属性を追加する

既定では、UserType 属性は Microsoft Entra Connect Space にインポートされません。 インポートされた属性の一覧に UserType 属性を追加するには:

1. Synchronization Service Manager の [**コネクタ**] タブに移動します。
2. **Microsoft Entra コネクタ**を右クリックし、[プロパティ] を選択**します**。
3. ポップアップ ダイアログ ボックスで、**[属性の選択]** タブに移動します。
4. 属性リストで UserType 属性がチェックされていることを確認します。
5. **[OK]** をクリックして保存します。

[Image: Microsoft Entra Connector スキーマにソース属性を追加する]

#### 手順 4: オンプレミスの Active Directory から属性値をフローする受信同期規則を作成する

受信同期規則では、属性値がオンプレミスの Active Directory からメタバースにソース属性からフローすることを許可します。

1. 同期規則エディターを開くには、[同期規則エディター&gt;**開始**] に移動**します**。
2. 検索フィルターの **[方向]** を **[受信]** に設定します。
3. [ **新しい規則の追加]** ボタンをクリックして、新しい受信規則を作成します。
4. [**説明**] タブで、次の構成を指定します。

    | 特性 | 価値 | 詳細 |
    | --- | --- | --- |
    | 名前 | *名前* を指定する | 例: *In from AD – User UserType* |
    | 説明 | *説明を入力* |  |
    | 接続システム | *オンプレミスの AD コネクタを選択する* |  |
    | 接続システム オブジェクトの種類 | **利用者** |  |
    | メタバース オブジェクト型 | **人物** |  |
    | リンクの種類 | **接続** |  |
    | 優先順位 | *1 ~ 99* の数値を選択します。 | 1 ~ 99 は、カスタム同期規則用に予約されています。 別の同期規則で使用される値は選択しないでください。 |
5. **[スコープ フィルター**] タブに移動し、次の句を含む **1 つのスコープ フィルター グループ**を追加します。

    | 特性 | オペレーター | 価値 |
    | --- | --- | --- |
    | 管理者説明 | NOTSTARTWITH | User\_ |

    スコープ フィルターは、この受信同期規則が適用されるオンプレミスの AD オブジェクトを決定します。 この例では、*In from AD – User Common* の既成の同期ルールで使用されるのと同じスコープフィルターを使用します。このフィルターは、Microsoft Entra ユーザー ライトバック機能を使用して作成されたユーザーオブジェクトに対して同期ルールが適用されないようにします。 Microsoft Entra Connect の展開に応じてスコープ フィルターを調整することが必要になる場合があります。
6. [ **変換** ] タブに移動し、目的の変換ルールを実装します。 たとえば、未使用のオンプレミス AD 属性 (extensionAttribute1 など) を UserType のソース属性として指定した場合、直接属性フローを実装できます。

    | フローの種類 | ターゲット属性 | 情報源 | 1 回適用 | マージの種類 |
    | --- | --- | --- | --- | --- |
    | 直接 | ユーザータイプ | extensionAttribute1 | 未チェック | 更新 |

    別の例では、他のプロパティから UserType 属性の値を派生させる必要があります。 たとえば、オンプレミスの AD userPrincipalName 属性がドメイン 部分 *@partners.fabrikam123.org* で終わる場合は、すべてのユーザーをゲストとして同期する必要があります。次のような式を実装できます。

    | フローの種類 | ターゲット属性 | 情報源 | 1 回適用 | マージの種類 |
    | --- | --- | --- | --- | --- |
    | 表現 | ユーザータイプ | IIF(IsPresent([userPrincipalName]),IIF(CBool(InStr(LCase([userPrincipalName]),"@partners.fabrikam123.org")=0),"Member","Guest"),Error("UserPrincipalName is not present to determine UserType")) | 未チェック | 更新 |
7. [ **追加]** をクリックして受信規則を作成します。

[Image: 受信方向の同期規則の作成]

#### 手順 5: 属性値を Microsoft Entra ID にフローする送信同期規則を作成する

送信同期規則では、属性値がメタバースから Microsoft Entra ID の UserType 属性にフローすることを許可します。

1. 同期規則エディターに移動します。
2. 検索フィルター **方向** を **外向き**に設定します。
3. [ **新しいルールの追加]** ボタンをクリックします。
4. [**説明**] タブで、次の構成を指定します。

    | 特性 | 価値 | 詳細 |
    | --- | --- | --- |
    | 名前 | *名前* を指定する | たとえば、*Microsoft Entra ID へのアウト – ユーザー利用者タイプ* |
    | 説明 | *説明を入力* |  |
    | 接続システム | *Microsoft Entra コネクタを選択する* |  |
    | 接続システム オブジェクトの種類 | **利用者** |  |
    | メタバース オブジェクト型 | **人物** |  |
    | リンクの種類 | **接続** |  |
    | 優先順位 | *1 ~ 99* の数値を選択します。 | 1 ~ 99 は、カスタム同期規則用に予約されています。 別の同期規則で使用される値は選択しないでください。 |
5. **[スコープ フィルター**] タブに移動し、2 つの句を含む **1 つのスコープ フィルター グループを**追加します。

    | 特性 | オペレーター | 価値 |
    | --- | --- | --- |
    | ソースオブジェクトタイプ | EQUAL | ユーザー |
    | cloudMastered | NOTEQUAL | 正しい |

    スコープ フィルターは、この送信同期規則が適用される Microsoft Entra オブジェクトを決定します。 この例では、 *Out to AD – User Identity* out-of-box 同期規則と同じスコープ フィルターを使用します。 これにより、オンプレミスの Active Directory から同期されていないユーザー オブジェクトに同期規則が適用されなくなります。 Microsoft Entra Connect の展開に応じてスコープ フィルターを調整することが必要になる場合があります。
6. [ **変換** ] タブに移動し、次の変換規則を実装します。

    | フローの種類 | ターゲット属性 | 情報源 | 1 回適用 | マージの種類 |
    | --- | --- | --- | --- | --- |
    | 直接 | ユーザータイプ | ユーザータイプ | 未チェック | 更新 |
7. [ **追加]** をクリックして送信規則を作成します。

[Image: 送信方向の同期規則の作成]

#### 手順 6: 完全同期サイクルを実行する

一般に、Active Directory スキーマと Microsoft Entra Connector スキーマの両方に新しい属性を追加し、カスタム同期規則を導入したため、完全同期サイクルが必要です。 変更を Microsoft Entra ID にエクスポートする前に確認する必要があります。

完全同期サイクルを構成する手順を手動で実行しながら、次の手順を使用して変更を確認できます。

1. **オンプレミス AD コネクタ**で**フル インポート**を実行します。

    1. Synchronization Service Manager の [**コネクタ**] タブに移動します。
    2. **オンプレミスの AD コネクタ**を右クリックし、[**実行**] を選択します。
    3. ポップアップ ダイアログ ボックスで、[ **フル インポート** ] を選択し、[OK] をクリック **します**。
    4. 操作が完了するのを待ちます。

        注

        インポートされた属性の一覧にソース属性が既に含まれている場合は、オンプレミス AD コネクタでの完全なインポートをスキップできます。 つまり、 手順 2: オンプレミス AD コネクタ スキーマにソース属性を追加する際に変更を加える必要はありませんでした。
2. **Microsoft Entra コネクタ**で**完全インポート**を実行します。

    1. **Microsoft Entra コネクタ**を右クリックし、[**実行**] を選択します。
    2. ポップアップ ダイアログ ボックスで、[ **フル インポート** ] を選択し、[OK] をクリック **します**。
    3. 操作が完了するのを待ちます。
3. 既存の User オブジェクトに対する同期規則の変更を確認します。

    オンプレミスの Active Directory のソース属性と Microsoft Entra ID の UserType が、それぞれのコネクタ スペースにインポートされています。 完全同期を続行する前に、オンプレミスの AD コネクタ スペース内の既存のユーザー オブジェクトで **プレビュー** を実行します。 選択したオブジェクトには、ソース属性が設定されている必要があります。

    メタバースに UserType が設定された **正常なプレビュー** は、同期規則が正しく構成されていることを示す適切なインジケーターです。 **プレビュー**を実行する方法については、「変更の確認」セクションを参照してください。
4. **オンプレミスの AD コネクタ**で**完全同期**を実行します。

    1. **オンプレミスの AD コネクタ**を右クリックし、[**実行**] を選択します。
    2. ポップアップ ダイアログ ボックスで、[ **完全同期** ] を選択し、[OK] をクリック **します**。
    3. 操作が完了するのを待ちます。
5. Microsoft Entra ID への**保留中のエクスポート**を確認します。

    1. **Microsoft Entra コネクタ**を右クリックし、[**コネクタ スペースの検索**] を選択します。
    2. [ **コネクタ スペースの検索** ] ポップアップ ダイアログ ボックスで、次の手順を実行します。

        - **[スコープ]** を **[保留中のエクスポート]** に設定します。
        - [ **追加**]、[ **変更**]、[削除] の 3 つのチェック ボックスをすべてオン **にします**。
        - [ **検索** ] ボタンをクリックして、エクスポートする変更を含むオブジェクトの一覧を取得します。 指定したオブジェクトの変更を検証するには、オブジェクトをダブルクリックします。
        - 変更が必要であることを確認します。
6. **Microsoft Entra コネクタ**で**エクスポート**を実行します。

    1. **Microsoft Entra コネクタ**を右クリックし、[**実行**] を選択します。
    2. [ **コネクタの実行** ] ポップアップ ダイアログ ボックスで、[ **エクスポート** ] を選択し、[OK] をクリック **します**。
    3. Microsoft Entra ID へのエクスポートが完了するまで待ちます。

注

これらの手順には、Microsoft Entra Connector の完全な同期とエクスポートの手順は含まれません。 属性値がオンプレミスの Active Directory から Microsoft Entra 専用に流れているため、これらの手順は必要ありません。

#### 手順 7: 同期スケジューラを再度有効にする

組み込みの同期スケジューラを再度有効にします。

1. PowerShell セッションを開始します。
2. コマンドレット `Set-ADSyncScheduler -SyncCycleEnabled $true`を実行して、スケジュールされた同期を再度有効にします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering"} -->
## Microsoft Entra Connect Sync: フィルター処理を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect Sync でフィルター処理を構成する方法を説明します。

フィルター処理を使用することによって、オンプレミスのディレクトリからどのオブジェクトを Microsoft Entra ID に反映するかを制御できます。 既定の構成では、構成されているフォレスト内の全ドメインのほとんどのオブジェクトが対象となります。 通常は、この構成を推奨します。 Microsoft 365 のワークロード (Exchange Online、Skype for Business など) を使っているユーザーには、完全なグローバル アドレス一覧を表示した方が、電子メールの送信先や電話の相手を探すうえで便利です。 既定では、オンプレミス環境の Exchange または Lync と同じ利便性が得られるように構成されています。

注

Microsoft Entra Cloud Sync と Microsoft Entra Connect Sync は、**isCriticalSystemObject** 属性が **True** に設定されている Active Directory オブジェクトをフィルターで除外します。 これにより、Administrator、DomainAdmins、EnterpriseAdmins などの AD 組み込みの高い特権オブジェクトが除外されます。 このフィルター処理は、最後の 2 つのグループは既定により Entra ID と同期**されない**ことを意味します。

ただし、これらの高い特権グループ (DomainAdmins、EnterpriseAdmins) に追加された他のオブジェクトは、クラウドへの同期からフィルターで除外されません。 たとえば、ローカル AD ユーザーを EnterpriseAdmins グループに追加した場合も、そのユーザーは Microsoft Entra ID に同期されます。

ただし、場合によっては、既定の構成を変更する必要があります。 次に例をいくつか示します。

- Azure または Microsoft 365 を試験運用しており、Microsoft Entra ID に存在するユーザーの一部だけが必要な場合。 小規模な試験では、完全なグローバル アドレス一覧がなくても機能を検証することができます。
- Microsoft Entra ID に含める必要のない、個人用以外のアカウント (サービス アカウントなど) が多数存在する。
- コンプライアンス上の理由から、オンプレミスのユーザー アカウントは一切削除できないので 単に無効にします。 一方、Microsoft Entra ID には、アクティブなアカウントだけが必要になります。

この記事では、各種フィルター処理方法の構成について説明します。

重要

公式に文書化されているアクションを除き、Microsoft では Microsoft Entra Connect Sync の変更や操作はサポートされていません。 サポート対象外のアクションを行うと、Microsoft Entra Connect Sync が不整合な状態やサポート対象外の状態になる可能性があります。Microsoft は、そのようなデプロイに対してテクニカル サポートを提供できません。

### 基本事項と注意事項

Microsoft Entra Connect Sync では、いつでもフィルター処理を有効にできます。 最初に既定の構成でディレクトリ同期を実行した後、フィルター処理を構成した場合、フィルター処理により除外されるオブジェクトは、Microsoft Entra ID に対する同期の対象外になります。 この変更により、以前同期されてからフィルター処理された Microsoft Entra ID のオブジェクトは、すべて Microsoft Entra ID から削除されます。

フィルター処理の変更を開始する前に、 組み込みのスケジューラを無効に するか [、サーバーをステージング モードに切り替えて](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server#change-currently-active-sync-server-to-staging-mode)、まだ正しいことを確認していない変更を誤ってエクスポートしないようにしてください。

フィルター処理によって多くのオブジェクトが同時に削除される可能性があります。フィルター処理に変更を加えたら、必ず検証したうえで、Microsoft Entra ID に変更をエクスポートするようにしてください。 構成作業を終えたら、変更内容を Microsoft Entra ID にエクスポートして反映する前に検証作業を実行することを強くお勧めします。

意図せず多数のオブジェクトを削除してしまうことのないよう、"[誤って削除されないように保護する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-prevent-accidental-deletes)" 機能が既定で有効になっています。 フィルター処理で多数のオブジェクトを削除する場合 (既定では 500 個)、この記事の手順に従って、削除処理を Microsoft Entra ID に反映できるようにする必要があります。

November 2015 ([1.0.9125](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history)) より前のビルドを使用し、フィルターの構成を変更して、パスワード ハッシュ同期を使用する場合は、構成を完了した後、すべてのパスワードの完全同期をトリガーする必要があります。 パスワードの完全同期をトリガーする方法の手順については、「[すべてのパスワードの完全同期の開始](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-password-hash-synchronization#trigger-a-full-sync-of-all-passwords)」を参照してください。 ビルド 1.0.9125 以降を使用している場合、パスワードを同期する必要があるかどうかとこの特別な手順が今後必要かどうかの計算も、通常の**完全同期**処理で行われます。

フィルター処理のエラーが原因で**ユーザー** オブジェクトが Microsoft Entra ID から誤って削除された場合、フィルター処理構成を削除することで Microsoft Entra ID 内にユーザー オブジェクトを再作成できます。 その後、ディレクトリの同期を再実行できます。 この操作により、Microsoft Entra ID 内のごみ箱からユーザーが復元されます。 ただし、その他の種類のオブジェクトについては削除を取り消すことができません。 たとえば、リソースの ACL に使用されていたセキュリティ グループをうっかり削除すると、そのグループおよび対応する ACL は復元できなくなります。

削除されるのは、Microsoft Entra Connect の作用対象 (スコープ) として認識されていたオブジェクトだけです。 別の同期エンジンによって作成されたオブジェクトが Microsoft Entra ID 内に存在していても、それらがスコープ内に存在しない場合、フィルター処理を追加しても、それらのオブジェクトは削除されません。 たとえば、Microsoft Entra ID でディレクトリ全体の完全なコピーを作成した Microsoft Entra クラウド同期サーバーから開始し、最初からフィルター処理を有効にして新しい Microsoft Entra Connect Sync サーバーを並行してインストールした場合、クラウド同期によって作成された追加のオブジェクトは削除されません。

新しいバージョンの Microsoft Entra Connect のインストールまたはアップグレードを行ってもフィルター処理の構成は保持されます。 新しいバージョンにアップグレードした後は、常に構成が誤って変更されていないことを確認してから、最初の同期サイクルを実行することをお勧めします。

複数のフォレストが存在する場合、このトピックで説明するフィルター処理構成をすべてのフォレストに適用する必要があります (すべてのフォレストに同じ構成を適用する場合)。

#### 同期スケジューラを無効にする

同期サイクルを 30 分おきにトリガーする、組み込みのスケジューラを無効にするには、以下の手順に従います。

1. Windows Powershell を開き、ADSync モジュールをインポートし、次のコマンドを使用してスケジューラを無効にします。

```Powershell
Import-Module ADSync
Set-ADSyncScheduler -SyncCycleEnabled $False
```

1. スコープ フィルターを変更し、この記事に記載されているように結果を確認します。
2. 準備ができたら、次のコマンドを使用して同期スケジューラを再度有効にします。

```Powershell
Set-ADSyncScheduler -SyncCycleEnabled $True
```

### フィルター処理オプション

ディレクトリ同期ツールには、次のフィルター処理構成タイプを適用できます。

- **グループ ベース**: 単一のグループに基づくフィルター処理は、インストール ウィザードを使用して、初回インストール時にのみ構成できます。
- **ドメインベース**: このオプションを使用すると、どのドメインを Microsoft Entra ID に同期させるかを選択できます。 また、Microsoft Entra Connect Sync をインストールした後にオンプレミス インフラストラクチャに変更を加えた場合、同期エンジンの構成に対してドメインを追加または削除することができます。
- **組織単位 (OU) ベース**: このオプションを使用すると、どの OU を Microsoft Entra ID に同期させるかを選択できます。 選択された OU 内のすべてのオブジェクト タイプが対象となります。
- **属性ベース**: このオプションを使用すると、オブジェクトの属性値に基づいてオブジェクトをフィルター処理できます。 オブジェクトの種類ごとに異なるフィルターを使用することもできます。

フィルター処理のオプションは、同時に複数使用することができます。 たとえば、1 つの OU 内のオブジェクトのみを含めるために OU ベース フィルター処理を使用できます。 同時に、オブジェクトをさらにフィルター処理するために属性ベースのフィルター処理を使用できます。 複数のフィルター処理方法を使用した場合、それらのフィルターが "論理積" で組み合わされます。

### ドメイン ベースのフィルター処理

このセクションでは、ドメイン フィルターを構成する手順について説明します。 Microsoft Entra Connect をインストールした後にフォレスト内のドメインを追加または削除した場合は、フィルター処理の構成も更新する必要があります。

ドメイン ベースのフィルター処理を変更するには、インストール ウィザード ([ドメインと OU のフィルタリング) を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#domain-and-ou-filtering)実行します。 インストール ウィザードは、このトピックに記載されているすべてのタスクを自動化します。

### 組織単位ベースのフィルター処理

OU ベースのフィルター処理を変更するには、インストール ウィザード ([ドメインと OU のフィルタリング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#domain-and-ou-filtering)) を実行します。 インストール ウィザードは、このトピックに記載されているすべてのタスクを自動化します。

重要

同期対象の OU を明示的に選択した場合、Microsoft Entra Connect では、その OU の DistinguishedName がドメインの同期スコープの包含リストに追加されます。 ただし、後で Active Directory でその OU の名前を変更すると、OU の DistinguishedName が変更されるため、Microsoft Entra Connect ではその OU が同期スコープ内にあるとは見なされなくなります。 これによりすぐに問題が発生することはありませんが、完全なインポート ステップの際に、Microsoft Entra Connect で同期スコープが再評価され、同期スコープ外のオブジェクトが削除 (廃止) されます。これにより、Microsoft Entra ID 内のオブジェクトが予期せず大量に削除される可能性があります。 この問題を回避するには、OU の名前を変更した後、Microsoft Entra Connect ウィザードを実行し、OU を再度選択してもう一度同期スコープに含めます。

### 属性ベースのフィルター処理

以下の手順は、November 2015 ([1.0.9125](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history)) 以降のビルドを想定しています。

重要

**Microsoft Entra Connect** によって作成された既定の規則は、変更しないことをお勧めします。 規則を変更する場合は、複製してから、元の規則を無効にします。 複製した規則を変更してください。 これによって (元の規則を無効にすることによって)、その規則によって有効にしたバグ修正や機能は見つからなくなります。

属性ベースのフィルター処理は、オブジェクトをフィルター処理する手段として最も柔軟性の高い方法となります。 [宣言型のプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-declarative-provisioning)の強みを活かして、Microsoft Entra ID に対してオブジェクトが同期されるタイミングをほぼすべての面から制御することができます。

Active Directory からメタバースへの受信フィルター処理と、メタバースから Microsoft Entra ID への送信フィルター処理を適用できます。 維持が最も簡単な受信フィルター処理を適用することをお勧めします。 送信側のフィルター処理は、複数のフォレストからのオブジェクトを合わせたうえで評価する必要がある場合にのみ使用してください。

#### 受信のフィルター処理

受信フィルター処理は既定の構成を使用します。既定の構成では、Microsoft Entra ID に送信されるオブジェクトを同期させるためには、メタバース属性 cloudFiltered の値が未設定となっている必要があります。 この属性の値が **True** に設定されている場合、オブジェクトは同期されません。 意図的に **False** に設定することは避けてください。 他の規則から確実に値が得られるよう、この属性の値は **True** または **NULL** (未設定) にする必要があります。

Microsoft Entra Connect は Microsoft Entra ID でプロビジョニングするオブジェクトをクリーンアップするように設計されていることに注意してください。 システムが過去に Microsoft Entra ID でオブジェクトをプロビジョニングしたことがなく、インポート手順で Microsoft Entra ID オブジェクトを取得した場合、このオブジェクトは他のシステムによって Microsoft Entra ID で作成されたと正しく想定されます。 メタバース属性 `cloudFiltered` が **True** に設定されている場合でも、Microsoft Entra Connect は、これらの種類の Microsoft Entra オブジェクトをクリーンアップしません。

受信フィルター処理では、同期の対象となるオブジェクトと対象外となるオブジェクトが**スコープ**の作用によって決定されます。 この点は、組織ごとに実際の要件に合わせて調整することになります。 スコープ モジュールには、同期規則の作用対象を判断する**グループ**と**句**があります。 グループは、1 つまたは複数の句を含みます。 複数の句は "論理積" で組み合わされ、複数のグループは "論理和" で組み合わされます。

たとえば、次のようなフィルターがあるとします。[Image: スコープ フィルターを追加する例を示すスクリーンショット。] これは、 **(department = IT) OR (department = Sales AND c = US)** という意味になります。

次のサンプルと手順は、ユーザー オブジェクトが例として使用されていますが、実際にはオブジェクトの種類に関係なく利用できます。

次の例では、優先順位の値は 50 から始まります。 これは既に使用されていない任意の数値であればよいですが、100 よりも小さい必要があります。

##### 否定のフィルター処理 (同期対象外の指定)

以下の例では、**extensionAttribute15** の値が **NoSync** であるすべてのユーザーをフィルターで除外 (同期対象外に) します。

1. **ADSyncAdmins** セキュリティ グループに属するアカウントを使用して、Microsoft Entra Connect Sync を実行しているサーバーにサインインします。
2. **[スタート]** メニューから、**同期規則エディター**を起動します。
3. **[受信]** が選択されていることを確認し、 **[新しい規則の追加]** をクリックします。
4. わかりやすい名前を規則に付けます ("*AD からの受信 – 同期しないユーザーのフィルター*" など)。 適切なフォレストを選択し、 **[接続されているシステム オブジェクトのタイプ]** として **[ユーザー]** を、 **[メタバース オブジェクトの種類]** として **[人]** を選択します。 **[リンクの種類]** で **[結合]** を選択します。 **[優先順位]** に、別の同期規則で現在使用されていない値 (50 など) を入力して、 **[次へ]** をクリックします。[Image: Inbound 1 の説明]
5. **[スコープ フィルター]** で、 **[グループの追加]** 、 **[句の追加]** の順にクリックします。 **[属性]** で **[ExtensionAttribute15]** を選択します。 **[演算子]** を **EQUAL** に設定し、 **[値]** ボックスに「**NoSync**」という値を入力します。 **[次へ]** をクリックします。[Image: Inbound 2 のスコープ]
6. **[参加]** 規則は空のままにして、 **[次へ]** をクリックします。
7. **[変換の追加]** をクリックし、 **[FlowType]** として **[Constant]** を、 **[ターゲット属性]** として **[cloudFiltered]** に選択します。 **[ソース]** テキスト ボックスに「**True**」を入力します。 **[追加]** をクリックして規則を保存します。[Image: Inbound 3 の変換]
8. 構成を完了するには、**完全同期**を実行する必要があります。続きは「変更の適用と検証」セクションを参照してください。

##### 肯定のフィルター処理 (同期対象の指定)

肯定のフィルター処理を式で表すことは、否定の場合と比べて難易度が上がります。同期の対象とするかどうかが不明確なオブジェクト (会議室など) も考慮しなければならないためです。 既定の規則 **[In from AD - User Join]** の既定のフィルターもオーバーライドします。 カスタム フィルターを作成するときは、重要なシステム オブジェクト、レプリケーション競合オブジェクト、特殊なメールボックス、Microsoft Entra Connect のサービス アカウントなどが含まれていないことを確認してください。

肯定のフィルター処理オプションには、2 つの同期規則が必要です。1 つ (またはそれ以上) は、同期対象のオブジェクトのスコープを厳密に指定した同期規則、もう 1 つは、同期対象でない残りのすべてのオブジェクトを除外する包括的な同期規則です。

以下の例では、department 属性の値が **Sales**であるユーザー オブジェクトだけを同期対象としています。

1. **ADSyncAdmins** セキュリティ グループに属するアカウントを使用して、Microsoft Entra Connect Sync を実行しているサーバーにサインインします。
2. **[スタート]** メニューから、**同期規則エディター**を起動します。
3. **[受信]** が選択されていることを確認し、 **[新しい規則の追加]** をクリックします。
4. わかりやすい名前を規則に付けます ("*AD からの受信 – 営業部のユーザーの同期*" など)。 適切なフォレストを選択し、 **[接続されているシステム オブジェクトのタイプ]** として **[ユーザー]** を、 **[メタバース オブジェクトの種類]** として **[人]** を選択します。 **[リンクの種類]** で **[結合]** を選択します。 **[優先順位]** に、別の同期規則で現在使用されていない値 (51 など) を入力して、 **[次へ]** をクリックします。[Image: Inbound 4 の説明]
5. **[スコープ フィルター]** で、 **[グループの追加]** 、 **[句の追加]** の順にクリックします。 **[属性]** で **[department]** を選択します。 [演算子] を **[EQUAL]** に設定し、 **[値]** ボックスに「**Sales**」という値を入力します。 **[次へ]** をクリックします。[Image: Inbound 5 のスコープ]
6. **[参加]** 規則は空のままにして、 **[次へ]** をクリックします。
7. **[変換の追加]** をクリックし、 **[FlowType]** として **[Constant]** を、 **[ターゲット属性]** として **[cloudFiltered]** に選択します。 **[ソース]** ボックスに、「**False**」を入力します。 **[追加]** をクリックして規則を保存します。[Image: Inbound 6 の変換] これは特殊なケースですが、cloudFiltered を明示的に **False** に設定します。
8. 次に、包括的な同期規則を作成する必要があります。 わかりやすい名前を規則に付けます ("*AD からの受信 – 包括的なユーザーのフィルター*" など)。 適切なフォレストを選択し、 **[接続されているシステム オブジェクトのタイプ]** として **[ユーザー]** を、 **[メタバース オブジェクトの種類]** として **[人]** を選択します。 **[リンクの種類]** で **[結合]** を選択します。 **[優先順位]** に、別の同期規則で現在使用されていない値 (99 など) を入力します。 前の同期規則より高い (優先順位の低い) 値を選択しました。 ただし、後で追加の部門の同期を開始した場合にフィルター処理の同期規則を追加できるように、多少の余地も残しています。 **[次へ]** をクリックします。[Image: Inbound 7 の説明]
9. **[スコープ フィルター]** は空のままにして、 **[次へ]** をクリックします。 フィルターを空にした場合、すべてのオブジェクトに規則が適用されます。
10. **[参加]** 規則は空のままにして、 **[次へ]** をクリックします。
11. **[変換の追加]** をクリックし、 **[FlowType]** として **[Constant]** を、 **[ターゲット属性]** として **[cloudFiltered]** に選択します。 **[ソース]** ボックスに、「**True**」を入力します。 **[追加]** をクリックして規則を保存します。[Image: Inbound 3 の変換]
12. 構成を完了するには、**完全同期**を実行する必要があります。続きは「変更の適用と検証」セクションを参照してください。

必要であれば、1 つ目のタイプの規則をさらに作成し、同期対象のオブジェクトを増やすこともできます。

#### 送信のフィルター処理

場合によっては、オブジェクトがメタバースに参加した後にのみ、フィルター処理を実行する必要があります。 たとえば、オブジェクトを同期する必要があるかどうかを確認するには、リソース フォレストの mail 属性と、アカウント フォレストの userPrincipalName 属性を確認する必要があります。 このような場合、送信ルールに関するフィルター処理を作成します。

この例では、mail と userPrincipalName の両方の末尾が @contoso.com であるユーザーのみが同期されるようにフィルター処理を変更します。

1. **ADSyncAdmins** セキュリティ グループに属するアカウントを使用して、Microsoft Entra Connect Sync を実行しているサーバーにサインインします。
2. **[スタート]** メニューから、**同期規則エディター**を起動します。
3. **[規則の種類]** の **[送信]** をクリックします。
4. 使用する Connect のバージョンに応じて、**Out to Microsoft Entra ID – User Join** と **Out to Microsoft Entra ID - User Join SOAInAD** のいずれかの規則を選択し、**[編集]** をクリックします。
5. ポップアップで **[はい]** を選択して規則のコピーを作成します。
6. **[説明]** ページの **[優先順位]** の値を、まだ使用していない値 (50 など) に設定します。
7. 左側のナビゲーションにある **[スコープ フィルター]** をクリックし、 **[句の追加]** をクリックします。 **[属性]** で **[mail]** を選択します。 **[演算子]** で **[ENDSWITH]** を選択します。 **[値]** に「**contoso.com**」と入力し、**[句の追加]** をクリックします。 **[属性]** で **[userPrincipalName]** を選択します。 **[演算子]** で **[ENDSWITH]** を選択します。 **[値]** に「**contoso.com**」と入力します。
8. **[保存]** をクリックします。
9. 構成を完了するには、**完全同期**を実行する必要があります。続きは「変更の適用と検証」セクションを参照してください。

### 変更の適用と検証

構成を変更したら、それらの変更をシステム内の既存のオブジェクトに適用する必要があります。 まだ同期エンジンに存在しないオブジェクトを処理 (さらに、同期エンジンがソース システムをもう一度読み取ってその内容を検証) しなければならないケースも考えられます。

**ドメイン**または**組織単位**のフィルター処理を使用して構成を変更した場合は、**完全インポート**の後に**差分同期**を実行する必要があります。

**属性**フィルターを使用して構成を変更した場合は、**完全同期**を実行する必要があります。

ベスト プラクティスとして、サーバーが [ステージング モード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server#change-currently-active-sync-server-to-staging-mode)であることを確認し、 **PowerShell** コマンド `Start-ADSyncSyncCycle -PolicyType Initial`を使用して、すべてのコネクタで完全なインポートと完全同期を実行する初期同期サイクルを開始します。

実行プロファイルを手動で開始するには、次の手順を実行します。

1. **[スタート]** メニューから **[同期サービス]** を起動します。
2. **[コネクタ]** を選択します。 **[コネクタ]** の一覧から、構成変更済みのコネクタを選択します。 **[アクション]** から **[実行]** を選択します。[Image: コネクタの実行]
3. **[実行プロファイル]** から、前のセクションで説明した操作を選択します。 2 つのアクションを実行する必要がある場合は、1 つ目が終了した後 (選択したコネクタの **[状態]** 列が **[Idle]** になっている) に 2 つ目を実行します。

同期後、すべての変更がエクスポートの対象としてステージングされます。 実際に Microsoft Entra ID に変更を加える前に、それらの変更がすべて正しいことを検証する必要があります。

1. コマンド プロンプトを起動し、`%ProgramFiles%\Microsoft Azure AD Sync\bin` に移動します。
2. `csexport "Name of Connector" %temp%\export.xml /f:x` を実行します。 同期サービスにコネクタの名前があることを確認できます。 Microsoft Entra ID に "contoso.com – Microsoft Entra ID" に似た名前が付けられます。
3. `CSExportAnalyzer %temp%\export.xml > %temp%\export.csv` を実行します。
4. %temp% に export.csv という名前のファイルが生成されます。このファイルは、Microsoft Excel で開くことができます。 このファイルには、エクスポートの対象となるすべての変更が含まれています。
5. データまたは構成に必要な変更を加え、エクスポートの対象となる変更が希望どおりになるまで、(インポート、同期、検証の) 手順を実行します。

問題がなければ、変更を Microsoft Entra ID にエクスポートします。

1. **[コネクタ]** を選択します。 **[コネクタ]** の一覧から Microsoft Entra コネクタを選択します。 **[アクション]** から **[実行]** を選択します。
2. **[実行プロファイル]** で **[エクスポート]** を選択します。
3. 構成の変更によって削除されるオブジェクトが多数存在し、その数が、構成されているしきい値 (既定では 500 個) を超えた場合、エクスポート時にエラーが表示されます。 エラーが表示された場合は、"[誤って削除されないように保護する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-prevent-accidental-deletes)" 機能を一時的に無効にする必要があります。

次に、 同期スケジューラを再度有効にします。

### グループベースのフィルター処理

グループベースのフィルター処理は、[カスタム インストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#sync-filtering-based-on-groups)を使用して Microsoft Entra Connect を初めてインストールするときに構成できます。 このフィルター処理は、同期が必要なオブジェクトがごく少数であるパイロット デプロイで使用するためのものです。 グループベースのフィルター処理は、一度無効にすると再び有効にすることができません。 カスタム構成でのグループベースのフィルター処理は、*サポートされていません*。 この機能を構成できるのは、インストール ウィザードを使用する場合のみです。 パイロットが完了したら、このトピックで説明されているいずれかの他のフィルター処理オプションを使用する必要があります。 OU ベースのフィルター処理とグループ ベースのフィルター処理を組み合わせて使用する場合は、グループとそのメンバーが配置されている OU を追加する必要があります。

複数の AD フォレストを同期すると、各 AD コネクタに別のグループを指定することで、グループベースのフィルター処理を構成できます。 1 つの AD フォレストでユーザーを同期する場合、同じユーザーが他の AD フォレストで 1 つ以上の対応するオブジェクトを持つとき、ユーザー オブジェクトとそれに対応するすべてのオブジェクトがグループ ベースのフィルター処理のスコープ内にあることを確認してください。 次に例を示します。

- あるフォレストにユーザーがいます。このフォレストには、他のフォレストの対応する FSP (外部セキュリティ プリンシパル) オブジェクトがあります。 両方のオブジェクトが、グループ ベースのフィルター処理のスコープ内にある必要があります。 そうしないと、ユーザーは Microsoft Entra ID と同期されません。
- あるフォレストにユーザーがいます。このフォレストには、他のフォレストの対応するリソース アカウント (リンクされたメールボックスなど) があります。 また、そのユーザーとリソース アカウントをリンクするように Microsoft Entra Connect を構成しました。 両方のオブジェクトが、グループ ベースのフィルター処理のスコープ内にある必要があります。 そうしないと、ユーザーは Microsoft Entra ID と同期されません。
- あるフォレストにユーザーがいます。このフォレストには、他のフォレストの対応するメール連絡先があります。 また、そのユーザーとメール連絡先をリンクするように Microsoft Entra Connect を構成しました。 両方のオブジェクトが、グループ ベースのフィルター処理のスコープ内にある必要があります。 そうしないと、ユーザーは Microsoft Entra ID と同期されません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-endpoint-api-v2"} -->
## Microsoft Entra Connect Sync V2 エンドポイント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-endpoint-api-v2
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このドキュメントでは、Microsoft Entra Connect Sync v2 エンドポイント API の更新について説明します。

Microsoft は、同期サービス操作のパフォーマンスを Microsoft Entra ID に向上させる、Microsoft Entra Connect 用の新しいエンドポイント (API) をデプロイしました。 新しい V2 エンドポイントを使用すると、Microsoft Entra ID へのエクスポートとインポートでパフォーマンスが大幅に向上します。 この新しいエンドポイントでは、次の機能がサポートされます。

- 最大 250,000 人のメンバーとグループを同期する。
- Microsoft Entra ID へのエクスポートとインポートのパフォーマンス向上。

手記

現在、新しいエンドポイントには、書き戻される Microsoft 365 グループのグループ サイズ制限が構成されていません。 これは、Active Directory と同期サイクルの待機時間に影響する可能性があります。 グループ のサイズを段階的に増やすことをお勧めします。

手記

Microsoft Entra Connect Sync V2 エンドポイント API は一般提供されていますが、現時点では次の Azure 環境でのみ使用できます。

- Azure コマーシャル
- 21Vianet クラウドが運用する Microsoft Azure
- Azure US Government クラウド Azure German クラウドでは使用できません

### 前提 条件

新しい V2 エンドポイントを使用するには、Microsoft Entra Connect V2.0 を使用する必要があります。 Microsoft Entra Connect V2.0 をデプロイすると、V2 エンドポイントが自動的に有効になります。 最新の V1.6 ビルドにアップグレードすると、グループ メンバーシップの制限が 50,000 にリセットされるという既知の問題があります。 サーバーが Azure AD Connect V1.6 にアップグレードされると、お客様は最初に適用した規則の変更を再適用して、グループ メンバーシップの制限を 250,000 に増やす必要があります。 これは、サーバーの同期を有効にする前に行う必要があります。

### よく寄せられる質問

**新しいエンドポイントがアップグレードと新規インストールの既定になるのはいつですか?** V2 エンドポイントは Microsoft Entra Connect V2.0 の既定の設定です。このエンドポイントの利点を使用するには、Microsoft Entra Connect V2.0 にアップグレードすることをお勧めします。 以前のバージョンで V2 エンドポイントを実行しているお客様には問題があります。 新しい V1.6 リリースにアップグレードしようとすると、グループ メンバーシップに対する 50 K の制限が再開されます。 サーバーが Azure AD Connect V1.6 にアップグレードされると、お客様は最初に適用した規則の変更を再適用して、グループ メンバーシップの制限を 250,000 に増やす必要があります。 これは、サーバーの同期を有効にする前に行う必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions"} -->
## Microsoft Entra Connect Sync: ディレクトリ拡張機能 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、Microsoft Entra Connect のディレクトリ拡張機能について説明します。

ディレクトリ拡張機能を使用すると、オンプレミスの Active Directory から独自の属性を使用して、Microsoft Entra ID のスキーマを拡張できます。 この機能により、オンプレミスで引き続き管理する属性を使用して LOB アプリを構築できます。 これらの属性は、[拡張機能](https://learn.microsoft.com/ja-jp/graph/extensibility-overview)から使用できます。 使用可能な属性は、 [Microsoft Graph Explorer](https://developer.microsoft.com/graph/graph-explorer)、 [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) 、または [Microsoft Entra PowerShell](https://learn.microsoft.com/ja-jp/powershell/entra-powershell/overview) を使用して確認できます。 現時点では、これらの属性を使用する Microsoft 365 ワークロードはありませんが、Microsoft Entra ID の動的グループ メンバーシップでこの機能を使用できます。

### Microsoft Entra ID と同期する属性を選択する

カスタム設定で、Microsoft Entra Connect 構成ウィザードを使用して、同期する拡張属性を構成します。

[Image: スキーマ拡張機能のウィザード]

ウィザードには、ディレクトリ拡張機能で使用する有効な候補である属性が表示されます。

- ユーザーおよびグループ オブジェクト型
- 単一値の属性: 文字列、ブール値、整数、バイナリ
- 複数値の属性: 文字列、バイナリ

### ディレクトリ拡張機能を使用する場合の重要な考慮事項

- 属性の一覧は、Microsoft Entra Connect の初期インストール時に Active Directory スキーマから読み取られます。 より多くのカスタム属性を使用して Active Directory スキーマを拡張する場合は、これらの新しい属性を表示する前に [スキーマを更新](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-installation-wizard#refresh-directory-schema) する必要があります。
- ディレクトリ拡張属性の同期に使用するカスタム 規則を含む構成をエクスポートし、この規則を Microsoft Entra Connect の新規または既存のインストールにインポートしようとすると、インポート中にルールが作成されますが、ディレクトリ拡張機能の属性はマップされません。 ディレクトリ拡張機能の属性を再選択し、それらをルールに再関連付けるか、ルール全体を再作成してこれを修正する必要があります。
- Microsoft Entra ID のすべての機能が、複数値の拡張属性をサポートしているわけではありません。 これらの属性を使用してサポートされていることを確認する予定の機能のドキュメントを参照してください。
- Microsoft Entra ID のオブジェクトには、最大 100 個のディレクトリ拡張属性値を指定できます。 複数値属性の場合、個々の値はこの 100 値の制限にカウントされます。
- 文字列属性値の最大長は 256 文字です。 属性値がこの制限を超えると、同期エンジンによって値が切り捨てられます。
- msDS-UserPasswordExpiryTimeComputed などの構築された属性の同期はサポートされていません。 古いバージョンの Microsoft Entra Connect からアップグレードしても、これらの属性がインストール ウィザードに表示される場合があります。その値は Microsoft Entra ID と同期されないため、有効にしないでください。 [学習モード](https://learn.microsoft.com/ja-jp/openspecs/windows_protocols/ms-adts/a3aff238-5f0e-4eec-8598-0a59c30ecd56)。
- badPwdCount、Last-Logon、Last-Logoff などのレプリケートされていない属性は、値が Microsoft Entra ID と同期されないため、同期はサポートされていません。
- Microsoft Entra Connect ウィザードの外部でオンプレミスディレクトリ拡張機能を管理することはサポートされていません。 ディレクトリ拡張機能の同期規則を手動で編集または複製すると、同期の問題が発生する可能性があります。
- Microsoft Entra Connect の属性値を、Microsoft Entra Connect によって作成されていない拡張属性と同期することはサポートされていません。 これを行うと、パフォーマンスの問題が発生し、予期しない結果が生じる可能性があります。

### ウィザードによって行われた Microsoft Entra ID の構成変更

Microsoft Entra Connect のインストール中に、これらの属性が構成されている場所にアプリケーションが登録されます。 このアプリケーションは、[テナント スキーマ拡張アプリ](https://entra.microsoft.com)という名前の **Microsoft Entra 管理センター**で確認できます。 このアプリを表示するには、[ **すべてのアプリケーション** ] を選択してください。

[Image: スキーマ拡張機能アプリ]

注意

**テナント スキーマ拡張アプリ**は、削除できないシステム専用アプリケーションです。 **テナント スキーマ拡張アプリ**に関連付けられているサービス プリンシパルを削除すると、同期が中断されます。 ディレクトリ拡張機能の同期を回復するには、論理的に削除されたサービス プリンシパルを復元するか、新しいサービス プリンシパルを再作成します。

### Microsoft Entra ID での拡張属性の表示

拡張属性の形式は `extension_{ApplicationId}_<attributeName>`。ApplicationId は *テナント スキーマ拡張アプリ*のアプリケーション識別子です。 この値は、このトピックの他のすべてのシナリオで必要です。

#### Microsoft Graph API を使用する

これらの属性は、Microsoft Graph エクスプローラーを使用して、 [Microsoft Graph](https://developer.microsoft.com/graph/graph-explorer#) API を使用して使用できます。

Microsoft Graph API で、属性が返されるように要求する必要があります。 次のような属性を明示的に選択します。

```
https://graph.microsoft.com/beta/users/abbie.spencer@fabrikamonline.com?$select=extension_9d98ed114c4840d298fad781915f27e4_employeeID,extension_9d98ed114c4840d298fad781915f27e4_division
```

詳細については、[Microsoft Graph: クエリ パラメーターの使用](https://learn.microsoft.com/ja-jp/graph/query-parameters#select-parameter)に関するトピックをご覧ください。

#### Microsoft Graph PowerShell SDK の使用

1. **テナント スキーマ拡張機能アプリ** アプリケーションを取得します。

```powershell
Get-MgApplication -Filter "DisplayName eq 'Tenant Schema Extension App'"
```

1. **テナント スキーマ拡張機能アプリ**のすべての拡張機能属性を一覧表示します。

```powershell
Get-MgDirectoryObjectAvailableExtensionProperty
```

1. ユーザー オブジェクトのすべての拡張属性を一覧表示します。

```powershell
(Get-MgBetaUser -UserId "<Id or UserPrincipalName>").AdditionalProperties

```

#### Microsoft Entra PowerShell の使用

1. **テナント スキーマ拡張アプリ アプリケーション**識別子を取得します。

```powershell
Get-EntraApplication -SearchString "Tenant Schema Extension App"
```

1. **テナント スキーマ拡張機能アプリ** アプリケーションのすべての拡張機能属性を一覧表示します。

```powershell
Get-EntraExtensionProperty | Where-Object {$_.AppDisplayName -eq 'Tenant Schema Extension App'}
```

1. ユーザー オブジェクトのすべての拡張属性を一覧表示します。

```powershell
Get-EntraUserExtension -UserId "<Id or UserPrincipalName>"
```

### 動的メンバーシップ グループで属性を使用する

最も便利なシナリオの 1 つは、動的セキュリティまたは Microsoft 365 グループで拡張属性を使用することです。

1. Microsoft Entra ID で新しいグループを作成します。 名前を付け、 **メンバーシップの種類** が **動的ユーザー**であることを確認します。

    [Image: 新しいグループを含むスクリーンショット]
2. **[動的クエリの追加]** を選択します。 プロパティを見ると、最初に追加する必要があるため、これらの拡張属性は見つかりません。 **[カスタム拡張機能のプロパティを取得します]** をクリックし、アプリケーション ID を入力して、 **[プロパティの更新]** をクリックします。

    [Image: ディレクトリ拡張が追加された画面のスクリーンショット]
3. プロパティのドロップダウンを開くと、今度は、追加した属性が表示されていることがわかります。

    [Image: UI に表示されるようになった新しい属性を含むスクリーンショット]
4. 実際の要件に合わせるには、式を完成させます。 この例では、ルールは次の値に設定されています。

    `(user.extension_9d98ed114c4840d298fad781915f27e4_division -eq "Sales and marketing")`
5. グループが作成されたら、Microsoft Entra にメンバーを設定してからメンバーを確認する時間を与えます。

    [Image: 動的グループ内のメンバーを含むスクリーンショット]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-feature-preferreddatalocation"} -->
## Microsoft Entra Connect: Microsoft 365 リソースの優先データの場所を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-preferreddatalocation
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect Sync を使用して、Microsoft 365 ユーザー リソースをユーザーの近くに配置する方法について説明します。

この記事の目的は、Microsoft Entra Connect Sync で優先されるデータの場所に対して属性を構成する方法について説明することです。Microsoft 365 で複数地域機能を使用しているユーザーは、この属性を使用して、ユーザーの Microsoft 365 データの地理的位置を指定します。 (リージョン  と *geo* の用語は同じ意味で使用されます)。

### サポートされている複数地域の場所

Microsoft Entra Connect でサポートされているすべての地域の一覧については、Microsoft 365 Multi-Geo の可用性について、 を参照してください。

### 優先データの場所の同期を有効にする

既定では、ユーザーの Microsoft 365 リソースは、Microsoft Entra テナントと同じ geo に配置されます。 たとえば、*テナント* が北米にある場合、ユーザーの Exchange メールボックスも北米に配置されます。 多国籍組織の場合、これは最適ではない可能性があります。

preferredDataLocation属性を設定することで、ユーザーの geo を定義できます。 メールボックスや OneDrive などのユーザーの Microsoft 365 リソースをユーザーと同じ地域に配置し、組織全体に対して 1 つのテナントを持つことができます。

重要

2023年6月1日より、Multi-GeoがCSPパートナー向けに利用可能となり、顧客の合計Microsoft 365サブスクリプションライセンスの少なくとも5%の購入が必要です。

Multi-Geo は、アクティブなエンタープライズ契約をお持ちのお客様も利用できます。 詳細については、Microsoft の担当者にお問い合わせください。

Microsoft Entra Connect でサポートされているすべての geo の一覧については、Microsoft 365 Multi-Geo 可用性を参照してください。

#### Microsoft Entra Connect による同期のサポート

Microsoft Entra Connect では、バージョン 1.1.524.0 以降の **ユーザー** オブジェクトの **preferredDataLocation** 属性の同期がサポートされています。 具体的には：

- Microsoft Entra Connector のオブジェクト型 **ユーザー** のスキーマが拡張されて、属性 **preferredDataLocation** が含まれます。 属性は、単一値の文字列型です。
- メタバースのオブジェクト型「Person」のスキーマが拡張され、属性「preferredDataLocation」が含まれるようになりました。 属性は、単一値の文字列型です。

既定では、preferredDataLocation同期は有効になっていません。 この機能は、大規模な組織を対象としています。 Windows Server 2019 の Active Directory スキーマには、この目的で使用する必要がある msDS-preferredDataLocation属性があります。 Active Directory スキーマを更新していない場合は、ユーザーの Microsoft 365 geo を保持する属性を特定する必要があります。 これは組織ごとに異なります。

重要

Microsoft Entra ID では、**クラウドのユーザー オブジェクト**の **preferredDataLocation** 属性を、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) を使用して直接構成できます。 同期されたユーザー オブジェクト に対してこの属性を構成するには、Microsoft Entra Connect を使用する必要があります。

同期を有効にする前に、

- Active Directory スキーマを 2019 にアップグレードしていない場合は、ソース属性として使用するオンプレミスの Active Directory 属性を決定します。 それは、**型の単一値の文字列**である必要があります。
- Microsoft Graph PowerShell を使用して Microsoft Entra ID で 既存の 同期ユーザー オブジェクトに対して preferredDataLocation 属性を以前に構成した場合は、その属性値をオンプレミスの Active Directory 内の対応する User オブジェクトにバックポートする必要があります。

    重要

    これらの値をバックポートしない場合、**preferredDataLocation** 属性の同期が有効になっている場合、Microsoft Entra Connect は Microsoft Entra ID の既存の属性値を削除します。
- 現在、少なくとも 2 つのオンプレミス Active Directory ユーザー オブジェクトにソース属性を構成します。 これは後で検証に使用できます。

以降のセクションでは、**preferredDataLocation** 属性の同期を有効にする手順について説明します。

手記

この手順は、単一フォレスト トポロジとカスタム同期規則を使用しない Microsoft Entra 展開のコンテキストで説明されています。 マルチフォレスト トポロジ、カスタム同期規則が構成されている場合、またはステージング サーバーがある場合は、それに応じて手順を調整する必要があります。

### 手順 1: 同期スケジューラを無効にし、進行中の同期がないことを確認する

意図しない変更が Microsoft Entra ID にエクスポートされないようにするには、同期規則の更新中に同期が行われないようにします。 組み込みの同期スケジューラを無効にするには:

1. Microsoft Entra Connect サーバーで PowerShell セッションを開始します。
2. スケジュールされた同期を無効にするには、次のコマンドレットを実行します: `Set-ADSyncScheduler -SyncCycleEnabled $false`。
3. **Synchronization Service Manager** を起動するには、**スタート**&gt;**同期サービス**を選択します。
4. **[操作]** タブを選択し、状態が "*進行中*" になっている操作がないことを確認します。

Synchronization Service Manager のスクリーンショット

### 手順 2: Active Directory のスキーマを更新する

Active Directory スキーマを 2019 に更新し、スキーマ拡張機能の前に Connect がインストールされている場合、Connect スキーマ キャッシュには更新されたスキーマがありません。 その後、UI に表示されるように、ウィザードからスキーマを更新する必要があります。

1. デスクトップから Microsoft Entra Connect ウィザードを起動します。
2. [ディレクトリ スキーマ の更新] オプション 選択し、[次 ] を選択します。
3. Microsoft Entra の資格情報を入力し、[**次へ**] を選択します。
4. [ディレクトリ スキーマの更新] ページで、すべてのフォレストが選択されていることを確認し、[次へ] を選択します。
5. 完了したら、ウィザードを閉じます。

[Image: 接続ウィザードの [ディレクトリ スキーマの更新]] のスクリーンショット

### 手順 3: オンプレミスの Active Directory コネクタ スキーマにソース属性を追加する

**この手順は、Connect バージョン 1.3.21 以前を実行する場合にのみ必要です。 1.4.18 以降の場合は、手順 5 に進みます。** すべての Microsoft Entra 属性がオンプレミスの Active Directory コネクタ スペースにインポートされるわけではありません。 既定で同期されていない属性を使用するように選択した場合は、それをインポートする必要があります。 インポートされた属性の一覧にソース属性を追加するには:

1. Synchronization Service Manager の [**コネクタ**] タブを選択します。
2. オンプレミスの Active Directory コネクタを右選択し、[プロパティ]選択します。
3. ポップアップ ダイアログ ボックスで、**[属性の選択]** タブに移動します。
4. 使用するために選択したソース属性が属性リストでオンになっていることを確認します。 属性が表示されない場合は、[ **すべてを表示** ] チェック ボックスをオンにします。
5. 保存するには、[OK]選択します。

[Image: [属性] リストが強調表示されている [Synchronization Service Manager とプロパティ] ダイアログ ボックスを示すスクリーンショット。]

### 手順 4: **preferredDataLocation** を Microsoft Entra Connector スキーマに追加する

**この手順は、Connect バージョン 1.3.21 以前を実行する場合にのみ必要です。 1.4.18 以降の場合は、手順 5 に進みます。** 既定では、**preferredDataLocation** 属性は Microsoft Entra コネクタ スペースにインポートされません。 インポートされた属性の一覧に追加するには:

1. Synchronization Service Manager の [**コネクタ**] タブを選択します。
2. Microsoft Entra コネクタを右クリックし、[プロパティ]選択します。
3. ポップアップ ダイアログ ボックスで、**[属性の選択]** タブに移動します。
4. 一覧で、**preferredDataLocation** 属性を選択します。
5. 保存するには、[OK]選択します。

[Image: 同期サービス マネージャーと [プロパティ] ダイアログ ボックスのスクリーンショット]

### 手順 5: 受信同期規則を作成する

受信同期規則では、属性値がオンプレミスの Active Directory のソース属性からメタバースにフローすることを許可します。

1. **同期規則エディター** を起動するには、**スタート ボタンをクリックして**&gt;**同期規則エディター**を選択します。
2. 検索フィルターの **[方向]** を **[受信]** に設定します。
3. 新しい受信規則を作成するには、[新しい規則追加] を選択します。
4. [**説明**] タブで、次の構成を指定します。

    | 属性 | 価値 | 細部 |
    | --- | --- | --- |
    | 名前 | *名前* を指定する | 例: "ADから入力 - ユーザーの指定したデータロケーション" |
    | 説明 | *カスタムの説明* を指定する |  |
    | 接続システム | *オンプレミスの Active Directory コネクタ* を選択する |  |
    | 接続システム オブジェクトの種類 | **利用者** |  |
    | メタバース オブジェクト型 | **人物** |  |
    | リンクの種類 | **接続** |  |
    | 優先順位 | *1 ~ 99* の数値を選択します。 | 1 ~ 99 は、カスタム同期規則用に予約されています。 別の同期規則で使用される値は選択しないでください。 |
5. **スコープ フィルター** 空のままにして、すべてのオブジェクトを含めます。 Microsoft Entra Connect の展開に応じてスコープ フィルターを調整することが必要になる場合があります。
6. [**変換] タブ**に移動し、次の変換規則を実装します。

    | フローの種類 | ターゲット属性 | ソース | 1 回適用 | マージの種類 |
    | --- | --- | --- | --- | --- |
    | 直接 | 希望データ保存場所 | ソース属性を選択する | 未チェック | 更新 |
7. 受信方向の規則を作成するには、**[追加]** を選択します。

[Image: 受信同期規則の作成] のスクリーンショット

### 手順 6: 送信同期規則を作成する

送信同期規則では、属性値をメタバースから Microsoft Entra ID の **preferredDataLocation** 属性にフローすることを許可します。

1. **同期規則エディターの**に移動します。
2. 検索フィルター **方向** を **外向き**に設定します。
3. **の新しいルール**を追加を選択します。
4. [**説明**] タブで、次の構成を指定します。

    | 属性 | 価値 | 細部 |
    | --- | --- | --- |
    | 名前 | *名前* を指定する | 例: "Out to Microsoft Entra ID – User preferredDataLocation" |
    | 説明 | *説明を入力* |  |
    | 接続システム | *Microsoft Entra コネクタを選択する* |  |
    | 接続システム オブジェクトの種類 | **利用者** |  |
    | メタバース オブジェクト型 | **人物** |  |
    | リンクの種類 | **接続** |  |
    | 優先順位 | *1 ~ 99* の数値を選択します。 | 1 ~ 99 は、カスタム同期規則用に予約されています。 別の同期規則で使用される値は選択しないでください。 |
5. **スコープ フィルター** タブに移動し、2 つの句を含む 1 つのスコープ フィルター グループを追加します。

    | 属性 | オペレーター | 価値 |
    | --- | --- | --- |
    | ソースオブジェクトタイプ | EQUAL | 利用者 |
    | cloudMastered | NOTEQUAL | 本当 |

    スコープ フィルターは、この送信同期規則が適用される Microsoft Entra オブジェクトを決定します。 この例では、「Out to Microsoft Entra ID – User Identity」のOOB（標準搭載）同期ルールと同じスコープフィルターを使用します。 これにより、オンプレミスの Active Directory から同期されていないユーザー オブジェクト に同期規則が適用されなくなります。 Microsoft Entra Connect の展開に応じてスコープ フィルターを調整することが必要になる場合があります。
6. [**変換**] タブに移動し、次の変換規則を実装します。

    | フローの種類 | ターゲット属性 | ソース | 1 回適用 | マージの種類 |
    | --- | --- | --- | --- | --- |
    | 直接 | 希望データ保存場所 | 希望データ保存場所 | 未チェック | 更新 |
7. まず **を閉じ、次に** を追加して送信規則を作成します。

[Image: 送信同期規則の作成] のスクリーンショット

### 手順 7: 完全同期サイクルを実行する

一般に、完全同期サイクルが必要です。 これは、Active Directory スキーマと Microsoft Entra Connector スキーマの両方に新しい属性を追加し、カスタム同期規則を導入したためです。 変更を Microsoft Entra ID にエクスポートする前に確認します。 完全同期サイクルを構成する手順を手動で実行しながら、次の手順を使用して変更を確認できます。

1. オンプレミスの Active Directory コネクタにて、**のフルインポート** を実行します。

    1. Synchronization Service Manager の [**コネクタ**] タブに移動します。
    2. **オンプレミスの Active Directory コネクタ**を右クリックし、**[実行]** を選択します。
    3. ダイアログ ボックスで、[フル インポート]選択し、[OK]選択します。
    4. 操作が完了するまで待ちます。

        手記

        インポートされた属性の一覧にソース属性が既に含まれている場合は、オンプレミスの Active Directory コネクタでの完全なインポートをスキップできます。 言い換えると、この記事の前の手順 2 で変更を加える必要はありませんでした。
2. Microsoft Entra コネクタでの**フル インポート**を実行する:

    1. **Microsoft Entra コネクタ**を右クリックし、**[実行]** を選択します。
    2. ダイアログ ボックスで、[フル インポート]選択し、[OK]選択します。
    3. 操作が完了するまで待ちます。
3. 既存の **User** オブジェクトの同期規則の変更を確認します。

    オンプレミスの Active Directory からのソース属性と、Microsoft Entra ID から優先されるDataLocationは、それぞれのコネクタ スペースにインポートされます。 完全同期手順に進む前に、オンプレミスの Active Directory コネクタ スペースにある既存の **User** オブジェクトでプレビューを実行します。 選択したオブジェクトには、ソース属性が設定されている必要があります。 **優先データロケーション** がメタバースで入力された成功したプレビューは、同期規則を正しく構成したことを示す適切な指標です。 プレビューを実行する方法については、「[変更](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration#verify-the-change)を確認する」を参照してください。
4. オンプレミスの Active Directory コネクタ **完全同期** を実行します。

    1. **オンプレミスの Active Directory コネクタ**を右クリックし、**[実行]** を選択します。
    2. ダイアログ ボックスで、[完全同期 ] を選択し、[OK] を選択します。
    3. 操作が完了するまで待ちます。
5. Microsoft Entra ID への**保留中のエクスポート**を確認します。

    1. **Microsoft Entra Connector**を右クリックし、**[検索コネクタ スペース]**を選択します。
    2. [**検索コネクタ スペースの**] ダイアログ ボックスで、次の手順を実行します。

        ａ. **[スコープ]** を **[保留中のエクスポート]** に設定します。 b。 **追加、変更、削除**など、3 つのチェック ボックスをすべてオンにします。 c. エクスポートする変更を含むオブジェクトの一覧を表示するには、[検索選択します。 特定のオブジェクトの変更を調べるには、オブジェクトをダブルクリックします。 d. 変更が必要であることを確認します。
6. **Microsoft Entra Connector** で **Export** を実行する

    1. **Microsoft Entra コネクタ**を右クリックし、**[実行]** を選択します。
    2. [実行コネクタ] ダイアログ ボックスで、[エクスポート] を選択し、[OK]を選択します。
    3. 操作が完了するまで待ちます。

手記

この手順には、Microsoft Entra Connector の完全な同期手順や Active Directory コネクタのエクスポート手順が含まれていないことに気付く場合があります。 属性値はオンプレミスの Active Directory から Microsoft Entra 専用に流れているため、手順は必要ありません。

### 手順 8: 同期スケジューラを再度有効にする

組み込みの同期スケジューラを再度有効にします。

1. PowerShell セッションを開始します。
2. 次のコマンドレットを実行して、スケジュールされた同期を再度有効にします: `Set-ADSyncScheduler -SyncCycleEnabled $true`

### 手順 9: 結果を確認する

次に、構成を確認し、ユーザーに対して有効にします。

1. ユーザーの選択した属性に geo を追加します。 使用できる geo の一覧については、[こちらの表](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/microsoft-365-multi-geo)を参照してください。[Image: ユーザー] に追加された AD 属性のスクリーンショット
2. 属性が Microsoft Entra ID に同期されるまで待ちます。
3. Exchange Online PowerShell を使用して、メールボックスのリージョンが正しく設定されていることを確認します。 Exchange Online PowerShell の スクリーンショット テナントがこの機能を使用するようにマークされていると仮定すると、メールボックスは正しい geo に移動されます。 これは、メールボックスが配置されているサーバー名を調べることで確認できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-feature-prevent-accidental-deletes"} -->
## Microsoft Entra Connect Sync: 誤って削除されないようにする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-prevent-accidental-deletes
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、Microsoft Entra Connect で誤って削除されないようにする方法について説明します。

このトピックでは、Microsoft Entra Connect での誤削除 (誤削除の防止) を防ぐ機能について説明します。

Microsoft Entra Connect をインストールする場合、誤って削除されないようにする機能は既定で有効になっており、500 を超える削除を含むエクスポートを許可しないように構成されています。 この機能は、多くのユーザーや他のオブジェクトに影響を与えるオンプレミス ディレクトリに対する誤った構成変更や変更からユーザーを保護するように設計されています。

### 誤って削除されるのを防ぐもの

多くのオブジェクトの削除に関連する一般的なシナリオを次に示します。

- [OU](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#organizational-unitbased-filtering) 全体または[ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#domain-based-filtering)全体を除外していた[フィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering)に変更を加えた場合。
- OU 内のすべてのオブジェクトが移動または削除されます。
- OU の名前が変更され、すべての子オブジェクトが同期の対象範囲外になります。

既定値の 500 個のオブジェクトは、Microsoft Entra Connect と共にインストールされた AD 同期モジュールの一部である `Enable-ADSyncExportDeletionThreshold`を使用して PowerShell で変更できます。 この値は、組織のサイズに合わせて構成する必要があります。

### 誤った削除を防ぐための通知

ステージングされた削除が多すぎて Microsoft Entra ID にエクスポートできない場合は、オブジェクトを削除する前にエクスポートが停止し、次のようなメールが届きます。

[Image: 誤ってメールを削除しないようにする]

| 差出人: | Microsoft セキュリティMSSecurity-noreply@microsoft.com |
| --- | --- |
| タイトル: | Microsoft Entra ID へのエクスポートが停止されました。 予想外の削除のしきい値に達しました。 |
| 説明: | Microsoft Entra ID へのエクスポート操作が失敗しました。 構成されたしきい値を超える数の削除されるオブジェクトがありました。 その結果、オブジェクトはエクスポートされませんでした。 |
| 発生時期: | 2025 年 1 月 24 日 00:00 UTC |
| サーバー: | &lt;サーバー名&gt; |
| サービス： | fabrikamonline.onmicrosoft.com |
| テナント: | FabrikamOnline.com |

Microsoft Entra Connect Health [ポータル](https://portal.azure.com/#blade/Microsoft_Azure_ADHybridHealth/AadHealthMenuBlade) から、同期サービスに移動し、テナントを選択してから、アクティブな Entra Connect サーバーを選択し、[アラート] を選択して、誤削除しきい値が報告されたイベントの一覧を表示します。

[Image: Microsoft Entra Connect 同期アラートを示すスクリーンショット。]

アプリケーション イベント ビューアーのログから、次の例のように警告イベント ID 116 が表示されます。

```
Log Name:      Application
Source:        Directory Synchronization
Date:          <Date/Time>
Event ID:      116
Task Category: None
Level:         Warning
Keywords:      Classic
User:          N/A
Computer:      <server name>
Description:   Prevent Accidental Deletes: The number of deletions for this sync cycle (100 pending deletes) has exceeded the current threshold of 50 objects. Deletions will be suppressed for this sync cycle. Please visit http://go.microsoft.com/fwlink/?LinkId=390655 for more information.
```

### 削除が保留中のオブジェクトを特定する

[エクスポート] ステップの **Synchronization Service Manager** UI を見ると、実行プロファイルの状態`stopped-deletion-threshold-exceeded`を確認できます。 [Image: 誤って削除されないように保護する Sync Service Manager UI] が誤って削除されないようにする

削除しようとしているオブジェクトを確認するには、次の手順を実行します。

1. [スタート] メニューから同期サービス開始します。
2. **コネクタ**に移動します。
3. Windows Azure Active Directory コネクタの種類を選択します。
4. 右にある **[アクション]** で、 **[コネクタの検索領域]** を選択します。
5. **スコープ**のドロップダウン ボックスで、[保留中のエクスポート]  選択し、[**削除]**のチェック ボックスをオンにします。
6. [検索] を選択すると、削除しようとしているすべてのオブジェクトの一覧が表示されます。 各項目を開くと、オブジェクトに関する追加情報を取得できます。 列の設定を選択して、グリッドに表示する属性を追加することもできます (たとえば、onPremisesDistinguishedName)。

    [Image: 検索コネクタ スペースを示すスクリーンショット。]

#### 削除が予期しない場合

不確かな場合や、すべての削除が望ましいか確信が持てない場合、安全のための別の方法を選択することができます。その方法として、スプレッドシートから削除予定のすべてのオブジェクトを [確認](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server) する、より詳細な方法を利用できます。

通常、予期しない削除は、OU 構造の変更またはドメイン/OU スコープのフィルター処理 が原因で発生するため、削除が保留中のオブジェクトが同期スコープ内にあることを確認してください。 たとえば、Active Directory で OU の名前を変更すると、Microsoft Entra Connect ウィザードで OU を再選択しない限り、Microsoft Entra ID で予期しない大量の削除が発生する可能性があります。 属性スコープ フィルターを使用している場合は、同期規則エディターで必要な同期規則を調整して、オブジェクトが同期スコープに戻っていることを確認します。

重要

ドメイン/OU スコープ フィルターと同期規則の変更は、完全同期サイクル ( `Start-ADSyncSyncCycle -PolicyType Initial`) を実行するまで有効になりません。

#### すべての削除が必要な場合

削除が保留中のすべてのオブジェクトが Microsoft Entra ID で削除されるはずである場合は、Entra 全体管理者またはハイブリッド ID 管理者の資格情報を使用して、次の手順を実行します。

警告

この操作により、Microsoft Entra ID 内のオブジェクトが完全に削除される可能性があります。

1. この保護を一時的に無効にし、すべての削除を実行するには、PowerShell コマンドレット (`Disable-ADSyncExportDeletionThreshold -AADUserName "<UserPrincipalName>"`を実行します。
2. Microsoft Entra Connector が選択されている状態で、**[実行]** アクションを選択し、**[エクスポート]** を選択します。
3. 今後予期しない削除から保護するには、削除しきい値機能が完全に無効になっていないことを確認します。 既定値で保護を再度有効にするには、次を実行します: `Enable-ADSyncExportDeletionThreshold -DeletionThreshold 500 -AADUserName "<UserPrincipalName>"`。

組織内で予想される削除の数が多い場合は、この保護を無効にするのではなく、削除のしきい値を増やすことをお勧めします。これにより、望ましくない削除によって重要なデータが失われ、サービスが中断される可能性があるためです。 特定の削除数を評価し、次の PowerShell コマンドレットを使用して新しい制限を設定します。たとえば、削除のしきい値を 1000 に設定するには、 `Enable-ADSyncExportDeletionThreshold -DeletionThreshold 1000 -AADUserName "<UserPrincipalName>"`を使用します。

現在の削除しきい値を確認するには、 `Get-ADSyncExportDeletionThreshold -AADUserName "<UserPrincipalName>"`を実行します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler"} -->
## Microsoft Entra Connect 同期: スケジューラ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、Microsoft Entra Connect Sync の組み込みスケジューラ機能について説明します。

このトピックでは、Microsoft Entra Connect Sync (同期エンジン) の組み込みスケジューラについて説明します。

この機能は、ビルド 1.1.105.0 (2016 年 2 月リリース) で導入されました。

### 概要

Microsoft Entra Connect Sync は、スケジューラを使用して、オンプレミス ディレクトリで発生する変更を同期します。 2 つのスケジューラ プロセスがあります。1 つはパスワード同期用で、もう 1 つはオブジェクト/属性の同期とメンテナンス タスク用です。 このトピックでは、後者について説明します。

以前のリリースでは、オブジェクトと属性のスケジューラは同期エンジンの外部でした。 Windows タスク スケジューラまたは別の Windows サービスを使用して同期プロセスをトリガーしました。 スケジューラには、同期エンジンに組み込まれている 1.1 リリースが含まれており、いくつかのカスタマイズが可能です。 新しい既定の同期頻度は 30 分です。

スケジューラは、次の 2 つのタスクを担当します。

- **同期サイクル** 変更をインポート、同期、およびエクスポートするプロセス。
- **メンテナンス タスク** パスワード リセットとデバイス登録サービス (DRS) のキーと証明書を更新します。 操作ログの古いエントリを消去します。

スケジューラ自体は常に実行されますが、これらのタスクを 1 つだけ実行するように構成することも、実行しない場合もあります。 たとえば、独自の同期サイクル プロセスが必要な場合は、スケジューラでこのタスクを無効にしても、メンテナンス タスクを実行できます。

重要

既定では、同期サイクルは 30 分ごとに実行されます。 同期サイクルを変更した場合は、同期サイクルが少なくとも 7 日に 1 回実行されるようにする必要があります。

- 差分同期は、最後の差分同期から 7 日以内に行う必要があります。
- 差分同期 (完全同期の後) は、最後の完全同期が完了してから 7 日以内に行われる必要があります。

これを行わないと、同期の問題が発生する可能性があるため、完全同期を実行して解決する必要があります。 これは、ステージング モードのサーバーにも適用されます。

### スケジューラの構成

現在の構成設定を確認するには、PowerShell に移動し、`Get-ADSyncScheduler`を実行します。 次のような画像が表示されます。

[Image: GetSyncScheduler]

**このコマンドレットの実行時に同期コマンドまたはコマンドレット** 使用できない場合、PowerShell モジュールは読み込まれません。 この問題は、既定の設定よりも高い PowerShell 制限レベルのドメイン コントローラーまたはサーバーで Microsoft Entra Connect を実行する場合に発生する可能性があります。 このエラーが表示された場合は、`Import-Module ADSync` を実行してコマンドレットを使用できるようにします。

- **AllowedSyncCycleInterval**。 Microsoft Entra ID で許可される同期サイクル間の最短の時間間隔。 この設定よりも頻繁に同期することはできませんが、引き続きサポートされます。
- **CurrentlyEffectiveSyncCycleInterval**。 現在有効なスケジュール。 AllowedSyncInterval よりも頻繁でない場合は、CustomizedSyncInterval (設定されている場合) と同じ値を持ちます。 1.1.281 より前のビルドを使用し、CustomizedSyncCycleInterval を変更した場合、この変更は次の同期サイクルの後に有効になります。 ビルド 1.1.281 から、変更はすぐに有効になります。
- **CustomizedSyncCycleInterval**。 スケジューラを既定の 30 分以外の頻度で実行する場合は、この設定を構成します。 前の図では、代わりにスケジューラが 1 時間ごとに実行されるように設定されています。 この設定を AllowedSyncInterval より小さい値に設定すると、後者が使用されます。
- **NextSyncCyclePolicyType**。 Delta または Initial です。 次の実行で差分変更のみを処理するか、または次の実行で完全なインポートと同期を行う必要があるかどうかを定義します。後者は、新しいルールまたは変更されたルールも再処理します。
- **NextSyncCycleStartTimeInUTC**。 次回スケジューラが次の同期サイクルを開始します。
- **PurgeRunHistoryInterval**。 時間操作ログは保持する必要があります。 これらのログは、同期サービス マネージャーで確認できます。 既定では、これらのログは 7 日間保持されます。
- **SyncCycleEnabled**。 スケジューラがその操作の一部としてインポート、同期、およびエクスポートプロセスを実行しているかどうかを示します。
- **MaintenanceEnabled**。 メンテナンス プロセスが有効になっているかどうかを示します。 証明書/キーを更新し、操作ログを消去します。
- **StagingModeEnabled**。 ステージング モード  が有効になっているかどうかを示します。 この設定を有効にすると、エクスポートの実行は抑制されますが、インポートと同期は引き続き実行されます。
- **SchedulerSuspended**。 アップグレード中に Connect によって設定され、スケジューラの実行を一時的にブロックします。

これらの設定の一部は、`Set-ADSyncScheduler`で変更できます。 次のパラメーターを変更できます。

- カスタマイズされた同期サイクル間隔 (CustomizedSyncCycleInterval)
- NextSyncCyclePolicyType
- PurgeRunHistoryInterval
- 同期サイクル有効
- MaintenanceEnabled

Microsoft Entra Connect の以前のビルドでは、**isStagingModeEnabled** が Set-ADSyncScheduler で公開されていました。 このプロパティの設定は**サポートされていません**。 SchedulerSuspendedプロパティは、Connect でのみ変更する必要があります。 PowerShell でこれを直接設定することは **サポートされていません**。

スケジューラの構成は、Microsoft Entra ID に格納されます。 ステージング サーバーがある場合、プライマリ サーバーの変更もステージング サーバーに影響します (IsStagingModeEnabled を除く)。

#### カスタマイズされた同期サイクル間隔 (CustomizedSyncCycleInterval)

構文: `Set-ADSyncScheduler -CustomizedSyncCycleInterval d.HH:mm:ss` d - 日、HH - 時間、mm - 分、秒 - 秒

例: `Set-ADSyncScheduler -CustomizedSyncCycleInterval 03:00:00` スケジューラを 3 時間ごとに実行するように変更します。

例: `Set-ADSyncScheduler -CustomizedSyncCycleInterval 1.0:0:0` 変更により、スケジューラが毎日実行されるように変更されます。

#### スケジューラを無効にする

構成を変更する必要がある場合は、スケジューラを無効にします。 たとえば、フィルター処理 を構成 場合や、同期規則を変更 場合などです。

スケジューラを無効にするには、`Set-ADSyncScheduler -SyncCycleEnabled $false`を実行します。

[Image: スケジューラ] を無効にする

変更を加えるときは、`Set-ADSyncScheduler -SyncCycleEnabled $true`でスケジューラをもう一度有効にすることを忘れないでください。

### スケジューラを起動する

スケジューラは、既定では 30 分ごとに実行されます。 場合によっては、スケジュールされたサイクルの間に同期サイクルを実行するか、別の種類を実行する必要があります。

#### 差分同期サイクル

差分同期サイクルには、次の手順が含まれます。

- すべてのコネクタでの差分インポート
- すべてのコネクタでの差分同期
- すべてのコネクタでエクスポートする

#### 完全同期サイクル

完全同期サイクルには、次の手順が含まれます。

- すべてのコネクタに対するフル インポート
- すべてのコネクタで完全同期
- すべてのコネクタでエクスポートする

すぐに同期する必要がある緊急の変更がある可能性があるため、手動でサイクルを実行する必要があります。

同期サイクルを手動で実行する必要がある場合は、PowerShell から `Start-ADSyncSyncCycle -PolicyType Delta`実行します。

完全同期サイクルを開始するには、PowerShell プロンプトから `Start-ADSyncSyncCycle -PolicyType Initial` を実行します。

完全同期サイクルの実行には非常に時間がかかる場合があります。このプロセスを最適化する方法については、次のセクションを参照してください。

#### さまざまな構成変更に必要な同期手順

構成の変更が異なると、すべてのオブジェクトに変更が正しく適用されるように、異なる同期手順が必要になります。

- ソース ディレクトリからインポートするオブジェクトまたは属性を追加しました (同期規則を追加または変更する)
    - そのソース ディレクトリのコネクタで完全インポートが必要です
- 同期規則に変更を加えた
    - 変更された同期規則については、コネクタで完全同期が必要です
- [のフィルタリングが](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering)に変更されたので、異なる数のオブジェクトが含まれることになった。
    - 各 AD コネクタに対してコネクタ上でフル インポートが必要です。 例外は、同期エンジンに既にインポートされている属性に基づく属性ベースのフィルター処理を使用している場合です

#### 同期サイクルをカスタマイズすることで、デルタ同期と完全同期のステップを適切に組み合わせて実行することができます。

完全同期サイクルを実行しないようにするには、次のコマンドレットを使用して、特定のコネクタに完全な手順を実行するようにマークします。

`Set-ADSyncSchedulerConnectorOverride -Connector <ConnectorGuid> -FullImportRequired $true`

`Set-ADSyncSchedulerConnectorOverride -Connector <ConnectorGuid> -FullSyncRequired $true`

`Get-ADSyncSchedulerConnectorOverride -Connector <ConnectorGuid>`

例: 新しいインポートされた属性を必要としないコネクタ "AD フォレスト A" の同期規則を変更した場合は、次のコマンドレットを実行して差分同期サイクルを実行し、そのコネクタの完全同期手順も実行します。

`Set-ADSyncSchedulerConnectorOverride -ConnectorName “AD Forest A” -FullSyncRequired $true`

`Start-ADSyncSyncCycle -PolicyType Delta`

例: コネクタ "AD フォレスト A" の同期規則を変更して、新しい属性をインポートする必要が生じるようにした場合は、次のコマンドレットを実行して差分同期サイクルを実行します。このコマンドレットでは、そのコネクタの完全なインポートと完全同期の手順も実行します。

`Set-ADSyncSchedulerConnectorOverride -ConnectorName “AD Forest A” -FullImportRequired $true`

`Set-ADSyncSchedulerConnectorOverride -ConnectorName “AD Forest A” -FullSyncRequired $true`

`Start-ADSyncSyncCycle -PolicyType Delta`

### スケジューラを停止する

スケジューラが現在同期サイクルを実行している場合は、それを停止することが必要な場合があります。 たとえば、インストール ウィザードを起動すると、次のエラーが表示されます。

[Image: スクリーンショットには、[構成を変更できません] というエラー メッセージが表示されます。]

同期サイクルが実行されている場合、構成を変更することはできません。 スケジューラがプロセスを完了するまで待つことができますが、すぐに変更を加えるように停止することもできます。 現在のサイクルを停止することは有害ではなく、保留中の変更は次の実行で処理されます。

1. 最初に、PowerShell コマンドレット `Stop-ADSyncSyncCycle`を使用して現在のサイクルを停止するようにスケジューラに指示します。
2. 1.1.281 より前のビルドを使用している場合、スケジューラを停止しても、現在のコネクタが現在のタスクから停止されることはありません。 コネクタを強制的に停止するには、次のアクションを実行します。

    [Image: スクリーンショットでは、コネクタが選択され、[停止] アクションが選択された状態で実行中のコネクタが強調表示された Synchronization Service Manager が示されています。]

    - [スタート] メニューから **Synchronization Service** を開始します。 [**コネクタ]**に移動し、状態が [**実行中]**のコネクタを強調表示し、アクションから [停止 **]** を選択します。

スケジューラはまだアクティブであり、次の機会に再び開始されます。

### カスタム スケジューラ

このセクションに記載されているコマンドレットは、ビルド [1.1.130.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) 以降でのみ使用できます。

組み込みのスケジューラが要件を満たしていない場合は、PowerShell を使用してコネクタをスケジュールできます。

#### Invoke-ADSyncRunProfile

コネクタのプロファイルは、次の方法で開始できます。

```
Invoke-ADSyncRunProfile -ConnectorName "name of connector" -RunProfileName "name of profile"
```

コネクタ名 および実行プロファイル名に使用する名前は、Synchronization Service Manager UIにあります。

[Image: 実行プロファイル] の呼び出し

`Invoke-ADSyncRunProfile` コマンドレットは同期的です。つまり、コネクタが操作を正常に完了するかエラーが発生するまで制御は返されません。

コネクタをスケジュールするときは、次の順序でスケジュールすることをお勧めします。

1. オンプレミスのディレクトリ（例: Active Directory）から完全/差分インポートを行う
2. (完全/差分) Microsoft Entra ID からのインポート
3. (完全/差分) Active Directory などのオンプレミスのディレクトリから同期する
4. (完全/差分) Microsoft Entra ID からの同期
5. Microsoft Entra ID へのエクスポート
6. Active Directory などのオンプレミスのディレクトリにエクスポートする

この順序は、組み込みのスケジューラがコネクタを実行する方法です。

#### Get-ADSyncConnectorRunStatus

同期エンジンを監視して、ビジー状態かアイドル状態かを確認することもできます。 同期エンジンがアイドル状態でコネクタを実行していない場合、このコマンドレットは空の結果を返します。 コネクタが実行されている場合は、コネクタの名前が返されます。

```
Get-ADSyncConnectorRunStatus
```

[Image: コネクタの実行状態] 上の図では、最初の行は同期エンジンがアイドル状態の状態です。 Microsoft Entra コネクタが実行されている場合の 2 行目。

### スケジューラとインストール ウィザード

インストール ウィザードを開始すると、スケジューラは一時的に中断されます。 この動作は、構成を変更することが前提であり、同期エンジンがアクティブに実行されている場合は、これらの設定を適用できないためです。 このため、同期エンジンが同期操作を実行するのを停止するため、インストール ウィザードを開いたままにしないでください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-recycle-bin"} -->
## Microsoft Entra Connect Sync: AD のごみ箱を有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-recycle-bin
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、Microsoft Entra Connect で AD ごみ箱機能を使用することをお勧めします。

Microsoft Entra ID に同期される Active Directory (AD) のオンプレミス インスタンスに対して、Active Directory のごみ箱機能を有効にすることをお勧めします。

オンプレミスの AD ユーザー オブジェクトを誤って削除し、その機能を使用して復元した場合、Microsoft Entra ID は対応する Microsoft Entra ユーザー オブジェクトを復元します。 Active Directory オブジェクトの復元の詳細については、「[削除された Active Directory オブジェクト](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/dd379542%28v=ws.10%29)を復元するためのシナリオの概要」を参照してください。

Active Directory のごみ箱機能を有効にする方法については、「[Active Directory 管理センターの機能強化](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/get-started/adac/introduction-to-active-directory-administrative-center-enhancements--level-100-#ad_recycle_bin_mgmt)を参照してください。

### AD のごみ箱を有効にする利点

この機能は、次の手順を実行して Microsoft Entra ユーザー オブジェクトを復元するのに役立ちます。

- オンプレミスの AD ユーザー オブジェクトを誤って削除した場合、対応する Microsoft Entra ユーザー オブジェクトは次の同期サイクルで削除されます。 既定では、Microsoft Entra ID は削除された Microsoft Entra ユーザー オブジェクトを論理的に削除された状態で 30 日間保持します。
- オンプレミスの AD ごみ箱機能を有効にしている場合は、ソース アンカーの値を変更せずに、削除されたオンプレミス AD ユーザー オブジェクトを復元できます。 回復されたオンプレミスの AD ユーザー オブジェクトが Microsoft Entra ID に同期されると、Microsoft Entra ID は、対応する論理的に削除された Microsoft Entra ユーザー オブジェクトを復元します。 ソース アンカー属性の詳細については、「Microsoft Entra Connect: Design concepts」記事を参照してください。
- オンプレミスの AD ごみ箱機能を有効にしていない場合は、削除されたオブジェクトを置き換えるために AD ユーザー オブジェクトの作成が必要になる場合があります。 ソース アンカー属性にシステム生成 AD 属性 (ObjectGuid など) を使用するように Microsoft Entra Connect 同期サービスが構成されている場合、新しく作成された AD ユーザー オブジェクトは、削除された AD ユーザー オブジェクトと同じソース アンカー値を持ちません。 新しく作成された AD ユーザー オブジェクトが Microsoft Entra ID に同期されると、Microsoft Entra ID は、論理的に削除された Microsoft Entra ユーザー オブジェクトを復元するのではなく、新しい Microsoft Entra ユーザー オブジェクトを作成します。

手記

既定では、Microsoft Entra ID は、削除された Microsoft Entra ユーザー オブジェクトを完全に削除される前に、論理的に削除された状態で 30 日間保持します。 ただし、管理者はこのようなオブジェクトの削除を高速化できます。 オブジェクトが完全に削除されると、オンプレミスの AD ごみ箱機能が有効になっている場合でも、オブジェクトを回復できなくなります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui"} -->
## Microsoft Entra Connect Sync: Synchronization Service Manager UI - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect の Synchronization Service Manager について説明します。

[Image: Synchronization Service Managerのユーザーインターフェースを示すスクリーンショット]

**Synchronization Service Manager** UI は、同期エンジンのより高度な側面を構成し、サービスの運用面を確認するために使用されます。

[スタート] メニューから **Synchronization Service Manager** UI を起動します。 **同期サービス** と命名され、**Microsoft Entra Connect** グループにあります。[Image: 同期サービスマネージャー]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-connectors"} -->
## Microsoft Entra Connect Sync サービス マネージャー UI 内のコネクタ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-connectors
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect 同期の Service Manager の [コネクタ] タブについて説明します。

[Image: Microsoft Entra Connect Sync Service Manager を示すスクリーンショット。]

[コネクタ] タブを利用し、同期エンジンが接続されているすべてのシステムを管理します。

### コネクタのアクション

| アクション | コメント |
| --- | --- |
| 作成 | サポートされていません。 追加の AD フォレストを接続する場合は、構成ウィザードを使用します。 |
| プロパティ | 読み取り専用。 接続性、ドメインとOUのフィルタリング、属性の選択とアンカーに関するコネクタのプロパティ。 |
| 削除する (コネクタまたはコネクタスペース) | サポートされていません。 AD フォレストを削除する場合は、Microsoft Entra Connect 製品を再インストールします。 |
| 実行プロファイルの構成 | 読み取り専用。 コネクタ実行プロファイル。 |
| 実行 | 1 回限りのコネクタ実行プロファイルを開始します。 |
| 止まれ | コネクタ実行プロファイルを停止します。 |
| コネクタをエクスポート | 読み取り専用。 コネクタ構成をエクスポートします。 |
| コネクタのインポート | サポートされていません。 |
| コネクタの更新 | サポートされていません。 |
| スキーマの更新 | サポートされていません。 同期規則も更新する構成ウィザードの "ディレクトリ スキーマの更新" タスクを使用します。 |
| コネクタ スペースを検索 | オブジェクトを検索し、メタバースとその他の接続されたソース全体のオブジェクト データを表示します。 |

Warning

コネクタまたはコネクタ スペースの削除は **サポートされていないため** 、ハイブリッド ID 環境に重大な影響を与える可能性があります。 これらのアクションをトラブルシューティング手順として使用しないでください。

#### 実行プロファイルの構成

このオプションを使用すると、コネクタ用に構成された実行プロファイルを表示できます。

[Image: 「Delta Import」が選択された「Configure Run Profiles」ウィンドウが表示されたスクリーンショット。]

#### コネクタ スペースの検索

コネクタ スペースの検索アクションは、オブジェクトを検索し、データ問題を解決するのに役立ちます。

[Image: [Search Connector Space](コネクタ スペースの検索) ウィンドウを示すスクリーンショット。]

まず、 **[scope]** (範囲) を選択します。 データ (RDN、DN、アンカー、サブツリー) またはオブジェクトの状態 (その他すべてのオプション) に基づいて検索できます。[Image: [Scope](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/スコープ) ドロップダウン メニューを示すスクリーンショット。] たとえば、Sub-Tree 検索を実行すると、1 つの OU 内のすべてのオブジェクトが取得されます。[Image: [Sub-Tree](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/サブツリー) 検索の例を示すスクリーンショット。] このグリッドからオブジェクトを選択し、 **[プロパティ]** を選択して、ソース コネクタ スペースからメタバースを経てターゲット コネクタ スペースまで[フォロー](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-object-not-syncing)できます。

#### AD DS アカウント パスワードの変更

アカウントのパスワードを変更すると、Synchronization Service でオンプレミスの AD に変更をインポートまたはエクスポートできなくなります。 次のように表示されます。

- AD コネクタのインポートまたはエクスポート手順が失敗し、"no-start-credentials (開始資格情報なし)" エラーが表示されます。
- Windows イベント ビューアーのアプリケーション イベント ログに、イベント ID 6000 のエラーと「資格情報が有効でないため、管理エージェント "contoso.com" は実行できませんでした。」というメッセージが表示されます。

この問題を解決するには、次の手順で AD DS ユーザー アカウントを更新します。

1. Synchronization Service Manager を起動します ([スタート]、[同期サービス] の順に移動します)。 [Image: 同期サービス マネージャー]
2. **[コネクタ]** タブに移動します。
3. AD DS アカウントを使用して構成されている AD コネクタを選択します。
4. [アクション] の **[プロパティ]** を選択します。
5. ポップアップ ダイアログで、[Active Directory フォレストに接続] を選択します。
6. フォレスト名は、対応するオンプレミス AD を示しています。
7. ユーザー名は、同期に使用する AD DS アカウントを示します。
8. [Image: [Microsoft Entra Connect Sync 暗号化キー ユーティリティ]] というパスワード テキスト ボックスに、AD DS アカウントの新しいパスワードを入力します
9. [OK] を選択して新しいパスワードを保存し、同期サービスを再起動して古いパスワードをメモリ キャッシュから削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-mvdesigner"} -->
## Microsoft Entra Connect MV デザイナー - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-mvdesigner
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect の Synchronization Service Manager の [Metaverse Designer] (メタバース デザイナー) タブについて説明します。

[Image: 同期サービスマネージャー]

ほとんどのお客様には、ここで行う必要のある構成はありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-mvsearch"} -->
## Microsoft Entra Connect の Sync Service Manager のメタバース検索 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-mvsearch
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect の Synchronization Service Manager の [メタバース検索] タブについて説明します。

[Image: 同期サービスマネージャー]

[metaverse search (メタバース検索)] タブは、データ関連の問題のトラブルシューティングに役立ちます。 上半分では、属性の組み合わせに基づいたクエリを作成できます。 目的のクエリが作成できたら、 **[検索]**をクリックします。 結果は、下部のグリッドに表示されます。 **[Column Settings]**(列の設定) を使用して、表示する列を選択できます。

検索結果では、オブジェクトを選択してから **[Properties]** (プロパティ) を選択すると [メタバース オブジェクトのプロパティ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-object-not-syncing#metaverse-object-properties)を確認できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-operations"} -->
## Microsoft Entra Connect Synchronization Service Manager の操作 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-operations
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect の Synchronization Service Manager の [操作] タブについて説明します。

[Image: 同期サービスマネージャー]

[操作] タブには、最新の操作の結果が表示されます。 このタブは、問題を理解してトラブルシューティングするための重要なタブです。

### [操作] タブに表示される情報を理解する

上半分には、すべての実行が時系列で表示されます。 既定では、操作ログには過去 7 日間の情報が保持されますが、この設定は [スケジューラ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler)で変更できます。 成功ステータスが表示されない実行を検索したい。 ヘッダーを選択することで、並べ替えを変更できます。

**状態** 列は最も重要な情報であり、実行の最も重大な問題を示します。 調査する優先順位の最も一般的な状態の簡単な概要を次に示します (\*は、考えられるいくつかのエラー文字列を示します)。

| 地位 | コメント |
| --- | --- |
| stopped-\* | 実行を完了できませんでした。 たとえば、リモート システムがダウンしていて、接続できない場合です。 |
| stopped-error-limit | 5,000 を超えるエラーがあります。 多数のエラーが発生したため、実行は自動的に停止されました。 |
| completed-\*-errors | 実行は完了しましたが、調査する必要があるエラー (5,000 未満) があります。 |
| completed-\*-warnings | 実行は完了しましたが、一部のデータが予期した状態ではありません。 エラーがある場合、このメッセージは症状にすぎません。 エラーに対処するまでは、警告を調査しないでください。 |
| 成功 | 問題はありません。 |

行を選択すると、下部が更新され、その実行の詳細が表示されます。 一番下の左端には、**手順 #**というリストがある場合があります。 この一覧は、フォレスト内に複数のドメインがあり、各ドメインがステップで表されている場合にのみ表示されます。 ドメイン名は、パーティション見出しの下にあります。 **同期統計**で、処理された変更の数に関する詳細情報を確認できます。 リンクを選択すると、変更されたオブジェクトの一覧を取得できます。 エラーのあるオブジェクトがある場合は、**同期エラー**にエラーが表示されます。

詳細については、「[同期していないオブジェクトのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-object-not-syncing)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-staging-server"} -->
## Microsoft Entra Connect Sync: 操作タスクおよび考慮事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、Microsoft Entra Connect Sync の運用タスクと、このコンポーネントを操作するための準備方法について説明します。

ステージング モードのサーバーでは、構成を変更した後、そのサーバーをアクティブにする前に変更内容をプレビューできます。 また、フル インポートおよび完全同期を実行して、変更を運用環境に加える前に、すべての変更が予定どおりに加えられていることを確認できます。

### ステージング モード

ステージング モードは、次のシナリオを含むいくつかのシナリオに使用できます。

- フォールト トレランス。
- 新しい構成の変更をテストおよびデプロイする。
- 新しいサーバーを導入し、古いサーバーの使用を中止する。

インストール中またはウィザードを使用して、 **ステージング モード**にするサーバーを選択できます。 この操作により、サーバーでインポートと同期がアクティブになりますが、エクスポートは一切実行されません。 ステージング モードのサーバーでは、インストール中にこれらの機能を選択した場合でも、パスワード同期またはパスワード ライトバックが実行されていません。 ステージング モードを無効にすると、サーバーはエクスポートを開始し、パスワード同期とパスワード ライトバックが有効になります。

ステージング モードが無効になっている場合、パスワードの同期は最後に記録されたウォーターマークから再開されます。 サーバーが長時間ステージング モードのままであった場合、パスワード同期では、ステージング モードの間に発生したすべてのパスワード変更を処理するために、長いキャッチアップ期間 (大規模な環境では数時間以上) が必要になる場合があります。 キャッチアップ中、新しく変更されたパスワードは、バックログが完了した後にのみ処理されるため、Microsoft Entra ID ではすぐには機能しません。 ビジネスへの影響が大きい場合 (たとえば、フェールオーバー時など)、時折ステージングサーバーを一時的にアクティブに昇格させ、将来的な役割スイッチで処理するパスワード変更のバックログを小さくするよう、パスワード同期のキャッチアップが完了するまでアクティブな状態を維持することを検討してください (できればオフピーク時に)。 キャッチアップ中にパスワード同期が進行していることを確認するには、サーバーのアプリケーション イベント ログで進行中のアクティビティ (バッチ処理を示すイベント ID 654/656 など) を監視します。 パスワードの変更が処理されていることを検証するのに役立つユーザーごとの成功イベント (イベント ID 657 など) も表示される場合があります。

警告

パスワード同期のキャッチアップは、ステージング モードを無効にした後、長時間かかる場合があります。 **キャッチアップ中に同期サービスを再起動しないでください** 。サービスを停止すると、再起動時に以前の基準値から PHS が再開され、最新になる時間が長くなる可能性があります。

ステージング モードのサーバーは Active Directory と Microsoft Entra ID から変更を受信し続け、障害発生時に別のサーバーの役割を迅速に引き継ぐことができます。 ステージング モードでは、同期サービス マネージャーを使用してエクスポートを強制できます。

従来の同期テクノロジの知識を持つ管理者にとっては、サーバーが独自の SQL Database を持つ点で、ステージング モードは異なるテクノロジに思えることでしょう。 このアーキテクチャにより、ステージング モードのサーバーを別のデータ センターに配置できます。

#### サーバーの構成の確認

この方法を適用するには、次の手順に従います。

1. 準備
2. 構成
3. インポートおよび同期
4. 確認
5. アクティブなサーバーの切り替え

##### 準備

1. Microsoft Entra Connect をインストールし、**[ステージング モード]** を選択します。インストール ウィザードの最後のページで、**[同期の開始]** を選択解除します。 このモードにより、同期エンジンを手動で実行することができます。 [Image: スクリーンショットには、[Microsoft Entra Connect] ダイアログ ボックスの [構成の準備完了] ページが示されています。]
2. いったんサインオフし、サインインし直してから、[スタート] メニューの **[Synchronization Service (同期サービス)]** を選択します。

##### 構成

プライマリ サーバーに構成を変更する場合は、ステージング モードでサーバーに同じ変更を加える必要があります。

##### インポートおよび同期

1. **[コネクタ]** を選択します。種類が "**Active Directory Domain Services**" の 1 つ目のコネクタを選択します。 [実行] を選択し、[フル インポート] を選択し、[OK] を選択します。 この種類のすべてのコネクタに対して、これらの手順を繰り返します。
2. 種類が **Microsoft Entra ID (Microsoft)** のコネクタを選択します。 [実行] を選択し、[フル インポート] を選択し、[OK] を選択します。
3. [コネクタ] タブがまだ選ばれていることを確認します。 種類が **Active Directory Domain Services** の各コネクタに対し、**[実行]**、**[差分同期]**、**[OK]** の順に選択します。 この種類のすべてのコネクタに対して、これらの手順を繰り返します。
4. 種類が **Microsoft Entra ID (Microsoft)** のコネクタを選択します。 [**実行**] を選択し、[**差分同期**] を選択し、[**OK**] を選択します。

Microsoft Entra ID とオンプレミス AD へのエクスポートの変更をステージングしました (Exchange ハイブリッド展開を使用している場合)。 次の手順では、実際にディレクトリへのエクスポートを開始する前に、変更される内容を確認できます。

##### 確認する

1. コマンド プロンプトを起動し、`%ProgramFiles%\Microsoft Azure AD Sync\bin` に移動します。
2. 次のコマンドを実行します。`csexport "Name of Connector" %temp%\export.xml /f:x` 同期サービスにコネクタの名前があることを確認できます。 Microsoft Entra ID に対して "contoso.com – Microsoft Entra ID" に似た名前が付けられています。
3. 次のコマンドを実行します。`CSExportAnalyzer %temp%\export.xml > %temp%\export.csv` %temp% に export.csv という名前のファイルが生成されます。このファイルは、Microsoft Excel で開くことができます。 このファイルには、エクスポートの対象となるすべての変更が含まれています。
4. データまたは構成に必要な変更を加え、エクスポートされた変更が予期されるまで、インポートと同期と検証の手順をもう一度実行します。

**export.csv ファイルを理解する**

ファイルのほとんどの部分は、一目瞭然です。 内容の理解に役立つ省略形のいくつかを次に示します。

- OMODT - オブジェクトの変更の種類。 オブジェクト レベルでの操作が追加、更新、または削除のいずれかであるかを示します。
- AMODT - 属性の変更の種類。 属性レベルでの操作が追加、更新、または削除のいずれかであるかを示します。

**共通識別子を取得する**

export.csv ファイルには、エクスポートの対象となるすべての変更が含まれています。 各行はコネクタ スペースのオブジェクトの変更に対応しており、オブジェクトは DN 属性で識別されます。 DN 属性は、コネクタ スペースのオブジェクトに割り当てられている一意識別子です。 export.csv に分析対象となる行/変更が多数含まれていると、DN 属性だけに基づいて、変更が行われたオブジェクトを特定するのは難しい場合があります。 変更の分析プロセスを簡素化するには、`csanalyzer.ps1` PowerShell スクリプトを使用します。 このスクリプトは、オブジェクトの共通識別子 (displayName、userPrincipalName など) を取得します。 このスクリプトを使用するには、次の手順に従います。

1. セクション CSAnalyzer から `csanalyzer.ps1` という名前のファイルに PowerShell スクリプトをコピーします。
2. PowerShell ウィンドウを開き、PowerShell スクリプトを作成したフォルダーを参照します。
3. `.\csanalyzer.ps1 -xmltoimport %temp%\export.xml` を実行します。
4. これで、microsoft Excel で調べることができる `processedbatch[n].csv` という名前のファイルまたは複数のファイル ( `[n]` はバッチの数 ( `processedbatch1.csv`など) が作成されました。 このファイルには、DN 属性から共通識別子 (displayName、userPrincipalName など) へのマッピングが示されています。 現時点では、エクスポートの対象となる実際の属性変更は含まれていません。

##### アクティブなサーバーの切り替え

Microsoft Entra Connect は、Active-Passive の高可用性セットアップで設定できます。 このセットアップでは、1 つのサーバーが同期された AD オブジェクトに対する変更を Microsoft Entra ID にアクティブにプッシュし、パッシブ サーバーは、これらの変更を引き継ぐ必要がある場合に備えてステージングします。

注記

Microsoft Entra Connect をアクティブ-アクティブ構成で設定することはできません。 アクティブ/パッシブである必要があります。 変更をアクティブに同期する Microsoft Entra Connect サーバーが 1 つのみであることを確認します。

ステージング モードで Microsoft Entra Connect 同期サーバーを設定する方法の詳細については、「[ステージング モード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server)」を参照してください。

Microsoft Entra Connect のバージョンのアップグレードや、同期サービスの正常性サービスが最新の情報を受信していないというアラートの受信など、いくつかの理由で同期サーバーのフェールオーバーを実行することが必要になる場合があります。 これらのイベントでは、次の手順に従って同期サーバーのフェールオーバーを試みることができます。

重要

ステージング サーバーをアクティブ モードに切り替えると、次の条件が満たされない場合、同期に重大な影響を与える可能性があります。 予防措置として、常に初期同期サイクルを実行し、保留中のエクスポートを確認してから、この操作を実行します。

##### 前提条件

- 現在アクティブな Microsoft Entra Connect 同期サーバー 1 台
- ステージング用 Microsoft Entra Connect 同期サーバー (1 台)
- ステージング サーバーで同期スケジューラが有効になっており、最近 Microsoft Entra ID と同期されました
- 同期規則または同期スコープで更新が行われた場合は、最初の同期サイクルを実行します
- [誤削除を防止](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-prevent-accidental-deletes)するために Microsoft Entra Connect 同期サーバーが構成されていることを確認します
- 保留中のエクスポートを確認し、重要な更新がないこと、およびこのような更新が予想されることを確認します
- [Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect#what-is-microsoft-entra-connect-health) エージェントでサーバーを確認することで [Microsoft Entra Connect Health](https://aka.ms/aadconnecthealth) ポータルが更新されているかどうかを確認します
- 現在のアクティブ サーバーをステージング モードに切り替えてから、ステージング サーバーをアクティブに切り替えます

##### 現在アクティブな同期サーバーをステージング モードに変更する

このプロセスでは、常に 1 つの同期サーバーだけが変更を同期していることを確認する必要があります。 現在アクティブな同期サーバーに到達できる場合は、次の手順を実行してステージング モードに移動できます。 到達できない場合は、サーバーをシャットダウンするか、送信接続から分離することで、サーバーまたは VM が予期せずアクセスを回復しないようにします。

1. 現在アクティブな Microsoft Entra Connect サーバーの場合は、Microsoft Entra Connect ウィザードを開き、[ステージング モードの構成] を選択し、[次へ] を選択します。

[Image: ステージング モードが強調表示されているアクティブな Microsoft Entra Connect ダイアログ ボックスのスクリーンショット。]

1. ハイブリッド ID 管理者の資格情報を使用して Microsoft Entra ID にサインインする必要があります。

[Image: サインイン プロンプトが表示されている Microsoft Entra Connect ダイアログ ボックスのスクリーンショット。]

1. ステージング モードのチェック ボックスをオンにし、[次へ] を選択します。

[Image: ステージング モードの構成が表示されているアクティブな Microsoft Entra Connect ダイアログ ボックスのスクリーンショット。]

1. Microsoft Entra Connect サーバーでインストール済みコンポーネントが確認され、その後、構成の変更が完了したときに同期プロセスを開始するかどうかを確認するプロンプトが表示されます。

[Image: アクティブな Microsoft Entra Connect ダイアログ ボックスの [構成の準備完了] 画面が表示されているスクリーンショット。]

サーバーはステージング モードであるため、Microsoft Entra ID への変更は書き込まれませんが、AD への変更はコネクタ スペースで保持され、あとで書き込めます。 ステージング モードでサーバーの同期プロセスをオンのままにしておくことをお勧めします。そのため、アクティブになるとすぐに引き継ぎ、スコープ内の Active Directory/Microsoft Entra オブジェクトの現在の状態に追いつくために大規模な同期を行う必要はありません。

1. 同期プロセスの開始を選択し、[構成] を選択すると、Microsoft Entra Connect サーバーがステージング モードに構成されます。 完了すると、ステージング モードが有効になっていることを確認する画面が表示されます。 [終了] を選択して完了できます。
2. サーバーが正常にステージング モードになっていることを確認するには、Windows PowerShell を開き、次の各コマンドを使用して、"ADSync" モジュールを読み込み、ADSync スケジューラの構成を確認します。

```powershell
Import-Module ADSync
Get-ADSyncScheduler
```

結果から、"StagingModeEnabled" 設定の値を確認します。 サーバーがステージング モードに正常に切り替えられた場合、この設定の値は次の例のように trueする必要があります。

[Image: 同期サービス コンソールが表示されている Microsoft Entra Connect ダイアログ ボックスのスクリーンショット。]

##### 現在のステージング同期サーバーをアクティブ モードに変更する

この時点で、すべての Microsoft Entra Connect 同期サーバーはステージング モードであり、変更はエクスポートされていないはずです。

警告

パスワード ライトバックを使用している間に Entra Connect サーバーをアクティブ モードに切り替えると、別の Entra Connect サーバーがまだアクティブな場合、一度にパスワード ライトバックを利用できるアクティブなサーバーが 1 つだけであるため、後者のサービス バス通信が中断されます。

次は、ステージング同期サーバーをアクティブ モードに移行し、変更をアクティブに同期するようにします。

1. 元々ステージング モードだった Microsoft Entra Connect サーバーに移動し、Microsoft Entra Connect ウィザードを開きます。

[ステージング モードの構成] を選択し、[次へ] を選択します。

[Image: ステージング モードが強調表示されているステージング Microsoft Entra Connect ダイアログ ボックスのスクリーンショット。]

ウィザードの下部にあるメッセージは、このサーバーがステージング モードであることを示しています。

1. Microsoft Entra ID にサインインし、[ステージング モード] 画面に移動します。

ステージング モードのボックスをオフにし、[次へ] を選択します。

[Image: ステージング モードの構成が表示されているステージング Microsoft Entra Connect ダイアログ ボックスのスクリーンショット。]

このページの警告に従って、他の Microsoft Entra Connect サーバーがアクティブに同期されていないことを確認することが重要です。

アクティブな Microsoft Entra Connect 同期サーバーは、常に 1 つだけであるべきです。

1. 同期プロセスを開始するように求められたら、このボックスにチェックを入れ、[構成] を選択します。

[Image: ステージング Microsoft Entra Connect ダイアログ ボックスの [構成の準備完了] 画面が表示されているスクリーンショット。]

1. プロセスが完了すると、次の確認画面が表示され、[終了] を選択して完了できます。

[Image: 確認画面が表示されているステージング Microsoft Entra Connect ダイアログ ボックスのスクリーンショット。]

1. このプロセスが動作していることを確認するには、同期サービス コンソールを開き、エクスポート手順が実行されているかどうかを確認します。

[Image: 同期サービス コンソールが表示されているステージング Microsoft Entra Connect ダイアログ ボックスのスクリーンショット。]

### 障害復旧

実装の設計には、同期サーバーを喪失するという障害発生時の対処方法を計画することが含まれます。 モデルにはさまざまなものがあり、どのモデルを使用するかは、次の要素を含むいくつかの要素に依存します。

- ダウンタイム中に Microsoft Entra ID のオブジェクトを変更できないことに関してどれだけ許容できますか?
- パスワード同期を使用する場合、オンプレミスでパスワードを変更する場合に備えて Microsoft Entra ID で古いパスワードを使用することが求められることについてユーザーの同意が得られますか?
- パスワード ライトバックなどのリアルタイムの操作に依存していますか?

これらの質問の回答と組織のポリシーに応じて、次の戦略のいずれかを実装することができます。

- 必要に応じて再構築する。
- 予備のスタンバイ サーバーを用意する (" **ステージング モード**" と呼ばれます)。
- 仮想マシンを使用する。

組み込みの SQL Express データベースを使用しない場合は、SQL 高可用性セクションも確認してください。

#### 必要に応じて再構築する

実行可能な戦略は、必要に応じてサーバーの再構築を計画することです。 通常、同期エンジンのインストールと最初のインポートおよび同期操作は、数時間以内に完了します。 使用可能な予備のサーバーがない場合は、ドメイン コントローラーを一時的に使用して同期エンジンをホストできます。

オブジェクトに関する状態は同期エンジン サーバーには保存されないため、Active Directory と Microsoft Entra ID 内のデータからデータベースを再構築することができます。 **sourceAnchor** 属性は、オンプレミスとクラウドからのオブジェクトを結合するために使用されます。 オンプレミスとクラウドの既存のオブジェクトを使ってサーバーを再構築する場合、同期エンジンは、再インストール時にこれらのオブジェクトをもう一度まとめて適合させます。 ドキュメント化して保存する必要があることは、フィルター規則、同期規則など、サーバーに行った構成の変更です。 同期を開始する前に、これらのカスタム構成を再適用する必要があります。

Microsoft [Entra Connect のインポートとエクスポートの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-import-export-config) 方法を使用してサーバーを再構築することもできます。そのため、サーバー構成の up-to-date エクスポートのバックアップがあることを確認してください。

#### 予備のスタンバイ サーバーを用意する - ステージング モード

環境がより複雑な場合は、1 つまたは複数のスタンバイ サーバーを持つことをお勧めします。 インストール時、サーバーを **ステージング モード**に設定できます。

詳しくは、「ステージング モード」をご覧ください。

#### 仮想マシンを使用する

一般的なサポートされている方法は、仮想マシンで同期エンジンを実行する方法です。 ホストに問題が発生した場合、同期エンジン サーバーを含むイメージを別のサーバーに移行できます。

#### SQL 高可用性

Microsoft Entra Connect に付属している SQL Server Express を使用しない場合は、SQL Server の高可用性も考慮する必要があります。 サポートされている高可用性ソリューションには、SQL クラスタリングおよび AOA (Always On 可用性グループ) が含まれます。 サポートされていないソリューションには、ミラーリングがあります。

SQL AOA のサポートが、Microsoft Entra Connect のバージョン 1.1.524.0 に追加されました。 Microsoft Entra Connect をインストールする前に SQL AOA を有効にする必要があります。 インストール中、指定された SQL インスタンスで SQL AOA が有効であるかどうかが Microsoft Entra Connect によって検出されます。 SQL AOA が有効である場合、Microsoft Entra Connect はさらに、SQL AOA が、同期レプリケーションまたは非同期レプリケーションを使用するように構成されているかどうかを調べます。 可用性グループ リスナーを設定する場合、RegisterAllProvidersIP プロパティを 0 に設定する必要があります。 Microsoft Entra Connect は現在、SQL Native Client を使用して SQL に接続していますが、SQL Native Client は、MultiSubNetFailover プロパティの使用をサポートしていません。

### 付録 CSAnalyzer

このスクリプトの使い方については、「確認」をご覧ください。

```powershell
Param(
 [Parameter(Mandatory=$true, HelpMessage="Must be a file generated using csexport 'Name of Connector' export.xml /f:x)")]
 [string]$xmltoimport="%temp%\exportedStage1a.xml",
 [Parameter(Mandatory=$false, HelpMessage="Maximum number of users per output file")][int]$batchsize=1000,
 [Parameter(Mandatory=$false, HelpMessage="Show console output")][bool]$showOutput=$false
)

#LINQ isn't loaded automatically, so force it
[Reflection.Assembly]::Load("System.Xml.Linq, Version=3.5.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089") | Out-Null

[int]$count=1
[int]$outputfilecount=1
[array]$objOutputUsers=@()

#XML must be generated using "csexport "Name of Connector" export.xml /f:x"
write-host "Importing XML" -ForegroundColor Yellow

#XmlReader.Create won't properly resolve the file location,
#so expand and then resolve it
$resolvedXMLtoimport=Resolve-Path -Path ([Environment]::ExpandEnvironmentVariables($xmltoimport))

#use an XmlReader to deal with even large files
$result=$reader=[System.Xml.XmlReader]::Create($resolvedXMLtoimport)
$result=$reader.ReadToDescendant('cs-object')
if($result)
{
 do
 {
  #create the object placeholder
  #adding them up here means we can enforce consistency
  $objOutputUser=New-Object psobject
  Add-Member -InputObject $objOutputUser -MemberType NoteProperty -Name ID -Value ""
  Add-Member -InputObject $objOutputUser -MemberType NoteProperty -Name Type -Value ""
  Add-Member -inputobject $objOutputUser -MemberType NoteProperty -Name DN -Value ""
  Add-Member -inputobject $objOutputUser -MemberType NoteProperty -Name operation -Value ""
  Add-Member -inputobject $objOutputUser -MemberType NoteProperty -Name UPN -Value ""
  Add-Member -inputobject $objOutputUser -MemberType NoteProperty -Name displayName -Value ""
  Add-Member -inputobject $objOutputUser -MemberType NoteProperty -Name sourceAnchor -Value ""
  Add-Member -inputobject $objOutputUser -MemberType NoteProperty -Name alias -Value ""
  Add-Member -inputobject $objOutputUser -MemberType NoteProperty -Name primarySMTP -Value ""
  Add-Member -inputobject $objOutputUser -MemberType NoteProperty -Name onPremisesSamAccountName -Value ""
  Add-Member -inputobject $objOutputUser -MemberType NoteProperty -Name mail -Value ""

  $user = [System.Xml.Linq.XElement]::ReadFrom($reader)
  if ($showOutput) {Write-Host Found an exported object... -ForegroundColor Green}

  #object id
  $outID=$user.Attribute('id').Value
  if ($showOutput) {Write-Host ID: $outID}
  $objOutputUser.ID=$outID

  #object type
  $outType=$user.Attribute('object-type').Value
  if ($showOutput) {Write-Host Type: $outType}
  $objOutputUser.Type=$outType

  #dn
  $outDN= $user.Element('unapplied-export').Element('delta').Attribute('dn').Value
  if ($showOutput) {Write-Host DN: $outDN}
  $objOutputUser.DN=$outDN

  #operation
  $outOperation= $user.Element('unapplied-export').Element('delta').Attribute('operation').Value
  if ($showOutput) {Write-Host Operation: $outOperation}
  $objOutputUser.operation=$outOperation

  #now that we have the basics, go get the details

  foreach ($attr in $user.Element('unapplied-export-hologram').Element('entry').Elements("attr"))
  {
   $attrvalue=$attr.Attribute('name').Value
   $internalvalue= $attr.Element('value').Value

   switch ($attrvalue)
   {
    "userPrincipalName"
    {
     if ($showOutput) {Write-Host UPN: $internalvalue}
     $objOutputUser.UPN=$internalvalue
    }
    "displayName"
    {
     if ($showOutput) {Write-Host displayName: $internalvalue}
     $objOutputUser.displayName=$internalvalue
    }
    "sourceAnchor"
    {
     if ($showOutput) {Write-Host sourceAnchor: $internalvalue}
     $objOutputUser.sourceAnchor=$internalvalue
    }
    "alias"
    {
     if ($showOutput) {Write-Host alias: $internalvalue}
     $objOutputUser.alias=$internalvalue
    }
    "proxyAddresses"
    {
     if ($showOutput) {Write-Host primarySMTP: ($internalvalue -replace "SMTP:","")}
     $objOutputUser.primarySMTP=$internalvalue -replace "SMTP:",""
    }
   }
  }

  $objOutputUsers += $objOutputUser

  Write-Progress -activity "Processing ${xmltoimport} in batches of ${batchsize}" -status "Batch ${outputfilecount}: " -percentComplete (($objOutputUsers.Count / $batchsize) * 100)

  #every so often, dump the processed users in case we blow up somewhere
  if ($count % $batchsize -eq 0)
  {
   Write-Host Hit the maximum users processed without completion... -ForegroundColor Yellow

   #export the collection of users as a CSV
   Write-Host Writing processedbatch${outputfilecount}.csv -ForegroundColor Yellow
   $objOutputUsers | Export-Csv -path processedbatch${outputfilecount}.csv -NoTypeInformation

   #increment the output file counter
   $outputfilecount+=1

   #reset the collection and the user counter
   $objOutputUsers = $null
   $count=0
  }

  $count+=1

  #need to bail out of the loop if no more users to process
  if ($reader.NodeType -eq [System.Xml.XmlNodeType]::EndElement)
  {
   break
  }

 } while ($reader.Read)

 #need to write out any users that didn't get picked up in a batch of 1000
 #export the collection of users as CSV
 Write-Host Writing processedbatch${outputfilecount}.csv -ForegroundColor Yellow
 $objOutputUsers | Export-Csv -path processedbatch${outputfilecount}.csv -NoTypeInformation
}
else
{
 Write-Host "Imported XML file is empty. No work to do." -ForegroundColor Red
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-technical-concepts"} -->
## Microsoft Entra Connect Sync: 技術的な概念 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-technical-concepts
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect Sync の技術的概念について説明します。

この記事は、トピック [アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-technical-concepts)の概要を説明します。

Microsoft Entra Connect Sync は、強固なメタディレクトリ同期プラットフォームに基づいています。 次のセクションでは、メタディレクトリ同期の概念について説明します。 Azure Active Directory Sync Services には、データ ソースに接続したり、データ ソース間でデータを同期したり、ID のプロビジョニングとプロビジョニング解除を行うプラットフォームが用意されています。

[Image: の技術的概念]

以降のセクションでは、同期サービスの次の側面について詳しく説明します。

- コネクタ
- 属性フロー
- コネクタ領域
- メタバース
- プロビジョニング

### コネクタ

接続されたディレクトリとの通信に使用されるコード モジュールは、コネクタ (旧称管理エージェント (MA)) と呼ばれます。

これらは、Microsoft Entra Connect Sync を実行しているコンピューターにインストールされます。コネクタは、特殊なエージェントのデプロイに依存するのではなく、リモート システム プロトコルを使用して会話するエージェントレス機能を提供します。 これは、特に重要なアプリケーションやシステムを扱う場合に、リスクとデプロイ時間が短縮されたことを意味します。

前の図では、コネクタはコネクタ スペースと同義ですが、外部システムとのすべての通信が含まれています。

コネクタは、システムへのすべてのインポートおよびエクスポート機能を担当し、宣言型プロビジョニングを使用してデータ変換をカスタマイズする場合に、開発者が各システムにネイティブに接続する方法を理解する必要をなくします。

インポートとエクスポートは、スケジュールされたときにのみ行われ、システム内で発生する変更からさらに絶縁できます。これは、変更が接続されたデータ ソースに自動的に反映されないためです。 さらに、開発者は、ほぼすべてのデータ ソースに接続するための独自のコネクタを作成することもできます。

### 属性フロー

メタバースは、隣接するコネクタ スペースから結合されたすべての ID の統合ビューです。 前の図では、属性フローは、受信フローと送信フローの両方の矢印が付いた線で示されています。 属性フローは、あるシステムから別のシステムおよびすべての属性フロー (受信または送信) にデータをコピーまたは変換するプロセスです。

同期 (完全または差分) 操作の実行がスケジュールされている場合、コネクタ スペースとメタバースの双方向の間で属性フローが発生します。

属性フローは、これらの同期が実行されるときにのみ発生します。 属性フローは、同期規則で定義されます。 受信 (前の図の ISR) または送信 (前の図の OSR) を指定できます。

### 接続システム

接続されたシステムは、Microsoft Entra Connect Sync が接続されているリモート システムを参照し、ID データの送受信を行います。

### コネクタ領域

接続されている各データ ソースは、コネクタ スペース内のオブジェクトと属性のフィルター処理されたサブセットとして表されます。 これにより、同期サービスは、オブジェクトを同期するときにリモート システムに接続しなくてもローカルで動作し、インポートとエクスポートのみに操作を制限できます。

データ ソースとコネクタが変更の一覧 (差分インポート) を提供できる場合、最後のポーリング サイクル以降の変更のみが交換されるため、運用効率が大幅に向上します。 コネクタ スペースは、コネクタがインポートとエクスポートをスケジュールすることを要求することで、接続されたデータ ソースを変更が自動的に反映されないようにします。 これにより、次の更新プログラムのテスト、プレビュー、または確認中に安心できる保険が追加されました。

### メタバース

メタバースは、隣接するコネクタ スペースから結合されたすべての ID の統合ビューです。

ID がリンクされ、インポート フロー マッピングを通じてさまざまな属性に権限が割り当てられると、中央メタバース オブジェクトは複数のシステムからの情報の集計を開始します。 このオブジェクト属性フローから、マッピングは送信システムに情報を伝達します。

オブジェクトは、権限のあるシステムによってメタバースに投影されるときに作成されます。 すべての接続が削除されるとすぐに、メタバース オブジェクトが削除されます。

メタバース内のオブジェクトを直接編集することはできません。 オブジェクト内のすべてのデータは、属性フローを通じて提供される必要があります。 メタバースは、各コネクタスペースと永続的に接続を維持します。 これらのコネクタでは、同期の実行ごとに再評価は必要ありません。 つまり、Microsoft Entra Connect Sync は、毎回一致するリモート オブジェクトを見つける必要はありません。 これにより、通常はオブジェクトを関連付ける必要がある属性に対する変更を防ぐために、コストの高いエージェントが不要になります。

管理する必要がある既存のオブジェクトを持つ新しいデータ ソースを検出する場合、Microsoft Entra Connect Sync は結合ルールと呼ばれるプロセスを使用して、リンクを確立する候補を評価します。 リンクが確立されると、この評価は繰り返されません。また、リモート接続されたデータ ソースとメタバースの間で通常の属性フローが発生する可能性があります。

### プロビジョニング

権限のあるソースがメタバースに新しいオブジェクトを射影すると、ダウンストリーム接続データ ソースを表す別のコネクタで新しいコネクタ スペース オブジェクトを作成できます。

これは本質的にリンクを確立し、属性フローは双方向に進むことができます。

新しいコネクタ スペース オブジェクトを作成する必要があるとルールで判断されるたびに、プロビジョニングと呼ばれます。 ただし、この操作はコネクタスペース内でのみ行われるため、エクスポートが実行されるまでは接続されたデータソースに引き継がれません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-sync-whatis"} -->
## Microsoft Entra接続同期: 同期を理解してカスタマイズする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect Sync のしくみとカスタマイズ方法について説明します。

Microsoft Entra Connect 同期サービス (Microsoft Entra Connect Sync) は、Microsoft Entra Connect の主要コンポーネントです。 オンプレミス環境とMicrosoft Entra IDの間で ID データを同期するために関連するすべての操作が処理されます。 Microsoft Entra Connect Sync は、DirSync と Azure AD Sync の後継です。

このトピックは、**Microsoft Entra Connect Sync** (**sync エンジン** とも呼ばれます) のホームであり、それに関連する他のすべてのトピックへのリンクを一覧表示します。 Microsoft Entra Connect へのリンクについては、「 Microsoft Entra ID」を参照してください。

同期サービスは、オンプレミスの **Microsoft Entra Connect Sync** コンポーネントと、**Microsoft Entra Connect Sync サービス** と呼ばれるMicrosoft Entra IDのサービス側の 2 つのコンポーネントで構成されます。

重要

Microsoft Entra Connect クラウド同期は、ユーザー、グループ、連絡先をMicrosoft Entra IDに同期するためのハイブリッド ID 目標を満たし、達成するように設計された Microsoft の新しいオファリングです。 これを実現するには、Microsoft Entra Connect アプリケーションではなく、Microsoft Entra クラウド プロビジョニング エージェントを使用します。 Microsoft Entra Connect クラウド同期は、Microsoft Entra Connect Sync を置き換えます。これは、クラウド同期が Microsoft Entra Connect Sync と完全な機能パリティを持つようになった後に廃止されます。この記事の残りの部分は Microsoft Entra Connect Sync についてですが、Microsoft Entra Connect Sync をデプロイする前に、クラウド同期の機能と利点を確認することをお勧めします。

既にクラウド同期の対象になっているかどうかを確認するには、 [このウィザード](https://admin.microsoft.com/adminportal/home?Q=setupguidance#/modernonboarding/identitywizard)で要件を確認してください。

クラウド同期の詳細については、 [この記事](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/what-is-cloud-sync)を参照するか、この [短いビデオ](https://learn-video.azurefd.net/vod/player?id=2b0047aa-84ba-430d-8ce9-39cfdc55276d)をご覧ください。

### 機能と構成のリファレンス

| トピック | 内容と読み取りタイミング |
| --- | --- |
| **Microsoft Entra Connect Sync の基礎** |  |
| [アーキテクチャについて](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-architecture) | 同期エンジンを初めて使用し、アーキテクチャと使用される用語について学習したいユーザー向け。 |
| [技術的な概念](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-technical-concepts) | アーキテクチャ トピックの短いバージョンと、使用される用語について簡単に説明します。 |
| [Microsoft Entra Connect のトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-topologies) | 同期エンジンがサポートするさまざまなトポロジとシナリオについて説明します。 |
| **カスタム構成** |  |
| [インストール ウィザードをもう一度実行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-installation-wizard) | Microsoft Entra接続インストール ウィザードを再度実行するときに使用できるオプションについて説明します。 |
| [宣言型プロビジョニングについて](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-declarative-provisioning) | 宣言型プロビジョニングと呼ばれる構成モデルについて説明します。 |
| [宣言型プロビジョニング式について](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-declarative-provisioning-expressions) | 宣言型のプロビジョニングで使用される式言語の構文について説明します。 |
| [既定の構成について](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-default-configuration) | 既定のルールと既定の構成について説明します。 また、既定のシナリオが機能するためにルールがどのように連携するかについても説明します。 |
| [ユーザーと連絡先について](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-user-and-contacts) | 前のトピックに進み、特にマルチフォレスト環境でのユーザーと連絡先の構成のしくみについて説明します。 |
| [既定の構成を変更する方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration) | 属性フローに共通の構成変更を行う方法について説明します。 |
| [既定の構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-best-practices-changing-default-configuration) を変更するためのベスト プラクティス | サポートの制限事項と、既定の構成を変更する場合。 |
| [フィルター処理の構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering) | 同期するオブジェクトをMicrosoft Entra IDに制限する方法に関するさまざまなオプションと、これらのオプションを構成する手順について説明します。 |
| **機能とシナリオ** |  |
| [誤って削除されないようにする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-prevent-accidental-deletes) | *誤って削除されないように*する機能とその構成方法について説明します。 |
| [Scheduler](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler) | データのインポート、同期、エクスポートを行う組み込みスケジューラについて説明します。 |
| [パスワード ハッシュ同期を実装する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization) | パスワード同期のしくみ、実装方法、操作方法とトラブルシューティング方法について説明します。 |
| [デバイスの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-device-writeback) | Microsoft Entra Connect でのデバイス ライトバックのしくみについて説明します。 |
| [ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions) | 独自のカスタム属性を使用してMicrosoft Entra スキーマを拡張する方法について説明します。 |
| [Microsoft 365 PreferredDataLocation](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-preferreddatalocation) | ユーザーのMicrosoft 365 リソースをユーザーと同じリージョンに配置する方法について説明します。 |
| **同期サービス** |  |
| [Microsoft Entra Connect Sync サービスの機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-features) | 同期サービス側と、Microsoft Entra IDで同期設定を変更する方法について説明します。 |
| [重複属性の回復性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-duplicate-attribute-resiliency) | **userPrincipalName** と **proxyAddresses** 重複属性値の回復性を有効にして使用する方法について説明します。 |
| **操作と UI** |  |
| [Synchronization Service Manager](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui) | 同期Service Manager UI について説明します。 [Operations](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-operations)、[Connectors](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-connectors)、[Metaverse Designer](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-mvdesigner)、[Metaverse Search](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-mvsearch) タブが含まれます。 |
| [運用タスクと考慮事項](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server) | ディザスター リカバリーなどの運用上の問題について説明します。 |
| **操作方法。。。** |  |
| [Microsoft Entra アカウントをリセットします](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-azureadaccount) | Microsoft Entra Connect Sync から Microsoft Entra ID への接続に使用するサービス アカウントの資格情報をリセットする方法。 |
| **詳細情報とリファレンス** |  |
| [ポート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-ports) | 同期エンジンとオンプレミスのディレクトリとMicrosoft Entra IDの間で開く必要があるポートを一覧表示します。 |
| [属性がMicrosoft Entra IDに同期されます](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized) | オンプレミスの AD とMicrosoft Entra IDの間で同期されているすべての属性を一覧表示します。 |
| [関数リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-functions-reference) | 宣言型プロビジョニングで使用できるすべての関数を一覧表示します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-syncservice-duplicate-attribute-resiliency"} -->
## ID 同期と重複属性の回復性 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-duplicate-attribute-resiliency
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect によるディレクトリ同期中に UPN または ProxyAddress が競合しているオブジェクトを処理する方法の新しい動作です。

重複属性の回復性は、Microsoft の同期ツールの 1 つを実行するときに、**UserPrincipalName** と SMTP **ProxyAddress** の競合によって引き起こされる摩擦を排除する Microsoft Entra ID の機能です。

これらの 2 つの属性は、特定の Microsoft Entra テナント内のすべての **ユーザー**、**グループ**、または **Contact** オブジェクトで一意である必要があります。

注

UPN を持てるのは、User のみです。

この機能が有効にする新しい動作は同期パイプラインのクラウド部分にあるため、クライアントに依存せず、Microsoft Entra Connect、DirSync、MIM + Connector などの Microsoft 同期製品に関連します。 このドキュメントでは、これらの製品のいずれかを表すために、一般的な用語 "同期クライアント" を使用します。

### 現在の動作

この一意性制約に違反する UPN または ProxyAddress 値で新しいオブジェクトをプロビジョニングしようとすると、Microsoft Entra ID はそのオブジェクトの作成をブロックします。 同様に、一意でない UPN または ProxyAddress でオブジェクトが更新されると、更新は失敗します。 同期クライアントは、エクスポート サイクルごとにプロビジョニングの試行または更新を再試行し、競合が解決されるまで失敗し続けます。 試行のたびにエラー レポートの電子メールが生成され、エラーが同期クライアントによって記録されます。

### 重複属性の回復性による動作

属性が重複するオブジェクトのプロビジョニングまたは更新を完全に失敗させる代わりに、Microsoft Entra ID は一意性の制約に違反する重複属性を「検疫」します。 この属性が、UserPrincipalName のように、プロビジョニングに必要な場合、サービスはプレースホルダー値を割り当てます。 これらの一時的な値の形式は、*** &lt;OriginalPrefix&gt;+&lt;4DigitNumber&gt;@&lt;InitialTenantDomain&gt;.onmicrosoft.com*** です。

属性の回復性プロセスでは、UPN と SMTP **ProxyAddress** の値のみが処理されます。

**ProxyAddress**など、属性が必要ない場合、Microsoft Entra ID は競合属性を検疫し、オブジェクトの作成または更新を続行するだけです。

属性の検疫時に、競合に関する情報は、従来の動作で使用されるのと同じエラー レポート電子メールで送信されます。 ただし、この情報はエラー レポートに 1 回だけ表示されます。検疫が発生しても、今後のメールには記録されません。 また、このオブジェクトのエクスポートが成功するため、同期クライアントはエラーをログに記録せず、以降の同期サイクル時に作成/更新操作を再試行しません。

この動作をサポートするために、User、Group、Contact オブジェクト クラスに新しい属性が追加されます。**DirSyncProvisioningErrors**

これは複数値の属性であり、普通に追加されたら一意性の制約に違反する、競合している属性を格納します。 Microsoft Entra ID でバックグラウンド タイマー タスクが有効になっています。このタスクは 1 時間ごとに実行され、解決された重複する属性の競合を検索し、問題の属性を検疫から自動的に削除します。

#### 重複属性の回復性の有効化

重複属性の回復性は、すべての Microsoft Entra テナントの新しい既定の動作です。 2016 年 8 月 22 日以降に初めて同期を有効にしたすべてのテナントに対しては、既定でオンになっています。 この日付より前に同期を有効にしたテナントでは、機能がバッチで有効になっています。 このロールアウトは 2016 年 9 月に開始され、機能が有効になっている特定の日付を含む電子メール通知が各テナントの技術通知連絡先に送信されます。

注

重複属性の回復性を有効にすると、無効にすることはできません。

この機能がテナントで有効になっているかどうかを確認するには、Azure Active Directory PowerShell モジュールの最新バージョンをダウンロードして、次のように実行することによって、有効にすることができます。

`Get-EntraDirSyncFeature -Feature DuplicateUPNResiliency`

`Get-EntraDirSyncFeature -Feature DuplicateProxyAddressResiliency`

注

テナントに対して有効にする前に、 `Set-EntraDirSyncFeature` コマンドレットを使用して重複属性の回復性機能を事前に有効にすることはできません。 この機能をテストできるようにするには、新しい Microsoft Entra テナントを作成する必要があります。

### DirSyncProvisioningErrors を持つオブジェクトの特定

現在、重複するプロパティの競合が原因でこれらのエラーが発生したオブジェクトを特定する方法は、Microsoft Entra PowerShell と [Microsoft 365 管理センターの](https://admin.microsoft.com) 2 つあります。 今後のレポートに基づいてポータルを追加する拡張が予定されています。

#### Microsoft Entra PowerShell

このトピックの PowerShell コマンドレットには、以下のような特徴があります。

- 以下のすべてのコマンドレットが、大文字と小文字を区別します。

まず、 **Connect-Entra** を実行し、テナント管理者の資格情報を入力することから始めます。

次に、以下のコマンドレットと演算子を使用して、エラーをさまざまな方法で表示します。

1. すべて表示
2. プロパティの型ごと
3. 競合する値ごと
4. 文字列検索を使用
5. 並べ替え
6. 数量制限あり

##### すべて表示

接続されたら、テナント内の属性プロビジョニング エラーの全般的な一覧を表示するために、次のように実行します。

`Get-MsolDirSyncProvisioningError`

##### プロパティの型ごと

プロパティの種類別にエラーを表示するには、 **UserPrincipalName** または **ProxyAddresses** を指定します。

`Get-EntraDirectoryObjectOnPremisesProvisioningError | Where-Object PropertyCausingError -eq 'UserPrincipalName'`

または

`Get-EntraDirectoryObjectOnPremisesProvisioningError | Where-Object PropertyCausingError -eq 'ProxyAddresses'`

##### 競合する値ごと

特定のプロパティに関連するエラーを表示するには、値を追加します。

`Get-EntraDirectoryObjectOnPremisesProvisioningError | Where-Object PropertyCausingError -eq 'UserPrincipalName' | Where-Object Value -eq 'User@domain.com'`

##### 文字列検索を使用

広範な文字列検索を実行するには:

`Get-EntraDirectoryObjectOnPremisesProvisioningError | Select-Object 'User@domain.com'`

##### 数量制限あり

クエリを特定の数の値に制限するには、次のコマンドを使用します。

`Get-EntraDirectoryObjectOnPremisesProvisioningError | Select-Object -First 10`

### Microsoft 365 管理センター

Microsoft 365 管理センターでは、ディレクトリ同期エラーを表示できます。 Microsoft 365 管理センターのレポートには、これらのエラーを持つ **User** オブジェクトだけが表示されます。 **グループ** と **連絡先**間の競合に関する情報は表示されません。

[Image: Microsoft 365 管理センターでディレクトリ同期エラーを示すスクリーンショット。]

Microsoft 365 管理センターでディレクトリ同期エラーを表示する方法については、「[Microsoft 365 でディレクトリ同期エラーを確認する](https://support.office.com/article/Identify-directory-synchronization-errors-in-Office-365-b4fc07a5-97ea-4ca6-9692-108acab74067)」を参照してください。

#### ID 同期のエラー レポート

重複する属性の競合を持つオブジェクトがこの新しい動作で処理されると、テナントの技術通知連絡先に送信される標準の ID 同期エラー レポート電子メールに通知が含まれます。 ただし、この動作には重要な変更があります。 以前は、重複属性の競合に関する情報が、競合が解決されるまで、後続のすべてのエラー レポートに含められました。 この新しい動作では、特定の競合のエラー通知は、競合する属性が検疫されたときに 1 回だけ表示されます。

ProxyAddress の競合に関する電子メール通知の例を、次に示します。[Image: ProxyAddress の競合に関する電子メール通知の例を示すスクリーンショット。]

### 競合の解決

これらのエラーのトラブルシューティング戦略と解決方法は、過去に重複する属性エラーが処理された方法と変わるべきではありません。 唯一の違いは、タイマー タスクがサービス側のテナント全体をスイープして、競合が解決したら問題の属性を適切なオブジェクトに自動的に追加することです。

次の記事では、さまざまなトラブルシューティングと解決戦略の概要を示しています。[Office 365 でのディレクトリ同期を妨げる重複または無効な属性に関する記事](https://learn.microsoft.com/ja-jp/microsoft-365/troubleshoot/active-directory/duplicate-attributes-prevent-dirsync)

### 既知の問題

これらの既知の問題が、データの損失やサービスの低下を引き起こすことはありません。 いくつかは見た目の問題で、その他には、競合属性が検疫されずに、一般的な "*回復前*" 重複属性エラーがスローされる原因となる問題や、手動での修正を必要とするエラーを引き起こす問題があります。

**主要な動作:**

1. 特定の属性構成を持つオブジェクトは、検疫されている重複属性ではなく、エクスポート エラーを受信し続けます。 例えば次が挙げられます。

    ａ. AD で、UPN を **Joe@contoso.com**、ProxyAddress を **smtp:Joe@contoso.com** として新しいユーザーが作成されました

    b。 このオブジェクトのプロパティが、ProxyAddress が **SMTP:Joe@contoso.com** である既存の Group と競合します。

    c. エクスポート時に、競合属性を検疫するのではなく、**ProxyAddress 競合**エラーがスローされます。 回復性機能が有効になる前と同様に、後続の同期サイクルごとに操作が再試行されます。
2. オンプレミスで 2 つの Group が同じ SMTP アドレスで作成された場合、一方は標準の重複 **ProxyAddress** エラーのために、最初の試行でプロビジョニングに失敗します。 ただし、重複している値は、次の同期サイクル時に適切に検疫されます。

**Office ポータル レポート**:

1. UPN 競合セットの 2 つのオブジェクトの詳細なエラー メッセージは、同一です。 つまり、両方で UPN が変更/検疫されたと示されますが、実際には一方だけでデータが変更されています。
2. UPN 競合に関する詳細なエラーメッセージには、UPN が変更または隔離されたユーザーの表示名が誤って表示されます。 例えば次が挙げられます。

    ａ. **ユーザー A** が最初に **UPN = User@contoso.com** で同期を実行します。

    b。 **ユーザー B** が次に **UPN = User@contoso.com** で同期を試行します。

    c. **ユーザー B** の UPN が **User1234@contoso.onmicrosoft.com** に変更され、**User@contoso.com** が **DirSyncProvisioningErrors** に追加されます。

    d. **ユーザー B** のエラー メッセージには、**ユーザー A** が既に **User@contoso.com** を UPN として持っていることを示す必要がありますが、実際には**ユーザー B** 自身の displayName が表示されます。

**ID 同期のエラー レポート**:

*この問題を解決する方法の手順*のリンクが正しくありません。[Image: [アクティブ ユーザー]]

指している必要がありますhttps://aka.ms/duplicateattributeresiliency。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-syncservice-features"} -->
## Microsoft Entra Connect Sync サービスの機能と構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-features
- Service: entra-id / hybrid-connect
- Article date: 2026-07-03
- Summary: Microsoft Entra Connect Sync サービスのサービス側の機能について説明します。

Microsoft Entra Connect の同期機能には 2 つのコンポーネントがあります。

- **Microsoft Entra Connect Sync** という名前のオンプレミスのコンポーネント (**同期エンジン**とも呼ばれます)。
- Microsoft Entra ID に存在するサービス (**Microsoft Entra Connect Sync サービス**とも呼ばれます)。

このトピックでは、**Microsoft Entra Connect Sync サービス**の以下の機能のしくみと、Windows PowerShell を使用してそれらを構成する方法について説明します。

Graph PowerShell を使用して Microsoft Entra ディレクトリの構成を確認するには、次のコマンドを使用します。

```powershell
Connect-MgGraph -Scopes "OnPremDirectorySynchronization.Read.All"

Get-MgDirectoryOnPremiseSynchronization | Select-Object -ExpandProperty Features | Format-List
```

結果は次のような出力になります。

```powershell
BlockCloudObjectTakeoverThroughHardMatchEnabled  : False
BlockSoftMatchEnabled                            : False
BypassDirSyncOverridesEnabled                    : False
CloudPasswordPolicyForPasswordSyncedUsersEnabled : False
ConcurrentCredentialUpdateEnabled                : False
ConcurrentOrgIdProvisioningEnabled               : False
DeviceWritebackEnabled                           : False
DirectoryExtensionsEnabled                       : True
FopeConflictResolutionEnabled                    : False
GroupWriteBackEnabled                            : False
PasswordSyncEnabled                              : True
PasswordWritebackEnabled                         : False
QuarantineUponProxyAddressesConflictEnabled      : False
QuarantineUponUpnConflictEnabled                 : False
SoftMatchOnUpnEnabled                            : True
SynchronizeUpnForManagedUsersEnabled             : False
UnifiedGroupWritebackEnabled                     : True
UserForcePasswordChangeOnLogonEnabled            : False
UserWritebackEnabled                             : True
AdditionalProperties                             : {
       allowOnPremUpdateOfOnPremisesObjectIdentifierEnabled : False
}
```

注

2016 年 8 月 24 日以降、*重複属性の回復性* 機能は、新しい Microsoft Entra ディレクトリに対して既定で有効になっています。 この機能はロールアウトされ、この日付より前に作成されたディレクトリでも有効になります。 お使いのディレクトリでこの機能が有効になるときに電子メール通知を受け取ります。

Microsoft Entra Connect では、以下の設定が構成されます。

| DirSyncFeature | コメント |
| --- | --- |
| SoftMatchOnUpn | プライマリ SMTP アドレスに加えて userPrincipalName でオブジェクトを結合できます。 |
| UPNを管理対象のユーザーと同期 | 同期エンジンに、管理対象ユーザー/ライセンス ユーザー (非フェデレーション ユーザー) の userPrincipalName 属性の更新を許可します。 |
| DeviceWriteback | [Microsoft Entra Connect: デバイス ライトバックの有効化](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-device-writeback) |
| ディレクトリ拡張子 | [Microsoft Entra Connect Sync: ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions) |
| 重複プロキシアドレスの回復力DuplicateUPNResiliency | エクスポー中に別のオブジェクトとの重複がある場合、オブジェクト全体が失敗するのではなく、属性を検疫状態にすることができます。 |
| パスワード ハッシュの同期 | [Microsoft Entra Connect Sync を使用したパスワード ハッシュ同期の実装](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization) |
| パスワード ライトバック | サポートされていません。 このサービス機能は提供終了しました。 パスワード ライトバックを構成するには、「[Microsoft Entra Connect でパスワード ライトバックを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback#enable-password-writeback-in-microsoft-entra-connect)」をご覧ください。 |
| パススルー認証 | [Microsoft Entra パススルー認証を使用したユーザー サインイン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) |
| 統合グループ書き戻し | グループの書き戻し |
| UserWriteback | 現在サポートされていません。 |

### 重複属性の回復性

UPN や proxyAddress が重複している場合、そのオブジェクトのプロビジョニングが失敗するのではなく、重複している属性を検疫状態にし、一時的な値を割り当てます。 競合が解決されると、一時的な UPN は自動的に適切な値に変更されます。 詳細については、「[ID 同期と重複属性の回復性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-duplicate-attribute-resiliency)」をご覧ください。

### UserPrincipalName のあいまい一致

この機能を有効にすると、[プライマリ SMTP アドレス](https://support.microsoft.com/kb/2641663)に加えて UPN にもあいまい一致が有効になります。プライマリ SMTP アドレスでは、あいまい一致が常に有効になっています。 あいまい一致は、Microsoft Entra ID 内の既存のクラウド ユーザーをオンプレミスのユーザーと照合するために使用されます。

オンプレミスの AD アカウントを、クラウドで作成された既存のアカウントと照合する必要がある場合で、Exchange Online を使用していない場合に、この機能は役立ちます。 このような状況では、通常、クラウドで SMTP 属性を設定する理由がありません。

新たに作成される Microsoft Entra ディレクトリでは、この機能が既定で有効になっています。 この機能が有効になっているかどうかを確認するには、次のコマンドレットを実行します。

```powershell
Connect-MgGraph -Scopes "OnPremDirectorySynchronization.Read.All"

$DirectorySync = Get-MgDirectoryOnPremiseSynchronization
$DirectorySync.Features.SoftMatchOnUpnEnabled
```

この機能が Microsoft Entra ディレクトリに対して有効になっていない場合は、次のコマンドレットを実行して有効にすることができます。

```powershell
Connect-MgGraph -Scopes "OnPremDirectorySynchronization.ReadWrite.All"

$SoftMatchOnUpn = @{ SoftMatchOnUpnEnabled = "true" }
Update-MgDirectoryOnPremiseSynchronization -Features $SoftMatchOnUpn `
   -OnPremisesDirectorySynchronizationId $DirectorySync.Id
```

### BlockSoftMatch

この機能が有効になっていると、あいまい一致機能はブロックされます。 お客様は、この機能を有効にし、テナントにソフト一致機能が再度必要になるまでは有効にしたままにすることをお勧めします。 このフラグは、ソフト一致が完了し、不要になった後で、再度有効にする必要があります。

例 - テナント内でのあいまい一致のブロック:

```powershell
Connect-MgGraph -Scopes "OnPremDirectorySynchronization.ReadWrite.All"

$SoftBlock = @{ BlockSoftMatchEnabled = "true" }
Update-MgDirectoryOnPremiseSynchronization -Features $SoftBlock `
   -OnPremisesDirectorySynchronizationId $DirectorySync.Id
```

注

BlockSoftMatch が有効になっている場合、新しいハイブリッド参加済みデバイスでは、ソフト マッチの試行中に InvalidSoftMatch エラーが発生します。 これは、オンプレミスの Active Directory (AD) から Entra に同期されたコンピューター オブジェクトが、クラウドに登録されている新しいデバイスとマージされるときに発生します。 この問題を解決するには、管理者は BlockSoftMatch を一時的に無効にして、ハイブリッド参加を続行できるようにする必要があります。

### ハード マッチの強制中に onPremisesObjectIdentifier の更新を許可する

ハード マッチのセキュリティ強制により、ターゲット クラウド ユーザーの `onPremisesObjectIdentifier` の値がオンプレミス オブジェクトから受信した値と異なる場合、Microsoft Entra ID はハード マッチをブロックします。 問題を修復するには、既存のクラウド ユーザーの `onPremisesObjectIdentifier` 値をクリアし、ハード マッチを再試行します。

修復できない場合は、テナント レベルの機能フラグ `allowOnPremUpdateOfOnPremisesObjectIdentifierEnabled` を一時的に有効にして更新を許可します。 このフラグは既定で無効になっており、移行、復旧、または統合のシナリオで一時的なバイパスとしてのみ使用する必要があります。 修復が完了したら、フラグを無効にします。

このバイパスを使用するタイミングの有効化手順とガイダンスについては、「 [onPremisesObjectIdentifier の更新を一時的に許可](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-tenant#temporarily-allow-onpremisesobjectidentifier-updates)する」を参照してください。

### userPrincipalName の更新の同期

これまで、次の 2 つの条件が両方とも当てはまらない限り、オンプレミスから同期サービスを使用して UserPrincipalName 属性を更新することはできませんでした。

- 管理対象ユーザー (非フェデレーション)。
- ユーザーにライセンスが割り当てられていない。

注

2019 年 3 月から、フェデレーション ユーザー アカウントでの UPN 変更の同期が許可されています。

この機能を有効にすると、userPrincipalName がオンプレミスで変更されたときに、パスワード ハッシュ同期またはパススルー認証を使用している場合は、同期エンジンによって userPrincipalName が更新されます。

新たに作成される Microsoft Entra ディレクトリでは、この機能が既定で有効になっています。 この機能が有効になっているかどうかを確認するには、次のコマンドレットを実行します。

```powershell
Connect-MgGraph -Scopes "OnPremDirectorySynchronization.Read.All"

$DirectorySync = Get-MgDirectoryOnPremiseSynchronization
$DirectorySync.Features.SynchronizeUpnForManagedUsersEnabled
```

この機能が Microsoft Entra ディレクトリに対して有効になっていない場合は、次のコマンドレットを実行して有効にすることができます。

```powershell
Connect-MgGraph -Scopes "OnPremDirectorySynchronization.ReadWrite.All"

$SyncUpnManagedUsers = @{ SynchronizeUpnForManagedUsersEnabled = "true" }
Update-MgDirectoryOnPremiseSynchronization -Features $SyncUpnManagedUsers `
   -OnPremisesDirectorySynchronizationId $DirectorySync.Id
```

この機能を有効にすると、既存の userPrincipalName の値は as-isのままです。 次に userPrincipalName 属性をオンプレミスで変更したときに、ユーザーに関する通常の差分同期によって UPN が更新されます。 この機能を一度有効にすると、無効にすることはできません。

### パスワード ハッシュの同期

この機能により、同期エンジンはパスワード ハッシュ同期を使用でき、同期クライアントによって自動的に有効になります。

この機能が有効になっているかどうかを確認するには、次のコマンドレットを実行します。

```powershell
# Connect to Microsoft Graph
Connect-MgGraph -Scopes "OnPremDirectorySynchronization.Read.All"

# Retrieve DirSync service features
$DirectorySync = Get-MgDirectoryOnPremiseSynchronization
$DirectorySync.Features.PasswordSyncEnabled
```

たとえば、オンプレミスの Active Directory からの同期を使用停止した後など、パスワード ハッシュ同期が不要になった場合は、次を使用して無効にすることができます。

```powershell
# Connect to Microsoft Graph
Connect-MgGraph -Scopes "OnPremDirectorySynchronization.ReadWrite.All"

# Disable Password Hash Sync
$DirectorySync = Get-MgDirectoryOnPremiseSynchronization
$DirectorySync.Features.PasswordSyncEnabled = $false
Update-MgDirectoryOnPremiseSynchronization -Features $DirectorySync.Features -OnPremisesDirectorySynchronizationId $DirectorySync.Id

```

### パスワード ライトバック

このプロパティは、Microsoft Entra ID からオンプレミス Active Directory へのパスワード ライトバックが有効かどうかを示します。

Von Bedeutung

このプロパティは使用されなくなり、更新はサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-syncservice-shadow-attributes"} -->
## Microsoft Entra Connect Sync サービスのシャドウ属性 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-shadow-attributes
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect Sync サービスのシャドウ属性の機能について説明します。

のほとんどの属性は、Microsoft Entra ID でも、オンプレミスの Active Directory の場合と同じように表現されます。 ただし、一部の属性には特別な処理が必要であるため、Microsoft Entra ID での属性値が Microsoft Entra Connect で同期された値と異なる場合があります。

### シャドウ属性の概要

Microsoft Entra ID では、一部の属性に 2 つの表現があります。 オンプレミスの値と計算された値の両方が格納されます。 これらの特別な属性を、シャドウ属性と呼びます。 この動作が見られる最も一般的な 2 つの属性に、**userPrincipalName** と **proxyAddress** があります。 Microsoft Entra Connect とクラウド同期の同期エンジンは、値をシャドウ属性にエクスポートし、Microsoft Entra ID はこの属性を処理して最終的な値を計算します。 同期エンジンはシャドウ属性からも値をインポートするため、最終的な計算値が異なる場合でも、同期エンジンの観点からは、エクスポートされた元の値を確認できます。 [Microsoft Entra 管理センター](https://entra.microsoft.com)または PowerShell を使用してシャドウ属性を表示することはできませんが、この概念を理解することは、属性がオンプレミスとクラウドで異なる値を持つ特定のシナリオのトラブルシューティングに役立ちます。

この動作を理解するには、次の Fabrikam の例をご覧ください。[Image: 対応する Microsoft Entra ドメインの値が] オンプレミスの Active Directory には複数の UserPrincipalName (UPN) サフィックスがありますが、Microsoft Entra ID で検証されるのは 1 つだけです。

#### userPrincipalName

ユーザーは、未検証ドメインに次の属性値を持っています。

| 特性 | 価値 |
| --- | --- |
| オンプレミスの userPrincipalName | lee.sperry@fabrikam.com |
| Microsoft Entra shadowUserPrincipalName | lee.sperry@fabrikam.com |
| Microsoft Entra userPrincipalName | lee.sperry@fabrikam.onmicrosoft.com |

userPrincipalName 属性は、PowerShell を使用しているときに表示される値です。

実際のオンプレミスの属性値は Microsoft Entra ID に格納されるため、ユーザーが fabrikam.com ドメインを確認するときに、Microsoft Entra ID は shadowUserPrincipalName の値で userPrincipalName 属性を更新します。 これらの値を更新するために、Microsoft Entra Connect またはクラウド同期からの変更を同期する必要はありません。

#### プロキシアドレス

確認済みドメインのみを含めるための同じプロセスは proxyAddresses でも発生しますが、いくつかの追加のロジックが使用されます。 確認済みドメインのチェックは、メールボックスのユーザーに対してのみ発生します。 メールが有効なユーザーまたは連絡先は別の Exchange 組織内のユーザーであることを意味するため、proxyAddresses のあらゆる値をこれらのオブジェクトに追加することができます。

オンプレミスまたは Exchange Online のメールボックスのユーザーの場合、確認済みドメインの値のみが表示されます。 次のようになります。

| 特性 | 価値 |
| --- | --- |
| オンプレミスの proxyAddresses | SMTP:abbie.spencer@fabrikamonline.com smtp:abbie.spencer@fabrikam.com smtp:abbie@fabrikamonline.com |
| Exchange Online の proxyAddresses | SMTP:abbie.spencer@fabrikamonline.com smtp:abbie@fabrikamonline.com SIP:abbie.spencer@fabrikamonline.com |

この場合、 **smtp:abbie.spencer@fabrikam.com** は、そのドメインが検証されていないため削除されました。 ただし、Exchange では **SIP:abbie.spencer@fabrikamonline.com** の追加も行われています。 Fabrikam はオンプレミスの Lync/Skype for Business を使用していない可能性がありますが、Microsoft Entra ID と Exchange Online はそれに備えます。

proxyAddresses のこのロジックは、**ProxyCalc** と呼ばれます。 ProxyCalc は、ユーザーに対する以下の変更のたびに呼び出されます。

- ユーザーが Exchange メールボックスのライセンスを取得していない場合でも、Exchange Online を含むサービス プランが割り当てられます。 たとえば、ユーザーに Office E3 SKU が割り当てられているが、SharePoint Online サービスのみが選択されている場合です。 この条件は、ユーザーのメールボックスがまだオンプレミスの場合でも当てはまります。
- 属性 msExchRecipientTypeDetails には値があります。
- proxyAddresses または userPrincipalName に変更を加えるとき。

ShadowProxyAddresses に未検証ドメインが含まれており、ユーザーに次のいずれかのプロパティが構成されている場合、ProxyCalc プロセスはアドレスをサニタイズします。

- ユーザーが EXO サービスの種類のプランが有効な状態でライセンスされている (MyAnalytics を除く)
- ユーザーに MSExchRemoteRecipientType が設定されている (null 以外)
- ユーザーが共有リソースと見なされている

CloudMSExchRecipientDisplayType 属性に次のいずれかの値がある場合、ユーザーは共有リソースと見なされます。

| オブジェクトの表示の種類 | 値 (10 進数) |
| --- | --- |
| MailboxUser | 0 |
| パブリックフォルダー | 2 |
| 会議室メールボックス | 7 |
| EquipmentMailbox | 8 |
| アービトレーションメールボックス (調停用メールボックス) | 10 |
| ルームリスト | 15 |
| TeamMailboxUser | 16 |
| GroupMailbox | 十七 |
| スケジューリングメールボックス | 18 |
| ACLableMailboxUser | 1073741824 |
| ACLエイブルチームメールボックスユーザー | 1073741840 |

注

CloudMSExchRecipientDisplayType は Microsoft Entra ID 側からは表示されず、Exchange Online コマンドレット [Get-Recipient](https://learn.microsoft.com/ja-jp/powershell/module/exchange/get-recipient) を使用してのみ表示できます。

例：

```PowerShell
  Get-Recipient admin | fl *type*
```

ProxyCalc は、ユーザーの変更を処理するのに時間がかかる場合があり、Microsoft Entra Connect エクスポート プロセスと同期されません。

注

この ProxyCalc のロジックは、高度なシナリオでその他の動作を行いますが、このトピックには記載していません。 このトピックは動作の説明を目的とするため、文書化されているのは一部の内部ロジックのみです。

#### 検疫済みの属性値

シャドウ属性は、属性値が重複している場合にも使用されます。 詳細については、[重複属性の回復性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-duplicate-attribute-resiliency)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-connect-uninstall"} -->
## Microsoft Entra Connect をアンインストールする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-uninstall
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このドキュメントでは、Microsoft Entra Connect をアンインストールする方法について説明します。

このドキュメントでは、Microsoft Entra Connect を正しくアンインストールする方法について説明します。

### サーバーから Microsoft Entra Connect をアンインストールする

最初に実行されているサーバーから Microsoft Entra Connect を削除する必要があります。 次の手順を使用します。

1. Microsoft Entra Connect を実行しているサーバーで、コントロール パネルに移動します。
2. **[プログラムのアンインストール]** を選択します[Image: プログラムのアンインストール]
3. **Microsoft Entra Connect**を選択します。 [Image: Microsoft Entra Connect] を選択する
4. メッセージが表示されたら、[はい]選択して確認します。
5. この確認により、Microsoft Entra Connect 画面が表示されます。 [**を選択して**を削除] [Image: 削除]
6. この操作が完了したら、**終了**を選択します。
7. [Image: 出口]
8. コントロール パネル **に戻って**、**更新** を選択すると、すべてのコンポーネントは削除されるはずです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-dirsync-upgrade-get-started"} -->
## Microsoft Entra Connect: DirSync からのアップグレード - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-dirsync-upgrade-get-started
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: DirSync から Microsoft Entra Connect にアップグレードする方法について説明します。 この記事では、DirSync から Microsoft Entra Connect へのアップグレード手順について説明します。

Microsoft Entra Connect は DirSync の後継のツールです。 この記事では、DirSync から Microsoft Entra Connect にアップグレードする方法について説明します。 この記事で説明する手順は、Microsoft Entra Connect の別のバージョンから、または Azure Active Directory (Azure AD) Sync からアップグレードする場合には機能しません。

DirSync と Azure AD Sync はサポートされておらず、動作しません。 DirSync または Azure AD Sync をまだ使用している場合は、Microsoft Entra Connect にアップグレードして同期プロセスを再開する*必要があります*。

Microsoft Entra Connect のインストールを始める前に、必ず [Microsoft Entra Connect をダウンロード](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)し、[Microsoft Entra Connect のハードウェアと前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)に関するページで説明されている前提条件の手順を完了してください。 Microsoft Entra Connect に関する以下の要件は DirSync と異なるため、特に注意してください。

- **.NET と PowerShell の必要なバージョン**: DirSync で必要な新しいバージョンは、Microsoft Entra Connect ではサーバー上にある必要があります。
- **プロキシ サーバーの構成**: プロキシ サーバーを使用してインターネットに接続する場合は、アップグレードする前にこの設定を構成する必要があります。 DirSync では、プロキシ サーバーをインストールしたユーザー向けに構成されたプロキシ サーバーが常に使用されていましたが、Microsoft Entra Connect では、代わりにコンピューターの設定が使用されます。
- **プロキシ サーバーで開く必要がある URL**: DirSync でもサポートされていた基本的なシナリオでは、要件は同じです。 Microsoft Entra Connect の新機能のいずれかを使用する場合は、いくつかの新しい URL を開く必要があります。

警告

新しい Microsoft Entra Connect サーバーが Microsoft Entra ID に対する変更の同期を開始できるようにした後は、DirSync または Azure AD Sync を使用してロールバックしないでください。Azure AD Connect から DirSync、Microsoft Entra Sync などの従来のクライアントへのダウングレードはサポートされておらず、Microsoft Entra ID のデータ損失のような問題につながる場合があります。

DirSync からアップグレードしない場合は、関連ドキュメントでその他のシナリオを確認してください。

### DirSync からのアップグレード

現在の DirSync のデプロイに応じて、アップグレードのためのさまざまなオプションがあります。 予想されるアップグレード時間が 3 時間未満の場合は、インプレース アップグレードを実行することをお勧めします。 予想されるアップグレード時間が 3 時間を超える場合は、別のサーバーで並列デプロイを行うことをお勧めします。 50,000 以上のオブジェクトがある場合、アップグレードの所要時間は 3 時間を超えることが予想されます。

アップグレードのシナリオを次の表にまとめています。

| 予想されるアップグレード時間 | オブジェクトの数 | 使用するアップグレード オプション |
| --- | --- | --- |
| 3 時間未満 | 50,000 未満 | インプレース アップグレード |
| 3 時間を超える | 50,000 以上 | 並列デプロイ |

注

DirSync から Microsoft Entra Connect へのアップグレードを計画している場合は、アップグレードより前に DirSync を自分でアンインストールしないでください。 Microsoft Entra Connect が DirSync から構成を読み取って移行し、サーバーを検査した後に、アンインストールします。

- **インプレース アップグレード**。 ウィザードには、アップグレードが完了する予定時刻が表示されます。 この推定値は、50,000 のオブジェクト (ユーザー、連絡先、グループ) を含むデータベースのアップグレードが完了するまでに 3 時間かかるという前提に基づいています。 データベース内のオブジェクトの数が 50,000 未満である場合は、Microsoft Entra Connect ではインプレース アップグレードが推奨されています。 続行すると、現在の設定がアップグレード中に自動的に適用され、サーバーが自動的にアクティブな同期を再開します。

    構成の移行を実行し、*かつ*並列デプロイを実行する場合は、インプレース アップグレードに関する推奨事項を無視してもかまいません。 たとえば、ハードウェアとオペレーティング システムを更新する機会としてアップグレードを使用できます。 詳細については、「並列デプロイ」を参照してください。
- **並列デプロイ**。 50,000 以上のオブジェクトがある場合は、並列デプロイをお勧めします。 この種類のデプロイでは、ユーザーに対して操作時の遅延が発生しません。 Microsoft Entra Connect のインストールでは、アップグレードのためのダウンタイムを予測しますが、過去に DirSync をアップグレードしたことがある場合は、その経験がアップグレードの所要時間に関する最善の指標となります。

#### アップグレードでサポートされる DirSync の構成

DirSync からのアップグレードでは、次の構成の変更がサポートされています。

- ドメインと組織単位 (OU) のフィルター処理
- 別の ID (UPN)
- パスワード同期と Exchange ハイブリッドの設定
- あなたのフォレスト、ドメイン、Microsoft Entra の設定
- ユーザー属性に基づくフィルター処理

次の変更をアップグレードすることはできません。 これが構成されている場合は、アップグレードがブロックされます。

- サポートされていない DirSync の変更 (属性の削除やカスタム拡張 DLL の使用など)

    [Image: DirSync の構成が原因でアップグレードがブロックされていることを示すスクリーンショット。]

    サポートされていないアップグレードのシナリオでは、[ステージング モード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server)で新しい Microsoft Entra Connect サーバーをインストールし、古い DirSync と新しいMicrosoft Entra Connect の構成を確認することが推奨されます。 カスタム構成を使用して変更をもう一度適用する場合は、[Microsoft Entra Connect Sync のカスタム構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)に関するページを参照してください。

DirSync がサービス アカウントに使用するパスワードは取得できないため、移行されません。 これらのパスワードはアップグレード中にリセットされます。

#### DirSync から Microsoft Entra Connect へのアップグレードの大まかな手順

1. Microsoft Entra Connect へようこそ
2. 現在の DirSync 構成の分析
3. Microsoft Entra ハイブリッド ID 管理者アカウントのパスワードを収集する
4. エンタープライズ管理者アカウントの資格情報を収集する (Microsoft Entra Connect のインストール時にのみ使用)
5. Microsoft Entra Connect のインストール:
    1. DirSync のアンインストール (または一時的な無効化)
    2. Microsoft Entra Connect をインストールする
    3. 必要に応じて同期を開始する

次の場合、さらに手順が必要になります。

- 現在、ローカルかリモートかにかかわらず、SQL Server の完全バージョンを使用している。
- 50,000 以上のオブジェクトが同期のスコープにある。

### 一括アップグレード

インプレース アップグレードを実行するには、次の手順に従います。

1. Microsoft Entra Connect のインストーラー (MSI ファイル) を開きます。
2. ライセンス条項とプライバシーに関する声明を確認し、同意します。

    [Image: [Microsoft Entra Connect へようこそ] ページを示すスクリーンショット。]
3. **[次へ]** を選択して、既存の DirSync インストールを分析します。

    [Image: 既存の DirSync インストールを分析しているときの Microsoft Entra Connect を示すスクリーンショット。]
4. 分析が完了すると、続行方法に関する推奨事項が表示されます。

    - SQL Server Express を使用しており、オブジェクトの数が 50,000 未満である場合は、次のページが表示されます。

        [Image: 分析が完了し、DirSync からアップグレードする準備ができていることを示すスクリーンショット。]
    - DirSync に完全バージョンの SQL Server を使用する場合、次のページが表示されます。

        [Image: 使用されている既存の SQL データベース サーバーを示すスクリーンショット。]

        DirSync が使用している既存の SQL Server データベース サーバーに関する情報が表示されます。 必要に応じて、調整を行います。 **[次へ]** を選択し、インストールを続行します。
    - 50,000 以上のオブジェクトがある場合は、次のページが表示されます。

        [Image: アップグレードするオブジェクトが 50,000 以上ある場合に表示されるページを示すスクリーンショット。]

        インプレース アップグレードを続行するには、**[このコンピューターの DirSync のアップグレードを続行します]** を選択します。

        並列デプロイを行うには、DirSync の構成設定をエクスポートして新しいサーバーに移します。
5. Microsoft Entra ID への接続に現在使用しているアカウントのパスワードを入力します。 これは、DirSync が使用するアカウントである必要があります。

    [Image: Microsoft Entra の資格情報を入力する場所を示すスクリーンショット。]

    エラー メッセージが表示される場合や接続に問題がある場合は、[接続の問題に対するトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-connectivity)についてのページを参照してください。
6. Active Directory Domain Services (AD DS) の Enterprise Admins アカウントを入力します。

    [Image: AD DS の資格情報を入力する場所を示すスクリーンショット。]
7. 構成する準備が整いました。 **[アップグレード]** を選択すると、DirSync がアンインストールされ、Microsoft Entra Connect が構成されて、同期が開始されます。

    [Image: [構成の準備完了] ページを示すスクリーンショット。]
8. インストールが終了したら、Synchronization Service Manager または同期規則エディターを使用する前、または他の構成の変更を試す前に、Windows からサインアウトしてもう一度サインインします。

### 並列デプロイ

並列デプロイを使用してアップグレードするには、次のタスクを完了します。

#### DirSync の構成をエクスポートする

**オブジェクトが 50,000 以上の場合の並列デプロイ**

オブジェクトの数が 50,000 以上の場合、Microsoft Entra Connect のインストール ウィザードでは並列デプロイが推奨されます。

次の例のようなページが表示されます。

[Image: 分析完了と [設定のエクスポート] ボタンを示すスクリーンショット。]

並列デプロイを開始する場合は、次の手順を完了します。

- **[エクスポート設定]** を選択します。 Microsoft Entra Connect を別のサーバーにインストールすると、これらの設定が現在の DirSync インスタンスから新しい Microsoft Entra Connect のインストールに移行されます。

設定が正常にエクスポートされた後、DirSync サーバーで Microsoft Entra Connect ウィザードを終了できます。 次の手順に進み、別のサーバーに Microsoft Entra Connect をインストールします。

**オブジェクトが 50,000 未満の場合の並列デプロイ**

オブジェクトの数が 50,000 未満の場合に並列デプロイを実行するには、次の手順を実行します。

1. Microsoft Entra Connect のインストーラーを実行します。
2. **[Microsoft Entra Connect へようこそ]** で、ウィンドウの右上隅にある [X] を選択してインストール ウィザードを終了します。
3. コマンド プロンプト ウィンドウを開きます。
4. Microsoft Entra Connect のインストール場所 (既定値は *C:\Program Files\Microsoft Azure Active Directory Connect) で*、次のコマンドを実行します。

    `AzureADConnect.exe /ForceExport`
5. **[エクスポート設定]** を選択します。 Microsoft Entra Connect を別のサーバーにインストールすると、これらの設定が現在の DirSync インスタンスから新しい Microsoft Entra Connect のインストールに移行されます。

    [Image: 新しい Microsoft Entra Connect のインストールに設定を移行するための [設定のエクスポート] オプションを示すスクリーンショット。]

設定が正常にエクスポートされた後、DirSync サーバーで Microsoft Entra Connect ウィザードを終了できます。 次の手順に進み、別のサーバーに Microsoft Entra Connect をインストールします。

#### 別のサーバーに Microsoft Entra Connectをインストールする

Microsoft Entra Connect を新しいサーバーにインストールする場合、Microsoft Entra Connect のクリーン インストールを実行するものと見なされます。 DirSync の構成を使用するには、実行する手順が増えます。

1. Microsoft Entra Connect のインストーラーを実行します。
2. **[Microsoft Entra Connect へようこそ]** で、ウィンドウの右上隅にある [X] を選択してインストール ウィザードを終了します。
3. コマンド プロンプト ウィンドウを開きます。
4. Microsoft Entra Connect のインストール場所 (既定は *C:\Program Files\Microsoft Entra Connect*) で、次のコマンドを実行します。

    `AzureADConnect.exe /migrate`

    Microsoft Entra Connect のインストール ウィザードが起動し、次のページが表示されます。

    [Image: アップグレード時に設定ファイルをインポートする場所を示すスクリーンショット。]
5. DirSync インストールからエクスポートした設定ファイルを選択します。
6. 次の高度なオプションを構成します。

    - Microsoft Entra Connect のカスタムのインストール場所。
    - SQL Server の既存のインスタンス (既定では、Microsoft Entra Connect により SQL Server 2019 Express がインストールされます)。 DirSync サーバーで使用するのと同じデータベース インスタンスは使用しないでください。
    - SQL Server への接続に使用されるサービス アカウント。 SQL Server データベースがリモートの場合、このアカウントはドメイン サービス アカウントにする必要があります。

    次の図は、このページにあるその他のオプションを示しています。

    [Image: DirSync からアップグレードするための高度な構成オプションを示すスクリーンショット。]
7. [**次へ**] を選択します。
8. **[構成の準備完了]** で、**[構成が完了したら、同期処理を開始してください]** オプションは選択されたままにします。 サーバーは[ステージング モード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server)になっているため、変更は Microsoft Entra ID にエクスポートされません。
9. **[インストール]** を選択します。
10. インストールが終了したら、Synchronization Service Manager または同期規則エディターを使用する前、または他の構成の変更を試す前に、Windows からサインアウトしてもう一度サインインします。

注

この時点で、オンプレミスの Windows Server Active Directory (Windows Server AD) と Microsoft Entra ID の間で同期が開始しますが、変更は Microsoft Entra ID にエクスポートされません。 一度に 1 つの同期ツールのみが変更をアクティブにエクスポートできます。 この状態は[ステージング モード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server)と呼ばれます。

#### Microsoft Entra Connect の同期を開始する準備が完了していることを確認する

Microsoft Entra Connect で DirSync からの引き継ぎが準備できていることを確認するには、[スタート] メニューで **[Microsoft Entra Connect]**&gt;**[Synchronization Service Manager]** を選択します。

アプリケーションで、**[操作]** タブに移動します。このタブで、以下の操作が完了成功を示していることを確認します。

- Windows Server AD コネクタでの**フル インポート**
- Microsoft Entra コネクタでの**完全インポート**
- Windows Server AD コネクタでの**完全同期**
- Microsoft Entra コネクタでの**完全同期**

[Image: コネクタ操作で完了したインポートと同期を示すスクリーンショット。]

これらの操作の結果を確認し、エラーが発生しないことを確認します。

どの変更が Microsoft Entra ID にエクスポートされるのかを調べるには、[ステージング モードで構成を確認する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server)方法を確認してください。 予期しない内容が表示されなくなるまで構成の変更を行ってください。

これらの手順が完了し、結果に問題がなければ、DirSync から Microsoft Entra ID に切り替える準備ができています。

#### DirSync (古いサーバー) をアンインストールする

次に、DirSync をアンインストールします。

1. **[プログラムと機能]** で **[Windows Azure Active Directory 同期ツール]** を見つけて選択します。
2. コマンド バーの **[アンインストール]** を選択します。

アンインストールには最大で 15 分ほどかかる場合があります。

後で DirSync をアンインストールする場合は、一時的にサーバーをシャットダウンするか、サービスを無効にしておくことができます。 この方法を使用すると、何か問題が発生した場合にサービスを再び有効にできます。

DirSync がアンインストールされているか無効になっている場合、Microsoft Entra ID へのエクスポートが行われているアクティブなサーバーはありません。 オンプレミスの Windows Server AD のインスタンスでのすべての変更が引き続き Microsoft Entra ID に同期されるようにするには、次の手順を完了して Microsoft Entra Connect を有効にする必要があります。

#### Microsoft Entra Connect を有効にする (新しいサーバー)

インストール後、さらに構成変更を行うには、Microsoft Entra Connect をもう一度開きます。 [スタート] メニューから、またはデスクトップのショートカットから Microsoft Entra Connect を開きます。 *インストール MSI ファイルをもう一度実行しないようにしてください*。

1. **[追加のタスク]** で、**[ステージング モードの構成]** を選択します。
2. **[ステージング モードの構成]** で、**[ステージング モードを有効にする]** チェック ボックスをオフにしてステージングをオフにします。

    [Image: ステージング モードを有効にするためのオプションを示すスクリーンショット。]
3. [**次へ**] を選択します。
4. 確認ページで **[インストール]** を選択します。

これで、Microsoft Entra Connect がアクティブなサーバーになりました。 既存の DirSync サーバーの使用に再び切り替えることはしないでください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/how-to-upgrade-previous-version"} -->
## Microsoft Entra Connect: 旧バージョンからアップグレードする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version
- Service: entra-id / hybrid-connect
- Article date: 2025-09-17
- Summary: インプレース アップグレードとスウィング移行など、Microsoft Entra Connect の最新リリースにアップグレードするさまざまな方法について説明します。

重要

最新バージョンの Microsoft Enra Connect にアップグレードするのではなく、クラウド同期が最適かどうかを確認してください。 詳細については、[サポートされている同期シナリオの比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)を使用してオプションを評価します

このトピックでは、Microsoft Entra Connect のインストールを最新リリースにアップグレードするために使用できるさまざまな方法について説明します。 以前の 1.x バージョンから構成を大幅に変更またはアップグレードする場合は、「 Swing 移行 」セクションの手順を使用することをお勧めします。

Microsoft Entra Connect の最新リリースでサーバーを最新の状態に保つことが重要です。 Microsoft Entra Connect に対するアップグレードは絶えず行われており、これらのアップグレードには、セキュリティの問題およびバグの修正プログラムの他、サービス性、パフォーマンス、スケーラビリティの向上が含まれます。

重要

**必須のアップグレードが必要です。**Connect Sync Microsoft Entraバージョン 2.6.84.0 以降にアップグレードし、2027 年 4 月 7 日までにアプリケーション ベースの認証を構成します。 レガシ認証は廃止され、これらの要件が満たされていない場合、同期サービスはこの日以降動作を停止します。

同期が停止した場合は、最新バージョンにアップグレードし、サービスを復元するようにアプリケーション ベースの認証を構成します。 Microsoft Entra Connect Sync .msi インストール ファイルは、 [Microsoft Entra Admin Center](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) でのみ使用できます。 .NET Framework 4.7.2 や TLS 1.2 などの最小要件を満たしていることを確認します。

最新バージョンを確認し、バージョン間で行われた変更を確認するには、[リリース バージョンの履歴](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history)を参照してください

Microsoft Entra Connect V2 より古いバージョンは、現在非推奨となっています。 詳細については、「 [Microsoft Entra Connect V2 の概要](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect-v2)」を参照してください。 Microsoft Entra Connect の任意のバージョンから現在のバージョンにアップグレードできます。 DirSync または ADSync のインプレース アップグレードはサポートされておらず、スウィング移行が必要となります。 DirSync からアップグレードする場合は、「 [Azure AD Sync ツール (DirSync) からのアップグレード」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-dirsync-upgrade-get-started) または 「Swing 移行 」セクションを参照してください。

実際には、古いバージョンをご使用の場合、Microsoft Entra Connect には直接関係のない問題が発生する可能性があります。 何年も運用されてきたサーバーは通常、パッチがいくつも適用されており、そのすべてを把握しきれないことがあります。 12 か月から 18 か月間 (およそ 1 年半) アップグレードを行っていないお客様は、代わりにスウィング アップグレードを検討してください。これが最も慎重でリスクの少ない選択肢です。

Microsoft Entra Connect のアップグレードで使用できる方法は複数あります。

| メソッド | 説明 | 利点 | 短所 |
| --- | --- | --- | --- |
| [自動アップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade) | これは、高速インストールで最も簡単な方法です | - 手動による介入がない | - 自動アップグレード バージョンには最新の機能が含まれていない可能性がある |
| インプレース アップグレード | サーバーが1台しかない場合は、そのサーバーでその場でアップグレードすることができます。 | - 別のサーバーが不要 | - インプレース アップグレード中に問題が発生した場合、新しいリリースまたは構成をロールバックして、準備ができたらアクティブ サーバーを変更することはできません |
| スウィング移行 | 切り替える前に、更新された新しいサーバーを別に構築できます | - 最も安全なアプローチであり、新しいバージョンへの移行がスムーズ - Windows OS (オペレーティング システム) のアップグレードをサポートする - 同期が中断されず、運用環境にリスクを課さない | - 別のサーバーにインストールする必要がある |

アクセス許可の情報については、 [アップグレードに必要なアクセス許可を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions#upgrade)参照してください。

注

新しい Microsoft Entra Connect サーバーが Microsoft Entra ID への変更の同期を開始できるようにしたら、DirSync または Azure AD Sync を使用してロールバックしないでください。Microsoft Entra Connect から DirSync や Azure AD Sync などのレガシ クライアントへのダウングレードはサポートされておらず、Microsoft Entra ID のデータ損失などの問題につながる可能性があります。

### 一括アップグレード

インプレース アップグレードは、Azure AD Sync または Microsoft Entra Connect からの移行に使用できます。 DirSync から移動しても、これは機能しません。

この方法は、サーバーが 1 台でオブジェクトが約 100,000 未満の場合にお勧めします。 標準の同期規則に対する変更があった場合は、アップグレード後にフル インポートと完全同期が実行されます。 この方法により、新しい構成がシステムのすべての既存のオブジェクトに適用されることが確実になります。 同期エンジンのスコープ内のオブジェクトの数によっては、数時間かかることがあります。 通常の差分同期スケジューラー (既定では 30 分ごとに同期) は中断されますが、パスワード同期は継続されます。 インプレース アップグレードは週末に実行するよう検討してください。 新しい Microsoft Entra Connect リリースで標準構成に変更がなかった場合は、通常の差分インポートまたは差分同期が開始します。

[Image: その場でのアップグレード]

既定の同期規則に変更を加えた場合、これらの規則はアップグレード時に既定の構成に戻されます。 アップグレードの間に構成が保持されるようにするには、「既定の構成を [変更するためのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-best-practices-changing-default-configuration)」で説明されているように、変更を行ってください。 既定の同期規則を既に変更している場合は、アップグレード プロセスを開始する前に、 [Microsoft Entra Connect で変更された既定の規則を修正](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-best-practices-changing-default-configuration)する方法を参照してください。

インプレース アップグレード中、アップグレード後に特定の同期アクティビティ (フル インポート手順、完全同期手順など) の実行を必要とする変更が行われる可能性があります。 このようなアクティビティを延期するには、 アップグレード後に完全同期を延期する方法に関するセクションを参照してください。

標準以外のコネクタ (Generic LDAP (Lightweight Directory Access Protocol) コネクタや Generic SQL Connector など) で Microsoft Entra Connect を使用している場合は、インプレース アップグレード後に [Synchronization Service Manager](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-connectors) で対応するコネクタ構成を更新する必要があります。 コネクタの構成を更新する方法の詳細については、「 [コネクタバージョンのリリース履歴 - トラブルシューティング](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-version-history#troubleshooting)」セクションを参照してください。 構成を更新しないと、コネクタのインポートとエクスポートの実行手順が正しく機能しません。 アプリケーション イベント ログに次のエラーが表示されます。

```
Assembly version in AAD Connector configuration ("X.X.XXX.X") is earlier than the actual version ("X.X.XXX.X") of "C:\Program Files\Microsoft Azure AD Sync\Extensions\Microsoft.IAM.Connector.GenericLdap.dll".
```

### スウィング移行

一部のお客様には、アップグレード中に問題が発生し、サーバーをロールバックできない場合、インプレース アップグレードで運用環境にかなりのリスクが発生する可能性があります。 初期同期サイクルには数日かかる可能性があり、この間は差分変更が処理されないので、運用サーバーが 1 台というのも実用的でない場合があります。

このようなシナリオでは、スウィング移行を使用することをお勧めします。 この方法は、Windows Server オペレーティング システムをアップグレードする必要がある場合に使用できます。 さらに、運用環境にプッシュする前にテストする必要がある環境構成を大幅に変更する予定の場合にも使用できます。

アクティブ サーバーが 1 台とステージング サーバーが 1 台、(少なくとも) 2 台のサーバーが必要です。 アクティブ サーバー (次の図の青い実線) では、アクティブな運用負荷を処理します。 ステージングサーバー（紫の破線で示されている）は、新しいリリースまたは構成の準備が整っています。 このサーバーの準備ができたら、アクティブになります。 古くなったバージョンまたは構成がインストールされている前のアクティブ サーバーは、ステージング サーバーになりアップグレードされます。

2 つのサーバーには、それぞれ異なるバージョンを使用できます。 たとえば、使用を停止する予定のアクティブ サーバーでは Azure AD Sync を使用でき、新しいステージング サーバーでは Microsoft Entra Connect を使用できます。 スウィング移行を使用して新しい構成を開発する場合は、2 つのサーバーで同じバージョンを使用することをお勧めします。

[Image: ステージング サーバーの図。]

注

このシナリオでは、3 台または 4 台のサーバーを使用することが望ましい場合があります。 ステージング サーバーがアップグレードされると、 [ディザスター リカバリー](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server#disaster-recovery)用のバックアップ サーバーがありません。 3 台か 4 台のサーバーを使用すると、更新されたバージョンのプライマリ/スタンバイ サーバーのセットを用意でき、引き継ぎ用のステージング サーバーが常に確保できます。

以下の手順は、Azure AD Sync または MIM と Microsoft Entra コネクタのソリューションからの移行にも使用できます。 これらの手順は DirSync では機能しませんが、DirSync の手順と同じスウィング移行方法 (並列デプロイとも呼ばれます) は [、Azure Active Directory Sync (DirSync) のアップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-dirsync-upgrade-get-started)にあります。

#### スイング移行を使ってアップグレードを行う

1. Microsoft Entra Connect サーバーが 1 つしかない場合、AD Sync からアップグレードする場合、または古いバージョンからアップグレードする場合は、新しいバージョンを新しい Windows Server にインストールすることをお勧めします。 既に 2 台の Microsoft Entra Connect サーバーがある場合は、まずステージング サーバーをアップグレードします。 その後、ステージングをアクティブにレベル上げします。 アクティブ/ステージング サーバーのペアで常に同じバージョンを実行することをお勧めしますが、必須ではありません。
2. カスタム構成を作成し、ステージング サーバーにカスタム構成がない場合は、「アクティブ なサーバー からステージング サーバーにカスタム構成を移動する」の手順に従います。
3. 同期エンジンがステージング サーバーで完全なインポートと完全な同期を実行するまで待ちます。
4. サーバーの構成を確認するの「確認」の手順を使用して、新しい構成で予期しない変更が発生していないことを [確認します](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server#verify-the-configuration-of-a-server)。 想定どおりでない場合は、修正し、同期サイクルを実行し、正常に表示されるまでデータを確認します。
5. もう一方のサーバーをアップグレードする前に、ステージング モードに切り替えて、ステージング サーバーをアクティブ サーバーに昇格させます。 これは、サーバーの [構成を確認](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server#verify-the-configuration-of-a-server)するプロセスの最後の手順 "アクティブ サーバーの切り替え" です。
6. ステージング モードになったサーバーを最新リリースにアップグレードします。 前と同じ手順に従って、データと構成をアップグレードします。 Azure AD Sync からアップグレードしている場合は、ここで、以前のサーバーの電源を切って、使用を停止できます。

注

古い Microsoft Entra Connect サーバーを完全に使用停止にすることが重要です。古い同期サーバーがネットワーク上に残っていたり、後で誤って電源が入ったりした場合に、同期の問題が発生したり、トラブルシューティングが困難になったりする可能性があるためです。 このような "悪い" サーバーは、Microsoft Entra データを古い情報で上書きする傾向があります。これは、オンプレミスの Active Directory にアクセスできなくなった (たとえば、コンピューター アカウントの有効期限が切れた、コネクタ アカウントのパスワードが変更された場合など) にもかかわらず、Microsoft Entra ID には接続できるため、同期サイクルごと (たとえば、30 分ごと) に属性値が継続的に元に戻される可能性があるためです。 Microsoft Entra Connect サーバーを完全に使用停止にするには、製品とそのコンポーネントを完全にアンインストールするか、仮想マシンの場合はサーバーを完全に削除してください。

#### カスタム構成のアクティブ サーバーからステージング サーバーへ移動する

アクティブ サーバーの構成を変更してある場合は、新しいステージング サーバーに同じ変更が適用されていることを確認する必要があります。 この移動に役立つには、 [同期設定のエクスポートとインポートに](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-import-export-config)この機能を使用できます。 この機能を使用すると、ネットワーク内の別の Microsoft Entra Connect サーバーとまったく同じ設定で、いくつかのステップで新しいステージング サーバーをデプロイできます。

#### 個々のカスタム同期規則の移動

作成した個々のカスタム同期規則は、PowerShell を使用して移動できます。 両方のシステムで同じ方法で他の変更を適用する必要があり、変更を移行できない場合、両方のサーバーで次の構成を手動で行う必要がある場合があります。

- 同じフォレストへの接続
- ドメインと OU のすべてのフィルター処理
- 同じオプション機能 (パスワード同期やパスワード ライトバックなど)

**カスタム同期規則をコピーする** カスタム同期規則を別のサーバーにコピーするには、次の操作を行います。

1. アクティブなサーバーで **同期規則エディター** を開きます。
2. カスタム規則を選択します。 [ **エクスポート] を選択します**。 メモ帳ウィンドウが表示されます。 一時ファイルを PS1 という拡張子で保存します。 そうすることで、PowerShell スクリプトになります。 PS1 ファイルをステージング サーバーにコピーします。

    [Image: 同期規則エディターのエクスポート ウィンドウを示すスクリーンショット。]
3. コネクタの GUID (グローバル一意識別子) はステージング サーバー上で異なるため、これを変更する必要があります。 GUID を取得するには、**同期規則エディター**を起動し、同じ接続先システムを表す既定の規則のいずれかを選択して、**[エクスポート]** を選択します。 PS1 ファイルの GUID を、ステージング サーバーから取得した GUID に置き換えます。
4. PowerShell プロンプトで、PS1 ファイルを実行します。 これにより、ステージング サーバーにカスタム同期規則が作成されます。
5. すべてのカスタム規則について、これを繰り返します。

### アップグレード後に完全な同期を保留にする方法

インプレース アップグレード中、特定の同期アクティビティ (フル インポート手順、完全同期手順など) の実行を必要とする変更が行われる可能性があります。 たとえば、コネクタ スキーマの変更には **完全なインポート** 手順が必要であり、既定の同期規則の変更では、影響を受けるコネクタで **完全な同期** 手順を実行する必要があります。 アップグレード中、Microsoft Entra Connect が、必要な同期アクティビティを判断し、"オーバーライド" として記録します。 次の同期サイクルで、同期スケジューラはこうしたオーバーライドを取得して、実行します。 オーバーライドは、正常に実行された時点で削除されます。

アップグレード直後にこれらのオーバーライドを実行したくない場合があります。 たとえば、同期されたオブジェクトが多数あり、こうした同期ステップを営業時間後に実行したい場合などです。 こうしたオーバーライドを削除するには、次の手順に従います。

1. アップグレード中に、構成の**完了時に同期プロセスを開始**するオプションを**オフにします**。 これにより同期スケジューラが無効になり、オーバーライドが削除される前に、同期サイクルが自動的に実行されることがなくなります。

    オプション [構成が完了したら、同期プロセスを開始する] がクリアされる必要があるところを強調したスクリーンショット。
2. アップグレードの完了後、次のコマンドレットを実行して、追加されたオーバーライドを確認します: `Get-ADSyncSchedulerConnectorOverride | fl`

    注

    オーバーライドはコネクタ固有です。 次の例では、フル インポート手順と完全同期手順が、オンプレミスの AD コネクタと Microsoft Entra コネクタの両方に追加されます。

    [Image: アップグレード後にフル同期を無効にする]
3. 追加された既存のオーバーライドを書き留めてください。
4. 任意のコネクタでフル インポートと完全同期の両方に対するオーバーライドを削除するには、次のコマンドレットを実行します。`Set-ADSyncSchedulerConnectorOverride -ConnectorIdentifier <Guid-of-ConnectorIdentifier> -FullImportRequired $false -FullSyncRequired $false`

    すべてのコネクタでオーバーライドを削除するには、次の PowerShell スクリプトを実行します。

    ```
    foreach ($connectorOverride in Get-ADSyncSchedulerConnectorOverride)
    {
        Set-ADSyncSchedulerConnectorOverride -ConnectorIdentifier $connectorOverride.ConnectorIdentifier.Guid -FullSyncRequired $false -FullImportRequired $false
    }
    ```
5. スケジューラを再開するには、次のコマンドレットを実行します。`Set-ADSyncScheduler -SyncCycleEnabled $true`

    重要

    必要な同期手順は、できるだけ早く実行してください。 Synchronization Service Manager を使用してこの手順を手動で実行するか、Set-ADSyncSchedulerConnectorOverride コマンドレットを使用して、オーバーライドを戻すことができます。

任意のコネクタでフル インポートと完全同期の両方に対するオーバーライドを追加するには、次のコマンドレットを実行します。`Set-ADSyncSchedulerConnectorOverride -ConnectorIdentifier <Guid> -FullImportRequired $true -FullSyncRequired $true`

### サーバーのオペレーティング システムをアップグレードする

Microsoft Entra Connect サーバーのオペレーティング システム (OS) をアップグレードする必要がある場合、推奨される方法は、目的のオペレーティング システムで新しいサーバーを準備し、 スウィング移行を実行することです。

ただし、これが不可能な場合は、次のインプレース OS アップグレードがサポートされます。

| 初期 OS | サポートされるインプレース アップグレード OS |
| --- | --- |
| Windows Server 2016 | Windows Server 2025 |
| Windows Server 2019 | Windows Server 2025 |
| Windows Server 2022 | Windows Server 2025 |

### トラブルシューティング

次のセクションには、Microsoft Entra Connect のアップグレード時に問題が発生した場合に使用できるトラブルシューティングと情報が含まれています。

#### Microsoft Entra Connect のアップグレード中に Microsoft Entra コネクタが見つからないエラーが発生する

Microsoft Entra Connect を以前のバージョンからアップグレードする際に、アップグレードの最初の段階で次のエラーに遭遇することがあります。

[Image: エラー]

このエラーは、Microsoft Entra コネクタ (ID b891884f-051e-4a83-95af-2544101c9083) が現在の Microsoft Entra Connect の構成に存在しないことが原因で発生します。 それが事実であるかどうかを確認するには、PowerShell ウィンドウを開いて `Get-ADSyncConnector -Identifier b891884f-051e-4a83-95af-2544101c9083` コマンドレットを実行します。

```
PS C:\> Get-ADSyncConnector -Identifier b891884f-051e-4a83-95af-2544101c9083
Get-ADSyncConnector : Operation failed because the specified MA could not be found.
At line:1 char:1
+ Get-ADSyncConnector -Identifier b891884f-051e-4a83-95af-2544101c9083
+ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    + CategoryInfo          : ReadError: (Microsoft.Ident...ConnectorCmdlet:GetADSyncConnectorCmdlet) [Get-ADSyncConne
   ctor], ConnectorNotFoundException
    + FullyQualifiedErrorId : Operation failed because the specified MA could not be found.,Microsoft.IdentityManageme
   nt.PowerShell.Cmdlet.GetADSyncConnectorCmdlet

```

PowerShell コマンドレットは、 **指定された MA が見つからなかったエラーを**報告します。

このエラーは、現在の Microsoft Entra Connect 構成がアップグレードでサポートされていないために発生します。

新しいバージョンの Microsoft Entra Connect をインストールする場合は、Microsoft Entra Connect ウィザードを閉じ、既存の Microsoft Entra Connect をアンインストールして、新しい Microsoft Entra Connect のクリーン インストールを実行してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/howto-troubleshoot-upn-changes"} -->
## Microsoft Entra ID でのユーザー プリンシパル名の変更の計画とトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/howto-troubleshoot-upn-changes
- Service: entra-id / hybrid-connect
- Article date: 2024-04-29
- Summary: ユーザー プリンシパル名 (UPN) の変更に関する既知の問題と軽減策について説明します。

UserPrincipalName (UPN) 属性は、ユーザー アカウントのインターネット通信標準です。 UPN は次のもので構成されます。

- **プレフィックス**: ユーザー アカウント名
- **サフィックス**: ドメイン ネーム サーバー (DNS) ドメイン名

プレフィックスとサフィックスは、"@" 記号を使用して結合されます。 たとえば、someone@example.com のようにします。 計画の間に、UPN がディレクトリ フォレスト内のセキュリティ プリンシパル オブジェクト間で一意であることを確認します。

注釈

この記事では、UPN がユーザー識別子であると想定しています。 この記事では、UPN の変更の計画と、変更によって発生する可能性がある問題からの復旧について説明します。 開発者には、UPN やメール アドレスではなく、不変の識別子であるユーザーの objectID を使うことをお勧めします。

### UPN を変更する理由

値がユーザーの UPN である場合、サインイン ページでユーザーがメール アドレスの入力を求められることがよくあります。 そのため、ユーザーのプライマリ メール アドレスが変わったら、ユーザー UPN を変更します。 一般に、ユーザーのプライマリ メール アドレスは次の理由で変わります。

- 従業員の名前の変更
- 従業員の異動
- サフィックスに影響する再構築に伴う変更
- 合併または買収に伴う変更

#### UPN のプレフィックスとサフィックスの変更

プライマリ メール アドレスが変更されたときはユーザー UPN を変更することをお勧めします。 Active Directory から Microsoft Entra ID への初期同期の間に、ユーザーのメール アドレスと UPN が同じであることを確認します。 次のプレフィックスとサフィックスの変更の例を参照してください。

プレフィックスの変更の例:

- **BSimon**@contoso.com から **BJohnson**@contoso.com
- **Bsimon**@contoso.com から **Britta.Simon**@contoso.com

サフィックスの変更の例:

- Britta.Simon@**contoso.com** から Britta.Simon@**contosolabs.com**
- Britta.Simon@corp.**contoso.com** から Britta.Simon@**labs.contoso.com**

#### Active Directory での UPN

Active Directory では、既定の UPN サフィックスは、ユーザー アカウントを作成した DNS です。 ほとんどの場合、このドメイン名を企業ドメインとして登録します。 contoso.com ドメインにユーザー アカウントを作成すると、既定の UPN は username@contoso.com になります。 Active Directory のドメインと信頼を使用して、UPN サフィックスをさらに追加します。 たとえば、labs.contoso.com を追加し、それを反映するようにユーザー UPN とメールを変更すると、結果は username@labs.contoso.com になります。

[カスタム ドメイン名をテナントに追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)ことができます。

重要

Active Directory でサフィックスを変更する場合は、Microsoft Entra ID で一致するカスタム ドメイン名を追加して確認します。

[Image: [カスタム ドメイン名] の [カスタム ドメインの追加] オプションのスクリーンショット。]

#### Microsoft Entra ID の UPN

ユーザーは、userPrincipalName 属性値を使用して Microsoft Entra ID にサインインします。

Microsoft Entra ID とオンプレミスの Active Directory を使うと、ユーザー アカウントは Microsoft Entra Connect サービスで同期されます。 Microsoft Entra Connect ウィザードではオンプレミスの Active Directory の userPrincipalName 属性が Microsoft Entra ID の UPN として使用されます。 カスタム インストールでは、これを別の属性に変更できます。

注釈

ユーザーと組織の UserPrincipalName を更新するプロセスを定義します。

ユーザー アカウントを Active Directory から Microsoft Entra ID に同期するときは、Active Directory の UPN が Microsoft Entra ID 内の検証済みドメインにマップされていることを確認します。 userPrincipalName 属性値が Microsoft Entra ID の検証済みドメインに対応しない場合は、同期によってサフィックスが .onmicrosoft.com に置き換えられます。

#### UPN の変更の一括ロールアウト

UPN の一括変更をテストするには、UPN を元に戻すためのテスト済みのロールバック計画を作成します。 パイロットでは、組織のロールを含む小さなユーザー セットと、アプリまたはデバイスのセットを対象にします。 このプロセスは、ユーザー エクスペリエンスを理解するのに役立ちます。 関係者やユーザーへの変更の通知に、この情報を含めます。

[Microsoft Entra の展開プラン](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)に関する詳細を参照してください。

個々のユーザーの UPN を変更する手順を作成することをお勧めします。 既知の問題と回避策に関するドキュメントを含めます。 詳細については、次のセクションを参照してください。

### SaaS と LoB アプリの問題

サービスとしてのソフトウェア (SaaS) および基幹業務 (LoB) の各アプリケーションでは、多くの場合、UPN を利用して、ユーザーの検索や、ロールなどのユーザー プロファイル情報の格納が行われます。 UPN の変更の影響を受ける可能性があるアプリケーションでは、Just-In-Time (JIT) プロビジョニングを使用して、ユーザーが最初にアプリにサインインするときにユーザー プロファイルを作成します。

詳細情報:

- [SaaS とは](https://azure.microsoft.com/overview/what-is-saas/)
- [Microsoft Entra ID でのアプリ プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)

#### 既知の問題: 破損した関係、新しいプロファイル

ユーザーの UPN を変更すると、Microsoft Entra ユーザーと、アプリケーション上のユーザー プロファイルの間の関係が破損する可能性があります。 アプリケーションで JIT プロビジョニングを使っている場合、新しいユーザー プロファイルが作成される可能性があります。 その場合は、アプリケーション管理者が手動で変更して関係を修正します。

**回避策: 自動プロビジョニング**

サポートされているクラウド アプリケーションで、ユーザー ID を作成、保守、削除するには、Microsoft Entra ID の自動アプリ プロビジョニングを使います。 アプリケーションで UPN を更新するには、アプリケーションに自動ユーザー プロビジョニングを構成します。 アプリケーションをテストして、UPN の変更が成功することを検証します。 開発者は、自動ユーザー プロビジョニングを有効にするため、クロスドメイン ID 管理システム (SCIM) のサポートをアプリケーションに追加できます。

詳細情報:

- [Microsoft Entra ID でのアプリ プロビジョニングとは](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)
- [チュートリアル: Microsoft Entra ID の SCIM エンドポイントのプロビジョニングを開発および計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)

### マネージド デバイスに関する問題

クラウドとオンプレミスのリソースの全体でシングル サインオン (SSO) を使ってユーザーの生産性を最大にするには、デバイスを Microsoft Entra ID に取り込みます。

詳しくは、「[デバイス ID とは](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)」をご覧ください。

#### Microsoft Entra 参加済みデバイス

規模や業界に関係なく、組織は Microsoft Entra 参加済みデバイスを展開できます。 Microsoft Entra の参加はハイブリッド環境でも動作し、クラウドとオンプレミスのアプリとリソースにアクセスできます。 Microsoft Entra 参加済みデバイスは、Microsoft Entra ID に参加しています。 ユーザーは、組織 ID を使用してデバイスにサインインします。

詳しくは、「[Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)」をご覧ください。

**既知の問題: SSO**

認証を Microsoft Entra ID に依存するアプリケーションで、SSO に関する問題がユーザーに発生する可能性があります。 この問題は、Windows 10 の 2020 年 5 月の更新プログラムで修正されました。

**回避策**

1. UPN の変更が Microsoft Entra ID に同期される時間を設けます。
2. 新しい UPN が [Microsoft Entra 管理センター](https://entra.microsoft.com)に表示されることを確認します。
3. 新しい UPN でサインインするには **[その他のユーザー]** を選ぶようユーザーに指示します。
4. Microsoft Graph PowerShell の [Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser) を使って確認します。

    注釈

    ユーザーが新しい UPN でサインインした後、**[職場または学校へのアクセス]** の Windows 設定に以前の UPN への参照が表示される場合があります。

    [Image: サインイン画面の User-1 と Other-user ドメインのスクリーンショット。]

#### Microsoft Entra ハイブリッド参加済みデバイスに関連する問題

Microsoft Entra ハイブリッド参加済みデバイスは、Active Directory と Microsoft Entra ID に参加しています。 環境にオンプレミスの Active Directory フットプリントが含まれる場合は、Microsoft Entra ハイブリッド参加を実装します。

詳しくは、「[Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)」をご覧ください。

**既知の問題: Windows 10 Microsoft Entra ハイブリッド参加済みデバイス**

Windows 10 の Microsoft Entra ハイブリッド参加済みデバイスでは、予期しない再起動とアクセスの問題が発生します。 新しい UPN が Microsoft Entra ID と同期する前にユーザーが Windows にサインインした場合、認証に Microsoft Entra ID を使用するアプリで SSO の問題が発生する可能性があります。 このシナリオは、ユーザーが Windows セッション内にいる場合に発生する可能性があります。 この状況は、ハイブリッド参加済みデバイスを使ってリソースにアクセスするように条件付きアクセスが構成されている場合に発生します。 さらに、次のメッセージで、1 分後に強制的に再起動される可能性があります。

*PC は 1 分で自動的に再起動されます。 Windows で問題が発生したため、再起動する必要があります。 今すぐこのメッセージを閉じて、作業中のデータを保存してください。*

この問題は、Windows 10 の 2020 年 5 月の更新プログラムで修正されました。

**回避策**

1. Microsoft Entra ID からデバイスの登録を解除します。
2. 再起動。
3. デバイスは Microsoft Entra ID に参加します。
4. サインインするには、ユーザーは **[その他のユーザー]** を選びます。

デバイスの Microsoft Entra ID への参加を解除するには、コマンド プロンプトでコマンド dsregcmd/leave を実行します

注釈

Windows Hello for Business を使っている場合、ユーザーは Windows Hello for Business に再登録します。

ヒント

Windows 7 と 8.1 のデバイスは、この問題による影響を受けません。

### Microsoft Authenticator に関する問題

組織でアプリケーションにサインインしてデータにアクセスするために Authenticator が必要な場合、ユーザー名がアプリで表示される可能性があります。 ただし、ユーザーが登録を完了するまで、アカウントは検証方法ではありません。

[Authenticator の使用方法](https://support.microsoft.com/account-billing/how-to-use-the-microsoft-authenticator-app-9783c865-0308-42fb-a519-8cf666fe0acc)に関する記事をご覧ください。

Authenticator アプリには、4 つの主要な機能があります。

- プッシュ通知または確認コードによる**多要素認証**
- **認証ブローカー**: iOS と Android デバイスでの、ブローカー認証を使用するアプリケーションの SSO 用
    - [Microsoft Authentication Library (MSAL) を使用して Android でアプリ間 SSO を有効にする](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-android-single-sign-on)
- **デバイスの登録または職場参加**は、Microsoft Entra ID に対するものであり、Intune アプリ保護やデバイスの登録および管理の要件となっています。
- **電話でのサインイン** MFA とデバイスの登録が必要です

#### Android デバイスでの多要素認証

Authenticator を帯域外検証に使用します。 多要素認証は、ユーザーへの自動通話またはショート メッセージ サービス (SMS) ではなく、ユーザー デバイスの Authenticator に通知をプッシュします。

1. ユーザーは、**[承認]** を選ぶか、PIN や生体認証を入力します。
2. ユーザーは **[認証]** を選びます。

「[しくみ: Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)」をご覧ください。

**既知の問題: 通知を受け取らない**

ユーザーの UPN を変更したとき、ユーザー アカウントに以前の UPN が表示されます。 通知を受け取らない可能性があります。 代わりに、確認コードを使います。

[Authenticator に関する一般的な質問](https://prod.support.services.microsoft.com/account-billing/common-questions-about-the-microsoft-authenticator-app-12d283d1-bcef-4875-9ae5-ac360e2945dd)の一覧をご覧ください。

**回避策**

1. 通知が表示される場合は、無視するようにユーザーに指示します。
2. Authenticator を開きます。
3. **[通知の確認]** を選びます。
4. MFA のプロンプトを承認します。
5. アカウントの UPN が更新されます。

注釈

更新された UPN が新しいアカウントとして表示される場合があります。 この変更は、他の Authenticator 機能が原因です。

#### ブローカー認証

Android と iOS では、Authenticator などのブローカーによって次のことが有効になります。

- SSO: ユーザーは、アプリケーションごとにサインインしません
- デバイスの識別: ブローカーは、デバイスがワークプレースに参加したときに作成されたデバイス証明書にアクセスします。
- アプリケーション ID の検証: アプリケーションはブローカーを呼び出すときにリダイレクト URL を渡し、ブローカーがそれを検証します

詳細情報:

- [Microsoft Entra 条件付きアクセスに関するドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/)

**既知の問題: ユーザー プロンプト**

アプリケーションによって渡される `login_hint` とブローカー上の UPN が一致しないため、ブローカーで支援されたサインインにより、いっそう多くの認証プロンプトがユーザーに表示されます。

**回避策**

ユーザーは、Authenticator からアカウントを手動で削除し、ブローカーで支援されたアプリケーションから新しいサインインを始めます。 初期認証の後、アカウントが追加されます。

#### デバイス登録

Authenticator がデバイスを Microsoft Entra ID に登録し、デバイスは Microsoft Entra ID に対して認証できるようになります。 この登録は、次の要件になっています。

- Intune アプリ保護 (Intune App Protection)
- Intune デバイスの登録
- 電話によるサインイン

**既知の問題: 新しいアカウントが表示される**

UPN を変更すると、新しい UPN を使用する新しいアカウントが Authenticator に表示されます。 以前の UPN を使用するアカウントは残っています。 また、以前の UPN は、アプリ設定の [デバイスの登録] にも表示されます。 [デバイスの登録] または依存するシナリオの機能に変更はありません。

**回避策**

ユーザーが Authenticator での以前の UPN への参照を削除するには、Authenticator から以前のアカウントと新しいアカウントを削除します。 MFA に再登録し、デバイスに再び参加します。

#### 電話によるサインイン

電話によるサインインを使って、パスワードを使わずに Microsoft Entra ID にサインインします。 ユーザーは、Authenticator を使って、MFA に登録してから、電話によるサインインを有効にします。 デバイスは、Microsoft Entra ID で登録されます。

**既知の問題: 通知がない**

ユーザーは、通知を受け取らなかったため、電話によるサインインを使用できません。 ユーザーが **[通知の確認]** を選択すると、エラーが表示されます。

**回避策**

ユーザーは、電話によるサインインが有効になっているアカウントで、ドロップダウン メニューの **[電話によるサインインを無効にする]** を選びます。

### モバイル デバイス管理

#### 既知の問題: デバイスの再登録が必要

組織がモバイル デバイス管理と Intune アプリまたはポータル サイト アプリを使用してデバイスを管理している場合、UPN の変更中にデバイスの登録に回復性がありません。 UPN を変更すると、デバイスは Entra で登録解除済みとして検出され、ユーザーはサインインし、管理と条件付きアクセスのためにデバイスをもう一度登録して作業を続行する必要があります。 登録が完了するまで、ユーザーはこのデバイス上の企業リソースにアクセスできない場合があります。

詳細情報:

- [Microsoft Intune のデバイス登録ガイド](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/deployment-guide-enrollment)
- [Microsoft Intuneコンプライアンス ポリシーで条件付きアクセスを使用する](https://learn.microsoft.com/ja-jp/mem/intune/protect/conditional-access)

**回避策**

UPN が変更された後、エンド ユーザーはサインインし、アプリ内のプロンプトに従って再登録する必要があります。

### セキュリティ キー (FIDO2) に関する問題

#### 既知の問題: アカウントの選択

複数のユーザーが同じキーで登録されていると、アカウント選択に以前の UPN が表示されます。 UPN の変更は、セキュリティ キーを使用したサインインには影響しません。

**回避策**

以前の UPN への参照を削除するには、ユーザーがセキュリティ キーをリセットして再登録します。

[パスワードレスのセキュリティ キー サインインの有効化、既知の問題、UPN の変更](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key#known-issues)。

### OneDrive に関する問題

OneDrive のユーザーは、UPN の変更後に問題が発生する可能性があります。

「[UPN の変更による OneDrive URL および OneDrive 機能への影響](https://learn.microsoft.com/ja-jp/sharepoint/upn-changes)」をご覧ください。

### Teams 会議メモに関する問題

Teams 会議メモを使用して、メモを作成し、共有します。

**既知の問題: メモにアクセスできない**

ユーザーの UPN が変更されると、以前の UPN で作成された会議メモに、Microsoft Teams や会議メモの URL でアクセスできなくなります。

**回避策**

UPN の変更後は、ユーザーは OneDrive からメモをダウンロードできます。

1. **[マイ ファイル]** にアクセスします。
2. **[Microsoft Teams データ]** を選択します。
3. **[Wiki]** を選択します。

UPN 変更後に作成された新しい会議ノートは影響を受けません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication"} -->
## Microsoft Entra ID でフェデレーションからクラウド認証に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/migrate-from-federation-to-cloud-authentication
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事には、フェデレーションからクラウド認証へのハイブリッド ID 環境の移行に関する情報が含まれています

この記事では、Microsoft Entra の[パスワード ハッシュ同期 (PHS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) または[パススルー認証 (PTA)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) のいずれかを使用してクラウド ユーザー認証をデプロイする方法について学習します。 [Active Directory フェデレーション サービス (AD FS)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-fed) からクラウド認証方法に移行する場合のユース ケースを示しますが、ガイダンスの大部分は他のオンプレミス システムにも適用されます。

続行する前に、[適切な認証方法の選択](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)に関するガイドを確認し、組織に最適な方法を比較してください。

クラウド認証には PHS を使用することをお勧めします。

### 段階的なロールアウト

段階的ロールアウトは、ドメインをカットオーバーする前に、Microsoft Entra 多要素認証、条件付きアクセス、Microsoft Entra ID 保護による漏洩した資格情報の保護、ID ガバナンスなどのクラウド認証機能を使用して、一連のユーザーを選択的にテストするための便利な方法です。

[サポートされるシナリオ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#supported-scenarios)と[サポートされていないシナリオ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#unsupported-scenarios)については、段階的ロールアウトの実装計画を参照してください。 ドメインを切り替える前に、段階的なロールアウトを使用してテストすることをお勧めします。

### 移行プロセス フロー

[Image: クラウド認証に移行するためのプロセス フロー]

### 前提条件

移行を開始する前に、これらの前提条件を満たしていることを確認してください。

#### 必要なロール

段階的なロールアウトを使用するには、テナントのハイブリッド ID 管理者である必要があります。

#### Microsoft Entra Connect サーバー をステップ アップする

[Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) (Microsoft Entra Connect) をインストールするか、[最新バージョンにアップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version)します。 Microsoft Entra Connect サーバーをステップアップすると、AD FS からクラウド認証方法への移行にかかる時間を数時間から数分に短縮できる可能性があります。

#### 現在のフェデレーション設定をドキュメント化する

現在のフェデレーション設定を確認するには、[Get-MgDomainFederationConfiguration](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdomainfederationconfiguration?view=graph-powershell-1.0&viewFallbackFrom=graph-powershell-beta&preserve-view=true) を実行します。

```powershell
Get-MgDomainFederationConfiguration -DomainID yourdomain.com
```

ご利用のフェデレーションの設計とデプロイのドキュメント用にカスタマイズされた可能性のある、すべての設定を確認します。 具体的には、**PreferredAuthenticationProtocol**、**federatedIdpMfaBehavior**、**SupportsMfa** (**federatedIdpMfaBehavior** が設定されていない場合)、**PromptLoginBehavior** 内のカスタマイズを探します。

#### フェデレーション設定をバックアップする

このデプロイでは、AD FS ファーム内の他の証明書利用者は変更されませんが、設定をバックアップできます。

- Microsoft [AD FS Rapid Restore Tool](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/ad-fs-rapid-restore-tool) を使用して、既存のファームを復元するか、新しいファームを作成します。
- 次の PowerShell の例を使用して、Microsoft 365 ID プラットフォームの証明書利用者信頼と、追加したすべての関連カスタム要求規則をエクスポートします。

    ```powershell
    
    (Get-AdfsRelyingPartyTrust -Name "Microsoft Office 365 Identity Platform") | Export-CliXML "C:\temp\O365-RelyingPartyTrust.xml"
    
    ```

### プロジェクトを計画する

テクノロジ プロジェクトが失敗した場合、その原因は通常、影響、結果、および責任に対する想定の不一致です。 これらの落とし穴を回避するには、[適切な利害関係者が担当していることを確認](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)し、プロジェクトにおけるその利害関係者の役割がよく理解されていることを確認します。

#### 連絡を計画する

クラウド認証に移行した後、Microsoft Entra ID を通じて認証される Microsoft 365 およびその他のリソースにアクセスするためのユーザーのサインイン エクスペリエンスが変更されます。 ネットワークの外部のユーザーには、Microsoft Entra サインイン ページのみが表示されます。

ユーザー エクスペリエンスがどのように変わるのか、いつ変わるのか、および問題が発生したときにサポートを受ける方法について、ユーザーに事前に連絡します。

#### メンテナンス期間の計画

先進認証クライアント (Office 2016 と Office 2013、iOS、および Android アプリ) では、リソースへのアクセスを継続するための新しいアクセス トークンを取得するために、AD FS に戻るのではなく、有効な更新トークンが使用されます。 これらのクライアントに、ドメイン変換プロセスの結果としてパスワードの入力を求めるメッセージを表示する必要はありません。 クライアントは、追加の構成を行わなくても機能し続けます。

注

フェデレーション認証からクラウド認証に移行する場合、ドメインをフェデレーションからマネージドに変換するプロセスには、最大 60 分かかる場合があります。 このプロセス中、ユーザーは、[Microsoft Entra 管理センター](https://entra.microsoft.com)または Microsoft Entra ID で保護されたその他のブラウザー ベースのアプリケーションへの新しいログインで資格情報を求められない場合もあります。 この遅延をメンテナンス期間に含めることをお勧めします。

#### ロールバックのための計画

ヒント

ロールバックする必要がある場合は、営業時間外にドメインのカットオーバーを計画してください。

ロールバックを計画する場合は、ドキュメント化された現在のフェデレーション設定を使用して、[フェデレーションの設計とデプロイに関するドキュメント](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/deployment/windows-server-2012-r2-ad-fs-deployment-guide)を確認してください。

ロールバック プロセスでは、[New-MgDomainFederationConfiguration](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdomainfederationconfiguration?view=graph-powershell-1.0&preserve-view=true) コマンドレットを使用して、マネージド ドメインをフェデレーション ドメインに変換する必要があります。 必要に応じて、追加の要求規則を構成します。

### 移行に関する注意事項

移行に関する主な注意事項を次に示します。

#### カスタマイズ設定を計画する

onload.js ファイルを Microsoft Entra ID で複製することはできません。 AD FS インスタンスが大幅にカスタマイズされ、onload.js ファイル内の特定のカスタマイズ設定に依存している場合は、Microsoft Entra ID が現在のカスタマイズ要件を満たしていることを確認し、適切に計画します。 これらの今後の変更をユーザーに伝えます。

##### サインイン エクスペリエンス

Microsoft Entra サインイン エクスペリエンスをカスタマイズすることはできません。 Microsoft Entra ID にサインインするには、ユーザーが以前にサインインした方法に関係なく、ユーザー プリンシパル名 (UPN) や電子メールなどの完全修飾ドメイン名が必要です。

##### 組織のブランド化

[Microsoft Entra サインイン ページをカスタマイズ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)できます。 サインイン ページでの AD FS からの一部の視覚的変更は、変換後に行う必要があります。

注

無料の Microsoft Entra ID ライセンスでは、Microsoft 365 ライセンスを持っている場合を除いて、組織のブランド化を使用できません。

#### 条件付きアクセス ポリシーを計画する

現在認証に条件付きアクセスを使用しているかどうか、または AD FS でアクセス制御ポリシーを使用しているかどうかを評価します。

AD FS アクセス制御ポリシーを同等の Microsoft Entra [条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)と [Exchange Online のクライアント アクセス規則](https://learn.microsoft.com/ja-jp/exchange/clients-and-mobile-in-exchange-online/client-access-rules/client-access-rules)に置き換えることを検討します。 条件付きアクセスには、Microsoft Entra ID またはオンプレミスのグループを使用できます。

**レガシ認証を無効にする** - レガシ認証プロトコルに関連してリスクが増加するため、[レガシ認証をブロックする条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication)を作成します。

#### MFA のサポートを計画する

フェデレーション ドメインに対しては、MFA を Microsoft Entra 条件付きアクセスまたはオンプレミス フェデレーション プロバイダーによって適用することができます。 セキュリティ設定の **federatedIdpMfaBehavior** を構成して、Microsoft Entra 多要素認証のバイパスを防ぐ保護を有効にできます。 Microsoft Entra テナント内のフェデレーション ドメインの保護を有効にします。 フェデレーション ユーザーが、MFA を必要とする条件付きアクセス ポリシーで管理されているアプリケーションにアクセスする場合は、Microsoft Entra 多要素認証が常に実行されるようにします。 これには、オンプレミスの MFA が実行されたフェデレーション トークン クレームをフェデレーション ID プロバイダーが発行した場合でも、Microsoft Entra 多要素認証を実行することが含まれます。 Microsoft Entra 多要素認証を毎回適用すると、悪意のあるアクターは、その ID プロバイダーが既に MFA を実行したことを模倣して Microsoft Entra 多要素認証をバイパスできなくなるため、サードパーティの MFA プロバイダーを使ってフェデレーション ユーザーに対する MFA を実行するのでない限り、この方法を強くお勧めします。

次の表で、各オプションの動作について説明します。 詳細については、**federatedIdpMfaBehavior** に関する記事を参照してください。

| 価値 | 説明 |
| --- | --- |
| acceptIfMfaDoneByFederatedIdp | Microsoft Entra ID は、フェデレーション ID プロバイダーが実行する MFA を受け入れます。 MFA がフェデレーション ID プロバイダーによって実行されなかった場合、Microsoft Entra によって実行されます。 |
| フェデレーテッドIDPによるMFAの強制 | Microsoft Entra ID は、フェデレーション ID プロバイダーが実行する MFA を受け入れます。 フェデレーション ID プロバイダーによって MFA が実行されなかった場合、その要求が MFA を実行するフェデレーション ID プロバイダーにリダイレクトされます。 |
| rejectMfaByFederatedIdp | Microsoft Entra では常に MFA を実行し、フェデレーション ID プロバイダーによって実行される MFA は拒否します。 |

**federatedIdpMfaBehavior** 設定は、**Set-MsolDomainFederationSettings MSOnline v1 PowerShell コマンドレット**の [SupportsMfa](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/new-mgdomainfederationconfiguration?view=graph-powershell-1.0&preserve-view=true) プロパティの進化したバージョンです。

**SupportsMfa** プロパティを既に設定しているドメインの場合、これらの規則によって、**federatedIdpMfaBehavior** と **SupportsMfa** の連携方法が決まります。

- **federatedIdpMfaBehavior** と **SupportsMfa** の切り替えはサポートされていません。
- **federatedIdpMfaBehavior** プロパティが設定されると、Microsoft Entra ID は **SupportsMfa** 設定を無視します。
- **federatedIdpMfaBehavior** プロパティが一度も設定されていない場合、Microsoft Entra ID は引き続き **SupportsMfa** 設定に従います。
- **federatedIdpMfaBehavior** と **SupportsMfa** のどちらも設定されていない場合、Microsoft Entra の既定動作は `acceptIfMfaDoneByFederatedIdp` になります。

保護の状態を確認するには、 [Get-MgDomainFederationConfiguration](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdomainfederationconfiguration?viewFallbackFrom=graph-powershell-beta&preserve-view=true&view=graph-powershell-1.0) を実行します。

```powershell
Get-MgDomainFederationConfiguration -DomainId yourdomain.com
```

### 実装を計画する

このセクションには、サインイン方法を切り替えてドメインを変換する前の事前作業が含まれています。

#### 段階的なロールアウトに必要なグループを作成する

*段階的なロールアウトを使用していない場合は、この手順をスキップします。*

段階的ロールアウト用のグループを作成し、条件付きアクセス ポリシーを追加する場合は条件付きアクセス ポリシー用のグループも作成します。

クラウド専用グループとも呼ばれる、Microsoft Entra ID で管理されているグループを使用することをお勧めします。 MFA へのユーザーの移動と、条件付きアクセス ポリシーの両方に、Microsoft Entra セキュリティ グループまたは Microsoft 365 グループを使用できます。 詳細については、「[Microsoft Entra セキュリティ グループの作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)」と、「[管理者向け Microsoft 365 グループの概要](https://learn.microsoft.com/ja-jp/microsoft-365/admin/create-groups/office-365-groups)」を参照してください。

グループ内のメンバーは、段階的ロールアウトに対して自動的に有効になります。 段階的ロールアウトでは、ネストされたグループと動的メンバーシップグループはサポートされていません。

#### SSO の事前作業

使用する SSO のバージョンは、デバイスの OS と参加状態によって異なります。

- **Windows 10、Windows Server 2016、およびそれ以降のバージョン**の場合、[Microsoft Entra 参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-primary-refresh-token)、[Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-directory-join)、または [Microsoft Entra 登録済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)で[プライマリ更新トークン (PRT)](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-device-registration) 経由の SSO を使用することをお勧めします。
- **macOS および iOS デバイスの場合**は、[Apple デバイス用の Microsoft Enterprise SSO プラグイン](https://learn.microsoft.com/ja-jp/entra/identity-platform/apple-sso-plugin)から SSO を使うことをお勧めします。 この機能を使うには、お使いの Apple デバイスが MDM によって管理されている必要があります。 MDM として Intune を使う場合は、[Apple 用 Microsoft Enterprise SSO プラグインの Intune デプロイ ガイド](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-macos)に関する記事に従ってください。 それ以外の MDM を使う場合は、[Jamf Pro と汎用 MDM のデプロイ ガイド](https://learn.microsoft.com/ja-jp/mem/intune/configuration/use-enterprise-sso-plug-in-ios-ipados-macos)に関する記事に従ってください。
- **Windows 7 および 8.1 デバイスの場合**、ドメインに参加している[シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) を使用して、コンピューターを Microsoft Entra に登録することをお勧めします。 Windows 10 デバイスの場合のように、これらのアカウントを同期させる必要はありません。 ただし、[PowerShell を使用してシームレス SSO の事前作業](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#prework-for-seamless-sso)を完了する必要があります。

#### PHS と PTA の事前作業

サインイン方法の選択に応じて、[PHS](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#prework-for-password-hash-sync) または [PTA の事前作業](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#prework-for-pass-through-authentication)を完了します。

### ソリューションを実装する

最後に、サインイン方法を計画どおりに PHS または PTA に切り替え、ドメインをフェデレーションからクラウド認証に変換します。

#### 段階的なロールアウトを使用する場合

段階的なロールアウトを使用している場合は、次のリンクの手順に従ってください。

1. [テナントの特定の機能の段階的なロールアウトを有効にします。](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#enable-staged-rollout)
2. テストが完了したら、ドメインをフェデレーションからマネージドに変換します。

#### 段階的なロールアウトを使用しない場合

この変更を有効にするには、2 つのオプションがあります。

- **オプション A:** Microsoft Entra Connect を使用して切り替えます。

    最初に Microsoft Entra Connect を使用して AD FS/ping フェデレーション環境を構成した場合に使用できます。
- **オプション B:** Microsoft Entra Connect と PowerShell を使用して切り替えます

    最初に Microsoft Entra Connect を使用してフェデレーション ドメインを構成しなかった場合、またはサードパーティのフェデレーション サービスを使用している場合に使用できます。

これらのオプションのいずれかを選択するには、現在の設定を把握している必要があります。

##### 現在の Microsoft Entra Connect の設定を確認する

1. 少なくともハイブリッド ID 管理者として、Microsoft Entra 管理センターにサインインします。
2. **Entra ID**&gt;、**Entra Connect**&gt;、そして**Cloud 同期**にアクセスします。

    Microsoft Entra Connect Cloud Sync のホームページを示すスクリーンショット。

1. 次の図に示す **USER SIGN\_IN** 設定を確認します。

[Image: 現在の Microsoft Entra Connect の設定を確認する]

**フェデレーションが構成された方法を確認するには:**

1. Microsoft Entra Connect サーバー上で、**[Microsoft Entra Connect]** を開き **[設定]** を選択します。
2. **[追加のタスク] &gt; [フェデレーションの管理]** で、**[フェデレーション構成の表示]** を選択します。

    [Image: [フェデレーションの管理] を表示する]

    このセクションに AD FS 構成が表示される場合は、AD FS が最初に Microsoft Entra Connect を使用して構成されたと見なすことができます。 例として、次の図を参照してください。

    [Image: AD FS 構成を表示する]

    AD FS が現在の設定の一覧に表示されていない場合は、PowerShell を使用して、ドメインをフェデレーション ID からマネージド ID に手動で変換する必要があります。

##### オプション A

**Microsoft Entra Connect を使用してフェデレーションから新しいサインイン方法に切り替える**

1. Microsoft Entra Connect サーバー上で、**[Microsoft Entra Connect]** を開き **[設定]** を選択します。
2. **[追加のタスク]** ページで、 **[ユーザー サインインの変更]** を選択し、 **[次へ]** を選択します。

    [Image: [追加のタスク] を表示する]
3. **[Microsoft Entra ID に接続]** ページで、全体管理者アカウントの資格情報を入力します。
4. **[ユーザー サインイン]** ページで次の操作を行います。

    - **[パススルー認証]** オプション ボタンを選択した場合、および SSO が Windows 7 デバイスや 8.1 デバイスに必要な場合は、**[シングル サインオンを有効にする]** をオンにしてから、**[次へ]** を選択します。
    - **[パスワード ハッシュの同期]** オプション ボタンを選択した場合は、 **[ユーザー アカウントを変換しない]** チェック ボックスがオンになっていることを確認します。 このオプションは非推奨になりました。 SSO が Windows 7 デバイスや 8.1 デバイスに必要な場合は、**[シングル サインオンを有効にする]** をオンにしてから、**[次へ]** を選択します。

        [Image: [ユーザー サインイン] ページで [シングル サインオンを有効にする] をオンにする]

    詳細情報: [PowerShell を使用してシームレス SSO を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#prework-for-seamless-sso)。
5. **[シングル サインオンを有効にする]** ページで、ドメイン管理者アカウントの資格情報を入力し、 **[次へ]** を選択します。

    [Image: [シングル サインオンを有効にする] ページ]

    シームレス SSO を有効にするには、ドメイン管理者アカウントの資格情報が必要です。 このプロセスでは、管理者特権のアクセス許可を必要とする、以下のアクションが実行されます。

    - オンプレミスの Active Directory インスタンスに、(Microsoft Entra を表す) AZUREADSSO という名前のコンピューター アカウントが作成されます。
    - コンピューター アカウントの Kerberos の復号化キーは、Microsoft Entra ID と安全に共有されます。
    - Microsoft Entra のサインイン時に使用される 2 つの URL を表す、2 つの Kerberos サービス プリンシパル名 (SPN) が作成されます。

    ドメイン管理者の資格情報は Microsoft Entra Connect または Microsoft Entra ID に格納されず、プロセスが正常に終了したときに破棄されます。 これらは、この機能を有効にするために使用されます。

    詳細情報: [シームレス SSO の技術的な詳細](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-how-it-works)。
6. **[構成の準備完了]** ページで、 **[構成が完了したら、同期プロセスを開始してください]** チェック ボックスがオンになっていることを確認します。 次に、 **[構成]** を選択します。

    [Image: 構成準備が整ったページ]

    重要

    この時点で、すべてのフェデレーション ドメインがマネージド認証に変更されます。 選択したユーザー サインイン方法は、新しい認証方法です。
7. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**[Microsoft Entra ID]** を選択し、**[Microsoft Entra Connect]**選択します。
8. 以下の設定を確認します。

    - **[フェデレーション]** が **[無効]** に設定されている。
    - **[シームレス シングル サインオン]** が **[有効]** に設定されている。
    - **[パスワード ハッシュの同期]** が **[有効]** に設定されている。

    [Image: 現在のユーザー設定を再確認する]
9. PTA に切り替える場合は、次の手順に従います。

###### PTA 用に追加の認証エージェントをデプロイする

注

PTA では、Microsoft Entra Connect サーバー、および Windows サーバーを実行しているオンプレミス コンピューターで軽量のエージェントをデプロイする必要があります。 待ち時間を短縮するには、Active Directory ドメイン コントローラーのできるだけ近くにエージェントをインストールします。

ほとんどのお客様の場合、高可用性と必要な容量を提供するのに、2 つまたは 3 つの認証エージェントがあれば十分です。 テナントには、最大 12 個のエージェントを登録できます。 最初のエージェントは、常に Microsoft Entra Connect サーバー自体にインストールされます。 エージェントの制限事項とエージェントのデプロイ オプションの詳細については、「[Microsoft Entra パススルー認証: 現在の制限事項](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-current-limitations)」を参照してください。

1. **[パススルー認証]** を選択します。
2. **[パススルー認証]** ページで、 **[ダウンロード]** ボタンを選択します。
3. **[エージェントのダウンロード]** ページで、**[使用条件に同意してダウンロード]** を選択します。

    追加の認証エージェントのダウンロードが開始されます。 セカンダリ認証エージェントは、ドメイン参加済みサーバーにインストールします。
4. 認証エージェントのインストールを実行します。 インストール中に、グローバル管理者アカウントの資格情報を入力する必要があります。
5. 認証エージェントがインストールされたら、PTA の正常性ページに戻って、追加のエージェントの状態を確認できます。

##### オプション B

**Microsoft Entra Connect と Powershell を使用してフェデレーションから新しいサインイン方法に切り替える**

最初に Microsoft Entra Connect を使用してフェデレーション ドメインを構成しなかった場合、またはサードパーティのフェデレーション サービスを使用している場合に使用できます。

Microsoft Entra Connect サーバーで、オプション A の手順 1 から 5 に従います。[ユーザー サインイン] ページで、**[構成しない]** オプションが事前に選択されていることがわかります。

[Image: [ユーザー サインイン] ページの [構成しない] オプションを参照する]

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**[Microsoft Entra ID]** を選択し、**[Microsoft Entra Connect]**選択します。
2. 以下の設定を確認します。

- **[フェデレーション]** が **[有効]** に設定されている。
- **[シームレス シングル サインオン]** が **[無効]** に設定されている。
- **[パスワード ハッシュの同期]** が **[有効]** に設定されている。

    [Image: Microsoft Entra 管理センター上で現在のユーザー設定を検証する]

**PTA のみの場合**は、次の手順に従って、追加の PTA エージェント サーバーをインストールします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**[Microsoft Entra ID]** を選択し、**[Microsoft Entra Connect]**選択します。
2. **[パススルー認証]** を選択します。 状態が **[アクティブ]** であることを確認します。

    [Image: パススルー認証の設定]

    認証エージェントがアクティブでない場合は、次の手順でドメインの変換プロセスを続行する前に、これらの[トラブルシューティング手順](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-pass-through-authentication)を完了します。 PTA エージェントが正常にインストールされたことと、**Microsoft Entra 管理センター**でそれらの状態が[アクティブ](https://entra.microsoft.com)であることを検証する前にドメインを変換すると、認証の停止を引き起こすリスクがあります。
3. 追加の認証エージェントをデプロイします。

#### ドメインをフェデレーションからマネージドに変換する

**この時点では、フェデレーション認証はまだアクティブであり、ドメインのために機能しています**。 デプロイを続行するには、各ドメインをフェデレーション ID からマネージド ID に変換する必要があります。

重要

すべてのドメインを同時に変換する必要はありません。 運用環境テナントのテスト ドメインや、ユーザー数が最も少ないドメインから開始することができます。

**Microsoft Graph PowerShell SDK を使用して変換を完了します。**

1. PowerShell で、グローバル管理者アカウントを使用して Microsoft Entra ID にサインインします。

    ```powershell
     Connect-MGGraph -Scopes "Domain.ReadWrite.All", "Directory.AccessAsUser.All"
    ```
2. 最初のドメインを変換するには、次のコマンドを実行します。

    ```powershell
     Update-MgDomain -DomainId <domain name> -AuthenticationType "Managed"
    ```
3. [Microsoft Entra 管理センター](https://entra.microsoft.com)で、**[Microsoft Entra ID] &gt; [Microsoft Entra Connect]** を選択します。
4. 下のコマンドを実行して、ドメインがマネージドに変換されたことを確認します。 認証の種類が "マネージド" に設定されているはずです。

    ```powershell
    Get-MgDomain -DomainId yourdomain.com
    ```

ドメインをマネージド認証に変換した後、Microsoft Entra Connect によってパススルー認証が有効と認識されるようにするには、変更を反映するように、Microsoft Entra Connect のサインイン方法の設定を更新します。 詳しくは、[Microsoft Entra パススルー認証のドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)をご覧ください。

### 移行を完了する

サインアップ方法を確認し、変換プロセスを完了するには、次のタスクを実行します。

#### 新しいサインイン方法をテストする

テナントでフェデレーション ID が使用されていたときに、ユーザーは Microsoft Entra サインイン ページから AD FS 環境にリダイレクトされていました。 フェデレーション認証の代わりに新しいサインイン方法を使用するようにテナントが構成されたので、ユーザーは AD FS にリダイレクトされません。

**その代わりに、ユーザーは Microsoft Entra サインイン ページから直接サインインします。**

次のリンクの手順に従います - [PHS/PTA とシームレス SSO を使用したサインインの検証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#validation) (必要な場合)

#### 段階的なロールアウトからユーザーを削除する

段階的なロールアウトを使用した場合は、カットオーバーが完了したら、段階的ロールアウト機能を無効にすることを忘れないでください。

**段階的ロールアウト機能を無効にするには、コントロールを [無効] に戻します。**

#### UserPrincipalName の更新を同期する

これまで、次の条件が両方とも当てはまらない限り、オンプレミス環境から同期サービスを使用する、**UserPrincipalName** 属性の更新はブロックされていました。

- ユーザーがマネージド (非フェデレーション) ID ドメインに存在する。
- ユーザーにライセンスが割り当てられていない。

この機能を確認または有効にする方法については、「[userPrincipalName の更新を同期する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-features)」を参照してください。

### 実装を管理する

#### シームレス SSO の Kerberos 復号化キーのロールオーバー

Active Directory ドメイン メンバーがパスワードの変更を送信する方法に合わせて、少なくとも 30 日ごとに Kerberos 復号化キーをロールオーバーすることをお勧めします。 関連するデバイスが AZUREADSSO コンピューター アカウント オブジェクトにアタッチされていないため、ロールオーバーは手動で実行する必要があります。

FAQ の「[AZUREADSSO コンピューター アカウントの Kerberos 復号化キーをロールオーバーするにはどうすればよいですか](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-faq#how-can-i-roll-over-the-kerberos-decryption-key-of-the--azureadsso--computer-account-)」を参照してください。

#### 監視およびログ記録

ソリューションの可用性を維持するには、認証エージェントを実行するサーバーを監視します。 認証エージェントでは、一般的なサーバー パフォーマンス カウンターに加えて、認証の統計情報とエラーを把握するのに役立つパフォーマンス オブジェクトが公開されます。

認証エージェントによって、操作のログが、アプリケーションとサービス ログにある Windows イベント ログに記録されます。 トラブルシューティングのためのログを有効にすることもできます。

段階的なロールアウトで実行されるさまざまなアクションを確認するために、[PHS、PTA、またはシームレス SSO のイベントを監査できます](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout#auditing)。

#### トラブルシューティング

お客様のサポート チームは、フェデレーションからマネージドへの変更中または変更後に発生する認証の問題のトラブルシューティング方法を理解する必要があります。 以下のトラブルシューティングのドキュメントは、お客様のサポート チームが一般的なトラブルシューティングの手順と、問題の特定および解決に役立つ適切な対処を理解するために役立ちます。

- [Microsoft Entra PHS](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-password-hash-synchronization)
- [Microsoft Entra PTA](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-pass-through-authentication)
- [Microsoft Entra シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sso)

### AD FS インフラストラクチャの使用を停止する

#### AD FS から Microsoft Entra ID にアプリ認証を移行する

移行では、まずアプリケーションがオンプレミスでどのように構成されているかを評価し、その構成を Microsoft Entra ID にマッピングすることが必要です。

SAML/WS-FED または OAuth プロトコルを使用してオンプレミスの SaaS アプリケーションで AD FS を引き続き使用する予定の場合は、ユーザー認証用にドメインを変換した後、AD FS と Microsoft Entra ID の両方を使用します。 この場合、[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)またはいずれかの [Microsoft Entra ID パートナー統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access)を使用したセキュリティで保護されたハイブリッド アクセス (SHA) により、オンプレミスのアプリケーションとリソースを保護できます。 アプリケーション プロキシまたはいずれかのパートナーを使用すると、お使いのオンプレミスのアプリケーションにセキュリティで保護されたリモート アクセスを提供できます。 ユーザーは [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)の後、簡単に任意のデバイスからアプリケーションに接続できるようになります。 詳細については、「 [証明書利用者セキュリティ トークン サービスを使用してエンタープライズ アプリケーションのシングル サインオンを有効にする」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso-rpsts)参照してください。

現在 ADFS とフェデレーションされている SaaS アプリケーションを、Microsoft Entra ID に移動できます。 [Azure アプリ ギャラリー](https://azuremarketplace.microsoft.com/marketplace/apps/category/azure-active-directory-apps)の組み込みコネクタを使用するか、[Microsoft Entra ID にアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)ことにより、Microsoft Entra ID で認証を行うように再構成します。

詳細については、「[アプリケーション認証を Active Directory フェデレーション サービス (AD FS) から Microsoft Entra ID に移行する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages)」を参照してください。

#### 依存先パーティ信頼を削除する

Microsoft Entra Connect Health がある場合は、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs)から[使用状況を監視](https://entra.microsoft.com)できます。 使用状況に新しい認証要求が表示されていない場合は、すべてのユーザーとクライアントが Microsoft Entra ID を通じて正常に認証されていることを確認したら、Microsoft 365 の証明書利用者信頼を削除しても問題ありません。

AD FS を他の目的で (つまり、他の証明書利用者信頼で) 使用していない場合は、この時点で AD FS の使用を停止できます。

#### AD FS の削除

環境から AD FS を完全に削除するために実行する手順の完全な一覧については、「[Active Directory フェデレーション サービス (AD FS) の使用停止ガイド](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/decommission/adfs-decommission-guide)」に従ってください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/parallel-hybrid-migration"} -->
## Microsoft Entra Connect を使用するホスト側での複数組織のオンプレミス Exchange メールボックスの移行 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/parallel-hybrid-migration
- Service: entra-id-governance
- Article date: 2025-04-09
- Summary: ホスト側がハイブリッド ID を使用して複数組織のメールボックス移行を実行できる方法を説明するシナリオ。

Microsoft Entra Connect を使って、オンプレミスの Exchange Server から Microsoft 365 クラウドおよび Exchange Online への、複数組織のメールボックスの並列移行を実行できます。 この方法には、次のような利点があります。

- ダウンタイムが発生しません。
- エンド ユーザーのパスワードの同期。
- 移行後にエンド ユーザー デバイスで Outlook デスクトップ アプリを再構成する必要がありません。

### 概要

企業によっては、独自の Active Directory アーキテクチャを使って、同じフォレストで複数の小さな組織をサポートしている場合があります。 たとえば、オンプレミスの Exchange Server ホスティング会社のような場合です。

このような企業は、Microsoft の移行ツールを使ってメールボックスをオンプレミスからクラウドに移行する場合に課題に直面します。 このような企業では、多くの場合、移行を実行するためにサード パーティの解決策を探す必要があります。

このシナリオでは、既存の Microsoft ツールセットを使ってハイブリッド構成を設定した後でメールボックスを移行する解決策を提供します。

[Image: 並列ハイブリッド移行シナリオの図。]

### 前提条件

- 移行先のテナントごとに、1 つの Microsoft Entra Connect サーバーが必要です。
- Microsoft Entra Connect サーバーごとに仮想マシンを作成する必要があり、それらをドメインに参加させる必要があります。
- オンプレミスの Active Directory のユーザーは、独自の組織単位 (OU) に存在する必要があります。
- 各 Microsoft Entra Connect サーバーには、個々の OU を対象とする同期規則があります。
- 移行するテナントのすべてのプライマリ ドメインを追加し、Microsoft 365 で検証する必要があります。
- [Exchange のハイブリッド展開](https://learn.microsoft.com/ja-jp/exchange/exchange-hybrid)について熟知している必要があります。
- [Microsoft Entra Connect の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)を満たしていることを確認します。
- [ハイブリッド構成ウィザードの前提条件](https://learn.microsoft.com/ja-jp/exchange/hybrid-deployment-prerequisites)を満たしていることを確認します。

### 並列ハイブリッド移行

以下では、並列ハイブリッド環境を使って Microsoft Entra Connect で複数組織のオンプレミス Exchange メールボックスの移行を行う手順の概要を説明します。 移行先のテナントごとに、各ステップを完了する必要があります。

#### ステップ 1 - Microsoft Entra Connect

1. 作成した各[仮想マシン](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/get-started/create-a-virtual-machine-in-hyper-v?tabs=hyper-v-manager)で、Microsoft Entra Connect を[ダウンロード](https://www.microsoft.com/download/details.aspx?id=47594)します。
2. [カスタム設定](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom)を使って、Microsoft Entra Connect をインストールします。
3. Microsoft Entra Connect と同期するテナントに対応する移行元の[オンプレミス組織単位](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#organizational-unitbased-filtering)にスコープを構成します。

    [Image: OU のスコープ設定のスクリーンショット。]
4. **[Exchange ハイブリッド展開]** と **[パスワード ハッシュ同期]** を有効にします

    [Image: オプションの機能のスクリーンショット。]
5. Microsoft Entra Connect で[インストール後のタスク](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-post-installation)を行います。
6. すべてのユーザーが移行先のテナントに同期されていることを確認します。

#### ステップ 2 - ハイブリッド構成ウィザード

Microsoft Entra Connect サーバーを構成し、同期が完了したら、次の手順に従って Exchange ハイブリッド構成ウィザードをダウンロードして構成します。

1. 仮想マシンごとに、[ハイブリッド構成ウィザード](https://aka.ms/hybridwizard)を[ダウンロード](https://learn.microsoft.com/ja-jp/exchange/hybrid-deployment/deploy-hybrid)してインストールします。
2. インストールでは、[\[最小ハイブリッド\]](https://learn.microsoft.com/ja-jp/exchange/mailbox-migration/use-minimal-hybrid-to-quickly-migrate) を選びます。

    [Image: 最小ハイブリッドのスクリーンショット。]

Exchange ハイブリッドについて詳しくは、[Exchange のハイブリッド展開](https://learn.microsoft.com/ja-jp/exchange/exchange-hybrid)に関する記事をご覧ください

#### ステップ 3 - Exchange 管理センター

1. [Exchange 管理センター](https://learn.microsoft.com/ja-jp/exchange/exchange-admin-center)で [移行] に移動し、移行するユーザーを選びます。 EAC には URL https://admin.exchange.microsoft.com/ を使ってアクセスできます
2. [ユーザーを移行します](https://learn.microsoft.com/ja-jp/exchange/troubleshoot/move-or-migrate-mailboxes/migrate-data-with-admin-center)。
3. メールボックスが完全に転送された後で、移行バッチを完了します。

注

ハイブリッド構成ウィザードの最後のステップでエンドポイントを作成する必要があり、Exchange 管理センターでの移行バッチの作成にそれを利用できる必要があります。 そうでない場合は、エンドポイントを手動で作成します。

注

Microsoft Entra Connect によるユーザーのプロビジョニングが完了すると、組織内のすべてのユーザーが Exchange 管理センターで MailUser として使用でき、移行バッチの作成時に選択できるようになります。

#### ステップ 4 - ハイブリッド構成ウィザードと Microsoft Entra Connect をアンインストールする

移行が完了したら、仮想サーバー上の HCW と Microsoft Entra Connect をアンインストールしてかまいません。 この時点で、ドメインからサーバーを削除してオフにできます。

#### ステップ 5 - テナントごとに繰り返す

移行の手順が完了したら、残りのすべてのテナントについて手順を繰り返します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/plan-connect-design-concepts"} -->
## Microsoft Entra Connect: 設計概念 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、特定の実装設計の各領域について詳しく説明します。

このドキュメントの目的は、Microsoft Entra Connect の構成時に考慮する必要がある分野について説明することです。 このドキュメントでは特定の領域について詳しく説明しますが、これらの概念については、他のドキュメントでも簡単に説明しています。

### sourceAnchor

sourceAnchor 属性は、 *オブジェクトの有効期間中に変更できない属性*として定義されています。 この属性により、オブジェクトは、オンプレミスと Microsoft Entra ID で同じオブジェクトとして一意に識別されます。 また、この属性は **immutableId** とも呼ばれており、この 2 つの名前のどちらを使ってもかまいません。

変更不可 (つまり、変更できない) という単語は、このドキュメントで大きな意味を持ちます。 この属性の値は設定後に変更できないため、シナリオをサポートするデザインを選択することが重要です。

この属性は、次のシナリオで使用されます。

- 新しい同期エンジン サーバーを構築する場合、またはディザスター リカバリー シナリオの後に再構築する場合、この属性によって、Microsoft Entra ID 内の既存のオブジェクトがオンプレミスのオブジェクトとリンクされます。
- クラウド専用の ID から同期 ID モデルに移行する場合、この属性によって、Microsoft Entra ID 内の既存のオブジェクトとオンプレミスのオブジェクトを "完全一致" させることができます。
- フェデレーションを使用する場合は、ユーザーを一意に識別するために、要求でこの属性と共に **userPrincipalName** を使用します。

ユーザーに関連するのは sourceAnchor であることから、このトピックではこの属性のみについて説明します。 すべてのオブジェクトの種類に同じ規則が適用されますが、通常この問題が関係するのはユーザーのみです。

#### 適切な sourceAnchor 属性の選択

属性の値は、次の規則に従う必要があります。

- 60 文字未満であること
    - a ～ z、A ～ Z、0 ～ 9 のいずれでもない文字はエンコードされ、3 文字としてカウントされます。
- 次の特殊文字が含まれていないこと: \ ! # $ % & \* + / = ? ^ ` { } | ~ &lt;&gt; ( ) ' ; : , [ ] " @ \_
- グローバルに一意であること
- 文字列、整数、バイナリのいずれかであること
- 変更される可能性があるためユーザーの名前に基づかないこと
- 大文字と小文字の区別をしないようにし、大文字と小文字によって異なる値を避けること
- オブジェクトの作成時に割り当てること

選択された sourceAnchor が文字列型でない場合、Microsoft Entra Connect では、特殊文字が表示されないように、属性値に対して Base64Encode を実行します。 ADFS とは別のフェデレーション サーバーを使用する場合、そのサーバーでも属性に対して Base64Encode を実行できることを確認してください。

sourceAnchor 属性では、大文字小文字の区別があります。 値 "JohnDoe" と "johndoe" は同じではありません。 しかし、大文字と小文字が異なるだけの 2 つのオブジェクトは作成しないでください。

オンプレミスの単一フォレストがある場合、使用する必要がある属性は **objectGUID** です。 これは、Microsoft Entra Connect で簡単設定を使用する際に使用される属性でもあり、DirSync で使用される属性でもあります。

複数のフォレストがあり、フォレストおよびドメイン間でユーザーを移動しない場合でも、**objectGUID** 属性を使用することをお勧めします。

フォレストおよびドメイン間でユーザーを移動する場合は、変更されない属性、または移動時にユーザーと共に移動できる属性を探す必要があります。 お勧めする方法は、合成属性を導入することです。 GUID のような情報を保持可能な属性が適しています。 オブジェクトの作成中に、新しい GUID が作成され、ユーザーに設定されます。 同期エンジン サーバーでは、**objectGUID** に基づいてこの値を作成し、AD DS の選択した属性を更新するカスタム同期規則を作成できます。 オブジェクトを移動するときは、この値の内容も必ずコピーしてください。

別の方法は、変更されないことがわかっている既存の属性を選択することです。 一般的に使用される属性に **employeeID**があります。 文字を含む属性について考慮する場合は、属性値の文字 (大文字と小文字) が変化する可能性がないことを確認します。 使用しない方がよい属性として、ユーザーの名前を含む属性があります。 名前は、結婚や離婚によって変更されることが予想されるため、この属性には使用できません。 これは、**userPrincipalName**、**mail**、**targetAddress** などの属性を Microsoft Entra Connect のインストール ウィザードで選択できない理由の 1 つでもあります。 これらの属性に含まれる "@" 文字も sourceAnchor では使用できません。

#### sourceAnchor 属性の変更

オブジェクトが Microsoft Entra ID で作成され、ID が同期された後は、sourceAnchor 属性値を変更できません。

このような理由から、Microsoft Entra Connect には次の制限が適用されます:

- sourceAnchor 属性を設定できるのは、初回インストール時のみです。 インストール ウィザードを再実行すると、このオプションは読み取り専用になります。 この設定を変更する必要がある場合は、アンインストールして再インストールする必要があります。
- 別の Microsoft Entra Connect サーバーをインストールする場合は、前に使用したのと同じ sourceAnchor 属性を選択する必要があります。 以前に DirSync を使用していて Microsoft Entra Connect に移行する場合は、**objectGUID** を使用する必要があります。これは、DirSync で使用される属性です。
- オブジェクトが Microsoft Entra ID にエクスポートされた後に sourceAnchor の値が変更された場合、Microsoft Entra Connect Sync はエラーをスローし、問題が修正され、sourceAnchor がソース ディレクトリに戻される前に、そのオブジェクトに対するそれ以上の変更を許可しません。

### sourceAnchor としての ms-DS-ConsistencyGuid の使用

Microsoft Entra Connect (バージョン 1.1.486.0 以前) では既定で、objectGUID が sourceAnchor 属性として使用されます。 ObjectGUID はシステムによって生成されます。 その値を、オンプレミスの AD オブジェクトを作成するときに自分で指定することはできません。 「sourceAnchor」セクションで説明したように、シナリオによっては、sourceAnchor の値を自分で指定する必要があります。 そのシナリオが自分に当てはまる場合は、設定によって変更できる AD 属性 (ms-DS-ConsistencyGuid など) を sourceAnchor 属性として使用してください。

Microsoft Entra Connect (バージョン 1.1.524.0 以降) では、ms-DS-ConsistencyGuid 機能が sourceAnchor 属性として使用できるようになりました。 この機能を使用すると、次のことを行う同期規則が Microsoft Entra Connect によって自動的に構成されます:

1. ユーザー オブジェクトの場合、sourceAnchor 属性として ms-DS-ConsistencyGuid が使用されます。 その他の種類のオブジェクトでは、ObjectGUID が使用されます。
2. ms-DS-ConsistencyGuid 属性の値が設定されていないオンプレミスの AD ユーザー オブジェクトについては、Microsoft Entra Connect によって、その objectGUID の値がオンプレミス Active Directory の ms-DS-ConsistencyGuid 属性に書き戻されます。 ms-DS-ConsistencyGuid 属性が設定されたら、Microsoft Entra Connect によってオブジェクトがMicrosoft Entra ID にエクスポートされます。

注

オンプレミス AD オブジェクトが Microsoft Entra Connect にインポートされた (つまり、AD のコネクタ スペースにインポートされてメタバースに反映された) 後は、その sourceAnchor の値は変更できなくなります。 特定のオンプレミス AD オブジェクトに対して sourceAnchor の値を指定するには、そのオブジェクトが Microsoft Entra Connect にインポートされる前に、その ms-DS-ConsistencyGuid 属性を構成してください。

#### アクセス許可が必要

この機能を利用するためには、オンプレミス Active Directory との同期に使用する AD DS アカウントに、オンプレミス Active Directory 内の ms-DS-ConsistencyGuid 属性への書き込みアクセス許可を付与する必要があります。

#### ConsistencyGuid 機能を有効にする方法 - 新規インストール

新規インストール時に ConsistencyGuid の使用を sourceAnchor として有効にすることができます。 このセクションでは、高速インストールとカスタム インストールの両方を詳細に説明します。

注

新規インストール時に ConsistencyGuid を sourceAnchor として使用することは、新しいバージョンの Microsoft Entra Connect (1.1.524.0 以降) でのみサポートされています。

#### ConsistencyGuid 機能を有効にする方法

##### 高速インストール

Microsoft Entra Connect を高速モードでインストールする場合、sourceAnchor 属性として最適な AD 属性が Microsoft Entra Connect ウィザードによって自動的に決定されます。その際、以下のロジックが使用されます:

- 最初に、Microsoft Entra Connect ウィザードは、Microsoft Entra テナントに対してクエリを実行して、前の Microsoft Entra Connect インストールで sourceAnchor 属性として使用された AD 属性を取得します (存在する場合)。 この情報が判明した場合は、Microsoft Entra Connect は同じ AD 属性を使用します。

    注

    インストール時に使用される sourceAnchor 属性についての情報を Microsoft Entra テナントに格納するのは、新しいバージョンの Microsoft Entra Connect (1.1.524.0 以降) のみです。 古いバージョンの Microsoft Entra Connect では使用できません。
- 使用されている sourceAnchor 属性の情報がない場合、ウィザードがオンプレミスの Active Directory で ms-DS-ConsistencyGuid 属性の状態を確認します。 この属性がディレクトリ内のどのオブジェクトに対しても構成されていない場合、ms-DS-ConsistencyGuid が sourceAnchor 属性として使用されます。 ディレクトリ内の 1 つまたは複数のオブジェクトに対してこの属性が構成されている場合、ウィザードでは、この属性が他のアプリケーションによって使用されており、sourceAnchor 属性としては適さないと判断されます。
- どの場合でも、ウィザードは objectGUID を sourceAnchor 属性として使用します。
- ウィザードによって sourceAnchor 属性が決定されると、その情報が Microsoft Entra テナントに格納されます。 この情報は、Microsoft Entra Connect の今後のインストールで使用されます。

高速インストールが完了すると、ソース アンカー属性として選択された属性がウィザードによって通知されます。

[Image: sourceAnchor として選択された AD 属性がウィザードから通知される]

##### カスタム インストール

カスタム モードで Microsoft Entra Connect をインストールする場合、Microsoft Entra Connect ウィザードで sourceAnchor 属性を構成するときに 2 つのオプションが表示されます:

[Image: カスタムインストール - sourceAnchor の構成]

| 設定 | 説明 |
| --- | --- |
| Let Microsoft Entra ID manage the source anchor for me (ソース アンカーの管理を Microsoft Entra ID に任せる) | Microsoft Entra ID で属性を自動的に選択する場合は、このオプションを選びます。 このオプションを選択した場合、Microsoft Entra Connect ウィザードで高速インストール時に使用される sourceAnchor 属性の選択ロジックが同じように適用されます。 高速インストールと同様に、カスタム インストールの完了後にソース アンカー属性としてどの属性が選択されているかがウィザードによって通知されます。 |
| 特有の属性 | sourceAnchor 属性として既存の AD 属性を指定する場合は、このオプションを選択します。 |

#### ConsistencyGuid 機能を有効にする方法 - 既存のデプロイ

ソース アンカー属性として objectGUID を使用する Microsoft Entra Connect のデプロイが既にある場合は、代わりに ConsistencyGuid を使用するよう切り替えることができます。

注

ソース アンカー属性として ObjectGuid から ConsistencyGuid への切り替えをサポートするのは、新しいバージョンの Microsoft Entra Connect (1.1.552.0 以降) のみです。

ソース アンカー属性を ObjectGuid から ConsistencyGuid に切り替えるには:

1. Microsoft Entra Connect ウィザードを起動し、**[** の構成] を選択してタスク画面に移動します。
2. **ソース アンカー** の構成タスク オプションを選択し、次 を選択します。

    [Image: 既存のデプロイで ConsistencyGuid を有効にする - 手順 2]
3. Microsoft Entra 管理者資格情報を入力し、**[次へ]**を選択します。
4. オンプレミスの Active Directory で ms-DS-ConsistencyGuid 属性の状態が Microsoft Entra Connect ウィザードによって解析されます。 属性がディレクトリ内のどのオブジェクトにも構成されていない場合、Microsoft Entra Connect はその属性を使用しているアプリケーションは現在なく、ソース アンカー属性として使用しても問題がないと判断します。 [次  を選択して続行します。

    [Image: 既存のデプロイで ConsistencyGuid を有効にする - 手順 4]
5. **構成の準備完了** 画面で、**構成** を選択して構成を変更します。

    [Image: 既存のデプロイで ConsistencyGuid を有効にする - 手順 5]
6. 構成が完了すると、ウィザードに、ms-DS-ConsistencyGuid がソース アンカー属性として使用されていることが示されます。

    [Image: 既存のデプロイで ConsistencyGuid を有効にする - 手順 6]

分析中 (手順 4) に、ディレクトリ内の 1 つ以上のオブジェクトに対して属性が構成されている場合、ウィザードは属性が別のアプリケーションで使用されていると判断し、次の図に示すようにエラーを返します。 このエラーは、プライマリ Microsoft Entra Connect サーバーで ConsistencyGuid 機能を有効にしていて、ステージング サーバーで同じ操作を行おうとしている場合にも発生する可能性があります。

[Image: 既存のデプロイで ConsistencyGuid を有効にする - エラー]

属性が他の既存のアプリケーションで使用されていないことがわかっている場合は、**/SkipLdapSearch** スイッチを指定して Microsoft Entra Connect ウィザードを再起動することで、エラーを抑制することができます。 これを行うには、コマンド プロンプトで次のコマンドを実行します。

```
"c:\Program Files\Microsoft Azure Active Directory Connect\AzureADConnect.exe" /SkipLdapSearch
```

#### AD FS の構成またはサードパーティのフェデレーションの構成に対する影響

Microsoft Entra Connect を使用してオンプレミス AD FS デプロイを管理している場合、Microsoft Entra Connect では、同じ AD 属性を sourceAnchor として使用するように要求規則が自動的に更新されます。 これによって、ADFS によって生成される ImmutableID 要求と Microsoft Entra ID にエクスポートされる sourceAnchor 値との整合性が確実に保たれます。

Microsoft Entra Connect の外部で AD FS を管理している場合や、サードパーティのフェデレーション サーバーを認証に使用している場合は、ImmutableID 要求の要求規則を手動で更新して、Microsoft Entra ID にエクスポートされる sourceAnchor 値との整合性を確保する必要があります (記事のセクション「[AD FS の要求規則を変更する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-management#modclaims)」を参照)。 インストールが完了するとウィザードから次の警告が返されます。

[Image: サード パーティのフェデレーション構成]

#### 既存のデプロイに対する新しいディレクトリの追加

ConsistencyGuid 機能を有効にして Microsoft Entra Connect をデプロイしており、そのデプロイにもう 1 つディレクトリを追加したいとします。 ディレクトリを追加しようとすると、そのディレクトリ内の ms-DS-ConsistencyGuid 属性の状態が Microsoft Entra Connect ウィザードによってチェックされます。 ディレクトリ内の少なくとも 1 つのオブジェクトに対してこの属性が構成済みであった場合は、この属性が他のアプリケーションによって使用されていると判断され、以下の図のようなエラーが返されます。 属性が既存のアプリケーションで使用されていないことが確実な場合は、前述のように指定した **/SkipLdapSearch** スイッチを使用して Microsoft Entra Connect ウィザードを再起動することで、エラーを抑制できます。詳細については、サポートにお問い合わせください。

[Image: 既存のデプロイに対する新しいディレクトリの追加]

### Microsoft Entra サインイン

オンプレミス ディレクトリを Microsoft Entra ID に統合する場合、同期の設定がユーザーの認証方法に与える可能性がある影響について理解することが重要です。 Microsoft Entra ID では、userPrincipalName (UPN) がユーザーの認証に使用されます。 ただし、ユーザーを同期する場合は、userPrincipalName の値として使用される属性を慎重に選択する必要があります。

#### userPrincipalName の属性を選択する

Microsoft Entra ID で使われる UPN の値を提供するための属性を選ぶときは、次のことを確認する必要があります

- 属性値が UPN の構文 (RFC 822) に準拠していること (username@domain の形式である必要がある)
- 値のサフィックスが、Microsoft Entra ID で確認済みのカスタム ドメインのいずれかに一致すること

簡単設定では、属性の値として userPrincipalName が想定されます。 ユーザーが Microsoft Entra ID にサインインするために必要な値が userPrincipalName 属性に含まれていない場合は、**[カスタム インストール]** を選ぶ必要があります。

注

ベスト プラクティスとして、UPN プレフィックスに複数の文字を含めるようにすることをお勧めします。

#### カスタム ドメインの状態と UPN

UPN サフィックスの検証済みドメインがあることを確認することが重要です。

John は、contoso.com に属するユーザーです。 Microsoft Entra ディレクトリ contoso.onmicrosoft.com にユーザーを同期した後、John にオンプレミスの UPN john@contoso.com を使って Microsoft Entra ID にサインインしてもらいたい。 この場合は、ユーザーの同期を開始する前に、contoso.com を Microsoft Entra ID にカスタム ドメインとして追加する必要があります。 John の UPN サフィックス (この例では contoso.com) が Microsoft Entra ID で検証済みのドメインと一致しない場合、Microsoft Entra ID は UPN サフィックスを contoso.onmicrosoft.com に置き換えます。

#### Microsoft Entra ID のルーティング不可能なオンプレミス ドメインと UPN

一部の組織には、contoso.local などのルーティング不可能なドメインや、contoso のような単純な単一ラベル ドメインがあります。 Microsoft Entra ID では、ルーティング不可能なドメインを確認できません。 Microsoft Entra Connect は、Microsoft Entra ID の検証済みドメインにのみ同期できます。 Microsoft Entra ディレクトリを作成すると、ルーティング可能なドメインが作成され、そのドメインが Microsoft Entra ID の既定のドメインになります (例: contoso.onmicrosoft.com)。 そのため、既定の onmicrosoft.com ドメインに同期しないシナリオでは、他のルーティング可能なドメインを確認することが必要になります。

ドメインの追加と確認の詳細については、「 [Microsoft Entra ID へのカスタム ドメイン名の追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain) 」を参照してください。

Microsoft Entra Connect は、ルーティング不可能なドメイン環境で実行されているかどうかを検出し、簡易設定を進めることに対して適切に警告を行います。 ルーティング不可能なドメインで運用している場合は、ユーザーの UPN にもルーティング不可能なサフィックスがある可能性があります。 たとえば、contoso.local で実行している場合、Microsoft Entra Connect はクイック設定ではなく、カスタム設定を提案します。 カスタム設定を使うと、ユーザーが Microsoft Entra ID に同期された後で、Microsoft Entra ID にサインインするための UPN として使う必要がある属性を指定できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/plan-connect-performance-factors"} -->
## Microsoft Entra Connect のパフォーマンスに影響を及ぼす要因 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-performance-factors
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このドキュメントでは、さまざまな要因が Microsoft Entra Connect プロビジョニング エンジンに与える影響について説明します。 これらの要因は、組織が Microsoft Entra Connect の展開を計画して、同期要件を満たしていることを確認するのに役立ちます。

Microsoft Entra Connect は、Active Directory を Microsoft Entra ID に同期します。 このサービスは、ユーザー ID をクラウドに移動するための重要なコンポーネントです。 Microsoft Entra Connect のパフォーマンスに影響する主な要因は以下のとおりです。

| **設計の因子** | **定義** |
| --- | --- |
| トポロジ | ネットワーク上の、Microsoft Entra Connect による管理が必要なエンドポイントやコンポーネントの分布状況。 |
| スケール | Microsoft Entra Connect によって管理されるユーザー、グループ、OU などのオブジェクトの数。 |
| ハードウェア | Microsoft Entra Connect 用のハードウェア (物理または仮想) と、それらに必要とされる個々のハードウェア コンポーネント (CPU、メモリ、ネットワーク、ハード ドライブ構成など) のパフォーマンス能力。 |
| 構成 | Microsoft Entra Connect でディレクトリや情報を処理する方法。 |
| [読み込み] | オブジェクト変更の頻度。 負荷は、1 時間、1 日、または 1 週間の間で変化する可能性があります。 コンポーネントによっては、ピーク時の負荷または平均負荷に合わせた設計が必要な場合があります。 |

このドキュメントの目的は、Microsoft Entra Connect プロビジョニング エンジンのパフォーマンスに影響する要因について説明することです。 大規模な組織や複雑な組織 (10 万を超える数のオブジェクトをプロビジョニングする組織) において、ここで説明するようなパフォーマンス上の問題が発生した場合、推奨事項に従って Microsoft Entra Connect の実装を最適化することができます。 Microsoft Entra Connect のその他のコンポーネント ([Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install) やエージェントなど) については、ここでは説明しません。

重要

公式なドキュメントに記載されたアクション以外の方法で Microsoft Entra Connect の変更や操作を行うことは、Microsoft のサポートの対象外です。 サポート対象外のアクションを行うと、Microsoft Entra Connect Sync が不整合な状態やサポート対象外の状態になる可能性があります。Microsoft は、そのようなデプロイに対してテクニカル サポートを提供できません。

### Microsoft Entra Connect コンポーネントの要因

次の図は、単一のフォレストに接続しているプロビジョニング エンジンのアーキテクチャの概要を示しています (ただし、複数のフォレストには対応していません)。 このアーキテクチャは、さまざまなコンポーネントが相互にやり取りする方法を示しています。

[Image: 接続されているディレクトリと Microsoft Entra Connect プロビジョニング エンジンとの間で行われるやり取りの説明図 (SQL Database 内のコネクタ スペースやメタバース コンポーネントを含む)。]

プロビジョニング エンジンは、個々の Active Directory フォレストと Microsoft Entra ID に接続します。 各ディレクトリから情報を読み取るプロセスを、インポートと言います。 エクスポートは、プロビジョニング エンジンからディレクトリを更新することを指します。 同期では、プロビジョニング エンジン内でオブジェクトがどのように流れるかのルールが評価されます。 より深く理解するために、「[Microsoft Entra Connect Sync のアーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-architecture)について」を参照してください。

Microsoft Entra Connect では、以下に示すステージング領域、規則、プロセスに基づいて、Active Directory から Microsoft Entra ID への同期が許可されます。

- **コネクタ スペース (CS)** -接続先されたディレクトリ (CD) のそれぞれからのオブジェクト (実際のディレクトリ) は、最初にここでステージングされてから、プロビジョニング エンジンで処理できます。 Microsoft Entra ID はそれ自身の CS を持っており、接続先の各フォレストもそれぞれの CS を持っています。
- **メタバース (MV)** - 同期する必要があるオブジェクトは、同期規則に基づいてここに作成します。 接続されているその他のディレクトリにオブジェクトと属性を設定するには、オブジェクトが MV に存在する必要があります。 MV は 1 つしかありません。
- **同期規則** - MV 内のオブジェクトに対して作成 (投影) または接続 (結合) するオブジェクトを決定します。 同期規則では、ディレクトリとの間でコピーまたは変換する属性値も決定します。
- **実行プロファイル** - ステージング領域と接続されたディレクトリの間の同期規則に従って、オブジェクトとその属性値のコピー プロセスの手順を 1 つにまとめます。

プロビジョニング エンジンのパフォーマンスを最適化する、別の実行プロファイルが存在しています。 ほとんどの組織では、通常の操作に既定のスケジュールと実行プロファイルを使用しますが、一部の組織では、一般的でない状況に対応するために、スケジュール の変更や他の実行プロファイルのトリガーを する必要がある場合があります。 使用できる実行プロファイルは次のとおりです。

#### 初期同期プロファイル

初期同期プロファイルは、Active Directory フォレストのような、接続されたディレクトリの初めての読み取りのプロセスです。 その後で、同期エンジン データベースのすべてのエントリの解析を行います。 最初のサイクルでは、Microsoft Entra ID に新しいオブジェクトが作成され、Active Directory フォレストが大きい場合、完了までに余分な時間がかかります。 初期同期の手順は、次のとおりです。

1. すべてのコネクタでのフル インポート
2. すべてのコネクタでの完全同期
3. すべてのコネクタでのエクスポート

#### 差分同期プロファイル

同期プロセスを最適化するために、接続されたディレクトリ内のオブジェクトの最後の同期プロセス以降の変更 (作成、削除および更新) を処理するのは、この実行プロファイルだけです。 既定では、差分同期プロファイルは 30 分ごとに実行されます。 Microsoft Entra ID を常に最新の状態に保つため、この所要時間が 30 分を超えることがないように工夫することをお勧めします。 Microsoft Entra Connect の正常性を監視するには、プロセスのさまざまな問題を発見できる、[稼働状況の監視エージェント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-sync)を使用します。 差分同期プロファイルの手順は、次のとおりです。

1. すべてのコネクタでの差分インポート
2. すべてのコネクタでの差分同期
3. すべてのコネクタでのエクスポート

一般的なエンタープライズ組織の差分同期のシナリオは、次のとおりです。

- 約 1% のオブジェクトが削除される
- 約 1% のオブジェクトが作成される
- 約 5% のオブジェクトが変更される

変化率は、組織が Active Directory のユーザーを更新する頻度によって異なります。 たとえば、変化率の上昇は、従業員の雇用と削減の季節性によって発生する可能性があります。

#### 完全同期プロファイル

次のいずれかの構成変更を行った場合は、完全同期サイクルが必要です。

- 接続されたディレクトリからインポートするオブジェクトまたは属性の範囲を拡大した。 たとえば、インポート範囲にドメインや OU を追加した場合です。
- 同期規則に変更を加えた。 たとえば、ユーザーの役職を Active directory の extension\_attribute3 から読み込んで Microsoft Entra ID に設定するための新しい規則を作成した場合などです。 この更新には、肩書を更新して今後の変更を適用するために、プロビジョニング エンジンが既存のすべてのユーザーを再確認する必要があります。

完全同期サイクルには、次の操作が含まれています。

1. すべてのコネクタでのフル インポート
2. すべてのコネクタでの完全/差分同期
3. すべてのコネクタでのエクスポート

注

Active Directory 内または Microsoft Entra ID 内の多数のオブジェクトに対して一括更新を実施する場合は、慎重な計画が必要です。 一括更新では、多くのオブジェクトが変更されているため、インポート時に差分同期プロセスに時間がかかります。 一括更新が同期プロセスに影響を及ぼさない場合でも、インポートに長時間かかる可能性があります。 たとえば、Microsoft Entra ID で多数のユーザーにライセンスを割り当てると、Microsoft Entra ID からのインポート サイクルが長くなりますが、Active Directory では属性が変更されません。

#### 同期化

同期プロセスの実行時には、次のパフォーマンス特性があります。

- 同期はシングル スレッドです。つまり、プロビジョニング エンジンは、接続されたディレクトリ、オブジェクト、または属性の実行プロファイルの並列処理を実行しません。
- インポート時間は、同期されるオブジェクトの数に伴って線形に増加します。 たとえば、10,000 個のオブジェクトのインポートに 10 分かかる場合、20,000 オブジェクトは同じサーバーで約 20 分かかります。
- エクスポートも線形です。
- 同期は、他のオブジェクトへの参照を持つオブジェクトの数に基づいて指数関数的に増加します。 グループ メンバーシップと入れ子になったグループは、そのメンバーがユーザー オブジェクトまたはその他のグループを参照するため、主要なパフォーマンスへの影響があります。 同期サイクルを完了するには、これらの参照を検出し、MV 内の実際のオブジェクトに参照されるようにする必要があります。
- グループ メンバーを変更すると、すべてのグループ メンバーが再評価されます。 たとえば、50 K のメンバーを持つグループがあり、1 つのメンバーのみを更新する場合、50 K のすべてのメンバーの同期がトリガーされます。

#### フィルタリング

インポートする Active Directory トポロジのサイズは、プロビジョニング エンジンの内部コンポーネントが完了するまでのパフォーマンスと全体的な時間に影響を与える 1 番目の要素です。

[フィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering)は、同期するオブジェクトを減らすために使用してください。 不要なオブジェクトが処理され、Microsoft Entra ID にエクスポートされるのを防ぎます。 フィルター処理に使用できる手法を、最も望ましいものから順に次に示します。

- **ドメインベースのフィルター処理** – このオプションは、特定のドメインを選択して Microsoft Entra ID に同期する場合に使用します。 Microsoft Entra Connect Sync をインストールした後にオンプレミス インフラストラクチャに変更を加えた場合、同期エンジンの構成に含まれるドメインの追加や削除が必要になります。
- **組織単位 (OU) のフィルター処理** - OU に基づき、Active Directory ドメイン内の特定のオブジェクトが Microsoft Entra ID へのプロビジョニング対象となるようにします。 OU のフィルター処理は、2 番目に推奨されるフィルター処理メカニズムです。これは、単純な LDAP スコープ クエリを使用して、Active Directory からオブジェクトの小さなサブセットをインポートするためです。
- **オブジェクトごとの属性のフィルター処理** - オブジェクトの属性値に基づいて、Active Directory 内の特定のオブジェクトが Microsoft Entra ID でのプロビジョニング対象となるようにします。 属性のフィルター処理は、ドメインと OU のフィルター処理が特定のフィルター処理要件を満たしていない場合に、フィルターを微調整するのに適しています。 属性のフィルター処理では、インポート時間は短縮されませんが、同期時間とエクスポート時間を短縮できます。
- **グループベースのフィルター処理** - グループ メンバーシップに基づいて、Microsoft Entra ID でのプロビジョニング対象となるオブジェクトを決定します。 グループベースのフィルター処理は、同期サイクル中にグループ メンバーシップをチェックするために余分なオーバーヘッドが必要なため、状況をテストする場合のみ適していて、運用環境には推奨されません。

プロビジョニング エンジンは同期サイクルで可能な接続について各非コネクタ オブジェクトを再評価する必要があるため、Active Directory CS に永続的な[非コネクタ オブジェクト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-architecture#relationships-between-staging-objects-and-metaverse-objects)が多数あると、同期に時間がかかる可能性があります。 この問題を克服するためには、次のいずれかの推奨事項について検討してください。

- 非コネクタ オブジェクトを、ドメインまたは OU のフィルター処理を使用したインポートの範囲外に配置します。
- オブジェクトを MV に投影/結合し、[cloudFiltered](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#negative-filtering-do-not-sync-these) 属性を True に設定して、それらのオブジェクトが Microsoft Entra CS でプロビジョニングされないようにします。

注

フィルター処理するオブジェクトが多すぎると、ユーザーが混乱したり、アプリケーションのアクセス許可の問題が発生したりする可能性があります。 たとえば、ハイブリッド Exchange オンライン実装では、オンプレミスのメールボックスを持つユーザーには、Exchange Online のメールボックスを持つユーザーよりも多くのユーザーがグローバル アドレス一覧に表示されます。 その他の場合、ユーザーは、フィルター処理されたオブジェクトのセットのスコープに含まれていない別のユーザーに、クラウド アプリ内のアクセス権を付与することができます。

#### 属性フロー

属性フローとは、接続されたディレクトリ間でオブジェクトの属性値をコピーまたは変換するプロセスのことです。 これは同期規則の一部として定義します。 たとえば、Active Directory でユーザーの電話番号が変更されると、Microsoft Entra ID の電話番号が更新されます。 組織では、さまざまな要件に合わせて[属性フローを変更](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-the-configuration)することができます。 変更する前に、既存の属性フローをコピーすることをお勧めします。

属性値を別の属性にフローするといった単純なリダイレクトでは、パフォーマンスへの重要な影響はありません。 リダイレクトでは、たとえば、Active Directory 内の携帯電話番号を Microsoft Entra ID 内の職場の電話番号へとフローさせることができます。

属性値の変換は、同期プロセスの際のパフォーマンスに影響を及ぼす可能性があります。 属性値の変換には、属性の値の変更、再フォーマット、連結、または減算が含まれます。

組織では特定の属性を Microsoft Entra ID へのフロー対象から除外できますが、そのような指定は、プロビジョニング エンジンのパフォーマンスには影響しません。

注

同期規則にある不要な属性フローは削除しないでください。 削除した規則は Microsoft Entra Connect のアップグレード時に再度作成されるため、削除ではなく無効にすることをお勧めします。

### Microsoft Entra Connect の依存関係要因

Microsoft Entra Connect のパフォーマンスは、インポートとエクスポートの実行先となる、接続されたディレクトリのパフォーマンスに依存します。 これには、インポートする Active Directory のサイズや、Microsoft Entra サービスに接続するネットワークの待ち時間などが含まれます。 プロビジョニング エンジンが使用する SQL データベースも、同期サイクルの全体的なパフォーマンスに影響します。

#### Active Directory の因子

前述のように、インポートするオブジェクトの数は、パフォーマンスにかなりの影響を及ぼします。 [Microsoft Entra Connect のハードウェアと前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)に関するページには、デプロイ規模に応じた、ハードウェアの具体的なレベルに関する概要説明が記載されています。 Microsoft Entra Connect では、「[Microsoft Entra Connect のトポロジ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-topologies)」で説明されているとおり、特定のトポロジのみがサポートされます。 サポートされていないトポロジに対するパフォーマンスの最適化と推奨事項はありません。

お使いの Microsoft Entra Connect サーバーが、インポートする Active Directory のサイズに応じたハードウェア要件を満たしていることを確認してください。 Microsoft Entra Connect サーバーと Active Directory ドメイン コントローラーの間を接続するネットワークの信頼性や速度に問題があると、インポートの速度低下の原因になります。

#### Microsoft Entra ID の要因

Microsoft Entra ID では、帯域幅調整を使用して、サービス拒否 (DoS) 攻撃からクラウド サービスを保護しています。 現在、Microsoft Entra ID のスロットル制限は、5 分あたり 6,000 書き込み (1 時間あたり 72,000) です。 たとえば、次の操作を制限することができます。

- Microsoft Entra Connect から Microsoft Entra ID へのエクスポート。
- 動的メンバーシップ グループなど、バックグラウンドでも Microsoft Entra ID を直接更新する PowerShell スクリプトやアプリケーション。
- MFA または SSPR (セルフサービス パスワード リセット) の登録など、独自の ID レコードを更新するユーザー。
- グラフィカル ユーザー インターフェイスでの操作。

重要

この調整制限の増加はサポートされていません。

Microsoft Entra Connect 同期サイクルが調整制限の影響を受けないように、デプロイとメンテナンスのタスクを計画します。 たとえば、何千ものユーザー ID を作成する大規模な人材採用活動がある場合、動的メンバーシップ グループに対する更新、ライセンスの割り当て、およびセルフサービス パスワード リセットの登録が発生する可能性があります。 これらの書き込みは、数時間または数日に分散させることをお勧めします。

#### SQL データベースの因子

ソース Active Directory トポロジのサイズは、SQL データベースのパフォーマンスに影響します。 SQL Server データベースの[ハードウェア要件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)に従って、次の推奨事項について検討してください。

- ユーザーの人数が 10 万人を超える組織では、SQL データベースとプロビジョニング エンジンを同じサーバー上に共存させることで、ネットワーク待機時間を減らすことができます。
- SQL 名前付きパイプ プロトコルは、同期サイクルに大幅な遅延が発生し、SQL Native Clients と SQL Server Network の SQL Server Configuration Manager で無効にする必要があるため、サポートされていません。 名前付きパイプの構成変更は、データベースと ADSync サービスを再起動した後にのみ反映される点にご注意ください。
- 同期プロセスのディスク入出力 (I/O) の要件が高いため、最適な結果を得るには、プロビジョニング エンジンの SQL データベース用にソリッド ステート ドライブ (SSD) を使用してください。できない場合は、RAID 0 または RAID 1 構成を検討してください。
- 完全同期は事前には行わないでください。不要なチャーンが発生し、応答時間が遅くなります。

### まとめ

Microsoft Entra Connect 実装のパフォーマンスを最適化するには、以下の推奨事項を考慮してください。

- Microsoft Entra Connect サーバーの実装サイズに応じた、[推奨されるハードウェア構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)を使用してください。
- 大規模なデプロイ環境内の Microsoft Entra Connect をアップグレードする場合は、[スウィング移行](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version#swing-migration)の手法によって、ダウンタイムを最小限に抑え、最大限の信頼性を確保することを検討してください。
- 最適な書き込みパフォーマンスを得るためには、SQL データベース用に SSD を使用してください。
- Azure Backup を使用した ADSync データベースのバックアップは推奨されません。
- ドメイン、OU、または属性のフィルター処理で Active Directory のスコープを絞り込み、Microsoft Entra ID でのプロビジョニングを必要とするオブジェクトだけが含まれるようにしてください。
- 既定の属性フロー規則を変更する必要がある場合は、最初に規則をコピーしてから、そのコピーを変更し、元の規則を無効にしてください。 忘れずに完全同期を再度実行してください。
- 最初の完全同期の実行プロファイルの計画には、十分な時間を取ってください。
- 差分同期サイクルは 30 分で完了するよう努めてください。 差分同期プロファイルが 30 分で完了しない場合は、完全な差分同期サイクルが含まれるように既定の同期の頻度を変更してください。
- Microsoft Entra ID で [Microsoft Entra Connect Sync の正常性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install)を監視してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/plan-connect-topologies"} -->
## Microsoft Entra Connect: サポートされているトポロジ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-topologies
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、Microsoft Entra Connect のサポートされているトポロジとサポートされていないトポロジについて詳しく説明します。

この記事では、主要な統合ソリューションとして Microsoft Entra Connect Sync を使用する、さまざまなオンプレミス トポロジおよび Microsoft Entra トポロジについて説明します。 この記事には、サポートされている構成とサポートされていない構成の両方が含まれています。

以下に、この記事での図の凡例を示します。

| 説明 | 記号 |
| --- | --- |
| オンプレミスの Active Directory フォレスト | [Image: オンプレミスの Active Directory フォレスト] |
| オンプレミス Active Directory とフィルター処理されたインポート | [Image: Active Directory とフィルター処理されたインポート] |
| Microsoft Entra Connect Sync サーバー | [Image: Microsoft Entra Connect Sync サーバー] |
| Microsoft Entra Connect Sync サーバーの "ステージング モード" | [Image: Microsoft Entra Connect Sync サーバーの] |
| GALSync (Microsoft Identity Manager (MIM) 2016 を使用) | [Image: MIM 2016 を使用した GALSync] |
| Microsoft Entra Connect Sync サーバー (詳細) | [Image: Microsoft Entra Connect Sync サーバー (詳細)] |
| Microsoft Entra ID | [Image: Microsoft Entra ID] |
| サポートされていないシナリオ | [Image: サポートされていないシナリオ] |

重要

Microsoft は、公式に文書化されている構成やアクションを除き、Microsoft Entra Connect Sync の変更や操作をサポートしていません。 このような構成やアクションを行うと、Microsoft Entra Connect Sync が整合性のない状態またはサポートされていない状態になる可能性があります。結果的に、Microsoft ではこのようなデプロイについてテクニカル サポートを提供できなくなります。

### 単一のフォレスト、単一の Microsoft Entra テナント

[Image: 単一のフォレストと単一のテナントのトポロジ]

最も一般的なトポロジは、1 つまたは複数のドメインを含む単一のオンプレミス フォレストと、単一の Microsoft Entra テナントです。 Microsoft Entra 認証では、パスワード ハッシュ同期が使用されます。 Microsoft Entra Connect の高速インストールでは、このトポロジのみがサポートされます。

#### 単一のフォレスト、1 つの Microsoft Entra テナントに接続された複数の同期サーバー

[Image: サポートされていない、単一のフォレストのフィルター処理されたトポロジ]

複数の Microsoft Entra Connect Sync サーバーを同じ Microsoft Entra テナントに接続することはサポートされていません。ただし、ステージング サーバーを除きます。 これらのサーバーが相互に排他的な一連のオブジェクトと同期するように構成されている場合でもサポートされません。 1 台のサーバーからフォレスト内のすべてのドメインに到達できない場合や、複数のサーバー間で負荷を分散したい場合に、このトポロジを検討したことがあるかもしれません。 (新しい Azure AD Sync Server が新しい Microsoft Entra フォレストと新しい検証済み子ドメイン用に構成されている場合、エラーは発生しません)

### 複数のフォレスト、単一の Microsoft Entra テナント

[Image: 複数のフォレストと単一のテナントのトポロジ]

多くの組織の環境には、オンプレミス Active Directory フォレストが複数存在します。 複数のオンプレミス Active Directory フォレストが使用される理由はさまざまです。 一般的な例として、アカウント リソース フォレストのある設計や、合併や買収の結果としての状況があります。

複数のフォレストがある場合は、単一の Microsoft Entra Connect Sync サーバーがすべてのフォレストに到達できる必要があります。 サーバーをドメインに参加させる必要があります。 すべてのフォレストに到達する必要がある場合は、境界ネットワーク (DMZ、非武装地帯、スクリーン サブネットとも呼ばれます) にサーバーを配置できます。

Microsoft Entra Connect のインストール ウィザードには、複数のフォレストで表されるユーザーを統合するためのオプションがいくつか用意されています。 その目的は、ユーザーが Microsoft Entra ID 内で 1 回だけ表されるようにすることです。 インストール ウィザードのカスタム インストール パスで構成できる一般的なトポロジはいくつかあります。 **[ユーザーを一意に識別]** ページで、トポロジを表す対応するオプションを選択します。 統合は、ユーザーに対してのみ構成されます。 重複したグループは、既定の構成と統合されません。

一般的なトポロジについては、分離トポロジ、フル メッシュ、およびアカウントリソース トポロジに関するセクションで説明しています。

Microsoft Entra Connect Sync 内の既定の構成では、次のことを前提としています。

- 各ユーザーが持つ有効なアカウントは 1 つのみで、このアカウントが配置されているフォレストがユーザーの認証に使用されます。 これは、パスワード ハッシュ同期、パススルー認証、およびフェデレーションを前提としています。 UserPrincipalName と sourceAnchor/immutableID は、このフォレストから取得されます。
- 各ユーザーは、メールボックスを 1 つだけ持っています。
- ユーザーのメールボックスをホストするフォレストは、Exchange のグローバル アドレス一覧 (GAL) で確認できる属性に対して最適なデータ品質を備えています。 ユーザーにメールボックスがない場合、どのフォレストを使用してもこれらの属性値を提供できます。
- リンクされたメールボックスがある場合は、別のフォレストに、サインインに使用されるアカウントもあります。

環境がこれらの前提条件と一致しない場合は、次のことが発生します。

- アクティブなアカウントまたはメールボックスが複数ある場合、同期エンジンは 1 つを選び、他は無視します。
- 他のアクティブなアカウントを持たないリンクされたメールボックスは、Microsoft Entra ID にエクスポートされません。 ユーザー アカウントは、どのグループのメンバーとしても表されません。 DirSync のリンクされたメールボックスは、常に通常のメールボックスとして表されます。 この変更は、マルチフォレスト シナリオをより適切にサポートするための意図的に異なる動作です。

詳細については、[既定の構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-default-configuration)に関するページを参照してください。

#### 複数のフォレスト、1 つの Microsoft Entra テナントに接続された複数の同期サーバー

[Image: 複数のフォレストと複数の同期サーバーのサポートされていないトポロジ]

1 つの Microsoft Entra テナントに複数の Microsoft Entra Connect Sync サーバーを接続することはサポートされていません。 例外として、 ステージング サーバーの使用があります。

このトポロジは、1 つの Microsoft Entra テナントに接続された**複数の同期サーバー**がサポートされていないという点で、以下のトポロジとは異なります。 (サポートはされていませんが、これは引き続き機能します。)

#### 複数のフォレスト、単一の同期サーバー、ユーザーは 1 つのディレクトリだけで表される

[Image: ユーザーがすべてのディレクトリで 1 度だけ示されるオプション]

[Image: 複数のフォレストと分離トポロジの説明図]

この環境では、すべてのオンプレミス フォレストが個別のエンティティとして扱われます。 他のどのフォレストにもユーザーは存在しません。 各フォレストには独自の Exchange 組織があり、フォレスト間に GALSync はありません。 このトポロジは、合併や買収後の組織や、各部署が独立して運営されている組織で見られます。 Microsoft Entra ID ではこれらのフォレストは同じ組織内にあり、統合された GAL で表示されます。 前の図では、すべてのフォレスト内の各オブジェクトが、一度メタバースに表され、ターゲットの Microsoft Entra テナントに集約されます。

#### 複数のフォレスト: ユーザーの一致

これらのすべてのシナリオで共通しているのは、配布グループとセキュリティ グループにユーザー、連絡先、および外部セキュリティ プリンシパル (FSP) を組み合わせて含めることができることです。 FSP は、セキュリティ グループ内の他のフォレストのメンバーを表すために、Active Directory Domain Services (ADDS) で使用されます。 すべての FSP は、Microsoft Entra ID 内の実際のオブジェクトに解決されます。

#### 複数のフォレスト - オプションの GALSync を使用したフル メッシュ

[Image: ユーザーの ID が複数のディレクトリに存在する場合の照合にメール属性を使用するオプション]

[Image: 複数のフォレストのフル メッシュ トポロジ]

フル メッシュ トポロジでは、ユーザーおよびリソースを任意のフォレストに配置することができます。 一般的には、フォレスト間に双方向の信頼があります。

複数のフォレストに Exchange が存在する場合は、オンプレミス GALSync ソリューションが (オプションで) 存在することがあります。 その場合、すべてのユーザーが他のすべてのフォレストにおける連絡先として表されます。 GALSync は、通常、Microsoft Identity Manager によって実装されます。 Microsoft Entra Connect は、オンプレミスの GALSync には使用できません。

このシナリオでは、ID オブジェクトはメール属性を通じて結合されます。 あるフォレストのメールボックスを持つユーザーが、他のフォレストの連絡先と結合されます。

#### 複数のフォレスト: アカウント リソース フォレスト

[Image: ID が複数のディレクトリに存在する場合の照合に ObjectSID および msExchMasterAccountSID 属性を使用するオプション]

[Image: 複数のフォレストのアカウント リソース フォレスト トポロジ]

アカウント リソース フォレスト トポロジでは、アクティブなユーザー アカウントを持つ 1 つ以上の "*アカウント*" フォレストが存在します。 また、アカウントが無効になった 1 つ以上の "*リソース*" フォレストも存在します。

このシナリオでは、1 つ (以上) のリソース フォレストがすべてのアカウント フォレストを信頼します。 リソース フォレストには、通常、Exchange および Lync を使用する拡張 Active Directory スキーマがあります。 すべての Exchange および Lync サービスと、他の共有サービスは、このフォレストに配置されます。 ユーザーのユーザー アカウントはこのフォレストで無効になり、メールボックスはアカウント フォレストにリンクされます。

### Microsoft 365 とトポロジの考慮事項

Microsoft 365 の一部のワークロードでは、サポートされるトポロジに一定の制限が生じます。

| ワークロード | Restrictions (制限) |
| --- | --- |
| エクスチェンジ・オンライン | Exchange Online でサポートされているハイブリッド トポロジの詳細については、「[Hybrid deployments with multiple Active Directory forests (複数の Active Directory フォレストを伴うハイブリッド展開)](https://learn.microsoft.com/ja-jp/Exchange/hybrid-deployment/hybrid-with-multiple-forests)」を参照してください。 |
| Skype for Business | 複数のオンプレミス フォレストを使用している場合は、アカウント リソース フォレスト トポロジのみがサポートされます。 詳細については、「[Skype for Business Server 2015 の環境要件](https://learn.microsoft.com/ja-jp/skypeforbusiness/plan-your-deployment/requirements-for-your-environment/environmental-requirements)」を参照してください。 |

大規模な組織の場合は、[Microsoft 365 PreferredDataLocation](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-preferreddatalocation) 機能を使用することを検討してください。 これにより、ユーザーのリソースが配置されているデータ センターのリージョンを定義できます。

### ステージング サーバー

[Image: トポロジでのステージング サーバー]

Microsoft Entra Connect では、*ステージング モード*でのセカンド サーバーのインストールがサポートされています。 このモードのサーバーは、接続されているすべてのディレクトリからデータを読み取りますが、接続されたディレクトリには何も書き込まれません。 通常の同期サイクルを使用するため、ID データの更新されたコピーを保持します。

プライマリ サーバーで障害が発生した場合は、ステージング サーバーにフェールオーバーできます。 これは、Microsoft Entra Connect ウィザード内で行います。 このセカンド サーバーは、インフラストラクチャをプライマリ サーバーと共有していないため、別のデータ センターに配置することができます。 プライマリ サーバーで行われたすべての構成の変更をセカンド サーバーに手動でコピーする必要があります。

ステージング サーバーは、新しいカスタム構成と、それがデータに与える影響をテストする場合に使用できます。 変化をプレビューし、構成を調整できます。 新しい構成に問題がなければ、ステージング サーバーをアクティブ サーバーにし、元のアクティブ サーバーをステージング モードに設定できます。

この方法は、アクティブな同期サーバーを交換する場合にも使用できます。 新しいサーバーを準備し、ステージング モードに設定してください。 サーバーが良好な状態であることを確認し、ステージング モードを無効 (アクティブ) にしたら、現在アクティブなサーバーをシャットダウンします。

異なるデータ センターに複数のバックアップを用意する場合は、複数のステージング サーバーを持つことができます。

### 複数の Microsoft Entra テナント

組織の Microsoft Entra ID には 1 つのテナントを置くことをお勧めします。 複数の Microsoft Entra テナントの使用を計画する前に、「[Microsoft Entra ID の管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)」を参照してください。 単一のテナントを使用できる一般的なシナリオを説明しています。

#### AD オブジェクトを複数の Microsoft Entra テナントに同期する

[Image: 複数の Microsoft Entra テナントのトポロジを示した図。]

このトポロジは、次のユース ケースを実現します。

- Microsoft Entra Connect では、1 つの Active Directory から複数の Microsoft Entra テナントにユーザー、グループ、連絡先を同期できます。 これらのテナントはそれぞれ異なる Azure 環境、たとえば 21Vianet によって運営される Microsoft Azure 環境や Azure Government 環境に存在する可能性もありますが、2 つのテナントがどちらも Azure Commercial に存在するなど、同じ Azure 環境に存在することも考えられます。 オプションの詳細については、「[Azure Government アプリケーションの ID の計画](https://learn.microsoft.com/ja-jp/azure/azure-government/documentation-government-plan-identity)」を参照してください。
- 別々のテナントに存在する単一のオブジェクトに対して同じソース アンカーを使用できます (ただし、同じテナント内の複数のオブジェクトに対しては使用できません)。 (検証済みドメインを 2 つのテナントで同じにすることはできません。同じオブジェクトに 2 つの UPN を設定できるようにするには、追加の詳細が必要です。)
- 同期する Microsoft Entra テナントごとに Microsoft Entra Connect サーバーを展開する必要があります。1 つの Microsoft Entra Connect サーバーを複数の Microsoft Entra テナントに同期することはできません。
- 異なるテナントに対して異なる同期スコープと異なる同期規則を持つことがサポートされています。
- Active Directory への書き戻しを行うように構成できる Microsoft Entra テナントの同期は、同じオブジェクトにつき 1 つだけです。 これには、デバイスとグループの書き戻しとハイブリッド Exchange の構成が含まれます。これらの機能は、1 つのテナントでのみ構成できます。 ただし、パスワード ライトバックだけは例外です。この点については後述します。
- 同じユーザー オブジェクトに対して、Active Directory から複数の Microsoft Entra テナントへのパスワード ハッシュ同期を構成することがサポートされています。 テナントに対してパスワード ハッシュ同期を有効にした場合、パスワード ライトバックも有効になります。この場合、複数のテナントでパスワード ライトバックを実行できます。つまり、あるテナントでパスワードを変更した場合、パスワード ライトバックによって Active Directory 側でもパスワードが更新され、さらに、パスワード ハッシュ同期によって他のテナントのパスワードも更新されます。
- これらのテナントが異なる Azure 環境にある場合でも、複数の Microsoft Entra テナントに同じカスタム ドメイン名を追加して検証することはサポートされていません。
- 複数のテナントでシームレス SSO や Microsoft Entra ハイブリッド参加 (非対象アプローチ) など、AD のフォレスト レベルの構成を利用するハイブリッド エクスペリエンスを構成することはサポートされていません。 この構成を利用すると、他のテナントの構成が上書きされ、使用できなくなってしまいます。 その他の情報については、「[Microsoft Entra ハイブリッド参加の実装を計画する](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan#hybrid-azure-ad-join-for-single-forest-multiple-azure-ad-tenants)」を参照してください。
- デバイス オブジェクトを複数のテナントに同期できますが、1 つのデバイスは 1 つのテナントに対してのみ Microsoft Entra ハイブリッド参加できます。
- 各 Microsoft Entra Connect インスタンスは、ドメインに参加しているコンピューター上で実行されている必要があります。

注

グローバル アドレス一覧同期 (GalSync) はこのトポロジでは自動的に行われず、各テナントが Exchange Online と Skype for Business Online で完全なグローバル アドレス一覧 (GAL) を持っていることを確認するために、追加のカスタム MIM 実装が必要です。

#### 書き戻しの使用による GALSync

[Image: 複数のフォレストと複数のディレクトリのサポートされていないトポロジ (GALSync は Microsoft Entra ID に重点を置いている)][Image: 複数のフォレストと複数のディレクトリのサポートされていないトポロジ (GALSync はオンプレミスの Azure AD に重点を置いている)]

#### GALSync とオンプレミスの同期サーバー

[Image: 複数のフォレストと複数のディレクトリのトポロジにおける GALSync]

オンプレミスの Microsoft Identity Manager を使用して、2 つの Exchange 組織間でユーザーを同期できます (GALSync 経由)。 1 つの組織内のユーザーは、他の組織では外部ユーザーおよび連絡先として表示されます。 これらの異なるオンプレミス Active Directory インスタンスは、独自の Microsoft Entra テナントと同期できます。

#### 承認されていないクライアントを使用した Microsoft Entra Connect バックエンドへのアクセス

[Image: 承認されていないクライアントを使用した Microsoft Entra Connect バックエンドへのアクセス]

Microsoft Entra Connect サーバーは、Microsoft Entra Connect バックエンドを介して Microsoft Entra ID と通信します。 このバックエンドとの通信に使用できるソフトウェアは Microsoft Entra Connect のみです。 他のソフトウェアまたは方法を使用して Microsoft Entra Connect バックエンドと通信することはサポートされていません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/plan-connect-user-signin"} -->
## Microsoft Entra Connect: ユーザー サインイン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-user-signin
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: カスタム設定用の Microsoft Entra Connect ユーザー サインイン。

Microsoft Entra Connect を使用すると、ユーザーは同じパスワードを使用してクラウドとオンプレミスの両方のリソースにサインインできます。 この記事では、Microsoft Entra ID へのサインインに使用する ID を選択するのに役立つ、各 ID モデルの主要な概念について説明します。

Microsoft Entra ID モデルを既に理解していて、特定の方法の詳細については、適切なリンクを参照してください。

- シームレス シングル サインオン (SSO) による[パスワード ハッシュの同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)
- [シームレス シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) による[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)
- フェデレーション SSO (Active Directory フェデレーション サービス (AD FS) を使用)
- PingFederate によるフェデレーション

注

Microsoft Entra ID のフェデレーションを構成することで、Microsoft Entra テナントとフェデレーション ドメインの間に信頼が確立されることを覚えておく必要があります。 この信頼により、フェデレーション ドメイン ユーザーはテナント内の Microsoft Entra クラウド リソースにアクセスできます。

### 組織のユーザー サインイン方法の選択

Microsoft Entra Connect を実装する最初の決定は、ユーザーがサインインに使用する認証方法を選択することです。 組織のセキュリティと高度な要件を満たす適切な方法を選択することが重要です。 クラウド内のアプリとデータにアクセスするためにユーザーの ID を検証するため、認証は重要です。 適切な認証方法を選択するには、時間、既存のインフラストラクチャ、複雑さ、選択した実装コストを考慮する必要があります。 これらの要因は組織ごとに異なり、時間の経過とともに変化する場合があります。

Microsoft Entra ID では、次の認証方法がサポートされています。

- **クラウド認証**- この認証方法を選択すると、Microsoft Entra ID がユーザーのサインインの認証プロセスを処理します。 クラウド認証では、次の 2 つのオプションから選択できます。
    - **パスワード ハッシュ同期 (PHS)** - パスワード ハッシュ同期を使用すると、ユーザーはオンプレミスで使用するのと同じユーザー名とパスワードを使用でき、Microsoft Entra Connect 以外の追加のインフラストラクチャをデプロイする必要はありません。
    - **パススルー認証 (PTA)** - このオプションはパスワード ハッシュ同期に似ていますが、強力なセキュリティとコンプライアンス ポリシーを持つ組織に対して、オンプレミスのソフトウェア エージェントを使用した簡単なパスワード検証を提供します。
- **フェデレーション認証** - この認証方法を選択すると、Microsoft Entra ID は、ユーザーのサインインを検証するために、AD FS やサード パーティのフェデレーション システムなどの別の信頼された認証システムに認証プロセスを渡します。

Microsoft 365、SaaS アプリケーション、およびその他の Microsoft Entra ID ベースのリソースへのユーザー サインインを有効にするだけのほとんどの組織では、既定のパスワード ハッシュ同期オプションをお勧めします。

認証方法の選択の詳細については、「[Microsoft Entra ハイブリッド ID ソリューションに適した認証方法を選択](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/choose-ad-authn)する」を参照してください。

#### パスワード ハッシュの同期

パスワード ハッシュの同期では、ユーザー パスワードのハッシュがオンプレミスの Active Directory から Microsoft Entra ID に同期されます。 パスワードがオンプレミスで変更またはリセットされると、新しいパスワード ハッシュが Microsoft Entra ID にすぐに同期されるため、ユーザーはクラウド リソースとオンプレミス リソースに対して常に同じパスワードを使用できます。 パスワードがクリア テキスト形式で Microsoft Entra ID に送信されたり、Microsoft Entra ID に格納されたりすることはありません。 パスワード ハッシュ同期とパスワード ライトバックを使用して、Microsoft Entra ID でセルフサービス パスワード リセットを有効にすることができます。

さらに、企業ネットワーク上にあるドメイン参加済みマシン上のユーザーに対して [シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) を有効にすることができます。 シングル サインオンでは、有効になっているユーザーは、クラウド リソースに安全にアクセスできるようにユーザー名を入力するだけで済みます。

[Image: パスワード ハッシュ同期]

詳細については、 [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization) に関する記事を参照してください。

#### パススルー認証

パススルー認証では、ユーザーのパスワードがオンプレミスの Active Directory コントローラーに対して検証されます。 パスワードはどの形式でも Microsoft Entra ID に存在する必要はありません。 これにより、クラウド サービスへの認証時に、サインイン時間の制限などのオンプレミス ポリシーを評価できます。

パススルー認証では、オンプレミス環境の Windows Server ドメイン参加済みマシン上の単純なエージェントを使用します。 このエージェントは、パスワード検証要求をリッスンします。 インターネットに対して受信ポートを開く必要はありません。

さらに、企業ネットワーク上にあるドメイン参加済みマシン上のユーザーに対してシングル サインオンを有効にすることもできます。 シングル サインオンでは、有効になっているユーザーは、クラウド リソースに安全にアクセスできるようにユーザー名を入力するだけで済みます。 [Image: パススルー認証]

詳細については、以下を参照してください。

- [パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)
- [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)

#### Windows Server で AD FS を使用する新規または既存のファームを使用するフェデレーション

フェデレーション サインインを使用すると、ユーザーはオンプレミスのパスワードを使用して Microsoft Entra ID ベースのサービスにサインインできます。 企業ネットワーク上にいる時には、パスワードを入力する必要すらありません。 AD FS でフェデレーション オプションを使用すると、Windows Server 2025 または Windows Server 2022 で AD FS を使用して新規または既存のファームを展開できます。 既存のファームを指定することを選択する場合、ユーザーがサインインできるように Microsoft Entra Connect によってファームと Microsoft Entra ID との間の信頼が構成されます。
 [Image: Windows Server での AD FS とのフェデレーション]
##### Windows Server で AD FS とのフェデレーションを展開する

新しいファームをデプロイする場合は、次のものが必要です。

- フェデレーション サーバー用の Windows Server 2025 または Windows Server 2022 サーバー。
- Web アプリケーション プロキシ用の Windows Server 2025 または Windows Server 2022 サーバー。
- 目的のフェデレーション サービス名に対して 1 つの TLS/SSL 証明書を含む .pfx ファイル。 例: fs.contoso.com。

新しいファームをデプロイする場合、または既存のファームを使用する場合は、次のものが必要です。

- フェデレーション サーバー上のローカル管理者の資格情報。
- Web アプリケーション プロキシ ロールを展開するワークグループ サーバー (ドメインに参加していない) 上のローカル管理者の資格情報。
- ウィザードを実行して、Windows リモート管理を使用して AD FS または Web アプリケーション プロキシをインストールする他のマシンに接続できるようにするコンピューター。

詳細については、「 [AD FS での SSO の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#configuring-federation-with-ad-fs)参照してください。

#### PingFederate によるフェデレーション

フェデレーション サインインを使用すると、ユーザーはオンプレミスのパスワードを使用して Microsoft Entra ID ベースのサービスにサインインできます。 企業ネットワーク上にいる時には、パスワードを入力する必要すらありません。

Microsoft Entra ID で使用するように PingFederate を構成する方法の詳細については、「 [Ping ID のサポート」を](https://support.pingidentity.com/s/)参照してください。

PingFederate を使用して Microsoft Entra Connect を設定する方法については、[Microsoft Entra Connect のカスタム インストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#configuring-federation-with-pingfederate)を参照してください。

##### 以前のバージョンの AD FS またはサード パーティのソリューションを使用してサインインする

以前のバージョンの AD FS (AD FS 2.0 など) またはサード パーティのフェデレーション プロバイダーを使用してクラウド サインインを既に構成している場合は、Microsoft Entra Connect を使用してユーザー のサインイン構成をスキップすることを選択できます。 これにより、サインインに既存のソリューションを引き続き使用しながら、Microsoft Entra Connect の最新の同期やその他の機能を取得できます。

詳細については、 [Microsoft Entra のサード パーティのフェデレーション互換性リスト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-compatibility)を参照してください。

### ユーザーのサインインと UserPrincipalName

#### UserPrincipalName について

Active Directory では、既定の UserPrincipalName (UPN) サフィックスは、ユーザー アカウントが作成されたドメインの DNS 名です。 ほとんどの場合、これはインターネット上のエンタープライズ ドメインとして登録されているドメイン名です。 ただし、Active Directory ドメインと信頼を使用して、UPN サフィックスをさらに追加できます。

ユーザーの UPN には、username@domain形式があります。 たとえば、"contoso.com" という名前の Active Directory ドメインの場合、John という名前のユーザーは UPN "john@contoso.com" を持っている可能性があります。 ユーザーの UPN は RFC 822 に基づいています。 UPN と電子メールは同じ形式を共有しますが、ユーザーの UPN の値は、ユーザーのメール アドレスと同じである場合とそうでない場合があります。

#### Microsoft Entra ID のユーザープリンシパル名

Microsoft Entra Connect ウィザードでは、userPrincipalName 属性を使用するか、オンプレミスから使用する属性 (カスタム インストール) を Microsoft Entra ID の UserPrincipalName として指定できます。 これは、Microsoft Entra ID へのサインインに使用される値です。 userPrincipalName 属性の値が Microsoft Entra ID の検証済みドメインに対応していない場合は、Microsoft Entra ID によって既定の .onmicrosoft.com 値に置き換えられます。

Microsoft Entra ID 内のすべてのディレクトリには、contoso.onmicrosoft.com 形式の組み込みのドメイン名が付属しています。これにより、Microsoft Entra やその他の Microsoft オンライン サービスの使用を開始できます。 カスタム ドメインを使用すると、サインイン エクスペリエンスを向上させ、簡素化できます。 Microsoft Entra ID のカスタム ドメイン名とドメインの確認方法については、「 [カスタム ドメイン名を Microsoft Entra ID に追加する](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)」を参照してください。

### Microsoft Entra のサインインの構成

#### Microsoft Entra Connect を使用した Microsoft Entra サインイン構成

Microsoft Entra サインイン エクスペリエンスは、Microsoft Entra ID が、Microsoft Entra ディレクトリで検証されるカスタム ドメインのいずれかに同期されているユーザーの UserPrincipalName サフィックスと一致できるかどうかによって異なります。 Microsoft Entra Connect では、Microsoft Entra のサインイン設定を構成するときにヘルプが提供されるため、クラウドでのユーザー サインイン エクスペリエンスはオンプレミスのエクスペリエンスと似ています。

Microsoft Entra Connect には、ドメインに対して定義されている UPN サフィックスが一覧表示され、Microsoft Entra ID のカスタム ドメインとの照合が試行されます。 その後、実行する必要がある適切なアクションに役立ちます。 Microsoft Entra サインイン ページには、オンプレミスの Active Directory に対して定義されている UPN サフィックスが一覧表示され、各サフィックスに対応する状態が表示されます。 状態の値には、次のいずれかを指定できます。

| 状態 | 説明 | 必要な対処 |
| --- | --- | --- |
| 検証済み | Microsoft Entra Connect によって、Microsoft Entra ID に一致する検証済みドメインが見つかりました。 このドメインのすべてのユーザーは、オンプレミスの資格情報を使用してサインインできます。 | アクションは必要ありません。 |
| 未検証 | Microsoft Entra Connect では、Microsoft Entra ID に一致するカスタム ドメインが見つかりましたが、検証されていません。 ドメインが検証されていない場合、このドメインのユーザーの UPN サフィックスは、同期後に既定の .onmicrosoft.com サフィックスに変更されます。 | [Microsoft Entra ID でカスタム ドメインを確認します。](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain#verify-your-custom-domain-name) |
| 未追加 | Microsoft Entra Connect では、UPN サフィックスに対応するカスタム ドメインが見つかりませんでした。 ドメインが Entra ID で追加および検証されていない場合、このドメインのユーザーの UPN サフィックスは既定の .onmicrosoft.com サフィックスに変更されます。 | [UPN サフィックスに対応するカスタム ドメインを追加して確認します。](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain) |

Microsoft Entra サインイン ページには、オンプレミスの Active Directory に対して定義されている UPN サフィックスと、Microsoft Entra ID の対応するカスタム ドメインと、現在の検証状態が一覧表示されます。 カスタム インストールでは、 **Microsoft Entra サインイン** ページで UserPrincipalName の属性を選択できるようになりました。

[Image: Microsoft Entra サインイン ページ]

[更新] ボタンをクリックすると、Microsoft Entra ID からカスタム ドメインの最新の状態を再フェッチできます。

#### Microsoft Entra ID で UserPrincipalName の属性を選択する

userPrincipalName 属性は、ユーザーが Microsoft Entra ID と Microsoft 365 にサインインするときに使用する属性です。 ユーザーが同期される前に、Microsoft Entra ID で使用されているドメイン (UPN サフィックスとも呼ばれます) を確認する必要があります。

既定の属性 userPrincipalName のままにすることを強くお勧めします。 この属性がルーティング不可能で、検証できない場合は、サインイン ID を保持する属性として別の属性 (電子メールなど) を選択できます。 これは代替 ID と呼ばれます。 代替 ID 属性値は RFC 822 標準に従う必要があります。 サインイン ソリューションとして、パスワード SSO とフェデレーション SSO の両方で代替 ID を使用できます。

注

代替 ID の使用は、すべての Microsoft 365 ワークロードと互換性がありません。 詳細については、「 [代替ログイン ID の構成」を](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id)参照してください。

##### さまざまなカスタム ドメインの状態と Entra ID サインイン エクスペリエンスへの影響

Microsoft Entra ディレクトリ内のカスタム ドメインの状態と、オンプレミスで定義されている UPN サフィックスの関係を理解することは非常に重要です。 Microsoft Entra Connect を使用して同期を設定するときに、考えられるさまざまな Entra ID サインイン エクスペリエンスについて説明します。

次の情報では、UPN サフィックス contoso.com について考えてみましょう。これは、オンプレミス ディレクトリで UPN の一部として使用されます。たとえば、 user@contoso.com。

###### 高速設定/パスワード ハッシュ同期

| 状態 | ユーザー Entra ID サインイン エクスペリエンスへの影響 |
| --- | --- |
| 未追加 | この場合、Microsoft Entra ディレクトリに contoso.com のカスタム ドメインは追加されていません。 サフィックス @contoso.com を持つオンプレミスの UPN を持つユーザーは、オンプレミスの UPN を使用して Entra ID にサインインすることはできません。 代わりに、既定の Microsoft Entra ディレクトリのサフィックスを追加することで、Microsoft Entra ID によって提供される新しい UPN を使用する必要があります。 たとえば、ユーザーを Microsoft Entra ディレクトリ contoso.onmicrosoft.com に同期している場合、オンプレミスのユーザー user@contoso.com には user@contoso.onmicrosoft.com の UPN が与えられます。 |
| 未検証 | この場合、Microsoft Entra ディレクトリに追加されるカスタム ドメイン contoso.com があります。 ただし、まだ検証されていません。 ドメインを確認せずにユーザーの同期を進める場合、"追加されていない" シナリオと同様に、ユーザーには Microsoft Entra ID によって新しい UPN が割り当てられます。 |
| 検証済み | この場合、UPN サフィックスの Microsoft Entra ID で既に追加および検証されているカスタム ドメイン contoso.com があります。 ユーザーは、オンプレミスの UserPrincipalName ( user@contoso.com など) を使用して、Microsoft Entra ID に同期した後に Entra にサインインできます。 |

###### AD FS のフェデレーション

Microsoft Entra ID の既定の .onmicrosoft.com ドメインまたは Microsoft Entra ID の未確認のカスタム ドメインとのフェデレーションを作成することはできません。 Microsoft Entra Connect ウィザードを実行しているときに、フェデレーションを作成する未確認のドメインを選択すると、ドメインの DNS がホストされている場所で作成するために必要なレコードが Microsoft Entra Connect によって求められます。 詳細については、「 [フェデレーション用に選択された Microsoft Entra ドメインを確認する」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#verify-the-azure-ad-domain-selected-for-federation)参照してください。

ユーザー サインイン オプション [ **AD FS とのフェデレーション**] を選択した場合、Microsoft Entra ID でフェデレーションを作成し続けるには、カスタム ドメインが必要です。 ここでは、Microsoft Entra ディレクトリにカスタム ドメイン contoso.com 追加する必要があることを意味します。

| 状態 | ユーザー Entra ID サインイン エクスペリエンスへの影響 |
| --- | --- |
| 未追加 | この場合、Microsoft Entra Connect は、Microsoft Entra ディレクトリに UPN サフィックス contoso.com の一致するカスタム ドメインを見つけることができませんでした。 ユーザーがオンプレミスの UPN ( user@contoso.com など) で AD FS を使用してサインインする必要がある場合は、カスタム ドメイン contoso.com を追加する必要があります。 |
| 未検証 | この場合、Microsoft Entra Connect は、後の段階でドメインを確認する方法に関する適切な詳細を求められます。 |
| 検証済み | この場合は、追加の操作を行わずに構成を進めることができます。 |

### ユーザーのサインイン方法の変更

ウィザードを使用した Microsoft Entra Connect の初期構成後に Microsoft Entra Connect で使用できるタスクを使用して、フェデレーション、パスワード ハッシュ同期、またはパススルー認証からユーザー サインイン方法を変更できます。 Microsoft Entra Connect ウィザードをもう一度実行すると、実行できるタスクの一覧が表示されます。 タスクの一覧から **[ユーザー サインインの変更** ] を選択します。

[Image: ユーザー サインインを変更する]

次のページでは、Microsoft Entra ID の資格情報を入力するように求められます。

[Image: Microsoft Entra ID の資格情報を入力する場所を示すスクリーンショット。]

[ **ユーザー サインイン** ] ページで、目的のユーザー サインインを選択します。

[Image: Microsoft Entra ID に接続する]

注

パスワード ハッシュ同期に一時的に切り替えるだけの場合は、[ **ユーザー アカウントを変換しない** ] チェック ボックスをオンにします。 このオプションをオンにしないと、各ユーザーがフェデレーションに変換され、数時間かかることがあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/plan-connect-userprincipalname"} -->
## Microsoft Entra の UserPrincipalName の設定 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-userprincipalname
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: 次のドキュメントでは、UserPrincipalName 属性がどのように設定されるかについて説明します。

この記事では、Microsoft Entra ID での UserPrincipalName 属性の設定方法について説明します。 UserPrincipalName 属性の値は、ユーザー アカウントに対する Microsoft Entra のユーザー名です。

### UPN の用語

この記事では、次の用語を使用します。

| 用語 | 説明 |
| --- | --- |
| 初期ドメイン | Microsoft Entra テナントでの既定のドメイン (onmicrosoft.com)。 たとえば、"contoso.onmicrosoft.com" などです。 |
| マイクロソフトオンラインメールルーティングアドレス (MOERA) | Microsoft Entra ID は、Microsoft Entra MailNickName 属性と Microsoft Entra 初期ドメインから MOERA を "&lt;MailNickName&gt;@&lt;initial domain&gt;" として計算します。 |
| オンプレミスの mailNickName 属性 | Active Directory 内の属性。Exchange 組織におけるユーザーの別名は、この属性の値によって表されます。 |
| オンプレミスのメール属性 | Active Directory 内の属性。ユーザーのメール アドレスは、この属性の値によって表されます。 |
| プライマリ SMTP アドレス | Exchange 受信者オブジェクトの通常のメール アドレス。 たとえば、"SMTP:user@contoso.com" などです。 |
| 代替ログイン ID | UserPrincipalName 以外でログインに使用されるオンプレミスの属性 (mail 属性など)。 |

### UserPrincipalName とは

UserPrincipalName は、インターネット標準 [RFC 822](https://datatracker.ietf.org/doc/html/rfc2822) に基づく属性で、ユーザーのインターネット形式のログイン名となります。

#### UPN の形式

UPN は、UPN プレフィックス (ユーザー アカウント名) と UPN サフィックス (DNS ドメイン名) とから成ります。 プレフィックスとサフィックスは、"@" 記号を使用して結合されます (例: "someone@example.com")。 UPN は、ディレクトリ フォレスト内のすべてのセキュリティ プリンシパル オブジェクトの中で一意であることが必要です。

### Microsoft Entra ID の UPN

UPN は、ユーザーがログインできるようにするために Microsoft Entra ID によって使用されます。 ユーザーが使用できる UPN は、ドメインが検証されているかどうかによって異なります。 ドメインが検証されると、そのサフィックスを持つユーザーは Microsoft Entra ID にログインできるようになります。

この属性は、Microsoft Entra Connect によって同期されます。 検証済みのドメインとそうでないドメインは、インストール中に確認することができます。

[Image: 未検証のドメイン]

### 代替ログイン ID

環境によっては、エンド ユーザーは電子メール アドレスのみを認識し、UPN を認識していない場合があります。 電子メール アドレスの使用は、企業のポリシーまたはオンプレミス基幹業務アプリの依存関係によって求められることがあります。

代替ログイン ID を使用すると、ユーザーがメールなどの UPN 以外の属性でログイン可能になるログイン エクスペリエンスを構成できます。

Microsoft Entra ID で代替ログイン ID を有効にするには、Microsoft Entra Connect を使用するときに追加の構成手順は必要ありません。 代替 ID は、ウィザードから直接構成することができます。 ユーザーの Microsoft Entra ログイン構成は、[同期] セクションに表示されます。**[ユーザー プリンシパル名]** ドロップダウンで、代替ログイン ID の属性を選びます。

[Image: ユーザープリンシパル名の一覧が強調表示されているスクリーンショット。ここで、代替ログイン ID 属性を選択します。]

詳細については、「[代替ログイン ID](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id) の構成」および「[Microsoft Entra ログイン構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#azure-ad-sign-in-configuration)」を参照してください。

### 未検証の UPN サフィックス

オンプレミスの UserPrincipalName 属性/代替ログイン ID サフィックスが Microsoft Entra テナントで検証されていない場合、Microsoft Entra UserPrincipalName 属性値にこのドメイン サフィックスを付けることはできません。 Microsoft Entra ID は、プレフィックスとして Microsoft Entra MailNickName 属性値に基づいて新しい UPN を計算し、ドメイン サフィックスとして Microsoft Entra 初期ドメイン (&lt;MailNickName&gt;@&lt;initial domain&gt;を計算します。

### 検証済みの UPN サフィックス

Microsoft Entra テナントで、オンプレミスの UserPrincipalName 属性と代替ログイン ID のサフィックスが検証されている場合は、Microsoft Entra の UserPrincipalName 属性の値は、オンプレミスの UserPrincipalName 属性および代替ログイン ID と同じになります。

警告

オンプレミスの UserPrincipalName に存在する無効な文字 (空白、改行など) は、同期された UPN 値を無効にします。 このような場合、Microsoft Entra ID は、ドメイン サフィックスが Microsoft Entra テナントで検証されないシナリオと同様に、新しい UPN を計算します。

### Microsoft Entra の MailNickName 属性値の計算

Microsoft Entra UserPrincipalName 属性値は `<MailNickName>@<initial domain>`に再計算できるため、UPN プレフィックスとなる Microsoft Entra MailNickName 属性値がどのように計算されるかを理解することが重要です。

ユーザー オブジェクトが Microsoft Entra テナントに初めて同期されるときは、Microsoft Entra ID によって以下の項目がこの順序でチェックされて、最初に見つかったものに MailNickName 属性の値が設定されます。

- オンプレミスの mailNickName 属性
- プライマリ SMTP アドレスのプレフィックス
- オンプレミスメール属性の接頭辞
- オンプレミスの userPrincipalName 属性/代替ログイン ID のプレフィックス
- セカンダリ SMTP アドレスのプレフィックス

ユーザー オブジェクトへの更新が Microsoft Entra テナントに同期されるときは、オンプレミスの mailNickName 属性の値が更新された場合にだけ、Microsoft Entra ID によって MailNickName 属性の値が更新されます。

重要

オンプレミスの UserPrincipalName 属性または代替ログイン ID の値に対する更新が Microsoft Entra テナントに同期される場合にのみ、Microsoft Entra ID によって UserPrincipalName 属性値が再計算されます。

Microsoft Entra ID が UserPrincipalName 属性を再計算し、ユーザーに Exchange ライセンスが割り当てられている場合は常に、新しい UserPrincipalName 値もセカンダリ SMTP プロキシ アドレスとして追加されます。

検証済みドメインの変更操作 (たとえば、新しい検証済みドメインの追加や既存のドメインの削除) の場合、Microsoft Entra ID では、テナント上のすべてのユーザーの UserPrincipalName 属性も再計算されます。 詳しくは、「[トラブルシューティング: 検証済みドメインの変更に関する監査データ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/troubleshoot-audit-data-verified-domain)」をご覧ください

### UPN のシナリオ

以降、UPN がどのように計算されるかの例を、設定したシナリオごとに紹介します。

#### シナリオ 1: 未検証 UPN サフィックス – 初期同期

[Image: シナリオ 1]

オンプレミス ユーザー オブジェクト:

- mailNickName: &lt;未設定&gt;
- proxyAddresses : {SMTP:user1@contoso.com}
- 電子メール: user2@contoso.com
- ユーザープリンシパル名: user3@contoso.com

Microsoft Entra テナントにユーザー オブジェクトを初めて同期した

- Microsoft Entra の MailNickName 属性をプライマリ SMTP アドレスのプレフィックスに設定します。
- MOERA を &lt;MailNickName&gt;@&lt;initial domain&gt; に設定します。
- Microsoft Entra の UserPrincipalName 属性を MOERA に設定します。

Microsoft Entra テナントのユーザー オブジェクト:

- MailNickName: user1
- ユーザープリンシパル名: user1@contoso.onmicrosoft.com

#### シナリオ 2: 未検証 UPN サフィックス - オンプレミスの mailNickName 属性を設定する

[Image: シナリオ 2]

オンプレミス ユーザー オブジェクト:

- メールニックネーム：user4
- proxyAddresses : {SMTP:user1@contoso.com}
- 電子メール: user2@contoso.com
- ユーザープリンシパル名: user3@contoso.com

オンプレミスの mailNickName 属性での更新を Microsoft Entra テナントに同期する

- Microsoft Entra の MailNickName 属性を、オンプレミスの mailNickName 属性で更新します。
- オンプレミスの userPrincipalName 属性は更新されないため、Microsoft Entra UserPrincipalName 属性は変更されません。

Microsoft Entra テナントのユーザー オブジェクト:

- MailNickName: user4
- ユーザープリンシパル名: user1@contoso.onmicrosoft.com

#### シナリオ 3: 未検証 UPN サフィックス - オンプレミスの userPrincipalName 属性を更新する

[Image: シナリオ 3]

オンプレミス ユーザー オブジェクト:

- メールニックネーム：user4
- proxyAddresses : {SMTP:user1@contoso.com}
- 電子メール: user2@contoso.com
- ユーザープリンシパル名: user5@contoso.com

オンプレミスの userPrincipalName 属性での更新を Microsoft Entra テナントに同期する

- オンプレミスの userPrincipalName 属性を更新すると、MOERA と Microsoft Entra の UserPrincipalName 属性の再計算がトリガーされます。
- MOERA を &lt;MailNickName&gt;@&lt;initial domain&gt; に設定します。
- Microsoft Entra の UserPrincipalName 属性を MOERA に設定します。

Microsoft Entra テナントのユーザー オブジェクト:

- MailNickName: user4
- ユーザープリンシパル名: user4@contoso.onmicrosoft.com

#### シナリオ 4: 未検証の UPN サフィックス - プライマリ SMTP アドレスとオンプレミスの mail 属性を更新する

[Image: シナリオ 4]

オンプレミス ユーザー オブジェクト:

- メールニックネーム：user4
- proxyAddresses : {SMTP:user6@contoso.com}
- 電子メール: user7@contoso.com
- ユーザープリンシパル名: user5@contoso.com

オンプレミスのメール属性とプライマリ SMTP アドレスに対する更新を、Microsoft Entra テナントに同期する

- ユーザー オブジェクトの初期同期の後で、オンプレミスの mail 属性とプライマリ SMTP アドレスを更新しても、Microsoft Entra の MailNickName 属性または UserPrincipalName 属性は影響を受けません。

Microsoft Entra テナントのユーザー オブジェクト:

- MailNickName: user4
- ユーザープリンシパル名: user4@contoso.onmicrosoft.com

#### シナリオ 5: 検証済みの UPN サフィックス - オンプレミスの userPrincipalName 属性のサフィックスを更新

[Image: シナリオ 5]

オンプレミス ユーザー オブジェクト:

- メールニックネーム：user4
- proxyAddresses : {SMTP:user6@contoso.com}
- 電子メール: user7@contoso.com
- ユーザープリンシパル名: user5@verified.contoso.com

オンプレミスの userPrincipalName 属性での更新を Microsoft Entra テナントに同期する

- オンプレミスの userPrincipalName 属性を更新すると、Microsoft Entra の UserPrincipalName 属性の再計算がトリガーされます。
- Microsoft Entra テナントで UPN サフィックスが検証されているため、Microsoft Entra の UserPrincipalName 属性がオンプレミスの userPrincipalName 属性に設定されます。

Microsoft Entra テナントのユーザー オブジェクト:

- MailNickName: user4
- ユーザープリンシパル名: user5@verified.contoso.com
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-accounts-permissions"} -->
## Microsoft Entra Connect: アカウントとアクセス許可 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: 使用および作成されるアカウントと、Microsoft Entra Connect のインストールと使用に必要なアクセス許可について説明します。

使用および作成されるアカウントと、Microsoft Entra Connect のインストールと使用に必要なアクセス許可について説明します。

[Image: Microsoft Entra Connect に必要なアカウントの概要を示す図。]

### Microsoft Entra Connect に使用されるアカウント

Microsoft Entra Connect では、オンプレミスの Windows Server Active Directory (Windows Server AD) から Microsoft Entra ID に "情報を同期" させるために 3 つのアカウントが使用されます。

- **AD DS コネクタ アカウント**: Active Directory Domain Services (AD DS) を使用した Windows Server AD に対する情報の読み取りと書き込みに使用されます。
- **ADSync サービス アカウント**: 同期サービスの実行と SQL Server データベースへのアクセスに使用されます。
- **Microsoft Entra コネクタ アカウント**: Microsoft Entra ID に情報を書き込むために使用されます。

Microsoft Entra Connect を "インストール" するには、次のアカウントも必要です。

- **ローカル管理者アカウント**: Microsoft Entra Connect をインストールし、コンピューターのローカル管理者アクセス許可を持っている管理者。
- **AD DS エンタープライズ管理者アカウント**: 必要な AD DS コネクタ アカウントを作成するために必要に応じて使用されます。
- **Microsoft Entra ハイブリッド ID 管理者アカウント**: Microsoft Entra コネクタ アカウントを作成するため、および Microsoft Entra ID を構成するために使用されます。 ハイブリッド ID の管理者として [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。 「[Microsoft Entra ロールの割り当てを一覧表示する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments)」を参照してください。
- **SQL SA アカウント (任意)**: 完全版の SQL Server を使用している場合、ADSync データベースの作成に使用されます。 SQL Server のインスタンスは、Microsoft Entra Connect のインストールに対してローカルでもリモートでもかまいません。 このアカウントは、エンタープライズ管理者アカウントと同じアカウントにすることもできます。

    現在では、SQL Server 管理者が帯域外でデータベースをプロビジョニングし、その後、Microsoft Entra Connect 管理者がインストールできます (そのアカウントがデータベース所有者 (DBO) 権限を持つ場合)。 詳しくは、「[SQL によって委任された管理者のアクセス許可を使用した Microsoft Entra Connect のインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-sql-delegation)」をご覧ください。

重要

ビルド 1.4.###.# 以降では、エンタープライズ管理者アカウントまたはドメイン管理者アカウントは、AD DS コネクタ アカウントとして使用できなくなりました。 **既存のアカウントを使用**するためにエンタープライズ管理者またはドメイン管理者であるアカウントを入力しようとすると、ウィザードにエラー メッセージが表示され、続行できません。

注

"エンタープライズ アクセス モデル" を使用して、Microsoft Entra Connect で使用される管理アカウントを管理できます。 組織は、エンタープライズ アクセス モデルを使用して、運用環境よりもセキュリティ制御が強化されている環境で管理アカウント、ワークステーション、およびグループをホストできます。 詳細については、「[エンタープライズ アクセス モデル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-access-model#esae-administrative-forest-design-approach)」を参照してください。

ハイブリッド ID 管理者ロールは、初期セットアップ後は必要ありません。 セットアップ後に必要なアカウントは、[ディレクトリ同期アカウント ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-synchronization-accounts)のアカウントのみです。 ハイブリッド ID管理者ロールを持つアカウントを削除する代わりに、より低いレベルのアクセス許可を持つロールにロールを変更することをお勧めします。 アカウントを完全に削除すると、ウィザードをもう一度実行する必要がある場合に問題が発生するおそれがあります。 Microsoft Entra Connect ウィザードをもう一度使用する必要がある場合は、アクセス許可を追加できます。

### Microsoft Entra Connect のインストール

Microsoft Entra Connect インストール ウィザードには次の 2 つのパスが用意されています。

- **簡単設定**: Microsoft Entra Connect の簡単設定では、インストールを簡単に構成できるように、より多くのアクセス許可がウィザードに必要です。 ウィザードによってユーザーの作成とアクセス許可の設定が行われるので、自分で行う必要はありません。
- **カスタム設定**: Microsoft Entra Connect のカスタム設定では、ウィザードの選択肢とオプションがより多くなります。 ただし、一部のシナリオでは、自分が適切なアクセス許可を持っていることを確認することが重要です。

### 簡単設定

簡単設定では、インストール ウィザードで次の情報を入力します。

- AD DS エンタープライズ管理者の資格情報
- Microsoft Entra テナントのハイブリッド ID 管理者の資格情報。

#### AD DS エンタープライズ管理者の資格情報

AD DS エンタープライズ管理者アカウントは、Windows Server AD の構成に使用されます。 これらの資格情報が使用されるのは、インストール中のみです。 ドメイン管理者ではなく、エンタープライズ管理者が、すべてのドメインで Windows Server AD 内のアクセス許可が設定できることを確認する必要があります。

DirSync からアップグレードする場合は、AD DS エンタープライズ管理者の資格情報を使用して、DirSync で使用されていたアカウントのパスワードをリセットします。 Microsoft Entra ハイブリッド ID管理者の資格情報も必要になります。

#### Microsoft Entra テナントのハイブリッド ID 管理者の資格情報。

Microsoft Entra ハイブリッド ID 管理者アカウント用の資格情報は、インストール中にのみ使用されます。 アカウントは、Microsoft Entra ID への変更を同期する Microsoft Entra コネクタ アカウントを作成するために使用されます。 また、このアカウントにより、同期が Microsoft Entra ID の機能として有効化されます。

詳細については、「[Hybrid Identity Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)」 (ハイブリッド ID 管理者) を参照してください。

#### AD DS コネクタ アカウントの必須のアクセス許可 (簡単設定の場合)

AD DS Connector アカウントは、Windows Server AD に対する読み取りと書き込みを行うために作成されます。 このアカウントは、簡単設定インストール中に作成される際に、次のアクセス許可を付与されます。

| 権限 | 使用目的 |
| --- | --- |
| - ディレクトリの変更のレプリケート- ディレクトリの変更をすべてにレプリケート | パスワード ハッシュの同期 |
| すべてのプロパティの読み取り/書き込み (ユーザー) | インポートおよび Exchange ハイブリッド |
| すべてのプロパティの読み取り/書き込み (iNetOrgPerson) | インポートおよび Exchange ハイブリッド |
| すべてのプロパティの読み取り/書き込み (グループ) | インポートおよび Exchange ハイブリッド |
| すべてのプロパティの読み取り/書き込み (連絡先) | インポートおよび Exchange ハイブリッド |
| [パスワードのリセット] | パスワード ライトバックを有効にするための準備 |

#### 簡単設定ウィザード

簡単設定のインストールでは、ウィザードによっていくつかのアカウントと設定が自動的に作成されます。

[Image: Microsoft Entra Connect Sync の [高速設定] ページを示すスクリーンショット。]

次の表は、簡単設定ウィザードのページ、収集される資格情報、その使用目的をまとめたものです。

| ウィザードのページ | 収集される資格情報 | 必要なアクセス許可 | 目的 |
| --- | --- | --- | --- |
| 該当なし | インストール ウィザードを実行しているユーザー。 | ローカル サーバーの管理者。 | 同期サービスの実行に使用する ADSync サービス アカウントを作成するために使用されます。 |
| Microsoft Entra ID に接続する | Microsoft Entra ディレクトリ資格情報。 | Microsoft Entra ID のハイブリッド ID 管理者ロール。 | - Microsoft Entra ディレクトリでの同期を有効にするために使用されます。 - Microsoft Entra ID での継続的な同期操作に使用される Microsoft Entra コネクタ アカウントを作成するために使用されます。 |
| AD DS に接続 | Windows Server AD の資格情報。 | Windows Server AD の Enterprise Admins グループのメンバー。 | Windows Server AD での AD DS コネクタ アカウントの作成およびそれに対するアクセス許可の付与に使用されます。 この作成されたアカウントは、同期中にディレクトリ情報の読み取りと書き込みを行うために使用されます。 |

### カスタム設定

カスタム設定インストールでは、ウィザードの選択肢とオプションが多くなります。

[Image: Microsoft Entra Connect の [簡単設定] ページを示すスクリーンショット。[カスタマイズ] ボタンが強調表示されています。]

#### カスタム設定ウィザード

次の表は、カスタム設定ウィザードのページ、収集される資格情報、その使用目的をまとめたものです。

| ウィザードのページ | 収集される資格情報 | 必要なアクセス許可 | 目的 |
| --- | --- | --- | --- |
| 該当なし | インストール ウィザードを実行しているユーザー。 | - ローカル サーバーの管理者。- 完全な SQL Server のインスタンスを使用する場合、ユーザーは SQL Server のシステム管理者 (sysadmin) である必要があります。 | 既定では、同期エンジン サービス アカウントとして使用するローカル アカウントの作成に使用されます。 このアカウントは、管理者がアカウントを指定しない場合のみに作成されます。 |
| 同期サービスのインストール、サービス アカウントのオプション | Windows Server AD またはローカル ユーザー アカウントの資格情報。 | ユーザーとアクセス許可は、インストール ウィザードにより付与されます。 | 管理者がアカウントを指定している場合は、このアカウントは、同期サービスのサービス アカウントとして使用します。 |
| Microsoft Entra ID に接続する | Microsoft Entra ディレクトリ資格情報。 | Microsoft Entra ID のハイブリッド ID 管理者ロール。 | - Microsoft Entra ディレクトリでの同期を有効にするために使用されます。- Microsoft Entra ID での継続的な同期操作に使用される Microsoft Entra コネクタ アカウントを作成するために使用されます。 |
| ディレクトリの接続 | Microsoft Entra ID に接続されている各フォレストの Windows Server AD 資格情報。 | アクセス許可は、有効にする機能によって異なり、「AD DS コネクタ アカウントの作成」に記載されています。 | このアカウントは、同期中にディレクトリ情報の読み取りと書き込みを行うために使用されます。 |
| AD FS サーバー | ウィザードを実行しているユーザーのサインイン資格情報が接続には不十分である場合に、一覧の各サーバーに対して、ウィザードで資格情報が収集されます。 | ドメイン管理者アカウント。 | Active Directory フェデレーション サービス (AD FS) サーバーロールのインストールと構成中に使用されます。 |
| Web アプリケーション プロキシ サーバー | ウィザードを実行しているユーザーのサインイン資格情報が接続には不十分である場合に、一覧の各サーバーに対して、ウィザードで資格情報が収集されます。 | ターゲット マシンのローカル管理者。 | Web アプリケーション プロキシ (WAP) サーバーロールのインストールと構成中に使用されます。 |
| プロキシ信頼資格情報 | フェデレーション サービスの信頼資格情報 (フェデレーション サービス (FS) から信頼証明書を登録するためにプロキシで使用される資格情報)。 | AD FS サーバーのローカル管理者であるドメイン アカウント。 | FS-WAP 信頼証明書の初回登録。 |
| [AD FS サービス アカウント] ページ **[ドメイン ユーザー アカウントを使用します] オプション** | Windows Server AD ユーザー アカウントの資格情報。 | ドメイン ユーザー。 | 資格情報が提供されている Microsoft Entra ユーザー アカウントは、AD FS サービスのサインイン アカウントとして使用されます。 |

#### AD DS コネクタ アカウントの作成

重要

*ADSyncConfig.psm1* という名前の新しい PowerShell モジュールが、ビルド 1.1.880.0 (2018 年 8 月にリリース) で導入されました。 このモジュールには、Microsoft Entra Domain Services Connector アカウントに対して適切な Windows Server AD アクセス許可を構成するのに役立つコマンドレットのコレクションが含まれています。

詳細については、「[Microsoft Entra Connect: AD DS コネクタ アカウントのアクセス許可の構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-configure-ad-ds-connector-account)」を参照してください。

**[Connect your directories](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/ディレクトリの接続)** ページで指定するアカウントは、インストール前に通常のユーザー オブジェクト (VSA、MSA、または gMSA はサポートされていません) として Windows Server AD に作成する必要があります。 Microsoft Entra Connect バージョン 1.1.524.0 以降には、Microsoft Entra Connect ウィザードで、Windows Server AD への接続に使用される AD DS コネクタ アカウントを作成できるオプションがあります。

指定するアカウントにも、必要なアクセス許可が付与されていなければなりません。 インストール ウィザードでアクセス許可は検証されないため、何らかの問題が検出されるのは、同期プロセス中のみです。

必要なアクセス許可は、有効にしたオプションの機能によって異なります。 複数のドメインがある場合は、フォレスト内のすべてのドメインにアクセス許可を付与する必要があります。 これらのいずれの機能も有効にしない場合、既定のドメイン ユーザー アクセス許可で十分です。

| 機能 | アクセス許可 |
| --- | --- |
| ms-DS-ConsistencyGuid 機能 | `ms-DS-ConsistencyGuid`に記載されている  属性への書き込みアクセス許可。 |
| パスワード ハッシュの同期 | - ディレクトリの変更のレプリケート- ディレクトリの変更をすべてにレプリケート |
| Exchange ハイブリッドのデプロイメント | ユーザー、グループ、連絡先用の「[Exchange ハイブリッドの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized#exchange-hybrid-writeback)」に記載された属性への書き込みアクセス許可。 |
| Exchange メールのパブリック フォルダー | パブリック フォルダーに関して、「[Exchange メールのパブリック フォルダー](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized#exchange-mail-public-folder)」に記載された属性への読み取りアクセス許可。 |
| パスワードの書き戻し | ユーザー向けの「[パスワード管理の概要](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)」に記載された属性への書き込みアクセス許可。 |
| デバイスの書き戻し | [デバイス ライトバック](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-device-writeback)に関する記事で説明されているように、PowerShell スクリプトを使用して付与されたアクセス許可。 |
| グループの書き戻し | Exchange がインストールされているフォレストに "Microsoft 365 グループ" を書き戻すことを許可します。 |

### アップグレードに必要なアクセス許可

Microsoft Entra Connect のいずれかのバージョンから新しいリリースにアップグレードする場合、次のアクセス許可が必要です。

| プリンシパル | 必要なアクセス許可 | 目的 |
| --- | --- | --- |
| インストール ウィザードを実行しているユーザー | ローカル サーバーの管理者 | バイナリを更新するために使用されます。 |
| インストール ウィザードを実行しているユーザー | ADSyncAdmins のメンバー | 同期規則などの構成を変更するために使用されます。 |
| インストール ウィザードを実行しているユーザー | SQL Server の完全なインスタンスを使用する場合: 同期エンジン データベースの DBO (または類似のもの) | 新しい列でテーブルを更新するなど、データベース レベルの変更を行うために使用されます。 |

重要

ビルド 1.1.484 では、Microsoft Entra Connect に回帰バグがありました。 このバグにより、SQL Server データベースをアップグレードするために sysadmin アクセス許可が必要です。 このバグは、ビルド 1.1.647 で修正されています。 このビルドにアップグレードするには、sysadmin アクセス許可が必要です。 このシナリオでは、DBO のアクセス許可は十分ではありません。 sysadmin アクセス許可なしで Microsoft Entra Connect をアップグレードしようとすると、アップグレードは失敗し、Microsoft Entra Connect が正しく機能しなくなります。

### 作成されたアカウントの詳細

以降のセクションでは、Microsoft Entra Connect で作成されたアカウントの詳細について説明します。

#### AD DS コネクタ アカウント

簡単設定を使用すると、同期に使用されるアカウントが Windows Server AD に作成されます。 作成されたアカウントは、Users コンテナーのフォレスト ルート ドメインに配置されます。 アカウント名には *MSOL\_* というプレフィックスが付いています。 アカウントは、有効期限のない長く複雑なパスワードと共に作成されます。 ドメインにパスワード ポリシーがある場合は、このアカウントに対して長い複雑なパスワードが許可されることを確認してください。

[Image: Microsoft Entra Connect 内の MSOL プレフィックス付き AD DS コネクタ アカウントを示すスクリーンショット。]

カスタム設定を使用する場合は、インストールを開始する前に、ご自身でアカウントを作成する必要があります。 「AD DS コネクタ アカウントの作成」を参照してください。

#### ADSync サービス アカウント

同期サービスは、複数のアカウントで実行できます。 これは、"仮想サービス アカウント" (VSA)、"グループ管理サービス アカウント" (gMSA)、"スタンドアロンの管理サービス" (sMSA)、または通常のユーザー アカウントで実行できます。 サポートされるオプションは、新規インストールを実行した場合、Microsoft Entra Connect の 2017 年 4 月のリリースで変更されています。 Microsoft Entra Connect の以前のリリースからアップグレードする場合は、このような他のオプションは利用できません。

| アカウントの種類 | インストール オプション | 説明 |
| --- | --- | --- |
| VSA | 高速およびカスタム、2017 年 4 月以降 | このオプションは、ドメイン コントローラー上のインストールを除くすべての簡単設定インストールに使用されます。 カスタム設定の場合は、既定オプションです。 |
| gMSA | カスタム、2017 年 4 月以降 | SQL Server のリモート インスタンスを使用する場合は、gMSA を使用することをお勧めします。 |
| ユーザー アカウント | 高速およびカスタム、2017 年 4 月以降 | Microsoft Entra Connect が Windows Server 2008 にインストールされる場合とドメイン コントローラーにインストールされる場合にのみ、*AAD\_* というプレフィックスが付いたユーザー アカウントがインストール時に作成されます。 |
| ユーザー アカウント | 高速およびカスタム、2017 年 3 月以前 | *AAD\_* というプレフィックスが付いたローカル アカウントがインストール時に作成されます。 カスタム インストールでは、別のアカウントを指定できます。 |

2017 年 3 月以前のビルドで Microsoft Entra Connect を使用する場合は、サービス アカウントのパスワードをリセットしないでください。 セキュリティ上の理由から、Windows によって暗号化キーが破棄されます。 アカウントを他のアカウントに変更するには、Microsoft Entra Connect を再インストールする必要があります。 2017 年 4 月以降のビルドにアップグレードする場合、サービス アカウントのパスワードを変更できますが、使用されるアカウントを変更することはできません。

重要

サービス アカウントを設定できるのは、初回のインストール時のみです。 インストールが完了した後にサービス アカウントを変更することはできません。

次の表に、同期サービス アカウントの既定、推奨、サポート対象の各オプションを示します。

凡例:

- **太字** = 既定のオプション。ほとんどの場合、お勧めのオプションです。
- *斜体* = 既定のオプションでない場合のお勧めのオプション。
- 2008 = Windows Server 2008 にインストールした場合の既定のオプション
- 太字以外 = サポートされているオプション
- ローカル アカウント = サーバー上のローカル ユーザー アカウント
- ドメイン アカウント = ドメイン ユーザー アカウント
- sMSA = [スタンドアロンの管理サービス アカウント](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-on-premises)
- gMSA = [グループ管理サービス アカウント](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/group-managed-service-accounts-overview)

| - | ローカル データベース簡易 | ローカル データベース/ローカル SQL Server習慣 | リモート SQL Server習慣 |
| --- | --- | --- | --- |
| **ドメインに参加しているコンピューター** | **VSA**ローカル アカウント (2008) | **VSA**ローカル アカウント (2008)ローカル アカウントドメイン アカウントsMSA、gMSA | **gMSA**ドメイン アカウント |
| **ドメイン コントローラー** | **ドメイン アカウント** | *gMSA***ドメイン アカウント**sMSA | *gMSA***ドメイン アカウント** |

##### VSA

VSA は、パスワードのない特殊な種類のアカウントであり、Windows によって管理されます。

[Image: 仮想サービス アカウントを示すスクリーンショット。]

VSA は、同期エンジンと SQL Server が同じサーバー上にあるシナリオで使用するためのものです。 リモート SQL Server を使用する場合は、VSA ではなく gMSA を使用することをお勧めします。

VSA 機能を使用するには、Windows Server 2008 R2 以降が必要です。 Windows Server 2008 に Microsoft Entra Connect をインストールすると、インストールは VSA の代わりにユーザー アカウントを使用するようにフォールバックします。

##### gMSA

SQL Server のリモート インスタンスを使用する場合は、gMSA を使用することをお勧めします。 gMSA 用に Windows Server AD を準備する方法の詳細については、「[グループの管理されたサービス アカウントの概要](https://learn.microsoft.com/ja-jp/windows-server/security/group-managed-service-accounts/group-managed-service-accounts-overview)」を参照してください。

このオプションを使用するには、[\[必須コンポーネントのインストール\]](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#install-required-components) ページで **[既存のサービス アカウントを使用する]** を選んでから、**[マネージド サービス アカウント]** を選びます。

[Image: Windows Server の [マネージド サービス アカウント] の選択を示すスクリーンショット。]

このシナリオでは、[sMSA](https://learn.microsoft.com/ja-jp/entra/architecture/service-accounts-on-premises) を使用することもできます。 ただし、sMSA はローカル コンピューターでのみ使用でき、既定の VSA の代わりに sMSA を使用してもメリットはありません。

sMSA 機能を使用するには、Windows Server 2012 以降が必要です。 以前のバージョンのオペレーティング システムを使用する必要があり、リモート SQL Server を使用する場合は、ユーザー アカウントを使用する必要があります。

##### ユーザー アカウント

ローカル サービス アカウントはインストール ウィザードで作成されます (カスタム設定で使用するアカウントを指定した場合を除く)。 このアカウントは *AAD\_* というプレフィックスが付き、実際の同期サービスの実行に使用されます。 Microsoft Entra Connect をドメイン コントローラーにインストールした場合、アカウントはドメインに作成されます。 次の場合、*AAD\_* サービス アカウントがドメインに存在する必要があります。

- SQL Server を実行しているリモート サーバーを使用する。
- 認証が必要なプロキシを使用する。

[Image: Windows Server 内の同期サービス ユーザー アカウントを示すスクリーンショット。]

*AAD\_* サービス アカウントは、有効期限のない長く複雑なパスワードと共に作成されます。

このアカウントは、その他のアカウントのパスワードを安全に保存するために使用されます。 それらのパスワードはデータベース内で暗号化されて保存されます。 暗号化キーの秘密キーは、Windows データ保護 API (DPAPI) を使用して、暗号化サービスの秘密キー暗号化で保護されます。

SQL Server の完全なインスタンスを使用する場合、サービス アカウントは、同期エンジン用に作成されたデータベースの DBO です。 このサービスは、他のアクセス許可では意図したように機能しません。 SQL Server ログインも作成されます。

アカウントには、ファイル、レジストリ キー、同期エンジンに関連するその他のオブジェクトへの各アクセス許可も付与されます。

#### Microsoft Entra コネクタ アカウント

Microsoft Entra ID のアカウントが、同期サービスで使用するために作成されます。 このアカウントは、その表示名で識別できます。

[Image: DC1 プレフィックスが付いた Microsoft Entra アカウントを示すスクリーンショット。]

アカウントが使用されるサーバーの名前は、ユーザー名の 2 番目の部分で識別できます。 上の図では、サーバー名は DC1 です。 ステージング サーバーがある場合、各サーバーに独自のアカウントが指定されます。

サーバー アカウントは、有効期限のない長く複雑なパスワードと共に作成されます。 このアカウントには、特別な[ディレクトリ同期アカウント ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-synchronization-accounts)が付与されます。これは、ディレクトリ同期タスクのみを実行するアクセス許可を持つものです。 この特別な組み込みロールは、Microsoft Entra Connect ウィザードの外部では付与できません。 [Microsoft Entra 管理センター](https://entra.microsoft.com)には、このアカウントがユーザー ロールと共に表示されます。

Microsoft Entra ID での同期サービスのアカウント数の上限は 20 個です。

- Microsoft Entra インスタンスで既存の Microsoft Entra サービス アカウントのリストを取得するには、次のコマンドを実行します。

    ```powershell
    $directoryRoleId = Get-MgDirectoryRole | where {$_.DisplayName -eq "Directory Synchronization Accounts"}
    Get-MgDirectoryRoleMember -DirectoryRoleId $directoryRoleId.Id | Select-Object Id,@{Name="UserPrincipalName"; Expression={$_.AdditionalProperties.userPrincipalName}}
    
    ```
- 使用されていない Microsoft Entra サービス アカウントを削除するには、次のコマンドを実行します。

    ```powershell
    Remove-MgUser -UserId <Id-of-the-account-to-remove>
    ```

注

これらの PowerShell コマンドを使用する前に、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview#install-the-microsoft-graph-powershell-sdk) モジュールをインストールし、[Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) を使用して Microsoft Entra ID のインスタンスに接続する必要があります。

Microsoft Entra Connect アカウントの管理やパスワードのリセットを行う方法の詳細については、[Microsoft Entra Connect アカウントの管理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-azureadaccount)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-adconnectivitytools"} -->
## Microsoft Entra Connect: ADConnectivityTools PowerShell リファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-adconnectivitytools
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このドキュメントでは、ADConnectivityTools.psm1 PowerShell モジュールのリファレンス情報を提供します。

次のドキュメントでは、`ADConnectivityTools`の Microsoft Entra Connect に含まれる `C:\Program Files\Microsoft Azure Active Directory Connect\Tools\ADConnectivityTool.psm1` PowerShell モジュールのリファレンス情報を提供します。

### Confirm-DnsConnectivity

#### 概要

ローカル Dns の問題を検出します。

#### 構文

```
Confirm-DnsConnectivity [-Forest] <String> [-DCs] <Array> [-ReturnResultAsPSObject] [<CommonParameters>]
```

#### 形容

ローカル Dns 接続テストを実行します。 Active Directory コネクタを構成するには、Microsoft Entra Connect サーバーに、接続しようとしているフォレストの名前解決と、このフォレストに関連付けられているドメイン コントローラーの両方が必要です。

#### 例

##### 例 1

```powershell
Confirm-DnsConnectivity -Forest "TEST.CONTOSO.COM" -DCs "MYDC1.CONTOSO.COM","MYDC2.CONTOSO.COM"
```

##### 例 2

```powershell
Confirm-DnsConnectivity -Forest "TEST.CONTOSO.COM"
```

#### パラメーター

##### -森

テスト対象のフォレストの名前を指定します。

```yml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -DC

テスト対象の DC を指定します。

```yml
Type: Array
Parameter Sets: (All)
Aliases:

Required: True
Position: 2
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ReturnResultAsPSObject

この診断の結果を PSObject の形式で返します。 このツールを手動で操作する場合は必要ありません。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Confirm-ForestExists

#### 概要

指定したフォレストが存在するかどうかを判断します。

#### 構文

```
Confirm-ForestExists [-Forest] <String> [<CommonParameters>]
```

#### 形容

フォレストに関連付けられている IP アドレスを DNS サーバーに照会します。

#### 例

##### 例 1

```powershell
Confirm-TargetsAreReachable -Forest "TEST.CONTOSO.COM"
```

#### パラメーター

##### -森

テスト対象のフォレストの名前を指定します。

```yml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Confirm-FunctionalLevel

#### 概要

AD フォレストの機能レベルを確認します。

#### 構文

##### SamAccount

```
Confirm-FunctionalLevel -Forest <String> [-RunWithCurrentlyLoggedInUserCredentials] [<CommonParameters>]
```

##### ForestFQDN

```
Confirm-FunctionalLevel -ForestFQDN <Forest> [-RunWithCurrentlyLoggedInUserCredentials] [<CommonParameters>]
```

#### 形容

AD フォレストの機能レベルが特定の MinAdForestVersion (WindowsServer2003) 以上であることを確認します。 アカウント (ドメイン\ユーザー名) とパスワードが要求される場合があります。

#### 例

##### 例 1

```powershell
Confirm-FunctionalLevel -Forest "test.contoso.com"
```

##### 例 2

```powershell
Confirm-FunctionalLevel -Forest "test.contoso.com" -RunWithCurrentlyLoggedInUserCredentials -Verbose
```

##### 例 3

```powershell
Confirm-FunctionalLevel -ForestFQDN $ForestFQDN -RunWithCurrentlyLoggedInUserCredentials -Verbose
```

#### パラメーター

##### -森

ターゲット フォレスト。 既定値は、現在ログインしているユーザーのフォレストです。

```yml
Type: String
Parameter Sets: SamAccount
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ForestFQDN

ターゲット ForestFQDN オブジェクト。

```yml
Type: Forest
Parameter Sets: ForestFQDN
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunWithCurrentlyLoggedInUserCredentials

この関数は、ユーザーにカスタム資格情報を要求するのではなく、コンピューターに現在ログインしているユーザーの資格情報を使用します。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Confirm-NetworkConnectivity

#### 概要

ローカル ネットワーク接続の問題を検出します。

#### 構文

```
Confirm-NetworkConnectivity [-DCs] <Array> [-SkipDnsPort] [-ReturnResultAsPSObject] [<CommonParameters>]
```

#### 形容

ローカル ネットワーク接続テストを実行します。

ローカル ネットワーク テストの場合、Microsoft Entra Connect は、ポート 53 (DNS)、88 (Kerberos)、および 389 (LDAP) の名前付きドメイン コントローラーと通信できる必要があります。ほとんどの組織は DC で DNS を実行するため、このテストは現在統合されています。 別の DNS サーバーが指定されている場合は、ポート 53 をスキップする必要があります。

#### 例

##### 例 1

```powershell
Confirm-NetworkConnectivity -SkipDnsPort -DCs "MYDC1.CONTOSO.COM","MYDC2.CONTOSO.COM"
```

##### 例 2

```powershell
Confirm-NetworkConnectivity -DCs "MYDC1.CONTOSO.COM","MYDC2.CONTOSO.COM" -Verbose
```

#### パラメーター

##### -DC

テスト対象の DC を指定します。

```yml
Type: Array
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SkipDnsPort

ユーザーが AD サイト/ログオン DC によって提供される DNS サービスを使用していない場合は、ポート 53 のチェックをスキップできます。 ユーザーは引き続き\_.ldap.\_tcp解決できる必要があります。active Directory コネクタの構成を成功させるために、forestfqdn&lt; を&gt;します。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ReturnResultAsPSObject

この診断の結果を PSObject の形式で返します。 このツールを手動で操作する場合は必要ありません。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Confirm-TargetsAreReachable

#### 概要

指定したフォレストとそれに関連付けられているドメイン コントローラーに到達できるかどうかを判断します。

#### 構文

```
Confirm-TargetsAreReachable [-Forest] <String> [-DCs] <Array> [<CommonParameters>]
```

#### 形容

"ping" テストを実行します (コンピューターがネットワークやインターネットを介してターゲット コンピューターに到達できるかどうか)

#### 例

##### 例 1

```powershell
Confirm-TargetsAreReachable -Forest "TEST.CONTOSO.COM" -DCs "MYDC1.CONTOSO.COM","MYDC2.CONTOSO.COM"
```

##### 例 2

```powershell
Confirm-TargetsAreReachable -Forest "TEST.CONTOSO.COM"
```

#### パラメーター

##### -森

テスト対象のフォレストの名前を指定します。

```yml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -DC

テスト対象の DC を指定します。

```yml
Type: Array
Parameter Sets: (All)
Aliases:

Required: True
Position: 2
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Confirm-ValidDomains

#### 概要

取得したフォレスト FQDN 内のドメインに到達可能であることを検証する

#### 構文

##### SamAccount

```
Confirm-ValidDomains [-Forest <String>] [-RunWithCurrentlyLoggedInUserCredentials] [<CommonParameters>]
```

##### ForestFQDN

```
Confirm-ValidDomains -ForestFQDN <Forest> [-RunWithCurrentlyLoggedInUserCredentials] [<CommonParameters>]
```

#### 形容

DomainGuid と DomainDN の取得を試みることで、取得したフォレスト FQDN 内のすべてのドメインに到達可能であることを検証します。 アカウント (ドメイン\ユーザー名) とパスワードが要求される場合があります。

#### 例

##### 例 1

```powershell
Confirm-ValidDomains -Forest "test.contoso.com" -Verbose
```

##### 例 2

```powershell
Confirm-ValidDomains -Forest "test.contoso.com" -RunWithCurrentlyLoggedInUserCredentials -Verbose
```

##### 例 3

```powershell
Confirm-ValidDomains -ForestFQDN $ForestFQDN -RunWithCurrentlyLoggedInUserCredentials -Verbose
```

#### パラメーター

##### -森

ターゲット フォレスト。

```yml
Type: String
Parameter Sets: SamAccount
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ForestFQDN

ターゲット ForestFQDN オブジェクト。

```yml
Type: Forest
Parameter Sets: ForestFQDN
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunWithCurrentlyLoggedInUserCredentials

この関数は、ユーザーにカスタム資格情報を要求するのではなく、コンピューターに現在ログインしているユーザーの資格情報を使用します。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Confirm-ValidEnterpriseAdminCredentials

#### 概要

ユーザーがエンタープライズ管理者の資格情報を持っているかどうかを確認します。

#### 構文

```
Confirm-ValidEnterpriseAdminCredentials [-RunWithCurrentlyLoggedInUserCredentials] [<CommonParameters>]
```

#### 形容

指定されたユーザーがエンタープライズ管理者の資格情報を持っているかどうかを検索します。 アカウント (ドメイン\ユーザー名) とパスワードが要求される場合があります。

#### 例

##### 例 1

```powershell
Confirm-ValidEnterpriseAdminCredentials -DomainName test.contoso.com -Verbose
```

##### 例 2

```powershell
Confirm-ValidEnterpriseAdminCredentials -RunWithCurrentlyLoggedInUserCredentials -Verbose
```

#### パラメーター

##### -RunWithCurrentlyLoggedInUserCredentials

この関数は、ユーザーにカスタム資格情報を要求するのではなく、コンピューターに現在ログインしているユーザーの資格情報を使用します。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Get-DomainFQDNData

#### 概要

アカウントとパスワードの組み合わせから DomainFQDN を取得します。

#### 構文

```
Get-DomainFQDNData [[-DomainFQDNDataType] <String>] [-RunWithCurrentlyLoggedInUserCredentials]
 [-ReturnExceptionOnError] [<CommonParameters>]
```

#### 形容

指定された資格情報から domainFQDN オブジェクトを取得しようとします。 domainFQDN が有効な場合は、ユーザーの選択に応じて DomainFQDNName または RootDomainName が返されます。 アカウント (ドメイン\ユーザー名) とパスワードが要求される場合があります。

#### 例

##### 例 1

```powershell
Get-DomainFQDNData -DomainFQDNDataType DomainFQDNName -Verbose
```

##### 例 2

```powershell
Get-DomainFQDNData -DomainFQDNDataType RootDomainName -RunWithCurrentlyLoggedInUserCredentials
```

#### パラメーター

##### -DomainFQDNDataType

取得される目的の種類のデータ。 現在、"DomainFQDNName" または "RootDomainName" に制限されています。

```yml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunWithCurrentlyLoggedInUserCredentials

この関数は、ユーザーにカスタム資格情報を要求するのではなく、コンピューターに現在ログインしているユーザーの資格情報を使用します。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ReturnExceptionOnError

Start-NetworkConnectivityDiagnosisTools 関数で使用される補助パラメーター

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Get-ForestFQDN

#### 概要

アカウントとパスワードの組み合わせから ForestFQDN を取得します。

#### 構文

```
Get-ForestFQDN [-Forest] <String> [-RunWithCurrentlyLoggedInUserCredentials] [<CommonParameters>]
```

#### 形容

指定された資格情報から ForestFQDN を取得しようとします。 アカウント (ドメイン\ユーザー名) とパスワードが要求される場合があります。

#### 例

##### 例 1

```powershell
Get-ForestFQDN -Forest CONTOSO.MICROSOFT.COM -Verbose
```

##### 例 2

```powershell
Get-ForestFQDN -Forest CONTOSO.MICROSOFT.COM -RunWithCurrentlyLoggedInUserCredentials -Verbose
```

#### パラメーター

##### -森

ターゲット フォレスト。既定値は、現在ログインしているユーザーのドメインです。

```yml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunWithCurrentlyLoggedInUserCredentials

この関数は、ユーザーにカスタム資格情報を要求するのではなく、コンピューターに現在ログインしているユーザーの資格情報を使用します。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Start-ConnectivityValidation

#### 概要

Main 関数。

#### 構文

```
Start-ConnectivityValidation [-Forest] <String> [-AutoCreateConnectorAccount] <Boolean> [[-UserName] <String>]
 [<CommonParameters>]
```

#### 形容

AD 資格情報が有効であることを確認する使用可能なすべてのメカニズムを実行します。

#### 例

##### 例 1

```powershell
Start-ConnectivityValidation -Forest "test.contoso.com" -AutoCreateConnectorAccount $True -Verbose
```

#### パラメーター

##### -森

ターゲット フォレスト。

```yml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -AutoCreateConnectorAccount

カスタム インストールの場合: ユーザーが Microsoft Entra Connect のウィザードの [AD フォレスト アカウント] ウィンドウで [新しい AD アカウントの作成] を選択した場合に$Trueされるフラグ。 ユーザーが [既存の AD アカウントを使用する] を選択した場合に$Falseします。 高速インストールの場合: この変数の値は、Express-installations に$Trueする必要があります。

```yml
Type: Boolean
Parameter Sets: (All)
Aliases:

Required: True
Position: 2
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -UserName

ユーザーの資格情報が要求されたときに Username フィールドを事前に設定するパラメーター。

```yml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: 3
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Start-NetworkConnectivityDiagnosisTools

#### 概要

ネットワーク接続テストのメイン関数。

#### 構文

```
Start-NetworkConnectivityDiagnosisTools [[-Forest] <String>] [-Credentials] <PSCredential>
 [[-LogFileLocation] <String>] [[-DCs] <Array>] [-DisplayInformativeMessage] [-ReturnResultAsPSObject]
 [-ValidCredentials] [<CommonParameters>]
```

#### 形容

ローカル ネットワーク接続テストを実行します。

#### 例

##### 例 1

```powershell
Start-NetworkConnectivityDiagnosisTools -Forest "TEST.CONTOSO.COM"
```

##### 例 2

```powershell
Start-NetworkConnectivityDiagnosisTools -Forest "TEST.CONTOSO.COM" -DCs "DC1.TEST.CONTOSO.COM", "DC2.TEST.CONTOSO.COM"
```

#### パラメーター

##### -森

テスト対象のフォレスト名を指定します。

```yml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -資格 情報

テストを実行しているユーザーのユーザー名とパスワード。 Microsoft Entra Connect ウィザードを実行するために必要なのと同じレベルのアクセス許可が必要です。

```yml
Type: PSCredential
Parameter Sets: (All)
Aliases:

Required: True
Position: 2
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -LogFileLocation

この関数の出力を格納するログ ファイルの場所を指定します。

```yml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: 3
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -DC

テスト対象の DC を指定します。

```yml
Type: Array
Parameter Sets: (All)
Aliases:

Required: False
Position: 4
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -DisplayInformativeMessage

この関数の目的に関するメッセージを表示できるようにするフラグ。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ReturnResultAsPSObject

この診断の結果を PSObject の形式で返します。 このツールを手動で操作する際に指定する必要はありません。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ValidCredentials

ユーザーが入力した資格情報が有効かどうかを示します。 このツールを手動で操作する際に指定する必要はありません。

```yml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、共通パラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable をサポートしています。 詳細については、「about\_CommonParameters (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-adsync"} -->
## Microsoft Entra Connect: ADSync PowerShell リファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-adsync
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このドキュメントでは、ADSync.psm1 PowerShell モジュールのリファレンス情報を提供します。

次のドキュメントでは、Microsoft Entra Connect に含まれている `ADSync` PowerShell モジュールのリファレンス情報を提供します。

### Add-ADSyncAADServiceAccount

#### 概要

新しい Microsoft Entra 同期サービス アカウントを追加し、Entra ID コネクタの資格情報を更新するか、現在のアカウントを更新します。

#### 構文

```powershell
Add-ADSyncAADServiceAccount [-AADCredential] <PSCredential> [[-Name] <String>] [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### Description

新しい Microsoft Entra 同期サービス アカウントを追加し、Entra ID コネクタの資格情報を更新するか、現在のアカウントを更新します。 パラメーター `-Name` 指定すると、新しい同期サービス アカウントが作成され、Entra ID コネクタのユーザー名とパスワードが更新されます。 `-Name`パラメーターがないと、現在の同期サービス アカウントのパスワードがリセットされ、Entra ID コネクタのユーザー名とパスワードが更新されます。 たとえば、 `-Name Sync_CONNECT01`を使用して、 `Sync_CONNECT01@Contoso.onmicrosoft.com`と呼ばれる Microsoft Entra 同期サービス アカウントを追加または更新します。 現在の Microsoft Entra 同期サービス アカウントの内容を確認するには、次を使用します。

`(Get-ADsyncConnector -Identifier 'b891884f-051e-4a83-95af-2544101c9083').ConnectivityParameters['UserName'].Value`

#### 例

##### 例 1

```powershell
# Get the Microsoft Entra credential
PS C:\> $credEntra = Get-Credential
# Add or update the synchronization service account
PS C:\> Add-ADSyncAADServiceAccount -AADCredential $credEntra
```

##### 例 2

```powershell
# Get the current synchronization service account
PS C:\> (Get-ADsyncConnector -Identifier 'b891884f-051e-4a83-95af-2544101c9083').ConnectivityParameters['UserName'].Value
# Get the Microsoft Entra credential
PS C:\> $credEntra = Get-Credential
# Add or updatethe synchronization service account
PS C:\> Add-ADSyncAADServiceAccount -AADCredential $credEntra -Name Sync_CONNECT01
```

#### パラメーター

##### -AADCredential

Microsoft Entra 資格情報。

```yaml
Type: PSCredential
Aliases: None

Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -名前

サービス アカウント名 (ドメイン サフィックスなし)。

```yaml
Type: String
Aliases: None

Required: False
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -確認

コマンドレットを実行する前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.Management.Automation.PSCredential

##### System.String

##### System.Management.Automation.SwitchParameter

#### 出力

##### System.Object

### Add-ADSyncADDSConnectorAccount

#### 概要

このコマンドレットは、サービス アカウントのパスワードをリセットし、Microsoft Entra ID と同期エンジンの両方で更新します。

#### 構文

##### byIdentifier

```powershell
   Add-ADSyncADDSConnectorAccount [-Identifier] <Guid> [-EACredential <PSCredential>] [<CommonParameters>]
```

##### byName

```powershell
    Add-ADSyncADDSConnectorAccount [-Name] <String> [-EACredential <PSCredential>] [<CommonParameters>]
```

#### Description

このコマンドレットは、サービス アカウントのパスワードをリセットし、Microsoft Entra ID と同期エンジンの両方で更新します。

#### 例

##### 例 1

```powershell
  PS C:\> Add-ADSyncADDSConnectorAccount -Name contoso.com -EACredential $EAcredentials
```

contoso.com に接続されているサービス アカウントのパスワードをリセットします。

#### パラメーター

##### -EACredential

Active Directory のエンタープライズ管理者アカウントの資格情報。

```yaml
  Type: PSCredential
  Parameter Sets: (All)
  Aliases:

  Required: False
  Position: Named
  Default value: None
  Accept pipeline input: False
  Accept wildcard characters: False
```

##### -識別子

サービス アカウントのパスワードをリセットする必要があるコネクタの識別子。

```yaml
  Type: Guid
  Parameter Sets: byIdentifier
  Aliases:

  Required: True
  Position: 0
  Default value: None
  Accept pipeline input: True (ByValue)
  Accept wildcard characters: False
```

##### -名前

コネクタの名前。

```yaml
  Type: String
  Parameter Sets: byName
  Aliases:

  Required: True
  Position: 1
  Default value: None
  Accept pipeline input: True (ByValue)
  Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.Guid

##### System.String

#### 出力

##### System.Object

### Disable-ADSyncExportDeletionThreshold

#### 概要

エクスポート ステージで削除しきい値の機能を無効にします。

#### 構文

```powershell
   Disable-ADSyncExportDeletionThreshold [-AADUserName] <string> [-WhatIf] [-Confirm] [<CommonParameters>]
    [<CommonParameters>]
```

#### Description

エクスポート ステージで削除しきい値の機能を無効にします。

#### 例

##### 例 1

```powershell
    PS C:\> Disable-ADSyncExportDeletionThreshold -AADUserName "<UserPrincipalName>"
```

指定された Microsoft Entra 資格情報を使用して、エクスポート削除のしきい値の機能を無効にします。

#### パラメーター

##### -AADUserName &lt;string&gt;

Microsoft Entra ID UserPrincipalName。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -確認

確認を求めるパラメーター スイッチ。

```yaml
    Type: SwitchParameter
    Parameter Sets: (All)
    Aliases: cf

    Required: False
    Position: Named
    Default value: None
    Accept pipeline input: False
    Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
    Type: SwitchParameter
    Parameter Sets: (All)
    Aliases: wi

    Required: False
    Position: Named
    Default value: None
    Accept pipeline input: False
    Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.String

#### 出力

##### System.Object

### Enable-ADSyncExportDeletionThreshold

#### 概要

エクスポート削除のしきい値機能を有効にし、しきい値の値を設定します。

#### 構文

```powershell
Enable-ADSyncExportDeletionThreshold [-DeletionThreshold] <UInt32>  [-AADUserName] <string> [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### Description

エクスポート削除のしきい値機能を有効にし、しきい値の値を設定します。

#### 例

##### 例 1

```powershell
PS C:\> Enable-ADSyncExportDeletionThreshold -DeletionThreshold 999 -AADUserName "<UserPrincipalName>"
```

エクスポート削除しきい値機能を有効にし、削除しきい値を 777 に設定します。

#### パラメーター

##### -AADUserName &lt;string&gt;

Microsoft Entra ID UserPrincipalName。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -確認

コマンドレットを実行する前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -DeletionThreshold

削除のしきい値。

```yaml
Type: UInt32
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.UInt32

##### System.String

#### 出力

##### System.Object

### Get-ADSyncAutoUpgrade

#### 概要

インストール時の AutoUpgrade の状態を取得します。

#### 構文

```powershell
Get-ADSyncAutoUpgrade [-Detail] [<CommonParameters>]
```

#### Description

インストール時の AutoUpgrade の状態を取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncAutoUpgrade -Detail
```

インストールの AutoUpgrade 状態を返し、AutoUpgrade が中断された場合の中断の理由を示します。

#### パラメーター

##### -ディテール

AutoUpgrade 状態が中断されている場合、このパラメーターを使用すると中断の理由が表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Get-ADSyncCSObject

#### 概要

指定したコネクタ スペース オブジェクトを取得します。

#### 構文

##### SearchByIdentifier

```powershell
Get-ADSyncCSObject [-Identifier] <Guid> [<CommonParameters>]
```

##### SearchByConnectorIdentifierDistinguishedName

```powershell
Get-ADSyncCSObject [-ConnectorIdentifier] <Guid> [-DistinguishedName] <String> [-SkipDNValidation] [-Transient]
[<CommonParameters>]
```

##### SearchByConnectorIdentifier

```powershell
Get-ADSyncCSObject [-ConnectorIdentifier] <Guid> [-Transient] [-StartIndex <Int32>] [-MaxResultCount <Int32>]
[<CommonParameters>]
```

##### SearchByConnectorNameDistinguishedName

```powershell
Get-ADSyncCSObject [-ConnectorName] <String> [-DistinguishedName] <String> [-SkipDNValidation] [-Transient]
[<CommonParameters>]
```

##### SearchByConnectorName

```powershell
Get-ADSyncCSObject [-ConnectorName] <String> [-Transient] [-StartIndex <Int32>] [-MaxResultCount <Int32>]
[<CommonParameters>]
```

#### Description

指定したコネクタ スペース オブジェクトを取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncCSObject -ConnectorName "contoso.com" -DistinguishedName "CN=fabrikam,CN=Users,DC=contoso,DC=com"
```

contoso.com ドメイン内のユーザー fabrikam のコネクタ スペース オブジェクトを取得します。

#### パラメーター

##### -ConnectorIdentifier

コネクタの識別子。

```yaml
Type: Guid
Parameter Sets: SearchByConnectorIdentifierDistinguishedName, SearchByConnectorIdentifier 
Aliases:

Required: True
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ConnectorName

コネクタの名前。

```yaml
Type: String
Parameter Sets: SearchByConnectorNameDistinguishedName, SearchByConnectorName
Aliases:

Required: True
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -DistinguishedName

コネクタ スペース オブジェクトの識別名。

```yaml
Type: String
Parameter Sets: SearchByConnectorIdentifierDistinguishedName, SearchByConnectorNameDistinguishedName
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -識別子

コネクタ スペース オブジェクトの識別子。

```yaml
Type: Guid
Parameter Sets: SearchByIdentifier
Aliases:

Required: True
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -MaxResultCount

結果セットの最大カウント。

```yaml
Type: Int32
Parameter Sets: SearchByConnectorIdentifier, SearchByConnectorName
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SkipDNValidation

Parameter Switch to Skip DistinguishedName validation.

```yaml
Type: SwitchParameter
Parameter Sets: SearchByConnectorIdentifierDistinguishedName, SearchByConnectorNameDistinguishedName
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -StartIndex

カウントを返す開始インデックス。

```yaml
Type: Int32
Parameter Sets: SearchByConnectorIdentifier, SearchByConnectorName
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -儚い

パラメーター スイッチを使用して、一時的なコネクタ スペース オブジェクトを取得します。

```yaml
Type: SwitchParameter
Parameter Sets: SearchByConnectorIdentifierDistinguishedName, SearchByConnectorIdentifier, SearchByConnectorNameDistinguishedName, SearchByConnectorName
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Get-ADSyncCSObjectLog

#### 概要

コネクタ スペース オブジェクトのログ エントリを取得します。

#### 構文

```powershell
Get-ADSyncCSObjectLog [-Identifier] <Guid> [-Count] <UInt32> [<CommonParameters>]
```

#### Description

コネクタ スペース オブジェクトのログ エントリを取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncCSObjectLog -Identifier "00000000-0000-0000-0000-000000000000" -Count 1
```

指定した識別子を持つ 1 つのオブジェクトを返します。

#### パラメーター

##### -数える

取得するコネクタ スペース オブジェクト のログ エントリの最大数が予想されます。

```yaml
Type: UInt32
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -識別子

コネクタ スペース オブジェクト識別子。

```yaml
Type: Guid
Parameter Sets: (All)
Aliases:

Required: True
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Get-ADSyncDatabaseConfiguration

#### 概要

ADSync データベースの構成を取得します。

#### 構文

```powershell
Get-ADSyncDatabaseConfiguration [<CommonParameters>]
```

#### Description

ADSync データベースの構成を取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncDatabaseConfiguration
```

ADSync データベースの構成を取得します。

#### パラメーター

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Get-ADSyncExportDeletionThreshold

#### 概要

Microsoft Entra ID からエクスポート削除のしきい値を取得します。

#### 構文

```powershell
Get-ADSyncExportDeletionThreshold [-AADUserName] <string> [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### Description

Microsoft Entra ID からエクスポート削除のしきい値を取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncExportDeletionThreshold -AADUserName "<UserPrincipalName>"
```

指定した Microsoft Entra 資格情報を使用して、Microsoft Entra ID からエクスポート削除のしきい値を取得します。

#### パラメーター

##### -AADUserName &lt;string&gt;

Microsoft Entra ID UserPrincipalName。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -確認

コマンドレットを実行する前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.String

#### 出力

##### System.Object

### Get-ADSyncMVObject

#### 概要

メタバース オブジェクトを取得します。

#### 構文

```powershell
Get-ADSyncMVObject -Identifier <Guid> [<CommonParameters>]
```

#### Description

メタバース オブジェクトを取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncMVObject -Identifier "00000000-0000-0000-0000-000000000000"
```

指定した識別子を持つメタバース オブジェクトを取得します。

#### パラメーター

##### -識別子

メタバース オブジェクトの識別子。

```yaml
Type: Guid
Parameter Sets: (All)
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Get-ADSyncRunProfileResult

#### 概要

クライアントからの入力を処理し、1 つ以上の実行プロファイルの結果を取得します。

#### 構文

```powershell
Get-ADSyncRunProfileResult [-RunHistoryId <Guid>] [-ConnectorId <Guid>] [-RunProfileId <Guid>]
[-RunNumber <Int32>] [-NumberRequested <Int32>] [-RunStepDetails] [-StepNumber <Int32>] [-WhatIf] [-Confirm]
[<CommonParameters>]
```

#### Description

クライアントからの入力を処理し、1 つ以上の実行プロファイルの結果を取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncRunProfileResult -ConnectorId "00000000-0000-0000-0000-000000000000"
```

指定したコネクタのすべての同期実行プロファイルの結果を取得します。

#### パラメーター

##### -確認

コマンドレットを実行する前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ConnectorId

コネクタ識別子。

```yaml
Type: Guid
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -NumberRequested

戻り値の最大数。

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunHistoryId

特定の実行の識別子。

```yaml
Type: Guid
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunNumber

特定の実行の実行番号。

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunProfileId

特定の実行の実行プロファイル識別子。

```yaml
Type: Guid
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunStepDetails

実行ステップの詳細のパラメーター スイッチ。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -StepNumber

ステップ番号でフィルター処理します。

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Get-ADSyncRunStepResult

#### 概要

AD 同期の実行ステップの結果を取得します。

#### 構文

```powershell
Get-ADSyncRunStepResult [-RunHistoryId <Guid>] [-StepHistoryId <Guid>] [-First] [-StepNumber <Int32>] [-WhatIf]
[-Confirm] [<CommonParameters>]
```

#### Description

AD 同期の実行ステップの結果を取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncRunStepResult -RunHistoryId "00000000-0000-0000-0000-000000000000"
```

指定した実行の AD 同期実行ステップの結果を取得します。

#### パラメーター

##### -確認

コマンドレットを実行する前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -まずは

最初のオブジェクトのみを取得するためのパラメーター スイッチ。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunHistoryId

特定の実行の ID。

```yaml
Type: Guid
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -StepHistoryId

特定の実行ステップの ID。

```yaml
Type: Guid
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -StepNumber

ステップ番号。

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Get-ADSyncScheduler

#### 概要

同期スケジューラの現在の同期サイクル設定を取得します。

#### 構文

```powershell
Get-ADSyncScheduler [<CommonParameters>]
```

#### Description

同期スケジューラの現在の同期サイクル設定を取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncScheduler
```

同期スケジューラの現在の同期サイクル設定を取得します。

#### パラメーター

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Get-ADSyncSchedulerConnectorOverride

#### 概要

指定した 1 つ以上のコネクタの AD 同期スケジューラのオーバーライド値を取得します。

#### 構文

```powershell
Get-ADSyncSchedulerConnectorOverride [-ConnectorIdentifier <Guid>] [-ConnectorName <String>]
[<CommonParameters>]
```

#### Description

指定した 1 つ以上のコネクタの AD 同期スケジューラのオーバーライド値を取得します。

#### 例

##### 例 1

```powershell
PS C:\> Get-ADSyncSchedulerConnectorOverride -ConnectorName "contoso.com"
```

"contoso.com" コネクタの AD 同期スケジューラのオーバーライド値を取得します。

##### 例 2

```powershell
PS C:\> Get-ADSyncSchedulerConnectorOverride
```

すべての AD 同期スケジューラのオーバーライド値を取得します。

#### パラメーター

##### -ConnectorIdentifier

コネクタ識別子。

```yaml
Type: Guid
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ConnectorName

コネクタ名。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Invoke-ADSyncCSObjectPasswordHashSync

#### 概要

指定された AD コネクタ スペース オブジェクトのパスワード ハッシュを同期します。

#### 構文

##### SearchByDistinguishedName

```powershell
Invoke-ADSyncCSObjectPasswordHashSync [-ConnectorName] <String> [-DistinguishedName] <String>
[<CommonParameters>]
```

##### SearchByIdentifier

```powershell
Invoke-ADSyncCSObjectPasswordHashSync [-Identifier] <Guid> [<CommonParameters>]
```

##### CSObject

```powershell
Invoke-ADSyncCSObjectPasswordHashSync [-CsObject] <CsObject> [<CommonParameters>]
```

#### Description

指定された AD コネクタ スペース オブジェクトのパスワード ハッシュを同期します。

#### 例

##### 例 1

```powershell
PS C:\> Invoke-ADSyncCSObjectPasswordHashSync -ConnectorName "contoso.com" -DistinguishedName "CN=fabrikam,CN=Users,DN=contoso,DN=com"
```

指定したオブジェクトのパスワード ハッシュを同期します。

#### パラメーター

##### -ConnectorName

コネクタの名前。

```yaml
Type: String
Parameter Sets: SearchByDistinguishedName
Aliases:

Required: True
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -CsObject

コネクタ スペース オブジェクト。

```yaml
Type: CsObject
Parameter Sets: CSObject
Aliases:

Required: True
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -DistinguishedName

コネクタ スペース オブジェクトの識別名。

```yaml
Type: String
Parameter Sets: SearchByDistinguishedName
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -識別子

コネクタ スペース オブジェクトの識別子。

```yaml
Type: Guid
Parameter Sets: SearchByIdentifier
Aliases:

Required: True
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Invoke-ADSyncRunProfile

#### 概要

特定の実行プロファイルを呼び出します。

#### 構文

##### ConnectorName

```powershell
Invoke-ADSyncRunProfile -ConnectorName <String> -RunProfileName <String> [-Resume] [<CommonParameters>]
```

##### ConnectorIdentifier

```powershell
Invoke-ADSyncRunProfile -ConnectorIdentifier <Guid> -RunProfileName <String> [-Resume] [<CommonParameters>]
```

#### Description

特定の実行プロファイルを呼び出します。

#### 例

##### 例 1

```powershell
PS C:\> Invoke-ADSyncRunProfile -ConnectorName "contoso.com" -RunProfileName Export
```

'contoso.com' コネクタでエクスポートを呼び出します。

#### パラメーター

##### -ConnectorIdentifier

コネクタの識別子。

```yaml
Type: Guid
Parameter Sets: ConnectorIdentifier
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -ConnectorName

コネクタの名前。

```yaml
Type: String
Parameter Sets: ConnectorName
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -レジュメ

以前にストールした RunProfile または半完了の RunProfile の再開を試みるパラメーター スイッチ。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RunProfileName

選択したコネクタで呼び出す実行プロファイルの名前。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.String

##### System.Guid

#### 出力

##### System.Object

### Remove-ADSyncAADServiceAccount

#### 概要

Microsoft Entra テナントの既存の Microsoft Entra 同期サービス アカウント (指定された資格情報に関連付けられている) を削除します。

#### 構文

```powershell
Remove-ADSyncAADServiceAccount [-AADCredential] <PSCredential> [-Name] <String> [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### Description

Microsoft Entra テナントの既存の Microsoft Entra 同期サービス アカウント (指定された資格情報に関連付けられている) を削除します。

#### 例

##### 例 1

```powershell
PS C:\> $credEntra = Get-Credential
PS C:\> Remove-ADSyncAADServiceAccount -AADCredential $credEntra -Name Sync_CONNECT01
```

 Sync\_CONNECT01@Contoso.onmicrosoft.comという Microsoft Entra 同期サービス アカウントを削除します。

#### パラメーター

##### -AADCredential

Microsoft Entra 資格情報。

```yaml
Type: PSCredential
Parameter Sets: ServiceAccount
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -名前

サービス アカウント名 (ドメイン サフィックスなし)。

```yaml
Type: String
Parameter Sets: ServiceAccount
Aliases:

Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -確認

コマンドレットを実行する前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.Management.Automation.PSCredential

##### System.String

#### 出力

##### System.Object

### Set-ADSyncAutoUpgrade

#### 概要

インストール時の AutoUpgrade の状態を [有効] と [無効] の間で変更します。

#### 構文

```powershell
Set-ADSyncAutoUpgrade [-AutoUpgradeState] <AutoUpgradeConfigurationState> [[-SuspensionReason] <String>]
[<CommonParameters>]
```

#### Description

インストール時の AutoUpgrade 状態を設定します。 このコマンドレットは、[有効] と [無効] の間で AutoUpgrade の状態を変更する場合にのみ使用してください。 システムのみが状態を中断に設定する必要があります。

#### 例

##### 例 1

```powershell
PS C:\> Set-ADSyncAutoUpgrade -AutoUpgradeState Enabled
```

AutoUpgrade の状態を [有効] に設定します。

#### パラメーター

##### -AutoUpgradeState

AtuoUpgrade の状態。 指定できる値: Suspended、Enabled、Disabled。

```yaml
Type: AutoUpgradeConfigurationState
Parameter Sets: (All)
Aliases:
Accepted values: Suspended, Enabled, Disabled

Required: True
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SuspensionReason

中断の理由。 自動アップグレードの状態を一時停止に設定する必要があるのは、システムだけです。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Set-ADSyncScheduler

#### 概要

同期スケジューラの現在の同期サイクル設定を設定します。

#### 構文

```powershell
Set-ADSyncScheduler [[-CustomizedSyncCycleInterval] <TimeSpan>] [[-SyncCycleEnabled] <Boolean>]
[[-NextSyncCyclePolicyType] <SynchronizationPolicyType>] [[-PurgeRunHistoryInterval] <TimeSpan>]
[[-MaintenanceEnabled] <Boolean>] [[-SchedulerSuspended] <Boolean>] [-Force] [<CommonParameters>]
```

#### Description

同期スケジューラの現在の同期サイクル設定を設定します。

#### 例

##### 例 1

```powershell
PS C:\> Set-ADSyncScheduler -SyncCycleEnabled $true
```

SyncCycleEnabled の現在の同期サイクル設定を True に設定します。

#### パラメーター

##### -CustomizedSyncCycleInterval

設定するカスタム同期間隔の期間の値を指定します。 許可されている最小の設定で実行する場合は、このパラメーターを null に設定します。

```yaml
Type: TimeSpan
Parameter Sets: (All)
Aliases:

Required: False
Position: 0
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -フォース

値の設定を強制するためのパラメーター スイッチ。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: 6
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -MaintenanceEnabled

MaintenanceEnabled を設定するためのパラメーター。

```yaml
Type: Boolean
Parameter Sets: (All)
Aliases:

Required: False
Position: 4
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -NextSyncCyclePolicyType

NextSyncCyclePolicyType を設定するためのパラメーター。 受け入れ可能な値: Unspecified、Delta、Initial。

```yaml
Type: SynchronizationPolicyType
Parameter Sets: (All)
Aliases:
Accepted values: Unspecified, Delta, Initial

Required: False
Position: 2
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -PurgeRunHistoryInterval

PurgeRunHistoryInterval を設定するためのパラメーター。

```yaml
Type: TimeSpan
Parameter Sets: (All)
Aliases:

Required: False
Position: 3
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -SchedulerSuspended

SchedulerSuspended を設定するためのパラメーター。

```yaml
Type: Boolean
Parameter Sets: (All)
Aliases:

Required: False
Position: 5
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -SyncCycleEnabled

SyncCycleEnabled を設定するためのパラメーター。

```yaml
Type: Boolean
Parameter Sets: (All)
Aliases:

Required: False
Position: 1
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.Nullable'1[[System.TimeSpan, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089]]

##### System.Nullable'1[[System.Boolean, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089]]

##### System.Nullable'1[[Microsoft.IdentityManagement.PowerShell.ObjectModel.SynchronizationPolicyType, Microsoft.IdentityManagement.PowerShell.ObjectModel, Version=1.4.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35]]

##### System.Management.Automation.SwitchParameter

#### 出力

##### System.Object

### Set-ADSyncSchedulerConnectorOverride

#### 概要

同期スケジューラの現在の同期サイクル設定を設定します。

#### 構文

##### ConnectorIdentifier

```powershell
Set-ADSyncSchedulerConnectorOverride -ConnectorIdentifier <Guid> [-FullImportRequired <Boolean>]
[-FullSyncRequired <Boolean>] [<CommonParameters>]
```

##### ConnectorName

```powershell
Set-ADSyncSchedulerConnectorOverride -ConnectorName <String> [-FullImportRequired <Boolean>]
[-FullSyncRequired <Boolean>] [<CommonParameters>]
```

#### Description

同期スケジューラの現在の同期サイクル設定を設定します。

#### 例

##### 例 1

```powershell
PS C:\> Set-ADSyncSchedulerConnectorOverride -Connectorname "contoso.com" -FullImportRequired $true
-FullSyncRequired $false
```

完全なインポートを必要とし、完全同期を必要としないように、'contoso.com' コネクタの同期サイクル設定を設定します。

#### パラメーター

##### -ConnectorIdentifier

コネクタ識別子。

```yaml
Type: Guid
Parameter Sets: ConnectorIdentifier
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -ConnectorName

コネクタ名。

```yaml
Type: String
Parameter Sets: ConnectorName
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -FullImportRequired

次のサイクルで完全なインポートを要求するには、true に設定します。

```yaml
Type: Boolean
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -FullSyncRequired

次のサイクルで完全同期を要求するには、true に設定します。

```yaml
Type: Boolean
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.Guid

##### System.String

##### System.Nullable'1[[System.Boolean, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089]]

#### 出力

##### System.Object

### Start-ADSyncPurgeRunHistory

#### 概要

指定された期間より古い実行履歴を消去するコマンドレット。

#### 構文

##### オンライン

```powershell
Start-ADSyncPurgeRunHistory [[-PurgeRunHistoryInterval]  <TimeSpan>] [<CommonParameters>]
```

##### オフライン

```powershell
Start-ADSyncPurgeRunHistory [-Offline] [<CommonParameters>]
```

#### Description

指定された期間より古い実行履歴を消去するコマンドレット。

#### 例

##### 例 1

```powershell
PS C:\> Start-ADSyncPurgeRunHistory -PurgeRunHistoryInterval (New-Timespan -Hours 5)
```

5 時間以上前のすべての実行履歴を消去します。

#### パラメーター

##### -オフライン

サービスがオフラインの間、データベースからすべての実行履歴を消去します。

```yaml
Type: SwitchParameter
Parameter Sets: offline
Aliases:

Required: True
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -PurgeRunHistoryInterval

履歴を保持する間隔。

```yaml
Type: TimeSpan
Parameter Sets: online
Aliases:

Required: False
Position: 0
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.TimeSpan

#### 出力

##### System.Object

### Start-ADSyncSyncCycle

#### 概要

同期サイクルをトリガーします。

#### 構文

```powershell
Start-ADSyncSyncCycle [[-PolicyType] <SynchronizationPolicyType>] [[-InteractiveMode] <Boolean>]
[<CommonParameters>]
```

#### Description

同期サイクルをトリガーします。

#### 例

##### 例 1

```powershell
PS C:\> Start-ADSyncSyncCycle -PolicyType Initial
```

初期ポリシーの種類を使用して同期サイクルをトリガーします。

#### パラメーター

##### -InteractiveMode

対話型 (コマンド ライン) モードとスクリプト/コード モード (他のコードからの呼び出し) を区別します。

```yaml
Type: Boolean
Parameter Sets: (All)
Aliases:

Required: False
Position: 2
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -PolicyType

実行するポリシーの種類。 受け入れ可能な値: Unspecified、Delta、Initial。

```yaml
Type: SynchronizationPolicyType
Parameter Sets: (All)
Aliases:
Accepted values: Unspecified, Delta, Initial

Required: False
Position: 1
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.Nullable'1[[Microsoft.IdentityManagement.PowerShell.ObjectModel.SynchronizationPolicyType, Microsoft.IdentityManagement.PowerShell.ObjectModel, Version=1.4.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35]]

##### System.Boolean

#### 出力

##### System.Object

### Stop-ADSyncRunProfile

#### 概要

すべてのビジー状態のコネクタまたは指定されたビジー状態のコネクタを検索して停止します。

#### 構文

```powershell
Stop-ADSyncRunProfile [[-ConnectorName] <String>] [<CommonParameters>]
```

#### Description

すべてのビジー状態のコネクタまたは指定されたビジー状態のコネクタを検索して停止します。

#### 例

##### 例 1

```powershell
PS C:\> Stop-ADSyncRunProfile -ConnectorName "contoso.com"
```

'contoso.com' で実行中の同期を停止します。

#### パラメーター

##### -ConnectorName

コネクタの名前。 コネクタが指定されていない場合、すべてのビジー状態のコネクタが停止します。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: 0
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Stop-ADSyncSyncCycle

#### 概要

現在実行中の同期サイクルを停止するようにサーバーに通知します。

#### 構文

```powershell
Stop-ADSyncSyncCycle [<CommonParameters>]
```

#### Description

現在実行中の同期サイクルを停止するようにサーバーに通知します。

#### 例

##### 例 1

```powershell
PS C:\> Stop-ADSyncSyncCycle
```

現在実行中の同期サイクルを停止するようにサーバーに通知します。

#### パラメーター

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Sync-ADSyncCSObject

#### 概要

コネクタ スペース オブジェクトで同期プレビューを実行します。

#### 構文

##### ConnectorName\_ObjectDN

```powershell
Sync-ADSyncCSObject -ConnectorName <String> -DistinguishedName <String> [-Commit] [<CommonParameters>]
```

##### ConnectorIdentifier\_ObjectDN

```powershell
Sync-ADSyncCSObject -ConnectorIdentifier <Guid> -DistinguishedName <String> [-Commit] [<CommonParameters>]
```

##### オブジェクト識別子

```powershell
Sync-ADSyncCSObject -Identifier <Guid> [-Commit] [<CommonParameters>]
```

#### Description

コネクタ スペース オブジェクトで同期プレビューを実行します。

#### 例

##### 例 1

```powershell
PS C:\> Sync-ADSyncCSObject -ConnectorName "contoso.com" -DistinguishedName "CN=fabrikam,CN=Users,DC=contoso,DC=com"
```

指定したオブジェクトの同期プレビューを返します。

#### パラメーター

##### -犯す

コミットのパラメーター スイッチ。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ConnectorIdentifier

コネクタの識別子。

```yaml
Type: Guid
Parameter Sets: ConnectorIdentifier_ObjectDN
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ConnectorName

コネクタの名前。

```yaml
Type: String
Parameter Sets: ConnectorName_ObjectDN
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -DistinguishedName

コネクタ スペース オブジェクトの識別名。

```yaml
Type: String
Parameter Sets: ConnectorName_ObjectDN, ConnectorIdentifier_ObjectDN
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -識別子

コネクタ スペース オブジェクトの識別子。

```yaml
Type: Guid
Parameter Sets: ObjectIdentifier
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### なし

#### 出力

##### System.Object

### Test-AdSyncAzureServiceConnectivity

#### 概要

Microsoft Entra ID への接続の問題を調査して特定します。

#### 構文

##### ByEnvironment

```powershell
Test-AdSyncAzureServiceConnectivity [-AzureEnvironment] <Identifier> [[-Service] <AzureService>] [-CurrentUser]
[<CommonParameters>]
```

##### ByTenantName

```powershell
Test-AdSyncAzureServiceConnectivity [-Domain] <String> [[-Service] <AzureService>] [-CurrentUser]
[<CommonParameters>]
```

#### Description

Microsoft Entra ID への接続の問題を調査して特定します。

#### 例

##### 例 1

```powershell
PS C:\> Test-AdSyncAzureServiceConnectivity -AzureEnvironment Worldwide -Service SecurityTokenService -CurrentUser
```

接続の問題がない場合は "True" を返します。

#### パラメーター

##### -AzureEnvironment

テストする Azure 環境。 使用できる値: Worldwide、China、UsGov、Germany、AzureUSGovernmentCloud、AzureUSGovernmentCloud2、AzureUSGovernmentCloud3、PreProduction、OneBox、Default。

```yaml
Type: Identifier
Parameter Sets: ByEnvironment
Aliases:
Accepted values: Worldwide, China, UsGov, Germany, AzureUSGovernmentCloud, AzureUSGovernmentCloud2, AzureUSGovernmentCloud3, PreProduction, OneBox, Default

Required: True
Position: 0
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -CurrentUser

コマンドレットを実行しているユーザー。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: 3
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -ドメイン

接続がテストされているドメイン。

```yaml
Type: String
Parameter Sets: ByTenantName
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -サービス

接続がテストされているサービス。

```yaml
Type: AzureService
Parameter Sets: (All)
Aliases:
Accepted values: SecurityTokenService, AdminWebService

Required: False
Position: 2
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### Microsoft.Online.Deployment.Client.Framework.MicrosoftOnlineInstance+Identifier

##### System.String

##### System.Nullable'1[[Microsoft.Online.Deployment.Client.Framework.AzureService, Microsoft.Online.Deployment.Client.Framework, Version=1.6.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35]]

##### System.Management.Automation.SwitchParameter

#### 出力

##### System.Object

### Test-AdSyncUserHasPermissions

#### 概要

Active Directory コネクタ アカウントに必要なアクセス許可があるかどうかを確認するコマンドレット。

#### 構文

```powershell
Test-AdSyncUserHasPermissions [-ForestFqdn] <String> [-AdConnectorId] <Guid>
[-AdConnectorCredential] <PSCredential> [-BaseDn] <String> [-PropertyType] <String> [-PropertyValue] <String>
[-WhatIf] [-Confirm] [<CommonParameters>]
```

#### Description

Active Directory コネクタ アカウントに必要なアクセス許可があるかどうかを確認するコマンドレット。

#### 例

##### 例 1

```powershell
PS C:\> Test-AdSyncUserHasPermissions -ForestFqdn "contoso.com" -AdConnectorId "00000000-0000-0000-000000000000"
-AdConnectorCredential $connectorAcctCreds -BaseDn "CN=fabrikam,CN=Users,DC=contoso,DC=com" -PropertyType "Allowed-Attributes" -PropertyValue "name"
```

ADMA ユーザーがユーザー 'fabrikam' の 'name' プロパティにアクセスするアクセス許可を持っているかどうかを確認します。

#### パラメーター

##### -AdConnectorCredential

AD Connector アカウントの資格情報。

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:

Required: True
Position: 2
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -AdConnectorId

AD コネクタ ID。

```yaml
Type: Guid
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -BaseDn

チェックするオブジェクトのベース DN。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 3
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットを実行する前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ForestFqdn

フォレストの名前。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 0
Default value: None
Accept pipeline input: True (ByValue)
Accept wildcard characters: False
```

##### -PropertyType

探しているアクセス許可の種類。 受け入れ可能な値: Allowed-Attributes、Allowed-Attributes-effective、Allowed-Child-classes、Allowed-Child-classes-effective。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 4
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -PropertyValue

PropertyType 属性で探している値。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 5
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

##### System.String

##### System.Guid

#### 出力

##### System.Object
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-adsyncconfig"} -->
## Microsoft Entra Connect: ADSyncConfig PowerShell リファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-adsyncconfig
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このドキュメントでは、ADSyncConfig.psm1 PowerShell モジュールの参照情報を示します。

次のドキュメントには、Microsoft Entra Connect に含まれる `ADSyncConfig.psm1` PowerShell モジュールの参照情報が記載されています。

### Get-ADSyncADConnectorAccount

#### 概要

各 AD コネクタに構成されているアカウント名とドメインを取得します

#### 構文

```
Get-ADSyncADConnectorAccount
```

#### 説明

この関数では、Microsoft Entra コネクトにある 'Get-ADSyncConnector' コマンドレットを使用し、接続パラメーターから AD コネクタ アカウントを示すテーブル取得します。

#### 例

##### 例 1

```
Get-ADSyncADConnectorAccount
```

### Get-ADSyncObjectsWithInheritanceDisabled

#### 概要

アクセス許可の継承が無効になっている AD オブジェクトを取得します

#### 構文

```
Get-ADSyncObjectsWithInheritanceDisabled [-SearchBase] <String> [[-ObjectClass] <String>] [<CommonParameters>]
```

#### 説明

AD で SearchBase パラメーターから開始し検索を実行し、ACL の継承が現在無効になっていて ObjectClass パラメーターによってフィルター処理されているオブジェクトをすべて返します。

#### 例

##### 例 1

'Contoso' ドメインで継承が無効になっているオブジェクトを検索します (既定では 'organizationalUnit' オブジェクトのみが返されます)

```
Get-ADSyncObjectsWithInheritanceDisabled -SearchBase 'Contoso'
```

##### 例 2

'Contoso' ドメインで継承が無効になっている 'user' オブジェクトを検索します

```
Get-ADSyncObjectsWithInheritanceDisabled -SearchBase 'Contoso' -ObjectClass 'user'
```

##### 例 3

OU 内で継承が無効になっているすべての種類のオブジェクトを検索します

```
Get-ADSyncObjectsWithInheritanceDisabled -SearchBase OU=AzureAD,DC=Contoso,DC=com -ObjectClass '*'
```

#### パラメーター

##### -SearchBase

AD ドメインの DistinguishedName または FQDN が可能な LDAP クエリの SearchBase

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ObjectClass

'\*' (すべてのオブジェクト クラス)、'user'、group'、'container' など、検索するオブジェクトのクラス。 既定で、この関数は 'organizationalUnit' オブジェクト クラスを検索します。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: 2
Default value: OrganizationalUnit
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Set-ADSyncBasicReadPermissions

#### 概要

指定されたコネクターアカウントに対して、ご使用の Active Directory フォレストとドメインを読み取るためのアクセス権を付与します。

#### 構文

##### ユーザードメイン

```
Set-ADSyncBasicReadPermissions -ADConnectorAccountName <String> -ADConnectorAccountDomain <String>
 [-ADobjectDN <String>] [-SkipAdminSdHolders] [-WhatIf] [-Confirm] [<CommonParameters>]
```

##### DistinguishedName

```
Set-ADSyncBasicReadPermissions -ADConnectorAccountDN <String> [-ADobjectDN <String>] [-SkipAdminSdHolders]
 [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### 説明

Set-ADSyncBasicReadPermissions 関数は、必要なアクセス許可を AD 同期アカウントに付与します。これには次のものが含まれます。1.すべての子孫コンピューター オブジェクトのすべての属性に対する読み取りプロパティ アクセス 2.すべての子孫デバイス オブジェクトのすべての属性に対する読み取りプロパティ アクセス 3. すべての子孫 foreignsecurityprincipal オブジェクトのすべての属性に対する読み取りプロパティ アクセス 5. すべての子孫ユーザー オブジェクトのすべての属性に対する読み取りプロパティ アクセス 6.すべての子孫 inetorgperson オブジェクトのすべての属性に対する読み取りプロパティ アクセス 7。すべての子孫グループ オブジェクトのすべての属性に対する読み取りプロパティ アクセス 8.すべての子孫連絡先オブジェクトのすべての属性に対する読み取りプロパティ アクセス

これらのアクセス許可は、フォレスト内のすべてのドメインに適用されます。 必要に応じて ADobjectDN パラメーターに DistinguishedName を指定し、これらのアクセス許可をその AD オブジェクトのみに設定できます (サブ オブジェクトへの継承を含む)。

#### 例

##### 例 1

```
Set-ADSyncBasicReadPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com'
```

##### 例 2

```
Set-ADSyncBasicReadPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com'
```

##### 例 3

```
Set-ADSyncBasicReadPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com' -SkipAdminSdHolders
```

##### 例 4

```
Set-ADSyncBasicReadPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com' -ADobjectDN 'OU=AzureAD,DC=Contoso,DC=com'
```

#### パラメーター

##### -ADConnectorAccountName

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの名前。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDomain

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントのドメイン。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDN

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの DistinguishedName。

```yaml
Type: String
Parameter Sets: DistinguishedName
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADobjectDN

アクセス許可を設定するターゲットの AD オブジェクトの DistinguishedName (省略可能)

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SkipAdminSdHolders

これらのアクセス許可で AdminSDHolder コンテナーを更新しないことを示す省略可能なパラメーター

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットの実行時に発生する内容を示します。 このコマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットの実行前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Set-ADSyncExchangeHybridPermissions

#### 概要

指定されたコネクターアカウントに対して、ご使用の Active Directory フォレストとドメインで利用される Exchange ハイブリッド機能のためのアクセス権を付与します。

#### 構文

##### ユーザードメイン

```
Set-ADSyncExchangeHybridPermissions -ADConnectorAccountName <String> -ADConnectorAccountDomain <String>
 [-ADobjectDN <String>] [-SkipAdminSdHolders] [-WhatIf] [-Confirm] [<CommonParameters>]
```

##### DistinguishedName

```
Set-ADSyncExchangeHybridPermissions -ADConnectorAccountDN <String> [-ADobjectDN <String>] [-SkipAdminSdHolders]
 [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### 説明

Set-ADSyncExchangeHybridPermissions 関数は、AD 同期アカウントに必要なアクセス許可を付与します。これには、次のものが含まれます。1.すべての子孫ユーザー オブジェクトのすべての属性に対する読み取り/書き込みプロパティ アクセス 2.すべての子孫 inetorgperson オブジェクトのすべての属性に対する読み取り/書き込みプロパティ アクセス 3.すべての子孫グループ オブジェクトのすべての属性に対する読み取り/書き込みプロパティ アクセス 4.すべての子孫連絡先オブジェクトのすべての属性に対する読み取り/書き込みプロパティ アクセス

これらのアクセス許可は、フォレスト内のすべてのドメインに適用されます。 必要に応じて ADobjectDN パラメーターに DistinguishedName を指定し、これらのアクセス許可をその AD オブジェクトのみに設定できます (サブ オブジェクトへの継承を含む)。

#### 例

##### 例 1

```
Set-ADSyncExchangeHybridPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com'
```

##### 例 2

```
Set-ADSyncExchangeHybridPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com'
```

##### 例 3

```
Set-ADSyncExchangeHybridPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com' -SkipAdminSdHolders
```

##### 例 4

```
Set-ADSyncExchangeHybridPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com' -ADobjectDN 'OU=AzureAD,DC=Contoso,DC=com'
```

#### パラメーター

##### -ADConnectorAccountName

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの名前。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDomain

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントのドメイン。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDN

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの DistinguishedName。

```yaml
Type: String
Parameter Sets: DistinguishedName
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADobjectDN

アクセス許可を設定するターゲットの AD オブジェクトの DistinguishedName (省略可能)

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SkipAdminSdHolders

これらのアクセス許可で AdminSDHolder コンテナーを更新しないことを示す省略可能なパラメーター

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットの実行時に発生する内容を示します。 このコマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットの実行前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Set-ADSyncExchangeMailPublicFolderPermissions

#### 概要

指定されたコネクターアカウントに対して、ご使用の Active Directory フォレストとドメインで利用される Exchange メールのパブリック フォルダー機能のためのアクセス権を付与します。

#### 構文

##### ユーザードメイン

```
Set-ADSyncExchangeMailPublicFolderPermissions -ADConnectorAccountName <String>
 -ADConnectorAccountDomain <String> [-ADobjectDN <String>] [-SkipAdminSdHolders] [-WhatIf] [-Confirm]
 [<CommonParameters>]
```

##### DistinguishedName

```
Set-ADSyncExchangeMailPublicFolderPermissions -ADConnectorAccountDN <String> [-ADobjectDN <String>]
 [-SkipAdminSdHolders] [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### 説明

Set-ADSyncExchangeMailPublicFolderPermissions 関数は、必要なアクセス許可を AD 同期アカウントに付与します。これには以下が含まれます。1. すべての子孫 publicfolder オブジェクトのすべての属性に対する読み取りプロパティアクセス

これらのアクセス許可は、フォレスト内のすべてのドメインに適用されます。 必要に応じて ADobjectDN パラメーターに DistinguishedName を指定し、これらのアクセス許可をその AD オブジェクトのみに設定できます (サブ オブジェクトへの継承を含む)。

#### 例

##### 例 1

```
Set-ADSyncExchangeMailPublicFolderPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com'
```

##### 例 2

```
Set-ADSyncExchangeMailPublicFolderPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com'
```

##### 例 3

```
Set-ADSyncExchangeMailPublicFolderPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com' -SkipAdminSdHolders
```

##### 例 4

```
Set-ADSyncExchangeMailPublicFolderPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com' -ADobjectDN 'OU=AzureAD,DC=Contoso,DC=com'
```

#### パラメーター

##### -ADConnectorAccountName

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの名前。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDomain

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントのドメイン。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDN

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの DistinguishedName。

```yaml
Type: String
Parameter Sets: DistinguishedName
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADobjectDN

アクセス許可を設定するターゲットの AD オブジェクトの DistinguishedName (省略可能)

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SkipAdminSdHolders

これらのアクセス許可で AdminSDHolder コンテナーを更新しないことを示す省略可能なパラメーター

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットの実行時に発生する内容を示します。 このコマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットの実行前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Set-ADSyncMsDsConsistencyGuidPermissions

#### 概要

指定されたコネクターアカウントに対して、ご使用の Active Directory フォレストとドメインで利用される mS-DS-ConsistencyGuid 機能のためのアクセス権を付与します。

#### 構文

##### ユーザードメイン

```
Set-ADSyncMsDsConsistencyGuidPermissions -ADConnectorAccountName <String> -ADConnectorAccountDomain <String>
 [-ADobjectDN <String>] [-SkipAdminSdHolders] [-WhatIf] [-Confirm] [<CommonParameters>]
```

##### DistinguishedName

```
Set-ADSyncMsDsConsistencyGuidPermissions -ADConnectorAccountDN <String> [-ADobjectDN <String>]
 [-SkipAdminSdHolders] [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### 説明

Set-ADSyncMsDsConsistencyGuidPermissions 関数は、AD 同期アカウントに必要なアクセス許可を付与します。これには、以下が含まれます。1. すべての子孫ユーザー オブジェクトの mS-DS-ConsistencyGuid 属性への読み取り/書き込みプロパティ アクセス

これらのアクセス許可は、フォレスト内のすべてのドメインに適用されます。 必要に応じて ADobjectDN パラメーターに DistinguishedName を指定し、これらのアクセス許可をその AD オブジェクトのみに設定できます (サブ オブジェクトへの継承を含む)。

#### 例

##### 例 1

```
Set-ADSyncMsDsConsistencyGuidPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com'
```

##### 例 2

```
Set-ADSyncMsDsConsistencyGuidPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com'
```

##### 例 3

```
Set-ADSyncMsDsConsistencyGuidPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com' -SkipAdminSdHolders
```

##### 例 4

```
Set-ADSyncMsDsConsistencyGuidPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com' -ADobjectDN 'OU=AzureAD,DC=Contoso,DC=com'
```

#### パラメーター

##### -ADConnectorAccountName

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの名前。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDomain

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントのドメイン。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDN

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの DistinguishedName。

```yaml
Type: String
Parameter Sets: DistinguishedName
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADobjectDN

アクセス許可を設定するターゲットの AD オブジェクトの DistinguishedName (省略可能)

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SkipAdminSdHolders

これらのアクセス許可で AdminSDHolder コンテナーを更新しないことを示す省略可能なパラメーター

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットの実行時に発生する内容を示します。 このコマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットの実行前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Set-ADSyncPasswordHashSyncPermissions

#### 概要

指定されたコネクターアカウントに対して、ご使用の Active Directory フォレストとドメインで利用されるパスワード ハッシュの同期機能のためのアクセス権を付与します。

#### 構文

##### ユーザードメイン

```
Set-ADSyncPasswordHashSyncPermissions -ADConnectorAccountName <String> -ADConnectorAccountDomain <String>
 [-WhatIf] [-Confirm] [<CommonParameters>]
```

##### DistinguishedName

```
Set-ADSyncPasswordHashSyncPermissions -ADConnectorAccountDN <String> [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### 説明

Set-ADSyncPasswordHashSyncPermissions 関数は、必要なアクセス許可を AD 同期アカウントに付与します。これには以下が含まれます。1. ディレクトリの変更のレプリケート 2.ディレクトリの変更をすべてレプリケートする

これらのアクセス許可は、フォレストのすべてのドメインに付与されます。

#### 例

##### 例 1

```
Set-ADSyncPasswordHashSyncPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com'
```

##### 例 2

```
Set-ADSyncPasswordHashSyncPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com'
```

#### パラメーター

##### -ADConnectorAccountName

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される Active Directory アカウントの名前。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDomain

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される Active Directory アカウントのドメイン。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDN

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される Active Directory アカウントの DistinguishedName。

```yaml
Type: String
Parameter Sets: DistinguishedName
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットの実行時に発生する内容を示します。 このコマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットの実行前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Set-ADSyncPasswordWritebackPermissions

#### 概要

Microsoft Entra ID からパスワードの書き戻しを行うために、Active Directory フォレストとドメインを初期化します。

#### 構文

##### ユーザードメイン

```
Set-ADSyncPasswordWritebackPermissions -ADConnectorAccountName <String> -ADConnectorAccountDomain <String>
 [-ADobjectDN <String>] [-SkipAdminSdHolders] [-WhatIf] [-Confirm] [<CommonParameters>]
```

##### DistinguishedName

```
Set-ADSyncPasswordWritebackPermissions -ADConnectorAccountDN <String> [-ADobjectDN <String>]
 [-SkipAdminSdHolders] [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### 説明

Set-ADSyncPasswordWritebackPermissions 関数は、AD 同期アカウントに必要なアクセス許可を付与します。これには、次のものが含まれます。1.子孫ユーザー オブジェクトのパスワードのリセット 2.すべての子孫ユーザー オブジェクトの lockoutTime 属性に対する書き込みプロパティ アクセス 3.すべての子孫ユーザー オブジェクトの pwdLastSet 属性に対する書き込みプロパティ アクセス

これらのアクセス許可は、フォレスト内のすべてのドメインに適用されます。 必要に応じて ADobjectDN パラメーターに DistinguishedName を指定し、これらのアクセス許可をその AD オブジェクトのみに設定できます (サブ オブジェクトへの継承を含む)。

#### 例

##### 例 1

```
Set-ADSyncPasswordWritebackPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com'
```

##### 例 2

```
Set-ADSyncPasswordWritebackPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com'
```

##### 例 3

```
Set-ADSyncPasswordWritebackPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com' -SkipAdminSdHolders
```

##### 例 4

```
Set-ADSyncPasswordWritebackPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com' -ADobjectDN 'OU=AzureAD,DC=Contoso,DC=com'
```

#### パラメーター

##### -ADConnectorAccountName

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの名前。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDomain

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントのドメイン。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDN

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの DistinguishedName。

```yaml
Type: String
Parameter Sets: DistinguishedName
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADobjectDN

アクセス許可を設定するターゲットの AD オブジェクトの DistinguishedName (省略可能)

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SkipAdminSdHolders

これらのアクセス許可で AdminSDHolder コンテナーを更新しないことを示す省略可能なパラメーター

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットの実行時に発生する内容を示します。 このコマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットの実行前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Set-ADSyncRestrictedPermissions

#### 概要

AD で保護されているいかなるセキュリティ グループにも含まれない AD オブジェクトのアクセス許可のセキュリティを強化します。 典型的な例は、自動的に Microsoft Entra Connect によって作成される AD Connect アカウント (MSOL) です。 このアカウントは、すべてのドメインへのレプリケートのアクセス許可を持ちます。しかし、保護されていないので、簡単に侵害される可能性があります。

#### 構文

```
Set-ADSyncRestrictedPermissions [-ADConnectorAccountDN] <String> [-Credential] <PSCredential>
 [-DisableCredentialValidation] [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### 説明

Set-ADSyncRestrictedPermissions 関数は、提供されているアカウントのアクセス許可のセキュリティを強化します。 アクセス許可のセキュリティの強化には、次の手順が含まれます。

1. 指定したオブジェクトの継承を無効にします
2. SELF に固有の ACE を除き、特定のオブジェクトのすべての ACE を削除します。 SELF については、既定のアクセス許可を維持します。
3. 以下の特定のアクセス許可を割り当てます。

    | タイプ | 名前 | アクセス | 適用対象 |
    | --- | --- | --- | --- |
    | 許可する | 制 | フル コントロール | このオブジェクト |
    | 許可する | エンタープライズ管理者 | フル コントロール | このオブジェクト |
    | 許可する | ドメイン管理者 | フル コントロール | このオブジェクト |
    | 許可する | 管理者 | フル コントロール | このオブジェクト |
    | 許可する | エンタープライズ ドメイン コントローラー | コンテンツの一覧  すべてのプロパティの読み取り  読み取りのアクセス許可 | このオブジェクト |
    | 許可する | 認証済みユーザー | コンテンツの一覧  すべてのプロパティの読み取り  読み取りのアクセス許可 | このオブジェクト |

#### 例

##### 例 1

```
Set-ADSyncRestrictedPermissions -ADConnectorAccountDN "CN=TestAccount1,CN=Users,DC=Contoso,DC=com" -Credential $(Get-Credential)
```

#### パラメーター

##### -ADConnectorAccountDN

アクセス許可のセキュリティを強化する必要がある Active Directory アカウントの DistinguishedName。 これは通常は、MSOL\_nnnnnnnnnn アカウントであるか、AD コネクタに構成されているカスタム ドメイン アカウントです。

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -資格 情報

ADConnectorAccountDN アカウントに対するアクセス許可を制限するために必要な権限を持つ管理者資格情報。 これは通常、企業またはドメインの管理者です。 アカウント参照の失敗を回避するには、管理者アカウントの完全修飾ドメイン名を使用します。 例:CONTOSO\admin

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:

Required: True
Position: 2
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -DisableCredentialValidation

DisableCredentialValidation を使用すると、-Credential で提供されている資格情報が AD では有効であるか、また提供されたアカウントに ADConnectorAccountDN アカウントのアクセス許可を制限するのに必要なアクセス許可があるかが関数によって確認されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットの実行時に発生する内容を示します。 このコマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットの実行前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Set-ADSyncUnifiedGroupWritebackPermissions

#### 概要

Microsoft Entra ID からグループの書き戻しを行うために、Active Directory フォレストとドメインを初期化します。

#### 構文

##### ユーザードメイン

```
Set-ADSyncUnifiedGroupWritebackPermissions -ADConnectorAccountName <String> -ADConnectorAccountDomain <String>
 [-ADobjectDN <String>] [-SkipAdminSdHolders] [-WhatIf] [-Confirm] [<CommonParameters>]
```

##### DistinguishedName

```
Set-ADSyncUnifiedGroupWritebackPermissions -ADConnectorAccountDN <String> [-ADobjectDN <String>]
 [-SkipAdminSdHolders] [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### 説明

Set-ADSyncUnifiedGroupWritebackPermissions 関数は、必要なアクセス許可を AD 同期アカウントに付与します。これには、次のものが含まれます。1.すべてのグループ オブジェクトの種類とサブオブジェクトに対する汎用の読み取り/書き込み、削除、ツリーの削除、子の作成と削除

これらのアクセス許可は、フォレスト内のすべてのドメインに適用されます。 必要に応じて ADobjectDN パラメーターに DistinguishedName を指定し、これらのアクセス許可をその AD オブジェクトのみに設定できます (サブ オブジェクトへの継承を含む)。 この場合、GroupWriteback 機能とリンクしたいコンテナーの識別名は ADobjectDN になります。

#### 例

##### 例 1

```
Set-ADSyncUnifiedGroupWritebackPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com'
```

##### 例 2

```
Set-ADSyncUnifiedGroupWritebackPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com'
```

##### 例 3

```
Set-ADSyncUnifiedGroupWritebackPermissions -ADConnectorAccountDN 'CN=ADConnector,OU=AzureAD,DC=Contoso,DC=com' -SkipAdminSdHolders
```

##### 例 4

```
Set-ADSyncUnifiedGroupWritebackPermissions -ADConnectorAccountName 'ADConnector' -ADConnectorAccountDomain 'Contoso.com' -ADobjectDN 'OU=AzureAD,DC=Contoso,DC=com'
```

#### パラメーター

##### -ADConnectorAccountName

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの名前。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDomain

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントのドメイン。

```yaml
Type: String
Parameter Sets: UserDomain
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorAccountDN

ディレクトリ内のオブジェクトを管理するために Microsoft Entra Connect Sync によって使用される、または使用される可能性のある Active Directory アカウントの DistinguishedName。

```yaml
Type: String
Parameter Sets: DistinguishedName
Aliases:

Required: True
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADobjectDN

アクセス許可を設定するターゲットの AD オブジェクトの DistinguishedName (省略可能)

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SkipAdminSdHolders

これらのアクセス許可で AdminSDHolder コンテナーを更新しないことを示す省略可能なパラメーター

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:

Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットの実行時に発生する内容を示します。 このコマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットの実行前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf

Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。

### Show-ADSyncADObjectPermissions

#### 概要

指定した AD オブジェクトのアクセス許可を示します。

#### 構文

```
Show-ADSyncADObjectPermissions [-ADobjectDN] <String> [<CommonParameters>]
```

#### 説明

この関数では、パラメーター -ADobjectDN で提供されている特定の AD オブジェクトに現在設定されている AD アクセス許可をすべて返します。 ADobjectDN は、DistinguishedName の形式で返される必要があります。

#### 例

##### 例 1

```
Show-ADSyncADObjectPermissions -ADobjectDN 'OU=AzureAD,DC=Contoso,DC=com'
```

#### パラメーター

##### -ADobjectDN

{{ADobjectDN の説明を入力}}

```yaml
Type: String
Parameter Sets: (All)
Aliases:

Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーターをサポートしています。-Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、-WarningVariable です。 詳細については、「\_CommonParameters について (https://go.microsoft.com/fwlink/?LinkID=113216)」を参照してください。
<!-- /MSL-PAGE -->
