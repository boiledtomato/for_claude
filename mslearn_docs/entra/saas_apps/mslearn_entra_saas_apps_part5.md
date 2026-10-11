# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 5)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 73

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/check-point-remote-access-vpn-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Check Point Remote Secure Access VPN を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/check-point-remote-access-vpn-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Check Point Remote Secure Access VPN の間でシングル サインオンを構成する方法について説明します。

この記事では、Check Point Remote Secure Access VPN と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Check Point Remote Secure Access VPN を統合すると、次のことができます。

- Check Point Remote Secure Access VPN にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Check Point Remote Secure Access VPN に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Check Point Remote Secure Access VPN でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Check Point Remote Secure Access VPN では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Check Point Remote Secure Access VPN の追加

Microsoft Entra ID への Check Point Remote Secure Access VPN の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Check Point Remote Secure Access VPN を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Check Point Remote Secure Access VPN**」と入力します。
4. 結果のパネルから **[Check Point Remote Secure Access VPN]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Check Point Remote Secure Access VPN の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Check Point Remote Secure Access VPN に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Check Point Remote Secure Access VPN の関連ユーザーとの間にリンク関係を確立する必要があります。

Check Point Remote Secure Access VPN に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する** - ユーザーがこの機能を使用できるようにします。

    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Check Point Remote Secure Access VPN SSO の構成** - ユーザーがこの機能を使用できるようにします。

    1. **Check Point Remote Secure Access VPN のテストユーザーを作成** - Microsoft Entra のユーザーとしての B.Simon に対応するユーザーを Check Point Remote Secure Access VPN で作成し、そのリンクを確立します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Check Point Remote Secure Access VPN]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    1. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<GATEWAY_IP>/saml-vpn/spPortal/ACS/ID/<IDENTIFIER_UID>`
    2. **[応答 URL]** ボックスに、`https://<GATEWAY_IP>/saml-vpn/spPortal/ACS/Login/<IDENTIFIER_UID>` のパターンを使用して URL を入力します
    3. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<GATEWAY_IP>/saml-vpn/`

    Note

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Check Point Remote Secure Access VPN クライアント サポート チーム](mailto:support@checkpoint.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Check Point Remote Secure Access VPN のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Check Point Remote Secure Access VPN SSO の構成

#### 外部ユーザー プロファイル オブジェクトを構成する

Note

このセクションは、オンプレミスの Active Directory (LDAP) を使用しない場合にのみ必要です。

**レガシ SmartDashboard で汎用ユーザー プロファイルを構成する**:

1. SmartConsole で、**[Manage & Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理と設定) &gt; [Blades](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ブレード)** に移動します。
2. [ **モバイル アクセス** ] セクションで、 **SmartDashboard で [構成**] を選択します。 レガシ SmartDashboard が表示されます。
3. [ネットワーク **オブジェクト]** ウィンドウで、[ユーザー] を選択 **します**。
4. 空の領域を右クリックし、[**新規 &gt; 外部ユーザー プロファイル &gt; すべてのユーザーに一致させる]** を選択します。
5. **[External User Profile](外部ユーザー プロファイル)** のプロパティを構成します。

    1. **[General Properties](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般プロパティ)** ページで:

        - **外部ユーザー プロファイル**の名前フィールドは、既定の名前 (`generic`\*) のままにします
        - **[Expiration Date](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/有効期限)** フィールドで、適切な日付を設定します
    2. **[認証]** ページで、次の操作を実行します。

        - **[Authentication Scheme](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証スキーム)** ボックスの一覧から [`undefined`](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/未定義) を選択します
    3. **[Location](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/場所)** 、 **[Time](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/時刻)** 、 **[Encryption](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/暗号化)** の各ページで:

        - その他の関連する設定を構成します
    4. **[OK] を選択**.
6. 上部のツール バーから [ **更新** ] を選択します (または Ctrl + S キーを押します)。
7. SmartDashboard を閉じます。
8. SmartConsole で、Access Control Policy をインストールします。

#### Remote Access VPN を構成する

1. 適切な Security Gateway のオブジェクトを開きます。
2. [General Properties](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般プロパティ) ページで、**IPSec VPN** Software Blade を有効にします。
3. 左側のツリーから、 **IPSec VPN** ページを選択します。
4. この **セキュリティ ゲートウェイが次の VPN コミュニティに参加しているセクションで**、[ **追加** ] を選択し、[ **リモート アクセス コミュニティ**] を選択します。
5. 左側のツリーから、 **リモート アクセス &gt; VPN クライアント**を選択します。
6. **[Support Visitor Mode](ビジター モードのサポート)** を有効にします。
7. 左側のツリーから、 **Office モード &gt; VPN クライアント**を選択します。
8. **[Allow Office Mode](オフィス モードを許可する)** を選択し、適切なオフィス モード メソッドを選択します。
9. 左側のツリーから、[ **VPN クライアント] &gt; [SAML ポータルの設定]** を選択します。
10. [Main URL](メイン URL) に、ゲートウェイの完全修飾ドメイン名が指定されていることを確認します。 このドメイン名の末尾は、自分が所属する組織によって登録された DNS サフィックスであることが必要です。 例: `https://gateway1.company.com/saml-vpn`
11. 証明書が、エンド ユーザーのブラウザーによって信頼されていることを確認します。
12. **[OK] を選択**.

#### ID プロバイダー オブジェクトを構成する

1. Remote Access VPN に参加する各 Security Gateway について、次の手順を実行します。
2. SmartConsole の **[ゲートウェイとサーバー]** ビューで、[ **新規] &gt; [その他] &gt; [ユーザー/ID &gt; ID プロバイダー] を選択します**。
3. **[New Identity Provider](新しい ID プロバイダー)** ウィンドウで、次の手順を実行します。

    [Image: [Identity Provider](ID プロバイダー) セクションのスクリーンショット。]

    a. **[Gateway](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ゲートウェイ)** フィールドで、SAML 認証を実行する必要があるセキュリティ ゲートウェイを選択します。

    b。 **[Service](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サービス)** フィールドのドロップダウンから **[Remote Access VPN]** を選択します。

    c. **[識別子 (エンティティ ID)]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[識別子]** テキスト ボックスにこの値を貼り付けます。

    d. **[応答 URL]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL]** テキスト ボックスにこの値を貼り付けます。

    e. **[Import Metadata File] (メタデータ ファイルのインポート)** を選択して、ダウンロードした**フェデレーション メタデータ XML** をアップロードします。

    Note

    または、 **[Insert Manually] (手動で挿入)** を選択した後、**エンティティ ID** と**ログイン URL** の値を対応するフィールドに手動で貼り付け、**証明書ファイル**をアップロードすることもできます。

    f. **[OK] を選択**.

#### 認証方法として ID プロバイダーを構成する

1. 適切な Security Gateway のオブジェクトを開きます。
2. **[VPN Clients](VPN クライアント) &gt; [Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** ページで:

    a. **[Allow older clients to connect to this gateway](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/以前のクライアントがこのゲートウェイに接続できるようにする)** チェック ボックスをオフにします。

    b。 新しいオブジェクトを追加するか、既存の領域を編集します。

    [Image: 新しいオブジェクトを追加する画面のスクリーンショット。]
3. 名前と表示名を入力し、認証方法を追加/編集します。MEP に参加している GW でログイン オプションを使用する場合は、スムーズなユーザー エクスペリエンスを実現するために、名前はプレフィックス `SAMLVPN_` で始める必要があります。

    [Image: ログイン オプションに関するスクリーンショット。]
4. [ **ID プロバイダー**] オプションを選択し、緑色の [ `+` ] ボタンを選択し、該当する ID プロバイダー オブジェクトを選択します。

    [Image: 適切な ID プロバイダー オブジェクトを選択する画面のスクリーンショット。]
5. [複数のログオン オプション] ウィンドウで、左側のウィンドウで [ **ユーザー ディレクトリ** ] を選択し、[ **手動構成**] を選択します。 次の 2 つのオプション用意されています：

    1. オンプレミスの Active Directory (LDAP) を使用しない場合は、[外部ユーザー プロファイル] のみを選択し、[OK] を選択します。
    2. オンプレミスの Active Directory (LDAP) を使用したい場合は、[LDAP users](LDAP ユーザー) のみを選択し、[LDAP Lookup Type](LDAP ルックアップ タイプ) で [email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メール) を選択します。 [OK] をクリックします。

    [Image: 手動構成のスクリーンショット。]
6. 管理データベースで必要な設定を構成します。

    1. SmartConsole を閉じます。
    2. GuiDBEdit ツールを使用して管理サーバーに接続します ([sk13009](https://supportcenter.checkpoint.com/supportcenter/portal?eventSubmit_doGoviewsolutiondetails&amp;solutionid=sk13009) を参照)。
    3. 左上のペインで、**[Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集) &gt; [Network Objects](ネットワーク オブジェクト)** に移動します。
    4. 右上のペインで、 **[Security Gateway object](セキュリティ ゲートウェイ オブジェクト)** を選択します。
    5. 一番下のペインで、**[realms\_for\_blades]**&gt;**[vpn]** に移動します。
    6. オンプレミスの Active Directory (LDAP) を使用しない場合は、**do\_ldap\_fetchを** **false** に設定し、**do\_generic\_fetch** **を true** に設定します。 その後、**OK** を選択します。 オンプレミスの Active Directory (LDAP) を使用したい場合は、 **[do\_ldap\_fetch]** を **true** に、 **[do\_generic\_fetch]** を **false** に設定します。 その後、**OK** を選択します。
    7. 該当するすべての Security Gateway について、手順 4. から手順 6. を繰り返します。
    8. **[ファイル]**&gt;**[保存]** を選択して、すべての変更を保存します。
7. GuiDBEdit ツールを閉じます。
8. 各 Security Gateway と各 Software Blade には個別の設定があります。 各 Security Gateway と各 Software Blade の設定のうち、認証を使用する設定を確認します (VPN、Mobile Access、Identity Awareness)。

    - **[LDAP users](LDAP ユーザー)** オプションは、LDAP を使用する Software Blade でのみ選択してください。
    - LDAP を使用しないソフトウェア ブレードに対してのみ、[ **外部ユーザー プロファイル** ] オプションを選択してください。
9. 各 Security Gateway に Access Control Policy をインストールします。

#### VPN RA クライアントのインストールと構成

1. VPN クライアントをインストールします。
2. ID プロバイダーのブラウザー モード (オプション) を設定します。

    既定では、Windows クライアントはその埋め込みブラウザーを、macOS クライアントは Safari を ID プロバイダーのポータルでの認証に使用します。 Windows クライアントで、この動作を変更して Internet Explorer を使用するには、次の手順を実行します。

    1. クライアント マシンで、プレーン テキスト エディターを管理者として開きます。
    2. テキスト エディターで `trac.defaults` ファイルを開きます。

        - 32 ビット Windows の場合:

            `%ProgramFiles%\CheckPoint\Endpoint Connect\trac.defaults`
        - 64 ビット Windows の場合:

            `%ProgramFiles(x86)%\CheckPoint\Endpoint Connect\trac.defaults`
    3. `idp_browser_mode` の値を `embedded` から `IE` に変更します。
    4. ファイルを保存します。
    5. Check Point Endpoint Security VPN クライアント サービスを再起動します。

    管理者として Windows コマンド プロンプトを開き、これらのコマンドを実行します。

    `# net stop TracSrvWrapper`

    `# net start TracSrvWrapper`
3. バックグラウンドで実行中のブラウザーを使用して認証を開始します。

    1. クライアント マシンで、プレーン テキスト エディターを管理者として開きます。
    2. テキスト エディターで `trac.defaults` ファイルを開きます。

        - 32 ビット Windows の場合:

            `%ProgramFiles%\CheckPoint\Endpoint Connect\trac.defaults`
        - 64 ビット Windows の場合:

            `%ProgramFiles(x86)%\CheckPoint\Endpoint Connect\trac.defaults`
        - macOS の場合:

            `/Library/Application Support/Checkpoint/Endpoint Security/Endpoint Connect/trac.defaults`
    3. `idp_show_browser_primary_auth_flow` の値を `false` に変更します。
    4. ファイルを保存します。
    5. Check Point Endpoint Security VPN クライアント サービスを再起動します。

        - Windows クライアントで、管理者として Windows コマンド プロンプトを開き、次のコマンドを実行します。

            `# net stop TracSrvWrapper`

            `# net start TracSrvWrapper`
        - macOS クライアントで、次を実行します。

            `sudo launchctl stop com.checkpoint.epc.service`

            `sudo launchctl start com.checkpoint.epc.service`

#### Check Point Remote Secure Access VPN テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Check Point Remote Secure Access VPN に作成します。 [Check Point Remote Secure Access VPN サポート チーム](mailto:support@checkpoint.com)と連携して、Check Point Remote Secure Access VPN プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

1. VPN クライアントを開き、[ **接続先...**] を選択します。

    [Image: [接続先] のスクリーンショット。]
2. ドロップダウンから **[サイト** ] を選択し、[ **接続**] を選択します。

    [Image: サイトを選択する画面のスクリーンショット。]
3. Microsoft Entra のログイン ポップアップで、「**Microsoft Entra のテスト ユーザーの作成**」セクションで作成した Microsoft Entra の資格情報を使用してサインインします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/checkpoint-infinity-portal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Check Point Infinity Portal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/checkpoint-infinity-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Check Point Infinity Portal の間のシングル サインオンを構成する方法について説明します。

この記事では、Check Point Infinity Portal と Microsoft Entra ID を統合する方法について説明します。 Check Point Infinity Portal と Microsoft Entra ID を統合すると、次のことが可能になります。

- Check Point Infinity Portal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Check Point Infinity Portal に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Check Point Infinity Portal でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Check Point Infinity Portal では、 **SP** によって開始される SSO がサポートされます。
- Check Point Infinity Portal では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリー から Check Point Infinity Portal を追加する

Check Point Infinity Portal と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Check Point Infinity Portal をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Check Point Infinity Portal**」と入力します。
4. 結果パネルから **Check Point Infinity Portal** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Check Point Infinity Portal の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Check Point Infinity Portal に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと、Check Point Infinity Portal での関連ユーザーの間にリンク関係を確立する必要があります。

Check Point Infinity Portal の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Check Point Infinity Portal の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Check Point Infinity Portal のテスト ユーザーを作成する** - Microsoft Entra におけるユーザーの表現にリンクされた、Check Point Infinity Portal で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Check Point Infinity Portal**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの値を入力します。

    | 環境 | 識別子 |
    | --- | --- |
    | ヨーロッパまたは米国 | `cloudinfra.checkpoint.com` |
    | AP | `ap.portal.checkpoint.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | [応答 URL] |
    | --- | --- |
    | ヨーロッパまたは米国 | `https://portal.checkpoint.com/` |
    | AP | `https://ap.portal.checkpoint.com/` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | サインオン用URL |
    | --- | --- |
    | ヨーロッパまたは米国 | `https://portal.checkpoint.com/` |
    | AP | `https://ap.portal.checkpoint.com/` |
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Check Point Infinity Portal のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

ユーザーを認証するには、2 つの方法があります。

- Azure portal で Check Point Infinity Portal アプリケーションのユーザー ロールを構成する
- Check Point Infinity Portal で Check Point Infinity Portal アプリケーションのユーザー ロールを構成する

##### Azure portal で Check Point Infinity Portal アプリケーションのユーザー ロールを構成する

このセクションでは、管理者ロールと Read-Only ロールを作成します。

1. Azure portal の左側のウィンドウで、[ **アプリの登録**] を選択し、[ **すべてのアプリケーション**] を選択してから、 **Check Point Infinity Portal** アプリケーションを選択します。
2. 左側のウィンドウで、[ **アプリ ロール**] を選択し、[ **アプリ ロールの作成** ] を選択し、次の手順に従います。

    a. [ **表示名** ] フィールドに「 **Admin」**と入力します。

    b。 **[許可されるメンバーの種類] で**、[**ユーザー/グループ**] を選択します。

    c. [ **値** ] フィールドに「 **admin**」と入力します。

    d. [ **説明** ] フィールドに、「 **Check Point Infinity Portal Admin role**」と入力します。

    e. **[このアプリ ロールを有効にする]** オプションが選択されていることを確認します。

    f. [ **適用]** を選択します。

    g. [ **アプリ ロールの作成** ] をもう一度選択します。

    h. [ **表示名** ] フィールドに、「 **読み取り専用」**と入力します。

    一. **[許可されるメンバーの種類] で**、[**ユーザー/グループ**] を選択します。

    j. [ **値** ] フィールドに「 **readonly**」と入力します。

    k. [ **説明** ] フィールドに、「 **Check Point Infinity Portal Admin role**」と入力します。

    l. **[このアプリ ロールを有効にする]** オプションが選択されていることを確認します。

    m. [ **適用]** を選択します。

##### Check Point Infinity Portal で Check Point Infinity Portal アプリケーションのユーザー ロールを構成する

この構成は、Microsoft Entra ID で Check Point Infinity Portal アプリケーションに割り当てられたグループにのみ適用されます。

このセクションでは、関連する Microsoft Entra グループのグローバル ロールとサービス ロールを持つユーザー グループを 1 つ以上作成します。

- Check Point Infinity Portal ユーザー グループで使用するために割り当てられたグループの ID をコピーします。
- ユーザー グループの構成については、 [Infinity Portal 管理ガイド](https://sc1.checkpoint.com/documents/Infinity_Portal/WebAdminGuides/EN/Infinity-Portal-Admin-Guide/Default.htm#cshid=user_groups)を参照してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Check Point Infinity Portal の SSO の構成

1. Check Point Infinity Portal 企業サイトに管理者としてログインします。
2. **[グローバル設定]**&gt;**[アカウント設定]**に移動し、[SSO 認証] で **[定義**] を選択します。

    [Image: 勘定科目]
3. **[SSO 認証**] ページで、**ID プロバイダー**として **SAML 2.0** を選択し、[**次へ**] を選択します。

    [Image: 認証]
4. [ **VERIFY DOMAIN** ]\(ドメインの検証\) セクションで、次の手順を実行します。

    [Image: ドメインの確認]

    a. DNS レコードの値をコピーし、会社の DNS サーバーの DNS 値に追加します。

    b。 [ **ドメイン] フィールド** に会社のドメイン名を入力し、[ **検証**] を選択します。

    c. Check Point により DNS レコード更新が承認されるまで待ちます。最大 30 分かかる場合があります。

    d. ドメイン名が検証されたら、[ **次へ** ] を選択します。
5. [ **接続の許可** ] セクションで、次の手順を実行します。

    [Image: 接続を許可する]

    a. **エンティティ ID の値を**コピーし、[基本的な SAML 構成] セクションの **Microsoft Entra 識別子**テキスト ボックスにこの値を貼り付けます。

    b。 **[応答 URL]** の値をコピーし、[基本的な SAML 構成] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    c. **[サインオン URL**] の値をコピーし、[基本的な SAML 構成] セクションの **[サインオン URL**] テキスト ボックスにこの値を貼り付けます。

    d. **[次へ**] を選択します。
6. **[構成**] セクションで、[**ファイルの選択**] を選択し、ダウンロードした**フェデレーション メタデータ XML** ファイルをアップロードし、[**次へ**] を選択します。

    [Image: の構成]
7. [ **CONFIRM IDENTITY PROVIDER]\(ID プロバイダーの確認** \) セクションで、構成を確認し、[ **SUBMIT]** を選択します。

    [Image: 設定の提出]

#### Check Point Infinity Portal テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Check Point Infinity Portal に作成します。 Check Point Infinity Portal では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Check Point Infinity Portal にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Check Point Infinity Portal のサインオン URL にリダイレクトされます。
- Check Point Infinity Portal のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Check Point Infinity Portal] タイルを選択すると、このオプションは Check Point Infinity Portal のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/checkproof-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に CheckProof を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/checkproof-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-31
- Summary: Microsoft Entra IDから CheckProof にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために CheckProof と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループが [CheckProof](https://checkproof.com) に自動的にプロビジョニングおよび解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- CheckProof でユーザーを作成する
- アクセスが不要になった場合に CheckProof でユーザーを削除する
- Microsoft Entra IDと CheckProof の間でユーザー属性の同期を維持する
- CheckProof でグループとグループ メンバーシップをプロビジョニングする
- CheckProof に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/checkproof-tutorial)する (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- **SCIM プロビジョニング**機能が有効になっている CheckProof アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとCheckProofの間でマッピングするデータを決める。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように CheckProof を構成する

1. [CheckProof 管理者アカウント](https://admin.checkproof.com/login)にログインします。
2. **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)**&gt;**[Company Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社の設定)** に移動します。

    [Image: プロビジョンのスクリーンショット。]
3. **[プロビジョニング] タブを選択します。**
4. **プロビジョニング URL** と**プロビジョニング シークレット トークン**が表示されます。 これらの値は、CheckProof アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: テナントのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから CheckProof を追加する

Microsoft Entra アプリケーション ギャラリーから CheckProof を追加して、CheckProof へのプロビジョニングの管理を開始します。 SSO のために以前に CheckProof を設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: CheckProof への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて CheckProof でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで CheckProof の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、 **[CheckProof]** を選択します。

    [Image: アプリケーションの一覧の CheckProof リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、CheckProof テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが CheckProof に接続できることを確認します。 接続に失敗した場合は、CheckProof アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから CheckProof に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で CheckProof のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、CheckProof API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | roles | 糸 |  |
    | displayName | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | externalId | 糸 |  |
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから CheckProof に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で CheckProof のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
    | members | リファレンス |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/checkproof-tutorial"} -->
## Microsoft Entra ID で CheckProof for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/checkproof-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CheckProof の間でシングル サインオンを構成する方法について説明します。

この記事では、CheckProof と Microsoft Entra ID を統合する方法について説明します。 CheckProof と Microsoft Entra ID を統合すると、次のことができます。

- CheckProof にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して CheckProof に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- CheckProof でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- CheckProof では、 **IDP** によって開始される SSO がサポートされます。
- CheckProof では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/checkproof-provisioning-tutorial)。

### ギャラリーから CheckProof を追加する

Microsoft Entra ID への CheckProof の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に CheckProof を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「CheckProof**」と入力します。
4. 結果パネルから **CheckProof** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### CheckProof の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、CheckProof に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと CheckProof の関連ユーザーとの間にリンク関係を確立する必要があります。

CheckProof で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **CheckProof SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **CheckProof テスト ユーザーの作成** - CheckProof で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[CheckProof]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.checkproof.com/api/v1/saml/<ID>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.checkproof.com/api/v1/saml/<ID>/acs`

    手記

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [CheckProof クライアント サポート チーム](mailto:support@checkproof.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **CheckProof のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### CheckProof SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として CheckProof Web サイトにサインインします。
2. **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) &gt; [Company Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社の設定) &gt; [SAML SETTINGS](SAML 設定)** ページで、**[Federation XML](フェデレーション XML)** テキストボックスから**フェデレーション メタデータ XML** をアップロードします。

    [Image: [SAML 設定] ページ。]

#### CheckProof テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、管理者として CheckProof Web サイトにサインインします。
2. [ **プロファイル** ] を選択し、[ **マイ プロファイル**] を選択します。

    [Image: CheckProof テスト ユーザー ページ。]
3. [**CREATE USER]\(ユーザーの作成\) を選択します**
4. [ **CREATE USER** ]\(ユーザーの作成\) ページで、必要なフィールドに入力し、[保存] を選択 **します**。

    [Image: CheckProof ユーザーページを作成。]

手記

CheckProof では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した CheckProof に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [CheckProof] タイルを選択すると、SSO を設定した CheckProof に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cheetah-for-benelux-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cheetah For Benelux を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cheetah-for-benelux-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cheetah For Benelux の間でシングル サインオンを構成する方法について説明します。

この記事では、Cheetah For Benelux と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Cheetah For Benelux を統合すると、次のことができます。

- Cheetah For Benelux にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して Cheetah For Benelux に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Cheetah For Benelux でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cheetah For Benelux では、 **SP** Initiated SSO がサポートされます。
- Cheetah For Benelux では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Cheetah For Benelux を追加する

Microsoft Entra ID への Cheetah For Benelux の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cheetah For Benelux を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Cheetah For Benelux**」と入力します。
4. 結果パネルから **Cheetah For Benelux** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cheetah For Benelux の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cheetah For Benelux に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Cheetah For Benelux の関連ユーザーとの間にリンク関係を確立する必要があります。

Cheetah For Benelux に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cheetah For Benelux SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cheetah For Benelux テスト ユーザーの作成** - Cheetah For Benelux で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリケーション]**&gt;**[Cheetah For Benelux]**&gt;**[シングル サインオン]** にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **応答 URL** ] ボックスに、URL を入力します。 `https://ups.eu.sso.cheetah.com/saml2/idpresponse`

    b。 [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://ups.eu.sso.cheetah.com/login?client_id=5c2m16mhv4cd4o5cpgekmsmlne&response_type=token&scope=aws.cognito.signin.user.admin+openid+profile&redirect_uri=https://prodeditor.eu.cheetah.com/CssWebTask/landing/?cheetah_client=BNLX`
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Cheetah For Benelux のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成を適切な U R L でコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cheetah For Benelux SSO の構成

**Cheetah For Benelux** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Cheetah For Benelux サポート チーム](mailto:support@cheetah.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cheetah For Benelux テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Cheetah For Benelux に作成します。 Cheetah For Benelux では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Cheetah For Benelux にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cheetah For Benelux のサインオン URL にリダイレクトされます。
- Cheetah For Benelux サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cheetah For Benelux] タイルを選択すると、このオプションは Cheetah For Benelux のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/chengliye-smart-sms-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Chengliye Smart SMS Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chengliye-smart-sms-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Chengliye Smart SMS Platform 間にシングル サインオンを構成する方法について説明します。

この記事では、Chengliye Smart SMS Platform と Microsoft Entra ID を統合する方法について説明します。 Chengliye Smart SMS Platform は2014年に設立され、同社は主にソフトウェア開発と電気通信付加価値サービスを行っています。 SMS 端末やデータ転送などのサービスに特化しています。 Chengliye Smart SMS Platform と Microsoft Entra ID を統合すると、次のことができます。

- Chengliye Smart SMS Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Chengliye Smart SMS Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Chengliye Smart SMS Platform 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Chengliye Smart SMS Platform では、 **IDP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングがサポートされます。

### 前提条件

Microsoft Entra ID を Chengliye Smart SMS Platform と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Chengliye Smart SMS Platform のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Chengliye Smart SMS Platform アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Chengliye Smart SMS Platform を追加する

Microsoft Entra アプリケーション ギャラリーから Chengliye Smart SMS Platform を追加して、Chengliye Smart SMS Platform でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Chengliye Smart SMS Platform**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Chengliye Smart SMS Platform SSO を構成する

**Chengliye Smart SMS Platform** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Chengliye Smart SMS Platform サポート チーム](http://www.cly-chn.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Chengliye Smart SMS Platform テスト ユーザーを作成する

このセクションでは、Chengliye Smart SMS Platform で B.Simon というユーザーを作成します。 Chengliye Smart SMS Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Chengliye Smart SMS Platform にユーザーがまだ存在していない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Chengliye Smart SMS Platform に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Chengliye Smart SMS Platform] タイルを選択すると、SSO を設定した Chengliye Smart SMS Platform に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cherwell-tutorial"} -->
## Microsoft Entra ID で Cherwell for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cherwell-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cherwell の間のシングル サインオンを構成する方法について説明します。

この記事では、Cherwell と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Cherwell を統合すると、次のことができます。

- Cherwell にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Cherwell に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cherwell でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Cherwell は **SP** によって開始された SSO をサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Cherwell の追加

Cherwell の Microsoft Entra ID への統合を構成するには、Cherwell をギャラリーから管理対象 SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Cherwell**」と入力します。
4. 結果パネルから **Cherwell** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cherwell の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Cherwell に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Cherwell での関連ユーザーとの間にリンク関係を確立する必要があります。

Cherwell に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cherwell SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Cherwell テスト ユーザーの作成** - Cherwell で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Cherwell]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.cherwellondemand.com/cherwellclient`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://*.cherwellondemand.com`

    注

    これは実際の値ではありません。 実際のサインオン URL と応答 URL で値を更新してください。 この値を取得するには、 [Cherwell クライアント サポート チーム](https://cherwellsupport.com/CherwellPortal) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Cherwell のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cherwell の SSO の構成

**Cherwell** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Cherwell サポート チーム](https://cherwellsupport.com/CherwellPortal)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

注

Cherwell サポート チームが、実際に SSO を構成する必要があります。 サブスクリプションに対して SSO が有効になっていると、通知が表示されます。

#### Cherwell のテスト ユーザーの作成

Microsoft Entra ユーザーが Cherwell にサインインできるようにするには、そのユーザーを Cherwell にプロビジョニングする必要があります。 Cherwell の場合は、 [Cherwell サポート チーム](https://cherwellsupport.com/CherwellPortal)がユーザー アカウントを作成する必要があります。

注

Cherwell から提供されている他の Cherwell ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Cherwell のサインオン URL にリダイレクトされます。
- Cherwell のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cherwell] タイルを選択すると、SSO を設定した Cherwell に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/chromeriver-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Chromeriver を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chromeriver-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Chromeriver 間にシングル サインオンを構成する方法について学習します。

この記事では、Chromeriver と Microsoft Entra ID を統合する方法について説明します。 Chromeriver を Microsoft Entra ID と統合すると、次のことができます:

- Chromeriver にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Chromeriver に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Chromeriver でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Chromeriver では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Chromeriver の追加

Microsoft Entra ID への Chromeriver の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Chromeriver を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Chromeriver**」と入力します。
4. 結果のパネルから **[Chromeriver]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Chromeriver 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Chromeriver に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Chromeriver の関連ユーザーの間にリンク関係を確立する必要があります。

Chromeriver に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Chromeriver の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Chromeriver のテストユーザーを作成する** - Microsoft Entra のユーザーとリンクされた Chromeriver 上に B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Chromeriver**&gt;**シングル サインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.chromeriver.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.chromeriver.com/login/sso/saml/consume?customerId=<uniqueid>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Chromeriver クライアント サポート チーム](https://www.chromeriver.com/services/support)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Chromeriver のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Chromeriver の SSO の構成

**Chromeriver** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Chromeriver サポート チーム](https://www.chromeriver.com/services/support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Chromeriver のテスト ユーザーの作成

Microsoft Entra ユーザーが Chromeriver にログインできるようにするには、ユーザーを Chromeriver にプロビジョニングする必要があります。 Chromeriver の場合、[Chromeriver サポート チーム](https://www.chromeriver.com/services/support)がユーザー アカウントを作成する必要があります。

注

Chromeriver から提供されている他の Chromeriver ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Chromeriver に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Chromeriver] タイルを選択すると、SSO を設定した Chromeriver に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/chronicx-tutorial"} -->
## Microsoft Entra ID で ChronicX® for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chronicx-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ChronicX® 間にシングル サインオンを構成する方法について学習します。

この記事では、ChronicX® と Microsoft Entra ID を統合する方法について説明します。 ChronicX® を Microsoft Entra ID と統合すると、次のことができます。

- ChronicX® にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ChronicX® に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ChronicX® シングル サインオン (SSO) 対応サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ChronicX® では、 **SP** Initiated SSO がサポートされます。
- ChronicX® では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ChronicX® 追加

Microsoft Entra ID への ChronicX® の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ChronicX® を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ChronicX®**」と入力します。
4. 結果パネルから **ChronicX®** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ChronicX® 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ChronicX® に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ChronicX® の関連ユーザーとの間にリンク関係を確立する必要があります。

ChronicX® に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ChronicX SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ChronicX テスト ユーザーの作成** - ChronicX® で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**ChronicX®**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `ups.chronicx.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.chronicx.com/ups/processlogonSSO.jsp`

    注

    サインオン URL の値は実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、ChronicX® クライアント サポート チーム](https://www.casebank.com/contact-us/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **ChronicX® のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 設定を適切な URL によってコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ChronicX SSO の構成

**ChronicX®** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [ChronicX® サポート チーム](https://www.casebank.com/contact-us/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ChronicX テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ChronicX® 内に作成します。 ChronicX® では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 ChronicX® にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [ChronicX® サポート チーム](https://www.casebank.com/contact-us/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる ChronicX® Sign-On URL にリダイレクトされます。
- ChronicX® サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ChronicX®] タイルを選択すると、このオプションは ChronicX® Sign-On URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/chronus-saml-tutorial"} -->
## Microsoft Entra ID と共にシングル サインオン用の Chronus SAML を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chronus-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Chronus SAML との間でシングル サインオンを構成する方法について説明します。

この記事では、Chronus SAML と Microsoft Entra ID を統合する方法について説明します。 Chronus SAML を Microsoft Entra ID と統合すると、次のことができます。

- Chronus SAML にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Chronus SAML に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Chronus SAML でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Chronus SAML は、**SP と IDP** による SSO の開始をサポートしています。
- Chronus SAML では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Chronus SAML の追加

Microsoft Entra ID への Chronus SAML の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Chronus SAML を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「Chronus SAML**」と入力します。
4. 結果パネルから **Chronus SAML** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Chronus SAML に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Chronus SAML に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Chronus SAML での関連ユーザーとの間にリンク関係を確立する必要があります。

Chronus SAML に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Chronus SAML SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Chronus SAML テストユーザーの作成** - Chronus SAML で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Chronus SAML**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `<CustomerName>.domain.extension`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.domain.extension/session`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.domain.extension/session`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Chronus SAML クライアント サポート チーム](mailto:support@chronus.com) に問い合わせてください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Chronus SAML のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 設定を適切な URL にコピーするためのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Chronus SAML の SSO の構成

Chronus SAML 側でシングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Chronus SAML サポート チーム](mailto:support@chronus.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Chronus SAML のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Chronus SAML に作成します。 Chronus SAML では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Chronus SAML にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Chronus SAML サインオン URL にリダイレクトされます。
- Chronus SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Chronus SAML に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Chronus SAML] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Chronus SAML に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cic-intelligent-compensation-control-tutorial"} -->
## Microsoft Entra ID とのシングルサインオンのための CIC の構成 - Controle Inteligente de Compensação - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cic-intelligent-compensation-control-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CIC - Controle Inteligente de Compensação の間でシングル サインオンを構成する方法について説明します。

この記事では、CIC - Controle Inteligente de Compensação と Microsoft Entra ID を統合する方法について説明します。 CIC - Controle Inteligente de Compensação と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で、誰が Controle Inteligente de Compensação (CIC) にアクセスできるかを制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して CIC - Controle Inteligente de Compensação に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- CIC - Controle Inteligente de Compensação でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- CIC - Controle Inteligente de Compensação では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### CIC - Controle Inteligente de Compensação をギャラリーから追加する

Microsoft Entra ID への CIC - Controle Inteligente de Compensação の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に CIC - Controle Inteligente de Compensação を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**CIC - Controle Inteligente de Compensação**」と入力します。
4. 結果パネルから **CIC - Controle Inteligente de Compensação** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### CIC の Microsoft Entra SSO の構成とテスト - Controle Inteligente de Compensação

**B.Simon** というテスト ユーザーを使用して、CIC - Controle Inteligente de Compensação に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと CIC - Controle Inteligente de Compensação の関連ユーザーとの間にリンク関係を確立する必要があります。

CIC - Controle Inteligente de Compensação に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **CIC の構成 - Controle Inteligente de Compensação SSO**- アプリケーション側でシングル サインオン設定を構成します。
    1. **CIC - Controle Inteligente de Compensação テスト ユーザーの作成** - CIC - Controle Inteligente de Compensação で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**CIC - Controle Inteligente de Compensação**&gt;**Single のサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `cic-prod`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://prodgtw.perdcomp.com.br/auth/login/saml/callback`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://perdcomp.com.br/`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. 「設定 CIC - Controle Inteligente de Compensação」セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### CIC - Controle Inteligente de Compensação SSO の構成

**CIC - Controle Inteligente de Compensação** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と Microsoft Entra 管理センターからコピーした適切な URL を [CIC - Controle Inteligente de Compensação サポート チーム](mailto:cicsso@perdcomp.com.br)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### CIC - Controle Inteligente de Compensação テスト ユーザーの作成

このセクションでは、CIC - Controle Inteligente de Compensação で B.Simon というユーザーを作成します。 [CIC - Controle Inteligente de Compensação サポート チーム](mailto:cicsso@perdcomp.com.br)と協力して、CIC - Controle Inteligente de Compensação プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる CIC - Controle Inteligente de Compensação のサインオン URL にリダイレクトされます。
- CIC - Controle Inteligente de Compensação のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [CIC - Controle Inteligente de Compensação] タイルを選択すると、このオプションは CIC - Controle Inteligente de Compensação のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cimpl-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cimpl を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cimpl-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cimpl の間のシングル サインオンを構成する方法について説明します。

この記事では、Cimpl と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Cimpl を統合すると、次のことができます。

- Cimpl へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Cimpl に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cimpl でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Cimpl では、 **SP** Initiated SSO がサポートされます。

### ギャラリーから Cimpl を追加する

Microsoft Entra ID への Cimpl の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cimpl を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Cimpl**」と入力します。
4. 結果パネルから **Cimpl** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cimpl の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Cimpl に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Cimpl の関連ユーザーとの間にリンク関係を確立する必要があります。

Cimpl に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cimpl SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cimpl のテスト ユーザーの作成** - Cimpl で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cimpl**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.etelesolv.com/<TENANTNAME>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.etelesolv.com/<TENANTNAME>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 **+1 866-982-8250** の Cimpl チームにお問い合わせください。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Cimpl のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cimpl の SSO の構成

**Cimpl** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とコピーした適切な URL をアプリケーション構成から Cimpl サポート (**+1 866-982-8250**) に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cimpl のテスト ユーザーの作成

このセクションの目的は、Cimpl で Britta Simon というユーザーを作成することです。 **+1 866-982-8250** で Cimpl のサポートを使用して、Cimpl アカウントにユーザーを追加します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cimpl のサインオン URL にリダイレクトされます。
- Cimpl のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cimpl] タイルを選択すると、このオプションは Cimpl のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cinode-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Cinode を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cinode-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-31
- Summary: Microsoft Entra IDから Cinode にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Cinode と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループが自動的に [Cinode](https://cinode.com/) にプロビジョニングおよびプロビジョニング解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Cinode にユーザーを作成する
- アクセスが不要になった場合に Cinode のユーザーを削除する
- Microsoft Entra IDと Cinode の間でユーザー属性の同期を維持する
- Cinode にグループとグループ メンバーシップをプロビジョニングする
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 管理者権限を持つ Cinode のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとCinodeの間でどのデータを[対応付ける](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)かを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Cinode を構成する

1. 管理者権限を持つユーザー アカウントで Cinode にサインインします。 **[管理]** に移動します。
2. **[統合**] に移動します。
3. **[トークン**] に移動し、新しいトークンを作成します。
4. 一意の名前を入力し、[ **対象ユーザー] として [https://api.cinode.app/scim/v2]** を選択し、有効期限を適切に設定します。
5. [ **トークンの作成] を選択します**。

[Image: [トークンの作成] のスクリーンショット。]

1. **テナント URL** と**トークン**をコピーします。 これらの値は、Cinode アプリケーションの [プロビジョニング] タブに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Cinode を追加する

Microsoft Entra アプリケーション ギャラリーから Cinode を追加して、Cinode へのプロビジョニングの管理を開始します。 SSO のために Cinode を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Cinode への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Cinode の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Cinode**] を選択します。

    [Image: アプリケーションの一覧の [Cinode] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Cinode テナント URL とシークレット トークンを入力します。 **[接続のテスト**を選択して、Microsoft Entra IDが Cinode に接続できることを確認します。 接続に失敗した場合は、Cinode アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cinode に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Cinode のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Cinode API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | externalId | 糸 |
    | 活動中 | ブール値 |
    | タイトル | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cinode に同期されるグループ属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Cinode 内のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | externalId | 糸 |
    | members | リファレンス |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/circus-street-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にチルコ ストリートを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/circus-street-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Circus Street の間でシングル サインオンを構成する方法について説明します。

この記事では、Circus Street と Microsoft Entra ID を統合する方法について説明します。 Circus Street は、独自のプラットフォームを通じて組織に eコマース、データ分析、デジタルマーケティングなどのデジタル トレーニングを提供するグローバル リーダーです。

Circus Street を Microsoft Entra ID を統合すると、以下のことができます。

- Microsoft Entra ID を使用して、Circus Street にアクセスできるユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Circus Street に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Circus Street 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Circus Street は、**SP** および **IDP** によるシングル サインオンをサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Circus Street と統合するには、以下のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/free/)を取得できます。
- Circus Street でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Circus Street アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Circus Street を追加する

Microsoft Entra アプリケーション ギャラリーから Circus Street を追加して、Circus Street とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Circus Street**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure に事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerSubDomainName>.circusstreet.com`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには [、チルコ ストリート サポート チーム](mailto:support@circusstreet.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Circus Street アプリケーションは、特定の形式の SAML アサーションを想定しているため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記のことに加えて、Circus Street アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Eメール | ユーザーのメールアドレス |
    | 名 | ユーザー.ファーストネーム |
    | 姓 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **サーカスストリートのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Circus Street SSO を構成する

**Circus Street** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を[、Circus Street サポート チーム](mailto:support@circusstreet.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Circus Street のテスト ユーザーを作成する

このセクションでは、 [チルコ ストリート のサポート チーム](mailto:support@circusstreet.com) に連絡して、サーカス ストリート プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる、チルコ ストリート のサインオン URL にリダイレクトされます。
- Circus Street のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Circus Street に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Circus Street] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Circus Street に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cirrus-identity-bridge-for-azure-ad-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cirrus Identity Bridge for Microsoft Entra ID を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cirrus-identity-bridge-for-azure-ad-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cirrus Identity Bridge for Microsoft Entra ID の間でシングル サインオンを構成する方法について説明します。

この記事では、Microsoft Graph API ベースの統合パターンを使用して、Cirrus Identity Bridge for Microsoft Entra ID と Microsoft Entra ID を統合する方法について説明します。 この方法で Cirrus Identity Bridge for Microsoft Entra ID と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID から InCommon またはその他の多国間フェデレーション サービス プロバイダーにアクセスできるユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントで InCommon またはその他の多国間フェデレーション サービス プロバイダーに SSO を実行できるようにします。
- ユーザーが自分の Microsoft Entra アカウントを使用して Central Authentication Service (CAS) アプリケーションにアクセスできるようにします。
- 1 つの中央サイトで自分のアプリケーション アクセスを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cirrus Identity Bridge for Microsoft Entra でのシングル サインオン (SSO) が有効になったサブスクリプション。 まだサブスクライバーでない場合は、 [Cirrus Identity Microsoft Entra ID Bridge の登録ページ](https://info.cirrusidentity.com/cirrus-identity-azure-ad-app-gallery-registration)にアクセスしてください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cirrus Identity Bridge for Microsoft Entra ID では、**SP** initiated SSO と **IDP** initiated SSO がサポートされます。

### ギャラリーから Cirrus Identity Bridge for Microsoft Entra ID を追加する前に

Cirrus Identity Bridge for Microsoft Entra ID をサブスクライブすると、Microsoft Entra TenantID の入力が求められます。 これを表示するには、次のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Overview**&gt;**Properties** に移動します。
3. [ **テナント ID** ] セクションまで下にスクロールすると、ボックスにテナント ID が表示されます。
4. 値をコピーし、使用している Cirrus ID コントラクトの担当者に送信します。

Microsoft Graph API 統合を使用するには、テナントで API を使用するために Cirrus Identity Bridge for Microsoft Entra ID アクセス権を付与する必要があります。 これを行うには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. URL`https://login.microsoftonline.com/$TENANT_ID/adminconsent?client_id=ea71bc49-6159-422d-84d5-6c29d7287974&state=12345&redirect_uri=https://admin.cirrusidentity.com/azure-registration` を編集して、**$TENANT\_ID** を Microsoft Entra テナントの値に置き換えます。
3. サインインしているブラウザーに URL を貼り付けます。
4. アクセス権の付与に同意するように求められます。
5. 成功すると、Cirrus Bridge API という新しいアプリケーションができます。
6. 使用している Cirrus ID コントラクトの担当者に、Cirrus Identity Bridge for Microsoft Entra ID への API アクセスが正常に許可されたことを通知します。

Cirrus Identity がテナント ID を取得し、アクセス権が付与されると、Microsoft Entra インフラストラクチャ用の Cirrus Identity Bridge がプロビジョニングされ、サブスクリプションに固有の次の情報が提供されます。

- 識別子 URI/エンティティ ID
- リダイレクト URI/応答 URL
- シングル ログアウト URL
- SP 暗号化証明書 (暗号化されたアサーションまたはログアウトを使用している場合)
- テスト用の URL
- サブスクリプションに含まれるオプションに応じた追加の手順

注

Cirrus Identity Bridge for Microsoft Entra ID への API アクセスを許可できない場合は、従来の SAML2 統合を使用してブリッジを統合できます。 MS Graph API 統合を使用できないことを、使用している Cirrus Identity コントラクトの担当者にアドバイスします。

### ギャラリーからの Cirrus Identity Bridge for Microsoft Entra ID の追加

Microsoft Entra ID への Cirrus Identity Bridge for Microsoft Entra ID の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cirrus Identity Bridge for Microsoft Entra ID を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;で**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Cirrus Identity Bridge for Microsoft Entra ID**」と入力します。
4. 結果パネルから **Cirrus Identity Bridge for Microsoft Entra ID を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cirrus Identity Bridge for Microsoft Entra ID 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cirrus Identity Bridge for Microsoft Entra ID に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Cirrus Identity Bridge for Microsoft Entra ID の関連ユーザーとの間にリンク関係を確立する必要があります。

Cirrus Identity Bridge for Microsoft Entra ID で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cirrus Identity Bridge for Microsoft Entra SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Microsoft Entra テスト用に Cirrus Identity Bridge をセットアップ**し、Cirrus Identity Bridge for Microsoft Entra ID 内で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Cirrus Identity Bridge for Microsoft Entra ID** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて**、[プロパティ**] を選択します。
3. [ **プロパティ** ] ページで、アクセス要件に基づいて **[割り当てが必要]** を切り替えます。 **[はい**] に設定した場合は、[**ユーザーとグループ**] ページのアクセス制御グループに **Cirrus Identity Bridge for Microsoft Entra ID** アプリケーションを割り当てる必要があります。
4. **[プロパティ**] ページで、[ユーザーに**表示]** を **[いいえ**] に切り替えます。 初回の統合では、複数のサービス プロバイダーに使用される既定の統合が常に表されます。 この場合、エンド ユーザーを誘導するサービス プロバイダーは 1 つも存在しません。 エンド ユーザーに特定のアプリケーションを表示するには、シングル サインオンのリンクを使用して、エンド ユーザーに特定のサービス プロバイダーへのアクセス権をマイ アプリに付与する必要があります。 [詳細については、こちらを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-linked-sign-on) 参照してください。
5. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
6. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[Cirrus Identity Bridge for Microsoft Entra ID]**&gt;**[Single サインオン]** に移動します。
7. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
8. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
9. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ． [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN>/bridge`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<NAME>.proxy.cirrusidentity.com/module.php/saml/sp/saml2-acs.php/<NAME>_proxy`
10. SP 開始モードでアプリケーションを構成する場合は、[追加の URL の設定] を選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<CUSTOMER_LOGIN_URL>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 Cirrus Bridge にまだ登録していない場合は、 [登録ページ](https://info.cirrusidentity.com/cirrus-identity-azure-ad-app-gallery-registration)にアクセスしてください。 既存の Cirrus Bridge のお客様は、 [Cirrus Identity Bridge for Microsoft Entra クライアント サポート チーム](https://www.cirrusidentity.com/resources/service-desk) に連絡してこれらの値を取得してください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
11. Cirrus Identity Bridge for Microsoft Entra アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
12. Cirrus Identity Bridge for Microsoft Entra は、InCommon 信頼フェデレーションで一般的に使用される **属性と要求** を事前に設定します。 要件を満たすように、それらを確認して変更できます。 詳細については、 [eduPerson スキーマの仕様](https://wiki.refeds.org/display/STAN/eduPerson) を参照してください。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:2.5.4.42 | User.givenname |
    | urn:oid:2.5.4.4 | ユーザーの名字 |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
    | urn:oid:1.3.6.1.4.1.5923.1.1.1.6 | user.userprincipalname |
    | cirrus.nameIdFormat | "urn:oasis:names:tc:SAML:2.0:nameid-format:transient" |

    注

    これらの既定値は、Microsoft Entra UPN が eduPersonPrincipalName としての使用に適していることを前提としています。
13. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cirrus Identity Bridge for Microsoft Entra の SSO の構成

Cirrus Bridge の構成に関するその他のドキュメントは、 [Cirrus Identity から](https://blog.cirrusidentity.com/documentation/azure-bridge-setup)入手できます。 また、CAS サービスのアクセスをサポートするように Cirrus Bridge を構成するために、 [Cirrus Bridge](https://blog.cirrusidentity.com/documentation/cas-bridge-setup) の CAS サポートも利用できます。

#### Microsoft Entra テスト用に Cirrus Identity Bridge をセットアップする

このセクションでは、Britta Simon というユーザーをテストに使用できることを確認します。 [Cirrus Identity Bridge for Microsoft Entra サポート チーム](https://www.cirrusidentity.com/resources/service-desk)は、Britta Simon が Microsoft Entra プラットフォーム用 Cirrus Identity Bridge で使用できる状態であることを確認するためのテスト URL を提供します。 テスト ユーザーの Britta Simon は、Cirrus Identity Bridge for Microsoft Entra ID を認証方法として使用するアプリケーション (多角的なフェデレーション メタデータを使用するアプリケーションなど) にも追加する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Cirrus Identity Bridge for Microsoft Entra ID のサインオン URL にリダイレクトされます。
- Cirrus Identity Bridge for Microsoft Entra のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Cirrus Identity Bridge for Microsoft Entra ID に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Cirrus Identity Bridge for Microsoft Entra ID] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Cirrus Identity Bridge for Microsoft Entra ID に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-expressway-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cisco Expressway を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-expressway-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cisco Expressway の間のシングル サインオンを構成する方法について説明します。

この記事では、Cisco Expressway を Microsoft Entra ID と統合する方法について説明します。 Cisco Expressway は、IP テレフォニー システムの通話制御と関連機能を提供するアプリケーション スイートで、メディア フローがある場合はメディア品質分析用のツールも提供します。 Cisco Expressway を Microsoft Entra ID と統合すると、次のことが可能になります。

- Cisco Expressway にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cisco Expressway に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Cisco Expressway に対して Microsoft Entra シングル サインオンを構成してテストします。 Cisco Expressway では、 **SP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

Microsoft Entra ID を Cisco Expressway と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Cisco Expressway でのシングル サインオン (SSO) に対応したサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Cisco Expressway アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Cisco Expressway を追加する

Microsoft Entra アプリケーション ギャラリーから Cisco Expressway を追加して、Cisco Expressway でのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Cisco Expressway]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** がある場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする方法を示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択して参照する方法を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    注

    **Cisco Expressway サポート チーム**から[サービス プロバイダー メタデータ ファイル](mailto:Tp-global@cisco.com)を取得します。 **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. Cisco Expressway アプリケーションでは、特定の形式の SAML アサーションを想定しているため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. また、Cisco Expressway アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | uid (ユーザー識別子) | user.onpremisessamaccountname |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. **Cisco Expressway の設定**セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

### Cisco Expressway の SSO を構成する

**Cisco Expressway** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Cisco Expressway サポート チーム](mailto:Tp-global@cisco.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cisco Expressway のテスト ユーザーを作成する

このセクションでは、Cisco Expressway で Britta Simon というユーザーを作成します。 [Cisco Expressway サポート チーム](mailto:Tp-global@cisco.com)と協力して、Cisco Expressway プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Cisco Expressway のサインオン URL にリダイレクトされます。
- Cisco Expressway のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Cisco Expressway タイルを選択すると、このオプションは Cisco Expressway のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-intersight-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cisco Intersight を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-intersight-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cisco Intersight の間のシングル サインオンを構成する方法について説明します。

この記事では、Cisco Intersight と Microsoft Entra ID を統合する方法について説明します。 Cisco Intersight を Microsoft Entra ID と統合すると、次のことが可能になります。

- Cisco Intersight にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cisco Intersight に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cisco Intersight でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cisco Intersight では、 **SP** Initiated SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Cisco Intersight の追加

Microsoft Entra ID への Cisco Intersight の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cisco Intersight を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;にブラウズして、**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Cisco Intersight**」と入力します。
4. 結果パネルから **Cisco Intersight** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

エンタープライズ アプリケーションの追加に関する詳細なガイダンスについては、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を参照してください。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Cisco Intersight に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cisco Intersight に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Cisco Intersight の関連ユーザーとの間にリンク関係を確立する必要があります。

Cisco Intersight で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cisco Intersight の SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Cisco Intersight**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://intersight.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `www.intersight.com`
6. Cisco Intersight アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットはその例です。 **一意ユーザー識別子**の既定値は **user.userprincipalname** ですが、Cisco Intersight ではこれがユーザーの電子メール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. その他に、Cisco Intersight アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | First\_Name | User.givenname |
    | Last\_Name | User.surname |
    | memberOf | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. 「 **Cisco Intersight のセットアップ** 」セクションで、要件に基づいて 1 つ以上の適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cisco Intersight の SSO の構成

**Cisco Intersight** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Cisco Intersight サポート チーム](mailto:intersight-feedback@cisco.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Cisco Intersight のテスト ユーザーの作成

#### Cisco Intersight 用に Microsoft Entra SSO を構成してテストする

Cisco Intersight の SSO 構成はセルフサービスになり、Cisco Intersight プラットフォームを介して直接管理されるようになりました。 セットアップを完了するには、Cisco Intersight ヘルプ センターにアクセスし、up-to-date ガイダンスに従ってください: [Cisco Intersight ヘルプ ドキュメント](https://www.intersight.com/help/saas)

##### 手順の概要:

1. Cisco Intersight にサインインします。
    1. **[設定]** セクションに移動します。
    2. **[SSO 構成]** ページにアクセスします。
2. Cisco Intersight ヘルプ ドキュメントに記載されているセルフサービスのセットアップ手順に従って、必要な SAML 属性、メタデータ、証明書の詳細を設定します。
3. Microsoft Entra ID と Cisco Intersight の両方で利用できる組み込みのテスト ツールを使用して統合をテストし、シームレスな SSO を確保します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは Cisco Intersight のサインオン URL にリダイレクトされ、そこでサインイン フローを開始できます。
- Cisco Intersight のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Cisco Intersight] タイルを選択すると、このオプションは Cisco Intersight のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-secure-firewall-secure-client"} -->
## Cisco Secure Firewall - Secure Client を Microsoft Entra ID によるシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-secure-firewall-secure-client
- Service: entra-id / saas-apps
- Article date: 2026-10-01
- Summary: Microsoft Entra ID と Cisco Secure Firewall - Secure Client の間でシングル サインオンを構成する方法について説明します。

この記事では、Cisco Secure Firewall - Secure Client と Microsoft Entra ID を統合する方法について説明します。 Cisco Secure Firewall - Secure Client と Microsoft Entra ID を統合すると、次のことができます。

- Cisco Secure Firewall - Secure Client にアクセスできるユーザーの Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cisco Secure Firewall - Secure Client に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cisco Secure Firewall - Secure Client でのシングル サインオン (SSO) が有効なサブスクリプションを提供します。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cisco Secure Firewall - Secure Client では、**IDP** によって開始される SSO のみがサポートされます。

### ギャラリーからの Cisco Secure Firewall - Secure Client の追加

Cisco Secure Firewall - Secure Client の Microsoft Entra ID への統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cisco Secure Firewall - Secure Client を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Cisco Secure Firewall - Secure Client**」と入力します。
4. 結果パネルから **Cisco Secure Firewall - Secure Client** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cisco Secure Firewall - Secure Client の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Cisco Secure Firewall - Secure Client に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Cisco Secure Firewall - Secure Client の関連ユーザーとの間にリンク関係を確立する必要があります。

Cisco Secure Firewall - Secure Client に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cisco Secure Firewall - Secure Client SSO**- 構成してアプリケーション側でシングル サインオン設定を構成します。
    1. **Cisco Secure Firewall - Secure Client のテストユーザーの作成** - Cisco Secure Firewall - Secure Client で B.Simon に対応するユーザーを作成し、それを Microsoft Entra での B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cisco Secure Firewall - Secure Client**&gt;**シングル サインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. **[SAML でシングル サインオンをセットアップします]** ページで、次のフィールドの値を入力します。

    1. **[識別子]** ボックスに、次の形式で URL を入力します。`https://<YOUR_CISCO_FQDN>/saml/sp/metadata/<Tunnel_Group_Name>`
    2. **[応答 URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<YOUR_CISCO_ANYCONNECT_FQDN>/+CSCOE+/saml/sp/acs?tgname=<Tunnel_Group_Name>`

    Important

    この記事で示す **識別子** と **応答 URL** の値は、例のみです。 SSO を設定する前に、これらの値が Cisco セキュア ファイアウォール環境の正しいエンドポイントを参照していることを確認します。 本番環境では、サンプルドメインやプレースホルダードメインを使用しないでください。 適切な値については、Cisco TAC または [Cisco Secure Firewall - Secure Client サポートチーム](https://www.cisco.com/c/en/us/support/index.html) にお問い合わせください。

    注

    `<Tunnel_Group_Name>` では大文字と小文字が区別され、値にドット (".") とスラッシュ ("/") を含めることはできません。

    注

    これらの値の詳細については、Cisco TAC のサポートに問い合わせてください。 これらの値を実際の識別子と、Cisco TAC から提供された応答 URL の値で更新します。 これらの値を取得するには、[Cisco Secure Firewall - Secure Client サポート チーム](https://www.cisco.com/c/en/us/support/index.html)に問い合わせてください。 **基本的なSAML構成** セクションに示されているパターンも参照できます。

    Important

    **セキュリティのベスト プラクティス:** 新規および既存のデプロイの場合は、SAML SSO を有効にする前に、構成されているすべての **識別子** と **応答 URL** の値が目的のエンドポイントと信頼されたドメインを参照していることを確認します。 例またはプレースホルダー ドメインはドキュメントのみを目的としており、運用環境では使用しないでください。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけ、**[ダウンロード]** を選択して証明書ファイルをダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **Cisco Secure Firewall - Secure Client のセットアップ**に関するセクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の URL をコピーする方法を示すスクリーンショット。]

注

サーバーの複数の TGT をオンボードする場合は、ギャラリーから Cisco Secure Firewall - Secure Client アプリケーションの複数のインスタンスを追加する必要があります。 これらすべてのアプリケーション インスタンスについて、Microsoft Entra ID に独自の証明書をアップロードすることもできます。 このようにアプリケーションに同じ証明書を使用できる一方で、アプリケーションごとに異なる識別子と応答 URL を構成することができます。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cisco Secure Firewall - Secure Client SSO の構成

1. 最初に CLI でこれを行いますが、別の機会に戻って ASDM のウォークスルーを行うかもしれません。
2. VPN アプライアンスに接続すると、9.8 コード トレーニングを実行する ASA が使用され、VPN クライアントは 4.6 以降になります。
3. まず、トラストポイントを作成し、SAML 証明書をインポートします。

    ```
     config t
    
     crypto ca trustpoint AzureAD-AC-SAML
       revocation-check none
       no id-usage
       enrollment terminal
       no ca-check
     crypto ca authenticate AzureAD-AC-SAML
     -----BEGIN CERTIFICATE-----
     …
     PEM Certificate Text from download goes here
     …
     -----END CERTIFICATE-----
     quit
    ```
4. 次のコマンドを実行すると、SAML IdP がプロビジョニングされます。

    ```
     webvpn
     saml idp https://sts.windows.net/xxxxxxxxxxxxx/ (This is your Azure AD Identifier from the Set up Cisco Secure Firewall - Secure Client section in the Azure portal)
     url sign-in https://login.microsoftonline.com/xxxxxxxxxxxxxxxxxxxxxx/saml2 (This is your Login URL from the Set up Cisco Secure Firewall - Secure Client section in the Azure portal)
     url sign-out https://login.microsoftonline.com/common/wsfederation?wa=wsignout1.0 (This is Logout URL from the Set up Cisco Secure Firewall - Secure Client section in the Azure portal)
     trustpoint idp AzureAD-AC-SAML
     trustpoint sp (Trustpoint for SAML Requests - you can use your existing external cert here)
     no force re-authentication
     no signature
     base-url https://my.asa.com
    ```
5. これで、VPN トンネル構成に SAML 認証を適用できるようになります。

    ```
    tunnel-group AC-SAML webvpn-attributes
       saml identity-provider https://sts.windows.net/xxxxxxxxxxxxx/
       authentication saml
    end
    
     write mem
    ```

    注

    SAML IdP 構成に関する回避策があります。 IdP の構成に変更を加えた場合は、変更を有効にするために、トンネル グループから SAML ID プロバイダーの構成を削除し、再度適用する必要があります。

#### Cisco Secure Firewall - Secure Client テスト ユーザーの作成

このセクションでは、Cisco Secure Firewall - Secure Client で Britta Simon というユーザーを作成します。 [Cisco Secure Firewall - Secure Client サポート チーム](https://www.cisco.com/c/en/us/support/index.html)と協力して、Cisco Secure Firewall - Secure Client プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Cisco Secure Firewall - Secure Client に自動的にサインインします
- Microsoft アクセス パネルを使用することができます。 アクセス パネルで [Cisco Secure Firewall - Secure Client] タイルを選択すると、SSO を設定した Cisco Secure Firewall - Secure Client に自動的にサインインします。 アクセス パネルの詳細については、[アクセス パネルの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-spark-tutorial"} -->
## Microsoft Entra ID で Cisco Webex for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-spark-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cisco Webex の間でシングル サインオンを構成する方法について説明します。

この記事では、Cisco Webex と Microsoft Entra ID を統合する方法について説明します。 Cisco Webex と Microsoft Entra ID を統合すると、以下が可能になります。

- Cisco Webex にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して Cisco Webex に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cisco Webex でのシングル サインオン (SSO) が有効なサブスクリプション。
- Cisco Webex のサービス プロバイダー メタデータ ファイル。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cisco Webex では、**SP** Initiated SSO がサポートされます。
- Cisco Webex では、[**自動化されたユーザー プロビジョニング**](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-webex-provisioning-tutorial)がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Cisco Webex の追加

Microsoft Entra ID への Cisco Webex の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cisco Webex を追加する必要があります。

1. [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上として [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Cisco Webex**」と入力します。
4. 結果パネルで **[Cisco Webex]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Clebex Webex に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cisco Webex に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Cisco Webex の関連ユーザーとの間にリンク関係を確立する必要があります。

Cisco Webex に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成して**、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成**し、B.Simon を使用して Microsoft Entra シングル サインオンをテストします。
    2. **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **Cisco Webex SSO の構成**- アプリケーション側で SSO 設定を構成します。
    1. **Cisco Webex でテストユーザーを作成し**、Microsoft Entra のユーザー表現にリンクして B.Simon に対応させます。
3. **SSO をテスト**して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上として [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cisco Webex** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、ダウンロードした**サービス プロバイダー メタデータ ファイル**をアップロードし、次の手順を実行してアプリケーションを構成します。

    Note

    サービス プロバイダー メタデータ ファイルは、「 **Cisco Webex の構成」** セクションから取得します。これについては、この記事の後半で説明します。

    a。 [ **メタデータ ファイルのアップロード]** を選択します。

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    c. サービス プロバイダー メタデータ ファイルのアップロードが正常に完了すると、次のように、**識別子**と**応答 URL** の値が **[基本的な SAML 構成]** セクションに自動的に入力されます。

    d. **[サインオン URL]** ボックスに、`https://web.ciscospark.com/idb/Consumer/metaAlias/<ID>/sp` のパターンを使用して URL を入力します。

    Note

    この値は実際の値ではありません。 応答 URL のリテラル値をコピーし、この値を `https://web.ciscospark.com/` に追加して、実際のサインオン URL の値を作成します。
6. Cisco Webex アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
7. その他に、Cisco Webex アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | uid (ユーザー識別子) | user.userprincipalname |

    Note

    ソース属性値は、既定で userprincipalname にマップされます。 これは、user.mail か user.onpremiseuserprincipalname のほか、Webex の設定に基づく任意の値に変更することができます。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cisco Webex SSO の構成

1. 管理者の資格情報を使用して Cisco Webex にサインインします。
2. [ **組織の設定] を** 選択し、[ **認証** ] セクションで [ **変更**] を選択します。

    [Image: スクリーンショットは、[変更] を選択できる [Authentication Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証設定) を示しています。]
3. [ **サードパーティ ID プロバイダーの統合] を選択します。(詳細)** をクリックし、[ **次へ**] を選択します。

    [Image: サード パーティの ID プロバイダーの統合を示すスクリーンショット。]
4. [ **メタデータ ファイルのダウンロード** ] を選択して **サービス プロバイダー メタデータ ファイル** をダウンロードし、コンピューターに保存し、[ **次へ**] を選択します。

    [Image: サービス プロバイダー メタデータ ファイルを示すスクリーンショット。]
5. **ファイル ブラウザー** オプションを選択して、Microsoft Entra メタデータ ファイルを見つけてアップロードします。 次に、 **メタデータで証明機関によって署名された証明書が必要 (より安全)** を選択し、[ **次へ**] を選択します。

    [Image: スクリーンショットは、[Import Idp Metadata] (IdP メタデータのインポート) ページを示しています。]
6. **[SSO 接続のテスト]** を選択し、ブラウザーの新しいタブが開いたら、サインインして Microsoft Entra ID で認証します。
7. **Cisco Cloud Collaboration Management** ブラウザー タブに戻ります。テストが成功した場合は、[**このテストが成功しました] を選択します。[単一 Sign-On オプションを有効に**して**、[次へ**] を選択します。
8. **保存** を選択します。

Note

Cisco Webex の構成方法について詳しくは、[こちら](https://help.webex.com/WBX000022701/How-Do-I-Configure-Microsoft-Azure-Active-Directory-Integration-with-Cisco-Webex-Through-Site-Administration#:%7E:text=In%20the%20Azure%20portal%2C%20select,in%20the%20Add%20Assignment%20dialog)のページを参照してください。

#### Cisco Webex のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーが Cisco Webex に作成されます。このアプリケーションは自動ユーザー プロビジョニングをサポートしているため、ビジネス ルールに基づく自動プロビジョニングと自動プロビジョニング解除が可能です。 可能な限り自動プロビジョニングを使用することをお勧めします。 [Cisco Webex](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-webex-provisioning-tutorial) の自動プロビジョニングを有効にする方法をご覧ください。

ユーザーを手動で作成する必要がある場合は、次の手順を実行します。

1. 管理者の資格情報を使用して Cisco Webex にサインインします。
2. [ **ユーザー** ] を選択し、[ **ユーザーの管理] を選択します**。

    [Image: スクリーンショットは、[Manage Users ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理) を設定できる [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) ページを示しています。]
3. **[Manage Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理)** ウィンドウで **[Manually Add or Modify Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーを手動で追加または変更)** を選択します。
4. **[Names and Email address (名前と電子メール アドレス)]** を選択します。 次のようにテキスト ボックスに入力します。

    [Image: スクリーンショットは、ユーザーを手動で追加または変更できる [Manage User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理) ダイアログ ボックスを示しています。]

    a。 **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名を入力します (この例では **B**)。

    b。 **[姓]** ボックスに、ユーザーの姓を入力します (この例では **Simon**)。

    c. **[Email address](メール アドレス)** ボックスに、ユーザーのメール アドレス (b.simon@contoso.com など) を入力します。
5. プラス記号を選択して B.Simon を追加します。 次に、 **[次へ]** を選択します。
6. [ **ユーザーのサービスの追加** ] ウィンドウで、[ **ユーザーの追加** ] を選択し、[ **完了] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cisco Webex サインオン URL にリダイレクトされます。
- Cisco Webex のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Cisco Webex] タイルを選択すると、このオプションは Cisco Webex のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-umbrella-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cisco Umbrella Admin SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-umbrella-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Cisco Umbrella Admin SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Cisco Umbrella Admin SSO と Microsoft Entra ID を統合する方法について説明します。 Cisco Umbrella Admin SSO を Microsoft Entra ID と統合すると、次のことができます。

- Cisco Umbrella Admin SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cisco Umbrella Admin SSO に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

Cisco Umbrella Admin SSO は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- シングル サインオン (SSO) が有効な Cisco Umbrella Admin SSO サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Cisco Umbrella Admin SSO では、**SP および IDP**開始の SSO がサポートされています。

### ギャラリーからの Cisco Umbrella Admin SSO の追加

Microsoft Entra ID への Cisco Umbrella Admin SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cisco Umbrella Admin SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Cisco Umbrella Admin SSO**」と入力します。
4. 結果パネルから **Cisco Umbrella Admin SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cisco Umbrella Admin SSO に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cisco Umbrella Admin SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Cisco Umbrella Admin SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Cisco Umbrella Admin SSO に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cisco Umbrella Admin SSO SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cisco Umbrella Admin SSO テストユーザーの作成** - Cisco Umbrella Admin SSO 内で Microsoft Entra にリンクされた B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Cisco Umbrella Admin SSO]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。

    a. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    b。 [ **追加の URL の設定] を選択します**。

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://login.umbrella.com/sso`
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Cisco Umbrella Admin SSO のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成をコピーするための適切なU R Lを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cisco Umbrella Admin SSO の SSO の構成

1. 別のブラウザーのウィンドウで、管理者として Cisco Umbrella Admin SSO 企業サイトにサインオンします。
2. メニューの左側から [ **管理者** ] を選択し、[ **認証** ] に移動して、[ **SAML**] を選択します。

    [Image: [管理] メニュー ウィンドウを示すスクリーンショット。]
3. [ **その他]** を選択し、[ **次へ**] を選択します。

    [Image: [その他] メニュー ウィンドウを示すスクリーンショット。]
4. **Cisco Umbrella Admin の SSO メタデータ**のページで、[**次へ**] を選択します。

    [Image: メタデータ ファイル ページを示すスクリーンショット。]
5. [ **メタデータのアップロード** ] タブで、SAML が事前に構成されている場合は、[ **ここを選択して変更する] オプションを選択** し、次の手順に従います。

    [Image: スクリーンショットは『次のフォルダー』ウィンドウを表示しています。]
6. **オプション A: XML ファイルのアップロード**で、ダウンロードした**フェデレーション メタデータ XML** ファイルをアップロードし、メタデータをアップロードした後、以下の値が自動的に設定され、[**次へ**] を選択します。

    [Image: フォルダーからファイルを選択する画面を示すスクリーンショット。]
7. [ **SAML 構成の検証]** セクションで、[ **SAML 構成のテスト**] を選択します。

    [Image: テストSAML構成が表示されたスクリーンショット。]
8. **[保存] を選択します**。

#### Cisco Umbrella Admin SSO テスト ユーザーの作成

Microsoft Entra ユーザーが Cisco Umbrella Admin SSO にログインできるようにするには、ユーザーを Cisco Umbrella Admin SSO にプロビジョニングする必要があります。 Cisco Umbrella Admin SSO の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. 別のブラウザーのウィンドウで、管理者として Cisco Umbrella Admin SSO 企業サイトにサインオンします。
2. メニューの左側にある [ **管理者** ] を選択し、[ **アカウント]** に移動します。

    [Image: Cisco Umbrella Admin のアカウントを示すスクリーンショット。]
3. [ **アカウント** ] ページで、ページの右上にある [ **追加** ] を選択し、次の手順を実行します。

    [Image: [アカウント] のユーザーを示すスクリーンショット。]

    a. **名（ファーストネーム）**フィールドに、**Britta**のような名前を入力します。

    b。 [ **姓]** フィールドに、 **simon** のような姓を入力します。

    c. [ **委任された管理者ロールの選択**] で、自分のロールを選択します。

    d. [ **電子メール アドレス]** フィールドに、ユーザーの電子メールアドレス ( **brittasimon@contoso.com**など) を入力します。

    e. [ **パスワード** ] フィールドにパスワードを入力します。

    f. [ **パスワードの確認** ] フィールドに、パスワードを再入力します。

    g. **作成**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cisco Umbrella Admin SSO サインオン URL にリダイレクトされます。
- Cisco Umbrella Admin SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Cisco Umbrella Admin SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Cisco Umbrella Admin SSO] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Cisco Umbrella Admin SSO に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-unified-communications-manager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cisco Unified Communications Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-unified-communications-manager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cisco Unified Communications Manager の間でシングル サインオンを構成する方法について説明します。

この記事では、Cisco Unified Communications Manager と Microsoft Entra ID を統合する方法について説明します。 Cisco Unified Communications Manager (Unified CM) は、信頼性が高く、セキュリティで保護され、スケーラブルで管理しやすい呼び出し制御とセッション管理を提供します。 Cisco Unified Communications Manager を Microsoft Entra ID を統合すると、次のことが可能になります。

- Cisco Unified Communications にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cisco Unified Communications Manager に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Cisco Unified Communications Manager に対して Microsoft Entra シングル サインオンを構成してテストします。 Cisco Unified Communications Manager は、**SP** Initiated シングル サインオンをサポートしています。

### [前提条件]

Microsoft Entra ID を Cisco Unified Communications Manager と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Cisco Unified Communications Manager でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Cisco Unified Communications Manager アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Cisco Unified Communications Manager を追加する

Microsoft Entra アプリケーション ギャラリーから Cisco Unified Communications Manager を追加して、Cisco Unified Communications Manager でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Cisco Unified Communications Manager]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** がある場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする方法を示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択と参照の方法を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    注

    **サービス プロバイダー メタデータ ファイル**は[、Cisco Unified Communications Manager サポート チーム](mailto:email-in@cisco.com)から取得します。 **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. Cisco Unified Communications Manager アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Cisco Unified Communications Manager アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | uid (ユーザー識別子) | user.onpremisessamaccountname |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Set up Cisco Unified Communications Manager] (Cisco Unified Communications Manager のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

### Cisco Unified Communications Manager SSO を構成する

**Cisco Unified Communications Manager** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Cisco Unified Communications Manager サポート チーム](mailto:email-in@cisco.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cisco Unified Communications Manager テスト ユーザーを作成する

このセクションでは、Cisco Unified Communications Manager で Britta Simon というユーザーを作成します。 [Cisco Unified Communications Manager サポート チーム](mailto:email-in@cisco.com)と協力して、Cisco Unified Communications Manager プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Cisco Unified Communications Manager のサインオン URL にリダイレクトされます。
- Cisco Unified Communications Manager のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cisco Unified Communications Manager] タイルを選択すると、このオプションは Cisco Unified Communications Manager のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-unity-connection-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Cisco Unity 接続を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-unity-connection-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cisco Unity Connection 間にシングル サインオンを構成する方法について説明します。

この記事では、Cisco Unity Connection を Microsoft Entra ID と統合する方法について説明します。 Cisco Unity Connection は、音声コマンド、STT 文字起こしなどのサポートを含む柔軟なメッセージ アクセス オプションをユーザーに提供する、堅牢なユニファイド メッセージングおよびボイスメール ソリューションです。 Cisco Unity Connection と Microsoft Entra ID を統合すると、次が可能になります。

- Cisco Unity Connection へのアクセス許可のある Microsoft Entra ID を制御する。
- ユーザーが Microsoft Entra アカウントを使用して Cisco Unity Connection に自動的にサインインできるようになる。
- アカウントを一元的に管理する。

テスト環境で Cisco Unity Connection 向けの Microsoft Entra シングル サインオンを構成してテストします。 Cisco Unity Connection では、 **SP** によって開始されたシングル サインオンがサポートされます。

### 前提条件

Microsoft Entra ID を Cisco Unity Connection と統合するには、次が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Cisco Unity Connection でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Cisco Unity Connection アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Cisco Unity Connection を追加する

Microsoft Entra アプリケーション ギャラリーから Cisco Unity Connection を追加して、Cisco Unity Connection を使用してシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、[クイック スタート: ギャラリーからのアプリケーションの追加](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)に関する記事を参照してください。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[ユーザー アカウントの作成と割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 ウィザードは、シングル サインオン構成ウィンドウへのリンクも提供します。 [Microsoft 365 ウィザードの詳細をご覧ください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO を構成する

Microsoft Entra のシングル サインオンを有効にするには、以下の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com) 以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Cisco Unity Connection**&gt;**シングル サインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルのアップロード方法を示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択と参照の方法を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**と**応答 URL** の値が、[基本的な SAML 構成] セクションに自動的に設定されます。

    d. **サインオン URL** ボックスに、次のパターンを使用して URL を入力します。`https://<FQDN_CUC_node>`

    Note

    **サービス プロバイダーのメタデータ ファイル**は[、Cisco Unity 接続サポート チーム](mailto:unity-tme@cisco.com)から取得します。 **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. Cisco Unity Connection アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性構成の画像を示すスクリーンショット。]
7. その他に、Cisco Unity Connection アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されます。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | uid (ユーザー識別子) | user.onpremisessamaccountname |
8. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、**[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **Cisco Unity Connection のセットアップ** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

### Cisco Unity Connection SSO を構成する

**Cisco Unity Connection** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Cisco Unity Connection サポート チーム](mailto:unity-tme@cisco.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Cisco Unity Connection テスト ユーザーを作成する

このセクションでは、Cisco Unity Connection で Britta Simon というユーザーを作成します。 [Cisco Unity Connection サポート チーム](mailto:unity-tme@cisco.com) と協力して、Cisco Unity Connection プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションを選択すると、ログイン フローを開始できる Cisco Unity 接続サインオン URL にリダイレクトされます。
- Cisco Unity Connection のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Cisco Unity 接続] タイルを選択すると、このオプションは Cisco Unity 接続のサインオン URL にリダイレクトされます。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-user-management-for-secure-access-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用にセキュア アクセス用に Cisco User Management を設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-user-management-for-secure-access-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-31
- Summary: セキュリティで保護されたアクセスのために、Microsoft Entra IDから Cisco User Management にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、セキュリティで保護されたアクセスのための Cisco ユーザ管理と、自動ユーザ プロビジョニングを設定するためにMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成された Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、[Cisco User Management for Secure Access](https://www.cisco.com) にユーザーとグループを自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- セキュリティで保護されたアクセスのための Cisco User Management でユーザーを作成する
- アクセスが不要になった場合に、セキュリティで保護されたアクセスのための Cisco ユーザ管理のユーザを削除する
- セキュリティで保護されたアクセスのために、Microsoft Entra IDと Cisco User Management の間でユーザー属性の同期を維持する
- セキュリティで保護されたアクセスのために Cisco User Management でグループとグループ メンバーシップをプロビジョニングする
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [Cisco Umbrella サブスクリプション](https://signup.umbrella.com)。
- 完全な管理者権限を持つ Cisco Umbrella のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. セキュリティで保護されたアクセスのための Microsoft Entra ID と Cisco User Management の間で[マップするデータ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を決定します。

### 手順 2: Microsoft Entra Connect を使用して ObjectGUID 属性をインポートする (省略可能)

エンドポイントが AnyConnect または Cisco Secure Client バージョン 4.10 MR5 以前を実行している場合は、ユーザー ID 属性の ObjectGUID 属性を同期する必要があります。 Microsoft Entra IDからグループをインポートした後、グループの Umbrella ポリシーを再構成する必要があります。

注

ObjectGUID 属性をインポートする前に、オンプレミスの Umbrella AD コネクタをオフにする必要があります。

Microsoft Entra Connect を使用する場合、ユーザーの ObjectGUID 属性は、既定ではオンプレミス AD からMicrosoft Entra IDに同期されません。 この属性を同期するには、オプションの **ディレクトリ拡張機能属性の同期** を有効にして、ユーザーの objectGUID 属性を選択します。

[Image: Microsoft Entra 接続ウィザードオプション機能ページ]

注

**[使用可能な属性]** での検索は、大文字と小文字が区別されます。

[Image: [ディレクトリ拡張機能] 選択ページを示すスクリーンショット]

注

すべてのエンドポイントが Cisco Secure Client または AnyConnect バージョン 4.10 MR6 以降を実行している場合、この手順は必要ありません。

### 手順 3: Microsoft Entra IDを使用したプロビジョニングをサポートするように、セキュリティで保護されたアクセスのための Cisco ユーザ管理を設定する

1. [Cisco Umbrella ダッシュボード](https://login.umbrella.com)にログインします。 **デプロイ**&gt;**コア ID**&gt;**ユーザーとグループ**に移動します。
2. Microsoft Entra カードを展開し、**API キー ページ** を選択>。

    [Image: API]
3. [API キー] ページでMicrosoft Entra カードを展開し、**Generate Token** を選択します。

    [Image: 生成する]
4. 生成されたトークンは 1 回だけ表示されます。 URL とトークンをコピーして保存します。 これらの値は、Cisco User Management for Secure Access アプリケーションの [プロビジョニング] タブの **[テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドにそれぞれ入力されます。

### 手順 4: Microsoft Entra アプリケーション ギャラリーからセキュリティで保護されたアクセスのための Cisco ユーザー管理を追加する

Microsoft Entra アプリケーション ギャラリーから Cisco User Management for Secure Access を追加して、セキュリティで保護されたアクセスのための Cisco User Management へのプロビジョニングの管理を開始します。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 5: プロビジョニングの対象ユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザー/グループの属性に基づいて、プロビジョニングされたユーザーのスコープを設定できます。 割り当てに基づいてアプリにプロビジョニングされたユーザーのスコープを設定する場合は、次の [手順](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) を使用して、ユーザーとグループをアプリケーションに割り当てることができます。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされたユーザーのスコープを設定する場合は、 [ここで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)説明するようにスコープ フィルターを使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 6: セキュリティで保護されたアクセスのために Cisco ユーザー管理への自動ユーザー プロビジョニングを設定する

このセクションでは、Microsoft Entra IDのユーザーやグループの割り当てに基づいて、セキュリティで保護されたアクセスのために Cisco User Management のユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDでセキュリティで保護されたアクセスのための Cisco ユーザ管理の自動ユーザ プロビジョニングを設定するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で、 **セキュリティで保護されたアクセスのための Cisco ユーザ管理を選択して下さい**。

    [Image: アプリケーションの一覧の [Cisco User Management for Secure Access](セキュリティで保護されたアクセスの Cisco ユーザー管理) リンクを示すスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: プロビジョニングタブの自動化]
6. [ **テナント URL** ] フィールドに、セキュリティで保護されたアクセス テナント URL とシークレット トークンの Cisco ユーザー管理を入力します。 **Test Connection** を選択して、セキュリティで保護されたアクセスのためにMicrosoft Entra IDが Cisco User Management に接続できることを確認します。 接続に失敗した場合は、Cisco User Management for Secure Access アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cisco User Management for Secure Access に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Cisco User Management のユーザ アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Cisco User Management for Secure Access API が、その属性に基づくユーザのフィルタリングをサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 |  |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:ciscoumbrella:2.0:User:nativeObjectId | 糸 |  |

    注

    Microsoft Entra Connect 経由でユーザーの objectGUID 属性をインポートした場合 (手順 2 を参照)、objectGUID から urn:ietf:params:scim:schemas:extension:ciscoumbrella:2.0:User:nativeObjectId へのマッピングを追加します。
12. Microsoft Entra IDから Cisco User Management for Secure Access に同期されるグループ属性については、「**Attribute-Mapping**」セクションを参照してください。 **[照合**]プロパティとして選択されている属性は、アップデート操作用のセキュア アクセスの Cisco ユーザ管理のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
    | members | リファレンス |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 7: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

- [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、正常にプロビジョニングされたユーザーまたは失敗したユーザーを特定する
- [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
- プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、を参照してください。

### コネクタの制限事項

- セキュリティで保護されたアクセスのための Cisco ユーザ管理は最大 200 グループのプロビジョニングをサポートします。 この数を超えるスコープ内のグループは、Cisco Umbrella にプロビジョニングできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-webex-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Cisco Webex を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-webex-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: ユーザー アカウントを Cisco Webex に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Cisco Webex で実行する手順と、ユーザーを Cisco Webex に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

このコネクタは、現在プレビューの段階です。 プレビューの詳細については、「[Universal License Terms For Online Services](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)」を参照してください。

Cisco Webex は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### サポートされている機能

- Cisco Webex でユーザーを作成します。
- アクセスが不要になった場合は、Cisco Webex のユーザーを削除します。
- Microsoft Entra IDと Cisco Webex の間でユーザー属性の同期を維持します。
- Cisco Webex でグループとグループ メンバーシップをプロビジョニングします。
- Cisco Webex への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-webex-tutorial) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)..

- [Cisco Webex テナント](https://www.webex.com/pricing/index.html)。
- Admin アクセス許可がある Cisco Webex のユーザー アカウント。

### ギャラリーからの Cisco Webex の追加

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Cisco Webex を構成する前に、Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に Cisco Webex を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Cisco Webex を追加するには、以下の手順を実行します。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、「**Cisco Webex**」と入力し、結果パネルから **Cisco Webex** を選択し、[**追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Cisco Webex を示すスクリーンショット。]

### Cisco Webex へのユーザーの割り当て

Microsoft Entra IDでは、"割り当て" という概念を使用して、選択したアプリにaccessを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに "割り当てられている" ユーザーやグループのみが同期されます。

自動ユーザープロビジョニングを構成して有効にする前に、どのMicrosoft Entra IDのユーザーがCisco Webexにアクセスする必要があるかを決定する必要があります。 決定し終えたら、次の手順でこれらのユーザーを Cisco Webex に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Cisco Webex に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Cisco Webex に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後で追加のユーザーを割り当てられます。
- Cisco Webex にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **Default Access** ロールを持つユーザーは、プロビジョニングから除外されます。

### Cisco Webex への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて Cisco Webex のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順をguidesします。

#### Microsoft Entra IDで Cisco Webex の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Cisco Webex** を参照してください。

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で **[Cisco Webex]** を選択します。

    [Image: スクリーンショットは、アプリケーションの一覧の Cisco Webex リンクを示しています。]
4. **[プロビジョニング]** タブを選択します。

    [Image: メニューのスクリーンショット。[管理] の下の [プロビジョニング] が強調表示されています。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Cisco Webex テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Cisco Webex に接続できることを確認します。 接続に失敗した場合は、Cisco Webex アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. **[テナントの URL]** フィールドに `https://api.ciscospark.com/v1/scim/[OrgId]` という形式で値を入力します。 `[OrgId]` を取得するには、[Cisco Webex Control Hub](https://admin.webex.com/login) にサインインします。 左下にある組織名を選択し、 **組織 ID** から値をコピーします。

    - **[シークレット トークン]** の値を取得するには、この [\[URL\]](https://idbroker.webex.com/idb/saml2/jsp/doSSO.jsp?type=login&amp;goto=https%3A%2F%2Fidbroker.webex.com%2Fidb%2Foauth2%2Fv1%2Fauthorize%3Fresponse_type%3Dtoken%26client_id%3DC4ca14fe00b0e51efb414ebd45aa88c1858c3bfb949b2405dba10b0ca4bc37402%26redirect_uri%3Dhttp%253A%2f%2flocalhost%253A3000%2fauth%2fcode%26scope%3Dspark%253Apeople_read%2520spark%253Apeople_write%2520Identity%253ASCIM%26state%3Dthis-should-be-a-random-string-for-security-purpose) に移動します。 表示される webex サインイン ページから、組織の完全な Cisco Webex 管理者アカウントでサインインします。 サイトに到達できないというエラー ページが表示されますが、これは正常です。

        [Image: エラー メッセージが表示されている Web ページのスクリーンショット。このメッセージには、サイトにアクセスできないことが示され、いくつかのトラブルシューティングのヒントが含まれています。]
    - 次に示すように、生成されたベアラー トークンの値を URL からコピーします。 このトークンは 365 日間有効です。

        [Image: 長い U R L を示すスクリーンショット。アドレスの一部は解読できませんが、強調表示され、[ベアラー トークン] というラベルが付いています。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cisco Webex に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Cisco Webex のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Cisco Webex API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Cisco Webex で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | externalId | 糸 |  |  |
13. **[グループ]** を選びます。
14. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cisco Webex に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Cisco Webex のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Cisco Webex で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  |  |
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### コネクタの制限事項

- 現在、Cisco Webex は Cisco において初期フィールド テスト (EFT) が行われています。 詳しくは、[Cisco サポート チーム](https://www.webex.co.in/support/support-overview.html)にお問い合わせください。
- Cisco Webex の構成の詳細については、Cisco ドキュメント [here](https://help.webex.com/en-us/aumpbz/Synchronize-Azure-Active-Directory-Users-into-cisco-webex-Control-Hub)を参照してください。

### 変更ログ

- 2023 年 2 月 7 日 - **グループ プロビジョニング**のサポートを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cisco-webex-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Cisco Webex Meetings を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-webex-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Cisco Webex Meetings の間でシングル サインオンを構成する方法について説明します。

この記事では、Cisco Webex Meetings と Microsoft Entra ID を統合する方法について説明します。 Cisco Webex Meetings と Microsoft Entra ID を統合すると、次が可能になります。

- Cisco Webex Meetings へのアクセス許可のある Microsoft Entra ID を制御する。
- ユーザーが Microsoft Entra アカウントを使用して Cisco Webex Meetings に自動的にサインインするように設定する。
- 1 つの中央の場所でアカウントを管理します。

Cisco Webex Meetings は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Cisco Webex Meetings でのシングル サインオン (SSO) が有効なサブスクリプション。
- Cisco Webex Meetings のサービス プロバイダー メタデータ ファイル。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cisco Webex Meetings では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Cisco Webex Meetings では、 [**自動** ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cisco-webex-provisioning-tutorial) がサポートされます (推奨)。
- Cisco Webex Meetings では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Cisco Webex Meetings の追加

Microsoft Entra ID への Cisco Webex Meetings の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cisco Webex Meetings を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動してください。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Cisco Webex Meetings」と**入力します。
4. 結果パネルから **Cisco Webex Meetings** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cisco Webex Meetings に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cisco Webex Meetings に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Cisco Webex Meetings の関連ユーザーとの間にリンク関係を確立する必要があります。

Cisco Webex Meetings を使用して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cisco Webex Meetings の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cisco Webex Meetings のテストユーザーを作成** - Cisco Webex Meetings で B.Simon に対応するユーザーを作成し、Microsoft Entra ユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Cisco Webex Meetings]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [SAML を使用**した単一 Sign-On のセットアップ**] ページで、**次のようにサービス プロバイダーメタデータ**ファイルをアップロードすることで、**IDP** 開始モードでアプリケーションを構成できます。

    1. [ **メタデータ ファイルのアップロード]** を選択します。
    2. **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。
    3. サービス プロバイダー メタデータ ファイルのアップロードが正常に完了すると、[**基本的な SAML 構成]** セクションに**識別子**と**応答 URL** の値が自動的に設定されます。

        注

        サービス プロバイダー メタデータ ファイルは、「 **Cisco Webex Meetings SSO の構成** 」セクションから取得します。これについては、この記事の後半で説明します。
5. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    1. [ **基本的な SAML 構成]** セクションで、鉛筆アイコンを選択します。

        [Image: 基本的な SAML 構成の編集]
    2. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<customername>.my.webex.com`
6. Cisco Webex Meetings アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: 画像]
7. その他に、Cisco Webex Meetings アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [ユーザー属性] ダイアログの [ユーザー要求] セクションで、以下の手順を実行して、以下の表のように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastname | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | uid (ユーザー識別子) | ユーザーのメールアドレス |

    1. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。
    2. [ **名前** ] ボックスに、その行に表示される属性名を入力します。
    3. **名前空間**は空白のままにします。
    4. [ソース] を **[属性**] として選択します。
    5. [ **ソース属性** ] ボックスの一覧から、その行に表示される属性値をドロップダウン リストから選択します。
    6. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Cisco Webex Meetings のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cisco Webex Meetings の SSO の構成

1. 管理者の資格情報を使用して Cisco Webex Meetings にサインインします。
2. **[共通サイト設定]** に移動し、[**SSO 構成]** に移動します。

    [Image: [Common Site Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/共通サイト設定) と [S S O Configuration](S S O 構成) が選択されている Cisco Webex Administration のスクリーンショット。]
3. **[Webex Administration]\(Webex 管理**\) ページで、次の手順を実行します。

    [Image: この手順で説明されている情報を含む [Webex Administration](Webex 管理) ページを示すスクリーンショット。]

    1. **[フェデレーション プロトコル**] として **[SAML 2.0]** を選択します。
    2. [ **SAML メタデータのインポート]** リンクを選択して、前にダウンロードしたメタデータ ファイルをアップロードします。
    3. **IDP 開始**として **SSO プロファイル**を選択し、[**エクスポート**] ボタンを選択してサービス プロバイダー メタデータ ファイルをダウンロードし**、[基本的な SAML 構成]** セクションでアップロードします。
    4. [ **アカウントの自動作成] を**選択します。

        注

        **Just-In-Time** ユーザー プロビジョニングを有効にするには、**自動アカウント作成**を確認する必要があります。 さらに、SAML トークン属性を、SAML 応答で渡す必要があります。
    5. **[保存] を選択します**。

        注

        この構成は、メール形式の Webex UserID を使用するユーザー専用です。

        Cisco Webex 会議を構成する方法の詳細については、 [Webex のドキュメント](https://help.webex.com/WBX000022701/How-Do-I-Configure-Microsoft-Azure-Active-Directory-Integration-with-Cisco-Webex-Through-Site-Administration#:%7E:text=In%20the%20Azure%20portal%2C%20select,in%20the%20Add%20Assignment%20dialog) ページを参照してください。

#### Cisco Webex Meetings のテスト ユーザーの作成

このセクションの目的は、Cisco Webex Meetings で B.Simon というユーザーを作成することです。 Cisco Webex Meetings では、 **Just-In-Time** プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Cisco Webex Meetings に存在しない場合は、Cisco Webex Meetings にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cisco Webex Meetings のサインオン URL にリダイレクトされます。
- Cisco Webex Meetings のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Cisco Webex Meetings に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Cisco Webex Meetings] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Cisco Webex Meetings に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ciscocloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cisco Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ciscocloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cisco Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、Cisco Cloud と Microsoft Entra ID を統合する方法について説明します。 Cisco Cloud と Microsoft Entra ID を統合すると、次のことができます。

- Cisco Cloud にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cisco Cloud に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cisco Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Cisco Cloud では、**SPとIDP**によるSSOがサポートされます。

### ギャラリーから Cisco Cloud を追加する

Microsoft Entra ID への Cisco Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cisco Cloud を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Cisco Cloud**」と入力します。
4. 結果パネルから Cisco Cloud  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cisco Cloud の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Cisco Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Cisco Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Cisco Cloud で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cisco Cloud の SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **Cisco Cloud のテストユーザーを作成** - Cisco Cloud で B.Simon に相当するユーザーを作成し、それを Microsoft Entra の代表ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Cisco Cloud]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `<subdomain>.cisco.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.cisco.com/sp/ACS.saml2`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.cloudapps.cisco.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Cisco Cloud クライアント サポート チーム](mailto:cpr-ops@cisco.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Cisco Cloud アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: [編集] アイコンが選択されているユーザー属性を示すスクリーンショット。]
8. 上記に加えて、Cisco Cloud アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | 国 | ユーザー.国 |
    | company | user.companyname |
    |  |  |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [新しい要求の追加] オプションを含むユーザー要求を示すスクリーンショット。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. [ソース] を **[属性**] として選択します。

    e. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。

    f. [ **OK] を選択する**

    g. **[保存] を選択します**。
9. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cisco Cloud SSO の構成

**Cisco Cloud** 側でシングル サインオンを構成するには、**アプリ フェデレーション メタデータ URL を**[Cisco Cloud サポート チーム](mailto:cpr-ops@cisco.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cisco Cloud のテスト ユーザーの作成

このセクションでは、Cisco Cloud で Britta Simon というユーザーを作成します。 cisco Cloud サポート チーム  と連携して、Cisco Cloud プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションを選択すると、ログイン フローを開始できる Cisco Cloud サインオン URL にリダイレクトされます。
- Cisco Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Cisco Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Cisco Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Cisco Cloud に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ciscocloudlock-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cloud Security Fabric を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ciscocloudlock-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と The Cloud Security Fabric の間でシングル サインオンを構成する方法について説明します。

この記事では、The Cloud Security Fabric と Microsoft Entra ID を統合する方法について説明します。 The Cloud Security Fabric を Microsoft Entra ID と統合すると、次のことができます。

- The Cloud Security Fabric へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して自動的に The Cloud Security Fabric にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- The Cloud Security Fabric でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cloud Security Fabric では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから The Cloud Security Fabric を追加する

The Cloud Security Fabric の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに The Cloud Security Fabric を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「The Cloud Security Fabric**」と入力します。
4. 結果パネルから **[Cloud Security Fabric** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### The Cloud Security Fabric の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、The Cloud Security Fabric に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと The Cloud Security Fabric の関連ユーザーとの間にリンク関係を確立する必要があります。

The Cloud Security Fabric に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cloud Security Fabric の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cloud Security Fabric のテストユーザーを作成する** - Cloud Security Fabric で B.Simon の対応ユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Cloud Security Fabric** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://platform.cloudlock.com` |
    | `https://app.cloudlock.com` |

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://platform.cloudlock.com/gate/saml/sso/<subdomain>` |
    | `https://app.cloudlock.com/gate/saml/sso/<subdomain>` |

    Note

    識別子の値は実際の値ではありません。 この値を実際の識別子で更新してください。 この値を取得するには、 [Cloud Security Fabric クライアント サポート チーム](mailto:support@cloudlock.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. 要件に従って **署名** オプションを変更するには、[ **編集** ] ボタンを選択して **SAML 署名証明書** ダイアログを開きます。

    a. **[署名オプション]** で **[SAML 応答とアサーションへの署名]** オプションを選択します。

    b。 **署名アルゴリズム**の **SHA-256** オプションを選択します。

    c. **[保存] を選択します**。
8. [ **Cloud Security Fabric のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### The Cloud Security Fabric SSO の構成

**The Cloud Security Fabric** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Cloud Security Fabric サポート チーム](mailto:support@cloudlock.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### The Cloud Security Fabric テスト ユーザーの作成

このセクションでは、The Cloud Security Fabric で B.Simon というユーザーを作成します。 [The Cloud Security Fabric サポート チーム](mailto:support@cloudlock.com)と協力して、The Cloud Security Fabric プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Cloud Security Fabric のサインオン URL にリダイレクトされます。
- The Cloud Security Fabric のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Cloud Security Fabric] タイルを選択すると、このオプションは Cloud Security Fabric のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/citi-program-tutorial"} -->
## Microsoft Entra ID で CITI Program for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citi-program-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CITI Program 間のシングル サインオンを構成する方法について説明します。

この記事では、CITI Program と Microsoft Entra ID を統合する方法について説明します。 CITIプログラムは、私たちが提供するコミュニティの教育とトレーニングのニーズを特定し、それらのニーズを満たすために高品質でピアレビューされた、Webベースの教材を提供します。 CITI Program を Microsoft Entra ID と統合すると、次のことが可能になります。

- CITI Program へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して CITI Program に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

CITI Program に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストする。 CITI Program では、 **SP によって開始される** シングル サインオンと **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を CITI Program と統合するには、以下が必要です。

- CITI Program でのシングル サインオン (SSO) が有効なサブスクリプション。 [SSO は CITI Program での有料サービス](https://support.citiprogram.org/s/article/single-sign-on-sso-and-shibboleth-technical-specs#General)であることに注意してください。
- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンを構成する前に、Microsoft Entra ギャラリーから CITI Program アプリケーションを追加し、テスト ユーザー アカウントを割り当てる必要があります。 その後、シングル サインオンの構成をテストできます。

#### Microsoft Entra ギャラリーから CITI Program を追加する

Microsoft Entra アプリケーション ギャラリーから CITI Program を追加し、CITI Program とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)参照してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) に関する記事のガイドラインに従って、テスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[CITI Program]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** ボックスで、URL を使用します。 `https://www.citiprogram.org/shibboleth`

    b。 **応答 URL (Assertion Consumer Service URL)** テキストボックス内で、URL を使用します。`https://www.citiprogram.org/Shibboleth.sso/SAML2/POST`

    c. [ **サインオン URL** ] ボックスで、URL を使用します。 `https://www.citiprogram.org/portal`

    注

    構成の最後に、CITI Program Support によって提供される SSO リンクを使用して **サインオン URL を** 更新できます。
6. CITI Program アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. CITI Program アプリケーションでは、次に示すように、urn:oid の名前付き属性が SAML 応答で返されることを想定しています。 これらはすべて必須です。 既定の属性の名前を変更または削除して構成できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:1.3.6.1.4.1.5923.1.1.1.6 | user.userprincipalname |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
    | urn:oid:2.5.4.42 | User.givenname |
    | urn:oid:2.5.4.4 | ユーザーの名字 |
8. SAML 応答で追加情報を渡す場合、CITI Program で次の省略可能な属性を受け入れることもできます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:2.16.840.1.113730.3.1.241 | user.displayname |
    | urn:oid:2.16.840.1.113730.3.1.3 | user.employeeid |

    注

    ソース属性は一般的に推奨されますが、必ずしもルールであるとは限りません。 たとえば、user.mail が一意でスコープ指定されている場合は、urn:oid:1.3.6.1.4.1.5923.1.1.1.6 として渡すこともできます。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **アプリのフェデレーション メタデータ URL を** 見つけてコピーするか、 **フェデレーション メタデータ XML** を選択し、[ **ダウンロード** ] を選択して証明書をダウンロードします。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **CITI Program のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### CITI Program SSO の構成

**CITI Program** 側でシングル サインオンを構成するには、コピーした**アプリのフェデレーション メタデータ URL** またはダウンロードした**フェデレーション メタデータ XML** を [CITI プログラム サポート](mailto:shibboleth@citiprogram.org)に送信する必要があります。 これは、SAML SSO 接続を両方の側で正しく設定するために必要です。 また、統合に向けて、追加のスコープまたはドメインを指定できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる CITI Program のサインオン URL にリダイレクトされます。
- CITI Program のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [CITI Program] タイルを選択すると、CITI Program のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。

CITI Program は、Just-In-Time ユーザー プロビジョニングをサポートしています。 初回 SSO ユーザーは、次のいずれかを求められます。

- 既存の CITI Program アカウントがある場合は、そのアカウントをリンクしますSSOHaveAccount既存の CITI Program アカウントをリンクする
- または新しい CITI Program アカウントを作成する (これは自動的にプロビジョニングされます) [Image: SSONotHaveAccount]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/citrix-cloud-saml-sso-tutorial"} -->
## Microsoft Entra ID で Citrix Cloud SAML SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrix-cloud-saml-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Citrix Cloud SAML SSO 間のシングル サインオンを構成する方法について説明します。

この記事では、Citrix Cloud SAML SSO と Microsoft Entra ID を統合する方法について説明します。 Citrix Cloud SAML SSO を Microsoft Entra ID と統合すると、次のことが可能になります。

- Citrix Cloud SAML SSO にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Citrix Cloud SAML SSO に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Citrix Cloud のサブスクリプション。 サブスクリプションをお持ちでない場合は、サインアップしてください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Citrix Cloud SAML SSO では、 **SP** によって開始される SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Citrix Cloud SAML SSO の追加

Microsoft Entra ID への Citrix Cloud SAML SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Citrix Cloud SAML SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「Citrix Cloud SAML SSO**」と入力します。
4. 結果パネルから **Citrix Cloud SAML SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Citrix Cloud SAML SSO に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Citrix Cloud SAML SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Citrix Cloud SAML SSO の関連ユーザーとの間にリンク関係を確立する必要があります。 このユーザーは、Microsoft Entra Connect と Microsoft Entra サブスクリプションに同期されている Active Directory にも存在する必要があります。

Citrix Cloud SAML SSO で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Citrix Cloud SAML SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Citrix Cloud SAML SSO]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cloud.com`

    Note

    これは実際の値ではありません。 Citrix ワークスペースの URL で値を更新します。 値を取得するには、Citrix Cloud アカウントにアクセスしてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Citrix Cloud SAML SSO アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
7. その他に、Citrix Cloud SAML SSO アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。 SAML 応答で渡される値は、ユーザーの Active Directory 属性にマップされる必要があります。

    | 名前 | ソース属性 |
    | --- | --- |
    | cip\_sid | user.onpremisesecurityidentifier (ユーザー.オンプレミスセキュリティ識別子) |
    | cip\_upn | user.userprincipalname |
    | cip\_oid | ObjectGUID (拡張属性) |
    | cip\_email | User.mail |
    | displayName | user.displayname |

    Note

    ObjectGUID は、要件に応じて手動で構成する必要があります。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Citrix Cloud SAML SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Citrix Cloud SAML SSO を構成する

1. Web ブラウザーの別のウィンドウで、Citrix Cloud SAML SSO 企業サイトに管理者としてサインインします。
2. Citrix Cloud メニューに移動し、[ **ID とアクセス管理] を**選択します。

    [Image: [アカウント] ページを示すスクリーンショット。]
3. [ **認証**] で **SAML 2.0** を探し、省略記号メニューから **[接続** ] を選択します。
4. [ **SAML の構成** ] ページで、次の手順を実行します。

    [Image: [構成] を示すスクリーンショット。]

    a. [ **エンティティ ID** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    b。 認証**要求に署名**し、を使用する場合`SAML Request signing`] を選択し、それ以外の場合**は [いいえ**] を選択します。

    c. **[SSO サービス URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    d. ドロップダウンから **[バインディング メカニズム** ] を選択し、 **HTTP-POST** または **HTTP-Redirect** バインディングのいずれかを選択できます。

    e. **[SAML Response](SAML 応答)** で、ドロップダウンから **[Sign Either Response or Assertion](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/応答またはアサーションに署名する)** を選択します。

    f. **X.509** 証明書セクションに**証明書 (PEM)** をアップロードします。

    g. **[認証コンテキスト**] で、ドロップダウンから **[未指定**] と [**正確]** を選択します。

    h. [ **テストと完了] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Citrix ワークスペースの URL に直接移動し、そこからログイン フローを開始します。
- AD 同期された Active Directory ユーザーを使用して Citrix ワークスペースにログインし、テストを完了します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/citrix-gotomeeting-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に GoToMeeting を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrix-gotomeeting-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: GoToMeeting と Microsoft Entra ID を統合するために必要な手順について説明します。

この記事では、GoToMeeting と Microsoft Entra ID を統合する方法について説明します。 GoToMeeting を Microsoft Entra ID と統合すると、次のことが可能になります。

- GoToMeeting にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで GoToMeeting に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GoToMeeting でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- GoToMeeting では、 **IDP** Initiated SSO がサポートされます。
- GoToMeeting では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrixgotomeeting-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの GoToMeeting の追加

Microsoft Entra ID への GoToMeeting の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に GoToMeeting を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「GoToMeeting**」と入力します。
4. 結果パネルから **GoToMeeting を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### GoToMeeting 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、GoToMeeting に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと GoToMeeting の関連ユーザーとの間にリンク関係を確立する必要があります。

GoToMeeting で Microsoft Entra の SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **GoToMeeting SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **GoToMeeting テスト ユーザーの作成** - B.Simon の GoToMeeting における対となるユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**GoToMeeting**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://authentication.logmeininc.com/saml/sp`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://authentication.logmeininc.com/saml/acs`

    c. [ **追加 URL の設定] を** 選択し、次の URL を構成します

    d. **サインオン URL** (空白のままにします)

    e. **[RelayState**] テキスト ボックスに、次のいずれかの URL を入力します。

    - GoToMeeting App の場合は、`https://global.gotomeeting.com` を使用します。
    - GoToTraining の場合は、`https://global.gototraining.com` を使用します。
    - GoToWebinar の場合は、`https://global.gotowebinar.com` を使用します。
    - GoToAssist の場合は、`https://app.gotoassist.com` を使用します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **GoToMeeting のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### GoToMeeting の SSO の構成

1. 別のブラウザー ウィンドウで、 [GoToMeeting 組織センターに](https://organization.logmeininc.com/)ログインします。 IdP が更新されたことを確認するメッセージが表示されます。
2. [My Identity Provider has been updated with the new domain](マイ ID プロバイダーが新しいドメインで更新されました) チェックボックスをオンにします。 完了したら **、[完了] を選択します** 。

#### GoToMeeting のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを GoToMeeting に作成します。 GoToMeeting では、Just-In-Time プロビジョニングがサポートされています。これは既定で有効になっています。

このセクションにはアクション項目はありません。 ユーザーがまだ GoToMeeting に存在しない場合は、GoToMeeting にアクセスしようとしたときに新しいユーザーが作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [GoToMeeting サポート チーム](https://support.logmeininc.com/gotomeeting)にお問い合わせください。

注

GoToMeeting では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrixgotomeeting-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した GoToMeeting に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [GoToMeeting] タイルを選択すると、SSO を設定した GoToMeeting に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/citrix-netscaler-tutorial"} -->
## Microsoft Entra ID とのシングルサインオンのために、Kerberosベースの認証を利用するCitrix ADC SAML Connectorを構成する。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrix-netscaler-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Kerberos ベースの認証を使用して Microsoft Entra ID と Citrix ADC SAML Connector for Microsoft Entra ID の間でシングル サインオン (SSO) を構成する方法について説明します。

この記事では、Citrix ADC SAML Connector for Microsoft Entra ID と Microsoft Entra ID を統合する方法について説明します。 Citrix ADC SAML Connector for Microsoft Entra ID を Microsoft Entra ID と統合すると、次のことが可能になります。

- Citrix ADC SAML Connector for Microsoft Entra ID にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Citrix ADC SAML Connector for Microsoft Entra ID に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Citrix ADC SAML Connector for Microsoft Entra でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。 この記事には、次のシナリオが含まれています。

- Citrix ADC SAML Connector for Microsoft Entra ID 用の **SP 起点の** SSO。
- Citrix ADC SAML Connector for Microsoft Entra ID 用の **ジャスト イン タイム** ユーザー プロビジョニング。
- Citrix ADC SAML Connector for Microsoft Entra ID の Kerberos ベースの認証。
- [Microsoft Entra ID 用 Citrix ADC SAML Connector のヘッダーベース認証](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/header-citrix-netscaler-tutorial#publish-the-web-server)。

### ギャラリーからの Citrix ADC SAML Connector for Microsoft Entra ID の追加

Citrix ADC SAML Connector for Microsoft Entra ID を Microsoft Entra ID と統合するには、まず、Citrix ADC SAML Connector for Microsoft Entra ID をギャラリーからマネージド SaaS アプリの一覧に追加します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「Citrix ADC SAML Connector for Microsoft Entra ID**」と入力します。
4. 結果で、 **Citrix ADC SAML Connector for Microsoft Entra ID** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Citrix ADC SAML Connector for Microsoft Entra ID に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Citrix ADC SAML Connector for Microsoft Entra ID に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Citrix ADC SAML Connector for Microsoft Entra ID の関連ユーザーの間に、リンク関係を確立する必要があります。

Citrix ADC SAML Connector for Microsoft Entra ID で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. Microsoft Entra SSO を構成 する - ユーザーがこの機能を使用できるようにします。

    1. Microsoft Entra テスト ユーザーを作成する - B.Simon で Microsoft Entra SSO をテストします。
    2. Microsoft Entra テスト ユーザーを割り当てて、B.Simon が Microsoft Entra SSO を使用できるようにします。
2. Citrix ADC SAML Connector for Microsoft Entra SSO の構成 - アプリケーション側で SSO 設定を構成します。

    1. Citrix ADC SAML Connector for Microsoft Entra テストユーザーの作成 - Citrix ADC SAML Connector for Microsoft Entra ID で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. SSO のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Azure portal を使用して Microsoft Entra SSO を有効にするには、これらの手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID**&gt;**Enterprise apps**&gt;**Citrix ADC SAML Connector for Microsoft Entra ID** アプリケーション統合ペインを参照し、[**管理**] で [**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ウィンドウで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ウィンドウで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **IDP 開始** モードでアプリケーションを構成するには、次の手順に従います。

    1. [ **識別子** ] テキスト ボックスに、次のパターンの URL を入力します。 `https://<YOUR_FQDN>`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンの URL を入力します。 `http(s)://<YOUR_FQDN>.of.vserver/cgi/samlauth`
6. **SP 開始**モードでアプリケーションを構成するには、[**追加の URL の設定**] を選択し、次の手順を実行します。

    - [ **サインオン URL** ] テキスト ボックスに、次のパターンの URL を入力します。 `https://<YOUR_FQDN>/CitrixAuthService/AuthService.asmx`

    Note

    - このセクションで使用される URL は、実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL の値で更新してください。 これらの値を取得するには、 [Citrix ADC SAML Connector for Microsoft Entra クライアント サポート チーム](https://www.citrix.com/contact/technical-support.html) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
    - SSO を設定するには、パブリック Web サイトから URL にアクセスできる必要があります。 Microsoft Entra ID が構成済みの URL でトークンをポストできるようにするには、Citrix ADC SAML Connector for Microsoft Entra ID 側でファイアウォールまたはその他のセキュリティ設定を有効にする必要があります。
7. [ **SAML を使用した単一 Sign-On の設定** ] ウィンドウの [ **SAML 署名証明書** ] セクションの **[アプリのフェデレーション メタデータ URL**] で、URL をコピーしてメモ帳に保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. 「 **Citrix ADC SAML Connector for Microsoft Entra ID のセットアップ** 」セクションで、要件に基づいて関連する URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Citrix ADC SAML Connector for Microsoft Entra SSO を構成する

構成したい認証の種類に対応する手順のリンクを選択してください。

- Kerberos ベースの認証用に Citrix ADC SAML Connector for Microsoft Entra SSO を構成する
- [ヘッダーベースの認証用に Citrix ADC SAML Connector for Microsoft Entra SSO を構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/header-citrix-netscaler-tutorial#publish-the-web-server)

#### Web サーバーを公開する

仮想サーバーを作成するには:

1. [ **Traffic Management**&gt;**Load Balancing**&gt;**Services** を選択します。
2. **追加**を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [サービス] ウィンドウのスクリーンショット。]
3. アプリケーションを実行している Web サーバーに対して、次の値を設定します。

    - **サービス名**
    - **サーバー IP/既存のサーバー**
    - **議定書**
    - **ポート**

#### ロード バランサーを構成します

ロード バランサーを構成するには:

1. **Traffic Management**&gt;**Load Balancing**&gt;**Virtual Servers** に移動します。
2. **追加**を選択します。
3. 下のスクリーンショットに示すように、次の値を設定します。

    - **名前**
    - **議定書**
    - **IPアドレス**
    - **ポート**
4. [ **OK] を選択します**。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [基本設定] ウィンドウのスクリーンショット。]

#### 仮想サーバーをバインドする

ロード バランサーを仮想サーバーにバインドするには:

1. [ **サービスとサービス グループ** ] ウィンドウで、[ **負荷分散仮想サーバー サービス バインドなし**] を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [負荷分散仮想サーバー サービス のバインド] ペインのスクリーンショット。]
2. 次のスクリーンショットに示すように設定を確認し、[ **閉じる**] を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成のスクリーンショット - 仮想サーバー サービスのバインドを確認します。]

#### 証明書をバインドする

このサービスを TLS として公開するには、サーバー証明書をバインドしてから自分のアプリケーションをテストします。

1. [ **証明書**] で、[ **サーバー証明書なし**] を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [サーバー証明書] ペインのスクリーンショット。]
2. 次のスクリーンショットに示すように設定を確認し、[ **閉じる**] を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成のスクリーンショット - 証明書を確認します。]

### Citrix ADC SAML Connector for Microsoft Entra SAML のプロファイル

Citrix ADC SAML Connector for Microsoft Entra プロファイルを構成するには、次のセクションを完了します。

#### 認証ポリシーを作成する

認証ポリシーを作成するには:

1. **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)**&gt;**[AAA - Application Traffic](AAA - アプリケーション トラフィック)**&gt;**[Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ポリシー)**&gt;**[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)**&gt;**[Authentication Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証ポリシー)** に移動します。
2. **追加**を選択します。
3. [ **認証ポリシーの作成** ] ウィンドウで、次の値を入力または選択します。

    - **名前**: 認証ポリシーの名前を入力します。
    - **アクション**: **SAML** を入力し、[ **追加**] を選択します。
    - **式**: **true を入力します**。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [認証ポリシーの作成] ウィンドウのスクリーンショット。]
4. **[作成]**を選択します。

#### 認証 SAML サーバーを作成する

認証 SAML サーバーを作成するには、[ **認証 SAML サーバーの作成** ] ウィンドウに移動し、次の手順を実行します。

1. **[名前]** に、認証 SAML サーバーの名前を入力します。
2. [ **SAML メタデータのエクスポート]** で、次の操作を行います。

    1. [ **メタデータのインポート** ] チェック ボックスをオンにします。
    2. 前に自分がコピーした、Azure SAML UI のフェデレーション メタデータ URL を入力します。
3. **[発行者名]** に、関連する URL を入力します。
4. **[作成]**を選択します。

[Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [Create Authentication SAML Server](認証 SAML サーバーの作成) ペインのスクリーンショット。]

#### 認証仮想サーバーを作成する

認証仮想サーバーを作成するには:

1. **[Security**&gt;**AAA - Application Traffic**&gt;**Policies**&gt;**Authentication**&gt;**Authentication Virtual Servers**] に移動します。
2. [ **追加]** を選択し、次の手順を実行します。

    1. [ **名前]** に、認証仮想サーバーの名前を入力します。
    2. [ **アドレス指定不可** ] チェック ボックスをオンにします。
    3. [ **プロトコル**] で 、[SSL] を選択 **します**。
    4. [ **OK] を選択します**。
3. [ **続行] を選択します**。

#### Microsoft Entra ID を使用するための認証仮想サーバーを構成する

認証仮想サーバーの 2 つのセクションを変更します。

1. [ **高度な認証ポリシー** ] ウィンドウで、[ **認証ポリシーなし**] を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [高度な認証ポリシー] ペインのスクリーンショット。]
2. [ **ポリシー バインド** ] ウィンドウで、認証ポリシーを選択し、[ **バインド**] を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra 構成のスクリーンショット - [ポリシー バインド] ペイン]
3. **[フォーム ベースの仮想サーバー**] ウィンドウで、[**負荷分散仮想サーバーなし**] を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成画面にある「フォーム ベース 仮想サーバー」ペインのスクリーンショット。]
4. **[認証 FQDN]** には、完全修飾ドメイン名 (FQDN) を入力します (必須)。
5. Microsoft Entra 認証によって保護する負荷分散仮想サーバーを選択します。
6. **バインド**を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - 負荷分散仮想サーバーバインド ウィンドウのスクリーンショット。]

    Note

    [**認証仮想サーバーの構成**] ウィンドウで必ず **[完了]** を選択してください。
7. 変更を確認するには、ブラウザーでアプリケーションの URL に移動します。 前に表示されていた非認証アクセスではなく、ご自分のテナントのサインイン ページが表示されます。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - Web ブラウザーのサインイン ページのスクリーンショット。]

### Kerberos ベースの認証用に Citrix ADC SAML Connector for Microsoft Entra SSO を構成する

#### Citrix ADC SAML Connector for Microsoft Entra ID 用の Kerberos 委任アカウントを作成する

1. ユーザー アカウントを作成します (この例では *AppDelegation* を使用します)。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [プロパティ] ペインのスクリーンショット。]
2. このアカウントに HOST SPN を設定します。

    例: `setspn -S HOST/AppDelegation.IDENTT.WORK identt\appdelegation`

    この例では、次のように記述されています。

    - `IDENTT.WORK` はドメインの FQDN です。
    - `identt` はドメインの NetBIOS 名です。
    - `appdelegation` は委任ユーザー アカウント名です。
3. 次のスクリーンショットに示すように、Web サーバーの委任を構成します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra 構成のスクリーンショット - [プロパティ] の [委任] ペイン。]

    Note

    スクリーンショットの例では、Windows 統合認証 (WIA) サイトを実行している内部 Web サーバー名は *CWEB2 です*。

#### Citrix ADC SAML Connector for Microsoft Entra AAA KCD (Kerberos 委任アカウント)

Citrix ADC SAML Connector for Microsoft Entra AAA KCD アカウントを構成するには、次の手順を実行します。

1. **Citrix Gateway**&gt;**AAA KCD (Kerberos の制約付き委任) アカウントに移動します**。
2. [ **追加]** を選択し、次の値を入力または選択します。

    - **名前**: KCD アカウントの名前を入力します。
    - **領域**: ドメインと拡張機能を大文字で入力します。
    - **サービス SPN**: `http/<host/fqdn>@<DOMAIN.COM>`。

        Note

        `@DOMAIN.COM` は必須です。また、大文字にする必要があります。 例: `http/cweb2@IDENTT.WORK`.
    - **委任されたユーザー**: 委任されたユーザー名を入力します。
    - [ **代理ユーザーのパスワード** ] チェック ボックスをオンにし、パスワードを入力して確認します。
3. [ **OK] を選択します**。

#### Citrix トラフィック ポリシーおよびトラフィック プロファイル

Citrix トラフィック ポリシーおよびトラフィック プロファイルを構成するには、次の手順を実行します。

1. **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)**&gt;**[AAA - Application Traffic](AAA - アプリケーション トラフィック)**&gt;**[Policies](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ポリシー)**&gt;**[Traffic Policies, Profiles and Form SSO ProfilesTraffic Policies](トラフィック ポリシー、プロファイル、およびフォーム SSO プロファイルのトラフィック ポリシー)** に移動します。
2. **トラフィック プロファイル**を選択します。
3. **追加**を選択します。
4. トラフィック プロファイルを構成するには、次の値を入力または選択します。

    - **名前**: トラフィック プロファイルの名前を入力します。
    - **シングル サインオン**: **[オン]** を選択します。
    - **KCD アカウント**: 前のセクションで作成した KCD アカウントを選択します。
5. [ **OK] を選択します**。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [トラフィック プロファイルの構成] ウィンドウのスクリーンショット。]
6. [ **トラフィック ポリシー] を選択します**。
7. **追加**を選択します。
8. トラフィック ポリシーを構成するには、次の値を入力または選択します。

    - **名前**: トラフィック ポリシーの名前を入力します。
    - **プロファイル**: 前のセクションで作成したトラフィック プロファイルを選択します。
    - **式**: **true を入力します**。
9. [ **OK] を選択します**。

    [Image: Citrix ADC SAML Connector for Microsoft Entra 構成のスクリーンショット - [トラフィック ポリシーの構成] ペイン]

#### Citrix でトラフィック ポリシーを仮想サーバーにバインドする

GUI を使用してトラフィック ポリシーを仮想サーバーにバインドするには、次の手順を実行します。

1. **Traffic Management**&gt;**Load Balancing**&gt;**Virtual Servers** に移動します。
2. 仮想サーバーの一覧で、書き換えポリシーをバインドする仮想サーバーを選択し、[ **開く**] を選択します。
3. [ **負荷分散仮想サーバー** ] ウィンドウの [ **詳細設定]** で、[ポリシー] を選択 **します**。 自分の NetScaler インスタンス用に構成されているすべてのポリシーが、一覧に表示されます。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [負荷分散仮想サーバー] ペインのスクリーンショット。]

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - ポリシーダイアログボックスのスクリーンショット。]
4. この仮想サーバーにバインドするポリシーの名前の横にあるチェック ボックスをオンにします。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [負荷分散仮想サーバー トラフィック ポリシーのバインド] ペインのスクリーンショット。]
5. [ **種類の選択** ] ダイアログ ボックスで、次の手順を実行します。

    1. [ **ポリシーの選択]** で、[トラフィック] を選択 **します**。
    2. **「種類の選択」** で、**「要求」** を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - 「種類選択」ペインのスクリーンショット。]
6. ポリシーがバインドされたら、[ **完了]** を選択します。

    [Image: Citrix ADC SAML Connector for Microsoft Entra の構成 - [ポリシー] ペインのスクリーンショット。]
7. WIA Web サイトを使用してバインドをテストします。

    [Image: Citrix ADC SAML Connector for Microsoft Entra 構成のスクリーンショット - Webブラウザー内のテストページ]

#### Citrix ADC SAML Connector for Microsoft Entra テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Citrix ADC SAML Connector for Microsoft Entra ID に作成します。 Citrix ADC SAML Connector for Microsoft Entra ID では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションには、ユーザー側で行うアクションはありません。 Citrix ADC SAML Connector for Microsoft Entra ID にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

Note

ユーザーを手動で作成する必要がある場合は、 [Citrix ADC SAML Connector for Microsoft Entra クライアント サポート チーム](https://www.citrix.com/contact/technical-support.html)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションを選択すると、ログイン フローを開始できる Citrix ADC SAML Connector for Microsoft Entra のサインオン URL にリダイレクトされます。
- Citrix ADC SAML Connector for Microsoft Entra のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで Citrix ADC SAML Connector for Microsoft Entra ID タイルを選択すると、このオプションは Citrix ADC SAML Connector for Microsoft Entra のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/citrixgotomeeting-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に GoToMeeting を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/citrixgotomeeting-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-04
- Summary: Microsoft Entra IDと GoToMeeting の間でシングル サインオンを構成する方法について説明します。

この記事の目的は、ユーザー アカウントを Microsoft Entra ID から GoToMeeting に自動的にプロビジョニングおよびプロビジョニング解除するために GoToMeeting とMicrosoft Entra IDで実行する必要がある手順を示することです。

警告

このプロビジョニング統合のサポートは終了しています。 その結果、Microsoft Entra Enterprise アプリ ギャラリーの GoToMeeting アプリケーションのプロビジョニング機能は間もなく削除されます。 アプリケーションの SSO 機能はそのまま維持されます。 Microsoft は GoToMeeting と協力して新しい最新化されたプロビジョニング統合を構築していますが、完了したタイミングに関するタイムラインはありません。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- Microsoft Entra テナント。
- GoToMeeting でのシングル サインオンが有効なサブスクリプション。
- Team Admin アクセス許可がある GoToMeeting のユーザー アカウント。

### GoToMeeting へのユーザーの割り当て

Microsoft Entra IDでは、"割り当て" という概念を使用して、選択したアプリにaccessを受け取るユーザーを決定します。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに "割り当てられている" ユーザーとグループのみが同期されます。

プロビジョニングサービスを構成して有効にする前に、あなたのGoToMeetingアプリにアクセスが必要なユーザーやグループがMicrosoft Entra ID内でどのユーザーやグループであるかを決定する必要があります。 決定し終えたら、次の手順でこれらのユーザーを GoToMeeting アプリに割り当てることができます。

[エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを GoToMeeting に割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを GoToMeeting に割り当てることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- GoToMeeting にユーザーを割り当てるときに、有効なユーザー ロールを選択する必要があります。 "既定のAccess" ロールは、プロビジョニングでは機能しません。

### 自動化されたユーザー プロビジョニングを有効にする

このセクションでは、Microsoft Entra ID を GoToMeeting のユーザーアカウントプロビジョニングAPIに接続し、Microsoft Entra ID のユーザーとグループの割り当てに基づいて、GoToMeeting におけるユーザーアカウントの作成、更新、無効化を行うプロビジョニングサービスの構成方法について説明します。

ヒント

Azure portal。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### 自動ユーザー アカウント プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. シングル サインオンのために GoToMeeting を既に構成している場合は、検索フィールドで GoToMeeting のインスタンスを検索します。 それ以外の場合は、[ **追加]** を選択し、アプリケーション ギャラリーで **GoToMeeting** を検索します。 検索結果から GoToMeeting を選択してアプリケーションの一覧に追加します。
4. GoToMeeting のインスタンスを選択し、[プロビジョニング] タブ **を** 選択します。
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、GoToMeeting テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが GoToMeeting に接続できることを確認します。 接続に失敗した場合は、GoToMeeting アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. Microsoft Entra IDから GoToMeeting に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で GoToMeeting のユーザー アカウントとの照合に使用されます。 [保存] ボタンをクリックして変更をコミットします。
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/civic-eye-sso-tutorial"} -->
## Microsoft Entra ID で CivicEye SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/civic-eye-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CivicEye SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、CivicEye SSO と Microsoft Entra ID を統合する方法について説明します。 既存の AD デプロイを通じて、CivicEye プラットフォームの顧客に SSO 機能を提供します。 CivicEye SSO を Microsoft Entra ID と統合すると、次のことができます。

- CivicEye SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して CivicEye SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

CivicEye SSO に対する Microsoft Entra シングル サインオンをテスト環境で構成してテストします。 CivicEye SSO は **SP** によって開始されるシングル サインオンをサポートしています。

### [前提条件]

Microsoft Entra ID と CivicEye SSO を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- CivicEye SSO のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから CivicEye SSO アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから CivicEye SSO を追加する

Microsoft Entra アプリケーション ギャラリーから CivicEye SSO を追加して、CivicEye SSO に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[CivicEye SSO]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.civiceye.com`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.civiceye.com/consumer`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.civiceye.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[CivicEye SSO のサポート チーム](mailto:help@civiceye.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Set up CivicEye SSO] (CivicEye SSO のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### CivicEye SSO を構成する

**CivicEye SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とアプリケーション構成からコピーした適切な URL を [CivicEye SSO のサポート チーム](mailto:help@civiceye.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### CivicEye SSO テスト ユーザーを作成する

このセクションでは、CivicEye SSO で Britta Simon というユーザーを作成します。 [CivicEye SSO のサポート チーム](mailto:help@civiceye.com)と連携して、CivicEye SSO プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる CivicEye SSO サインオン URL にリダイレクトされます。
- CivicEye SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで CivicEye SSO タイルを選択すると、このオプションは CivicEye SSO のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/civic-platform-tutorial"} -->
## Microsoft Entra ID で Civic Platform for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/civic-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Civic Platform の間のシングル サインオンを構成する方法について説明します。

この記事では、Civic Platform と Microsoft Entra ID を統合する方法について説明します。 Civic Platform を Microsoft Entra ID を統合すると、次のことができます。

- Civic Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Civic Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Civic Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Civic Platform では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Civic Platform の追加

Microsoft Entra ID への Civic Platform の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Civic Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Civic Platform**」と入力します。
4. 結果ウィンドウで **[Civic Platform]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Civic Platform に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Civic Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Civic Platform での関連ユーザーとの間にリンク関係を確立する必要があります。

Civic Platform に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Civic Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Civic Platform のテストユーザーを作成する** - Civic Platform で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザーとしてリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Civic Platform** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `civicplatform.accela.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.accela.com`

    注

    サインオン URL の値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、[Civic Platform クライアント サポート チーム](mailto:skale@accela.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [SAML 署名証明書] ページを示すスクリーンショット。ここでは、自分のアプリフェデレーション メタデータをコピーできます。]
7. **Entra ID**&gt;**App registrations** に移動し、アプリケーションを選択します。
8. **ディレクトリ (テナント) ID** をコピーし、メモ帳に保存します。

    [Image: ディレクトリ (テナント ID) をコピーし、自分のアプリ コードに保存する]
9. **アプリケーション ID** をコピーし、メモ帳に保存します。

    [Image: アプリケーション (クライアント) ID をコピーする]
10. **Entra ID**&gt;**App registrations** に移動し、アプリケーションを選択します。 **[証明書とシークレット]** を選択します。
11. **[クライアント シークレット] -&gt; [新しいクライアント シークレット]** を選択します。
12. シークレットの説明と期間を指定します。 完了したら、 **[追加]** をクリックします。

    注

    クライアント シークレットを保存すると、クライアント シークレットの値が表示されます。 キーは後で取得できないため、この値をコピーしておきます。

    [Image: 後からこれを取得することはできないので、このシークレット値をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Civic Platform の SSO の構成

1. 新しい Web ブラウザー ウィンドウを開き、Atlassian Cloud 企業サイトに管理者としてサインインします。
2. [ **標準の選択肢] を選択します**。

    [Image: スクリーンショットは、[Administrator Tools](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理ツール) で [Standard Choices](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/標準選択項目) が選択されている Atlassian Cloud サイトを示しています。]
3. 標準選択項目 **ssoconfig** を作成します。
4. **ssoconfig** を検索して送信します。

    [Image: スクリーンショットは、ssoconfig という名前が入力された [Standard Choices - Search](標準選択項目 - 検索) を示しています。]
5. 赤い点を選択して SSOCONFIG を展開します。

    [Image: スクリーンショットは、S S O CONFIG が利用できる [Standard Choices - Browse](標準選択項目 - 参照) を示しています。]
6. 次の手順に従って、SSO 関連の構成情報を指定します。

    [Image: スクリーンショットは、S S O CONFIG の [Standard Choices Item - Edit](標準選択項目 - 編集) を示しています。]

    1. **[applicationid] ** フィールドに、先ほどコピーした**アプリケーション ID** の値を入力します。
    2. **[clientSecret]** フィールドに、先ほどコピーした**シークレット**の値を入力します。
    3. **[directoryId]** フィールドに、先ほどコピーした**ディレクトリ (テナント) ID** の値を入力します。
    4. [idpName] を入力します。 例: `Azure`。

#### Civic Platform のテスト ユーザーの作成

このセクションでは、Civic Platform で B.Simon というユーザーを作成します。 Civic Platform サポート チームと連携し、[Civic Platform クライアント サポート チーム](mailto:skale@accela.com)にユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Civic Platform のサインオン URL にリダイレクトされます。
- Civic Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Civic Platform] タイルを選択すると、このオプションは Civic Platform のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clarivatewos-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ClarivateWOS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clarivatewos-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ClarivateWOS の間のシングル サインオンを構成する方法について説明します。

この記事では、ClarivateWOS と Microsoft Entra ID を統合する方法について説明します。 ClarivateWOS を Microsoft Entra ID と統合すると、次のことが可能になります。

- ClarivateWOS へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで ClarivateWOS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な ClarivateWOS のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ClarivateWOS では、**SP** によって開始される SSO がサポートされます。
- ClarivateWOS では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ClarivateWOS を追加する

Microsoft Entra ID への ClarivateWOS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ClarivateWOS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ClarivateWOS**」と入力します。
4. 結果のパネルから **[ClarivateWOS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ClarivateWOS に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、ClarivateWOS 用に Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ClarivateWOS の関連ユーザーとの間にリンク関係を確立する必要があります。

ClarivateWOS に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ClarivateWOS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ClarivateWOS テスト ユーザーの作成** - ClarivateWOS において B.Simon のカウンターパートユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**ClarivateWOS**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://sp.tshhosting.com/shibboleth`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://www.webofknowledge.com/?auth=Shibboleth`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.webofknowledge.com/`
6. ClarivateWOS アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、ClarivateWOS アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | user.userprincipalname |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | アプリケーション | "WOK" |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ClarivateWOS SSO を構成する

**ClarivateWOS** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [ClarivateWOS サポート チーム](mailto:shibbolethsupport@clarivate.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ClarivateWOS テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ClarivateWOS に作成します。 ClarivateWOS では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ClarivateWOS にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる ClarivateWOS のサインオン URL にリダイレクトされます。
- ClarivateWOS のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ClarivateWOS] タイルを選択すると、このオプションは ClarivateWOS のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clarizen-one-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Clarizen One を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clarizen-one-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-04
- Summary: Microsoft Entra IDから Clarizen One にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Clarizen One とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Clarizen One](https://www.clarizen.com/) に自動的にプロビジョニングし、プロビジョニング解除します。 このサービスの動作、しくみ、よく寄せられる質問については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用したサービスとしてのソフトウェア (SaaS) アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- Clarizen One でユーザーを作成する。
- アクセスが不要になったとき、Clarizen Oneのユーザーを削除します。
- Microsoft Entra IDと Clarizen One の間でユーザー属性の同期を維持します。
- Clarizen One にグループとグループ メンバーシップをプロビジョニングする。
- Clarizen One への[シングル サインオン (SSO)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clarizen-tutorial) をお勧めします。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Clarizen One の**統合ユーザー**および**Lite 管理者**[アクセス許可](https://success.clarizen.com/hc/articles/360011833079-API-Keys-Support)を持つユーザー アカウント。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとClarizen Oneの間で[対応付けるデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Clarizen One を構成する

1. Clarizen One 環境とデータセンターに応じて、次の 4 つのテナント URL のいずれかを選択します。

    - 米国運用データ センター: `https://servicesapp2.clarizen.com/scim/v2`
    - ヨーロッパ運用データ センター: `https://serviceseu1.clarizen.com/scim/v2`
    - 米国サンドボックス データ センター: `https://servicesapp.clarizentb.com/scim/v2`
    - ヨーロッパ サンドボックス データ センター: `https://serviceseu.clarizentb.com/scim/v2`
2. [API キー](https://success.clarizen.com/hc/articles/360011833079-API-Keys-Support)を生成します。 この値は、Clarizen One アプリケーションの [**プロビジョニング**] タブの [**シークレット トークン**] ボックスに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Clarizen One を追加する

Microsoft Entra アプリケーション ギャラリーから Clarizen One を追加して、Clarizen One へのプロビジョニングの管理を開始します。 SSO のために Clarizen One を以前に設定している場合は、同じアプリケーションを使用できます。 統合を初めてテストするときは、別のアプリを作成してください。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [Microsoft Entra テナントにアプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)」を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Clarizen One への自動ユーザー プロビジョニングを構成する

このセクションは、Microsoft Entra ID のユーザーまたはグループの割り当てに基づいて、TestApp でユーザーまたはグループを作成、更新、無効にするための Microsoft Entra プロビジョニング サービスの構成手順を案内します。

#### Microsoft Entra IDで Clarizen One の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ウィンドウを示すスクリーンショット。]
3. アプリケーションの一覧で **Clarizen One** を選択します。

    [Image: アプリケーションの一覧の Clarizen One リンクを示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Clarizen One テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Clarizen One に接続できることを確認します。 接続に失敗した場合は、Clarizen One アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Clarizen One に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Clarizen One のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Clarizen One API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | displayName | 糸 |
    | 活動中 | ブール値 |
    | タイトル | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | メール[タイプ eq "自宅"].値 | 糸 |
    | emails[タイプ eq "その他"].値 | 糸 |
    | 優先言語 | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | name.formatted | 糸 |
    | name.honorificPrefix | 糸 |
    | name.honorificSuffix | 糸 |
    | アドレス[タイプ eq "other"].フォーマット済み | 糸 |
    | addresses[type eq "work"].フォーマット済み | 糸 |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |
    | phoneNumbers[type eq "ファックス"].value | 糸 |
    | 電話番号[タイプ eq "home"].値 | 糸 |
    | phoneNumbers[種類 eq "その他"].value | 糸 |
    | 電話番号[タイプイコール "ポケベル"].値 | 糸 |
    | externalId | 糸 |
    | ニックネーム | 糸 |
    | ロケール | 糸 |
    | roles[primary eq "True".type] | 糸 |
    | roles[primary eq "True".value] | 糸 |
    | タイムゾーン | 糸 |
    | ユーザータイプ | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 関連項目 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Clarizen One に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Clarizen One のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | externalId | 糸 |
    | members | 関連項目 |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### トラブルシューティングのヒント

Clarizen One ギャラリー アプリにユーザーを割り当てるときは、 **ユーザー** ロールのみを選択します。 次のロールは無効です。

- 管理者 (Admin)
- 電子メール レポート ユーザー
- 外部ユーザー
- 財務ユーザー
- ソーシャル ユーザー
- スーパーユーザー
- 時間と経費ユーザー
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clarizen-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Clarizen One を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clarizen-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Clarizen One の間のシングル サインオンを構成する方法について説明します。

この記事では、Clarizen One と Microsoft Entra ID を統合する方法について説明します。 Clarizen One を Microsoft Entra ID と統合すると、次のことができます。

- Clarizen One にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Clarizen One に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Clarizen One でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Clarizen One では、**IDP** Initiated SSO がサポートされます。
- Clarizen One では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clarizen-one-provisioning-tutorial) (推奨) がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Clarizen One の追加

Microsoft Entra ID への Clarizen One の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Clarizen One を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Clarizen One**」と入力します。
4. 結果のパネルから **[Clarizen One]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Clarizen On に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Clarizen One に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Clarizen One の関連ユーザーとの間にリンク関係を確立する必要があります。

Clarizen One に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Clarizen One の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Clarizen のテスト ユーザーの作成** - Clarizen One で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Clarizen One**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、値を入力します。 `Clarizen`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.clarizen.com/Clarizen/Pages/Integrations/SAML/SamlResponse.aspx`

    注

    この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Clarizen One のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Clarizen One の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Clarizen One 企業サイトに管理者としてサインインします。
2. ユーザー名を選択し、[ **設定]** を選択します。

    [Image: ユーザー名の下にある [設定] を選択] を選択する
3. [ **グローバル設定] タブを** 選択します。次に、[ **フェデレーション認証**] の横にある **[編集**] を選択します。

    [Image: [Global Settings (グローバル設定)] タブ]
4. **[Federated Authentication (フェデレーション認証)]** ダイアログ ボックスで、次の手順を実行します。

    [Image: [Federated Authentication (フェデレーション認証)] ダイアログ ボックス]

    a. **[Enable Federated Authentication (フェデレーション認証を有効にする)]** をオンにします。

    b。 [ **アップロード]** を選択して、ダウンロードした証明書をアップロードします。

    c. **[サインイン URL])** ボックスに、Microsoft Entra アプリケーション構成ウィンドウの **[ログイン URL]** の値を入力します。

    d. **[サインアウト URL]** ボックスに、Microsoft Entra アプリケーション構成ウィンドウの **[ログアウト URL]** の値を入力します。

    e. [**Use POST**] を選択します。

    f. **保存** を選択します。

#### Clarizen One のテスト ユーザーの作成

このセクションの目的は、Clarizen One で Britta Simon というユーザーを作成することです。

**ユーザーを手動で作成する必要がある場合は、次の手順を実行してください。**

Microsoft Entra ユーザーが Clarizen One にサインインできるようにするには、ユーザー アカウントをプロビジョニングする必要があります。 Clarizen One の場合、プロビジョニングは手動で行います。

1. Clarizen One 企業サイトに管理者としてサインインします。
2. **[People]** を選びます。

    [Image: [ユーザ] の選択]
3. [ **ユーザーの招待]** を選択します。

    [Image: [Invite User (ユーザーの招待)] ボタン]
4. **[Invite People (ユーザーの招待)]** ダイアログ ボックスで、次の手順を実行します。

    [Image: [Invite People (ユーザーの招待)] ダイアログ ボックス]

    a. **[Email (電子メール)]** ボックスに、Britta Simon アカウントの電子メール アドレスを入力します。

    b。 [ **招待**] を選択します。

    注

    Microsoft Entra アカウント所有者が電子メールを受信し、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Clarizen One に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Clarizen One] タイルを選択すると、SSO を設定した Clarizen One に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/claromentis-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Claromentis を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/claromentis-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Claromentis の間のシングル サインオンを構成する方法について説明します。

この記事では、Claromentis と Microsoft Entra ID を統合する方法について説明します。 Claromentis を Microsoft Entra ID と統合すると、次のことが可能になります。

- Claromentis にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Claromentis に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Claromentis でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Claromentis では、**SP と IDP** がサポートされます。
- Claromentis では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Claromentis の追加

Microsoft Entra ID への Claromentis の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Claromentis を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Claromentis**」と入力します。
4. 結果のパネルから **[Claromentis]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Claromentis に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Claromentis に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Claromentis の関連ユーザーとの間にリンク関係を確立する必要があります。

Claromentis に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Claromentis SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Claromentis のテストユーザーを作成** - Microsoft Entra のユーザー表現と連携して、Claromentis 上で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Claromentis**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. **[識別子]** ボックスに、組織の要件に従って識別子の値を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_SITE_URL>/custom/loginhandler/simplesaml/www/module.php/saml/sp/saml2-acs.php/claromentis`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<CUSTOMER_SITE_URL>/login` |
    | `https://<CUSTOMER_SITE_URL>/login?no_auto=0` |

    注

    これらの値は実際の値ではありません。 これらの値は、記事の後半で説明する実際の応答 URL とサインオン URL で更新します。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Claromentis のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Claromentis SSO の構成

1. 別のブラウザー ウィンドウで、Claromentis Web サイトに管理者としてサインインします。
2. **アプリケーション アイコン**を選択し、[管理者] を選択**します**。

    [Image: スクリーンショットは、[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) が選択されている Claromentis Web サイトを示します。]
3. **[Custom Login Handler](カスタム ログイン ハンドラー)** タブを選択します。

    [Image: スクリーンショットは、[Custom Login Handler](カスタム ログイン ハンドラー) が選択されている [管理者] ページを示します。]
4. **[SAML Config](SAML 構成)** を選択します。

    [Image: スクリーンショットは、SAML の構成ページを示します。]
5. **[SAML Config](SAML 構成)** タブで **[Config](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成)** セクションまで下へスクロールし、以下の手順を実行します。

    [Image: スクリーンショットは、この手順で説明されている情報を入力できるページの [Config](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) セクションを示します。]

    a. **[Technical Contact Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/技術部連絡先名)** ボックスに、技術部連絡先担当者の名前を入力します。

    b。 **[Technical Contact Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/技術部連絡先メール)** ボックスに、技術部連絡先担当者のメール アドレスを入力します。

    c. **[Auth Admin Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証管理者パスワード)** ボックスにパスワードを指定します。
6. **[Auth Sources](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証ソース)** まで下へスクロールし、以下の手順を実行します。

    [Image: スクリーンショットは、この手順で説明されている情報を入力できる [Auth Sources](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証ソース) セクションを示します。]

    a. **[IDP]** テキストボックスに、先ほどコピーした **Microsoft Entra ID** の値を入力します。

    b。 **[Entity ID](エンティティ ID)** ボックスに、エンティティ ID 値を入力します。

    c. ダウンロードした**フェデレーション メタデータ XML** ファイルをアップロードします。

    d. **保存** を選択します。
7. **SAML 構成**セクションの **ID プロバイダー** セクション内にすべての URL が設定されていることがわかります。

    [Image: スクリーンショットは、URL が設定されている [Identity Provider](ID プロバイダー) ページを示します。]

    a. **[Identifier (Entity ID)](識別子 (エンティティ ID))** の値をコピーして、Azure portal の **[基本的な SAML 構成]** セクションにある **[識別子]** ボックスに貼り付けます。

    b。 **[Reply URL](応答 URL)** の値をコピーして、Azure portal の **[基本的な SAML 構成]** セクションにある **[応答 URL]** ボックスに貼り付けます。

    c. **[Sign On URL](サインオン URL)** の値をコピーして、Azure portal の **[基本的な SAML 構成]** セクションにある **[サインオン URL]** ボックスに貼り付けます。

#### Claromentis テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Claromentis に作成します。 Claromentis では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Claromentis にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Claromentis のサインオン URL にリダイレクトされます。
- Claromentis のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Claromentis に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Claromentis] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Claromentis に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cleanmail-swiss-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Cleanmail Swiss を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cleanmail-swiss-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-04
- Summary: Microsoft Entra IDから Cleanmail Swiss にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Cleanmail Swiss と Microsoft Entra ID の両方で自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [Cleanmail](https://www.alinto.com/fr) に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Cleanmail でユーザーを作成する
- アクセスが不要になった場合は、Cleanmail Swiss のユーザーを削除します。
- Microsoft Entra IDと Cleanmail の間でユーザー属性の同期を維持する
- Cleanmail Swiss に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Cleanmail Swiss のユーザー アカウント

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra IDとCleanmail](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)の間でマップするデータを決定する。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Cleanmail Swiss を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように Cleanmail Swiss を構成するには、[Cleanmail Swiss サポート](https://www.alinto.com/contact-email-provider/) にお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Cleanmail Swiss を追加する

Microsoft Entra アプリケーション ギャラリーから Cleanmail Swiss を追加して、Cleanmail へのプロビジョニングの管理を開始します。 SSO 用に Cleanmail Swiss を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Cleanmail Swiss への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDでのユーザーの割り当てに基づいて Cleanmail Swiss のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順をguidesします。

#### Microsoft Entra IDで Cleanmail Swiss の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Cleanmail**] を選択します。

    [Image: アプリケーションの一覧の Cleanmail Swiss リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Cleanmail Swiss テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra ID が Cleanmail Swiss に接続できることを確認します。 接続に失敗した場合は、Cleanmail Swiss アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cleanmail Swiss に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Cleanmail Swiss のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Cleanmail Swiss API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | Cleanmail で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | アクティブ | ブール値 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clearcompany-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ClearCompany を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clearcompany-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ClearCompany の間のシングル サインオンを構成する方法について説明します。

この記事では、ClearCompany と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と ClearCompany を統合すると、次のことができます。

- ClearCompany にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで ClearCompany に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ClearCompany でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ClearCompanyは、**SPおよびIDP**によるSSOの開始をサポートします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ClearCompany の追加

Microsoft Entra ID への ClearCompany の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ClearCompany を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;から**新しいアプリケーション**を開きます。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ClearCompany**」と入力します。
4. 結果パネルから **ClearCompany** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ClearCompany の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、ClearCompany に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ClearCompany の関連ユーザーとの間にリンク関係を確立する必要があります。

ClearCompany に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ClearCompany SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ClearCompany のテスト ユーザーの作成** - ClearCompany で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ClearCompany]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://api.clearcompany.com`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://api.clearcompany.com/v1/auth/sso/saml`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.clearcompany.com`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、 [ClearCompany クライアント サポート チーム](https://www.clearcompany.com/support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **ClearCompany のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ClearCompany SSO の構成

**ClearCompany** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [ClearCompany サポート チーム](https://www.clearcompany.com/support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ClearCompany のテスト ユーザーの作成

このセクションでは、ClearCompany で Britta Simon というユーザーを作成します。 [ClearCompany サポート チーム](https://www.clearcompany.com/support)と協力して、ClearCompany プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ClearCompany のサインオン URL にリダイレクトされます。
- ClearCompany のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ClearCompany に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで ClearCompany タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ClearCompany に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clearreview-tutorial"} -->
## Microsoft Entra ID を使用して Clear Review のシングル サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clearreview-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Clear Review の間のシングル サインオンを構成する方法について説明します。

この記事では、Clear Review と Microsoft Entra ID を統合する方法について説明します。 Clear Review を Microsoft Entra ID と統合すると、次のことができます。

- Clear Review にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Clear Review に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

Clear Review は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Clear Review でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Clear Review では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの Clear Review の追加

Microsoft Entra ID への Clear Review の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Clear Review を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Clear Review」**と入力します。
4. 結果パネルから **[レビューのクリア** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Clear Review の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Clear Review に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Clear Review の関連ユーザーとの間にリンク関係を確立する必要があります。

Clear Review に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Clear Review SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Clear Review のテスト ユーザーの作成** - Clear Review で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Clear Review**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.clearreview.com/sso/metadata/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.clearreview.com/sso/acs/`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.clearreview.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Clear Review クライアント サポート チーム](https://clearreview.com/contact/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Clear Review アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 Clear Review アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集** ] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: [編集] アイコンが選択されているユーザー属性を示すスクリーンショット。]
8. [ **ユーザー属性と要求** ] ダイアログで、次の手順を実行します。

    a. **[名前識別子の値**] の右側にある **[編集] アイコン**を選択します。

    [Image: [編集] アイコンが選択されている [ユーザー属性] と [要求] を示すスクリーンショット。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 **ソース属性**の一覧から、その行の **user.mail** 属性値を選択します。

    c. **[保存] を選択します**。
9. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Clear Review のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Clear Review SSO の構成

1. **Clear Review** 側でシングル サインオンを構成するには、管理者の資格情報を使用して **Clear Review** ポータルを開きます。
2. 左側のナビゲーションから **[管理者]** を選択します。

    [Image: スクリーンショットは、[管理者] が選択された [レビューのクリア] ポータルを示しています。]
3. ページの下部にある [**統合**] セクションで、[**Single Sign-On Settings]\(単一の Sign-On 設定**\) の右側にある **[変更**] ボタンを選択します。

    [Image: [Single Sign-On Change](単一 Sign-On 変更) ボタンを示すスクリーンショット。]
4. **[単一 Sign-On 設定]** ページで次の手順を実行します。

    [Image: この手順の情報を入力できる [Single Sign-On Settings](単一の Sign-On 設定) ページを示すスクリーンショット。]

    a. **[発行者 URL**] ボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    b。 **[SAML エンドポイント]** ボックスに、**ログイン URL** の値を貼り付けます。

    c. **[SLO エンドポイント]** ボックスに、**ログアウト URL** の値を貼り付けます。

    d. ダウンロードした証明書をメモ帳で開き、[ **X.509 証明書** ] ボックスに内容を貼り付けます。

    e. **[保存] を選択します**。

#### Clear Review のテスト ユーザーの作成

このセクションでは、Clear Review で Britta Simon というユーザーを作成します。 [Clear Review サポート チーム](https://clearreview.com/contact/)と協力して、Clear Review プラットフォームにユーザーを追加してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Clear Review Sign on URL にリダイレクトされます。
- Clear Review のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Clear Review に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [確認のクリア] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Clear Review に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clearview-trade-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に ClearView Trade を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clearview-trade-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-04
- Summary: Microsoft Entra IDから ClearView Trade にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、ClearView Trade と Microsoft Entra ID の両方で実行して、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成済みの Microsoft Entra ID により、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [ClearView Trade](https://gateway.clearviewtrade.com) に自動的にプロビジョニングおよび削除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- ClearView Trade でユーザーを作成します。
- accessが不要になったら、ClearView Trade のユーザーを削除します。
- Microsoft Entra IDと ClearView Trade の間でユーザー属性の同期を維持します。
- ClearView Trade に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある ClearView Trade のユーザー アカウント。
- この機能を完全にaccessするには、[こちら](https://clearviewtrade.com/en/single-sign-on-and-scim/)に記載されている登録プロセスを確認してください。

### 手順 一:プロビジョニングのデプロイを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- Microsoft Entra IDとClearView Tradeの間で[マップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra アプリケーション ギャラリーから ClearView Trade を追加する

Microsoft Entra アプリケーション ギャラリーから ClearView Trade を追加して、ClearView Trade へのプロビジョニングの管理を開始します。 SSO のために ClearView Trade を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 3: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 4: ClearView Trade への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID におけるユーザーの割り当てに基づいて、ClearView Trade でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順を説明します。

#### Microsoft Entra IDで ClearView Trade の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **ClearView Trade** を選択します。

    [Image: アプリケーションの一覧の ClearView Trade リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、ClearView Trade Tenant URL と Secret Token を入力します。 [**Test Connection** を選択して、Microsoft Entra IDが ClearView Trade に接続できることを確認します。 接続に失敗した場合は、ClearView Trade アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから ClearView Trade に同期されるユーザー属性を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で ClearView Trade のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、ClearView Trade API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | ClearView Trade で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | 名前.名 | 糸 |  | ✓ |
    | 名前.姓 | 糸 |  | ✓ |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | エクスターナルID | 糸 |  | ✓ |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clebex-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Clebex を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clebex-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-31
- Summary: ユーザー アカウントを Microsoft Entra ID から Clebex に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Clebex と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Clebex](https://www.clebex.com/en/index.html) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Clebex でユーザーを作成する
- アクセスが不要になった場合に Clebex のユーザーを削除する
- Microsoft Entra ID と Clebex の間でユーザー属性の同期を維持する
- Clebex への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clebex-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 作成および編集アクセス許可を持つ Clebex のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Clebex の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用してプロビジョニングをサポートするように Clebex を構成する

1. Clebex HUB にログインします。
2. **コネクタ**&gt;**SCIM**&gt;**Azure SCIM** に移動します。
3. **アクティブ**ボタンを切り替えます。
4. **URL** と**トークン**をコピーします。 この値は、Clebex アプリケーションの [プロビジョニング] タブの **[テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: コネクタのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Clebex を追加する

Microsoft Entra アプリケーション ギャラリーから Clebex を追加して、Clebex へのプロビジョニングの管理を開始します。 SSO のために Clebex を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Clebex への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Clebex に対する自動ユーザー プロビジョニングを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Clebex** を選択します。

    [Image: アプリケーションの一覧の [Clebex] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Clebex テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Clebex に接続できることを確認します。 接続に失敗した場合は、Clebex アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. [属性マッピング] セクションで、Microsoft Entra ID から Clebex に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Clebex のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Clebex API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 |  |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | displayName | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clebex-tutorial"} -->
## Microsoft Entra ID で Clebex for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clebex-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Clebex の間のシングル サインオンを構成する方法について説明します。

この記事では、Clebex と Microsoft Entra ID を統合する方法について説明します。 Clebex を Microsoft Entra ID と統合すると、次のことが可能になります。

- Clebex にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Clebex に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Clebex でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Clebex では、 **SP** Initiated SSO がサポートされます。
- Clebex では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- Clebex では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clebex-provisioning-tutorial)。

### ギャラリーから Clebex を追加する

Microsoft Entra ID への Clebex の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Clebex を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Clebex**」と入力します。
4. 結果パネルから **Clebex** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Clebex に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Clebex に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Clebex の関連ユーザーとの間にリンク関係を確立する必要があります。

Clebex で Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Clebex SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Clebex テスト ユーザーを作成する - Clebex における B.Simon に対応するユーザーを作成し、それを Microsoft Entra の B.Simon とリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Clebex**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.domain.extention/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.domain.extention/<ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.domain.extention/<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、Sign-On URL でこれらの値を更新します。 これらの値を取得するには [、Clebex クライアント サポート チーム](mailto:support@clebex.net) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Clebex のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Clebex の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Clebex 企業サイトに管理者としてサインインします。
2. COMPANY ADMIN -&gt;**Connectors**&gt;**Single Sign On (SSO) に** 移動し **、選択します**。

    [Image: コネクタの種類を選択するスクリーンショット。]
3. CONNECTORS で Azure **SSO** を選択し、EDIT-CONNECTOR セクションで次の手順を実行します。

    [Image: コネクタと構成を選択するスクリーンショット。]

    a. **IDENTIFIER 値を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。

    b。 **[応答 URL]** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    c. **ENTITY ID** テキスト ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    d. **SAML** テキスト ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    e. ダウンロードした **証明書 (Base64)** をメモ帳に開き、内容を **CERTIFICATE** テキストボックスに貼り付けます。

    f. [ **SAVE-CHANGES]\(変更の保存\) を選択します**。

#### Clebex のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Clebex に作成します。 Clebex では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Clebex にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

Clebex では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clebex-provisioning-tutorial) 。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Clebex のサインオン URL にリダイレクトされます。
- Clebex のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Clebex] タイルを選択すると、このオプションは Clebex のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clever-nelly-tutorial"} -->
## Microsoft Entra ID で Clever Nelly for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clever-nelly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Clever Nelly の間のシングル サインオンを構成する方法について説明します。

この記事では、Clever Nelly と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Clever Nelly を統合すると、次のことができます。

- Clever Nelly にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Clever Nelly に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Clever Nelly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Clever Nelly では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Clever Nelly の追加

Microsoft Entra ID への Clever Nelly の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Clever Nelly を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Clever Nelly**」と入力します。
4. 結果ウィンドウで **[Clever Nelly]** を選択してそのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Clever Nelly の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Clever Nelly に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Clever Nelly の関連ユーザーとの間にリンク関係を確立する必要があります。

Clever Nelly に対して Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Clever Nelly の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Clever Nelly のテストユーザーを作成し** - Clever Nellyで Microsoft Entraのユーザー表現にリンクされたB.Simonに対応するユーザーを作ります。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Clever Nelly** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** Initiated モードで構成する場合は、次の手順を行います。

    a. **[識別子]** テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | テスト | `https://test.elephantsdontforget.com/plato` |
    | 生産 | `https://secure.elephantsdontforget.com/plato` |
    |  |  |

    b。 **[応答 URL]** ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | テスト | `https://test.elephantsdontforget.com/plato/callback?client_name=SAML2Client` |
    | 生産 | `https://secure.elephantsdontforget.com/plato/callback?client_name=SAML2Client` |
    |  |  |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL パターン |
    | --- | --- |
    | テスト | `https://test.elephantsdontforget.com/plato/sso/microsoft/index.xhtml` |
    | 生産 | `https://secure.elephantsdontforget.com/plato/sso/microsoft/index.xhtml` |
    |  |  |
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Clever Nelly SSO の構成

**Clever Nelly** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Clever Nelly サポート チーム](mailto:support@elephantsdontforget.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Clever Nelly テスト ユーザーの作成

このセクションでは、Clever Nelly で Britta Simon というユーザーを作成します。 [Clever Nelly サポート チーム](mailto:support@elephantsdontforget.com)と連携して、Clever Nelly プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Clever Nelly のサインオン URL にリダイレクトされます。
- Clever Nelly のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Clever Nelly に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Clever Nelly タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Clever Nelly に自動的にサインインされます。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clever-tutorial"} -->
## Microsoft Entra ID で Clever for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clever-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra IDと Clever の間でシングル サインオンを構成する方法について説明します。

この記事では、Clever と Microsoft Entra ID を統合する方法について説明します。 Clever と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra IDでCleverへのアクセスを制御します。
- ユーザーが自分のMicrosoft Entra アカウントを使用して Clever に自動的にサインインできるように設定できます。
- 1 つの場所でアカウントを管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Clever でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で sso Microsoft Entraを構成し、テストします。

- Clever では、**SP** Initiated SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Clever の追加

Microsoft Entra IDへの Clever の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Clever を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Clever**」と入力します。
4. 結果ウィンドウで **Clever** を選択してそのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細については](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)を参照してください。

Note

SAML 属性と要求の構成に関する詳細なガイダンスについては、「 [シングル サインオン SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)」を参照してください。

### Clever のMicrosoft Entra SSO の構成とテスト

**B.Simon** というテストユーザーを使用して、Clever と Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Clever の関連ユーザーとの間にリンク関係を確立する必要があります。

Clever Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**を構成する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra のテストユーザーを作成** - B.Simon を使用して Microsoft Entra のシングルサインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra のシングルサインオンを利用できるようにします。
2. **Clever の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cleverテストユーザーを作成する** - Clever で B.Simon と対応するユーザーを作成し、Microsoft Entraにおけるユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Clever**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** ボックスに `https://clever.com/oauth/saml/metadata.xml` という URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://clever.com/<COMPANY_NAME>` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://clever.com/in/<COMPANY_NAME>`

    Note

    これらの値は実際の値ではありません。 これらの値を、実際の応答 URL およびサインオン URL で更新してください。 この値を取得するには、[Clever クライアント サポート チーム](https://clever.com/about/contact/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]

#### Microsoft Entra のテストユーザーを作成して割り当てる。

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Clever の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Clever ディストリクト ダッシュボードに管理者としてログインします。
2. 左側のナビゲーションから、 **メニュー**&gt;**Portal**&gt;**SSO 設定**を選択します。
3. **[SSO Settings]** ページで、次の手順を実行します。

    a. **[Add Login Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログイン方法の追加)** を選択します。

    b。 [**Active Directory 認証** を選択します。

    c. ダウンロードした **App Federation Metadata Url** をメモ帳に開き、その内容を **Metadata URL** ボックスの **Configure Active Directory Authentication** ダイアログに貼り付けます。

    [Image: 証明書のアップロード]

    d. **[保存] を選択します**。

#### Clever テスト ユーザーの作成

Microsoft Entraユーザーが Clever にサインインできるようにするには、ユーザーを Clever にプロビジョニングする必要があります。

Clever の場合は、[Clever クライアント サポート チーム](https://clever.com/about/contact/)と協力して、Clever プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

Note

Clever が提供する他の Clever ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entraユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra シングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Clever のサインオン URL にリダイレクトされます。
- Clever のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Clever タイルを選択すると、このオプションは Clever のサインオン URL にリダイレクトされます。 マイ アプリの詳細に関しては、「[マイ アプリ イントロダクション](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clicktime-tutorial"} -->
## Microsoft Entra ID で ClickTime for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clicktime-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ClickTime の間のシングル サインオンを構成する方法について説明します。

この記事では、ClickTime と Microsoft Entra ID を統合する方法について説明します。 ClickTime を Microsoft Entra ID と統合すると、次のことが可能になります。

- ClickTime にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで ClickTime に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ClickTime でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ClickTime では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ClickTime を追加する

Microsoft Entra ID への ClickTime の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ClickTime を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ClickTime**」と入力します。
4. 結果パネルから **ClickTime** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ClickTime に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ClickTime に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、ClickTime の関連ユーザーとの間にリンク関係を確立する必要があります。

ClickTime で Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ClickTime SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ClickTime テスト ユーザーの作成** - ClickTime で B.Simon に対応するユーザーを作成し、Microsoft Entra でのユーザーにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ClickTime**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://app.clicktime.com/sp/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://app.clicktime.com/Login/` |
    | `https://app.clicktime.com/App/Login/Consume.aspx` |
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **ClickTime のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ClickTime SSO を構成する

1. 別の Web ブラウザー ウィンドウで、ClickTime 企業サイトに管理者としてログインします。
2. 上部のツール バーで、[ **基本設定]** を選択し、[ **セキュリティ設定]** を選択します。
3. [ **Single Sign-On Preferences configuration** ] セクションで、次の手順を実行します。

    [Image: セキュリティ設定の]

    a. [**Microsoft Entra ID** でシングル Sign-On (SSO) を使用してサインイン**を許可する**] を選択します。

    b。 [ **ID プロバイダー エンドポイント** ] ボックスに、 **ログイン URL を**貼り付けます。

    c. Azure portal からダウンロード **した base-64 でエンコードされた証明書** を **メモ帳**で開き、内容をコピーして **、[X.509 証明書** ] ボックスに貼り付けます。

    d. **[保存] を選択します**。

#### ClickTime のテスト ユーザーの作成

Microsoft Entra ユーザーが ClickTime にログインできるようにするには、そのユーザーを ClickTime にプロビジョニングする必要があります。 ClickTime の場合、プロビジョニングは手動で行います。

注

ClickTime から提供されている他の ClickTime ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **ClickTime** テナントにログインします。
2. 上部のツール バーで、[ **会社**] を選択し、[ **ユーザー**] を選択します。

    [Image: スクリーンショットは、[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社) と [People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) が選択されている ClickTime テナントを示しています。]
3. [ **ユーザーの追加] を選択します**。

    [Image: 人物の追加]
4. [New Person] セクションで、次の手順を実行します。

    [Image: この手順で情報を追加できる [Add Person](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) セクションを示すスクリーンショット。]

    a. **[Full name**]\(フル ネーム\) ボックスに、**Britta Simon** のようなユーザーのフル ネームを入力します。

    b。 **電子メール アドレス**のテキスト ボックスに、ユーザーの電子メール (**brittasimon@contoso.com**など) を入力します。

    注

    必要に応じて、新しいユーザー オブジェクトの追加プロパティを設定できます。

    c. **[保存] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ClickTime に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ClickTime] タイルを選択すると、SSO を設定した ClickTime に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clickup-productivity-platform-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に ClickUp Productivity Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clickup-productivity-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ClickUp Productivity Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、ClickUp Productivity Platform と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と ClickUp Productivity Platform を統合すると、次のことが可能になります。

- ClickUp Productivity Platform にアクセスする Microsoft Entra ID ユーザーを制御する。
- ユーザーが自分の Microsoft Entra アカウントで ClickUp Productivity Platform に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ClickUp Productivity Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ClickUp Productivity Platform では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの ClickUp Productivity Platform の追加

ClickUp Productivity Platform と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に ClickUp Productivity Platform をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ClickUp Productivity Platform**」と入力します。
4. 結果パネルから **ClickUp Productivity Platform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ClickUp Productivity Platform 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ClickUp Productivity Platform に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、ClickUp Productivity Platform での関連ユーザーとの間にリンク関係を確立する必要があります。

ClickUp Productivity Platform で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ClickUp Productivity Platform の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **ClickUp Productivity Platform のテスト ユーザーを作成する** - ClickUp Productivity Platform で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ClickUp Productivity Platform]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.clickup.com/login/sso`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.clickup.com/v1/team/<team_id>/microsoft`

    注

    識別子の値は実際の値ではありません。 この値を実際の識別子で更新します。これについては、この記事の後半で説明します。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ClickUp Productivity Platform の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として ClickUp Productivity Platform テナントにサインオンします。
2. **ユーザー プロファイル**を選択し、[**設定]** を選択します。

    [Image: [設定] アイコンが選択されている ClickUp Productivity テナントを示すスクリーンショット。]

    [Image: [設定] を示すスクリーンショット。]
3. [Single Sign-On (SSO) Provider]\(シングル Sign-On (SSO) プロバイダー\) で **Microsoft** を選択します。

    [Image: Microsoft が選択されている [認証] ウィンドウを示すスクリーンショット。]
4. [ **Microsoft シングル サインオンの構成** ] ページで、次の手順を実行します。

    [Image: スクリーンショットは、エンティティ ID をコピーして Azure フェデレーション メタデータ U R L を保存できる [Microsoft シングル サインオンの構成] ページを示しています。]

    a. [**コピー]** を選択してエンティティ ID の値をコピーし、[**基本的な SAML 構成**] セクションの **[識別子 (エンティティ ID)]** ボックスに貼り付けます。

    b。 **[Azure フェデレーション メタデータ URL**] ボックスに、コピーしたアプリのフェデレーション メタデータ URL の値を貼り付け、[**保存]** を選択します。
5. セットアップを完了するには、[ **Microsoft による認証] を選択してセットアップを完了** し、Microsoft アカウントで認証します。

    [Image: [設定を完了するために Microsoft で認証する] ボタンを示すスクリーンショット。]

#### ClickUp Productivity Platform のテスト ユーザーの作成

1. 別の Web ブラウザーのウィンドウで、管理者として ClickUp Productivity Platform テナントにサインオンします。
2. **[ユーザー プロファイル**] を選択し、[**ユーザー**] を選択します。

    [Image: ClickUp Productivity テナントを示すスクリーンショット。]

    [Image: スクリーンショットは、[People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) リンクが選択されている状態を示しています。]
3. テキスト ボックスにユーザーのメール アドレスを入力し、[ **招待**] を選択します。

    [Image: スクリーンショットには、メールでユーザーを招待できる [チーム ユーザーの設定] が示されています。]

    注

    ユーザーは通知を受け取ったら、招待を承諾してアカウントをアクティブにする必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる ClickUp Productivity Platform のサインオン URL にリダイレクトされます。
- ClickUp Productivity Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ClickUp Productivity Platform] タイルを選択すると、このオプションは ClickUp Productivity Platform のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/clockwork-recruiting-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Clockwork Recruiting を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/clockwork-recruiting-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Clockwork Recruiting の間のシングル サインオンを構成する方法について説明します。

この記事では、Clockwork Recruiting と Microsoft Entra ID を統合する方法について説明します。 Clockwork Recruiting を Microsoft Entra ID と統合すると、次のことが可能になります。

- Clockwork Recruiting にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Clockwork Recruiting に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Clockwork Recruiting でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Clockwork Recruiting では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから Clockwork Recruiting を追加する

Clockwork Recruiting と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Clockwork Recruiting をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Clockwork Recruiting**」と入力します。
4. 結果パネルから **Clockwork Recruiting を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Clockwork Recruiting 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Clockwork Recruiting に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと、Clockwork Recruiting での関連ユーザーとの間にリンク関係を確立する必要があります。

Clockwork Recruiting 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Clockwork Recruiting の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Clockwork Recruiting のテストユーザーを作成** - Clockwork Recruiting で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の対応ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Clockwork Recruiting**&gt;**シングル サインオン** を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.clockworkrecruiting.com/sp`
    2. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.clockworkrecruiting.com/session/new`

        注

        これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Clockwork Recruiting クライアント サポート チーム](mailto:support@clockworkrecruiting.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Clockwork Recruiting の SSO の構成

**Clockwork Recruiting** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Clockwork Recruiting サポート チーム](mailto:support@clockworkrecruiting.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Clockwork Recruiting のテスト ユーザーの作成

このセクションでは、Clockwork Recruiting で Britta Simon というユーザーを作成します。 [Clockwork Recruiting サポート チーム](mailto:support@clockworkrecruiting.com)と協力して、Clockwork Recruiting プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択します。 ログイン フローを開始できる Clockwork Recruiting のサインオン URL にリダイレクトされます。
- Clockwork Recruiting のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Clockwork Recruiting] タイルを選択すると、Clockwork Recruiting のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloud-academy-sso-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に QA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloud-academy-sso-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-31
- Summary: Microsoft Entra ID から QA にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために QA と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [QA](https://www.qa.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- QA でユーザーを作成する
- アクセスが不要になった場合に QA のユーザーを削除する
- Microsoft Entra ID と QA の間でユーザー属性の同期を維持する
- QA への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloud-academy-sso-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AD 統合をアクティブ化して API キーを生成するための、社内の管理者ロールを持つ QA のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と QA の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように QA を構成する

1. [QA](https://www.qa.com) 管理ポータルにログインします。
2. プロファイル アイコンの横にあるホーム ページで **[ダッシュボード** ] を選択します。

    [Image: ホームのスクリーンショット。]
3. **プロファイル**&gt;**Settings > Integrations** に移動します。

    [Image: 統合のスクリーンショット。]
4. [ **統合** ] タブを選択し、[Microsoft Entra ID で **統合を表示** ] を選択します。

    [Image: ディレクトリのスクリーンショット。]
5. [ **新しい API キーの生成] を選択します**。

    [Image: [新しい API キーの生成] のスクリーンショット。]
6. 完全な API キーをコピーします。 この値は、QA アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    注

    必要に応じて、新しい API キーを生成できます。 古い API キーは、AD ポータルで構成を更新するために必要な時間を許可するために、次の **8 時間以内** に期限切れとしてマークされます。
7. テナント URL は `https://app.qa.com/webhooks/ad/v1/scim` です。 この値は、QA アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから QA を追加する

Microsoft Entra アプリケーション ギャラリーから QA を追加して、QA へのプロビジョニングの管理を開始します。 SSO 用に QA を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: QA への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で QA の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**にアクセスする

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **QA** を選択します。

    [Image: アプリケーションの一覧の QA リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、QA テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が QA に接続できることを確認します。 接続に失敗した場合は、QA アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. 「属性マッピング」セクションで、Microsoft Entra ID から QA に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で QA のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、QA API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 |  |
    | 活動中 | ブール値 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloud-academy-sso-tutorial"} -->
## Microsoft Entra ID で Cloud Academy for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloud-academy-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: この記事では、Microsoft Entra ID と Cloud Academy の間でシングル サインオンを構成する方法について説明します。

この記事では、Cloud Academy と Microsoft Entra ID を統合する方法について説明します。 Cloud Academy と Microsoft Entra ID を統合すると、次のことができます。

- Cloud Academy にアクセスできるユーザーを、Microsoft Entra ID を使って制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cloud Academy に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Cloud Academy サブスクリプション。

### 記事の説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cloud Academy では、 **SP** Initiated SSO がサポートされます。
- Cloud Academy では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- Cloud Academy では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloud-academy-sso-provisioning-tutorial)。

### ギャラリーから Cloud Academy を追加する

Microsoft Entra ID への Cloud Academy の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cloud Academy を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Cloud Academy**」と入力します。
4. 結果パネルで **Cloud Academy** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cloud Academy 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cloud Academy に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra のユーザーと Cloud Academy の対応するユーザーとの間にリンク関係を確立する必要があります。

Cloud Academy に対する Microsoft Entra SSO を構成してテストするには、次の大まかな手順を実行します。

1. ユーザーがこの機能を使用できるように **Microsoft Entra SSO を構成**します。
    1. **Microsoft Entra テスト ユーザーを作成** して、Microsoft Entra のシングル サインオンをテストします。
    2. **テスト ユーザーにアクセス権を付与** して、ユーザーが Microsoft Entra シングル サインオンを使用できるようにします。
2. アプリケーション側**で Cloud Academy のシングル サインオンを構成**します。
    1. **Cloud Academy のテスト ユーザー** を、Microsoft Entra のユーザー表現に対応するユーザーとして作成します。
3. **SSO をテスト** して、構成が機能することを確認します。

### Microsoft Entra SSO の構成

Azure portal で、次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. [**管理**] セクションで、[&gt;&gt;**Cloud Academy** アプリケーション統合ページに移動し、**シングル サインオン**を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆ボタンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するための鉛筆ボタンを示すスクリーンショット。]
5. [ **基本的な SAML 構成** ] セクションで、[ **識別子** ] テキスト ボックスを更新し、次の URL を入力して続行します。

    | 識別子 |
    | --- |
    | `urn:federation:cloudacademy` |
6. [ **基本的な SAML 構成** ] セクションで、[ **応答 URL** ] テキスト ボックスを更新し、次のいずれかの URL を入力して続行します。

    | [応答 URL] |
    | --- |
    | `https://cloudacademy.com/labs/social/complete/saml/` |
    | `https://app.qa.com/labs/social/complete/saml/` |
7. [ **基本的な SAML 構成]** セクションで、[ **サインオン URL** ] テキスト ボックスを更新し、次のいずれかの URL を入力して保存します。

    | [サインオン URL] |
    | --- |
    | `https://cloudacademy.com/login/enterprise/` |
    | `https://app.qa.com/login/enterprise/` |
8. **SAML 署名証明書**の鉛筆ボタンを選択して設定を編集します。

    [Image: 証明書を編集する方法を示すスクリーンショット。]
9. **PEM 証明書**をダウンロードします。

    [Image: P E M 証明書をダウンロードする方法を示すスクリーンショット。]
10. [ **Cloud Academy のセットアップ** ] セクションで、 **ログイン URL を**コピーします。

    [Image: ログイン U R L のコピー ボタンを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成**を選択します。

#### テスト ユーザーへのアクセス権の付与

このセクションでは、B. Simon に Cloud Academy へのアクセスを許可して、Azure シングル サインオンを使用できるようにします。

1. **Entra ID**&gt;**Enterprise アプリ**にアクセスします。
2. アプリケーションの一覧で [ **Cloud Academy**] を選択します。
3. アプリの概要ページの [ **管理** ] セクションで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザーの追加]** を選択し、[**割り当ての追加**] ダイアログ ボックスで [**ユーザーとグループ**] を選択します。
5. [**ユーザーとグループ**] ダイアログ ボックスで、[**ユーザー**] の一覧で **[B.Simon**] を選択し、画面の下部にある **[選択**] ボタンを選択します。
6. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
7. [ **割り当ての追加** ] ダイアログ ボックスで、[ **割り当て**] を選択します。

### Cloud Academy のシングル サインオンを構成する

1. 別のブラウザー ウィンドウで、Cloud Academy 企業サイトに管理者としてサインインします。
2. ホーム ページで、 **Azure Integration Team** アイコンを選択し、左側のメニューで **[設定]** を選択します。
3. [ **INTEGRATIONS** ] タブで、 **SSO** カードを選択します。

    [Image: [設定] と [統合] オプションを示すスクリーンショット。]
4. [ **構成の開始] を** 選択して SSO を設定します。
5. [ **全般設定]** ページで、次の手順を実行します。

    [Image: 一般的な設定での統合を示すスクリーンショット。]

    1. [ **SSO URL (場所)]** ボックスに、「 Microsoft Entra SSO の構成」の手順 9 で、コピーしたログイン URL の値を貼り付けます。
    2. ダウンロードした Base64 証明書をメモ帳で開きます。 その内容を **[証明書** ] ボックスに貼り付けます。
6. 次のページで以下の手順を実行します。

    [Image: 追加の設定の統合を示すスクリーンショット。]

    1. **[SAML 属性マッピング**] セクションで、必須フィールドにソース属性値を入力します。

        `http://schemas.microsoft.com/identity/claims/objectidentifier``http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname``http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname``http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`
    2. [ **セキュリティ設定]** セクションで、[ **署名された認証要求]** チェック ボックスをオンにして、この値を True に設定 **します**。
    3. [**追加の設定 (省略可能)]** セクションで、「**Microsoft Entra SSO の構成**」の手順 9 で、コピーしたログアウト URL 値を [ログアウト URL] ボックスに入力します。
7. [ **保存してテスト] を選択します**。
8. 次に、サービス プロバイダーの情報を示すダイアログが表示されます。 XML ファイルをダウンロードします。

    [Image: メタデータ構成ファイルのダウンロードを示すスクリーンショット。]
9. これで、サービス プロバイダーの XML ファイルが作成されたので、作成したアプリケーションに戻ります。 [ **シングル サインオン** ] セクションで、メタデータ ファイルをアップロードします。

    [Image: Azure アプリケーションでのメタデータのアップロードを示すスクリーンショット。]
10. サービス プロバイダーのメタデータを更新したので、ご利用の Cloud Academy 企業サイトの SSO パネルに戻り、テストそしてアクティブ化と進むことができます。 [サービス プロバイダー] ダイアログで、[ **続行**] を選択します。

    [Image: サービス プロバイダー ダイアログを示すスクリーンショット。]
11. **[TEST SSO connection]\(SSO 接続のテスト**\) を選択して、テスト フローを開始します。

    [Image: [Test S S O connection](S O 接続のテスト) ボタンを示すスクリーンショット。]

    注

    作成したテスト ユーザー アカウントを使用して Cloud Academy にサインインしている場合は、テストフローに進みます。 それ以外の場合は、ダイアログを閉じ **、[全般設定]** までスクロールし、サブドメインの URL をコピーしてプライベート ブラウザータブまたはシークレット ブラウザー タブに貼り付けてから、テスト ユーザーとしてサインインします。 サインインが成功した場合は、ブラウザー タブを閉じて、[ **保存してテスト]** を選択できます。 ブラウザー タブが再び開き、サービス プロバイダーのダイアログが表示されます。 **[続行**] を選択し、[**SSO 接続のテスト**] をもう一度選択します。 最後に、[ **テストが成功しました** ] を選択します。これは、プライベート タブまたはシークレット タブを使用してサインインを既にテストしているためです。

    次の手順に進みます。
12. サインインに成功した場合は、組織全体の SSO 統合をアクティブにすることができます。

    [Image: S S O のアクティブ化が成功したことを示すスクリーンショット。]

注

Cloud Academy を構成する方法の詳細については、「 [シングル サインオンの設定](https://support.cloudacademy.com/hc/articles/360043908452-Setting-Up-Single-Sign-On)」を参照してください。

#### Cloud Academy テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Cloud Academy に作成します。 Cloud Academy では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションでは、ユーザー側で必要な操作はありません。 Cloud Academy にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

Cloud Academy では、自動ユーザー プロビジョニングもサポートされています。 詳細については、 [Cloud Academy の SSO プロビジョニングに関する記事を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloud-academy-sso-provisioning-tutorial)。

### SSO のテスト

このセクションでは、次のいずれかのオプションを使って、Microsoft Entra SSO の構成をテストします。

- Azure portal で、[ **このアプリケーションをテスト**する] を選択します。 Cloud Academy のサインオン URL にリダイレクトされ、サインイン フローを開始することができます。
- Cloud Academy のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリ ポータルで [Cloud Academy] タイルを選択すると、このオプションは Cloud Academy のサインオン URL にリダイレクトされます。 マイ アプリ ポータルの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloud-attendance-management-system-king-of-time-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に CLOUD ATTENDANCE MANAGEMENT SYSTEM KING OF TIME を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloud-attendance-management-system-king-of-time-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID とクラウド勤怠管理システム KING OF TIME の間でシングル サインオンを構成する方法について説明します。

この記事では、CLOUD ATTENDANCE MANAGEMENT SYSTEM KING OF TIME と Microsoft Entra ID を統合する方法について説明します。 クラウド勤怠管理システム KING OF TIME は、勤怠管理システム市場でシェア第 1 位であり、2023 年 4 月時点でアクティブ ユーザーが 277 万人に到達しました。 これは、高い満足度、認識、およびNo.1の市場シェアを持つクラウドの出席管理システムです。 オフィスや店舗から緊急時のテレワークや在宅勤務まで。 紙のタイム カードや Excel による複雑なものとなっていた勤怠管理が、自動的に集計されます。 クラウド勤怠管理システム KING OF TIME を Microsoft Entra ID と統合すると、次のことが可能になります。

- クラウド勤怠管理システム KING OF TIME にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra ID アカウントを使用してクラウド勤怠管理システム KING OF TIME に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

クラウド勤怠管理システム KING OF TIME 用の Microsoft Entra ID シングル サインオンをテスト環境で構成してテストします。 クラウド勤怠管理システム KING OF TIME は、**SP** initiated シングル サインオンをサポートします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID をクラウド勤怠管理システム KING OF TIME と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- クラウド勤怠管理システム KING OF TIME のシングル サインオン (SSO) に対応したサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーからクラウド勤怠管理システム KING OF TIME アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーからクラウド勤怠管理システム KING OF TIME を追加する

Microsoft Entra アプリケーション ギャラリーからクラウド勤怠管理システム KING OF TIME を追加して、クラウド勤怠管理システム KING OF TIME でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[クラウド勤怠管理システム KING OF TIME]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `https://s3.ta.kingoftime.jp/saml/v2.0/acs` |
    | `https://s2.ta.kingoftime.jp/saml/v2.0/acs` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://s2.ta.kingoftime.jp/saml/v2.0/acs` |
    | `https://s3.ta.kingoftime.jp/saml/v2.0/acs` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://s2.ta.kingoftime.jp/admin` |
    | `https://s3.ta.kingoftime.jp/admin` |
    | `https://s2.ta.kingoftime.jp/independent/recorder2/personal` |
    | `https://s3.ta.kingoftime.jp/independent/recorder2/personal` |
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[クラウド勤怠管理システム KING OF TIME のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### クラウド勤怠管理システム KING OF TIME の SSO を構成する

**クラウド勤怠管理システム KING OF TIME** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[クラウド勤怠管理システム KING OF TIME のサポート チーム](https://www.kingoftime.jp/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### クラウド勤怠管理システム KING OF TIME のテスト ユーザーを作成する

このセクションでは、クラウド勤怠管理システム KING OF TIME で Britta Simon というユーザーを作成します。 [クラウド勤怠管理システム KING OF TIME のサポート チーム](https://www.kingoftime.jp/contact/)と協力して、クラウド勤怠管理システム KING OF TIME プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる CLOUD ATTENDANCE MANAGEMENT SYSTEM KING OF TIME のサインオン URL にリダイレクトされます。
- クラウド勤怠管理システム KING OF TIME のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [CLOUD ATTENDANCE MANAGEMENT SYSTEM KING OF TIME] タイルを選択すると、このオプションは CLOUD ATTENDANCE MANAGEMENT SYSTEM KING OF TIME のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloud-imanage-tutorial"} -->
## Microsoft Entra ID を使用して Cloud iManage のシングルサインオンを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloud-imanage-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-05
- Summary: Microsoft Entra ID と Cloud iManage の間でシングル サインオンを構成する方法について説明します。

この記事では、Cloud iManage と Microsoft Entra ID を統合する方法について説明します。 Cloud iManage と Microsoft Entra ID を統合すると、次のことができます。

- Cloud iManage にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cloud iManage に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cloud iManage でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cloud iManage では、**SP開始SSO とIDP開始SSO**の両方がサポートされます。

### ギャラリーから Cloud iManage を追加する

Microsoft Entra ID への Cloud iManage の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cloud iManage を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Cloud iManage**」と入力します。
4. 結果パネルから **[Cloud iManage** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cloud iManage の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Cloud iManage に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Cloud iManage の関連ユーザーとの間にリンク関係を確立する必要があります。

Cloud iManage で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cloud iManage SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cloud iManage テスト ユーザーの作成** - Cloud iManage で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Cloud iManage**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    エー。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cloudimanage.com/auth/api/v1/saml/login/<Customer_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cloudimanage.com/auth/api/v1/saml/login/<Customer_ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://cloudimanage.com`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Cloud iManage サポート チーム](mailto:cloudsupport@imanage.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Cloud iManage のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: Screenshot shows to Copy configuration URLs.]構成 URL のコピーを示しているスクリーンショットです。メタデータ の

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cloud iManage SSO の構成

**Cloud iManage** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Cloud iManage サポート チーム](mailto:cloudsupport@imanage.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。 詳細については、このドキュメントを参照してください [。](https://docs.imanage.com/cloud/cc-help/en-US/SAML_Single_Sign-On_%28SSO%29.html)

#### Cloud iManage テスト ユーザーの作成

このセクションでは、Cloud iManage で B.Simon というユーザーを作成します。 [Cloud iManage サポート チーム](mailto:cloudsupport@imanage.com)と協力して、Cloud iManage プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Cloud iManage のサインオン URL にリダイレクトします。
- Cloud iManage のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Cloud iManage に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Cloud iManage] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Cloud iManage に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloud-service-picco-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cloud Service PICCO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloud-service-picco-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cloud Service PICCO の間でシングル サインオンを構成する方法について説明します。

この記事では、Cloud Service PICCO と Microsoft Entra ID を統合する方法について説明します。 Cloud Service PICCO を Microsoft Entra ID と統合すると、次が可能になります。

- Cloud Service PICCO へのアクセス許可を持つ Microsoft Entra ID を制御する。
- ユーザーが Microsoft Entraアカウントを使用して Cloud Service PICCO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Cloud Service PICCO でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Cloud Service PICCO では、 **SP** Initiated SSO がサポートされます。
- クラウド サービス PICCO では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Cloud Service PICCO の追加

Microsoft Entra ID への Cloud Service PICCO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cloud Service PICCO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;に移動して、**新しいアプリケーション**を開きます。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「Cloud Service PICCO**」と入力します。
4. 結果パネルから **[Cloud Service PICCO** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cloud Service PICCO に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cloud Service PICCO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Cloud Service PICCO の関連ユーザーとの間にリンク関係を確立する必要があります。

Cloud Service PICCO を使用して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cloud Service PICCO SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cloud Service PICCO のテスト ユーザーを作成する - Microsoft Entra にリンクされた B.Simon に対応するユーザーを Cloud Service PICCO 内で作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Cloud Service PICCO**&gt;**シングル サインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `<SUB DOMAIN>.cloudservicepicco.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUB DOMAIN>.cloudservicepicco.com/app`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUB DOMAIN>.cloudservicepicco.com/app`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Cloud Service PICCO クライアント サポート チーム](mailto:picco.support@est.fujitsu.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cloud Service PICCO の SSO の構成

**Cloud Service PICCO** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Cloud Service PICCO サポート チーム](mailto:picco.support@est.fujitsu.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cloud Service PICCO テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Cloud Service PICCO に作成します。 Cloud Service PICCO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Cloud Service PICCO にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cloud Service PICCO のサインオン URL にリダイレクトされます。
- Cloud Service PICCO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [クラウド サービス PICCO] タイルを選択すると、このオプションは Cloud Service PICCO のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloudbees-ci-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に CloudBees CI を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloudbees-ci-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CloudBees CI 間のシングル サインオンを構成する方法について説明します。

この記事では、CloudBees CI と Microsoft Entra ID を統合する方法について説明します。 Jenkins ベースの安全でスケーラブルかつ柔軟な CI ソリューションである CloudBees CI で、管理を一元化し、コンプライアンスを確保し、大規模な自動化を実現します。 CloudBees CI を Microsoft Entra ID と統合すると、次のことが可能になります。

- CloudBees CI にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで CloudBees CI に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

CloudBees CI に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストする。 CloudBees CI は **SP** 開始のシングル サインオンのみをサポートしています。

### [前提条件]

Microsoft Entra ID を CloudBees CI と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- CloudBees CI でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから CloudBees CI アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから CloudBees CI を追加する

Microsoft Entra アプリケーション ギャラリーから CloudBees CI を追加して、CloudBees CI に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**CloudBees CI**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`<Customer_EntityID>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerDomain>/cjoc/securityRealm/finishLogin` |
    | `https://<CustomerDomain>/<Environment>/securityRealm/finishLogin` |
    | `https://cjoc.<CustomerDomain>/securityRealm/finishLogin` |
    | `https://<Environment>.<CustomerDomain>/securityRealm/finishLogin` |

    c. **[サインオン URL]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerDomain>/cjoc` |
    | `https://<CustomerDomain>/<Environment>` |
    | `https://cjoc.<CustomerDomain>` |
    | `https://<Environment>.<CustomerDomain>` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[CloudBees CI サポート チーム](mailto:support@cloudbees.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. CloudBees CI アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、CloudBees CI アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ユーザー名 | user.userprincipalname |
    | displayName | User.givenname |
    | groups | ユーザー.グループ |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[CloudBees CI のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### CloudBees CI SSO を構成する

CloudBees CI でシングル サインオンを構成するには、「[フェデレーション メタデータ XML とコピーした URL を使用して Azure を構成する](https://github.com/jenkinsci/saml-plugin/blob/main/doc/CONFIGURE_AZURE.md)」に従ってください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる CloudBees CI サインオン URL にリダイレクトされます。
- CloudBees CI のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで CloudBees CI タイルを選択すると、このオプションは CloudBees CI のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloudcords-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に CloudCords を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloudcords-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CloudCords の間のシングル サインオンを構成する方法について説明します。

この記事では、CloudCords と Microsoft Entra ID を統合する方法について説明します。 CloudCords を Microsoft Entra ID と統合すると、次のことが可能になります。

- CloudCords へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで CloudCords に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- CloudCords でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- CloudCords では、**SP開始SSO** および **IDP開始SSO** がサポートされます。

### ギャラリーからの CloudCords の追加

Microsoft Entra ID への CloudCords の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に CloudCords を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;から**新しいアプリケーション**を開きます。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「CloudCords**」と入力します。
4. 結果パネルから **CloudCords** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### CloudCords に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、CloudCords に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと CloudCords の関連ユーザーとの間にリンク関係を確立する必要があります。

CloudCords に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **CloudCords SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **CloudCords のテスト ユーザーの作成** - CloudCords で B.Simon に対応するユーザーを作成し、それを Microsoft Entra でのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[CloudCords]**&gt;**[シングル サインオン]**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] のスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cloudcords.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cloudcords.com/Saml/ProcessResponseFromIdentityProvider`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cloudcords.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、CloudCords クライアント サポート チーム](mailto:support@kiran.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
8. [ **CloudCords のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### CloudCords SSO の構成

**CloudCords** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [CloudCords サポート チーム](mailto:support@kiran.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### CloudCords テスト ユーザーの作成

このセクションでは、CloudCords で Britta Simon というユーザーを作成します。 [CloudCords サポート チーム](mailto:support@kiran.com)と協力して、CloudCords プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる CloudCords Identity Authentication のサインオン URL にリダイレクトされます。
- CloudCords Identity Authentication のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した CloudCords Identity Authentication に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [CloudCords Identity Authentication] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した CloudCords Identity Authentication に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloudmore-tutorial"} -->
## Microsoft Entra ID で Cloudmore for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloudmore-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cloudmore の間のシングル サインオンを構成する方法について説明します。

この記事では、Cloudmore と Microsoft Entra ID を統合する方法について説明します。 Cloudmore を Microsoft Entra ID と統合すると、次のことが可能になります。

- Cloudmore にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントを使って Cloudmore に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cloudmore でのシングル サインオン (SSO) が有効になったサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cloudmore では、**SP-initiated SSO** と **IDP-initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリー\*から Cloudmore を追加

Microsoft Entra ID への Cloudmore の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cloudmore を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業用アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Cloudmore**」と入力します。
4. 結果パネルから **Cloudmore** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cloudmore 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Cloudmore に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Cloudmore の関連ユーザーとの間にリンク関係を確立する必要があります。

Cloudmore を使って Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cloudmore の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Cloudmore テストユーザーの作成** - Cloudmore における B.Simon の対応ユーザーを作成し、それを Microsoft Entra 上のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cloudmore**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.cloudmore.com`
7. **[保存] を選択します**。
8. Cloudmore アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Cloudmore アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | テスト名 | user.companyname |
    | 郵便 | user.userprincipalname |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cloudmore SSO の構成

**Cloudmore** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Cloudmore サポート チーム](mailto:platformsupport@cloudmore.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cloudmore のテスト ユーザーの作成

このセクションでは、Cloudmore で B.Simon というユーザーを作成します。 [Cloudmore サポート チーム](mailto:platformsupport@cloudmore.com)と協力して、Cloudmore プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cloudmore のサインオン URL にリダイレクトされます。
- Cloudmore のサインオン URL\* に直接移動し、そこからログイン フロー\*を開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Cloudmore に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Cloudmore タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Cloudmore に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloudpassage-tutorial"} -->
## Microsoft Entra ID で CloudPassage for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloudpassage-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CloudPassage 間のシングル サインオンを構成する方法について説明します。

この記事では、CloudPassage と Microsoft Entra ID を統合する方法について説明します。 CloudPassage を Microsoft Entra ID と統合すると、次のことが可能になります。

- CloudPassage にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで CloudPassage に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- CloudPassage でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- CloudPassage では、**SP** によって開始される SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの CloudPassage の追加

Microsoft Entra ID への CloudPassage の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に CloudPassage を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**CloudPassage**」と入力します。
4. 結果のパネルから **CloudPassage** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### CloudPassage に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストする

**B.Simon** というテスト ユーザーを使用して、CloudPassage に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと CloudPassage の関連ユーザー間にリンク関係を確立する必要があります。

CloudPassage に対する Microsoft Entra SSO を構成・テストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **CloudPassage SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **CloudPassage テストユーザーの作成** - CloudPassage の B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**CloudPassage**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://portal.cloudpassage.com/saml/init/accountid`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://portal.cloudpassage.com/saml/consume/accountid`。 この属性の値を取得するには、CloudPassage ポータルの **[シングル サインオン設定]** セクションで **SSO セットアップのドキュメント**を選択します。

    [Image: このスクリーンショットは、[S S O Setup Documentation](S S O セットアップのドキュメント) リンクがコールアウトされた状態の CloudPassage ポータルを示しています。]

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 これらの値を取得するには、[CloudPassage クライアント サポート チーム](https://fidelissecurity.com/contact/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. CloudPassage アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、CloudPassage アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastname | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[CloudPassage のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### CloudPassage の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として CloudPassage 企業サイトにサインオンします。
2. 上部のメニューで、[ **設定]** を選択し、[ **サイトの管理**] を選択します。

    [Image: このスクリーンショットは、[Site Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サイトの管理) が選択された状態の CloudPassage サイトを示しています。]
3. [ **認証設定] タブを** 選択します。

    [Image: このスクリーンショットは、[Authentication Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証設定) タブが選択された状態の CloudPassage サイトを示しています。]
4. **[Single Sign-on Settings (シングル サインオンの設定)]** セクションで、次の手順に従います。

    [Image: このスクリーンショットは、[Single Sign-on Settings](シングル サインオンの設定) セクションを示しています。ここで、この手順の情報を入力します。]

    a. **[Enable Single sign-on(SSO)(SSO Setup Documentation)](シングル サインオン (SSO)(SSO セットアップ ドキュメント) を有効にする)** チェックボックスをオンにします。

    b。 **Microsoft Entra の識別子**を **[SAML 発行者 URL]** ボックスに貼り付けます。

    c. **ログイン URL** を **[SAML endpoint URL](SAML エンドポイント URL)** ボックスに貼り付けます。

    d. **ログアウト URL** を **[Logout landing page](ログアウト ランディング ページ)** ボックスに貼り付けます。

    e. ダウンロードした証明書をメモ帳で開き、ダウンロードした証明書の内容をクリップボードにコピーして、 **[x 509 certificate](x 509 証明書)** ボックスに貼り付けます。

    f. **保存** を選択します。

#### CloudPassage テスト ユーザーの作成

このセクションの目的は、CloudPassage で B.Simon というユーザーを作成することです。

**CloudPassage で B.Simon というユーザーを作成するには、以下の手順を実行します。**

1. **CloudPassage** 企業サイトに管理者としてサインオンします。
2. 上部のツール バーで、[ **設定]** を選択し、[ **サイトの管理**] を選択します。

    [Image: このスクリーンショットは、[Site Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サイトの管理) が選択された状態の CloudPassage を示しています。]
3. [ **ユーザー** ] タブを選択し、[ **新しいユーザーの追加]** を選択します。

    [Image: このスクリーンショットは、[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) タブと [Add New User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの追加) オプションが選択された状態の CloudPassage の [Site Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サイトの管理) を示しています。]
4. **[新しいユーザーの追加]** セクションで、次の手順を実行します。

    [Image: このスクリーンショットは、[Add New User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの追加) セクションを示しています。ここで、ユーザー情報を指定できます。]

    a. **[名]** ボックスに「Britta」と入力します。

    b。 **[姓]** ボックスに「Simon」と入力します。

    c. **[ユーザー名]**、**[Email]** 、**[メールアドレス再入力]** の各ボックスに、Britta の Microsoft Entra ID でのユーザー名を入力します。

    d. **[Access Type]** で **[Enable Halo Portal Access]** を選択します。

    e. [**] を選択し、[**] を追加します。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [CloudPassage] タイルを選択すると、SSO を設定した CloudPassage に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloudsign-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に CloudSign を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloudsign-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CloudSign の間のシングル サインオンを構成する方法について説明します。

この記事では、CloudSign と Microsoft Entra ID を統合する方法について説明します。 CloudSign を Microsoft Entra ID と統合すると、次のことが可能になります。

- CloudSign へのアクセス許可を持つ Microsoft Entra ID を管理する。
- ユーザーが Microsoft Entra アカウントを使って CloudSign に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な CloudSign サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- CloudSign では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの CloudSign の追加

Microsoft Entra ID への CloudSign の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に CloudSign を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「CloudSign**」と入力します。
4. 結果パネルから **CloudSign** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### CloudSign の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、CloudSign に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと CloudSign の関連ユーザーとの間にリンク関係を確立する必要があります。

CloudSign を使って Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **CloudSign SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **CloudSign テストユーザーの作成** - CloudSign において、Microsoft Entra における B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**CloudSign**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:amazon:cognito:sp:ap-northeast-1_<CUSTOM_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cloudsign-<CUSTOM_ID>.auth.ap-northeast-1.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.cloudsign.jp/login`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [CloudSign クライアント サポート チーム](mailto:contact@cloudsign.jp) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **CloudSign のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### CloudSign の SSO の構成

CloudSign 側でシングル サインオンを構成するには、 [CloudSign サポート ページ](https://help.cloudsign.jp/ja/articles/4000055)の指示に従います。

#### CloudSign のテスト ユーザーの作成

このセクションでは、CloudSign で B.Simon というユーザーを作成します。 [CloudSign サポート チーム](mailto:contact@cloudsign.jp)と協力して、CloudSign プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる CloudSign のサインオン URL にリダイレクトされます。
- CloudSign のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [CloudSign] タイルを選択すると、このオプションは CloudSign のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cloudtamer-io-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Kion (以前の cloudtamer.io) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cloudtamer-io-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Kion (旧 cloudtamer.io) 間のシングル サインオンを構成する方法について説明します。

この記事では、Kion と Microsoft Entra ID を統合する方法について説明します。 Kion を Microsoft Entra ID と統合すると、次のことが可能になります。

- Kion にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Kion に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Kion のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Kion では **IDP** 開始の SSO がサポートされています。
- Kion では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Kion (旧称 cloudtamer.io) の追加

Microsoft Entra ID への Kion の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Kion を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Kion**」と入力します。
4. 結果パネルから **[Kion]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Kion (旧 cloudtamer.io) に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Kion に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Kion の関連ユーザー間にリンク関係を確立する必要があります。

Kion に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Kion SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Kion のテスト ユーザーの作成** - Kion で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。
4. **グループ アサーション** - Microsoft Entra ID と Kion に対してグループ アサーションを設定します。

#### Kion SSO の構成を開始する

1. Kion の Web サイトに管理者としてログインします。
2. 右上隅 **+** プラスアイコンを選択し、 **IDMS** を選択します。

    [Image: IDMS の作成のスクリーンショット。]
3. IDMS の種類として **[SAML 2.0]** を選択します。
4. この画面を開いたままにして、ここに表示されている値を Microsoft Entra の構成にコピーします。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Kion]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** テキスト ボックスで、Kion からの **[SERVICE PROVIDER ISSUER (ENTITY ID)](サービス プロバイダー発行者 (エンティティ ID))** をこのボックスに貼り付けます。

    b。 **[応答 URL]** テキスト ボックスで、Kion からの **[SERVICE PROVIDER ACS URL](サービス プロバイダー ACS URL)** をこのボックスに貼り付けます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Kion のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Kion SSO を構成する

1. **[Add IDMS](IDMS の追加)** ページで、以下の手順を行います。

    [Image: IDMS の追加を示すスクリーンショット。]

    a. **[IDMS Name](IDMS 名)** に、ユーザーがログイン画面から認識する名前を指定します。

    b。 **[ID プロバイダー発行者 (エンティティ ID)]** テキスト ボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    c. ダウンロードした**フェデレーション メタデータ XML** をメモ帳で開き、その内容を **[ID プロバイダーのメタデータ]** テキスト ボックスに貼り付けます。

    d. **[サービス プロバイダー発行者 (エンティティ ID)]** の値をコピーし、この値を [基本的な SAML 構成] セクションの **[識別子]** テキスト ボックスに貼り付けます。

    e. **[サービス プロバイダー ACS URL]** の値をコピーし、その値を [基本的な SAML 構成] セクションにある **[応答 URL]** ボックスに貼り付けます。

    f. [Assertion Mapping](アサーション マッピング) で、次の値を入力します。

    | フィールド | 価値 |
    | --- | --- |
    | 名 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname` |
    | 姓 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname` |
    | Email | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name` |
    | ユーザー名 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name` |
2. [ **IDMS の作成] を選択します**。

#### Kion テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Kion に作成します。 Kion では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Kion にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Kion に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Kion] タイルを選択すると、SSO を設定した Kion に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。

### グループ アサーション

既存の Microsoft Entra グループを使用して Kion ユーザーのアクセス許可を簡単に管理するには、以下の手順を完了します。

#### Microsoft Entra の構成

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. 一覧で、Kion のエンタープライズ アプリケーションを選択します。
4. **[概要]** の左側のメニューで、 **[シングル サインオン]** を選択します。
5. **[シングル サインオン] で**、[**ユーザー属性と要求**] で [編集] を選択**します**。
6. [ **グループ要求の追加] を選択します**。
    注

    グループ要求は 1 つだけ保持できます。 このオプションが無効になっている場合は、グループ要求が既に定義されている可能性があります。
7. **[グループ要求]**に対して、要求で返される必要があるグループを選択します。
    - このエンタープライズ アプリケーションに Kion で使用するすべてのグループが常に割り当てられている場合は、[ **アプリケーションに割り当てられたグループ]** を選択します。
    - すべてのグループを表示する場合 (この選択によってグループ アサーションが大量に発生し、制限の対象になることがあります) は、 **[アプリケーションに割り当てられているグループ]** を選択します。
8. **[ソース属性]** については、既定の**グループ ID** をままにします。
9. **[グループ要求の名前をカスタマイズする]** チェック ボックスをオンにします。
10. **[名前]** に「**memberOf**」と入力します。
11. **[保存]** を選択して、Microsoft Entra ID での構成を完了します。

#### Kion の構成

1. Kion で、**[ユーザー]**&gt;**[ID 管理システム]** に移動します。
2. Microsoft Entra ID 用に作成した IDMS を選択します。
3. 概要ページで、 **[User Group Associations](ユーザー グループの関連付け)** タブを選択します。
4. 必要なユーザー グループ マッピングごとに、以下の手順を完了します。
    1. **[追加]**&gt;**[新規追加]** を選択します。
    2. 表示されるダイアログで、次の操作を行います。
        1. **[名前]** に「**memberOf**」と入力します。
        2. **[Regex]** には、照合するグループの (Microsoft Entra ID の) オブジェクト ID を入力します。
        3. **[User Group](ユーザー グループ)** では、 **[Regex]** 内のグループにマップする Kion 内部グループを選択します。
        4. **[Update on Login](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログイン時に更新)** チェック ボックスをオンにします。
    3. **[Add](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加)** を選択して、グループの関連付けを追加します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cmd-ctrl-base-camp-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に CMD + Ctrl Base Camp を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cmd-ctrl-base-camp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CMD+CTRL Base Camp の間でシングル サインオンを構成する方法について学習します。

この記事では、CMD+CTRL Base Camp を Microsoft Entra ID と統合する方法について学習します。 CMD + CTRL Base Camp は、ソフトウェア セキュリティのトレーニング コース、ラボ、サイバー レンジの手法を組み合わせて、魅力的で効果的な統合学習者エクスペリエンスを提供するユニークな学習プラットフォームです。 CMD+CTRL Base Camp を Microsoft Entra ID と統合すると、次のことができます。

- CMD+CTRL Base Camp にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って CMD+CTRL Base Camp に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で CMD+CTRL Base Camp 用の Microsoft Entra シングル サインオンを構成してテストします。 CMD + CTRL Base Camp では、 **SP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングがサポートされます。

### [前提条件]

Microsoft Entra ID を CMD + Ctrl Base Camp と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- CMD+CTRL Base Camp のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから CMD+CTRL Base Camp アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから CMD+CTRL Base Camp を追加する

Microsoft Entra アプリケーション ギャラリーから CMD+CTRL Base Camp を追加して、CMD+CTRL Base Camp でのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**CMD+CTRL Base Camp**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:cmdnctrl:<ConnectionName>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.cmdnctrl.net/login/callback?connection=<ConnectionName>`

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://login.cmdnctrl.net`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [CMD + Ctrl Base Camp クライアント サポート チーム](mailto:support@cmdnctrl.net) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **CMD + Ctrl Base Camp のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### CMD+CTRL Base Camp の SSO を構成する

**CMD + CTRL ベース キャンプ**側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [CMD + CTRL Base Camp サポート チーム](mailto:support@cmdnctrl.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### CMD+CTRL Base Camp のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを CMD+CTRL Base Camp に作成します。 CMD+CTRL Base Camp では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 CMD+CTRL Base Camp にユーザーがまだ存在していない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは CMD + Ctrl Base Camp のサインオン URL にリダイレクトされ、ログイン フローを開始できます。
- CMD+CTRL Base Camp のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで CMD + Ctrl Base Camp タイルを選択すると、このオプションは CMD + Ctrl ベース キャンプのサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cobalt-tutorial"} -->
## Microsoft Entra ID で Cobalt をシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cobalt-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cobalt の間のシングル サインオンを構成する方法について説明します。

この記事では、Cobalt と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Cobalt を統合すると、次のことができます。

- Cobalt にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Cobalt に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cobalt でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cobalt では、**SP** イニシエーテッド SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Cobalt を追加する

Microsoft Entra ID への Cobalt の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cobalt を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Cobalt**」と入力します。
4. 結果パネルから **Cobalt** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cobalt 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cobalt に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Cobalt の関連ユーザーとの間にリンク関係を確立する必要があります。

Cobalt の Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cobalt SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cobalt テスト ユーザーの作成** - B.Simon に対応するユーザーを Cobalt 上で作成し、それを Microsoft Entra における B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cobalt**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://brightside-prod-<INSTANCENAME>.cobaltdl.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、Cobalt クライアント サポート チーム](https://cobaltio.zendesk.com/hc/requests/new) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Cobalt アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Cobalt アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 郵便 | ユーザーのメールアドレス |
    | Othermail | ユーザー名: user.othermail |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Cobalt のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cobalt の SSO の構成

1. Cobalt の Web サイトに管理者としてログインします。
2. 左側のメニューで **[設定]** を選択します。
3. [ **ID とアクセス] を**選択し、[SAML 2.0 で **有効にする]** を選択します。

    [Image: [設定] ページのスクリーンショット]
4. [SAML 2.0] セクションで、次の手順を実行します。

    [Image: [構成] ページのスクリーンショット]

    1. **[IDP ISSUER URL**] ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    2. **[IDP ターゲット URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。
    3. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **IDP CERTIFICATE** テキストボックスに貼り付けます。
5. **[保存] を選択します**。

注

Cobalt 側で SSO を構成する方法の詳細については、 [この](https://cobaltio.zendesk.com/hc/articles/360058406992-Setting-up-SAML-for-Azure-AD) 記事に従ってください。

#### Cobalt テスト ユーザーの作成

1. Cobalt の Web サイトに管理者としてログインします。
2. **People -&gt; Organization** に移動し、[ユーザーの招待] を選択します。
3. 表示されるオーバーレイで、招待するユーザーのメール アドレスを指定します。 電子メールを入力し、[ **追加** ] を選択するか **、Enter キー**を押します。
4. 複数のメール アドレスを区切るには、コンマを使用します。
5. ユーザーごとに、ロール ( **メンバー** または所有者) を選択 **します**。
6. メンバーと所有者の両方が、組織のすべての資産と侵入テストにアクセスできます。
7. [ **招待** ] を選択して確定します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Cobalt のサインオン URL にリダイレクトされます。
- Cobalt のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cobalt] タイルを選択すると、このオプションは Cobalt のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/coda-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Coda を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/coda-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-31
- Summary: Microsoft Entra ID から Coda へのユーザー アカウントの自動プロビジョニングおよび自動プロビジョニング解除を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Coda と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Coda](https://coda.io/) に対してユーザーの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Coda でユーザーを作成する
- アクセスが不要になった場合に Coda のユーザーを削除する
- Microsoft Entra ID と Coda の間でユーザー属性の同期を維持する。
- Coda に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/coda-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- [Coda Enterprise](https://help.coda.io/en/articles/3530917-set-up-sso-for-your-org) 管理者アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Coda の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように Coda を構成する

1. ワークスペースの [...] メニューから [組織の設定] を選択して、[組織管理コンソール] を開きます。

    [Image: Coda Enterprise Organization SCIM 設定のスクリーンショット。]
2. [SCIM のプロビジョニング] が有効であることを確認します。
3. SCIM ベース URL と SCIM ベアラー トークンをメモします。 ベアラー トークンがない場合は、[新しいトークンの生成] を選択します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Coda を追加する

Microsoft Entra アプリケーション ギャラリーから Coda を追加して、Coda へのプロビジョニングの管理を開始します。 SSO のために Coda を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Coda への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Coda に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Coda]** を選択します。

    [Image: アプリケーションの一覧の [Coda] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [自動] オプションが強調表示された [プロビジョニング モード] ドロップダウン リストのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Coda テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Coda に接続できることを確認します。 接続に失敗した場合は、Coda アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続を示すスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Coda に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Coda のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Coda API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/coda-tutorial"} -->
## Microsoft Entra ID で Coda for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/coda-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Coda の間でシングル サインオンを構成する方法について説明します。

この記事では、Coda と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Coda を統合すると、次のことができます。

- Coda にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Coda に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- GDrive 統合が無効になっている Coda でのシングル サインオン (SSO) が有効なサブスクリプション (Enterprise)。 現在有効になっている場合は、 [Coda サポート チーム](mailto:support@coda.io) に連絡して、組織の GDrive 統合を無効にしてください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Coda では、**IDP** Initiated SSO がサポートされます。
- Coda では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Coda は、自動ユーザー プロビジョニング [に対応しています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/coda-provisioning-tutorial)。

### ギャラリーから Coda を追加する

Microsoft Entra ID への Coda の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Coda を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **ギャラリーから追加する** セクションで、検索ボックスに **Coda** と入力します。
4. 結果パネル **Coda** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Coda の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Coda に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Coda の関連ユーザーとの間にリンク関係を確立する必要があります。

Coda に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Coda SSO** の構成を開始する - Coda での SSO の構成を開始します。
2. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
3. **Coda SSO**の構成 - Coda でのシングル サインオン設定の構成を完了します。
    1. **Coda テストユーザーを作成** - Microsoft Entra のユーザープレゼンテーションにリンクしたB.Simonの対応ユーザーをCodaで作成します。
4. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Coda SSO の構成を開始する

開始するには、Coda の次の手順に従います。

1. Coda で、**組織の設定** パネルを開きます。

    [Image: 組織の設定を開く]
2. 組織で GDrive 統合がオフになっていることを確認します。 現在有効になっている場合は、 [Coda サポート チーム](mailto:support@coda.io) に連絡して GDrive から移行してください。

    [Image: GDrive無効]
3. [**SSO (SAML)**による認証] で、**[SAML** の構成] オプションを選択します。

    [Image: SAML 設定]
4. **エンティティ ID** と **SAML 応答 URL** の値をメモします。これは、後続の手順で必要になります。

    Azure[Image: Entity ID and SAML Response URL to use in Azure]Entity ID and SAML Response URL to use in Azureで使用するエンティティ ID と SAML レスポンス URL を指定するための

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Coda**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**SAML** でのシングル サインオンの設定] ページで、次の手順に従います。

    a. [**識別子** テキスト ボックスに、上の "エンティティ ID" を入力します。 次のパターンに従う必要があります: `https://coda.io/samlId/<CUSTOMID>`

    b。 [**応答 URL** テキスト ボックスに、上記の "SAML 応答 URL" を入力します。 次のパターンに従う必要があります: `https://coda.io/login/sso/saml/<CUSTOMID>/consume`

    手記

    値は上記とは異なります。Coda の [CONFIGURE SAML]\(SAML の構成\) コンソールで値を確認できます。 実際の識別子と応答 URL でこれらの値を更新します。
6. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Coda** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Coda SSO の構成

セットアップを完了するには、Coda の **[Saml の構成]** パネルで Microsoft Entra ID の値を入力します。

1. Coda で、**組織の設定** パネルを開きます。
2. [**SSO (SAML)**による認証] で、**[SAML** の構成] オプションを選択します。
3. **[SAML プロバイダー]** を **Microsoft Entra ID** に設定します。
4. **ID プロバイダーのログイン URL**に、Azure コンソールから **ログイン URL** を貼り付けます。
5. **ID プロバイダー発行者**で、Azure コンソールから **Microsoft Entra Identifier** を貼り付けます。
6. **ID プロバイダーのパブリック証明書**で、**[証明書** のアップロード] オプションを選択し、前にダウンロードした証明書ファイルを選択します。
7. **保存**を選択します。

これで、SAML SSO 接続のセットアップに必要な作業が完了します。

#### Coda テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Coda に作成します。 Coda では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Coda にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

Coda では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Coda に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Coda] タイルを選択すると、SSO を設定した Coda に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/code42-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Code42 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/code42-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-31
- Summary: ユーザー アカウントを Code42 に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Code42 と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Code42](https://www.code42.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Code42 でユーザーを作成する
- アクセスが不要になった場合に Code42 のユーザーを削除する
- Microsoft Entra ID と Code42 の間でユーザー属性の同期を維持する。
- Code42 でグループとグループ メンバーシップをプロビジョニングする
- Code42 に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/code42-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- ID 管理が有効になっている Code42 テナント。
- Customer Cloud 管理者アクセス許可を持つ Code42 ユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Code42 の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID によるプロビジョニングをサポートするように Code42 を構成する

このセクションでは、Code42 のコンソールの ID 管理セクションで、Microsoft Entra ID をプロビジョニング プロバイダーとして構成する手順について説明します。 これにより、Code42 で Microsoft Entra ID からのプロビジョニング要求を安全に受信できるようになります。

#### Code42 のコンソールでプロビジョニング プロバイダーを作成するには:

1. Code42 コンソールにサインインします。 **[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)** を選択して、ナビゲーション メニューを展開します。 **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** 、**[Identity Management](ID 管理)** の順に選択します。
2. **[プロビジョニング]** タブを選択します。次に、 **[Add provisioning provider](プロビジョニング プロバイダーの追加)** メニューを展開し、**[Add SCIM provider](SCIM プロバイダーの追加)** を選択します。
3. **[Display name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示名)** フィールドに、プロビジョニング プロバイダーの一意の名前を入力します。 **[Authentication credential type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証資格情報の種類)** を **[OAuth token](OAuth トークン)** に設定します。 **[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ)** を選択して資格情報を生成します。

注

- 次の手順で必要な **[Base URL](ベース URL)** と **[Token](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/トークン)** の入力を求められるまで、このウィンドウは開いたままにします。
- または、後で参照するために、この情報を一時的な場所にコピーします。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Code42 を追加する

Microsoft Entra アプリケーション ギャラリーから Code42 を追加して、Code42 へのプロビジョニングの管理を開始します。 SSO のために Code42 を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Code42 への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Code42 に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Code42]** を選択します。

    [Image: アプリケーションの一覧の Code42 リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Code42 テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Code42 に接続できることを確認します。 接続に失敗した場合は、Code42 アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。
10. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Code42 に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Code42 のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Code42 API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | タイトル | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |
    | externalId | 糸 |
    | ユーザータイプ | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |
13. **[グループ] を選択します**。
14. **[属性マッピング]** セクションで、Microsoft Entra ID から Code42 に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Code42 のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | externalId | 糸 |
    | members | リファレンス |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/code42-tutorial"} -->
## Microsoft Entra ID で Code42 for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/code42-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Code42 の間でシングル サインオンを構成する方法について説明します。

この記事では、Code42 と Microsoft Entra ID を統合する方法について説明します。 Code42 と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Code42 へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Code42 に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Code42 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Code42 では、**SP** Initiated SSO がサポートされます。
- Code42 では、自動ユーザー プロビジョニングとプロビジョニング解除がサポートされます (推奨)。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Code42 を追加する

Microsoft Entra ID への Code42 の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Code42 を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Code42**」と入力します。
4. 結果パネル **Code42** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Code42 の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Code42 に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Code42 の関連ユーザーとの間にリンク関係を確立する必要があります。

Code42 で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Code42 SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Code42 テストユーザーの作成** - B.Simon に対応する Code42 のユーザーを作成し、このユーザーを Microsoft Entra の表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Code42]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://www.crashplan.com/console` を入力します。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Code42 SSO の構成

Code42 **側** シングル サインオンを構成するには、**アプリフェデレーションメタデータURL** を Code42サポートチーム に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Code42 テスト ユーザーの作成

このセクションでは、Code42 で B.Simon というユーザーを作成します。 [Code42 サポート チームの](http://gethelp.code42.com/) と連携して、Code42 プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Code42 サインオン URL にリダイレクトされます。
- Code42 のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Code42] タイルを選択すると、このオプションは Code42 のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/codility-tutorial"} -->
## Microsoft Entra ID で Codility for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/codility-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Codility の間のシングル サインオンを構成する方法について説明します。

この記事では、Codility と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Codility を統合すると、次のことができます。

- Codility にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Codility に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Codility でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Codility では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Codility では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Codility の追加

Microsoft Entra ID への Codility の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Codility を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Codility**」と入力します。
4. 結果のパネルから **[Codility]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Codility 向け Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Codility に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Codility の関連ユーザーとの間にリンク関係を確立する必要があります。

Codility を使用して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Codility の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Codility のテスト ユーザーを作成する** - Codility で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Codility**&gt;**シングルサインオン**へ移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.codility.net/social/complete/saml/`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.codility.net`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.codility.net`

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<UNIQUE_IDENTIFIER>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL、識別子、サインオン URL、リレー状態でこれらの値を更新します。 この値を取得するには、[Codility クライアント サポート チーム](mailto:support@codility.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Codility のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Codility の SSO の構成

**Codility** 側でシングル サインオンを構成するには、ダウンロードした **フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Codility サポート チーム](mailto:support@codility.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Codility のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Codility に作成します。 Codility では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Codility にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Codility のサインオン URL にリダイレクトされます。
- Codility のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Codility に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Codility] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Codility に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cofense-provision-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Cofense Recipient Sync を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cofense-provision-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: ユーザー アカウントを Cofense Recipient Sync に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Cofense Recipient Sync と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [Cofense Recipient Sync](https://cofense.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Cofense Recipient Sync でユーザーを作成する
- アクセスが不要になった場合に Cofense Recipient Sync のユーザーを削除する
- Microsoft Entra ID と Cofense Recipient Sync 間でユーザー属性の同期を維持する
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cofense PhishMe の標準オペレーターのアカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Cofense Recipient Sync の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID を使用してプロビジョニングをサポートするように Cofense Recipient Sync を構成する

1. Cofense PhishMe にログインします。 **[受信者] &gt; [受信者の同期]** に移動します。
2. 使用条件に同意し、[ **はじめに**] を選択します。

    [Image: Recipient Sync tnc]
3. **[URL**] フィールドと [**トークン**] フィールドから値をコピーします。

    [Image: 受信者の同期]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Cofense Recipient Sync を追加する

Microsoft Entra アプリケーション ギャラリーから Cofense Recipient Sync を追加して、Cofense Recipient Sync へのプロビジョニングの管理を開始します。SSO 用に Cofense Recipient Sync を既に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Cofense Recipient Sync に対する自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーの割り当てに基づいて Microsoft Entra ID 内のユーザーを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Cofense Recipient Sync の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で [ **Cofense Recipient Sync**] を選択します。

    [Image: アプリケーションの一覧の Cofense のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. **[プロビジョニング モード] を** **[自動**] に設定します。

    [Image: [プロビジョニング] タブの [自動]]
6. [ **管理者資格情報** ] セクションで、手順 2. で前に取得した **SCIM 2.0 ベース URL と SCIM 認証トークン** の値を入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Cofense Recipient Sync に接続できることを確認します。接続に失敗した場合は、Cofense Recipient Sync アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: テナント URL トークン]
7. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーまたはグループのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: 通知メール]
8. **[保存] を選択します**。
9. [マッピング] セクション **で** 、[ **Microsoft Entra ユーザーを Cofense Recipient Sync に同期する**] を選択します。
10. [属性マッピング] セクションで、Microsoft Entra ID から Cofense Recipient Sync に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Cofense Recipient Sync のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | externalId | 糸 | ✓ |
    | ユーザー名 | 糸 |  |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | name.formatted | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.honorificSuffix | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | 電話番号[タイプ eq "home"].値 | 糸 |  |
    | phoneNumbers[種類 eq "その他"].value | 糸 |  |
    | 電話番号[タイプイコール "ポケベル"].値 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |
    | アドレス[タイプ eq "other"].フォーマット済み | 糸 |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |
    | タイトル | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | メール[タイプ eq "自宅"].値 | 糸 |  |
    | emails[タイプ eq "その他"].値 | 糸 |  |
    | 優先言語 | 糸 |  |
    | ニックネーム | 糸 |  |
    | ユーザータイプ | 糸 |  |
    | ロケール | 糸 |  |
    | タイムゾーン | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |
11. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
12. Cofense Recipient Sync に対して Microsoft Entra プロビジョニング サービスを有効にするには、[**設定]** セクションで **[プロビジョニングの状態]** を **[オン] に**変更します。

    [Image: プロビジョニングの状態を [オン] に切り替える]
13. **[設定]** セクションの **[スコープ**] で目的の値を選択して、Cofense Recipient Sync にプロビジョニングするユーザーやグループを定義します。

    [Image: プロビジョニング スコープ]
14. プロビジョニングの準備ができたら、[ **保存]** を選択します。

    [Image: プロビジョニング構成の保存]

この操作により、[**設定]** セクションの **[スコープ**] で定義されているすべてのユーザーとグループの初期同期サイクルが開始されます。 最初のサイクルは、Microsoft Entra プロビジョニング サービスが実行されている限り、約 40 分ごとに発生する後続のサイクルよりも実行に時間がかかります。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 変更ログ

- 2020 年 1 月 15 日 - objectId -&gt; externalId マッピングについて、"オブジェクトの作成中のみ" から "Always (常時)" への変更を実装しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/coggle-tutorial"} -->
## Microsoft Entra ID で Coggle for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/coggle-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Coggle の間のシングル サインオンを構成する方法について説明します。

この記事では、Coggle と Microsoft Entra ID を統合する方法について説明します。 Coggle を Microsoft Entra ID と統合すると、次のことができます。

- Coggle へのアクセス権を持つユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Coggle に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Coggle サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Coggle では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Coggle では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Coggle を追加する

Microsoft Entra ID への Coggle の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Coggle を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Coggle**」と入力します。
4. 結果パネルから **[Coggle** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Coggle 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Coggle に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Coggle の関連ユーザーとの間にリンク関係を確立する必要があります。

Coggle 用の Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Coggle SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Coggle テスト ユーザーの作成** - Coggle で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Coggle]**&gt;**[シングル サインオン]**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://coggle.it/<TENANT_NAME>/login`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには [、Coggle クライアント サポート チーム](mailto:hello@Coggle.it) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. **[保存] を選択します**。
8. Coggle アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Coggle アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. [ **Coggle のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Coggle の SSO の構成

1. 別のブラウザー ウィンドウで、Coggle 企業サイトに管理者としてサインオンします。
2. **[Coggle** アカウント] を選択し**、[マイ設定]** を選択します。

    [Image: [マイ設定] が選択されている Coggle 企業サイトを示すスクリーンショット。]
3. 次の **ロゴ** を選択し、[ **認証**] を選択します。

    [Image: ホエール アイコンと [認証] が選択されているスクリーンショット。]
4. [ **SAML 構成の編集] を選択します**。

    [Image: [SAML 構成の編集] オプションを含む [SAML 統合] ページを示すスクリーンショット。]
5. **[SAML 統合**] ダイアログ ページで、次の手順を実行します。

    [Image: この手順の情報を入力できる [SAML 統合] ページを示すスクリーンショット。]

    a. **Entrypoint (ID Provider SSO URL)** ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    b。 ダウンロードした **証明書 (Base64)** をメモ帳に開き、[ **証明書** ] テキストボックスに内容を貼り付けます。

    c. **[保存] を選択します**。

#### Coggle のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Coggle に作成します。 Coggle では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Coggle にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Coggle サインオン URL にリダイレクトされます。
- Coggle のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Coggle に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Coggle] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Coggle に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cognician-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cognician を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cognician-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cognician の間のシングル サインオンを構成する方法について説明します。

この記事では、Cognician と Microsoft Entra ID を統合する方法について説明します。 Cognician を Microsoft Entra ID と統合すると、次のことが可能になります。

- Cognician にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Cognician に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cognician でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cognician では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Cognician の追加

Cognician と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Cognician をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Cognician**」と入力します。
4. 結果パネルから **Cognician** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cognician 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cognician に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Cognician での関連ユーザーとの間にリンク関係を確立する必要があります。

Cognician 用の Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cognician SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cognician のテスト ユーザーを作成する** - Cognician で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cognician**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.cognician.com/saml-sso/<INSTANCE NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.cognician.com/saml-sso/<INSTANCE NAME>/saml`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 これらの値を取得するには、 [Cognician クライアント サポート チーム](mailto:support@cognician.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cognician の SSO の構成

**Cognician** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Cognician サポート チーム](mailto:support@cognician.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cognician のテスト ユーザーの作成

このセクションでは、Cognician で Britta Simon というユーザーを作成します。 [Cognician サポート チーム](mailto:support@cognician.com)と協力して、Cognician プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cognician のサインオン URL にリダイレクトされます。
- Cognician のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cognician] タイルを選択すると、このオプションは Cognician のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cognidox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cognidox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cognidox-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cognidox の間のシングル サインオンを構成する方法について説明します。

この記事では、Cognidox と Microsoft Entra ID を統合する方法について説明します。 Cognidox を Microsoft Entra ID と統合すると、次のことが可能になります。

- Cognidox にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Cognidox に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cognidox でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cognidox では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Cognidox では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Cognidox を追加する

Cognidox と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Cognidox をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Cognidox**」と入力します。
4. 結果ウィンドウで **[Cognidox]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cognidox 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cognidox 用の Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Cognidox での関連ユーザーとの間にリンク関係を確立する必要があります。

Cognidox 用の Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cognidox の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cognidox テストユーザーを作成する** - B.Simon に対応するユーザーを Microsoft Entra の表現にリンクさせ、Cognidox で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cognidox** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:net.cdox.<YOURCOMPANY>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOURCOMPANY>.cdox.net/auth/postResponse`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOURCOMPANY>.cdox.net/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Cognidox クライアント サポート チーム](mailto:support@cognidox.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Cognidox アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [編集] アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: 画像]
8. その他に、Cognidox アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [ユーザー属性] ダイアログの [ユーザー要求] セクションで、以下の手順を実行して、以下の表のように SAML トークン属性を追加します。

    | 名前 | Namespace | 変革 | パラメーター 1 |
    | --- | --- | --- | --- |
    | wanshort | http://appinux.com/windowsaccountname2 | メールプレフィックスを抽出() | user.userprincipalname |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **[名前空間]** ボックスに、その行に表示される名前空間を入力します。

    d. [ソース] として **[変換]** を選択します。

    e. **[変換]** の一覧に、その行に対して表示される値を入力します。

    f. **[パラメーター 1]** の一覧に、その行に対して表示される値を入力します。

    g. **保存** を選択します。
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Cognidox のセットアップ]** セクションで、要件に基づく適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cognidox SSO の構成

**Cognidox** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーションの構成からコピーした適切な URL を、[Cognidox サポート チーム](mailto:support@cognidox.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cognidox テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Cognidox に作成します。 Cognidox では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Cognidox にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Cognidox のサインオン URL にリダイレクトされます。
- Cognidox のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Cognidox に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Cognidox] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Cognidox に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cognism-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cognism を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cognism-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cognism の間でシングル サインオンを構成する方法について説明します。

この記事では、Cognism と Microsoft Entra ID を統合する方法について説明します。 Cognism と Microsoft Entra ID を統合すると、次のことができます。

- Cognism にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cognism に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cognism でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cognism では、 **IDP** によって開始される SSO のみがサポートされます。
- Cognism では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Cognism を追加する

Microsoft Entra ID への Cognism の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cognism を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Cognism**」と入力します。
4. 結果パネルから **[Cognism** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cognism の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Cognism に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Cognism の関連ユーザーとの間にリンク関係を確立する必要があります。

Cognism に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cognism SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cognism テストユーザーを作成し、Cognism 内で B.Simon に対応するユーザーを作成して、Microsoft Entra のユーザー表示にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cognism**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | 生産 | `https://app.cognism.com/<ID>` |
    | ステージング | `https://app-staging.cognism.com/<ID>` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | 生産 | `https://app.cognism.com/api/users/sso/azureSamlResponse/<ID>` |
    | ステージング | `https://app-staging.cognism.com/api/users/sso/azureSamlResponse/<ID>` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Cognism サポート チーム](mailto:help@cognism.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. [ **Cognism のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cognism SSO の構成

1. Cognism 企業サイトに管理者としてログインします。
2. **設定**&gt;**シングルサインオン**&gt;**Microsoft Azure** に移動して、[**構成**] を選択します。

    [Image: 構成の設定を示すスクリーンショット。]
3. **Microsoft Azure SSO 構成**セクションで、次の手順を実行します。

    [Image: スクリーンショットは、構成を示しています。]

    1. **識別子 (エンティティ ID) を**コピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの [**識別子 (エンティティ ID)]** ボックスに貼り付けます。
    2. **ACS URL を**コピーし、Microsoft Entra 管理センターの **[基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。
    3. **[エンティティ ID**] ボックスに、**Microsoft Entra** 管理センターからコピーした Microsoft Entra 識別子を貼り付けます。
    4. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[X.509 証明書]** テキストボックスに貼り付けます。
    5. **[有効化]** を選択します。

#### Cognism テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Cognism に作成します。 Cognism では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Cognism にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した Cognism に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cognism] タイルを選択すると、SSO を設定した Cognism に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/colab-tutorial"} -->
## Microsoft Entra ID で CoLab for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/colab-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CoLab の間のシングル サインオンを構成する方法について説明します。

この記事では、CoLab と Microsoft Entra ID を統合する方法について説明します。 CoLab を Microsoft Entra ID と統合すると、次のことが可能になります。

- CoLab にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで CoLab に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- CoLab でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- CoLab は、**SP および IDP** による SSO をサポートしています。
- CoLab では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの CoLab の追加

Microsoft Entra ID への CoLab の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に CoLab を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**を表示します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「CoLab**」と入力します。
4. 結果パネルから **[CoLab** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### CoLab の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、CoLab に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと CoLab の関連ユーザーとの間にリンク関係を確立する必要があります。

CoLab に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **CoLab SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **CoLab テストユーザーを作成する** - Microsoft Entra における B.Simon に対応するユーザーを CoLab で作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**CoLab**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:colab-production:<customer>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.colabsoftware.com/login/callback?connection=<Customer>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://app.colabsoftware.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [CoLab サポート チーム](mailto:support@colabsoftware.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **CoLab のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### CoLab SSO を構成する

**CoLab** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と Microsoft Entra 管理センターからコピーした適切な URL を [CoLab サポート チーム](mailto:support@colabsoftware.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### CoLab テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを CoLab に作成します。 CoLab では、Just-In-Time プロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ CoLab に存在しない場合は、CoLab にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる CoLab サインオン URL にリダイレクトします。
- CoLab のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した CoLab に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [CoLab] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した CoLab に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->
