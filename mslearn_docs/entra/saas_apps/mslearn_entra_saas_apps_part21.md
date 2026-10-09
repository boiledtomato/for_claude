# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 21)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 73

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/saphana-tutorial"} -->
## Microsoft Entra ID を使用して SAP HANA for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/saphana-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と SAP HANA 間にシングル サインオンを構成する方法について説明します。

この記事では、SAP HANA と Microsoft Entra ID を統合する方法について説明します。 SAP HANA を Microsoft Entra ID と統合すると、次のことができます。

- SAP HANA にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SAP HANA に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な SAP HANA サブスクリプション
- 任意のパブリック IaaS、オンプレミス、Azure VM、または Azure 内の SAP Large Instances で実行している HANA インスタンス
- XSA 管理 Web インターフェイス、および HANA インスタンスにインストールされている HANA Studio

注意

SAP HANA の運用環境を使用して、この記事の手順をテストすることはお勧めしません。 最初にアプリケーションの開発環境またはステージング環境で統合をテストし、そのあとに運用環境を使用してください。

この記事の手順をテストするには、次の推奨事項に従います。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、[ここで](https://azure.microsoft.com/pricing/free-trial/) 1 か月の試用版を入手できます
- SAP HANA でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SAP HANA では、 **IDP** Initiated SSO がサポートされます。
- SAP HANA では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの SAP HANA の追加

Microsoft Entra ID への SAP HANA の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAP HANA を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;で**エンタープライズ アプリ**&gt;を開き、**新しいアプリケーション**に進みます。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「SAP HANA**」と入力します。
4. 結果パネルから **SAP HANA** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAP HANA 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SAP HANA に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SAP HANA の関連ユーザーとの間にリンク関係を確立する必要があります。

SAP HANA に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAP HANA SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAP HANA テスト ユーザーの作成** - SAP HANA で Britta Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業用アプリケーション**&gt;**SAP HANA**&gt;**シングルサインオン**する。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer-SAP-instance-url>/sap/hana/xs/saml/login.xscfunc`

    注意

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 値を取得するには、 [SAP HANA クライアント サポート チーム](https://cloudplatform.sap.com/contact.html) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. SAP HANA アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションには、次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの 「 **ユーザー属性」** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: [編集] アイコンが選択されている [ユーザー属性] セクションを示すスクリーンショット。]
7. [ **ユーザー属性** と要求] ダイアログの [ **ユーザー属性** ] セクションで、次の手順を実行します。

    ある。 [ **編集] アイコン** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [編集] アイコンが選択されている [ユーザー属性と要求] ダイアログを示すスクリーンショット。]

    [Image: 画像]

    b。 **変換**の一覧から **ExtractMailPrefix()**を選択します。

    c. **パラメーター 1** の一覧から **user.mail** を選択します。

    d. **[保存] を選択します**。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAP HANA の SSO の構成

1. SAP HANA 側でシングル サインオンを構成するには、それぞれの HTTPS エンドポイントに移動して **HANA XSA Web コンソール** にサインインします。

    注意

    既定の構成では、この URL で要求はサインイン画面にリダイレクトされ、認証済みの SAP HANA データベース ユーザーの資格情報を要求されます。 サインインするユーザーには、SAML 管理タスクを実行するアクセス許可が必要です。
2. XSA Web インターフェイスで、SAML ID プロバイダー に移動します。 ここから画面の下部にある **+** ボタンを選択し、 **+** ウィンドウを表示します。 その後、次の手順を実行します。

    [Image: ID プロバイダーの追加]

    ある。 [ **ID プロバイダー情報の追加** ] ウィンドウで、メタデータ XML (ダウンロードした) の内容を [ **メタデータ** ] ボックスに貼り付けます。

    [Image: [メタデータ] ボックスと [名前] ボックスが強調表示されている [Add Identity Provider Info](ID プロバイダー情報の追加) ペインを示すスクリーンショット。]

    b。 XML ドキュメントの内容が有効な場合、解析プロセスは、[**全般] データ**画面領域の **[サブジェクト]、[エンティティ ID]、および [発行者**] フィールドに必要な情報を抽出します。 また、[ **宛先** ] 画面領域の URL フィールドに必要な情報 ([ **ベース URL] フィールドや [SingleSignOn URL (\*)** ] フィールドなど) も抽出されます。

    [Image: ID プロバイダーの設定を追加する]

    c. [**全般データ**] 画面領域の **[名前**] ボックスに、新しい SAML SSO ID プロバイダーの名前を入力します。

    注意

    SAML IDP の名前は必須です。また、一意にする必要があります。 SAP HANA XS アプリケーションで使用する認証方法として SAML を選択すると表示される、使用可能な SAML IDP の一覧に表示されます。 たとえば、XS アーティファクト管理ツールの **[認証** ] 画面領域でこれを行うことができます。
3. [ **保存]** を選択して SAML ID プロバイダーの詳細を保存し、新しい SAML IDP を既知の SAML IDP の一覧に追加します。

    [Image: [保存] ボタン]
4. HANA Studio の [ **構成** ] タブのシステム プロパティ内で、saml で設定をフィルター処理 **します**。 次に **、assertion\_timeout** を **10 秒** から **120 秒**に調整します。

    [Image: assertion_timeoutの設定]

#### SAP HANA のテスト ユーザーを作成する

SAP HANA への Microsoft Entra ユーザーのサインインを有効にするには、SAP HANA でプロビジョニングする必要があります。 SAP HANA では、 **Just-In-Time プロビジョニング**がサポートされています。この設定は既定で有効になっています。

ユーザーを手動で作成する必要がある場合は、次の手順を実行します。

注意

ユーザーが使う外部認証を変更することができます。 Kerberos などの外部システムを使用して認証できます。 外部 ID の詳細については、 [ドメイン管理者](https://cloudplatform.sap.com/contact.html)にお問い合わせください。

1. [SAP HANA Studio](https://help.sap.com/viewer/a2a49126a5c546a9864aae22c05c3d0e/2.0.01/en-us) を管理者として開き、SAML SSO の DB-User を有効にします。
2. **SAML** の左側にある非表示のチェック ボックスをオンにし、[**構成**] リンクを選択します。
3. [ **追加]** を選択して SAML IDP を追加します。 適切な SAML IDP を選択し、[ **OK]** を選択します。
4. **外部 ID** (この場合は BrittaSimon) を追加します。 次に、[ **OK] を選択します**。

    注意

    ユーザーの **外部 ID** フィールドを設定する必要があり、その値は Microsoft Entra ID の SAML トークンの **NameID** フィールドと一致する必要があります。 このオプションでは、現在 Microsoft Entra ID ではサポートされていない NameID フィールドに **SPProviderID** プロパティを送信する IDP が必要であるため、[**任意**] チェック ボックスをオンにしないでください。 詳細については、「 [SAML 2.0 を使用した単一 Sign-On](https://help.sap.com/viewer/b3ee5778bc2e4a089d3299b82ec762a7/2.0.05/en-US/db6db355bb571014b56eb25057daec5f.html)」を参照してください。
5. テスト目的で、すべての **XS** ロールをユーザーに割り当てます。

    [Image: ロールの割り当て]

    ヒント

    ユース ケースに適切なアクセス許可のみを付与する必要があります。
6. ユーザーを保存します。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SAP HANA に自動的にサインインします
- Microsoft マイ アプリを使用することができます。 マイ アプリで [SAP HANA] タイルを選択すると、SSO を設定した SAP HANA に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sapient-tutorial"} -->
## Microsoft Entra ID で Sapient for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sapient-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sapient の間にシングル サインオンを構成する方法について説明します。

この記事では、Sapient と Microsoft Entra ID を統合する方法について説明します。 Sapient と Microsoft Entra ID を統合すると、次のことができます。

- Sapient にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Sapient に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Sapient サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Sapient では、**SP** 開始 SSO がサポートされます
- Sapient では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Sapient の追加

Microsoft Entra ID への Sapient の統合を構成するには、ギャラリーから管理対象の SaaS アプリの一覧に Sapient を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Sapient**」と入力します。
4. 結果のパネルから **[Sapient]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sapient 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Sapient に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Sapient の関連ユーザーとの間にリンク関係を確立する必要があります。

Sapient に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sapient SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Sapient テスト ユーザーを作成 - Sapient** 内で B.Simon に対応するユーザーを作成し、それを Microsoft Entra の B.Simon とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sapient**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMERNAME>.app.sapient.industries`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Sapient クライアント サポート チーム](mailto:help@sapient.industries)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sapient の SSO の構成

**Sapient** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Sapient サポート チーム](mailto:help@sapient.industries)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Sapient のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Sapient に作成します。 Sapient では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Sapient にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Sapient のサインオン URL にリダイレクトされます。
- Sapient のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Sapient] タイルを選択すると、このオプションは Sapient のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sas-viya-sso-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SAS Viya SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sas-viya-sso-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Microsoft Entra ID から SAS Viya SSO にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SAS Viya SSO と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [SAS Viya SSO](https://www.sas.com/en_us/software/viya.html) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- SAS Viya SSO でユーザーを作成します。
- アクセスが不要になった場合は、SAS Viya SSO のユーザーを削除します。
- Microsoft Entra ID と SAS Viya SSO の間でユーザー属性の同期を維持します。
- SAS Viya SSO への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sas-viya-sso-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ SAS Viya SSO のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- [Microsoft Entra ID と SAS Viya SSO の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように SAS Viya SSO を構成する

Microsoft Entra ID を使用したプロビジョニングをサポートするように SAS Viya SSO を構成するには、「SAS Viya Platform Administration で **[SCIM を構成する方法](https://go.documentation.sas.com/doc/en/sasadmincdc/default/calids/n1rl3gjjjqmxmfn1hw9ebjjz5778.htm)** 」を参照してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから SAS Viya SSO を追加する

Microsoft Entra アプリケーション ギャラリーから SAS Viya SSO を追加して、SAS Viya SSO へのプロビジョニングの管理を開始します。 SSO 用に SAS Viya SSO を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: SAS Viya SSO への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて SAS Viya SSO でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で SAS Viya SSO の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **SAS Viya SSO**] を選択します。

    [Image: アプリケーションの一覧の [SAS Viya SSO] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: アプリケーション管理メニューの [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、SAS Viya SSO テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が SAS Viya SSO に接続できることを確認します。 接続に失敗した場合は、SAS Viya SSO アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    - Microsoft Entra プロビジョニング サービスを構成するには、テナントの URL が必要です。

        例：

        `https://sas.viya.example.com/identities/scim/v2`

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から SAS Viya SSO に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で SAS Viya SSO のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、SAS Viya SSO API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | SAS Viya SSO で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | addresses[type eq "work"].streetAddress | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | addresses[type eq "work"].region | 糸 |  |  |
    | addresses[type eq "work"].postalCode | 糸 |  |  |
    | addresses[type eq "work"].country | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | phoneNumbers[type eq "fax"].value | 糸 |  |  |
    | externalId | 糸 | ✓ | ✓ |
12. [属性マッピング] セクションで、Microsoft Entra ID から SAS Viya SSO に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で SAS Viya SSO のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | SAS Viya SSO で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  |  |
    | members | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sas-viya-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SAS Viya SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sas-viya-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAS Viya SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、SAS Viya SSO と Microsoft Entra ID を統合する方法について説明します。 SAS Viya SSO と Microsoft Entra ID を統合すると、次のことができます。

- SAS Viya SSO にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SAS Viya SSO に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAS Viya SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SAS Viya SSO では、**SPおよびIDP**によるSSOの両方をサポートします。
- SAS Viya SSO では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sas-viya-sso-provisioning-tutorial)。

### ギャラリーからの SAS Viya SSO の追加

Microsoft Entra ID への SAS Viya SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAS Viya SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「SAS Viya SSO**」と入力します。
4. 結果パネルから **SAS Viya SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAS Viya SSO の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SAS Viya SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SAS Viya SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

SAS Viya SSO で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAS Viya の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAS Viya SSO テスト ユーザーの作成** - Microsoft Entra のユーザーに対応する B.Simon の SAS Viya SSO 内の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SAS Viya SSO**&gt;**シングルサインオン**を参照する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、値を入力します。 `cloudfoundry-saml-login`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SAS_DOMAIN>.com/SASLogon/saml/SSO/alias/cloudfoundry-saml-login`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://miroda.unx.sas.com/SASLogon/home`

    注

    応答 URL は実際のものではありません。 実際の応答 URL で値を更新します。 この値を取得するには、 [SAS Viya SSO サポート チーム](mailto:support@sas.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAS Viya SSO の構成

Microsoft Entra ID でのシングル サインオンをサポートするように SAS Viya SSO を構成するには、SAS Viya Platform Administration の [この](https://go.documentation.sas.com/doc/en/sasadmincdc/default/calauthmdl/n1iyx40th7exrqn1ej8t12gfhm88.htm#n1mj93glryngkgn1e2mam2uy2dt8) リンクを参照してください。

#### SAS Viya SSO テスト ユーザーの作成

このセクションでは、SAS Viya SSO で B.Simon というユーザーを作成します。 [SAS Viya SSO サポート チーム](mailto:support@sas.com)と協力して、SAS Viya SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

SAS Viya SSO では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sas-viya-sso-provisioning-tutorial) 。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる SAS Viya SSO サインオン URL にリダイレクトします。
- SAS Viya SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した SAS Viya SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SAS Viya SSO] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SAS Viya SSO に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sauce-labs-tutorial"} -->
## Microsoft Entra ID で Sauce Labs for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sauce-labs-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sauce Labs の間のシングル サインオンを構成する方法について説明します。

この記事では、Sauce Labs を Microsoft Entra ID と統合する方法について説明します。 Sauce Labs でのシングル サインオンと自動アカウント プロビジョニングのアプリ統合。 Sauce Labs と Microsoft Entra ID を統合すると、次のことができます。

- Sauce Labs にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、Sauce Labs に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Microsoft Entra 向けの Azure AD のシングル サインオンを構成してテストします。 Sauce Labs は、**SP** Initiated と **IDP** Initiated の両方のシングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。 会社で、1 つの Azure テナント内に SAML SSO と統合する Sauce Labs の組織が複数ある場合は、次の[ドキュメント](https://docs.saucelabs.com/basics/sso/setting-up-sso-special-cases/#single-identity-provider-and-multiple-organizations-at-sauce-labs)を参照してください。

### [前提条件]

Microsoft Entra ID を Sauce Labs と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Sauce Labs のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Sauce Labs アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Sauce Labs を Microsoft Entra ギャラリーから追加する

Microsoft Entra アプリケーション ギャラリーから Sauce Labs を追加して、Sauce Labs とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Sauce Labs]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure に事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://accounts.saucelabs.com/`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Sauce Labs のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Sauce Labs SSO を構成する

Sauce Labs 側でシングル サインオンを構成するには、こちらの[ドキュメント](https://docs.saucelabs.com/basics/sso/setting-up-sso/#integrating-with-sauce-labs-service-provider)を参照して、両方の側で SAML SSO 接続を正しく設定してください。 ヘルプやクエリがある場合は、[Sauce Labs のサポート チーム](mailto:support@saucelabs.com)にお問い合わせください。

#### Sauce Labs テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Sauce Labs に作成します。 Sauce Labs では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Sauce Labs にユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Sauce Labs のサインオン URL にリダイレクトされます。
- Sauce Labs のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Sauce Labs に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Sauce Labs] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Sauce Labs に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/saucelabs-mobileandwebtesting-tutorial"} -->
## Sauce Labs - Mobile and Web Testing を Microsoft Entra ID でシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/saucelabs-mobileandwebtesting-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sauce Labs - Mobile and Web Testing の間でシングル サインオンを構成する方法について学習します。

この記事では、Sauce Labs - Mobile and Web Testing と Microsoft Entra ID を統合する方法について説明します。 Sauce Labs - Mobile and Web Testing を Microsoft Entra ID と統合すると、次のことができます。

- Sauce Labs - Mobile and Web Testing にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Sauce Labs - Mobile and Web Testing に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sauce Labs - Mobile and Web Testing でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Sauce Labs - Mobile and Web Testing では、 **IDP** Initiated SSO がサポートされます。
- Sauce Labs - Mobile and Web Testing では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Sauce Labs - Mobile and Web Testing を追加する

Microsoft Entra ID への Sauce Labs - Mobile and Web Testing の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Sauce Labs - Mobile and Web Testing を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Sauce Labs - Mobile and Web Testing」と**入力します。
4. 結果パネルから **Sauce Labs - Mobile and Web Testing** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sauce Labs - Mobile and Web Testing 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Sauce Labs - Mobile and Web Testing に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Sauce Labs - Mobile and Web Testing の関連ユーザーとの間にリンク関係を確立する必要があります。

Sauce Labs - Mobile and Web Testing で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sauce Labs - Mobile and Web Testing SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Sauce Labs - Mobile and Web Testing テスト ユーザーの作成** - Sauce Labs - Mobile and Web Testing で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Sauce Labs - Mobile and Web Testing**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Sauce Labs - Mobile and Web Testing のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sauce Labs - Mobile and Web Testing の SSO の構成

1. Web ブラウザーの別のウィンドウで、Sauce Labs - Mobile and Web Testing 企業サイトに管理者としてサインインします。
2. [ **アカウント** ] ドロップダウンを選択し、[ **チーム管理** ] タブを選択します。

    [Image: [アカウント] ドロップダウンと [チーム管理] ドロップダウン 項目が選択されていることを示すスクリーンショット。]
3. **組織の設定** で **設定の表示** を選択します。

    [Image: [組織の設定] ボックスの [設定の表示] ボタンを示すスクリーンショット。]
4. [ **シングル サインオン** ] タブを選択します。

    [Image: [組織の設定] で [単一 Sign-On] タブが選択されていることを示すスクリーンショット。]
5. [ **シングル サインオン** ] セクションで、次の手順を実行します。

    [Image: 「単一 Sign-On」タブでオプションを選択する様子を示したスクリーンショット。]

    1. 一意識別子文字列 (UIS) を定義し、**[保存]** を選択します。
    2. [ **新しいメタデータ ファイルのアップロード** ] を選択し、Microsoft Entra ID からダウンロードしたメタデータ ファイルをアップロードします。
    3. [ **シングル サインオンを有効にする] で**、[ **有効]** を選択します。

#### Sauce Labs - Mobile and Web Testing テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Sauce Labs - Mobile and Web Testing に作成します。 Sauce Labs - Mobile and Web Testing では、Just-In-Time ユーザー プロビジョニングがサポートされています。これ常に有効になっています。 このセクションにはアクション項目はありません。 Sauce Labs - Mobile and Web Testing にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Sauce Labs - Mobile and Web Testing サポート チーム](mailto:support@saucelabs.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Sauce Labs - Mobile and Web Testing に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Sauce Labs - Mobile and Web Testing タイルを選択すると、SSO を設定した Sauce Labs - Mobile and Web Testing に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/saviynt-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Saviynt を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/saviynt-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Saviynt の間にシングル サインオンを構成する方法について学習します。

この記事では、Saviynt と Microsoft Entra ID を統合する方法について説明します。 Saviynt と Microsoft Entra ID を統合すると、次のことができます。

- Saviynt にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Saviynt に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Saviynt サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Saviynt では、**サービスプロバイダー(SP)開始型シングルサインオン**および**アイデンティティープロバイダー(IDP)開始型シングルサインオン**をサポートします。
- Saviynt では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Saviynt を追加する

Microsoft Entra ID への Saviynt の統合を構成するには、ギャラリーから管理対象の SaaS アプリのリストに Saviynt を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Saviynt**」と入力します。
4. 結果パネルから **[Saviynt** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Saviynt 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Saviynt に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Saviynt の関連ユーザーとの間にリンク関係を確立する必要があります。

Saviynt に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Saviynt SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Saviynt のテストユーザー作成** - Microsoft Entra 上のユーザー B.Simon に対応するユーザーを Saviynt に作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Saviynt**&gt;**シングルサインオン**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `Saviynt-<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.saviyntcloud.com/ECM/saml/SSO/alias/<SAVIYNT_ID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.saviyntcloud.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Saviynt クライアント サポート チーム](mailto:support@saviynt.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Saviynt のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Saviynt の SSO を構成する

**Saviynt** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Saviynt サポート チーム](mailto:support@saviynt.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Saviynt テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Saviynt に作成します。 Saviynt では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Saviynt にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Saviynt のサインオン URL にリダイレクトされます。
- Saviynt のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Saviynt に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 MyApps で [Saviynt] タイルを選択すると、SSO を設定した Saviynt に自動的にサインインします。 MyApps の詳細については、「 [MyApps の概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/scalex-enterprise-tutorial"} -->
## Microsoft Entra ID を使用して、ScaleX Enterprise のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/scalex-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ScaleX Enterprise 間にシングル サインオンを構成する方法について説明します。

この記事では、ScaleX Enterprise と Microsoft Entra ID を統合する方法について説明します。 ScaleX Enterprise を Microsoft Entra ID と統合すると、次のことができます。

- ScaleX Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ScaleX Enterprise に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ScaleX Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ScaleX Enterprise では、**SP および IDP** Initiated SSO がサポートされます。

### ギャラリーから ScaleX Enterprise を追加する

Microsoft Entra ID への ScaleX Enterprise の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ScaleX Enterprise を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ScaleX Enterprise**」と入力します。
4. 結果のパネルから **[ScaleX Enterprise]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ScaleX Enterprise 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、ScaleX Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ScaleX Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

ScaleX Enterprise に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ScaleX Enterprise の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ScaleX Enterprise のテストユーザーを作成** - ScaleX Enterprise の B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra での B.Simon の表象にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ScaleX Enterprise**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://platform.rescale.com/saml2/<company id>/` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://platform.rescale.com/saml2/<company id>/acs/` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://platform.rescale.com/saml2/<company id>/sso/` という形式で URL を入力します。

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[ScaleX Enterprise クライアント サポート チーム](https://about.rescale.com/contactus.html)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. ScaleX Enterprise アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**emailaddress** は **user.mail** にマップされています。 ScaleX Enterprise アプリケーションでは **、emailaddress** が **user.userprincipalname** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
9. **[ScaleX Enterprise のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ScaleX Enterprise の SSO の構成

1. 別の Web ブラウザー ウィンドウで、ScaleX Enterprise 企業サイトに管理者としてサインインします
2. 右上のメニューを選択し、[ **Contoso 管理**] を選択します。

    注意

    Contoso は一例です。 これは、実際の会社名でなければなりません。

    [Image: 右上のメニューで選択された会社名の例を示すスクリーンショット。]
3. 上部メニューから **[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)** を選択し、 **[single sign-on](シングル サインオン)** を選択します。

    [Image: ドロップダウン メニューで選択されている [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)、[Single Sign-On](シングル サインオン) を示すスクリーンショット。]
4. 次のようにフォームの操作を実行します。

    [Image: シングル サインオンの構成]

    ある。 **[SSO で認証できるユーザーを作成する]** を選択します。

    b。 **[サービス プロバイダー SAML]** : 値 **urn:oasis:names:tc:SAML:2.0:nameid-format:persistent** を貼り付けます。

    c. **[ACS 応答での ID プロバイダーの電子メール フィールドの名前]** : 値 `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress` を貼り付けます。

    ｄ。 **Identity Provider EntityDescriptor Entity ID (ID プロバイダーの EntityDescriptor エンティティ ID):** コピーした **Microsoft Entra 識別子**の値を貼り付けます。

    え **Identity Provider SingleSignOnService URL (ID プロバイダーの SingleSignOnService URL):** **ログイン URL** を貼り付けます。

    f. **[Identity Provider public X509 certificate](ID プロバイダーのパブリック X509 証明書):** Azure からダウンロードした X509 証明書をメモ帳で開いて、このボックスに貼り付けます。 証明書の内容の途中に改行が含まれていないことを確認します。

    ジー 次のチェックボックスをオンにします: **[Enabled](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/有効化)、[Encrypt NameID](暗号 NameID)、[Sign AuthnRequests](AuthnRequests に署名する)** 。

    h. [ **SSO 設定の更新] を** 選択して設定を保存します。

#### ScaleX Enterprise のテスト ユーザーの作成

ScaleX Enterprise への Microsoft Entra ユーザーのサインインを有効にするには、ScaleX Enterprise でプロビジョニングする必要があります。 ScaleX Enterprise の場合、プロビジョニングは自動化されたタスクであり、手動の手順は必要ありません。 SSO 資格情報を使用して正常に認証できるユーザーは、ScaleX 側で自動的にプロビジョニングされます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる ScaleX Enterprise サインオン URL にリダイレクトされます。
- ScaleX Enterprise のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ScaleX Enterprise に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ScaleX Enterprise] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ScaleX Enterprise に自動的にサインインされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/scclifecycle-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SCC LifeCycle を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/scclifecycle-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SCC LifeCycle 間のシングル サインオンを構成する方法について説明します。

この記事では、SCC LifeCycle と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と LifeCycle を統合すると、次のことができます。

- LifeCycle にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで SCC LifeCycle に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SCC LifeCycle でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SCC LifeCycle では、**SP** Initiated SSO がサポートされます。

### ギャラリーから SCC LifeCycle を追加する

Microsoft Entra ID への SCC LifeCycle の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SCC LifeCycle を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SCC LifeCycle**」と入力します。
4. 結果のパネルから **[SCC LifeCycle]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SCC LifeCycle の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SCC LifeCycle に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SCC LifeCycle の関連ユーザー間にリンク関係を確立する必要があります。

SCC LifeCycle に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SCC LifeCycle SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SCC LifeCycle のテストユーザーを作成し、SCC LifeCycle において B.Simon のカウンターパートにします。** - そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[SCC LifeCycle]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://bs1.scc.com/<entity>` |
    | `https://lifecycle.scc.com/<entity>` |

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<sub-domain>.scc.com/ic7/welcome/customer/PICTtest.aspx`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[SCC LifeCycle クライアント サポート チーム](mailto:lifecycle.support@scc.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SCC LifeCycle のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SCC LifeCycle SSO の構成

**SCC LifeCycle** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML** とアプリケーション構成からコピーした適切な URL を [SCC LifeCycle サポート チーム](mailto:lifecycle.support@scc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

注

[SCC LifeCycle サポート チーム](mailto:lifecycle.support@scc.com)がシングル サインオンを有効にする必要があります。

#### SCC LifeCycle テスト ユーザーの作成

Microsoft Entra ユーザーが SCC LifeCycle にログインできるようにするには、ユーザーを SCC LifeCycle にプロビジョニングする必要があります。 SCC LifeCycle へのユーザー プロビジョニングを構成するためのアクション項目はありません。

割り当て済みユーザーが SCC LifeCycle にログインしようとすると、必要に応じて SCC LifeCycle アカウントが自動的に作成されます。

注

Microsoft Entra アカウント所有者が電子メールを受信し、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SCC LifeCycle のサインオン URL にリダイレクトされます。
- SCC LifeCycle のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SCC LifeCycle] タイルを選択すると、このオプションは SCC LifeCycle のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/schoolstream-asa-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SchoolStream ASA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/schoolstream-asa-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から SchoolStream ASA へのユーザー アカウントの自動プロビジョニングおよび自動プロビジョニング解除を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SchoolStream ASA と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[SchoolStream ASA](https://www.ssk12.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- SchoolStream ASA でユーザーを作成する
- アクセスが不要になった場合は、SchoolStream ASA のユーザーを削除します。
- Microsoft Entra ID と SchoolStream ASA 間でユーザー属性の同期を維持する
- SchoolStream ASA でグループとグループ メンバーシップをプロビジョニングする。
- SchoolStream ASA への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SchoolStream Web サイト。 お持ちでない場合は [、SchoolStream サポート](mailto:support@rtresponse.com) にお問い合わせください。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と SchoolStream ASA の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように SchoolStream ASA を構成する

1. SchoolStream ASA の統合を要求するには、 [SchoolStream サポート](mailto:support@rtresponse.com) に問い合わせてください。 **Microsoft Entra テナント ID** と **SchoolStream Web サイトの URL を**指定する必要があります。
2. SchoolStream が SchoolStream Web サイトと Microsoft Entra テナント ID をマップした後、 **シークレット トークン** と SchoolStream ASA テナント **URL を** 取得します。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから SchoolStream ASA を追加する

Microsoft Entra ID で SchoolStream ASA へのプロビジョニングの管理を始めるには、Microsoft Entra アプリケーション ギャラリーから SchoolStream ASA を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[Microsoft Entra ギャラリーの参照]** セクションで、検索ボックスに「**SchoolStream ASA**」と入力します。
4. 結果のパネルから **[SchoolStream ASA]** を選択し、**アプリにサインアップ**します。 お使いのテナントにアプリが追加されるのを数秒待機します。

SSO のために以前 SchoolStream ASA を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: SchoolStream ASA への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて SchoolStream ASA 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で SchoolStream ASA の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で、 **[SchoolStream ASA]** を選択します。

    [Image: アプリケーションの一覧の [SchoolStream ASA] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブ]
5. 初めてプロビジョニングを構成する場合は、[ **開始**] を選択します。
6. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
7. [ **テナント URL** ] フィールドに、SchoolStream ASA テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が SchoolStream ASA に接続できることを確認します。 接続に失敗した場合は、SchoolStream ASA アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. **[マッピング]** セクションで、**[Microsoft Entra ユーザーのプロビジョニング]** を選択します。
12. 下部の **[新しいマッピングの追加]** を選択します。
13. ダイアログ **[属性の編集]** で、次のようにします。

    - **[マッピングの種類]** フィールドで、ドロップダウンから **[ダイレクト]** を選択します。
    - **[基になる属性]** フィールドで、ドロップダウンから **[extensionAttribute1]** を選択します。
    - フィールド **[null の場合の既定値 (オプション)]** に **Microsoft Entra のテナント ID** を入力します。
    - **[対象の属性]** フィールドで、ドロップダウンから **[urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization]** を選択します。
    - **[この属性を使用してオブジェクトを照合]** フィールドで、ドロップダウンから **[いいえ]** を選択します。
    - **[Apply this mapping](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/このマッピングを適用する)** フィールドで、ドロップダウンから **[常に]** を選択します。
    - **OK** を選択します。

        [Image: 属性の編集]
14. **[属性マッピング]** セクションで、Microsoft Entra ID から SchoolStream ASA に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で SchoolStream ASA のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、SchoolStream ASA API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | name.formatted | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |
15. **[グループ]** を選びます。
16. [属性マッピング] セクションで、Microsoft Entra ID から SchoolStream ASA に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で SchoolStream ASA のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | 関連項目 |  |
    | externalId | 糸 |  |
17. **[保存]** ボタンをクリックして変更をコミットします。 **[アプリケーション]** タブに戻り、 **[Edit provisioning](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロビジョニングの編集)** を選択して続行できます。
18. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
19. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
20. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### ログの変更

- 2020 年 9 月 24 日 - グループ プロビジョニングが有効になりました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/schoox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Schoox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/schoox-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Schoox の間でシングル サインオンを構成する方法について説明します。

この記事では、Schoox と Microsoft Entra ID を統合する方法について説明します。 Schoox を Microsoft Entra ID を統合すると、次のことができます。

- Schoox にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Schoox に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Schoox でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Schoox では、**SP と IDP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Schoox の追加

Microsoft Entra ID への Schoox の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Schoox を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Schoox**」と入力します。
4. 結果のパネルから **[Schoox]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Schoox 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Schoox に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Schoox の関連ユーザーとの間にリンク関係を確立する必要があります。

Schoox に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Schoox SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Schoox テスト ユーザーの作成** - Microsoft Entra におけるユーザーの表現とリンクされた B.Simon の対応者として、Schoox 内にテストユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Schoox**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    **[識別子]** ボックスに、`https://saml.schoox.com/saml/adfsmetadata` という URL を入力します。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://saml.schoox.com/saml/login?idpUrl=<entityID>` という形式で URL を入力します。

    注意

    `<entityID>` は、後の記事で説明するクイック リファレンス セクションからコピーされた SAML エンティティ ID です。
7. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[Schoox のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Schoox SSO の構成

**Schoox** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Schoox サポート チーム](https://www.schoox.com/contact-us)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Schoox のテスト ユーザーの作成

このセクションでは、Schoox で Britta Simon というユーザーを作成します。 [Schoox サポート チーム](https://www.schoox.com/contact-us)と連携し、Schoox プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Schoox サインオン URL にリダイレクトされます。
- Schoox のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Schoox に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Schoox] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Schoox に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sciforma-tutorial"} -->
## Microsoft Entra ID で Sciforma for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sciforma-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sciforma の間にシングル サインオンを構成する方法について説明します。

この記事では、Sciforma と Microsoft Entra ID を統合する方法について説明します。 Sciforma を Microsoft Entra ID と統合すると、次のことができます。

- Sciforma にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Sciforma に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sciforma でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Sciforma では、**SP** Initiated SSO がサポートされます。
- Sciforma では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Sciforma の追加

Microsoft Entra ID への Sciforma の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Sciforma を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Sciforma**」と入力します。
4. 結果のパネルから **[Sciforma]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sciforma 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Sciforma に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Sciforma の関連ユーザーとの間にリンク関係を確立する必要があります。

Sciforma に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sciforma の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Sciforma のテストユーザーを作成** - Sciforma で B.Simon に対応するユーザーを作成し、そのユーザーが Microsoft Entra のユーザー表現にリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sciforma**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.sciforma.net/sciforma`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.sciforma.net/sciforma/saml/post`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.sciforma.net/sciforma/main.html`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、Sciforma クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Sciforma のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sciforma SSO を設定する

**Sciforma** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を Sciforma サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Sciforma のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Sciforma に作成します。 Sciforma では、Just-In-Time ユーザー プロビジョニングがサポートされます。この設定は既定で有効です。 このセクションにはアクション項目はありません。 Sciforma にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Sciforma のサインオン URL にリダイレクトされます。
- Sciforma のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Sciforma] タイルを選択すると、このオプションは Sciforma のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/scilife-azure-ad-sso-tutorial"} -->
## Microsoft Entra IDとのシングルサインオンのためにScilife Microsoft Entra SSOを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/scilife-azure-ad-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Scilife Microsoft Entra SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Scilife Microsoft Entra SSO と Microsoft Entra ID を統合する方法について説明します。 このアプリケーションを使用すると、構成のほとんどが最小限の労力で自動的に行われるため、SSO 統合がシンプルで手間のかからないものになります。 Scilife Microsoft Entra SSO を Microsoft Entra ID を統合すると、次のことができます。

- Scilife Microsoft Entra SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Scilife Microsoft Entra SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Scilife Microsoft Entra SSO 用に Microsoft Entra のシングル サインオンを構成してテストします。 Scilife Microsoft Entra SSO では、**SP** Initiated シングル サインオンと **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### [前提条件]

Microsoft Entra ID を Scilife Microsoft Entra SSO と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Scilife Microsoft Entra SSO シングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Scilife Microsoft Entra SSO アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Scilife Microsoft Entra SSO を追加する

Microsoft Entra アプリケーション ギャラリーから Scilife Microsoft Entra SSO を追加して、Scilife Microsoft Entra SSO とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Scilife Microsoft Entra SSO**&gt;**シングル サインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://ldap-Environment.scilife.io/simplesaml/module.php/saml/sp/metadata.php/<CustomerUrlPrefix>-<Environment>-sp` |
    | `https://ldap.scilife.io/simplesaml/module.php/saml/sp/metadata.php/<CustomerUrlPrefix>-sp` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerUrlPrefix>.scilife.io/<languageCode>/login` |
    | `https://ldap.scilife.io/simplesaml/module.php/saml/sp/metadata.php/<CustomerUrlPrefix>-sp` |
    | `https://ldap.scilife.io/simplesaml/module.php/saml/sp/saml2-acs.php/<CustomerUrlPrefix>-sp` |
    | `https://<CustomerUrlPrefix>-<Environment>.scilife.io/<languageCode>/login` |
    | `https://ldap-<Environment>.scilife.io/simplesaml/module.php/saml/sp/metadata.php/<CustomerUrlPrefix>-<Environment>-sp` |
    | `https://ldap-<Environment>.scilife.io/simplesaml/module.php/saml/sp/saml2-acs.php/<CustomerUrlPrefix>-<Environment>-sp` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerUrlPrefix>.scilife.io/<languageCode>/login` |
    | `https://<CustomerUrlPrefix>-<Environment>.scilife.io/<languageCode>/login` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 [Scilife Microsoft Entra SSO サポート チーム](mailto:support@scilife.io)に連絡して、これらの値を取得します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Scilife Microsoft Entra SSO アプリケーションは特定の形式の SAML アサーションを予測しているため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Scilife Microsoft Entra SSO アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 名字 | ユーザーの名字 |
    | LDAPユーザーID (ldap\_user\_id) | ユーザー.ユーザープリンシパルネーム |
    | モバイル | ユーザー.電話番号 |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Scilife Microsoft Entra SSO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Scilife Microsoft Entra SSO を構成する

1. Scilife Microsoft Entra SSO の企業サイトに管理者としてログインします。
2. **[Manage] (管理)**&gt;**[Active Directory Settings] (Active Directory 設定)** の順に移動し、次の手順を実行します。

    [Image: Scilife Azure 管理ポータルを示すスクリーンショット。]

    1. **[Configure Active Directory] (Active Directory を構成する)** を有効にします。
    2. ドロップダウンから **[AD Azure]** の種類を選択します。
    3. **[ファイルの選択**] を選択して、**フェデレーション メタデータ XML ファイル**をダウンロードし、**MetadataXML** ファイルをアップロードします。
    4. [ **メタデータの解析]** を選択します。
3. 次のフィールドに **[テナント ID]**、**[アプリケーション ID]**、**[クライアント ID]** を入力します。

    [Image: Scilife Azure テナント ID を示すスクリーンショット。]
4. **[AD TRUST URL]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[識別子 (エンティティ ID)]** テキスト ボックスにその値を貼り付けます。
5. **AD CONSUMER SERVICE URL** をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスにその値を貼り付けます。

    [Image: Scilife Azure ポータルの URL を示すスクリーンショット。]
6. [ **構成の保存] を選択します**。

#### Scilife Microsoft Entra SSO テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Scilife Microsoft Entra SSO に作成します。 Scilife Microsoft Entra SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Scilife Microsoft Entra SSO にユーザーがまだ存在していない場合、一般に認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Scilife Microsoft Entra SSO のサインオン URL にリダイレクトされます。
- Scilife Microsoft Entra SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Scilife Microsoft Entra SSO] タイルを選択すると、このオプションは Scilife Microsoft Entra SSO のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sciquest-spend-director-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SciQuest Spend Director を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sciquest-spend-director-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SciQuest Spend Director の間でシングル サインオンを構成する方法について説明します。

この記事では、SciQuest Spend Director と Microsoft Entra ID を統合する方法について説明します。 SciQuest Spend Director を Microsoft Entra ID と統合すると、次のことができます:

- SciQuest Spend Director にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SciQuest Spend Director に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SciQuest Spend Director でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SciQuest Spend Director では、 **SP** によって開始される SSO がサポートされます。
- SciQuest Spend Director では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの SciQuest Spend Director の追加

Microsoft Entra ID への SciQuest Spend Director の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SciQuest Spend Director を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「SciQuest Spend Director**」と入力します。
4. 結果パネルから **SciQuest Spend Director** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SciQuest Spend Director の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SciQuest Spend Director に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Scuba SciQuest Spend Director の関連ユーザーとの間にリンク関係を確立する必要があります。

SciQuest Spend Director に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SciQuest Spend Director の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SciQuest Spend Director のテストユーザーを作成 - Microsoft Entra のユーザーとして B.Simon に対応するアカウントを SciQuest Spend Director 内で作成し、リンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**SciQuest Spend Director**&gt;**シングルサインオンへ移動します。**
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a． [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.sciquest.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.sciquest.com/apps/Router/ExternalAuth/Login/<instancename>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.sciquest.com/apps/Router/SAMLAuth/<instancename>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、SciQuest Spend Director クライアント サポート チーム](https://www.jaggaer.com/contact-us/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **SciQuest Spend Director のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SciQuest Spend Director の SSO の構成

**SciQuest Spend Director** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [SciQuest Spend Director サポート チーム](https://www.jaggaer.com/contact-us/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SciQuest Spend Director のテスト ユーザーの作成

このセクションの目的は、SciQuest Spend Director で Britta Simon というユーザーを作成することです。

[SciQuest Spend Director サポート チーム](https://www.jaggaer.com/contact-us/)に連絡し、テスト アカウントの詳細を提供して作成する必要があります。

また、SciQuest Spend Director でサポートされているシングル サインオン機能である Just-In-Time プロビジョニングを利用することもできます。 ジャストインタイム プロビジョニングが有効な場合、ユーザーが存在しなければ、シングル サインオンの試行中に SciQuest Spend Director によりユーザーが自動的に作成されます。 この機能により、対応するシングル サインオン ユーザーを手動で作成する必要がなくなります。

Just-In-Time プロビジョニングを有効にするには、 [SciQuest Spend Director サポート チーム](https://www.jaggaer.com/contact-us/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる SciQuest Spend Director のサインオン URL にリダイレクトされます。
- SciQuest Spend Director のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SciQuest Spend Director] タイルを選択すると、このオプションは SciQuest Spend Director のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/screencast-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ScreenPal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/screencast-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ScreenPal の間でシングル サインオンを構成する方法について説明します。

この記事では、ScreenPal と Microsoft Entra ID を統合する方法について説明します。 ScreenPal と Microsoft Entra ID を統合すると、次のことができます。

- ScreenPal にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して ScreenPal に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ScreenPal でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ScreenPal では、 **SP** によって開始される SSO のみがサポートされます。
- ScreenPal では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ScreenPal を追加する

Microsoft Entra ID への ScreenPal の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ScreenPal を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ScreenPal**」と入力します。
4. 結果パネルから **ScreenPal** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ScreenPal の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、ScreenPal に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ScreenPal の関連ユーザーとの間にリンク関係を確立する必要があります。

ScreenPal で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ScreenPal の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. ScreenPal テストユーザーの作成 - ScreenPal で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**ScreenPal**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://screencast-o-matic.com/<InstanceName>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、ScreenPal サポート チーム](mailto:support@screencast-o-matic.com) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. [ **ScreenPal のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ScreenPal SSO の構成

1. 別の Web ブラウザー ウィンドウで、ScreenPal 企業サイトに管理者としてサインインします
2. 左側のナビゲーションで **[認証** ] を選択し、次の手順を実行します。

    [Image: [アクセス ページ] セクションを示すスクリーンショット。]

    1. **[SAML 認証**] トグルをオンにします。
    2. **[IDP メタデータ ファイルのアップロード**] で、[ファイルの選択] を選択し、前にダウンロードしたメタデータをアップロードします。
    3. **ScreenPal SAML Info** から**メタデータ XML ファイル**をダウンロードします。
    4. **保存** を選択します。
3. **[SAML User Access]\(SAML ユーザー アクセス**\) でトグルを **[オン]** の位置に移動すると、ユーザーは SAML 経由でログインするように強制されます。 有効にすると、ScreenPal と ADFS ID プロバイダー間の通信を設定するための追加の設定が表示されます。
4. **ScreenPal SAML Info** および [UPLOAD IDP Metadata XML File]\(IDP メタデータ XML ファイルのアップロード\) でメタデータ XML ファイルをダウンロードし、[Choose File]\(ファイルの選択\) を選択して、以前にダウンロードしたメタデータをアップロードします。

#### ScreenPal テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ScreenPal に作成します。 ScreenPal では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ScreenPal にユーザーがまだ存在していない場合は、認証後に新しく作成されます。 ユーザーを手動で作成する必要がある場合は、 [ScreenPal クライアント サポート チーム](mailto:support@screencast-o-matic.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ScreenPal のサインオン URL にリダイレクトされます。
- ScreenPal のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ScreenPal] タイルを選択すると、このオプションは ScreenPal のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/screensteps-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に ScreenSteps を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/screensteps-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から ScreenSteps へのユーザー アカウントの自動プロビジョニングおよび自動プロビジョニング解除を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために ScreenSteps と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[ScreenSteps](http://www.screensteps.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- ScreenSteps でユーザーを作成する。
- アクセスが不要になったら、ScreenSteps のユーザーを削除します。
- Microsoft Entra ID と ScreenSteps の間でユーザー属性の同期を維持する。
- ScreenSteps でグループとグループ メンバーシップをプロビジョニングする
- ScreenSteps への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/screensteps-tutorial) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Admin アクセス許可がある ScreenSteps のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と ScreenSteps の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように ScreenSteps を構成する

ScreenSteps のサポートに連絡して、Microsoft Entra ID でのプロビジョニングをサポートするように ScreenSteps を構成します。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから ScreenSteps を追加する

Microsoft Entra アプリケーション ギャラリーから ScreenSteps を追加して、ScreenSteps へのプロビジョニングの管理を開始します。 SSO 向けに ScreenSteps を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: ScreenSteps への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で ScreenSteps に対する自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[ScreenSteps]** を選択します。

    [Image: アプリケーション リストの ScreenSteps リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、ScreenSteps テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が ScreenSteps に接続できることを確認します。 接続に失敗した場合は、ScreenSteps アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から ScreenSteps に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で ScreenSteps のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、ScreenSteps API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | ScreenSteps で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から ScreenSteps に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で ScreenSteps のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | ScreenSteps で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/screensteps-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ScreenSteps を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/screensteps-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-08
- Summary: Microsoft Entra ID と ScreenSteps の間にシングル サインオンを構成する方法について説明します。

この記事では、ScreenSteps と Microsoft Entra ID を統合する方法について説明します。 ScreenSteps を Microsoft Entra ID と統合すると、次のことができます。

- ScreenSteps にアクセスできるユーザーを Microsoft Entra ID 内で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して ScreenSteps に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な ScreenSteps のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ScreenSteps では、**SP** によって開始される SSO がサポートされます。
- ScreenSteps では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/screensteps-provisioning-tutorial)がサポートされています。

### ギャラリーから ScreenSteps を追加する

Microsoft Entra ID への ScreenSteps の統合を構成するには、ギャラリーから、お使いの管理対象 SaaS アプリの一覧に ScreenSteps を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ScreenSteps**」と入力します。
4. 結果のパネルから **[ScreenSteps]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ScreenSteps 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ScreenSteps に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ScreenSteps 内の関連ユーザーとの間にリンク関係を確立する必要があります。

ScreenSteps に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ScreenSteps の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ScreenSteps のテストユーザーを作成して、B.Simon に相当するユーザーとして Microsoft Entra の表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ScreenSteps**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<tenantname>.ScreenSteps.com` という形式で URL を入力します。

    注意

    この値は実際の値ではありません。 この値を実際の Sign-On URL で更新します。これについては、この記事で後述します。
6. [SAML **を使用して単一 Sign-On を設定する**] ページの [**SAML 署名証明書の**] セクションで、[ ダウンロード] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **ScreenSteps** の設定セクションで、要件に従って 1 つ以上の適切な URL をコピーします。

    [Image: 構成の URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ScreenSteps の SSO の構成

1. ScreenSteps 企業サイトに管理者としてサインインします。
2. **[Account Settings]**&gt;**[Site Access]** に移動します。

    [Image: パスを示すスクリーンショット。]
3. [**コンテンツ管理と管理センター**] の鉛筆アイコンを選択し、ドロップダウンメニューからアイデンティティプロバイダーとして [SAML ] を選択します。

    [Image: [Configuration] を示すスクリーンショット。]

    a **Remote Login URL** フィールドに、Microsoft Entra 管理センターからコピーした**ログイン URL** を貼り付けます。

    b。 **ログアウト URL** フィールドは空白のままにします

    c. **[SAML Consumer URL]** の値をコピーして、この値を Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクション内にある **[サインオン URL]** テキスト ボックスに貼り付けます。

    d. **[Entity ID]** の値をコピーし、この値を Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションの **[識別子 (エンティティ ID)]** テキスト ボックスに貼り付けます。

    え **[保存]** を選択します。

注意

シングル サインオンの画面の手順を構成するには、「[シングル サインオン](https://help.screensteps.com/a/1097728-how-to-set-up-single-sign-on)を設定する方法」を参照してください。 この記事では、Microsoft Entra ID を使用するように ScreenSteps を設定する手順について説明します。 ヘルプ記事でいくつかの質問に答えた後、"SSO の設定方法を選択してください" というメッセージが表示されます。 Microsoft Entra ID を選択し、「Microsoft Entra SSO を構成する」に進みます。

#### ScreenSteps のテスト ユーザーの作成

このセクションでは、ScreenSteps で Britta Simon というユーザーを作成します。 [ScreenSteps クライアント サポート チーム](https://www.screensteps.com/contact)と協力して、ScreenSteps プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- ScreenSteps の **[Testing and Activation]** タブに切り替えます。
- [**保存] & [コピー**] ボタンを選択して、**SAML テスト URL** をクリップボードにコピーします。
- Incognito ブラウザー ウィンドウを開き、URL を貼り付けます。
- 前の手順で作成した `B.Simon@contoso.com` テスト ユーザーでサインインします。 ScreenSteps へのアクセス権が付与され、`B.Simon@contoso.com` が ScreenSteps の [Users] の一覧に表示されます。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [ScreenSteps] タイルを選択すると、このオプションは ScreenSteps のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/scuba-analytics-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にスキューバ分析を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/scuba-analytics-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Scuba Analytics の間のシングル サインオンを構成する方法について説明します。

この記事では、Scuba Analytics と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID を Scuba Analytics を統合すると、次のことができます。

- Scuba Analytics にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Scuba Analytics に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Scuba Analytics でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- スキューバ分析では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーから Scuba Analytics を追加する

Microsoft Entra ID への Scuba Analytics の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Scuba Analytics を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに「**Scuba Analytics**」と入力します。
4. 結果パネルから **[Scuba Analytics]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Scuba Analytics の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Scuba Analytics に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Scuba Analytics の関連ユーザーとの間にリンク関係を確立する必要があります。

Scuba Analytics に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Scuba Analytics の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Scuba Analytics のテストユーザーを作成** - Scuba Analytics で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Scuba Analytics]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **スキューバ分析のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Scuba Analytics SSO の構成

**スキューバ分析**側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を[スキューバ分析サポート チーム](mailto:help@scuba.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Scuba Analytics テスト ユーザーの作成

このセクションでは、Scuba Analytics で Britta Simon というユーザーを作成します。 [スキューバ分析サポート チーム](mailto:help@scuba.io)と協力して、スキューバ分析プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したスキューバ分析に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [スキューバ分析] タイルを選択すると、SSO を設定したスキューバ分析に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sd-elements-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SD Elements を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sd-elements-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SD Elements の間でシングル サインオンを構成する方法について説明します。

この記事では、SD Elements と Microsoft Entra ID を統合する方法について説明します。 SD Elements を Microsoft Entra ID を統合すると、次のことができます。

- SD Elements にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SD Elements に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SD Elements でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SD Elements では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの SD Elements の追加

Microsoft Entra ID への SD Elements の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SD Elements を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「SD Elements**」と入力します。
4. 結果パネルから **SD 要素** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SD Elements 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SD Elements に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SD Elements の関連ユーザーとの間にリンク関係を確立する必要があります。

SD Elements に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SD Elements の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SD Elements のテストユーザーを作成する** - Microsoft Entra におけるユーザーの表現とリンクする形で、SD Elements 内に B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDエンタープライズ アプリSD Elementsシングルサインオンに移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    a [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANT_NAME>.sdelements.com/sso/saml2/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANT_NAME>.sdelements.com/sso/saml2/acs/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [SD Elements クライアント サポート チーム](mailto:support@sdelements.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. SD Elements アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、SD Elements アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 名字 | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **SD Elements のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SD Elements の SSO の構成

1. シングル サインオンを有効にするには、 [SD Elements サポート チーム](mailto:support@sdelements.com) に連絡し、ダウンロードした証明書ファイルを提供してください。
2. 別のブラウザー ウィンドウで、管理者として SD Elements テナントにサインオンします。
3. 上部のメニューで[ **システム**]、[ **シングル サインオン**]の順に選択します。

    [Image: [システム] が選択され、ドロップダウンから [シングル サインオン] が選択されていることを示すスクリーンショット。]
4. [ **単一 Sign-On 設定]** ダイアログで、次の手順を実行します。

    [Image: シングル サインオンの構成]

    a **[SSO の種類] で** 、[SAML] を選択**します**。

    b。 **Identity Provider Entity ID** ボックスに、**Microsoft Entra Identifier** の値を貼り付けます。

    c. **「Identity Provider Single Sign-On Service」** ボックスに、**ログイン URL** の値を貼り付けます。

    d. **[保存] を選択します**。

#### SD Elements のテスト ユーザーの作成

このセクションの目的は、SD Elements で B.Simon というユーザーを作成することです。 SD Elements の場合、SD Elements ユーザーは手動で作成します。

**SD Elements で B.Simon を作成するには、次の手順に従います。**

1. Web ブラウザー ウィンドウで、管理者として SD Elements 企業サイトにサインオンします。
2. 上部のメニューで、[ **ユーザー管理**] を選択し、[ユーザー] を選択 **します**。

    [Image: [ユーザー管理] ドロップダウンから選択された [ユーザー] を示すスクリーンショット。]
3. [ **新しいユーザーの追加] を選択します**。

    [Image: [新しいユーザーの追加] ボタンが選択されていることを示すスクリーンショット。]
4. [ **新しいユーザーの追加** ] ダイアログで、次の手順を実行します。

    [Image: SD Elements テスト ユーザーの作成]

    a **[電子メール**] ボックスに、ユーザーの電子メール (**b.simon@contoso.com**など) を入力します。

    b。 [ **名** ] ボックスに、ユーザーの名 ( **B.** など) を入力します。

    c. [ **姓]** ボックスに、ユーザーの姓 ( **Simon** など) を入力します。

    d. **ロール**として、[ユーザー] を選択**します**。

    え [ **ユーザーの作成] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SD Elements に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SD Elements] タイルを選択すると、SSO を設定した SD Elements に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sds-chemical-information-management-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SDS と Chemical Information Management を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sds-chemical-information-management-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SDS & Chemical Information Management の間でシングル サインオンを構成する方法について説明します。

この記事では、SDS と Chemical Information Management を Microsoft Entra ID と統合する方法について説明します。 SDS と Chemical Information Management を Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra ID で SDS と化学情報管理へのアクセスを制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SDS および Chemical Information Management に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SDS と Chemical Information Management でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SDS と Chemical Information Management では、 **SP** によって開始される SSO がサポートされます。
- SDS と化学情報管理では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの SDS と化学情報管理の追加

Microsoft Entra ID への SDS と化学情報管理の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SDS と化学情報管理を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. ギャラリーからの追加セクションで、検索ボックスに「SDS & 化学情報管理」と入力します。
4. 結果パネルから **SDS と化学情報管理** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SDS および Chemical Information Management の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SDS & Chemical Information Management に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SDS & Chemical Information Management の関連ユーザーとの間にリンク関係を確立する必要があります。

SDS および Chemical Information Management に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SDS と Chemical Information Management の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **SDS & Chemical Information Management テストユーザーを生成 - SDS** & Chemical Information Management で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**SDS & Chemical Information Management**&gt;**シングルサインオンを参照してください**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、`https://cs.cloudsds.com/saml/<ID>` という形式で URL を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cs.cloudsds.com/saml/<ID>/consumeAssertion`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://cs.cloudsds.com/saml/<ID>/Login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、Sign-On URL でこれらの値を更新します。 これらの値を取得するには [、SDS & Chemical Information Management クライアント サポート チーム](mailto:info@cloudsds.com) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SDS と Chemical Information Management の SSO の構成

**SDS および Chemical Information Management** 側でシングル サインオンを構成するには、**SDS および Chemical Information Management サポート チーム**に[アプリのフェデレーション メタデータ URL を](mailto:info@cloudsds.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SDS および化学情報管理のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを SDS & Chemical Information Management に作成します。 SDS と Chemical Information Management では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 SDS & Chemical Information Management にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SDS および Chemical Information Management のサインオン URL にリダイレクトされます。
- SDS & Chemical Information Management のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SDS および化学情報管理] タイルを選択すると、このオプションは SDS および Chemical Information Management のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/seattletimessso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SeattleTimesSSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/seattletimessso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SeattleTimesSSO の間でシングル サインオンを構成する方法について説明します。

この記事では、SeattleTimesSSO と Microsoft Entra ID を統合する方法について説明します。 これは、シアトル タイムズの機関購読用 SSO です。 SeattleTimesSSO と Microsoft Entra ID を統合すると、次のことができます。

- SeattleTimesSSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SeattleTimesSSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で SeattleTimesSSO 向けの Microsoft Entra のシングル サインオンを構成してテストする。 SeattleTimesSSO は、**IDP** によって開始されるシングル サインオンをサポートしています。

### 前提条件

Microsoft Entra ID を SeattleTimesSSO と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SeattleTimesSSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから SeattleTimesSSO アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから SeattleTimesSSO を追加する

Microsoft Entra アプリケーション ギャラリーから SeattleTimesSSO を追加して、SeattleTimesSSO でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「[クイック スタート: ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)からアプリケーションを追加する」を参照してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[作成してユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) 割り当てる方法に関する記事のガイドラインに従ってください。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides).

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SeattleTimesSSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[SeattleTimesSSO の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### SeattleTimesSSO の SSO を構成する

**SeattleTimesSSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [SeattleTimesSSO サポート チーム](mailto:it-hostingadmin@seattletimes.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### SeattleTimesSSO テスト ユーザーを作成する

このセクションでは、SeattleTimesSSO で Britta Simon というユーザーを作成します。 [SeattleTimesSSO サポート チーム](mailto:it-hostingadmin@seattletimes.com)と連携して、SeattleTimesSSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SeattleTimesSSO に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで SeattleTimesSSO タイルを選択すると、SSO を設定した SeattleTimesSSO に自動的にサインインします。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/second-nature-ai-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Second Nature AI を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/second-nature-ai-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-08
- Summary: Microsoft Entra ID と Second Nature AI の間でシングル サインオンを構成する方法について説明します。

この記事では、Second Nature AI と Microsoft Entra ID を統合する方法について説明します。 Second Nature AI を Microsoft Entra ID を統合すると、次のことができます。

- Second Nature AI にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Second Nature AI に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Second Nature AI でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Second Nature AI では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Second Nature AI では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Second Nature AI を追加する

Microsoft Entra ID への Second Nature AI の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Second Nature AI を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Second Nature AI**」と入力します。
4. 結果パネルから **[Second Nature AI]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Second Nature AI 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Second Nature AI に対して Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Second Nature AI の関連ユーザーとの間にリンク関係を確立する必要があります。

Second Nature AI に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Second Nature AI の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Second Nature AI テスト ユーザーの作成** - Second Nature AI で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Second Nature AI**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`urn:auth0:secondnature:<ID>` の形式で値を入力します。

    b。 **[応答 URL]** ボックスに、`https://secondnature.auth0.com/login/callback?connection=<ID>` のパターンを使用して URL を入力します
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://app.secondnature.ai/?sso-name=<ID>`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Second Nature AI サポート チーム](mailto:support@secondnature.ai)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Second Nature AI アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]
8. その他に、Second Nature AI アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | user.主要な正式メールアドレス |
    | 苗字 | User.surname |
    | 名（ファーストネーム） | User.givenname |
9. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[Second Nature AI の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは構成 URL をコピーするように示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Second Nature AI SSO を構成する

**Second Nature AI** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Second Nature AI サポート チーム](mailto:support@secondnature.ai)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Second Nature AI テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Second Nature AI に作成します。 Second Nature AI では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Second Nature AI にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Second Nature AI サインオン URL にリダイレクトされます。
- Second Nature AI のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Second Nature AI に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Second Nature AI タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Second Nature AI に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/secretserver-on-premises-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Secret Server (オンプレミス) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/secretserver-on-premises-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Secret Server (on-premises) の間でシングル サインオンを構成する方法を学習します。

この記事では、Secret Server (オンプレミス) と Microsoft Entra ID を統合する方法について説明します。 Secret Server (on-premises) を Microsoft Entra ID と統合すると、次のことができます。

- 誰が Secret Server (on-premises) にアクセスできるかを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Secret Server (on-premises) に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Secret Server (on-premises) シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Secret Server (on-premises) では、**SP および IDP** Initiated SSO がサポートされます

### Secret Server (on-premises) をギャラリーから追加する

Microsoft Entra ID への Secret Server (on-premises) の統合を構成するには、Secret Server (on-premises) をギャラリーから管理対象 SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Secret Server (On-Premises)** 」と入力します。
4. 結果ウィンドウで **[Secret Server (On-Premises)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Secret Server (on-premises) に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Secret Server (on-premises) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Secret Server (on-premises) 内の関連ユーザーとの間にリンク関係を確立する必要があります。

Secret Server (on-premises) に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Secret Server (On-Premises) の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Secret Server (オンプレミス) テスト ユーザーの作成** - Secret Server (オンプレミス) で B.Simon の対応ユーザーを作成し、Microsoft Entra のユーザーにリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Secret Server (On-Premises)** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、`https://secretserveronpremises.azure` という URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<SecretServerURL>/SAML/AssertionConsumerService.aspx` のパターンを使用して URL を入力します

    注

    上記のエンティティ ID は一例であり、Microsoft Entra ID で Secret Server インスタンスを識別する一意の値を自由に選択できます。 このエンティティ ID を [Secret Server (On-Premises) クライアント サポート チーム](https://support.delinea.com/s/)に送り、サポート チーム側で構成してもらう必要があります。 詳細については、[こちらの記事](https://docs.delinea.com/online-help/library/start.htm)を参照してください。
6. アプリケーションを **SP** initiated モードで構成する場合は、**[追加の URL を設定します]** を選択して次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<SecretServerURL>/login.aspx` という形式で URL を入力します。

    注

    これらの値は実際の値ではありません。 実際の応答 URLとサインオン URL でこれらの値を更新します。 これらの値を取得するには、[Secret Server (On-Premises) クライアント サポート チーム](https://support.delinea.com/s/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[SAML でシングル サインオンをセットアップします]** ページで、**[編集]** アイコンを選択して **[SAML 署名証明書]** ダイアログを開きます。

    [Image: [Certificate (Base64)](証明書 (Base64)) の [Download](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ダウンロード) アクションが選択されている [S A M L Signing Certificate](S A M L 署名証明書) セクションを示すスクリーンショット。]
9. **[証明書オプション]** で **[SAML 応答とアサーションへの署名]** を選択します。

    [Image: 署名オプション]
10. **[Secret Server (On-Premises) のセットアップ]** セクションで、要件に基づいて 1 つ以上の適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Secret Server (on-premises) SSO の構成

**Secret Server (On-Premises)** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とコピーした該当 URL を [Secret Server (on-premises) サポート チーム](https://support.delinea.com/s/)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Secret Server (on-premises) テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Secret Server (on-premises) 内に作成します。 [Secret Server (on-premises) サポート チーム](https://support.delinea.com/s/)と連携しながら、ユーザーを Secret Server (on-premises) プラットフォームに追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる Secret Server (オンプレミス) のサインオン URL にリダイレクトされます。
- Secret Server (on-premises) のサインオン URL に直接移動し、ここからサインイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Secret Server (オンプレミス) に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Secret Server (on-premises)] タイルを選択すると、SP モードで構成されている場合はサインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされ、IDP モードで構成されている場合は、SSO を設定した Secret Server (on-premises) に自動的にサインインした状態になります。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sectigo-certificate-manager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Sectigo Certificate Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sectigo-certificate-manager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sectigo Certificate Manager の間でシングル サインオンを構成する方法について説明します。

この記事では、Sectigo Certificate Manager と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Sectigo Certificate Manager を統合すると、次のことができます。

- Sectigo Certificate Manager にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Sectigo Certificate Manager に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sectigo Certificate Manager アカウント。

注

Sectigo では、複数の Sectigo Certificate Manager インスタンスを運用しています。 Sectigo Certificate Manager のメイン インスタンスは **https://cert-manager.com**であり、この記事ではこの URL を使用します。 アカウントが別のインスタンスにある場合は、それに応じて URL を調整する必要があります。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成してテストし、Sectigo Certificate Manager と Microsoft Entra ID を統合します。

Sectigo Certificate Manager では、次の機能がサポートされます。

- **SP Initiated シングル サインオン**。
- **IDP Initiated シングル サインオン**。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### Azure portal で Sectigo Certificate Manager を追加する

Microsoft Entra ID への Sectigo Certificate Manager の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Sectigo Certificate Manager を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Sectigo Certificate Manager**」と入力します。
4. 結果のパネルから **[Sectigo Certificate Manager]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sectigo Certificate Manager の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Sectigo Certificate Manager に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Sectigo Certificate Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

Sectigo Certificate Manager に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sectigo Certificate Manager の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Sectigo Certificate Manager のテスト ユーザーの作成 - Sectigo Certificate Manager** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sectigo Certificate Manager**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. Sectigo Certificate Manager のメイン インスタンスの **[識別子 (エンティティ ID)]** ボックスに「**https://cert-manager.com/shibboleth**」と入力します。
    2. Sectigo Certificate Manager のメイン インスタンスの **[応答 URL]** ボックスに「**https://cert-manager.com/Shibboleth.sso/SAML2/POST**」と入力します。

    注

    一般に "**SP Initiated モード**" では**サインオン URL** が必須となっていますが、Sectigo Certificate Manager からのログインでは不要です。
6. 必要に応じて、**IDP Initiated モード**を構成し、**テスト**を機能させるために、 **[基本的な SAML 構成]** セクションで次の手順を実行します。

    1. [ **追加の URL の設定] を選択します**。
    2. **[リレー状態]** ボックスに、Sectigo Certificate Manager の顧客別 URL を入力します。 Sectigo Certificate Manager のメイン インスタンスには、「**https://cert-manager.com/customer/&lt;customerURI&gt;/idp**」を入力します。
7. [ **ユーザー属性と要求** ] セクションで、次の手順を実行します。

    1. **追加の要求**をすべて削除します。
    2. **[新しいクレームの追加]** を選択し、次の 4 つのクレームを追加します。

        | 名前 | Namespace | 情報源 | ソース属性 | 説明 |
        | --- | --- | --- | --- | --- |
        | eduPersonPrincipalName（教育機関のユーザー識別名） | 空 | 特性 | ユーザー.ユーザープリンシパルネーム | 管理者用 Sectigo Certificate Manager の **[IdP Person ID](IdP 人物 ID)** フィールドの内容と合致している必要があります。 |
        | メール | 空 | 特性 | ユーザーのメールアドレス | 必須 |
        | givenName | 空 | 特性 | ユーザー.ファーストネーム | オプション |
        | エスエヌ | 空 | 特性 | ユーザーの名字 | オプション |

        [Image: Sectigo Certificate Manager - 4 つのクレームの追加]
8. **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** の隣にある **[ダウンロード]** を選択します。 XML ファイルを、お使いのコンピューターに保存します。

    [Image: フェデレーション メタデータ XML のダウンロード オプション]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sectigo Certificate Manager の SSO の構成

Sectigo Certificate Manager 側でシングル サインオンを構成するには、ダウンロードしたフェデレーション メタデータ XML ファイルを [Sectigo Certificate Manager サポート チーム](https://sectigo.com/support)に送信します。 Sectigo Certificate Manager チームは、送られてきた情報を使用して、SAML シングル サインオン接続が両方の側で正しく設定されているかどうかを確認します。

#### Sectigo Certificate Manager のテスト ユーザーの作成

このセクションでは、Sectigo Certificate Manager で Britta Simon というユーザーを作成します。 [Sectigo Certificate Manager サポート チーム](https://sectigo.com/support)と連携して、Sectigo Certificate Manager プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、Microsoft Entra のシングル サインオン構成をテストします。

##### Sectigo Certificate Manager からのテスト (SP Initiated シングル サインオン)

自分の顧客別 URL (Sectigo Certificate Manager のメイン インスタンスの場合には https://cert-manager.com/customer/&lt;customerURI&gt;) を参照し、**[または、他のサインイン手段を使用する]** の下にあるボタンを選択します。 正しく構成されている場合は、Sectigo Certificate Manager に自動的にサインインします。

##### Azure のシングル サインオン構成からのテスト (IDP Initiated シングル サインオン)

**Sectigo Certificate Manager** アプリケーション統合ペインで、 **[シングル サインオン]** 、 **[テスト]** の順に選択します。 正しく構成されている場合は、Sectigo Certificate Manager に自動的にサインインします。

##### マイ アプリ ポータルを使ったテスト (IDP Initiated シングル サインオン)

マイ アプリ ポータルで **[Sectigo Certificate Manager]** を選択します。 正しく構成されている場合は、Sectigo Certificate Manager に自動的にサインインします。 マイ アプリ ポータルの詳細については、「[マイ アプリ ポータルでアプリにアクセスして使用する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/seculio-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Seculio を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/seculio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Seculio の間にシングル サインオンを構成する方法について説明します。

この記事では、Seculio と Microsoft Entra ID を統合する方法について説明します。 Seculio を Microsoft Entra ID を統合すると、次のことができます。

- Seculio にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Seculio に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Seculio でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Seculio では、**SP Initiated SSO** と **IDP Initiated SSO** がサポートされます。

### ギャラリーからの Seculio の追加

Microsoft Entra ID への Seculio の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Seculio を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Seculio**」と入力します。
4. 結果パネルから **[Seculio]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Seculio 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Seculio に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Seculio の関連ユーザーとの間にリンク関係を確立する必要があります。

Seculio に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Seculio の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Seculio テストユーザーの作成** - SeculioでB.Simonに対応するユーザーを作成し、そのユーザーがMicrosoft EntraにおけるB.Simonの表現にリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Seculio**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://seculio.com/saml/<ID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://seculio.com/saml/acs/<ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://seculio.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Seculio サポート チーム](mailto:seculio@lrm.jp)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Set up Seculio](Seculio のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Seculio の SSO の構成

**Seculio** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Seculio サポート チーム](mailto:seculio@lrm.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Seculio のテスト ユーザーの作成

このセクションでは、Seculio で Britta Simon というユーザーを作成します。 [Seculio サポート チーム](mailto:seculio@lrm.jp)と協力して、Seculio プラットフォームでユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Seculio のサインオン URL にリダイレクトされます。
- Seculio のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Seculio に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Seculio] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Seculio に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/secure-login-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SecureLogin を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/secure-login-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から SecureLogin に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために SecureLogin と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[SecureLogin](https://securelogin.nu) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- SecureLogin でユーザーを作成する
- アクセスが不要になった場合に SecureLogin のユーザーを削除する
- Microsoft Entra ID と SecureLogin の間でユーザー属性の同期を維持する
- SecureLogin でグループとグループ メンバーシップをプロビジョニングする
- SecureLogin にシングル サインオンする (推奨)
- コード認証許可フロー認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [SecureLogin](https://securelogin.nu) のアカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と SecureLogin の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように SecureLogin を構成する

手順 5 の[管理者の資格情報](https://securelogin.nu)のセクションで**承認**するには、管理者としての **SecureLogin** アカウントが必要です。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから SecureLogin を追加する

Microsoft Entra アプリケーション ギャラリーから SecureLogin を追加して、SecureLogin へのプロビジョニングの管理を開始します。 SSO のために SecureLogin を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: SecureLogin への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で SecureLogin の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[SecureLogin]** を選択します。

    [Image: アプリケーションの一覧の [SecureLogin] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、[ **承認**] を選択します。 **SecureLogin** の [ドメインに移動] ページにリダイレクトされます。 SecureLogin ドメインを入力し、[ **Go** ] ボタンを選択します。 **SecureLogin** の [承認] ページにリダイレクトされます。 **ユーザー名**と**パスワード**を入力し、[**ログイン**] ボタンを選択します。 [ **テスト接続]** を選択して、Microsoft Entra ID が SecureLogin に接続できることを確認します。 接続できない場合は、使用中の SecureLogin アカウントで管理者アクセス許可を確保してから、もう一度試します。

    [Image: [Admin Credentials (管理者の資格情報)]]

    [Image: ようこそ]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から SecureLogin に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で SecureLogin のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、SecureLogin API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | エクスターナルID | 糸 |  |
    | 優先言語 | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から SecureLogin に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で SecureLogin のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ |
    | エクスターナルID | 糸 |  |
    | メンバー | リファレンス |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/securedeliver-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SECURE DELIVER を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/securedeliver-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-17
- Summary: Microsoft Entra ID と SECURE DELIVER の間でシングル サインオンを構成する方法について説明します。

この記事では、SECURE DELIVER と Microsoft Entra ID を統合する方法について説明します。 SECURE DELIVER と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で SECURE DELIVER へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SECURE DELIVER に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

SECURE DELIVER と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SECURE DELIVER でのシングル サインオンが有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SECURE DELIVER では、SP **によって開始される SSO** をサポートします。

### ギャラリーから SECURE DELIVER を追加する

Microsoft Entra ID への SECURE DELIVER の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SECURE DELIVER を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックスに「SECURE DELIVER  入力します。
4. 結果パネルから **SECURE DELIVER** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SECURE DELIVER の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、SECURE DELIVER に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SECURE DELIVER の関連ユーザーとの間にリンク関係を確立する必要があります。

SECURE DELIVER に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SECURE DELIVER SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **SECURE DELIVERテストユーザーを作成 - SECURE DELIVERでB.Simonに対応するユーザーを作成し、Microsoft EntraでそのユーザーがB.Simonにリンクされるようにします。**
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**SECURE DELIVER**&gt;**シングルサインオン**にアクセスします。
3. [**シングル サインオン方法の選択]** ページで、[SAML **] を**選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    ある。 [**識別子 (エンティティ ID)** テキスト ボックスに、次のパターンを使用して URL を入力します:`https://<companyname>.i-securedeliver.jp/sd/<tenantname>/postResponse`

    b。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<companyname>.i-securedeliver.jp/sd/<tenantname>/jsf/login/sso`

    手記

    これらの値は実際の値ではありません。 実際の識別子とサインオン URL でこれらの値を更新します。 これらの値 [取得するには、SECURE DELIVER クライアント サポート チーム](mailto:iw-sd-support@fujifilm.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SECURE DELIVER SSO の構成

SECURE DELIVER **側** でシングルサインオンを構成するには、ダウンロードした **フェデレーションメタデータXML** と、アプリケーション構成からコピーした適切なURLを、[SECURE DELIVER サポートチーム](mailto:iw-sd-support@fujifilm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SECURE DELIVER テスト ユーザーの作成

このセクションでは、SECURE DELIVER で Britta Simon というユーザーを作成します。 SECURE DELIVER サポート チーム  と連携して、SECURE DELIVER プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる SECURE DELIVER のサインオン URL にリダイレクトされます。
- SECURE DELIVER のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SECURE DELIVER] タイルを選択すると、このオプションは SECURE DELIVER のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/securejoinnow-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SecureW2 JoinNow Connector を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/securejoinnow-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SecureW2 JoinNow Connector の間でシングル サインオンを構成する方法について説明します。

この記事では、SecureW2 JoinNow Connector と Microsoft Entra ID を統合する方法について説明します。 SecureW2 JoinNow Connector と Microsoft Entra ID を統合すると、次のことができるようになります:

- SecureW2 JoinNow Connector にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SecureW2 JoinNow Connector に自動的にサインインできるようにすることができます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SecureW2 JoinNow Connector でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SecureW2 JoinNow Connector では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの SecureW2 JoinNow Connector の追加

Microsoft Entra ID への SecureW2 JoinNow Connector の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SecureW2 JoinNow Connector を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SecureW2 JoinNow Connector**」と入力します。
4. 結果のパネルから **[SecureW2 JoinNow Connector]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SecureW2 JoinNow Connector に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SecureW2 JoinNow Connector に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SecureW2 JoinNow Connector の関連ユーザーとの間にリンク関係を確立する必要があります。

SecureW2 JoinNow Connector で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SecureW2 JoinNow Connector SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SecureW2 JoinNow Connector のテストユーザーの作成** - SecureW2 JoinNow Connector の B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra で表現された B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SecureW2 JoinNow Connector** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<organization-identifier>-auth.securew2.com/auth/saml/SSO`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<organization-identifier>-auth.securew2.com/auth/saml`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[SecureW2 JoinNow Connector クライアント サポート チーム](mailto:support@securew2.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **メタデータ XML** を見つけて **[ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SecureW2 JoinNow Connector のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SecureW2 JoinNow Connector SSO の構成

**SecureW2 JoinNow Connector** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML** とアプリケーション構成からコピーした適切な URL を [SecureW2 JoinNow Connector サポート チーム](mailto:support@securew2.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SecureW2 JoinNow Connector テスト ユーザーの作成

このセクションでは、SecureW2 JoinNow Connector で Britta Simon というユーザーを作成します。 [SecureW2 JoinNow Connector サポート チーム](mailto:support@securew2.com)と連携して、SecureW2 JoinNow Connector プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SecureW2 JoinNow Connector のサインオン URL にリダイレクトされます。
- SecureW2 JoinNow Connector のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SecureW2 JoinNow Connector] タイルを選択すると、このオプションは SecureW2 JoinNow Connector のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/securetransport-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SecureTransport を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/securetransport-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SecureTransport の間のシングル サインオンを構成する方法について説明します。

この記事では、SecureTransport を Microsoft Entra ID と統合する方法について説明します。 SecureTransport は、小規模または大規模なあらゆる組織のすべての重要なファイルの転送ニーズを満たすためのフォールト トレランスと高可用性を備えた、拡張性の高い、回復性があるマルチプロトコル MFT ゲートウェイです。 Microsoft Entra ID と SecureTransport を統合すると、次のことができます。

- SecureTransport にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで SecureTransport に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SecureTransport に対する Microsoft Entra シングル サインオンをテスト環境で構成してテストする。 SecureTransport では、 **SP** によって開始されるシングル サインオンがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

SecureTransport を Microsoft Entra ID と統合するためには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SecureTransport でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから SecureTransport アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### SecureTransport を Microsoft Entra ギャラリーから追加する

Microsoft Entra アプリケーション ギャラリーから SecureTransport を追加して、SecureTransport でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SecureTransport**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかの値を入力します。

    | ユーザー タイプ | 価値 |
    | --- | --- |
    | 管理者 | `st.sso.admin` |
    | エンドユーザー | `st.sso.enduser` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | ユーザー タイプ | URL |
    | --- | --- |
    | 管理者 | `https://<SecureTransport_Address>:<PORT>/saml2/sso/post/j_security_check` |
    | エンドユーザー | `https://<SecureTransport_Address>:<PORT>/saml2/sso/post` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | ユーザー タイプ | URL |
    | --- | --- |
    | 管理者 | `https://<SecureTransport_Address>:<PORT>` |
    | エンドユーザー | `https://<SecureTransport_Address>:<PORT>` |

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには [、SecureTransport クライアント サポート チーム](mailto:support@axway.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. SecureTransport アプリケーションは特定の形式の SAML アサーションを予測しているため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、SecureTransport ではこれがユーザーの表示名にマップされていることが想定されています。 そのため、リストから **user.displayname** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: トークン属性の構成の画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **SecureTransport のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### SecureTransport の SSO の構成

**SecureTransport** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [SecureTransport サポート チーム](mailto:support@axway.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SecureTransport テスト ユーザーを作成する

このセクションでは、SecureTransport で Britta Simon というユーザーを作成します。 [SecureTransport サポート チーム](mailto:support@axway.com)と協力して、SecureTransport プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SecureTransport のサインオン URL にリダイレクトされます。
- SecureTransport のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SecureTransport] タイルを選択すると、このオプションは SecureTransport のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/securitystudio-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SecurityStudio を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/securitystudio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SecurityStudio の間でシングル サインオンを構成する方法について説明します。

この記事では、SecurityStudio と Microsoft Entra ID を統合する方法について説明します。 SecurityStudio と Microsoft Entra ID を統合すると、次のことができます。

- SecurityStudio にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SecurityStudio に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SecurityStudio でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SecurityStudio では、**IDP** イニシエート SSO がサポートされます。

### ギャラリーから SecurityStudio を追加する

Microsoft Entra ID への SecurityStudio の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SecurityStudio を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリーから **追加**] セクションで、検索ボックスに「**SecurityStudio**」を入力します。
4. 結果パネル **SecurityStudio** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SecurityStudio の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、SecurityStudio に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SecurityStudio の関連ユーザーとの間にリンク関係を確立する必要があります。

SecurityStudio に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SecurityStudio SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **SecurityStudio のテスト ユーザーの作成 - SecurityStudio** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. \*\* **Entra ID**&gt;**エンタープライズ アプリ**&gt;**SecurityStudio**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法の選択**] ページで、[**SAML**] を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: SAML の基本構成を編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. SecurityStudio アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: イメージ]
7. 上記に加えて、SecurityStudio アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザーのメール |

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SecurityStudio SSO の構成

SecurityStudio **側** シングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を SecurityStudio サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SecurityStudio テスト ユーザーの作成

このセクションでは、SecurityStudio で Britta Simon というユーザーを作成します。 SecurityStudio サポート チーム  と連携して、SecurityStudio プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SecurityStudio に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SecurityStudio] タイルを選択すると、SSO を設定した SecurityStudio に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sedgwickcms-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Sedgwick CMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sedgwickcms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sedgwick CMS の間のシングル サインオンを構成する方法について説明します。

この記事では、Sedgwick CMS と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Sedgwick CMS を統合すると、次のことができます。

- Sedgwick CMS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Sedgwick CMS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sedgwick CMS でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Sedgwick CMS では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Sedgwick CMS を追加する

Microsoft Entra ID への Sedgwick CMS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Sedgwick CMS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「Sedgwick CMS**」と入力します。
4. 結果パネルから **Sedgwick CMS** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sedgwick CMS の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Sedgwick CMS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Sedgwick CMS の関連ユーザーとの間にリンク関係を確立する必要があります。

Sedgwick CMS に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sedgwick CMS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Sedgwick CMS テストユーザーを作成** - Sedgwick CMS で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sedgwick CMS**&gt;**シングルサインオン**に進みます。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかの値を入力します。

    | **識別子** |
    | --- |
    | `expresspreview.sedgwickcms.net/voe/sso` |
    | `claimlookup.com/Voe/sso` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<subdomain>.sedgwickcms.net/voe/sso` |
    | `https://claimlookup.com/Voe/sso` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Sedgwick CMS クライアント サポート チーム](https://www.sedgwick.com/help) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Sedgwick CMS のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sedgwick CMS SSO を構成する

**Sedgwick CMS** 側でシングル サインオンを構成するには、ダウンロードした **FederationMetadata XML** と、アプリケーション構成からコピーした適切な URL を [Sedgwick CMS サポート チームに送信する](https://www.sedgwick.com/help)必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Sedgwick CMS のテスト ユーザーの作成

このセクションでは、Sedgwick CMS で Britta Simon というユーザーを作成します。 [Sedgwick CMS サポート チーム](https://www.sedgwick.com/help)と協力して、Sedgwick CMS プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Sedgwick CMS に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Sedgwick CMS] タイルを選択すると、SSO を設定した Sedgwick CMS に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/seekout-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に SeekOut を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/seekout-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SeekOut 間にシングル サインオンを構成する方法について学習します。

この記事では、SeekOut と Microsoft Entra ID を統合する方法について説明します。 SeekOut を Microsoft Entra ID と統合すると、次のことができます。

- SeekOut にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SeekOut に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SeekOut でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SeekOut では、**SP Initiated SSO** と **IDP Initiated SSO** がサポートされます。
- SeekOut では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから SeekOut を追加する

Microsoft Entra ID への SeekOut の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SeekOut を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SeekOut**」と入力します。
4. 結果パネルから **[SeekOut]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SeekOut 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SeekOut に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SeekOut の関連ユーザーとの間にリンク関係を確立する必要があります。

SeekOut に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SeekOut の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SeekOut テスト ユーザーの作成 - SeekOut** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SeekOut**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.seekout.io/api/auth/sso/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.seekout.io`

    注

    この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、[SeekOut サポート チーム](mailto:support@seekout.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[SeekOut のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SeekOut の SSO の構成

**SeekOut** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [SeekOut サポート チーム](mailto:support@seekout.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SeekOut テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを SeekOut に作成します。 SeekOut では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 SeekOut にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SeekOut サインオン URL にリダイレクトされます。
- SeekOut のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SeekOut に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SeekOut] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SeekOut に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/segment-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用にセグメントを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/segment-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から Segment に対するユーザー アカウントのプロビジョニングおよびプロビジョニング解除を自動的に行う方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Segment ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、[Segment](https://www.segment.com/) に対して自動でユーザーとグループのプロビジョニングおよびプロビジョニング解除を行います。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Segment でユーザーを作成する
- アクセスが不要になった場合にセグメント内のユーザーを削除する
- Microsoft Entra ID と Segment の間でユーザー属性の同期を維持する
- セグメントでグループとグループメンバーシップを設定する
- Segment への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/segment-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 所有者アクセス許可を持つ Segment 内のユーザー アカウント。
- お客様のワークスペースで SSO が有効になっている必要があります (ビジネス層のサブスクリプションが必要です)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. どのデータを [Microsoft Entra ID と Segment の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)かを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Segment を構成する

1. テナント URL は `https://scim.segmentapis.com/scim/v2` です。 この値は、セグメント アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力されます。
2. [Segment](https://www.segment.com/) アプリにサインインします。
3. 左側のパネルで、 **[設定]**&gt;**[認証]**&gt;**[詳細設定]** に移動します。

    [Image: パネル]
4. [ **SSO Sync** ] まで下にスクロールし、[ **SSO トークンの生成**] を選択します。

    [Image: アクセス]
5. [Bearer token] (ベアラー トークン) をコピーして保存します。 この値は、セグメント アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: トークン]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Segment を追加する

Microsoft Entra アプリケーション ギャラリーから Segment を追加して、Segment へのプロビジョニングの管理を開始します。 SSO のために Segment を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Segment への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Segment の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[セグメント]** を選択します。

    [Image: アプリケーション一覧のSegmentリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、セグメント テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Segment に接続できることを確認します。 接続に失敗した場合は、Segment アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Segment に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Segment のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Segment API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |
    | ディスプレイ名 | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Segment に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Segment のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ |
    | メンバー | リファレンス |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/segment-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にセグメントを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/segment-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Segment 間にシングル サインオンを構成する方法について説明します。

この記事では、Segment と Microsoft Entra ID を統合する方法について説明します。 Segment を Microsoft Entra ID と統合すると、次のことができます。

- Segment にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Segment に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- セグメントでのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- セグメントでは、**SP および IDP** Initiated SSO がサポートされています。
- セグメントでは、**Just-In-Time** ユーザー プロビジョニングがサポートされています。
- Segment では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/segment-provisioning-tutorial)がサポートされます。

### ギャラリーからのセグメントの追加

Microsoft Entra ID への Segment の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Segment を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**セグメント**」と入力します。
4. [結果] パネルから **[セグメント]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Segment 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Segment に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Segment の関連ユーザーとの間にリンク関係を確立する必要があります。

Segment に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **セグメント SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Segment テスト ユーザーの作成** - Segment 上で B.Simon に対応するユーザーを作成し、それを Microsoft Entra におけるユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**セグメント**&gt;**シングル サインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:segment-prod:samlp-<CUSTOMER_VALUE>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://segment-prod.auth0.com/login/callback?connection=<CUSTOMER_VALUE>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.segment.com`

    注

    これらの値はプレースホルダーです。 実際の識別子と応答 URL を使用する必要があります。 これらの値を取得する手順については、この記事の後半で説明します。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[セグメントのセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### セグメント SSO の構成

1. 新しい Web ブラウザー ウィンドウで、セグメント企業サイトに管理者としてサインインします。
2. **[設定] アイコン**を選択し、[**認証**] まで下にスクロールし、[接続] を選択**します**。

    [Image: [Setting](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) アイコンが選択され、[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) メニューの [Connections](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/接続) が選択されているスクリーンショット。]
3. [ **新しい接続の追加] を選択します**。

    [Image: [Connections](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/接続) セクションを示すスクリーンショット。[Add new Connection](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しい接続の追加) ボタンが選択されています。]
4. 構成する接続として **SAML 2.0** を選択し、[接続の **選択** ] ボタンを選択します。

    [Image: [Choose a Connection](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/接続の選択) セクションを示すスクリーンショット。[S A M L 2.0] および [Select Connection](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/接続を選択) ボタンが選択されています。]
5. 次のページで、以下の手順を実行します。

    [Image: [Configure Identity Provider](I D プロバイダーの構成) ページを示すスクリーンショット。[Single Sign-On U R L](シングル サインオン U R L) および [Audience U R L](対象 U R L) テキスト ボックスが強調表示され、[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ) ボタンが選択されています。]

    ある。 **[シングル サインオン URL]** の値をコピーし、**[Basic SAML Configuration](基本的な SAML 構成)** ダイアログ ボックスの **[応答 URL]** ボックスに貼り付けます。

    b。 **[Audience URL](対象 URL)** の値をコピーし、それを **[Basic SAML Configuration](基本的な SAML 構成)** ダイアログ ボックスの **[識別子 URL]** ボックスに貼り付けます。

    c. [**次へ**] を選択します。

    [Image: セグメント構成]
6. **[SAML 2.0 エンドポイント URL**] ボックスに、コピーした**ログイン URL** の値を貼り付けます。
7. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[公開証明書]** テキストボックスに貼り付けます。
8. [ **接続の構成] を選択します**。

#### セグメント テスト ユーザーの作成

このセクションでは、B.Simon というユーザーをセグメントに作成します。 セグメントでは、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 セグメントにユーザーがまだ存在していない場合は、認証後に新規に作成されます。

Segment では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/segment-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できるセグメント サインオン URL にリダイレクトされます。
- セグメントのサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したセグメントに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [セグメント] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したセグメントに自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/seismic-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Seismic を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/seismic-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と Seismic の間にシングル サインオンを構成する方法について説明します。

この記事では、Seismic と Microsoft Entra ID を統合する方法について説明します。 Seismic を Microsoft Entra ID と統合すると、次のことができます。

- Seismic にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Seismic に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Seismic は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Seismic でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Seismic では、 **SP** Initiated SSO がサポートされます。

### ギャラリーから Seismic を追加

Microsoft Entra ID への Seismic の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Seismic を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーから追加**] セクションで、検索ボックスに「**Seismic**」と入力します。
4. 結果パネルから **[Seismic** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Seismic 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Seismic に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Seismic の関連ユーザーとの間にリンク関係を確立する必要があります。

Seismic に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Seismic SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Seismic テスト ユーザーの作成** - Seismic で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Seismic**&gt;**シングルサインオン**にブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.seismic.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.seismic.com`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.seismic.com/SSO/<ROUTEURL>`

    注

    これらの値は実際の値ではありません。 これらの値は、実際のサインオン URL、識別子、応答 URL で更新してください。 これらの値を取得するには、 [Seismic クライアント サポート チーム](mailto:support@seismic.com) に問い合わせてください。 **サービス プロバイダー メタデータ**をアップロードして識別子の値を自動的に設定することもできます。**サービス プロバイダー メタデータ**の詳細については、[Seismic クライアント サポート チーム](mailto:support@seismic.com)にお問い合わせください。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Seismic のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Seismic SSO の設定

**Seismic** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Seismic サポート チーム](mailto:support@seismic.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Seismic のテスト ユーザーの作成

このセクションでは、Seismic で Britta Simon というユーザーを作成します。 [Seismic サポート チーム](mailto:support@seismic.com)と協力して、Seismic プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Seismic サインオン URL にリダイレクトされます。
- Seismic のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Seismic] タイルを選択すると、このオプションは Seismic のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sendpro-enterprise-tutorial"} -->
## Microsoft Entra ID でシングルサインオンのために SendPro Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sendpro-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SendPro Enterprise 間にシングル サインオンを構成する方法について説明します。

この記事では、SendPro Enterprise と Microsoft Entra ID を統合する方法について説明します。 SendPro Enterprise を Microsoft Entra ID と統合すると、次のことができます。

- SendPro Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SendPro Enterprise に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SendPro Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SendPro Enterprise では、**SP** Initiated SSO がサポートされます

### ギャラリーからの SendPro Enterprise の追加

Microsoft Entra ID への SendPro Enterprise の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SendPro Enterprise を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SendPro Enterprise**」と入力します。
4. 結果パネルから **[SendPro Enterprise]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SendPro Enterprise 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SendPro Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SendPro Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

SendPro Enterprise に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SendPro Enterprise の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SendPro Enterprise のテストユーザーを作成** - Microsoft Entra のユーザー表現にリンクされた、SendPro Enterprise 内の B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SendPro Enterprise**&gt;**シングルサインオン**に移動する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANT_NAME>.sendproenterprise.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[SendPro Enterprise クライアント サポート チーム](https://www.pitneybowes.com/us/support.html)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SendPro Enterprise のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SendPro Enterprise の SSO の構成

**SendPro Enterprise** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [SendPro Enterprise サポート チーム](https://www.pitneybowes.com/us/support.html)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SendPro Enterprise のテスト ユーザーの作成

このセクションでは、SendPro Enterprise で Britta Simon というユーザーを作成します。 [SendPro Enterprise サポート チーム](https://www.pitneybowes.com/us/support.html)と連携して、SendPro Enterprise プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SendPro Enterprise のサインオン URL にリダイレクトされます。
- SendPro Enterprise のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SendPro Enterprise] タイルを選択すると、このオプションは SendPro Enterprise のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sendsafely-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SendSafely を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sendsafely-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SendSafely の間にシングル サインオンを構成する方法について学習します。

この記事では、SendSafely と Microsoft Entra ID を統合する方法について説明します。 SendSafely を Microsoft Entra ID と統合すると、次のことができます。

- SendSafely にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SendSafely に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SendSafely でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SendSafely では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- SendSafely では、**Just-In-Time** ユーザー プロビジョニングがサポートされます
- SendSafely を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスを拡張したものです。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)でセッション制御を適用する方法について説明します。

### ギャラリーからの SendSafely の追加

Microsoft Entra ID への SendSafely の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SendSafely を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SendSafely**」と入力します。
4. 結果のパネルから **[SendSafely]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SendSafely の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、SendSafely に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SendSafely の関連ユーザーとの間にリンク関係を確立する必要があります。

SendSafely に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SendSafely SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SendSafely テスト ユーザーの作成** - SendSafelyで B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SendSafely**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ア. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SENDSAFELY_URL>/auth/saml2/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SENDSAFELY_URL>/auth/saml2/`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    ア. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SENDSAFELY_URL>/auth/`

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SENDSAFELY_URL>/auth/saml2/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態でこれらの値を更新します。 この値を取得するには、[SendSafely クライアント サポート チーム](mailto:support@sendsafely.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[SendSafely のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SendSafely SSO の構成

**SendSafely** 側にシングル サインオンを構成するには、[ここ](https://sendsafely.zendesk.com/hc/articles/360004152492-Setup-Single-Sign-On-SSO-with-SAML)に記載されている手順に従ってください。

#### SendSafely テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを SendSafely に作成します。 SendSafely では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ SendSafely に存在しない場合は、SendSafely にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [SendSafely] タイルを選択すると、SSO を設定した SendSafely に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/senhasegura-saml-authentication-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に senhasegura SAML Authentication を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/senhasegura-saml-authentication-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と senhasegura SAML Authentication の間でシングル サインオンを構成する方法について説明します。

この記事では、senhasegura SAML Authentication と Microsoft Entra ID を統合する方法について説明します。 senhasegura SAML Authentication と Microsoft Entra ID を統合すると、次のことができます。

- senhasegura SAML 認証にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して senhasegura SAML Authentication に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- senhasegura SAML Authentication でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- senhasegura SAML 認証は、**SP および IDP** による SSO 開始をサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから senhasegura SAML Authentication を追加する

Microsoft Entra ID への senhasegura SAML Authentication の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に senhasegura SAML Authentication を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「senhasegura SAML Authentication**」と入力します。
4. 結果パネルから **senhasegura SAML Authentication** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### senhasegura SAML Authentication の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、senhasegura SAML Authentication に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと senhasegura SAML Authentication の関連ユーザーとの間にリンク関係を確立する必要があります。

senhasegura SAML Authentication に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **senhasegura SAML Authentication SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **senhasegura SAML認証用のテストユーザーを作成する - Microsoft Entra上のユーザーの一例であるB.Simonに対応するsenhasegura SAML認証ユーザーを作成し、リンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**senhasegura SAML 認証**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、値を入力します。 `senhasegura-saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<senhasegura_CUSTOM_URL>/flow/saml/auth/assert`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<senhasegura_CUSTOM_URL>/flow/saml/auth/assert`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには [、senhasegura SAML 認証サポート チーム](mailto:suporte@senhasegura.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### senhasegura SAML Authentication SSO の構成

**senhasegura SAML Authentication** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[senhasegura SAML Authentication サポート チーム](mailto:suporte@senhasegura.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### senhasegura SAML Authentication テスト ユーザーの作成

このセクションでは、senhasegura SAML Authentication で B.Simon というユーザーを作成します。 [senhasegura SAML Authentication サポート チーム](mailto:suporte@senhasegura.com)と協力して、senhasegura SAML Authentication プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる senhasegura SAML 認証サインオン URL にリダイレクトします。
- senhasegura SAML Authentication のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した senhasegura SAML Authentication に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [senhasegura SAML Authentication] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した senhasegura SAML Authentication に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/senomix-timesheets-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Senomix タイムシートを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/senomix-timesheets-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Senomix タイムシートの間でシングル サインオンを構成する方法について説明します。

この記事では、Senomix Timesheets と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Senomix Timesheets を統合すると、次のことができます。

- Senomix タイムシートにアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが Microsoft Entra アカウントを使用して Senomix タイムシートに自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Senomix Timesheets でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Senomix Timesheets では、**SP および IDP** によって開始された SSO の両方がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Senomix タイムシートを追加する

Microsoft Entra ID への Senomix Timesheets の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Senomix タイムシートを追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Senomix Timesheets**」と入力します。
4. 結果パネルから **Senomix タイムシートを** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Senomix タイムシートの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Senomix タイムシートに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Senomix Timesheets の関連ユーザーとの間にリンク関係を確立する必要があります。

Senomix Timesheets に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Senomix Timesheets の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Senomix Timesheets のテストユーザーを作成する** - Microsoft Entra 上の B.Simon に対応するユーザーを Senomix Timesheets で作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Senomix Timesheets**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://timesheet.senomix.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.senomix.com/simplesaml/module.php/saml/sp/saml2-acs.php/<CUSTOMER_AZURE_TENANT_ID>`

    c. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.senomix.com/saml_sso/<CUSTOMER_AZURE_TENANT_ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のいずれかの URL/パターンを入力します。

    | **サインオン URL** |
    | --- |
    | `https://www.senomix.com/timesheet` |
    | `https://www.senomix.com/saml_sso/<CUSTOMER_AZURE_TENANT_ID>` |

    注

    これらの値は実際の値ではありません。 実際の応答 URL、リレー状態、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Senomix Timesheets サポート チーム](mailto:support@senomix.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Senomix Timesheets のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Senomix Timesheets SSO を設定する

**Senomix Timesheets** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と Microsoft Entra 管理センターからコピーした適切な URL を [Senomix タイムシート サポート チーム](mailto:support@senomix.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Senomix Timesheets テスト ユーザーの作成

このセクションでは、Senomix Timesheets で B.Simon というユーザーを作成します。 [Senomix Timesheets サポート チーム](mailto:support@senomix.com)と協力して、Senomix Timesheets プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Senomix Timesheets のサインオン URL にリダイレクトされます。
- Senomix Timesheets のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Senomix タイムシートに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Senomix Timesheets] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Senomix タイムシートに自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sensoscientific-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SensoScientific Wireless Temperature Monitoring System を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sensoscientific-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と SensoScientific Wireless Temperature Monitoring System の間でシングル サインオンを構成する方法について説明します。

この記事では、SensoScientific Wireless Temperature Monitoring System と Microsoft Entra ID を統合する方法について説明します。 SensoScientific Wireless Temperature Monitoring System と Microsoft Entra ID を統合すると、次のことができます。

- SensoScientific Wireless Temperature Monitoring System にアクセスするユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SensoScientific Wireless Temperature Monitoring System に自動的にサインイン できるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

Microsoft Entra と SensoScientific Wireless Temperature Monitoring System の統合を構成するには、次が必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SensoScientific Wireless Temperature Monitoring System でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SensoScientific Wireless Temperature Monitoring System では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーからの SensoScientific Wireless Temperature Monitoring System の追加

Microsoft Entra ID への SensoScientific Wireless Temperature Monitoring System の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに SensoScientific Wireless Temperature Monitoring System を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加]** セクションで、検索ボックスに「 **SensoScientific Wireless Temperature Monitoring System** 」と入力します。
4. 結果パネルから **[SensoScientific Wireless Temperature Monitoring System]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SensoScientific Wireless Temperature Monitoring System 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SensoScientific Wireless Temperature Monitoring System に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと SensoScientific Wireless Temperature Monitoring System の関連ユーザーとの間にリンク リレーションシップを確立する必要があります。

SensoScientific Wireless Temperature Monitoring System に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SensoScientific Wireless Temperature Monitoring System の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SensoScientific Wireless Temperature Monitoring System のテスト ユーザーの作成** - SensoScientific Wireless Temperature Monitoring System で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SensoScientific Wireless Temperature Monitoring System**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[SensoScientific Wireless Temperature Monitoring System の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SensoScientific Wireless Temperature Monitoring System のSSO の構成

1. 管理者として SensoScientific Wireless Temperature Monitoring System アプリケーションにサインオンします。
2. 上部のナビゲーション メニューで、[**構成**] を選択し、[**シングル サインオン**] の下の **[構成**] に移動して[シングル サインオン設定] を開き、次の手順を実行します。

    [Image: シングル サインオンを構成することを示すスクリーンショット。]

    ア。 **[発行者名]** を [Microsoft Entra ID] として選択します。

    b。 **[発行者 URL]** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    c. **[シングル サインオン サービス URL]** テキストボックスに**ログイン URL** を貼り付けます。

    d. **[シングル サインアウト サービス URL]** テキストボックスに**ログアウト URL** を貼り付けます。

    え Azure ポータルからダウンロードした証明書を参照してアップロードします。

    f. **保存** を選択します。

#### SensoScientific Wireless Temperature Monitoring System のテスト ユーザーの作成

Microsoft Entra ユーザーが SensoScientific Wireless Temperature Monitoring System にサインインできるようにするには、ユーザーを SensoScientific Wireless Temperature Monitoring System にプロビジョニングする必要があります。 [SensoScientific Wireless Temperature Monitoring System サポート チーム](https://www.sensoscientific.com/contact-us/)と連携して、SensoScientific Wireless Temperature Monitoring System プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SensoScientific Wireless Temperature Monitoring System に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SensoScientific Wireless Temperature Monitoring System] タイルを選択すると、SSO を設定した SensoScientific Wireless Temperature Monitoring System に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sentry-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Sentry を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sentry-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-20
- Summary: Microsoft Entra ID から Sentry に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Sentry ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Sentry](https://sentry.io/welcome/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Sentry でユーザーを作成する。
- アクセスが不要になった場合は、Sentry のユーザーを削除します。
- Microsoft Entra ID と Sentry の間でユーザー属性の同期を維持する。
- Sentry でグループとグループメンバーシップを設定する。
- Sentry への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sentry-tutorial) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- この機能は、Sentry 組織がビジネスプランまたはエンタープライズプランの場合にのみ使用できます。 試用版プランでは使用できません。
- 組織で Azure SSO のセットアップが既に構成されている必要があります。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Sentry の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Sentry を構成する

1. Sentry 組織にサインインします。 **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) &gt; [Auth](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** を選択します
2. [全般設定] で、 **[Enable SCIM](SCIM を有効にする)** 、 **[設定の保存]** の順に選択します。
3. Sentry に、認証トークンと SCIM ベース URL を含む **SCIM 情報**が表示されます。
4. SCIM ベース URL は Microsoft Entra ID のテナント URL であり、認証トークンはシークレット トークンです。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Sentry を追加する

Microsoft Entra アプリケーション ギャラリーから Sentry を追加して、Sentry へのプロビジョニングの管理を開始します。 SSO のために Sentry を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Sentry への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Sentry でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Sentry の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Sentry]** を選択します。

    [Image: アプリケーションの一覧の Sentry のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Sentry テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Sentry に接続できることを確認します。 接続に失敗した場合は、Sentry アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Sentry に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性を使用して、Sentry での更新処理でユーザー アカウントが照合されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Sentry API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Sentry に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Sentry のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | 表示名 | 糸 | ✓ |
    | メンバー | リファレンス |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sentry-tutorial"} -->
## Microsoft Entra ID で Sentry for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sentry-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sentry の間にシングル サインオンを構成する方法について説明します。

この記事では、Sentry と Microsoft Entra ID を統合する方法について説明します。 Sentry と Microsoft Entra ID を統合すると、次のことができます。

- Sentry にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Sentry に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sentry でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Sentry では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Sentry では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Sentry の追加

Microsoft Entra ID への Sentry の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Sentry を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Sentry**」と入力します。
4. 結果のパネルから **[Sentry]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sentry 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Sentry に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Sentry の関連ユーザーとの間にリンク関係を確立する必要があります。

Sentry に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sentry の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Sentry テスト ユーザーを作成** - Microsoft Entra にある B.Simon の対応ユーザーを Sentry にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sentry**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sentry.io/saml/metadata/<ORGANIZATION_SLUG>/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sentry.io/saml/acs/<ORGANIZATION_SLUG>/`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sentry.io/organizations/<ORGANIZATION_SLUG>/`

    注

    これらの値は実際の値ではありません。 実際の値の識別子、応答 URL、サインオン URL でこれらの値を更新してください。 これらの値の詳細については、[Sentry のドキュメント](https://docs.sentry.io/product/accounts/sso/azure-sso/#installation)を参照してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、コピー アイコンを選択して **アプリ メタデータ URL** の値をコピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sentry の SSO の構成

**Sentry** 側でシングル サインオンを構成するには、 **[組織の設定]**&gt;**[認証]** に移動し (または `https://sentry.io/settings/<YOUR_ORG_SLUG>/auth/` に移動します)、Active Directory の **[構成]** を選択します。 Azure SAML 構成から [アプリのフェデレーション メタデータ URL] を貼り付けます。

#### Sentry のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Sentry に作成します。 Sentry では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Sentry にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

1. [ **このアプリケーションをテストする**] を選択します。 サインイン フローを開始することができる Sentry のサインオン URL にリダイレクトされます。
2. Sentry のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP 起動しました。

- Azure portal で、[ **このアプリケーションをテスト**する] を選択します。 SSO を設定した Sentry アプリケーションに自動的にサインインされます。

##### 両方のモード:

マイ アプリ ポータルを使用して、任意のモードでアプリケーションをテストできます。 マイ アプリ ポータルで [Sentry] タイルを選択すると、SP モードで構成されている場合は、アプリケーションのサインオン ページにリダイレクトされ、サインイン フローが開始されます。 IDP モードで構成されている場合は、SSO を設定した Sentry アプリケーションに自動的にサインインされます。 マイ アプリ ポータルの詳細については、「[マイ アプリ ポータルからアプリにサインインして開始する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sequr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Genea Access Control を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sequr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Genea Access Control の間でシングル サインオンを構成する方法について説明します。

この記事では、Genea Access Control と Microsoft Entra ID を統合する方法について説明します。 Genea Access Control と Microsoft Entra ID を統合すると、次のことができます。

- Genea Access Control にアクセスできる Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Genea Access Control に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Genea Access Controls でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Genea Access Control は、**SP および IDP** によって開始される SSO をサポートします。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Genea Access Control の追加

Microsoft Entra ID への Genea Access Control の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Genea Access Control を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Genea Access Control**」と入力します。
4. 結果パネルから **Genea Access Control** を選択して、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Genea Access Control の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Genea Access Control に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Genea Access Control の関連ユーザーとの間にリンク関係を確立する必要があります。

Genea Access Control に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Genea Access Control SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Genea Access Controlのテストユーザーの作成** - Microsoft Entraでのユーザー表現にリンクされた、Genea Access ControlでB.Simonに対応するユーザーを作成します。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Geneaアクセスコントロール**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **SAML の基本構成** セクションで、アプリケーションを **IDP** 開始モードを使用して構成する場合、次の手順を実行します。

    [**識別子** テキスト ボックスに、URL: `https://login.sequr.io` を入力します。
6. **追加の URL を設定** し、次の手順を実行します。アプリケーションを **SP** 開始モードで構成する場合は、次の手順に従ってください。

    ある。 [**サインオン URL** テキスト ボックスに、URL: `https://login.sequr.io` を入力します。

    b。 [ **リレー状態** ] ボックスには、この値が表示されます。これについては、この記事の後半で説明します。
7. [SAML **を使用して単一 Sign-On を設定する**] ページの [**SAML 署名証明書の**] セクションで、[ ダウンロード] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Genea Access Control** の設定]セクションで、要件に従って 1 つ以上の適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Genea Access Control SSO の構成

1. 別の Web ブラウザー ウィンドウで、Genea Access Control 企業サイトに管理者としてサインインします。
2. 左側のナビゲーション パネルから **[統合** ] を選択します。

    [Image: スクリーンショットは、ナビゲーション パネルから選択された統合を示しています。]
3. **[シングル サインオン]** セクションまで下方向にスクロールして、**[管理]** を選択します。

    [Image: スクリーンショットは、[管理] ボタンが選択された [シングル サインオン] セクションを示しています。]
4. [**シングル サインオン** の管理] セクションで、次の手順に従います。

    [Image: スクリーンショットは、説明されている値を入力できる [単一 Sign-On の管理] セクションを示しています。]

    ある。 **ID プロバイダーの単一 Sign-On URL** テキストボックスに、先にコピーした **ログイン URL** 値を貼り付けます。

    b。 ダウンロードした**証明書**ファイルをドラッグ アンド ドロップするか、証明書の内容を手動で入力します。

    c. 構成を保存すると、リレー状態の値が生成されます。 **リレー状態** の値をコピーし、**Basic SAML Configuration** セクションの **リレーステート** テキストボックスに貼り付けます。

    d. **[保存]** を選択します。

#### Genea Access Control テスト ユーザーの作成

このセクションでは、Genea Access Control で Britta Simon というユーザーを作成します。 genea Access Control クライアント サポート チーム  と連携して、Genea Access Control プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる Genea Access Control のサインオン URL にリダイレクトされます。
- Genea Access Control のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Genea Access Control に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Genea Access Control] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Genea Access Control に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。

### 1 つの Genea ポータルでの複数の Microsoft Entra インスタンス

- Genea は複数の Microsoft Entra インスタンスをサポートしていますか?

    はい。複数の Microsoft Entra インスタンスと接続して、ユーザー グループが有効になっている 1 つの Genea ポータルに接続できます。 留意すべき必須の考慮事項を次に示します。

    1. 外部 ID マッピング: 外部 ID がユーザー プロビジョニングのオブジェクト ID にマップされていることを確認します。それ以外の場合、ユーザーはアクセスを失う可能性があります。 マッピングを更新するには、Microsoft Entra にサインインし、Enterprise Applications &gt; Genea SCIM Application &gt; Provisioning &gt; Edit Provisioning &gt; Mappings &gt; Edit User Mappings に移動し、externalId マッピングを mailNickname から objectId に変更します。
    2. 一意のグループ名: Genea では、重複するユーザー グループ名はサポートされていません。 潜在的なエラーを回避するために、各 Microsoft Entra インスタンスで個別のグループ名が使用されていることを確認します。
    3. 単一の Microsoft Entra インスタンスへの移行: 複数の Microsoft Entra インスタンスから 1 つのインスタンスに移行する場合は、明確な移行計画を立てる必要があります。 この移行を内部で管理するか、Genea と協力して、サービスの中断なしにスムーズな移行を実現できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/serenity-connect-tutorial"} -->
## セレニティコネクトを構成します。 Microsoft Entra ID を使用したシングルサインオン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/serenity-connect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Serenity Connect の間でシングル サインオンを構成する方法について説明します。

この記事では、セレニティ コネクトと Microsoft Entra ID を統合する方法について説明します。 Serenity Connect を Microsoft Entra ID と統合すると、次のことができます。

- Serenity Connect にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Serenity Connect に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Serenity Connect でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Serenity Connect では、**SP** Initiated SSO がサポートされます

### ギャラリーからの Serenity Connect の追加

Microsoft Entra ID への Serenity Connect の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Serenity Connect を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Serenity Connect**」と入力します。
4. 結果パネルから **[Serenity Connect]** を選び、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Serenity Connect 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Serenity Connect に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Serenity Connect の関連ユーザーとの間にリンク関係を確立する必要があります。

Serenity Connect に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Serenity Connect の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Serenity Connect のテスト ユーザーの作成** - Serenity Connect で B.Simon に対応するユーザーを作成し、Microsoft Entra におけるユーザーの表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**Serenity Connect**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    えい。 **[識別子]** ボックスに、`urn:amazon:cognito:sp:us-east-2_<SerenityUniqueID>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://serenityconnect.auth.us-east-2.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://app.serenityconnect.com/sso-sign-in`

    注

    この値は実際の値ではありません。 この値を実際の識別子で更新します。 この値を取得するには、[Serenity Connect サポート チーム](mailto:hello@serenityconnect.com)にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Serenity Connect の SSO を構成する

**Serenity Connect** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Serenity Connect サポート チーム](mailto:hello@serenityconnect.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Serenity Connect テスト ユーザーを作成する

このセクションでは、Serenity Connect で B.Simon というユーザーを作成します。 [Serenity Connect サポート チーム](mailto:hello@serenityconnect.com)と連携して、Serenity Connect プラットフォームにこのユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる、セレニティコネクトのサインオン URL にリダイレクトされます。
- Serenity Connect のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [セレニティコネクト] タイルを選択すると、このオプションは、セレニティコネクトのサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/serraview-space-utilization-software-solutions-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Serraview Space Utilization Software Solutions を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/serraview-space-utilization-software-solutions-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Serraview Space Utilization Software Solutions の間でシングル サインオンを構成する方法について説明します。

この記事では、Serraview Space Utilization Software Solutions と Microsoft Entra ID を統合する方法について説明します。 Serraview Space Utilization Software Solutions と Microsoft Entra ID を統合すると、次のことができます。

- Serraview Space Utilization Software Solutions にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Serraview Space Utilization Software Solutions に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Serraview Space Utilization Software Solutions でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Serraview Space Utilization Software Solutions では、**SP と IDP によって開始される SSO** がサポートされます。

### ギャラリーからの Serraview Space Utilization Software Solutions の追加

Microsoft Entra ID への Serraview Space Utilization Software Solutions の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Serraview Space Utilization Software Solutions を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Serraview Space Utilization Software Solutions**」と入力します。
4. 結果のパネルから **[Serraview Space Utilization Software Solutions]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Serraview Space Utilization Software Solutions の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Serraview Space Utilization Software Solutions に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Serraview Space Utilization Software Solutions の関連ユーザーとの間にリンク関係を確立する必要があります。

Serraview Space Utilization Software Solutions に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Serraview Space Utilization Software Solutions SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Serraview Space Utilization Software Solutions のテスト ユーザーの作成 - B.Simon の代理ユーザーを Serraview Space Utilization Software Solutions 内で作成し、それを Microsoft Entra のユーザー表現にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Serraview Space Utilization Software Solutions**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:Serraview:<SERRAVIEW_IDENTIFIER>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.serraview.com/SAML/AssertionConsumerService.aspx`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.serraview.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Serraview Space Utilization Software Solutions クライアント サポート チーム](mailto:svprodops@serraview.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Serraview Space Utilization Software Solutions のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Serraview Space Utilization Software Solutions SSO の構成

**Serraview Space Utilization Software Solutions** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Serraview Space Utilization Software Solutions サポート チーム](mailto:svprodops@serraview.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Serraview Space Utilization Software Solutions のテスト ユーザーの作成

このセクションでは、Serraview Space Utilization Software Solutions で B.Simon というユーザーを作成します。 [Serraview Space Utilization Software Solutions サポート チーム](mailto:svprodops@serraview.com)と連携して、Serraview Space Utilization Software Solutions プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Serraview Space Utilization Software Solutions のサインオン URL にリダイレクトされます。
- Serraview Space Utilization Software Solutions のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Serraview Space Utilization Software Solutions に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Serraview Space Utilization Software Solutions] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Serraview Space Utilization Software Solutions に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/servicechannel-tutorial"} -->
## Microsoft Entra ID で ServiceChannel for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicechannel-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ServiceChannel 間にシングル サインオンを構成する方法について学習します。

この記事では、ServiceChannel と Microsoft Entra ID を統合する方法について説明します。 ServiceChannel を Microsoft Entra ID と統合すると、次のことができます。

- ServiceChannel にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、ServiceChannel に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な ServiceChannel のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ServiceChannel では、 **IDP** Initiated SSO がサポートされます
- ServiceChannel では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの ServiceChannel の追加

Microsoft Entra ID への ServiceChannel の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ServiceChannel を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ServiceChannel**」と入力します。
4. 結果パネルから **ServiceChannel** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ServiceChannel 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ServiceChannel に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ServiceChannel の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を ServiceChannel と一緒に構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ServiceChannel SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ServiceChannel テストユーザーを作成** - B.Simon に対応するユーザーを ServiceChannel に作成し、Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ServiceChannel**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のように値を入力します。 `http://adfs.<domain>.com/adfs/service/trust`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer domain>.servicechannel.com/saml/acs`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 ここでは、識別子に一意の文字列値を使用することをお勧めします。 これらの値を取得するには [、ServiceChannel クライアント サポート チーム](https://servicechannel.zendesk.com/hc/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. ロール要求は事前構成されているため、構成する必要はありませんが、この [記事](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)を使用して Microsoft Entra ID で作成する必要があります。 クレームの詳細なガイダンスについては、 [ServiceChannel ガイドを参照してください](https://servicechannel.zendesk.com/hc/en-us) 。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **ServiceChannel のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ServiceChannel SSO の構成

**ServiceChannel** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [ServiceChannel サポート チーム](https://servicechannel.zendesk.com/hc/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ServiceChannel のテスト ユーザーの作成

アプリケーションでは、ジャストインタイムのユーザー プロビジョニングがサポートされ、認証後にユーザーがアプリケーションに自動的に作成されます。 完全なユーザー プロビジョニングについては、 [ServiceChannel サポート チーム](https://servicechannel.zendesk.com/hc/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ServiceChannel に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [ServiceChannel] タイルを選択すると、SSO を設定した ServiceChannel に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/servicely-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Servicely を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicely-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から Servicely に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Servicely ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Servicely](https://servicely.ai/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Servicely でユーザーを作成する。
- アクセスが不要になった場合は、Servicely のユーザーを削除します。
- Microsoft Entra ID と Servicely の間でユーザー属性の同期を維持する。
- Servicelyでグループとグループメンバーシップをプロビジョンする。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Servicelyのテナント。
- 管理者のアクセス許可がある Servicely のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Servicely の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Servicely を構成する

Servicely のサポートに連絡して、Microsoft Entra ID でのプロビジョニングをサポートするように Servicely を構成します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Servicely を追加する

Microsoft Entra アプリケーション ギャラリーから Servicely を追加して、Servicely へのプロビジョニングの管理を開始します。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Servicely への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Servicely の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Servicely]** を選択します。

    [Image: アプリケーションの一覧の Servicely リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Servicely テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Servicely に接続できることを確認します。 接続に失敗した場合は、Servicely アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Servicely に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Servicely のユーザー アカウントとの照合に使用されます。 [照合対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合、その属性に基づいたユーザーのフィルター処理を Servicely API がサポートしているか確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Servicely で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | externalId | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | タイムゾーン | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |
12. **[グループ] を選択します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Servicely に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Servicely のグループの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Servicely で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/servicenow-provisioning-tutorial"} -->
## ServiceNow を構成し、Microsoft Entra ID を使った自動ユーザー プロビジョニングに対応させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から ServiceNow に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法についてご確認ください。

この記事では、自動ユーザー プロビジョニングを構成するために ServiceNow と Microsoft Entra ID の両方で実行する手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使って、[ServiceNow](https://www.servicenow.com) に対するユーザーとグループのプロビジョニングとプロビジョニング解除が自動的に行われます。

Microsoft Entra の自動ユーザー プロビジョニング サービスについて詳しくは、[Microsoft Entra ID での SaaS アプリケーションのユーザーのプロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に関する記事を参照してください。

### サポートされている機能

- ServiceNow でユーザーを作成する。
- アクセスが不要になったユーザーを ServiceNow で削除する。
- Microsoft Entra ID と ServiceNow の間でユーザー属性の同期を維持する。
- ServiceNow でグループとグループ メンバーシップをプロビジョニングする。
- ServiceNow への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-tutorial)を許可する (推奨)。
- 基本認証がサポートされています。

ServiceNow は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Calgary 以降の [ServiceNow インスタンス](https://www.servicenow.com)。
- Helsinki 以降の [ServiceNow Express インスタンス](https://www.servicenow.com)。
- 管理者ロールを持つ ServiceNow のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- [Microsoft Entra ID と ServiceNow の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように ServiceNow を構成する

1. ServiceNow インスタンス名を指定します。 インスタンス名は、ServiceNow にアクセスするために使用する URL で確認できます。 次の例では、インスタンス名は **dev35214** です。

    [Image: ServiceNow インスタンスを示すスクリーンショット。]
2. ServiceNow で管理者の資格情報を取得します。 ServiceNow でユーザー プロファイルに移動し、ユーザーに管理者ロールが割り当てられていることを確認します。

    [Image: ServiceNow 管理者ロールを示すスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから ServiceNow を追加する

Microsoft Entra アプリケーション ギャラリーから ServiceNow を追加して、ServiceNow へのプロビジョニングの管理を開始します。 シングル サインオン (SSO) のために ServiceNow を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、統合をテストするときは、別のアプリを作成することをお勧めします。 [ギャラリーからアプリケーションを追加する方法の詳細をご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5:ServiceNow への自動ユーザー プロビジョニングを構成する

このセクションでは、TestApp でユーザーとグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。 Microsoft Entra ID では、ユーザーとグループの割り当てに基づいて構成を行うことができます。

#### Microsoft Entra ID で ServiceNow の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ウィンドウを示すスクリーンショット。]
3. アプリケーションの一覧で、 **[ServiceNow]** を選択します。
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、ServiceNow テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が ServiceNow に接続できることを確認します。 接続に失敗した場合は、ServiceNow アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から ServiceNow に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で ServiceNow のユーザー アカウントとの照合に使用されます。

    [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、ServiceNow API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。

    **[保存]** ボタンをクリックして変更をコミットします。
12. **[グループ] を選択します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から ServiceNow に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で ServiceNow のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### トラブルシューティングのヒント

- ServiceNow の特定の属性 (**Department** や **Location** など) をプロビジョニングする場合は、ServiceNow の参照テーブルに値が既に存在する必要があります。 そうでない場合は、 **InvalidLookupReference** エラーが発生します。

    たとえば、ServiceNow の特定のテーブルに 2 つの場所 (Seattle、Los Angeles) と 3 つの部門 (Sales、Finance、Marketing) があるとします。 部門が "Sales" で場所が "Seattle" であるユーザーをプロビジョニングしようとすると、そのユーザーは正常にプロビジョニングされます。 部門が "Sales" で場所が "LA" のユーザーをプロビジョニングしようとすると、そのユーザーはプロビジョニングされません。 ServiceNow の参照テーブルに場所 "LA" を追加するか、ServiceNow の形式に合わせて Microsoft Entra ID のユーザー属性を更新する必要があります。
- **EntryJoiningPropertyValueIsMissing** エラーが発生した場合は、[属性マッピング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を確認して、一致する属性を特定します。 プロビジョニングしようとしているユーザーまたはグループに、この値が存在する必要があります。
- 要件や制限事項 (ユーザーの国番号を指定する際の形式など) については、[ServiceNow SOAP API](https://docs.servicenow.com/bundle/rome-application-development/page/integrate/web-services-apis/reference/r_DirectWebServiceAPIFunctions.html) を確認してください。
- プロビジョニング要求は、既定では https://{your-instance-name}.service-now.com/{table-name} に送信されます。 カスタム テナント URL が必要な場合は、URL 全体をインスタンス名として指定できます。
- **ServiceNowInstanceInvalid** エラーは、ServiceNow インスタンスとの通信に問題があることを示しています。 このエラーのテキストを次に示します。

    `Details: Your ServiceNow instance name appears to be invalid.  Please provide a current ServiceNow administrative user name and          password along with the name of a valid ServiceNow instance.`

    テスト接続の問題が発生している場合は、ServiceNow の次の設定で **[No](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/いいえ)** を選択してみてください。

    - **[System Security](システム セキュリティ)**&gt;**[High security settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/高セキュリティ設定)**&gt;**[Require basic authentication for incoming SCHEMA requests](受信 SCHEMA 要求に対して基本認証を要求する)**
    - **[System Properties](システム プロパティ)**&gt;**[Web Services](Web サービス)**&gt;**[Require basic authorization for incoming SOAP requests](受信 SOAP 要求に対して基本認証を要求する)**

        [Image: SOAP 要求を承認するためのオプションを示すスクリーンショット。]

    引き続き問題が解決しない場合は、ServiceNow サポートに連絡し、トラブルシューティングに役立てるために SOAP デバッグを有効にするように依頼してください。
- 現在、Microsoft Entra プロビジョニング サービスは特定の [IP 範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#ip-ranges)で動作します。 必要に応じて、他の IP 範囲を制限し、これらの特定の IP 範囲をアプリケーションの許可リストに追加できます。 この手法により、Microsoft Entra プロビジョニング サービスからアプリケーションへのトラフィック フローが可能になります。
- セルフホステッド ServiceNow インスタンスはサポートされていません。
- ServiceNow の *アクティブな* 属性の更新がプロビジョニングされると、 *locked\_out* が Azure プロビジョニング サービスにマップされていない場合でも、 *locked\_out* 属性も適宜更新されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/servicenow-tutorial"} -->
## Microsoft Entra ID で ServiceNow for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と ServiceNow の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、ServiceNow と Microsoft Entra ID を統合する方法について説明します。 ServiceNow を Microsoft Entra ID を統合すると、次のことができます。

- ServiceNow にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、ServiceNow に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

ServiceNow は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- ServiceNow でのシングル サインオン (SSO) が有効なサブスクリプション。
- ServiceNow では、Calgary、Kingston、London、Madrid、New York、Orlando、Paris、San Diego バージョン以降が ServiceNow のインスタンスまたはテナントでサポートされています。
- ServiceNow Express の場合は、Helsinki バージョン以降の ServiceNow Express のインスタンス。
- ServiceNow テナントでは、 [複数プロバイダー シングル サインオン プラグイン](https://docs.servicenow.com/bundle/washingtondc-platform-security/page/integrate/single-sign-on/concept/c_MultipleProviderSingleSignOn.html) が有効になっている必要があります。
- 自動構成のために、ServiceNow の Multi-Provider プラグインを有効にします。
- ServiceNow Agent (モバイル) アプリケーションをインストールするには、適切なストアに移動して ServiceNow Agent アプリケーションを検索します。 その後、ダウンロードします。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ServiceNow では、 **SP** Initiated SSO がサポートされます。
- ServiceNow では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-provisioning-tutorial)。
- ServiceNow Agent (モバイル) アプリケーションを Microsoft Entra ID で構成して SSO を有効にできます。 Android と iOS の両方のユーザーがサポートされます。 この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

### ギャラリーからの ServiceNow の追加

Microsoft Entra ID への ServiceNow の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ServiceNow を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「ServiceNow**」と入力します。
4. 結果パネルから **ServiceNow** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ServiceNow 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ServiceNow に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ServiceNow の関連ユーザーとの間にリンク関係を確立する必要があります。

ServiceNow に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. ユーザーがこの機能を使用できるように Microsoft Entra SSO を構成します。
    1. B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft Entra テスト ユーザーを作成します。
    2. B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます。
    3. ServiceNow Express の Microsoft Entra SSO を構成 して、ユーザーがこの機能を使用できるようにします。
2. ServiceNow を構成して、アプリケーション側で SSO 設定を構成します。
    1. ServiceNow で B.Simon に対応するテストユーザーを作成し、そのユーザーを Microsoft Entra のデータとリンクさせます。
    2. ServiceNow Express SSO を構成 して、アプリケーション側でシングル サインオン設定を構成します。
3. SSO をテスト して、構成が機能するかどうかを確認します。
4. ServiceNow Agent (Mobile) の SSO をテスト して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ServiceNow** アプリケーション統合ページに移動し、[**管理**] セクションを見つけます。 **[シングル サインオン]** を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] のペン アイコンを選択して設定を編集します。

    [Image: ペン アイコンが強調表示された [SAML を使用して単一 Sign-On を設定する] ページのスクリーンショット]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL] に**、次のいずれかの URL パターンを入力します。

    | サインオンURL |
    | --- |
    | `https://<instancename>.service-now.com/navpage.do` |
    | `https://<instance-name>.service-now.com/login_with_sso.do?glide_sso_id=<sys_id of the sso configuration>` |
    |  |

    注

    この記事の後半で説明する **ServiceNow の構成** セクションから、sys\_id値をコピーしてください。

    b。 **識別子 (エンティティ ID)** に、次のパターンを使用する URL を入力します。`https://<instance-name>.service-now.com`

    c. [ **応答 URL]** に、次のいずれかの URL パターンを入力します。

    | 返信 URL |
    | --- |
    | `https://<instancename>.service-now.com/navpage.do` |
    | `https://<instancename>.service-now.com/consumer.do` |
    |  |

    d. [ **ログアウト URL]** に、次のパターンを使用する URL を入力します。 `https://<instancename>.service-now.com/navpage.do`

    注

    識別子の値に "/" が追加されている場合は、手動で削除してください。

    注

    これらは実際の値ではありません。 これらの値は、実際のサインオン URL、応答 URL、ログアウト URL、識別子で更新する必要があります。これについては、後で説明します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を見つけます。

    [Image: [ダウンロード] が強調表示されている [SAML 署名証明書] セクションのスクリーンショット]

    a. コピー ボタンを選択して **アプリのフェデレーション メタデータ URL を**コピーし、メモ帳に貼り付けます。 この URL は、この記事の後半で使用します。

    b。 [ **ダウンロード** ] を選択して **証明書 (Base64)** をダウンロードし、証明書ファイルをコンピューターに保存します。
7. [ **ServiceNow のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: URL が強調表示されている [ServiceNow のセットアップ] セクションのスクリーンショット]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### ServiceNow Express 用に Microsoft Entra SSO を構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ServiceNow** アプリケーション統合ページに移動し、**シングル サインオンを選択します**。

    [Image: [シングル サインオン] が強調表示されている ServiceNow アプリケーション統合ページのスクリーンショット]
3. [ **シングル サインオン方法の選択** ] ダイアログ ボックスで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: SAML が強調表示されている [シングル サインオン方法の選択] のスクリーンショット]
4. [ **SAML でのシングル サインオンの設定** ] ページで、ペン アイコンを選択して [ **基本的な SAML 構成]** ダイアログ ボックスを開きます。

    [Image: [SAML でシングル サインオンを設定する] ページのスクリーンショット(ペン アイコンが強調表示されています)]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[サインオン URL] に**、次のいずれかの URL パターンを入力します。

    | サインオンURL |
    | --- |
    | `https://<instance-name>.service-now.com/login_with_sso.do?glide_sso_id=<sys_id of the sso configuration>` |
    | `https://<instancename>.service-now.com/consumer.do` |
    |  |

    b。 **[識別子 (エンティティ ID)]** に、次のパターンを使用する URL を入力します。`https://<instance-name>.service-now.com`

    c. [ **応答 URL]** に、次のいずれかの URL パターンを入力します。

    | 返信 URL |
    | --- |
    | `https://<instancename>.service-now.com/navpage.do` |
    | `https://<instancename>.service-now.com/consumer.do` |
    |  |

    d. [ **ログアウト URL]** に、次のパターンを使用する URL を入力します。 `https://<instancename>.service-now.com/navpage.do`

    注

    識別子の値に "/" が追加されている場合は、手動で削除してください。

    注

    これらは実際の値ではありません。 これらの値は、実際のサインオン URL、応答 URL、ログアウト URL、識別子で更新する必要があります。これについては、後で説明します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って、指定したオプションから **証明書 (Base64)** をダウンロードします。 それを自分のコンピューターに保存します。

    [Image: [ダウンロード] が強調表示されている [SAML 署名証明書] セクションのスクリーンショット]
7. Microsoft Entra ID では、SAML ベースの認証に対応するように ServiceNow を自動的に構成できます。 このサービスを有効にするには、[ **ServiceNow のセットアップ** ] セクションに移動し、[ **ステップ バイ ステップの表示** ] を選択して **[サインオンの構成** ] ウィンドウを開きます。
8. [ **サインオンの構成** ] フォームで、ServiceNow インスタンス名、管理者ユーザー名、管理者パスワードを入力します。 [ **今すぐ構成] を選択**します。 これを機能させるには、指定された管理者 **ユーザー** 名に ServiceNow でsecurity\_adminロールが割り当てられている必要があります。 それ以外の場合は、Microsoft Entra ID を SAML ID プロバイダーとして使用するように ServiceNow を手動 **で構成するには、[手動でシングル サインオンを構成する**] を選択します。 [クイック リファレンス] セクションから **、ログアウト URL、Microsoft Entra 識別子、およびログイン URL を** コピーします。

    [Image: [今すぐ構成] が強調表示されている [サインオンの構成] フォームのスクリーンショット]

### ServiceNow の構成

1. ServiceNow アプリケーションに管理者としてサインオンします。
2. 次の手順に従って、 **Integration - Multiple Provider シングル サインオン インストーラー** プラグインをアクティブ化します。

    a. 左側のウィンドウで、検索ボックスから **[システム定義]** セクションを検索し、[ **プラグイン**] を選択します。

    [Image: [システム定義] セクションのスクリーンショット。[システム定義] と [プラグインのアクティブ] が強調表示されています

    b。 **統合 - 複数プロバイダー シングル サインオン インストーラー**を検索し、**インストール**して**アクティブ化**します。

    [Image: システムプラグインのページのスクリーンショット、[統合 - 複数プロバイダー単一 Sign-On インストーラー] が強調表示されています]
3. 左側のウィンドウで、検索バーから **[マルチプロバイダー SSO**] セクションを検索し、[**管理**] で **[プロパティ**] を選択します。

    [Image: [マルチプロバイダー SSO] セクションのスクリーンショット。[マルチプロバイダー SSO] と [プロパティ] が強調表示されている]
4. [ **複数プロバイダー SSO のプロパティ** ] ダイアログ ボックスで、次の手順を実行します。

    [Image: [複数プロバイダー SSO のプロパティ] ダイアログ ボックスの []] のスクリーンショット

    - [ **複数のプロバイダーの SSO を有効にする]** で、[ **はい**] を選択します。
    - **すべての ID プロバイダーからユーザー テーブルへのユーザーの自動インポートを有効にするには**、[**はい**] を選択します。
    - **[複数プロバイダー SSO 統合のデバッグ ログを有効にする**] で、[**はい**] を選択します。
    - [. **..] というユーザー テーブルのフィールド**に電子メールを入力 **します**。
    - **[保存] を選択します**。
5. ServiceNow の構成は、自動または手動で行うことができます。 ServiceNow を自動的に構成するには、これらの手順に従います。

    1. **ServiceNow** のシングル サインオン ページに戻ります。
    2. ServiceNow には、ワンセレクト構成サービスが用意されています。 このサービスを有効にするには、[ **ServiceNow 構成]** セクションに移動し、[ **ServiceNow の構成** ] を選択して **[サインオンの構成** ] ウィンドウを開きます。

        [Image: [ステップ バイ ステップの手順を表示する] が強調表示された [ServiceNow のセットアップ] のスクリーンショット]
    3. [ **サインオンの構成** ] フォームで、ServiceNow インスタンス名、管理者ユーザー名、管理者パスワードを入力します。 [ **今すぐ構成] を選択**します。 指定された管理者ユーザー名を機能させるには、ServiceNow で **セキュリティ管理者** ロールが割り当てられている必要があります。 それ以外の場合は、Microsoft Entra ID を SAML ID プロバイダーとして使用するように ServiceNow を手動 **で構成するには、[手動でシングル サインオンを構成する**] を選択します。 [クイック リファレンス] セクションから ** 、Sign-Out URL、SAML エンティティ ID、SAML シングル サインオン サービス URL を** コピーします。

        [Image: [今すぐ構成] が強調表示されている [サインオンの構成] フォームのスクリーンショット]
    4. ServiceNow アプリケーションに管理者としてサインオンします。

        - 自動構成では、必要なすべての設定が **ServiceNow** 側で構成されますが、 **X.509 証明書** は既定では有効ではなく、 **単一 Sign-On スクリプト** の値を **MultiSSOv2\_SAML2\_custom**として指定します。 ServiceNow でご使用の ID プロバイダーに手動でマップする必要があります。 次の手順に従います。

            1. 左側のウィンドウで、検索ボックスから **[マルチプロバイダー SSO** ] セクションを検索し、[ **ID プロバイダー]** を選択します。

                [Image: マルチプロバイダー SSO セクションのスクリーンショット、]
            2. 自動的に生成された ID プロバイダーを選択します。

                [Image: ID プロバイダーが表示されているスクリーンショット、自動生成された ID プロバイダーが強調表示されています]
            3. [ **ID プロバイダー** ] セクションで、次の手順を実行します。

                [Image: [Identity Provider](ID プロバイダー) セクション]の のスクリーンショット

                a. 画面の上部にある灰色のバーを右クリックし、[**sys\_idコピー**] を選択し、[**基本的な SAML 構成**] セクションの **[サインオン URL**] にこの値を使用します。

                b。 **[名前]** に、構成の名前 (**Microsoft Azure Federated シングル サインオン**など) を入力します。

                c. **ServiceNow ホームページ**の値をコピーし、**ServiceNow の [基本的な SAML 構成]** セクションの **[サインオン URL**] に貼り付けます。

                注

                ServiceNow インスタンスのホーム ページは、 **ServiceNow テナント URL** と **/navpage.do** (例: `https://fabrikam.service-now.com/navpage.do`) を連結したものです。

                d. **エンティティ ID/発行者**の値をコピーし、**ServiceNow の [基本的な SAML 構成**] セクションの **[識別子**] に貼り付けます。

                e. **NameID ポリシー**が値`urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified`設定されていることを確認します。

                f. **[詳細設定]** を選択し、**[単一 Sign-On スクリプト]** の値を **MultiSSOv2\_SAML2\_custom** として指定します。
            4. **[X.509 証明書**] セクションまで下にスクロールし、[**編集]** を選択します。

                [Image: [編集] が強調表示されている [X.509 Certificate](X.509 証明書) セクション]
            5. 証明書を選択し、右矢印のアイコンを選択して証明書を追加します。

                [Image: 証明書と右矢印アイコンが強調表示されているコレクションのスクリーンショット]
            6. **[保存] を選択します**。
            7. ページの右上隅にある [ **テスト接続**] を選択します。

                [Image: [接続テストが強調表示されたページのスクリーンショット]] [プラグインのアクティブ化]

                注

                テスト接続が失敗し、この接続をアクティブ化できない場合、ServiceNow はオーバーライド スイッチを提供します。 **Sys\_properties.LIST**を**検索ナビゲーション**に入力すると、システムプロパティの新しいページが開きます。 ここでは、名前が **glide.authenticate.multisso.test.connection.mandatory** で **データ型** が **True/False** の新しいプロパティを作成し、 **値** を **False** に設定する必要があります。

>
> [Image: [テスト接続] ページの []スクリーンショット
            8. 自分の資格情報の入力を求められたら、入力します。 次のページが表示されます。 **SSO ログアウト テスト結果**エラーが予想されます。 エラーを無視し、[ **アクティブ化**] を選択します。

                [Image: [資格情報] ページの] のスクリーンショット
6. **ServiceNow** を手動で構成するには、次の手順に従います。

    1. ServiceNow アプリケーションに管理者としてサインオンします。
    2. 左側のウィンドウで、[ **ID プロバイダー] を選択します**。

        [Image: シングル サインオンの構成][Identity Providers が強調表示された Multi-Provider SSO のスクリーンショット]
    3. [ **ID プロバイダー** ] ダイアログ ボックスで、[ **新規**] を選択します。

        [Image: [新規] が強調表示された [ID プロバイダー] ダイアログ ボックスのスクリーンショット]
    4. [ **ID プロバイダー** ] ダイアログ ボックスで、[SAML] を選択 **します**。

        [Image: SAML が強調表示されている [ID プロバイダー] ダイアログ ボックスのスクリーンショット]
    5. **ID プロバイダー メタデータのインポート**で、次の手順を実行します。

        [Image: URL とインポートが強調表示されている Id プロバイダー メタデータのインポートのスクリーンショット]

        1. コピーした **アプリのフェデレーション メタデータ URL を** 入力します。
        2. [ **インポート] を選択します**。
    6. IdP メタデータ URL が読み取られ、すべてのフィールド情報が設定されます。

        [Image: [Identity Provider]

        a. 画面の上部にある灰色のバーを右クリックし、[**sys\_idコピー**] を選択し、[**基本的な SAML 構成**] セクションの **[サインオン URL**] にこの値を使用します。

        b。 **[名前]** に、構成の名前 (**Microsoft Azure Federated シングル サインオン**など) を入力します。

        c. **ServiceNow ホームページ**の値をコピーします。 **ServiceNow の [基本的な SAML 構成]** セクションの **[サインオン URL**] に貼り付けます。

        注

        ServiceNow インスタンスのホーム ページは、 **ServiceNow テナント URL** と **/navpage.do** (例: `https://fabrikam.service-now.com/navpage.do`) を連結したものです。

        d. **エンティティ ID/発行者**の値をコピーします。 **ServiceNow の [基本的な SAML 構成]** セクションの **[識別子**] に貼り付けます。

        e. **NameID ポリシー**が値`urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified`設定されていることを確認します。

        f. [ **詳細設定] を選択します**。 **[ユーザー フィールド] に**電子メールを入力**します**。

        注

        SAML トークンの一意の識別子として Microsoft Entra ユーザー ID (ユーザーのプリンシパル名) かメール アドレスを出力するように Microsoft Entra ID を構成できます。 これを行うには、Azure portal の **ServiceNow**&gt;**Attributes**&gt;**シングルサインオン** セクションに移動して、目的のフィールドを **nameidentifier** 属性にマッピングします。 Microsoft Entra ID に格納される選択した属性 (ユーザー プリンシパル名など) の値と、ServiceNow に格納される入力したフィールド (user\_name など) の値が一致している必要があります。

        g. ページの右上隅にある **[テスト接続** ] を選択します。

        注

        テスト接続が失敗し、この接続をアクティブ化できない場合、ServiceNow はオーバーライド スイッチを提供します。 **Sys\_properties.LIST**を**検索ナビゲーション**に入力すると、システムプロパティの新しいページが開きます。 ここでは、名前が **glide.authenticate.multisso.test.connection.mandatory** で **データ型** が **True/False** の新しいプロパティを作成し、 **値** を **False** に設定する必要があります。

>
> [Image: テスト接続の]スクリーンショット

        h. 自分の資格情報の入力を求められたら、入力します。 次のページが表示されます。 **SSO ログアウト テスト結果**エラーが予想されます。 エラーを無視し、[ **アクティブ化**] を選択します。

        [Image: credentials]

#### ServiceNow テスト ユーザーの作成

このセクションの目的は、ServiceNow 内で B.Simon というユーザーを作成することです。 ServiceNow では、自動ユーザー プロビジョニングがサポートされており、既定で有効になっています。

注

ユーザーを手動で作成する必要がある場合 [は、ServiceNow クライアント サポート チーム](https://support.servicenow.com/now)にお問い合わせください。

#### ServiceNow Express SSO の構成

1. ServiceNow Express アプリケーションに管理者としてサインオンします。
2. 左側のウィンドウで、[ **シングル サインオン**] を選択します。

    [Image: [シングル Sign-On] が強調表示されている ServiceNow Express アプリケーションのスクリーンショット]
3. [ **シングル サインオン** ] ダイアログ ボックスで、右上にある構成アイコンを選択し、次のプロパティを設定します。

    [Image: 「単一 Sign-On」ダイアログ ボックスのスクリーンショット]

    a. [ **複数のプロバイダーの SSO を有効にする]** を右に切り替えます。

    b。 **[Enable debug logging for the multiple provider SSO integration]\(複数プロバイダー SSO 統合のデバッグ ログを有効にする**\) を右側に切り替えます。

    c. ユーザーテーブルのフィールドに**user\_name**と入力してください**...**。
4. [ **シングル サインオン** ] ダイアログ ボックスで、[ **新しい証明書の追加**] を選択します。

    [Image: [新しい証明書の追加] が強調表示されている [シングル Sign-On] ダイアログ ボックス]
5. [ **X.509 証明書** ] ダイアログ ボックスで、次の手順を実行します。

    [Image: [X.509 証明書] ダイアログ ボックスの]] のスクリーンショット

    a. **[名前]** に、構成の名前を入力します (例: **TestSAML2.0**)。

    b。 **[アクティブ] を選択します**。

    c. **形式**で、**PEM** を選択します。

    d. **[種類]** で、[**信頼ストア証明書**] を選択します。

    e. Azure portal からダウンロードした `Base64` エンコード証明書をメモ帳で開きます。 その内容をクリップボードにコピーし、[ **PEM 証明書** ] テキスト ボックスに貼り付けます。

    f. **[更新] を選択する**
6. [ **シングル サインオン** ] ダイアログ ボックスで、[ **新しい IdP の追加]** を選択します。

    [Image: [新しい IdP の追加] が強調表示された [シングル Sign-On] ダイアログ ボックス]
7. [ **新しい ID プロバイダーの追加** ] ダイアログ ボックスの [ **ID プロバイダーの構成**] で、次の手順を実行します。

    [Image: [新しい ID プロバイダーの追加] ダイアログ ボックスの]] のスクリーンショット

    a. **[名前]** に、構成の名前を入力します (例: **SAML 2.0**)。

    b。 **[ID プロバイダーの URL]** に、コピーした ID プロバイダー ID の値を貼り付けます。

    c. **ID プロバイダーの AuthnRequest** の場合は、コピーした認証要求 URL の値を貼り付けます。

    d. **ID プロバイダーの SingleLogoutRequest** の場合は、コピーしたログアウト URL の値を貼り付けます。

    e. **[ID プロバイダー証明書**] で、前の手順で作成した証明書を選択します。
8. [ **詳細設定] を選択します**。 [ **追加の ID プロバイダーのプロパティ] で**、次の手順を実行します。

    [Image: [詳細設定] が強調表示された [新しい ID プロバイダーの追加] ダイアログ ボックス]

    a. **IDP の SingleLogoutRequest のプロトコル バインド**の場合は、**urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect** を入力します。

    b。 **NameID Policy** には、「**urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified**」と入力します。

    c. **AuthnContextClassRef メソッド**の場合は、「`http://schemas.microsoft.com/ws/2008/06/identity/authenticationmethod/password`」と入力します。

    d. **[Create an AuthnContextClass]\(AuthnContextClass の作成**\) で、オフ (非選択) に切り替えます。
9. [ **その他のサービス プロバイダーのプロパティ] で**、次の手順を実行します。

    [Image: さまざまなプロパティが強調表示されている [新しい ID プロバイダーの追加] ダイアログ ボックス]

    a. **ServiceNow ホームページ**の場合は、ServiceNow インスタンスホームページの URL を入力します。

    注

    ServiceNow インスタンスホームページは、 **ServiceNow テナント URL** と **/navpage.do** (例: `https://fabrikam.service-now.com/navpage.do`) を連結したものです。

    b。 **[エンティティ ID/ 発行者]** に、ServiceNow テナントの URL を入力します。

    c. **[対象ユーザー URI**] に、ServiceNow テナントの URL を入力します。

    d. **[クロック スキュー**] に「**60」と入力します**。

    e. **[ユーザー フィールド] に** **電子メール**を入力します。

    注

    SAML トークンの一意の識別子として Microsoft Entra ユーザー ID (ユーザーのプリンシパル名) かメール アドレスを出力するように Microsoft Entra ID を構成できます。 これを行うには、Azure portal の **ServiceNow**&gt;**Attributes**&gt;**シングルサインオン** セクションに移動して、目的のフィールドを **nameidentifier** 属性にマッピングします。 Microsoft Entra ID に格納される選択した属性 (ユーザー プリンシパル名など) の値と、ServiceNow に格納される入力したフィールド (user\_name など) の値が一致している必要があります。

    f. **[保存] を選択します**。

### SSO のテスト

アクセス パネルで [ServiceNow] タイルを選択すると、SSO を設定した ServiceNow に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

### ServiceNow Agent (モバイル) の SSO をテストする

1. **ServiceNow Agent (Mobile)** アプリケーションを開き、次の手順を実行します。

    b。 ServiceNow インスタンスのアドレス、ニックネームを入力し、[ **保存してログイン]** を選択します。

    [Image: [続行] が強調表示されている [インスタンスの追加] ページのスクリーンショット]

    c. [ログイン] ページ **で** 、次の手順を実行します。

    [Image: [外部ログインの使用] が強調表示されている [ログイン] ページのスクリーンショット]

    - など、B.simon@contoso.comを入力します。
    - [ **外部ログインを使用]** を選択します。 ユーザーはサインイン用の Microsoft Entra ID ページにリダイレクトされます。
    - 資格情報を入力します。 任意のサードパーティの認証またはその他の有効になっているセキュリティ機能がある場合、ユーザーはそれに対応する必要があります。 アプリケーションの **ホーム ページ** が表示されます。

        [Image: アプリケーションのホーム ページのスクリーンショット]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/servicessosafe-tutorial"} -->
## Microsoft Entra ID で SoSafe for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicessosafe-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SoSafe 間にシングル サインオンを構成する方法について説明します。

この記事では、SoSafe と Microsoft Entra ID を統合する方法について説明します。 SoSafe を Microsoft Entra ID を統合すると、次のことができます:

- SoSafe にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SoSafe に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な SoSafe サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SoSafe では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- SoSafe では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- SoSafe では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sosafe-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの SoSafe の追加

Microsoft Entra ID への SoSafe の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに 4me を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「SoSafe」と入力します。
4. 結果のパネルから [SoSafe] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SoSafe 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SoSafe に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SoSafe の関連ユーザーとの間にリンク関係を確立する必要があります。

SoSafe に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SoSafe の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SoSafe テストユーザーの作成 - B.Simon に対応する SoSafe ユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt; SoSafe アプリケーション統合にアクセスする
3. [ **シングル サインオン] を選択します**。
4. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
5. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
6. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
7. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.sosafe.de/v1/auth/saml/login/<TENANT_ID>`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには、[SoSafe クライアント サポート チーム](mailto:support@sosafe.de)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [SoSafe のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SoSafe の SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として SoSafe Web サイトにサインインします。
2. **拡張データ**を選択し、次のページで次の手順を実行します。

    [Image: 拡張データの SAML ページ]

    ある。 **[Azure Tenant ID](Azure テナント ID)** ボックスに、Azure portal からテナント ID の値を貼り付けます。

    b。 ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[証明書]** テキストボックスに貼り付けます。

    c. [ **ログイン URL** ] ボックスに、コピーした **ログイン URL** の値を貼り付けます。

    d. **[Microsoft Entra 識別子]** ボックスに、コピーした**エンティティ ID** の値を貼り付けます。

    え **[Logout URL] (ログアウト URL)** ボックスに、コピーした**ログアウト URL** の値を貼り付けます。

    f. **[保存] を選択する**

#### SoSafe のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを SoSafe に作成します。 SoSafe では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 SoSafe にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

SoSafe では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sosafe-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SoSafe サインオン URL にリダイレクトされます。
- SoSafe のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SoSafe に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SoSafe] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SoSafe に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/servusconnect-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ServusConnect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servusconnect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ServusConnect 間にシングル サインオンを構成する方法について学習します。

この記事では、ServusConnect と Microsoft Entra ID を統合する方法について説明します。 ServusConnect では、Microsoft Entra ID を使用してユーザー アクセスを管理し、ServusConnect メンテナンス操作プラットフォームでシングル サインオンを有効にします。 既存の ServusConnect サブスクリプションが必要です。

ServusConnect を Microsoft Entra ID と統合すると、次のことができます。

- ServusConnect にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して、ServusConnect に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

自分の Azure 環境で ServusConnect 用の Azure AD シングル サインオンを構成してテストします。 ServusConnect では、**SP** Initiated SSO と **Just In Time** ユーザー プロビジョニングがサポートされます。

### [前提条件]

Microsoft Entra ID を ServusConnect と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ServusConnect のシングル サインオン (SSO) 対応サブスクリプション。 ServusConnect を持っていない場合は、[詳細を確認し、デモを依頼](https://www.netvendor.com/servusconnect/)できます。

### アプリケーションを追加してユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから ServusConnect アプリケーションを追加する必要があります。 また、アプリケーションに割り当てるユーザー アカウントも必要です。 組織へのロールアウトを開始する前に、まずテスト ユーザーを作成して割り当てることを検討してください。

#### Microsoft Entra ギャラリーから ServusConnect を追加する

Microsoft Entra アプリケーション ギャラリーから ServusConnect を追加して、ServusConnect でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra ユーザーを作成または割り当てる

「[ユーザー アカウントを作成して割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)」のガイドラインに従ってユーザーを作成し (必要な場合)、ServusConnect エンタープライズ アプリケーションに 1 人以上のユーザーを割り当てます。 シングル サインオンを使用して ServusConnect にアクセスできるのは、アプリケーションに割り当てたユーザーだけです。 個々のユーザーまたはグループ全体を割り当てることができます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ServusConnect**&gt;**シングル サインオン**にナビゲートします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、値「`urn:amazon:cognito:sp:us-east-1_rlgU6e3y5`」を入力します。

    b。 **[応答 URL]** ボックスに、URL「`https://login.servusconnect.com/saml2/idpresponse`」を入力します。

    c. **[サインオン URL]** ボックスに、URL「`https://app.servusconnect.com`」を入力します。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### ServusConnect SSO の構成

**ServusConnect** アプリケーションでシングル サインオンを構成するには、Azure portal からダウンロードした**フェデレーション メタデータ XML** ファイルを [ServusConnect サポート チーム](mailto:support@servusconnect.com)に送信する必要があります。 ServusConnect サポート チームにメールを送信する場合は、次の情報を入力してください。

1. フェデレーション メタデータ XML ファイル。
2. Microsoft Entra アカウントから SSO を介して接続するすべての電子メール ドメインのリスト。

ServusConnect サポート チームは、SAML SSO 接続を完了し、準備ができたら通知します。

### ServusConnect ユーザー アカウント

ServusConnect ユーザー アカウントは、ユーザーが最初に SSO を試行する前にプロビジョニングするか、SSO の試行の結果として "Just-In-Time" でプロビジョニングすることができます。 ただし、2 つの方法では、ユーザーがアクセスできる内容が異なります。

#### 事前プロビジョニング済みのユーザー

SSO ログインと一致する電子メール アドレスを持つ ServusConnect に存在するユーザーには、SSO 操作後に ServusConnect へのアクセス権が自動的に付与されます。

#### Just-In-Time ユーザーと Waiting Room

ServusConnect にまだ存在しないユーザーには、SSO ログインと一致する電子メールを使用して作成されたユーザー アカウントがあります。 ただし、これらのユーザーは、ServusConnect に直接アクセスするのではなく、"Waiting Room" に配置されます。 これらのユーザーは、SSO によって Waiting Room の通過を許可される前に、正しいアクセス レベルとプロパティ レベルのアクセス権がプロビジョニングされる必要があります。

適切なアクセス権を持つ既存の ServusConnect ユーザーは、作業しているサイト/プロパティの ServusConnect の [管理] ページにある ServusConnect の "新しいユーザー" フォームに入力できます。 これが完了したら、ServusConnect サポート チームが要求を処理し、メールでユーザーに通知します。 その後、ユーザーは SSO を使用してサインインし、ServusConnect にアクセスできます。

### SSO をテストする

次の方法のいずれかを使用して、Microsoft Entra のシングル サインオン構成をテストすることができます。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる ServusConnect のサインオン URL にリダイレクトされます。
- [ServusConnect のサインオン URL](https://app.servusconnect.com/) に直接移動し、そこからログイン フローを開始します。 以下の「**SSO を使用したサインオン**」を参照してください。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ServusConnect] タイルを選択すると、このオプションは ServusConnect のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。

### SSO を使用したサインオン

サインオンするには、次の手順を実行します。

1. [ServusConnect のサインオン URL](https://app.servusconnect.com/) にアクセスします。
2. メール アドレスを入力し、**[続行]** をクリックします。 メール ドメインは、構成時に ServusConnect と共有したドメインと一致している必要があることに注意してください (次のスクリーンショットをご覧ください)。

    [Image: サインオン画面にメール アドレスを入力する方法を示すスクリーンショット。]
3. ドメインが Microsoft Entra ID に対する SSO 用に適切に構成されている場合は、[ **Microsoft でのログイン]** ボタンが表示されます。 (次のスクリーンショットをご覧ください)。

    [Image: [Log In with Microsoft] (Microsoft アカウントでログイン) ボタンを示すスクリーンショット。]
4. [ **Microsoft でログイン** ] ボタンを選択すると、標準の Microsoft ログイン画面に移動します。 ログインに成功すると、ServusConnect にリダイレクトされます。
5. SSO 認証に一致する既存の ServusConnect ユーザーが存在する場合は、すぐにログインします。 それ以外の場合は、下図のように **待機室** に入ってください。

    [Image: ServusConnect Waiting Room を示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/settlingmusic-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に「Settling music」を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/settlingmusic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と楽楽精算の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、落ち着く音楽を Microsoft Entra ID と統合する方法について説明します。 楽楽精算を Microsoft Entra ID を統合すると、次のことができます:

- 楽楽精算にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して楽楽精算に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 楽楽精算でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- 楽楽精算では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの楽楽精算の追加

Microsoft Entra ID への楽楽精算の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに楽楽精算を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**楽楽精算**」と入力します。
4. 結果のパネルから **[楽楽精算]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Settling music 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、楽楽精算に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと楽楽精算の関連ユーザーの間にリンク関係を確立する必要があります。

楽楽精算に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **楽楽精算の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Settling music テストユーザーの作成** - Microsoft Entra のユーザー表現にリンクされた Settling music で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**音楽調整**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<SUBDOMAIN>.rakurakuseisan.jp/<USERACCOUNT>/`

    b。 **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<SUBDOMAIN>.rakurakuseisan.jp/<USERACCOUNT>/`

    注意

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[楽楽精算クライアント サポート チーム](https://rakurakuseisan.jp/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[楽楽精算のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ログアウト URL には、次の URL を使います。

    ```text
    Logout URL https://login.microsoftonline.com/common/wsfederation?wa=wsignout1.0
    ```

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 楽楽精算の SSO の構成

1. 別の Web ブラウザー ウィンドウで、セキュリティ管理者として楽楽精算にサインインします。
2. ページの上部にある [ **管理** ] タブを選択します。

    [Image: 楽楽精算手順 1]
3. [ **システム設定** ] タブを選択します。

    [Image: 楽楽精算手順 2]
4. **[セキュリティ]** タブに切り替えます。

    [Image: 楽楽精算手順 3]
5. **[シングル サインオンの設定]** セクションで、次の手順に従います。

    [Image: 楽楽精算手順 5]

    ある。 **有効にする** を選択します。

    b。 **[Login URL of the ID provider] (ID プロバイダーのログイン URL)** テキストボックスに、**ログイン URL** の値を貼り付けます。

    c. **[ID プロバイダー ログアウト URL]** テキスト ボックスに、**[Microsoft Entra SSO の構成]** セクションで説明されている [ログアウト URL] の値を貼り付けます。

    d. [ **ファイルの選択] を選択** して、Azure portal からダウンロードした **証明書 (Base64)** をアップロードします。

    え **[保存]** ボタンを選択します。

#### 楽楽精算テスト ユーザーの作成

このセクションでは、楽楽精算で Britta Simon というユーザーを作成します。 [楽楽精算クライアント サポート チーム](https://rakurakuseisan.jp/)と連携し、楽楽精算プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [**このアプリケーションをテストする**] を選択すると、このオプションによってログインフローを開始できる、Settling music のサインオンURLにリダイレクトされます。
- 楽楽精算のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [楽楽精算] タイルを選択すると、このオプションは楽楽精算のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sevone-network-monitoring-system-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SevOne ネットワーク監視システム (NMS) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sevone-network-monitoring-system-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SevOne ネットワーク監視システム (NMS) の間でシングル サインオンを構成する方法について説明します。

この記事では、SevOne ネットワーク監視システム (NMS) と Microsoft Entra ID を統合する方法について説明します。 SevOne ネットワーク監視システム (NMS) と Microsoft Entra ID を統合すると、次のことを実行できます。

- SevOne ネットワーク監視システム (NMS) にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SevOne ネットワーク監視システム (NMS) に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SevOne ネットワーク監視システム (NMS) でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SevOne ネットワーク監視システム (NMS) では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから SevOne ネットワーク監視システム (NMS) を追加する

SevOne ネットワーク監視システム (NMS) の Microsoft Entra ID への統合を構成するには、マネージド SaaS アプリのリストにギャラリーから SevOne ネットワーク監視システム (NMS) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「SevOne Network Monitoring System (NMS)」**と入力します。
4. 結果パネルから **SevOne ネットワーク監視システム (NMS)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SevOne ネットワーク監視システム (NMS) 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SevOne Network Monitoring System (NMS) に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SevOne ネットワーク監視システム (NMS) の関連ユーザーとの間にリンク関係を確立する必要があります。

SevOne ネットワーク監視システム (NMS) に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SevOne Network Monitoring System (NMS) SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **SevOne Network Monitoring System (NMS) テストユーザーの作成** - SevOne Network Monitoring System (NMS) で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SevOne Network Monitoring System (NMS)**&gt;**シングルサインオン**へブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、URL を入力します。 `https://azwcusehnmspas01.corp.microsoft.com/sso/callback`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://azwcusehnmspas01.corp.microsoft.com/sso/callback`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://azwcusehnmspas01.corp.microsoft.com/sso/callback`

    d. [ **リレー状態** ] テキスト ボックスに、次の値を入力します。 `sevonenms`
6. SevOne ネットワーク監視システム (NMS) アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性マッピングの画像を示すスクリーンショット。]
7. その他に、SevOne ネットワーク監視システム (NMS) アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 表示名 | ユーザー表示名 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **SevOne ネットワーク監視システム (NMS) のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SevOne ネットワーク監視システム (NMS) SSO の構成

**SevOne ネットワーク監視システム (NMS)** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [SevOne ネットワーク監視システム (NMS) サポート チーム](mailto:support@sevone.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SevOne ネットワーク監視システム (NMS) テスト ユーザーを作成する

このセクションでは、SevOne ネットワーク監視システム (NMS) で Britta Simon というユーザーを作成します。 [SevOne ネットワーク監視システム (NMS) サポート チーム](mailto:support@sevone.com)と協力して、SevOne ネットワーク監視システム (NMS) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる SevOne ネットワーク監視システム (NMS) Sign-On URL にリダイレクトされます。
- SevOne ネットワーク監視システム (NMS) サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SevOne ネットワーク監視システム (NMS)] タイルを選択すると、このオプションは SevOne ネットワーク監視システム (NMS) Sign-On URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sharecal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ShareCal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharecal-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-07-15
- Summary: Microsoft Entra ID と ShareCal の間のシングル サインオンを構成する方法について説明します。

この記事では、ShareCal と Microsoft Entra ID を統合する方法について説明します。 ShareCal と Microsoft Entra ID を統合すると、次のことができます。

- ShareCal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ShareCal に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ShareCal でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ShareCal では、**SP** によって開始される SSO のみがサポートされます。
- ShareCal では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ShareCal を追加する

Microsoft Entra ID への ShareCal の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ShareCal を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ShareCal**」と入力します。
4. 結果のパネルから **[ShareCal]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ShareCal 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、ShareCal に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ShareCal の関連ユーザーとの間にリンク関係を確立する必要があります。

ShareCal に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ShareCal の SSO を構成する**- アプリケーション側でシングル サインオンを構成します。
    1. **ShareCal テスト ユーザーの作成** - Microsoft Entra に表示される B.Simon とリンクされる、ShareCal 内での B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ShareCal**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a **[識別子 (エンティティ ID)]** ボックスに `https://saml.sharecal.io` という URL を入力します。

    b。 **[応答 URL]** ボックスに、URL として「`https://admin.sharecal.io/api/oauth/saml`」と入力します。

    c. **[サインオン URL]** ボックスに、URL として「`https://sharecal.io/app`」と入力します。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ShareCal の SSO を構成する

**ShareCal** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [ShareCal サポート チーム](mailto:support@sharecal.io)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### ShareCal のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを ShareCal に作成します。 ShareCal では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションでは、ユーザー側で必要な操作はありません。 ShareCal にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる ShareCal のサインオン URL にリダイレクトします。
- ShareCal のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [ShareCal] タイルを選択すると、このオプションは ShareCal のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sharefile-tutorial"} -->
## Microsoft Entra ID で Citrix ShareFile for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharefile-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Citrix ShareFile の間のシングル サインオンを構成する方法について説明します。

この記事では、Citrix ShareFile と Microsoft Entra ID を統合する方法について説明します。 Citrix ShareFile を Microsoft Entra ID と統合すると、次のことが可能になります。

- Citrix ShareFile へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Citrix ShareFile に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

Citrix ShareFile は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Citrix ShareFile でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Citrix ShareFile では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Citrix ShareFile の追加

Microsoft Entra ID への Citrix ShareFile の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Citrix ShareFile を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Citrix ShareFile**」と入力します。
4. 結果のパネルから **[Citrix ShareFile]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Citrix ShareFile 用に Microsoft Entra SSO を構成してテストする

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Citrix ShareFile で Microsoft Entra シングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Citrix ShareFile 内の関連ユーザー間にリンク関係が確立されている必要があります。

Citrix ShareFile で Microsoft Entra シングル サインオンを構成し、テストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する** - ユーザーがこの機能を使用できるようにします。

    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Citrix ShareFile の SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。

    1. **Citrix ShareFile テスト ユーザーの作成** - Citrix ShareFile で Britta Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の Britta Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Citrix ShareFile]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** テキストボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<tenant-name>.sharefile.com` |
    | `https://<tenant-name>.sharefile.com/saml/info` |
    | `https://<tenant-name>.sharefile1.com/saml/info` |
    | `https://<tenant-name>.sharefile1.eu/saml/info` |
    | `https://<tenant-name>.sharefile.eu/saml/info` |

    b。 **[応答 URL]** ボックスに、次のいずれかの形式で URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<tenant-name>.sharefile.com/saml/acs` |
    | `https://<tenant-name>.sharefile.eu/saml/<URL path>` |
    | `https://<tenant-name>.sharefile.com/saml/<URL path>` |

    c. **[サインオン URL]** ボックスに、`https://<tenant-name>.sharefile.com/saml/login` という形式で URL を入力します。

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得する場合は、[Citrix ShareFile クライアント サポート チーム](https://support.sharefile.com/s/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書の [ダウンロード] リンクを示すスクリーンショット。]
7. **[Citrix ShareFile のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成の URL のコピーを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Citrix ShareFile の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Citrix ShareFile 企業サイトに管理者としてサインインします。
2. **ダッシュボード**で、[**設定]** を選択し**、[管理者設定]** を選択します。

    [Image: 管理ページを示すスクリーンショット。]
3. [管理者設定] で、[ **セキュリティ**&gt;**ログイン] & [セキュリティ ポリシー**] に移動します。

    [Image: アカウント管理ページを示すスクリーンショット。]
4. [**Single Sign-On/ SAML 2.0 Configuration**] ダイアログ ページの [**Basic Settings**] で、以下の手順を実行します。

    [Image: シングル サインオン ページを示すスクリーンショット。]

    a. **[Enable SAML](SAML を有効にする)** で **[はい]** を選択します。

    b。 **[ShareFile 発行者/エンティティ ID]** の値をコピーし、**[基本的な SAML 構成]** ダイアログ ボックスにある **[識別子 URL]** ボックスに貼り付けます。

    c. **[IDP 発行者/エンティティ ID]** テキストボックスに **[Microsoft Entra 識別子]** の値を貼り付けます。

    d. ダウンロードした**証明書 (Base64)** をメモ帳に開き、[**変更**] ボタンを選択してコンテンツを **[X.509 証明書**] ボックスに貼り付けます。

    e. **[ログイン URL]** テキストボックスに、**[ログイン URL]** の値を貼り付けます。

    f. **[ログアウト URL]** テキストボックスに、**[ログアウト URL]** の値を貼り付けます。

    g. **[Optional Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/オプション設定)** の **[SP-Initiated Auth Context](SP Initiated 認証コンテキスト)** で、 **[User Name and Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名とパスワード)** および **[Exact](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/完全一致)** を選択します。
5. **保存** を選択します。

### Citrix ShareFile テスト ユーザーの作成

1. **Citrix ShareFile** テナントにログインします。
2. [ **People**&gt;**Manage Users Home**&gt;**Create New Users**&gt;**Create Employee** を選択します。

    [Image: [従業員の作成] を示すスクリーンショット]
3. **[Basic Information]** セクションで、次の手順を実行します。

    [Image: [基本情報] を示すスクリーンショット。]

    a. **[名]** ボックスに、ユーザーの**名**を、「**Britta**」と入力します。

    b。 **[姓]** ボックスに、ユーザーの**姓**を、「**Simon**」と入力します。

    c. **[Email Address](電子メール アドレス)** ボックスに、Britta Simon アカウントの電子メール アドレスを **brittasimon@contoso.com** と入力します。
4. [ **ユーザーの追加] を選択します**。

    注

    Microsoft Entra のアカウント所有者が電子メールを受信し、リンクをたどって自分のアカウントを確認すると、アカウントがアクティブになります。Microsoft Entra ユーザー アカウントのプロビジョニングには、他の Citrix ShareFile ユーザー アカウント作成ツールまたは Citrix ShareFile が提供する API を使用できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションを選択すると、ログイン フローを開始できる Citrix ShareFile のサインオン URL にリダイレクトされます。
- Citrix ShareFile のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Citrix ShareFile] タイルを選択すると、このオプションは Citrix ShareFile のサインオン URL にリダイレクトされます。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sharepoint-on-premises-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SharePoint オンプレミスを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharepoint-on-premises-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SharePoint オンプレミスの間のフェデレーション認証を実装する方法について学習します。

### シナリオの説明

この記事では、Microsoft Entra ID と SharePoint オンプレミスの間でフェデレーション認証を構成します。 ユーザーが Microsoft Entra ID にサインインし、その ID を使用して SharePoint オンプレミス サイトにアクセスできるようにすることが目標です。

### 前提条件

構成を実行するには、次のリソースが必要です。 - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。. アカウントがない場合は、[無料アカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。

- SharePoint 2013 以降のファーム。

この記事では、次の値を使用しています。

- エンタープライズ アプリケーション名 (Microsoft Entra ID): `SharePoint corporate farm`
- 信頼識別子 (Microsoft Entra ID)、領域 (SharePoint 内): `urn:sharepoint:federation`
- loginUrl (Microsoft Entra ID へ): `https://login.microsoftonline.com/dc38a67a-f981-4e24-ba16-4443ada44484/wsfed`
- SharePoint サイトの URL: `https://spsites.contoso.local/`
- SharePoint サイトの応答 URL: `https://spsites.contoso.local/_trust/`
- SharePoint の信頼の構成名: `MicrosoftEntraTrust`
- Microsoft Entra テスト ユーザーの UserPrincipalName: `AzureUser1@demo1984.onmicrosoft.com`

### Microsoft Entra ID でエンタープライズ アプリケーションを構成する

Microsoft Entra ID でフェデレーションを構成するには、専用のエンタープライズ アプリケーションを作成する必要があります。 その構成は、アプリケーション ギャラリーにあるあらかじめ構成されているテンプレート `SharePoint on-premises` を使用することで簡単に行うことができます。

#### エンタープライズ アプリケーションを作成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに、「**SharePoint on-premises**」と入力します。 結果ペインで **[SharePoint on-premises](SharePoint オンプレミス)** を選択します。
4. アプリケーションの名前 (この記事では `SharePoint corporate farm`) を指定し、[ **作成** ] を選択してアプリケーションを追加します。
5. 新しいエンタープライズ アプリケーションで **[プロパティ]** を選択し、 **[ユーザーの割り当てが必要ですか?]** の値を確認します。 このシナリオでは、その値を **[いいえ** ] に設定し、[ **保存]** を選択します。

#### エンタープライズ アプリケーションを構成する

このセクションでは、SAML 認証を構成し、認証が成功したときに SharePoint に送信される要求を定義します。

1. エンタープライズ アプリケーション `SharePoint corporate farm` の [概要] で、 **[2. シングル サインオンの設定]** を選択し、次のダイアログで **[SAML]** を選択します。
2. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** ペインの**編集**アイコンを選択します。
3. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. **[識別子]** ボックスに、`urn:sharepoint:federation` という値が存在することを確認します。
    2. **[応答 URL]** ボックスに、次のパターンを使用して URL を入力します: `https://spsites.contoso.local/_trust/`。
    3. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します: `https://spsites.contoso.local/`。
    4. **[保存]** を選択します。
4. **[User Attributes & Claims]\(ユーザー属性とクレーム\)** セクションで、不要な次のクレームの種類を削除します。SharePoint がアクセス許可を付与する際に、これらは使用されません。

    - `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`
    - `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`
    - `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`
5. 設定は次のように表示されます。

    [Image: 基本的な SAML 設定]
6. 後で SharePoint で必要な情報をコピーします。

    - **[SAML 署名証明書]** セクションで、**証明書 (Base64)** を**ダウンロード**します。 これは、Microsoft Entra ID が SAML トークンへの署名に使用する署名証明書の公開キーです。 SharePoint が、受け取った SAML トークンの整合性を検証する際に必要となります。
    - **[Set up SharePoint corporate farm](SharePoint 企業ファームのセットアップ)** セクションで、 **[ログイン URL]** をメモ帳にコピーします。末尾の文字列 **/saml2** は **/wsfed** に置き換えてください。

    重要

    SharePoint によって必要とされる SAML 1.1 トークンが Microsoft Entra ID から確実に発行されるよう、**/saml2** は必ず **/wsfed** に置き換えてください。

    - **[Set up SharePoint corporate farm](SharePoint 企業ファームのセットアップ)** セクションで、 **[ログアウト URL]** をコピーします。

### Microsoft Entra ID を信頼するように SharePoint を構成する

#### SharePoint で信頼を作成する

この手順では、SharePoint が Microsoft Entra ID を信頼するために必要な構成を格納する SPTrustedLoginProvider を作成します。 そのため、上記でコピーした Microsoft Entra ID の情報が必要です。Windows PowerShell を使用すると、いくつかのコマンドが失敗する場合があることに注意してください。SharePoint 管理シェルを起動し、次のスクリプトを実行して作成します。

```powershell
# Path to the public key of the Microsoft Entra SAML signing certificate (self-signed), downloaded from the Enterprise application in the Azure portal
$signingCert = New-Object System.Security.Cryptography.X509Certificates.X509Certificate2("C:\Microsoft Entra app\SharePoint corporate farm.cer")
# Unique realm (corresponds to the "Identifier (Entity ID)" in the Microsoft Entra enterprise application)
$realm = "urn:sharepoint:federation"
# Login URL copied from the Microsoft Entra enterprise application. Make sure to replace "saml2" with "wsfed" at the end of the URL:
$loginUrl = "https://login.microsoftonline.com/dc38a67a-f981-4e24-ba16-4443ada44484/wsfed"

# Define the claim types used for the authorization
$userIdentifier = New-SPClaimTypeMapping -IncomingClaimType "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name" -IncomingClaimTypeDisplayName "name" -LocalClaimType "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/upn"
$role = New-SPClaimTypeMapping "http://schemas.microsoft.com/ws/2008/06/identity/claims/role" -IncomingClaimTypeDisplayName "Role" -SameAsIncoming

# Let SharePoint trust the Microsoft Entra signing certificate
New-SPTrustedRootAuthority -Name "Microsoft Entra signing certificate" -Certificate $signingCert

# Create a new SPTrustedIdentityTokenIssuer in SharePoint
$trust = New-SPTrustedIdentityTokenIssuer -Name "MicrosoftEntraTrust" -Description "Microsoft Entra ID" -Realm $realm -ImportTrustCertificate $signingCert -ClaimsMappings $userIdentifier, $role -SignInUrl $loginUrl -IdentifierClaim $userIdentifier.InputClaimType
```

#### SharePoint Web アプリケーションを構成する

この手順では、先ほど作成した Microsoft Entra エンタープライズ アプリケーションを信頼するように SharePoint の Web アプリケーションを構成します。 その際に考慮すべき重要なルールは次のとおりです。

- SharePoint Web アプリケーションの既定のゾーンでは、Windows 認証が有効になっている必要があります。 これは検索クローラーの要件となります。
- Microsoft Entra 認証を使用する SharePoint URL は、HTTPS を使用して設定されている必要があります。

1. Web アプリケーションを作成または拡張します。 この記事では、想定される 2 つの構成について説明します。

    - 既定のゾーンで Windows 認証と Microsoft Entra 認証の両方を使用する新しい Web アプリケーションを作成した場合:

        1. **SharePoint 管理シェル**を起動し、次のスクリプトを実行します。

            ```powershell
            # This script creates a new web application and sets Windows and Microsoft Entra authentication on the Default zone
            # URL of the SharePoint site federated with Microsoft Entra
            $trustedSharePointSiteUrl = "https://spsites.contoso.local/"
            $applicationPoolManagedAccount = "Contoso\spapppool"
            
            $winAp = New-SPAuthenticationProvider -UseWindowsIntegratedAuthentication -DisableKerberos:$true
            $sptrust = Get-SPTrustedIdentityTokenIssuer "MicrosoftEntraTrust"
            $trustedAp = New-SPAuthenticationProvider -TrustedIdentityTokenIssuer $sptrust    
            
            New-SPWebApplication -Name "SharePoint - Microsoft Entra" -Port 443 -SecureSocketsLayer -URL $trustedSharePointSiteUrl -ApplicationPool "SharePoint - Microsoft Entra" -ApplicationPoolAccount (Get-SPManagedAccount $applicationPoolManagedAccount) -AuthenticationProvider $winAp, $trustedAp
            ```
        2. **[SharePoint サーバーの全体管理]** サイトを開きます。
        3. **[システム設定]** で、**[代替アクセス マッピングの構成]** を選択します。 **[代替アクセス マッピング コレクション]** ボックスが開きます。
        4. 新しい Web アプリケーションで表示をフィルター処理し、次のような内容が表示されることを確認します。

            [Image: Web アプリケーションの代替アクセス マッピング]
    - 新しいゾーンで Microsoft Entra 認証を使用するように既存の Web アプリケーションを拡張した場合:

        1. SharePoint 管理シェルを起動し、次のスクリプトを実行します。

            ```powershell
            # This script extends an existing web application to set Microsoft Entra authentication on a new zone
            # URL of the default zone of the web application
            $webAppDefaultZoneUrl = "http://spsites/"
            # URL of the SharePoint site federated with ADFS
            $trustedSharePointSiteUrl = "https://spsites.contoso.local/"
            $sptrust = Get-SPTrustedIdentityTokenIssuer "MicrosoftEntraTrust"
            $ap = New-SPAuthenticationProvider -TrustedIdentityTokenIssuer $sptrust
            $wa = Get-SPWebApplication $webAppDefaultZoneUrl
            
            New-SPWebApplicationExtension -Name "SharePoint - Microsoft Entra" -Identity $wa -SecureSocketsLayer -Zone Internet -Url $trustedSharePointSiteUrl -AuthenticationProvider $ap
            ```
        2. **[SharePoint サーバーの全体管理]** サイトを開きます。
        3. **[システム設定]** で、**[代替アクセス マッピングの構成]** を選択します。 **[代替アクセス マッピング コレクション]** ボックスが開きます。
        4. 拡張された Web アプリケーションで表示をフィルター処理し、次のような内容が表示されることを確認します。

            [Image: 拡張された Web アプリケーションの代替アクセス マッピング]

Web アプリケーションが作成されたら、ルート サイト コレクションを作成し、自分の Windows アカウントをプライマリ サイト コレクション管理者として追加します。

1. SharePoint サイトの証明書を作成する

    SharePoint URL では HTTPS プロトコル (`https://spsites.contoso.local/`) が使用されるため、対応するインターネット インフォメーション サービス (IIS) サイトで証明書を設定する必要があります。 その手順に従って、自己署名証明書を生成してください。

    重要

    自己署名証明書はテスト目的にのみ適しています。 運用環境では、代わりに証明機関が発行した証明書を使用することを強くお勧めします。

    1. Windows PowerShell コンソールを開きます。
    2. 次のスクリプトを実行して自己署名証明書を生成し、それをコンピューターの MY ストアに追加します。

        ```powershell
        New-SelfSignedCertificate -DnsName "spsites.contoso.local" -CertStoreLocation "cert:\LocalMachine\My"
        ```
2. IIS サイトで証明書を設定します。

    1. インターネット インフォメーション サービス マネージャー コンソールを開きます。
    2. ツリー ビューでサーバーを展開し、**[サイト]** を展開し、**[SharePoint - Microsoft Entra ID]** サイトを選択して **[バインド]** を選択します。
    3. **https バインド**を選択して、**[編集]** を選択します。
    4. [TLS/SSL 証明書] フィールドで、使用する証明書 (先ほど作成した **spsites.contoso.local** など) を選択し、 **[OK]** を選択します。

    注意

    Web フロント エンド サーバーが複数ある場合、この操作を各サーバーで繰り返す必要があります。

SharePoint と Microsoft Entra ID の間の信頼に関する基本的な構成は以上です。 Microsoft Entra ユーザーとして SharePoint サイトにサインインする方法を見てみましょう。

### メンバー ユーザーとしてサインインする

Microsoft Entra ID には、[2 種類のユーザー](https://learn.microsoft.com/ja-jp/entra/external-id/user-properties)が存在します。ゲスト ユーザーとメンバー ユーザーです。 まず、組織に所属する単なるユーザーであるメンバー ユーザーから始めましょう。

#### Microsoft Entra ID でメンバー ユーザーを作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. "**表示名**" フィールドに「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。
6. このユーザーとサイトを共有し、そこへのアクセスを許可することができます。

#### SharePoint で Microsoft Entra ユーザーにアクセス許可を付与する

Windows アカウント (サイト コレクション管理者) として SharePoint ルート サイト コレクションにサインインし、[共有] を選択 **します**。 ダイアログには、userprincipalname の正確な値、たとえば「`AzureUser1@demo1984.onmicrosoft.com`」を入力する必要があります。また、**name** クレームの結果を慎重に選択してください (結果にマウスを合わせると、そのクレームの種類が表示されます)。

重要

招待するユーザーの正確な値を入力し、一覧で適切な要求の種類を選択するように注意してください。そうしないと、共有は機能しません。

[Image: EntraCP を使用しないユーザー ピッカーの結果のスクリーンショット。]

この制限は、SharePoint がユーザー選択ウィンドウからの入力を検証しないためです。混乱を招き、スペルミスやユーザーが誤って誤った要求の種類を選択する可能性があります。 このシナリオを修正するには、[EntraCP](https://entracp.yvand.net/) というオープンソース ソリューションを使用して、SharePoint 2019、2016、2013 を Microsoft Entra ID に接続し、Microsoft Entra テナントに対して入力を解決します。 詳細については、[EntraCP](https://entracp.yvand.net/) を参照してください。

以下に示したのは、EntraCP が構成されている状態で同じ検索を実行した結果です。入力内容に基づいて実際のユーザーが SharePoint から返されます。

[Image: EntraCP を使用したユーザー ピッカーの結果のスクリーンショット。]

重要

EntraCP は Microsoft 製品ではないため、Microsoft サポートではサポートされません。 オンプレミスの SharePoint ファームで EntraCP をダウンロード、インストール、構成するには、[EntraCP](https://entracp.yvand.net/) Web サイトを参照してください。

これで Microsoft Entra ユーザー `AzureUser1@demo1984.onmicrosoft.com` が、自分の ID を使用して SharePoint サイト `https://spsites.contoso.local/` にサインインできるようになりました。

### セキュリティ グループにアクセス許可を付与する

#### グループ クレームの種類をエンタープライズ アプリケーションに追加する

1. エンタープライズ アプリケーション `SharePoint corporate farm` の [概要] で、 **[2. シングル サインオンの設定]** を選択します。
2. グループ クレームが存在しない場合は、**[User Attributes & Claims]\(ユーザー属性とクレーム\)** セクションで、これらの手順に従います。

    1. **[グループ要求を追加する]** を選択し、 **[セキュリティ グループ]** を選択して、 **[ソース属性]** を **[グループ ID]** に設定します。
    2. [ **グループ要求の名前をカスタマイズする**] をオンにし、[ **グループをロール要求として出力する** ] をオンにして **、[保存]** を選択します。
    3. **[User Attributes & Claims]\(ユーザー属性とクレーム\)** は次のようになります。

    [Image: ユーザーとグループのクレーム]

#### Microsoft Entra ID でセキュリティ グループを作成する

セキュリティ グループを作成しましょう。

1. **Entra ID**&gt;**Groups** に移動します。
2. **[新しいグループ]** を選びます。
3. **[グループの種類]** (Security)、 **[グループ名]** (`AzureGroup1` など)、 **[メンバーシップの種類]** を入力します。 上記で作成したユーザーをメンバーとして追加し、[ **作成**] を選択します。

    [Image: Microsoft Entra セキュリティ グループの作成]

#### SharePoint でセキュリティ グループにアクセス許可を付与する

Microsoft Entra のセキュリティ グループは、その `Id` 属性、つまり GUID (`00aa00aa-bb11-cc22-dd33-44ee44ee44ee` など) で識別されます。 カスタム クレーム プロバイダーを使用しなかった場合、ユーザーは、グループの正確な値 (`Id`) をユーザー ピッカーで入力し、対応するクレームの種類を選択しなければなりません。 これはユーザー フレンドリでも信頼性もありません。 それを避けるために、この記事では、サードパーティのクレーム プロバイダー [EntraCP](https://entracp.yvand.net/) を使用することで、グループを SharePoint から簡単に探し出せるようにしています。

[Image: ユーザー ピッカーによる Microsoft Entra グループの検索]

### ゲスト ユーザー アクセスを管理する

ゲスト アカウントには、次の 2 種類があります。

- B2B ゲスト アカウント: これらのユーザーは、外部の Microsoft Entra テナントに属します
- MSA ゲスト アカウント: これらのユーザーは、Microsoft ID プロバイダー (Hotmail、Outlook) またはソーシャル アカウント プロバイダー (Google など) に属します。

既定では、Microsoft Entra ID によって、"一意のユーザー ID" とクレームの "名前" がどちらも `user.userprincipalname` 属性に設定されます。 あいにくゲスト アカウントでは、この属性は、あいまいさが生じます。次の表をご覧ください。

| Microsoft Entra ID に設定されたソース属性 | B2B ゲストのMicrosoft Entra ID で使用される実際のプロパティ | MSA ゲストのMicrosoft Entra ID で使用される実際のプロパティ | SharePoint が ID の検証に利用できるプロパティ |
| --- | --- | --- | --- |
| `user.userprincipalname` | `mail` (例: `guest@PARTNERTENANT`) | `userprincipalname` (例: `guest_outlook.com#EXT#@TENANT.onmicrosoft.com`) | あいまい |
| `user.localuserprincipalname` | `userprincipalname` (例: `guest_PARTNERTENANT#EXT#@TENANT.onmicrosoft.com`) | `userprincipalname` (例: `guest_outlook.com#EXT#@TENANT.onmicrosoft.com`) | `userprincipalname` |

結論として、すべてのゲスト アカウントを確実に同じ属性で識別するためには、`user.localuserprincipalname` ではなく `user.userprincipalname` 属性を使用するように、エンタープライズ アプリケーションの ID クレームを更新する必要があります。

#### すべてのゲスト ユーザーに対して一貫した属性を使用するようにアプリケーションを更新する

1. エンタープライズ アプリケーション `SharePoint corporate farm` の [概要] で、 **[2. シングル サインオンの設定]** を選択します。
2. **[SAML でシングル サインオンをセットアップします]** ページで、**[User Attributes & Claims]\(ユーザー属性とクレーム\)** ペインの**編集**アイコンを選択します。
3. **[User Attributes & Claims]\(ユーザー属性とクレーム\)** セクションで、これらの手順に従います。

    1. **一意のユーザー識別子 (名前 ID) を**選択し、**そのソース属性**プロパティを **user.localuserprincipalname** に変更して、[**保存]** を選択します。
    2. `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`選択し、**そのソース属性**プロパティを **user.localuserprincipalname** に変更して、[**保存]** を選択します。
    3. **[User Attributes & Claims]\(ユーザー属性とクレーム\)** は次のようになります。

    [Image: ゲストのユーザー属性とクレーム]

#### SharePoint でゲスト ユーザーを招待する

注意

このセクションでは、クレーム プロバイダー EntraCP が使用されていることを前提としています

上のセクションでは、すべてのゲスト アカウントに対して一貫した属性を使用するようにエンタープライズ アプリケーションを更新しました。 今度は、その変更を反映し、ゲスト アカウントに 属性 `userprincipalname` を使用するように、EntraCP の構成を更新する必要があります。

1. **[SharePoint サーバーの全体管理]** サイトを開きます。
2. **[セキュリティ]** で **[EntraCP global configuration](EntraCP グローバル構成)** を選択します。
3. **[User identifier property](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー識別子プロパティ)** セクションで、 **[User identifier for 'Guest' users]("ゲスト" ユーザーのユーザー識別子)** を **UserPrincipalName** に設定します。
4. [OK] を選択する

SharePoint サイトでゲスト ユーザーを招待できるようになりました。

### 複数の Web アプリケーションに対してフェデレーションを構成する

この構成は 1 つの Web アプリケーションに役立ちますが、複数の Web アプリケーションに対して同一の信頼できる ID プロバイダーを使用する場合は追加の構成が必要になります。 たとえば、別個の Web アプリケーション `https://otherwebapp.contoso.local/` があり、そのアプリケーションの Microsoft Entra 認証を有効にしたいとします。 そのためには、SAML WReply パラメーターを渡すように SharePoint を構成し、該当する URL をエンタープライズ アプリケーションに追加します。

#### SAML WReply パラメーターを渡すように SharePoint を構成する

1. SharePoint サーバーで SharePoint 201x 管理シェルを開き、次のコマンドを実行します。 先ほど使用した信頼できる ID トークン発行者の名前を使用します。

```powershell
$t = Get-SPTrustedIdentityTokenIssuer "MicrosoftEntraTrust"
$t.UseWReplyParameter = $true
$t.Update()
```

#### エンタープライズ アプリケーションに URL を追加する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を参照し&gt;以前に作成したエンタープライズ アプリケーションを選択し、[**シングル サインオン**] を選択します。
3. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** を編集します。
4. [ **応答 URL (Assertion Consumer Service URL)]** セクションで、Microsoft Entra ID を使用してユーザーをサインインさせる必要がある追加の Web アプリケーションの URL (たとえば、 `https://otherwebapp.contoso.local/`) を追加し、[ **保存]** を選択します。

[Image: 追加の Web アプリケーションを指定する]

### セキュリティ トークンの有効期間を構成する

既定では、Microsoft Entra ID は、Azure portal または条件付きアクセス ポリシーを使用してカスタマイズできない、1 時間有効な SAML トークンを作成します。 ただし、 [カスタム トークン有効期間ポリシー](https://learn.microsoft.com/ja-jp/entra/identity-platform/configurable-token-lifetimes)を作成し、SharePoint Server 用に作成したエンタープライズ アプリケーションに割り当てることができます。 これを実現するには、次のスクリプトを実行します。

```powershell
Install-Module Microsoft.Graph
Connect-MgGraph -Scopes "Policy.ReadWrite.ApplicationConfiguration","Policy.Read.All","Application.ReadWrite.All"

$appDisplayName = "SharePoint corporate farm"
$sp = Get-MgServicePrincipal -Search DisplayName:"$appDisplayName" -ConsistencyLevel eventual

$oldPolicy = Get-MgServicePrincipalTokenLifetimePolicy -ServicePrincipalId $sp.Id
if ($null -ne $oldPolicy) {# There can be only 1 TokenLifetimePolicy associated to the service principal (or 0, as by default)
    Remove-MgServicePrincipalAppManagementPolicy -AppManagementPolicyId $oldPolicy.Id -ServicePrincipalId $sp.Id
}

# Get / create a custom token lifetime policy
$policyDisplayName = "WebPolicyScenario"
$policy = Get-MgPolicyTokenLifetimePolicy -Filter "DisplayName eq '$policyDisplayName'"
if ($null -eq $policy) {$params = @{	Definition = @('{"TokenLifetimePolicy":{"Version":1,"AccessTokenLifetime":"4:00:00"}}') 	DisplayName = $policyDisplayName	IsOrganizationDefault = $false}$policy = New-MgPolicyTokenLifetimePolicy -BodyParameter $params
}

# Assign the token lifetime policy to an app
$body = @{"@odata.id" = "https://graph.microsoft.com/v1.0/policies/tokenLifetimePolicies/$($policy.Id)"
}
Invoke-GraphRequest -Uri ('https://graph.microsoft.com/v1.0/servicePrincipals/{0}/tokenLifetimePolicies/$ref' -f $sp.Id) -Method POST -Body $body
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sharevault-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ShareVault を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharevault-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ShareVault の間のシングル サインオンを構成する方法について説明します。

この記事では、ShareVault と Microsoft Entra ID を統合する方法について説明します。 ShareVault を Microsoft Entra ID と統合すると、次のことが可能になります。

- ShareVault にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで ShareVault に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ShareVault でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ShareVault では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- ShareVault では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ShareVault の追加

Microsoft Entra ID, への ShareVault の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ShareVault を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**ShareVault**」と入力します。
4. 結果パネルで **[ShareVault]** を選択し、アプリケーションを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ShareVault 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ShareVault に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、ShareVault での関連ユーザーとの間にリンク関係を確立する必要があります。

ShareVault 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ShareVault SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ShareVault テストユーザーを作成し、Microsoft Entra のユーザー表現にリンクされた B.Simon の対応ユーザーを ShareVault で作ります。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ShareVault]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.sharevault.net/panajax/index.jsp?et=ssobe&svid=<SVID>`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[ShareVault クライアント サポート チーム](mailto:support@sharevault.net)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **保存** を選択します。
8. ShareVault アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: ShareVault アプリケーションの画像を示すスクリーンショット。]
9. その他に、ShareVault アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | sv.svid | &lt; `svid number` &gt; |
    | sv.firstname | User.givenname |
    | sv.lastname | ユーザーの名字 |
    | sv.email | user.userprincipalname |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ShareVault SSO の構成

**ShareVault** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [ShareVault サポート チーム](mailto:support@sharevault.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ShareVault テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ShareVault に作成します。 ShareVault では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 ShareVault にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ShareVault のサインオン URL にリダイレクトされます。
- ShareVault のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ShareVault に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ShareVault] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ShareVault に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sharingcloud-tutorial"} -->
## SharingCloudを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sharingcloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Instant Suite 間にシングル サインオンを構成する方法について説明します。

この記事では、SharingCloud と Microsoft Entra ID を統合する方法について説明します。 SharingCloud を Microsoft Entra ID を統合すると、次のことができます。

- SharingCloud にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って SharingCloud に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Sapient サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SharingCloud では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- SharingCloud では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの SharingCloud の追加

Microsoft Entra ID への SharingCloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SharingCloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SharingCloud**」と入力します。
4. 結果のパネルから **[SharingCloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SharingCloud 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SharingCloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SharingCloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を SharingCloud と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SharingCloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SharingCloud テストユーザーの作成** - B.Simon に対応するユーザーを SharingCloud に作成し、Microsoft Entra 上のアカウントにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SharingCloud**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.sharingcloud.net/auth/realms/<COMPANY_NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.sharingcloud.net/auth/realms/<COMPANY_NAME>/broker/saml/endpoint`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.factset.com/services/saml2/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[SharingCloud サポート チーム](mailto:support@sharingcloud.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. SharingCloud アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 編集アイコンが強調表示された [ユーザー属性] ユーザー インターフェイスのスクリーンショット。]
8. その他に、SharingCloud アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:sharingcloud:sso:firstname | ユーザー.ファーストネーム |
    | urn:sharingcloud:sso:lastname | ユーザーの名字 |
    | urn:sharingcloud:sso:email | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **コピー** ] アイコンを選択して、要件に従って指定されたオプションから **フェデレーション メタデータ URL を** コピーします。

    [Image: コピーするメタデータ URL]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SharingCloud の SSO の構成

**SharingCloud** 側でシングル サインオンを構成するには、Azure portal からコピーした**フェデレーション メタデータ URL** を [SharingCloud サポート チーム](mailto:support@sharingcloud.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SharingCloud テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを SharingCloud に作成します。 SharingCloud では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 SharingCloud にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる SharingCloud のサインオン URL にリダイレクトされます。
- SharingCloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SharingCloud に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SharingCloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SharingCloud に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/shibumi-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Shibumi を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shibumi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Shibumi の間のシングル サインオンを構成する方法について説明します。

この記事では、Shibumi と Microsoft Entra ID を統合する方法について説明します。 Shibumi を Microsoft Entra ID と統合すると、次の利点があります。

- Shibumi にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで Shibumi に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Shibumi でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Shibumi では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます
- Shibumi では、**Just-In-Time** ユーザー プロビジョニングがサポートされています

### ギャラリーからの Shibumi の追加

Microsoft Entra ID への Shibumi の統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Shibumi を追加する必要があります。

**ギャラリーから Shibumi を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Shibumi**」と入力し、結果パネルで **[Shibumi** ] を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Shibumi]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Shibumi で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Shibumi の関連ユーザー間にリンク関係を確立する必要があります。

Shibumi に対する Microsoft Entra シングル サインオンを構成およびテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Shibumi のシングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Shibumi のテストユーザーを作成** - Shibumiで「Britta Simon」に対応するユーザーを作成し、そのユーザーを「Microsoft Entra」で表現されたものにリンクします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Shibumi に対する Microsoft Entra シングル サインオンを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Shibumi** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: スクリーンショットには、[基本的な SAML 構成] が示されています。ここで、[識別子]、[応答 URL] の順に入力し、[保存] を選択できます。]

    ａ。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.shibumi.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.shibumi.com/saml/SSO`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: スクリーンショットには、追加のURLを設定する画面が表示されており、ここでサインオンURLを入力することができます。]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.shibumi.com/saml/SSO`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Shibumi クライアント サポート チーム](mailto:support@shibumi.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Shibumi の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ａ。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Shibumi のシングル サインオンの構成

**Shibumi** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション側からコピーした適切な URL を [Shibumi サポート チーム](mailto:support@shibumi.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Shibumi のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Shibumi に作成します。 Shibumi では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Shibumi にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Shibumi] タイルを選択すると、SSO を設定した Shibumi に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/shiftplanning-tutorial"} -->
## Microsoft Entra ID で Humanity for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shiftplanning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Humanity の間でシングル サインオンを構成する方法について説明します。

この記事では、Humanity と Microsoft Entra ID を統合する方法について説明します。 Humanity を Microsoft Entra ID と統合すると、次のことができます。

- Humanity にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Humanity に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Humanity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Humanity では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Humanity の追加

Humanity の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Humanity を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Humanity**」と入力します。
4. 結果パネルから **Humanity** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Humanity 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Humanity に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Humanity の関連ユーザーとの間にリンク関係を確立する必要があります。

Humanity で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Humanity SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Humanity テスト ユーザーの作成** - Microsoft Entra ユーザーの B.Simon に対応するユーザーを Humanity で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Humanity**&gt;**シングルサインオンに**移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://company.humanity.com/app/`

    b。 [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://company.humanity.com/includes/saml/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、Humanity クライアント サポート チームに問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Humanity のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Humanity SSO の構成

1. 別の Web ブラウザー ウィンドウで、 **Humanity** 企業サイトに管理者としてログインします。
2. 上部のメニューで、[管理者] を選択 **します**。

    [Image: 管理者]
3. [ **統合**] で、[ **シングル サインオン**] を選択します。

    [Image: [統合] メニューから [Single Sign-On] が選択されているスクリーンショット。]
4. [ **シングル サインオン** ] セクションで、次の手順に従います。

    [Image: [Single Sign-On](単一 Sign-On) セクションを示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 [ **SAML Enabled] を選択します**。

    b。 [ **パスワード ログインを許可する] を選択します**。

    c. **[SAML Issuer URL]\(SAML 発行者 URL**\) ボックスに、**ログイン URL** の値を貼り付けます。

    d. [ **リモート ログアウト URL** ] ボックスに、 **ログアウト URL** の値を貼り付けます。

    え Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **X.509 証明書** ボックスに貼り付けます。

    f. [ **設定の保存] を選択します**。

#### Humanity のテスト ユーザーの作成

Microsoft Entra ユーザーが Humanity にログインできるようにするには、そのユーザーを Humanity にプロビジョニングする必要があります。 Humanity の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **Humanity** 企業サイトに管理者としてログインします。
2. [ **管理者] を選択します**。

    [Image: 管理者]
3. [ **スタッフ]** を選択します。

    [Image: スタッフ]
4. [ **アクション] で**、[ **従業員の追加]** を選択します。

    [Image: 従業員の追加]
5. [ **従業員の追加** ] セクションで、次の手順を実行します。

    [Image: 従業員を保存 従業員を保存]

    ある。 プロビジョニングする有効な Microsoft Entra アカウントの **名**、 **姓**、 **電子メール** を関連するテキスト ボックスに入力します。

    b。 **従業員の保存**を選択します。

注

Humanity から提供されている他の Humanity ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Humanity のサインオン URL にリダイレクトされます。
- Humanity のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Humanity] タイルを選択すると、このオプションは Humanity のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/shiftwizard-saml-tutorial"} -->
## ShiftWizard SAML のシングル サインオンを Microsoft Entra ID を使用して構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shiftwizard-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ShiftWizard SAML の間のシングル サインオンを構成する方法について説明します。

この記事では、ShiftWizard SAML と Microsoft Entra ID を統合する方法について説明します。 ShiftWizard SAML を Microsoft Entra ID と統合すると、次のことが可能になります。

- ShiftWizard SAML にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで ShiftWizard SAML に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ShiftWizard SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ShiftWizard SAML では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ShiftWizard SAML を追加する

Microsoft Entra ID への ShiftWizard SAML の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ShiftWizard SAML を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「ShiftWizard SAML**」と入力します。
4. 結果パネルから **ShiftWizard SAML** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ShiftWizard SAML に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、ShiftWizard SAML に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ShiftWizard SAML の関連ユーザーとの間にリンク関係を確立する必要があります。

ShiftWizard SAML 向けに Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ShiftWizard SAML SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ShiftWizard SAML のテストユーザーを作成する** - ShiftWizard SAML で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ShiftWizard SAML**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://azureadsso.myshiftwizard.com/SSOActiveDirectory`
6. ShiftWizard SAML アプリケーションでは、特定の形式の SAML アサーションが想定されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、ShiftWizard SAML アプリケーションでは、SAML 応答でいくつかの属性が返されると想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員ID | user.employeeid |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ShiftWizard SAML SSO を構成する

**ShiftWizard SAML** 側でシングル サインオンを構成するには、**証明書 (PEM)** を [ShiftWizard SAML サポート チーム](mailto:it@shiftwizard.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ShiftWizard SAML テスト ユーザーを作成する

このセクションでは、ShiftWizard SAML で Britta Simon というユーザーを作成します。 [ShiftWizard SAML サポート チーム](mailto:it@shiftwizard.com)と協力して、ShiftWizard SAML プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ShiftWizard SAML サインオン URL にリダイレクトされます。
- ShiftWizard SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ShiftWizard SAML] タイルを選択すると、このオプションは ShiftWizard SAML サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/shiphazmat-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ShipHazmat を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shiphazmat-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ShipHazmat の間でシングル サインオンを構成する方法について説明します。

この記事では、ShipHazmat と Microsoft Entra ID を統合する方法について説明します。 ShipHazmat を Microsoft Entra ID を統合すると、次のことができます。

- ShipHazmat にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ShipHazmat に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ShipHazmat でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ShipHazmat では、**IDP** initiated SSO がサポートされます。
- ShipHazmat では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの ShipHazmat の追加

Microsoft Entra ID への ShipHazmat の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に ShipHazmat を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ShipHazmat**」と入力します。
4. 結果ウィンドウで **[ShipHazmat]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ShipHazmat 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ShipHazmat に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ShipHazmat の関連ユーザーとの間にリンク関係を確立する必要があります。

ShipHazmat に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ShipHazmat SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ShipHazmat テスト ユーザーの作成 - ShipHazmat** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ShipHazmat**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `ShipHazmat<CustomOrganization>Sso`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.shiphazmat.net/<CustomOrganization>/sso/saml/v1/ConsumerService.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[ShipHazmat クライアント サポート チーム](mailto:support@bureaudg.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. ShipHazmat アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、ShipHazmat アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 都市 | ユーザーの都市 |
    | 状態 | ユーザーの状態 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ShipHazmat SSO の構成

**ShipHazmat** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [ShipHazmat サポート チーム](mailto:support@bureaudg.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ShipHazmat テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを ShipHazmat に作成します。 ShipHazmat では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ShipHazmat にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ShipHazmat に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ShipHazmat] タイルを選択すると、SSO を設定した ShipHazmat に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/shmoopforschools-tutorial"} -->
## Microsoft Entra ID を使用して Shmoop For Schools for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shmoopforschools-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Shmoop For Schools の間のシングル サインオンを構成する方法について説明します。

この記事では、Shmoop For Schools と Microsoft Entra ID を統合する方法について説明します。 Shmoop For Schools と Microsoft Entra ID を統合すると、次のことができます。

- Shmoop For Schools にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Shmoop For Schools に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Shmoop For Schools のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Shmoop For Schools では、**SP** によって開始される SSO がサポートされます
- Shmoop For Schools では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Shmoop For Schools の追加

Microsoft Entra ID への Shmoop For Schools の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Shmoop For Schools を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Shmoop For Schools**」と入力します。
4. 結果パネルで **[Shmoop For Schools]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Shmoop For Schools 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Shmoop For Schools に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Shmoop For Schools の関連ユーザーとの間にリンク関係を確立する必要があります。

Shmoop For Schools に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Shmoop For Schools SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Shmoop For Schools のテスト ユーザーの作成** - Shmoop For Schools で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Shmoop For Schools** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://schools.shmoop.com/public-api/saml2/start/<uniqueid>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://schools.shmoop.com/<uniqueid>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Shmoop For Schools クライアント サポート チーム](mailto:support@shmoop.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Shmoop For Schools アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Shmoop For Schools アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ロール | user.assignedroles |

    注

    Shmoop For Schools では、**[教師]** と **[学生]** の 2 つのユーザー ロールがサポートされます。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定します。 Microsoft Entra ID でロールを構成する方法については、 [こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)。
8. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Shmoop For Schools SSO の構成

**Shmoop For Schools** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Shmoop For Schools サポート チーム](mailto:support@shmoop.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Shmoop For Schools テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Shmoop For Schools に作成します。 Shmoop For Schools では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションにはアクション項目はありません。 Shmoop For Schools にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Shmoop For Schools サポート チーム](mailto:support@shmoop.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Shmoop For Schools のサインオン URL にリダイレクトされます。
- Shmoop For Schools のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Shmoop For Schools] タイルを選択すると、このオプションは Shmoop For Schools のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/shopify-plus-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Shopify Plus を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shopify-plus-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から Shopify Plus に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Shopify Plus と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Shopify Plus](https://www.shopify.com/plus) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Shopify Plus でユーザーを作成する
- アクセスが不要になった場合に Shopify Plus のユーザーを削除する
- Microsoft Entra ID と Shopify Plus の間でユーザー属性の同期を維持する
- Shopify Plus への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shopify-plus-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- ドメインを確認し、SAML 構成を作成します。 管理できるのは、確認済みドメインに関連付けられているユーザーのみです。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Shopify Plus の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Shopify Plus を構成する

1. [Shopify Plus 組織管理者](https://shopify.plus)にログインします。**[ユーザー &gt; セキュリティ**] に移動します。
2. **SCIM 統合**セクションに移動し、[**API トークンの生成**] を選択します。
3. 生成されたトークンをコピーして保存します。 この値は、Shopify Plus アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。
4. ベース URL が `https://shopifyscim.com/scim/v2/`。 この値は、Shopify Plus アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Shopify Plus を追加する

Microsoft Entra アプリケーション ギャラリーから Shopify Plus を追加して、Shopify Plus へのプロビジョニングの管理を開始します。 SSO のために以前 Shopify Plus を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Shopify Plus への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Shopify Plus の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を閲覧する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Shopify Plus** を選択します。

    [Image: アプリケーションの一覧の Shopify Plus リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Shopify Plus テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Shopify Plus に接続できることを確認します。 接続に失敗した場合は、Shopify Plusアカウントに必要な管理者権限があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Shopify Plus に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Shopify Plus のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Shopify Plus API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート | Shopify Plus で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | roles | 糸 |  |  |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 変更ログ

2023 年 6 月 22 日 - **ロール**のサポートを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/shopify-plus-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Shopify Plus を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shopify-plus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Shopify Plus の間でシングル サインオンを構成する方法について説明します。

この記事では、 [Shopify Plus](https://www.shopify.com/plus) と Microsoft Entra ID を統合する方法について説明します。 Shopify Plus と Microsoft Entra ID を統合すると、次のことができます。

- Shopify Plus にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Shopify Plus に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Shopify Plus でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Shopify Plus では、SP **および IDP** によって開始される SSO がサポートされています。
- Shopify Plusは、自動ユーザープロビジョニング [をサポートしています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shopify-plus-provisioning-tutorial)。

### ギャラリーから Shopify Plus を追加する

Microsoft Entra ID への Shopify Plus の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Shopify Plus を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Shopify Plus**」と入力します。
4. 結果のパネルから Shopify Plus  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Shopify Plus の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Shopify Plus に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Shopify Plus の関連ユーザーとの間にリンク関係を確立する必要があります。

Shopify Plus で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Shopify Plus SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Shopify Plus テストユーザーを作成** - Microsoft Entra におけるユーザーとして B.Simon とリンクした Shopify Plus の対応ユーザーを作成します。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Shopify Plus**&gt;**シングルサインオン**に参照します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを **IDP 開始モード** で構成する場合は、次のフィールドの値を入力します。

    [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://accounts.shopify.com/saml/consume/organization/<ORGANIZATION_ID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://shopify.plus/login` を入力します。

    手記

    応答 URL は、実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、Shopify Plus クライアント サポート チーム  にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. Shopify Plus アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: イメージ]
8. 上記に加えて、Shopify Plus アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザー.メール |
9. **[名前 ID]** の形式を **[永続的]** に変更します。 **一意ユーザー識別子 (名前 ID)** オプションを選択し、**名前識別子** 形式を選択します。 このオプションでは **[永続的]** を選択します。 変更を保存します。
10. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書の**] セクションで、[コピー] ボタンを選択して **アプリフェデレーション メタデータ URL** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Shopify Plus の SSO の構成

完全な手順については、SAML 統合 [の設定に関する Shopify のドキュメント](https://help.shopify.com/en/manual/your-account/users/security/advanced-security-features/saml)参照してください。

**Shopify Plus** 側でシングル サインオンを構成するには、Microsoft Entra ID から **アプリフェデレーション メタデータ URL** をコピーします。 次に、[組織の管理者](https://shopify.plus) にログインし、**Users**&gt;**Security**に移動します。 **構成**を設定する] を選択し、[**ID プロバイダーメタデータ URL**] セクションにアプリのフェデレーション メタデータ URL を貼り付けます。 **を選択し、** を追加して、この手順を完了します。

#### Shopify Plus のテスト ユーザーの作成

このセクションでは、Shopify Plus で B.Simon というユーザーを作成します。 [**ユーザー**] セクションに戻り、メールとアクセス許可を入力してユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

手記

Shopify Plus では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

#### SAML 認証を適用する

手記

広範に適用する前に、個々のユーザーを使用して統合をテストすることをお勧めします。

個々のユーザー:

1. Microsoft Entra ID によって管理され、Shopify Plus で確認された電子メール ドメインを使用して、Shopify Plus の個々のユーザーのページに移動します。
2. [SAML 認証] セクションで、[**の編集]**選択し、[必要な ] を選択し、[**の保存]**選択します。
3. このユーザーが idP によって開始されたフローと SP によって開始されたフローを介して正常にサインインできることをテストします。

電子メール ドメインのすべてのユーザーの場合:

1. **セキュリティ** ページに戻ります。
2. SAML 認証設定では **[必須]** を選択します。 これにより、Shopify Plus 全体でその電子メール ドメインを持つすべてのユーザーに SAML が適用されます。
3. **[保存]** を選択します。

重要

電子メール ドメインのすべてのユーザーに対して SAML を有効にすると、このアプリケーションを使用するすべてのユーザーに影響します。 ユーザーは、通常のサインイン ページを使用してサインインすることはできません。 Microsoft Entra ID を介してのみアプリにアクセスできます。 Shopify では、ユーザーが通常のユーザー名とパスワードを使用してサインインできるバックアップ サインイン URL は提供されていません。 必要に応じて、Shopify サポートに連絡して SAML を無効にすることができます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Shopify Plus のサインオン URL にリダイレクトされます。
- Shopify Plus のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Shopify Plus に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Shopify Plus] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Shopify Plus に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/showpad-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Showpad を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/showpad-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Showpad 間にシングル サインオンを構成する方法について説明します。

この記事では、Showpad と Microsoft Entra ID を統合する方法について説明します。 Showpad を Microsoft Entra ID と統合すると、次のことができます。

- Showpad にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Showpad に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Showpad でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Showpad では、 **SP** Initiated SSO がサポートされます。
- Showpad では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Showpad の追加

Microsoft Entra ID への Showpad の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Showpad を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Showpad**」と入力します。
4. 結果パネルから **[Showpad** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Showpad 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Showpad に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Showpad の関連ユーザーとの間にリンク関係を確立する必要があります。

Showpad に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Showpad の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Showpad テストユーザーの作成** - Microsoft Entra に表現された B.Simon にリンクさせるため、Showpad で対応するB.Simonのユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Showpad**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.showpad.biz`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company-name>.showpad.biz/login`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Showpad クライアント サポート チーム](https://help.showpad.com/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Showpad のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Showpad SSO の構成

1. Showpad テナントに管理者としてサインインします。
2. 上部のメニューで、[ **設定]** を選択します。

    [Image: [設定] メニューから選択されている [設定] を示すスクリーンショット。]
3. **[シングル サインオン**] に移動し、[**有効にする**] を選択します。

    [Image: スクリーンショットは、単一の Sign-On が選択され、有効オプションが表示されていることを示しています。]
4. [ **SAML 2.0 サービスの追加** ] ダイアログで、次の手順を実行します。

    [Image: [SAML 2.0 サービスの追加] ダイアログ ボックスを示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 [ **名前** ] ボックスに、識別子プロバイダーの名前 (会社名など) を入力します。

    b。 **メタデータ ソース**として、[**XML**] を選択します。

    c. ダウンロードしたメタデータ XML ファイルの内容をコピーし、 **メタデータ XML** テキスト ボックスに貼り付けます。

    d. **ログイン時に新しいユーザーのアカウントを自動プロビジョニングするを選択します**。

    え [ **送信] を選択します**。

#### Showpad テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Showpad に作成します。 Showpad では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Showpad にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Showpad のサインオン URL にリダイレクトされます。
- Showpad のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Showpad] タイルを選択すると、このオプションは Showpad のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/shucchonavi-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Shuccho Navi を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shucchonavi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Shuccho Navi の間のシングル サインオンを構成する方法について説明します。

この記事では、Shuccho Navi と Microsoft Entra ID を統合する方法について説明します。 Shuccho Navi を Microsoft Entra ID と統合すると、以下のことが可能になります。

- 誰が Shuccho Navi にアクセスできるかを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Shuccho Navi に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Shuccho Navi でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Shuccho Navi では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Shuccho Navi を追加する

Shuccho Navi の Microsoft Entra ID への統合を構成するには、Shuccho Navi をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Shuccho Navi**」と入力します。
4. 結果パネルから **Shuccho Navi** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Shuccho Navi の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Shuccho Navi に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Shuccho Navi の関連ユーザーとの間にリンク関係を確立する必要があります。

Shuccho Navi で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Shuccho Navi SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Shuccho Navi のテストユーザーを作成 - B.Simon の対応ユーザーを Shuccho Navi に作成し、そのユーザーを Microsoft Entra の表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Shuccho Navi**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://naviauth.nta.co.jp/saml/login?ENTP_CD=<Your company code>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、Shuccho Navi クライアント サポート チーム](mailto:sys_ntabtm@nta.co.jp) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Shuccho Navi のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Shuccho Navi の SSO の構成

**Shuccho Navi** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Shuccho Navi サポート チーム](mailto:sys_ntabtm@nta.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Shuccho Navi テスト ユーザーの作成

このセクションでは、Shuccho Navi で Britta Simon というユーザーを作成します。 [Shuccho Navi サポート チーム](mailto:sys_ntabtm@nta.co.jp)と協力して、Shuccho Navi プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Shuccho Navi のサインオン URL にリダイレクトされます。
- Shuccho Navi のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Shuccho Navi] タイルを選択すると、このオプションは Shuccho Navi のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/shutterstock-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Shutterstock を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/shutterstock-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Shutterstock の間にシングル サインオンを構成する方法について学習します。

この記事では、Shutterstock と Microsoft Entra ID を統合する方法について説明します。 Shutterstock を Microsoft Entra ID と統合すると、次のことができます。

- Shutterstock にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Shutterstock に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Shutterstock でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Shutterstock では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの Shutterstock の追加

Microsoft Entra ID への Shutterstock の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Shutterstock を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Shutterstock**」と入力します。
4. 結果のパネルから **[Shutterstock]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Shutterstock 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Shutterstock で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Shutterstock の関連ユーザーとの間にリンク関係を確立する必要があります。

Shutterstock で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Shutterstock の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Shutterstock のテストユーザーを作成** - Shutterstock で B.Simon に対応するユーザーを作成し、そのユーザーが Microsoft Entra の B.Simon にリンクされていることを確認します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリ**&gt;**Shutterstock**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://accounts.shutterstock.com/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.shutterstock.com/saml/<CUSTOMER_ID>/callback`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://accounts.shutterstock.com/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Shutterstock クライアント サポート チーム](mailto:premierintegrations@shutterstock.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Shutterstock のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Shutterstock の SSO の構成

**Shutterstock** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Shutterstock サポート チーム](mailto:premierintegrations@shutterstock.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Shutterstock のテスト ユーザーの作成

このセクションでは、Shutterstock で Britta Simon というユーザーを作成します。 [Shutterstock サポート チーム](mailto:premierintegrations@shutterstock.com)と協力して、Shutterstock プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Shutterstock のサインオン URL にリダイレクトされます。
- Shutterstock のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Shutterstock に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Shutterstock] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Shutterstock に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sifulan-connect-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SIFULAN Connect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sifulan-connect-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-14
- Summary: Microsoft Entra ID と SIFULAN Connect の間のシングル サインオンを構成する方法について説明します。

この記事では、SIFULAN Connect と Microsoft Entra ID を統合する方法について説明します。 SIFULAN Connect と Microsoft Entra ID を統合すると、次のことができます。

- SIFULAN Connect にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SIFULAN Connect に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SIFULAN Connect でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SIFULAN Connect では、**SP** Initiated SSO をサポートしています。

### ギャラリーから SIFULAN Connect を追加する

Microsoft Entra ID への SIFULAN Connect の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SIFULAN Connect を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SIFULAN Connect**」と入力します。
4. 結果パネルから **[SIFULAN Connect]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO を SIFULAN Connect 用に構成してテストする

**B.Simon** というテスト ユーザーを使用して、SIFULAN Connect に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SIFULAN Connect の関連ユーザーとの間にリンク関係を確立する必要があります。

SIFULAN Connect に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SIFULAN Connect SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SIFULAN ConnectでB.Simonに対応するユーザーをMicrosoft Entra IDのユーザーにリンクさせ、SIFULAN Connect テスト ユーザーの作成を行います。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SIFULAN Connect**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<Sub-DomainName>/idp/shibboleth`

    b。 **[応答 URL]** ボックスに、`https://<Sub-DomainName>/idp/shibboleth` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<Sub-DomainName>/idp/profile/SAML2/POST/SSO`

    注意

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[SIFULAN Connect サポート チーム](mailto:support@sifulan.my)に連絡してください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. SIFULAN Connect アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]
7. その他に、SIFULAN Connect アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
    | urn:oid:2.16.840.1.113730.3.1.3 | ユーザー.社員ID |
8. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[SIFULAN Connect のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピーを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SIFULAN Connect SSO を構成する

**SIFULAN Connect** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [SIFULAN Connect サポート チーム](mailto:support@sifulan.my)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### SIFULAN Connect テスト ユーザーを作成する

このセクションでは、SIFULAN Connect で B.Simon というユーザーを作成します。 [SIFULAN Connect サポート チーム](mailto:support@sifulan.my)と協力して、SIFULAN Connect プラットフォームにこのユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、マイ アプリを使って Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる SIFULAN Connect サインオン URL にリダイレクトします。
- SIFULAN Connect のサインオン URL に直接移動し、そこからログイン フローを開始します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sigma-computing-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Sigma Computing を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sigma-computing-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から Sigma Computing に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Sigma Computing と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Sigma Computing](https://www.sigmacomputing.com/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Sigma Computing でユーザーを作成する
- アクセスが不要になった場合に Sigma Computing のユーザーを削除する
- Microsoft Entra ID と Sigma Computing の間でユーザー属性の同期を維持する
- Sigma Computing でグループとグループ メンバーシップを設定する
- Sigma Computing に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sigma-computing-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Sigma 組織の管理者アカウント。
- Sigma Computing との既存の [SSO](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sigma-computing-tutorial) 統合。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Sigma Computing の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするように Sigma Computing を構成する

1. Sigma アカウントにログインします。
2. ユーザー メニューの **[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)** を選択して、 **[Admin Portal](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理ポータル)** に移動します。
3. 左側のパネルで、[ **認証** ] を選択して組織の [認証] ページを開きます。
4. **[Authentication Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証方法)** が **[SAML]** のみであることを確認します。
5. [アカウントの種類] **と [チーム プロビジョニング**] の下にある **[セットアップ**] ボタンを選択して、[プロビジョニング] モーダルを開きます。

    [Image: アカウントの種類とチーム プロビジョニングのスクリーンショット。]
6. [Provisioning modal](プロビジョニング モーダル) の概要セクションに記載されている注意事項に目を通します。 確認ボックスをオンにし、[ **次へ** ] を選択して続行します。
7. トークン名を入力し、[ **次へ**] を選択します。

    [Image: [トークン名] と [次へ] オプションのスクリーンショット。]
8. Sigma から **[Bearer Token](ベアラー トークン)** と **[Directory Base URL](ディレクトリ ベース URL)** が提供されます。 これらの値をコピーし、安全な場所に保存します。 これらの値は、Sigma Computing アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。 **完了**を選択します。

    [Image: Sigma Computing アプリケーションのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Sigma Computing を追加する

Microsoft Entra アプリケーション ギャラリーから Sigma Computing を追加して、Sigma Computing へのプロビジョニングの管理を開始します。 SSO のために Sigma Computing を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Sigma Computing への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Sigma Computing の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Sigma Computing]** を選択します。

    [Image: アプリケーションの一覧の Sigma Computing リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Sigma Computing テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Sigma Computing に接続できることを確認します。 接続に失敗した場合は、Sigma Computing アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Sigma Computing に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Sigma Computing のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Sigma Computing API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | ユーザータイプ | 糸 |  |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
12. **[グループ] を選択します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Sigma Computing に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Sigma Computing のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | リファレンス |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sigma-computing-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Sigma Computing を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sigma-computing-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sigma Computing の間でシングル サインオンを構成する方法について説明します。

この記事では、Sigma Computing と Microsoft Entra ID を統合する方法について説明します。 Sigma Computing を Microsoft Entra ID と統合すると、次のことができます。

- Sigma Computing にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Sigma Computing に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sigma Computing でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Sigma Computing では、**SP および IDP** Initiated SSO がサポートされます。
- Sigma Computing では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Sigma Computing では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sigma-computing-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Sigma Computing の追加

Microsoft Entra ID への Sigma Computing の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Sigma Computing を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Sigma Computing**」と入力します。
4. 結果のパネルから **[Sigma Computing]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sigma Computing 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Sigma Computing に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Sigma Computing の関連ユーザーとの間にリンク関係を確立する必要があります。

Sigma Computing に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sigma Computing の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Sigma Computing のテストユーザーを作成** - Microsoft Entra のユーザー B.Simon に対応するユーザーを Sigma Computing 内に作成してリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sigma Computing**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://app.sigmacomputing.com/<CustomerOrg>` |
    | `https://aws.sigmacomputing.com/<CustomerOrg>` |

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[Sigma Computing クライアント サポート チーム](mailto:support@sigmacomputing.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Sigma Computing のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sigma Computing の SSO の構成

1. Sigma アカウントにログインします。
2. ユーザー メニューの **[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)** を選択して、 **[Admin Portal](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理ポータル)** に移動します。
3. 次のページで以下の手順を実行します。

    [Image: Sigma Computing の SSO セクションを構成する]

    ある。 左側のパネルで **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** ページを選択します。

    b。 **[Authentication Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証方法)** で **[SAML]** または **[SAML or password](SAML またはパスワード)** を選択します。

    c. **[ID プロバイダーのログイン URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を入力します。

    d. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[ID プロバイダー X509 証明書]** テキストボックスに貼り付けます。

    え **保存** を選択します。

#### Sigma Computing のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Sigma Computing に作成します。 Sigma Computing では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Sigma Computing にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

Sigma Computing では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sigma-computing-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Sigma Computing のサインオン URL にリダイレクトされます。
- Sigma Computing のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Sigma Computing に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Sigma Computing] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Sigma Computing に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sign-in-enterprise-host-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ホスト プロビジョニング用にサインイン エンタープライズを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sign-in-enterprise-host-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: Microsoft Entra ID から Sign In Enterprise に対してホストを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ホスト プロビジョニングを構成するためにサインイン エンタープライズ ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ホストとホスト グループを自動的にプロビジョニングおよび解除し、[Sign In Enterprise](https://signinenterprise.com) に接続します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Sign In Enterprise でホストを作成します。
- Sign In Enterprise でホスト グループとそのメンバーシップをプロビジョニングします。
- Sign In Enterprise で、アプリケーションから割り当てられていないホストを非表示としてマークします。
- アプリケーションから割り当てられていないホスト グループを削除します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者権限のある Sign In Enterprise のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID とサインイン エンタープライズの間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順2: Sign In Enterprise から SCIM Host Provisioning 情報を収集する

1. サインイン エンタープライズ アカウントの右上隅にある歯車アイコンを選択します。
2. **[基本設定] を選択します**。
3. [ **全般] タブ**で、[ **SCIM Host Provisioning]\(SCIM ホスト プロビジョニング** \) セクションに移動するまで下にスクロールします。 その後、URL とトークンの両方をコピーする必要があります。これは、以下の手順 5 で必要になります。

### 手順3: Microsoft Entra アプリケーション ギャラリーから Sign In Enterprise Host Provisioning を追加する

Microsoft Entra アプリケーション ギャラリーから Sign In Enterprise Host Provisioning を追加して、Sign In Enterprise へのプロビジョニングの管理を開始します。 以前に Sign In Enterprise を SSO 用に設定した場合は、それと同じアプリケーションを使用することはできません。 Sign In Enterprise Host Provisioning 用に別のアプリを作成する必要があります。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順5: Sign In Enterprise への自動ユーザー プロビジョニングを構成します。

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Sign In Enterprise のホスト プロビジョニングの自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧 **で、[サインイン エンタープライズ ホスト プロビジョニング**] を選択します。

    [Image: アプリケーションの一覧の [サインイン エンタープライズ ホスト プロビジョニング] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、サインイン Enterprise Host Provisioning テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID がサインイン Enterprise Host Provisioning に接続できることを確認します。 接続に失敗した場合は、サインイン Enterprise Host Provisioning アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID からサインイン エンタープライズ ホスト プロビジョニングに同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のためにサインイン Enterprise Host Provisioning のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、サインイン Enterprise Host Provisioning API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Sign In Enterprise Host Provisioning で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | emails[type eq "other"].value | 糸 |  |  |
12. **[グループ] を選択します**。
13. [属性マッピング] セクションで、Microsoft Entra ID からサインイン エンタープライズに同期されるグループ **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作のためにサインイン エンタープライズのグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Sign In Enterprise Host Provisioning で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/signagelive-provisioning-tutorial"} -->
## Signagelive を構成して、Microsoft Entra ID を使用した自動ユーザー プロビジョニングに対応させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/signagelive-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Signagelive に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するために Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、Signagelive と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Signagelive に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- [Signagelive テナント](https://signagelive.com/pricing/)
- 管理者アクセス許可がある Signagelive のユーザー アカウント。

### Signagelive へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Signagelive へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Signagelive に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Signagelive に割り当てる際の重要なヒント

- 単一の Microsoft Entra ユーザーを Signagelive に割り当てて、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- Signagelive にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### プロビジョニングのための Signagelive の設定

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Signagelive を構成する前に、Signagelive で SCIM プロビジョニングを有効にする必要があります。

[Signagelive](mailto:development@signagelive.com) に連絡して、SCIM のプロビジョニングを構成するために必要なシークレット トークンを入手します。

### ギャラリーからの Signagelive の追加

Microsoft Entra ID での自動ユーザー プロビジョニング用に Signagelive を構成するには、Signagelive を Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Signagelive を追加するには、以下の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Signagelive**」と入力し、**[Signagelive]** を選択します。
4. 結果のパネルから **[Signagelive]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: Signagelive の結果リスト]

### Signagelive への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて Signagelive でユーザーやグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Signagelive のシングル サインオンに関する記事で説明されている手順に従って、Signagelive に対して SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/signagelive-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Signagelive の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Signagelive]** を選択します。

    [Image: アプリケーションの一覧の Signagelive リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Signagelive テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Signagelive に接続できることを確認します。 接続に失敗した場合は、Signagelive アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    ` https://samlapi.signagelive.com/scim/v2` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Signagelive に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Signagelive のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Signagelive API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: 7 つのマッピングが表示されている [属性マッピング] セクションのスクリーンショット。]
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Signagelive に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Signagelive のグループ アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: 3 つのマッピングが表示されている [属性マッピング] セクションのスクリーンショット。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。
<!-- /MSL-PAGE -->
