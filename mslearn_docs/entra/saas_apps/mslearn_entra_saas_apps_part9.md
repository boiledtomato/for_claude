# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 9)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 73

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/f5-big-ip-oracle-enterprise-business-suite-easy-button"} -->
## Oracle Enterprise Business Suite への SSO 用に F5 の BIG-IP Easy Button を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/f5-big-ip-oracle-enterprise-business-suite-easy-button
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: F5 の BIG-IP Easy Button ガイド付き構成を使用して、Oracle Enterprise Business Suite へのヘッダーベースの SSO による SHA を実装する方法について説明します

この記事では、F5 の BIG-IP Easy Button のガイド付き構成を通じて、Microsoft Entra ID を使用して Oracle Enterprise Business Suite (EBS) をセキュリティで保護する方法について説明します。

BIG-IP と Microsoft Entra ID の統合には、以下のように数多くの利点があります。

- Microsoft Entra の事前認証および[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)による[ゼロ トラスト ガバナンスの強化](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)
- Microsoft Entra ID と BIG-IP 公開サービスとの間の完全な SSO
- 1 つのコントロール プレーンである [Azure portal](https://portal.azure.com/) からの ID とアクセスの管理

すべての利点については、[F5 BIG-IP と Microsoft Entra の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)に関する記事と [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオン](https://learn.microsoft.com/ja-jp/azure/active-directory/active-directory-appssoaccess-whatis)に関する記事を参照してください。

### シナリオの説明

このシナリオでは、**HTTP 認証ヘッダー**を使用して保護されたコンテンツへのアクセスを管理する従来の **Oracle EBS アプリケーション**を説明します。

従来のアプリケーションには、Microsoft Entra ID との直接的な統合をサポートする最新のプロトコルがありません。 アプリケーションは最新化できますが、コストがかかり、慎重な計画が必要であり、潜在的なダウンタイムのリスクが生じます。 代わりに、プロトコル遷移によって従来のアプリケーションと最新の ID コントロール プレーンとの間のギャップを橋渡しするために、F5 BIG IP Application Delivery Controller (ADC) を使用します。

アプリの前に BIG-IP があると、Microsoft Entra の事前認証とヘッダー ベースの SSO によってサービスをオーバーレイできるようになるため、アプリケーションの全体的なセキュリティ体制が大幅に強化されます。

### シナリオのアーキテクチャ

このシナリオの安全なハイブリッド アクセス ソリューションは、多階層の Oracle アーキテクチャを含むいくつかのコンポーネントで構成されています。

**Oracle EBS アプリケーション:** Microsoft Entra SHA によって保護される BIG-IP の公開済みサービス。

**Microsoft Entra ID:** Security Assertion Markup Language (SAML) ID プロバイダー (IdP)。ユーザーの資格情報の検証、条件付きアクセス (CA)、および BIG-IP に対する SAML ベースの SSO に責任があります。 SSO を介して、Microsoft Entra ID により、必要なセッション属性が BIG-IP に提供されます。

**Oracle Internet Directory (OID):** ユーザー データベースをホストします。 BIG-IP では、LDAP 経由で認可属性を確認します。

**Oracle AccessGate:** EBS アクセス cookie を発行する前に、OID サービスを使用してバック チャネルを介して認可属性を検証します

**BIG-IP:** アプリケーションに対するリバース プロキシおよび SAML サービス プロバイダー (SP)。Oracle アプリケーションへのヘッダーベースの SSO を実行する前に認証を SAML IdP に委任します。

このシナリオの SHA では、SP と IdP によって開始されたフローの両方がサポートされます。 次の図は、SP Initiated フローを示しています。

[Image: セキュア ハイブリッド アクセス - SP Initiated フロー]

| 手順 | 説明 |
| --- | --- |
| 1 | ユーザーがアプリケーション エンドポイント (BIG-IP) に接続する |
| 2 | BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトする |
| 3 | Microsoft Entra ID によって、ユーザーの事前認証と、条件付きアクセス ポリシーの適用が行われる |
| 4 | ユーザーがリダイレクトされて BIG-IP (SAML SP) に戻され、発行された SAML トークンを使用して SSO が実行される |
| 5 | BIG IP によって、ユーザーの一意の ID (UID) 属性に対して LDAP クエリが実行される |
| 6 | BIG IP によって、返された UID 属性が、EBS セッション Cookie 要求の user\_orclguid ヘッダーとして Oracle AccessGate に挿入される |
| 7 | Oracle AccessGate によって、Oracle Internet Directory (OID) サービスに対して UID が検証され、EBS アクセス Cookie が発行される |
| 8 | EBS ユーザー ヘッダーと Cookie がアプリケーションに送信され、ユーザーにペイロードが返される |

### 前提条件

以前の BIG-IP エクスペリエンスは必要ありませんが、以下が必要です。

- Microsoft Entra ID 無料サブスクリプション (またはそれ以上)
- 既存の BIG-IP。または、Azure に BIG-IP Virtual Edition (VE) をデプロイします。
- 次のいずれかの F5 BIG-IP ライセンス SKU

    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス
    - 既存の BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - 90 日間の BIG-IP 全機能[試用版ライセンス](https://www.f5.com/trial/big-ip-trial.php)。
- ユーザー ID (オンプレミス ディレクトリから Microsoft Entra ID に[同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)、または Microsoft Entra ID 内で直接作成してオンプレミス ディレクトリに返したもの)
- Microsoft Entra アプリケーション管理者の[アクセス許可](https://learn.microsoft.com/ja-jp/azure/active-directory/users-groups-roles/directory-assign-admin-roles#application-administrator)を持つアカウント
- HTTPS でサービスを公開するための [SSL Web 証明書](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide#ssl-profile) (または、テスト中に使用する既定の BIG-IP 証明書)
- Oracle AccessGate と LDAP が有効な OID (Oracle Internet Database) を含む既存の Oracle EBS スイート

### BIG-IP の構成方法

このシナリオで使用する BIG-IP は、テンプレートを使用した 2 つの方法や高度な構成を含め、さまざまな方法で構成できます。 この記事では、Easy ボタン テンプレートを提供する最新のガイド付き構成 16.1 について説明します。 Easy Button を使用すると、管理者は、Microsoft Entra ID と BIG-IP の間を行き来して SHA のためにサービスを有効にする必要がなくなります。 デプロイとポリシー管理は、APM のガイド付き構成ウィザードと Microsoft Graph との間で直接処理されます。 この充実した BIG-IP APM と Microsoft Entra ID の統合により、アプリケーションでは確実に ID フェデレーション、SSO、Microsoft Entra 条件付きアクセスを迅速かつ容易にサポートできるため、管理オーバーヘッドが軽減されます。

注

このガイド全体で参照されている文字列または値の例はすべて、実際の環境に合わせて置き換える必要があります。

### Easy Button を登録する

クライアントまたはサービスから Microsoft Graph にアクセスするには、[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)によって信頼されている必要があります。

この最初の手順では、Graph への **Easy Button** アクセスを承認するために使用されるテナント アプリの登録を作成します。 これらのアクセス許可により、BIG-IP は、発行されたアプリケーションの SAML SP インスタンスと SAML IdP としての Microsoft Entra ID との間に信頼を確立するために必要な構成をプッシュできます。

1. アプリケーションの管理者権限を持つアカウントを使用して、[Azure portal](https://portal.azure.com/) にサインインします。
2. 左側のナビゲーション ウィンドウから、**Microsoft Entra ID** サービスを選択します。
3. [管理] で、**[アプリの登録]**&gt;**[新規登録]** の順に選択します。
4. `F5 BIG-IP Easy Button` など、アプリケーションの表示名を入力します。
5. アプリケーションを使用できるユーザー &gt;**[Accounts in this organizational directory only] (この組織ディレクトリのアカウントのみ)** を指定します。
6. **[登録]** を選択して、初期のアプリ登録を完了します。
7. **[API\* のアクセス許可\*]** に移動し、次の Microsoft Graph\* の**アプリケーション\*のアクセス許可\***を承認します：

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
8. 組織に管理者の同意を付与します
9. **[Certificates & Secrets](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書とシークレット)** に移動し、新しい**クライアント シークレット**を生成して、それをメモします
10. **[Overview](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/概要)** に移動し、**クライアント ID** と **テナント ID** をメモします

### Easy Button を構成する

APM\* の **[ガイド\*付き構成\*]** を開始して、**Easy Button** テンプレート\*を起動します。

1. **[アクセス] &gt; [ガイド付き構成] &gt; [Microsoft 統合]** に移動し、**[Microsoft Entra アプリケーション]** を選択します。
2. **[次の手順に従ってソリューションを構成すると、必要なオブジェクトが作成されます]** で、構成手順の一覧を確認し、**[次へ]** を選択します。
3. **[ガイド付き構成]** で、アプリケーションの公開に必要な一連の手順に従います。

#### Configuration Properties

**[構成のプロパティ]** タブでは、BIG-IP アプリケーション構成と SSO オブジェクトが作成されます。 **[Azure サービス アカウントの詳細]** セクションは、アプリケーションとして、以前に Microsoft Entra テナントに登録したクライアントを表すものとします。 これらの設定\*により、BIG-IP\* の OAuth クライアント\*では、通常は手動で構成する\* SSO\* プロパティと共に、SAML SP をテナント\*に直接個別に登録できるようになります。 Easy Button により、公開\*されて SHA が有効になっているすべての BIG-IP\* サービス\*に対してこの操作が行われます。

これらの一部はグローバル設定であるため、より多くのアプリケーションを公開するために再利用でき、デプロイの時間と労力をさらに削減するのに役立ちます。

1. 管理者が Easy Button 構成を容易に区別できる一意の**構成名**を指定します
2. **[Single Sign-On (SSO) & HTTP Headers](シングル サインオン (SSO) と HTTP ヘッダー)** を有効にします
3. テナントに Easy Button クライアントを登録するときに記録した**テナント ID、クライアント ID**、および**クライアント シークレット**を入力します。
4. **[Next] (次へ)** を選択する前に、BIG-IP がテナントに正常に接続できることを確認してください。

    [Image: 構成の一般プロパティとサービス アカウントのプロパティのスクリーンショット]

#### サービス プロバイダー

サービス\* プロバイダー\*設定\*では、SHA によって保護されるアプリケーション\*の SAML SP インスタンス\*のプロパティを定義します。

1. **ホスト**を入力します。 これは、セキュリティで保護されるアプリケーションのパブリック FQDN です
2. **エンティティ ID** を入力します。 これは、トークンを要求する SAML SP を識別するために Microsoft Entra ID によって使用される識別子です

    [Image: [Service Provider](サービス プロバイダー) 設定のスクリーンショット]

    次に、オプションの **[セキュリティ設定]** で、発行された SAML アサーションを Microsoft Entra ID で暗号化する必要があるかどうかを指定します。 Microsoft Entra ID と BIG-IP APM の間でアサーションを暗号化すると、コンテンツ トークンが傍受されないこと、および個人や会社のデータが侵害されないことが保証されます。
3. **[アサーション解読秘密キー]** の一覧から、**[新規作成]** を選択します

    [Image: Easy Button の構成のスクリーンショット - 新しいインポートの作成]
4. **OK** を選択します。 新しいタブで **[Import SSL Certificate and Keys](SSL 証明書とキーのインポート)** ダイアログが開きます
5. **PKCS 12 (IIS)** を選択して、証明書と秘密キーをインポートします。 プロビジョニングが完了したら、ブラウザー タブを閉じて、メイン タブに戻ります。

    [Image: Easy Button の構成のスクリーンショット - 新しい証明書をインポートする]
6. **[Enable Encrypted Assertion](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/暗号化アサーションを有効にする)** をオンにします
7. 暗号化を有効にした場合は、**[Assertion Decryption Private Key](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アサーション解読秘密キー)** の一覧から証明書を選択します。 これは、Microsoft Entra アサーションを解読するために BIG-IP APM で使用される証明書の秘密キーです。
8. 暗号化を有効にした場合は、**[Assertion Decryption Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アサーション解読証明書)** の一覧から証明書を選択します。 これは、発行された SAML アサーションを暗号化するために BIG IP が Microsoft Entra ID にアップロードする証明書です。

    [Image: サービス プロバイダーの [Security Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ設定) のスクリーンショット]

#### マイクロソフト エントラ ID

このセクションでは、Microsoft Entra テナント内で新しい BIG-IP SAML アプリケーションを手動で構成するために通常使用するすべてのプロパティを定義します。 Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP、その他のアプリ用の汎用 SHA テンプレート用の定義済みアプリケーション テンプレートのセットが用意されています。

このシナリオでは、**[Azure の構成]** ページで、**[Oracle E-Business Suite]**&gt;**[追加]** を選択します。

##### Azure の構成

**[Azure の構成]** ページで、次の手順を実行します。

1. **[構成プロパティ]** で、BIG-IP が Microsoft Entra テナントに作成するアプリの**表示名**を入力し、[MyApps ポータル](https://myapplications.microsoft.com/)に表示するアイコンを指定します。
2. **[Sign On URL](サインオン URL)(オプション)** で、セキュリティで保護されている EBS アプリケーションのパブリック FQDN と、Oracle EBS ホームページの既定のパスを入力します

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - 表示情報を追加する]
3. **[署名キー]** と **[署名証明書]** の横にある更新アイコンを選択して、先ほどインポートした証明書を見つけます
4. **[署名キーのパスフレーズ]** に証明書のパスワードを入力します。
5. **[署名オプション]** (省略可能) を有効にします。 これにより、BIG-IP は、Microsoft Entra ID によって署名されたトークンと要求のみを受け入れるようになります

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - 署名証明書情報を追加する]
6. **ユーザーとユーザー グループ**は、Microsoft Entra テナントから動的に照会され、アプリケーションへのアクセスを承認するために使用されます。 後でテストに使用できるユーザーまたはグループを追加します。それ以外の場合は、すべてのアクセスが拒否されます

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - ユーザーとグループを追加する]

##### ユーザー属性と要求

ユーザーが正常に認証されると、Microsoft Entra ID は、ユーザーを一意に識別する要求と属性の既定のセットを使用して SAML トークンを発行します。 **[ユーザー属性と要求] タブ**には、新しいアプリケーションに対して発行する既定の要求が表示されます。 また、さらに多くの要求を構成することもできます。

[Image: [ユーザー属性と要求] のスクリーンショット]

必要に応じて、追加のMicrosoft Entra 属性を含めることができますが、Oracle EBS シナリオでは既定の属性のみが必要です。

##### 追加のユーザー属性

**[追加のユーザー属性]** タブでは、セッション拡張のために、他のディレクトリに格納されている属性を必要とする、さまざまな分散システムをサポートできます。 次に、LDAP ソースからフェッチされた属性を追加の SSO ヘッダーとして挿入して、ロール、パートナー ID などに基づいてアクセスをさらに制御できます。

1. **[Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定)** オプションを有効にします
2. **[LDAP Attributes](LDAP 属性)** チェック ボックスをオンにします
3. **[Choose Authentication Server](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証サーバーの選択)** で **[Create New](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新規作成)** を選択します
4. 実際の設定に応じて、**[Use pool](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プールを使用)** または **[Direct](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/直接)** サーバー接続モードを選択します。 これにより、ターゲット LDAP サービスの **[Server Address](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サーバーアドレス)** が指定されます。 単一の LDAP サーバーを使用する場合は、**[Direct](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/直接)** を選択します。
5. **[Service Port](サービス ポート)** に、3060 (既定値)、3161 (セキュア)、または Oracle LDAP サービスが動作するその他のポートを入力します
6. 検索に使用する**ベース検索 DN** (識別名) を入力します。 この検索 DN は、ディレクトリ全体でグループを検索するために使用されます。
7. **[Admin DN](管理者の DN)**に、APM で LDAP クエリの認証に使用されるアカウントの正確な識別名を、パスワードと共に設定します

    [Image: [Additional User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加のユーザー属性) のスクリーンショット]
8. 既定のすべての **[LDAP Schema Attributes](LDAP スキーマ属性)** をそのまま使用します

    [Image: LDAP スキーマ属性のスクリーンショット]
9. **[LDAP Query Properties](LDAP クエリのプロパティ)** で、 **[Search Dn](検索 Dn)** にユーザー オブジェクトを検索する LDAP サーバーのベース ノードを設定します
10. LDAP ディレクトリから返される必要があるユーザー オブジェクト属性の名前を追加します。 EBS の場合、既定値は **orclguid** です

    [Image: LDAP クエリ properties.png のスクリーンショット]

##### 条件付きアクセス ポリシー

条件付きアクセス ポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御するために、Microsoft Entra の事前認証後に適用されます。

既定では、[ **使用可能なポリシー]** ビューには、ユーザーベースのアクションを含まないすべての条件付きアクセス ポリシーが一覧表示されます。

**[選択されたポリシー]** ビューには、既定で、すべてのリソースをターゲットとするすべてのポリシーが表示されます。 これらのポリシーは、テナント レベルで適用されるため、選択を解除したり、[使用可能なポリシー] リストに移動したりすることはできません。

公開されているアプリケーションに適用するポリシーを選択するには:

1. **[Available Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/使用可能なポリシー)** リストで目的のポリシーを選択します
2. 右矢印を選択して、これを **[Selected Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/選択されたポリシー)** リストに移動します

    選択されたポリシーについては、**[含める]** または **[除外する]** オプションをオンにする必要があります。 両方のオプションをオンにすると、ポリシーは適用されません。

    [Image: 条件付きアクセス ポリシーのスクリーンショット]

注

ポリシーの一覧は、最初にこのタブに切り替えたときに 1 回だけ列挙されます。ウィザードから手動でテナントにクエリを実行するための更新ボタンが用意されていますが、このボタンはアプリケーションがデプロイされている場合にのみ表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは BIG-IP データ プレーン オブジェクトであり、アプリケーションに対するクライアント要求をリッスンする仮想 IP アドレスで表されます。 受信したトラフィックは、ポリシーの結果と設定に従って送信される前に、仮想サーバーに関連付けられている APM プロファイルに対して処理および評価されます。

1. **宛先アドレス**を入力します。 これは、BIG-IP がクライアント トラフィックを受信するために使用できる任意の IPv4 または IPv6 アドレスです。 対応するレコードが DNS にも存在する必要があり、それによってクライアントでは、BIG-IP の公開済みアプリケーションの外部 URL を、アプリケーション自体ではなく、この IP に解決できるようになります。 テストでは、テスト PC の localhost DNS を使用しても問題ありません。
2. **[Service Port](サービス ポート)** で、HTTPS 用に「*443*」と入力します
3. **[Enable Redirect Port](リダイレクト ポートを有効にする)** をオンにし、**リダイレクト ポート**を入力します。 これにより、受信 HTTP クライアント トラフィックが HTTPS にリダイレクトされます
4. クライアント\* SSL\* プロファイル\*を使用すると、HTTPS\* 用の仮想サーバー\*が有効になり、クライアント\*接続が TLS\* で暗号化されるようになります。 前提条件の一部として作成した**クライアント\* SSL\* プロファイル\***を選択するか、テスト\*する場合は既定値\*のままにします

    [Image: 仮想サーバーのスクリーンショット]

#### プールのプロパティ

**[Application Pool](アプリケーション プール) タブ**には、1 つまたは複数のアプリケーション サーバーを含むプールとして表される、BIG-IP の背後にあるサービスの詳細が表示されます。

1. **[Select a Pool](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プールの選択)** から選択します。 新しいプールを作成するか、既存のプールを選択します
2. **[Load Balancing Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/負荷分散方法)** で、[Round Robin](ラウンド ロビン) を選択します
3. **[プール サーバー]** では、既存のノードを選択するか、Oracle EBS アプリケーションをホストするサーバーの IP とポートを指定します。

    [Image: [Application Pool](アプリケーション プール) のスクリーンショット]
4. **[Access Gate Pool](アクセス ゲート プール)** は、Oracle EBS で SSO 認証済みユーザーを Oracle E-Business Suite セッションにマッピングするために使用するサーバーを指定します。 アプリケーションをホストする Oracle アプリケーション サーバーの IP およびポートで **[Pool Servers](プール サーバー)** を更新します

    [Image: AccessGate プールのスクリーンショット]

##### シングル サインオン & HTTP ヘッダー

**Easy Button ウィザード**では、公開されたアプリケーションに対する SSO 用に、Kerberos、OAuth Bearer、HTTP Authorization ヘッダーがサポートされています。 Oracle EBS アプリケーションではヘッダーが要求されるため、 **[HTTP Headers](HTTP ヘッダー)** を有効にし、次のプロパティを入力します。

- **ヘッダー操作:** 置換
- **ヘッダー名:** USER\_NAME
- **ヘッダー値:** %{session.sso.token.last.username}
- **ヘッダー操作:** 置換
- **ヘッダー名:** USER\_ORCLGUID
- **ヘッダー値:** %{session.ldap.last.attr.orclguid}

    [Image: SSO と HTTP ヘッダーのスクリーンショット]

注

中かっこ内で定義されている APM セッション変数は、大文字と小文字が区別されます。 たとえば、Microsoft Entra の属性名が orclguid として定義されている場合に「OrclGUID」と入力すると、属性マッピング エラーが発生します

#### セッションの管理

BIG-IP のセッション管理の設定は、ユーザー セッションが終了されるか続行が許可される条件、ユーザーと IP アドレスの制限、および対応するユーザー情報を定義するために使用されます。 これらの設定の詳細については、[F5 のドキュメント](https://support.f5.com/csp/article/K18390492)を参照してください。

しかし、ユーザーがサインオフするときに IdP、BIG-IP、およびユーザー エージェント間のすべてのセッションが確実に終了されるようにする、シングル ログアウト (SLO) 機能についての説明はここにはありません。 Easy Button によって SAML アプリケーションが Microsoft Entra テナントでインスタンス化されると、ログアウト URL にも、APM の SLO エンドポイントが設定されます。 このように、Microsoft Entra マイ アプリ ポータルからの IdP Initiated サインアウトでは、BIG-IP とクライアント間のセッションも終了します。

これに加え、テナントから公開済みアプリケーションの SAML フェデレーション メタデータもインポートされて、APM に Microsoft Entra ID の SAML ログアウト エンドポイントが提供されます。 これにより、SP Initiated サインアウトでクライアントと Microsoft Entra ID との間のセッションが確実に終了するようになります。 しかし、これを真に効果的に行うには、APM で、ユーザーがいつアプリケーションからサインアウトしたのかを正確に知る必要があります。

BIG-IP Web トップ ポータルを使用して公開済みアプリケーションにアクセスする場合は、そこからのサインアウトが APM によって処理され、Microsoft Entra サインアウト エンドポイントも呼び出されます。 しかし、BIG-IP Web トップ ポータルが使用されていないために、サインアウトするようにユーザーが APM に指示する方法がないシナリオについて考えてみます。ユーザーがアプリケーション自体からサインアウトした場合でも、BIG-IP では技術的にはこれが認識されません。 このため、SP によって開始されるサインアウトでは、セッションが不要になったときに確実に安全に終了されるように、慎重に検討する必要があります。 これを実現する 1 つの方法は、SLO 関数をアプリケーションのサインアウト ボタンに追加して、クライアントを Microsoft Entra SAML または BIG-IP サインアウト エンドポイントにリダイレクトできるようにすることです。 テナントの SAML サインアウト エンドポイントの URL については、**[アプリの登録] &gt; [エンドポイント]** で確認できます。

アプリに変更を加えることができない場合は、BIG-IP でアプリケーションのサインアウト呼び出しをリッスンし、要求を検出したら SLO をトリガーすることを検討してください。 これを実現するための BIG-IP iRules の使用については、[Oracle PeopleSoft SLO ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button#peoplesoft-single-logout)を参照してください。 これを実現するための BIG-IP iRules の使用の詳細については、F5 のナレッジ記事「[URI 参照ファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145)」および「[ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)」を参照してください。

### まとめ

この最後の手順では、構成の内容を示します。 **[デプロイ]** を選択してすべての設定をコミットし、エンタープライズ アプリケーションのテナント リストにアプリケーションが存在することを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/f5-big-ip-oracle-jd-edwards-easy-button"} -->
## Microsoft Entra ID を使用して、Oracle JD Edwards への SSO 向けに F5 BIG-IP Easy Button を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/f5-big-ip-oracle-jd-edwards-easy-button
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: F5 の BIG-IP Easy Button ガイド付き構成を使用して Oracle JD Edwards にヘッダーベースのシングル サインオンを使用して SHA を実装する方法について説明します

この記事では、F5 の BIG-IP Easy Button のガイド付き構成で、Microsoft Entra ID を使用して Oracle JD Edwards (JDE) をセキュリティで保護する方法について説明します。

BIG-IP と Microsoft Entra ID の統合には、以下のように数多くの利点があります。

- Microsoft Entra 事前認証と[条件付きアクセス](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)による[ゼロ トラスト ガバナンスの改善](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- Microsoft Entra ID と BIG-IP 公開サービスとの間の完全な SSO
- 1 つのコントロール プレーンである [Azure portal](https://portal.azure.com/) からの ID とアクセスの管理

すべての利点については、[F5 BIG-IP と Microsoft Entra の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)に関する記事と [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオン](https://learn.microsoft.com/ja-jp/azure/active-directory/active-directory-appssoaccess-whatis)に関する記事を参照してください。

### シナリオの説明

このシナリオでは、**HTTP 認証ヘッダー**を使用して保護されたコンテンツへのアクセスを管理する従来の **Oracle JDE アプリケーション**を説明します。

従来のアプリケーションには、Microsoft Entra ID との直接的な統合をサポートする最新のプロトコルがありません。 アプリケーションは最新化できますが、コストがかかり、慎重な計画が必要であり、潜在的なダウンタイムのリスクが生じます。 代わりに、プロトコル遷移によって従来のアプリケーションと最新の ID コントロール プレーンとの間のギャップを橋渡しするために、F5 BIG IP Application Delivery Controller (ADC) を使用します。

アプリの前に BIG-IP することで、Microsoft Entra 事前認証とヘッダーベースの SSO でサービスをオーバーレイできるため、アプリケーションの全体的なセキュリティ体制が大幅に向上します。

### シナリオのアーキテクチャ

このシナリオのセキュリティで保護されたハイブリッド アクセス (SHA) ソリューションは、次のいくつかのコンポーネントで構成されています。

**Oracle JDE アプリケーション:** Microsoft Entra SHA によって保護される BIG-IP の公開済みサービス。

**Microsoft Entra ID:** Security Assertion Markup Language (SAML) ID プロバイダー (IdP)。ユーザーの資格情報の検証、条件付きアクセス (CA)、および BIG-IP に対する SAML ベースの SSO に責任があります。 SSO を介して、Microsoft Entra ID により、必要なセッション属性が BIG-IP に提供されます。

**BIG-IP:** アプリケーションに対するリバース プロキシおよび SAML サービス プロバイダー (SP)。Oracle サービスへのヘッダーベースの SSO を実行する前に認証を SAML IdP に委任します。

このシナリオの SHA では、SP と IdP によって開始されたフローの両方がサポートされます。 次の図は、SP Initiated フローを示しています。

[Image: セキュア ハイブリッド アクセス - SP Initiated フロー]

| 手順 | 説明 |
| --- | --- |
| 1 | ユーザーがアプリケーション エンドポイント (BIG-IP) に接続する |
| 2 | BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトする |
| 3 | Microsoft Entra ID によって、ユーザーの事前認証と、条件付きアクセス ポリシーの適用が行われる |
| 4 | ユーザーがリダイレクトされて BIG-IP (SAML SP) に戻され、発行された SAML トークンを使用して SSO が実行される |
| 5 | BIG-IP によって、Microsoft Entra 属性がアプリケーションへの要求のヘッダーとして挿入される |
| 6 | アプリケーションが要求を承認し、ペイロードを返す |

### 前提条件

以前の BIG-IP エクスペリエンスは必要ありませんが、以下が必要です。

- Microsoft Entra ID 無料サブスクリプション (またはそれ以上)
- Azure で既存の BIG-IP または [BIG-IP Virtual Edition (VE) をデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)
- 次のいずれかの F5 BIG-IP ライセンス SKU

    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス
    - 既存の BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - 90 日間の BIG-IP 全機能[試用版ライセンス](https://www.f5.com/trial/big-ip-trial.php)。
- ユーザー ID (オンプレミス ディレクトリから Microsoft Entra ID に[同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)、または Microsoft Entra ID 内で直接作成してオンプレミス ディレクトリに返したもの)
- Microsoft Entra アプリケーション管理者の[アクセス許可](https://learn.microsoft.com/ja-jp/azure/active-directory/users-groups-roles/directory-assign-admin-roles#application-administrator)を持つアカウント
- HTTPS でサービスを公開するための [SSL Web 証明書](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide#ssl-profile) (または、テスト中に使用する既定の BIG-IP 証明書)
- 既存の Oracle JDE 環境

### BIG-IP の構成方法

このシナリオで使用する BIG-IP は、テンプレートを使用した 2 つの方法や高度な構成を含め、さまざまな方法で構成できます。 この記事では、Easy ボタン テンプレートを提供する最新のガイド付き構成 16.1 について説明します。 Easy Button を使用すると、管理者は、Microsoft Entra ID と BIG-IP の間を行き来して SHA のためにサービスを有効にする必要がなくなります。 デプロイとポリシー管理は、APM のガイド付き構成ウィザードと Microsoft Graph との間で直接処理されます。 この充実した BIG-IP APM と Microsoft Entra ID の統合により、アプリケーションでは確実に ID フェデレーション、SSO、Microsoft Entra 条件付きアクセスを迅速かつ容易にサポートできるため、管理オーバーヘッドが軽減されます。

注

このガイド全体で参照されている文字列または値の例はすべて、実際の環境に合わせて置き換える必要があります。

### Microsoft Entra IDで F5 BIG-IP Easy ボタンを登録する

クライアントまたはサービスから Microsoft Graph にアクセスするには、[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)によって信頼されている必要があります。

この最初の手順では、Graph への **Easy Button** アクセスを承認するために使用されるテナント アプリの登録を作成します。 これらのアクセス許可により、BIG-IP は、発行されたアプリケーションの SAML SP インスタンスと SAML IdP としての Microsoft Entra ID との間に信頼を確立するために必要な構成をプッシュできます。

1. アプリケーションの管理者権限を持つアカウントを使用して、[Azure portal](https://portal.azure.com/) にサインインします。
2. 左側のナビゲーション ウィンドウから、**Microsoft Entra ID** サービスを選択します。
3. [管理] で、**[アプリの登録]**&gt;**[新規登録]** の順に選択します。
4. `F5 BIG-IP Easy Button` など、アプリケーションの表示名を入力します。
5. アプリケーションを使用できるユーザー &gt;**[Accounts in this organizational directory only] (この組織ディレクトリのアカウントのみ)** を指定します。
6. **[登録]** を選択して、初期のアプリ登録を完了します。
7. **[API\* のアクセス許可\*]** に移動し、次の Microsoft Graph\* の**アプリケーション\*のアクセス許可\***を承認します：

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
8. 組織に管理者の同意を付与します
9. **[Certificates & Secrets](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書とシークレット)** に移動し、新しい**クライアント シークレット**を生成して、それをメモします
10. **[Overview](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/概要)** に移動し、**クライアント ID** と **テナント ID** をメモします

### F5 BIG-IP Easy Button の設定を構成する

APM\* の **[ガイド\*付き構成\*]** を開始して、**Easy Button** テンプレート\*を起動します。

1. **[アクセス] &gt; [ガイド付き構成] &gt; [Microsoft 統合]** に移動し、**[Microsoft Entra アプリケーション]** を選択します。
2. **[次の手順に従ってソリューションを構成すると、必要なオブジェクトが作成されます]** で、構成手順の一覧を確認し、**[次へ]** を選択します。
3. **[ガイド付き構成]** で、アプリケーションの公開に必要な一連の手順に従います。

#### 簡易ボタンの構成プロパティを構成する

**[構成のプロパティ]** タブでは、BIG-IP アプリケーション構成と SSO オブジェクトが作成されます。 **[Azure サービス アカウントの詳細]** セクションは、アプリケーションとして、以前に Microsoft Entra テナントに登録したクライアントを表すものとします。 これらの設定\*により、BIG-IP\* の OAuth クライアント\*では、通常は手動で構成する\* SSO\* プロパティと共に、SAML SP をテナント\*に直接個別に登録できるようになります。 Easy Button により、公開\*されて SHA が有効になっているすべての BIG-IP\* サービス\*に対してこの操作が行われます。

これらの一部はグローバル設定であり、より多くのアプリケーションを発行するために再利用でき、デプロイの時間と労力がさらに短縮されます。

1. 管理者が Easy Button 構成を容易に区別できる一意の**構成名**を指定します
2. **[Single Sign-On (SSO) & HTTP Headers](シングル サインオン (SSO) と HTTP ヘッダー)** を有効にします
3. メモした、登録したアプリケーションの**テナント ID、クライアント ID**、**クライアント シークレット**を入力します
4. **[Next] (次へ)** を選択する前に、BIG-IP がテナントに正常に接続できることを確認してください。

    [Image: 構成の一般プロパティとサービス アカウントのプロパティのスクリーンショット]

#### サービス プロバイダーの設定を構成する

サービス\* プロバイダー\*設定\*では、SHA によって保護されるアプリケーション\*の SAML SP インスタンス\*のプロパティを定義します。

1. **ホスト**を入力します。 これは、セキュリティで保護されるアプリケーションのパブリック FQDN です
2. **エンティティ ID** を入力します。 これは、トークンを要求する SAML SP を識別するために Microsoft Entra ID によって使用される識別子です

    [Image: [Service Provider](サービス プロバイダー) 設定のスクリーンショット]

    次に、オプションの **[セキュリティ設定]** で、発行された SAML アサーションを Microsoft Entra ID で暗号化する必要があるかどうかを指定します。 Microsoft Entra ID と BIG-IP APM の間でアサーションを暗号化すると、コンテンツ トークンが傍受されないこと、および個人や会社のデータが侵害されないことが保証されます。
3. **[アサーション解読秘密キー]** の一覧から、**[新規作成]** を選択します

    [Image: Easy Button の構成のスクリーンショット - 新しいインポートの作成]
4. **OK** を選択します。 新しいタブで **[Import SSL Certificate and Keys](SSL 証明書とキーのインポート)** ダイアログが開きます
5. **PKCS 12 (IIS)** を選択して、証明書と秘密キーをインポートします。 プロビジョニングが完了したら、ブラウザー タブを閉じて、メイン タブに戻ります。

    [Image: Easy Button の構成のスクリーンショット - 新しい証明書をインポートする]
6. **[Enable Encrypted Assertion](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/暗号化アサーションを有効にする)** をオンにします
7. 暗号化を有効にした場合は、**[Assertion Decryption Private Key](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アサーション解読秘密キー)** の一覧から証明書を選択します。 これは、Microsoft Entra アサーションを解読するために BIG-IP APM で使用される証明書の秘密キーです。
8. 暗号化を有効にした場合は、**[Assertion Decryption Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アサーション解読証明書)** の一覧から証明書を選択します。 これは、発行された SAML アサーションを暗号化するために BIG IP が Microsoft Entra ID にアップロードする証明書です。

    [Image: サービス プロバイダーの [Security Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ設定) のスクリーンショット]

#### Microsoft Entra ID設定を構成する

Microsoft Entra ID構成では、Microsoft Entra テナント内で新しい BIG-IP SAML アプリケーションを手動で構成するために通常使用するすべてのプロパティが定義されます。 Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP、その他のアプリ用の汎用 SHA テンプレート用の定義済みアプリケーション テンプレートのセットが用意されています。

このシナリオでは、**[Azure の構成]** ページで、**[JD Edwards Protected by F5 BIG-IP]**&gt;**[追加]** を選択します。

##### Azure アプリケーション設定を構成する

1. BIG-IP が Microsoft Entra テナントに作成するアプリの **[表示名]** と、MyApps ポータルでユーザーに表示されるアイコンを入力します。
2. **[サインオン URL (オプション)]** に、セキュリティで保護される JDE アプリケーションのパブリック FQDN を入力します。

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - 表示情報を追加する]
3. **[署名キー]** と **[署名証明書]** の横にある更新アイコンを選択して、先ほどインポートした証明書を見つけます
4. **[Signing Key Passphrase]\(署名キーのパスフレーズ)** で証明書のパスワードを入力します
5. **[署名オプション]** (省略可能) を有効にします。 これにより、BIG-IP は、Microsoft Entra ID によって署名されたトークンと要求のみを受け入れるようになります

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - 署名証明書情報を追加する]
6. **ユーザーとユーザー グループ**は、Microsoft Entra テナントから動的に照会され、アプリケーションへのアクセスを承認するために使用されます。 後でテストに使用できるユーザーまたはグループを追加します。それ以外の場合は、すべてのアクセスが拒否されます

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - ユーザーとグループを追加する]

##### ユーザー属性と要求を構成する

ユーザーが正常に認証されると、Microsoft Entra ID は、ユーザーを一意に識別する要求と属性の既定のセットを使用して SAML トークンを発行します。 **[ユーザー属性と要求]** タブには、新しいアプリケーションに対して発行する既定の要求が表示されます。 また、さらに多くの要求を構成することもできます。

[Image: [ユーザー属性と要求] のスクリーンショット]

必要に応じて、追加のMicrosoft Entra 属性を含めることができますが、Oracle JDE シナリオでは既定の属性のみが必要です。

##### 追加のユーザー属性を構成する

**[追加のユーザー属性]** タブでは、セッション拡張のために、他のディレクトリに格納されている属性を必要とする、さまざまな分散システムをサポートできます。 次に、LDAP ソースからフェッチされた属性を追加の SSO ヘッダーとして挿入して、ロール、パートナー ID などに基づいてアクセスをさらに制御できます。

[Image: [Additional User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加のユーザー属性) のスクリーンショット]

注

この機能は Microsoft Entra ID と相関関係はありませんが、属性のもう 1 つのソースです。

##### 条件付きアクセス ポリシーを構成する

条件付きアクセス ポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御するために、Microsoft Entra の事前認証後に適用されます。

既定では、[ **使用可能なポリシー]** ビューには、ユーザーベースのアクションを含まないすべての条件付きアクセス ポリシーが一覧表示されます。

**[選択されたポリシー]** ビューには、既定で、すべてのリソースをターゲットとするすべてのポリシーが表示されます。 これらのポリシーは、テナント レベルで適用されるため、選択を解除したり、[使用可能なポリシー] リストに移動したりすることはできません。

公開されているアプリケーションに適用するポリシーを選択するには:

1. **[Available Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/使用可能なポリシー)** リストで目的のポリシーを選択します
2. 右矢印を選択して、これを **[Selected Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/選択されたポリシー)** リストに移動します

    選択されたポリシーについては、**[含める]** または **[除外する]** オプションをオンにする必要があります。 両方のオプションをオンにすると、ポリシーは適用されません。

    [Image: 条件付きアクセス ポリシーのスクリーンショット]

注

ポリシーの一覧は、最初にこのタブに切り替えたときに 1 回だけ列挙されます。ウィザードから手動でテナントにクエリを実行するための更新ボタンが用意されていますが、このボタンはアプリケーションがデプロイされている場合にのみ表示されます。

#### 仮想サーバーのプロパティを構成する

仮想サーバーは BIG-IP データ プレーン オブジェクトであり、アプリケーションに対するクライアント要求をリッスンする仮想 IP アドレスで表されます。 受信したトラフィックは、ポリシーの結果と設定に従って送信される前に、仮想サーバーに関連付けられている APM プロファイルに対して処理および評価されます。

1. **宛先アドレス**を入力します。 これは、BIG-IP がクライアント トラフィックを受信するために使用できる任意の IPv4 または IPv6 アドレスです。 対応するレコードが DNS にも存在する必要があり、それによってクライアントでは、BIG-IP の公開済みアプリケーションの外部 URL を、アプリケーション自体ではなく、この IP に解決できるようになります。 テストでは、テスト PC の localhost DNS を使用しても問題ありません。
2. **[Service Port](サービス ポート)** で、HTTPS 用に「*443*」と入力します
3. **[Enable Redirect Port](リダイレクト ポートを有効にする)** をオンにし、**リダイレクト ポート**を入力します。 これにより、受信 HTTP クライアント トラフィックが HTTPS にリダイレクトされます
4. クライアント\* SSL\* プロファイル\*を使用すると、HTTPS\* 用の仮想サーバー\*が有効になり、クライアント\*接続が TLS\* で暗号化されるようになります。 前提条件の一部として作成した**クライアント\* SSL\* プロファイル\***を選択するか、テスト\*する場合は既定値\*のままにします

    [Image: 仮想サーバーのスクリーンショット]

#### プールのプロパティを構成する

**[Application Pool](アプリケーション プール) タブ**には、1 つまたは複数のアプリケーション サーバーを含むプールとして表される、BIG-IP の背後にあるサービスの詳細が表示されます。

1. **[Select a Pool](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プールの選択)** から選択します。 新しいプールを作成するか、既存のプールを選択します
2. **[Load Balancing Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/負荷分散方法)** で、[Round Robin](ラウンド ロビン) を選択します
3. **[プール サーバー]** では、既存のノードを選択するか、Oracle JDE アプリケーションをホストするサーバーの IP とポートを指定します。

    [Image: [Application Pool](アプリケーション プール) のスクリーンショット]

##### シングル サインオンと HTTP ヘッダーの構成

**Easy Button ウィザード**では、公開されたアプリケーションに対する SSO 用に、Kerberos、OAuth Bearer、HTTP Authorization ヘッダーがサポートされています。 Oracle JDE アプリケーションにはヘッダーが必要なため、**[HTTP ヘッダー]** を有効にして、以下のプロパティを入力します。

- **ヘッダー操作:** 置換
- **ヘッダー名:** JDE\_SSO\_UID
- **ヘッダー値:** %{session.sso.token.last.username}

[Image: SSO と HTTP ヘッダーのスクリーンショット]

注

中かっこ内で定義されている APM セッション変数は、大文字と小文字が区別されます。 たとえば、Microsoft Entra 属性名が orclguid として定義されているときに OrclGUID を入力すると、属性マッピングエラーが発生します。

#### セッション管理の設定を構成する

BIG-IP のセッション管理の設定は、ユーザー セッションが終了されるか続行が許可される条件、ユーザーと IP アドレスの制限、および対応するユーザー情報を定義するために使用されます。 これらの設定の詳細については、 [F5 BIG-IP APM セッション管理](https://support.f5.com/csp/article/K18390492) 設定を参照してください。

しかし、ユーザーがサインオフするときに IdP、BIG-IP、およびユーザー エージェント間のすべてのセッションが確実に終了されるようにする、シングル ログアウト (SLO) 機能についての説明はここにはありません。 Easy Button によって SAML アプリケーションが Microsoft Entra テナントでインスタンス化されると、ログアウト URL にも、APM の SLO エンドポイントが設定されます。 このように、Microsoft Entra マイ アプリ ポータルからの IdP Initiated サインアウトでは、BIG-IP とクライアント間のセッションも終了します。

これに加え、テナントから公開済みアプリケーションの SAML フェデレーション メタデータもインポートされて、APM に Microsoft Entra ID の SAML ログアウト エンドポイントが提供されます。 これにより、SP Initiated サインアウトでクライアントと Microsoft Entra ID との間のセッションが確実に終了するようになります。 しかし、これを真に効果的に行うには、APM で、ユーザーがいつアプリケーションからサインアウトしたのかを正確に知る必要があります。

BIG-IP Web トップ ポータルを使用して公開済みアプリケーションにアクセスする場合は、そこからのサインアウトが APM によって処理され、Microsoft Entra サインアウト エンドポイントも呼び出されます。 しかし、BIG-IP Web トップ ポータルが使用されていないために、サインアウトするようにユーザーが APM に指示する方法がないシナリオについて考えてみます。ユーザーがアプリケーション自体からサインアウトした場合でも、BIG-IP では技術的にはこれが認識されません。 このため、SP によって開始されるサインアウトでは、セッションが不要になったときに確実に安全に終了されるように、慎重に検討する必要があります。 これを実現する 1 つの方法は、SLO 関数をアプリケーションのサインアウト ボタンに追加して、クライアントを Microsoft Entra SAML または BIG-IP サインアウト エンドポイントにリダイレクトできるようにすることです。 テナントの SAML サインアウト エンドポイントの URL については、**[アプリの登録] &gt; [エンドポイント]** で確認できます。

アプリに変更を加えることができない場合は、BIG-IP でアプリケーションのサインアウト呼び出しをリッスンし、要求を検出したら SLO をトリガーすることを検討してください。 これを実現するための BIG-IP iRules の使用については、[Oracle PeopleSoft SLO ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button#peoplesoft-single-logout)を参照してください。 これを実現するための BIG-IP iRules の使用の詳細については、F5 のナレッジ記事「[URI 参照ファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145)」および「[ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)」を参照してください。

### まとめ

[確認と展開] ステップでは、構成の内訳が表示されます。 **[デプロイ]** を選択してすべての設定をコミットし、エンタープライズ アプリケーションのテナント リストにアプリケーションが表示されることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/f5-big-ip-sap-erp-easy-button"} -->
## Microsoft Entra ID を使用した SAP ERP への SSO 用の F5 BIG-IP Easy Button の構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/f5-big-ip-sap-erp-easy-button
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: F5 の BIG-IP Easy Button ガイド付き構成を通じて Microsoft Entra ID を使用して SAP ERP をセキュリティで保護する方法について説明します。

この記事では、F5 の BIG-IP Easy Button ガイド付き構成を通じて Microsoft Entra ID を使用して SAP ERP をセキュリティで保護する方法について説明します。

BIG-IP と Microsoft Entra ID の統合には、以下のように数多くの利点があります。

- Microsoft Entra 事前認証と[条件付きアクセス](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)による[ゼロ トラスト ガバナンスの改善](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- Microsoft Entra ID と BIG-IP 公開サービスとの間の完全な SSO
- 単一のコントロール プレーンである [Azure portal](https://portal.azure.com/) からの ID とアクセスの管理

すべての利点については、[F5 BIG-IP と Microsoft Entra の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)に関する記事と [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオン](https://learn.microsoft.com/ja-jp/azure/active-directory/active-directory-appssoaccess-whatis)に関する記事を参照してください。

### シナリオの説明

このシナリオでは、**Kerberos 認証\*を使用して保護されたコンテンツ\*へのアクセスを管理する従来のSAP ERP\* アプリケーション\***について説明します。

従来のアプリケーションには、Microsoft Entra ID との直接的な統合をサポートする最新のプロトコルがありません。 アプリケーションは最新化できますが、コストがかかり、慎重な計画が必要であり、潜在的なダウンタイムのリスクが生じます。 代わりに、プロトコル遷移によって従来のアプリケーションと最新の ID コントロール プレーンとの間のギャップを橋渡しするために、F5 BIG IP Application Delivery Controller (ADC) を使用します。

アプリケーションの前に BIG-IP があると、Microsoft Entra の事前認証とヘッダーベースの SSO でサービスをオーバーレイできるため、アプリケーションの全体的なセキュリティ態勢が大幅に向上します。

### シナリオのアーキテクチャ

このシナリオの SHA ソリューションは次のもので構成されます。

**SAP ERP アプリケーション:** BIG-IP で公開されており、Microsoft Entra SHA によって保護されるサービス。

**Microsoft Entra ID:** Security Assertion Markup Language (SAML) ID プロバイダー (IdP)。ユーザーの資格情報の検証、条件付きアクセス (CA)、および BIG-IP に対する SAML ベースの SSO に責任があります。

**BIG-IP:** アプリケーションに対するリバース プロキシおよび SAML サービス プロバイダー (SP)。SAP サービスへのヘッダー ベースの SSO を実行する前に認証を SAML IdP に委任します。

このシナリオの SHA では、SP と IdP によって開始されたフローの両方がサポートされます。 次の図は、SP Initiated フローを示しています。

[Image: セキュア ハイブリッド アクセス - SP Initiated フロー]

| 手順 | 説明 |
| --- | --- |
| 1 | ユーザーがアプリケーション エンドポイント (BIG-IP) に接続する |
| 2 | BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトする |
| 3 | Microsoft Entra ID によって、ユーザーの事前認証と、条件付きアクセス ポリシーの適用が行われる |
| 4 | ユーザーが BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行される |
| 5 | BIG-IP が KDC に Kerberos チケットを要求する |
| 6 | BIG-IP がバックエンド アプリケーションに対し、SSO 用の Kerberos チケット一緒に要求を送信する |
| 7 | アプリケーションが要求を承認し、ペイロードを返す |

### 前提条件

以前の BIG-IP エクスペリエンスは必要ありませんが、次のものが必要です。

- Microsoft Entra ID 無料サブスクリプション (またはそれ以上)
- Azure で既存の BIG-IP または [BIG-IP Virtual Edition (VE) をデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)
- 次のいずれかの F5 BIG-IP ライセンス プラン

    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP APM スタンドアロン ライセンス
    - 既存の BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP APM アドオン ライセンス
    - 90 日間の BIG-IP 全機能[試用版ライセンス](https://www.f5.com/trial/big-ip-trial.php)。
- ユーザー ID (オンプレミス ディレクトリから Microsoft Entra ID に[同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)、または Microsoft Entra ID 内で直接作成してオンプレミス ディレクトリに返したもの)
- Microsoft Entra アプリケーション管理者の[アクセス許可](https://learn.microsoft.com/ja-jp/azure/active-directory/users-groups-roles/directory-assign-admin-roles#application-administrator)を持つアカウント
- HTTPS でサービスを公開するための [SSL Web 証明書](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide#ssl-profile) (または、テスト中に使用する既定の BIG-IP 証明書)
- Kerberos 認証用に構成された既存の SAP ERP\* 環境\*

### BIG-IP の構成方法

このシナリオで使用する BIG-IP は、テンプレートを使用した 2 つの方法や高度な構成を含め、さまざまな方法で構成できます。 この記事では、Easy ボタン テンプレートを提供する最新のガイド付き構成 16.1 について説明します。

Easy Button を使用すると、管理者は、Microsoft Entra ID と BIG-IP の間を行き来して SHA のためにサービスを有効にする必要がなくなります。 デプロイとポリシー管理は、APM のガイド付き構成ウィザードと Microsoft Graph との間で直接処理されます。 この充実した BIG-IP APM と Microsoft Entra ID の統合により、アプリケーションでは確実に ID フェデレーション、SSO、Microsoft Entra 条件付きアクセスを迅速かつ容易にサポートできるため、管理オーバーヘッドが軽減されます。

注

このガイド全体で参照されている文字列または値の例はすべて、実際の環境に合わせて置き換える必要があります。

### Easy Button を登録する

クライアントまたはサービスから Microsoft Graph にアクセスするには、[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)によって信頼されている必要があります。

また、Easy Button クライアントは Microsoft Entra ID で登録されている必要もあります。その後、BIG-IP 公開済みアプリケーションの各 SAML SP インスタンスと、SAML IdP となる Microsoft Entra ID の間に信頼を確立することが可能になります。

1. アプリケーションの管理者権限を持つアカウントを使用して、[Azure portal](https://portal.azure.com/) にサインインします。
2. 左側のナビゲーション ウィンドウから、**Microsoft Entra ID** サービスを選択します。
3. [管理] で、**[アプリの登録]**&gt;**[新規登録]** の順に選択します。
4. `F5 BIG-IP Easy Button` など、アプリケーションの表示名を入力します。
5. アプリケーションを使用できるユーザー &gt;**[Accounts in this organizational directory only] (この組織ディレクトリのアカウントのみ)** を指定します。
6. **[登録]** を選択して、初期のアプリ登録を完了します。
7. **[API\* のアクセス許可\*]** に移動し、次の Microsoft Graph\* の**アプリケーション\*のアクセス許可\***を承認します：

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
8. 組織に管理者の同意を付与します
9. **[Certificates & Secrets]\(証明書とシークレット)** ブレードで、新しい**クライアント シークレット**を生成し、メモします
10. **[概要]** ブレードで、**[クライアント ID]** と **[テナント ID]** をメモします

### Easy Button を構成する

APM\* の **[ガイド\*付き構成\*]** を開始して、**Easy Button** テンプレート\*を起動します。

1. ブラウザーから **F5 BIG-IP 管理コンソール**にサインインします
2. **[アクセス] &gt; [ガイド付き構成] &gt; [Microsoft 統合]** に移動し、**[Microsoft Entra アプリケーション]** を選択します。
3. **[次の手順に従ってソリューションを構成すると、必要なオブジェクトが作成されます]** で、構成手順の一覧を確認し、**[次へ]** を選択します。
4. **[ガイド付き構成]** で、アプリケーションの公開に必要な一連の手順に従います。

#### 構成プロパティ

これらは、一般プロパティとサービス アカウントのプロパティです。 **[構成のプロパティ]** タブでは、BIG-IP アプリケーション構成と SSO オブジェクトが作成されます。 **[Azure サービス アカウントの詳細]** セクションは、アプリケーションとして、以前に Microsoft Entra テナントに登録したクライアントを表すものとします。 これらの設定\*により、BIG-IP\* の OAuth クライアント\*では、通常は手動で構成する\* SSO\* プロパティと共に、SAML SP をテナント\*に直接個別に登録できるようになります。 Easy Button により、公開\*されて SHA が有効になっているすべての BIG-IP\* サービス\*に対してこの操作が行われます。

これらの一部はグローバル設定であるため、より多くのアプリケーションを公開するために再利用でき、デプロイの時間と労力をさらに削減するのに役立ちます。

1. 一意の**構成名**を指定して、管理者が Easy Button 構成を容易に区別できるようにします
2. **[Single Sign-On (SSO) & HTTP Headers](シングル サインオン (SSO) と HTTP ヘッダー)** を有効にします
3. テナント\*に Easy Button クライアントを登録するときに記録した**テナント\* ID\*、クライアント ID\***、および**クライアント シークレット\***を入力する\*
4. BIG-IP\* がテナントに正常に接続されたことを確認してから、**[次へ]** を選択する

    [Image: 構成の一般プロパティとサービス アカウントのプロパティのスクリーンショット]

#### サービス プロバイダー

サービス\* プロバイダー\*設定\*では、SHA によって保護されるアプリケーション\*の SAML SP インスタンス\*のプロパティを定義します。

1. **ホスト**を入力します。 これは、セキュリティで保護されるアプリケーションのパブリック FQDN です
2. **エンティティ ID を入力します。**これは、トークンを要求する SAML SP を識別するために Microsoft Entra ID が使用する識別子です

    [Image: [Service Provider](サービス プロバイダー) 設定のスクリーンショット]

    省略可能な **[セキュリティ設定]** で、発行された SAML アサーションを Microsoft Entra ID で暗号化するかどうかを指定します。 Microsoft Entra ID と BIG-IP APM の間でアサーションを暗号化すると、コンテンツ トークンが傍受されないこと、および個人や会社のデータが侵害されないことの追加の保証が提供されます。
3. **[アサーション解読秘密キー]** の一覧から、**[新規作成]** を選択します

    [Image: Easy Button の構成のスクリーンショット - 新しいインポートの作成]
4. **OK** を選択します。 新しいタブで **[Import SSL Certificate and Keys](SSL 証明書とキーのインポート)** ダイアログが開きます
5. **PKCS 12 (IIS)** を選択して、証明書と秘密キーをインポートします。 プロビジョニング\*が完了したら、ブラウザー\* タブ\*を閉じて、メイン タブ\*に戻ります

    [Image: Easy Button の構成のスクリーンショット - 新しい証明書をインポートする]
6. **[Enable Encrypted Assertion](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/暗号化アサーションを有効にする)** をオンにします
7. 暗号化を有効にした場合は、**[Assertion Decryption Private Key](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アサーション解読秘密キー)** の一覧から証明書を選択します。 これは、Microsoft Entra アサーションを解読するために BIG-IP APM で使用される証明書の秘密キーです。
8. 暗号化を有効にした場合は、**[Assertion Decryption Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アサーション解読証明書)** の一覧から証明書を選択します。 これは、発行された SAML アサーションを暗号化するために BIG IP が Microsoft Entra ID にアップロードする証明書です

    [Image: サービス プロバイダーの [Security Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ設定) のスクリーンショット]

#### マイクロソフト エントラ ID

このセクションでは、Microsoft Entra テナント内で新しい BIG-IP SAML アプリケーションを手動で構成するために通常使用するすべてのプロパティを定義します。

Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP、その他のアプリ用の汎用 SHA テンプレート用の定義済みアプリケーション テンプレートのセットが用意されています。

このシナリオでは、**Azure の構成**ページで **[SAP ERP Central Component]**&gt;**[追加]** を選択して Azure の構成を開始します。

##### Azure の構成

1. BIG-IP が Microsoft Entra テナントで作成するアプリの**表示名**と、[ユーザーが MyApps ポータル](https://myapplications.microsoft.com/)に表示するアイコンを入力します
2. IdP Initiated サインオンを有効にする\*には、**[サインオン URL\*]** に何も入力しないでください (省略可能)

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - 表示情報を追加する]
3. **[署名キー]** と **[署名証明書]** の横にある更新アイコンを選択して、先ほどインポートした証明書を見つけます
4. **[Signing Key Passphrase]\(署名キーのパスフレーズ)** で証明書のパスワードを入力します
5. **[署名オプション]** (省略可能) を有効にします。 これにより、BIG-IP は、Microsoft Entra ID によって署名されたトークンと要求のみを受け入れるようになります

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - 署名証明書情報を追加する]
6. **ユーザーとユーザー グループ**は、Microsoft Entra テナントから動的に照会され、アプリケーションへのアクセスを承認するために使用されます。 後でテストに使用できるユーザーまたはグループを追加します。それ以外の場合は、すべてのアクセスが拒否されます

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - ユーザーとグループを追加する]

##### ユーザー属性と要求

ユーザーが Microsoft Entra ID に対して正常に認証されると、Microsoft Entra ID は、ユーザーを一意に識別する要求と属性の既定のセットを使用して SAML トークンを発行します。 **[ユーザー属性と要求] タブ**には、新しいアプリケーションに対して発行する既定の要求が表示されます。 また、さらに多くの要求を構成することもできます。

AD インフラストラクチャ\*は、内部と外部の両方で使用される .com ドメイン\* サフィックスに基づいているため、機能的な KCD SSO\* 実装を実現するための追加の属性\*は必要ありません。 代替サフィックスを使用して複数のドメインまたはユーザーのログインがある場合は、 [詳細な記事](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-kerberos-advanced) を参照してください。

[Image: [ユーザー属性と要求] のスクリーンショット]

必要に応じて、追加の Microsoft Entra 属性を含めることができますが、この SAP ERP のシナリオでは既定の属性のみが必要です。

##### 追加のユーザー属性

**[Additional User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加のユーザー属性)** タブでは、セッション拡張のために、他のディレクトリに格納されている属性を必要とする、さまざまな分散システムをサポートできます。 次に、LDAP ソースからフェッチされた属性を追加の SSO ヘッダーとして挿入して、ロール、パートナー ID などに基づいてアクセスをさらに制御できます。

[Image: [Additional User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加のユーザー属性) のスクリーンショット]

注

この機能は Microsoft Entra ID と相関関係はありませんが、属性のもう 1 つのソースです。

##### 条件付きアクセス ポリシー

条件付きアクセス ポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御するために、Microsoft Entra の事前認証後に適用されます。

既定では、[ **使用可能なポリシー]** ビューには、ユーザー ベースのアクションを含まないすべての条件付きアクセス ポリシーが一覧表示されます。

**[選択されたポリシー]** ビューには、既定で、すべてのリソースをターゲットとするすべてのポリシーが表示されます。 これらのポリシーは、テナント レベルで適用されるため、選択を解除したり、[使用可能なポリシー] リストに移動したりすることはできません。

公開されているアプリケーションに適用するポリシーを選択するには:

1. **[Available Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/使用可能なポリシー)** リストで目的のポリシーを選択します
2. 右矢印を選択して、これを **[Selected Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/選択されたポリシー)** リストに移動します

選択したポリシーでは、**[Include](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/含める)** または **[Exclude](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/除外する)** オプションをオンにする必要があります。 両方のオプションをオンにした場合、選択したポリシーは適用されません。

[Image: 条件付きアクセス ポリシーのスクリーンショット]

注

ポリシーの一覧は、最初にこのタブに切り替えたときに 1 回だけ列挙されます。ウィザードから手動でテナントにクエリを実行するための更新ボタンが用意されていますが、このボタンはアプリケーションがデプロイされている場合にのみ表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは BIG-IP データ プレーン オブジェクトであり、アプリケーションに対するクライアント要求をリッスンする仮想 IP アドレスで表されます。 受信したトラフィックは、ポリシーの結果と設定に従って送信される前に、仮想サーバーに関連付けられている APM プロファイルに対して処理および評価されます。

1. **宛先アドレス**を入力します。 これは、BIG-IP がクライアント トラフィックを受信するために使用できる任意の IPv4 または IPv6 アドレスです。 対応するレコードが DNS にも存在する必要があり、それによってクライアントでは、BIG-IP の公開済みアプリケーションの外部 URL を、アプリケーション自体ではなく、この IP に解決できるようになります。 テスト\*では、テスト\* PC の localhost\* DNS\* を使用しても問題ありません
2. **[Service Port](サービス ポート)** で、HTTPS 用に「*443*」と入力します
3. **[Enable Redirect Port](リダイレクト ポートを有効にする)** をオンにし、**リダイレクト ポート**を入力します。 これにより、受信 HTTP クライアント トラフィックが HTTPS にリダイレクトされます
4. クライアント\* SSL\* プロファイル\*を使用すると、HTTPS\* 用の仮想サーバー\*が有効になり、クライアント\*接続が TLS\* で暗号化されるようになります。 前提条件の一部として作成した **クライアント SSL プロファイル** を選択するか、テスト中に既定値のままにします

[Image: 仮想サーバーのスクリーンショット]

#### プールのプロパティ

**[Application Pool](アプリケーション プール) タブ**には、1 つまたは複数のアプリケーション サーバーを含むプールとして表される、BIG-IP の背後にあるサービスの詳細が表示されます。

1. **[プールの選択]** で、プールの新規作成を選択するか、既存のプールを選択します
2. **[Load Balancing Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/負荷分散方法)** で、[Round Robin](ラウンド ロビン) を選択します
3. **[プール\* サーバー\*]** では、既存のサーバー ノード\*を選択するか、ヘッダーベースのアプリケーション\*をホスト\*するバックエンド\* ノード\*の IP\* とポート\*を指定します

    [Image: [Application Pool](アプリケーション プール) のスクリーンショット]

##### シングル サインオン & HTTP ヘッダー

SSO を有効にすると、ユーザーは資格情報を入力しなくても、BIG-IP で公開されているサービスにアクセスできるようになります。 **Easy Button ウィザード**では、SSO 用に Kerberos、OAuth Bearer、HTTP 承認ヘッダーがサポートされています。 この手順を完了するには、前に作成した Kerberos 委任アカウントが必要です。

**[Kerberos]** と **[詳細設定の表示]** を有効にして、次の情報を入力します。

- **[ユーザー名ソース]:** SSO のためにキャッシュする優先ユーザー名を指定します。 ユーザー ID のソースとして任意のセッション変数を指定できますが、*session.saml.last.identity* は、ログインしたユーザー ID を含む Microsoft Entra 要求が保持されるため、最も効果的に機能する傾向があります
- **[ユーザー領域ソース]:** ユーザー ドメインが BIG-IP の Kerberos 領域と異なる場合に必要です。 その場合、APM セッション変数には、ログインしているユーザー ドメインが含まれます。 たとえば、*session.saml.last.attr.name.domain* など

    [Image: SSO と HTTP ヘッダーのスクリーンショット]
- **[KDC]:** ドメイン コントローラーの IP (DNS が構成されていて効率的である場合は FQDN)
- **[UPN サポート]:** APM で Kerberos チケット発行に UPN を使用する場合に有効にします
- **SPN パターン:** HTTP/%h を使用して、クライアント要求のホスト ヘッダーを使用するように APM に通知し、Kerberos トークンを要求している SPN をビルドします。
- **[承認の送信]:** 1 回目の要求で Kerberos トークンを受信するのではなく、認証をネゴシエートした方が望ましいアプリケーションでは無効にしてください (例: *Tomcat*)。

    [Image: SSO メソッド構成のスクリーンショット]

#### セッションの管理

BIG-IP のセッション管理の設定は、ユーザー セッションが終了されるか続行が許可される条件、ユーザーと IP アドレスの制限、および対応するユーザー情報を定義するために使用されます。 これらの設定の詳細については、[F5 のドキュメント](https://support.f5.com/csp/article/K18390492)を参照してください。

ただし、ユーザー\*がログオフする\*ときに IdP\*、BIG-IP\*、およびユーザー エージェント\*間のすべてのセッション\*が確実に終了されるようにする、シングル ログアウト\* (SLO\*) 機能についての説明はありません。 Easy Button によって SAML アプリケーションが Microsoft Entra テナントにデプロイされると、ログアウト URL にも、APM の SLO エンドポイントが指定されます。 このように、Microsoft [MyApps ポータル*](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510) からの IdP\* Initiated サインアウトでは、BIG-IP\* とクライアント\*間のセッション\*も終了します。

デプロイ時には、テナントから公開済みアプリケーションの SAML フェデレーション メタデータがインポートされて、APM に Microsoft Entra ID の SAML ログアウト エンドポイントが提供されます。 これにより、SP Initiated サインアウトでクライアントと Microsoft Entra ID との間のセッションが確実に終了します。

### まとめ

この最後の手順では、構成の概要を示します。 **[デプロイ]** を選択してすべての設定をコミットし、エンタープライズ アプリケーションのテナント リストにアプリケーションが表示されることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fabric-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Fabric を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fabric-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fabric の間でシングル サインオンを構成する方法について説明します。

この記事では、Fabric と Microsoft Entra ID を統合する方法について説明します。 Fabric と Microsoft Entra ID を統合すると、次のことができます。

- Fabric へのアクセス権を持つユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Fabric に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fabric でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fabric では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからFabricを追加する

Microsoft Entra ID への Fabric の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Fabric を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Fabric**」と入力します。
4. 結果パネルから **[Fabric** ] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fabric の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Fabric に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Fabric の関連ユーザーとの間にリンク関係を確立する必要があります。

Fabric に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fabric SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fabric ロールを作成** する - Fabric で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Fabric**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。`https://<HOSTNAME>`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。`https://<HOSTNAME>:<PORT>/api/authenticate`
    3. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。`https://<HOSTNAME>:<PORT>`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、K2View COE チームに問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **ファブリックのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]
8. [ **トークン暗号化** ] セクションで、[ **証明書のインポート** ] を選択し、ファブリック証明書ファイルをアップロードします。 K2View COE チームに連絡して入手してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fabric SSO の構成

**Fabric** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とコピーした適切な URL を K2View COE サポート チームに送信します。 チームは、SAML SSO 接続が両方の側で正しく設定されるように設定を構成します。

詳細については、*K2view ナレッジ ベース*の*ファブリック SAML 構成*と [Microsoft Entra SAML セットアップ ガイド](https://support.k2view.com/knowledge-base.html)を参照してください。

#### ファブリック ロールを作成する

K2View COE サポート チームと協力して、Microsoft Entra グループに一致し、Fabric を使用するユーザーに関連する Fabric ロールを設定します。 グループ ID は SAML 応答で送信されるため、Fabric チームにグループ ID を提供します。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- Azure portal で、[ **このアプリケーションをテスト**する] を選択します。 Fabric のサインオン URL にリダイレクトされ、そこでログイン フローを開始できます。
- Fabric のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリ ポータルで **[Fabric** ] タイルを選択すると、Fabric のサインオン URL にリダイレクトされます。 マイ アプリ ポータルの詳細については、「マイ アプリ [ポータルの概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/facebook-work-accounts-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Meta Work Accounts を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/facebook-work-accounts-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-09
- Summary: ユーザー アカウントを Meta Work Accounts に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Meta Work アカウントと Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Meta Work アカウント](https://work.meta.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Meta Work Accounts でユーザーを作成する
- アクセスが不要になった Meta Work アカウントのユーザーを削除する
- Microsoft Entra ID と Meta Work Accounts の間でユーザー属性の同期を維持する
- Meta Work Accounts にシングル サインオンする (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 会社の設定を変更して統合を構成するためのアクセス許可を持っている、職場アカウント内の管理者アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Meta Work アカウントの間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra アプリケーション ギャラリーから Meta Work Accounts を追加する

Microsoft Entra アプリケーション ギャラリーから Meta Work Accounts を追加して、Meta Work Accounts に対するプロビジョニングの管理を開始します。 以前、SSO のために Meta Work Accounts を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 3: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 4: Meta Work Accounts に対する自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Meta Work Accounts の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**にアクセスする
3. アプリケーションの一覧で [ **Meta Work Accounts**] を選択します。
4. [プロビジョニング] タブ **を** 選択します。
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、[ **承認**] を選択します。 **メタワークアカウント**の承認ページにリダイレクトされます。 Meta Work Accounts ユーザー名を入力し、[ **続行** ] ボタンを選択します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Meta Work アカウントに接続できることを確認します。 接続が失敗した場合、お使いの Meta Work Accounts アカウントに Admin アクセス許可があることを確認し、再試行してください。

    [Image: [Meta Work Accounts authorization](メタワーク アカウントの承認) ページを示すスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Meta Work Accounts に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Meta Work Accounts のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Meta Work Accounts API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 |  |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/factset-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に FactSet を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/factset-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FactSet の間のシングル サインオンを構成する方法について説明します。

この記事では、FactSet と Microsoft Entra ID を統合する方法について説明します。 FactSet を Microsoft Entra ID と統合すると、次のことが可能になります。

- フェデレーションを介して FactSet URL にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで FactSet に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な FactSet サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FactSet では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの FactSet の追加

Microsoft Entra ID への FactSet の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FactSet を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**FactSet**」と入力します。
4. [結果] パネルから **[FactSet]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FactSet 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、FactSet に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと FactSet の関連ユーザーとの間にリンク関係を確立する必要があります。

FactSet に対する Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FactSet の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FactSet テストユーザーの作成** - FactSet で B.Simon に対応するものとしてユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[FactSet]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://auth.factset.com`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://auth.factset.com/sp/ACS.saml2`
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[フェデレーション メタデータ XML]** を見つけて **[ダウンロード]** を選択し、メタデータ ファイルをダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[FactSet のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FactSet の SSO の構成

FactSet 側でシングル サインオンを構成するには、FactSet の[コントロール センター](https://controlcenter.factset.com)にアクセスし、Azure portal の  ページで&gt; とコピーした適切な URL を構成する必要があります。 このページへのアクセスが必要な場合は、[FactSet サポート チーム](https://www.factset.com/contact-us) に問い合わせ、FactSet 製品 8514 (コントロール センター - ソース IP、セキュリティ + 認証) を要求してください。

#### FactSet のテスト ユーザーの作成

FactSet アカウント サポート担当者と連携するか、[FactSet サポート チーム](https://www.factset.com/contact-us)に連絡して、FactSet プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- FactSet では、SP によって開始される SAML のみがサポートされます。 SSO をテストするには、 [Issue Tracker](https://issuetracker.factset.com) や [FactSet-Web](https://my.factset.com) などの認証済みの FactSet URL にアクセスし、ログオン ポータルで **[シングル Sign-On (SSO)]** を選択し、後続のページでメール アドレスを指定します。 追加情報と使用方法については、付属の[ドキュメント](https://download.factset.com/documents/web/FactSet_Single_Sign-On.pdf)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fareharbor-saml-sso-tutorial"} -->
## Microsoft Entra IDを使用してシングルサインオンするためにFareHarbor SAML SSOを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fareharbor-saml-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fareharbor SAML SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Fareharbor SAML SSO と Microsoft Entra ID を統合する方法について説明します。 Fareharbor SAML SSO と Microsoft Entra ID を統合すると、次のことができます。

- Fareharbor SAML SSO にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで Fareharbor SAML SSO に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fareharbor SAML SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fareharbor SAML SSO では、**SP** によって開始される SSO のみがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからFareharbor SAML SSOを追加する

Microsoft Entra ID への Fareharbor SAML SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Fareharbor SAML SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Insignia SAML SSO**」と入力します。
4. 結果のパネルから Fareharbor SAML SSO  選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fareharbor SAML SSO の Microsoft Entra SSO の構成とテスト

B.Simon **というテストユーザーを使用して、Fareharbor SAML SSO に対して Microsoft Entra SSO**の構成とテストを行います。 SSO を機能させるには、Microsoft Entra ユーザーと Fareharbor SAML SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Fareharbor SAML SSO で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fareharbor SAML SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Fareharbor SAML SSO テスト ユーザーの作成** - Fareharbor SAML SSO で Microsoft Entra に登録されている B.Simon に対応するユーザーを作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Fareharbor SAML SSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **識別子 (エンティティ ID)** テキスト ボックスに、URL を入力します: `https://fareharbor.com`

    b。 **[応答 URL]** テキスト ボックスでは、次の URL/パターンのいずれかを入力します。

    | **応答 URL** |
    | --- |
    | `https://fareharbor.com/api/v1/login/provider/azure/complete/` |
    | `https://<ENVIRONMENT>.fareharbor.com/api/v1/login/provider/azure/complete/` |

    c. [**サインオン URL** テキスト ボックスに、次のいずれかの URL/パターンを入力します。

    | **サインオン URL** |
    | --- |
    | `https://fareharbor.com/login/` |
    | `https://<ENVIRONMENT>.fareharbor.com/login/` |

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[Fareharbor SAML SSO サポート チーム](mailto:support@fareharbor.com) にお問い合わせください。 Microsoft Entra 管理センターの「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、**証明書 (未加工)** を探し、[**ダウンロード]** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **Fareharbor SAML SSO** の設定セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fareharbor SAML SSO の構成

**Fareharbor SAML SSO** 側でシングル サインオンを構成するには、ダウンロードされた**証明書 (未加工)** と Microsoft Entra 管理センターからコピーされた適切な URL を [Fareharbor SAML SSO のサポート チーム](mailto:support@fareharbor.com)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Fareharbor SAML SSO テスト ユーザーの作成

1. Fareharbor SAML SSO 企業サイトに管理者としてログインします。
2. **設定**&gt;**ユーザー & アクセス許可**に移動します。

    [Image: スクリーンショットは、アプリケーションでユーザーを作成する方法を示しています。]
3. **ユーザー**&gt;**新しいユーザー** に移動し、次の手順を実行します。

    [Image: スクリーンショットは、ページで新しいユーザーを作成する方法を示しています。]

    1. 「**名前**」テキストボックスに、ユーザーの有効な名前を入力します。
    2. **ユーザー名** テキストボックスに、ユーザー名を入力します。
    3. [**Email** ボックスに、Azure SSO の電子メール アドレスを入力します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Fareharbor SAML SSO サインオン URL にリダイレクトされます。
- Fareharbor SAML SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Fareharbor SAML SSO] タイルを選択すると、このオプションは Fareharbor SAML SSO のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fastly-edge-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Fastly Edge Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fastly-edge-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fastly Edge Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、Fastly Edge Cloud と Microsoft Entra ID を統合する方法について説明します。 Fastly Edge Cloud を Microsoft Entra ID と統合すると、次のことが可能になります。

- Fastly Edge Cloud にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Fastly Edge Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fastly Edge Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fastly Edge Cloud では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Fastly Edge Cloud の追加

Fastly Edge Cloud の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に、Fastly Edge Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Fastly Edge Cloud**」と入力します。
4. 結果のパネルから **[Fastly Edge Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fastly Edge Cloud 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Fastly Edge Cloud に対する Microsoft Entra SSO を構成およびテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Fastly Edge Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Fastly Edge Cloud に対して Microsoft Entra SSO を構成およびテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fastly Edge Cloud SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fastly Edge Cloud テスト ユーザーの作成** - Fastly Edge Cloud で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Fastly Edge Cloud**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.fastly.com/saml/<CUSTOM_IDENTIFIER>`

    注

    この値は実際の値ではありません。 実際の識別子で値を更新します。 この値を取得するには、[Fastly Edge Cloud クライアント サポート チーム](mailto:support@fastly.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Fastly Edge Cloud のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fastly Edge Cloud SSO の構成

**Fastly Edge Cloud** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Fastly Edge Cloud サポート チーム](mailto:support@fastly.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Fastly Edge Cloud テスト ユーザーの作成

このセクションでは、Fastly Edge Cloud で B.Simon というユーザーを作成します。 [Fastly Edge Cloud サポート チーム](mailto:support@fastly.com)と連携して、Fastly Edge Cloud プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fastly Edge Cloud に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Fastly Edge Cloud] タイルを選択すると、SSO を設定した Fastly Edge Cloud に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fax-plus-tutorial"} -->
## Microsoft Entra ID でシングルサインオンを使用するために、FAX.PLUS を構成します。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fax-plus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FAX.PLUS の間のシングル サインオンを構成する方法について説明します。

この記事では、FAX.PLUS を Microsoft Entra ID と統合する方法について学びます。 FAX.PLUS を Microsoft Entra ID と統合すると、次のことが可能になります。

- FAX.PLUS へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して FAX.PLUS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な FAX.PLUS サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FAX.PLUS では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- FAX.PLUS では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの FAX.PLUS の追加

Microsoft Entra ID への FAX.PLUS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FAX.PLUS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**FAX.PLUS**」と入力します。
4. 結果パネルから **[FAX.PLUS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FAX.PLUS に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、FAX.PLUS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと FAX.PLUS の関連ユーザーとの間にリンク関係を確立する必要があります。

FAX.PLUS に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FAX.PLUS の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FAX.PLUS テストユーザーを作成する** - Microsoft Entra のユーザー表現にリンクされた、FAX.PLUS 上の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[FAX.PLUS]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.fax.plus/login`
7. **保存** を選択します。
8. FAX.PLUS アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. 上記に加えて、FAX.PLUS アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastname | ユーザーの名字 |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[FAX.PLUS のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FAX.PLUS の SSO の構成

1. 別の Web ブラウザー ウィンドウで、FAX.PLUS 企業サイトに管理者としてサインインします。
2. 管理者プロファイルの **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)** セクションに移動し、 **[Advanced](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細)** まで下にスクロールします。
3. **[構成**] パネルで、[**シングル サインオンのアクティブ化**] ボタンを選択し、次の手順を実行します。

    [Image: アカウント]

    a. [ **エンティティ ID** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    b。 [ **単一 Sign-On URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。

    c. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[x.509 証明書]** ボックスに貼り付けます。

    d. SSO 経由でログインする場合は、 **[Only Allow SSO Login for Admin User](管理者ユーザーにのみ SSO ログインを許可する)** チェック ボックスをオンにします。

    e. **確認** を選択します。

#### FAX.PLUS のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを FAX.PLUS に作成します。 FAX.PLUS では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 FAX.PLUS にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションは FAX にリダイレクトされます。PLUS ログイン フローを開始できるサインオン URL。
- FAX.PLUS のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- **このアプリケーションをテスト**を選択すると、SSO を設定した FAX.PLUS アカウントに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 アプリの「FAX.PLUS」タイルを選択すると、SP モードで構成されている場合はログインフローを開始するためにアプリケーションのサインオンページにリダイレクトされます。IDP モードで構成されている場合は、設定された SSO により自動的に「FAX.PLUS」にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fcm-hub-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に FCM HUB を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fcm-hub-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FCM HUB の間のシングル サインオンを構成する方法について説明します。

この記事では、FCM HUB と Microsoft Entra ID を統合する方法について説明します。 FCM HUB を Microsoft Entra ID と統合すると、次のことが可能になります。

- FCM HUB へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して FCM HUB に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FCM HUB のシングル サインオン (SSO) が有効になったサブスクリプション。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FCM HUB では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの FCM HUB の追加

Microsoft Entra ID への FCM HUB の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FCM HUB を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**FCM HUB**」と入力します。
4. 結果のパネルから **[FCM HUB]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FCM HUB に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用し、FCM HUB に対する Microsoft Entra SSO を構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと FCM HUB の関連ユーザーとの間にリンク関係を確立する必要があります。

FCM HUB に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FCM HUB の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FCM HUB テストユーザーの作成** - FCM HUB に B.Simon の対応ユーザーを作成して、そのユーザーが Microsoft Entra における B.Simon の表現とリンクするようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**FCM HUB**&gt;**シングルサインオン**に移動する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://hub.fcm.travel/SsoSp/SpInit?clientid=<CUSTOMID>`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、自分に割り当てられているアカウント マネージャーに連絡するか、[FCM HUB クライアント サポート チーム](mailto:fcmssoadmin@us.fcm.travel)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **保存** を選択します。
8. [ **要求の管理** ] ページの [ **ユーザー属性と要求** ] セクションで、次のカスタム属性を追加します。

    - **名前**: PortalID
    - **ソース**: 属性
    - **ソース属性**: PortalID、FCM によって提供される値
9. **[SAML 署名証明書]** セクションで、編集オプションを使用して次の設定を選択または入力し、 **[保存]** を選択します。

    - **署名オプション**: SAML 応答とアサーションに署名
    - **署名アルゴリズム**: SHA-256
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Set up FCM HUB](FCM HUB の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FCM HUB SSO を構成する

**FCM HUB** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を、サポートに関して自分に割り当てられているアカウント マネージャーに送信するか、[FCM HUB クライアント サポート チーム](mailto:fcmssoadmin@us.fcm.travel)に連絡する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FCM HUB テスト ユーザーを作成する

このセクションでは、FCM HUB で B.Simon というユーザーを作成します。 ユーザーを FCM HUB プラットフォームに追加するには、アカウント マネージャーと協力するか、[FCM HUB クライアント サポート チーム](mailto:fcmssoadmin@us.fcm.travel)に連絡してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる FCM HUB のサインオン URL にリダイレクトされます。
- FCM HUB のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した FCM HUB に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [FCM HUB] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した FCM HUB に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/federated-directory-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Federated Directory を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/federated-directory-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: ユーザー アカウントを Federated Directory に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Federated Directory で実行する手順と、ユーザーやグループを Federated Directory に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- フェデレーション ディレクトリにユーザーを作成します。
- アクセスが不要になった場合は、フェデレーション ディレクトリのユーザーを削除します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーションオーナー](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)

- [連合ディレクトリ](https://www.federated.directory/pricing)。
- 管理者アクセス許可を持つ Federated Directory 内のユーザー アカウント。

### ユーザーを Federated Directory に割り当てる

Microsoft Entra IDでは、割り当てと呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Federated Directory へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Federated Directory に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Federated Directory に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーをフェデレーション ディレクトリに割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Federated Directory にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 既定のアクセス ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニング用に Federated Directory を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Federated Directory を構成する前に、Federated Directory で SCIM プロビジョニングを有効にする必要があります。

1. [Federated Directory Admin Console](https://federated.directory/of) にサインインします

    [Image: 会社名を入力するためのフィールドを示す Federated Directory 管理コンソールのスクリーンショット。サインイン ボタンも表示されています。]
2. **[Directories](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ディレクトリ) &gt; [User directories](ユーザー ディレクトリ)** に移動して、テナントを選択します。

    [Image: ディレクトリとフェデレーション ディレクトリのMicrosoft Entra IDテストが強調表示されているフェデレーション ディレクトリ管理コンソールのスクリーンショット。]
3. 永続的なベアラー トークンを生成するには、**[Directory Keys](ディレクトリ キー) &gt; [Create New Key](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいキーの作成)** に移動します。

    [Image: Federated Directory 管理コンソールの [Directory Keys](ディレクトリ キー) ページのスクリーンショット。[Create New Key](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいキーの作成) ボタンが強調表示されます。]
4. ディレクトリ キーを作成します。

    [Image: [名前] と [説明] フィールドと [Create key](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/キーの作成) ボタンがある Federated Directory 管理コンソールの [Create directory key](ディレクトリ キーの作成) ページのスクリーンショット。]
5. **[アクセス トークン]** 値をコピーします。 この値は、フェデレーション ディレクトリ アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Federated Directory 管理コンソールのページのスクリーンショット。アクセス トークンのプレースホルダーとキー名、説明、発行者が表示されています。]

### ギャラリーから Federated Directory を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Federated Directory を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Federated Directory を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーからフェデレーション ディレクトリを追加するには、次の手順に従います:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、「**Federated Directory**」と入力し、結果パネルで **[Federated Directory]** を選択します。

    [Image: 結果一覧のフェデレーション ディレクトリのスクリーンショット。]
4. 個別のブラウザーで、以下で強調表示されている **URL** に移動します。

    [Image: フェデレーション ディレクトリに関する情報を表示するAzure ポータルのページのスクリーンショット。U R L の値が強調表示されています。]
5. [ **ログイン] を選択します**。

    [Image: Federated Directory サイトのメイン メニューのスクリーンショット。[ログイン] ボタンが強調表示されます。]
6. Federated Directory は OpenIDConnect アプリであるため、Microsoftの職場アカウントを使用してフェデレーション ディレクトリにログインすることを選択します。

    [Image: フェデレーション ディレクトリ サイトの S C I M A D テスト ページのスクリーンショット。Microsoft アカウントが強調表示されている状態でログインします。]
7. 認証に成功した後、同意ページの同意プロンプトを受け入れます。 その後、アプリケーションがテナントに自動的に追加され、フェデレーション ディレクトリ アカウントにリダイレクトされます。

### Federated Directory への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Federated Directory のユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Federated Directory の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーション一覧で **[Federated Directory]** を選択します。

    [Image: アプリケーションの一覧の [フェデレーション ディレクトリ] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [自動] オプションが強調表示された [プロビジョニング モード] ドロップダウン リストのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、フェデレーション ディレクトリ テナントの URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDがフェデレーション ディレクトリに接続できることを確認します。 接続に失敗した場合は、フェデレーション ディレクトリ アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute Mapping** セクションで、Microsoft Entra IDから Federated Directory に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Federated Directory のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: 属性マッピング ページのスクリーンショット。テーブルには Microsoft Entra ID とフェデレーション ディレクトリの属性と、その一致状況が一覧表示されます。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「自動ユーザー アカウント プロビジョニングに関するレポート
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fence-mobile-remotemanager-sso-tutorial"} -->
## Microsoft Entra ID によるシングルサインオンのために FENCE-Mobile RemoteManager SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fence-mobile-remotemanager-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FENCE-Mobile RemoteManager SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、FENCE-Mobile RemoteManager SSO と Microsoft Entra ID を統合する方法について説明します。 FENCE-Mobile RemoteManager SSO を Microsoft Entra ID と統合すると、次のことが可能になります。

- FENCE-Mobile RemoteManager SSO にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して FENCE-Mobile RemoteManager SSO に自動的にサインインするようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FENCE-Mobile RemoteManager SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FENCE-Mobile RemoteManager SSO では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの FENCE-Mobile RemoteManager SSO の追加

FENCE-Mobile RemoteManager SSO の Microsoft Entra ID への統合を構成するには、ギャラリーの FENCE-Mobile RemoteManager SSO をマネージド SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックス **FENCE-Mobile RemoteManager SSO** と入力します。
4. **FENCE-Mobile RemoteManager SSO** を結果パネルから選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FENCE-Mobile RemoteManager SSO 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、FENCE-Mobile RemoteManager SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと FENCE-Mobile RemoteManager SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

FENCE-Mobile RemoteManager SSO に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FENCE-Mobile RemoteManager SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **FENCE-Mobile RemoteManager SSO テスト ユーザーの作成** - FENCE-Mobile RemoteManager SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[RemoteManager SSO]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `api://www.fence-mrm.bsc.fujitsu.com/<TID>/<GUID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://www.fence-mrm.bsc.fujitsu.com/SConsole/SSOServlet?tid=<TID>` |
    | `https://ctl.fence-mrm.bsc.fujitsu.com/SControl/SSOServlet?tid=<TID>` |
    | `https://www.fence-mrm.bsc.fujitsu.com/IMDMLogin/SSOServlet?tid=<TID>` |
    |  |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.fence-mrm.bsc.fujitsu.com/SConsole/login.jsf?tid=<TID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値FENCE-Mobile 取得するには、 [RemoteManager SSO クライアント サポート チーム](mailto:fj-FMRM_Dev_Azure@dl.jp.fujitsu.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **FENCE-Mobile RemoteManager SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FENCE-Mobile RemoteManager SSO を構成する

** RemoteManager SSO 側FENCE-Mobile** シングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [RemoteManager SSO サポート チームFENCE-Mobile](mailto:fj-FMRM_Dev_Azure@dl.jp.fujitsu.com) 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FENCE-Mobile RemoteManager SSO テスト ユーザーを作成する

このセクションでは、FENCE-Mobile RemoteManager SSO で Britta Simon というユーザーを作成します。 [FENCE-Mobile RemoteManager SSO サポート チーム](mailto:fj-FMRM_Dev_Azure@dl.jp.fujitsu.com)と協力して、FENCE-Mobile RemoteManager SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプション FENCE-Mobile ログイン フローを開始できる RemoteManager SSO サインオン URL にリダイレクトされます。
- FENCE-Mobile RemoteManager SSO サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで FENCE-Mobile RemoteManager SSO タイルを選択すると、このオプションは FENCE-Mobile RemoteManager SSO サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fexa-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Fexa を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fexa-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fexa 間のシングル サインオンを構成する方法について説明します。

この記事では、Fexa と Microsoft Entra ID を統合する方法について説明します。 Fexa を Microsoft Entra ID と統合すると、次のことが可能になります。

- Fexa にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Fexa に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fexa でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fexa では、**IDP** によって開始される SSO がサポートされています。
- Fexa では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Fexa を追加する

Microsoft Entra ID への Fexa の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Fexa を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Fexa**」と入力します。
4. 結果のパネルから **Fexa** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fexa に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Fexa に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するためには、Microsoft Entra ユーザーと Fexa の関連ユーザーとの間にリンク関係を確立する必要があります。

Fexa に対する Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fexa の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fexa テスト ユーザーの作成** - Fexa で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Fexa]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.fexa.io`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.fexa.io/users/saml/auth`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 この値を取得するには、[Fexa クライアント サポート チーム](mailto:support@fexa.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Fexa アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Fexa アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | User.givenname |
    | 姓 | ユーザーの名字 |
8. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
9. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
10. **[Fexa のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fexa の SSO を構成する

**Fexa** 側でシングル サインオンを構成するには、**サムプリントの値**とアプリケーション構成からコピーした適切な URL を [Fexa サポート チーム](mailto:support@fexa.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Fexa のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Fexa に作成します。 Fexa では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Fexa にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fexa に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Fexa] タイルを選択すると、SSO を設定した Fexa に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fidelity-planviewer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Fidelity PlanViewer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fidelity-planviewer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fidelity PlanViewer 間のシングル サインオンを構成する方法について説明します。

この記事では、Fidelity PlanViewer と Microsoft Entra ID を統合する方法について説明します。 Fidelity PlanViewer を Microsoft Entra ID と統合すると、次のことができます。

- Fidelity PlanViewer にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Fidelity PlanViewer に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Fidelity PlanViewer でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fidelity PlanViewer では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Fidelity PlanViewer の追加

Microsoft Entra ID への Fidelity PlanViewer の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Fidelity PlanViewer を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業用アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Fidelity PlanViewer**」と入力します。
4. 結果パネルから **Fidelity PlanViewer** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fidelity PlanViewer に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Fidelity PlanViewer に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Fidelity PlanViewer の関連ユーザーとの間にリンク関係を確立する必要があります。

Fidelity PlanViewer に対して Microsoft Entra SSO を構成してテストするには、次のステップを実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fidelity PlanViewer の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Fidelity PlanViewer のテスト ユーザーの作成** - Fidelity PlanViewer で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザーをリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Fidelity PlanViewer]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、値を入力します。 `sp.fidelityworldwideinvestments.com`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://sso.sp.fidelity.co.uk/sp/ACS.saml2`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://cat-idr560.fidelity.co.uk/planviewer/jsp/home.jsp`
6. Fidelity PlanViewer アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: Fidelity PlanViewer アプリケーション属性の画像を示すスクリーンショット。]
7. その他に、Fidelity PlanViewer アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | LAST\_NAME | ユーザーの名字 |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Fidelity PlanViewer のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 設定に適したURLをコピーするためのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fidelity PlanViewer の SSO の構成

**Fidelity PlanViewer** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Fidelity PlanViewer サポート チーム](mailto:service.delivery@fil.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Fidelity PlanViewer のテスト ユーザーの作成

このセクションでは、Fidelity PlanViewer で Britta Simon というユーザーを作成します。 [Fidelity PlanViewer サポート チーム](mailto:service.delivery@fil.com)と協力して、Fidelity PlanViewer プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Fidelity PlanViewer のサインオン URL にリダイレクトされます。
- Fidelity PlanViewer のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Fidelity PlanViewer] タイルを選択すると、Fidelity PlanViewer のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/field-id-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Field iD を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/field-id-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Field iD 間にシングル サインオンを構成する方法について説明します。

この記事では、Field iD と Microsoft Entra ID を統合する方法について説明します。 Field iD と Microsoft Entra ID を統合すると、次のことができます:

- Field iD にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Field iD に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Field iD でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Field iD では、IDP Initiated SSO がサポートされます。

### ギャラリーからの Field iD の追加

Microsoft Entra ID への Field iD の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Field iD を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Field iD**」と入力します。
4. 結果パネルから **[フィールド iD]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Field iD 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Field iD に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Field iD の関連ユーザーとの間にリンク関係を確立する必要があります。

Field iD に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。
    1. B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft Entra テスト ユーザーを作成します。
    2. B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます。
2. Field iD SSO を構成して、アプリケーション側でシングル サインオン設定を構成します。
    1. Field iD のテストユーザーを作成し、Field iD 内で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Field iD** アプリケーション統合ページに移動し、[**管理**] セクションを見つけます。 **シングル サインオン**を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [SAML を使用した単一 Sign-On のセットアップ] ページのスクリーンショット。鉛筆アイコンが強調表示されています]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア。 [ **識別子** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `https://<tenantname>.fieldid.com/fieldid`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `https://<tenantname>.fieldid.com/fieldid/saml/SSO/alias/<Tenant Name>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Field iD サポート チーム](mailto:support@ecompliance.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、コピー アイコンを選択して **アプリのフェデレーション メタデータ URL をコピーします**。 それを自分のコンピューターに保存します。

    [Image: コピー アイコンが強調表示されている SAML 署名証明書のスクリーンショット]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Field iD SSO の構成

Field iD 側でシングル サインオンを構成するには、 **アプリのフェデレーション メタデータ URL を**[Field iD サポート チーム](mailto:support@ecompliance.com)に送信します。 SAML SSO 接続が両方の側で正しく設定されていることを確認します。

#### Field iD のテスト ユーザーの作成

このセクションでは、Field iD で Britta Simon というユーザーを作成します。 [Field iD サポート チーム](mailto:support@ecompliance.com)と協力して、Field iD プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Field iD に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Field iD] タイルを選択すると、SSO を設定した Field iD に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fieldglass-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SAP Fieldglass を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fieldglass-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と SAP Fieldglass の間でシングル サインオンを構成する方法について説明します。

この記事では、SAP Fieldglass と Microsoft Entra ID を統合する方法について説明します。 Fieldglass を Microsoft Entra ID と統合すると、次のことが可能になります。

- シングル サインオンを使用して Fieldglass にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントで Fieldglass に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fieldglass の実装は、シングル サインオン (SSO) 用に構成する準備ができました。 詳細については、「 [SAP Fieldglass Single-Sign on (SSO) 構成ガイド」を](https://help.sap.com/doc/eb7e719be14d4e3c9a4802a73f9b2f52/cloud/en-US/SAPFieldglassSSOConfigurationGuide.pdf)参照してください。

### シナリオの説明

運用環境の展開でシングル サインオンを構成する前に、テスト環境で Microsoft Entra のシングル サインオンを構成してテストすることをお勧めします。

- Fieldglass とのこの統合では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Fieldglass の追加

Fieldglass と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に Fieldglass をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Fieldglass**」と入力します。
4. 結果のパネルから **[Fieldglass]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fieldglass 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Fieldglass 用の Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、Fieldglass での関連ユーザーとの間にリンク関係を確立する必要があります。

Fieldglass 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fieldglass の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fieldglass のテスト ユーザーの作成 - Fieldglass** で B.Simon に対応するユーザーを作成し、そのユーザーの Microsoft Entra 表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Fieldglass]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. **[識別子]** ボックスに、「`https://www.fieldglass.com`」と入力するか、`https://<company name>.fgvms.com` 形式の URL を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://www.fieldglass.net/<company name>` |
    | `https://<company name>.fgvms.com/<company name>` |
    |  |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Fieldglass クライアント サポート チーム](https://www.fieldglass.com/customer-support)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Fieldglass のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fieldglass の SSO の構成

**Fieldglass** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を、[Fieldglass サポート チーム](https://www.fieldglass.com/customer-support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Fieldglass のテスト ユーザーの作成

このセクションでは、Fieldglass で Britta Simon というユーザーを作成します。 必要に応じて、 [Fieldglass サポート チーム](https://www.fieldglass.com/customer-support) と協力してユーザーの一意識別子を生成し、Fieldglass プラットフォームでユーザーを作成できるようにします。 シングル サインオンを使用する前に、Fieldglass でユーザーを作成する必要があります。

注

Fieldglass では、Fieldglass のユーザー識別子の一意性を確保するために、SAML アサーションの Fieldglass に送信されるユーザー識別子が Microsoft Entra の一般的なユーザー アカウント名と異なる必要がある場合があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fieldglass に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Fieldglass] タイルを選択すると、SSO を設定した Fieldglass に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/figbytes-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に FigBytes を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/figbytes-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FigBytes の間のシングル サインオンを構成する方法について説明します。

この記事では、FigBytes と Microsoft Entra ID を統合する方法について説明します。 FigBytes を Microsoft Entra ID と統合すると、次のことが可能になります。

- FigBytes にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで FigBytes に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な FigBytes のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FigBytes では、**SP**開始SSOと**IDP**開始SSOがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの FigBytes の追加

Microsoft Entra ID への FigBytes の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FigBytes を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「FigBytes**」と入力します。
4. 結果パネルから **FigBytes** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FigBytes 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、FigBytes に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと FigBytes の関連ユーザーとの間にリンク関係を確立する必要があります。

FigBytes 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FigBytes SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FigBytes のテスト ユーザーを作成する** - FigBytes で B.Simon に相当するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**FigBytes**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://figbytes.biz/`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **FigBytes のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FigBytes SSO の構成

**FigBytes** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [FigBytes サポート チーム](mailto:support@figbytes.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FigBytes のテスト ユーザーの作成

このセクションでは、FigBytes で Britta Simon というユーザーを作成します。 [FigBytes サポート チーム](mailto:support@figbytes.com)と協力して、FigBytes プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる FigBytes のサインオン URL にリダイレクトされます。
- FigBytes のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した FigBytes に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [FigBytes] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した FigBytes に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/figma-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Figma を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/figma-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: ユーザー アカウントを Figma に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Figma に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するために Figma とMicrosoft Entra IDで実行する手順を示すことです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Figma でユーザーを作成します。
- アクセスが不要になった場合は、Figma のユーザーを削除します。
- Figma に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/figma-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- [Figma テナント](https://www.figma.com/pricing/)。
- 管理者アクセス許可がある Figma のユーザー アカウント。

### ユーザーをアプリに割り当てる

Microsoft Entra IDでは、割り当てと呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Figma へのアクセスが必要なMicrosoft Entra IDのユーザーやグループを決定する必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Figma に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Figma に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを Figma に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Figma にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 既定のアクセス ロールのユーザーは、プロビジョニングから除外されます。

### Figma をプロビジョニング用に設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Figma を構成する前に、Figma からプロビジョニング情報を取得する必要があります。

1. [Figma 管理コンソール](https://www.Figma.com/)にサインインします。 お使いのテナントの横にある歯車アイコンを選択します。

    [Image: Figma 管理コンソールのスクリーンショット。A A D Scim Test という名前のテナントが表示されています。テナントの横にある歯車アイコンが強調表示されています。]
2. **[General](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般) &gt; [Update Log in Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログイン設定の更新)** に移動します。

    [Image: Figma 管理コンソール上の [General](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般) タブのスクリーンショット。[Log in and provisioning](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログインとプロビジョニング) の下にある [Update log in settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログイン設定の更新) が強調表示されています。]
3. **テナント ID** をコピーします。 この値は、Figma アプリケーションの [プロビジョニング] タブの **[テナント URL** ] フィールドに入力する SCIM エンドポイント URL を構築するために使用されます。

    [Image: Figma 管理コンソールの S A M L S S O セクションのスクリーンショット。[Tenant ID](テナント ID) ラベルと、その横にある [Copy](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/コピー) というリンクが強調表示されています。]
4. 下にスクロールし、[ **API トークンの生成**] を選択します。

    [Image: Figma 管理コンソールの S C I M プロビジョニング セクションのスクリーンショット。[Generate A P I token](A P I トークンの生成) というラベルが付いたリンクが強調表示されています。]
5. **[API Token](API トークン)** 値をコピーします。 この値は、Figma アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Figma 管理コンソールのページのスクリーンショット。[Your provisioning A P I token](あなたのプロビジョニング A P I トークン) の下にあるトークンのプレースホルダーが強調表示されています。]

### ギャラリーから Figma を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Figma を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Figma を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Figma**」と入力し、**Figma** を選択します。
4. 結果のパネルから **[Figma]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の Figma のスクリーンショット。]

### Figma への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Figma のユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Figma のシングル サインオンに関する記事で説明されている手順に従って、Figma に対して SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/figma-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra IDで Figma の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Figma]** を選択します。

    [Image: アプリケーションの一覧の Figma リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [自動] オプションが強調表示された [プロビジョニング モード] ドロップダウン リストのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Figma テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Figma に接続できることを確認します。 接続に失敗した場合は、Figma アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute Mapping** セクションで、Microsoft Entra IDから Figma に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Figma のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Figma ユーザー属性のスクリーンショット。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「[自動ユーザー アカウント プロビジョニングに関するレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/figma-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Figma を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/figma-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Figma 間のシングル サインオンを構成する方法について説明します。

この記事では、Figma と Microsoft Entra ID を統合する方法について説明します。 Figma を Microsoft Entra ID と統合すると、次のことが可能になります。

- Figma にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Figma に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Figma でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Figma では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Figma では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/figma-provisioning-tutorial) (推奨) がサポートされます。
- Figma では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Figma を追加する

Microsoft Entra ID への Figma の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Figma を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Figma**」と入力します。
4. 結果のパネルから **[Figma]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Figma 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Figma に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する Figma ユーザーをリンクする必要があります。

Figma に対する Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Figma SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Figmaテストユーザーを作成する** - FigmaでB.Simonの対応役を作成し、それをMicrosoft Entraのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Figma**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.figma.com/saml/<TENANT ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.figma.com/saml/<TENANT ID>/consume`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.figma.com/saml/<TENANT ID>/start`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 Figma の記事「`TENANT ID`」の手順 11 からを取得します。
7. Figma アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Figma アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | `externalId` | `user.mailnickname` |
    | `displayName` | `user.displayname` |
    | `title` | `user.jobtitle` |
    | `emailaddress` | `user.mail` |
    | `familyName` | `user.surname` |
    | `givenName` | `givenName` |
    | `userName` | `user.userprincipalname` |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Figma の SSO の構成

Figma 側でシングル サインオンを構成するには、Figma の記事「[Configure Microsoft Entra SAML SSO process (Microsoft Entra SAML SSO プロセスを構成する)](https://help.figma.com/hc/en-us/articles/360040532413-Configure-and-Provision-SAML-SSO-with-Azure-Active-Directory)」に従う必要があります。

#### Figma のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Figma に作成します。 Figma では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Figma に存在しない場合は、Figma にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Figma サインオン URL にリダイレクトされます。
- Figma のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Figma に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Figma] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Figma に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/filecloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に FileCloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/filecloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FileCloud の間にシングル サインオンを構成する方法について説明します。

この記事では、FileCloud と Microsoft Entra ID を統合する方法について説明します。 FileCloud を Microsoft Entra ID と統合すると、次のことができます。

- FileCloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って FileCloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FileCloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- FileCloud では、**SP** Initiated SSO がサポートされます。
- FileCloud では、**Just-In-Time** ユーザー プロビジョニングがサポーされます。

### ギャラリーからの FileCloud の追加

Microsoft Entra ID への FileCloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FileCloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**FileCloud**」と入力します。
4. 結果のパネルから **FileCloud** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FileCloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、FileCloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと FileCloud の関連ユーザーとの間にリンク関係を確立する必要があります。

FileCloud に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FileCloud SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FileCloud テスト ユーザーの作成** - Microsoft Entra でのユーザー表現にリンクされた B.Simon に対応する FileCloud ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**FileCloud**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.filecloudonline.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.filecloudonline.com/simplesaml/module.php/saml/sp/metadata.php/default-sp`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[FileCloud クライアント サポート チーム](mailto:support@codelathe.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[FileCloud のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FileCloud SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として FileCloud テナントにサインオンします。
2. 左側のナビゲーション ウィンドウで、**[設定]** を選択します。

    [Image: 左側のナビゲーション ペインで強調表示されている [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) を示すスクリーンショット。]
3. [設定] セクションの [ **SSO** ] タブを選択します。

    [Image: [S S O] タブが選択されている [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) セクションを示すスクリーンショット。]
4. **[Single Sign On (SSO) Settings (シングル サインオン (SSO) 設定)]** パネルで、 **[Default SSO Type (既定の SSO タイプ)]** として **[SAML]** を選択します。

    [Image: [S A M L] が選択されている [Single Sign On (S S O) Settings](シングル サインオン (S S O) 設定) パネルを示すスクリーンショット。]
5. **[IdP End Point URL] (IdP エンド ポイント URL)** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    [Image: [I d P End Point U R L](I d P のエンド ポイント U R L) が強調表示されている [S A M L Settings](S A M L 設定) セクションを示すスクリーンショット。]
6. ダウンロードしたメタデータ ファイルをメモ帳で開き、その内容をクリップボードにコピーし、 **[SAML Settings (SAML 設定)]** パネルの **[IdP Meta Data (IdP メタ データ)]** ボックスに貼りつけます。

    [Image: アプリ側でのシングル サインオンの構成]
7. [ **保存] ボタンを** 選択します。

#### FileCloud のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを FileCloud に作成します。 FileCloud では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 FileCloud にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[FileCloud クライアント サポート チーム](mailto:support@codelathe.com)に問い合わせてください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる FileCloud のサインオン URL にリダイレクトされます。
- FileCloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [FileCloud] タイルを選択すると、このオプションは FileCloud のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fileorbis-tutorial"} -->
## Microsoft Entra ID で FileOrbis for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fileorbis-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FileOrbis の間のシングル サインオンを構成する方法について説明します。

この記事では、FileOrbis と Microsoft Entra ID を統合する方法について説明します。 FileOrbis を Microsoft Entra ID と統合すると、次のことが可能になります。

- FileOrbis にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで FileOrbis に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な FileOrbis のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FileOrbis では、 **SP** によって開始される SSO がサポートされます。
- FileOrbis では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの FileOrbis の追加

Microsoft Entra ID への FileOrbis の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FileOrbis を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「FileOrbis**」と入力します。
4. 結果パネルから **FileOrbis** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FileOrbis 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、FileOrbis に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと FileOrbis の関連ユーザーとの間にリンク関係を確立する必要があります。

FileOrbis 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FileOrbis SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FileOrbis のテスト ユーザーの作成** - FileOrbis で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**FileOrbis**&gt;**シングルサインオン**をブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<ApplicationURL>/portal`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ApplicationURL>/portal/Account/LoginSAMLConsume`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ApplicationURL>/portal`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、FileOrbis クライアント サポート チーム](mailto:support@fileorbis.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **FileOrbis のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FileOrbis SSO の構成

**FileOrbis** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [FileOrbis サポート チーム](mailto:support@fileorbis.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FileOrbis のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを FileOrbis に作成します。 FileOrbis では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 FileOrbis にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる FileOrbis のサインオン URL にリダイレクトされます。
- FileOrbis のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [FileOrbis] タイルを選択すると、このオプションは FileOrbis のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/filesanywhere-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に FilesAnywhere を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/filesanywhere-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FilesAnywhere の間のシングル サインオンを構成する方法について説明します。

この記事では、FilesAnywhere と Microsoft Entra ID を統合する方法について説明します。 FilesAnywhere と Microsoft Entra ID を統合すると、次の利点がもたらされます。

- FilesAnywhere にアクセスできるユーザーを Microsoft Entra ID で制御できる。
- ユーザーが自分の Microsoft Entra アカウントで FilesAnywhere に自動的にサインイン (シングル サインオン) できるようになる。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FilesAnywhere でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- FilesAnywhere では、**SP** 開始の SSO と **IDP** 開始の SSO がサポートされています。
- FilesAnywhere では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの FilesAnywhere の追加

Microsoft Entra ID への FilesAnywhere の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FilesAnywhere を追加する必要があります。

**ギャラリーから FilesAnywhere を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **FilesAnywhere」**と入力し、結果パネルで **FilesAnywhere** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の FilesAnywhere]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、FilesAnywhere で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと FilesAnywhere での関連ユーザーとの間にリンク関係を確立する必要があります。

FilesAnywhere で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **FilesAnywhere シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **FilesAnywhere テストユーザーの作成** - Microsoft Entra のユーザーである Britta Simon にリンクした FilesAnywhere における対応するユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

FilesAnywhere で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**FilesAnywhere** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: [Reply U R L](応答 URL) フィールドが強調表示され、[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) ボタンが選択されている [Basic S A M L Configuration](基本的な S A M L 構成) セクションを示すスクリーンショット。]

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.filesanywhere.com/saml20.aspx?c=<Client Id>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [Image: [FilesAnywhere のドメインと URL] のシングル サインオン情報]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<sub domain>.filesanywhere.com/`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、 [FilesAnywhere クライアント サポート チーム](mailto:support@FilesAnywhere.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. FilesAnywhere アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [編集] アイコンを選択して属性を追加します。

    [Image: [編集] ボタンが選択されている [ユーザー属性] セクションを示すスクリーンショット。]

    ユーザーが FilesAnywhere にサインアップすると、**FilesAnywhere チーム**から [clientid](mailto:support@FilesAnywhere.com) 属性の値が取得されます。 "クライアント ID" 属性を FilesAnywhere によって提供される一意の値で追加する必要があります。
8. その他に、FilesAnywhere アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | clientid | *"uniquevalue"* |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] と [保存] が選択された [ユーザー要求] ダイアログを示すスクリーンショット。]

    [Image: 画像]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. [ソース] を **[属性**] として選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. [ **OK] を選択する**

    g. **[保存] を選択します**。
9. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **FilesAnywhere のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### FilesAnywhere のシングル サインオンの構成

**FilesAnywhere** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [FilesAnywhere サポート チーム](mailto:support@FilesAnywhere.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### FilesAnywhere のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを FilesAnywhere に作成します。 FilesAnywhere では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 FilesAnywhere にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [FilesAnywhere] タイルを選択すると、SSO を設定した FilesAnywhere に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/finvari-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Finvari を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/finvari-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Finvari の間のシングル サインオンを構成する方法について説明します。

この記事では、Finvari と Microsoft Entra ID を統合する方法について説明します。 Finvari を Microsoft Entra ID と統合すると、次のことが可能になります。

- Finvari にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Finvari に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Finvari でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Finvari では、 **SP** Initiated SSO がサポートされます。
- Finvari では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Finvari の追加

Microsoft Entra ID への Finvari の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Finvari を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Finvari**」と入力します。
4. 結果パネルから **Finvari** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Finvari 向けに Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Finvari に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Finvari の関連ユーザーとの間にリンク関係を確立する必要があります。

Finvari に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Finvari SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Finvari テスト ユーザーの作成** - Finvari での B.Simon の対応として、Microsoft Entra のユーザー表現にリンクされているテストユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Finvari]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://us.finvari.com/<CUSTOMER>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://us.finvari.com/<CUSTOMER>/auth/handler`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://us.finvari.com/?program=<CUSTOMER>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Finvari クライアント サポート チーム](mailto:support@finvari.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Finvari のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Finvari SSO の構成

**Finvari** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Finvari サポート チーム](mailto:support@finvari.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Finvari のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Finvari に作成します。 Finvari では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Finvari にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Finvari サインオン URL にリダイレクトされます。
- Finvari のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Finvari] タイルを選択すると、このオプションは Finvari のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/firmex-vdr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Firmex VDR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/firmex-vdr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Firmex VDR の間のシングル サインオンを構成する方法について説明します。

この記事では、Firmex VDR と Microsoft Entra ID を統合する方法について説明します。 Firmex VDR を Microsoft Entra ID と統合すると、次のことが可能になります。

- Firmex VDR にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Firmex VDR に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Firmex VDR でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Firmex VDR では、**SP および IDP** Initiated SSO がサポートされます。

### ギャラリーから Firmex VDR を追加する

Microsoft Entra ID への Firmex VDR の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Firmex VDR を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Firmex VDR**」と入力します。
4. 結果のパネルから **[Firmex VDR]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Firmex VDR に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Firmex VDR に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Firmex VDR の関連ユーザーとの間にリンク関係を確立する必要があります。

Firmex VDR に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Firmex VDR の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Firmex VDR のテスト ユーザーの作成** - Firmex VDR で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Firmex VDR**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンのセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://login.firmex.com`
7. **保存** を選択します。
8. Firmex VDR アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Firmex VDR アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
10. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Firmex VDR のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Firmex VDR SSO の構成

#### 作業を開始する前に

##### 必要なもの

- アクティブな Firmex サブスクリプション。
- SSO サービスとしての Microsoft Entra ID。
- SSO を構成する IT 管理者。
- SSO が有効になったら、自社のすべてのユーザーは、ログインとパスワードではなく SSO を使用して Firmex にログインする必要があります。

##### 所要時間

SSO の実装には数分かかります。 Firmex サポートがサイトの SSO を有効にすることと、SSO を使用して会社のユーザーが認証を行う間に、実質的にダウンタイムはありません。 以下の手順に従ってください。

#### 手順 1 - 自社のドメインを特定する

自社のユーザーがログインに使用するドメインを特定します。

例えば次が挙げられます。

- @firmex.com。
- @firmex.ca。

#### 手順 2 - ドメインを Firmex サポートに連絡する

[Firmex サポート チーム](mailto:support@firmex.com)にメールを送信するか、1888 688 4042 x.11 に電話して、Firmex サポートと連絡を取ります。 ご自分のドメイン情報を伝えてください。 Firmex サポートによって、**要求されたドメイン**として、ご使用の VDR にドメインが追加されます。 これで、管理者が SSO を構成できます。

警告: サイト管理者が要求されたドメインを構成するまで、会社のユーザーは VDR にログインできません。 会社以外のユーザー (つまり、ゲスト ユーザー) は、引き続きメールとパスワードを使用してログインできます。 構成には数分かかります。

#### 手順 3 - 要求されたドメインを構成する

1. サイト管理者として Firmex にログインします。
2. 左上隅から、会社のロゴを選択します。
3. **[SSO]** タブを選択します。次に、**[SSO 構成]** を選択します。 構成するドメインを選択します。

    [Image: 要求されたドメイン]
4. 次のフィールドの入力を行うよう IT 管理者に依頼します。 フィールドに入力する内容は、ID プロバイダーから取得する必要があります。

    [Image: [SSO Configuration]]

    a. [ **エンティティ ID** ] ボックスに、先にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    b。 **[ID プロバイダー URL]** テキスト ボックスに、前にコピーした**ログイン URL** 値を貼り付けます。

    c. **[Public Key Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/公開キー証明書)** - SAML メッセージは、認証を目的として発行者によってデジタル署名される場合があります。 メッセージの署名を検証するために、メッセージの受信者は、発行者のものだとわかっている公開キーを使用します。 同様に、メッセージを暗号化するには、最終的な受信者のものである公開暗号化キーを発行者が知っている必要があります。 署名と暗号化のどちらの場面でも、信頼された公開キーを事前に共有する必要があります。 これは、**フェデレーション メタデータ XML** からの **X509Certificate** です

    d. [ **保存] を** 選択して SSO 構成を完了します。 変更はすぐに有効になります。
5. この時点で、サイトに対して SSO が有効になっています。

#### Firmex VDR テスト ユーザーの作成

このセクションでは、Firmex で B.Simon というユーザーを作成します。 [Firmex サポート チーム](mailto:support@firmex.com)と協力して、Firmex プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Firmex VDR サインオン URL にリダイレクトされます。
- Firmex VDR のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Firmex VDR に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Firmex VDR] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Firmex VDR に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/firmplay-tutorial"} -->
## Microsoft Entra ID でシングルサインオンを用いて、FirmPlay - Employee Advocacy for Recruiting を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/firmplay-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FirmPlay - Employee Advocacy for Recruiting の間でシングル サインオンを構成する方法について説明します。

この記事では、FirmPlay - Employee Advocacy for Recruiting と Microsoft Entra ID を統合する方法について説明します。 FirmPlay - Employee Advocacy for Recruiting を Microsoft Entra ID と統合すると、次のことが可能になります。

- FirmPlay - Employee Advocacy for Recruiting にアクセスできるユーザーを Microsoft Entra ID で制御。
- ユーザーが自分の Microsoft Entra アカウントを使用して FirmPlay - Employee Advocacy for Recruiting に自動的にサインインできる。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FirmPlay - Employee Advocacy for Recruiting でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- FirmPlay - Employee Advocacy for Recruiting では、**SP** initiated SSO がサポートされます。

### ギャラリーからの FirmPlay - Employee Advocacy for Recruiting の追加

Microsoft Entra ID への FirmPlay - Employee Advocacy for Recruiting の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FirmPlay - Employee Advocacy for Recruiting を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **ギャラリーから追加］ セクションで**、検索ボックスに **「FirmPlay - Employee Advocacy for Recruiting**」 と入力します。
4. 結果パネルから **FirmPlay - Employee Advocacy for Recruiting** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FirmPlay - Employee Advocacy for Recruiting ための Microsoft Entra SSO 構成とテスト

**B.Simon** というテスト ユーザーを使用して FirmPlay - Employee Advocacy for Recruiting で Microsoft Entra SSO 構成とテストをします。 SSO を機能させるには、Microsoft Entra ユーザーと関連ユーザーとの間に FirmPlay - Employee Advocacy for Recruiting でリンク関係を確立する必要があります。

FirmPlay - Employee Advocacy for Recruiting で Microsoft Entra SSO を構成、テストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FirmPlay - Employee Advocacy for Recruiting SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FirmPlay - Employee Advocacy for Recruiting テストユーザーの作成** - Microsoft Entra に関連付けられた Britta Simon に対応するユーザーを FirmPlay - Employee Advocacy for Recruiting に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[FirmPlay - Employee Advocacy for Recruiting]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] 画面を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<your-subdomain>.firmplay.com/`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[FirmPlay - Employee Advocacy for Recruiting サポート チーム](mailto:engineering@firmplay.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[FirmPlay - Employee Advocacy for Recruiting のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FirmPlay - Employee Advocacy for Recruiting SSO の構成

**FirmPlay - Employee Advocacy for Recruiting** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [FirmPlay - Employee Advocacy for Recruiting サポート チーム](mailto:engineering@firmplay.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FirmPlay - Employee Advocacy for Recruiting のテスト ユーザーの作成

このセクションでは、FirmPlay - Employee Advocacy for Recruiting で Britta Simon というユーザーを作成します。 [FirmPlay - Employee Advocacy for Recruiting サポート チーム](mailto:engineering@firmplay.com)と連携して、FirmPlay - Employee Advocacy for Recruiting プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる FirmPlay - Employee Advocacy for Recruiting のサインオン URL にリダイレクトされます。
- FirmPlay - FirmPlay - Employee Advocacy for Recruiting サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [FirmPlay - Employee Advocacy for Recruiting] タイルを選択すると、このオプションは FirmPlay - Employee Advocacy for Recruiting のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fiscalnote-tutorial"} -->
## Microsoft Entra ID を使用して FiscalNote for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fiscalnote-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FiscalNote の間のシングル サインオンを構成する方法について説明します。

この記事では、FiscalNote と Microsoft Entra ID を統合する方法について説明します。 FiscalNote を Microsoft Entra ID と統合すると、次のことが可能になります。

- FiscalNote にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで FiscalNote に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FiscalNote (SSO) でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FiscalNote では、 **SP** Initiated SSO がサポートされます
- FiscalNote では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの FiscalNote の追加

Microsoft Entra ID への FiscalNote の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FiscalNote を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「FiscalNote**」と入力します。
4. 結果パネルから **FiscalNote** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FiscalNote 向けに Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、FiscalNote に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと FiscalNote の関連ユーザーとの間にリンク関係を確立する必要があります。

FiscalNote の Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FiscalNote SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FiscalNote テストユーザーの作成** - FiscalNote において B.Simon の対応ユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**&gt;**FiscalNote**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<InstanceName>.fiscalnote.com/login?client=<ClientID>&redirect_uri=https://app.fiscalnote.com/saml-login.html&audience=https://api.fiscalnote.com/&connection=<CONNECTION_NAME>&response_type=id_token%20token`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:auth0:fiscalnote:<CONNECTIONNAME>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [FiscalNote クライアント サポート チーム](mailto:support@fiscalnote.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. FiscalNote アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、FiscalNote アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | familyName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **FiscalNote のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FiscalNote の SSO の構成

**FiscalNote** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [FiscalNote サポート チーム](mailto:support@fiscalnote.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FiscalNote のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを FiscalNote に作成します。 FiscalNote では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 FiscalNote にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [FiscalNote サポート チーム](mailto:support@fiscalnote.com)にお問い合わせください。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [FiscalNote] タイルを選択すると、SSO を設定した FiscalNote に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/five9-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Five9 Plus Adapter (CTI、Contact Center Agents) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/five9-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Five9 Plus Adapter (CTI、Contact Center Agents) の間でシングル サインオンを構成する方法について説明します。

この記事では、Five9 Plus Adapter (CTI、Contact Center Agents) と Microsoft Entra ID を統合する方法について説明します。 Five9 Plus Adapter (CTI、Contact Center Agents) を Microsoft Entra ID と統合すると、次のことができます。

- Five9 Plus Adapter (CTI、Contact Center Agents) にアクセスできるユーザーを Microsoft Entra ID で管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Five9 Plus Adapter (CTI、Contact Center Agents) に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Five9 Plus Adapter (CTI、Contact Center Agents) でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Five9 Plus Adapter (CTI、Contact Center Agents) では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Five9 Plus Adapter (CTI、Contact Center Agents) の追加

Microsoft Entra ID への Five9 Plus Adapter (CTI、Contact Center Agents) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Five9 Plus Adapter (CTI、Contact Center Agents) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **ギャラリーから追加セクションで**、検索ボックスに**「Five9 Plus Adapter (CTI,Contact Center Agents)」**と入力します。
4. 結果パネルから **Five9 Plus Adapter (CTI、Contact Center Agents) を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Five9 Plus Adapter (CTI、Contact Center Agents) の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Five9 Plus Adapter (CTI、Contact Center Agents) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Five9 Plus Adapter (CTI、Contact Center Agents) の関連ユーザーとの間にリンク関係を確立する必要があります。

Five9 Plus Adapter (CTI、Contact Center Agents) に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Five9 Plus Adapter (CTI、Contact Center Agents) の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Five9 Plus Adapter (CTI、Contact Center Agents) のテストユーザーを作成** - Microsoft Entra のユーザー表現とリンクするために、Five9 Plus Adapter (CTI、Contact Center Agents) で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Five9 Plus Adapter (CTI、Contact Center Agents)**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | "Five9 Plus Adapter for Microsoft Dynamics CRM" の場合 | `https://app.five9.com/appsvcs/saml/metadata/alias/msdc` |
    | "Five9 Plus Adapter for Zendesk" の場合 | `https://app.five9.com/appsvcs/saml/metadata/alias/zd` |
    | "Five9 Plus Adapter for Agent Desktop Toolkit" の場合 | `https://app.five9.com/appsvcs/saml/metadata/alias/adt` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | "Five9 Plus Adapter for Microsoft Dynamics CRM" の場合 | `https://app.five9.com/appsvcs/saml/SSO/alias/msdc` |
    | "Five9 Plus Adapter for Zendesk" の場合 | `https://app.five9.com/appsvcs/saml/SSO/alias/zd` |
    | "Five9 Plus Adapter for Agent Desktop Toolkit" の場合 | `https://app.five9.com/appsvcs/saml/SSO/alias/adt` |
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Five9 Plus Adapter (CTI、Contact Center Agents) のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Five9 Plus Adapter (CTI、Contact Center Agents) の SSO の構成

1. **Five9 Plus Adapter (CTI、Contact Center Agents)** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とコピーした適切な URL を [Five9 Plus Adapter (CTI、Contact Center Agents) サポート チーム](https://www.five9.com/about/contact)に送信する必要があります。 また、SSO をさらに構成するために、アダプターに従って以下の手順のようにしてください。

    a. "Five9 Plus Adapter for Agent Desktop Toolkit" 管理ガイド: https://webapps.five9.com/assets/files/for_customers/documentation/integrations/agent-desktop-toolkit/plus-agent-desktop-toolkit-administrators-guide.pdf

    b。 "Five9 Plus Adapter for Microsoft Dynamics CRM" 管理ガイド: https://manualzz.com/download/25793001

    c. "Five9 Plus Adapter for Zendesk" 管理ガイド: https://webapps.five9.com/assets/files/for_customers/documentation/integrations/zendesk/zendesk-plus-administrators-guide.pdf

#### Five9 Plus Adapter (CTI、Contact Center Agents) テスト ユーザーの作成

このセクションでは、Five9 Plus Adapter (CTI、Contact Center Agents) で Britta Simon というユーザーを作成します。 [Five9 Plus Adapter (CTI、Contact Center Agents) サポート チーム](https://www.five9.com/about/contact)と協力して、Five9 Plus Adapter (CTI、Contact Center Agents) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Five9 Plus Adapter (CTI、Contact Center Agents) に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Five9 Plus Adapter (CTI、Contact Center Agents) タイルを選択すると、SSO を設定した Five9 Plus Adapter (CTI、Contact Center Agents) に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fivetran-tutorial"} -->
## Microsoft Entra ID で Fivetran for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fivetran-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fivetran の間のシングル サインオンを構成する方法について説明します。

この記事では、Fivetran と Microsoft Entra ID を統合する方法について説明します。 Fivetran を Microsoft Entra ID と統合すると、次のことが可能になります。

- Fivetran にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Fivetran に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fivetran アカウント。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fivetran では、**IDP** によって開始される SSO がサポートされています。
- Fivetran では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Fivetran を追加する

Microsoft Entra ID への Fivetran の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Fivetran を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Fivetran**」と入力します。
4. 結果のパネルから **[Fivetran]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fivetran 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Fivetran に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Fivetran の関連ユーザーとの間にリンク関係を確立する必要があります。

Fivetran に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fivetran の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fivetran テストユーザーの作成** - Fivetran 内で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Fivetran**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Fivetran アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Fivetran アプリケーションでは、以下のような、いくつかの属性が SAML 応答で返されることが想定されています。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Set up Fivetran] (Fivetran の設定)** セクションで、 **[ログイン URL]** と **[Microsoft Entra 識別子]** の値をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fivetran の SSO を構成する

このセクションでは、 **Fivetran** 側でシングル サインオンを構成します。

1. 別の Web ブラウザーのウィンドウで、アカウント所有者として Fivetran アカウントにサインインします。
2. ウィンドウの左上隅にある矢印を選択し、ドロップダウン リストから **[Manage Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントを管理する)** を選択します。

    [Image: [Manage Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントを管理する) メニュー オプションが選択されている画面のスクリーンショット。]
3. **[設定]** ページの **[SAML Config](SAML 構成)** セクションに移動します。

    [Image: [SAML Config](SAML 構成) ウィンドウのスクリーンショット。構成オプションが強調表示されています。]

    1. **[SAML 認証を有効にする]** には **[オン]** を選択します。
    2. **[サインオン URL]** に、コピーした**ログイン URL** の値を貼り付けます。
    3. **[発行者]** に、コピーした **Microsoft Entra 識別子**の値を貼り付けます。
    4. ダウンロードした証明書ファイルをテキスト エディターで開き、証明書をクリップボードにコピーし、 **[公開証明書]** ボックスに貼り付けます。
    5. **[SAVE CONFIG](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成の保存)** を選択します。

#### Fivetran のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Fivetran に作成します。 Fivetran では、Just-In-Time ユーザー プロビジョニングがサポートされており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 Fivetran にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fivetran に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Fivetran タイルを選択すると、SSO を設定した Fivetran に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/flatter-files-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Flatter Files を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flatter-files-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Flatter Files との間でシングル サインオンを構成する方法について説明します。

この記事では、Flatter Files と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に Flatter Files を統合すると次の利点が得られます。

- どのユーザーが Flatter Files にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して Flatter Files に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Flatter Files でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Flatter Files では、 **IDP** によって開始される SSO がサポートされます

### ギャラリーからの Flatter Files の追加

Microsoft Entra ID への Flatter Files の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Flatter Files を追加する必要があります。

**ギャラリーから Flatter Files を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. 検索ボックスに「 **Flatter Files」**と入力し、結果パネルで **Flatter Files** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Flatter Files]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Flatter Files で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Flatter Files の関連ユーザーとの間にリンク関係を確立する必要があります。

Flatter Files で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Flatter Files のシングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Flatter Files テストユーザーの作成** - Flatter Files で Britta Simon に相当するテストユーザーを作成し、それを Microsoft Entra のそのユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Flatter Files で Microsoft Entra のシングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Flatter Files** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。

    [Image: Flatter Files のドメインとURL用シングルサインオン情報]
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Flatter Files のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Flatter Files のシングル サインオンの構成

1. 管理者として Flatter Files アプリケーションにサインオンします。
2. **[ダッシュボード**] を選択します。

3. **[設定]** を選択し、[**会社**] タブで次の手順を実行します。

    [Image: [Use S A M L 2.0 for Authentication](認証に S A M L 2.0 を使用する) がオンで、[Configure S A M L](S A M L の構成) ボタンが選択されている [Company] タブを示すスクリーンショット。]

    1. [ **認証に SAML 2.0 を使用する] を**選択します。
    2. [ **SAML の構成] を選択します**。
4. [ **SAML 構成** ] ダイアログで、次の手順を実行します。

    [Image: シングル サインオンの構成]

    a. [ **ドメイン** ] ボックスに、登録済みのドメインを入力します。

    注

    登録済みのドメインがない場合は、Flatter Files のサポート チーム ( support@flatterfiles.com」を参照してください。

    b。 **[Identity Provider URL**] ボックスに、Azure portal からコピーした**ログイン URL** の値を貼り付けます。

    c. Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、[ **ID プロバイダー証明書** ] ボックスに貼り付けます。

    d. [ **更新] を**選択します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Flatter Files テスト ユーザーの作成

このセクションの目的は、Flatter Files で Britta Simon というユーザーを作成することです。

**Flatter Files で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. **Flatter Files** 企業サイトに管理者としてサインオンします。
2. 左側のナビゲーション ウィンドウで、[ **設定]** を選択し、[ **ユーザー** ] タブを選択します。

    [Image: [ユーザー] タブが選択されている [設定] ページを示すスクリーンショット。]
3. [ **ユーザーの追加] を選択します**。
4. [ **ユーザーの追加** ] ダイアログで、次の手順を実行します。

    [Image: Flatter Files ユーザーの作成]

    a. [ **名** ] ボックスに「 **Britta**」と入力します。

    b。 [ **姓** ] ボックスに「 **Simon**」と入力します。

    c. [ **電子メール アドレス]** ボックスに、Britta のメール アドレスを入力します。

    d. [ **送信] を選択します**。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Flatter Files] タイルを選択すると、SSO を設定した Flatter Files に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fleet-management-system-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Fleet Management System を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fleet-management-system-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-20
- Summary: Microsoft Entra ID と Fleet Management System の間でシングル サインオンを構成する方法について説明します。

この記事では、Fleet Management System と Microsoft Entra ID を統合する方法について説明します。 Microsoft が利用する地上レベルの車両と地下のボートとカートのフリートを管理および監視します。 Fleet Management System と Microsoft Entra ID を統合すると、次のことができます。

- Fleet Management System にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Fleet Management System に自動的にサインインできるようにすることができます。
- アカウントを一元的に管理する。

テスト環境で Fleet Management System 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Fleet Management System では、**IDP** によって開始されるシングル サインオンがサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### 前提条件

Microsoft Entra ID を Fleet Management System と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Fleet Management System のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Fleet Management System アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Fleet Management System を追加する

Microsoft Entra アプリケーション ギャラリーから Fleet Management System を追加して、Fleet Management System でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、[クイック スタート: ギャラリーからのアプリケーションの追加](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)に関する記事を参照してください。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[ユーザー アカウントの作成と割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 ウィザードは、シングル サインオン構成ウィンドウへのリンクも提供します。 [Microsoft 365 ウィザードの詳細をご覧ください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO を構成する

Microsoft Entra のシングル サインオンを有効にするには、以下の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Fleet Management System**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://msfms.net/SAMLFms` |
    | ステージング | `https://test.msfms.net/SAMLFms` |

    b。 **[応答 URL]** ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://msfms.net/saml2/acs` |
    | ステージング | `https://test.msfms.net/saml2/acs` |
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Fleet Management System の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーすることを示すスクリーンショット。]

### Fleet Management System SSO の構成

**Fleet Management System** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Fleet Management System サポート チーム](mailto:msfms-support@navagis.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Fleet Management System のテスト ユーザーを作成する

このセクションでは、Fleet Management System で Britta Simon というユーザーを作成します。 [Fleet Management System サポート チーム](mailto:msfms-support@navagis.com)と連携して、Fleet Management System プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fleet Management System に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Fleet Management System] タイルを選択すると、SSO を設定した Fleet Management System に自動的にサインインします。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/flexera-one-tutorial"} -->
## Microsoft Entra ID で Flexera One for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flexera-one-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Flexera One の間のシングル サインオンを構成する方法について説明します。

この記事では、Flexera One と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Flexera One を統合すると、次のことができます。

- Flexera One にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Flexera One に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Flexera One は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Flexera One サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Flexera One では、**SP と IDP** によって開始される SSO がサポートされます。
- Flexera One では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーから Flexera One を追加する

Microsoft Entra ID への Flexera One の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Flexera One を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Flexera One**」と入力します。
4. 結果のパネルから **[Flexera One]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Flexera On に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Flexera One 向けに Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Flexera One の関連ユーザーとの間にリンク関係を確立する必要があります。

Flexera One に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Flexera One SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Flexera One のテストユーザーを作成** - Flexera One 内で B.Simon に相当するユーザーを作成し、Microsoft Entra のユーザー表示にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Flexera One]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://secure.flexera.com/sso/saml2/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://secure.flexera.com/sso/saml2/<ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://secure.flexera.com/sso/saml2/<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Flexera One クライアント サポート チーム](mailto:support@flexera.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Flexera One アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: Flexera One アプリケーションの画像を示すスクリーンショット。]
7. その他に、Flexera One アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Flexera One のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: のスクリーンショットは、構成に適した U R L をコピーする手順を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Flexera One SSO を構成する

**Flexera One** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Flexera One サポート チーム](mailto:support@flexera.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。 方法については、[こちら](https://docs.flexera.com/flexera/EN/Administration/AzureADSSO.htm)をご覧ください。

#### Flexera One テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Flexera One に作成します。 Flexera One では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Flexera One にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Flexera One のサインオン URL にリダイレクトされます。
- Flexera One のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Flexera One に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Flexera One] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Flexera One に自動的にサインインされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/flipsnack-saml-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Flipsnack SAML を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flipsnack-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Flipsnack SAML の間のシングル サインオンを構成する方法について説明します。

この記事では、Flipsnack SAML と Microsoft Entra ID を統合する方法について説明します。 Flipsnackはインタラクティブなカタログ、雑誌、パンフレットなどを作成するのに最適な完全なソリューションです。 PDF ファイルをフリップブックに変換するか、デザイン全体をゼロから作成します。 Microsoft Entra ID と Flipsnack SAML を統合すると、次のことが可能になります。

- Flipsnack SAML にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Flipsnack SAML に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Flipsnack SAML 向けに Microsoft Entra のシングル サインオンを構成してテストします。 Flipsnack SAML は、**SP** Initiated と **IDP** Initiated の両方のシングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と Flipsnack SAML を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Flipsnack SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Flipsnack SAML アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Flipsnack SAML を追加する

Microsoft Entra アプリケーション ギャラリーから Flipsnack SAML を追加して、Flipsnack SAML でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Flipsnack SAML**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://www.flipsnack.com`

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://auth.flipsnack.com/login/callback` |
    | `https://www.flipsnack.com/accounts/sign-in-sso.html` |
6. SP Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.flipsnack.com/accounts/sign-in-sso.html?accountId=<CustomerHash>`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 値を取得するには、[Flipsnack SAML クライアント サポート チーム](mailto:contact@flipsnack.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Set up Flipsnack SAML] (Flipsnack SAML のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Flipsnack SAML SSO を構成する

1. Flipsnack SAML 企業サイトに管理者としてログインします。
2. Flipsnack の **[Settings] (設定)**&gt;**[Single Sign On] (シングル サインオン)** に移動します。

    [Image: SSO 構成を示すスクリーンショット。]

    1. **[SSO]** を有効にし、**[SAML]** プロトコルを選択します。
    2. **[ログイン URL]** テキスト ボックスに、コピーした**ログイン URL** の値を貼り付けます。
    3. **[ID]** テキスト ボックスに、コピーした **Microsoft Entra 識別子**の値を貼り付けます。
    4. **[ログアウト URL]** テキスト ボックスに、コピーした**[ログイン URL]** の値を貼り付けます。
    5. ダウンロードした **証明書 (Base64)** をメモ帳に開き、**証明書** ボックスに内容を貼り付けます。
    6. **[変更の保存]** を選択します。

#### Flipsnack SAML テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Flipsnack SAML に作成します。 Flipsnack SAML では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Flipsnack SAML にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Flipsnack SAML サインオン URL にリダイレクトされます。
- Flipsnack SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Flipsnack SAML に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Flipsnack SAML] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Flipsnack SAML に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/float-tutorial"} -->
## Microsoft Entra ID で Float for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/float-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Float 間のシングル サインオンを構成する方法について説明します。

この記事では、Float と Microsoft Entra ID を統合する方法について説明します。 Float を Microsoft Entra ID と統合すると、次のことが可能になります。

- Float にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Float に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Float のサブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://app.float.com/join?)を取得できます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Float では、**SP開始SSO** および **IDP開始SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Float の追加

Microsoft Entra ID への Float の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Float を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Float」**と入力します。
4. 結果パネルから **[Float]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Float 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Float に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する Float ユーザーをリンクする必要があります。

Float に対する Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Float SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Float テスト ユーザーの作成** - B.Simon に対応するユーザーを Float で作成し、Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Float**&gt;**シングルサインオン**
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL: `https://app.float.com/sso/metadata`を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<HOSTNAME>.float.com/sso/azuread`。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<HOSTNAME>.float.com/login`。

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 &lt;hostname&gt; は、実際の Float ホスト名に置き換えてください。 不明な場合は、 [Float クライアント サポート チーム](mailto:support@float.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Float アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. 上記に加えて、Float アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | user.userprincipalname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Float のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Float SSO の構成

Float 側でシングル サインオンを構成するには、 **Float** Team Settings セクションにアクセスし、認証モジュールから [構成] を選択します。 [SAML 2.0 エンドポイント URL] フィールドに Microsoft Entra ログイン URL を貼り付け、[ID プロバイダー発行者 URL] フィールドに Microsoft Entra 識別子を貼り付け、ダウンロードした **証明書 (Base64)** のフルテキストを [X.509 証明書] フィールドに貼り付けて保存します。

#### Float テスト ユーザーの作成

このセクションでは、Float で Britta Simon というユーザーを作成します。 [People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/人) セクションまたは [Team Settings Guest](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/チーム設定のゲスト) セクションからユーザーを追加し、それらのユーザーにアクセス権を付与します。 シングル サインオンを使用する前に、ユーザーを作成し、招待を受け入れる必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Float Sign on URL にリダイレクトされます。
- Float のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Float に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Float] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Float に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/flock-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Flock を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flock-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: ユーザー アカウントを Flock に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Flock と Microsoft Entra ID で実行する手順を示し、ユーザーやグループを Flock に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Flock でユーザーを作成します。
- アクセスが不要になった場合に Flock のユーザーを削除します。
- Flock に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flock-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- [フロック テナント](https://flock.com/pricing/)
- Admin アクセス許可がある Flock のユーザー アカウント。

### Flock へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Flock へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Flock に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Flock に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを Flock に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Flock にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Flock を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Flock を構成する前に、Flock で SCIM プロビジョニングを有効にする必要があります。

1. [Flock](https://web.flock.com/?) にログインします。 **[設定] アイコン**&gt;**チームを管理します**。

    [Image: Flock Web サイトのスクリーンショット。設定アイコンが強調表示されていて、そのショートカット メニューが表示されています。そのメニューで、[チームを管理する] が強調表示されています。]
2. **[Auth and Provisioning](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証とプロビジョニング)** を選択します。

    [Image: Flock Web サイトのメニューのスクリーンショット。[認証とプロビジョニング] 項目が強調表示されています。]
3. **[API Token](API トークン)** をコピーします。 これらの値は、Flock アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: Flock Web サイトの [プロビジョニング] タブのスクリーンショット。[API トークン] の下に、値が強調表示されています。トークンの横に [トークンのコピー] ボタンがあります。]

### ギャラリーから Flock を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Flock を構成するには、Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に Flock を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Flock を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで「**Flock**」と入力し、検索ボックスで **Flock** を選びます。
4. 結果パネルから **[Flock]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の Flock のスクリーンショット。]

### Flock への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Flock のユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Flock のシングル サインオンに関する記事で説明されている手順に従って、Flock に対して SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flock-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra IDで Flock の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Flock]** を選択します。

    [Image: アプリケーションの一覧の Flock リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [自動] オプションが強調表示された [プロビジョニング モード] ドロップダウン リストのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Flock テナントの URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Flock に接続できることを確認します。 接続に失敗した場合は、Flock アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute Mapping** セクションで、Microsoft Entra IDから Flock に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Flock のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Flock ユーザー属性のスクリーンショット。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

**Current Status** セクションを使用して進行状況を監視し、リンクをクリックしてプロビジョニング アクティビティ レポートに移動できます。このレポートには、Microsoft Entra プロビジョニング サービスによって Flock に対して実行されたすべてのアクションが記載されています。 詳細については、「[ユーザー プロビジョニングの状態を確認する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)」を参照してください。 Microsoft Entraプロビジョニング ログを読み取る方法については、「[自動ユーザー アカウント プロビジョニングのレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/flock-safety-tutorial"} -->
## Microsoft Entra ID でシングルサインオン用の Flock Safety を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flock-safety-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Flock Safety の間のシングル サインオンを構成する方法について説明します。

この記事では、Flock Safety と Microsoft Entra ID を統合する方法について説明します。 Flock Safety を Microsoft Entra ID と統合すると、以下のことが可能になります。

- Flock Safety にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Flock Safety に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Flock Safety でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Flock Safety では、**SP** Initiated SSO がサポートされます。
- Flock Safety では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Flock Safety の追加

Microsoft Entra ID への Flock Safety の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Flock Safety を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Flock Safety**」と入力します。
4. 結果パネルから **[Flock Safety]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Flock Safety 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Flock Safety に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するには、Microsoft Entra ユーザーと Flock Safety の関連ユーザー間にリンク関係を確立する必要があります。

Flock Safety 用の Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Flock Safety SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Flock Safety のテスト ユーザーの作成** - Flock Safety で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Flock Safety]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`urn:auth0:prod-flock:<ID>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.flocksafety.com/login/callback?connection=<ID>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://users.flocksafety.com/sso-login/<CustomName>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[Flock Safety サポート チーム](mailto:support@flocksafety.com)にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Flock Safety のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Flock Safety SSO を構成する

**Flock Safety** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と Microsoft Entra 管理センターからコピーした適切な URL を [Flock Safety サポート チーム](mailto:support@flocksafety.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Flock Safety テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Flock Safety に作成します。 Flock Safety では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Flock Safety にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Flock Safety のサインオン URL にリダイレクトします。
- Flock Safety のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Flock Safety] タイルを選択すると、このオプションは Flock Safety のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/flock-tutorial"} -->
## Microsoft Entra ID で Flock for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flock-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Flock 間のシングル サインオンを構成する方法について説明します。

この記事では、Flock と Microsoft Entra ID を統合する方法について説明します。 Front を Microsoft Entra ID と統合すると、次のことができます。

- Flock にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Flock に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Flock でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Flock では、 **SP** Initiated SSO がサポートされます。
- Flock では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flock-provisioning-tutorial)。

### ギャラリーからの Flock の追加

Microsoft Entra ID への Flock の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Flock を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Flock**」と入力します。
4. 結果パネルから **Flock** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Front に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Flock に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Flock での関連ユーザーとの間にリンク関係を確立する必要があります。

Flock に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Flock SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Flock テスト ユーザーの作成** - Flock において Britta Simon に相当するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Flock]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.flock.com/`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.flock.com/`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Flock クライアント サポート チーム](mailto:support@flock.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Flock のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Flock SSO の構成

1. 別の Web ブラウザー ウィンドウで、Flock 企業サイトに管理者としてログインします。
2. 左側のナビゲーション パネルから [ **認証** ] タブを選択し、[ **SAML 認証**] を選択します。

    [Image: [認証] タブを示すスクリーンショット。[S A M L Authentication](S A M L 認証) が選択されています。]
3. **[SAML Authentication]\(SAML 認証**\) セクションで、次の手順を実行します。

    [Image: Flock の構成]

    a. **SAML 2.0 Endpoint(HTTP)** ボックスに、前にコピーした**ログイン URL** 値を貼り付けます。

    b。 **ID プロバイダー発行者** テキストボックスに、前にコピーした **Microsoft Entra Identifier** 値を貼り付けます。

    c. Azure portal からダウンロードした **証明書 (Base64)** をメモ帳で開き、その内容を **[パブリック証明書** ] ボックスに貼り付けます。

    d. **[保存] を選択します**。

#### Flock テスト ユーザーの作成

Microsoft Entra ユーザーが Flock にログインできるようにするには、ユーザーを Flock にプロビジョニングする必要があります。 Flock の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. Flock 企業サイトに管理者としてログインします。
2. 左側のナビゲーション パネルから [ **チームの管理** ] を選択します。

    [Image: [チームの管理] が選択されていることを示すスクリーンショット。]
3. [ **メンバーの追加]** タブを選択し、[ **チーム メンバー**] を選択します。

    [Image: [メンバーの追加] タブと [チーム メンバー] が選択されていることを示すスクリーンショット。]
4. **Brittasimon@contoso.com**などのユーザーのメール アドレスを入力し、[**ユーザーの追加]** を選択します。

    [Image: 従業員の追加]

注

Flock では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/flock-provisioning-tutorial) 。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Flock のサインオン URL にリダイレクトされます。
- Flock のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Flock タイルを選択すると、このオプションは Flock のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/floqast-tutorial"} -->
## Microsoft Entra ID で FloQast をシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/floqast-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FloQast 間のシングル サインオンを構成する方法について説明します。

この記事では、FloQast と Microsoft Entra ID を統合する方法について説明します。 FloQast を Microsoft Entra ID と統合すると、次のことが可能になります。

- FloQast にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで FloQast に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FloQast でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FloQast では、**SP と IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの FloQast の追加

Microsoft Entra ID への FloQast の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FloQast を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「FloQast**」と入力します。
4. 結果パネルから **FloQast** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FloQast 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、FloQast に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと FloQast の関連ユーザーとの間にリンク関係を確立する必要があります。

FloQast に対する Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FloQast SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FloQast のテスト ユーザーの作成** - FloQast で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**FloQast**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 識別子 |
    | --- |
    | `https://go.floqast.com/` |
    | `https://eu.floqast.app/` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://go.floqast.com/api/sso/saml/azure` |
    | `https://eu.floqast.app/api/sso/saml/azure` |
    |  |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | サインオン URL |
    | --- |
    | `https://go.floqast.com/login/sso` |
    | `https://eu.floqast.app/login/sso` |
    |  |
7. FloQast アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、FloQast アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
    | Email | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[SAML 署名証明書**] セクションで、[**編集**] ボタンを選択して **[SAML 署名証明書**] ダイアログを開き、次の手順を実行します。

    [Image: SAML 署名証明書の編集]

    1. **署名オプション**から**SAML 応答とアサーションに署名する**を選択します。
    2. **[保存] を選択します**。
11. [ **FloQast のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FloQast SSO の構成

**FloQast** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [FloQast サポート チーム](mailto:support@floqast.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FloQast テスト ユーザーの作成

このセクションでは、FloQast で B.Simon というユーザーを作成します。 [FloQast サポート チーム](mailto:support@floqast.com)と協力して、FloQast プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる FloQast のサインオン URL にリダイレクトされます。
- FloQast のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した FloQast に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで FloQast タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した FloQast に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fluxxlabs-tutorial"} -->
## Microsoft Entra ID で Fluxx Labs for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fluxxlabs-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fluxx Labs の間のシングル サインオンを構成する方法について説明します。

この記事では、Fluxx Labs と Microsoft Entra ID を統合する方法について説明します。 Fluxx Labs と Microsoft Entra ID を統合すると、次のことができます。

- Fluxx Labs にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Fluxx Labs に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fluxx Labs のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fluxx Labs では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Fluxx Labs の追加

Microsoft Entra ID への Fluxx Labs の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Fluxx Labs を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Fluxx Labs**」と入力します。
4. 結果のパネルから **[Fluxx Labs]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fluxx Labs 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Fluxx Labs に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Fluxx Labs の関連ユーザーとの間にリンク関係を確立する必要があります。

Fluxx Labs に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fluxx Labs の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fluxx Labs 上で B.Simon に対応するテストユーザーを作成し、Microsoft Entra 内のユーザーとリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Fluxx Labs**&gt;**シングルサインオン**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    ア [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | 生産 | `https://<subdomain>.fluxx.io` |
    | 運用前 | `https://<subdomain>.preprod.fluxxlabs.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | 生産 | `https://<subdomain>.fluxx.io/auth/saml/callback` |
    | 運用前 | `https://<subdomain>.preprod.fluxxlabs.com/auth/saml/callback` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Fluxx Labs クライアント サポート チーム](https://fluxx.zendesk.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Fluxx Labs のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fluxx Labs での SSO の構成

1. 別の Web ブラウザーのウィンドウで、Fluxx Labs 企業サイトに管理者としてサインインします。
2. **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** セクションで **[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者)** を選択します。

    [Image: [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) が選択されている [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) セクションを示すスクリーンショット。]
3. 管理パネルで、 **[Plug-ins](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プラグイン)**&gt;**[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インテグレーション)** の順に選択し、 **[SAML SSO-(Disabled)](SAML SSO (無効))** を選択します。

    [Image: [S A M L S S O - (Disabled)](S A M L S S O - (無効)) が選択されている [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インテグレーション) タブを示すスクリーン。]
4. [Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/属性) セクションで、次の手順に従います。

    [Image: [S A M L S S O] がオンになり、フィールドに値が入力され、[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) ボタンが選択されている [Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/属性) セクションを示すスクリーンショット。]

    ア **[SAML SSO]** チェックボックスをオンにします。

    b。 **[Request Path](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/要求パス)** ボックスに、「 **/auth/saml**」と入力します。

    c. **[Callback Path](コールバック パス)** ボックスに、「 **/auth/saml/callback**」と入力します。

    d. **[Assertion Consumer Service URL (シングル サインオン URL)]** テキストボックスに、入力した **[応答 URL]** の値を入力します。

    え **[対象 (SP エンティティ ID)]** テキストボックスに、入力した **[ID]** の値を入力します。

    f. **[ID プロバイダーの SSO ターゲット URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を入力します。

    ジー Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、[ **ID プロバイダー証明書** ] ボックスに貼り付けます。

    h. **[Name identifier Format](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前識別子の形式)** ボックスに、次の値を入力します。`urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress`

    一. **保存** を選択します。

    注

    コンテンツが保存されると、セキュリティのためにフィールドは空白で表示されますが、値は構成に保存されています。

#### Fluxx Labs テスト ユーザーを作成する

Microsoft Entra ユーザーが Fluxx Labs にサインインできるようにするには、そのユーザーを Fluxx Labs にプロビジョニングする必要があります。 Fluxx Labs の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. Fluxx Labs 企業サイトに管理者としてサインインします。
2. 表示されている以下のアイコンを選択 **します**。

    [Image: [Your Dashboard is Empty](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ダッシュボードが空です) の下のプラス記号アイコンが選択されている管理者オプションを示すスクリーンショット。]
3. ダッシュボードで、以下に表示されているアイコンを選択して**新しい人の**カードを開きます。

    [Image: [People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) の隣のプラス記号アイコンが選択されている [Contact Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/連絡先の管理) メニューを示すスクリーンショット。]
4. **[NEW PEOPLE](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新規ユーザー)** セクションで、次の手順を実行します。

    [Image: Fluxx Labs の構成]

    ア Fluxx Labs では、SSO ログインの一意識別子として電子メールを使用します。 **[SSO UID]** フィールドにユーザーの電子メール アドレスを入力します。これは、SSO でのログインとして使用される電子メールアドレスと一致する電子メール アドレスです。

    b。 **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fluxx Labs に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Fluxx Labs] タイルを選択すると、SSO を設定した Fluxx Labs に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fm-systems-tutorial"} -->
## Microsoft Entra ID を使用して FM:Systems for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fm-systems-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FM:Systems の間のシングル サインオンを構成する方法について説明します。

この記事では、FM:Systems と Microsoft Entra ID を統合する方法について説明します。 FM:Systems を Microsoft Entra ID と統合すると、次のことが可能になります。

- FM:Systems にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して FM:Systems に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FM:Systems でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- FM:Systems では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから FM:Systems を追加する

Microsoft Entra ID への FM:Systems の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FM:Systems を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「FM:Systems**」と入力します。
4. 結果パネルから **FM:Systems** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FM:Systems の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、FM:Systems に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する FM:Systems ユーザーをリンクする必要があります。

FM:Systems に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FM:Systems SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FM:Systems のテスト ユーザーの作成** - FM:Systems で B.Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**FM:Systems**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.fmshosted.com/fminteract/ConsumerService2.aspx`

    注

    この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには [、FM:Systems クライアント サポート チーム](https://fmsystems.com/support-services/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **FM:Systems のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FM:Systems SSO の構成

**FM:Systems** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [FM:Systems サポート チーム](https://fmsystems.com/support-services/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FM:Systems のテスト ユーザーの作成

1. Web ブラウザー ウィンドウで、FM:Systems 企業サイトに管理者としてサインインします。
2. **システム管理**&gt;**管理セキュリティ**&gt;**Users**&gt;**User リスト**に移動します。

    [Image: システム管理]
3. [ **新しいユーザーの作成] を選択します**。

    [Image: 新しいユーザーの作成 新しい]
4. [ **ユーザーの作成** ] セクションで、次の手順を実行します。

    [Image: ユーザーの作成]

    a. 関連するテキストボックスに、プロビジョニングする有効な Microsoft Entra アカウントの **UserName**、 **Password**、 **Confirm Password**、 **E-mail** 、 **Employee ID を** 入力します。

    b。 [ **次へ**] を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した FM:Systems に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [FM:Systems] タイルを選択すると、SSO を設定した FM:Systems に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/foko-retail-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Foko Retail を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/foko-retail-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Foko Retail の間のシングル サインオンを構成する方法について説明します。

この記事では、Foko Retail と Microsoft Entra ID を統合する方法について説明します。 Foko Retail を Microsoft Entra ID と統合すると、次のことができます。

- Foko Retail にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Foko Retail に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Foko Retail サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Foko Retail では、**SP** initiated SSO\* がサポート\*されます。

### ギャラリー\*からの Foko Retail の追加

Microsoft Entra ID への Foko Retail の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Foko Retail を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Foko Retail**」と入力します。
4. 結果のパネルから **[Foko Retail]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Foko Retail 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Foko Retail に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Foko Retail の関連ユーザー間にリンク関係を確立する必要があります。

Foko Retail に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Foko Retail の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Foko Retail のテスト ユーザーの作成** - Foko Retail で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Foko Retail**&gt;** シングル サインオンに移動します。**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.foko.io/sso/{$CUSTOM_ID}/metadata.xml`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.foko.io/sso/{$CUSTOM_ID}/login`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Motus のクライアント サポート チーム](mailto:support@fokoretail.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up Foko Retail](Foko Retail の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Foko Retail の SSO の構成

**Foko Retail** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Foko Retail のサポート チーム](mailto:support@fokoretail.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Foko Retail のテスト ユーザーの作成

このセクションでは、Foko Retail で B.Simon というユーザーを作成します。 [Foko Retail のサポート チーム](mailto:support@fokoretail.com)と連携し、Foko Retail プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Foko Retail のサインオン URL にリダイレクトされます。
- Foko Retail のサインオン URL\* に直接移動し、そこからログイン フロー\*を開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Foko Retail] タイルを選択すると、このオプションは Foko Retail のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/folloze-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Folloze を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/folloze-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Folloze の間のシングル サインオンを構成する方法について説明します。

この記事では、Folloze と Microsoft Entra ID を統合する方法について説明します。 Folloze を Microsoft Entra ID と統合すると、次のことが可能になります。

- Folloze にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Folloze に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Folloze サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Folloze では、 **IDP** Initiated SSO がサポートされます。
- Folloze では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Folloze の追加

Microsoft Entra ID への Folloze の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Folloze を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Folloze**」と入力します。
4. 結果のパネルから **Folloze** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Folloze 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Folloze に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Folloze の関連ユーザーとの間にリンク関係を確立する必要があります。

Folloze に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Folloze の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Folloze のテスト ユーザーを作成する** - Microsoft Entra のユーザーの表現にリンクされた B.Simon の対応セクションを Folloze で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Folloze**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure で事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Folloze アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Folloze アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザー名: user.othermail |
    | Nameasemail | user.userprincipalname |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Folloze のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Folloze の SSO の構成

**Folloze ** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Folloze サポート チーム](mailto:support@folloze.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Folloze のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Folloze に作成します。 Folloze では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Folloze にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Folloze に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Folloze] タイルを選択すると、SSO を設定した Folloze に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/foodee-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Foodee を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/foodee-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: Foodee に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事では、Foodee と Microsoft Entra ID でユーザーまたはグループを Foodee に自動的にプロビジョニングまたはプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能とそのしくみ、よく寄せられる質問に対する回答については、[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除に関するページを参照してください。

このコネクタは、現在プレビューの段階です。 プレビューの詳細については、「 [オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)」を参照してください。

### 前提条件

この記事では、次の前提条件を満たしていることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Foodee テナント](https://www.food.ee/about-us/)
- Admin アクセス許可がある Foodee のユーザー アカウント

### ユーザーを Foodee に割り当てる

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID内のアプリケーションに割り当てられているユーザーまたはグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Foodee へのアクセスが必要なMicrosoft Entra ID内のユーザーまたはグループを決定する必要があります。 この決定を行った後、「エンタープライズ アプリにユーザーまたはグループを割り当てる」の手順に従って、これらの [ユーザーまたはグループを Foodee に割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。

### ユーザーを Foodee に割り当てる際の重要なヒント

ユーザーを割り当てるときは、次のヒントに留意してください。

- Foodee には、1 人のMicrosoft Entra ユーザーのみを割り当てて、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 後で、追加のユーザーまたはグループを割り当てることができます。
- Foodee にユーザーを割り当てるときは、[ **割り当て** ] ウィンドウで、有効なアプリケーション固有のロール (使用可能な場合) を選択します。 *既定のアクセス* ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Foodee を設定する

Microsoft Entra IDを使用して自動ユーザー プロビジョニング用に Foodee を構成する前に、Foodee で System for Cross-domain Identity Management (SCIM) プロビジョニングを有効にする必要があります。

1. [Foodee](https://www.food.ee/login/) にサインインし、テナント ID を選択します。

    [Image: Foodee エンタープライズ ポータルのメイン メニューのスクリーンショット。テナント ID プレースホルダーがメニューに表示されます。]
2. **エンタープライズ ポータル**で、[**シングル サインオン**] を選択します。

    [Image: Foodee Enterprise Portal の左側のウィンドウ メニューのスクリーンショット。]
3. 後で使用するために、[ **API トークン** ] ボックスの値をコピーします。 Foodee アプリケーションの [**プロビジョニング**] タブの [**シークレット トークン**] ボックスに入力します。

    [Image: Foodee エンタープライズ ポータルのページのスクリーンショット。A P I トークン値が強調表示されています。]

### ギャラリーからの Foodee の追加

Microsoft Entra IDを使用して自動ユーザー プロビジョニング用に Foodee を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Foodee を追加する必要があります。

Microsoft Entra アプリケーション ギャラリーから Foodee を追加するには、次の操作を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ウィンドウのスクリーンショット。]
3. 新しいアプリケーションを追加するには、ウィンドウの上部にある [ **新しいアプリケーション** ] を選択します。

    [Image: [新しいアプリケーション] ボタンのスクリーンショット。]
4. 検索ボックスに「 **Foodee**」と入力し、結果ウィンドウで **Foodee** を選択し、[ **追加]** を選択してアプリケーションを追加します。

    [Image: 結果一覧の Foodee のスクリーンショット。]

### Foodee への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Foodee のユーザーまたはグループを作成、更新、無効化するように、Microsoft Entra プロビジョニング サービスを構成します。

ヒント

Foodee のシングル サインオンに関する記事の手順に従って、Foodee の SAML ベースの [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/foodee-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

次の手順に従って、Microsoft Entra IDで Foodee の自動ユーザー プロビジョニングを構成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ウィンドウのスクリーンショット。]
3. アプリケーションの一覧 **で** 、 **Foodee** を選択します。

    [Image: アプリケーションの一覧の Foodee リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [自動] オプションが強調表示されている [プロビジョニング モード] ドロップダウン リストのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Foodee テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択してMicrosoft Entra ID Foodee に接続できることを確認します。 接続に失敗した場合は、Foodee アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute Mappings** で、Microsoft Entra IDから Foodee に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Foodee の *ユーザー アカウント* との照合に使用されます。

    [Image: 属性マッピング ページのスクリーンショット。テーブルには、Microsoft Entra ID属性と Foodee 属性と一致する優先順位が一覧表示されます。]
13. 変更をコミットするには、[ **保存]** を選択します。
14. [Mappings で、Microsoft Entra グループを Foodee。
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

**[現在の状態]** セクションを使用して進行状況を監視し、リンクをクリックしてプロビジョニング アクティビティ レポートに進むことができます。 このレポートには、Foodee に対してMicrosoft Entra プロビジョニング サービスによって実行されるすべてのアクションが記述されています。 詳細については、「 [ユーザー プロビジョニングの状態を確認する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)参照してください。 Microsoft Entraプロビジョニング ログを読み取る方法については、「[自動ユーザー アカウント プロビジョニングのレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/foodee-tutorial"} -->
## Microsoft Entra ID で Foodee for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/foodee-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Foodee の間のシングル サインオンを構成する方法について説明します。

この記事では、Foodee と Microsoft Entra ID を統合する方法について説明します。 Foodee を Microsoft Entra ID と統合すると、次のことが可能になります。

- Foodee にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Foodee に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Foodee サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Foodee では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Foodee では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Foodee では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/foodee-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Foodee の追加

Microsoft Entra ID への Foodee の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Foodee を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Foodee**」と入力します。
4. 結果パネルから **Foodee** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Foodee 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Foodee に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Foodee の関連ユーザーとの間にリンク関係を確立する必要があります。

Foodee に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Foodee SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Foodeeテストユーザーの作成** - FoodeeでB.Simonに対応するユーザーを作成し、Microsoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Foodee**&gt;**シングルサインオン**へ移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://concierge.food.ee/sso/saml/<INSTANCENAME>/consume`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://concierge.food.ee/sso/saml/<INSTANCENAME>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには [、Foodee クライアント サポート チーム](mailto:dev@food.ee) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Foodee のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Foodee の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Foodee 企業サイトに管理者としてサインインします。
2. ページの右上隅にある **プロファイル ロゴ** を選択し、[ **シングル サインオン]** に移動し、次の手順を実行します。

    [Image: Foodee の構成]

    1. **[IDP 名**] テキスト ボックスに、例: Azure のような名前を入力します。
    2. メモ帳でフェデレーション メタデータ XML を開き、その内容をコピーし、 **IDP METADATA XML** テキスト ボックスに貼り付けます。
    3. **[保存] を選択します**。

#### Foodee のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Foodee に作成します。 Foodee では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Foodee に存在しない場合は、Foodee にアクセスしようとしたときに新しいユーザーが作成されます。

Foodee は自動ユーザー プロビジョニングもサポートしています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/foodee-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Foodee のサインオン URL にリダイレクトされます。
- Foodee のサインオン URL に直接移動し、そこからログイン フローを開始します。

IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Foodee に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Foodee] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Foodee に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/forcepoint-cloud-security-gateway-provisioning-tutorial"} -->
## Forcepoint Cloud Security Gateway の構成 - Microsoft Entra IDを使用した自動ユーザー プロビジョニング用のユーザー認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/forcepoint-cloud-security-gateway-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-07
- Summary: Microsoft Entra IDから Forcepoint Cloud Security Gateway - ユーザー認証にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Forcepoint Cloud Security Gateway - ユーザー認証とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されると、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを[Forcepoint Cloud Security Gateway - ユーザー認証](https://admin.forcepoint.net) に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Forcepoint Cloud Security Gateway - User Authentication でユーザーを作成します。
- Forcepoint Cloud Security Gateway - ユーザー認証で、アクセスが不要になった場合にユーザーを削除します。
- Microsoft Entra IDと Forcepoint Cloud Security Gateway - ユーザー認証の間でユーザー属性の同期を維持します。
- Forcepoint Cloud Security Gateway - User Authentication でグループとグループ メンバーシップをプロビジョニングします。
- Forcepoint Cloud Security Gateway - User Authentication への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/forcepoint-cloud-security-gateway-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Forcepoint Cloud Security Gateway - User Authentication テナント。
- Forcepoint Cloud Security Gateway - User Authentication で管理者のアクセス許可を持つユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとForcepoint Cloud Security Gateway - ユーザー認証の間で[マップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Forcepoint Cloud Security Gateway - ユーザー認証を構成して、Microsoft Entra IDでのプロビジョニングをサポートする

Forcepoint Cloud Security Gateway - ユーザー認証のサポートに問い合わせて、Forcepoint Cloud Security Gateway - ユーザー認証を構成して、Microsoft Entra IDでのプロビジョニングをサポートします。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Forcepoint Cloud Security Gateway - ユーザー認証を追加する

Forcepoint Cloud Security Gateway - Microsoft Entra アプリケーション ギャラリーからのユーザー認証を追加して、Forcepoint Cloud Security Gateway - ユーザー認証へのプロビジョニングの管理を開始します。 SSO を使用するためにセットアップした Forcepoint Cloud Security Gateway - User Authentication があれば、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Forcepoint Cloud Security Gateway - User Authentication の自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Forcepoint Cloud Security Gateway - ユーザー認証の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、**[Forcepoint Cloud Security Gateway - User Authentication]** を選択します。

    [Image: アプリケーションの一覧に表示された Forcepoint Cloud Security Gateway - User Authentication のリンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Forcepoint テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Forcepoint に接続できることを確認します。 接続に失敗した場合は、Forcepoint アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから Forcepoint Cloud Security Gateway - ユーザー認証に同期されるユーザー属性を確認します。 **照合する**プロパティとして選択した属性は、更新処理で Forcepoint Cloud Security Gateway - User Authentication のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Forcepoint Cloud Security Gateway - User Authentication API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Forcepoint Cloud Security Gateway - User Authentication で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:forcepoint:2.0:User:ntlmId | 糸 |  |  |
13. **Mappings** セクションで、** Microsoft Entra グループを Forcepoint Cloud Security Gateway - ユーザー認証**に同期します。
14. Microsoft Entra IDから Forcepoint Cloud Security Gateway - ユーザー認証に同期されるグループ属性を、**Attribute-Mapping** セクションで確認します。 **照合する**プロパティとして選択した属性は、更新処理で Forcepoint Cloud Security Gateway - User Authentication のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Forcepoint Cloud Security Gateway - User Authentication で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  |  |
    | members | リファレンス |  |  |
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/forcepoint-cloud-security-gateway-tutorial"} -->
## Forcepoint Cloud Security Gateway の構成 - Microsoft Entra ID を使用したシングル サインオンのユーザー認証 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/forcepoint-cloud-security-gateway-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Forcepoint Cloud Security Gateway - User Authentication との間にシングル サインオンを構成する方法について説明します。

この記事では、Forcepoint Cloud Security Gateway - ユーザー認証と Microsoft Entra ID を統合する方法について説明します。 Forcepoint Cloud Security Gateway - User Authentication と Microsoft Entra ID を統合すると、次のことが可能になります。

- Forcepoint Cloud Security Gateway - User Authentication にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Forcepoint Cloud Security Gateway - User Authentication に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Forcepoint Cloud Security Gateway - User Authentication のシングル サインオン (SSO) 対応サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Forcepoint Cloud Security Gateway - User Authentication では、**SP** によって開始される SSO がサポートされます。
- Forcepoint Cloud Security Gateway - ユーザー認証では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/forcepoint-cloud-security-gateway-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Forcepoint Cloud Security Gateway - User Authentication を追加する

Forcepoint Cloud Security Gateway - User Authentication の Microsoft Entra ID への統合を構成するには、ギャラリーから自分のマネージド SaaS アプリの一覧に Forcepoint Cloud Security Gateway - User Authentication を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Forcepoint Cloud Security Gateway - User Authentication**」と入力します。
4. 結果パネルから **[Forcepoint Cloud Security Gateway - User Authentication]** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Forcepoint Cloud Security Gateway - User Authentication 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Forcepoint Cloud Security Gateway - User Authentication に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Forcepoint Cloud Security Gateway - User Authentication の関連ユーザーとの間にリンク関係を確立する必要があります。

Forcepoint Cloud Security Gateway - User Authentication で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Forcepoint Cloud Security Gateway - User Authentication の SSO を構成する**- アプリケーション側のシングル サインオンを構成します。
    1. **Forcepoint Cloud Security Gateway - User Authentication のテスト ユーザーを作成する** - Forcepoint Cloud Security Gateway - User Authentication で B.Simon に対応するユーザーを作成し、Microsoft Entra 側での表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;** [Forcepoint Cloud Security Gateway - User Authentication] **&gt;** [シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://mailcontrol.com/sp_metadata.xml`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://proxy-login.blackspider.com/`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://forcepoint.com`
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Forcepoint Cloud Security Gateway - User Authentication のセットアップ]** セクションで、要件に基づいて該当する URL をコピーします。

    [Image: 適切な構成 URL のコピー操作を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Forcepoint Cloud Security Gateway - User Authentication の SSO を構成する

1. Forcepoint Cloud Security Gateway - User Authentication 企業サイトに管理者としてログインします。
2. **Web**&gt;**SETTINGS** に移動し、[**シングル サインオン**] を選択します。
3. **[シングル サインオン]** ページで、次の手順を実行します。

    [Image: シングル サインオン構成を示すスクリーンショット。]

    a. **[Use identity provider for single sign-on] (シングル サインオンに ID プロバイダーを使用する)** チェック ボックスをオンにします。

    b。 ドロップダウンから **[ID プロバイダー]** を選択します。

    c. **[参照**] オプションを選択して、[**ファイルのアップロード**] テキストボックスに**フェデレーション メタデータ XML** ファイルをアップロードします。

    d. **保存** を選択します。

#### Forcepoint Cloud Security Gateway - User Authentication のテスト ユーザーを作成する

このセクションでは、Forcepoint Cloud Security Gateway - User Authentication で Britta Simon という名前のユーザーを作成します。 [Forcepoint Cloud Security Gateway - User Authentication サポート チーム](mailto:support@forcepoint.com)と連携して、Forcepoint Cloud Security Gateway - User Authentication プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Forcepoint Cloud Security Gateway - ユーザー認証のサインオン URL にリダイレクトされます。
- Forcepoint Cloud Security Gateway - User Authentication のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Forcepoint Cloud Security Gateway - ユーザー認証] タイルを選択すると、このオプションは Forcepoint Cloud Security Gateway - ユーザー認証のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/foreseecxsuite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ForeSee CX Suite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/foreseecxsuite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ForeSee CX Suite の間でシングル サインオンを構成する方法について説明します。

この記事では、ForeSee CX Suite と Microsoft Entra ID を統合する方法について説明します。 ForeSee CX Suite を Microsoft Entra ID と統合すると、次のことが可能になります。

- ForeSee CX Suite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して ForeSee CX Suite に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ForeSee CX Suite でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ForeSee CX Suite では、 **SP** によって開始される SSO がサポートされます。
- ForeSee CX Suite では、 **Just In Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの ForeSee CX Suite の追加

Microsoft Entra ID への ForeSee CX Suite の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ForeSee CX Suite を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ForeSee CX Suite**」と入力します。
4. 結果パネルから **ForeSee CX Suite** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO を ForeSee CX Suite 向けに構成してテストする

**B.Simon** というテスト ユーザーを使用して、ForeSee CX Suite に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ForeSee CX Suite の関連ユーザーとの間にリンク関係を確立する必要があります。

ForeSee CX Suite に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ForeSee CX Suite の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **ForeSee CX Suite のテスト ユーザーを作成する** - ForeSee CX Suite で B.Simon に対応するテスト ユーザーを作成し、それを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップを実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ForeSee CX Suite]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] 画面を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションで **識別子** の値が自動的に設定されます。

    d. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://cxsuite.foresee.com/`

    e. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します: https://www.okta.com/saml2/service-provider/&lt;UniqueID&gt;

    注

    **識別子**の値が自動的に設定されない場合は、上記のパターンに従って値を手動で入力してください。 識別子の値は実際の値ではありません。 実際の識別子でこの値を更新します。 この値を取得するには [、ForeSee CX Suite クライアント サポート チーム](mailto:support@foresee.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **ForeSee CX Suite のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ForeSee CX Suite SSO の構成

**ForeSee CX Suite** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [ForeSee CX Suite サポート チーム](mailto:support@foresee.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### ForeSee CX Suite のテスト ユーザーの作成

このセクションでは、ForeSee CX Suite で Britta Simon というユーザーを作成します。 [ForeSee CX Suite サポート チーム](mailto:support@foresee.com)と協力して、ForeSee CX Suite プラットフォームの許可リストに追加する必要があるユーザーまたはドメインを追加します。 ドメインがチームによって追加されると、ユーザーは自動的に ForeSee CX Suite プラットフォームにプロビジョニングされます。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ForeSee CX Suite のサインオン URL にリダイレクトされます。
- ForeSee CX Suite サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [ForeSee CX Suite] タイルを選択すると、このオプションは ForeSee CX Suite のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/formcom-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Form.com を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/formcom-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Form.com 間のシングル サインオンを構成する方法について説明します。

この記事では、Form.com と Microsoft Entra ID を統合する方法について説明します。 Form.com を Microsoft Entra ID と統合すると、次のことが可能になります。

- Form.com にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Form.com に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Form.com でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Form.com では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Form.com の追加

Microsoft Entra ID への Form.com の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Form.com を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Form.com**」と入力します。
4. 結果のパネルから **[Form.com]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Form.com 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Form.com に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Form.com の関連ユーザーとの間にリンク関係を確立する必要があります。

Form.com に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Form.com の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Form.com テストユーザーを作成して** - Form.com に B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Form.com]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.wa-form.com`

    b。 **[識別子]** ボックスに、`https://<subdomain>.form.com` という形式で URL を入力します。

    c. [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    ```http
    https://<subdomain>.wa-form.com/Member/UserAccount/SAML2.action
    https://<subdomain>.form.com/Member/UserAccount/SAML2.action
    ```

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 これらの値 Form.com 取得するには、クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して **証明書 (Base64)** をダウンロードし、コピー **アイコン** を選択して、要件に従って指定されたオプションから **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Form.com のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Form.com の SSO の構成

**Form.com** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)**、**アプリのフェデレーション メタデータ URL**、アプリケーション構成からコピーした適切な URL をサポート チーム Form.com 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Form.com テスト ユーザーの作成

このセクションでは、Form.com で Britta Simon というユーザーを作成します。 Form.com サポート チームと協力して、Form.com プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Form.com サインオン URL にリダイレクトされます。
- Form.com のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Form.com] タイルを選択すると、このオプションは Form.com サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/forms-workflow-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Granicus Forms とワークフローを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/forms-workflow-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-21
- Summary: Microsoft Entra ID から Forms & Workflow にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Forms & Workflow と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Forms & Workflow](https://granicus.com/product/forms-workflow-openforms) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- フォームとワークフローでユーザーを作成します。
- アクセスが不要になった場合は、フォームとワークフローのユーザーを削除します。
- Microsoft Entra ID とフォームとワークフローの間でユーザー属性の同期を維持します。
- フォームとワークフローでグループとグループ メンバーシップをプロビジョニングします。
- フォームとワークフローへの[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- アカウント所有者のアクセス許可を持つフォームおよびワークフローのユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントの計画を立てる

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID とフォームとワークフローの間でマップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするようにフォームとワークフローを構成する

この手順の詳細については、 [フォームとワークフローのヘルプ センター](https://help.openforms.com/Developers/Set-up-Azure-AD-to-work-with-Forms-Workflow)を参照してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーからフォームとワークフローを追加する

Microsoft Entra アプリケーション ギャラリーからフォームとワークフローを追加して、Forms & Workflow へのプロビジョニングの管理を開始します。 SSO 用に Forms & Workflow を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: フォーム & ワークフローへの自動ユーザープロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID でフォームとワークフローの自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **フォーム] と [ワークフロー**] を選択します。

    [Image: アプリケーションの一覧の [フォームとワークフロー] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、OpenForms テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが OpenForms に接続できることを確認します。 接続に失敗した場合は、OpenForms アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID からフォームおよびワークフローに同期されるユーザー **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作のためにフォームとワークフローのユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Forms & Workflow API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | Attribute | タイプ | フィルター処理のサポート | フォームとワークフローで要求される |
    | --- | --- | --- | --- |
    | ユーザー名 | String | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "work"].value | String |  |  |
    | name.givenName | String |  | ✓ |
    | name.familyName | String |  | ✓ |
    | externalId | String |  | ✓ |
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから OpenForms に同期されるグループ属性を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で OpenForms のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | Attribute | タイプ | フィルター処理のサポート | フォームとワークフローで要求される |
    | --- | --- | --- | --- |
    | displayName | String | ✓ | ✓ |
    | externalId | String |  | ✓ |
    | members | Reference |  |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fortes-change-cloud-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Fortes Change Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortes-change-cloud-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから Fortes Change Cloud にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Fortes Change Cloud と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成Microsoft Entra ID、Microsoft Entra プロビジョニング サービスを使用して、[Fortes Change Cloud](https://fortesglobal.com/) へのユーザーとグループのプロビジョニングとプロビジョニング解除を自動的に行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Fortes Change Cloud でユーザーを作成する
- アクセスが不要になった Fortes Change Cloud のユーザーを削除する
- Microsoft Entra IDと Fortes Change Cloud の間でユーザー属性の同期を維持する
- Fortes Change Cloud に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortes-change-cloud-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fortes Change Cloud テナント。
- 管理者のアクセス許可がある Fortes Change Cloud のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDと Fortes Change Cloud の間で[マップするデータ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Fortes Change Cloud を構成する

1. 管理者アカウントを使用して Fortes Change Cloud にログインします。 **[設定] アイコン**を選択し、[**ユーザー プロビジョニング (SCIM)]** に移動します。

    [Image: Fortes Change Cloud の SCIM の設定]
2. 新しいウィンドウで、**テナント URL** と**プライマリ トークン**.をコピーして保存します。 テナント URL は **テナント URL** \* フィールドに入力され、プライマリ トークンは Fortes Change Cloud アプリケーションの [プロビジョニング] タブの [ **シークレット** \* トークン] フィールドに入力されます。

    [Image: Fortes Change Cloud のプライマリ トークン]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Fortes Change Cloud を追加する

fortes Change Cloud へのプロビジョニングの管理を開始するには、Microsoft Entra アプリケーション ギャラリーから Fortes Change Cloud を追加します。 SSO のために Fortes Change Cloud を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Fortes Change Cloud への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Fortes Change Cloud の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Fortes Change Cloud]** を選択します。

    [Image: アプリケーションの一覧内の [Fortes Change Cloud] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Fortes Change Cloud Tenant URL と Secret Token を入力します。 **Test Connection** を選択してMicrosoft Entra IDが Fortes Change Cloud に接続できることを確認します。 接続に失敗した場合は、Fortes Change Cloud アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Fortes Change Cloud に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Fortes Change Cloud のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Fortes Change Cloud API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | 名前.整形済み | 糸 |  |
    | エクスターナルID | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:fcc:2.0:User:administrator | ブール値 |  |
    | urn:ietf:params:scim:schemas:extension:fcc:2.0:User:loginDisabled | ブール値 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 10/28/2021 - **スキーマ検出**が有効になりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fortes-change-cloud-tutorial"} -->
## Microsoft Entra ID で Fortes Change Cloud for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortes-change-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fortes Change Cloud の間にシングル サインオンを構成する方法について説明します。

この記事では、Fortes Change Cloud と Microsoft Entra ID を統合する方法について説明します。 Fortes Change Cloud を Microsoft Entra ID と統合すると、次のことができます。

- Fortes Change Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Fortes Change Cloud に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fortes Change Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fortes Change Cloud では、**SP と IDP** によるSSOの開始がサポートされます。
- Fortes Change Cloud では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortes-change-cloud-provisioning-tutorial)。

### ギャラリーからの Fortes Change Cloud の追加

Microsoft Entra ID への Fortes Change Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Fortes Change Cloud を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;で**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Fortes Change Cloud**」と入力します。
4. 結果パネルから **Fortes Change Cloud** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fortes Change Cloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Fortes Change Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Fortes Change Cloud の関連ユーザーの間にリンク関係を確立する必要があります。

Fortes Change Cloud に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fortes Change Cloud SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fortes Change Cloud のテスト ユーザーを作成する** - Fortes Change Cloud で B.Simon に対応するユーザーを作成し、それを Microsoft Entra でのユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Fortes Change Cloud**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ａ [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<UNIQUE_IDENTIFIER>.fortes-online.com/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<UNIQUE_IDENTIFIER>.fortes-online.com/`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<UNIQUE_IDENTIFIER>.fortes-online.com/saml/SSO`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Fortes Change Cloud クライアント サポート チーム](mailto:support@fortes.nl) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Fortes Change Cloud アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 Fortes Change Cloud アプリケーションでは、 **一意のユーザー識別子** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fortes Change Cloud の SSO の構成

**Fortes Change Cloud** 側でシングル サインオンを構成するには、**Fortes Change Cloud サポート チーム**に[アプリのフェデレーション メタデータ URL を](mailto:support@fortes.nl)送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Fortes Change Cloud のテスト ユーザーの作成

このセクションでは、Fortes Change Cloud で Britta Simon というユーザーを作成します。 [Fortes Change Cloud サポート チーム](mailto:support@fortes.nl)と協力して、Fortes Change Cloud プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

Fortes Change Cloud では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortes-change-cloud-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションを選択すると、ログイン フローを開始できる Fortes Change Cloud のサインオン URL にリダイレクトされます。
- Fortes Change Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fortes Change Cloud に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Fortes Change Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Fortes Change Cloud に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fortigate-ssl-vpn-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に FortiGate SSL VPN を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortigate-ssl-vpn-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: FortiGate SSL VPN と Microsoft Entra ID を統合するために実行する必要がある手順について説明します。

この記事では、FortiGate SSL VPN と Microsoft Entra ID を統合する方法について説明します。 FortiGate SSL VPN と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID を使用して、FortiGate SSL VPN にアクセスできるユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して FortiGate SSL VPN に自動的にサインインできるようにします。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な FortiGate SSL VPN。

### 記事の説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

FortiGate SSL VPN では、SP によって開始される SSO がサポートされます。

### ギャラリーから FortiGate SSL VPN を追加する

Microsoft Entra ID への FortiGate SSL VPN の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FortiGate SSL VPN を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「FortiGate SSL VPN**」と入力します。
4. 結果パネルで **FortiGate SSL VPN** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FortiGate SSL VPN の Microsoft Entra SSO の構成とテスト

B.Simon というテスト ユーザーを使用して、FortiGate SSL VPN に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと FortiGate SSL VPN の対応する SAML SSO ユーザー グループとの間にリンク関係を確立する必要があります。

FortiGate SSL VPN に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**して、ユーザーの機能を有効にします。
    1. **Microsoft Entra テスト ユーザーを作成** して、Microsoft Entra のシングル サインオンをテストします。
    2. **テスト ユーザーにアクセス権を付与** して、そのユーザーに対して Microsoft Entra シングル サインオンを有効にします。
2. アプリケーション側で **FortiGate SSL VPN SSO を構成**します。
    1. ユーザーの Microsoft Entra 表現に対応する **FortiGate SAML SSO ユーザー グループを作成**します。
3. **SSO をテスト** して、構成が機能することを確認します。

#### Microsoft Entra SSO の構成

Azure portal でこれらの手順を実行して、Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. [**管理**] セクションで、[&gt;&gt;**FortiGate SSL VPN** アプリケーション統合ページに移動し、**シングル サインオン**を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML を使用した単一 Sign-On のセットアップ**] ページで、[**基本的な SAML 構成**] の **[編集**] ボタンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] ページを示すスクリーンショット。]
5. [ **SAML を使用した単一 Sign-On の設定** ] ページで、次の値を入力します。

    a. [ **識別子** ] ボックスに、パターン `https://<FortiGate IP or FQDN address>:<Custom SSL VPN port>/remote/saml/metadata`に URL を入力します。

    b。 [ **応答 URL** ] ボックスに、パターン `https://<FortiGate IP or FQDN address>:<Custom SSL VPN port>/remote/saml/login`に URL を入力します。

    c. [ **サインオン URL** ] ボックスに、パターン `https://<FortiGate IP or FQDN address>:<Custom SSL VPN port>/remote/saml/login`に URL を入力します。

    d. [ **ログアウト URL** ] ボックスに、パターン `https://<FortiGate IP or FQDN address>:<Custom SSL VPN port>/remote/saml/logout`に URL を入力します。

    注

    これらの値は単なるパターンです。 FortiGate で構成されている実際の **サインオン URL**、 **識別子**、 **応答 URL**、 **ログアウト URL を** 使用する必要があります。 FortiGate のサポートでは、環境に適切な値を指定する必要があります。
6. FortiGate SSL VPN アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: [属性と要求] セクションを示すスクリーンショット。]
7. FortiGate SSL VPN に必要な要求を次の表に示します。 これらの要求の名前は、この記事の「 **FortiGate の実行」コマンド ライン構成** セクションで使用されている名前と一致している必要があります。 名前は大文字と小文字が区別されます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザー名 | user.userprincipalname |
    | グループ | ユーザー.グループ |

    これらの主張を追加で作成するには:

    a. **[ユーザー属性と要求] の**横にある **[編集**] を選択します。

    b。 [ **新しい要求の追加] を選択します**。

    c. **[名前]** にユーザー名を入力**します**。

    d. **ソース属性**の場合は、**user.userprincipalname** を選択します。

    e. **保存** を選択します。

    注

    **ユーザー属性と要求で** 許可されるグループ要求は 1 つだけです。 グループ要求を追加するには、要求に既に存在する既存のグループ要求 **user.groups [SecurityGroup] を** 削除して、新しい要求を追加するか、既存の要求を **[すべてのグループ**] に編集します。

    f. [ **グループ要求の追加] を選択します**。

    g. **[すべてのグループ]** を選択します。

    h. [ **詳細オプション**] で、[ **グループ要求の名前をカスタマイズする** ] チェック ボックスをオンにします。

    一. **[名前]** で、**グループ**を入力します。

    j. **保存** を選択します。
8. [**SAML を使用した単一 Sign-On のセットアップ**] ページの [**SAML 署名証明書**] セクションで、[**証明書 (Base64)]** の横にある **[ダウンロード**] リンクを選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. [ **FortiGate SSL VPN のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL を示すスクリーンショット。]

##### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon という名前のテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **を選択して**を作成します。

##### テスト ユーザーへのアクセス権の付与

このセクションでは、B.Simon に FortiGate SSL VPN へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
2. アプリケーションの一覧で [ **FortiGate SSL VPN**] を選択します。
3. アプリの概要ページの [ **管理** ] セクションで、[ **ユーザーとグループ**] を選択します。
4. **[ユーザー**追加] を選択し、次に **[割り当ての追加**] ダイアログボックスで **[ユーザーとグループ**] を選択します。
5. [**ユーザーとグループ**] ダイアログ ボックスで、[**ユーザー**] の一覧で **[B.Simon**] を選択し、画面の下部にある **[選択**] ボタンを選択します。
6. SAML アサーション内にロール値が必要な場合、 **[ロールの選択]** ダイアログ ボックスで、一覧からユーザーに適したロールを選択します。 画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログ ボックスで **[割り当て]** を選びます。

##### テスト ユーザーのセキュリティ グループを作成する

このセクションでは、テスト ユーザーの Microsoft Entra ID でセキュリティ グループを作成します。 FortiGate は、このセキュリティ グループを使用して、VPN 経由でユーザー ネットワーク アクセスを許可します。

1. Microsoft Entra 管理センターで、 **Entra ID**&gt;**Groups**&gt;**New グループ**に移動します。
2. **[新しいグループ**] プロパティで、次の手順を実行します。
    1. **[グループの種類]** リストで **[セキュリティ]** を選択します。
    2. [ **グループ名** ] ボックスに「 **FortiGateAccess**」と入力します。
    3. [ **グループの説明** ] ボックスに、「 **FortiGate VPN アクセスを許可するグループ**」と入力します。
    4. **Microsoft Entra ロールをグループ (プレビュー) 設定に割り当てることができる**場合は、[**いいえ**] を選択します。
    5. [ **メンバーシップの種類** ] ボックスで、[ **割り当て済み**] を選択します。
    6. **メンバー**の下で、**メンバーが選択されていない**を選択します。
    7. [**ユーザーとグループ**] ダイアログ ボックスで、[**ユーザー**] リストから **[B.Simon**] を選択し、画面の下部にある **[選択**] ボタンを選択します。
    8. **を選択して**を作成します。
3. Microsoft Entra ID の **[グループ** ] セクションに戻ったら、 **FortiGate Access** グループを見つけて **、オブジェクト ID をメモします**。後で必要になります。

#### FortiGate SSL VPN SSO の構成

##### Base64 SAML 証明書を FortiGate アプライアンスにアップロードする

テナントで FortiGate アプリの SAML 構成を完了したら、Base64 でエンコードされた SAML 証明書をダウンロードしました。 この証明書を FortiGate アプライアンスにアップロードする必要があります。

1. FortiGate アプライアンスの管理ポータルにサインインします。
2. 左側のウィンドウで、[ **システム**] を選択します。
3. [ **システム**] で [証明書] を選択 **します**。
4. **[Import](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インポート)**&gt;**[Remote Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/リモート証明書)** の順に選択します。
5. Azure テナントの FortiGate アプリデプロイからダウンロードした証明書を参照して選択し、[ **OK] を選択します**。

証明書がアップロードされたら、 **System**&gt;**Certificates**&gt;**Remote Certificate** の名前を書き留めます。 既定では、REMOTE\_Cert\_*N* という名前で、 *N* は整数値です。

##### FortiGate コマンド ライン構成の完了

FortiOS 7.0 以降は GUI から SSO を構成できますが、CLI 構成はすべてのバージョンに適用されるため、ここに示します。

これらの手順を完了するには、前に記録した値が必要です。

| FortiGate SAML CLI の設定 | 同等の Azure 構成 |
| --- | --- |
| SP エンティティ ID (`entity-id`) | 識別子 (エンティティ ID) |
| SP シングル サインオン URL (`single-sign-on-url`) | 応答 URL (Assertion Consumer Service URL) |
| SP シングル サインアウト URL (`single-logout-url`) | サインアウト URL |
| IdP エンティティ ID (`idp-entity-id`) | Microsoft Entra 識別子 |
| IdP シングル サインオン URL (`idp-single-sign-on-url`) | Azure サインイン URL |
| IdP シングル サインアウト URL (`idp-single-logout-url`) | Azure サインアウト URL |
| IdP 証明書 (`idp-cert`) | Base64 SAML 証明書名 (REMOTE\_Cert\_N) |
| ユーザー名属性 (`user-name`) | ユーザー名 |
| グループ名属性 (`group-name`) | グループ |

注

基本的な SAML 構成の下のサインオン URL は、FortiGate 構成では使用されません。 これは、SP によって開始されるシングル サインオンをトリガーして、ユーザーを SSL VPN ポータル ページにリダイレクトするために使用されます。

1. FortiGate アプライアンスへの SSH セッションを確立し、FortiGate 管理者アカウントでサインインします。
2. これらのコマンドを実行し、 `<values>` を前に収集した情報に置き換える:

    ```console
    config user saml
      edit azure
        set cert <FortiGate VPN Server Certificate Name>
        set entity-id < Identifier (Entity ID)Entity ID>
        set single-sign-on-url < Reply URL Reply URL>
        set single-logout-url <Logout URL>
        set idp-entity-id <Azure AD Identifier>
        set idp-single-sign-on-url <Azure Login URL>
        set idp-single-logout-url <Azure Logout URL>
        set idp-cert <Base64 SAML Certificate Name>
        set user-name username
        set group-name group
      next
    end
    ```

##### グループの照合用に FortiGate を構成する

このセクションでは、テスト ユーザーを含むセキュリティ グループのオブジェクト ID を認識するように FortiGate を構成します。 この構成により、FortiGate はグループ メンバーシップに基づいてアクセスの決定を行うことができます。

これらの手順を完了するには、この記事で前に作成した FortiGateAccess セキュリティ グループのオブジェクト ID が必要です。

1. FortiGate アプライアンスへの SSH セッションを確立し、FortiGate 管理者アカウントでサインインします。
2. これらのコマンドを実行します。

    ```console
    config user group
      edit FortiGateAccess
        set member azure
        config match
          edit 1
            set server-name azure
            set group-name <Object Id>
          next
        end
      next
    end
    ```

##### FortiGate VPN ポータルとファイアウォール ポリシーを作成する

このセクションでは、この記事で前に作成した FortiGateAccess セキュリティ グループへのアクセスを許可する FortiGate VPN ポータルとファイアウォール ポリシーを構成します。

手順については、「 [SAML IdP として機能する Microsoft Entra ID を使用した SSL VPN の SAML SSO サインインの構成」を参照してください](https://docs.fortinet.com/document/fortigate-public-cloud/7.0.0/azure-administration-guide/584456/configuring-saml-sso-login-for-ssl-vpn-web-mode-with-azure-ad-acting-as-saml-idp)。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Azure SSO 構成の手順 5) で、*アプリでシングル サインオンをテストする*場合には、**[テスト]** ボタンを選択します。 このオプションは、サインイン フローを開始できる FortiGate VPN のサインオン URL にリダイレクトします。
- FortiGate VPN のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [FortiGate VPN] タイルを選択すると、このオプションは FortiGate VPN のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fortisase-sia-tutorial"} -->
## Microsoft Entra ID で FortiSASE for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortisase-sia-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FortiSASE の間のシングル サインオンを構成する方法について説明します。

この記事では、FortiSASE と Microsoft Entra ID を統合する方法について説明します。 FortiSASE を Microsoft Entra ID と統合すると、次のことが可能になります。

- FortiSASE にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで FortiSASE に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な FortiSASE サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FortiSASE では、 **SP** Initiated SSO がサポートされます。
- FortiSASE では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの FortiSASE の追加

Microsoft Entra ID への FortiSASE の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に FortiSASE を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「FortiSASE**」と入力します。
4. 結果パネルから **FortiSASE** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FortiSASE 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、FortiSASE に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと FortiSASE の関連ユーザーとの間にリンク関係を確立する必要があります。

FortiSASE に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FortiSASE SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FortiSASE テストユーザーの作成** - FortiSASE で B.Simon に対応するテストユーザーを作成し、それを Microsoft Entra のユーザー表示にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**FortiSASE**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | ユーザー | URL |
    | --- | --- |
    | FortiSASE VPN ユーザー SSO の場合 | `https://<TENANTHOSTNAME>.edge.prod.fortisase.com/remote/saml/metadata` |
    | FortiSASE SWG ユーザー SSO の場合 | `https://<TENANTHOSTNAME>.edge.prod.fortisase.com:7831/XX/YY/ZZ/saml/metadata` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | ユーザー | URL |
    | --- | --- |
    | FortiSASE VPN ユーザー SSO の場合 | `https://<TENANTHOSTNAME>.edge.prod.fortisase.com/remote/saml/login` |
    | FortiSASE SWG ユーザー SSO の場合 | `https://<TENANTHOSTNAME>.edge.prod.fortisase.com:7831/XX/YY/ZZ/saml/login` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | ユーザー | URL |
    | --- | --- |
    | FortiSASE VPN ユーザー SSO の場合 | `https://<TENANTHOSTNAME>.edge.prod.fortisase.com/remote/login` |
    | FortiSASE SWG ユーザー SSO の場合 | `https://<TENANTHOSTNAME>.edge.prod.fortisase.com:7831/XX/YY/ZZ/login` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 FortiSASE ポータルで、 **Configuration &gt; VPN User SSO** または **Configuration &gt; SWG User SSO** に移動して、サービス プロバイダーの URL を見つけます。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. FortiSASE アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、FortiSASE アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を以下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
    | ユーザー名 | user.userprincipalname |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **FortiSASE のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FortiSASE の SSO の構成

1. FortiSASE の企業サイトに管理者としてログインします。
2. 使用される FortiSASE モードに応じて、[**Configuration &gt; VPN User SSO** **] または [Configuration &gt; SWG User SSO**] に移動します。
3. [ **ID プロバイダーの構成** ] セクションで、次の URL をコピーし **、[基本的な SAML 構成]** セクションに貼り付けます。

    [Image: 構成を示すスクリーンショット]
4. [ **サービス プロバイダーの構成]** セクションで、次の手順を実行します。

    [Image: サービスプロバイダーの構成を示すスクリーンショット]

    a. **[IdP エンティティ ID**] ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    b。 **[IdP Single Sign-On URL**] ボックスに、先にコピーした**ログイン URL** の値を貼り付けます。

    c. **[IdP Single Log-Out URL**] ボックスに、前にコピーした**ログアウト URL** の値を貼り付けます。

    d. ダウンロードした **証明書 (Base64)** をメモ帳で開き、[ **IdP 証明書** ] テキストボックスにコンテンツをアップロードします。
5. 構成を確認して送信します。

#### FortiSASE のテスト ユーザーの作成

FortiSASE では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる FortiSASE サインオン URL にリダイレクトされます。
- FortiSASE のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [FortiSASE] タイルを選択すると、このオプションは FortiSASE のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fortiweb-web-application-firewall-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に FortiWeb Web Application Firewall を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fortiweb-web-application-firewall-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と FortiWeb Web Application Firewall の間にシングル サインオンを構成する方法について説明します。

この記事では、FortiWeb Web Application Firewall と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と FortiWeb Web Application Firewall を統合すると、次のことができます:

- FortiWeb Web Application Firewall にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して FortiWeb Web Application Firewall に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

FortiWeb Web Application Firewallは、次の [一次クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FortiWeb Web Application Firewall でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FortiWeb Web Application Firewall では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの FortiWeb Web Application Firewall の追加

FortiWeb Web Application Firewall の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に FortiWeb Web Application Firewall を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスしてください。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「FortiWeb Web Application Firewall**」と入力します。
4. 結果パネルから **FortiWeb Web Application Firewall** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FortiWeb Web Application Firewall の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、FortiWeb Web Application Firewall に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと FortiWeb Web Application Firewall の関連ユーザーとの間にリンク関係を確立する必要があります。

FortiWeb Web Application Firewall に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FortiWeb Web Application Firewall の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **FortiWeb Web Application Firewall のテストユーザーを作成する** - Microsoft Entra における B.Simon に対応するユーザーを FortiWeb Web Application Firewall で作成し、その B.Simon とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**FortiWeb Web Application Firewall**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] のペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.<CUSTOMER_DOMAIN>.com`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.<CUSTOMER_DOMAIN>.com/<FORTIWEB_NAME>/saml.sso/SAML2/POST`
    3. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.<CUSTOMER_DOMAIN>.com`
    4. [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.<CUSTOMER_DOMAIN>.info/<FORTIWEB_NAME>/saml.sso/SLO/POST`

    注

    `<FORTIWEB_NAME>` は、後で FortiWeb に構成を指定するときに使用される名前識別子です。 実際の URL 値を取得するには、 [FortiWeb Web Application Firewall サポート チーム](mailto:support@fortinet.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FortiWeb Web Application Firewall の SSO の構成

1. `https://<address>:8443` に移動します。ここで `<address>` は、FortiWeb VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. FortiWeb VM のデプロイ中に指定された管理者資格情報を使用してサインインします。
3. 次のページで、以下の手順を実行します。

    [Image: SAML サーバー ページのスクリーンショット]

    ある。 左側のメニューで、[ユーザー] を選択 **します**。

    b。 [ユーザー] で [ **リモート サーバー**] を選択します。

    c. **[SAML サーバー] を選択します**。

    ｄ。 [ **新規作成] を選択します**。

    え [ **名前** ] フィールドで、[Microsoft Entra ID の構成] セクションで使用する `<fwName>` の値を指定します。

    f. [ **エンティティ ID** ] ボックスに、 **識別子 (エンティティ ID)** の値を入力します(例: `https://www.<CUSTOMER_DOMAIN>.com/samlsp`

    ジー [ **メタデータ**] の横にある [ **ファイルの選択** ] を選択し、ダウンロードした **フェデレーション メタデータ XML** ファイルを選択します。

    h. [ **OK] を選択します**。

#### サイト公開ルールの作成

1. `https://<address>:8443` に移動します。ここで `<address>` は、FortiWeb VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. FortiWeb VM のデプロイ中に指定された管理者資格情報を使用してサインインします。
3. 次のページで、以下の手順を実行します。

    [Image: サイト発行規則のスクリーンショット]

    ある。 左側のメニューで、[ **アプリケーション配信**] を選択します。

    b。 [ **アプリケーションの配信**] で、[ **サイトの発行**] を選択します。

    c. [ **サイトの発行]** で、[ **サイトの発行**] を選択します。

    ｄ。 **サイト公開ルール**を選択します。

    え [ **新規作成] を選択します**。

    f. サイト公開ルールの名前を指定します。

    ジー **[発行済みサイトの種類]** の横にある [正規表現] を選択**します**。

    一. **[発行済みサイト]** の横に、発行する Web サイトのホスト ヘッダーと一致する文字列を指定します。

    j. **[パス**] の横に /を指定します。

    ケー [ **クライアント認証方法] の**横にある [ **SAML 認証**] を選択します。

    l. **[SAML サーバー]** ドロップダウンで、先ほど作成した SAML サーバーを選択します。

    m. [ **OK] を選択します**。

#### サイト公開ポリシーの作成

1. `https://<address>:8443` に移動します。ここで `<address>` は、FortiWeb VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. FortiWeb VM のデプロイ中に指定された管理者資格情報を使用してサインインします。
3. 次のページで、以下の手順を実行します。

    [Image: サイト発行ポリシーのスクリーンショット]

    ある。 左側のメニューで、[ **アプリケーション配信**] を選択します。

    b。 [ **アプリケーションの配信**] で、[ **サイトの発行**] を選択します。

    c. [ **サイトの発行]** で、[ **サイトの発行**] を選択します。

    ｄ。 **サイト発行ポリシー**を選択します。

    え [ **新規作成] を選択します**。

    f. サイト公開ポリシーの名前を指定します。

    ジー [ **OK] を選択します**。

    h. [ **新規作成] を選択します**。

    一. [ **ルール** ] ドロップダウンで、先ほど作成したサイト発行ルールを選択します。

    j. [ **OK] を選択します**。

#### Web 保護プロファイルの作成と割り当て

1. `https://<address>:8443` に移動します。ここで `<address>` は、FortiWeb VM に割り当てられている FQDN またはパブリック IP アドレスです。
2. FortiWeb VM のデプロイ中に指定された管理者資格情報を使用してサインインします。
3. 左側のメニューで、[ポリシー] を選択 **します**。
4. [ **ポリシー**] で、[ **Web 保護プロファイル**] を選択します。
5. [ **インライン標準保護** ] を選択し、[ **複製** ] を選択します。
6. 新しい Web 保護プロファイルの名前を指定し、[ **OK] を選択します**。
7. 新しい Web 保護プロファイルを選択し、[ **編集]** を選択します。
8. [ **サイトの発行]** の横で、先ほど作成したサイト発行ポリシーを選択します。
9. [ **OK] を選択します**。

    [Image: サイト公開用のスクリーンショット]
10. 左側のメニューで、[ポリシー] を選択 **します**。
11. [ **ポリシー**] で、[ **サーバー ポリシー**] を選択します。
12. Microsoft Entra ID を使用して認証したい Web サイトの公開に使用されるサーバー ポリシーを選択します。
13. [ **編集] を選択します**。
14. [ **Web 保護プロファイル]** ドロップダウンで、先ほど作成した Web 保護プロファイルを選択します。
15. [ **OK] を選択します**。
16. FortiWeb の Web サイト公開先となる外部 URL に対するアクセスを試行します。 認証のために Microsoft Entra ID にリダイレクトされます。

#### FortiWeb Web Application Firewall テスト ユーザーの作成

このセクションでは、FortiWeb Web Application Firewall で Britta Simon というユーザーを作成します。 [FortiWeb Web Application Firewall サポート チーム](mailto:support@fortinet.com)と協力して、FortiWeb Web Application Firewall プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる FortiWeb Web アプリケーションのサインオン URL にリダイレクトされます。
- FortiWeb Web Application のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [FortiWeb Web アプリケーション] タイルを選択すると、このオプションは FortiWeb Web アプリケーションのサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/foundu-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に foundU を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/foundu-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と foundU の間のシングル サインオンを構成する方法について説明します。

この記事では、foundU と Microsoft Entra ID を統合する方法について説明します。 foundU を Microsoft Entra ID と統合すると、次のことが可能になります。

- foundU にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで foundU に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- foundU でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- foundU では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの foundU の追加

Microsoft Entra ID への foundU の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に foundU を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**foundU**」と入力します。
4. 結果パネルから **[foundU]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### foundU 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、foundU に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと foundU の関連ユーザーとの間にリンク関係を確立する必要があります。

foundU に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **foundU SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **foundUでのテストユーザー作成** - Microsoft Entra における B.Simon の対応として foundU にユーザーを作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**foundU**&gt;**シングルサインオン**に移動する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.foundu.com.au/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.foundu.com.au/saml/consume`

    c. [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.foundu.com.au/saml/logout`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、ログアウト URL でこれらの値を更新します。 この値を取得するには、[foundU クライアント サポート チーム](mailto:help@foundu.com.au)にお問い合わせください。 Azure portal の [ **基本的な SAML 構成** ] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up foundU](foundU の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### foundU SSO の構成

1. foundU の Web サイトに管理者としてログインします。
2. メニュー アイコンを選択し **、[プラットフォーム設定]** で [ **シングル サインオン**] を選択します。

    [Image: foundU シングル サインオンのスクリーンショット]
3. **[Single Sign-on Settings](シングル サインオンの設定)** ページで次の手順を実行します。

    [Image: foundU SSO 構成のスクリーンショット]

    a. **識別子 (エンティティ ID)** の値をコピーし、この値を Azure portal の **[基本的な SAML 構成] セクション**の **[識別子**] テキスト ボックスに貼り付けます。

    b。 **応答 URL (Assertion Consumer Service URL)** の値をコピーし、この値を Azure portal の **[基本的な SAML 構成] セクション**の **[応答 URL**] テキスト ボックスに貼り付けます。

    c. **ログアウト URL** の値をコピーし、この値を Azure portal の **[基本的な SAML 構成] セクション**の [**ログアウト URL**] テキスト ボックスに貼り付けます。

    d. [ **エンティティ ID** ] ボックスに、Azure portal からコピーした **識別子** の値を貼り付けます。

    e. **[シングル サインオン サービス URL**] ボックスに、Azure portal からコピーした**ログイン URL** の値を貼り付けます。

    f. **[Single Logout Service URL**] ボックスに、Azure portal からコピーした**ログアウト URL** の値を貼り付けます。

    g. [ **ファイルの選択] を選択** して、Azure portal からダウンロードした **証明書 (Base64)** ファイルをアップロードします。

    h. [ **設定の保存] を選択します**。

#### foundU のテスト ユーザーの作成

このセクションでは、foundU で Britta Simon というユーザーを作成します。 [foundU サポート チーム](mailto:help@foundu.com.au)と連携して、foundU プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Azure portal で **[このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる foundU サインオン URL にリダイレクトします。
- foundU のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Azure portal で **[このアプリケーションをテスト** する] を選択すると、SSO を設定した foundU に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで foundU タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した foundU に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fountain-tutorial"} -->
## Microsoft Entra ID で Fountain for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fountain-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fountain の間のシングル サインオンを構成する方法について説明します。

この記事では、Fountain を Microsoft Entra ID と統合する方法について説明します。 Fountain のオールインワン大量採用プラットフォームは、世界をリードする企業がスマートで高速かつシームレスな採用を通じて適切な人材を見つけられるよう支援します。 Fountain を Microsoft Entra ID と統合すると、次のことができます。

- Fountain にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Fountain に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Fountain 向けの Microsoft Entra のシングル サインオンを構成してテストする。 Fountain では、**SP** と **IDP** Initiated の両方のシングル サインオンと、**Just In Time** ユーザー プロビジョニングがサポートされています。

### [前提条件]

Fountain を Microsoft Entra ID と統合するためには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Fountain のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Fountain アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Fountain を追加する

Microsoft Entra アプリケーション ギャラリーから Fountain を追加して、Fountain でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Fountain]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<CustomerUniqueId>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://fountain.okta.com/sso/saml2/<CustomerUniqueId>`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://fountain.okta.com/sso/saml2/<CustomerUniqueId>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Fountain クライアント サポート チーム](mailto:support@fountain.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Fountain アプリケーションは、特定の形式で構成された SAML アサーションを受け入れるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、Fountain アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を以下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Set up Fountain](Fountain の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Fountain SSO を構成する

**Fountain** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Fountain サポート チーム](mailto:support@fountain.com)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Fountain テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Fountain に作成します。 Fountain では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Fountain にユーザーがまだ存在していない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、ログイン フローを開始できる Fountain のサインオン URL にリダイレクトされます。
- Fountain のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fountain に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Fountain] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Fountain に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fourkites-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に FourKites SAML2.0 SSO for Tracking を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fourkites-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FourKites SAML2.0 SSO for Tracking の間にシングル サインオンを構成する方法について説明します。

この記事では、FourKites SAML2.0 SSO for Tracking と Microsoft Entra ID を統合する方法について説明します。 FourKites SAML2.0 SSO for Tracking を Microsoft Entra ID と統合すると、次のことが可能になります。

- FourKites SAML2.0 SSO for Tracking にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って FourKites SAML2.0 SSO for Tracking に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- FourKites SAML2.0 SSO for Tracking でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FourKites SAML2.0 SSO for Tracking は、**SP** および **IDP** initiated SSO をサポートします。
- FourKites SAML2.0 SSO for Tracking では、**ジャストインタイム** ユーザー プロビジョニングがサポートされます。

### ギャラリーから FourKites SAML2.0 SSO for Tracking を追加する

FourKites SAML2.0 SSO for Tracking の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリ リストに FourKites SAML2.0 SSO for Tracking を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;から**エンタープライズアプリ**&gt;と**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「FourKites SAML2.0 SSO for Tracking」**と入力します。
4. 結果パネルから **[FourKites SAML2.0 SSO for Tracking]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FourKites SAML2.0 SSO for Tracking 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、FourKites SAML2.0 SSO for Tracking に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと FourKites SAML2.0 SSO for Tracking の関連ユーザーとの間にリンク関係を確立する必要があります。

FourKites SAML2.0 SSO for Tracking に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FourKites SAML2.0 SSO for Tracking SSO の**構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **FourKites SAML2.0 SSO for Tracking テストユーザーの作成** - FourKites SAML2.0 SSO for Tracking における B.Simon の対応ユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[FourKites SAML2.0 SSO for Tracking]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://upsgff.fourkites.com` |
    | `https://upsgff-staging.fourkites.com` |
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FourKites SAML2.0 SSO for Tracking SSO を構成する

**FourKites SAML2.0 SSO for Tracking** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[FourKites SAML2.0 SSO for Tracking サポート チーム](mailto:support@fourkites.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FourKites SAML2.0 SSO for Tracking テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを FourKites SAML2.0 SSO for Tracking に作成します。 FourKites SAML2.0 SSO for Tracking は Just-In-Time ユーザー プロビジョニングをサポートしています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 FourKites SAML2.0 SSO for Tracking にまだユーザーが存在しない場合、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

#### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる FourKites SAML2.0 SSO for Tracking のサインオン URL にリダイレクトされます。
- [FourKites SAML2.0 SSO for Tracking Sign-on URL] (FourKites SAML2.0 SSO for Tracking サインオン URL) に直接移動し、そこからログイン フローを開始します。

#### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した FourKites SAML2.0 SSO for Tracking に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [FourKites SAML2.0 SSO for Tracking] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した FourKites SAML2.0 SSO for Tracking に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/framer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Framer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/framer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Framer の間のシングル サインオンを構成する方法について説明します。

この記事では、Framer と Microsoft Entra ID を統合する方法について説明します。 Framer を Microsoft Entra ID と統合すると、次のことが可能になります。

- Framer にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Framer に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Framer でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Framer では、**SP Initiated SSO** と **IDP Initiated SSO** がサポートされます。

### ギャラリーからの Framer の追加

Microsoft Entra ID への Framer の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Framer を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Framer**」と入力します。
4. 結果パネルから **Framer** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Framer に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Framer に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Framer の関連ユーザーとの間にリンク関係を確立する必要があります。

Framer に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Framer SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Framer テスト ユーザーの作成** - Framer で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Framer]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.framer.com/auth/saml/callback/<ID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.framer.com/auth/saml/callback/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.framer.com/auth/saml/callback/<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Framer クライアント サポート チーム](mailto:support@framer.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Framer のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 設定に適した URL をコピーする様子を示したスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Framer SSO の構成

**Framer** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Framer サポート チーム](mailto:support@framer.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Framer のテスト ユーザーの作成

このセクションでは、Framer で Britta Simon というユーザーを作成します。 [Framer サポート チーム](mailto:support@framer.com)と協力して、Framer プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Framer のサインオン URL にリダイレクトされます。
- Framer のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Framer に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Framer] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Framer に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/frankli-io-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に frankli を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/frankli-io-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから frankli にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために frankli と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループの [frankli](https://www.frankli.io/) への自動プロビジョニングおよび解除を行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- frankli でユーザーを作成する。
- アクセスが不要になった場合は、frankli のユーザーを削除します。
- Microsoft Entra IDと frankli の間でユーザー属性の同期を維持します。
- frankli に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)する。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとfrankliの間で[マッピングするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように frankli を構成する

1. 管理者アカウントを使用して [frankli](https://beta.frankli.io/login) にサインインします。
2. **Admin -&gt; Integrations -&gt; Microsoft Entra ID** に移動します。 [Image: Active Directory Setup]
3. [ **ディレクトリのセットアップ] を選択します**。
4. 新しい外部ディレクトリの名前を定義します。 [Image: Active Directory Name]
5. [ **ディレクトリの作成] を選択します**。 [Image: Active Directory Details]
6. **ベース URL** と**ベアラー トークン**を書き留めます。**[テナント URL**] フィールドにベース **URL** が入力されます。 **ベアラー トークン**が **[シークレット トークン**] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから frankli を追加する

Microsoft Entra アプリケーション ギャラリーから frankli を追加して、frankli へのプロビジョニングの管理を開始します。 以前に、SSO 用に frankli を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: frankli への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーとグループの割り当てに基づいて frankli でユーザーとグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで frankli の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[frankli]** を選択します。

    [Image: アプリケーションの一覧の frankli リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、frankli テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが frankli に接続できることを確認します。 接続に失敗した場合は、frankli アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから frankli に同期されるユーザー属性を確認します。 "**照合**" プロパティとして選択されている属性は、更新処理で frankli のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、frankli API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | frankli で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | addresses[type eq "work"].フォーマット済み | 糸 |  | ✓ |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  | ✓ |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  | ✓ |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  | ✓ |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  | ✓ |
    | タイトル | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/freedcamp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Freedcamp を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freedcamp-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Freedcamp との間でシングル サインオンを構成する方法について説明します。

この記事では、Freedcamp と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に Freedcamp を統合すると、次の利点が得られます。

- どのユーザーが Freedcamp にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して Freedcamp に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Freedcamp は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Freedcamp でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Freedcamp では、**SP Initiated SSO と IDP Initiated SSO** をサポートしています。

### ギャラリーからの Freedcamp の追加

Microsoft Entra ID に Freedcamp を統合するには、マネージド SaaS アプリの一覧にギャラリーから Freedcamp を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで検索ボックスに「**Freedcamp**」と入力します。
4. 結果パネルから「**Freedcamp**」を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Freedcamp 向けに Microsoft Entra の SSO を構成してテストする

**Britta Simon** というテスト ユーザーを使用して、Freedcamp で Microsoft Entra の SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Freedcamp の関連ユーザーとの間にリンク関係を確立する必要があります。

Freedcamp で Microsoft Entra の SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Freedcamp の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Freedcamp のテスト ユーザーの作成** - Freedcamp で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Freedcamp** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.freedcamp.com/sso/<UNIQUEID>`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.freedcamp.com/sso/acs/<UNIQUEID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.freedcamp.com/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 ユーザーは、顧客ドメインについては URL 値を入力することもできます。これは必ずしも `freedcamp.com` のパターンに従っている必要はなく、顧客のアプリケーション インスタンスに固有の顧客ドメインに固有な任意の値を入力できます。 URL のパターンの詳細については、[Freedcamp クライアント サポート チーム](mailto:devops@freedcamp.com)にお問い合わせいただくことも可能です。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Freedcamp のセットアップ]** セクションで、ご自分の要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Freedcamp SSO の構成

1. Web ブラウザーの別のウィンドウで、自社の Freedcamp 企業サイトに管理者としてサインインします。
2. ページの右上隅にある **プロファイル** を選択し、[ **マイ アカウント**] に移動します。

    [Image: 選択されている [プロファイル] と [マイ アカウント] 示すスクリーンショット。]
3. メニュー バーの左側から SSO を選択し、[ **SSO** **接続** ] ページで次の手順を実行します。

    [Image: 左側のメニュー バーで選択されている [S S O] と、値が入力され、[送信] ボタンが選択されている [Your S S O connections](お使いの S S O 接続) ページを示すスクリーンショット。]

    a. **[タイトル]** テキスト ボックスに、タイトルを入力します。

    b。 **[エンティティ ID]** テキスト ボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    c. **[ログイン URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    d. Base64 でエンコードされた証明書をメモ帳で開き、その内容をコピーして **[証明書]** テキスト ボックスに貼り付けます。

    e. **送信**を選択します。

#### Freedcamp テスト ユーザーの作成

Microsoft Entra ユーザーが Freedcamp にサインインできるようにするには、そのユーザーを Freedcamp にプロビジョニングする必要があります。 Freedcamp では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. 別の Web ブラウザー ウィンドウで、Freedcamp にセキュリティ管理者としてサインインします。
2. ページの右上隅にある **プロファイル** を選択し、[ **システムの管理**] に移動します。

    [Image: Freedcamp の構成]
3. [Manage System](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システムの管理) ページの右側で、次の手順を実行します。

    [Image: 選択されている [Add or invite Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加または招待) ボタン、強調表示されている [Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール) フィールド、選択されている [Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ボタン示すスクリーンショット。]

    a. [ **ユーザーの追加または招待]** を選択します。

    b。 **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** テキスト ボックスに、`Brittasimon@contoso.com` などユーザーのメール アドレスを入力します。

    c. [ **ユーザーの追加] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Freedcamp のサインオン URL にリダイレクトされます。
- Freedcamp のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Freedcamp に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Freedcamp] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Freedcamp に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/freight-audit-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Freight Audit を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freight-audit-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-19
- Summary: Microsoft Entra ID と Freight Audit の間でシングル サインオンを構成する方法について説明します。

この記事では、Freight Audit と Microsoft Entra ID を統合する方法について説明します。 Freight Audit と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID でフレート監査へのアクセス権を管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Freight Audit に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Freight Audit でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Freight Audit では、 **IDP** Initiated SSO がサポートされます。
- Freight Audit では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Freight Audit の追加

Microsoft Entra ID への Freight Audit の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Freight Audit を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに「**Freight Audit**」と入力します。
4. 結果パネルから **[Freight Audit** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Freight Audit の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Freight Audit に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Freight Audit の関連ユーザーとの間にリンク関係を確立する必要があります。

Freight Audit に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Freight Audit SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Freight Audit のテストユーザーを作成する** - Freight Audit 内で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現につなげます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Freight Audit**&gt;**シングル サインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    `https://login.controlpay.com/identifier/saml2/<company>` またはフレート監査が提案した他のいかなる値。

    1. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    `https://login.controlpay.com/reply/saml2/<company>` またはフレート監査が提案した他のいかなる値。

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 値を取得するには、 [Freight Audit サポート チーム](mailto:tp_fa_sso-ug@trimble.com) に問い合わせてください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Freight Audit SSO の構成

**Freight Audit** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Freight Audit サポート チーム](mailto:tp_fa_sso-ug@trimble.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Freight Audit テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Freight Audit に作成します。 Freight Audit では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Freight Audit に存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した Freight Audit に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Freight Audit] タイルを選択すると、SSO を設定した Freight Audit に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/freightender-sso-for-trp-tender-response-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Freightender SSO for TRP (Tender Response Platform) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freightender-sso-for-trp-tender-response-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Freightender SSO for TRP (Tender Response Platform) の間でシングル サインオンを構成する方法について説明します。

この記事では、Freightender SSO for TRP (Tender Response Platform) と Microsoft Entra ID を統合する方法について説明します。 Freightender SSO for TRP (Tender Response Platform) と Microsoft Entra ID を統合すると、次のことができます。

- TRP（Tender Response Platform）用のFreightender SSOへのアクセス権を有するユーザーをMicrosoft Entra IDで制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Freightender SSO for TRP (Tender Response Platform) に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Freightender SSO for TRP (Tender Response Platform) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Freightender SSO for TRP (Tender Response Platform) では、**SP開始SSO**と**IDP開始SSO**の両方がサポートされます。
- Freightender SSO for TRP (Tender Response Platform) では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### TRP (Tender Response Platform) 用の Freightender SSO をギャラリーから追加する

Microsoft Entra ID への Freightender SSO for TRP (Tender Response Platform) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Freightender SSO for TRP (Tender Response Platform) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Freightender SSO for TRP (Tender Response Platform)」**と入力します。
4. 結果パネルから **Freightender SSO for TRP (Tender Response Platform)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO を使用して TRP（テンダー応答プラットフォーム）用の Freightender SSO を構成およびテストします。

**B.Simon** というテスト ユーザーを使用して、Freightender SSO for TRP (Tender Response Platform) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Freightender SSO for TRP (Tender Response Platform) の関連ユーザーとの間にリンク関係を確立する必要があります。

Freightender SSO for TRP (Tender Response Platform) に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Freightender SSO for TRP (Tender Response Platform) SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Freightender SSO for TRP (Tender Response Platform) のテストユーザーを作成する** - Freightender SSO for TRP (Tender Response Platform) で B.Simon に対応するユーザーを作成し、それを Microsoft Entra の B.Simon にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Freightender SSO for TRP (Tender Response Platform)**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの URL/パターンを入力します。

    | **識別子** |
    | --- |
    | `https://trp.freightender.com` |
    | `https://trp-dev.freightender.com` |
    | `https://<SUBDOMAIN>.freightender.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL/パターンを入力します。

    | **応答 URL** |
    | --- |
    | `https://trp.freightender.com` |
    | `https://trp-dev.freightender.com` |
    | `https://<SUBDOMAIN>.freightender.com` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL/パターンを入力します。

    | **サインオン URL** |
    | --- |
    | `https://trp.freightender.com` |
    | `https://trp-dev.freightender.com` |
    | `https://<SUBDOMAIN>.freightender.com` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Freightender SSO for TRP (Tender Response Platform) サポート チーム](mailto:support@freightender.com) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **TRP (Tender Response Platform) の Freightender SSO の設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TRP (Tender Response Platform) SSOのためのFreightender SSO設定の構成

**Freightender SSO for TRP (Tender Response Platform)** 側でシングルサインオンを構成するには、ダウンロードした**証明書 (未加工)**と Microsoft Entra 管理センターからコピーした適切な URL を Freightender SSO for TRP (Tender Response Platform) サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TRP (Tender Response Platform) テスト ユーザーの Freightender SSO の作成

このセクションでは、Britta Simon というユーザーを Freightender SSO for TRP (Tender Response Platform) に作成します。 Freightender SSO for TRP (Tender Response Platform) では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Freightender SSO for TRP (Tender Response Platform) に存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Freightender SSO for TRP (Tender Response Platform) のサインオン URL にリダイレクトします。
- Freightender SSO for TRP (Tender Response Platform) のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Freightender SSO for TRP (Tender Response Platform) に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Freightender SSO for TRP (Tender Response Platform)] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Freightender SSO for TRP (Tender Response Platform) に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fresh-relevance-tutorial"} -->
## Microsoft Entra ID でシングルサインオン用のFresh Relevanceを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fresh-relevance-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fresh Relevance の間のシングル サインオンを構成する方法について説明します。

この記事では、Fresh Relevance と Microsoft Entra ID を統合する方法について説明します。 Fresh Relevance を Microsoft Entra ID と統合すると、次のことが可能になります。

- Fresh Relevance にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Fresh Relevance に自動的にサインインできるように設定する。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fresh Relevance でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fresh Relevance では、**IDP** Initiated SSO がサポートされます。
- Fresh Relevance では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Fresh Relevance の追加

Microsoft Entra ID への Fresh Relevance の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Fresh Relevance を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Fresh Relevance**」と入力します。
4. 結果パネルから **[Fresh Relevance]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fresh Relevance 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Fresh Relevance に対する Microsoft Entra SSO を構成およびテストするします。 SSO が機能するには、Microsoft Entra ユーザーと Fresh Relevance の関連ユーザーとの間にリンク関係を確立する必要があります。

Fresh Relevance に対する Microsoft Entra SSO を構成およびテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fresh Relevance SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fresh Relevance のテスト ユーザーの作成** - Fresh Relevance で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップを実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Fresh Relevance**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイル]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: 画像]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**と**応答 URL** の値が、[基本的な SAML 構成] セクションに自動的に設定されます。

    Note

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。

    d. **[リレー状態]** ボックスに、次の形式で値を入力します: `<ID>`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fresh Relevance SSO の構成

1. 別の Web ブラウザー ウィンドウで、Fresh Relevance 企業サイトに管理者としてサインインします。
2. **[設定]**&gt;&gt;] に移動し、[**SAML/Azure AD シングル サインオン**] を選択します。
3. **[SAML/Single Sign-On Configuration]** ページで、[**このアカウントの SAML SSO を有効にする**] チェック ボックスをオンにして、[**Create new IdP Configuration]\(新しい IdP 構成の作成**\) ボタンを選択します。

    [Image: 新しい IdP 構成の作成を示すスクリーンショット。]
4. **[SAML IdP Configuration](SAML IdP 構成)** ページで、次の手順を実行します。

    [Image: [SAML IdP Configuration](SAML IdP 構成) ページを示すスクリーンショット。]

    [Image: IdP メタデータ XML を示すスクリーンショット。]

    a. **[エンティティ ID]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[識別子 (エンティティ ID)]** テキスト ボックスにこの値を貼り付けます。

    b。 **[Assertion Consumer Service (ACS)] URL** の値をコピーし、その値を **[基本的な SAML 構成]** セクションの **[応答 URL]** テキスト ボックスに貼り付けます。

    c. **[RelayState 値]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[リレー状態]** テキスト ボックスにこの値を貼り付けます。

    d. [ **SP メタデータ XML のダウンロード** ] を選択し、[ **基本的な SAML 構成]** セクションでメタデータ ファイルをアップロードします。

    e. **アプリのフェデレーション メタデータ URL を**メモ帳にコピーし、その内容を **IdP メタデータ XML** テキスト ボックスに貼り付けて、[**保存]** ボタンを選択します。

    f. 成功した場合は、IdP **のエンティティ ID** などの情報が **IdP エンティティ ID** テキスト ボックスに表示されます。

    g. **[属性マッピング]** セクションの必須フィールドに、あらかじめコピーしてあった内容を手動で入力します。

    h. [ **全般構成** ] セクションで、[ **Just-In-Time (JIT) アカウントの作成を許可** する] を有効にして、[保存] を選択 **します**。

    Note

    これらのパラメーターが正しくマップされていない場合、ログイン/アカウントの作成は成功せず、エラーが表示されます。 サインオンに失敗したときに拡張属性デバッグ情報を一時的に表示するには、**[Show Debugging Information](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デバッグ情報の表示)** チェックボックスをオンにします。

#### Fresh Relevance テストユーザーの作成

このセクションでは、Britta Simon というユーザーを Fresh Relevance に作成します。 Fresh Relevance では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Fresh Relevance にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fresh Relevance に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Fresh Relevance] タイルを選択すると、SSO を設定した Fresh Relevance に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/freshdesk-tutorial"} -->
## Microsoft Entra ID で Freshdesk for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freshdesk-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-09
- Summary: Microsoft Entra ID と Freshdesk の間でシングル サインオンを構成する方法について説明します。

この記事では、Freshdesk と Microsoft Entra ID を統合する方法について説明します。 Freshdesk と Microsoft Entra ID を統合すると、次のことができます。

- Freshdesk にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Freshdesk に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Freshdesk でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Freshdesk では、 **SP** Initiated SSO がサポートされます

### ギャラリーから Freshdesk を追加する

Microsoft Entra ID への Freshdesk の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Freshdesk を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Freshdesk**」と入力します。
4. 結果パネルから **Freshdesk** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Freshdesk の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Freshdesk に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Freshdesk の関連ユーザーとの間にリンク関係を確立する必要があります。

Freshdesk に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Freshdesk SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    - **Freshdesk のテスト ユーザーの作成** - Freshdesk で Britta Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Freshdesk**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンのセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    1. **[サインオン URL]** ボックスに、`https://<tenant-name>.freshdesk.com` のパターン、または FreshDesk から示されたその他の値を使用して URL を入力します。
    2. **[Identifier (Entity ID)](ID (エンティティ ID))** ボックスに、`https://<tenant-name>.freshdesk.com` のパターン、または FreshDesk から示されたその他の値を使用して URL を入力します。
    3. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.freshdesk.com/login/saml`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには [、Freshdesk クライアント サポート チーム](https://freshdesk.com/helpdesk-software?utm_source=Google-AdWords&amp;utm_medium=Search-IND-Brand&amp;utm_campaign=Search-IND-Brand&amp;utm_term=freshdesk&amp;device=c&amp;gclid=COSH2_LH7NICFVUDvAodBPgBZg) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Freshdesk アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示していますが、 **一意のユーザー識別子** は **user.userprincipalname** にマップされていますが、Freshdesk ではこの要求が **user.mail** にマップされると想定されているため、[編集] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Freshdesk のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Freshdesk SSO の構成

1. 別の Web ブラウザー ウィンドウで、Freshdesk 企業サイトに管理者としてログインします。
2. **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)** アイコンを選択し、 **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)** セクションで次の手順のようにします。

    [Image: シングル サインオン]

    1. **[シングル サインオン] で** [**オン**] を選択します。
    2. **[ログイン方法**] で、[**SAML SSO**] を選択します。
    3. **IdP テキスト ボックスによって提供されるエンティティ ID** に、前にコピーした**エンティティ ID** 値を貼り付けます。
    4. **[SAML SSO URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。
    5. [ **署名オプション]** で、ドロップダウンから **[署名付きアサーションのみ** ] を選択します。
    6. [ **ログアウト URL** ] ボックスに、前にコピーした **ログアウト URL** 値を貼り付けます。
    7. [ **セキュリティ証明書** ] ボックスに、先ほど取得した **証明書 (Base64)** の値を貼り付けます。
    8. **保存** を選択します。

### Freshdesk テスト ユーザーの作成

Microsoft Entra ユーザーが Freshdesk にログインできるようにするには、ユーザーを Freshdesk にプロビジョニングする必要があります。 Freshdesk の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. **Freshdesk** テナントにログインします。
2. 左側のメニューで 、[ **管理者** ] を選択し **、[全般設定]** タブで [ **エージェント**] を選択します。

    [Image: エージェント]
3. **新しいエージェント** を選択します。

    [Image: 新しいエージェント]
4. [エージェント情報] ダイアログで、必要なフィールドを入力し、[ **エージェントの作成**] を選択します。

    [Image: エージェント情報]

    注

    Microsoft Entra アカウント所有者は、アカウントをアクティブ化する前にアカウントを確認するためのリンクを含む電子メールを受け取ります。

    注

    Freshdesk が提供する他の Freshdesk ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントを Freshdesk にプロビジョニングできます。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Freshdesk のサインオン URL にリダイレクトされます。
- Freshdesk のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Freshdesk タイルを選択すると、SSO を設定した Freshdesk に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/freshgrade-tutorial"} -->
## Microsoft Entra ID で FreshGrade for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freshgrade-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FreshGrade 間のシングル サインオンを構成する方法について説明します。

この記事では、FreshGrade と Microsoft Entra ID を統合する方法について説明します。 FreshGrade を Microsoft Entra ID と統合すると、次のことが可能になります。

- FreshGrade にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで FreshGrade に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な FreshGrade サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- FreshGrade では、**SP** initiated SSO がサポートされます。

### ギャラリーからの FreshGrade の追加

Microsoft Entra ID への FreshGrade の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に FreshGrade を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**FreshGrade**」と入力します。
4. 結果のパネルから **FreshGrade** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FreshGrade に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、FreshGrade に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと FreshGrade の関連ユーザー間にリンク関係を確立する必要があります。

FreshGrade に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FreshGrade SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FreshGrade テストユーザーの作成** - Microsoft Entra における B.Simon の対応として、FreshGrade でそれにリンクされたユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[FreshGrade]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** テキストボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://login.onboarding.freshgrade.com:443/saml/metadata/alias/<instancename>` |
    | `https://login.freshgrade.com:443/saml/metadata/alias/<instancename>` |

    b。 [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<subdomain>.freshgrade.com/login` |
    | `https://<subdomain>.onboarding.freshgrade.com/login` |

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[FreshGrade クライアント サポート チーム](mailto:support@freshgrade.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FreshGrade の SSO の構成

**FreshGrade** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [FreshGrade サポート チーム](mailto:support@freshgrade.com)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FreshGrade テスト ユーザーの作成

このセクションでは、FreshGrade で Britta Simon というユーザーを作成します。 [FreshGrade サポート チーム](mailto:support@freshgrade.com)と連携して、FreshGrade プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる FreshGrade のサインオン URL にリダイレクトされます。
- FreshGrade のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [FreshGrade] タイルを選択すると、このオプションは FreshGrade のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/freshservice-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Freshservice Provisioning を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freshservice-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントをMicrosoft Entra IDから Freshservice Provisioning に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Freshservice Provisioning と Microsoft Entra ID の両方で自動ユーザー プロビジョニングを構成するために実行する必要がある手順について説明します。 Microsoft Entra IDが構成されると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーは[Freshservice Provisioning](https://effy.co.in/)に自動でプロビジョニングおよび解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Freshservice Provisioning でユーザーを作成する
- アクセスが不要になった場合に Freshservice Provisioning でユーザーを削除する
- Microsoft Entra ID と Freshservice Provisioning の間でユーザー属性の同期を維持する
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 組織の管理者アクセス許可を持つ [Freshservice アカウント](https://www.freshservice.com) 。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとFreshservice Provisioningの間でマップするデータを[決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Freshservice Provisioning を構成する

1. Freshservice アカウントで、マーケットプレイスから Azure Provisioning (SCIM) アプリをインストールします。Freshservice 管理者からAppsGet Appsを開きます。
2. 構成画面で、 **Freshservice ドメイン** (たとえば、 `acme.freshservice.com`) と **組織管理者 API キー**を指定します。
3. [ **続行] を選択します**。
4. **ベアラー トークン**を強調表示してコピーします。 この値は、Freshservice Provisioning アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。
5. [ **インストール]** を選択してインストールを完了します。
6. **テナント URL** が`https://scim.freshservice.com/scim/v2`。 この値は、Freshservice Provisioning アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Freshservice Provisioning を追加する

Microsoft Entra アプリケーション ギャラリーから Freshservice Provisioning を追加して、Freshservice Provisioning へのプロビジョニングの管理を開始します。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Freshservice Provisioning への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて Freshservice Provisioning でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Freshservice Provisioning の自動ユーザー プロビジョニングを構成するには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で[ **Freshservice Provisioning]\(Freshservice プロビジョニング**\) を選択します。

    [Image: アプリケーションの一覧の Freshservice Provisioning リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Freshservice Provisioning テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Freshservice Provisioning に接続できることを確認します。 接続に失敗した場合は、Freshservice Provisioning アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Freshservice Provisioning に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作の Freshservice Provisioning のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Freshservice Provisioning API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | displayName | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |
    | ロケール | 糸 |  |
    | タイトル | 糸 |  |
    | タイムゾーン | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |
    | urn:ietf:params:scim:schemas:extension:freshservice:2.0:User:isAgent | 糸 |  |

注

下の手順に従い、アプリケーションのニーズに合わせてカスタム拡張属性をスキーマに追加できます。

- [マッピング] で、**[Microsoft Entra ユーザーのプロビジョニング]**を選択します。
- ページの下部にある [ **詳細オプションの表示**] を選択します。
- **Freshservice の [属性リストの編集] を**選択します。
- 属性の一覧の下部にあるフィールドに、カスタム属性に関する情報を入力します。 カスタム属性 urn 名前空間は、下の例に示すパターンに従う必要があります。 **CustomAttribute** は、アプリケーションの要件に従ってカスタマイズできます(例: urn:ietf:params:scim:schemas:extension:freshservice:2.0:User:**isAgent**)。
- カスタム属性に対して適切なデータ型を選択し、[ **保存]** を選択する必要があります。
- 既定のマッピング画面に戻り、[ **新しいマッピングの追加]** を選択します。 カスタム属性は、[ **ターゲット属性** ] リストドロップダウンに表示されます。

1. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
2. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
3. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/freshservice-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Freshservice を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freshservice-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Freshservice との間でシングル サインオンを構成する方法について説明します。

この記事では、Freshservice と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID に Freshservice を統合すると、次の利点が得られます。

- どのユーザーが Freshservice にアクセスできるかを Microsoft Entra ID で制御できます。
- ユーザーがそれぞれの Microsoft Entra アカウントを使用して Freshservice に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Freshservice は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FreshService でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Freshservice では、 **SP** Initiated SSO がサポートされます。
- Freshservice では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freshservice-provisioning-tutorial)。

### ギャラリーからの Freshservice の追加

Microsoft Entra ID への Freshservice の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Freshservice を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Freshservice**」と入力します。
4. 結果パネルから **Freshservice** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Freshservice 向けに Microsoft Entra の SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Freshservice に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Freshservice の関連ユーザーとの間にリンク関係を確立する必要があります。

Freshservice で Microsoft Entra の SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Freshservice SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Freshservice テストユーザーの作成** - B.Simon に対応する Freshservice のユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Freshservice]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.freshservice.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.freshservice.com`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.freshservice.com/login/saml`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには [、Freshservice クライアント サポート チーム](https://support.freshservice.com/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **Azure portal** の [**Freshservice のセットアップ**] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Freshservice の SSO の構成

1. Web ブラウザーの別のウィンドウで、自社の Freshservice 企業サイトに管理者としてサインインします。
2. 左側のメニューで[**管理**]を選択し**、[全般設定]**で**[ヘルプデスクセキュリティ**]を選択します。
3. **[セキュリティ**] で、[**Freshservice 360 Security に移動**] を選択します。
4. [ **セキュリティ** ] セクションで、次の手順を実行します。

    [Image: シングル サインオン]

    a. **[シングル サインオン] で** [**オン**] を選択します。

    b。 **[ログイン方法**] で、[**SAML SSO**] を選択します。

    c. **IdP テキスト ボックスによって提供されるエンティティ ID** に、前にコピーした**エンティティ ID** 値を貼り付けます。

    d. **[SAML SSO URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    e. [ **署名オプション]** で、ドロップダウンから **[署名付きアサーションのみ** ] を選択します。

    f. [ **ログアウト URL** ] ボックスに、前にコピーした **ログアウト URL** 値を貼り付けます。

    g. [ **セキュリティ証明書** ] ボックスに、先ほど取得した **証明書 (Base64)** の値を貼り付けます。

    h. **[保存] を選択します**。

### Freshservice のテスト ユーザーの作成

Microsoft Entra ユーザーが FreshService にサインインできるようにするには、そのユーザーを FreshService にプロビジョニングする必要があります。 FreshService の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **FreshService** 企業サイトに管理者としてサインインします。
2. 左側のメニューで、[管理者] を選択 **します**。
3. [ **ユーザー管理** ] セクションで、[ **要求者**] を選択します。

    [Image: 要求者]
4. **[新しい要求者]** を選択します。

    [Image: 新しい要求者]
5. [ **新しい要求者** ] セクションで、必須フィールドを入力し、[ **保存]** を選択します。 [Image: 新しい要求者]

    注

    アカウントがアクティブになる前に、Microsoft Entra アカウント所有者に、アカウント確認用のリンクを記述した電子メールが送信されます。

    注

    他の FreshService ユーザー アカウント作成ツールや、FreshService から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

注

Freshservice では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freshservice-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Freshservice のサインオン URL にリダイレクトされます。
- Freshservice のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Freshservice] タイルを選択すると、SSO を設定した Freshservice に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/freshworks-tutorial"} -->
## Microsoft Entra ID で Freshworks for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/freshworks-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Freshworksの間にシングル サインオンを構成する方法について説明します。

この記事では、Freshworks と Microsoft Entra ID を統合する方法について説明します。 Freshworks を Microsoft Entra ID と統合すると、次のことができます。

- Freshworks にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Freshworks に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

Freshworks は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Freshworks でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Freshworks では、**SP および IDP によって開始される SSO** がサポートされます

### ギャラリーからの Freshworks の追加

Microsoft Entra ID への Freshworks の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Freshworks を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Freshworks**」と入力します。
4. 結果パネルから **Freshworks** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Freshworks 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Freshworks に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Freshworks の関連ユーザーとの間にリンク関係を確立する必要があります。

Freshworks で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Freshworks SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Freshworks のテスト ユーザーを作成** - B.Simon に対応するユーザーを Freshworks に作成し、それを Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Freshworks**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.freshworks.com/sp/SAML/<MODULE_ID>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.freshworks.com/sp/SAML/CUSTOM_URL`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.freshworks.com/login`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには [、Freshworks クライアント サポート チーム](mailto:support@freshworks.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. 要件に従って **署名** オプションを変更するには、[ **編集** ] ボタンを選択して **SAML 署名証明書** ダイアログを開きます。

    [Image: 画像]
9. [ **SAML 署名証明書** ] ダイアログの [ **署名オプション**] で、[ **SAML 応答の署名**] を選択します。 次に、[ **保存]** を選択します。
10. [ **Freshworks のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Freshworks の SSO の構成

1. 新しい Web ブラウザー ウィンドウを開き、Freshworks 企業サイトに管理者としてサインインして、次の手順を実行します。
2. メニューの左側にある **[セキュリティ**] アイコンを選択し、[**シングル サインオン**] オプションをオンにして、[**認証方法**] で **[SAML SSO**] を選択します。

    [Image: [セキュリティ - 認証方法] セクションを示すスクリーンショット。[シングル サインオン] オプションがオンになっていて、[S A M L S S O] が選択されています。]
3. [ **シングル サインオン** ] セクションで、次の手順に従います。

    [Image: Freshworks の構成]

    ある。 [**コピー**] を選択してインスタンスの**サービス プロバイダー (SP) エンティティ ID を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子 (エンティティ ID)]** テキスト ボックスに貼り付けます。

    b。 IdP テキスト ボックス **によって提供されるエンティティ ID** に、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    c. **[SAML SSO URL**] テキスト ボックスに、先にコピーした**ログイン URL** の値を貼り付けます。

    d. Base64 でエンコードされた証明書をメモ帳で開き、その内容をコピーして **[セキュリティ証明書** ] テキスト ボックスに貼り付けます。

    え **[保存] を選択します**。

#### Freshworks のテスト ユーザーの作成

このセクションでは、Freshworks で B.Simon というユーザーを作成します。 [Freshworks クライアント サポート チーム](mailto:support@freshworks.com)と協力して、Freshworks プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Freshworks のサインオン URL にリダイレクトされます。
- Freshworks のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP によって開始:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Freshworks に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Freshworks タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Freshworks に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/front-tutorial"} -->
## Microsoft Entra ID で Front for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/front-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Front 間のシングル サインオンを構成する方法について説明します。

この記事では、Front と Microsoft Entra ID を統合する方法について説明します。 Front を Microsoft Entra ID と統合すると、次のことが可能になります。

- Front にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Front に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Front でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Front では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Front の追加

Microsoft Entra ID への Front の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Front を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Front**」と入力します。
4. 結果パネルから **[Front]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Front に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Front に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Front の関連ユーザー間にリンク関係を確立する必要があります。

Front に対して Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Front SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Front テスト ユーザーの作成** - Front で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現に対応させます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;の**フロント**&gt;、**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.frontapp.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.frontapp.com/sso/saml/callback`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 この値を取得するには、[Front クライアント サポート チーム](mailto:support@frontapp.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Front のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Front の SSO の構成

1. Front の Web サイトに管理者としてログインします。
2. **[settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** に移動し、 **[Preferences](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/基本設定)** を選択します。
3. **[Company preferences](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社の設定)** ページで、次の手順を実行します。

    [Image: [Single Sign On](シングル サイン オン) リンクが選択されている [Company preferences](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社の設定) セクションを示すスクリーンショット。]

    a. 左側のナビゲーション **で [シングル サインオン** ] を選択します。

    b。 **[Single Sign On](シングル サインオン)** のドロップダウン リストで、 **[SAML]** を選択します。

    c. **[エントリ ポイント]** テキスト ボックスに、先ほどコピーした**ログイン URL** の値を入力します。

    d. **[Requested authentication context](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/要求された認証コンテキスト)** の種類として **[Disabled](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/無効)** を選択します。

    e. ダウンロードした**証明書 (Base64)** ファイルをメモ帳で開き、その内容をクリップボードにコピーし、 **[Signing certificate]** ボックスに貼り付けます。
4. **[Service provider settings]** セクションで、次の手順に従います。

    [Image: アプリ側でのシングル サインオンの構成]

    a. **Entity ID** の値をコピーして Azure Portal の **[Front のドメインと URL]** セクションの **[識別子]** ボックスに貼り付けます。

    b。 **ACS URL** の値をコピーして Azure Portal の **[Front のドメインと URL]** セクションの **[応答 URL]** ボックスに貼り付けます。
5. [ **保存] ボタンを** 選択します。

#### Front のテスト ユーザーを作成する

このセクションでは、Front で Britta Simon というユーザーを作成します。 [Front クライアント サポート チーム](mailto:support@frontapp.com)と連携し、Front プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Front に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Front] タイルを選択すると、SSO を設定した Front に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/frontify-tutorial"} -->
## Microsoft Entra ID で Frontify for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/frontify-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Frontify の間でシングル サインオンを構成する方法について説明します。

この記事では、Frontify と Microsoft Entra ID を統合する方法について説明します。 Frontify と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID を使用して Frontify へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Frontify に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Frontify でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Frontify では、 **SP** Initiated SSO がサポートされます。

### ギャラリーから Frontify を追加する

Microsoft Entra ID への Frontify の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Frontify を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Frontify**」と入力します。
4. 結果パネルから **Frontify** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Frontify の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Frontify に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Frontify の関連ユーザーとの間にリンク関係を確立する必要があります。

Frontify で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Frontify SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Frontify テストユーザーの作成 - B.Simon に対応するユーザーを Frontify に作成し、それを Microsoft Entra のユーザー表現にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Frontify**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN NAME>/api/auth/saml/metadata/`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN NAME>/`

    手記

    これらの値は実際の値ではありません。 実際の識別子とサインオン URL でこれらの値を更新します。 これらの値を取得するには、Frontify クライアント サポート チーム  にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Frontify SSO の構成

**Frontify** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Frontify サポート チーム](mailto:support@frontify.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Frontify テスト ユーザーの作成

このセクションでは、Frontify で Britta Simon というユーザーを作成します。 frontify サポート チーム  と連携して、Frontify プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Frontify のサインオン URL にリダイレクトされます。
- Frontify のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Frontify] タイルを選択すると、このオプションは Frontify のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/frontline-education-tutorial"} -->
## Microsoft Entra ID を使用して Frontline Education for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/frontline-education-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Frontline Education の間にシングル サインオンを構成する方法について説明します。

この記事では、Frontline Education と Microsoft Entra ID を統合する方法について説明します。 Frontline Education と Microsoft Entra ID を統合すると、次のことができます。

- Frontline Education にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Frontline Education に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Frontline Education のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Frontline Education では、**SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Frontline Education を追加する

Microsoft Entra ID への Frontline Education の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Frontline Education を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで検索ボックスに「**Frontline Education**」と入力します。
4. 結果パネルで **[Frontline Education]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Frontline Education の Microsoft Entra SSO を構成しテストする

**B.Simon** というテスト ユーザーを使用して、Frontline Education に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Frontline Education の関連ユーザーとの間にリンク関係を確立する必要があります。

Frontline Education に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Frontline Education SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Frontline Education のテストユーザーの作成** - Microsoft Entra ユーザーの表現とリンクされている、B.Simon に対応するユーザーを Frontline Education に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Frontline Education**&gt;**Single サインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.frontlineeducation.com/sso/<CLIENTID>`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[Frontline Education クライアント サポート チーム](mailto:support@frontlineed.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Frontline Education SSO の構成

**Frontline Education** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Frontline Education サポート チーム](mailto:support@frontlineed.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Frontline Education テスト ユーザーの作成

このセクションでは、Frontline Education で Britta Simon というユーザーを作成します。 [Frontline Education サポート チーム](mailto:support@frontlineed.com)と連携し、Frontline Education プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Frontline Education のサインオン URL にリダイレクトされます。
- Frontline Education のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Frontline Education] タイルを選択すると、このオプションは Frontline Education のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ftapi-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に FTAPI を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ftapi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と FTAPI の間でシングル サインオンを構成する方法について説明します。

この記事では、FTAPI と Microsoft Entra ID を統合する方法について説明します。 FTAPI と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で誰が FTAPI にアクセスできるかを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して FTAPI に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- FTAPI でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- FTAPI では、 **SP** によって開始される SSO がサポートされます。
- FTAPI では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから FTAPI を追加する

Microsoft Entra ID への FTAPI の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に FTAPI を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「FTAPI**」と入力します。
4. 結果パネルから **FTAPI** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### FTAPI の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、FTAPI に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと FTAPI の関連ユーザーとの間にリンク関係を確立する必要があります。

FTAPI に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **FTAPI SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **FTAPI テスト ユーザーの作成 - FTAPI** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**FTAPI**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.<DOMAIN>.<EXTENSION>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.<DOMAIN>.<EXTENSION>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.<DOMAIN>.<EXTENSION>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [FTAPI サポート チーム](mailto:support@ftapi.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. FTAPI アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、FTAPI アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 電話番号 | ユーザー.電話番号 |
    | カンパニーネーム | ユーザー.companyname |
    | ロール | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **FTAPI のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### FTAPI SSO の構成

**FTAPI** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [FTAPI サポート チーム](mailto:support@ftapi.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### FTAPI テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを FTAPI に作成します。 FTAPI では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 FTAPI にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる FTAPI サインオン URL にリダイレクトします。
- FTAPI サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [FTAPI] タイルを選択すると、このオプションは FTAPI サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fulcrum-tutorial"} -->
## Microsoft Entra ID で Fulcrum for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fulcrum-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Fulcrum の間でシングル サインオンを構成する方法について説明します。

この記事では、Fulcrum と Microsoft Entra ID を統合する方法について説明します。 Fulcrum と Microsoft Entra ID を統合すると、次のことができます。

- Fulcrum にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Fulcrum に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fulcrum でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fulcrum では、**SP と IDP のどちらでも開始された SSO** がサポートされます。
- Fulcrum では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Fulcrum を追加する

Microsoft Entra ID への Fulcrum の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Fulcrum を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Fulcrum**」と入力します。
4. 結果パネルから **Fulcrum** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fulcrum の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Fulcrum に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Fulcrum の関連ユーザーとの間にリンク関係を確立する必要があります。

Fulcrum に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fulcrum SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fulcrum のテスト ユーザーの作成** - Fulcrum で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Fulcrum**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://web.fulcrumapp.com/saml/consume?organization=<DOMAIN>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://web.fulcrumapp.com/users/saml`

    手記

    応答 URL は、実際の値ではありません。 実際の応答 URL で値を更新します。 値を取得するには、Fulcrumのクライアントサポートチーム にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Fulcrum アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. 上記に加えて、Fulcrum アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | first\_name | User.givenname |
    | last\_name | User.surname |
    | メール | User.mail |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Fulcrum のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fulcrum SSO の構成

**Fulcrum** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Fulcrum サポート チーム](mailto:support@fulcrumapp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Fulcrum テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Fulcrum に作成します。 Fulcrum では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Fulcrum にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Fulcrum のサインオン URL にリダイレクトされます。
- Fulcrum のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Fulcrum に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Fulcrum] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Fulcrum に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/fullstory-saml-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Fullstory SAML を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/fullstory-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-07-04
- Summary: Microsoft Entra ID と Fullstory SAML の間のシングル サインオンを構成する方法を学習します。

この記事では、Fullstory SAML と Microsoft Entra ID を統合する方法について説明します。 Fullstory SAML を Microsoft Entra ID と統合すると、以下のことが可能になります。

- 誰が Fullstory SAML にアクセスできるかを Microsoft Entra ID 内で制御する。
- ユーザーが各自の Microsoft Entra アカウントで Fullstory SAML に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Fullstory SAML のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Fullstory SAML がサポートしているのは **SP** Initiated SSO だけです。
- Fullstory SAML は **Just-In-Time** ユーザー プロビジョニングをサポートしています。

### ギャラリーから Fullstory SAML を追加する

Fullstory SAML の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Fullstory SAML を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加]** セクションで、検索ボックスに「**Fullstory SAML**」と入力します。
4. 結果パネルから **[Fullstory SAML]** を選択した後、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Fullstory SAML の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して Fullstory SAML での Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Fullstory SAML 内の関連ユーザーとの間にリンク関係を確立する必要があります。

Fullstory SAML での Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Fullstory SAML SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Fullstory SAML テスト ユーザーの作成 - Fullstory SAML** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Fullstory SAML**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して値を入力します。`urn:auth0:fullstory:<Entity ID>`

    b。 **[応答 URL]** ボックスに、`https://fullstory.auth0.com/login/callback?connection=<Entity ID>` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://app.fullstory.com/sso/<Entity ID>`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Fullstory SAML のサポート チーム](mailto:support@fullstory.com)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、**[フェデレーション証明書 (XML)]** を選択し、**[ダウンロード]** を選択して、IdP で生成された metadata.xml ファイルをダウンロードして自分のコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Fullstory SAML SSO を構成する

**Fullstory SAML** 側でシングル サインオンを構成するには、**フェデレーション証明書 (XML)** をダウンロードして、IdP で生成された metadata.xml ファイルの内容をコピーし、Fullstory に貼り付けて構成を完了する必要があります。 詳細については、この[ドキュメント](https://help.fullstory.com/hc/articles/360020623014-How-do-I-configure-SSO)を参照してください。

#### Fullstory SAML のテスト ユーザーを作成する

このセクションでは、Fullstory SAML 内に Britta Simon というユーザーを作成します。 Fullstory SAML は Just-In-Time ユーザー プロビジョニングをサポートしており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 Fullstory SAML 内にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Fullstory SAML サインオン URL にリダイレクトされます。
- Fullstory SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Fullstory SAML] タイルを選択すると、このオプションは Fullstory SAML サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->
