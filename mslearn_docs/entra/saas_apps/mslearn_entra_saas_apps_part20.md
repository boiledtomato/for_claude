# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 20)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 56

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/riva-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にリーバを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/riva-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Riva 間にシングル サインオンを構成する方法について説明します。

この記事では、Riva と Microsoft Entra ID を統合する方法について説明します。 Riva を Microsoft Entra ID と統合すると、次のことができます:

- Riva にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Riva に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Riva でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Riva では、**IDP** によって開始される SSO がサポートされます

### ギャラリーからの Riva の追加

Microsoft Entra ID への Riva の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Riva を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Riva**」と入力します。
4. 結果のパネルから **[Riva]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Riva の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Riva で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Riva の関連ユーザーとの間にリンク関係を確立する必要があります。

Riva で Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Riva SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Riva テストユーザーを作成** - Microsoft Entra の B.Simon とリンクされる Riva 内での対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Riva**&gt;**シングルサインオン**に移動する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Riva のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Riva SSO の構成

**Riva** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Riva サポート チーム](mailto:support@rivacrmintegration.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Riva テスト ユーザーの作成

このセクションでは、Riva で B.Simon というユーザーを作成します。 [Riva サポート チーム](mailto:support@rivacrmintegration.com)と連携して、Riva プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [リバ] タイルを選択すると、SSO を設定した Riva に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rivial-cybersecurity-management-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Rivial Cybersecurity Management Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rivial-cybersecurity-management-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rivial Cybersecurity Management Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Rivial Cybersecurity Management Platform と Microsoft Entra ID を統合する方法について説明します。 Rivial Cybersecurity Management Platform と Microsoft Entra ID を統合すると、次のことができます。

- Rivial Cybersecurity Management Platform にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Rivial Cybersecurity Management Platform に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Rivial Cybersecurity Management Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Rivial Cybersecurity Management Platform では、 **SP** によって開始される SSO のみがサポートされます。

### ギャラリーから Rivial Cybersecurity Management Platform を追加する

Microsoft Entra ID への Rivial Cybersecurity Management Platform の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Rivial Cybersecurity Management Platform を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Rivial Cybersecurity Management Platform**」と入力します。
4. 結果パネルから **Rivial Cybersecurity Management Platform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Rivial Cybersecurity Management Platform の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Rivial Cybersecurity Management Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Rivial Cybersecurity Management Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Rivial Cybersecurity Management Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rivial Cybersecurity Management Platform の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Rivial Cybersecurity Management Platform のテスト ユーザーの作成** - Rivial Cybersecurity Management Platform で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Rivial Cybersecurity Management Platform**&gt;**シングルサインオンに移動します**。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:amazon:cognito:sp:us-west-2_<Rivial_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://rivialsecurity.auth.us-west-2.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://rivialsecurity.auth.us-west-2.amazoncognito.com`

    注

    識別子は実際のものではありません。 実際の識別子で値を更新します。 この値を取得するには [、Rivial Cybersecurity Management Platform サポート チーム](mailto:support@rivialsecurity.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rivial Cybersecurity Management Platform の SSO の構成

**Rivial Cybersecurity Management Platform** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Rivial Cybersecurity Management Platform サポート チーム](mailto:support@rivialsecurity.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Rivial Cybersecurity Management Platform のテスト ユーザーの作成

このセクションでは、Rivial Cybersecurity Management Platform で B.Simon というユーザーを作成します。 [Rivial Cybersecurity Management Platform サポート チーム](mailto:support@rivialsecurity.com)と協力して、Rivial Cybersecurity Management Platform プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Rivial Cybersecurity Management Platform のサインオン URL にリダイレクトします。
- Rivial Cybersecurity Management Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Rivial Cybersecurity Management Platform] タイルを選択すると、このオプションは Rivial Cybersecurity Management Platform のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/roadmunk-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Roadmunk を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/roadmunk-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Roadmunk 間にシングル サインオンを構成する方法について説明します。

この記事では、Roadmunk と Microsoft Entra ID を統合する方法について説明します。 Roadmunk を Microsoft Entra ID と統合すると、次のことができます。

- Roadmunk にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Roadmunk に自動的にサインインできるようにします。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Roadmunk サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

Roadmunk では、"*サービス プロバイダー*" (SP) と "*ID プロバイダー*" (IDP) によって開始される SSO がサポートされます。

### ギャラリーからの Roadmunk の追加

Roadmunk を Microsoft Entra ID に統合するには、ギャラリーからマネージド SaaS アプリの一覧に Roadmunk を追加します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションの検索ボックスに「**Roadmunk**」と入力します。
4. 結果から **[Roadmunk]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Roadmunk 用の Microsoft Entra SSO の構成とテスト

*B.Simon* というテスト ユーザーを使って、Roadmunk に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Roadmunk の関連ユーザーとの間にリンク関係を確立する必要があります。

Roadmunk に対して Microsoft Entra SSO を構成してテストする方法の概要は次のとおりです。

1. Microsoft Entra SSO を構成して、ユーザーがこの機能を使用できるようにします。
    1. B.Simon を使用して Microsoft Entra SSO をテストする Microsoft Entra テスト ユーザーを作成します。
    2. B.Simon が Microsoft Entra SSO を使用できるように、Microsoft Entra テスト ユーザーを割り当てます。
2. Roadmunk SSO を構成して、アプリケーション側で SSO 設定を構成します。
    1. Roadmunk のテスト ユーザーを作成し、Roadmunk の B.Simon に対応するユーザーを、Microsoft Entra のこのユーザーにリンクできるようにします。
3. SSO をテストし、構成が機能することを確認します。

### Microsoft Entra SSO の構成

Azure portal で、次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Roadmunk** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、**シングル サインオン**を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** のペン アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] の [編集] アイコンを示すスクリーンショット。]
5. SP メタデータ ファイルがあり、IDP-initiated モードで構成する場合は、 **[基本的な SAML 構成]** セクションで次の手順に従います。

    エー。 **[メタデータ ファイルをアップロードする]** を選択します。

    [Image: メタデータ ファイルをアップロードするためのリンクを示すスクリーンショット。]

    b。 フォルダー アイコンを選択して、「Roadmunk SSO の構成」手順の手順 4 でダウンロードしたメタデータ ファイルを選択します。 **[アップロード]**を選択します。

    [Image: メタデータ ファイルの選択方法を示すスクリーンショット。]

    メタデータ ファイルがアップロードされると、 **[基本的な SAML 構成]** セクションの **[識別子]** と **[応答 URL]** の値が自動的に設定されます。

    [Image: [基本的な SAML 構成] セクションを示すスクリーンショット。[識別子] フィールドと [応答 URL] フィールドが強調表示されています。]

    注意

    **[識別子]** と **[応答 URL]** の値が自動的に設定されない場合は、手動で値を入力してください。
6. アプリケーションを SP-initiated モードで構成する場合は、 **[追加の URL を設定します]** を選択します。 **[サインオン URL]** フィールドに、「`https://login.roadmunk.com`」と入力します。

    [Image: SP-initiated モード用のサインオン URL を設定する場所を示すスクリーンショット。]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を見つけます。 次に、 **[ダウンロード]** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: SAML 署名証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Roadmunk のセットアップ]** セクションで、必要な URL をコピーします。

    [Image: 構成 URL をコピーする場所を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Roadmunk SSO の構成

1. Roadmunk の Web サイトに管理者としてサインインします。
2. ページ下部のユーザー アイコンを選択し、 **[Account Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント設定)** を選択します。

    [Image: ユーザー アカウント設定を選択する場所を示すスクリーンショット。]
3. **[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社)**&gt;**[Authentication Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の設定)** に移動します。
4. **[Authentication Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の設定)** ページで、次の手順に従います。

    [Image: [Authentication Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の設定) ページを示すスクリーンショット。]

    エー。 **[SAML Single Sign On (SSO)](SAML シングル サインオン (SSO))** をオンにします。

    b。 **[Step 1](手順 1)** セクションで、メタデータ XML ファイルをアップロードするか、メタデータの URL を指定します。

    c. **[Step 2](手順 2)** セクションで、Roadmunk メタデータ ファイルをダウンロードしてお使いのコンピューターに保存します。

    d. SSO を使用してサインインする場合は、 **[Step 3](手順 3)** セクションで、 **[Enforce SAML Sign-In Only](SAML サインインのみを適用する)** を選択します。

    え **[保存]** を選択します。

#### Roadmunk のテスト ユーザーの作成

1. Roadmunk の Web サイトに管理者としてサインインします。
2. ページ下部のユーザー アイコンを選択し、 **[Account Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント設定)** を選択します。

    [Image: テスト ユーザーの [Account Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント設定) を開く方法を示すスクリーンショット。]
3. **[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** タブを開き、 **[Invite User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの招待)** を選択します。

    [Image: [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) タブを示すスクリーンショット。[Invite User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの招待) ボタンが強調表示されています。開かれているウィンドウでは、[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メール) および [Role](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ロール) フィールドが強調表示されています。]
4. 表示されたフォームに必要な情報を入力し、 **[Invite](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/招待)** を選択します。

### SSO のテスト

このセクションでは、アクセス パネルを使って Microsoft Entra SSO の構成をテストします。

マイ アプリ ポータルで **[Roadmunk]** タイルをクリックすると、SSO を設定した Roadmunk アカウントに自動的にサインインします。 詳細については、「[マイ アプリ ポータルからアプリにサインインして開始する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/robin-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Robin を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/robin-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Robin Powered に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、Robin と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Robin に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Robin テナント](https://robinpowered.com/pricing/)。
- Admin アクセス許可がある Robin のユーザー アカウント

### Robin へのユーザーの割り当て

Microsoft Entra ID では、 *割り当て* と呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Robin へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Robin に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Robin に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Robin に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Robin にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Robin を設定する

1. [Robin 管理コンソール](https://dashboard.robinpowered.com/login)にサインインします。 **[Manage](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) &gt; [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) &gt; [SCIM] &gt; [Manage](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)** に移動します。

    [Image: robin powered Admin Console]
2. 新しい組織トークンを生成します。 このトークンを紛失しても、既存のユーザーに影響を与えることなく、いつでも新しいトークンを作成できます。

    [Image: ロビンパワード SCIM を追加]
3. **SCIM 認証トークン**をコピーします。 この値は、Robin アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

### ギャラリーからの Robin の追加

Microsoft Entra ID での自動ユーザー プロビジョニング用に Robin を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションのリストに Robin を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Robin を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、「**Robin**」と入力し、検索ボックスで **Robin** を選択します。
4. 結果パネルから **Robin** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

    [Image: Robin は結果一覧にあります。]

### Robin への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて、Robin でユーザーやグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Robin のシングル サインオンに関する記事で説明されている手順に従って、Robin に対して SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/robin-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra ID で Robin の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動する

    [Image: エンタープライズ アプリケーション ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Robin を選択**します。

    [Image: アプリケーションの一覧の Robin リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Robin テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Robin に接続できることを確認します。 接続に失敗した場合は、Robin アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    `https://api.robinpowered.com/v1.0/scim-2` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Robin に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Robin のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Robin API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: Robin を利用したユーザー属性のスクリーンショット。]
12. [属性マッピング] セクションで、Microsoft Entra ID から Robin に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Robin のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: Robin を使用したグループ属性のスクリーンショット。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/robin-tutorial"} -->
## Microsoft Entra ID で Robin for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/robin-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Robin 間にシングル サインオンを構成する方法について説明します。

この記事では、Robin と Microsoft Entra ID を統合する方法について説明します。 Robin と Microsoft Entra ID を統合すると、次のことができます:

- Robin にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Robin に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Robin でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Robin では、**SP によって開始される SSO と IDP によって開始される SSO** がサポートされます。
- Robin では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- Robin では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/robin-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Robin の追加

Microsoft Entra ID への Robin の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Robin を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDエンタープライズ アプリ新しいアプリケーションに移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Robin**」と入力します。
4. 結果パネルから **Robin** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Robin 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Robin に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Robin の関連ユーザーとの間にリンク関係を確立する必要があります。

Robin に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Robin SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Robin テストユーザーを作成** - Microsoft Entra のユーザー表示にリンクされた、Robin で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Robin**&gt;**シングルサインオン**
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://dashboard.robinpowered.com/`
7. Robin アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Robin アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | Email | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Robin のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Robin SSO の構成

**Robin** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [Robin サポート チーム](mailto:support@robinpowered.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Robin のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Robin に作成します。 Robin では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Robin にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

Robin では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/robin-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Robin Sign on URL にリダイレクトされます。
- Robin のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Robin に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Robin] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Robin に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rocketreach-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に RocketReach SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rocketreach-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RocketReach SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、RocketReach SSO と Microsoft Entra ID を統合する方法について説明します。 RocketReach SSO と Microsoft Entra ID を統合すると、次のことができます。

- RocketReach SSO にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して RocketReach SSO に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- RocketReach SSO でのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RocketReach SSO では、**SP** および **IDP** による SSO の開始がサポートされます。
- RocketReach SSO では、**Just In Time** ユーザープロビジョニングがサポートされます。

### ギャラリーからの RocketReach SSO の追加

Microsoft Entra ID への RocketReach SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に RocketReach SSO を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**RocketReach SSO**」と入力します。
4. 結果のパネルから RocketReach SSO  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### RocketReach SSO の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、RocketReach SSO に対する Microsoft Entra SSO を構成・テストします。 SSO を機能させるには、Microsoft Entra ユーザーと RocketReach SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

RocketReach SSO で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RocketReach SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **RocketReach SSO のテストユーザー作成** - Microsoft Entra における B.Simon の対応ユーザーを RocketReach SSO で作成します。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[RocketReach SSO]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://rocketreach.co/login/sso` を入力します。
7. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットには、「証明書のダウンロード」リンクが表示されています。]
8. **RocketReach SSO** の設定] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成の適切な URL をコピーすることを示しています。] のメタデータ

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RocketReach SSO の構成

**RocketReach SSO** 側でシングルサインオンを構成するには、以下の手順に従う必要があります。

1. チーム所有者またはチーム管理者として RocketReach.co にログインします。
2. [&gt;] セクションに進み、[**SSO のセットアップ**] ボタンを選択します。
3. サイドバー メニュー **Azure** を選択します。
4. Microsoft Entra プラットフォームから URL を **ログイン URL** フィールドと **Azure AD 識別子** フィールドにコピーします。
5. Microsoft Entra の **証明書 (Base64)** ファイルの内容を \*\*[Key x509 Certificate]\(キー x509 証明書\) フィールドに貼り付けます。
6. SAML 接続をテストし、変更を保存します。

#### RocketReach SSO テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを RocketReach SSO に作成します。 RocketReach SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 このメール アドレスを持つユーザーが既に存在する場合は、RocketReach の適切なチームに割り当てる必要があります。 RocketReach にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる RocketReach SSO サインオン URL にリダイレクトされます。
- RocketReach SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RocketReach SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [RocketReach SSO] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した RocketReach SSO に自動的にサインインされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rolemapper-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に RoleMapper を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rolemapper-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RoleMapper の間のシングル サインオンを構成する方法について説明します。

この記事では、RoleMapper と Microsoft Entra ID を統合する方法について説明します。 RoleMapper を Microsoft Entra ID と統合すると、次のことが可能になります。

- RoleMapper へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで RoleMapper に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RoleMapper でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RoleMapper では、**SP および IDP による SSO の開始**がサポートされます。
- RoleMapper では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの RoleMapper の追加

Microsoft Entra ID への RoleMapper の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に RoleMapper を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「RoleMapper**」と入力します。
4. 結果パネルから **RoleMapper** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RoleMapper に対する Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、RoleMapper に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと RoleMapper の関連ユーザーの間にリンク関係を確立する必要があります。

RoleMapper に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RoleMapper SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RoleMapper テスト ユーザーの作成 - RoleMapper** に B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon 表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[RoleMapper]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `api.role-mapper.com/sso/<CustomerName>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.role-mapper.com/sso/saml2/<CustomerName>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.role-mapper.com/sso/<CustomerName>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、RoleMapper サポート チーム](mailto:support@rolemapper.tech) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **RoleMapper のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RoleMapper SSO を構成する

**RoleMapper** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [RoleMapper サポート チーム](mailto:support@rolemapper.tech)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### RoleMapper テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを RoleMapper に作成します。 RoleMapper では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 RoleMapper にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる RoleMapper のサインオン URL にリダイレクトされます。
- RoleMapper のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した RoleMapper に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで RoleMapper タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した RoleMapper に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rolepoint-tutorial"} -->
## Microsoft Entra ID を使用して RolePoint for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rolepoint-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: この記事では、Microsoft Entra ID と RolePoint の間でシングル サインオンを構成する方法について説明します。

この記事では、RolePoint と Microsoft Entra ID を統合する方法について説明します。 RolePoint と Microsoft Entra ID を統合すると、次のことができます。

- RolePoint にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って RolePoint に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオンが有効な RolePoint サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- RolePoint では、SP Initiated SSO がサポートされます。

### ギャラリーからの RolePoint の追加

Microsoft Entra ID への RolePoint の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに RolePoint を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **ギャラリーから追加する**セクションで、検索ボックスに、**RolePoint**と入力します。
4. 結果のパネルから **RolePoint** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RolePoint 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、RolePoint に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと RolePoint の関連ユーザーとの間にリンク関係を確立する必要があります。

RolePoint に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RolePoint の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RolePoint テストユーザーを作成** - RolePoint で B.Simon に対応するユーザーの作成を行い、Microsoft Entra のユーザー表象にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**RolePoint**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ダイアログ ボックスで、次の手順を実行します:

    1. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します:

        `https://app.rolepoint.com/<instancename>`
    2. **[サインオン URL]** ボックスに、 という形式で URL を入力します。

        `https://<subdomain>.rolepoint.com/login`

    注

    これらの値はプレースホルダーです。 実際の識別子とサインオン URL を使用する必要があります。 識別子には一意の文字列値を使用することをお勧めします。 これらの値を取得するには、[RolePoint サポート チーム](mailto:info@rolepoint.com)にお問い合わせください。 **[基本的な SAML 構成]** ダイアログ ボックスに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、実際の要件に従って、 **[フェデレーション メタデータ XML]** の横にある **[ダウンロード]** リンクを選択し、ファイルを自分のコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[RolePoint のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RolePoint SSO の構成

RolePoint 側にシングル サインオンを設定するには、[RolePoint サポート チーム](mailto:info@rolepoint.com)と連携する必要があります。 このチームに、取得したフェデレーション メタデータ XML ファイルと URL を送信します。 SAML SSO 接続が両方の側で正しく設定されるように、RolePoint が構成されます。

#### RolePoint のテスト ユーザーの作成

次に、RolePoint で Britta Simon というユーザーを作成する必要があります。 [RolePoint サポート チーム](mailto:info@rolepoint.com)と連携して、RolePoint にユーザーを追加してください。 シングル サインオンを使用できるようにするには、ユーザーを作成してアクティブにする必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる RolePoint のサインオン URL にリダイレクトされます。
- RolePoint Sign-on URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [RolePoint] タイルを選択すると、このオプションは RolePoint のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rollbar-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Rollbar を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rollbar-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Rollbar に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Rollbar と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Rollbar](https://rollbar.com/pricing/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Rollbar でユーザーを作成する
- アクセスが不要になった場合に Rollbar でユーザーを削除する
- Microsoft Entra ID と Rollbar の間でユーザー属性の同期を維持する
- Rollbar でグループとグループメンバーシップをプロビジョニングする
- Rollbar への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rollbar-tutorial) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- Enterprise プランを契約している[Rollbar テナント](https://rollbar.com/pricing/)。
- Admin アクセス許可がある Rollbar のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Rollbar の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Rollbar を構成する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Rollbar を構成する前に、Rollbar で SCIM プロビジョニングを有効にする必要があります。

1. [Rollbar 管理コンソール](https://rollbar.com/login/)にサインインします。 [ **アカウント設定] を選択します**。

    [Image: Rollbar 管理コンソール]
2. **[Rollbar Tenant Name](Rollbar テナント名) &gt; [ID プロバイダー]** の順に移動します。

    [Image: Rollbar ID プロバイダー]
3. **[プロビジョニングのオプション]** まで下にスクロールします。 アクセス トークンをコピーします。 この値は、Rollbar アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。 [ **ユーザーとチームのプロビジョニングを有効にする** ] チェック ボックスをオンにし、[ **保存]** を選択します。

    [Image: Rollbar アクセス トークン]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Rollbar を追加する

Microsoft Entra アプリケーション ギャラリーから Rollbar を追加して、Rollbar へのプロビジョニングの管理を開始します。 SSO のために Rollbar を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Rollbar への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Rollbar の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Rollbar]** を選択します。

    [Image: アプリケーションの一覧の [Rollbar] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Rollbar テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Rollbar に接続できることを確認します。 接続に失敗した場合は、Rollbar アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Rollbar に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Rollbar のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Rollbar API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | externalId | 糸 |
    | 活動中 | ブール値 |
    | name.familyName | 糸 |
    | name.givenName | 糸 |
    | emails[type eq "work"] | 糸 |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Rollbar に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Rollbar のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

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
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rollbar-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Rollbar を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rollbar-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rollbar 間にシングル サインオンを構成する方法について学習します。

この記事では、Rollbar と Microsoft Entra ID を統合する方法について説明します。 Rollbar と Microsoft Entra ID を統合すると、次のことができます。

- Rollbar にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Rollbar に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Rollbar でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Rollbar では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Rollbar では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rollbar-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Rollbar を追加する

Microsoft Entra ID への Rollbar の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Rollbar を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Rollbar**」と入力します。
4. 結果のパネルから **[Rollbar]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Rollbar 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Rollbar に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Rollbar の関連ユーザーとの間にリンク関係を確立する必要があります。

Rollbar に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rollbar の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Rollbar テストユーザーを作成し、B.Simon に対応する Microsoft Entra ユーザーにリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Rollbar**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://saml.rollbar.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://rollbar.com/<ACCOUNT_NAME>/saml/sso/azure/`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://rollbar.com/<ACCOUNT_NAME>/saml/login/azure/`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、[Rollbar クライアント サポート チーム](mailto:support@rollbar.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Rollbar のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rollbar の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Rollbar 企業サイトに管理者としてサインインします。
2. 右上隅にある **[プロファイル設定]** を選択し、[ **アカウント名の設定]** を選択します。

    [Image: [Profile Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロファイル設定) から選択されたアカウント名の設定を示すスクリーンショット。]
3. [セキュリティ **] で [ID プロバイダー] を選択します** 。

    [Image: [SECURITY](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ) の [Identity Provider](ID プロバイダー) が選択された画面のスクリーンショット。]
4. **[SAML Identity Provider](SAML ID プロバイダー)** セクションで、次の手順に従います。

    [Image: [SAML Identity Provider](SAML ID プロバイダー) を示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[SAML Identity Provider](SAML ID プロバイダー)** ドロップダウンから **[AZURE]** を選択します。

    b。 メタデータ ファイルをメモ帳で開き、その内容をクリップボードにコピーし、 **[SAML Metadata](SAML メタデータ)** テキストボックスに貼りつけます。

    c. **保存** を選択します。
5. 保存ボタンを選択すると、画面は次のようになります。

    [Image: [SAML Identity Provider](SAML ID プロバイダー) ページの結果を示すスクリーンショット。]

    注

    次の手順を行うには、最初に、Azure で自分自身を Rollbar アプリにユーザーとして追加する必要があります。

    ある。 すべてのユーザーに Azure による認証を要求する場合は、 **ID プロバイダー経由でログイン** して Azure 経由で再認証を行います。

    b。 画面に戻った後、 **[Require login via SAML Identity Provider](SAML ID プロバイダーによるログインを要求する)** チェック ボックスをオンにします。

    b。 **保存** を選択します。

#### Rollbar のテスト ユーザーの作成

Microsoft Entra ユーザーが Rollbar にサインインできるようにするには、Rollbar にプロビジョニングする必要があります。 Rollbar の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. Rollbar 企業サイトに管理者としてサインインします。
2. 右上隅にある **[プロファイル設定]** を選択し、[ **アカウント名の設定]** を選択します。

    [Image: 利用者]
3. **ユーザー**を選択します。

    [Image: 従業員の追加]
4. [ **チーム メンバーの招待]** を選択します。

    [Image: [Invite Team Members](チーム メンバーの招待) オプションが選択された画面のスクリーンショット。]
5. テキストボックスに、 **brittasimon@contoso.com** などのユーザーの名前を入力し、[ **追加/招待**] を選択します。

    [Image: メンバーの [Add/Invite](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加/招待) および指定されたアドレスを示すスクリーンショット。]
6. ユーザーが招待状を受け取り、承認すると、システムにそのユーザーが作成されます。

注

Rollbar では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rollbar-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Rollbar のサインオン URL にリダイレクトされます。
- Rollbar のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Rollbar に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Rollbar] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Rollbar に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rootly-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Rootly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rootly-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Microsoft Entra ID から Rootly にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Rootly ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [Rootly に自動的に](https://rootly.com/) プロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Rootly でユーザーを作成します。
- アクセスが不要になった場合は、Rootly でユーザーを削除します。
- Microsoft Entra ID と Rootly の間でユーザー属性の同期を維持します。
- Rootly に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rootly-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Rootly のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- [Microsoft Entra ID と Rootly の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Rootly を構成する

1. 無料の [Rootly アカウントを](https://rootly.com) 作成します。
2. プランで SCIM が許可されていない場合は、Rootly サポートに連絡して SCIM を有効にしてください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Rootly を追加する

Microsoft Entra アプリケーション ギャラリーから Rootly を追加して、Rootly へのプロビジョニングの管理を開始します。 SSO 用に Rootly を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Rootly への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Rootly でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Rootly の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Rootly**] を選択します。

    [Image: アプリケーションの一覧の [Rootly] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Rootly テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Rootly に接続できることを確認します。 接続に失敗した場合は、Rootly アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    Note

    `https://rootly.com/scim` に「」と入力します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Rootly に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Rootly のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Rootly API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Rootly で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  |  |
12. [属性マッピング] セクションで、Microsoft Entra ID から Rootly に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Rootly のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Rootly で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
    | externalId | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rootly-tutorial"} -->
## Microsoft Entra ID で Rootly for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rootly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Rootly の間のシングル サインオンを構成する方法について説明します。

この記事では、Rootly と Microsoft Entra ID を統合する方法について説明します。 Rootly を Microsoft Entra ID と統合すると、次のことが可能になります。

- Rootly にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Rootly に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Rootly でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Rootly では、**SP**および**IDP**によって開始されるシングルサインオン（SSO）がサポートされます。
- Rootly では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Rootly では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rootly-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Rootly の追加

Microsoft Entra ID への Rootly の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Rootly を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Rootly**」と入力します。
4. 結果パネルから **Rootly** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Rootly に対する Microsoft Entra SSO を構成・検証する

**B.Simon** というテスト ユーザーを使用して、Rootly に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Rootly の関連ユーザーとの間にリンク関係を確立する必要があります。

Rootly に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Rootly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Rootly のテストユーザーを作成する - Microsoft Entra の B.Simon にリンクされている B.Simon 対応ユーザーを Rootly で作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Rootly]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. (省略可能) **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://rootly.com/sso`
7. Rootly アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、Rootly アプリケーションの画像を示しています。]
8. その他に、Rootly アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | user.displayname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **Rootly のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成を適切な U R L にコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Rootly SSO の構成

以前にコピーした URL と PEM 証明書を使用して、 **Rootly** 側でシングル サインオンを構成します。

1. ID プロバイダーの ID: `Microsoft Entra Identifier`
2. ID ログイン URL: `Your Login URL`
3. ID ログアウト URL: `Your Login URL`
4. IDP 証明書: `Content of your Certificate PEM file`
5. ドメイン名: `Your company domain used for SSO`

次に、 `Enable and require SSO` を有効にし、SSO セットアップを Rootly に保存します。

#### Rootly テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Rootly に作成します。 Rootly では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Rootly にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Rootly Sign-On URL にリダイレクトされます。
- Rootly のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Rootly に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Rootly] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション Sign-On ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Rootly に自動的にサインインされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rsa-archer-suite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に RSA Archer Suite を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rsa-archer-suite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RSA Archer Suite の間にシングル サインオンを構成する方法について説明します。

この記事では、RSA Archer Suite と Microsoft Entra ID を統合する方法について説明します。 RSA Archer Suite と Microsoft Entra ID を統合すると、次のことができます。

- RSA Archer Suite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って RSA Archer Suite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RSA Archer Suite でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RSA Archer Suite では、**SP** Initiated SSO がサポートされます。
- RSA Archer Suite では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの RSA Archer Suite の追加

Microsoft Entra ID への RSA Archer Suite の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに RSA Archer Suite を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**RSA Archer Suite**」と入力します。
4. 結果のパネルから **[RSA Archer Suite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RSA Archer Suite 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、RSA Archer Suite に対して Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと RSA Archer Suite の関連ユーザーとの間にリンク関係を確立する必要があります。

RSA Archer Suite に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RSA Archer Suite の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RSA Archer Suite のテスト ユーザーの作成** - Microsoft Entra のユーザーの表現として、RSA Archer Suite で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**RSA Archer Suite**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `RSAArcherSuite_TENANT_STRING`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<BASE_URL>/default.aspx?IDP=<REALM_NAME>`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得する場合は、[RSA Archer Suite クライアント サポート チーム](mailto:archersupport@rsa.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. RSA Archer Suite アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、RSA Archer Suite アプリケーションでは、以下のような、いくつかの属性が SAML 応答で返されることが想定されています。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | 電話番号 | ユーザー.電話番号 |
    | 市区町村 | ユーザーの都市 |
    | 郵便番号 | ユーザー.郵便番号 |
    | 状態 | ユーザーの状態 |
    | 通り | ユーザーの住所 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **Set up RSA Archer Suite(RSA Archer Suite の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RSA Archer Suite の SSO を構成する

1. 管理者として、別のブラウザーで RSA Archer Suite の Web サイトにサインインします。
2. 次のページで、以下の手順を実行します。

    [Image: RSA Archer Suite の SSO を構成する。]

    ある。 **[シングル サインオン]** タブに移動し、ドロップダウンから **[シングル サインオン モード]** として **[SAML]** を選択します。

    b。 **[Allow manual bypass](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/手動バイパスを許可する)** チェックボックスをオンにします。

    c. **[Instance Entity ID](インスタンス エンティティ ID)** テキストボックスに、有効な名前を入力します。

    d. **拇印の値**を、 **[証明書の拇印]** テキストボックスに貼り付けます。

    え [ **選択** ] ボタンを選択し、ダウンロードした **フェデレーション メタデータ XML** ファイルを Azure portal からアップロードします。

    f. シングル サインオンの設定を**保存**します。

#### RSA Archer Suite のテスト ユーザーを作成する

このセクションでは、B. Simon というユーザーを RSA Archer Suite に作成します。 RSA Archer Suite では、Just-In-Time ユーザー プロビジョニングがサポートされており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 RSA Archer Suite にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる RSA Archer Suite のサインオン URL にリダイレクトされます。
- RSA Archer Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [RSA Archer Suite] タイルを選択すると、このオプションは RSA Archer Suite のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rstudio-connect-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に RStudio Connect SAML Authentication を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rstudio-connect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RStudio Connect SAML Authentication の間でシングル サインオンを構成する方法について説明します。

この記事では、RStudio Connect SAML Authentication と Microsoft Entra ID を統合する方法について説明します。 RStudio Connect SAML Authentication と Microsoft Entra ID を統合すると、次のことが可能になります。

- RStudio Connect SAML Authentication にアクセスできるユーザーを Microsoft Entra ID で管理できます。
- ユーザーが自分の Microsoft Entra アカウントで RStudio Connect SAML Authentication に自動的にサインインするように設定できます。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RStudio Connect SAML Authentication。 [45 日間の無料評価](https://www.rstudio.com/products/connect/)があります。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- RStudio Connect の SAML 認証では、**SP および IDP により開始される SSO** がサポートされます。
- RStudio Connect SAML Authentication では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの RStudio Connect SAML Authentication の追加

Microsoft Entra ID への RStudio Connect SAML Authentication の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に RStudio Connect SAML Authentication を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「RStudio Connect SAML Authentication**」と入力します。
4. 結果パネルから **RStudio Connect SAML Authentication** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RStudio Connect SAML Authentication 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、RStudio Connect SAML Authentication に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと RStudio Connect SAML Authentication の関連ユーザーとの間にリンク関係を確立する必要があります。

RStudio Connect SAML Authentication に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RStudio Connect SAML Authentication SSO の構成**- アプリケーション側で単一 Sign-On 設定を構成します。
    1. **RStudio Connect SAML Authentication テストユーザーの作成** - RStudio Connect SAML Authentication で Britta Simon に対応するユーザーを作成し、Microsoft Entra のユーザーとしてリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RStudio Connect SAML Authentication**&gt;**シングル サインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **[基本的な SAML 構成]** セクションで、**IDP** 開始モードでアプリケーションを構成する場合は、次の手順に従って、`<example.com>`を RStudio Connect SAML 認証サーバーのアドレスとポートに置き換えます。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<example.com>/__login__/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<example.com>/__login__/saml/acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<example.com>/`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 それらは、RStudio Connect SAML Authentication のサーバー アドレス (上記例の `https://example.com`) から判別されます。 問題が発生した場合は、 [RStudio Connect SAML 認証サポート チーム](mailto:support@rstudio.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. RStudio Connect SAML Authentication アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 RStudio Connect SAML Authentication アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
8. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RStudio Connect SAML Authentication の SSO の構成

**RStudio Connect SAML Authentication** のシングル サインオンを構成するには、上記で使用した**アプリのフェデレーション メタデータ URL** と**サーバー アドレス**を使用する必要があります。 これは、`/etc/rstudio-connect.rstudio-connect.gcfg` にある RStudio Connect SAML Authentication 構成ファイルで行います。

これは、構成ファイルの例です。

```
[Server]
SenderEmail =

; Important! The user-facing URL of your RStudio Connect SAML Authentication server.
Address = 

[Http]
Listen = :3939

[Authentication]
Provider = saml

[SAML]
Logging = true

; Important! The URL where your IdP hosts the SAML metadata or the path to a local copy of it placed in the RStudio Connect SAML Authentication server.
IdPMetaData = 

IdPAttributeProfile = azure
SSOInitiated = IdPAndSP
```

`IdPAttributeProfile = azure`場合、プロファイルは NameIDFormat を永続的な設定に設定し、構成[ファイル](https://docs.rstudio.com/connect/admin/authentication/saml/#the-azure-profile)で定義されている他の指定された属性をオーバーライドします。

これは、RStudio Connect API を使用して事前にユーザーを作成し、ユーザーが初めてログインする前にアクセス許可を適用する場合に問題になります。 NameIDFormat は emailAddress かその他の一意の識別子に設定します。永続に設定されると、値がハッシュされ、その値が何であるか、事前にわからなくなるためです。 そのため、API の使用は機能しません。 SAML のユーザーを作成するための API: https://docs.rstudio.com/connect/api/#post-/v1/users

そのため、この状況では、構成ファイルにこれを含めることが推奨されます。

```
[SAML]
NameIDFormat = emailAddress
UniqueIdAttribute = NameID
UsernameAttribute = http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name
FirstNameAttribute = http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname
LastNameAttribute = http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname
EmailAttribute = http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailAddress
```

の値に`Server.Address`を格納し、値に`SAML.IdPMetaData`格納します。 このサンプル構成では、暗号化されていない HTTP 接続を使用しますが、Microsoft Entra ID では暗号化された HTTPS 接続を使用する必要があることに注意してください。 RStudio Connect SAML 認証の前で [リバース プロキシ](https://docs.rstudio.com/connect/admin/proxy/) を使用するか、 [HTTPS を直接使用](https://docs.rstudio.com/connect/admin/appendix/configuration/#HTTPS)するように RStudio Connect SAML 認証を構成できます。

構成に問題がある場合は、 [RStudio Connect SAML 認証管理者ガイド](https://docs.rstudio.com/connect/admin/authentication/saml/) を参照するか、 [RStudio サポート チーム](mailto:support@rstudio.com) に問い合わせてください。

#### RStudio Connect SAML Authentication のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを RStudio Connect SAML Authentication に作成します。 RStudio Connect SAML Authentication では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ RStudio Connect SAML Authentication に存在しない場合は、RStudio Connect SAML Authentication にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる RStudio Connect SAML 認証サインオン URL にリダイレクトされます。
- RStudio Connect SAML Authentication のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RStudio Connect SAML Authentication に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [RStudio Connect SAML Authentication] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した RStudio Connect SAML Authentication に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/rstudio-server-pro-tutorial"} -->
## Microsoft Entra ID を使用して RStudio Server Pro のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/rstudio-server-pro-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RStudio Server Pro の間にシングル サインオンを構成する方法について説明します。

この記事では、RStudio Server Pro (RSP) と Microsoft Entra ID を統合する方法について説明します。 RSP を Microsoft Entra ID と統合すると、次のことができます。

- RSP にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して RSP に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RSP (バージョン 1.4 以上) のインストール。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RSP では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの RStudio Server Pro の追加

Microsoft Entra ID への RSP の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に RStudio Server Pro SAML Authentication を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**RStudio Server Pro SAML Authentication**」と入力します。
4. 結果のパネルから **[RStudio Server Pro SAML Authentication]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RStudio Server Pro 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、RSP に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと RSP の関連ユーザーとの間にリンク関係を確立する必要があります。

RStudio Server Pro SAML Authentication に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RStudio Server Pro の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RStudio Server Pro テストユーザーの作成** - RStudio Server Pro で B.Simon に対応するユーザーを作成し、Microsoft Entra 上のユーザーと連携させます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RStudio Server Pro SAML Authentication**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<RSP-SERVER>/<PATH>/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<RSP-SERVER>/<PATH>/saml/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<RSP-SERVER>/<PATH>/`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の RSP インストールの URI で更新してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RStudio Server Pro の SSO の構成

1. RSP 構成ファイル `/etc/rstudio/rserver.conf` を次の内容で更新します。

    ```
    auth-saml=1
    auth-saml-metadata-url=<federation-metadata-URI>
    auth-saml-sp-name-id-format=emailaddress
    auth-saml-sp-attribute-username=NameID
    auth-saml-sp-base-uri=<RSP-Server-URI>
    ```
2. 次のコマンドを実行して RSP を再起動します。

    ```
    sudo rstudio-server restart
    ```

#### RStudio Server Pro のテスト ユーザーの作成

サーバーには、RSP を使用する予定のあるすべてのユーザーをプロビジョニングする必要があります。 ユーザーは、`useradd` コマンドまたは `adduser` コマンドを使用して作成できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる RStudio Server Pro SAML 認証サインオン URL にリダイレクトされます。
- RStudio Server Pro SAML Authentication のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した RStudio Server Pro SAML Authentication に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [RStudio Server Pro SAML Authentication] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した RStudio Server Pro SAML Authentication に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/runmyprocess-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に RunMyProcess を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/runmyprocess-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と RunMyProcess の間のシングル サインオンを構成する方法について説明します。

この記事では、RunMyProcess と Microsoft Entra ID を統合する方法について説明します。 RunMyProcess を Microsoft Entra ID と統合すると、次のことが可能になります。

- RunMyProcess にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで RunMyProcess に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- RunMyProcess でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- RunMyProcess では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの RunMyProcess の追加

RunMyProcess と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に RunMyProcess をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「RunMyProcess**」と入力します。
4. 結果パネルから **[RunMyProcess** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### RunMyProcess 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、RunMyProcess に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、RunMyProcess での関連ユーザーとの間にリンク関係を確立する必要があります。

RunMyProcess 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **RunMyProcess SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **RunMyProcess のテスト ユーザーを作成する** - RunMyProcess で B.Simon に対応するユーザーを作成し、Microsoft Entra の当該ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**RunMyProcess** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://live.runmyprocess.com/live/<tenant id>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、RunMyProcess クライアント サポート チーム](mailto:support@runmyprocess.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **RunMyProcess のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### RunMyProcess SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として RunMyProcess テナントにサインオンします。
2. 左側のナビゲーション パネルで、[ **アカウント** ] を選択し、[ **構成]** を選択します。

    [Image: [アカウント] から選択された [構成] を示すスクリーンショット。]
3. **[認証方法**] セクションに移動し、次の手順を実行します。

    [Image: [認証方法] タブを示すスクリーンショット。ここで、説明されている値を入力できます。]

    a. **方法**として、**Samlv2 に対する SSO** を選択します。

    b。 **[SSO リダイレクト**] ボックスに、**ログイン URL** の値を貼り付けます。

    c. [ **ログアウト リダイレクト** ] ボックスに、 **ログアウト URL** の値を貼り付けます。

    d. [ **名前 ID 形式** ] ボックスに、**名前識別子形式**の値**を urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress**として入力します。

    e. Azure portal からダウンロードした証明書ファイルをメモ帳で開き、証明書ファイルの内容をコピーして、[ **証明書** ] ボックスに貼り付けます。

    f. **[保存] アイコンを選択します**。

#### RunMyProcess テスト ユーザーの作成

Microsoft Entra ユーザーが RunMyProcess にサインインできるようにするには、ユーザーを RunMyProcess にプロビジョニングする必要があります。 RunMyProcess の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. RunMyProcess 企業サイトに管理者としてサインインします。
2. 左側のナビゲーション パネルで [ **アカウント** ] を選択し、[ **ユーザー** ] を選択し、[ **新しいユーザー**] を選択します。

    [Image: 新しいユーザー]
3. [ **ユーザー設定]** セクションで、次の手順を実行します。

    [Image: プロファイル]

    a. プロビジョニングする有効な Microsoft Entra アカウントの **名前** と **電子メール** を関連するテキスト ボックスに入力します。

    b。 **IDE 言語**、**言語**、プロファイルを選択**します**。

    c. **アカウント作成用のメールを自分に送信する** を選択します。

    d. **[保存] を選択します**。

    注

    他の RunMyProcess ユーザー アカウントの作成ツールまたは RunMyProcess から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる RunMyProcess のサインオン URL にリダイレクトされます。
- RunMyProcess のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [RunMyProcess] タイルを選択すると、このオプションは RunMyProcess のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/s4-digitsec-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に S4 - Digitsec を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/s4-digitsec-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と S4 - Digitsec の間のシングル サインオンを構成する方法について説明します。

この記事では、S4 - Digitsec と Microsoft Entra ID を統合する方法について説明します。 S4 - Digitsec を Microsoft Entra ID と統合すると、次のことが可能になります。

- S4 - Digitsec にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで S4 - Digitsec に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- S4 - Digitsec でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- S4 - Digitsec では、**SP と IDP** によって開始される SSO がサポートされます。
- S4 - Digitsec では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの S4 - Digitsec の追加

S4 - Digitsec と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリのリストに S4 - Digitsec をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**S4 - Digitsec**」と入力します。
4. 結果パネルから **S4 - Digitsec** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### S4 - Digitsec 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、 S4 - Digitsec で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、S4 - Digitsec での関連ユーザーとの間にリンク関係を確立する必要があります。

S4 - Digitsec 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **S4 - Digitsec の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **S4 - Digitsec テスト ユーザーの作成** - S4 - Digitsec で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の対応するユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**S4 - Digitsec**&gt;**シングル サインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://s4.digitsec.com`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[S4 - Digitsec のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### S4 - Digitsec SSOを構成する

S4 - Digitsec 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を、[S4 - Digitsec サポート チーム](mailto:Support@digitsec.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### S4 - Digitsec テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを S4 - Digitsec に作成します。 S4 - Digitsec では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定では有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ S4 - Digitsec に存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる S4 - Digitsec サインオン URL にリダイレクトされます。
- S4 - Digitsec のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した S4 - Digitsec に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [S4 - Digitsec] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した S4 - Digitsec に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/saba-cloud-tutorial"} -->
## Microsoft Entra ID で Saba Cloud for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/saba-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Saba Cloud の間にシングル サインオンを構成する方法について説明します。

この記事では、Saba Cloud と Microsoft Entra ID を統合する方法について説明します。 Saba Cloud を Microsoft Entra ID と統合すると、次のことができます。

- Saba Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Saba Cloud に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Saba Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Saba Cloud では、 **SP Initiated SSO と IDP** Initiated SSO がサポートされます。
- Saba Cloud では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- Saba Cloud モバイル アプリケーションを Microsoft Entra ID と共に構成して SSO を有効にできるようになりました。 この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

### ギャラリーからの Saba Cloud の追加

Microsoft Entra ID への Saba Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Saba Cloud を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Saba Cloud**」と入力します。
4. 結果パネルから **Saba Cloud** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Saba Cloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Saba Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Saba Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Saba Cloud と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Saba Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Saba Cloud のテスト ユーザーの作成** - Saba Cloud で B.Simon に対応するテストユーザーを作成し、このユーザーを Microsoft Entra での表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。
4. **Saba Cloud (モバイル) の SSO をテスト** して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Saba Cloud**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します (この値は手順 6 の「Saba Cloud SSO の構成」セクションで取得しますが、通常は `<CUSTOMER_NAME>_sp`の形式です)。 `<CUSTOMER_NAME>_sp`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します (ENTITY\_IDは前の手順を参照します。通常は `<CUSTOMER_NAME>_sp`)。 `https://<CUSTOMER_NAME>.sabacloud.com/Saba/saml/SSO/alias/<ENTITY_ID>`

    注

    応答 URL を正しく指定しない場合は、[**エンタープライズ アプリケーション**] セクションではなく、Microsoft Entra ID の [**アプリの登録**] セクションでそれを調整する必要があります。 **[基本的な SAML 構成]** セクションを変更しても、必ずしも応答 URL が更新されるとは限りません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.sabacloud.com`

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `IDP_INIT---SAML_SSO_SITE=<SITE_ID> `または SAML がマイクロサイト用に構成されている場合は、次のパターンを使用して URL を入力します。 `IDP_INIT---SAML_SSO_SITE=<SITE_ID>---SAML_SSO_MICRO_SITE=<MicroSiteId>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態でこれらの値を更新します。 これらの値を取得するには、 [Saba Cloud クライアント サポート チーム](mailto:support@saba.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。

    RelayState の構成の詳細については、[マイクロサイトの IdP および SP によるイニシエーター SSO に関するページを](https://help.sabacloud.com/sabacloud/help-system/topics/help-system-idp-and-sp-initiated-sso-for-a-microsite.html)参照してください。
7. [ **ユーザー属性と要求** ] セクションで、[一意のユーザー識別子] を、Saba ユーザーのプライマリ ユーザー名として使用する予定の組織に合わせて調整します。

    この手順が必要になるのは、ユーザー名とパスワードから SSO への変換を試みる場合のみです。 まだユーザーがいない新しい Saba Cloud デプロイである場合、この手順はスキップしてかまいません。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Saba Cloud のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Saba Cloud の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Saba Cloud 企業サイトに管理者としてサインインします
2. **メニュー** アイコンを選択し、[**管理者**] を選択し、[**システム管理者**] タブを選択します。

    [Image: システム管理者のスクリーンショット]
3. [ **システムの構成**] で、[ **SAML SSO Setup]** を選択し、[ **SETUP SAML SSO]\(SAML SSO の設定** \) ボタンを選択します。

    [Image: 構成のスクリーンショット]
4. ポップアップ ウィンドウで、ドロップダウンから **[Microsite** ] を選択し、[ **追加と構成**] を選択します。

    [Image: サイト/マイクロサイトの追加のスクリーンショット]
5. [ **IDP の構成** ] セクションで、[ **参照** ] を選択して、ダウンロードした **フェデレーション メタデータ XML** ファイルをアップロードします。 [ **サイト固有の IDP** ] チェックボックスを有効にして、[ **インポート**] を選択します。

    [Image: 証明書のインポートのスクリーンショット]
6. [**SP の構成**] セクションで、[**エンティティ エイリアス**] の値をコピーし、[**基本的な SAML 構成**] セクションの [**識別子 (エンティティ ID)]** テキスト ボックスにこの値を貼り付けます。 **生成**を選択します。

    [Image: [SP の構成] のスクリーンショット]
7. [ **プロパティの構成** ] セクションで、入力されたフィールドを確認し、[保存] を選択 **します**。

    [Image: [プロパティの構成] のスクリーンショット]

    Microsoft Entra ID でログインが許可されている既定の最大ローリング期間と一致させるために、 **最大認証有効期間 (秒単位)** を **7776000** (90 日) に設定する必要がある場合があります。 そのようにしないと、"`(109) Login failed. Please contact system administrator.` ((109) ログインに失敗しました。システム管理者に問い合わせてください)" というエラーが発生する可能性があります。

#### Saba Cloud のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Saba Cloud に作成します。 Saba Cloud では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Saba Cloud にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

Saba クラウドで SAML Just In Time ユーザー プロビジョニングを有効にするには、 [この](https://help.sabacloud.com/sabacloud/help-system/topics/help-system-user-provisioning-with-saml.html) ドキュメントを参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Saba Cloud のサインオン URL にリダイレクトされます。
- Saba Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Saba Cloud に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Saba Cloud タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Saba Cloud に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

注

サインオン URL が Microsoft Entra ID で設定されていない場合、アプリケーションは IDP 開始モードとして扱われ、サインオン URL が設定されている場合、Microsoft Entra ID は常にサービス プロバイダーが開始するフローのためにユーザーを Saba Cloud アプリケーションにリダイレクトします。

### Saba Cloud (モバイル) の SSO のテスト

1. Saba Cloud Mobile アプリケーションを開き、テキストボックスに **サイト名** を指定して Enter キーを **押します**。

    [Image: サイト名のスクリーンショット。]
2. **メール アドレス**を入力し、[**次へ**] を選択します。

    [Image: メール アドレスのスクリーンショット。]
3. 最後に、サインインに成功すると、アプリケーション ページが表示されます。

    [Image: サインインに成功したスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/safeconnect-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SafeConnect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/safeconnect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SafeConnect の間にシングル サインオンを構成する方法について説明します。

この記事では、SafeConnect と Microsoft Entra ID を統合する方法について説明します。 SafeConnect を Microsoft Entra ID と統合すると、次のことができます:

- SafeConnect にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SafeConnect に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SafeConnect でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SafeConnect では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから SafeConnect を追加する

Microsoft Entra ID への SafeConnect の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SafeConnect を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「SafeConnect**」と入力します。
4. 結果パネルから **SafeConnect** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SafeConnect 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SafeConnect に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SafeConnect の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を SafeConnect と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SafeConnect SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SafeConnect テストユーザーの作成** - SafeConnect で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SafeConnect** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://portal.myweblogon.com:8443/saml/login`
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **メタデータ XML** を見つけて **[ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **SafeConnect のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SafeConnect SSO の構成

**SafeConnect** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [SafeConnect サポート チーム](mailto:support@impulse.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SafeConnect テスト ユーザーの作成

このセクションでは、SafeConnect で Britta Simon というユーザーを作成します。 [SafeConnect サポート チーム](mailto:support@impulse.com)と協力して、SafeConnect プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SafeConnect のサインオン URL にリダイレクトされます。
- SafeConnect のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SafeConnect] タイルを選択すると、このオプションは SafeConnect のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/safeguard-cyber-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SafeGuard Cyber を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/safeguard-cyber-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: ユーザー アカウントを RoleMapper に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SafeGuard Cyber と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [SafeGuard Cyber](https://www.safeguardcyber.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- SafeGuard Cyber でユーザーを作成します。
- アクセスが不要になったら、SafeGuard Cyber のユーザーを削除します。
- Microsoft Entra ID と SafeGuard Cyber の間でユーザー属性の同期を維持します。
- SafeGuard Cyber にグループとグループ メンバーシップをプロビジョニングする

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可がある SafeGuard Cyber のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と SafeGuard Cyber の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように SafeGuard Cyber を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように SafeGuard Cyber を構成するには SafeGuard Cyber サポートにお問い合わせください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから SafeGuard Cyber を追加する

Microsoft Entra アプリケーション ギャラリーから SafeGuard Cyber を追加して、SafeGuard Cyber へのプロビジョニングの管理を開始します。 SSO のために SafeGuard Cyber を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: SafeGuard Cyber への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で SafeGuard Cyber の自動ユーザー プロビジョニングを構成するには、以下の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **SafeGuard Cyber**] を選択します。

    [Image: アプリケーションの一覧の [SafeGuard Cyber] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、SafeGuard Cyber テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が SafeGuard Cyber に接続できることを確認します。 接続に失敗した場合は、SafeGuard Cyber アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から SafeGuard Cyber に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で SafeGuard Cyber のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、SafeGuard Cyber API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | SafeGuard Cyber で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:safeguard:2.0:User:scimSource | 糸 |  | ✓ |
12. [属性マッピング] セクションで、Microsoft Entra ID から SafeGuard Cyber に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で SafeGuard Cyber のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | SafeGuard Cyber で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/safety-culture-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SafetyCulture (旧称 iAuditor) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/safety-culture-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と SafetyCulture (以前の iAuditor) の間でシングル サインオンを構成する方法について説明します。

この記事では、SafetyCulture (旧称 iAuditor) と Microsoft Entra ID を統合する方法について説明します。 SafetyCulture と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で SafetyCulture へのアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SafetyCulture に自動的にログインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

SafetyCulture は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- [SafetyCulture 有料プラン](https://safetyculture.com/pricing/) - シングル サインオンに必要です。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SafetyCulture は、**SP Initiated SSO および IDP Initiated SSO** をサポートしています。

### ギャラリーから SafetyCulture を追加する

Microsoft Entra ID への SafetyCulture の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SafetyCulture を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「SafetyCulture**」と入力します。
4. 結果パネルから **SafetyCulture** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SafetyCulture の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SafetyCulture に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SafetyCulture の関連ユーザーとの間にリンク関係を確立する必要があります。

SafetyCulture に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
    - **SafetyCulture テスト ユーザーの作成** - Microsoft Entra の B.Simon の表現にリンクされる、SafetyCulture 内の B.Simon に相当するユーザーを作成します。
2. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SafetyCulture**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. SafetyCulture Web アプリに移動します。

    1. [SafetyCulture](https://app.safetyculture.com) Web アプリにログインします。
    2. ページの左下隅にある組織名を選択し、[ **組織の設定**] を選択します。
    3. ページの上部にある **[セキュリティ** ] を選択します。
    4. [**シングル サインオン (SSO)]** ボックスで **[設定**] を選択します。
    5. 接続オプションとして、***Microsoft Entra ID ではなく*****SAML** を選択します。
    6. 次のページで、次の手順を実行します。

        [Image: SafetyCulture Web アプリの SSO の詳細のサンプルを示すスクリーンショット。]

        ある。 **サービス プロバイダーエンティティ ID** の値をコピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。

        b。 **サービス プロバイダー アサーション コンシューマー サービスの URL 値を**コピーし、この値を [**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスに貼り付けます。
6. Azure portal に戻ります。 [ **基本的な SAML 構成]** セクションで、 **IdP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、SafetyCulture の **サービス プロバイダー エンティティ ID を** 貼り付けます。

    b。 [ **応答 URL** ] テキスト ボックスに、SafetyCulture の **サービス プロバイダー アサーション コンシューマー サービス URL を** 貼り付けます。
7. **SP** 開始モードでアプリケーションを構成する場合は、**サインオン URL (省略可能)** テキスト ボックスに、SafetyCulture の**サービス プロバイダー アサーション コンシューマー サービス URL を**入力します。
8. SafetyCulture アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: SafetyCulture アプリケーションの画像を示すスクリーンショット。]
9. 上記に加えて、SafetyCulture アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 名字 | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、次の手順で保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
11. SafetyCulture Web アプリに戻り、[ **続行** ] を選択し、 **手順 2: ログインの詳細** ページで以下の手順を実行します。

    [Image: SafetyCulture の SSO セットアップのログインの詳細ステップを示すスクリーンショット。]

    ある。 **のログイン URL** テキストボックスに、前にコピーした **のログイン URL** 値を貼り付けます。

    b。 ダウンロードした **証明書 (PEM)** を **署名証明書** フィールドにアップロードします。

    c. [ **セットアップの完了**] を選択します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### SafetyCulture テスト ユーザーの作成

このセクションでは、SafetyCulture で Britta Simon というユーザーを作成します。 SafetyCulture 組織の管理者と協力して、テスト ユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP によって開始される

1. [ **このアプリケーションをテストする**] を選択します。 これにより、ログイン フローを開始できる SafetyCulture のサインオン URL にリダイレクトされます。
2. SafetyCulture ログイン ページで、テスト ユーザーの電子メール アドレスを入力して SSO ログインを開始します。
3. [ **シングル サインオン (SSO) でログイン**] を選択します。

    [Image: SafetyCulture の SSO オプションを使用したログインを示すスクリーンショット。]

##### IDP によって開始される

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SafetyCulture に自動的にログインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SafetyCulture] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IdP モードで構成されている場合は、SSO を設定した SafetyCulture に自動的にログインされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/safetynet-tutorial"} -->
## Microsoft Entra ID で SafetyNet for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/safetynet-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SafetyNet の間のシングル サインオンを構成する方法について説明します。

この記事では、SafetyNet と Microsoft Entra ID を統合する方法について説明します。 SafetyNet を Microsoft Entra ID と統合すると、次のことが可能になります。

- SafetyNet にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで SafetyNet に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SafetyNet でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SafetyNet では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの SafetyNet の追加

SafetyNet と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に SafetyNet をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SafetyNet**」と入力します。
4. 結果ウィンドウで **[SafetyNet]** を選択し、アプリケーションを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SafetyNet 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SafetyNet 用の Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、SafetyNet での関連ユーザーとの間にリンク関係を確立する必要があります。

SafetyNet 用の Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SafetyNet の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SafetyNet テストユーザーの作成** - SafetyNet で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SafetyNet** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.predictivesolutions.com/sp`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.predictivesolutions.com/CRMApp/saml/SSO`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.predictivesolutions.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[SafetyNet クライアント サポート チーム](mailto:dev@predictivesolutions.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SafetyNet SSO の構成

**SafetyNet** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [SafetyNet サポート チーム](mailto:dev@predictivesolutions.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SafetyNet テスト ユーザーの作成

このセクションでは、SafetyNet で Britta Simon というユーザーを作成します。 [SafetyNet サポート チーム](mailto:dev@predictivesolutions.com)と連携し、SafetyNet プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる SafetyNet サインオン URL にリダイレクトされます。
- SafetyNet のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SafetyNet に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SafetyNet] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SafetyNet に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sailpoint-identity-security-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SailPoint Identity Security Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sailpoint-identity-security-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と SailPoint Identity Security Cloud の間にシングル サインオンを構成する方法について説明します。

この記事では、SailPoint Identity Security Cloud と Microsoft Entra ID を統合する方法について説明します。 SailPoint Identity Security Cloud を Microsoft Entra ID を統合すると、次のことができます。

- SailPoint Identity Security Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SailPoint Identity Security Cloud に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

SailPoint Identity Security Cloud は、次の [国内クラウドデプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- SailPoint Identity Security Cloud のアクティブなサブスクリプション。 Identity Security Cloud をお持ちでない場合は、 [SailPoint Identity Security Cloud サポート チーム](mailto:support@sailpoint.com)にお問い合わせください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SailPoint Identity Security Cloud では、**SP Initiated SSO と IDP Initiated SSO** がサポートされています。

### ギャラリーから SailPoint Identity Security Cloud を追加する

Microsoft Entra ID への SailPoint Identity Security Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SailPoint Identity Security Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SailPoint Identity Security Cloud**」と入力します。
4. 結果パネルから **[SailPoint Identity Security Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO を SailPoint Identity Security Cloud に対して構成しテストする

**B.Simon** というテスト ユーザーを使って、Microsoft Entra SSO を SailPoint Identity Security Cloud に対して構成しテストします。 SSO が機能するには、Microsoft Entra ユーザーと SailPoint Identity Security Cloud の関連ユーザーの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を SailPoint Identity Security Cloud に対して構成しテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SailPoint Identity Security Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SailPoint Identity Security Cloud のテスト ユーザーを作成** - SailPoint Identity Security Cloud において、Microsoft Entra のユーザー表現にリンクされた B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SailPoint Identity Security Cloud**&gt;**Single のサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://<TENANT_NAME>.identitynow.com/sp` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<TENANT_NAME>.login.sailpoint.com/saml/SSO/alias/<TENANT_NAME>-sp` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<TENANT_NAME>.identitynow.com/` という形式で URL を入力します。

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[SailPoint Identity Security Cloud クライアント サポート チーム](mailto:support@sailpoint.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[SailPoint Identity Security Cloud の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SailPoint Identity Security Cloud の SSO を構成する

1. 別の Web ブラウザー ウィンドウで、SailPoint Identity Security Cloud 企業サイトに管理者としてサインインします。
2. **[Global] -&gt; [Security Settings] -&gt; [Service Provider]** に移動し、次の構成を変更します。

    [Image: SailPoint SSO 設定のスクリーンショット。]

    ある。 リモート ID プロバイダーを有効にします。

    b。 **[エンティティ ID]** テキスト ボックスに、前にコピーした**エンティティ ID** の値を貼り付けます。

    c. **[ログイン URL]** フィールドに、前にコピーした**ログイン URL** の値を貼り付けます。

    d. **[リダイレクト用ログイン URL]** フィールドに、前にコピーした**ログイン URL** の値を貼り付けます。

    え **[Logout URL]** フィールドに、値「`https://<IDN Tenant>.login.sailpoint.com/signout`」を入力します。

    f. **[SAML Request Attribute]** セクションで、次の値を選択します。

    - IDマッピング属性 - `uid`
    - [SAML NameID] - `Unspecified`
    - SAML バインディング - `Post`
    - 要求された認証コンテキストを除外 - `checked`

    ジー **署名証明書**で、[**インポート**] を選択して、Azure portal からダウンロードした**証明書 (Base64)** をアップロードします。

#### SailPoint Identity Security Cloud のテスト ユーザーを作成する

このセクションでは、SailPoint Identity Security Cloud で Britta Simon というユーザーを作成します。 [SailPoint Identity Security Cloud サポート チーム](mailto:support@sailpoint.com)と協力して、SailPoint Identity Security Cloud プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる SailPoint Identity Security Cloud のサインオン URL にリダイレクトされます。
- SailPoint Identity Security Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP によって開始:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SailPoint Identity Security Cloud に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SailPoint Identity Security Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SailPoint Identity Security Cloud に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sakon-device-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Sakon Device Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sakon-device-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-04
- Summary: Microsoft Entra ID と Sakon Device Platform の間にシングル サインオンを構成する方法について学習します。

この記事では、Sakon Device Platform と Microsoft Entra ID を統合する方法について説明します。 Sakon Device Platform を Microsoft Entra ID と統合すると、以下のことが可能になります。

- Sakon Device Platform へのアクセス権を持つユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Sakon Device Platform に自動でサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sakon Device Platform のシングル サインオン (SSO) 対応サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Sakon Device Platform では、**IDP** 開始 SSO のみがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Sakon Device Platform を追加する

Microsoft Entra ID への Sakon Device Platform の統合を構成するには、Sakon Device Platform をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Sakon Device Platform**」と入力します。
4. 結果パネルから **[Sakon Device Platform]** を選択し、このアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sakon Device Platform 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Sakon Device Platform に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Sakon Device Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Sakon Device Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sakon Device Platform の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Sakon Device Platformのテストユーザーを作成し、Microsoft Entra IDのユーザーに対応させます** - Sakon Device PlatformでB.Simonに対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Sakon Device Platform**&gt;**シングルサインオンに移動します。**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://mobilemanager.net/5/` という URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<CUSTOMER_NAME>.gsgcloud.net/core5/AssertionConsumerService.aspx` のパターンを使用して URL を入力します

    注

    応答 URL は実際のものではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、[Sakon Device Platform サポート チーム](mailto:imsteam@sakon.com)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Sakon Device Platform アプリケーションでは、特定の形式の SAML アサーションが必要です。 通常、属性マッピングには SAML トークン構成の **emailaddress** 属性を使用します。 次のスクリーンショットは、この構成の例を示しています。 組織で **代わりに employeeid** を使用する場合は、カスタム属性マッピングを追加する必要があります。 その方法は次のとおりです。

    1. Azure の SAML 構成に移動し、新しい要求を追加します。
    2. 新しい要求を追加して、属性の一覧から **user.employeeid** を選択するか、自組織の構成に基づいて適切な属性を選択します。
    3. 送信要求の種類を **employeeid** に設定します。

    [Image: カスタム属性マッピングの画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sakon Device Platform の SSO を構成する

**Sakon Device Platform** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Sakon Device Platform サポート チーム](mailto:imsteam@sakon.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Sakon Device Platform のテスト ユーザーを作成する

このセクションでは、Sakon Device Platform で B.Simon というユーザーを作成します。 [Sakon Device Platform サポート チーム](mailto:imsteam@sakon.com)と連携して、Sakon Device Platform プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した Sakon Device Platform に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Sakon Device Platform] タイルを選択すると、SSO を設定した Sakon Device Platform に自動的にサインインします。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/salesforce-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Salesforce を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-23
- Summary: Microsoft Entra IDから Salesforce にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するために、Salesforce とMicrosoft Entra IDで実行する必要のある手順について説明します。

この記事の目的は、Microsoft Entra ID から Salesforce へユーザー アカウントを自動的にプロビジョニングおよび解除するために、Salesforce と Microsoft Entra ID で必要な手順を示すことです。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Salesforce.com テナント。
- Salesforce アカウントのユーザー名とパスワード、およびトークン。 トークンを取得する方法については、 自動ユーザー アカウント プロビジョニングの構成 を参照してください。 今後、アカウントパスワードをリセットすると、Salesforce から新しいトークンが提供され、Salesforce プロビジョニング設定を編集する必要があります。
- 統合ユーザーの Salesforce のカスタム ユーザー プロファイル。 Salesforce ポータルでカスタム プロファイルを作成したら、以下を有効にするようにプロファイルの管理権限を編集します。

    - API の有効化。
    - ユーザーの管理: このオプションを有効にすると、次のものが自動的に有効になります: アクセス許可セットの割り当て、内部 UsersManage IP アドレスの管理、ログイン アクセス ポリシーの管理、パスワード ポリシーの管理、プロファイルとアクセス許可セットの管理、ロールの管理、共有の管理、ユーザー パスワードのリセットとユーザーのロック解除、すべてのユーザーの表示、ロールと階層の表示、セットアップと構成の表示。

    Salesforce ドキュメントの「[プロファイルの作成またはクローン](https://help.salesforce.com/s/articleView?id=sf.users_profiles_cloning.htm&amp;type=5)」も参照してください。

    注

    このプロファイルにアクセス許可を直接割り当てます。 アクセス許可セットを通したアクセス許可の追加は行わないでください。

重要

Salesforce.com 試用版アカウントを使用している場合、自動ユーザー プロビジョニングを構成することはできません。 試用版アカウントでは、購入するまで必要な API アクセスは有効になりません。 この制限を回避するには、無料の [開発者アカウント](https://developer.salesforce.com/signup) を使用してこの記事を完了します。

Salesforce Sandbox 環境を使用している場合は、 [Salesforce Sandbox 統合に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-sandbox-tutorial)関する記事を参照してください。

### Salesforce へのユーザーの割り当てを計画する

Microsoft Entra IDでは、"割り当て" という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに "割り当てられている" ユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、Salesforce アプリにアクセスする必要Microsoft Entra IDユーザーまたはグループを決定する必要があります。

#### ユーザーを Salesforce に割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、1 人のMicrosoft Entra ユーザーを Salesforce に割り当てることをお勧めします。 「ユーザーの割り当て」で説明されているメカニズムを使用して、後でさらに多くのユーザーやグループを 割り当てることができます。
- Salesforce にユーザーを割り当てるときに、有効なユーザー ロールを選択する必要があります。 "既定のアクセス" ロールはプロビジョニングでは機能しません。 一部のロールでは、Salesforce でのライセンスが必要になる場合があることに注意してください。

    注

    プロビジョニング プロセスの一環として、Microsoft Entraは Salesforce からプロファイルをインポートします。 Salesforce からインポートされるプロファイルは、Microsoft Entra IDのアプリケーション ロールとして表示されるため、Microsoft Entra IDでユーザーを割り当てるときに選択できます。 ユーザーをカスタム プロファイルに割り当てる場合は、Salesforce からプロファイルがインポートされるまで待ってから、アプリケーションにユーザーを割り当てます。 ロールのインポートを行うときは、Microsoft Entra IDでアプリケーション ロールを手動で編集しないでください。

#### Salesforce での既存のユーザーの識別

Microsoft Entraとの統合の前に、Salesforce アカウントには、Salesforce 管理者またはその他のプロセスによって作成された 1 人以上のユーザーが既に存在している可能性があります。 Salesforce に既に存在するユーザーを確認するには、2 つの方法があります。

**オプション 1** アカウント検出機能を使用すると、Salesforce のすべてのユーザーのレポートを生成し、Entra で一致するアカウントを持つユーザーと、Salesforce に対してローカルなユーザーを 1 回のクリックで識別できます。 アカウント検出機能の詳細については [、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)。

**オプション 2** Salesforce エクスポート データ機能を使用して、どのユーザーが既に存在するかを確認できます。 詳細については、「 [Salesforce からバックアップ データをエクスポートする](https://help.salesforce.com/s/articleView?id=xcloud.admin_exportdata.htm)」を参照してください。 Salesforce からエクスポートする場合は、 `User` データがエクスポートされたデータ セットに含まれていることを確認し、組織内のすべての名前 ( `Unicode (UTF-8)` など) を許可するエクスポート ファイル エンコードを選択します。

Salesforce からエクスポートされたデータを取得したら、`User.csv` ファイルを抽出し、Excelまたは PowerShell で開いて、Salesforce に既に存在するアクティブなユーザーの一覧を表示できます。

```powershell
import-csv .\User.csv | where {$_.IsActive -eq '1'}  | sort UserName | ft UserName
```

### 自動化されたユーザー プロビジョニングを有効にする

このセクションでは、Microsoft Entra IDを [Salesforce のユーザー アカウント プロビジョニング API - v40](https://developer.salesforce.com/docs/atlas.en-us.208.0.api.meta/api/implementation_considerations.htm) に接続する方法について説明します。

ヒント

[Azure ポータル](https://portal.azure.com)に記載されている手順に従って、Salesforce に対して SAML ベースの単一 Sign-On を有効にすることもできます。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### 自動ユーザー アカウント プロビジョニングを構成する

このセクションでは、Active Directoryユーザー アカウントの Salesforce へのユーザー プロビジョニングを有効にする方法について説明します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. シングル サインオンのために Salesforce を構成している場合は、検索フィールドで Salesforce のインスタンスを検索します。 それ以外の場合は、**[追加]** を選択し、アプリケーションギャラリーで **Salesforce** を検索します。 検索結果から Salesforce を選択してアプリケーションの一覧に追加します。
4. Salesforce のインスタンスを選択してから、 **[プロビジョニング]** タブを選択します。
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションに次の構成設定を指定します。

    1. **[管理ユーザー名]** ボックスに、Salesforce.com で**システム管理者**プロファイルが割り当てられている Salesforce アカウント名を入力します。
    2. **[管理パスワード]** テキストボックスに、このアカウントのパスワードを入力します。
7. Salesforce のセキュリティ トークンを取得するには、新しいタブを開き、同じ Salesforce の管理者アカウントにサインインします。 ページの右上隅で、自分の名前を選択し、[ **設定]** を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) リンクが選択された状態を示すスクリーンショット。]
8. 左側のナビゲーション ウィンドウで、[ **マイ 個人情報** ] を選択して関連セクションを展開し、[ **セキュリティ トークンのリセット**] を選択します。

    [Image: [私の個人情報] で [自分のセキュリティ トークンのリセット] が選択されていることを示すスクリーンショット。]
9. [ **セキュリティ トークンのリセット** ] ページで、[ **セキュリティ トークンのリセット** ] ボタンを選択します。

    [Image: [セキュリティ トークンのリセット] ページを示すスクリーンショット。[セキュリティ トークンのリセット] の説明文とオプションが表示されている]
10. この管理アカウントに関連付けられている電子メールの受信トレイを確認してください。 新しいセキュリティ トークンが記載された Salesforce.com からの電子メールを探します。
11. トークンをコピーし、Microsoft Entra ウィンドウに移動し、**Secret Token** フィールドに貼り付けます。
12. **テナント URL** は、Salesforce のインスタンスが Salesforce Government クラウドにある場合にのみ入力する必要があります。 それ以外の場合は省略可能です。 テナント URL は、`https://<your-instance>.my.salesforce.com` の形式で入力します。`<your-instance>` は、ご利用の Salesforce のインスタンスの名前に置き換えてください。
13. **Test Connection** を選択して、Microsoft Entra IDが Salesforce アプリに接続できることを確認します。
14. [ **作成]** を選択して構成を作成します。
15. [**概要**] ページで **[プロパティ**] を選択します。
16. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
17. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
18. プロビジョニングの概要で **[ID の** 検出] を選択して、Salesforce の [アカウントを検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery) します。 このオプションは、[Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) ライセンスを持つ組織にのみ表示されます。
19. Microsoft Entra IDから Salesforce に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Salesforce のユーザー アカウントとの照合に使用されることに注意してください。 [保存] ボタンをクリックして変更をコミットします。

注

ユーザーが Salesforce アプリケーションでプロビジョニングされたら、管理者は言語固有の設定を構成する必要があります。 言語の構成の詳細については、[こちら](https://help.salesforce.com/articleView?id=setting_your_language.htm&amp;type=5)の記事を参照してください。

1. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
2. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
3. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### モニタリング

**[同期の詳細]** セクションを使用すると、進行状況を監視できるほか、リンクをクリックしてプロビジョニング アクティビティ ログを取得できます。このログには、プロビジョニング サービスによって Salesforce アプリに対して実行されたすべてのアクションが記載されています。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「[自動ユーザー アカウント プロビジョニングに関するレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

### ユーザーを割り当てる

テストが完了し、ユーザーが Salesforce に正常にプロビジョニングされたら、Salesforce を必要とする他のユーザーがアプリケーション ロールに割り当てられていることを確認します。 これには、「Salesforce の既存のユーザーの識別」セクションで説明されているように、Salesforce で現在アクティブなアカウントを持っている すべてのユーザーが含まれます。 次のいずれかの手順に従って、Microsoft Entraの Salesforce アプリケーションに、これらのユーザーと追加の承認されたユーザーを割り当てることができます。

- Microsoft Entra 管理センターで、[個々のユーザーをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。
- Microsoft Graphまたは PowerShell コマンドレット `New-MgServicePrincipalAppRoleAssignedTo` を使用して、個々のユーザーをアプリケーションに割り当てることができます。
- 組織がMicrosoft Entra ID ガバナンスのライセンスを持っている場合は、[アクセスの割り当てを自動化するためのエンタイトルメント管理ポリシー](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-deploy#deploy-entitlement-management-policies-for-automating-access-assignment)を展開して、ユーザーが組織に参加するときに割り当てを追加または削除したり、ロールを離れたり変更したりすることもできます。 [このアプリケーションのエンタイトルメント管理アクセス パッケージを作成](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app)できます。 ユーザーが要求するとき、 [管理者が](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity)、 [ルールに基づいて自動的に](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)、または [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios#administrator-assign-employees-access-from-lifecycle-workflows)を通じてアクセス権を割り当てるためのポリシーを設定できます。

アプリケーションに割り当てられているユーザーがMicrosoft Entra IDで更新されると、それらの変更は Salesforce に自動的にプロビジョニングされます。

### 一般的な問題

- Salesforce へのプロビジョニングを有効にする際に問題が発生した場合は、次の点を確認してください。
    - 使用する資格情報に、Salesforce への管理者アクセス権がある。
    - 使用している Salesforce のバージョンでは、Web Access (開発者、エンタープライズ、サンドボックス、Salesforce の無制限エディションなど) がサポートされます。
    - Web API アクセスがユーザーに対して有効になっている。
- Microsoft Entra プロビジョニング サービスでは、ユーザーの言語、ロケール、timeZone のプロビジョニングがサポートされています。 これらの属性は既定の属性マッピングに含まれていますが、既定のソース属性はありません。 既定のソース属性を選択し、そのソース属性が SalesForce で想定されている形式となるようにしてください。 たとえば、英語 (米国) の localeSidKey は、en\_US です。 [こちら](https://help.salesforce.com/articleView?id=faq_getstart_what_languages_does.htm&amp;type=5)に記載されているガイダンスを確認して、適切な localeSidKey 形式を把握してください。 languageLocaleKey の形式は、[こちら](https://help.salesforce.com/articleView?id=faq_getstart_what_languages_does.htm&amp;type=5)に記載されています。 この形式が正しいことを確認するだけでなく、[こちら](https://help.salesforce.com/articleView?id=faq_getstart_what_languages_does.htm&amp;type=5)に説明されているように、ユーザーに対して言語が有効になっていることを確認することが必要な場合もあります。
- **SalesforceLicenseLimitExceeded:** このユーザーに使用可能なライセンスがないため、Salesforce でユーザーを作成できませんでした。 ターゲット アプリケーションの追加ライセンスを調達するか、 [ユーザーの割り当てを確認](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation) して、正しいユーザーが割り当てられていることを確認します。
- **SalesforceDuplicateUserName:** ユーザーは、別の Salesforce.com テナントで複製された Salesforce.com 'Username' を持っているため、プロビジョニングできません。 Salesforce.com では、'Username' 属性の値は、すべての Salesforce.com テナントにわたって一意である必要があります。 既定では、Microsoft Entra IDのユーザーの userPrincipalName は、Salesforce.com の "Username" になります。 この場合、2 つの選択肢があります。 1 つ目のオプションは、他の Salesforce.com テナントも管理する場合に、その他のテナントの重複する 'Username' を持つユーザーを探して、名前を変更することです。 もう 1 つのオプションは、ディレクトリが統合されている Salesforce.com テナントへのMicrosoft Entra ユーザーからのアクセスを削除することです。 次回の同期の試行時に、この操作を再試行します。
- **SalesforceRequiredFieldMissing:** Salesforce では、ユーザーを正常に作成または更新するために、特定の属性がユーザーに存在する必要があります。 このユーザーには、必須の属性の 1 つがありません。 Salesforce にプロビジョニングするすべてのユーザーに、email や alias などの属性が設定されていることを確認してください。 [属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用して、これらの属性を持たないユーザーを対象外にすることができます。
- Salesforce へのプロビジョニング用の既定の属性マッピングには、Microsoft Entra ID の appRoleAssignments を Salesforce の ProfileName にマッピングするための式である SingleAppRoleAssignments が含まれています。 属性マッピングでは 1 つのロールのプロビジョニングのみがサポートされるため、Microsoft Entra IDでユーザーが複数のアプリ ロールの割り当てを持たないようにします。 グループが 1 つのロールに割り当てられているユーザーのグループがある場合、そのグループのメンバーは、別のロールを持つ Salesforce アプリケーションに直接割り当てることはできません。
- Salesforce では、メールを変更する前に、その更新を手動で承認する必要があります。 そのため、メールの変更が承認されるまでは、プロビジョニングのログに、ユーザーのメールを更新するエントリが複数表示されることがあります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/salesforce-sandbox-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Salesforce Sandbox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-sandbox-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-23
- Summary: Microsoft Entra ID から Salesforce Sandbox に対するユーザー アカウントのプロビジョニングおよびプロビジョニング解除を自動的に行うために、Salesforce Sandbox と Microsoft Entra ID で行う必要がある手順について学習します。

この記事の目的は、Microsoft Entra ID から Salesforce Sandbox にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するために Salesforce Sandbox と Microsoft Entra ID で実行する必要がある手順を示することです。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- Microsoft Entra テナント。
- Salesforce Sandbox for Work または Salesforce Sandbox for Education の有効なテナント。 どちらのサービスにも無料試用版のアカウントを使用できます。
- Team Admin アクセス許可がある Salesforce Sandbox のユーザー アカウント

### Salesforce Sandbox へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に "割り当て" という概念が使用されます。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra ID のアプリケーションに "割り当て済み" のユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、Salesforce Sandbox アプリへのアクセスが必要な Microsoft Entra ID 内のユーザーまたはグループを決定しておく必要があります。 これを決定したら、[エンタープライズ アプリへのユーザーまたはグループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に関する手順に従って、これらのユーザーを Salesforce Sandbox アプリに割り当てることができます。

#### ユーザーを Salesforce Sandbox に割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを Salesforce Sandbox に割り当てることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Salesforce Sandbox にユーザーを割り当てるときに、有効なユーザー ロールを選択する必要があります。 "既定のアクセス" ロールはプロビジョニングでは機能しません。

注意

Salesforce Sandbox アプリでは、既定で、プロビジョニングされたユーザーのユーザー名とメール アドレスに文字列が付加されます。 ユーザー名とメール アドレスは Salesforce 全体で一意である必要があります。これは、サンドボックスで実際のユーザー データが作成されないようにするためです。作成されると、これらのユーザーが実稼働 Salesforce 環境にプロビジョニングされなくなります。

注意

このアプリにより、プロビジョニング プロセスの一環として Salesforce Sandbox からカスタム ロールがインポートされます。顧客はユーザーを割り当てるとき、ロールを選択します。

### 自動化されたユーザー プロビジョニングを有効にする

このセクションでは、Microsoft Entra ID を Salesforce Sandbox のユーザー アカウント プロビジョニング API に接続する手順と、Microsoft Entra ID でのユーザーとグループの割り当てに基づいて、割り当て済みのユーザー アカウントを Salesforce Sandbox で作成、更新、無効化するようにプロビジョニング サービスを構成する手順を説明します。

ヒント

Salesforce Sandbox では SAML ベースのシングル サインオンを有効にすることもできます。これを行うには、[Azure portal](https://portal.azure.com) で説明されている手順に従ってください。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### 自動ユーザー アカウント プロビジョニングを構成する

このセクションでは、Active Directory のユーザー アカウントのユーザー プロビジョニングを Salesforce Sandbox に対して有効にする方法について説明します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
3. シングル サインオンのために Salesforce Sandbox を既に構成している場合は、検索フィールドで Salesforce Sandbox のインスタンスを検索します。 アプリケーション ギャラリーで **Salesforce Sandbox** を検索するには、**[追加]** を選択してください。 検索結果から Salesforce Sandbox を選択してアプリケーションの一覧に追加します。
4. Salesforce Sandbox のインスタンスを選択してから、 **[プロビジョニング]** タブを選択します。
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションに次の構成設定を指定します。

    1. **[管理ユーザー名]** ボックスに、Salesforce.com で**システム管理者**プロファイルが割り当てられている Salesforce Sandbox アカウント名を入力します。
    2. **[管理パスワード]** テキストボックスに、このアカウントのパスワードを入力します。
7. Salesforce Sandbox のセキュリティ トークンを取得するには、新しいタブを開き、同じ Salesforce Sandbox の管理者アカウントにサインインします。 ページの右上隅で、自分の名前を選択し、[ **設定]** を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) リンクが選択された状態を示すスクリーンショット。]
8. 左側のナビゲーション ウィンドウで、[ **マイ 個人情報** ] を選択して関連セクションを展開し、[ **セキュリティ トークンのリセット**] を選択します。

    [Image: [私の個人情報] で [自分のセキュリティ トークンのリセット] が選択されていることを示すスクリーンショット。]
9. [ **セキュリティ トークンのリセット** ] ページで、[ **セキュリティ トークンのリセット** ] ボタンを選択します。

    [Image: スクリーンショットは、]
10. この管理アカウントに関連付けられている電子メールの受信トレイを確認してください。 新しいセキュリティ トークンが記載された Salesforce Sandbox.com からの電子メールを探します。
11. トークンをコピーして、Microsoft Entra のウィンドウに移動し、**[シークレット トークン]** フィールドに貼り付けます。
12. **[接続のテスト]** をクリックして、Microsoft Entra ID で Salesforce Sandbox アプリに接続できることを確かめます。
13. [ **作成]** を選択して構成を作成します。
14. [**概要**] ページで **[プロパティ**] を選択します。
15. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
16. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
17. Microsoft Entra ID から Salesforce Sandbox に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Salesforce Sandbox のユーザー アカウントとの照合に使用されることに注意してください。 [保存] ボタンをクリックして変更をコミットします。
18. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
19. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
20. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/salesforce-sandbox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Salesforce Sandbox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-sandbox-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-24
- Summary: Microsoft Entra ID と Salesforce Sandbox の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Salesforce Sandbox と Microsoft Entra ID を統合する方法について説明します。 Salesforce Sandbox と Microsoft Entra ID を統合すると、次のことができます。

- Salesforce Sandbox にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Salesforce Sandbox に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

注意

**更新日: 2026 年 7 月 24** 日 - 2026 年 6 月 29 日以降、Microsoft Entra IDは、MICROSOFT IDENTITY PLATFORM v2.0 トークンを使用して SAML 2.0 および OpenID Connect (OIDC) アプリケーションに発行されたトークンに、認証方法参照 (`amr`) および認証コンテキスト参照 (`acr`) 要求を自動的に含めます。 **Entra IDを使用して外部 MFA プロバイダーを構成しており、そのプロバイダーが AMR シグナルを送信している場合、Entra IDはその AMR シグナルを必要に応じて Salesforce または他の 3P アプリケーションに転送します。** これらの要求は、ユーザーの認証方法と、サインイン中に認証コンテキストが満たされた方法に関する追加情報を提供します。 Microsoft Entra ID for Salesforce Sandbox のシングル サインオンでは、構成の変更は必要ありません。 Salesforce 管理者向けの Salesforce [フィッシング耐性のある MFA 要件](https://help.salesforce.com/s/articleView?id=005321563&amp;type=1)および[シングル サインオン (SSO) ログイン向けのデバイス有効化の変更](https://help.salesforce.com/s/articleView?id=005237070&amp;type=1)を満たすために、お客様は、Salesforce 管理者のサインインに対してフィッシング耐性のある認証方法を必須とする Microsoft Entra 条件付きアクセス ポリシーを適用する必要があります。

Entra IDを使用するフェデレーション プロバイダーとして AD FS を使用しているお客様の場合は、[SAML 2.0 フェデレーション IdP に対して MFA で想定される受信アサーション ガイダンスに](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-expected-inbound-assertions#using-saml-20-federated-idp)従って、Entra IDが SAML トークンにこの要求を含めます。

他のフェデレーション プロバイダーを使用している場合でも、まず AMR と ACR シグナルの標準化に取り組んでおり、近日中に更新プログラムを提供します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Salesforce Sandbox でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Salesforce Sandbox では、**SP と IDP** によって開始される SSO がサポートされます
- Salesforce Sandbox では、**Just-In-Time** ユーザー プロビジョニングがサポートされます
- Salesforce Sandbox では、[**自動化された**ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-sandbox-provisioning-tutorial)がサポートされます

### ギャラリーからの Salesforce Sandbox の追加

Microsoft Entra への Salesforce Sandbox の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Salesforce Sandbox を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Salesforce Sandbox**」と入力します。
4. 結果のパネルから **[Salesforce Sandbox]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Salesforce Sandbox 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Salesforce Sandbox に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Salesforce Sandbox の関連ユーザーとの間にリンク関係を確立する必要があります。

Salesforce Sandbox に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Salesforce Sandbox の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Salesforce Sandbox テストユーザーの作成** - Salesforce Sandbox で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Salesforce Sandbox**&gt;**シングルサインオン**を閲覧します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **サービス プロバイダー メタデータ ファイル**を保持しており、**IDP** によって開始されるモードに構成したい場合は、 **[基本的な SAML 構成]** セクション上で次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択する]

    注意

    サービス プロバイダーのメタデータ ファイルは、Salesforce サンドボックス管理ポータルから取得します。これについては、この記事の後半で説明します。

    c. メタデータファイルが正常にアップロードされると、**応答 URL** の値が**応答 URL**テキストボックスに自動的に設定されます。

    [Image: 画像]

    注意

    **応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Salesforce Sandbox のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Salesforce Sandbox の SSO の構成

1. ブラウザーで新しいタブを開き、Salesforce Sandbox の管理者アカウントにサインインします。
2. ページの右上隅にある **[設定] アイコン**の下にある **[セットアップ]** を選択します。

    [Image: 右上の]
3. 左側のナビゲーション ウィンドウの **[設定]** まで下にスクロールし、[ **ID** ] を選択して関連セクションを展開します。 次に**単一 Sign-On 設定**を選択します。

    [Image: 左側のペインの [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) メニューを示すスクリーンショット。[Identity](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ID) メニューの [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) が選択されています。]
4. [ **単一 Sign-On 設定]** ページで、[ **編集** ] ボタンを選択します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。[Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集) ボタンが選択されています。]
5. [ **SAML Enabled] を**選択し、[ **保存]** を選択します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。[S A M L Enabled](S A M L 有効) チェック ボックスがオンになっていて、[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) ボタンが選択されています。]
6. SAML シングル サインオン設定を構成するには、[ **メタデータ ファイルから新規**] を選択します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。[New from Metadata File](メタデータ ファイルから新規) ボタンが選択されています。]
7. [ **ファイルの選択] を選択** して、ダウンロードしたメタデータ XML ファイルをアップロードし、[ **作成**] を選択します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。[Choose File](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ファイルの選択) および [Create](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/作成) ボタンが選択されています。]
8. **[SAML Single Sign-On Settings]\(SAML 単一 Sign-On 設定\)** ページで、フィールドが自動的に設定され、[保存] を選択します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。フィールドは入力済みで、[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) ボタンが選択されています。]
9. [ **単一 Sign-On 設定]** ページで、[ **メタデータのダウンロード** ] ボタンを選択して、サービス プロバイダーのメタデータ ファイルをダウンロードします。 前述の必要な URL を構成するために、Azure portal の **[基本的な SAML 構成]** セクションでこのファイルを使用します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。[Download Metadata](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メタデータのダウンロード) ボタンが選択されています。]
10. **SP** 開始モードでアプリケーションを構成する場合、その前提条件を以下に示します。

    a. 確認済みドメインを持っている必要があります。

    b。 Salesforce Sandbox でドメインを構成して有効にする必要があります。この手順については、この記事の後半で説明します。

    c. Azure portal の [ **基本的な SAML 構成]** セクションで、[ **追加の URL の設定** ] を選択し、次の手順を実行します。

    [Image: Salesforce Sandbox Domain のドメインと URL のシングル サインオン情報]

    **[サインオン URL]** ボックスに、`https://<instancename>--Sandbox.<entityid>.my.salesforce.com` のパターンを使用して値を入力します。

    注意

    この値は、ドメインを有効にした後で Salesforce Sandbox ポータルからコピーする必要があります。
11. [ **SAML 署名証明書** ] セクションで、[ **フェデレーション メタデータ XML** ] を選択し、XML ファイルをコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
12. ブラウザーで新しいタブを開き、Salesforce Sandbox の管理者アカウントにサインインします。
13. ページの右上隅にある **[設定] アイコン**の下にある **[セットアップ]** を選択します。

    [Image: 右上の]
14. 左側のナビゲーション ウィンドウの **[設定]** まで下にスクロールし、[ **ID** ] を選択して関連セクションを展開します。 次に**単一 Sign-On 設定**を選択します。

    [Image: 左側のナビゲーション ウィンドウの [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) メニューを示すスクリーンショット。[Identity](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ID) メニューの [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) が選択されています。]
15. [ **単一 Sign-On 設定]** ページで、[ **編集** ] ボタンを選択します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。[Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集) ボタンが選択されています。]
16. [ **SAML Enabled] を**選択し、[ **保存]** を選択します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。[S A M L Enabled](S A M L 有効) チェック ボックスがオンになっていて、[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) ボタンが選択されています。]
17. SAML シングル サインオン設定を構成するには、[ **メタデータ ファイルから新規**] を選択します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。[New from Metadata File](メタデータ ファイルから新規) ボタンが選択されています。]
18. [ **ファイルの選択] を選択** してメタデータ XML ファイルをアップロードし、[作成] を選択 **します**。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。[Choose File](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ファイルの選択) ボタンと [Create](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/作成) ボタンが選択されています。]
19. **[SAML Single Sign-On Settings]\(SAML 単一 Sign-On 設定**\) ページで、フィールドが自動的に設定され、[*名前*] ボックスに構成の名前 (**例: SPSSOWAAD\_Test**) を入力し、[保存] を選択します。

    [Image: [Single Sign-On Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シングルサインオンの設定) ページを示すスクリーンショット。フィールドは入力済みで、[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前) テキストボックスには名前の例が入力され、[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) ボタンが選択されています。]
20. Salesforce Sandbox でドメインを有効にするには、次の手順を実行します。

    注意

    ドメインを有効にする前に、Salesforce Sandbox 上に同じドメインを作成する必要があります。 詳細については、「[ドメイン名の定義](https://help.salesforce.com/HTViewHelpDoc?id=domain_name_define.htm&amp;language=en_US)」をご覧ください。 ドメインを作成したら、ドメインが正しく構成されていることを確認してください。
21. Salesforce Sandbox の左側のナビゲーション ウィンドウで、[ **会社の設定]** を選択して関連セクションを展開し、[ **マイ ドメイン**] を選択します。

    [Image: 左側のナビゲーション ウィンドウを示すスクリーンショット。[Company Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社の設定) と [My Domain](マイ ドメイン) が選択されています。]
22. [ **認証構成]** セクションで、[ **編集**] を選択します。

    [Image: [Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) セクションを示すスクリーンショット。[Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集) ボタンが選択されています。]
23. [ **認証構成]** セクションで、 **認証サービス**として、Salesforce Sandbox の SSO 構成時に設定した SAML シングル サインオン設定の名前を選択し、[保存] を選択 **します**。

    [Image: シングルサインオンを構成する]

#### Salesforce Sandbox テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Salesforce Sandbox に作成します。 Salesforce Sandbox では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Salesforce Sandbox に存在しない場合は、Salesforce Sandbox にアクセスしようとしたときに新しいユーザーが作成されます。 Salesforce Sandbox は、自動ユーザー プロビジョニングもサポートしています。自動ユーザー プロビジョニングの構成方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-sandbox-provisioning-tutorial)を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SPによって開始されました:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Salesforce Sandbox のサインオン URL にリダイレクトされます。
- Salesforce Sandbox のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Salesforce Sandbox に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Salesforce Sandbox タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Salesforce Sandbox に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/salesforce-tutorial"} -->
## Microsoft Entra ID でのシングル サインオン用の Salesforce の構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-24
- Summary: Microsoft Entra IDと Salesforce の間でシングル サインオンを構成する方法について説明します。

この記事では、Salesforce と Microsoft Entra ID を統合する方法について説明します。 Salesforce をMicrosoft Entra IDと統合すると、次のことができます。

- Salesforce にアクセスできるユーザーをMicrosoft Entra IDで制御できます。
- ユーザーが自分のMicrosoft Entraアカウントを使用して Salesforce に自動的にサインインできるように設定できます。
- 1 つの場所でアカウントを管理します。

注意

**更新日: 2026 年 7 月 24** 日 - 2026 年 6 月 29 日以降、Microsoft Entra IDは、Microsoft ID プラットフォーム v2.0 トークンを使用して SAML 2.0 および OpenID Connect (OIDC) アプリケーションに発行されたトークンに、認証方法参照 (`amr`) および認証コンテキスト参照 (`acr`) 要求を自動的に含めます。 **Entra IDを使用して外部 MFA プロバイダーを構成しており、そのプロバイダーが AMR シグナルを送信している場合、Entra IDはその AMR シグナルを必要に応じて Salesforce または他の 3P アプリケーションに転送します。** これらの要求は、ユーザーの認証方法と、サインイン中に認証コンテキストが満たされた方法に関する追加情報を提供します。 Microsoft Entra ID for Salesforce シングル サインオンでは、構成の変更は必要ありません。 Salesforce 管理者向けの Salesforce [フィッシング耐性のある MFA 要件](https://help.salesforce.com/s/articleView?id=005321563&amp;type=1)および[シングル サインオン (SSO) ログイン向けのデバイス有効化の変更](https://help.salesforce.com/s/articleView?id=005237070&amp;type=1)を満たすために、お客様は、Salesforce 管理者のサインインに対してフィッシング耐性のある認証方法を必須とする Microsoft Entra 条件付きアクセス ポリシーを適用する必要があります。

Entra IDを使用するフェデレーション プロバイダーとして AD FS を使用しているお客様の場合は、[SAML 2.0 フェデレーション IdP に対して MFA で想定される受信アサーション ガイダンスに](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-mfa-expected-inbound-assertions#using-saml-20-federated-idp)従って、Entra IDが SAML トークンにこの要求を含めます。

他のフェデレーション プロバイダーを使用している場合でも、まず AMR と ACR シグナルの標準化に取り組んでおり、近日中に更新プログラムを提供します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Salesforce のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で sso Microsoft Entraを構成し、テストします。

- Salesforce では、**SP** Initiated SSO がサポートされます。
- Salesforce では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-provisioning-tutorial) (推奨) がサポートされます。
- Salesforce では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Salesforce Mobile アプリケーションを、SSO を有効にするためのMicrosoft Entra IDで構成できるようになりました。 この記事では、テスト環境で sso Microsoft Entraを構成し、テストします。

### ギャラリーから Salesforce を追加する

Microsoft Entra IDへの Salesforce の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Salesforce を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Salesforce**」と入力します。
4. 結果パネルで **[Salesforce]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細については](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)を参照してください。

### Salesforce Microsoft Entra SSO の構成とテスト

Salesforce で Microsoft Entra SSO を構成してテストするために、**B.Simon** というテストユーザーを使用します。 SSO を機能させるには、Microsoft Entraテスト ユーザー B.Simon と Salesforce の対応するユーザー アカウントとの間にリンクを確立する必要があります。

Salesforce Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**を構成する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra のテスト ユーザーの作成** - Microsoft Entra のシングル サインオンを B.Simon でテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Salesforce の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Salesforce テストユーザーの作成** - Salesforce で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Salesforce**&gt;**Single sign-on** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. **[識別子]** ボックスに、次の形式で値を入力します。

    エンタープライズ アカウント: `https://<subdomain>.my.salesforce.com`

    開発者アカウント: `https://<subdomain>-dev-ed.my.salesforce.com`

    b。 **[応答 URL]** ボックスに、次のパターンを使用して値を入力します。

    エンタープライズ アカウント: `https://<subdomain>.my.salesforce.com`

    開発者アカウント: `https://<subdomain>-dev-ed.my.salesforce.com`

    c. **[サインオン URL]** ボックスに、次のパターンを使用して値を入力します。

    エンタープライズ アカウント: `https://<subdomain>.my.salesforce.com`

    開発者アカウント: `https://<subdomain>-dev-ed.my.salesforce.com`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Salesforce クライアント サポート チーム](https://help.salesforce.com/support)に問い合わせてください。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Salesforce のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーを作成して割り当てる

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Salesforce SSO の構成

1. 別の Web ブラウザー ウィンドウで、Salesforce 企業サイトに管理者としてサインインします
2. ページの右上隅にある **[設定] アイコン**の下にある **[セットアップ]** を選択します。

    [Image: シングル サインオンの構成 (設定アイコン)]
3. ナビゲーション ウィンドウの **[設定]** まで下にスクロールし、[ **ID** ] を選択して関連セクションを展開します。 次に**単一 Sign-On 設定**を選択します。

    [Image: シングル サインオンの構成 (設定)]
4. [ **単一 Sign-On 設定]** ページで、[ **編集** ] ボタンを選択します。

    [Image: シングル サインオンの構成 (編集)]

    注意

    Salesforce アカウントのシングル サインオン設定を有効にできない場合は、 [Salesforce クライアント サポート チーム](https://help.salesforce.com/support)にお問い合わせください。
5. [ **SAML Enabled] を**選択し、[ **保存]** を選択します。

    [Image: シングル サインオンの構成 (SAML 有効)]
6. SAML シングル サインオン設定を構成するには、[ **メタデータ ファイルから新規**] を選択します。

    [Image: メタデータ ファイルからの新規シングル サインオンの設定]
7. [ **ファイルの選択] を選択** して、ダウンロードしたメタデータ XML ファイルをアップロードし、[ **作成**] を選択します。

    [Image: シングル サインオンの構成 (ファイルの選択)]
8. **[SAML Single Sign-On Settings](SAML シングル サインオンの設定)** ページでは、各フィールドが自動的に設定されます。SAML JIT を使用する場合は、 **[User Provisioning Enabled](ユーザー プロビジョニングは有効です)** を選択し、 **[SAML Identity Type](SAML ID の種類)** として **[Assertion contains the Federation ID from the User object](アサーションにはユーザー オブジェクトからのフェデレーション ID が含まれます)** を選択します。それ以外の場合は、 **[User Provisioning Enabled](ユーザー プロビジョニングは有効です)** の選択を解除し、 **[SAML Identity Type](SAML ID の種類)** として **[Assertion contains the User's Salesforce username](アサーションにユーザーの Salesforce ユーザー名が含まれています)** を選択します。 **保存** を選択します。

    [Image: シングル サインオンのユーザー プロビジョニングを有効にする]

    注意

    SAML JIT を構成した場合は、「**Microsoft Entra SSO の構成」**セクションに必要な SAML トークン属性を追加する必要があります。 Salesforce アプリケーションでは特定の SAML アサーションが使用されるため、SAML トークン属性の構成に特定の属性が必要です。 次のスクリーンショットには、Salesforce で必要な属性の一覧が示されています。

    [Image: JIT に必須の属性のウィンドウを示すスクリーンショット。]

    SAML JIT でユーザーをプロビジョニングする場合に問題が引き続き発生する場合は、「[Just-In-Time プロビジョニング要件と SAML アサーション フィールド](https://help.salesforce.com/s/articleView?id=sf.sso_jit_requirements.htm&amp;type=5)」を参照してください。 一般に、JIT が失敗すると、次のようなエラーが表示される場合があります。`We can't log you in because of an issue with single sign-on. Contact your Salesforce admin for help.`
9. Salesforce の左側のナビゲーション ウィンドウで、[ **会社の設定]** を選択して関連セクションを展開し、[ **マイ ドメイン**] を選択します。

    [Image: シングル サインオンの構成 (マイ ドメイン)]
10. [ **認証構成]** セクションまで下にスクロールし、[ **編集** ] ボタンを選択します。

    [Image: シングル サインオン認証構成の設定]
11. [ **認証構成]** セクションで、SAML SSO 構成の **ログイン ページ** と **AzureSSO** as **Authentication Service** を確認し、[保存] を選択 **します**。

    注意

    複数の認証サービスを選択した場合、ユーザーが Salesforce 環境へのシングル サインオンを開始すると、サインインに使用する認証サービスを選択するよう要求されます。 このメッセージが表示されないようにするには、**その他すべての認証サービスをオフのままに**しておいてください。

#### Salesforce テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Salesforce に作成します。 Salesforce では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Salesforce に存在しない場合は、Salesforce にアクセスしようとしたときに新しいユーザーが作成されます。 Salesforce では、自動ユーザー プロビジョニングもサポートされています。 詳細については、「 [Salesforce 自動ユーザー プロビジョニングの構成」を](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-provisioning-tutorial)参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra シングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Salesforce のサインオン URL にリダイレクトされます。
- Salesforce のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリ ポータルで [Salesforce] タイルを選択すると、SSO を設定した Salesforce に自動的にサインインします。 マイ アプリ ポータルの詳細については、「[マイ アプリ ポータルの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)を参照してください。

### Salesforce (モバイル) の SSO をテストする

Salesforce モバイル アプリで SSO をテストするには、次の手順を実行します。

1. Salesforce モバイル アプリケーションを開きます。 サインイン ページで、[ **カスタム ドメインの使用**] を選択します。

    [Image: Salesforce モバイル アプリの [カスタムドメインを使用]]
2. [ **カスタム ドメイン]** ボックスに、登録済みのカスタム ドメイン名を入力し、[ **続行**] を選択します。

    [Image: Salesforce モバイル アプリの [カスタムドメイン]]
3. Microsoft Entra資格情報を入力して Salesforce アプリケーションにサインインし、**Next** を選択します。

    [Image: Salesforce モバイル アプリ Microsoft Entra 資格情報]
4. 次に示すように、[ **アクセスの許可** ] ページで、[ **許可** ] を選択して Salesforce アプリケーションへのアクセスを許可します。

    [Image: Salesforce モバイル アプリの [アクセスを許可しますか?]]
5. 最後に、サインインに成功すると、アプリケーションのホームページが表示されます。

    [Image: Salesforce モバイル アプリのホームページ][Image: Salesforce モバイル アプリ]

### Salesforce で既存のユーザーを検出する

Microsoft Entraとの統合の前に、Salesforce アカウントに 1 人以上のユーザーが既に存在する場合があります。 アカウント検出機能を使用すると、Salesforce のすべてのユーザーのレポートを生成し、Entra で一致するアカウントを持つユーザーと、Salesforce に対してローカルなユーザーを 1 回のクリックで識別できます。 詳細については、 [アプリケーションのプロビジョニングにアカウント検出を使用する方法に関するページを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)参照してください。 これにより、Entra へのオンボードを簡素化しながら、承認されていないアクセスを定期的に監視することもできます。

### ローカル アカウントを介したアプリケーション アクセスを禁止する

SSO が機能することを検証し、組織内でロールアウトしたら、 [ローカル資格情報](https://help.salesforce.com/s/articleView?id=sf.sso_enforce_sso_login.htm&amp;type=5)を使用してアプリケーション アクセスを無効にします。 これにより、Salesforce へのサインインを保護するために、条件付きアクセス ポリシーや MFA などが確実に設定されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/samanage-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SolarWinds Service Desk (以前の Samanage) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/samanage-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-23
- Summary: Microsoft Entra ID から SolarWinds Service Desk (旧称 Samanage) にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について学習します。

この記事では、自動ユーザー プロビジョニングを構成するために SolarWinds Service Desk (以前の Samanage) と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[SolarWinds Service Desk](https://www.samanage.com/pricing/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### 新しい SolarWinds Service Desk アプリケーションに移行する

SolarWinds Service Desk との既存の統合がある場合は、今後の変更について次のセクションを参照してください。 SolarWinds Service Desk を初めて設定する場合は、このセクションをスキップして、「**サポートされる機能**」に進むことができます。

##### 変更点

- Microsoft Entra ID 側の変更: Samange でユーザーをプロビジョニングするための承認方法は、これまで **基本認証**でした。間もなく、承認方法が **有効期間の長いシークレット トークン**に変更されたことがわかります。

##### 既存のカスタム統合を新しいアプリケーションに移行するには、どうすればよいですか?

有効な管理者資格情報を持つ既存の SolarWinds Service Desk 統合がある場合、**操作は必要はありません**。 新しいアプリケーションに顧客が自動的に移行されます。 この処理は、完全にバックグラウンドで実行されます。 既存の資格情報の有効期限が切れている場合、またはアプリケーションへのアクセスを再度承認する必要がある場合は、有効期間の長いシークレット トークンを生成する必要があります。 新しいトークンを生成するには、この記事の手順 2 を参照してください。

##### アプリケーションが移行されたかどうかを確認するにはどうすればよいですか?

アプリケーションが移行されると、[ **管理者資格情報** ] セクションの [ **管理者ユーザー名** ] フィールドと **[管理者パスワード** ] フィールドが 1 つの **[シークレット トークン** ] フィールドに置き換えられます。

### サポートされている機能

- SolarWinds Service Desk でユーザーを作成する
- アクセスが不要になった場合に SolarWinds Service Desk のユーザーを削除する
- Microsoft Entra ID と SolarWinds Service Desk の間でユーザー属性の同期を維持する
- SolarWinds Service Desk でグループとグループ メンバーシップをプロビジョニングする
- SolarWinds Service Desk への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/samanage-tutorial) (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Professional パッケージを使用する [SolarWinds Service Desk テナント](https://www.samanage.com/pricing/)。
- 管理者のアクセス許可を持つ SolarWinds Service Desk のユーザー アカウント。

注

ロールのインポートを行うときは、Microsoft Entra ID でロールを手動で編集しないでください。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と SolarWinds Service Desk の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように SolarWinds Service Desk を構成する

認証用のシークレット トークンを生成するには、 [API 統合のアーティクル トークン認証に関する記事を](https://help.samanage.com/s/article/Tutorial-Tokens-Authentication-for-API-Integration-1536721557657)参照してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから SolarWinds Service Desk を追加する

Microsoft Entra アプリケーション ギャラリーから SolarWinds Service Desk を追加し、SolarWinds Service Desk へのプロビジョニングの管理を開始します。 以前に SSO に対応するように SolarWinds Service Desk を設定した場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: SolarWinds Service Desk への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で SolarWinds Service Desk の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **SolarWinds Service Desk** を選択します。
4. **[プロビジョニング]** タブを選択します。

    [Image: 選択されている [プロビジョニング] タブを示すスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、 `https://api.samanage.com`入力します。 前に取得したシークレット トークンの値を、 **[シークレット トークン]** に入力します。 **[接続のテスト]** を選択して、Microsoft Entra ID が SolarWinds Service Desk に接続できることを確かめます。 接続に失敗した場合は、SolarWinds Service Desk アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から SolarWinds Service Desk に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で SolarWinds Service Desk のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、SolarWinds Service Desk API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Samanage のユーザー マッピング]
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から SolarWinds Service Desk に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で SolarWinds Service Desk のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Samange のグループ マッピング]
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

**[すべてのユーザーとグループを同期する]** オプションを選択し、SolarWinds Service Desk の**ロール**属性に値を構成した場合、 **[null の場合の既定値 (オプション)]** ボックスの値は次の形式で表現する必要があります。

- {"displayName":"role"}。この role は使用する既定値です。

### 変更ログ

- 2020 年 9 月 14 日 - 2 つの SaaS 記事の会社名を、 `https://github.com/ravitmorales`ごとに Samanage から SolarWinds Service Desk (以前の Samanage) に変更しました。
- 2020 年 4 月 22 日 - 基本認証から有効期間の長いシークレット トークンへ承認方法を更新しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/samanage-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SolarWinds Service Desk (以前の Samanage) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/samanage-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra ID と SolarWinds Service Desk (旧称 Samanage) の間でシングル サインオンを構成する方法について説明します。

この記事では、SolarWinds と Microsoft Entra ID を統合する方法について説明します。 SolarWinds を Microsoft Entra ID と統合すると、次のことが可能になります。

- SolarWinds にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで SolarWinds に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SolarWinds Service Desk は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- SolarWinds でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SolarWinds では、**SP**-Initiated SSO がサポートされます。
- SolarWinds では[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/samanage-provisioning-tutorial)がサポートされます。

### ギャラリーからの SolarWinds の追加

SolarWinds と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に SolarWinds をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SolarWinds**」と入力します。
4. 結果のパネルから **[SolarWinds]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SolarWinds 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SolarWinds 用の Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、SolarWinds での関連ユーザーとの間にリンク関係を確立する必要があります。

SolarWinds 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SolarWinds の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SolarWinds のテストユーザーを作成** - Microsoft Entra のユーザーの表現にリンクされた、SolarWinds における B.Simon の対応者を持つようになります。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[SolarWinds]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Company Name>.samanage.com/saml_login/<Company Name>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Company Name>.samanage.com`

    注

    これらの値は実際の値ではありません。 これらの値は、実際のサインオン URL と識別子で更新します。これについては、この記事の後半で説明します。 詳細については、[Samanage クライアント サポート チーム](https://www.samanage.com/support)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SolarWinds のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SolarWinds の SSO の構成

1. 別の Web ブラウザー ウィンドウで、SolarWinds 企業サイトに管理者としてログインします。
2. **ダッシュボードを**選択し、左側のナビゲーション ウィンドウで **[セットアップ]** を選択します。

    [Image: ダッシュボード]
3. [ **シングル サインオン] を選択します**。

    [Image: シングル サインオン]
4. **[Login using SAML (SAML でログイン)]** セクションで、次の手順を実行します。

    [Image: [SAML でログイン]]

    a. **SAML で単一 Sign-On を有効にする** を選択します。

    b。 **[Identity Provider URL](ID プロバイダー URL)** ボックスに、`https://YourAccountName.samanage.com` のような値を入力します。

    c. **[Login URL](ログイン URL)** が Azure portal の **[基本的な SAML 構成]** セクションの **[サインオン URL]** と一致することを確認します。

    d. **[ログアウト URL]** テキストボックスに **ログアウト URL** の値を入力します。

    e. **[SAML Issuer]** (SAML 発行者) ボックスに、ID プロバイダーに設定されたアプリ ID URI を入力します。

    f. Azure Portal からダウンロードした base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、 **[Paste your Identity Provider x.509 Certificate below](ID プロバイダー x.509 証明書を貼り付けてください)** ボックスに貼り付けます。

    g. **SolarWinds にユーザーが存在しない場合は、[ユーザーの作成]** を選択します。

    h. **[更新]** を選択します。

#### SolarWinds のテスト ユーザーを作成する

Microsoft Entra ユーザーが SolarWinds にログインできるようにするには、そのユーザーを SolarWinds にプロビジョニングする必要があります。 SolarWinds の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. SolarWinds 企業サイトに管理者としてログインします。
2. **[ダッシュボード**] を選択し、左側のナビゲーション パンで **[セットアップ]** を選択します。

    [Image: セットアップ]
3. [ **ユーザー** ] タブを選択する

    [Image: ユーザー]
4. **新しいユーザー**を選択します。

    [Image: 新しいユーザー]
5. プロビジョニングする Microsoft Entra アカウントの **名前** と **メール アドレス** を入力し、[ **ユーザーの作成**] を選択します。

    [Image: ユーザーの作成]

    注

    Microsoft Entra アカウント所有者が電子メールを受信し、リンクに従ってアカウントを確認すると、そのアカウントがアクティブになります。 SolarWinds から提供されている他の SolarWinds ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

注

SolarWinds では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/samanage-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる SolarWinds のサインオン URL にリダイレクトされます。
- SolarWinds のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SolarWinds] タイルを選択すると、このオプションは SolarWinds のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/saml-toolkit-tutorial"} -->
## Microsoft Entra SAML Toolkit for Single sign-on を Microsoft Entra ID で構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/saml-toolkit-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Microsoft Entra SAML Toolkit 間にシングル サインオンを構成する方法について学習します。

この記事では、Microsoft Entra SAML Toolkit と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra SAML Toolkit と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra SAML Toolkit にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Microsoft Entra SAML Toolkit に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Microsoft Entra SAML Toolkit でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Microsoft Entra SAML Toolkit では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Microsoft Entra SAML Toolkit の追加

Microsoft Entra SAML Toolkit の Microsoft Entra ID への統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Microsoft Entra SAML Toolkit を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Microsoft Entra SAML Toolkit**」と入力します。
4. 結果のパネルから **[Microsoft Entra SAML Toolkit]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SAML Toolkit に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Microsoft Entra SAML Toolkit での Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Microsoft Entra SAML Toolkit の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SAML Toolkit で Microsoft Entra SSO を構成するには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Microsoft Entra SAML Toolkit SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    - **Microsoft Entra SAML Toolkit テストユーザーを作成する** - Microsoft Entra SAML Toolkit で B.Simon に対応するユーザーを作成し、Microsoft Entra におけるユーザーの表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Microsoft Entra SAML Toolkit**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[応答 URL]** ボックスに、URL として「`https://samltoolkit.azurewebsites.net/SAML/Consume`」と入力します。

    b。 **[サインオン URL]** ボックスに、URL として「`https://samltoolkit.azurewebsites.net/`」と入力します。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (未加工)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up Microsoft Entra SAML Toolkit] (Microsoft Entra SAML Toolkit の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Microsoft Entra SAML Toolkit SSO を構成する

1. 新しい Web ブラウザー ウィンドウを開きます。Microsoft Entra SAML Toolkit Web サイトに登録していない場合は、まず [ **登録**] を選択して登録します。 既に登録が済んでいる場合は、登録済みのサインイン資格情報を使用して Microsoft Entra SAML Toolkit 企業サイトにサインインします。

    [Image: Microsoft Entra SAML Toolkit [登録]]
2. **[SAML Toolkit]** ウィンドウで、**[SAML 構成]** を選択します。
3. **[作成]** を選択します。

    [Image: Microsoft Entra SAML Toolkit]
4. **[SAML SSO Configuration](SAML SSO 構成)** ページで、次の手順を実行します。

    [Image: Microsoft Entra SAML Toolkit SSO 構成の作成]

    1. **[ログイン URL]** テキスト ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。
    2. **[Microsoft Entra 識別子]** テキストボックスに、先ほどコピーした **[Microsoft Entra 識別子]** の値を貼り付けます。
    3. **[ログアウト URL]** テキストボックスに、前にコピーした**ログアウト URL** の値を貼り付けます。
    4. [ **ファイルの選択] を選択** し、ダウンロードした **証明書 (未加工)** ファイルをアップロードします。
    5. **[作成]** を選択します。
    6. SAML Toolkit SSO の構成ページでサインオン URL、識別子、および ACS URL の値をコピーし、**[基本的な SAML 構成]** セクションで、対応するテキスト ボックスに貼り付けます。

#### Microsoft Entra SAML Toolkit テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Microsoft Entra SAML Toolkit に作成します。 新しいユーザーを登録してツールでテスト ユーザーを作成し、すべてのユーザーの詳細を入力してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Microsoft Entra SAML Toolkit のサインオン URL にリダイレクトされます。
- Microsoft Entra SAML Toolkit のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Microsoft Entra SAML Toolkit] タイルを選択すると、このオプションは Microsoft Entra SAML Toolkit のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/saml-tutorial"} -->
## SAML 1.1 トークンが有効な LOB アプリを Microsoft Entra ID でシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAML 1.1 Token enabled LOB App の間でシングル サインオンを構成する方法について説明します。

この記事では、SAML 1.1 Token enabled LOB App と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と SAML 1.1 Token enabled LOB App を統合すると、次のことができます。

- SAML 1.1 Token enabled LOB App にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SAML 1.1 Token enabled LOB App に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な SAML 1.1 Token enabled LOB App サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SAML 1.1 Token enabled LOB App では、**SP** によって開始される SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの SAML 1.1 Token enabled LOB App の追加

Microsoft Entra ID への SAML 1.1 Token enabled LOB App の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SAML 1.1 Token enabled LOB App を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SAML 1.1 Token enabled LOB App**」と入力します。
4. 結果パネルから **[SAML 1.1 Token enabled LOB App]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAML 1.1 Token enabled LOB App の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SAML 1.1 Token enabled LOB App に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SAML 1.1 Token enabled LOB App の関連ユーザーとの間にリンク関係を確立する必要があります。

SAML 1.1 Token enabled LOB App に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAML 1.1 Token enabled LOB App の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAML 1.1 トークンが有効化されている LOB アプリ用のテストユーザーを作成** - SAML 1.1 トークンが有効化された LOB アプリにおいて、B.Simon の対応ユーザーを作成し、Microsoft Entra でのそのユーザーにリンクする。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SAML 1.1 トークン対応のLOBアプリ**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://your-app-url`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://your-app-url`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、SAML 1.1 Token enabled LOB App クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SAML 1.1 Token enabled LOB App のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAML 1.1 Token enabled LOB App の SSO の構成

**SAML 1.1 Token enabled LOB App** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を SAML 1.1 Token enabled LOB App サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SAML 1.1 Token enabled LOB App のテスト ユーザーの作成

このセクションでは、SAML 1.1 Token enabled LOB App で Britta Simon というユーザーを作成します。 SAML 1.1 Token enabled LOB App サポート チームと連携して、SAML 1.1 Token enabled LOB App プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションにより、ログイン フローを開始できる SAML 1.1 トークンが有効な LOB アプリのサインオン URL にリダイレクトされます。
- SAML 1.1 Token enabled LOB App のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SAML 1.1 Token enabled LOB App] タイルを選択すると、このオプションは SAML 1.1 Token enabled LOB App のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/samlssoconfluence-tutorial"} -->
## Microsoft Entra IDとシングルサインオンするために、resolution GmbHのConfluence用SAML SSOを設定する。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/samlssoconfluence-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAML SSO for Confluence by resolution GmbH の間のシングル サインオンを構成する方法について説明します。

この記事では、SAML SSO for Confluence by resolution GmbH と Microsoft Entra ID を統合する方法について説明します。 SAML SSO for Confluence by resolution GmbH と Microsoft Entra ID を統合すると、次のことができます:

- SAML SSO for Confluence by resolution GmbH にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SAML SSO for Confluence by resolution GmbH に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な SAML SSO for Confluence by resolution GmbH サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SAML SSO for Confluence by resolution GmbH では、**SP と IDP** によって開始される SSO がサポートされます

### ギャラリーからの SAML SSO for Confluence by resolution GmbH の追加

Microsoft Entra ID への SAML SSO for Confluence by resolution GmbH の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに SAML SSO for Confluence by resolution GmbH を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SAML SSO for Confluence by resolution GmbH**」と入力します。
4. 結果パネルから **[SAML SSO for Confluence by resolution GmbH]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAML SSO for Confluence by resolution GmbH 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、SAML SSO for Confluence by resolution GmbH に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SAML SSO for Confluence by resolution GmbH の関連ユーザーとの間にリンク関係を確立する必要があります。

SAML SSO for Confluence by resolution GmbH に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAML SSO for Confluence by resolution GmbH の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAML SSO for Confluence by resolution GmbH テスト ユーザーの作成** - SAML SSO for Confluence by resolution GmbH で Britta Simon に対応するユーザーを作成し、Microsoft Entra の Britta Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**SAML SSO for Confluence by resolution GmbH**&gt;**シングル サインオンに移動**します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、`https://<server-base-url>/plugins/servlet/samlsso` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<server-base-url>/plugins/servlet/samlsso` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<server-base-url>/plugins/servlet/samlsso` という形式で URL を入力します。

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[SAML SSO for Confluence by resolution GmbH クライアント サポート チーム](https://www.resolution.de/go/support)に問い合わせます。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAML SSO for Confluence by resolution GmbH の SSO の構成

1. 別の Web ブラウザー ウィンドウで、**SAML SSO for Confluence by resolution GmbH 管理者ポータル**に管理者としてログインします。
2. 歯車アイコンにカーソルを合わせ、**アドオン**を選択します。

3. 管理者アクセス ページにリダイレクトされます。 パスワードを入力し、[ **確認** ] ボタンを選択します。

    [Image: [Administrator Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者アクセス) ページを示すスクリーンショット。[Confirm](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/確認) ボタンが選択されています。]
4. **ATLASSIAN MARKETPLACE タブで**、[**新しいアドオンの検索**] を選択します。

    [Image: [新しいアドオンの検索] が選択されている [Atlassian Marketplace] タブを示すスクリーンショット。]
5. **SAML シングル サインオン (SSO) で Confluence を**検索し、[**インストール**] ボタンを選択して新しい SAML プラグインをインストールします。

    [Image: [Find new add-ons](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいアドオンの検索) ページを示すスクリーンショット。検索ボックスに [S A M L Single Sign On (S S O) for Confluence](Confluence の S A M L シングル サインオン (S S O)) と表示されており、[Install](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インストール) ボタンが選択されています。]
6. プラグインのインストールが開始されます。 **を選択して**を閉じます。

    [Image: [Installing](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インストール中) ダイアログを示すスクリーンショット。]

    [Image: [閉じる] アクションが選択されている [インストールされ、使用できるようになりました] ダイアログを示すスクリーンショット。]
7. **管理**を選択します。
8. [ **構成] を** 選択して新しいプラグインを構成します。

    [Image: [Manage](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) ページを示すスクリーンショット。[Configure](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) ボタンが選択されています。]
9. この新しいプラグインは、**[USERS & SECURITY]\(ユーザーとセキュリティ\)** タブにも表示されます。

    [Image: [USERS & SECURITY](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーとセキュリティ) タブを示すスクリーンショット。[S A M L SingleSignOn](S A M L SingleSignOn) が選択されています。]
10. **[SAML SingleSignOn Plugin Configuration]\(SAML SingleSignOn プラグインの構成\**) ページで、[**Add new IdP]\(新しい IdP の追加**\) ボタンを選択して ID プロバイダーの設定を構成します。

    [Image: [S A M L SingleSignOn Plugin Configuration](S A M L SingleSignOn プラグインの構成) ページを示すスクリーンショット。[Add new I d P](新しい I D P の追加) ボタンが選択されています。]
11. **[Choose your SAML Identity Provider](SAML ID プロバイダーの選択)** ページで、次の手順を実行します。

    [Image: [Choose your S A M L Identity Provider](S A M L I D プロバイダーの選択) ページを示すスクリーンショット。[I d P Type](I d P の種類)、[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)、および [Description](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/説明) テキスト ボックスが強調表示されています。]

    ある。 **Microsoft Entra ID** を Idp の種類として選択します。

    b。 ID プロバイダーの **[名前]** を追加します (例: Microsoft Entra ID)。

    c. ID プロバイダーの **[説明]** を追加します (例: Microsoft Entra ID)。

    d. [**次へ**] を選択します。
12. **[ID プロバイダーの構成]** ページで、[**次へ**] ボタンを選択します。
13. **[Import SAML IdP Metadata](SAML IDP メタデータのインポート)** ページで、次の手順を実行します。

    [Image: [Import S A M L I d P Metadata](S A M L I D P メタデータのインポート) ページを示すスクリーンショット。[Import](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インポート)、[Load File](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ファイルの読み込み)、および [Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ) ボタンが選択されています。]

    ある。 [ **ファイルの読み込み** ] ボタンを選択し、手順 5 でダウンロードしたメタデータ XML ファイルを選択します。

    b。 [ **インポート]** ボタンを選択します。

    c. インポートが成功するまでしばらく待ちます。

    d. **[次へ]** ボタンを選択します。
14. [ **ユーザー ID 属性と変換** ] ページで、[ **次へ** ] ボタンを選択します。

    [Image: [User ID attribute and transformation](ユーザーの I D 属性と変換) ページを示すスクリーンショット。[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ) ボタンが選択されています。]
15. [ **ユーザーの作成と更新** ] ページで、[ **保存] & [次へ** ] を選択して設定を保存します。

    [Image: [User creation and update](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成と更新) ページを示すスクリーンショット。[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ) ボタンが選択されています。]
16. [ **設定のテスト** ] ページで、[ **テストをスキップして手動で構成** ] を選択して、ユーザー テストを今のところスキップします。 これは次のセクションで実行され、Azure portal でいくつかの設定が必要です。

    [Image: [Test your settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定のテスト) ページを示すスクリーンショット。[Skip test & configure manually](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/テストをスキップして手動で構成) ボタンが選択されています。]
17. 表示されるダイアログ「**テストをスキップするということは...**」を確認して、「**OK**」を選択します。

    [Image: Configure single sign-on]

#### SAML SSO for Confluence by resolution GmbH のテスト ユーザーの作成

Microsoft Entra ユーザーが SAML SSO for Confluence by resolution GmbH にログインできるようにするには、ユーザーを SAML SSO for Confluence by resolution GmbH にプロビジョニングする必要があります。 SAML SSO for Confluence by resolution GmbH の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. SAML SSO for Confluence by resolution GmbH 企業サイトに管理者としてログインします。
2. 歯車アイコンをポイントし、[ **ユーザー管理**] を選択します。

3. [ユーザー] セクションで、[ **ユーザーの追加** ] タブを選択します。 **[ユーザーの追加** ] ダイアログ ページで、次の手順を実行します。

    [Image: 従業員の追加]

    ある。 **[Username](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名)** ボックスに、ユーザーのメール (Britta Simon など) を入力します。

    b。 **[Full Name](フル ネーム)** ボックスに、ユーザーの氏名 (Britta Simon など) を入力します。

    c. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メール)** ボックスに、ユーザーのメール アドレス (Brittasimon@contoso.com など) を入力します。

    d. **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** ボックスに、Britta Simon のパスワードを入力します。

    え [ **パスワードの確認]** を選択して、パスワードを再入力します。

    f. **[追加]** ボタンを選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる SAML SSO for Confluence by resolution GmbH サインオン URL にリダイレクトされます。
- SAML SSO for Confluence by resolution GmbH のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SAML SSO for Confluence by resolution GmbH に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで SAML SSO for Confluence by resolution GmbH タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SAML SSO for Confluence by resolution GmbH に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/samlssojira-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SAML SSO for Jira by Resolution GmbH を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/samlssojira-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAML SSO for Jira by resolution GmbH の間のシングル サインオンを構成する方法について説明します。

この記事では、SAML SSO for Jira by resolution GmbH と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と SAML SSO for Jira by resolution GmbH を統合すると、次のことができます:

- SAML SSO for Jira by resolution GmbH にアクセスするユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで SAML SSO for Jira by resolution GmbH に自動的にサインインするように設定する。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAML SSO for Jira by resolution GmbH でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SAML SSO for Jira by resolution GmbH では、**SP** と **IDP** によって開始される SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから SAML SSO for Jira by resolution GmbH を追加する

Microsoft Entra ID への SAML SSO for Jira by resolution GmbH の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAML SSO for Jira by resolution GmbH を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SAML SSO for Jira by resolution GmbH**」と入力します。
4. 結果パネルから **[SAML SSO for Jira by resolution GmbH]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAML SSO for Jira by resolution GmbH の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SAML SSO for Jira by resolution GmbH に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと SAML SSO for Jira by resolution GmbH の関連ユーザーとの間にリンクされた関係を確立する必要があります。

SAML SSO for Jira by resolution GmbH で Microsoft Entra SSO を構成してテストするには、次の手順に従います:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAML SSO for Jira by resolution GmbH の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAML SSO for Jira by resolution GmbH 用のテストユーザーを作成** - SAML SSO for Jira by resolution GmbH において B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SAML SSO for Jira by resolution GmbH**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順のようにします。

    N/A **[識別子]** ボックスに、`https://<server-base-url>/plugins/servlet/samlsso` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<server-base-url>/plugins/servlet/samlsso` のパターンを使用して URL を入力します

    c. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定**] を選択し、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<server-base-url>/plugins/servlet/samlsso` という形式で URL を入力します。

    注意

    識別子、応答 URL、サインオン URL では、**&lt;server-base-url&gt;** を Jira インスタンスのベース URL に置き換えます。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。 問題がある場合は、[SAML SSO for Jira by resolution GmbH クライアント サポート チーム](https://www.resolution.de/go/support)に問い合わせてください。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAML SSO for Jira by resolution GmbH の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Jira インスタンスに管理者としてサインインします。
2. 右側にある歯車アイコンをポイントし、[ **アプリの管理**] を選択します。

3. [管理者アクセス] ページにリダイレクトされた場合は、 **パスワード** を入力し、[ **確認** ] ボタンを選択します。

    [Image: [Administrator Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者アクセス) ページを示すスクリーンショット。]
4. Jira では通常、Atlassian マーケットプレースにリダイレクトされます。 そうでない場合は、左側のパネルで [ **新しいアプリを検索** ] を選択します。 **JIRA の SAML シングル サインオン (SSO)** を検索し、[**インストール**] ボタンを選択して SAML プラグインをインストールします。

    [Image: [Atlassian Marketplace for JIRA](JIRA の Atlassian マーケットプレース) を示すスクリーンショット。[S A M L Single Sign On (S S O) Jira, S A M L/S S O](S A M L シングル サインオン (S S O) Jira、S A M L/S S O) アプリの [Install](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インストール) ボタンを指している矢印が表示されています。]
5. プラグインのインストールが開始されます。 完了したら、[ **閉じる** ] ボタンを選択します。

    [Image: [Installing](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インストール中) ダイアログを示すスクリーンショット。]

    [Image: [Installed and ready to go!](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/インストールされ、使用できるようになりました) ダイアログと [閉じる] ボタンが選択されていることを示すスクリーンショット。]
6. 次に、**[管理]** を選択します。
7. その後、[ **構成** ] を選択して、インストールしたプラグインを構成します。

    [Image: [Manage apps](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリの管理) ページを示すスクリーンショット。[S A M L SingleSignOn for Jira](Jira の S A M L シングル サインオン) アプリの [Configure](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) ボタンが選択されています。]
8. **SAML SingleSignOn プラグイン構成**ウィザードで、[**Add new IdP]\(新しい IdP の追加**\) を選択して、Microsoft Entra ID を新しい ID プロバイダーとして構成します。

    [Image: [Welcome](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ようこそ) ページを示すスクリーンショット。[Add new I d P](新しい I D P の追加) ボタンが選択されています。]
9. **[Choose your SAML Identity Provider](SAML ID プロバイダーの選択)** ページで、次の手順のようにします。

    [Image: [Choose your S A M L Identity Provider](S A M L I D プロバイダーの選択) ページを示すスクリーンショット。[I d P Type](I d P の種類) および [Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前) テキスト ボックスが強調表示され、[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ) ボタンが選択されています。]

    N/A **Microsoft Entra ID** を Idp の種類として選択します。

    b。 ID プロバイダーの **[名前]** を追加します (例: Microsoft Entra ID)。

    c. (省略可能) ID プロバイダーの **[説明]** を追加します (例: Microsoft Entra ID)。

    d. [**次へ**] を選択します。
10. [ **ID プロバイダーの構成** ] ページで、[ **次へ**] を選択します。
11. **[Import SAML IdP Metadata](SAML IDP メタデータのインポート)** ページで、次の手順を実行します。

    [Image: [Import S A M L I d P Metadata](S A M L I D P メタデータのインポート) ページを示すスクリーンショット。[Select Metadata X M L File](メタデータ X M L ファイルの選択) アクションが選択されています。]

    N/A [ **メタデータ XML ファイルの選択** ] ボタンを選択し、前にダウンロードした **フェデレーション メタデータ XML** ファイルを選択します。

    b。 [ **インポート** ] ボタンを選択します。

    c. インポートが成功するまでしばらく待ちます。

    d. [ **次へ** ] ボタンを選択します。
12. [ **ユーザー ID 属性と変換** ] ページで、[ **次へ** ] ボタンを選択します。

    [Image: [User I D attribute and transformation](ユーザーの I D 属性と変換) ページを示すスクリーンショット。[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ) ボタンが選択されています。]
13. [ **ユーザーの作成と更新** ] ページで、[ **保存] & [次へ** ] を選択して設定を保存します。

    [Image: [User creation and update](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成と更新) ページを示すスクリーンショット。[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ) ボタンが選択されています。]
14. [ **設定のテスト** ] ページで、[ **テストをスキップして手動で構成** ] を選択して、ユーザー テストを今のところスキップします。 これは次のセクションで実行され、いくつかの設定が必要です。

    [Image: [Test your settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定のテスト) ページを示すスクリーンショット。[Skip test & configure manually](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/テストをスキップして手動で構成) ボタンが選択されています。]
15. [ **OK] を** 選択して警告をスキップします。

    [Image: [O K] ボタンが選択されている警告ダイアログを示すスクリーンショット。]

#### SAML SSO for Jira by resolution GmbH のテスト ユーザーの作成

Microsoft Entra ユーザーが SAML SSO for Jira by resolution GmbH にサインインできるようにするには、ユーザーを SAML SSO for Jira by resolution GmbH にプロビジョニングする必要があります。 この記事の場合は、手動でプロビジョニングを行う必要があります。 ただし、resolution による SAML SSO プラグインでは、他のプロビジョニング モデルも使用できます (**Just In Time** プロビジョニングなど)。 [SAML SSO by resolution GmbH](https://wiki.resolution.de/doc/saml-sso/latest/all) のドキュメントをご覧ください。 質問がある場合は、[resolution のサポート](https://www.resolution.de/go/support)に問い合わせてください。

**ユーザー アカウントを手作業でプロビジョニングするには、次の手順のようにします。**

1. 管理者として Jira インスタンスにサインインします。
2. 歯車アイコンをポイントし、 **[User management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー管理)** を選択します。

3. [管理者アクセス] ページにリダイレクトされた場合は、 **パスワード** を入力し、[ **確認** ] ボタンを選択します。

    [Image: [Administrator Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者アクセス) ページを示すスクリーンショット。[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード) テキスト ボックスが強調表示されています。]
4. [ **ユーザー管理** ] タブ セクションで、[ **ユーザーの作成**] を選択します。

    [Image: [User management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー管理) タブを示すスクリーンショット。[Create user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの作成) ボタンが選択されています。]
5. **[Create new user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの作成)** ダイアログ ページで、次の手順のようにします。 Microsoft Entra ID とまったく同じようにユーザーを作成する必要があります。

    [Image: 従業員の追加]

    N/A **[Email address](電子メール アドレス)** ボックスに、ユーザーのメール アドレス **BrittaSimon@contoso.com** を入力します。

    b。 **[Full Name](フル ネーム)** ボックスに、ユーザーの氏名を入力します: **Britta Simon**。

    c. **[Username](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名)** ボックスに、ユーザーのメール アドレス **BrittaSimon@contoso.com** を入力します。

    d. **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** ボックスに、ユーザーのパスワードを入力します。

    え [ **ユーザーの作成]** を選択して、ユーザーの作成を完了します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる SAML SSO for Jira by resolution GmbH のサインオン URL にリダイレクトされます。
- SAML SSO for Jira by resolution GmbH のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SAML SSO for Jira by resolution GmbH に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで SAML SSO for Jira by resolution GmbH タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SAML SSO for Jira by resolution GmbH に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。

### Jira の SSO リダイレクトを有効にする

前のセクションで説明したように、現在、シングル サインオンをトリガーするには 2 つの方法があります。 **Azure portal** を使用するか、または **Jira インスタンスへの特別なリンク**を使用します。 resolution GmbH による SAML SSO プラグインでは、単純に **Jira インスタンスを指している任意の URL にアクセスする**ことでシングル サインオンをトリガーすることもできます。

基本的に、Jira にアクセスするすべてのユーザーは、プラグインのオプションをアクティブ化した後、シングル サインオンにリダイレクトされます。

SSO リダイレクトを有効にするには、**お使いの Jira インスタンス**で次のようにします。

1. Jira で SAML SSO プラグインの構成ページにアクセスします。
2. 左側のパネルで [ **リダイレクト** ] を選択します。
3. **[Enable SSO Redirect](SSO リダイレクトを有効にする)** をオンにします。

    [Image: Jira の SAML SingleSignOn プラグインの構成ページの断片的なスクリーンショット ([Enable SSO Redirect](SSO リダイレクトを有効にする) チェック ボックスのオン状態を強調表示したところ)。]
4. 右上隅の **[Save Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定の保存)** ボタンをクリックします。

 に移動して `https://<server-base-url>/login.jsp?nosso` をオンにした場合は、このオプションを有効にした後でもユーザー名とパスワードの入力を求められることがあります。 やはり、**&lt;server-base-url&gt;** はベース URL に置き換えます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/samsara-tutorial"} -->
## Microsoft Entra ID で Samsara for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/samsara-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Samsara の間にシングル サインオンを構成する方法について説明します。

この記事では、Samsara と Microsoft Entra ID を統合する方法について説明します。 Samsara と Microsoft Entra ID を統合すると、次のことができます。

- Samsara にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Samsara に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Samsara でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Samsara では、**SP**開始の SSO と **IDP**開始の SSO をサポートしています。
- Samsara では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからSamsaraを追加

Microsoft Entra ID への Samsara の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Samsara を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Samsara**」と入力します。
4. 結果のパネルから **[Samsara]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Samsara 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Samsara に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Samsara の関連ユーザーの間にリンク関係を確立する必要があります。

Samsara に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **[Samsara でドメイン検証を構成する - Samsara](https://kb.samsara.com/hc/en-us/articles/31499789674893-Verify-Domains-for-Secure-SSO-Authentication#UUID-9e9af4f3-fa9a-e18c-723d-66e148c98140)** 内で SSO を有効にするには、ドメイン検証が前提条件です。
2. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
3. **Samsara テストユーザーを作成し、Microsoft Entra で B.Simon にリンクさせる対応ユーザーを設定します。**
4. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Samsara**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンのセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。
5. Samsara ダッシュボードを開き、[設定] &gt; [シングル Sign-On] タブに移動します。ユーザー SSO 接続を作成する場合は、[ユーザー SSO] ボックスで [ **追加** ] をクリックします。 ドライバー SSO 接続を作成する場合は、[ドライバー SSO] ボックスで [ **追加** ] をクリックします。 Samsara から Entra ID の SAML 構成に値をコピーする必要があります。

    [Image: 基本的なSAML構成の編集]
6. Entra IDで、**Basic SAML Configuration** セクションで、次の手順を実行します。

    ある。 Samsara のサービス プロバイダー エンティティ ID フィールドから、Entra IDの **Identifier (Entity ID)** テキスト ボックスにリンクをコピーします。

    b。 Samsara の [ポストバック/ACS URL] フィールドから、Entra IDの **Reply URL** テキスト ボックスにリンクをコピーします。

    注

    実際の応答 URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Samsara クライアント サポート チーム](mailto:support@samsara.com) に問い合わせるか、Samsara で **[設定**&gt;**single-sign-on]** に移動し、作成する接続を選択して適切な ACS と識別子の URL を取得します。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **アプリのフェデレーション メタデータ URL を** 見つけてコピーするか、 **フェデレーション メタデータ XML** をダウンロードします。 関連する SSO 構成 (ユーザーまたはドライバー) の [設定] &gt; [シングル サインオン] の Samsara ダッシュボードで、メタデータ URL を貼り付けるか、ファイルをアップロードします。 変更を適用するには、**保存**をクリックします。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Samsara テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Samsara に作成します。 Samsara では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Samsara に存在しない場合は、組織の標準管理者 (車載カメラへのアクセス権なし) の既定のロールを使用して認証後に新しいユーザーが作成されます。 その後、Samsara で、必要に応じてユーザーのアクセス権を増減できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Samsara のサインオン URL にリダイレクトされます。
- Samsara のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Samsara に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Samsara] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Samsara に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/samsung-knox-and-business-services-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Samsung Knox と Business Services を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/samsung-knox-and-business-services-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-09-29
- Summary: Microsoft Entra ID と Samsung Knox およびビジネス サービスの間でシングル サインオンを構成する方法について説明します。

この記事では、Samsung Knox と Business Services を Microsoft Entra ID と統合する方法について説明します。 Microsoft Entra ID と Samsung Knox およびビジネス サービスを統合すると、次のことが可能になります。

- Samsung Knox およびビジネス サービスにアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Samsung Knox およびビジネス サービスに自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Samsung Knox アカウント。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Samsung Knox と Business Services では、 **SP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Samsung Knox およびビジネス サービスの追加

Samsung Knox およびビジネス サービスと Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Samsung Knox およびビジネス サービスをギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「Samsung Knox and Business Services」と**入力します。
4. 結果パネルから **Samsung Knox と Business Services** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Samsung Knox およびビジネス サービス用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Samsung Knox と Business Services に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと [SamsungKnox.com](https://samsungknox.com/) の関連ユーザーとの間にリンク関係を確立する必要があります。

Samsung Knox およびビジネス サービス用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Samsung Knox と Business Services の SSO の**構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Samsung Knox およびビジネス サービスのテスト ユーザーを作成する** - Samsung Knox およびビジネス サービスで B.Simon に対応するユーザーを作成し、Microsoft Entra の当該ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Samsung Knox および Business Services**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://www.samsungknox.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://central.samsungknox.com/ams/ad/saml/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://account.samsung.com/`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Samsung Knox およびビジネス サービス SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として [SamsungKnox.com](https://samsungknox.com/) にサインインします。
2. 右上隅にある **アバター** を選択します。

    [Image: Samsung Knox アバター]
3. **[マイ アカウント**&gt;**SSO 設定**] に移動し、次の手順を実行します。

    [Image: Samsung knox の設定を示すスクリーンショット。]

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、Microsoft Entra 管理センターからコピーした **識別子** URL を貼り付けます。

    b。 **応答 URL (アサーション コンシューマー サービス URL)** の値をコピーし、この値を Microsoft Entra 管理センターの **[基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスに貼り付けます。

    c. [ **アプリのフェデレーション メタデータ URL** ] テキスト ボックスに、Microsoft Entra 管理センターからコピーした **アプリのフェデレーション メタデータ URL を** 貼り付けます。

    d. [ **SSO に接続] を**選択します。

#### Samsung Knox およびビジネス サービスのテスト ユーザーの作成

このセクションでは、Samsung Knox およびビジネス サービスで Britta Simon というユーザーを作成します。 Samsung Knox 組織にサブ管理者またはテスト ユーザーを招待する方法については、 [Knox Configure](https://docs.samsungknox.com/admin/knox-configure/Administrators.htm) または [Knox Mobile Enrollment](https://docs.samsungknox.com/admin/knox-mobile-enrollment/kme-add-an-admin.htm) 管理者ガイドを参照してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる [SamsungKnox.com](https://samsungknox.com/) にリダイレクトされます。
- [SamsungKnox.com](https://samsungknox.com/) に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Samsung Knox と Business Services] タイルを選択すると、このオプションは [SamsungKnox.com](https://samsungknox.com/) にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sansan-tutorial"} -->
## Microsoft Entra ID で Sansan for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sansan-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sansan 間にシングル サインオンを構成する方法について学習します。

この記事では、Sansan と Microsoft Entra ID を統合する方法について説明します。 Sansan を Microsoft Entra ID と統合すると、次のことができます。

- Sansan にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Sansan に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SanSan でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Sansan では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Sansan の追加

Microsoft Entra ID への Sansan の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Sansan を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Sansan**」と入力します。
4. 結果ウィンドウで **[Sansan]** を選択し、アプリケーションを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sansan 用の Microsoft Entra SSO を構成してテストする

**Britta Simon** というテスト ユーザーを使用して、Sansan で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Sansan の関連ユーザーとの間にリンク関係を確立する必要があります。

Sansan で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成する** - Britta Simon を使って Microsoft Entra シングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当て**、Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sansan SSO の構成**- アプリケーション側で SSO 設定を構成します。
    1. **Sansan でテストユーザーを作成し**、Britta Simon に対応するユーザーとして、Microsoft Entra のユーザー表示にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sansan** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ページで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://ap.sansan.com/saml2/<COMPANY_NAME>`
    2. [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

        | 環境 | URL |
        | --- | --- |
        | パソコン | `https://ap.sansan.com/v/saml2/<COMPANY_NAME>/acs` |
        | スマートフォン アプリ | `https://internal.api.sansan.com/saml2/<COMPANY_NAME>/acs` |
        | スマートフォン Web | `https://ap.sansan.com/s/saml2/<COMPANY_NAME>/acs` |
    3. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://ap.sansan.com/`

    注

    これらの値は実際の値ではありません。 **Sansan 管理者設定**で実際の識別子と応答 URL の値を確認してください。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Sansan のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ```Logout
     https://login.microsoftonline.com/common/wsfederation?wa=wsignout1.0
    ```

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sansan SSO の構成

**Sansan** 側で**シングル サインオン設定**を実行するには、要件に従って以下の手順を実行してください。

- [日本語](https://jp-help.sansan.com/hc/ja/articles/900001551383)バージョン。
- [英語](https://jp-help.sansan.com/hc/en-us/articles/900001551383)バージョン。

#### Sansan のテスト ユーザーの作成

このセクションでは、SanSan に、 Britta Simon というユーザーを作成します。 ユーザーを作成する方法の詳細については、[こちらの](https://jp-help.sansan.com/hc/articles/206508997-Adding-users)手順を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Sansan のサインオン URL にリダイレクトされます。
- Sansan のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Sansan] タイルを選択すると、このオプションは Sansan のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-analytics-cloud-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して SAP Analytics Cloud へのユーザー プロビジョニングを自動化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-analytics-cloud-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-23
- Summary: SAP Cloud Identity Services を使用して、Microsoft Entra ID から SAP Analytics Cloud に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SAP Cloud Identity Services と Microsoft Entra ID で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスと SAP Cloud Identity Services を使用して、SAP Analytics Cloud に対するユーザーのプロビジョニングとプロビジョニング解除が自動的に行われます。 Microsoft Entra プロビジョニングが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされている機能

- SAP Analytics Cloud でユーザーを作成し、SAP Analytics Cloud へのシングル サインオンを有効にする
- アクセスが不要になった場合に SAP Analytics Cloud のユーザーを削除する
- Microsoft Entra ID と SAP Analytics Cloud の間でユーザー属性の同期を維持する

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP Analytics Cloud
- SAP Cloud Identity Services のテナント
- 管理者アクセス許可がある SAP Identity Provisioning の管理コンソールのユーザー アカウント。 Identity Provisioning 管理コンソールのプロキシ システムにアクセスできることを確認します。 **[プロキシ システム]** タイルが表示されない場合は、このタイルへのアクセスを要求するコンポーネント **BC-IAM-IPS** のインシデントを作成します。

### 手順 1: プロビジョニングの展開を計画する

Microsoft Entra には、SAP ECC、SAP Cloud Identity Services、SAP SuccessFactors へのコネクタがあります。 SAP Analytics Cloud またはその他のアプリケーションへのプロビジョニングでは、ユーザーはまず Microsoft Entra ID に存在する必要があります。 Microsoft Entra ID にユーザーを作成すると、それらのユーザーを Microsoft Entra ID から SAP Cloud Identity Services にプロビジョニングできます。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内の Microsoft Entra ID から送信されたユーザーを、SAP クラウド コネクタなどを介した [`SAP Analytics Cloud`](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/sap-analytics-cloud) やその他を含むダウンストリーム SAP アプリケーションにプロビジョニングします。

[Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

### 手順 2: Microsoft Entra ID に適切なユーザーが存在することを確認する

SuccessFactors などのソースからの HR 受信を使用することで、従業員が所属したり異動したり退職したりする際に、Microsoft Entra ID のユーザー一覧を常に最新の状態に保つことができます。 グループまたはアプリケーション ロールの割り当てを使用して、SAP Analytics Cloud にアクセスできるユーザーまたは SAP Analytics Cloud にアクセスできるロールのスコープを設定する予定で、テナントにMicrosoft Entra ID ガバナンスのライセンスがある場合は SAP Cloud Identity Services または SAP Analytics Cloud を表すアプリケーションの Microsoft Entra ID で、[アプリケーション ロールの割り当てに対する変更を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#assign-users-the-necessary-application-access-rights-in-microsoft-entra)することもできます。 プロビジョニング前に職務の分離やその他のコンプライアンス チェックを実行する方法の詳細については、「[アクセス ライフサイクル管理シナリオの移行](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/migrate-from-sap-idm#migrate-access-lifecycle-management-scenarios)」を参照してください。

SAP アプリケーションをターゲットとする ID ライフサイクルの詳細なガイダンスについては、「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー ID プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

### 手順 3: Microsoft Entra ID から SAP Cloud Identity Services へのプロビジョニングを構成する

SAP Cloud Identity Services と統合された SAP Analytics Cloud や他のアプリケーションへのユーザーのプロビジョニングを準備するには、SAP Cloud Identity Services にそれらのアプリケーションに必要なスキーマ マッピングがあることを確認します。 次に、[Microsoft Entra ID から SAP Cloud Identity Services へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#provision-users-to-sap-cloud-identity-services)を構成します。 SAP Cloud Identity Services はその後、必要に応じてダウンストリーム SAP アプリケーションにユーザーをプロビジョニングします。

Microsoft Entra から SAP Cloud Identity Services にユーザーをプロビジョニングするには、2 つの方法があります。

- SAP Analytics Cloud のロールにユーザーを割り当てるなど、Microsoft Entra ID のグループを使用する場合は、SAP Cloud Identity Services プロビジョニングを使用します。 まず、SAP Analytics Cloud で使用される SAP ビジネス ロール用の Microsoft Entra グループを作成します。 次に、SAP Cloud Identity Services のプロビジョニングで、[Microsoft Entra ID をソースとして構成](https://help.sap.com/docs/identity-provisioning/identity-provisioning/microsoft-azure-active-directory)し、ユーザーとグループを Microsoft Entra ID から SAP Cloud Identity Services に取り込み、作成されたグループを SAP ビジネス ロールにマップします。 詳細については、SAP のドキュメントの「[Provision users from Microsoft Azure AD to SAP Cloud Identity Services - Identity Authentication (Microsoft Azure AD から SAP Cloud Identity Services にユーザーをプロビジョニングする - Identity Authentication)](https://blogs.sap.com/2022/02/04/provision-users-from-microsoft-azure-ad-to-sap-cloud-identity-services-identity-authentication/)」を参照してください。
- または、Microsoft Entra ID でグループを使用する必要がない場合は、Microsoft Entra プロビジョニング サービスを使用できます。 このシナリオでは、SAP Analytics Cloud を表すアプリケーションを作成し、SAP Analytics Cloud へのアクセスを必要とするユーザーをそのアプリケーションに割り当てます。 次に、[Microsoft Entra ID を使用した、SAP Cloud Identity Services への自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)を構成します。 これらのユーザーが SAP Cloud Identity Services にプロビジョニングされるまで待ち、SAP Analytics Cloud ターゲットに必要な属性があることを確認します。

注

小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 ユーザーが SAP ダウンストリーム ターゲットで適切なアクセス権を持っていること、サインイン時に適切なロールを持っていることを確認します。

### 手順 4: SAP Cloud Identity Services から SAP Analytics Cloud へのプロビジョニングを構成する

この手順では、SAP Cloud Identity Services Identity Provisioning を使用して SAP Analytics Cloud をターゲット システムとして構成します。このシステムでは、ユーザーとグループ メンバーをプロビジョニングできます。 SAP Analytics Cloud については、[SAP Analytics Cloud へのプロビジョニングに関する SAP ドキュメント](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/sap-analytics-cloud)を参照してください。

### 手順 5: シングル サインオンの構成

SAP アプリケーションのユーザーのプロビジョニングを設定すると、それらのアプリケーションとのシングル サインオンを有効にする必要があります。 Microsoft Entra ID は、SAP アプリケーションの ID プロバイダーおよび認証機関として機能することができます。 [Microsoft Entra シングル サインオン (SSO) と SAP Cloud Identity Services との統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)をまだ構成していない場合は、構成します。

SAP SaaS や最新のアプリにシングル サインオンを構成する方法の詳細は、「[SSO の有効化](https://learn.microsoft.com/ja-jp/entra/id-governance/sap#enable-sso)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-ariba-spend-management-solutions-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して SAP Spend Management ソリューションへのユーザー プロビジョニングを自動化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-ariba-spend-management-solutions-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-10-01
- Summary: SAP Cloud Identity Services を使用して、Microsoft Entra ID から SAP Spend Management ソリューションに対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために SAP Cloud Identity Services と Microsoft Entra ID で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスと SAP Cloud Identity Services を使用して、SAP Spend Management ソリューションに対するユーザーのプロビジョニングとプロビジョニング解除が自動的に行われます。 Microsoft Entra プロビジョニングが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされている機能

- SAP Spend Management ソリューション アプリでユーザーを作成し、SAP Spend Management ソリューション アプリへのシングル サインオンを有効にする
- アクセスが不要になった場合に SAP Spend Management ソリューション アプリのユーザーを削除する
- Microsoft Entra ID と SAP Spend Management ソリューション アプリ の間でのユーザー属性の同期を維持する

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP Spend Management ソリューション アプリ (SAP Ariba Category Management または SAP Ariba Central Invoice Management)
- SAP Cloud Identity Services のテナント
- 管理者アクセス許可がある SAP Identity Provisioning の管理コンソールのユーザー アカウント。 Identity Provisioning 管理コンソールのプロキシ システムにアクセスできることを確認します。 **[プロキシ システム]** タイルが表示されない場合は、このタイルへのアクセスを要求するコンポーネント **BC-IAM-IPS** のインシデントを作成します。

### 手順 1:プロビジョニングのデプロイを計画する

Microsoft Entra には、SAP ECC、SAP Cloud Identity Services、SAP SuccessFactors へのコネクタがあります。 SAP Spend Management ソリューション アプリまたはその他のアプリケーションへのプロビジョニングでは、ユーザーはまず Microsoft Entra ID に存在する必要があります。 Microsoft Entra ID にユーザーを作成すると、それらのユーザーを Microsoft Entra ID から SAP Cloud Identity Services にプロビジョニングできます。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内の Microsoft Entra ID から送信されたユーザーを、SAP クラウド コネクタを介した [`SAP Ariba Category Management`](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/sap-ariba-category-management-e4c55e449bb14338a22dd7a49026445b) や[「SAP Ariba Central Invoice Management」](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/sap-ariba-central-invoice-management)、その他を含むダウンストリーム SAP アプリケーションにプロビジョニングします。

[Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

### 手順 2: Microsoft Entra ID に適切なユーザーがあることを確かめる

その後、SuccessFactors からの HR 受信を使用して、従業員の参加、移動、離脱時に Microsoft Entra ID のユーザーの一覧を最新の状態に保つことができます。 グループまたはアプリケーション ロールの割り当てを使用して、SAP Spend Management ソリューション アプリにアクセスできるユーザーまたは同アプリにアクセスできるロールのスコープを設定する予定で、テナントにMicrosoft Entra ID ガバナンスのライセンスがある場合は SAP Cloud Identity Services または SAP Spend Management ソリューション アプリを表すアプリケーションの Microsoft Entra ID で、[アプリケーション ロールの割り当てに対する変更を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#assign-users-the-necessary-application-access-rights-in-microsoft-entra)することもできます。 プロビジョニング前に職務の分離やその他のコンプライアンス チェックを実行する方法の詳細については、「[アクセス ライフサイクル管理シナリオの移行](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/migrate-from-sap-idm#migrate-access-lifecycle-management-scenarios)」を参照してください。

SAP アプリケーションをターゲットとする ID ライフサイクルの詳細なガイダンスについては、「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー ID プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

### 手順 3: Microsoft Entra ID から SAP Cloud Identity Services へのプロビジョニングを構成する

SAP Cloud Identity Services と統合された SAP Spend Management そソリューション アプリや他のアプリケーションへのユーザーのプロビジョニングを準備するには、SAP Cloud Identity Services にそれらのアプリケーションに必要なスキーマ マッピングがあることを確認します。 次に、[Microsoft Entra ID から SAP Cloud Identity Services へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#provision-users-to-sap-cloud-identity-services)を構成します。 SAP Cloud Identity Services はその後、必要に応じてダウンストリーム SAP アプリケーションにユーザーをプロビジョニングします。

Microsoft Entra から SAP Cloud Identity Services にユーザーをプロビジョニングするには、2 つの方法があります。

- SAP Spend Management ソリューション アプリのロールにユーザーを割り当てるなど、Microsoft Entra ID のグループを使用している場合は、SAP Cloud Identity Services プロビジョニングを使用します。 まず、SAP Analytics Cloud で使用される SAP ビジネス ロール用の Microsoft Entra グループを作成します。 次に、SAP Cloud Identity Services のプロビジョニングで、[Microsoft Entra ID をソースとして構成](https://help.sap.com/docs/identity-provisioning/identity-provisioning/microsoft-azure-active-directory)し、ユーザーとグループを Microsoft Entra ID から SAP Cloud Identity Services に取り込み、作成されたグループを SAP ビジネス ロールにマップします。 詳細については、SAP のドキュメントの「[Provision users from Microsoft Azure AD to SAP Cloud Identity Services - Identity Authentication (Microsoft Azure AD から SAP Cloud Identity Services にユーザーをプロビジョニングする - Identity Authentication)](https://blogs.sap.com/2022/02/04/provision-users-from-microsoft-azure-ad-to-sap-cloud-identity-services-identity-authentication/)」を参照してください。
- または、Microsoft Entra ID でグループを使用する必要がない場合は、Microsoft Entra プロビジョニング サービスを使用できます。 このシナリオでは、SAP Spend Management ソリューション アプリを表すアプリケーションを作成し、SAP Spend Management ソリューション アプリへのアクセスを必要とするユーザーをそのアプリケーションに割り当てます。 次に、[Microsoft Entra ID を使用した、SAP Cloud Identity Services への自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)を構成します。 これらのユーザーが SAP Cloud Identity Services にプロビジョニングされるまで待ち、SAP Spend Management ソリューション ターゲット アプリに必要な属性があることを確認します。

注

小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 ユーザーが SAP ダウンストリーム ターゲットで適切なアクセス権を持っていること、サインイン時に適切なロールを持っていることを確認します。

### 手順 4: SAP Cloud Identity Services から SAP Ariba へのプロビジョニングを構成する

この手順では、SAP Cloud Identity Services Identity Provisioning を使用して、SAP Spend Management ソリューション アプリをターゲット システムとして構成します。これにより、ユーザーとグループ メンバーをプロビジョニングできます。 SAP Ariba カテゴリ管理については、[SAP Ariba カテゴリ管理へのプロビジョニングに関する SAP ドキュメント](https://help.sap.com/docs/identity-provisioning/identity-provisioning/sap-ariba-category-management-e4c55e449bb14338a22dd7a49026445b)を参照してください。 SAP Ariba Central Invoice Management については、[SAP Ariba Central Invoice Management へのプロビジョニングに関する SAP ドキュメント](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/sap-ariba-central-invoice-management)を参照してください。

### 手順 5: シングル サインオンの構成

SAP アプリケーションのユーザーのプロビジョニングを設定すると、それらのアプリケーションとのシングル サインオンを有効にする必要があります。 Microsoft Entra ID は、SAP アプリケーションの ID プロバイダーおよび認証機関として機能することができます。 [Microsoft Entra シングル サインオン (SSO) と SAP Cloud Identity Services との統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)をまだ構成していない場合は、構成します。

SAP SaaS や最新のアプリにシングル サインオンを構成する方法の詳細は、「[SSO の有効化](https://learn.microsoft.com/ja-jp/entra/id-governance/sap#enable-sso)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-btp-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して SAP BTP へのユーザー プロビジョニングを自動化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-btp-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-10-01
- Summary: SAP Cloud Identity Services を使用して、Microsoft Entra ID から SAP BTP に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SAP Cloud Identity Services と Microsoft Entra ID で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスと SAP Cloud Identity Services を使用して、SAP BTP に対するユーザーのプロビジョニングとプロビジョニング解除が自動的に行われます。 Microsoft Entra プロビジョニングが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされている機能

- SAP BTP でユーザーを作成して SAP BTP へのシングル サインオンを有効にする
- アクセスが不要になった場合に SAP BTP のユーザーを削除する
- Microsoft Entra ID と SAP BTP の間でユーザー属性の同期を維持する

Microsoft Entra ID Governance を使用して、アプリケーションの BTP ロール コレクション内のロールに関連付けられているグループを設定することで、SAP BTP アプリケーションへのアクセスを管理することもできます。 詳細については、 [SAP BTP へのアクセスの管理を](https://community.sap.com/t5/technology-blogs-by-members/identity-and-access-management-with-microsoft-entra-part-i-managing-access/ba-p/13873276)参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP BTP アプリ (SAP BTP ABAP 環境または SAP BTP XS Advanced UAA Cloud Foundry)
- SAP Cloud Identity Services のテナント
- 管理者アクセス許可がある SAP Identity Provisioning の管理コンソールのユーザー アカウント。 Identity Provisioning 管理コンソールのプロキシ システムにアクセスできることを確認します。 **[プロキシ システム]** タイルが表示されない場合は、このタイルへのアクセスを要求するコンポーネント **BC-IAM-IPS** のインシデントを作成します。

### 手順 1:プロビジョニングのデプロイを計画する

Microsoft Entra には、SAP ECC、SAP Cloud Identity Services、SAP SuccessFactors へのコネクタがあります。 SAP BTP またはその他のアプリケーションへのプロビジョニングでは、ユーザーはまず Microsoft Entra ID に存在する必要があります。 Microsoft Entra ID にユーザーを作成すると、それらのユーザーを Microsoft Entra ID から SAP Cloud Identity Services にプロビジョニングできます。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内の Microsoft Entra ID から送信されたユーザーを、SAP クラウド コネクタを介した [`SAP BTP ABAP environment`](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/target-sap-btp-abap-environment) や [「SAP BTP XS Advanced UAA (Cloud Foundry)」](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/target-sap-btp-xs-advanced-uaa-cloud-foundry)、その他を含むダウンストリーム SAP アプリケーションにプロビジョニングします。

[Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

### 手順 2: Microsoft Entra ID に適切なユーザーがあることを確かめる

その後、SuccessFactors からの HR 受信を使用して、従業員の参加、移動、離脱時に Microsoft Entra ID のユーザーの一覧を最新の状態に保つことができます。 グループまたはアプリケーション ロールの割り当てを使用して、SAP BTP にアクセスできるユーザーまたは SAP BTP にアクセスできるロールのスコープを設定する予定で、テナントにMicrosoft Entra ID ガバナンスのライセンスがある場合は SAP Cloud Identity Services または SAP BTP を表すアプリケーションの Microsoft Entra ID で、[アプリケーション ロールの割り当てに対する変更を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#assign-users-the-necessary-application-access-rights-in-microsoft-entra)することもできます。 プロビジョニング前に職務の分離やその他のコンプライアンス チェックを実行する方法の詳細については、「[アクセス ライフサイクル管理シナリオの移行](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/migrate-from-sap-idm#migrate-access-lifecycle-management-scenarios)」を参照してください。

SAP アプリケーションをターゲットとする ID ライフサイクルの詳細なガイダンスについては、「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー ID プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

### 手順 3: Microsoft Entra ID から SAP Cloud Identity Services へのプロビジョニングを構成する

SAP Cloud Identity Services と統合された SAP BTP や他のアプリケーションへのユーザーのプロビジョニングを準備するには、SAP Cloud Identity Services にそれらのアプリケーションに必要なスキーマ マッピングがあることを確認します。 次に、[Microsoft Entra ID から SAP Cloud Identity Services へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#provision-users-to-sap-cloud-identity-services)を構成します。 SAP Cloud Identity Services はその後、必要に応じてダウンストリーム SAP アプリケーションにユーザーをプロビジョニングします。

Microsoft Entra から SAP Cloud Identity Services にユーザーをプロビジョニングするには、2 つの方法があります。

- SAP BTP クラウドのロールにユーザーを割り当てるなど、Microsoft Entra ID のグループを使用している場合は、SAP Cloud Identity Services プロビジョニングを使用します。 まず、SAP Analytics Cloud で使用される SAP ビジネス ロール用の Microsoft Entra グループを作成します。 次に、SAP Cloud Identity Services のプロビジョニングで、[Microsoft Entra ID をソースとして構成](https://help.sap.com/docs/identity-provisioning/identity-provisioning/microsoft-azure-active-directory)し、ユーザーとグループを Microsoft Entra ID から SAP Cloud Identity Services に取り込み、作成されたグループを SAP ビジネス ロールにマップします。 詳細については、SAP のドキュメントの「[Provision users from Microsoft Azure AD to SAP Cloud Identity Services - Identity Authentication (Microsoft Azure AD から SAP Cloud Identity Services にユーザーをプロビジョニングする - Identity Authentication)](https://blogs.sap.com/2022/02/04/provision-users-from-microsoft-azure-ad-to-sap-cloud-identity-services-identity-authentication/)」を参照してください。
- または、Microsoft Entra ID でグループを使用する必要がない場合は、Microsoft Entra プロビジョニング サービスを使用できます。 このシナリオでは、SAP BTP を表すアプリケーションを作成し、SAP BTP へのアクセスを必要とするユーザーをそのアプリケーションに割り当てます。 次に、[Microsoft Entra ID を使用した、SAP Cloud Identity Services への自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)を構成します。 これらのユーザーが SAP Cloud Identity Services にプロビジョニングされるまで待ち、SAP BTP ターゲットに必要な属性があることを確認します。

注

小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 ユーザーが SAP ダウンストリーム ターゲットで適切なアクセス権を持っていること、サインイン時に適切なロールを持っていることを確認します。

### 手順 4: SAP Cloud Identity Services から SAP BTP へのプロビジョニングを構成する

この手順では、SAP Cloud Identity Services Identity Provisioning を使用して SAP BTP をターゲット システムとして構成します。このシステムでは、ユーザーとグループ メンバーをプロビジョニングできます。 SAP BTP ABAP 環境については、[SAP BTP ABAP 環境へのプロビジョニングに関する SAP ドキュメント](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/target-sap-btp-abap-environment)を参照してください。 SAP BTP XS Advanced UAA (Cloud Foundry) については、[SAP BTP XS Advanced UAA (Cloud Foundry) へのプロビジョニングに関する SAP ドキュメント](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/target-sap-btp-xs-advanced-uaa-cloud-foundry)を参照してください。

### 手順 5: シングル サインオンの構成

SAP アプリケーションのユーザーのプロビジョニングを設定すると、それらのアプリケーションとのシングル サインオンを有効にする必要があります。 Microsoft Entra ID は、SAP アプリケーションの ID プロバイダーと認証機関として機能し、クレームのグループ メンバーシップを提供できます。 [Microsoft Entra シングル サインオン (SSO) と SAP Cloud Identity Services との統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)をまだ構成していない場合は、構成します。

### 手順 6: SAP BTP ロール コレクションにグループを割り当てることでアクセスを管理する

Microsoft Entra ID Governance を使用して SAP BTP アプリケーションへのアクセスを管理し、アプリケーションの BTP ロール コレクション内のロールに関連付けられているグループを設定できます。 詳細については、 [SAP BTP へのアクセスの管理を](https://community.sap.com/t5/technology-blogs-by-members/identity-and-access-management-with-microsoft-entra-part-i-managing-access/ba-p/13873276)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に SAP Cloud Identity Services を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-20
- Summary: ユーザー アカウントを SAP Cloud Identity Services に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事では、Microsoft Entra IDから SAP Cloud Identity Services へのプロビジョニングを構成する手順について説明します。 目標は、ユーザーが SAP Cloud Identity Services に対して認証を行い、他の SAP ワークロードにアクセスできるように、ユーザーを SAP Cloud Identity Services に自動的にプロビジョニングおよびプロビジョニング解除するMicrosoft Entra IDを設定することです。 SAP Cloud Identity Services では、そのローカル ID ディレクトリから他の SAP アプリケーションへ、[ターゲット システム](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-systems)としてのプロビジョニングがサポートされています。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスに組み込まれているコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。 SAP Cloud Identity Services には、Microsoft Entra IDからユーザーとグループを読み取る独自のコネクタもあります。 詳細については、「[SAP Cloud Identity Services - Identity Provisioning - Microsoft Entra ID をソース システムとして](https://help.sap.com/docs/identity-provisioning/identity-provisioning/microsoft-azure-active-directory)」を参照>。

注

SAP Cloud Identity Services コネクタの新しいバージョンが一般公開され、Microsoft Entra アプリ ギャラリーの SAP Cloud Identity Services 一覧の既定値になりました。 このコネクタの現在のバージョンでは、次の変更が行われます。

- SCIM 2.0 標準に更新されました
- SAP Cloud Identity Services へのグループ プロビジョニングとプロビジョニング解除のサポート
- カスタム拡張機能属性のサポート
- [OAuth 2.0 クライアント資格情報の付与の](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-client-creds-grant-flow)サポート

Important

SAP IAG で統合する場合は、ユーザー名行で EDIT を選択して、Microsoft Entra ObjectId をユーザー名にマップし、Source 属性と Target 属性を次のように設定します。

- ソース属性: objectId
- ターゲット属性: userName

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [SAP Cloud Identity Services のテナント](https://www.sap.com/products/cloud-platform.html)
- 管理者権限を持つ SAP Cloud Identity Services のユーザー アカウント。
- グループ プロビジョニング機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。

注

この統合は、米国政府機関向けクラウド環境Microsoft Entraから使用することもできます。 このアプリケーションは、Microsoft Entra US Government Cloud Application Gallery で見つけ、パブリック クラウド環境から行うのと同じ方法で構成できます。

Microsoft Entra IDにユーザーがまだ存在しない場合は、記事「SAPのソースアプリとターゲットアプリを用いたユーザーのプロビジョニングのためのMicrosoft Entra展開計画」から始めてください。 この記事では、SAP SuccessFactors など、組織内のワーカーの一覧の権限のあるソースとMicrosoft Entraを接続する方法について説明します。 また、Microsoft Entraを使用してこれらのワーカーの ID を設定し、SAP ECC や SAP S/4HANA などの 1 つ以上の SAP アプリケーションにサインインできるようにする方法についても説明します。

Microsoft Entra ID ガバナンスを使用して SAP ワークロードへのアクセスを管理する運用環境で SAP Cloud Identity Services へのプロビジョニングを構成する場合は、先に進む前に[Microsoft Entra IDを構成する前に前提条件](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-prepare#prerequisites-before-configuring-microsoft-entra-id-and-microsoft-entra-id-governance-for-identity-governance)を確認してください。

### プロビジョニング用に SAP Cloud Identity Services を設定する

この記事では、SAP Cloud Identity Services に管理システムを追加し、Microsoft Entraを構成します。

[Image: SAP アプリケーション、SAP Cloud Identity Services、および Microsoft Entra の間の SSO とプロビジョニングフローのアーキテクチャのスクリーンショット]

1. SAP Cloud Identity Services 管理コンソール、`https://<tenantID>.accounts.ondemand.com/admin`、または試用版の場合には `https://<tenantID>.trial-accounts.ondemand.com/admin` にサインインします。 **[Users & Authorizations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーと承認) &gt; [Administrators](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者)** に移動します。

    [Image: SAP Cloud Identity Services 管理コンソールのスクリーンショット。]
2. 新しい管理者を一覧に追加するには、左側パネルの **[+ 追加]** ボタンを押します。 **[システムの追加]** を選択し、システムの名前を入力します。

    注

    SAP Cloud Identity Services の管理者 ID の種類は **System** にする必要があります。 管理者ユーザーは、プロビジョニング時に SAP SCIM API に対して認証を行うことができません。 SAP Cloud Identity Services では、システムの作成後にシステムの名前を変更することはできません。
3. [Configure Authorizations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/承認の構成) で、**[Manage Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理) ** のトグル ボタンをオンにします。 次に、[ **保存]** を選択してシステムを作成します。

    [Image: SAP Cloud Identity Services での SCIM の追加のスクリーンショット。]
4. 管理者システムが作成されたら、そのシステムに新しいシークレットを追加します。
5. SAP によって生成された **クライアント ID** と **クライアント シークレット** をコピーします。 これらの値は、[管理者ユーザー名] フィールドと [管理者パスワード] フィールドにそれぞれ入力されます。 次のセクションで設定した SAP Cloud Identity Services アプリケーションの [プロビジョニング] タブに、これらの値を入力します。
6. SAP Cloud Identity Services には、ターゲット システムとして 1 つ以上の SAP アプリケーションへのマッピングが含まれる場合があります。 それらの SAP アプリケーションを SAP Cloud Identity Services を通してプロビジョニングする必要がある属性がユーザーにあるかどうかを確認します。 この記事では、SAP Cloud Identity Services とダウンストリーム ターゲット システムには、 `userName` と `emails[type eq "work"].value`の 2 つの属性が必要であると想定しています。 SAP ターゲット システムに他の属性が必要であり、Microsoft Entra ID ユーザー スキーマに含まれていない場合は、[synching 拡張機能属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning-sync-attributes-for-mapping)を構成する必要があります。

### ギャラリーから SAP Cloud Identity Services を追加する

Microsoft Entra IDを構成して SAP Cloud Identity Services への自動ユーザー プロビジョニングを行う前に、Microsoft Entra アプリケーション ギャラリーからテナントのエンタープライズ アプリケーションの一覧に SAP Cloud Identity Services を追加する必要があります。 この手順は、Microsoft Entra 管理センターで行うか、Graph APIを使用して実行できます。

SAP Cloud Identity Services が SAML を使用してMicrosoft Entraからのシングル サインオン用に既に構成されており、アプリケーションがエンタープライズ アプリケーションのMicrosoft Entra一覧に既に存在する場合は、「SAP Cloud Identity Services への自動ユーザー プロビジョニングの構成」を参照してください。

注

OpenID Connect 統合用にアプリケーション登録を以前に構成している場合、そのアプリケーション登録のプロビジョニングを構成することはできません。 代わりに、プロビジョニング用の別のエンタープライズ アプリケーションを作成します。

#### Microsoft Entra 管理センターを使用した SAP Cloud Identity Services の追加

**Microsoft Entra 管理センターを使用してMicrosoft Entra アプリケーション ギャラリーから SAP Cloud Identity Services を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. アプリをギャラリーから追加するには、検索ボックスに｢**SAP CLoud Identity Services**」と入力します。
4. 結果のパネルから **[SAP Cloud Identity Services]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。
5. 引き続き SAP Cloud Identity Services への自動ユーザー プロビジョニングの構成 に進み、プロビジョニングを構成します。

#### Microsoft Graphを使用した SAP Cloud Identity Services の追加

[「Microsoft Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api) を使用したプロビジョニングの構成」の手順に従って、アプリケーションとサービス プリンシパルを作成できます。

まず、 `SAP Cloud Identity Services`のギャラリー アプリケーション テンプレート識別子を取得します。

```msgraph
GET https://graph.microsoft.com/v1.0/applicationTemplates?$filter=displayName eq 'SAP Cloud Identity Services'
```

応答からアプリケーション テンプレートの `id` を抽出します。 次に、ギャラリー アプリケーションとサービス プリンシパルを作成します。

```msgraph
POST https://graph.microsoft.com/v1.0/applicationTemplates/{applicationTemplateId}/instantiate
Content-type: application/json

{
  "displayName": "SAP Cloud Identity Services"
}
```

応答には、新しいアプリケーション オブジェクトとサービス プリンシパル オブジェクトが含まれます。

次に、先ほど作成したサービス プリンシパルの `id` を使用して、プロビジョニング構成用のテンプレートを取得します。

```msgraph
GET https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/templates
```

プロビジョニングを有効にするには、ジョブを作成する必要があります。 プロビジョニング ジョブを作成するには、次の要求を使用します。 ジョブに使用するテンプレートを指定するときは、前の手順の `id` を `templateId` として使用します。

```msgraph
POST https://graph.microsoft.com/beta/servicePrincipals/{id}/synchronization/jobs
Content-type: application/json

{
    "templateId": "sapcloudidentityservices"
}
```

「SAP Cloud Identity Services への自動ユーザー プロビジョニングの構成」の説明に従って、サービス プリンシパルに関連付けられている[プロビジョニング ジョブとテンプレート スキーマ](https://learn.microsoft.com/ja-jp/graph/api/synchronization-synchronizationschema-update?view=graph-rest-1.0&preserve-view=true)をさらに構成できます。 次に、Microsoft Entra が SAP Cloud Identity Services に対する認証を行うためにアクセスを許可し、その後、プロビジョニングジョブを開始します。

### SAP Cloud Identity Services に対する自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのアプリケーションへのユーザーとグループの割り当てに基づいて SAP Cloud Identity Services のユーザーとグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で自動ユーザー プロビジョニングを構成する

Microsoft Entra IDで SAP Cloud Identity Services の自動ユーザー プロビジョニングを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションのリストで、**SAP Cloud Identity Services** アプリケーションを選択します。

    [Image: アプリケーションの一覧の SAP Cloud Identity Services リンクのスクリーンショット。]
4. **[プロパティ]** タブを選択します。
5. **[割り当てが必要ですか?]** オプションが **[はい]** に設定されていることを確認します。 **[いいえ]** に設定されている場合は、外部 ID を含むディレクトリ内のすべてのユーザーがアプリケーションにアクセスでき、アプリケーションへのアクセスをレビューすることはできません。
6. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
7. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
8. [ **テナント URL** ] フィールドに、SAP Cloud Identity Services テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが SAP Cloud Identity Services に接続できることを確認します。 接続に失敗した場合は、SAP Cloud Identity Services アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
9. [ **作成]** を選択して構成を作成します。
10. [**概要**] ページで **[プロパティ**] を選択します。
11. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
12. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
13. **Attribute Mapping** セクションで、Microsoft Entra IDから SAP Cloud Identity Services に同期されるユーザー属性とグループ属性を確認します。 マッピングのターゲットとして使用できる SAP Cloud Identity Services の属性が表示されない場合は、[ **詳細オプションの表示** ] を選択し、 **SAP Cloud Platform Identity Authentication Service の属性リストの編集** を選択して [、サポートされている属性の一覧を編集します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#editing-the-list-of-supported-attributes)。 SAP Cloud Identity Services テナントの属性を追加します。
14. **Matching** プロパティとして選択したソース属性とターゲット属性を確認して記録します。 **Matching precedence** を持つマッピング。これらの属性は、Microsoft Entra プロビジョニング サービスの SAP Cloud Identity Services のユーザーとグループを照合して、新しいユーザー/グループを作成するか、既存のユーザー/グループを更新するかを決定するために使用されます。 照合に関する詳細については、「[ソース システムとターゲット システムのユーザー照合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#matching-users-in-the-source-and-target--systems)」を参照してください。 その後の手順では、重複するユーザーが作成されないようにするために、SAP Cloud Identity Services に既に存在するすべてのユーザーに[ **照合** プロパティ]が設定された属性が選択されていることを確認します。
15. `IsSoftDeleted` の属性マッピング、または `IsSoftDeleted` を含む関数がアプリケーションの属性にマップされていることを確認します。 ユーザーがアプリケーションから割り当て解除されたり、Microsoft Entra IDで論理的に削除されたり、サインインがブロックされたりすると、Microsoft Entra プロビジョニング サービスによって、`isSoftDeleted` にマップされた属性が更新されます。 マップされた属性がない場合、後でアプリケーション ロールから割り当て解除されたユーザーは、引き続きアプリケーションのデータ ストアに存在します。
16. SAP Cloud Identity Services またはダウンストリームのターゲット SAP システムに必要なその他のマッピングを追加します。
17. **[保存]** ボタンをクリックして変更をコミットします。

    | ユーザー属性 | タイプ | フィルター処理のサポート | SAP Cloud Identity Services に必要 |
    | --- | --- | --- | --- |
    | `userName` | 糸 | ✓ | ✓ |
    | `emails[type eq "work"].value` | 糸 |  | ✓ |
    | `active` | ブール値 |  |  |
    | `displayName` | 糸 |  |  |
    | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager` | リファレンス |  |  |
    | `addresses[type eq "work"].country` | 糸 |  |  |
    | `addresses[type eq "work"].locality` | 糸 |  |  |
    | `addresses[type eq "work"].postalCode` | 糸 |  |  |
    | `addresses[type eq "work"].region` | 糸 |  |  |
    | `addresses[type eq "work"].streetAddress` | 糸 |  |  |
    | `name.givenName` | 糸 |  |  |
    | `name.familyName` | 糸 |  |  |
    | `name.honorificPrefix` | 糸 |  |  |
    | `phoneNumbers[type eq "fax"].value` | 糸 |  |  |
    | `phoneNumbers[type eq "mobile"].value` | 糸 |  |  |
    | `phoneNumbers[type eq "work"].value` | 糸 |  |  |
    | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter` | 糸 |  |  |
    | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department` | 糸 |  |  |
    | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division` | 糸 |  |  |
    | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber` | 糸 |  |  |
    | `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization` | 糸 |  |  |
    | `locale` | 糸 |  |  |
    | `timezone` | 糸 |  |  |
    | `userType` | 糸 |  |  |
    | `company` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute1` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute2` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute3` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute4` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute5` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute6` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute7` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute8` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute9` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:User:attributes:customAttribute10` | 糸 |  |  |
    | `sendMail` | 糸 |  |  |
    | `mailVerified` | 糸 |  |  |

    | グループ属性 | タイプ | フィルター処理のサポート | SAP Cloud Identity Services に必要 |
    | --- | --- | --- | --- |
    | `id` | 糸 | ✓ | ✓ |
    | `externalId` | 糸 |  |  |
    | `displayName` | 糸 |  | ✓ |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:Group:name` | 糸 |  |  |
    | `urn:sap:cloud:scim:schemas:extension:custom:2.0:Group:description` | 糸 |  |  |
    | `members` | リファレンス |  | ✓ |
18. スコープ フィルターを構成するには、「 [ユーザー アカウントをプロビジョニングするための条件付きルールを定義する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)」の手順を参照してください。

    [Image: プロビジョニング設定の保存のスクリーンショット。]
19. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
20. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### Microsoft Entra IDから SAP Cloud Identity Services への新しいテスト ユーザーのプロビジョニング

1 人の新しいMicrosoft Entraテスト ユーザーを SAP Cloud Identity Services に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)およびユーザー管理者としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. 新しいテスト ユーザーの **[ユーザー プリンシパル名]** と **[表示名]** を入力します。 ユーザー プリンシパル名は、現在または以前のMicrosoft Entraユーザーまたは SAP Cloud Identity Services ユーザーと同じではなく、一意である必要があります。 **[確認と作成]**、**[作成]** の順に選択します。
5. テスト ユーザーが作成されたら、**Entra ID**&gt;**Enterprise アプリ**に移動します。
6. SAP Cloud Identity Services アプリケーションを選択します。
7. **[ユーザーとグループ]** を選択し、次に **[ユーザー/グループの追加]** を選択します。
8. **[ユーザーとグループ]** で、**[選択なし]** を選択して、テキスト ボックスにテスト ユーザーのユーザー プリンシパル名を入力します。
9. **[選択]** を選択し、次に **[割り当て]** を選択します。
10. **[プロビジョニング]** を選択し、次に **[オンデマンド プロビジョニング]** を選択します。
11. **[ユーザーまたはグループの選択]** テキスト ボックスに、テスト ユーザーのユーザー プリンシパル名を入力します。
12. **プロビジョン** を選択します。
13. プロビジョニングが完了するまで待ちます。 成功した場合は、メッセージ `Modified attributes (successful)`が表示されます。

また、必要に応じて、ユーザーがアプリケーションのスコープ外になったときに、Microsoft Entra プロビジョニング サービスがプロビジョニングする内容を確認することもできます。

1. ユーザーおよびグループの選択
2. テスト ユーザーを選択し、次に **[削除]** を選択します。
3. テスト ユーザーが削除されたら、**[プロビジョニング]** を選択し、**[オンデマンドのプロビジョニング]** を選択します。
4. **[ユーザーまたはグループ]** テキスト ボックスに、割り当て解除されたばかりのテスト ユーザーのユーザー プリンシパル名を入力します。
5. **プロビジョン** を選択します。
6. プロビジョニングが完了するまで待ちます。

最後に、Microsoft Entra IDからテスト ユーザーを削除できます。

1. **Entra ID**&gt;**Users** に移動します。
2. テスト ユーザーを選択し、**[削除]** を選択し、次に **[OK]** を選択します。 このアクションにより、テスト ユーザーがMicrosoft Entra IDから論理的に削除されます。

その後、SAP Cloud Identity Services からテスト ユーザーを削除することもできます。

### アプリケーション内の既存のユーザーを識別し、エンタープライズ アプリケーションに割り当てる

Microsoft Entraは、アプリケーション内の既存のユーザーを検出し、エンタープライズ アプリケーションへの割り当てを簡素化できます。 プロビジョニングの概要ページの [ [ID の検出](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery) ] ボタンをクリックします。 レポートが生成されると、アプリケーション内のすべてのユーザーのビューが表示されます。アプリケーション内のユーザーは、Microsoft Entra ID ユーザーと一致し、どのユーザーが Microsoft Entra ID のエンタープライズ アプリケーションに既に割り当てられているか、アプリケーション内のどのユーザーがMicrosoft Entra IDユーザーと一致していません。 その後、単純な PowerShell スクリプトを実行して、検出されたユーザーをアプリケーションに割り当てることができます。

1. [Assign-CorrelatedUsers PowerShell スクリプトをダウンロード](https://aka.ms/AssignCorrelatedUsersPowerShell)します。
2. ドライラン モードでスクリプトを実行して、変更を加えずにアプリケーション ロールの割り当てを受け取るユーザーをプレビューします。

    ```powershell
    .\Assign-CorrelatedUsers.ps1 -ServicePrincipalId "7A22..." -DryRun
    ```
3. ドライラン出力を確認した後、 `-DryRun` せずにスクリプトを実行し、一致したユーザーの実際のアプリケーション ロールの割り当てを作成します。

    ```powershell
    .\Assign-CorrelatedUsers.ps1 -ServicePrincipalId "7A22..."
    ```
4. 変更がMicrosoft Entra ID内に反映されるまで 1 分待ちます。

検出機能には、[Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals) ライセンスが必要です。 必要なライセンスを持たない組織でも、以下の手順に従って SAP CLout Identity Services の既存のユーザーを特定し、Microsoft Entraのエンタープライズ アプリケーションに割り当てることができます。

### 既存の SAP Cloud Identity Services ユーザーが必要な合致属性を持っていることを確認する

Microsoft Entra IDの SAP Cloud Identity Services アプリケーションに非テスト ユーザーを割り当てる前に、Microsoft Entra IDのユーザーと同じユーザーを表す SAP Cloud Identity Services に既に存在するすべてのユーザーに、SAP Cloud Identity Services にマッピング属性が設定されていることを確認する必要があります。

プロビジョニング マッピングでは、**Matching** プロパティとして選択された属性が、Microsoft Entra IDのユーザー アカウントと SAP Cloud Identity Services のユーザー アカウントとの照合に使用されます。 SAP Cloud Identity Services に一致しないユーザーがMicrosoft Entra IDに存在する場合、Microsoft Entra プロビジョニング サービスは新しいユーザーの作成を試みます。 Microsoft Entra IDにユーザーが存在し、SAP Cloud Identity Services に一致する場合、Microsoft Entra プロビジョニング サービスはその SAP Cloud Identity Services ユーザーを更新します。 このため、SAP Cloud Identity Services に既に存在するユーザーには、**[照合]** プロパティとして選択されている属性が設定されていることを確認する必要があります。そうしないと、重複するユーザーが作成される可能性があります。 Microsoft Entraアプリケーション属性マッピングで一致する属性を変更する必要がある場合は、「[ソース システムとターゲット システムのユーザーの照合](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes#matching-users-in-the-source-and-target--systems)を参照してください。

1. SAP Cloud Identity Services 管理コンソール、`https://<tenantID>.accounts.ondemand.com/admin`、または試用版の場合には `https://<tenantID>.trial-accounts.ondemand.com/admin` にサインインします。
2. **[ユーザーと認可] &gt; [ユーザーのエクスポート]** に移動します。
3. Microsoft Entraユーザーを SAP のユーザーと照合するために必要なすべての属性を選択します。 これらの属性には、`SCIM ID`、`userName`、`emails`、および識別子として SAP システムで使用できるその他の属性が含まれます。
4. **[エクスポート]** を選択し、ブラウザーが CSV ファイルをダウンロードするまで待ちます。
5. PowerShell ウィンドウを開きます。
6. エディターに次のスクリプトを入力します。 1 行目で、`userName` 以外の照合属性を選択した場合は、`sapScimUserNameField` 変数の値を SAP Cloud Identity Services 属性の名前に変更します。 2 行目で、エクスポートした CSV ファイルのファイル名に引数を `Users-exported-from-sap.csv` からダウンロードしたファイルの名前に変更します。

    ```powershell
    $sapScimUserNameField = "userName"
    $existingSapUsers = import-csv -Path ".\Users-exported-from-sap.csv" -Encoding UTF8
    $count = 0
    $warn = 0
    foreach ($u in $existingSapUsers) {
     $id = $u.id
     if (($null -eq $id) -or ($id.length -eq 0)) {
         write-error "Exported CSV file doesn't contain the ID attribute of SAP Cloud Identity Services users."
         throw "ID attribute not available, re-export"
         return
     }
     $count++
     $userName = $u.$sapScimUserNameField
     if (($null -eq $userName) -or ($userName.length -eq 0)) {
         write-warning "SAP Cloud Identity Services user $id doesn't have a $sapScimUserNameField attribute populated"
         $warn++
     }
    }
    write-output "$warn of $count users in SAP Cloud Identity Services did not have the $sapScimUserNameFIeld attribute populated."
    ```
7. スクリプトを実行します。 スクリプトが完了したら、必要な照合属性がないユーザーが 1 人以上いた場合は、エクスポートされた CSV ファイルまたは SAP Cloud Identity Services 管理コンソールでそれらのユーザーを検索します。 これらのユーザーもMicrosoft Entraに存在する場合は、最初にそれらのユーザーの SAP Cloud Identity Services 表現を更新して、一致する属性が設定されるようにする必要があります。
8. SAP Cloud Identity Services でこれらのユーザーの属性を更新したら、手順 2 から 5 と当セクションの PowerShell 手順で説明されているように SAP Cloud Identity Services からユーザーを再エクスポートし、SAP Cloud Identity Services のユーザーにそれらのユーザーへのプロビジョニングを妨げる照合属性がないことを確認します。

SAP Cloud Identity Services から取得したすべてのユーザーの一覧が作成されたので、アプリケーションのデータ ストアのユーザーを、既にMicrosoft Entra IDユーザーと照合して、プロビジョニングのスコープに含めるユーザーを決定します。

#### Microsoft Entra IDでユーザーの ID を取得する

このセクションでは、[Microsoft Graph PowerShell](https://www.powershellgallery.com/packages/Microsoft.Graph) コマンドレットを使用してMicrosoft Entra IDを操作する方法について説明します。

このシナリオで組織が初めてこれらのコマンドレットを使用するときは、テナントで powerShell Microsoft Graph使用できるようにするには、グローバル管理者ロールである必要があります。 以降の操作では、次のような低い特権のロールを使用できます。

- ユーザー管理者、新しいユーザーの作成が予測される場合。
- アプリケーション管理者または [ID ガバナンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)、アプリケーション ロールの割り当ての管理だけを行う場合。

1. PowerShell を開きます。
2. [Microsoft Graph PowerShell モジュール](https://www.powershellgallery.com/packages/Microsoft.Graph)が既にインストールされていない場合は、次のコマンドを使用して、`Microsoft.Graph.Users` モジュールなどをインストールします。

    ```powershell
    Install-Module Microsoft.Graph
    ```

    これらのモジュールが既にインストールされている場合は、最新バージョンを使用していることを確認します。

    ```powershell
    Update-Module microsoft.graph.users,microsoft.graph.identity.governance,microsoft.graph.applications
    ```
3. Microsoft Entra IDに接続します。

    ```powershell
    $msg = Connect-MgGraph -ContextScope Process -Scopes "User.ReadWrite.All,Application.ReadWrite.All,AppRoleAssignment.ReadWrite.All,EntitlementManagement.ReadWrite.All"
    ```
4. このコマンドを初めて使用する場合は、Microsoft Graphコマンド ライン ツールにこれらのアクセス許可を付与することを許可する必要があります。
5. アプリケーションのデータ ストアから取得したユーザーの一覧を、PowerShell セッションに読み込みます。 ユーザーの一覧の形式が CSV ファイルであった場合は、PowerShell コマンドレット `Import-Csv` を使用し、引数として前のセクションのファイルの名前を指定できます。

    たとえば、SAP Cloud Identity Services から取得したファイル名が *Users-exported-from-sap.csv* で、現在のディレクトリにある場合は、次のコマンドを入力します。

    ```powershell
    $filename = ".\Users-exported-from-sap.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```

    別の例として、データベースまたはディレクトリを使用している場合、ファイル名が *users.csv* で、現在のディレクトリにある場合は、次のコマンドを入力します:

    ```powershell
    $filename = ".\users.csv"
    $dbusers = Import-Csv -Path $filename -Encoding UTF8
    ```
6. Microsoft Entra IDのユーザーの属性と一致する*users.csv* ファイルの列を選択します。

    SAP Cloud Identity Services を使用している場合、既定のマッピングは、Microsoft Entra ID属性 `userName` を持つ SAP SCIM 属性 `userPrincipalName` です。

    ```powershell
    $db_match_column_name = "userName"
    $azuread_match_attr_name = "userPrincipalName"
    ```

    別の例として、データベースまたはディレクトリを使用している場合、`EMail` という名前の列の値が Microsoft Entra 属性 `userPrincipalName` と同じ値であるデータベース内のユーザーがいる場合があります。

    ```powershell
    $db_match_column_name = "EMail"
    $azuread_match_attr_name = "userPrincipalName"
    ```
7. Microsoft Entra IDでこれらのユーザーの ID を取得します。

    次の PowerShell スクリプトでは、前に指定された `$dbusers`、`$db_match_column_name`、`$azuread_match_attr_name` の各値を使用します。 Microsoft Entra IDクエリを実行して、ソース ファイル内の各レコードに一致する値を持つ属性を持つユーザーを検索します。 ソース SAP Cloud Identity Services、データベース、またはディレクトリから取得したファイルに多数のユーザーが存在する場合、このスクリプトが完了するまでに数分かかる場合があります。 Microsoft Entra IDに値を持つ属性がなく、`contains` またはその他のフィルター式を使用する必要がある場合は、このスクリプトをカスタマイズし、次の手順 11 で別のフィルター式を使用する必要があります。

    ```powershell
    $dbu_not_queried_list = @()
    $dbu_not_matched_list = @()
    $dbu_match_ambiguous_list = @()
    $dbu_query_failed_list = @()
    $azuread_match_id_list = @()
    $azuread_not_enabled_list = @()
    $dbu_values = @()
    $dbu_duplicate_list = @()
    
    foreach ($dbu in $dbusers) { 
       if ($null -ne $dbu.$db_match_column_name -and $dbu.$db_match_column_name.Length -gt 0) { 
          $val = $dbu.$db_match_column_name
          $escval = $val -replace "'","''"
          if ($dbu_values -contains $escval) { $dbu_duplicate_list += $dbu; continue } else { $dbu_values += $escval }
          $filter = $azuread_match_attr_name + " eq '" + $escval + "'"
          try {
             $ul = @(Get-MgUser -Filter $filter -All -Property Id,accountEnabled -ErrorAction Stop)
             if ($ul.length -eq 0) { $dbu_not_matched_list += $dbu; } elseif ($ul.length -gt 1) {$dbu_match_ambiguous_list += $dbu } else {
                $id = $ul[0].id; 
                $azuread_match_id_list += $id;
                if ($ul[0].accountEnabled -eq $false) {$azuread_not_enabled_list += $id }
             } 
          } catch { $dbu_query_failed_list += $dbu } 
        } else { $dbu_not_queried_list += $dbu }
    }
    
    ```
8. 前のクエリの結果を表示します。 エラーまたは一致が見つからないために、SAP Cloud Identity Services のユーザー、データベース、またはディレクトリがMicrosoft Entra IDに配置できなかったかどうかを確認します。

    次の PowerShell スクリプトでは、見つからなかったレコードの数を表示します。

    ```powershell
    $dbu_not_queried_count = $dbu_not_queried_list.Count
    if ($dbu_not_queried_count -ne 0) {
      Write-Error "Unable to query for $dbu_not_queried_count records as rows lacked values for $db_match_column_name."
    }
    $dbu_duplicate_count = $dbu_duplicate_list.Count
    if ($dbu_duplicate_count -ne 0) {
      Write-Error "Unable to locate Microsoft Entra ID users for $dbu_duplicate_count rows as multiple rows have the same value"
    }
    $dbu_not_matched_count = $dbu_not_matched_list.Count
    if ($dbu_not_matched_count -ne 0) {
      Write-Error "Unable to locate $dbu_not_matched_count records in Microsoft Entra ID by querying for $db_match_column_name values in $azuread_match_attr_name."
    }
    $dbu_match_ambiguous_count = $dbu_match_ambiguous_list.Count
    if ($dbu_match_ambiguous_count -ne 0) {
      Write-Error "Unable to locate $dbu_match_ambiguous_count records in Microsoft Entra ID as attribute match ambiguous."
    }
    $dbu_query_failed_count = $dbu_query_failed_list.Count
    if ($dbu_query_failed_count -ne 0) {
      Write-Error "Unable to locate $dbu_query_failed_count records in Microsoft Entra ID as queries returned errors."
    }
    $azuread_not_enabled_count = $azuread_not_enabled_list.Count
    if ($azuread_not_enabled_count -ne 0) {
     Write-Error "$azuread_not_enabled_count users in Microsoft Entra ID are blocked from sign-in."
    }
    if ($dbu_not_queried_count -ne 0 -or $dbu_duplicate_count -ne 0 -or $dbu_not_matched_count -ne 0 -or $dbu_match_ambiguous_count -ne 0 -or $dbu_query_failed_count -ne 0 -or $azuread_not_enabled_count) {
     Write-Output "You will need to resolve those issues before access of all existing users can be reviewed."
    }
    $azuread_match_count = $azuread_match_id_list.Count
    Write-Output "Users corresponding to $azuread_match_count records were located in Microsoft Entra ID." 
    ```
9. スクリプトが完了すると、データ ソースのレコードがMicrosoft Entra IDに存在しなかった場合にエラーが表示されます。 アプリケーションのデータ ストアのユーザーのすべてのレコードをMicrosoft Entra IDのユーザーとして配置できない場合は、一致しなかったレコードとその理由を調査する必要があります。

    たとえば、アプリケーションのデータ ソースで対応する `mail` プロパティが更新されずに、他のユーザーの電子メール アドレスと userPrincipalName がMicrosoft Entra IDで変更されている可能性があります。 または、ユーザーは既に組織を離れているが、まだアプリケーションのデータ ソースに存在する可能性があります。 または、アプリケーションのデータ ソースにベンダーまたはスーパー管理者アカウントがあり、Microsoft Entra IDの特定のユーザーに対応していない場合があります。
10. Microsoft Entra IDに見つからないか、アクティブでサインインできないユーザーがいる場合で、SAP Cloud Identity Services、データベース、またはディレクトリで彼らのアクセス権を確認したり、属性を更新したりしたい場合は、アプリケーションや照合ルールを更新するか、Microsoft Entraでユーザーを更新または作成する必要があります。 変更を加えるための詳細については、「Microsoft Entra ID に一致しなかったアプリケーションの管理マッピングとユーザーアカウント」を参照してください。

    Microsoft Entra IDでユーザーを作成するオプションを選択した場合は、次のいずれかを使用してユーザーを一括で作成できます。

    - Microsoft Entra 管理センターで大量のユーザーを作成する方法については、[CSV ファイル](https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-add) を参照してください。
    - [New-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/new-mguser?view=graph-powershell-1.0#examples&preserve-view=true) コマンドレット

    これらの新しいユーザーには、Microsoft Entra IDが後でアプリケーション内の既存のユーザーと一致するために必要な属性と、`userPrincipalName`、`mailNickname`、`displayName` など、Microsoft Entra IDで必要な属性が設定されていることを確認します。 `userPrincipalName` は、ディレクトリ内のすべてのユーザー間で一意である必要があります。

    たとえば、`EMail` という名前の列の値が Microsoft Entra ユーザー プリンシパル名として使用する値、`Alias` 列の値にMicrosoft Entra IDメール のニックネームが含まれ、`Full name` 列の値にユーザーの表示名が含まれているデータベースにユーザーがいる場合があります。

    ```powershell
    $db_display_name_column_name = "Full name"
    $db_user_principal_name_column_name = "Email"
    $db_mail_nickname_column_name = "Alias"
    ```

    その後、このスクリプトを使用して、SAP Cloud Identity Services、データベース、またはディレクトリ内でMicrosoft Entra IDのユーザーと一致しないユーザーに対し、Microsoft Entraのユーザーを作成できます。 このスクリプトを変更して、組織で必要なMicrosoft Entra属性を追加する必要がある場合や、`$azuread_match_attr_name` が `mailNickname` でも`userPrincipalName`でもない場合は、Microsoft Entra属性を指定する必要があります。

    ```powershell
    $dbu_missing_columns_list = @()
    $dbu_creation_failed_list = @()
    foreach ($dbu in $dbu_not_matched_list) {
       if (($null -ne $dbu.$db_display_name_column_name -and $dbu.$db_display_name_column_name.Length -gt 0) -and
           ($null -ne $dbu.$db_user_principal_name_column_name -and $dbu.$db_user_principal_name_column_name.Length -gt 0) -and
           ($null -ne $dbu.$db_mail_nickname_column_name -and $dbu.$db_mail_nickname_column_name.Length -gt 0)) {
          $params = @{
             accountEnabled = $false
             displayName = $dbu.$db_display_name_column_name
             mailNickname = $dbu.$db_mail_nickname_column_name
             userPrincipalName = $dbu.$db_user_principal_name_column_name
             passwordProfile = @{
               Password = -join (((48..90) + (96..122)) * 16 | Get-Random -Count 16 | % {[char]$_})
             }
          }
          try {
            New-MgUser -BodyParameter $params
          } catch { $dbu_creation_failed_list += $dbu; throw }
       } else {
          $dbu_missing_columns_list += $dbu
       }
    }
    ```
11. 不足しているユーザーをMicrosoft Entra IDに追加したら、手順 7 のスクリプトをもう一度実行します。 次に、手順 8 のスクリプトを実行します。 エラーが報告されていないことを確認します。

    ```powershell
    $dbu_not_queried_list = @()
    $dbu_not_matched_list = @()
    $dbu_match_ambiguous_list = @()
    $dbu_query_failed_list = @()
    $azuread_match_id_list = @()
    $azuread_not_enabled_list = @()
    $dbu_values = @()
    $dbu_duplicate_list = @()
    
    foreach ($dbu in $dbusers) { 
       if ($null -ne $dbu.$db_match_column_name -and $dbu.$db_match_column_name.Length -gt 0) { 
          $val = $dbu.$db_match_column_name
          $escval = $val -replace "'","''"
          if ($dbu_values -contains $escval) { $dbu_duplicate_list += $dbu; continue } else { $dbu_values += $escval }
          $filter = $azuread_match_attr_name + " eq '" + $escval + "'"
          try {
             $ul = @(Get-MgUser -Filter $filter -All -Property Id,accountEnabled -ErrorAction Stop)
             if ($ul.length -eq 0) { $dbu_not_matched_list += $dbu; } elseif ($ul.length -gt 1) {$dbu_match_ambiguous_list += $dbu } else {
                $id = $ul[0].id; 
                $azuread_match_id_list += $id;
                if ($ul[0].accountEnabled -eq $false) {$azuread_not_enabled_list += $id }
             } 
          } catch { $dbu_query_failed_list += $dbu } 
        } else { $dbu_not_queried_list += $dbu }
    }
    
    $dbu_not_queried_count = $dbu_not_queried_list.Count
    if ($dbu_not_queried_count -ne 0) {
      Write-Error "Unable to query for $dbu_not_queried_count records as rows lacked values for $db_match_column_name."
    }
    $dbu_duplicate_count = $dbu_duplicate_list.Count
    if ($dbu_duplicate_count -ne 0) {
      Write-Error "Unable to locate Microsoft Entra ID users for $dbu_duplicate_count rows as multiple rows have the same value"
    }
    $dbu_not_matched_count = $dbu_not_matched_list.Count
    if ($dbu_not_matched_count -ne 0) {
      Write-Error "Unable to locate $dbu_not_matched_count records in Microsoft Entra ID by querying for $db_match_column_name values in $azuread_match_attr_name."
    }
    $dbu_match_ambiguous_count = $dbu_match_ambiguous_list.Count
    if ($dbu_match_ambiguous_count -ne 0) {
      Write-Error "Unable to locate $dbu_match_ambiguous_count records in Microsoft Entra ID as attribute match ambiguous."
    }
    $dbu_query_failed_count = $dbu_query_failed_list.Count
    if ($dbu_query_failed_count -ne 0) {
      Write-Error "Unable to locate $dbu_query_failed_count records in Microsoft Entra ID as queries returned errors."
    }
    $azuread_not_enabled_count = $azuread_not_enabled_list.Count
    if ($azuread_not_enabled_count -ne 0) {
     Write-Warning "$azuread_not_enabled_count users in Microsoft Entra ID are blocked from sign-in."
    }
    if ($dbu_not_queried_count -ne 0 -or $dbu_duplicate_count -ne 0 -or $dbu_not_matched_count -ne 0 -or $dbu_match_ambiguous_count -ne 0 -or $dbu_query_failed_count -ne 0 -or $azuread_not_enabled_count -ne 0) {
     Write-Output "You will need to resolve those issues before access of all existing users can be reviewed."
    }
    $azuread_match_count = $azuread_match_id_list.Count
    Write-Output "Users corresponding to $azuread_match_count records were located in Microsoft Entra ID." 
    ```

### 既存のMicrosoft Entra ユーザーが必要な属性を持っていることを確認する

自動ユーザー プロビジョニングを有効にする前に、sap Cloud Identity Services にアクセスする必要Microsoft Entra IDユーザーを決定する必要があります。その後、それらのユーザーがMicrosoft Entra IDで必要な属性を持ち、それらの属性が SAP Cloud Identity Services の予想されるスキーマにマップされていることを確認する必要があります。

- 既定では、Microsoft Entra ユーザー `userPrincipalName` 属性の値は、SAP Cloud Identity Services の `userName` 属性と `emails[type eq "work"].value` 属性の両方にマップされます。 ユーザーのメール アドレスがユーザー プリンシパル名と異なる場合は、このマッピングを変更する必要があります。
- 会社の郵便番号の形式が会社の国または地域と一致しない場合、SAP Cloud Identity Services では、 `postalCode` 属性の値が無視されることがあります。
- 既定では、Microsoft Entra属性 `country` は SAP Cloud Identity Services `addresses[type eq "work"].country` フィールドにマップされます。 `country`属性の値が 2 文字の ISO 3166 国コードでない場合、SAP Cloud Identity Services でそれらのユーザーを作成できない可能性があります。 詳細については、「 [countries.properties](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/change-master-data-texts-rest-api#countries-properties)」を参照してください。
- 既定では、Microsoft Entra属性 `department` は SAP Cloud Identity Services `urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department` 属性にマップされます。 Microsoft Entraユーザーが `department` 属性の値を持っている場合、それらの値は SAP Cloud Identity Services で既に構成されている部門と一致する必要があります。そうしないと、ユーザーの作成または更新が失敗します。 詳細については、 [departments.properties](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/change-master-data-texts-rest-api#departments-properties) を参照してください。 Microsoft Entraユーザーの`department`値が SAP 環境内の値と一致しない場合は、ユーザーを割り当てる前に、Microsoft Entraの部門の値を更新するか、SAP Cloud Identity Services で許可されている部門の値を更新するか、マッピングを削除します。
- SAP Cloud Identity Services の SCIM エンドポイントでは、いくつかの属性を特定の形式にする必要があります。 これらの属性とその特定の形式の詳細については、 [SAP Cloud Identity Services SCIM API 属性の詳細を参照してください](https://help.sap.com/viewer/6d6d63354d1242d185ab4830fc04feb1/Cloud/en-US/b10fc6a9a37c488a82ce7489b1fab64c.html#)。

### Microsoft Entra IDで SAP Cloud Identity Services アプリケーションにユーザーを割り当てる

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストで、**Scope** の設定値が **割り当てられたユーザーとグループのみを同期する**の場合、Microsoft Entra IDでそのアプリケーションのアプリケーション ロールに割り当てられているユーザーとグループのみが SAP Cloud Identity Services と同期されます。 SAP Cloud Identity Services にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。 現在、SAP Cloud Identity Services で使用可能なロールは **[ユーザー]** のみです。

アプリケーションに対してプロビジョニングが既に有効になっている場合は、アプリケーションにさらにユーザーを割り当てる前に、アプリケーション プロビジョニングが [検疫](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) されていないことを確認します。 先に進む前に、検疫の原因となっている問題をすべて解決します。

#### SAP Cloud Identity Services に存在し、Microsoft Entra IDでアプリケーションにまだ割り当てられていないユーザーを確認します

前の手順では、SAP Cloud Identity Services のユーザーも Microsoft Entra ID のユーザーとして存在するかどうかを評価しました。 ただし、Microsoft Entra ID 内のすべてのユーザーがアプリケーションのロールに現在割り当てられているとは限りません。 そこで、次のステップでは、アプリケーション ロールに割り当てられていないユーザーがいないかを確認します。

1. PowerShell を使用して、アプリケーションのサービスプリンシパルIDを検索します。

    たとえば、エンタープライズ アプリケーションの名前が `SAP Cloud Identity Services` である場合は、次のコマンドを入力します。

    ```powershell
    $azuread_app_name = "SAP Cloud Identity Services"
    $azuread_sp_filter = "displayName eq '" + ($azuread_app_name -replace "'","''") + "'"
    $azuread_sp = Get-MgServicePrincipal -Filter $azuread_sp_filter -All
    ```
2. Microsoft Entra IDで現在アプリケーションに割り当てられているユーザーを取得します。

    これは前のコマンドで設定した `$azuread_sp` 変数に基づいています。

    ```powershell
    $azuread_existing_assignments = @(Get-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $azuread_sp.Id -All)
    ```
3. SAP Cloud Identity Services と Microsoft Entra ID の両方に既に存在するユーザーのユーザー ID の一覧を、Microsoft Entra IDのアプリケーションに現在割り当てられているユーザーと比較します。 このスクリプトは、前のセクションで設定した `$azuread_match_id_list` 変数に基づいてビルドされています:

    ```powershell
    $azuread_not_in_role_list = @()
    foreach ($id in $azuread_match_id_list) {
       $found = $false
       foreach ($existing in $azuread_existing_assignments) {
          if ($existing.principalId -eq $id) {
             $found = $true; break;
          }
       }
       if ($found -eq $false) { $azuread_not_in_role_list += $id }
    }
    $azuread_not_in_role_count = $azuread_not_in_role_list.Count
    Write-Output "$azuread_not_in_role_count users in the application's data store aren't assigned to the application roles."
    ```

    0 人の*ユーザーがアプリケーション* ロールに割り当てられていない場合、すべてのユーザー*が*アプリケーション ロールに割り当てられていることを示します。その結果、Microsoft Entra IDと SAP Cloud Identity Services 間で共通するユーザーがいなかったため、変更は必要ありません。 ただし、SAP Cloud Identity Services に既に存在する 1 人以上のユーザーが現在アプリケーション ロールに割り当てられていない場合は、手順を続行し、アプリケーションのロールのいずれかに追加する必要があります。
4. 一致したユーザーを適切なロールに割り当てることができるように、サービス プリンシパルから `User` アプリ ロール ID を取得します。

    ```powershell
    $azuread_app_role_name = "User"
    $azuread_app_role_id = ($azuread_sp.AppRoles | where-object {$_.AllowedMemberTypes -contains "User" -and $_.DisplayName -eq "User"}).Id
    if ($null -eq $azuread_app_role_id) { write-error "role $azuread_app_role_name not located in application manifest"}
    ```
5. SAP Cloud Identity Services と Microsoft Entra に既に存在し、現在アプリケーションにロールの割り当てがないユーザーに対して、アプリケーション ロールの割り当てを作成します。

    ```powershell
    foreach ($u in $azuread_not_in_role_list) {
       $res = New-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $azuread_sp.Id -AppRoleId $azuread_app_role_id -PrincipalId $u -ResourceId $azuread_sp.Id
    }
    ```
6. 変更がMicrosoft Entra ID内に反映されるまで 1 分待ちます。
7. 次のMicrosoft Entraプロビジョニング サイクルでは、Microsoft Entra プロビジョニング サービスによって、アプリケーションに割り当てられているユーザーの表現と SAP Cloud Identity Services の表現が比較され、SAP Cloud Identity Services ユーザーがMicrosoft Entra IDの属性を持つよう更新されます。

#### 残りのユーザーを割り当てて初期同期を監視する

テストが完了し、ユーザーが SAP Cloud Identity Services に正常にプロビジョニングされ、既存の SAP Cloud Identity Services ユーザーがアプリケーション ロールに割り当てられると、ここの手順のいずれかに従って、追加の許可されているユーザーを SAP Cloud Identity Services アプリケーションに割り当てることができます:

- Microsoft Entra 管理センター で[個々のユーザーをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。
- 前のセクションに示すように、PowerShell コマンドレット `New-MgServicePrincipalAppRoleAssignedTo` を使用して個々のユーザーをアプリケーションに割り当てることができます。または、
- 組織がMicrosoft Entra ID ガバナンスのライセンスを持っている場合は、アクセスの割り当てを自動化するためのエンタイトルメント管理ポリシーを[展開することもできます](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-deploy#deploy-entitlement-management-policies-for-automating-access-assignment)。

ユーザーがアプリケーション ロールに割り当てられ、プロビジョニングのスコープに入ると、Microsoft Entra プロビジョニング サービスによって SAP Cloud Identity Services にプロビジョニングされます。 初期同期は後続の同期よりも実行に時間がかかることに注意してください。これは、Microsoft Entra プロビジョニング サービスが実行されている限り、約 40 分ごとに発生します。

ユーザーがプロビジョニングされているのが表示されない場合は、[ユーザーがプロビジョニングされない問題に関するトラブルシューティング ガイド](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned)の手順を確認します。 次に、Microsoft Entra API または [Graph API](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)[のプロビジョニング ログでプロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#monitor-provisioning-events-using-the-provisioning-logs)を確認します。 ログを状態 **[失敗]** でフィルター処理します。 **DuplicateTargetEntries** の ErrorCode でエラーが発生した場合、これはプロビジョニングの一致ルールのあいまいさを示し、各Microsoft Entra ユーザーが 1 人のアプリケーション ユーザーと一致するように、照合に使用されるMicrosoft Entraユーザーまたはマッピングを更新する必要があります。 次に、ログをアクション **[作成]** と状態 **[スキップ済み]** でフィルター処理します。 ユーザーが**NotEffectivelyEntitled**のSkipReasonコードでスキップされた場合、これはMicrosoft Entra IDのユーザーアカウントが一致しなかったことを示している可能性があります。なぜなら、ユーザーアカウントの状態が**Disabled**であったからです。

### シングル サインオンの構成

SAP Cloud Identity Services のシングル サインオン チュートリアルで説明されている手順に従って、 [SAP Cloud Identity Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial) に対して SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

### プロビジョニングを監視する

**Synchronization Details** セクションを使用すると、進行状況を監視し、リンクをクリックしてプロビジョニング アクティビティ レポートを取得できます。このレポートには、MICROSOFT ENTRA プロビジョニング サービスによって SAP Cloud Identity Services に対して実行されたすべてのアクションが記載されています。 また、Microsoft [Graph API](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-configuration-api#monitor-the-provisioning-job-status) を使用してプロビジョニング プロジェクトを監視することもできます。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「[自動ユーザー アカウント プロビジョニングに関するレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

### アプリケーション ロールの割り当てを維持する

アプリケーションに割り当てられているユーザーがMicrosoft Entra IDで更新されると、それらの変更は SAP Cloud Identity Services に自動的にプロビジョニングされます。

Microsoft Entra ID ガバナンスがある場合は、Microsoft Entra IDの SAP Cloud Identity Services のアプリケーション ロールの割り当ての変更を自動化し、ユーザーが組織に参加するときに割り当てを追加または削除したり、ロールを離れたり変更したりできます。

- [アプリケーション ロールの割り当ての 1 回限りの、または定期的なアクセス レビューを実行できます](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-application-preparation)。
- [このアプリケーション用のエンタイトルメント管理アクセス パッケージを作成する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-create-app)ことができます。 ユーザーがアクセス権を割り当てるためのポリシーは、要求時、 [管理者が直接割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-assignments#directly-assign-an-identity)場合、 [自動割り当てポリシーを使用する場合](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-access-package-auto-assignment-policy)、または [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios#administrator-assign-employees-access-from-lifecycle-workflows)を通じて行うことができます。

### SAP Cloud Identity Services SCIM 2.0 エンドポイントを使用するように SAP Cloud Identity Services アプリケーションを更新する

2025 年 9 月Microsoft、SAP Cloud Identity Services の SCIM 2.0 コネクタがリリースされました。このコネクタでは、SAP Cloud Identity Services へのグループ プロビジョニングとプロビジョニング解除、カスタム拡張機能属性、OAuth 2.0 クライアント資格情報の付与のサポートが追加されました。

以下の手順を完了すると、SAP Cloud Identity Services コネクタを既に使用していたお客様は、SCIM 1.0 エンドポイントから SCIM 2.0 エンドポイントに切り替えることができます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID &gt; Enterprise Apps &gt; SAP Cloud Identity Services** に移動します。
3. [ **プロパティ** ] セクションで、オブジェクト ID をコピーします。

    [Image: SAP Cloud Identity Services コネクタの [プロパティ] ブレードでオブジェクト ID をコピーする場所のスクリーンショット。]
4. 新しい Web ブラウザー ウィンドウ[で、Microsoft Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)に移動し、アプリが追加されたMicrosoft Entra テナントの管理者としてサインインします。
5. 使用されているアカウントに正しいアクセス許可があることを確認します。 この変更を行うには、アクセス許可 "Directory.ReadWrite.All" が必要です。

    [Image: 管理者が Directory.ReadWrite.All アクセス許可に同意するオプションを選択している Graph エクスプローラーの [アクセス許可] 画面のスクリーンショット。]
6. 以前にアプリから選択したオブジェクト ID を使用して、サービス プリンシパルの既存の同期ジョブを一覧表示します。

```http
GET https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs/
```

1. 前の例の `GET` 要求の応答本文から "ID" 値を取得し、SCIM 2.0 テンプレートを使用して再作成できるように、既存の同期ジョブを削除します。 "[job-id]" を、 `GET` 要求の ID 値に置き換えます。 この値の形式は "sapcloudidentityservices.xxxxxxxxxxxxxxx.xxxxxxxxxxxxxxx" である必要があります。

```http
DELETE https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs/[job-id]
```

1. Microsoft Graph エクスプローラーで、SAP Cloud Identity Services SCIM 2.0 テンプレートを使用して新しいプロビジョニング ジョブを作成します。 "[object-id]" を、3 番目の手順からコピーしたサービス プリンシパル ID (オブジェクト ID) に置き換えます。

```http
POST https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs { "templateId": "sapcloudidentityservices" }
```

1. 最初の Web ブラウザー ウィンドウに戻り、アプリケーションの [プロビジョニング] タブ **を** 選択します。 構成がリセットされます。 ジョブ ID が "sapcloudidentityservices" で始まれば、アップグレードが成功したことを確認できます。
2. **[管理者資格情報**] セクションのテナント URL を次のように更新します。`https://<tenantID>.accounts.ondemand.com/scim`、試用版の場合は`https://<tenantid>.trial-accounts.ondemand.com/service/scim`。
3. アプリケーションに対して行った以前の変更 (認証の詳細、スコープ フィルター、カスタム属性マッピング) を復元し、プロビジョニングを再度有効にします。

注

前の設定を復元できないと、SAP Cloud Identity Services で属性 (name.formatted など) が予期せず更新される可能性があります。 プロビジョニングを有効にする前に、必ず構成を確認してください。

### 変更ログ

このコネクタには、次の変更が加えられます。

- 2025 年 9 月 30 日 – SCIM 2.0 エンドポイントを使用する SAP Cloud Identity Services コネクタの新しいバージョンを一般公開にリリース。 新しいバージョンでは、SAP Cloud Identity Services へのグループ プロビジョニングとプロビジョニング解除、カスタム拡張機能属性、OAuth 2.0 クライアント資格情報の付与がサポートされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-concur-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して SAP Concur へのユーザー プロビジョニングを自動化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-concur-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-10-01
- Summary: SAP Cloud Identity Services を使用して、Microsoft Entra ID から SAP Concur へのユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に行う方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SAP Cloud Identity Services と Microsoft Entra ID で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスと SAP Cloud Identity Services を使用して、SAP Concur に対するユーザーのプロビジョニングとプロビジョニング解除が自動的に行われます。 Microsoft Entra プロビジョニングが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされている機能

- SAP Concur でユーザーを作成して SAP Concur へのシングル サインオンを有効にする
- アクセスが不要になった場合に SAP Concur のユーザーを削除する
- Microsoft Entra ID と SAP Concur の間でユーザー属性の同期を維持する

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP Concur
- SAP Cloud Identity Services のテナント
- 管理者アクセス許可がある SAP Identity Provisioning の管理コンソールのユーザー アカウント。 Identity Provisioning 管理コンソールのプロキシ システムにアクセスできることを確認します。 **[プロキシ システム]** タイルが表示されない場合は、このタイルへのアクセスを要求するコンポーネント **BC-IAM-IPS** のインシデントを作成します。

### 手順 1:プロビジョニングのデプロイを計画する

Microsoft Entra には、SAP ECC、SAP Cloud Identity Services、SAP SuccessFactors へのコネクタがあります。 SAP Concur またはその他のアプリケーションへのプロビジョニングでは、ユーザーはまず Microsoft Entra ID に存在する必要があります。 Microsoft Entra ID にユーザーを作成すると、それらのユーザーを Microsoft Entra ID から SAP Cloud Identity Services にプロビジョニングできます。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内の Microsoft Entra ID から送信されたユーザーを、[`SAP Concur`](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/target-sap-concur) などのダウンストリーム SAP アプリケーションにプロビジョニングします。

[Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

### 手順 2: Microsoft Entra ID に適切なユーザーが存在することを確認する

その後、SuccessFactors からの HR 受信を使用して、従業員の参加、移動、離脱時に Microsoft Entra ID のユーザーの一覧を最新の状態に保つことができます。 グループまたはアプリケーション ロールの割り当てを使用して、SAP Concur にアクセスできるユーザーまたは SAP Concur にアクセスできるロールのスコープを設定する予定で、テナントに Microsoft Entra ID ガバナンスのライセンスがある場合は SAP Cloud Identity Services または SAP Concur を表すアプリケーションの Microsoft Entra ID で、[アプリケーション ロールの割り当てに対する変更を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#assign-users-the-necessary-application-access-rights-in-microsoft-entra)することもできます。 プロビジョニング前に職務の分離やその他のコンプライアンス チェックを実行する方法の詳細については、「[アクセス ライフサイクル管理シナリオの移行](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/migrate-from-sap-idm#migrate-access-lifecycle-management-scenarios)」を参照してください。

SAP アプリケーションをターゲットとする ID ライフサイクルの詳細なガイダンスについては、「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー ID プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

### 手順 3: Microsoft Entra ID から SAP Cloud Identity Services へのプロビジョニングを構成する

SAP Cloud Identity Services と統合された SAP Concur や他のアプリケーションへのユーザーのプロビジョニングを準備するには、SAP Cloud Identity Services にそれらのアプリケーションに必要なスキーマ マッピングがあることを確認します。 次に、[Microsoft Entra ID から SAP Cloud Identity Services へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#provision-users-to-sap-cloud-identity-services)を構成します。 SAP Cloud Identity Services はその後、必要に応じてダウンストリーム SAP アプリケーションにユーザーをプロビジョニングします。

Microsoft Entra から SAP Cloud Identity Services にユーザーをプロビジョニングするには、2 つの方法があります。

- SAP Concur クラウドのロールにユーザーを割り当てるなど、Microsoft Entra ID のグループを使用している場合は、SAP Cloud Identity Services プロビジョニングを使用します。 まず、SAP Analytics Cloud で使用される SAP ビジネス ロール用の Microsoft Entra グループを作成します。 次に、SAP Cloud Identity Services のプロビジョニングで、[Microsoft Entra ID をソースとして構成](https://help.sap.com/docs/identity-provisioning/identity-provisioning/microsoft-azure-active-directory)し、ユーザーとグループを Microsoft Entra ID から SAP Cloud Identity Services に取り込み、作成されたグループを SAP ビジネス ロールにマップします。 詳細については、SAP のドキュメントの「[Provision users from Microsoft Azure AD to SAP Cloud Identity Services - Identity Authentication (Microsoft Azure AD から SAP Cloud Identity Services にユーザーをプロビジョニングする - Identity Authentication)](https://blogs.sap.com/2022/02/04/provision-users-from-microsoft-azure-ad-to-sap-cloud-identity-services-identity-authentication/)」を参照してください。
- または、Microsoft Entra ID でグループを使用する必要がない場合は、Microsoft Entra プロビジョニング サービスを使用できます。 このシナリオでは、SAP Concur を表すアプリケーションを作成し、SAP Concur へのアクセスを必要とするユーザーをそのアプリケーションに割り当てます。 次に、[Microsoft Entra ID を使用した、SAP Cloud Identity Services への自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)を構成します。 これらのユーザーが SAP Cloud Identity Services にプロビジョニングされるのを待ち、SAP Concur ターゲットに必要な属性があることを確認します。

Note

小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 ユーザーが SAP ダウンストリーム ターゲットで適切なアクセス権を持っていること、サインイン時に適切なロールを持っていることを確認します。

### 手順 4: SAP Cloud Identity Services から SAP Concur へのプロビジョニングを構成する

この手順では、SAP Cloud Identity Services Identity Provisioning を使用して SAP Concur をターゲット システムとして構成します。このシステムでは、ユーザーとグループ メンバーをプロビジョニングできます。 SAP Concur については、[SAP Concur へのプロビジョニングに関する SAP ドキュメント](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/target-sap-concur)を参照してください。

### 手順 5: シングル サインオンの構成

SAP アプリケーションのユーザーのプロビジョニングを設定すると、それらのアプリケーションとのシングル サインオンを有効にする必要があります。 Microsoft Entra ID は、SAP アプリケーションの ID プロバイダーおよび認証機関として機能することができます。 [Microsoft Entra シングル サインオン (SSO) と SAP Cloud Identity Services の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)をまだ構成していない場合は、構成します。

SAP SaaS や最新のアプリにシングル サインオンを構成する方法の詳細は、「[SSO の有効化](https://learn.microsoft.com/ja-jp/entra/id-governance/sap#enable-sso)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-customer-cloud-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に SAP Cloud for Customer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-customer-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAP Cloud for Customer の間でシングル サインオンを構成する方法について説明します。

この記事では、SAP Cloud for Customer と Microsoft Entra ID を統合する方法について説明します。 SAP Cloud for Customer と Microsoft Entra ID を統合すると、次のことができます。

- SAP Cloud for Customer にアクセスする Microsoft Entra ID ユーザーを制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SAP Cloud for Customer に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP Cloud for Customer でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SAP Cloud for Customer では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから SAP Cloud for Customer を追加する

Microsoft Entra ID への SAP Cloud for Customer の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAP Cloud for Customer を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「SAP Cloud for Customer**」と入力します。
4. 結果パネルから **SAP Cloud for Customer** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAP Cloud for Customer の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SAP Cloud for Customer に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SAP Cloud for Customer の関連ユーザーとの間にリンク関係を確立する必要があります。

SAP Cloud for Customer に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAP Cloud for Customer SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAP Cloud for Customer のテストユーザーを作成 - SAP Cloud for Customer で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズアプリ]**&gt;**[SAP Cloud for Customer]**&gt;**[シングルサインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server name>.crm.ondemand.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<server name>.crm.ondemand.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [SAP Cloud for Customer Client サポート チーム](https://www.sap.com/about/agreements.sap-cloud-services-customers.html) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. SAP Cloud for Customer アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: [編集] アイコンが選択されている [ユーザー属性] ダイアログを示すスクリーンショット。]
7. [ **ユーザー属性** と要求] ダイアログの [ **ユーザー属性** ] セクションで、次の手順を実行します。

    a. [ **編集] アイコン** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: [編集] アイコンが選択された [ユーザー属性と要求] を示すスクリーンショット。]

    [Image: 画像]

    b。 **変換元**として **[変換] を**選択します。

    c. **変換**の一覧から **ExtractMailPrefix()**を選択します。

    d. **パラメーター 1** の一覧から、実装に使用するユーザー属性を選択します。 たとえば、一意のユーザー識別子として EmployeeID を使用し、その属性値を ExtensionAttribute2 に保存している場合、[user.extensionattribute2] を選択します。

    e. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **SAP Cloud for Customer のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAP Cloud for Customer SSO の構成

1. 新しい Web ブラウザー ウィンドウを開き、SAP Cloud for Customer 企業サイトに管理者としてサインインします。
2. **アプリケーションとリソース**&gt;**テナント設定**に移動し、[**SAML 2.0 構成]** を選択します。

    [Image: [IDENTITY Providers](ID プロバイダー) ページが選択されていることを示すスクリーンショット。]
3. **[SAML 2.0 構成]** セクションで、次の手順を実行します。

    [Image: [参照] ボタンが選択されている [S A M L 2.0 構成] を示すスクリーンショット。]

    a. [ **参照** ] を選択して、以前にダウンロードしたフェデレーション メタデータ XML ファイルをアップロードします。

    b。 XML ファイルが正常にアップロードされると、以下の値が自動的に設定され、[ **保存]** を選択します。

#### SAP Cloud for Customer のテスト ユーザーの作成

Microsoft Entra ユーザーが SAP Cloud for Customer にサインインできるようにするには、そのユーザーを SAP Cloud for Customer にプロビジョニングする必要があります。 SAP Cloud for Customer では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. セキュリティ管理者として SAP Cloud for Customer にサインインします。
2. メニューの左側で、[**ユーザーと承認**]、[**ユーザーの管理&gt;ユーザー**の追加&gt;を選択**します**。

    [Image: [ユーザーの追加] ボタンが選択されている [ユーザー管理] ページを示すスクリーンショット。]
3. [ **新しいユーザーの追加** ] セクションで、次の手順を実行します。

    [Image: SAP 構成]

    a. **名** テキスト ボックスに、ユーザーの名前を **B** などと入力します。

    b。 [ **姓** ] テキスト ボックスに、ユーザーの名前 ( **Simon** など) を入力します。

    c. **[電子メール**] テキスト ボックスに、ユーザーの電子メール (`B.Simon@contoso.com`など) を入力します。

    d. [ **ログイン名** ] テキスト ボックスに、 **B.Simon** などのユーザーの名前を入力します。

    e. 要件に従って **[ユーザーの種類]** を選択します。

    f. 要件に従って **、[アカウントのアクティブ化** ] オプションを選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SAP Cloud for Customer のサインオン URL にリダイレクトされます。
- SAP Cloud for Customer のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SAP Cloud for Customer] タイルを選択すると、このオプションは SAP Cloud for Customer のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-fiori-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SAP Fiori を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-fiori-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAP Fiori の間にシングル サインオンを構成する方法について学習します。

この記事では、SAP Fiori と Microsoft Entra ID を統合する方法について説明します。 SAP Fiori を Microsoft Entra ID と統合すると、次のことができます。

- SAP Fiori にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SAP Fiori に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP Fiori でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SAP Fiori では、 **SP** Initiated SSO がサポートされます

注

SAP Fiori によって開始される iFrame 認証の場合は、サイレント認証に SAML AuthnRequest で **IsPassive** パラメーターを使用することをお勧めします。 **IsPassive** パラメーターの詳細については、[Microsoft Entra SAML のシングル サインオン情報を](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)参照してください。

### ギャラリーからの SAP Fiori の追加

Microsoft Entra ID への SAP Fiori の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに SAP Fiori を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「SAP Fiori**」と入力します。
4. 結果パネルから **SAP Fiori** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAP Fiori 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SAP Fiori に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SAP Fiori の関連ユーザーとの間にリンク関係を確立する必要があります。

SAP Fiori で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAP Fiori SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAP Fiori テストユーザーを作成 - B.Simon の対応として、SAP Fiori のユーザーを作成し、Microsoft Entra にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 新しい Web ブラウザー ウィンドウを開き、SAP Fiori 企業サイトに管理者としてサインインします。
2. **http** サービスと **https** サービスがアクティブであり、関連するポートがトランザクション コード **SMICM** に割り当てられていることを確認します。
3. シングル サインオンが必要な SAP Business Client for SAP システム **T01** にサインインします。 次に、HTTP セキュリティ セッション管理を有効にします。

    1. トランザクション コード **SICF\_SESSIONS**に移動します。 すべての関連プロファイル パラメーターとその現在の値が表示されます。 次の例のようになります。

        ```
        login/create_sso2_ticket = 2
        login/accept_sso2_ticket = 1
        login/ticketcache_entries_max = 1000
        login/ticketcache_off = 0  login/ticket_only_by_https = 0
        icf/set_HTTPonly_flag_on_cookies = 3
        icf/user_recheck = 0  http/security_session_timeout = 1800
        http/security_context_cache_size = 2500
        rdisp/plugin_auto_logout = 1800
        rdisp/autothtime = 60
        ```

        注

        組織の要件に基づいて、パラメーターを調整します。 上のパラメーターは、単に例として挙げたものです。
    2. 必要に応じて SAP システムのインスタンス (既定) プロファイルでパラメーターを調整し、SAP システムを再起動します。
    3. 関連するクライアントをダブルクリックして、HTTP セキュリティ セッションを有効にします。

        [Image: SAP の [関連プロファイル パラメーターの現在の値] ページ]
    4. 以下の SICF サービスをアクティブ化します。

        ```
        /sap/public/bc/sec/saml2
        /sap/public/bc/sec/cdc_ext_service
        /sap/bc/webdynpro/sap/saml2
        /sap/bc/webdynpro/sap/sec_diag_tool (This is only to enable / disable trace)
        ```
4. Business Client for SAP システムのトランザクション コード **SAML2** [**T01/122**] に移動します。 新しいブラウザー ウィンドウで構成 UI が開きます。 この例では、SAP システム 122 の Business Client を使用します。

    [Image: SAP Fiori Business クライアントのサインイン ページ]
5. ユーザー名とパスワードを入力し、[ログオン] を選択 **します**。

    [Image: SAP の ABAP システム T01/122 の SAML 2.0 構成ページ]
6. [ **プロバイダー名** ] ボックスで、 **T01122** を **http://T01122**に置き換え、[ **保存**] を選択します。

    注

    既定では、プロバイダー名は &lt;sid&gt;&lt;client&gt; という形式です。 Microsoft Entra ID では、&lt;プロトコル&gt;://&lt;名前&gt; という形式の名前が必要です。 Microsoft Entra ID で複数の SAP Fiori ABAP エンジンを構成できるように、プロバイダー名を https://&lt;sid&gt;&lt;クライアント&gt; として保持することをお勧めします。

    [Image: SAP の SAML 2.0 Configuration of ABAP System T01/122 ページの更新されたプロバイダー名]
7. [ **ローカル プロバイダー] タブ**&gt;**Metadata** を選択します。
8. **[SAML 2.0 メタデータ**] ダイアログ ボックスで、生成されたメタデータ XML ファイルをダウンロードし、コンピューターに保存します。

    [Image: [SAP SAML 2.0 メタデータ] ダイアログ ボックスの [メタデータのダウンロード] リンク]
9. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
10. **Entra ID**&gt;**Enterprise apps**&gt;**SAP Fiori**&gt;**シングルサインオン**にアクセスします。
11. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
12. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
13. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    1. [ **メタデータ ファイルのアップロード]** を選択します。

        [Image: メタデータ ファイルをアップロードする]
    2. **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

        [Image: メタデータ ファイルの選択]
    3. メタデータ ファイルが正常にアップロードされると、[**基本的な SAML 構成]** ウィンドウに**識別子**と**応答 URL** の値が自動的に設定されます。 [ **サインオン URL** ] ボックスに、次のパターンを持つ URL を入力します: `https://<your company instance of SAP Fiori>`。

        注

        一部のお客様からは、インスタンスに対して構成された応答 URL に誤りがあるというエラーの報告を受けています。 このようなエラーが発生した場合は、これらの PowerShell コマンドを使用します。 まず、アプリケーション オブジェクト内の応答 URL をこの応答 URL で更新してから、サービス プリンシパルを更新します。 [Get-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) を使用して、サービス プリンシパル ID 値を取得します。

        ```powershell
        $params = @{
           web = @{
              redirectUris = "<Your Correct Reply URL>"
           }
        }
        Update-MgApplication -ApplicationId "<Application ID>" -BodyParameter $params
        Update-MgServicePrincipal -ServicePrincipalId "<Service Principal ID>" -ReplyUrls "<Your Correct Reply URL>"
        ```
14. SAP Fiori アプリケーションは、特定の形式の SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性値を管理するには、[SAML を **使用した単一 Sign-On の設定** ] ウィンドウで [編集] を選択 **します**。

    [Image: [ユーザー属性] ウィンドウ]
15. [ **ユーザー属性と要求** ] ウィンドウで、前の図に示すように SAML トークン属性を構成します。 次に、次の手順を実行します。

    1. [ **編集] を** 選択して、[ **ユーザー要求の管理** ] ウィンドウを開きます。
    2. **変換**の一覧で、**ExtractMailPrefix()**を選択します。
    3. **パラメーター 1** の一覧で **user.userprincipalname** を選択します。
    4. **[保存] を選択します**。

        [Image: [ユーザー要求の管理] ウィンドウ]

        [Image: [ユーザー要求の管理] ウィンドウの [変換] セクション]
16. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
17. [ **SAP Fiori のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAP Fiori の SSO の構成

1. SAP システムにサインインし、トランザクション コード **SAML2** に移動します。 新しいブラウザー ウィンドウで SAML 構成ページが開かれます。
2. 信頼できる ID プロバイダー (Microsoft Entra ID) のエンドポイントを構成するには、[ **信頼されたプロバイダー** ] タブを選択します。

    [Image: SAP の [信頼できるプロバイダー] タブ]
3. [ **追加]** を選択し、コンテキスト メニューから **[メタデータ ファイルのアップロード** ] を選択します。

    [Image: SAP の [メタデータ ファイルの追加とアップロード] オプション]
4. ダウンロードしたメタデータ ファイルをアップロードします。 [ **次へ**] を選択します。

    [Image: SAP でアップロードするメタデータ ファイルを選択する]
5. 次のページの [ **エイリアス** ] ボックスにエイリアス名を入力します。 たとえば、 **aadsts などです**。 [ **次へ**] を選択します。

    [Image: SAP の [エイリアス] ボックス]
6. **[ダイジェスト アルゴリズム**] ボックスの値が **SHA-256** であることを確認します。 [ **次へ**] を選択します。

    [Image: SAP でダイジェスト アルゴリズムの値を確認する]
7. [ **単一 Sign-On エンドポイント**] で [ **HTTP POST**] を選択し、[ **次へ**] を選択します。

    [Image: SAP の単一 Sign-On エンドポイント オプション]
8. [ **単一ログアウト エンドポイント]** で 、[ **HTTP リダイレクト**] を選択し、[ **次へ**] を選択します。

    [Image: SAP の単一ログアウト エンドポイント オプション]
9. [ **アーティファクト エンドポイント] で**、[ **次へ** ] を選択して続行します。

    [Image: SAP のアーティファクト エンドポイント オプション]
10. [ **認証要件**] で [完了] を選択 **します**。

    [Image: 認証要件オプションと SAP の [完了] オプション]
11. **[信頼されたプロバイダー**&gt;**識別フェデレーション**] を選択します (ページの下部)。 [ **編集] を選択します**。

    [Image: SAP の [信頼されたプロバイダーと ID フェデレーション] タブ]
12. **追加**を選択します。

    [Image: [ID フェデレーション] タブの [追加] オプション]
13. [ **サポートされている NameID 形式** ] ダイアログ ボックスで、[ **未指定**] を選択します。 [ **OK] を選択します**。

    [Image: SAP の [サポートされている NameID 形式] ダイアログ ボックスとオプション]

    **ユーザー ID ソース**と**ユーザー ID マッピング モード**の値によって、SAP ユーザーと Microsoft Entra 要求の間のリンクが決まります。

    **シナリオ 1**: SAP ユーザーから Microsoft Entra へのユーザー マッピング

    1. SAP の **NameID 形式の詳細 "Unspecified"** の下で、詳細を書き留めます。

        [Image: S A P の [NameID 形式]
    2. Azure portal の [ **ユーザー属性と要求**] で、Microsoft Entra ID から必要な要求を書き留めます。

        [Image: [ユーザー属性と要求] ダイアログ ボックスを示すスクリーンショット。]

    **シナリオ 2**: SU01 で構成された電子メール アドレスに基づいて SAP ユーザー ID を選択します。 このケースでは、SSO を必要とする各ユーザーに対して、SU01 でメール ID を構成する必要があります。

    1. SAP の **NameID 形式の詳細 "Unspecified"** の下で、詳細を書き留めます。

        SAP の [NameID フォーマット「Unspecified」ダイアログ ボックス] についての詳細
    2. Azure portal の [ **ユーザー属性と要求**] で、Microsoft Entra ID から必要な要求を書き留めます。

        [Image: Azure portal の [ユーザー属性と要求] ダイアログ ボックス]
14. [ **保存]** を選択し、[ **有効]** を選択して ID プロバイダーを有効にします。

    [Image: SAP の [保存と有効化] オプション]
15. メッセージが表示されたら **、[OK] を選択します** 。

    [Image: SAP の [SAML 2.0 構成] ダイアログ ボックスの [OK] オプション]

#### SAP Fiori のテスト ユーザーの作成

このセクションでは、SAP Fiori で Britta Simon というユーザーを作成します。 組織内の SAP 専門家チームまたは組織の SAP パートナーと協力して、SAP Fiori プラットフォームにユーザーを追加してください。

### SSO のテスト

1. SAP Fiori で ID プロバイダー Microsoft Entra ID がアクティブ化された後、次のいずれかの URL にアクセスしてみて、シングル サインオンをテストします (ユーザー名とパスワードの入力は求められないはずです)。

    - `https://<sap-url>/sap/bc/bsp/sap/it00/default.htm`
    - `https://<sap-url>/sap/bc/bsp/sap/it00/default.htm`

    注

    `<sap-url>` は実際の SAP のホスト名に置き換えます。
2. テスト URL によって、SAP の以下のテスト アプリケーション ページに移動するはずです。 ページが開いた場合は、Microsoft Entra シングル サインオンが正常に設定されています。

    [Image: SAP の標準テスト アプリケーション ページ]
3. ユーザー名とパスワードの入力を求められた場合は、問題の診断に役立つトレースを有効にします。 トレースには次の URL を使用します。

    `https://<sap-url>/sap/bc/webdynpro/sap/sec_diag_tool?sap-client=122&sap-language=EN#`。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SAP Cloud Identity Services を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra IDと SAP Cloud Identity Services の間でシングル サインオンを構成する方法について説明します。

この記事では、シングル サインオンのために SAP Cloud Identity Services とMicrosoft Entra IDを統合する方法について説明します。 SAP Cloud Identity Services を Microsoft Entra ID と統合すると、次のことができます。

- ユーザーが SAP Cloud Identity Services に対して認証する方法をMicrosoft Entra ID制御します。
- ユーザーが自分のMicrosoft Entra アカウントを使用して SAP Cloud Identity Services およびダウンストリーム SAP アプリケーションに自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

ヒント

推奨事項とベスト プラクティス ガイド「[MICROSOFT ENTRA IDを使用して SAP プラットフォームとアプリケーションへのアクセスをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/fundamentals/scenario-azure-first-sap-identity-integration)」に従ってセットアップを運用化します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [SAP Cloud Identity Services テナント](https://www.sap.com/products/cloud-platform.html)
- 管理者アクセス許可を持つ SAP Cloud Identity Services のユーザー アカウント。

Microsoft Entra IDにユーザーがまだ存在しない場合は、記事「SAPのソースアプリとターゲットアプリを用いたユーザーのプロビジョニングのためのMicrosoft Entra展開計画」から始めてください。 この記事では、SAP SuccessFactors など、組織内のワーカーの一覧の権限のあるソースとMicrosoft Entraを接続する方法について説明します。 また、Microsoft Entraを使用してこれらのワーカーの ID を設定し、SAP ECC や SAP S/4HANA などの 1 つ以上の SAP アプリケーションにサインインできるようにする方法についても説明します。

Microsoft Entra ID Governanceを使用して SAP ワークロードへのアクセスを管理する運用環境で SAP Cloud Identity Services へのシングル サインインを構成する場合は、先に進む前に、id ガバナンス のMicrosoft Entra IDを構成する前に、前提条件を確認してください。

### シナリオの説明

この記事では、Microsoft Entra のシングル サインオンを SAP Cloud Identity Services に構成してテストします。

- SAP Cloud Identity Services では、SAML を使用してサービス プロバイダー (**SP**) と ID プロバイダー (**IDP**) によって開始される SSO がサポートされます。 SAP Cloud Identity Services では OpenID Connect もサポートされていますが、このオプションについてはこの記事では説明しません。
- SAP Cloud Identity Services では、Microsoft Entra IDからのユーザーとグループのプロビジョニングもサポートされています。 詳細については、「 [自動ユーザー プロビジョニング」を](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)参照してください。

技術的な詳細の説明に入る前に、調べようとしている事柄の概念を理解する必要があります。 SAP Cloud Identity Services を使用すると、ID プロバイダーとしてMicrosoft Entra IDと直接統合された SAP 以外のアプリケーションと同じ SSO エクスペリエンスを使用して、SAP アプリケーションとサービス全体に SSO を実装できます。

SAP Cloud Identity Services は、 [ターゲット システム](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-systems)として他の SAP アプリケーションに対するプロキシ ID プロバイダーとして機能します。 Microsoft Entra IDは、このセットアップで主要な ID プロバイダーとして機能します。

次の図は、信頼関係を示しています。

[Image: SAP アプリケーション、SAP Cloud Identity Services、Microsoft Entra 間の信頼関係のアーキテクチャ図。]

このセットアップにより、SAP Cloud Identity Services は、Microsoft Entra IDで 1 つ以上のアプリケーションとして構成されます。 Microsoft Entraは、SAP Cloud Identity Services で **Corporate Identity Provider** として構成されます。

この方法でシングル サインインを提供するすべての SAP アプリケーションとサービスは、その後、SAP Cloud Identity Services でアプリケーションとして構成されます。

[Image: SSO とプロビジョニング フローのアーキテクチャの図、SAP アプリケーション、SAP Cloud Identity Services、Microsoft Entra の間で。]

Microsoft EntraでのSAP Cloud Identity Servicesアプリケーションロールへのユーザー割り当てによって、トークン発行はMicrosoft EntraによってSAP Cloud Identity Servicesに制御されます。 特定の SAP アプリケーションとサービスへのアクセスを許可するための承認と、それらの SAP アプリケーションのロールの割り当ては、SAP Cloud Identity Services とアプリケーション自体で行われます。 この承認は、Microsoft Entra IDからプロビジョニングされたユーザーとグループに基づいて行うことができます。

注

現在、その両者で Web SSO のみがテストされています。 アプリ対API または API 対 API の通信に必要なフローは機能するものの、まだテストされていません。 これらは、後続のアクティビティ中にテストされます。

### ギャラリーからの SAP Cloud Identity Services の追加

Microsoft Entra IDへの SAP Cloud Identity Services の SAML 統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAP Cloud Identity Services を追加する必要があります。

1. [Microsoft Entra admin center](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「SAP Cloud Identity Services」と**入力します。
4. 結果パネルから **SAP Cloud Identity Services を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 Microsoft 365 wizards.

### SAP Cloud Identity Services のMicrosoft Entra SSO の構成とテスト

B.Simonします。 SSO を機能させるには、Microsoft Entra ユーザーと SAP Cloud Identity Services の関連ユーザーとの間にリンク関係を確立する必要があります。

SAP Cloud Identity Services Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**を構成する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra のテスト ユーザーの作成** - Microsoft Entra のシングル サインオンを B.Simon でテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAP Cloud Identity Services の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAP Cloud Identity Services のテスト ユーザーの作成** - SAP Cloud Identity Services で B.Simon に相当するユーザーを作成し、Microsoft Entra のユーザーに関連付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### SAP Cloud Identity Services フェデレーション メタデータを取得する

1. SAP Cloud Identity Services 管理コンソールにサインインします。 URL には、 `https://<tenant-id>.accounts.ondemand.com/admin` または `https://<tenant-id>.trial-accounts.ondemand.com/admin`というパターンがあります。
2. [ **アプリケーションとリソース]** で、[ **テナント設定]** を選択します。

    [Image: テナント設定を示すスクリーンショット。]
3. [ **シングル サインオン** ] タブで、[ **SAML 2.0 構成]** を選択します。 次に、[ **メタデータ ファイルのダウンロード** ] を選択して、SAP Cloud Identity Services のフェデレーション メタデータをダウンロードします。

    [Image: メタデータのダウンロード ボタンを示すスクリーンショット。]

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra admin center](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。 アプリケーションの名前 ( **SAP Cloud Identity Services など) を**入力します。 アプリケーションを選択し、[ **シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **基本的な SAML 構成]** セクションで、SAP Cloud Identity Services **のサービス プロバイダー メタデータ ファイル** がある場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    b。 **フォルダー ロゴ**を選択して、SAP からダウンロードしたメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択を示すスクリーンショット]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    [Image: URL を示すスクリーンショット。]

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
5. さらに構成する場合は、[ **SAML でシングル サインオンを設定** する] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]

    [ **サインオン URL (省略可能)]** テキスト ボックスに、特定のビジネス アプリケーションのサインオン URL を入力します。

    注

    この値を実際のサインオン URL で更新してください。 詳細については、 [SAP ナレッジ ベースの記事3128585](https://userapps.support.sap.com/sap/support/knowledge/en/3128585)を参照してください。 ご質問がある場合は、 [SAP Cloud Identity Services クライアント サポート チーム](https://cloudplatform.sap.com/capabilities/security/trustcenter.html) にお問い合わせください。
6. SAP Cloud Identity Services アプリケーションでは、特定の形式の SAML アサーションが予測されるため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性を示すスクリーンショット。]
7. 上記に加えて、SAP Cloud Identity Services アプリケーションでは、下に示すいくつかの追加の属性が SAML 応答で戻されることが予測されます。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **SAP Cloud Identity Services のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entraのテスト ユーザーを作成して割り当てる

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAP Cloud Identity Services の SSO の構成

このセクションでは、SAP Cloud Identity Services 管理コンソールで企業 ID プロバイダーを作成します。 詳細については、 [管理コンソールでの企業 IdP の作成を参照してください](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/create-corporate-idp-in-administration-console)。

1. SAP Cloud Identity Services 管理コンソールにサインインします。 URL には、 `https://<tenant-id>.accounts.ondemand.com/admin` または `https://<tenant-id>.trial-accounts.ondemand.com/admin`というパターンがあります。
2. [ **ID プロバイダー**] で、[ **企業 ID プロバイダー] タイルを** 選択します。
3. [ **+ 作成** ] を選択して ID プロバイダーを作成します。

    [Image: ID プロバイダーを示すスクリーンショット。]
4. **[ID プロバイダーの作成**] ダイアログ ボックスで次の手順を実行します。

    [Image: ID プロバイダーの作成を示すスクリーンショット。]

    a. **[表示名**] に有効な名前を付けます。

    b。 ドロップダウンから **Microsoft ADFS/Entra ID (SAML 2.0)** を選択します。

    c. **作成**を選択します。
5. **[信頼] -&gt; [SAML 2.0 構成]** に移動します。 **Metadata File** のフィールドで、**Browse** を選択して、Microsoft Entra SSO 構成からダウンロードした **Metadata XML** ファイルをアップロードします。

    [Image: ID プロバイダーの構成を示すスクリーンショット。]
6. **[保存] を選択します**。
7. 以降は、もう 1 つの SAP アプリケーションに対して SSO を追加して有効にする場合にのみ行います。 **「ギャラリーからの SAP Cloud Identity Services の追加**」セクションの手順を繰り返します。
8. Entra 側の **SAP Cloud Identity Services** アプリケーション統合ページで、[ **リンクされたサインオン**] を選択します。

    [Image: リンクされたサインオンの構成を示すスクリーンショット]
9. 構成を保存します。
10. 詳細については、[integration with Microsoft Entra ID](https://developers.sap.com/tutorials/cp-ias-azure-ad.html) の SAP Cloud Identity Services に関するドキュメントを参照してください。

注

新しいアプリケーションでは、前の SAP アプリケーションのシングル サインオン構成が再利用されます。 SAP Cloud Identity Services 管理コンソールで、同じ会社の ID プロバイダーを使用していることを確認してください。

#### SAP Cloud Identity Services のテスト ユーザーの作成

SAP Cloud Identity Services でユーザーを作成する必要はありません。 Microsoft Entra ユーザー ストアにいるユーザーは、SSO 機能を使用できます。

SAP Cloud Identity Services では、ID フェデレーション オプションがサポートされています。 このオプションにより、アプリケーションは、会社の ID プロバイダーによって認証されたユーザーが、SAP Cloud Identity Services のユーザー ストアに存在するかどうかを確認できます。

既定では、ID フェデレーション オプションは無効になっています。 ID フェデレーションが有効になっていると、SAP Cloud Identity Services にインポートされているユーザーのみがアプリケーションにアクセスできます。

SAP Cloud Identity Services との ID フェデレーションを有効または無効にする方法の詳細については、「SAP Cloud Identity Services の [ユーザー ストアとの ID フェデレーションの構成」の「SAP Cloud Identity Services との ID フェデレーションを](https://help.sap.com/viewer/6d6d63354d1242d185ab4830fc04feb1/Cloud/c029bbbaefbf4350af15115396ba14e2.html)有効にする」を参照してください。

注

SAP Cloud Identity Services では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、**SP initiated** および **IDP initiated** オプションを使用して、Microsoft Entra シングル サインオン構成をテストします。

関連付け ID を使用して SAP Cloud Identity Services へのサインイン時にエラーが発生した場合は、SAP Cloud Identity Services 管理コンソールで、その関連付け ID の [トラブルシューティング ログを検索](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/view-troubleshooting-logs) できます。 詳細については、 [SAP ナレッジ ベースの記事2698571](https://userapps.support.sap.com/sap/support/knowledge/en/2698571) および [SAP ナレッジ ベースの記事3201824](https://userapps.support.sap.com/sap/support/knowledge/en/3201824)を参照してください。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SAP Cloud Identity Services のサインオン URL にリダイレクトされます。
- SAP Cloud Identity Services のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SAP Cloud Identity Services に自動的にサインインします

Microsoft My Appsを使用して、任意のモードでアプリケーションをテストすることもできます。 My Appsで [SAP Cloud Identity Services] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SAP Cloud Identity Services に自動的にサインインされます。 My Appsの詳細については、「[My Appsのイントロダクション](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。

### SAP Cloud Identity Services で既存のユーザーを検出する

Microsoft Entraとの統合の前に、SAP Cloud Identity Services アカウントに既に 1 人以上のユーザーが存在する可能性があります。 アカウント検出機能を使用すると、SAP Cloud Identity Services 内のすべてのユーザーのレポートを生成し、Entra で一致するアカウントを持っているユーザーと、SAP Cloud Identity Services に対してローカルなユーザーを 1 回のクリックで識別できます。 アカウント検出機能の詳細については [、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)。 これにより、Entra へのオンボードを簡素化しながら、承認されていないアクセスを段階的に監視することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-hana-cloud-platform-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に SAP Business Technology Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAP Business Technology Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、SAP Business Technology Platform と Microsoft Entra ID を統合する方法について説明します。 SAP Business Technology Platform と Microsoft Entra ID を統合すると、次のことができます。

- SAP Business Technology Platform にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SAP Business Technology Platform に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。
- Microsoft Entra のユーザーを SAP Business テクノロジ プラットフォームロールに割り当てます。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP Business Technology Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

重要

シングル サインオンをテストするには、独自のアプリケーションをデプロイするか、SAP Business Technology Platform アカウントでアプリケーションをサブスクライブする必要があります。 この記事では、アプリケーションをアカウントにデプロイします。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SAP Business Technology Platform では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから SAP Business Technology Platform を追加する

Microsoft Entra ID への SAP Business Technology Platform の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAP Business Technology Platform を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動してください。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「SAP Business Technology Platform**」と入力します。
4. 結果パネルから **SAP Business Technology Platform** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAP Business Technology Platform の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SAP Business Technology Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SAP Business Technology Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

SAP Business Technology Platform に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAP Business Technology Platform の SSO の構成**- アプリケーション側で単一 Sign-On 設定を構成します。
    1. **SAP Business Technology Platform のテスト ユーザーを作成する** - Microsoft Entra の Britta Simon のユーザー表現にリンクされている、SAP Business Technology Platform 内の Britta Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDエンタープライズ アプリSAP Business Technology Platformシングルサインオンに移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して、SAP Business Technology Platform の URL を入力します。

    | **識別子** |
    | --- |
    | `https://hanatrial.ondemand.com/<instancename>` |
    | `https://hana.ondemand.com/<instancename>` |
    | `https://us1.hana.ondemand.com/<instancename>` |
    | `https://ap1.hana.ondemand.com/<instancename>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<subdomain>.hanatrial.ondemand.com/<instancename>` |
    | `https://<subdomain>.hana.ondemand.com/<instancename>` |
    | `https://<subdomain>.us1.hana.ondemand.com/<instancename>` |
    | `https://<subdomain>.dispatcher.us1.hana.ondemand.com/<instancename>` |
    | `https://<subdomain>.ap1.hana.ondemand.com/<instancename>` |
    | `https://<subdomain>.dispatcher.ap1.hana.ondemand.com/<instancename>` |
    | `https://<subdomain>.dispatcher.hana.ondemand.com/<instancename>` |

    c. [ **サインオン URL** ] ボックスに、 **SAP Business Technology Platform** アプリケーションへのサインインにユーザーが使用する URL を入力します。 これは、SAP Business Technology Platform アプリケーションの保護されたリソースのアカウント固有の URL です。 URL は、次のパターンに基づいています: `https://<applicationName><accountName>.<landscape host>.ondemand.com/<path_to_protected_resource>`

    手記

    これは、ユーザーに認証を要求する SAP Business Technology Platform アプリケーションの URL です。

    | **サインオン URL** |
    | --- |
    | `https://<subdomain>.hanatrial.ondemand.com/<instancename>` |
    | `https://<subdomain>.hana.ondemand.com/<instancename>` |

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 Sign-On URL と識別子を取得するには、SAP Business Technology Platform クライアント サポート チーム  にお問い合わせください。 信頼管理セクションから取得できる応答 URL については、この記事の後半で説明します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAP Business Technology Platform の SSO の構成

1. 別の Web ブラウザー ウィンドウで、`https://account.<landscape host>.ondemand.com/cockpit`の SAP Business Technology Platform Cockpit にサインオンします (例: https://account.hanatrial.ondemand.com/cockpit)。
2. [ **信頼** ] タブを選択します。

    [Image: 信頼]
3. [信頼の管理] セクションの [ローカル サービス プロバイダー で、次の手順を実行します。

    [Image: [ローカル サービス プロバイダー] タブが選択され、すべてのテキスト ボックスが強調表示されている [信頼の管理] セクションを示すスクリーンショット。]

    ある。 [ **編集] を選択します**。

    b。 **[構成の種類] で**、[カスタム] を選択**します**。

    c. **[ローカル プロバイダー名] は**既定値のままにします。 この値をコピーし、SAP Business Technology Platform の Microsoft Entra 構成の **[識別子** ] フィールドに貼り付けます。

    d. **署名キーと署名** **証明書**キーのペアを生成するには、[**キー ペアの生成**] を選択します。

    え **プリンシパル伝達**として、**[無効]**を選択します。

    f. **強制認証**で、**無効**を選択します。

    ジー **[保存] を選択します**。
4. **ローカル サービス プロバイダー**の設定を保存した後、次の手順に従って応答 URL を取得します。

    [Image: メタデータの取得]

    ある。 [ **メタデータの取得**] を選択して、SAP Business Technology Platform メタデータ ファイルをダウンロードします。

    b。 ダウンロードした SAP Business Technology Platform メタデータ XML ファイルを開き、 **ns3:AssertionConsumerService** タグを見つけます。

    c. **Location** 属性の値をコピーし、SAP Business Technology Platform の Microsoft Entra 構成の **[応答 URL**] フィールドに貼り付けます。
5. [ **信頼できる ID プロバイダー** ] タブを選択し、[ **信頼された ID プロバイダーの追加] を選択します**。

    [Image: [信頼された ID プロバイダー] タブが選択されている [信頼の管理] ページを示すスクリーンショット。]

    手記

    信頼できる ID プロバイダーの一覧を管理するには、[ローカル サービス プロバイダー] セクションでカスタム構成の種類を選択する必要があります。 既定の構成の種類では、SAP ID サービスに対する編集不可能で暗黙の信頼があります。 [なし] の場合、信頼設定はありません。
6. [ **全般** ] タブを選択し、[ **参照** ] を選択して、ダウンロードしたメタデータ ファイルをアップロードします。

    [Image: 信頼管理]

    手記

    メタデータ ファイルをアップロードすると、 **シングル サインオン URL**、 **シングル ログアウト URL**、署名 **証明書** の値が自動的に設定されます。
7. [属性] タブ **を** 選択します。
8. [属性] タブ **で** 、次の手順を実行します。

    [Image: 属性]

    ある。 [ **Assertion-Based 属性の追加**] を選択し、次のアサーション ベースの属性を追加します。

    | アサーション属性 | プリンシパル属性 |
    | --- | --- |
    | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname` | ファーストネーム |
    | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname` | 名字 |
    | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress` | メール |

    手記

    属性の構成は、SCP 上のアプリケーションの開発方法、つまり SAML 応答で期待される属性、およびコード内でこの属性にアクセスする名前 (プリンシパル属性) によって異なります。

    b。 スクリーンショットの **既定の属性** は、説明のみを目的としたものです。 シナリオを機能させる必要はありません。

    c. スクリーンショットに表示される **プリンシパル属性** の名前と値は、アプリケーションの開発方法によって異なります。 アプリケーションで異なるマッピングが必要な場合があります。

#### アサーション ベースのグループ

オプションの手順として、Microsoft Entra ID プロバイダーのアサーション ベースのグループを構成できます。

SAP Business Technology Platform でグループを使用すると、SAML 2.0 アサーション内の属性の値によって決定される、SAP Business Technology Platform アプリケーションの 1 つ以上のロールに 1 人以上のユーザーを動的に割り当てることができます。

たとえば、アサーションに属性 "*contract=temporary*" が含まれている場合、影響を受けるすべてのユーザーをグループ "*TEMPORARY*" に追加できます。 グループ "*TEMPORARY*" には、SAP Business Technology Platform アカウントにデプロイされた 1 つ以上のアプリケーションの 1 つ以上のロールを含めることができます。

SAP Business Technology Platform アカウント内のアプリケーションの 1 つ以上のロールに多数のユーザーを同時に割り当てる場合は、アサーション ベースのグループを使用します。 1 人または少数のユーザーのみを特定のロールに割り当てる場合は、SAP Business Technology Platform コックピットの [**承認**] タブで直接割り当てることをお勧めします。

#### SAP Business Technology Platform のテスト ユーザーの作成

Microsoft Entra ユーザーが SAP Business Technology Platform にログインできるようにするには、SAP Business Technology Platform のロールをユーザーに割り当てる必要があります。

**ユーザーにロールを割り当てるには、次の手順に従います。**

1. **SAP Business Technology Platform** コックピットにログインします。
2. 次の操作を実行します。

    [Image: 承認]

    ある。 [ **承認] を選択します**。

    b。 [ **ユーザー** ] タブを選択します。

    c. [ **ユーザー** ] ボックスに、ユーザーのメール アドレスを入力します。

    d. [ **割り当て]** を選択して、ユーザーをロールに割り当てます。

    え **[保存] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SAP Business Technology Platform のサインオン URL にリダイレクトされます。
- SAP Business Technology Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SAP Business Technology Platform] タイルを選択すると、SSO を設定した SAP Business Technology Platform に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。

### SAP Business Technology Platform アプリケーション ロールのグループを作成し、グループを Business Technology Platform ロール コレクションに割り当てる

Microsoft Entra セキュリティ グループを作成し、それらのグループ ID をアプリケーション ロールにマップできます。 詳細については、 [SAP BTP へのアクセスの管理を](https://community.sap.com/t5/technology-blogs-by-members/identity-and-access-management-with-microsoft-entra-part-i-managing-access/ba-p/13873276)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-hana-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して SAP HANA へのユーザー プロビジョニングを自動化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-10-01
- Summary: SAP Cloud Identity Services を使用して、Microsoft Entra ID から SAP HANA に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SAP Cloud Identity Services と Microsoft Entra ID で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスと SAP Cloud Identity Services を使用して、SAP HANA に対するユーザーのプロビジョニングとプロビジョニング解除が自動的に行われます。 Microsoft Entra プロビジョニングが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされている機能

- SAP HANA でユーザーを作成し、SAP HANA へのシングル サインオンを有効にする
- アクセスが不要になった場合に SAP HANA のユーザーを削除する
- Microsoft Entra ID と SAP HANA の間でユーザー属性の同期を維持する

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP HANA Cloud または SAP HANA Database
- SAP Cloud Identity Services のテナント
- 管理者アクセス許可がある SAP Identity Provisioning の管理コンソールのユーザー アカウント。 Identity Provisioning 管理コンソールのプロキシ システムにアクセスできることを確認します。 **[プロキシ システム]** タイルが表示されない場合は、このタイルへのアクセスを要求するコンポーネント **BC-IAM-IPS** のインシデントを作成します。

### 手順 1:プロビジョニングのデプロイを計画する

Microsoft Entra には、SAP ECC、SAP Cloud Identity Services、SAP SuccessFactors へのコネクタがあります。 SAP HANA またはその他のアプリケーションへのプロビジョニングでは、ユーザーはまず Microsoft Entra ID に存在する必要があります。 Microsoft Entra ID にユーザーを作成すると、それらのユーザーを Microsoft Entra ID から SAP Cloud Identity Services にプロビジョニングできます。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内の Microsoft Entra ID から送信されたユーザーを、SAP クラウド コネクタを介した [`SAP HANA Cloud` や「SAP HANA Database」](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/sap-hana-cloud-sap-hana-database-7e2b54ec36344dc1983d5f8a6437b11b)、その他を含むダウンストリーム SAP アプリケーションにプロビジョニングします。

[Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

### 手順 2: Microsoft Entra ID に適切なユーザーがあることを確かめる

その後、SuccessFactors からの HR 受信を使用して、従業員の参加、移動、離脱時に Microsoft Entra ID のユーザーの一覧を最新の状態に保つことができます。 グループまたはアプリケーション ロールの割り当てを使用して、SAP HANA にアクセスできるユーザーまたは SAP HANA にアクセスできるロールのスコープを設定する予定で、テナントにMicrosoft Entra ID ガバナンスのライセンスがある場合は SAP Cloud Identity Services または SAP HANA を表すアプリケーションの Microsoft Entra ID で、[アプリケーション ロールの割り当てに対する変更を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#assign-users-the-necessary-application-access-rights-in-microsoft-entra)することもできます。 プロビジョニング前に職務の分離やその他のコンプライアンス チェックを実行する方法の詳細については、「[アクセス ライフサイクル管理シナリオの移行](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/migrate-from-sap-idm#migrate-access-lifecycle-management-scenarios)」を参照してください。

SAP アプリケーションをターゲットとする ID ライフサイクルの詳細なガイダンスについては、「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー ID プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

### 手順 3: Microsoft Entra ID から SAP Cloud Identity Services へのプロビジョニングを構成する

SAP Cloud Identity Services と統合された SAP HANA や他のアプリケーションへのユーザーのプロビジョニングを準備するには、SAP Cloud Identity Services にそれらのアプリケーションに必要なスキーマ マッピングがあることを確認します。 次に、[Microsoft Entra ID から SAP Cloud Identity Services へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#provision-users-to-sap-cloud-identity-services)を構成します。 SAP Cloud Identity Services はその後、必要に応じてダウンストリーム SAP アプリケーションにユーザーをプロビジョニングします。

Microsoft Entra から SAP Cloud Identity Services にユーザーをプロビジョニングするには、2 つの方法があります。

- SAP HANA クラウドのロールにユーザーを割り当てるなど、Microsoft Entra ID のグループを使用している場合は、SAP Cloud Identity Services プロビジョニングを使用します。 まず、SAP Analytics Cloud で使用される SAP ビジネス ロール用の Microsoft Entra グループを作成します。 次に、SAP Cloud Identity Services のプロビジョニングで、[Microsoft Entra ID をソースとして構成](https://help.sap.com/docs/identity-provisioning/identity-provisioning/microsoft-azure-active-directory)し、ユーザーとグループを Microsoft Entra ID から SAP Cloud Identity Services に取り込み、作成されたグループを SAP ビジネス ロールにマップします。 詳細については、SAP のドキュメントの「[Provision users from Microsoft Azure AD to SAP Cloud Identity Services - Identity Authentication (Microsoft Azure AD から SAP Cloud Identity Services にユーザーをプロビジョニングする - Identity Authentication)](https://blogs.sap.com/2022/02/04/provision-users-from-microsoft-azure-ad-to-sap-cloud-identity-services-identity-authentication/)」を参照してください。
- または、Microsoft Entra ID でグループを使用する必要がない場合は、Microsoft Entra プロビジョニング サービスを使用できます。 このシナリオでは、SAP HANA を表すアプリケーションを作成し、SAP HANA へのアクセスを必要とするユーザーをそのアプリケーションに割り当てます。 次に、[Microsoft Entra ID を使用した、SAP Cloud Identity Services への自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)を構成します。 これらのユーザーが SAP Cloud Identity Services にプロビジョニングされるまで待ち、SAP HANA ターゲットに必要な属性があることを確認します。

注

小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 ユーザーが SAP ダウンストリーム ターゲットで適切なアクセス権を持っていること、サインイン時に適切なロールを持っていることを確認します。

### 手順 4: SAP Cloud Identity Services から SAP HANA へのプロビジョニングを構成する

この手順では、SAP Cloud Identity Services Identity Provisioning を使用して SAP HANA をターゲット システムとして構成します。このシステムでは、ユーザーとグループ メンバーをプロビジョニングできます。 SAP HANA Cloud または SAP HANA Database については、[SAP HANA Cloud または SAP HANA Database へのプロビジョニングに関する SAP ドキュメント](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/sap-hana-cloud-sap-hana-database-7e2b54ec36344dc1983d5f8a6437b11b)を参照してください。

### 手順 5: シングル サインオンの構成

SAP アプリケーションのユーザーのプロビジョニングを設定すると、それらのアプリケーションとのシングル サインオンを有効にする必要があります。 Microsoft Entra ID は、SAP アプリケーションの ID プロバイダーおよび認証機関として機能することができます。 [Microsoft Entra シングル サインオン (SSO) と SAP Cloud Identity Services との統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)をまだ構成していない場合は、構成します。

SAP SaaS や最新のアプリにシングル サインオンを構成する方法の詳細は、「[SSO の有効化](https://learn.microsoft.com/ja-jp/entra/id-governance/sap#enable-sso)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-hcm-microsoft-entra-identity-provisioning"} -->
## SAP Human Capital Management (HCM) から Microsoft Entra ID へのユーザーのプロビジョニング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hcm-microsoft-entra-identity-provisioning
- Service: entra-id / saas-apps
- Article date: 2025-07-18
- Summary: SAP Human Capital Management (HCM) から Microsoft Entra ID にユーザーをプロビジョニングする方法について説明します。

この記事では、SAP IDM から Microsoft Entra に移行する組織のアーキテクチャに関する考慮事項を含め、SAP HCM [から Microsoft Entra ID にユーザーをプロビジョニングする計画に](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/migrate-from-sap-idm)役立ちます。

大まかに言うと、このドキュメントでは、SAP HCM for SAP S/4HANA On-Premise を含む SAP HCM の 3 つの統合オプションについて説明します。

- オプション 1: SAP HCM からの CSV ファイル ベースの受信プロビジョニング
- オプション 2: Azure Logic Apps SAP コネクタを使用した SAP BAPI ベースの受信プロビジョニング
- オプション 3: Azure Logic Apps SAP コネクタを使用した SAP IDocs ベースの受信プロビジョニング

4 番目のオプションもあります。

- オプション 4: SAP SuccessFactors と SAP HCM の両方を使用する組織は、SAP Integration Suite を使用して SAP HCM と SAP SuccessFactors の間でワーカーのリストを同期することで、ID を Microsoft Entra ID に取り込むこともできます。 そこから、Microsoft Entra ID コネクタを使用して、 [SuccessFactors の従業員の ID を Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial)[に取り込んだり、SuccessFactors からオンプレミスの Active Directory](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial) にプロビジョニングしたりできます。

### 用語

| 任期 | Definition |
| --- | --- |
| AS ABAP | 高度なビジネス アプリケーション プログラミング (ABAP) ランタイムをサポートする SAP NetWeaver Application Server プラットフォーム。 |
| SAP NetWeaver | SAP HCM を含むオンプレミスの SAP ERP アプリケーションをホストし、統合機能を提供する SAP のアプリケーション サーバー プラットフォーム。 |
| BAPI | ビジネス アプリケーション プログラミング インターフェイス;を使用すると、外部アプリケーションから SAP ビジネス オブジェクトのデータとプロセスにアクセスできます。 |
| IDocs | 中間ドキュメント;システム間でビジネス データを交換するための標準化された SAP 形式。バッチ処理用の制御レコードとデータ レコードで構成されます。 |
| RFC | リモート関数呼び出し;SAP と外部システム間の通信のための SAP プロトコル。RFC ポートを介した受信関数呼び出しと送信関数呼び出しをサポートします。 |
| FM | Function Modules;SAP HCM で構成された BAPI 汎用モジュール。 |

### アーキテクチャの概要

#### SAP HCM テーブルとインフォタイプ

SAP HCM の従業員データは、SQL リレーショナル データベースに格納されます。 適切な汎用モジュール (FM) とアクセス許可にアクセスすることで、従業員情報を格納するバックエンド データベース テーブルに対してクエリを実行できます。 データベース内のすべてのテーブルに対して、SAP HCM にはインフォ **タイプ**と呼ばれる機能的に同等の機能があります。これは、関連するデータを論理的にグループ化するためのメカニズムです。 SAP HCM 管理者は、SAP HCM 画面で従業員データを管理するときにインフォタイプ用語を使用します。 このセクションでは、従業員データを格納する SAP HCM の重要なテーブルの一覧を提供します。 [ **担当者番号 - PERNR** ] フィールドは、これらのテーブル内のすべての従業員を一意に識別します。

| テーブル名 | インフォタイプ | 注釈 |
| --- | --- | --- |
| PA0000 | 0000 - アクション | 従業員に対して HR によって実行されたアクションをキャプチャします。 フィールドの例: 雇用状態、アクションの種類、アクション名、開始日、終了日。 |
| PA0001 | 0001 – 従業員組織の割り当て | 従業員の組織データをキャプチャします。 フィールドの例: 会社コード、原価センタ、事業領域。 |
| PA0002 | 0002 – 個人データ | 個人データをキャプチャします。 フィールドの例: 名、姓、生年月日、国籍。 |
| PA0006 | 0006 – アドレス | 住所と電話のデータをキャプチャします。 フィールドの例: street、city、country、phone。 |

アクティブな従業員のユーザー名、状態、名、姓を取得する SQL クエリの例:

```sql
SELECT DISTINCT p0.PERNR username, CASE WHEN (p0.STAT2 = 3) THEN 1 END statuskey, p2.NACHN last_name, p2.VORNA first_name
FROM SAP_PA0000 p0 left join SAP_PA0002 p2 on p0.PERNR = p2.PERNR
WHERE p0.STAT2 = 3 and p0.ENDDA > SYSDATE()
```

### SAP HCM と Entra Inbound Provisioning オプション

このセクションでは、SAP HCM から Microsoft Entra/オンプレミス Active Directory への受信プロビジョニングを実装するために SAP HCM のお客様が検討できるオプションについて説明します。 次のデシジョン ツリーを使用して、使用するオプションを決定します。

[Image: SAP HCM から Entra ID へのデシジョン ツリー ワークフローの図。]

### オプション 1: CSV ファイル ベースの受信プロビジョニング

#### この方法を使用する場合

このアプローチは、SAP HCM と SAP SuccessFactors の両方をサイド バイ サイドデプロイ モードで使用している場合に使用します。この場合、SAP SuccessFactors はまだ主要な HR データ ソースとして権限を持ち、運用できません。 このアプローチにより、価値を得る時間が短縮され、最終的に SAP SuccessFactors に移行する目的に合わせて調整されます。

**お客様が SAP HCM と SAP SuccessFactors の両方をデプロイしたシナリオは何ですか?**

SAP SuccessFactors にコア HR モジュールを移行する前に、パフォーマンスや目標、Learn などの補助的な HR モジュールを使用して SAP SuccessFactors のデプロイを開始するのが一般的です。 このシナリオでは、オンプレミスの SAP HCM システムは、従業員と組織のデータの権限のあるソースであり続けます。

**SAP SuccessFactors デプロイ 計画を使用している SAP HCM のお客様にのみ、このアプローチが推奨されるのはなぜですか?**

このサイド バイ サイドのデプロイ構成を持つお客様のみが、従業員データの CSV ファイルへの定期的なエクスポートを簡略化する [SAP HCM および SuccessFactors 用の SAP アドオン統合モジュール](https://help.sap.com/doc/87c19c94e71e4e389e5b1daea1942c72/3.0%20SP06/en-US/loio06b98261c1d34e67b554c9527d6a3565_06b98261c1d34e67b554c9527d6a3565.pdf) を使用できます。

#### 高レベルのデータ フローと構成手順

この図は、高レベルのデータ フローと構成手順を示しています。

[Image: SAP HCM から Entra ID への大まかなデータ フローの図。]

- **手順 1**: SAP HCM で、従業員データを含む CSV ファイルの定期的なエクスポートを構成します。 統合を初めて実行する場合は、初期同期に対して完全エクスポートを実行することをお勧めします。初期同期が完了したら、変更のみをキャプチャする増分エクスポートを実行できます。 エクスポートされた CSV ファイルは、暗号化された形式で SFTP サーバーまたは Azure ファイル共有に格納できます。
    - 参照：
        - [SAP ERP HCM からの従業員データのレプリケート](https://help.sap.com/doc/2eff62546be748739ca05477c2ab7ba7/2505/en-US/SF_ERP_EC_EE_Data_HCI_en-US.pdf)
        - [2214465 - SAP ERP HCM Add-On 3.0 の統合 - SAP for Me](https://me.sap.com/notes/0002214465) (SAP サポート ログインが必要)
        - **SBN Conference 2019** で発表された [SAP ERP HCM と SuccessFactors](https://sbn.no/2019_sbnconference) の統合について説明するスライド デッキ。 会議サイトからプレゼンテーションをダウンロードするには、 **1 日目 &gt; Wed 13:30-14:10 &gt; SAP ERP HCM と SuccessFactors Trygve Berg、Capgemini の統合**に移動します。

    注

    CSV ファイルには、差分 (または増分) データを含めることができます。
- **手順 2**: Microsoft Entra で、SAP HCM から従業員データを受信するように API 駆動型プロビジョニング アプリを構成します。
    - 参照：
        - [API 主導の受信プロビジョニングの概念](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)
        - [API ドリブンの受信プロビジョニング アプリを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)
- **手順 3**: CSV を復号化/読み取り、SCIM 一括ペイロードに変換し、手順 2 で構成した API エンドポイントにデータを送信するようにミドルウェア ツールを構成します。 CSV ファイルは、Azure File Share などのステージングの場所に格納できます。 不適切なデータが Entra に流れないように、ミドルウェア ツールに検証ロジックとサーキットブレーク ロジックを実装することをお勧めします。 たとえば、 `employeeType`が無効な場合は、レコードをスキップし、HR レコードの一定の割合に無効なデータがある場合は、一括アップロード操作を停止します。
    - 参照：
        - [PowerShell スクリプトを使用した API 主導の受信プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-powershell)
        - [Azure Logic Apps を使用した API 主導の受信プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-logic-apps)

#### デプロイのバリエーション

SAP が提供するアドオン統合モジュールにアクセスできない場合、または SuccessFactors を使用する予定がない場合でも、完全同期と増分同期の両方で CSV ファイルを定期的にエクスポートする SAP HCM でカスタム自動化を構築できるため、CSV アプローチを使用することはできます。

### オプション 2: SAP BAPI ベースの受信プロビジョニング

#### この方法を使用する場合

SAP SuccessFactors がデプロイされておらず、システムの制約またはデプロイの要件により、Azure Logic Apps を使用してスケジュールされた定期的な同期がソリューション アーキテクチャによって呼び出される場合は、このアプローチを使用します。 この統合では、 [Azure Logic Apps SAP コネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/sap)が使用されます。 Azure Logic Apps には、SAP 用の 2 種類のコネクタが付属しています。

- [SAP 組み込みコネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/built-in/reference/sap)。シングルテナント Azure Logic Apps の Standard ワークフローでのみ使用できます。
- マルチテナント Azure でホストおよび実行される [SAP マネージド コネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/sap)。 Standard ロジック アプリワークフローと従量課金ロジック アプリ ワークフローの両方で使用できます。

SAP の "組み込みコネクタ" には、この記事に記載されている理由から、マネージド コネクタよりも特定の利点があります。 たとえば、SAP 組み込みコネクタを使用すると、オンプレミスの接続ではオンプレミスのデータ ゲートウェイは必要ありません。また、専用アクションを使用すると、ステートフル BAPI と RFC トランザクションのエクスペリエンスが向上します。

#### 高レベルのデータ フローと構成手順

この図は、SAP BAPI ベースの受信プロビジョニングの概要データ フローと構成手順を示しています。

注

この図は、Azure Logic Apps SAP 組み込みコネクタのデプロイ コンポーネントを示しています。

**ネットワークに関する考慮事項**:

- Azure Logic Apps SAP マネージド コネクタを使用する場合は、オンプレミスの SAP デプロイと Logic App Standard ネットワークのピアリングを許可する Azure Express Route の使用を検討してください。 オンプレミス データ ゲートウェイ コンポーネントは、セキュリティの低下につながるため、推奨されません。
- SAP HCM システムが既に Azure で実行されている場合は、Logic Apps から SAP システムへの接続を同じ VNET で行うことができます (オンプレミス データ ゲートウェイは必要ありません)。

[Image: SAP BAPI ベースの受信プロビジョニングのデプロイ コンポーネントの概要データ フローの図。]

- **手順 1**: SAP 組み込みコネクタを使用するように SAP HCM の [前提条件](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/sap#prerequisites) を構成する。 これには、次の BAPI 汎用モジュールを呼び出す適切な権限を持つ SAP システム アカウントの設定が含まれます。 `RPY*`および`SWO*`機能モジュールを使用すると、使用可能なビジネスオブジェクトを一覧表示し、これらのオブジェクトに対してどの ABAP メソッドを使用するかを検出できる専用の BAPI アクションを使用できます。 入力出力の検出可能性とより具体的なメタデータを得る場合は、BAPI メソッドの RFC 実装を直接呼び出すよりも、これをお勧めします。

    - `RFC_READ_DATA`
    - `RFC_READ_TABLE`
    - `BAPI_USER_GETLIST`
    - `BAPI_USER_GET_DETAIL`
    - `BAPI_EMPLOYEE_GETDATA`
    - `RFC_METADATA`
        - `RFC_METADATA_GET`
        - `RFC_METADATA_GET_TIMESTAMP`
    - `RPY_BOR_TREE_INIT`
    - `SWO_QUERY_METHODS`
    - `SWO_QUERY_API_METHODS`

カスタム関数モジュールを定義している場合は、それらを一覧に含める必要があります。

- **手順 2**: Microsoft Entra で、SAP HCM から従業員データを受信するように API 駆動型プロビジョニング アプリを構成します。

    - 参照：
        - [API 主導の受信プロビジョニングの概念](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)
        - [API ドリブンの受信プロビジョニング アプリを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)
- **手順 3**: SAP の BAPI [呼び出しメソッド](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/built-in/reference/sap/#%5Bbapi%5D-call-method-in-sap)を介して適切な BAPI 関数モジュールを呼び出し、応答を処理し、SCIM ペイロードを構築し、Microsoft Entra プロビジョニング API エンドポイントに応答を送信するロジック アプリ ワークフローを構成します。 [API 駆動型プロビジョニングの使用制限](https://learn.microsoft.com/ja-jp/entra/id-governance/licensing-fundamentals#api-driven-provisioning)内に留まるベスト プラクティスとして、変更ごとに 1 つの SCIM 一括要求を送信するのではなく、複数の変更を 1 つの SCIM 一括要求にバッチ処理することをお勧めします。

    - 参照：
        - [Azure Logic Apps を使用した API 主導の受信プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-logic-apps)
- **手順 4**: Entra ID プロビジョニング ログ エンドポイントに対してクエリを実行して、プロビジョニング操作の状態を確認します。 成功した操作と再試行エラーを記録します。

#### デルタ インポート用のカスタム RFC を定義する

SAP GUI/ABAP Workbench でカスタム RFC を作成するには、次の手順を使用します。 このカスタム RFC は、特定の開始日から終了日までの間に変更されたユーザーの属性を返します。

1. **RFC 関数モジュールを定義する**

    a. トランザクション **SE37** を使用して、開始日 (`p_begda`) と終了日 (`p_endda`) を入力として受け入れるカスタム RFC 対応関数モジュールを作成する b。 関数モジュールで、関連するテーブル (例: PA0001、PA0002) に対してユーザー属性を照会するロジックを記述します。 c. 最後の変更のタイムスタンプに基づいてデータをフィルター処理します (例: `CHANGED_ON field`を使用)。
2. **ロジックを実装する**

    a. ABAP コードを使用して必要な属性をフェッチし、過去 1 時間以内に変更のフィルターを適用します。 b。 ABAP スニペットの例:

    ```abap
    FORM read_database USING p_begda p_endda. 
    
        IF lv_pernr IS INITIAL. 
    
    *-- > Employee list 
            SELECT pernr endda begda FROM pa0000 
                INTO TABLE 1t_pernr WHERE aedtm BETWEEN p_begda AND p_endda. 
    
    *-- > Org assignment details 
            SELECT pernr endda begda FROM pa0001 
                APPENDING TABLE 1t_pernr WHERE aedtm BETWEEN p_begda AND p_endda. 
    
    *-- > Personal Details 
            SELECT pernr endda begda FROM pa0002 
                APPENDING TABLE 1t_pernr WHERE aedtm BETWEEN p_begda AND p_endda. 
    
        ENDIF.
    
    ENDFORM.
    ```

    c. 関数モジュールが構造化形式でデータを返すようにします (例: 内部テーブル `1t_pernr`)。
3. **RFC アクセスを有効にする**

    a. 関数モジュールの属性で RFC 対応としてマークします。 b。 トランザクション SM59 を使用して RFC をテストし、リモートで呼び出すことができることを確認します。
4. **テストとデプロイ**

    a. RFC をローカルおよびリモートでテストして、その機能を確認します。 b。 ECC システムに RFC を展開し、その使用法を文書化します。
5. **Logic Apps で RFC 呼び出しを構成する**

    a. SAP ECC で作成した RFC 名など、SAP システムの詳細を指定します。[Image: SAP でのユーザー変更 RFC 関数の呼び出しのスクリーンショット。] b. RFC に必要なパラメーターを入力します (たとえば、前のスクリーンショットのパラメーター BEGDA は、前の実行に対応する Azure Blob Storage に格納されている透かしの日付を指し、ENDDA は現在のロジック アプリの実行の開始日です)。
6. RFC 呼び出しからの JSON 応答を解析し、それを使用して SCIM 一括要求ペイロードを作成します。 [Image: SAP の Call get user changes RFC 関数の出力のスクリーンショット。]

- 参照：
    - [Azure Logic Apps を使用した API 主導の受信プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-logic-apps)

### オプション 3: SAP IDocs ベースの受信プロビジョニング

#### この方法を使用する場合

SAP SuccessFactors がデプロイされておらず、ソリューション アーキテクチャがシステムの制約またはデプロイ要件のために Azure Logic Apps を使用してイベントベースの同期を呼び出す場合は、このアプローチを使用します。

この統合では、Azure Logic Apps SAP Connector が使用されます。 Azure Logic Apps には、SAP 用の 2 種類のコネクタが付属しています。

- [SAP 組み込みコネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/built-in/reference/sap)。シングルテナント Azure Logic Apps の Standard ワークフローでのみ使用できます。
- マルチテナント Azure でホストおよび実行される [SAP マネージド コネクタ](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/sap)。 Standard ロジック アプリワークフローと従量課金ロジック アプリ ワークフローの両方で使用できます。

SAP 組み込みコネクタには、 [この記事](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/sap#connector-differences)に記載されている理由から、マネージド コネクタよりも特定の利点があります。 たとえば、SAP 組み込みコネクタでは、オンプレミス接続ではオンプレミス データ ゲートウェイは必要ありません。IDoc 重複除去をサポートし、 [メッセージを受信したときに](https://learn.microsoft.com/ja-jp/azure/logic-apps/connectors/built-in/reference/sap#when-a-message-is-received)トリガーで IDoc ファイル形式を処理するためのサポートが強化されています。

#### 高レベルのデータ フローと構成手順

この図は、高レベルのデータ フローと構成手順を示しています。

注

この図は、Azure Logic Apps SAP 組み込みコネクタのデプロイ コンポーネントを示しています。

**ネットワークに関する考慮事項**:

- Azure Logic Apps SAP マネージド コネクタを使用する場合は、オンプレミスの SAP デプロイを使用した Logic Apps Standard ネットワークのピアリングを許可する Azure Express Route の使用を検討してください。 オンプレミス データ ゲートウェイ コンポーネントは、セキュリティの低下につながるため、推奨されません。
- SAP HCM システムが既に Azure で実行されている場合、Azure Logic Apps から顧客への接続は、(オンプレミス データ ゲートウェイを必要とせずに) 同じ VNET で行うことができます。 [Image: Azure Logic Apps SAP 組み込みコネクタのデプロイ コンポーネントの概要データ フローの図。]
- **手順 1**: Azure Logic Apps SAP 組み込みコネクタを使用するように SAP HCM の前提条件を構成する。 この手順には、BAPI 汎用モジュールと IDoc メッセージを呼び出すための適切な権限を持つ SAP システム アカウントの設定が含まれます。 SAP からロジック アプリ ワークフローへの IDoc の送信を設定してテストする手順を完了します。
- **手順 2**: Microsoft Entra で、SAP HCM から従業員データを受信するように API 駆動型プロビジョニング アプリを構成します。

    - 参照：
        - [API 主導の受信プロビジョニングの概念](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-concepts)
        - [API ドリブンの受信プロビジョニング アプリを構成する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/inbound-provisioning-api-configure-app)
- **手順 3**: メッセージの受信時にトリガーで開始されるロジック アプリ ワークフローを構築し、IDocs メッセージを処理し、SCIM ペイロードを作成して、Microsoft Entra プロビジョニング API エンドポイントに応答を送信します。 API 駆動型プロビジョニングの使用制限内に留まるベスト プラクティスとして、変更ごとに 1 つの SCIM 一括要求を送信するのではなく、複数の変更を 1 つの SCIM 一括要求にバッチ処理することをお勧めします。

    - 参照：
        - Azure Logic Apps を使用した API 主導の受信プロビジョニング
- **手順 4**: Entra ID プロビジョニング ログ エンドポイントに対してクエリを実行して、プロビジョニング操作の状態を確認します。 成功した操作と再試行エラーを記録します。

### SAP HCM への書き戻しを構成する

SAP HCM のワーカー レコードが Entra ID でプロビジョニングされた後、多くの場合、メールやユーザー名などの IT で管理される属性を SAP HCM に書き戻す必要があります。 このシナリオでは、カスタム Logic Apps 拡張機能と共に Microsoft Entra ID Governance -&gt; Joiner Lifecycle Workflow を使用することをお勧めします。 フローの概略は、次の図に示すとおりです。 [Image: Azure Logic Apps と SAP 組み込みコネクタを使用した Joiner ライフサイクル ワークフローの図。]

書き戻しを構成するには、次の手順に従います。

1. 入社日をトリガーするように Joiner ライフサイクル ワークフローを構成します。
2. Joiner ワークフローの一部としてカスタム Logic Apps 拡張機能を構成します。
3. この Logic Apps 拡張機能で、 `BAPI_USER_CHANGE` 関数を呼び出して、ユーザーのメール アドレスとユーザー名を更新します。

### 謝辞

この記事のレビューと投稿に関して、次のパートナーに感謝します。

- [Kocho](https://kocho.co.uk/)
- [iC の相談](https://ic-consult.com/en/)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-netweaver-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SAP NetWeaver を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-netweaver-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAP NetWeaver の間にシングル サインオンを構成する方法について説明します。

この記事では、SAP NetWeaver と Microsoft Entra ID を統合する方法について説明します。 SAP NetWeaver を Microsoft Entra ID と統合すると、次のことができます。

- SAP NetWeaver にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SAP NetWeaver に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP NetWeaver でのシングル サインオン (SSO) が有効なサブスクリプション。
- SAP NetWeaver V7.20 以降

### シナリオの説明

- SAP NetWeaver では、**SAML** (**SP Initiated SSO**) と **OAuth** の両方がサポートされます。 この記事では、SAML を使用して、テスト環境で SAP NetWeaver Microsoft Entra SSO を構成し、テストします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

注

自分の組織の要件に応じて SAML または OAuth でアプリケーションを構成してください。

### ギャラリーからの SAP NetWeaver の追加

Microsoft Entra ID への SAP NetWeaver の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAP NetWeaver を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SAP NetWeaver**」と入力します。
4. 結果パネルから **[SAP NetWeaver]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAP NetWeaver 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SAP NetWeaver に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SAP NetWeaver の関連ユーザーとの間にリンク関係を確立する必要があります。

SAP NetWeaver に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**して、ユーザーがシングル サインオンを使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成**し、B.Simon を使用して Microsoft Entra シングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当て**、B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAML を使用した SAP NetWeaver の構成**- アプリケーション側で SSO 設定を構成します。
    1. **SAP NetWeaver のテスト ユーザーの作成** - SAP NetWeaver で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。
4. **SAP NetWeaver の OAuth 向け構成** - アプリケーション側で OAuth 設定を構成します。
5. Id プロバイダー (IdP) として **Microsoft Entra ID を使用するように Microsoft Entra ID からアクセス トークンを要求**します。

### Microsoft Entra SSO の構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

SAP NetWeaver で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. 新しい Web ブラウザー ウィンドウを開き、SAP NetWeaver 企業サイトに管理者としてサインインします。
2. **http** および **https** サービスがアクティブであり、**SMICM** T-Code で適切なポートが割り当てられていることを確認します。
3. SAP システム (T01) のビジネス クライアントにサインオンします。SSO が必要であり、HTTP セキュリティ セッション管理をアクティブ化します。

    1. トランザクション コードの **SICF\_SESSIONS** に移動します。 すべての関連プロファイル パラメーターと現在の値が表示されます。 次のように表示されます。

        ```
        login/create_sso2_ticket = 2
        login/accept_sso2_ticket = 1
        login/ticketcache_entries_max = 1000
        login/ticketcache_off = 0  login/ticket_only_by_https = 0 
        icf/set_HTTPonly_flag_on_cookies = 3
        icf/user_recheck = 0  http/security_session_timeout = 1800
        http/security_context_cache_size = 2500
        rdisp/plugin_auto_logout = 1800
        rdisp/autothtime = 60
        ```

        注

        組織の要件に合わせて上記のパラメーターを調整します。上記のパラメーターは参考用にのみ示してあります。
    2. 必要に応じて SAP システムのインスタンスまたは既定プロファイルでパラメーターを調整し、SAP システムを再起動します。
    3. 関連するクライアントをダブル選択して、HTTP セキュリティ セッションを有効にします。

        [Image: HTTP セキュリティ セッション]
    4. 以下の SICF サービスをアクティブ化します。

        ```
        /sap/public/bc/sec/saml2
        /sap/public/bc/sec/cdc_ext_service
        /sap/bc/webdynpro/sap/saml2
        /sap/bc/webdynpro/sap/sec_diag_tool (This is only to enable / disable trace)
        ```
4. SAP システム [T01/122] のビジネス クライアントでトランザクション コード **SAML2** に移動します。 ブラウザーでユーザー インターフェイスが開きます。 この例では、SAP ビジネス クライアントとして 122 を想定しています。

    [Image: トランザクション コード]
5. ユーザー インターフェイスに入力するユーザー名とパスワードを入力し、[ **編集]** を選択します。

    [Image: ユーザー名とパスワード]
6. **プロバイダー名**を T01122 から`http://T01122`に置き換え、[**保存]** を選択します。

    注

    既定ではプロバイダー名は `<sid><client>` という形式ですが、Microsoft Entra ID では `<protocol>://<name>` という形式の名前が想定されています。プロバイダー名は `https://<sid><client>` のままにして、Microsoft Entra ID で複数の SAP NetWeaver ABAP エンジンを構成できるようにすることをお勧めします。

    [Image: 複数の SAP NetWeaver ABAP エンジン]
7. **サービス プロバイダー メタデータの生成**:- SAML 2.0 ユーザー インターフェイスで **ローカル プロバイダー** と **信頼されたプロバイダー** の設定を構成したら、次の手順は、サービス プロバイダーのメタデータ ファイル (SAP のすべての設定、認証コンテキスト、およびその他の構成を含む) を生成することです。 このファイルが生成されたら、このファイルを Microsoft Entra ID にアップロードします。

    [Image: サービス プロバイダーのメタデータを生成する]

    1. **[Local Provider](ローカル プロバイダー)** タブに移動します。
    2. **[メタデータ]** を選択します。
    3. 生成された**メタデータ XML ファイル**をコンピューターに保存し、それを Azure portal の **[基本的な SAML 構成]** セクションにアップロードして、 **[識別子]** と **[応答 URL]** の値を自動的に設定します。

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SAP NetWeaver** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    1. [ **メタデータ ファイルのアップロード** ] を選択して、" **サービス プロバイダー メタデータ** の生成" 手順で生成したサービス プロバイダー メタデータ ファイルをアップロードします。
    2. **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。
    3. メタデータ ファイルが正常にアップロードされると、次に示すように、**識別子**と**応答 URL** の値が、 **[基本的な SAML 構成]** セクションのテキスト ボックスに自動的に設定されます。
    4. **[サインオン URL]** ボックスに、`https://<your company instance of SAP NetWeaver>` という形式で URL を入力します。

    注

    一部のお客様からは、インスタンスに対して構成された応答 URL に誤りがあるというエラーの報告を受けています。 このようなエラーが発生した場合は、これらの PowerShell コマンドを使用します。 まず、アプリケーション オブジェクト内の応答 URL をこの応答 URL で更新してから、サービス プリンシパルを更新します。 [Get-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) を使用して、サービス プリンシパル ID の値を取得します。

    ```powershell
    $params = @{
       web = @{
          redirectUris = "<Your Correct Reply URL>"
       }
    }
    Update-MgApplication -ApplicationId "<Application ID>" -BodyParameter $params
    Update-MgServicePrincipal -ServicePrincipalId "<Service Principal ID>" -ReplyUrls "<Your Correct Reply URL>"
    ```
6. SAP NetWeaver アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。 **[編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: 属性を編集する]
7. **[ユーザー属性]** ダイアログの **[ユーザーの要求]** セクションで、上の図のように SAML トークン属性を構成し、次の手順を実行します。

    1. [ **編集] アイコン** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

        [Image: [編集] アイコン]

        [Image: 画像]
    2. **[変換]** の一覧で、**ExtractMailPrefix()** を選択します。
    3. **[パラメーター 1]** の一覧で、**user.userprincipalname** を選択します。
    4. **保存** を選択します。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
9. **[SAP NetWeaver の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### SAML を使用した SAP NetWeaver の構成

シングル サインオンに SAML を使用するように SAP NetWeaver を構成するには、次の手順に従います。

1. SAP システムにサインインし、トランザクション コード SAML2 に移動します。 新しいブラウザー ウィンドウで SAML 構成画面が開きます。
2. 信頼できる ID プロバイダー (Microsoft Entra ID) のエンド ポイントを構成するには、**[Trusted Providers](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/信頼できるプロバイダー)** タブに移動します。

    [Image: シングル サインオンの構成 (信頼できるプロバイダー)]
3. **[Add](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加)** をクリックして、コンテキスト メニューから **[Upload Metadata File](メタデータ ファイルのアップロード)** を選択します。

    [Image: シングルサインオン2の構成]
4. **[SAML 署名証明書**] セクションからダウンロードしたMicrosoft Entraフェデレーション メタデータ XML ファイルをアップロードします。

    [Image: シングル サインオン設定 3]
5. 次の画面で、エイリアス名を入力します。 たとえば `aadsts` と入力して、**[次へ]** を押して次に進みます。

    [Image: シングル サインオンの構成 4]
6. **[Digest Algorithm](ダイジェスト アルゴリズム)** が **[SHA-256]** であることを確認します。何も変更する必要はありません。**[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ)** をクリックします。

    [Image: シングル サインオン 5 の構成]
7. **[Single Sign-On Endpoints](シングル サインオン エンドポイント)** で、**[HTTP POST]** を使用し、**[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ)** を選んで続行します。

    [Image: シングル サインオンの構成 6]
8. **シングル ログアウト エンドポイント**で **HTTPRedirect** を選択し、[**次へ**] を選択して続行します。

    [Image: シングルサインオン7の構成]
9. **[Artifact Endpoints](アーティファクト エンドポイント)** では、**[Next](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/次へ)** をクリックして続行します。

    [Image: シングル サインオンの構成 8]
10. **[認証要件**] で、[**完了]** を選択します。

    [Image: シングル サインオンの構成 9]
11. **[Trusted Providers](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/信頼できるプロバイダー)**&gt;**[Identity Federation](ID フェデレーション)** タブ (画面下部) に移動します。 **[編集]** を選択します。

    [Image: シングル サインオンの構成 10]
12. [**ID フェデレーション**] タブ (下部ウィンドウ) で [**追加]** を選択します。

    [Image: シングル サインオンの構成 11]
13. ポップアップ ウィンドウで、[**サポートされている NameID 形式** **] から [未指定**] を選択し、[OK] を選択します。

    [Image: シングルサインオンの構成 12]
14. **[User ID Source](ユーザー ID ソース)** の値として「**Assertion Attribute**」を、 **[User ID mapping mode](ユーザー ID マッピング モード)** の値として「**Email**」を、 **[Assertion Attribute Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アサーション属性名)** の値として「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`」を指定します。

    [Image: シングルサインオンの設定]
15. **[User ID Source](ユーザー ID ソース)** と **[User ID mapping mode](ユーザー ID マッピング モード)** の値によって、SAP ユーザーと Microsoft Entra 要求の間のリンクが決まることに注意してください。

**シナリオ: SAP ユーザーから Microsoft Entra ユーザーへのマッピング。**

1. SAP の NameID 詳細スクリーンショット。

    [Image: シングル サインオンの構成 13]
2. Microsoft Entra ID からの必須要求に関するスクリーンショット。

    [Image: シングル サインオンの構成 14]

    **シナリオ:SU01 で構成済みのメール アドレスに基づいて SAP ユーザー ID を選択する。 このケースでは、SSO を必要とする各ユーザーの su01 でメール ID を構成する必要があります。**

    1. SAP の NameID 詳細スクリーンショット。

        [Image: シングル サインオンの構成 15]
    2. Microsoft Entra ID からの必須要求に関するスクリーンショット。

    [Image: シングルサインオン 16 の構成]
3. **[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存)** を 選んでから **[Enable](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/有効)** を選び、ID プロバイダーを有効にします。

    [Image: シングル サインオンの構成 17]
4. メッセージが表示されたら **、[OK] を選択します** 。

    [Image: シングルサインオンを構成する - 18]

#### SAP NetWeaver のテスト ユーザーの作成

このセクションでは、SAP NetWeaver で B.simon というユーザーを作成します。 社内の SAP 専門家チームまたは組織の SAP パートナーと協力して、SAP NetWeaver プラットフォームにユーザーを追加してください。

### SSO のテスト

シングル サインオンが正しく機能していることを確認するには、次の手順に従います。

1. ID プロバイダーの Microsoft Entra ID がアクティブ化されたら、以下の URL にアクセスして SSO を確認し、ユーザー名とパスワードの入力を求めないことを確認してください。

    `https://<sapurl>/sap/bc/bsp/sap/it00/default.htm`

    (または) 下記の URL を使用します

    `https://<sapurl>/sap/bc/bsp/sap/it00/default.htm`

    注

    sapurl は実際の SAP のホスト名に置き換えます。
2. 上記の URL により、下の画面に移動するはずです。 以下のページまでアクセスできる場合は、Microsoft Entra SSO のセットアップが正常に完了します。

    [Image: シングル サインオンのテスト]
3. ユーザー名とパスワードのプロンプトが表示された場合は、以下のように URL を使用してトレースを有効にすることで、問題を診断できます。

    `https://<sapurl>/sap/bc/webdynpro/sap/sec_diag_tool?sap-client=122&sap-language=EN#`

### SAP NetWeaver の OAuth 向け構成

OAuth 用に SAP NetWeaver を構成するには、次の手順を実行します。

1. SAP によって文書化されたプロセスが「[NetWeaver Gateway サービスの有効化と OAuth 2.0 スコープの作成](https://wiki.scn.sap.com/wiki/display/Security/NetWeaver+Gateway+Service+Enabling+and+OAuth+2.0+Scope+Creation)」に記載されています
2. SPRO に移動し、**[Activate and Maintain services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サービスのアクティブ化と管理)** を探します。

    [Image: サービスのアクティブ化と管理]
3. この例では、MICROSOFT ENTRA IDを ID プロバイダーとして使用して OData サービス (`DAAG_MNGGRP`) を OAuth に接続します。 テクニカル サービス名の検索を使用して、`DAAG_MNGGRP` というサービスを探し、まだアクティブ ([ICF nodes](ICF ノード) タブで `green` 状態を確認) になっていない場合はアクティブ化します。 システム エイリアス (サービスが実際に実行される接続先バックエンド システム) が正しいことを確認してください。

    [Image: OData サービス]

    - 次に、上部のボタン バーでプッシュ ボタン **OAuth** を選択し、 `scope` を割り当てます (既定の名前をそのまま使用します)。
4. この例では、スコープは `DAAG_MNGGRP_001` です。 番号を自動的に追加することで、サービス名から生成されます。 レポート `/IWFND/R_OAUTH_SCOPES` は、スコープの名前の変更または作成を手動で行うために使用できます。

    [Image: OAuth の構成]

    注

    `soft state status isn't supported` というメッセージは問題ないので無視してかまいません。

#### OAuth 2.0 クライアントのサービス ユーザーを作成する

1. OAuth2 では、`service ID` を使用してエンドユーザーのアクセス トークンを代わりに取得します。 OAuth の設計上の重要な制限として、`OAuth 2.0 Client ID` は、OAuth 2.0 クライアントがアクセス トークンを要求する際のログインに使用される `username` と一致している必要があります。 したがって、この例では、名前CLIENT1を使用して OAuth 2.0 クライアントを登録します。 前提条件として、同じ名前 (CLIENT1) を持つユーザーが SAP システムに存在する必要があり、OAuth 2.0 クライアント アプリケーションで使用されるように構成します。
2. OAuth クライアントを登録する際は、`SAML Bearer Grant type` を使用します。

    注

    詳細については、[SAMLベアラー・グラントタイプの OAuth 2.0 クライアント登録](https://wiki.scn.sap.com/wiki/display/Security/OAuth+2.0+Client+Registration+for+the+SAML+Bearer+Grant+Type)を参照してください。
3. T-Code `SU01` を実行してユーザー CLIENT1 を `System type` として作成し、パスワードを割り当てます。 API プログラマに資格情報を提供する必要があるため、パスワードを保存します。このパスワードは、呼び出し元のコードにユーザー名を付けて保存する必要があります。 プロファイルやロールを割り当てる必要はありません。

#### 新しい OAuth 2.0 クライアント ID を作成ウィザードで登録する

SAP に新しい OAuth 2.0 クライアント ID を登録するには、次の手順に従います。

1. 新しい **OAuth 2.0 クライアント**を登録するために、トランザクション **SOAUTH2** を開始します。 このトランザクションは、既に登録されている OAuth 2.0 クライアントについての概要を表示します。 この例では CLIENT1 という名前の新しい OAuth クライアントのために、**[作成]** を選択してウィザードを起動します。
2. T-Code: **SOAUTH2** に移動し、説明を入力して **次を選択します**。

    [Image: SOAUTH2]

    [Image: OAuth 2.0 クライアント ID]
3. あらかじめ追加されている **[SAML2 IdP - Microsoft Entra ID]** をドロップダウン リストから選択して保存します。

    [Image: SAML2 IdP – Microsoft Entra ID 1]

    [Image: SAML2 IdP – Microsoft Entra ID 2]

    [Image: SAML2 IdP – Microsoft Entra ID 3]
4. [スコープの割り当て] で [ **追加]** を選択して、以前に作成したスコープを追加します。 `DAAG_MNGGRP_001`

    [Image: スコープ]

    [Image: スコープの割り当て]
5. **[完了]** を選択します。

### Microsoft Entra ID からアクセス トークンを要求する

Microsoft Entra ID (旧称 Azure AD) を ID プロバイダー (IdP) として使用して SAP システムからアクセス トークンを要求するには、次の手順に従います。

#### 手順 1: Microsoft Entra ID でアプリケーションを登録する

次の手順を実行して、アプリケーションをMicrosoft Entra IDに登録します。

1. **Azure portal にログインします**: https://portal.azure.comの Azure portal に移動します。
2. **新しいアプリケーションを登録する**:
    - [Microsoft Entra ID] に移動します。
    - [アプリの登録] &gt; [新しい登録] の順に選びます。
    - 名前、リダイレクト URI などのアプリケーションの詳細を入力します。
    - [登録] を選択します。
3. **API のアクセス許可を構成する**
    - 登録後、[API アクセス許可] に移動します。
    - [アクセス許可の追加] を選択し、[組織が使用する API] を選択します。
    - SAP システムまたは関連する API を検索し、必要なアクセス許可を追加します。
    - アクセス許可に対して管理者の同意を付与する。

#### 手順 2: クライアント シークレットを作成する

次のように、登録済みアプリケーションのクライアント シークレットを作成します。

1. **登録済みアプリケーションに移動します**: [証明書とシークレット] に移動します。
2. **新しいクライアント シークレットを生成する**:
    - [新しいクライアント シークレット] を選択します。
    - 説明を入力し、有効期限を設定します。
    - [追加] を選択し、認証に必要なクライアント シークレットの値をメモします。

#### 手順 3: SAP System for Microsoft Entra ID 統合を構成する

次の手順に従って、Microsoft Entra IDを信頼するように SAP システムを構成します。

1. **SAP クラウド プラットフォームにアクセス**: SAP Cloud Platform コックピットにログインします。
2. **信頼の構成を設定する**:
    - [セキュリティ] &gt; [信頼の構成] に移動します。
    - Microsoft Entra ID からフェデレーション メタデータ XML をインポートして、信頼できる IdP として Microsoft Entra ID を追加します。 これは、Microsoft Entra ID アプリ登録の [エンドポイント] セクション ([フェデレーション メタデータ ドキュメント] の下) にあります。
3. **OAuth2 クライアントを構成します**:
    - SAP システムで、Microsoft Entra ID から取得したクライアント ID とクライアント シークレットを使用して OAuth2 クライアントを構成します。
    - トークン エンドポイントとその他の関連する OAuth2 パラメーターを設定します。

#### 手順 1: アクセス トークンを要求する

ヒント

Azure API Management を使用して、スマート トークン キャッシュ、セキュリティで保護されたトークン処理、要求調整などのガバナンス オプションを含む、Azure、Power Platform、Microsoft 365 などのすべてのクライアント アプリの SAP プリンシパル伝達プロセスを 1 か所で効率化することを検討してください。 [Azure API Management を使用した SAP プリンシパル伝達の詳細情報](https://community.powerplatform.com/blogs/post/?postid=c6a609ab-3556-ef11-a317-6045bda95bf0)。 SAP Business Technology Platform が推奨される場合は、[SAP Integration Suite を使用したローコード ソリューションとMicrosoftの統合](https://community.sap.com/t5/enterprise-resource-planning-blogs-by-members/integrating-low-code-solutions-with-microsoft-using-sap-integration-suite/ba-p/13789298)に関するセクションを参照してください。

1. **トークン要求を準備する**:

    - 次の詳細を使用してトークン要求を作成します。
        - **トークン エンドポイント**: 通常、これは `https://login.microsoftonline.com/{tenant}/oauth2/v2.0/token` です。
        - **クライアント ID**: Microsoft Entra ID からのアプリケーション (クライアント) ID。
        - **クライアント シークレット**: Microsoft Entra ID からのクライアント シークレット値。
        - **スコープ**: 必要なスコープ (例: `https://your-sap-system.com/.default`)。
        - **付与タイプ**: サーバー間認証に `client_credentials` を使用します。
2. **トークン要求を行う**:

    - Postman やスクリプトのようなツールを使用して、POST 要求をトークン エンドポイントに送信します。
    - 要求の例 (cURL 内):

        ```sh
        curl -X POST \
          https://login.microsoftonline.com/{tenant}/oauth2/v2.0/token \
          -H 'Content-Type: application/x-www-form-urlencoded' \
          -d 'client_id={client_id}&scope=https://your-sap-system.com/.default&client_secret={client_secret}&grant_type=client_credentials'
        ```
3. **アクセス トークンを抽出します**:

    - 要求が成功した場合、応答にはアクセス トークンが含まれます。 このアクセス トークンを使用して、SAP システムに対する API 要求を認証します。

#### 手順 5: API 要求にアクセス トークンを使用する

後続の API 要求では、次のようにアクセス トークンを使用します。

1. **API 要求にアクセス トークンを含めます**:
    - SAP システムへの要求ごとに、 `Authorization` ヘッダーにアクセス トークンを含めます。
    - ヘッダーの例:

        ```
        Authorization: Bearer {access_token}
        ```

### SAML2 と OAuth2 用の SAP NetWeaver 用のエンタープライズ アプリを同時に構成する

SSO 用の SAML2 と API アクセス用の OAuth2 の並列使用では、両方のプロトコルに対して Microsoft Entra ID で同じエンタープライズ アプリを構成できます。

一般的なセットアップでは、SSO の場合は SAML2、API アクセスの場合は OAuth2 が既定で使用されます。

[Image: SAML2 と OAuth2 の並列使用の構成を示す Azure portal のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-s4hana-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して SAP S/4HANA へのユーザー プロビジョニングを自動化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-s4hana-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-10-01
- Summary: SAP Cloud Identity Services を使用して、Microsoft Entra ID から SAP S/4HANA に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SAP Cloud Identity Services と Microsoft Entra ID で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスと SAP Cloud Identity Services を使用して、SAP S/4HANA に対するユーザーのプロビジョニングとプロビジョニング解除が自動的に行われます。 Microsoft Entra プロビジョニングが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされている機能

- SAP S/4HANA でユーザーを作成し、SAP S/4HANA へのシングル サインオンを有効にする
- アクセスが不要になった場合に SAP S/4HANA のユーザーを削除する
- Microsoft Entra ID と SAP S/4HANA の間でユーザー属性の同期を維持する

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP S/4HANA Cloud、プライベート エディション、SAP S/4HANA Cloud、パブリック エディション、または SAP S/4HANA オンプレミス
- SAP Cloud Identity Services のテナント
- 管理者アクセス許可がある SAP Identity Provisioning の管理コンソールのユーザー アカウント。 Identity Provisioning 管理コンソールのプロキシ システムにアクセスできることを確認します。 **[プロキシ システム]** タイルが表示されない場合は、このタイルへのアクセスを要求するコンポーネント **BC-IAM-IPS** のインシデントを作成します。

### 手順 1:プロビジョニングのデプロイを計画する

Microsoft Entra には、SAP ECC、SAP Cloud Identity Services、SAP SuccessFactors へのコネクタがあります。 SAP S/4HANA またはその他のアプリケーションへのプロビジョニングでは、ユーザーはまず Microsoft Entra ID に存在する必要があります。 Microsoft Entra ID にユーザーを作成すると、それらのユーザーを Microsoft Entra ID から SAP Cloud Identity Services にプロビジョニングできます。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内の Microsoft Entra ID から送信されたユーザーを、SAP クラウド コネクタなどを介した [`SAP S/4HANA Cloud`](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-sap-s-4hana-cloud) や [`SAP S/4HANA On-Premise`](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-sap-s-4hana-on-premise) その他を含むダウンストリーム SAP アプリケーションにプロビジョニングします。

[Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

### 手順 2: Microsoft Entra ID に適切なユーザーがあることを確認する

その後、SuccessFactors からの HR 受信を使用して、従業員の参加、移動、離脱時に Microsoft Entra ID のユーザーの一覧を最新の状態に保つことができます。 グループまたはアプリケーション ロールの割り当てを使用して、SAP S/4HANA にアクセスできるユーザーまたは SAP S/4HANA にアクセスできるロールのスコープを設定する予定で、テナントにMicrosoft Entra ID ガバナンスのライセンスがある場合は SAP Cloud Identity Services または SAP S/4HANA を表すアプリケーションの Microsoft Entra ID で、[アプリケーション ロールの割り当てに対する変更を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#assign-users-the-necessary-application-access-rights-in-microsoft-entra)することもできます。 プロビジョニング前に職務の分離やその他のコンプライアンス チェックを実行する方法の詳細については、「[アクセス ライフサイクル管理シナリオの移行](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/migrate-from-sap-idm#migrate-access-lifecycle-management-scenarios)」を参照してください。

SAP アプリケーションをターゲットとする ID ライフサイクルの詳細なガイダンスについては、「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー ID プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

### 手順 3: Microsoft Entra ID から SAP Cloud Identity Services へのプロビジョニングを構成する

SAP Cloud Identity Services と統合された SAP S/4HANA や他のアプリケーションへのユーザーのプロビジョニングを準備するには、SAP Cloud Identity Services にそれらのアプリケーションに必要なスキーマ マッピングがあることを確認します。 次に、[Microsoft Entra ID から SAP Cloud Identity Services へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#provision-users-to-sap-cloud-identity-services)を構成します。 SAP Cloud Identity Services はその後、必要に応じてダウンストリーム SAP アプリケーションにユーザーをプロビジョニングします。

Microsoft Entra から SAP Cloud Identity Services にユーザーをプロビジョニングするには、2 つの方法があります。

- SAP S/4HANA クラウドのロールにユーザーを割り当てるなど、Microsoft Entra ID のグループを使用している場合は、SAP Cloud Identity Services プロビジョニングを使用します。 まず、SAP Analytics Cloud で使用される SAP ビジネス ロール用の Microsoft Entra グループを作成します。 次に、SAP Cloud Identity Services のプロビジョニングで、[Microsoft Entra ID をソースとして構成](https://help.sap.com/docs/identity-provisioning/identity-provisioning/microsoft-azure-active-directory)し、ユーザーとグループを Microsoft Entra ID から SAP Cloud Identity Services に取り込み、作成されたグループを SAP ビジネス ロールにマップします。 詳細については、SAP のドキュメントの「[Provision users from Microsoft Azure AD to SAP Cloud Identity Services - Identity Authentication (Microsoft Azure AD から SAP Cloud Identity Services にユーザーをプロビジョニングする - Identity Authentication)](https://blogs.sap.com/2022/02/04/provision-users-from-microsoft-azure-ad-to-sap-cloud-identity-services-identity-authentication/)」を参照してください。
- または、Microsoft Entra ID でグループを使用する必要がない場合は、Microsoft Entra プロビジョニング サービスを使用できます。 このシナリオでは、SAP S/4HANA を表すアプリケーションを作成し、SAP S/4HANA へのアクセスを必要とするユーザーをそのアプリケーションに割り当てます。 次に、[Microsoft Entra ID を使用した、SAP Cloud Identity Services への自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)を構成します。 これらのユーザーが SAP Cloud Identity Services にプロビジョニングされるまで待ち、SAP S/4HANA ターゲットに必要な属性があることを確認します。

注

小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 ユーザーが SAP ダウンストリーム ターゲットで適切なアクセス権を持っていること、サインイン時に適切なロールを持っていることを確認します。

### 手順 4: SAP Cloud Identity Services から SAP S/4HANA へのプロビジョニングを構成する

この手順では、SAP Cloud Identity Services Identity Provisioning を使用して SAP S/4HANA をターゲット システムとして構成します。このシステムでは、ユーザーとグループ メンバーをプロビジョニングできます。 SAP S/4HANA Cloud については、[SAP S/4HANA クラウドへのプロビジョニングに関する SAP ドキュメント](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-sap-s-4hana-cloud)を参照してください。 SAP S/4HANA オンプレミスおよび SAP S/4HANA Cloud のプライベート エディションについては、[SAP S/4HANA オンプレミス](https://help.sap.com/docs/identity-provisioning/identity-provisioning/target-sap-s-4hana-on-premise)を参照してください。

### 手順 5: シングル サインオンの構成

SAP アプリケーションのユーザーのプロビジョニングを設定すると、それらのアプリケーションとのシングル サインオンを有効にする必要があります。 Microsoft Entra ID は、SAP アプリケーションの ID プロバイダーおよび認証機関として機能することができます。 [Microsoft Entra シングル サインオン (SSO) と SAP Cloud Identity Services との統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)をまだ構成していない場合は、構成します。

SAP SaaS や最新のアプリにシングル サインオンを構成する方法の詳細は、「[SSO の有効化](https://learn.microsoft.com/ja-jp/entra/id-governance/sap#enable-sso)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial"} -->
## Microsoft Entra ID で SuccessFactors 受信プロビジョニングを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-06
- Summary: SuccessFactors から Microsoft Entra ID へのインバウンド プロビジョニングを構成する方法について説明します

この記事の目的は、SuccessFactors Employee Central から Microsoft Entra ID にワーカー データをプロビジョニングするために実行する必要がある手順を示し、オプションで SuccessFactors にメール アドレスを書き戻します。

注

SuccessFactors からプロビジョニングするユーザーが、オンプレミスの AD アカウントを必要としないクラウド専用ユーザーである場合は、この記事を使用します。 ユーザーがオンプレミスの AD アカウントのみ、または AD と Microsoft Entra アカウントの両方を必要とする場合は、 [SAP SuccessFactors から Active Directory への](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial#overview) ユーザー プロビジョニングの構成に関する記事を参照してください。

次のビデオでは、SAP SuccessFactors とのプロビジョニング統合を計画するときに必要な手順の簡単な概要について説明します。

### 概要

[Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)は、ユーザーの ID ライフ サイクルを管理するために [SuccessFactors Employee Central](https://www.successfactors.com/products-services/core-hr-payroll/employee-central.html) と統合されます。

Microsoft Entra のユーザー プロビジョニング サービスでサポートされている SuccessFactors ユーザー プロビジョニング ワークフローは、次の人事管理および ID ライフサイクル管理シナリオを自動化します。

- **新入社員の雇用** - 新しい従業員が SuccessFactors に追加されると、ユーザー アカウントは Microsoft Entra ID で自動的に作成され、必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)で、電子メール アドレスが SuccessFactors に書き戻されます。
- **従業員の属性とプロファイルの更新** - SuccessFactors で従業員レコード (名前、タイトル、マネージャーなど) が更新されると、ユーザー アカウントは自動的に Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされるその他の SaaS アプリケーションに更新されます](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)。
- **従業員の退職** - SuccessFactors で従業員が退職すると、ユーザー アカウントは Microsoft Entra ID で自動的に無効になり、必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされているその他の SaaS アプリケーションが無効](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)になります。
- **従業員の再雇用** - SuccessFactors で従業員が再雇用されると、古いアカウントを Microsoft Entra ID と、必要に応じて Microsoft 365 および Microsoft [Entra ID でサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に自動的に再アクティブ化または再プロビジョニングできます (ユーザーの好みに応じて)。

#### このユーザー プロビジョニング ソリューションが最適な場合

この SuccessFactors から Microsoft Entra へのユーザー プロビジョニング ソリューションは、次の場合に最適です。

- SuccessFactors のユーザー プロビジョニングに、あらかじめ用意されたクラウドベースのソリューションを求める組織 (SAP Integration Suite を使って SAP HCM から SuccessFactors にデータを取り込む組織など)
- [MICROSOFT Entra を使用して SAP ソースアプリとターゲット アプリを使用してユーザー プロビジョニング用に Microsoft Entra をデプロイ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)している組織は、ワーカーの ID を設定して、SAP ECC や SAP S/4HANA などの 1 つ以上の SAP アプリケーション、および必要に応じて SAP 以外のアプリケーションにサインインできるようにする
- SuccessFactors から Microsoft Entra ID への直接ユーザー プロビジョニングを必要とするが、それらのユーザーが Windows Server Active Directory に含まれる必要がない組織
- [SuccessFactors Employee Central (EC)](https://www.successfactors.com/products-services/core-hr-payroll/employee-central.html) から取得したデータを使用してユーザーをプロビジョニングする必要がある組織
- 電子メールに Microsoft 365 を使用している組織

### ソリューション アーキテクチャ

このセクションでは、クラウド専用のユーザーに向けた、エンド ツー エンドのユーザー プロビジョニング ソリューションのアーキテクチャについて説明します。 2 つの関連するフローがあります。

- **権限のある HR データ フロー – SuccessFactors から Microsoft Entra ID へ:** このフローワーカー イベント (新入社員、異動、退職など) は、最初にクラウド SuccessFactors Employee Central で発生し、次にイベント データが Microsoft Entra ID に流れます。 イベントによっては、Microsoft Entra ID での作成/更新/有効化/無効化の操作に至る可能性があります。
- **電子メール ライトバック フロー – Microsoft Entra ID から SuccessFactors へ:** Microsoft Entra ID でアカウントの作成が完了すると、Microsoft Entra ID で生成された電子メール属性値または UPN を SuccessFactors に書き戻すことができます。

    [Image: 概要]

#### エンド ツー エンドのユーザー データ フロー

1. 人事チームは、SuccessFactors Employee Central で社員のトランザクション (参加者/異動者/休暇者または新規雇用/移動/退職) を実行します。
2. Microsoft Entra プロビジョニング サービスは、SuccessFactors EC からの、スケジュールされた ID の同期を実行し、Microsoft Entra ID との同期のために処理する必要がある変更を識別します。
3. Microsoft Entra プロビジョニング サービスは、変更を特定し、Microsoft Entra ID のユーザーに対する作成、更新、有効化、無効化の操作を呼び出します。
4. [SuccessFactors ライトバック アプリ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial)が構成されている場合、ユーザーの電子メール アドレスは Microsoft Entra ID から取得されます。
5. Microsoft Entra プロビジョニング サービスは、使用された一致する属性に基づいて、メール属性を SuccessFactors に書き戻します。

### デプロイの計画

SuccessFactors から Microsoft Entra ID へのクラウド人事駆動型のユーザー プロビジョニングを構成するには、次のようなさまざまな側面をカバーするかなりの計画が必要です。

- 一致する ID の決定
- 属性マッピング
- 属性の変換
- スコープ フィルター

ID の照合、属性マッピング、属性変換、スコープ フィルターに関する包括的なガイドラインについては、 [クラウド人事デプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision) を参照してください。 サポートされているエンティティ、処理の詳細、さまざまな人事シナリオに合わせて統合をカスタマイズする方法については、 [SAP SuccessFactors 統合リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference) を参照してください。

### 統合のための SuccessFactors の構成

すべての SuccessFactors プロビジョニング コネクタの一般的な要件は、SuccessFactors OData API を呼び出すための適切なアクセス許可を持つ SuccessFactors アカウントの資格情報が必要なことです。 次の手順では、SuccessFactors でサービス アカウントを作成し、必要なアクセス許可を付与する方法について説明します。

- SuccessFactors で API ユーザー アカウントを作成/識別する
- API アクセス許可ロールを作成する
- API ユーザーのアクセス許可グループを作成する
- アクセス許可グループにアクセス許可ロールを付与する

#### SuccessFactors で API ユーザー アカウントを作成または識別する

SuccessFactors 管理チームまたは実装パートナーと協力して、OData API を呼び出すための SuccessFactors のユーザー アカウントを作成または識別します。 Microsoft Entra ID でプロビジョニング アプリを構成するときに、このアカウントのユーザー名とパスワードの資格情報が必要です。

#### API アクセス許可ロールを作成する

1. Admin Center にアクセスできるユーザーアカウントで SAP SuccessFactors にログインします。
2. [ *アクセス許可ロールの管理*] を検索し、検索結果から [ **アクセス許可ロールの管理** ] を選択します。 [Image: アクセス許可ロールの管理]
3. アクセス許可ロールの一覧で、[ **新規作成**] を選択します。

[Image: 新しいアクセス許可ロールの作成]
4. 新しいアクセス許可 **ロールのロール名** と **説明** を追加します。 名前と説明では、このロールが API 使用アクセス許可されていることを示す必要があります。
5. [アクセス許可の設定] で [ **アクセス許可...**] を選択し、アクセス許可の一覧を下にスクロールし、[ **統合ツールの管理**] を選択します。 [ **管理者が基本認証を使用して OData API にアクセスできるようにする**] チェック ボックスをオンにします。

[Image: 統合ツールの管理]
6. 同じボックスを下にスクロールし、[ **Employee Central API**] を選択します。 次に示すように、ODATA API を使用して読み取り、ODATA API を使用して編集するためのアクセス許可を追加します。 SuccessFactors への書き戻しシナリオに同じアカウントを使用する場合は、編集オプションを選択してください。

[Image: 読み取りと書き込みのアクセス許可]
7. 同じアクセス許可ボックスで、[ **ユーザーのアクセス許可] -&gt; Employee Data** に移動し、サービス アカウントが SuccessFactors テナントから読み取ることができる属性を確認します。 たとえば、SuccessFactors から *Username* 属性を取得するには、この属性に対して "表示" アクセス許可が付与されていることを確認します。 同様に、各属性の表示アクセス許可を確認してください。

[Image: 従業員データのアクセス許可]

    注

    このプロビジョニング アプリによって取得される属性の完全な一覧については、「[SuccessFactors 属性リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference)」を参照してください
8. [ **完了] を選択します**。 [ **変更の保存] を選択します**。

#### API ユーザーのアクセス許可グループを作成する

1. SuccessFactors 管理センターで、[ *アクセス許可グループの管理*] を検索し、検索結果から **[アクセス許可グループの管理** ] を選択します。
[Image: アクセス許可グループの管理]
2. [アクセス許可グループの管理] ウィンドウで、[ **新規作成**] を選択します。
[Image: 新しいグループを追加する]
3. 新しいグループのグループ名を追加します。 グループ名は、グループが API ユーザー用であることを示す必要があります。
[Image: アクセス許可グループ名]
4. グループにメンバーを追加します。 たとえば、[ユーザー プール] ドロップダウン メニューから **[ユーザー名** ] を選択し、統合に使用する API アカウントのユーザー名を入力できます。
[Image: グループ メンバーを追加する]
5. [ **完了] を** 選択して、アクセス許可グループの作成を完了します。

#### 許可グループに許可ロールを付与する

1. SuccessFactors 管理センターで、[ *アクセス許可ロールの管理*] を検索し、検索結果から [ **アクセス許可ロールの管理** ] を選択します。
2. **アクセス許可ロールの一覧**から、API の使用アクセス許可用に作成したロールを選択します。
3. **このロールを付与する...**で、**追加...**ボタンを選択します。
4. ドロップダウン メニューから **[アクセス許可グループ...]** を選択し、[ **選択]...** を選択して [グループ] ウィンドウを開き、上で作成したグループを検索して選択します。
5. アクセス許可グループに対するアクセス許可ロールの付与を確認します。
6. [ **変更の保存] を選択します**。

### SuccessFactors から Microsoft Entra ID へのユーザー プロビジョニングの構成

次の手順では、SuccessFactors から Microsoft Entra ID にユーザー アカウントをプロビジョニングする手順を示します。

- プロビジョニング コネクタ アプリを追加し、SuccessFactors への接続を構成する
- 属性マッピングの構成
- ユーザー プロビジョニングを有効にして起動する

#### パート 1: プロビジョニング コネクタ アプリを追加して、SuccessFactors への接続の構成をする

**SuccessFactors を Microsoft Entra プロビジョニングに構成するには:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **Microsoft Entra ユーザー プロビジョニングに対する SuccessFactors を**検索し、ギャラリーからそのアプリを追加します。
4. アプリが追加され、アプリの詳細画面が表示されたら、[**プロビジョニング**] を選択します
5. **プロビジョニング** **モード**を**自動**に変更する
6. 次のように、[ **管理者資格情報]** セクションに入力します。

    - **管理者ユーザー名** – SuccessFactors API ユーザー アカウントのユーザー名を入力し、会社 ID を追加します。 次の形式があります: **username@companyID**
    - **管理者パスワード –** SuccessFactors API ユーザー アカウントのパスワードを入力します。
    - **テナント URL –** SuccessFactors OData API サービス エンドポイントの名前を入力します。 http または https なしでサーバーのホスト名のみを入力してください。 この値は次のようになります: **api-server-name.successfactors.com**。
    - **通知メール –** メール アドレスを入力し、[失敗した場合にメールを送信する] チェック ボックスをオンにします。

    注

    プロビジョニング ジョブが [検疫](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) 状態になった場合、Microsoft Entra プロビジョニング サービスは電子メール通知を送信します。

    - [ **テスト接続** ] ボタンを選択します。 接続テストが成功した場合は、上部にある **[保存]** ボタンを選択します。 失敗した場合は、SuccessFactors 資格情報および URL が有効か再度確認します。
    - 資格情報が正常に保存されると、[**マッピング**] セクションに既定のマッピングの **Synchronize SuccessFactors Users to Microsoft Entra ID** が表示されます

#### パート 2: 属性マッピングの構成

このセクションでは、ユーザー データが SuccessFactors から Microsoft Entra ID に流れる方法を構成します。

1. [ **マッピング**] の [プロビジョニング] タブで、[ **SuccessFactors ユーザーを Microsoft Entra ID に同期**する] を選択します。
2. [ **ソース オブジェクト スコープ]** フィールドでは、属性ベースのフィルターのセットを定義することで、SuccessFactors のどのユーザー セットを Microsoft Entra ID へのプロビジョニングのスコープに含めるかを選択できます。 既定のスコープは、"SuccessFactors のすべてのユーザー" です。 フィルターの例:

    - 例:1000000 から 2000000 (2000000 を除く) までの personIdExternal を持つユーザーにスコープを設定

        - 属性: personIdExternal
        - 演算子:REGEX Match
        - 値:(1[0-9][0-9][0-9][0-9][0-9][0-9])
    - 例:臨時社員ではなく、従業員のみ

        - 属性:EmployeeID
        - 演算子:IS NOT NULL

    ヒント

    初めてプロビジョニング アプリを構成するときは、属性マッピングと式をテストして検証し、目的の結果が得られていることを確認する必要があります。 Microsoft では、 **ソース オブジェクト スコープ** のスコープ フィルターを使用して、SuccessFactors のいくつかのテスト ユーザーとのマッピングをテストすることをお勧めします。 マッピングが機能していることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

注意事項

プロビジョニング エンジンの既定の動作では、スコープ外に出るユーザーが無効化または削除されます。 これは、SuccessFactors と Microsoft Entra の統合には望ましくない場合があります。 スコープ外のユーザーを無効または削除する既定の動作をオーバーライドするには、「スコープ[外のユーザー アカウントの削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)」の記事を参照してください
3. [ **ターゲット オブジェクト アクション]** フィールドでは、Microsoft Entra ID で実行されるアクションをグローバルにフィルター処理できます。 **作成** と **更新** が最も一般的です。
4. [ **属性マッピング** ] セクションでは、個々の SuccessFactors 属性を Microsoft Entra 属性にマップする方法を定義できます。

    注

    アプリケーションでサポートされている SuccessFactors 属性の完全な一覧については、「[SuccessFactors 属性リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference)」を参照してください
5. 既存の属性マッピングを選択して更新するか、画面の下部にある **[新しいマッピングの追加** ] を選択して新しいマッピングを追加します。 個々の属性マッピングでは、次のプロパティがサポートされます。

    - **マッピングの種類**

        - **Direct** – SuccessFactors 属性の値を変更なしで Microsoft Entra 属性に書き込みます
        - **定数** - 静的な定数文字列値を Microsoft Entra 属性に書き込む
        - **式** – 1 つ以上の SuccessFactors 属性に基づいて、Microsoft Entra 属性にカスタム値を書き込みます。 詳細については、「 [アプリケーション データをカスタマイズするための式」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)参照してください。
    - **ソース属性** - SuccessFactors のユーザー属性
    - **既定値** – 省略可能。 ソース属性に空の値がある場合、マッピングではこの値が代わりに書き込まれます。 最も一般的な構成では、これを空白のままにします。
    - **ターゲット属性** – Microsoft Entra ID のユーザー属性。
    - **この属性を使用してオブジェクトを照合** します。このマッピングを使用して SuccessFactors と Microsoft Entra ID の間でユーザーを一意に識別する必要があるかどうか。 この値は、通常は SuccessFactors の Worker ID フィールドで設定され、Microsoft Entra ID の従業員 ID 属性のいずれかにマッピングされます。
    - **一致する優先順位** – 複数の一致する属性を設定できます。 複数の場合は、このフィールドで定義された順序で評価されます。 1 件でも一致が見つかると、一致する属性の評価はそれ以上行われません。
    - **このマッピングを適用する**

        - **常に** – ユーザー作成アクションと更新アクションの両方にこのマッピングを適用する
        - **作成時のみ** - ユーザー作成アクションにのみこのマッピングを適用する
6. マッピングを保存するには、[Attribute-Mapping] セクションの上部にある **[保存]** を選択します。

属性マッピングの構成が完了したら、 ユーザー プロビジョニング サービスを有効にして起動できるようになりました。

### ユーザー プロビジョニングの有効化と起動

SuccessFactors プロビジョニング アプリの構成が完了すると、Aプロビジョニング サービスを有効にできます。

ヒント

既定では、プロビジョニング サービスを有効にすると、スコープ内のすべてのユーザーに対してプロビジョニング操作が開始されます。 マッピングのエラーまたは SuccessFactors データの問題がある場合、プロビジョニング ジョブが失敗し、検疫状態になる可能性があります。 これを回避するには、ベスト プラクティスとして、すべてのユーザーの完全同期を起動する前に、 **ソース オブジェクト スコープ** フィルターを構成し、少数のテスト ユーザーで属性マッピングをテストすることをお勧めします。 マッピングが機能し、目的の結果が得られていることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

1. [ **プロビジョニング** ] タブで、[ **プロビジョニングの状態]** を **[オン]** に設定します。
2. **[保存] を選択します**。
3. プロビジョニングの状態を保存すると、初期同期が開始されます。これは、SuccessFactors テナント内のユーザー数に応じて、可変時間かかる場合があります。 進行状況バーをチェックして、同期サイクルの進行状況を追跡できます。
4. いつでも、Entra 管理センターの [ **プロビジョニング** ] タブで、プロビジョニング サービスが実行したアクションを確認します。 プロビジョニング ログには、SuccessFactors から読み込まれたユーザーや、その後 Microsoft Entra ID に追加または更新されたユーザーなど、プロビジョニング サービスによって実行された個々の同期イベントがすべて表示されます。
5. 初期同期が完了すると、次に示すように、[ **プロビジョニング** ] タブに監査概要レポートが書き込まれます。

[Image: プロビジョニングの進行状況バー]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial"} -->
## AD と Microsoft Entra ID で SuccessFactors 受信プロビジョニングを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-06
- Summary: SuccessFactors からの受信プロビジョニングを構成する方法について説明します

この記事の目的は、SuccessFactors Employee Central から Active Directory (AD) と Microsoft Entra ID にユーザーをプロビジョニングするために実行する必要がある手順を示し、オプションで SuccessFactors への電子メール アドレスの書き戻しを行います。

注

SuccessFactors からプロビジョニングするユーザーにオンプレミスの AD アカウントと必要に応じて Microsoft Entra アカウントが必要な場合は、この記事を使用します。 SuccessFactors のユーザーが Microsoft Entra アカウント (クラウド専用ユーザー) のみを必要とする場合は、 [SAP SuccessFactors から Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial) へのユーザー プロビジョニングの構成に関する記事を参照してください。

次のビデオでは、SAP SuccessFactors とのプロビジョニング統合を計画するときに必要な手順の簡単な概要について説明します。

### 概要

[Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)は、ユーザーの ID ライフ サイクルを管理するために [SuccessFactors Employee Central](https://www.successfactors.com/products-services/core-hr-payroll/employee-central.html) と統合されます。

Microsoft Entra のユーザー プロビジョニング サービスでサポートされている SuccessFactors ユーザー プロビジョニング ワークフローは、次の人事管理および ID ライフサイクル管理シナリオを自動化します。

- **新入社員の雇用** - 新しい従業員が SuccessFactors に追加されると、Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)にユーザー アカウントが自動的に作成され、電子メール アドレスが SuccessFactors に書き戻されます。
- **従業員の属性とプロファイルの更新** - SuccessFactors で従業員レコード (名前、タイトル、マネージャーなど) が更新されると、ユーザー アカウントは Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされるその他の SaaS アプリケーションで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)自動的に更新されます。
- **従業員の退職** - SuccessFactors で従業員が退職すると、Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされているその他の SaaS アプリケーションで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)、ユーザー アカウントが自動的に無効になります。
- **従業員の再雇用** - 従業員が SuccessFactors で再雇用されると、古いアカウントを Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされるその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に自動的に再アクティブ化または再プロビジョニングできます (ユーザー設定に応じて)。

#### このユーザー プロビジョニング ソリューションが最適な場合

この SuccessFactors から Active Directory へのユーザー プロビジョニング ソリューションは、次の場合に最適です。

- SuccessFactors のユーザー プロビジョニングに、あらかじめ用意されたクラウドベースのソリューションを求める組織 (SAP Integration Suite を使って SAP HCM から SuccessFactors にデータを取り込む組織など)
- [MICROSOFT Entra を使用して SAP ソースアプリとターゲット アプリを使用してユーザー プロビジョニング用に Microsoft Entra をデプロイ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)している組織は、ワーカーの ID を設定して、SAP ECC や SAP S/4HANA などの 1 つ以上の SAP アプリケーション、および必要に応じて SAP 以外のアプリケーションにサインインできるようにする
- ユーザーが Windows Server Active Directory 統合アプリケーションと Microsoft Entra ID 統合アプリケーションにアクセスできるように、SuccessFactors から Active Directory への直接ユーザー プロビジョニングを必要とする組織
- [SuccessFactors Employee Central (EC)](https://www.successfactors.com/products-services/core-hr-payroll/employee-central.html) から取得したデータを使用してユーザーをプロビジョニングする必要がある組織
- [SuccessFactors Employee Central (EC)](https://www.successfactors.com/products-services/core-hr-payroll/employee-central.html)で検出された変更情報のみに基づき、参加、移動、または退職するユーザーを1つ以上のActive Directoryのフォレスト、ドメイン、およびOUに同期させる必要がある組織。
- 電子メールに Microsoft 365 を使用している組織

### ソリューションのアーキテクチャ

このセクションでは、一般的なハイブリッド環境に向けた、エンド ツー エンドのユーザー プロビジョニング ソリューションのアーキテクチャについて説明します。 2 つの関連するフローがあります。

- **権限のある HR データ フロー – SuccessFactors からオンプレミス Active Directory へ:** このフローでは、worker イベント (新入社員、異動、退職など) がクラウド SuccessFactors Employee Central で最初に発生し、次にイベント データが Microsoft Entra ID とプロビジョニング エージェントを介してオンプレミスの Active Directory に流れ込みます。 イベントによっては、AD での作成/更新/有効化/無効化の操作に至る可能性があります。
- **電子メール ライトバック フロー – オンプレミスの Active Directory から SuccessFactors へ:** Active Directory でアカウントの作成が完了すると、Microsoft Entra Connect Sync を介して Microsoft Entra ID と同期され、電子メール属性を SuccessFactors に書き戻すことができます。

    [Image: 概要]

#### エンド ツー エンドのユーザー データ フロー

1. 人事チームは、SuccessFactors Employee Central で社員のトランザクション (就職者/異動者/退職者または新規雇用/異動/退職) を実行します。
2. Microsoft Entra プロビジョニング サービスは、SuccessFactors EC からの、スケジュールされた ID の同期を実行し、オンプレミスの Active Directory との同期のために処理する必要がある変更を識別します。
3. Microsoft Entra プロビジョニング サービスは、AD アカウントの作成/更新/有効化/無効化の操作を含む要求ペイロードを使用して、オンプレミスの Microsoft Entra Connect プロビジョニング エージェントを呼び出します。
4. Microsoft Entra Connect プロビジョニング エージェントは、サービス アカウントを使用して AD アカウントのデータを追加/更新します。
5. Microsoft Entra Connect 同期エンジンによってデルタ同期が実行され、AD 内の更新がプルされます。
6. Active Directory の更新は、Microsoft Entra ID と同期されます。
7. [SuccessFactors ライトバック アプリ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial)が構成されている場合、使用された一致する属性に基づいて、SuccessFactors に電子メール属性が書き戻されます。

### デプロイの計画

SuccessFactors から AD へのクラウド人事駆動型のユーザー プロビジョニングを構成するには、次のようなさまざまな側面をカバーするかなりの計画が必要です。

- Microsoft Entra Connect プロビジョニング エージェントの設定
- AD ユーザー プロビジョニング アプリにデプロイする SuccessFactors の数
- 一致する ID、属性マッピング、変換、およびスコープ フィルター

これらのトピックに関する包括的なガイドラインについては、 [クラウド人事デプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision) を参照してください。 サポートされているエンティティ、処理の詳細、さまざまな人事シナリオに合わせて統合をカスタマイズする方法については、 [SAP SuccessFactors 統合リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference) を参照してください。

### 統合のための SuccessFactors の構成

すべての SuccessFactors プロビジョニング コネクタの一般的な要件は、SuccessFactors OData API を呼び出すための適切なアクセス許可を持つ SuccessFactors アカウントの資格情報が必要なことです。 このセクションでは、SuccessFactors でサービス アカウントを作成し、適切なアクセス許可を付与する手順を説明します。

- SuccessFactors で API ユーザー アカウントを作成/識別する
- API アクセス許可ロールを作成する
- API ユーザーのアクセス許可グループを作成する
- アクセス許可グループに権限ロールを付与する

#### SuccessFactors で API ユーザー アカウントを作成または識別する

SuccessFactors 管理チームまたは実装パートナーと協力して、OData API を呼び出すために SuccessFactors のユーザー アカウントを作成または識別します。 Microsoft Entra ID でプロビジョニング アプリを構成する場合、このアカウントのユーザー名とパスワードの資格情報が必要になります。

#### API アクセス許可ロールを作成する

1. Admin Center にアクセスできるユーザーアカウントで SAP SuccessFactors にログインします。
2. [ *アクセス許可ロールの管理*] を検索し、検索結果から [ **アクセス許可ロールの管理** ] を選択します。 [Image: アクセス許可ロールの管理]
3. アクセス許可ロールの一覧で、[ **新規作成**] を選択します。

[Image: 新しいアクセス許可ロールの作成]
4. 新しいアクセス許可 **ロールのロール名** と **説明** を追加します。 名前と説明では、このロールが API 使用アクセス許可されていることを示す必要があります。
5. [アクセス許可の設定] で [ **アクセス許可...**] を選択し、アクセス許可の一覧を下にスクロールし、[ **統合ツールの管理**] を選択します。 [ **管理者が基本認証を使用して OData API にアクセスできるようにする**] チェック ボックスをオンにします。

[Image: 統合ツールの管理]
6. 同じボックスを下にスクロールし、[ **Employee Central API**] を選択します。 次に示すように、ODATA API を使用して読み取り、ODATA API を使用して編集するためのアクセス許可を追加します。 SuccessFactors への書き戻しシナリオに同じアカウントを使用する場合は、編集オプションを選択してください。

[Image: 読み取り-書き込みアクセス許可]
7. 同じアクセス許可ボックスで、[ **ユーザーのアクセス許可] -&gt; Employee Data** に移動し、サービス アカウントが SuccessFactors テナントから読み取ることができる属性を確認します。 たとえば、SuccessFactors から *Username* 属性を取得するには、この属性に対して "表示" アクセス許可が付与されていることを確認します。 同様に、各属性の表示アクセス許可を確認してください。

[Image: 従業員データのアクセス許可]

    注

    このプロビジョニング アプリによって取得される属性の完全な一覧については、「[SuccessFactors 属性リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference)」を参照してください
8. [ **完了] を選択します**。 [ **変更の保存] を選択します**。

#### API ユーザーのアクセス許可グループを作成する

1. SuccessFactors 管理センターで、[ *アクセス許可グループの管理*] を検索し、検索結果から **[アクセス許可グループの管理** ] を選択します。
[Image: アクセス許可グループの管理]
2. [アクセス許可グループの管理] ウィンドウで、[ **新規作成**] を選択します。
[Image: 新しいグループを追加する]
3. 新しいグループのグループ名を追加します。 グループ名は、グループが API ユーザー用であることを示す必要があります。
[Image: アクセス許可グループ名]
4. グループにメンバーを追加します。 たとえば、[ユーザー プール] ドロップダウン メニューから **[ユーザー名** ] を選択し、統合に使用する API アカウントのユーザー名を入力できます。
[Image: グループ メンバーを追加する]
5. [ **完了] を** 選択して、アクセス許可グループの作成を完了します。

#### 許可グループに許可ロールを付与する

1. SuccessFactors 管理センターで、[ *アクセス許可ロールの管理*] を検索し、検索結果から [ **アクセス許可ロールの管理** ] を選択します。
2. **アクセス許可ロールの一覧**から、API の使用アクセス許可用に作成したロールを選択します。
3. **この役割を付与する相手...**で、**追加...**ボタンを選択します。
4. ドロップダウン メニューから **[アクセス許可グループ...]** を選択し、[ **選択]...** を選択して [グループ] ウィンドウを開き、上で作成したグループを検索して選択します。
5. アクセス許可グループに対するアクセス許可ロールの付与を確認します。
6. [ **変更の保存] を選択します**。

### SuccessFactors から Active Directory へのユーザー プロビジョニングの構成

このセクションでは、SuccessFactors から、統合の範囲内にある各 Active Directory ドメインへのユーザー アカウントのプロビジョニングの手順について説明します。

- プロビジョニング コネクタ アプリを追加し、プロビジョニング エージェントをダウンロードする
- オンプレミスのプロビジョニング エージェントをインストールして構成する
- SuccessFactors と Active Directory への接続を構成する
- 属性マッピングの構成
- ユーザー プロビジョニングを有効にして起動する

#### パート 1: プロビジョニング コネクタ アプリを追加し、プロビジョニング エージェントをダウンロードする

**SuccessFactors から Active Directory へのプロビジョニングを構成するには:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を開きます。
3. **Active Directory ユーザー プロビジョニングに対する SuccessFactors を**検索し、ギャラリーからそのアプリを追加します。
4. アプリが追加され、アプリの詳細画面が表示されたら、[**プロビジョニング**] を選択します
5. **プロビジョニング** **モード**を**自動**に変更する
6. 表示された情報バナーを選択して、プロビジョニング エージェントをダウンロードします。

[Image: プロビジョニング エージェント情報のスクリーンショット。]

#### パート 2: オンプレミス プロビジョニング エージェントのインストールと構成

オンプレミスの Active Directory にプロビジョニングするには、目的の Active Directory ドメインへのネットワーク アクセスを備えたドメイン参加済みのサーバーに、プロビジョニング エージェントがインストールされている必要があります。

ダウンロードしたエージェント インストーラーをサーバー ホストに転送し、 [エージェントのインストール セクションに](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/how-to-install) 記載されている手順に従ってエージェントの構成を完了します。

#### パート 3: プロビジョニング アプリで、SuccessFactors と Active Directory の接続を構成します。

この手順では、SuccessFactors と Active Directory との接続を確立します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **パート 1** で作成した Active Directory ユーザー プロビジョニング アプリに &gt;&gt; SuccessFactors を参照する
3. 次のように、[ **管理者資格情報]** セクションに入力します。

    - **管理者ユーザー名** – SuccessFactors API ユーザー アカウントのユーザー名を入力し、会社 ID を追加します。 次の形式があります: **username@companyID**
    - **管理者パスワード –** SuccessFactors API ユーザー アカウントのパスワードを入力します。
    - **テナント URL –** SuccessFactors OData API サービス エンドポイントの名前を入力します。 http または https なしでサーバーのホスト名のみを入力してください。 この値は**、&lt;api-server-name&gt;.successfactors.com** のようになります。
    - **Active Directory フォレスト -** エージェントに登録されている Active Directory ドメインの "名前" です。 ドロップダウンを使用して、プロビジョニングのターゲット ドメインを選択します。 通常、この値は次のような文字列です: *contoso.com*
    - **Active Directory コンテナー -** エージェントが既定でユーザー アカウントを作成するコンテナー DN を入力します。 例: *OU=Users,DC=contoso,DC=com*

        注

        この設定は、 *parentDistinguishedName* 属性が属性マッピングで構成されていない場合にのみ、ユーザー アカウントの作成で有効になります。 この設定は、ユーザーの検索や更新の操作には使用されません。 ドメインのサブツリー全体が、検索操作の範囲内になります。
    - **通知メール –** メール アドレスを入力し、[失敗した場合にメールを送信する] チェック ボックスをオンにします。

        注

        プロビジョニング ジョブが [検疫](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) 状態になった場合、Microsoft Entra プロビジョニング サービスは電子メール通知を送信します。
    - [ **テスト接続** ] ボタンを選択します。 接続テストが成功した場合は、上部にある **[保存]** ボタンを選択します。 失敗する場合は、エージェントのセットアップで構成された SuccessFactors 資格情報と AD 資格情報が有効であることを再確認します。
    - 資格情報が正常に保存されると、「**マッピング**」セクションに既定のマッピング「**SuccessFactorsユーザーをオンプレミスのActive Directoryに同期**」が表示されます。

#### パート 4:属性マッピングの構成

このセクションでは、ユーザー データが SuccessFactors から Active Directory に流れる方法を構成します。

1. [ **マッピング**] の [プロビジョニング] タブで、[ **SuccessFactors ユーザーをオンプレミス Active Directory に同期**する] を選択します。
2. [ **ソース オブジェクト スコープ]** フィールドでは、属性ベースのフィルターのセットを定義することで、AD へのプロビジョニングのスコープに含める SuccessFactors のユーザー セットを選択できます。 既定のスコープは **、SuccessFactors のすべてのユーザーです**。 フィルターの例:

    - 例:1000000 から 2000000 (2000000 を除く) までの personIdExternal を持つユーザーにスコープを設定

        - 属性: personIdExternal
        - 演算子:REGEX Match
        - 値:(1[0-9][0-9][0-9][0-9][0-9][0-9])
    - 例:臨時社員ではなく、従業員のみ

        - 属性:EmployeeID
        - 演算子:IS NOT NULL

    ヒント

    初めてプロビジョニング アプリを構成するときは、属性マッピングと式をテストして検証し、目的の結果が得られていることを確認する必要があります。 Microsoft では、 **ソース オブジェクト スコープ** のスコープ フィルターを使用して、SuccessFactors のいくつかのテスト ユーザーとのマッピングをテストすることをお勧めします。 マッピングが機能していることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

注意事項

プロビジョニング エンジンの既定の動作では、スコープ外に出るユーザーが無効化または削除されます。 これはご使用の SuccessFactors と AD の統合には望ましくない場合があります。 この既定の動作をオーバーライドするには、[記事「スコープ外のユーザー アカウントの削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)」を参照してください
3. [ **ターゲット オブジェクト アクション]** フィールドでは、Active Directory で実行されるアクションをグローバルにフィルター処理できます。 **作成** と **更新** が最も一般的です。
4. [ **属性マッピング** ] セクションでは、個々の SuccessFactors 属性を Active Directory 属性にマップする方法を定義できます。

    注

    アプリケーションでサポートされている SuccessFactors 属性の完全な一覧については、「[SuccessFactors 属性リファレンス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-attribute-reference)」を参照してください
5. 既存の属性マッピングを選択して更新するか、画面の下部にある **[新しいマッピングの追加** ] を選択して新しいマッピングを追加します。 個々の属性マッピングは、次のプロパティをサポートしています。

    - **マッピングの種類**

        - **Direct** – SuccessFactors 属性の値を AD 属性に書き込みますが、変更はありません。
        - **定数** - 静的な定数文字列値を AD 属性に書き込む
        - **式** – 1 つ以上の SuccessFactors 属性に基づいて、AD 属性にカスタム値を書き込みます。 [詳細については、式に関するこの記事を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)。
    - **ソース属性** - SuccessFactors のユーザー属性
    - **既定値** – 省略可能。 ソース属性に空の値がある場合、マッピングではこの値が代わりに書き込まれます。 最も一般的な構成では、これを空白のままにします。
    - **ターゲット属性** – Active Directory のユーザー属性。
    - **この属性を使用してオブジェクトを照合** します。このマッピングを使用して SuccessFactors と Active Directory の間でユーザーを一意に識別する必要があるかどうか。 この値は、通常、SuccessFactors の Worker ID フィールドで設定され、通常は Active Directory の従業員 ID 属性のいずれかにマッピングされます。
    - **一致する優先順位** – 複数の一致する属性を設定できます。 複数の場合は、このフィールドで定義された順序で評価されます。 1 件でも一致が見つかると、一致する属性の評価はそれ以上行われません。
    - **このマッピングを適用する**

        - **常に** – ユーザー作成アクションと更新アクションの両方にこのマッピングを適用する
        - **作成時のみ** - ユーザー作成アクションにのみこのマッピングを適用する
6. マッピングを保存するには、[Attribute-Mapping] セクションの上部にある **[保存]** を選択します。

属性マッピングの構成が完了したら、オンデマンド プロビジョニングを使用して 1 人のユーザーのプロビジョニング [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand) テストし、 ユーザー プロビジョニング サービスを有効にして起動できます。

### ユーザー プロビジョニングの有効化と起動

SuccessFactors プロビジョニング アプリの構成が完了し、オンデマンド プロビジョニングを使用して 1 人のユーザーのプロビジョニング [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)確認したら、プロビジョニング サービスを有効にすることができます。

ヒント

既定では、プロビジョニング サービスを有効にすると、スコープ内のすべてのユーザーに対してプロビジョニング操作が開始されます。 マッピングのエラーまたは SuccessFactors データの問題がある場合、プロビジョニング ジョブが失敗し、検疫状態になる可能性があります。 これを回避するには、ベスト プラクティスとして、すべてのユーザーの完全同期を起動する前に、**オンデマンド プロビジョニング**を使用して、ソース [オブジェクト スコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand) フィルターを構成し、少数のテスト ユーザーで属性マッピングをテストすることをお勧めします。 マッピングが機能し、目的の結果が得られていることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

1. [ **プロビジョニング** ] ブレードに移動し、[ **プロビジョニングの開始**] を選択します。
2. この操作により初期同期が開始されます。所要時間数は SuccessFactors テナントのユーザー数に応じて変わる可能性があります。 進行状況バーをチェックして、同期サイクルの進行状況を追跡できます。
3. いつでも、Entra 管理センターの [ **プロビジョニング** ] タブで、プロビジョニング サービスが実行したアクションを確認します。 プロビジョニング ログには、SuccessFactors から読み込まれたユーザーや、その後 Active Directory に追加または更新されたユーザーなど、プロビジョニング サービスによって実行された個々の同期イベントがすべて表示されます。
4. 初期同期が完了すると、次に示すように、[ **プロビジョニング** ] タブに監査概要レポートが書き込まれます。

[Image: プロビジョニングの進行状況バー]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-successfactors-learning-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して SAP SuccessFactors Learning へのユーザー プロビジョニングを自動化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-learning-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-10-01
- Summary: SAP Cloud Identity Services を使用して、Microsoft Entra ID から SAP SuccessFactors Learning に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SAP Cloud Identity Services と Microsoft Entra ID で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスと SAP Cloud Identity Services を使用して、SAP SuccessFactors Learning に対するユーザーのプロビジョニングとプロビジョニング解除が自動的に行われます。 Microsoft Entra プロビジョニングが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされている機能

- SAP SuccessFactors Learning でユーザーを作成し、SAP SuccessFactors Learning へのシングル サインオンを有効にする
- アクセスが不要になった場合に SAP SuccessFactors Learning のユーザーを削除する
- Microsoft Entra ID と SAP SuccessFactors Learning の間でユーザー属性の同期を維持する

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP SuccessFactors の学習
- SAP Cloud Identity Services のテナント
- 管理者アクセス許可がある SAP Identity Provisioning の管理コンソールのユーザー アカウント。 Identity Provisioning 管理コンソールのプロキシ システムにアクセスできることを確認します。 **[プロキシ システム]** タイルが表示されない場合は、このタイルへのアクセスを要求するコンポーネント **BC-IAM-IPS** のインシデントを作成します。

### 手順 1:プロビジョニングのデプロイを計画する

Microsoft Entra には、SAP ECC、SAP Cloud Identity Services、SAP SuccessFactors へのコネクタがあります。 SAP SuccessFactors Learning またはその他のアプリケーションへのプロビジョニングでは、ユーザーはまず Microsoft Entra ID に存在する必要があります。 Microsoft Entra ID にユーザーを作成すると、それらのユーザーを Microsoft Entra ID から SAP Cloud Identity Services にプロビジョニングできます。 その後、SAP Cloud Identity Services は、SAP Cloud Identity Directory 内の Microsoft Entra ID から送信されたユーザーを、[`SAP SuccessFactors Learning`](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/target-sap-successfactors-learning) などのダウンストリーム SAP アプリケーションにプロビジョニングします。

[Image: Microsoft Entra ID からの ID のプロビジョニングに関連する Microsoft および SAP のテクノロジを示す図。]

### 手順 2: Microsoft Entra ID に適切なユーザーが存在することを確認する

その後、SuccessFactors からの HR 受信を使用して、従業員の参加、移動、離脱時に Microsoft Entra ID のユーザーの一覧を最新の状態に保つことができます。 グループまたはアプリケーション ロールの割り当てを使用して、SAP SuccessFactors Learning にアクセスできるユーザーまたはそのロールにアクセスできるスコープを設定し、テナントに Microsoft Entra ID ガバナンスのライセンスがある場合は、SAP Cloud Identity Services または SAP SuccessFactors Learning を表すアプリケーションの Microsoft Entra ID で[アプリケーション ロールの割り当てに対する変更を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#assign-users-the-necessary-application-access-rights-in-microsoft-entra)することもできます。 プロビジョニング前に職務の分離やその他のコンプライアンス チェックを実行する方法の詳細については、「[アクセス ライフサイクル管理シナリオの移行](https://learn.microsoft.com/ja-jp/entra/id-governance/scenarios/migrate-from-sap-idm#migrate-access-lifecycle-management-scenarios)」を参照してください。

SAP アプリケーションをターゲットとする ID ライフサイクルの詳細なガイダンスについては、「[SAP ソース アプリケーションとターゲット アプリケーションを使用したユーザー ID プロビジョニングのために Microsoft Entra のデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target)」を参照してください。

### 手順 3: Microsoft Entra ID から SAP Cloud Identity Services へのプロビジョニングを構成する

SAP Cloud Identity Services と統合された SAP SuccessFactors Learning やその他のアプリケーションへのユーザーのプロビジョニングを準備するには、SAP Cloud Identity Services にそれらのアプリケーションに必要なスキーマ マッピングがあることを確認します。 次に、[Microsoft Entra ID から SAP Cloud Identity Services へのユーザーのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-sap-user-source-and-target#provision-users-to-sap-cloud-identity-services)を構成します。 SAP Cloud Identity Services はその後、必要に応じてダウンストリーム SAP アプリケーションにユーザーをプロビジョニングします。

Microsoft Entra から SAP Cloud Identity Services にユーザーをプロビジョニングするには、2 つの方法があります。

- SAP SuccessFactors Learning のロールにユーザーを割り当てるなど、Microsoft Entra ID のグループを使用している場合は、SAP Cloud Identity Services プロビジョニングを使用します。 まず、SAP Analytics Cloud で使用される SAP ビジネス ロール用の Microsoft Entra グループを作成します。 次に、SAP Cloud Identity Services のプロビジョニングで、[Microsoft Entra ID をソースとして構成](https://help.sap.com/docs/identity-provisioning/identity-provisioning/microsoft-azure-active-directory)し、ユーザーとグループを Microsoft Entra ID から SAP Cloud Identity Services に取り込み、作成されたグループを SAP ビジネス ロールにマップします。 詳細については、SAP のドキュメントの「[Provision users from Microsoft Azure AD to SAP Cloud Identity Services - Identity Authentication (Microsoft Azure AD から SAP Cloud Identity Services にユーザーをプロビジョニングする - Identity Authentication)](https://blogs.sap.com/2022/02/04/provision-users-from-microsoft-azure-ad-to-sap-cloud-identity-services-identity-authentication/)」を参照してください。
- または、Microsoft Entra ID でグループを使用する必要がない場合は、Microsoft Entra プロビジョニング サービスを使用できます。 このシナリオでは、SAP SuccessFactors Learning を表すアプリケーションを作成し、SAP SuccessFactors Learning にアクセスする必要があるユーザーをそのアプリケーションに割り当てます。 次に、[Microsoft Entra ID を使用した、SAP Cloud Identity Servicesへの自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial)を構成します。 これらのユーザーが SAP Cloud Identity Services にプロビジョニングされるまで待ち、SAP SuccessFactors Learning ターゲットに必要な属性があることを確認します。

注

小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 ユーザーが SAP ダウンストリーム ターゲットで適切なアクセス権を持っていること、サインイン時に適切なロールを持っていることを確認します。

### 手順 4: SAP Cloud Identity Services から SAP SuccessFactors Learning へのプロビジョニングを構成する

この手順では、SAP Cloud Identity Services Identity Provisioning を使用して SAP SuccessFactors Learning をターゲット システムとして構成します。このシステムでは、ユーザーとグループ メンバーをプロビジョニングできます。 SAP SuccessFactors Learning については、[SAP SuccessFactors Learning へのプロビジョニングに関する SAP ドキュメント](https://help.sap.com/docs/cloud-identity-services/cloud-identity-services/target-sap-successfactors-learning)を参照してください。

### 手順 5: シングル サインオンの構成

SAP アプリケーションのユーザーのプロビジョニングを設定すると、それらのアプリケーションとのシングル サインオンを有効にする必要があります。 Microsoft Entra ID は、SAP アプリケーションの ID プロバイダーおよび認証機関として機能することができます。 [Microsoft Entra シングル サインオン (SSO) と SAP Cloud Identity Services との統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial)をまだ構成していない場合は、構成します。

SAP SaaS や最新のアプリにシングル サインオンを構成する方法の詳細は、「[SSO の有効化](https://learn.microsoft.com/ja-jp/entra/id-governance/sap#enable-sso)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sap-successfactors-writeback-tutorial"} -->
## Microsoft Entra ID で SAP SuccessFactors 書き戻しを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-writeback-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-06
- Summary: Microsoft Entra ID から SAP SuccessFactors への属性の書き戻しを構成する方法について説明します

この記事の目的は、Microsoft Entra ID から SAP SuccessFactors Employee Central に属性を書き戻す手順を示することです。

### 概要

Microsoft Entra ID から SAP SuccessFactors Employee Central に特定の属性が書き込まれるように、SAP SuccessFactors Writeback アプリを構成することができます。 SuccessFactors 書き戻しプロビジョニング アプリでは、次の Employee Central 属性に対する値の割り当てがサポートされています。

- 勤務先の電子メール
- ユーザー名
- 勤務先の電話番号 (国番号、市外局番、番号、内線番号を含む)
- 勤務先電話番号のプライマリ フラグ
- 携帯電話番号 (国番号、市外局番、番号を含む)
- 携帯電話のプライマリ フラグ
- ユーザーの custom01 - custom15 属性
- loginMethod 属性

注意

このアプリは、SuccessFactors 受信ユーザー プロビジョニング統合アプリに依存しません。 [SuccessFactors からオンプレミスの AD](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial) プロビジョニング アプリ、または [SuccessFactors から Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-cloud-only-tutorial) プロビジョニング アプリに関係なく構成できます。

サポートされているシナリオ、既知の問題、制限事項の詳細については、SAP SuccessFactors 統合リファレンス ガイドの [「書き戻](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference#writeback-scenarios) しシナリオ」セクションを参照してください。

#### このユーザー プロビジョニング ソリューションは誰にとって最適ですか？

この SuccessFactors Writeback ユーザー プロビジョニング ソリューションは、次の場合に最適です：

- IT によって管理される信頼できる属性 (メール アドレス、電話番号、ユーザー名など) を SuccessFactors Employee Central に書き戻す必要のある、Microsoft 365 を使用している組織。

### 統合のための SuccessFactors の構成

すべての SuccessFactors プロビジョニング コネクタでは、Employee Central OData API を呼び出すための適切なアクセス許可を持つ SuccessFactors アカウントの資格情報が必要です。 次の手順では、SuccessFactors でサービス アカウントを作成し、必要なアクセス許可を付与する方法について説明します。

- SuccessFactors で API ユーザー アカウントを作成/識別する
- API アクセス許可ロールを作成する
- API ユーザーのアクセス許可グループを作成する
- アクセス許可グループにアクセス許可ロールを付与する

#### SuccessFactors で API ユーザー アカウントを作成または識別する

SuccessFactors 管理チームまたは実装パートナーと協力して、OData API を呼び出すために SuccessFactors のユーザー アカウントを作成または識別します。 Microsoft Entra ID でプロビジョニング アプリを構成する場合、このアカウントのユーザー名とパスワードの資格情報が必要になります。

#### API アクセス許可ロールを作成する

SuccessFactors で API アクセス許可ロールを作成するには、次の手順を実行します。

1. Admin Center にアクセスできるユーザーアカウントで SAP SuccessFactors にログインします。
2. [ *アクセス許可ロールの管理*] を検索し、検索結果から [ **アクセス許可ロールの管理** ] を選択します。

    [Image: アクセス許可ロールの管理]
3. アクセス許可ロールの一覧で、[ **新規作成**] を選択します。

[Image: 新しいアクセス許可ロールの作成]
4. 新しいアクセス許可 **ロールのロール名** と **説明** を追加します。 名前と説明では、このロールが API 使用アクセス許可されていることを示す必要があります。
5. [アクセス許可の設定] で [ **アクセス許可...**] を選択し、アクセス許可の一覧を下にスクロールし、[ **統合ツールの管理**] を選択します。 [ **管理者が基本認証を使用して OData API にアクセスできるようにする**] チェック ボックスをオンにします。

[Image: 統合ツールの管理]
6. 同じボックスを下にスクロールし、[ **Employee Central API**] を選択します。 次に示すように、ODATA API を使用して読み取り、ODATA API を使用して編集するためのアクセス許可を追加します。 SuccessFactors への書き戻しシナリオに同じアカウントを使用する場合は、編集オプションを選択します。

[Image: 読み取りと書き込みのアクセス許可]
7. [ **完了] を選択します**。 [ **変更の保存] を選択します**。

#### API ユーザーのアクセス許可グループを作成する

API ユーザーのアクセス許可グループを作成するには、次の手順を実行します。

1. SuccessFactors 管理センターで、[ *アクセス許可グループの管理*] を検索し、検索結果から **[アクセス許可グループの管理** ] を選択します。

[Image: アクセス許可グループの管理]
2. [アクセス許可グループの管理] ウィンドウで、[ **新規作成**] を選択します。

[Image: 新しいグループを追加する]
3. 新しいグループのグループ名を追加します。 グループ名は、グループが API ユーザー用であることを示す必要があります。

[Image: アクセス許可グループ名]
4. グループにメンバーを追加します。 たとえば、[ユーザー プール] ドロップダウン メニューから **[ユーザー名** ] を選択し、統合に使用する API アカウントのユーザー名を入力できます。

[Image: グループ メンバーを追加する]
5. [ **完了] を** 選択して、アクセス許可グループの作成を完了します。

#### 許可グループに許可ロールを付与する

アクセス許可グループにアクセス許可ロールを付与するには、次の手順を実行します。

1. SuccessFactors 管理センターで、[ *アクセス許可ロールの管理*] を検索し、検索結果から [ **アクセス許可ロールの管理** ] を選択します。
2. **アクセス許可ロールの一覧**から、API の使用アクセス許可用に作成したロールを選択します。
3. **「このロールを付与...」**で、**「追加...」ボタンを**選択します。
4. ドロップダウン メニューから **[アクセス許可グループ...]** を選択し、[ **選択]...** を選択して [グループ] ウィンドウを開き、上で作成したグループを検索して選択します。
5. アクセス許可グループに対するアクセス許可ロールの付与を確認します。
6. [ **変更の保存] を選択します**。

### SuccessFactors Writeback の準備

SuccessFactors ライトバック プロビジョニング アプリは、Employee Central で電子メールと電話番号を設定するために特定の *コード* 値を使用します。 これらの *コード* 値は、属性マッピング テーブルで定数値として設定され、SuccessFactors インスタンスごとに異なります。 次の手順では、属性マッピング テーブルで使用するためにこれらの *コード* 値をキャプチャする方法について説明します。

注意

このセクションの手順を完了するには、SuccessFactors 管理者に協力を要請してください。

#### メール アドレスと電話番号の候補リストの名前を識別する

SAP SuccessFactors では、 *選択リスト* は、ユーザーが選択できる構成可能なオプションのセットです。 さまざまな種類のメール アドレスと電話番号 (例: 勤務先、個人、その他) は、候補リストを使用して表されます。 次の手順では、電子メールと電話番号の値を格納するように SuccessFactors テナントで構成されている候補リストを識別します。

1. SuccessFactors 管理センターで、[ *ビジネス構成の管理*] を検索します。

[Image: ビジネス構成の管理]
2. **HRIS 要素**で **emailInfo** を選択し、*電子メールタイプ*フィールドの**詳細**を選択します。

[Image: 電子メール情報を取得する]
3. **電子メールの種類**の詳細ページで、このフィールドに関連付けられている候補リストの名前をメモします。 既定では **ecEmailType です**。 ただし、テナントによって異なる場合があります。

[Image: メールの候補リストを特定する]
4. **HRIS 要素**で**、phoneInfo** を選択し、*電話タイプ* フィールドの**詳細**を選択します。

[Image: 電話情報を取得する]
5. **電話の種類**の詳細ページで、このフィールドに関連付けられている候補リストの名前をメモします。 既定では **ecPhoneType です**。 ただし、テナントによって異なる場合があります。

[Image: 電話候補リストを識別する]

#### emailType の定数値を取得する

emailType の定数値を取得するには、次の手順を実行します。

1. SuccessFactors 管理センターで、 *候補リスト センター*を検索して開きます。
2. [電子メールの識別] と [電話番号] の 選択リスト名 (ecEmailType など) でキャプチャしたメール選択リストの名前を使用して、メール選択リストを検索します。

[Image: メールの種類の候補リストを検索する]
3. アクティブなメール アドレスの候補リストを開きます。

[Image: アクティブなメールの種類の候補リストを開く]
4. [電子メールの種類] 候補リスト ページで、[Business email type]\( *ビジネス* 電子メールの種類\) を選択します。

[Image: ビジネス用メールの種類を選択する]
5. **ビジネス** メールに関連付けられている*オプション ID を*メモします。 これは、属性マッピング テーブルの *emailType* で使用するコードです。

[Image: 電子メールの種類コードを取得する]

    注意

    値をコピーするときは、コンマ文字を削除してください。 たとえば、 **オプション ID の** 値が *8,448* の場合は、Microsoft Entra ID の *emailType* を定数番号 *8448* (コンマ文字なし) に設定します。

#### phoneType の定数値を取得する

phoneType の定数値を取得するには、次の手順を実行します。

1. SuccessFactors 管理センターで、 *候補リスト センター*を検索して開きます。
2. メール アドレスと電話番号の候補リストの名前を識別する でキャプチャされた電話の候補リストの名前を使用して、電話の候補リストを検索します。

[Image: 電話の種類の候補リストを検索する]
3. アクティブな電話のピックリストを開きます。

[Image: アクティブな電話の種類の候補リストを開く]
4. 電話の種類の候補リスト ページで、[候補リストの値] に一覧表示されているさまざまな電話の種類 **を確認します**。

[Image: 電話の種類を確認する]
5. **ビジネス**用電話に関連付けられている*オプション ID を*メモしておきます。 これは、属性マッピング テーブルの *businessPhoneType* で使用するコードです。

[Image: 勤務先の電話コードを取得する]
6. **携帯電話**に関連付けられている*オプション ID を*メモします。 これは、属性マッピング テーブルの *cellPhoneType* で使用するコードです。

[Image: 携帯電話のコードを取得する]

    注意

    値をコピーするときは、コンマ文字を削除してください。 たとえば、 **オプション ID** の値が *10,606* の場合は、Microsoft Entra ID の *cellPhoneType* を定数番号 *10606* (コンマ文字なし) に設定します。

### SuccessFactors Writeback アプリの構成

SuccessFactors 書き戻しアプリを設定するには、次の手順を実行します。

- プロビジョニング コネクタ アプリを追加し、SuccessFactors への接続を構成する
- 属性マッピングの構成
- ユーザー プロビジョニングを有効にして起動する

#### パート 1: プロビジョニング コネクタ アプリを追加して、SuccessFactors への接続の構成をする

**SuccessFactors Writeback を構成するには:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照してください。
3. **SuccessFactors Writeback** を検索し、ギャラリーからそのアプリを追加します。
4. アプリが追加され、アプリの詳細画面が表示されたら、[**プロビジョニング**] を選択します
5. **プロビジョニング** **モード**を**自動**に変更する
6. 次のように、[ **管理者資格情報]** セクションに入力します。

    - **管理者ユーザー名** – SuccessFactors API ユーザー アカウントのユーザー名を入力し、会社 ID を追加します。 次の形式があります: **username@companyID**
    - **管理者パスワード –** SuccessFactors API ユーザー アカウントのパスワードを入力します。
    - **テナント URL –** SuccessFactors OData API サービス エンドポイントの名前を入力します。 http または https なしでサーバーのホスト名のみを入力してください。 この値は次のようになります: `api4.successfactors.com`。
    - **通知メール –** メール アドレスを入力し、[失敗した場合にメールを送信する] チェック ボックスをオンにします。

    注意

    Microsoft Entra プロビジョニング サービスは、プロビジョニング ジョブが [プロビジョニングの検疫](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) 状態になった場合に、電子メール通知を送信します。

    - [ **テスト接続** ] ボタンを選択します。 接続テストが成功した場合は、上部にある **[保存]** ボタンを選択します。 失敗した場合は、SuccessFactors 資格情報および URL が有効か再度確認します。
    - 資格情報が正常に保存されると、[ **マッピング** ] セクションに既定のマッピングが表示されます。 属性マッピングが表示されない場合は、ページを更新します。

#### パート 2: 属性マッピングの構成

属性マッピングを定義して、ユーザー データが Microsoft Entra ID から SAP SuccessFactors にどのように流れるかを構成します。

1. [ **マッピング**] の [プロビジョニング] タブで、[ **Microsoft Entra ユーザーのプロビジョニング**] を選択します。
2. [ **ソース オブジェクト スコープ]** フィールドでは、属性ベースのフィルターのセットを定義することで、Microsoft Entra ID のどのユーザー セットをライトバックと見なすかを選択できます。 既定のスコープは、 **Microsoft Entra ID のすべてのユーザーです**。

    ヒント

    初めてプロビジョニング アプリを構成するときは、属性マッピングと式をテストして検証し、目的の結果が得られていることを確認する必要があります。 Microsoft では、 **ソース オブジェクト スコープ** のスコープ フィルターを使用して、Microsoft Entra ID のいくつかのテスト ユーザーでマッピングをテストすることをお勧めします。 マッピングが機能していることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。
3. **[ターゲット オブジェクト アクション]** フィールドでは、**更新**操作のみがサポートされます。
4. [ **属性マッピング** ] セクションのマッピング テーブルで、次の Microsoft Entra 属性を SuccessFactors にマップできます。 次の表では、書き戻し属性をマップする方法に関するガイダンスを示します。

    | # | Microsoft Entra 属性 | SuccessFactors 属性 | 解説 |
    | --- | --- | --- | --- |
    | 1 | 従業員ID | personIdExternal | 既定では、この属性は一致する識別子です。 employeeId の代わりに、SuccessFactors の personIdExternal に等しい値が格納されている可能性がある他の Microsoft Entra 属性を使うことができます。 |
    | 2 | メール | メール | メール属性ソースをマップします。 テストのため、userPrincipalName をメールにマップできます。 |
    | 3 | 8448 | メールタイプ | この定数値は、勤務先のメールに関連付けられている SuccessFactors ID の値です。 SuccessFactors の環境に合わせてこの値を更新します。 この値を設定する手順については、「 emailType の定数値を取得 する」セクションを参照してください。 |
    | 4 | ほんとう | メールは主要です | SuccessFactors のプライマリとして勤務先のメールを設定するには、この属性を使用します。 ビジネス メールがプライマリでない場合は、このフラグを false に設定します。 |
    | 5 | userPrincipalName | [カスタム01 – カスタム15] | **新しいマッピングの追加**を使用すると、必要に応じて、SuccessFactors User オブジェクトで使用できるカスタム属性に userPrincipalName または任意の Microsoft Entra 属性を書き込むことができます。 |
    | 6 | On Prem SamAccountName | ユーザー名 | **[新しいマッピングの追加]** を使用すると、必要に応じてオンプレミスの samAccountName を SuccessFactors ユーザー名属性にマップできます。 [Microsoft Entra Connect Sync: ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)を使用して samAccountName を Microsoft Entra ID に同期します。 これはソース ドロップダウンに*extension\_yourTenantGUID\_samAccountName*として表示されます |
    | 7 | SSO | ログイン方法 | SuccessFactors テナントが部分 SSO 用にセットアップされている場合は、[新しいマッピングの追加] を使用して、必要に応じて loginMethod を定数値 "SSO" または "PWD" に設定できます。 |
    | 8 | 電話番号 | ビジネス電話番号 | このマッピングを使用して、microsoft Entra ID から SuccessFactors のビジネス/勤務先電話番号に *telephoneNumber* をフローします。 |
    | 9 | 10605 | ビジネス用電話タイプ | この定数値は、勤務先の電話に関連付けられている SuccessFactors ID の値です。 SuccessFactors の環境に合わせてこの値を更新します。 この値を設定する手順については、「 phoneType の定数値を取得 する」セクションを参照してください。 |
    | 10 | ほんとう | 勤務先電話は主要です | 勤務先電話番号に対してプライマリ フラグを設定するには、この属性を使用します。 有効な値は true または false です。 |
    | 11 | モバイル | 携帯電話番号 | このマッピングを使用して、microsoft Entra ID から SuccessFactors のビジネス/勤務先電話番号に *telephoneNumber* をフローします。 |
    | 12 | 10606 | 携帯電話タイプ | この定数値は、携帯電話に関連付けられている SuccessFactors ID の値です。 SuccessFactors の環境に合わせてこの値を更新します。 この値を設定する手順については、「 phoneType の定数値を取得 する」セクションを参照してください。 |
    | 13 | 偽り | 携帯電話が主要 | 携帯電話番号に対してプライマリ フラグを設定するには、この属性を使用します。 有効な値は true または false です。 |
    | 14 | [extensionAttribute1-15] | ユーザーID | このマッピングを使用して、同じユーザーに対して複数の雇用記録がある場合に、SuccessFactors のアクティブ レコードが必ず更新されるようにします。 詳細については、[UserID を使用した書き戻しの有効化を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/sap-successfactors-integration-reference#enabling-writeback-with-userid)参照してください。 |
5. 属性マッピングを検証して確認します。
6. [ **保存] を** 選択してマッピングを保存します。 次に、SuccessFactors インスタンスで phoneType コードを使用するように JSON Path API 式を更新します。
7. [ **詳細オプションの表示] を選択します**。
8. **SuccessFactors の [属性リストの編集] を選択します**。

    注意

    **[SuccessFactors の属性リストの編集]** オプションが Entra 管理センターに表示されない場合は、URL *https://portal.azure.com/?Microsoft_AAD_IAM_forceSchemaEditorEnabled=true*を使用してページにアクセスします。
9. このビューの **API 式** 列には、コネクタで使用される JSON パス式が表示されます。
10. 環境に対応する ID 値 (*businessPhoneType と cellPhoneType* ) を使用するように、ビジネス用電話と *携帯電話*の JSON パス式を更新します。

[Image: 電話の JSON パスの変更]
11. [ **保存] を** 選択してマッピングを保存します。

### ユーザー プロビジョニングの有効化と起動

SuccessFactors プロビジョニング アプリの構成が完了すると、プロビジョニング サービスを有効にできます。

ヒント

既定では、プロビジョニング サービスを有効にすると、スコープ内のすべてのユーザーに対してプロビジョニング操作が開始されます。 マッピングのエラーまたはデータの問題がある場合、プロビジョニング ジョブが失敗し、検疫状態になる可能性があります。 これを回避するには、ベスト プラクティスとして、すべてのユーザーの完全同期を起動する前に、 **ソース オブジェクト スコープ** フィルターを構成し、少数のテスト ユーザーで属性マッピングをテストすることをお勧めします。 マッピングが機能し、目的の結果が得られていることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

1. [ **プロビジョニング** ] タブで、[ **プロビジョニングの状態]** を **[オン]** に設定します。
2. **[スコープ]** を選択します。 以下のオプションのいずれかを選択できます。

    - **すべてのユーザーとグループを同期**する: **マッピング**&gt;**Source オブジェクト スコープ**で定義されているスコープ規則に従って、Microsoft Entra ID から SuccessFactors にすべてのユーザーのマップされた属性を書き戻す場合は、このオプションを選択します。
    - **割り当てられたユーザーとグループのみを同期**する: &gt;&gt; メニュー オプションで、このアプリケーションに割り当てたユーザーのみのマップされた属性を書き戻す場合は、このオプションを選択します。 これらのユーザーには、 **Mappings**&gt;**Source オブジェクト スコープ**で定義されているスコープ規則も適用されます。

[Image: 書き戻しスコープの選択]

    注意

    2022 年 10 月 12 日以降に作成された SuccessFactors 書き戻しプロビジョニング アプリでは、"グループの割り当て" 機能がサポートされます。 2022 年 10 月 12 日より前にアプリを作成した場合、"ユーザー割り当て" のみがサポートされます。 "グループ割り当て" 機能を使用するには、SuccessFactors 書き戻しアプリの新しいインスタンスを作成し、既存のマッピング構成をこのアプリに移動します。
3. **[保存] を選択します**。
4. この操作を行うと初期同期が開始します。所要時間は、Microsoft Entra テナントのユーザー数と操作に対して定義されているスコープに応じて変わります。 進行状況バーをチェックして、同期サイクルの進行状況を追跡できます。
5. いつでも、Entra 管理センターの [ **プロビジョニング ログ** ] タブで、プロビジョニング サービスが実行したアクションを確認します。 プロビジョニング ログには、プロビジョニング サービスによって実行される個々の同期イベントがすべて一覧表示されます。
6. 初期同期が完了すると、次に示すように、[ **プロビジョニング** ] タブに監査概要レポートが書き込まれます。

[Image: プロビジョニングの進行状況バー]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sapboc-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に SAP Analytics Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sapboc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAP Analytics Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、SAP Analytics Cloud と Microsoft Entra ID を統合する方法について説明します。 SAP Analytics Cloud を Microsoft Entra ID を統合すると、次のことができます。

- 1 つの場所でアカウントを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使って SAP Analytics Cloud に自動的にサインインできるようにする。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SAP Analytics Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SAP Analytics Cloud では、**SP** Initiated SSO がサポートされます。
- SAP Analytics Cloud では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-analytics-cloud-provisioning-tutorial)がサポートされます。

### ギャラリーからの SAP Analytics Cloud の追加

Microsoft Entra ID への SAP Analytics Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAP Analytics Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SAP Analytics Cloud**」と入力します。
4. 結果のパネルから **[SAP Analytics Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SAP Analytics Cloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、SAP Analytics Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SAP Analytics Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

SAP Analytics Cloud に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAP Analytics Cloud SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAP Analytics Cloud のテスト ユーザーの作成** - SAP Analytics Cloud で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SAP Analytics Cloud** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    「a」 **[識別子 (エンティティ ID)]** ボックスに、次のいずれかのパターンを使用して値を入力します。

    | **識別子** |
    | --- |
    | `<sub-domain>.sapbusinessobjects.cloud` |
    | `<sub-domain>.sapanalytics.cloud` |

    b。 **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<sub-domain>.sapanalytics.cloud/` |
    | `https://<sub-domain>.sapbusinessobjects.cloud/` |

    注意

    これらの URL の値は、単なる例です。 値を実際の識別子とサインオン URL で更新してください。 サインオン URL を取得するには、[SAP Analytics Cloud クライアント サポート チーム](https://help.sap.com/viewer/product/SAP_BusinessObjects_Cloud/release/)に問い合わせてください。 識別子 URL は、管理コンソールから SAP Analytics Cloud のメタデータをダウンロードすることで取得できます。 これについては、この記事の後半で説明します。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[SAP Analytics Cloud のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAP Analytics Cloud SSO の構成

1. Web ブラウザーの別のウィンドウで、SAP Analytics Cloud 企業サイトに管理者としてサインインします。
2. **[Menu](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メニュー)**&gt;**[System](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム)**&gt;**[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)** を選択します。

    [Image: [Menu](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メニュー)、[System](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム)、および [Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) の選択]
3. **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)** タブで **[Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集)** (ペン) アイコンを選択します。

    [Image: [Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ) タブで [Edit](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/編集)アイコンを選択]
4. **[Authentication Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証方法)** として **[SAML Single Sign-On (SSO)](SAML シングル サインオン (SSO))** を選択します。

    [Image: 認証方法として [SAML Single Sign-On (SSO)](SAML シングル サインオン (SSO)) を選択]
5. サービス プロバイダーのメタデータをダウンロードする (手順 1) には、**[Download](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ダウンロード)** を選択します。 メタデータ ファイルで、**entityID** を見つけてその値をコピーします。 Azure portal で、**[基本的な SAML 構成]** ダイアログの **[識別子]** ボックスに値を貼り付けます。

    [Image: EntityID 値をコピーして貼り付ける]
6. ダウンロードしたファイル内のサービス プロバイダーのメタデータをアップロードする (手順 2) には、**[Upload Identity Provider metadata] (ID プロバイダーのメタデータのアップロード)** で、**[アップロード]** を選択します。

    [Image: [Upload Identity Provider metadata](ID プロバイダーのメタデータのアップロード) で [アップロード] を選択]
7. **[User Attribute](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性)** の一覧で、実装で使用するユーザー属性を選択します (手順 3)。 このユーザー属性が ID プロバイダーにマップされます。 ユーザーのページでカスタム属性を入力するには、**[Custom SAML Mapping](カスタム SAML マッピング)** オプションを使用します。 または、ユーザー属性として **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** または **[USER ID](ユーザー ID)** のいずれかを選択できます。 この例では、**[ユーザー属性とクレーム]** セクションの **userprincipalname** 属性にユーザー識別子要求をマップしたため、**[メール]** を選択しています。 これにより、一意のユーザーのメールが用意され、SAML 応答が成功するたびに SAP Analytics Cloud アプリケーションに送信されます。

    [Image: ユーザー属性の選択]
8. アカウントを ID プロバイダーで確認する (ステップ 4) には、**[Login Credential (Email)](ログイン資格情報 (電子メール))** ボックスに、ユーザーの電子メール アドレスを入力します。 次に、**[Verify Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの確認)** を選択します。 システムがサインイン資格情報をユーザー アカウントに追加します。

    [Image: 電子メール アドレスを入力し、[Verify Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの確認) を選択]
9. **[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) ** アイコンを選択します。

    [Image: [Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) アイコン]

#### SAP Analytics Cloud のテスト ユーザーの作成

Microsoft Entra ユーザーが SAP Analytics Cloud にサインインできるようにするには、そのユーザーを SAP Analytics Cloud にプロビジョニングしておく必要があります。 SAP Analytics Cloud では、プロビジョニングは手動のタスクです。

ユーザー アカウントをプロビジョニングするには:

1. SAP Analytics Cloud 企業サイトに管理者としてサインインします。
2. **[Menu](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メニュー)**&gt;**[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)**&gt;**[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** を選択します。

    [Image: 従業員の追加]
3. **[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** ページで、新しいユーザーの詳細を追加するには、**+** を選択します。

    [Image: [Add Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ページ]

    その後、次の手順を完了します。

    1. **[USER ID](ユーザー ID)** ボックスに、ユーザーのユーザー ID を入力します (**B** など)。
    2. **[FIRST NAME](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名を入力します (**B** など)。
    3. **[LAST NAME](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (この例では **Simon**)。
    4. **[DISPLAY NAME](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示名)** ボックスに、ユーザーのフル ネームを入力します (**B.Simon** など)。
    5. **[E-MAIL](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** ボックスに、ユーザーの電子メール アドレスを入力します (この例では `b.simon@contoso.com`)。
    6. **[Select Roles](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ロールの選択)** ページで、ユーザーの適切なロールを選択し、**[OK]** を選択します。

        [Image: ロールの選択]
    7. **[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存) ** アイコンを選択します。

注意

SAP Analytics Cloud では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-analytics-cloud-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SAP Analytics Cloud のサインオン URL にリダイレクトされます。
- SAP Analytics Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [SAP Analytics Cloud] タイルを選択すると、このオプションは SAP Analytics Cloud のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sapbusinessbydesign-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SAP Business ByDesign を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sapbusinessbydesign-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SAP Business ByDesign の間でシングル サインオンを構成する方法について説明します。

この記事では、SAP Business ByDesign と Microsoft Entra ID を統合する方法について説明します。 SAP Business ByDesign を Microsoft Entra ID を統合すると、次のことができます。

- SAP Business ByDesign にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SAP Business ByDesign に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な SAP Business ByDesign サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SAP Business ByDesign では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの SAP Business ByDesign の追加

Microsoft Entra ID への SAP Business ByDesign の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SAP Business ByDesign を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SAP Business ByDesign**」と入力します。
4. 結果パネルから **[SAP Business ByDesign]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当て、および SSO 構成の設定が可能です。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、SAP Business ByDesign に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SAP Business ByDesign の関連ユーザーとの間にリンク関係を確立する必要があります。

SAP Business ByDesign に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SAP Business ByDesign の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SAP Business ByDesign のテスト ユーザーの作成** - SAP Business ByDesign で Britta Simon の対応ユーザーを設定し、Microsoft Entra におけるユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SAP Business ByDesign**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<servername>.sapbydesign.com`

    b。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<servername>.sapbydesign.com`

    注意

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、SAP Business ByDesign クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. SAP Business ByDesign アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションには、次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **[ユーザー属性]** セクションで管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: image1]
7. [ **編集** ] アイコンを選択して **、[名前識別子] の値**を編集します。

    [Image: Image2]
8. **[ユーザー要求の管理]** セクションで、以下の手順を実行します。

    [Image: image3]

    ある。 **[ソース]** として **[変換]** を選択します。

    b。 **[変換]** ドロップダウン リストで、 **[ExtractMailPrefix()]** を選択します。

    注意

    既定では、SAP Business ByDesign はユーザー マッピングに**指定されていない** NameID の形式を使用します。 このアプリケーションは、SAML アサーションの NameID を SAP Business ByDesign のユーザー別名にマッピングします。 さらに、このアプリケーションは、名前 ID の形式 **emailAddress** をサポートしています。 この場合、アプリケーションは、SAML アサーションの NameID を、SAP Business ByDesign 従業員の連絡先データの SAP Business ByDesign ユーザー メール アドレスにマップします。 詳細については、[SAP Business ByDesign でのシングル サインオン (SSO)](https://community.sap.com/t5/enterprise-resource-planning-blogs-by-sap/single-sign-on-sso-with-sap-business-bydesign/ba-p/13337088) に関するページを参照してください。
9. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
10. **[SAP Business ByDesign のセットアップ]** セクションで、アプリケーションに必要な適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SAP Business ByDesign の SSO の構成

1. SAP Business ByDesign ポータルに管理者権限でサインオンします。
2. **アプリケーションとユーザー管理の共通タスク**に移動し、[**ID プロバイダー**] タブを選択します。
3. **[新しい ID プロバイダー] を**選択し、ダウンロードしたメタデータ XML ファイルを選択します。 メタデータをインポートした後、必要な署名証明書と暗号化証明書が、アプリケーションによって自動的にアップロードされます。

    [Image: シングル サインオンの構成 1]
4. **Assertion Consumer Service URL** を SAML 要求に追加するには、 **[Include Assertion Consumer Service URL (Assertion Consumer Service URL を含める)]** を選択します。
5. [ **シングル サインオンのアクティブ化]** を選択します。
6. 変更を保存します。
7. [ **マイ システム** ] タブを選択します。

    [Image: シングル サインオンの構成 2]
8. **[Microsoft Entra ID サインオン URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    [Image: シングル サインオンの構成 3]
9. ユーザー ID とパスワードでログオンするか、SSO でログオンするかを従業員が手動で選択できるかどうかを、 **[Manual Identity Provider Selection (ID プロバイダーの手動選択)]** を選択して指定します。
10. **[SSO URL]** セクションには、従業員がアプリケーションへのサインオンに使用する URL を指定します。 [URL Sent to Employee (従業員に送信する URL)] ボックスの一覧で、次のオプションを選択できます。

    **非SSO URL (Non-SSO URL)**

    従業員に送信されるのは、システムの通常の URL のみです。 従業員のサインオンに SSO は使用できず、パスワードまたは証明書を使用する必要があります。

    **SSO URL**

    従業員に送信されるのは、SSO の URL のみです。 従業員は SSO を使用してサインオンすることができます。 認証要求は IdP を介してリダイレクトされます。

    **[Automatic Selection (自動選択)]**

    SSO が有効ではない場合、システムの通常の URL が従業員に送信されます。 SSO が有効である場合は、従業員がパスワードを持っているかどうかがシステムによってチェックされます。 パスワードを持っていた場合は、SSO の URL と非 SSO の URL の両方が従業員に送信されます。 一方、従業員がパスワードを持っていない場合は、SSO の URL だけが従業員に送信されます。
11. 変更を保存します。

#### SAP Business ByDesign テスト ユーザーの作成

このセクションでは、SAP Business ByDesign で Britta Simon というユーザーを作成します。 SAP Business ByDesign クライアント サポート チームと協力して、SAP Business ByDesign プラットフォームにユーザーを追加してください。

注意

NameID 値は必ず、SAP Business ByDesign プラットフォームのユーザー名フィールドと一致させてください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテスト**する] を選択すると、Web ブラウザーが SAP Business ByDesign のサインオン URL にリダイレクトされ、そこでログイン フローを開始できます。
2. SAP Business ByDesign のサインオン URL に直接移動し、そこからログイン フローを開始します。
3. Microsoft マイ アプリを使用することができます。 マイ アプリで [SAP Business ByDesign] タイルを選択すると、Web ブラウザーは SAP Business ByDesign のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->
