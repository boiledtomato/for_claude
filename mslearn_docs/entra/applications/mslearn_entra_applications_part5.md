# Microsoft Learn — Microsoft Entra / エンタープライズアプリ・プロビジョニング・アプリプロキシ (part 5)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 56

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-oracle-enterprise-business-suite-easy-button"} -->
## Oracle EBS への SSO 用に F5 BIG-IP Easy Button を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-enterprise-business-suite-easy-button
- Service: entra-id / enterprise-apps
- Article date: 2023-03-23
- Summary: F5 BIG-IP Easy Button ガイド付き構成を使用して、Oracle EBS へのヘッダーベースの SSO による SHA を実装する方法について説明します

F5 BIG-IP Easy Button ガイド付き構成を使用して、Microsoft Entra ID で Oracle E-Business Suite (EBS) を保護する方法について説明します。 BIG-IP と Microsoft Entra ID の統合には、多くの利点があります。

- Microsoft Entra の事前認証および条件付きアクセスによるゼロ トラスト ガバナンスの強化
    - 「[条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)」を参照してください。
    - 「[ゼロ トラスト セキュリティ](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/zero-trust)」を参照してください
- Microsoft Entra ID と BIG-IP 公開サービスとの間の完全な SSO
- マネージド ID と 1 つのコントロール プレーンからのアクセス
    - 「[Microsoft Entra 管理センター](https://entra.microsoft.com)」を参照

詳細情報:

- [F5 BIG-IP を Microsoft Entra ID と統合する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- [エンタープライズ アプリケーションの SSO を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)

### シナリオの説明

このシナリオでは、HTTP 認証ヘッダーを使用して保護されたコンテンツへのアクセスを管理する従来の Oracle EBS アプリケーションを説明します。

レガシ アプリケーションには、Microsoft Entra 統合をサポートするための最新のプロトコルがありません。 最新化にはコストも時間もかかり、ダウンタイムのリスクが生じます。 代わりに、プロトコルを切り換えて、F5 BIG-IP Application Delivery Controller (ADC) を使用して、レガシ アプリケーションと最新の ID コントロール プレーンとの間のギャップを埋めます。

アプリの前面に BIG-IP を配置することで、Microsoft Entra の事前認証とヘッダーに基づく SSO をサービスに追加できます。 この構成により、アプリケーションのセキュリティ態勢が向上します。

### シナリオのアーキテクチャ

セキュア ハイブリッド アクセス (SHA) ソリューションは、次のコンポーネントで構成されています。

- **Oracle EBS アプリケーション** - Microsoft Entra SHA によって保護される BIG-IP の公開済みサービス。
- **Microsoft Entra ID**- Security Assertion Markup Language (SAML) ID プロバイダー (IdP)。これにより、ユーザーの資格情報、条件付きアクセス、BIG-IP への SAML ベースの SSO が検証されます
    - SSO では、Microsoft Entra ID が BIG-IP セッション属性を提供します
- **Oracle Internet Directory (OID)**- ユーザー データベースをホストします。
    - BIG-IP では、LDAP を使用して認可属性を検証します
- **Oracle E-Business Suite AccessGate** - OID サービスで認可属性を検証し、EBS アクセス Cookie を発行します
- **BIG-IP**- アプリケーションへのリバース プロキシおよび SAML サービス プロバイダー (SP)
    - 認証が SAML IdP に委任された後、Oracle アプリケーションへのヘッダーベースの SSO が発生します

SHA では、SP と IdP によって開始されるフローがサポートされます。 次の図は、SP によって開始されるフローを示しています。

[Image: SP によって開始されるフローに基づいたセキュア ハイブリッド アクセスの図。]

1. ユーザーがアプリケーション エンドポイント (BIG-IP) に接続します。
2. BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトします。
3. Microsoft Entra がユーザーを事前認証し、条件付きアクセス ポリシーを適用します。
4. ユーザーは BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行されます。
5. BIG-IP によって、ユーザーの一意の ID (UID) 属性に対して LDAP クエリが実行されます。
6. BIG-IP によって、返された UID 属性が、Oracle EBS セッション Cookie 要求の user\_orclguid ヘッダーとして Oracle AccessGate に挿入されます。
7. Oracle AccessGate によって、OID サービスに対して UID が検証され、Oracle EBS アクセス Cookie が発行されます。
8. Oracle EBS ユーザー ヘッダーと Cookie がアプリケーションに送信され、ユーザーにペイロードが返されます。

### 前提条件

次のコンポーネントが必要です。

- Azure サブスクリプションとして
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得してください。
- クラウド アプリケーション管理者またはアプリケーション管理者ロール。
- BIG-IP、または Azure に BIG-IP Virtual Edition (VE) をデプロイします
    - 「[F5 BIG-IP Virtual Edition VM を Azure にデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)」を参照してください
- 次のいずれかの F5 BIG-IP ライセンス SKU
    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス
    - BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - 90 日間の BIG-IP 全機能試用版。 [無料試用版](https://www.f5.com/trial/big-ip-trial.php)に関するページを参照してください。
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID
    - 「[Microsoft Entra Connect Sync: 同期について理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)」を参照してください
- HTTPS でサービスを公開するための SSL 証明書。または、テスト中は既定の証明書を使用します
    - 「[SSL プロファイル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide#ssl-profile)」を参照してください
- Oracle EBS、Oracle AccessGate、LDAP 対応 Oracle Internet Database (OID)

### BIG-IP の構成方法

このチュートリアルでは、Guided Configuration v16.1 Easy Button テンプレートを使用します。 Easy Button を使用すると、管理者は SHA のサービスを有効にするために行き来する必要がなくなります。 APM のガイド付き構成ウィザードと Microsoft Graph によって、デプロイとポリシー管理が処理されます。 この統合により、アプリケーションでは確実に ID フェデレーション、SSO、条件付きアクセスをサポートでき、管理オーバーヘッドが軽減されます。

Note

サンプルの文字列や値は、お使いの環境のものに置き換えてください。

### Easy Button を登録する

クライアントまたはサービスは、Microsoft Graph にアクセスする前に、Microsoft ID プラットフォームによって信頼される必要があります。

詳細情報: [クイック スタート: Microsoft ID プラットフォームにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)

Graph への Easy Button アクセスを認可するために、テナント アプリの登録を作成します。 BIG-IP では、公開済みアプリケーションの SAML SP インスタンスと、SAML IdP となる Microsoft Entra ID の間に信頼を確立するための構成をプッシュします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**アプリ登録**&gt;に移動し、**新規登録**を選択します。
3. アプリケーションの**名前**を入力します。 たとえば、F5 BIG-IP Easy Button です
4. アプリケーションを使用できるユーザー &gt;**[Accounts in this organizational directory only] (この組織ディレクトリのアカウントのみ)** を指定します。
5. **[登録]** を選択します。
6. **[API permissions] (API のアクセス許可)** に移動します。
7. 次の Microsoft Graph **アプリケーションのアクセス許可**を認可します。

    - Application.Read.All
    - Application.ReadWrite.All
    - Application.ReadWrite.OwnedBy
    - Directory.Read.All (ディレクトリのすべてを読む)
    - Group.Read.All
    - IdentityRiskyUser.Read.All（アイデンティティリスキーユーザー.リード.オール）
    - Policy.Read.All
    - ポリシー.読み取り書き込み.アプリケーション設定
    - Policy.ReadWrite.ConditionalAccess
    - User.Read.All
8. 組織に管理者の同意を付与します。
9. **[証明書とシークレット]** にアクセスします。
10. 新しい**クライアント シークレット**を生成します。 クライアント シークレットをメモします。
11. **[概要]** に移動します。 クライアント ID とテナント ID をメモします。

### Easy Button を構成する

1. APM **Guided Configuration** (ガイド付き構成) を開始します。
2. **Easy Button** テンプレートを開始します。
3. **[Access](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/アクセス) &gt; [Guided Configuration] &gt; [Microsoft Integration](Microsoft 統合)** に移動します。
4. **[Microsoft Entra アプリケーション]** を選択します。
5. 構成オプションを確認します。
6. **[次へ]** を選択します。
7. アプリケーションの発行に役立つ図を使用します。

    [Image: 構成領域を示す図のスクリーンショット。]

#### 構成プロパティ

**[構成のプロパティ]** タブでは、BIG-IP アプリケーション構成と SSO オブジェクトが作成されます。 **[Azure サービス アカウントの詳細]** セクションには、Microsoft Entra テナントに登録したクライアントがアプリケーションとして表示されます。 BIG-IP OAuth クライアントでは、これらの設定を使用して、SSO プロパティと共に SAML SP をテナントに登録します。 Easy Button により、発行されて SHA が有効になっている BIG-IP サービスに対してこのアクションが実行されます。

時間と労力を削減するために、グローバル設定を再利用して他のアプリケーションを発行します。

1. **構成名**を入力します。
2. **[Single sign-on (SSO) & HTTP Headers] (シングル サインオン (SSO) と HTTP ヘッダー)** で、**[On] (オン)** を選びます。
3. **[テナント ID]、[クライアント ID]**、**[クライアント シークレット]** には、Easy Button クライアント登録時にメモした内容を入力します。
4. BIG-IP がテナントに接続されたことを確認します。
5. **[次へ]** を選択します。

#### サービス プロバイダー

保護されたアプリケーションの SAML SP インスタンスのプロパティには、サービス プロバイダー設定を使用します。

1. **[ホスト]** には、アプリケーションのパブリック FQDN を入力します。
2. **[エンティティ ID]** には、トークンを要求する SAML SP に対して Microsoft Entra ID によって使用される識別子を入力します。

    [Image: サービス プロバイダーの入力とオプションのスクリーンショット。]
3. (省略可能) **[セキュリティ設定]** で、**[暗号化アサーションを有効にする]** オプションをオンまたはオフにします。 Microsoft Entra ID と BIG-IP APM の間でアサーションを暗号化することは、コンテンツ トークンが傍受されないこと、および個人や会社のデータが侵害されないことを意味します。
4. **[アサーション解読秘密キー]** の一覧から、**[新規作成]** を選択します

    [Image: [Assertion Decryption Private Key] (アサーション解読の秘密キー) ドロップダウンにある [Create New] (新規作成) オプションのスクリーンショット。]
5. **OK** を選択します。
6. 新しいタブで **[SSL 証明書とキーのインポート]** ダイアログが表示されます。
7. **[PKCS 12 (IIS)]** を選択します。
8. 証明書と秘密キーがインポートされます。
9. ブラウザー タブを閉じて、メイン タブに戻ります。

    [Image: [インポートの種類]、[証明書とキー名]、[パスワード] の入力のスクリーンショット。]
10. **[暗号化アサーションを有効にする]** をオンにします。
11. 暗号化を有効にしている場合は、**[アサーション解読秘密キー]** の一覧から、Microsoft Entra アサーションの暗号化解除に BIG-IP APM で使用される証明書の秘密キーを選択します。
12. 暗号化を有効にしている場合は、**[アサーション解読の証明書]** の一覧から、発行された SAML アサーションを暗号化するために BIG-IP でMicrosoft Entra ID にアップロードされた証明書を選択します。

    [Image: [アサーション解読秘密キー] と [アサーション解読の証明書] で選択された証明書のスクリーンショット。]

#### Microsoft Entra ID

Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP 用のアプリケーション テンプレートと、汎用の SHA テンプレートが用意されています。 次のスクリーンショットは、[Azure の構成] の下にある [Oracle E-Business Suite] オプションです。

1. **[Oracle E-Business Suite]** を選択します。
2. **追加**を選択します。

##### Azure の構成

1. BIG-IP によって Microsoft Entra テナント内に作成されるアプリと、MyApps のアイコンの **[表示名]** を入力します。
2. **[サインオン URL (省略可能)]** に、EBS アプリケーションのパブリック FQDN を入力します。
3. Oracle EBS ホームページの既定のパスを入力します。
4. **[署名キー]** と **[署名証明書]** の横にある**更新**アイコンを選択します。
5. インポートした証明書を見つけます。
6. **[署名キーのパスフレーズ]** に証明書のパスワードを入力します。
7. (省略可能) **[署名オプション]** を有効にします。 このオプションにより、BIG-IP は Microsoft Entra ID によって署名されたトークンとクレームを受け入れます。

    [Image: [署名キー]、[署名証明書]、[署名キーのパスフレーズ] のオプションとエントリのスクリーンショット。]
8. **[ユーザーとユーザー グループ]** には、テストに使うユーザーまたはグループを追加します。そうしないと、すべてのアクセスが拒否されます。 ユーザーとユーザー グループは、Microsoft Entra テナントから動的に照会され、アプリケーションへのアクセスを承認するために使用されます。

    [Image: [ユーザーとユーザー グループ] の [追加] オプションのスクリーンショット。]

##### ユーザー属性と要求

ユーザーが認証されると、Microsoft Entra ID は、ユーザーを識別する既定の要求と属性を使用して SAML トークンを発行します。 **[ユーザー属性と要求]** タブには、新しいアプリケーションのために発行する既定の要求が表示されます。 さらに要求を構成するには、この領域を使用します。 必要に応じて Microsoft Entra 属性を追加しますが、Oracle EBS シナリオでは既定の属性が必要です。

[Image: ユーザー属性と要求のオプションとエントリのスクリーンショット。]

##### 追加のユーザー属性

**[追加のユーザー属性]** タブでは、セッション拡張用のディレクトリに格納されている属性を必要とする分散システムがサポートされます。 LDAP ソースからフェッチされた属性を追加の SSO ヘッダーとして挿入して、ロール、パートナー ID などに基づいてアクセスを制御します。

1. **[詳細設定]** オプションを有効にします。
2. **[LDAP 属性]** チェック ボックスをオンにします。
3. **[認証サーバーの選択]** で **[新規作成]** を選択します。
4. 実際の設定に応じて、ターゲット LDAP サービスのサーバー アドレスに **[Use pool](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/プールを使用)** または **[Direct](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/直接)** サーバー接続モードを選択します。 単一の LDAP サーバーの場合は、**[Direct](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/直接)** を選択します。
5. **[サービス ポート]** には、「**3060**」 (既定値)、「**3161**」 (セキュア)、または Oracle LDAP サービス用の別のポートを入力します。
6. **[基本検索 DN]** を入力します。 識別名 (DN) を使用して、ディレクトリ内のグループを検索します。
7. **[管理者の DN]** には、APM が LDAP クエリの認証に使用するアカウント識別名を入力します。
8. **[管理パスワード]** には、パスワードを入力します。

    [Image: 追加のユーザー属性のオプションとエントリのスクリーンショット。]
9. 既定の **[LDAP Schema Attributes](LDAP スキーマ属性)** をそのまま使用します。

    [Image: LDAP スキーマ属性のスクリーンショット]
10. **[LDAP クエリのプロパティ]** の **[検索 DN]** に、ユーザー オブジェクト検索用の LDAP サーバー ベース ノードを入力します。
11. **[必須属性]** には、LDAP ディレクトリから返されるユーザー オブジェクト属性名を入力します。 EBS の場合、既定値は **orclguid** です。

    [Image: LDAP クエリ プロパティのエントリとオプションのスクリーンショット]

##### 条件付きアクセス ポリシー

条件付きアクセスのポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御します。 ポリシーは、Microsoft Entra の事前認証後に適用されます。 [使用可能なポリシー] ビューには、ユーザー アクションのない条件付きアクセス ポリシーがあります。 [選択されたポリシー] ビューには、クラウド アプリのポリシーがあります。 これらのポリシーは、テナント レベルで適用されるため、選択解除することも、[使用可能なポリシー] に移動することもできません。

公開されるアプリケーションのポリシーを選択するには、以下の手順を実行します。

1. **[使用可能なポリシー]** でポリシーを選択します。
2. **右矢印**を選択します。
3. ポリシーを **[選択されたポリシー]** に移動します。

    Note

    一部のポリシーでは、**[含める]** または **[除外する]** オプションが選択されています。 両方のオプションがオンになっている場合、そのポリシーは適用されません。

    [Image: 4 つのポリシーで [除外する] オプションが選択されているスクリーンショット。]

    Note

    **[条件付きアクセス ポリシー]** タブを選択すると、ポリシーの一覧が表示されます。 **[最新の情報に更新]** を選択すると、ウィザードによってテナントが照会されます。 デプロイされたアプリケーションの更新が表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは BIG-IP データ プレーン オブジェクトであり、アプリケーション クライアント要求をリッスンする仮想 IP アドレスで表されます。 受信されたトラフィックは処理され、仮想サーバーに関連する APM プロファイルに対して評価されます。 その後、トラフィックはポリシーに従って送信されます。

1. **[Destination Address] (宛先アドレス)** に、BIG-IP でクライアント トラフィックの受信に使用する IPv4 または IPv6 アドレスを入力します。 クライアントが BIG-IP で発行済みのアプリケーションの外部 URL をこの IP に解決できるようにする、DNS 内の対応するレコードを確保してください。 テストのために、テスト コンピューターの localhost DNS を使用します。
2. **[Service Port] (サービス ポート)** には「**443**」と入力し、**[HTTPS]** を選びます。
3. **[リダイレクト ポートを有効にする]** を選択します。
4. **[リダイレクト ポート]** には「**80**」と入力し、**[HTTP]** を選択します。 このアクションにより、受信 HTTP クライアント トラフィックが HTTPS にリダイレクトされます。
5. 作成した**クライアント SSL プロファイル**を選びます。または、テストの場合には既定値のままにします。 クライアント SSL プロファイルを使用すると、HTTPS 用の仮想サーバーが有効になります。 クライアント接続は TLS 経由で暗号化されます。

    [Image: 仮想サーバーのプロパティのオプションと選択のスクリーンショット。]

#### プールのプロパティ

**[Application Pool] (アプリケーション プール)** タブには、BIG-IP の背後にあるサービスがあります。これは、1 つ以上のアプリケーション サーバーが含まれるプールです。

1. **[Select a Pool] (プールの選択)** から、**[Create New] (新規作成)** を選ぶか、別のオプションを選びます。
2. **[Load Balancing Method] (負荷分散方法)** では、**[Round Robin] (ラウンド ロビン)** を選びます。
3. **[プール サーバー]** で、Oracle EBS をホストするサーバーの **[IP Address/Node Name] (IP アドレスとノード名)** と **[ポート]** を選択して入力します。
4. [HTTPS] を選択します。

    [Image: プールのプロパティのオプションと選択のスクリーンショット]
5. **[アクセス ゲート プール]** で、**[アクセス ゲート サブパス]** を確認します。
6. **[プール サーバー]** で、Oracle EBS をホストするサーバーの **[IP Address/Node Name] (IP アドレスとノード名)** と **[ポート]** を選択して入力します。
7. [HTTPS] を選択します。

    [Image: [アクセス ゲート プール] のオプションとエントリのスクリーンショット。]

##### シングル サインオン & HTTP ヘッダー

Easy Button ウィザードでは、公開済みアプリケーションへの SSO のために、Kerberos、OAuth Bearer、HTTP 承認ヘッダーがサポートされています。 Oracle EBS アプリケーションではヘッダーが必要であるため、HTTP ヘッダーを有効にします。

1. **[シングル サインオンと HTTP ヘッダー]** で、**[HTTP ヘッダー]** を選択します。
2. **[ヘッダー操作]** で、**[replace] (置換)** を選びます。
3. **[ヘッダー名]** に「**USER\_NAME**」と入力します。
4. **[ヘッダー値]** に「**%{session.sso.token.last.username}**」と入力します。
5. **[ヘッダー操作]** で、**[replace] (置換)** を選びます。
6. **[ヘッダー名]** に「**USER\_ORCLGUID**」と入力します。
7. **[ヘッダー値]** に「**%{session.ldap.last.attr.orclguid}**」と入力します。

    [Image: [ヘッダー操作]、[ヘッダー名]、[ヘッダー値] のエントリと選択のスクリーンショット。]

    Note

    中かっこ内の APM セッション変数では、大文字と小文字が区別されます。

#### セッションの管理

BIG-IP セッション管理を使用して、ユーザー セッションの終了または継続の条件を定義します。

詳細については、support.f5.com にアクセスして、「[K18390492: セキュリティ | BIG-IP APM 操作ガイド](https://support.f5.com/csp/article/K18390492)」を参照してください

シングル ログアウト (SLO) 機能を使用すると、IdP、BIG-IP、ユーザー エージェント間のセッションがユーザーのサインアウト時に終了するようにできます。Easy Button によって Microsoft Entra テナントに SAML アプリケーションがインスタンス化されると、ログアウト URL に APM SLO エンドポイントが設定されます。 そのため、マイ アプリ ポータルからの IdP によって開始されるサインアウトでも、BIG-IP とクライアント間のセッションが終了します。

Microsoft [マイ アプリ](https://myapplications.microsoft.com/)を参照してください

発行済みアプリケーションの SAML フェデレーション メタデータがテナントからインポートされます。 このアクションによって、Microsoft Entra ID の SAML サインアウト エンドポイントが APM に提供されます。 その後、SP によって開始されるサインアウトによって、クライアントと Microsoft Entra のセッションが終了します。 ユーザーがいつサインアウトしたかを APM が認識していることを確認します。

BIG-IP Web トップ ポータルを使用して発行済みアプリケーションにアクセスする場合は、APM によってサインアウトが処理され、Microsoft Entra サインアウト エンドポイントが呼び出されます。 BIG-IP Web トップ ポータルを使用しない場合、ユーザーはサインアウトするよう APM に指示できません。ユーザーがアプリケーションからサインアウトした場合、BIG-IP ではアクションが認識されません。 SP によって開始されたサインアウトによって、セキュリティで保護されたセッションの終了がトリガーされることを確認します。 SLO 関数をアプリケーションの **[サインアウト]** ボタンに追加して、クライアントを Microsoft Entra SAML または BIG-IP サインアウト エンドポイントにリダイレクトします。 テナントの SAML サインアウト エンドポイントの URL を、**[アプリの登録] &gt; [エンドポイント]** で探します。

アプリを変更できない場合は、BIG-IP でアプリケーションのサインアウト呼び出しをリッスンして SLO をトリガーします。

詳細情報:

- [PeopleSoft SLO ログアウト](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button#peoplesoft-single-logout)
- support.f5.com にアクセスして、以下を参照してください。
    - [K42052145: URI 参照ファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145)
    - [K12056: ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)

### 展開

1. 設定をコミットするには、**[Deploy] (デプロイ)** を選びます。
2. テナントの [エンタープライズ アプリケーション] の一覧にアプリケーションが表示されていることを確認します。

### テスト

1. ブラウザーから、Oracle EBS アプリケーションの外部 URL に接続するか、[マイ アプリ](https://myapps.microsoft.com/)のアプリケーションのアイコンを選択します。
2. Microsoft Entra ID に対して認証します。
3. アプリケーションの BIG-IP 仮想サーバーにリダイレクトされ、SSO でサインインされます。

セキュリティを向上させるには、アプリケーションへの直接アクセスをブロックして、BIG-IP を介したパスを強制します。

### 詳細なデプロイ

ガイド付き構成テンプレートに要件の柔軟性が欠けている場合があります。

詳細情報: [チュートリアル: ヘッダーベースの SSO 用に F5 BIG-IP の Access Policy Manager を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-header-advanced)。

#### 構成を手動で変更する

別の方法として、BIG-IP でガイド付き構成の厳格な管理モードを無効にして、構成を手動で変更します。 ウィザード テンプレートによって、ほとんどの構成が自動化されます。

1. **[アクセス] &gt; [ガイド付き構成]** に移動します。
2. アプリケーション構成の行の右端で、**南京錠**アイコンを選択します。

    [Image: 南京錠アイコンのスクリーンショット]

厳格モードを無効にした後は、ウィザードで変更することはできません。 ただし、発行済みのアプリ インスタンスに関連する BIG-IP オブジェクトは、管理のためにロック解除されます。

Note

厳格モードを再度有効にすると、新しい構成によって、ガイド付き構成なしで実行された設定が上書きされます。 運用サービスに対しては詳細構成の方式をお勧めします。

### トラブルシューティング

次の手順を実行すると、問題のトラブルシューティングに役立ちます。

#### ログの詳細度を上げる

BIG-IP のログを使用して、接続、SSO、ポリシー違反、正しく構成されていない変数マッピングなどの問題を分離します。 ログの冗長レベルを上げます。

1. **[Access Policy] (アクセス ポリシー) &gt; [Overview] (概要) &gt; [Event Logs] (イベント ログ)** に移動します。
2. **設定**を選択します。
3. 発行されたアプリケーションの行を選択します。
4. **[編集] &gt; [システム ログへのアクセス]** を選択します。
5. SSO の一覧で、**[デバッグ]** を選択します。
6. **OK** を選択します。
7. 問題を再現します。
8. ログを調べます。

詳細モードでは過剰なデータが生成されるため、設定の変更を元に戻します。

#### BIG-IP のエラー メッセージ

Microsoft Entra 事前認証後に BIG-IP のエラーが表示される場合、Microsoft Entra ID と BIG-IP の SSO に関する問題が発生している可能性があります。

1. [\*\*アクセス] &gt; [概要] に移動します。
2. **[Access reports] (レポートへのアクセス)** を選びます。
3. 直近 1 時間のレポートを実行します。
4. ログに手がかりがないか確認します。

セッションの **[セッションの表示]** リンクを使用して、APM が予想される Microsoft Entra 要求を受信したことを確認します。

#### BIG-IP エラー メッセージなし

BIG-IP エラー ページが表示されない場合、問題はバックエンド要求、または BIG-IP とアプリケーションの SSO に関連している可能性があります。

1. **[Access Policy] (アクセス ポリシー) &gt; [Overview] (概要)** に移動します。
2. **[アクティブ セッション]** を選びます。
3. アクティブなセッションのリンクを選択します。

**[変数の表示]** リンクを使用すると、特に BIG-IP APM で Microsoft Entra ID または別のソースから適切な属性を取得できない場合に、SSO の問題を調査できます。

詳細情報:

- devcentral.f5.com にアクセスして、「[APM 変数の割り当ての例](https://devcentral.f5.com/s/articles/apm-variable-assign-examples-1107)」を参照してください
- techdocs.f5.com にアクセスして、[手動チャプター: セッション変数](https://techdocs.f5.com/en-us/bigip-15-1-0/big-ip-access-policy-manager-visual-policy-editor/session-variables.html)に関する記事を参照してください

#### APM サービス アカウントを検証する

次の bash シェル コマンドを使用して、LDAP クエリの APM サービス アカウントを検証します。 このコマンドによって、ユーザー オブジェクトの認証とクエリが実行されます。

`ldapsearch -xLLL -H 'ldap://192.168.0.58' -b "CN=oraclef5,dc=contoso,dc=lds" -s sub -D "CN=f5-apm,CN=partners,DC=contoso,DC=lds" -w 'P@55w0rd!' "(cn=testuser)"`

詳細情報:

- support.f5.com にアクセスして、「[K11072: AD 向けの LDAP リモート認証の構成](https://support.f5.com/csp/article/K11072)」を参照してください
- techdocs.f5.com にアクセスして、「[手動チャプター: LDAP クエリ](https://techdocs.f5.com/en-us/bigip-16-1-0/big-ip-access-policy-manager-authentication-methods/ldap-query.html)」を参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-oracle-jde-easy-button"} -->
## Oracle JDE への SSO 用に F5 BIG-IP Easy Button を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-jde-easy-button
- Service: entra-id / enterprise-apps
- Article date: 2023-05-03
- Summary: F5 BIG-IP Easy Button Guided Configuration 16.1 を使用して、Oracle JD Edwards へのヘッダーベース SSO によるセキュリティで保護されたハイブリッド アクセスを実装します。

このチュートリアルでは、F5 BIG-IP Easy Button ガイド付き構成で、Microsoft Entra ID を使用して Oracle JD Edwards (JDE) を保護する方法について学習します。

BIG-IP と Microsoft Entra ID の統合には、次のようなさまざまな利点があります。

- Microsoft Entra 事前認証と条件付きアクセスによるゼロ トラスト ガバナンスの改善
    - [リモート作業を有効にするゼロ トラスト フレームワークを](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)参照してください
    - 「[条件付きアクセスとは」](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を参照してください。
- Microsoft Entra ID と BIG-IP 公開サービスの間のシングル サインオン (SSO)
- [Microsoft Entra 管理センター](https://entra.microsoft.com)から ID とアクセスを管理する

詳細情報:

- [F5 BIG-IP と Microsoft Entra ID の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- [エンタープライズ アプリケーションのシングル サインオンを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)

### シナリオの説明

このチュートリアルでは、保護されたコンテンツへのアクセスを管理するために HTTP 承認ヘッダーを使用する Oracle JDE アプリケーションを使用します。

レガシ アプリケーションには、Microsoft Entra 統合をサポートするための最新のプロトコルがありません。 最新化にはコストがかかり、計画が必要になり、潜在的なダウンタイム リスクが発生します。 代わりに、プロトコルを切り換えることで、F5 BIG-IP Application Delivery Controller (ADC) を使用してレガシ アプリケーションと最新の ID コントロールとの間のギャップを埋めます。

アプリの手前に BIG-IP を配置すると、Microsoft Entra の事前認証とヘッダーベースの SSO がサービスにオーバーレイされます。 このアクションにより、アプリケーションのセキュリティ態勢が向上します。

### シナリオのアーキテクチャ

このシナリオの SHA ソリューションは次のいくつかのコンポーネントで構成されています。

- **Oracle JDE アプリケーション** - Microsoft Entra SHA によって保護された公開サービス BIG-IP
- Microsoft Entra ID - ユーザーの資格情報を検証し、条件付きアクセスや SAM に基づくシングル サインオンを実現するセキュリティ アサーション マークアップ言語 (SAML) のアイデンティティ プロバイダー (IdP) BIG-IP
    - SSO を使用して、Microsoft Entra ID は BIG-IP にセッション属性を提供する
- **BIG-IP**- アプリケーションへのリバース プロキシと SAML サービス プロバイダー (SP)
    - BIG-IP では SAML IdP に認証を委任した後、Oracle サービスに対するヘッダーベースの SSO を実行します

このチュートリアルでは、SHA は SP と IdP によって開始される各フローをサポートします。 次の図は、SP によって開始されるフローを示しています。

[Image: SP によって開始されるフローを使用したセキュリティで保護されたハイブリッド アクセスの図。]

1. ユーザーがアプリケーション エンドポイント (BIG-IP) に接続します。
2. BIG-IP APM アクセス ポリシーにより、ユーザーが Microsoft Entra ID (SAML IdP) にリダイレクトされます。
3. Microsoft Entra がユーザーを事前認証し、条件付きアクセスのポリシーを適用します。
4. ユーザーは BIG-IP (SAML SP) にリダイレクトされます。 発行された SAML トークンを使用した SSO が発生します。
5. BIG-IP によって、Microsoft Entra 属性がアプリケーション要求のヘッダーとして挿入される。
6. アプリケーションが要求を承認し、ペイロードを返します。

### 前提条件

- Microsoft Entra ID 無料アカウント以上
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn/)を入手してください
- BIG-IP、または Azure の BIG-IP Virtual Edition (VE)
    - [Azure での F5 BIG-IP Virtual Edition VM のデプロイに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)参照してください
- 次のいずれかの F5 BIG-IP ライセンス:
    - F5 BIG-IP® ベストバンドル
    - F5 BIG-IP APM スタンドアロン ライセンス
    - 既存の BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP APM アドオン ライセンス
    - 90 日間 BIG-IP 完全な機能 [試用版ライセンス](https://www.f5.com/trial/big-ip-trial.php)
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID、または Microsoft Entra ID 内で作成されてオンプレミス ディレクトリに戻されたユーザー ID
    - [Microsoft Entra Connect Sync を参照: 同期を理解してカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)する
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者
- HTTPS を介してサービスを発行するための SSL Web 証明書。または、テストの場合には既定の BIG-IP 証明書を使用します
    - [Azure での F5 BIG-IP Virtual Edition VM のデプロイに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)参照してください
- Oracle JDE 環境

### BIG-IP の構成

このチュートリアルでは、Easy Button テンプレートを備えたガイド付き構成 16.1 を使用します。 Easy Button を使用すると、管理者は SHA のサービスを有効にするために Microsoft Entra ID と BIG-IP の間を行き来することがなくなります。 APM のガイド付き構成ウィザードと Microsoft Graph によって、デプロイとポリシー管理が処理されます。 統合により、アプリケーションで ID フェデレーション、SSO、条件付きアクセスが確実にサポートされます。

注

このチュートリアルの文字列または値の例は、実際の環境のものに置き換えてください。

### Easy Button を登録する

クライアントまたはサービスは、Microsoft Graph にアクセスする前に、Microsoft ID プラットフォームによって信頼される必要があります。

詳細情報: [クイック スタート: アプリケーションを Microsoft ID プラットフォームに登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)

次の手順は、Graph への Easy Button アクセスを承認するためのテナント アプリ登録を作成する際に役立ちます。 BIG-IP はこの権限を使って、発行済みアプリケーションの SAML SP インスタンスと SAML IdP である Microsoft Entra ID の間に信頼を確立するための構成をプッシュします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**アプリ登録**&gt;に移動し、**新規登録**を選択します。
3. アプリケーション名を入力 **します**。
4. **この組織ディレクトリのアカウントの場合のみ**、アプリケーションを使用するユーザーを指定します。
5. [ **登録**] を選択します。
6. **API のアクセス許可**に移動します。
7. 次の Microsoft Graph **アプリケーションのアクセス許可を**承認します。

    - Application.ReadWrite.All
    - Application.ReadWrite.OwnedBy
    - Directory.Read.All (ディレクトリのすべてを読む)
    - グループ.リード.オール
    - IdentityRiskyUser.Read.All（アイデンティティリスキーユーザー.リード.オール）
    - Policy.Read.All
    - ポリシー.リードライト.アプリケーション構成
    - Policy.ReadWrite.ConditionalAccess（ポリシーの読み書き条件付きアクセス）
    - User.Read.All（ユーザー全体の読み取り）
8. 組織に対して管理者の同意を付与します。
9. **[証明書] と [シークレット**] に移動します。
10. 新しい **クライアント シークレット** を生成してメモします。
11. **[概要**] に移動し、**クライアント ID** と**テナント ID を**書き留めます

### Easy Button を構成する

1. APM Guided Configuration (ガイド付き構成) を開始します。
2. Easy Button テンプレートを起動します。
3. **Access &gt; ガイド付き構成**へ移動してください。
4. **[Microsoft 統合**] を選択します。
5. **Microsoft Entra アプリケーションを選択します**。
6. 構成シーケンスを確認します。
7. **[次へ**] を選択する
8. 構成シーケンスに従います。

    [Image: [Microsoft Entra Application Configuration](Microsoft Entra アプリケーションの構成) の下の構成シーケンスのスクリーンショット。]

#### 構成プロパティ

**[構成プロパティ**] タブを使用して、新しいアプリケーション構成と SSO オブジェクトを作成します。 **[Azure サービス アカウントの詳細]** セクションは、アプリケーションとして Microsoft Entra テナントに登録したクライアントを表します。 BIG-IP OAuth クライアントの設定を使用して、テナントに SSO プロパティと共に SAML SP を登録します。 Easy Button により、発行されて SHA が有効になっている BIG-IP サービスに対してこのアクションが実行されます。

注

次の一部の設定はグローバルです。 これらを再利用して、より多くのアプリケーションを公開できます。

1. **[シングル Sign-On (SSO) と HTTP ヘッダー] で** **、[オン]** を選択します。
2. 指定した **テナント ID、クライアント ID**、クライアント **シークレット** を入力します。
3. テナントに BIG-IP が接続されたことを確認します。
4. **[次へ**] を選択する

#### サービス プロバイダー

サービス\* プロバイダー\*設定\*では、SHA によって保護されるアプリケーション\*の SAML SP インスタンス\*のプロパティを定義します。

1. **[ホスト**] に、セキュリティで保護されたアプリケーションのパブリック FQDN を入力します。
2. **エンティティ ID** には、トークンを要求する SAML SP を識別するために Microsoft Entra ID が使用する識別子を入力します。

    [Image: サービス プロバイダーのオプションと選択のスクリーンショット。]
3. (省略可能) **[セキュリティ設定]** で、発行された SAML アサーションを Microsoft Entra ID で暗号化します。 このオプションを使用すると、コンテンツ トークンが傍受されることも、データが侵害されることもないという保証が高くなります。
4. **[Assertion Decryption 秘密キー**] ボックスの一覧で、[**新規作成**] を選択します。

    [Image: [Assertion Decryption 秘密キー] リストの [新規作成] のスクリーンショット。]
5. [ **OK] を選択します**。
6. [ **SSL 証明書とキーのインポート** ] ダイアログが新しいタブに表示されます。
7. **[インポートの種類]** で、[**PKCS 12 (IIS)]** を選択します。 このオプションにより、証明書と秘密キーがインポートされます。
8. ブラウザー タブを閉じて、メイン タブに戻ります。

    [Image: SSL 証明書とキー ソースのオプションと選択のスクリーンショット。]
9. [ **暗号化されたアサーションを有効にする]** で、チェック ボックスをオンにします。
10. 暗号化を有効にした場合は、 **Assertion Decryption 秘密キー** の一覧から証明書を選択します。 この秘密キーは証明書のためのもので、Microsoft Entra アサーションを解読するために BIG-IP APM が使用します。
11. 暗号化を有効にした場合は、[ **Assertion Decryption Certificate]\(アサーション復号化証明書** \) の一覧から証明書を選択します。 BIG-IP は、発行された SAML アサーションを暗号化するために、この証明書を Microsoft Entra ID にアップロードします。

[Image: [セキュリティ設定] のオプションと選択のスクリーンショット。]

#### Microsoft Entra ID

Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP 用のテンプレートと、汎用の SHA テンプレートが用意されています。

1. **F5 BIG-IP で保護された JD Edwards** を選択します。
2. **追加**を選択します。

##### Azure の構成

1. テナントで BIG-IP が作成したアプリの**表示名**を入力します。 マイ [アプリ](https://myapplications.microsoft.com/)のアイコンに名前が表示されます。
2. (省略可能) **[サインオン URL] に** PeopleSoft アプリケーションのパブリック FQDN を入力します。
3. **署名キー**と**署名証明書**の横にある **[更新**] を選択します。 このアクションによって、インポートした証明書が検索されます。
4. **[署名キーパスフレーズ**] に、証明書パスワードを入力します。
5. (省略可能) **[署名オプション**] で、オプションを選択します。 この選択により、Microsoft Entra ID によって署名されたトークンとクレームを BIG-IP が受け入れるようになります。

    [Image: [SAML 署名証明書] の [署名キー]、[署名証明書]、および [署名キー Passprhase] オプションのスクリーンショット。]
6. **ユーザーおよびユーザーグループ** は、Microsoft Entra テナントから動的に照会されます。
7. テストに使用するユーザーまたはグループを追加します。そうしないと、アクセスが拒否されます。

    [Image: [ユーザーとユーザー グループ] の [追加] オプションのスクリーンショット。]

##### ユーザー属性とクレーム

ユーザーが認証されると、Microsoft Entra ID は、ユーザーを識別する既定のクレームと属性を使用して SAML トークンを発行します。 **[ユーザー属性と要求**] タブには、新しいアプリケーションに対して発行する既定の要求があります。 それを使用して、より多くの要求を構成します。

[Image: ユーザー属性と要求のオプションと選択のスクリーンショット。]

必要に応じて、他の Microsoft Entra 属性を含めます。 Oracle JDE のシナリオでは、既定の属性が必要です。

##### 追加のユーザー属性

[ **追加のユーザー属性** ] タブでは、セッション拡張のために属性を他のディレクトリに格納する必要がある分散システムがサポートされます。 LDAP ソースからの属性を追加の SSO ヘッダーとして挿入して、ロール、パートナー ID などに基づいてアクセスを制御します。

注

この機能には、Microsoft Entra ID との相関関係はありません。別の属性ソースです。

##### 条件付きアクセス ポリシー

条件付きアクセス ポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御するために、Microsoft Entra の事前認証後に適用されます。 **[使用可能なポリシー]** ビューには、ユーザー アクションのない条件付きアクセス ポリシーがあります。 **[選択したポリシー**] ビューには、クラウド アプリを対象とするポリシーがあります。 これらのポリシーはテナント レベルで適用されるため、選択を解除したり、[使用可能なポリシー] リストに移動できません。

アプリケーションに応じたポリシーを選択します。

1. [ **使用可能なポリシー** ] ボックスの一覧で、ポリシーを選択します。
2. **右矢印**を選択し、ポリシーを **[選択したポリシー]** に移動します。

選択したポリシーの **[含める** ] または **[除外]** オプションがオンになっています。 両方のオプションをオンにすると、ポリシーは適用されません。

[Image: [条件付きアクセス ポリシー] タブの [選択したポリシー] にある除外されたポリシーのスクリーンショット。]

注

タブを選択すると、ポリシーの一覧が 1 回表示されます。ウィザードの **[更新]** を使用してテナントに対してクエリを実行します。 このボタンは、アプリケーションのデプロイ後に表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは、仮想 IP アドレスで表される BIG-IP データ プレーン オブジェクトです。 サーバーは、アプリケーションへのクライアント要求をリッスンします。 受信したトラフィックは処理され、仮想サーバー APM プロファイルに対して評価されます。 その後、トラフィックはポリシーに従って送信されます。

1. [ **宛先アドレス]** に、クライアント トラフィックの受信に使用 BIG-IP IPv4 または IPv6 アドレスを入力します。 対応するレコードが DNS に表示され、クライアントは公開されたアプリケーションの外部 URL を IP に解決できます。 テストのために、テスト コンピューターの localhost DNS を使用します。
2. **[サービス ポート]** に「**443**」と入力し、[**HTTPS**] を選択します。
3. [ **Enable Redirect Port]\(リダイレクト ポートを有効にする\)** で、チェック ボックスをオンにします。
4. **[リダイレクト ポート]** に「**80**」と入力し、[**HTTP**] を選択します。 このオプションにより、受信 HTTP クライアント トラフィックが HTTPS にリダイレクトされます。
5. **[クライアント SSL プロファイル] で**、[**既存のものを使用**] を選択します。
6. [ **共通]** で、作成したオプションを選択します。 テストする場合は既定値のままにします。 [クライアント SSL プロファイル] は仮想サーバーの HTTPS を有効にします。そのため、クライアント接続は TLS 経由で暗号化されます。

    [Image: 仮想サーバーのプロパティのオプションと選択のスクリーンショット。]

#### プールの特性

[ **アプリケーション プール** ] タブには、アプリケーション サーバーを含むプールとして表される BIG-IP の背後にサービスがあります。

1. [ **プールの選択**] で、[ **新規作成**] を選択するか、プールを選択します。
2. **[負荷分散方法**] で、[**ラウンド ロビン**] を選択します。
3. **プール サーバー**の場合は、[**IP アドレス/ノード名]** でノードを選択するか、Oracle JDE アプリケーションをホストするサーバーの IP とポートを入力します。

    [Image: プールのプロパティの IP アドレス/ノード名とポート オプションのスクリーンショット。]

##### シングル サインオンと HTTP ヘッダー

Easy Button ウィザードでは、公開済みアプリケーションへの SSO のために、Kerberos、OAuth Bearer、HTTP 承認ヘッダーがサポートされています。 PeopleSoft アプリケーションではヘッダーが必要になります。

1. **HTTP ヘッダーの**場合は、チェック ボックスをオンにします。
2. **[ヘッダー操作]** で、**[置換] を選択します**。
3. **[ヘッダー名]** に「JDE\_SSO\_UID」と入力**します**。
4. **[ヘッダー値]** ** に、「{session.sso.token.last.username}」%入力します**。

    [Image: [単一の Sign-On] と [HTTP ヘッダー] の下の [ヘッダー操作]、[ヘッダー名]、および [ヘッダー値] エントリのスクリーンショット。]

    注

    中かっこ内の APM セッション変数では、大文字と小文字が区別されます。 たとえば、OrclGUID と入力すると、属性名が orclguid の場合に属性マッピングのエラーが発生します。

#### セッションの管理

BIG-IP セッション管理設定を使用して、ユーザー セッションの終了または継続の条件を定義します。 ユーザーと IP アドレス、および対応するユーザー情報に制限を設定します。

詳細については、「K18390492[の support.f5.com: セキュリティ | BIG-IP APM 操作ガイド](https://support.f5.com/csp/article/K18390492)」を参照してください。

運用ガイドでは、ユーザーがサインアウトしたときに IdP、BIG-IP、およびユーザー エージェント セッションが確実に終了するシングル ログアウト (SLO) 機能については説明されていません。Easy Button によって Microsoft Entra テナントに SAML アプリケーションがインスタンス化されると、ログアウト URL に APM SLO エンドポイントが入力されます。 [マイ アプリ](https://myapplications.microsoft.com/)からの IdP によって開始されるサインアウトは、BIG-IP セッションとクライアント セッションを終了します。

公開されたアプリケーションの SAML フェデレーション データは、テナントからインポートされます。 このアクションにより、APM に Microsoft Entra ID の SAML サインアウト エンドポイントが提示され、SP によって開始されるサインアウトでクライアントと Microsoft Entra のセッションが終了します。 APM は、ユーザーがサインアウトするタイミングを把握する必要があります。

BIG-IP Web トップ ポータルから発行済みアプリケーションにアクセスすると、APM によってサインアウトが処理され、Microsoft Entra サインアウト エンドポイントが呼び出されます。 BIG-IP Web トップ ポータルを使用しない場合、ユーザーはサインアウトするよう APM に指示できません。ユーザーがアプリケーションからサインアウトすると、BIG-IP は無視されます。 SP によって開始されるサインアウトには、セキュリティで保護されたセッションの終了が必要になります。 SLO 関数をアプリケーションの **[サインアウト]** ボタンに追加して、クライアントを Microsoft Entra SAML のサインアウト エンドポイントまたは BIG-IP エンドポイントにリダイレクトします。 テナントの SAML サインアウト エンドポイント URL は、**[アプリの登録] &gt; [エンドポイント]** にあります。

アプリを変更できない場合は、BIG-IP にアプリケーションのサインアウト呼び出しをリッスンさせてから、SLO をトリガーすることを検討してください。

詳細情報: [チュートリアル: Oracle PeopleSoft、PeopleSoft シングル ログアウトへの SSO 用に F5 BIG-IP Easy Button を構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button#peoplesoft-single-logout)する

詳細については、support.f5.com にアクセスし、次のページを参照してください。

- [K42052145: URI で参照されるファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145)
- [K12056: ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)。

### デプロイ

1. [ **デプロイ] を選択します**。
2. アプリケーションがエンタープライズ アプリケーションのテナント リストにあることを確認します。

### 構成の確認

1. ブラウザーを使用して、Oracle JDE アプリケーションの外部 URL に接続するか、[ [マイ アプリ](https://myapps.microsoft.com/)] でアプリケーション アイコンを選択します。
2. Microsoft Entra ID に対して認証を行います。
3. アプリケーションの BIG-IP 仮想サーバーにリダイレクトされ、SSO によってサインインされます。

    注

    アプリケーションへの直接アクセスをブロックできます。これにより BIG-IP を介したパスが強制されます。

### 詳細なデプロイ

ガイド付き構成テンプレートは、柔軟性に欠けている場合があります。

詳細情報: [チュートリアル: ヘッダーベースの SSO 用に F5 BIG-IP Access Policy Manager を構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-header-advanced)する

または、BIG-IP でガイド付き構成の厳格な管理モードを無効にします。 構成は手動で変更できますが、ほとんどの構成はウィザード テンプレートで自動化されています。

1. **Access &gt; ガイド付き構成**へ移動してください。
2. 行の末尾で、 **南京錠**を選択します。

    [Image: 南京錠アイコンのスクリーンショット。]

ウィザード UI での変更はできませんが、アプリケーションの公開インスタンスに関連付けられている BIG-IP オブジェクトは管理のためにロックが解除されます。

注

厳密モードを再度有効にして構成をデプロイすると、ガイド付き構成以外で行われた設定が上書きされます。 運用サービスの場合には高度な構成をお勧めします。

### トラブルシューティング

BIG-IP のログを使用して、接続、SSO、ポリシー違反、正しく構成されていない変数マッピングなどの問題を分離します。

#### ログの詳細

1. **アクセス ポリシー &gt;概要**に移動します。
2. **[イベント ログ]** を選択します。
3. [ **設定] を選択します**。
4. 発行されたアプリケーションの行を選択します。
5. [**編集] を選択します**。
6. **[アクセス システム ログ]** を選択する
7. SSO の一覧から [ **デバッグ**] を選択します。
8. [ **OK] を選択します**。
9. 問題を再現する。
10. ログを調べます。

詳細モードでは大量のデータが生成されるため、完了後に、この機能を元に戻します。

#### BIG-IP のエラー メッセージ

Microsoft Entra の事前認証後に BIG-IP のエラーが表示される場合は、この問題が Microsoft Entra ID から BIG-IP の SSO に関連している可能性があります。

1. **[Access &gt; Overview**] に移動します。
2. [ **レポートへのアクセス**] を選択します。
3. 直近 1 時間のレポートを実行します。
4. ログに手がかりがないか確認します。

セッションの **[セッションの表示]** リンクを使用して、APM が予想される Microsoft Entra 要求を受信することを確認します。

#### BIG-IP エラー メッセージなし

BIG-IP エラー メッセージが表示されない場合、その問題はバックエンド要求、または BIG-IP からアプリケーションへの SSO に関連している可能性があります。

1. **アクセス ポリシー &gt;概要**に移動します。
2. **[アクティブなセッション] を選択します**。
3. アクティブなセッション リンクを選びます。

[ **変数の表示** ] リンクを使用して、SSO の問題を特定します(特に、BIG-IP APM がセッション変数から正しくない属性を取得する場合)。

詳細情報:

- [APM 変数の割り当て例](https://devcentral.f5.com/s/articles/apm-variable-assign-examples-1107)の devcentral.f5.com に移動する
- [セッション変数](https://techdocs.f5.com/en-us/bigip-15-1-0/big-ip-access-policy-manager-visual-policy-editor/session-variables.html)の techdocs.f5.com に移動する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button"} -->
## Oracle PeopleSoft への SSO 用に F5 の BIG-IP Easy Button を構成する方法のチュートリアル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button
- Service: entra-id / enterprise-apps
- Article date: 2023-05-03
- Summary: F5 BIG-IP Easy Button ガイド付き構成 16.1 を使用して、PeopleSoft に対し、ヘッダーベース SSO によるセキュア ハイブリッド アクセスを実装します。

この記事では、F5 BIG-IP Easy Button ガイド付き構成 16.1 で、Microsoft Entra ID を使用して Oracle PeopleSoft (PeopleSoft) をセキュリティで保護する方法について説明します。

BIG-IP と Microsoft Entra ID を統合すると、次のような多くの利点があります。

- Microsoft Entra の事前認証および条件付きアクセスによるゼロ トラスト ガバナンスの強化
    - 「[リモート作業を可能にする Microsoft ゼロ トラスト フレームワーク](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)」を参照してください。
    - 「[条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)」を参照してください。
- Microsoft Entra ID と BIG-IP 公開済みサービス間のシングル サインオン (SSO)
- [Microsoft Entra 管理センター](https://entra.microsoft.com)からの ID とアクセスを管理する

詳細情報:

- [F5 BIG-IP を Microsoft Entra ID と統合する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- [エンタープライズ アプリケーションのシングル サインオンを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)

### シナリオの説明

このチュートリアルでは、保護されたコンテンツへのアクセスを管理するために HTTP Authorization ヘッダーを使用する PeopleSoft アプリケーションが使用されます。

レガシ アプリケーションには、Microsoft Entra 統合をサポートするための最新のプロトコルがありません。 最新化にはコストがかかり、計画が必要であり、ダウンタイムのリスクが伴います。 代わりに、プロトコルを切り換えて、F5 BIG-IP Application Delivery Controller (ADC) を使用して、レガシ アプリケーションと最新の ID コントロールとの間のギャップを埋めます。

アプリの手前に BIG-IP を配置すれば、Microsoft Entra の事前認証とヘッダーベース SSO でサービスをオーバーレイできます。 このアクションにより、アプリケーションのセキュリティ体制が向上します。

注

Microsoft Entra アプリケーション プロキシを使用すると、このタイプのアプリケーションにリモート アクセスできます。 「[Microsoft Entra アプリケーション プロキシからのオンプレミス アプリケーションへのリモート アクセス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)」を参照してください。

### シナリオのアーキテクチャ

このチュートリアルのためのセキュア ハイブリッド アクセス (SHA) ソリューションは、次のコンポーネントで構成されています。

- **PeopleSoft アプリケーション** - Microsoft Entra SHA によって保護された BIG-IP 公開サービス
- **Microsoft Entra ID**- Security Assertion Markup Language (SAML) ID プロバイダー (IdP)。これにより、ユーザーの資格情報、条件付きアクセス、BIG-IP への SAML ベースの SSO が検証されます
    - Microsoft Entra ID は、SSO を使用して BIG-IP にセッション属性を与えます
- **BIG-IP** - アプリケーションへのリバース プロキシおよび SAML サービス プロバイダー (SP)。 SAML IdP に認証を委任し、その後、PeopleSoft サービスに対してヘッダーベースの SSO を実行します。

このシナリオの場合、SHA では、SP と IdP によって開始される各フローがサポートされます。 次の図は、SP によって開始されるフローを示しています。

[Image: SP Initiated フローによるセキュア ハイブリッド アクセスについての図。]

1. ユーザーがアプリケーション エンドポイント (BIG-IP) に接続します。
2. BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトします。
3. Microsoft Entra がユーザーを事前認証し、条件付きアクセス ポリシーを適用します。
4. ユーザーは BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行されます。
5. BIG-IP によって、Microsoft Entra 属性がアプリケーションへの要求のヘッダーとして挿入されます。
6. アプリケーションが要求を承認し、ペイロードを返します。

### 前提条件

- Microsoft Entra ID 無料アカウント、またはそれ以上のアカウント
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn/)を取得してください。
- BIG-IP、または Azure の BIG-IP Virtual Edition (VE)
    - 「[F5 BIG-IP Virtual Edition VM を Azure にデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)」を参照してください
- 次のいずれかの F5 BIG-IP ライセンス:
    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP APM スタンドアロン ライセンス
    - 既存の BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP APM アドオン ライセンス
    - 90 日間の BIG-IP 全機能[試用版ライセンス](https://www.f5.com/trial/big-ip-trial.php)
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID、または Microsoft Entra ID 内で作成されてオンプレミス ディレクトリに戻されたユーザー ID
    - 「[Microsoft Entra Connect Sync: 同期について理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)」を参照してください
- クラウド アプリケーション管理者ロール、アプリケーション管理者ロールのいずれか。
- HTTPS を介してサービスを発行するための SSL Web 証明書。または、テストの場合には既定の BIG-IP 証明書を使用します
    - 「[F5 BIG-IP Virtual Edition VM を Azure にデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)」を参照してください
- PeopleSoft 環境

### BIG-IP の構成

このチュートリアルでは、Easy Button テンプレートを備えたガイド付き構成 16.1 を使用します。

Easy Button を使用すると、管理者は Microsoft Entra ID と BIG-IP の間を移動せずに SHA のサービスを有効化できます。 APM の [ガイド付き構成] ウィザードと Microsoft Graph でデプロイとポリシー管理を処理します。 統合により、アプリケーションでは ID フェデレーション、SSO、条件付きアクセスのサポートが確保されます。

注

このチュートリアルの文字列または値の例は、実際の環境のものに置き換えてください。

### Easy Button を登録する

クライアントまたはサービスは、Microsoft Graph にアクセスする前に、Microsoft ID プラットフォームによって信頼される必要があります。

詳細情報: [クイック スタート: Microsoft ID プラットフォームにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)

次の手順では、Easy Button が Graph へのアクセスを許可するためのテナント アプリ登録を作成する方法を説明します。 これらのアクセス許可を使用して、BIG-IP は、発行済みアプリケーションの SAML SP インスタンスと、SAML IdP となる Microsoft Entra ID の間に信頼を確立するための構成をプッシュします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**アプリ登録**&gt;に移動し、**新規登録**を選択します。
3. アプリケーションの**名前**を入力します。
4. **この組織のディレクトリ内のアカウントのみ**に対し、アプリケーションを使用するユーザーを指定します
5. **[登録]** を選択します。
6. **[API permissions] (API のアクセス許可)** に移動します。
7. 次の Microsoft Graph **アプリケーションのアクセス許可**を認可します。

    - Application.ReadWrite.All
    - Application.ReadWrite.OwnedBy
    - Directory.Read.All (ディレクトリのすべてを読む)
    - Group.Read.All
    - IdentityRiskyUser.Read.All（アイデンティティリスキーユーザー.リード.オール）
    - Policy.Read.All
    - ポリシー.読み取り書き込み.アプリケーション設定
    - Policy.ReadWrite.ConditionalAccess
    - User.Read.All
8. 組織に管理者の同意を付与します。
9. **[証明書とシークレット]** にアクセスします。
10. 新しい **[クライアント シークレット]** を生成してメモします。
11. **[概要]** に移動し、**クライアント ID** と **テナント ID** をメモします

### Easy Button を構成する

1. APM Guided Configuration (ガイド付き構成) を開始します。
2. Easy Button テンプレートを起動します。
3. **[アクセス] &gt; [ガイド付き構成]** に移動します。
4. **[Microsoft Integration] (Microsoft 統合)** を選択します。
5. **[Microsoft Entra アプリケーション]** を選択します。
6. 構成シーケンスを確認します。
7. **[次へ]** を選択します
8. 構成シーケンスに従います。

[Image: [Microsoft Entra Application Configuration] (Microsoft Entra アプリケーションの構成) の構成シーケンスのスクリーンショット。]

#### 構成プロパティ

**[構成プロパティ**] タブを使用して、新しいアプリケーション構成と SSO オブジェクトを作成します。 **[Azure サービス アカウントの詳細]** セクションは、アプリケーションとして、Microsoft Entra テナントに登録したクライアントを表すものとします。 BIG-IP OAuth クライアントの設定を使用して、SSO プロパティと共に SAML SP をテナントに登録します。 Easy Button により、発行されて SHA が有効になっている BIG-IP サービスに対してこのアクションが実行されます。

注

次の設定の一部はグローバルです。 その設定を再利用して、さらにアプリケーションを発行できます。

1. **構成名**を入力します。 一意の名前にすると、構成を区別しやすくなります。
2. **[Single Sign-On (SSO) & HTTP Headers] (シングル サインオン (SSO) と HTTP ヘッダー)** で、**[On] (オン)** を選びます。
3. **[テナント ID]** および **[クライアント ID]** に、メモした内容を入力します。
4. BIG-IP がテナントに接続されたことを確認します。
5. **[次へ]** を選択します。

#### サービス プロバイダー

**[サービスプロバイダー]** 設定を使用して、SHA で保護されたアプリケーションを表す APM インスタンスの SAML SP プロパティを定義します。

1. **[ホスト]** に、セキュリティで保護されるアプリケーションのパブリック FQDN を入力します。
2. **[エンティティ ID]** に、トークンを要求する SAML SP を識別するために Microsoft Entra によって使用される識別子を入力します。

[Image: [サービス プロバイダー] のオプションと選択内容のスクリーンショット。]

1. (オプション) **[セキュリティ設定]** では、発行された SAML アサーションの Microsoft Entra ID 暗号化を指定します。 このオプションを使用すると、より確実にコンテンツ トークンが傍受されないことと、データが漏洩しないことを確保できます。
2. **[Assertion Decryption Private Key] (アサーション解読秘密キー)** の一覧から、**[新規作成]** を選択します。

[Image: [アサーション解読秘密キー] の一覧にある [新規作成] のスクリーンショット。]

1. **OK** を選択します。
2. 新しいタブで **[SSL 証明書とキーのインポート]** ダイアログが表示されます。
3. **[Import Type] (インポートの種類)** で、**[PKCS 12 (IIS)]** を選びます。 このオプションを使用すると、証明書と秘密キーがインポートされます。
4. ブラウザー タブを閉じて、メイン タブに戻ります。

[Image: SSL 証明書とキー ソースのオプションと選択内容のスクリーンショット。]

1. **[暗号化アサーションを有効にする]** で、チェック ボックスをオンにします。
2. 暗号化を有効にした場合は、**[アサーション解読の秘密キー]** リストから、証明書を選択します。 これは、Microsoft Entra アサーションを解読するために BIG-IP APM で使用される証明書の秘密キーです。
3. 暗号化を有効にした場合は、**[アサーション解読の証明書]** リストから、証明書を選択します。 BIG-IP は、発行された SAML アサーションを暗号化するためにこの証明書を Microsoft Entra ID にアップロードします。

[Image: [セキュリティ設定] オプションと選択内容のスクリーンショット。]

#### Microsoft Entra ID

Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP 用のテンプレートと、汎用の SHA テンプレートが用意されています。

1. **[Oracle PeopleSoft]** を選択します。
2. **追加** を選択します。

##### Azure の構成

1. BIG-IP がテナントで作成するアプリの **[表示名]** を入力します。 [\[マイ アプリ\]](https://myapplications.microsoft.com/) のアイコンに名前が表示されます。
2. (オプション) **[サインオン URL]** に、PeopleSoft アプリケーションのパブリック FQDN を入力します。
3. **[署名キー]** と **[署名証明書]** の横にある**[更新]** を選択します。 このアクションで、インポートした証明書を検索します。
4. **[署名キーのパスフレーズ]** に、証明書のパスワードを入力します。
5. (オプション) **[署名オプション]** で、オプションを選択します。 この選択により、BIG-IP は Microsoft Entra ID によって署名されたトークンとクレームを受け入れます。

[Image: [SAML 署名証明書] の下の [署名キー]、[署名証明書]、[署名キーのパスフレーズ] オプションのスクリーンショット。]

1. **[ユーザーとユーザー グループ]** は、Microsoft Entra テナントから動的に照会されます。
2. テストに使用するユーザーまたはグループを追加します。そうしないと、アクセスが拒否されます。

[Image: [ユーザーとユーザー グループ] の [追加] オプションのスクリーンショット。]

##### ユーザー属性と要求

ユーザーが認証されると、Microsoft Entra ID は、ユーザーを識別する既定の要求と属性を使用して SAML トークンを発行します。 **[ユーザー属性と要求]** タブには、新しいアプリケーションのために発行する既定の要求が表示されます。 それを使用して、より多くの要求を構成します。 Easy Button テンプレートには、PeopleSoft に必要な従業員 ID 要求があります。

[Image: [ユーザー属性と要求] のオプションと選択内容のスクリーンショット。]

必要に応じて、他の Microsoft Entra 属性を含めます。 サンプルの PeopleSoft アプリケーションには、定義済みの属性が必要です。

##### 追加のユーザー属性

**[追加のユーザー属性]** タブでは、セッション拡張用のその他のディレクトリに格納されている属性を必要とする分散システムがサポートされます。 LDAP ソースからフェッチされた属性は、追加の SSO ヘッダーとして挿入して、ロール、パートナー ID などに基づいてアクセスを制御します。

注

この機能は Microsoft Entra ID と相関関係はなく、別の属性ソースです。

##### 条件付きアクセス ポリシー

条件付きアクセス ポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御するために、Microsoft Entra の事前認証の後に適用されます。 **[使用可能なポリシー]** ビューには、ユーザー アクションのない条件付きアクセス ポリシーがあります。 **[選択されたポリシー]** ビューには、クラウド アプリをターゲットとするポリシーがあります。 これらのポリシーは、テナント レベルで適用されるため、選択解除することも、[使用可能なポリシー] リストに移動することもできません。

アプリケーションのポリシーを選択します。

1. **[使用可能なポリシー]** リストでポリシーを選択します。
2. **右矢印**を選択して、ポリシーを **[選択されたポリシー]** リストに移動します。

選択したポリシーでは、**[含める]** または **[除外する]** オプションにチェックを入れます。 両方のオプションがオンになっている場合、そのポリシーは適用されません。

[Image: [条件付きアクセス ポリシー] タブの[選択されたポリシー] の下の除外されたポリシーのスクリーンショット。]

注

タブを選択すると、ポリシー リストが 1 回表示されます。ウィザードでテナントを照会するには、**[更新]** を使用します。 このボタンは、アプリケーションのデプロイ後に表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは、仮想 IP アドレスで表される BIG-IP データ プレーン オブジェクトです。 サーバーは、アプリケーションへのクライアント要求をリッスンします。 受信されたトラフィックは処理され、仮想サーバー の APM プロファイルに対して評価されます。 その後、トラフィックはポリシーに従って送信されます。

1. **[宛先アドレス]** に、BIG-IP でクライアント トラフィックの受信に使用する IPv4 または IPv6 アドレスを入力します。 対応するレコードが DNS に表示され、クライアントは公開済みアプリケーションの外部 URL を IP に解決できます。 テストのために、テスト コンピューターの localhost DNS を使用します。
2. **[サービス ポート]** には、「**443**」と入力し、**[HTTPS]** を選択します。
3. **[リダイレクト ポートを有効にする]** で、チェック ボックスをオンにします。
4. **[リダイレクト ポート]** には「**80**」と入力し、**[HTTP]** を選択します。 このオプションにより、受信 HTTP クライアント トラフィックが HTTPS にリダイレクトされます。
5. **[クライアント SSL プロファイル]** で、**[既存のものを使用]** を選択します。
6. **[共通]** で、作成したオプションを選択します。 テストする場合は、既定値のままにします。 [クライアント SSL プロファイル] では、クライアント接続が TLS で暗号化されるように、HTTPS 用の仮想サーバーを有効にできます。

[Image: 仮想サーバーのプロパティのオプションと選択のスクリーンショット。]

#### プールのプロパティ

**[アプリケーション プール]** タブには、アプリケーション サーバーを含むプールとして表される、BIG-IP の背後にあるサービスがあります。

1. **[プールの選択]** で、**[新規作成]** を選択するか、1 つを選びます。
2. **[Load Balancing Method] (負荷分散方法)** では、**[Round Robin] (ラウンド ロビン)** を選びます。
3. **[プール サーバー]** には、**[IP アドレス/ノード名]** でノードを選択するか、PeopleSoft アプリケーションをホストするサーバーの IP とポートを入力します。

[Image: [プールのプロパティ] の [IP アドレスとノード名] と [ポート] オプションのスクリーンショット。]

##### シングル サインオンと HTTP ヘッダー

Easy Button ウィザードでは、公開済みアプリケーションへの SSO のために、Kerberos、OAuth Bearer、HTTP Authorization ヘッダーがサポートされています。 PeopleSoft アプリケーションでは、ヘッダーが必要です。

1. **[HTTP エディター]** で、チェック ボックスをオンにします。
2. **[ヘッダー操作]** で、**[replace] (置換)** を選びます。
3. **[ヘッダー名]** に「**PS\_SSO\_UID**」と入力します。
4. **[ヘッダー値]** に「**%{session.sso.token.last.username}**」と入力します。

[Image: [シングル サインオンと HTTP ヘッダー] の [ヘッダー操作]、[ヘッダー名]、[ヘッダー値] エントリのスクリーンショット。]

注

中かっこ内の APM セッション変数では、大文字と小文字が区別されます。 たとえば、OrclGUID と入力すると、属性名が orclguid の場合に属性マッピングのエラーが発生します。

#### セッションの管理

BIG-IP セッション管理設定を使用して、ユーザー セッションの終了または継続の条件を定義します。 ユーザー、IP アドレス、および対応するユーザー情報に制限を設定します。

詳細については、support.f5.com にアクセスして、「[K18390492: セキュリティ | BIG-IP APM 操作ガイド](https://support.f5.com/csp/article/K18390492)」を参照してください

運用ガイドには、ユーザーがログアウトした際に IdP、BIG-IP、ユーザー エージェント セッションを終了する、シングル ログアウト (SLO) 機能は含まれていません。Easy Button によって Microsoft Entra テナントに SAML アプリケーションがインスタンス化されると、ログアウト URL に APM SLO エンドポイントが設定されます。 [マイ アプリ](https://myapplications.microsoft.com/)からの IdP によって開始されたサインアウトにより、BIG-IP とクライアントのセッションが終了します。

公開済みアプリケーションの SAML フェデレーション データは、テナントからインポートされます。 このアクションにより、APM に Microsoft Entra ID の SAML サインアウト エンドポイントが提供され、SP が開始したサインアウトによってクライアントと Microsoft Entra のセッションが終了します。 APM では、ユーザーがサインアウトするタイミングを把握する必要があります。

BIG-IP Web トップ ポータルで公開済みアプリケーションにアクセスすると、APM はサインアウト処理を実行し、Microsoft Entra サインアウト エンドポイントが呼び出されます。 BIG-IP Web ポータルが使用されない場合、ユーザーはサインアウトするよう APM に指示できません。ユーザーがアプリケーションからサインアウトした場合、BIG-IP で認識されません。 SP によって開始されるサインアウトには、セッションが安全に終了する必要があります。 SLO 関数をアプリケーションの **[サインアウト]** ボタンに追加して、クライアントを Microsoft Entra SAML または BIG-IP サインアウト エンドポイントにリダイレクトします。 テナントの サインアウト エンドポイント URL の SAML については、**[アプリの登録] &gt; [エンドポイント]** にあります。

アプリを変更できない場合は、BIG-IP でアプリケーションのサインアウト呼び出しをリッスンして SLO をトリガーすることを検討してください。 詳細については、次のセクションの「**PeopleSoft シングル ログアウト**」を参照してください。

#### 配置

1. **[デプロイ]** を選択します。
2. エンタープライズ アプリケーションのテナント一覧でアプリケーションを確認します。
3. アプリケーションは公開済みになり、SHA を使用してアクセスできます。

### PeopleSoft を構成する

PeopleSoft アプリケーション に Oracle Access Manager を使用すると、アプリケーションの ID とアクセスの管理が提供されます。

詳細については、docs.oracle.com で「[Oracle Access Manger 統合ガイド、PeopleSoft の統合](https://docs.oracle.com/cd/E12530_01/oam.1014/e10356/people.htm)」を参照してください

#### Oracle Access Manager の SSO を構成する

Oracle Access Manager で、BIG-IP からのシングル サイン オン (SSO) を受け入れるように設定します。

1. 管理者権限を使用して、Oracle コンソールにサインインします。

[Image: Oracle コンソールのスクリーンショット。]

1. **[PeopleTools &gt; セキュリティ]** に移動します。
2. **[ユーザー プロファイル]** を選択します。
3. **[ユーザー プロファイル]** を選択します。
4. 新しいユーザー プロファイルを作成します。
5. **[ユーザー ID]** に、「**OAMPSFT**」と入力します
6. **[ユーザー ロール]** に、「**PeopleSoft User**」と入力します。
7. **[保存]** を選択します。
8. **[People Tools**&gt; Web プロファイル] に移動します。
9. Web プロファイルを選択します。
10. **[パブリック ユーザー]** の **[セキュリティ]** タブで、**[パブリック アクセスを許可]** を選択します。
11. **[ユーザー ID]** に、「**OAMPSFT**」と入力します。
12. **パスワード**を入力します。
13. Peoplesoft コンソールを終了します。
14. **PeopleTools アプリケーション デザイナー** を起動します。
15. **[LDAPAUTH]** フィールドを右クリックします。
16. **[PeopleCode の表示]** を選択します。

[Image: [アプリケーション デザイナー] の [LDAPAUTH] オプションのスクリーンショット。]

1. **[LDAPAUTH]** コード ウィンドウが開きます。
2. **OAMSSO\_AUTHENTICATION** 関数を見つけます。
3. **&defaultUserId** 値を **OAMPSFT**に置き換えます。

    [Image: デフォルトのユーザー ID 値のスクリーンショットは、[関数] の下の [OAMPSFT] と同じです。]
4. レコードを保存します。
5. \*\*[PeopleTools&gt;セキュリティ] に移動します。
6. **[セキュリティ オブジェクト]** を選択します。
7. **[PeopleCode にサインオン]** を選択します。
8. **[OAMSSO\_AUTHENTICATION]** を有効にします。

#### PeopleSoft シングル ログアウト

[\[マイ アプリ\]](https://myapplications.microsoft.com/) からサインアウトすると、PeopleSoft SLO が開始され、BIG-IP SLO エンドポイントが呼び出されます。 BIG-IP には、アプリケーションに代わって SLO を実行する手順が必要です。 BIG-IP に PeopleSoft へのユーザー サインアウト要求をリッスンさせ、SLO をトリガーさせます。

すべての PeopleSoft ユーザーに対して SLO サポートを追加します。

1. PeopleSoft ポータルのサインアウト URL を取得します。
2. Web ブラウザーを使用して、ポータルを開きます。
3. デバッグ ツールを有効にします。
4. **PT\_LOGOUT\_MENU** ID を持つ 要素を見つけます。
5. クエリ パラメーターを使用して URL パスを保存します。 この例では、`/psp/ps/?cmd=logout` です。

[Image: PeopleSoft ログアウト URL のスクリーンショット。]

BIG-IP iRule を作成して、ユーザーを SAML SP サインアウト エンドポイントの `/my.logout.php3` にリダイレクトします。

1. \*\*[ローカル トラフィック &gt; iRules] リストに移動します。
2. **[作成]** を選択します。
3. [ルール**名]** を入力します
4. [コマンド ライン] に次のように入力します。

`when HTTP_REQUEST {switch -glob -- [HTTP::uri] { "/psp/ps/?cmd=logout" {HTTP::redirect "/my.logout.php3" }}}`

1. **[完了]** を選択します。

この iRule を BIG-IP 仮想サーバーに割り当てます。

1. **[アクセス] &gt; [ガイド付き構成]** に移動します。
2. PeopleSoft アプリケーションの構成リンクを選択します。

[Image: PeopleSoft アプリケーション構成リンクのスクリーンショット。]

1. 上部のナビゲーション バーから、**[仮想サーバー]** を選択します。
2. **[詳細設定]** で、\**[オン]* を選択します。

[Image: 仮想サーバーのプロパティの [詳細設定] オプションのスクリーンショット。]

1. 一番下までスクロールします。
2. **[共通]** で、作成した iRule を追加します。

[Image: 仮想サーバー構成の共通の下にある iRule のスクリーンショット。]

1. **[保存]** を選択します。
2. **[次へ]** を選択します。
3. 引き続き、設定を構成します。

詳細については、support.f5.com にアクセスし、次のページを参照してください。

- [K42052145: URI 参照ファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145)
- [K12056: ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)

#### 既定で PeopleSoft ランディング ページに設定する

ルート ("/") から外部 PeopleSoft ポータル (通常は "/psc/ps/EXTERNAL/HRMS/c/NUI\_FRAMEWORK.PT\_LANDINGPAGE.GBL" にある) に、ユーザー要求をリダイレクトします

1. **[ローカル トラフィック]&gt; [iRule]** に移動します。
2. **[iRule\_PeopleSoft]** を選択します。
3. 次のコマンド ラインを追加します。

`when HTTP_REQUEST {switch -glob -- [HTTP::uri] {"/" {HTTP::redirect "/psc/ps/EXTERNAL/HRMS/c/NUI_FRAMEWORK.PT_LANDINGPAGE.GB"/psp/ps/?cmd=logout" {HTTP::redirect "/my.logout.php3"} } }`

1. この iRule を BIG-IP 仮想サーバーに割り当てます。

### 構成を確定する

1. ブラウザーを使用して、PeopleSoft アプリケーションの外部 URL に移動するか、[\[マイ アプリ\]](https://myapps.microsoft.com/) でアプリケーションのアイコンを選択します。
2. Microsoft Entra ID に対して認証します。
3. BIG-IP 仮想サーバーにリダイレクトされ、SSO でサインインされます。

    注

    アプリケーションへの直接アクセスをブロックできます。これにより BIG-IP を介したパスが強制されます。

### 詳細なデプロイ

ガイド付き構成テンプレートは、柔軟性が不足している場合があります。

詳細情報: [チュートリアル: ヘッダーベースの SSO 用に F5 BIG-IP Access Policy Manager を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-header-advanced)を参照してください。

または、BIG-IP で、ガイド付き構成の厳格な管理モードを無効にできます。 構成を手動で変更しますが、ほとんどの構成はウィザード テンプレートを使用して自動化されます。

1. **[アクセス] &gt; [ガイド付き構成]** に移動します。
2. 行の末尾で、**[padlock]** を選択します。

[Image: 南京錠アイコンのスクリーンショット。]

ウィザード UI を介した変更は行えません。ただし、公開済みアプリケーションのインスタンスに関連付けられているすべての BIG-IP オブジェクトのロックが解除され、管理できるようになります。

注

厳密モードを再有効化して構成をデプロイすると、ガイド付き構成以外で行われた設定が上書きされます。 運用サービスには高度な構成をお勧めします。

### トラブルシューティング

BIG-IP のログを使用して、接続、SSO、ポリシー違反、正しく構成されていない変数マッピングなどの問題を分離します。

#### ログの冗長性

1. **[Access Policy] (アクセス ポリシー) &gt; [Overview] (概要)** に移動します。
2. **[Event Logs] (イベント ログ)** を選びます。
3. **設定** を選択します。
4. 発行されたアプリケーションの行を選択します。
5. **[編集]** を選択します。
6. **[Access System Logs] (システム ログへのアクセス)** を選択します。
7. SSO の一覧で、**[デバッグ]** を選択します。
8. **OK** を選択します。
9. 問題を再現します。
10. ログを調べます。

完了したら、詳細モードでは多くのデータが生成されるため、この機能を元に戻します。

#### BIG-IP のエラー メッセージ

Microsoft Entra の事前認証後に BIG-IP のエラーが表示される場合は、Microsoft Entra ID から BIG IP SSO に関連する問題が発生している可能性があります。

1. **[アクセス] &gt;[概要]** に移動します。
2. **[Access reports] (レポートへのアクセス)** を選びます。
3. 直近 1 時間のレポートを実行します。
4. ログに手がかりがないか確認します。

セッションの **[セッションの表示]** リンクを使用して、APM が予想される Microsoft Entra 要求を受信したことを確認します。

#### BIG-IP エラー メッセージなし

BIG-IP エラー メッセージが表示されない場合、問題はバックエンド要求、または BIG-IP からアプリケーションへの SSO に関連している可能性があります。

1. **[Access Policy] (アクセス ポリシー) &gt; [Overview] (概要)** に移動します。
2. **[アクティブ セッション]** を選びます。
3. アクティブなセッション リンクを選びます。

特に、BIG-IP APM がセッション変数から不正な属性を取得した場合は、**[変数の表示]** リンクを使用して、SSO の問題を特定します。

詳細情報:

- devcentral.f5.com にアクセスして、「[APM 変数の割り当ての例](https://devcentral.f5.com/s/articles/apm-variable-assign-examples-1107)」を参照してください
- techdocs.f5.com にアクセスして、「[セッション変数](https://techdocs.f5.com/en-us/bigip-15-1-0/big-ip-access-policy-manager-visual-policy-editor/session-variables.html)」に関する記事を参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-sap-erp-easy-button"} -->
## SAP ERP* への SSO* 用に F5 BIG-IP* Easy Button を構成する* - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-sap-erp-easy-button
- Service: entra-id / enterprise-apps
- Article date: 2024-06-28
- Summary: F5 BIG-IP Easy Button ガイド付き構成で、Microsoft Entra ID を使用して SAP Enterprise Resource Planning (ERP) をセキュリティで保護する方法について説明します。

この記事では、F5 BIG-IP Easy Button ガイド付き構成 16.1 で、Microsoft Entra ID を使用して SAP Enterprise Resource Planning (ERP) をセキュリティで保護する方法について説明します。 BIG-IP と Microsoft Entra ID の統合には、多くの利点があります。

- [リモート作業を有効にするゼロ トラスト フレームワーク](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)
- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- Microsoft Entra ID と BIG-IP 公開済みサービス間のシングル サインオン (SSO)
- [Microsoft Entra 管理センター](https://entra.microsoft.com)から ID とアクセスを管理する

詳細情報:

- [F5 BIG-IP と Microsoft Entra ID の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- [エンタープライズ アプリケーションの SSO を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)。

### シナリオの説明

このシナリオでは、SAP ERP アプリケーションによって、Kerberos 認証を使用して保護されたコンテンツへのアクセスを管理します。

レガシ アプリケーションには、Microsoft Entra ID の統合をサポートするための最新のプロトコルがありません。 最新化にはコストがかかり、計画が必要であり、ダウンタイムのリスクが伴います。 代わりに、プロトコル遷移によって、F5 BIG IP Application Delivery Controller (ADC) を使用して、レガシ アプリケーションと最新の ID コントロール プレーンとの間のギャップを埋めます。

アプリケーションの手前に BIG-IP を置くことにより、Microsoft Entra の事前認証およびヘッダーベース SSO をサービスにオーバーレイできます。 この構成により、アプリケーションのセキュリティ態勢全体が向上します。

### シナリオのアーキテクチャ

セキュア ハイブリッド アクセス (SHA) ソリューションは、次のコンポーネントで構成されています。

- **SAP ERP アプリケーション** - Microsoft Entra SHA によって保護された BIG-IP 公開サービス
- **Microsoft Entra ID** - Security Assertion Markup Language (SAML) ID プロバイダー (IdP)。これにより、ユーザーの資格情報、条件付きアクセス、BIG-IP への SAML ベースの SSO が検証されます
- **BIG-IP** - アプリケーションへのリバース プロキシと SAML サービス プロバイダー (SP)。 BIG-IP では、SAML IdP に認証を委任した後、SAP サービスに対するヘッダーベースの SSO を実行します

SHA では、SP と IdP によって開始されるフローがサポートされます。 次の図は、SP Initiated フローを示しています。

[Image: セキュリティで保護されたハイブリッド アクセス、SP によって開始されるフローの図。]

1. ユーザーがアプリケーション エンドポイント (BIG-IP) に接続します。
2. BIG-IP Access Policy Manager (APM) アクセス ポリシーにより、ユーザーが Microsoft Entra ID (SAML IdP) にリダイレクトされます。
3. Microsoft Entra ID によって、ユーザーの事前認証と、条件付きアクセス ポリシーの適用が行われます。
4. ユーザーは BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行されます。
5. BIG-IP は、キー配布センター (KDC) から Kerberos チケットを要求します。
6. BIG-IP がバックエンド アプリケーションに対し、SSO 用の Kerberos チケットとともに要求を送信する
7. アプリケーションが要求を承認し、ペイロードを返します。

### 前提条件

- Microsoft Entra ID 無料アカウント、またはそれ以上のアカウント
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を入手してください
- BIG-IP、または Azure の BIG-IP Virtual Edition (VE)
    - [Azure での F5 BIG-IP Virtual Edition 仮想マシン (VM) のデプロイに関](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)するページを参照してください
- 次のいずれかの F5 BIG-IP ライセンス:
    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP APM スタンドアロン ライセンス
    - 既存の BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP APM アドオン ライセンス
    - 90 日間 BIG-IP 完全な機能 [試用版ライセンス](https://www.f5.com/trial/big-ip-trial.php)
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID、または Microsoft Entra ID 内で作成されてオンプレミス ディレクトリに戻されたユーザー ID
    - [Microsoft Entra Connect Sync を参照: 同期を理解してカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)する
- クラウド アプリケーション管理者ロール、アプリケーション管理者ロールのいずれか。
- HTTPS を介してサービスを発行するための SSL Web 証明書。または、テストの場合には既定の BIG-IP 証明書を使用します
    - [Azure での F5 BIG-IP Virtual Edition VM のデプロイに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)参照してください
- Kerberos 認証用に構成された SAP ERP 環境

### BIG-IP の構成方法

このチュートリアルでは、Easy Button テンプレートを備えたガイド付き構成 16.1 を使用します。 Easy Button を使用すると、管理者は Microsoft Entra ID と BIG-IP の間を移動せずに SHA のサービスを有効化できます。 APM のガイド付き構成ウィザードと Microsoft Graph によって、デプロイとポリシー管理が処理されます。 この統合により、アプリケーションでは ID フェデレーション、SSO、条件付きアクセスのサポートが確保されます。

Note

このガイドの文字列または値の例は、実際の環境のものに置き換えてください。

### Easy Button を登録する

クライアントまたはサービスは、Microsoft Graph にアクセスする前に、Microsoft ID プラットフォームによって信頼される必要があります。

「[クイック スタート: Microsoft ID プラットフォームにアプリケーションを登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)する」を参照してください

Microsoft Entra ID で Easy Button クライアントを登録すると、BIG-IP 公開アプリケーションの SAML SP インスタンスと、SAML IdP としての Microsoft Entra ID の間の信頼が確立されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[アプリの登録]**&gt;**[新規登録]** にアクセスします。
3. 新しいアプリケーションの名前を入力 **します** 。
4. **この組織ディレクトリのアカウントでのみ**、アプリケーションを使用できるユーザーを指定します。
5. [ **登録**] を選択します。
6. **API のアクセス許可**に移動します。
7. 次の Microsoft Graph アプリケーションのアクセス許可を認可します。

    - Application.Read.All
    - Application.ReadWrite.All
    - Application.ReadWrite.OwnedBy
    - Directory.Read.All (ディレクトリのすべてを読む)
    - Group.Read.All
    - IdentityRiskyUser.Read.All（アイデンティティリスキーユーザー.リード.オール）
    - Policy.Read.All
    - ポリシー.読み取り書き込み.アプリケーション設定
    - Policy.ReadWrite.ConditionalAccess
    - User.Read.All
8. 組織に管理者の同意を付与します。
9. **[証明書とシークレット]** で、新しい**クライアント シークレット**を生成します。
10. シークレットをメモします。
11. **[概要]** から、**クライアント ID** と**テナント ID を**書き留めます。

### Easy Button を構成する

1. APM Guided Configuration (ガイド付き構成) を開始します。
2. Easy Button テンプレートを起動します。
3. ブラウザーから F5 BIG-IP 管理コンソールにサインインします。
4. **Access &gt; ガイド付き構成&gt; Microsoft Integration** に移動します。
5. **Microsoft Entra アプリケーションを選択します**。
6. 構成ファイルリストを確認します。
7. [ **次へ**] を選択します。
8. **Microsoft Entra Application Configuration** の下の構成シーケンスに従います。

[Image: 構成シーケンスのスクリーンショット。]

#### 構成プロパティ

[ **構成プロパティ** ] タブにはサービス アカウントのプロパティがあり、BIG-IP アプリケーション構成と SSO オブジェクトが作成されます。 **[Azure サービス アカウントの詳細]** セクションは、Microsoft Entra テナントでアプリケーションとして登録したクライアントを表します。 BIG-IP OAuth クライアントの設定を使用して、SSO プロパティと共に SAML SP を個別にテナントに登録します。 Easy Button により、発行されて SHA が有効になっている BIG-IP サービスに対してこのアクションが実行されます。

Note

一部の設定はグローバルであり、より多くのアプリケーションを公開するために再利用できます。

1. **構成名**を入力します。 一意の名前を使用すると、Easy Button の構成が区別されます。
2. **[シングル Sign-On (SSO) と HTTP ヘッダー] で** **、[オン]** を選択します。
3. **[テナント ID]、[クライアント ID]、[** **クライアント シークレット]** に、テナント登録時に指定したテナント ID、クライアント ID、およびクライアント シークレットを入力します。
4. [ **テスト接続]** を選択します。 このアクションにより、BIG-IP がテナントに接続されたことを確認します。
5. [ **次へ**] を選択します。

#### サービス プロバイダー

[サービス プロバイダー] 設定を使用して、SHA によってセキュリティ保護されたアプリケーションの SAML SP インスタンス プロパティを定義します。

1. **[ホスト**] に、セキュリティで保護されているアプリケーションのパブリック完全修飾ドメイン名 (FQDN) を入力します。
2. **エンティティ ID** には、トークンを要求する SAML SP を識別するために Microsoft Entra ID が使用する識別子を入力します。

    [Image: サービス プロバイダーのオプションと選択のスクリーンショット。]
3. (省略可能) **セキュリティ設定** を使用して、発行された SAML アサーションを Microsoft Entra ID で暗号化することを示します。 Microsoft Entra ID と BIG-IP APM 間の暗号化されたアサーションにより、コンテンツ トークンが傍受されることや、データが侵害されないことへの信頼が高まります。
4. **Assertion Decryption 秘密キー**から、[**新規作成**] を選択します。

    [Image: [Assertion Decryption 秘密キー] リストの [新規作成] オプションのスクリーンショット。]
5. [ **OK] を選択します**。
6. [ **SSL 証明書とキーのインポート** ] ダイアログが新しいタブに表示されます。
7. 証明書と秘密キーをインポートするには、 **PKCS 12 (IIS)** を選択します。
8. ブラウザー タブを閉じて、メイン タブに戻ります。

    [Image: [SSL 証明書とキーのインポート] のオプションと選択のスクリーンショット。]
9. [ **暗号化されたアサーションを有効にする]** で、チェック ボックスをオンにします。
10. 暗号化を有効にした場合、[ **Assertion Decryption 秘密キー** ] ボックスの一覧から、APM が Microsoft Entra アサーションの暗号化解除に使用 BIG-IP 証明書の秘密キーを選択します。
11. 暗号化を有効にした場合は、[ **Assertion Decryption Certificate]\(アサーション復号化証明書** \) の一覧から、発行された SAML アサーションを暗号化するために Microsoft Entra ID にアップロード BIG-IP 証明書を選択します。

[Image: サービス プロバイダーのオプションと選択のスクリーンショット。]

#### Microsoft Entra ID

Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP 用のアプリケーション テンプレートと、汎用の SHA テンプレートが用意されています。

1. Azure 構成を開始するには、 **SAP ERP Central Component &gt; Add** を選択します。

    [Image: Azure Configuration の SAP ERP Central Component オプションと [追加] ボタンのスクリーンショット。]

    Note

    Microsoft Entra テナントで新しい BIG-IP SAML アプリケーションを手動で構成する場合は、以下のセクションの情報を使用できます。

##### Azure の構成

1. **[表示名]** に、Microsoft Entra テナントで BIG-IP が作成したアプリの名前を入力します。 マイ [アプリ](https://myapplications.microsoft.com/) ポータルのアイコンに名前が表示されます。
2. (省略可能) **サインオン URL (省略可能)** は空白のままにします。

    [Image: [表示名] と[サインオン URL] のエントリのスクリーンショット。]
3. **[署名キー**] の横にある [更新] を選択**します**。
4. [ **署名証明書**] を選択します。 このアクションで、入力した証明書を検索します。
5. **[署名キーパスフレーズ**] に、証明書パスワードを入力します。
6. (省略可能) **署名オプションを**有効にします。 このオプションにより、BIG-IP は Microsoft Entra ID によって署名されたトークンとクレームを受け入れます

    [Image: 署名キー、署名証明書、および署名キー パスフレーズのエントリのスクリーンショット。]
7. **ユーザーとユーザー グループ** は、Microsoft Entra テナントから動的に照会されます。 グループがあると、アプリケーションへのアクセスを承認しやすくなります。
8. テストに使用するユーザーまたはグループを追加します。そうしないと、アクセスが拒否されます。

    [Image: ユーザーとユーザー グループの [追加] ボタンのスクリーンショット。]

##### ユーザー属性と要求

ユーザーが Microsoft Entra ID への認証を行うと、ユーザーを識別する既定の要求と属性を使用して SAML トークンを発行します。 [ **ユーザー属性と要求** ] タブには、新しいアプリケーションに対して発行する既定の要求が表示されます。 それを使用して、より多くの要求を構成します。

このチュートリアルは、内部および外部で使用される .com ドメイン サフィックスを基に作成されています。 機能する Kerberos の制約付き委任 (KCD) の SSO 実装を実現するために、その他の属性は必要ありません。

[Image: [ユーザー属性] > [要求] タブのスクリーンショット。]

より多くの Microsoft Entra 属性を含めることができます。 このチュートリアルでは、SAP ERP に既定の属性が必要です。

詳細情報: [チュートリアル: Kerberos 認証用に F5 BIG-IP Access Policy Manager を構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-kerberos-advanced)する。 別のサフィックスを使用する複数のドメインまたはユーザーのサインインに関する手順を参照してください。

##### 追加のユーザー属性

[ **追加のユーザー属性** ] タブでは、セッション拡張のために、他のディレクトリに格納されている属性を必要とする分散システムがサポートされます。 次に、Lightweight Directory Access Protocol (LDAP) ソースからの属性を追加の SSO ヘッダーとして挿入すると、ロールベースのアクセス、パートナー ID などを制御できます。

Note

この機能は Microsoft Entra ID と相関関係ありません。別の属性ソースです。

##### 条件付きアクセス ポリシー

条件付きアクセス ポリシーは、Microsoft Entra の事前認証後に適用されます。 このアクションは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御します。

**[使用可能なポリシー] ビューには、**ユーザーベースのアクションのない条件付きアクセス ポリシーが一覧表示されます。

**[選択したポリシー] ビューには、**クラウド アプリを対象とするポリシーが一覧表示されます。 これらのポリシーはテナント レベルで適用され、選択解除することも、[使用可能なポリシー] リストに移動することもできません。

公開されるアプリケーションのポリシーを選択するには、以下の手順を実行します。

1. **使用可能なポリシーの**一覧から、ポリシーを選択します。
2. 右矢印を選択します。
3. ポリシーを **選択したポリシー** の一覧に移動します。

選択したポリシーの **[含める** ] または **[除外]** オプションがオンになっています。 両方のオプションをオンにした場合、選択したポリシーは適用されません。

[Image: [選択したポリシー] の除外されたポリシーのスクリーンショット。]

Note

このタブを最初に選択すると、ポリシーの一覧が表示されます。 **[更新** ] ボタンを使用してテナントのクエリを実行します。 アプリケーションがデプロイされると、更新内容が表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは、仮想 IP アドレスで表される BIG-IP データ プレーン オブジェクトです。 このサーバーは、アプリケーションへのクライアント要求をリッスンします。 受信されたトラフィックは処理され、仮想サーバーに関連する APM プロファイルに対して評価されます。 トラフィックは、その後ポリシーに従って送信されます。

1. **宛先アドレス**を入力します。 BIG-IP でクライアント トラフィックを受信するために使用される IPv4/IPv6 アドレスを使用します。 対応するレコードは、ドメイン ネーム サーバー (DNS) に存在します。これにより、クライアントは BIG-IP 公開アプリケーションの外部 URL をこの IP アドレスに解決できます。 テストのために、テスト コンピューターの localhost DNS を使用できます。
2. **[サービス ポート]** に「**443」と入力します**。
3. **[HTTPS]** を選択します。
4. [ **Enable Redirect Port]\(リダイレクト ポートを有効にする\)** で、チェック ボックスをオンにします。
5. **[リダイレクト ポート]** に番号を入力し、[**HTTP**] を選択します。 このオプションにより、受信 HTTP クライアント トラフィックが HTTPS にリダイレクトされます。
6. 作成した **クライアント SSL プロファイル** を選択します。 または、テスト用に既定値のままにします。 クライアント SSL プロファイルを使用すると、HTTPS 用の仮想サーバーが有効になり、クライアント接続がトランスポート層セキュリティ (TLS) で暗号化されるようになります。

[Image: 仮想サーバーのプロパティのオプションと選択のスクリーンショット。]

#### プールのプロパティ

[ **アプリケーション プール** ] タブには、アプリケーション サーバーを含むプールとして表される BIG-IP の背後にサービスがあります。

1. [ **プールの選択**] で、[ **新規作成**] を選択するか、プールを選択します。
2. **[負荷分散方法**] で、[**ラウンド ロビン**] を選択します。
3. **プール サーバー**の場合は、サーバー ノードを選択するか、ヘッダーベースのアプリケーションをホストするバックエンド ノードの IP とポートを入力します。

    [Image: アプリケーション プールのオプションと選択のスクリーンショット。]

##### シングル サインオンと HTTP ヘッダー

SSO を使用すると、ユーザーは資格情報を入力することなく、BIG-IP で発行済みのサービスにアクセスできます。 Easy Button ウィザードでは、SSO 用に Kerberos、OAuth Bearer、HTTP Authorization ヘッダーがサポートされています。 次の手順では、作成した Kerberos 委任アカウントが必要です。

1. **[シングル サインオン& HTTP ヘッダー]** の **[詳細設定]**で、**[オン]** を選択します。
2. **[選択されたシングル サインオンの種類]**で、**[Kerberos]** を選択します。
3. **[Username Source]\(ユーザー名ソース**\) に、ユーザー ID ソースとしてセッション変数を入力します。 `session.saml.last.identity` は、サインインしているユーザー ID と共に Microsoft Entra 要求を保持します。
4. ユーザー・ドメインが BIG-IP kerberos 領域と異なる場合は、ユーザー **領域ソース** ・オプションが必要です。 したがって、APM セッション変数には、サインインしているユーザー ドメインが含まれます。 たとえば、「 `session.saml.last.attr.name.domain` 」のように入力します。

    [Image: シングル サインオンと HTTP ヘッダーのオプションと選択のスクリーンショット。]
5. **KDC** の場合は、ドメイン コントローラーの IP、または DNS が構成されている場合は FQDN を入力します。
6. **UPN サポート**の場合は、チェック ボックスをオンにします。 APM では、kerberos チケット発行にユーザー プリンシパル名 (UPN) を使用します。
7. **SPN パターン**の場合は、「**HTTP/%h**」と入力します。 このアクションでは、クライアント要求ホスト ヘッダーを使用して、APM に対し Kerberos トークンを要求しているサービス プリンシパル名 (SPN) をビルドするように通知します。
8. **[承認の送信] では、**認証をネゴシエートするアプリケーションのオプションを無効にします。 たとえば、 **Tomcat** です。

    [Image: SSO メソッド構成のオプションと選択のスクリーンショット。]

#### セッションの管理

BIG-IP セッション管理設定を使用して、ユーザー セッションの終了または継続の条件を定義します。 条件には、ユーザーと IP アドレスの制限、および対応するユーザー情報が含まれます。

詳細については、「K18390492の [my.f5.com: セキュリティ | BIG-IP APM 操作ガイド](https://support.f5.com/csp/article/K18390492)」を参照してください。

運用ガイドでは、シングル ログアウト (SLO) についての記載はありません。 ユーザーがサインアウトした場合、この機能により IdP、BIG-IP、ユーザー エージェント間のセッションが確実に終了します。Easy Button により、SAML アプリケーションが Microsoft Entra テナントに SAML アプリケーションをデプロイします。 ログアウト URL に APM SLO エンドポイントを設定します。 [マイ アプリ](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510) ポータルから IdP によって開始されたサインアウトにより、BIG-IP とクライアント セッションが終了します。

デプロイ時に、テナントから公開アプリケーションの SAML フェデレーション メタデータがインポートされます。 このアクションにより、APM に Microsoft Entra ID の SAML サインアウト エンドポイントが提供され、SP が開始したサインアウトによってクライアントと Microsoft Entra のセッションが終了します。

### 配置

1. [ **デプロイ] を選択します**。
2. アプリケーションがテナントの **エンタープライズ アプリケーション** の一覧に含まれているかどうかを確認します。
3. ブラウザーで、アプリケーションの外部 URL に接続するか、[**マイ アプリ**] でアプリケーション [アイコン](https://myapps.microsoft.com/)を選択します。
4. Microsoft Entra ID に対して認証します。
5. BIG-IP 仮想サーバーにリダイレクトされ、SSO を通じてサインインされます。

セキュリティを強化するため、アプリケーションへの直接アクセスをブロックして、BIG-IP を介したパスを適用できます。

### 詳細なデプロイ

ガイド付き構成テンプレートは、柔軟性が不足している場合があります。

詳細情報: [チュートリアル: Kerberos 認証用に F5 BIG-IP Access Policy Manager を構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-kerberos-advanced)する。

#### 厳格な管理モードを無効化する

また、BIG-IP では、ガイド付き構成の厳格な管理モードを無効化できます。 構成を手動で変更できますが、ほとんどの構成はウィザード テンプレートを使用して自動化されます。

1. **[アクセス] &gt; [ガイド付き構成]** に移動します。
2. アプリケーション構成の行の最後で、 **南京錠**を選択します。
3. 公開アプリケーションに関連する BIG-IP オブジェクトは、管理のためにロック解除されます。 ウィザード UI を介した変更はできなくなります。

    [Image: 南京錠アイコンのスクリーンショット。]

    Note

    厳格な管理モードを再有効化して、ガイド付き構成 UI 以外の設定を上書きする構成をデプロイするには、運用サービス向けの詳細構成の方式をお勧めします。

### トラブルシューティング

SHA で保護されたアプリケーションにアクセスできない場合は、次のトラブルシューティング ガイダンスを参照してください。

- Kerberos は時刻に依存します。 サーバーとクライアントが正しい時刻に設定され、信頼性の高いタイム ソースと同期されていることを確認します。
- ドメイン コントローラーと Web アプリのホスト名が DNS で解決されることを確認します。
- 環境内に重複する SPN がないことを確認します。
    - ドメイン コンピューターのコマンド ラインで、`setspn -q HTTP/my_target_SPN` のクエリを使用します。

インターネット インフォメーション サービス (IIS) アプリケーションの KCD 構成を検証するには、「[アプリケーション プロキシの KCD 構成のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-back-end-kerberos-constrained-delegation-how-to)」を参照してください。

techdocs.f5.comに移動して、[Kerberos のシングル サインオン方法](https://techdocs.f5.com/en-us/bigip-17-1-0/big-ip-access-policy-manager-single-sign-on-concepts-configuration/kerberos-single-sign-on-method.html)をご覧ください。

#### ログ分析

##### ログの冗長性

BIG-IP のログを使用して、接続、SSO、ポリシー違反、正しく構成されていない変数マッピングなどの問題を分離します。 トラブルシューティングを開始するには、ログの詳細度を上げます。

1. **アクセス ポリシー &gt;概要**に移動します。
2. **[イベント ログ]** を選択します。
3. [ **設定] を選択します**。
4. 発行されたアプリケーションの行を選択します。
5. [ **編集] を選択します**。
6. **[アクセス システム ログ]** を選択する
7. SSO の一覧から [ **デバッグ**] を選択します。
8. [ **OK] を選択します**。
9. 問題を再現します。
10. ログを調べます。

このモードでは過剰なデータが生成されるため、検査が完了したらログの詳細度を元に戻します。

##### BIG-IP のエラー メッセージ

Microsoft Entra 事前認証後に BIG-IP のエラー メッセージが表示される場合、Microsoft Entra ID から BIG-IP への SSO に関する問題が発生している可能性があります。

1. **[Access &gt; Overview**] に移動します。
2. [ **レポートへのアクセス**] を選択します。
3. 直近 1 時間のレポートを実行します。
4. ログを調べます。

現在のセッションの **[セッション変数の表示]** リンクを使用して、APM が予想される Microsoft Entra 要求を受け取るかどうかを確認します。

##### BIG-IP エラー メッセージなし

BIG-IP エラー メッセージが表示されない場合、問題はバックエンド要求、または BIG-IP からアプリケーションへの SSO に関連している可能性があります。

1. **アクセス ポリシー &gt;概要**に移動します。
2. **[アクティブなセッション] を選択します**。
3. 現在のセッションのリンクを選択します。
4. [ **変数の表示** ] リンクを使用して、KCD の問題を特定します。特に、BIG-IP APM がセッション変数から正しいユーザー識別子とドメイン識別子を取得しない場合です。

詳細情報:

- [APM 変数の割り当て例](https://devcentral.f5.com/s/articles/apm-variable-assign-examples-1107)の devcentral.f5.com に移動する
- [セッション変数](https://techdocs.f5.com/en-us/bigip-16-1-0/big-ip-access-policy-manager-visual-policy-editor/session-variables.html)の techdocs.f5.com に移動する
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-bigip-deployment-guide"} -->
## セキュリティで保護されたハイブリッド アクセスと F5 デプロイのガイド - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide
- Service: entra-id / enterprise-apps
- Article date: 2024-11-07
- Summary: セキュリティで保護されたハイブリッド アクセスのために F5 BIG-IP Virtual Edition (VE) VM を Azure IaaS にデプロイするチュートリアル

このチュートリアルでは、BIG-IP Virtual Edition (VE) を Azure サービスとしてのインフラストラクチャ (IaaS) にデプロイする方法について説明します。 チュートリアルの最後には、以下のものが得られます。

- セキュリティで保護されたハイブリッド アクセス (SHA) の概念実証をモデル化するため準備された BIG-IP 仮想マシン (VM)
- 新しい BIG-IP システムの更新プログラムと修正プログラムをテストするためのステージング インスタンス

詳細情報: [SHA: Microsoft Entra ID を使用してレガシ アプリをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access)

### 前提条件

以前の F5 BIG-IP の経験や知識は必要ありません。 しかし、F5 の[用語集](https://www.f5.com/services/resources/glossary)で業界標準の用語を確認することをお勧めします。

Azure で SHA のために BIG-IP をデプロイするには、次のものが必要です。

- 有料の Azure サブスクリプション
    - お持ちでない場合は、[Azure 無料試用版](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を入手できます
- 次のいずれかの F5 BIG-IP ライセンス SKU
    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス
    - BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - 90 日間の BIG-IP 全機能[試用版ライセンス](https://www.f5.com/trial/big-ip-trial.php)
- SSL (Secure Socket Layer) 経由で Web アプリケーションを公開するための、ワイルドカードまたはサブジェクト代替名 (SAN) 証明書
    - letsencrypt.org に移動してオファーを表示します。 [\[開始\]](https://letsencrypt.org/) を選択します。
- BIG-IP 管理インターフェイスをセキュリティで保護するための SSL 証明書。 サブジェクトが BIG-IP の完全修飾ドメイン名 (FQDN) に対応している場合は、Web アプリを公開するために証明書を使用できます。 たとえば、`*.contoso.com` のサブジェクト `https://big-ip-vm.contoso.com:8443` にワイルドカード証明書を使用することができます。

VM のデプロイと基本システムの構成には約 30 分かかります。そのとき、BIG-IP では、「[F5 BIG-IP と Microsoft Entra ID の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)」の SHA シナリオを実装します。

#### テストのシナリオ

シナリオをテストする場合、このチュートリアルでは次のことを前提としています。

- BIG-IP は、Active Directory (AD) 環境を使用して Azure リソース グループにデプロイされます
- この環境は、ドメイン コントローラー (DC) とインターネット インフォメーション サービス (IIS) Web ホスト VM で構成されます
- BIG-IP VM と同じ場所にないサーバーは、BIG-IP でシナリオをサポートするために必要なロールが認識されている場合は受け入れられます
- VPN 接続経由で別の環境に接続されている BIG-IP VM はサポートされています

テスト用に前述の項目を用意できない場合は、[Cloud Identity Lab](https://github.com/Rainier-MSFT/Cloud_Identity_Lab) のスクリプトを使用して、AD ドメイン環境を Azure にデプロイできます。 [Demo Suite](https://github.com/jeevanbisht/DemoSuite) でスクリプト化された自動化を使用して、サンプル テスト アプリケーションを IIS Web ホストにプログラムでデプロイできます。

注

このチュートリアルの一部の手順は、Microsoft Entra 管理センター のレイアウトとは異なる場合があります。

### Azure のデプロイ

BIG-IP はさまざまなトポロジでデプロイできます。 このガイドでは、ネットワーク インターフェイス カード (NIC) のデプロイを中心に説明します。 しかし、BIG-IP のデプロイで、高可用性、ネットワーク分離、または 1 GB を超えるスループットを実現するために複数のネットワーク インターフェイスが必要な場合は、F5 のプリコンパイルされた [Azure Resource Manager (ARM) テンプレート](https://clouddocs.f5.com/cloud/public/v1/azure/Azure_multiNIC.html)の使用を検討してください。

[Azure Marketplace](https://azuremarketplace.microsoft.com/marketplace/apps) から BIG-IP VE をデプロイするには

1. アプリケーション管理者などの VM を作成するためのアクセス許可を持つアカウントを使用して、[Microsoft Entra管理センター](https://entra.microsoft.com)にサインインします。
2. 上部のリボンの検索ボックスに、「**marketplace**」と入力します
3. **[Enter]** を選択します。
4. Marketplace のフィルターに「**F5**」と入力します。
5. **[Enter]** を選択します。
6. 上部のリボンで、**[+ 追加]** を選択します。
7. Marketplace フィルターに「**F5**」と入力します。
8. **[Enter]** を選択します。
9. **[F5 BIG-IP Virtual Edition (BYOL)]**&gt;**[ソフトウェア プランの選択]**&gt;**[F5 BIG-IP VE - ALL (BYOL, 2 Boot Locations)]** の順に選択します。
10. **［作成］** を選択します

    [Image: ソフトウェア プランの選択のスクリーンショット。]
11. **[基本]** の場合:

- **サブスクリプション**: BIG-IP VM デプロイのターゲット サブスクリプション
- **リソース グループ**: BIG-IP VM がデプロイされる Azure RG、または新規に作成します。 これは DC と IIS VM のリソース グループです

1. **[インスタンスの詳細]** の場合:

- **VM 名**: たとえば、BIG-IP-VM
- **リージョン**: BIG-IP-VM のターゲット Azure geo
- **可用性オプション**: 運用環境で VM を使用する場合に有効にします
- **イメージ**: F5 BIG-IP VE - ALL (BYOL, 2 Boot Locations)
- **Azure Spot インスタンス**: いいえ。ただし、必要に応じて有効にします
- **サイズ**: 最小仕様は 2 vCPU と 8 GB メモリです

1. **[管理者アカウント]** の場合:

- **認証の種類**: ここではパスワードを選択し、後でキー ペアに切り替えます
- **ユーザー名**: 管理インターフェイスにアクセスするために BIG-IP のローカル アカウントとして作成される ID。 ユーザー名の大文字と小文字は区別されます。
- **パスワード**: 強力なパスワードを使用して管理者アクセスをセキュリティで保護します

1. **受信ポートの規則**: パブリック受信ポート、なし。
2. **ディスク** を選択します。 既定値のままにします。
3. **[次へ: ネットワーク]** を選択します。
4. **[ネットワーク]** の場合:

- **仮想スイッチ**: お使いの DC および IIS VM で使用されている Azure VNet、または新規に作成します
- **サブネット**: お使いの DC および IIS VM と同じ Azure 内部サブネット、または新規に作成します
- **パブリック IP**: なし
- **NIC ネットワーク セキュリティ グループ**: 選択した Azure サブネットがネットワーク セキュリティ グループ (NSG) に関連付けられている場合は、[なし] を選択します。それ以外の場合は [Basic] を選択します
- **ネットワークの高速化**: オフ

1. **[負荷分散]** の場合: VM の負荷分散、いいえ。
2. **[次へ: 管理]** を選択し、設定を完了します。

- **詳細な監視**: オフ
- **ブート診断** カスタム ストレージ アカウントで有効にします。 この機能により、Microsoft Entra 管理センター の [シリアル コンソール] オプションを使用して、BIG-IP Secure Shell (SSH) インターフェイスに接続できるようになります。 使用可能な Azure ストレージ アカウントを選択します。

1. **[ID]** の場合:

- **システム割り当てマネージド ID**: オフ
- **Microsoft Entra ID**: BIG-IP はこのオプションをサポートしていません

1. **[自動シャットダウン]** の場合: 有効にするか、テストする場合は、BIG-IP-VM を毎日シャットダウンするように設定できます
2. **[次へ: 詳細設定]** を選択します。既定値のままにします。
3. **タグ**を選択します。
4. BIG-IP-VM の構成を確認するには、**[次へ: 確認と作成]** を選択します。
5. **［作成］** を選択します BIG-IP VM がデプロイされるまでの時間は、通常 5 分です。
6. 完了したら、Microsoft Entra 管理センターの左側のメニューを展開します。
7. **[リソース グループ]** を選択し、BIG-IP-VM に移動します。

注

VM の作成に失敗した場合は、 **[戻る]** 、 **[次へ]** の順に選択します。

### ネットワーク構成

BIG-IP VM の起動時に、接続先の Azure サブネットの動的ホスト構成プロトコル (DHCP) サービスによって発行された**プライマリ** プライベート IP を使用して、その NIC がプロビジョニングされます。 BIG-IP の Traffic Management Operating System (TMOS) では、IP を使用して次の通信を行います。

- ホストとサービス
- パブリック インターネットへの送信アクセス
- BIG-IP Web 構成および SSH 管理インターフェイスへの受信アクセス

管理インターフェイスをインターネットに公開すると、BIG-IP の攻撃対象領域が増えます。 このリスクは、デプロイ中に BIG-IP プライマリ IP がパブリック IP でプロビジョニングされなかったことによるものです。 代わりに、セカンダリ内部 IP、および関連するパブリック IP が公開用にプロビジョニングされます。 VM のパブリック IP とプライベート IP を 1 対 1 でマッピングすると、外部トラフィックが VM に到達できるようになります。 しかし、ファイアウォールと同じようにトラフィックを許可するには、Azure NSG 規則が必要です。

以下の図は、Azure での BIG-IP VE の NIC デプロイを示しています。一般的な操作と管理用のプライマリ IP を使用して構成されています。 サービスの公開用の別の仮想サーバー IP があります。 NSG 規則では、`intranet.contoso.com` 宛てのリモート トラフィックを公開されたサービスのパブリック IP にルーティングしてから、BIG-IP 仮想サーバーに転送することが許可されます。

[Image: 単一の NIC デプロイの図。]

既定では、Azure VM に発行されるプライベートおよびパブリック IP は動的であるため、VM の再起動時に変更される可能性があります。 BIG-IP 管理 IP を静的に変更することで、接続の問題を回避します。 サービスを公開するには、セカンダリ IP に対して同じアクションを実行します。

1. BIG-IP VM のメニューから、**[設定]**&gt;**[ネットワーク]** の順に移動します。
2. ネットワーク ビューで、**[ネットワーク インターフェイス]** の右側にあるリンクを選択します。

    [Image: ネットワーク構成のスクリーンショット。]

注

VM 名はデプロイ時にランダムに生成されます。

1. 左側のペインで、**[IP 構成]** を選択します。
2. **[ipconfig1]** 行を選択します。
3. **[IP 割り当て]** オプションを **[静的]**に設定します。 必要に応じて、BIG-IP VM のプライマリ IP アドレスを変更します。
4. **[保存]** を選択します。
5. **[ipconfig1]** メニューを閉じます。

注

プライマリ IP を使用して、BIG-IP-VM の接続と管理を行います。

1. 上部のリボンで、**[+ 追加]** を選択します。
2. セカンダリ プライベート IP 名 (ipconfig2 など) を指定します。
3. プライベート IP アドレス設定では、**[割り当て]** オプションを **[静的]** に設定します。 1 つ上または下の IPを指定すると、秩序を保つのに役立ちます。
4. [パブリック IP アドレス] を **[関連付け]** に設定します。
5. **［作成］** を選択します
6. 新しいパブリック IP アドレスでは、名前 (たとえば、BIG-IP-VM\_ipconfig2\_Public) を指定します。
7. メッセージが表示されたら、**[SKU]** を **[Standard]** に設定します。
8. メッセージが表示されたら、**[階層]** を **[グローバル]** に設定します。
9. **[割り当て]** オプションを **[静的]**に設定します。
10. **[OK]** を 2 回選びます。

BIG-IP-VM は次の準備ができています。

- **プライマリ プライベート IP**:Web 構成ユーティリティと SSH を使用して BIG-IP-VM を管理する場合にのみ使用されます。 これは、BIG-IP システムで、公開されているバックエンド サービスに接続するためのセルフ IP として使用されます。 次の外部サービスに接続されます。
    - ネットワーク タイム プロトコル (NTP)
    - Active Directory (AD)
    - ライトウェイト ディレクトリ アクセス プロトコル (LDAP)
- **セカンダリ プライベート IP**: BIG-IP APM 仮想サーバーを作成し、公開されたサービスへの受信要求をリッスンするために使用します
- **パブリック IP**: セカンダリ プライベート IP に関連付けられています。パブリック インターネットからのクライアント トラフィックが公開されたサービスの BIG-IP 仮想サーバーに到達できるようになります

この例は、VM のパブリックおよびプライベート IP の間の 1 対 1 の関係を示しています。 Azure VM NIC には 1 つのプライマリ IP があり、その他の IP はセカンダリです。

注

BIG-IP サービスを公開するには、セカンダリ IP のマッピングが必要です。

[Image: [IP 構成] のスクリーンショット。]

BIG-IP のアクセス ガイド付き構成を使用して SHA を実装するには、手順を繰り返して、BIG-IP APM 経由で公開するサービスに対してさらにプライベートおよびパブリック IP のペアを作成します。 BIG-IP の詳細構成を使用してサービスを公開する場合も同じ方法を使用します。 ただし、[Server Name Indicator (SNI)](https://support.f5.com/csp/#/article/K13452) 構成を使用してパブリック IP オーバーヘッドを回避してください。BIG-IP 仮想サーバーでは、受信したクライアント トラフィックを受け入れ、それをその宛先に送信します。

### DNS の構成

公開した SHA サービスを BIG-IP-VM パブリック IP に解決するには、クライアントの DNS を構成します。 次の手順では、SHA サービスのパブリック ドメイン DNS ゾーンが Azure で管理されていることを前提としています。 DNS ゾーンがどこで管理されていても、ロケーターの作成については DNS の原則を適用します。

1. ポータルの左側のメニューを展開します。
2. **[リソース グループ]** オプションを使用して、BIG-IP-VM に移動します。
3. BIG-IP VM のメニューから、**[設定]**&gt;**[ネットワーク]** の順に移動します。
4. BIG-IP-VM のネットワーク ビューで、[IP 構成] ドロップダウン リストから、最初のセカンダリ IP を選択します。
5. **[NIC パブリック IP]** リンクを選択します。

    [Image: NIC パブリック IP のスクリーンショット。]
6. 左側のペインで、**[設定]** セクションの下にある **[構成]** を選択します。
7. パブリック IP と DNS プロパティのメニューが表示されます。
8. エイリアス レコードを選択して**作成**します。
9. ドロップダウン メニューから、ご利用の **DNS ゾーン**を選択します。 DNS ゾーンがない場合は、Azure の外部で管理することも、ドメイン サフィックス用に作成して Microsoft Entra ID で確認することもできます。
10. 最初の DNS エイリアス レコードを作成するには:

    - **サブスクリプション**: BIG-IP-VM と同じサブスクリプション
    - **DNS ゾーン**: 公開された Web サイトが使用する検証済みドメイン サフィックスに対して権限のある DNS ゾーン (例: `www.contoso.com`
    - **名前**: 指定したホスト名は、選択したセカンダリ IP に関連付けられているパブリック IP に解決されます。 DNS から IP へのマッピングを定義します たとえば、intranet.contoso.com から 11.22.333.444 へのマッピングなどです
    - **TTL**: 1
    - **TTL 単位**: 時間
11. **［作成］** を選択します
12. **DNS 名ラベル (省略可能)** はそのままにします。
13. **[保存]** を選択します。
14. [パブリック IP] メニューを閉じます。

注

BIG-IP のガイド付き構成を使用して公開するサービスの追加の DNS レコードを作成するには、手順 1 から 6 を繰り返します。

DNS レコードを作成したら、[DNS checker](https://dnschecker.org/) などのツールを使用して、作成したレコードがグローバル パブリック DNS サーバーに伝達されたことを確認できます。 [GoDaddy](https://www.godaddy.com/) のような外部プロバイダーを使用して DNS ドメイン名前空間を管理する場合は、DNS 管理機能を使用してレコードを作成します。

注

DNS レコードをテストして頻繁に切り替える場合は、PC のローカル hosts ファイルを使用できます。**Win** + **R** キーを押します。**[ファイル名を指定して実行]** ボックスに、「**drivers**」と入力します。 ローカル の host レコードでは、他のクライアントではなく、ローカル PC の DNS 解決が提供されます。

### クライアント トラフィック

既定では、Azure 仮想ネットワーク (VNet) および関連するサブネットは、インターネット トラフィックを受信できないプライベート ネットワークです。 デプロイ時に指定された NSG に BIG-IP-VM NIC をアタッチします。 外部 Web トラフィックが BIG-IP-VM に到達するようにするには、パブリック インターネットからポート 443 (HTTPS) および 80 (HTTP) へのアクセスを許可する受信 NSG 規則を定義します。

1. BIG-IP VM のメイン **[概要]** メニューから、**[ネットワーク]** を選択します。
2. **[受信規則の追加]** を選択します。
3. NSG 規則のプロパティを入力します。

- **送信元**: 任意
- **ソース ポート範囲**: \*|
- **宛先 IP アドレス**: BIG-IP-VM セカンダリ プライベート IP のコンマ区切りリスト
- **宛先ポート**: 80、443
- **Protocol**:TCP
- **アクション**: 許可
- **優先度**: 100 から 4096 までの使用可能な最小値
- **名前**: わかりやすい名前 (たとえば、`BIG-IP-VM_Web_Services_80_443`)

1. **[追加]** を選択します。
2. **[ネットワーク]** メニューを閉じます。

HTTP および HTTPS トラフィックは、BIG-IP-VM のセカンダリ インターフェイスに到達できます。 ポート 80 を許可すると、BIG-IP APM でユーザーを HTTP から HTTPS に自動的にリダイレクトできます。 この規則を編集して、宛先 IP を追加または削除します。

### BIG-IP の管理

BIG-IP システムは、Web 構成 UI を使用して管理されます。 次の場所から UI にアクセスします。

- BIG-IP の内部ネットワーク内のマシン
- BIG-IP-VM の内部ネットワークに接続された VPN クライアント
- [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)を介して発行済み

注

残りの構成に進む前に、前述の 3 つの方法のいずれかを選択します。 必要に応じて、インターネットから Web 構成に直接接続します。そのためには、パブリック IP を使用して BIG-IP のプライマリ IP を構成します。 その後、そのプライマリ IP への 8443 トラフィックを許可する NSG 規則を追加します。 ソースは独自の信頼された IP に限定してください。そうしないと、だれでも接続できるようになります。

#### 接続を確認する

BIG-IP VM の Web 構成に接続し、VM のデプロイ時に指定した資格情報でログインできることを確認します。

- 内部ネットワーク上の VM から、または VPN 経由で接続する場合は、BIG-IP のプライマリ IP と Web 構成ポートに接続します。 たとえば、「 `https://<BIG-IP-VM_Primary_IP:8443` 」のように入力します。 ブラウザー プロンプトで、接続が安全でないと示される場合があります。 BIG-IP が構成されるまで、プロンプトを無視します。 ブラウザーによってアクセスがブロックされる場合は、そのキャッシュをクリアして、もう一度やり直してください。
- アプリケーション プロキシ経由で Web 構成を公開した場合は、外部から Web 構成にアクセスするために定義された URL を使用します。 ポート (`https://big-ip-vm.contoso.com` など) を追加しないでください。 内部 URL は、Web 構成ポート (`https://big-ip-vm.contoso.com:8443` など) を使用して定義します。

注

SSH 環境を使用して BIG-IP システムを管理できます。通常は、コマンドライン (CLI) タスクとルートレベルのアクセスに使用されます。

CLI に接続するには:

- [Azure Bastion サービス](https://learn.microsoft.com/ja-jp/azure/bastion/bastion-overview): 任意の場所から、VNet 内の VM に接続します
- Just-In-Time (JIT) アプローチを使用した PowerShell などの SSH クライアント
- シリアル コンソール: ポータルの [VM] メニューの [サポートとトラブルシューティング] セクション。 ファイル転送はサポートされません。
- インターネットから: パブリック IP を使用して BIG-IP プライマリ IP を構成します。 SSH トラフィックを許可する NSG 規則を追加します。 信頼された IP ソースを制限します。

### BIG-IP ライセンス

サービスと SHA を公開するように構成する前に、APM モジュールを使用して BIG-IP システムをアクティブ化してプロビジョニングします。

1. Web 構成にサインインします。
2. **[全般プロパティ]** ページで、**[アクティブ化]** を選択します。
3. **[基本登録キー]** フィールドに、F5 で提供された大文字と小文字を区別するキーを入力します。
4. **[アクティブ化の方法]** は **[自動]** に設定したままにします。
5. **[次へ]** を選択します。
6. BIG-IP ではライセンスが検証され、エンドユーザー使用許諾契約 (EULA) が表示されます。
7. **[同意する]** を選択し、アクティブ化が完了するのを待ちます。
8. **[続行]** をクリックします。
9. [ライセンスの概要] ページの下部で、サインインします。
10. **[次へ]** を選択します。
11. SHA に必要なモジュールのリストが表示されます。

注

リストが表示されない場合は、メイン タブで **[システム]**&gt;**[リソースのプロビジョニング]** の順に移動します。 アクセス ポリシー (APM) のプロビジョニング列を確認します

[Image: アクセス プロビジョニングのスクリーンショット。]

1. **[Submit](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/送信)** をクリックします。
2. 警告を受け入れます。
3. 初期化が完了するまで待ちます。
4. **[続行]** をクリックします。
5. **[バージョン情報]** タブで、**[セットアップ ユーティリティの実行]** を選択します。

重要

F5 ライセンスは、1 つの BIG-IP VE インスタンス用です。 あるインスタンスから別のものにライセンスを移行する場合は、AskF5 の記事「[K41458656: 別の BIG-IP VE システムでの BIG-IP VE ライセンスの再利用](https://support.f5.com/csp/article/K41458656)」を参照してください。 ライセンスを使用停止にする前に、アクティブなインスタンスの試用版ライセンスを取り消します。そうしないと、ライセンスは完全に失われます。

### BIG-IP のプロビジョニング

BIG-IP Web 構成との間の管理トラフィックをセキュリティで保護することが重要です。Web 構成チャネルを侵害から保護するのに役立つように、デバイス管理証明書を構成します。

1. 左側のナビゲーション バーから、**[システム]**&gt;**[証明書の管理]**&gt;**[トラフィック証明書の管理]**&gt;**[SSL 証明書の一覧]**&gt;**[インポート]** の順に移動します。
2. **[インポートの種類]** ドロップダウン リストから、**[PKCS 12 (IIS)]** および **[ファイルの選択]** を選びます。
3. 後で BIG-IP-VM を割り当てる、FQDN に対応するサブジェクト名または SAN を持つ SSL Web 証明書を見つけます。
4. 証明書のパスワードを指定します。
5. **[インポート]** を選択します。
6. 左側のナビゲーション バーから、**[システム]**&gt;**[プラットフォーム]** の順に移動します。
7. [全般プロパティ] に、修飾された **[ホスト名]** と環境の **[タイム ゾーン]** を入力します。

    [Image: [全般プロパティ] のスクリーンショット。]
8. **[更新]** を選択します。
9. 左側のナビゲーション バーから、**[システム]**&gt;**[構成]**&gt;**[デバイス]**&gt;**[NTP]** の順に移動します。
10. NTP ソースを指定します。
11. **[追加]** を選択します。
12. **[更新]** を選択します。 たとえば、`time.windows.com` のように指定します。

前の手順で指定した、BIG-IP の FQDN をそのプライマリ プライベート IP に解決するための DNS レコードが必要です。 環境の内部 DNS、または PC localhost ファイルにレコードを追加して、BIG-IP Web 構成に接続します。Web 構成に接続すると、アプリケーション プロキシやその他のリバース プロキシが使用されず、ブラウザーの警告が表示されなくなります。

### SSL プロファイル

リバース プロキシとしての BIG-IP システムは、透過プロキシとも呼ばれる転送サービスか、またはクライアントとサーバー間のやり取りに関与するフル プロキシです。 フル プロキシでは、フロントエンド TCP クライアント接続とバックエンド TCP サーバー接続という 2 つの接続が作成され、中間にソフト ギャップがあります。 クライアントは一方の端でプロキシ リスナーに接続し、プロキシによってバックエンド サーバーに対する個別の独立した接続が確立されます。 この構成は両方の側で双方向です。 このフル プロキシ モードでは、F5 BIG-IP システムでトラフィックを検査でき、要求と応答によるやり取りが可能です。 負荷分散や Web パフォーマンスの最適化などの機能と、高度なトラフィック管理サービス (アプリケーション層セキュリティ、Web アクセラレーション、ページ ルーティング、セキュリティで保護されたリモート アクセス) はこの機能に依存しています。 SSL ベースのサービスを公開すると、BIG-IP SSL プロファイルで、クライアントとバックエンド サービス間のトラフィックの暗号化解除と暗号化が処理されます。

プロファイルには次の 2 種類があります。

- **クライアント SSL**: このプロファイルの作成は、SSL を使用して内部サービスを公開する BIG-IP システムを設定する最も一般的な方法です。 クライアント SSL プロファイルを使用すると、BIG-IP システムによって受信クライアント要求の暗号化が解除されてから、ダウンストリーム サービスに送信されます。 送信バックエンド応答は暗号化されてから、クライアントに送信されます。
- **サーバー SSL**: HTTPS 用に構成されたバックエンド サービスでは、サーバー SSL プロファイルを使用するように BIG-IP を構成できます。 このプロファイルを使用すると、BIG-IP によってクライアント要求が再暗号化され、宛先バックエンド サービスに送信されます。 サーバーから暗号化された応答が返されると、BIG-IP システムでは、構成済みのクライアント SSL プロファイルを使用して、応答の暗号化を解除および再暗号化してからクライアントに送信します。

BIG-IP を事前に構成し、SHA シナリオに対応できるようするには、クライアントとサーバーの SSL プロファイルをプロビジョニングします。

1. 左側のナビゲーションから、**[システム]**&gt;**[証明書の管理]**&gt;**[トラフィック証明書の管理]**&gt;**[SSL 証明書の一覧]**&gt;**[インポート]** の順に移動します。
2. **[インポートの種類]** ドロップダウン リストから、**[PKCS 12 (IIS)]** を選択します。
3. インポートされた証明書の場合は、`ContosoWildcardCert` などの名前を入力します。
4. **[ファイルの選択]** を選びます。
5. 公開されたサービスのドメイン サフィックスに対応するサブジェクト名を持つ SSL Web 証明書を参照します。
6. インポートされた証明書の場合は、**[パスワード]** を指定します。
7. **[インポート]** を選択します。
8. 左側のナビゲーションから、**[ローカル トラフィック]**&gt;**[プロファイル]**&gt;**[SSL]**&gt;**[クライアント]** の順に移動します。
9. **［作成］** を選択します
10. **[新しいクライアント SSL プロファイル]** ページで、一意のわかりやすい **[名前] ** を入力します。
11. [親プロファイル] が **clientssl** に設定されていることを確かめます。

    [Image: [名前] と [親プロファイル] の選択のスクリーンショット。]
12. **[証明書キー チェーン]** 行で、右端のチェック ボックスをオンにします。
13. **[追加]** を選択します。
14. **[証明書]**、**[キー]**、**[チェーン]** ドロップダウン リストから、パスフレーズを使用せずにインポートしたワイルドカード証明書を選択します。
15. **[追加]** を選択します。
16. **[完了]** を選択します。

    [Image: 証明書、キー、チェーンの選択のスクリーンショット。]
17. 手順を繰り返して、**SSL サーバー証明書プロファイル**を作成します。
18. 上部のリボンから **[SSL]**&gt;**[サーバー]**&gt;**[作成]** を選択します。
19. **[新しいサーバー SSL プロファイル]** ページで、一意のわかりやすい **[名前]** を入力します。
20. [親プロファイル] が **serverssl** に設定されていることを確かめます。
21. **[証明書]** および **[キー]** 行の右端にあるチェック ボックスをオンにします
22. **[証明書]** および **[キー]** ドロップダウン リストから、インポートした証明書を選択します。
23. **[完了]** を選択します。

    [Image: [全般プロパティ] と [構成] の選択のスクリーンショット。]

注

SSL 証明書を調達できない場合は、統合された自己署名済みの BIG-IP サーバーとクライアント SSL 証明書を使用します。 ブラウザーに証明書エラーが表示されます。

#### リソースを見つける

SHA 用に BIG-IP を準備するには、公開しているリソースと、SSO で利用するディレクトリ サービスを見つけます。 BIG-IP には名前解決のソースが 2 つあります。最初は local/.../hosts ファイルが使用されます。 レコードが見つからない場合、BIG-IP システムでは構成時に使用された DNS サービスが使われます。 hosts ファイルの方法は、FQDN を使用する APM ノードおよびプールには適用されません。

1. Web 構成で、**[システム]**&gt;**[構成]**&gt;**[デバイス]**&gt;**[DNS]** の順に移動します。
2. **[DNS 参照サーバーの一覧]** で、ご利用の環境の DNS サーバーの IP アドレスを入力します。
3. **[追加]** を選択します。
4. **[更新]** を選択します。

オプションの手順は、ローカルの BIG-IP アカウントを管理するのではなく、BIG-IP のシステム管理者を Active Directory に対して認証する [LDAP 構成](https://somoit.net/f5-big-ip/authentication-using-active-directory)です。

### BIG-IP の更新

更新関連のガイダンスについては、以下のリストを参照してください。 その下に更新手順が示されています。

- TMOS (Traffic Management Operating System) のバージョンを確認するには:
    - メイン ページの左上にある BIG-IP ホスト名の上にカーソルを置きます
- v15.x 以降を実行します。 [F5 のダウンロード](https://downloads.f5.com/esd/productlines.jsp)を参照してください。 サインインが必要です。
- メイン TMOS を更新する場合は、F5 の記事「[K34745165: BIG-IP システムでのソフトウェア イメージの管理](https://support.f5.com/csp/article/K34745165)」を参照してください
    - メイン TMOS を更新できない場合は、ガイド付き構成をアップグレードできます。 次の手順に従います。
- [シナリオベースのガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)も参照してください

1. BIG-IP Web 構成のメイン タブで、**[アクセス]**&gt;**[ガイド付き構成]** の順に移動します。
2. **[ガイド付き構成]** ページで、**[ガイド付き構成のアップグレード]** を選択します。

    [Image: [ガイド付き構成] ページのスクリーンショット。]
3. **[ガイド付き構成のアップグレード]** ダイアログで、**[ファイルの選択]** を選びます。
4. **[アップロードとインストール]** を選択します。
5. アップグレードが完了するまで待ちます。
6. **[続行]** をクリックします。

### BIG-IP をバックアップする

BIG-IP システムがプロビジョニングされている場合は、完全構成バックアップをお勧めします。

1. **[システム]**&gt;**[アーカイブ]**&gt;**[作成]** の順に移動します。
2. 一意の **[ファイル名]** を指定します。
3. パスフレーズによる **[暗号化]** を有効にします。
4. デバイスおよび SSL 証明書をバックアップするため、**[秘密キー]** オプションを **[含める]** に設定します。
5. **[完了]** を選択します。
6. この処理が完了するまで待ちます。
7. 結果を含むメッセージが表示されます。
8. **[OK]** を選択します。
9. バックアップ リンクを選択します。
10. ユーザー構成セット (UCS) アーカイブをローカルに保存します。
11. **[Download]** を選択します。

[Azure スナップショット](https://learn.microsoft.com/ja-jp/azure/virtual-machines/snapshot-copy-managed-disk)を使用して、システム ディスク全体のバックアップを作成できます。 このツールは、TMOS のバージョン間のテストや新しいシステムへのロールバックを行うための代替手段になります。

```PowerShell
# Install modules
Install-module Az
Install-module AzureVMSnapshots

# Authenticate to Azure
Connect-azAccount

# Set subscription by Id
Set-AzContext -SubscriptionId ‘<Azure_Subscription_ID>’

#Create Snapshot
New-AzVmSnapshot -ResourceGroupName '<E.g.contoso-RG>' -VmName '<E.g.BIG-IP-VM>'

#List Snapshots
#Get-AzVmSnapshot -ResourceGroupName ‘<E.g.contoso-RG>'

#Get-AzVmSnapshot -ResourceGroupName ‘<E.g.contoso-RG>' -VmName '<E.g.BIG-IP-VM>' | Restore-AzVmSnapshot -RemoveOriginalDisk 

```

### BIG-IP の復元

BIG-IP の復元はバックアップ プロセスに似ており、BIG-IP VM 間で構成を移行するために使用できます。 バックアップをインポートする前に、サポートされているアップグレード パスを確認します。

1. **[システム]**&gt;**[アーカイブ]** の順に移動します。

- バックアップ リンクを選択します。**または**
- [アップロード] を選択し、リストにない保存済みの UCS アーカイブを参照します

1. バックアップ パスフレーズを指定します。
2. **[復元]** を選択します

```PowerShell
# Authenticate to Azure
Connect-azAccount

# Set subscription by Id
Set-AzContext -SubscriptionId ‘<Azure_Subscription_ID>’

#Restore Snapshot
Get-AzVmSnapshot -ResourceGroupName '<E.g.contoso-RG>' -VmName '<E.g.BIG-IP-VM>' | Restore-AzVmSnapshot

```

注

現在、AzVmSnapshot コマンドレットでは、日付に基づいて最新のスナップショットを復元できます。 スナップショットは、VM リソース グループ ルートに格納されます。 スナップショットを復元すると Azure VM が再起動されるため、タスクに最適なタイミングを確保してください。

### リソース

- [Reset BIG-IP VE password in Azure (Azure での BIG-IP VE パスワードのリセット)](https://clouddocs.f5.com/cloud/public/v1/shared/azure_passwordreset.html)
- [Reset the password without using the portal (ポータルを使用せずにパスワードをリセットする)](https://clouddocs.f5.com/cloud/public/v1/shared/azure_passwordreset.html#reset-the-password-without-using-the-portal)
- [Change the NIC used for BIG-IP VE management (BIG-IP VE の管理に使用する NIC の変更)](https://clouddocs.f5.com/cloud/public/v1/shared/change_mgmt_nic.html)
- [About routes in a single NIC configuration (単一 NIC 構成でのルートについて)](https://clouddocs.f5.com/cloud/public/v1/shared/routes.html)
- [Microsoft Azure:waagent](https://clouddocs.f5.com/cloud/public/v1/azure/Azure_waagent.html)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-integration"} -->
## F5 BIG-IP と Microsoft Entra ID と統合する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration
- Service: entra-id / enterprise-apps
- Article date: 2024-06-28
- Summary: F5 BIG-IP を Microsoft Entra ID と統合して、セキュリティで保護されたハイブリッド アクセス (SHA) を実現し、アクセスとセキュリティを強化します。

脅威の状況や複数のモバイル デバイスの使用が増加するにつれ、組織はリソースアクセスとガバナンスを再考しています。 最新化プログラムの一部には、ID、デバイス、アプリ、インフラストラクチャ、ネットワーク、データの準備状況の評価が含まれます。 [リモート作業を可能にするゼロ トラスト フレームワーク](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)とゼロ トラスト評価ツールについて学習できます。

デジタル変革は長期的な取り組みであり、潜在的に重要なリソースは最新化されるまで公開されます。 F5 BIG-IP と Microsoft Entra ID のセキュリティで保護されたハイブリッド アクセス (SHA) の目標は、オンプレミス アプリケーションへのリモート アクセスを改善し、脆弱なレガシ サービスのセキュリティ体制を強化することです。

調査では、オンプレミス アプリケーションの 60% から 80% がレガシであるか、Microsoft Entra ID と統合できないと推定されています。 同じ調査で、類似のシステムの大部分が、SAP、Oracle、SAGE、重要なサービス向けのその他の既知のワークロードの以前のバージョンで実行されていることが示されました。

SHA を使用すると、組織は F5 ネットワークとアプリケーション配信への投資を引き続き使用できます。 Microsoft Entra ID と共に SHA では、ID コントロール プレーンとの格差を埋めます。

### メリット

Microsoft Entra ID で BIG-IP 公開サービスへのアクセスを事前認証すると、次のような多くの利点があります。

- 次のものを使用したパスワードレス認証:
    - [Windows Hello for Business](https://learn.microsoft.com/ja-jp/windows/security/identity-protection/hello-for-business/)
    - [Microsoft Authenticator](https://support.microsoft.com/account-billing/download-and-install-the-microsoft-authenticator-app-351498fc-850a-45da-b7b6-27e523b8702a)
    - [Fast Identity Online (FIDO) キー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)
    - [証明書ベースの認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-certificate-based-authentication)

他にも次のようなメリットがあります。

- ID とアクセスを管理する 1 つのコントロール プレーン
    - [Microsoft Entra 管理センター](https://entra.microsoft.com)
- プリエンプティブな[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- [Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)
- ユーザーとセッションのリスク プロファイルを使用した適応型保護
    - [Microsoft Entra ID Protection](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)
- [セルフサービス パスワード リセット (SSPR)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)
- 管理対象ゲスト アクセスのエンタイトルメント管理
    - [パートナー コラボレーション](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-external-users)
- アプリの検出と制御
    - [Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/what-is-defender-for-cloud-apps)
- [Microsoft Sentinel](https://azure.microsoft.com/services/azure-sentinel/) を使用した脅威の監視と分析

### シナリオの説明

アプリケーション デリバリー コントローラー (ADC) および Secure Socket Layer 仮想プライベート ネットワーク (SSL-VPN) としての BIG-IP システムでは、次に示すようなサービスへのローカルおよびリモート アクセスを提供します。

- 最新の Web アプリケーションとレガシ Web アプリケーション
- Web ベースではないアプリケーション
- Representational State Transfer (REST) および簡易オブジェクト アクセス プロトコル (SOAP) Web アプリケーション プログラミング インターフェイス (API) サービス

BIG-IP Local Traffic Manager (LTM) はセキュリティで保護されたサービスの発行用ですが、Access Policy Manager (APM) では、ID フェデレーションとシングル サインオン (SSO) を有効にする BIG-IP 機能を拡張します。

統合により、次のような制御を使って、セキュリティで保護されたレガシ サービスまたは他の統合サービスへのプロトコル移行を実現します。

- [パスワードレスの認証](https://www.microsoft.com/security/business/identity/passwordless)
- [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)

このシナリオでは、BIG-IP はサービスの事前認証と認可を Microsoft Entra ID に引き渡すリバース プロキシです。 統合は、APM と Microsoft Entra ID 間の標準のフェデレーション信頼に基づいています。 このシナリオは SHA で一般的です。 チュートリアル: [Microsoft Entra SSO 用に F5 BIG-IP SSL-VPN を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-passwordless-vpn)。 SHA を使用すると、Security Assertion Markup Language (SAML)、Open Authorization (OAuth)、Open ID Connect (OIDC) リソースをセキュリティで保護できます。

注

ローカルおよびリモート アクセスに使用すると、BIG-IP を、サービスとしてのソフトウェア (SaaS) アプリを含むサービスへのゼロ トラスト アクセスのチョークポイントにすることができます。

次の図は、サービス プロバイダー (SP) で開始したフローにおける、ユーザー、BIG-IP、Microsoft Entra ID 間のフロントエンドの事前認証のやり取りを示しています。 その後は、後続の APM セッション エンリッチメントと、個々のバックエンド サービスへの SSO を示しています。

[Image: 統合アーキテクチャの図。]

1. ユーザーがポータルでアプリケーション アイコンを選び、URL が SAML SP (BIG IP) に解決される
2. BIG-IP によって、事前認証のためにユーザーが SAML ID プロバイダー (IdP) である Microsoft Entra ID にリダイレクトされる
3. Microsoft Entra ID が、承認のために条件付きアクセス ポリシーと[セッション制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session)を処理する
4. ユーザーが BIG-IP に戻り、Microsoft Entra ID によって発行された SAML 要求を提示する
5. BIG-IP で、公開済みサービスへの [SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso) および[ロールベースのアクセス制御 (RBAC)](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview) に関するセッション情報を要求する
6. BIG-IP によって、クライアント要求がバックエンド サービスに転送される

### ユーザー側の表示と操作

従業員、関係者、コンシューマーのいずれであっても、ほとんどのユーザーは Office 365 のサインイン エクスペリエンスに精通しています。 BIG-IP サービスへのアクセスも同様です。

ユーザーは、デバイスや場所に関係なく、セルフサービス機能を使用して、[マイ アプリ ポータル](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)または [Microsoft 365 アプリ起動ツール](https://support.microsoft.com/office/meet-the-microsoft-365-app-launcher-79f12104-6fed-442f-96a0-eb089a3f476a)で BIG-IP 公開サービスを見つけることができます。 ユーザーは、BIG-IP Webtop ポータルを使用して、公開されたサービスに引き続きアクセスできます。 ユーザーがサインアウトすると、SHA では BIG-IP と Microsoft Entra ID のセッション終了が確実に行われるため、サービスが認可されていないアクセスから保護されたままになります。

ユーザーは、マイ アプリ ポータルにアクセスして BIG-IP で公開されているサービスを見つけて、アカウントのプロパティを管理します。 次のグラフィックのギャラリーとページを参照してください。

[Image: woodgrove マイ アプリ ページのスクリーンショット。]

### 分析情報と分析

デプロイされた BIG-IP インスタンスを監視して、公開されたサービスが SHA レベルと運用レベルで高可用性であることを保証できます。

ストレージとテレメトリの処理を可能にするセキュリティ情報イベント管理 (SIEM) ソリューションを使用して、イベントをローカルまたはリモートでログに記録するには、いくつかのオプションがあります。 Microsoft Entra ID と SHA のアクティビティを監視するには、[Azure Monitor](https://learn.microsoft.com/ja-jp/azure/azure-monitor/overview) と [Microsoft Sentinel](https://learn.microsoft.com/ja-jp/azure/sentinel/overview) を一緒に使用できます:

- 組織 (複数のクラウドにまたがる可能性もあり) とオンプレミスの場所の概要 (BIG-IP インフラストラクチャを含む)
- シグナルのビューを持つ 1 つのコントロール プレーン (複雑で多様なツールへの依存を回避)

    [Image: 監視フローの図。]

### 統合の前提条件

SHA を実装するために事前の経験や F5 BIG-IP に関する知識は必要ありませんが、F5 BIG-IP の用語を学習することをお勧めします。 F5 サービスの[用語集](https://www.f5.com/services/resources/glossary)を参照してください。

SHA 向けに F5 BIG-IP と Microsoft Entra ID を統合するには、以下の前提条件があります:

- 次のもので実行されている F5 BIG-IP インスタンス:

    - 物理アプライアンス
    - Microsoft Hyper-V、VMware ESXi、Linux カーネルベースの仮想マシン (KVM)、Citrix Hypervisor などのハイパーバイザー仮想エディション
    - Azure、VMware、KVM、Community Xen、MS Hyper-v、AWS、OpenStack、Google Cloud などのクラウド仮想エディション

    注

    BIG-IP インスタンスの場所は、オンプレミスまたは Azure を含むサポートされているクラウド プラットフォームにすることができます。 インスタンスにはインターネット接続があり、リソースが公開され、その他のサービスがあります。
- アクティブな F5 BIG-IP APM ライセンス:

    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP Access Policy Manager™ スタンドアロン ライセンス
    - 既存の BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - BIG-IP アクセス ポリシー マネージャー™ (APM) の 90 日間 [試用版ライセンス](https://www.f5.com/trial/big-ip-trial.php)
- Microsoft Entra ID ライセンス:

    - [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) には、パスワードレス認証を使用した SHA の最小コア要件があります
    - [プレミアム サブスクリプション](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) には、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)、[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)、および [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) があります

### 構成シナリオ

テンプレート ベースのオプションまたは手動構成を使用して、SHA 用の BIG-IP を構成できます。 下記のチュートリアルでは、BIG-IP と Microsoft Entra ID のセキュリティで保護されたハイブリッド アクセスの実装に関するガイダンスがあります。

#### 詳細な構成

高度なアプローチは、SHA を実装するための柔軟な方法です。 すべての BIG-IP 構成オブジェクトを手動で作成します。 ガイド付き構成テンプレートにないシナリオでは、このアプローチを使用します。

詳細な構成のチュートリアル:

- [F5 BIG-IP を使用した Azure のデプロイに関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)
- [Microsoft Entra SHA による F5 BIG-IP SSL-VPN](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-passwordless-vpn)
- [F5 BIG-IP APM と Microsoft Entra SSO から Kerberos アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-kerberos-advanced)
- [F5 BIG-IP APM と Microsoft Entra SSO から ヘッダーベース アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-header-advanced)
- [F5 BIG-IP APM と Microsoft Entra SSO から フォームベース アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-forms-advanced)

#### ガイド付き構成テンプレートと Easy Button テンプレート

BIG-IP バージョン 13.1 のガイド付き構成ウィザードを使用すると、一般的な BIG-IP 公開シナリオを実装するための時間と労力を最小限に抑えることができます。 ワークフローベースのフレームワークにより、特定のアクセス トポロジ用の直感的なデプロイ エクスペリエンスを利用できます。

ガイド付き構成バージョン 16.x には、Easy Button 機能があります。 管理者は、SHA のサービスを有効にするために Microsoft Entra ID と BIG-IP の間を行ったり来たりする必要はありません。 APM のガイド付き構成ウィザードと Microsoft Graph によって、デプロイとポリシー管理が処理されます。 BIG-IP APM と Microsoft Entra ID のこの統合により、アプリケーションでは確実に ID フェデレーション、SSO、Microsoft Entra 条件付きアクセスをサポートでき、アプリごとにこれを行う管理オーバーヘッドが発生しません。

Easy Button テンプレートを使用するためのチュートリアル、次のものに対する F5 BIG-IP Easy Button for SSO:

- [Kerberos アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-kerberos-easy-button)
- [ヘッダーベースのアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-headers-easy-button)
- [ヘッダー ベースおよびライトウェイト ディレクトリ アクセス プロトコル (LDAP) アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-ldap-header-easybutton)
- [Oracle Enterprise Business Suite (EBS)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-enterprise-business-suite-easy-button)
- [Oracle JD Edwards](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-jde-easy-button)
- [Oracle PeopleSoft](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button)
- [SAP エンタープライズ リソース プランニング (ERP)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-sap-erp-easy-button)

### Microsoft Entra B2B ゲスト アクセス

SHA で保護されたアプリケーションへの Microsoft Entra B2B ゲスト アクセスも可能ですが、チュートリアルにない手順が必要になる場合があります。 一例として、Kerberos SSO があります。この場合、BIG-IP では kerberos の制約付き委任 (KCD) を実行して、ドメイン コントローラーからサービス チケットを取得します。 ローカル ゲスト ユーザーのローカル表現がない場合、ユーザーが存在しないため、ドメイン コントローラーは要求を受け入れません。 このシナリオをサポートするには、外部 ID が Microsoft Entra テナントから、アプリケーションで使用されるディレクトリにフローダウンされることを確認します。

詳細情報: [Microsoft Entra ID の B2B ユーザーにオンプレミスのアプリケーションへのアクセスを許可する](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-passwordless-vpn"} -->
## Microsoft Entra ID で F5 BIG-IP SSL-VPN ソリューションを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-passwordless-vpn
- Service: entra-id / enterprise-apps
- Article date: 2024-04-19
- Summary: 安全なハイブリッド アクセス (SHA) 用に Microsoft Entra ID を使って F5 BIG-IP ベースの Secure Socket Layer 仮想プライベート ネットワーク (SSL-VPN) ソリューションを構成するチュートリアル。

このチュートリアルでは、セキュリティで保護されたハイブリッド アクセス (SHA) のために F5 BIG-IP に基づく Secure Socket Layer 仮想プライベート ネットワーク (SSL-VPN) と Microsoft Entra ID を統合する方法について学習します。

Microsoft Entra シングル サインオン (SSO) に対して BIG-IP SSL VPN を有効にすると、次のような多くの利点が得られます。

- Microsoft Entra 事前認証と条件付きアクセスによるゼロ トラスト ガバナンス。
    - [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- VPN サービスに対する[パスワードレス認証](https://www.microsoft.com/security/business/identity/passwordless)
- 単一のコントロール プレーンである [Microsoft Entra 管理センター](https://entra.microsoft.com)からの ID およびアクセス管理

その他の利点については、以下を参照してください。

- [F5 BIG-IP と Microsoft Entra ID の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- [Microsoft Entra ID での SSO](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)

    注

    従来の VPN はネットワーク指向のままであるため、多くの場合、企業アプリケーションへのきめ細かなアクセスがほとんどまたはまったく提供されません。 ゼロ トラストを実現するために、より ID 中心のアプローチをお勧めします。 詳細については、[すべてのアプリを Microsoft Entra ID と統合するための 5 つの手順](https://learn.microsoft.com/ja-jp/entra/fundamentals/five-steps-to-full-application-integration)に関するページを参照してください。

### シナリオの説明

このシナリオでは、SSL-VPN サービスの BIG-IP Access Policy Manager (APM) インスタンスは Security Assertion Markup Language (SAML) サービス プロバイダー (SP) として構成され、Microsoft Entra ID は信頼された SAML ID プロバイダー (IdP) となります。 Microsoft Entra ID からのシングル サインオン (SSO) は、BIG-IP APM への要求ベースの認証を通じて行われ、シームレスな仮想プライベート ネットワーク (VPN) アクセス エクスペリエンスを実現します。

[Image: 統合アーキテクチャの図。]

注

このガイドの文字列または値の例は、実際の環境のものに置き換えてください。

### 前提条件

F5 BIG-IP に関する事前の経験や知識は必要ありませんが、次のものが必要です。

- Microsoft Entra サブスクリプション
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/trial/get-started-active-directory/)を取得できます
- [オンプレミスのディレクトリから Microsoft Entra ID に同期済み](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)のユーザー ID
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者
- BIG-IP との間のクライアント トラフィック ルーティングを備えた BIG-IP インフラストラクチャ
    - または、[Azure に BIG-IP Virtual Edition を デプロイします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)
- パブリック ドメイン ネーム サーバー (DNS) 内の BIG-IP 公開 VPN サービスのレコード
    - または、テスト時におけるテスト クライアント localhost ファイル
- HTTPS 経由でサービスを公開するために必要な SSL 証明書を使用してプロビジョニングされた BIG-IP

チュートリアル エクスペリエンスを向上させるために、F5 BIG-IP の[用語集](https://www.f5.com/services/resources/glossary)で業界標準の用語を学習できます。

### Microsoft Entra ギャラリーから F5 BIG-IP を追加する

BIG-IP との間に SAML フェデレーション信頼を設定して、公開された VPN サービスへのアクセスを許可する前に、Microsoft Entra BIG-IP で事前認証と[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を Microsoft Entra ID に引き渡すことができるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**すべてのアプリケーション**を参照し、[**新しいアプリケーション**] を選択します。
3. ギャラリーで *F5* を検索し、**[F5 BIG-IP APM Microsoft Entra ID 統合]** を選びます。
4. アプリケーションの名前を入力します。
5. **[追加]**、**[作成]** の順に選択します。
6. 名前はアイコンとして、Microsoft Entra 管理センターとOffice 365 ポータルに表示されます。

### Microsoft Entra SSO の構成

1. F5 アプリケーションのプロパティが表示されている状態で、**[管理]**&gt;**[シングル サインオン]** の順に移動します。
2. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
3. **[いいえ、後で保存します]** を選択します。
4. **[Setup single sign-on with SAML] (SAML によるシングル サインオンのセットアップ)** メニューで **[基本的な SAML 構成]** のペン アイコンを選択します。
5. **[識別子 URL]** を、BIG-IP で公開されたサービス URL に置き換えます。 たとえば、「 `https://ssl-vpn.contoso.com` 」のように入力します。
6. **[応答 URL]** と SAML エンドポイント パスを置き換えます。 たとえば、「 `https://ssl-vpn.contoso.com/saml/sp/profile/post/acs` 」のように入力します。

    注

    この構成では、アプリケーションは IdP 開始モードで動作します。その場合、Microsoft Entra ID では、SAML アサーションを発行してから BIG-IP SAML サービスにリダイレクトします。
7. IdP 開始モードをサポートしていないアプリの場合、BIG-IP SAML サービスでは **[サインオン URL]** を指定します。たとえば、`https://ssl-vpn.contoso.com` のように指定します。
8. [ログアウト URL] には、公開するサービスのホスト ヘッダーが先頭に付いた BIG-IP APM シングル ログアウト (SLO) エンドポイントを入力します。 たとえば、`https://ssl-vpn.contoso.com/saml/sp/profile/redirect/slr` のように指定します。

    注

    SLO URL により、ユーザーがサインアウトした後に、BIG-IP と Microsoft Entra ID でユーザー セッションが確実に終了します。BIG-IP APM には、アプリケーション URL の呼び出し時にすべてのセッションを終了するオプションが用意されています。 詳細については、F5 の記事「[K12056: ログアウト URI インクルード オプションの概要 (K12056: Overview of the Logout URI Include option)](https://support.f5.com/csp/article/K12056)」を参照してください。

[Image: [基本的な SAML 構成] の URL のスクリーンショット。]

注

TMOS v16 以降、SAML SLO エンドポイントが /saml/sp/profile/redirect/slo に変更されました。

1. **[保存]** を選びます。
2. SSO テスト プロンプトをスキップします。
3. **[User Attributes & Claims]** (ユーザー属性とクレーム) プロパティで詳細を確認してください。

    [Image: ユーザー属性とクレームのプロパティを示すスクリーンショット。]

BIG-IP 公開サービスに他のクレームを追加できます。 既定のセットに加えて定義されたクレームは、Microsoft Entra ID に存在する場合に発行されます。 ディレクトリの[ロールまたはグループ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-group-claims)のメンバーシップをクレームとして発行するには、Microsoft Entra ID 内のユーザー オブジェクトに対してそれらを事前に定義します。

Microsoft Entra ID によって作成された SAML 署名証明書の有効期間は 3 年です。

#### Microsoft Entra 承認

既定では、Microsoft Entra ID で、サービスへのアクセスが許可されているユーザーにトークンが発行されます。

1. アプリケーション構成ビューで、**[ユーザーおよびグループ]** を選択します。
2. **[+ ユーザーの追加]** を選択します。
3. **[割り当ての追加]** メニューで **[ユーザーおよびグループ]** を選択します。
4. **[ユーザーおよびグループ]** ダイアログで、VPN へのアクセス権限を持つユーザー グループを追加します。
5. **[選択]**&gt;**[割り当てる]** を選択します。

    [Image: [ユーザーの追加] オプションのスクリーンショット。]

SSL-VPN サービスを公開するように BIG-IP APM を設定できます。 対応するプロパティを使ってこれを構成し、SAML 事前認証の信頼を完了します。

### BIG-IP APM 構成

#### SAML フェデレーション

Microsoft Entra ID で VPN サービスのフェデレーションを完了するには、BIG-IP SAML サービス プロバイダーと、対応する SAML IDP オブジェクトを作成します。

1. **[Access] (アクセス)**&gt;**[Federation] (フェデレーション)**&gt;**[SAML Service Provider] (SAML サービス プロバイダー)**&gt;**[Local SP Services] (ローカル SP サービス)** の順に移動します。
2. **［作成］** を選択します

    [Image: [Local SP Services] (ローカル SP サービス) ページの [Create] (作成) オプションのスクリーンショット。]
3. **[名前]** と、Microsoft Entra ID で定義した **[エンティティ ID]** を入力します。
4. アプリケーションに接続するホストの完全修飾ドメイン名 (FQDN) を入力します。

    [Image: 名前とエンティティの入力のスクリーンショット。]

    注

    エンティティ ID が、公開 URL のホスト名と完全に一致しない場合は、SP の **[Name](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/名前)** の設定を構成します。または、ホスト名 URL 形式でない場合に、このアクションを実行します。 エンティティ ID が `urn:ssl-vpn:contosoonline` の場合は、公開されているアプリケーションの外部スキームとホスト名を指定します。
5. 下にスクロールして、新しい **SAML SP オブジェクト**を選択します。
6. **[Bind/UnBind IDP Connectors] (IDP コネクタのバインドまたはバインド解除)** を選択します。

    [Image: [Local SP Services] (ローカル SP サービス) ページの [Bind/Unbind IDP Connectors] (IDP コネクタのバインドまたはバインド解除) オプションのスクリーンショット。]
7. **[Create New IDP Connector] (新しい IDP コネクタの作成)** を選択します。
8. ドロップダウン メニューから、**[From Metadata] (メタデータから)** を選択します。

    [Image: [Edit SAML IdPs] (SAML IdP の編集) ページの [From Metadata] (メタデータから) オプションのスクリーンショット。]
9. ダウンロードしたフェデレーション メタデータ XML ファイルを参照します。
10. APM オブジェクトの場合、外部 SAML IdP を表す **ID プロバイダー名**を指定します。
11. 新しい Microsoft Entra 外部 IdP コネクタを選ぶには、**[新しい行の追加]** を選択します。

    [Image: [Edit SAML IdPs] (SAML IdP の編集) ページの [SAML IdP Connectors] (SAML IdP コネクター) オプションのスクリーンショット。]
12. **[更新]** を選択します。
13. **[OK]** を選択します。

    [Image: [Edit SAML IdPs] (SAML IdP の編集) ページの Common/VPN Azure リンクのスクリーンショット。]

#### Web トップ構成

BIG-IP Web ポータルを使用してユーザーに SSL-VPN を提供できるようにします。

1. **[Access] (アクセス)**&gt;**[Webtops] (Web トップ)**&gt;**[Webtop Lists] (Web トップの一覧)** に移動します。
2. **［作成］** を選択します
3. ポータル名を入力します。
4. タイプを **[Full] (完全)** に設定します (例: `Contoso_webtop`)。
5. 残りの設定を完了します。
6. **[完了]** を選択します。

    [Image: [General Properties] (全般プロパティ) の名前とタイプの入力のスクリーンショット。]

#### VPN 構成

VPN 要素は、サービス全体の各側面を制御します。

1. **[Access] (アクセス)**&gt;**[Connectivity/VPN] (接続/VPN)**&gt;**[Network Access (VPN)] (ネットワーク アクセス (VPN))**&gt;**[IPV4 Lease Pools] (IPV4 リース プール)** の順に移動します。
2. **［作成］** を選択します
3. VPN クライアントに割り当てられた IP アドレス プールの名前を入力します。 たとえば、「Contoso\_VPN\_Pool」などです。
4. タイプを **[IP Address Range] (IP アドレス範囲)** に設定します。
5. 開始 と終了の IP を入力します。
6. **[追加]** を選択します。
7. **[完了]** を選択します。

    [Image: [General Properties] (全般プロパティ) の名前とメンバー リストの入力のスクリーンショット。]

ネットワーク アクセス リストでは、VPN プールの IP および DNS 設定とユーザー ルーティングのアクセス許可でサービスをプロビジョニングし、アプリケーションを起動できます。

1. **[Access] (アクセス)**&gt;**[Connectivity/VPN: Network Access (VPN)] (接続/VPN: ネットワーク アクセス (VPN))**&gt;**[Network Access Lists] (ネットワーク アクセスの一覧)** の順に移動します。
2. **［作成］** を選択します
3. VPN アクセス リストとキャプションの名前 (たとえば、「Contoso-VPN」) を指定します。
4. **[完了]** を選択します。

    [Image: [General Properties] (全般プロパティ) の名前の入力と、[Customization Settings for English] (英語のカスタマイズ設定) のキャプションの入力を示すスクリーンショット。]
5. 上部のリボンで、**[Network Settings] (ネットワーク設定)** を選択します。
6. **[Supported IP version] (サポートされている IP バージョン)** で、IPV4 を選択します。
7. **[IPV4 Lease Pool] (IPV4 リース プール)** で、作成した VPN プール (たとえば、「Contoso\_vpn\_pool」) を選択します。

    [Image: [General Settings] (全般設定) の [IPV4 Lease Pool] (IPV4 リース プール) の入力のスクリーンショット。]

    注

    [Client Settings] (クライアント設定) オプションを使用して、確立された VPN のクライアント トラフィックのルーティング方法に関する制限を適用します。
8. **[完了]** を選択します。
9. **[DNS/Hosts] (DNS/ホスト)** タブに移動します。
10. **[IPV4 Primary Name Server] (IPV4 プライマリ ネーム サーバー)**: お使いの環境の DNS IP
11. **[DNS Default Domain Suffix] (DNS の既定のドメイン サフィックス)**: この VPN 接続のドメイン サフィックス。 たとえば、「contoso.com」などです

注

その他の設定については、F5 の記事の「[ネットワーク アクセス リソースの構成 (Configuring Network Access Resources)](https://techdocs.f5.com/kb/en-us/products/big-ip_apm/manuals/product/apm-network-access-11-5-0/2.html)」を参照してください。

VPN サービスでサポートする必要がある VPN クライアントの種類の設定を構成するために、BIG-IP 接続プロファイルが必要になります。 たとえば、Windows 10、OSX、Android などです。

1. **[Access] (アクセス)**&gt;**[Connectivity/VPN] (接続/VPN)**&gt;**[Connectivity] (接続)**&gt;**[Profiles] (プロファイル)** の順に移動します。
2. **[追加]** を選択します。
3. プロファイル名を入力します。
4. たとえば、「Contoso\_VPN\_Profile」とし、親プロファイルを **/Common/connectivity** に設定します。

    [Image: [Create New Connectivity Profile] (新しい接続プロファイルの作成) の [Profile Name] (プロファイル名) と [Parent Name] (親名) の入力のスクリーンショット。]

### アクセス プロファイル構成

アクセス ポリシーを使用すると、サービスで SAML 認証が有効になります。

1. **[Access] (アクセス)**&gt;**[Profiles/Policies] (プロファイル/ポリシー)**&gt;**[Access Profiles (Per-Session Policies)] (アクセス プロファイル (セッション単位のポリシー))** に移動します。
2. **［作成］** を選択します
3. プロファイル名とプロファイルの種類を入力します。
4. たとえば、「Contoso\_network\_access」とし、**[All] (すべて)** を入力します。
5. 下にスクロールして、**[Accepted Languages] (許容される言語)** の一覧に少なくとも 1 つの言語を追加します。
6. **[完了]** を選択します。

    [Image: [New Profile] (新しいプロファイル) の [Name] (名前)、[Profile Type] (プロファイルの種類)、[Language] (言語) の入力を示すスクリーンショット。]
7. 新しいアクセス プロファイルの [Per-Session Policy] (セッション単位のポリシー) で、**[Edit] (編集)** を選択します。
8. ビジュアル ポリシー エディターが新しいタブで開きます。

    [Image: [アクセス プロファイル] の [セッション単位のポリシー] での [編集] オプションのスクリーンショット。]
9. **+** 記号を選択します。
10. メニューで、**[Authentication] (認証)**&gt;**[SAML Auth] (SAML 認証)** を選択します。
11. **[Add Item] (項目の追加)** を選択します。
12. SAML 認証の SP の構成で、作成した VPN SAML SP オブジェクトを選択します。
13. **[保存]** を選択します。

    [Image: [Properties] (プロパティ) タブの [SAML Authentication SP] (SAML 認証の SP) の下の [AAA Server] (AAA サーバー) の入力を示すスクリーンショット。]
14. SAML 認証の[Successful] (成功) の分岐で、**+** を選択します。
15. [Assignment] (割り当て) タブで、**[Advanced Resource Assign] (高度なリソースの割り当て)** を選択します。
16. **[Add Item] (項目の追加)** を選択します。
17. ポップアップで、**[New Entry] (新しいエントリ)** を選択します。
18. **[Add/Delete] (追加/削除)** を選択します。
19. ウィンドウで、**[Network Access] (ネットワーク アクセス)** を選択します。
20. 作成したネットワーク アクセスのプロファイルを選択します。

    [Image: [Properties] (プロパティ) タブの [Resource Assignment] (リソースの割り当て) の [Add new entry] (新しいエントリの追加) ボタンを示すスクリーンショット。]
21. **[Webtop] (Web トップ)** タブに移動します。
22. 作成した Web トップ オブジェクトを追加します。

    [Image: [Webtop] (Web トップ) タブ上の作成された Web トップのスクリーンショット。.]
23. **[更新]** を選択します。
24. **[保存]** を選択します。
25. [Successful] (成功) 分岐を変更するには、上記の **[Deny] (拒否)** ボックスのリンクを選択します。
26. [Allow] (許可) ラベルが表示されます。
27. **保存**。

    [Image: [Access Policy] (アクセス ポリシー) の [Deny] (拒否) オプションのスクリーンショット。]
28. **[Apply Access Policy] (アクセス ポリシーの適用)** を選択します。
29. ビジュアル ポリシー エディターのタブを閉じます。

    [Image: [Apply Access Policy] (アクセス ポリシーの適用) オプションのスクリーンショット。]

### VPN サービスの公開

APM では、VPN に接続しているクライアントをリッスンするフロントエンド仮想サーバーが必要になります。

1. **[Local Traffic] (ローカル トラフィック)**&gt;**[Virtual Servers] (仮想サーバー)**&gt;**[Virtual Server List] (仮想サーバーの一覧)** の順に選択します。
2. **［作成］** を選択します
3. VPN 仮想サーバーの **[Name] (名前)** (たとえば、「VPN\_Listener」) を入力します。
4. クライアント トラフィックを受信するためのルーティングを備えた未使用の **IP 宛先アドレス**を選択します。
5. [Service Port] (サービス ポート) を「**443 HTTPS**」に設定します。
6. **[State] (状態)** で、**[Enabled] (有効)** が選択されていることを確認します。

    [Image: [General Properties] (全般プロパティ) の [Name] (名前) と [Destination Address/Mask] (宛先アドレス/マスク) の入力を示すスクリーンショット。]
7. **[HTTP Profile] (HTTP プロファイル)** を「**http**」に設定します。
8. 作成したパブリック SSL 証明書の SSL プロファイル (クライアント) を追加します。

    [Image: クライアントの HTTP プロファイルの入力と、クライアントに対して選択された SSL プロファイルの入力のスクリーンショット。]
9. [Access Policy] (アクセス ポリシー) で、作成した VPN オブジェクトを使用するには、**[Access Profile] (アクセス プロファイル)** と **[Connectivity Profile] (接続プロファイル)** を設定します。

    [Image: [Access Policy] (アクセス ポリシー) の [Access Profile] (アクセス プロファイル) と [Connectivity Profile] (接続プロファイル) の入力のスクリーンショット。]
10. **[完了]** を選択します。

SSL VPN サービスが公開され、その URL または Microsoft のアプリケーション ポータルを介して、SHA 経由でアクセスできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/grant-admin-consent"} -->
## アプリケーションに対してテナント全体の管理者の同意を付与する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent
- Service: entra-id / enterprise-apps
- Article date: 2025-03-25
- Summary: エンド ユーザーがアプリケーションにサインインするときに同意を求めるメッセージが表示されないように、テナント全体の同意をアプリケーションに付与する方法について説明します。

この記事では、Microsoft Entra ID で、テナント全体の管理者の同意をアプリケーションに付与する方法について説明します。 個々のユーザーの同意設定をどのように構成するかについては、[エンドユーザーによるアプリケーションの同意の構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)に関する記事を参照してください。

テナント全体の管理者の同意をアプリケーションに付与すると、そのアプリケーションは、組織全体に代わって、要求されたアクセス許可にアクセスできるようになります。 組織に代わって管理者の同意を付与することは機密性の高い操作であり、組織のデータの大部分、または高度な特権付きの操作を行うアクセス許可にアプリケーションの発行者がアクセスできる可能性があります。 そのような操作の例は、ロール管理、すべてのメールボックスまたはすべてのサイトへのフルアクセス、完全なユーザー偽装などです。 そのため、同意を付与する前に、アプリケーションで要求されているアクセス許可をよく確認する必要があります。

既定では、テナント全体の管理者の同意をアプリケーションに付与すると、特に制限がない限り、すべてのユーザーがアプリケーションにアクセスできるようになります。 アプリケーションにサインインできるユーザーを制限するには、アプリが[ユーザー割り当てを要求する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-properties#assignment-required)ように構成し、[アプリケーションにユーザーまたはグループを割り当てます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。

重要

テナント全体の管理者の同意を付与すると、そのアプリケーションに対して既にテナント全体に付与されていたアクセス許可が取り消される場合があります。 ユーザーが既に代理で付与しているアクセス許可は影響を受けません。

### 前提条件

テナント全体の管理者の同意を付与するには、組織を代表して同意する権限を持つユーザーとしてサインインする必要があります。

テナント全体の管理者の同意を許可するには、次が必要です。

- 次のいずれかのロールを持つ Microsoft Entra ユーザー アカウント。

    - 任意の API に対してアクセス許可を要求するアプリに同意を付与する特権ロール管理者。
    - クラウド アプリケーション管理者、AI 管理者、またはアプリケーション管理者。Microsoft Graph アプリ ロール (アプリケーションのアクセス許可 *) を除く* 、任意の API に対するアクセス許可を要求するアプリに同意を付与します。
    - アプリケーションで必要なアクセス許可に対して、[アプリケーションにアクセス許可を付与するための権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-consent-permissions)が含まれたカスタム ディレクトリ ロール。

::: zone pivot="portal"

### エンタープライズ アプリ ウィンドウでテナント全体の管理者の同意を付与する

アプリケーションが既にテナントにプロビジョニングされている場合は、**Enterprise アプリケーション** ウィンドウからテナント全体の管理者の同意を付与できます。 たとえば、少なくとも 1 人のユーザーがアプリケーションに同意した場合、テナントにアプリをプロビジョニングできます。 詳細については、「[アプリケーションを Microsoft Entra に追加する方法と理由](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-applications-are-added)」を参照してください。

**[エンタープライズ アプリケーション]** ウィンドウに一覧表示されているアプリにテナント全体の管理者の同意を付与するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. **[セキュリティ]** で **[アクセス許可]** を選択します。 [Image: テナント全体の管理者の同意を付与する方法を示すスクリーンショット。]
5. アプリケーションに必要なアクセス許可を慎重に確認します。 アプリケーションで必要なアクセス許可に同意する場合は、 **[管理者の同意の付与]** を選択します。

### アプリの登録ウィンドウで管理者の同意を付与する

Microsoft Entra 管理センターの **アプリ登録** から、組織が開発し、Microsoft Entra テナントに直接登録するアプリケーションに対して、テナント全体の管理者同意を付与できます。

**[アプリの登録]** からテナント全体の管理者の同意を付与するには:

1. Microsoft Entra 管理センターで、**Entra ID**&gt;**アプリ登録**&gt;**すべてのアプリケーション**を参照します。
2. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
3. **[管理]** の下にある **[API のアクセス許可]** を選びます。
4. アプリケーションに必要なアクセス許可を慎重に確認します。 同意する場合は、 **[管理者の同意の付与]** を選択します。

### テナント全体の管理者の同意を付与するための URL を作成する

前のセクションのどちらかの方法を使ってテナント全体の管理者の同意を付与すると、Microsoft Entra 管理センターでウィンドウが開き、テナント全体の管理者の同意を求めるプロンプトが表示されます。 アプリケーションのクライアント ID (アプリケーション ID とも呼ばれます) がわかっている場合は、同じ URL を作成して、テナント全体の管理者の同意を付与することができます。

テナント全体の管理者の同意の URL は、次のような形式です。

```http
https://login.microsoftonline.com/{organization}/adminconsent?client_id={client-id}
```

どこ：

- `{client-id}` は、アプリケーションのクライアント ID (アプリ ID とも呼ばれます) です。
- `{organization}` は、アプリケーションに同意するテナントのテナント ID または検証済みドメイン名です。 値 `organizations` を使用すると、サインインするユーザーのホーム テナントで同意が行われます。

この場合も、同意を付与する前に、アプリケーションで要求されているアクセス許可をよく確認してください。

テナント全体の管理者の同意 URL を構築する方法の詳細については、「[Microsoft ID プラットフォームの管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-admin-consent)」を参照してください。

::: zone-end

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用して委任されたアクセス許可に対して管理者の同意を付与する

このセクションでは、委任されたアクセス許可をアプリケーションに付与します。 委任されたアクセス許可は、サインインしているユーザーの代わりにアプリケーションが API にアクセスするために必要なアクセス許可です。 アクセス許可はリソース API によって定義され、クライアント アプリケーションであるエンタープライズ アプリケーションに付与されます。 この同意は、すべてのユーザーに代わって付与されます。

次の例では、リソース API は、オブジェクト ID `ffffffff-eeee-dddd-cccc-bbbbbbbbbbb0` の Microsoft Graph です。 Microsoft Graph API は、委任されたアクセス許可、`User.Read.All`、および `Group.Read.All`を定義します。 consentType は `AllPrincipals` であり、テナント内のすべてのユーザーに代わって同意していることを示します。 クライアント エンタープライズ アプリケーションのオブジェクト ID は です `ffffffff-eeee-dddd-cccc-bbbbbbbbbbb0`。

注意事項

ご注意ください。 プログラムによって付与されたアクセス許可は、レビューまたは確認の対象になりません。 それらはすぐに有効になります。

1. Microsoft Graph PowerShell に接続し、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。

    ```powershell
    Connect-MgGraph -Scopes "Application.ReadWrite.All", "DelegatedPermissionGrant.ReadWrite.All"
    ```
2. テナント アプリケーションで Microsoft Graph (リソース アプリケーション) によって定義された、すべての委任されたアクセス許可を取得します。 クライアント アプリケーションに付与する必要がある委任されたアクセス許可を特定します。 この例では、委任のアクセス許可は `User.Read.All` と `Group.Read.All` です

    ```powershell
    Get-MgServicePrincipal -Filter "displayName eq 'Microsoft Graph'" -Property Oauth2PermissionScopes | Select -ExpandProperty Oauth2PermissionScopes | fl
    ```
3. 次の要求を実行して、委任されたアクセス許可をクライアント エンタープライズ アプリケーションに付与します。

    ```powershell
    $params = @{
    
    "ClientId" = "00001111-aaaa-2222-bbbb-3333cccc4444"
    "ConsentType" = "AllPrincipals"
    "ResourceId" = "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1"
    "Scope" = "User.Read.All Group.Read.All"
    }
    
    New-MgOauth2PermissionGrant -BodyParameter $params | 
    Format-List Id, ClientId, ConsentType, ResourceId, Scope
    ```
4. 次の要求を実行して、テナント全体の管理者の同意を付与したことを確認します。

```powershell
 Get-MgOauth2PermissionGrant -Filter "clientId eq '00001111-aaaa-2222-bbbb-3333cccc4444' and consentType eq 'AllPrincipals'" 
```

### Microsoft Graph PowerShell を使用してアプリケーションのアクセス許可に対する管理者の同意を付与する

このセクションでは、エンタープライズ アプリケーションにアプリケーションのアクセス許可を付与します。 アプリケーションのアクセス許可は、アプリケーションがリソース API にアクセスするために必要なアクセス許可です。 アクセス許可はリソース API によって定義され、プリンシパル アプリケーションであるエンタープライズ アプリケーションに付与されます。 アプリケーションにリソース API へのアクセスを許可すると、サインインしているユーザーなしでバックグラウンド サービスまたはデーモンとして実行されます。 アプリケーションのアクセス許可は、アプリ ロールとも呼ばれます。

次の例では、Microsoft Graph アプリケーション (ID `aaaaaaaa-bbbb-cccc-1111-222222222222` のプリンシパル) に、ID `df021288-bdef-4463-88db-98f22de89214` のリソース API によって公開される ID `aaaabbbb-0000-cccc-1111-dddd2222eeee` のアプリ ロール (アプリケーションのアクセス許可) を付与します。

1. Microsoft Graph PowerShell に接続し、少なくとも [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。

    ```powershell
    Connect-MgGraph -Scopes "Application.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"
    ```
2. テナント内の Microsoft Graph によって定義されたアプリ ロールを取得します。 クライアント エンタープライズ アプリケーションに付与する必要があるアプリ ロールを特定します。 この例では、アプリ ロール ID は `df021288-bdef-4463-88db-98f22de89214` です。

    ```powershell
    Get-MgServicePrincipal -Filter "displayName eq 'Microsoft Graph'" -Property AppRoles | Select -ExpandProperty appRoles |fl
    ```
3. 次の要求を実行して、プリンシパル アプリケーションにアプリケーションのアクセス許可 (アプリ ロール) を付与します。

```powershell
 $params = @{
  "PrincipalId" ="aaaaaaaa-bbbb-cccc-1111-222222222222"
  "ResourceId" = "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1"
  "AppRoleId" = "df021288-bdef-4463-88db-98f22de89214"
}

New-MgServicePrincipalAppRoleAssignment -ServicePrincipalId 'aaaaaaaa-bbbb-cccc-1111-222222222222' -BodyParameter $params | 
  Format-List Id, AppRoleId, CreatedDateTime, PrincipalDisplayName, PrincipalId, PrincipalType, ResourceDisplayName
```

::: zone-end

::: zone pivot="ms-graph"

[Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を使用して、委任されたアクセス許可とアプリケーションのアクセス許可の両方を付与します。

### Microsoft Graph API を使用して委任されたアクセス許可に対して管理者の同意を付与する

このセクションでは、委任されたアクセス許可をアプリケーションに付与します。 委任されたアクセス許可は、サインインしているユーザーの代わりにアプリケーションが API にアクセスするために必要なアクセス許可です。 アクセス許可はリソース API によって定義され、クライアント アプリケーションであるエンタープライズ アプリケーションに付与されます。 この同意は、すべてのユーザーに代わって付与されます。

少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。

次の例では、リソース API は、オブジェクト ID `ffffffff-eeee-dddd-cccc-bbbbbbbbbbb0` の Microsoft Graph です。 Microsoft Graph API では、委任されたアクセス許可の `User.Read.All` および `Group.Read.All` を定義します。 consentType は `AllPrincipals` であり、テナント内のすべてのユーザーに代わって同意していることを示します。 クライアント エンタープライズ アプリケーションのオブジェクト ID は です `ffffffff-eeee-dddd-cccc-bbbbbbbbbbb0`。

注意事項

ご注意ください。 プログラムによって付与されたアクセス許可は、レビューまたは確認の対象になりません。 それらはすぐに有効になります。

1. テナント アプリケーションで Microsoft Graph (リソース アプリケーション) によって定義された、すべての委任されたアクセス許可を取得します。 クライアント アプリケーションに付与する必要がある委任されたアクセス許可を特定します。 この例では、委任のアクセス許可は `User.Read.All` と `Group.Read.All` です

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals?$filter=displayName eq 'Microsoft Graph'&$select=id,displayName,appId,oauth2PermissionScopes
    ```
2. 次の要求を実行して、委任されたアクセス許可をクライアント エンタープライズ アプリケーションに付与します。

    ```http
    POST https://graph.microsoft.com/v1.0/oauth2PermissionGrants
    
    Request body
    {
       "clientId": "00001111-aaaa-2222-bbbb-3333cccc4444",
       "consentType": "AllPrincipals",
       "resourceId": "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1",
       "scope": "User.Read.All Group.Read.All"
    }
    ```
3. 次の要求を実行して、テナント全体の管理者の同意を付与したことを確認します。

    ```http
    GET https://graph.microsoft.com/v1.0/oauth2PermissionGrants?$filter=clientId eq '00001111-aaaa-2222-bbbb-3333cccc4444' and consentType eq 'AllPrincipals'
    ```

### Microsoft Graph API を使用してアプリケーションのアクセス許可に対する管理者の同意を付与する

このセクションでは、エンタープライズ アプリケーションにアプリケーションのアクセス許可を付与します。 アプリケーションのアクセス許可は、アプリケーションがリソース API にアクセスするために必要なアクセス許可です。 アクセス許可はリソース API によって定義され、プリンシパル アプリケーションであるエンタープライズ アプリケーションに付与されます。 アプリケーションにリソース API へのアクセスを許可すると、サインインしているユーザーなしでバックグラウンド サービスまたはデーモンとして実行されます。 アプリケーションのアクセス許可は、アプリ ロールとも呼ばれます。

次の例では、Microsoft Graph アプリケーション (ID `00001111-aaaa-2222-bbbb-3333cccc4444` のプリンシパル) に、ID `df021288-bdef-4463-88db-98f22de89214` のリソース エンタープライズ アプリケーションによって公開される ID `11112222-bbbb-3333-cccc-4444dddd5555` のアプリ ロール (アプリケーションのアクセス許可) を付与します。

少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインする必要があります。

1. テナント内の Microsoft Graph によって定義されたアプリ ロールを取得します。 クライアント エンタープライズ アプリケーションに付与する必要があるアプリ ロールを特定します。 この例では、アプリ ロール ID は `df021288-bdef-4463-88db-98f22de89214` です

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals?$filter=displayName eq 'Microsoft Graph'&$select=id,displayName,appId,appRoles
    ```
2. 次の要求を実行して、プリンシパル アプリケーションにアプリケーションのアクセス許可 (アプリ ロール) を付与します。

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/11112222-bbbb-3333-cccc-4444dddd5555/appRoleAssignedTo
    
    Request body
    
    {
       "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
       "resourceId": "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1",
       "appRoleId": "df021288-bdef-4463-88db-98f22de89214"
    }
    ```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/grant-consent-single-user"} -->
## 1 人のユーザーに代わって同意を付与する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-consent-single-user
- Service: entra-id / enterprise-apps
- Article date: 2024-12-12
- Summary: ユーザーの同意が無効または制限されている場合に、1 人のユーザーに代わって同意を付与する方法について説明します。

この記事では、PowerShell を使用して 1 人のユーザーに代わって同意を許可する方法について説明します。

ユーザーが自分の代わりに同意を許可すると、次のイベントが頻繁に発生します。

1. クライアント アプリケーションのサービス プリンシパルが作成されます (存在しない場合)。 サービス プリンシパルは、Microsoft Entra テナント内のアプリケーションまたはサービスのインスタンスです。 アプリまたはサービスに許可されているアクセス権は、このサービス プリンシパル オブジェクトに関連付けられます。
2. アプリケーションがアクセスを必要とする API ごとに、アプリケーションが必要なアクセス許可について、その API に対して委任されたアクセス許可が作成されます。 アクセス権は、ユーザーに代わって付与されます。 委任されたアクセス許可の付与は、そのユーザーがサインインしたときに、アプリケーションがユーザーに代わって API にアクセスする権限を許可します。
3. ユーザーはクライアント アプリケーションを割り当てられます。 アプリケーションをユーザーに割り当てると、そのユーザーの[マイ アプリ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview) ポータルにアプリケーションが表示されます。 ユーザーは、マイ アプリ ポータルから自分の代わりに付与されたアクセス権を確認および取り消すことができます。

### 前提条件

- 特権ロール管理者、アプリケーション管理者、またはクラウド アプリケーション管理者ロールを持つユーザー アカウント。
- Microsoft Entra 管理センターの次の詳細:

    - 同意を付与するアプリのアプリ ID。 この記事では、これを *クライアント アプリケーション*と呼びます。
    - クライアント アプリケーションに必要な API アクセス許可。 API のアプリ ID とアクセス許可 ID または要求値を調べます。
    - そのユーザーに代わってアクセス権を許可するユーザーのユーザー名またはオブジェクト ID。

### 1 人のユーザーに代わって同意を許可する

::: zone pivot="msgraph-powershell"

この記事の例では、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started) を使用して、1 人のユーザーに代わって同意を付与します。 クライアント アプリケーションは [Microsoft Graph Explorer](https://aka.ms/ge) であり、Microsoft Graph API へのアクセス権を付与します。

Microsoft Graph PowerShell を使用して 1 人のユーザーに代わってアプリケーションに同意するには、少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。

```powershell
# The app for which consent is being granted.
$clientAppId = "de8bc8b5-d9f9-48b1-a8ad-b748da725064" # Your client application

# The API to which access will be granted. Your client application makes API 
# requests to the Microsoft Graph API, so we'll use that here.
$resourceAppId = "00000003-0000-0000-c000-000000000000" # Microsoft Graph API

# The permissions to grant. Here we're including "openid", "profile", "User.Read",
# and "offline_access" (for basic sign-in), as well as "User.ReadBasic.All" (for 
# reading other users' basic profile).
$permissions = @("openid", "profile", "offline_access", "User.Read", "User.ReadBasic.All")

# The user on behalf of whom access will be granted. The app will be able to access 
# the API on behalf of this user.
$userUpnOrId = "user@example.com"

# Step 0. Connect to Microsoft Graph PowerShell. We need User.ReadBasic.All to get
#    users' IDs, Application.ReadWrite.All to list and create service principals, 
#    DelegatedPermissionGrant.ReadWrite.All to create delegated permission grants, 
#    and AppRoleAssignment.ReadWrite.All to assign an app role.
#    WARNING: These are high-privilege permissions!
Connect-MgGraph -Scopes ("User.ReadBasic.All Application.ReadWrite.All " `
                        + "DelegatedPermissionGrant.ReadWrite.All " `
                        + "AppRoleAssignment.ReadWrite.All")

# Step 1. Check if a service principal exists for the client application. 
#     If one doesn't exist, create it.
$clientSp = Get-MgServicePrincipal -Filter "appId eq '$($clientAppId)'"
if (-not $clientSp) {
   $clientSp = New-MgServicePrincipal -AppId $clientAppId
}

# Step 2. Create a delegated permission that grants the client app access to the
#     API, on behalf of the user. (This example assumes that an existing delegated 
#     permission grant does not already exist, in which case it would be necessary 
#     to update the existing grant, rather than create a new one.)
$user = Get-MgUser -UserId $userUpnOrId
$resourceSp = Get-MgServicePrincipal -Filter "appId eq '$($resourceAppId)'"
$scopeToGrant = $permissions -join " "
$grant = New-MgOauth2PermissionGrant -ResourceId $resourceSp.Id `
                                     -Scope $scopeToGrant `
                                     -ClientId $clientSp.Id `
                                     -ConsentType "Principal" `
                                     -PrincipalId $user.Id

# Step 3. Assign the app to the user. This ensures that the user can sign in if assignment
#     is required, and ensures that the app shows up under the user's My Apps portal.
if ($clientSp.AppRoles | ? { $_.AllowedMemberTypes -contains "User" }) {
    Write-Warning ("A default app role assignment cannot be created because the " `
                 + "client application exposes user-assignable app roles. You must " `
                 + "assign the user a specific app role for the app to be listed " `
                 + "in the user's My Apps portal.")
} else {
    # The app role ID 00000000-0000-0000-0000-000000000000 is the default app role
    # indicating that the app is assigned to the user, but not for any specific 
    # app role.
    $assignment = New-MgServicePrincipalAppRoleAssignedTo `
          -ServicePrincipalId $clientSp.Id `
          -ResourceId $clientSp.Id `
          -PrincipalId $user.Id `
          -AppRoleId "00000000-0000-0000-0000-000000000000"
}
```

::: zone-end

::: zone pivot="ms-graph"

Microsoft Graph API を使用している 1 人のユーザーに代わってアプリケーションに同意するには、少なくとも Cloud アプリケーション管理者として Microsoft Graph Explorer にサインインします。

`Application.ReadWrite.All`、`Directory.ReadWrite.All`、`DelegatedPermissionGrant.ReadWrite.All`のアクセス許可に同意する必要があります。

次の例では、リソース API によって定義された委任されたアクセス許可を、1 人のユーザーに代わってクライアント エンタープライズ アプリケーションに付与します。 この例では、次のようになります。

- リソース エンタープライズ アプリケーションは、オブジェクト ID `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb` のMicrosoft Graphです。
- Microsoft Graphは、委任されたアクセス許可 (`User.Read.All` と `Group.Read.All`) を定義します。
- consentType は `Principal`であり、テナント内の 1 人のユーザーに代わって同意していることを示します。
- クライアント エンタープライズ アプリケーションのオブジェクト ID は です `aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb`。
- ユーザーのプリンシパル ID が `aaaaaaaa-bbbb-cccc-1111-222222222222`。

注意事項

ご注意ください。 プログラムによって付与されたアクセス許可は、レビューまたは確認の対象になりません。 それらはすぐに有効になります。

1. テナント アプリケーションでMicrosoft Graph (リソース アプリケーション) によって定義されているすべての委任されたアクセス許可を取得します。 クライアント アプリケーションに付与する委任されたアクセス許可を特定します。 この例では、委任のアクセス許可は `User.Read.All` と `Group.Read.All` です。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals?$filter=displayName eq 'Microsoft Graph'&$select=id,displayName,appId,oauth2PermissionScopes
    ```
2. 次の要求を実行して、委任されたアクセス許可をユーザーに代わってクライアント エンタープライズ アプリケーションに付与します。

    ```http
    POST https://graph.microsoft.com/v1.0/oauth2PermissionGrants
    
    Request body
    {
       "clientId": "00001111-aaaa-2222-bbbb-3333cccc4444",
       "consentType": "Principal",
       "resourceId": "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1",
       "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
       "scope": "User.Read.All Group.Read.All"
    }
    ```
3. 次の要求を実行して、ユーザーに同意を付与したことを確認します。

    ```http
    GET https://graph.microsoft.com/v1.0/oauth2PermissionGrants?$filter=clientId eq '00001111-aaaa-2222-bbbb-3333cccc4444' and consentType eq 'Principal'
    ```
4. アプリをユーザーに割り当てます。 この割り当てにより、割り当てが必要な場合にユーザーがサインインできるようになります。 また、ユーザーのマイ アプリ ポータルからアプリを使用できるようになります。

    次の例では、 `resourceId` は、ユーザーが割り当てられているクライアント アプリを表します。 ユーザーには既定のアプリ ロール (`00000000-0000-0000-0000-000000000000`) が割り当てられます。

    ```http
        POST /servicePrincipals/resource-servicePrincipal-id/appRoleAssignedTo
    
        {
        "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
        "resourceId": "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1",
        "appRoleId": "00000000-0000-0000-0000-000000000000"
        }
    ```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/hide-application-from-user-portal"} -->
## エンタープライズ アプリケーションを非表示にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/hide-application-from-user-portal
- Service: entra-id / enterprise-apps
- Article date: 2025-03-04
- Summary: Microsoft Entra ID アクセス ポータルまたは Microsoft 365 ランチャーでユーザーのエクスペリエンスからエンタープライズ アプリケーションを非表示にする方法。

Microsoft Entra ID でエンタープライズ アプリケーションを非表示にする方法について説明します。 アプリケーションを非表示にしても、アプリケーションに対するユーザーのアクセス許可は維持されます。

### 前提条件

マイ アプリ ポータルと Microsoft 365 起動ツールでアプリケーションを非表示にするには、次の手順を使用します。

- アクティブなサブスクリプションを持つ Microsoft Entra アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者。
    - すべての Microsoft 365 アプリケーションを非表示にするは、グローバル管理者特権が必要です。

### エンドユーザーに対してアプリケーションを非表示にする

::: zone pivot="portal"

マイ アプリ ポータルと Microsoft 365 アプリケーション ランチャーにアプリケーションが表示されないようにするには、次の手順を使用します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** に移動します。
3. 非表示にするアプリケーションを検索し、アプリケーションを選択します。
4. 左側のナビゲーション ウィンドウで、[ **プロパティ**] を選択します。
5. [**ユーザーに表示しますか?** ] の質問に対して [**いいえ**] を選択します。
6. **[保存] を選択します**。

::: zone-end

注

これらの手順は、ファースト パーティではない Microsoft エンタープライズ アプリケーションにのみ適用されます。 ファースト パーティの Microsoft アプリケーションの詳細については、 [サインイン レポートのファースト パーティの Microsoft アプリケーション](https://learn.microsoft.com/ja-jp/troubleshoot/azure/entra/entra-id/governance/verify-first-party-apps-sign-in)を参照してください。 管理者は、アプリケーションをユーザーから非表示にしても、マイ アプリ ポータル以外の方法 (共有リンクやサービスの依存関係など) を通じてサインインできる可能性があることも念頭に置く必要があります。

::: zone pivot="entra-powershell"

Microsoft Entra PowerShell を使用してマイ アプリ ポータルからアプリケーションを非表示にするには、Microsoft Entra PowerShell に接続し、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。 **HideApp** タグは、アプリケーションのサービス プリンシパルに手動で追加できます。 次の Microsoft Entra PowerShell コマンドを実行して、アプリケーションの **Visible to Users? プロパティを** **[いいえ**] に設定します。

```PowerShell
Connect-Entra -scopes "Application.ReadWrite.All"

$objectId = "<objectId>"
$servicePrincipal = Get-EntraServicePrincipal -ObjectId $objectId
$tags = $servicePrincipal.tags
$tags += "HideApp"
Set-EntraServicePrincipal -ObjectId $objectId -Tags $tags
```

::: zone-end

::: zone pivot="ms-powershell"

Microsoft Graph PowerShell を使用してマイ アプリ ポータルからアプリケーションを非表示にするには、Microsoft Graph PowerShell に接続し、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。 アプリケーションのサービス プリンシパルに HideApp タグを手動で追加できます。 次の Microsoft Graph PowerShell コマンドを実行して、アプリケーションの **Visible to Users? プロパティを** **[いいえ**] に設定します。

```PowerShell
Connect-MgGraph "Application.ReadWrite.All"
$tags = $servicePrincipal.tags
$tags += "HideApp"
Update-MgServicePrincipal -ServicePrincipalID  $objectId -Tags $tags
```

::: zone-end

::: zone pivot="ms-graph"

[Graph Explorer](https://developer.microsoft.com/graph/graph-explorer) を使用してエンタープライズ アプリケーションを非表示にするには、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。

クエリを実行する前に、 `Application.ReadWrite.All` アクセス許可に同意してください。

次のクエリを実行します。

1. 非表示にするアプリケーションを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/00001111-aaaa-2222-bbbb-3333cccc4444
    ```
2. ユーザーに表示されないようにアプリケーションを更新します。

    ```http
    PATCH https://graph.microsoft.com/v1.0/servicePrincipals/00001111-aaaa-2222-bbbb-3333cccc4444/
    ```

    次の要求本文を指定します。

    ```json
    {
        "tags": [
        "HideApp"
        ]
    }
    ```

    警告

    アプリケーションに他のタグがある場合は、それらを要求本文に含める必要があります。 そうしないと、クエリによって上書きされます。

::: zone-end

::: zone pivot="portal"

### マイ アプリ ポータルに Microsoft 365 アプリケーションが表示されないようにする

マイ アプリ ポータルに一切の Microsoft 365 アプリケーションが表示されないようにするには、次の手順を使用します。 なお、アプリケーションは Office 365 ポータルには表示されます。

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. [**管理**] メニュー項目**の下にある [アプリ起動ツール**] を選択します。
4. [ **設定] を選択します**。
5. [ユーザー] のオプションを有効にすると **、Microsoft 365 ポータルでのみ Microsoft 365 アプリを表示できます**。
6. **[保存] を選択します**。

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/home-realm-discovery-policy"} -->
## アプリケーションのホーム領域検出ポリシー - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/home-realm-discovery-policy
- Service: entra-id / enterprise-apps
- Article date: 2024-11-26
- Summary: 自動高速化やドメイン ヒントなど、フェデレーション ユーザーのMicrosoft Entra認証のホーム領域検出ポリシーを管理する方法について説明します。

ホーム領域検出 (HRD) を使用すると、Microsoft Entra IDはサインイン時にユーザー認証に適した ID プロバイダー (IdP) を識別できます。 ユーザーがリソースまたは共通サインイン ページにアクセスするためにMicrosoft Entra テナントにサインインすると、ユーザー プリンシパル名 (UPN) を入力します。 Microsoft Entra IDはこの情報を使用して、正しいサインイン場所を決定します。

ユーザーは、認証のために次のいずれかの ID プロバイダーに転送されます。

- ユーザーのホーム テナント (リソース テナントと同じ場合があります)。
- ユーザーがリソース テナントのゲストであり、コンシューマー アカウントを使用している場合は、マイクロソフト アカウント。
- Active Directory フェデレーション サービス (AD FS) (AD FS) などのオンプレミス ID プロバイダー。
- Microsoft Entra テナントとフェデレーションされている別の ID プロバイダー。

### 自動高速化

組織は、ユーザー認証のために、Microsoft Entra テナント内のドメインを別の IdP (AD FS など) とフェデレーションするように構成できます。 ユーザーがアプリケーションにサインインすると、最初に Microsoft Entra サインイン ページが表示されます。 フェデレーション ドメインに属している場合は、そのドメインの IdP のサインイン ページにリダイレクトされます。 管理者は、特定のアプリケーションの初期Microsoft Entra ID ページをバイパスできます。 このプロセスは、 *サインイン自動高速化*と呼ばれます。

Microsoftは、FIDO やコラボレーションなどのより強力な認証方法を妨げる可能性があるため、自動高速化の構成を推奨します。 詳細については、「 [パスワードレス セキュリティ キーのサインインを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key)」を参照してください。 サインインの自動高速化を防止する方法については、「[自動高速化サインインを無効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/prevent-domain-hints-with-home-realm-discovery)」を参照してください。

自動高速化により、別の IdP とフェデレーションされているテナントのサインインを効率化できます。 個々のアプリケーションに対して構成できます。 HRD を使用して自動高速化を強制する方法については、「 [自動高速化の構成」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-authentication-for-federated-users-portal)参照してください。

Note

自動高速化用にアプリケーションを構成すると、ユーザーはマネージド資格情報 (FIDO など) を使用したり、ゲスト ユーザーがサインインしたりできなくなります。 認証のためにユーザーをフェデレーション IdP に誘導すると、Microsoft Entraサインイン ページがバイパスされ、ゲスト ユーザーが他のテナントや外部 IdP (Microsoft アカウントなど) にアクセスできなくなります。

フェデレーション IdP への自動高速化は、次の 3 つの方法で制御できます。

- アプリケーションの認証要求でドメイン ヒントを使用する。
- HRD ポリシーを構成して[自動高速化を強制する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-authentication-for-federated-users-portal)。
- 特定のアプリケーションまたはドメインの [ドメイン ヒントを無視](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/prevent-domain-hints-with-home-realm-discovery) するように HRD ポリシーを構成します。

### ドメインの確認ダイアログ

2023 年 4 月の時点で、自動高速化またはスマート リンクを使用する組織では、サインイン UI でドメイン確認ダイアログが表示される可能性があります。 このダイアログは、Microsoftのセキュリティ強化作業の一部であり、ユーザーはサインインしているテナントのドメインを確認する必要があります。

#### 実行する必要があること

ドメインの確認ダイアログが表示されたら、ドメインを確認します。 ダイアログのドメイン名が、サインイン先の組織と一致することを確認します。

ドメインが認識された場合は、[ **確認** ] を選択して続行します。 ドメインが認識されない場合は、サインイン プロセスをキャンセルし、IT 管理者に問い合わせてください。

#### ドメイン確認ダイアログのコンポーネント

次のスクリーンショットは、ドメイン確認ダイアログの例を示しています。

[Image: サインイン識別子とテナント ドメインを示すドメイン確認ダイアログのスクリーンショット。]

ダイアログの上部にある識別子 ( `kelly@contoso.com`) は、サインインの識別子を表します。 ダイアログの見出しとテキストには、アカウントのホーム テナントのドメインが表示されます。

このダイアログは、自動高速化またはスマート リンクのすべてのインスタンスに対して表示されない場合があります。 ブラウザー ポリシーが原因で組織が Cookie をクリアすると、ドメインの確認ダイアログが頻繁に表示されることがあります。

Microsoft Entra IDは自動高速化サインイン フローを管理するため、ドメイン確認ダイアログでアプリケーションが破損しないようにする必要があります。

### ドメイン ヒント

ドメイン ヒントは、ユーザーをフェデレーション IdP サインイン ページに高速化できるアプリケーションからの認証要求のディレクティブです。 マルチテナント アプリケーションは、それらを使用して、テナントのブランド化されたMicrosoft Entra サインイン ページにユーザーを誘導できます。

たとえば、 `largeapp.com` はカスタム URL `contoso.largeapp.com` 経由でアクセスを許可し、認証要求に `contoso.com` するドメイン ヒントを含めることができます。

ドメイン ヒントの構文はプロトコルによって異なります。

- **WS-Federation**: クエリ文字列パラメーター `whr` 。たとえば、 `whr=contoso.com`。
- **SAML**: ドメイン ヒントまたは `whr=contoso.com`を使用した SAML 認証要求。
- **OpenID Connect**: クエリ文字列パラメーター`domain_hint`。たとえば、`domain_hint=contoso.com`。

Microsoft Entra IDは、次の場合*both*が当てはまる場合に、ドメインの構成済み IdP にサインインをリダイレクトします。

- ドメイン ヒントは認証要求に含まれています。
- そのドメインとテナントがフェデレーションされている。

ドメイン ヒントが検証済みのフェデレーション ドメインを参照していない場合は、無視できます。

Note

認証要求のドメイン ヒントは、HRD ポリシー内のアプリケーションの自動高速化セットをオーバーライドします。

#### 自動高速化の HRD ポリシー

一部のアプリケーションでは、認証要求の構成が許可されていません。 このような場合、ドメイン ヒントを使用して自動高速化を制御することはできません。 [ホーム領域検出](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-authentication-for-federated-users-portal)ポリシーを使用して自動高速化を構成します。

#### 自動高速化を防ぐための HRD ポリシー

一部のMicrosoftおよびサービスとしてのソフトウェア (SaaS) アプリケーションにはドメイン ヒントが自動的に含まれ、FIDO などのマネージド資格情報のロールアウトが中断される可能性があります。 [ホーム領域検出ポリシーを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/prevent-domain-hints-with-home-realm-discovery)使用して、マネージド資格情報のロールアウト中に特定のアプリまたはドメインからのドメイン ヒントを無視します。

### レガシ アプリケーションのフェデレーション ユーザーの直接 ROPC 認証

ベスト プラクティスは、アプリケーションがユーザー認証にMicrosoft Entra ライブラリと対話型サインインを使用することです。 リソース所有者パスワード資格情報 (ROPC) の付与を使用するレガシ アプリケーションは、フェデレーションを理解せずに資格情報をMicrosoft Entra IDに直接送信する場合があります。 HRD を実行したり、適切なフェデレーション エンドポイントと対話したりしません。 [Home Realm Discovery ポリシーを使用して、特定のレガシ アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-authentication-for-federated-users-portal)がMicrosoft Entra IDで直接認証できるようにします。 このオプションは、パスワード ハッシュ同期が有効になっている場合に機能します。

重要

パスワード ハッシュ同期がアクティブで、オンプレミスの IdP ポリシーを使用せずにアプリケーションを認証できる場合にのみ、直接認証を有効にします。 パスワード ハッシュ同期または Microsoft Entra Connect とのディレクトリ同期が無効になっている場合は、古いパスワード ハッシュによる直接認証を防ぐために、このポリシーを削除します。

### HRD ポリシーを設定する手順

フェデレーション サインイン自動高速化または直接クラウドベースアプリケーションのアプリケーションに HRD ポリシーを設定するには:

1. HRD ポリシーを作成します。
2. ポリシーをアタッチするサービス プリンシパルを探します。
3. サービス プリンシパルにポリシーをアタッチします。

ポリシーは、サービス プリンシパルにアタッチされている特定のアプリケーションに対して有効になります。 サービス プリンシパルで一度にアクティブにできる HRD ポリシーは 1 つだけです。 [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) コマンドレットを使用して、HRD ポリシーを作成および管理します。

HRD ポリシー定義の例を次に示します。

```json
{  
  "HomeRealmDiscoveryPolicy": {  
    "AccelerateToFederatedDomain": true,  
    "PreferredDomain": "federated.example.edu",  
    "AllowCloudPasswordValidation": false  
  }  
}  
```

- `AccelerateToFederatedDomain`: オプション。 値が `false`の場合、ポリシーは自動高速化に影響しません。 値が `true` で、検証済みのフェデレーション ドメインが 1 つある場合、ユーザーはフェデレーション IdP に転送されます。 複数のドメインが存在する場合は、 `PreferredDomain`を指定します。
- `PreferredDomain`: オプション。 アクセラレーションのドメインを示します。 フェデレーション ドメインが 1 つだけ存在する場合は省略します。 複数のドメインで省略した場合、ポリシーは無効になります。
- `AllowCloudPasswordValidation`: オプション。 値が `true` の場合、この設定では、ユーザー名/パスワード資格情報を使用したフェデレーション ユーザー認証をMicrosoft Entra トークン エンドポイントに直接許可し、パスワード ハッシュ同期が必要です。

追加のテナント レベルの HRD オプションは次のとおりです。

- `AlternateIdLogin`: オプション。 Microsoft Entra サインイン ページで UPN ではなく電子メール サインイン[AlternateLoginID](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-use-email-signin) を有効にします。 フェデレーション IDP に自動高速化されていないユーザーに依存します。
- `DomainHintPolicy`: ドメイン ヒントがユーザーをフェデレーション ドメインに自動的に移行させることを防ぐ、オプションの複合オブジェクト。 ドメイン ヒントを送信するアプリケーションが、クラウドで管理された資格情報のサインインを妨げないようにします。

#### HRD ポリシーの優先順位と評価

複数のポリシーをアプリケーションに適用できるように、組織とサービス プリンシパルに HRD ポリシーを割り当てることができます。 Microsoft Entra IDは、次の規則を使用して優先順位を決定します。

- ドメイン ヒントが存在する場合、テナントの HRD ポリシーはドメイン ヒントを無視する必要があるかどうかを確認します。 ドメイン ヒントが許可されている場合、Microsoft Entra IDはドメイン ヒントの動作を使用します。
- ポリシーがサービス プリンシパルに明示的に割り当てられている場合は、Microsoft Entra IDによって適用されます。
- ドメイン ヒントまたはサービス プリンシパル ポリシーが存在しない場合、Microsoft Entra IDは親組織に割り当てられたポリシーを適用します。
- ドメイン ヒントまたはポリシーが割り当てられていない場合は、既定の HRD 動作が適用されます。

Note

HRD ポリシーは、モバイル プラットフォームと macOS のブローカー認証では機能しません。 この制限には、モバイル プラットフォーム上のMicrosoft Authenticator アプリまたは Mac のポータル サイト アプリの使用が含まれます。 このような場合に自動高速化が必要な場合は、呼び出し元アプリの認証要求でドメイン ヒントを渡す必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/howto-enforce-signed-saml-authentication"} -->
## 署名付き SAML 認証要求を適用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-enforce-signed-saml-authentication
- Service: entra-id / enterprise-apps
- Article date: 2025-07-10
- Summary: 署名付き SAML 認証要求を適用する方法について説明します。

SAML 要求の署名検証は、署名された認証要求の署名を検証する機能です。 アプリ管理者は、署名された要求の適用を有効または無効にし、検証の実行に使用する公開キーをアップロードできます。

有効にした場合、Microsoft Entra ID は、構成された公開キーに対して要求を検証します。 認証要求が失敗する可能性があるシナリオがいくつかあります。

- プロトコルで署名済み要求が許可されていない。 SAML プロトコルのみがサポートされている。
- 要求は署名されていないが、検証は有効になっている。
- SAML 要求の署名検証用に構成された検証証明書がない。 証明書の要件の詳細については、「 [証明書の署名オプション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/certificate-signing-options)」を参照してください。
- 署名の確認に失敗しました。
- 要求のキー識別子が見つからず、最近追加された 2 つの証明書が要求署名と一致しない。
- 要求が署名されていても、アルゴリズムがない。
- 指定されたキー識別子と一致する証明書なない。
- 署名アルゴリズムが許可されていない。 RSA-SHA256 のみがサポートされている。

注

`Signature` 要素内の `AuthnRequest` 要素は省略可能です。 `Require Verification certificates`チェックされていない場合、署名が存在する場合、Microsoft Entra ID は署名された認証要求を検証しません。 要求元の検証は、登録されている Assertion Consumer Service URL に応答することによってのみ提供されます。

>
> `Require Verification certificates` がチェックされた場合、SAML 要求署名検証は SP によって開始される (サービス プロバイダー/証明書利用者が開始した) 認証要求に対してのみ機能します。 サービス プロバイダーによって構成されたアプリケーションのみが、アプリケーションからの受信 SAML 認証 Reqeusts に署名するための秘密キーと公開キーへのアクセス権を持ちます。 要求の検証を許可するために公開キーをアップロードする必要があります。この場合、Microsoft Entra ID は公開キーにのみアクセスできます。

>
> `Require Verification certificates` を有効にすると、IDP が登録済みアプリケーションと同じ秘密キーを持っていないので、IDP によって開始される認証要求 (SSO テスト機能、MyApps、M365 アプリ起動ツールなど) は検証されません。

### 前提条件

SAML 要求の署名検証を構成するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者、またはサービス プリンシパルの所有者。

### SAML 要求の署名検証を構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. **[シングル サインオン**] に移動します。
5. **[シングル サインオン**] 画面で、[**SAML 証明書**] の下にある **[検証証明書**] というサブセクションまでスクロールします。

    [Image: [エンタープライズ アプリケーション] ページの [SAML 証明書] にある検証証明書のスクリーンショット。]
6. [ **編集] を選択します。**
7. アプリケーションで引き続き RSA-SHA1 を使用して認証要求に署名する場合に備えて、新しいペインでは、署名済み要求の検証を有効にし、脆弱なアルゴリズムの検証をオプトインできます。
8. 署名された要求の検証を有効にするには、[ **確認証明書を要求** する] を選択し、要求の署名に使用される秘密キーと一致する検証公開キーをアップロードします。

    [Image: [エンタープライズ アプリケーション] ページの [確認証明書の要求] のスクリーンショット。]
9. 確認証明書をアップロードしたら、[ **保存]** を選択します。
10. 署名された要求の検証を有効にすると、サービス プロバイダーが要求に署名する必要があるため、テスト エクスペリエンスは無効になります。

    [Image: [エンタープライズ アプリケーション] ページで署名された要求が有効になっている場合の無効な警告のテストのスクリーンショット。]
11. エンタープライズ アプリケーションの現在の構成を確認する場合は、[ **シングル サインオン** ] 画面に移動し、[ **SAML 証明書**] で構成の概要を確認できます。 ここでは、署名された要求の検証が有効かどうか、およびアクティブな検証証明書と期限切れの検証証明書の数を確認できます。

    [Image: シングル サインオン画面のエンタープライズ アプリケーション構成のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/howto-saml-token-encryption"} -->
## Microsoft Entra の SAML トークン暗号化を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption
- Service: entra-id / enterprise-apps
- Article date: 2025-03-06
- Summary: Microsoft Entra SAML トークン暗号化を構成する方法について説明します。

注

トークン暗号化は、Microsoft Entra ID P1 または P2 の機能です。 Microsoft Entra のエディション、機能、価格の詳細については、 [Microsoft Entra の価格](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)に関するページを参照してください。

SAML トークン暗号化を使用すると、それをサポートしているアプリケーションで、暗号化された SAML アサーションを使用できるようになります。 アプリケーションに対して構成すると、そのアプリケーション用に出力される SAML アサーションが Microsoft Entra ID によって暗号化されます。 SAML アサーションは、Microsoft Entra ID に格納されている証明書から取得した公開キーを使用して暗号化されます。 アプリケーションでは、対応する秘密キーを使用してトークンを復号化する必要があります。これにより、現在サインインしているユーザーの認証の証拠として、そのトークンを使用できるようになります。

Microsoft Entra ID とアプリケーションの間で SAML アサーションを暗号化すると、トークンの内容がインターセプトされるのをより強力に防護して、個人や会社のデータが侵害されるのを防ぐことができます。

トークン暗号化を使用しない場合でも、Microsoft Entra の SAML トークンがネットワーク上でクリア テキストのまま渡されることはありません。 Microsoft Entra ID では、トークンの要求/応答の交換が、暗号化された HTTPS/TLS チャネル経由で行われるようにする必要があります。これにより、IDP、ブラウザー、およびアプリケーション間の通信が、暗号化されたリンク経由で行われるようになります。 お客様の環境でトークン暗号化を使用するメリットを、もっと多くの証明書の管理で生じるオーバーヘッドと比較して検討してください。

トークン暗号化を構成するには、公開キーを含んだ X.509 証明書ファイルを、アプリケーションを表す Microsoft Entra アプリケーション オブジェクトにアップロードする必要があります。

X.509 証明書を取得するには、アプリケーション自体からダウンロードします。 アプリケーション ベンダーが暗号化キーを提供している場合は、そのアプリケーション ベンダーから証明書を取得することもできます。 アプリケーションで秘密キーを指定する必要がある場合は、暗号化ツールを使用して作成できます。 秘密キー部分はアプリケーションのキー ストアにアップロードされ、一致する公開キー証明書は Microsoft Entra ID にアップロードされます。

Microsoft Entra ID では、SAML アサーション データの暗号化に AES-256 が使われます。

### 前提条件

SAML トークン暗号化を構成するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者
    - サービス プリンシパルの所有者

### エンタープライズ アプリケーションの SAML トークン暗号化を構成する

このセクションでは、エンタープライズ アプリケーションの SAML トークン暗号化を構成する方法について説明します。 これらのアプリケーションは、Microsoft Entra 管理センターの **[エンタープライズ アプリケーション** ] ウィンドウから、アプリケーション ギャラリーまたはギャラリー以外のアプリから設定されます。 アプリ登録エクスペリエンスを通じて登録されたアプリケーション **の場合は** 、「 登録済みアプリケーション SAML トークン暗号化の構成 」ガイダンスに従ってください。

エンタープライズ アプリケーションの SAML トークン暗号化を構成するには、次の手順に従います。

1. アプリケーションで構成されている秘密キーに一致する公開キー証明書を取得します。

    暗号化に使用する非対称キー ペアを作成します。 なお、暗号化に使用する公開キーがアプリケーションで提供される場合は、アプリケーションの指示に従って X.509 証明書をダウンロードします。

    公開キーは、.cer 形式の X.509 証明書ファイルに格納する必要があります。 証明書ファイルの内容をテキスト エディターにコピーし、.cer ファイルとして保存できます。 証明書ファイルには公開キーのみを含め、秘密キーは含めないようにする必要があります。

    インスタンス用に作成したキーがアプリケーションで使用される場合は、アプリケーションの指示に従って、Microsoft Entra テナントからのトークンの復号化に使用される秘密キーをインストールします。
2. Microsoft Entra ID のアプリケーション構成に証明書を追加します。

#### Microsoft Entra 管理センターでトークン暗号化を構成する

Microsoft Entra 管理センター内で、アプリケーション構成に公開証明書を追加できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. アプリケーションのページで、[ **トークン**暗号化] を選択します。

    注

    **[トークン暗号化**] オプションは、Microsoft Entra 管理センターの **[エンタープライズ アプリケーション**] ウィンドウから設定された SAML アプリケーション (アプリケーション ギャラリーまたはギャラリー以外のアプリ) でのみ使用できます。 その他のアプリケーションについては、このオプションは無効化されています。
5. [ **トークン暗号化** ] ページで、[ **証明書のインポート** ] を選択して、パブリック X.509 証明書を含む.cer ファイルをインポートします。

    [Image: Microsoft Entra 管理センターを使用して証明書ファイルをインポートする方法を示すスクリーンショット。]
6. 証明書がインポートされ、秘密キーがアプリケーション側で使用されるように構成されたら、拇印の状態の横にある **...** を選択して暗号化をアクティブ化し、ドロップダウン メニューのオプションから [ **トークン暗号化のアクティブ化** ] を選択します。
7. トークン暗号化証明書のアクティブ化を確認するには、[ **はい** ] を選択します。
8. アプリケーション用に出力された SAML アサーションが暗号化されたことを確認します。

#### Microsoft Entra 管理センターでトークン暗号化を非アクティブ化する

1. Microsoft Entra 管理センターで、 **Entra ID**&gt;**Enterprise アプリ**&gt;**すべてのアプリケーション**を参照し、SAML トークン暗号化が有効になっているアプリケーションを選択します。
2. アプリケーションのページで、[ **トークン暗号化**] を選択し、証明書を見つけて、[ **...** ] オプションを選択してドロップダウン メニューを表示します。
3. [ **トークン暗号化の非アクティブ化]** を選択します。

### 登録済みアプリケーションの SAML トークン暗号化を構成する

このセクションでは、登録済みアプリケーションの SAML トークン暗号化を構成する方法について説明します。 これらのアプリケーションは、Microsoft Entra 管理センターの [ **アプリの登録** ] ウィンドウから設定されます。 エンタープライズ アプリケーションの場合は、 エンタープライズ アプリケーションの SAML トークン暗号化の構成 に関するガイダンスに従ってください。

暗号化証明書は、`encrypt` 使用タグを使用して Microsoft Entra ID 内のアプリケーション オブジェクトに格納されます。 暗号化証明書は複数構成できます。トークンの暗号化用にアクティブ化された証明書は、`tokenEncryptionKeyID` 属性によって識別されます。

Microsoft Graph API または PowerShell を使用してトークン暗号化を構成するには、アプリケーションのオブジェクト ID が必要になります。 この値はプログラムで見つけることができます。または、Microsoft Entra 管理センターのアプリケーションの **[プロパティ]** ページに移動し、 **オブジェクト ID** の値を確認します。

Graph、PowerShell、またはアプリケーション マニフェストを使用して keyCredential を構成する場合は、keyId に使用する GUID を生成する必要があります。

アプリケーション登録のトークン暗号化を構成するには、次の手順を実行します。

## [ポータル](#tab/azure-portal)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**アプリケーションの登録**&gt;**すべてのアプリケーション**に移動します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. アプリケーションのページで、[ **マニフェスト** ] を選択して [アプリケーション マニフェスト](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-app-manifest)を編集します。

    次の例は、2 つの暗号化証明書を使用して構成されたアプリケーション マニフェストを示したものです。2 つ目の証明書は、tokenEncryptionKeyId を使用してアクティブな証明書として選択されています。

    ```json
    { 
      "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
      "accessTokenAcceptedVersion": null,
      "allowPublicClient": false,
      "appId": "00001111-aaaa-2222-bbbb-3333cccc4444",
      "appRoles": [],
      "oauth2AllowUrlPathMatching": false,
      "createdDateTime": "2017-12-15T02:10:56Z",
      "groupMembershipClaims": "SecurityGroup",
      "informationalUrls": { 
         "termsOfService": null, 
         "support": null, 
         "privacy": null, 
         "marketing": null 
      },
      "identifierUris": [ 
        "https://testapp"
      ],
      "keyCredentials": [ 
        { 
          "customKeyIdentifier": "Tog/O1Hv1LtdsbPU5nPphbMduD=", 
          "endDate": "2039-12-31T23:59:59Z", 
          "keyId": "aaaaaaaa-0b0b-1c1c-2d2d-333333333333", 
          "startDate": "2018-10-25T21:42:18Z", 
          "type": "AsymmetricX509Cert", 
          "usage": "Encrypt", 
          "value": <Base64EncodedKeyFile> 
          "displayName": "CN=SAMLEncryptTest" 
        }, 
        {
          "customKeyIdentifier": "A1bC2dE3fH4iJ5kL6mN7oP8qR9sT0u=",
          "endDate": "2039-12-31T23:59:59Z", 
          "keyId": "bbbbbbbb-1c1c-2d2d-3e3e-444444444444",
          "startDate": "2018-10-25T21:42:18Z", 
          "type": "AsymmetricX509Cert", 
          "usage": "Encrypt", 
          "value": <Base64EncodedKeyFile> 
          "displayName": "CN=SAMLEncryptTest2" 
        } 
      ], 
      "knownClientApplications": [], 
      "logoUrl": null, 
      "logoutUrl": null, 
      "name": "Test SAML Application", 
      "oauth2AllowIdTokenImplicitFlow": true, 
      "oauth2AllowImplicitFlow": false, 
      "oauth2Permissions": [], 
      "oauth2RequirePostResponse": false, 
      "orgRestrictions": [], 
      "parentalControlSettings": { 
         "countriesBlockedForMinors": [], 
         "legalAgeGroupRule": "Allow" 
        }, 
      "passwordCredentials": [], 
      "preAuthorizedApplications": [], 
      "publisherDomain": null, 
      "replyUrlsWithType": [], 
      "requiredResourceAccess": [], 
      "samlMetadataUrl": null, 
      "signInUrl": "https://127.0.0.1:444/applications/default.aspx?metadata=customappsso|ISV9.1|primary|z" 
      "signInAudience": "AzureADMyOrg",
      "tags": [], 
      "tokenEncryptionKeyId": "bbbbbbbb-1c1c-2d2d-3e3e-444444444444" 
    }  
    ```

## [Microsoft Graph PowerShell](#tab/msgraph-powershell)
1. Microsoft Graph PowerShell モジュールを使用してテナントに接続します。 少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。
2. **[Update-MgApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/update-mgapplication?view=graph-powershell-1.0&preserve-view=true)** コマンドを使用してトークン暗号化設定を設定します。

    ```powershell
    connect-MgGraph -Scopes "Application.ReadWrite.All"
    Update-MgApplication -ApplicationId <ApplicationObjectId> -KeyCredentials "<KeyCredentialsObject>"  -TokenEncryptionKeyId <keyID>
    
    ```
3. 次のコマンドを使用して、トークン暗号化設定を読み取ります。

    ```powershell
    
    $app=Get-MgApplication -ApplicationId <ApplicationObjectId>
    
    $app.KeyCredentials
    
    $app.TokenEncryptionKeyId
    
    ```

## [Microsoft Graph](#tab/microsoft-graph)
1. 暗号化用の X.509 証明書を使用して、アプリケーションの `keyCredentials` を更新します。 次の例は、アプリケーションに関連付けられているキー資格情報のコレクションを含む Microsoft Graph JSON ペイロードを示しています。

    `Application.ReadWrite.All` アクセス許可に同意していることを確認します。

    ```HTTP
    PATCH https://graph.microsoft.com/beta/applications/<application objectid>
    
    { 
       "keyCredentials":[ 
          { 
             "type":"AsymmetricX509Cert","usage":"Encrypt",
             "keyId":"aaaaaaaa-0b0b-1c1c-2d2d-333333333333",    (Use a GUID generator to obtain a value for the keyId)
             "key": "MIICADCCAW2gAwIBAgIQ5j9/b+n2Q4pDvQUCcy3…"  (Base64Encoded .cer file)
          }
        ]
    }
    ```
2. トークンの暗号化用にアクティブ化された暗号化証明書を特定します。 次の例は、`tokenEncryptionKeyId` 要素を含む Microsoft Graph JSON ペイロードを示しています。 この `tokenEncryptionKeyId` 要素は、`keyCredentials` コレクションの公開キーのキー ID を指定します。

    ```HTTP
    PATCH https://graph.microsoft.com/beta/applications/<application objectid> 
    
    { 
       "tokenEncryptionKeyId":"aaaaaaaa-0b0b-1c1c-2d2d-333333333333" (The keyId of the keyCredentials entry to use)
    }
    ```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/manage-app-consent-policies"} -->
## アプリへの同意ポリシーを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-app-consent-policies
- Service: entra-id / enterprise-apps
- Article date: 2026-01-22
- Summary: 組み込みおよびカスタムのアプリ同意ポリシーを管理して、どのような場合に同意を許可できるかを制御する方法について説明します。

アプリ同意ポリシーは、アプリが組織内のデータにアクセスするために必要なアクセス許可を管理する方法です。 ユーザーが同意できるアプリを制御し、ユーザーがデータにアクセスする前にアプリが特定の条件を満たしていることを確認するために使用されます。 これらのポリシーは、組織がデータの制御を維持し、信頼できるアプリにのみアクセス権を付与するのに役立ちます。 [Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/overview) と [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started?view=graph-powershell-1.0&preserve-view=true) を使用すると、アプリの同意ポリシーを表示および管理できます。

この記事では、組み込みおよびカスタムのアプリ同意ポリシーを管理して、どのような場合に同意を許可できるかを制御する方法について説明します。 アプリの同意ポリシーは、カスタム ロールを使用して特定のユーザーまたはグループに割り当てることも、組織内のエンド ユーザーに既定のアプリ同意ポリシーを設定することもできます。

注

アプリの同意ポリシーを使用する以外に、ユーザーまたはサービス プリンシパルに同意を付与する機能を付与する方法は他にもあります。 同意ポリシーがアタッチされているプリンシパル割り当てロールの一覧は、組織内で同意を付与できる内容の完全な一覧として使用しないでください。

### アプリの同意ポリシー セグメント

アプリ同意ポリシーは、1 つ以上の "包含" 条件セットと、0 個以上の "除外" 条件セットで構成されます。 イベントがアプリ同意ポリシーにおいて考慮されるには、"いずれか" の "除外" 条件セットにではなく、"少なくとも" 1 つの "包含" 条件セットに一致している必要があります。 除外と包含は、特定のポリシーの影響を受けるアクターが同意を付与できるかどうかを判断するために使用されます。

アプリの同意ポリシーには、主に次の 3 つの部分があります。

- **メタデータ:** アプリの同意ポリシーのプロパティは、ID、説明、同意ポリシーの表示名などの情報を保持します。
- **含まれる条件セット:** 特定のアプリの同意要求がポリシーを通過させるために *少なくとも 1 つと* 一致する必要がある条件セットのコレクション。 このコレクションには、少なくとも *1 つの* 条件が設定されている必要があります。 各条件セットには、検証済みの発行元の状態、要求されたアクセス許可など、アプリの同意要求の特性を記述する規則が含まれています。
- **除外条件セット:** 特定のアプリ同意要求がポリシーに合格するために、*いずれの*条件セットにも一致してはならない条件セットのコレクション。 このコレクションは空にすることができます (除外された条件セットを 0 個含めることができます)。 各条件セットには、検証済みの発行元の状態、要求されたアクセス許可など、アプリの同意要求の特性を記述する規則が含まれています。

#### サポートされている条件

各条件セットは、いくつかの条件で構成されます。 イベントが条件セットに一致するためには、条件セット内の "*すべての*" 条件が満たされる必要があります。 たとえば、条件セットで「発行元が確認済みで、このテナント内で作成され、Microsoft Graph の Mail.Read の委任を要求するクライアント アプリケーション」と指定した場合、発行元が確認済みで、テナント内で作成され、openid およびプロファイル スコープを要求するクライアント アプリケーションに対する同意要求には一致しません。

条件セットには、要求されたアプリまたはアクセス許可の特性を定義するために使用される 1 つ以上のプロパティが含まれます。 プロパティの完全な一覧はこちら [にあります。](https://learn.microsoft.com/ja-jp/graph/api/resources/permissiongrantconditionset)

### 組み込みの同意ポリシー

すべてのテナントには、すべてのテナントで同じアプリ同意ポリシーのセットが付属しています。 これらの組み込みポリシーの一部は、既存の組み込みディレクトリ ロールで使用されます。 たとえば、`microsoft-application-admin` というアプリ同意ポリシーには、アプリケーション管理者とクラウド アプリケーション管理者のロールが、テナント全体にわたる管理者の同意を許可される条件が記述されています。 組み込みポリシーは、カスタム ディレクトリ ロールで使用することも、組織の既定の同意ポリシーを構成することもできます。 これらのポリシーは編集できません。 組み込みポリシーの一覧は次のとおりです。

- **microsoft-user-default-low:** 既定では、すべてのリスクの低いアクセス許可にメンバータイプのユーザーが同意できます。
- **microsoft-user-default-recommended:** 現在の Microsoft の推奨事項に基づいて同意可能なアクセス許可。
- **microsoft-user-default-allow-consent-apps:** ユーザーが同意できる人気のあるメール クライアント
- **microsoft-all-application-permissions:** すべてのクライアント アプリケーションのすべての API に対するすべてのアプリケーションアクセス許可 (アプリ ロール) が含まれます。
- **microsoft-dynamically-managed-permissions-for-chat:** チャット リソース固有の同意に許可される動的に管理されたアクセス許可が含まれます。
- **microsoft-all-application-permissions-for-chat:** すべてのクライアント アプリケーションについて、すべての API に対するすべてのチャット リソース固有のアプリケーションのアクセス許可が含まれます。
- **microsoft-dynamically-managed-permissions-for-team:** チーム リソース固有の同意に対して許可される動的に管理されたアクセス許可が含まれます。
- **microsoft-pre-approval-apps-for-chat:** チャットのリソース固有の同意のための事前承認ポリシーによって許可されたアプリが含まれています。
- **microsoft-pre-approval-apps-for-team:** チーム リソース固有の同意に対する承認前の許可ポリシーによって事前に承認されたアプリが含まれます。
- **microsoft-all-application-permissions-verified:** すべての API、検証済み発行元のクライアント アプリケーション、またはこの組織に登録されたクライアント アプリケーションのすべてのアプリケーションアクセス許可 (アプリ ロール) が含まれます。
- **microsoft-application-admin:** アプリケーション管理者が同意できるアクセス許可。
- **microsoft-company-admin:** 会社の管理者が同意できるアクセス許可。

警告

Microsoft-user-default-recommended および microsoft-user-default-allow-consent-apps は、Microsoft マネージド ポリシーです。 ポリシーに含まれる条件は、エンド ユーザーの同意に関するMicrosoftの最新のセキュリティに関する推奨事項に基づいて自動的に更新されます。

### Microsoftの推奨設定

#### Microsoftの推奨ユーザー同意ポリシー

Microsoft管理ポリシーの "同意設定Microsoft管理できるようにする" というラベルの付いた設定は、Microsoftの最新の推奨される既定の同意設定で更新されます。 これは、新しいテナントの既定値でもあります。 現在、設定のルールは次のとおりです。エンド ユーザーは、次を除き、ユーザーが同意できる委任されたアクセス許可に対して同意できます。

- Microsoft Graph の場合: `Files.Read.All`、`Files.ReadWrite.All`、`Sites.Read.All`、`Sites.ReadWrite.All`、`Mail.Read`、`Mail.ReadWrite`、`Mail.ReadBasic`、`Mail.Read.Shared`、`Mail.ReadBasic.Shared`、`Mail.ReadWrite.Shared`、`MailboxItem.Read`、`Calendars.Read`、`Calendars.ReadBasic`、`Calendars.ReadWrite`、`Calendars.Read.Shared`、`Calendars.ReadWrite.Shared`、`Chat.Read`、`Chat.ReadWrite`、`OnlineMeetings.Read`、`OnlineMeetings.ReadWrite`、`MailBoxFolder.Read`、`MailBoxFolder.ReadWrite`、`MailBoxSettings.Read`、`MailBoxSettings.ReadWrite`、`Contacts.ReadWrite`、`Contacts.Read.Shared`、`Contacts.ReadWrite.Shared`、`Tasks.Read`、`Tasks.Read.Shared`、`Tasks.ReadWrite`、`Tasks.ReadWrite.Shared`、`People.Read`。
- Office 365 Exchange Onlineの場合: `EAS.AccessAsUser.All`、`EWS.AccessAsUser.All`、`IMAP.AccessAsUser.All`、`POP.AccessAsUser.All`。

#### メール クライアント ポリシー

既定で有効になっている追加のポリシーは、 **microsoft-user-allow-default-consent-apps** ポリシーです。 このポリシーを使用すると、組織内のエンド ユーザーは、一般的なメール アプリケーションのメールアクセス許可に同意できます。 このポリシーを有効にすると、エンド ユーザーは、次のアプリケーションの特定の委任されたメールアクセス許可 (上記のすべてのMicrosoft GraphとOffice 365 Exchange Onlineのアクセス許可) に同意できます。

- Apple Mail (アプリケーション ID: f8d98a96-0999-43f5-8af3-69971c7bb423)
- Spark 電子メール (アプリケーション ID:b50c1dbd-1855-4e54-b07c-d3c3029e93d3)
- eM クライアント (アプリケーション ID:e9a7fea1-1cc0-4cd9-a31b-9137ca5deedd)
- Android-Samsung (アプリケーション ID:8acd33ea-7197-4a96-bc33-d7cc7101262f)
- Android-Mail (アプリケーション ID:2cee05de-2b8f-45a2-8289-2a06ca32c4c8)
- Thunderbird (アプリケーション ID:9e5f94bc-e8a4-4e73-b8be-63364c29d753)

### 同意を付与するための複数のポリシーまたは承認メカニズム

ユーザーは、同意を許可する複数のポリシーを持つことができます。 各ポリシーは個別に評価され (1 つのポリシーからの除外は別のポリシーの包含には影響しません)、ユーザーは特定のイベントに対する同意を許可するために承認するポリシーを 1 つだけ必要とします。 たとえば、アプリケーション管理者は(すべてのユーザーに適用される既定のポリシーのおかげで) 通常のユーザーができることすべてに同意でき、microsoft-application-admin ポリシーを通じてより広範なアクセス許可を持ちます。これにより、アプリ ロールを除き、API アクセス許可の要求Microsoft Graph承認できます。

同様に、ユーザーまたはサービス プリンシパルには、アプリの同意ポリシー以外の方法で同意を付与する機能を付与できます。 たとえば、サービス プリンシパルに "所有者" として割り当てられているユーザーは、同意ポリシーがアタッチされたロールが割り当てられていない場合でも、サービス プリンシパルが公開するアプリ ロールに同意を付与できます。`Application.ReadWrite.All` アプリケーションのアクセス許可が割り当てられているアプリケーションは、(Microsoft Graph によって公開されているものを除く) 任意のアプリ ロールに同意を付与できます。 ユーザーまたはサービス プリンシパルは、特定のイベントに対する同意を許可するために承認する承認メカニズムを 1 つだけ必要とします。

### 前提条件

- 次のいずれかのロールを持つユーザーまたはサービス:
    - 特権ロール管理者ディレクトリ ロール
    - [アプリへの同意ポリシーを管理するために必要なアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-consent-permissions#managing-app-consent-policies)を持つカスタム ディレクトリ ロール
    - アプリまたはサービスとして接続する場合のMicrosoft Graph アプリ ロール (アプリケーションのアクセス許可) `Policy.ReadWrite.PermissionGrant`
- (アクセス許可付与条件セット)[/graph/api/resources/permissiongrantconditionset?view=graph-rest-1.0] について理解する

::: zone pivot="ms-powershell"

Microsoft Graph PowerShell を使用してアプリケーションのアプリの同意ポリシーを管理するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started?view=graph-powershell-1.0&preserve-view=true) に接続します。

```powershell
Connect-MgGraph -Scopes "Policy.ReadWrite.PermissionGrant"
```

### 既存のアプリ同意ポリシーを一覧表示する

まず、組織内の既存のアプリ同意ポリシーについて理解しておくことをお勧めします。

1. すべてのアプリの同意ポリシーを一覧表示します。 これには、組織が作成したすべての組み込みポリシーとカスタム ポリシーが表示されます。

    ```powershell
    Get-MgPolicyPermissionGrantPolicy | ft Id, DisplayName, Description
    ```
2. ポリシーの "包含" 条件セットを表示します。

    ```powershell
    Get-MgPolicyPermissionGrantPolicyInclude -PermissionGrantPolicyId "microsoft-application-admin" | fl
    ```
3. "除外" 条件セットを表示します。

    ```powershell
    Get-MgPolicyPermissionGrantPolicyExclude -PermissionGrantPolicyId "microsoft-application-admin" | fl
    ```

### PowerShell を使用してカスタム アプリ同意ポリシーを作成する

カスタムのアプリ同意ポリシーを作成するには、次の手順に従います。

1. 新しい空のアプリ同意ポリシーを作成します。

    ```powershell
    $params = @{
     Id          = "my-custom-policy"
     DisplayName = "My first custom consent policy"
     Description = "This is a sample custom app consent policy."
    }
    
    New-MgPolicyPermissionGrantPolicy @params
    ```
2. "包含" 条件セットを追加します。

    ```powershell
    # Include delegated permissions classified "low", for apps from verified publishers
    New-MgPolicyPermissionGrantPolicyInclude `
        -PermissionGrantPolicyId "my-custom-policy" `
        -PermissionType "delegated" `
        -PermissionClassification "low" `
        -ClientApplicationsFromVerifiedPublisherOnly
    $params = @{
      PermissionGrantPolicyId                     = "my-custom-policy"
      PermissionType                               = "delegated"
      PermissionClassification                     = "low"
      ClientApplicationsFromVerifiedPublisherOnly  = $true
    }
    
    New-MgPolicyPermissionGrantPolicyInclude @params
    ```

    この手順を繰り返して、さらに "包含" 条件セットを追加します。
3. 必要に応じて、"除外" 条件セットを追加します。

    ```powershell
    # Retrieve the service principal for the Azure Management API
    $azureApi = Get-MgServicePrincipal -Filter "servicePrincipalNames/any(n:n eq 'https://management.azure.com/')"
    
    # Exclude delegated permissions for the Azure Management API
    New-MgPolicyPermissionGrantPolicyExclude `
        -PermissionGrantPolicyId "my-custom-policy" `
        -PermissionType "delegated" `
        -ResourceApplication $azureApi.AppId
    $params = @{
     PermissionGrantPolicyId = "my-custom-policy"
     PermissionType           = "delegated"
     ResourceApplication      = $azureApi.AppId
    }
    
    New-MgPolicyPermissionGrantPolicyExclude @params
    ```

    この手順を繰り返して、さらに "除外" 条件セットを追加します。

アプリの同意ポリシーを作成した後、Microsoft Entra IDのカスタム ロールに割り当てる必要があります。 その後、作成したアプリ同意ポリシーにアタッチされているそのカスタム役割にユーザーを割り当てる必要があります。 アプリ同意ポリシーをカスタム役割に割り当てる方法の詳細については、「[カスタム役割に対するアプリ同意のアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-consent-permissions)」を参照してください。

### PowerShell を使用してカスタム アプリ同意ポリシーを削除する

次のコマンドレットは、カスタムのアプリ同意ポリシーを削除する方法を示しています。

```powershell
   Remove-MgPolicyPermissionGrantPolicy -PermissionGrantPolicyId "my-custom-policy"
```

::: zone-end

::: zone pivot="ms-graph"

アプリの同意ポリシーを管理するには、前提条件セクションに記載されているいずれかのロールで [Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)にサインインします。

`Policy.ReadWrite.PermissionGrant` アクセス許可に同意する必要があります。

### Microsoft Graphを使用して既存のアプリ同意ポリシーを一覧表示する

まず、組織内の既存のアプリ同意ポリシーについて理解しておくことをお勧めします。

1. すべてのアプリの同意ポリシーを一覧表示します。 これには、組織が作成したすべての組み込みポリシーとカスタム ポリシーが表示されます。

    ```http
    GET /policies/permissionGrantPolicies?$select=id,displayName,description
    ```
2. ポリシーの "包含" 条件セットを表示します。

    ```http
    GET /policies/permissionGrantPolicies/{ microsoft-application-admin }/includes
    ```
3. "除外" 条件セットを表示します。

    ```http
    GET /policies/permissionGrantPolicies/{ microsoft-application-admin }/excludes
    ```

### Microsoft Graphを使用してカスタム アプリの同意ポリシーを作成する

カスタムのアプリ同意ポリシーを作成するには、次の手順に従います。

1. 新しい空のアプリ同意ポリシーを作成します。

    ```http
    POST https://graph.microsoft.com/v1.0/policies/permissionGrantPolicies
    Content-Type: application/json
    
    {
      "id": "my-custom-policy",
      "displayName": "My first custom consent policy",
      "description": "This is a sample custom app consent policy"
    }
    ```
2. "包含" 条件セットを追加します。

    検証済みの発行元からのアプリに対して、"低" に分類されている委任されたアクセス許可を含める

    ```http
    POST https://graph.microsoft.com/v1.0/policies/permissionGrantPolicies/{ my-custom-policy }/includes
    Content-Type: application/json
    
    {
      "permissionType": "delegated",
      "PermissionClassification": "low",
      "clientApplicationsFromVerifiedPublisherOnly": true
    }
    ```

    この手順を繰り返して、さらに "包含" 条件セットを追加します。
3. 必要に応じて、"除外" 条件セットを追加します。 Azure Management API の委任されたアクセス許可を除外する (appId 00001111-aaaa-2222-bbbb-3333cccc4444)

    ```http
    POST https://graph.microsoft.com/v1.0/policies/permissionGrantPolicies/my-custom-policy /excludes
    Content-Type: application/json
    
    {
      "permissionType": "delegated",
      "resourceApplication": "00001111-aaaa-2222-bbbb-3333cccc4444 "
    }
    ```

    この手順を繰り返して、さらに "除外" 条件セットを追加します。

アプリの同意ポリシーを作成した後、Microsoft Entra IDのカスタム ロールに割り当てる必要があります。 その後、作成したアプリ同意ポリシーにアタッチされているそのカスタム役割にユーザーを割り当てる必要があります。 アプリ同意ポリシーをカスタム役割に割り当てる方法の詳細については、「[カスタム役割に対するアプリ同意のアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-consent-permissions)」を参照してください。

### カスタム アプリの同意ポリシー Microsoft Graphを削除する

1. カスタムのアプリ同意ポリシーを削除する方法を次に示します。

    ```http
    DELETE https://graph.microsoft.com/v1.0/policies/permissionGrantPolicies/ my-custom-policy
    ```

::: zone-end

警告

削除されたアプリの同意ポリシーを復元することはできません。 カスタム アプリの同意ポリシーを誤って削除した場合は、ポリシーを再作成する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/manage-application-permissions"} -->
## エンタープライズ アプリケーションに付与されるアクセス許可の確認 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions
- Service: entra-id / enterprise-apps
- Article date: 2025-03-03
- Summary: Microsoft Entra ID のアプリケーションでアクセス許可を確認して取り消す方法と、更新トークンを無効にする方法について説明します。

この記事では、Microsoft Entra テナントのアプリケーションに付与されているアクセス許可を確認する方法について説明します。 悪意のあるアプリケーションが検出された場合、またはアプリケーションに必要以上のアクセス許可が付与されている場合は、アクセス許可の確認が必要になることがあります。 Microsoft Graph API と既存バージョンの PowerShell を使用して、アプリケーションに付与されたアクセス許可を取り消す方法について説明します。

この記事に含まれる手順は、ユーザーまたは管理者の同意によって Microsoft Entra テナントに追加されたすべてのアプリケーションに適用されます。 アプリケーションへの同意の詳細については、「 [ユーザーと管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/user-admin-consent-overview)」を参照してください。

### 前提条件

アプリケーションに付与されたアクセス許可を確認するには、次のものが必要です。

- アクティブなサブスクリプションを持つ Microsoft Entra アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者。
    - 管理者ではないサービス プリンシパル所有者は、更新トークンを無効にできます。

::: zone pivot="portal"

### Microsoft Entra 管理センターでアクセス許可を確認および取り消す

Microsoft Entra 管理センターにアクセスして、アプリに付与されたアクセス許可を表示できます。 管理者が組織全体に付与したアクセス許可を取り消したり、コンテキスト PowerShell スクリプトを取得して他のアクションを実行したりできます。

取り消されたアクセス許可または削除されたアクセス許可を復元する方法については、「 [アプリケーションに付与されたアクセス許可を復元する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/restore-permissions)参照してください。

組織全体または特定のユーザーまたはグループに対して付与されているアプリケーションのアクセス許可を監視するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**すべてのアプリケーション**を参照します。
3. アクセスを制限するアプリケーションを選択します。
4. [ **アクセス許可] を選択します**。
5. 組織全体に適用されるアクセス許可を表示するには、[ **管理者の同意** ] タブを選択します。特定のユーザーまたはグループに付与されたアクセス許可を表示するには、[ **ユーザーの同意** ] タブを選択します。
6. 所定のアクセス許可の詳細を表示するには、一覧からアクセス許可を選択します。 [ **アクセス許可の詳細** ] ウィンドウが開きます。 アプリケーションに付与されたアクセス許可を確認した後で、組織全体に対して管理者によって付与されたアクセス許可を取り消すことができます。
    注記

    ポータルを使用して、[ **ユーザーの同意** ] タブでアクセス許可を取り消すことはできません。 これらのアクセス許可は、Microsoft Graph API 呼び出しまたは PowerShell コマンドレットを使用して取り消すことができます。 詳細については、この記事の「PowerShell」タブおよび「Microsoft Graph」タブを参照してください。

**[管理者の同意**] タブでアクセス許可を取り消すには:

1. **[管理者の同意**] タブでアクセス許可の一覧を表示します。
2. 取り消すアクセス許可を選択し、そのアクセス許可の **...** コントロールを選択します。 [Image: 管理者の同意を取り消す方法を示すスクリーンショット。]
3. [ **権限の取り消し**] を選択します。

::: zone-end

::: zone pivot="entra-powershell"

### Microsoft Entra PowerShell を使用してアクセス許可を確認および取り消す

次の Microsoft Entra PowerShell スクリプトを使用して、アプリケーションに付与されたすべてのアクセス許可を取り消します。 少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。

```powershell
Connect-Entra -scopes "Application.ReadWrite.All", "DelegatedPermissionGrant.ReadWrite.All", "AppRoleAssignment.ReadWrite.All" 

# Get Service Principal using objectId
$app_name = "<app-displayName>"
$sp = Get-EntraServicePrincipal -Filter "displayName eq '$app_name'"

# Get all delegated permissions for the service principal
$spOAuth2PermissionsGrants = Get-EntraOAuth2PermissionGrant -All | Where-Object { $_.clientId -eq $sp.ObjectId }

# Remove all delegated permissions granted to the service principal
$spOAuth2PermissionsGrants | ForEach-Object {
    Remove-EntraOAuth2PermissionGrant -ObjectId $_.ObjectId
}

# Get all application permissions for the service principal
$spApplicationPermissions = Get-EntraServicePrincipalAppRoleAssignment -ObjectId $sp.ObjectId -All | Where-Object { $_.PrincipalType -eq "ServicePrincipal" }

# Remove all application permissions
$spApplicationPermissions | ForEach-Object {
    Remove-EntraServicePrincipalAppRoleAssignment -ObjectId $_.PrincipalId -AppRoleAssignmentId $_.objectId
}
```

### Microsoft Entra PowerShell を使用してすべてのユーザーとグループの割り当てを削除する

次のスクリプトを使って、アプリケーションに対するユーザーまたはグループの appRoleAssignments を削除します。

```powershell
connect-entra -scopes "Application.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"
#Retrieve the service principal object ID.
$app_name = "<Your App's display name>"
$sp = Get-EntraServicePrincipal -Filter "displayName eq '$app_name'"
$sp.ObjectId

# Get Microsoft Entra App role assignments using objectId of the Service Principal
$assignments = Get-EntraServicePrincipalAppRoleAssignedTo -ObjectId $sp.ObjectId -All $true

# Remove all users and groups assigned to the application
$assignments | ForEach-Object {
    if ($_.PrincipalType -eq "User") {
        Remove-EntraUserAppRoleAssignment -ObjectId $_.PrincipalId -AppRoleAssignmentId $_.ObjectId
    } elseif ($_.PrincipalType -eq "Group") {
        Remove-EntraGroupAppRoleAssignment -ObjectId $_.PrincipalId -AppRoleAssignmentId $_.ObjectId
    }
}
```

::: zone-end

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用してアクセス許可を確認および取り消す

次の Microsoft Graph PowerShell スクリプトを使用すると、アプリケーションに付与されているすべてのアクセス許可が取り消されます。 少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。

```powershell
Connect-MgGraph -Scopes "Application.ReadWrite.All", "Directory.ReadWrite.All", "DelegatedPermissionGrant.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"

# Get Service Principal using objectId
$sp = Get-MgServicePrincipal -ServicePrincipalID "<ServicePrincipal objectID>"

Example: Get-MgServicePrincipal -ServicePrincipalId 'aaaaaaaa-bbbb-cccc-1111-222222222222'

# Get all delegated permissions for the service principal
$spOAuth2PermissionsGrants= Get-MgOauth2PermissionGrant -All| Where-Object { $_.clientId -eq $sp.Id }

# Remove all delegated permissions
$spOauth2PermissionsGrants |ForEach-Object {
  Remove-MgOauth2PermissionGrant -OAuth2PermissionGrantId $_.Id
}

# Get all application permissions for the service principal
$spApplicationPermissions = Get-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $Sp.Id -All | Where-Object { $_.PrincipalType -eq "ServicePrincipal" }

# Remove all application permissions
$spApplicationPermissions | ForEach-Object {
Remove-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $Sp.Id  -AppRoleAssignmentId $_.Id
}
```

### Microsoft Graph PowerShell を使用してすべてのユーザーとグループの割り当てを削除する

次のスクリプトを使って、アプリケーションに対するユーザーまたはグループの appRoleAssignments を削除します。

```powershell
Connect-MgGraph -Scopes "Application.ReadWrite.All", "Directory.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"

# Get Service Principal using objectId
$sp = Get-MgServicePrincipal -ServicePrincipalID "<ServicePrincipal objectID>"

Example: Get-MgServicePrincipal -ServicePrincipalId 'aaaaaaaa-bbbb-cccc-1111-222222222222'

# Get Microsoft Entra App role assignments using objectID of the Service Principal
$spApplicationPermissions = Get-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalID $sp.Id -All | Where-Object { $_.PrincipalType -eq "ServicePrincipal" }

# Revoke refresh token for all users assigned to the application
  $spApplicationPermissions | ForEach-Object {
  Remove-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $_.PrincipalId -AppRoleAssignmentId $_.Id
}
```

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph を使用してアクセス許可を確認および取り消す

アクセス許可を確認するには、少なくとも[クラウド アプリケーション管理者](https://developer.microsoft.com/graph/graph-explorer)として [Graph Explorer](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。

次のアクセス許可に同意する必要があります。

`Application.ReadWrite.All`、`Directory.ReadWrite.All`、`DelegatedPermissionGrant.ReadWrite.All`、`AppRoleAssignment.ReadWrite.All`。

#### デリゲートされたアクセス許可

次のクエリを実行し、アプリケーションに付与されたアクセス許可を確認します。

1. objectID を使ってサービス プリンシパルを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/{id}
    ```

    例:

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/00001111-aaaa-2222-bbbb-3333cccc4444
    ```
2. サービス プリンシパルのすべての委任されたアクセス許可を取得する

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/{id}/oauth2PermissionGrants
    ```
3. oAuth2PermissionGrants ID を使って、委任されたアクセス許可を削除します。

    ```http
    DELETE https://graph.microsoft.com/v1.0/oAuth2PermissionGrants/{id}
    ```

#### アプリケーションのアクセス許可

次のクエリを実行して、アプリケーションに付与されたアプリケーション アクセス許可を確認します。

1. サービス プリンシパルのすべてのアプリケーションのアクセス許可を取得する

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipal-id}/appRoleAssignments
    ```
2. appRoleAssignment ID を使ってアプリケーションのアクセス許可を削除する

    ```http
    DELETE https://graph.microsoft.com/v1.0/servicePrincipals/{resource-servicePrincipal-id}/appRoleAssignedTo/{appRoleAssignment-id}
    ```

### Microsoft Graph を使用してすべてのユーザーとグループの割り当てを削除する

次のクエリを実行して、アプリケーションに対するユーザーまたはグループの appRoleAssignments を削除します。

1. objectID を使ってサービス プリンシパルを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/{id}
    ```

    例:

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb
    ```
2. サービス プリンシパルの objectID を使って、Microsoft Entra アプリのロールの割り当てを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipal-id}/appRoleAssignedTo
    ```
3. appRoleAssignment ID を使って、アプリケーションに割り当てられているユーザーやグループの更新トークンを取り消します。

    ```http
    DELETE https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipal-id}/appRoleAssignedTo/{appRoleAssignment-id}
    ```

::: zone-end

注記

現在付与されているアクセス許可を取り消しても、ユーザーはアプリケーションの要求されたアクセス許可に再同意できなくなります。 [動的な同意によって、アプリケーションがアクセス許可を要求しないようにする必要があります](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-update-permissions)。 ユーザーの同意を完全にブロックする場合は、「ユーザーが [アプリケーションに同意する方法を構成する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)参照してください。

### 考慮すべきその他の認可

委任されたアクセス許可とアプリケーションのアクセス許可は、保護されたリソースへのアクセスをアプリケーションとユーザーに許可する唯一の方法ではありません。 管理者は、機密情報へのアクセスを許可している他の認可システムを把握しておく必要があります。 Microsoft のさまざまな承認システムの例としては、 [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)、 [Exchange RBAC](https://learn.microsoft.com/ja-jp/exchange/permissions-exo/application-rbac)、 [Teams リソース固有の同意](https://learn.microsoft.com/ja-jp/microsoftteams/platform/graph-api/rsc/resource-specific-consent)などがあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/manage-consent-requests"} -->
## アプリケーションの同意の管理と同意要求の評価 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-consent-requests
- Service: entra-id / enterprise-apps
- Article date: 2025-07-20
- Summary: Microsoft Entra ID での同意要求の評価とテナント全体の管理者の同意について説明します。 アプリケーションのアクセス許可とセキュリティを管理する管理者向けの重要なガイダンス。

Microsoft では、ユーザーが検証された発行元からのアプリと選択したアクセス許可に対してのみ同意できるように、[ユーザーの同意を制限する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)ことをお勧めします。 この基準を満たしていないアプリの場合、組織のセキュリティ チームと ID 管理者チームに意思決定プロセスが一元化されます。

ユーザーの同意を無効にしたり制限したりした後には、ビジネスクリティカルなアプリケーションを引き続き使用できるようにする上で、組織を安全に保つために実行するべきいくつかの重要な手順があります。 これらの手順は、組織のサポート チームと IT 管理者への影響を最小限に抑え、Microsoft 以外のアプリケーション内での管理されていないアカウントの使用を防止する上で不可欠です。

この記事では、検証済みの発行元へのユーザーの同意の制限や選択したアクセス許可など、アプリケーションへの同意の管理と Microsoft の推奨事項での同意要求の評価に関する主な概念について説明します。 プロセスの変更、管理者向けの教育、監査と監視、テナント全体の管理者の同意の管理などの概念について説明します。

### 変更と教育を処理する

- [管理者の同意ワークフロー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)を有効にして、ユーザーが同意画面から直接管理者の承認を要求できるようにすることを検討してください。
- すべての管理者が次のことを理解しているか確認します。

    - [アクセス許可と同意フレームワーク](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)
    - [同意エクスペリエンスとプロンプト](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)のしくみ。
    - テナント全体の管理者の同意要求を評価する方法。
- ユーザーがアプリケーションの管理者の承認を要求する方法について、組織の既存のプロセスを確認し、必要に応じて更新します。 プロセスが変更された場合:

    - 関連するドキュメント、監視、自動化などを更新します。
    - 影響を受けるすべてのユーザー、開発者、サポート チーム、および IT 管理者にプロセスの変更を伝達します。

### 監査と監視

- 組織内の[アプリと付与済みのアクセス許可を監査](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity#audit-apps-and-consented-permissions)し、不正または疑わしいアプリケーションにはデータへのアクセスが許可されていないことを確認します。
- OAuth 同意を要求する疑わしいアプリケーションに対する追加のベスト プラクティスと保護のために、[Office 365 での不正な同意付与の検出して修復する](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/detect-and-remediate-illicit-consent-grants)に関する記事を確認します。
- 組織が適切なライセンスを持っている場合:

    - [Microsoft Defender for Cloud Apps で他の OAuth アプリケーション監査機能](https://learn.microsoft.com/ja-jp/defender-cloud-apps/investigate-risky-oauth)を使用します。
    - [Azure Monitor Workbooks](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks) を使用して、アクセス許可および同意に関連するアクティビティを監視します。 *Consent Insights* ワークブックには、失敗した同意要求の数を示すアプリの一覧が表示されます。 この情報は、管理者の同意を許可するかどうかを管理者が確認および判断するため、アプリケーションの優先順位を決定するのに役立ちます。

#### 摩擦の軽減に関するその他の考慮事項

既に使用されている、信頼されたビジネス クリティカルなアプリケーションへの影響を最小限に抑えるには、許可するユーザーの同意が多いアプリケーションに管理者の同意を事前に許可することを検討してください。

- サインイン ログまたは同意許可アクティビティに基づいて、使用率が高い組織に既に追加されているアプリのインベントリを取得します。 PowerShell [スクリプト](https://gist.github.com/psignoret/41793f8c6211d2df5051d77ca3728c09)を使用すると、多数のユーザーの同意が許可されたアプリケーションをすばやく簡単に検出できます。
- 上位のアプリケーションを評価して、管理者の同意を付与します。

    重要

    組織内の多くのユーザーが既に自分で同意している場合でも、テナント全体の管理者の同意を付与する前に、アプリケーションを慎重に評価します。
- 承認されたアプリケーションごとに、テナント全体の管理者の同意を付与し、[ユーザー割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)を要求してユーザー アクセスを制限を検討します。

### テナント全体の管理者の同意要求を評価します。

テナント全体の管理者の同意を許可する際は、慎重に行う必要があります。 アクセス許可は、組織全体に代わって許可され、高度な権限を必要とする操作を試行するためのアクセス許可が含まれることがあります。 このような操作の例として、ロール管理、すべてのメールボックスまたはすべてのサイトへのフルアクセス、完全なユーザー偽装などがあります。

テナント全体の管理者の同意を許可する前に、許可するアクセス レベルに対して、アプリケーションとアプリケーションの発行元を信頼していることを確認することが重要です。 アプリケーションを制御しているユーザーと、アプリケーションがアクセス許可を要求している理由について、確信を持てない場合は、同意を許可しないでください。

管理者の同意を与える要求を評価する場合に考慮すべき推奨事項を次に示します。

- Microsoft ID プラットフォームでの[アクセス許可と同意フレームワーク](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)について理解する。
- [委任されたアクセス許可とアプリケーションのアクセス許可の違い](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#types-of-permissions)について理解する。

    アプリケーションのアクセス許可を許可すると、アプリケーションはユーザーの介入を必要とせずに組織全体のデータにアクセスできます。 委任されたアクセス許可を使用すると、ある時点でアプリケーションにサインインしたユーザーの代わりにアプリケーションを操作できます。
- 要求されているアクセス許可を理解します。

    アプリケーションによって要求されたアクセス許可は、[同意プロンプト](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)に一覧表示されます。 アクセス許可のタイトルを展開すると、アクセス許可の説明が表示されます。 アプリケーションのアクセス許可の説明には、通常、"サインインしているユーザーなしで" という語句が含まれます。委任されたアクセス許可の説明には、通常、"サインインしているユーザーの代わりに" という語句が含まれます。Microsoft Graph API のアクセス許可については、「[Microsoft Graph のアクセス許可のリファレンス](https://learn.microsoft.com/ja-jp/graph/permissions-reference)」を参照してください。 公開されるアクセス許可については、他の API のドキュメントを参照してください。

    要求されているアクセス許可を把握していない場合は、同意を許可しないでください。
- アクセス許可を要求しているアプリケーションとアプリケーションの発行元を把握します。

    他のアプリケーションに偽装している悪意のあるアプリケーションに注意してください。

    アプリケーションまたはその発行元の正当性が不明な場合は、同意を許可しないでください。 代わりに、確認を (たとえば、アプリケーションの発行元から直接) 取ります。
- 要求されたアクセス許可が、アプリケーションに期待される機能と一致していることを確認します。

    たとえば、SharePoint サイト管理を提供するアプリケーションでは、すべてのサイト コレクションの読み取りに委任アクセスを求める場合がありますが、すべてのメールボックスへのフルアクセス、またはディレクトリ内の完全な借用権限が必要ない場合もあります。

    アプリケーションが必要以上のアクセス許可を要求していると思われる場合は、同意を許可しないでください。 詳細については、アプリケーションの発行元にお問い合わせください。

### テナント全体の管理者の同意を許可する

テナント全体の管理者の同意を Microsoft Entra 管理センターから許可する手順については、「[アプリケーションに対してテナント全体の管理者の同意を付与する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent)」を参照してください。

### テナント全体の管理者の同意を取り消す

テナント全体の管理者の同意を取り消すには、以前にアプリケーションに付与されたアクセス許可を確認して、取り消すことができます。 詳細については、「[アプリケーションに付与されるアクセス許可の確認](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions)」参照してください。 また、[アプリケーションへのユーザーのサインインを無効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal)か、[アプリケーションを非表示にして](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/hide-application-from-user-portal)、マイ アプリ ポータルに表示されないようにすることで、アプリケーションへのユーザーのアクセスを削除することもできます。

#### 特定のユーザーに代わって同意を許可する

管理者は、組織全体に同意を許可するのではなく、[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/use-the-api) を使用して、1 人のユーザーに代わって委任されたアクセス許可に同意を許可することもできます。 Microsoft Graph PowerShell を使用した詳細な例については、「[PowerShell を使用して 1 人のユーザーに代わって同意を許可する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-consent-single-user)」を参照してください。

### アプリケーションへのユーザー アクセスを制限する

テナント全体の管理者の同意が付与されている場合でも、アプリケーションへのユーザー アクセスは制限することができます。 ユーザー アクセスを制限するには、アプリケーションへのユーザー割り当てを要求します。 詳細については、[ユーザーとグループの割り当て方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に関するページを参照してください。 管理者は、すべてのアプリケーションに対して、将来のすべてのユーザー同意操作を無効にすることで、アプリケーションへのユーザー アクセスを制限できます。

その他の複雑なシナリオの処理方法などについての詳細な概要については、[Microsoft Entra IDを使用したアプリケーション アクセス管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/manage-group-owner-consent-policies"} -->
## グループ所有者のアプリ同意ポリシーを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-group-owner-consent-policies
- Service: entra-id / enterprise-apps
- Article date: 2025-05-16
- Summary: グループ所有者が、組み込みおよびカスタムのアプリ同意ポリシーを管理して、どのような場合に同意を許可できるかを制御する方法について説明します。

アプリ同意ポリシーは、アプリが組織内のデータにアクセスするために必要なアクセス許可を管理する方法です。 ユーザーが同意できるアプリを制御し、ユーザーがデータにアクセスする前にアプリが特定の条件を満たしていることを確認するために使用されます。 これらのポリシーは、組織がデータの制御を維持しやすくすることにより、信頼できるアプリのみがアクセスできるようにします。

この記事では、組み込みおよびカスタムのアプリ同意ポリシーを管理して、どのような場合にグループ所有者の同意を許可できるかを制御する方法について説明します。

[Microsoft Graph](https://learn.microsoft.com/ja-jp/graph/overview) と [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started?view=graph-powershell-1.0&preserve-view=true) を使用すると、グループ所有者の同意ポリシーを表示および管理できます。

グループ所有者の同意ポリシーは、0 個以上の "包含" 条件セットと、0 個以上の "除外" 条件セットで構成されます。 イベントがグループ所有者の同意ポリシーにおいて考慮されるには、"包含" 条件セットが*いずかれの* "除外" 条件とも一致していない必要があります。

各条件セットは、いくつかの条件で構成されます。 イベントが条件セットに一致するためには、条件セット内の "*すべての*" 条件が満たされる必要があります。

ID が "microsoft-" で始まるグループ所有者の同意ポリシーは、組み込みのポリシーです。 たとえば、`microsoft-pre-approval-apps-for-group` グループ所有者の同意ポリシーでは、管理者が事前に承認したリストから申請されたアプリケーションが、グループ所有者が所有するデータにアクセスするための同意を付与する条件が定められています。 組み込みのポリシーは、カスタム ディレクトリ ロール内で、また、ユーザーの同意設定を構成するために使用できますが、編集または削除することはできません。

### 前提条件

- 次のいずれかのロールを持つユーザーまたはサービス:
    - [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)
    - [グループ所有者の同意ポリシーを管理するために必要なアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-consent-permissions#managing-app-consent-policies) を持つカスタム ディレクトリ ロール
    - Microsoft Graph アプリ ロール (アプリケーションのアクセス許可) Policy.ReadWrite.PermissionGrant (アプリまたはサービスとして接続する場合)

::: zone pivot="ms-powershell"

Microsoft Graph PowerShell を使用してアプリケーションのグループ所有者の同意ポリシーを管理するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started?view=graph-powershell-1.0&preserve-view=true) に接続し、「前提条件」セクションに記載されているロールのいずれかでサインインします。 また、`Policy.ReadWrite.PermissionGrant` アクセス許可に同意する必要があります。

```powershell
# change the profile to beta by using the `Select-MgProfile` command
Select-MgProfile -Name "beta"
```

```powershell
Connect-MgGraph -Scopes "Policy.ReadWrite.PermissionGrant"
```

### PowerShell を使用して、グループ所有者の同意ポリシーの現在の値を取得する

グループ所有者の同意設定が他の方法で承認されているかどうかを確認する方法について説明します。

1. グループ所有者の同意設定の現在の値を取得する

    ```powershell
      Get-MgPolicyAuthorizationPolicy | select -ExpandProperty DefaultUserRolePermissions | ft PermissionGrantPoliciesAssigned
    ```

    `ManagePermissionGrantPoliciesForOwnedResource` が `PermissionGrantPoliciesAssigned` で返された場合、グループ所有者の同意設定が他の方法で承認されている可能性があります。
2. ポリシーのスコープが `group` に設定されているかどうかを確認します。

    ```powershell
       Get-MgPolicyPermissionGrantPolicy -PermissionGrantPolicyId {"microsoft-all-application-permissions-for-group"} | Select -ExpandProperty AdditionalProperties
    ```

`ResourceScopeType` == `group` の場合、グループ所有者の同意設定は他の方法で承認されています。 さらに、グループのアプリ同意ポリシーに `microsoft-pre-approval-apps-for-group` が割り当てられている場合は、テナントに対して事前適用機能が有効化されています。

### PowerShell を使用して、既存のグループ所有者の同意ポリシーを一覧表示する

まず、組織内の既存のグループ所有者の同意ポリシーについて理解しておくことをお勧めします。

1. グループ所有者の同意ポリシーを一覧表示する方法:

    ```powershell
    Get-MgPolicyPermissionGrantPolicy | ft Id, DisplayName, Description
    ```
2. ポリシーの "包含" 条件セットを表示します。

    ```powershell
    Get-MgPolicyPermissionGrantPolicyInclude -PermissionGrantPolicyId {"microsoft-all-application-permissions-for-group"} | fl
    ```
3. "除外" 条件セットを表示します。

    ```powershell
    Get-MgPolicyPermissionGrantPolicyExclude -PermissionGrantPolicyId {"microsoft-all-application-permissions-for-group"} | fl
    ```

### PowerShell を使用して、カスタム グループ所有者の同意ポリシーを作成する

カスタムのグループ所有者の同意ポリシーを作成するには、次の手順に従います。

1. 新規の空のグループ所有者の同意ポリシーを作成します。

    ```powershell
    New-MgPolicyPermissionGrantPolicy `
        -Id "my-custom-app-consent-policy-for-group" `
        -DisplayName "My first custom app consent policy for group" `
        -Description "This is a sample custom app consent policy for group." `
        -AdditionalProperties @{includeAllPreApprovedApplications = $false; resourceScopeType = "group"}
    ```
2. "包含" 条件セットを追加します。

    ```powershell
    # Include delegated permissions classified "low", for apps from verified publishers
    New-MgPolicyPermissionGrantPolicyInclude `
        -PermissionGrantPolicyId "my-custom-app-consent-policy-for-group" `
        -PermissionType "delegated" `
        -PermissionClassification "low" `
        -ClientApplicationsFromVerifiedPublisherOnly
    ```

    この手順を繰り返して、さらに "包含" 条件セットを追加します。
3. 必要に応じて、"除外" 条件セットを追加します。

    ```powershell
    # Retrieve the service principal for the Azure Management API
    $azureApi = Get-MgServicePrincipal -Filter "servicePrincipalNames/any(n:n eq 'https://management.azure.com/')"
    
    # Exclude delegated permissions for the Azure Management API
    New-MgPolicyPermissionGrantPolicyExclude `
        -PermissionGrantPolicyId "my-custom-app-consent-policy-for-group" `
        -PermissionType "delegated" `
        -ResourceApplication $azureApi.AppId
    ```

    この手順を繰り返して、さらに "除外" 条件セットを追加します。

グループのアプリの同意ポリシーが作成されたら、 [グループ所有者にこのポリシーに従って同意を付与することを許可](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent-groups) できます。

### PowerShell を使用して、カスタム グループ所有者の同意ポリシーを削除する

1. カスタムのグループ所有者の同意ポリシーを削除する方法を次に示します。

    ```powershell
    Remove-MgPolicyPermissionGrantPolicy -PermissionGrantPolicyId "my-custom-app-consent-policy-for-group"
    ```

::: zone-end

::: zone pivot="ms-graph"

グループ補修者の同意ポリシーを管理するには、前提条件セクションに記載されているいずれかのロールで [Graph Explorer](https://developer.microsoft.com/graph/graph-explorer) にサインインします。 また、`Policy.ReadWrite.PermissionGrant` アクセス許可に同意する必要があります。

### Microsoft Graph を使用して、グループ所有者の同意ポリシーの現在の値を取得する

グループ所有者の同意設定が他の方法で承認されているかどうかを確認する方法について説明します。

1. 現在のポリシー値を取得する

    ```http
    GET /policies/authorizationPolicy
    ```

    `ManagePermissionGrantPoliciesForOwnedResource` が表示された場合、グループ所有者の同意設定が他の方法で承認されている可能性があります。
2. ポリシーのスコープが `group` に設定されているかどうかを確認します

    ```http
    GET /policies/permissionGrantPolicies/{ microsoft-all-application-permissions-for-group }
    ```

    `resourceScopeType` == `group` の場合、グループ所有者の同意設定は他の方法で承認されています。 さらに、グループのアプリ同意ポリシーに `microsoft-pre-approval-apps-for-group` が割り当てられている場合は、テナントに対して事前適用機能が有効化されています。

### Microsoft Graph を使用して、既存のグループ所有者の同意ポリシーを一覧表示する

まず、組織内の既存のグループ所有者の同意ポリシーについて理解しておくことをお勧めします。

1. すべてのアプリ同意ポリシーを一覧表示します。

    ```http
    GET /policies/permissionGrantPolicies
    ```
2. ポリシーの "包含" 条件セットを表示します。

    ```http
    GET /policies/permissionGrantPolicies/{ microsoft-all-application-permissions-for-group }/includes
    ```
3. "除外" 条件セットを表示します。

    ```http
    GET /policies/permissionGrantPolicies/{ microsoft-all-application-permissions-for-group }/excludes
    ```

### Microsoft Graph を使用して、カスタム グループ所有者の同意ポリシーを作成する

カスタムのグループ所有者の同意ポリシーを作成するには、次の手順に従います。

1. 新規の空のグループ所有者の同意ポリシーを作成します。

    ```http
    POST https://graph.microsoft.com/v1.0/policies/permissionGrantPolicies
    
    {
      "id": "my-custom-app-consent-policy-for-group",
      "displayName": "My first custom app consent policy for group",
      "description": "This is a sample custom app consent policy for group",
      "includeAllPreApprovedApplications": false,
      "resourceScopeType": "group"
    }
    ```
2. "包含" 条件セットを追加します。

    検証済みの発行元からのアプリに対して、"低" に分類されている委任されたアクセス許可を含める

    ```http
    POST https://graph.microsoft.com/v1.0/policies/permissionGrantPolicies/{ my-custom-app-consent-policy-for-group }/includes
    
    {
      "permissionType": "delegated",
      "permissionClassification": "low",
      "clientApplicationsFromVerifiedPublisherOnly": true
    }
    ```

    この手順を繰り返して、さらに "包含" 条件セットを追加します。
3. 必要に応じて、"除外" 条件セットを追加します。 Azure Management API (appId 00001111-aaaa-2222-bbbb-3333cccc4444) の委任されたアクセス許可を除外する

    ```http
    POST https://graph.microsoft.com/v1.0/policies/permissionGrantPolicies/{ my-custom-app-consent-policy-for-group }/excludes
    
    {
      "permissionType": "delegated",
      "resourceApplication": "00001111-aaaa-2222-bbbb-3333cccc4444 "
    }
    ```

    この手順を繰り返して、さらに "除外" 条件セットを追加します。

グループ所有者の同意ポリシーが作成されたら、このポリシーに従って、[グループ所有者の同意を許可する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent?tabs=azure-powershell#allow-user-consent-subject-to-an-app-consent-policy-using-powershell)ことができます。

### Microsoft Graph を使用して、カスタム グループ所有者の同意ポリシーを削除する

1. カスタムのグループ所有者の同意ポリシーを削除する方法を次に示します。

    ```http
    DELETE https://graph.microsoft.com/v1.0/policies/permissionGrantPolicies/ my-custom-policy
    ```

::: zone-end

警告

削除したグループ所有者の同意ポリシーは復元できません。 カスタムのグループ所有者の同意ポリシーを誤って削除した場合は、ポリシーを再作成する必要があります。

#### サポートされている条件

次の表に、グループ所有者の同意ポリシーでサポートされている条件の一覧を示します。

| 条件 | 説明 |
| --- | --- |
| 権限分類 | 付与されるアクセス許可を表す[アクセス許可の分類](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-permission-classifications)。または、任意のアクセス許可の分類 (分類されていないアクセス許可を含む) と一致する "all"。 既定値は "all" です。 |
| PermissionType | 付与されるアクセス許可を表すアクセス許可の種類。 アプリケーションのアクセス許可 (アプリ ロールなど) を表す "application"、または委任されたアクセス許可を表す "delegated" を使用します。 **注**: 値 "delegatedUserConsentable" は、API 発行元によって管理者の同意を必要とするように構成されていない委任されたアクセス許可を示しています。 この値は、組み込みのアクセス許可付与ポリシーで使用できますが、カスタムのアクセス許可付与ポリシーでは使用できません。 必須。 |
| ResourceApplication | アクセス許可が付与されるリソース アプリケーション (API など) の **AppId**。または、任意のリソース アプリケーションや API と一致する "any"。 既定値は "any" です。 |
| アクセス許可 | 一致する特定のアクセス許可のアクセス許可 ID の一覧。または、任意のアクセス許可と一致する "all" という単一の値。 既定値は単一の値 "all" です。  - 委任されたアクセス許可 ID は、API の ServicePrincipal オブジェクトの **OAuth2Permissions** プロパティで確認できます。 - アプリケーションのアクセス許可 ID は、API の ServicePrincipal オブジェクトの **AppRoles** プロパティで確認できます。 |
| クライアントアプリケーションID | 一致するクライアント アプリケーションの **AppId** 値の一覧。または、任意のクライアント アプリケーションと一致する単一の値 "all" を含む一覧。 既定値は単一の値 "all" です。 |
| ClientApplicationTenantIds | クライアント アプリケーションが登録されている Microsoft Entra テナント ID の一覧。または、任意のテナントに登録されているクライアント アプリと一致する単一の値 "all" を含む一覧。 既定値は単一の値 "all" です。 |
| クライアントアプリケーションパブリッシャーID | クライアント アプリケーションの[確認済みの発行元](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)を表す Microsoft Partner Network (MPN) ID の一覧。または、任意の発行元のクライアント アプリと一致する単一の値 "all" を含む一覧。 既定値は単一の値 "all" です。 |
| 検証済み発行元のみからのクライアントアプリケーション | このスイッチを設定すると、[確認済みの発行元](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)のクライアント アプリケーションでのみ一致します。 このスイッチ (`-ClientApplicationsFromVerifiedPublisherOnly:$false`) を無効にすると、確認済みの発行元がない場合でも、任意のクライアント アプリで一致します。 既定値は `$false` です。 |

警告

削除したグループ所有者の同意ポリシーは復元できません。 カスタムのグループ所有者の同意ポリシーを誤って削除した場合は、ポリシーを再作成する必要があります。

ヘルプを表示したり、質問に対する回答を検索したりするには、以下を参照してください。

- [Microsoft Q&A 上の Microsoft Entra ID](https://learn.microsoft.com/ja-jp/answers/products/)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/manage-self-service-access"} -->
## セルフサービス アプリケーションの割り当てを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-self-service-access
- Service: entra-id / enterprise-apps
- Article date: 2025-04-28
- Summary: セルフサービス アプリケーション アクセスを有効にして、ユーザーがマイ アプリ ポータルで自分のアプリケーションを見つけられるようにします

この記事では、Microsoft Entra 管理センターを使用して、セルフサービス アプリケーションのアクセスを有効にする方法を説明します。

ユーザーが [マイ アプリ ポータル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview)からアプリケーションを自己検出できるようにするには、アプリケーションの **セルフサービス アプリケーション アクセス** を有効にする必要があります。 この機能は、Microsoft Entra ギャラリーから追加されたアプリケーションで使用できます。 Microsoft [Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)、または [ユーザーまたは管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience)を使用して追加されたアプリケーションでも使用できます。

この機能を使用すると、以下のような操作ができます。

- IT グループに依頼せずに、ユーザーがマイ アプリ ポータルから自分でアプリケーションを見つけられるようにします。
- これらのユーザーを事前設定されたグループに追加することで、アクセス権を要求したユーザーの表示、アクセス権の削除、およびユーザーに割り当てたロールの管理を実行できます。
- 必要に応じて、ビジネス承認者にアプリケーションへのアクセス要求の承認を許可します。これにより、IT グループが承認する必要がなくなります。
- 必要に応じて、このアプリケーションへのアクセスを承認するユーザーを最大 10 人設定します。
- 必要に応じて、それらのユーザーがアプリケーションへのサインインに使用するパスワードを、ビジネス承認者が自分のマイ アプリ ポータルで設定できるようにします
- 必要に応じて、アプリケーション ロールへのセルフサービス割り当てユーザーの直接割り当てを自動化します。

### 前提条件

セルフサービス アプリケーション アクセスを有効にするには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成してください](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者。
- ユーザーがセルフサービス アプリへの参加を要求する場合、または所有者が要求を承認または拒否する場合は、Microsoft Entra ID P1 または P2 ライセンスが必要です。 Microsoft Entra ID P1 または P2 ライセンスがないと、ユーザーはセルフサービス アプリを追加できません。

### セルフ サービス アプリケーションへのアクセスを有効にすることでユーザーによる独自のアプリケーションの検索を許可します。

セルフサービス アプリケーション アクセスは、ユーザーがアプリケーションを自己検出できるようにし、必要に応じて、ビジネス グループがそれらのアプリケーションへのアクセス承認できるようにするための優れた方法です。 アプリケーションでのパスワードによるシングル サインオンについては、それらのユーザーに割り当てたサインイン情報を、ビジネス グループが自分のマイ アプリ ポータルで管理できるように設定することもできます。

セルフサービス アプリケーションからアプリケーションへのアクセスを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. 左側のナビゲーション メニューで、[ **セルフサービス**] を選択します。
5. このアプリケーションのセルフサービス アプリケーション アクセスを有効にするには、[ユーザーがこのアプリケーション**へのアクセスを要求できるようにする]** を **[はい**] に設定します。
6. **追加するユーザーを割り当てるグループ**の横にある**グループの選択**を選択します。 グループを選択して、**[選択]**を押します。 ユーザーの要求が承認されると、このグループに追加されます。 このグループのメンバーシップを表示しているときに、セルフサービス アクセスによってアプリケーションへのアクセスの許可を持つユーザーを確認できます。

    注意

    この設定では、オンプレミスから同期されたグループはサポートされません。
7. **随意：** ユーザーがアクセスを許可する前にビジネスの承認を要求するには、[ **このアプリケーションへのアクセスを許可する前に承認を要求する]** を **[はい**] に設定します。
8. **随意：** [ **このアプリケーションへのアクセスを承認できるユーザー**] の横にある [ **承認者の選択** ] を選択して、このアプリケーションへのアクセスを承認できるビジネス承認者を指定します。 個々のビジネス承認者を最大 10 人選択し、[選択] を **選択します**。

    注意

    グループはサポートされていません。 個々のビジネス承認者を最大 10 人まで選択できます。 複数の承認者を指定する場合、任意の 1 人の承認者がアクセス要求を承認できます。
9. **随意：** **[このアプリケーションでユーザーを割り当てる必要があるロール] の横にある** [**ロールの選択**] を選択して、セルフサービス承認ユーザーをロールに割り当てます。 これらのユーザーを割り当てる役割を選択し、[選択] をクリックします。 このオプションは、ロールを公開するアプリケーション用です。
10. ウィンドウの上部にある **[保存]** ボタンを選択して完了します。

セルフサービス アプリケーションの構成を完了すると、ユーザーは自分のマイ アプリ ポータルに移動し、[ **新しいアプリの要求** ] を選択して、セルフサービス アクセスが有効になっているアプリを見つけることができます。 ビジネス承認者のマイ アプリ ポータルにも通知が表示されます。 ユーザーが、承認が必要なアプリケーションへのアクセスを要求した場合、それを通知する電子メールを有効にできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/methods-for-removing-user-access"} -->
## Microsoft Entra ID でアプリケーションへのユーザーのアクセス権を削除する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/methods-for-removing-user-access
- Service: entra-id / enterprise-apps
- Article date: 2021-11-17
- Summary: Microsoft Entra ID でアプリケーションへのユーザーのアクセス権を削除する方法を理解する

この記事では、Microsoft Entra ID でアプリケーションへのユーザー アクセスを削除するためのいくつかのシナリオについて説明します。

### シナリオ

#### 特定のユーザーまたはグループのアプリケーションへの割り当てを削除する

アプリケーションへのユーザーまたはグループの割り当てを削除するには、「 [Microsoft Entra ID でエンタープライズ アプリからユーザーまたはグループの割り当てを削除する」の手順に](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)従います。

#### アプリケーションへのすべてのユーザー アクセスを無効にする

アプリケーションへのすべてのユーザー サインインを無効にするには、「 [Microsoft Entra ID でエンタープライズ アプリのユーザー サインインを無効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal)」の手順に従います。

#### アプリケーションを完全に削除する

Microsoft Entra テナントからアプリケーションを削除するには、 [Application Management のクイック スタート シリーズ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal)のガイダンスに従ってください。

#### 任意のアプリケーションで今後のすべてのユーザー同意操作を無効にする

アプリケーションで今後のすべてのユーザー同意操作を無効にするには、「エンド ユーザーがアプリケーションに [同意する方法を構成する」の手順に](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)従います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-ad-fs-application-howto"} -->
## AD FS アプリを Microsoft Entra ID に移動するための AD FS アプリケーションの移行 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-ad-fs-application-howto
- Service: entra-id / enterprise-apps
- Article date: 2025-06-20
- Summary: AD FS アプリケーション移行を使って、AD FS 証明書利用者アプリケーションを AD FS から Microsoft Entra ID に移行する方法について説明します。 このガイド付きエクスペリエンスでは、基本的な SAML URL、要求のマッピング、ユーザー割り当てをワンクリックで構成して、アプリケーションと Microsoft Entra ID を統合できます。

この記事では、AD FS アプリケーション移行を使って、Active Directory フェデレーション サービス (AD FS) アプリケーションを Microsoft Entra ID に移行する方法について説明します。

AD FS アプリケーション移行によって提供されるガイド付きエクスペリエンスを使うと、IT 管理者は、AD FS 証明書利用者アプリケーションを AD FS から Microsoft Entra ID に移行できます。 このウィザードは、新しい Microsoft Entra アプリケーションの検出、評価、構成を行うための統合エクスペリエンスを提供します。 基本的な SAML URL、要求のマッピング、ユーザー割り当てをワンクリックで構成して、アプリケーションと Microsoft Entra ID を統合できます。

AD FS アプリケーション移行ツールは、オンプレミスの AD FS から Microsoft Entra ID へのアプリケーションの移行をエンド ツー エンドでサポートするように設計されています。

AD FS アプリケーション移行を使うと、次のことができます。

- **AD FS 証明書利用者アプリケーションのサインイン アクティビティを評価**します。これは、特定のアプリケーションの使用状況と影響を特定するのに役立ちます。
- **AD FS から Microsoft Entra への移行の実現可能性を分析** します。これにより、アプリケーションを Microsoft Entra プラットフォームに移行するために必要な移行の阻害要因やアクションを特定できます。
- **ワンクリック アプリケーション移行プロセスを使用して新しい Microsoft Entra アプリケーションを**構成します。このプロセスでは、指定された AD FS アプリケーション用に新しい Microsoft Entra アプリケーションが自動的に構成されます。

### 前提条件

AD FS アプリケーション移行を使うには:

- アプリケーションにアクセスするには、自分の組織で現在 AD FS が使用されている必要があります。
- ユーザーが、Microsoft Entra ID P1 または P2 ライセンスを持っていること。
- ユーザーに、次のいずれかのロールが割り当てられている必要があります。
    - クラウド アプリケーション管理者
    - アプリケーション管理者
    - グローバル閲覧者 (読み取り専用アクセス)
    - レポート閲覧者 (読み取り専用アクセス)
- Microsoft Entra Connect が、Microsoft Entra Connect Health AD FS 正常性エージェントと共に、オンプレミス環境にインストールされている必要があります。
    - [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594)
    - [Microsoft Entra Connect Heath AD FS エージェント](https://go.microsoft.com/fwlink/?LinkID=518973)

AD FS 用の Microsoft Entra Connect Health エージェントをインストールした後に、予期するすべてのアプリケーションが表示されない理由がいくつかあります。

- AD FS アプリケーション移行ダッシュボードには、過去 30 日間にユーザーがログインした AD FS アプリケーションのみが表示されます。
- Microsoft 関連の AD FS 証明書利用者アプリケーションは、ダッシュボードでは使用できません。

### Microsoft Entra ID で AD FS アプリケーション移行ダッシュボードを表示する

AD FS アプリケーション移行ダッシュボードは、Microsoft Entra 管理センターの **[使用状況と分析情報** ] レポートで使用できます。 ウィザードは、次の 2 つの方法で開始できます。

**[エンタープライズ アプリケーション] セクションから**:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**を参照します。
3. [ **使用状況と分析情報]** で、 **AD FS アプリケーションの移行** を選択して、AD FS アプリケーションの移行ダッシュボードにアクセスします。

**「監視と正常性」セクションより:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**監視とヘルス**&gt;、**使用状況とインサイト** に移動します。
3. [ **管理**] で [ **使用状況] と [分析情報**] を選択し、 **AD FS アプリケーションの移行** を選択して AD FS アプリケーションの移行ダッシュボードにアクセスします。

AD FS アプリケーション移行ダッシュボードには、過去 30 日間にアクティブなサインイン トラフィックを持つすべての AD FS 証明書利用者アプリケーションの一覧が表示されます。

ダッシュボードには日付範囲フィルターがあります。 フィルターを使うと、選んだ時間の範囲に従って、すべてのアクティブな AD FS 証明書利用者アプリケーションを選択できます。 このフィルターでは、過去 1 日、7 日、30 日の期間がサポートされます。

すべてのアプリケーションの一覧、構成可能なアプリケーション、以前に構成されたアプリケーションが表示される 3 つのタブがあります。 このダッシュボードから、移行作業の全体的な進行状況の概要がわかります。

ダッシュボードの 3 つのタブは次のとおりです。

- **すべてのアプリ** - オンプレミス環境から検出されたすべてのアプリケーションの一覧が表示されます。
- **[移行の準備完了** ] - **[準備完了** ] または [移行の確認 **が必要** ] になっているすべてのアプリケーションの一覧が表示されます。
- **構成の準備完了** - AD FS アプリケーション移行ウィザードを使用して以前に移行されたすべての Microsoft Entra アプリケーションの一覧が表示されます。

#### アプリケーションの移行の状態

Microsoft Entra Connect および AD FS 用の Microsoft Entra Connect Health エージェントは、AD FS 証明書利用者アプリケーションの構成とサインイン監査ログを読み取ります。 各 AD FS アプリケーションに関するこのデータは、アプリケーションを as-isに移行できるかどうか、またはさらにレビューが必要かどうかを判断するために分析されます。 この分析の結果に基づき、特定のアプリケーションの移行状態として次のいずれかの状態が示されます。

- **移行準備完了** とは、AD FS アプリケーション構成が Microsoft Entra ID で完全にサポートされ、as-is移行できることを意味します。
- **要確認** とは、アプリケーションの設定の一部を Microsoft Entra ID に移行できることを意味しますが、as-isに移行できない設定を確認する必要があります。 ただし、設定は移行の阻害要因ではありません。
- **追加の手順は、** Microsoft Entra ID がアプリケーションの設定の一部をサポートしていないため、アプリケーションを現在の状態で移行できないことを意味します。

AD FS アプリケーション移行ダッシュボードの各タブについて詳しく説明します。

#### [すべてのアプリ] タブ

[ **すべてのアプリ** ] タブには、選択した日付範囲のすべてのアクティブな AD FS 証明書利用者アプリケーションが表示されます。 ユーザーは、集計されたサインイン データを使って、各アプリケーションの影響を分析できます。 [ **移行の状態** ] リンクを使用して、詳細ウィンドウに移動することもできます。

各検証規則の詳細については、 [AD FS アプリケーション移行の検証規則](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-ad-fs-application-overview#ad-fs-application-migration-validation-tests)に関するページを参照してください。

[Image: AD FS アプリケーション移行の詳細ウィンドウのスクリーンショット。]

メッセージを選択して、他の移行規則の詳細を開きます。 テストされるすべてのプロパティの一覧については、後の構成テストの表を参照してください。

##### 要求規則テストの結果を確認する

AD FS でアプリケーションの要求規則を構成した場合、エクスペリエンスでは、すべての要求規則の詳細な分析が提供されます。 Microsoft Entra ID に移動できる要求規則と、さらに確認が必要なものが表示されます。

1. [ **すべての** アプリ] タブのアプリの一覧からアプリを選択し、[ **移行の状態** ] 列で状態を選択して移行の詳細を表示します。 合格した構成テストの概要と、移行の潜在的な問題が表示されます。
2. [ **移行ルールの詳細** ] ページで、結果を展開して、潜在的な移行の問題に関する詳細を表示し、さらにガイダンスを取得します。 テストされたすべての要求規則の詳細な一覧については、この記事の 「要求規則のテスト 」セクションを参照してください。

次に示すのは、IssuanceTransform 規則に関する移行規則の詳細の例です。 ここには、アプリケーションを Microsoft Entra ID に移行する前に確認して対処する必要がある、要求の特定の部分が一覧表示されています。

[Image: AD FS アプリケーション移行規則の詳細ウィンドウのスクリーンショット。]

##### 要求規則テスト

次の表に、AD FS アプリケーションに対して実行されるすべての要求規則テストの一覧を示します。

| プロパティ | 説明 |
| --- | --- |
| サポートされていない条件/パラメータ | 条件ステートメントで、要求が特定のパターンに一致するかどうかを評価するために正規表現が使用されています。 Microsoft Entra ID で同様の機能を実現するには、IfEmpty()、StartWith()、Contains() など、事前に定義された変換を使用できます。 詳細については、「 [エンタープライズ アプリケーションの SAML トークンで発行された要求をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。 |
| サポートされていない条件クラス | 条件ステートメントで、発行ステートメントを実行する前に評価する必要がある複数の条件が使用されています。 Microsoft Entra ID では、複数の要求の値を評価できる要求の変換関数で、この機能をサポートできます。 詳細については、「 [エンタープライズ アプリケーションの SAML トークンで発行された要求をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。 |
| UNSUPPORTED\_RULE\_TYPE (サポートされていないルールタイプ) | 要求規則を認識できませんでした。 Microsoft Entra ID で要求を構成する方法の詳細については、「 [エンタープライズ アプリケーションの SAML トークンで発行された要求をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。 |
| 条件がサポートされていない発行者に一致しました | Microsoft Entra ID ではサポートされていない発行者が、条件ステートメントで使われています。 現在、Microsoft Entra では、Active Directory または Microsoft Entra ID と異なるストアからの要求は取得されません。 この条件によってアプリケーションが Microsoft Entra ID に移行されない場合は、 [お知らせください](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)。 |
| サポートされていない条件関数 | 条件ステートメントで、一致の数に関係なく単一の要求を発行または追加するための集計関数が使用されています。 Microsoft Entra ID では、IfEmpty()、StartWith()、Contains() などの関数を使用して、ユーザーの属性を評価し、要求に使用する値を決定できます。 詳細については、「 [エンタープライズ アプリケーションの SAML トークンで発行された要求をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。 |
| 制限されたクレームが発行されました | 条件ステートメントで、Microsoft Entra ID で制限されている要求が使用されています。 制限された要求を発行できる可能性はありますが、そのソースを変更したり、変換を適用したりすることはできません。 詳細については、「 [Microsoft Entra ID の特定のアプリのトークンで出力される要求をカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)する」を参照してください。 |
| 外部属性ストア | 発行ステートメントで、Active Directory と異なる属性ストアが使用されています。 現在、Microsoft Entra では、Active Directory または Microsoft Entra ID と異なるストアからの要求は取得されません。 この結果、アプリケーションを Microsoft Entra ID に移行できなくなる場合は、 [お知らせください](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)。 |
| サポートされていない発行クラス | 発行ステートメントで、受信した要求セットに要求を追加する ADD が使用されています。 Microsoft Entra ID では、この設定を複数の要求変換として構成できます。 詳細については、「 [エンタープライズ アプリケーションの SAML トークンで発行された要求をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。 |
| 未対応の発行変換 | 発行ステートメントで、出力される要求の値を変換するために正規表現が使用されています。 Microsoft Entra ID で同様の機能を実現するには、`Extract()`、`Trim()`、`ToLower()` など、事前に定義された変換を使用できます。 詳細については、「 [エンタープライズ アプリケーションの SAML トークンで発行された要求をカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。 |

#### [移行準備完了] タブ

[ **移行の準備完了** ] タブには、移行状態が [ **準備完了** ] または [ **確認が必要**] になっているすべてのアプリケーションが表示されます。

サインイン データを使って、各アプリケーションの影響を特定し、移行に適したアプリケーションを選択できます。 [ **移行の開始** ] リンクを選択して、支援されたワンクリック のアプリケーション移行プロセスを開始します。

#### [構成の準備完了] タブ

このタブには、AD FS アプリケーション移行ウィザードを使って以前に移行されたすべての Microsoft Entra アプリケーションの一覧が表示されます。

**アプリケーション名**は、新しい Microsoft Entra アプリケーションの名前です。 **アプリケーション識別子** は、アプリケーションを AD FS 環境と関連付けるために使用できる AD FS 証明書利用者アプリケーション識別子と同じです。 [ **Microsoft Entra でアプリケーションを構成する]** リンクを使用すると、[ **エンタープライズ アプリケーション** ] セクション内で新しく構成された Microsoft Entra アプリケーションに移動できます。

### AD FS アプリケーション移行ウィザードを使用して AD FS から Microsoft Entra ID にアプリを移行する

1. アプリケーションの移行を開始するには、[移行の準備完了] タブから、移行するアプリケーションの **[移行の** **開始**] リンクを選択します。
2. このリンクにより、ユーザーは AD FS アプリケーション移行ウィザードの支援付きワンクリック アプリケーション移行セクションにリダイレクトされます。 ウィザードのすべての構成は、オンプレミスの AD FS 環境からインポートされます。

ウィザードのさまざまなタブの詳細を確認する前に、サポートされている構成とサポートされていない構成を理解しておくことが重要です。

#### サポートされている構成

支援付き AD FS アプリケーション移行では、次の構成がサポートされます。

- SAML アプリケーションの構成のみをサポートします。
- 新しい Microsoft Entra アプリケーション名をカスタマイズするオプション。
- ユーザーは、アプリケーション テンプレート ギャラリーから任意のアプリケーション テンプレートを選択できます。
- SAML アプリケーションの基本的な構成 (識別子と応答 URL) の構成。
- テナントのすべてのユーザーを許可する Microsoft Entra アプリケーションの構成。
- Microsoft Entra アプリケーションへのグループの自動割り当て。
- AD FS 証明書利用者の要求構成から抽出された Microsoft Entra と互換性のある要求の構成。

#### サポートされない構成:

AD FS アプリケーション移行では、次の構成はサポートされません。

- OIDC (OpenID Connect)、OAuth、WS-Fed の構成はサポートされていません。
- 条件付きアクセス ポリシーの自動構成はサポートされていません。ただし、ユーザーはテナントで新しいアプリケーションを構成した後で同じように構成できます。
- 署名証明書は、AD FS 証明書利用者アプリケーションから移行されません。 AD FS アプリケーション移行ウィザードには、次のタブがあります。

ウィザードの支援されたワンクリック アプリケーション移行セクションの各タブの詳細を見てみましょう。

#### [基本] タブ

- AD FS の信頼対象アプリケーション名で既に事前入力されている**アプリケーション名**。 新しい Microsoft Entra アプリケーションの名前として使用できます。 名前を他の任意の値に変更することもできます。
- **アプリケーション テンプレート**。 アプリケーションに最適な任意のアプリケーション テンプレートを選びます。 テンプレートを使わない場合は、このオプションをスキップできます。

#### [ユーザーとグループ] タブ

オンクリック構成では、オンプレミスの構成と同じようにユーザーとグループが Microsoft Entra アプリケーションに自動的に割り当てられます。

すべてのグループは、AD FS 証明書利用者アプリケーションのアクセス制御ポリシーから抽出されます。 グループは、Microsoft Entra Connect エージェントを使って Microsoft Entra テナントに同期する必要があります。 グループが AD FS 証明書利用者アプリケーションにマップされているが、Microsoft Entra テナントと同期されていない場合。 これらのグループは構成からスキップされます。

支援されたユーザーとグループの構成では、オンプレミスの AD FS 環境からの次の構成がサポートされます。

- テナントのすべてのユーザーを許可します。
- 特定のグループを許可します。

[Image: [AD FS のユーザーとグループの設定] ウィンドウのスクリーンショット。]

構成ウィザードでユーザーとグループを表示できます。 このセクションは読み取り専用ビューであり、このセクションに変更を加えることはできません。

#### [SAML 構成] タブ

このタブには、Microsoft Entra アプリケーションのシングル サインオン設定に使われる基本的な SAML プロパティが表示されます。 現時点では、必須のプロパティのみがマップされており、それは識別子と応答 URL です。

これらの設定は、AD FS 証明書利用者アプリケーションから直接実装され、このタブから変更することはできません。ただし、アプリケーションを構成した後は、エンタープライズ アプリケーションの Microsoft Entra 管理センターの [シングル サインオン] ウィンドウからこれらの設定を変更できます。

[Image: AD FS SAML 構成ペインのスクリーンショット。]

[Image: AD FS アプリケーション移行の [SAML 構成] タブのスクリーンショット。]

#### [要求] タブ

すべての AD FS 要求が Microsoft Entra 要求にそのまま変換されることはありません。 移行ウィザードでは、特定の要求のみがサポートされます。 足りない要求がある場合は、Microsoft Entra 管理センターの移行されたエンタープライズ アプリケーションで構成できます。

AD FS 証明書利用者アプリケーションが、Microsoft Entra ID でサポートされている構成 `nameidentifier` 、 `nameidentifier`として構成されている場合。 それ以外の場合は、`user.userprincipalname` が既定の nameidentifier 要求として使われます。

[Image: AD FS 要求構成ペインのスクリーンショット。]

このセクションは読み取り専用ビューです。ここでは変更を加えることはできません。

[Image: AD FS アプリケーション移行要求の構成タブのスクリーンショット。]

#### [次のステップ] タブ

このタブでは、ユーザーが期待する次の手順プまたはレビューに関する情報が表示されます。 次に示すのは、Microsoft Entra ID ではサポートされていない、この AD FS 証明書利用者アプリケーションの構成の一覧の例です。

このタブから関連するドキュメントにアクセスし、問題を調べて理解できます。

[Image: AD FS アプリケーションの移行の次の手順タブのスクリーンショット。]

#### [確認と作成] タブ

このタブには、前のタブで確認したすべての構成の概要が表示されます。 それをもう一度確認できます。 すべての構成に満足していて、アプリケーションの移行に進む場合は、[ **作成** ] ボタンを選択して移行プロセスを開始します。 新しいアプリケーションが Microsoft Entra テナントに移行されます。

現在、アプリケーションの移行は 9 つのステップで行われ、通知を使って監視できます。 ワークフローでは、次のアクションが行われます。

- アプリケーションの登録を作成する
- サービス プリンシパルを作成する
- SAML の設定を構成する
- ユーザーとグループをアプリケーションに割り当てる
- 要求を構成する

移行プロセスが完了すると、 **アプリケーションの移行が成功**したことを示す通知メッセージが表示されます。

[Image: アプリケーションの移行に成功したメッセージのスクリーンショット。]

アプリケーションの移行が完了すると、[ **構成の準備完了** ] タブにリダイレクトされ、以前に移行したすべてのアプリケーション (構成した最新のアプリケーションを含む) が表示されます。

### エンタープライズ アプリケーションを確認して構成する

1. [ **構成の準備完了** ] タブから、[ **Microsoft Entra でアプリケーションを構成** する] リンクを使用して、[**エンタープライズ アプリケーション**] セクションで新しく構成されたアプリケーションに移動できます。 既定では、アプリケーションの **SAML ベースのサインオン ページに** 移動します。

    [Image: SAML ベースのサインオン ウィンドウのスクリーンショット。]
2. **SAML ベースのサインオン** ウィンドウでは、すべての AD FS 証明書利用者アプリケーション設定が、新しく移行された Microsoft Entra アプリケーションに既に適用されています。 基本的**な SAML 構成**の**識別子**と**応答 URL** のプロパティと、AD FS アプリケーション移行ウィザードの **[属性] タブの [要求**] タブの要求の一覧は、エンタープライズ アプリケーションの要求と同じです。
3. アプリケーションの **[プロパティ** ] ウィンドウから、アプリケーション テンプレートのロゴは、アプリケーションが選択したアプリケーション テンプレートにリンクされていることを意味します。 [ **所有者** ] ページで、現在の管理者ユーザーがアプリケーションの所有者の 1 人として追加されます。
4. [ **ユーザーとグループ** ] ウィンドウでは、必要なすべてのグループが既にアプリケーションに割り当てられます。

移行されたエンタープライズ アプリケーションを確認した後は、ビジネス ニーズに応じてアプリケーションを更新できます。 要求を追加または更新したり、さらにユーザーとグループを割り当てたり、条件付きアクセス ポリシーを構成して多要素認証や他の条件付き認可機能のサポートを有効にしたりできます。

### ロールバック

AD FS アプリケーション移行ウィザードのワンクリック構成により、新しいアプリケーションが Microsoft Entra テナントに移行されます。 ただし、移行されたアプリケーションは、サインイン トラフィックをそれにリダイレクトするまでは非アクティブなままです。 それまでは、ロールバックしたければ、新しく移行した Microsoft Entra アプリケーションをテナントから削除できます。

ウィザードには自動クリーンアップ機能はありません。 移行されたアプリケーションの設定を続けたくない場合は、テナントからアプリケーションを手動で削除する必要があります。 アプリケーションの登録とそれに対応するエンタープライズ アプリケーションを削除する方法については、次の URL を参照してください。

- [アプリケーションの登録を削除する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-remove-app)
- [エンタープライズ アプリケーションを削除する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal)

### トラブルシューティングのヒント

#### 一部の AD FS アプリケーションがレポートに表示されません

AD FS 用の Microsoft Entra Connect Health エージェントをインストールしても、インストールのプロンプトが表示される場合、またはレポートにすべての AD FS アプリケーションが表示されない場合は、次の 2 つの理由が考えられます。

- アクティブな AD FS アプリケーションがありません。
- 不足している AD FS アプリケーションは Microsoft アプリケーションです。

注

AD FS アプリケーション移行の一覧に表示されるのは、組織内のすべての AD FS アプリケーションのうち、過去 30 日間にアクティブなユーザー サインインがあったものだけです。 レポートには、Office 365 など、AD FS 内の Microsoft 関連の証明書利用者は表示されません。 たとえば、`urn:federation:MicrosoftOnline`、`microsoftonline`、`microsoft:winhello:cert:prov:server` といった名前の証明書利用者は、一覧に表示されません。

#### "同じ識別子を持つアプリケーションが既に存在する" という検証エラーが表示されるのはなぜですか?

テナント内の各アプリケーションは、一意のアプリケーション識別子を持っている必要があります。 このエラー メッセージが表示される場合は、同じ識別子を持つ別のアプリケーションが Microsoft Entra テナントに既に存在することを意味します。 この場合は、既存のアプリケーションの識別子を更新するか、AD FS 証明書利用者アプリケーションの識別子を更新し、更新が反映されるまで 24 時間待つ必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-ad-fs-application-overview"} -->
## AD FS アプリケーション移行の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-ad-fs-application-overview
- Service: entra-id / enterprise-apps
- Article date: 2024-06-10
- Summary: AD FS アプリケーション移行ウィザードの機能と、そのダッシュボードに表示される移行の状態について説明します。 アプリケーションの移行で生じるさまざまな検証テストと、検証の問題を解決する方法について説明します。

この記事では、AD FS アプリケーション移行ウィザードの機能と、そのダッシュボードに表示される移行の状態について説明します。 また、AD FS から Microsoft Entra ID に移行する各アプリケーションにおいて、アプリケーションの移行で生じるさまざまな検証テストについても説明します。

AD FS アプリケーション移行ウィザードでは、どのアプリケーションを Microsoft Entra ID に移行できるのかをすばやく特定できます。 あらゆる AD FS アプリケーションで Microsoft Entra ID との互換性を評価します。 また、問題がないか確認し、個々のアプリケーションでの移行に向けた準備と、ワンクリック エクスペリエンスを使用した新しい Microsoft Entra アプリケーションの構成についてのガイダンスも提供します。

AD FS アプリケーションの移行では、次のことができます。

- **AD FS アプリケーションを検出して、移行のスコープを設定する** - AD FS アプリケーション移行ウィザードには、過去 30 日間にアクティブなユーザー サインインが行われた組織内のすべての AD FS アプリケーションが一覧表示されます。 レポートには、アプリケーションの Microsoft Entra ID への移行の準備状況が示されます。 レポートには、Office 365 など、AD FS 内の Microsoft 関連の証明書利用者は表示されません。 たとえば、`urn:federation:MicrosoftOnline` という名前を持つ証明書利用者です。
- **移行するアプリケーションに優先順位を付ける** - 過去 1、7、または 30 日間にそのアプリケーションにサインインした一意のユーザー数を取得して、そのアプリケーション移行の重要度またはリスクを判断するために役立てます。
- **移行テストを実行して問題を修正する** - アプリケーションを移行する準備ができているかどうかを判断するために、レポート サービスで自動的にテストを実行します。 結果は、AD FS アプリケーションの移行ダッシュボードに移行の状態として表示されます。 AD FS 構成に Microsoft Entra 構成との互換性がない場合は、構成に対処する方法についての具体的なガイダンスが Microsoft Entra ID に示されます。
- **ワンクリックのアプリケーション構成エクスペリエンスを使用して、新しい Microsoft Entra アプリケーションを構成する** - これにより、オンプレミスの証明書利用者アプリケーションをクラウドに移行するためのガイド付きエクスペリエンスが提供されます。 移行エクスペリエンスでは、オンプレミス環境から直接インポートされる証明書利用者アプリケーションのメタデータを使用します。 また、このエクスペリエンスでは、いくつかの基本的な SAML 設定、クレーム構成、グループ割り当てを使用して、Microsoft Entra プラットフォーム上の SAML アプリケーションをワンクリックで構成できます。

Note

AD FS アプリケーションの移行では、SAML ベースのアプリケーションのみがサポートされます。 OpenID Connect、WS-Fed、OAuth 2.0 などのプロトコルを使用するアプリケーションはサポートされていません。 これらのプロトコルを使用するアプリケーションを移行する場合は、「[AD FS アプリケーション アクティビティ レポートの使用](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-application-activity)」を参照して、移行するアプリケーションを特定します。 移行するアプリケーションを特定したら、Microsoft Entra ID で手動で構成できます。 手動での移行を開始する方法の詳細については、「[アプリケーションの移行とテスト](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-plan-migration-test)」を参照してください。

### AD FS アプリケーションの移行の状態

Microsoft Entra Connect および AD FS 用の Microsoft Entra Connect Health エージェントでは、オンプレミスの証明書利用者アプリケーションの構成とサインイン監査ログを読み取ります。 各 AD FS アプリケーションに関するこうしたデータが分析されて、そのまま移行できるかどうか、または追加の確認が必要かどうかが判断されます。 この分析の結果に基づいて、特定のアプリケーションにおける移行の状態が判断されます。

アプリケーションは、次の移行の状態に分類されます。

- **[移行準備完了]** は、AD FS アプリケーション構成が Microsoft Entra ID で完全にサポートされており、そのままの状態で移行できることを意味します。
- **[確認が必要です]** は、アプリケーションの設定の一部は Microsoft Entra ID に移行できますが、そのままでは移行できない設定を確認する必要があることを意味します。
- **[追加の手順が必要です]** は、アプリケーションの設定の一部が Microsoft Entra ID でサポートされていないため、現在の状態ではアプリケーションを移行できないことを意味します。

### AD FS アプリケーションの移行の検証テスト

アプリケーションの準備状況は、次の定義済みの AD FS アプリケーション構成テストに基づいて評価されます。 テストは自動的に実行され、結果は AD FS アプリケーション移行ダッシュボードに**移行の状態**として表示されます。 AD FS 構成に Microsoft Entra 構成との互換性がない場合は、構成に対処する方法についての具体的なガイダンスが Microsoft Entra ID に示されます。

### AD FS アプリケーション移行の分析情報の状態の更新

アプリケーションが更新されると、内部エージェントは数分以内に更新プログラムを同期します。 ただし、AD FS 移行分析情報ジョブは、更新プログラムを評価し、新しい移行状態を計算する役割を担います。 これらのジョブは 24 時間ごとに実行されるようにスケジュールされています。つまり、データは 1 日に 1 回だけ、協定世界時 (UTC) の 00:00 頃に計算されます。

| 結果 | 合格/警告/不合格 | 説明 |
| --- | --- | --- |
| Test-ADFSRPAdditionalAuthenticationRules  AdditionalAuthentication について、移行できない規則が 1 つ以上検出されました。 | 合格/警告 | 証明書利用者には、多要素認証を要求するための規則があります。 Microsoft Entra ID に移行するには、これらの規則を条件付きアクセス ポリシーに変換します。 オンプレミス MFA を使用している場合は、Microsoft Entra 多要素認証に移行することをお勧めします。 [条件付きアクセスの詳細を確認してください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)。 |
| Test-ADFSRPAdditionalWSFedEndpoint  Relying party has AdditionalWSFedEndpoint set to true. (証明書利用者の AdditionalWSFedEndpoint が true に設定されている)。 | 合格/不合格 | AD FS の証明書利用者で、複数の WS-Fed アサーション エンドポイントが許可されています。 現在、Microsoft Entra では 1 つのみがサポートされています。 この結果によって移行が妨げられている場合は、[ご連絡ください](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)。 |
| Test-ADFSRPAllowedAuthenticationClassReferences  Relying Party has set AllowedAuthenticationClassReferences. (証明書利用者で AllowedAuthenticationClassReferences が設定されている)。 | 合格/不合格 | AD FS のこの設定では、特定の認証の種類のみを許可するようアプリケーションを構成するかどうかを指定します。 この機能を実現するには、条件付きアクセスを使用することをお勧めします。 この結果によって移行が妨げられている場合は、[ご連絡ください](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)。 [条件付きアクセスの詳細についてご確認ください](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)。 |
| Test-ADFSRPAlwaysRequireAuthentication  AlwaysRequireAuthenticationCheckResult | 合格/不合格 | AD FS のこの設定では、SSO Cookie を無視して "**認証のためのプロンプトを毎回表示する**" ようアプリケーションが構成されているかどうかを指定します。 Microsoft Entra ID では、条件付きアクセス ポリシーを使用して認証セッションを管理し、同様の動作を実現することができます。 [条件付きアクセスを使用して認証セッション管理を構成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)方法を確認してください。 |
| Test-ADFSRPAutoUpdateEnabled  Relying Party has AutoUpdateEnabled set to true (証明書利用者で AutoUpdateEnabled が true に設定されている) | 合格/警告 | AD FS のこの設定では、フェデレーション メタデータ内の変更に基づいてアプリケーションを自動的に更新するよう AD FS を構成するかどうかを指定します。 これは現在 Microsoft Entra ID でサポートされていませんが、Microsoft Entra ID へのアプリケーションの移行を妨げることはありません。 |
| Test-ADFSRPClaimsProviderName  Relying Party has multiple ClaimsProviders enabled (証明書利用者で複数の ClaimsProviders が有効にされている) | 合格/不合格 | AD FS のこの設定は、どの ID プロバイダーからの要求を証明書利用者が受け入れているかを示します。 Microsoft Entra ID では、Microsoft Entra B2B を使用して外部コラボレーションを有効にすることができます。 [Microsoft Entra B2B の詳細情報](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)。 |
| Test-ADFSRPDelegationAuthorizationRules | 合格/不合格 | アプリケーションで、カスタム委任承認規則が定義されています。 これは、OpenID Connect や OAuth 2.0 などの最新の認証プロトコルを使用して Microsoft Entra ID でサポートされている WS-Trust の概念です。 [Microsoft ID プラットフォームの詳細をご確認ください](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)。 |
| Test-ADFSRPImpersonationAuthorizationRules | 合格/警告 | アプリケーションで、カスタム偽装承認規則が定義されています。 これは、OpenID Connect や OAuth 2.0 などの最新の認証プロトコルを使用して Microsoft Entra ID でサポートされている WS-Trust の概念です。 [Microsoft ID プラットフォームの詳細をご確認ください](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)。 |
| Test-ADFSRPIssuanceAuthorizationRules  IssuanceAuthorization について、移行できない規則が 1 つ以上検出されました。 | 合格/警告 | アプリケーションで、AD FS にカスタム発行承認規則が定義されています。 Microsoft Entra ID では、Microsoft Entra 条件付きアクセスによりこの機能がサポートされています。 [条件付きアクセスの詳細を確認してください](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)。  アプリケーションに割り当てられたユーザーまたはグループによってアプリケーションへのアクセスを制限することもできます。 [アプリケーションにアクセスするユーザーとグループの割り当ての詳細についてご確認ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 |
| Test-ADFSRPIssuanceTransformRules  IssuanceTransform について、移行できない規則が 1 つ以上検出されました。 | 合格/警告 | アプリケーションで、AD FS にカスタム発行変換規則が定義されています。 Microsoft Entra ID では、トークンで発行された要求のカスタマイズがサポートされています。 詳細については、[エンタープライズ アプリケーションの SAML トークンで発行された要求のカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)に関するページを参照してください。 |
| Test-ADFSRPMonitoringEnabled  Relying Party has MonitoringEnabled set to true. (証明書利用者で MonitoringEnabled が true に設定されている)。 | 合格/警告 | AD FS のこの設定では、フェデレーション メタデータ内の変更に基づいてアプリケーションを自動的に更新するよう AD FS を構成するかどうかを指定します。 これは現在 Microsoft Entra でサポートされていませんが、Microsoft Entra ID へのアプリケーションの移行を妨げることはありません。 |
| Test-ADFSRPNotBeforeSkew  NotBeforeSkewCheckResult | 合格/警告 | AD FS では、SAML トークン内の NotBefore および NotOnOrAfter の時間に基づいて時間のずれが許可されます。 Microsoft Entra ID では、これは既定で自動的に処理されます。 |
| Test-ADFSRPRequestMFAFromClaimsProviders  Relying Party has RequestMFAFromClaimsProviders set to true. (証明書利用者で RequestMFAFromClaimsProviders が true に設定されている)。 | 合格/警告 | AD FS のこの設定は、別の要求プロバイダーからのユーザーであるときの MFA の動作を決定します。 Microsoft Entra ID では、Microsoft Entra B2B を使用して外部コラボレーションを有効にすることができます。 次に、条件付きアクセス ポリシーを適用して、ゲスト アクセスを保護することができます。 [Microsoft Entra B2B](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) と[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)の詳細を確認してください。 |
| Test-ADFSRPSignedSamlRequestsRequired  Relying Party has SignedSamlRequestsRequired set to true (証明書利用者で SignedSamlRequestsRequired が true に設定されている) | 合格/不合格 | アプリケーションは、SAML 要求の署名を検証するよう AD FS で構成されています。 Microsoft Entra ID では、署名された SAML 要求は受け入れられますが、署名は検証されません。 Microsoft Entra ID には、悪意のある呼び出しから保護するためのさまざまな方法があります。 たとえば、Microsoft Entra ID では、アプリケーションで構成された応答 URL を使用して SAML 要求が検証されます。 Microsoft Entra ID によってトークンが送信されるのは、アプリケーション用に構成された応答 URL のみです。 この結果によって移行が妨げられている場合は、[ご連絡ください](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)。 |
| Test-ADFSRPTokenLifetime  TokenLifetimeCheckResult | 合格/警告 | アプリケーションでカスタム トークンの有効期間が構成されています。 AD FS での既定値は 1 時間です。 Microsoft Entra ID では、条件付きアクセスを使用してこの機能がサポートされています。 詳細については、「[条件付きアクセスを使用して認証セッション管理を構成する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-session-lifetime)」を参照してください。 |
| Relying Party is set to encrypt claims. (証明書利用者が、要求を暗号化するよう設定されている)。 これは、Microsoft Entra ID でサポートされています | パス | Microsoft Entra ID を使用すると、アプリケーションに送信されるトークンを暗号化できます。 詳細については、｢[Microsoft Entra の SAML トークン暗号化を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)」を参照してください。 |
| EncryptedNameIdRequiredCheckResult | 合格/不合格 | アプリケーションは、SAML トークン内の nameID 要求を暗号化するよう構成されています。 Microsoft Entra ID を使用すると、アプリケーションに送信されるトークン全体を暗号化できます。 特定の要求の暗号化がまだサポートされていません。 詳細については、｢[Microsoft Entra の SAML トークン暗号化を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)」を参照してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-adfs-apps-phases-overview"} -->
## Microsoft Entra ID へのアプリケーションの移行を計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-phases-overview
- Service: entra-id / enterprise-apps
- Article date: 2023-05-31
- Summary: この記事では、Microsoft Entra ID の利点について説明し、詳細な計画と終了基準を使用して移行戦略を計画および実行するための 4 フェーズ ガイドを提供します。

この記事では、Microsoft Entra ID の利点と、アプリケーション認証の移行を計画する方法について説明します。 この記事では、移行戦略を計画し、Microsoft Entra 認証が組織の目標をどのようにサポートできるかを理解するのに役立つ、計画と終了基準の概要について説明します。

プロセスは 4 つのフェーズに分割されます。 各フェーズには、移行戦略を計画し、Microsoft Entra 認証が組織の目標をどのようにサポートするかを理解するのに役立つ詳細な計画と終了基準が含まれています。

### はじめに

現在、あなたの組織では、仕事を完了するためにユーザーは多くのアプリケーションを必要としています。 引き続き、毎日アプリを追加、開発、または削除する可能性があります。 ユーザーはさまざまな企業や個人のデバイス、および場所からこれらのアプリケーションにアクセスします。 彼らは次のようなさまざまな方法でアプリを開きます。

- 会社のホームページまたはポータルを経由する
- ブラウザーでブックマークに登録するまたはお気に入りに追加する
- サービスとしてのソフトウェア (SaaS) アプリのベンダーの URL を使用する
- モバイル デバイスまたはアプリケーション管理 (MDM または MAM) ソリューションを介して、ユーザーのデスクトップまたはモバイル デバイスに直接プッシュされたリンクを使用する

アプリケーションでは次の種類の認証が使用されている可能性があります。

- オンプレミスまたはクラウドでホストされる ID およびアクセス管理 (IAM) ソリューションのフェデレーション ソリューション (Active Directory フェデレーション サービス (AD FS)、Okta、Ping など) を介した Security Assertion Markup Language (SAML) または OpenID Connect (OIDC)
- Active Directory を使用した Kerberos または NTLM
- Ping Access を使用したヘッダーベースの認証

確実にユーザーがアプリケーションに簡単かつ安全にアクセスできるようにするための目標は、オンプレミスとクラウドの環境全体で 1 セットのアクセス制御とポリシーを使用することです。

[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra) によって提供されるユニバーサル ID プラットフォームでは、従業員、パートナー、顧客に対し、単一の ID で必要なアプリケーションにアクセスできます。 プラットフォームは、任意のプラットフォームとデバイスからのコラボレーションを促進します。

[Image: Microsoft Entra 接続を示す図。]

Microsoft Entra ID には、必要な ID 管理機能がすべて備わっています。 アプリの認証と承認を Microsoft Entra ID に標準化することで、これらの機能によって提供される利点が得られます。

その他の移行リソースについては、[https://aka.ms/migrateapps](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources) を参照してください

### 移行フェーズとプロジェクト戦略を計画する

テクノロジ プロジェクトが失敗する原因は、予測が一致しない、適切な利害関係者が関与していない、またはコミュニケーション不足にあることがよくあります。 プロジェクト自体を計画し、確実に成功するようにしてください。

#### 移行のフェーズ

ツールの詳細を確認する前に、移行プロセスをどのように検討するかを理解しておく必要があります。 いくつかの直接顧客ワークショップでは、次の 4 つのフェーズをお勧めします。

[Image: 移行のフェーズを示す図。]

#### プロジェクト チームを結成する

アプリケーションの移行はチームの作業です。あなたは確実に重要な職位をすべて埋める必要があります。 シニア ビジネス リーダーからのサポートが重要です。 必ず、エグゼクティブ スポンサー、ビジネス上の意思決定者、対象分野の専門家 (SME) の適切なセットを含めるようにしてください。

移行プロジェクト中は、組織の規模と構造に応じて、1 人のユーザーが複数の役割を果たす場合や、複数のユーザーがそれぞれの役割を果たす場合があります。 また、セキュリティ ランドスケープにおいて重要な役割を果たす他のチームにも依存関係がある場合があります。

次の表には、重要な役割とその貢献が含まれています。

| 役割 | 貢献 |
| --- | --- |
| **プロジェクト マネージャー** | プロジェクトを導く責任があるプロジェクト コーチは次のことを行います。 - 経営幹部からのサポートを得る - 利害関係者を参加させる - スケジュール、ドキュメント、コミュニケーションを管理する |
| **ID アーキテクト/Microsoft Entra アプリ管理者** | 以下のタスクを担当します。 - 利害関係者と連携してソリューションを設計する - 運用チームにハンドオフするためのソリューションの設計と運用手順を文書化する - 運用前環境および運用環境を管理する |
| **オンプレミスの AD 運用チーム** | AD フォレスト、LDAP ディレクトリ、HR システムなど、さまざまなオンプレミス ID ソースを管理する組織。 - 同期する前に必要な修復タスクを実行する - 同期に必要なサービス アカウントを提供する - Microsoft Entra ID にフェデレーションを構成するためのアクセス権を提供する |
| **IT サポート マネージャー** | ヘルプデスクの観点から、この変更のサポート可能性に関する情報を提供できる、IT サポート組織の代表。 |
| **セキュリティ所有者** | 計画が組織のセキュリティ要件を満たしていることを保証できる、セキュリティ チームの代表。 |
| **アプリケーションの技術所有者** | Microsoft Entra ID と統合されるアプリとサービスの技術所有者が含まれます。 彼らは、同期プロセスに含める必要があるアプリケーションの ID 属性を提供します。 通常、CSV 担当者と関係があります。 |
| **アプリケーションのビジネス所有者** | ユーザーの視点から、ユーザー エクスペリエンスとこの変更の有用性について意見を提供できる代表的な同僚。 この担当者は、アクセスの管理など、アプリケーションの全体的なビジネス面も所有します。 |
| **ユーザーのパイロット グループ** | 日常業務の一環としてパイロット エクスペリエンスをテストし、その他のデプロイをガイドするためのフィードバックを提供するユーザー。 |

#### 連絡を計画する

効果的なビジネス エンゲージメントとコミュニケーションは成功への鍵です。 情報を取得し、スケジュールの更新を常に把握するための手段を利害関係者とエンドユーザーに与えることが重要です。 移行の価値、予想されるタイムライン、および一時的なビジネスの中断を計画する方法について、すべてのユーザーを対象に教育します。 ブリーフィング セッション、電子メール、1 対 1 の会議、バナー、タウン ホールなど、複数の手段を使用します。

アプリに対して選択したコミュニケーション戦略に基づいて、保留中のダウンタイムをユーザーに通知することができます。 また、デプロイの延期が必要となる最近の変更やビジネスへの影響がないことを確認する必要もあります。

次の表では、利害関係者が常に状況を把握できるようにするために推奨される最小限のコミュニケーションについて説明します。

### 計画フェーズとプロジェクト戦略

| 通信 | 対象ユーザー |
| --- | --- |
| プロジェクトの認識とビジネスまたは技術的価値 | エンドユーザーを除くすべて |
| パイロット アプリの要請 | - アプリのビジネス所有者- アプリの技術所有者- アーキテクトと ID チーム |

**フェーズ 1 - 検出してスコープを設定する**:

| 通信 | 対象ユーザー |
| --- | --- |
| - アプリケーション情報の要請- スコーピング演習の結果 | - アプリの技術所有者- アプリのビジネス所有者 |

**フェーズ 2 - アプリを分類し、パイロットを計画する**:

| 通信 | 対象ユーザー |
| --- | --- |
| - 分類の結果と移行スケジュールにおける意味- 事前の移行スケジュール | - アプリの技術所有者 - アプリのビジネス所有者 |

**フェーズ 3 – 移行とテストを計画する**:

| 通信 | 対象ユーザー |
| --- | --- |
| - アプリケーションの移行テストの結果 | - アプリの技術所有者- アプリのビジネス所有者 |
| - 移行が予定されていることの通知と、その結果として得られるエンドユーザー エクスペリエンスについての説明。- ダウンタイムが予定されていることと、今すべきこと、フィードバック、 支援を受ける方法を含むきめ細かいコミュニケーション | - エンド ユーザー (およびその他すべて) |

**フェーズ 4 – 管理し、分析情報を得る**:

| 通信 | 対象ユーザー |
| --- | --- |
| 利用可能な分析とアクセス方法 | - アプリの技術所有者- アプリのビジネス所有者 |

### 移行の状態に関するコミュニケーション ダッシュボード

移行プロジェクトの全体的な状態を伝えることは、進行状況を示すうえで重要であり、移行が予定されているアプリの所有者がその移行を準備するのに役立ちます。 Power BI またはその他のレポート ツールを使用して、シンプルなダッシュボードを作成することにより、移行中にアプリケーションの状態を可視化することができます。

使用を検討する可能性がある移行の状態は次のとおりです。

| 移行の状態 | 行動計画 |
| --- | --- |
| **最初の要求** | アプリを見つけて、詳細について所有者に問い合わせる |
| **評価の完了** | アプリの所有者がアプリの要件を評価し、アプリのアンケートを返す |
| **構成が進行中** | Microsoft Entra ID に対する認証を管理するために必要な変更を進める |
| **テスト構成に成功** | 変更を評価し、テスト環境のテスト Microsoft Entra テナントに対してアプリを認証する |
| **運用環境の構成に成功** | 運用 AD テナントに対して動作するように構成を変更し、テスト環境でアプリの認証を評価する |
| **完了または署名** | アプリの変更を運用環境にデプロイし、運用 Microsoft Entra テナントに対して実行する |

このフェーズにより、アプリの所有者は、アプリが移行の準備が整ったときに、アプリの移行とテストのスケジュールがどのようになるかを把握できます。 また、移行された他のアプリの結果も把握しています。 また、所有者が移行中のアプリの問題を報告および表示できるように、バグ トラッカー データベースへのリンクを提供することも検討できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-adfs-apps-stages"} -->
## AD FS から Microsoft Entra ID にアプリケーション認証を移行するステージについて理解する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages
- Service: entra-id / enterprise-apps
- Article date: 2025-04-29
- Summary: 4 段階で AD FS から Microsoft Entra ID にアプリケーション認証を移行する。 移動、テスト構成、セキュリティで保護されたアプリを計画します。

Microsoft Entra ID によって提供されるユニバーサル ID プラットフォームでは、ユーザー、パートナー、顧客に対し、任意のプラットフォームやデバイスからアプリケーションにアクセスして共同作業を行うための 1 つの ID が提供されます。 Microsoft Entra ID には、必要な ID 管理機能がすべて備わっています。 アプリケーション認証と承認を Microsoft Entra ID に標準化すると、次のような利点があります。

### 移行するアプリの種類

アプリケーションでは、認証に最新のプロトコルまたはレガシ プロトコルを使用する場合があります。 Microsoft Entra ID への移行を計画する場合は、先進認証プロトコル (SAML や OpenID Connect など) を使用するアプリを最初に移行することを検討してください。

これらのアプリは、Azure アプリ ギャラリーの組み込みコネクタを使用して、Microsoft Entra ID で認証するように再構成できます。 カスタム アプリケーションを Microsoft Entra ID に登録することで再構成することもできます。

古いプロトコルを使用するアプリは、[アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)または[安全なハイブリッド アクセス (SHA) パートナー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access-integrations)のいずれかを使用して統合できます。

詳細については、以下を参照してください。

- [Microsoft Entra アプリケーション プロキシを使用してリモート ユーザー向けにオンプレミス アプリを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)。
- [アプリケーション管理とは](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management)
- [アプリケーションを Microsoft Entra ID に移行するための AD FS アプリケーション アクティビティ レポート](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-application-activity)。
- 「[Microsoft Entra Connect Health を使用して AD FS を監視する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs)」。

### 移行プロセス

Microsoft Entra ID にアプリ認証を移動するプロセスの間に、アプリと構成をテストします。 運用環境に移動する前の移行テスト用に、既存のテスト環境を引き続き使用することをお勧めします。 テスト環境を現在使用できない場合は、アプリケーションのアーキテクチャに応じて、[Azure App Service](https://azure.microsoft.com/services/app-service/) または [Azure 仮想マシン](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を使用して設定できます。

アプリ構成を開発する別のテスト Microsoft Entra テナントを設定することもできます。

移行プロセスは次のようになります。

#### ステージ 1 – 現在の状態: 運用アプリは AD FS で認証を行っている

[Image: 移行ステージ 1 を示す図。]

#### ステージ 2 – (省略可能) アプリのテスト インスタンスがテスト用の Microsoft Entra テナントを指し示すようにする

アプリのテスト インスタンスがテスト用の Microsoft Entra テナントを指し示すように構成を更新し、必要な変更を行います。 テスト用 Microsoft Entra テナントのユーザーを使用してアプリをテストできます。 開発プロセスの間に、[Fiddler](https://www.telerik.com/fiddler) などのツールを使用して、要求と応答を比較および検証できます。

テスト用のテナントを別に設定できない場合は、このステージをスキップし、下のステージ 3 で説明するように、アプリのテスト インスタンスが運用環境の Microsoft Entra テナントを指し示すようにします。

[Image: 移行ステージ 2 を示す図。]

#### ステージ 3 – アプリのテスト インスタンスが運用環境の Microsoft Entra テナントを指し示すようにする

アプリのテスト インスタンスが運用環境の Microsoft Entra テナントを指し示すように構成を更新します。 これで、運用テナントのユーザーを使用してテストできるようになります。 必要に応じて、この記事の、ユーザーの移行に関するセクションを確認してください。

[Image: 移行ステージ 3 を示す図。]

#### ステージ 4 – 運用アプリが運用環境の Microsoft Entra テナントを指し示すようにする

運用アプリの構成を更新して、運用環境の Microsoft Entra テナントを指し示すようにします。

[Image: 移行ステージ 4 を示す図。]

AD FS で認証を行うアプリでは、アクセス許可に Active Directory グループを使用する可能性があります。 移行を始める前にオンプレミス環境と Microsoft Entra ID の間で ID データを同期するには、[Microsoft Entra Connect 同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)を使用します。 アプリケーションが移行されるときに同じユーザーにアクセス権を付与できるよう、移行前にそれらのグループとメンバーシップを確認します。

### 基幹業務アプリ

基幹業務アプリは、組織が開発したアプリ、または標準のパッケージ製品であるアプリです。

OAuth 2.0、OpenID Connect、または WS-Federation を使用する基幹業務アプリは、[アプリの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)として Microsoft Entra ID と統合できます。 [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)のエンタープライズ アプリケーション ページで、SAML 2.0 または WS-Federation を使用するカスタム アプリを[ギャラリー以外のアプリケーション](https://entra.microsoft.com/#home)として統合します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-adfs-classify-apps-plan-pilot"} -->
## フェーズ 2:アプリを分類し、パイロットを計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-classify-apps-plan-pilot
- Service: entra-id / enterprise-apps
- Article date: 2024-08-25
- Summary: この記事では、AD FS から Microsoft Entra ID へのアプリケーションの移行を計画するフェーズ 2 について説明します

アプリの移行を分類することは、重要な演習です。 すべてのアプリを同時に移行して切り替える必要はありません。 各アプリに関する情報を収集したら、最初に移行する必要があるアプリと、時間がかかる可能性があるものを合理化します。

### 対象となるアプリを分類する

このアスペクトについて考える 1 つの方法は、ビジネス上の重要度、使用状況、および有効期間の軸に沿うことです。これらはそれぞれ、複数の要因に依存しています。

#### ビジネス上の重要度

ビジネス上の重要度には、ビジネスごとに異なるディメンションが使用されますが、考慮する必要がある 2 つの基準は、**特徴と機能**および**ユーザー プロファイル**です。 機能が重複しているか古いアプリよりも高いポイント値を、固有機能を持つアプリに割り当てます。

[Image: 特徴と機能およびユーザー プロファイルの適用範囲を示す図。]

#### 使用法

**使用率の高い**アプリケーションが、使用率の低いアプリよりも高い値を受け取る必要があります。 外部、経営幹部、またはセキュリティ チーム ユーザーが存在するアプリにはより高い値を割り当てます。 移行ポートフォリオ内の各アプリについて、これらの評価を完了します。

[Image: ユーザーのボリュームとユーザーの幅のスペクトラムを示す図。]

ビジネス上の重要度と使用状況の値を決定したら、**アプリケーションの有効期間**を決定し、優先順位のマトリックスを作成できます。 この図はマトリックスを示しています。

[Image: 使用状況、予想される有効期間、ビジネス上の重要度の関係を示す三角形の図。]

注

このビデオでは、移行プロセスのフェーズ 1 とフェーズ 2 の両方を取り上げています。

### 移行するアプリに優先度を付ける

組織のニーズに基づいて、優先度の最も低いアプリまたは優先度の最も高いアプリのいずれかを使用して、アプリの移行を開始することを選択できます。

Microsoft Entra ID と ID サービスの使用経験がないと思われるシナリオでは、まず**優先度の最も低いアプリ**を Microsoft Entra に移行することを検討してください。 このオプションにより、ビジネスへの影響を最小限に抑え、徐々に進めていくことができます。 これらのアプリを正常に移動し、利害関係者の信頼を得たら、他のアプリの移行を続けることができます。

明確な優先度がない場合は、[Microsoft Entra ギャラリー](https://azuremarketplace.microsoft.com/marketplace/apps/category/azure-active-directory-apps)にあり、複数の ID プロバイダーをサポートするアプリからまず移行することを検討してください。これらは統合が容易であるためです。 これらのアプリは、組織内で**優先度の最も高いアプリ**である可能性があります。 SaaS アプリケーションを Microsoft Entra ID と統合するのに役立つように、構成を段階的に説明する[チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)のコレクションがあります。

アプリの移行期限がある場合、これらの優先度の最も高いアプリのバケットに重点を置いて作業します。 期限を遅らせてもコストは変わらないため、最終的には優先度の低いアプリを選択できます。

この分類に加え、移行の緊急度に応じて、アプリ所有者がそれぞれのアプリを移行するために取り組む必要がある**移行スケジュール**を公開する必要があります。 このプロセスの最後には、移行のために優先度が付けられたバケットのすべてのアプリケーションの一覧が用意できているはずです。

### アプリをドキュメント化する

まず、アプリケーションについての重要情報を収集します。 [アプリケーション検出ワークシート](https://download.microsoft.com/download/2/8/3/283F995C-5169-43A0-B81D-B0ED539FB3DD/Application%20Discovery%20worksheet.xlsx)を使用すると、移行に関する決定を迅速に行い、ビジネス グループに対してすぐに推奨事項を伝えることができます。

移行を決定するうえで重要な情報には、次のものがあります。

- **アプリ名** – ビジネスにおいて、このアプリは何と呼ばれていますか?
- **アプリの種類** – サードパーティの SaaS アプリですか? カスタムの基幹 Web アプリですか? API ですか?
- **ビジネス上の重要度** – その重要度は高いですか? 低いですか? それともその中間ですか?
- **ユーザー アクセス ボリューム** – このアプリにはすべてのユーザーまたはごく少数のユーザーがアクセスしますか?
- **ユーザー アクセスの種類**: アプリケーションにアクセスする必要があるのはどのユーザーですか? 従業員、ビジネス パートナー、顧客、またはすべてですか?
- **計画された有効期間** – 利用可能時間はどのくらいですか? 6 か月未満ですか? 2 年を超えますか?
- **現在の ID プロバイダー** - このアプリの主要な IdP は何ですか? AD FS、Active Directory、または Ping Federate ですか?
- **セキュリティ要件** - アプリケーションにアクセスするには、アプリケーションに MFA が必要ですか、またはユーザーが企業ネットワーク上にある必要がありますか?
- **認証方法** – アプリはオープン標準を使用して認証されますか?
- **アプリ コードの更新を計画しているかどうか** - アプリは計画されている、またはアクティブな開発中ですか?
- **アプリをオンプレミスに保持する予定があるかどうか** - アプリをデータセンターに長期間保持しますか?
- **アプリが他のアプリまたは API に依存しているかどうか** – 現在、アプリから他のアプリまたは API を呼び出していますか?
- **アプリが Microsoft Entra ギャラリーにあるかどうか** - 現在、アプリは既に [Microsoft Entra ギャラリー](https://azuremarketplace.microsoft.com/marketplace/apps/category/azure-active-directory-apps)に統合されていますか?

次のようなその他のデータは、移行に関する決定を行ううえで現時点では不要ですが、後で役に立ちます。

- **アプリの URL** – ユーザーはアプリにアクセスするためにどこに移動しますか?
- **アプリケーション ロゴ**: Microsoft Entra アプリ ギャラリーにないアプリケーションを Microsoft Entra ID に移行する場合は、わかりやすいロゴを指定することをお勧めします
- **アプリの説明** – アプリの機能についての簡単な説明は何ですか?
- **アプリの所有者** – 社内のアプリの主要な POC は誰ですか?
- **一般的なコメントまたは注意事項** – アプリまたはビジネスの所有権に関するその他の一般的な情報

アプリケーションを分類し、詳細を文書化したら、必ず、計画された移行戦略に対するビジネス所有者の同意を得てください。

### アプリケーション ユーザー

Microsoft Entra ID がサポートしているアプリとリソースのユーザーには、次の 2 つの主なカテゴリがあります。

- **内部:** ID プロバイダー内にアカウントを持つ従業員、請負業者、およびベンダー。 このカテゴリには、マネージャーまたはリーダーと他の従業員では規則が異なるピボットがさらに必要になる可能性があります。
- **外部:**[Microsoft Entra B2B コラボレーション](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)を使って通常の業務で組織とやりとりするベンダー、供給元、販売代理店、またはその他のビジネス パートナー。

これらのユーザーのグループを定義し、さまざまな方法でこれらのグループを設定することができます。 管理者が手動でメンバーをグループに追加しなければならないようにすることも、セルフサービス動的メンバーシップ グループを有効にすることもできます。 [動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership)を使用して、指定された基準に基づきメンバーをグループに自動的に追加するルールを確立できます。

外部ユーザーが顧客を参照する場合もあります。 [Azure AD B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview) という別の製品でカスタマー認証がサポートされています。 しかし、本書ではこれについて説明しません。

### パイロットを計画する

パイロット用に選択したアプリでは、組織の主要な ID とセキュリティ要件を表す必要があります。また、アプリケーションの所有者からの明確な同意が必要です。 パイロットは通常、別個のテスト環境で実行されます。

外部のパートナーを忘れないでください。 彼らが移行スケジュールとテストに参加していることをご確認ください。 最後に、問題が発生した場合に、ヘルプ デスクにアクセスする方法があることを確認します。

### 制限事項について計画する

一部のアプリの移行は簡単ですが、サーバーまたはインスタンスが複数あることが原因で、時間がかかる可能性があるものもあります。 たとえば、カスタム サインイン ページが原因で SharePoint の移行には時間がかかることがあります。

多くの SaaS アプリ ベンダーは、アプリケーションを再構成するためのセルフサービスの手段を提供せず、SSO 接続の変更料を請求します。 彼らに確認し、制限について計画してください。

### アプリ所有者の承認

ビジネス クリティカルで一般的に使用されるアプリケーションでは、パイロット段階でアプリをテストするためにパイロット ユーザーのグループが必要になる場合があります。 運用前の環境またはパイロット環境でアプリをテストしたら、そのアプリを移行する前にアプリ ビジネスの所有者がパフォーマンスについて承認をしていることを確認してください。 また、すべてのユーザーが認証に Microsoft Entra ID を運用環境で使用するように移行することも確認する必要があります。

### セキュリティ体制を計画する

移行プロセスを開始する前に、企業の ID システム用に開発するセキュリティ体制を十分に検討してください。 このアスペクトは、重要な情報である**データにアクセスしている ID、デバイス、場所**を収集することに基づいています。

#### ID とデータ

ほとんどの組織には、業界や組織内の職務によって異なる ID とデータ保護に関する特定の要件があります。 推奨事項については、「[ID とデバイスのアクセス構成](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/microsoft-365-policies-configurations)」を参照してください。 推奨事項には、[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)と関連する機能の所定のセットが含まれます。

この情報を使って、Microsoft Entra ID に統合されているすべてのサービスへのアクセスを保護できます。 これらの推奨事項は、Microsoft セキュア スコアと [Microsoft Entra ID の ID スコア](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-identity-secure-score)に準拠しています。 このスコアは、次のために役立ちます。

- ID セキュリティ体制を客観的に測定する
- ID セキュリティの強化を計画する
- 強化の成功を確認する

Microsoft セキュア スコアは、[ID インフラストラクチャをセキュリティで保護するための 5 つのステップ](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity)を実装するのにも役立ちます。 組織の出発点としてガイダンスを使用し、組織固有の要件を満たすようにポリシーを調整します。

#### データへのアクセスに使用されるデバイスまたは場所

ユーザーがアプリへのアクセスに使用するデバイスと場所も重要です。 企業ネットワークに物理的に接続されているデバイスのほうが安全です。 ネットワークの外部からの VPN 経由の接続には、調査が必要になる場合があります。

[Image: ユーザーの場所とデータ アクセスの関係を示す図。]

リソース、ユーザー、デバイスのこれらの側面を考慮して、[Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)機能を使うことを選択できます。 条件付きアクセスがユーザーのアクセス許可を超えています。 次のような要因の組み合わせによって異なります。

- ユーザーまたはグループの ID
- ユーザーが接続されているネットワーク
- ユーザーが使用しているデバイスとアプリケーション
- ユーザーがアクセスしようとしているデータの種類。

ユーザーに付与されたアクセス権は、このより広範な条件セットに適応します。

### 終了基準

次のような場合、このフェーズは成功です。

- 移行する予定のアプリを完全に文書化した
- ビジネス上の重要度、使用量、および有効期間に基づいて、アプリに優先度を付けた
- パイロットの要件を表すアプリを選択した
- 優先度付けと戦略について、ビジネス所有者の同意を得ている
- セキュリティ体制のニーズとその実装方法を理解している
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-adfs-discover-scope-apps"} -->
## フェーズ 1:アプリを検出してスコープを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-discover-scope-apps
- Service: entra-id / enterprise-apps
- Article date: 2025-01-31
- Summary: この記事では、AD FS から Microsoft Entra ID へのアプリケーションの移行を計画するフェーズ 1 について説明します

アプリケーションの検出と分析は、適切な出発点を提供するための基本的な演習です。 すべてを把握していない場合があるため、不明なアプリに対応できるように準備する必要があります。

### 自分のアプリを見つける

移行プロセスではまず、どのアプリを移行するのか、どれを維持する必要があるのか (存在する場合)、どのアプリを非推奨にするのかを決定します。 組織で使用しないアプリはいつでも非推奨にできます。 組織でアプリを見つける方法はいくつかあります。 アプリの検出時には、必ず開発中および計画段階のアプリを含めるようにしてください。 今後のすべてのアプリで認証には、Microsoft Entra ID を使用してください。

ADFS を使用してアプリケーションを検出する:

- **Microsoft Entra Connect Health for ADFS を使用する**: Microsoft Entra ID P1 または P2 ライセンスをお持ちの場合は、[Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs) をデプロイして、オンプレミス環境でアプリの使用状況を分析することをお勧めします。 [ADFS アプリケーション レポート](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-application-activity)を使用して、移行可能な ADFS アプリケーションを検出し、移行するアプリケーションの準備状況を評価できます。
- Microsoft Entra ID P1 または P2 ライセンスをお持ちでない場合は、ADFS を使用して [PowerShell](https://github.com/AzureAD/Deployment-Plans/tree/master/ADFS%20to%20AzureAD%20App%20Migration) に基づき Microsoft Entra アプリ移行ツールを使用することをお勧めします。 [ソリューション ガイド](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages)を参照してください:

注

このビデオでは、移行プロセスのフェーズ 1 とフェーズ 2 の両方を取り上げています。

### 他の ID プロバイダー (IdP) の使用

他の ID プロバイダーを使用している場合は、次の方法を使用してアプリケーションを検出できます。

- 現在 Okta を使用している場合は、[Okta から Microsoft Entra への移行ガイド](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-applications-from-okta)を参照してください。
- 現在 Ping フェデレーションを使用している場合は、[Ping 管理 API](https://docs.pingidentity.com/pingfederate/latest/developers_reference_guide/pf_admin_api.html) の使用を検討してください
- アプリケーションが Active Directory と統合されている場合は、アプリケーションに使用できるサービス プリンシパルまたはサービス アカウントを検索します。

### Cloud Discovery ツールの使用

クラウド環境では、ご利用のクラウド サービス全体にわたるサイバー攻撃の脅威を検出し、対処するために、豊富な表示機能、データ送受信の制御、高度な分析が必要です。 次のツールを使用して、クラウド アプリのインベントリを収集できます。

- **クラウド アクセス セキュリティ ブローカー (CASB**) – [CASB](https://learn.microsoft.com/ja-jp/defender-cloud-apps/) は通常、ファイアウォールと共に機能し、従業員のクラウド アプリケーションの使用状況を可視化します。これは、サイバーセキュリティの脅威から企業データを保護するのに役立ちます。 CASB レポートは、組織内で最も使用されているアプリと、Microsoft Entra ID に移行する初期のターゲットを判別するのに役立ちます。
- **Cloud Discovery** - [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/what-is-defender-for-cloud-apps) を構成して、クラウド アプリの使用状況を把握し、承認されていないまたはシャドウ IT アプリを検出できます。
- **Azure でホストされているアプリケーション**- Azure インフラストラクチャに接続されているアプリでは、それらのシステム上の API とツールを使用して、ホストされているアプリのインベントリを確認できます。 Azure 環境では、次のことを行います。
    - [Get-AzWebApp](https://learn.microsoft.com/ja-jp/powershell/module/Az.websites/get-Azwebapp) コマンドレットを使用して、Azure Web Apps に関する情報を取得します。
    - Microsoft Entra ID でクエリを実行して、[アプリケーション](https://learn.microsoft.com/ja-jp/previous-versions/azure/ad/graph/api/entity-and-complex-type-reference#application-entity)と[サービス プリンシパル](https://learn.microsoft.com/ja-jp/previous-versions/azure/ad/graph/api/entity-and-complex-type-reference#serviceprincipal-entity)を検索する。

### 手動検出プロセス

この記事で説明している自動アプローチを使用すると、自分のアプリケーションを適切に処理できます。 しかし、すべてのユーザー アクセス領域で十分な範囲を確保するために、次のようにすることを検討してください。

- 組織内のさまざまなビジネス所有者に連絡して、組織内で使用されているアプリケーションを見つける。
- プロキシ サーバーで HTTP 検査ツールを実行するか、プロキシ ログを分析して、トラフィックが一般的にルーティングされる場所を確認する。
- 人気のある会社のポータル サイトからの Web ログを調べ、ユーザーが最もアクセスするリンクを確認する。
- 経営幹部やその他の主要なビジネス メンバーに連絡し、ビジネスクリティカル アプリを確実に対象とする。

### 移行するアプリの種類

自分のアプリが見つかったら、組織内の次の種類のアプリを特定します。

- [Security Assertion Markup Language (SAML)](https://learn.microsoft.com/ja-jp/entra/architecture/auth-saml) や [OpenID Connect (OIDC)](https://learn.microsoft.com/ja-jp/entra/architecture/auth-oidc) などの最新の認証プロトコルを使用するアプリ。
- [Kerberos](https://techcommunity.microsoft.com/t5/itops-talk-blog/deep-dive-how-azure-ad-kerberos-works/ba-p/3070889) や NT LAN Manager (NTLM) のようなレガシー認証を使用しているアプリを最新化することを選択する。
- 最新化しないことを選択したレガシ認証プロトコルを使用しているアプリ
- 新しい基幹業務 (LoB) アプリ

#### 既に先進認証を使用しているアプリ

既に最新化されているアプリは、Microsoft Entra ID に移行される可能性が最も高くなります。 これらのアプリでは、SAML や OIDC などの最新の認証プロトコルが既に使用されており、Microsoft Entra ID で認証するように再構成できます。

[Microsoft Entra アプリ ギャラリー](https://azuremarketplace.microsoft.com/marketplace/apps/category/azure-active-directory-apps)からアプリケーションを検索して追加することをお勧めします。 ギャラリーに見つからない場合でも、カスタム アプリケーションをオンボードできます。

#### 最新化することを選択したレガシ アプリ

最新化を行うレガシ アプリに対して、コア認証と承認を Microsoft Entra ID に移行すると、[Microsoft Graph](https://developer.microsoft.com/graph/gallery/?filterBy=Samples,SDKs) および[インテリジェント セキュリティ グラフ](https://www.microsoft.com/security/operations/intelligence?rtc=1)が提供するすべての機能や豊富なデータを利用できるようになります。

これらのアプリケーションについては、レガシ プロトコル (Windows 統合認証、Kerberos、HTTP ヘッダーベースの認証など) から最新のプロトコル (SAML や OpenID Connect など) に認証スタック コードを更新することをお勧めします。

#### 最新化しないことを選択したレガシ アプリ

レガシ認証プロトコルを使用している特定のアプリでは、ビジネス上の理由により、認証の最新化が適切ではない場合があります。 これには、次の種類のアプリが含まれます。

- コンプライアンスまたは制御上の理由により、オンプレミスに保持されているアプリ。
- 変更したくないオンプレミスの ID またはフェデレーション プロバイダーに接続されているアプリ。
- 移動する予定がないオンプレミスの認証標準を使用して開発されたアプリ

これらのレガシ アプリに対し、Microsoft Entra ID は大きな利点をもたらします。 これらのアプリにまったく手を加えることなく、[多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)、[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)、[Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/)、[委任されたアプリケーション アクセス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-self-service-access)、[アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/manage-user-access-with-access-reviews#create-and-perform-an-access-review)など、最新の Microsoft Entra のセキュリティとガバナンス機能を有効にすることができます。

- [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)を使用して、これらのアプリをクラウドに拡張することから始めましょう。
- または、既にデプロイされている可能性のある、いずれかの[セキュア ハイブリッド アクセス (SHA) パートナー統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access)を使用を検討します。

#### 新しい基幹業務 (LoB) アプリ

通常は、組織の社内で使用する LoB アプリを開発します。 パイプラインに新しいアプリがある場合は、[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)を使用して OIDC を実装することをお勧めします。

### 非推奨にするアプリ

所有者およびメンテナンスと監視が明確でないアプリは、組織にセキュリティ上のリスクをもたらします。 次の場合は、アプリケーションを非推奨にすることを検討してください。

- 他のシステムと**機能が非常に重複している**
- **ビジネス所有者がいない**
- **使用されていないこと**が明らかである

**影響が大きい、ビジネスクリティカル アプリケーションは非推奨にしない**ことをお勧めします。 そのような場合は、ビジネス所有者と協力して適切な戦略を決定してください。

### 終了基準

次のような場合、このフェーズは成功です。

- 移行のスコープ内のアプリケーション、最新化が必要なアプリケーション、そのまま維持する必要があるアプリケーション、または非推奨とマークしたアプリケーションを十分に把握している。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-adfs-plan-management-insights"} -->
## フェーズ 4: 管理と分析情報について計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-plan-management-insights
- Service: entra-id / enterprise-apps
- Article date: 2023-05-30
- Summary: この記事では、AD FS から Microsoft Entra ID へのアプリケーションの移行を計画するフェーズ 4 について説明します

アプリが移行されたら、確実に次のようにする必要があります。

- ユーザーが安全にアクセスして管理できる
- 使用状況とアプリの正常性に関する適切な分析情報を得ることができる

組織に応じて、次の操作を行うことをお勧めします。

### ユーザーのアプリへのアクセスを管理する

アプリを移行したら、次の提案を適用してユーザーのエクスペリエンスを強化することを検討してください。

- [Microsoft MyApplications ポータル](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510#download-and-install-the-my-apps-secure-sign-in-extension)にアプリを公開して、見つけられるようにする。
- ユーザーがビジネス機能に基づいてアプリケーションを検索できるように、[アプリ コレクション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/access-panel-collections)を追加する。
- [MyApplications ポータル](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510#download-and-install-the-my-apps-secure-sign-in-extension)に独自のアプリケーション ブックマークを追加する。
- アプリに対して[アプリケーションのセルフサービス アクセス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-self-service-access)を有効にし、**キュレーションしたアプリをユーザーが追加できるようにする**。
- 必要に応じて、[エンド ユーザーに対してアプリケーションを非表示にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/hide-application-from-user-portal)。
- ユーザーは [Office.com](https://www.office.com) に移動して**対象のアプリを検索し、最近使用したアプリを表示する**ことができます。これは、作業場から直接行うことができます。
- ユーザーは Chrome または Microsoft Edge で MyApps のセキュリティで保護されたサインイン拡張機能をダウンロードできます。これにより、MyApplications に最初に移動することなく、ブラウザーから直接アプリケーションを起動できます。
- ユーザーは、[iOS 7.0](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/hide-application-from-user-portal) 以降または [Android](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/hide-application-from-user-portal) デバイス上で Intune で管理されたブラウザーを使用して、MyApps ポータルにアクセスできます。

    - **Android デバイス**の場合は、[Google Play ストア](https://play.google.com/store/apps/details?id=com.microsoft.intune)から
    - **Apple デバイス**の場合は、[Apple App Store から](https://apps.apple.com/us/app/intune-company-portal/id719171358)。

### アプリへのアクセスをセキュリティで保護する

Microsoft Entra ID によって、移行されたアプリを管理するための一元的なアクセスの場所が提供されます。 [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインし、次の機能を有効にします。

- **アプリへのユーザー アクセスをセキュリティで保護する。** デバイスの状態や場所などに基づいてアプリケーションへのユーザー アクセスをセキュリティで保護するには、[条件付きアクセスポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を有効にします。
- **自動プロビジョニング。** ユーザーがアクセスする必要があるさまざまなサードパーティ製の SaaS アプリを使用して、[ユーザーの自動プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を設定します。 これには、ユーザー ID の作成に加えて、状態または役割が変化したときのユーザー ID のメンテナンスおよび削除が含まれます。
- **ユーザー アクセス** **管理を委任する**。 必要に応じて、ご利用のアプリに対してアプリケーションのセルフサービス アクセスを有効にし、"*それらのアプリへのアクセスを承認するビジネス承認者を割り当てます*"。 アプリのコレクションに割り当てられたグループには、[セルフサービス グループ管理](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)を使用します。
- **ディレクトリ ロール を使用して管理者アクセス** を委任し、管理者ロール (アプリケーション管理者、クラウド アプリケーション管理者、アプリケーション開発者など) をユーザーに割り当てます。
- **アプリケーションをアクセス パッケージに追加**して、ガバナンスと構成証明を提供します。

### アプリを監査し、分析情報を得る

[Microsoft Entra 管理センター](https://entra.microsoft.com)を使用し、一元化された場所からすべてのアプリを監査することもできます。

- **[エンタープライズ アプリケーション] の [監査]** を使用して**アプリを監査**したり、[Microsoft Entra reporting API](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-prerequisites-for-reporting-api) から同じ情報にアクセスして、お気に入りのツールに統合したりします。
- OAuth または OpenID Connect を使用するアプリの場合は、**[エンタープライズ アプリケーション] の [アクセス許可]** を使用して、**アプリのアクセス許可を表示します**。
- **[エンタープライズ アプリケーション] の [サインイン]** を使用して、**サインインの分析情報を取得します**。[Microsoft Entra reporting API](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-configure-prerequisites-for-reporting-api) から同じ情報にアクセスします。
- **Microsoft Entra ID Power BI コンテンツ パック**から[アプリの使用状況を視覚化する](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-workbooks)

### 終了基準

次のような場合、このフェーズは成功です。

- ユーザーにセキュリティで保護されたアプリへのアクセスを提供している
- 移行されたアプリを監査して分析情報を得るために管理している

### デプロイ計画でさらに多くのことを行う

デプロイ計画では、アプリの移行シナリオを含む、Microsoft Entra ソリューションのビジネス価値、計画、実装手順、および管理について説明します。 デプロイおよび Microsoft Entra 機能から価値を得る作業を開始するのに必要なすべてをまとめます。 デプロイ ガイドには、Microsoft が推奨するベスト プラクティス、エンドユーザーのコミュニケーション、計画ガイド、実装手順、テスト ケースなどの内容が含まれています。

使用できる[デプロイ計画](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans)は多数ありますが、常にさらに多くのもの策定しています。

### サポートにお問い合せください

サポート チケットを作成または追跡したり、正常性を監視したりする場合は、次のサポート リンクを参照してください。

- **Azure サポート:**[Microsoft サポート](https://azure.microsoft.com/support)にお問い合わせいただき、Microsoft との Enterprise Agreement に応じて、任意の Azure ID デプロイの問題に関するチケットを開くことができます。
- **FastTrack**: Enterprise Mobility + Security (EMS) ライセンス、もしくは Microsoft Entra ID P1 または P2 ライセンスを購入した場合は、[FastTrack プログラム](https://learn.microsoft.com/ja-jp/microsoft-365/fasttrack/introduction)からデプロイのサポートを受ける資格があります。
- **製品エンジニアリング チームに参加する:** 数百万ものユーザーが存在する大規模な顧客デプロイで作業している場合は、Microsoft アカウント チームまたはクラウド ソリューション アーキテクトからサポートを受ける権利があります。 プロジェクトのデプロイの複雑さに基づいて、[Azure ID 製品エンジニアリング チーム](https://portal.azure.com/#blade/Microsoft_Azure_Marketplace/MarketplaceOffersBlade/selectedMenuItemId/solutionProviders)と直接連携することができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-adfs-plan-migration-test"} -->
## フェーズ 3: 移行とテストを計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-plan-migration-test
- Service: entra-id / enterprise-apps
- Article date: 2023-05-30
- Summary: この記事では、AD FS から Microsoft Entra ID へのアプリケーションの移行を計画するフェーズ 3 について説明します

ビジネス上の同意を得たら、次の手順として、Microsoft Entra 認証へのこれらのアプリの移行を開始します。

### 移行ツールとガイダンス

提供されているツールとガイダンスを使用して、Microsoft Entra ID にアプリケーションを移行するために必要となる正確な手順に従います:

- **一般的な移行ガイダンス** – [Microsoft Entra アプリの移行ツールキット](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources)のホワイトペーパー、ツール、電子メール テンプレート、およびアプリケーションのアンケートを使用して、アプリの検出、分類、および移行を行います。
- **SaaS アプリケーション** – [SaaS アプリのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)のリストと、「[Microsoft Entra SSO デプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment)」を参照して、エンドツーエンド プロセスを確認してください。
- **オンプレミスで実行されているアプリケーション** – [Microsoft Entra アプリケーション プロキシについて](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)十分理解し、完全な [Microsoft Entra アプリケーション プロキシ デプロイ計画](https://aka.ms/AppProxyDPDownload)を使って迅速に作業を進めるか、既に所有している可能性がある[セキュア ハイブリッド アクセス パートナー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access)を検討します。
- **開発中のアプリ** – 詳細な[統合](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)と[登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)のガイダンスを参照してください。

### テストを計画する

移行プロセス時に、アプリには通常のデプロイ中に使用されるテスト環境が既に存在する場合があります。 移行のテストにこの環境を引き続き使用することができます。 テスト環境を現在使用できない場合、アプリケーションのアーキテクチャによっては、Azure App Service または Azure Virtual Machines を使用して設定できます。

アプリの構成を開発するための別のテスト Microsoft Entra テナントを設定することもできます。 このテナントはクリーンな状態で開始され、どのシステムとも同期するように構成されません。

アプリの構成方法に応じて、SSO が正常に機能することを確認します。

| 認証の種類 | テスティング |
| --- | --- |
| **OAuth/OpenID Connect** | **[エンタープライズ アプリケーション] &gt; [アクセス許可]** の順に選択し、アプリのユーザー設定において組織内で使用されるアプリケーションに確実に同意しておきます。 |
| **SAML ベースの SSO** | [\[シングル サインオン\]](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/debug-saml-sso-issues) の下にある **[SAML 設定のテスト]** ボタンを使用します。 |
| **パスワードベースの SSO** | [マイ アプリによるセキュリティで保護されたサインイン拡張機能](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510#download-and-install-the-my-apps-secure-sign-in-extension)をダウンロードしてインストールします。 この拡張機能は、SSO プロセスを使用する必要がある組織の任意のクラウド アプリを開始する場合に役立ちます。 |
| **[アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)** | コネクタが実行されていて、アプリケーションに割り当てられていることを確認します。 詳細については、[アプリケーション プロキシのトラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-troubleshoot)に関する記事をご覧ください。 |

テスト ユーザーでログインすることで各アプリをテストし、すべての機能が移行前と同じであることを確認できます。 テスト中に、ユーザーが [MFA](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userstates) または [SSPR](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr) の設定を更新する必要があると判断した場合、または移行中にこの機能を追加する場合は、必ず、エンド ユーザーのコミュニケーション計画にそれを追加してください。 [MFA](https://aka.ms/mfatemplates) と [SSPR](https://aka.ms/ssprtemplates) のエンド ユーザー通信テンプレートを参照してください。

### トラブルシューティング

問題が発生した場合は、[アプリのトラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/isv-automatic-provisioning-multi-tenant-apps)と[安全なハイブリッド アクセスのパートナー統合に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access-integrations)を参照して役立ててください。 トラブルシューティングの記事を確認することもできます。「[SAML ベースのシングル サインオンで構成されたアプリへのサインインに関する問題](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/troubleshoot-sign-in-saml-based-apps)」を参照してください。

### ロールバックを計画する

移行が失敗した場合、AD FS サーバー上の既存の証明書利用者はそのままにして、証明書利用者へのアクセスを削除することをお勧めします。 これにより、デプロイの間に必要になった場合、迅速にフォールバックを行うことができます。

移行の問題を軽減するためのアクションに関する次の推奨事項を検討してください。

- アプリの既存の構成の**スクリーンショットを取得**します。 もう一度アプリを再構成する必要があるかどうかを確認できます。
- また、クラウド認証に問題がある場合に備えて、**代替認証オプション (レガシまたはローカル認証) を使用するためのリンクをアプリケーションに提供**することも検討します。
- 移行を完了する前に、既存の ID プロバイダーで**既存の構成を変更しないでください**。
- **複数の IdP をサポートするアプリ**では、より簡単なロールバック計画が提供されます。
- アプリのエクスペリエンスに、 **[フィードバック] ボタン**または問題が発生したときの**ヘルプ デスク**へのポインターがあることを確認します。

#### 従業員への通知

計画的な停止期間自体は最小限で済む可能性がありますが、それでも AD FS から Microsoft Entra ID に切り替えるときは、これらの時間枠を従業員に事前に伝達することを計画する必要があります。 アプリのエクスペリエンスに、[フィードバック] ボタンまたは問題が発生したときのヘルプ デスクへのポインターがあることを確認します。

デプロイが完了したら、ユーザーに、デプロイが成功したことを知らせるとともに、実行する必要がある手順について再確認させることができます。

- [マイ アプリ](https://myapps.microsoft.com)を使用して、移行されたすべてのアプリケーションにアクセスするようにユーザーに指示します。
- MFA の設定の更新が必要な場合があることをユーザーに通知します。
- セルフサービス パスワード リセットがデプロイされている場合、ユーザーは自分の認証方法を更新または確認することが必要な場合があります。 [MFA](https://aka.ms/mfatemplates) と [SSPR](https://aka.ms/ssprtemplates) のエンド ユーザー通信テンプレートを参照してください。

#### 外部ユーザーへの通知

このユーザー グループは通常、問題が発生した場合に最も重大な影響を受けます。 セキュリティ対策によって外部パートナーに対して異なる一連の条件付きアクセス規則またはリスク プロファイルが指示されている場合は特にそうです。 外部パートナーがクラウド移行スケジュールを認識していること、およびパイロット デプロイへの参加が推奨される期間が外部パートナーに提供されていることを確認します。パイロット デプロイでは、外部コラボレーションに固有のすべてのフローをテストします。 最後に、問題が発生した場合にヘルプ デスクにアクセスする方法があることを確認します。

### 終了基準

次のような場合、このフェーズは成功です。

- 移行ツールを確認した
- テスト環境とグループを含む、テストを計画した
- ロールバックを計画した
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-adfs-represent-security-policies"} -->
## Microsoft Entra ID で AD FS セキュリティ ポリシーを表す: マッピングと例 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-represent-security-policies
- Service: entra-id / enterprise-apps
- Article date: 2023-05-31
- Summary: アプリ認証の移行時に、認可規則や多要素認証規則などの AD FS セキュリティ ポリシーを Microsoft Entra ID にマップする方法について説明します。

この記事では、アプリ認証を移行するときに、承認ルールと多要素認証ルールを AD FS から Microsoft Entra ID にマップする方法について説明します。 各規則のマッピングによってアプリの移行プロセスを容易にしながら、アプリ所有者のセキュリティ要件を満たす方法を確認できます。

アプリの認証を Microsoft Entra ID に移動する場合は、既存のセキュリティ ポリシーから、Microsoft Entra ID で使用できる同等または代替のバリアントへのマッピングを作成します。 アプリの所有者によって要求されるセキュリティ標準に適合しながら、これらのマッピングを確実に実行できるようにすることで、残りのアプリの移行が容易になります。

各規則の例について、ここでは、AD FS での規則の表示方法、AD FS の規則言語と同等のコード、および Microsoft Entra ID にこれをマップする方法を示します。

### 承認規則のマッピング

AD FS の承認規則のさまざまな種類の例と、それらを Microsoft Entra ID にマップする方法を次に示します。

#### 例 1:すべてのユーザーにアクセスを許可する

AD FS ですべてのユーザーにアクセスを許可する:

[Image: すべてのユーザーのアクセスを編集する方法を示すスクリーンショット。]

これは、次のいずれかの方法で Microsoft Entra ID にマップされます。

1. **[割り当てが必要]** を **[いいえ]** に設定します。

    注

    **[割り当てが必要]** を **[はい]** に設定すると、アクセスできるようにするにはユーザーをアプリケーションに割り当てる必要があります。 **[いいえ]** に設定すると、すべてのユーザーがアクセスできます。 このスイッチでは、 **[マイ アプリ]** エクスペリエンスでユーザーに表示されるものは制御されません。
2. **[ユーザーとグループ]** タブで、アプリケーションを **[すべてのユーザー]** 自動グループに割り当てます。 既定の[すべてのユーザー](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-create-rule) グループを使用できるようにするには、Microsoft Entra テナントで**動的グループを有効にする**必要があります。

    [Image: Microsoft Entra ID のマイ SaaS アプリを示すスクリーンショット。]

#### 例 2:グループを明示的に許可する

AD FS での明示的なグループの承認:

[Image: 要求規則 Allow domain admins の [規則の編集] ダイアログ ボックスを示すスクリーンショット。]

このルールを Microsoft Entra ID にマップするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/#home)で、AD FS のユーザーのグループに対応する[ユーザー グループを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups)します。
2. グループにアプリのアクセス許可を割り当てます。

    [Image: 割り当てをアプリに追加する方法を示すスクリーンショット。]

#### 例 3: 特定のユーザーを承認する

AD FS での明示的なユーザーの承認:

[Image: [入力方向の要求の種類] に [プライマリ SID] が表示されている要求規則 Allow a specific user の [規則の編集] ダイアログ ボックスを示すスクリーンショット。]

このルールを Microsoft Entra ID にマップするには:

- [Microsoft Entra 管理センター](https://entra.microsoft.com/#home)で、次に示すように、アプリの [割り当ての追加] タブを使用してアプリにユーザーを追加します。

    [Image: Azure のマイ SaaS アプリを示すスクリーンショット。]

### 多要素認証規則のマッピング

AD FS とフェデレーションしているため、[多要素認証 (MFA)](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) と AD FS のオンプレミスでの展開は、移行後も引き続き機能します。 ただし、Microsoft Entra の条件付きアクセス ポリシーに関連付けられている Azure の組み込みの MFA 機能に移行することを検討してください。

AD FS における MFA 規則の種類の例と、さまざまな条件に基づいてそれらを Microsoft Entra ID にマップする方法を次に示します。

AD FS での MFA 規則の設定:

[Image: Microsoft Entra 管理センターの Microsoft Entra ID の [条件] を示すスクリーンショット。]

#### 例 1:ユーザーまたはグループに基づいて MFA を適用する

ユーザーやグループのセレクターは、グループごと (グループ SID) またはユーザーごと (プライマリ SID) に MFA を適用できる規則です。 ユーザーやグループの割り当てとは別に、AD FS MFA 構成 UI のその他のチェック ボックスはすべて、ユーザーやグループの規則が適用された後に評価される追加の規則として機能します。

[一般的な条件付きアクセス ポリシー: すべてのユーザーに対して MFA を必須にする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)

#### 例 2:未登録のデバイスに MFA を適用する

Microsoft Entra で未登録のデバイスに対する MFA 規則を指定します。

[一般的な条件付きアクセス ポリシー: すべてのユーザーに対して準拠デバイス、Microsoft Entra ハイブリッド参加済みデバイス、または多要素認証を必須にする](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-alt-all-users-compliant-hybrid-or-mfa)

### 出力属性を要求規則としてマッピングする

AD FS における要求としての属性の出力規則:

[Image: Emit attributes as Claims の [規則の編集] ダイアログ ボックスを示すスクリーンショット。]

ルールを Microsoft Entra ID にマップするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com/#home)で、**[エンタープライズ アプリケーション]**、**[シングル サインオン]** の順に選択して、SAML ベースのサインオン構成を表示します。

    [Image: エンタープライズ アプリケーションのシングル サインオン ページを示すスクリーンショット。]
2. 属性を変更するには、 **[編集]** (強調表示) を選択します。

    [Image: ユーザー属性とクレームを編集するページを示すスクリーンショット。]

### 組み込みアクセス制御ポリシーのマッピング

AD FS 2016 の組み込みアクセス制御ポリシー:

[Image: Microsoft Entra ID の組み込みのアクセス制御ポリシーを示すスクリーンショット。]

Microsoft Entra ID で組み込みのポリシーを実装するには、[新しい条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)を使用してアクセス制御を構成するか、AD FS 2016 のカスタム ポリシー デザイナーを使用してアクセス制御ポリシーを構成します。 ルール エディターには、あらゆる種類の配列を作成するのに役立つ、許可オプションと除外オプションの完全な一覧があります。

[Image: Microsoft Entra ID の組み込みのアクセス制御ポリシーを示すスクリーンショット。]

次の表では、いくつかの便利な許可および除外オプションと、それらが Microsoft Entra ID にどのようにマッピングするかを示します。

| オプション | Microsoft Entra ID で Permit オプションを構成する方法 | Microsoft Entra ID で Except オプションを構成する方法 |
| --- | --- | --- |
| 特定のネットワークから | Microsoft Entra の[ネームド ロケーション](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)にマップする | **信頼できる場所**に対しては [\[除外\]](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network#trusted-locations) オプションを使用します |
| 特定のグループから | [ユーザーやグループの割り当てを設定します](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) | ユーザーおよびグループで **[除外]** オプションを使用します |
| 特定の信頼レベルのデバイスから | [割り当て] - [条件] の下にある &gt; コントロールから設定します | デバイスの状態の条件で **[除外]** オプションを使用し、 **[すべてのデバイス]** を含めます |
| 要求の特定の要求で | この設定を移行することはできません | この設定を移行することはできません |

Microsoft Entra 管理センターで信頼できる場所に対する [除外] オプションを構成する方法の例を次に示します。

[Image: アクセス制御ポリシーのマッピングのスクリーンショット。]

### ユーザーを AD FS から Microsoft Entra ID に移行する

#### Microsoft Entra ID で AD FS グループを同期する

承認規則をマッピングするときは、AD FS で認証を行うアプリで、アクセス許可に Active Directory グループを使用する場合があります。 このような場合は、アプリケーションを移行する前に、[Microsoft Entra Connect](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) を使用してこれらのグループを Microsoft Entra ID と同期します。 アプリケーションが移行されるときに同じユーザーにアクセス権を付与できるよう、移行前にそれらのグループとメンバーシップを確認します。

詳細については、「[Active Directory から同期されたグループ属性を使用する場合の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-group-claims)」を参照してください。

#### ユーザーのセルフプロビジョニングを設定する

一部の SaaS アプリケーションでは、ユーザーが初めてアプリケーションにサインインするときに、ユーザーを Just-In-Time (JIT) プロビジョニングする機能がサポートされています。 Microsoft Entra ID でのアプリ プロビジョニングという用語は、ユーザーがアクセスする必要のあるクラウド ([SaaS](https://azure.microsoft.com/overview/what-is-saas/)) アプリケーションに、ユーザーの ID とロールを自動的に作成することを意味します。 移行されるユーザーは、既に SaaS アプリケーションにアカウントを持っています。 移行後に追加された新しいユーザーはいずれもプロビジョニングする必要があります。 アプリケーションを移行した後、[SaaS アプリのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)をテストします。

#### Microsoft Entra ID で外部ユーザーを同期する

既存の外部ユーザーは、AD FS で、こちらの 2 つの方法によって設定できます。

- **組織内にローカル アカウントを持つ外部ユーザー** — これらのアカウントは、内部ユーザー アカウントの動作と同じ方法で引き続き使用できます。 これらの外部ユーザー アカウントは、組織内にプリンシパル名を持っていますが、アカウントのメール アドレスは外部を指している可能性があります。

移行の進行に合わせて、独自の企業 ID が使用できるときにそれを使用するようにこれらのユーザーを移行することによって、[Microsoft Entra B2B](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b) で提供される利点を利用できます。 これにより、ユーザーが独自の企業サインインでサインインすることが多い場合に、ユーザーのサインイン プロセスが効率化されます。 外部ユーザーのアカウントを管理する必要がなくなり、組織の管理も容易になります。

- **フェデレーション外部 ID**— 現在、外部組織とフェデレーションを行っている場合は、いくつかの方法を使用できます。
    - [Microsoft Entra 管理センターで Microsoft Entra B2B コラボレーション ユーザーを追加します](https://learn.microsoft.com/ja-jp/entra/external-id/add-users-administrator)。 Microsoft Entra 管理ポータルから取引先組織に対し、個々のメンバーが引き続きこれまでと同じアプリと資産を使用するように、B2B コラボレーションの招待を事前に送信できます。
    - B2B 招待 API を使用して取引先組織の個々のユーザーに対する要求を生成する、[セルフサービス B2B サインアップ ワークフローを作成します](https://learn.microsoft.com/ja-jp/entra/external-id/self-service-portal)。

既存の外部ユーザーの構成方法にかかわらず、グループ メンバーシップまたは特定のアクセス許可のいずれかで、外部ユーザーがアカウントに関連付けられたアクセス許可を持っている可能性があります。 これらのアクセス許可を移行またはクリーンアップする必要があるかどうかを評価します。

外部ユーザーを表す組織内のアカウントは、ユーザーが外部 ID に移行された後で、無効にする必要があります。 リソースへの接続が中断される可能性があるため、移行プロセスについてビジネス パートナーと検討する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-adfs-saml-based-sso"} -->
## SAML ベースのシングル サインオン: 構成と制限事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-saml-based-sso
- Service: entra-id / enterprise-apps
- Article date: 2024-08-20
- Summary: ユーザー マッピング、制限事項、SAML 証明書、トークンの暗号化、署名検証、カスタム クレームなど、Microsoft Entra ID で SAML ベースの SSO を構成する際に使用される概念について説明します。

この記事では、Microsoft Entra ID で SAML ベースのシングル サインオン (SSO) をアプリケーションに対して構成する方法について説明します。 ここでは主に、Active Directory Federation Services (ADFS) から Microsoft Entra ID に移行されたアプリへの SAML SSO の構成に焦点を当てます。

取り上げる概念は、ルールに基づいてユーザーを特定のアプリケーション ロールにマッピングする方法や、属性のマッピング時に考慮すべき制限事項などです。 また、SAML 署名証明書、SAML トークンの暗号化、SAML 要求の署名検証、カスタム クレーム プロバイダーについても説明します。

認証に SAML 2.0 を使用するアプリは、[SAML ベースのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) (SSO) 用に構成できます。 SAML ベースの SSO を使用すると、SAML クレームで定義したルールに基づいて、ユーザーを特定のアプリケーション ロールにマッピングできます。

SAML ベースの SSO 用に SaaS アプリケ―ションを構成するには、[クイック スタート: SAML ベースのシングル サインオンを設定する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)ことに関するページを参照してください。

[Image: SAML SSO 設定ペインのスクリーンショット。]

多くの SaaS アプリケ―ションには[アプリケーション固有のチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)が用意されており、SAML ベースの SSO の構成が順を追って説明されています。

一部のアプリは簡単に移行することができます。 カスタム クレームなど、より複雑な要件を持つアプリでは、Microsoft Entra ID や [Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) で追加の構成が必要になる場合があります。 サポートされているクレーム マッピングの詳細については、「[方法: テナントの特定のアプリケーションに対するトークンに出力された要求のカスタマイズ (プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。

属性をマッピングするときは、次の制限事項に注意してください。

- AD FS で発行可能なすべての属性が、SAML トークンに出力する属性として Microsoft Entra ID に表示されるわけではありません (属性が同期されていても同様です)。 属性を編集すると、**[値]** ドロップダウン リストに Microsoft Entra ID で利用可能な属性が表示されます。 必要な属性 (**samAccountName** など) が Microsoft Entra ID に同期されていることを確認するには、[Microsoft Entra Connect Sync に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)で構成を確認してください。 Microsoft Entra ID の標準ユーザー スキーマに含まれていないクレームは、拡張属性を使用して発行できます。
- 最も一般的なシナリオでアプリに必要なのは、**NameID** クレームとその他の一般的なユーザー識別子クレームだけです。 追加のクレームが必要かどうかを判断するには、AD FS からどのクレームを発行しているかを確認してください。
- 一部のクレームは Microsoft Entra ID で保護されているため、発行できません。
- 暗号化された SAML トークンを使用する機能は現在プレビュー段階です。 「[方法: エンタープライズ アプリケーションの SAML トークンで発行されたクレームのカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」をご覧ください。

### サービスとしてのソフトウェア (SaaS) アプリ

ユーザーが Salesforce、ServiceNow、Workday などの SaaS アプリにサインインし、AD FS に統合されている場合は、SaaS アプリに対してフェデレーション サインオンが使用されています。

ほとんどの SaaS アプリケ―ションは Microsoft Entra ID で構成できます。 Microsoft は多数の SaaS アプリに対する事前構成済みの接続を [Microsoft Entra アプリ ギャラリー](https://azuremarketplace.microsoft.com/marketplace/apps/category/azure-active-directory-apps)に用意しているため、移行が容易です。 SAML 2.0 アプリケーションは、Microsoft Entra アプリ ギャラリー経由または[ギャラリー以外のアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)として Microsoft Entra ID に統合できます。

OAuth 2.0 または OpenID Connect を使用するアプリも、[アプリ登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)として同様に Microsoft Entra ID に統合できます。 レガシ プロトコルを使用するアプリは、[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)を使用して Microsoft Entra ID で認証できます。

### SSO 用の SAML 署名証明書

署名証明書は、すべての SSO デプロイの重要な部分です。 Microsoft Entra ID は、SAML ベースのフェデレーション SSO を SaaS アプリケ―ションに対して確立するために署名証明書を作成します。 ギャラリー アプリでもギャラリー以外のアプリでも追加すると、フェデレーション SSO オプションを使用して追加したアプリケーションを構成します。 [Microsoft Entra ID でのフェデレーション シングル サインオンの証明書の管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)に関するページを参照してください。

### SAML トークン暗号化

AD FS と Microsoft Entra ID はどちらも、トークンの暗号化機能を提供しています。これは、アプリケーションに送信される SAML セキュリティ アサーションを暗号化する機能です。 アサーションは公開キーで暗号化され、対応する秘密キーを使用して受信側のアプリケーションによって復号化されます。 トークンの暗号化を構成するときに、X.509 証明書ファイルをアップロードして公開キーを提供します。

Microsoft Entra の SAML トークン暗号化とその構成方法の詳細については、「[Microsoft Entra SAML トークン暗号化の構成方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)」を参照してください。

注

トークン暗号化は、Microsoft Entra ID P1 または P2 の機能です。 Microsoft Entra のエディション、機能、価格の詳細については、[Microsoft Entra の価格](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)に関する記事を参照してください。

### SAML 要求の署名検証

この機能では、署名付き認証要求の署名が検証されます。 アプリ管理者は、署名付き要求の適用を有効または無効にし、検証の実行に使用する公開キーをアップロードします。 詳細については、[カスタム クレーム プロバイダーの概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-enforce-signed-saml-authentication)に関する記事を参照してください。

### カスタム クレーム プロバイダー (プレビュー)

ADFS などのレガシ システムや LDAP などのデータ ストアからデータを移行する場合は、アプリがトークン内の特定のデータに依存していることがあります。 カスタム クレーム プロバイダーを使用して、トークンにクレームを追加できます。 詳細については、[カスタム クレーム プロバイダーの概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-claims-provider-overview)に関する記事を参照してください。

### 今すぐ移動できるアプリと構成

現時点で簡単に移動できるのは、構成要素とクレームの標準セットを使用する SAML 2.0 アプリなどです。 これらの標準項目には、次のものがあります。

- ユーザー プリンシパル名
- メール アドレス
- 指定された名前
- 姓
- SAML **NameID** としての代替属性 (Microsoft Entra ID の mail 属性、メール プレフィックス、従業員 ID、拡張属性 1〜15、オンプレミスの **SamAccountName** 属性など)。 詳細については、「[NameIdentifier クレームの編集](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization)」を参照してください。
- カスタム クレーム。

次のようなアプリケーションは、Microsoft Entra ID に移行する際に追加の構成手順が必要です。

- AD FS におけるカスタム承認ルールや多要素認証 (MFA) ルール。 これらは、[Microsoft Entra の条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)機能を使用して構成します。
- 複数の応答 URL エンドポイントを持つアプリ。 Microsoft Entra ID で PowerShell または Microsoft Entra 管理センターのインターフェイスを使用して構成します。
- SAML バージョン 1.1 のトークンを必要とする SharePoint アプリなどの WS-Federation アプリ。 これらは、PowerShell を使用して手動で構成できます。 また、ギャラリーから SharePoint および SAML 1.1 アプリケーション向けの事前統合済み汎用テンプレートを追加することもできます。 SAML 2.0 プロトコルがサポートされています。
- 複雑なクレーム発行変換規則。 サポートされているクレーム マッピングの詳細については、以下を参照してください。
    - [Microsoft Entra ID におけるクレーム マッピング](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization).
    - [Microsoft Entra ID におけるエンタープライズ アプリケーション向け SAML トークンで発行されるクレームのカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization).

### 現時点ではMicrosoft Entra でサポートされていないアプリと構成

特定の機能を必要とするアプリは、現時点では移行することができません。

#### プロトコル機能

以下のプロトコル機能を必要とするアプリは、現時点では移行することができません。

- WS-Trust ActAs パターンのサポート
- SAML アーティファクト解決

### AD FS から Microsoft Entra ID へのアプリ設定のマッピング

移行では、まずアプリケーションがオンプレミスでどのように構成されているかを評価し、その構成を Microsoft Entra ID にマッピングする必要があります。 AD FS と Microsoft Entra ID は動作が似ているため、信頼関係の構成、サインオンとサインアウトの URL、および識別子の概念は、両方に当てはまります。 Microsoft Entra ID で簡単に構成できるように、アプリケーションの AD FS の構成設定を文書化することをお勧めします。

#### アプリの構成設定のマッピング

次の表では、AD FS 証明書利用者の信頼と Microsoft Entra エンタープライズ アプリケーションの間での、設定の最も一般的なマッピングについて説明します。

- AD FS – アプリに対する AD FS 証明書利用者の信頼で設定を探します。 証明書利用者を右クリックし、[プロパティ] を選択します。
- Microsoft Entra ID – 設定は、[Microsoft Entra 管理センター](https://entra.microsoft.com/#home)内の各アプリケーションの SSO プロパティで構成します。

| 構成設定 | AD FS | Microsoft Entra ID を構成する方法 | SAML トークン |
| --- | --- | --- | --- |
| **アプリのサインオン URL**<br> サービス プロバイダー (SP) によって開始された SAML フローでユーザーがアプリにサインインするための URL。 | 対象外 | SAML ベースのサインオンから [基本的な SAML 構成] を開きます | 対象外 |
| **アプリの応答 URL**<br> ID プロバイダー (IdP) から見たアプリの URL。 IdP は、ユーザーが IdP にサインインした後、ここでユーザーとトークンを送信します。 **SAML アサーション コンシューマー エンドポイント**とも呼ばれます。 | **[エンドポイント]** タブを選択します | SAML ベースのサインオンから [基本的な SAML 構成] を開きます | SAML トークンの Destination 要素。 値の例: `https://contoso.my.salesforce.com` |
| **アプリのサインアウト URL**<br> ユーザーがアプリからサインアウトしたときにサインアウト クリーンアップ要求が送信される URL です。 IdP は、他のすべてのアプリからユーザーをサインアウトさせる要求も送信します。 | **[エンドポイント]** タブを選択します | SAML ベースのサインオンから [基本的な SAML 構成] を開きます | 対象外 |
| **アプリ識別子**<br> IdP の観点からの、アプリの識別子。 多くの場合、サインオン URL 値が識別子に使用されます (そうでない場合もあります)。 ‎アプリではこれを "*エンティティ ID*" と呼ぶこともあります。 | **[識別子]** タブを選択します | SAML ベースのサインオンから [基本的な SAML 構成] を開きます | SAML トークンの **Audience** 要素にマッピングします。 |
| **アプリのフェデレーション メタデータ**<br> アプリのフェデレーション メタデータの場所。 エンドポイントや暗号化証明書などの特定の構成設定を自動更新するために、IdP によって使用されます。 | **[監視]** タブをクリックします | 該当なし。 Microsoft Entra ID では、アプリケーション フェデレーション メタデータの直接の読み込みはサポートしていません。 フェデレーション メタデータは手動でインポートできます。 | 対象外 |
| **ユーザー識別子/名前 ID**<br> Microsoft Entra ID または AD FS のユーザー ID をアプリに一意に示すために使用される属性。 この属性は、通常、ユーザーの UPN またはメール アドレスです。 | クレーム ルール。 ほとんどの場合、クレーム規則では末尾が **NameIdentifier** の型の要求が発行されます。 | この識別子は、ヘッダー **[ユーザー属性とクレーム]** にあります。 既定では、UPN が適用されます。 | SAML トークンの **NameID** 要素にマッピングします。 |
| **その他のクレーム**<br> IdP からアプリによく送信される他のクレーム情報の例としては、名、姓、メール アドレス、グループ メンバーシップなどがあります。 | AD FS では、証明書利用者のその他のクレーム ルールとなっています。 | この識別子は、**[ユーザー属性とクレーム]** のヘッダーの下にあります。 **[表示]** を選択し、他のすべてのユーザー属性を編集します。 | 対象外 |

#### ID プロバイダー (IdP) の設定のマッピング

アプリケーションは、AD FS ではなく Microsoft Entra ID を指すように SSO 用に構成します。 ここでは、SAML プロトコルを使用する SaaS アプリに焦点を当てます。 ただし、この概念はカスタム基幹業務アプリにも及びます。

注

Microsoft Entra ID の構成値は、Azure テナント ID が `{tenant-id}` に、アプリケーション ID が {application-id} に置き換えられるパターンに従います。 これらの情報は、[Microsoft Entra 管理センター](https://entra.microsoft.com/#home)の **[Microsoft Entra ID] &gt; [プロパティ]** で確認できます。

- テナント ID を確認するには [ディレクトリ ID] を選択します。
- アプリケーション ID を確認するには [アプリケーション ID] を選択します。

大まかに言うと、SaaS アプリの次の主要な構成要素は、Microsoft Entra ID に次のようにマッピングされます。

| 要素 | 構成値 |
| --- | --- |
| ID プロバイダーの発行者 | `https://sts.windows.net/{tenant-id}/` |
| ID プロバイダーのサインイン URL | `https://login.microsoftonline.com/{tenant-id}/saml2` |
| ID プロバイダーのサインアウト URL | `https://login.microsoftonline.com/{tenant-id}/saml2` |
| フェデレーション メタデータの場所 | `https://login.windows.net/{tenant-id}/federationmetadata/2007-06/federationmetadata.xml?appid={application-id}` |

### SaaS アプリに対する SSO の設定のマッピング

SaaS アプリでは、認証要求の送信先と、受信したトークンの検証方法がわかっている必要があります。 次の表では、アプリで SSO 設定を構成するための要素と、その値、または AD FS と Microsoft Entra ID 内での場所について説明します

| 構成設定 | AD FS | Microsoft Entra ID を構成する方法 |
| --- | --- | --- |
| **IdP のサインオン URL**<br> アプリから見た IdP のサインオン URL (ユーザーがサインインのためにリダイレクトされる場所)。 | AD FS のサインオン URL は、AD FS フェデレーション サービス名の末尾に `/adfs/ls/` を付加したものです。 <br> 例: `https://fs.contoso.com/adfs/ls/` | `{tenant-id}` を実際のテナント ID に置き換えます。 <br> SAML-P プロトコルを使用するアプリの場合:`https://login.microsoftonline.com/{tenant-id}/saml2`<br><br> ‎WS-Federation プロトコルを使用するアプリの場合: `https://login.microsoftonline.com/{tenant-id}/wsfed` |
| **IdP のサインアウト URL**<br> アプリから見た IdP のサインアウト URL (ユーザーがアプリのサインアウトを選択したときにリダイレクトされる場所)。 | サインアウト URL はサインオン URL と同じであるか、同じ URL に `wa=wsignout1.0` を付加したものです。 例: `https://fs.contoso.com/adfs/ls/?wa=wsignout1.0` | `{tenant-id}` を実際のテナント ID に置き換えます。 <br> SAML-P プロトコルを使用するアプリの場合:<br><br>`https://login.microsoftonline.com/{tenant-id}/saml2`<br><br> ‎WS-Federation プロトコルを使用するアプリの場合: `https://login.microsoftonline.com/common/wsfederation?wa=wsignout1.0` |
| **トークン署名証明書**<br> IdP では、発行されたトークンに署名するために、証明書の秘密キーが使用されます。 アプリが信頼するように構成されているのと同じ IdP からトークンが来ていることを確認します。 | AD FS トークン署名証明書は、[AD FS の管理] の **[証明書]** の下にあります。 | Microsoft Entra 管理者センターの、アプリケーションの **[シングル サインオンのプロパティ]** の **[SAML 署名証明書]** ヘッダーの下にあります。 ここで、アプリにアップロードするための証明書をダウンロードすることができます。 <br> アプリケーションに複数の証明書がある場合は、フェデレーション メタデータ XML ファイルですべての証明書を確認することができます。 |
| **識別子/"発行者"**<br> アプリから見た IdP の識別子 ("発行者 ID" と呼ばれる場合もあります)。 <br><br> SAML トークンでは、値は Issuer 要素として表示されます。 | AD FS の識別子は、通常、**[サービス] &gt; [フェデレーション サービスのプロパティの編集]** の下にある [AD FS の管理] のフェデレーション サービス識別子です。 例: `http://fs.contoso.com/adfs/services/trust` | `{tenant-id}` を実際のテナント ID に置き換えます。 <br>`https://sts.windows.net/{tenant-id}/` |
| **IdP のフェデレーション メタデータ**<br> IdP の一般公開されているフェデレーション メタデータの場所。 (一部のアプリでは、管理者によって個別に構成される URL、識別子、およびトークン署名証明書の代わりに、フェデレーション メタデータを使用します)。 | AD FS フェデレーション メタデータ URL は、[AD FS の管理] にあり、これは **[サービス] &gt; [エンドポイント] &gt; [メタデータ] &gt; [種類: フェデレーション メタデータ]** で確認できます。 例: `https://fs.contoso.com/FederationMetadata/2007-06/FederationMetadata.xml` | Microsoft Entra ID での対応する値は、`https://login.microsoftonline.com/{TenantDomainName}/FederationMetadata/2007-06/FederationMetadata.xml` というパターンに従います。 {TenantDomainName} を、`contoso.onmicrosoft.com` の形式のテナント名に置き換えてください。 <br> 詳細については、「[フェデレーション メタデータ](https://learn.microsoft.com/ja-jp/entra/identity-platform/federation-metadata)」を参照してください。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-applications-from-okta"} -->
## アプリケーションを Okta から Microsoft Entra ID に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-applications-from-okta
- Service: entra-id / enterprise-apps
- Article date: 2024-12-06
- Summary: SAML、OpenID Connect、OAuth 2.0 の構成をカバーする、Okta から Microsoft Entra ID にアプリケーションを移行するプロセスについて説明します。

このチュートリアルでは、Okta から Microsoft Entra ID にアプリケーションを移行する方法について説明します。

### 前提条件

Microsoft Entra ID でアプリケーションを管理するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者、またはサービス プリンシパルの所有者。

### 現在の Okta アプリケーションのインベントリを作成する

移行の前に、現在の環境とアプリケーション設定を文書化します。 この情報は、Okta API を使用して収集できます。 [Postman](https://www.postman.com/) などの API エクスプローラー ツールを使用します。

アプリケーション インベントリを作成するには:

1. Postman アプリを使用して、Okta 管理コンソールから API トークンを生成します。
2. API ダッシュボードの [**セキュリティ**] で、[**トークン**] &gt; [**トークンの作成**] を選択します。

    [Image: [セキュリティ] の [トークン] オプションと [トークンの作成] オプションのスクリーンショット。]
3. トークン名を入力し、[ **トークンの作成**] を選択します。

    [Image: [トークンの作成] の [名前] エントリのスクリーンショット。]
4. トークン値を記録し、それを保存します。 **[OK] を選択して取得**した後は、アクセスできません。

    [Image: [Token Value](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/トークン値) フィールドと [OK got it](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/OK) オプションのスクリーンショット。]
5. Postman アプリのワークスペースで、[インポート] を選択 **します**。
6. [ **インポート** ] ページで、[ **リンク**] を選択します。 API をインポートするには、次のリンクを挿入します。

`https://developer.okta.com/docs/api/postman/example.oktapreview.com.environment`

[Image: [インポート] の [リンクと続行] オプションのスクリーンショット。]

Note

テナント値でリンクを変更しないでください。

1. [ **インポート] を選択します**。

    [Image: [インポート] の [インポート] オプションのスクリーンショット。]
2. API がインポートされたら、 **環境** の選択を **{yourOktaDomain}** に変更します。
3. Okta 環境を編集するには、 **目** のアイコンを選択します。 次に、[ **編集]** を選択します。

    [Image: [概要] の目のアイコンと [編集] オプションのスクリーンショット。]
4. [ **初期値]** フィールドと [ **現在の値]** フィールドで、URL と API キーの値を更新します。 環境が反映されるように名前を変更します。
5. これらの値を保存します。

    [Image: [概要] の [初期値] フィールドと [現在の値] フィールドのスクリーンショット。]
6. [Postman に API を読み込みます](https://app.getpostman.com/run-collection/377eaf77fdbeaedced17)。
7. ［**アプリ**&gt;**リスト取得アプリ**&gt;を選択し、**送信**］します。

Note

Okta テナント内のアプリケーションを出力できます。 この一覧は、JSON 形式になっています。

[Image: [送信] オプションと [アプリ] リストのスクリーンショット。]

この JSON 一覧をコピーし、CSV 形式に変換することをお勧めします。

- [Konklone](https://konklone.io/json/) などのパブリック コンバーターを使用する
- または PowerShell の場合は、[ConvertFrom-Json](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.utility/convertfrom-json) と [ConvertTo-CSV](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.utility/convertto-csv) を使用します

Note

Okta テナント内のアプリケーションのレコードを入手するには、この CSV をダウンロードします。

### SAML アプリケーションを Microsoft Entra ID に移行する

SAML 2.0 アプリケーションを Microsoft Entra ID に移行するには、Microsoft Entra テナントでアプリケーション アクセス用にアプリケーションを構成します。 この例では、Salesforce インスタンスを変換します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**すべてのアプリケーション**を参照し、[**新しいアプリケーション**] を選択します。
3. **Microsoft Entra ギャラリー**で **Salesforce** を検索し、アプリケーションを選択して、[**作成**] を選択します。

    [Image: Microsoft Entra ギャラリー内のアプリケーションのスクリーンショット。]
4. アプリケーションが作成されたら、[ **シングル サインオン (SSO)] タブで** [ **SAML**] を選択します。

    [Image: シングル サインオンの SAML オプションのスクリーンショット。]
5. **証明書 (未加工)** と**フェデレーション メタデータ XML** をダウンロードして Salesforce にインポートします。
6. Salesforce 管理コンソールで、[ **アイデンティティ**&gt;**Single Sign-On 設定**&gt;**メタデータ ファイルから新規作成** を選択します。

    [Image: [シングル サインオン設定] の [メタデータ ファイルから新規作成] オプションのスクリーンショット。]
7. Microsoft Entra 管理センターからダウンロードした XML ファイルをアップロードします。 次に、[ **作成**] を選択します。
8. Azure からダウンロードした証明書をアップロードします。 **[保存] を選択します**。
9. 次のフィールドの値を記録します。 値は Azure にあります。

    - **エンティティ ID**
    - **ログイン URL**
    - **ログアウト URL**
10. [ **メタデータのダウンロード**] を選択します。
11. Microsoft Entra 管理センターにファイルをアップロードするには、Microsoft Entra ID **Enterprise アプリケーション** ページの SAML SSO 設定で、[ **メタデータ ファイルのアップロード**] を選択します。
12. インポートされた値が記録された値と一致することを確認します。 **[保存] を選択します**。

    [Image: SAML ベースのサインオンと基本的な SAML 構成のエントリのスクリーンショット。]
13. Salesforce 管理コンソールで、[**会社の設定]**&gt;**[マイ ドメイン]** を選択します。 **[認証構成]** に移動し、[編集] を選択**します**。

    [Image: [マイ ドメイン] の [編集] オプションのスクリーンショット。]
14. サインイン オプションで、構成した新しい SAML プロバイダーを選択します。 **[保存] を選択します**。

    [Image: [認証構成] の [認証サービス] オプションのスクリーンショット。]
15. Microsoft Entra ID の **[エンタープライズ アプリケーション** ] ページで、[ **ユーザーとグループ**] を選択します。 次に、テスト ユーザーを追加します。

    [Image: テスト ユーザーの一覧を含むユーザーとグループのスクリーンショット。]
16. 構成をテストするには、テスト ユーザーとしてサインインします。 Microsoft [アプリ ギャラリー](https://aka.ms/myapps) に移動し、 **Salesforce** を選択します。

    [Image: [マイ アプリ] の [すべてのアプリ] の [Salesforce] オプションのスクリーンショット。]
17. サインインするには、構成された ID プロバイダー (IdP) を選択します。

    [Image: Salesforce サインイン ページのスクリーンショット。]

Note

構成が正しい場合、テスト ユーザーは Salesforce ホーム ページに移動します。 トラブルシューティングのヘルプについては、 [デバッグ ガイドを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/debug-saml-sso-issues)参照してください。

1. [ **エンタープライズ アプリケーション** ] ページで、残りのユーザーを適切なロールで Salesforce アプリケーションに割り当てます。

Note

残りのユーザーを Microsoft Entra アプリケーションに追加したら、これらのユーザーは接続をテストして、アクセス権があることを確認することができます。 次の手順の前に、接続をテストしてください。

1. Salesforce 管理コンソールで、[**会社の設定]**&gt; [**マイ ドメイン]** を選択します。
2. [ **認証構成]** で [編集] を選択 **します**。 認証サービスの場合は、 **Okta** の選択を解除します。

    [Image: [認証構成] の [保存] オプションと [認証サービス] オプションのスクリーンショット。]

### OpenID Connect または OAuth 2.0 アプリケーションを Microsoft Entra ID に移行する

OpenID Connect (OIDC) または OAuth 2.0 アプリケーションを Microsoft Entra ID に移行するには、Microsoft Entra テナントでアプリケーションをアクセス用に構成します。 この例では、カスタム OIDC アプリを変換します。

移行を完了するには、Okta テナント内のすべてのアプリケーションに対して次の構成を繰り返します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
3. [ **新しいアプリケーション]** を選択します。
4. [独自のアプリケーション 作成] を選択します。
5. 表示されるメニューで、OIDC アプリに名前を付け、[ **作業中のアプリケーションの登録] を選択して Microsoft Entra ID と統合**します。
6. **作成** を選択します。
7. 次のページで、アプリケーション登録のテナントを設定します。 詳細については、[Microsoft Entra ID のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps)に関する説明を参照してください。 **[任意の組織ディレクトリ内のアカウント (任意の Microsoft Entra ディレクトリ - マルチテナント)] **&gt;** [登録]** に移動します。

    [Image: 任意の組織ディレクトリ (任意の Microsoft Entra ディレクトリ - マルチテナント) の [アカウント] オプションのスクリーンショット。]
8. [ **アプリの登録** ] ページの **[Microsoft Entra ID**] で、作成した登録を開きます。

Note

[アプリケーションのシナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-flows-app-scenarios)に応じて、さまざまな構成アクションがあります。 ほとんどのシナリオでは、アプリ クライアント シークレットが必要です。

1. [ **概要** ] ページで、 **アプリケーション (クライアント) ID を記録します**。 この ID は、アプリケーションで使用します。
2. 左側で、[ **証明書とシークレット**] を選択します。 次に、[ **+ 新しいクライアント シークレット**] を選択します。 クライアント シークレットに名前を付け、その有効期限を設定します。
3. シークレットの値と ID を記録します。

Note

クライアント シークレットを失った場合、それを取得することはできません。 代わりに、シークレットを再生成します。

1. 左側で、 **API のアクセス許可**を選択します。 次に、そのアプリケーションに OIDC スタックへのアクセス権を付与します。
2. **+ アクセス許可の追加**&gt;**Microsoft Graph**&gt;**委任されたアクセス許可**を選択します。
3. [ **OpenId のアクセス許可** ] セクションで、 **電子メール**、 **openid**、プロファイルを選択 **します**。 次に、[ **アクセス許可の追加]** を選択します。
4. ユーザー エクスペリエンスを向上させ、ユーザーの同意プロンプトを抑制するには、[ **テナント ドメイン名に管理者の同意を付与**する] を選択します。 **[付与済み**] 状態が表示されるまで待ちます。

    [Image: [API アクセス許可] の [要求されたアクセス許可に対して管理者の同意が正常に付与されました] メッセージのスクリーンショット。]
5. アプリケーションにリダイレクト URI がある場合は、URI を入力します。 応答 URL が **[認証**] タブを対象とし、[プラットフォームと **Web** の**追加]** が続く場合は、URL を入力します。
6. **アクセス トークン**と **ID トークンを選択します**。
7. **設定**を選択します。
8. 必要に応じて、[ **認証** ] メニューの [ **詳細設定]** と [ **パブリック クライアント フローを許可する**] で 、[ **はい**] を選択します。

    [Image: [認証] の [はい] オプションのスクリーンショット。]
9. テストする前に、OIDC で構成されたアプリケーションで、アプリケーション ID とクライアント シークレットをインポートします。

Note

前の手順を使用して、そのアプリケーションをクライアント ID、シークレット、スコープなどの設定で構成します。

### カスタム承認サーバーを Microsoft Entra ID に移行する

Okta 承認サーバーは、 [API を公開](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis#add-a-scope)するアプリケーション登録に 1 対 1 でマップします。

既定の Okta 認可サーバーを Microsoft Graph のスコープまたはアクセス許可にマップします。

[Image: [公開と API] の [スコープの追加] オプションのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-applications-from-secrets"} -->
## シークレット ベースの認証からアプリケーションを移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-applications-from-secrets
- Service: entra-id / enterprise-apps
- Article date: 2025-03-19
- Summary: セキュリティとユーザー エクスペリエンスを向上させるために、シークレット ベースの認証からアプリケーションを移行します。

クライアント シークレットを使用するアプリケーションは、それらを構成ファイルに格納したり、スクリプトにハードコーディングしたり、他の方法で公開を危険にさらしたりする可能性があります。 シークレット管理の複雑さにより、シークレットはリークの影響を受けやすく、攻撃者にとって魅力的になります。 クライアント シークレットは、公開されると、攻撃者に正当な資格情報を提供して、アクティビティと正当な操作を組み合わせて、セキュリティ制御をバイパスしやすくします。 攻撃者がアプリケーションのクライアント シークレットを侵害した場合、システム内の特権をエスカレートし、アプリケーションのアクセス許可に応じて、より広範なアクセスと制御を行うことができます。 侵害された証明書を置き換えると、非常に時間がかかり、混乱を招く可能性があります。 このような理由から、Microsoft では、すべての顧客がパスワードまたは証明書ベースの認証からトークン ベースの認証に移行することをお勧めします。

この記事では、シークレットベースの認証から、より安全で使いやすい認証方法にアプリケーションを移行するのに役立つリソースとベスト プラクティスについて説明します。

### シークレット ベースの認証からアプリケーションを移行する理由

シークレット ベースの認証からアプリケーションを移行すると、いくつかの利点があります。

- **セキュリティの強化**: シークレットベースの認証は、リークや攻撃の影響を受けやすくなります。 マネージド ID などのより安全な認証方法に移行すると、セキュリティが向上します。
- **複雑さの軽減**: シークレットの管理は複雑でエラーが発生しやすい場合があります。 より安全な認証方法に移行すると、複雑さが軽減され、セキュリティが向上します。
- **スケーラビリティ**: より安全な認証方法に移行すると、アプリケーションを安全にスケーリングできます。
- **コンプライアンス**: より安全な認証方法への移行は、コンプライアンス要件とセキュリティのベスト プラクティスを満たすのに役立ちます。

### シークレット ベースの認証からアプリケーションを移行するためのベスト プラクティス

アプリケーションをシークレット ベースの認証から移行するには、次のベスト プラクティスを検討してください。

#### Azure リソースにマネージド ID を使用する

マネージド ID は、資格情報を管理したり、コードに資格情報を含めたりすることなく、クラウド サービスに対してアプリケーションを認証するための安全な方法です。 Azure サービスでは、この ID を使用して、Microsoft Entra 認証をサポートするサービスに対する認証を行います。 詳細については、「 [マネージド ID アクセスをアプリケーション ロールに割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/how-to-assign-app-role-managed-identity)参照してください。

短期間で移行できないアプリケーションの場合は、シークレットをローテーションし、Azure Key Vault の使用などのセキュリティで保護されたプラクティスを確実に使用します。 Azure Key Vault は、クラウド アプリケーションとサービスで使用される暗号化キーとシークレットを保護するのに役立ちます。 キー、シークレット、証明書は、コードを自分で記述しなくても保護され、アプリケーションから簡単に使用できます。 詳細については、 [Azure Key Vault](https://learn.microsoft.com/ja-jp/azure/key-vault/general/developers-guide) に関するページを参照してください。

#### ワークロード ID の条件付きアクセス ポリシーをデプロイする

ワークロード ID の条件付きアクセスを使用すると、Microsoft Entra Protection によって検出されたリスクに基づいて、または認証コンテキストと組み合わせて、既知のパブリック IP 範囲の外部からサービス プリンシパルをブロックできます。 詳細については、「 [ワークロード ID の条件付きアクセス」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity)参照してください。

重要

サービス プリンシパルをスコープとする条件付きアクセス ポリシーを作成または変更するには、ワークロード ID Premium ライセンスが必要です。 適切なライセンスがないディレクトリでは、ワークロード ID の既存の条件付きアクセス ポリシーは引き続き機能しますが、変更することはできません。 詳細については、「 [Microsoft Entra ワークロード ID」を](https://www.microsoft.com/security/business/identity-access/microsoft-entra-workload-identities#office-StandaloneSKU-k3hubfz)参照してください。

#### シークレット スキャンを実装する

リポジトリのシークレット スキャンでは、履歴とプッシュ保護の間でソース コードに既に存在する可能性があるシークレットがチェックされるため、新しいシークレットがソース コードで公開されなくなります。 詳細については、「 [シークレットスキャン](https://learn.microsoft.com/ja-jp/azure/devops/repos/security/github-advanced-security-secret-scanning)」を参照してください。

#### アプリケーション認証ポリシーを展開して、セキュリティで保護された認証プラクティスを適用する

アプリケーション管理ポリシーを使用すると、IT 管理者は、組織のアプリの構成方法に関するベスト プラクティスを適用できます。 たとえば、管理者は、パスワード シークレットの使用をブロックしたり、有効期間を制限したりするポリシーを構成できます。 詳細については、「 [チュートリアル: アプリケーション管理ポリシーと Microsoft Entra アプリケーション管理ポリシー API の概要を使用してシークレットと証明書の標準を適用](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-enforce-secret-standards) する」を参照 [してください](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy)。

重要

Premium ライセンスは、アプリケーション認証ポリシー管理を実装するために必要です。詳細については、Microsoft Entra ライセンス参照してください。

#### サービス アカウントにフェデレーション ID を使用する

ID フェデレーションを使用すると、フェデレーション ID 資格情報を構成することで、外部 ID プロバイダー (IdP) と Microsoft Entra ID 内のアプリの間に信頼関係を作成することで、シークレット (サポートされているシナリオの場合) を管理しなくても、Microsoft Entra で保護されたリソースにアクセスできます。 詳細については、「 [Microsoft Entra ID のフェデレーション ID 資格情報の概要」を](https://learn.microsoft.com/ja-jp/graph/api/resources/federatedidentitycredentials-overview)参照してください。

#### 最小限の特権を持つカスタム ロールを作成してアプリケーションの資格情報をローテーションする

Microsoft Entra ロールを使用すると、最小特権の原則に従って、管理者に詳細なアクセス許可を付与できます。 カスタム ロールを作成してアプリケーションの資格情報をローテーションし、タスクを完了するために必要なアクセス許可のみが付与されるようにすることができます。 詳細については、「 [Microsoft Entra ID でカスタム ロールを作成する」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)参照してください。

#### アプリケーションをトリアージして監視するプロセスがあることを確認する

このプロセスには、定期的なセキュリティ評価、脆弱性スキャン、インシデント対応手順が含まれている必要があります。 アプリケーションのセキュリティ体制を認識することは、セキュリティで保護された環境を維持するために不可欠です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-okta-federation"} -->
## Okta フェデレーションを Microsoft Entra 認証に移行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-okta-federation
- Service: entra-id / enterprise-apps
- Article date: 2024-12-06
- Summary: Okta フェデレーション アプリケーションを Microsoft Entra ID の下のマネージド認証に移行します。 段階的な方法でフェデレーションを移行する方法を確認してください。

このチュートリアルでは、Office 365 テナントを Okta for Single sign-on (SSO) とフェデレーションする方法について学習します。

ユーザーの良好な認証エクスペリエンスを確保するためにフェデレーションを Microsoft Entra ID に段階的に移行することができます。 段階的な移行では、残りの Okta SSO アプリケーションへの逆フェデレーション アクセスをテストすることができます。

注

このチュートリアルで説明するシナリオは、移行の実装が可能な方法の 1 つにすぎません。 情報を特定の設定に合わせる必要があります。

### 前提条件

- Okta for SSO にフェデレーションされた Office 365 テナント
- Microsoft Entra ID へのユーザー プロビジョニング用に構成された Microsoft Entra Connect サーバーまたは Microsoft Entra Connect クラウド プロビジョニング エージェント
- 次のいずれかのロール: アプリケーション管理者、クラウド アプリケーション管理者、またはハイブリッド ID 管理者。

### Microsoft Entra Connect を認証用に構成する

Okta を使用して自身の Office 365 ドメインのフェデレーションを行っているお客様は、Microsoft Entra ID の有効な認証方法を持っていない場合があります。 マネージド認証に移行する前に、Microsoft Entra Connect を検証し、ユーザー サインイン用に構成します。

次のようにサインイン方法を設定します。

- **パスワード ハッシュ同期**- Microsoft Entra Connect サーバーまたはクラウド プロビジョニング エージェントによって実装されるディレクトリ同期機能の拡張機能
    - この機能を使用して Microsoft 365 などの Microsoft Entra サービスにサインインする
    - オンプレミスの Active Directory インスタンスにサインインするときのパスワードを使って、このサービスにサインインします。
    - 「[Microsoft Entra ID を使用したパスワード ハッシュ同期とは?](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs)」を参照してください
- **パススルー認証**- 同じパスワードを使ってオンプレミスとクラウド アプリケーションにサインインします
    - ユーザーが Microsoft Entra ID を使用してサインインすると、パススルー認証エージェントがオンプレミスの AD に対してパスワードの検証を行います
    - 「[Microsoft Entra パススルー認証を使用したユーザー サインイン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)」を参照してください
- **シームレス SSO**- 会社のネットワークに接続された会社のデスクトップ上のユーザーにサインインします
    - ユーザーは、他のオンプレミス コンポーネントを使わずにクラウド アプリケーションにアクセスできます。
    - 「[Microsoft Entra シームレス SSO](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)」を参照してください

Microsoft Entra ID でシームレス認証のユーザー エクスペリエンスを作成するには、シームレス SSO をパスワード ハッシュ同期またはパススルー認証にデプロイします。

シームレス SSO の前提条件については、「[クイックスタート: Microsoft Entra シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start#step-1-check-the-prerequisites)」を参照してください。

このチュートリアルでは、パスワード ハッシュ同期とシームレス SSO を構成します。

#### パスワード ハッシュ同期およびシームレス SSO 用に Microsoft Entra Connect を構成する

1. Microsoft Entra Connect サーバーで、**Microsoft Entra Connect** アプリを開きます。
2. **[構成]** をクリックします。
3. **[ユーザー サインインの変更]** を選びます。
4. [**次へ**] を選択します。
5. Microsoft Entra Connect サーバーのハイブリッド ID 管理者の資格情報を入力します。
6. サーバーは Okta とのフェデレーション用に構成されています。 選択内容を **[パスワード ハッシュ同期]** に変更します。
7. **[シングル サインオンを有効にする]** を選択します。
8. [**次へ**] を選択します。
9. ローカル オンプレミス システムの場合は、ドメイン管理者の資格情報を入力します。
10. [**次へ**] を選択します。
11. 最後のページで **[構成]** を選びます。
12. Microsoft Entra Hybrid Join の警告を無視します。

### 段階的ロールアウト機能を構成する

ドメインのフェデレーション解除をテストする前に、Microsoft Entra ID でクラウド認証の段階的ロールアウトを使用して、ユーザーのフェデレーション解除をテストします。

詳細情報: [段階的なロールアウトを使用してクラウド認証に移行する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-staged-rollout)

Microsoft Entra Connect サーバーでパスワード ハッシュ同期とシームレス SSO を有効にしてから、以下のように段階的ロールアウトを構成します。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Entra Connect**&gt;**Connect Sync** に移動します。
3. テナントで **[パスワード ハッシュの同期]** が有効になっていることを確認します。
4. **[マネージド ユーザー サインインの段階的ロールアウトを有効にする]** を選択します。
5. サーバーを構成したら、**[パスワード ハッシュの同期]** 設定を **[オン]** に変更できます。
6. 設定を有効にします。
7. **[シームレス シングル サインオン]** は **[オフ]** です。 これを有効にすると、テナントで有効にしたためにエラーが表示されます。
8. **[グループの管理]** を選択します。

    [Image: Microsoft Entra 管理センターの [段階的なロールアウト機能の有効化] ページのスクリーンショット。[グループの管理] ボタンが表示されます。]
9. パスワード ハッシュ同期のロールアウトにグループを追加します。
10. テナントで機能が有効になるまで約 30 分間待ちます。
11. この機能が有効になると、ユーザーが Office 365 サービスにアクセスしようとしたときに Okta にリダイレクトされなくなります。

段階的ロールアウト機能にはサポートされないシナリオがいくつかあります。

- Post Office Protocol 3 (POP3) や Simple Mail Transfer Protocol (SMTP) などのレガシ認証プロトコルはサポートされていません。
- Okta に対して Microsoft Entra Hybrid Join を構成した場合、ドメインのフェデレーションが解除されるまで、Microsoft Entra Hybrid Join フローは Okta に流れます。
    - Microsoft Entra Hybrid Join の Windows クライアントのレガシ認証のために、サインオン ポリシーは Okta に残ります。

### Microsoft Entra ID で Okta アプリを作成する

マネージド認証に変換したユーザーには、Okta 内のアプリケーションへのアクセスが必要な場合があります。 そのようなアプリケーションへのユーザー アクセスのために、Okta ホーム ページにリンクされる Microsoft Entra アプリケーションを登録します。

Okta 用のエンタープライズ アプリケーションの登録を構成します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。

    [Image: Microsoft Entra 管理センターの左側のメニューのスクリーンショット。]
3. **[新しいアプリケーション]** を選択します。

    [Image: Microsoft Entra 管理センターの [すべてのアプリケーション] ページを示すスクリーンショット。新しいアプリケーションが表示されています。]
4. **[独自のアプリケーションの作成]** を選択します。
5. このメニューで Okta アプリに名前を付けます。
6. **[操作中のアプリケーションを登録して Microsoft Entra ID と統合する]** を選択します。
7. **［作成］** を選択します
8. **[任意の組織ディレクトリ (任意の Microsoft Entra ディレクトリ - マルチテナント) 内のアカウント]** を選択します。
9. **登録** を選択します。

    [Image: アプリケーションの登録のスクリーンショット。]
10. Microsoft Entra ID メニューで、**[アプリの登録]** を選択します。
11. 作成した登録を開きます。

[Image: Microsoft Entra 管理センターの [アプリの登録] ページのスクリーンショット。新しいアプリの登録が表示されます。]

1. テナント ID とアプリケーション ID をメモします。

注

Okta で ID プロバイダーを構成するために、そのテナント ID とアプリケーション ID が必要になります。

[Image: Microsoft Entra 管理センターの [Okta アプリケーション アクセス] ページのスクリーンショット。テナント ID とアプリケーション ID が表示されます。]

1. 左側のメニューで **[証明書とシークレット]** を選択します。
2. **[新しいクライアント シークレット]** を選択します。
3. シークレット名を入力します。
4. その有効期限を入力します。
5. シークレットの値と ID をメモします。

注

この値と ID は後で表示されません。 この情報をメモしていない場合は、シークレットを再生成する必要があります。

1. 左側のメニューで、 **[API のアクセス許可]** を選択します。
2. OpenID Connect (OIDC) スタックへのアクセス許可をアプリケーションに付与します。
3. **[アクセス許可の追加]** を選択します。
4. **[Microsoft Graph]** を選びます。
5. **[委任されたアクセス許可]** を選択します。
6. [OpenID のアクセス許可] セクションで、**メール**、**OpenID**、**プロファイル**を追加します。
7. **[アクセス許可の追加]** を選択します.
8. **[&lt;テナント ドメイン名&gt;に管理者の同意を与えます]** を選びます。
9. **[許可]** 状態が表示されるまで待ちます。

    [Image: [API のアクセス許可] ページのスクリーンショット。同意の付与に関するメッセージが表示されています。]
10. 左側のメニューで、 **[ブランド]** を選択します。
11. **[ホーム ページ URL]** で、ユーザーのアプリケーション ホーム ページを追加します。
12. Okta 管理ポータルで新しい ID プロバイダーを追加するには、**[Security] (セキュリティ)**、**[Identity Providers] (ID プロバイダー)** の順に選びます。
13. **[Add Microsoft](Microsoft の追加)** を選択します。

    [Image: Okta 管理ポータルのスクリーンショット。[Add Identity Provider] (ID プロバイダーの追加) リストに [Add Microsoft] (Microsoft の追加) が表示されています。]
14. **[Identity Providers] (ID プロバイダー)** ページの **[Client ID] (クライアント ID)** フィールドにアプリケーション ID を入力します。
15. **[Client Secret] (クライアント シークレット)** フィールドにクライアント シークレットを入力します。
16. **[詳細設定の表示]** を選択します。 既定では、この構成は逆フェデレーション アクセスのために Okta のユーザー プリンシパル名 (UPN) を Microsoft Entra ID の UPN に結び付けます。

    重要

    Okta と Microsoft Entra ID の UPN が一致しない場合は、ユーザー間で共通の属性を選択します。
17. 自動プロビジョニングの選択を完了します。
18. 既定では、Okta ユーザーに一致するものが見つからない場合、システムは Microsoft Entra ID のユーザーのプロビジョニングを試行します。 Okta からプロビジョニングを移行した場合は、**[Redirect to Okta sign-in page] (Okta サインイン ページにリダイレクトする)** を選びます。

    [Image: Okta 管理ポータルの [General Settings] (全般設定) ページのスクリーンショット。Okta サインイン ページにリダイレクトするためのオプションが表示されています。]

ID プロバイダー (IDP) を作成しました。 ユーザーを正しい IDP に送信します。

1. **[Identity Providers] (ID プロバイダー)** メニューで、**[Routing Rules] (ルーティング規則)**、**[Add Routing Rule] (ルーティング規則の追加)** の順に選びます。
2. Okta プロファイルで使用できる属性のいずれかを使用します。
3. デバイスと IP からのサインインを Microsoft Entra ID に転送するには、次の画像のようにポリシーを設定します。 この例では、どの Okta プロファイルでも **[Division] (ディビジョン)** 属性は使われていません。 これは、IDP ルーティングの場合に適しています。
4. アプリケーションの登録に追加するリダイレクト URI をメモします。

    [Image: リダイレクト URI の位置を示すスクリーンショット。]
5. アプリケーションの登録で、左側のメニューの **[認証]** を選びます。
6. **[プラットフォームを追加]** を選びます
7. **[Web]** を選択します。
8. Okta の IDP でメモしたリダイレクト URI を追加します。
9. **[アクセス トークン]** と **[ID トークン]** を選択します。
10. 管理コンソールで **[ディレクトリ]** を選びます。
11. **[ユーザー]** を選びます。
12. プロファイルを編集するには、テスト ユーザーを選択します。
13. プロファイルに **ToAzureAD** を追加します。 次の図を参照してください。
14. **[保存]** を選択します。

    [Image: Okta 管理ポータルのスクリーンショット。プロファイル設定が表示され、[Division] (ディビジョン) ボックスには ToAzureAD が表示されています。]
15. 変更されたユーザーとして [Microsoft 356 ポータル](https://portal.office.com)にサインインします。 ユーザーがマネージド認証パイロットに含まれない場合、アクションがループに入ります。 ループを終了するには、マネージド認証エクスペリエンスにユーザーを追加します。

### パイロット メンバーでの Okta アプリのアクセスをテストする

Microsoft Entra ID で Okta アプリを構成し、Okta ポータルで IDP を構成したら、アプリケーションをユーザーに割り当てます。

1. Microsoft Entra 管理センターで、 **Entra ID**&gt;**Enterprise アプリ**を参照します。
2. 作成したアプリの登録を選びます。
3. **[ユーザーとグループ]** に移動します。
4. マネージド認証パイロットと関連するグループを追加します。

    注

    ユーザーとグループは **[エンタープライズ アプリケーション]** ページから追加できます。 **[アプリの登録]** メニューからユーザーを追加することはできません。

    [Image: Microsoft Entra 管理センターの [ユーザーとグループ] ページのスクリーンショット。マネージド認証ステージング グループというグループが表示されます。]
5. 15 分ほど待ちます。
6. マネージド認証パイロット ユーザーとしてサインインします。
7. [\[マイ アプリ\]](https://myapplications.microsoft.com) に移動します。

    [Image: [マイ アプリ] ギャラリーのスクリーンショット。[Okta Application Access] (Okta アプリケーション アクセス) アイコンが表示されています。]
8. Okta ホーム ページに戻るには、**[Okta Application Access] (Okta アプリケーション アクセス)** タイルを選びます。

### パイロット メンバーでマネージド認証をテストする

Okta 逆フェデレーション アプリを構成したら、マネージド認証エクスペリエンスに対してテストを実行するようにユーザーに依頼します。 ユーザーがテナントを認識できるように、会社のブランドを構成することをお勧めします。

詳細情報: [会社のブランドを構成する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)。

重要

Okta からドメインのフェデレーションを解除する前に、必要な条件付きアクセス ポリシーを特定します。 切断する前に環境をセキュリティで保護できます。 「[チュートリアル: Okta サインオン ポリシーを Microsoft Entra 条件付きアクセスに移行する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-okta-sign-on-policies-conditional-access)」を参照してください。

### Office 365 ドメインのフェデレーションを解除する

組織がマネージド認証エクスペリエンスに慣れたら、Okta からドメインのフェデレーションを解除することができます。 まず、次のコマンドを使用して Microsoft Graph PowerShell に接続します。 Microsoft Graph PowerShell モジュールがない場合は、「`Install-Module Microsoft.Graph`」と入力してダウンロードしてください。

1. PowerShell で、ハイブリッド ID 管理者アカウントを使用して Microsoft Entra ID にサインインします。

    ```powershell
     Connect-MgGraph -Scopes "Domain.ReadWrite.All", "Directory.AccessAsUser.All"
    ```
2. ドメインを変換するには、次のコマンドを実行します。

    ```powershell
     Update-MgDomain -DomainId contoso.com -AuthenticationType "Managed"
    ```
3. 次のコマンドを実行して、ドメインがマネージドに変換されていることを確認します。 認証の種類が "マネージド" に設定されているはずです。

    ```powershell
    Get-MgDomain -DomainId contoso.com
    ```

ドメインをマネージド認証に設定した後、Okta ホームページへのユーザーアクセスを維持しながら、Office 365 テナントを Okta からフェデレーションを解除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-okta-sign-on-policies-conditional-access"} -->
## Okta サインオン ポリシーを Microsoft Entra 条件付きアクセスに移行するためのチュートリアル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-okta-sign-on-policies-conditional-access
- Service: entra-id / enterprise-apps
- Article date: 2023-01-13
- Summary: Okta サインオン ポリシーを Microsoft Entra 条件付きアクセスに移行する方法を説明します。

このチュートリアルでは、組織を Okta のグローバルまたはアプリケーションレベルのサインオン ポリシーから Microsoft Entra ID の条件付きアクセスに移行する方法を説明します。 条件付きアクセス ポリシーによって、Microsoft Entra ID および接続されたアプリケーションのユーザー アクセスがセキュリティで保護されます。

詳細情報: [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)

このチュートリアルでは、以下があることを前提としています。

- サインインと多要素認証のために Okta にフェデレーションされている Office 365 テナント
- Microsoft Entra ID へのユーザー プロビジョニング用に構成された Microsoft Entra Connect サーバーまたは Microsoft Entra Connect クラウド プロビジョニング エージェント

### 前提条件

ライセンスと資格情報の前提条件については、次の 2 つのセクションを参照してください。

#### ライセンス

Okta のサインオンから条件付きアクセスに切り替える場合、ライセンス要件があります。 このプロセスでは、Microsoft Entra 多要素認証の登録を有効にするために、Microsoft Entra ID P1 ライセンスが必要です。

詳細情報: [Microsoft Entra 管理センターでライセンスを割り当てるまたは削除](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)する

#### エンタープライズ管理者の資格情報

サービス接続ポイント (SCP) レコードを構成するには、オンプレミス フォレストにエンタープライズ管理者の資格情報があることを確認します。

### 移行のために Okta のサインオン ポリシーを評価する

Okta のサインオン ポリシーを見つけて評価し、Microsoft Entra ID に移行するものを決定します。

1. Okta で、[ **セキュリティ**&gt;**認証**&gt;**サインオン**] に移動します。

    [Image: [認証] ページのグローバル MFA サインオン ポリシー エントリのスクリーンショット。]
2. **[アプリケーション**] に移動します。
3. サブメニューから[アプリケーション]を選択 **します。**
4. **[アクティブなアプリ] の一覧**から、Microsoft Office 365 接続インスタンスを選択します。

    [Image: Microsoft Office 365 の [サインオン] の設定のスクリーンショット。]
5. **[サインオン] を選択します**。
6. ページの一番下までスクロールします。

Microsoft Office 365 アプリケーション のサインオン ポリシーには、次の 4 つの規則があります。

- **モバイル セッションに MFA を適用する** - iOS または Android での先進認証またはブラウザー セッションからの MFA が必要
- **信頼できる Windows デバイスを許可** する - 信頼された Okta デバイスに対する不要な検証または要素のプロンプトを防ぎます
- **信頼されていない Windows デバイスから MFA を要求する - 信頼されていない Windows デバイス** での先進認証またはブラウザー セッションからの MFA が必要
- **レガシ認証をブロック** する - レガシ認証クライアントがサービスに接続できないようにします

次のスクリーンショットは、[サインオン ポリシー] 画面の 4 つの規則の条件とアクションです。

[Image: [サインオン ポリシー] 画面の 4 つのルールの条件とアクションのスクリーンショット。]

### 条件付きアクセス ポリシーを構成する

Okta の条件に一致するように条件付きアクセス ポリシーを構成します。 ただし、シナリオによっては、さらに多くの設定が必要になる場合があります。

- Okta のネットワークの場所から Microsoft Entra ID の名前付きの場所へ
    - [条件付きアクセス ポリシーでの場所の条件の使用](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)
- Okta デバイスの信頼からデバイス ベースの条件付きアクセスへ (ユーザー デバイスを評価するための 2 つのオプション):
    - Windows 10、Windows Server 2016、2019 などの Windows デバイスを Microsoft Entra ID に同期するための Microsoft **Entra ハイブリッド参加の構成** については、次のセクションを参照してください。
    - 次のセクション「**デバイス コンプライアンスの構成」を**参照してください
    - Windows 10、Windows Server 2016、Windows Server 2019 などの Windows デバイスを Microsoft Entra ID に同期する Microsoft Entra Connect サーバーの機能である Microsoft Entra ハイブリッド参加の使用に関する記事を参照してください。
    - 「 Microsoft Intune にデバイスを登録 し、コンプライアンス ポリシーを割り当てる」を参照してください

#### Microsoft Entra Hybrid Join の構成

Microsoft Entra Connect サーバーで Microsoft Entra Hybrid Join を有効にするには、構成ウィザードを起動します。 構成後にデバイスを登録します。

注

Microsoft Entra Hybrid Join は、Microsoft Entra Connect クラウド プロビジョニング エージェントではサポートされていません。

1. [Microsoft Entra のハイブリッド参加を設定する](https://learn.microsoft.com/ja-jp/entra/identity/devices/how-to-hybrid-join)。
2. **[SCP 構成**] ページで、[**認証サービス**] ドロップダウンを選択します。

    [Image: Microsoft Entra Connect ダイアログの [認証サービス] ドロップダウンのスクリーンショット。]
3. Okta フェデレーション プロバイダーの URL を選択します。
4. **追加**を選択します。
5. オンプレミスのエンタープライズ管理者の資格情報を入力します。
6. [ **次へ**] を選択します。

    ヒント

    グローバルまたはアプリレベルのサインオン ポリシーで Windows クライアント上のレガシ認証をブロックしている場合は、Microsoft Entra Hybrid Join プロセスが終了できるようにするルールを作成します。 Windows クライアントに対してレガシ認証スタックを許可します。 アプリ ポリシーでカスタム クライアント文字列を有効にするには、 [Okta ヘルプ センター](https://support.okta.com/help/)にお問い合わせください。

#### デバイス コンプライアンスの構成

Microsoft Entra Hybrid Join は、Windows 上の Okta デバイス信頼に置き換わるものです。 条件付きアクセス ポリシーは、Microsoft Intune に登録されているデバイスのコンプライアンスを認識します。

##### デバイス コンプライアンス ポリシー

- [コンプライアンス ポリシーを使用して Intune で管理するデバイスのルールを設定する](https://learn.microsoft.com/ja-jp/mem/intune/protect/device-compliance-get-started)
- [Microsoft Intune でコンプライアンス ポリシーを作成する](https://learn.microsoft.com/ja-jp/mem/intune/protect/create-compliance-policy)

##### Windows 10/11、iOS、iPadOS、Android の登録

Microsoft Entra Hybrid Join をデプロイしている場合は、別のグループ ポリシーをデプロイして、Intune のこれらのデバイスの自動登録を実行できます。

- [Microsoft Intune での登録](https://learn.microsoft.com/ja-jp/mem/intune/)
- [クイック スタート: Windows 10/11 デバイスの自動登録を設定する](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/quickstart-setup-auto-enrollment)
- [Android デバイスを登録する](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/deployment-guide-enrollment-android)
- [Intune に iOS/iPadOS デバイスを登録する](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/deployment-guide-enrollment-ios-ipados)

### Microsoft Entra 多要素認証テナントの設定を構成する

条件付きアクセスに変換する前に、組織の MFA テナントの基本設定を確認します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. [**ユーザー**] ウィンドウの上部メニューで [**ユーザーごとの MFA**] を選択します。
4. レガシ Microsoft Entra 多要素認証ポータルが表示されます。 または、 [Microsoft Entra 多要素認証ポータル](https://aka.ms/mfaportal)を選択します。

    [Image: 多要素認証画面のスクリーンショット。]
5. レガシ MFA が有効になっているユーザーがないことを確認します。[**多要素認証**] メニューの [**多要素認証] の状態**で、[**有効] と [** **適用]** を選択します。 次のビューでテナントにユーザーがいる場合は、レガシ メニューでそれらを無効にします。

    [Image: 検索機能が強調表示されている多要素認証画面のスクリーンショット。]
6. **[適用]** フィールドが空であることを確認します。
7. [ **サービス設定]** オプションを選択します。
8. [ **アプリ パスワード** ] の選択を [ **ユーザーがブラウザー以外のアプリにサインインするためのアプリ パスワードを作成できないようにする] に**変更します。

    [Image: サービス設定が強調表示されている多要素認証画面のスクリーンショット。]
9. **[イントラネット上のフェデレーション ユーザーからの要求に対する多要素認証をスキップ**する] と **[信頼するデバイスでの多要素認証をユーザーに記憶できるようにする ]のチェック ボックスをオフにします (1 日から 365 日**)。
10. **[保存] を選択します**。

    [Image: [アクセスに信頼できるデバイスが必要] 画面のチェック ボックスがオフになっているスクリーンショット。]

    注

    [「再認証プロンプトを最適化する」を参照し、Microsoft Entra 多要素認証のセッションの有効期間について理解](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concepts-azure-multi-factor-authentication-prompts-session-lifetime)します。

### 条件付きアクセス ポリシーを作成する

条件付きアクセス ポリシーを構成するには、「 [条件付きアクセスの展開と設計のベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access#conditional-access-policy-components)」を参照してください。

前提条件と確立された基本設定を構成したら、条件付きアクセス ポリシーを作成できます。 アプリケーション、テスト用ユーザー グループ、またはその両方に対してポリシーをターゲットにすることができます。

開始する前に、以下の操作を行います。

- [条件付きアクセス ポリシー コンポーネントについて](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/plan-conditional-access)
- [条件付きアクセス ポリシーの構築](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies)

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Conditional Access** に移動します。
3. Microsoft Entra ID でポリシーを作成する方法を確認するには。 「 [一般的な条件付きアクセス ポリシー: すべてのユーザーに MFA を要求する」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)参照してください。
4. デバイスの信頼ベースの条件付きアクセス規則を作成します。

    [Image: [条件付きアクセス] の [アクセスに信頼されたデバイスが必要] のエントリのスクリーンショット。]

    [Image: 成功メッセージが表示された [アカウントをセキュリティで保護する] ダイアログのスクリーンショット。]
5. 場所ベースのポリシーとデバイス信頼ポリシーを構成したら、 [条件付きアクセスを使用して Microsoft Entra ID によるレガシ認証をブロック](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-block-legacy-authentication)します。

これら 3 つの条件付きアクセス ポリシーにより、元の Okta サインオン ポリシー エクスペリエンスが Microsoft Entra ID にレプリケートされます。

### MFA にパイロット メンバーを登録する

ユーザーは MFA 方法に登録します。

個々の登録の場合、ユーザーは [Microsoft サインイン ウィンドウ](https://aka.ms/mfasetup)に移動します。

登録を管理するには、ユーザーは [Microsoft マイ Sign-Ins | セキュリティ情報](https://aka.ms/mysecurityinfo)にアクセスします。

詳細情報: [Microsoft Entra ID で統合されたセキュリティ情報の登録を有効にします](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-registration-mfa-sspr-combined)。

注

ユーザーが登録されると、MFA を満たした後、 **マイ セキュリティ** ページにリダイレクトされます。

### 条件付きアクセス ポリシーを有効にする

1. テストするには、作成したポリシーを **[有効なテスト ユーザー ログイン]** に変更します。

    [Image: [条件付きアクセス] の [ポリシー] 画面のポリシーのスクリーンショット。]
2. Office 365 **サインイン ウィンドウで** 、テスト ユーザーの John Smith に Okta MFA と Microsoft Entra 多要素認証を使用してサインインするように求められます。
3. Okta による MFA の検証を完了します。

    [Image: Okta による MFA 検証のスクリーンショット。]
4. ユーザーは条件付きアクセスを求められます。
5. MFA をトリガーするようにポリシーが構成されていることを確認します。

    [Image: 条件付きアクセスを求められた Okta による MFA 検証のスクリーンショット。]

### 条件付きアクセス ポリシーに組織のメンバーを追加する

パイロット メンバーに対してテストを実施した後、登録後に、残りの組織メンバーを条件付きアクセス ポリシーに追加します。

Microsoft Entra 多要素認証と Okta MFA の間で二重にプロンプトが表示されるのを避けるために、Okta MFA からオプトアウトするため、サインオン ポリシーを変更します。

1. Okta 管理コンソールに移動します。
2. **セキュリティ**&gt;**認証**の選択
3. **[サインオン ポリシー] に移動します**。

    注

    Okta のすべてのアプリケーションがアプリケーション サインオン ポリシーによって保護されている場合は、グローバル ポリシーを **非アクティブ** に設定します。
4. **[MFA** ポリシーの適用] を **[非アクティブ]** に設定します。 Microsoft Entra ユーザーを含まない新しいグループにポリシーを割り当てることができます。

    [Image: グローバル MFA サインオン ポリシーが非アクティブであるスクリーンショット。]
5. アプリケーション レベルのサインオン ポリシー ウィンドウで、[ **ルールの無効化** ] オプションを選択します。
6. **[非アクティブ]** を選択します。 Microsoft Entra ユーザーを含まない新しいグループにポリシーを割り当てることができます。
7. MFA なしでのアクセスを許可するアプリケーションに対して、有効なアプリケーションレベルのサインオン ポリシーが少なくとも 1 つあることを確認します。

    [Image: MFA を使用しないアプリケーション アクセスのスクリーンショット。]
8. ユーザーは、次回のサインイン時に条件付きアクセスを求められます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migrate-okta-sync-provisioning"} -->
## Okta 同期プロビジョニングを Microsoft Entra Connect ベースの同期に移行する方法のチュートリアル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-okta-sync-provisioning
- Service: entra-id / enterprise-apps
- Article date: 2024-12-04
- Summary: Okta から Microsoft Entra ID にユーザー プロビジョニングを移行します。 Microsoft Entra Connect サーバーまたは Microsoft Entra クラウド プロビジョニングを使用する方法を参照してください。

このチュートリアルでは、Okta から Microsoft Entra ID にユーザー プロビジョニングを移行し、ユーザー同期またはユニバーサル同期を Microsoft Entra Connect に移行するため方法について学習します。 この機能により、Microsoft Entra ID や Office 365 にプロビジョニングできます。

注

同期プラットフォームを移行する場合、Microsoft Entra Connect をステージング モードから削除したり、Microsoft Entra クラウド プロビジョニング エージェントを有効にしたりする前に、お使いの環境に対してこの記事の手順を検証してください。

### 前提条件

Okta プロビジョニングから Microsoft Entra ID に切り替える場合は、2 つの選択肢があります。 Microsoft Entra Connect サーバーまたは Microsoft Entra クラウド プロビジョニングを使用します。

詳細情報: [Microsoft Entra Connect とクラウド同期の比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/connect-to-cloud-sync-decision-guide#comparison-between-microsoft-entra-connect-and-cloud-sync)。

Microsoft Entra クラウド プロビジョニングは、ユニバーサル同期やユーザー同期を使っている Okta のお客様にとって最もわかりやすい移行パスです。クラウド プロビジョニング エージェントは軽量です。 Okta ディレクトリ同期エージェントのように、ドメイン コントローラー上またはその近くに、それらをインストールできます。 これらを同じサーバーにはインストールしないでください。

ユーザーを同期するときに、お客様の組織で次のいずれかのテクノロジが必要な場合は、Microsoft Entra Connect サーバーを使います。

- デバイスの同期: Microsoft Entra ハイブリッド結合または Hello for Business
- パススルー認証
- 15万個以上のオブジェクトへの対応
- 書き戻しのサポート

Microsoft Entra Connect を使用するには、ハイブリッド ID の管理者ロールでサインインする必要があります。

注

Microsoft Entra Connect や Microsoft Entra クラウド プロビジョニングをインストールする場合は、すべての前提条件を考慮してください。 インストールを続行する前に、「[Microsoft Entra Connect の前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites)」を参照してください。

### Okta によって同期された ImmutableID 属性を確認する

ImmutableID 属性を使って、同期されたオブジェクトをオンプレミスの対応するオブジェクトに結び付けることができます。 オンプレミス オブジェクトの Active Directory objectGUID が Okta で受け取られると、それは Base-64 でエンコードされた文字列に変換されます。 既定では、その文字列が Microsoft Entra ID の ImmutableID フィールドにスタンプされます。

Microsoft Graph PowerShell に接続すると、現在の ImmutableID 値を確認できます。 Microsoft Graph PowerShell モジュールを使用していない場合は、次を実行します。

次のコマンドを実行する前に、管理セッションで `Install-Module Microsoft.Graph -Scope CurrentUser -Repository PSGallery -Force` を設定してください。

```Powershell
Connect-MgGraph
```

このモジュールがある場合は、最新バージョンへの更新を求める警告が表示されることがあります。

1. インストールされているモジュールをインポートします。
2. 認証ウィンドウで、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
3. テナントに接続します。
4. ImmutableID 値の設定を確認します。 objectGUID を ImmutableID に変換する既定値の例を次に示します。
5. オンプレミスで objectGUID から Base64 への変換を手動で確認します。 個々の値をテストするには、次のコマンドを使います。

    ```PowerShell
    Get-MgUser onpremupn | fl objectguid
    $objectguid = 'your-guid-here-1010'
    [system.convert]::ToBase64String(([GUID]$objectGUID).ToByteArray())
    ```

### objectGUID の一括検証メソッド

Microsoft Entra Connect に移行する前に、Microsoft Entra ID の ImmutableID の値がオンプレミスでのそれらの値と一致することを検証することが重要です。

次のコマンドは、オンプレミスの Microsoft Entra ユーザーを取得し、それらの objectGUID の値と計算済みの ImmutableID の値の一覧を CSV ファイルにエクスポートします。

1. オンプレミスのドメイン コントローラーの Microsoft Graph PowerShell で次のコマンドを実行します。

    ```PowerShell
    Get-ADUser -Filter * -Properties objectGUID | Select-Object
    UserPrincipalName, Name, objectGUID, @{Name = 'ImmutableID';
    Expression = {
    [system.convert]::ToBase64String((GUID).tobytearray())
    } } | export-csv C:\Temp\OnPremIDs.csv
    ```
2. Microsoft Graph PowerShell セッションで次のコマンドを実行して、同期された値を一覧表示します。

    ```powershell
    Get-MgUser -all $true | Where-Object {$_.dirsyncenabled -like
    "true"} | Select-Object UserPrincipalName, @{Name = 'objectGUID';
    Expression = {
    [GUID][System.Convert]::FromBase64String($_.ImmutableID) } },
    ImmutableID | export-csv C:\\temp\\AzureADSyncedIDS.csv
    ```
3. 両方のエクスポート後、ユーザーの ImmutableID の値が一致することを確認します。

    重要

    クラウドの ImmutableID 値が objectGUID 値と一致しない場合は、Okta 同期の既定値が変更されています。ImmutableID 値を決定するために別の属性が選択されている可能性があります。 次のセクションに進む前に、ImmutableID の値を設定するソース属性を確認します。 Okta 同期を無効にする前に、Okta が同期している属性を更新します。

### ステージング モードで Microsoft Entra Connect をインストールする

ソースと移行先ターゲットの一覧が準備できたら、Microsoft Entra Connect サーバーをインストールします。 Microsoft Entra Connect クラウド プロビジョニングを使う場合は、このセクションをスキップしてください。

1. サーバーに Microsoft Entra Connectをダウンロードしてインストールします。 「[Microsoft Entra Connect のカスタム インストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom)」を参照してください。
2. 左側のパネルで、**[ユーザーの識別]** を選びます。
3. **[一意のユーザー識別]** ページの **[Microsoft Entra ID でのユーザーの識別方法を選択する]** で、**[特定の属性を選択する]** を選びます。
4. Okta の既定値を変更しなかった場合は、**[mS-DS-ConsistencyGUID]** を選択します。

    警告

    この手順は重要です。 ソース アンカーに選んでいる属性が Microsoft Entra ユーザーに設定されていることを確実にします。 間違った属性を選んだ場合は、Microsoft Entra Connect をアンインストールしてから再インストールして、このオプションをもう一度選びます。
5. **[次へ]** を選択します。
6. 左側のパネルで **[構成]** を選びます。
7. **[構成の準備完了]** ページで、**[ステージング モードを有効にする]** をオンにします。
8. **[インストール]** を選択します。
9. ImmutableID 値が一致することを確認します。
10. 構成が完了したら、**[終了]** を選びます。
11. 管理者として **Synchronization Service** を開きます。
12. domain.onmicrosoft.com コネクタ スペースへの**完全同期**を見つけます。
13. **[Connectors with Flow Updates] (フロー更新を含むコネクタ)** タブにユーザーが表示されることを確認します。
14. エクスポートで保留中の削除がないことを確認します。
15. **[コネクタ]** タブを選択します。
16. domain.onmicrosoft.com コネクタ スペースを強調表示します。
17. **[Search Connector Space (コネクタ スペースの検索)]** を選択します。
18. **[Search Connector Space](コネクタ スペースの検索)** ダイアログの **[スコープ]** で、**[保留中のエクスポート]** を選びます。
19. **削除**を選択します。
20. **[Search]** を選択します。 すべてのオブジェクトが一致する場合、**[削除]** に一致するレコードは表示されません。
21. 削除を保留しているオブジェクトとそのオンプレミスの値をメモします。
22. **[削除]** をクリアします。
23. **[追加]** を選択します。
24. **[変更]** を選択します。
25. **[Search]** を選択します。
26. Okta 経由で Microsoft Entra ID に同期しているユーザーに対して、更新機能が表示されます。 Microsoft Entra Connect のインストール時に選んだ組織単位 (OU) 構造にある、Okta が同期していない新しいオブジェクトを追加します。
27. Microsoft Entra Connect が Microsoft Entra ID と通信した内容を確認するには、更新をダブルクリックします。

注

Microsoft Entra ID 内のユーザーに対して**追加**機能がある場合、そのオンプレミス アカウントはクラウド アカウントと一致しません。 Entra Connect により、新しいオブジェクトが作成され、新規と予期しない追加が記録されます。

1. ステージング モードを終了する前に、Microsoft Entra ID の ImmutableID 値を修正します。

この例では、オンプレミスの値が正確でなかったにもかかわらず、Okta は**メール**属性をユーザーのアカウントにスタンプしました。 Microsoft Entra Connect がアカウントを引き継ぐと、**メール**属性はこのユーザーのオブジェクトから削除されます。

1. 更新に Microsoft Entra ID で想定される属性が含まれていることを確認します。 複数の属性を削除する場合、ステージング モードを削除する前に、オンプレミスの AD 値を設定することができます。

注

次に進む前に、ユーザー属性が同期され、**[保留中のエクスポート]** タブに表示されていることを確認します。削除されている場合は、ImmutableID の値が一致し、ユーザーが同期のために選ばれた OU に属していることを確認します。

### Microsoft Entra Connect クラウド同期エージェントをインストールする

ソースと移行先ターゲットの一覧が準備できたら、Microsoft Entra Connect クラウド同期エージェントをインストールして構成します。 「[チュートリアル: 単一のフォレストを単一の Microsoft Entra テナントに統合する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/tutorial-single-forest)」を参照してください。

注

Microsoft Entra Connect サーバーを使う場合は、このセクションをスキップしてください。

### Microsoft Entra ID への Okta プロビジョニングを無効にする

Microsoft Entra Connect のインストールを確認したら、Microsoft Entra ID への Okta プロビジョニングを無効にします。

1. Okta ポータルに移動します
2. **[アプリケーション]** を選択します。
3. ユーザーを Microsoft Entra ID にプロビジョニングする Okta アプリを選びます。
4. **[プロビジョニング]** タブを選択します。
5. **[Integration] (統合)** セクションを選びます。

    [Image: Okta ポータルの [Integration] (統合) セクションのスクリーンショット。]
6. **[編集]** を選択します。
7. **[Enable API integration] (API 統合を有効にする)** オプションをオフにします。
8. **[保存]** を選択します。

    [Image: Okta ポータルの [Integration] (統合) セクションのスクリーンショット。]

    注

    Microsoft Entra ID へのプロビジョニングを処理する Office 365 アプリが複数ある場合は、それらがオフになっていることを確実にします。

### Microsoft Entra Connect でステージング モードを無効にする

Okta のプロビジョニングを無効にすると、Microsoft Entra Connect サーバーはオブジェクトを同期できるようになります。

注

Microsoft Entra Connect クラウド同期エージェントを使う場合は、このセクションをスキップしてください。

1. デスクトップからインストール ウィザードを実行します。
2. **[構成]** をクリックします。
3. **[ステージング モードの構成]** を選びます。
4. **[次へ]** を選択します。
5. お使いの環境のハイブリッド ID の管理者アカウントの資格情報を入力します。
6. **[ステージング モードを有効にする]** をオフにします。
7. **[次へ]** を選択します。
8. **[構成]** をクリックします。
9. 構成が終わったら、管理者として**同期サービス**を開きます。
10. domain.onmicrosoft.com コネクタの **[エクスポート]** を確認します。
11. 追加、更新、削除を確認します。
12. 移行が完了しました。 インストール ウィザードをもう一度実行し、Microsoft Entra Connect の機能を更新して拡張します。

### クラウド同期エージェントを有効にする

Okta のプロビジョニングを無効にすると、Microsoft Entra Connect クラウド同期エージェントはオブジェクトを同期できるようになります。

1. 少なくとも[ハイブリッド ID 管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)にサインインします。
2. **Entra ID**&gt;**Entra Connect**&gt;**Connect Sync** に移動します。
3. **[構成]** プロファイルを選びます。
4. **[有効化]** を選択します。
5. プロビジョニング メニューに戻り、 **[ログ]** を選択します。
6. プロビジョニング コネクタによってインプレース オブジェクトが更新されたことを確認します。 クラウド同期エージェントは非破壊的です。 一致するものが見つからない場合、更新は失敗します。
7. ユーザーが一致しない場合は、更新を行って ImmutableID 値をバインドします。
8. クラウド プロビジョニング同期を再開します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/migration-resources"} -->
## アプリを Microsoft Entra ID に移行するためのリソース - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migration-resources
- Service: entra-id / enterprise-apps
- Article date: 2024-05-27
- Summary: アプリケーションのアクセスと認証を Microsoft Entra ID に移行するために役立つリソース。

アプリケーションのアクセスと認証を Microsoft Entra ID に移行するために役立つリソース。

| リソース | 説明 |
| --- | --- |
| [Microsoft Entra ID へのアプリの移行](https://aka.ms/migrateapps/whitepaper) | この記事では、検出、分類、移行、継続的な管理という 4 つの明確なフェーズで移行を計画する方法について説明する一連の記事を紹介しています。 プロセスの考え方と、プロジェクトを実行しやすいピースに分割する方法について説明しています。 シリーズ全体で、作業中に役立つ重要なリソースへのリンクが提供されています。 |
| [開発者向けチュートリアル: 開発者向けの AD FS から Microsoft Entra へのアプリケーション移行プレイブック](https://aka.ms/adfsplaybook) | この ASP.NET コード サンプルのセットと付随するチュートリアルは、Active Directory Federation Services (AD FS) と統合されているアプリケーションを Microsoft Entra ID に安全に移行する方法を学習するのに役立ちます。 このチュートリアルは、AD FS と Microsoft Entra ID の両方でアプリを構成する方法を学習するだけでなく、このプロセスでコード ベースに必要となる変更について認識し、確信を持つ必要がある開発者を対象にしています。 |
| [Tool: Active Directory Federation Services Migration Readiness Script](https://aka.ms/migrateapps/adfstools) (ツール: Active Directory フェデレーション サービス移行準備スクリプト) | これは、アプリが Microsoft Entra ID に移行する準備が整っているかどうかを判別するために、オンプレミスの Active Directory フェデレーション サービス (AD FS) サーバーで実行できるスクリプトです。 |
| [Deployment plan: Migrating from AD FS to password hash sync](https://aka.ms/ADFSTOPHSDPDownload) (デプロイ計画: AD FS からパスワード ハッシュの同期への移行) | パスワード ハッシュ同期では、ユーザー パスワードのハッシュがオンプレミスの Active Directory から Microsoft Entra ID に同期されます。 これにより、Microsoft Entra ID は、オンプレミスの Active Directory との対話なしでユーザーを認証できます。 |
| [Deployment plan: Migrating from AD FS to pass-through authentication](https://aka.ms/ADFSTOPTADPDownload) (デプロイ計画: AD FS からパススルー認証への移行) | Microsoft Entra パススルー認証を使用すると、ユーザーは同じパスワードを使用して、オンプレミスのアプリケーションとクラウド ベースのアプリケーションの両方にサインインできます。 この機能は、ユーザーが記憶するパスワードが 1 つ減るため、エクスペリエンスが向上します。 さらに、覚えておく必要があるパスワードが 1 つだけであれば、ユーザーがサインイン方法を忘れる可能性が低くなるため、IT ヘルプデスクのコストが削減されます。 この機能により、ユーザーが Microsoft Entra ID を使用してサインインするとき、ユーザーのパスワードがオンプレミスの Active Directory に対して直接検証されます。 |
| [Deployment plan: Enabling single sign-on to a SaaS app with Microsoft Entra ID](https://aka.ms/SSODPDownload) (デプロイ計画: Microsoft Entra ID による SaaS アプリに対するシングル サインオンの有効化) | シングル サインオン (SSO) は、1 つのユーザー アカウントを使って 1 回サインインするだけで作業に必要なすべてのアプリとリソースにアクセスできる機能です。 たとえば、サインインした後、ユーザーは、2 回目の認証 (パスワードの入力など) なしで Microsoft Office から SalesForce や Box に移動できます。 |
| [Deployment plan: Extending apps to Microsoft Entra ID with Application Proxy](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-deployment-plan) (デプロイ計画: アプリケーション プロキシによる Microsoft Entra ID へのアプリの拡張) | 従業員のノート PC やその他のデバイスからオンプレミスのアプリケーションにアクセスするには、従来は仮想プライベート ネットワーク (VPN) または非武装地帯 (DMZ) が必要でした。 これらのソリューションは、複雑でセキュリティ保護が困難であるだけでなく、設定と管理にコストがかかります。 Microsoft Entra アプリケーション プロキシを使用すると、オンプレミスのアプリケーションに簡単にアクセスできます。 |
| [その他のデプロイ計画](https://learn.microsoft.com/ja-jp/entra/architecture/deployment-plans) | Microsoft Entra の多要素認証、条件付きアクセス、ユーザーのプロビジョニング、シームレス SSO、セルフサービス パスワード リセットなどの機能をデプロイするためのデプロイ計画を確認できます。 |
| [Migrating apps from Symantec SiteMinder to Microsoft Entra ID](https://azure.microsoft.com/mediahandler/files/resourcefiles/migrating-applications-from-symantec-siteminder-to-azure-active-directory/Migrating-applications-from-Symantec-SiteMinder-to-Azure-Active-Directory.pdf) (Symantec SiteMinder から Microsoft Entra ID へのアプリの移行) | Symantec SiteMinder から Microsoft Entra ID へのアプリケーションの移行手順を説明する例と共に、アプリケーションの移行および統合オプションに関するステップ バイ ステップ ガイダンスを提供します。 |
| [アプリケーションの ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare) | このガイドでは、アプリケーションの ID ガバナンスを以前の ID ガバナンス テクノロジから移行して、Microsoft Entra ID をそのアプリケーションに接続するために必要な手順について説明します。 |
| [Active Directory フェデレーション サービス (AD FS) の使用停止ガイド](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/decommission/adfs-decommission-guide) | このガイドでは、ユーザー認証とアプリケーションの Microsoft Entra ID への移行など、使用停止の前提条件について説明します。 また、ロード バランサー エントリの削除、WAP サーバーと AD FS サーバーのアンインストール、SSL 証明書とデータベースの削除など、AD FS サーバーの使用を停止する手順についても説明します。 |
| [ADFS から Microsoft Entra ID にアプリを移行するフェーズ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-phases-overview) | この一連の記事では、ADFS から Microsoft Entra ID へのアプリケーションの一般的な移行の 5 つのフェーズについて説明されています。 |
| [ID 管理シナリオを SAP IDM から Microsoft Entra に移行する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/migrate-from-sap-idm) | SAP Identity Management (IDM) を使用しているのであれば、ID 管理シナリオを SAP IDM から Microsoft Entra に移行できます。 |
| [ID およびアクセス管理のシナリオを Microsoft Identity Manager から Microsoft Entra に移行する](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/migrate-entra-id) | このドキュメントでは、ID およびアクセス管理 (IAM) のシナリオを、Microsoft Identity Manager から Microsoft Entra のクラウドでホストされるサービスに移行するための、移行オプションとアプローチに関するガイダンスを提供します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/myapps-overview"} -->
## マイ アプリ ポータルの概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview
- Service: entra-id / enterprise-apps
- Article date: 2024-10-31
- Summary: マイ アプリ ポータルでアプリケーションを管理する方法について説明します。

マイ アプリは、Microsoft Entra ID でアプリケーションを管理および起動するために使用される Web ベースのポータルです。 マイ アプリでアプリケーションを操作するには、Microsoft Entra ID の組織アカウントを使用し、Microsoft Entra 管理者によって付与されるアクセス権を取得します。

マイ アプリは Microsoft Entra 管理センターから独立しているため、ユーザーが Azure サブスクリプションや Microsoft 365 サブスクリプションを持っている必要はありません。

ユーザーは以下のためにマイ アプリ ポータルにアクセスします。

- 自分がアクセス権を持っているアプリケーションを確認する
- 組織がセルフサービスの対象としてサポートする新しいアプリケーションを要求する
- アプリケーションの個人コレクションを作成する
- アプリケーションへのアクセスの管理

Microsoft Entra 管理センターのエンタープライズ アプリケーションの一覧のアプリケーションが、マイ アプリ ポータルでユーザーまたはグループに表示されるかどうかは、次の条件によって決まります。

- アプリケーションがそのプロパティで表示されるように設定されている
- アプリケーションがユーザーまたはグループに割り当てられている

注

Microsoft Entra 管理センターの **[ユーザーは Office 365 ポータルでのみ Office 365 アプリを表示できる]** プロパティは、ユーザーが Office 365 ポータルで Office 365 アプリケーションのみを表示できるかどうかに影響する場合があります。 この設定が **[いいえ]** に設定されている場合、ユーザーはマイ アプリ ポータルと Office 365 ポータルの両方で Office 365 アプリケーションを表示できます。 この設定は、 の &gt; にあります。

管理者は以下を構成できます。

- サービス使用条件を含む同意エクスペリエンス
- セルフサービスによるアプリケーションの検出とアクセスの要求
- アプリケーションのコレクション
- 会社とアプリケーションのブランド化

### アプリケーションのプロパティを理解する

アプリケーションに対して定義されているプロパティは、マイ アプリ ポータルでのユーザーの操作方法に影響を与える可能性があります。

- **ユーザーのサインインが有効になっていますか?** – このプロパティが **[はい]** に設定されている場合、割り当てられているユーザーは、マイ アプリ ポータルからアプリケーションにサインインできます。
- **[名前]** - マイ アプリ ポータルでユーザーに表示されるアプリケーションの名前。 管理者は、アプリケーションへのアクセスを管理する際に名前を確認します。
- **[ホームページ URL]** - アプリケーションがマイ アプリ ポータルで選ばれると起動される URL。
- **[ロゴ]** - マイ アプリ ポータルでユーザーに表示されるアプリケーションのロゴ。
- **[ユーザーに表示しますか]** - アプリケーションがマイ アプリ ポータルに表示されるようにします。 この値を **[はい]** に設定しても、ユーザーまたはグループがまだ割り当てられていない場合は、アプリケーションがマイ アプリ ポータルに表示されません。 割り当てられたユーザーのみが、マイ アプリ ポータルでアプリケーションを見ることができます。

詳細については、[エンタープライズアプリケーションのプロパティ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-properties)に関する記事を参照してください。

#### アプリケーションの検出

[マイ アプリ](https://myapps.microsoft.com) ポータルにサインインすると、表示されるように設定されているアプリケーションが表示されます。 マイ アプリ ポータルにアプリケーションが表示されるようにするには、[Microsoft Entra 管理センター](https://entra.microsoft.com)で適切なプロパティを設定します。 また、Microsoft Entra 管理センターで、ユーザーまたは適切なメンバーを含むグループを割り当てます。

マイ アプリ ポータルでアプリケーションを検索するには、ページの上部にある検索ボックスにアプリケーションの名前を入力して、アプリケーションを見つけます。 一覧表示されるアプリケーションは、**リスト ビュー**または**グリッド ビュー**の形式に設定できます。

注

エンド ユーザーはマイ アプリでパスワード SSO アプリを追加できなくなりました。 エンド ユーザー用のパスワード SSO アプリを追加する必要がある場合は、Microsoft Entra 管理センターで行うことができます。 詳細については、[パスワードベースのシングル サインオンのアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-password-single-sign-on-non-gallery-applications)に関する記事を参照してください。

[Image: マイ アプリ ポータルの検索ボックスを示すスクリーンショット。]

重要

アプリケーションが Microsoft Entra 管理センターでテナントに追加されてから、マイ アプリ ポータルに表示されるまで、数分かかる場合があります。 また、追加された後のアプリケーションにユーザーがアクセスできるようになるまで、しばらくかかる場合もあります。

アプリケーションは非表示にすることができます。 詳細については、[エンタープライズ アプリケーションを非表示にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/hide-application-from-user-portal)に関する記事を参照してください。

### 会社のブランドを割り当てる

マイ アプリ ポータルで会社のブランドを表すアプリケーションのロゴと名前を、Microsoft Entra 管理センターで定義します。 次の Contoso デモのロゴのように、ページの上部にバナー ロゴが表示されます。

[Image: マイ アプリ ポータルのバナー ロゴを示すスクリーンショット。]

詳しくは、[組織のサインイン ページへのブランドの追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding)に関する記事をご覧ください。

### アプリケーションへのアクセスの管理

ユーザーがアプリケーションにアクセスする方法とアクセスするかどうかには、複数の要因が影響します。 アプリケーションに割り当てられたアクセス許可により、それで実行できることが異なる場合があります。 セルフサービスのアクセスを許可するようにアプリケーションを構成することも、テナントの管理者のみがアクセスを許可することもできます。

#### マイ アプリによるセキュリティで保護されたサインイン拡張機能

一部のアプリケーションにサインインするには、マイ アプリのセキュリティで保護されたサインイン拡張機能をインストールします。 パスワード ベースの SSO アプリケーション、または Microsoft Entra アプリケーション プロキシによってアクセスされるアプリケーションにサインインするには、この拡張機能が必要です。 ユーザーは、パスワード ベースのシングル サインオンまたはアプリケーション プロキシ アプリケーションを最初に起動するときに、この拡張機能のインストールを求められます。

このようなアプリケーションを統合するには、サポートされているブラウザーで拡張機能を大規模にデプロイするためのメカニズムを定義します。 次のオプションがあります。

- Chrome、Microsoft Edge、IE でのユーザー主導のダウンロードと構成
- Internet Explorer の Configuration Manager

パスワード ベースの SSO を使用するアプリケーション、または Microsoft Entra アプリケーション プロキシを使用してアクセスされるアプリケーションの場合は、Microsoft Edge モバイルを使います。 その他のアプリケーションには、任意のモバイル ブラウザーを使用できます。 モバイルの設定で、パスワード ベースの SSO を必ず有効にしてください。既定でオフになっている場合があります。 たとえば、**[設定] &gt; [プライバシーとセキュリティ] &gt; [Microsoft Entra パスワード SSO]** です。

拡張機能をダウンロードしてインストールするには:

- **Microsoft Edge** - Microsoft Store から、[マイ アプリによるセキュリティで保護されたサインイン拡張](https://microsoftedge.microsoft.com/addons/detail/my-apps-secure-signin-ex/gaaceiggkkiffbfdpmfapegoiohkiipl)機能に移動し、**[取得] を選んで Microsoft Edge レガシ ブラウザー用の拡張機能を取得**します。
- **Google Chrome** - Chrome Web ストアから、[マイ アプリによるセキュリティで保護されたサインイン拡張](https://chrome.google.com/webstore/detail/my-apps-secure-sign-in-ex/ggjhpefgjjfobnfoldnjipclpcfbgbhl)機能に移動して、 **[Chrome に追加]** を選択します。

アドレス バーの右側にアイコンが追加され、拡張機能のサインインとカスタマイズが可能になります。

注

現在、ゲスト B2B Microsoft アカウント (MSA) では拡張機能へのサインインはサポートされていません。

#### アクセス許可

アプリケーションに付与されているアクセス許可を確認するには、アプリケーションを表すタイルの右上隅を選択し、**[アプリケーションの管理]** を選択します。

表示されるアクセス許可は、管理者またはユーザーが同意したものです。 ユーザーが同意したアクセス許可は、ユーザーが取り消すことができます。

#### セルフサービス アクセス

アクセス権は、テナント レベルで付与したり、特定のユーザーに割り当てたり、セルフサービス アクセスから付与したりできます。 ユーザーがマイ アプリ ポータルから自分でアプリケーションを見つけられるようにするには、Microsoft Entra 管理センターでセルフサービス アプリケーション アクセスを有効にします。 この機能は、次の方法を使って追加されたアプリケーションで使用できます。

- Microsoft Entra アプリケーション ギャラリー
- Microsoft Entra アプリケーション プロキシ
- ユーザーまたは管理者の同意の使用

ユーザーがマイ アプリ ポータルを使ってアプリケーションを検出し、アクセスを要求できるようにします。 そのためには、Microsoft Entra 管理センターで次のタスクを実行します。

- セルフサービスのグループ管理を有効にする
- シングル サインオンに対してアプリケーションを有効にする
- アプリケーション アクセス用のグループを作成する

ユーザーがアクセスを要求するときは、基になるグループへのアクセスを要求します。グループの所有者は、グループのメンバーシップとアプリケーションのアクセスを管理するためのアクセス許可を委任できます。 承認ワークフローは、アプリケーションにアクセスするための明示的な承認に使用できます。 承認者であるユーザーは、アプリケーションへのアクセス要求が保留中になっていると、マイ アプリ ポータルで通知を受け取ります。

詳細については、[セルフサービス アプリケーションの割り当てを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-self-service-access)に関する記事を参照してください。

#### シングル サインオン

可能な場合は常に、マイ アプリ ポータルで使用できるようにするすべてのアプリケーションについて、Microsoft Entra 管理センターでシングル サインオン (SSO) を有効にします。 SSO が設定されている場合、ユーザーのエクスペリエンスはシームレスになり、資格情報を入力する必要がなくなります。 詳細については、「[Microsoft Entra ID のシングル サインオン オプション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on#single-sign-on-options)」を参照してください。

アプリケーションはリンクされた SSO オプションを使用して追加できます。 既存の Web アプリケーションの URL にリンクするアプリケーション タイルを構成します。 リンクされた SSO を使用すると、すべてのアプリケーションを Microsoft Entra SSO に移行することなく、マイ アプリ ポータルにユーザーを誘導することができます。 ユーザーのエクスペリエンスが中断しないように、Microsoft Entra SSO が構成されたアプリケーションに段階的に移行します。

詳細については、[リンクされたシングル サインオンをアプリケーションに追加する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-linked-sign-on)に関する記事を参照してください。

### コレクションを作成する

既定では、すべてのアプリケーションが 1 つのページにまとめて表示されます。 コレクションを使用して関連するアプリケーションをグループ化し、別々のタブで表示すれば、アプリケーションが見つけやすくなります。 たとえば、コレクションを使用して、特定の担当業務、タスク、プロジェクトなどに関連したアプリケーションの論理グループを作成します。 ユーザーがアクセスできるすべてのアプリケーションが既定のアプリ コレクションに表示されますが、ユーザーはコレクションからアプリケーションを削除できます。

ユーザーは、次のようにしてエクスペリエンスをカスタマイズすることもできます。

- 自分用のアプリケーション コレクションを作成する
- アプリケーション コレクションを非表示にしたり、並べ替えたりする

ユーザーまたは管理者は、アプリケーションがマイ アプリ ポータルに表示されないようにすることができます。 非表示のアプリケーションには、Microsoft 365 ポータルなどの他の場所から引き続きアクセスできます。 マイ アプリ ポータルからアクセスできるのは、ユーザーがアクセスできる 950 個のアプリケーションだけです。

詳細については、[マイ アプリ ポータルでコレクションを作成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/access-panel-collections)に関する記事を参照してください。

重要

ドメイン フェデレーションがあり、認証のためにユーザーが外部フェデレーション エンドポイントにリダイレクトされる場合、マイ アプリへの要求に "domain\_hint=example.com" などのドメイン ヒント URL パラメーターが含まれている必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/overview-application-gallery"} -->
## Microsoft Entra アプリケーション ギャラリーの概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-application-gallery
- Service: entra-id / enterprise-apps
- Article date: 2026-01-05
- Summary: Microsoft Entra アプリケーション ギャラリーを調べて、事前構成済みの SSO とユーザー プロビジョニングとシームレスな SaaS 統合を実現します。 クラウド アプリのデプロイを強化します。

Microsoft Entra アプリケーション ギャラリーは、Microsoft Entra ID で事前に統合されたサービスとしてのソフトウェア (SaaS) [アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals) のコレクションです。 このコレクションには、 [シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol) と [自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)のデプロイと構成を簡単に行える何千ものアプリケーションが含まれています。

テナントにサインインしたときにギャラリーを見つけるには、**Entra ID**&gt;、**エンタープライズ アプリケーション**&gt;、**すべてのアプリケーション**&gt;、**新しいアプリケーション**に移動します。

ギャラリーから入手できるアプリケーションは、ユーザーがインターネット経由でクラウドベースのアプリケーションに接続して使用できるようにする SaaS モデルに従います。 一般的な例としては、メール、予定表作成、オフィス ツール (Microsoft Office 365) などがあります。

ギャラリーで利用可能なアプリケーションを使用することで実現する利点を次に示します。

- ユーザーは、アプリケーションに対して考えられる最良の SSO エクスペリエンスを見つけられます。
- アプリケーションの構成は簡単で最小限です。
- クイック検索で必要なアプリケーションが検索されます。
- Free、Basic、Premium すべての Microsoft Entra ユーザーがこのアプリケーションを使用できます。
- ユーザーは、ギャラリー アプリケーションのオンボードに使用できる [ステップ バイ ステップの構成チュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) を簡単に見つけることができます。
- 組織は、セキュリティ、コンプライアンス、法律、および一般的なカテゴリ全体で 90 を超えるリスク要因を評価する計算されたリスク スコアを使用して、アプリケーションのセキュリティを評価できます。

### ギャラリー内のアプリケーション

ギャラリーには、Microsoft Entra ID に事前に統合されている何千ものアプリケーションが含まれています。 ギャラリーを使用する場合は、特定のクラウドプラットフォームのアプリケーションやおすすめのアプリケーションを使用するか、または使用するアプリケーションを検索します。

#### アプリケーションの検索

おすすめのアプリケーションで探しているアプリケーションが見つからない場合は、名前を指定して特定のアプリケーションを検索できます。

[Image: Microsoft Entra 管理センターの Microsoft Entra アプリケーション ギャラリー ウィンドウの検索オプションを示すスクリーンショット。]

アプリケーションを検索するときに、シングル サインオン オプション、自動プロビジョニング、カテゴリなどの特定のフィルターを指定することもできます。

- **シングル サインオン オプション** – SAML、OpenID Connect (OIDC)、パスワード、またはリンクされた SSO オプションをサポートするアプリケーションを検索できます。 これらのオプションの詳細については、「 [Microsoft Entra ID でのシングル サインオン展開の計画」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment)参照してください。
- **ユーザー アカウント管理** – 使用可能な唯一のオプションは、 [自動プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)です。
- **カテゴリ** – アプリケーションをギャラリーに追加すると、特定のカテゴリに分類できます。 **ビジネス管理**、**コラボレーション**、**教育**など、多くのカテゴリを利用できます。
- **リスク スコア** – 計算されたセキュリティ リスク スコアでアプリケーションを 1 (最高リスク) から 10 (最も低いリスク) に表示します。 このスコアは、組織のセキュリティ要件を満たすアプリケーションを識別するのに役立ちます。
- **セキュリティ リスク要因** – 多要素認証、管理者監査証跡、ユーザー監査証跡、アプリケーションで使用されるデータを保護するその他のセキュリティ標準などの特定のセキュリティ対策を満たすアプリケーションを検索します。
- **コンプライアンス リスク要因** – SOC 2、ISO 27001、HIPAA、その他の規制要件など、コンプライアンス標準と認定を受けるアプリケーションに絞り込み、アプリケーションが業界のベスト プラクティスを確実に満たしていることを確認します。

Note

[外部テナント](https://learn.microsoft.com/ja-jp/entra/external-id/customers/overview-customers-ciam)では、エンタープライズ アプリケーションはサポートされていますが、アプリケーション ギャラリー カタログは使用できません。 外部テナントでエンタープライズ アプリケーションを検索して追加するには、[**新しいアプリケーション**] を選択&gt;**独自のアプリケーションを作成**し、検索バーにアプリの名前を入力し、表示されたら一覧から選択します。

#### クラウド プラットフォーム

AWS、Google、Oracle などの主要なクラウド プラットフォームに固有のアプリケーションは、適切なプラットフォームを選択することで見つけることができます。

[Image: Microsoft Entra 管理センターの Microsoft Entra アプリケーション ギャラリー ウィンドウのクラウド アプリケーション オプションを示すスクリーンショット。]

#### オンプレミスのアプリケーション

オンプレミス アプリケーションは、5 つの方法で Microsoft Entra ID に接続することができます。 1 つ目は、シングル サインオン用として、Microsoft Entra アプリケーション プロキシを使用することです。 アプリケーションで SAML または Kerberos 経由のシングル サインオンがサポートされていれば、Microsoft Entra ギャラリーのオンプレミス セクションから、次のタスクを実行できます。

- オンプレミスのアプリケーションへのリモート アクセスを有効にするようアプリケーション プロキシを構成します。
- アプリケーション プロキシを使用してオンプレミス アプリケーションへのリモート アクセスをセキュリティで保護する方法の詳細については、ドキュメントを参照してください。
- 作成したプライベート ネットワーク コネクタを管理する。

[Image: Microsoft Entra 管理センターの Microsoft Entra アプリケーション ギャラリー ウィンドウのオンプレミス アプリケーション オプションを示すスクリーンショット。]

アプリケーションで Kerberos を使用し、グループ メンバーシップも必要な場合は、Microsoft Entra ID の対応するグループから Windows Server AD グループを設定できます。 詳細情報については、「[Microsoft Entra クラウド同期を使ったグループ書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/group-writeback-cloud-sync)」を参照してください。

2 つ目は、プロビジョニング エージェントを使用して、独自のユーザー ストアを持ち、Windows Server AD に依存しないオンプレミス アプリケーションにプロビジョニングすることです。 [SCIM をサポートするオンプレミス アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-scim-provisioning)、[SQL データベース](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure)を使用するアプリケーション、[LDAP ディレクトリ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)を使用するアプリケーション、[または SOAP または REST プロビジョニング API をサポートするオンプレミス アプリケーションへのプロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-web-services-connector)構成できます。

3 つ目は、アプリごとの接続用にグローバル セキュア アクセス アプリを構成することで、Microsoft Entra Private Access を使用することです。 詳細については、「 [Microsoft Entra Private Access について学習](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-private-access)する」を参照してください。

4 つ目は、アプリケーション独自のコネクタを使用することです。 [`SAP S/4HANA On-premise`](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-sap-s-4hana-on-premise) を持っている場合なら、Microsoft Entra ID から SAP Cloud Identity Directory にユーザーをプロビジョニングします。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内のユーザーを、ダウンストリームの SAP アプリケーション (たとえば `SAP S/4HANA On-Premise`) に SAP クラウド コネクタ経由でプロビジョニングします。 詳細については、 [SAP ソース アプリとターゲット アプリを使用したユーザー プロビジョニングのための Microsoft Entra のデプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)を参照してください。

5 つ目は、サード パーティの統合テクノロジを使用することです。 アプリケーションが SCIM などの標準をサポートしていない場合、パートナーは、Microsoft Entra ID をオンプレミス アプリケーションなど追加のアプリケーションと統合するために、カスタム ECMA コネクタと SCIM ゲートウェイを使用しています。 詳細については、 [使用可能なパートナー主導の統合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/partner-driven-integrations#available-partner-driven-integrations)の一覧を参照してください。

#### おすすめのアプリケーション

Microsoft Entra ギャラリーを開くと、既定では、おすすめのアプリケーションのコレクションが一覧表示されます。 各アプリケーションには記号が付いており、フェデレーション SSO または自動プロビジョニングのどちらをサポートしているかを識別できます。

[Image: Microsoft Entra 管理センターの Microsoft Entra アプリケーション ギャラリー ウィンドウの注目のアプリケーションを示すスクリーンショット。]

- **フェデレーション SSO** - 複数の ID プロバイダー間で動作するように [SSO](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) を設定すると、フェデレーションが行われます。 フェデレーション プロトコルに基づく SSO の実装を使用すると、セキュリティ、信頼性、ユーザー エクスペリエンス、実装が向上します。 一部のアプリケーションでは、SAML ベースまたは OIDC ベースのフェデレーション SSO が実装されています。 SAML アプリケーションの場合、[作成] を選択すると、アプリケーションがテナントに追加されます。 OIDC アプリケーションの場合、管理者はまずアプリケーションの Web サイトにサインアップまたはサインインして、アプリケーションを Microsoft Entra ID に追加する必要があります。
- **プロビジョニング** - SaaS [アプリケーション プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) に対する Microsoft Entra ID は、ユーザーがアクセスする必要がある SaaS アプリケーションでユーザー ID とロールを自動的に作成することを指します。

Note

リンクされたサインオン ギャラリー アプリケーションでは、[作成] ボタンが淡色表示されます。 [独自のアプリケーションの作成] オプションで使用するリンクされたサインオン URL が提供されます。

[ **作成** ] ボタンは、特定のギャラリー アプリに対してデザインによって無効に表示される場合があります。 これは、リンクベースの SSO アプリケーションの場合、2 つのシナリオで発生します。 これらのテンプレートはリンク専用であり、Microsoft Entra ID での新しいアプリまたはサービス プリンシパルの作成はサポートされていません。 サービス プロバイダーによって管理されている外部 URL にユーザーをリダイレクトします。 Microsoft Entra オブジェクトは作成されないため、ボタンは意図的に使用できません。

2 つ目は、ギャラリー アプリケーションがテナントごとに 1 つのインスタンスに制限されるため、テナントにアプリが既に存在する場合です。 どちらの場合も、無効になっている **[作成** ] ボタンが予期される動作です。

### アプリケーション リスク スコアについて

Microsoft Defender for Cloud Apps は、ギャラリー内の SaaS アプリケーションにリスク スコアを割り当てて、組織がセキュリティ体制を評価し、情報に基づいた導入の決定を行うのに役立ちます。 リスク スコア情報へのアクセスには、 [Microsoft Entra Suite](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing) または [Microsoft Entra Internet Access](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-internet-access) ライセンスが必要です。

各アプリケーションのスコアは 1 から 10 で、1 は最も高いリスクを示し、10 は最も低いリスクを示します。 スコアは、次の 4 つのリスク カテゴリの加重平均を使用して計算されます。

- **一般**: 会社の安定性、ドメインの年齢、人気
- **セキュリティ**: 暗号化方法、多要素認証、監査証跡
- **コンプライアンス**: SOC 2、ISO 27001、HIPAA、PCI などの標準
- **法的**: データ保護ポリシーと規制コンプライアンス

スコアリング モデルは、公開されているデータ、ベンダーの開示、および観察されたセキュリティ プラクティスから派生した 90 を超えるリスク要因を評価します。

このリスク評価機能は、IT 管理者が潜在的なセキュリティの脆弱性を特定し、組織のアプリケーションを選択する際にデータドリブンの意思決定を行うのに役立ちます。

アプリケーション所有者は、ギャラリーに移動して、リスク スコアの更新を要求できます。&gt; 更新プログラムが必要なアプリを選択する -&gt; 更新プログラムが必要な特定のリスク要因までスクロールします。&gt; リスク要因名の右側にあるフィードバック シンボルを選択します。&gt; スコア更新要求などのオプションを使用 **して Microsoft フォームにフィードバックを送信** します。 古いアプリ データ、または新しいリスク要因の提案 -&gt; 要求された変更に関する詳細情報を提供し、要求を送信します。

Note

このプロセスを通じて送信されたフィードバックは、Microsoft Defender for Cloud Apps に送信されます。これにより、アプリケーションのリスク スコアとデータに必要な更新がレビューされ、行われます。

[Image: 個々のリスク要因に関するフィードバック オプションを含むアプリケーション リスク スコアの詳細を示すスクリーンショット。]

リスク スコアリング手法の詳細と、Microsoft Defender for Cloud Apps でスコア更新を要求する方法については、「 [クラウド アプリを検索してリスク スコアを計算](https://learn.microsoft.com/ja-jp/defender-cloud-apps/risk-score)する」を参照してください。 [List applicationTemplates API](https://learn.microsoft.com/ja-jp/graph/api/applicationtemplate-list) を使用して、アプリケーション テンプレートとそのリスク スコアにプログラムでアクセスすることもできます。

### 独自のアプリケーションの作成

ウィンドウの上部付近にある [ **独自のアプリケーションの作成** ] リンクを選択すると、次の選択肢を一覧表示する新しいウィンドウが表示されます。

- **Microsoft Entra ID と統合するアプリケーションを登録する (開発中のアプリ)** - この選択は、OpenID Connect と Microsoft Entra ID を使用するアプリケーションの統合に取り組みたい開発者を対象としています。 この選択では、ギャラリーにアプリケーションを発行する機会はありません。 統合に取り組むのは開発目的のみです。 詳細については、「アプリケーションの [OIDC ベースのシングル サインオンを設定する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)参照してください。
- **オンプレミス アプリケーションへのセキュリティで保護されたリモート アクセス用にアプリケーション プロキシを構成** する – この選択は、管理者がアプリケーション プロキシに接続して、オンプレミスでホストされている Web アプリケーションに対して SSO とセキュリティで保護されたリモート アクセスを有効にすることを目的とします。 詳細については、「 [Microsoft Entra アプリケーション プロキシとは」](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)を参照してください。

### ギャラリーに追加するアプリを要求する

アプリケーションを Microsoft Entra ID と正常に統合し、徹底的にテストしたら、それをギャラリーへ追加するよう要求を提起できます。 ポータルからギャラリーへのアプリケーションの発行はサポートされていませんが、それを追加するよう要求するために従うことができるプロセスがあります。 ギャラリーへの発行の詳細については、[ [Request new gallery application\]\(新しいギャラリー アプリケーションの要求](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)\) を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/overview-assign-app-owners"} -->
## エンタープライズ アプリケーションの所有権の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-assign-app-owners
- Service: entra-id / enterprise-apps
- Article date: 2024-12-06
- Summary: 既定の割り当て、構成の管理、所有者なしのアプリの効果的な処理など、Microsoft Entra IDでのアプリケーションの所有権について説明します。

Microsoft Entra IDにアプリケーションを登録したユーザーは、アプリケーション所有者として自動的に追加されます。 エンタープライズ アプリケーションの既定の所有権は、管理者ロールのないユーザーが新しいアプリケーション登録を作成する場合にのみ割り当てられます。

クラウド アプリケーション管理者がエンタープライズ アプリケーションを通じてサービス プリンシパルの所有者としてユーザーを割り当てると、そのユーザーはシングルテナント OpenID Connect (OIDC) アプリケーションと Security Assertion Markup Language (SAML) アプリケーションのアプリケーション所有者としても追加されます。

その他のすべてのシナリオでは、所有権は既定ではエンタープライズ アプリケーションに割り当てされません。 ユーザーはエンタープライズ アプリケーションの所有者として割り当てることができますが、グループを所有者として割り当てることはできません。

ユーザーは、Microsoft Entra IDのエンタープライズ アプリケーションの所有者として、アプリケーションの組織固有の構成を管理できます。 この構成には、シングル サインオン、プロビジョニング、ユーザー割り当てが含まれます。 所有者は、他の所有者を追加または削除することもできます。

アプリケーション管理者とは異なり、所有者は自分が所有するエンタープライズ アプリケーションのみを管理できます。 所有者は、アプリケーション管理者と同じアクセス許可を持ち、個々のアプリケーションを対象とします。 アプリケーションの所有者が持つアクセス許可の詳細については、「 [所有権のアクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)」を参照してください。

注

アプリケーションのアクセス許可が所有者よりも多い場合があります。 この状況は、所有者がユーザーとしてアクセスできる権限に対する特権の昇格です。 その場合、アプリケーション所有者は、アプリケーションの偽装中にユーザーまたは他のオブジェクトを作成または更新できます。 所有者への特権の昇格により、アプリケーションのアクセス許可によっては、セキュリティ上の問題が発生する場合があります。

現在、バックグラウンド アプリケーションとサービス プリンシパル オブジェクトの設定の依存関係のため、Microsoft Entra 管理センター (Microsoft Graph API や PowerShell など) 以外のメソッドを使用して追加されたアプリケーション所有者は、一部のエンタープライズ アプリケーション設定を管理できません。 これらの設定には、属性と要求、構成された SAML 証明書のプロパティ、またはトークン暗号化の設定が含まれます。

### FAQ

**所有者が組織に所属しなくなったアプリケーションで何を行う必要がありますか?**

テナントに所有者のいないアプリケーションがある場合は、アプリケーションの監査ログにアクセスして、アプリケーションの構成に関わっている可能性のある他のユーザーを調査することができます。 ただし、監査ログが保存されている期間には制限があります。 [Microsoft Entra監査ログレポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention)を参照してください。

[ **ロールと管理者** ] タブに移動して、アプリケーションのアクセス許可をスコープとする他のユーザーも表示される場合があります。アプリケーションを所有する適切なユーザーを見つけたら、組織内の高い特権を持つ管理者ロールを持つユーザーが、アプリケーションの新しい所有者を割り当てることができます。 「[エンタープライズ アプリケーション所有者の割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-app-owners)」をご覧ください。

ベスト プラクティスとして、環境内のアプリケーションを事前に監視して、可能な限り少なくとも 2 人の所有者が存在することを確認することをお勧めします。 この監視は、所有者のないアプリの状況を回避するのに役立ちます。

さらに、アプリケーション オブジェクトの `serviceManagementReference` プロパティを使用して、エンタープライズ サービスまたは資産管理データベースからチームの連絡先情報を参照する必要があります。 `serviceManagementReference`プロパティを使用すると、個人が組織を離れた場合でも、チームの連絡先が確保されます。

**組織内で所有者がいない、または所有者がいない恐れのあるエンタープライズ アプリケーションを見つけるにはどうすればよいですか?**

Microsoft Graph API を使用して所有者のないエンタープライズ アプリ (または所有者が 1 人のみのアプリ) を識別する方法については、「[所有者レス アプリケーションの一覧](https://learn.microsoft.com/ja-jp/graph/tutorial-applications-basics#manage-application-ownership)を参照してください。

**エンタープライズ アプリケーションの所有者として自分自身を追加するにはどうすればよいですか?**

アプリケーションの既存の所有者は、他のユーザーを所有者として追加できます。 また、アプリケーション管理者やクラウド アプリケーション管理者などの特権ロールを持つユーザーは、組織内のアプリケーションに所有者を割り当てることができます。 管理者でない場合は、組織の管理者と協力し、アプリケーションの[所有者として自分を割り当てます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-app-owners)。

**自分が所有するすべてのアプリケーションを見つけるにはどうすればよいですか?**

1. **[エンタープライズ アプリケーション**] に移動し、[**すべてのアプリケーション**] を選択します。
2. [ **フィルターの追加]** を選択し、 **所有** アプリを使用して、自分または他のユーザーが所有するアプリを検索します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/plan-an-application-integration"} -->
## Microsoft Entra ID とアプリの統合の概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-an-application-integration
- Service: entra-id / enterprise-apps
- Article date: 2024-12-05
- Summary: この記事は、オンプレミスのアプリケーションおよびクラウド アプリケーションと Microsoft Entra ID を統合するための概要ガイドです。

この記事では、アプリケーションと Microsoft Entra ID を統合するプロセスの概要を示します。 以降の各セクションには、このファースト ステップ ガイドのどの部分が自分に関連するかを特定できるように、より詳細な記事の簡単な概要が含まれています。

詳細な展開計画をダウンロードするには、次のステップを参照してください。

### インベントリの取り込み

アプリケーションを Microsoft Entra ID と統合する前に、自分がどこにいるか、どこに行きたいかを知る必要があります。 次の質問は、Microsoft Entra のアプリケーション統合プロジェクトについて考える際に役立つように設計されています。

#### アプリケーション インベントリ

- すべてのアプリケーションがどこに存在するか。 どのユーザーがそのアプリケーションを所有しているか。
- どの種類の認証がアプリケーションで必要とされるか。
- どのユーザーがどのアプリケーションにアクセスする必要があるか。
- 新しいアプリケーションをデプロイする必要があるか。
    - 社内で開発して Azure コンピューティング インスタンスにデプロイするか。
    - Azure アプリケーション ギャラリーで利用できるものを使用するか。

#### ユーザーとグループのインベントリ

- どこにユーザー アカウントが存在するか。
    - オンプレミスの Active Directory
    - Microsoft Entra ID
    - 自社で所有する独立したアプリケーション データベース内
    - 承認されていないアプリケーション内
    - ここに示されたオプションすべて
- 個々のユーザーは現在どのようなアクセス許可とロールの割り当てを所有しているか。 アクセス権を見直す必要があるか、それともユーザーのアクセス権とロールの割り当てが適切であることを確認済みか。
- オンプレミスの Active Directory でグループが既に確立されているか。
    - グループをどのように編成するか。
    - だれがグループのメンバーか。
    - グループは現在どのようなアクセス許可やロールの割り当てを所有しているか。
- 統合する前にユーザーやグループのデータベースをクリーンアップする必要があるか。 (これは重要な質問です。ゴミを入れるとゴミが出てきます。)

#### アクセス管理インベントリ

- アプリケーションへのユーザーのアクセスを現在どのように管理しているか。 変更する必要があるか。 [Azure RBAC](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-portal) など、他の方法でアクセスを管理することを検討したか。
- どのユーザーが何にアクセスする必要があるか。

一部の質問にはあらかじめ回答できないこともありますが、それでもかまいません。 このガイドにより、そのような質問の一部に回答し、一部に情報に基づいて判断できるようになります。

#### 承認されていないクラウド アプリケーションを Cloud Discovery で検出する

前のセクションで説明したように、組織が今まで管理しているアプリケーションが存在する可能性があります。 インベントリ プロセスの一環として、承認されていないクラウド アプリケーションを見つけることができます。 「[Cloud Discovery の設定](https://learn.microsoft.com/ja-jp/defender-cloud-apps/set-up-cloud-discovery)」を参照してください。

### Microsoft Entra ID を使用したアプリケーションの統合

次の記事では、アプリケーションを Microsoft Entra ID と統合するさまざまな方法について説明し、ガイダンスをいくつか示します。

- [Azure アプリケーション ギャラリーのアプリケーションの使用](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)
- [SaaS アプリケーションのチュートリアルの一覧の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)

### Microsoft Entra ギャラリーに記載されていないアプリの機能

組織に既に存在する任意のアプリケーション、または Microsoft Entra ギャラリーにまだ含まれていないベンダーのサードパーティ製アプリケーションを追加できます。 [使用許諾契約書](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)に応じて、以下の機能を使用することができます。

- [Security Assertion Markup Language (SAML) 2.0](https://wikipedia.org/wiki/SAML_2.0) ID プロバイダーをサポートする任意のアプリケーションのセルフサービス統合 (SP または IdP によって開始)
- [パスワードベースの SSO](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment#password-based-sso)
- [ユーザー プロビジョニング用の System for Cross-Domain Identity Management (SCIM) プロトコル](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups)を使用するアプリケーションのセルフサービス接続
- [Office 365 アプリ ランチャー](https://support.microsoft.com/office/meet-the-microsoft-365-app-launcher-79f12104-6fed-442f-96a0-eb089a3f476a)または[マイ アプリ](https://myapplications.microsoft.com/)での任意のアプリケーションへのリンクの追加機能

カスタム アプリケーションと Microsoft Entra ID を統合する方法に関する開発者向けガイダンスをお探しの場合は、[Microsoft Entra ID の認証シナリオ](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-vs-authorization)に関するページを参照してください。 [OpenId Connect/OAuth](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols) などの最新のプロトコルを使用してユーザーを認証するアプリを開発する場合は、Microsoft ID プラットフォームに登録します。 Azure portal の [アプリ登録エクスペリエンスを](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) 使用して登録できます。

#### 認証の種類

アプリケーションごとに異なる認証要件がある場合があります。 Microsoft Entra ID では、証明書の署名に、パスワードによるシングル サインオンだけでなく、SAML 2.0、WS-Federation、OpenID Connect プロトコルを使用するアプリケーションを使用することができます。 アプリケーション認証の種類の詳細については、「Microsoft Entra ID および[パスワード ベース](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)[のシングル サインオンでのフェデレーション シングル サインオンの証明書の管理」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)参照してください。

#### Microsoft Entra アプリケーション プロキシを使用した SSO の有効化

Microsoft Entra アプリケーション プロキシを使用すると、プライベート ネットワーク内に置かれたアプリケーションへの、任意の場所および任意のデバイスからのアクセスを安全に許可することができます。 環境内にプライベート ネットワーク コネクタをインストールすると、Microsoft Entra ID で簡単に構成できます。

#### カスタム アプリケーションの統合

カスタム アプリケーションを Azure アプリケーション ギャラリーに追加する場合は、「[アプリを Microsoft Entra アプリ ギャラリーに公開する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)」を参照してください。

### アプリケーションへのアクセスの管理

次の記事では、アプリケーションが Microsoft Entra Connectors と Microsoft Entra ID を使用して Microsoft Entra ID と統合された後で、アプリケーションへのアクセスを管理する方法について説明します。

- [Microsoft Entra ID を使用したアプリへのアクセスの管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management)
- [Microsoft Entra コネクタを使用した自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)
- [アプリケーションへのユーザーの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)
- [アプリケーションへのグループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)
- [アカウントの共有](https://learn.microsoft.com/ja-jp/entra/identity/users/users-sharing-accounts)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/plan-sso-deployment"} -->
## シングル サインオンの展開を計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment
- Service: entra-id / enterprise-apps
- Article date: 2025-04-30
- Summary: Microsoft Entra ID でシングル サインオンの展開を計画します。 ロールの割り当て、証明書の管理、ライセンスを合理化して、アクセスを中断しないようにします。

この記事では、Microsoft Entra ID でのシングル サインオン (SSO) のデプロイを計画するために使用できる情報を提供します。 Microsoft Entra ID でお使いのアプリケーションを使用した SSO のデプロイを計画する場合は、次の質問について考える必要があります。

- アプリケーションを管理するには、どのような管理者ロールが必要ですか?
- Security Assertion Markup Language (SAML) アプリケーション証明書を更新する必要がありますか?
- SSO の実装に関連する変更を誰に通知する必要がありますか?
- アプリケーションの効果的な管理を確保するためにどのライセンスが必要ですか?
- 共有ユーザー アカウントとゲスト ユーザー アカウントは、アプリケーションへのアクセスに使用されますか?
- SSO デプロイのオプションを理解していますか?

### 管理役割

Microsoft Entra ID 内で必要なタスクを実行するために使用できるアクセス許可が最も少ないロールを常に使用します。 使用可能なさまざまなロールを確認し、アプリケーションの各ペルソナのニーズを解決するための適切なロールを選択します。 一部のロールは、デプロイの完了後に一時的に適用して削除する必要があります。

| ペルソナ | 役割 | Microsoft Entra ロール (必要な場合) |
| --- | --- | --- |
| ヘルプ デスク管理者 | 階層 1 のサポートでは、サインイン ログを表示して問題を解決します。 | 無し |
| アイデンティティ管理者 | 問題に Microsoft Entra ID が関係する場合の構成とデバッグ | クラウド アプリケーション管理者 |
| アプリケーション管理者 | アプリケーションでのユーザー認証、アクセス許可を持つユーザーの設定 | 無し |
| インフラストラクチャ管理者 | 証明書のロールオーバー所有者 | クラウド アプリケーション管理者 |
| ビジネス所有者/利害関係者 | アプリケーションでのユーザー認証、アクセス許可を持つユーザーの設定 | 無し |

Microsoft Entra 管理ロールの詳細については、 [Microsoft Entra の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### 証明 書

SAML アプリケーションでフェデレーションを有効にすると、Microsoft Entra ID によって、既定で 3 年間有効な証明書が作成されます。 必要に応じて、その証明書の有効期限をカスタマイズできます。 有効期限が切れる前に証明書を更新するプロセスがあることを確認します。

Microsoft Entra 管理センターでその証明書の期間を変更します。 有効期限を文書化し、証明書の更新を管理する方法を確認してください。 署名証明書のライフサイクルの管理に関連する適切なロールと電子メール配布リストを特定することが重要です。 次のロールが推奨されます。

- アプリケーションでユーザーのプロパティを更新する所有者
- アプリケーションのトラブルシューティングサポート担当者が待機中
- 証明書関連の変更通知の詳細に監視された電子メール配布リスト

Microsoft Entra ID とアプリケーションの間の証明書の変更を処理する方法のプロセスを設定します。 このプロセスを実施することで、証明書の期限切れまたは強制証明書のロールオーバーによる停止を防止または最小限に抑えることができます。 詳細については、「[Microsoft Entra ID でフェデレーション シングル サインオンの証明書を管理する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)」を参照してください。

### 通信

通信は、新しいサービスの成功に不可欠です。 今後のエクスペリエンスの変更について、ユーザーに事前に通知します。 変更が行われるタイミングと、問題が発生した場合にサポートを受ける方法を伝えます。 ユーザーが SSO 対応アプリケーションにアクセスする方法のオプションを確認し、選択内容に合わせて通信を作成します。

コミュニケーション計画を実装します。 変更が来て、到着したら、今何をすべきかをユーザーに知らせる必要があることを確認します。 また、支援を求める方法に関する情報を必ず提供してください。

### ライセンス

アプリケーションが次のライセンス要件の対象となっていることを確認します。

- **Microsoft Entra ID ライセンス** - 事前に設定されたエンタープライズ アプリケーションの SSO は無料です。 ただし、ディレクトリ内のオブジェクトの数とデプロイする機能には、より多くのライセンスが必要な場合があります。 ライセンス要件の完全な一覧については、 [Microsoft Entra の価格](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)を参照してください。
- **アプリケーション ライセンス** - ビジネス ニーズを満たすために、アプリケーションに適したライセンスが必要です。 アプリケーション所有者と連携して、アプリケーションに割り当てられたユーザーに、アプリケーション内での自分のロールに適したライセンスが設定されているかどうかを判断します。 Microsoft Entra ID がロールに基づいて自動プロビジョニングを管理する場合、Microsoft Entra ID で割り当てられたロールは、アプリケーション内で所有されているライセンスの数と一致する必要があります。 アプリケーションで所有されているライセンスの数が正しくないと、ユーザー アカウントのプロビジョニングまたは更新中にエラーが発生する可能性があります。

### 共有アカウント

サインインの観点からは、共有アカウントを持つアプリケーションは、個々のユーザーにパスワード SSO を使用するエンタープライズ アプリケーションと異なるわけではありません。 ただし、共有アカウントを使用するためのアプリケーションの計画と構成には、さらに多くの手順が必要です。

- ユーザーと協力して、次の情報を文書化します。
    - アプリケーションを使用する組織内のユーザーのセット。
    - ユーザーのセットに関連付けられているアプリケーション内の既存の資格情報のセット。
- ユーザー セットと資格情報の組み合わせごとに、要件に基づいてクラウドまたはオンプレミスにセキュリティ グループを作成します。
- 共有資格情報をリセットします。 Microsoft Entra ID にアプリケーションがデプロイされると、個々のユーザーには共有アカウントのパスワードが不要になります。 パスワードは Microsoft Entra ID で保存されるので、長く複雑なものに設定する必要があります。
- アプリケーションがサポートしている場合は、パスワードの自動ロールオーバーを構成します。 こうすることで、初期セットアップを行った管理者も共有アカウントのパスワードを知りません。

### シングル サインオン オプション

SSO 用にアプリケーションを構成するには、いくつかの方法があります。 SSO 方法の選択は、アプリケーションが認証用に構成される方法によって異なります。

- クラウド アプリケーションでは、OpenID Connect、OAuth、SAML、パスワードベース、またはリンクを SSO に使用できます。 シングル サインオンを無効にすることもできます。
- オンプレミスアプリケーションでは、パスワードベース、統合 Windows 認証、ヘッダーベース、または SSO 用のリンクを使用できます。 オンプレミスの選択肢は、アプリケーションの[アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)が構成されている場合に機能します。

このフローチャートは、状況に最適な SSO 方法を決定するのに役立ちます。

[Image: シングル サインオン方法の決定フローチャートの画像。]

次の SSO プロトコルを使用できます。

- **OpenID Connect と OAuth** - 接続しているアプリケーションでサポートされている場合は、OpenID Connect と OAuth 2.0 を選択します。 詳細については、「[Microsoft ID プラットフォームにおける OAuth 2.0 プロトコルと OpenID Connect プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols)」を参照してください。 OpenID Connect SSO を実装する手順については、「 [Microsoft Entra ID でアプリケーションの OIDC ベースのシングル サインオンを設定](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)する」を参照してください。
- **SAML** - OpenID Connect または OAuth を使用しない既存のアプリケーションでは、可能な限り SAML を選択します。 詳細については、「 [シングル サインオン SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)」を参照してください。
- **パスワードベースの** - アプリケーションに HTML サインイン ページがある場合は、パスワードベースを選択します。 パスワードベースの SSO はパスワード保管とも呼ばれます。 パスワードベースの SSO を使用すると、ID フェデレーションをサポートしていない Web アプリケーションへのユーザー アクセスとパスワードを管理できます。 これは、複数のユーザーが 1 つのアカウント (組織のソーシャル メディア アプリ アカウントなど) を共有する必要がある場合にも便利です。

    パスワードベースの SSO では、サインインにユーザー名フィールドとパスワード フィールド以上のものを必要とするアプリケーションに対して、複数のサインイン フィールドを必要とするアプリケーションがサポートされます。 ユーザーが自分の資格情報を入力したときにマイ アプリに表示されるユーザー名フィールドとパスワード フィールドのラベルをカスタマイズできます。 パスワードベースの SSO を実装する手順については、「 [パスワードベースのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-password-single-sign-on-non-gallery-applications)」を参照してください。
- **リンクされた** - アプリケーションが別の ID プロバイダー サービスで SSO 用に構成されている場合は、リンクを選択します。 リンクされたオプションを使用すると、ユーザーが組織のエンド ユーザー ポータルでアプリケーションを選択したときにターゲットの場所を構成できます。 Active Directory フェデレーション サービス (ADFS) など、現在フェデレーションを使用しているカスタム Web アプリケーションへのリンクを追加できます。

    また、ユーザーのアクセス パネルに表示する特定の Web ページや、認証を必要としないアプリへのリンクも追加できます。 リンクされたオプションでは、Microsoft Entra 資格情報を使用したサインオン機能は提供されません。 リンクされた SSO を実装する手順については、「 [リンクされたシングル サインオン」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-linked-sign-on)を参照してください。
- **無効** - SSO 用にアプリケーションを構成する準備ができていない場合は、無効な SSO を選択します。
- **統合 Windows 認証 (IWA)** - IWA を使用するアプリケーションまたはクレーム対応アプリケーションに対して IWA シングル サインオンを選択します。 詳細については、「 [アプリケーション プロキシを使用してアプリケーションにシングル サインオンするための Kerberos の制約付き委任」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso-with-kcd)。
- **ヘッダーベースの** - アプリケーションで認証にヘッダーを使用する場合は、ヘッダーベースのシングル サインオンを選択します。 詳細については、「 [ヘッダーベースの SSO](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-single-sign-on-with-headers)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/plan-sso-integration-isv"} -->
## Microsoft Entra ID (ISV) との SSO 統合を計画する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-integration-isv
- Service: entra-id / enterprise-apps
- Article date: 2026-06-04
- Summary: シングル サインオン (SSO) とMicrosoft Entra IDを統合するための準備を行う独立系ソフトウェア ベンダー (ISV) 向けの概要計画と意思決定ガイド。

Microsoft Entra ID でのシングル サインオン (SSO) の計画が必要です。 初期の決定は、アプリのアーキテクチャ、顧客のオンボード、長期的な維持管理に影響します。 このガイドは、独立系ソフトウェア ベンダー (ISV) が構築前にこれらの決定を行うのに役立ちます。

独立系ソフトウェア ベンダー (ISV) の SSO 計画は、組織の SSO とは異なります。 ISV として、アプリを 1 回設計し、それを多くの顧客テナントにデプロイします。 各テナントには、異なる要件、プロトコル、構成を設定できます。

このデプロイ アプローチは、"設計 1 回、テナントごとに構成する" モデルです。 意思決定は、多様な顧客をどの程度サポートしているかを早期に形成します。 計画が弱い場合は、オンボーディングの摩擦、サポート作業の増加、エンタープライズ売上の損失につながります。

計画を適切に行うために、まず基本を理解します。 背景については、「[シングル サインオン (SSO) とは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)と[「Microsoftの SSO モデル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/understand-microsoft-sso-model)について」を参照してください。

### ここから始める: ほとんどの SaaS ISV に推奨される既定値

最新の SaaS ISV の場合は、次の既定値を使用します。

- **マルチテナント アーキテクチャ**: スケーラブルな SaaS 分散の既定値。
- **OpenID Connect (OIDC):**新しい開発に推奨されるプロトコルです。
- **シングルページ アプリケーション (SPA) とモバイル アプリ**: OIDC を使用する必要があります。
- **SAML**: 特定の従来のエンタープライズ顧客の要件に対してのみ選択します。
- **シングルテナント アプリ**: Microsoft Entra アプリ ギャラリーの検証に失敗する。

これらの推奨される既定値は、最新の開発プラクティスとMicrosoft Entra検証の要件に一致します。 それらから逸脱した場合、検証中に手直しに直面することがよくあります。

Important

設計上の決定は、アプリが検証Microsoft Entra合格し、テスト ID を受け取るかどうかに直接影響します。 間違ったテナント モデルまたはプロトコルにより、多くの場合、再作業が発生します。

### Microsoft Entra IDの ISV SSO コンテキストについて

Microsoft Entra IDは構造化モデルを使用します。 アプリには、アプリケーション オブジェクトと呼ばれる 1 つのグローバル定義があります。これは、すべてのテナント間で共有されるアプリの登録を表します。 各テナントでは、アプリにはサービス プリンシパルと呼ばれるテナント固有のインスタンスもあります。 顧客が自分のテナントにお客様のアプリを追加すると、そのアプリのサービス プリンシパルを作成します。 サービス プリンシパルは、そのテナントの SSO 構成、ユーザー割り当て、プロトコル設定を保持します。

アプリケーション オブジェクトとサービス プリンシパルを分離することで、アプリはテナント間で異なる SSO プロトコルと構成をサポートでき、コア設計は変わりません。 各テナントは、他のデプロイに影響を与えずに、独自に SSO 設定を構成します。

**ISV に対する重大な影響:** プロトコル構成は、サービス プリンシパルに存在します。 そのため、アプリでは、テナント間のさまざまな SSO プロトコルと要求マッピングを許容する必要があります。 あるお客様は、特定の属性マッピングで SAML を使用できます。 別のユーザーは、要求が異なる OIDC を使用する場合があります。 アプリ アーキテクチャでは、このバリエーションを適切に処理する必要があります。

このモデルの詳細については、「 [アプリケーション オブジェクトとサービス プリンシパル](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals) 」および [「シングルテナント アプリとマルチテナント アプリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps)」を参照してください。

### テナント モデルを決定する

**ほとんどの SaaS ISV では、マルチテナント アーキテクチャが推奨される既定です。** アプリは、シングルテナント (1 つの組織) またはマルチテナント (多くの組織) として設計できます。 この選択は、SSO の設計、オンボードの複雑さ、およびビジネス モデルのスケーリングに大きく影響します。

**マルチテナント アプリケーション** (推奨) は、1 つのアプリ登録から多くの顧客組織にサービスを提供します。 1 回の登録でデプロイが簡素化され、スケーリングも容易になります。 マルチテナント アプリでは、テナントの分離と顧客ごとの構成を慎重に設計する必要があります。

**シングルテナント アプリケーションには、** 顧客ごとに個別のデプロイが必要です。 分離性は高くなりますが、運用作業が追加されます。 このアプローチは、特殊なエンタープライズまたはコンプライアンスのニーズに対してのみ選択します。

**検証と発行への影響:**

- シングルテナント アプリは、Microsoft Entra アプリ ギャラリーの発行の検証に失敗します。
- スケーラブルなエンタープライズ配布とギャラリーの包含には、マルチテナントが必要です。
- ギャラリーの公開には、多様な顧客にサービスを提供するためのマルチテナント アーキテクチャが必要です。

シングルテナント アーキテクチャとマルチテナント アーキテクチャのどちらを選択しても、顧客がアプリの使用を開始した後に変更するのは困難です。 完全なガイダンスについては、 [シングルテナントアプリとマルチテナント アプリに関](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps)するページを参照してください。

### アプリケーション アーキテクチャとサインイン パターンを定義する

アプリのアーキテクチャによって、サポートできる認証フローと SSO パターンが決まります。 アーキテクチャとプロトコルを一緒に選択し、慎重に選択します。 一致が不十分な場合、検証中にやり直しが発生します。

**推奨されるアーキテクチャとプロトコルのアラインメント:**

- **単一ページ アプリケーション (SPA)** → OIDC が必要 (クライアント側トークン管理)
- **モバイル アプリケーション →** OIDC が必要 (ネイティブ認証フロー)
- **最新の Web アプリケーション** → OIDC 推奨既定値 (サーバー側トークン処理)
- SAML 許容→**レガシ エンタープライズ アプリケーション** (XML ベースのフェデレーション)

**Web アプリ** では SAML または OpenID Connect を使用できますが、新しい開発では OIDC が推奨される既定値です。 シングルページ アプリケーションとモバイル アプリは、クライアントで実行され、最新の認証パターンを使用するため、OIDC で最適に動作します。

Note

誤って選択した場合: アーキテクチャとプロトコルの選択が不適切な場合、多くの場合、検証中に大幅なやり直しが必要になり、ギャラリーの発行が遅れる可能性があります。

アプリケーションの種類と認証パターンの詳細なガイダンスについては、[Microsoft ID プラットフォームアプリの種類](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-app-types)と [OAuth 2.0 承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)に関する記事を参照してください。

### プロトコル固有の計画に関する考慮事項を確認する

**ISV では、SSO 用に SAML 2.0 と OpenID Connect (OIDC) のどちらかを選択する必要があります。** ほとんどの最新の SaaS ISV では、OIDC が推奨される既定値です。 最新の開発プラクティスに一致し、最新のアプリ アーキテクチャをサポートし、より単純に統合します。

**特定のエンタープライズ顧客の要件またはレガシ統合のニーズに対してのみ SAML を選択します。** SAML はまだ広くサポートされており、一部のシナリオに適合していますが、通常、実装と構成にはさらに多くの作業が必要です。

**プロトコルの選択は、検証の成功とテスト ID の生成に直接影響します。** 推奨されるパターンとプロトコルを使用するアプリは、通常、Microsoft Entra検証に速く合格します。 標準以外の選択肢では、追加の検証サイクルが必要になる場合があります。

比較と決定のフレームワークの詳細については、「 [SAML と OpenID Connect: 適切な SSO プロトコルを選択する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/saml-vs-oidc-decision-guide)」を参照してください。

### 顧客の構成とオンボーディング エクスペリエンスを計画する

**構成はシンプルに保ちますが、顧客が必要とするカスタマイズを許可します。** 一部の値は、リダイレクト URI やアプリケーション識別子など、すべての顧客で同じです。 クレーム マッピングやユーザー属性など、顧客ごとに異なるものもあります。

**最初に自動化を設計する:**

- 可能な限り標準構成を自動化する
- 顧客固有の設定に関する明確なガイダンスを提供する
- 顧客が構成値を取得して検証する方法を計画する
- 複数の顧客シナリオでオンボード プロセスをテストする

オンボード設計が不十分な場合、顧客の導入とサポートのオーバーヘッドに直接影響します。 構成の概念については、 [アプリ登録の概念](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)、 [SAML メタデータのドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)、 [リダイレクト URI ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity-platform/reply-url)を参照してください。

### 要求、ID データ、承認マッピングを計画する

**必要な要求と省略可能な要求を早期に定義します。** 通常、ユーザー識別子などの一部の要求が必要です。 グループ メンバーシップやカスタム属性などのその他の属性は、省略可能または顧客固有の場合があります。

**顧客の変動性を計画する:**

- 使用できる ID データは顧客によって異なります
- 要求の形式と配信の基本設定はテナントによって異なります
- 最小限かつ豊富な要求シナリオでアプリケーションをテストする
- 省略可能な要求がない場合のドキュメント フォールバック動作

要求とトークンの概念については、[トークンと要求の概要](https://learn.microsoft.com/ja-jp/entra/identity-platform/security-tokens)、ID トークン、[およびセキュリティ トークン](https://learn.microsoft.com/ja-jp/entra/identity-platform/id-tokens)[に関する](https://learn.microsoft.com/ja-jp/entra/identity-platform/access-tokens)ページを参照してください。

### サインアウト、ライフサイクル管理、長期運用を計画する

**SSO は最初のサインインを超えています。** サインアウト、セッション管理、アカウントライフサイクルを早期に計画します。 多くの場合、これらのニーズは、エンタープライズ販売時に顧客の要件として生まれます。

**対処する主なシナリオ:**

- アプリケーション間でのシングル サインアウト
- 組織のポリシーとのセッション タイムアウトの調整
- アカウントの非アクティブ化とクリーンアップ
- トークンの更新と有効期限の処理

実装ガイダンスについては、SAML プロトコル [ドキュメントの OpenID Connect ログアウト ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc#send-a-sign-out-request) と SAML サインアウトリファレンスを [参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)。

### 検証とギャラリー公開の準備をする

**最初からギャラリーの要件を計画します。** Microsoft Entra アプリ ギャラリーに発行するには、特定の統合標準を満たし、検証テストに合格してテスト ID を取得する必要があります。

**検証の成功要因:**

- マルチテナント アーキテクチャ (必須)
- プロトコル コンプライアンス (検証を高速化するために OIDC をお勧めします)
- 標準認証フローとエラー処理
- 適切な要求とトークンの管理

**通常、確立されたパターンに従うアプリは、検証に速く合格します。** 計画に関する不十分な決定は、多くの場合、大幅なやり直しを必要とする検証エラーとして表示されます。

発行のガイダンスと検証の要件については、[Microsoft Entra アプリケーション ギャラリーのドキュメントを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)。

### SSO 計画準備チェックリスト

次の表を使用して、検証とギャラリーの発行の準備ができていることを確認します。 各項目は、テナント モデル、アプリケーション アーキテクチャ、プロトコルの選択、顧客の構成、要求、サインアウトという主要な SSO 計画の決定にマップされます。

#### アプリケーション設計チェックリスト

検証の前に、これらのアプリケーション設計の選択肢を確認します。

| 準備項目 | 共同作業の重要性 |
| --- | --- |
| マルチテナント モデルの確認 | Microsoft Entra アプリ ギャラリーは、マルチテナント アプリケーションのみを受け入れます。 |
| 定義されているアプリケーション アーキテクチャ (Web、SPA、モバイル、または API) | アーキテクチャによって、適用される認証フローが決まります。 |
| アーキテクチャに合わせて識別および調整された認証フロー | フローの配置が正しく行わないと、検証エラーが発生します。 |

#### プロトコルの選択チェックリスト

プロトコルの選択とその影響を確認します。

| 準備項目 | 共同作業の重要性 |
| --- | --- |
| OIDC が既定であることを確認済み *または* SAML についてビジネス上の理由が文書化されている | OIDC は推奨される既定値です。偏差には正当な理由が必要です。 |
| プロトコルの選択がアプリケーション アーキテクチャに合わせて調整される | SPA とモバイル アプリには OIDC が必要です。 |
| アプリケーションの種類に対して認識されるプロトコル固有の影響 | 検証段階での後期の再設計を回避します。 |

#### カスタマー エクスペリエンスチェックリスト

顧客のオンボードと構成のエクスペリエンスを確認します。

| 準備項目 | 共同作業の重要性 |
| --- | --- |
| 固定または顧客固有として分類された構成値 | オンボード手順とテナント モデルを駆動します。 |
| 顧客の複雑さを最小限に抑えるように設計されたオンボーディング プロセス | 導入の障壁が低いほど、テナント管理者による導入が進みます。 |
| 定義およびテストされた要求と ID データの要件 | 実行時の承認の不一致を防ぎます。 |

#### 検証と発行のチェックリスト

検証と公開の準備を確認します。

| 準備項目 | 共同作業の重要性 |
| --- | --- |
| 開発の早い段階で確認済みのギャラリー公開要件 | 提出前の再作業を回避します。 |
| 考慮される設計上の決定の検証の影響 | 一部の決定では、検証が完全にブロックされます。 |
| サインアウトとセッション管理のアプローチの計画 | コンプライアンスとクリーンなユーザー エクスペリエンスに必要です。 |
| テスト済みのアカウント ライフサイクル シナリオ | プロビジョニング解除時のエッジ ケースを捕捉します。 |

#### 事前検証チェックリスト

検証を開始する前に、この最終チェックを完了してください。

| 準備項目 | 共同作業の重要性 |
| --- | --- |
| 文書化および正当化されたすべての重要な決定 | スピードアップにより、業務の引き継ぎが円滑になり、手戻りを減らします。 |
| テスト ID 検証経路が確認されました | 発行フローに入る前に必須。 |
| 評価および最小化された再作業のリスク | 検証前の最終的な「実施/中止」の判断。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/prevent-domain-hints-with-home-realm-discovery"} -->
## 自動高速化サインインを無効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/prevent-domain-hints-with-home-realm-discovery
- Service: entra-id / enterprise-apps
- Article date: 2024-11-29
- Summary: ホーム領域検出ポリシーを使用してフェデレーション IDP への domain_hint の自動高速化を防ぐ方法について説明します。

この記事では、ホーム領域検出 (HRD) ポリシーを使用して、特定のドメインとアプリケーションの自動高速化サインインを無効にする方法について説明します。 このポリシーを構成すると、管理者はユーザーが常にマネージド資格情報を使用し、セキュリティを向上させ、一貫したサインイン エクスペリエンスを提供できるようにします。

ホーム領域検出ポリシー (HRD) により、管理者がユーザー認証の方法と場所を制御するための複数の方法が提供されます。 HRD ポリシーの `domainHintPolicy` セクションを使用すると、フェデレーション ユーザーを常に Microsoft Entra のサインイン ページにアクセスさせ、ドメイン ヒントによってフェデレーション IDP に自動高速化されないようにすることで、[FIDO](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-passwordless-security-key) などのクラウドで管理されている資格情報に移行させるのに役立ちます。 HRD ポリシーの詳細については、「[ホーム領域検出](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/home-realm-discovery-policy)」を参照してください。

このポリシーは、管理者がサインイン中にドメイン ヒントを制御または更新できない場合に必要です。 たとえば、`outlook.com/contoso.com` の場合、ユーザーは `&domain_hint=contoso.com` パラメーターが追加されたサインイン ページに移動します。これは、ユーザーを `contoso.com` ドメインのフェデレーション IDP に直接高速化することが目的です。 管理されている資格情報を持つユーザーをフェデレーション IDP に移動させた場合、管理されている資格情報を使用したサインインはできません。その結果、サインイン エクスペリエンスのランダム化によるセキュリティの低下とユーザーの不満が発生します。 管理者は、マネージド資格情報をロールアウトして、ユーザーが常に自分のマネージド資格情報を使用できるように、このポリシーを設定する必要もあります。

### 前提条件

Microsoft Entra ID でアプリケーションの自動高速化サインインを無効にするには、次が必要です。

- アクティブなサブスクリプションが含まれる Azure アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者、またはサービス プリンシパルの所有者。

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用してドメイン ヒントを防止するように HRD を構成する

フェデレーション ドメインの管理者は、HRD ポリシーのこのセクションを 4 フェーズ計画で設定する必要があります。 この計画の目的は、最終的にテナント内のすべてのユーザーが、ドメインやアプリケーションに関係なく管理されている資格情報を使用できるようにすることです。ただし、`domain_hint` の使用に対して固定された依存関係を持つアプリは除きます。 この計画は、管理者がこのようなアプリを検出し、新しいポリシーの適用対象から除外し、テナントの残りの部分への変更のロールアウトを続行するのに役立ちます。

この変更を最初にロールアウトするドメインを選択します。 このドメインはテスト ドメインであるため、UX の変更に対してより受け入れ性が高い可能性があるドメインを選択します (たとえば、別のサインイン ページが表示されます)。 次の例は、このドメイン名を使用するすべてのアプリケーションのすべてのドメイン ヒントを無視するように構成されています。 テナントの既定の HRD ポリシーで、このポリシーを設定します。

Connect コマンドを実行して、少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールを持つ Microsoft Entra ID にサインインします。

```powershell
connect-MgGraph -scopes "Policy.ReadWrite.ApplicationConfiguration"
```

1. 次のコマンドを実行して、テスト ドメインのドメイン ヒントを防ぎます。

    ```powershell
    # Define the Home Realm Discovery Policy parameters  
    $params = @{
    definition = @(
        '{
            "HomeRealmDiscoveryPolicy": {
                "DomainHintPolicy": {
                    "IgnoreDomainHintForDomains": ["federated.example.edu"],
                    "RespectDomainHintForDomains": [],
                    "IgnoreDomainHintForApps": [],
                    "RespectDomainHintForApps": []
                }
            }
        }'
    )
    displayName = "Home Realm Discovery Domain Hint Exclusion Policy"
    isOrganizationDefault = $true
    }
    
    # Define the Home Realm Discovery Policy ID (ensure this is set to a valid ID)  
    $homeRealmDiscoveryPolicyId = "<Your-Policy-ID-Here>"  # Replace with your actual policy ID  
    
    # Update the policy to ignore domain hints for the specified domains  
    Update-MgPolicyHomeRealmDiscoveryPolicy -HomeRealmDiscoveryPolicyId $homeRealmDiscoveryPolicyId -BodyParameter $params  
    ```

    実際のアプリ GUID で `app-client-Guid` を置換し、プレースホルダードメインの値を実際のドメインで置換してください。
2. テスト ドメインのユーザーからフィードバックを収集します。 この変更の結果として中断されたアプリケーションの詳細を収集します。それらはドメイン ヒントの使用に対して依存関係があるため、更新する必要があります。 ここでは、`RespectDomainHintForApps` セクションに追加します。

    ```powershell
    # Define the Home Realm Discovery Policy parameters
    $params = @{
    definition = @(
        '{
            "HomeRealmDiscoveryPolicy": {
                "DomainHintPolicy": {
                    "IgnoreDomainHintForDomains": ["federated.example.edu"],
                    "RespectDomainHintForDomains": [],
                    "IgnoreDomainHintForApps": [],
                    "RespectDomainHintForApps": ["app1-clientID-Guid", "app2-clientID-Guid"]
                }
            }
        }'
    )
    displayName = "Home Realm Discovery Domain Hint Exclusion Policy"
    isOrganizationDefault = $true
    }
    # Define the Home Realm Discovery Policy ID (ensure this is set to a valid ID)  
    $homeRealmDiscoveryPolicyId = "<Your-Policy-ID-Here>"  # Replace with your actual policy ID  
    
    # Update the policy to ignore domain hints for the specified domains  
    Update-MgPolicyHomeRealmDiscoveryPolicy -HomeRealmDiscoveryPolicyId $homeRealmDiscoveryPolicyId -BodyParameter $params
    ```

    実際のアプリ GUID で `app-client-Guid` を置換し、プレースホルダードメインの値を実際のドメインで置換してください。
3. 新しいドメインへのポリシーのロールアウトを引き続き展開し、さらにフィードバックを収集します。

    ```powershell
    # Define the Home Realm Discovery Policy parameters  
    $params = @{
    definition = @(
        '{
            "HomeRealmDiscoveryPolicy": {
                "DomainHintPolicy": {
                    "IgnoreDomainHintForDomains": ["federated.example.edu", "otherDomain.com", "anotherDomain.com"],
                    "RespectDomainHintForDomains": [],
                    "IgnoreDomainHintForApps": [],
                    "RespectDomainHintForApps": ["app1-clientID-Guid", "app2-clientID-Guid"]
                }
            }
        }'
    )
    displayName = "Home Realm Discovery Domain Hint Exclusion Policy"
    isOrganizationDefault = $true
    }
    
    # Define the Home Realm Discovery Policy ID (ensure this is set to a valid ID)  
    $homeRealmDiscoveryPolicyId = "<Your-Policy-ID-Here>"  # Replace with your actual policy ID  
    
    # Update the policy to ignore domain hints for the specified domains  
    Update-MgPolicyHomeRealmDiscoveryPolicy -HomeRealmDiscoveryPolicyId $homeRealmDiscoveryPolicyId -BodyParameter $params
    ```

    実際のアプリ GUID で `app-client-Guid` を置換し、プレースホルダードメインの値を実際のドメインで置換してください。
4. ロールアウトを完了します。ターゲットは、引き続き高速化する必要があるドメインを除外した、すべてのドメインです。

    ```powershell
    $params = @{
    definition = @(
        '{
            "HomeRealmDiscoveryPolicy": {
                "DomainHintPolicy": {
                    "IgnoreDomainHintForDomains": ["*"],
                    "RespectDomainHintForDomains": ["guestHandlingDomain.com"],
                    "IgnoreDomainHintForApps": [],
                    "RespectDomainHintForApps": ["app1-clientID-Guid", "app2-clientID-Guid"]
                }
            }
        }'
    )
    displayName = "Home Realm Discovery Domain Hint Exclusion Policy"
    isOrganizationDefault = $true
    }  
    
    # Define the Home Realm Discovery Policy ID (ensure this is set to a valid ID)  
    $homeRealmDiscoveryPolicyId = "<Your-Policy-ID-Here>"  # Replace with your actual policy ID  
    
    # Update the policy to ignore domain hints for the specified domains  
    Update-MgPolicyHomeRealmDiscoveryPolicy -HomeRealmDiscoveryPolicyId $homeRealmDiscoveryPolicyId -BodyParameter $params
    ```

    実際のアプリ GUID で `app-client-Guid` を置換し、プレースホルダードメインの値を実際のドメインで置換してください。

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph を使用してドメイン ヒントを防止するように HRD を構成する

フェデレーション ドメインの管理者は、HRD ポリシーのこのセクションを 4 フェーズ計画で設定する必要があります。 この計画の目的は、最終的にテナント内のすべてのユーザーが、ドメインやアプリケーションに関係なく管理されている資格情報を使用できるようにすることです。ただし、`domain_hint` の使用に対して固定された依存関係を持つアプリは除きます。 この計画は、管理者がこのようなアプリを検出し、新しいポリシーの適用対象から除外し、テナントの残りの部分への変更のロールアウトを続行するのに役立ちます。

Microsoft Graph エクスプローラー ウィンドウで、少なくとも [アプリケーション管理者の](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールでサインインします。

`Policy.ReadWrite.ApplicationConfiguration` の権限に同意してください。

1. この変更を最初にロールアウトするドメインを選択します。 このドメインはテスト ドメインであるため、UX の変更に対してより受け入れ性が高い可能性があるドメインを選択します (たとえば、別のサインイン ページが表示されます)。 これにより、このドメイン名を使用するすべてのアプリケーションからのすべてのドメイン ヒントが無視されます。 テナントの既定の HRD ポリシーでこのポリシーを設定します。 新しいポリシーを POST するか、PATCH を使用して既存のポリシーを更新します。

    ```http
    PATCH https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies/{homeRealmDiscoveryPolicyId} 
        {  
    "definition": [  
        "{\"HomeRealmDiscoveryPolicy\":{\"IgnoreDomainHintForDomains\":[\"testDomain.com\"],\"RespectDomainHintForDomains\":[],\"IgnoreDomainHintForApps\":[],\"RespectDomainHintForApps\":[]}}"
    ],
    "displayName": "Home Realm Discovery Domain Hint Exclusion Policy",  
    "isOrganizationDefault": true 
    }
    ```
2. テスト ドメインのユーザーからフィードバックを収集します。 この変更の結果として中断されたアプリケーションの詳細を収集します。それらはドメイン ヒントの使用に対して依存関係があるため、更新する必要があります。 ここでは、`RespectDomainHintForApps` セクションに追加します。

    ```http
    PATCH https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies/{homeRealmDiscoveryPolicyId} 
    {  
    "definition": [  
        "{\"HomeRealmDiscoveryPolicy\":{\"IgnoreDomainHintForDomains\":[\"testDomain.com\"],\"RespectDomainHintForDomains\":[],\"IgnoreDomainHintForApps\":[],\"RespectDomainHintForApps\":[\"app1-clientID-Guid\",\"app2-clientID-Guid\"]}}"
    ],
    "displayName": "Home Realm Discovery Domain Hint Exclusion Policy6",  
    "isOrganizationDefault": false   
    }
    ```
3. 新しいドメインへのポリシーのロールアウトを引き続き展開し、さらにフィードバックを収集します。

    ```http
    PATCH https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies/{homeRealmDiscoveryPolicyId} 
    {  
    "definition": [  
        "{\"HomeRealmDiscoveryPolicy\":{\"IgnoreDomainHintForDomains\":[\"testDomain.com\",\"otherDomain.com\",\"anotherDomain.com\"],\"RespectDomainHintForDomains\":[],\"IgnoreDomainHintForApps\":[],\"RespectDomainHintForApps\":[\"app1-clientID-Guid\",\"app2-clientID-Guid\"]}}"  
    ],  
    "displayName": "Home Realm Discovery Domain Hint Exclusion Policy",  
    "isOrganizationDefault": true  
    }
    ```
4. ロールアウトを完了する - すべてのドメインをターゲットにし、引き続き高速化する必要があるドメインを除外します。

    ```http
    PATCH https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies/{homeRealmDiscoveryPolicyId} 
    {  
    "definition": [  
        "{\"HomeRealmDiscoveryPolicy\":{\"IgnoreDomainHintForDomains\":[\"*\"],\"RespectDomainHintForDomains\":[\"guestHandlingDomain.com\"],\"IgnoreDomainHintForApps\":[],\"RespectDomainHintForApps\":[\"app1-clientID-Guid\",\"app2-clientID-Guid\"]}}"  
    ],  
    "displayName": "Home Realm Discovery Domain Hint Exclusion Policy",  
    "isOrganizationDefault": true   
    }
    ```

::: zone-end

手順 4 の完了後、`guestHandlingDomain.com`のユーザーを除くすべてのユーザーは、ドメイン ヒントによってフェデレーション IDP への自動高速化が発生する場合でも、Microsoft Entra サインイン ページでサインインできます。 この設定の例外は、サインインを要求するアプリが除外対象のアプリの 1 つである場合です。これらのアプリでは、すべてのドメイン ヒントが引き続き受け入れられます。

### DomainHintPolicy の詳細

HRD ポリシーの DomainHintPolicy セクションは JSON オブジェクトであり、管理者はこれを使用して、ドメイン ヒントの使用対象から特定のドメインとアプリケーションをオプトアウトできます。 機能的には、このセクションでは、サインイン要求の `domain_hint` パラメーターが存在しないかのように動作するように Microsoft Entra サインイン ページに指示します。

#### ポリシーの Respect (優先) と Ignore (無視) のセクション

| セクション | 意味 | 値 |
| --- | --- | --- |
| `IgnoreDomainHintForDomains` | このドメイン ヒントが要求で送信された場合は、無視します。 | ドメイン アドレスの配列 (`contoso.com` など)。 `all_domains` もサポートされています |
| `RespectDomainHintForDomains` | 要求でアプリを自動高速化しないことが `IgnoreDomainHintForApps` によって指示されていても、このドメイン ヒントが要求で送信された場合は、それを優先します。 このプロパティは、ネットワーク内の非推奨のドメイン ヒントのロールアウトを遅らせるためのプロパティです。一部のドメインを引き続き高速化する必要があることを示すことができます。 | ドメイン アドレスの配列 (`contoso.com` など)。 `all_domains` もサポートされています |
| `IgnoreDomainHintForApps` | このアプリケーションからの要求にドメイン ヒントが含まれている場合は、無視します。 | アプリケーション ID (GUID) の配列。 `all_apps` もサポートされています |
| `RespectDomainHintForApps` | このアプリケーションからの要求にドメイン ヒントが含まれている場合は、`IgnoreDomainHintForDomains` にそのドメインが含まれていても、それを優先します。 ドメイン ヒントなしで中断されていることを検出した場合に、一部のアプリの動作を維持するために使用します。 | アプリケーション ID (GUID) の配列。 `all_apps` もサポートされています |

#### ポリシーの評価

DomainHintPolicy ロジックは、ドメイン ヒントを含む受信要求ごとに実行され、要求に含まれる 2 つのデータ、つまりドメイン ヒント内のドメインと、クライアント ID (アプリ) に基づいて高速化されます。 要するに、ドメインまたはアプリの "Respect" は、特定のドメインまたはアプリケーションのドメイン ヒントを "無視" する命令よりも優先されます。

- ドメイン ヒント ポリシーがない場合、または 4 つのセクションのいずれも言及されているアプリまたはドメイン ヒントを参照していない場合は、HRD ポリシーの残りの部分 評価されます。
- 要求で `RespectDomainHintForApps` または `RespectDomainHintForDomains` セクションのどちらか一方 (または両方) にアプリまたはドメイン ヒントが含まれている場合、ユーザーは要求どおりにフェデレーション IDP に自動高速化されます。
- 要求の `IgnoreDomainHintsForApps` または `IgnoreDomainHintsForDomains` のどちらか一方 (または両方) でアプリまたはドメイン ヒントが参照されていて、"Respect" セクションでは参照されていない場合、要求は自動高速化されず、ユーザーは Microsoft Entra のサインイン ページにとどまってユーザー名を指定します。

ユーザーがサインイン ページでユーザー名を入力すると、ユーザーは自分のマネージド資格情報を使用できます。 管理対象の資格情報を使用しないことを選択した場合、または登録されていない場合は、通常どおり資格情報の入力のためにフェデレーション IDP に移動されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/protect-against-consent-phishing"} -->
## 同意フィッシングから保護する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/protect-against-consent-phishing
- Service: entra-id / enterprise-apps
- Article date: 2023-12-11
- Summary: Microsoft Entra ID を使用してアプリケーション ベースの同意フィッシング攻撃を軽減する方法について説明します。

生産性がプライベート ネットワークに限定されなくなり、作業がクラウド サービスに大きく移行しました。 クラウド アプリケーションを使用すると、従業員はリモートで生産性を高めることができますが、攻撃者もアプリケーションベースの攻撃を使用して重要な組織データにアクセスできます。 メールのフィッシングや資格情報の侵害など、ユーザーに焦点を当てた攻撃にはなじみがあるかもしれません。 "同意フィッシング" は、注意が必要なもう 1 つの脅威ベクトルです。

この記事では、同意フィッシングの概要、組織を保護するための Microsoft の取り組み、組織が安全を維持するため実行できる手順について説明します。

### 同意フィッシングとは

同意フィッシング攻撃では、悪意のあるクラウド アプリケーションにアクセス許可を付与するようにユーザーが誘導されます。 その後、これらの悪意のあるアプリケーションは、ユーザーの正当なクラウド サービスとデータにアクセスできます。 資格情報の侵害とは異なり、同意フィッシングを実行する "*脅威アクター*" は、個人または組織のデータへのアクセスを直接許可できるユーザーを標的にします。 同意画面には、アプリケーションが受け取るすべてのアクセス許可が表示されます。 正規のプロバイダー (Microsoft ID プラットフォームなど) がアプリケーションをホストしているため、疑いを持たないユーザーは条件を受け入れます。 このアクションにより、悪意のあるアプリケーションに、要求されたデータへのアクセス許可が付与されます。 次の図は、さまざまなアクセス許可へのアクセスを要求する OAuth アプリの例を示しています。

[Image: ユーザーの同意を必要とする [アクセス許可が要求されています] ウィンドウを示すスクリーンショット。]

### 同意フィッシング攻撃を軽減する

管理者、ユーザー、または Microsoft のセキュリティ研究者は、疑わしい動作を示す OAuth アプリケーションにフラグを付けることができます。 Microsoft は、フラグが立てられたアプリケーションをレビューして、サービス利用規約に違反しているかどうかを判断します。 違反が確認された場合、Microsoft Entra ID ではそのアプリケーションが無効化され、すべての Microsoft サービスで以後使用することが禁止されます。

Microsoft Entra ID で OAuth アプリケーションが無効化されると、次のアクションが実行されます。

- 悪意のあるアプリケーションとそれに関連するサービス プリンシパルは、完全に無効な状態になります。 新しいトークン要求または更新トークンの要求は拒否されますが、既存のアクセス トークンは有効期限が切れるまで引き続き有効です。
- これらのアプリケーションは、Microsoft Graph の関連`DisabledDueToViolationOfServicesAgreement`および`disabledByMicrosoftStatus` リソースの種類の  プロパティに  と表示されます。 将来的に組織で再度インスタンス化されないようにするために、これらのオブジェクトを削除することはできません。
- アプリケーションが無効化される前に組織内のユーザーがそのアプリケーションに同意すると、メールが特権ロール管理者に送信されます。 このメールでは、実行されたアクションと、セキュリティ体制を調査して改善するために実行できる推奨手順が示されます。

### 推奨される対応と修復

Microsoft が無効にしたアプリケーションが組織に影響を与える場合、組織は環境を安全に保つために次の手順を実行する必要があります。

1. 次を含む、無効化されたアプリケーションのアプリケーション アクティビティを調べます。
    - アプリケーションによって要求された、委任されたアクセス許可またはアプリケーションのアクセス許可。
    - アプリケーションによるアクティビティとアプリケーションを使用する権限を持つユーザーのサインイン アクティビティに関する Microsoft Entra ID 監査ログ。
2. [不正な同意許可を防止するためのガイダンス](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/detect-and-remediate-illicit-consent-grants)を確認して使用します。 このガイダンスでは、確認中に検出された無効なアプリケーションと疑わしいアプリケーションへのアクセス許可と同意の監査について説明されています。
3. 以下のセクションで説明する、同意フィッシングに対するセキュリティ強化のベスト プラクティスを実装します。

### 同意フィッシング攻撃に対するセキュリティ強化のベスト プラクティス

管理者は、組織内でアプリケーションを許可および使用する方法を制御するための適切な分析情報と機能を用意して、アプリケーションの使用を制御する必要があります。 攻撃者は決してその手を緩めませんが、組織がそのセキュリティ体制を改善するために実行できる手順があります。 従うべきベスト プラクティスのいくつかを、次に示します。

- アクセス許可と同意フレームワークのしくみについて組織を教育する。
    - アプリケーションから求められるデータとアクセス許可を理解し、[アクセス許可と同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)がプラットフォーム内でどのように機能するかを理解します。
    - [同意要求を管理および評価](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-consent-requests)する方法を管理者が理解しているか確認します。
    - 定期的に組織内の[アプリケーションと同意済みのアクセス許可を監査](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/steps-secure-identity#audit-apps-and-consented-permissions)して、それらのアプリケーションが必要なデータにのみアクセスしており、最小特権の原則に準拠していることを確認します。
- 一般的な同意フィッシングの戦術を特定してブロックする方法を理解する。
    - スペルや文法に誤りがあるか確認します。 メール メッセージまたはアプリケーションの同意画面にスペルミスや文法の誤りがある場合、それは疑わしいアプリケーションである可能性があります。 その場合は、[同意プロンプト](https://learn.microsoft.com/ja-jp/entra/identity-platform/application-consent-experience#building-blocks-of-the-consent-prompt)の **[こちらでご報告ください]** リンクを使用して直接報告します。Microsoft では、それが悪意のあるアプリケーションかどうかを調べ、悪意のあるアプリケーションである場合は無効化します。
    - アプリケーション名やドメイン URL を信頼性のソースとして利用しないでください。 攻撃者は、正当なサービスまたは企業が提供しているアプリケーションやドメインに見えるようにそれらの名前を偽造し、悪意のあるアプリケーションに同意させようとします。 代わりに、ドメイン URL のソースを検証し、可能であれば[確認済みの発行元](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)から入手したアプリケーションを使用します。
    - 攻撃者が組織内の既知のユーザーを偽装しているフィッシング キャンペーンから保護することで、[Microsoft Defender for Office 365 による同意フィッシング メール](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/anti-phishing-policies-about#impersonation-settings-in-anti-phishing-policies-in-microsoft-defender-for-office-365)をブロックします。
    - 組織でアプリケーションの異常なアクティビティを管理できるように、Microsoft Defender for Cloud Apps のポリシーを構成します。 たとえば、[アクティビティ ポリシー](https://learn.microsoft.com/ja-jp/defender-cloud-apps/user-activity-policies)、[異常検出](https://learn.microsoft.com/ja-jp/defender-cloud-apps/anomaly-detection-policy)、[OAuth アプリ ポリシー](https://learn.microsoft.com/ja-jp/defender-cloud-apps/app-permission-policy)などです。
    - [Microsoft 365 Defender を使用した高度な検出](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/advanced-hunting-overview)に関するガイダンスに従って、同意フィッシング攻撃の調査と検出を行います。
- 特定の条件を満たすと共に、それらを満たしていないアプリケーションから保護する信頼されたアプリケーションへのアクセスを許可します。
    - 特定の条件を満たすアプリケーションにのみユーザーが同意できるように、[ユーザーの同意設定を構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent?tabs=azure-portal)します。 このようなアプリケーションには、組織によって開発されたアプリケーションや、検証済みの発行元から開発されたアプリケーションが含まれます。これは、選択した低リスクのアクセス許可に対してのみです。
    - 発行元が確認されたアプリケーションを使用します。 [発行元の確認](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)により、管理者とユーザーは、Microsoft がサポートする審査プロセスを通じてアプリケーション開発者の信頼性を把握できます。 アプリケーションに確認された発行元が存在する場合でも、同意プロンプトをレビューすることにより、要求を理解して評価することが引き続き重要です。 たとえば、要求されているアクセス許可をレビューして、それが有効にするようアプリで要求されているシナリオに合っていることを確認することや、同意プロンプト上でのその他のアプリと発行元の詳細などのレビューです。
    - 一般的な疑わしいアプリケーションの動作に対処するため、プロアクティブな[アプリケーション ガバナンス](https://learn.microsoft.com/ja-jp/defender-cloud-apps/app-governance-manage-app-governance) ポリシーを作成して、Microsoft 365 プラットフォーム上でサードパーティのアプリケーションの動作を監視します。
    - [Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/copilot/security/microsoft-security-copilot)を使用すると、自然言語プロンプトを使用して、Microsoft Entra データから分析情報を取得できます。 これは、アプリケーションまたはワークロード ID に関連するリスクを特定して理解するのに役立ちます。 Microsoft Entraで Microsoft Security Copilot を使用してアプリケーションのリスクを 評価する方法について説明します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/publish-app-gallery"} -->
## Microsoft Entra アプリ ギャラリーにアプリを発行する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/publish-app-gallery
- Service: entra-id / enterprise-apps
- Article date: 2026-08-24
- Summary: 検証済みのアプリを Microsoft Entra アプリ ギャラリーに発行する方法について説明します。

アプリケーションでサポートされている ID 統合を検証したら、セルフサービス発行エクスペリエンスを使用して、Microsoft Entra アプリ ギャラリーでアプリケーションを発行用に送信します。

発行エクスペリエンスにより、独立系ソフトウェア ベンダー (ISV) は次のことを行うことができます。

- ギャラリーの申請を作成して管理します。
- ギャラリーの一覧に含める機能を選択します。
- 該当する検証結果を提出に関連付けます。
- アプリケーションと発行元の情報を指定します。
- 顧客向けのドキュメントとアプリケーション ロゴをアップロードします。
- Microsoftレビューのためにアプリケーションを送信します。
- 送信を追跡し、フィードバックに応答します。

Important

セルフサービス検証とセルフサービス発行は別々のエクスペリエンスです。 パブリケーション用のアプリケーションを送信する前に、ギャラリーの一覧に含めるすべての ID 機能に適用される検証を完了します。

### 始める前の準備

発行プロセスを開始する前に、次の内容があることを確認します。

- 該当する SAML、OpenID Connect、またはユーザー プロビジョニングの検証を完了しました。
- アプリの検証に合格したテスト結果。
- 組織に関連付けられた Partner One ID（旧 Microsoft Partner Network (MPN) ID）
- Microsoft Entra のテナント。
- 顧客向けの構成ドキュメント。
- 必要なアプリケーション ロゴ。
- 正確なアプリケーション、プライバシー、使用条件、カスタマー サポート情報。
- 顧客が使用できるアプリケーション。

アプリケーションでシングル サインオンとユーザー プロビジョニングの両方がサポートされている場合は、両方の機能の検証要件を満たします。

Important

検証結果は、発行する予定の統合を表す必要があります。 検証後に統合を大幅に変更する場合は、アプリケーションを送信する前に、該当する検証を繰り返します。

### 発行エクスペリエンスにアクセスする

セルフサービス発行エクスペリエンスには、3 つの方法でアクセスできます。

#### オプション 1: Microsoft Entra アプリ ギャラリーから

ギャラリー閲覧エクスペリエンスから開始するには、このオプションを使用します。

1. Microsoft Entra 管理センターにサインインします。
2. **Identity**&gt;**Applications**&gt;**Enterprise アプリケーション** に移動します。
3. [**Browse Microsoft Entra App Gallery**] を選択します。
4. [ **アプリケーションをギャラリーに発行する] を選択します**。

#### オプション 2: エンタープライズ アプリケーションを作成する場合

このオプションは、エンタープライズ アプリケーション領域から開始するときに使用します。

1. Microsoft Entra 管理センターにサインインします。
2. **Identity**&gt;**Applications**&gt;**Enterprise アプリケーション** に移動します。
3. **新規アプリケーション** を選択します。
4. Microsoft Entra アプリ ギャラリーで、[アプリケーション**をギャラリーに発行**] を選択します。

#### オプション 3: 既存の下書き提出を再開する

ギャラリー申請を以前に作成して保存した場合は、このオプションを使用します。

1. セルフサービス発行エクスペリエンスを開きます。
2. **[発行済みアプリケーション] を選択します**。
3. 申請の下書きを選択します。
4. 送信を続行します。

### ギャラリーの申請を作成する

申請を作成するには:

1. セルフサービス発行エクスペリエンスを開きます。
2. アプリケーション名を入力します。
3. Microsoft Partnerネットワーク ID を入力します。
4. 申請を保存します。

申請を保存すると、発行エクスペリエンスによって提出 ID が作成されます。

Important

提出 ID は使用可能な状態にしておきます。 ギャラリーの申請が識別され、発行プロセス全体で使用されます。 サポートに連絡するとき、またはフィードバックを提供するときに含めます。

申請を作成しても、アプリケーションは発行されません。 要件を完了し、Microsoftレビューのために提出するまで、アプリケーションはドラフト状態のままです。

### 発行する機能を選択する

ギャラリーの一覧に含める ID 機能を選択します。

使用可能なオプションは次のとおりです。

- シングルサインオン
- シングルサインオン + ユーザープロビジョニング

アプリケーションでシングル サインオンとユーザー プロビジョニングがサポートされている場合は、[ **シングル Sign-On + ユーザー プロビジョニング**] を選択します。

以下の機能のみを含めます。

- 実装およびテスト済み。
- 該当するセルフサービス検証を完了しました。
- 顧客向けに文書化されています。
- 顧客が使用できる状態です。

### 検証結果を提供する

発行エクスペリエンスでは、該当するセルフサービス検証の結果を使用して、提出に含まれる機能の準備状況を確認します。

#### 単一サインイン

申請にシングル サインオンが含まれている場合:

1. 該当する SAML または OIDC 検証結果をギャラリーの申請に関連付けます。
2. 検証済みの構成が、発行する予定の統合を表していることを確認します。
3. 発行エクスペリエンスに表示されるブロックの問題を解決します。

手順については、以下を参照してください。

- [Microsoft Entra アプリ ギャラリーの OIDC マルチテナント アプリケーションを検証する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-oidc-multitenant-app-gallery)
- [Microsoft Entra アプリ ギャラリーの SAML シングル サインオンを検証する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-saml-single-sign-on-app-gallery)

#### ユーザー プロビジョニング

申請にユーザー プロビジョニングが含まれている場合:

1. 要求された開発者テナント情報を指定します。
2. 発行エクスペリエンスに、該当するプロビジョニング検証結果が表示されることを確認します。
3. 発行エクスペリエンスに表示されるブロックの問題を解決します。

検証手順については、「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニングを検証する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-app-gallery)」を参照してください。

アプリケーションでシングル サインオンとユーザー プロビジョニングがサポートされている場合は、両方の機能に必要な検証結果を提供します。 発行エクスペリエンスは、レビュー中にプロビジョニングの検証結果を取得して表示します。

### アプリケーション情報の確認と完了

出版のプロセスでは、以下の情報に基づいて、アプリケーション発行元に関する一部の情報が事前入力されます:

- Microsoft Partner One ID (以前の Microsoft Partner Network (MPN) ID) に関連付けられている情報。
- 完了したセルフサービス検証のアプリケーション情報と結果。

事前入力された情報を確認し、残りの必須フィールドを入力します。 この情報には、アプリケーションと発行元の詳細、サポートされている機能、アプリケーション URL、プライバシーと使用条件の情報、カスタマー サポート情報が含まれます。

事前入力された情報が正しくないか古い場合は、発行エクスペリエンスで許可されている場所で更新してください。 パートナー プロファイルまたは検証結果から取得され、編集できない場合は、ソースで修正してから続行してください。

Important

事前入力された検証情報が、発行する予定の統合に対応していることを確認します。 検証後に統合が大幅に変更された場合は、該当する検証をもう一度完了します。

資格情報、シークレット、プライベート テナント情報、または内部専用の手順は含めないでください。

### アプリケーションの準備とアップロードに関するドキュメント

事前入力された情報を確認し、残りの必須フィールドを入力します。 この情報には、アプリケーションと発行元の詳細、サポートされている機能、アプリケーション URL、プライバシーと使用条件の情報、カスタマー サポート情報が含まれます。

事前入力された情報が正しくないか古い場合は、発行エクスペリエンスで許可されている場所で更新してください。 パートナー プロファイルまたは検証結果から取得され、編集できない場合は、ソースで修正してから続行してください。

Important

事前入力された検証情報が、発行する予定の統合に対応していることを確認します。 検証後に統合が大幅に変更された場合は、該当する検証をもう一度完了します。

資格情報、シークレット、プライベート テナント情報、または内部専用の手順は含めないでください。

### アプリケーション ロゴをアップロードする

必要なアプリケーション ロゴを PNG 形式でアップロードします。

提供してください:

- 215 x 215 ピクセルの正方形のアプリケーション ロゴ。
- 150 x 122 ピクセルの長方形のアプリケーション ロゴ。

各ロゴについて、次の点を確認してください:

- 透明な背景を持っています。
- 白い背景がありません。
- 高品質のソース イメージを使用します。
- 必要なディメンションを持つ。
- アプリケーションを明確に表します。

ロゴをプレビューし、正しく表示されることを確認します。

### アプリケーションを確認して送信する

アプリケーションを送信する前に、次の点を確認します。

- アプリケーションと発行元の情報は正確です。
- 正しい機能が選択されています。
- 該当する検証結果が存在し、公開されている統合を表します。
- 顧客向けのドキュメントが承認されました。
- 必要なアプリケーション ロゴがアップロードされました。
- すべての必須フィールドは完全です。
- すべてのブロックの問題が解決されます。

提出の概要を開き、該当する使用条件に同意して、[ **送信]** を選択します。

申請後、アプリケーションは下書きから送信済みに移動し、Microsoft Entra アプリ ギャラリーの発行ワークフローに入ります。

Note

アプリケーションを送信しても、すぐにギャラリーで使用できるわけではありません。 Microsoftは、公開前にアプリケーションを確認します。

### 発行ワークフロー

申請は次の状態で進行します。

1. **下書き** - 提出物を作成して準備します。
2. **審査中** - Microsoft が申請内容、構成、および必要な情報を検証します。
3. **承認済み** - 申請が検証に成功しました。
4. **プレビュー段階** - 統合は、Microsoft Entra アプリ ギャラリーでプレビュー オファリングとして利用できます。
5. **発行済み** - 統合は、Microsoft Entra アプリ ギャラリーで一般公開されています。

Note

公開タイムラインは営業日単位で測定されます。 実際の処理時間は、送信の完全性、検証結果、および発行元から追加情報が必要かどうかによって異なる場合があります。

#### タイムラインの例

次の例は、発行プロセスの一般的な進行状況を示しています。

- 提出は **[レビュー中** ]に入り、 **5 営業日以内**にレビューされます。
- 承認後、**統合は 10 営業日以内**に**プレビュー**で利用できるようになります。
- すべての公開要件が満たされると、**統合は 7 営業日以内**に**一般公開されます**。

Tip

遅延を回避するには、レビューのために統合を送信する前に、必要なすべてのメタデータ、テスト情報、検証要件が完了していることを確認します。

### 申請を追跡する

アプリケーションを監視し、フィードバックを確認するには:

1. セルフサービス発行エクスペリエンスを開きます。
2. **[発行済みアプリケーション] を選択します**。
3. アプリケーションを選択します。
4. その状態と必要なアクションを確認します。

Microsoftが変更を要求する場合は、影響を受けるアプリケーション情報、ドキュメント、ロゴ、または統合を更新します。 統合が変更された場合は検証を繰り返し、影響を受けるコンテンツをレビューのために再送信します。

申請に関するMicrosoftに連絡するときに、提出 ID を含めます。

### 公開後

Microsoft がアプリケーションを公開すると、お客様は Microsoft Entra アプリ ギャラリーでそのアプリケーションを見つけて、サポートされている ID 機能を構成できます。

発行後にギャラリー構成またはカスタマー セットアップ エクスペリエンスを変更するには、既存のアプリケーションを更新するプロセスを使用します。

### 既存のアプリケーションを更新または削除する

この記事で説明するセルフサービス発行エクスペリエンスは、新しいアプリケーションの申請に適用されます。

既に発行されているアプリケーションを変更するには、「[既存の Microsoft Entra アプリ ギャラリー アプリケーションを更新する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/update-or-remove-app-gallery)」を参照してください。

既存のアプリケーションの削除を要求するには、「[Microsoft Entra アプリ ギャラリーからアプリケーションを削除する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/update-or-remove-app-gallery)」を参照してください。

Important

既存のギャラリー登録情報を置き換えるために、重複する申請を作成しないでください (Microsoftが指示しない限り)。

### ヘルプを取得する

発行中に問題が発生した場合は、Microsoft Entra アプリ ギャラリー Self-Service 発行フィードバック フォームを使用します。

ヘルプを要求する場合は、次の情報を入力します。

- アプリケーション名です。
- 申請 ID。
- 申請に含まれる機能。
- 問題が発生した手順。
- 予想される結果と実際の結果。
- 関連するエラー メッセージ。
- 資格情報、シークレット、または個人情報を公開しないスクリーンショット。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/restore-application"} -->
## 削除されたエンタープライズ アプリケーションを論理的に復元する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/restore-application
- Service: entra-id / enterprise-apps
- Article date: 2025-02-28
- Summary: Microsoft Entra ID で論理的に削除されたエンタープライズ アプリケーションを復元します。

この記事では、Microsoft Entra テナントで論理的に削除されたエンタープライズ アプリケーションを復元する方法について学習します。 論理的に削除されたエンタープライズ アプリケーションは、削除後最初の 30 日以内であればごみ箱から復元できます。 30 日間の期間が経過すると、エンタープライズ アプリケーションは完全に削除され、復元することができません。

Microsoft Entra 管理センターでのアプリ登録によってホーム テナント内の[アプリケーションの登録](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-remove-app)を削除した場合、対応するサービス プリンシパルであるエンタープライズ アプリケーションも削除されます。

削除されたアプリケーションの登録を Microsoft Entra 管理センターから復元すると、対応するサービス プリンシパルも復元されます。 そのため、復元されない条件付きアクセス ポリシーなどの以前のポリシーを除き、サービス プリンシパルの以前の構成を回復できます。

### 前提条件

エンタープライズ アプリケーションを復元するには、次のものが必要になります。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者
    - サービス プリンシパルの所有者。
- テナントで[論理的に削除されたエンタープライズ アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal)。

最近削除されたエンタープライズ アプリケーションを復元するには、次の手順を実行します。 アプリケーションの削除と回復についてよく寄せられる質問の詳細については、[アプリケーションの削除と回復の FAQ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-recover-faq) に関するページを参照してください。

::: zone pivot="entra-powershell"

### Microsoft Entra PowerShell を使用して復元可能なエンタープライズ アプリケーションを表示する

[Microsoft Entra PowerShell](https://learn.microsoft.com/ja-jp/powershell/entra-powershell/?preserve-view=true&view=entra-powershell) モジュールを使用していることを確認します。

[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上としてサインインする必要があります。

1. 最近削除されたエンタープライズ アプリケーションを表示するには、次のコマンドを実行します。

    ```powershell
    Connect-Entra -Scopes 'Application.Read.All'
    Get-EntraDeletedServicePrincipal
    ```

ID を、復元するサービス プリンシパルのオブジェクト ID に置き換えます。

::: zone-end

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用して復元可能なエンタープライズ アプリケーションを表示する

1. `connect-MgGraph -Scopes "Application.ReadWrite.All"` を実行します。 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上としてサインインする必要があります。
2. 最近削除されたエンタープライズ アプリケーションを表示するには、次のコマンドを実行します。

    ```powershell
    Get-MgDirectoryDeletedItem -DirectoryObjectId <id>
    ```

ID を、復元するサービス プリンシパルのオブジェクト ID に置き換えます。

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph API を使用して復元可能なエンタープライズ アプリケーションを表示する

[Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を使用して、最近削除されたエンタープライズ アプリケーションを表示および復元します。 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上としてサインインする必要があります。

テナント内の削除されたエンタープライズ アプリケーションの一覧を取得するには、次のクエリを実行します。

```http
GET https://graph.microsoft.com/v1.0/directory/deletedItems/microsoft.graph.servicePrincipal
```

生成された削除済みサービス プリンシパルの一覧から、復元するエンタープライズ アプリケーションの ID を記録します。

または、削除された特定のエンタープライズ アプリケーションを取得する場合は、削除されたサービス プリンシパルをフェッチし、次の構文を使ってクライアントのアプリケーション ID (appId) プロパティで結果をフィルター処理します。

`https://graph.microsoft.com/v1.0/directory/deletedItems/microsoft.graph.servicePrincipal?$filter=appId eq '{appId}'`。 削除されたサービス プリンシパルのオブジェクト ID を取得したら、復元に進みます。

::: zone-end

::: zone pivot="entra-powershell"

### Microsoft Entra PowerShell を使用してエンタープライズ アプリを復元する

1. 論理的に削除されたエンタープライズ アプリケーションを復元するには、次のコマンドを実行します。

    ```powershell
    Connect-Entra -Scopes 'Application.ReadWrite.All'
    #get the deleted service principal by filtering by the display name.
    $deletedServicePrincipal = Get-EntraDeletedServicePrincipal -Filter "DisplayName eq 'test-App1-Deleted'"
    
    #assign the value returned to a variable and restore the deleted service principal
    $Id = $deletedServicePrincipal.Id
    Restore-EntraDeletedDirectoryObject -Id $deletedServicePrincipal.Id
    ```

::: zone-end

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用してエンタープライズ アプリを復元する

1. エンタープライズ アプリケーションを復元するには、次のコマンドを実行します。

    ```powershell
    Restore-MgDirectoryDeletedItem -DirectoryObjectId <id>
    ```

ID を、復元するサービス プリンシパルのオブジェクト ID に置き換えます。

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph API を使用してエンタープライズ アプリを復元する

エンタープライズ アプリケーションを復元するには、次のクエリを実行します。

```http
POST https://graph.microsoft.com/v1.0/directory/deletedItems/{id}/restore
```

ID を、復元するサービス プリンシパルのオブジェクト ID に置き換えます。

::: zone-end

論理的に削除されたマネージド ID サービス プリンシパルは表示できますが、顧客が復旧または完全に削除することはできません。

警告

エンタープライズ アプリケーションを完全に削除することは、元に戻すことができない操作です。 アプリ上の現在の構成はすべて失われます。 エンタープライズ アプリケーションの詳細を慎重に確認して、完全に削除する必要があるか確認します。

::: zone pivot="entra-powershell"

### Microsoft Entra PowerShell を使用してエンタープライズ アプリを完全に削除する

論理的に削除されたエンタープライズ アプリケーションを完全に削除するには、次のコマンドを実行します。

```powershell
   Connect-Entra -Scopes 'Application.ReadWrite.All'
   #get the deleted service principal by filtering by the display name.
   $deletedServicePrincipal = Get-EntraDeletedServicePrincipal -Filter "DisplayName eq 'test-App1-Deleted'"

   #assign the value returned to a variable and permanently delete the service principal
   $Id = $deletedServicePrincipal.Id
   Remove-EntraDeletedDirectoryObject -Id $deletedServicePrincipal.Id
```

::: zone-end

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用してエンタープライズ アプリを完全に削除する

1. 論理的に削除されたエンタープライズ アプリケーションを完全に削除するには、次のコマンドを実行します。

    ```powershell
    Remove-MgDirectoryDeletedItem -DirectoryObjectId <id>
    ```

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph API を使用してエンタープライズ アプリを完全に削除する

論理的に削除されたエンタープライズ アプリケーションを完全に削除するには、Microsoft Graph エクスプローラーで次のクエリを実行します。

```http
DELETE https://graph.microsoft.com/v1.0/directory/deletedItems/{object-id}
```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/restore-permissions"} -->
## アプリケーションに付与されて取り消されたアクセス許可を Microsoft Entra ID で復元する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/restore-permissions
- Service: entra-id / enterprise-apps
- Article date: 2023-07-05
- Summary: Microsoft Entra ID でアプリケーションの取り消されたアクセス許可を確認および復元する方法について説明します。

この記事では、アプリケーションに付与され、以前に取り消されたアクセス許可を復元する方法について説明します。 組織のデータにアクセスするためのアクセス許可が付与されたアプリケーションのアクセス許可を復元することができます。 ユーザーとして機能するアクセス許可が付与されたアプリケーションのアクセス許可を復元することもできます。

現在、アクセス許可の復元は、Microsoft Graph PowerShell と Microsoft Graph API 呼び出しを通じてのみ可能です。 Microsoft Entra 管理センターからアクセス許可を復元することはできません。 この記事では、Microsoft Graph PowerShell を使用してアクセス許可を復元する方法について説明します。

### 必須コンポーネント

以前に取り消されたアプリケーションのアクセス許可を復元するには、次のものが必要です。

- アクティブなサブスクリプションが含まれる Azure アカウント。 [無料でアカウントを作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者。
- 管理者ではないサービス プリンシパル所有者は、更新トークンを無効にできます。

### アプリケーションの取り消されたアクセス許可を復元する

アクセス許可を復元するためのさまざまな方法を試すことができます。

- アプリの [**アクセス許可**] ページの [**管理者の同意の付与**] ボタンを使用して、もう一度同意を適用します。 この同意により、アプリの開発者が最初にアプリ マニフェストで要求したアクセス許可のセットが適用されます。

注意

管理者の同意を再度付与すると、開発者が構成した既定のセットに含まれていない付与済みのアクセス許可はすべて削除されます。

- 取り消された特定のアクセス許可がわかっている場合は、[PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/tutorial-grant-delegated-api-permissions?view=graph-powershell-1.0&preserve-view=true) または [Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/permissions-grant-via-msgraph?tabs=http&pivots=grant-delegated-permissions) を使用して手動で同意を再度付与することができます。
- 取り消されたアクセス許可がわからない場合は、この記事で提供されているスクリプトを使用して、取り消されたアクセス許可を検出して復元することができます。

まず、スクリプトの servicePrincipalId 値を、復元するアクセス許可を持っているエンタープライズ アプリの ID 値に設定します。 この ID は、Microsoft Entra 管理センターの `object ID` ページでは、 とも呼ばれています。

次に、 `$ForceGrantUpdate = $false` を使用して各スクリプトを実行して、削除された可能性がある委任されたアクセス許可またはアプリ専用のアクセス許可の一覧を表示します。 アクセス許可が既に復元されている場合でも、監査ログの取り消しイベントがスクリプトの結果に表示される場合があります。

スクリプトが検出した取り消されたアクセス許可の復元を試行する場合は、`$ForceGrantUpdate` を `$true` に設定したままにします。 スクリプトは確認を求めますが、復元するアクセス許可ごとに個別の承認を求めることはありません。

アプリにアクセス許可を付与するときは注意してください。 アクセス許可を評価する方法の詳細については、[アクセス許可の評価](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-consent-requests#evaluate-a-request-for-tenant-wide-admin-consent)を参照してください。

::: zone pivot="delegated-perms"

#### 委任されたアクセス許可を復元する

```powershell
# WARNING: Setting $ForceGrantUpdate to true will modify permission grants without
# prompting for confirmation. This can result in unintended changes to your
# application's security settings. Use with caution!
$ForceGrantUpdate = $false

# Set the start and end dates for the audit log search
# If setting date use yyyy-MM-dd format
# endDate is set to tomorrow to include today's audit logs
$startDate = (Get-Date).AddDays(-7).ToString('yyyy-MM-dd')
$endDate = (Get-Date).AddDays(1).ToString('yyyy-MM-dd')

# Set the service principal ID
$servicePrincipalId = "aaaaaaaa-bbbb-cccc-1111-222222222222"

Write-Host "Searching for audit logs between $startDate and $endDate" -ForegroundColor Green
Write-Host "Searching for audit logs for service principal $servicePrincipalId" -ForegroundColor Green

if ($ForceGrantUpdate -eq $true) {
    Write-Host "WARNING: ForceGrantUpdate is set to true. This will modify permission grants without prompting for confirmation. This can result in unintended changes to your application's security settings. Use with caution!" -ForegroundColor Red
    $continue = Read-Host "Do you want to continue? (Y/N)"
    if ($continue -eq "Y" -or $continue -eq "y") {
        Write-Host "Continuing..."
    } else {
        Write-Host "Exiting..."
        exit
    }
}

# Connect to MS Graph
Connect-MgGraph -Scopes "AuditLog.Read.All","DelegatedPermissionGrant.ReadWrite.All" -ErrorAction Stop | Out-Null

# Create a hashtable to store the OAuth2PermissionGrants
$oAuth2PermissionGrants = @{}

function Merge-Scopes($oldScopes, $newScopes) {
    $oldScopes = $oldScopes.Trim() -split '\s+'
    $newScopes = $newScopes.Trim() -split '\s+'
    $mergedScopesArray = $oldScopes + $newScopes | Select-Object -Unique
    $mergedScopes = $mergedScopesArray -join ' '
    return $mergedScopes.Trim()
}

# Function to merge scopes if multiple OAuth2PermissionGrants are found in the audit logs
function Add-Scopes($resourceId, $newScopes) {
    if($oAuth2PermissionGrants.ContainsKey($resourceId)) {
        $oldScopes = $oAuth2PermissionGrants[$resourceId]
        $oAuth2PermissionGrants[$resourceId] = Merge-Scopes $oldScopes $newScopes
    }
    else {
        $oAuth2PermissionGrants[$resourceId] = $newScopes
    }
}

function Get-ScopeDifference ($generatedScope, $currentScope) {
    $generatedScopeArray = $generatedScope.Trim() -split '\s+'
    $currentScopeArray = $currentScope.Trim() -split '\s+'
    $difference = $generatedScopeArray | Where-Object { $_ -notin $currentScopeArray }
    $difference = $difference -join ' '
    return $difference.Trim()
}

# Set the filter for the audit log search
$filterOAuth2PermissionGrant = "activityDateTime ge $startDate and activityDateTime le $endDate" +
    " and Result eq 'success'" +
    " and ActivityDisplayName eq 'Remove delegated permission grant'" +
    " and targetResources/any(x: x/id eq '$servicePrincipalId')"
try {
    # Retrieve the audit logs for removed OAuth2PermissionGrants
    $oAuth2PermissionGrantsAuditLogs = Get-MgAuditLogDirectoryAudit -Filter $filterOAuth2PermissionGrant -All -ErrorAction Stop
}
catch {
    Disconnect-MgGraph | Out-Null
    throw $_
}

# Remove User Delegated Permission Grants
$oAuth2PermissionGrantsAuditLogs = $oAuth2PermissionGrantsAuditLogs | Where-Object {
    -not ($_.TargetResources.ModifiedProperties.OldValue -eq '"Principal"')
}

# Merge duplicate OAuth2PermissionGrants from AuditLogs using Add-Scopes
foreach ($auditLog in $oAuth2PermissionGrantsAuditLogs) {
    $resourceId = $auditLog.TargetResources[0].Id
    # We only want to process OAuth2PermissionGrant Audit Logs where $servicePrincipalId is the clientId not the resourceId
    if ($resourceId -eq $servicePrincipalId) {
        continue
    }
    $oldScope = $auditLog.TargetResources[0].ModifiedProperties | Where-Object { $_.DisplayName -eq "DelegatedPermissionGrant.Scope" } | Select-Object -ExpandProperty OldValue
    if ($oldScope -eq $null) {
        $oldScope = ""
    }
    $oldScope = $oldScope.Replace('"', '')
    $newScope = $auditLog.TargetResources[0].ModifiedProperties | Where-Object { $_.DisplayName -eq "DelegatedPermissionGrant.Scope" } | Select-Object -ExpandProperty NewValue
    if ($newScope -eq $null) {
        $newScope = ""
    }
    $newScope = $newScope.Replace('"', '')
    $scope = Merge-Scopes $oldScope $newScope
    Add-Scopes $resourceId $scope
}

$permissionCount = 0
foreach ($resourceId in $oAuth2PermissionGrants.keys) {
    $scope = $oAuth2PermissionGrants[$resourceId]
    $params = @{
        clientId = $servicePrincipalId
        consentType = "AllPrincipals"
        resourceId = $resourceId
        scope = $scope
    }

    try {
        $currentOAuth2PermissionGrant = Get-MgOauth2PermissionGrant -Filter "clientId eq '$servicePrincipalId' and consentType eq 'AllPrincipals' and resourceId eq '$resourceId'" -ErrorAction Stop
        $action = "Creating"
        if ($currentOAuth2PermissionGrant -ne $null) {
            $action = "Updating"
        }
        Write-Host "--------------------------"
        if ($ForceGrantUpdate -eq $true) {
            Write-Host "$action OAuth2PermissionGrant with the following parameters:"
        } else {
            Write-Host "Potentially removed OAuth2PermissionGrant scopes with the following parameters:"
        }
        Write-Host "    clientId: $($params.clientId)"
        Write-Host "    consentType: $($params.consentType)"
        Write-Host "    resourceId: $($params.resourceId)"
        if ($currentOAuth2PermissionGrant -ne $null) {
            $scopeDifference = Get-ScopeDifference $scope $currentOAuth2PermissionGrant.Scope
            if ($scopeDifference -eq "") {
                Write-Host "OAuth2PermissionGrant already exists with the same scope" -ForegroundColor Yellow
                if ($ForceGrantUpdate -eq $true) {
                    Write-Host "Skipping Update" -ForegroundColor Yellow
                }
                continue
            }
            else {
                Write-Host "    scope diff: '$scopeDifference'"
            }
        }
        else {
            Write-Host "    scope: '$($params.scope)'"
        }
        if ($ForceGrantUpdate -eq $true -and $currentOAuth2PermissionGrant -eq $null) {
            New-MgOauth2PermissionGrant -BodyParameter $params -ErrorAction Stop | Out-Null
            Write-Host "OAuth2PermissionGrant was created successfully" -ForegroundColor Green
        }
        if ($ForceGrantUpdate -eq $true -and $currentOAuth2PermissionGrant -ne $null) {
            Write-Host "    Current Scope: '$($currentOAuth2PermissionGrant.scope)'" -ForegroundColor Yellow
            Write-Host "    Merging with scopes from audit logs" -ForegroundColor Yellow
            $params.scope = Merge-Scopes $currentOAuth2PermissionGrant.scope $params.scope
            Write-Host "    New Scope: '$($params.scope)'" -ForegroundColor Yellow
            Update-MgOauth2PermissionGrant -OAuth2PermissionGrantId $currentOAuth2PermissionGrant.id -BodyParameter $params -ErrorAction Stop | Out-Null
            Write-Host "OAuth2PermissionGrant was updated successfully" -ForegroundColor Green
        }
        $permissionCount++
    }
    catch {
        Disconnect-MgGraph | Out-Null
        throw $_
    }
}

Disconnect-MgGraph | Out-Null

if ($ForceGrantUpdate -eq $true) {
    Write-Host "--------------------------"
    Write-Host "$permissionCount OAuth2PermissionGrants were created/updated successfully" -ForegroundColor Green
} else {
    Write-Host "--------------------------"
    Write-Host "$permissionCount OAuth2PermissionGrants were found" -ForegroundColor Green
}

```

::: zone-end

::: zone pivot="app-perms"

#### アプリ専用のアクセス許可を復元する

注意

アプリ専用の Microsoft Graph アクセス許可を付与するには、特権ロール管理者ロールが必要です。

```powershell
# WARNING: Setting $ForceGrantUpdate to true will modify permission grants without
# prompting for confirmation. This can result in unintended changes to your
# application's security settings. Use with caution!
$ForceGrantUpdate = $false

# Set the start and end dates for the audit log search
# If setting date use yyyy-MM-dd format
# endDate is set to tomorrow to include today's audit logs
$startDate = (Get-Date).AddDays(-7).ToString('yyyy-MM-dd')
$endDate = (Get-Date).AddDays(1).ToString('yyyy-MM-dd')

# Set the service principal ID
$servicePrincipalId = "aaaaaaaa-bbbb-cccc-1111-222222222222"

Write-Host "Searching for audit logs between $startDate and $endDate" -ForegroundColor Green
Write-Host "Searching for audit logs for service principal $servicePrincipalId" -ForegroundColor Green

if ($ForceGrantUpdate -eq $true) {
    Write-Host "WARNING: ForceGrantUpdate is set to true. This will modify permission grants without prompting for confirmation. This can result in unintended changes to your application's security settings. Use with caution!" -ForegroundColor Red
    $continue = Read-Host "Do you want to continue? (Y/N)"
    if ($continue -eq "Y" -or $continue -eq "y") {
        Write-Host "Continuing..."
    } else {
        Write-Host "Exiting..."
        exit
    }
}

# Connect to MS Graph
Connect-MgGraph -Scopes "AuditLog.Read.All","Application.Read.All","AppRoleAssignment.ReadWrite.All" -ErrorAction Stop | Out-Null

# Set the filter for the audit log search
$filterAppRoleAssignment = "activityDateTime ge $startDate and activityDateTime le $endDate" + 
    " and Result eq 'success'" +
    " and ActivityDisplayName eq 'Remove app role assignment from service principal'" +
    " and targetResources/any(x: x/id eq '$servicePrincipalId')"

try {
    # Retrieve the audit logs for removed AppRoleAssignments
    $appRoleAssignmentsAuditLogs = Get-MgAuditLogDirectoryAudit -Filter $filterAppRoleAssignment -All -ErrorAction Stop
}
catch {
    Disconnect-MgGraph | Out-Null
    throw $_
}

$permissionCount = 0
foreach ($auditLog in $appRoleAssignmentsAuditLogs) {
    $resourceId = $auditLog.TargetResources[0].Id
    # We only want to process AppRoleAssignments Audit Logs where $servicePrincipalId is the principalId not the resourceId
    if ($resourceId -eq $servicePrincipalId) {
        continue
    }
    $appRoleId = $auditLog.TargetResources[0].ModifiedProperties | Where-Object { $_.DisplayName -eq "AppRole.Id" } | Select-Object -ExpandProperty OldValue
    $appRoleId = $appRoleId.Replace('"', '')
    $params = @{
        principalId = $servicePrincipalId
        resourceId = $resourceId
        appRoleId = $appRoleId
    }

    try {
        $sp = Get-MgServicePrincipal -ServicePrincipalId $resourceId
        $appRole = $sp.AppRoles | Where-Object { $_.Id -eq $appRoleId }

        Write-Host "--------------------------"
        if ($ForceGrantUpdate -eq $true) {
            Write-Host "Creating AppRoleAssignment with the following parameters:"
        } else {
            Write-Host "Potentially removed AppRoleAssignment with the following parameters:"
        }
        Write-Host "    principalId: $($params.principalId)"
        Write-Host "    resourceId: $($params.resourceId)"
        Write-Host "    appRoleId: $($params.appRoleId)"
        Write-Host "    appRoleValue: $($appRole.Value)"
        Write-Host "    appRoleDisplayName: $($appRole.DisplayName)"
        if ($ForceGrantUpdate -eq $true) {
            New-MgServicePrincipalAppRoleAssignment -ServicePrincipalId $servicePrincipalId -BodyParameter $params -ErrorAction Stop | Out-Null
            Write-Host "AppRoleAssignment was created successfully" -ForegroundColor Green
        }
        $permissionCount++
    }
    catch {
        if ($_.Exception.Message -like "*Permission being assigned already exists on the object*") {
            Write-Host "AppRoleAssignment already exists skipping creation" -ForegroundColor Yellow
        }
        else {
            Disconnect-MgGraph | Out-Null
            throw $_
        }
    }
}

Disconnect-MgGraph | Out-Null

if ($ForceGrantUpdate -eq $true) {
    Write-Host "--------------------------"
    Write-Host "$permissionCount AppRoleAssignments were created successfully" -ForegroundColor Green
} else {
    Write-Host "--------------------------"
    Write-Host "$permissionCount AppRoleAssignments were found" -ForegroundColor Green
}

```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/review-admin-consent-requests"} -->
## 管理者の同意要求の確認とアクションの実行 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/review-admin-consent-requests
- Service: entra-id / enterprise-apps
- Article date: 2025-04-08
- Summary: レビュー担当者として指定された後に作成された管理者の同意要求を確認してアクションを実行する方法について学習します。

この記事では、管理者の同意要求を確認してアクションを実行する方法について学習します。 同意要求を確認して処理するには、レビュー担当者として指定されている必要があります。 詳細については、 [管理者の同意ワークフローの構成に関する記事を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow) 参照してください。 レビュー担当者は、すべての管理者の同意要求を表示できますが、レビュー担当者として指定された後に作成された要求に限って対処できます。

管理者の同意要求を確認するときは、次から選択できるいくつかのオプションがあります。

- **レビュー**: このオプションを使用すると、管理者は要求を評価し、適切と判断された場合は同意を付与できます。
- **拒否**: このオプションを選択すると、同意の要求が拒否され、要求されたアクセス許可にアプリケーションがアクセスできなくなります。 このアクションは、要求を行ったユーザーにフィードバックを提供しません。
- **ブロック**: このオプションは、現在の要求を拒否するだけでなく、同じアプリケーションに対する今後の要求が送信されるのを防ぎます。 これは、信頼できない、または組織にとって不要と見なされるアプリケーションに役立ちます。

たとえば、アプリケーションが会社のポリシーに準拠していないと検出された場合、管理者はそれを [ブロック] することを選択できます。 逆に、アプリケーションが正当であっても、さらにレビューが必要な場合、管理者は、より多くの情報を求めながら、要求を一時的に "拒否" することを選択できます。

注

[My Pending]\(保留中\) タブは、管理者の同意要求に対してアクションを実行できる場所です。 [すべて (プレビュー)]タブでは、管理者の同意要求の履歴のみが表示されます。

### 前提条件

管理者の同意要求を確認してアクションを実行するには、次が必要です。

- Azure アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 管理者の [同意要求を確認](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent#prerequisites)するための適切なロールを持つ管理者ロールまたは指定されたレビュー担当者。

### 管理者の同意要求の確認とアクションの実行

管理者の同意要求を確認してアクションを実行するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインするには、指定された校閲者である[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)である必要があります。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. [ **アクティビティ**] で、[ **管理者の同意要求**] を選択します。
4. [ **保留中]** タブを選択して、保留中の要求を表示して操作します。
5. 要求の対象となっているアプリケーションを選択します。
6. 要求の詳細を確認します。
    - アプリケーションによって要求されているアクセス許可を確認するには、[ **アクセス許可と同意の確認**] を選択します。
    - アプリケーションの詳細を表示するには、[ **アプリの詳細** ] タブを選択します。
    - アクセスを要求しているユーザーとその理由を確認するには、[ **要求者** ] タブを選択します。
7. 要求を評価し、適切なアクションを実行します。
    - **要求を承認します**。 要求を承認する場合には、アプリケーションに管理者の同意を付与します。 要求が承認されたら、すべての要求元にアクセス要求が承認されたことが通知されます。 要求を承認すると、ユーザー割り当てによって他に制限されていない限り、テナント内のすべてのユーザーがアプリケーションにアクセスできます。
    - **要求を拒否します**。 要求を拒否する場合には、理由を入力する必要があります。入力した理由は、すべての要求元に提供されます。 要求が拒否されたら、すべての要求元にアクセス要求が拒否されたことが通知されます。 要求を拒否しても、ユーザーがその後同じアプリについて管理者の同意を要求できなくなるわけではありません。
    - **要求をブロックします**。 要求をブロックする場合には、理由を入力する必要があります。入力した理由は、すべての要求元に提供されます。 要求がブロックされたら、すべての要求元にアプリケーションへのアクセス要求が拒否されたことが通知されます。 要求をブロックすると、テナント内でそのアプリケーションのサービス プリンシパル オブジェクトが、無効の状態で作成されます。 以降は、ユーザーがそのアプリケーションに関する管理者の同意を要求することができなくなります。

### Microsoft Graph を使って管理者の同意要求を確認する

管理者の同意要求をプログラムで確認するには、[`appConsentRequest` リソースの種類](https://learn.microsoft.com/ja-jp/graph/api/resources/appconsentrequest)と、Microsoft Graph `userConsentRequest`と関連するメソッド使用します。 Microsoft Graph を使って同意要求を承認または拒否することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/saml-vs-oidc-decision-guide"} -->
## SAML と OpenID Connect: 適切な SSO プロトコルを選択する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/saml-vs-oidc-decision-guide
- Service: entra-id / enterprise-apps
- Article date: 2026-06-22
- Summary: SAML 2.0 と OpenID Connect (OIDC) プロトコルを比較して、アプリケーションの SSO と Microsoft Entra ID の統合に適したアプローチを選択します。

Microsoft Entra IDでシングル サインオン (SSO) を設定するには、セキュリティ アサーション マークアップ言語 (SAML) 2.0 と OpenID Connect (OIDC) の 2 つのプロトコルから選択します。 この記事では、2 つのプロトコルの違い、それぞれを使用するタイミング、アプリの選択方法について説明します。

### プロトコルの選択が重要な理由

プロトコルの選択は、アプリの構築方法、統合の難しい程度、顧客の環境にどの程度適合するかを示します。 Microsoft Entra IDは両方のプロトコルを完全にサポートしていますが、それぞれ異なるニーズに適しています。

この選択は、次の 2 つのロールに影響します。

- **独立系ソフトウェア ベンダー (ISV) アプリケーション開発者**: 開発作業、フレームワーク サポート、さまざまな顧客要件をどの程度簡単に満たすかを推進します。
- **IT 管理者**: アプリが ID インフラストラクチャとセキュリティ ポリシーにどの程度適合するかに影響します。

### SAML 2.0 とは

SAML 2.0 は、XML に基づく成熟したフェデレーション プロトコルです。 企業は、10 年以上にわたってそれを広く使用してきました。 SAML は、ID プロバイダーとアプリの間でサインインとアクセスのデータを安全に共有します。

SAML は、XML メッセージとアサーションを使用してサインイン データを伝達します。 ユーザー属性を詳細にマップします。 また、署名付き XML アサーションや詳細な属性ステートメントなど、複雑なエンタープライズ ニーズもサポートします。

### OpenID Connect (OIDC) とは

OpenID Connect (OIDC) は、OAuth 2.0 上に構築された最新のサインイン プロトコルです。 JSON Web トークン (JWT) と REST API と呼ばれる JSON トークンを使用します。 OIDC はユーザーをサインインさせ、OAuth 2.0 がアクセスを処理します。

OIDC は、よりシンプルで開発者向けです。 シングルページ アプリケーション、モバイル アプリ、マイクロサービスなど、最新のアプリ設計に適合します。

### SAML と OIDC の比較

次の表は、SAML 2.0 と OpenID Connect を SSO 統合に最も重要な要素と比較しています。

| 特徴 | SAML 2.0 | OpenID Connect |
| --- | --- | --- |
| **メッセージの形式** | XML ベース | JSON ベース |
| **トークンの種類** | XML アサーション | JWT トークン |
| **企業導入** | 広く確立された | 急速に成長 |
| **開発者エクスペリエンス** | 複雑、XML 処理が必要 | シンプルで REST ベース |
| **開発の複雑さ** | 上位の XML 処理が必要 | 下位、REST/JSON ベース |
| **最新のフレームワーク** | 制限付きネイティブ サポート | 優れたサポート |
| **モバイル/SPA のサポート** | 課題 | ネイティブ サポート |
| **検証の配置** | 良い、確立されたパターン | 優れたモダンな標準 |
| **マルチテナント SaaS の適合性** | 複雑さに対して適切 | ネイティブ、最適 |
| **顧客のオンボーディング作業** | より複雑な手動セットアップ | よりシンプルで自動化された検出 |
| **長期的な保守性** | オーバーヘッドの増加 | メンテナンス頻度が低い |
| **属性の処理** | リッチ XML 属性ステートメント | JSON クレーム |
| **セキュリティ機能** | XML シグネチャ、複雑なコントロール | JWT 署名、OAuth スコープ |

### 決定の要約

どちらのプロトコルもセキュリティで保護されており、Microsoft Entra IDは各プロトコルを完全にサポートします。 上の表では、それらをアスペクト別に比較しているため、詳細な相違点に使用してください。 この概要では、ISV が各プロトコルを選択する理由を示します。OIDC は新しいクラウドネイティブ SaaS と最新の ID を持つ顧客に適していますが、SAML は、企業のお客様、コンプライアンスまたは調達の義務、または SAML のみをサポートするレガシ ID プロバイダーの要件に基づく選択肢です。 顧客ベースが両方の世界にまたがる場合は、両方のプロトコルをサポートします。

**次の場合は、OpenID Connect (OIDC) を選択します。**

- 新しい SaaS またはクラウドネイティブ アプリを構築する
- シングルページ アプリケーション (SPA) またはモバイル アプリを構築する
- 開発速度と最新の統合が必要
- 最新の ID システムを使用して顧客にサービスを提供する
- マルチテナントでスケーラブルな配布を計画する

**次の場合は、[SAML] を選択します。**

- SAML を必要とする企業のお客様がいる
- OIDC に対応していないレガシー ID プロバイダーと連携する
- SAML を義務付けるコンプライアンスまたは調達規則を満たす必要がある
- 動作する SAML 実装が既に存在する

**次の場合は、両方のプロトコルをサポートします。**

- 両方を必要とする混合顧客環境にサービスを提供する
- 組織全体で最も広い互換性を実現する

**不明な場合は、新しい SaaS 開発用に OIDC が既定で使用されます。** OIDC は、最新のアプリ、Microsoft Entra検証、および今日のエンタープライズの傾向に適合します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/scripts/powershell-export-all-app-registrations-secrets-and-certs"} -->
## PowerShell サンプル: アプリ登録用のシークレットと証明書をエクスポートする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-all-app-registrations-secrets-and-certs
- Service: entra-id / enterprise-apps
- Article date: 2025-01-23
- Summary: Microsoft Entra テナント内の指定されたアプリ登録のすべてのシークレットと証明書をエクスポートする PowerShell の例。

この PowerShell スクリプトの例では、指定したアプリ登録のすべてのシークレットと証明書をディレクトリから CSV ファイルにエクスポートします。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)がない場合は、開始する前に、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成します。

このサンプルには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) SDK モジュールが必要です。

### サンプル スクリプト

```powershell
<#################################################################################
DISCLAIMER:

This is not an official PowerShell Script. We designed it specifically for the situation you have
encountered right now.

Please do not modify or change any preset parameters.

Please note that we will not be able to support the script if it's changed or altered in any way
or used in a different situation for other means.

This code-sample is provided "AS IS" without warranty of any kind, either expressed or implied,
including but not limited to the implied warranties of merchantability and/or fitness for a
particular purpose.

This sample is not supported under any Microsoft standard support program or service.

Microsoft further disclaims all implied warranties including, without limitation, any implied
warranties of merchantability or of fitness for a particular purpose.

The entire risk arising out of the use or performance of the sample and documentation remains with
you.

In no event shall Microsoft, its authors, or anyone else involved in the creation, production, or
delivery of the script be liable for any damages whatsoever (including, without limitation, damages
for loss of business profits, business interruption, loss of business information, or other
pecuniary loss) arising out of the use of or inability to use the sample or documentation, even if
Microsoft has been advised of the possibility of such damages.
#################################################################################>

Connect-MgGraph -Scopes 'Application.Read.All'

$Messages = @{
    DurationNotice = @{
        Info = @(
            'The operation is running and will take longer the more applications the tenant has...'
            'Please wait...'
        ) -join ' '
    }
    Export         = @{
        Info   = 'Where should the CSV file export to?'
        Prompt = 'Enter the full path in the format of <C:\Users\<USER>\Desktop\Users.csv>'
    }
}

Write-Host $Messages.DurationNotice.Info -ForegroundColor yellow

$Applications = Get-MgApplication -All

$Logs = @()

foreach ($App in $Applications) {
    $AppName = $App.DisplayName
    $AppID   = $App.Id
    $ApplID  = $App.AppId

    $AppCreds = Get-MgApplication -ApplicationId $AppID |
        Select-Object PasswordCredentials, KeyCredentials

    $Secrets = $AppCreds.PasswordCredentials
    $Certs   = $AppCreds.KeyCredentials

    ############################################
    $Logs += [PSCustomObject]@{
        'ApplicationName'        = $AppName
        'ApplicationID'          = $ApplID
        'Secret Name'            = $Null
        'Secret Start Date'      = $Null
        'Secret End Date'        = $Null
        'Certificate Name'       = $Null
        'Certificate Start Date' = $Null
        'Certificate End Date'   = $Null
        'Owner'                  = $Null
        'Owner_ObjectID'         = $Null
    }
    ############################################
    foreach ($Secret in $Secrets) {
        $StartDate  = $Secret.StartDateTime
        $EndDate    = $Secret.EndDateTime
        $SecretName = $Secret.DisplayName

        $Owner    = Get-MgApplicationOwner -ApplicationId $App.Id
        $Username = $Owner.AdditionalProperties.userPrincipalName -join ';'
        $OwnerID  = $Owner.Id -join ';'

        if ($null -eq $Owner.AdditionalProperties.userPrincipalName) {
            $Username = @(
                $Owner.AdditionalProperties.displayName
                '**<This is an Application>**'
            ) -join ' '
        }
        if ($null -eq $Owner.AdditionalProperties.displayName) {
            $Username = '<<No Owner>>'
        }

        $Logs += [PSCustomObject]@{
            'ApplicationName'        = $AppName
            'ApplicationID'          = $ApplID
            'Secret Name'            = $SecretName
            'Secret Start Date'      = $StartDate
            'Secret End Date'        = $EndDate
            'Certificate Name'       = $Null
            'Certificate Start Date' = $Null
            'Certificate End Date'   = $Null
            'Owner'                  = $Username
            'Owner_ObjectID'         = $OwnerID
        }
    }

    foreach ($Cert in $Certs) {
        $StartDate = $Cert.StartDateTime
        $EndDate   = $Cert.EndDateTime
        $CertName  = $Cert.DisplayName

        $Owner    = Get-MgApplicationOwner -ApplicationId $App.Id
        $Username = $Owner.AdditionalProperties.userPrincipalName -join ';'
        $OwnerID  = $Owner.Id -join ';'

        if ($null -eq $Owner.AdditionalProperties.userPrincipalName) {
            $Username = @(
                $Owner.AdditionalProperties.displayName
                '**<This is an Application>**'
            ) -join ' '
        }
        if ($null -eq $Owner.AdditionalProperties.displayName) {
            $Username = '<<No Owner>>'
        }

        $Logs += [PSCustomObject]@{
            'ApplicationName'        = $AppName
            'ApplicationID'          = $ApplID
            'Secret Name'            = $Null
            'Certificate Name'       = $CertName
            'Certificate Start Date' = $StartDate
            'Certificate End Date'   = $EndDate
            'Owner'                  = $Username
            'Owner_ObjectID'         = $OwnerID
            'Secret Start Date'      = $Null
            'Secret End Date'        = $Null
        }
    }
}

Write-Host $Messages.Export.Info -ForegroundColor Green
$Path = Read-Host -Prompt $Messages.Export.Prompt
$Logs | Export-Csv $Path -NoTypeInformation -Encoding UTF8
```

### スクリプトの説明

スクリプトは変更なしで直接使用できます。 管理者は、有効期限について、および既に期限切れのシークレットまたは証明書を表示するかどうかを確認するメッセージが表示されます。

"Add-Member" コマンドは、CSV ファイル内の列を作成する役割を担います。 エクスポートを非対話型にする場合は、CSV ファイル パスを使用して、PowerShell で "$Path" 変数を直接変更できます。

| コマンド | メモ |
| --- | --- |
| [Get-MgApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgapplication?view=graph-powershell-1.0&preserve-view=true) | ディレクトリからアプリケーションを取得します。 |
| [Get-MgApplicationOwner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgapplicationowner?view=graph-powershell-1.0&preserve-view=true) | ディレクトリからアプリケーションの所有者を取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/scripts/powershell-export-all-enterprise-apps-secrets-and-certs"} -->
## PowerShell サンプル: エンタープライズ アプリのシークレットと証明書をエクスポートする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-all-enterprise-apps-secrets-and-certs
- Service: entra-id / enterprise-apps
- Article date: 2025-01-23
- Summary: Microsoft Entra テナント内の指定したエンタープライズ アプリのすべてのシークレットと証明書をエクスポートする PowerShell の例。

この PowerShell スクリプトの例では、指定したエンタープライズ アプリのすべてのシークレット、証明書、所有者をディレクトリから CSV ファイルにエクスポートします。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)がない場合は、開始する前に、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成します。

このサンプルには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) SDK モジュールが必要です。

### サンプル スクリプト

```powershell
<#################################################################################
DISCLAIMER:

This is not an official PowerShell Script. We designed it specifically for the situation you have
encountered right now.

Please do not modify or change any preset parameters.

Please note that we will not be able to support the script if it's changed or altered in any way
or used in a different situation for other means.

This code-sample is provided "AS IS" without warranty of any kind, either expressed or implied,
including but not limited to the implied warranties of merchantability and/or fitness for a
particular purpose.

This sample is not supported under any Microsoft standard support program or service.

Microsoft further disclaims all implied warranties including, without limitation, any implied
warranties of merchantability or of fitness for a particular purpose.

The entire risk arising out of the use or performance of the sample and documentation remains with
you.

In no event shall Microsoft, its authors, or anyone else involved in the creation, production, or
delivery of the script be liable for any damages whatsoever (including, without limitation, damages
for loss of business profits, business interruption, loss of business information, or other
pecuniary loss) arising out of the use of or inability to use the sample or documentation, even if
Microsoft has been advised of the possibility of such damages.

#################################################################################>

Connect-MgGraph -Scopes 'Application.Read.All'

$Messages = @{
    DurationNotice = @{
        Info = @(
            'The operation is running and will take longer the more applications the tenant has...'
            'Please wait...'
        ) -join ' '
    }
    Export         = @{
        Info   = 'Where should the CSV file export to?'
        Prompt = 'Enter the full path in the format of <C:\Users\<USER>\Desktop\Users.csv>'
    }
}

Write-Host $Messages.DurationNotice.Info -ForegroundColor Yellow

$EnterpriseApps = Get-MgServicePrincipal -all

$Logs = @()

foreach ($EnterpriseApp in $EnterpriseApps) {
    $AppName = $EnterpriseApp.DisplayName
    $AppID   = $EnterpriseApp.Id
    $ApplID  = $EnterpriseApp.AppId

    $AppCreds = Get-MgServicePrincipal -ServicePrincipalId $AppID |
        Select-Object PasswordCredentials, KeyCredentials

    $Secrets = $AppCreds.PasswordCredentials
    $Certs   = $AppCreds.KeyCredentials

    ############################################
    $Logs += [PSCustomObject]@{
        'ApplicationName'        = $AppName
        'ApplicationID'          = $ApplID
        'Secret Name'            = $Null
        'Secret Start Date'      = $Null
        'Secret End Date'        = $Null
        'Certificate Name'       = $Null
        'Certificate Start Date' = $Null
        'Certificate End Date'   = $Null
        'Owner'                  = $Null
        'Owner_ObjectID'         = $Null
    }
    ############################################
    foreach ($Secret in $Secrets) {
        $StartDate = $Secret.StartDateTime
        $EndDate   = $Secret.EndDateTime

        $Owner    = Get-MgServicePrincipalOwner -ServicePrincipalId $EnterpriseApp.Id
        $Username = $Owner.AdditionalProperties.userPrincipalName -join ';'
        $OwnerID  = $Owner.Id -join ';'

        if ($null -eq $Owner.AdditionalProperties.userPrincipalName) {
            $Username = @(
                $Owner.AdditionalProperties.displayName
                '**<This is an Application>**'
            ) -join ' '
        }
        if ($null -eq $Owner.AdditionalProperties.displayName) {
            $Username = '<<No Owner>>'
        }

        $Logs += [PSCustomObject]@{
            'ApplicationName'        = $AppName
            'ApplicationID'          = $ApplID
            'Secret Name'            = $SecretName
            'Secret Start Date'      = $StartDate
            'Secret End Date'        = $EndDate
            'Certificate Name'       = $Null
            'Certificate Start Date' = $Null
            'Certificate End Date'   = $Null
            'Owner'                  = $Username
            'Owner_ObjectID'         = $OwnerID
        }
    }

    foreach ($Cert in $Certs) {
        $StartDate = $Cert.StartDateTime
        $EndDate   = $Cert.EndDateTime
        $CertName  = $Cert.DisplayName

        $Owner    = Get-MgServicePrincipalOwner -ServicePrincipalId $EnterpriseApp.Id
        $Username = $Owner.AdditionalProperties.userPrincipalName -join ';'
        $OwnerID  = $Owner.Id -join ';'

        if ($null -eq $Owner.AdditionalProperties.userPrincipalName) {
            $Username = @(
                $Owner.AdditionalProperties.displayName
                '**<This is an Application>**'
            ) -join ' '
        }
        if ($null -eq $Owner.AdditionalProperties.displayName) {
            $Username = '<<No Owner>>'
        }

        $Logs += [PSCustomObject]@{
            'ApplicationName'        = $AppName
            'ApplicationID'          = $ApplID
            'Secret Name'            = $Null
            'Certificate Name'       = $CertName
            'Certificate Start Date' = $StartDate
            'Certificate End Date'   = $EndDate
            'Owner'                  = $Username
            'Owner_ObjectID'         = $OwnerID
            'Secret Start Date'      = $Null
            'Secret End Date'        = $Null
        }
    }
}

Write-Host $Messages.Export.Info -ForegroundColor Green
$Path = Read-Host -Prompt $Messages.Export.Prompt
$Logs | Export-Csv $Path -NoTypeInformation -Encoding UTF8
```

### スクリプトの説明

スクリプトは変更なしで直接使用できます。 管理者は、有効期限について、および既に期限切れのシークレットまたは証明書を表示するかどうかを確認するメッセージが表示されます。

"Add-Member" コマンドは、CSV ファイル内の列を作成する役割を担います。 エクスポートを非対話型にする場合は、CSV ファイル パスを使用して、PowerShell で "$Path" 変数を直接変更できます。

| コマンド | メモ |
| --- | --- |
| [Get-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal?view=graph-powershell-1.0&preserve-view=true) | ディレクトリからエンタープライズ アプリケーションを取得します。 |
| [Get-MgServicePrincipalOwner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipalowner?view=graph-powershell-1.0&preserve-view=true) | ディレクトリからエンタープライズ アプリケーションの所有者を取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/scripts/powershell-export-apps-with-expiring-secrets"} -->
## PowerShell サンプル: 期限切れのシークレットと証明書を含むアプリ登録をエクスポートする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-apps-with-expiring-secrets
- Service: entra-id / enterprise-apps
- Article date: 2025-01-23
- Summary: Microsoft Entra テナント内の指定されたアプリの期限切れのシークレットと証明書を含むすべてのアプリ登録をエクスポートする PowerShell の例。

この PowerShell スクリプトの例では、シークレットと証明書が期限切れになるすべてのアプリ登録を次の X 日後にエクスポートします。 また、有効期限が切れているものも含まれます(選択した場合)。 このスクリプトは、アプリの登録を所有者と共にエクスポートします。 指定したアプリのデータがディレクトリからエクスポートされます。 出力は CSV ファイルに保存されます。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)がない場合は、開始する前に、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成します。

このサンプルには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) SDK モジュールが必要です。

### サンプル スクリプト

```powershell
<#################################################################################
DISCLAIMER:

This is not an official PowerShell Script. We designed it specifically for the situation you have
encountered right now.

Please do not modify or change any preset parameters.

Please note that we will not be able to support the script if it's changed or altered in any way
or used in a different situation for other means.

This code-sample is provided "AS IS" without warranty of any kind, either expressed or implied,
including but not limited to the implied warranties of merchantability and/or fitness for a
particular purpose.

This sample is not supported under any Microsoft standard support program or service.

Microsoft further disclaims all implied warranties including, without limitation, any implied
warranties of merchantability or of fitness for a particular purpose.

The entire risk arising out of the use or performance of the sample and documentation remains with
you.

In no event shall Microsoft, its authors, or anyone else involved in the creation, production, or
delivery of the script be liable for any damages whatsoever (including, without limitation, damages
for loss of business profits, business interruption, loss of business information, or other
pecuniary loss) arising out of the use of or inability to use the sample or documentation, even if
Microsoft has been advised of the possibility of such damages.

#################################################################################>

Connect-MgGraph -Scopes 'Application.Read.All'

$Messages = @{
    ExpirationDays = @{
        Info   = 'Filter the applications to log by the number of days until their secrets expire.'
        Prompt = 'Enter the number of days until the secrets expire as an integer.'
    }
    AlreadyExpired = @{
        Info   = 'Would you like to see Applications with already expired secrets as well?'
        Prompt = 'Enter Yes or No'
    }
    DurationNotice = @{
        Info = @(
            'The operation is running and will take longer the more applications the tenant has...'
            'Please wait...'
        ) -join ' '
    }
    Export = @{
        Info = 'Where should the CSV file export to?'
        Prompt = 'Enter the full path in the format of <C:\Users\<USER>\Desktop\Users.csv>'
    }
}

Write-Host $Messages.ExpirationDays.Info -ForegroundColor Green
$DaysUntilExpiration = Read-Host -Prompt $Messages.ExpirationDays.Prompt

Write-Host $Messages.AlreadyExpired.Info -ForegroundColor Green
$IncludeAlreadyExpired = Read-Host -Prompt $Messages.AlreadyExpired.Prompt

$Now = Get-Date

Write-Host $Messages.DurationNotice.Info -ForegroundColor yellow

$Applications = Get-MgApplication -all

$Logs = @()

foreach ($App in $Applications) {
    $AppName = $App.DisplayName
    $AppID   = $App.Id
    $ApplID  = $App.AppId

    $AppCreds = Get-MgApplication -ApplicationId $AppID |
        Select-Object PasswordCredentials, KeyCredentials

    $Secrets = $AppCreds.PasswordCredentials
    $Certs   = $AppCreds.KeyCredentials

    foreach ($Secret in $Secrets) {
        $StartDate  = $Secret.StartDateTime
        $EndDate    = $Secret.EndDateTime
        $SecretName = $Secret.DisplayName

        $Owner    = Get-MgApplicationOwner -ApplicationId $App.Id
        $Username = $Owner.AdditionalProperties.userPrincipalName -join ';'
        $OwnerID  = $Owner.Id -join ';'

        if ($null -eq $Owner.AdditionalProperties.userPrincipalName) {
            $Username = @(
                $Owner.AdditionalProperties.displayName
                '**<This is an Application>**'
            ) -join ' '
        }
        if ($null -eq $Owner.AdditionalProperties.displayName) {
            $Username = '<<No Owner>>'
        }

        $RemainingDaysCount = ($EndDate - $Now).Days

        if ($IncludeAlreadyExpired -eq 'No') {
            if ($RemainingDaysCount -le $DaysUntilExpiration -and $RemainingDaysCount -ge 0) {
                $Logs += [PSCustomObject]@{
                    'ApplicationName'        = $AppName
                    'ApplicationID'          = $ApplID
                    'Secret Name'            = $SecretName
                    'Secret Start Date'      = $StartDate
                    'Secret End Date'        = $EndDate
                    'Certificate Name'       = $Null
                    'Certificate Start Date' = $Null
                    'Certificate End Date'   = $Null
                    'Owner'                  = $Username
                    'Owner_ObjectID'         = $OwnerID
                }
            }
        } elseif ($IncludeAlreadyExpired -eq 'Yes') {
            if ($RemainingDaysCount -le $DaysUntilExpiration) {
                $Logs += [PSCustomObject]@{
                    'ApplicationName'        = $AppName
                    'ApplicationID'          = $ApplID
                    'Secret Name'            = $SecretName
                    'Secret Start Date'      = $StartDate
                    'Secret End Date'        = $EndDate
                    'Certificate Name'       = $Null
                    'Certificate Start Date' = $Null
                    'Certificate End Date'   = $Null
                    'Owner'                  = $Username
                    'Owner_ObjectID'         = $OwnerID
                }
            }
        }
    }

    foreach ($Cert in $Certs) {
        $StartDate = $Cert.StartDateTime
        $EndDate   = $Cert.EndDateTime
        $CertName  = $Cert.DisplayName

        $Owner    = Get-MgApplicationOwner -ApplicationId $App.Id
        $Username = $Owner.AdditionalProperties.userPrincipalName -join ';'
        $OwnerID  = $Owner.Id -join ';'

        if ($null -eq $Owner.AdditionalProperties.userPrincipalName) {
            $Username = @(
                $Owner.AdditionalProperties.displayName
                '**<This is an Application>**'
            ) -join ' '
        }
        if ($null -eq $Owner.AdditionalProperties.displayName) {
            $Username = '<<No Owner>>'
        }

        $RemainingDaysCount = ($EndDate - $Now).Days

        if ($IncludeAlreadyExpired -eq 'No') {
            if ($RemainingDaysCount -le $DaysUntilExpiration -and $RemainingDaysCount -ge 0) {
                $Logs += [PSCustomObject]@{
                    'ApplicationName'        = $AppName
                    'ApplicationID'          = $ApplID
                    'Secret Name'            = $Null
                    'Certificate Name'       = $CertName
                    'Certificate Start Date' = $StartDate
                    'Certificate End Date'   = $EndDate
                    'Owner'                  = $Username
                    'Owner_ObjectID'         = $OwnerID
                    'Secret Start Date'      = $Null
                    'Secret End Date'        = $Null
                }
            }
        } elseif ($IncludeAlreadyExpired -eq 'Yes') {
            if ($RemainingDaysCount -le $DaysUntilExpiration) {
                $Logs += [PSCustomObject]@{
                    'ApplicationName'        = $AppName
                    'ApplicationID'          = $ApplID
                    'Secret Name'            = $Null
                    'Certificate Name'       = $CertName
                    'Certificate Start Date' = $StartDate
                    'Certificate End Date'   = $EndDate
                    'Owner'                  = $Username
                    'Owner_ObjectID'         = $OwnerID
                    'Secret Start Date'      = $Null
                    'Secret End Date'        = $Null
                }
            }
        }
    }
}

Write-Host $Messages.Export.Info -ForegroundColor Green
$Path = Read-Host -Prompt $Messages.Export.Prompt
$Logs | Export-Csv $Path -NoTypeInformation -Encoding UTF8
```

### スクリプトの説明

スクリプトは変更なしで直接使用できます。 管理者は、有効期限について、および既に期限切れのシークレットまたは証明書を表示するかどうかを確認するメッセージが表示されます。

"Add-Member" コマンドは、CSV ファイル内の列を作成する役割を担います。 "New-Object" コマンドは、CSV ファイルエクスポートの列に使用するオブジェクトを作成します。 エクスポートを非対話型にする場合は、CSV ファイル パスを使用して、PowerShell で "$Path" 変数を直接変更できます。

| コマンド | メモ |
| --- | --- |
| [Get-MgApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgapplication?view=graph-powershell-1.0&preserve-view=true) | ディレクトリからアプリケーションを取得します。 |
| [Get-MgApplicationOwner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgapplicationowner?view=graph-powershell-1.0&preserve-view=true) | ディレクトリからアプリケーションの所有者を取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/scripts/powershell-export-apps-with-secrets-beyond-required"} -->
## PowerShell サンプル: シークレットと証明書の有効期限が必要な日付を超えたアプリをエクスポートする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-apps-with-secrets-beyond-required
- Service: entra-id / enterprise-apps
- Article date: 2025-01-23
- Summary: Microsoft Entra テナント内の指定したアプリについて、必要な日付を過ぎた後に期限が切れるシークレットと証明書を含むすべてのアプリをエクスポートする PowerShell サンプル。

この PowerShell スクリプトの例では、必要な期間を超えて期限切れになるすべてのアプリ登録のシークレットと証明書をエクスポートします。 ディレクトリから指定されたアプリに対してこのタスクを実行します。 スクリプトは非対話形式で実行されます。 出力は CSV ファイルに保存されます。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

### サンプル スクリプト

```powershell
<#################################################################################
DISCLAIMER:

This is not an official PowerShell Script. We designed it specifically for the situation you have
encountered right now.

Please do not modify or change any preset parameters.

Please note that we will not be able to support the script if it's changed or altered in any way
or used in a different situation for other means.

This code-sample is provided "AS IS" without warranty of any kind, either expressed or implied,
including but not limited to the implied warranties of merchantability and/or fitness for a
particular purpose.

This sample is not supported under any Microsoft standard support program or service.

Microsoft further disclaims all implied warranties including, without limitation, any implied
warranties of merchantability or of fitness for a particular purpose.

The entire risk arising out of the use or performance of the sample and documentation remains with
you.

In no event shall Microsoft, its authors, or anyone else involved in the creation, production, or
delivery of the script be liable for any damages whatsoever (including, without limitation, damages
for loss of business profits, business interruption, loss of business information, or other
pecuniary loss) arising out of the use of or inability to use the sample or documentation, even if
Microsoft has been advised of the possibility of such damages.

#################################################################################>

$loginURL = 'https://login.microsoftonline.com'
$resource = 'https://graph.microsoft.com'

#PARAMETERS TO CHANGE
$ClientID     = 'App ID'
$ClientSecret = 'APP Secret'
$TenantName   = 'TENANT.onmicrosoft.com'

$Months = 'Number of months'
$Path   = 'add a path here\File.csv'
###################################################################
#Repeating Function to get an Access Token based on the parameters:
function Get-RefreshedToken($LoginURL, $ClientID, $ClientSecret, $TenantName) {
    $RequestParameters = @{
        Method = 'POST'
        Uri    = "$LoginURL/$TenantName/oauth2/v2.0/token"
        Body   = @{
            grant_type    = 'client_credentials'
            client_id     = $ClientID
            client_secret = $ClientSecret
            scope         = 'https://graph.microsoft.com/.default'
        }
    }

    Invoke-RestMethod @RequestParameters
}

#BUILD THE ACCESS TOKEN
$RefreshParameters = @{
    LoginURL     = $loginURL
    ClientID     = $ClientID
    ClientSecret = $ClientSecret
    TenantName   = $TenantName
}
$OAuth    = Get-RefreshedToken @RefreshParameters
$Identity = $OAuth.access_token

##############################################

$HeaderParams = @{
    'Authorization' = "$($OAuth.token_type) $($Identity)"
}
$AppsSecrets = 'https://graph.microsoft.com/v1.0/applications'

$ApplicationsList = Invoke-WebRequest -Headers $HeaderParams -Uri $AppsSecrets -Method GET

$Logs        = @()
$NextCounter = 0

do {
    $ApplicationEvents = $ApplicationsList.Content |
        ConvertFrom-Json |
        Select-Object -ExpandProperty value

    foreach ($ApplicationEvent in $ApplicationEvents) {
        $IDs     = $ApplicationEvent.id
        $AppName = $ApplicationEvent.displayName
        $AppID   = $ApplicationEvent.appId
        $Secrets = $ApplicationEvent.passwordCredentials

        $NextCounter++

        foreach ($Secret in $Secrets) {
            $StartDate       = $Secret.startDateTime
            $EndDate         = $Secret.endDateTime
            $pos             = $StartDate.IndexOf('T')
            $LeftPart        = $StartDate.Substring(0, $pos)
            $Position        = $EndDate.IndexOf('T')
            $LeftPartEnd     = $EndDate.Substring(0, $pos)
            $DateStringStart = [Datetime]::ParseExact($LeftPart, 'yyyy-MM-dd', $null)
            $DateStringEnd   = [Datetime]::ParseExact($LeftPartEnd, 'yyyy-MM-dd', $null)
            $OptimalDate     = $DateStringStart.AddMonths($Months)

            if ($OptimalDate -lt $DateStringEnd) {
                $Log = [PSCustomObject]@{
                    'Application'       = $AppName
                    'AppID'             = $AppID
                    'Secret Start Date' = $DateStringStart
                    'Secret End Date'   = $DateStringEnd
                }

                $OwnerRequestParams = @{
                    Headers = $HeaderParams
                    Uri     = "https://graph.microsoft.com/v1.0/applications/$IDs/owners"
                    Method  = 'GET'
                }
                $ApplicationsOwners = Invoke-WebRequest @OwnerRequestParams

                $Users = $ApplicationsOwners.Content |
                    ConvertFrom-Json |
                    Select-Object -ExpandProperty value

                foreach ($User in $Users) {
                    $Owner = $User.displayname
                    $Log | Add-Member -MemberType NoteProperty -Name  'AppOwner' -Value $Owner
                }

                $Logs += $Log
            }
        }

        If ($NextCounter -eq 100) {
            $OData = $ApplicationsList.Content | ConvertFrom-Json
            $AppsSecrets = $OData.'@odata.nextLink'
            try {
                $ListRequestParams = @{
                    UseBasicParsing = $true
                    Headers         = $HeaderParams
                    Uri             = $AppsSecrets
                    Method          = 'GET'
                    ContentType     = 'application/Json'
                }
                $ApplicationsList = Invoke-WebRequest @ListRequestParams
            } catch {
                $_
            }

            $NextCounter = 0

            Start-Sleep -Seconds 1
        }
    }

} while ($AppsSecrets -ne $null)

$Logs | Export-Csv $Path -NoTypeInformation -Encoding UTF8
```

### スクリプトの説明

このスクリプトは非対話形式で動作しています。 これを使用する管理者は、"#PARAMETERS TO CHANGE" セクションの値を変更する必要があります。 独自のアプリ ID、アプリケーション シークレット、テナント名を入力する必要があります。 また、アプリの資格情報の有効期限の期間も指定する必要があります。 最後に、CSV をエクスポートするパスを設定する必要があります。

このスクリプトでは、[Client_Credential Oauth フローを使用](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow) 関数 "RefreshToken" は、管理者によって変更されたパラメーターの値に基づいてアクセス トークンをビルドします。

"Add-Member" コマンドは、CSV ファイル内に列を作成する役割を担います。

| コマンド | 注記 |
| --- | --- |
| [Invoke-WebRequest](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.utility/invoke-webrequest) | Web ページまたは Web サービスに HTTP 要求と HTTPS 要求を送信します。 これは、応答を解析し、リンク、画像、およびその他の重要な HTML 要素のコレクションを返します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/scripts/powershell-export-enterprise-apps-with-expiring-secrets"} -->
## PowerShell サンプル: 期限切れのシークレットと証明書を含むエンタープライズ アプリをエクスポートする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-enterprise-apps-with-expiring-secrets
- Service: entra-id / enterprise-apps
- Article date: 2025-01-23
- Summary: Microsoft Entra テナント内の指定したエンタープライズ アプリの有効期限が切れるシークレットと証明書を含むすべてのエンタープライズ アプリをエクスポートする PowerShell の例。

この PowerShell スクリプトの例では、シークレットと証明書の有効期限が次の X 日後に切れるエンタープライズ アプリケーションをすべてエクスポートします。 また、有効期限が切れているものも含まれます(選択した場合)。 このスクリプトは、エンタープライズ アプリケーションを所有者と共にエクスポートします。 ディレクトリから指定されたエンタープライズ アプリに対してこのアクションを実行します。 出力は CSV ファイルに保存されます。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)がない場合は、開始する前に、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成します。

このサンプルには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) SDK モジュールが必要です。

### サンプル スクリプト

```powershell
<#################################################################################
DISCLAIMER:

This is not an official PowerShell Script. We designed it specifically for the situation you have
encountered right now.

Please do not modify or change any preset parameters.

Please note that we will not be able to support the script if it's changed or altered in any way
or used in a different situation for other means.

This code-sample is provided "AS IS" without warranty of any kind, either expressed or implied,
including but not limited to the implied warranties of merchantability and/or fitness for a
particular purpose.

This sample is not supported under any Microsoft standard support program or service.

Microsoft further disclaims all implied warranties including, without limitation, any implied
warranties of merchantability or of fitness for a particular purpose.

The entire risk arising out of the use or performance of the sample and documentation remains with
you.

In no event shall Microsoft, its authors, or anyone else involved in the creation, production, or
delivery of the script be liable for any damages whatsoever (including, without limitation, damages
for loss of business profits, business interruption, loss of business information, or other
pecuniary loss) arising out of the use of or inability to use the sample or documentation, even if
Microsoft has been advised of the possibility of such damages.

#################################################################################>

Connect-MgGraph -Scopes 'Application.Read.All'

$Applications = Get-MgServicePrincipal -all
$Logs = @()

$Messages = @{
    ExpirationDays = @{
        Info   = 'Filter the applications to log by the number of days until their secrets expire.'
        Prompt = 'Enter the number of days until the secrets expire as an integer.'
    }
    AlreadyExpired = @{
        Info   = 'Would you like to see Applications with already expired secrets as well?'
        Prompt = 'Enter Yes or No'
    }
    DurationNotice = @{
        Info = @(
            'The operation is running and will take longer the more applications the tenant has...'
            'Please wait...'
        ) -join ' '
    }
    Export = @{
        Info = 'Where should the CSV file export to?'
        Prompt = 'Enter the full path in the format of <C:\Users\<USER>\Desktop\Users.csv>'
    }
}

Write-Host $Messages.ExpirationDays.Info -ForegroundColor Green
$DaysUntilExpiration = Read-Host -Prompt $Messages.ExpirationDays.Prompt

Write-Host $Messages.AlreadyExpired.Info -ForegroundColor Green
$IncludeAlreadyExpired = Read-Host -Prompt $Messages.AlreadyExpired.Prompt

$Now = Get-Date

Write-Host $Messages.DurationNotice.Info -ForegroundColor yellow

foreach ($App in $Applications) {
    $AppName = $App.DisplayName
    $AppID   = $App.Id
    $ApplID  = $App.AppId

    $AppCreds = Get-MgServicePrincipal -ServicePrincipalId $AppID |
        Select-Object PasswordCredentials, KeyCredentials

    $Secrets = $AppCreds.PasswordCredentials
    $Certs   = $AppCreds.KeyCredentials

    foreach ($Secret in $Secrets) {
        $StartDate  = $Secret.StartDateTime
        $EndDate    = $Secret.EndDateTime
        $SecretName = $Secret.DisplayName

        $Owner    = Get-MgServicePrincipalOwner -ServicePrincipalId $App.Id
        $Username = $Owner.AdditionalProperties.userPrincipalName -join ';'
        $OwnerID  = $Owner.Id -join ';'

        if ($null -eq $Owner.AdditionalProperties.userPrincipalName) {
            $Username = @(
                $Owner.AdditionalProperties.displayName
                '**<This is an Application>**'
            ) -join ' '
        }

        if ($null -eq $Owner.AdditionalProperties.displayName) {
            $Username = '<<No Owner>>'
        }

        $RemainingDaysCount = $EndDate - $Now |
            Select-Object -ExpandProperty Days

        if ($IncludeAlreadyExpired -eq 'No') {
            if ($RemainingDaysCount -le $DaysUntilExpiration -and $RemainingDaysCount -ge 0) {
                $Logs += [PSCustomObject]@{
                    'ApplicationName'        = $AppName
                    'ApplicationID'          = $ApplID
                    'Secret Name'            = $SecretName
                    'Secret Start Date'      = $StartDate
                    'Secret End Date'        = $EndDate
                    'Certificate Name'       = $Null
                    'Certificate Start Date' = $Null
                    'Certificate End Date'   = $Null
                    'Owner'                  = $Username
                    'Owner_ObjectID'         = $OwnerID
                }
            }
        } elseif ($IncludeAlreadyExpired -eq 'Yes') {
            if ($RemainingDaysCount -le $DaysUntilExpiration) {
                $Logs += [pscustomobject]@{
                    'ApplicationName'        = $AppName
                    'ApplicationID'          = $ApplID
                    'Secret Name'            = $SecretName
                    'Secret Start Date'      = $StartDate
                    'Secret End Date'        = $EndDate
                    'Certificate Name'       = $Null
                    'Certificate Start Date' = $Null
                    'Certificate End Date'   = $Null
                    'Owner'                  = $Username
                    'Owner_ObjectID'         = $OwnerID
                }
            }
        }
    }

    foreach ($Cert in $Certs) {
        $StartDate = $Cert.StartDateTime
        $EndDate   = $Cert.EndDateTime
        $CertName  = $Cert.DisplayName

        $RemainingDaysCount = $EndDate - $Now |
            Select-Object -ExpandProperty Days

        $Owner    = Get-MgServicePrincipalOwner -ServicePrincipalId $App.Id
        $Username = $Owner.AdditionalProperties.userPrincipalName -join ';'
        $OwnerID  = $Owner.Id -join ';'

        if ($null -eq $Owner.AdditionalProperties.userPrincipalName) {
            $Username = @(
                $Owner.AdditionalProperties.displayName
                '**<This is an Application>**'
            ) -join ' '
        }
        if ($null -eq $Owner.AdditionalProperties.displayName) {
            $Username = '<<No Owner>>'
        }

        if ($IncludeAlreadyExpired -eq 'No') {
            if ($RemainingDaysCount -le $DaysUntilExpiration -and $RemainingDaysCount -ge 0) {
                $Logs += [pscustomobject]@{
                    'ApplicationName'        = $AppName
                    'ApplicationID'          = $ApplID
                    'Secret Name'            = $Null
                    'Certificate Name'       = $CertName
                    'Certificate Start Date' = $StartDate
                    'Certificate End Date'   = $EndDate
                    'Owner'                  = $Username
                    'Owner_ObjectID'         = $OwnerID
                    'Secret Start Date'      = $Null
                    'Secret End Date'        = $Null
                }
            }
        } elseif ($IncludeAlreadyExpired -eq 'Yes') {
            if ($RemainingDaysCount -le $DaysUntilExpiration) {
                $Logs += [pscustomobject]@{
                    'ApplicationName'        = $AppName
                    'ApplicationID'          = $ApplID
                    'Certificate Name'       = $CertName
                    'Certificate Start Date' = $StartDate
                    'Certificate End Date'   = $EndDate
                    'Owner'                  = $Username
                    'Owner_ObjectID'         = $OwnerID
                    'Secret Start Date'      = $Null
                    'Secret End Date'        = $Null
                }
            }
        }
    }
}

Write-Host $Messages.Export.Info -ForegroundColor Green
$Path = Read-Host -Prompt $Messages.Export.Prompt
$Logs | Export-Csv $Path -NoTypeInformation -Encoding UTF8
```

### スクリプトの説明

スクリプトは変更なしで直接使用できます。 管理者は、有効期限について、および既に期限切れのシークレットまたは証明書を表示するかどうかを確認するメッセージが表示されます。

"Add-Member" コマンドは、CSV ファイル内の列を作成する役割を担います。 "New-Object" コマンドは、CSV ファイルエクスポートの列に使用するオブジェクトを作成します。 エクスポートを非対話型にする場合は、CSV ファイル パスを使用して、PowerShell で "$Path" 変数を直接変更できます。

| コマンド | メモ |
| --- | --- |
| [Get-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal?view=graph-powershell-1.0&preserve-view=true) | ディレクトリからエンタープライズ アプリケーションを取得します。 |
| [Get-MgServicePrincipalOwner](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipalowner?view=graph-powershell-1.0&preserve-view=true) | ディレクトリからエンタープライズ アプリケーションの所有者を取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/secure-hybrid-access"} -->
## 安全なハイブリッド アクセス、Microsoft Entra ID がレガシ アプリを保護する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access
- Service: entra-id / enterprise-apps
- Article date: 2023-01-17
- Summary: オンプレミス、パブリッククラウド、またはプライベート クラウドにあるレガシ アプリケーションを Microsoft Entra ID と統合するためのパートナー ソリューションを見つけます。

この記事では、Microsoft Entra ID に接続して、オンプレミスやクラウドでお使いのレガシ認証アプリケーションを保護する方法を説明します。

- **アプリケーション プロキシ**:

    - [Microsoft Entra アプリケーション プロキシからのオンプレミス アプリケーションへのリモート アクセス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)
    - クラウドとオンプレミスのユーザー、アプリ、データを保護する
    - [これを使用してオンプレミスの Web アプリケーションを外部に発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)
- **Microsoft Entra ID パートナー統合を通じてハイブリッド アクセスをセキュリティで保護する**:

    - 事前構築済みのソリューション
    - [アプリケーションごとに条件付きアクセス ポリシーを適用する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access-integrations#apply-conditional-access-policies)

アプリケーション プロキシに加えて、[Microsoft Entra の条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)と [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection)を使用してセキュリティ態勢を強化できます。

### シングル サインオンと多要素認証

Microsoft Entra ID を ID プロバイダー (IdP) として使用することで、[シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on) や [Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) などの最新の認証および認可方式を使用し、お使いのレガシ オンプレミス アプリケーションをセキュリティで保護することができます。

### アプリケーション プロキシを使用した安全なハイブリッド アクセス

アプリケーション プロキシを使用して、クラウドとオンプレミスのユーザー、アプリ、データを保護します。 このツールを使用して、オンプレミスの Web アプリケーションへのリモート アクセスをセキュリティで保護します。 ユーザーは仮想プライベート ネットワーク (VPN) を使用する必要はありません。SSO を使用してデバイスからアプリケーションに接続します。

詳細情報:

- [Microsoft Entra アプリケーション プロキシからのオンプレミス アプリケーションへのリモート アクセス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)
- [チュートリアル: Microsoft Entra ID のアプリケーション プロキシを使用してリモート アクセスするためのオンプレミス アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-add-on-premises-application)
- [アプリケーション プロキシ アプリケーションに SSO を構成する方法](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso)
- [Microsoft Entra アプリケーション プロキシを使用してリモート ユーザーにオンプレミス アプリを発行する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)

#### アプリケーションの発行とアクセスの管理

サービスとしてのリモート アクセスを提供するアプリケーション プロキシを使用して、企業ネットワーク外のユーザーにアプリケーションを発行します。 オンプレミスのアプリケーションを変更することなく、クラウド アクセス管理を改善します。 [Microsoft Entra アプリケーション プロキシの展開を](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-deployment-plan)計画します。

### アプリ用のパートナー統合: オンプレミスとレガシ認証

Microsoft は、オンプレミス アプリケーション用の事前構築済みソリューションと、レガシ認証を使用するアプリケーションを提供するさまざまな企業と提携しています。 次の図は、サインインからアプリおよびデータへのセキュリティで保護されたアクセスまでのユーザー フローを示しています。

[Image: セキュリティで保護されたハイブリッド アクセス統合と、ユーザー アクセスを提供するアプリケーション プロキシの図。]

#### Microsoft Entra ID パートナー統合を通じてハイブリッド アクセスをセキュリティで保護する

次のパートナーは、[アプリケーションごとの条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access-integrations#apply-conditional-access-policies)をサポートするソリューションを提供しています。 パートナーと Microsoft Entra 統合ドキュメントについては、次のセクションの表を参照してください。

| パートナー | 統合ドキュメント |
| --- | --- |
| Akamai Technologies | [チュートリアル: Microsoft Entra SSO と Akamai の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/akamai-tutorial) |
| Citrix Systems Inc. | [チュートリアル: Microsoft Entra SSO と Citrix ADC SAML Connector for Microsoft Entra ID の統合 (Kerberos ベースの認証)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrix-netscaler-tutorial) |
| Cloudflare, Inc. | [チュートリアル: Cloudflare と Microsoft Entra ID の統合を構成して安全なハイブリッド アクセスを実現する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/cloudflare-integration) |
| データウィザ | [チュートリアル: Microsoft Entra ID と Datawiza を使用してセキュリティ保護されたハイブリッド アクセスを構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-configure-sha) |
| F5, Inc. | [F5 BIG-IP と Microsoft Entra ID の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)[チュートリアル: Microsoft Entra SSO 用に F5 BIG-IP SSL-VPN を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-passwordless-vpn) |
| Progress Software Corporation、Progress Kemp | [チュートリアル: Microsoft Entra SSO と Kemp LoadMaster Microsoft Entra の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kemp-tutorial) |
| Perimeter 81 有限会社 | [チュートリアル: Microsoft Entra SSO と Perimeter 81 の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/perimeter-81-tutorial) |
| Silverfort | [チュートリアル: Microsoft Entra ID と Silverfort を使用してセキュリティ保護されたハイブリッド アクセスを構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/silverfort-integration) |
| Strata Identity, Inc. | [Microsoft Entra SSO と Maverics Identity Orchestrator SAML Connector を統合する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/maverics-identity-orchestrator-saml-connector-tutorial) |

##### 事前構築済みのソリューションと統合ドキュメントを持つパートナー

| パートナー | 統合ドキュメント |
| --- | --- |
| Amazon Web Service, Inc. | [チュートリアル: Microsoft Entra SSO と AWS ClientVPN の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aws-clientvpn-tutorial) |
| Check Point Software Technologies Ltd. | [チュートリアル: Microsoft Entra シングル SSO と Check Point Remote Secure Access VPN の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/check-point-remote-access-vpn-tutorial) |
| Cisco Systems, Inc. | [チュートリアル: Microsoft Entra SSO と Cisco Secure Firewall - Secure Client の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-secure-firewall-secure-client) |
| Fortinet, Inc. | [チュートリアル: Microsoft Entra SSO と FortiGate SSL VPN の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortigate-ssl-vpn-tutorial) |
| パロアルトネットワークス | [チュートリアル: Microsoft Entra SSO と Palo Alto Networks Admin UI の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/paloaltoadmin-tutorial) |
| Pulse Secure | [チュートリアル: Microsoft Entra SSO と Pulse Connect Secure (PCS) の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pulse-secure-pcs-tutorial)[チュートリアル: Microsoft Entra SSO と Pulse Secure Virtual Traffic Manager の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pulse-secure-virtual-traffic-manager-tutorial) |
| Zscaler, Inc. | [チュートリアル: Microsoft Entra ID と Zscaler Private Access の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscalerprivateaccess-tutorial) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/secure-hybrid-access-integrations"} -->
## Microsoft Entra 統合による安全なハイブリッド アクセス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/secure-hybrid-access-integrations
- Service: entra-id / enterprise-apps
- Article date: 2023-01-19
- Summary: 顧客が SaaS アプリケーションを検出して Microsoft Entra ID に移行し、従来の認証方法を使用するアプリを Microsoft Entra ID に接続できるよう支援します。

Microsoft Entra ID では、アプリケーションのセキュリティを維持するのに役立つ最新の認証プロトコルがサポートされています。 ただし、多くのビジネス アプリケーションは保護された企業ネットワークの内部で動作し、レガシの認証方法が使用されているものもあります。 企業はゼロ トラスト戦略を立て、ハイブリッドおよびクラウド環境のサポートしているので、アプリを Microsoft Entra ID に接続し、レガシ アプリケーションに認証を提供するソリューションがあります。

詳細情報: [ゼロ トラスト セキュリティ](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/zero-trust)

Microsoft Entra ID では、最新のプロトコルがネイティブにサポートされています。

- セキュリティ アサーション マークアップ言語 (SAML)
- Web サービス フェデレーション (WS-Fed)
- OpenID コネクト (OIDC)

Microsoft Entra アプリケーション プロキシでは、Kerberos とヘッダーベースの認証がサポートされています。 Secure Shell (SSH)、(Microsoft Windows NT LAN Manager) NTLM、ライトウェイト ディレクトリ アクセス プロトコル (LDAP)、Cookie などのその他のプロトコルはサポートされていません。 ただし、独立系ソフトウェア ベンダー (ISV) は、これらのアプリケーションを Microsoft Entra ID に接続するためのソリューションを作成できます。

ISV は、顧客がサービスとしてのソフトウェア (SaaS) アプリケーションを検出して、Microsoft Entra ID に移行するのを支援できます。 彼らはレガシの認証方法を使用するアプリを Microsoft Entra ID に接続することもできます。 顧客は Microsoft Entra ID に統合して、アプリ管理の簡素化とゼロ トラスト原則の実装を行うことができます。

### ソリューションの概要

構築するソリューションには次のものを含めることができます。

- **アプリの検出**- 多くの場合、顧客は使用しているアプリケーションすべてを把握してはいません
    - アプリケーションの検出でアプリケーションを検出し、アプリと Microsoft Entra ID の統合を促進します
- **アプリの移行**- Microsoft Entra 管理センターを使用せずに、アプリを Microsoft Entra ID と統合するワークフローを作成します
    - 顧客が現在使用しているアプリを統合します
- **レガシ認証のサポート** - レガシ認証方法とシングル サインオン (SSO) を使用してアプリを接続します
- **条件付きアクセス** - 顧客が Microsoft Entra 管理センターを使用せずに、ソリューション内のアプリに Microsoft Entra ポリシーを適用できるようにします

詳細情報: [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)

技術的な考慮事項と推奨事項については、以降のセクションを参照してください。

### Azure Marketplace へのアプリケーションの発行

Azure Marketplace は、IT 管理者にとって信頼できるアプリケーション ソースです。 アプリケーションは Microsoft Entra ID と互換性があり、SSO をサポートしており、ユーザー プロビジョニングを自動化し、自動アプリ登録を使用して外部テナントに統合します。

アプリケーションを Microsoft Entra ID と事前に統合して、SSO や自動プロビジョニングをサポートできます。 「[Microsoft Entra アプリケーション ギャラリーでのアプリケーションの公開の要求を送信する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)」を参照してください。

信頼された発行元であると顧客がわかるように、確認済みの発行元になることをお勧めします。 「[発行者の確認](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)」を参照してください。

### IT 管理者のシングル サインオンを有効にする

ソリューションに対する IT 管理者の SSO を有効にする方法はいくつかあります。 [「シングル サインオンのデプロイを計画する」の SSO のオプション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment#single-sign-on-options)に関する記事を参照してください。

Microsoft Graph は OIDC/OAuth を使用しています。 顧客は OIDC を使用してソリューションにサインインします。 Microsoft Graph とやりとりするには、Microsoft Entra ID が発行する JSON Web トークン (JWT) を使用します。 「[Microsoft ID プラットフォームでの OpenID Connect](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)」を参照してください。

IT 管理者の SSO に SAML を使用するソリューションの場合、SAML トークンでソリューションと Microsoft Graph とのやり取りはできません。 IT 管理者の SSO には SAML を使用できますが、ソリューションで Microsoft Entra ID との OIDC 統合がサポートされている必要があります。これにより、Microsoft Entra ID から JWT を取得して、Microsoft Graph とのやりとりできます。 「[Microsoft ID プラットフォームでの SAML プロトコルの使用方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-protocol-reference)」を参照してください。

次のいずれかの SAML アプローチを利用できます。

- **推奨される SAML アプローチ**: Azure Marketplace で新しい登録を作成します。これは OIDC アプリです。 顧客は自分のテナントに SAML と OIDC の両方のアプリを追加します。 アプリケーションが Microsoft Entra ギャラリーにない場合、ギャラリーに含まれないマルチテナント アプリで開始できます。
    - [Microsoft Entra アプリ ギャラリーから OpenID Connect OAuth アプリケーションを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/openidoauth-tutorial)
    - [アプリケーションをマルチテナントにする](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant)
- **代替の SAML アプローチ**: 顧客は自分の Microsoft Entra テナントに OIDC のアプリケーションの登録を作成し、URI、エンドポイント、アクセス許可を設定できます

クライアント資格情報の付与タイプを使用します。これには、顧客がクライアント ID とシークレットを入力できるようにするソリューションが必要です。 ソリューションでは、この情報を格納する必要もあります。 Microsoft Entra ID から JWT を取得し、それを使用して Microsoft Graph とやりとりします。 「[トークンを取得する](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow#get-a-token)」を参照してください。 Microsoft Entra テナントでアプリケーション登録を作成する方法に関する顧客ドキュメントを準備することをお勧めします。 エンドポイント、URI、およびアクセス許可を含めてください。

注

アプリケーションを IT 管理者またはユーザーの SSO に使用するには、事前に顧客 IT 管理者が自分のテナント内でそのアプリケーションに同意する必要があります。 「[アプリケーションに対してテナント全体の管理者の同意を付与する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent)」を参照してください。

### 認証フロー

ソリューションの認証フローでは、次のシナリオがサポートされています。

- 顧客 IT 管理者がソリューションを管理するために SSO を使用してサインインする
- 顧客 IT 管理者がソリューションを使用して、Microsoft Graph によってアプリケーションを Microsoft Entra ID と統合する
- ユーザーが、ソリューションと Microsoft Entra ID で保護されたレガシ アプリケーションにサインインする

#### 顧客 IT 管理者がソリューションにシングル サインオンする

顧客 IT 管理者がサインインする際、ソリューションでは SSO に SAML または OIDC を使用できます。 IT 管理者は Microsoft Entra 資格情報を使用してソリューションにサインインすることをお勧めします。これにより、現在のセキュリティ制御を使用できるようになります。 SAML または OIDC を介した SSO の場合は Microsoft Entra ID と統合します。

次の図は、ユーザー認証フローを示しています。

[Image: 管理者がサインインするために Microsoft Entra ID にリダイレクトされた後、ソリューションにリダイレクトされる図。]

1. IT 管理者が自分の Microsoft Entra 資格情報を使用してソリューションにサインインします
2. ソリューションによって IT 管理者は SAML または OIDC サインイン要求と共に Microsoft Entra ID にリダイレクトされます
3. Microsoft Entra によって IT 管理者は認証され、ソリューション内で認可される SAML トークンまたは JWT と共にソリューションにリダイレクトされます

#### IT 管理者がアプリケーションを Microsoft Entra ID と統合する

IT 管理者は、ソリューションを使用してアプリケーションを Microsoft Entra ID と統合します。このソリューションは、Microsoft Graph を使用してアプリケーションの登録と Microsoft Entra 条件付きアクセス ポリシーを作成します。

次の図は、ユーザー認証フローを示しています。

[Image: IT 管理者、Microsoft Entra ID、ソリューション、Microsoft Graph の間の相互作用の図。]

1. IT 管理者が自分の Microsoft Entra 資格情報を使用してソリューションにサインインします
2. ソリューションによって IT 管理者は SAML または OIDC サインイン要求と共に Microsoft Entra ID にリダイレクトされます
3. Microsoft Entra によって IT 管理者は認証され、認可用の SAML トークンまたは JWT と共にソリューションにリダイレクトされます
4. IT 管理者がアプリケーションを Microsoft Entra ID と統合すると、このソリューションは JWT で Microsoft Graph を呼び出してアプリケーションを登録するか、Microsoft Entra 条件付きアクセス ポリシーを適用します

#### ユーザーがアプリケーションにサインインする

ユーザーがアプリケーションにサインインするとき、OIDC または SAML を使用します。 アプリケーションが Microsoft Graph または Microsoft Entra で保護された API と対話する必要がある場合は、OIDC を使用するように構成することをお勧めします。 この構成により、JWT が Microsoft Graph とのやり取りに適用されるようになります。 アプリケーションで Microsoft Graph、または Microsoft Entra で保護された API とやりとりする必要がない場合は、SAML を使用します。

次の図は、ユーザー認証フローを示しています。

[Image: ユーザー、Microsoft Entra ID、ソリューション、アプリの間の相互作用の図。]

1. ユーザーがアプリケーションにサインインします
2. ソリューションによってユーザーは SAML または OIDC サインイン要求と共に Microsoft Entra ID にリダイレクトされます
3. Microsoft Entra によってユーザーは認証され、認可用の SAML トークンまたは JWT と共にソリューションにリダイレクトされます
4. ソリューションでは、アプリケーション プロトコルを使用することで要求が許可されます

### Microsoft Graph API

次の API を使用することをお勧めします。 Microsoft Entra ID を使用して、委任されたアクセス許可またはアプリケーションのアクセス許可を構成します。 このソリューションには、委任されたアクセス許可を使用します。

- **アプリケーション テンプレート API**- Azure Marketplace で、この API を使用して一致するアプリケーション テンプレートを見つけます
    - 必要なアクセス許可: Application.Read.All
- **アプリケーションの登録 API**- ユーザーがソリューションでセキュリティ保護されたアプリケーションにサインインするために OIDC または SAML アプリケーションの登録を作成します
    - 必要なアクセス許可: Application.Read.All、Application.ReadWrite.All
- **サービス プリンシパル API**: アプリを登録した後、サービス プリンシパル オブジェクトを更新して、SSO プロパティを設定します
    - 必要なアクセス許可: Application.ReadWrite.All、Directory.AccessAsUser.All、AppRoleAssignment.ReadWrite.All (割り当て用)
- **条件付きアクセス API**- Microsoft Entra 条件付きアクセス ポリシーをユーザー アプリケーションに適用します
    - 必要なアクセス許可: Policy.Read.All、Policy.ReadWrite.ConditionalAccess、Application.Read.All

詳細情報: [Microsoft Graph API を使用する](https://learn.microsoft.com/ja-jp/graph/use-the-api?context=graph/api/1.0&view=graph-rest-1.0&preserve-view=true)

### Microsoft Graph API のシナリオ

アプリケーションの登録の実装、レガシ アプリケーションの接続、条件付きアクセス ポリシーの有効化を行うには、次の情報を使用します。 管理者の同意の自動化、トークン署名証明書の取得、ユーザーとグループの割り当てについて説明します。

#### Microsoft Graph API を使用してアプリを Microsoft Entra ID に登録する

##### Azure Marketplace 内のアプリを追加する

顧客が使用する一部のアプリケーションは、[Azure Marketplace](https://azuremarketplace.microsoft.com/marketplace/apps) にあります。 外部テナントにアプリケーションを追加するソリューションを作成できます。 Microsoft Graph API で次の例を使用して、Azure Marketplace を検索してテンプレートを見つけます。

注

アプリケーション テンプレート API では、表示名の大文字と小文字は区別されます。

```http
Authorization: Required with a valid Bearer token
Method: Get

https://graph.microsoft.com/v1.0/applicationTemplates?$filter=displayname eq "Salesforce.com"
```

API 呼び出しから一致するものが見つかったら、ID を取得します。 次の API 呼び出しを行い、JSON 本文内にアプリケーションの表示名を指定します。

```https
Authorization: Required with a valid Bearer token
Method: POST
Content-type: application/json

https://graph.microsoft.com/v1.0/applicationTemplates/cd3ed3de-93ee-400b-8b19-b61ef44a0f29/instantiate
{
    "displayname": "Salesforce.com"
}
```

API 呼び出しを行った後、サービス プリンシパル オブジェクトを生成します。 今後の API 呼び出しで使用するアプリケーション ID とサービス プリンシパル ID を取得します。

SAML プロトコルとログイン URL を指定して、サービス プリンシパル オブジェクトに対して PATCH を実行します。

```https
Authorization: Required with a valid Bearer token
Method: PATCH
Content-type: servicePrincipal/json

https://graph.microsoft.com/v1.0/servicePrincipals/aaaaaaaa-bbbb-cccc-1111-222222222222
{
    "preferredSingleSignOnMode":"saml",
    "loginURL": "https://www.salesforce.com"
}
```

リダイレクト URI と識別子 URI を指定して、アプリケーション オブジェクトに対して PATCH を実行します。

```https
Authorization: Required with a valid Bearer token
Method: PATCH
Content-type: application/json

https://graph.microsoft.com/v1.0/applications/00001111-aaaa-2222-bbbb-3333cccc4444
{
    "web": {
    "redirectUris":["https://www.salesforce.com"]},
    "identifierUris":["https://www.salesforce.com"]
}
```

##### Azure Marketplace にないアプリを追加する

Azure Marketplace に一致するものがない場合や、カスタム アプリケーションを統合する場合は、テンプレート ID 8adf8e6e-67b2-4cf2-a259-e3dc5476c621 を使用して Microsoft Entra ID にカスタム アプリケーションを登録します。 そして、次の API 呼び出しを行い、JSON 本文内にアプリケーション表示名を指定します。

```https
Authorization: Required with a valid Bearer token
Method: POST
Content-type: application/json

https://graph.microsoft.com/v1.0/applicationTemplates/8adf8e6e-67b2-4cf2-a259-e3dc5476c621/instantiate
{
    "displayname": "Custom SAML App"
}
```

API 呼び出しを行った後、サービス プリンシパル オブジェクトを生成します。 今後の API 呼び出しで使用するアプリケーション ID とサービス プリンシパル ID を取得します。

SAML プロトコルとログイン URL を指定して、サービス プリンシパル オブジェクトに対して PATCH を実行します。

```https
Authorization: Required with a valid Bearer token
Method: PATCH
Content-type: servicePrincipal/json

https://graph.microsoft.com/v1.0/servicePrincipals/aaaaaaaa-bbbb-cccc-1111-222222222222
{
    "preferredSingleSignOnMode":"saml",
    "loginURL": "https://www.samlapp.com"
}
```

リダイレクト URI と識別子 URI を指定して、アプリケーション オブジェクトに対して PATCH を実行します。

```https
Authorization: Required with a valid Bearer token
Method: PATCH
Content-type: application/json

https://graph.microsoft.com/v1.0/applications/00001111-aaaa-2222-bbbb-3333cccc4444
{
    "web": {
    "redirectUris":["https://www.samlapp.com"]},
    "identifierUris":["https://www.samlapp.com"]
}
```

##### Microsoft Entra シングル サインオンの使用

SaaS アプリケーションが Microsoft Entra ID に登録されたら、アプリケーションで ID プロバイダー (IdP) として Microsoft Entra ID の使用を開始する必要があります。

- **アプリケーションで One Click SSO がサポートされている**- Microsoft Entra ID によってアプリケーションが有効になります。 顧客は Microsoft Entra 管理センターで、サポートされている SaaS アプリケーションに対して管理者資格情報を使用して One Click SSO を実行します。
    - 詳細情報: [アプリの One Click シングル サインオンの構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/one-click-sso-tutorial)
- **アプリケーションで One Click SSO がサポートされていない**- 顧客がアプリケーションで Microsoft Entra ID を使用できるようにします。
    - [SaaS アプリケーションと Microsoft Entra ID の統合に関するチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)

#### レガシ認証を使用してアプリを Microsoft Entra ID に接続する

ソリューションにより、サポートされていないアプリケーションでも、顧客が SSO と Microsoft Entra 機能を使用できるようにすることが可能です。 レガシ プロトコルによるアクセスを許可するには、アプリケーションで Microsoft Entra ID を呼び出してユーザーを認証し、[Microsoft Entra 条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を適用します。 コンソールからこの統合を有効にしてください。 ソリューションと Microsoft Entra ID の間で SAML または OIDC アプリケーションの登録を作成します。

##### SAML のアプリケーションの登録を作成する

カスタム アプリケーション テンプレート ID の 8adf8e6e-67b2-4cf2-a259-e3dc5476c621 を使用します。 そして、次の API 呼び出しを行い、JSON 本文内に表示名を指定します。

```https
Authorization: Required with a valid Bearer token
Method: POST
Content-type: application/json

https://graph.microsoft.com/v1.0/applicationTemplates/8adf8e6e-67b2-4cf2-a259-e3dc5476c621/instantiate
{
    "displayname": "Custom SAML App"
}
```

API 呼び出しを行った後、サービス プリンシパル オブジェクトを生成します。 今後の API 呼び出しで使用するアプリケーション ID とサービス プリンシパル ID を取得します。

SAML プロトコルとログイン URL を指定して、サービス プリンシパル オブジェクトに対して PATCH を実行します。

```https
Authorization: Required with a valid Bearer token
Method: PATCH
Content-type: servicePrincipal/json

https://graph.microsoft.com/v1.0/servicePrincipals/aaaaaaaa-bbbb-cccc-1111-222222222222
{
    "preferredSingleSignOnMode":"saml",
    "loginURL": "https://www.samlapp.com"
}
```

リダイレクト URI と識別子 URI を指定して、アプリケーション オブジェクトに対して PATCH を実行します。

```https
Authorization: Required with a valid Bearer token
Method: PATCH
Content-type: application/json

https://graph.microsoft.com/v1.0/applications/00001111-aaaa-2222-bbbb-3333cccc4444
{
    "web": {
    "redirectUris":["https://www.samlapp.com"]},
    "identifierUris":["https://www.samlapp.com"]
}
```

##### OIDC のアプリケーションの登録を作成する

カスタム アプリケーション向けテンプレート ID の 8adf8e6e-67b2-4cf2-a259-e3dc5476c621 を使用します。 次の API 呼び出しを行い、JSON 本文内に表示名を指定します。

```https
Authorization: Required with a valid Bearer token
Method: POST
Content-type: application/json

https://graph.microsoft.com/v1.0/applicationTemplates/8adf8e6e-67b2-4cf2-a259-e3dc5476c621/instantiate
{
    "displayname": "Custom OIDC App"
}
```

API 呼び出しから、今後の API 呼び出しで使用するアプリケーション ID とサービス プリンシパル ID を取得します。

```https
Authorization: Required with a valid Bearer token
Method: PATCH
Content-type: application/json

https://graph.microsoft.com/v1.0/applications/{Application Object ID}
{
    "web": {
    "redirectUris":["https://www.samlapp.com"]},
    "identifierUris":["[https://www.samlapp.com"],
    "requiredResourceAccess": [
    {
        "resourceAppId": "00000003-0000-0000-c000-000000000000",
        "resourceAccess": [
        {
            "id": "7427e0e9-2fba-42fe-b0c0-848c9e6a8182",
            "type": "Scope"
        },
        {
            "id": "e1fe6dd8-ba31-4d61-89e7-88639da4683d",
            "type": "Scope"
        },
        {
            "id": "37f7f235-527c-4136-accd-4a02d197296e",
            "type": "Scope"
        }]
    }]
}
```

注

`resourceAccess` ノード内の API のアクセス許可により、そのアプリケーションに openid、User.Read、offline\_access アクセス許可 (これはサインインを有効にします) が付与されます。 「[Microsoft Graph のアクセス許可の概要](https://learn.microsoft.com/ja-jp/graph/permissions-overview)」を参照してください。

#### 条件付きアクセス ポリシーを適用する

顧客とパートナーは Microsoft Graph API を使用して、[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)をアプリケーションごとに作成したり、適用したりできます。 パートナーの場合、顧客は Microsoft Entra 管理センターを使用せずにソリューションからこれらのポリシーを適用できます。 Microsoft Entra 条件付きアクセス ポリシーを適用するには、次の 2 つのオプションがあります。

- アプリケーションを条件付きアクセス ポリシーに割り当てる
- 新しい条件付きアクセス ポリシーを作成し、アプリケーションをそれに割り当てる

##### 条件付きアクセス ポリシーを使用する

条件付きアクセス ポリシーの一覧を取得するには、次のクエリを実行します。 変更するポリシー オブジェクト ID を取得します。

```https
Authorization: Required with a valid Bearer token
Method:GET

https://graph.microsoft.com/v1.0/identity/conditionalAccess/policies
```

そのポリシーに対して PATCH を実行するには、JSON 本文内の `includeApplications` のスコープにそのアプリケーション オブジェクト ID を含めます。

```https
Authorization: Required with a valid Bearer token
Method: PATCH

https://graph.microsoft.com/v1.0/identity/conditionalAccess/policies/{policyid}
{
    "displayName":"Existing Conditional Access Policy",
    "state":"enabled",
    "conditions": 
    {
        "applications": 
        {
            "includeApplications":[
                "00000003-0000-0ff1-ce00-000000000000", 
                "{Application Object ID}"
            ]
        },
        "users": {
            "includeUsers":[
                "All"
            ] 
        }
    },
    "grantControls": 
    {
        "operator":"OR",
        "builtInControls":[
            "mfa"
        ]
    }
}
```

##### 新しい条件付きアクセス ポリシーを作成する

JSON 本文内の `includeApplications` のスコープにそのアプリケーション オブジェクト ID を追加します。

```https
Authorization: Required with a valid Bearer token
Method: POST

https://graph.microsoft.com/v1.0/identity/conditionalAccess/policies/
{
    "displayName":"New Conditional Access Policy",
    "state":"enabled",
    "conditions": 
    {
        "applications": {
            "includeApplications":[
                "{Application Object ID}"
            ]
        },
        "users": {
            "includeUsers":[
                "All"
            ]
        }
    },
    "grantControls": {
        "operator":"OR",
        "builtInControls":[
            "mfa"
        ]
    }
}
```

```https
#Policy Template for Requiring Compliant Device

{
    "displayName":"Enforce Compliant Device",
    "state":"enabled",
    "conditions": {
        "applications": {
            "includeApplications":[
                "{Application Object ID}"
            ]
        },
        "users": {
            "includeUsers":[
                "All"
            ]
        }
    },
    "grantControls": {
        "operator":"OR",
        "builtInControls":[
            "compliantDevice",
            "domainJoinedDevice"
        ]
    }
}

#Policy Template for Block

{
    "displayName":"Block",
    "state":"enabled",
    "conditions": {
        "applications": {
            "includeApplications":[
                "{Application Object ID}"
            ]
        },
        "users": {
            "includeUsers":[
                "All"
            ] 
        }
    },
    "grantControls": {
        "operator":"OR",
        "builtInControls":[
            "block"
        ]
    }
}
```

#### 管理者の同意を自動化する

顧客がソリューションから Microsoft Entra ID にアプリケーションを追加している場合は、Microsoft Graph を使用して管理者の同意を自動化できます。 API 呼び出しで作成したアプリケーション サービス プリンシパル オブジェクト ID と、外部テナントからの Microsoft Graph のサービス プリンシパル オブジェクト ID が必要です。

次の API 呼び出しを行って、Microsoft Graph のサービス プリンシパル オブジェクト ID を取得します。

```https
Authorization: Required with a valid Bearer token
Method:GET

https://graph.microsoft.com/v1.0/serviceprincipals/?$filter=appid eq '00000003-0000-0000-c000-000000000000'&$select=id,appDisplayName
```

管理者の同意を自動化するには、次の API 呼び出しを行います。

```https
Authorization: Required with a valid Bearer token
Method: POST
Content-type: application/json

https://graph.microsoft.com/v1.0/oauth2PermissionGrants
{
    "clientId":"{Service Principal Object ID of Application}",
    "consentType":"AllPrincipals",
    "principalId":null,
    "resourceId":"{Service Principal Object ID Of Microsoft Graph}",
    "scope":"openid user.read offline_access}"
}
```

#### トークン署名証明書を取得する

トークン署名証明書の公開部分を取得するには、そのアプリケーションの Microsoft Entra メタデータ エンドポイントから `GET` を使用します。

```https
Method:GET

https://login.microsoftonline.com/{Tenant_ID}/federationmetadata/2007-06/federationmetadata.xml?appid={Application_ID}
```

#### ユーザーとグループの割り当て

アプリケーションを Microsoft Entra ID に発行した後、アプリをユーザーとグループに割り当てて、マイ アプリ ポータルに表示されるようにすることができます。 この割り当ては、アプリケーションを作成したときに生成されたサービス プリンシパル オブジェクトに対するものです。 「[マイ アプリ ポータルの概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview)」を参照してください。

アプリケーションが関連付けられている可能性がある `AppRole` インスタンスを取得します。 通常、SaaS アプリケーションにはさまざまな `AppRole` インスタンスが関連付けられています。 通常、カスタム アプリケーションには既定の `AppRole` インスタンスが 1 つあります。 割り当てる `AppRole` インスタンス ID を取得します。

```https
Authorization: Required with a valid Bearer token
Method:GET

https://graph.microsoft.com/v1.0/servicePrincipals/aaaaaaaa-bbbb-cccc-1111-222222222222
```

Microsoft Entra ID から、アプリケーションに割り当てるユーザーまたはグループのオブジェクト ID を取得します。 前述の API 呼び出しからアプリ ロール ID を取得し、サービス プリンシパルに対する PATCH 本文と共に送信します。

```https
Authorization: Required with a valid Bearer token
Method: PATCH
Content-type: servicePrincipal/json

https://graph.microsoft.com/v1.0/servicePrincipals/aaaaaaaa-bbbb-cccc-1111-222222222222
{
    "principalId":"{Principal Object ID of User -or- Group}",
    "resourceId":"{Service Principal Object ID}",
    "appRoleId":"{App Role ID}"
}
```

### パートナーシップ

ネットワークおよびデリバリー コントローラーを使用しながらレガシ アプリケーションの保護を促進するために、Microsoft は次のアプリケーション デリバリー コントローラー (ADC) プロバイダーとパートナーシップを結んでいます。

- **Akamai エンタープライズアプリケーションへのアクセス**
    - [チュートリアル: Microsoft Entra SSO と Akamai の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/akamai-tutorial)
- **Citrix ADCの**
    - [チュートリアル: Microsoft Entra SSO と Citrix ADC SAML Connector for Microsoft Entra ID の統合 (Kerberos ベースの認証)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrix-netscaler-tutorial)
- **F5 BIG-IP アクセス ポリシー マネージャー**
    - [チュートリアル: Microsoft Entra SSO と Citrix ADC SAML Connector for Microsoft Entra ID の統合 (Kerberos ベースの認証)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- **ケンプロードマスター**
    - [チュートリアル: Microsoft Entra SSO と Kemp LoadMaster Microsoft Entra の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kemp-tutorial)
- **パルスセキュア仮想トラフィックマネージャー**
    - [チュートリアル: Microsoft Entra SSO と Pulse Secure Virtual Traffic Manager の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pulse-secure-virtual-traffic-manager-tutorial)

次の VPN ソリューション プロバイダーは Microsoft Entra ID と接続して、SSO や多要素認証 (MFA) などの最新の認証および認可方法を利用できるようにします。

- **Cisco セキュア ファイアウォール - セキュア クライアント**
    - [チュートリアル: Microsoft Entra SSO と Cisco Secure Firewall - Secure Client の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-secure-firewall-secure-client)
- **フォーティネット FortiGate**
    - [チュートリアル: Microsoft Entra SSO と FortiGate SSL VPN の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortigate-ssl-vpn-tutorial)
- **F5 BIG-IP アクセス ポリシー マネージャー**
    - [チュートリアル: Microsoft Entra SSO 用に F5 BIG-IP SSL-VPN を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-passwordless-vpn)
- **パロアルトネットワークス GlobalProtect**
    - [チュートリアル: Microsoft Entra SSO と Palo Alto Networks - Admin UI の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/paloaltoadmin-tutorial)
- **パルスコネクトセキュア**
    - [チュートリアル: Microsoft Entra SSO と Pulse Secure PCS の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/pulse-secure-pcs-tutorial)

次の SDP (Software Defined Perimeter) ソリューション プロバイダーは、SSO や MFA などの認証および認可方法を利用できるように Microsoft Entra ID と接続します。

- **Datawiza アクセス ブローカー**
    - [チュートリアル: Microsoft Entra ID と Datawiza を使用してセキュリティ保護されたハイブリッド アクセスを構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-configure-sha)
- **周囲 81**
    - [チュートリアル: Microsoft Entra SSO と Perimeter 81 の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/perimeter-81-tutorial)
- **Silverfort認証プラットフォーム**
    - [チュートリアル: Microsoft Entra ID および Silverfort で安全なハイブリッド アクセスを構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/silverfort-integration)
- **ストラタ・マベリクス・アイデンティティ・オーケストレーター**
    - [Microsoft Entra SSO と Maverics Identity Orchestrator SAML Connector を統合する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/maverics-identity-orchestrator-saml-connector-tutorial)
- **Zscaler プライベート アクセス**
    - [チュートリアル: Microsoft Entra ID と Zscaler Private Access の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zscalerprivateaccess-tutorial)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/silverfort-integration"} -->
## Microsoft Entra ID と Silverfort による安全なハイブリッド アクセス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/silverfort-integration
- Service: entra-id / enterprise-apps
- Article date: 2024-04-18
- Summary: このチュートリアルでは、Silverfort を Microsoft Entra ID と連携させて、安全にハイブリッド アクセス (SHA) を行う方法を説明します。

[Silverfort](https://www.silverfort.com/) では、エージェントもプロキシも使用しないテクノロジーにより、オンプレミスおよびクラウド上の資産を Microsoft Entra ID に接続します。 組織でこのソリューションを利用すれば、ID の保護、可視性、ユーザー エクスペリエンスを、Microsoft Entra ID 上の環境に適用できます。 これにより、オンプレミス環境とクラウド環境の、認証操作の普遍的なリスクに基づく監視と評価ができるようになり、脅威を防ぐことができます。

このチュートリアルでは、オンプレミス Silverfort 実装を Microsoft Entra ID と統合する方法について説明します。

詳細情報:

- [Microsoft Entra ハイブリッド参加済みデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)
- [Microsoft Entra ID への Silverfort ブリッジ](https://www.silverfort.com/resources/solution-brief/silverfort-bridging-to-entra-id/)

Silverfort は、資産を Microsoft Entra ID と接続します。 [ブリッジした](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)これらの資産は、通常のアプリケーションと同様に Microsoft Entra ID に表示され、条件付きアクセス、シングルサインオン (SSO)、多要素認証、監査などによって保護できます。 Silverfort を使用して、次のような資産を接続します。

- レガシ アプリケーション、自社製アプリケーション
- リモート デスクトップ、Secure Shell (SSH)
- コマンドライン ツールなどによる管理者のアクセス
- ファイル共有、データベース
- インフラストラクチャ、業務用システム

Silverfort は、会社の資産と、Active Directory フェデレーション サービス (AD FS) とリモート認証ダイヤルイン ユーザー サービス (RADIUS) を含むサード パーティの ID およびアクセス管理 (IAM) プラットフォームを Microsoft Entra ID に統合します。 このシナリオには、ハイブリッド環境とマルチクラウド環境が含まれます。

このチュートリアルを使用して、Microsoft Entra テナントで Silverfort Microsoft Entra ID ブリッジを構成してテストし、Silverfort 実装と通信します。 構成を終えたら、ID から Microsoft Entra ID に SSO の認証要求をブリッジする Silverfort 認証ポリシーを作成できます。 ブリッジした後のアプリケーションは Microsoft Entra ID で管理できます。

### Silverfort と Microsoft Entra 認証アーキテクチャ

次の図は、ハイブリッド環境で、Silverfort によって調整された認証アーキテクチャを示しています。

[Image: 図 アーキテクチャ図]

#### ユーザー フロー

1. ユーザーは、Kerberos、SAML、NTLM、OIDC、LDAP(s) などのプロトコルを使用して本来の ID プロバイダー (IdP) に認証要求を送信します。
2. 応答は、認証状態が有効であるかどうかを確認するため、そのまま Silverfort にルーティングされます。
3. Silverfort によって、Microsoft Entra ID に対する可視化、検出、ブリッジを行う
4. アプリケーションをブリッジした場合、認証の判断は Microsoft Entra ID に渡されます。 Microsoft Entra ID で条件付きアクセスのポリシーを確認して認証を承認します。
5. 認証状態の応答がそのまま Silverfort から IdP に送信される
6. IdP でリソースへのアクセスを許可または拒否する
7. ユーザーには、アクセス要求の許可または拒否が通知されます。

### 前提条件

このチュートリアルを実行するには、テナントまたはインフラストラクチャに Silverfort がデプロイされている必要があります。 テナントまたはインフラストラクチャに Silverfort をデプロイするには、silverfort.com [Silverfort](https://www.silverfort.com/) に移動して、ワークステーションに Silverfort デスクトップ アプリをインストールします。

Microsoft Entra テナントで Silverfort Microsoft Entra アダプターを設定します。

- アクティブなサブスクリプションが含まれる Azure アカウント
    - [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成できます。
- Azure アカウントの次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者
    - サービス プリンシパル所有者
- Microsoft Entra アプリケーション ギャラリーの Silverfort Microsoft Entra アダプター アプリケーションは、SSO をサポートするように事前構成されています。 ギャラリーから、Silverfort Microsoft Entra アダプターをエンタープライズ アプリケーションとしてテナントに追加します。

### Silverfort を設定してポリシーを作成する

1. ブラウザーで Silverfort 管理者コンソールにサインインします。
2. メイン メニューで **[設定]** に移動し、[一般] セクションで **[Microsoft Entra ID Bridge コネクタ]** までスクロールします。
3. テナント ID を確認し、 **[Authorize](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/認可)** をクリックします。
4. **[変更の保存]** を選択します。
5. **[要求されているアクセス許可]** ダイアログで、**[承諾]** を選択します。
6. 新しいタブで [Registration Completed](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/登録が完了しました)というメッセージが表示されます。このタブを閉じます。

    [Image: 登録完了画面の画像]
7. **[設定]** ページで、**[変更の保存]** をクリックします
8. Microsoft Entra アカウントにサインインします。 左側のウィンドウで、**[エンタープライズ アプリケーション]** を選択します。 **Silverfort Microsoft Entra アダプター** アプリケーションが登録済みとして表示されます。
9. Silvervort 管理者コンソールで **[Policies](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/ポリシー)** ページに移動し、**[Create Policy](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/ポリシーの作成)** をクリックします。 **[新しいポリシー]** ダイアログ ボックスが表示されます。
10. **[Policy Name](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/ポリシー名)** を入力します。Azure に作成するアプリケーションの名前のポリシー名を付けることができます。 たとえば、複数のサーバーまたはアプリケーションをこのポリシーに加える場合は、ポリシーの対象となるリソースを反映した名前を付けます。 例では、SL-APP1 サーバーのためのポリシーを作成します。

[Image: ポリシーを定義する画面の画像]

1. **[Auth Type](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/認証の型)** と **[Protocol](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/プロトコル)** を選択します。
2. **[ユーザーとグループ]** フィールドの**[編集]** アイコンをクリックし、ポリシーの対象となるユーザーを設定します。 これらのユーザーの認証は Microsoft Entra ID にブリッジされます。

[Image: ユーザーとグループの画面の画像]

1. ユーザー、グループ、または組織単位 (OU) を検索して選択します。

[Image: ユーザーを検索する画面の画像]

1. 選択したユーザーは **[SELECTED](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/選択)** ボックスに表示されます。

[Image: 選択したユーザーの画面の画像]

1. ポリシーを適用する **[ソース]** を選択します。 この例では、**[All Devices](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/すべてのデバイス)** を選択しています。

    [Image: ソースの画面の画像]
2. **[Destination](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/宛先)** を SL-App1 に設定します。 必要であれば、**編集**ボタンをクリックして、リソースおよびリソースのグループの変更、追加ができます。

    [Image: 宛先の画面の画像]
3. [アクション] で、**[Entra ID BRIDGE]** を選択します。
4. **[保存]** を選択します。 ポリシーを有効にするように求められます。
5. [Entra ID Bridge] セクションで、ポリシーが [ポリシー] ページに表示されます。
6. Microsoft Entra アカウントに戻り、**[Enterprise applications](エンタープライズ アプリケーション)** に移動します。 新しい Silverfort アプリケーションが表示されます。 このアプリケーションは、条件付きアクセス ポリシーに含めることができます。

詳細情報: [チュートリアル: Microsoft Entra MFA を使用してユーザー サインイン イベントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa#create-a-conditional-access-policy)。
<!-- /MSL-PAGE -->
