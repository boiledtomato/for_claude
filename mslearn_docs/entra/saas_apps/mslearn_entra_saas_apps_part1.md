# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 1)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 72

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/10000ftplans-tutorial"} -->
## Microsoft Entra ID を使用して 10,000ft Plans をシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/10000ftplans-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と 10,000ft Plans の間のシングル サインオンを構成する方法について説明します。

この記事では、10,000ft Plans と Microsoft Entra ID を統合する方法について説明します。 10,000ft Plans と Microsoft Entra ID を統合すると、次のことができます。

- 10,000ft Plans にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して 10,000ft Plans に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 10,000ft Plans でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- 10,000ft Plans では、**SP** によって開始される SSO がサポートされます。
- 10,000ft Plans **では、Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの 10,000ft Plans の追加

Microsoft Entra ID への 10,000ft Plans の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に 10,000ft Plans を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**10,000ft Plans**」と入力します。
4. 結果のパネルから **[10,000ft Plans]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 10,000ft Plans 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、10,000ft Plans に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと 10,000ft Plans の関連ユーザーとの間にリンク関係を確立する必要があります。

10,000ft Plans に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **10,000ft Plans の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **10,000ft Plans のテスト ユーザーを作成する** - 10,000ft Plans で B.Simon に対応するユーザーを作成し、それを Microsoft Entra 上のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**10,000ft Plans**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://rm.smartsheet.com/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://rm.smartsheet.com/saml/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 ` https://rm.smartsheet.com`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、コピー アイコンを選択して **アプリのフェデレーション メタデータ URL をコピーします**。 それを自分のコンピューターに保存します。

    [Image: コピー アイコンが強調表示されている SAML 署名証明書のスクリーンショット]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 10,000ft Plans の SSO の構成

1. 10000ft Plans の Web サイトに管理者としてサインインします。
2. [ **設定]** を選択し、ドロップダウンから **[アカウント設定]** を選択します。

    [Image: 設定アイコンのスクリーンショット。]
3. 左側のメニューで **[SSO** ] を選択し、次の手順を実行します。

    [Image: SSO の設定ページのスクリーンショット。]

    a. [SSO の設定]セクションで、 **[自動構成]** を選択します。

    b。 **[IdP メタデータ URL]** テキスト ボックスに、先ほどコピーした **[アプリのフェデレーション メタデータ URL]** の値を入力します。

    c. **[アカウントにない認証されたユーザーを自動プロビジョニングする]** チェックボックスをオンにします。

    d. **保存** を選択します。

#### 10000ft Plans のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを 10,000ft Plans に作成します。 10,000ft Plans では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 10,000ft Plans にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる 10,000ft Plans のサインオン URL にリダイレクトされます。
- 10,000ft Plans のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [10,000ft Plans] タイルを選択すると、このオプションは 10,000ft Plans のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/123formbuilder-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に 123FormBuilder SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/123formbuilder-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と 123FormBuilder SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、123FormBuilder SSO と Microsoft Entra ID を統合する方法について説明します。 123FormBuilder SSO を Microsoft Entra ID と統合すると、次のことが可能になります。

- 123FormBuilder SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで 123FormBuilder SSO に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 123FormBuilder SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- 123FormBuilder SSO では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- 123FormBuilder SSO では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの 123FormBuilder SSO の追加

Microsoft Entra ID への 123FormBuilder SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に 123FormBuilder SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**123FormBuilder SSO**」と入力します。
4. 結果のパネルから **[123FormBuilder SSO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 123FormBuilder SSO に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、123FormBuilder SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、123FormBuilder SSO での関連ユーザーとの間にリンク関係を確立する必要があります。

123FormBuilder SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **123FormBuilder SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **123FormBuilder SSO のテスト ユーザーの作成** - 123FormBuilder SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**123FormBuilder SSO**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    a. **[識別子]** ボックスに、`https://www.123formbuilder.com/saml/azure_ad/<TENANT_ID>/metadata` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://www.123formbuilder.com/saml/azure_ad/<TENANT_ID>/acs` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://www.123formbuilder.com/saml/azure_ad/<TENANT_ID>/sso` という形式で URL を入力します。

    Note

    これらの値は実際の値ではありません。 これらの値は、この記事で後述する実際の URL と識別子から更新する必要があります。
7. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、**[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[123FormBuilder SSO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 123FormBuilder SSO の構成

1. **123FormBuilder SSO** 側からシングル サインオンを構成するために、https://www.123formbuilder.com/form-2709121/ に移動して次の手順を実行します。

    [Image: [SSO SAML - Identity Provider](SSO SAML - ID プロバイダー) 構成画面が表示されているスクリーンショット。]

    a. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** ボックスに、ユーザーの電子メール (`B.Simon@Contoso.com` など) を入力します。

    b。 [ **アップロード]** を選択し、以前にダウンロードしたメタデータ XML ファイルを参照します。

    c. [ **送信フォーム] を選択します**。
2. The **Microsoft Entra ID - シングル サインオン - アプリケーション設定の構成**ページには、次の値が含まれます。

    - IDENTIFIER
    - 応答 URL
    - サインオン URL

    次の手順に従います。

    1. アプリケーションを **IDP 開始モード**で構成する場合は、インスタンスの **[IDENTIFIER](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/識別子)** をコピーし、**[基本的な SAML 構成]** セクションの **[識別子]** ボックスに貼り付けます。
    2. アプリケーションを **IDP 開始モード**で構成する場合は、インスタンスの **[REPLY URL](応答 URL)** をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL]** ボックスに貼り付けます。
    3. アプリケーションを **SP 開始モード**で構成する場合は、インスタンスの **[SIGN ON URL](サインオン URL)** をコピーし、**[基本的な SAML 構成]** セクションの **[サインオン URL]** ボックスに貼り付けます。

#### 123FormBuilder SSO のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを 123FormBuilder SSO に作成します。 123FormBuilder SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 123FormBuilder SSO にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる 123FormBuilder SSO サインオン URL にリダイレクトされます。
- 123FormBuilder SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した 123FormBuilder SSO に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで 123FormBuilder SSO タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した 123FormBuilder SSO に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/15five-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に 15Five を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/15five-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: ユーザー アカウントを 15Five に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、[15Five](https://www.15five.com/pricing/) にユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する 15Five とMicrosoft Entra IDで実行する手順を示することです。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「Microsoft Entra IDを使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- 15Five でユーザーを作成する
- アクセスが不要になった場合に 15Five でユーザーを削除する
- Microsoft Entra IDと 15Five の間でユーザー属性の同期を維持する
- 15Five でグループとグループ メンバーシップをプロビジョニングする
- 15Five への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/15five-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

15Five は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- ユーザー アカウントが Microsoft Entra ID において [permission](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持ち、プロビジョニングを構成できるアカウント ([Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [Application Owner](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications) など)。
- [15Five テナント](https://www.15five.com/pricing/)。
- Admin アクセス許可がある 15Five のユーザー アカウント

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDと15Fiveの間でどのデータをマッピングするか決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように 15Five を構成する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に 15Five を構成する前に、15Five で SCIM プロビジョニングを有効にする必要があります。

1. [15Five 管理コンソールにサインインします](https://my.15five.com/)。 **[機能&gt;統合**] に移動します。

    [Image: 15Five 管理コンソールのスクリーンショット。統合はメニューの [機能] の下に表示され、[機能] と [統合] の両方が強調表示されます。]
2. **SCIM 2.0** を選択します。

    [Image: 15Five 管理コンソールの [統合] ページのスクリーンショット。[ツール] で、S C I M 2.0 が強調表示されています。]
3. **SCIM 統合&gt; OAuth トークンの生成に移動します**。

    [Image: 15Five 管理コンソールの [S C I M 統合] ページのスクリーンショット。[OAuth トークンの生成] が強調表示されています。]
4. **SCIM 2.0 ベース URL** と**アクセス トークン**の値をコピーします。 この値は、15Five アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: S C I M 統合ページのスクリーン ショット。Token テーブルでは、S C I M 2.0 base U R L と Access トークンの横にある値が強調表示されています。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから 15Five を追加する

Microsoft Entra アプリケーション ギャラリーから 15Five を追加して、15Five へのプロビジョニングの管理を開始します。 SSO のために 15Five を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: 15Five への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、15Five でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで 15Five の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps** に移動します

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **15Five** を選択します。

    [Image: アプリケーションの一覧の 15Five のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、15Five テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが 15Five に接続できることを確認します。 接続に失敗した場合は、15Five アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute Mapping** セクションで、Microsoft Entra IDから 15Five に同期されるユーザー属性を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で 15Five のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | 活動中 | ブール値 |
    | タイトル | 糸 |
    | emails[type eq "仕事"].value | 糸 |
    | ユーザー名 | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | externalId | 糸 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |
    | urn:ietf:params:scim:schemas:extension:15Five:2.0:User:location | 糸 |
    | urn:ietf:params:scim:schemas:extension:15Five:2.0:User:startDate | 糸 |
12. グループを選択 **します**。
13. **Attribute Mapping** セクションで、Microsoft Entra IDから 15Five に同期されるグループ属性を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で 15Five のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | externalId | 糸 |
    | displayName | 糸 |
    | members | リファレンス |
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### コネクタの制限事項

- 15Five では、ユーザーの論理的な削除はサポートされていません。

### 変更ログ

- 2020/06/16 - ユーザー向けにエンタープライズ拡張属性 "Manager" とカスタム属性 "Location" と"Start Date" のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/15five-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に 15Five を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/15five-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と 15Five の間のシングル サインオンを構成する方法について説明します。

この記事では、15Five と Microsoft Entra ID を統合する方法について説明します。 15Five を Microsoft Entra ID と統合すると、次のことが可能になります。

- 15Five にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで 15Five に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 15Five でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- 15Five では、 **SP** Initiated SSO がサポートされます。
- 15Five では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/15five-provisioning-tutorial)。

### ギャラリーからの 15Five の追加

Microsoft Entra ID への 15Five の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に 15Five を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「15Five**」と入力します。
4. 結果パネルから **15Five** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 15Five 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、15Five に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと 15Five の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を 15Five と一緒に構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **15Five SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **15Five テスト ユーザーの作成** - 15Five に、Microsoft Entra のユーザー表現にリンクされた B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[15Five]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.15five.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_NAME>.15five.com/saml2/metadata/`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、15Five クライアント サポート チーム](https://www.15five.com/contact/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **15Five のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 15Five の SSO の構成

**15Five** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [15Five サポート チーム](https://www.15five.com/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### 15Five のテスト ユーザーの作成

Microsoft Entra ユーザーが 15Five にログインできるようにするには、ユーザーを 15Five にプロビジョニングする必要があります。 15Five の場合、プロビジョニングは手動で行います。

#### ユーザー プロビジョニングを構成するには、次の手順に従います。

1. **15Five** 企業サイトに管理者としてログインします。
2. [ **会社の管理**] に移動します。

    [Image: 会社を管理する]
3. 「**人々**」に移動&gt;**人々を追加します**。

    [Image: 人々]
4. [ **新しいユーザーの追加]** セクションで、次の手順を実行します。

    [Image: 新しい人物を追加]

    a. プロビジョニングする有効な Microsoft Entra アカウントの **名**、 **姓**、 **タイトル**、 **メール アドレス** を関連するテキスト ボックスに入力します。

    b。 [ **完了] を選択します**。

    注

    アカウントがアクティブになる前に、Microsoft Entra アカウント所有者に、アカウント確認用のリンクを記述した電子メールが送信されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる 15Five のサインオン URL にリダイレクトされます。
- 15Five のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [15Five] タイルを選択すると、このオプションは 15Five のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/23video-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に 23 Video を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/23video-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と 23 Video の間のシングル サインオンを構成する方法について説明します。

この記事では、23 Video と Microsoft Entra ID を統合する方法について説明します。 23 Video を Microsoft Entra ID と統合すると、次のことが可能になります。

- 23 Video へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して 23 Video に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 23 Video でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- 23 Video では、**SP** による SSO がサポートされています。

### ギャラリーから 23 Video を追加する

Microsoft Entra ID への 23 Video の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に 23 Video を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに「**23 Video**」と入力します。
4. 結果パネルから **[23 ビデオ** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 23 Video 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、23 Video に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと 23 Video の関連ユーザー間にリンク関係を確立する必要があります。

Microsoft Entra SSO と 23 Video を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **23 Video SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **23 Video テストユーザーの作成** - 23 Video で B.Simon に対応するユーザーを作成し、そのユーザーが Microsoft Entra の表現である B.Simon にリンクされるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**23 Video** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.23video.com/saml/trust/<uniqueid>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.23video.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [23 Video Client サポート チーム](mailto:support@23company.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **23 ビデオのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 23 Video SSO の構成

**23 Video** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [23 Video サポート チーム](mailto:support@23company.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### 23 Video テスト ユーザーの作成

このセクションの目的は、23 Video で B.Simon というユーザーを作成することです。

**23 Video で B.Simon というユーザーを作成するには、次の手順に従います。**

1. 23 Video 企業サイトに管理者としてサインオンします。
2. **[設定]** に移動します。
3. [ **ユーザー** ] セクションで、[構成] を選択 **します**。

    [Image: [ユーザー] セクションが強調表示されているスクリーンショット。]
4. [ **新しいユーザーの追加] を選択します**。

    [Image: [新しいユーザーの追加] ボタンが強調表示されているスクリーンショット。]
5. [ **このサイトに参加するユーザーを招待する** ] セクションで、次の手順を実行します。

    [Image: ユーザーの割り当て]

    a. [ **電子メール アドレス** ] ボックスに、ユーザーのメール アドレス ( B.Simon@contoso.comなど) を入力します。

    b。 [ **ユーザーの追加]を選択します。.**.

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる 23 ビデオ サインオン URL にリダイレクトされます。
- 23 Video のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [23 ビデオ] タイルを選択すると、このオプションは 23 ビデオ のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/360online-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に 360 Online を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/360online-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と 360 Online の間のシングル サインオンを構成する方法について説明します。

この記事では、360 Online と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と 360 Online を統合すると、次のことができます。

- 360 Online へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで 360 Online に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 360 Online のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- 360 Online により、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから 360 Online を追加する

Microsoft Entra ID への 360 Online の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に 360 Online を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**360 Online**」と入力します。
4. 結果パネルから **360 Online** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 360 Online 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、360 Online に Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと 360 Online の関連ユーザー間にリンク関係を確立する必要があります。

Microsoft Entra SSO と 360 Online を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **360 Online の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **360 Online テスト ユーザーの作成** - Microsoft Entra にリンクされている B.Simon に対応する 360 Online のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[360 Online]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.public360online.com`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[360 Online クライアント サポート チーム](mailto:360online@software-innovation.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up 360 Online](360 Online の設定)** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 360 Online の SSO の構成

**360 Online** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML** とアプリケーション構成からコピーした適切な URL を [360 Online サポート チーム](mailto:360online@software-innovation.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### 360 Online のテスト ユーザーの作成

このセクションでは、360 Online で Britta Simon というユーザーを作成します。 [360 Online サポート チーム](mailto:360online@software-innovation.com)と連携して、360 Online プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる 360 Online のサインオン URL にリダイレクトされます。
- 360 Online のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [360 Online] タイルを選択すると、このオプションは 360 Online のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/4dx-tutorial"} -->
## Microsoft Entra ID で 4DX for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/4dx-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と 4DX の間でシングル サインオンを構成する方法について説明します。

この記事では、4DX と Microsoft Entra ID を統合する方法について説明します。 4DX と Microsoft Entra ID を統合すると、次のことができます。

- 4DX にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して 4DX に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- 4DX でのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- 4DX では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーから 4DX を追加する

Microsoft Entra ID への 4DX の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に 4DX を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;と**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「4DX**」と入力します。
4. 結果のパネルから 4DX  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 4DX の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、4DX に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと 4DX の関連ユーザーとの間にリンク関係を確立する必要があります。

4DX に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **4DX SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **4DXのテストユーザーを作成 - 4DX**でB.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**4DX**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. 4DX アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、4DX アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | companykey | `<unique ID>` |

    手記

    顧客アサーションのこの `<unique ID>` については、 [4DX サポート チーム](mailto:support@bahrcode.com)にお問い合わせください。
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **4DX のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 4DX SSO の構成

**4DX** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [4DX サポート チーム](mailto:support@bahrcode.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### 4DX テスト ユーザーの作成

このセクションでは、4DX で Britta Simon というユーザーを作成します。 [4DX サポート チーム](mailto:support@bahrcode.com)と協力して、4DX プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した 4DX に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [4DX] タイルを選択すると、SSO を設定した 4DX に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/4me-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に 4me を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/4me-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: ユーザー アカウントを 4me に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、ユーザーやグループを 4me に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するために、4me とMicrosoft Entra IDで実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

4me は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [4me テナント](https://www.4me.com/)
- Admin アクセス許可がある 4me のユーザー アカウント。

### ギャラリーから 4me を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に 4me を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に 4me を追加する必要があります。

** Microsoft Entra アプリケーション ギャラリーから 4me を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. [ **ギャラリーからの追加** ] セクションで、「 **4me**」と入力し、検索ボックスで **4me** を選択します。
4. 結果パネルから **4me** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の 4me のスクリーンショット。]

### 4me へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、4me へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを 4me に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを 4me に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを 4me に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後で追加のユーザーやグループを割り当てることができます。
- 4me にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### 4me への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、4me でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

[4me シングル サインオンの記事] に記載されている手順に従って、4me に対して SAML ベースのシングル サインオンを有効にすることもできます。4me-tutorial.md)。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra IDで 4me の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **4me** を選択します。

    [Image: アプリケーションの一覧の [4me] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. 4me アカウントの **テナント URL** と **シークレット トークン** を取得するには、手順 6 の説明に従ってチュートリアルに従います。
7. 4me 管理コンソールにサインインします。 **[設定]** に移動します。

    [Image: 4me 設定のスクリーンショット。]

    検索バーに **アプリ** を入力します。

    [Image: 4me アプリのスクリーンショット。]

    **SCIM** ドロップダウンを開き、シークレット トークンと SCIM エンドポイントを取得します。

    [Image: 4me SCIM のスクリーンショット。]
8. 手順 5 に示すフィールドに値を入力したら、**[接続のテスト** を選択して、Microsoft Entra IDが 4me に接続できることを確認します。 接続できない場合は、使用中の 4me アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: トークンのスクリーンショット。]
9. [ **作成]** を選択して構成を作成します。
10. [**概要**] ページで **[プロパティ**] を選択します。
11. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
12. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
13. **Attribute Mapping** セクションで、Microsoft Entra IDから 4me に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で 4me のユーザー アカウントとの照合に使用されます。 選択した一致する属性の [フィルター処理が 4me でサポート](https://developer.xurrent.com/v1/scim/users/) されていることを確認してください。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: 4me ユーザー属性リストのスクリーンショット。][Image: 4me User attributes list-2 のスクリーンショット。]
14. グループを選択 **します**。
15. **Attribute Mapping** セクションで、Microsoft Entra IDから 4me に同期されるグループ属性を確認してください。 [ **照合** プロパティ] として選択されている属性は、更新操作で 4me のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: 4me グループ属性の一覧のスクリーンショット。]
16. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
17. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
18. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### コネクタの制限事項

- 4me には、テスト環境と運用環境用のさまざまな SCIM エンドポイント URL があります。 前者は **.qa** で終わり、後者は**.com**で終わります
- 4me で生成されたシークレット トークンには、生成から 1 か月の期限があります。
- 4me では、ユーザーの **HARD DELETE** はサポートされていません。 SCIM ユーザーは 4me で実際に削除されることはありません。代わりに、SCIM ユーザーの **アクティブな** 属性が **false** に設定され、関連する 4me 人物レコードが無効になります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/4me-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に 4me を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/4me-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と 4me 間のシングル サインオンを構成する方法について説明します。

この記事では、4me と Microsoft Entra ID を統合する方法について説明します。 4me を Microsoft Entra ID と統合すると、次のことが可能になります。

- 4me にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで 4me に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

4me は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- 4me でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- 4me では、 **SP** Initiated SSO がサポートされます。
- 4me では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- 4me では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/4me-provisioning-tutorial)。

### ギャラリーから 4me を追加する

Microsoft Entra ID への 4me の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に 4me を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「4me** 」と入力します。
4. 結果パネルから **4me** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 4me に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、4me に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと 4me の関連ユーザーとの間にリンク関係を確立する必要があります。

4me に対する Microsoft Entra SSO を構成およびテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **4me SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **4me テスト ユーザーの作成** - 4me で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**4me**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 運用 | `https://<SUBDOMAIN>.4me.com` |
    | QA | `https://<SUBDOMAIN>.4me.qa` |
    |  |  |

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 運用 | `https://<SUBDOMAIN>.4me.com` |
    | QA | `https://<SUBDOMAIN>.4me.qa` |
    |  |  |

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [4me クライアント サポート チーム](mailto:support@4me.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. 4me アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、4me アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
8. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
9. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
10. [ **4me のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### 4me SSO の構成

1. 別の Web ブラウザー ウィンドウで、4me に管理者としてサインインします。
2. 左上の **[設定** ] ロゴを選択し、左側のバーで [ **シングル サインオン**] を選択します。

    [Image: 4me 設定]
3. [ **シングル サインオン** ] ページで、次の手順に従います。

    [Image: 4me のシングル サインオン]

    a. **[有効]** オプションを選択します。

    b。 [ **リモート ログアウト URL** ] ボックスに、前にコピーした **ログアウト URL** の値を貼り付けます。

    c. [ **SAML** ] セクションの [ **SAML SSO URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。

    d. **[証明書のフィンガープリント]** ボックスに、以前コピーした **THUMBPRINT** の値をコロンで区切って貼り付けます (AA:BB:CC:DD:EE:FF:GG:HH:II)。

    e. **[保存] を選択します**。

#### 4me のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを 4me に作成します。 4me では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 4me にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [4me サポート チーム](mailto:support@4me.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる 4me のサインオン URL にリダイレクトされます。
- 4me のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [4me] タイルを選択すると、このオプションは 4me のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/8x8-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に 8x8 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/8x8-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: ユーザー アカウントを Microsoft Entra ID から 8x8 に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために 8x8 管理コンソールとMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra プロビジョニング サービスを使用して、Microsoft Entra ID はユーザーとグループを[8x8](https://www.8x8.com)に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- 8x8 でユーザーを作成する
- アクセスが不要になった場合に 8x8 でユーザーを非アクティブ化する
- Microsoft Entra IDと 8x8 の間でユーザー属性の同期を維持する
- 8x8 への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/8x8virtualoffice-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

8x8 は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- Microsoft Entra IDのユーザー アカウントには、プロビジョニングを構成する[権限](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) (例えば、[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)) が必要です。
- 任意のレベルの 8x8 X シリーズ サブスクリプション。
- [Admin Console](https://vo-cm.8x8.com) の管理者権限を備えた 8x8 ユーザー アカウント。
- [Microsoft Entra ID によるシングルサインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/8x8virtualoffice-tutorial)は既に構成されています。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDと8x8の間で[マッピングするデータを決定](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように 8x8 を構成する

このセクションでは、Microsoft Entra IDでのプロビジョニングをサポートするように 8x8 を構成する手順について説明します。

#### 8x8 Admin Console でユーザー プロビジョニング アクセス トークンを構成するには、次の操作を実行します。

1. [管理コンソール](https://admin.8x8.com)にサインインします。 **[ID とセキュリティ]** を選択します。

    [Image: [8x8 Admin Console] を示すスクリーンショット。]
2. **[ユーザー プロビジョニング統合 (SCIM)]** ウィンドウで、有効にするトグルを選択し、[保存] を選択**します**。

    [Image: ユーザー プロビジョニング統合スライダーに対するコールアウトのある Admin Console の [ID とセキュリティ] ページを示すスクリーンショット。]
3. **8x8 URL** 値と **8x8 API トークン**値をコピーします。 これらの値は、8x8 アプリケーションの [プロビジョニング] タブの **[テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドにそれぞれ入力されます。

    [Image: [トークン] フィールドに対するコールアウトのある Admin Console の [ID とセキュリティ] ページを示すスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから 8x8 を追加する

Microsoft Entra アプリケーション ギャラリーから 8x8 を追加して、8x8 へのプロビジョニングの管理を開始します。 SSO のために 8x8 を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: 8x8 への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、8x8 でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで 8x8 の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[8x8]** を選択します。

    [Image: [アプリケーションの一覧] の 8x8 のリンクを示すスクリーンショット]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、8x8 テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが 8x8 に接続できることを確認します。 接続に失敗した場合は、8x8 アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから 8x8 に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で 8x8 のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、8x8 API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | 注記 |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ユーザー名とフェデレーション ID の両方を設定します |
    | externalId | 糸 |  |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 | 個人の連絡先番号 |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 | 個人の連絡先番号 |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:8x8:1.1:User:site | 糸 | ユーザーの作成後に更新できない |
    | ロケール | 糸 | 既定ではマップされません |
    | タイムゾーン | 糸 | 既定ではマップされません |
12. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/8x8virtualoffice-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に 8x8 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/8x8virtualoffice-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と 8x8 間のシングル サインオンを構成する方法について説明します。

この記事では、8x8 と Microsoft Entra ID を統合する方法について説明します。 8x8 を Microsoft Entra ID と統合すると、次のことができます。

- 8x8 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで 8x8 に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

8x8 は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- 8x8 サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- 8x8 では、**SP 起動の SSO と IDP 起動の SSO** の両方がサポートされています。
- 8x8 では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/8x8-provisioning-tutorial) (推奨) がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの 8x8 の追加

Microsoft Entra ID への 8x8 の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に 8x8 を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**8x8**」と入力します。
4. 結果パネルで **[8x8]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### 8x8 に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、8x8 に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと 8x8 での関連ユーザーとの間にリンク関係を確立する必要があります。

8x8 に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **8x8 Admin Console での 8x8 の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **8x8 テストユーザーを作成し** - 8x8でのB.Simonの対応となるユーザーを作成し、Microsoft Entraのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**8x8**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`https://sso.8x8.com/saml2` という URL を入力します。

    b。 **[応答 URL]** ボックスに、URL として「`https://sso.8x8.com/saml2`」と入力します。
6. SP 開始モードでアプリケーションを構成するには、次の手順を実行します。

    **[サインオン URL]** ボックスに、URL として「`https://sso.8x8.com`」と入力します。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。 「 **8x8 SSO の構成」** セクションの記事で後述する証明書を使用します。

    [Image: 証明書のダウンロードのリンク]
8. **8x8 のセットアップ** セクションで、URL をコピーして、後で記事内でログイン URL、識別子、ログアウト URL として使用します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### 8x8 Admin Console で 8x8 の SSO を構成する

1. 別の Web ブラウザー ウィンドウで、8x8 の [Admin Console](https://admin.8x8.com/) に管理者としてログインします。
2. ホーム ページで[ **IDENTITY Management]\(ID 管理\**) を選択します。

    [Image: [Identity Management](ID 管理) タイルが強調表示されているスクリーンショット。]
3. **[シングル サインオン (SSO)]** をオンにし、**[Microsoft Entra ID]** を選択します。
4. 3 つの URL と署名証明書を、Microsoft Entra ID の **[SAML によるシングル サインオンの設定]** ページから、8x8 Admin Console の **[Microsoft Entra SAML Settings] (Microsoft Entra SAML 設定)** セクションにコピーします。

    a. Microsoft Entra 管理センターの**ログイン URL** をコピーし、**[IDP Login URL] (IDP ログイン URL)** フィールドに貼り付けます。

    b。 Microsoft Entra 管理センターから **Microsoft Entra 識別子**をコピーし、**[IDP Issuer URL/URN] (IDP 発行者 URL/URN)** フィールドに貼り付けます。

    c. Microsoft Entra 管理センターから **ログアウト URL** をコピーし、**[IDP Logout URL] (IDP ログアウト URL)** (省略可能) フィールドに貼り付けます。

    d. Microsoft Entra 管理センターから**Base64 形式の証明書**をダウンロードし、**[証明書]** フィールドにアップロードします。

    e. **保存** を選択します。

#### 8x8 のテスト ユーザーの作成

8x8 Admin Console で Britta Simon というユーザーを作成します。 シングル サインオンを利用するためには、事前に 8x8 Admin Console でユーザーを作成しておく必要があります。

### SSO のテスト

このセクションでは、8x8 ログイン ページから始める SP 起動フロー、または Microsoft マイ アプリから始める IDP 起動フローのいずれかを使用して、Microsoft Entra シングル サインオン構成をテストします。

##### SP Initiated:

- 8x8 の[サインオン URL](https://sso.8x8.com) に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した 8x8 に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [8x8] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した 8x8 に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/a-cloud-guru-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cloud Guru を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/a-cloud-guru-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と A Cloud Guru の間のシングル サインオンを構成する方法について説明します。

この記事では、A Cloud Guru と Microsoft Entra ID を統合する方法について説明します。 A Cloud Guru を Microsoft Entra ID と統合すると、次のことができます。

- A Cloud Guru にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して A Cloud Guru に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- A Cloud Guru でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- A Cloud Guru では、**SP および IDP** Initiated SSO がサポートされます。
- A Cloud Guru では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの A Cloud Guru の追加

A Cloud Guru から Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に A Cloud Guru を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに **[A Cloud Guru]** と入力します。
4. 結果のパネルから **[A Cloud Guru]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### A Cloud Guru 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、A Cloud Guru に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと A Cloud Guru の関連ユーザーとの間にリンク関係を確立する必要があります。

A Cloud Guru で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **A Cloud Guru SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **A Cloud Guru のテストユーザーを作成し**、Microsoft Entra のユーザー表現にリンクされた B. Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[A Cloud Guru]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:auth0:acloudguru:<CLIENT_CONNECTION_NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.acloud.guru/login/callback?connection=<CLIENT_CONNECTION_NAME>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://learn.acloud.guru/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[A Cloud Guru クライアント サポート チーム](mailto:sso@acloud.guru)にご連絡ください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. A Cloud Guru アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID (名前 ID)]** の既定値は **user.userprincipalname** ですが、A Cloud Guru ではこれをユーザーの名前にマップすることが求められます。 そのため、リストから **user.givenname** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
8. その他に、A Cloud Guru アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス (user.emailaddress) |
    | family\_name | ユーザーの名字 |
    | given\_name | User.givenname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[A Cloud Guru のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### A Cloud Guru SSO の構成

**A Cloud Guru** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [A Cloud Guru サポート チーム](mailto:sso@acloud.guru)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### A Cloud Guru のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを A Cloud Guru に作成します。 A Cloud Guru では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 A Cloud Guru にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる A Cloud Guru のサインオン URL にリダイレクトされます。
- A Cloud Guru のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した A Cloud Guru に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [A Cloud Guru] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した A Cloud Guru に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/abbyy-flexicapture-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ABBYY FlexiCapture Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/abbyy-flexicapture-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ABBYY FlexiCapture Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、ABBYY FlexiCapture Cloud と Microsoft Entra ID を統合する方法について説明します。 ABBYY FlexiCapture Cloud を Microsoft Entra ID と統合すると、次のことができるようになります。

- ABBYY FlexiCapture Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが各自の Microsoft Entra アカウントを使用して ABBYY FlexiCapture Cloud に自動的にサインインするように設定できる。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ABBYY FlexiCapture Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ABBYY FlexiCapture Cloud は、**SPおよびIDPによるSSOの開始**をサポートします。
- ABBYY FlexiCapture Cloud では、 **Just In Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの ABBYY FlexiCapture Cloud の追加

Microsoft Entra ID への ABBYY FlexiCapture Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ABBYY FlexiCapture Cloud を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「ABBYY FlexiCapture Cloud**」と入力します。
4. 結果パネルから **ABBYY FlexiCapture Cloud** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ABBYY FlexiCapture Cloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ABBYY FlexiCapture Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと ABBYY FlexiCapture Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

ABBYY FlexiCapture Cloud に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ABBYY FlexiCapture Cloud の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **ABBYY FlexiCapture Cloud のテストユーザーを作成** - ABBYY FlexiCapture Cloud で B.Simon の対応ユーザーを作成し、そのユーザーと Microsoft Entra での B.Simon 表現をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Browse to **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ABBYY FlexiCapture Cloud]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.flexicapture.com/FlexiCapture12/Login/<TENANT_NAME>/AccessToken/Saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.flexicapture.com/FlexiCapture12/Login/<TENANT_NAME>/AccessToken/Saml`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.flexicapture.com/FlexiCapture12/Login/<TENANT_NAME>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、ABBYY FlexiCapture Cloud クライアント サポート チーム](mailto:support@abbyy.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **ABBYY FlexiCapture Cloud のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ABBYY FlexiCapture Cloud の SSO を構成する

**ABBYY FlexiCapture Cloud** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [ABBYY FlexiCapture Cloud サポート チーム](mailto:support@abbyy.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ABBYY FlexiCapture Cloud のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを ABBYY FlexiCapture Cloud に作成します。 ABBYY FlexiCapture Cloud により、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ABBYY FlexiCapture Cloud にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ABBYY FlexiCapture Cloud サインオン URL にリダイレクトされます。
- ABBYY FlexiCapture Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ABBYY FlexiCapture Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで ABBYY FlexiCapture Cloud タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ABBYY FlexiCapture Cloud に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/abintegro-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Abintegro を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/abintegro-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Abintegro の間でシングル サインオンを構成する方法について説明します。

この記事では、Abintegro と Microsoft Entra ID を統合する方法について説明します。 Abintegro と Microsoft Entra ID を統合すると、次のことができます。

- Abintegro にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Abintegro に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Abintegro でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Abintegro では、 **SP** Initiated SSO がサポートされます。
- Abintegro では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Abintegro を追加する

Microsoft Entra ID への Abintegro の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Abintegro を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスしてください。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Abintegro**」と入力します。
4. 結果パネルから Abintegro  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Abintegro の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Abintegro に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Abintegro の関連ユーザーとの間にリンク関係を確立する必要があります。

Abintegro に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Abintegro SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Abintegro のテストユーザーを作成 - Microsoft Entra のユーザー表現とリンクされた、B.Simon に対応するアカウントを Abintegro で作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Abintegro**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.abintegro.com/Shibboleth.sso/Login?entityID=<Issuer>&target=https://www.abintegro.com/secure/`

    手記

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、Abintegro クライアント サポート チーム](mailto:support@abintegro.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Abintegro のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Abintegro SSO の構成

**Abintegro** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Abintegro サポート チーム](mailto:support@abintegro.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Abintegro テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Abintegro に作成します。 Abintegro では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Abintegro にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Abintegro のサインオン URL にリダイレクトされます。
- Abintegro のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Abintegro] タイルを選択すると、このオプションは Abintegro のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/absorblms-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Absorb LMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/absorblms-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Absorb LMS の間のシングル サインオンを構成する方法について説明します。

この記事では、Absorb LMS と Microsoft Entra ID を統合する方法について説明します。 Absorb LMS を Microsoft Entra ID と統合すると、次のことが可能になります。

- Absorb LMS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Absorb LMS に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

Absorb LMS は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Absorb LMS でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Absorb LMS では、 **IDP** によって開始される SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Absorb LMS を追加する

Microsoft Entra ID への Absorb LMS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Absorb LMS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Absorb LMS**」と入力します。
4. 結果パネルから **Absorb LMS** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Absorb LMS 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Absorb LMS に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Absorb LMS での関連ユーザーとの間にリンク関係を確立する必要があります。

Absorb LMS に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Absorb LMS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Absorb LMSテストユーザーを作成 - B.Simonに対応するユーザーをAbsorb LMSで作成し、Microsoft Entra上のユーザーとリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

次の手順に従って、Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Absorb LMS**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On の設定** ] ページで、[ **編集]** ボタンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    **Absorb 5 - UI** を使用している場合は、次の構成を使用します。

    ａ。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.myabsorb.com/account/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.myabsorb.com/account/saml`

    **Absorb 5 - New Learner Experience** を使用している場合は、次の構成を使用します。

    ａ。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.myabsorb.com/api/rest/v2/authentication/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.myabsorb.com/api/rest/v2/authentication/saml`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Absorb LMS クライアント サポート チーム](https://support.absorblms.com/hc/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。

    [Image: 画像]
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Absorb LMS のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Absorb LMS の SSO の構成

1. 新しい Web ブラウザー ウィンドウで、Absorb LMS 企業サイトに管理者としてサインインします。
2. 右上にある **[アカウント** ] ボタンを選択します。
3. [アカウント] ウィンドウで、[ **ポータルの設定]** を選択します。

    [Image: ポータルの [設定] リンク]
4. [ **SSO 設定の管理] タブを** 選択します。

    [Image: [ユーザー] タブ]
5. [ **単一 Sign-On 設定の管理]** ページで、次の操作を行います。

    [Image: シングル サインオンの構成ページを示すスクリーンショット。]

    ａ。 [ **名前** ] ボックスに、Microsoft Entra Marketplace SSO などの名前を入力します。

    b。 **[方法**] として **[SAML**] を選択します。

    c. メモ帳で、ダウンロードした証明書を開きます。 **---BEGIN CERTIFICATE---** および **---END CERTIFICATE---** タグを削除します。 次に、[ **キー** ] ボックスに残りのコンテンツを貼り付けます。

    d. **[モード]** ボックスで、**[Identity Provider Initiated](ID プロバイダー開始)** を選択します。

    e. [ **Id プロパティ** ] ボックスで、Microsoft Entra ID のユーザー識別子として構成した属性を選択します。 たとえば、Microsoft Entra ID で *nameidentifier* が選択されている場合は、[ **ユーザー名**] を選択します。

    f. **署名の種類**として **[Sha256**] を選択します。

    g. [**ログイン URL**] ボックスに、アプリケーションの **[プロパティ**] ページから**ユーザー アクセス URL を**貼り付けます。

    h. [**ログアウト URL**] に、[**サインオンの構成**] ウィンドウからコピーした **Sign-Out URL** の値を貼り付けます。

    一. **[自動的にリダイレクト**] を **[オン]** に切り替えます。
6. **[保存] を選択します。**

    [Image: [Only Allow SSO Login](SSO ログインのみを許可する) の切り替え]

#### Absorb LMS のテスト ユーザーの作成

Microsoft Entra ユーザーが Absorb LMS にサインインするには、そのユーザーを Absorb LMS で設定する必要があります。 Absorb LMS の場合、プロビジョニングは手動で行います。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. Absorb LMS 企業サイトに管理者としてサインインします。
2. [ **ユーザー** ] ウィンドウで、[ **ユーザー**] を選択します。

    [Image: [ユーザー] リンク]
3. [ **ユーザー** ] タブを選択します。

    [Image: 新規作成ドロップダウンリスト]
4. [ **ユーザーの追加** ] ページで、次の操作を行います。

    [Image: [ユーザーの追加] ページ]

    ａ。 **[名]** ボックスに、ユーザーの名 (たとえば、**Britta**) を入力します。

    b。 [ **姓** ] ボックスに、 **姓 (Simon** など) を入力します。

    c. [ **ユーザー名** ] ボックスに、 **Britta Simon** などの完全な名前を入力します。

    d. [ **パスワード** ] ボックスに、ユーザー パスワードを入力します。

    e. [ **パスワードの確認** ] ボックスに、パスワードを再入力します。

    f. **[アクティブ]** を **[アクティブ]** に切り替えます。
5. **[保存] を選択します。**

    [Image: [Only Allow SSO Login](SSO ログインのみを許可する) の切り替え]

    注

    既定では、ユーザー プロビジョニングは SSO では有効になっていません。 お客様がこの機能を有効にしたい場合は、 [この](https://support.absorblms.com/hc/en-us/articles/360014083294-Incoming-SAML-2-0-SSO-Account-Provisioning) ドキュメントで説明されているように設定する必要があります。 また、ユーザー プロビジョニングは ACS URL が  の `https://company.myabsorb.com/api/rest/v2/authentication/saml` でのみ使用できることに注意してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Absorb LMS に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで Absorb LMS タイルを選択すると、SSO を設定した Absorb LMS に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/abstract-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Abstract を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/abstract-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Abstract 間のシングル サインオンを構成する方法について説明します。

この記事では、Abstract と Microsoft Entra ID を統合する方法について説明します。 Abstract を Microsoft Entra ID と統合すると、次のことが可能になります。

- Abstract にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Abstract に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Abstract でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Abstract では、**SP および IDP による SSO** がサポートされます。

### ギャラリーからの Abstract の追加

Microsoft Entra ID への Abstract の統合を構成するには、管理対象の SaaS アプリの一覧にギャラリーから Abstract を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を開く。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Abstract**」と入力します。
4. 結果パネルから **[抽象]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Abstract に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Abstract に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Abstract の関連ユーザーとの間にリンク関係を確立する必要があります。

Abstract で Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Abstract SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Abstract テスト ユーザーの作成** - Abstract で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Abstract** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.abstract.com/signin`
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Abstract SSO の構成

Abstract で SSO を構成する必要がある場合は、必ず `App Federation Metadata Url` と `Azure AD Identifier`を取得してください。

その情報は、[SAML を使用 **した単一 Sign-On の設定** ] ページにあります。

- `App Federation Metadata Url`は SAML **署名証明書**セクションにあります。
- `Azure AD Identifier`は「**概要設定**」セクションにあります。

これで、Abstract で SSO を構成する準備ができました。

注

Abstract の SSO 設定にアクセスするには、組織の管理者アカウントで認証する必要があります。

1. 抽象 Web アプリを開きます。
2. 左側のバーの **[アクセス許可** ] ページに移動します。
3. [ **SSO の構成]** セクションで、 **メタデータ URL** と **エンティティ ID を**入力します。
4. 手動による例外がある場合は、それを入力します。 手動例外セクションに記載されている電子メールは SSO をバイパスし、電子メールとパスワードでログインできます。
5. [ **変更の保存] を選択します**。

注

手動による例外の一覧には、プライマリ メール アドレスを使用する必要があります。 一覧表示する電子メールがユーザーのセカンダリ 電子メールである場合、SSO のアクティブ化は失敗します。 その場合は、失敗したアカウントのプライマリ メール アドレスを含むエラー メッセージが表示されます。 お客様がそのユーザーを知っていると確認した後に、そのプライマリ メール アドレスを手動による例外一覧に追加します。

#### Abstract テスト ユーザーの作成

Abstract で SSO をテストするには:

1. 抽象 Web アプリを開きます。
2. 左側のバーの **[アクセス許可** ] ページに移動します。
3. [ **自分のアカウントでテスト] を選択します**。 テストに失敗した場合は、抽象サポート チームにお問い合わせください。

注

Abstract の SSO 設定にアクセスするには、組織の管理者アカウントで認証する必要があります。 この組織の管理者アカウントを Abstract に割り当てる必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる抽象サインオン URL にリダイレクトされます。
- Abstract のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Abstract に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [抽象] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Abstract に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/academy-attendance-tutorial"} -->
## Microsoft Entra ID で Academy Attendance for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/academy-attendance-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Academy Attendance の間にシングル サインオンを構成する方法について説明します。

この記事では、Academy Attendance と Microsoft Entra ID を統合する方法について説明します。 Academy Attendance と Microsoft Entra ID を統合すると、次のことができます。

- Academy Attendance にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Academy Attendance に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Academy Attendance でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Academy Attendance では、**SP** によって開始される SSO がサポートされています
- Academy Attendance では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからアカデミーの出席を追加する

Microsoft Entra ID への Academy Attendance の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Academy Attendance を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Academy Attendance**」と入力します。
4. 結果のパネルから **[Academy Attendance]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Academy Attendance 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Academy Attendance に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Academy Attendance の関連ユーザーとの間にリンク関係を確立する必要があります。

Academy Attendance に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Academy Attendance SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Academy Attendanceのテストユーザーを作成** - Academy AttendanceでB.Simonに対応するユーザーの作成を行い、Microsoft Entraのユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**アカデミー出席管理**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **SAML によるシングル サインオンのセットアップ** ページで、**基本的な SAML 構成** の編集アイコンを選択して設定を変更します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、次のフィールドの値を入力します。

    1. [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.aattendance.com/sso/saml2/login?idp=<IDP_NAME>`
    2. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<SUBDOMAIN>.aattendance.com/sso/saml2/metadata?idp=<IDP_NAME>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Academy Attendance クライアント サポート チーム](mailto:support@yournextconcepts.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Academy Attendance アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Academy Attendance アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ロール | user.assignedroles |

    注

    Academy Attendance では、 **[Lecturer](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/講師)** と **[Student](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/学生)** の 2 つのユーザー ロールがサポートされます。 ユーザーに適切なロールを割り当てることができるように、Microsoft Entra ID でこれらのロールを設定します。 Microsoft Entra ID でカスタム役割を作成する方法を説明している[こちらの](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)ドキュメントを参照してください。
8. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Academy Attendance のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Academy Attendance SSO の構成

**Academy Attendance** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Academy Attendance サポート チーム](mailto:support@yournextconcepts.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Academy Attendance のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Academy Attendance に作成します。 Academy Attendance では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Academy Attendance にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Academy Attendance のサインオン URL にリダイレクトされます。
- Academy Attendance のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Academy Attendance] タイルを選択すると、Academy Attendance のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/acadia-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Acadia を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/acadia-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Acadia の間のシングル サインオンを構成する方法について説明します。

この記事では、Acadia と Microsoft Entra ID を統合する方法について説明します。 Acadia を Microsoft Entra ID と統合すると、次のことが可能になります。

- Acadia へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Acadia に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Acadia でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Acadia では、**SPおよびIDPによるSSOの開始**がサポートされます。
- Acadia では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Acadia を追加する

Microsoft Entra ID への Acadia の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Acadia を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Acadia**」と入力します。
4. 結果パネルから **Acadia** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Acadia に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Acadia に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Acadia の関連ユーザーとの間にリンク関係を確立する必要があります。

Acadia に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Acadia SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Acadia テスト ユーザーの作成** - Acadia で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Acadia**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.acadia.sysalli.com/shibboleth`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.acadia.sysalli.com/Shibboleth.sso/SAML2/POST`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER>.acadia.sysalli.com/Shibboleth.sso/Login`

    注

    手順 4 と 5 の値は、Acadia チームによってメタデータ ファイルに提供されます。これは、[**基本的な SAML 構成**] セクションで [**メタデータ ファイルのアップロード**] を選択してインポートできます。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照して、メタデータ値が正しいことを確認することもできます。 指定された値が正しくない場合は、 [Acadia クライアント サポート チーム](mailto:support@systemsalliance.com) にお問い合わせください。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Acadia のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Acadia の SSO の構成

**Acadia** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML**、**アプリのフェデレーション メタデータ URL**、およびアプリケーション構成からコピーした適切な URL を [Acadia サポート チーム](mailto:support@systemsalliance.com)に送信する必要があります。 この設定が構成され、SAML SSO 接続が両側で正しく行われます。

#### Acadia のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Acadia に作成します。 Acadia では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Acadia にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Acadia のサインオン URL にリダイレクトされます。
- Acadia のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Acadia に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Acadia] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Acadia に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/accenture-academy-tutorial"} -->
## Microsoft Entra ID でシングルサインオン用のAccenture Academyを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/accenture-academy-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Accenture Academy 間にシングル サインオンを構成する方法について説明します。

この記事では、Accenture Academy と Microsoft Entra ID を統合する方法について説明します。 Accenture Academy と Microsoft Entra ID を統合すると、次のことができます。

- Accenture Academy にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Accenture Academy に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Accenture Academy でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Accenture Academy では、**SP および IDP による SSO の開始** がサポートされています。
- Accenture Academy では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Accenture Academy の追加

Microsoft Entra ID への Accenture Academy の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Accenture Academy を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Accenture Academy**」と入力します。
4. 結果のパネルから **[Accenture Academy]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Accenture Academy 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Accenture Academy に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Accenture Academy の関連ユーザーとの間にリンク関係を確立する必要があります。

Accenture Academy に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Accenture Academy SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Accenture Academy のテスト ユーザーの作成** - Accenture Academy で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Enterpriseアプリケーション]**&gt;**[Accenture Academy]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    a. **[識別子]** ボックスに、`https://www.accentureacademy.com/a/integration/saml_sso/<Customer ID>/` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://www.accentureacademy.com/a/integration/saml_sso/<Customer ID>/acs/` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://www.accentureacademy.com/a/integration/saml_sso/<Customer ID>/request_idp_auth/` という形式で URL を入力します。

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Accenture Academy クライアント サポート チーム](mailto:support@accentureacademy.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Accenture Academy のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Accenture Academy SSO の構成

**Accenture Academy** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Accenture Academy サポート チーム](mailto:support@accentureacademy.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Accenture Academy のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Accenture Academy に作成します。 Accenture Academy では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Accenture Academy にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Accenture Academy のサインオン URL にリダイレクトされます。
- Accenture Academy のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Accenture Academy に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Accenture Academy タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Accenture Academy に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/accredible-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Accredible を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/accredible-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Accredible の間にシングル サインオンを構成する方法について説明します。

この記事では、Accredible と Microsoft Entra ID を統合する方法について説明します。 Accredible を Microsoft Entra ID と統合すると、次の利点があります。

- Accredible にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで Accredible に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Accredible でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Accredible では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの Accredible の追加

Microsoft Entra ID への Accredible の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Accredible を追加する必要があります。

**ギャラリーから Accredible を追加するには、次の手順を実行します。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションに「**Accredible」**と入力し、結果パネルで **Accredible** を選択し、[**追加]** ボタンを選択してアプリケーションを追加します。

    [Image: Accredible が結果一覧に表示]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Accredible で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Accredible の関連ユーザー間にリンク関係を確立する必要があります。

Accredible に対する Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Accredible シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Accredible テスト ユーザーの作成** - Microsoft Entra ユーザーの表示にリンクされた Britta Simon の対応者を Accredible に作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Accredible で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Accredible** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: Accredible ドメインとURLのシングルサインオン情報]

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    ```http
    https://api.accredible.com/sp/admin/accredible
    https://api.accredible.com/sp/user/accredible
    ```

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.accredible.com/v1/saml/admin/<Unique id>/consume`

    注

    応答 URL は、実際の値ではありません。 ユーザーのロールに従って、対応する識別子の値を使います。 各顧客は、それぞれの ID に応じた一意の応答 URL を持っています。 これらの値を取得するには、 [Accredible サポート チーム](mailto:support@accredible.com) に問い合わせてください。
6. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Accredible のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    a. ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Accredible のシングル サインオンの構成

**Accredible** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Accredible サポート チーム](mailto:support@accredible.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Accredible のテスト ユーザーの作成

このセクションでは、Accredible で Britta Simon というユーザーを作成します。 ユーザーの電子メール ID を [Accredible サポート チーム](mailto:support@accredible.com)に送信し、電子メールを確認し、招待メールを送信して、accredible プラットフォームでユーザーを追加できるようにする必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Accredible] タイルを選択すると、SSO を設定した Accredible に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/achieve3000-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Achieve3000 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/achieve3000-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Achieve3000 間のシングル サインオンを構成する方法について説明します。

この記事では、Achieve3000 と Microsoft Entra ID を統合する方法について説明します。 Achieve3000 を Microsoft Entra ID と統合すると、次のことが可能になります。

- Achieve3000 へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Achieve3000 に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Achieve3000 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Achieve3000 では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Achieve3000 を追加する

Microsoft Entra ID への Achieve3000 の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Achieve3000 を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Achieve3000**」と入力します。
4. 結果パネルから **Achieve3000** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Achieve3000 に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Achieve3000 に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと、Achieve3000 での関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Achieve3000 と一緒に構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Achieve3000 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Achieve3000 テスト ユーザーの作成** - Achieve3000でB.Simonの対応ユーザーを作成し、そのユーザーをMicrosoft EntraでのB.Simonの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Achieve3000**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `achieve3000-saml`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://saml.achieve3000.com/district/<District Identifier>`

    注

    Sign-On URL 値は実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、Achieve3000 クライアント サポート チームにお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Achieve3000 アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
7. その他に、Achieve3000 アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 学生ID | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Achieve3000 のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Achieve3000 の SSO の構成

**Achieve3000** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を Achieve3000 サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Achieve3000 のテスト ユーザーの作成

このセクションでは、Achieve3000 で B.Simon というユーザーを作成します。 Achieve3000 サポート チームと協力して、Achieve3000 プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Achieve3000 サインオン URL にリダイレクトされます。
- Achieve3000 のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Achieve3000] タイルを選択すると、このオプションは Achieve3000 のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/aclp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ACLP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aclp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ACLP 間にシングル サインオンを構成する方法について説明します。

この記事では、ACLP と Microsoft Entra ID を統合する方法について説明します。 ACLP を Microsoft Entra ID と統合すると、次のことができます:

- Microsoft Entra ID における ACLP へのアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して ACLP に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

ACLP と Microsoft Entra の統合を構成するには、次の項目が必要です:

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ACLP でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- ACLP では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ACLP を追加

Microsoft Entra ID への ACLP の統合を構成するには、ACLP をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;と**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ACLP**」と入力します。
4. 結果パネルから **ACLP** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ACLP 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、ACLP に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ACLP の関連ユーザーとの間にリンク関係を確立する必要があります。

ACLP に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ACLP SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ACLP テスト ユーザーの作成** - ACLP で B.Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ACLP**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://access.sans.org/go/<COMPANYNAME>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [ACLP クライアント サポート チーム](mailto:mrichards@sans.org) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ACLP SSO の構成

**ACLP** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[ACLP サポート チーム](mailto:mrichards@sans.org)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ACLP テスト ユーザーの作成

このセクションでは、ACLP で Britta Simon というユーザーを作成します。 [ACLP サポート チーム](mailto:mrichards@sans.org)と協力して、ACLP プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ACLP サインオン URL にリダイレクトされます。
- ACLP のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ACLP] タイルを選択すると、このオプションは ACLP サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/acoustic-connect-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Acoustic Connect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/acoustic-connect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Acoustic Connect 間のシングル サインオンを構成する方法について説明します。

この記事では、Acoustic Connect と Microsoft Entra ID を統合する方法について説明します。 Acoustic Connect は、人々の心に響き、忠実なフォローを構築し、収益を上げるマーケティング キャンペーンを作成するのに役立つプラットフォームです。 Acoustic Connect を Microsoft Entra ID と統合すると、次のことができます。

- Acoustic Connect にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Acoustic Connect に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Acoustic Connect 用の Microsoft Entra シングル サインオンを構成してテストします。 Acoustic Connect では、 **SP** と **IDP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングの両方がサポートされます。

### [前提条件]

Microsoft Entra ID を Acoustic Connect と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Acoustic Connect でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Acoustic Connect アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Acoustic Connect を追加する

Microsoft Entra アプリケーション ギャラリーから Acoustic Connect を追加して、Acoustic Connect でのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Acoustic Connect]**&gt;**シングル サインオン** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<Acoustic_ID>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.goacoustic.com/sso/saml2/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、URL を入力します。 `https://login.goacoustic.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Acoustic Connect サポート チーム](mailto:support@acoustic.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Acoustic Connect のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成を適切な U R L にコピーする方法を示しています。]

### Acoustic Connect SSO を構成する

**Acoustic Connect** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Acoustic Connect サポート チーム](mailto:support@acoustic.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Taskize Connect のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Acoustic Connect で作成します。 Acoustic Connect では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Acoustic Connect にユーザーがまだ存在していない場合、一般に認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Acoustic Connect のサインオン URL にリダイレクトされます。
- Acoustic Connect のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Acoustic Connect に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Acoustic Connect] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Acoustic Connect に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/acquireio-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AcquireIO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/acquireio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AcquireIO の間のシングル サインオンを構成する方法について説明します。

この記事では、AcquireIO と Microsoft Entra ID を統合する方法について説明します。 AcquireIO を Microsoft Entra ID と統合すると、次のことが可能になります。

- AcquireIO へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して AcquireIO に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AcquireIO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AcquireIO では、 **IDP** Initiated SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの AcquireIO の追加

Microsoft Entra ID への AcquireIO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AcquireIO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「AcquireIO**」と入力します。
4. 結果のパネルから **[AcquireIO** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AcquireIO に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、AcquireIO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと AcquireIO の関連ユーザーの間にリンク関係を確立する必要があります。

AcquireIO に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AcquireIO SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AcquireIO テスト ユーザーの作成** - AcquireIO で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AcquireIO**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.acquire.io/ad/<acquire_account_uid>`

    Note

    これは実際の値ではありません。 実際の応答 URL を取得します。これについては、この記事の「 **AcquireIO の構成」** セクションで後述します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **AcquireIO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AcquireIO SSO の構成

1. 別の Web ブラウザー ウィンドウで、AcquireIO 企業サイトに管理者としてサインインします。
2. メニューの左側にある [ **App Store**] を選択します。

    [Image: App Store が強調表示されているスクリーンショット。]
3. **Active Directory** までスクロールし、[**インストール**] を選択します。
4. [Active Directory] ポップアップで、次の手順に従います。

    [Image: Active Directory 画面を示すスクリーンショット。]

    a. [**コピー]** を選択してインスタンスの応答 URL をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。

    b。 [ **ログイン URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    c. Base64 でエンコードされた証明書をメモ帳で開き、その内容をコピーして **[X.509 証明書** ] テキスト ボックスに貼り付けます。

    d. [ **今すぐ接続]** を選択します。

#### AcquireIO のテスト ユーザーの作成

Microsoft Entra ユーザーが AcquireIO にサインインできるようにするには、そのユーザーを AcquireIO にプロビジョニングする必要があります。 AcquireIO では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. 別の Web ブラウザー ウィンドウで、管理者として AcquireIO にサインインします。
2. メニューの左側から[ **プロファイル** ]を選択し、[ **プロファイルの追加]**に移動します。

    [Image: 画面の左側にあるメニューの [プロファイル] と [プロファイルの追加] オプションが強調表示されているスクリーンショット。]
3. [ **顧客の追加** ] ポップアップで、次の手順を実行します。

    [Image: AcquireIO 構成。]

    a. **[名前**] テキスト ボックスに、**B.simon** などのユーザーの名前を入力します。

    b。 [ **電子メール** ] テキスト ボックスに、ユーザーの電子メール ( **B.simon@contoso.com**など) を入力します。

    c. [ **送信] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AcquireIO に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [AcquireIO] タイルを選択すると、SSO を設定した AcquireIO に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/acronis-cyber-protect-cloud-tutorial"} -->
## Microsoft Entra ID を使用してシングル Sign-On 用にアクロニス サイバープロテクト クラウドを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/acronis-cyber-protect-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID とアクロニス サイバープロテクト クラウドの間でシングル サインオンを構成する方法について説明します。

この記事では、アクロニス サイバープロテクト クラウドと Microsoft Entra ID を統合する方法について説明します。 アクロニス サイバープロテクト クラウドと Microsoft Entra ID を統合すると、次のことができます。

- アクロニス サイバープロテクト クラウドにアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して自動的にアクロニス サイバープロテクト クラウドにサインオンできるようにします。
- SP によって開始され、IDP によって開始される SAML 単一ログアウト (SLO) プロセスを制御します。
- 1 つの中央の場所でアカウントを管理します。
- アクロニスの2FAチャレンジを回避する方法。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- アクロニス サイバープロテクト クラウド サブスクリプション。 [無料の 30 日間試用版をサブスクライブ](https://www.acronis.com/products/cloud/trial/)できます。
- アクロニス サイバープロテクト クラウド **パートナー テナント**。
- 会社の管理者ロールを持つアクロニス サイバープロテクト クラウド ユーザー アカウント。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- アクロニス サイバープロテクト クラウドでは、 **SP Initiated** SSO と **IDP Initiated SSO の両方がサポートされます** 。

### Microsoft Entra ID とアクロニスの統合を構成してテストする

#### Microsoft Entra ID とアクロニスの統合をアクティブ化する

まず、アクロニス パートナー テナントの Microsoft Entra ID とアクロニスの統合をアクティブにする必要があります。

1. ブラウザー タブを開きます。
2. パートナー管理者として、アクロニス管理ポータルにサインインします。
3. メイン メニューから [ **INTEGRATIONS** ] を選択します。
4. **Microsoft Entra ID** カタログ カードを見つけます。
5. **Microsoft Entra ID** カタログ カードにカーソルを合わせ、[**構成**] をクリックします。
6. Microsoft Entra ID ドメインを入力します。
7. **[次へ]** をクリックします。 このブラウザー タブは閉じないでください。

#### ギャラリーからアクロニス サイバープロテクト クラウドを追加する

Microsoft Entra ID へのアクロニス サイバープロテクト クラウドの統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に、アクロニス サイバープロテクト クラウドを追加する必要があります。

1. 新しいブラウザー タブを開きます。
2. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
3. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
4. **[ギャラリーから追加**] セクションで、検索ボックス**に「アクロニス サイバープロテクト クラウド**」と入力します。
5. 結果パネルから **Acronis Cyber Protect Cloud** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

1. アプリケーションを開きます。
2. メニューから [ **シングル サインオン** ] を選択し、 **SAML** シングル サインオン方法を選択します。
3. [**基本的な SAML 構成**] セクションの [**編集]** をクリックします。
4. [アクロニスサイバープロテクトクラウド]ブラウザタブに戻り、[ **識別子(エンティティID)]** フィールドのコピーアイコンをクリックして値をコピーします。
5. Microsoft Entra 管理センターのブラウザー タブに切り替え、コピーした値を **[識別子 (エンティティ ID)]** フィールドに貼り付けます。
6. もう一度Acronis Cyber Protect Cloudブラウザータブに戻り、 **応答 URL (Assertion Consumer Service URL)** フィールドのコピーアイコンをクリックして値をコピーします。
7. Microsoft Entra 管理センターのブラウザー タブに切り替え、コピーした値を **[応答 URL] (Assertion Consumer Service URL)** フィールドに貼り付けます。
8. [保存] をクリックします。
9. Microsoft Entra 管理センターのブラウザー タブで、[SAML 証明書] まで下にスクロールし、[ **ダウンロード** ] をクリックして **フェデレーション メタデータ XML** ファイルをダウンロードします。
10. [アクロニスサイバープロテクトクラウド]ブラウザタブに切り替えます。 **[アプリのフェデレーション メタデータ**] で、[ **ファイルの参照...** ] をクリックして、前の手順でダウンロードしたフェデレーション メタデータ XML ファイルをアップロードします。
11. **有効化** をクリックします。

### アクロニス サイバープロテクト クラウドの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、アクロニス サイバープロテクト クラウドに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーとアクロニス サイバープロテクト クラウドの関連ユーザーとの間にリンク関係を確立する必要があります。

アクロニス サイバープロテクト クラウドに対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Acronis Cyber Protect Cloud のテストユーザーを作成して有効にします** - アクロニス サイバー プロテクト クラウドで B.Simon に対応するユーザーを作成し、それを Microsoft Entra ID のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Acronis Cyber Protect Cloud**&gt;**シングルサインオン**を参照する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:cyber:protect:saml:<YOUR_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_DOMAIN>/api/2/saml/callback`

    c. [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_DOMAIN>/api/2/saml/logout`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、ログアウト URL でこれらの値を更新します。 これらの値を取得するには [、アクロニス サイバープロテクト クラウド サポート チーム](mailto:mspsupport@acronis.com) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. [ **アクロニス サイバープロテクト クラウドのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### アクロニスサイバープロテクトクラウドのテストユーザーを作成して有効にする

1. アクロニス管理ポータルで、B.Simon というユーザーを作成し、Microsoft Entra ID で作成したユーザーとマップします。 新しいユーザーにライセンス認証メールが送信されます。
2. メールを開き、ユーザーをアクティブにします。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定したアクロニス サイバープロテクト クラウドに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [アクロニス サイバープロテクト クラウド] タイルを選択すると、SSO を設定したアクロニス サイバープロテクト クラウドに自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/active-and-thriving-tutorial"} -->
## Microsoft Entra ID を使用してシングルサインオンのためにActive and Thrivingを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/active-and-thriving-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Active and Thriving の間でシングル サインオンを構成する方法について説明します。

この記事では、Active and Thriving と Microsoft Entra ID を統合する方法について説明します。 Active and Thriving と Microsoft Entra ID を統合すると、次のことができます。

- Active and Thriving にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Active and Thriving に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Active and Thriving でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Active and Thriving では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Active and Thriving の追加

Microsoft Entra ID への Active and Thriving の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Active and Thriving を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Active and Thriving**」と入力します。
4. 結果のパネルから **[Active and Thriving]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Active and Thriving 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Active and Thriving に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するために、Microsoft Entra ユーザーと Active and Thriving の関連ユーザーの間で、リンク関係を確立する必要があります。

Active and Thriving に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Active and Thriving の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Active and Thriving のテスト ユーザーの作成** - Active and Thriving で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Active and Thriving]**&gt;**[シングル サインオン]** を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.activeandthriving.com.au/saml2/aad/login`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Active and Thriving の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Active and Thriving の SSO の構成

**Active and Thriving** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Active and Thriving サポート チーム](mailto:support@activeandthriving.com.au)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Active and Thriving のテスト ユーザーの作成

このセクションでは、Active and Thriving で Britta Simon というユーザーを作成します。 [Active and Thriving サポート チーム](mailto:support@activeandthriving.com.au)と連携して、Active and Thriving プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できるアクティブおよびスライシング サインオン URL にリダイレクトされます。
- Active and Thriving のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Active and Thriving に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Active and Thriving] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Active and Thriving に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/acunetix-360-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Acunetix 360 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/acunetix-360-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-18
- Summary: Microsoft Entra IDから Acunetix 360 にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Acunetix 360 とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成された Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーおよびグループを[Acunetix 360](https://www.acunetix.com/) に自動的にプロビジョニングおよび削除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Acunetix 360 でユーザーを作成する。
- アクセスが不要になったら、Acunetix 360 のユーザーを削除します。
- Microsoft Entra IDと Acunetix 360 の間でユーザー属性の同期を維持します。
- Acunetix 360 でグループとグループ メンバーシップをプロビジョニングする
- Acunetix 360 に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/acunetix-360-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Acunetix 360 の管理者アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra ID と Acunetix 360 の間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Acunetix 360 を構成する

1. [Acunetix 360 管理コンソール](https://online.acunetix360.com/)にログインします。
2. プロファイル ロゴを選択し **、[API 設定]** に移動します。
3. **現在のパスワード**を入力し、[**送信]** を選択します。
4. **トークン**をコピーして保存します。この値は、Acunetix 360 アプリケーションの [プロビジョニング] タブの [**シークレット トークン**] フィールドに入力します。
    注

    **トークンをリセットするには、[API トークン**のリセット] を選択します。
5. `https://online.acunetix360.com/scim/v2`は、Acunetix 360 アプリケーションの [プロビジョニング] タブの [**テナント URL**] フィールドに入力します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Acunetix 360 を追加する

Microsoft Entra アプリケーション ギャラリーから Acunetix 360 を追加して、Acunetix 360 へのプロビジョニングの管理を開始します。 SSO のために Acunetix 360 を既に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Acunetix 360 への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Acunetix 360 の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Acunetix 360]** を選択します。

    [Image: アプリケーション リストの Acunetix 360 リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Acunetix 360 テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Acunetix 360 に接続できることを確認します。 接続に失敗した場合は、Acunetix 360 アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Acunetix 360 に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Acunetix 360 のユーザー アカウントの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Acunetix 360 API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Acunetix 360 で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
12. グループを選択 **します**。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Acunetix 360 に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Acunetix 360 のグループの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Acunetix 360 で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/acunetix-360-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Acunetix 360 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/acunetix-360-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Acunetix 360 の間のシングル サインオンを構成する方法について説明します。

この記事では、Acunetix 360 と Microsoft Entra ID を統合する方法について説明します。 Acunetix 360 を Microsoft Entra ID と統合すると、次のことが可能になります。

- Acunetix 360 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Acunetix 360 に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Acunetix 360 は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Acunetix 360 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Acunetix 360 は、**SP および IDP による SSO** をサポートしています。
- Acunetix 360 では、 **Just In Time** ユーザー プロビジョニングがサポートされています。
- Acunetix 360 では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/acunetix-360-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Acunetix 360 の追加

Microsoft Entra ID への Acunetix 360 の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Acunetix 360 を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Acunetix 360**」と入力します。
4. 結果のパネルから **[Acunetix 360]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Acunetix 360 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Acunetix 360 に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Acunetix 360 の関連ユーザーとの間にリンク関係を確立する必要があります。

Acunetix 360 に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Acunetix 360 の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Acunetix 360 に B.Simon のテストユーザーを作成する** - Microsoft Entra における B.Simon を表すユーザーを Acunetix 360 にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Acunetix 360**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://online.acunetix360.com/account/assertionconsumerservice/?spId=<SPID>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://online.acunetix360.com/account/ssosignin`

    注

    値は実際の値ではありません。 応答 URL の値は、実際の応答 URL で更新します。 この値を取得するには、[Acunetix 360 クライアント サポート チーム](mailto:support@acunetix.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Acunetix 360 アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Acunetix 360 ではこれをユーザーのメール アドレスにマップすることが求められます。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
8. その他に、Acunetix 360 アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | user.givenName |
    | LastName | ユーザーの姓 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Acunetix 360 のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Acunetix 360 の SSO の構成

**Acunetix 360** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Acunetix 360 サポート チーム](mailto:support@acunetix.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Acunetix 360 のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Acunetix 360 に作成します。 Acunetix 360 では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Acunetix 360 にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Acunetix 360 のサインオン URL にリダイレクトされます。
- Acunetix 360 のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Acunetix 360 に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Acunetix 360] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Acunetix 360 に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adaptive-shield-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用のアダプティブ シールドを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adaptive-shield-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Adaptive Shield の間のシングル サインオンを構成する方法について説明します。

この記事では、アダプティブ シールドと Microsoft Entra ID を統合する方法について説明します。 Adaptive Shield を Microsoft Entra ID と統合すると、次のことが可能になります。

- Adaptive Shield にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Adaptive Shield に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Adaptive Shield でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Adaptive Shield では、**SP** によって開始される SSO がサポートされます。
- Adaptive Shield では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Adaptive Shield の追加

Microsoft Entra ID への Adaptive Shield の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Adaptive Shield を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Adaptive Shield**」と入力します。
4. 結果ウィンドウで **[Adaptive Shield]** を選択し、アプリケーションを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Adaptive Shield に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Adaptive Shield に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Adaptive Shield の関連ユーザーとの間にリンク関係を確立する必要があります。

Adaptive Shield に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Adaptive Shield SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Adaptive Shield のテストユーザーを作成** - Adaptive Shield 内で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上の B.Simon にリンクしてください。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Adaptive Shield**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://dashboard.adaptive-shield.com/api/sso/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://dashboard.adaptive-shield.com/api/sso/saml`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://dashboard.adaptive-shield.com`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Adaptive Shield のセット アップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Adaptive Shield SSO の構成

**Adaptive Shield** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [Adaptive Shield サポート チーム](mailto:support@adaptive-shield.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Adaptive Shield のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Adaptive Shield に作成します。 Adaptive Shield では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Adaptive Shield にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Adaptive Shield のサインオン URL にリダイレクトされます。
- Adaptive Shield のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [アダプティブ シールド] タイルを選択すると、このオプションは Adaptive Shield のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adaptivesuite-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Adaptive Insights を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adaptivesuite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-09
- Summary: Microsoft Entra ID と Adaptive Insights の間のシングル サインオンを構成する方法について説明します。

この記事では、Adaptive Insights と Microsoft Entra ID を統合する方法について説明します。 Adaptive Insights を Microsoft Entra ID と統合すると、次のことが可能になります。

- Adaptive Insights にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Adaptive Insights に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Adaptive Insights でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Adaptive Insights では、**IDP** によって開始される SSO がサポートされます

### ギャラリーからの Adaptive Insights の追加

Microsoft Entra ID への Adaptive Insights の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Adaptive Insights を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Adaptive Insights**」と入力します。
4. 結果ウィンドウで **[Adaptive Insights]** を選択し、アプリケーションを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Adaptive Insights 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Adaptive Insights に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Adaptive Insights の関連ユーザーとの間にリンク関係を確立する必要があります。

Adaptive Insights に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Adaptive Insights の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Adaptive Insights のテスト ユーザーを作成** - Adaptive Insights で B.Simon に対応するユーザーを設定し、それを Microsoft Entra のユーザーとリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Adaptive Insights** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.adaptiveinsights.com:443/samlsso/<unique-id>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.adaptiveinsights.com:443/samlsso/<unique-id>`

    注

    [識別子 (エンティティ ID)] と [応答 URL] の値は、Adaptive Insights の **[SAML SSO 設定]** ページから取得できます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Adaptive Insights のセット アップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

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

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Adaptive Insights へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Adaptive Insights** を参照します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

#### Adaptive Insights SSO の構成

1. 別の Web ブラウザー ウィンドウで、Adaptive Insights 企業サイトに管理者としてサインインします。
2. **[Administration]** に移動します。
3. [ **ユーザーとロール]** セクションで、[ **SAML SSO 設定]** を選択します。
4. **[SAML SSO 設定]** ページで、次の手順を実行します。

    [Image: SAML SSO 設定]

    a. **[ID プロバイダー名]** テキスト ボックスに、構成の名前を入力します。

    b。 **[ID プロバイダーのエンティティ ID]** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    c. **[ID プロバイダーの SSO URL]** テキストボックスに、**ログイン URL** の値を貼り付けます。

    d. **ログアウト URL** の値を **[カスタム ログアウト URL]** テキストボックスに貼り付けます。

    e. ダウンロードした証明書をアップロードするには、[ **ファイルの選択**] を選択します。

    f. 次のように選択します。

    - **[SAML ユーザー ID]** では、**[ユーザーの Adaptive Insights ユーザー名]** を選択します。
    - **[SAML ユーザー ID の場所]** では、**[サブジェクトの NameID 内のユーザー ID]** を選択します。
    - **[SAML NameID 形式]** では、**[メール アドレス]** を選択します。
    - **[SAML を有効にする]** では、 **[SAML SSO を許可して Adaptive Insights に直接ログインする]** を選択します。

    g. **Adaptive Insights の SSO URL** をコピーし、**[基本的な SAML 構成]** セクションの **[識別子 (エンティティ ID)]** と **[応答 URL]** のテキストボックスに貼り付けます。

    h. **保存** を選択します。

#### Adaptive Insights のテスト ユーザーの作成

Microsoft Entra ユーザーが Adaptive Insights にサインインできるようにするには、ユーザーを Adaptive Insights にプロビジョニングする必要があります。 Adaptive Insights の場合、プロビジョニングは手動で行います。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. **Adaptive Insights** 企業サイトに管理者としてサインインします。
2. [ **管理**&gt;**ユーザーとロール**&gt;**Users** に移動します。
3. [ **新しいユーザー** ] セクションで、作成するユーザーの詳細を入力します。 Microsoft Entra ID のユーザー名が Adaptive Insights の対応するユーザー名と一致していることを確認します。
4. **送信**を選択します。

注

他の Adaptive Insights ユーザー アカウント作成ツールや、Adaptive Insights から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Adaptive Insights に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Adaptive Insights] タイルを選択すると、SSO を設定した Adaptive Insights に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adem-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ADEM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adem-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ADEM の間のシングル サインオンを構成する方法について説明します。

この記事では、ADEM と Microsoft Entra ID を統合する方法について説明します。 ADEM を Microsoft Entra ID と統合すると、次のことが可能になります。

- ADEM へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ADEM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ADEM でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ADEM では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ADEM を追加する

Microsoft Entra ID への ADEM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ADEM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ADEM**」と入力します。
4. 結果パネルから **[ADEM]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ADEM に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、TIMU に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと ADEM の関連ユーザーとの間にリンク関係を確立する必要があります。

ADEM に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ADEM の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ADEM テスト ユーザーを作成** - Microsoft Entra のユーザー表現にリンクする B.Simon の ADEM 上の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ADEM]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://cloud.patch.eu/adem/sso`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://cloud.patch.eu/adem/sso/module.php/saml/sp/saml2-acs.php/default-sp`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://cloud.patch.eu/adem/sso`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ADEM の SSO の構成

**ADEM** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [ADEM サポート チーム](mailto:info@deproefritplanner.nl)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ADEM のテスト ユーザーの作成

このセクションでは、ADEM で Britta Simon というユーザーを作成します。 [ADEM サポート チーム](mailto:info@deproefritplanner.nl)と連携して ADEM プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ADEM サインオン URL にリダイレクトされます。
- ADEM のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ADEM] タイルを選択すると、このオプションは ADEM サインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adglobalview-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ADP Globalview (非推奨) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adglobalview-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ADP Globalview (非推奨) の間のシングル サインオンを構成する方法について説明します。

この記事では、ADP Globalview (非推奨) と Microsoft Entra ID を統合する方法について説明します。 ADP Globalview (非推奨) を Microsoft Entra ID と統合すると、次のことができます。

- ADP Globalview (非推奨) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して ADP Globalview (非推奨) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ADP Globalview (非推奨) のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ADP Globalview (非推奨) では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの ADP Globalview (非推奨) の追加

ADP Globalview (非推奨) の Microsoft Entra ID への統合を構成するには、ADP Globalview (非推奨) をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を表示します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ADP Globalview (Deprecated)」**と入力します。
4. 結果パネルから **ADP Globalview (Deprecated)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ADP Globalview (非推奨) に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ADP Globalview (非推奨) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと ADP Globalview (非推奨) の関連ユーザーとの間にリンク関係を確立する必要があります。

ADP Globalview (非推奨) に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ADP Globalview (非推奨) SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ADP Globalview (非推奨) テスト ユーザーの作成** - ADP Globalview (非推奨) で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ADP Globalview (廃止予定)**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<subdomain>.globalview.adp.com/federate` |
    | `https://<subdomain>.globalview.adp.com/federate2` |
    |  |

    注

    この値は実際の値ではありません。 実際の識別子で値を更新します。 この値を取得するには [、ADP Globalview (非推奨) クライアント サポート チーム](https://www.adp.com/contact-us/overview.aspx) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **ADP Globalview のセットアップ (非推奨)]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ADP Globalview (非推奨) SSO の構成

**ADP Globalview (非推奨)** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [ADP Globalview (非推奨) サポート チームに](https://www.adp.com/contact-us/overview.aspx)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ADP Globalview (非推奨) テスト ユーザーの作成

このセクションでは、ADP Globalview (非推奨) で B.Simon というユーザーを作成します。 [ADP Globalview (非推奨) サポート チーム](https://www.adp.com/contact-us/overview.aspx)と協力して、ADP Globalview (非推奨) プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ADP Globalview (非推奨) に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [ADP Globalview (Deprecated)] タイルを選択すると、SSO を設定した ADP Globalview (Deprecated) に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adobe-echosign-tutorial"} -->
## Microsoft Entra ID で Adobe Sign for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adobe-echosign-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Adobe Sign の間のシングル サインオンを構成する方法について説明します。

この記事では、Adobe Sign と Microsoft Entra ID を統合する方法について説明します。 Adobe Sign を Microsoft Entra ID と統合すると、次のことが可能になります。

- Adobe Sign へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Adobe Sign に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Adobe Sign でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Adobe Sign では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの Adobe Sign の追加

Microsoft Entra ID への Adobe Sign の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Adobe Sign を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Adobe Sign**」と入力します。
4. 結果パネルから **Adobe Sign** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Adobe Sign に対する Microsoft Entra SSO の構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Adobe Sign で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Adobe Sign 内の関連ユーザー間にリンク関係が確立されている必要があります。

Adobe Sign で Microsoft Entra のシングル サインオンを構成してテストするには、次の手順を実行する必要があります。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Adobe Sign SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Adobe Sign のテスト ユーザーの作成** - Microsoft Entra のユーザー表現とリンクする、Adobe Sign での Britta Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Adobe Sign で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Adobe Sign** application integration page に移動し、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。
4. [ **SAML を使用して単一 Sign-On を設定** する] ページで、鉛筆アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.echosign.com/`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.echosign.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、Adobe Sign クライアント サポート チーム](https://helpx.adobe.com/support.html) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Adobe Sign のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Adobe Sign の SSO の構成

1. 構成する前に、 [Adobe Sign クライアント サポート チーム](https://helpx.adobe.com/support.html) に連絡して、Adobe Sign 許可リストにドメインを追加してください。 ドメインの追加方法は次のとおりです。

    a. [Adobe Sign クライアント サポート チーム](https://helpx.adobe.com/support.html)から、ランダムに生成されたトークンが送信されます。 ドメインの場合、トークンは** adobe-sign-verification= xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx** のようになります。

    b。 DNS テキスト レコードに検証トークンを発行し、 [Adobe Sign クライアント サポート チーム](https://helpx.adobe.com/support.html)に通知します。

    注

    これには数日以上かかる場合があります。 DNS への反映の遅延は、DNS で公開された値が 1 時間以上表示されない可能性があることを意味することに注意してください。 DNS テキスト レコードでこのトークンを発行する方法については、通常、組織の IT 管理者が熟知しています。

    c. サポート チケットを通じて [Adobe Sign クライアント サポート チーム](https://helpx.adobe.com/support.html) に通知すると、トークンが発行された後、ドメインが検証され、アカウントに追加されます。

    d. 通常、DNS レコードでトークンを発行する手順は次のとおりです。

    - ドメイン アカウントにサインインする
    - DNS レコードを更新するためのページを検索する。 このページは、DNS 管理、ネーム サーバー管理、または詳細設定と呼ばれる場合があります。
    - 自分のドメインの TXT レコードを検索する。
    - Adobe から提供された完全なトークン値を使用して TXT レコードを追加する。
    - 変更を保存します。
2. 別の Web ブラウザーのウィンドウで、管理者として Adobe Sign 企業サイトにサインインします。
3. SAML メニューで、 **アカウント設定**&gt;**SAML 設定**を選択します。

    [Image: Adobe Sign SAML Settings ページのスクリーンショット。]
4. [ **SAML 設定]** セクションで、次の手順を実行します。

    [Image: SAML 必須を含む SAML 設定が強調表示されているスクリーンショット。]

    [Image: SAML 設定のスクリーンショット。]

    a. [ **SAML モード] で**、[ **SAML 必須]** を選択します。

    b。 [ **Echosign アカウント管理者が Echosign 資格情報を使用してログインすることを許可する] を選択します**。

    c. [ **ユーザーの作成**] で、[ **SAML 経由で認証されたユーザーを自動的に追加**する] を選択します。

    d. **Microsoft Entra 識別子**を **Idp エンティティ ID** テキスト ボックスに貼り付けます。

    e. **Idp ログイン URL** テキスト ボックスに**ログイン URL を**貼り付けます。

    f. **[Idp ログアウト URL**] テキスト ボックスに**ログアウト URL を**貼り付けます。

    g. ダウンロードした **証明書 (Base64)** ファイルをメモ帳で開きます。 その内容をクリップボードにコピーし、[ **IdP 証明書** ] テキスト ボックスに貼り付けます。

    h. [ **変更の保存] を選択します**。

#### Adobe Sign のテスト ユーザーの作成

Microsoft Entra ユーザーが Adobe Sign にサインインできるようにするには、ユーザーを Adobe Sign にプロビジョニングする必要があります。 この設定は手動で行います。

注

他の Adobe Sign ユーザー アカウント作成ツールまたは Adobe Sign から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

1. **Adobe Sign** 企業サイトに管理者としてサインインします。
2. 上部のメニューで、[アカウント] を選択 **します**。 次に、左側のウィンドウで、[**ユーザー] と [グループ**] を選択&gt;**新しいユーザーを作成します**。

    [Image: [アカウント]、[ユーザー]、[グループ]、[新しいユーザーの作成] が強調表示されている Adobe Sign 企業サイトのスクリーンショット]
3. [ **新しいユーザーの作成** ] セクションで、次の手順を実行します。

    [Image: 「新しいユーザーの作成」セクションのスクリーンショット]

    a. プロビジョニングする有効な Microsoft Entra アカウントの **メール アドレス**、 **名**、 **姓** を関連するテキスト ボックスに入力します。

    b。 [ **ユーザーの作成] を選択します**。

注

アカウントがアクティブになる前に、Microsoft Entra アカウント所有者に、アカウント確認用のリンクを含む電子メールが送信されます。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Adobe Sign-on URL にリダイレクトされます。
- Adobe Sign のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Adobe Sign] タイルを選択すると、SSO を設定した Adobe Sign に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adobe-identity-management-provisioning-oidc-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Adobe Identity Management (OIDC) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adobe-identity-management-provisioning-oidc-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-18
- Summary: Microsoft Entra IDから Adobe Identity Management (OIDC) にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Adobe Identity Management (OIDC) と自動ユーザー プロビジョニングを構成するためにMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを Adobe Identity Management (OIDC) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Adobe Identity Management (OIDC) でユーザーを作成する
- アクセスが不要になった場合に Adobe Identity Management (OIDC) のユーザーを無効にする
- Microsoft Entra IDと Adobe Identity Management (OIDC) の間でユーザー属性の同期を維持する
- Adobe Identity Management (OIDC) でグループとグループ メンバーシップをプロビジョニングする
- Adobe Identity Management (OIDC) への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 検証済みドメインを含む [Adobe Admin Console](https://adminconsole.adobe.com/) のフェデレーション ディレクトリ。
- ユーザー プロビジョニングに関する [Adobe ドキュメント](https://helpx.adobe.com/enterprise/using/add-azure-sync.html) を確認する

注

組織でユーザー同期ツールまたは UMAPI 統合を使用している場合は、最初に統合を一時停止する必要があります。 次に、Microsoft Entra の自動プロビジョニングを追加して、ユーザー管理を自動化します。 Microsoft Entra の自動プロビジョニングを構成して実行してから、ユーザー同期ツールまたは UMAPI 統合を完全に削除できます。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとAdobe Identity Management (OIDC)の間でどのデータをマッピングするかを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Adobe Identity Management (OIDC) を構成する

1. [Adobe Admin Console](https://adminconsole.adobe.com/) にログインします。 [ **設定] &gt; [ディレクトリの詳細] &gt; [同期**] に移動します。
2. [ **同期の追加] を選択します**。

     の追加
3. **sync users from Microsoft Azure** を選択し、**Next** を選択します。

    [Image: 選択した [Microsoft Entra IDからのユーザーの同期] を示すスクリーンショット。]
4. **テナント URL** と**シークレット トークン**をコピーして保存します。 これらの値は、Adobe Identity Management (OIDC) アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: 同期]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Adobe Identity Management (OIDC) を追加する

Microsoft Entra アプリケーション ギャラリーから Adobe Identity Management (OIDC) を追加して、Adobe Identity Management (OIDC) へのプロビジョニングの管理を開始します。 前に SSO 用に Adobe Identity Management (OIDC) を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Adobe Identity Management (OIDC) への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Adobe Identity Management (OIDC) の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Adobe Identity Management (OIDC) ]** を選択します。

    [Image: アプリケーション一覧での Adobe Identity Management (OIDC) のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Adobe Identity Management (OIDC) テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Adobe Identity Management (OIDC) に接続できることを確認します。 接続に失敗した場合は、Adobe Identity Management (OIDC) アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Adobe Identity Management (OIDC) に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Adobe Identity Management (OIDC) のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Adobe Identity Management (OIDC) API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Adobe Identity Management (OIDC) に必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | addresses[type eq "work"].country | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Adobe:2.0:User:emailAliases | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Adobe:2.0:User:eduRole | 糸 |  |  |

    注

    **eduRole** フィールドは、`Teacher or Student`などの値を受け取ります。それ以外の値は無視されます。
12. グループを選択 **します**。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Adobe Identity Management (OIDC) に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Adobe Identity Management (OIDC) のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Adobe Identity Management (OIDC) に必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

2023 年 8 月 15 日 - スキーマ検出のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adobe-identity-management-provisioning-saml-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Adobe Identity Management (SAML) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adobe-identity-management-provisioning-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra IDから Adobe Identity Management (SAML) にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Adobe Identity Management (SAML) と自動ユーザー プロビジョニングを構成するためにMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成したMicrosoft Entra IDは、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループをAdobeのアイデンティティ管理 (SAML) に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Adobe Identity Management (SAML) でユーザーを作成します。
- accessが不要になった場合は、Adobe Identity Management (SAML) のユーザーを削除します。
- Microsoft Entra IDと Adobe Identity Management (SAML) の間でユーザー属性の同期を維持します。
- Adobe Identity Management (SAML) でグループとグループ メンバーシップをプロビジョニングします。
- Adobe Identity Management (SAML) への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adobe-identity-management-tutorial) (推奨)。

Adobe Identity Management (SAML) は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 検証済みドメインを含む [Adobe Admin Console](https://adminconsole.adobe.com/) のフェデレーション ディレクトリ。
- ユーザー プロビジョニングに関する [Adobe のドキュメント](https://helpx.adobe.com/enterprise/using/add-azure-sync.html#add-sync)を確認する

注

組織でユーザー同期ツールまたは UMAPI 統合を使用している場合は、最初に統合を一時停止する必要があります。 次に、ユーザー管理を自動化するために Microsoft Entra 自動プロビジョニングを追加します。 Microsoft Entra 自動プロビジョニングが構成されて実行されたら、ユーザー同期ツールまたは UMAPI 統合を完全に削除できます。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとAdobe Identity Management (SAML)の間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Adobe Identity Management (SAML) を構成する

1. [Adobe Admin Console](https://adminconsole.adobe.com/) にログインします。 [ **設定] &gt; [ディレクトリの詳細] &gt; [同期**] に移動します。
2. [ **同期の追加] を選択します**。

    [Image: 追加を示したスクリーンショット。]
3. [**Sync users from Microsoft Azure** を選択し、**Next** を選択します。

    [Image: 選択した [Microsoft Entra IDからのユーザーの同期] を示すスクリーンショット。]
4. **テナント URL** と**シークレット トークン**をコピーして保存します。 これらの値は、Adobe Identity Management (SAML) アプリケーションの [プロビジョニング] タブの **[テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: スクリーンショットは同期を示しています。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Adobe Identity Management (SAML) を追加する

Microsoft Entra アプリケーション ギャラリーから Adobe Identity Management (SAML) を追加して、Adobe Identity Management (SAML) へのプロビジョニングの管理を開始します。 SSO 用に Adobe Identity Management (SAML) を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Adobe Identity Management (SAML) への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づき、TestApp においてユーザーやグループを作成、更新、または無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Adobe Identity Management (SAML) の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で、 **Adobe Identity Management (SAML)** を選択します。

    [Image: アプリケーションの一覧の Adobe Identity Management (SAML) リンクを示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Adobe Identity Management (SAML) テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Adobe Identity Management (SAML) に接続できることを確認します。 接続に失敗した場合は、Adobe Identity Management (SAML) アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Adobe Identity Management (SAML) に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Adobe Identity Management (SAML) のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Adobe Identity Management (SAML) API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Adobe Identity Management (SAML) で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Adobe:2.0:User:emailAliases | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:Adobe:2.0:User:eduRole | 糸 |  |  |

    注

    **eduRole** フィールドは、`Teacher or Student`などの値を受け取ります。それ以外の値は無視されます。
12. **[グループ]** を選びます。
13. Microsoft Entra IDから Adobe Identity Management (SAML) に同期されるグループ属性については、「**Attribute-Mapping**」セクションで確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Adobe Identity Management (SAML) のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Adobe Identity Management (SAML) で必須 |
    | --- | --- | --- | --- |
    | 表示名 | 糸 | ✓ | ✓ |
    | メンバー | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 2023 年 7 月 18 日 - アプリが Gov Cloud に追加されました。
- 2023 年 8 月 15 日 - スキーマ検出のサポートが追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adobe-identity-management-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Adobe Identity Management (SAML) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adobe-identity-management-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Adobe Identity Management (SAML) の間でシングル サインオンを構成する方法について説明します。

この記事では、Adobe Identity Management (SAML) と Microsoft Entra ID を統合する方法について説明します。 Adobe Identity Management (SAML) と Microsoft Entra ID を統合すると、次のことができます。

- Adobe Identity Management (SAML) にアクセス権を持つユーザーを Microsoft Entra ID で管理できます。
- ユーザーが自分の Microsoft Entra アカウントで Adobe Identity Management (SAML) に自動的にサインインできるようにすることができます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Adobe Identity Management (SAML) のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Adobe Identity Management (SAML) では、 **SP** によって開始される SSO がサポートされます。
- Adobe Identity Management (SAML) では、 [**自動** ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adobe-identity-management-provisioning-saml-tutorial) がサポートされます (推奨)。

### ギャラリーから Adobe Identity Management (SAML) を追加する

Adobe Identity Management (SAML) の Microsoft Entra ID への統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから Adobe Identity Management (SAML) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Adobe Identity Management (SAML)」**と入力します。
4. 結果パネルから **Adobe Identity Management (SAML)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Adobe Identity Management (SAML) の Microsoft Entra ID SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Adobe Identity Management (SAML) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Adobe Identity Management (SAML) の関連ユーザーとの間にリンク関係を確立する必要があります。

Adobe Identity Management (SAML) に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Adobe Identity Management (SAML) SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Adobe Identity Management (SAML) のテスト ユーザーの作成** - Adobe Identity Management (SAML) で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリケーション**&gt;**Adobe Identity Management (SAML)**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://adobe.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://federatedid-na1.services.adobe.com/federated/saml/metadata/alias/<CUSTOM_ID>`

    注

    識別子の値は実際の値ではありません。 実際の識別子を使用して値を更新します。これは、フェデレーション ディレクトリのセットアップ ウィザード中に Adobe Admin Console から取得されます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Adobe Identity Management (SAML) のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Adobe Identity Management (SAML) SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として Adobe Identity Management (SAML) 企業サイトにサインインします。
2. **[設定]** タブに移動し、[**ディレクトリの作成**] を選択します。

    [Image: Adobe Identity Management の設定]
3. テキスト ボックスにディレクトリ名を指定し、[ **フェデレーション ID**] を選択し、[ **次へ**] を選択します。

    [Image: Adobe Identity Management のディレクトリの作成]
4. **[その他の SAML プロバイダー] を**選択し、[**次へ**] を選択します。

    [Image: Adobe Identity Management の SAML プロバイダー]
5. [ **選択] を選択** して、ダウンロードした **メタデータ XML** ファイルをアップロードします。

    [Image: Adobe Identity Management の SAML 構成]
6. [ **完了] を選択します**。

#### Adobe Identity Management (SAML) テスト ユーザーの作成

1. [ **ユーザー** ] タブに移動し、[ **ユーザーの追加]** を選択します。

    [Image: Adobe Identity Management のユーザーの追加]
2. [ **Enter user's email address]\(ユーザーの電子メール アドレスの入力** \) ボックスに、 **メール アドレス**を指定します。

    [Image: Adobe Identity Management によるユーザーの保存]
3. **[保存] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Adobe Identity Management (SAML) のサインオン URL にリダイレクトされます。
- Adobe Identity Management (SAML) のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Adobe Identity Management (SAML) タイルを選択すると、このオプションは Adobe Identity Management (SAML) のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adobecaptivateprime-tutorial"} -->
## Microsoft Entra ID を使用して Adobe Captivate Prime for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adobecaptivateprime-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Adobe Captivate Prime の間でシングル サインオンを構成する方法について説明します。

この記事では、Adobe Captivate Prime と Microsoft Entra ID を統合する方法について説明します。 Adobe Captivate Prime を Microsoft Entra ID と統合すると、次のことができます:

- Adobe Captivate Prime にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Adobe Captivate Prime に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Adobe Captivate Prime でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Adobe Captivate Prime では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Adobe Captivate Prime の追加

Microsoft Entra ID への Adobe Captivate Prime の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Adobe Captivate Prime を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Adobe Captivate Prime**」と入力します。
4. 結果のパネルから **[Adobe Captivate Prime]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Adobe Captivate Prime の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Adobe Captivate Prime に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Adobe Captivate Prime の関連ユーザーとの間にリンク関係を確立する必要があります。

Adobe Captivate Prime に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Adobe Captivate Prime SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Adobe Captivate Prime のテスト ユーザーの作成** - Adobe Captivate Prime で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Adobe Captivate Prime**&gt;** シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://captivateprime.adobe.com`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://captivateprime.adobe.com/saml/SSO`
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Adobe Captivate Prime のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]
8. **[プロパティ]** タブに移動し、**ユーザー アクセス URL** をコピーしてメモ帳に貼り付けます。

    [Image: ユーザー アクセスのリンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Adobe Captivate Prime の SSO の構成

**Adobe Captivate Prime** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML**、コピーした**ユーザー アクセス URL**、およびアプリケーションの構成からコピーした適切な URL を、[Adobe Captivate Prime サポート チーム](mailto:captivateprimesupport@adobe.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Adobe Captivate Prime のテスト ユーザーの作成

このセクションでは、Adobe Captivate Prime で Britta Simon というユーザーを作成します。 [Adobe Captivate Prime サポート チーム](mailto:captivateprimesupport@adobe.com)と協力して、Adobe Captivate Prime プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Adobe Captivate Prime に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Adobe Captivate Prime タイルを選択すると、SSO を設定した Adobe Captivate Prime に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adobeexperiencemanager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Adobe Experience Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adobeexperiencemanager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Adobe Experience Manager の間でシングル サインオンを構成する方法について説明します。

この記事では、Adobe Experience Manager と Microsoft Entra ID を統合する方法について説明します。 Adobe Experience Manager を Microsoft Entra ID と統合すると、次のことが可能になります。

- Adobe Experience Manager にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Adobe Experience Manager に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Adobe Experience Manager サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Adobe Experience Manager では、**SP と IDP** によって開始される SSO がサポートされます
- Adobe Experience Manager では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーから Adobe Experience Manager を追加する

Microsoft Entra ID への Adobe Experience Manager の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Adobe Experience Manager を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「Adobe Experience Manager**」と入力します。
4. 結果パネルから **Adobe Experience Manager** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Adobe Experience Manager 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Adobe Experience Manager に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Adobe Experience Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

Adobe Experience Manager に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Adobe Experience Manager の SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Adobe Experience Manager のテスト ユーザーの作成** - Adobe Experience Manager で Britta Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

次の手順に従って、Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Adobe Experience Manager]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、AEM サーバーでも定義する一意の値を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<AEM Server Url>/saml_login`

    Note

    応答 URL は、実際の値ではありません。 応答 URL 値を実際の応答 URL で更新します。 この値を取得するには、 [Adobe Experience Manager クライアント サポート チーム](https://helpx.adobe.com/support/experience-manager.html) に問い合わせてこの値を取得してください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、Adobe Experience Manager サーバーの URL を入力します。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Adobe Experience Manager のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Adobe Experience Manager の SSO の構成

1. 別のブラウザー ウィンドウで、 **Adobe Experience Manager** 管理ポータルを開きます。
2. **[設定]**&gt;**[セキュリティ**&gt;**ユーザー] を選択します**。

    [Image: Adobe Experience Manager の [ユーザー] タイルを示すスクリーンショット。]
3. **管理者**またはその他の関連するユーザーを選択します。
4. アカウント **設定**&gt;**Manage TrustStore** を選択します。

    [Image: [アカウント設定] の [TrustStore の管理] を示すスクリーンショット。]
5. [ **CER ファイルからの証明書の追加]** で、[ **証明書ファイルの選択**] を選択します。 既にダウンロードしている証明書ファイルを参照し選択します。

    [Image: [証明書ファイルの選択] ボタンが強調表示されているスクリーンショット。]
6. 証明書がトラストストアに追加されます。 証明書の別名に注意してください。

    [Image: 証明書が TrustStore に追加されたことを示すスクリーンショット。]
7. [ **ユーザー** ] ページで、 **認証サービスを**選択します。

    [Image: 画面上の認証サービスが強調表示されているスクリーンショット。]
8. [**アカウント設定**]&gt;**[キーストアの作成/管理]を選択します**。 パスワードを入力して、キーストアを作成します。

    [Image: [キーストアの管理] が強調表示されているスクリーンショット。]
9. 管理画面に戻ります。 次に、 **設定**&gt;**Operations**&gt;**Web コンソール**を選択します。

    [Image: [設定] セクションの [操作] で [Web コンソール] が強調表示されているスクリーンショット。]

    [構成] ページが開きます。

    [Image: シングル サインオンの保存ボタンを構成します。]
10. **Adobe Granite SAML 2.0 認証ハンドラーを検索します**。 次に、[ **追加** ] アイコンを選択します。

    [Image: Adobe Granite SAML 2.0 認証ハンドラーが強調表示されているスクリーンショット。]
11. このページで、次の操作を実行します。

    [Image: 「単一 Sign-On 保存ボタンの設定」のスクリーンショット。]

    a. [ **パス** ] ボックスに「 **/**」と入力します。

    b。 [ **IDP URL** ] ボックスに、コピーした **ログイン URL** の値を入力します。

    c. **[IDP 証明書のエイリアス**] ボックスに、TrustStore で追加した **[証明書のエイリアス**] の値を入力します。

    d. [ **セキュリティが提供されたエンティティ ID** ] ボックスに、構成した一意の **Microsoft Entra 識別子** の値を入力します。

    e. [ **Assertion Consumer Service URL]\(アサーション コンシューマー サービス URL** \) ボックスに、構成した **応答 URL** 値を入力します。

    f. [ **キー ストアのパスワード** ] ボックスに、KeyStore で設定した **パスワード** を入力します。

    g. [ **ユーザー属性 ID** ] ボックスに、 **該当する名前 ID** または別のユーザー ID を入力します。

    h. [ **CRX ユーザーの自動作成]** を選択します。

    一. [ **ログアウト URL** ] ボックスに、取得した一意の **ログアウト URL** 値を入力します。

    j. **[保存] を選択します**。
12. **Apache Sling Referrer Filter** セクションで、次の手順を実行します。

    [Image: スリング参照元フィルターのスクリーンショット。]

    a. **allow.empty** 値が true に設定されていることを確認します。

    b。 `login.microsoftonline.com`にを追加します。

    c. **[保存] を選択します**。

#### Adobe Experience Manager のテスト ユーザーの作成

このセクションでは、Adobe Experience Manager で Britta Simon というユーザーを作成します。 [ **CRX ユーザーの自動作成** ] オプションを選択した場合、認証が成功した後にユーザーが自動的に作成されます。

ユーザーを手動で作成する場合は、 [Adobe Experience Manager サポート チーム](https://helpx.adobe.com/support/experience-manager.html) と協力して、Adobe Experience Manager プラットフォームにユーザーを追加してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Adobe Experience Manager のサインオン URL にリダイレクトされます。
- Adobe Experience Manager のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Adobe Experience Manager に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Adobe Experience Manager タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Adobe Experience Manager に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adoddle-csaas-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Adoddle cSaas Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adoddle-csaas-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Adoddle cSaas Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Adoddle cSaas Platform と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Adoddle cSaas Platform を統合すると、次のことが可能になります。

- Adoddle cSaas Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Adoddle cSaas Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Adoddle cSaas Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Adoddle cSaas Platform では、**IDP** Initiated SSO がサポートされます。
- Adoddle cSaas Platform では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Adoddle cSaas Platform を追加する

Microsoft Entra ID への Adoddle cSaas Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Adoddle cSaas Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Adoddle cSaas Platform**」と入力します。
4. 結果パネルから **[Adoddle cSaas Platform]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Adoddle cSaas Platform 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Adoddle cSaas Platform に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Adoddle cSaas Platform での関連ユーザーとの間にリンク関係を確立する必要があります。

Adoddle cSaas Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Adoddle cSaas Platform SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Adoddle cSaas Platform のテストユーザーを作成する** - Adoddle cSaas Platform において、Microsoft Entra での B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Adoddle cSaas Platform]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Adoddle cSaas Platform の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Adoddle cSaas Platform SSO を構成する

**Adoddle cSaas Platform** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Adoddle cSaas Platform サポート チーム](mailto:support@asite.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Adoddle cSaas Platform のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Adoddle cSaas Platform に作成します。 Adoddle cSaas Platform では、**Just-In-Time プロビジョニング**がサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Adoddle cSaas Platform に存在しない場合は、Adoddle cSaas Platform にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Adoddle cSaas Platform に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Adoddle cSaas Platform] タイルを選択すると、SSO を設定した Adoddle cSaas Platform に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adp-emea-french-hr-portal-tutorial"} -->
## Microsoft Entra IDのシングルサインオン用にADP EMEAフランスHRポータルmon.adp.comを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adp-emea-french-hr-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ADP EMEA French HR Portal mon.adp.com の間でシングル サインオンを構成する方法について説明します。

この記事では、ADP EMEA French HR Portal mon.adp.com と Microsoft Entra ID を統合する方法について説明します。 ADP EMEA French HR Portal mon.adp.com と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID での ADP EMEA French HR Portal mon.adp.com にアクセスできるユーザーを制御する。
- ユーザーが自分の Microsoft Entra アカウントで ADP EMEA French HR Portal mon.adp.com に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ADP EMEA French HR Portal mon.adp.com でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ADP EMEA French HR Portal mon.adp.com では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの ADP EMEA French HR Portal mon.adp.com の追加

Microsoft Entra ID への ADP EMEA French HR Portal mon.adp.com の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ADP EMEA French HR Portal mon.adp.com を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ADP EMEA French HR Portal mon.adp.com**」と入力します。
4. 結果パネルで **[ADP EMEA French HR Portal mon.adp.com]** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ADP EMEA French HR Portal mon.adp.com 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、ADP EMEA French HR Portal mon.adp.com に対する Microsoft Entra SSO を構成およびテストします。 SSO を機能させるために、Microsoft Entra ユーザーと ADP EMEA French HR Portal mon.adp.com の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を ADP EMEA French HR Portal mon.adp.com で構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ADP EMEA French HR Portal mon.adp.com SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ADP EMEA French HR Portal mon.adp.com テスト ユーザーの作成** - ADP EMEA French HR Portal mon.adp.com で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[ADP EMEA French HR Portal mon.adp.com]**&gt;**[シングル サインオン]** を閲覧します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. ADP EMEA French HR Portal mon.adp.com アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、ADP EMEA French HR Portal mon.adp.com アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | CompanyID | &lt;given\_by\_adp&gt; |
    | ApplicationID | uxfr |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ADP EMEA French HR Portal mon.adp.com SSO の構成

**ADP EMEA French HR Portal mon.adp.com** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と適切にコピーされた URL をアプリケーション構成から [ADP EMEA French HR Portal mon.adp.com サポート チーム](mailto:asp.projects@europe.adp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ADP EMEA French HR Portal mon.adp.com テスト ユーザーの作成

このセクションでは、ADP EMEA French HR Portal mon.adp.com で Britta Simon というユーザーを作成します。 [ADP EMEA French HR Portal mon.adp.com サポート チーム](mailto:asp.projects@europe.adp.com)と連携して、ADP EMEA French HR Portal mon.adp.com プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ADP EMEA French HR Portal mon.adp.com に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで ADP EMEA French HR Portal mon.adp.com タイルを選択すると、SSO を設定した ADP EMEA French HR Portal mon.adp.com に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adp-oidc-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ADP (OIDC) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adp-oidc-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra と ADP (OIDC) の間でシングル サインオンを構成する方法について説明します。

この記事では、ADP (OIDC) と Microsoft Entra ID を統合する方法について説明します。 ADP (OIDC) と Microsoft Entra ID を統合すると、次のことができます。

Microsoft Entra ID を使用して、ADP (OIDC) にアクセスできるユーザーを制御します。 ユーザーが自分の Microsoft Entra アカウントを使用して ADP (OIDC) に自動的にサインインできるようにします。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- ADP (OIDC) でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから ADP (OIDC) を追加する

Microsoft Entra ID への ADP (OIDC) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ADP (OIDC) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「ADP (OIDC)」**と入力します。
4. 結果パネルで **ADP (OIDC)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ADP (OIDC)**&gt;**シングルサインオン**にアクセスします。
3. 次のセクションで以下の手順を実行します。

    1. [ **アプリケーションに移動] を**選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID を**コピーし、後で ADP (OIDC) 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. [ **エンドポイント** ] タブで、 **OpenID Connect メタデータ ドキュメント** リンクをコピーし、後で ADP (OIDC) 側の構成で使用します。

        [Image: タブにエンドポイントが表示されているスクリーンショット。]
4. 左側のメニューの [ **認証** ] タブに移動し、次の手順を実行します。

    1. **リダイレクト URI** ボックスに、ADP (OIDC) 側からコピーした **リライングパーティ リダイレクト URI** の値を貼り付けてください。

        [Image: リダイレクト値を示すスクリーンショット。]
    2. [ **構成] ボタンを** 選択します。
5. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **+新しいクライアント シークレット**] を選択します。
    2. テキストボックスに有効な **説明** を入力し、要件に従ってドロップダウンから **[有効期限** 日] を選択し、[ **追加**] を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、 **値** が生成されます。 値をコピーし、後で ADP (OIDC) 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成**を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に ADP (OIDC) へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ADP (OIDC)** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### ADP (OIDC) SSO の構成

OAuth/OIDC フェデレーションのセットアップを完了するための構成手順を次に示します。

1. ADP で発行された資格情報 (`https://identityfederation.adp.com/`) を使用して、ADP フェデレーション SSO サイトにサインインします。
2. [ **フェデレーション セットアップ] を**選択し、Id プロバイダーを **Microsoft Azure** として選択します。

    [Image: フェデレーションのセットアップを示すスクリーンショット。]
3. [OIDC セットアップの有効化] を選択して OIDC フェデレーションを有効にします。
4. **OIDC セットアップ** タブで次の手順を実行します。

    [Image: セットアップを示すスクリーンショット。]

    ａ。 **証明書利用者リダイレクト URI** の値をコピーし、後で Entra 構成で使用します。

    b。 Entra ページからコピーした**既知の URL** フィールドに **Open ID Connect メタデータ ドキュメント**の値を貼り付け、[**RETRIEVE**] を選択してエンドポイントの値を自動的に設定**します**。

    c. [ **アプリケーションの詳細** ] タブの [ **アプリケーション クライアント ID** ] フィールドにアプリケーション ID の値 **を** 貼り付けます。

    d. [**対象ユーザー**] フィールドに**アプリケーション ID を**貼り付けます。

    え [ **アプリケーション クライアント シークレット** ] フィールドに、Entra の **[証明書] および [シークレット** ] からコピーした値を貼り付けます。

    f. **ユーザー識別子**は、ADPとアイデンティティプロバイダーの間で同期されるユニークな識別子の属性名である必要があります。

    ジー **[保存] を選択します**。

    h. 構成を保存したら、[接続の **アクティブ化**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adpfederatedsso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ADP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adpfederatedsso-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と ADP の間のシングル サインオンを構成する方法について説明します。

この記事では、ADP と Microsoft Entra ID を統合する方法について説明します。 ADP を Microsoft Entra ID と統合すると、次のことが可能になります。

- ADP へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ADP に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理する。

ADP は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- ADP でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ADP では、 **IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから ADP を追加する

Microsoft Entra ID への ADP の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に ADP を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ADP**」と入力します。
4. 結果パネルから **ADP** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### ADP に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、ADP に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと ADP の関連ユーザーとの間にリンク関係を確立する必要があります。

ADP に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ADP SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ADP テスト ユーザーの作成** - ADP で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ADP** アプリケーション統合ページに移動し、[**プロパティ] タブ**を選択し、次の手順を実行します。

    [Image: シングル サインオンのプロパティ]

    a. **ユーザーのサインインを有効にする**フィールドの値を**「はい」**に設定します。

    b。 **ユーザー アクセス URL を**コピーし、後で説明する**「サインオン URL の構成」セクション**に貼り付ける必要があります。

    c. **[ユーザー割り当て必須**フィールド] の値を **[はい**] に設定します。

    d. [ **ユーザーに表示]** フィールドの値を **[いいえ**] に設定します。
3. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
4. **Entra ID**&gt;**Enterprise apps**&gt;**ADP** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
5. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
7. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://fed.adp.com`
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **ADP のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ADP SSO の構成

1. 別の Web ブラウザー ウィンドウで、ADP 企業サイトに管理者としてサインインします。
2. [ **フェデレーション セットアップ] を** 選択し、[ **ID プロバイダー]** に移動し、 **Microsoft Azure** を選択します。

    [Image: ID プロバイダーのスクリーンショット。]
3. **[サービスの選択] で**、接続に該当するすべてのサービスを選択し、[**次へ**] を選択します。

    [Image: サービスの選択のスクリーンショット。]
4. [ **構成** ] セクションで、[ **次へ**] を選択します。
5. [ **メタデータのアップロード**] で、[ **参照** ] を選択して、ダウンロードしたメタデータ XML ファイルをアップロードし、[ **アップロード**] を選択します。

    [Image: メタデータをアップロードするためのスクリーンショット。]

#### フェデレーション アクセスのために ADP サービスを構成する

重要

ADP サービスへのフェデレーション アクセスが必要な従業員を ADP サービス アプリに割り当て、その後、ユーザーを特定の ADP サービスに再割り当てする必要があります。 ADP 担当者から送信される確認の電子メールを受信したら、ADP サービスを構成し、ユーザーの割り当てまたは管理によって、特定の ADP サービスへのユーザー アクセスを制御します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「ADP**」と入力します。
4. 結果パネルから **ADP** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**に移動します。
3. **ADP** アプリケーション統合ページを選択し、[**プロパティ] タブ**を選択し、次の手順を実行します。

    [Image: シングル サインオンのリンクされたプロパティのタブ]

    1. **ユーザーのサインインを有効にする**フィールドの値を**「はい」**に設定します。
    2. **[ユーザー割り当て必須**フィールド] の値を **[はい**] に設定します。
    3. [ **ユーザーに表示]** フィールドの値を **[はい**] に設定します。
4. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
5. **Entra ID**&gt;**Enterprise apps**&gt;**ADP** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
6. [**シングル サインオン方法の選択**] ダイアログで、[**リンク**済み**モード**] を選択してアプリケーションを **ADP** にリンクします。
7. [ **サインオン URL の構成** ] セクションに移動し、次の手順に従います。

    [Image: シングル サインオンの構成]

    1. 上の **[プロパティ] タブ**からコピーした**ユーザー アクセス URL を**貼り付けます (メインの ADP アプリから)。
    2. さまざまなリレー状態 URL をサポートする 5 つのアプリを次 **に示します**。 特定のアプリケーションの適切な **リレー状態 URL** 値を **ユーザー アクセス URL** に手動で追加する必要があります。

        - **ADP Workforce Now**

            `<User access URL>&relaystate=https://fed.adp.com/saml/fedlanding.html?WFN`
        - **ADP Workforce Now 強化された時間管理**

            `<User access URL>&relaystate=https://fed.adp.com/saml/fedlanding.html?EETDC2`
        - **ADP Vantage HCM**

            `<User access URL>&relaystate=https://fed.adp.com/saml/fedlanding.html?ADPVANTAGE`
        - **ADP Enterprise HR**

            `<User access URL>&relaystate=https://fed.adp.com/saml/fedlanding.html?PORTAL`
        - **MyADP**

            `<User access URL>&relaystate=https://fed.adp.com/saml/fedlanding.html?REDBOX`
8. 変更を**保存します**。
9. ADP の担当者から確認の電子メールを受信したら、1 人または 2 人のユーザーでテストを開始します。

    1. 数名のユーザーを ADP サービス アプリに割り当てて、フェデレーション アクセスをテストします。
    2. ユーザーがギャラリーの ADP サービス アプリにアクセスして ADP サービスにアクセスできると、テストは成功です。
10. テストが成功したことを確認したら、フェデレーション ADP サービスを個々のユーザーまたはユーザー グループに割り当てます。これについては、この記事の後半で説明し、従業員にロールアウトします。

#### 同じテナント内の複数のインスタンスをサポートするように ADP を構成する

1. **[基本的な SAML 構成]** セクションに移動し、[**識別子 (エンティティ ID)]** ボックスにインスタンス固有の URL を入力します。

    注

    これは、インスタンスに関連していると思われる任意のランダムな値である可能性があります。
2. 同じテナント内の複数のインスタンスをサポートするには、次の手順に従ってください。

    [Image: オーディエンスクレームの値を構成する方法を示すスクリーンショット。]

    1. **[属性と要求**] セクション&gt;**[Advanced settings**&gt;**Advanced SAML claims options**] に移動し、[**編集]** を選択します。
    2. [ **発行者にアプリケーション ID を追加する** ] チェック ボックスをオンにします。
    3. [ **対象ユーザー要求のオーバーライド** ] チェックボックスを有効にします。
    4. [ **対象ユーザー要求の値** ] ボックスに「 `https://fed.adp.com` 」と入力し、[ **保存]** を選択します。
3. [管理] セクションの [ **プロパティ** ] タブに移動し、 **アプリケーション ID を**コピーします。

    [Image: [プロパティ] タブからアプリケーションの値をコピーする方法を示すスクリーンショット。]
4. **フェデレーション メタデータ XML** ファイルをダウンロードして開き、最後に**アプリケーション ID を**手動で追加して **entityID** 値を編集します。

    [Image: フェデレーション ファイルにアプリケーション値を追加する方法を示すスクリーンショット。]
5. XML ファイルを**保存**し、ADP 側で使用します。

#### ADP のテスト ユーザーの作成

このセクションの目的は、ADP で B.Simon というユーザーを作成することです。 [ADP サポート チーム](https://www.adp.com/contact-us/overview.aspx)と協力して、ADP アカウントにユーザーを追加します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ADP に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [ADP] タイルを選択すると、SSO を設定した ADP に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adra-by-trintech-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Adra by Trintech を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adra-by-trintech-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Adra by Trintech の間でシングル サインオンを構成する方法について説明します。

この記事では、Adra by Trintech と Microsoft Entra ID を統合する方法について説明します。 Adra by Trintech と Microsoft Entra ID を統合すると、次のことが可能になります。

- Adra by Trintech にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Adra by Trintech に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Adra by Trintech でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者に加え、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができる。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Adra by Trintech では、**SP** および **IDP** で開始される SSO がサポートされます。

### ギャラリーから Adra by Trintech を追加する

Microsoft Entra ID への Adra by Trintech の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Adra by Trintech を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Adra by Trintech**」と入力します。
4. 結果パネルから **[Adra by Trintech]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Adra by Trintech に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Adra by Trintech に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Adra by Trintech の関連ユーザーとの間にリンク関係を確立する必要があります。

Adra by Trintech に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO の構成**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Adra by Trintech SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Adra by Trintech のテスト ユーザーの作成** - Adra by Trintech で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**Adra by Trintech**&gt;**シングルサインオン**にブラウズしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **サービス プロバイダー メタデータ ファイル**を保持しており、**IDP** Initiated モードに構成したい場合は、 **[基本的な SAML 構成]** セクション上で次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルのアップロードを示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**と**応答 URL** の値が、 **[基本的な SAML 構成]** セクションに自動的に設定されます。

    d. **[サインオン URL]** テキスト ボックスに、URL として「`https://login.adra.com`」と入力します。

    e. **[リレー状態]** ボックスに、URL `https://setup.adra.com` を入力します。

    f. **[ログアウト URL]** テキスト ボックスに、次の URL を入力します。`https://login.adra.com/Saml/SLOServiceSP`

    Note

    **サービス プロバイダー メタデータ ファイル**は、後で説明する「**Adra by Trintech SSO の構成**」セクションから取得します。 **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Adra by Trintech SSO の構成

1. Adra by Trintech 企業サイトに管理者としてログインします。
2. **[エンゲージメント]**&gt;**[セキュリティ]** タブの &gt;**[セキュリティ ポリシー]**&gt; に移動し、**[フェデレーション ID プロバイダーを使用する]** ボタンを選択します。
3. Adra ページで**ここを**選択して**サービス プロバイダーのメタデータ ファイル**をダウンロードし、このメタデータ ファイルをアップロードします。

    [Image: [構成設定] を示すスクリーンショット。]
4. [ **新しいフェデレーション ID プロバイダーの追加** ] ボタンを選択し、必要なフィールドの詳細を適宜入力します。

    a. テキスト ボックスに有効な **[名前]** と **[説明]** の値を入力します。

    b。 [ **メタデータ URL** ] ボックスに、コピーした **アプリのフェデレーション メタデータ URL を** 貼り付け、[ **テスト URL** ] ボタンを選択します。

    c. [ **保存] を** 選択して SAML 構成を保存します。

#### Adra by Trintech テスト ユーザーの作成

このセクションでは、Adra by Trintech で Britta Simon というユーザーを作成します。 [Adra by Trintech サポート チーム](mailto:support@adra.com)と協力して、Adra by Trintech プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Adra by Trintech のサインオン URL にリダイレクトされます。
- Adra by Trintech のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Adra by Trintech に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Adra by Trintech] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Adra by Trintech に自動的にサインインされます。 詳細については、「[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/adstream-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Adstream' を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/adstream-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Adstream の間のシングル サインオンを構成する方法について説明します。

この記事では、Adstream と Microsoft Entra ID を統合する方法について説明します。 Adstream は、複数のチームが資産で共同作業を行い、コンテンツを配布する機能を提供するコンテンツ管理システムです。 Adstream を Microsoft Entra ID と統合すると、次のことが可能になります。

- Adstream にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Adstream に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Adstream 向けに Microsoft Entra シングル サインオンを構成してテストします。 Adstream では、 **SP** によって開始されるシングル サインオンのみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Adstream を Microsoft Entra ID と統合するためには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Adstream でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Adstream アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Adstream を追加する

Microsoft Entra アプリケーション ギャラリーから Adstream を追加して、Adstream でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Adstream**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **応答 URL** ] ボックスに、URL を入力します。 `https://msft.adstream.com/saml/assert`

    b。 [ **サインオン URL** ] ボックスに、URL を入力します。 `https://msft.adstream.com`

    c. [ **リレー状態** ] ボックスに、URL を入力します。 `https://a5.adstream.com/projects#/projects/projects`
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Adstream のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な U R L に構成をコピーするためのスクリーンショット。]

### Adstream SSO を構成する

**Adstream** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Adstream サポート チーム](mailto:support@adstream.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Adstream テスト ユーザーを作成する

このセクションでは、Adstream で Britta Simon というユーザーを作成します。 [Adstream サポート チーム](mailto:support@adstream.com)と協力して、Adstream プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Adstream のサインオン URL にリダイレクトされます。
- Adstream のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Adstream] タイルを選択すると、このオプションは Adstream のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/advance-kerbf5-tutorial"} -->
## 多層 SaaS アーキテクチャ用に高度な F5 Kerberos 委任を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/advance-kerbf5-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: この記事では、F5 と Microsoft Entra ID を統合するために必要な手順について説明します。

この記事では、F5 と Microsoft Entra ID を統合する方法について説明します。 F5 を Microsoft Entra ID と統合すると、次のことが可能になります。

- F5 にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して F5 に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- F5 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

F5 では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

F5 SSO は、次の 3 つの異なる方法で構成できます。

- Advanced Kerberos アプリケーションの F5 シングル サインオンを構成する
- [ヘッダー ベース アプリケーションの F5 シングル サインオンを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/f5-big-ip-headers-easy-button)
- [Kerberos アプリケーションの F5 シングル サインオンを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kerbf5-tutorial)

### ギャラリーからの F5 の追加

Microsoft Entra ID への F5 の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に F5 を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**を開きます。
3. 新しいアプリケーションを追加するには、[ **新しいアプリケーション**] を選択します。
4. **[ギャラリーからの追加**] セクションで、検索ボックスに**「F5**」と入力します。
5. 結果パネルから **F5 キー** を押し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### F5 に対して Microsoft Entra シングル サインオンを構成してテストする

**B.Simon** というテスト ユーザーを使用して、F5 に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと F5 の関連ユーザーとの間にリンク関係を確立する必要があります。

F5 で Microsoft Entra SSO を構成してテストするには、次の構成要素を順に実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **F5-SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **F5 テスト ユーザーの作成** - F5 で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**F5**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YourCustomFQDN>.f5.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YourCustomFQDN>.f5.com/`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YourCustomFQDN>.f5.com/`

    Note

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [F5 クライアント サポート チーム](https://support.f5.com/csp/knowledge-center/software/BIG-IP?module=BIG-IP%20APM45) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **F5 のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### F5 SSO の構成

- [ヘッダー ベース アプリケーションの F5 シングル サインオンを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/f5-big-ip-headers-easy-button)
- [Kerberos アプリケーションの F5 シングル サインオンを構成する](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/kerbf5-tutorial)

#### Advanced Kerberos アプリケーション用に F5 シングル サインオンを構成する

1. 新しい Web ブラウザー ウィンドウを開き、F5 (Advanced Kerberos) 企業サイトに管理者としてサインインして、次の手順を実行します。
2. セットアップ プロセスの後半で使用する F5 (Advanced Kerberos) にメタデータ証明書をインポートする必要があります。 **[System &gt; Certificate Management &gt; Traffic Certificate Management &gt;&gt; SSL Certificate List**] に移動します。 右上隅の **[インポート]** を選択します。

    [Image: メタデータ証明書をインポートするための [インポート] ボタンが強調表示されているスクリーンショット。]
3. SAML IDP を設定するには、**[Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス) &gt; [Federation](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/フェデレーション) &gt; [SAML Service Provider](SAML サービス プロバイダー) &gt; [Create](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/作成) &gt; [From Metadata](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メタデータから)** に移動します。

    [Image: メタデータから SAML IDP を作成する方法を示すスクリーンショット。]

    [Image: [Create New SAML IdP Connector](新しい SAML IdP コネクタの作成) 画面を示すスクリーンショット。]

    [Image: F5 (Advanced Kerberos) の構成]

    [Image: [シングル サインオン サービスの設定] 画面を示すスクリーンショット。]
4. タスク 3 からアップロードした証明書を指定します

    [Image: [SAML IdP コネクタの編集] 画面を示すスクリーンショット。]

    [Image: [Single Logout Service Settings](シングル ログアウト サービスの設定) 画面を示すスクリーンショット。]
5. SAML SP をセットアップするには、**アクセス&gt;フェデレーション&gt;SAML サービス フェデレーション&gt;ローカル SP サービス&gt;作成**に移動します。

    [Image: ローカル SP サービスを作成する画面を示すスクリーンショット。]
6. [ **OK] を選択します**。
7. SP 構成を選択し、 **バインド/バインド解除 IdP コネクタを選択します**。

    [Image: SAML サービス プロバイダーを示すスクリーンショット。]
8. [ **新しい行の追加]** を選択し、前の手順で作成した **外部 IdP コネクタ** を選択します。

    [Image: [新しい行の追加] ボタンが強調表示されているスクリーンショット。]
9. Kerberos SSO を構成する場合は、**[Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス) &gt; [Single Sign-on](シングル サインオン) &gt; [Kerberos]**

    Note

    Kerberos 委任アカウントを作成して指定する必要があります。 KCD セクションを参照してください (変数リファレンスについては、付録を参照してください)

    - ユーザー名のソース `session.saml.last.attr.name.http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`
    - ユーザー領域のソース `session.logon.last.domain`

    [Image: [Access &gt; Single Sign On] が強調表示されているスクリーンショット。]
10. アクセス プロファイルを構成する場合は、 **アクセス &gt; プロファイル/ポリシー &gt; アクセス プロファイル (セッション ポリシーごと)** です。

    [Image: [プロファイル/ポリシー] メニュー オプションの [プロパティ] タブが強調表示されているスクリーンショット。]

    [Image: [SSO/Auth Domains] タブを示すスクリーンショット。]

    [ **アクセス ポリシー** ]タブを選択して **、[全般プロパティ** ]と **[AAA サーバ]を表示します**。 **ビジュアル ポリシー エディター**の場合、編集するプロファイルのポリシーを選択します (この例では **KerbApp200**)。

    [Image: アクセス ポリシーの [プロパティ] タブを示すスクリーンショット。]

    [Image: 変数代入のプロパティを示すスクリーンショット。]

    - session.logon.last.usernameUPN expr {[mcget {session.saml.last.identity}]}
    - session.ad.lastactualdomain TEXT superdemo.live

        サーバー *の superdemo.live* と **SearchFilter** の値 **(userPrincipalName=%{session.logon.last.usernameUPN})** を指定するには、クエリプロパティを編集します。
    - (userPrincipalName=%{session.logon.last.usernameUPN})

        **ブランチ ルール** を選択してブランチルールを追加し、**プロパティ**を選択してプロパティを表示します。

    [Image: カスタム変数とカスタム式のテキスト ボックスを示すスクリーンショット。]

    - session.logon.last.username expr { "[mcget {session.ad.last.attr.sAMAccountName}]" }

    [Image: [SSO トークン名] フィールドと [SSO トークン パスワード] フィールドの値を示すスクリーンショット。]

    - mcget {session.logon.last.username}
    - mcget {session.logon.last.password}
11. 新しいノードを追加するには、 **ローカル トラフィック &gt; ノード &gt; ノード リスト &gt; +** に移動します。

    [Image: ローカル トラフィック &gt; ノードが強調表示されているスクリーンショット。]
12. 新しいプールを作成するには、[ **ローカル トラフィック &gt; プール] &gt; [プールの一覧] &gt; [作成]** に移動します。

    [Image: ローカル トラフィック &gt; プールが強調表示されているスクリーンショット。]
13. 新しい仮想サーバーを作成するには、 **ローカル トラフィック &gt; 仮想サーバー &gt; 仮想サーバーの一覧 &gt; +** に移動します。

    [Image: ローカル トラフィック &gt; 仮想サーバーが強調表示されているスクリーンショット。]
14. 前の手順で作成したアクセス プロファイルを指定します。

    [Image: 作成したアクセス プロファイルを指定する場所を示すスクリーンショット。]

#### Kerberos 委任の設定

Note

詳細については [、こちらを参照してください](https://www.f5.com/pdf/deployment-guides/kerberos-constrained-delegation-dg.pdf)

- **手順 1: 委任アカウントを作成する**

    - 例

    ```
    Domain Name : superdemo.live
    Sam Account Name : big-ipuser
    
    New-ADUser -Name "APM Delegation Account" -UserPrincipalName host/big-ipuser.superdemo.live@superdemo.live -SamAccountName "big-ipuser" -PasswordNeverExpires $true -Enabled $true -AccountPassword (Read-Host -AsSecureString "Password!1234")
    ```
- **手順 2: SPN (APM 委任アカウント) を設定する**

    - 例

    ```
    setspn –A host/big-ipuser.superdemo.live big-ipuser
    ```
- **手順 3: SPN 委任 (App Service アカウントの場合)**

    - F5 委任アカウントに適切な委任を設定します。
    - 次の例では、FRP-App1.superdemo.live アプリの KCD に対して APM 委任アカウントが構成されています。

        [Image: [APM 委任アカウントのプロパティ] &gt; [委任] タブを示すスクリーンショット。]

1. この下の上記のリファレンス ドキュメントに記載されている詳細を指定します。
2. 付録 - SAML – F5 BIG-IP 変数マッピングを以下に示します。

    [Image: [概要] &gt; [アクティブなセッション] タブを示すスクリーンショット。]

    [Image: 変数とセッション キーを示すスクリーンショット。]
3. 既定の SAML 属性の完全な一覧を次に示します。 GivenName は、次の文字列を使用して表されます。 `session.saml.last.attr.name.http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`

| セッション | 属性 |
| --- | --- |
| eb46b6b6.session.saml.last.assertionID | `<TENANT ID>` |
| eb46b6b6.session.saml.last.assertionIssueInstant | `<ID>` |
| eb46b6b6.session.saml.last.assertionIssuer | `https://sts.windows.net/<TENANT ID>`/ |
| eb46b6b6.session.saml.last.attr.name。http://schemas.microsoft.com/claims/authnmethodsreferences | `http://schemas.microsoft.com/ws/2008/06/identity/authenticationmethod/password` |
| eb46b6b6.session.saml.last.attr.name。http://schemas.microsoft.com/identity/claims/displayname | user0 |
| eb46b6b6.session.saml.last.attr.name。http://schemas.microsoft.com/identity/claims/identityprovider | `https://sts.windows.net/<TENANT ID>/` |
| eb46b6b6.session.saml.last.attr.name,http://schemas.microsoft.com/identity/claims/objectidentifier | `<TENANT ID>` |
| eb46b6b6.session.saml.last.attr.name。http://schemas.microsoft.com/identity/claims/tenantid | `<TENANT ID>` |
| eb46b6b6.session.saml.last.attr.name。http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress | `user0@superdemo.live` |
| eb46b6b6.session.saml.last.attr.name。http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname | user0 |
| eb46b6b6.session.saml.last.attr.name。http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name | `user0@superdemo.live` |
| eb46b6b6.session.saml.last.attr.name。http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname | 0 |
| eb46b6b6.session.saml.last.audience | `https://kerbapp.superdemo.live` |
| eb46b6b6.session.saml.last.authNContextClassRef | urn:oasis:names:tc:SAML:2.0:ac:classes:Password |
| eb46b6b6.session.saml.last.authNInstant | `<ID>` |
| eb46b6b6.session.saml.last.identity | `user0@superdemo.live` |
| eb46b6b6.session.saml.last.inResponseTo | `<TENANT ID>` |
| eb46b6b6.session.saml.last.nameIDValue | `user0@superdemo.live` |
| eb46b6b6.session.saml.last.nameIdFormat | urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress |
| eb46b6b6.session.saml.last.responseDestination | `https://kerbapp.superdemo.live/saml/sp/profile/post/acs` |
| eb46b6b6.session.saml.last.responseId | `<TENANT ID>` |
| eb46b6b6.session.saml.last.responseIssueInstant | `<ID>` |
| eb46b6b6.session.saml.last.responseIssuer | `https://sts.windows.net/<TENANT ID>/` |
| eb46b6b6.session.saml.last.result | 1 |
| eb46b6b6.session.saml.last.samlVersion | 2.0 |
| eb46b6b6.session.saml.last.sessionIndex | `<TENANT ID>` |
| eb46b6b6.session.saml.last.statusValue | urn:oasis:names:tc:SAML:2.0:status:Success |
| eb46b6b6.session.saml.last.subjectConfirmDataNotOnOrAfter | `<ID>` |
| eb46b6b6.session.saml.last.subjectConfirmDataRecipient | `https://kerbapp.superdemo.live/saml/sp/profile/post/acs` |
| eb46b6b6.session.saml.last.subjectConfirmMethod | urn:oasis:names:tc:SAML:2.0:cm:bearer |
| eb46b6b6.session.saml.last.validityNotBefore | `<ID>` |
| eb46b6b6.session.saml.last.validityNotOnOrAfter | `<ID>` |

#### F5 テスト ユーザーの作成

このセクションでは、F5 で B.Simon というユーザーを作成します。 [F5 クライアント サポート チーム](https://support.f5.com/csp/knowledge-center/software/BIG-IP?module=BIG-IP%20APM45)と協力して、F5 プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra シングル サインオン構成をテストします。

アクセス パネルで [F5] タイルを選択すると、SSO を設定した F5 に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/aftership-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AfterShip を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aftership-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AfterShip の間のシングル サインオンを構成する方法について説明します。

この記事では、AfterShip と Microsoft Entra ID を統合する方法について説明します。 AfterShip を Microsoft Entra ID と統合すると、次のことができます。

- AfterShip にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して AfterShip に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AfterShip でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AfterShip では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから AfterShip を追加する

Microsoft Entra ID への AfterShip の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに AfterShip を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「AfterShip**」と入力します。
4. 結果パネルから **AfterShip** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AfterShip 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、AfterShip に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと AfterShip の関連ユーザーとの間にリンク関係を確立する必要があります。

AfterShip に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AfterShip SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AfterShip テスト ユーザーの作成 - AfterShip** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AfterShip**&gt;**シングルサインオンにアクセスします**。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://accounts.aftership.com/auth/realms/business`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.aftership.com/auth/realms/business/broker/<CustomerName>/endpoint`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://admin.aftership.com/?idp_hint=<CustomerName>`

    注

    これらの値は実際の値ではありません。 これらの値を、実際の応答 URL およびサインオン URL で更新してください。 これらの値を取得するには、 [AfterShip サポート チーム](https://support.aftership.com/) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **AfterShip のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AfterShip SSO を構成する

**AfterShip** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [AfterShip サポート チーム](https://support.aftership.com/)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### AfterShip のテスト ユーザーを作成する

このセクションでは、AfterShip で B.Simon というユーザーを作成します。 [AfterShip サポート チーム](https://support.aftership.com/)と協力して、AfterShip プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる AfterShip のサインオン URL にリダイレクトされます。
- AfterShip のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [AfterShip] タイルを選択すると、このオプションは AfterShip のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/agile-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用にアジャイル プロビジョニングを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/agile-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Agile Provisioning の間のシングル サインオンを構成する方法について説明します。

この記事では、アジャイル プロビジョニングと Microsoft Entra ID を統合する方法について説明します。 Agile Provisioning と Microsoft Entra ID を統合すると、次のことができます。

- Agile Provisioning にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Agile Provisioning に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Agile Provisioning でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- アジャイル プロビジョニングでは、**SP**開始のSSOと**IDP**開始のSSOがサポートされます。

### ギャラリーからの Agile Provisioning の追加

Microsoft Entra ID への Agile Provisioning の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Agile Provisioning を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Agile Provisioning**」と入力します。
4. 結果パネルから **[アジャイル プロビジョニング** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Agile Provisioning に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、アジャイル プロビジョニングに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Agile Provisioning の関連ユーザーとの間にリンク関係を確立する必要があります。

Agile Provisioning に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Agile Provisioning の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Agile Provisioning テストユーザーを作成** - B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**アジャイル プロビジョニング**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<CustomerFullyQualifiedName>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerFullyQualifiedName>/web-portal/saml/SSO`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerFullyQualifiedName>/web-portal/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Agile Provisioning クライアント サポート チーム](mailto:support@flexcomlabs.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成**を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Agile Provisioning へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**アジャイル プロビジョニング** を参照します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Agile Provisioning SSO を構成する

**アジャイル プロビジョニング**側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Agile Provisioning サポート チーム](mailto:support@flexcomlabs.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Agile Provisioning テスト ユーザーを作成する

このセクションでは、Agile Provisioning で Britta Simon というユーザーを作成します。 [アジャイル プロビジョニング サポート チーム](mailto:support@flexcomlabs.com)と協力して、アジャイル プロビジョニング プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Agile Provisioning のサインオン URL にリダイレクトされます。
- Agile Provisioning のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したアジャイル プロビジョニングに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [アジャイル プロビジョニング] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したアジャイル プロビジョニングに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/agileworks-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AgileWorks クラウド版を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/agileworks-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-18
- Summary: ユーザーが自分のMicrosoft Entra アカウントでサインインできるように、Microsoft Entra IDと AgileWorks クラウド版の間でシングル サインオンを構成する方法について説明します。

この記事では、AgileWorks クラウド版とMicrosoft Entra IDを統合する方法について説明します。 Microsoft Entra IDと AgileWorks クラウド版を統合すると、次のことができます。

- AgileWorks クラウド版にアクセスできるユーザーをMicrosoft Entra IDで制御できます。
- ユーザーが自分のMicrosoft Entra アカウントを使用して AgileWorks クラウド版に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AgileWorks クラウド版でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AgileWorks クラウド版では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの AgileWorks クラウド版の追加

Microsoft Entra IDへの AgileWorks クラウド版の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AgileWorks クラウド版を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「AgileWorks クラウド版**」と入力します。
4. 結果パネルから **AgileWorks クラウド版** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細については](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)を参照してください。

### AgileWorks クラウド版Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AgileWorks クラウド版Microsoft Entra SSO を構成してテスト>します。 SSO を機能させるには、Microsoft Entra ユーザーと AgileWorks クラウド版の関連ユーザーとの間にリンク関係を確立する必要があります。

AgileWorks クラウド版Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra のテストユーザーを作成** - B.Simon を使用して Microsoft Entra のシングルサインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AgileWorks クラウド版 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AgileWorks クラウド版のテスト ユーザーを作成** - AgileWorks クラウド版で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AgileWorks クラウド版**&gt;**Single sign-on** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、「 `atled.jp`」という値を入力します。

    Note

    既定の識別子の値は `atled.jp`。 管理者がシステム設定でこの値を変更した場合は、代わりにその更新された値を使用します。

    b. [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** | **サイト** |
    | --- | --- |
    | `https://<SUBDOMAIN>.atledcloud.jp/AgileWorks/Broker/PicusSAML` | (ユーザー サイト) |
    | `https://<SUBDOMAIN>.atledcloud.jp/AgileWorks/Broker/EMMASAML` | (管理サイト) |
    | `https://<SUBDOMAIN>.atledcloud.jp/AgileWorks/Broker/MobileSAML` | (モバイル サイト) |
    | `https://<SUBDOMAIN>.atledcloud.jp/AgileWorks/Broker/AppSAML` | (アプリ) |
    | `https://<SUBDOMAIN>.atledcloud.jp/AgileWorks/Broker/GadgetSAML` | (ガジェット) |

    Note

    `<SUBDOMAIN>`の`https://<SUBDOMAIN>.atledcloud.jp`を AgileWorks クラウド版インスタンスのサブドメインに置き換えます。

    c. [ **サインオン URL** ] テキスト ボックスに、既定の (最初の) 応答 URL として設定したのと同じ URL を入力します。

    Note

    この設定は運用環境では省略可能であり、テスト後は空白のままにすることができます。 これは、Microsoft Entra 管理センターの **Test this application** ボタンが正常に動作するためにのみ必要です。 省略すると、[ **このアプリケーションのテスト** ] ボタンが失敗する可能性があります。これは、テストによってリンクにデータが追加され、URL が長くなりすぎて要求が失敗する可能性があるためです。
6. AgileWorks クラウド版アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 **一意のユーザー識別子 (名前 ID)** は **user.userprincipalname** にマップされています。 AgileWorks クラウド版アプリケーションでは、 **一意のユーザー識別子 (名前 ID)** が **ExtractMailPrefix(user.mail)** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 既定の名前 ID 属性マッピングを示す [属性と要求] ページのスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書 (Base64) ダウンロード] リンクが強調表示されている [SAML 署名証明書] セクションのスクリーンショット。]
8. [ **AgileWorks クラウド版のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: コピーする構成 URL を含む [AgileWorks クラウドのセットアップ] セクションのスクリーンショット。]

### Microsoft Entra のテストユーザーを作成して割り当てる。

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

Note

Microsoft Entraテスト ユーザーの作成時にテスト ユーザーの電子メール (user.mail) を設定し、値を記録します。 これは、 **AgileWorks クラウド版のテスト ユーザーの作成** セクションで使用します。

### AgileWorks クラウド版 SSO の構成

1. AgileWorks クラウド版管理サイト ( `https://<SUBDOMAIN>.atledcloud.jp/AgileWorks/Broker/EMMA` など) にサインインします。

    [Image: AgileWorks 管理サイトのスクリーンショット。]
2. **サイト管理**&gt;**サイトの共通設定**&gt;**認証とセキュリティ**&gt;**Login 認証**に移動し、**SAML 認証**という名前の行を選択し、[**編集]** を選択します。

    [Image: ログイン認証設定の一覧のスクリーンショット。]
3. 状態を **[使用可能]** に設定し、[ **保存]** を選択します。

    [Image: SAML 認証の状態が [使用可能] に設定されているスクリーンショット。]
4. [ **SAML** ] タブで、次の設定を構成し、[ **保存]** を選択します。

    | AgileWorksクラウド版 フィールド | 価値 |
    | --- | --- |
    | **リダイレクト認証 URL** | Microsoft Entraからコピーされた **Login URL** |
    | **公開キー証明書ファイル** | Microsoft Entra からダウンロードした**Certificate (Base64)** |

    [Image: SAML タブ証明書のスクリーンショット。]

#### AgileWorks クラウド版のテスト ユーザーの作成

このセクションでは、Microsoft Entra IDのユーザーに対応するユーザーを AgileWorks クラウド版で作成します。

1. AgileWorks クラウド版では、SAML **NameID** を使用してログイン時にユーザーを識別します。 Microsoft Entra IDは、 変換を使用して `ExtractMailPrefix(user.mail)` を送信するように構成され、ユーザーの電子メール アドレスから `@` の前の部分が抽出されます。
2. AgileWorks クラウド版の **ログイン ID と** 同じ値を設定する必要があります。

**例:** | Microsoft Entra ユーザーのメール アドレス | AgileWorksクラウド版ログインID | | :--- | :--- | | `bsimon@example.com` | `bsimon` |

[Image: [ログイン ID] フィールドが強調表示されている AgileWorks クラウド版ユーザー プロファイルのスクリーンショット。]

シングル サインオンを使用する前に、AgileWorks クラウド版でユーザーが作成され、アクティブ化されていることを確認します。

Note

別の属性 (user.userprincipalname や mail など) を使用して SAML NameID を送信するようにMicrosoft Entra ID構成されている場合は、対応するユーザーの AgileWorks ログイン ID が SAML NameID で送信された値と一致していることを確認します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

Note

[ **このアプリケーションのテスト** ] ボタンは、[基本的な SAML 構成] で **サインオン URL** が設定されている場合にのみ正しく機能します。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる AgileWorks クラウド版のサインオン URL にリダイレクトされます。
- AgileWorks クラウド版の応答 URL ( `https://<SUBDOMAIN>.atledcloud.jp/AgileWorks/Broker/PicusSAML` など) に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AgileWorks クラウド版] タイルを選択すると、AgileWorks クラウド版の応答 URL (たとえば、`https://<SUBDOMAIN>.atledcloud.jp/AgileWorks/Broker/PicusSAML`) にリダイレクトされます。 マイ アプリ の詳細については、[「マイ アプリ の概要」](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/agiloft-tutorial"} -->
## Agiloft Contract Management Suite を Microsoft Entra ID でシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/agiloft-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Agiloft Contract Management Suite の間でシングル サインオンを構成する方法について説明します。

この記事では、Agiloft Contract Management Suite と Microsoft Entra ID を統合する方法について説明します。 Agiloft Contract Management Suite を Microsoft Entra ID と統合すると、次のことが可能になります。

- Agiloft Contract Management Suite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Agiloft Contract Management Suite に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Agiloft Contract Management Suite でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Agiloft Contract Management Suite は、**SP および IDP が開始する SSO** に対応しています。
- Agiloft Contract Management Suite では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Agiloft Contract Management Suite を追加する

Microsoft Entra ID への Agiloft Contract Management Suite の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Agiloft Contract Management Suite を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Agiloft Contract Management Suite**」と入力します。
4. 結果パネルから **Agiloft Contract Management Suite** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Agiloft Contract Management Suite に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Agiloft Contract Management Suite に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Agiloft Contract Management Suite の関連ユーザーとの間にリンク関係を確立する必要があります。

Agiloft Contract Management Suite に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Agiloft Contract Management Suite の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Agiloft Contract Management Suite テスト ユーザーの作成** - Agiloft Contract Management Suite で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Agiloft Contract Management Suite**&gt;**シングルサインオン**を開きます。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.agiloft.com/<KB_NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.agiloft.com:443/gui2/spsamlsso?project=<KB_NAME>`

    Note

    識別子の値は、Agiloft SAML 構成エンティティ ID フィールドのエントリと一致する必要があります。 Agiloft のそのフィールドは、次のように更新する必要がある場合があります。

    1. 先頭に https:// を追加します。
    2. URL にスペースが含まれる場合は、それぞれをアンダースコア (\_) に置き換えます。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.agiloft.com:443/gui2/samlssologin.jsp?project=<KB_NAME>`

    Note

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには [、Agiloft Contract Management Suite クライアント サポート チーム](https://www.agiloft.com/support-login.htm) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Agiloft Contract Management Suite のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Agiloft Contract Management Suite SSO を構成する

1. 別の Web ブラウザー ウィンドウで、Agiloft Contract Management Suite 企業サイトに管理者としてログインします。
2. ページの右上隅にある **[設定]** アイコンを選択します。

    [Image: [セットアップ] アイコンが強調表示されているスクリーンショット。]
3. [ **アクセス]** を選択します。

    [Image: [アクセス] 領域が強調表示されているスクリーンショット]
4. [ **Configure SAML 2.0 Single Sign-On]\(SAML 2.0 シングル サインオンの構成**\) ボタンを選択します。
5. ウィザード ダイアログが表示されます。 ダイアログで ID **プロバイダーの詳細** を選択し、次のフィールドに入力します。

    [Image: Agiloft Contract Management Suite の構成]

    a. **IdP エンティティ ID/発行者**テキスト ボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    b。 **[IdP ログイン URL**] ボックスに、**ログイン URL** の値を貼り付けます。

    c. **[IdP ログアウト URL**] ボックスに、**ログアウト URL** の値を貼り付けます。

    d. **Base-64 でエンコードされた証明書**をメモ帳で開き、その内容をクリップボードにコピーして、**IdP Provided X.509 証明書の内容**ボックスに貼り付けます。

    e. **[完了] を選択します**。

#### Agiloft Contract Management Suite のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Agiloft Contract Management Suite に作成します。 Agiloft Contract Management Suite では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Agiloft Contract Management Suite にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Agiloft Contract Management Suite のサインオン URL にリダイレクトされます。
- Agiloft Contract Management Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Agiloft Contract Management Suite に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Agiloft Contract Management Suite] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Agiloft Contract Management Suite に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/aha-tutorial"} -->
## Aha! を設定する Microsoft Entra ID を使用したシングルサインオン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/aha-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Aha! の間にシングル サインオンを構成する方法について説明します。

この記事では、Aha! を統合する方法を学びます。 と Microsoft Entra ID を統合します。 Aha! を 統合する場合 Microsoft Entra ID を使用することで、次のことができます。

- Aha! にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自動的に Aha! にサインインできるようにします。 と Microsoft Entra アカウントを統合します。
- 1 つの中央の場所でアカウントを管理します。

ほう！ は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- ほう！ でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ほう！ では、**SP** initiated SSO がサポートされています
- ほう！ **Just In Time** ユーザープロビジョニングをサポートしています

### アハを追加 ギャラリーから

Aha! の統合を構成するには、 Microsoft Entra ID に構成する場合、Aha! を ギャラリーからマネージド SaaS アプリのリストに追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Aha!**」と入力します。
4. 結果のパネルから **[Aha!]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Aha! 用の Microsoft Entra SSO の構成とテスト

Microsoft Entra SSO を Aha! で構成およびテストする。 **B.Simon** というテスト ユーザーを使用します。 SSO が機能するためには、Microsoft Entra ユーザーと Aha! の関連ユーザーとの間にリンク関係を確立する必要があります。

Aha! に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Aha! SSOの設定**- アプリケーション側でシングル サインオンの設定を行います。
    1. **Aha! のテスト ユーザーの作成** - Aha! で B.Simon に対応するユーザーを作成し、 これは、Microsoft Entra のユーザー表現にリンクされています。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Aha!** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<companyname>.aha.io/session/new`

    b。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<companyname>.aha.io`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Aha! クライアント サポート チーム](https://www.aha.io/company/contact)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [SAML **を使用して単一 Sign-On を設定する**] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML **]** を見つけ、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Aha! のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Aha! を設定する SSO

1. 別の Web ブラウザー ウィンドウで、Aha! にログインしてください。 管理者としてサインインします
2. 上部のメニューで**設定**を選択します。

    [Image: 設定]
3. **アカウント** を選択します。

    [Image: プロファイル]
4. [ **セキュリティ] を選択し、シングル サインオンを選択します**。

    [Image: [セキュリティとシングル サインオン] メニュー オプションが強調表示されているスクリーンショット。]
5. **[シングル サインオン]** セクションで、 **[ID プロバイダー]** として **[SAML2.0]** を選択します。

    [Image: セキュリティとシングル サインオン]
6. **[シングル サインオン]** 構成ページで、次の手順を実行します。

    [Image: シングル サインオン]

    ある。 **[名前]** テキスト ボックスに、構成の名前を入力します。

    b。 **[次を使用した構成]** には **[メタデータ ファイル]** を選択します。

    c. ダウンロードしたメタデータ ファイルをアップロードするには、[ **参照**] を選択します。

    d. **[更新]** を選択します。

#### Aha! の作成 テストユーザー

このセクションでは、B. Simon というユーザーを Aha! に作成します。 ほう！ Just-In-Time ユーザー プロビジョニングはサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Aha! にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [**このアプリケーションをテストする**] を選択すると、このオプションはAha!へリダイレクトされます。 ログインフローを開始できるサインオンURL。
- Aha! に移動 サインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 Aha! を選択する場合 マイアプリのタイルでは、このオプションを選択するとAha!にリダイレクトされます。 サインオン URL。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ahrtemis-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Ahrtemis を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ahrtemis-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ahrtemis 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Ahrtemis と Microsoft Entra ID を統合する方法について説明します。 Ahrtemis と Microsoft Entra ID を統合すると、次のことができます。

- Ahrtemis にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Ahrtemis に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Ahrtemis のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Ahrtemis では、**SP** Initiated SSO がサポートされます。

### ギャラリーからAhrtemisを追加する

Microsoft Entra ID への Ahrtemis の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに Ahrtemis を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Ahrtemis**」と入力します。
4. 結果のパネルから **[Ahrtemis]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Ahrtemis 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Ahrtemis で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Ahrtemis の関連ユーザーとの間にリンク関係を確立する必要があります。

Ahrtemis に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ahrtemis SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Ahrtemis テスト ユーザーの作成** - Ahrtemis に、Microsoft Entra のユーザー表現にリンクされた B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Ahrtemis]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`https://auth.ahrtemis.com/<ID>` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://auth.workos.com/sso/saml/acs/<ID>` のパターンを使用して URL を入力します

    c. [**サインオン URL** テキスト ボックスに、URL: `https://app.ahrtemis.com/version-test/ent_connexion` を入力します。

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Ahrtemis クライアント サポート チーム](mailto:support@ahrtemis.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Ahrtemis SSO の構成

**Ahrtemis** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Ahrtemis サポート チーム](mailto:support@ahrtemis.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Ahrtemis のテスト ユーザーの作成

このセクションでは、Ahrtemis で B.Simon というユーザーを作成します。 [Ahrtemis クライアント サポート チーム](mailto:support@ahrtemis.com)と連携し、Ahrtemis プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Ahrtemis のサインオン URL にリダイレクトされます。
- Ahrtemis のサインオン URL に直接移動し、その URL からログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Ahrtemis] タイルを選択すると、このオプションは Ahrtemis のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/air-tutorial"} -->
## Microsoft Entra ID で Air for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/air-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Air の間でシングル サインオンを構成する方法について説明します。

この記事では、Air と Microsoft Entra ID を統合する方法について説明します。 Air と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Air へのアクセスを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Air に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Air でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Air では、**SP と IDP** によって開始される SSO\* がサポート\*されます。

### ギャラリー\*からの Air の追加

Microsoft Entra ID への Air の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Air を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリー\*から追加する]** セクションで、検索\*ボックスに**「Air」**と入力します。
4. 結果パネルから [**Air**] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Air 用の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Air に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Air の関連ユーザーとの間にリンク関係を確立する必要があります。

Air に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Air SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Air テスト ユーザーの作成** - Air で B.Simon に対応するユーザーを作成し、それを Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Air**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    a. **識別子** テキスト ボックスに、値を入力します: `urn:amazon:cognito:sp:us-east-1_hFBg5izBk`

    b。 [**応答 URL** テキスト ボックスに、URL: `https://auth.air.inc/saml2/idpresponse` を入力します。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://api.air.inc/integrations/saml/login/<CustomerID>`

    手記

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 値を取得するには、Air クライアント サポート チーム  にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Air SSO の構成

1. Air Web サイトに管理者としてログインします。
2. 左上隅にある **ワークスペース** を選択します。
3. **[設定]**&gt;**[セキュリティ]>[ID**]タブに移動し、次の手順を実行します。

    [Image: Air 構成のスクリーンショット]

    a. [**承認済みメール ドメインの管理** テキスト ボックスで、組織の電子メール ドメインを承認済みドメインの一覧に追加して、これらのドメインを持つユーザーが SAML SSO を使用して認証できるようにします。

    b。 **シングル サインオン URL** 値をコピーし、この値を [**基本的な SAML 構成**] セクションの [**サインオン URL**] テキスト ボックスに貼り付けます。

    c. [SAML メタデータ URL] テキスト ボックスに、コピーした **アプリのフェデレーション メタデータ URL** 値を貼り付けます。

    d. [ **SAML SSO を有効にする] を選択します**。

#### Air テストユーザーを作成する

Air Web サイトに管理者としてログインします。

1. 左上隅にある **ワークスペース** を選択します。
2. &gt;] タブに移動し、[メンバーの**追加]** を選択します。
3. メール アドレスを指定し、[ **招待**] を選択します。

    [Image: ユーザー作成のスクリーンショット]

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Air Sign on URL にリダイレクトされます。
- Air のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Air に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Air] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Air に自動的にサインインされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/airbase-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Airbase を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airbase-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-25
- Summary: Microsoft Entra IDから Airbase にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Airbase と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra IDが構成されると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを[Airbase](https://www.airbase.com/)に自動的にプロビジョニングおよびプロビジョニングを解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Airbase でユーザーを作成する。
- Airbaseのアクセスが不要になったユーザーを削除します。
- Microsoft Entra IDと Airbase の間でユーザー属性の同期を維持します。
- Airbase に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airbase-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Airbase のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- Microsoft Entra IDとAirbaseの間で対応付けるデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Airbase を構成する

1. Airbase ポータルにログインします。
2. [ユーザー] セクションに移動します。
3. [HRIS と同期] を選択します。

    People - Users ページから Azure を選択する画面のスクリーンショット
4. HRIS の一覧から Microsoft Entra ID を選択します。
5. [ベース URL] と [API トークン] を書き留めます。

    [Image: テナントの URL とトークンのスクリーンショット。]
6. これらの値はステップ 5.5 で使用します。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Airbase を追加する

Microsoft Entra アプリケーション ギャラリーから Airbase を追加して、Airbase へのプロビジョニングの管理を開始します。 以前に SSO 用に Airbase を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Airbase への自動ユーザー プロビジョニングを構成する

Microsoft Entraのプロビジョニングサービスを構成して、Microsoft Entra IDのユーザー割り当てに基づきTestAppのユーザーを作成、更新、無効化する手順をこのセクションで説明します。

#### Microsoft Entra IDで Airbase の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Airbase**] を選択します。

    [Image: アプリケーションの一覧の Airbase リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Airbase テナントの URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Airbase に接続できることを確認します。 接続に失敗した場合は、Airbase アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Airbase に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Airbase のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Airbase API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Airbase で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |  |
    | urn:ietf:params:scim:schemas:extension:airbase:2.0:User:accountingPolicy | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:airbase:2.0:User:subsidiary | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:airbase:2.0:User:role | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/airbase-tutorial"} -->
## Microsoft Entra ID で Airbase for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airbase-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Airbase の間のシングル サインオンを構成する方法について説明します。

この記事では、Airbase と Microsoft Entra ID を統合する方法について説明します。 管理と会計業務を効率的に拡張する必要がある今日の財務チームに、より高度な管理、可視性、自動化が提供されるように設計されたオールインワンの支出管理プラットフォームです。 Airbase を Microsoft Entra ID と統合すると、次のことが可能になります。

- Airbase へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Airbase に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Airbase に対する Microsoft Entra のシングル サインオンをテスト環境で構成およびテストする。 Airbase では、**SP** と **IDP** によって開始されるシングル サインオンがどちらもサポートされています。 Airbase では、 [自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airbase-provisioning-tutorial)もサポートされています。

### [前提条件]

Airbase を Microsoft Entra ID と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Airbase でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Airbase アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Airbase を追加する

Microsoft Entra アプリケーション ギャラリーから Airbase を追加して、Airbase でのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Airbase**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://auth.airbase.io/<ID>`

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://auth.airbase.io/login/callback?connection=<ID>` |
    | `https://auth.workos.com/sso/saml/acs/<ID>` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.airbase.io`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Airbase サポート チーム](mailto:integrations@airbase.io) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Airbase SSO を構成する

**Airbase** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Airbase サポート チーム](mailto:integrations@airbase.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Airbase テスト ユーザーを作成する

このセクションでは、Airbase SSO で Britta Simon というユーザーを作成します。 [Airbase サポート チーム](mailto:integrations@airbase.io)と協力して、Airbase SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Airbase のサインオン URL にリダイレクトされます。
- Airbase のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Airbase に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Airbase] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Airbase に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/airstack-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Airstack を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airstack-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-25
- Summary: ユーザー アカウントを Airstack に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Airstack で実行する手順を示し、Microsoft Entra IDを構成して、ユーザーやグループを Airstack に自動的にプロビジョニングおよびプロビジョニング解除することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

注

Airstack では、有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Airstack テナント。
- Admin アクセス許可がある Airstack のユーザー アカウント。

### Airstack へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリにaccessを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Airstack へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定する必要があります。 決定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Airstack に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを Airstack に割り当てる際の重要なヒント

- 自動ユーザー プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを Airstack に割り当てることをお勧めします。 後で追加のユーザーやグループを割り当てることができます。
- Airstack にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **Default Access** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニングのために Airstack を設定する

1. Airstack 管理コンソールにサインインします。 **[設定]** に移動します。

    [Image: Airstack 管理コンソール]
2. 画面の左側にあるメニューの **Azure Config** に移動します。

    [Image: Airstack に SCIM を追加]
3. [ **生成** ] ボタンを選択します。 Azure の**Secret トークン**をコピーしてください。 この値は、Airstack アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

    [Image: Airstack Create Token]

### ギャラリーからの Airstack の追加

Microsoft Entra IDを使用して自動ユーザー プロビジョニング用に Airstack を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Airstack を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Airstack を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションに「**Airstack」**と入力し、検索ボックスで **[Airstack**] を選択します。
4. 結果パネルから **Airstack** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の「Airstack」]

### Airstack への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、Airstackでユーザーやグループを作成、更新、無効化するようにMicrosoft Entraプロビジョニングサービスを構成する手順をガイドします。

ヒント

Airstack シングル サインオンに関する記事に記載されている手順に従って、Airstack で SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airstack-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra IDで Airstack の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で [ **Airstack**] を選択します。

    [Image: アプリケーションの一覧の Airstack リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Airstack テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Airstack に接続できることを確認します。 接続に失敗した場合は、Airstack アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute Mapping** セクションで、Microsoft Entra IDから Airstack に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Airstack のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Airstack ユーザー属性]
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/airtable-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Airtable を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airtable-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-25
- Summary: ユーザー アカウントをMicrosoft Entra IDから Airtable に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Airtable と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra IDを構成すると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループが[Airtable](https://www.airtable.com)に自動的にプロビジョニングおよび解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Airtable でユーザーを作成します。
- Airtableのユーザーは、アクセスが不要になった場合に削除します。
- Microsoft Entra IDと Airtable の間でユーザー属性の同期を維持します。
- Airtable にグループとグループ メンバーシップをプロビジョニングする。
- Airtable に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airtable-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Airtable テナント。
- 管理者アクセス許可がある Airtable のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- Microsoft Entra IDとAirtableの間でマッピングするデータを決定します。

### 手順 2: Airtable Personal Access トークンを作成して、Microsoft Entra IDを使用してプロビジョニングを承認します。

1. [Airtable Developer Hub](https://airtable.com) に管理者ユーザーとしてログインし、`https://airtable.com/create/tokens`に移動します。
2. 左側のナビゲーション バーから [個人用Access トークン] を選択します。

    [Image: 個人用Accessトークンの選択のスクリーンショット.]
3. "AzureAdScimProvisioning" などの覚えやすい名前で新しいトークンを作成します。
4. "enterprise.scim.usersAndGroups:manage" スコープを追加します。

    [Image: エンタープライズのSCIMスコープ追加のスクリーンショット。]
5. [トークンの作成] を選択し、次の **手順 5** で使用するために結果のトークンをコピーします。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Airtable を追加する

Microsoft Entra アプリケーション ギャラリーから Airtable を追加して、Airtable へのプロビジョニングの管理を開始します。 SSO 用に Airtable を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Airtable への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づき、TestApp においてユーザーやグループを作成、更新、または無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Airtable の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Airtable** を選択します。

    [Image: アプリケーションの一覧の Airtable リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Airtable テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Airtable に接続できることを確認します。 接続に失敗した場合は、Airtable アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Airtable に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Airtable のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Airtable API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Airtable で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | name.formatted | 糸 |  |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |  |
    | externalId | 糸 |  |  |
    | ニックネーム | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager.value | 糸 |  |  |
    | アドレス[type eq "ホーム"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプ eq "home"].ストリートアドレス | 糸 |  |  |
    | addresses[type eq "home"].locality（住所[タイプ＝「自宅」].地域） | 糸 |  |  |
    | アドレス[type eq "home"].リージョン | 糸 |  |  |
    | アドレス[タイプ eq "home"].郵便番号 | 糸 |  |  |
    | N/A | 糸 |  |  |
    | アドレス[タイプ eq "other"].フォーマット済み | 糸 |  |  |
    | addresses[type eq "その他"].streetAddress | 糸 |  |  |
    | 住所[タイプ eq "その他"].市区町村 | 糸 |  |  |
    | addresses[type eq "その他"].地域 | 糸 |  |  |
    | 住所[タイプ eq "その他"].郵便番号 | 糸 |  |  |
    | 住所[タイプ eq "その他"].国 | 糸 |  |  |
    | メール[タイプ eq "自宅"].値 | 糸 |  |  |
    | emails[タイプ eq "その他"].値 | 糸 |  |  |
    | ロケール | 糸 |  |  |
    | name.honorificPrefix | 糸 |  |  |
    | name.honorificSuffix | 糸 |  |  |
    | name.middleName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | 電話番号[タイプ eq "home"].値 | 糸 |  |  |
    | phoneNumbers[種類 eq "その他"].value | 糸 |  |  |
    | 電話番号[タイプイコール "ポケベル"].値 | 糸 |  |  |
    | タイムゾーン | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
    | ユーザータイプ | 糸 |  |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Airtable に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Airtable のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Airtable で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、組織内でより広範に展開する前に、少数のユーザーとの同期を検証します。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/airtable-tutorial"} -->
## Microsoft Entra ID で Airtable for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airtable-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Airtable の間のシングル サインオンを構成する方法について説明します。

この記事では、Airtable と Microsoft Entra ID を統合する方法について説明します。 Airtable を Microsoft Entra ID と統合すると、次のことが可能になります。

- Airtable にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Airtable に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Airtable でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Airtable では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Airtable では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Airtable の追加

Microsoft Entra ID への Airtable の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Airtable を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Airtable**」と入力します。
4. 結果のパネルから **[Airtable]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Airtable に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Airtable に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Airtable の関連ユーザーとの間にリンク関係を確立する必要があります。

Airtable に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Airtable の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Airtable テストユーザーの作成** - Airtable で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Airtable**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://airtable.com/sso/login`
7. **保存** を選択します。
8. Airtable アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Airtable アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:2.5.4.4 | ユーザーの名字 |
    | urn:oid:2.5.4.42 | User.givenname |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Airtable のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Airtable の SSO の構成

[リンク](https://support.airtable.com/docs/configuring-sso-with-azure-ad) に記載されている手順に従って、**Airtable** 側でシングル サインオンを構成します。

#### Airtable のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Airtable に作成します。 Airtable では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Airtable にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Airtable のサインオン URL にリダイレクトされます。
- Airtable のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Airtable に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Airtable] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Airtable に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/airwatch-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AirWatch を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/airwatch-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AirWatch の間のシングル サインオンを構成する方法について説明します。

この記事では、AirWatch と Microsoft Entra ID を統合する方法について説明します。 AirWatch を Microsoft Entra ID と統合すると、次のことが可能になります。

- AirWatch にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで AirWatch に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AirWatch でのシングル サインオン (SSO) が有効なサブスクリプション。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AirWatch では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの AirWatch の追加

Microsoft Entra ID への AirWatch の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AirWatch を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**AirWatch**」と入力します。
4. 結果のパネルから **[AirWatch]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AirWatch に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AirWatch に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと AirWatch の関連ユーザーとの間にリンク関係を確立する必要があります。

AirWatch に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AirWatch SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AirWatch のテストユーザーの作成** - AirWatch で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AirWatch** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** ページで、次のフィールドの値を入力します。

    a. **[識別子 (エンティティ ID)]** ボックスに、`AirWatch` という値を入力します。

    b。 **[応答 URL]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://<SUBDOMAIN>.awmdm.com/<COMPANY_CODE>` |
    | `https://<SUBDOMAIN>.airwatchportals.com/<COMPANY_CODE>` |
    |  |

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<subdomain>.awmdm.com/AirWatch/Login?gid=companycode`

    Note

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[AirWatch クライアント サポート チーム](https://customerconnect.omnissa.com/home)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. AirWatch アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションには、次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **[ユーザー属性]** セクションで管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: 画像]
7. **[ユーザー属性]** ダイアログの **[ユーザーの要求]** セクションで、**編集アイコン**を使用して要求を編集するか、 **[新しい要求の追加]** を使用して要求を追加することで、上の図のように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザー識別子 | user.userprincipalname |
    |  |  |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    b。 **[名前]** ボックスに、その行に対して表示される属性名を入力します。

    c. **[名前空間]** は空白のままにします。

    d. [ソース] として **[属性]** を選択します。

    e. **[ソース属性]** の一覧から、その行に表示される属性値を入力します。

    f. **[OK]** を選択します。

    g. **保存**を選択します。
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、メタデータ XML をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
9. **[AirWatch の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AirWatch SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として AirWatch 企業サイトにサインインします。
2. 左側のナビゲーション ウィンドウで、**[Groups] (グループ) & [Settings] (設定)** を選択し、**[All Settings] (すべての設定)** を選択します。
3. 次に、**[System] (システム) &gt; [Enterprise Integration] (エンタープライズ統合) &gt; [Directory Services] (ディレクトリ サービス)** に移動します。
4. **[User] (ユーザー)** タブを選択し、**[Base DN] (ベース DN)** フィールドに `domain name` を入力し、**[Save] (保存)** を選択します。
5. **[Group] (グループ)** タブを選択し、**[Base DN] (ベース DN)** フィールドに `domain name` を入力し、**[Save] (保存)** を選択します。
6. **[Server] (サーバー)** タブを選択し、次の手順を実行します。

    1. **[Directory Type]** として **[None]** を選択します。
    2. **[Use SAML For Authentication] (認証に SAML を使用する)** オプションを有効にします。
    3. [ **Import Identity Provider Settings]\(ID プロバイダーの設定のインポート** \) を選択し、[ **アップロード** ] を選択して、上記の手順 4 でダウンロードした XML ファイルをアップロードします。
7. **[Request]** セクションで、次の手順に従います。

    1. **[Request Binding Type]** として **[POST]** を選択します。
    2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**AirWatch** にブラウズします。
    3. **[AirWatch 構成]** セクションで **[AirWatch の構成]** を選択して、**[サインオンの構成]** ウィンドウを開きます。
    4. **[クイック リファレンス]** セクションから **[SAML シングル サインオン サービス URL]** をコピーし、**[ID プロバイダーのシングル サインオン URL]** テキストボックスに貼り付けます。
    5. **[NameID Format]** として **[Email Address]** を選択します。
    6. **保存**を選択します。
8. **[Response] (応答)** セクションの **[Response Binding Type] (応答バインドの種類)** で、**[Post] (投稿)** を選択します。
9. **[User] (ユーザー)** タブをもう一度クリックします。
10. **[Show Advanced] (詳細設定の表示)** を選択して、ユーザーの詳細設定を表示します。
11. **[Attribute]** セクションで、次の手順に従います。

    [Image: 属性]

    1. **[オブジェクト識別子]** ボックスに「`http://schemas.microsoft.com/identity/claims/objectidentifier`」と入力します。
    2. **[ユーザー名]** ボックスに「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」と入力します。
    3. **[表示名]** ボックスに「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`」と入力します。
    4. **[名]** ボックスに「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`」と入力します。
    5. **[姓]** ボックスに「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`」と入力します。
    6. **[電子メール]** ボックスに「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」と入力します。
    7. **保存**を選択します。

#### AirWatch のテスト ユーザーを作成する

Microsoft Entra ユーザーが AirWatch にサインインできるようにするには、そのユーザーを AirWatch にプロビジョニングする必要があります。 AirWatch の場合、プロビジョニングは手動で行います。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. **AirWatch** 企業サイトに管理者としてサインインします。
2. 左側のナビゲーション ウィンドウで、[ **アカウント**] を選択し、[ユーザー] を選択 **します**。
3. [ **ユーザー** ] メニューの **[リスト ビュー**] を選択し、[ **追加] &gt; [ユーザーの追加]** を選択します。
4. **[Add / Edit User]** ダイアログで、次の手順を実行します。

    a. 関連するテキストボックスに、プロビジョニングする有効な Microsoft Entra アカウントの **[ユーザー名]** 、 **[パスワード]** 、 **[パスワードの確認]** 、 **[名]** 、 **[姓]** 、 **[メール アドレス]** を入力します。

    b。 **保存**を選択します。

Note

他の AirWatch ユーザー アカウント作成ツールや、AirWatch から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AirWatch のサインオン URL にリダイレクトされます。
- AirWatch のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [AirWatch] タイルを選択すると、このオプションは AirWatch のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/akamai-enterprise-application-access-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Akamai Enterprise Application Accessを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/akamai-enterprise-application-access-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-25
- Summary: Microsoft Entra IDから Akamai Enterprise Application Accessにユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Akamai Enterprise Application Access と自動ユーザー プロビジョニングを構成するためにMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成されると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Akamai Enterprise Application Access](https://www.akamai.com) に自動的にプロビジョニングおよび削除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Akamai Enterprise Application Accessでユーザーを作成します。
- accessが不要になった場合は、Akamai Enterprise Application Accessのユーザーを削除します。
- Microsoft Entra IDと Akamai Enterprise Application Accessの間でユーザー属性の同期を維持します。
- Akamai Enterprise Application Access でグループとグループ メンバーシップをプロビジョニングする
- [Akami Enterprise Application Accessへのシングルサインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/akamai-tutorial)（推奨）。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- Microsoft Entra ID のユーザー アカウントで、プロビジョニングを構成するための [permission](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持っている ([Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud Application Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [Application Owner](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications) など)。
- Akamai [Enterprise Application Access](https://www.akamai.com/products/enterprise-application-access) を持つ管理者アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとAkamai Enterprise Application Accessの間でマップするデータを特定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Akamai Enterprise アプリケーション Accessを構成する

Akamai Enterprise Center でAzure種類の SCIM ディレクトリを構成し、SCIM ベース URL とプロビジョニング キーを保存します。

1. [Akamai Enterprise Center](https://control.akamai.com/apps/zt-ui/#/identity/directories) にサインインします。
2. メニューで、**Application Access &gt; Identity & Users &gt; Directories** に移動します。
3. [ **新しいディレクトリの追加 ]** (+) を選択します。
4. ディレクトリの名前と説明を入力します。
5. Directoryの種類SCIMを選択し、SCIM スキーマAzureを選択します。
6. [ **新しいディレクトリの追加] を選択します**。
7. 新しいディレクトリ **設定**&gt;**General** を開き、 **SCIM ベース URL をコピーします**。 STEP 4 で Azure SCIM プロビジョニングのためにそれを保存します。
8. **[設定]**&gt;**一般**で**プロビジョニングキーの作成**を選択します。
9. キーの名前と説明を入力します。
10. クリップボードへのコピー アイコンを選択して **プロビジョニング キー** をコピーします。 Azure SCIM プロビジョニング用に手順 5 で保存します。
11. **[ログイン設定属性**] で、**ユーザー プリンシパル名** (既定) または**電子メール**を選択して、ユーザーにログインする方法を選択します。
12. **保存**を選びます。 新しい SCIM ディレクトリが **Identity & Users**&gt;**Directories** のディレクトリリストに表示されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Akamai Enterprise アプリケーション Accessを追加する

Microsoft Entra アプリケーション ギャラリーから Akamai Enterprise Application Accessを追加して、Akamai Enterprise Application Access へのプロビジョニングの管理を開始します。 SSO 用に Akamai Enterprise Application Accessを以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Akamai Enterprise Application Accessへの自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づき、TestApp においてユーザーやグループを作成、更新、または無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Akamai Enterprise Application Accessの自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Akamai Enterprise Application Access** を選択します。

    アプリケーションリストの中の Akamai Enterprise Application Access リンクのスクリーンショット。
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **テナント URL** フィールドに、Akamai Enterprise Application Access テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択してMicrosoft Entra ID Akamai Enterprise Application Accessに接続できることを確認します。 接続に失敗した場合は、Akamai Enterprise Application Access アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. Microsoft Entra IDから Akamai Enterprise Application Accessに同期されるユーザー属性については、「**Attribute-Mapping**」セクションを参照してください。 **Matching** プロパティとして選択されている属性は、更新操作で Akamai Enterprise Application Accessのユーザー アカウントとの照合に使用されます。 [照合ターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Akamai Enterprise Application Access API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Akamai Enterprise Application Accessで必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | externalId | 糸 |  |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Akamai Enterprise Application Accessに同期されるグループ属性を確認します。 **Matching** プロパティとして選択されている属性は、更新操作の Akamai Enterprise Application Accessのグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Akamai Enterprise Application Accessで必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  |  |
    | members | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/akamai-tutorial"} -->
## Microsoft Entra ID で Akamai for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/akamai-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Akamai 間にシングル サインオンを構成する方法について説明します。

この記事では、Akamai と Microsoft Entra ID を統合する方法について説明します。 Akamai と Microsoft Entra ID を統合すると、次のことができます。

- Akamai にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Akamai に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

Microsoft Entra ID と Akamai Enterprise Application Access の統合により、クラウドまたはオンプレミスでホストされているレガシ アプリケーションにシームレスにアクセスできます。 統合ソリューションでは、Microsoft [Entra 条件付きアクセス、Microsoft Entra](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ID Protection、[Microsoft Entra ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) などの Microsoft [Entra ID](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview) のすべての最新機能を利用して、アプリの変更やエージェントのインストールなしでレガシ アプリケーションにアクセスできます。

次の図では、Akamai EAA がハイブリッド セキュリティで保護されたアクセスの広範なシナリオに適合する場所について説明します。

[Image: Akamai EAA は安全なハイブリッド アクセスの広範なシナリオに適しています]

### キー認証のシナリオ

先進認証プロトコル (Open ID Connect、SAML、WS-Fed など) に対する Azure Active Directory のネイティブ統合のサポートとは別に、Akamai EAA は、Microsoft Entra ID を使用することで、内部と外部の両方のアクセスに関してレガシベース認証アプリの安全なアクセスを拡張し、それらのアプリケーションへの最新のシナリオ (パスワードレス アクセスなど) を実現します。 これには、次のものが含まれます。

- ヘッダーベースの認証アプリ
- リモート デスクトップ
- SSH (Secure Shell)
- Kerberos 認証アプリ
- VNC (仮想ネットワーク コンピューティング)
- 匿名認証または非ビルトイン認証アプリ
- NTLM 認証アプリ (ユーザーに対する二重プロンプトでの保護)
- フォームベースのアプリケーション (ユーザーに対する二重プロンプトでの保護)

### 統合シナリオ

Microsoft と Akamai EAA のパートナーシップにより、ビジネス要件に基づく複数の統合シナリオがサポートされるため、柔軟にビジネス要件を満たすことができます。 これらの統合シナリオを使用して、すべてのアプリケーションに 0 日間のカバレッジを提供し、適切なポリシー分類を徐々に分類して構成することができます。

##### 統合シナリオ 1

Akamai EAA が Microsoft Entra ID 上で単一のアプリケーションとして構成されます。 管理者はそのアプリケーション上で条件付きアクセス ポリシーを構成することができ、条件が満たされると、ユーザーは Akamai EAA ポータルにアクセスできます。

**長所**:

- IDP の構成が 1 回だけで済む。

**短所**:

- ユーザーは最終的に 2 つのアプリケーション ポータルを持つことになる。
- すべてのアプリケーションを対象とする、共通する 1 つの条件付きアクセス ポリシー。

[Image: 統合シナリオ 1]

##### 統合シナリオ 2

Akamai EAA アプリケーションが Azure portal 上で個別に設定されます。 管理者は、アプリケーションで個別の条件付きアクセス ポリシーを構成でき、条件が満たされたら、ユーザーを特定のアプリケーションに直接リダイレクトできます。

**長所**:

- 個々の条件付きアクセス ポリシーを定義できます。
- すべてのアプリが O365 のワッフルと myApps.microsoft.com パネルに表示される。

**短所**:

- 複数の IDP を構成する必要がある。

[Image: 統合シナリオ 2]

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Akamai でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Akamai では、IDP Initiated SSO がサポートされます。

重要

次の手順のすべてのセットアップ手順は、 **統合シナリオ 1** と **シナリオ 2** で同じです。 **統合シナリオ 2** では、Akamai EAA で個々の IDP を設定する必要があり、アプリケーション URL を指すように認証構成 URL プロパティを変更する必要があります。

[Image: Akamai Enterprise Application Access の [AZURESSO-SP] の [General](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般) タブのスクリーンショット。[Authentication configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証構成) の [URL] フィールドが強調表示されている。]

### ギャラリーからの Akamai の追加

Microsoft Entra ID への Akamai の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Akamai を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Akamai**」と入力します。
4. 結果のパネルから **[Akamai]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Akamai 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Akamai に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Akamai の関連ユーザーとの間にリンク関係を確立する必要があります。

Akamai との Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Akamai の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **IDP の設定**
    - **ヘッダー ベースの認証**
    - **リモート デスクトップ**
    - **SSH**
    - **Kerberos 認証**
    - **Akamai テスト ユーザーをの作成** - Akamai で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Akamai**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    a. **[識別子]** ボックスに、`https://<Yourapp>.login.go.akamai-access.com/saml/sp/response` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https:// <Yourapp>.login.go.akamai-access.com/saml/sp/response` のパターンを使用して URL を入力します

    注意

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 この値を取得するには、[Akamai クライアント サポート チーム](https://www.akamai.com/us/en/contact-us/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Set up Akamai](Akamai の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Akamai の SSO の構成

#### IDP の設定

**AKAMAI EAA IDP の構成**

1. **Akamai Enterprise Application Access** コンソールにサインインします。
2. **Akamai EAA コンソール**で、[**ID**&gt;**Identity プロバイダー**] を選択し、[**ID プロバイダーの追加] を選択します**。

    [Image: Akamai EAA コンソールの [Identity Providers](ID プロバイダー) ウィンドウのスクリーンショット。[Identity](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ID) メニューの [Identity Providers](ID プロバイダー) を選択し、[Add Identity Provider](ID プロバイダーの追加) を選択する。]
3. **[新しい ID プロバイダーの作成]** で次の手順を実行します。

    a. **一意の名前**を指定します。

    b。 **[サード パーティの SAML**] を選択し、[**CREATE Identity Provider and Configure]\(ID プロバイダーの作成と構成\) を選択します**。

#### Akamai の全般設定を構成する

**[General]** タブで、次の情報を入力します。

1. **ID インターセプト** - ドメインの名前を指定します (SP ベース URL は Microsoft Entra 構成に使用されます)。

    注意

    独自のカスタム ドメイン (DNS エントリと証明書が必要) を選択できます。 この例では、Akamai ドメインを使用します。
2. **[Akamai Cloud Zone](Akamai クラウド ゾーン)** - 適切なクラウド ゾーンを選択します。
3. **[Certificate Validation](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書の検証)** - Akamai ドキュメントをチェックします (省略可)。

#### Akamai 認証設定を構成する

[ **認証構成]** セクションで、次の SAML 設定を構成します。

1. [URL] - ID インターセプトと同じ URL を指定します (認証後、ユーザーはここにリダイレクトされます)。
2. [Logout URL](ログアウト URL): ログアウト URL を更新します。
3. SAML リクエストに署名する: 既定では未チェックです。
4. IDP メタデータ ファイルには、Microsoft Entra ID コンソールでアプリケーションを追加します。

    [Image: Akamai EAA コンソールの [Authentication configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証構成) のスクリーンショット。[URL]、[Logout URL](ログアウト URL)、[Sign SAML Request](SAML 要求に署名する)、[IDP Metadata File](IDP メタデータ ファイル) の各設定が表示されている。]

#### Akamai セッション設定を構成する

設定は既定値のままにします。

[Image: Akamai EAA コンソールの [Session settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セッション設定) ダイアログのスクリーンショット。]

#### Akamai でディレクトリを構成する

**[Directories]** タブで、ディレクトリの構成をスキップします。

#### Akamai サインイン UI をカスタマイズする

IDP にカスタマイズを追加できます。 **[Customization]** タブには、**[Customize UI]**、**[Language settings]**、**[Themes]** の設定があります。

#### Akamai の高度な SSO 設定を構成する

**[Advanced settings]** タブで、既定値を受け入れます。 詳細については、 [Akamai EAA のドキュメント](https://techdocs.akamai.com/eaa) を参照してください。

#### Akamai 構成をデプロイする

前の設定の構成が完了したら、ID プロバイダーをデプロイします。

1. [ **デプロイ** ] タブで、[ID プロバイダーのデプロイ] を選択します。
2. 展開が正常に実行されたことを確認します。

#### ヘッダー ベースの認証

Akamai ヘッダー ベースの認証

1. アプリケーションの追加ウィザードから **[Custom HTTP](カスタム HTTP)** を選択します。

    [Image: Akamai EAA コンソールのアプリケーションの追加ウィザードのスクリーンショット。[Access Apps](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリへのアクセス) セクションに [Custom HTTP](カスタム HTTP) が表示されている。]
2. **[Application Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーション名)** と **[Description](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/説明)** を入力します。

    [Image: [Custom HTTP App](カスタム HTTP アプリ) ダイアログのスクリーンショット。[Application Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーション名) と [Description](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/説明) の各設定が表示されている。]

    [Image: Akamai EAA コンソールの [General](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般) タブのスクリーンショット。MYHEADERAPP の全般設定が表示されている。]

    [Image: Akamai EAA コンソールのスクリーンショット。[Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書) と [Location](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/場所) の各設定が表示されている。]

##### 認証

1. **[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証)** タブを選択します。

    [Image: [認証] タブが選択された Akamai EAA コンソールのスクリーンショット。]
2. **ID プロバイダーの割り当て** を選択します。

##### サービス

[保存] を選択し、[認証に移動] を選択します。

[Image: Akamai EAA コンソールの MYHEADERAPP の [Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サービス) タブのスクリーンショット。右下隅に [Save and go to Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存して詳細設定に移動) ボタンが表示されている。]

##### 詳細設定

[詳細設定] で、カスタム ヘッダー マッピングを構成し、デプロイを続行します。

1. **[Customer HTTP Headers](カスタマー HTTP ヘッダー)** で、**カスタマー ヘッダー**と **SAML 属性**を指定します。

    [Image: Akamai EAA コンソールの [Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) タブのスクリーンショット。[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) の [SSO Logged URL](SSO ログ URL) フィールドが強調表示されている。]
2. [ **保存] を選択し、[デプロイ] ボタンに移動** します。

    [Image: Akamai EAA コンソールの [Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) タブのスクリーンショット。右下隅に [Save and go to Deployment](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存してデプロイに移動) ボタンが表示されている。]

##### アプリケーションのデプロイ

構成が完了したら、アプリケーションをデプロイします。

1. [ **アプリケーションのデプロイ]** ボタンを選択します。

    [Image: Akamai EAA コンソールの [Deployment](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デプロイ) タブのスクリーンショット。[Deploy Application](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーションのデプロイ) ボタンが表示されている。]
2. アプリケーションが正しくデプロイされたことを確認します。

    [Image: Akamai EAA コンソールの [Deployment](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デプロイ) タブのスクリーンショット。[Application status](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーションの状態) に、]
3. エンドユーザー エクスペリエンス。

    [Image: myapps.microsoft.com の開始画面のスクリーンショット。背景画像と [サインイン] ダイアログが表示されている。]

    [Image: [アプリ] ウィンドウの一部を表示するスクリーンショット。[アドイン]、[HRWEB]、[Akamai - CorpApps]、[経費]、[グループ]、[アクセス レビュー] のアイコンが表示されている。]
4. 条件付きアクセス。

    [Image: MyHeaderApp のアイコンを表示する [アプリケーション] 画面のスクリーンショット。]

##### リモート デスクトップ

Akamai EAA でリモート デスクトップ アプリケーションを構成するには、次の手順に従います。

1. ADD Applications ウィザードから **[RDP]** を選択します。

    [Image: [Access Apps] セクションのアプリ一覧に RDP が表示されている Akamai EAA コンソールの [Add Applications] ウィザードのスクリーンショット。]
2. **[Application Name]** に *SecretRDPApp* などの名前を入力します。
3. **Description** を選択します。たとえば、*Microsoft Entra 条件付きアクセス を使用して RDP セッションを保護する* などです。
4. これを処理するコネクタを指定します。

    [Image: Akamai EAA コンソールのスクリーンショット。[Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書) と [Location](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/場所) の各設定が表示されている。関連するコネクタが USWST-CON1 に設定されている。]

##### 認証

[ **認証** ] タブで [ **保存] を選択し、[サービス] に移動**します。

##### サービス

[ **保存] を選択し、[詳細設定] に移動します**。

[Image: Akamai EAA コンソールの SECRETRDPAPP の [Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サービス) タブのスクリーンショット。右下隅に [Save and go to Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存して詳細設定に移動) ボタンが表示されている。]

##### 詳細設定

1. [ **保存] を選択し、[デプロイ] に移動します**。

    [Image: Akamai EAA コンソールの SECRETRDPAPP の [Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) タブのスクリーンショット。[Remote desktop configuration](リモート デスクトップ構成) の設定が表示されている。]

    [Image: Akamai EAA コンソールの SECRETRDPAPP の [Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) タブのスクリーンショット。[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) と [Health check configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/正常性チェック構成) の各設定が表示されている。]

    [Image: Akamai EAA コンソールの SECRETRDPAPP の [Custom HTTP headers](カスタム HTTP ヘッダー) 設定のスクリーンショット。右下隅に [Save and go to Deployment](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存してデプロイに移動) ボタンが表示されている。]
2. エンド ユーザー エクスペリエンス

    [Image: [myapps.microsoft.com] ウィンドウのスクリーンショット。背景画像と [サインイン] ダイアログが表示されている。]

    [Image: myapps.microsoft.com の [アプリ] ウィンドウのスクリーンショット。[アドイン]、[HRWEB]、[Akamai - CorpApps]、[経費]、[グループ]、[アクセス レビュー] のアイコンが表示されている。]
3. 条件付きアクセス

    [Image: MyHeaderApp と SecretRDPApp のアイコンを表示する [アプリケーション] 画面のスクリーンショット。]

    [Image: 汎用ユーザーのアイコンが示されている Windows Server 2012 RS 画面のスクリーンショット。 管理者、user0、user1 のアイコンが、それらのユーザーがサインイン済みであることを示している。]
4. または、RDP アプリケーションの URL を直接入力することもできます。

##### SSH

Akamai EAA 経由で SSH アクセスを構成するには、次の手順を実行します。

1. [Add Applications](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーションの追加) に移動し、 **[SSH]** を選択します。

    [Image: [アプリへのアクセス] セクションで一連のアプリの中で [SSH] が記載されている状態を示す Akamai EAA コンソールにあるアプリケーションの追加ウィザードのスクリーンショット。]
2. **[Application Name]** と **[Description]** を入力します (*Microsoft Entra modern authentication to SSH* など)。
3. アプリケーション ID を構成します。

    a. 名前と説明を指定します。

    b。 アプリケーション サーバーの IP (または FQDN) と SSH のポートを指定します。

    c. SSH ユーザー名とパスフレーズを指定します (Akamai EAA を確認してください)。

    d. 外部ホスト名を指定します。

    e. コネクタの場所を指定し、コネクタを選択します。

##### 認証

[ **認証** ] タブで [ **保存] を選択し、[サービス] に移動**します。

##### サービス

[ **保存] を選択し、[詳細設定] に移動します**。

[Image: Akamai EAA コンソールの SSH-SECURE の [Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サービス) タブのスクリーンショット。右下隅に [Save and go to Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存して詳細設定に移動) ボタンが表示されている。]

##### 詳細設定

[保存してデプロイ] を選択します。

[Image: Akamai EAA コンソールの SSH-SECURE の [Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) タブのスクリーンショット。[Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) と [Health check configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/正常性チェック構成) の各設定が表示されている。]

[Image: Akamai EAA コンソールの SSH-SECURE の [Custom HTTP headers](カスタム HTTP ヘッダー) のスクリーンショット。右下隅に [Save and go to Deployment](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存してデプロイに移動) ボタンが表示されている。]

##### デプロイ

SSH アプリケーションの構成が完了したら、デプロイします。

1. [ **アプリケーションのデプロイ] を選択します**。

    [Image: Akamai EAA コンソールの SSH-SECURE の [Deployment](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デプロイ) タブのスクリーンショット。[Deploy Application](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーションのデプロイ) ボタンが表示されている。]
2. エンド ユーザー エクスペリエンス

    [Image: [myapps.microsoft.com] ウィンドウの [サインイン] ダイアログのスクリーンショット。]

    [Image: myapps.microsoft.com の [アプリ] ウィンドウのスクリーンショット。[アドイン]、[HRWEB]、[Akamai - CorpApps]、[経費]、[グループ]、[アクセス レビュー] のアイコンが表示されている。]
3. 条件付きアクセス

    [Image: MyHeaderApp、SSH Secure、SecretRDPApp のアイコンを表示する [アプリケーション] 画面のスクリーンショット。]

    [Image: ssh-secure-go.akamai-access.com のコマンド ウィンドウのスクリーンショット。[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード) プロンプトが表示されている。]

    [Image: ssh-secure-go.akamai-access.com のコマンド ウィンドウのスクリーンショット。アプリケーションに関する情報のほか、コマンドのプロンプトが表示されている。]

#### Kerberos 認証

次の例では、 `http://frp-app1.superdemo.live` で内部 Web サーバーを発行し、KCD を使用して SSO を有効にします。

##### 全般タブ

次のスクリーンショットは、Kerberos アプリケーションの [全般] タブの設定を示しています。

[Image: Akamai EAA コンソールの MYKERBOROSAPP の [General](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/全般) タブのスクリーンショット。]

##### [認証] タブ

**[Authentication]** タブで、ID プロバイダーを割り当てます。

##### [サービス] タブ

次のスクリーンショットは、Kerberos アプリケーションの [サービス] タブの構成を示しています。

[Image: Akamai EAA コンソールの MYKERBOROSAPP の [Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サービス) タブのスクリーンショット。]

##### 詳細設定

次の例に示す [詳細設定] の値を確認します。

[Image: Akamai EAA コンソールの MYKERBOROSAPP の [Advanced Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細設定) タブのスクリーンショット。[Related Applications](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/関連アプリケーション) と [Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) の各設定が表示されている。]

注意

Web サーバーのサービス プリンシパル名 (SPN) は、SPN@Domain形式で設定されています(例: このデモの `HTTP/frp-app1.superdemo.live@SUPERDEMO.LIVE` )。 残りの設定は既定値のままにします。

##### [Deployment](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デプロイ) タブ

次のスクリーンショットは、Kerberos アプリケーションの [展開] タブを示しています。

[Image: Akamai EAA コンソールの MYKERBOROSAPP の [Deployment](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/デプロイ) タブのスクリーンショット。[Deploy Application](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アプリケーションのデプロイ) ボタンが表示されている。]

##### ディレクトリの追加

Active Directory ソースを追加するには、次の手順を実行します。

1. ドロップダウン リストから **[AD]** を選択します。

    [Image: Akamai EAA コンソールの [Directories](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ディレクトリ) ウィンドウのスクリーンショット。[Create New Directory](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいディレクトリの作成) ダイアログが表示され、[Directory Type](ディレクトリ タイプ) のボックスの一覧では [AD] が選択されている。]
2. 必要なデータを入力します。

    [Image: Akamai EAA コンソールの [SUPERDEMOLIVE] ウィンドウのスクリーンショット。[DirectoryName](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ディレクトリ名)、[Directory Service](ディレクトリ サービス)、[Connector](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/コネクタ)、[Attribute mapping](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/属性マッピング) の各設定が表示されている。]
3. ディレクトリの作成を確認します。

    [Image: Akamai EAA コンソールの [Directories](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ディレクトリ) ウィンドウのスクリーンショット。ディレクトリ superdemo.live が追加されていることがわかる。]
4. アクセスを必要とするグループ/OU を追加します。

    [Image: ディレクトリ superdemo.live の設定のスクリーンショット。グループまたは OU を追加するときに選択するアイコンが強調表示されている。]
5. この例では、グループは EAAGroup と呼ばれ、1 つのメンバーを持っています。

    [Image: Akamai EAA コンソール [SUPERDEMOLIVE ディレクトリのグループ] ウィンドウのスクリーンショット。1 名のユーザーが含まれている EAAGroup は [グループ] でリストされています。]
6. **ディレクトリを** ID プロバイダーに追加するには、&gt;を選択し、**ディレクトリ**タブを選択して、**ディレクトリの割り当て**を選択します。

#### EAA ウォークスルー向けの KCD 委任設定

##### 手順 1:アカウントの作成

次のように、Active Directoryで委任アカウントを作成します。

1. この例では、 **EAADelegation** という名前のアカウントを使用します。 これを行うには、 **[Active Directory ユーザーとコンピューター]** スナップインを使用します。

    注意

    このユーザー名は **ID インターセプト名**に基づく特定の形式にする必要があります。 この例では、ID インターセプト名は **corpapps.login.go.akamai-access.com**
2. ユーザー ログオン名は次のとおりです。`HTTP/corpapps.login.go.akamai-access.com`

    [Image: [EAADelegation Properties](EAADelegation のプロパティ) を示すスクリーンショット。[名] が]

##### 手順 2: このアカウントのサービス プリンシパル名 (SPN) を構成する

次のコマンドを使用して、委任アカウントの SPN を登録します。

1. このサンプルに基づいて、SPN は次のようになります。
2. setspn -s **Http/corpapps.login.go.akamai-access.com eaadelegation**

    [Image: 管理者コマンド プロンプトのスクリーンショット。コマンド setspn -s Http/corpapps.login.go.akamai-access.com eaadelegation の結果が表示されている。]

##### 手順 3:委任の構成

次に、EAADelegation アカウントの委任設定を構成します。

1. EAADelegation アカウントの場合は、[委任] タブを選択します。

    [Image: 管理者コマンド プロンプトのスクリーンショット。SPN を構成するためのコマンドが表示されている。]

    - [任意の認証プロトコルを使う] を選択します。
    - Kerberos Web サイト用にアプリ プール アカウントを追加するために、「追加」を選択します。 正しく構成されていれば、自動的に正しい SPN に解決されます。

##### 手順 4:AKAMAI EAA 用の keytab ファイルの作成

ktpass コマンドを使用して、Akamai EAA の keytab ファイルを作成します。

1. 一般的な構文を次に示します。
2. ktpass /out ActiveDirectorydomain.keytab /princ `HTTP/yourloginportalurl@ADDomain.com` /mapuser serviceaccount@ADdomain.com /pass +rdnPass /crypto All /ptype KRB5\_NT\_PRINCIPAL
3. 例の説明は次のとおりです。

    | スニペット | 説明 |
    | --- | --- |
    | Ktpass /out EAADemo.keytab | // 出力 keytab ファイルの名前 |
    | /princ HTTP/corpapps.login.go.akamai-access.com@superdemo.live | HTTP/yourIDPName@YourdomainName |
    | /mapuser さん eaadelegation@superdemo.live | // EAA 委任アカウント |
    | /pass RANDOMPASS | // EAA 委任アカウントのパスワード |
    | /crypto All ptype KRB5\_NT\_PRINCIPAL | // Akamai EAA のドキュメントを参照してください |
    |  |  |
4. Ktpass /out EAADemo.keytab /princ HTTP/corpapps.login.go.akamai-access.com@superdemo.live /mapuser eaadelegation@superdemo.live /pass RANDOMPASS /crypto All ptype KRB5\_NT\_PRINCIPAL

    [Image: 管理者コマンド プロンプトのスクリーンショット。AKAMAI EAA の Keytab ファイルを作成するためのコマンドの結果が表示されている。]

##### 手順 5:Akamai EAA コンソールでの keytab のインポート

keytab ファイルを作成したら、Akamai EAA コンソールにインポートします。

1. **[System**&gt;**Keytabs]** を選択します。

    [Image: Akamai EAA コンソールのスクリーンショット。[System](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム) メニューから [Keytabs](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/keytab) を選択したところ。]
2. [Keytab Type](keytab の種類) で **[Kerberos Delegation](Kerberos 委任)** を選択します。

    [Image: Akamai EAA コンソールの [EAAKEYTAB] 画面のスクリーンショット。keytab の設定が表示されている。[Keytab Type](keytab の種類) が [Kerberos Delegation](Kerberos 委任) に設定されている。]
3. keytab がデプロイ済みおよび確認済みとして表示されていることを確認します。

    [Image: Akamai EAA コンソールの [KEYTABS] 画面のスクリーンショット。[Keytab deployed and verified](keytab がデプロイ済みおよび確認済み) として EAA keytab が表示されている。]
4. ユーザー体験

    [Image: myapps.microsoft.com の [サインイン] ダイアログのスクリーンショット。]

    [Image: myapps.microsoft.com の [アプリ] ウィンドウのスクリーンショット。アプリのアイコンが表示されている。]
5. 条件付きアクセス

    [Image: MyHeaderApp、SSH Secure、SecretRDPApp、myKerberosApp のアイコンを表示する [アプリケーション] 画面のスクリーンショット。]

    [Image: myKerberosApp のスプラッシュ スクリーンのスクリーンショット。背景画像に]

#### Akamai のテスト ユーザーの作成

このセクションでは、Akamai で B.Simon というユーザーを作成します。 [Akamai クライアント サポート チーム](https://www.akamai.com/us/en/contact-us/)と連携し、Akamai プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Akamai に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Akamai] タイルを選択すると、SSO を設定した Akamai に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/akashi-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AKASHI を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/akashi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AKASHI の間のシングル サインオンを構成する方法について説明します。

この記事では、AKASHI と Microsoft Entra ID を統合する方法について説明します。 AKASHI を Microsoft Entra ID と統合すると、次のことが可能になります。

- AKASHI へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで AKASHI に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AKASHI でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AKASHI では、**SP および IDP によって開始された SSO** がサポートされています。

### ギャラリーからの AKASHI の追加

Microsoft Entra ID への AKASHI の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に AKASHI を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーから追加** する] セクションで、検索ボックスに **「AKASHI** 」と入力します。
4. 結果パネルから **[AKASHI** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AKASHI に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AKASHI に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと AKASHI の関連ユーザーとの間にリンク関係を確立する必要があります。

AKASHI に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AKASHI SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AKASHI テストユーザーの作成** - Microsoft Entra 内のユーザーである B.Simon に対応する AKASHI 上のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AKASHI**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://atnd.ak4.jp/sso/saml/<CUSTOM_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://atnd.ak4.jp/sso/saml/<CUSTOM_ID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://atnd.ak4.jp/sso/saml/<CUSTOM_ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、AKASHI クライアント サポート チーム](mailto:akashi_cc@ak4.jp) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AKASHI の SSO の構成

**AKASHI** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[AKASHI サポート チーム](mailto:akashi_cc@ak4.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AKASHI のテスト ユーザーの作成

このセクションでは、AKASHI で Britta Simon というユーザーを作成します。 [AKASHI サポート チーム](mailto:akashi_cc@ak4.jp)と協力して、AKASHI プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる AKASHI サインオン URL にリダイレクトされます。
- AKASHI のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AKASHI に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [AKASHI] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した AKASHI に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alacritylaw-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AlacrityLaw を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alacritylaw-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AlacrityLaw の間のシングル サインオンを構成する方法について説明します。

この記事では、AlacrityLaw と Microsoft Entra ID を統合する方法について説明します。 AlacrityLaw を Microsoft Entra ID と統合すると、次のことが可能になります。

- AlacrityLaw へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで AlacrityLaw に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AlacrityLaw でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AlacrityLaw では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの AlacrityLaw の追加

Microsoft Entra ID への AlacrityLaw の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に AlacrityLaw を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「AlacrityLaw**」と入力します。
4. 結果パネルから **AlacrityLaw** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AlacrityLaw に対する Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、AlacrityLaw に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと AlacrityLaw の関連ユーザーとの間にリンク関係を確立する必要があります。

AlacrityLaw で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AlacrityLaw の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AlacrityLaw のテストユーザーを作成 - B.Simon に対応するユーザーを AlacrityLaw で作成し、そのユーザーを Microsoft Entra にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**AlacrityLaw**&gt;**シングルサインオンのページに移動します**。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.alacritylaw.com/auth/saml/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.alacritylaw.com/auth/saml/<ID>/callback`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 これらの値を取得するには [、AlacrityLaw クライアント サポート チーム](mailto:infrastructure@alacritylaw.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **AlacrityLaw のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AlacrityLaw の SSO の構成

**AlacrityLaw** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [AlacrityLaw サポート チーム](mailto:infrastructure@alacritylaw.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AlacrityLaw のテスト ユーザーの作成

このセクションでは、AlacrityLaw で Britta Simon というユーザーを作成します。 [AlacrityLaw サポート チーム](mailto:infrastructure@alacritylaw.com)と協力して、AlacrityLaw プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる AlacrityLaw のサインオン URL にリダイレクトされます。
- AlacrityLaw のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AlacrityLaw] タイルを選択すると、このオプションは AlacrityLaw のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alation-data-catalog-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Alation Data Catalog を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alation-data-catalog-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-21
- Summary: Microsoft Entra ID と Alation Data Catalog の間のシングル サインオンを構成する方法についてご確認ください。

この記事では、Alation Data Catalog と Microsoft Entra ID を統合する方法について説明します。 Alation Data Catalog を Microsoft Entra ID と統合すると、次のことができます。

- Alation Data Catalog にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Alation Data Catalog に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Alation Data Catalog でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Alation Data Catalog では、**SP** と **IDP** の両方の始動による SSO がサポートされます。
- Alation Data Catalog では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Alation Data Catalog を追加する

Microsoft Entra ID への Alation Data Catalog の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Alation Data Catalog を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Alation Data Catalog**」と入力します。
4. 結果パネルから **Alation Data Catalog** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Alation Data Catalog 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Alation Data Catalog に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Alation Data Catalog の関連ユーザーとの間にリンク関係を確立する必要があります。

Alation Data Catalog に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Alation Data Catalog の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Alation Data Catalog のテスト ユーザーの作成 - B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表示にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Alation Data Catalog**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子** ] テキスト ボックスに、URL を入力します。 `http://alation.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer_Name>.<Domain>.<Extension>/saml2/acs/`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Customer_Name>.<Domain>.<Extension>/`

    注

    これらの値は実際の値ではありません。 これらの値を、実際の応答 URL およびサインオン URL で更新してください。 これらの値を取得するには [、Alation Data Catalog サポート チーム](mailto:support-all@alation.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Alation Data Catalog アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の構成の画像を示すスクリーンショット。]

    注

    **[一意のユーザー識別子 (名前 ID)]** 要求の場合は、[**要求の管理**] セクションのドロップダウンから **[名前識別子の形式**として**永続的**] を選択し、[保存] を選択**します**。 [Image: 一意のユーザー識別子の画像を示すスクリーンショット。]
8. その他に、Alation Data Catalog アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:2.5.4.42 | User.givenname |
    | urn:oid:2.5.4.4 | User.surname |
    | urn:oid:0.9.2342.19200300.100.1.3 | User.mail |
    | urn:oid:0.9.2342.19200300.100.1.1 | ユーザー.ユーザープリンシパルネーム |
    | urn:oid:2.5.4.12 | ユーザー.職名 |

    注

    上記のすべての必須要求について、[**要求の管理**] セクションのドロップダウンから [**名前識別子の形式**として **URI**] を選択し、[保存] を選択**します**。 [Image: [必須要求] の画像を示すスクリーンショット。]
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
10. [ **Alation Data Catalog のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Alation Data Catalog の SSO を構成する

**Alation Data Catalog** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Alation Data Catalog サポート チーム](mailto:support-all@alation.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Alation Data Catalog のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Alation Data Catalog に作成します。 Alation Data Catalog では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Alation Data Catalog にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Alation Data Catalog のサインオン URL にリダイレクトします。
- Alation Data Catalog のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Alation Data Catalog に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Alation Data Catalog] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Alation Data Catalog に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/albert-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Albert を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/albert-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-25
- Summary: Microsoft Entra IDから Albert にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、Albert と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成された Microsoft Entra ID を使用して、Microsoft Entra プロビジョニング サービスがユーザーを [Albert](https://www.albertinvent.com/) に自動的にプロビジョニングおよび削除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Albert でユーザーの状態を更新します。
- アクセスが不要になった場合は、Albertのユーザーを削除します。
- Microsoft Entra IDと Albert の間でユーザー属性の同期を維持します。
- Albert に[シングルサインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (使用推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Albert の管理者権限を持つユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとAlbertの間で[マップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Albert を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように Albert を構成するには、[Albert サポート](mailto:support@albertinvent.com)にお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Albert を追加する

Microsoft Entra アプリケーション ギャラリーから Albert を追加して、Albert へのプロビジョニングの管理を開始します。 SSO のために Albert を既に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Albert への自動ユーザー プロビジョニングを構成する

Microsoft Entraのプロビジョニングサービスを構成して、Microsoft Entra IDのユーザー割り当てに基づきTestAppのユーザーを作成、更新、無効化する手順をこのセクションで説明します。

#### Microsoft Entra IDで Albert の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [Albert] を選択 **します**。

    [Image: アプリケーションの一覧の [Albert] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Albert テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Albert に接続できることを確認します。 接続に失敗した場合は、Albert アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Albert に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Albert のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Albert API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Albert で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | externalId | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alchemer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Alchemer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alchemer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Alchemer 間のシングル サインオンを構成する方法について説明します。

この記事では、Alchemer を Microsoft Entra ID と統合する方法について説明します。 Alchemer は、世界で最も柔軟なフィードバックとデータ コレクションのプラットフォームを提供します。組織はこれを使って、顧客や従業員とのコミュニケーションを迅速かつ効果的に完結させることができます。 Alchemer を Microsoft Entra ID と統合すると、次のことが可能になります。

- Alchemer へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Alchemer に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Alchemer に対する Microsoft Entra シングル サインオンをテスト環境で構成してテストする。 Alchemer では、 **SP** と **IDP** によって開始されるシングル サインオンと Just In Time ユーザー プロビジョニングの両方がサポートされます。

### [前提条件]

Alchemer を Microsoft Entra ID と統合するためには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Alchemer でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Alchemer アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Alchemer を追加する

Microsoft Entra アプリケーション ギャラリーから Alchemer を追加して、Alchemer とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Alchemer**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.alchemer.com/login/getsamlxml/idp/<INSTANCE>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.alchemer.com/login/ssologin/idp/<INSTANCE>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.alchemer.com/login/initiatelogin/idp/<INSTANCE>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Alchemer クライアント サポート チーム](mailto:support@alchemer.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Alchemer のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Alchemer の SSO を構成する

**Alchemer** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** とアプリケーション構成からコピーした適切な URL を [Alchemer サポート チーム](mailto:support@alchemer.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Alchemer のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Alchemer に作成します。 Alchemer では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Alchemer にユーザーがまだ存在していない場合、一般に認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Alchemer のサインオン URL にリダイレクトされます。
- Alchemer のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Alchemer に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Alchemer] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Alchemer に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alcumus-info-tutorial"} -->
## Microsoft Entra ID で Alcumus Info Exchange for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alcumus-info-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Alcumus Info Exchange の間でシングル サインオンを構成する方法について説明します。

この記事では、Alcumus Info Exchange と Microsoft Entra ID を統合する方法について説明します。 Alcumus Info Exchange を Microsoft Entra ID と統合すると、次のことが可能になります。

- Alcumus Info Exchange にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Alcumus Info Exchange に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Alcumus Info Exchange でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Alcumus Info Exchange では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Alcumus Info Exchange の追加

Microsoft Entra ID への Alcumus Info Exchange の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Alcumus Info Exchange を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに **[Alcumus Info Exchange]** と入力します。
4. 結果のパネルから **[Alcumus Info Exchange]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Alcumus Info Exchange 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Alcumus Info Exchange で Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Alcumus Info Exchange の関連ユーザーとの間にリンク関係を確立する必要があります。

Alcumus Info Exchange に対して Microsoft Entra SSO を構成するには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Alcumus Info Exchange SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Alcumus Info Exchange のテストユーザーを作成する - これは Alcumus Info Exchange 内で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra上のユーザー表現とリンクさせるためです。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Alcumus Info Exchange**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.info-exchange.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.info-exchange.com/Auth/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Alcumus Info Exchange クライアント サポート チーム](mailto:helpdesk@alcumusgroup.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Alcumus Info Exchange のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Alcumus Info Exchange SSO の構成

**Alcumus Info Exchange** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Alcumus Info Exchange サポート チーム](mailto:helpdesk@alcumusgroup.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Alcumus Info Exchange テスト ユーザーの作成

このセクションでは、Alcumus Info Exchange で Britta Simon というユーザーを作成します。 [Alcumus Info Exchange サポート チーム](mailto:helpdesk@alcumusgroup.com)と連携して、Alcumus Info Exchange プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Alcumus Info Exchange に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Alcumus Info Exchange] タイルを選択すると、SSO を設定した Alcumus Info Exchange に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alert-enterprise-guardian-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの AlertEnterprise-Guardian を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alert-enterprise-guardian-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AlertEnterprise-Guardian の間でシングル サインオンを構成する方法について説明します。

この記事では、AlertEnterprise-Guardian と Microsoft Entra ID を統合する方法について説明します。 アプリケーションにより、ID 管理ライフサイクルが自動化されます。 組み込みの規制コンプライアンスにより、ID へのアクセスを許可する前に確実に制御が実施されます。 AlertEnterprise-Guardian を Microsoft Entra ID と統合すると、次のことが可能になります。

- AlertEnterprise-Guardian にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで AlertEnterprise-Guardian に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

AlertEnterprise-Guardian に対する Microsoft Entra シングル サインオンをテスト環境で構成およびテストします。 AlertEnterprise-Guardian では、**IDP** によって開始されるシングル サインオンがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を AlertEnterprise-Guardian と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- AlertEnterprise-Guardian シングル サインオン (SSO) 対応のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから AlertEnterprise-Guardian アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから AlertEnterprise-Guardian を追加する

Microsoft Entra アプリケーション ギャラリーから AlertEnterprise-Guardian を追加して、AlertEnterprise-Guardian でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**AlertEnterprise-Guardian**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、値を入力します。 `urn:mace:saml:pac4j.org`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.alerthsc.com/api/auth/sso/callback?client_name=<Client_Name>`

    注

    応答 URL は実際のものではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、[AlertEnterprise-Guardian サポート チーム](mailto:info@alertenterprise.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. AlertEnterprise-Guardian アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、AlertEnterprise-Guardian アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | テナント | &lt;ALERTチームによる共有&gt; |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### AlertEnterprise-Guardian SSO を構成する

**AlertEnterprise-Guardian** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [AlertEnterprise-Guardian サポート チーム](mailto:info@alertenterprise.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### AlertEnterprise-Guardian のテスト ユーザーを作成する

このセクションでは、AlertEnterprise-Guardian で Britta Simon というユーザーを作成します。 [AlertEnterprise-Guardian サポート チーム](mailto:info@alertenterprise.com)と連携して、AlertEnterprise-Guardian プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AlertEnterprise-Guardian に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AlertEnterprise-Guardian] タイルを選択すると、SSO を設定した AlertEnterprise-Guardian に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alertmedia-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に AlertMedia を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alertmedia-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-25
- Summary: Microsoft Entra IDから AlertMedia にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために AlertMedia と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを利用して、ユーザーおよびグループが[AlertMedia](https://www.alertmedia.com/)に自動的にプロビジョニングおよび解除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- AlertMedia にユーザーを作成する
- アクセスが不要になった場合には、AlertMediaからユーザーを削除する。
- Microsoft Entra IDと AlertMedia の間でユーザー属性の同期を維持する
- AlertMedia にグループとグループ メンバーシップをプロビジョニングする
- AlertMedia への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alertmedia-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [AlertMedia テナント](https://dashboard.alertmedia.com/#/login)。
- API 統合を構成するための管理者アクセス許可がある AlertMedia のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとAlertMediaの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように AlertMedia を構成する

1. AlertMedia アカウントにログインします。 **Company &gt; API** に移動します。
2. [ **新規追加] を選択します**。
3. **API 統合**に名前を付けて、キーが使用されている場所を簡単に認識できるようにします。
4. 統合を関連付けたい管理者を選択します。
5. [ **キーの生成** と **保存]** ボタンを選択します。
6. 統合から **クライアント トークン** をコピーして保存します。 これは、AlertMedia アプリケーションの [プロビジョニング] タブの **シークレット トークン** として使用されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから AlertMedia を追加する

Microsoft Entra アプリケーション ギャラリーから AlertMedia を追加して、AlertMedia へのプロビジョニングの管理を開始します。 SSO のために AlertMedia を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: AlertMedia への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づき、TestApp においてユーザーやグループを作成、更新、または無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで AlertMedia の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **AlertMedia** を選択します。

    [Image: アプリケーションの一覧の AlertMedia リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、AlertMedia テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが AlertMedia に接続できることを確認します。 接続に失敗した場合は、AlertMedia アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから AlertMedia に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で AlertMedia のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、AlertMedia API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:first\_name | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:last\_name | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:email | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:email2 | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:email3 | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:title | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:mobile\_phone | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:mobile\_phone\_post\_dial | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:mobile\_phone2 | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:mobile\_phone2\_post\_dial | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:mobile\_phone3 | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:mobile\_phone3\_post\_dial | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:home\_phone | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:home\_phone\_post\_dial | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:office\_phone | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:office\_phone\_post\_dial | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:address | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:address2 | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:city | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:state | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:country | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:zipcode | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:notes | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:customer\_user\_id | 糸 |
    | urn:ietf:params:scim:schemas:extension:alertmedia:2.0:CustomAttribute:User:user\_type | 糸 |
12. **[グループ]** を選びます。
13. Microsoft Entra IDから AlertMedia に同期されるグループ属性を、**Attribute-Mapping** セクションで確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で AlertMedia のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | members | リファレンス |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alertmedia-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に AlertMedia を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alertmedia-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AlertMedia の間のシングル サインオンを構成する方法について説明します。

この記事では、AlertMedia と Microsoft Entra ID を統合する方法について説明します。 AlertMedia を Microsoft Entra ID と統合すると、次のことが可能になります。

- AlertMedia にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで AlertMedia に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AlertMedia のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AlertMedia では、**IDP** Initiated SSO がサポートされます。
- AlertMedia では、**Just In Time** ユーザー プロビジョニングがサポートされます。
- AlertMedia では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alertmedia-provisioning-tutorial)がサポートされます。

### ギャラリーから AlertMedia を追加する

Microsoft Entra ID への AlertMedia の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に AlertMedia を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**AlertMedia**」と入力します。
4. 結果のパネルから **[AlertMedia]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AlertMedia の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、AlertMedia に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと AlertMedia の関連ユーザーとの間にリンク関係を確立する必要があります。

AlertMedia で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AlertMedia の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AlertMedia のテスト ユーザーの作成** - AlertMedia で B.Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AlertMedia**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.alertmedia.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.alertmedia.com/api/sso/saml/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[AlertMedia クライアント サポート チーム](mailto:support@alertmedia.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. AlertMedia アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、AlertMedia アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

| 名前 | ソース属性 |
| --- | --- |
| メール | user.userprincipalname |
| ファーストネーム | User.givenname |
| lastname | ユーザーの名字 |

1. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AlertMedia の SSO の構成

1. 新しい Web ブラウザー ウィンドウで、AlertMedia 企業サイトに管理者としてサインインします。
2. **[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社)** に移動し、 **[Single Sign-On](シングル サインオン)** を選択します。

    [Image: [アカウント] ボタン]
3. **[Authentication Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証方法)** で **[Remote SAML Metadata](リモート SAML メタデータ)** を選択します。
4. **[Sign Request](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サイン要求)** をオンに切り替えます。
5. **[Allow Passive Requests](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パッシブ要求を許可する)** をオンに切り替えます。
6. **[MetaData URL](メタデータ URL)** ボックスに、Azure portal からコピーした **[アプリのフェデレーション メタデータ URL]** の値を貼り付けます。
7. **[Requested Authentication Context Comparison](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/要求された認証コンテキストの比較)** で **[exact](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/完全)** を選択します。
8. **[IDP ログイン URL]** テキスト ボックスに、前にコピーした **[ログイン URL]** の値を貼り付けます。
9. **保存** を選択します。

#### AlertMedia のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを AlertMedia に作成します。 AlertMedia では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 AlertMedia にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AlertMedia に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [AlertMedia] タイルを選択すると、SSO を設定した AlertMedia に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alertops-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に AlertOps を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alertops-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と AlertOps の間のシングル サインオンを構成する方法について説明します。

この記事では、AlertOps と Microsoft Entra ID を統合する方法について説明します。 AlertOps を Microsoft Entra ID と統合すると、次のことが可能になります。

- AlertOps にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで AlertOps に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AlertOps でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- AlertOpsでは、**SPおよびIDP**が開始するSSOがサポートされます。

### ギャラリーから AlertOps を追加する

Microsoft Entra ID への AlertOps の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AlertOps を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「AlertOps**」と入力します。
4. 結果パネルから **AlertOps** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AlertOps 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、AlertOps に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと AlertOps の関連ユーザーとの間にリンク関係を確立する必要があります。

AlertOps に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **AlertOps SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AlertOps のテストユーザーを作成** - B.Simon に対応する AlertOps ユーザーを作成し、Microsoft Entra でのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**AlertOps** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.alertops.com/<SUBDOMAIN>`
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.alertops.com/api/v2/saml/<SUBDOMAIN>`
    3. [ **ログアウト URL (省略可能)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.alertops.com/<SUBDOMAIN>`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、およびログアウト URL で更新してください。 これらの値を取得するには [、AlertOps クライアント サポート チーム](mailto:support@alertops.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **AlertOps のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AlertOps の SSO の構成

1. 別の Web ブラウザー ウィンドウで、AlertOps 企業サイトに管理者としてサインインします。
2. ユーザー プロファイルから **アカウント設定** を選択します。

    [Image: [アカウント設定] が強調表示された [AlertOps] メニューを示すスクリーンショット。]
3. [**アカウント設定]** ページで、[**SSO の更新**] を選択し、[**シングル サインオン (SSO) の使用]** を選択します。

    [Image: この手順の説明に従って、更新 SSO の [サブスクリプション設定] ウィンドウを示すスクリーンショット。]
4. **SSO** セクションで、次の手順を実行します。

    [Image: この手順の説明に従って値が入力された S S O の [サブスクリプション設定] ウィンドウを示すスクリーンショット。]

    a. [ **発行者 URL** ] ボックスで、[ **基本的な SAML 構成** ] セクションで使用した識別子の値を使用します。

    b。 **[SAML エンドポイント URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    c. **[SLO エンドポイント URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    d. ドロップダウンから **SAML 署名アルゴリズム**として **SHA256** を選択します。

    e. ダウンロードした **証明書 (Base64)** ファイルをメモ帳で開きます。 その内容をクリップボードにコピーし、[ **X.509 証明書** ] テキスト ボックスに貼り付けます。

    f. [ **ユーザー名とパスワードのログインを許可する]** を有効にします。

#### AlertOps のテスト ユーザーの作成

1. 別のブラウザーのウィンドウで、管理者としてご自分の AlertOps 企業サイトにサインオンします。
2. [ **構成** ] を選択し、ナビゲーション パネルから **[ユーザー]** を選択します。

    [Image: このスクリーンショットは、[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) がコールアウトされた状態の AlertOps メニューを示しています。]
3. [ **ユーザーの追加] を選択します**。

    [Image: [ユーザーの追加] ボタンが表示された [ユーザー] ウィンドウを示すスクリーンショット。]
4. [ **ユーザーの追加** ] ダイアログで、次の手順を実行します。

    [Image: この手順の説明に従って値が入力された [ユーザーの追加] ペインを示すスクリーンショット。]

    a. [ **ユーザー名]** ボックスに、 **Brittasimon** などのユーザーのユーザー名を入力します。

    b。 **名** テキストボックスに、ユーザーの名を「**Britta**」のように入力します。

    c. [ **姓]** ボックスに、ユーザーの名 ( **Simon** など) を入力します。

    d. [ **電子メール** ] ボックスに、ユーザーのメール アドレス ( `Brittasimon@contoso.com`など) を入力します。

    f. 組織に応じて、ドロップダウンからユーザーのユーザー **ロール** を選択します。

    g. [ **送信] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる AlertOps のサインオン URL にリダイレクトされます。
- AlertOps のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した AlertOps に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [AlertOps] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した AlertOps に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alexishr-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に AlexisHR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alexishr-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-25
- Summary: Microsoft Entra IDから AlexisHR にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために AlexisHR と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成済みのMicrosoft Entra IDは、Microsoft Entraプロビジョニングサービスを使用して、ユーザーおよびグループを[AlexisHR](https://alexishr.com/)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- AlexisHR でユーザーを作成します。
- AlexisHR のユーザーは、アクセスが不要になったときに削除されます。
- Microsoft Entra IDと AlexisHR の間でユーザー属性の同期を維持します。
- AlexisHR に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alexishr-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある AlexisHR のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとAlexisHRの間でどのデータをマップするかを決めます。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように AlexisHR を構成する

1. [AlexisHR 管理コンソール](https://app.alexishr.com/login/)にログインします。 **設定 &gt; アクセストークン** に移動します。

    [Image: Access ユーザー管理]
2. Access トークン ページで、**Name** と **Description** ボックスに入力し、 **Save** を選択します。ポップアップ ウィンドウにトークンが表示されます。 トークンをコピーして保存します。 この値は、AlexisHR アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** \*] フィールドに入力されます。

    [Image: Access トークン]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから AlexisHR を追加する

Microsoft Entra アプリケーション ギャラリーから AlexisHR を追加して、AlexisHR へのプロビジョニングの管理を開始します。 SSO のために AlexisHR を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: AlexisHR への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID 内のユーザーやグループの割り当てに基づき、AlexisHR においてユーザーおよび／またはグループを作成、更新、無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで AlexisHR の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧 **で AlexisHR** を選択します。

    [Image: アプリケーションの一覧の AlexisHR リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、AlexisHR テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが AlexisHR に接続できることを確認します。 接続に失敗した場合は、AlexisHR アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから AlexisHR に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で AlexisHR のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、AlexisHR API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | AlexisHR で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |  |
    | 活動中 | ブール値 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | phoneNumbers[type eq "work"].value | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |

    注

    phonenumbers 値は E164 形式である必要があります。 (例: +16175551212)。
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/alexishr-tutorial"} -->
## Microsoft Entra ID で AlexisHR for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alexishr-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-25
- Summary: Microsoft Entra ID と AlexisHR 間のシングル サインオンを構成する方法について説明します。

この記事では、AlexisHR と Microsoft Entra ID を統合する方法について説明します。 AlexisHR を Microsoft Entra ID と統合すると、次のことが可能になります。

- AlexisHR へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで AlexisHR に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- AlexisHR でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra ID と AlexisHR の間で SAML SSO を構成し、テストします。

- AlexisHR では、 **IdP によって開始される SSO がサポートされます** 。
- 最初に、ログイン URL と証明書を取得するためにMicrosoft Entra IDに**基本的な (モック) SAML 構成**を作成し、AlexisHR で SSO を構成し、最後に Microsoft Entra ID に戻り、識別子と応答 URL を AlexisHR の実際の値で更新します。

### ギャラリーから AlexisHR を追加する

Microsoft Entra ID への AlexisHR の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に AlexisHR を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Microsoft Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「AlexisHR**」と入力します。
4. 結果パネルから **AlexisHR** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### AlexisHR に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、AlexisHR に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと AlexisHR の関連ユーザーとの間にリンク関係を確立する必要があります。

AlexisHR に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
2. シングル サインオンを検証するために**、Microsoft Entraテスト ユーザーを作成して割り当てます**。
3. **AlexisHR SSO の構成 - AlexisHR** でシングル サインオンを構成します。
4. **Microsoft Entra SSO を実際の値に更新**します。プレースホルダーの値を実際の値に置き換えます。
5. **SSO のテスト** – 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成 (初期モック セットアップ)

一時的な値でMicrosoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Microsoft Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**AlexisHR**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、最初のセットアップの **プレースホルダー値** を入力します。

    - **識別子 (エンティティ ID)**: `urn:auth0:alexishr:<YOUR_CONNECTION_NAME>`
    - **応答 URL (Assertion Consumer Service URL)**: `https://auth.alexishr.com/login/callback?connection=<YOUR_CONNECTION_NAME>`

    Example:

    - 会社： `acme`
    - 日付： `20250901`
    - 識別子: `urn:auth0:alexishr:acme-20250901`
    - 返信の URL: `https://auth.alexishr.com/login/callback?connection=acme-20250901`

    注

    これらの値は単なる仮の値です。 AlexisHR SSO を構成したら、このページに戻り、AlexisHR によって提供される実際の **対象ユーザー URI** と **Assertion Consumer Service の URL** 値に置き換えます。
6. [ **属性と要求** ] セクションで、[ **名前 ID の形式]** を **[電子メール アドレス** ] に設定し、[ **名前 ID** ] の値が **user.email** されていることを確認します。
7. [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を選択し、[ **ダウンロード**] を選択します。 このファイルには.cer拡張子があり、PEM でエンコードされ、AlexisHR のセットアップ中に後で必要になります。
8. [ **AlexisHR のセットアップ** ] セクションで、[ **ログイン URL** ] と [ **ログアウト URL** ] の値をコピーします。 これらの値は、AlexisHR セットアップでも必要になります。

Important

テストは、AlexisHR のセットアップを完了し、Microsoft Entra IDの識別子と応答 URL を実際の値で更新した**後**にのみ機能します。

### Microsoft Entra テスト ユーザーの作成と割り当て

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### AlexisHR の SSO の構成

1. AlexisHR 企業サイトに所有者としてログインします。
2. &gt;] に移動し、[**新しい ID プロバイダー**] を選択します。
3. [ **新しい ID プロバイダー**] セクションで、次の手順を実行します。
    - **ID プロバイダーの SSO URL**: Microsoft Entra IDからの**ログイン URL を**貼り付けます。
    - **ID プロバイダーのサインアウト URL**: Microsoft Entra IDからの**ログアウト URL を**貼り付けます。
    - **パブリック x509 証明書**: ダウンロードした **証明書 (Base64)** ファイルをテキスト エディターで開き、改行を変更せずに **PEM コンテンツ全体** ( `-----BEGIN CERTIFICATE-----` と `-----END CERTIFICATE-----` 行を含む) を貼り付けます。
4. [ **ID プロバイダーの作成] を選択します**。
5. ID プロバイダーを作成した後、AlexisHR は次の機能を提供します。
    - **対象ユーザー URI**
    - **Assertion Consumer Service URL** これらの値は、Microsoft Entra IDの更新に使用されます。

### Microsoft Entra SSO を実際の値で更新する

1. **Microsoft Entra 管理センター**&gt;**エンタープライズ アプリケーション**&gt;**AlexisHR**&gt;**シングル サインオン** に戻る。
2. [ **基本的な SAML 構成] セクションを** 編集します。
3. 一時的なプレースホルダーの値を次のように置き換えます。
    - **識別子 (エンティティ ID):** AlexisHR の **対象ユーザー URI を** 貼り付けます。
    - **応答 URL (アサーション コンシューマー サービス URL)**: AlexisHR の **アサーション コンシューマー サービス URL を** 貼り付けます。
4. 変更を保存します。

### AlexisHR のテスト ユーザーの作成

1. [AlexisHR サポート チーム](mailto:support@alexishr.com)と協力して、AlexisHR プラットフォームにテスト ユーザー (Britta Simon など) を追加します。
2. シングル サインオンをテストする前に、ユーザーが作成され、アクティブ化されていることを確認します。

### SSO のテスト

1. **Microsoft Entra 管理センター**で **AlexisHR** アプリに移動し、[**このアプリケーションをテスト**する] を選択します。 AlexisHR に自動的にサインインします。
2. または、マイ アプリ開[き](https://myapps.microsoft.com)、[**AlexisHR**] タイルを選択し、自動的にサインインしていることを確認します。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->
