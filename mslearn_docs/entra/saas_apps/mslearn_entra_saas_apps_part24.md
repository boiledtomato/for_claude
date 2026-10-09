# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 24)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 79

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamviewer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TeamViewer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamviewer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TeamViewer 間にシングル サインオンを構成する方法について学習します。

この記事では、TeamViewer と Microsoft Entra ID を統合する方法について説明します。 TeamViewer を Microsoft Entra ID と統合すると、次のことができます。

- TeamViewer にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TeamViewer に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TeamViewer でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TeamViewer では、 **SP** Initiated SSO がサポートされます。
- TeamViewer では、 [**自動** ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamviewer-provisioning-tutorial) がサポートされます (推奨)。

### ギャラリーからの TeamViewer の追加

Microsoft Entra ID への TeamViewer の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TeamViewer を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を開きます。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「TeamViewer**」と入力します。
4. 結果パネルから **TeamViewer** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TeamViewer 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TeamViewer に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TeamViewer の関連ユーザーの間にリンク関係を確立する必要があります。

TeamViewer で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TeamViewer の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TeamViewer のテスト ユーザーの作成 - TeamViewer** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TeamViewer**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://login.teamviewer.com/LogOn`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TeamViewer の SSO の構成

**TeamViewer** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[TeamViewer サポート チーム](https://community.teamviewer.com/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TeamViewer のテストユーザーの作成

このセクションでは、TeamViewer で B.Simon というユーザーを作成します。 [TeamViewer サポート チーム](https://community.teamviewer.com/)と協力して、TeamViewer プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TeamViewer のサインオン URL にリダイレクトされます。
- TeamViewer のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TeamViewer] タイルを選択すると、このオプションは TeamViewer のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamwork-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Teamwork.com を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamwork-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Teamwork.com 間にシングル サインオンを構成する方法について学習します。

この記事では、Teamwork.com と Microsoft Entra ID を統合する方法について説明します。 Teamwork.com を Microsoft Entra ID と統合すると、次のことができます:

- Teamwork.com にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Teamwork.com に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Teamwork.com でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Teamwork.com では、**SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Teamwork.com の追加

Microsoft Entra ID への Teamwork.com の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Teamwork.com を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Teamwork.com**」と入力します。
4. 結果パネルから **[Teamwork.com]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Teamwork.com 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Teamwork.com に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Teamwork.com の関連ユーザーとの間にリンク関係を確立する必要があります。

Teamwork.com に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Teamwork.com の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Teamwork.com 上でテストユーザーを作成** - Teamwork.com で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Teamwork.com**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかの URL を入力します。

    | **識別子** |
    | --- |
    | `https://teamwork.com/saml` |
    | `https://eu.teamwork.com/saml` |

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANYNAME>.teamwork.com`

    注

    このサインオン URL 値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには、[Teamwork.com サポート チーム](mailto:support@teamwork.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Teamwork.com のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Teamwork.com の SSO を構成する

**Teamwork.com** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Teamwork.com サポート チーム](mailto:support@teamwork.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Teamwork.com テスト ユーザーを作成する

このセクションでは、Teamwork.com で Britta Simon というユーザーを作成します。 [Teamwork.com サポート チーム](mailto:support@teamwork.com)と連携し、Teamwork.com プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Teamwork.com サインオン URL にリダイレクトされます。
- Teamwork.com のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Teamwork.com] タイルを選択すると、このオプションは Teamwork.com サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/techsafe-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TechSafe Entra ID SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/techsafe-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-18
- Summary: Microsoft Entra ID と TechSafe Entra ID SSO との間でシングル サインオンを構成する方法について説明します。

この記事では、TechSafe Entra ID SSO と Microsoft Entra ID を統合する方法について説明します。 TechSafe Entra ID SSO を Microsoft Entra ID と統合すると、次のことができます。

- TechSafe Entra ID SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して TechSafe Entra ID SSO に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TechSafe Entra ID SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TechSafe Entra ID SSO では、 **SP** によって開始される SSO のみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### TechSafe Entra ID SSO をギャラリーから追加する

TechSafe Entra ID SSO の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TechSafe Entra ID SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「TechSafe Entra ID SSO**」と入力します。
4. 結果パネルから **TechSafe Entra ID SSO を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TechSafe Entra ID SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TechSafe Entra ID SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TechSafe Entra ID SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

TechSafe Entra ID SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TechSafe Entra ID SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TechSafe Entra ID SSO テスト ユーザーの作成 - TechSafe Entra** ID SSO で B.Simon に対応する新しいユーザーを作成し、Microsoft Entra ID ユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TechSafe Entra ID SSO**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://portal.techsafe.nz/saml2/sso`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://portal.techsafe.nz/saml/module.php/saml/sp/saml2-acs.php/techsafe-sp`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://portal.techsafe.nz`

    d. [ **ログアウト URL** ] テキスト ボックスに、URL を入力します。 `https://portal.techsafe.nz/logout.php`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **TechSafe Entra ID SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TechSafe Entra ID SSO を構成する

**TechSafe Entra ID SSO** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [TechSafe Entra ID SSO サポート チーム](mailto:support@capellaconsulting.co.nz)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### TechSafe Entra ID SSO テスト ユーザーを作成する

このセクションでは、TechSafe Entra ID SSO で B.Simon というユーザーを作成します。 [TechSafe Entra ID SSO サポート チーム](mailto:support@capellaconsulting.co.nz)と協力して、TechSafe Entra ID SSO プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる TechSafe Entra ID SSO サインオン URL にリダイレクトされます。
- TechSafe Entra ID SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで TechSafe Entra ID SSO タイルを選択すると、このオプションは TechSafe Entra ID SSO サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/templafy-openid-connect-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Templafy OpenID Connect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/templafy-openid-connect-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントを Templafy OpenID Connect に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Templafy OpenID Connect で実行する手順と、Templafy OpenID Connect に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Templafy OpenID Connect でユーザーを作成します。
- アクセスが不要になったら、Templafy OpenID Connect のユーザーを削除します。
- Microsoft Entra IDと Templafy OpenID Connect の間でユーザー属性の同期を維持します。
- Templafy OpenID Connect でグループとグループ メンバーシップをプロビジョニングします。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [テンプレフィーのテナント](https://www.templafy.com/pricing/)。
- 管理者アクセス許可がある Templafy のユーザー アカウント。

### 手順 1: プロビジョニングの配置を計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとTemplafy OpenID Connectの間でどのデータをマップするかを決定します。

### 手順 1: Templafy OpenID Connect にユーザーを割り当てる

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Templafy OpenID Connect へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定したら、「エンタープライズ アプリにユーザーまたはグループを割り当てる」の手順に従って、これらのユーザーやグループを Templafy OpenID Connect [に割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。

#### ユーザーを Templafy OpenID Connect に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを Templafy OpenID Connect に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- Templafy OpenID Connect にユーザーを割り当てるときは、割り当てダイアログでアプリケーション固有の有効なロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Templafy OpenID Connect を構成する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Templafy OpenID Connect を構成する前に、Templafy OpenID Connect で SCIM プロビジョニングを有効にする必要があります。

1. Templafy 管理コンソールにサインインします。 [ **管理]** を選択します。

    [Image: Templafy 管理コンソールのスクリーンショット。]
2. [ **認証方法] を選択します**。

    [Image: [認証方法] オプションが強調表示されている Templafy 管理セクションのスクリーンショット。]
3. **SCIM Api キー**の値をコピーします。 この値は、Templafy OpenID Connect アプリケーションの [プロビジョニング] タブの **[シークレット トークン** ] フィールドに入力されます。

    [Image: S C I M A P I キーのスクリーンショット。]

### 手順 3: ギャラリーから Templafy OpenID Connect を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Templafy OpenID Connect を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Templafy OpenID Connect を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Templafy OpenID Connect を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加**] セクションに「**Templafy OpenID Connect**」と入力し、検索ボックスで **Templafy OpenID Connect** を選択します。
4. 結果パネルから **Templafy OpenID Connect** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

    [Image: 結果一覧の Templafy OpenID Connect のスクリーンショット。]

### 手順 4: Templafy OpenID Connect への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーやグループの割り当てに基づいて Templafy OpenID Connect でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Templafy のシングル サインオンに関する記事で説明されている手順に従って、Templafy に対して OpenID 接続ベース [のシングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/templafy-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Templafy OpenID Connect の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps を開く

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Templafy OpenID Connect** を選択します。

    [Image: アプリケーションの一覧の Templafy OpenID Connect リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [**管理者資格情報**] セクションで、[`https://scim.templafy.com/scim`] に「」と入力します。 先ほど取得した **SCIM API キー** の値を **シークレット トークン**に入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Templafy に接続できることを確認します。 接続できない場合は、使用中の Templafy アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute Mapping** セクションで、Microsoft Entra IDから Templafy OpenID Connect に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Templafy OpenID Connect のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | タイトル | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |
    | externalId | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |

    注

    このアプリケーションでは、スキーマ検出機能が有効になっています。
12. **[グループ]** を選びます。
13. **Attribute Mapping** セクションで、Microsoft Entra IDから Templafy OpenID Connect に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Templafy OpenID Connect のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | リファレンス |  |
    | externalId | 糸 |  |

    注

    このアプリケーションでは、スキーマ検出機能が有効になっています。
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 変更履歴

- 2023 年 5 月 4 日 - **スキーマ検出**のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/templafy-saml-2-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Templafy SAML2 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/templafy-saml-2-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントを Templafy SAML2 に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Templafy SAML2 で実行する手順と、Templafy SAML2 に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Templafy SAML2 でユーザーを作成します。
- アクセスが不要になった Templafy SAML2 のユーザーを削除します。
- Microsoft Entra IDと Templafy SAML2 の間でユーザー属性の同期を維持します。
- Templafy SAML2 でグループとグループ メンバーシップをプロビジョニングします。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [Templafy のテナント](https://www.templafy.com/pricing/)。
- 管理者アクセス許可がある Templafy のユーザー アカウント。

### 手順 1: プロビジョニングの配置を計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとTemplafy SAML2の間で[どのデータをマッピングするかを決定します。](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)

### Templafy SAML2 へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Templafy SAML2 へのアクセスが必要Microsoft Entra IDユーザーまたはグループを決定する必要があります。 決定したら、「エンタープライズ アプリにユーザーまたはグループを割り当てる」の手順に従って、これらのユーザーやグループを Templafy SAML2 [に割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。

### ユーザーを Templafy SAML2 に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを Templafy SAML2 に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- Templafy SAML2 にユーザーを割り当てるときは、割り当てダイアログでアプリケーション固有の有効なロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Templafy SAML2 を構成する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Templafy SAML2 を構成する前に、Templafy SAML2 で SCIM プロビジョニングを有効にする必要があります。

1. Templafy 管理コンソールにサインインします。 [ **管理]** を選択します。

    [Image: Templafy 管理コンソールのスクリーンショット。]
2. [ **認証方法] を選択します**。

    [Image: [認証方法] オプションが強調表示されている Templafy 管理セクションのスクリーンショット。]
3. **SCIM Api キー**の値をコピーします。 この値は、Templafy SAML2 アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: S C I M A P I キーのスクリーンショット。]

### 手順 3: ギャラリーから Templafy SAML2 を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Templafy SAML2 を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Templafy SAML2 を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Templafy SAML2 を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーからの追加**] セクションで、「**Templafy SAML2**」と入力し、検索ボックスで **Templafy SAML2** を選択します。
4. 結果パネルから **Templafy SAML2** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

    [Image: 結果一覧の Templafy SAML2 のスクリーンショット。]

### 手順 4: Templafy SAML2 への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Templafy SAML2 でユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Templafy のシングル サインオンに関する記事で説明されている手順に従って、Templafy に対して SAML ベース [のシングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/templafy-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Templafy SAML2 の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps を開く

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Templafy SAML2** を選択します。

    [Image: アプリケーションの一覧の Templafy SAML2 リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [**管理者資格情報**] セクションで、[`https://scim.templafy.com/scim`] に「」と入力します。 先ほど取得した **SCIM API キー** の値を **シークレット トークン**に入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Templafy に接続できることを確認します。 接続できない場合は、使用中の Templafy アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute Mappings** セクションで、Microsoft Entra IDから Templafy SAML2 に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Templafy SAML2 のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | タイトル | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |
    | externalId | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |

    注

    このアプリケーションでは、スキーマ検出機能が有効になっています。
12. **[グループ]** を選びます。
13. **Attribute Mappings** セクションで、Microsoft Entra IDから Templafy SAML2 に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Templafy SAML2 のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | members | リファレンス |  |
    | externalId | 糸 |  |

    注

    このアプリケーションでは、スキーマ検出機能が有効になっています。
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 変更履歴

- 2023 年 5 月 4 日 - **スキーマ検出**のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/templafy-tutorial"} -->
## Microsoft Entra ID で Templafy SAML2 for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/templafy-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Templafy SAML2 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Templafy SAML2 と Microsoft Entra ID を統合する方法について説明します。 Templafy SAML2 を Microsoft Entra ID と統合すると、次のことができます:

- Templafy SAML2 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Templafy SAML2 に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Templafy SAML2 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Templafy SAML2 では、**SP** Initiated SSO がサポートされます。
- Templafy SAML2 では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Templafy SAML2 では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/templafy-saml-2-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Templafy SAML2 を追加する

Microsoft Entra ID への Templafy SAML2 の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Templafy SAML2 を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Templafy SAML2**」と入力します。
4. 結果パネルから **Templafy SAML2** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Templafy SAML2 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Templafy SAML2 に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Templafy SAML2 の関連ユーザーとの間にリンク関係を確立する必要があります。

Templafy SAML2 に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Templafy SAML2 の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Templafy SAML2 テスト ユーザーの作成 - Templafy SAML2** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Templafy SAML2**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CLIENT_SUBDOMAIN>.templafy.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Templafy SAML2 クライアント サポート チーム](mailto:support@templafy.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Templafy SAML2 アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Templafy SAML2 アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 | Namespace |
    | --- | --- | --- |
    | givenname | ユーザー.ファーストネーム | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | 名字 | ユーザーの名字 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | メールアドレス | ユーザーのメールアドレス | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | 住所 | ユーザーの住所 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | 都市 | ユーザーの都市 | `http://schemas.templafy.com/2016/06/identity/claims` |
    | 郵便番号 | ユーザー.郵便番号 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | 州または省 | ユーザーの状態 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | 国 | ユーザーの国 | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | 職種 | ユーザー.職名 | `http://schemas.templafy.com/2016/06/identity/claims` |
    | 部署 | ユーザーの部署 | `http://schemas.templafy.com/2016/06/identity/claims` |
    | 電話番号 | ユーザー.電話番号 | `http://schemas.templafy.com/2016/06/identity/claims` |
    | ファクシミリ番号 | 利用者.ファクシミリ電話番号 | `http://schemas.templafy.com/2016/06/identity/claims` |
    | UPN | ユーザー.ユーザープリンシパルネーム | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    | ネームアイデンティファイア | ユーザーのメールアドレス | `http://schemas.xmlsoap.org/ws/2005/05/identity/claims` |
    |  |  |  |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Templafy SAML2 の SSO の構成

**Templafy SAML2** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Templafy SAML2 サポート チーム](mailto:support@templafy.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Templafy SAML2 のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Templafy SAML2 に作成します。 Templafy SAML2 では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Templafy SAML2 にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

Templafy SAML2 では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/templafy-saml-2-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Templafy SAML2 サインオン URL にリダイレクトされます。
- Templafy SAML2 のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Templafy SAML2] タイルを選択すると、このオプションは Templafy SAML2 のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tencent-cloud-idaas-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TencentCloud IDaaS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tencent-cloud-idaas-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TencentCloud IDaaS の間でシングル サインオンを構成する方法について説明します。

この記事では、TencentCloud IDaaS と Microsoft Entra ID を統合する方法について説明します。 Tencent Cloud IDaaS は、ID 認証とデータ管理サービスを企業に提供することに専念しています。 TencentCloud IDaaS と Microsoft Entra ID を統合すると、次のことができます。

- TencentCloud IDaaS にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して TencentCloud IDaaS に自動的にサインインするように設定できます。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で TencentCloud IDaaS 向けの Microsoft Entra のシングル サインオンを構成してテストします。 TencentCloud IDaaS では、**SP** Initiated と **IDP** Initiated の両方のシングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートしています。

### [前提条件]

Microsoft Entra ID と TencentCloud IDaaS を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な TencentCloud IDaaS のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから TencentCloud IDaaS アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから TencentCloud IDaaS を追加する

Microsoft Entra アプリケーション ギャラリーから TencentCloud IDaaS を追加して、TencentCloud IDaaS でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**&gt;**TencentCloud IDaaS**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`yufuid.com/<appInstanceId>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantUrl>/cidp/saml2/<appInstanceId>`
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<tenantUrl>/sso/<tenantId>/<appInstanceId>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[TencentCloud IDaaS クライアント サポート チーム](mailto:empdept@tencent.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### TencentCloud IDaaS SSO の構成

**TencentCloud IDaaS SSO** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [TencentCloud IDaaS SSO サポート チーム](mailto:empdept@tencent.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TencentCloud IDaaS テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを TencentCloud IDaaS に作成します。 TencentCloud IDaaS では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 TencentCloud IDaaS にユーザーがまだ存在していない場合は、認証後に新規作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TencentCloud IDaaS サインオン URL にリダイレクトされます。
- TencentCloud IDaaS のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TencentCloud IDaaS に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TencentCloud IDaaS] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TencentCloud IDaaS に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tendium-tutorial"} -->
## Microsoft Entra ID で Tendium for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tendium-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tendium の間のシングル サインオンを構成する方法について説明します。

この記事では、Tendium と Microsoft Entra ID を統合する方法について説明します。 Tendium を Microsoft Entra ID と統合すると、次のことが可能になります。

- Tendium にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Tendium に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Tendium でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Tendium では、**SP** Initiated SSO がサポートされます。
- Tendium では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Tendium を追加する

Microsoft Entra ID への Tendium の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Tendium を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Tendium**」と入力します。
4. 結果パネルから **[Tendium]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tendium に対する Microsoft Entra SSO を構成およびテストする

**B.Simon** という名前のテスト ユーザーを使用して、Tendium で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Tendium での関連ユーザーとの間にリンク関係を確立する必要があります。

Tendium 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tendium の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Tendium テストユーザーの作成** - Tendium において、B.Simon と同等であるユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Tendium**&gt;**シングル サインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、値を入力します。 `urn:amazon:cognito:sp:eu-west-1_bIV0Yblnt`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://auth-prod.app.tendium.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.tendium.com/auth/sign-in`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tendium SSO の構成

**Tendium** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Tendium サポート チーム](mailto:tech-partners@tendium.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。詳細については、[こちら](https://tendium.ai/contact/)で Tendium にお問い合わせください。

#### Tendium のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Tendium に作成します。 Tendium では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Tendium にユーザーがまだ存在していない場合は、認証後に新規作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Tendium のサインオン URL にリダイレクトされます。
- Tendium のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Tendium] タイルを選択すると、このオプションは Tendium のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/terraform-cloud-tutorial"} -->
## Microsoft Entra ID を使用して Terraform Cloud for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/terraform-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Terraform Cloud 間にシングル サインオンを構成する方法について学習します。

この記事では、Terraform Cloud と Microsoft Entra ID を統合する方法について説明します。 Terraform Cloud を Microsoft Entra ID と統合すると、次のことができます。

- Terraform Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Terraform Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Terraform Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Terraform Cloud では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Terraform Cloud では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Terraform Cloud の追加

Microsoft Entra ID への Terraform Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Terraform Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Terraform Cloud**」と入力します。
4. 結果のパネルから **[Terraform Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Terraform Cloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Terraform Cloud で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Terraform Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Terraform Cloud で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Terraform Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Terraform Cloud のテスト ユーザーの作成 - Terraform Cloud** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Terraform Cloud**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.terraform.io/sso/saml/samlconf-<ID>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.terraform.io/sso/saml/samlconf-<ID>/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.terraform.io/session`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Terraform Cloud クライアント サポート チーム](mailto:tf-cloud@hashicorp.support)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Terraform Cloud の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Terraform Cloud 企業サイトに管理者としてサインインします
2. **[設定] &gt; [SSO] &gt; [設定の編集]** に移動します。

    [Image: Terraform Cloud の設定]
3. **[Edit SSO](SSO の編集)** ページで、次の手順を実行します。

    [Image: Terraform Cloud の SSO の編集]

    ある。 ** [Sign-On URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    b。 **[エンティティ ID または識別子]** テキスト ボックスに、先ほどコピーした **[Microsoft Entra 識別子]** の値を貼り付けます。

    c. ダウンロードした**証明書** をメモ帳で開き、その内容を **[公開証明書]** ボックスに貼り付けます。

    d. [ **設定の保存] を選択します**。

#### Terraform Cloud のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Terraform Cloud に作成します。 Terraform Cloud では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Terraform Cloud にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Terraform Cloud サインオン URL にリダイレクトされます。
- Terraform Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Terraform Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Terraform Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Terraform Cloud に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/terraform-enterprise-tutorial"} -->
## Microsoft Entra ID でシングルサインオン用に Terraform Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/terraform-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Terraform Enterprise の間にシングル サインオンを構成する方法について説明します。

この記事では、Terraform Enterprise と Microsoft Entra ID を統合する方法について説明します。 Terraform Enterprise を Microsoft Entra ID と統合すると、次のことができます。

- Terraform Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Terraform Enterprise に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Terraform Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Terraform Enterprise では、 **SP** Initiated SSO がサポートされます。
- Terraform Enterprise では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Terraform Enterprise の追加

Microsoft Entra ID への Terraform Enterprise の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Terraform Enterprise を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Terraform Enterprise」**と入力します。
4. 結果パネルから **Terraform Enterprise** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Terraform Enterprise 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Terraform Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Terraform Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

Terraform Enterprise に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Terraform Enterprise の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Terraform Enterprise のテストユーザーを作成** - Terraform Enterprise で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現に対応する B.Simon とリンクさせるため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Terraform Enterprise**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TFE HOSTNAME>/users/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TFE HOSTNAME>/users/saml/auth`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TFE HOSTNAME>/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Terraform Enterprise クライアント サポート チーム](https://support.hashicorp.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Terraform Enterprise のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Terraform Enterprise の SSO の構成

`https://<TFE_HOSTNAME>/app/admin/saml`に移動し**、[SAML 設定]** ページで次の手順を実行します。

[Image: スクリーンショット: Terraform Enterprise SAML 設定]

ある。 [ **SAML シングル サインオンを有効にする** ] チェック ボックスをオンにします。

b。 **単一 Sign-On URL** テキストボックスに、コピーした **ログイン URL** の値を貼り付けます。

c. [ **単一ログアウト URL** ] ボックスに、コピーした **ログイン URL** の値を貼り付けます。

d. ダウンロードした **証明書** をメモ帳に開き、 **IDP CERTIFICATE** テキストボックスに内容を貼り付けます。

#### Terraform Enterprise のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Terraform Enterprise に作成します。 Terraform Enterprise では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Terraform Enterprise にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Terraform Enterprise のサインオン URL にリダイレクトされます。
- Terraform Enterprise のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Terraform Enterprise] タイルを選択すると、このオプションは Terraform Enterprise のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/terranova-security-awareness-platform-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Terranova Security Awareness Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/terranova-security-awareness-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Terranova Security Awareness Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Terranova Security Awareness Platform と Microsoft Entra ID を統合する方法について説明します。 Terranova Security Awareness Platform と Microsoft Entra ID を統合すると、次のことができます。

- Terranova Security Awareness Platform にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Terranova Security Awareness Platform に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Terranova Security Awareness Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Terranova Security Awareness Platform は、**SP 発起型認証**と**IDP 発起型認証**の両方をサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Terranova Security Awareness Platform を追加する

Microsoft Entra ID への Terranova Security Awareness Platform の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Terranova Security Awareness Platform を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Terranova Security Awareness Platform**」と入力します。
4. 結果パネルから **Terranova Security Awareness Platform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Terranova Security Awareness Platform の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Terranova Security Awareness Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Terranova Security Awareness Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Terranova Security Awareness Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Terranova Security Awareness Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Terranova Security Awareness Platform のテストユーザーを作成** - Microsoft Entra のユーザー表現にリンクされている B.Simon の対応ユーザーを Terranova Security Awareness Platform 内に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**Terranova Security Awareness Platform**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://secure.terranovasite.com/portal/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://secure.terranovasite.com/portal/Login/ACS?e=<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://secure.terranovasite.com/`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、 [Terranova Security Awareness Platform サポート チーム](mailto:support.terranova@helpsystems.com) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Terranova Security Awareness Platform のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Terranova Security Awareness Platform の SSO の構成

**Terranova Security Awareness Platform** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Terranova Security Awareness Platform サポート チーム](mailto:support.terranova@helpsystems.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Terranova Security Awareness Platform のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Terranova Security Awareness Platform に作成します。 [Terranova Security Awareness Platform サポート チーム](mailto:support.terranova@helpsystems.com)と協力して、Terranova Security Awareness プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Terranova Security Awareness Platform のサインオン URL にリダイレクトします。
- Terranova Security Awareness Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Terranova Security Awareness Platform に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Terranova Security Awareness Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Terranova Security Awareness Platform に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/terratrue-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に TerraTrue を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/terratrue-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから TerraTrue にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために TerraTrue とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成済みのMicrosoft Entra IDは、Microsoft Entraプロビジョニングサービスを使用して、ユーザーとグループを[TerraTrue](https://terratruehq.com/)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- TerraTrue でユーザーを作成します。
- アクセスが不要になった場合は、TerraTrue のユーザーを削除します。
- Microsoft Entra IDと TerraTrue の間でユーザー属性の同期を維持します。
- TerraTrueへのシングルサインオン。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [TerraTrue](https://terratruehq.com/) テナント。
- 管理者アクセス許可がある TerraTrue のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとTerraTrueの間でマップするデータを[決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように TerraTrue を構成する

1. TerraTrue で、**「組織設定」&gt;、「認証」&gt;、「SCIM」** に移動するか、`https://launch.terratrue.com/settings/auth/scim` を訪問します。
2. 次に、[ **SCIM 構成]** トグルを有効にし、[ **API キーのコピー** ] を選択して SCIM API キーをコピーします。

    [Image: トークンの生成のスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから TerraTrue を追加する

Microsoft Entra アプリケーション ギャラリーから TerraTrue を追加して、TerraTrue へのプロビジョニングの管理を開始します。 SSO に対して TerraTrue を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: TerraTrue への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TerraTrue でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で TerraTrue の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **TerraTrue** を選択します。

    [Image: アプリケーションの一覧の TerraTrue リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、TerraTrue テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが TerraTrue に接続できることを確認します。 接続に失敗した場合は、TerraTrue アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから TerraTrue に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で TerraTrue のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、TerraTrue API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | TerraTrue で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | name.formatted | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### トラブルシューティングとヒント

プロビジョニングが正常に機能していることを確認するために、`hello@terratrue.com` に連絡します。

TerraTrue では、ユーザーのアカウントに対するすべての変更のリビジョン履歴が、次のリンクで任意の TerraTrue 管理者に表示されます。 SCIM プロビジョニングの結果として行われたすべてのユーザー変更は、アクター列が "Scim System User" と表示されます。

`https://launch.terratrue.com/settings/history`

最後に、TerraTrue は、最初のユーザーの同期中に受信した名と姓に基づいて、ユーザーの表示名を設定します。その後、ユーザーの表示名を変更するには、次のリンクにある [ユーザー組織設定] の下にある TerraTrue 内部から管理者によって作成されます。

`https://launch.terratrue.com/settings/users`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/terratrue-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TerraTrue を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/terratrue-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TerraTrue 間にシングル サインオンを構成する方法について学習します。

この記事では、TerraTrue と Microsoft Entra ID を統合する方法について説明します。 TerraTrue を Microsoft Entra ID と統合すると、次のことができます。

- TerraTrue にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TerraTrue に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TerraTrue でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TerraTrue では、**SP および IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから TerraTrue を追加する

Microsoft Entra ID への TerraTrue の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに TerraTrue を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「TerraTrue」と**入力します。
4. 結果パネルから **TerraTrue** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TerraTrue 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TerraTrue に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TerraTrue の関連ユーザーとの間にリンク関係を確立する必要があります。

TerraTrue で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TerraTrue SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TerraTrue テスト ユーザーの作成** - TerraTrue で B.Simon に対応するユーザーを作成し、それが Microsoft Entra の B.Simon にリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**TeraTrue**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://launch.terratrue.com/idp-sso-login/<CUSTOMER-ID>`

    注

    この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、 [TerraTrue クライアント サポート チーム](mailto:hello@terratrue.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://launch.terratrue.com/`
7. TerraTrue アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、TerraTrue では、これがユーザーの電子メール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TerraTrue の SSO を構成する

**TerraTrue** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[TerraTrue サポート チーム](mailto:hello@terratrue.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TerraTrue のテスト ユーザーの作成

このセクションでは、TerraTrue で Britta Simon というユーザーを作成します。 [TerraTrue サポート チーム](mailto:hello@terratrue.com)と協力して、TerraTrue プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TerraTrue サインオン URL にリダイレクトされます。
- TerraTrue のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TerraTrue に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで TerraTrue タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TerraTrue に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tesma-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に tesma を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tesma-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と tesma 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、tesma と Microsoft Entra ID を統合する方法について説明します。tesma は tesma Essential やその他の tesma ポータルに SSO を提供します。 tesma を Microsoft Entra ID と統合すると、次のことができます。

- tesma にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して tesma に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で tesma 向けの Microsoft Entra のシングル サインオンを構成してテストします。 tesma では、 **SP** と **IDP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングの両方がサポートされます。

### [前提条件]

Microsoft Entra ID を tesma と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な tesma のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから tesma アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから tesma を追加する

Microsoft Entra アプリケーション ギャラリーから tesma を追加して、tesma とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**tesma**&gt;**シングルサインオン**に進みます。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子** ] ボックスに、値を入力します。 `urn:identityprovider.chg-meridian.com`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://identityprovider.chg-meridian.com`
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://qamajor.tesma.com`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **tesma のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

### tesma SSO を構成する

**tesma** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [tesma サポート チーム](mailto:support@tesma.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### tesma テスト ユーザーを作成する

このセクションでは、tesma で Britta Simon というユーザーを作成します。 [tesma サポート チーム](mailto:support@tesma.com)と協力して、tesma プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる tesma のサインオン URL にリダイレクトされます。
- tesma のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した tesma に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで tesma タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した tesma に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/testim-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Testim を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/testim-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Testim の間にシングル サインオンを構成する方法について説明します。

この記事では、Testim と Microsoft Entra ID を統合する方法について説明します。 Testim は、最も回復力のある e2e テストを最もすばやく作成する方法です。 AI ベースのプラットフォームはワークフローに適合し、ソフトウェア リリースを高速化します。 Testim と Microsoft Entra ID を統合すると、次のことができます。

- Testim にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Testim に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Testim 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Testim では、 **SP** と **IDP** によって開始されるシングル サインオンの両方がサポートされます。

### [前提条件]

Microsoft Entra ID を Testim と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Testim でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Testim アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Testim を追加する

Microsoft Entra アプリケーション ギャラリーから Testim を追加して、Testim とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Testim**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://services.testim.io/auth/sso/<ID>/metadata`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://services.testim.io/auth/sso/<ID>/callback`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://app.testim.io/#/azure-signin`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Testim クライアント サポート チーム](mailto:support@testim.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Testim のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Testim SSO を構成する

**Testim** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Testim サポート チーム](mailto:support@testim.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Testim テスト ユーザーを作成する

このセクションでは、Testim で Britta Simon というユーザーを作成します。 [Testim サポート チーム](mailto:support@testim.io)と協力して、Testim プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Testim のサインオン URL にリダイレクトされます。
- Testim のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Testim に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Testim] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Testim に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/testingbot-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TestingBot を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/testingbot-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra ID と TestingBot の間のシングル サインオンを構成する方法について説明します。

この記事では、TestingBot と Microsoft Entra ID を統合する方法について説明します。 TestingBot を Microsoft Entra ID と統合すると、次のことができます。

- TestingBot にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して TestingBot に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TestingBot でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TestingBot では、**SP と IDP** の Initiated SSO がサポートされています。
- TestingBot では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから TestingBot を追加する

Microsoft Entra ID への TestingBot の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TestingBot を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TestingBot**」と入力します。
4. 結果のパネルから **[TestingBot]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TestingBot 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TestingBot に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと TestingBot の関連ユーザーとの間にリンク関係を確立する必要があります。

TestingBot に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TestingBot SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TestingBot テスト ユーザーの作成** - TestingBot で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**TestingBot**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクション上では、アプリケーションは事前に構成されており、必要な URL は Microsoft Entra によって既に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://testingbot.com/users/sign_in`」と入力します。
7. TestingBot アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]
8. その他に、TestingBot アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | User.mail |
    | 名（ファーストネーム） | User.givenname |
    | last\_name | User.surname |
9. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集を示すスクリーンショット。]
10. **[SAML 署名証明書]** セクションで **[Thumbprint Value](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/拇印の値)** をコピーし、お使いのコンピューターに保存します。

    [Image: 拇印の値のコピーを示すスクリーンショット。]
11. **[TestingBot の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは構成 URL をコピーするように示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TestingBot SSO を構成する

**TestingBot** 側でシングル サインオンを構成するには、**拇印の値**と、Microsoft Entra 管理センターからコピーした適切な URL を [TestingBot サポート チーム](mailto:info@testingbot.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### TestingBot テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを TestingBot に作成します。 TestingBot では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 TestingBot にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる TestingBot のサインオン URL にリダイレクトします。
- TestingBot のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した TestingBot に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TestingBot] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TestingBot に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/textexpander-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TextExpander を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/textexpander-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TextExpander 間にシングル サインオンを構成する方法について学習します。

この記事では、TextExpander と Microsoft Entra ID を統合する方法について説明します。 TextExpander を Microsoft Entra ID と統合すると、次のことができます。

- TextExpander にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Azure AD アカウントを使用して TextExpander に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TextExpander でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TextExpander では、**IDP** Initiated SSO がサポートされています。
- TextExpander では、**Just In Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから TextExpander を追加する

Microsoft Entra ID への TextExpander の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TextExpander を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TextExpander**」と入力します。
4. 結果のパネルから **[TextExpander]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TextExpander 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TextExpander に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TextExpander の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を TextExpander と組み合わせて構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TextExpander の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TextExpander テストユーザーの作成 - B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TextExpander**&gt;**シングルサインオン**を参照する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.textexpander.com/acs/<ORGID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.textexpander.com/acs/<ORGID>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[TextExpander クライアント サポート チーム](mailto:support@smilesoftware.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. TextExpander アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、TextExpander アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザー.メール |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[TextExpander のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TextExpander の SSO の構成

**TextExpander** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [TextExpander サポート チーム](mailto:support@smilesoftware.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TextExpander のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TextExpander に作成します。 TextExpander では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 TextExpander にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TextExpander に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TextExpander] タイルを選択すると、SSO を設定した TextExpander に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/textline-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Textline を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/textline-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Textline 間にシングル サインオンを構成する方法について説明します。

この記事では、Textline と Microsoft Entra ID を統合する方法について説明します。 Textline と Microsoft Entra ID を統合すると、次のことができます。

- Textline にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Textline に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Textline サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Textline では、 **IDP** Initiated SSO がサポートされます。
- Textline では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Textline を追加する

Microsoft Entra ID への Textline の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Textline を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Textline**」と入力します。
4. 結果パネルから **[テキスト行]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Textline 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Textline に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Textline の関連ユーザーとの間にリンク関係を確立する必要があります。

Textline に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Textline SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Textline テストユーザーを作成** - Textline において、B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Textline**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションが事前に構成されており、必要な URL が既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **テキストラインの設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Textline の SSO の構成

**Textline** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Textline サポート チーム](mailto:security@textline.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Textline のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Textline に作成します。 Textline では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Textline にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Textline に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Textline] タイルを選択すると、SSO を設定した Textline に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/textmagic-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TextMagic を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/textmagic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TextMagic 間にシングル サインオンを構成する方法について説明します。

この記事では、TextMagic と Microsoft Entra ID を統合する方法について説明します。 TextMagic を Microsoft Entra ID と統合すると、次のことができます。

- TextMagic にアクセスできるユーザーを Microsoft Entra ID 内で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して TextMagic に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TextMagic でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TextMagic では、**IDP** Initiated SSO がサポートされます。
- TextMagic では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの TextMagic の追加

Microsoft Entra ID への TextMagic の統合を構成するには、ギャラリーから、お使いの管理対象 SaaS アプリの一覧に TextMagic を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TextMagic**」と入力します。
4. 結果のパネルから **TextMagic** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TextMagic 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TextMagic で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TextMagic 内の関連ユーザーとの間にリンク関係を確立する必要があります。

TextMagic に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TextMagic の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TextMagic テスト ユーザーの作成 - TextMagic** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TextMagic**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    **[識別子]** ボックスに、`https://my.textmagic.com/saml/metadata` という URL を入力します。
6. TextMagic アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 TextMagic アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. その他に、TextMagic アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 | 名前空間 |
    | --- | --- | --- |
    | 会社 | ユーザー.companyname | https://schemas.xmlsoap.org/ws/2005/05/identity/claims |
    | ファーストネーム | User.givenname | https://schemas.xmlsoap.org/ws/2005/05/identity/claims |
    | 苗字 | User.surname | https://schemas.xmlsoap.org/ws/2005/05/identity/claims |
    | 電話 | ユーザー.電話番号 | https://schemas.xmlsoap.org/ws/2005/05/identity/claims |
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
9. **[TextMagic のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TextMagic の SSO の構成

1. 別の Web ブラウザーのウィンドウで、TextMagic 企業サイトに管理者としてサインインします
2. ユーザー名で **[Account settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント設定)** を選択します。

    [Image: ユーザーが [アカウント設定] を選択したことを示すスクリーンショット。]
3. [ **Single Sign-On (SSO)]** タブを選択し、次のフィールドに入力します。

    [Image: [Single Sign-On](シングル サインオン) タブのスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[ID プロバイダー エンティティ ID:]** テキストボックスに **Microsoft Entra ID** の値を貼り付けます。

    b。 **[ID プロバイダーの SSO URL:]** テキスト ボックスに、**ログイン URL** の値を貼り付けます。

    c. **[ID プロバイダー ログアウト URL:]** テキストボックスに **ログアウト URL** の値を貼り付けます。

    d. Azure Portal からダウンロードした **base-64 でエンコードされた証明書**をメモ帳で開き、その内容をクリップボードにコピーしてから、それを **[Public x509 certificate](パブリック x509 証明書)** ボックスに貼り付けます。

    え **保存** を選択します。

#### TextMagic テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを TextMagic に作成します。 TextMagic では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションでは、ユーザー側で必要な操作はありません。 TextMagic にユーザーがまだ存在していない場合は、認証後に新規作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TextMagic に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [TextMagic] タイルを選択すると、SSO を設定した TextMagic に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/textunited-translation-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TextUnited Translation を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/textunited-translation-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TextUnited Translation の間でシングル サインオンを構成する方法について説明します。

この記事では、TextUnited Translation と Microsoft Entra ID を統合する方法について説明します。 TextUnited Translation と Microsoft Entra ID を統合すると、次のことができます。

- TextUnited Translation にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して TextUnited Translation に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TextUnited Translation でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TextUnited Translation では、 **SP** によって開始される SSO のみがサポートされます。

### ギャラリーから TextUnited 翻訳を追加する

Microsoft Entra ID への TextUnited Translation の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TextUnited Translation を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックス**に「TextUnited Translation**」と入力します。
4. 結果パネルから **[TextUnited Translation]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TextUnited Translation の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、TextUnited Translation に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TextUnited Translation の関連ユーザーとの間にリンク関係を確立する必要があります。

TextUnited Translation に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TextUnited Translation SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TextUnited Translation のテスト ユーザーの作成** - TextUnited Translation で B.Simon に対応するユーザーを作成し、Microsoft Entra ID の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TextUnited Translation**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.textunited.com/my/ssoauth/azure/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.textunited.com/my/ssoauth/azure/<ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.textunited.com/my/login/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、TextUnited Translation サポート チーム](mailto:support@textunited.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TextUnited Translation SSO の構成

**TextUnited Translation** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[TextUnited Translation サポート チーム](mailto:support@textunited.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TextUnited Translation テスト ユーザーの作成

このセクションでは、TextUnited Translation で B.Simon というユーザーを作成します。 [TextUnited Translation サポート チーム](mailto:support@textunited.com)と協力して、TextUnited Translation プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる TextUnited Translation のサインオン URL にリダイレクトします。
- TextUnited Translation のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TextUnited Translation] タイルを選択すると、このオプションは TextUnited Translation のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/the-funding-portal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に The Funding Portal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/the-funding-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Funding Protal 間にシングル サインオンを構成する方法について説明します。

この記事では、The Funding Portal と Microsoft Entra ID を統合する方法について説明します。 The Funding Portal を Microsoft Entra ID と統合すると、次のことができます:

- The Funding Portal にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して The Funding Portal に自動的にサインインできるように設定できます。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- The Funding Portal でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- The Funding Portal では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの The Funding Portal の追加

Microsoft Entra ID への The Funding Portal の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に The Funding Portal を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**The Funding Portal**」と入力します。
4. 結果のパネルから **[The Funding Portal]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### The Funding Portal 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、The Funding Portal に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと The Funding Portal の関連ユーザーとの間にリンク関係を確立する必要があります。

The Funding Portal で Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **The Funding Portal の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **The Funding Portal のテスト ユーザーの作成** - The Funding Portal で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**ファンディングポータル**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<subdomain>.regenteducation.net`

    b。 **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<subdomain>.regenteducation.net/`

    注意

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[The Funding Portal クライアント サポート チーム](mailto:info@regenteducation.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[The Funding Portal のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### The Funding Portal の SSO の構成

**The Funding Portal** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [The Funding Portal サポート チーム](mailto:info@regenteducation.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### The Funding Portal のテスト ユーザーの作成

このセクションでは、The Funding Portal で Britta Simon というユーザーを作成します。 [The Funding Portal サポート チーム](mailto:info@regenteducation.com)と連携して、Funding Portal プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる The Funding Portal のサインオン URL にリダイレクトされます。
- The Funding Portal Sign-on URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Funding Portal] タイルを選択すると、このオプションは The Funding Portal のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/the-people-experience-hub-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に People Experience Hub を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/the-people-experience-hub-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と The People Experience Hub の間のシングル サインオンを構成する方法について説明します。

この記事では、People Experience Hub と Microsoft Entra ID を統合する方法について説明します。 The People Experience Hub と Microsoft Entra ID を統合すると、次のことができます。

- The People Experience Hub にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って The People Experience Hub に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な The People Experience Hub のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- The People Experience Hub では、**SP と IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの The People Experience Hub の追加

Microsoft Entra ID への The People Experience Hub の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に The People Experience Hub を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**The People Experience Hub**」と入力します。
4. 結果のパネルから **The People Experience Hub** を選んで、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### The People Experience Hub 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、The People Experience Hub に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと The People Experience Hub の関連ユーザーとの間にリンク関係を確立する必要があります。

The People Experience Hub に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **The People Experience Hub の SSO を構成する**- アプリケーション側でシングル サインオンの設定を構成します。
    1. **People Experience Hub のテスト ユーザーを作成する** - The People Experience Hub における B.Simon の対応ユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**People Experience Hub**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、URL を入力します。 `https://app.pxhub.io`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://auth.api.pxhub.io/v1/auth/saml/<COMPANY_ID>/assert`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://auth.api.pxhub.io/v1/auth/saml/<COMPANY_ID>/login`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[The People Experience Hub サポート チーム](mailto:it@pxhub.io)に連絡してください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット]
8. **[The People Experience Hub のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### The People Experience Hub の SSO を構成する

1. The People Experience Hub 企業サイトに管理者としてログインします。
2. **管理者設定**&gt;**統合**&gt;**シングルサインオン** に移動し、[**管理**] を選択します。

    [Image: 構成の設定を示すスクリーンショット。]
3. **[SAML 2.0 Single sign-on] (SAML 2.0 シングル サインオン)** ページで、次の手順を実行します。

    [Image: ページの構成を示すスクリーンショット。]

    1. **[Enable SAML 2.0 Single sign-on] (SAML 2.0 シングル サインオンを有効にする)** トグルをオンにします。
    2. **[EntityID] (エンティティ ID)** の値をコピーし、Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションの **[識別子]** テキストボックスにこの値を貼り付けます。
    3. **[Login URL] (ログイン URL)** の値をコピーし、Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションの **[サインオン URL]** テキストボックスにこの値を貼り付けます。
    4. **[応答 URL]** の値をコピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスにこの値を貼り付けます。
    5. **[SSO Login URL] (SSO ログイン URL)** テキストボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL** の値を貼り付けます。
    6. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[X509 Certificate] (X509 証明書)** テキストボックスに貼り付けます。
    7. [ **構成の保存] を選択します**。

#### The People Experience Hub のテスト ユーザーを作成する

1. 別の Web ブラウザー ウィンドウで、The People Experience Hub の Web サイトに管理者としてサインインします。
2. [ **管理者設定]**&gt;**[ユーザー** ]に移動し、[ **作成**] を選択します。

    [Image: スクリーンショットは、アプリケーションでユーザーを作成する方法を示しています。]
3. **[Create a new admin users] (新しい管理者ユーザーの作成)** セクションで、次の手順を実行します。

    [Image: ページで新しいユーザーを作成する方法を示すスクリーンショット。]

    1. **[Email] (電子メール)** テキストボックスに、ユーザーの有効なメール アドレスを入力します。
    2. **[First Name] (名)** テキストボックスに、ユーザーの名を入力します。
    3. **[Last Name] (姓)** テキストボックスに、ユーザーの姓を入力します。
    4. [ **ユーザーの作成] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる People Experience Hub のサインオン URL にリダイレクトします。
- The People Experience Hub のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した People Experience Hub に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [People Experience Hub] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した People Experience Hub に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/theom-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Theom を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/theom-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Theom 間のシングル サインオンを構成する方法について説明します。

この記事では、Theom を Microsoft Entra ID と統合する方法について説明します。 Theom は、データ クラウド、データ レイクハウスに対するアクティブな攻撃を検出し、侵害を防止します。 顧客は、修復に信頼できる環境を使用しながら、Theom の AI 脅威インテリジェンスをシームレスに使用できます。 Theom を Microsoft Entra ID と統合すると、次のことが可能になります。

- Theom にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Theom に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Theom に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストする。 Theom は **SP** 開始のシングル サインオンをサポートしています。

### [前提条件]

Microsoft Entra ID を Theom と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Theom でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Theom アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Theom を追加する

Microsoft Entra アプリケーション ギャラリーから Theom を追加し、Theom に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Theom**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`urn:auth0:theom:<connection-name>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://theom.us.auth0.com/login/callback?connection=<connection-name>`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_SUBDOMAIN>.theom.ai`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Theom クライアント サポート チーム](mailto:help@theom.ai)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Theom のセットアップ]** セクションで、要件に基づいて該当の URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Theom の SSO を構成する

**Theom** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした該当の URL を [Theom のサポート チーム](mailto:help@theom.ai)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Theom のテスト ユーザーを作成する

このセクションでは、Theom で Britta Simon というユーザーを作成します。 [Theom のサポート チーム](mailto:help@theom.ai)と協力して、Theom プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Theom のサインオン URL にリダイレクトされます。
- Theom のサインオン URL に直接アクセスし、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Theom] タイルを選択すると、このオプションは Theom のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/theorgwiki-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に TheOrgWiki を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/theorgwiki-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: TheOrgWiki に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、TheOrgWiki で実行する手順と、ユーザーやグループを自動的に TheOrgWiki にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- TheOrgWiki でユーザーを作成します。
- アクセスが不要になった場合は、TheOrgWiki のユーザーを削除します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [OrgWiki テナント](https://www.theorgwiki.com/welcome/)。
- Admin アクセス許可がある TheOrgWiki のユーザー アカウント。

### 手順 1: TheOrgWiki にユーザーを割り当てる

Microsoft Entra IDでは、割り当てと呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、TheOrgWiki へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定したら、「エンタープライズ アプリにユーザーまたはグループを割り当てる」の手順に従って、これらの [ユーザーやグループを TheOrgWiki に割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。

#### ユーザーを TheOrgWiki に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを TheOrgWiki に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- TheOrgWiki にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 2: プロビジョニング用に TheOrgWiki を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に TheOrgWiki を構成する前に、TheOrgWiki で SCIM プロビジョニングを有効にする必要があります。

1. [TheOrgWiki 管理コンソール](https://www.theorgwiki.com/login/)にサインインします。 **[管理コンソール] を選択します**。

    [Image: The Org Wiki のスクリーンショット。ユーザーのアバターと [管理コンソール] が選択されています。]
2. 管理コンソールで、[ **設定] タブを選択します**。

    [Image: The Org Wiki の [管理コンソール] のスクリーンショット。[設定] タブが選択されています。]
3. **[サービス アカウント]** に移動します。

    [Image: TheOrgWiki の [管理コンソール] の [サービス アカウント] ページのスクリーンショット。]
4. **[+サービス アカウント] を選択します**。 **[サービス アカウントの種類]** で、**[トークン ベース]** を選択します。 **保存** を選択します。

    [Image: [新しいサービス アカウント] ダイアログ ボックスのスクリーンショット。[Service Account Type](サービス アカウントの種類)、[Token Based](トークン ベース)、[保存] が選択されています。]
5. **アクティブ トークン**をコピーします。 この値を、TheOrgWiki アプリケーションの [プロビジョニング] タブ内の [シークレット トークン] フィールドに入力します。

    [Image: [SCIM プロビジョニング] ダイアログ ボックスのトークン管理のスクリーンショット。]

### 手順 3: ギャラリーから TheOrgWiki を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に TheOrgWiki を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に TheOrgWiki を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションに「**TheOrgWiki**」と入力し、結果パネルの **[TheOrgWiki]** を選びます。

    [Image: 結果一覧の TheOrgWiki のスクリーンショット。]
4. **[Sign-up for TheOrgWiki](TheOrgWiki にサインアップ)** ボタンを選択します。TheOrgWiki のログイン ページにリダイレクトされます。

    [Image: URL が呼び出された [Org Wiki] ログイン ページのスクリーンショット。]
5. 右上隅にある **[ログイン]** を選択します。

    [Image: ログイン ページの右上隅のスクリーンショット。[ログイン] オプションが選択されています。]
6. TheOrgWiki は OpenIDConnect アプリであるため、Microsoft職場アカウントを使用して OrgWiki にログインすることを選択します。

    [Image: Microsoft でサインインするオプションが強調されている Org Wiki のサインインページのスクリーンショット]
7. 認証が成功すると、アプリケーションが自動的にテナントに追加され、TheOrgWiki アカウントにリダイレクトされます。

    [Image: OrgWiki Add SCIM のスクリーンショット。]

### 手順 4: TheOrgWiki への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDでのユーザーやグループの割り当てに基づいて TheOrgWiki のユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで TheOrgWiki の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[TheOrgWiki]** を選択します。

    [Image: アプリケーションの一覧の OrgWiki リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [**管理者資格情報**] セクションで、[`https://<TheOrgWiki Subdomain 		value>.theorgwiki.com/api/v2/scim/v2/`] に「」と入力します。

    例: `https://test1.theorgwiki.com/api/v2/scim/v2/`

    注

    **[Subdomain Value](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サブドメイン値)** は、TheOrgWiki の最初のサインアップ プロセスの間にしか設定できません。
7. TheOrgWiki から先ほど取得した [ **シークレット トークン** ] フィールドにトークン値を入力します。 **Test Connection** を選択して、Microsoft Entra IDが TheOrgWiki に接続できることを確認します。 接続できない場合は、使用中の TheOrgWiki アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute- Mapping** セクションで、Microsoft Entra IDから TheOrgWiki に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で TheOrgWiki のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: TheOrgWiki ユーザー属性のスクリーンショット。] .
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/thirdlight-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ThirdLight を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thirdlight-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: この記事では、Microsoft Entra ID と ThirdLight の間でシングル サインオンを構成する方法について説明します。

この記事では、ThirdLight と Microsoft Entra ID を統合する方法について説明します。 ThirdLight を Microsoft Entra ID と統合すると、次のことができます:

- ThirdLight にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って ThirdLight に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

ThirdLight と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra の環境がない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオンが有効な ThirdLight サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ThirdLight では、SP Initiated SSO がサポートされます。

### ギャラリーからの ThirdLight の追加

Microsoft Entra ID への ThirdLight の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ThirdLight を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ThirdLight**」と入力します。
4. 結果パネルから **[ThirdLight]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ThirdLight 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ThirdLight で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ThirdLight の関連ユーザーの間にリンク関係を確立する必要があります。

ThirdLight に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ThirdLight SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ThirdLight テスト ユーザーの作成** - Microsoft Entra のユーザー B.Simon に対応する ThirdLight ユーザーを作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ThirdLight**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** ダイアログ ボックスで、次の手順を実行します:

    1. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します: `https://<subdomain>.thirdlight.com/saml/sp`
    2. **[サインオン URL]** ボックスに、`https://<subdomain>.thirdlight.com/` という形式で URL を入力します。

        注

        これらの値はプレースホルダーです。 実際の識別子とサインオン URL を使用する必要があります。 この値を取得するには、[ThirdLight サポート チーム](https://www.thirdlight.com/support)に問い合わせてください。 **[基本的な SAML 構成]** ダイアログ ボックスに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、実際の要件に従って、 **[フェデレーション メタデータ XML]** の横にある **[ダウンロード]** リンクを選択し、ファイルを自分のコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Set up ThirdLight](ThirdLight の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### ThirdLight SSO の構成

1. 新しい Web ブラウザー ウィンドウで、ThirdLight 企業サイトに管理者としてサインインします。
2. **[Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成)**&gt;**[System Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム管理)**&gt;**[SAML2]** の順に移動します。

    [Image: システム管理を示すスクリーンショット。]
3. SAML2 の構成セクションで、次の手順を実行します。

    [Image: SAML 構成セクションを示すスクリーンショット。]

    1. **[Enable SAML2 Single Sign-On]**を選択します。
    2. **[Source for IdP Metadata](IdP メタデータのソース)** で、**[Load IdP Metadata from XML](XML から IdP メタデータをダウンロードする)** を選択します。
    3. 前のセクションでダウンロードしたメタデータ ファイルを開きます。 **[IdP Metadata XML](IdP メタデータ XML)** ボックスにファイルの内容をコピーして貼り付けます。
    4. **[Save SAML2 settings](SAML2 設定の保存)** を選択します。

#### ThirdLight のテスト ユーザーの作成

Microsoft Entra ユーザーが ThirdLight にサインインできるようにするには、そのユーザーを ThirdLight に追加する必要があります。 手動で追加する必要があります。

ユーザー アカウントを作成するには、次の手順を実行します。

1. ThirdLight 企業サイトに管理者としてサインインします。
2. **[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** タブに移動します。
3. **ユーザーとグループ** を選択します。
4. **[Add new User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの追加)** を選択します。
5. プロビジョニングする有効な Microsoft Entra アカウントのユーザー名、名前または説明、メール アドレスを入力します。 新しいメンバーのプリセットまたはグループを選択します。
6. **を選択して**を作成します。

注

ThirdLight から提供されているユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ThirdLight のサインオン URL にリダイレクトされます。
- ThirdLight のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ThirdLight] タイルを選択すると、このオプションは ThirdLight のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/thirdpartytrust-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ThirdPartyTrust を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thirdpartytrust-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ThirdPartyTrust の間でシングル サインオンを構成する方法について説明します。

この記事では、ThirdPartyTrust と Microsoft Entra ID を統合する方法について説明します。 ThirdPartyTrust を Microsoft Entra ID と統合すると、次のことができます:

- ThirdPartyTrust にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って ThirdPartyTrust に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ThirdPartyTrust でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ThirdPartyTrust では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ThirdPartyTrust の追加

Microsoft Entra ID への ThirdPartyTrust の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ThirdPartyTrust を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**ThirdPartyTrust**」と入力します。
4. 結果のパネルから **[ThirdPartyTrust]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ThirdPartyTrust 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、ThirdPartyTrust に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ThirdPartyTrust の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を ThirdPartyTrust と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ThirdPartyTrust SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ThirdPartyTrust テスト ユーザーの作成 - ThirdPartyTrust** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**ThirdPartyTrust**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://api.thirdpartytrust.com/sai3/saml/metadata`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://api.thirdpartytrust.com/sai3/test`
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[ThirdPartyTrust のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ThirdPartyTrust SSO の構成

**ThirdPartyTrust** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [ThirdPartyTrust サポート チーム](mailto:support@thirdpartytrust.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ThirdPartyTrust テスト ユーザーの作成

このセクションでは、ThirdPartyTrust で Britta Simon というユーザーを作成します。 [ThirdPartyTrust サポート チーム](mailto:support@thirdpartytrust.com)と連携して、ThirdPartyTrust プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ThirdPartyTrust のサインオン URL にリダイレクトされます。
- ThirdPartyTrust のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ThirdPartyTrust に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ThirdPartyTrust] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ThirdPartyTrust に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/thomson-reuters-account-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Thomson Reuters アカウントを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thomson-reuters-account-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-07
- Summary: Microsoft Entra ID と Thomson Reuters Account の間にシングル サインオンを構成する方法について説明します。

この記事では、Thomson Reuters アカウントと Microsoft Entra ID を統合する方法について説明します。 Thomson Reuters Account と Microsoft Entra ID を統合すると、ユーザーは、組織がサブスクライブしている Thomson Reuters の幅広いアプリケーションでシームレスなシングル サインオン エクスペリエンスを実現できます。 また、次のことができます。

- Thomson Reuters Account にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Thomson Reuters Account に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Thomson Reuters Account でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから Thomson Reuters Account を追加する

Microsoft Entra ID への Thomson Reuters Account の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Thomson Reuters Account を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**+ New application** に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックス**に「Thomson Reuters Account**」と入力します。
4. 結果パネルで **[Thomson Reuters Account]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Thomson Reuters アカウント**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **識別子 (エンティティ ID)**値については、次のように構成します。

    - **[識別子 (エンティティ ID)]** で`trtasso.thomson.com` (既定) を選択し、手順 5.b に進みます。
    - [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL: `trtasso.thomson.com_TRAccount `を入力します。

    Note

    Thomson Reuters 製品へのアクセスに使用される既存の SSO 構成がある場合、識別子/エンティティ ID として `trtasso.thomson.com` を選択して保存することはできません。 このような場合は、次のエラーが発生します。 そのため、既定の `trtasso.thomson.com_TRAccount` に切り替えるには、その横にあるチェックボックスをオンにして、 `trtasso.thomson.com` エンティティ ID を削除します。それ以外の場合は、構成を保存できません。

    [Image: 識別子のチェック ボックスを示すスクリーンショット。]

    b。 **[応答 URL]** ボックスに、URL として「`https://trtasso.thomson.com/sp/ACS.saml2`」と入力します。
6. Thomson Reuters Account アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 Thomson Reuters Account アプリケーションでは **、nameidentifier** が **user.objectid** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    | 名前 | Microsoft Entra ID属性マッピング | 価値 |
    | --- | --- | --- |
    | 一意のユーザー識別子 (Name ID) | user.objectid (ユーザーのオブジェクトID) | 一意で永続的な値。 時間の経過と同時に変更される可能性があるメール アドレスは使用しないでください。 |
    | メールアドレス | ユーザーのメールアドレス (user.emailaddress) | ユーザーのメール アドレス |
    | givenname | User.givenname | ユーザーの名 |
    | 名字 | ユーザーの名字 | ユーザーの姓 |
    | name | user.displayname | ユーザーのフル ネーム |
7. として`trtasso.thomson.com_TRAccount`選択した場合は、[**属性と要求**] で **[編集**] オプションを選択し、次のページで [**詳細設定**] を選択して展開し、[**SAML** 要求の詳細設定] オプションのすぐ横にある **[編集]** オプションを選択します。 これを行うと、ペインが右側に表示され、そこから **発行者にアプリケーション ID を追加** し、[ **保存**] を選択する必要があります。
8. [ **SAML でのシングル サインオンの設定** ] ページの [SAML 署名証明書] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL をコピーします**。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Thomson Reuters Account SSO を構成する

Thomson Reuters 側でシングル サインオンを構成するには、SSO の設定に協力している Thomson Reuters の担当者に次の詳細を送信する必要があります。

1. Microsoft Entra IDの構成の **App フェデレーション メタデータ URL**。
2. 選択された **識別子 (エンティティ ID)** (`trtasso.thomson.com` または `trtasso.thomson.com_TRAccount`)。
3. Thomson Reuters アプリケーションにアクセスする組織のユーザーの **電子メール ドメイン** (これは、これらの電子メール ドメインの SSO を有効にするために Thomson Reuters チームによって使用されます)。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/thoropass-tutorial"} -->
## Microsoft Entra ID で Thoropass for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thoropass-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-12
- Summary: Microsoft Entra ID と Thoropass の間のシングル サインオンを構成する方法について説明します。

この記事では、Thoropass と Microsoft Entra ID を統合する方法について説明します。 Thoropass と Microsoft Entra ID を統合すると、次のことができます。

- Thoropass にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Thoropass に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Thoropass サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Thoropass では、**SP Initiated SSO と IDP Initiated SSO** がサポートされています。
- Thoropass では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Thoropass を追加する

Microsoft Entra ID への Thoropass の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Thoropass を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Thoropass**」と入力します。
4. 結果パネルから **Thoropass** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Thoropass での Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Thoropass による Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Thoropass の関連ユーザーとの間にリンク関係を確立する必要があります。

Thoropass による Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Thoropass SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Thoropassのテストユーザーを作成し**、Microsoft Entra ID のユーザーとリンクされる B.Simon の対となるユーザーを Thoropass 内に設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Thoropass**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクション上では、アプリケーションは事前に構成されており、必要な URL は Microsoft Entra によって既に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://login.thoropass.com`」と入力します。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Thoropass SSO の構成

**Thoropass** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Thoropass サポート チーム](mailto:support@thoropass.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Thoropass テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Thoropass に作成します。 Thoropass では Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Thoropass に存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、マイ アプリを使って Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Thoropass のサインオン URL にリダイレクトします。
- Thoropass のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Thoropass に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Thoropass] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Thoropass に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/thoughtworks-mingle-tutorial"} -->
## Microsoft Entra ID で Thoughtworks Mingle for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thoughtworks-mingle-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Thoughtworks Mingle の間のシングル サインオンを構成する方法について説明します。

この記事では、Thoughtworks Mingle と Microsoft Entra ID を統合する方法について説明します。 Thoughtworks Mingle を Microsoft Entra ID と統合すると、次のことができます。

- Thoughtworks Mingle にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Thoughtworks Mingle に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Thoughtworks Mingle でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Thoughtworks Mingle では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Thoughtworks Mingle の追加

Microsoft Entra ID への Thoughtworks Mingle の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Thoughtworks Mingle を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「 **Thoughtworks Mingle** 」と入力します。
4. 結果パネルから **Thoughtworks Mingle** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Thoughtworks Mingle 用に Microsoft Entra SSO を構成してテストする

**B.Simon**というテスト ユーザーを使用して、Thoughtworks Mingle に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Thoughtworks Mingle の関連するユーザーの間のリンク関係を確立する必要があります。

Thoughtworks Mingle に対する Microsoft Entra SSO を構成してテストするには、次のステップを実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Thoughtworks Mingle SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Thoughtworks Mingle テスト ユーザーの作成** - Thoughtworks Mingle で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Thoughtworks Mingle]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集のスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.mingle.thoughtworks.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、Thoughtworks Mingle クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. **[Thoughtworks Mingle のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Thoughtworks Mingle SSO の構成

1. **Thoughtworks Mingle** 企業サイトに管理者としてサインインします。
2. [ **管理** ] タブを選択し、[ **SSO Config]** を選択します。

    [Image: [管理] タブのスクリーンショット。]
3. [**SSO 構成**] セクションで、以下の手順を実行します。

    [Image: SSO Config のスクリーンショット。]

    a. メタデータ ファイルをアップロードするには、[ **ファイルの選択**] を選択します。

    b。 [ **変更の保存] を選択します**。

#### Thoughtworks Mingle テスト ユーザーの作成

Microsoft Entra ユーザーがサインインできるようにするには、Azure Active Directory ユーザー名を使用して、Microsoft Entra ユーザーを Thoughtworks Mingle アプリケーションにプロビジョニングする必要があります。 Thoughtworks Mingle の場合、プロビジョニングは手動で行います。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. Thoughtworks Mingle 企業サイトに管理者としてサインインします。
2. **[プロファイル] を選択します**。

    [Image: 最初のプロジェクトのスクリーンショット。]
3. [ **管理者** ] タブを選択し、[ **ユーザー**] を選択します。

    [Image: [ユーザー] のスクリーンショット。]
4. **新しいユーザー**を選択します。

    [Image: [新しいユーザー] のスクリーンショット。]
5. **[New User]** ダイアログ ページで、次の手順に従います。

    [Image: [新しいユーザー] ダイアログのスクリーンショット。]

    a. プロビジョニングする有効な Microsoft Entra アカウントの**サインイン名**、**表示名**、**パスワードの選択**、**パスワードの確認**を該当するボックスに入力します。

    b。 [**ユーザーの種類**] として、[**完全なユーザー**] を選択します。

    c. [ **このプロファイルの作成**] を選択します。

注

他の Thoughtworks Mingle ユーザー アカウント作成ツールや、Thoughtworks Mingle から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Thoughtworks Mingle のサインオン URL にリダイレクトされます。
- Thoughtworks Mingle のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Thoughtworks Mingle] タイルを選択すると、Thoughtworks Mingle のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/thousandeyes-provisioning-tutorial"} -->
## Microsoft Entra IDを使用して ThousandEyes へのユーザー プロビジョニングを自動化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thousandeyes-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントを ThousandEyes に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、ThousandEyes で実行する必要がある手順と、ユーザー アカウントを Microsoft Entra ID から ThousandEyes に自動的にプロビジョニングおよびプロビジョニング解除するためにMicrosoft Entra IDを示することです。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Standard プラン](https://www.thousandeyes.com/pricing)以上が利用可能な ThousandEyes テナント
- Admin アクセス許可がある ThousandEyes のユーザー アカウント

注

Microsoft Entra プロビジョニング統合は、Standard プラン以上の ThousandEyes チームが利用できる [ThousandEyes SCIM API](https://success.thousandeyes.com/PublicArticlePage?articleIdParam=kA044000000CnWrCAK) に依存します。

### 手順 1: ThousandEyes にユーザーを割り当てる

Microsoft Entra IDでは、"割り当て" という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに "割り当てられている" ユーザーとグループのみが同期されます。

構成を行いプロビジョニング サービスを有効にする前に、Microsoft Entra ID内で、ThousandEyesアプリにアクセスが必要なユーザーやグループを決定する必要があります。 決定し終えたら、次の手順でこれらのユーザーを ThousandEyes アプリに割り当てることができます。

[エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを ThousandEyes に割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、1 人のMicrosoft Entra ユーザーを ThousandEyes に割り当てることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- ThousandEyes にユーザーを割り当てるときは、**ユーザー** ロールまたは別の有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **[既定のアクセス]** ロールはプロビジョニングでは使うことができず、このロールのユーザーはスキップされます。

### 手順 2: ThousandEyes へのユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDを ThousandEyes のユーザー アカウント プロビジョニング API に接続し、プロビジョニング サービスを構成して、Microsoft Entra IDのユーザーとグループの割り当てに基づいて、ThousandEyes で割り当てられたユーザー アカウントを作成、更新、無効化する方法について説明します。

ヒント

また、[Azure ポータル](https://portal.azure.com)に記載されている手順に従って、ThousandEyes に対して SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で ThousandEyes への自動ユーザー アカウント プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. シングル サインオンのために ThousandEyes を既に構成している場合は、検索フィールドで ThousandEyes のインスタンスを検索します。 それ以外の場合は、**[追加]** を選択してアプリケーションライブラリで **ThousandEyes** を検索します。 検索結果から ThousandEyes を選択してアプリケーションの一覧に追加します。

    [Image: アプリケーションの一覧の ThousandEyes リンクのスクリーンショット。]
4. ThousandEyes のインスタンスを選択してから、 **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、ThousandEyes のアカウントによって生成された **OAuth ベアラー トークン** を入力します (ThousandEyes アカウント **プロファイル** セクションでトークンを見つけて生成できます)。

    [Image: 現在のアカウント グループの [アカウント設定] リンクが表示されている場所を示すスクリーンショット。]
7. **Test Connection** を選択して、Microsoft Entra IDが ThousandEyes アプリに接続できることを確認します。 接続できない場合は、使用中の ThousandEyes アカウントに管理者アクセス許可があることを確認してから、手順 5 をもう一度試します。
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. Microsoft Entra IDから ThousandEyes に同期されるユーザー属性を、**Attribute-Mapping** セクションで確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で ThousandEyes のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、ThousandEyes API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | externalId | 糸 | ✓ |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | emails[type eq "work"].value | 糸 |  |
    | name.formatted | 糸 |  |
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)を使用して、正常にプロビジョニングされたユーザーと失敗したユーザーを特定します。
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/thousandeyes-tutorial"} -->
## Microsoft Entra ID を使用して ThousandEyes のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thousandeyes-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ThousandEyes 間にシングル サインオンを構成する方法について学習します。

この記事では、ThousandEyes と Microsoft Entra ID を統合する方法について説明します。 ThousandEyes を Microsoft Entra ID と統合すると、次のことができます。

- ThousandEyes にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って ThousandEyes に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ThousandEyes でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ThousandEyes では、**SP と IDP** Initiated SSO がサポートされます。
- ThousandEyes では、[**自動化された**ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thousandeyes-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ThousandEyes の追加

Microsoft Entra ID への ThousandEyes の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ThousandEyes を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ThousandEyes**」と入力します。
4. 結果のパネルから **[ThousandEyes]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ThousandEyes 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ThousandEyes に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ThousandEyes の関連ユーザーとの間にリンク関係を確立する必要があります。

ThousandEyes に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ThousandEyes の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ThousandEyes テストユーザーの作成 - Microsoft Entra における B.Simon の表現にリンクするための、ThousandEyes 内での B.Simon に対応するユーザーを作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ThousandEyes**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.thousandeyes.com/login/sso`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[ThousandEyes のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ThousandEyes の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として **ThousandEyes** 企業サイトにサインオンします。
2. 上部のメニューで**設定**を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) が選択されている ThousandEyes サイトを示すスクリーンショット。]
3. **アカウントの**選択

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) メニューから [Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント) が選択されていることを示すスクリーンショット。]
4. セキュリティ & 認証 タブを選択します。

    [Image: セキュリティと認証]
5. **[Setup Single Sign-On]** セクションで、次の手順を実行します。

    [Image: [Setup Single Sign-On](シングル サインオンの設定)]

    ある。 [ **シングル サインオンを有効にする] を選択します**。

    b。 **[ログイン ページ URL]** テキストボックスに、**ログイン URL** を貼り付けます。

    c. **[ログアウト ページ URL]** テキストボックスに **ログアウト URL** を貼り付けます。

    d. **[ID プロバイダーの発行者]** テキストボックスに、**Microsoft Entra 識別子** を貼り付けます。

    え **[検証証明書**] で [**ファイルの選択**] を選択し、前にダウンロードした証明書をアップロードします。

    f. **保存** を選択します。

#### ThousandEyes テスト ユーザーの作成

このセクションの目的は、ThousandEyesで Britta Simon というユーザーを作成することです。 ThousandEyes では、自動ユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thousandeyes-provisioning-tutorial) 。

**ユーザーを手動で作成する必要がある場合は、次の手順を実行します。**

1. ThousandEyes 企業サイトに管理者としてサインインします。
2. **設定**を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) が選択されている ThousandEyes サイトを示すスクリーンショット。]
3. **アカウント** を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) メニューから [Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント) が選択されていることを示すスクリーンショット。]
4. [ **アカウントとユーザー** ] タブを選択します。

    [Image: アカウントとユーザー]
5. [ **ユーザーとアカウントの追加** ] セクションで、次の手順を実行します。

    [Image: ユーザー アカウントの追加]

    ある。 **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** ボックスに、ユーザーの名前を入力します (**B.Simon** など)。

    b。 **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** ボックスに、ユーザーのメール アドレス (b.simon@contoso.com など) を入力します。

    b。 [ **アカウントに新しいユーザーを追加] を選択します**。

    注

    Microsoft Entra アカウント所有者は、アカウントを確認してアクティブ化するためのリンクを含む電子メールを受け取ります。

注

ThousandEyes から提供されている他の ThousandEyes ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ThousandEyes のサインオン URL にリダイレクトされます。
- ThousandEyes のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ThousandEyes に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで ThousandEyes タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ThousandEyes に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/threatq-platform-tutorial"} -->
## Microsoft Entra ID を使用して ThreatQ Platform for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/threatq-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ThreatQ Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、ThreatQ Platform と Microsoft Entra ID を統合する方法について説明します。 ThreatQ では、さまざまなデータ ソース、ツール、チームを融合して脅威の検出、調査、対応を加速および自動化することで、セキュリティ運用の効率と有効性が向上します。 ThreatQ Platform を Microsoft Entra ID を統合すると、次のことができます。

- ThreatQ Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ThreatQ Platform に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で ThreatQ Platform 向けの Microsoft Entra のシングル サインオンを構成してテストします。 ThreatQ Platform では、 **SP** によって開始されるシングル サインオンと **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### [前提条件]

Microsoft Entra ID を ThreatQ Platform と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な ThreatQ Platform のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから ThreatQ Platform アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから ThreatQ Platform を追加する

Microsoft Entra アプリケーション ギャラリーから ThreatQ Platform を追加して、ThreatQ Platform でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ThreatQ Platform**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer_Environment>.threatq.online/api/saml/metadata`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer_Environment>.threatq.online/api/saml/acs`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer_Environment>.threatq.online/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、ThreatQ Platform サポート チーム](mailto:support@threatq.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. ThreatQ Platform アプリケーションでは、特定の形式の SAML アサーションが必要とされるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、ThreatQ Platform では、これがユーザーの電子メール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、ThreatQ Platform アプリケーションでは、さらにいくつかの属性が SAML 応答で返されることが想定されています。それらの属性を下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | uid (ユーザー識別子) | ユーザーのメールアドレス |
    | グループ | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **ThreatQ Platform のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### ThreatQ Platform SSO の構成

**ThreatQ Platform** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と、アプリケーション構成からコピーした適切な URL を [ThreatQ Platform サポート チーム](mailto:support@threatq.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ThreatQ Platform のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを ThreatQ Platform に作成します。 ThreatQ Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ThreatQ Platform にユーザーがまだ存在していない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる ThreatQ Platform のサインオン URL にリダイレクトされます。
- ThreatQ Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ThreatQ Platform] タイルを選択すると、このオプションは ThreatQ Platform のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/thrive-lxp-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Thrive LXP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thrive-lxp-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: Microsoft Entra IDから Thrive LXP にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、Thrive LXP と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、[Thrive LXP](https://thrivelearning.com/) にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Thrive LXP でユーザーを作成する
- アクセスが不要になった場合に Thrive LXP のユーザーを削除する
- Microsoft Entra IDと Thrive LXP の間でユーザー属性の同期を維持する
- Thrive LXP でグループとグループ メンバーシップをプロビジョニングする
- Thrive LXP への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thrive-lxp-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 連絡先が THRIVE LXP で提供する **SCIM トークン**。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとThrive LXPの間でマッピングするデータを決定する。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Thrive LXP を構成する

[Thrive LXP クライアント サポート チーム](mailto:support@thrivelearning.com)に連絡して、**テナント URL** と**シークレット トークン**を生成します。 これらの値は、Thrive LXP アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドと [シークレット トークン] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Thrive LXP を追加する

Microsoft Entra アプリケーション ギャラリーから Thrive LXP を追加して、Thrive LXP へのプロビジョニングの管理を開始します。 SSO のために Thrive LXP を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Thrive LXP への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Thrive LXP の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Thrive LXP]** を選択します。

    [Image: アプリケーションの一覧の [Thrive LXP] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、Thrive LXP テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Thrive LXP に接続できることを確認します。 接続できない場合は、使用中の Thrive LXP アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Thrive LXP に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Thrive LXP のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Thrive LXP API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | タイトル | 糸 |  |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | タイムゾーン | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |
12. **[グループ] を選択します**。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Thrive LXP に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Thrive LXP のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/thrive-lxp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Thrive LXP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thrive-lxp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Thrive LXP 間のシングル サインオンを構成する方法について説明します。

この記事では、Thrive LXP と Microsoft Entra ID を統合する方法について説明します。 Thrive LXP を Microsoft Entra ID と統合すると、次のことが可能になります。

- Thrive LXP にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Thrive LXP に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Thrive LXP でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Thrive LXP では、**SP** 開始 SSO がサポートされます。
- Thrive LXP では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thrive-lxp-provisioning-tutorial)がサポートされます。

### ギャラリーからの Thrive LXP の追加

Microsoft Entra ID への Thrive LXP の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Thrive LXP を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Thrive LXP**」と入力します。
4. 結果のパネルから **[Thrive LXP]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Thrive LXP に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Thrive LXP に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Thrive LXP の関連ユーザー間にリンク関係を確立する必要があります。

Thrive LXP に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Thrive LXP SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Thrive LXP のテスト ユーザーの作成** - Thrive LXP で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Thrive LXP**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:amazon:cognito:sp:<THRIVE_LXP_IDENTIFIER>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>-lxp.auth.eu-west-2.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.learn.link`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[Thrive LXP クライアント サポート チーム](mailto:support@thrivelearning.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Thrive LXP のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Thrive LXP SSO の構成

**Thrive LXP** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Thrive LXP サポート チーム](mailto:support@thrivelearning.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Thrive LXP テスト ユーザーの作成

このセクションでは、Thrive LXP で Britta Simon というユーザーを作成します。 [Thrive LXP サポート チーム](mailto:support@thrivelearning.com)と連携して、Thrive LXP プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Thrive LXP では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/thrive-lxp-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Thrive LXP サインオン URL にリダイレクトされます。
- Thrive LXP のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Thrive LXP] タイルを選択すると、このオプションは Thrive LXP のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tic-tac-mobile-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Tic-Tac Mobile を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tic-tac-mobile-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: Microsoft Entra ID から Tic-Tac Mobile に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために、Tic-Tac Mobile と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Tic-Tac Mobile](https://www.tictacmobile.com/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの動作、しくみ、よく寄せられる質問については、「 [Microsoft Entra ID を使用してサービスとしてのソフトウェア (SaaS) アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)する」を参照してください。

### サポートされている機能

- Tic-Tac Mobile のユーザーを作成する。
- アクセスが不要になった Tic-Tac Mobile のユーザーを削除する。
- Microsoft Entra ID と Tic-Tac Mobile の間でユーザー属性の同期を維持します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- スーパー管理者ロールを持つ [Tic-Tac Mobile](https://www.tictacmobile.com/) アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Tic-Tac Mobile の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Tic-Tac Mobile を構成する

 support@tictacmobile.com に連絡して、**テナント URL** と**シークレット トークン**を入手してください。 トークンを受け取るには、Tic-Tac Mobile のスーパー管理者ロールを持っている必要があります。 トークンは、Tic-Tac Mobile アプリケーションの [**プロビジョニング**] タブの [**シークレット トークン**] ボックスに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Tic-Tac Mobile を追加する

Microsoft Entra アプリケーション ギャラリーから Tic-Tac Mobile を追加して、Tic-Tac Mobile へのプロビジョニングの管理を開始します。 シングル サインオンのために Tic-Tac Mobile を以前に設定している場合は、同じアプリケーションを使用できます。 統合を初めてテストするときは、別のアプリを作成してください。 ギャラリーからアプリケーションを追加する方法について詳しくは、「[スコープ フィルターを使用した属性ベースのアプリケーション プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)」を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Tic-Tac Mobile への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づいて TestApp でユーザーが作成、更新、無効化されるように、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Tic-Tac Mobile の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ウィンドウを示すスクリーンショット。]
3. アプリケーションの一覧で **Tic-Tac Mobile** を選択します。

    [Image: アプリケーションの一覧に表示される Tic-Tac Mobile のリンクを示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Tic-Tac モバイル テナント URL とシークレット トークンを入力します。 **[接続テスト]** を選択して、Microsoft Entra ID が Tic-Tac Mobile に接続できることを確認します。 接続に失敗した場合は、Tic-Tac Mobile アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Tic-Tac Mobile に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Tic-Tac Mobile のユーザー アカウントとの照合に使用されます。 [照合する対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づいたユーザーのフィルター処理が Tic-Tac Mobile API で確実にサポートされている必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | externalId | 糸 |
    | タイトル | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | 優先言語 | 糸 |
    | externalId | 糸 |
    | ユーザータイプ | 糸 |
    | ロケール | 糸 |
    | タイムゾーン | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ticketmanager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TicketManager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ticketmanager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TicketManager 間でシングル サインオンを構成する方法について説明します。

この記事では、TicketManager と Microsoft Entra ID を統合する方法について説明します。 TicketManager を Microsoft Entra ID と統合すると、次のことができます。

- TicketManager にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで TicketManager に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TicketManager でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TicketManager では、**SPおよびIDP**の開始によるSSOをサポートします。
- TicketManager では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの TicketManager の追加

Microsoft Entra ID への TicketManager の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに TicketManager を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「TicketManager**」と入力します。
4. 結果パネルから **TicketManager** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TicketManager 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TicketManager に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと TicketManager の関連ユーザーとの間にリンク関係を確立する必要があります。

TicketManager に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TicketManager SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TicketManager のテストユーザーを作成** - Microsoft Entra におけるユーザーの表示として、TicketManager に B.Simon の対応ユーザーを作ります。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**TicketManager**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集のスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.spotlighttms.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.spotlighttms.com/Shibboleth.sso/SAML2/POST`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.spotlighttms.com`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、TicketManager クライアント サポート チーム](mailto:help@ticketmanager.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. TicketManager アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 TicketManager アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集** ] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 属性のスクリーンショット。]
8. その他に、TicketManager アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | uid (ユーザー識別子) | ユーザー.ユーザープリンシパルネーム |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
10. [ **TicketManager のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TicketManager SSO の構成

**TicketManager** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [TicketManager サポート チーム](mailto:help@ticketmanager.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### TicketManager テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TicketManager に作成します。 TicketManager では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 TicketManager にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる TicketManager のサインオン URL にリダイレクトされます。
- TicketManager のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TicketManager に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TicketManager] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TicketManager に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tickitlms-learn-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TickitLMS Learn を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tickitlms-learn-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TickitLMS Learn の間でシングル サインオンを構成する方法について説明します。

この記事では、TickitLMS Learn と Microsoft Entra ID を統合する方法について説明します。 TickitLMS Learn と Microsoft Entra ID を統合すると、次のことができます。

- TickitLMS Learn にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して TickitLMS Learn に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TickitLMS Learn でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TickitLMS Learn では、**SP および IDP** 開始 SSO がサポートされます

### ギャラリーからの TickitLMS Learn の追加

Microsoft Entra ID への TickitLMS Learn の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TickitLMS Learn を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TickitLMS Learn**」と入力します。
4. 結果パネルから**TickitLMS Learn**を選択し、その後アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TickitLMS Learn の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、TickitLMS Learn に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TickitLMS Learn の関連ユーザーとの間にリンク関係を確立する必要があります。

TickitLMS Learn に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TickitLMS Learn の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **TickitLMS Learn のテストユーザーを作成** - Microsoft Entra で B.Simon に対応するユーザーを TickitLMS Learn に作成してリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**TickitLMS Learn**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://learn.tickitlms.com/sso/login` を入力します。
7. **保存** を選択します。
8. TickitLMS Learn アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. 上記に加えて、TickitLMS Learn アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | SAMLアカウント | ユーザーのSAMLアカウント |
    | 従業員ID | ユーザー.社員ID |
    | ロール | ユーザーの役割 |
    | 部署 | ユーザーの部署 |
    | 報告先 | ユーザー.報告する先 |

    手記

    TickitLMS Learn では、アプリケーションに割り当てられているユーザーのロールが必要です。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定してください。 Microsoft Entra ID でロールを構成する方法については、[の](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)を参照してください。
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TickitLMS Learn の SSO の構成

**TickitLMS Learn** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [TickitLMS Learn サポート チーム](mailto:support@tickitlms.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TickitLMS Learn テスト ユーザーの作成

このセクションでは、TickitLMS Learn で Britta Simon というユーザーを作成します。 [TickitLMS Learn サポート チーム](mailto:support@tickitlms.com) と連携して、TickitLMS Learn プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TickitLMS Learn のサインオン URL にリダイレクトされます。
- TickitLMS Learn のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TickitLMS Learn に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで TickitLMS Learn タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TickitLMS Learn に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tidemark-tutorial"} -->
## Microsoft Entra ID で Tidemark for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tidemark-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tidemark の間にシングル サインオンを構成する方法について説明します。

この記事では、Tidemark と Microsoft Entra ID を統合する方法について説明します。 Tidemark を Microsoft Entra ID と統合すると、次のことができます。

- Tidemark にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Tidemark に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Tidemark でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Tidemark では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Tidemark の追加

Microsoft Entra ID への Tidemark の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Tidemark を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Tidemark**」と入力します。
4. 結果のパネルから **[Tidemark]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tidemark 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Tidemark に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Tidemark の関連ユーザーとの間にリンク関係を確立する必要があります。

Tidemark に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tidemark の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. Tidemark のテストユーザーを作成し、Microsoft Entra ユーザーの表現にリンクされている B.Simon に対応するユーザーを設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Tidemark**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    "a." [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<subdomain>.tidemark.com/saml` |
    | `https://<subdomain>.tidemark.net/saml` |

    b。 **[サインオン URL]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<subdomain>.tidemark.com/login` |
    | `https://<subdomain>.tidemark.net/login` |

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、Tidemark クライアント サポート チームにお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Tidemark のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tidemark SSO の構成

**Tidemark** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を、Tidemark サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Tidemark のテスト ユーザーの作成

このセクションでは、Tidemark で Britta Simon というユーザーを作成します。 Tidemark サポート チームと連携して、Tidemark プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Tidemark のサインオン URL にリダイレクトされます。
- Tidemark のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Tidemark タイルを選択すると、このオプションは Tidemark のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tigergraph-tutorial"} -->
## Microsoft Entra ID で TigerGraph for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tigergraph-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TigerGraph の間にシングル サインオンを構成する方法について説明します。

この記事では、TigerGraph と Microsoft Entra ID を統合する方法について説明します。 TigerGraph を Microsoft Entra ID を統合すると、次のことができます。

- TigerGraph にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TigerGraph に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- TigerGraph でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TigerGraph では、**SP** および **IDP** で開始された SSO がサポートされます。

### ギャラリーから TigerGraph を追加する

Microsoft Entra ID への TigerGraph の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TigerGraph を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TigerGraph**」と入力します。
4. 結果パネルから **[TigerGraph]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TigerGraph 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、TigerGraph に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TigerGraph の関連ユーザーとの間にリンク関係を確立する必要があります。

TigerGraph に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TigerGraph の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. TigerGraphでB.Simonに対応するユーザーを作成し、そのユーザーをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TigerGraph**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<your-tigergraph-hostname>:14240/gsqlserver/gsql/saml/meta`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<your-tigergraph-hostname>:14240/api/auth/saml/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<your-tigergraph-hostname>:14240/#/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[TigerGraph クライアント サポート チーム](mailto:support@tigergraph.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[TigerGraph のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TigerGraph SSO の構成

**TigerGraph** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を、[TigerGraph サポート チーム](mailto:support@tigergraph.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TigerGraph のテスト ユーザーを作成する

このセクションでは、TigerGraph で Britta Simon というユーザーを作成します。 [TigerGraph サポートチーム](mailto:support@tigergraph.com) と協力して、TigerGraph プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる TigerGraph のサインオン URL にリダイレクトされます。
- TigerGraph のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TigerGraph に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで TigerGraph タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TigerGraph に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tigertext-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TigerConnect Secure Messenger を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tigertext-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TigerConnect Secure Messenger の間でシングル サインオンを構成する方法について学習します。

この記事では、TigerConnect Secure Messenger と Microsoft Entra ID を統合する方法について説明します。 TigerConnect Secure Messenger を Microsoft Entra ID と統合すると、次のことができます。

- TigerConnect Secure Messenger にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで TigerConnect Secure Messenger に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオンが有効になった TigerConnect Secure Messenger サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成してテストし、TigerConnect Secure Messenger と Microsoft Entra ID を統合します。

- TigerConnect Secure Messenger では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの TigerConnect Secure Messenger の追加

Microsoft Entra ID への TigerConnect Secure Messenger の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに TigerConnect Secure Messenger を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TigerConnect Secure Messenger**」と入力します。
4. 結果パネルから **[TigerConnect Secure Messenger]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TigerConnect Secure Messenger 用に Microsoft Entra SSO を構成してテストする

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、TigerConnect Secure Messenge で Microsoft Entra のシングル サインオンを構成してテストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと TigerConnect Secure Messenge の関連ユーザーとの間にリンク関係を確立する必要があります。

TigerConnect Secure Messenge で Microsoft Entra のシングル サインオンを構成してテストするには、次の手順を行う必要があります。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成する** - Britta Simon を使って Microsoft Entra シングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当て**、Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TigerConnect Secure Messenge の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TigerConnect Secure Messenge テスト ユーザーを作成する** - Britta Simon という Microsoft Entra ユーザーにリンクされている Britta Simon というユーザーが TigerConnect Secure Messenge に存在するようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

TigerConnect Secure Messenger で Microsoft Entra シングル サインオンを構成するには、次の手順を行います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**TigerConnect Secure Messenger**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. **[サインオン URL]** ボックスに、次の URL を入力します。

        `https://home.tigertext.com`
    2. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。

        `https://saml-lb.tigertext.me/v1/organization/<INSTANCE_ID>`

    注

    **[識別子 (エンティティ ID)]** の値は実際の値ではありません。 実際の識別子でこの値を更新します。 この値を取得するには、[TigerConnect Secure Messenger サポート チーム](mailto:prosupport@tigertext.com)に問い合わせてください。 **[Basic SAML Configuration] (基本的な SAML 構成)** ペインに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ウィンドウの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択し、特定のオプションの**フェデレーション メタデータ XML** をダウンロードします。

    [Image: フェデレーション メタデータ XML のダウンロード オプション]
7. **[TigerConnect Secure Messenger のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TigerConnect Secure Messenger SSO の構成

TigerConnect Secure Messenger 側でシングル サインオンを構成するには、ダウンロードしたフェデレーション メタデータ XML とコピーした適切な URL を [TigerConnect Secure Messenger サポート チーム](mailto:prosupport@tigertext.com) に送信する必要があります。 TigerConnect Secure Messenger チームは、SAML SSO 接続が両方の側で正しく設定されていることを確認します。

### TigerConnect Secure Messenger のテスト ユーザーの作成

このセクションでは、TigerConnect Secure Messenger で Britta Simon というユーザーを作成します。 [TigerConnect Secure Messenger サポート チーム](mailto:prosupport@tigertext.com)と協力して、TigerConnect Secure Messenger にユーザーとして Britta Simon を追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる TigerConnect Secure Messenger のサインオン URL にリダイレクトされます。
- TigerConnect Secure Messenger のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TigerConnect Secure Messenger] タイルを選択すると、このオプションは TigerConnect Secure Messenger のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/timeclock-365-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に TimeClock 365 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timeclock-365-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: Microsoft Entra ID から TimeClock 365 に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために TimeClock 365 と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[TimeClock 365](https://timeclock365.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- TimeClock 365 でユーザーを作成する
- アクセスが不要になった TimeClock 365 のユーザーを削除する
- Microsoft Entra ID と TimeClock 365 の間でユーザー属性の同期を維持する
- TimeClock 365 への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [TimeClock 365](https://timeclock365.com/) テナント。
- 管理者アクセス許可がある TimeClock 365 のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と TimeClock 365 の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように TimeClock 365 を構成する

1. [Timeclock365 管理コンソール](https://live.timeclock365.com)にログインします。
2. **[設定] &gt; [会社のプロファイル] &gt; [全般]** に移動します。

    [Image: [トークンの生成] ページのスクリーンショット。]
3. 下にスクロールして **Azure ユーザー同期**します。 **Microsoft Entra トークン**をコピーして保存します。 この値は、TimeClock 365 SAML アプリケーションの [プロビジョニング] タブの **[シークレット トークン** ] フィールドに入力されます。

    [Image: トークンの生成のスクリーンショット。]
4. `https://live.timeclock365.com/scim` は、TimeClock 365 アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから TimeClock 365 を追加する

Microsoft Entra アプリケーション ギャラリーから TimeClock 365 を追加して、TimeClock 365 へのプロビジョニングの管理を開始します。 SSO のために TimeClock 365 を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: TimeClock 365 への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、TimeClock 365 でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで TimeClock 365 の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Timeclock 365]** を選択します。

    [Image: アプリケーションの一覧の TimeClock 365 リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、TimeClock 365 テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が TimeClock 365 に接続できることを確認します。 接続に失敗した場合は、TimeClock 365 アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から TimeClock 365 に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で TimeClock 365 のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、TimeClock 365 API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/timeclock-365-saml-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に TimeClock 365 SAML を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timeclock-365-saml-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: Microsoft Entra ID から TimeClock 365 SAML に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために TimeClock 365 SAML と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[TimeClock 365 SAML](https://timeclock365.com/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- TimeClock 365 SAML でユーザーを作成する
- アクセスが不要になった TimeClock 365 SAML のユーザーを削除する
- Microsoft Entra ID と TimeClock 365 SAML の間でユーザー属性の同期を維持する
- TimeClock 365 SAML へ[のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timeclock-365-saml-tutorial)(推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [TimeClock 365 SAML](https://timeclock365.com/) テナント。
- 管理者アクセス許可がある TimeClock 365 SAML のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と TimeClock 365 SAML の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用してプロビジョニングをサポートするように TimeClock 365 SAML を構成する

1. [Timeclock365 管理コンソール](https://live.timeclock365.com)にログインします。
2. **[設定] &gt; [会社のプロファイル] &gt; [全般]** に移動します。

    [Image: [トークンの生成] ページのスクリーンショット。]
3. 下にスクロールして **Azure ユーザー同期**します。 **Microsoft Entra トークン**をコピーして保存します。 この値は、TimeClock 365 SAML アプリケーションの [プロビジョニング] タブの **[シークレット トークン** ] フィールドに入力されます。

    [Image: トークンの生成のスクリーンショット。]
4. `https://live.timeclock365.com/scim` は、TimeClock 365 SAML アプリケーションの [プロビジョニング] タブの **[テナント URL** ] フィールドに入力します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから TimeClock 365 SAML を追加する

Microsoft Entra アプリケーション ギャラリーから TimeClock 365 SAML を追加して、TimeClock 365 SAML へのプロビジョニングの管理を開始します。 SSO のために TimeClock 365 SAML を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: TimeClock 365 SAML への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、TimeClock 365 SAML でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で TimeClock 365 SAML の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[TimeClock 365 SAML]** を選択します。

    [Image: アプリケーションの一覧の TimeClock 365 SAML リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、TimeClock 365 SAML テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが TimeClock 365 SAML に接続できることを確認します。 接続に失敗した場合は、TimeClock 365 SAML アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から TimeClock 365 SAML に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で TimeClock 365 SAMLのユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、TimeClock 365 SAML API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/timeclock-365-saml-tutorial"} -->
## Microsoft Entra ID で Timeclock 365 SAML for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timeclock-365-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Timeclock 365 SAML の間でシングル サインオンを構成する方法について説明します。

この記事では、Timeclock 365 SAML と Microsoft Entra ID を統合する方法について説明します。 Timeclock 365 SAML を Microsoft Entra ID を統合すると、次のことができます。

- Timeclock 365 SAML にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Timeclock 365 SAML に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Timeclock 365 SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Timeclock 365 SAML では、**SP** Initiated SSO がサポートされます。
- Timeclock 365 SAML では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timeclock-365-saml-provisioning-tutorial)がサポートされます。

### ギャラリーからの Timeclock 365 SAML の追加

Microsoft Entra ID への Timeclock 365 SAML の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Timeclock 365 SAML を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Timeclock 365 SAML**」と入力します。
4. 結果パネルから **[Timeclock 365 SAML]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Timeclock 365 SAML 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Timeclock 365 SAML に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーとTimeclock 365 SAML の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Timeclock 365 SAML と組み合わせて構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Timeclock 365 SAML の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Timeclock 365 SAML テスト ユーザーの作成 - Timeclock** 365 SAML で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーに対応付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Timeclock 365 SAML**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://live.timeclock365.com/login`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Timeclock 365 SAML の SSO の構成

1. 別の Web ブラウザーのウィンドウで、Timeclock 365 SAML 企業サイトに管理者としてサインインします
2. 次の手順を実行します。

    [Image: Timeclock の構成]

    ある。 **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) &gt; [Company profile](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社プロファイル) &gt; [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** タブに移動します。

    b。 **[IDP メタデータ パス]** に、以前コピーした**アプリのFederation Metadata Url** を貼り付けます。

    c. 次に、**作成**を選択します。

#### Timeclock 365 SAML のテスト ユーザーの作成

1. ブラウザーで新しいタブを開き、Timeclock 365 SAML の会社のサイトに管理者としてサインインします。
2. **[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) &gt; [Add new user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの追加)** に移動します。

    [Image: テスト ユーザーの作成 1]
3. [ **ユーザー情報** ] ページで必要なすべての情報を入力し、[ **保存]** を選択します。

    [Image: テスト ユーザーの作成 2]
4. [ **作成** ] ボタンを選択してテスト ユーザーを作成します。

注

Timeclock 365 SAML では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timeclock-365-saml-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Timeclock 365 SAML サインオン URL にリダイレクトされます。
- Timeclock 365 SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Timeclock 365 SAML] タイルを選択すると、このオプションは Timeclock 365 SAML サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/timelive-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TimeLive を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timelive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TimeLive 間にシングル サインオンを構成する方法について説明します。

この記事では、TimeLive と Microsoft Entra ID を統合する方法について説明します。 TimeLive を Microsoft Entra ID と統合すると、次のことができます。

- TimeLive にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して TimeLive に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TimeLive でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TimeLive では、**SP** Initiated SSO がサポートされます。
- TimeLive では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの TimeLive の追加

Microsoft Entra ID への TimeLive の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TimeLive を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TimeLive**」と入力します。
4. 結果のパネルから **[TimeLive]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TimeLive 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、TimeLive に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TimeLive の関連ユーザーとの間にリンク関係を確立する必要があります。

TimeLive に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TimeLive の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TimeLive テストユーザーを作成 - Microsoft Entra の B.Simon に相当するユーザーを TimeLive にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**TimeLive**&gt;**Single サインオン**へ移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domainname>.livetecs.com/`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domainname>.livetecs.com/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[TimeLive クライアント サポート チーム](mailto:support@livetecs.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[TimeLive のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TimeLive の SSO の構成

1. 別の Web ブラウザーのウィンドウで、TimeLive 企業サイトに管理者としてサインインします。
2. **[Admin Options](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理オプション)** の **[Preferences](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/基本設定)** を選びます。

    [Image: [Admin Options](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理オプション) の [Preferences](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/基本設定) が選択されていることを示すスクリーンショット。]
3. **[Application Preference](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーション基本設定)** セクションで、次の手順に従います。

    [Image: [Application Preference](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーション基本設定) セクションを示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[セキュリティ]** タブを選びます。

    b。 **[Enable Single Sign On (SSO)](シングル サインオン (SSO) を有効にする)** チェック ボックスをオンにします。

    c. **[Sign in using Single Sign-On (SSO) with](サインインにシングル サインオン (SSO) を使用するもの)** という見出しのドロップダウン メニューで **[SAML]** を選びます。

    d. **[SAML SSO URL]** に、Azure portal からコピーした **[ログイン URL]** の値を入力します。

    え **[Remote logout URL](リモート ログアウト URL)** に、Azure portal からコピーした **[ログアウト URL]** の値を貼り付けます。

    f. Azure Portal からダウンロードした **Base-64 でエンコードされた証明書**をメモ帳で開き、その内容をコピーして **[X.509 証明書]** ボックスに貼り付けます。

    ジー **[更新]** を選択します。

#### TimeLive のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TimeLive に作成します。 TimeLive では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 TimeLive にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる TimeLive サインオン URL にリダイレクトされます。
- TimeLive のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TimeLive] タイルを選択すると、このオプションは TimeLive のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/timeoffmanager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TimeOffManager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timeoffmanager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TimeOffManager の間でシングル サインオンを構成する方法について説明します。

この記事では、TimeOffManager と Microsoft Entra ID を統合する方法について説明します。 TimeOffManager と Microsoft Entra ID を統合すると、次のことができます。

- TimeOffManager にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで TimeOffManager に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- TimeOffManager でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TimeOffManager では、 **IDP** によって開始される SSO がサポートされます。
- TimeOffManager では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの TimeOffManager の追加

Microsoft Entra ID への TimeOffManager の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TimeOffManager を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「TimeOffManager**」と入力します。
4. 結果パネルから **TimeOffManager** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TimeOffManager 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、TimeOffManager に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと TimeOffManager の関連ユーザーとの間にリンク関係を確立する必要があります。

TimeOffManager に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TimeOffManager の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TimeOffManager のテストユーザーを作成する - B.Simon に対応する TimeOffManager のユーザーを作成し、それを Microsoft Entra ユーザーにリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TimeOffManager**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.timeoffmanager.com/cpanel/sso/consume.aspx?company_id=<companyid>`

    注

    この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値は、記事または **TimeOffManager サポート チーム**に問い合わせて後ほど説明する[シングル サインオン設定ページ](https://www.purelyhr.com/contact-us)から取得できます。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. TimeOffManager アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: TimeOffManager アプリケーションの画像を示すスクリーンショット。]
7. その他に、TimeOffManager アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | ユーザー.名 |
    | 名字 | User.姓 |
    | Email | ユーザー.メール |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **TimeOffManager のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成 U R L をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TimeOffManager の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として TimeOffManager 企業サイトにサインインします。
2. アカウント&gt;&gt;移動します。

    [Image: スクリーンショットは、[アカウント オプション] から選択された [単一 Sign-On 設定] を示しています。]
3. [ **単一 Sign-On 設定]** セクションで、次の手順に従います。

    [Image: [Single Sign-On Settings](単一 Sign-On 設定) セクションを示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーし、証明書全体を **X.509 証明書** ボックスに貼り付けます。

    b。 **Idp Issuer** テキストボックスに、**Microsoft Entra Identifier** の値を貼り付けます。

    c. **[IdP エンドポイント URL**] ボックスに、**ログイン URL** の値を貼り付けます。

    d. **SAMLを有効にする** で、**いいえ**を選択します。

    え **[ユーザーの自動作成]** で、[**はい**] を選択します。

    f. [ **ログアウト URL** ] ボックスに、 **ログアウト URL** の値を貼り付けます。

    ジー [ **変更の保存]** を選択します。
4. **[シングル サインオン設定] ページで**、**Assertion Consumer Service URL** の値をコピーし、Azure portal の [**基本的な SAML 構成**] セクションの [**応答 URL**] テキスト ボックスに貼り付けます。

    [Image: [Assertion Consumer Service U R L](アサーション コンシューマー サービス U R L) リンクを示すスクリーンショット。]

#### TimeOffManager のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TimeOffManager に作成します。 TimeOffManager では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 TimeOffManager にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

TimeOffManager から提供されている他の TimeOffManager ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TimeOffManager に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TimeOffManager] タイルを選択すると、SSO を設定した TimeOffManager に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/timetabling-solutions-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Timetabling Solutions を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timetabling-solutions-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-09-02
- Summary: Microsoft Entra ID と Timetabling Solutions の間にシングル サインオンを構成する方法について説明します。

この記事では、Timetabling Solutions と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Timetabling Solutions を統合すると、次のことができます。

- Timetabling Solutions にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Timetabling Solutions に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Timetabling Solutions でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Timetabling Solutions では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Timetabling Solutions の追加

Microsoft Entra ID への Timetabling Solutions の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Timetabling Solutions を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Timetabling Solutions**」と入力します。
4. 結果パネルで **Timetabling Solutions** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Timetabling Solutions の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Timetabling Solutions に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Timetabling Solutions の関連ユーザーとの間にリンク関係を確立する必要があります。

Timetabling Solutions に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Timetabling Solutions の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Timetabling Solutions テストユーザーの作成 - Timetabling Solutions で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーとしてリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Timetabling Solutions**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    A. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://auth.timetabling.education`

    b。 [ **応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、URL を入力します。 `https://auth.timetabling.education`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://auth.timetabling.education`
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書を編集するスクリーンショット。]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする手順を示したスクリーンショット。]
8. **[Timetabling Solutions のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Timetabling Solutions の SSO の構成

このセクションでは、Timetabling Solutions 管理ポータルで関連する SSO 値を設定します。

1. [管理ポータル](https://admin.timetabling.education/)で **5 つの設定を**選択し、[**SAML SSO**] タブを選択します。
2. **[SAML SSO]** セクションで、次の手順を行います。

    [Image: SSO の設定のスクリーンショット。]

    A. SAML 統合を有効にします。

    b。 **[SAML ログイン パス]** テキストボックスに、先ほどコピーした **[ログイン URL]** の値を貼り付けます。

    c. **[SAML ログアウト パス]** テキストボックスに、先ほどコピーした **[ログアウト URL]** の値を貼り付けます。

    d. **[SAML 証明書のフィンガープリント]** テキストボックスに、先ほどコピーした "**拇印の値**" を貼り付けます。

    え **カスタム ドメイン**名を入力します。

    f. 設定を**保存**します。

### Timetabling Solutions のテスト ユーザーの作成

このセクションでは、Timetabling Solutions 管理ポータルで Britta Simon というユーザーを作成します。

1. [管理ポータル](https://admin.timetabling.education/)で**、1 つの [ユーザーの管理**] を選択し、[追加] を選択**します**。
2. 必須フィールド "**名**"、"**姓**"、"**電子メール アドレス**" を入力します。 その他の適切な値を必須以外のフィールドに追加します。
3. [状態] で**オンライン**がアクティブになっていることを確実にします。
4. [ **保存して次へ] を選択します**。

注

Timetabling Solutions プラットフォームにユーザーを追加するには、 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Timetabling Solutions Sign-On URL にリダイレクトされます。
- Timetabling Solutions のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [タイムタブリング ソリューション] タイルを選択すると、このオプションは Timetabling Solutions Sign-On URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/timetrack-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に TimeTrack を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timetrack-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TimeTrack 間にシングル サインオンを構成する方法について説明します。

この記事では、TimeTrack と Microsoft Entra ID を統合する方法について説明します。 TimeTrack と Microsoft Entra ID を統合すると、次のことができます。

- TimeTrack にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TimeTrack に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TimeTrack でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TimeTrack では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの TimeTrack の追加

Microsoft Entra ID への Track の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Track を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TimeTrack**」と入力します。
4. 結果のパネルから **[TimeTrack]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TimeTrack 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TimeTrack に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと TimeTrack の関連ユーザーとの間にリンク関係を確立する必要があります。

TimeTrack に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TimeTrack の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TimeTrack のテストユーザーを作成する** - B.Simon に対応するユーザーを TimeTrack で作成し、Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**TimeTrack**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant>.timetrackenterprise.com/api/v2/azure/saml20`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant>.timetrackenterprise.com/api/v2/azure/callback`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant>.timetrackenterprise.com`

    d. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `<ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態でこれらの値を更新します。 これらの値を取得するには、[TimeTrack クライアント サポート チーム](mailto:info@timetrackapp.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[TimeTrack のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TimeTrack SSO の構成

**TimeTrack** 側でシングル サインオンを構成するには、**サムプリントの値**とアプリケーション構成からコピーした適切な URL を [TimeTrack サポート チーム](mailto:info@timetrackapp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TimeTrack テストユーザーの作成

このセクションでは、TimeTrack というユーザーを作成します。 [Timetrack サポートチーム](mailto:info@timetrackapp.com) と協力して、Timetrack プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる TimeTrack サインオン URL にリダイレクトされます。
- TimeTrack のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TimeTrack に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TimeTrack] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TimeTrack に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/timu-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TIMU を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/timu-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TIMU 間にシングル サインオンを構成する方法について学習します。

この記事では、TIMU と Microsoft Entra ID を統合する方法について説明します。 TIMU を Microsoft Entra ID と統合すると、次のことができます:

- TIMU にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TIMU に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TIMU でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TIMU では、**SP** Initiated SSO がサポートされます
- TIMU では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの TIMU の追加

Microsoft Entra ID への TIMU の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに TIMU を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TIMU**」と入力します。
4. 結果のパネルから **[TIMU]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TIMU 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TIMU で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TIMU の関連ユーザーとの間にリンク関係を確立する必要があります。

TIMU で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TIMU の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TIMU テスト ユーザーの作成 - TIMU** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**TIMU**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | サインオン URL |
    | --- |
    | `https://auth.timu.com` |
    | `https://auth.timu.life` |
    |  |

    b。 **[識別子 (エンティティ ID)]** ボックスに、次のいずれかのパターンを入力します。

    | 識別子 |
    | --- |
    | `https://<SUBDOMAIN>.timu.com/api/login/saml/callback` |
    | `https://<SUBDOMAIN>.timu.life/api/login/saml/callback` |
    |  |

    c. **[応答 URL]** ボックスに、次のいずれかのパターンを入力します。

    | 応答 URL |
    | --- |
    | `https://<SUBDOMAIN>.timu.com/api/login/saml/callback` |
    | `https://<SUBDOMAIN>.timu.life/api/login/saml/callback` |
    |  |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[TIMU クライアント サポート チーム](mailto:support@timu.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TIMU の SSO の構成

**TIMU** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [TIMU サポート チーム](mailto:support@timu.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TIMU のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TIMU に作成します。 TIMU では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 TIMU にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TIMU サインオン URL にリダイレクトされます。
- TIMU のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft アクセス パネルを使用することができます。 アクセス パネルで [TIMU] タイルを選択すると、このオプションは TIMU のサインオン URL にリダイレクトされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tinfoil-security-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TINFOIL SECURITY を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tinfoil-security-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TINFOIL SECURITY の間にシングル サインオンを構成する方法について説明します。

この記事では、TINFOIL SECURITY と Microsoft Entra ID を統合する方法について説明します。 TINFOIL SECURITY と Microsoft Entra ID を統合すると、次のことができます。

- TINFOIL SECURITY にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TINFOIL SECURITY に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TINFOIL SECURITY でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TINFOIL SECURITY では、**IDP** Initiated SSO がサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの TINFOIL SECURITY の追加

Microsoft Entra ID への TINFOIL SECURITY の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TINFOIL SECURITY を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TINFOIL SECURITY**」と入力します。
4. 結果のパネルから **[TINFOIL SECURITY]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TINFOIL SECURITY 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、TINFOIL SECURITY に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと TINFOIL SECURITY の関連ユーザーとの間にリンク関係を確立する必要があります。

TINFOIL SECURITY に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TINFOIL SECURITY の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TINFOIL SECURITY テストユーザーの作成** - Microsoft Entra におけるユーザー表現にリンクされた TINFOIL SECURITY での B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TINFOIL SECURITY**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Visitly アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Visitly アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | アカウントID | UXXXXXXXXXXXXX |

    注

    この記事の後半で説明される accountid の値を取得します。
8. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
9. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
10. **[TINFOIL SECURITY のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TINFOIL SECURITY の SSO を設定する

1. 別の Web ブラウザーのウィンドウで、TINFOIL SECURITY の企業サイトに管理者としてサインインします。
2. 上部のツール バーで、[ **マイ アカウント**] を選択します。

    [Image: ダッシュボード (Dashboard)]
3. **[セキュリティ]** を選択します。

    [Image: セキュリティ]
4. **[シングル サインオン]** 構成ページで、次の手順を実行します。

    [Image: シングル サインオン]

    ある。 [ **SAML を有効にする] を選択します**。

    b。 [ **手動構成] を選択します**。

    c. **[SAML Post URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    d. **[SAML Certificate Fingerprint](SAML 証明書フィンガープリント)** ボックスに、 **[SAML 署名証明書]** セクションからコピーした**拇印**の値を貼り付けます。

    え **アカウント ID の値を**コピーし、Azure portal の [**ユーザー属性と要求**] セクションの [**ソース**属性] ボックスに値を貼り付けます。

    f. **保存** を選択します。

#### TINFOIL SECURITY のテスト ユーザーの作成

Microsoft Entra ユーザーが TINFOIL SECURITY にサインインできるようにするには、そのユーザーを TINFOIL SECURITY にプロビジョニングする必要があります。 TINFOIL SECURITY の場合、プロビジョニングは手動で行います。

**ユーザーをプロビジョニングするには、次の手順に従います。**

1. ユーザーがエンタープライズ アカウントの一部である場合は、TINFOIL SECURITY サポート チームに連絡して、ユーザー アカウントを作成する必要があります。
2. 通常の TINFOIL SECURITY SaaS ユーザーは、グループ作業者をユーザーの任意のサイトに追加できます。 ここでは、新しい TINFOIL SECURITY ユーザー アカウントを作成するための招待状を指定した電子メール アドレスに送信するプロセスが起動します。

注

TINFOIL SECURITY に用意されている他の TINFOIL SECURITY ユーザー アカウント作成ツールまたは API を使って、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TINFOIL SECURITY に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TINFOIL SECURITY] タイルを選択すると、SSO を設定した TINFOIL SECURITY に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/titanfile-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Titanfile を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/titanfile-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Titanfile 間にシングル サインオンを構成する方法について学習します。

この記事では、Titanfile と Microsoft Entra ID を統合する方法について説明します。 Titanfile と Microsoft Entra ID を統合すると、次のことができます:

- Titanfile にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Titanfile に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Titanfile でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Titanfile では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Titanfile の追加

Microsoft Entra ID への Titanfile の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Titanfile を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Titanfile**」と入力します。
4. 結果のパネルから **[Titanfile]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Titanfile 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Titanfile に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Titalfile の関連ユーザーとの間にリンク関係を確立する必要があります。

Titanfile に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Titanfile の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TitanfileでB.Simonの対応ユーザーを作成し、それをMicrosoft Entra上のユーザー表現にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Titanfile**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.titanfile.com/saml2/metadata/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.titanfile.com/saml2/acs/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Titanfile クライアント サポート チーム](mailto:support@titanfile.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Titanfile のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Titanfile の SSO の構成

**Titanfile** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Titanfile サポート チーム](mailto:support@titanfile.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Titanfile テスト ユーザーの作成

このセクションでは、Titanfile で B.Simon というユーザーを作成します。 [Titanfile サポート チーム](mailto:support@titanfile.com)と連携して、Titanfile プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Titanfile に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Titanfile] タイルを選択すると、SSO を設定した Titanfile に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tivitz-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TiViTz を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tivitz-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TiViTz 間にシングル サインオンを構成する方法について学習します。

この記事では、TiViTz と Microsoft Entra ID を統合する方法について説明します。 TiViTz を Microsoft Entra ID と統合すると、次のことができます:

- TiViTz にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TiViTz に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- TiViTz でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TiViTz では、**SP** Initiated SSO がサポートされます。
- TiViTz では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの TiViTz の追加

Microsoft Entra ID への TiViTz の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに TiViTz を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TiViTz**」と入力します。
4. 結果パネルから **[TiViTz]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TiViTz 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TiViTz に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TiViTz の関連ユーザーとの間にリンク関係を確立する必要があります。

TiViTz に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TiViTz SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TiViTz のテストユーザーを作成** - TiViTz 内で B.Simon に対応するユーザーを作成し、Microsoft Entra 表示の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**TiViTz**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    （詳細不明） [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.o365.tivitz.com/`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.o365.tivitz.com/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 この値を取得するには、[TiViTz クライアント サポート チーム](mailto:info@tivitz.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[TiViTz のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TiViTz SSO の構成

**TiViTz** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [TiViTz サポート チーム](mailto:info@tivitz.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TiViTz のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TiViTz に作成します。 TiViTz では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 TiViTz にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、[TiViTz のサポート チーム](mailto:info@tivitz.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TiViTz サインオン URL にリダイレクトされます。
- TiViTz のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TiViTz] タイルを選択すると、このオプションは TiViTz のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tonicdm-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TonicDM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tonicdm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TonicDM 間にシングル サインオンを構成する方法について説明します。

この記事では、TonicDM と Microsoft Entra ID を統合する方法について説明します。 TonicDM を Microsoft Entra ID と統合すると、次のことができます:

- TonicDM にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して TonicDM に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TonicDM でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TonicDM では、**SP** および **IDP** Initiated SSO がサポートされます。
- TonicDM では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから TonicDM を追加する

Microsoft Entra ID への TonicDM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TonicDM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TonicDM**」と入力します。
4. 結果パネルから **[TonicDM]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TonicDM 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、TonicDM で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TonicDM の関連ユーザーとの間にリンク関係を確立する必要があります。

TonicDM に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TonicDM の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TonicDM テストユーザーの作成** - Microsoft Entra の B.Simon にリンクさせ、TonicDM 内で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TonicDM**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.tonicdm.com/logon`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[TonicDM のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TonicDM の SSO の構成

**TonicDM** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [TonicDM サポート チーム](mailto:support@tonicdm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TonicDM のテスト ユーザーの作成

このセクションでは、TonicDM で Britta Simon というユーザーを作成します。 [TonicDM サポート チーム](mailto:support@tonicdm.com)と連携して、TonicDM プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる TonicDM サインオン URL にリダイレクトされます。
- TonicDM のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TonicDM に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TonicDM] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TonicDM に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tonichi-nexta-meishi-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Tonichi Nexta Meishi を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tonichi-nexta-meishi-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Tonichi Nexta Meishi の間でシングル サインオンを構成する方法について説明します。

この記事では、東日 Nexta Meishi と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Tonichi Nexta Meishi を統合すると、次のことができます。

- Microsoft Entra ID で、Tonichi Nexta Meishi へのアクセス権を持つユーザーを管理します。
- ユーザーが Microsoft Entra アカウントを使用して Tonichi Nexta Meishi に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

Tonichi Nexta Meishi は、以下の [ナショナル クラウド環境](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Tonichi Nexta Meishi でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Tonichi Nexta Meishiは、SPやIDPが開始するSSOの両方をサポートします。

### ギャラリーから「Tonichi Nexta Meishi」を追加する

Microsoft Entra ID への Tonichi Nexta Meishi の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Tonichi Nexta Meishi を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「東日 Nexta Meishi**」と入力します。
4. 結果パネルから **「東日 Nexta Meishi** 」を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tonichi Nexta Meishi の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Tonichi Nexta Meishi に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Tonichi Nexta Meishi の関連ユーザーとの間にリンク関係を確立する必要があります。

Tonichi Nexta Meishi に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tonichi Nexta Meishi の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Tonichi Nexta Meishi のテスト ユーザーの作成** - 「Tonichi Nexta Meishi」内の B.Simon に対応するレコードを、Microsoft Entra のユーザーの表明および保証にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Tonichi Nexta Meishi**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tnexta.com/accounts/saml2/all/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tnexta.com/accounts/saml2/all/acs`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tnexta.com/accounts/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値 [を取得するには、東日 Nexta Meishi サポート チーム](mailto:nexta@tonichi-printing.co.jp) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **Tonichi Nexta Meishi のセットアップ**セクションで、ニーズに基づいて適切なURLをコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tonichi Nexta Meishi SSOを構成する

1. 管理者として、東日 Nexta Meishi 企業サイトにサインインします。
2. **管理設定**&gt;**SSO** に移動し、次の手順を実行します。

    [Image: スクリーンショットは、構成を示しています。]

    a. **tnexta.com 値のエンドポイント URL をコピーします**。 この値を、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスに貼り付けます。

    b。 **エンティティ ID 値をコピーします**。 この値を、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの **[識別子 (エンティティ ID)]** テキスト ボックスに貼り付けます。

    c. **[SAML 2.0 エンドポイント URL**] ボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL** の値を貼り付けます。

    d. [ **ID プロバイダー発行者** ] ボックスに、Microsoft Entra 管理センターからコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    e. **[Idp Single Log Out URL**] ボックスに、Microsoft Entra 管理センターからコピーした**ログアウト URL** の値を貼り付けます。

    f. ダウンロードした **証明書 (未加工)** をメモ帳に開き、その内容を **[パブリック証明書** ] ボックスに貼り付けます。

    g. [ **ストアの設定] を選択し、SAML を試します**。

#### 「Tonichi Nexta Meishi」のテスト ユーザーを作成する

1. 別の Web ブラウザー ウィンドウで、管理者として東日 Nexta Meishi Web サイトにサインインします。
2. **[管理者設定]**&gt;**[ユーザーの管理**]に移動し、[**+ ユーザーの追加]**を選択します。

    [Image: スクリーンショットは、新しいユーザーを追加する方法を示しています。]
3. 次のページで、以下の手順を実行します。

    [Image: ユーザー情報を入力する [新しいユーザー] セクションを示すスクリーンショット。]

    1. 「**名前**」テキストボックスに、ユーザーの有効な名前を入力します。
    2. [ **電子メール** ] ボックスに、ユーザーの有効な emailaddress を入力します。
    3. **[送信]**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、サインイン フローを開始できる Tonichi Nexta Meishi のサインオン URL にリダイレクトされます。
- 東日 Nexta Meishi のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Tonichi Nexta Meishi に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [To日 Nexta Meishi] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Tonichi Nexta Meishi に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/topdesk-public-tutorial"} -->
## Microsoft Entra ID で TOPdesk をシングルサインオン用に構成する Public - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/topdesk-public-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TOPdesk - Public の間にシングル サインオンを構成する方法について説明します。

この記事では、TOPdesk - Public と Microsoft Entra ID を統合する方法について説明します。 TOPdesk - Public を Microsoft Entra ID と統合すると、次のことができます。

- TOPdesk - Public にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TOPdesk - Public に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TOPdesk - Public でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TOPdesk - Public では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの TOPdesk - Public の追加

Microsoft Entra ID への TOPdesk - Public の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TOPdesk - Public を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TOPdesk - Public**」と入力します。
4. 結果のパネルから **TOPdesk - Public** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TOPdesk - Public 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、TOPdesk - Public に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと TOPdesk - Public の関連ユーザーの間にリンク関係を確立する必要があります。

TOPdesk - Public に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TOPdesk - Public の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TOPdesk - Public テストユーザーの作成** - TOPdesk - Public で B.Simon の対応ユーザーを作成し、これを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TOPdesk - Public**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    注意

    **サービス プロバイダーのメタデータ ファイル**は、この記事で後述する「**TOPdesk - Public Single Sign-On の構成**」セクションから取得します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択する]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**と**応答 URL** の値が、[基本的な SAML 構成] セクションに自動的に設定されます。

    d. **[サインオン URL]** ボックスに、`https://<companyname>.topdesk.net` という形式で URL を入力します。

    え **[Identifier URL](識別子 URL)** ボックスに、TOPdesk 構成から取得できる TOPdesk メタデータ URL を入力します。 これには次のパターンを使用する必要があります: `https://<companyname>.topdesk.net/saml-metadata/<identifier>`

    f. **[応答 URL]** ボックスに、`https://<companyname>.topdesk.net/tas/public/login/verify` のパターンを使用して URL を入力します。

    注意

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、手動で入力する必要があります。 識別子の場合は、上記のパターンに従い、後で説明する「 **TOPdesk - Public Single Sign-On の構成** 」セクションから応答 URL 値を取得します。 **サインオン URL** の値は実際の値ではないので、実際の Sign-On URL で値を更新する必要があります。 この値を取得するには、[TOPdesk - Public クライアント サポート チーム](https://www.topdesk.com/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Set up TOPdesk - Public]**(TOPdesk - Public のセットアップ) セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TOPdesk - Public の SSO の構成

1. **TOPdesk - Public** 会社サイトに管理者としてサインインします。
2. **TOPdesk** メニューで、[**設定]** を選択します。

    [Image: [設定]]
3. [ **ログイン設定] を選択します**。

    [Image: [ログイン設定]]
4. [ **ログイン設定]** メニューを展開し、[ **全般**] を選択します。

    [Image: 全般設定]
5. [**SAML ログイン**] 構成セクションの [**公開**] セクションで、次の手順を実行します。

    [Image: 技術設定]

    ある。 [ **ダウンロード** ] を選択してパブリック メタデータ ファイルをダウンロードし、コンピューターにローカルに保存します。

    b。 ダウンロードしたメタデータ ファイルを開いて、**AssertionConsumerService** ノードを探します。

    [Image: AssertionConsumerService]

    c. **[AssertionConsumerService]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL]** テキストボックスにこの値を貼り付けます。
6. 証明書ファイルを作成するには、次の手順を実行します。

    [Image: 証明書]

    ある。 Azure Portal からダウンロードしたメタデータ ファイルを開きます。

    b。 **RoleDescriptor** ノードを展開します。ここには **fed:ApplicationServiceType** の **xsi:type** があります。

    c. **X509Certificate** ノードの値をコピーします。

    d. コピーした **X509Certificate** の値をコンピューターのローカルのファイルに保存します。
7. [ **パブリック** ] セクションで、[ **追加**] を選択します。

    [Image: SAML ログイン]
8. [**SAML 構成アシスタント**] ダイアログ ページで、次の手順を実行します。

    [Image: [SAML 構成アシスタント]]

    ある。 Azure portal からダウンロードしたメタデータ ファイルをアップロードするには、[ **フェデレーション メタデータ**] で [参照] を選択 **します**。

    b。 証明書ファイルをアップロードするには、[ **証明書 (RSA)] で** [参照] を選択 **します**。

    c. TOPdesk サポート チームから取得したロゴ ファイルをアップロードするには、[ **ロゴ] アイコン**の下にある **[参照**] を選択します。

    d. **[User name attribute]** ボックスに、「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」と入力します。

    え [**表示名**] テキストボックスに、構成の名前を入力します。

    f. **保存** を選択します。

#### TOPdesk - Public のテスト ユーザーの作成

Microsoft Entra ユーザーが TOPdesk - Public にサインインできるようにするには、そのユーザーを TOPdesk - Public にプロビジョニングする必要があります。 TOPdesk - Public の場合、プロビジョニングは手動で行います。

#### ユーザー プロビジョニングを構成するには、次の手順に従います。

1. **TOPdesk - Public** 企業サイトに管理者としてサインオンします。
2. 上部のメニューで、 **TOPdesk**&gt;**New**&gt;**Support Files**&gt;**Person** を選択します。

    [Image: Person (ユーザー)]
3. [New Person] ダイアログで、次の手順を実行します。

    [Image: New Person (新規ユーザー)]

    ある。 [全般] タブを選択します。

    b。 **[Surname]** ボックスに、ユーザーの姓を入力します (この例では Simon)。

    c. アカウントの [**サイト**] を選択します。

    d. **保存** を選択します。

注意

他の TOPdesk - Public ユーザー アカウントの作成ツールまたは TOPdesk - Public から提供されている API を使って、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TOPdesk - Public Sign-on URL にリダイレクトされます。
- TOPdesk - Public のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [TOPdesk - Public] タイルを選択すると、このオプションは TOPdesk - Public Sign-on URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/topdesk-secure-tutorial"} -->
## MICROSOFT Entra ID で TOPdesk - Secure for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/topdesk-secure-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と TOPdesk - Secure の間にシングル サインオンを構成する方法について説明します。

この記事では、TOPdesk - Secure と Microsoft Entra ID を統合する方法について説明します。 TOPdesk - Secure を Microsoft Entra ID と統合すると、次のことができます。

- TOPdesk - Secure にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで TOPdesk - Secure に自動的にサインイン (シングル サインオン) するように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TOPdesk - Secure でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TOPdesk - Secure では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの TOPdesk - Secure の追加

Microsoft Entra ID への TOPdesk - Secure の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TOPdesk - Secure を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**TOPdesk - Secure**」と入力します。
4. 結果パネルから **TOPdesk - Secure** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TOPdesk - Secure 用に Microsoft Entra SSO を構成してテストする

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、TOPdesk - Secure で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと TOPdesk - Secure 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

TOPdesk - Secure で Microsoft Entra のシングル サインオンを構成してテストするには、次の手順を実行する必要があります。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TOPdesk - Secure SSO の構成 -**アプリケーション側で単一 Sign-On 設定を構成します。
    1. **TOPdesk - Secure テストユーザーの作成 - Microsoft Entra のユーザー表現にリンクし、TOPdesk - Secure において Britta Simon の対応ユーザーを作成する。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

TOPdesk - Secure で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TOPdesk - Secure** application integration page に移動し、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。
4. [ **SAML を使用して単一 Sign-On を設定** する] ページで、鉛筆アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 URL** ] ボックスに、TOPdesk 構成から取得できる TOPdesk メタデータ URL を入力します。 これには次のパターンを使用する必要があります: `https://<companyname>.topdesk.net/saml-metadata/<identifier>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.topdesk.net/tas/secure/login/verify`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.topdesk.net`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [TOPdesk - Secure Client サポート チーム](https://www.topdesk.com/en/services/support/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **TOPdesk - Secure のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TOPdesk - Secure の SSO の構成

1. **TOPdesk - Secure** 企業サイトに管理者としてサインオンします。
2. **TOPdesk** メニューで、[**設定]** を選択します。

    [Image: 設定]
3. [ **ログイン設定] を選択します**。

    [Image: ログイン設定 ログイン設定]
4. [ **ログイン設定]** メニューを展開し、[ **全般**] を選択します。

    [Image: 全般]
5. **SAML ログイン**構成セクションの **Secure** セクションで、次の手順を実行します。

    [Image: 技術的な設定]

    ある。 [ **ダウンロード** ] を選択してパブリック メタデータ ファイルをダウンロードし、コンピューターにローカルに保存します。

    b。 メタデータ ファイルを開き、 **AssertionConsumerService** ノードを見つけます。

    [Image: アサーションコンシューマーサービス]

    c. **AssertionConsumerService** の値をコピーし、[**TOPdesk - Secure Domain and URLs]\(TOPdesk - セキュリティで保護されたドメインと URL**\) セクションの [応答 URL] ボックスにこの値を貼り付けます。
6. 証明書ファイルを作成するには、次の手順を実行します。

    [Image: 証明書証明書]

    ある。 Azure Portal からダウンロードしたメタデータ ファイルを開きます。

    b。 **xsi:type** が **fed:ApplicationServiceType** である **RoleDescriptor** ノードを展開します。

    c. **X509Certificate** ノードの値をコピーします。

    d. コピーした **X509Certificate** 値をコンピューター上のファイルにローカルに保存します。
7. [ **パブリック** ] セクションで、[ **追加**] を選択します。

    [Image: 追加 追加]
8. **[SAML 構成アシスタント**] ダイアログ ページで、次の手順を実行します。

    [Image: SAML 構成アシスタント]

    ある。 Azure portal からダウンロードしたメタデータ ファイルをアップロードするには、[ **フェデレーション メタデータ**] で [参照] を選択 **します**。

    b。 証明書ファイルをアップロードするには、[ **証明書 (RSA)] で** [参照] を選択 **します**。

    c. **秘密キー (RSA、PKCS8、DER) の**場合は、独自の秘密キーをアップロードするか、[TOPdesk - Secure Client サポート チーム](https://www.topdesk.com/en/services/support/)に連絡して秘密キーを取得できます。

    d. TOPdesk サポート チームから取得したロゴ ファイルをアップロードするには、[ **ロゴ] アイコン**の下にある **[参照**] を選択します。

    え [ **ユーザー名属性** ] ボックスに「 `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」と入力します。

    f. [ **表示名** ] ボックスに、構成の名前を入力します。

    ジー **[保存] を選択します**。

#### TOPdesk - Secure のテスト ユーザーの作成

Microsoft Entra ユーザーが TOPdesk - Secure にログインできるようにするには、そのユーザーを TOPdesk - Secure にプロビジョニングする必要があります。 TOPdesk - Secure の場合、プロビジョニングは手動で行います。

#### ユーザー プロビジョニングを構成するには、次の手順に従います。

1. **TOPdesk - Secure** 企業サイトに管理者としてサインオンします。
2. 上部のメニューで、 **TOPdesk**&gt;**New**&gt;**Support Files**&gt;**Operator** を選択します。

    [Image: 演算子]
3. [ **新しい演算子** ] ダイアログで、次の手順を実行します。

    [Image: 新しい演算子]

    ある。 [ **全般** ] タブを選択します。

    b。 **Surname** テキストボックスに、ユーザーの姓を「**Simon**」のように入力してください。

    c. [**場所**] セクションで、アカウントの**サイト**を選択します。

    d. [**TOPdesk Login]\(TOPdesk ログイン**\) セクションの [**Login Name**]\(ログイン名\) ボックスに、ユーザーのログイン名を入力します。

    え **[保存] を選択します**。

注

他の TOPdesk - Secure ユーザー アカウント作成ツールや、TOPdesk - Secure から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる TOPdesk - Secure Sign-on URL にリダイレクトされます。
- TOPdesk - Secure のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TOPdesk - Secure] タイルを選択すると、SSO を設定した TOPdesk - Secure に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/torii-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に、Torii を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/torii-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-09
- Summary: ユーザー アカウントを Torii に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動的なユーザー プロビジョニングを構成するために、Torii と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Torii](https://toriihq.com) に対してユーザーの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- Torii でユーザーを作成する。
- Toriiでアクセスが不要になったユーザーを削除します。
- Microsoft Entra ID と Torii 間でユーザー属性の同期を維持する。
- Torii への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/torii-tutorial) (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Torii の管理者アカウント。

### ステップ 1: プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Torii 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Torii を構成する

1. [Torii 管理コンソール](https://app.toriihq.com)にログインします。
2. [\[設定\]](https://app.toriihq.com/team/settings/apiAccess) ページ &gt;**Security** に移動し、SCIM トグルを有効にします。

    [Image: SCIM トグルのスクリーンショット。]
3. **[API アクセス]** タブに移動します。ここから、Torii API キーと SCIM を表示および管理できます。
4. [ **API キーの生成]** を選択して新しい SCIM を生成します。

    [Image: [API キーの生成] のスクリーンショット。]
5. 種類 (**[SCIM]**) を選択します。
6. **[説明]** を追加し、**[有効期限]** を設定します。 セキュリティ上の理由から、新しいキーを生成するときに有効期限を設定することをお勧めします。
7. [ **キーの生成] を選択します**。
8. API キーは次回から表示できないため、コピーして保存しておきます。 この値は、お使いの Torii アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。
9. **[了解]** を選択します。

    [Image: [API キーの作成] のスクリーンショット。]

    [Image: [API キーのコピー] のスクリーンショット。]
10. **Torii テナント URL:**`https://api.toriihq.com/v1.0/scim/v2` は、お使いの Torii アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力します。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Torii を追加する

Microsoft Entra アプリケーション ギャラリーから Torii を追加して、Torii へのプロビジョニングの管理を開始します。 SSO 用に Torii を既に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Torii に対する自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて Torii 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Torii の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Torii]** を選択します。

    [Image: アプリケーション リストの Torii リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. **テナント URL** フィールドに、お客様の Torii テナント URL とシークレット トークンを入力します。 [ **Test Connection]\(テスト接続** \) を選択して、Microsoft Entra ID が Torii に接続できることを確認します。 接続に失敗した場合は、必要な管理者アクセス許可を持つお使いの Torii アカウントを確認してから、もう一度お試しください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Torii に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、Torii での更新処理時にユーザー アカウントを照合するために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が、Torii API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Torii で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | ユーザータイプ | 糸 |  |  |

    Note

    - **userName** は、有効なドメインを持つ有効なメール アドレスである必要があります。
    - **userType**(Torii のロール):
    - これは、Torii に表示されるとおりに指定する必要があります
    - **userType** が空の場合、ユーザーは**従業員**ロールを既定値として取得します。
    - ユーザーの種類 (ロール) は[こちら](https://support.toriihq.com/hc/en-us/articles/6174005637787#h_01G6FRWNXDJG89HCBD93PNYW4H)で確認できます。
    - 管理者ユーザー（タイプが "Employee" ではない）だけが、Torii の UI に表示されます。
    - Torii の属性の詳細については、[こちら](https://support.toriihq.com/hc/en-us/articles/9183460072347-Torii-s-SCIM-User-Provisioning#heading-3)をご覧ください。
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### ステップ 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/torii-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Torii を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/torii-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Torii の間にシングル サインオンを構成する方法について説明します。

この記事では、Torii と Microsoft Entra ID を統合する方法について説明します。 Torii を Microsoft Entra ID を統合すると、次のことができます。

- Torii にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Torii に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Torii でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Torii では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Torii では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Torii では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/torii-provisioning-tutorial)。

### ギャラリーからの Torii の追加

Microsoft Entra ID への Torii の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Torii を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Torii**」と入力します。
4. 結果のパネルから **[Torii]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Torii 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Torii に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Torii の関連ユーザーとの間にリンク関係を確立する必要があります。

Torii に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Torii の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Torii のテストユーザーの作成** - Microsoft Entra のユーザーである B.Simon にリンクさせるための Torii 内の対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Torii**&gt;**シングル サインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.toriihq.com/api/saml/<idOrg>/callback`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.toriihq.com/api/saml/<idOrg>/callback`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.toriihq.com/api/saml/<idOrg>/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Torii クライアント サポート チーム](mailto:support@toriihq.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Torii のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Torii の SSO の構成

**Torii** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、アプリケーション構成からコピーした適切な URL を、[Toriiサポート チーム](mailto:support@toriihq.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Torii のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Torii に作成します。 Torii では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Torii にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Sign-On URL にリダイレクトされます。
- Torii のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Torii に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Torii] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Torii に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tracker-software-technologies-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Tracker Software Technologies を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tracker-software-technologies-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tracker Software Technologies 間にシングル サインオンを構成する方法について説明します。

この記事では、Tracker Software Technologies と Microsoft Entra ID を統合する方法について説明します。 Tracker Software Technologies を Microsoft Entra ID を統合すると、次のことができます。

- Tracker Software Technologies にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra ID アカウントを使用して Tracker Software Technologies に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) 対応の Tracker Software Technologies サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Tracker Software Technologies では、**IDP** によって開始される SSO がサポートされます。
- Tracker Software Technologies では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Tracker Software Technologies を追加する

Microsoft Entra ID への Tracker Software Technologies の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Tracker Software Technologies を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Tracker Software Technologies**」と入力します。
4. 結果のパネルから **[Tracker Software Technologies]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tracker Software Technologies 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Tracker Software Technologies に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Tracker Software Technologies の関連ユーザーとの間にリンク関係を確立する必要があります。

Tracker Software Technologies に対して Microsoft Entra AD SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tracker Software Technologies SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Tracker Software Technologies の B.Simon に対応するテスト ユーザーの作成** - Microsoft Entra 上の B.Simon にリンクされる対になるユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Tracker Software Technologies**&gt;**シングルサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Environment>.at-sw.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Environment>.at-sw.com/users/auth/<CustomerName>/callback`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[のクライアント サポート チーム](mailto:admin@gtglobaltracker.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tracker Software Technologies SSO の構成

**Tracker Software Technologies** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Tracker Software Technologies サポート チーム](mailto:admin@gtglobaltracker.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Tracker Software Technologies のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Tracker Software Technologies に作成します。 Tracker Software Technologies では、Just-In-Time ユーザー プロビジョニングがサポートされます。この設定は既定で有効です。 このセクションにはアクション項目はありません。 Tracker Software Technologies にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Tracker Software Technologies に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Tracker Software Technologies] タイルを選択すると、SSO を設定した Tracker Software Technologies に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trackvia-tutorial"} -->
## Microsoft Entra ID で TrackVia for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trackvia-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TrackVia 間のシングル サインオンを構成する方法について説明します。

この記事では、TrackVia と Microsoft Entra ID を統合する方法について説明します。 TrackVia を Microsoft Entra ID と統合すると、次のことが可能になります。

- TrackVia にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで TrackVia に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TrackVia でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TrackVia では、**SP** と **IDP** によって開始される SSO がサポートされます。
- TrackVia では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから TrackVia を追加する

Microsoft Entra ID への TrackVia の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TrackVia を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TrackVia**」と入力します。
4. 結果のパネルから **[TrackVia]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TrackVia に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、TrackVia に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと TrackVia の関連ユーザー間にリンク関係を確立する必要があります。

TrackVia に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TrackVia SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TrackViaテストユーザーの作成** - TrackViaでB.Simonに対応するユーザーを作成して、Microsoft Entraでのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TrackVia**&gt;**シングルサインオン**を閲覧します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、値を入力します。 `TrackVia`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://companyname.trackvia.com`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[TrackVia クライアント サポート チーム](mailto:support@trackvia.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[TrackVia の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TrackVia SSO を構成する

1. 別の Web ブラウザー ウィンドウで、管理者として TrackVia 企業サイトにサインオンします。
2. [ **マイ アカウント** 設定で追跡] を選択し、[ **シングル サインオン** ] タブを選択し、次の手順を実行します。

    [Image: TrackVia の構成]

    a. **[ID プロバイダー エンティティ ID]** テキスト ボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    b。 **[ファイルの選択]** を選択して、ダウンロードしたメタデータ ファイルをアップロードします。

    c. **保存** を選択します。

#### TrackVia のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを TrackVia に作成します。 TrackVia では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 TrackVia にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[TrackVia サポート チーム](mailto:support@trackvia.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TrackVia サインオン URL にリダイレクトされます。
- TrackVia のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TrackVia に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TrackVia] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TrackVia に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tradeshift-tutorial"} -->
## Microsoft Entra ID で Tradeshift for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tradeshift-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tradeshift の間にシングル サインオンを構成する方法について説明します。

この記事では、Tradeshift と Microsoft Entra ID を統合する方法について説明します。 Tradeshift を Microsoft Entra ID と統合すると、次のことができます。

- Tradeshift にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Tradeshift に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Tradeshift でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Tradeshift では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーから Tradeshift を追加する

Microsoft Entra ID への Tradeshift の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Tradeshift を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Tradeshift**」と入力します。
4. 結果のパネルから **[Tradeshift]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tradeshift 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Tradeshift に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Tradeshift の関連ユーザーとの間にリンク関係を確立する必要があります。

Tradeshift に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tradeshift の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Tradeshift テストユーザーを作成 - Tradeshift において、Microsoft Entra の B.Simon に対応するユーザーをリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Tradeshift**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    ア [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://go.tradeshift.com`

    b。 [ **リレー状態** ] テキスト ボックスに、URL を入力します。 `https://go.tradeshift.com`
7. **保存** を選択します。
8. Tradeshift アプリケーションでは、特定の形式の SAML アサーションを使用するため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Tradeshift アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:2.5.4.42 | ユーザー.ファーストネーム |
    | urn:oid:2.5.4.4 | ユーザーの名字 |
    | urn:oid:0.9.2342.19200300.100.1.1 | ユーザー.ユーザープリンシパルネーム |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Tradeshift のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tradeshift の SSO の構成

**Tradeshift** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Tradeshift サポート チーム](mailto:support@tradeshift.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Tradeshift のテスト ユーザーの作成

このセクションでは、Tradeshift で Britta Simon というユーザーを作成します。 [Tradeshift サポート チーム](mailto:support@tradeshift.com)と連携して、Tradeshift プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Tradeshift のサインオン URL にリダイレクトされます。
- Tradeshift のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Tradeshift に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Tradeshift タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Tradeshift に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/training-platform-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用のトレーニング プラットフォームを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/training-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Training Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Training Platform と Microsoft Entra ID を統合する方法について説明します。 Training Platform を Microsoft Entra ID と統合すると、次のことが可能になります。

- Training Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Training Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Training Platform でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- トレーニング プラットフォームでは、「**SP**イニシエーテッドSSO」と「**IDP**イニシエーテッドSSO」がサポートされます。
- トレーニング プラットフォームでは、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Training Platform の追加

Microsoft Entra ID への Training Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Training Platform を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに「**Training Platform**」と入力します。
4. 結果パネルから **[トレーニング プラットフォーム** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Training Platform に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Training Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと、Training Platform での関連ユーザーとの間にリンク関係を確立する必要があります。

Training Platform 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Training Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Training Platform のテストユーザーを作成** - B.Simon に対応するユーザーを Training Platform で作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Training Platform**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:living-security:<ID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://identity.livingsecurity.com/login/callback?connection=<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.livingsecurity.com`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Training Platform サポート チーム](mailto:support@livingsecurity.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Training Platform SSO の構成

1. Training Platform 企業サイトに管理者としてログインします。
2. **[構成]** セクションに移動し、[**SAML SSO 構成] タブを**選択します。
3. アプリケーションが **メタデータ URL** モードに設定されていることを確認します。
4. **Identity Provider Metadata Url\*** テキストボックスに、前にコピーした **アプリ フェデレーション メタデータ URL** を貼り付けます。

    [Image: [構成設定] を示すスクリーンショット。]
5. **[保存] を選択します**。

#### Training Platform のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Training Platform に作成します。 Training Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Training Platform にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Training Platform のサインオン URL にリダイレクトされます。
- Training Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Training Platform に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [トレーニング プラットフォーム] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したトレーニング プラットフォームに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trakopolis-tutorial"} -->
## Microsoft Entra ID で Trakopolis for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trakopolis-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Trakopolis の間でシングル サインオンを構成する方法について説明します。

この記事では、Trakopolis と Microsoft Entra ID を統合する方法について説明します。 Trakopolis と Microsoft Entra ID を統合すると、次のことができます。

- Trakopolis にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Trakopolis に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Trakopolis でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Trakopolis では、**SP** により開始された SSO がサポートされます

### ギャラリーからの Trakopolis の追加

Microsoft Entra ID への Trakopolis の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Trakopolis を追加する必要があります。

**ギャラリーから Trakopolis を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. 検索ボックスに **「Trakopolis**」と入力し、結果パネルで **Trakopolis** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Trakopolis]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Trakopolis で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Trakopolis 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Trakopolis で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Trakopolis シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Trakopolis テストユーザーの作成** - Microsoft Entra のユーザーとして Britta Simon に対応する Trakopolis ユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Trakopolis で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Trakopolis** アプリケーション統合ページを参照し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: Trakopolis ドメインおよびURLのシングルサインオン情報]

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.trakopolis.com/`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.trakopolis.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [Trakopolis クライアント サポート チーム](mailto:support@cantelematics.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Trakopolis のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### Trakopolis シングル サインオンの構成

**Trakopolis** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Trakopolis サポート チーム](mailto:support@cantelematics.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Trakopolis のテスト ユーザーの作成

このセクションでは、Trakopolis で Britta Simon というユーザーを作成します。 [Trakopolis サポート チーム](mailto:support@cantelematics.com)と協力して、Trakopolis プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Trakopolis] タイルを選択すると、SSO を設定した Trakopolis に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trakstar-tutorial"} -->
## Microsoft Entra ID で Trakstar for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trakstar-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Trakstar の間にシングル サインオンを構成する方法について学習します。

この記事では、Trakstar と Microsoft Entra ID を統合する方法について説明します。 Trakstar と Microsoft Entra ID を統合すると、次のことができます。

- Trakstar にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Trakstar に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Trakstar でのシングル サインオンが有効なサブスクリプション。
- SSO は、Trakstar の有料の機能です。 組織で有効にするには、 [Trakstar クライアント サポート チーム](mailto:support@trakstar.com)にお問い合わせください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Trakstar では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Trakstar の追加

Microsoft Entra ID への Trakstar の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Trakstar を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Trakstar**」と入力します。
4. 結果のパネルから **Trakstar** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Trakstar 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Trakstar に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Trakstar の関連ユーザーとの間にリンク関係を確立する必要があります。

Trakstar に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行してください:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Trakstar SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Trakstar テストユーザーを作成** - Microsoft Entra にリンクされた B.Simon の対応者を Trakstar で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Trakstar**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、対応する入力フィールドに次の値を入力します。

    | フィールド名 | 価値 | 注 |
    | --- | --- | --- |
    | **応答 URL (アサーションコンシューマーサービス URL)** | `https://perform.trakstar.com/auth/saml/callback?namespace=<YOUR_NAMESPACE>` | `<YOUR_NAMESPACE>`を実際の値に置き換えます。これは Trakstar Perform の **ACS (コンシューマー) URL** フィールドに表示されます。 この表の後に表示される注記を参照してください。 |
    | **サインオン URL** | `https://perform.trakstar.com/auth/saml/?namespace=<YOUR_NAMESPACE>` | この URL は上記の URL に *似ています* が、 `/callback` 部分はありません。 |
    | **識別子 (エンティティ ID)** | `https://perform.trakstar.com` |  |

    注

    これらの値は、例としてのみ使用しています。 Trakstar Perform で名前空間に固有の値を使用する必要があります。この値は、アプリケーションにサインインし、 **Settings**&gt;**Authentication & SSO**&gt;**SAML 2.0**&gt;**Configure** に移動することで表示されます。

    **[設定]** に **[認証と SSO**] タブが表示されない場合は、この機能がない可能性があり、Trakstar カスタマー サポートにお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Trakstar のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Trakstar SSO の構成

**Trakstar** 側でシングル サインオンを構成するには、管理者としてサインインし、Azure portal からダウンロードした**証明書 (Base64)** とコピーした適切な URL の内容を入力する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Trakstar のテスト ユーザーの作成

このセクションでは、Trakstar で Britta Simon というユーザーを作成します。 Trakstar 管理者と連携し、Trakstar プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Trakstar のサインオン URL にリダイレクトされます。
- Trakstar のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Trakstar] タイルを選択すると、このオプションは Trakstar のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/transperfect-globallink-dashboard-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TransPerfect GlobalLink ダッシュボードを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/transperfect-globallink-dashboard-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TransPerfect GlobalLink Dashboard の間でシングル サインオンを構成する方法について説明します。

この記事では、TransPerfect GlobalLink ダッシュボードと Microsoft Entra ID を統合する方法について説明します。 TransPerfect GlobalLink ダッシュボードを Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra ID で TransPerfect GlobalLink ダッシュボードへのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して TransPerfect GlobalLink ダッシュボードに自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TransPerfect GlobalLink Dashboard でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TransPerfect GlobalLink Dashboard では、**SPおよびIDP**によるシングルサインオンがサポートされます。
- TransPerfect GlobalLink ダッシュボードでは、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの TransPerfect GlobalLink ダッシュボードの追加

Microsoft Entra ID への TransPerfect GlobalLink Dashboard の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TransPerfect GlobalLink ダッシュボードを追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「TransPerfect GlobalLink Dashboard**」と入力します。
4. 結果パネルから **TransPerfect GlobalLink ダッシュボード** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TransPerfect GlobalLink ダッシュボードの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、TransPerfect GlobalLink Dashboard に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TransPerfect GlobalLink Dashboard の関連ユーザーとの間にリンク関係を確立する必要があります。

TransPerfect GlobalLink Dashboard で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TransPerfect GlobalLink Dashboard の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **TransPerfect GlobalLink Dashboard 用テストユーザーを作成** - Microsoft Entra における B.Simon に対応するユーザーを TransPerfect GlobalLink Dashboard に作成し、エントラのユーザー表示とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TransPerfect GlobalLink Dashboard**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://sso.transperfect.com`
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TransPerfect GlobalLink Dashboard SSO の構成

**TransPerfect GlobalLink ダッシュボード**側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[TransPerfect GlobalLink Dashboard サポート チーム](mailto:TechOps_Consulting@transperfect.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TransPerfect GlobalLink ダッシュボードのテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを TransPerfect GlobalLink Dashboard に作成します。 TransPerfect GlobalLink Dashboard では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 TransPerfect GlobalLink ダッシュボードにユーザーがまだ存在していない場合は、TransPerfect GlobalLink ダッシュボードにアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TransPerfect GlobalLink Dashboard のサインオン URL にリダイレクトされます。
- TransPerfect GlobalLink Dashboard のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TransPerfect GlobalLink ダッシュボードに自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TransPerfect GlobalLink Dashboard] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TransPerfect GlobalLink ダッシュボードに自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tranxfer-tutorial"} -->
## Microsoft Entra ID で Tranxfer' for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tranxfer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tranxfer 間にシングル サインオンを構成する方法について説明します。

この記事では、Tranxfer と Microsoft Entra ID を統合する方法について説明します。 Tranxfer は、ファイルの送受信に最も安全で最も使いやすいビジネス ソリューションを提供します。 Tranxfer を Microsoft Entra ID と統合すると、次のことができます。

- Tranxfer にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Tranxfer に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Tranxfer 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Tranxfer では、 **SP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングがサポートされます。

### [前提条件]

Microsoft Entra ID を Tranxfer と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Tranxfer でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Tranxfer アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Tranxfer を追加する

Microsoft Entra アプリケーション ギャラリーから Tranxfer を追加して、Tranxfer でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Tranxfer**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tranxfer.com`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tranxfer.com/SAMLResponse`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tranxfer.com/saml/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Tranxfer クライアント サポート チーム](mailto:soporte@tranxfer.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Tranxfer アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Tranxfer アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | user.groups [すべて] |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Tranxfer SSO を構成する

会社の管理者アカウントを使用して Tranxfer アプリケーションにログインする必要があります。

1. **[設定] -&gt; SAML** に移動し、[**アプリのフェデレーション メタデータ URL] を** **[メタデータ URL**] フィールドに貼り付けます。
2. 異なるユーザー グループに特定のアクセス許可を付与する場合は、Microsoft Entra グループを一般的な **Tranxfer** アクセス許可と照合できます。 これを行うには、アクセス許可ごとに Microsoft Entra グループ ID を入力します。

    ある。 ファイルを送信するための SEND アクセス許可。

    b。 ファイルを受信するための RECEIVE。

    c. 上記の両方を行うための SEND と RECEIVE。

    d. 管理会社の管理アクセス許可ですが、ファイルの送受信は行いません。

    え 上記のすべてを行うための FULL。

    [Image: Tranxfer SAML 設定を示すスクリーンショット。]
3. 組織の任意のユーザーに、どのグループを持っていても単純な送受信アクセス許可を付与する場合は、[ **アクセス許可を持つ空のグループ** ] オプションを有効にします。
4. グループ別のアクセス許可のみを照合するが、Microsoft Entra グループを Tranxfer グループにインポートしない場合は、[ **グループのインポートを無効にする] オプションを有効にします** 。

問題が見つかる場合 [は、Tranxfer サポート チーム](mailto:soporte@tranxfer.com)にお問い合わせください。 サポート チームが、アプリケーションでのシングル サインオンの設定についてサポートします。

#### Tranxfer テスト ユーザーを作成する

このセクションでは、B. Simon というユーザーを Tranxfer に作成します。 Tranxfer では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Tranxfer にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Tranxfer のサインオン URL にリダイレクトされます。
- Tranxfer のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Tranxfer] タイルを選択すると、このオプションは Tranxfer のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/travelperk-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に TravelPerk を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/travelperk-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から TravelPerk に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法についてご確認ください。

この記事では、自動ユーザー プロビジョニングを構成するために TravelPerk と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[TravelPerk](https://www.travelperk.com/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- コード認証許可フロー認証がサポートされています。

- TravelPerk でユーザーを作成する
- アクセスが不要になった場合に TravelPerk のユーザーを削除する
- Microsoft Entra ID と TravelPerk の間でユーザー属性の同期を維持する
- TravelPerk への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/travelperk-tutorial) (推奨)

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)ロール、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)ロールのいずれか。
- アクティブな [TravelPerk](https://app.travelperk.com/signup) 管理者アカウント。
- Premium または Pro [プラン](https://www.travelperk.com/pricing/)。

### 手順 1: プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と TravelPerk の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように TravelPerk を構成する

1. ご利用の管理者アカウントを使用して [TravelPerk](https://app.travelperk.com/company/integrations/scim) アプリケーションにログインします。
2. **[Company Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社の設定)**&gt;**[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)**&gt;**[SCIM]** の順に移動します。
3. [ **SCIM API を有効にする] を選択する**

    [Image: 有効にする]
4. SCIM を介して承認を有効にすることもできます。 承認を使用すると、指定した承認者によって旅行が最初に確実に承認されるようにすることで、追加のガバナンスを容易に設定できます。 詳細については、TravelPerk のドキュメントを参照してください。
5. どの人のマネージャーも旅行の承認を担当するユーザーに自動的になるようにするかどうかを指定できます。 そのため、承認者は対応する自動承認プロセスで割り当てられます。 TravelPerk によって、Azure の**マネージャー**の値が、ユーザーの希望する承認者に割り当てられます。 プロビジョニングされたユーザー承認者になるには、ユーザーは事前にプラットフォーム上に存在している必要があります。 TravelPerk 上で適切に設定されていない場合、承認者は作成されません。
6. [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) ページで SCIM を有効にしたら、 **[SCIM Settings](SCIM の設定)** 内で自動承認プロセスの作成を指定できるようになります。 それをオンにするには、 **[Through an Identity Provider](ID プロバイダー経由)** を選択し、 **[Enable automatic approval process creation](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/自動承認プロセスの作成を有効にする)** のトグルを切り替えます。
7. 必要な承認プロセスが構成されたら、[ **変更の保存]** を選択します。

    [Image: 自動化]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから TravelPerk を追加する

Microsoft Entra アプリケーション ギャラリーから TravelPerk を追加して、TravelPerk へのプロビジョニングの管理を開始します。 以前に SSO のために TravelPerk をセットアップしている場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: TravelPerk への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、TestApp でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で TravelPerk の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] 画面]
3. アプリケーションの一覧で **[TravelPerk]** を選択します。

    [Image: アプリケーションの一覧の [TravelPerk] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、[ **承認**] を選択します。 **TravelPerk** のログイン ページにリダイレクトされます。 **ユーザー名**と**パスワード**を入力し、[**サインイン**] ボタンを選択します。 [ **承認]** ページで [アプリの承認] を選択します。 [ **テスト接続]** を選択して、Microsoft Entra ID が TravelPerk に接続できることを確認します。 接続できない場合は、使用中の SecureLogin アカウントで管理者アクセス許可を確保してから、もう一度試します。

    [Image: [Admin Credentials (管理者の資格情報)]]

    [Image: ようこそ]

    [Image: アクセス]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から TravelPerk に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で TravelPerk のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、TravelPerk API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | エクスターナルID | 糸 |  |
    | 活動中 | ブール値 |  |
    | 名前.敬称 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.ミドルネーム | 糸 |  |
    | 優先言語 | 糸 |  |
    | ロケール | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | エクスターナルID | 糸 |  |
    | タイトル | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:コストセンター | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | リファレンス |  |
    | urn:ietf:params:scim:schemas:extension:travelperk:2.0:User:性別 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:travelperk:2.0:User:生年月日 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:travelperk:2.0:User:invoiceProfiles | 配列 |  |
    | urn:ietf:params:scim:schemas:extension:travelperk:2.0:User:emergencyContact.name | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:travelperk:2.0:User:緊急連絡先.電話 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:travelperk:2.0:User:travelPolicy | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/travelperk-tutorial"} -->
## Microsoft Entra ID で TravelPerk for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/travelperk-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TravelPerk の間にシングル サインオンを構成する方法について説明します。

この記事では、TravelPerk と Microsoft Entra ID を統合する方法について説明します。 TravelPerk と Microsoft Entra ID を統合すると、次のことができます。

- TravelPerk にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TravelPerk に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Premium サブスクリプションのある TravelPerk アカウント。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TravelPerk では、**SP** によって開始される SSO がサポートされます。
- TravelPerk では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- TravelPerk では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/travelperk-provisioning-tutorial)がサポートされます。

### ギャラリーからの TravelPerk の追加

Microsoft Entra ID への TravelPerk の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TravelPerk を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TravelPerk**」と入力します。
4. 結果のパネルから **[TravelPerk]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TravelPerk 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TravelPerk での Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TravelPerk の関連ユーザーとの間にリンク関係を確立する必要があります。

TravelPerk での Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TravelPerk の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TravelPerk テストユーザーを作成して、それを Microsoft Entra における B.Simon の表現にリンクする** - TravelPerk に、B.Simon と対応するユーザーを持たせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TravelPerk**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY>.travelperk.com/accounts/saml2/metadata/<APPLICATION_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY>.travelperk.com/accounts/saml2/callback/<APPLICATION_ID>/?acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY>.travelperk.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値は、TravelPerk アカウント内で、 **[Company Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社の設定)**&gt;**[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)**&gt;**[Single Sign On](シングル サインオン)** に移動して確認できます。 サポートについては、[TravelPerk ヘルプ センター](https://support.travelperk.com/hc/articles/360052450271-How-can-I-setup-SSO-for-Azure-SAML)にアクセスしてください。
6. TravelPerk アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 既定のマッピングでは、**emailaddress** は **user. mail** にマップされます。 ただし、TravelPerk アプリケーションでは、**emailaddress** を **user.userprincipalname** にマップする必要があります。 TravelPerk の場合は、属性マッピングを編集する必要があります。 **[編集]** アイコンを選択し、属性マッピングを変更します。 属性を編集するには、属性を選択して編集モードを開きます。

    [Image: TravelPerk アプリケーションの画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[TravelPerk のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL のコピーを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TravelPerk の SSO の構成

**TravelPerk** 側でシングル サインオンを構成するには、TravelPerk アプリで統合をセットアップする必要があります。

1. 管理者ユーザーとして https://app.travelperk.com に移動し、**[アカウント設定]** の &gt; で **[シングル サインオン (SSO)]** を開きます。
2. オプションとして **[SAML** ] を選択し、[ **新しい統合** ] を選択してから、次の手順を実行します。

    ある。 **[IdP entity ID] (IdP エンティティ ID)** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    b。 **[IdP SSO サービス URL]** テキストボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    c. **[IdP x509 証明書]** テキスト ボックスに、**フェデレーション メタデータ XML** ファイル (X509Certificate タグなし) を貼り付けます。

    d. 保存し、テストを続行します。

#### TravelPerk テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを TravelPerk に作成します。 TravelPerk では、Just-In-Time プロビジョニングがサポートされており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ TravelPerk に存在していない場合は、TravelPerk にアクセスしようとしたときに新しいユーザーが作成されます。

TravelPerk では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/travelperk-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TravelPerk のサインオン URL にリダイレクトされます。
- TravelPerk のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TravelPerk] タイルを選択すると、このオプションは TravelPerk のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/treasury-intelligence-solutions-tis-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に財務省インテリジェンス ソリューション (TIS) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/treasury-intelligence-solutions-tis-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と財務省インテリジェンス ソリューション (TIS) の間でシングル サインオンを構成する方法について説明します。

この記事では、財務省インテリジェンス ソリューション (TIS) と Microsoft Entra ID を統合する方法について説明します。 財務省インテリジェンス ソリューション (TIS) と Microsoft Entra ID を統合すると、次のことができます。

- 財務省インテリジェンス ソリューション (TIS) にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して財務省インテリジェンス ソリューション (TIS) に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 財務省インテリジェンス ソリューション (TIS) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- 財務インテリジェンス ソリューション (TIS) は、**SP開始SSOとIDP開始SSO**の両方をサポートします。

### ギャラリーからの財務省インテリジェンス ソリューション (TIS) の追加

Microsoft Entra ID への財務省インテリジェンス ソリューション (TIS) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に財務省インテリジェンス ソリューション (TIS) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「財務省インテリジェンス ソリューション (TIS)」**と入力します。
4. 結果パネルから **財務省インテリジェンス ソリューション (TIS)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### 財務省インテリジェンス ソリューション (TIS) の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、財務省インテリジェンス ソリューション (TIS) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと財務省インテリジェンス ソリューション (TIS) の関連ユーザーとの間にリンク関係を確立する必要があります。

財務省インテリジェンス ソリューション (TIS) に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **財務省インテリジェンス ソリューション (TIS) SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **財務省インテリジェンス ソリューション (TIS) のテスト ユーザーの作成** - 財務省インテリジェンス ソリューション (TIS) で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Treasury Intelligence Solutions (TIS)**&gt;**シングル サインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://eu.tispayments.com` , `https://us.tispayments.com` |
    | テスト | `https://eu-test.tispayments.com` , `https://us-test.tispayments.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://login.eu.tispayments.com/iam-server/SamlSsoLogin` , `https://login.us.tispayments.com/iam-server/SamlSsoLogin` |
    | テスト | `https://login.eu-test.tispayments.com/iam-server/SamlSsoLogin` , `https://login.us-test.tispayments.com/iam-server/SamlSsoLogin` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://login.eu.tispayments.com` , `https://login.us.tispayments.com` |
    | テスト | `https://login.eu-test.tispayments.com` , `https://login.us-test.tispayments.com` |
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **PEM 証明書のダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書には、[証明書のダウンロード] リンクが表示されます。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 財務省インテリジェンス ソリューション (TIS) SSO の構成

**財務省インテリジェンス ソリューション**側でシングル サインオンを構成するには:

1. TIS で、**Administration**&gt;**Security**&gt;**Single Sign-On Configuration** に移動し、**IdP によって開始されるシングル サインオン**または **SP によって開始されるシングル サインオン**を選択します
2. **ID プロバイダー (エンティティ ID) を**指定します。 これは、Microsoft Entra の [識別子] フィールドで Microsoft **Entra** SSO によって提供される値です。
3. [証明書] で、コンピューターにダウンロードした **PEM 証明書** を選択します。
4. **SP によって開始されるシングル サインオン**の場合は、[ログイン URL] フィールドに表示された **ID プロバイダーのログイン URL** **を指定します**。 **IdP によって開始されるシングル サインオン**の場合、ユーザー **ログイン URL** と**ユーザー ログアウト URL** の両方のフィールドは省略可能であり、空白のままにすることができます。

#### 財務省インテリジェンス ソリューション (TIS) のテスト ユーザーの作成

このセクションでは、財務省インテリジェンス ソリューション (TIS) で B.Simon というユーザーを作成します。

1. 新しいユーザーの場合は、新しいユーザーを選択し、 **編集**&gt;**SSO ユーザー**&gt;**有効 SSO ログイン**を選択して、SSO を有効にする必要があります。
2. **SSO** ID の値は Microsoft Entra によって提供されます。 既定では、これはユーザーの属性 **user.userprincipalname** です (Microsoft Entra SSO の **[一意のユーザー識別子** ] に別の属性を選択することで変更できます)。
3. Microsoft Entra ID でテスト ユーザーを選択し、[ **ユーザー プリンシパル名** ] の値をコピーし、TIS のフィールド **SSO ID** に貼り付けます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる財務省インテリジェンス ソリューション (TIS) のサインオン URL にリダイレクトします。
- 財務省インテリジェンス ソリューション (TIS) のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した財務省インテリジェンス ソリューション (TIS) に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [財務省インテリジェンス ソリューション (TIS)] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した財務省インテリジェンス ソリューションに自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trelica-tutorial"} -->
## Microsoft Entra ID で Trelica for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trelica-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Trelica の間にシングル サインオンを構成する方法について説明します。

この記事では、Trelica と Microsoft Entra ID を統合する方法について説明します。 Trelica を Microsoft Entra ID と統合すると、次のことができます。

- Trelica にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Trelica に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Trelica サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Trelica では、IDP initiated SSO がサポートされます。
- Trelica では、Just-In-Time ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Trelica を追加する

Microsoft Entra ID への Trelica の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Trelica を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Trelica**」と入力します。
4. 検索結果から **Trelica** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Trelica 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Trelica に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Trelica の関連ユーザーとの間にリンク関係を確立する必要があります。

Trelica に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. ユーザーがこの機能を使用できるように **Microsoft Entra SSO を構成**します。
    1. B.Simon で Microsoft Entra のシングル サインオンをテストする Microsoft **Entra テスト ユーザーを作成**します。
    2. **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **Trelica SSO を構成**して、アプリケーション側でシングル サインオン設定を構成します。
    1. **Trelica のテストユーザーを** B.Simon に対応するように作成します。 このユーザーを Microsoft Entra の対応するユーザーにリンクさせます。
3. **SSO をテスト** して、構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Azure portal で、次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Trelica** アプリケーション統合ページを参照して、[**管理**] セクションに移動します。 [ **シングル サインオン] を選択します**。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [SAML を使用した単一 Sign-On の設定] ページで、[基本的な SAML 構成] の鉛筆アイコンが強調表示されている]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子** ] ボックスに、URL: `https://app.trelica.com`を入力します。
    2. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します: `https://app.trelica.com/Id/Saml2/<CUSTOM_IDENTIFIER>/Acs`。

    注

    応答 URL は、実際の値ではありません。 この値は、実際の応答 URL (ACS とも呼ばれます) で更新してください。 これを確認するには、Trelica にログインし、 [SAML ID プロバイダーの構成ページ](https://app.trelica.com/Admin/Profile/SAML) (管理者 &gt; アカウント &gt; SAML) に移動します。 **アサーション コンシューマー サービス (ACS) URL** の横にあるコピー ボタンを選択してクリップボードに貼り付け、Microsoft Entra ID の **[応答 URL**] テキスト ボックスに貼り付けることができます。 [Trelica のヘルプ ドキュメント](https://docs.trelica.com/admin/saml/azure-ad)を読むか、質問がある場合[は Trelica クライアント サポート チーム](mailto:support@trelica.com)にお問い合わせください。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、コピー ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [アプリのフェデレーション メタデータ URL] の横にある [コピー] ボタンが強調表示されている [SAML 署名証明書] セクション]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Trelica の SSO の構成

**Trelica** 側でシングル サインオンを構成するには、[SAML ID プロバイダーの構成ページ](https://app.trelica.com/Admin/Profile/SAML) ([Admin &gt; Account &gt; SAML] に移動します)。 [ **新規** ] ボタンを選択します。 [名前] として **Microsoft Entra ID を** 入力し、[メタデータ] タイプの **URL から [メタデータ** ] を選択します。 Microsoft Entra ID から取得した **アプリのフェデレーション メタデータ URL を** Trelica の **[メタデータ URL]** フィールドに貼り付けます。

[Trelica のヘルプ ドキュメント](https://docs.trelica.com/admin/saml/azure-ad)を読むか、質問がある場合[は Trelica クライアント サポート チーム](mailto:support@trelica.com)にお問い合わせください。

#### Trelica のテスト ユーザーの作成

Trelica では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションには、ユーザー側で行うアクションはありません。 Trelica にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Trelica に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Trelica] タイルを選択すると、SSO を設定した Trelica に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trend-micro-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Trend Micro Web Security (TMWS) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trend-micro-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Trend Micro Web Security (TMWS) の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Trend Micro Web Security (TMWS) と Microsoft Entra ID を統合する方法について説明します。 TMWS を Microsoft Entra ID と統合すると、次のことができます:

- TMWS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して TMWS に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SSO が有効になっている TMWS サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TMWS では、**SP** initiated SSO がサポートされます。

### ギャラリーから TMWS を追加する

Microsoft Entra ID への TMWS の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに TMWS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Trend Micro Web Security (TMWS)** 」と入力します。
4. 検索結果で **[Trend Micro Web Security (TMWS)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TMWS 用に Microsoft Entra SSO を構成してテストする

B.Simon というテスト ユーザーを使用して、TMWS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TMWS の関連ユーザーとの間にリンクを確立する必要があります。

次の基本的な手順を実行して、TMWS との Microsoft Entra SSO を構成し、テストします。

1. Microsoft Entra SSO を構成して、この機能をユーザーに対して有効にします。
    1. Microsoft Entra ユーザーを作成して、Microsoft Entra シングル サインオンをテストします。
    2. Microsoft Entra テスト ユーザーに TMWS へのアクセスを許可します。
    3. Microsoft Entra ID でユーザーとグループの同期設定を構成します。
2. アプリケーション側で TMWS SSO を構成します。
3. SSO をテストして、構成を確認します。

### Microsoft Entra SSO の構成

次の手順を完了して Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Trend Micro Web Security (TMWS)** アプリケーション統合ページに移動し、[**管理**] セクションで**[シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** のペン ボタンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] の設定を編集する]
5. **[基本的な SAML 構成]** セクションで、次のボックスに値を入力します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンで URL を入力します。

    `https://auth.iws-hybrid.trendmicro.com/([0-9a-f]{16})`

    b。 **[応答 URL]** ボックスに、次の URL を入力します。

    `https://auth.iws-hybrid.trendmicro.com/simplesaml/module.php/saml/sp/saml2-acs.php/ics-sp`

    注

    前の手順の識別子の値は、実際に入力する値ではありません。 実際の識別子を使用する必要があります。 この値は、Azure AD の **[認証方法]** ページの **[Azure 管理ポータルのサービス プロバイダー設定]** セクションで **[管理] &gt; [ディレクトリ サービス]** から取得できます。
6. TMWS は、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性を示しています。

    [Image: 既定の属性]
7. 上記のスクリーンショットの属性に加えて、TMWS では、他に 2 つの属性が SAML 応答で返されることが想定されています。 これらの属性を次の表に示します。 これらの属性値は事前に設定されますが、要件に合わせて変更できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | `sAMAccountName` | `user.onpremisessamaccountname` |
    | `upn` | `user.userprincipalname` |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を見つけます。 証明書名の横の **[ダウンロード]** リンクを選択し、証明書をダウンロードしてご利用のコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Trend Micro Web Security (TMWS) のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピー]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **を選択して**を作成します。

#### Microsoft Entra テスト ユーザーに TMWS へのアクセスを許可する

このセクションでは、B.Simon に TMWS へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
2. アプリケーションの一覧で、 **[Trend Micro Web Security (TMWS)]** を選択します。
3. アプリの概要ページの **[管理]** セクションで、 **[ユーザーとグループ]** を選択します。
4. **[ユーザーの追加]** を選択し、 **[割り当ての追加]** ダイアログ ボックスで **[ユーザーとグループ]** を選択します。
5. [**ユーザーとグループ**] ダイアログ ボックスで、[**ユーザー**] の一覧で **[B.Simon**] を選択し、画面の下部にある **[選択**] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ロールの **選択** ] ダイアログ ボックスで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログ ボックスで **[割り当て]** を選びます。

#### Microsoft Entra ID でユーザーとグループの同期設定を構成する

1. 左側のウィンドウで、**Microsoft Entra ID**を選択します。
2. **[管理]** で、 **[アプリの登録]** を選択し、 **[すべてのアプリケーション]** で新しいエンタープライズ アプリケーションを選択します。
3. **[管理]** で、**[証明書とシークレット]** を選択します。
4. **[クライアント シークレット]** 領域で、 **[新しいクライアント シークレット]** を選択します。
5. **[クライアント シークレットの追加]** 画面で、必要に応じて説明を追加し、クライアント シークレットの有効期限を選択してから、 **[追加]** を選択します。 **[クライアント シークレット]** 領域に、新しいクライアント シークレットが表示されます。
6. クライアント シークレットの値を記録します。 後で TMWS に入力します。
7. [**管理**] で **API 許可**を選択します。
8. **[API のアクセス許可]** ウィンドウで、 **[アクセス許可の追加]** を選択します。
9. **[API アクセス許可の要求]** ウィンドウの **[Microsoft API]** タブで、 **[Microsoft Graph]** 、 **[アプリケーションの許可]** の順に選択します。
10. 次のアクセス許可を検索して追加します。

    - グループ.リード.オール
    - User.Read.All（ユーザー全体の読み取り）
11. **アクセス許可の追加** を選択します。 設定が保存されたことを確認するメッセージが表示されます。 新しいアクセス許可が、 **[API のアクセス許可]** ウィンドウに表示されます。
12. **[同意する]** 領域で、 **[ に管理者の同意を与えます (既定のディレクトリ)]** を選択し、 **[はい]** を選択します。 要求したアクセス許可に対して管理者の同意が与えられたことを確認するメッセージが表示されます。
13. **[概要]** を選択します。
14. 右側のペインに表示される **[アプリケーション (クライアント) ID]** と **[ディレクトリ (テナント) ID]** の値を記録します。 後で、その情報を TMWS に入力します。

### TMWS SSO を構成する

TMWS SSO を構成するには、アプリケーション側で次の手順を完了します。

1. TMWS 管理コンソールにサインインし、 **Administration**&gt;**USERS & AUTHENTICATION**&gt;**Directory Services** に移動します。
2. 画面の上部の領域の **[here](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ここ)** をクリックします。
3. **[認証方法]** ページで、**[Microsoft Entra ID]** を選択します。
4. **[オン]** または **[オフ]** を選択して、組織の Microsoft Entra ユーザーのデータが TMWS に同期されていない場合に、ユーザーが TMWS を通じて Web サイトにアクセスできるようにするかどうかを構成します。

    注

    Microsoft Entra External ID から同期されないユーザーは、既知の TMWS ゲートウェイまたは組織の専用ポートを介してのみ認証できます。
5. **[Identity Provider Settings](ID プロバイダーの設定)** セクションで、次の手順を実行します。

    ある。 **[サービス URL]** ボックスに、コピーした**ログイン URL** の値を入力します。

    b。 **[ログオン名属性]** ボックスに、取得した**ユーザー要求名**を **user.onpremisessamaccountname** ソース属性と共に入力します。

    c. **[公開 SSL 証明書]** ボックスには、ダウンロードした**証明書 (Base64)** を使用します。
6. **[Synchronization Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/同期設定)** セクションで、次の手順を実行します。

    ある。 **[テナント]** ボックスに、取得した **[ディレクトリ (テナント) ID]** または **[カスタム ドメイン名]** の値を入力します。

    b。 **[アプリケーション ID]** ボックスに、取得した **[アプリケーション (クライアント) ID]** の値を入力します。

    c. **[クライアント シークレット]** ボックスに**クライアント シークレット**を入力します。

    d. **[同期スケジュール]** を選択し、手動で、またはスケジュールに従って Microsoft Entra ID との同期を行います。 **[Manually](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/手動)** を選択した場合は、Active Directory ユーザー情報に変更があるたびに必ず **[Directory Services](ディレクトリ サービス)** ページに戻って手動同期を実行し、TMWS の情報を最新の状態に保つようにしてください。

    え **[接続のテスト]** を選択して、Microsoft Entra サービスに正常に接続できるかどうかを確認します。

    f. **保存** を選択します。

注

Microsoft Entra ID を使用して TMWS を構成する方法の詳細については、「[TMWS での Microsoft Entra 設定の構成](https://docs.trendmicro.com/en-us/enterprise/trend-micro-web-security-online-help/administration/directory-services/azure-active-directo/configuring-azure-ad.aspx)」を参照してください。

### SSO のテスト

Microsoft Entra サービスを構成し、ユーザーの認証方法として Microsoft Entra ID を指定したら、TMWS プロキシ サーバーにサインインしてセットアップを確認することができます。 Microsoft Entra サインインによってアカウントが確認されたら、インターネットにアクセスできます。

注

TMWS では、**[概要]**&gt;**[シングル サインオン]**&gt;**[SAML によるシングル サインオンのセットアップ]**&gt;**[テスト]** からの新規エンタープライズ アプリケーションのシングル サインオンのテストがサポートされていません。

1. ブラウザーからすべての Cookie をクリアし、ブラウザーを再起動します。
2. ブラウザーで TMWS プロキシ サーバーにアクセスします。 詳細については、「[PAC ファイルを使用したトラフィックの転送](https://docs.trendmicro.com/en-us/enterprise/trend-micro-web-security-online-help/administration/pac-files/traffic-forwarding-u.aspx)」を参照してください。
3. インターネットの任意の Web サイトにアクセスします。 TMWS は TMWS キャプティブ ポータルに誘導します。
4. Active Directory アカウント (形式: *domain*\*sAMAccountName* または *sAMAccountName*@*domain*)、メール アドレス、または UPN を指定し、 **[Log On](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ログオン)** を選択します。 TMWS により Microsoft Entra サインイン ウィンドウにリダイレクトされます。
5. [Microsoft Entra サインイン] ウィンドウで、Microsoft Entra アカウントの資格情報を入力します。 これで TMWS にサインインできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trendminer-tutorial"} -->
## Microsoft Entra ID で TrendMiner for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trendminer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TrendMiner の間のシングル サインオンを構成する方法について説明します。

この記事では、TrendMiner と Microsoft Entra ID を統合する方法について説明します。 TrendMiner を Microsoft Entra ID と統合すると、次のことが可能になります。

- TrendMiner にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで TrendMiner に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TrendMiner でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TrendMiner は、**SP および IDP** による SSO の開始をサポートしています

### ギャラリーからの TrendMiner の追加

Microsoft Entra ID への TrendMiner の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TrendMiner を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「TrendMiner**」と入力します。
4. 結果パネルから **TrendMiner** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TrendMiner に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TrendMiner に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TrendMiner の関連ユーザーとの間にリンク関係を確立する必要があります。

TrendMiner に対する Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TrendMiner SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TrendMiner テスト ユーザーの作成** - TrendMiner で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TrendMiner**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.trendminer.cloud/security/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.trendminer.cloud/security/saml/SSO`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.trendminer.cloud/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、TrendMiner クライアント サポート チーム](mailto:support@trendminer.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **TrendMiner のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TrendMiner の SSO の構成

1. 新しい Web ブラウザー ウィンドウを開き、TrendMiner 企業サイトに管理者としてサインインします。
2. 左側のメニューで、[**SECURITY &gt; Identity Provider**] を選択します
3. [ **ID プロバイダー** ] ページで、[ **SAML** ] を選択し、[ **次のステップ**] を選択します。

    [Image: SAML を選択する]
4. **[SAML バックアップ**] ページで **[次のステップ**] を選択します。

    [Image: [SAML Backup] を選択する]
5. Self-Signed の証明書については、**[次の手順]** を **選択します**。

    [Image: Self-Signed 証明書ページ]
6. 署名キーのアップロードを **スキップ** できます。

    [Image: 署名キーのアップロード]
7. **[エンティティのベース URL** **] の [SAML 構成]** 画面で、次のようにドメイン URL を入力します。`https://trendminer.domain.com/`
8. **ID プロバイダーのメタデータ**で、コピーした **Azure メタデータ ファイル**をアップロードし、[**次のステップ**] を選択します。

    [Image: SAML 構成]
9. **[SAML ユーザー マッピング**] セクションで、ログインに使用するユーザー名を入力し、[**完了]** を選択します。

    [Image: SAML ユーザー マッピング]

#### TrendMiner のテスト ユーザーの作成

このセクションでは、TrendMiner で Britta Simon というユーザーを作成します。 [TrendMiner サポート チーム](mailto:support@trendminer.com)と協力して、TrendMiner プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TrendMiner のサインオン URL にリダイレクトされます。
- TrendMiner のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TrendMiner に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで TrendMiner タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TrendMiner に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trevanna-tracks-tutorial"} -->
## Microsoft Entra ID で Trevanna Tracks for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trevanna-tracks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Trevanna Tracks の間でシングル サインオンを構成する方法について説明します。

この記事では、Trevanna Track と Microsoft Entra ID を統合する方法について説明します。 Trevanna Track と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Trevanna Tracks へのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Trevanna Track に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Trevanna Tracks でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Trevanna Tracks では、**SP** 発信 SSO のみがサポートされます。

### ギャラリーから Trevanna トラックを追加する

Microsoft Entra ID への Trevanna Track の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Trevanna トラックを追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックスに「Trevanna Track  入力します。
4. 結果パネルから **Trevanna Tracks** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Trevanna Tracks の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Trevanna Tracks に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Trevanna Tracks の関連ユーザーとの間にリンク関係を確立する必要があります。

Trevanna Tracks に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Trevanna Track の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Trevanna Tracks のテストユーザーを作成** - Trevanna Tracks で B.Simon に対応するユーザーとして作成し、Microsoft Entra 内のユーザーとリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Trevanna Tracks**&gt;**シングルサインオン**をブラウズします。
3. [**シングル サインオン方法の選択]** ページで、[SAML 選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    「a.」 [**識別子 (エンティティ ID)** テキスト ボックスに、次のパターンを使用して URL を入力します:`http://www.okta.com/<Trevanna_ID>`

    b。 [**応答 URL** テキスト ボックスに、URL: `https://api.trevannatracks.com/api/v1/authentication/sso/entra` を入力します。

    c. [**サインオン URL** テキスト ボックスに、URL: `https://app.trevannatracks.com/auth/login` を入力します。

    手記

    識別子の値は実際の値ではありません。 実際の識別子で値を更新します。 これらの値 [取得するには、Trevanna Track サポート チームの](mailto:hello@trevannatracks.com) にお問い合わせください。 Microsoft Entra 管理センターの「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、**証明書 (未加工)** を探し、[**ダウンロード]** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットには、証明書のダウンロードリンクが表示されています。]
7. [**Trevanna Track** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Trevanna TracksのSSOを構成する

Trevanna Tracks **側** でシングルサインオンを構成するには、ダウンロードした **証明書 (未加工)** と、Microsoft Entra 管理センターからコピーした適切な URL を Trevanna Tracks サポートチーム に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Trevanna Track のテスト ユーザーの作成

このセクションでは、Trevanna Tracks で B.Simon というユーザーを作成します。 [Trevanna Track サポート チーム](mailto:hello@trevannatracks.com) と連携して、Trevanna Track プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Trevanna Track のサインオン URL にリダイレクトします。
- Trevanna Track のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Trevanna トラック] タイルを選択すると、このオプションは Trevanna Track のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tribeloo-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Tribeloo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tribeloo-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Tribeloo に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Tribeloo と Microsoft Entra ID の両方で実行して、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Tribeloo](https://www.tribeloo.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Tribeloo でユーザーを作成する。
- アクセスが不要になった場合は、Tribeloo のユーザーを削除します。
- Microsoft Entra ID と Tribeloo の間でユーザー属性の同期を維持する。
- Tribeloo に対して[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tribeloo-tutorial)を行う (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Tribeloo](https://www.tribeloo.com/) テナント。
- 管理者アクセス許可を持つ Tribeloo のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Tribeloo の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Tribeloo を構成する

[Tribeloo アプリ](https://app.tribeloo.com/)に移動し、管理者アクセス許可を持つユーザーでログインします。

1. サイドメニュー (1) を用いて **[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)** (2) に移動し、 **[User management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー管理)** (3) を選択します。

    [Image: ユーザー管理にアクセスする]
2. [ **ユーザー プロビジョニング**(1)]タブを選択します。このタブでは、Microsoft Entra 統合の構成に使用する必要がある Tribeloo 情報にアクセスできます。

    1. **SCIM ベース URL** (2)
    2. **SCIM ベアラー トークン** (3)
3. これらの値をクリップボードにコピーし、対応する Microsoft Entra ID のフィールドに貼り付けます (手順 5 を参照)。 AD のフィールドにはそれぞれ **[テナントの URL]** と **[シークレット トークン]** という名前がついています。

    [Image: Tribeloo のプロビジョニング パラメーター]
4. [ **User Provisioning** ]\(ユーザー プロビジョニング\) タブで、[ **Enable User provisioning**(1)] ボタンを選択して Tribeloo でユーザー プロビジョニングを有効にできるようになりました。

    [Image: Tribeloo のプロビジョニングを有効にする]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Tribeloo を追加する

Microsoft Entra アプリケーション ギャラリーから Tribeloo を追加して、Tribeloo へのプロビジョニングの管理を開始します。 SSO のために Tribeloo を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Tribeloo への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Tribeloo でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Tribeloo の自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Tribeloo]** を選択します。

    [Image: アプリケーション一覧の [Tribeloo] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Tribeloo テナント URL とシークレット トークンを入力します。 [ **テスト接続** ] を選択して、Microsoft Entra ID が Tribeloo に接続できることを確認します。 接続に失敗した場合は、Tribeloo アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Tribeloo に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Tribeloo のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Tribeloo API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | emails[type eq "仕事"].value | 糸 |  |
    | 活動中 | ブール値 |  |
    | ディスプレイ名 | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更履歴

- 2021 年 8 月 12 日 - コア ユーザー属性 **emails[type eq "work"].value** と **addresses[type eq "work"].formatted** のサポートを追加。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tribeloo-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Tribeloo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tribeloo-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Tribeloo の間にシングル サインオンを構成する方法について説明します。

この記事では、Tribeloo と Microsoft Entra ID を統合する方法について説明します。 Tribeloo を Microsoft Entra ID と統合すると、次のことができます。

- Tribeloo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Tribeloo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Tribeloo は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Tribeloo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Tribeloo では、**SP** Initiated SSO がサポートされます。
- Tribeloo では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tribeloo-provisioning-tutorial)がサポートされます。

### ギャラリーからの Tribeloo の追加

Microsoft Entra ID への Tribeloo の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Tribeloo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Tribeloo**」と入力します。
4. 結果のパネルから **[Tribeloo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tribeloo 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Tribeloo に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Tribeloo の関連ユーザーとの間にリンク関係を確立する必要があります。

Tribeloo に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tribeloo の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Tribeloo テストユーザーの作成** - Tribeloo で B.Simon に対応するユーザーを作成し、Microsoft Entra におけるユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Tribeloo**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、次のフィールドの値を入力します。

    [**サインオン URL** テキスト ボックスに、URL: `https://app.tribeloo.com/login` を入力します。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Tribeloo のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Tribeloo SSO を構成する

**Tribeloo** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Tribeloo サポート チーム](mailto:support@tribeloo.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Tribeloo のテスト ユーザーの作成

このセクションでは、Tribeloo で Britta Simon というユーザーを作成します。 [Tribeloo サポート チーム](mailto:support@tribeloo.com)と連携して、Tribeloo プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Tribeloo では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tribeloo-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Tribeloo のサインオン URL にリダイレクトされます。
- Tribeloo のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Tribeloo] タイルを選択すると、このオプションは Tribeloo のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trint-us-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Trint (US) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trint-us-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-02
- Summary: Microsoft Entra ID と Trint (US) の間でシングル サインオンを構成する方法について説明します。

この記事では、Trint (US) と Microsoft Entra ID を統合する方法について説明します。 Trint (US) と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Trint (US) へのアクセスを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Trint (米国) に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Trint (US) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Trint (US) では、**SP-initiated SSO** と **IDP-initiated SSO** の両方がサポートされます。

### ギャラリーから Trint (US) を追加する

Microsoft Entra ID への Trint (US) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Trint (US) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Trint (US)」**と入力します。
4. 結果パネルから **Trint (US)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Trint の Microsoft Entra SSO の構成とテスト (米国)

**B.Simon** というテスト ユーザーを使用して、Trint (US) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Trint (US) の関連ユーザーとの間にリンク関係を確立する必要があります。

Trint (US) に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Trint (US) SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Trint (US) テスト ユーザーの作成 - Trint** (米国) で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Trint (US)**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    エー。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:trint:<Connection_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.trint.com/login/callback?connection=<Connection_ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.trint.com/sso-login/<Connection_ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Trint (米国) サポート チーム](mailto:support@trint.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Trint (US) のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Trint (US) SSO の構成

**Trint (米国)** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Trint (米国) サポート チーム](mailto:support@trint.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Trint (US) テスト ユーザーの作成

このセクションでは、Trint (米国) で B.Simon というユーザーを作成します。 [Trint (米国) サポート チーム](mailto:support@trint.com)と協力して、Trint (米国) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Trint (US) のサインオン URL にリダイレクトします。
- Trint (US) のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Trint (US) に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Trint (US)] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Trint (US) に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tripwire-enterprise-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Tripwire Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tripwire-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tripwire Enterprise の間にシングル サインオンを構成する方法について説明します。

この記事では、Tripwire Enterprise と Microsoft Entra ID を統合する方法について説明します。 Tripwire Enterprise は、ファイル整合性監視 (FIM) とセキュリティ構成管理 (SCM) を使用する、主要なコンプライアンス監視ソリューションです。 Tripwire Enterprise と Microsoft Entra ID を統合すると、次のことができます:

- Tripwire Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Tripwire Enterprise に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

テスト環境で Tripwire Enterprise 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Tripwire Enterprise では、 **IDP** によって開始されるシングル サインオンがサポートされます。

### 前提条件

Microsoft Entra ID を Tripwire Enterprise と統合するには、次のものが必要です:

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Tripwire Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Tripwire Enterprise アプリケーションを追加する必要があります。 アプリケーションに割り当て、シングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Tripwire Enterprise を追加する

Microsoft Entra アプリケーション ギャラリーから Tripwire Enterprise を追加して、Tripwire Enterprise でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーを作成して割り当てる

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができます。 このウィザードには、シングル サインオン構成ウィンドウへのリンクも表示されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra シングル サインオンを有効にするには、次の手順を行います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Tripwire Enterprise**&gt;**シングルサインオンにアクセスします。**
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** がある場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする方法を示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択するスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。
6. Tripwire Enterprise アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Tripwire Enterprise では、これがユーザーの電子メール アドレスにマップされることを想定しています。 そのため、リストから **user.mailnickname** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: トークン属性の画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Tripwire Enterprise の SSO の構成

Tripwire Enterprise でシングル サインオンを構成するには、Tripwire Enterprise セキュリティ強化ガイドの「 **Tripwire Enterprise と SAML 認証の使用** 」セクションを参照してください。Tripwire Customer Center でダウンロードできます。 サポートが必要な場合 [は、Tripwire Enterprise サポート チーム](mailto:support@tripwire.com)にお問い合わせください。

#### Tripwire Enterprise のテスト ユーザーの作成

Tripwire Enterprise ユーザーを作成するには、Tripwire Enterprise ユーザー ガイドの「 **ユーザー アカウントの作成** 」セクションを参照してください。Tripwire カスタマー センターでダウンロードできます。 サポートが必要な場合 [は、Tripwire Enterprise サポート チーム](mailto:support@tripwire.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Tripwire Enterprise に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Tripwire Enterprise] タイルを選択すると、SSO を設定した Tripwire Enterprise に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trisotechdigitalenterpriseserver-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Trisotech Digital Enterprise Server を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trisotechdigitalenterpriseserver-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Trisotech Digital Enterprise Server の間でシングル サインオンを構成する方法について説明します。

この記事では、Trisotech Digital Enterprise Server と Microsoft Entra ID を統合する方法について説明します。 Trisotech Digital Enterprise Server と Microsoft Entra ID の統合には、次の利点があります。

- Trisotech Digital Enterprise Server にアクセスする Microsoft Entra ID ユーザーを制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Trisotech Digital Enterprise Server に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Trisotech Digital Enterprise Server でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Trisotech Digital Enterprise Server では、**SP** Initiated SSO がサポートされます
- Trisotech Digital Enterprise Server では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Trisotech Digital Enterprise Server の追加

Microsoft Entra ID への Trisotech Digital Enterprise Server の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Trisotech Digital Enterprise Server を追加する必要があります。

**ギャラリーから Trisotech Digital Enterprise Server を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Trisotech Digital Enterprise Server**」と入力し、結果パネルで **Trisotech Digital Enterprise Server** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果リスト内の Trisotech Digital Enterprise Server]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Trisotech Digital Enterprise Server で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Trisotech Digital Enterprise Server 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Trisotech Digital Enterprise Server で Microsoft Entra のシングル サインオンを構成してテストするには、次の手順を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Trisotech Digital Enterprise Server シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Trisotech Digital Enterprise Server のテストユーザーを作成** - Trisotech Digital Enterprise Server で Microsoft Entra のユーザーの表示にリンクした Britta Simon に対応するテストユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Trisotech Digital Enterprise Server で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Trisotech Digital Enterprise Server** アプリケーション統合ページを参照し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Trisotech Digital Enterprise Server のドメインと URL] のシングル サインオン情報]

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.trisotech.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.trisotech.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Trisotech Digital Enterprise Server サポート チーム](mailto:support@trisotech.com)にご連絡ください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Trisotech Digital Enterprise Server シングル サインオンの構成

1. 別の Web ブラウザー ウィンドウで、Trisotech Digital Enterprise Server 構成企業サイトに管理者としてサインインします。
2. **メニュー アイコン**を選択し、[管理] を選択**します**。

    [Image: Microsoft Digital Enterprise Server の [管理] アイコンのスクリーンショット。]
3. **[User Provider]\(ユーザー プロバイダー\)** を選択します。

    [Image: メニューから [User Provider](ユーザー プロバイダー) が選択された画面のスクリーンショット。]
4. **[User Provider Configurations]\(ユーザー プロバイダー構成\)** セクションで、次の手順を実行します。

    [Image: [User Provider Configurations](ユーザー プロバイダー構成) 画面のスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[認証方法]** のドロップダウン リストから **[Secured Assertion Markup Language 2 (SAML 2)]** を選択します。

    b。 **[メタデータ URL]** テキスト ボックスに、Azure Portal からコピーした **[アプリのフェデレーション メタデータ URL]** 値を貼り付けます。

    c. **[アプリケーション ID]** テキストボックスに、次のパターンを使って URL を入力します: `https://<companyname>.trisotech.com`。

    d. **[保存]** を選びます。

    え **[Allowed Domains (empty means everyone)]\(許可されているドメイン (空の場合はすべてのユーザー))** テキストボックスにドメイン名を入力します。許可されているドメインに一致するユーザーのライセンスが自動的に割り当てられます

    f. **[保存]** を選びます。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Trisotech Digital Enterprise Server のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Trisotech Digital Enterprise Server に作成します。 Trisotech Digital Enterprise Server では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Trisotech Digital Enterprise Server にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Trisotech Digital Enterprise Server サポート チーム](mailto:support@trisotech.com)にお問い合わせください。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Trisotech Digital Enterprise Server] タイルを選択すると、SSO を設定した Trisotech Digital Enterprise Server に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/true-office-learning-lio-tutorial"} -->
## Microsoft Entra ID を使用して True Office Learning - LIO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/true-office-learning-lio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と True Office Learning - LIO 間にシングル サインオンを構成する方法について学習します。

この記事では、True Office Learning - LIO と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と True Office Learning - LIO を統合すると、次のことができます。

- True Office Learning - LIO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して True Office Learning - LIO に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- True Office Learning - LIO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- True Office Learning - LIO では、**SP** Initiated SSO がサポートされます。

### ギャラリーから True Office Learning - LIO を追加する

Microsoft Entra ID への True Office Learning - LIO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に True Office Learning - LIO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**True Office Learning - LIO**」と入力します。
4. 結果パネルから **[True Office Learning - LIO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### True Office Learning - LIO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、True Office Learning - LIO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと True Office Learning - LIO の関連ユーザーとの間にリンク関係を確立する必要があります。

True Office Learning - LIO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **True Office Learning - LIO の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **True Office Learning - LIO テスト ユーザーの作成** - True Office Learning - LIO における B.Simon の対応ユーザーを作成し、そのユーザーを Microsoft Entra 上の B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**True Office Learning - LIO**&gt;**シングルサインオンに移動してください。**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://learn-sso.trueoffice.com/simplesaml/module.php/saml/sp/metadata.php/<CUSTOMER_NAME>-sp`
    2. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://learn-sso.trueoffice.com/<CUSTOMER_NAME>`

        注

        これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[True Office Learning - LIO クライアント サポート チーム](mailto:service@trueoffice.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[True Office Learning - LIO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### True Office Learning - LIO の SSO の構成

構成に関する質問やメタデータのコピーを要求するには、[True Office Learning - LIO サポート チーム](mailto:service@trueoffice.com)にお問い合わせください。 リクエストには、次の情報を含めてください。

- 会社名。
- 会社 ID (わかっている場合)。
- 既存または新規の構成。

#### True Office Learning - LIO のテスト ユーザーの作成

このセクションでは、True Office Learning - LIO で Britta Simon というユーザーを作成します。 [True Office Learning - LIO サポート チーム](mailto:service@trueoffice.com)と連携して、True Office Learning - LIO プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択します。 ログイン フローを開始できる True Office Learning - LIO のサインオン URL にリダイレクトされます。
- True Office Learning - LIO のサインオン URL に直接移動し、そのサイトからのログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [True Office Learning - LIO] タイルを選択すると、True Office Learning - LIO のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/truechoice-tutorial"} -->
## Microsoft Entra ID で TrueChoice for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/truechoice-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TrueChoice 間にシングル サインオンを構成する方法について説明します。

この記事では、TrueChoice と Microsoft Entra ID を統合する方法について説明します。 TrueChoice と Microsoft Entra ID を統合すると、次のことができます。

- TrueChoice にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して TrueChoice に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な TrueChoice サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TrueChoice では、 **SP** Initiated SSO がサポートされます。
- TrueChoice では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから TrueChoice を追加する

Microsoft Entra ID への TrueChoice の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TrueChoice を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「TrueChoice**」と入力します。
4. 結果パネルから **TrueChoice** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TrueChoice 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TrueChoice に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TrueChoice の関連ユーザーとの間にリンク関係を確立する必要があります。

TrueChoice に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TrueChoice の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TrueChoice テストユーザーの作成** - TrueChoice で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TrueChoice**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:amazon:cognito:sp:<TRUECHOICE_APPID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<APP>.auth.us-east-2.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<APP>.auth.us-east-2.amazoncognito.com/login?response_type=code&client_id=<ID>&redirect_uri=https://<APP_ID>.amplifyapp.com/auth/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [TrueChoice クライアント サポート チーム](mailto:helpdesk@truechoice.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. TrueChoice アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TrueChoice の SSO の構成

**TrueChoice** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[TrueChoice サポート チーム](mailto:helpdesk@truechoice.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TrueChoice のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TrueChoice に作成します。 TrueChoice では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 TrueChoice にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TrueChoice のサインオン URL にリダイレクトされます。
- TrueChoice のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TrueChoice] タイルを選択すると、このオプションは TrueChoice のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->
