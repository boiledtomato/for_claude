# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 23)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 74

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sso-for-jama-connect-tutorial"} -->
## Microsoft Entra ID を使用して Jama Connect® for Single sign-on の SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sso-for-jama-connect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SSO for Jama Connect® の間でシングル サインオンを構成する方法について説明します。

この記事では、SSO for Jama Connect® を Microsoft Entra ID と統合する方法について説明します。 Jama Software® の業界をリードするプラットフォームは、実績のあるサイクル時間の短縮と品質の向上のためのシステム開発プロセスを通じて、チームが Live Traceability™ を使用して要件を管理するのに役立ちます。 SSO for Jama Connect® を Microsoft Entra ID と統合すると、次のことができます:

- SSO for Jama Connect® にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SSO for Jama Connect® に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で SSO for Jama Connect® 用の Microsoft Entra シングル サインオンを構成してテストします。 SSO for Jama Connect® では、**SP** と **IDP** initiated シングル サインオンと、**Just In Time** ユーザー プロビジョニングの両方がサポートされています。

### [前提条件]

Microsoft Entra ID を SSO for Jama Connect® に統合するには、次のものが必要です:

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SSO for Jama Connect® でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから SSO for Jama Connect® アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから SSO for Jama Connect® を追加する

Microsoft Entra アプリケーション ギャラリーから SSO for Jama Connect® を追加して、SSO for Jama Connect® でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SSO for Jama Connect®**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a。 **[識別子]** ボックスに、`urn:auth0:<First_Part_of_Auth0_Domain>:<TenantID>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Auth0_Domain>/login/callback?connection=<TenantID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Tenant_Name>.jamacloud.com/login.req`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[SSO for Jama Connect® サポート チーム](mailto:support@jamasoftware.zendesk.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### SSO for Jama Connect® の SSO を構成する

**SSO for Jama Connect®** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [SSO for Jama Connect® サポート チーム](mailto:support@jamasoftware.zendesk.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SSO for Jama Connect® テスト ユーザーを作成する

このセクションでは、B. Simon というユーザーを SSO for Jama Connect® に作成します。 SSO for Jama Connect® では、既定で有効になっている Just-In-Time ユーザー プロビジョニングがサポートされています。 このセクションにはアクション項目はありません。 SSO for Jama Connect® にユーザーがまだ存在していない場合は、認証後に新規作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Jama Connect® のサインオン URL の SSO にリダイレクトされます。
- SSO for Jama Connect® サインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Jama Connect® の SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SSO for Jama Connect®] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Jama Connect® の SSO に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ssogen-tutorial"} -->
## Oracle E-Business Suite - EBS、PeopleSoft、JDE向けのMicrosoft Entra ID SSOゲートウェイであるSSOGENの構成を設定し、Microsoft Entra IDとのシングルサインオンを実現합니다。 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ssogen-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE の間でシングル サインオンを構成する方法について説明します。

この記事では、SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS、PeopleSoft、JDE を Microsoft Entra ID と統合する方法について説明します。 SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE と Microsoft Entra ID を統合すると、次のことができます。

- SSOGEN - Microsoft Entra Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE では、**SP および IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE の追加

Microsoft Entra ID への SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE の統合を構成するには、ギャラリーからご自分のマネージド SaaS アプリの一覧に SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE**」と入力します。
4. 結果のパネルから **[SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE の関連ユーザーとの間にリンク関係を確立する必要があります。

SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS、PeopleSoft、JDE テスト ユーザーの作成** - SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS、PeopleSoft、JDE で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS、PeopleSoft、JDE**&gt;**Single のサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    **[応答 URL]** ボックスに、`https://<customer_name>.ssogen.com/ssogen/login?client_name=<customer_name>` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<customer_name>.ssogen.com/ssogen/login?client_name=<customer_name>` という形式で URL を入力します。

    注

    これらの値は実際の値ではありません。 実際の応答 URLとサインオン URL でこれらの値を更新します。 これらの値を取得するには、[SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE クライアント サポート チーム](mailto:support@ssogen.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. 対象の SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE アプリケーションでは、特定の形式の SAML アサーションを使用するため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS、PeopleSoft、JDE アプリケーションでは、 **nameidentifier** が **user.onpremisessamaccountname に**マップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE の SSO の構成

**SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE** 側でシングル サインオンを構成するには、アプリケーション固有の SSO の登録に関するドキュメントを参照してください。

- Oracle EBS - Microsoft Entra IDSSO の統合:https://www.ssogen.com/oracle-ebs-sso-ldap/
- PeopleSoft - Microsoft Entra SSO の統合:https://www.ssogen.com/peoplesoft-sso/
- JD Edwards - Microsoft Entra SSO の統合:https://www.ssogen.com/oracle-jde-sso/
- Apache - Microsoft Entra SSO の統合:https://www.ssogen.com/apache-sso-authentication/

#### SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE のテスト ユーザーの作成

認証が成功すると、Microsoft Entra ID によって一意のユーザー識別子 (名前 ID) がユーザー アプリケーションに送信されます。 この一意のユーザー識別子 (名前 ID) が自分のアプリケーションのユーザー レコード (たとえば、Oracle EBS の FND\_USER.USER\_NAME) と一致することを確認してください。

サポートが必要な場合は、info@ssogen.com および support@ssogen.com にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS、PeopleSoft、および JDE サインオン URL にリダイレクトされます。
- SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS, PeopleSoft, and JDE のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS、PeopleSoft、および JDE に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS、PeopleSoft、JDE タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSOGEN - Microsoft Entra SSO Gateway for Oracle E-Business Suite - EBS に自動的にサインインされます。 SSO を設定した PeopleSoft と JDE。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/stackby-tutorial"} -->
## Microsoft Entra ID で Stackby for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/stackby-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Stackby 間にシングル サインオンを構成する方法について学習します。

この記事では、Stackby と Microsoft Entra ID を統合する方法について説明します。 Stackby を Microsoft Entra ID と統合すると、次のことができます:

- Stackby にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Stackby に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Stackby でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Stackby では、**IDP** Initiated SSO がサポートされます。
- Stackby では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Stackby を追加する

Microsoft Entra ID への Stackby の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Stackby を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Stackby**」と入力します。
4. 結果パネルから **[Stackby]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Stackby 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Stackby に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Stackby の関連ユーザーとの間にリンク関係を確立する必要があります。

Stackby に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Stackby SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Stackby のテスト ユーザーを作成する** - Stackby で B.Simon に対応するユーザーを作成し、Microsoft Entra でのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Stackby**&gt;**シングル サインオン**へ移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Set up Stackby] (Stackby のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Stackby SSO の構成

**Stackby** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Stackby サポート チーム](mailto:support@stackby.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Stackby テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Stackby に作成します。 Stackby では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Stackby にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Stackby に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Stackby] タイルを選択すると、SSO を設定した Stackby に自動的にサインインします。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/stackit-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に STACKIT Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/stackit-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-18
- Summary: Microsoft Entra ID と STACKIT Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、STACKIT Cloud と Microsoft Entra ID を統合する方法について説明します。 STACKIT Cloud と Microsoft Entra ID を統合すると、次のことができます。

- STACKIT Cloud にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して STACKIT Cloud に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- STACKIT Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

STACKIT Cloud では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから STACKIT Cloud を追加する

Microsoft Entra ID への STACKIT Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に STACKIT Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「STACKIT Cloud**」と入力します。
4. 結果パネルから **STACKIT Cloud** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細については](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)を参照してください。

### STACKIT Cloud の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、STACKIT Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、電子メールに基づいて、Microsoft Entra ユーザーと STACKIT Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

STACKIT Cloud に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra のテストユーザーを作成** - B.Simon を使用して Microsoft Entra のシングルサインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **STACKIT Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. STACKIT Cloud テストユーザーの作成 - Microsoft Entra のユーザー表現にリンクされた B.Simon の対応ユーザーとして、STACKIT Cloud でテストユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**STACKIT Cloud**&gt;**シングルサインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://portal.stackit.cloud`

    b. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://accounts.stackit.cloud/idps/*/saml/metadata`

    c. [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://accounts.stackit.cloud/ui/login/login/externalidp/saml/acs`

    Note

    識別子の値は実際の値ではありません。 実際の識別子を使用して値を更新します。これは、フェデレーション ディレクトリのセットアップ プロセス中に STACKIT から取得されます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、コピー ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. [ **STACKIT Cloud のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra のテストユーザーを作成して割り当てる。

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### STACKIT Cloud SSO の構成

STACKIT Cloud SSO を構成するには、STACKIT ポータルに移動し、次の情報を含むサポート チケットを開きます。

1. Microsoft Entra ID を使用して SAML 2.0 として **フェデレーションの種類** を選択します。
2. **統合の理由**と簡単な説明を入力します (例: "エンタープライズ ユーザーの SSO を有効にする")。
3. 従業員がログインに使用するすべてのメール ドメイン (や@example.orgなど) を含む@foobar.com)を入力します。
4. **IdP メタデータ URL を、IdP** のメタデータ ファイルへのパブリックにアクセス可能な URL として入力します。 システムはこの URL を使用して、エンドポイントや証明書などの構成の詳細を自動的に取得します。

Note

ダウンロードしたファイルは、STACKIT 側でフェデレーションを構成するのにも十分です。

必要な情報を入力すると、STACKIT サポート チームによってフェデレーションが構成されます。 その後、STACKIT IdP の一意の SAML メタデータ URL が提供されます。

#### STACKIT Cloud テスト ユーザーの作成

[ **組織** ] ページで、[ **アクセス権の付与** ] を選択して、ユーザーを組織に招待します。 Microsoft Entra ID にユーザーのメール アドレスを入力します。

[Image: STACKIT Cloud の [組織] ページの [アクセス権の付与] ボタンのスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる STACKIT Cloud のサインオン URL にリダイレクトされます。
- STACKIT Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft My Appsを使用できます。 マイ アプリで [STACKIT Cloud] タイルを選択すると、このオプションは STACKIT Cloud のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/stage-and-screen-tutorial"} -->
## Microsoft Entra ID でシングル サインオンのステージと画面を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/stage-and-screen-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Stage と Screen の間でシングル サインオンを構成する方法について説明します。

この記事では、Stage と Screen を Microsoft Entra ID と統合する方法について説明します。 Stage と Screen を Microsoft Entra ID と統合すると、次のことができます。

- ステージと画面にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Stage および Screen に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- ステージと画面でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Stage と Screen では、 **SP Initiated SSO と IDP** Initiated SSO の両方がサポートされます。
- ステージとスクリーンは**Just In Time**ユーザー プロビジョニングをサポートします。

### ギャラリーからのステージと画面の追加

Microsoft Entra ID への Stage と Screen の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Stage と Screen を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **ギャラリーから追加**のセクションで、検索ボックスに**「ステージとスクリーン」**と入力します。
4. 結果パネルから **[ステージ] と [画面]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ステージと画面の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Stage と Screen に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Stage and Screen の関連ユーザーとの間にリンク関係を確立する必要があります。

Stage と Screen で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Stage と Screen の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ステージとスクリーンでのテストユーザーの作成** - Microsoft Entra のユーザー表現として、ステージとスクリーンで B.Simon に対応するユーザーを作成し、その B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ステージとスクリーン**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションでは、アプリは既に Microsoft Entra と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://portal.stageandscreen.travel/ssosp/spinit?clientid=<Client_ID>`

    注

    サインオン URL は実際のものではありません。 実際のサインオン URL で値を更新します。 この値を取得するには [、Stage および Screen サポート チーム](mailto:corporate_support@flightcentre.com) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. ステージおよびスクリーン アプリケーションでは、特定の形式の SAML アサーションが必要です。そのためには、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、既定値を持つユーザー属性と要求を示しています。]
8. 上記に加えて、Stage および Screen アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | PortalID | `<ID>` |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Stage および Screen の SSO を設定する

**ステージ側と画面**側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Stage および Screen サポート チーム](mailto:corporate_support@flightcentre.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### 「ステージおよびスクリーンテストユーザーの作成」

このセクションでは、Britta Simon というユーザーを Stage and Screen に作成します。 ステージと画面では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ステージと画面にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できるステージおよびスクリーン サインオン URL にリダイレクトします。
- ステージと画面のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Stage and Screen に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ステージと画面] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したステージと画面に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/standard-for-success-accreditation-tutorial"} -->
## Microsoft Entra ID を用いてシングル サインオンを設定し、Standard for Success 認証を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/standard-for-success-accreditation-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Standard for Success Accreditation の間にシングル サインオンを構成する方法について説明します。

この記事では、Standard for Success の認定を Microsoft Entra ID と統合する方法について説明します。 Standard for Success Accreditation を Microsoft Entra ID と統合すると、次のことができます。

- Standard for Success Accreditation にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Standard for Success Accreditation に自動的にサインインできるようにします。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Standard for Success Accreditation でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Standard for Success Accreditation では **SP および IDP** の Initiated SSO がサポートされています。

### ギャラリーから Standard for Success Accreditation を追加する

Standard for Success Accreditation の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Standard for Success Accreditation を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Standard for Success Accreditation**」と入力します。
4. 結果パネルから **Standard for Success Accreditation** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Standard for Success Accreditation 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Standard for Success Accreditation に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Standard for Success Accreditation の関連ユーザーの間にリンク関係を確立する必要があります。

Standard for Success Accreditation に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Standard for Success Accreditation SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Standard for Success の認定テストユーザーを作成** - Standard for Success 認定において B.Simon に相当するユーザーを作成し、そのユーザーを Microsoft Entra 表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Standard for Success Authentication**&gt;**Single のサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `api://<ApplicationId>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://edu.sfsed.com/access/saml_consume?did=<INSTITUTION-ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://edu.sfsed.com/access/saml_int?did=<INSTITUTION-ID>`

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://edu.sfsed.com/access/saml_consume?did=<INSTITUTION-ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態でこれらの値を更新します。 これらの値を取得するには、[Standard for Success Accreditation クライアント サポート チーム](mailto:help_he@standardforsuccess.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
8. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
9. **[Standard for Success Accreditation のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Standard for Success Accreditation SSO を構成する

1. 新しい Web ブラウザー ウィンドウを開き、スーパーユーザー アクセス権を持つ管理者として Standard for Success Accreditation サイトにサインインします。
2. メニューから[ **管理ポータル**]を選択します。
3. [ **Single Sign On Settings]\(シングル サインオン設定** \) まで下にスクロールし、[ **Microsoft Azure Single Sign On]\(Microsoft Azure シングル サインオン\** ) リンクを選択し、次の手順を実行します。

    [Image: Standard for Success Accreditation で Azure シングル サインオンを有効にする方法を示すスクリーンショット。]

    ある。 **[Enable Azure Single Sign On](Azure シングル サインオンを有効にする)** チェック ボックスをオンにします。

    b。 URL と識別子のフィールドに、SAML の設定からコピーした適切な URL を入力します。

    c. **[Application ID](アプリケーション ID)** テキスト ボックスに、アプリケーション ID を入力します。

    d. [ **証明書の拇印** ] テキスト ボックスに、コピーした **拇印の値** を貼り付けます。

    え **保存** を選択します。

#### Standard for Success Accreditation テスト ユーザーを作成する

1. スーパーユーザー特権を持つ管理者として Standard for Success Accreditation にサインインします。
2. メニューから[**管理ポータル**]&gt;**[新しい評価の作成**]を選択し、次の手順を実行します。

    [Image: テスト ユーザーの作成。]

    ある。 **[名]** テキスト ボックスに「B」と入力します。

    b。 **[姓]** テキスト ボックスに「Simon」と入力します。

    c. **[University Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/大学のメール)** テキスト ボックスに、Azure で B.Simon に追加したメール アドレスを入力します。

    d. 一番下までスクロールし、[ **ユーザーの作成**] を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Standard for Success の認定サインオン URL にリダイレクトされます。
- Standard for Success Accreditation のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Standard for Success の認定に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [成功の認定の標準] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Standard for Success 認定に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/standard-for-success-tutorial"} -->
## Standard for Success K-12 for Single sign-on を Microsoft Entra ID で構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/standard-for-success-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Standard for Success K-12 の間にシングル サインオンを構成する方法について説明します。

この記事では、Standard for Success K-12 と Microsoft Entra ID を統合する方法について説明します。 Standard for Success K-12 を Microsoft Entra ID と統合すると、次のことができます。

- Standard for Success K-12 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Standard for Success K-12 に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Standard for Success K-12 でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Success K-12の標準では、**SP**の開始によるSSOと**IDP**の開始によるSSOがサポートされています。

### ギャラリーから Standard for Success K-12 を追加する

Microsoft Entra ID への Standard for Success K-12 の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Standard for Success K-12 を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Standard for Success K-12**」と入力します。
4. 結果のパネルから **[Standard for Success K-12** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Standard for Success K-12 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Standard for Success K-12 に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Standard for Success K-12 の関連ユーザーの間にリンク関係を確立する必要があります。

Standard for Success K-12 に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Standard for Success K-12 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Standard for Success K-12 テストユーザーの作成 - Standard for Success K-12** で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Standard for Success K-12**&gt;**シングルサインオン**の画面に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `api://<ApplicationId>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://edu.standardforsuccess.com/access/mssaml_consume?did=<INSTITUTION-ID>`
6. SP 開始モードでアプリケーションを構成する場合は、[ **追加の URL の設定] を** 選択し、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://edu.standardforsuccess.com/access/mssaml_int?did=<INSTITUTION-ID>`

    b。 [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://edu.standardforsuccess.com/access/mssaml_consume?did=<INSTITUTION-ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態でこれらの値を更新します。 INSTITUTION-ID の値を取得するには、 [Standard for Success K-12 クライアント サポート チーム](mailto:help@standardforsuccess.com) にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
7. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書を編集するスクリーンショット。]
8. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする手順を示したスクリーンショット。]
9. [ **Standard for Success K-12 のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成を適切なURLにコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Standard for Success K-12 SSO を構成する

1. スーパーユーザー アクセス権を持つ管理者として Standard for Success K-12 企業サイトにログインします。
2. メニューから、 **ユーティリティ**&gt;**ツールと機能**に移動します。
3. [ **シングル サインオン設定]** まで下にスクロールし、[ **Microsoft Azure シングル サインオン]** リンクを選択し、次の手順を実行します。

    [Image: [構成設定] を示すスクリーンショット。]

    ある。 [ **Enable Azure Single Sign On]\(Azure シングル サインオンを有効にする\)** チェック ボックスをオンにします。

    b。 [ **ログイン URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。

    c. **[Microsoft Entra Identifier]\(Microsoft Entra 識別子\)** ボックスに、前にコピーした **Microsoft Entra Identifier** の値を貼り付けます。

    d. [ **アプリケーション ID** ] テキスト ボックスに **アプリケーション ID を** 入力します。

    え [ **証明書の拇印** ] テキスト ボックスに、コピーした **拇印の値** を貼り付けます。

    f. **[保存] を選択します**。

#### Standard for Success K-12 テスト ユーザーを作成する

1. 別の Web ブラウザー ウィンドウで、スーパーユーザー特権を持つ管理者として Standard for Success K-12 Web サイトにログインします。
2. メニューから **Utilities**&gt;**Accounts Manager** に移動し、[ **Create New User]\(新しいユーザーの作成** \) を選択し、次の手順を実行します。

    [Image: [ユーザー情報] フィールドを示すスクリーンショット。]

    ある。 [ **名** ] テキスト ボックスに、ユーザーの名を入力します。

    b。 [ **姓]** テキスト ボックスに、ユーザーの姓を入力します。

    c. [ **電子メール** ] テキスト ボックスに、Azure 内で追加したメール アドレスを入力します。

    d. 一番下までスクロールし、[ **ユーザーの作成**] を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Standard for Success K-12 のサインオン URL にリダイレクトされます。
- Standard for Success K-12 のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Standard for Success K-12 に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Standard for Success K-12] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Standard for Success K-12 に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/starleaf-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に StarLeaf を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/starleaf-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: ユーザー アカウントを StarLeaf に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、StarLeaf と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを StarLeaf に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

このコネクタは、現在プレビューの段階です。 プレビューの詳細については、「 [オンライン サービスのユニバーサル ライセンス条項](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)」を参照してください。

### サポートされている機能

- StarLeaf でユーザーを作成します。
- アクセスが不要になった場合は、StarLeaf のユーザーを削除します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [StarLeaf テナント](https://starleaf.com/)。
- 管理者アクセス許可がある StarLeaf のユーザー アカウント。

### 手順 1: StarLeaf にユーザーを割り当てる

Microsoft Entra IDでは、割り当てと呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、StarLeaf へのアクセスが必要なMicrosoft Entra IDのユーザーとグループを決定する必要があります。 次の [手順](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に従って、StarLeaf にユーザーとグループを割り当てることができます。

#### ユーザーを StarLeaf に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを StarLeaf に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後で追加のユーザーやグループを割り当てることができます。
- StarLeaf にユーザーを割り当てるとき、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 既定のアクセス ロールのユーザーは、プロビジョニングから除外されます。

### 手順 2: プロビジョニング用に StarLeaf を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に StarLeaf を構成する前に、StarLeaf で SCIM プロビジョニングを構成する必要があります。

1. StarLeaf 管理コンソールにサインインします。 **統合**&gt;**統合の追加**に移動します。

    [Image: 統合オプションと [統合の追加] オプションが強調表示されている StarLeaf 管理コンソールのスクリーンショット。]
2. **Type** を「Microsoft Entra ID」に選択します。 **[名前]** に適切な名前を入力します。 [ **適用]** を選択します。

    [Image: [統合の追加] ダイアログ ボックスのスクリーンショット。[種類] ボックスと [名前] テキスト ボックスが強調表示されています。]
3. **SCIM のベース URL** と**アクセス トークン**の値が表示されます。 これらの値は、StarLeaf アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: [統合の編集] ダイアログ ボックスのスクリーンショット。[種類]、[名前]、[SCIM ベース URL] テキスト ボックスが強調表示されています。]

### 手順 3: ギャラリーから StarLeaf を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に StarLeaf を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に StarLeaf を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから StarLeaf を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加**] セクションに「**StarLeaf」**と入力し、結果パネルで **[StarLeaf**] を選択します。

    [Image: 結果一覧の StarLeaf のスクリーンショット。]

### 手順 4: StarLeaf への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて StarLeaf でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps を開く

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **StarLeaf**] を選択します。

    [Image: アプリケーションの一覧の [StarLeaf] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、StarLeaf テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが StarLeaf に接続できることを確認します。 接続に失敗した場合は、StarLeaf アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute Mapping** セクションで、Microsoft Entra IDから StarLeaf に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で StarLeaf のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: 9 つのマッピングが表示されている [属性マッピング] セクションのスクリーンショット。]
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- 現在、StarLeaf ではグループのプロビジョニングはサポートされていません。
- StarLeaf では、同じソース値を持つ **電子メール** と **userName** の値が必要です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/starmind-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Starmind を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/starmind-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra IDから Starmind にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Starmind と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成済みのMicrosoft Entra IDでは、Microsoft Entraプロビジョニングサービスを使用して、ユーザーに[Starmind](https://www.starmind.com/)へのプロビジョニングおよび解除を自動的に行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- Starmind でユーザーを作成します。
- アクセスが不要になった場合は、Starmind のユーザーを削除します。
- Microsoft Entra IDと Starmind の間でユーザー属性の同期を維持します。
- Starmind への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/starmind-tutorial) (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 少なくともユーザー管理者アクセス許可を持つ Starmind のユーザー アカウント。

### プロビジョニング展開を計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- Microsoft Entra IDとStarmindの間でマップするデータを決定します。

### Microsoft Entra IDでのプロビジョニングをサポートするように Starmind を構成する

[Starmind サポート](https://starmind.atlassian.net/servicedesk/customer/portal/2)に連絡して、starmind がMicrosoft Entra IDでのプロビジョニングをサポートできるようにするためのサービス要求を開きます。 ユーザー プロビジョニングを有効にする Starmind ネットワーク ドメイン (acme.starmind.com など) を必ず指定してください。 その後、承認のためにテナント URL とシークレット トークンが提供されます。

### 手順 1: Microsoft Entra アプリケーション ギャラリーから Starmind を追加する

Microsoft Entra アプリケーション ギャラリーから Starmind を追加して、Starmind へのプロビジョニングの管理を開始します。 SSO のために Starmind を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 2: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 3: Starmind への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて Starmind でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Starmind の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Starmind]** を選択します。

    [Image: アプリケーション リストの Starmind リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Starmind テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Starmind に接続できることを確認します。 接続に失敗した場合は、Starmind アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Starmind に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Starmind のユーザー アカウントとの照合に使用されます。 [照合対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合、その属性に基づいたユーザーのフィルター処理を Starmind API がサポートしているか確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Starmind により必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "work"].value | 糸 | ✓ | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 4: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/starmind-tutorial"} -->
## Microsoft Entra ID で Starmind for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/starmind-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Starmind の間でシングル サインオンを構成する方法について説明します。

この記事では、Starmind と Microsoft Entra ID を統合する方法について説明します。 Starmind を Microsoft Entra ID と統合すると、次のことができます。

- Starmind にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Starmind に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Starmind でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Starmind では、 **SP** Initiated SSO がサポートされます。
- Starmind では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Starmind を追加する

Starmind の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Starmind を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「Starmind**」と入力します。
4. 結果パネルから **[Starmind** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Starmind 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Starmind に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Starmind の関連ユーザーとの間にリンク関係を確立する必要があります。

Starmind で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Starmind SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Starmind テストユーザーの作成** - B.Simon に対応するユーザーを Starmind に作成し、Microsoft Entra におけるユーザーと関連付けられます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Starmind**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.starmind.com/auth/realms/<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.starmind.com/auth/realms/<ID>/broker/saml/endpoint`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.starmind.com`

    d. [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.starmind.com/auth/realms/<ID>/broker/saml/endpoint`

    注

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL、およびログアウト URL で更新してください。 これらの値を取得するには [、Starmind クライアント サポート チーム](mailto:support@starmind.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Starmind のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Starmind の SSO の構成

**Starmind** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Starmind サポート チーム](mailto:support@starmind.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Starmind のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Starmind に作成します。 Starmind では、Just-In-Time ユーザー プロビジョニングがサポートされており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Starmind に存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Starmind のサインオン URL にリダイレクトされます。
- Starmind のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Starmind] タイルを選択すると、このオプションは Starmind のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/statuspage-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に StatusPage を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/statuspage-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と StatusPage の間にシングル サインオンを構成する方法について説明します。

この記事では、StatusPage と Microsoft Entra ID を統合する方法について説明します。 StatusPage を Microsoft Entra ID と統合すると、次のことができます。

- StatusPage にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して StatusPage に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- StatusPage でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- StatusPage では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの StatusPage の追加

Microsoft Entra ID への StatusPage の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに StatusPage を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**StatusPage**」と入力します。
4. 結果パネルから **StatusPage** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### StatusPage 用に Microsoft Entra SSO を構成してテストする

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、StatusPage で Microsoft Entra シングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと StatusPage の関連ユーザーの間にリンク関係を確立する必要があります。

StatusPage に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **StatusPage の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **StatusPageのテストユーザーを作成** - Microsoft EntraのユーザーであるBritta Simonに対応するStatusPageのテストユーザーを準備します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**AskYourTeam**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[SAML でシングル サインオンをセットアップします]** ページで、次の手順を実行します。

    ある。 **[識別子]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<subdomain>.statuspagestaging.com/` |
    | `https://<subdomain>.statuspage.io/` |
    |  |

    b。 **[応答 URL]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://<subdomain>.statuspagestaging.com/sso/saml/consume` |
    | `https://<subdomain>.statuspage.io/sso/saml/consume` |
    |  |

    注意

    シングル サインオンを構成するために必要なメタデータは、StatusPage サポート チーム ( SupportTeam@statuspage.io) に連絡して入手してください。

    ある。 メタデータから発行者の値をコピーし、 **[識別子]** ボックスに貼り付けます。

    b。 メタデータから応答 URL をコピーし、 **[応答 URL]** ボックスに貼り付けます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[StatusPage のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### StatusPage の SSO の構成

1. 別の Web ブラウザー ウィンドウで、StatusPage 企業サイトに管理者としてサインインします
2. メイン ツール バーで、[ **アカウントの管理**] を選択します。

    [Image: StatusPage 企業サイトから選択された [Manage Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの管理) のスクリーンショット。]
3. [ **シングル サインオン** ] タブを選択します。
4. [SSO のセットアップ] ページで、次の手順を実行します。

    [Image: [S S O Setup ](S S O の設定) ページを示すスクリーンショット。ここで、説明されている値を入力できます。]

    [Image: [Save Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成を保存) ボタンを示すスクリーンショット。]

    ある。 **[SSO ターゲット URL]** テキストボックスに、**ログイン URL** の値を貼り付けます。

    b。 ダウンロードした証明書をメモ帳で開き、その内容をコピーして、 **[Certificate]** ボックスに貼り付けます。

    c. [ **構成の保存] を選択します**。

#### StatusPage のテスト ユーザーの作成

このセクションの目的は、StatusPage で Britta Simon というユーザーを作成することです。

StatusPage では、ジャストインタイム プロビジョニングがサポートされています。 この機能は、「Microsoft Entra シングル サインオンの構成」で既に有効にしています。

**StatusPage で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. StatusPage 企業サイトに管理者としてサインオンします。
2. 上部のメニューで、[ **アカウントの管理**] を選択します。

    [Image: StatusPage 企業サイトから選択された [Manage Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの管理) のスクリーンショット。]
3. [ **チーム メンバー** ] タブを選択します。
4. [ **チーム メンバーの追加] を選択します**。

    [Image: [チーム メンバーの追加] ボタンのスクリーンショット。]
5. プロビジョニングする有効なユーザーの**電子メール アドレス**、**名**、**姓**を、関連するテキスト ボックスに入力します。
6. **[Role]** で **[Client Administrator]** を選択します。
7. [ **アカウントの作成] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した StatusPage に自動的にサインインします
- Microsoft マイ アプリを使用することができます。 マイ アプリで [StatusPage] タイルを選択すると、SSO を設定した StatusPage に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/steeple-saml-tutorial"} -->
## Microsoft Entra ID で Steeple SAML for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/steeple-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra ID と Steeple SAML の間のシングル サインオンを構成する方法について説明します。

この記事では、Steeple SAML と Microsoft Entra ID を統合する方法について説明します。 Steeple SAML と Microsoft Entra ID を統合すると、次のことができます。

- Steeple SAML にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Steeple SAML に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Steeple SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Steeple SAML では、**SP** Initiated SSO をサポートしています。

### ギャラリーから Steeple SAML を追加する

Microsoft Entra ID への Steeple SAML の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Steeple SAML を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Steeple SAML**」と入力します。
4. 結果パネルから **[Steeple SAML]** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Steeple SAML 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Steeple SAML に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Steeple SAML の関連ユーザーとの間にリンク関係を確立する必要があります。

Steeple SAML に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Steeple SAML SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Steeple SAML テストユーザーの作成** - Microsoft Entra ID の B.Simon に対応するユーザーを Steeple SAML で作成し、リンクします。
3. **SSO をテストする** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Steeple SAML**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://api.steeple.fr/api/v1/idp/<ID>/SAML2`

    b。 **[応答 URL]** ボックスに、`https://api.steeple.fr/api/v1/idp/<ID>/SAML2/POST` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://api.steeple.fr/api/v1/idp/<ID>SAML2/init`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Steeple SAML のサポート チーム](mailto:support@steeple.com)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Steeple SAML アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]
7. その他に、Steeple SAML アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | User.mail |
    | 名（ファーストネーム） | User.givenname |
    | last\_name | User.surname |
    | プロバイダー識別子 | ユーザー.ユーザープリンシパルネーム |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Steeple SAML SSO を構成する

**Steeple SAML** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Steeple SAML サポート チーム](mailto:support@steeple.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Steeple SAML のテスト ユーザーを作成する

このセクションでは、Steeple SAML で B.Simon というユーザーを作成します。 [Steeple SAML サポート チーム](mailto:support@steeple.com)と協力して、Steeple SAML プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Steeple SAML SSO サインオン URL にリダイレクトします。
- Steeple SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Steeple SAML SSO] タイルを選択すると、このオプションは Steeple SAML SSO のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/stonebranch-universal-automation-center-saas-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Stonebranch Universal Automation Center (SaaS Cloud) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/stonebranch-universal-automation-center-saas-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Stonebranch Universal Automation Center (SaaS Cloud) の間でシングル サインオンを構成する方法について説明します。

この記事では、Stonebranch Universal Automation Center (SaaS Cloud) と Microsoft Entra ID を統合する方法について説明します。 Stonebranch Universal Automation Center (SaaS Cloud) と Microsoft Entra ID を統合すると、次のことができます。

- Stonebranch Universal Automation Center (SaaS Cloud) にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Stonebranch Universal Automation Center (SaaS Cloud) に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Stonebranch Universal Automation Center (SaaS Cloud) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Stonebranch Universal Automation Center (SaaS Cloud) では、**SPによるSSOとIDPによるSSO**の両方がサポートされます。
- Stonebranch Universal Automation Center (SaaS Cloud) では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Stonebranch Universal Automation Center (SaaS Cloud) を追加する

Microsoft Entra ID への Stonebranch Universal Automation Center (SaaS Cloud) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Stonebranch Universal Automation Center (SaaS Cloud) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「Stonebranch Universal Automation Center (SaaS Cloud)」**と入力します。
4. 結果パネルから **Stonebranch Universal Automation Center (SaaS Cloud)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Stonebranch Universal Automation Center (SaaS Cloud) の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Stonebranch Universal Automation Center (SaaS Cloud) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Stonebranch Universal Automation Center (SaaS Cloud) の関連ユーザーとの間にリンク関係を確立する必要があります。

Stonebranch Universal Automation Center (SaaS Cloud) に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Stonebranch Universal Automation Center (SaaS Cloud) の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Stonebranch Universal Automation Center (SaaS Cloud) のテストユーザーを作成** - Stonebranch Universal Automation Center (SaaS Cloud) で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Stonebranch Universal Automation Center (SaaS Cloud)**&gt;**シングルサインオンにアクセスします。**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Environment>.uc.stonebranch.com/sp`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName><Environment>.stonebranch.cloud:443/saml/SSO`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName><Environment>.stonebranch.cloud:443`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Stonebranch Universal Automation Center (SaaS Cloud) サポート チーム](mailto:support@stonebranch.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Stonebranch Universal Automation Center (SaaS Cloud) アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: アサーションの画像を表示するスクリーンショット。]
8. 上記に加えて、Stonebranch Universal Automation Center (SaaS Cloud) アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |

    注

    [グループ要求] に移動し、このアプリケーション ボタンに割り当てられたグループを有効にし、Microsoft Entra ID から手動で作成した場合はドロップダウンから [Cloud Only as Source]\(ソースとしてクラウドのみ\) 属性を選択するか、LDAP から同期されたグループの samaccountname を選択して[保存]を選択します。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. [ **Set up Stonebranch Universal Automation Center (SaaS Cloud)]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Stonebranch Universal Automation Center (SaaS Cloud) SSO の構成

**Stonebranch Universal Automation Center (SaaS Cloud)** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Stonebranch ユニバーサル オートメーション センター (SaaS Cloud) サポート チーム](mailto:support@stonebranch.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Stonebranch Universal Automation Center (SaaS Cloud) のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Stonebranch ユニバーサル オートメーション センター (SaaS Cloud) に作成します。 Stonebranch Universal Automation Center (SaaS Cloud) では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Stonebranch Universal Automation Center (SaaS Cloud) にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Stonebranch Universal Automation Center (SaaS Cloud) のサインオン URL にリダイレクトされます。
- Stonebranch Universal Automation Center (SaaS Cloud) のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで **[このアプリケーションをテスト** する] を選択すると、SSO を設定した Stonebranch Universal Automation Center (SaaS Cloud) に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Stonebranch Universal Automation Center (SaaS Cloud)] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Stonebranch Universal Automation Center (SaaS Cloud) に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/storegate-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Storegate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/storegate-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: ユーザー アカウントを Storegate に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、ユーザーやグループを Storegate に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するために、Storegate とMicrosoft Entra IDで実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Storegate でユーザーを作成します。
- アクセスが不要になった場合は、Storegate のユーザーを削除します。
- Microsoft Entra IDと Storegate の間でユーザー属性の同期を維持します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Storegate テナント](https://www.storegate.com)
- 管理者アクセス許可を持つ Storegate のユーザー アカウント。

### 手順 1: Storegate にユーザーを割り当てる

Microsoft Entra IDでは、割り当てと呼ばれる概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Storegate へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定したら、「エンタープライズ アプリにユーザーまたはグループを割り当てる」の手順に従って、これらの [ユーザーやグループを Storegate に割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。

#### ユーザーを Storegate に割り当てる際の重要なヒント

- 1 人のMicrosoft Entra ユーザーを Storegate に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Storegate にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 2: プロビジョニング用に Storegate を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Storegate を構成する前に、Storegate からプロビジョニング情報を取得する必要があります。

1. [Storegate 管理コンソール](https://ws1.storegate.com/identity/core/login?signin=c71fb8fe18243c571da5b333d5437367)にサインインし、右上隅にあるユーザー アイコンを選択して設定に移動し**、[アカウント設定]** を選択します。

    [Image: Storegate の [SCIM 統合の追加] ページのスクリーンショット。]
2. 設定で **[Team](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/チーム) &gt; [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** に移動し、**[Single sign-on](シングル サインオン)** セクションでトグル スイッチがオンになっていることを確認します。

    [Image: Storegate チーム設定ページのスクリーンショット。]

    [Image: Storegate SSO トグル ボタンの設定のスクリーンショット。]
3. **[Tenant URL](テナント URL)** と **[Token](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/トークン)** をコピーします。 これらの値は、Storegate アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドにそれぞれ入力されます。

### 手順 3: ギャラリーから Storegate を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Storegate を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Storegate を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、「**Storegate**」と入力し、結果パネルで **[Storegate]** を選択します。

    [Image: 結果一覧の Storegate のスクリーンショット。]
4. **[Storegate にサインアップ]** ボタンを選択します。Storegate のログイン ページにリダイレクトされます。

    [Image: Storegate OIDC のサインアップ ページのスクリーンショット。]
5. [Storegate 管理コンソール](https://ws1.storegate.com/identity/core/login?signin=c71fb8fe18243c571da5b333d5437367)にサインインし、右上隅にあるユーザー アイコンを選択して設定に移動し**、[アカウント設定]** を選択します。

    [Image: Storegate 管理コンソールのログイン ページのスクリーンショット。]
6. 設定内で **Team &gt; Settings** に移動し、[シングル サインオン] セクションでトグル スイッチを選択すると、同意フローが開始されます。 [**を選択し、**をアクティブ化します。]

    [Image: Storegate チーム設定ページのスクリーンショット。]

    [Image: Storegate SSO 構成トグルのスクリーンショット。]
7. Storegate は OpenIDConnect アプリであるため、Microsoftの職場アカウントを使用して Storegate にログインすることを選択します。

    [Image: Storegate OIDC ログイン ダイアログのスクリーンショット。]
8. 認証に成功した後、同意ページの同意プロンプトを受け入れます。 その後、アプリケーションがテナントに自動的に追加され、Storegate アカウントにリダイレクトされます。

### 手順 4: Storegate への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、Storegate でユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

注

Storegate の SCIM エンドポイントの詳細については、[こちら](https://en-support.storegate.com/article/step-by-step-instruction-how-to-enable-azure-provisioning-to-your-storegate-team-account/)を参照してください。

#### Microsoft Entra ID で Storegate の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: 検索結果一覧の Storegate のスクリーンショット。]
3. アプリケーションの一覧で **[Storegate]** を選択します。

    [Image: アプリケーションの一覧の Storegate リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Storegate テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Storegate に接続できることを確認します。 接続に失敗した場合は、Storegate アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. Microsoft Entra IDから Storegate に同期されるユーザー属性を、**Attribute Mapping** セクションで確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Storegate のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Storegate が要求する |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/stormboard-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Stormboard を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/stormboard-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Stormboard 間のシングル サインオンを構成する方法について説明します。

この記事では、Stormboard と Microsoft Entra ID を統合する方法について説明します。 Stormboard を Microsoft Entra ID と統合すると、次のことができます。

- Stormboard にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Stormboard に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Stormboard でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Stormboard では、**SP および IDP によって開始された SSO** がサポートされます。
- Stormboard では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Stormboard の追加

Microsoft Entra ID への Stormboard の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Stormboard を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Stormboard**」と入力します。
4. 結果パネルから **Stormboard** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Stormboard 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Stormboard に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、Stormboard での関連ユーザーとの間にリンク関係を確立する必要があります。

Stormboard に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Stormboard の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Stormboard テストユーザーの作成** - Stormboard で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Stormboard**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.stormboard.com/saml2/ad/acs/<TEAMID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.stormboard.com/saml2/ad/login/<TEAMID>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには [、Stormboard クライアント サポート チーム](mailto:support@stormboard.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Stormboard のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Stormboard の SSO の構成

**Stormboard** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Stormboard サポート チーム](mailto:support@stormboard.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Stormboard のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Stormboard に作成します。 Stormboard では、 **Just-In-Time ユーザー プロビジョニング**がサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Stormboard にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Stormboard のサインオン URL にリダイレクトされます。
- Stormboard のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Stormboard に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Stormboard タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Stormboard に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/stormshield-network-security-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Stormshield Network Security (OIDC) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/stormshield-network-security-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-10-03
- Summary: Microsoft Entra ID と Stormshield Network Security (OIDC) の間でシングル サインオンを構成する方法について説明します。

この記事では、Stormshield Network Security (OIDC) と Microsoft Entra ID を統合する方法について説明します。 Stormshield Network Security (OIDC) と Microsoft Entra ID を統合すると、次のことができます。

- Stormshield Network Security (OIDC) にアクセスできるユーザーを制御するには、Microsoft Entra ID を使用します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Stormshield Network Security (OIDC) に自動的にサインインできるようにします。
- 1 つの中央の場所 (Microsoft Entra 管理センター) でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Stormshield Network Security (OIDC) でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーからの Stormshield Network Security (OIDC) の追加

Microsoft Entra ID への Stormshield Network Security (OIDC) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Stormshield Network Security (OIDC) を追加する必要があります。

### OIDC プロバイダーの資格情報 (クライアント ID、シークレット、ドメイン、テナント ID) を収集して生成する

最初の手順では、Microsoft Entra ID から必要な情報を収集し、Stormshield ネットワーク セキュリティ ファイアウォールを構成するためのアプリケーション シークレットを生成します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Stormshield Network Security (OIDC)」**と入力します。
4. 結果パネルで **Stormshield Network Security (OIDC)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO を構成する

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Stormshield Network Security (OIDC)**&gt;**シングル サインオン**を参照してください。
3. 次の手順を実行します。

    1. **[アプリケーションに移動]**を選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID を**コピーし、後で Stormshield Network Security (OIDC) 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. [ **エンドポイント** ] タブで、 **OpenID Connect メタデータ ドキュメント** リンクをコピーし、後で Stormshield Network Security (OIDC) 側の構成で使用します。

        [Image: スクリーンショットは、タブ上のエンドポイントを示しています。]

### リダイレクト URI を構成する

Important

この手順により、認証が成功した後、Microsoft Entra ID によってユーザーが正しいファイアウォール URL に戻されることが保証されます。

1. 左側のメニューの [ **認証** ] タブに移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスに、Stormshield Network Security (OIDC) 側からコピーした **リライングパーティリダイレクト URI** の値を貼り付けます。 これらの URI は HTTPS を使用する必要があります。

        [Image: リダイレクト値を示すスクリーンショット。]
    2. **[構成]** ボタンを選択します。
2. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. **[クライアント シークレット**] タブに移動し、[**+新しいクライアント シークレット**] を選択します。
    2. テキスト ボックスに有効な **説明** を入力し、要件に従って [ **有効期限** ] ドロップダウンから有効期限を選択し、[ **追加**] を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、**[値]** が生成されます。 この値をコピーし、後で Stormshield Network Security (OIDC) 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **を選択して**を作成します。

#### アプリケーションのユーザーまたはグループにロールを割り当てる (省略可能)

このセクションでは、アプリケーションの使用とアクセス権の管理を許可されている Microsoft Entra ID のユーザーを構成します。 B.Simon に Stormshield Network Security (OIDC) へのアクセスを許可することで、シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Stormshield Network Security (OIDC)** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. **[ユーザーとグループ]** ダイアログ ボックスの [ユーザー] の一覧で **[B.Simon]** を選択し、画面の下部にある **[選択]** ボタンを選択します。
    2. ユーザーにロールが割り当てられることが想定される場合は、**[ロールの選択]** ドロップダウンからそれを選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Stormshield Network Security (OIDC) SSO の構成

**Stormshield ネットワーク セキュリティ**側でシングル サインオンを構成するには、ユーザー グループをダウンロードして SNS ファイアウォールにインポートする必要があります。 SNS ファイアウォールでのローカル グループ オブジェクトの作成を簡略化するために、Microsoft Entra ID グループの一意識別子 (GUID) を取得できます。

### ユーザー グループをダウンロードして SNS ファイアウォールにインポートする (省略可能)

SNS ファイアウォールでのローカル グループ オブジェクトの作成を簡略化するために、Microsoft Entra ID グループの一意識別子 (GUID) を取得できます。

1. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
2. 目的のグループ ( **例: SNS 認証**) を選択します。
3. グループの **オブジェクト ID** (GUID) をコピーします。

注

SNS ファイアウォールは、オブジェクト ID (GUID) を使用して Microsoft Entra ID からグループを識別します。 次のセクションでは、グループの完全な一覧を CSV 形式で**インポートする**方法について説明します。これは、多数のグループに対して最も効率的な方法です。

#### Microsoft Entra ID 認証用に Stormshield ネットワーク セキュリティ ファイアウォールを構成する

このセクションでは、**Microsoft Entra ID** を介して **OIDC 認証**を有効にするために **Stormshield Network Security (SNS) ファイアウォール**で必要な構成について説明します。

ファイアウォールの Web 管理インターフェイスに**サインイン**します。

#### キャプティブ ポータルにアクセスするためのファイアウォール FQDN を設定する

注

ここで構成された FQDN は、キャプティブ ポータルに正しくアクセスできるように、クライアント ワークステーション上のブラウザーで解決できる必要があります。 これはネットワークの前提条件です。

**System**&gt;**Configuration**&gt;**一般設定**タブ&gt;**Captiveポータル**セクションの:

1. **キャプティブ ポータルへのリダイレクト** フィールドで、**ドメイン名 (FQDN) を指定**する値を選択します。
2. [ **ドメイン名 (FQDN)]** フィールドに、ファイアウォールの完全な名前を入力します (例: `documentation-firewall.stormshield.eu`)。

Important

この FQDN は、Microsoft Entra ID テナントで定義されている **Stormshield アプリケーションで URI を宣言**するときに使用される FQDN と同じである必要があります。

#### この FQDN に基づいてサーバー ID を構成する

このサーバー ID の証明書は、ファイアウォールのキャプティブ ポータルで使用するためのものです。

注

**SSL** VPN アクセスでは、キャプティブ ポータル ID は既にブラウザーに統合されているため、パブリック CA から取得することをお勧めしています。

#### パブリック サーバー ID をインポートする (推奨)

前の手順で定義した FQDN と一致するサーバー証明書をパブリック CA から取得した場合は、ファイアウォールにインポートする必要があります。

1. **[オブジェクト**&gt;**Certificates と PKI** に移動します。
2. [ **ファイルのインポート]** (または **ID のインポート) を**選択します。
3. ウィザードの手順に従って、証明書とその秘密キーをインポートします。

注

ファイアウォールの内部 CA からサーバー ID を生成することもできますが、運用環境では **推奨されません** 。

#### ID を使用するようにキャプティブ ポータルを構成する

ファイアウォールでサーバー ID を使用できるようになったら、次の手順を実行します。

1. **[構成**&gt;**ユーザー**&gt;**認証**] タブに移動します。
2. **SSL サーバー** フィールドセットで、[**証明書 (秘密キー)]** フィールドのドロップダウン メニューを使用して、インポートまたは作成したサーバー ID を選択します。
3. [ **適用]** を選択して、構成への変更を保存します。

#### OIDC 認証方法を有効にする

**[Users**&gt;**Authentication &gt; Available methods]\(使用可能なメソッド\**) タブで、次の手順を実行します。

1. [ **メソッドを有効にする] を選択します**。
2. **OIDC/Microsoft Entra ID を選択します**。 構成ウィザードが自動的に起動します。
3. **ドメイン名**: Microsoft Entra ID 管理センターから取得した メイン ドメイン名 (例: `snsdoc.onmicrosoft.com`) を示します。
4. **テナント ID**: このフィールドに 、Microsoft Entra ID 管理センターから取得した ID を 入力します。
5. **アプリケーション ID (クライアント):**Microsoft Entra ID 管理センターから取得した値を このフィールドに入力します。
6. **クライアント シークレット**: Microsoft Entra ID テナントでの SNS アプリケーションの作成時に**アプリケーションのシークレットを作成**するときに取得および保存された値を入力します。 この値を保存しなかった場合は、前にアプリケーション用に作成した **クライアント シークレット** を削除し、 この手順で説明する手順に従って新しいシークレットを生成する必要があります。
7. **次へ**を選択します。 ウィザードでは、キャプティブ ポータル サービス、 **SSL** VPN サービス、ファイアウォールの Web 管理インターフェイスへのアクセスに対応する URL が提案されます。 これらの URL は、このウィザードから直接コピーして、必要に応じて **Microsoft Entra ID** 管理センターにリダイレクト URL として入力できます。 OIDC/**Microsoft Entra ID** メソッド編集パネルでも使用できます。
8. **次へ**を選択します。
9. ユーザー グループを**ダウンロードして SNS ファイアウォールにインポート**するときにダウンロードした Microsoft Entra ID テナント内のグループを含む CSV ファイルを選択し、[**次へ**] を選択します。 その後、グループのインポート操作の概要が表示されます。
10. **次へ**を選択します。
11. **[完了]** を選択して、構成を確認します。 OIDC/**Microsoft Entra ID** 認証方法編集パネルにリダイレクトされます。
12. [ **適用]** を選択して、 **Microsoft Entra ID** 認証方法の構成をファイアウォールに保存します。

この例では、ファイアウォールでの OIDC/**Microsoft Entra ID** メソッドの構成は、次のようになります。

[Image: スクリーンショットは、SNS OIDC-Entra 構成を示しています。]

#### 認証規則を作成する

**Configuration**&gt;**Users**&gt;**Authentication**&gt;**Authentication ポリシー** タブに移動します。

1. [ **新しいルール** ] を選択し、[ **標準ルール**] を選択します。
2. [**ユーザー**] メニューで [**すべての**ユーザー] を選択します。 **Microsoft Entra ID** を介して認証することで、キャプティブ ポータル、Web 管理インターフェイス、または **SSL** VPN に接続するためのアクセス許可は、テナントに設定されている特権に従って付与されます。
3. [ **ソース** ] メニューで、 **Microsoft Entra ID**によって認証されたユーザーがファイアウォールに表示されるネットワーク インターフェイスを追加します。 この例では、次のインターフェイスが使用されます。
    - **in**: 内部キャプティブ ポータルにアクセスし、Web 管理インターフェイスを介して管理者を認証するインターフェイス。
    - **out**: **SSL** VPN クライアントが構成ファイルの取得とトンネルの設定に使用する外部キャプティブ ポータルにアクセスするためのインターフェイス。
    - **sslvpn**: トンネルのセットアップ時に **ファイアウォール** の SSL VPN サービスにアクセスするために **SSL** VPN クライアントによって使用されるインターフェイス。
4. [ **認証方法** ] メニューで、[ **方法を有効にする** ] を選択し、 **OIDC** メソッドを選択します。
5. 同様に、ユーザーの他の認証方法 ( **LDAP** など) を追加します。
6. [ **OK]** を選択して、この認証規則を確認します。 規則は認証ポリシーに追加されますが、既定では有効になりません。
7. 認証規則グリッドで、ルールの状態を選択して有効にします。

認証規則は次のようになります。

[Image: 認証ポリシーを示すスクリーンショット。]

注

認証中、ルールはリストに表示される順序でスキャンされます。 そのため、必要に応じて **[上へ** ] ボタンと **[下へ]** ボタンと関連するアクション (**Allow**/**Block**) を使用してそれらを整理してください。

ファイアウォールのキャプティブ ポータルでは、 **Microsoft Entra ID** 認証が提供されるようになりました。

[Image: Captif ポータルのログイン ページを示すスクリーンショット]

#### キャプティブ ポータルを構成する

**[構成]**&gt;**[ユーザー]**&gt;**[認証]**&gt;**[Captive ポータル]** タブ:

1. イン**インターフェイスと** **アウト** インターフェイスを追加して、それぞれキャプティブ ポータルの**内部**プロファイルと**外部**プロファイルを関連付けます。
2. ファイアウォールの FQDN に基づいてサーバー ID 証明書を選択します。

キャプティブ ポータルの構成は次のようになります。

[Image: [Captive Portal Configuration](キャプティブ ポータルの構成) ページを示すスクリーンショット。]

#### Microsoft Entra セキュリティ グループをインポートする

**[構成**&gt;**ユーザー**&gt;**ユーザーとグループ&gt; Microsoft Entra ID**] タブに移動します。インポートされた **Microsoft Entra ID** グループとそのグループ ID の一覧がグリッドに表示されます。

**Microsoft Entra ID** 管理センターでグループを追加または編集する場合は、[グループのインポート**] ボタンを**使用してこれらのグループをこのモジュールにインポートし、ユーザー グループをダウンロードするときに **Microsoft Entra ID** テナントからエクスポートされた CSV ファイルを選択して、それらのグループを SNS ファイアウォールにインポートできます。

注

このモジュールを通じてカスタム グループが追加された場合、CSV ファイルのインポート時に上書きされることはありません。 インポートされたグループの名前がカスタム グループと同じである場合は、一意の識別子 (UID) によって区別され、構成内で共存できます。

#### ファイアウォールでアプリケーション ロールを作成する (省略可能)

アプリケーション ロールを使用してユーザーのアクセス許可を管理するには、これらのロールにファイアウォールと **Microsoft Entra ID** テナント アプリケーションで同じ構成が必要です。

注

Stormshield ネットワーク セキュリティ ファイアウォールには、**Microsoft Entra ID** テナント アプリケーションで使用可能なロールを反映する既定のアプリケーション ロールが用意されています。 カスタム ロールを使用する場合にのみ、このセクションで **ロール**を作成または編集する必要があります。

[ **Users &gt; Users**&gt;**Microsoft Entra ID** ] タブに移動します。

1. [ **追加]** を選択し、[ **アプリケーション ロール**] を選択します。
2. 次のフィールドに記入してください。
    - **アプリケーション ロール名** (任意のテキスト)。
    - **アプリケーション ロール UID**。`Actions.Permissions`形式 (`SNS.Config.All.Write`、`SNS.Config.All.Read` など) で構文を使用する必要があります。
    - 省略可能な **説明** (任意のテキスト)。

Important

ロール UID はファイアウォール上で一意であり、**Microsoft Entra ID** テナントで作成された対応するアプリケーション ロールの UID と同じである必要があります。

1. [ **適用]** を選択して、ロールの作成を確認します。
2. [ **適用]** を選択して、構成に対する変更を保存します。

#### Microsoft Entra ID を介して認証されたユーザーに対して SSL VPN を許可する

**設定**&gt;**ユーザー**&gt;**アクセス権限**&gt;**詳細アクセス**タブ:

1. [**] を選択し、[**] を追加します。
2. **Microsoft Entra ID を**有効にし、**Microsoft Entra ID**、カスタム グループ、またはアプリケーション ロールからインポートされたグループを選択します。
3. **を選択して**を適用します。 ルールがグリッドに追加されます。
4. このルールの **[SSL VPN** ] 列を選択し、[許可] を選択 **します**。
5. このルールの **[状態]** 列を選択して有効にします。
6. [ **適用]**、[ **保存] の** 順に選択して、構成の変更を確認します。

[Image: SSL VPN ルールを示すスクリーンショット。]

#### 管理者が Web 管理インターフェイスにアクセスできるようにする

**System**&gt;**Administrators** の場合:

1. [ **管理者の追加] を選択します**。
2. 管理者グループに付与するアクセス許可の種類を選択します。
3. **Microsoft Entra ID を**選択し、**Microsoft Entra ID**、カスタム セキュリティ グループ、またはアプリケーション ロールからインポートされたセキュリティ グループを選択します。
4. **[適用**] を選択して、選択内容を確認します。
5. [ **適用]** を選択して、構成の変更を確認します。

[Image: 管理者アクセス権を示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/striim-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Striim Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/striim-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Striim Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、Striim Cloud と Microsoft Entra ID を統合する方法について説明します。 Striim Cloud と Microsoft Entra ID を統合すると、次のことができます。

- Striim Cloud にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Striim Cloud に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Striim Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Striim Cloud では、**SP と IDP** Initiated の SSO が共にサポートされています。
- Striim Cloud では、**ジャストインタイム** ユーザープロビジョニングがサポートされています。

### ギャラリーから Striim Cloud を追加する

Microsoft Entra ID への Striim Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Striim Cloud を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Striim Cloud**」と入力します。
4. 結果パネル **Striim Cloud** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Striim Cloud の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Striim Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Striim Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Striim Cloud に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Striim Cloud SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Striim Cloud のテスト ユーザーの作成** - Striim Cloud で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Striim Cloud**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    ある。 [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.us-striim.cloud`

    b。 [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.us-striim.cloud/auth/saml/callback`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.us-striim.cloud`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、Striim Cloud サポート チーム  にお問い合わせください。 Microsoft Entra 管理センターの「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. Striim Cloud アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、属性の構成の画像を示しています。]
8. 上記に加えて、Striim Cloud アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | メール | ユーザー メール |
9. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットには、「証明書ダウンロードリンク」が表示されます。]
10. [**Striim Cloud** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピーを示すスクリーンショット。] メタデータ

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Striim Cloud の SSO の構成

Striim Cloud **側** でシングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした URL を Striim Cloud サポート チーム に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Striim Cloud のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Striim Cloud に作成します。 Striim Cloud では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Striim Cloud にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Striim Cloud のサインオン URL にリダイレクトします。
- Striim Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Striim Cloud に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Striim Cloud] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Striim Cloud に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/striim-platform-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Striim Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/striim-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Striim Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Striim Platform と Microsoft Entra ID を統合する方法について説明します。 Striim Platform と Microsoft Entra ID を統合すると、次のことができます。

- Striim Platform にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Striim Platform に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Striim Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Striim Platform は、 **SP** および **IDP** の両方による SSO のイニシエーションをサポートします。
- Striim Platform では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Striim Platform を追加する

Microsoft Entra ID への Striim Platform の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Striim Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Striim Platform**」と入力します。
4. 結果パネルから **Striim Platform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Striim Platform の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Striim Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Striim Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Striim Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Striim Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Striim Platform のテスト ユーザーの作成** - Striim Platform で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Striim Platform**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Striim_IP>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Striim_IP>/saml/callback`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<Striim_IP>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Striim Platform サポート チーム](mailto:fan@striim.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Striim Platform アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: アサーションの画像を表示するスクリーンショット。]
8. 上記に加えて、Striim Platform アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. [ **Striim Platform のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Striim Platform SSO の構成

**Striim Platform** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と Microsoft Entra 管理センターからコピーした適切な URL を [Striim Platform サポート チーム](mailto:fan@striim.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Striim Platform テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Striim Platform に作成します。 Striim Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Striim Platform にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Striim Platform のサインオン URL にリダイレクトします。
- Striim Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Striim Platform に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Striim Platform] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Striim Platform に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/styleflow-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Styleflow を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/styleflow-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Styleflow 間にシングル サインオンを構成する方法について説明します。

この記事では、Styleflow と Microsoft Entra ID を統合する方法について説明します。 Styleflow を Microsoft Entra ID と統合すると、次のことができます:

- Styleflow にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Styleflow に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Styleflow でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Styleflow では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの Styleflow の追加

Microsoft Entra ID への Styleflow の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Styleflow を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Styleflow**」と入力します。
4. 結果パネルから **[スタイルフロー]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Styleflow 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Styleflow に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Styleflow の関連ユーザーとの間にリンク関係を確立する必要があります。

Styleflow に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Styleflow SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Styleflowのテストユーザーを作成する** - StyleflowでB.Simonに対応するユーザーを作成し、それをMicrosoft EntraのB.Simonにリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Styleflow**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.styleflow.jp/kumade/services/trust/<DOMAIN_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.styleflow.jp/kumade/saml@<DOMAIN_ID>?serviceid=cupflow`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 ` https://www.styleflow.jp/kumade/samls@<DOMAIN_ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、Sign-On URL でこれらの値を更新します。 これらの値を取得するには、 [Styleflow クライアント サポート チーム](mailto:styleflow-support@tdc.co.jp) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Styleflow のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Styleflow SSO の構成

**Styleflow** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Styleflow サポート チーム](mailto:styleflow-support@tdc.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Styleflow テスト ユーザーの作成

このセクションでは、Styleflow で Britta Simon というユーザーを作成します。 [Styleflow サポート チーム](mailto:styleflow-support@tdc.co.jp)と協力して、Styleflow プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Check Point Styleflow のサインオン URL にリダイレクトされます。
- Check Point Styleflow のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Check Point Styleflow] タイルを選択すると、このオプションは Check Point Styleflow のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/successfactors-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの SuccessFactors を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/successfactors-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SuccessFactors の間にシングル サインオンを構成する方法について説明します。

この記事では、SuccessFactors と Microsoft Entra ID を統合する方法について説明します。 SuccessFactors と Microsoft Entra ID を統合すると、次のことができます。

- SuccessFactors にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SuccessFactors に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SuccessFactors でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SuccessFactors では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの SuccessFactors の追加

Microsoft Entra ID への SuccessFactors の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに SuccessFactors を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SuccessFactors**」と入力します。
4. 結果パネルで **[SuccessFactors]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SuccessFactors 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、SuccessFactors に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SuccessFactors の関連ユーザーとの間にリンク関係を確立する必要があります。

SuccessFactors に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SuccessFactors SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SuccessFactors のテストユーザーを作成 - Microsoft Entra のユーザーにリンクされた B.Simon に対応するユーザーを SuccessFactors に作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SuccessFactors** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    - `https://<companyname>.successfactors.com/<companyname>`
    - `https://<companyname>.sapsf.com/<companyname>`
    - `https://<companyname>.successfactors.eu/<companyname>`
    - `https://<companyname>.sapsf.eu`

    b。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    - `https://www.successfactors.com/<companyname>`
    - `https://www.successfactors.com`
    - `https://<companyname>.successfactors.eu`
    - `https://www.successfactors.eu/<companyname>`
    - `https://<companyname>.sapsf.com`
    - `https://hcm4preview.sapsf.com/<companyname>`
    - `https://<companyname>.sapsf.eu`
    - `https://www.successfactors.cn`
    - `https://www.successfactors.cn/<companyname>`

    c. [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    - `https://<companyname>.successfactors.com/<companyname>`
    - `https://<companyname>.successfactors.com`
    - `https://<companyname>.sapsf.com/<companyname>`
    - `https://<companyname>.sapsf.com`
    - `https://<companyname>.successfactors.eu/<companyname>`
    - `https://<companyname>.successfactors.eu`
    - `https://<companyname>.sapsf.eu`
    - `https://<companyname>.sapsf.eu/<companyname>`
    - `https://<companyname>.sapsf.cn`
    - `https://<companyname>.sapsf.cn/<companyname>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、および応答 URL で値を更新します。 これらの値を取得するには、[SuccessFactors クライアント サポート チーム](https://www.sap.com/services-support.html)に問い合わせてください。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SuccessFactors のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SuccessFactors SSO の構成

1. 別の Web ブラウザー ウィンドウで、**SuccessFactors 管理者ポータル**に管理者としてログインします。
2. **[アプリケーション セキュリティ]** で **[Single Sign On Feature (シングル サインオン機能)]** に移動します。
3. **[トークンのリセット**] に任意の値を配置し、[**トークンの保存]** を選択して SAML SSO を有効にします。

    [Image: [Application Security](アプリケーション セキュリティ) タブのスクリーンショット。トークンを入力するための [Single Sign On Features](シングル サインオン機能) が強調表示されています。]

    注

    この値は、オン/オフのスイッチとして使用されます。 任意の値を保存すると、SAML SSO はオンになります。 値が空白のまま保存すると、SAML SSO はオフになります。
4. 次のスクリーンショットの画面に移動して、次の操作を実行します。

    [Image: [For SAML-based S S O](SAML ベースの S S O 用) ペインのスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[SAML v2 SSO]** オプションをクリックします。

    b。 **[SAML Asserting Party Name](SAML アサーティング パーティ名)** を設定します (例: SAML 発行者 + 会社名)。

    c. **[発行者 URL]** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    d. **[Require Mandatory Signature](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/必須の署名が必要)** として **[アサーション]** を選択します。

    え **[Enable SAML Flag (SAML フラグを有効にする)]** で **[Enabled (有効にする)]** を選択します。

    f. **[Login Request Signature(SF Generated/SP/RP) (ログイン要求署名 (SF 生成/SP/RP))]** で **[No (いいえ)]** を選択します。

    ジー **[SAML Profile (SAML プロファイル)]** で **[Browser/Post Profile (Browser/Post プロファイル)]** を選択します。

    h. **[Enforce Certificate Valid Period (証明書の有効期間を適用する)]** で **[No (いいえ)]** を選択します。

    一. Azure Portal からダウンロードした証明書ファイルのコンテンツをコピーし、 **[SAML Verifying Certificate](SAML で確認する証明書)** ボックスに貼り付けます。

    注

    この証明書には、証明書の開始タグおよび終了タグが必要です。
5. [SAML V2] に移動して、次の手順に従います。

    [Image: [SAML v2 S P initiated logout](SAML v2 S P initiated ログアウト) ペインのスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[Support SP-initiated Global Logout (SP によって開始されたグローバル ログアウトのサポート)]** で **[Yes (はい)]** を選択します。

    b。 **[Global Logout Service URL (LogoutRequest destination)](グローバル ログアウト サービスの URL (LogoutRequest の送信先))** ボックスに、Azure Portal からコピーした**サインアウト URL** の値を貼り付けます。

    c. **[Require sp must encrypt all NameID element (すべての NameID 要素で SP での暗号化を要求)]** で **[No (いいえ)]** を選択します。

    d. **[NameID Format (NameID の形式)]** で **[unspecified (未指定)]** を選択します。

    え **[Enable sp initiated login (AuthnRequest) (SP によって開始されたログインを有効にする (AuthnRequest))]** で **[Yes (はい)]** を選択します。

    f. 前にコピーした **[ログイン URL]** の値を、**[会社全体の発行者として要求を送信する]** ボックスに貼り付けます。
6. 大文字と小文字を区別しないログイン ユーザー名を作成する場合、次の手順を実行します。

    [Image: シングルサインオンの設定]

    ある。 下部の **[Company Settings (会社設定)]** に移動します。

    b。 **[Enable Non-Case-Sensitive Username](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/大文字と小文字を区別しないユーザー名)** の横のチェック ボックスをオンにします。

    c. **保存** を選択します。

    注

    これを有効にしようとすると、重複する SAML ログイン名が作成されるかどうかがシステムによって確認されます。 たとえば、顧客が User1 および user1 というユーザー名を持っている場合などです。 大文字と小文字の区別をしないと、これらの重複が発生します。 エラー メッセージが表示され、この機能は有効になりません。 顧客はユーザー名の一方を、違うスペルになるように変更する必要があります。

#### SuccessFactors のテスト ユーザーの作成

Microsoft Entra ユーザーが SuccessFactors にサインインできるようにするには、そのユーザーを SuccessFactors にプロビジョニングする必要があります。 SuccessFactors の場合、プロビジョニングは手動で行います。

SuccessFactors でユーザーを作成するには、 [SuccessFactors のサポート チーム](https://www.sap.com/services-support.html)に連絡する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SuccessFactors のサインオン URL にリダイレクトされます。
- SuccessFactors のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SuccessFactors] タイルを選択すると、このオプションは SuccessFactors のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sugarcrm-tutorial"} -->
## Microsoft Entra ID で Sugar CRM for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sugarcrm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SiteISugar CRM の間でシングル サインオンを構成する方法について学習します。

この記事では、Sugar CRM と Microsoft Entra ID を統合する方法について説明します。 SiteISugar CRM を Microsoft Entra ID と統合すると、次のことができます。

- SiteISugar CRM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SiteISugar CRM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sugar CRM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Sugar CRM では、**SP** によって開始される SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Sugar CRM の追加

Microsoft Entra ID への Sugar CRM の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Sugar CRM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Sugar CRM**」と入力します。
4. 結果ウィンドウで **[Sugar CRM]** を選択し、アプリケーションを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SiteISugar CRM 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SiteISugar CRM で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SiteISugar CRM の関連ユーザーとの間にリンク関係を確立する必要があります。

SiteISugar CRM で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sugar CRM の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Sugar CRM のテスト ユーザーを作成** - Microsoft Entra のユーザーである B.Simon にリンクする、Sugar CRM 内の対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;、**Sugar CRM**&gt;、**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    - `https://<companyname>.sugarondemand.com`
    - `https://<companyname>.trial.sugarcrm`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    - `https://<companyname>.sugarondemand.com/<companyname>`
    - `https://<companyname>.trial.sugarcrm.com/<companyname>`
    - `https://<companyname>.trial.sugarcrm.eu/<companyname>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 これらの値を取得するには、[Sugar CRM クライアント サポート チーム](https://support.sugarcrm.com/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up Sugar CRM](Sugar CRM のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sugar CRM の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Sugar CRM 企業サイトに管理者としてサインインします。
2. **[Admin]** に移動します。

    [Image: 管理者]
3. [ **管理** ] セクションで、[ **パスワード管理**] を選択します。

    [Image: [Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) セクションのスクリーンショット。ここで、[Password Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード管理) を選択できます。]
4. **[Enable SAML Authentication]** を選択します。

    [Image: SAML 認証を選択するためのオプションを示すスクリーンショット。]
5. **[SAML Authentication]\(SAML 認証**\) セクションで、次の手順を実行します。

    [Image: SAML 認証]

    ある。 [ **ログイン URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    b。 **[SLO URL]** テキストボックスに **[ログアウト URL]** の値を貼り付けます。

    c. Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーし、証明書全体を **X.509 証明書** ボックスに貼り付けます。

    d. **保存** を選択します。

#### Sugar CRM テスト ユーザーの作成

Microsoft Entra ユーザーが SiteISugar CRM にサインインできるようにするには、そのユーザーを SiteISugar CRM にプロビジョニングする必要があります。 Sugar CRM の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. **Sugar CRM** 企業サイトに管理者としてサインインします。
2. **[Admin]** に移動します。

    [Image: 管理者]
3. [ **管理** ] セクションで、[ **ユーザー管理**] を選択します。

    [Image: [Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) セクションのスクリーンショット。ここで、[User Management](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー管理) を選択できます。]
4. [**ユーザー**] &gt;**[新しいユーザーの作成]** に移動します。

    [Image: 新しいユーザーの作成 新しい]
5. **[User Profile]** タブで、次の手順に従います。

    [Image: [User Profile](ユーザー プロファイル) タブのスクリーンショット。ここで、説明されている値を入力できます。]

    - 関連するテキスト ボックスに、有効な Microsoft Entra ユーザーの **[ユーザー名]**、**[姓]**、および **[メール アドレス]** を入力します。
6. **[Status]** として、 **[Active]** を選択します。
7. [Password] タブで、次の手順に従います。

    [Image: [Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード) タブのスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 該当するテキスト ボックスにパスワードを入力します。

    b。 **保存** を選択します。

注

Sugar CRM から提供される他の Sugar CRM ユーザー アカウント作成ツールや API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Sugar CRM サインオン URL にリダイレクトされます。
- Sugar CRM のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Sugar CRM] タイルを選択すると、このオプションは Sugar CRM のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sumologic-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SumoLogic を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sumologic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SumoLogic 間にシングル サインオンを構成する方法について説明します。

この記事では、SumoLogic と Microsoft Entra ID を統合する方法について説明します。 SumoLogic を Microsoft Entra ID を統合すると、次のことができます:

- SumoLogic にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SumoLogic に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SumoLogic でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SumoLogic では、**IDP** initiated SSO がサポートされます。

### ギャラリーから SumoLogic を追加する

Microsoft Entra ID への SumoLogic の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SumoLogic を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SumoLogic**」と入力します。
4. 結果のパネルから **SumoLogic** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SumoLogic 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SumoLogic に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SumoLogic の関連ユーザーとの間にリンク関係を確立する必要があります。

SumoLogic に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SumoLogic の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SumoLogic テストユーザーの作成** - SumoLogic で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SumoLogic**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    a [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://service.sumologic.com` |
    | `https://<tenantname>.us2.sumologic.com` |
    | `https://<tenantname>.us4.sumologic.com` |
    | `https://<tenantname>.eu.sumologic.com` |
    | `https://<tenantname>.jp.sumologic.com` |
    | `https://<tenantname>.de.sumologic.com` |
    | `https://<tenantname>.ca.sumologic.com` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://service.sumologic.com/sumo/saml/consume/<tenantname>` |
    | `https://service.us2.sumologic.com/sumo/saml/consume/<tenantname>` |
    | `https://service.us4.sumologic.com/sumo/saml/consume/<tenantname>` |
    | `https://service.eu.sumologic.com/sumo/saml/consume/<tenantname>` |
    | `https://service.jp.sumologic.com/sumo/saml/consume/<tenantname>` |
    | `https://service.de.sumologic.com/sumo/saml/consume/<tenantname>` |
    | `https://service.ca.sumologic.com/sumo/saml/consume/<tenantname>` |
    | `https://service.au.sumologic.com/sumo/saml/consume/<tenantname>` |
    |  |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[SumoLogic クライアント サポート チーム](https://www.sumologic.com/contact-us/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. SumoLogic アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、SumoLogic アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | 役割 | user.assignedroles |

    注

    Microsoft Entra ID で[ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/enterprise-app-role-management)を構成する方法については、**こちらを**選択してください。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[SumoLogic のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SumoLogic の SSO の構成

1. 別の Web ブラウザーのウィンドウで、SumoLogic 企業サイトに管理者としてサインインします。
2. [**管理**&gt;セキュリティ] に移動**します**。

    [Image: 管理]
3. **[SAML**] を選択します。

    [Image: グローバル セキュリティ設定]
4. [ **構成の選択] または [新しい構成の作成** ] ボックスの一覧 **で、[Microsoft Entra ID**] を選択し、[ **構成**] を選択します。

    [Image: [Configure SAML 2.0](SAML 2.0 の構成) を示すスクリーンショット。ここで [Microsoft Entra ID] を選択できます。]
5. **[Configure SAML 2.0]** ダイアログで、次の手順に従います。

    [Image: [Configure SAML 2.0](SAML 2.0 の構成) ダイアログ ボックスを示すスクリーンショット。ここで、説明されている値を入力できます。]

    a **[Configuration Name]** テキスト ボックスに、「**Microsoft Entra ID**」と入力します。

    b。 **[Debug Mode]** を選択します。

    c. **[発行者**] ボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    d. **[Authn Request URL]** テキストボックスに、**ログイン URL** の値を貼り付けます。

    え Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーし、証明書全体を **X.509 証明書** ボックスに貼り付けます。

    f. **[Email Attribute]** として、 **[Use SAML subject]** を選択します。

    ジー **[SP initiated Login Configuration]** を選択します。

    h. [ **ログイン パス** ] ボックスに「 **Azure** 」と入力し、[ **保存]** を選択します。

#### SumoLogic のテスト ユーザーの作成

Microsoft Entra ユーザーが SumoLogic にサインインできるようにするには、そのユーザーを SumoLogic にプロビジョニングする必要があります。 SumoLogic の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. **SumoLogic** テナントにサインインします。
2. **管理**&gt;**ユーザー** に移動します。

    [Image: [管理] メニューから [ユーザー] が選択された状態を示すスクリーンショット。]
3. [**] を選択し、[**] を追加します。

    [Image: ユーザーの [追加] ボタンを示すスクリーンショット。]
4. **[New User]** ダイアログ ページで、次の手順に従います。

    [Image: 新しいユーザー]

    a プロビジョニングする Microsoft Entra アカウントに関連する情報を、**[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)**、**[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)**、および **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** ボックスに入力します。

    b。 ロールを選択します。

    c. **[Status]** として、 **[Active]** を選択します。

    d. **保存** を選択します。

注

他の SumoLogic ユーザー アカウント作成ツールや、SumoLogic から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SumoLogic に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで SumoLogic タイルを選択すると、SSO を設定した SumoLogic に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sumtotalcentral-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SumTotalCentral を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sumtotalcentral-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SumTotalCentral の間にシングル サインオンを構成する方法について説明します。

この記事では、SumTotalCentral と Microsoft Entra ID を統合する方法について説明します。 SumTotalCentral と Microsoft Entra ID を統合すると、次のことができます:

- SumTotalCentral にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SumTotalCentral に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SumTotalCentral でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SumTotalCentral では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから SumTotalCentral を追加する

Microsoft Entra ID への SumTotalCentral の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに SumTotalCentral を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション** に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「SumTotalCentral**」と入力します。
4. 結果パネルから **SumTotalCentral** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SumTotalCentral に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SumTotalCentral の関連ユーザーとの間にリンク関係を確立する必要があります。

SumTotalCentral に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SumTotalCentral SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **SumTotalCentral テスト ユーザーの作成** - Britta Simon に対応するユーザーを SumTotalCentral に作成し、Microsoft Entra の Britta Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**SumTotalCentral**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] のスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.sumtotalsystems.com/sites/default`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `SumTotalFederationGateway`

    c. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。`https://<subdomain>.sumtotalsystems.com/Broker/Token/CUSTOM_URL`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL で値を更新してください。 この値を取得するには [、SumTotalCentral クライアント サポート チーム](http://www.sumtotalsystems.com/support/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. [ **SumTotalCentral のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SumTotalCentral の SSO の構成

**SumTotalCentral** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [SumTotalCentral サポート チーム](http://www.sumtotalsystems.com/support/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SumTotalCentral のテスト ユーザーの作成

このセクションでは、SumTotalCentral で Britta Simon というユーザーを作成します。 [SumTotalCentral サポート チーム](http://www.sumtotalsystems.com/support/)と協力して、SumTotalCentral プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SumTotalCentral のサインオン URL にリダイレクトされます。
- SumTotalCentral のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで SumTotalCentral タイルを選択すると、このオプションは SumTotalCentral のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/superannotate-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SuperAnnotate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/superannotate-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SuperAnnotate の間にシングル サインオンを構成する方法について学習します。

この記事では、SuperAnnotate と Microsoft Entra ID を統合する方法について説明します。 SuperAnnotate はオールインワンの AI データ インフラストラクチャ プラットフォームであり、ML とデータ チームが最高品質のトレーニング データである SuperData を使用して正確な AI モデルを構築する時間を節約するのに役立ちます。 SuperAnnotate を Microsoft Entra ID と統合すると、次のことができるようになります。

- SuperAnnotate にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SuperAnnotate に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で SuperAnnotate 用の Microsoft Entra シングル サインオンを構成してテストします。 SuperAnnotate では、**SP** によって開始されたシングル サインオンのみがサポートされます。

### [前提条件]

Microsoft Entra ID を SuperAnnotate と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な SuperAnnotate のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから SuperAnnotate アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから SuperAnnotate を追加する

Microsoft Entra アプリケーション ギャラリーから SuperAnnotate を追加して、SuperAnnotate とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SuperAnnotate**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`urn:amazon:cognito:sp:<USER_POOL_ID>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN PREFIX>.auth.<REGION>.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] ボックスに、URL を入力します。 `https://auth.superannotate.com/login`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[SuperAnnotate サポート チーム](mailto:support@superannotate.com)にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. SuperAnnotate アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、SuperAnnotate アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザーのグループ [ApplicationGroup] |
8. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、**[アプリのフェデレーション メタデータ URL]** をコピーするか、**[フェデレーション メタデータ XML]** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### SuperAnnotate SSO の構成

**SuperAnnotate** 側でシングル サインオンを構成するには、SuperAnnotate 側の SSO 設定ページで、コピーした **[アプリのフェデレーション メタデータ URL]** またはダウンロードした **[フェデレーション メタデータ XML]** を設定して、双方で SAML SSO 接続を正しく設定する必要があります。

#### SuperAnnotate テスト ユーザーの作成

このセクションでは、SuperAnnotate で Britta Simon というユーザーを作成します。 [SuperAnnotate サポート チーム](mailto:support@superannotate.com)と連携し、SuperAnnotate プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SuperAnnotate のサインオン URL にリダイレクトされます。
- SuperAnnotate のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SuperAnnotate] タイルを選択すると、このオプションは SuperAnnotate のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/superluminal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Superluminal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/superluminal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Superluminal の間でシングル サインオンを構成する方法について説明します。

この記事では、Superluminal と Microsoft Entra ID を統合する方法について説明します。 Superluminal と Microsoft Entra ID を統合すると、次のことができます。

- スーパールミナルにアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Superluminal に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Superluminal でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Superluminal では、 **SP** によって開始される SSO のみがサポートされます。
- Superluminal では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからスーパールミナルを追加する

Microsoft Entra ID への Superluminal の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Superluminal を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Superluminal**」と入力します。
4. 結果パネルから **[スーパールミナル** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Superluminal の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Superluminal に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Superluminal の関連ユーザーとの間にリンク関係を確立する必要があります。

Superluminal に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Superluminal SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Superluminal テスト ユーザーの作成 - Superluminal** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Superluminal**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://superluminal.eu`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://portal.superluminal.eu/Identity/Account/SSO/SamlACS`

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://portal.superluminal.eu/Identity/Account/Login` |
    | `https://portal.superluminal.eu/Identity/Account/LoginSSO` |
6. Superluminal アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、Superluminal アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザー.メール | ユーザーのメールアドレス |
    | ユーザー名.ファーストネーム | ユーザー.ファーストネーム |
    | ユーザー.姓 | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Superluminal SSO の構成

両方の側で SAML SSO 接続を適切に設定する方法については [、この](https://portal.superluminal.eu/Documentation#sso) 記事を参照するか、クエリ担当者の [Superluminal サポート チーム](mailto:info@superluminal.eu) にお問い合わせください。

#### スーパールミナル テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Superluminal に作成します。 Superluminal では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Superluminal にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Superluminal のサインオン URL にリダイレクトします。
- Superluminal のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [スーパールミナル] タイルを選択すると、このオプションはスーパールミナル サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/supermood-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Supermood を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/supermood-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Supermood の間にシングル サインオンを構成する方法について説明します。

この記事では、Supermood と Microsoft Entra ID を統合する方法について説明します。 Supermood を Microsoft Entra ID を統合すると、次のことができます:

- Supermood にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Supermood に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Supermood でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Supermood では、**SP および IDP による SSO** がサポートされます
- Supermood では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Supermood の追加

Microsoft Entra ID への Supermood の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Supermood を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Supermood**」と入力します。
4. 結果パネルから **Supermood** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Supermood の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Supermood に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Supermood の関連ユーザーとの間にリンク関係を確立する必要があります。

Supermood に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Supermood SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Supermood テストユーザーを作成 - B.Simon に対応するユーザーを Supermood に作成し、Microsoft Entra にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Supermood**&gt;**シングルサインオンに移動します。**
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ａ **追加の URL の設定**を確認します。

    b。 [ **リレー状態** ] ボックスに、URL を入力します。 `https://supermood.co/auth/sso/saml20`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://supermood.co/app/#!/loginv2`
7. **[保存] を選択します**。
8. Supermood アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
9. その他に、Supermood アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | 苗字 | User.surname |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Supermood の SSO の構成

1. セキュリティ管理者として、Supermood.co 管理パネルに移動します。
2. [ **マイ アカウント** ] (左下) と **[シングル サインオン (SSO)]**を選択します。
3. **SAML 2.0 構成**で、**電子メール ドメインの SAML 2.0 構成の追加を選択します**。

    [Image: 証明書の追加]
4. **メールドメインに対する SAML 2.0 設定を追加**を選択します。 セクションで、次の手順に従います。

    [Image: 証明書 saml]

    ａ **この ID プロバイダーのテキスト ボックスの電子メール ドメイン**に、ドメインを入力します。

    b。 [ **メタデータ URL の使用** ] ボックスに、 **アプリのフェデレーション メタデータ URL を**貼り付けます。

    c. **追加**を選択します。

#### Supermood のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Supermood に作成します。 Supermood では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Supermood にユーザーがまだ存在していない場合は、認証後に新しく作成されます。 ユーザーを手動で作成する必要がある場合は、 [Supermood サポート チーム](mailto:hello@supermood.fr)にお問い合わせください。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra シングル サインオン構成をテストします。

アクセス パネルで [Supermood] タイルを選択すると、SSO を設定した Supermood に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/supply-chain-catalyst-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にサプライ チェーン Catalyst を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/supply-chain-catalyst-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Supply Chain Catalyst の間でシングル サインオンを構成する方法について説明します。

この記事では、サプライ チェーン Catalyst と Microsoft Entra ID を統合する方法について説明します。 サプライ チェーン Catalyst と Microsoft Entra ID を統合すると、次のことができます。

- サプライ チェーン Catalyst にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用してサプライ チェーン Catalyst に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Supply Chain Catalyst でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Supply Chain Catalyst は、**SP と IDP** による SSO の両方をサポートします。
- サプライ チェーン Catalyst では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからサプライチェーンのカタリストを追加する

Microsoft Entra ID へのサプライ チェーン Catalyst の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧にサプライ チェーン Catalyst を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Supply Chain Catalyst**」と入力します。
4. 結果パネルから **サプライ チェーン Catalyst** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### サプライ チェーン Catalyst の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、サプライ チェーン Catalyst に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーとサプライ チェーン Catalyst の関連ユーザーとの間にリンク関係を確立する必要があります。

サプライ チェーン Catalyst に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Supply Chain Catalyst SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Supply Chain Catalyst のテストユーザーの作成** - Microsoft Entra に表されるユーザーと連携する Supply Chain Catalyst 内の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Supply Chain Catalyst**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://authenticate.bvdep.com/<CUSTOMER_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://authenticate.bvdep.com/<CUSTOMER_ID>/Shibboleth.sso/SAML2/POST`

    c. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://authenticate.bvdep.com/<CUSTOMER_ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.bvdinfo.com/supplychaincatalyst/sso/<CUSTOMER_ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、リレー状態、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [サプライ チェーン Catalyst サポート チーム](mailto:help@bvdinfo.com) にお問い合わせください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Supply Chain Catalyst SSO の設定

**サプライ チェーン Catalyst** 側でシングル サインオンを構成するには、サプライ **チェーン Catalyst サポート チーム**に[アプリフェデレーション メタデータ URL を](mailto:help@bvdinfo.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### サプライ チェーン Catalyst テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーをサプライ チェーン Catalyst に作成します。 サプライ チェーン Catalyst では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 サプライ チェーン Catalyst にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できるサプライ チェーン Catalyst サインオン URL にリダイレクトします。
- サプライ チェーン Catalyst のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定したサプライ チェーン Catalyst に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Supply Chain Catalyst] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したサプライ チェーン Catalyst に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/surfconext-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SURFconext を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/surfconext-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SURFconext の間にシングル サインオンを構成する方法について説明します。

この記事では、SURFconext と Microsoft Entra ID を統合する方法について説明します。 SURF に接続されたインスティテューションでは、SURFconext でインスティテューションの資格情報を使用してさまざまなクラウド アプリケーションにログインできます。 SURFconext を Microsoft Entra ID を統合すると、次のことができます:

- SURFconext にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SURFconext に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で SURFconext 向けの Microsoft Entra のシングル サインオンを構成してテストします。 SURFconext は、**SP** Initiated シングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を SURFconext と統合するには、次のものが必要です:

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SURFconext のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから SURFconext アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから SURFconext を追加する

Microsoft Entra アプリケーション ギャラリーから SURFconext を追加して、SURFconext とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SURFconext**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://engine.surfconext.nl/authentication/sp/metadata` |
    | ステージング | `https://engine.test.surfconext.nl/authentication/sp/metadata` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://engine.surfconext.nl/authentication/sp/consume-assertion` |
    | ステージング | `https://engine.test.surfconext.nl/authentication/sp/consume-assertion` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://engine.surfconext.nl/authentication/sp/debug` |
    | ステージング | `https://engine.test.surfconext.nl/authentication/sp/debug` |
6. SURFconext アプリケーションでは、特定の形式の SAML アサーションを使用するため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]

    注

    これらの既定の属性は、必要ない場合は、[追加の要求] セクションで手動で削除できます。
7. その他に、SURFconext アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:mace:dir:attribute-def:cn | ユーザー表示名 |
    | urn:mace:dir:attribute-def:displayName | ユーザー表示名 |
    | urn:mace:dir:attribute-def:eduPersonPrincipalName | ユーザー.ユーザープリンシパルネーム |
    | urn:mace:dir:attribute-def:givenName | ユーザー.ファーストネーム |
    | urn:mace:dir:attribute-def:mail | ユーザーのメールアドレス |
    | urn:mace:dir:attribute-def:preferredLanguage | ユーザーの優先言語 |
    | urn:mace:dir:attribute-def:sn（属性定義の識別子） | ユーザーの名字 |
    | urn:mace:dir:attribute-def:uid | ユーザー.ユーザープリンシパルネーム |
    | urn:mace:terena.org:attribute-def:schacHomeOrganization | ユーザー.ユーザープリンシパルネーム |
8. **urn:mace:terena.org:attribute-def:schacHomeOrganization** 要求に対して変換操作を実行するには、**[要求の管理]** セクションの [ソース] として **[変換]** ボタンを選択します。
9. **[変換の管理]** ページで、次の手順を実行します。

    [Image: Azure portal の属性を示すスクリーンショット。]

    1. [**変換**] フィールドのドロップダウンから **[Extract()**] を選択し、[**一致後**] ボタンを選択します。
    2. **[Parameter 1 (Input)] (パラメーター 1 (入力))** として **[属性]** を選択します。
    3. **[属性名]** フィールドで、ドロップダウンから **[user.userprinciplename]** を選択します。
    4. ドロップダウンから値 **@** を選択します。
    5. [**] を選択し、[**] を追加します。
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### SURFconext SSO を構成する

**SURFconext** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [SURFconext サポート チーム](mailto:support@surfconext.nl)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SURFconext テスト ユーザーを作成する

このセクションでは、B. Simon というユーザーを SURFconext に作成します。 SURFconext では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 SURFconext にユーザーがまだ存在していない場合、一般に認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SURFconext のサインオン URL にリダイレクトされます。
- SURFconext のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで SURFconext タイルを選択すると、このオプションは SURFconext のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/surfsecureid-azure-mfa-tutorial"} -->
## SURFsecureID の構成 - Microsoft Entra ID を使用したシングル サインオン用の Azure MFA - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/surfsecureid-azure-mfa-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SURFsecureID - Azure MFA の間にシングル サインオンを構成する方法について説明します。

この記事では、SURFsecureID - Azure MFA と Microsoft Entra ID を統合する方法について説明します。 SURFsecureID - Azure MFA を Microsoft Entra ID を統合すると、次のことができます。

- SURFsecureID - Azure MFA にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って SURFsecureID - Azure MFA に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SURFsecureID - Azure MFA でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SURFsecureID - Azure MFA では、**SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから SURFsecureID - Azure MFA を追加する

Microsoft Entra ID への SURFsecureID - Azure MFA の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SURFsecureID - Azure MFA を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SURFsecureID - Azure MFA**」と入力します。
4. 結果のパネルから **[SURFsecureID - Azure MFA]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SURFsecureID - Azure MFA 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、SURFsecureID - Azure MFA に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SURFsecureID - Azure MFA の関連ユーザーとの間にリンク関係を確立する必要があります。

SURFsecureID - Azure MFA に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SURFsecureID - Azure MFA の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SURFsecureID - Azure MFA テスト ユーザーの作成** - SURFsecureID - Azure MFA で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**SURFsecureID - Azure MFA**&gt;**シングルサインオンを参照してください**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかの URL を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | ステージング | `https://azuremfa.test.surfconext.nl/saml/metadata` |
    | 生産 | `https://azuremfa.surfconext.nl/saml/metadata` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | ステージング | `https://azuremfa.test.surfconext.nl/saml/acs` |
    | 生産 | `https://azuremfa.surfconext.nl/saml/acs` |

    b。 [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **環境** | **URL** |
    | --- | --- |
    | ステージング | `https://sa.test.surfconext.nl` |
    | 生産 | `https://sa.surfconext.nl` |
6. SURFsecureID - Azure MFA アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、SURFsecureID - Azure MFA アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:mace:dir:attribute-def:mail | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SURFsecureID - Azure MFA の SSO の構成

**SURFsecureID - Azure MFA** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [SURFsecureID - Azure MFA サポート チーム](mailto:support@surfconext.nl)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SURFsecureID - Azure MFA のテスト ユーザーの作成

このセクションでは、SURFsecureID - Azure MFA で Britta Simon というユーザーを作成します。 [SURFsecureID - Azure MFA サポート チーム](mailto:support@surfconext.nl)と連携して、SURFsecureID - Azure MFA プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる SURFsecureID - Azure MFA サインオン URL にリダイレクトされます。
- SURFsecureID - Azure MFA のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで SURFsecureID - Azure MFA タイルを選択すると、このオプションは SURFsecureID - Azure MFA サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/surveymonkey-enterprise-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に SurveyMonkey Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/surveymonkey-enterprise-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra IDから SurveyMonkey Enterprise にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために SurveyMonkey Enterprise とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成されたMicrosoft Entra IDが、Microsoft Entra プロビジョニング サービスを使用してユーザーを[SurveyMonkey Enterprise](https://www.surveymonkey.com/)に自動的にプロビジョニングおよび削除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- SurveyMonkey Enterprise にユーザーを作成します。
- アクセスが不要になったら、SurveyMonkey Enterprise のユーザーを削除します。
- Microsoft Entra IDと SurveyMonkey Enterprise の間でユーザー属性の同期を維持します。
- SurveyMonkey Enterprise に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/surveymonkey-enterprise-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者またはプライマリ管理者のアクセス許可がある SurveyMonkey Enterprise のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとSurveyMonkey Enterpriseの間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDを使用したプロビジョニングをサポートするように SurveyMonkey Enterprise を構成する

#### SCIM プロビジョニングを設定する

組織の SCIM プロビジョニングを設定できるのは、プライマリ管理者のみです。 SCIM が IdP に最適であることを確認するには、プライマリ管理者が SurveyMonkey Customer Success Manager (CSM) とその組織の IT 部門とともにチェックインする必要があります。

チームが配置されると、プライマリ管理者は次のことを行えます。

1. [**\[設定\]**](https://www.surveymonkey.com/team/settings/) に移動します。
2. **[SCIM を使用した自動ユーザー プロビジョニング]** を選択します。
3. SCIM エンドポイント リンクをコピーし、それを IT パートナーに提供します。
4. [ **トークンの生成]** を選択します。 この一意のトークンは、プライマリ管理者パスワードと同様に扱い、IT パートナーにのみ付与します。

組織の IT パートナーは、IdP のセットアップ中に SCIM エンドポイント リンクとアクセス トークンを使用します。 また、チームのニーズに合わせて既定のマッピングを調整する必要もあります。

#### SCIM プロビジョニングを取り消す

システムが同期しなくなったために Surveymonkey を IdP から切断する必要がある場合、プライマリ管理者は SCIM プロビジョニングを取り消すことができます。 SSO が有効になっている限り、既に同期されているユーザーには影響しません。

SCIM プロビジョニングを取り消すには:

1. [**\[設定\]**](https://www.surveymonkey.com/team/settings/) に移動します。
2. **[SCIM を使用した自動ユーザー プロビジョニング]** を選択します。
3. アクセス トークンの横で **[取り消す]** を選択します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから SurveyMonkey Enterprise を追加する

Microsoft Entra アプリケーション ギャラリーから SurveyMonkey Enterprise を追加して、SurveyMonkey Enterprise へのプロビジョニングの管理を開始します。 SSO のために SurveyMonkey Enterprise を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: SurveyMonkey Enterprise への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて SurveyMonkey Enterprise のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で SurveyMonkey Enterprise の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise apps に移動します

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、**[SurveyMonkey Enterprise]** を選択します。

    [Image: アプリケーションの一覧の SurveyMonkey Enterprise のリンクを示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、SurveyMonkey Enterprise テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが SurveyMonkey Enterprise に接続できることを確認します。 接続に失敗した場合は、SurveyMonkey Enterprise アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから SurveyMonkey Enterprise に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で SurveyMonkey Enterprise のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、SurveyMonkey Enterprise API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | SurveyMonkey Enterprise で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 活動中 | ブール値 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | externalId | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/surveymonkey-enterprise-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SurveyMonkey Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/surveymonkey-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と SurveyMonkey Enterprise の間でシングル サインオンを構成する方法について説明します。

この記事では、SurveyMonkey Enterprise と Microsoft Entra ID を統合する方法について説明します。 SurveyMonkey Enterprise と Microsoft Entra ID を統合すると、次のことができます。

- SurveyMonkey Enterprise にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SurveyMonkey Enterprise に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

SurveyMonkey Enterprise は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- SurveyMonkey Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SurveyMonkey Enterprise では、 **IDP** によって開始される SSO がサポートされます。
- SurveyMonkey Enterprise では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/surveymonkey-enterprise-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから SurveyMonkey Enterprise を追加する

Microsoft Entra ID への SurveyMonkey Enterprise の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SurveyMonkey Enterprise を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「SurveyMonkey Enterprise**」と入力します。
4. 結果パネルから **SurveyMonkey Enterprise** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SurveyMonkey Enterprise の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SurveyMonkey Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SurveyMonkey Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

SurveyMonkey Enterprise に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SurveyMonkey Enterprise SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SurveyMonkey Enterprise のテスト ユーザーを作成** - Microsoft Entra の B.Simon とリンクするために、SurveyMonkey Enterprise で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SurveyMonkey Enterprise**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. SurveyMonkey Enterprise アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、SurveyMonkey Enterprise アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザーのメールアドレス |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **SurveyMonkey Enterprise のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SurveyMonkey Enterprise SSO の構成

**SurveyMonkey Enterprise** 側でシングル サインオンを構成するには、[この](https://help.surveymonkey.com/teams/single-sign-on/#set-up)記事を参照してください。

#### SurveyMonkey Enterprise テスト ユーザーの作成

SurveyMonkey Enterprise でテスト ユーザーを作成する必要はありません。 ユーザー アカウントは、ユーザーが SAML アサーションに基づいて新しいアカウントを作成することを選択した場合にプロビジョニングされます。 SurveyMonkey Enterprise Customer Success Manager では、Azure メタデータが SurveyMonkey Enterprise 構成に追加され、検証の準備ができたら、このプロセスを完了するための手順が提供されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SurveyMonkey Enterprise に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SurveyMonkey Enterprise] タイルを選択すると、SSO を設定した SurveyMonkey Enterprise に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/swit-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Swit を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/swit-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-14
- Summary: Microsoft Entra ID から Swit にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Swit ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [Swit](https://swit.io) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Swit でユーザーを作成します。
- アクセスが不要になったら、Swit のユーザーを削除します。
- Microsoft Entra ID と Swit の間でユーザー属性の同期を維持する
- Swit でグループとグループメンバーシップを設定します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者のアクセス許可がある Swit のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Swit の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Swit を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように、Swit を構成し、`help@swit.io`にメールを送信します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Swit を追加する

Microsoft Entra アプリケーション ギャラリーから Swit を追加して、Swit へのプロビジョニングの管理を開始します。 シングル サインオン (SSO) 用に Swit を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5:Swit への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Swit でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Swit の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Swit]** を選択します。

    [Image: アプリケーションの一覧の [Swit] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **管理者資格情報** ] セクションで 、[承認] を選択し、Swit アカウントの管理者資格情報を入力していることを確認します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Swit に接続できることを確認します。 接続できない場合は、使用中の Swit アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: [Swit 承認トークン] ダイアログのスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Swit に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Swit のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Swit API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Swit で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | displayName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
    | 優先言語 | 糸 |  |  |
12. **[グループ] を選択します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Swit に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Swit のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Swit で必要 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、組織内でより広範に展開する前に、少数のユーザーとの同期を検証します。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/swit-tutorial"} -->
## Microsoft Entra ID で Swit for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/swit-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Swit の間でシングル サインオンを構成する方法について説明します。

この記事では、Swit と Microsoft Entra ID を統合する方法について説明します。 Swit を Microsoft Entra ID と統合すると、次のことができます。

- Swit にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Swit に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Swit は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Swit でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Swit は、**SP** initiated SSO をサポートします。

### ギャラリーから Swit を追加する

Microsoft Entra ID への Swit の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Swit を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Swit**」と入力します。
4. 結果パネルから **[Swit]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Swit 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、Swit に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Swit の関連ユーザーとの間にリンク関係を確立する必要があります。

Swit に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Swit SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Swit テスト ユーザーを作成** - B.Simon に対応する Swit のユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Swit]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<OrgName>.swit.io`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://saml.swit.io/saml/acs`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://swit.io/auth/login?subdomain=<OrgName>`

    注

    これらの値は実際の値ではありません。 値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Swit サポート チーム](mailto:help@swit.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Swit アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]

    注

    上記の既定の属性の一覧から、**givenname** を **firstname に**、**surname** を **lastname に**、name を ユーザー名に に置き換え、Swit アプリケーションの要件に従って emailaddress クレームを削除してください。
7. 次に示す省略可能な要求は、ユーザーにマップし、要件に基づいて SAML 応答で返すことができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 言語 | ユーザーの優先言語 |
    | tel | ユーザー.電話番号 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Swit のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Swit SSO を設定

1. Swit 企業サイトに管理者としてログインします。
2. [管理] ページの左下隅にある **[管理コンソール]** に移動し、**[SAML 構成]** を選択します。
3. **[SAML 構成]** ページで、次の手順を実行します。

    [Image: SSO 構成を示すスクリーンショット。]

    a. **[SAML でのシングル サインオンを有効にする]** ボタンを選択します。

    b。 **[SAML 2.0 Endpoint (HTTP)]** ボックスに、先にコピーした**ログイン URL** の値を貼り付けます。

    c. **[ID プロバイダー 発行者 (エンティティ ID)]** テキスト ボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    d. ダウンロードした **証明書 (Base64)** をメモ帳に開き、[ **パブリック証明書** ] ボックスに内容を貼り付けます。

    e. ドロップダウンから **[許可されたサインイン方法]** を選択します。

    f. **保存** を選択します。

#### Swit テストユーザーの作成

1. 別の Web ブラウザー ウィンドウで、Swit 企業サイトに管理者としてログインします。
2. 管理コンソールメンバー&チームに移動し、招待を選択します。
3. **[招待]** ページで、次の手順を実行します。

    [Image: SSO の メンバーを示すスクリーンショット。]

    a. **[電子メールで招待する]** テキストボックスに、有効なメール アドレスを入力します。

    b。 ドロップダウン メニューから **[ロール]** を選択します。

    c. ドロップダウンメニューから **[プライマリ チーム]** を選択します。

    d. [ **招待の送信]** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Swit のサインオン URL にリダイレクトされます。
- Swit のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Swit] タイルを選択すると、このオプションは Swit のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/symantec-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Symantec Web Security Service (WSS) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/symantec-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Symantec Web Security Service (WSS) の間でシングル サインオンを構成する方法について説明します。

この記事では、Symantec Web Security Service (WSS) アカウントを Microsoft Entra アカウントと統合して、WSS が SAML 認証を使用して Microsoft Entra ID でプロビジョニングされたエンド ユーザーを認証し、ユーザーまたはグループ レベルのポリシー 規則を適用できるようにする方法について説明します。

Symantec Web Security Service (WSS) と Microsoft Entra ID の統合には、次の利点があります。

- WSS アカウントによって使用されるすべてのエンド ユーザーとグループを Azure portal から管理できます。
- エンド ユーザーは、Microsoft Entra 資格情報を使用して WSS で自己認証を行うことができます。
- WSS アカウントに定義されたユーザー レベルおよびグループ レベルのポリシー ルールを適用できます。

Symantec Web Security Service (WSS) は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Symantec Web Security Service (WSS) シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Symantec Web Security Service (WSS) では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Symantec Web Security Service (WSS) を追加する

Microsoft Entra ID への Symantec Web Security Service (WSS) の統合を構成するには、管理対象の SaaS アプリの一覧にギャラリーから Symantec Web Security Service (WSS) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Symantec Web Security Service (WSS)** 」と入力します。
4. [結果] パネルで **[Symantec Web Security Service (WSS)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Symantec Web Security Service (WSS) に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Symantec Web Security Service (WSS) に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するために、Microsoft Entra ユーザーと Symantec Web Security Service (WSS) の関連ユーザーの間で、リンク関係を確立する必要があります。

Symantec Web Security Service (WSS) で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Symantec Web Security Service (WSS) の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Symantec Web Security Service（WSS）テストユーザーの作成 - Symantec** Web Security Service（WSS）で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Symantec Web Security Service (WSS)**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ダイアログ ボックスで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://saml.threatpulse.net:8443/saml/saml_realm`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://saml.threatpulse.net:8443/saml/saml_realm/bcsamlpost`

    注

    [識別子](https://www.symantec.com/contact-us)と**応答 URL** の値が何らかの理由で機能しない場合は**、Symantec Web Security Service (WSS) クライアント サポート チーム**にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Symantec Web Security Service (WSS) の SSO の構成

Symantec Web Security Service (WSS) 側にシングル サインオンを構成するには、WSS のオンライン ドキュメントを参照してください。 ダウンロードした**フェデレーション メタデータ XML** は、WSS ポータルにインポートする必要があります。 WSS ポータルの構成でサポートが必要な場合には、[Symantec Web Security Service (WSS) サポート チーム](https://www.symantec.com/contact-us)に問い合わせてください。

#### Symantec Web Security Service (WSS) のテスト ユーザーの作成

このセクションでは、Symantec Web Security Service (WSS) で Britta Simon というユーザーを作成します。 対応するエンド ユーザー名を WSS ポータルで手動で作成するか、または、Microsoft Entra ID にプロビジョニングされたユーザーまたはグループが数分 (最大 15 分) 後に WSS ポータルに同期されるのを待ちます。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。 Web サイトの参照に使用されるエンド ユーザー コンピューターのパブリック IP アドレスも、Symantec Web Security Service (WSS) ポータルでプロビジョニングする必要があります。

注

コンピューターのパブリック IPaddress を取得するには、 [ここを](https://www.bing.com/search?q=my+ip+address&amp;qs=AS&amp;pq=my+ip+a&amp;sc=8-7&amp;cvid=29A720C95C78488CA3F9A6BA0B3F98C5&amp;FORM=QBLH&amp;sp=1) 選択してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Symantec Web Security Service (WSS) に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Symantec Web Security Service (WSS) タイルを選択すると、SSO を設定した Symantec Web Security Service (WSS) に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/symantec-web-security-service"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Symantec Web Security Service (WSS) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/symantec-web-security-service
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Symantec Web Security Service (WSS) に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事の目的は、Symantec Web Security Service (WSS) と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを Symantec Web Security Service (WSS) に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

手記

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

このコネクタは現在パブリック プレビュー段階です。 プレビューの詳細については、「[オンライン サービス](https://www.microsoft.com/licensing/terms/product/ForOnlineServices/all)のユニバーサル ライセンス条項」を参照してください。

### サポートされている機能

- Symantec Web Security Service (WSS) でユーザーを作成します。
- アクセスが不要になったら、Symantec Web Security Service (WSS) のユーザーを削除します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.

- Symantec Web Security Service (WSS) テナント [の](https://www.websecurity.digicert.com/buy-renew?inid=brmenu_nav_brhome)。
- 管理者アクセス許可を持つ Symantec Web Security Service (WSS) のユーザー アカウント。

### ユーザーを Symantec Web Security Service (WSS) に割り当てる

Microsoft Entra ID では、*割り当て* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID でアプリケーションに割り当てられているユーザーまたはグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Symantec Web Security Service (WSS) へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定する必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを Symantec Web Security Service (WSS) に割り当てることができます。

- [エンタープライズ アプリ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal) にユーザーまたはグループを割り当てる

### ユーザーを Symantec Web Security Service (WSS) に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Symantec Web Security Service (WSS) に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後で追加のユーザーやグループを割り当てることができます。
- ユーザーを Symantec Web Security Service (WSS) に割り当てるときは、割り当てダイアログで有効なアプリケーション固有のロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニング用に Symantec Web Security Service (WSS) をセットアップする

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Symantec Web Security Service (WSS) を構成する前に、Symantec Web Security Service (WSS) で SCIM プロビジョニングを有効にする必要があります。

1. [Symantec Web Security Service 管理コンソール](https://portal.threatpulse.com/login.jsp)にサインインします。 **ソリューション**&gt;**サービス**に移動します。

    [Image: Symantec Web Security Service (WSS)]
2. **アカウントメンテナンス**&gt;**統合**&gt;**新しい統合**に移動します。

    [Image: Symantec Web Security Service (WSS)]
3. [**サード パーティユーザー & グループ同期**を選択します。

    [Image: サードパーティ ユーザー & グループ同期オプションのスクリーンショット。]
4. **SCIM URL** とトークン をコピーします。 これらの値は、Symantec Web Security Service (WSS) アプリケーションの [プロビジョニング] タブの **[テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: [新しい統合] ダイアログ ボックスのスクリーンショット。[S C I M U R L] ボックスと [トークン] テキスト ボックスが強調表示されています。]

### ギャラリーから Symantec Web Security Service (WSS) を追加する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Symantec Web Security Service (WSS) を構成するには、Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に Symantec Web Security Service (WSS) を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Symantec Web Security Service (WSS) を追加するには、次の手順に従います**

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. ギャラリー **から追加する** セクションで、「Symantec Web Security Service 入力し、検索ボックスで Symantec Web Security Service  選択します。
4. 結果パネル **Symantec Web Security Service** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。 結果一覧 [Image: Symantec Web Security Service (WSS)]

### Symantec Web Security Service (WSS) への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID のユーザーまたはグループの割り当てに基づいて、Symantec Web Security Service (WSS) でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Symantec Web Security Service (WSS) のシングル サインオンに関する記事で説明されている手順に従って、 [Symantec Web Security Service (WSS) の SAML ベースのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/symantec-tutorial)を有効にすることもできます。 シングル サインオンは、自動ユーザー プロビジョニングとは別に構成できますが、これら 2 つの機能は相互に補完します。

#### Microsoft Entra ID で Symantec Web Security Service (WSS) の自動ユーザー プロビジョニングを構成するには:

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズアプリケーションブレードの]
3. アプリケーションの一覧で **Symantec Web Security Service**を選択します。

    [Image: アプリケーションの一覧の Symantec Web Security Service (WSS) リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニング オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [**プロビジョニング モード**] を [**自動**] に設定します。

    [Image: [自動] オプションが強調表示されている [プロビジョニング モード] ドロップダウン リストのスクリーンショット。]
6. [管理者資格情報] セクションで、前に取得した **SCIM URL** 値と **トークン** 値をそれぞれ **テナント URL** と **シークレット トークン** に入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Symantec Web Security Service に接続できることを確認します。 接続に失敗した場合は、Symantec Web Security Service (WSS) アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: テナント URL + トークン]
7. [**通知メール**] フィールドに、プロビジョニング エラー通知を受け取るユーザーまたはグループのメール アドレスを入力し、[**エラーが発生したときに電子メール通知を送信する] チェック ボックスをオン**。

    [Image: 通知メール]
8. **保存** を選択します。
9. [**マッピング**] セクションで、[**Microsoft Entra ユーザーを Symantec Web Security Service (WSS)**に同期する] を選択します。

    [Image: [Mappings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/マッピング) セクションのスクリーンショット。[Synchronize Microsoft Entra users to Symantec Web Security Service W S S S](Microsoft Entra ユーザーを Symantec Web Security Service W S S S に同期する) オプションが強調表示されています。]
10. **属性マッピング** セクションで、Microsoft Entra ID から Symantec Web Security Service (WSS) に同期されるユーザー属性を確認します。 **照合** プロパティとして選択されている属性は、更新操作のために Symantec Web Security Service (WSS) のユーザー アカウントとの照合に使用されます。 **[** 保存] ボタンを選択して、変更をコミットします。

    [Image: 16 個の一致するプロパティを示す [属性マッピング] セクションのスクリーンショット。]
11. [**マッピング**] セクションで、[Microsoft Entra グループ **Symantec Web Security Service**に同期する] を選択します。

    [Image: [Mappings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/マッピング) セクションのスクリーンショット。[Synchronize Microsoft Entra groups to Symantec Web Security Service W S S S](Microsoft Entra グループを Symantec Web Security Service W S S S に同期する) オプションが強調表示されています。]
12. **属性マッピング** セクションで、Microsoft Entra ID から Symantec Web Security Service (WSS) に同期されるグループ属性を確認します。 **照合** プロパティとして選択されている属性は、更新操作で Symantec Web Security Service (WSS) のグループとの照合に使用されます。 **[** 保存] ボタンを選択して、変更をコミットします。

    [Image: 3 つの一致するプロパティを示す [属性マッピング] セクションのスクリーンショット。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. Symantec Web Security Service に対して Microsoft Entra プロビジョニング サービスを有効にするには、[**設定]** セクションで **[プロビジョニング状態]** を [**オン]** に変更します。

    [Image: プロビジョニングの状態が] で切り替えられます
15. Symantec Web Security Service (WSS) にプロビジョニングするユーザーやグループを定義するには、[**設定]** セクションの [スコープ] **で** 必要な値を選択します。

    [Image: プロビジョニング スコープ]
16. プロビジョニングの準備ができたら、 **[保存]** を選択します。

    [Image: プロビジョニング構成の保存]

この操作により、[**設定]** セクションの **スコープ** で定義されているすべてのユーザーまたはグループの初期同期が開始されます。 初期同期は、後続の同期よりも実行に時間がかかります。 ユーザーやグループのプロビジョニングにかかる時間の詳細については、「[ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user#how-long-will-it-take-to-provision-users)プロビジョニングにかかる時間」を参照してください。

**の [現状**] セクションを使用して進行状況を監視し、プロビジョニング アクティビティ レポートへのリンクをたどることで、Symantec Web Security Service (WSS) における Microsoft Entra プロビジョニング サービスによって実行されたすべてのアクションを確認できます。 詳細については、「[ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)の状態を確認する」を参照してください。 Microsoft Entra プロビジョニング ログを読み取る方法については、「[自動ユーザー アカウント プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)に関するレポート」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/symantec-ztna-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Symantec ZTNA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/symantec-ztna-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-15
- Summary: Microsoft Entra ID と Symantec ZTNA の間でシングル サインオンを構成する方法について説明します。

この記事では、Symantec ZTNA と Microsoft Entra ID を統合する方法について説明します。 Symantec ZTNA と Microsoft Entra ID を統合すると、次のことができます。

- Symantec ZTNA にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Symantec ZTNA に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Symantec ZTNA でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Symantec ZTNA では、**SP による SSO** と **IDP による SSO** の両方がサポートされます。

### ギャラリーから Symantec ZTNA を追加する

Microsoft Entra ID への Symantec ZTNA の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Symantec ZTNA を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Symantec ZTNA**」と入力します。
4. 結果パネルから **Symantec ZTNA** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Symantec ZTNA の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Symantec ZTNA に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Symantec ZTNA の関連ユーザーとの間にリンク関係を確立する必要があります。

Symantec ZTNA に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Symantec ZTNA SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Symantec ZTNA 用テストユーザーを作成します - Symantec ZTNA** で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra ID 表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Symantec ZTNA**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ORG.TENANT_DOMAIN_NAME>/luminate/saml/<ORG.IDP_ID>/entityid`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ORG.TENANT_DOMAIN_NAME>/luminate/saml/<ORG.IDP_ID>/acs`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR_COMPANY>.luminatesec.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Symantec ZTNA サポート チーム](mailto:technical.support@broadcom.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Symantec ZTNA のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Symantec ZTNA SSO の構成

**Symantec ZTNA** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Symantec ZTNA サポート チーム](mailto:technical.support@broadcom.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Symantec ZTNA テスト ユーザーの作成

このセクションでは、Symantec ZTNA で B.Simon というユーザーを作成します。 [Symantec ZTNA サポート チーム](mailto:technical.support@broadcom.com)と協力して、Symantec ZTNA プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Symantec ZTNA サインオン URL にリダイレクトします。
- Symantec ZTNA のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Symantec ZTNA に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Symantec ZTNA] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Symantec ZTNA に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/synchronet-click-tutorial"} -->
## Microsoft Entra ID で SynchroNet CLICK for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/synchronet-click-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SynchroNet CLICK の間でシングル サインオンを構成する方法について学習します。

この記事では、SynchroNet CLICK と Microsoft Entra ID を統合する方法について説明します。 SynchroNet CLICK を Microsoft Entra ID と統合すると、次のことができます。

- SynchroNet CLICK にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SynchroNet CLICK に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SynchroNet CLICK でのシングル サインオン (SSO) が有効なサブスクリプション。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SynchroNet CLICK では、**SP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの SynchroNet CLICK の追加

Microsoft Entra ID への SynchroNet CLICK の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに SynchroNet CLICK を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SynchroNet CLICK**」と入力します。
4. 結果のパネルから **[SynchroNet CLICK]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SynchroNet CLICK 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、SynchroNet CLICK で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SynchroNet CLICK の関連ユーザーとの間にリンク関係を確立する必要があります。

SynchroNet CLICK で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SynchroNet CLICK の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SynchroNet CLICKのテストユーザーを作成し、Microsoft Entra内でのユーザー表現にリンクされるSynchroNet CLICK内のB.Simonの対応ユーザーを持つようにする。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SynchroNet CLICK**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    **[サインオン URL]** ボックスに、URL として「`https://select.synchronet.com`」と入力します。
6. SynchroNet CLICK アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは既定の属性の一覧を示していますが、**emailaddress** は **user.mail** にマップされています。 SynchroNet CLICK アプリケーションでは **、emailaddress** が **user.userprincipalname** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SynchroNet CLICK の SSO の構成

**SynchroNet CLICK** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [SynchroNet CLICK サポート チーム](mailto:tickets@synchronet.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### SynchroNet CLICK のテスト ユーザーの作成

このセクションでは、SynchroNet CLICK で Britta Simon というユーザーを作成します。 [SynchroNet CLICK サポート チーム](mailto:tickets@synchronet.com)と連携し、SynchroNet CLICK プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる SynchroNet CLICK のサインオン URL にリダイレクトされます。
- SynchroNet CLICK のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [SynchroNet CLICK] タイルを選択すると、このオプションは SynchroNet CLICK のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/syncplicity-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Syncplicity を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/syncplicity-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Syncplicity の間でシングル サインオンを構成する方法について説明します。

この記事では、Syncplicity と Microsoft Entra ID を統合する方法について説明します。 Syncplicity と Microsoft Entra ID を統合すると、次のことができます。

- Syncplicity にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Syncplicity に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Syncplicity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Syncplicity では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Syncplicity を追加

Microsoft Entra ID への Syncplicity の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Syncplicity を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[Microsoft Entra ギャラリーの参照]** セクションで、検索ボックスに「**Syncplicity**」と入力します。
4. 結果パネルから **[Syncplicity** ] を選択し、[ **作成** ] を選択してアプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Syncplicity の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Syncplicity に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Syncplicity の関連ユーザーとの間にリンク関係を確立する必要があります。

Syncplicity で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Syncplicity SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Syncplicityのテストユーザーを作成する** - B.Simonに対応するユーザーをSyncplicityに作成し、それをMicrosoft EntraでのB.Simonにリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。
4. **SSO** の更新 - Microsoft Entra ID の SSO 設定を変更した場合に Syncplicity で必要な変更を行います。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Syncplicity**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    ある。 [**識別子 (エンティティ ID)** テキスト ボックスに、次のパターンを使用して URL を入力します:`https://<COMPANY_NAME>.syncplicity.com/sp`

    b。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<COMPANY_NAME>.syncplicity.com`

    c. **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、`https://<COMPANY_NAME>.syncplicity.com/Auth/AssertionConsumerService.aspx` というパターンを使用して URL を入力します。

    手記

    これらの値は実際の値ではありません。 実際の応答 URL、サインオン URL、識別子でこれらの値を更新します。 これらの値を取得するには、[Syncplicity クライアント サポート チーム](https://www.syncplicity.com/contact-us) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[編集] を選択 **します**。 次に、ダイアログで、アクティブな証明書の横にある省略記号ボタンを選択し、 **PEM 証明書のダウンロード**を選択します。

    [Image: 証明書のダウンロード リンク]

    手記

    Syncplicity は CER 形式の証明書を受け入れないので、PEM 証明書が必要です。
7. **Syncplicity** の設定セクションで、要件に応じて適切なURLをコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Syncplicity SSO の構成

1. **Syncplicity** テナントにサインインします。
2. 上部のメニューで 、[ **管理者**] を選択し、[ **設定]** を選択し、[ **カスタム ドメイン] を選択してシングル サインオンします**。

    [Image: Syncplicity] をする
3. [**シングル Sign-On (SSO)**] ダイアログ ページで、次の手順を実行します。

    [Image: シングル サインオン (SSO)]

    ある。 **カスタム ドメイン** テキストボックスに、ドメインの名前を入力します。

    b。 **[シングル サインオンの状態]** として **[有効]** を選択します。

    c. [**エンティティ ID**] テキストボックスに、**基本的な SAML 構成**で使用した **識別子 (エンティティ ID)** の値を貼り付けます。

    d. [**サインインページのURL** ボックスに、前にコピーした**サインオンURL** を貼り付けます。]

    え [**ログアウトページのURL** テキストボックスに、前にコピーした **ログアウトURL** を貼り付けます。

    f. **ID プロバイダー証明書**で、[ファイルの**選択**] を選択し、ダウンロードした証明書をアップロードします。

    ジー [ **変更の保存] を選択します**。

#### Syncplicity テスト ユーザーの作成

Microsoft Entra ユーザーがサインインできるようにするには、ユーザーを Syncplicity アプリケーションにプロビジョニングする必要があります。 このセクションでは、Syncplicity で Microsoft Entra ユーザー アカウントを作成する方法について説明します。

**Syncplicity にユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. あなたの **Syncplicity** テナントにログインします (例: `https://company.Syncplicity.com`)。
2. [ **管理者]** を選択し **、[ユーザー アカウント]** を選択し、[ **ユーザーの追加]** を選択します。

    [Image: ユーザーの管理]
3. プロビジョニングする Microsoft Entra アカウントの**メール アドレス**を入力し、[**ロール**として**ユーザー**] を選択し、[**次へ**] を選択します。

    [Image: アカウント情報]

    手記

    Microsoft Entra アカウント所有者は、アカウントを確認してアクティブ化するためのリンクを含む電子メールを受け取ります。
4. 新しいユーザーがメンバーになる会社のグループを選択し、[ **次へ**] を選択します。

    [Image: グループ メンバーシップ]

    手記

    グループが一覧に表示されていない場合は、[ **次へ**] を選択します。
5. ユーザーのコンピューターで Syncplicity のコントロールの下に配置するフォルダーを選択し、[ **次へ**] を選択します。

    [Image: Syncplicity フォルダー]

手記

Syncplicity によって提供される他の Syncplicity ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Syncplicity のサインオン URL にリダイレクトされます。
- Syncplicity のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Syncplicity] タイルを選択すると、このオプションは Syncplicity のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。

#### SSO の更新

SSO を変更する必要がある場合は常に、使用されている SAML 署名証明書  を確認する必要があります。 証明書が変更された場合は、「Syncplicity SSO**の構成」の説明に従って、新しい証明書**Syncplicity にアップロードしてください。

Syncplicity Mobile アプリを使用している場合は、Syncplicity カスタマー サポート (support@syncplicity.com) にお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/syndio-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Syndio を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/syndio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Syndio の間のシングル サインオンを構成する方法について説明します。

この記事では、Syndio と Microsoft Entra ID を統合する方法について説明します。 Syndio と Microsoft Entra ID を統合すると、次のことができます。

- Syndio にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Syndio に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Syndio でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Syndio では、**SP** Initiated SSO がサポートされます
- Syndio では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Syndio の追加

Microsoft Entra ID への Syndio の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Syndio を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Syndio**」と入力します。
4. 結果のパネルから **[Syndio]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Syndio 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Syndio に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Syndio の関連ユーザーとの間にリンク関係を確立する必要があります。

Syndio に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Syndio の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Syndio テストユーザーを作成** - Syndio で B.Simon の対応ユーザーを作成し、それを Microsoft Entra のユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Syndio**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://payeq<SyndioEnv>.synd.io`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:auth0:syndio-payeq<SyndioEnv>:<OrganizationID>`

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth<SyndioEnv>.synd.io/login/callback?connection=<OrganizationID>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、応答 URL でこれらの値を更新します。 この値を取得するには、[Syndio クライアント サポート チーム](mailto:support@synd.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Syndio のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Syndio の SSO の構成

**Syndio** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Syndio サポート チーム](mailto:support@synd.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Syndio のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Syndio に作成します。 Syndio では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Syndio にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Syndio のサインオン URL にリダイレクトされます。
- Syndio のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Syndio] タイルを選択すると、このオプションは Syndio のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/synergi-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Synergi を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/synergi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Synergi の間にシングル サインオンを構成する方法について説明します。

この記事では、Synergi と Microsoft Entra ID を統合する方法について説明します。 Synergi と Microsoft Entra ID を統合すると、次のことができます。

- Synergi にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Synergi に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Synergi でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Synergi では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Synergi の追加

Microsoft Entra ID への Synergi の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Synergi を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Synergi**」と入力します。
4. 結果のパネルから **[Synergi]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Synergi 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Synergi との Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Synergi の関連ユーザーとの間にリンク関係を確立する必要があります。

Synergi との Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Synergi SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Synergi のテストユーザーを作成** - Microsoft Entra のユーザーとしての B.Simon にリンクされた Synergi での B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Synergi**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.irmsecurity.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.irmsecurity.com/sso/<organization id>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Synergi クライアント サポート チーム](https://www.irmsecurity.com/contact/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Synergi のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Synergi SSO の構成

**Synergi** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Synergi サポート チーム](https://www.irmsecurity.com/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Synergi のテスト ユーザーの作成

このセクションでは、Synergi で Britta Simon というユーザーを作成します。 [Synergi サポート チーム](https://www.irmsecurity.com/contact/)と連携し、Synergi プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Synergi に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Synergi] タイルを選択すると、SSO を設定した Synergi に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/synerise-ai-growth-ecosystem-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Synerise AI Growth オペレーティング システムを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/synerise-ai-growth-ecosystem-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Synerise AI Growth Operating System の間にシングル サインオンを構成する方法について説明します。

この記事では、Synerise と Microsoft Entra ID を統合する方法について説明します。 Synerise を Microsoft Entra ID と統合すると、次のことができます。

- Synerise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Synerise に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Synerise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Synerise では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Synerise では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Synerise AI Growth Operating System の追加

Microsoft Entra ID への Synerise の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Synerise を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーから追加**する] セクションで、検索ボックス**に「Synerise AI Growth Operating System**」と入力します。
4. 結果パネルから **Synerise AI Growth オペレーティング システム** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Synerise AI Growth Operating System の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Synerise に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと、Synerise での関連ユーザーとの間にリンク関係を確立する必要があります。

Synerise に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Synerise AI Growth オペレーティング システムの SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Synerise AI Growth Operating System のテスト ユーザーの作成** - Synerise で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリケーション]**&gt;**[Synerise AI Growth Operating System]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.synerise.com/api-portal/uauth/saml/auth/<PROFILE_HASH>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.synerise.com/api-portal/uauth/saml/auth/<PROFILE_HASH>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには [、Synerise サポート チーム](mailto:support@synerise.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Synerise のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Synerise AI Growth Operating System の SSO の構成

1. 管理者として Synerise にログインします。
2. アクセス **制御 &gt; 設定**に移動します。

    [Image: Synerise の設定]
3. [**アクセス制御**] ページで、[**シングル サインオン**] タブの [**表示**] ボタンを選択します。

    [Image: Synerise のアクセスの制御]
4. 次のページで以下の手順を実行します。

    [Image: Synerise の構成]

    a. [ **識別子プロバイダー エンティティ ID** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    b。 **SSO エンドポイント (https) ボックスに**、前にコピーした**ログイン URL** の値を貼り付けます。

    c. [ **ID プロバイダー アプリケーション ID** ] ボックスに、 **アプリケーション ID** の値を貼り付けます。

    d. **サービス プロバイダーのリダイレクト URI 値を**コピーし、[基本的な SAML 構成] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    e. **要求バインド**で **[HTTP REDIRECT**] を選択します。

    f. **要求署名**をオンにします。

    g. ダウンロードした **証明書 (Base64)** ファイルを **ID プロバイダー署名証明書**にアップロードします。

    一. [ **適用]** を選択します。

#### Synerise AI Growth Operating System のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Synerise に作成します。 Synerise では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Synerise にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Synerise のサインオン URL にリダイレクトされます。
- Synerise のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Synerise に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Synerise] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Synerise に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/syniverse-customer-portal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Syniverse Customer Portal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/syniverse-customer-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Syniverse Customer Portal の間にシングル サインオンを構成する方法について説明します。

この記事では、Syniverse Customer Portal と Microsoft Entra ID を統合する方法について説明します。 Syniverse Customer Portal を Microsoft Entra ID と統合すると、次のことができるようになります。

- Syniverse Customer Portal にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Syniverse Customer Portal に自動的にサインインできるようにすることができます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Syniverse Customer Portal でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Syniverse Customer Portal では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- Syniverse Customer Portal では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### Syniverse Customer Portal をギャラリーから追加する

Microsoft Entra ID への Syniverse Customer Portal の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Syniverse Customer Portal を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Syniverse Customer Portal**」と入力します。
4. 結果のパネルから **[Syniverse Customer Portal]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Syniverse Customer Portal 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Syniverse Customer Portal で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Syniverse Customer Portal の関連ユーザーとの間にリンク関係を確立する必要があります。

Syniverse Customer Portal で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Syniverse Customer Portal SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Syniverse Customer Portal のテストユーザーを作成し、B.Simon に対応するユーザーを Microsoft Entra 上の表現にリンクさせる。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Syniverse Customer Portal**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://symphony.dalab.syniverse.com/ups` |
    | `https://symphony.syniverse.com/ups` |
    | `https://symphony-test.dalab.syniverse.com/ups` |
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Syniverse Customer Portal SSO を構成する

**Syniverse Customer Portal **側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** とアプリケーション構成からコピーした適切な URL を [Syniverse Customer Portal サポート チーム](mailto:portalDevOps@syniverse.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Syniverse Customer Portal のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Syniverse Customer Portal に作成します。 Syniverse Customer Portal では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Syniverse Customer Portal にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Syniverse Customer Portal のサインオン URL にリダイレクトされます。
- Syniverse Customer Portal のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Syniverse Customer Portal に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Syniverse Customer Portal] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Syniverse カスタマー ポータルに自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/syxsense-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Syxsense を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/syxsense-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Syxsense の間にシングル サインオンを構成する方法について説明します。

この記事では、Syxsense と Microsoft Entra ID を統合する方法について説明します。 Syxsense を Microsoft Entra ID を統合すると、次のことができます。

- Syxsense にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Syxsense に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Syxsense でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Syxsense では、**SP と IDP** Initiated SSO がサポートされます。

### ギャラリーからの Syxsense の追加

Microsoft Entra ID への Syxsense の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Syxsense を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Syxsense**」と入力します。
4. 結果のパネルから **Syxsense** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Syxsense 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Syxsense に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Syxsense の関連ユーザーとの間にリンク関係を確立する必要があります。

Syxsense に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Syxsense の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Syxsense のテスト ユーザーの作成 - Syxsense** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Syxsense**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cloudmanagementsuite.com/Saml2`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.cloudmanagementsuite.com/Saml2/Acs`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Syxsense クライアント サポート チーム](mailto:DevTeam@syxsense.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Syxsense アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Syxsense アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザー.メール |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Syxsense の SSO の構成

1. 別のブラウザー ウィンドウで、Syxsense Web サイトに管理者としてサインインします。
2. **[設定] アイコン**を選択します。

    [Image: スクリーンショットは、設定アイコンを示しています。]
3. **[外部認証**] を選択し、[**アプリのフェデレーション メタデータ URL]** の値を **SAML2.0 [メタデータ**] ボックスに入力し、[**保存]** を選択します。

    [Image: [External Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/外部認証) ページを示すスクリーンショット。ここで、アプリのフェデレーション メタデータ URL 値を入力できます。]

#### Syxsense のテスト ユーザーの作成

1. 別のブラウザー ウィンドウで、Syxsense Web サイトに管理者としてサインインします。
2. 左側のナビゲーション パネルから [ **ユーザー アカウント]** を選択します。

    [Image: ナビゲーション パネルから [User Accounts](ユーザー アカウント) が選択されていることを示すスクリーンショット。]
3. [**] を選択し、[**] を追加します。

    [Image: [User Accounts](ユーザー アカウント) ページを示すスクリーンショット。ここで [追加] を選択できます。]
4. 組織の要件に従ってユーザーの詳細を指定し、[ **保存]** を選択します。

    [Image: 情報を入力できるページを示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Syxsense のサインオン URL にリダイレクトされます。
- Syxsense のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Syxsense に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Syxsense] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Syxsense に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tableau-online-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Tableau Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tableau-online-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-14
- Summary: Microsoft Entra ID から Tableau Cloud に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Tableau Cloud と Microsoft Entra ID の両方で、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して Tableau [Cloud](https://www.tableau.com/) にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- Tableau Cloud のユーザーを作成する。
- アクセスが不要になったら、Tableau Cloud のユーザーを削除します。
- Microsoft Entra ID と Tableau Cloud の間でユーザー属性の同期を維持します。
- Tableau Cloud でグループとグループ メンバーシップをプロビジョニングする。
- Tableau Cloud に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tableauonline-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。
- 基本認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- [Tableau Cloud テナント](https://www.tableau.com/)。
- 管理者アクセス許可がある Tableau Cloud のユーザー アカウント

注

Microsoft Entra プロビジョニング統合は [、Tableau Cloud REST API](https://onlinehelp.tableau.com/current/api/rest_api/en-us/help.htm) に依存しています。 この API は Tableau Cloud 開発者が利用できます。

### 手順1: プロビジョニング デプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Tableau Cloud の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Tableau Cloud を構成する

Microsoft Entra ID で SCIM サポートを有効にするには、次の手順に従います。

1. SCIM 機能を使用するには、SAML シングル サインオンをサポートするようにサイトを構成する必要があります。 まだこれを行っていない場合は、「 [Microsoft Entra ID を使用して SAML を構成する」](https://help.tableau.com/current/online/en-us/saml_config_azure_ad.htm)の次のセクションを完了します。

    - 手順 1: [Tableau Cloud の SAML 設定を開きます](https://help.tableau.com/current/online/en-us/saml_config_azure_ad.htm#open-the-tableau-online-saml-settings)。
    - 手順 2: [Microsoft Entra アプリケーションに Tableau Cloud を追加する](https://help.tableau.com/current/online/en-us/saml_config_azure_ad.htm#add-tableau-online-to-your-azure-ad-applications)。

    注

    SAML シングル サインオンを設定していない場合、ユーザーの認証方法を SAML から Tableau Cloud または Tableau MFA に手動で変更しない限り、ユーザーはプロビジョニング後に Tableau Cloud にサインインできません。
2. Tableau Cloud で **、[設定 &gt; 認証]** ページに移動し、[ **自動プロビジョニングとグループ同期 (SCIM)]** で [ **SCIM を有効にする** ] チェック ボックスをオンにします。 これにより、[ **ベース URL** ] ボックスと [ **シークレット** ] ボックスに、IdP の SCIM 構成で使用する値が設定されます。

    注

    シークレット トークンは、生成された直後に表示されます。 Microsoft Entra ID に適用する前に紛失した場合は、[ **新しいシークレットの生成**] を選択できます。 さらに、シークレット トークンは、SCIM サポートを有効にするサイト管理者の Tableau Cloud ユーザー アカウントに関連付けられています。 そのユーザーのサイト ロールが変更されたり、サイトから削除されたりすると、シークレット トークンは無効になり、別のサイト管理者が新しいシークレット トークンを生成して Microsoft Entra ID に適用する必要があります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Tableau Cloud を追加する

Microsoft Entra アプリケーション ギャラリーから Tableau Cloud を追加して、Tableau Cloud へのプロビジョニングの管理を開始します。 SSO のために Tableau Cloud を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Tableau Cloud への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーとグループの割り当てに基づいて、Tableau Cloud でユーザーとグループが作成、更新、無効化されるように、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Tableau Cloud に対する SAML ベースのシングル サインオンを有効にする必要があります。 [Tableau Cloud のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tableauonline-tutorial)に関する記事の手順に従います。 SAML が有効になっていない場合、プロビジョニングされたユーザーはサインインできません。

#### Microsoft Entra ID で Tableau Cloud の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**にアクセスする

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Tableau Cloud** を選択します。

    [Image: アプリケーションの一覧の Tableau Cloud リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Tableau Cloud テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Tableau Cloud に接続できることを確認します。 接続に失敗した場合は、Tableau Cloud アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: Tableau Cloud のテスト接続ダイアログのスクリーンショット。]

    注

    認証方法には、 **ベアラー認証** と **基本認証**の 2 つのオプションがあります。 ベアラー認証を選択していることを確認します。 SCIM 2.0 エンドポイントでは基本認証が機能しません。
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Tableau Cloud に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、Tableau Cloud のユーザー アカウントを更新操作に照合するために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Tableau Cloud API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Tableau Cloud で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | roles | 糸 |  |  |
12. **[グループ] を選択します**。
13. [属性マッピング] セクションで、Microsoft Entra ID から Tableau Cloud に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Tableau Cloud のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Tableau Cloud で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ |  |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

#### Tableau Cloud アプリケーションを更新して Tableau Cloud SCIM 2.0 エンドポイントを使用できるようにする

2022 年 6 月、Tableau は SCIM 2.0 コネクタをリリースしました。 以下の手順を完了すると、Tableau API エンドポイントを使用するように構成されたアプリケーションが、SCIM 2.0 エンドポイントを使用するように更新されます。 これらの手順により、Tableau Cloud アプリケーションに対してこれまでに行われたカスタマイズが削除されます。これには次が含まれます。

- 認証の詳細 (SSO に使用される資格情報ではなく、プロビジョニングに使用される資格情報)
- スコープ フィルター
- カスタム属性マッピング

注

以下の手順を完了する前に、上記の設定に加えた変更を必ずメモしておいてください。 これを行わないと、カスタマイズされた設定が失われます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Tableau Cloud** を参照してください。
3. 新しいカスタム アプリの [プロパティ] セクションで、 **オブジェクト ID をコピーします**。

    [Image: Tableau Cloud アプリのスクリーンショット。]
4. 新しい Web ブラウザー ウィンドウで `https://developer.microsoft.com/graph/graph-explorer` に移動し、アプリの追加先の Microsoft Entra テナントの管理者としてサインインします。

    [Image: Microsoft Graph エクスプローラーのサインイン ページのスクリーンショット。]
5. 使用されているアカウントに適切なアクセス許可が付与されていることを確認します。 この変更を行うには、 **アクセス許可 Directory.ReadWrite.All** が必要です。

    [Image: Microsoft Graph 設定オプションのスクリーンショット。]

    [Image: Microsoft Graph のアクセス許可のスクリーンショット。]
6. 前にアプリから選択したオブジェクト ID を使用して、次のコマンドを実行します。

    `GET https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs/`
7. 上記の GET 要求の応答本文から "id" 値を取得し、次のコマンドを実行します。"[job-id]" は GET 要求からの id 値で置き換える必要があります。 値は、"Tableau.xxxxxxxxxxxxxxx.xxxxxxxxxxxxxxx" という形式にする必要があります。

    `DELETE https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs/[job-id]`
8. Graph エクスプローラーで、次のコマンドを実行します。 "[object-id]" を、手順 3 でコピーしたサービス プリンシパル ID (オブジェクト ID) に置き換えます。

    `POST https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs { "templateId": "TableauOnlineSCIM" }`

    [Image: Microsoft Graph 要求のスクリーンショット。]
9. 最初の Web ブラウザー ウィンドウに戻り、アプリケーションの [プロビジョニング] タブを選択します。 構成がリセットされます。 ジョブ ID が **TableauOnlineSCIM** で開始されていることを確認することで、アップグレードが行われたか確認できます。
10. [管理者資格情報] セクションで、認証方法として [ベアラー認証] を選び、プロビジョニングする Tableau インスタンスのテナント URL とシークレット トークンを入力します。 [Image: Tableau Cloud の管理者資格情報のスクリーンショット。]
11. アプリケーションに対して加えた以前の変更 (認証の詳細、スコープ フィルター、カスタム属性マッピング) を復元し、プロビジョニングを再び有効にします。

注

前の設定を復旧できない場合、ワークプレースで属性 (name.formatted など) が突然更新されることがあります。 プロビジョニングを有効にする前に必ず構成を確認してください。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### 更新履歴

- 09/30/2020 - ユーザー用の "authSetting" 属性のサポートを追加。
- 06/24/2022 - アプリを SCIM 2.0 に準拠するよう更新。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tableauonline-tutorial"} -->
## Microsoft Entra ID で Tableau Cloud for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tableauonline-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Tableau Cloud 間にシングル サインオンを構成する方法について説明します。

この記事では、Tableau Cloud と Microsoft Entra ID を統合する方法について説明します。 Tableau Cloud を Microsoft Entra ID を統合すると、次のことができます:

- Tableau Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Tableau Cloud に自動的にサインインできるように設定できます。
- 1 つの場所でアカウントを管理します。

Tableau Cloud は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Tableau Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Tableau Cloud は、**SP** によって開始された SSO をサポートします。
- Tableau Cloud は、[**自動化されたユーザー プロビジョニングとプロビジョニング解除**](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tableau-online-provisioning-tutorial) (推奨) をサポートします。

### ギャラリーから Tableau Cloud を追加する

Microsoft Entra ID への Tableau Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Tableau Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Tableau Cloud**」と入力します。
4. 結果パネルから **[Tableau Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tableau Cloud 用に Microsoft Entra SSO を構成してテストする

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Tableau Cloud で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Tableau Cloud 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Microsoft Entra SSO を Tableau Cloud と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tableau Cloud SSO の構成**- アプリケーション側でシングルサインオン設定を構成します。
    1. **Tableau Cloud のテスト ユーザーを作成する** - Microsoft Entra における B.Simon の表現とリンクされた、Tableau Cloud 上の B.Simon に相当するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Tableau Cloud**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://sso.online.tableau.com/public/sp/metadata?alias=<entityid>`

    b。 **[応答 URL]** ボックスに、`https://sso.online.tableau.com/public/sp/<CUSTOM_URL>` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、URL として「`https://sso.online.tableau.com`」と入力します。

    注

    `<entityid>`値は、この記事の**「Tableau Cloud のセットアップ」**セクションから取得します。 エンティティ ID の値は、[**Tableau Cloud のセットアップ]** セクションの **Microsoft Entra 識別子**の値です。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Tableau Cloud のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tableau Cloud SSO の構成

1. 別の Web ブラウザー ウィンドウで、Tableau Cloud 企業サイトに管理者としてサインインします
2. **[設定]** 、 **[認証]** の順にクリックします。

    [Image: [設定] メニューから [認証] が選択された画面のスクリーンショット。]
3. SAML を有効にするには、 **[Authentication types](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の種類)** セクションで、 **[Enable an additional authentication method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加の認証方法を有効にする)** をオンにし、 **[SAML]** チェック ボックスをオンにします。

    [Image: [Authentication types](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の種類) セクションのスクリーンショット。ここで値を選択することができます。]
4. 下へスクロールして、**[Tableau Cloud にメタデータ ファイルをインポートする]** セクションを表示します。 [参照] を選択し、Microsoft Entra ID からダウンロードしたメタデータ ファイルをインポートします。 次に、[ **適用**] を選択します。

    [Image: メタデータ ファイルをインポートするためのセクションのスクリーンショット。]
5. **[Match assertions (アサーションの一致)]** セクションで、**メール アドレス**、**名**、**姓**に対応する ID プロバイダーのアサーション名を挿入します。 Microsoft Entra ID からこの情報を取得するには:

    a. Azure Portal で 「**Tableau Cloud**」 アプリケーション統合ページに移動します。

    b。 [ **ユーザー属性と要求** ] セクションで、編集アイコンを選択し、次の手順に従って SAML トークン属性を追加します(次の表を参照)。

    [Image: 編集アイコンを選択できる [User Attributes & Claims](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性とクレーム) セクションのスクリーンショット。]

    | 名前 | ソース属性 |
    | --- | --- |
    | DisplayName | user.displayname |

    c. 以下の手順で、属性 givenname、email、surname の名前空間の値をコピーします。

    [Image: Givenname、Surname、Emailaddress の各属性を示すスクリーンショット。]

    d. **user.givenname** 値を選択する

    e. **[名前空間]** ボックスと **[要求名]** の値をコピーします。

    [Image: [Manage user claims](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー要求の管理) セクションのスクリーンショット。ここに名前空間を入力できます。]

    f. email、surname の名前空間の値をコピーするには、上の手順を繰り返します。

    g. Tableau Cloud アプリケーションに切り替え、次のように **[ユーザー属性とクレーム]** セクションを設定します。

    - Email (電子メール): **mail** または **userprincipalname**
    - フル ネーム: **displayname**

    [Image: スクリーンショットには、値を入力できる [Match attributes] セクションが表示されています。]

#### Tableau Cloud テスト ユーザーの作成

このセクションでは、Tableau Cloud で Britta Simon というユーザーを作成します。

1. **Tableau Cloud** で、[**設定]** を選択し、[**認証**] セクションを選択します。 下へスクロールして、 **[Manage Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理)** セクションを表示します。 [ **ユーザーの追加]** を選択し、[ **電子メール アドレスの入力**] を選択します。

    [Image: [Manage users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理) セクションのスクリーンショット。ここで [Add users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) を選択できます。]
2. **[Add users for (SAML) authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/(SAML) 認証用にユーザーを追加する)** を選択します。 **[Enter email addresses](メール アドレスを入力)** ボックスに「britta.simon@contoso.com」と入力します

    [Image: [Add Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ページのスクリーンショット。ここでメール アドレスを入力することができます。]
3. **[ユーザーの追加]** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Tableau Cloud のサインオン URL にリダイレクトされます。
- Tableau Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Tableau Cloud] タイルを選択すると、このオプションは Tableau Cloud のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tableauserver-tutorial"} -->
## Microsoft Entra ID を使用して Tableau Server for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tableauserver-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tableau Server 間にシングル サインオンを構成する方法について説明します。

この記事では、Tableau Server と Microsoft Entra ID を統合する方法について説明します。 Tableau Server を Microsoft Entra ID を統合すると、次のことができます:

- Tableau Server にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Tableau Server に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Tableau Server でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Tableau Server では、**SP** によって開始される SSO がサポートされます

### ギャラリーから Tableau Server を追加する

Microsoft Entra ID への Tableau Server の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Tableau Server を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Tableau Server**」と入力します。
4. 結果パネルで **[Tableau Server]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tableau Server 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Tableau Server に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Tableau Server の関連ユーザーとの間にリンク関係を確立する必要があります。

Tableau Server で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tableau Server SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Tableau Server のテストユーザーを作成** - Tableau Server で Microsoft Entra 上の B.Simon の表現にリンクされた対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Tableau Server**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://azure.<domain name>.link`

    b。 **[識別子]** ボックスに、`https://azure.<domain name>.link` という形式で URL を入力します。

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://azure.<domain name>.link/wg/saml/SSO/index.html`

    注

    上記の値は実際の値ではありません。 この記事で後述する Tableau Server 構成ページの実際のサインオン URL、識別子、応答 URL で値を更新します。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Tableau Server のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tableau Server SSO の構成

1. アプリケーションに合わせて SSO を構成するには、管理者として Tableau Server テナントにサインインする必要があります。
2. [ **構成** ] タブで、[ **ユーザー ID とアクセス] を**選択し、[ **認証** 方法] タブを選択します。

    [Image: [ユーザー ID] と [アクセス] から選択された認証を示すスクリーンショット。]
3. **[CONFIGURATION](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成)** ページで、次の手順を実行します。

    [Image: [Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) ページを示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[Authentication Method](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証方法)** として SAML を選択します。

    b。 **[Enable SAML Authentication for the server](サーバーの SAML 認証を有効にする)** のチェック ボックスをオンにします。

    c. Tableau Server のリターン URL - Tableau Server ユーザーがアクセスしている URL ( `http://tableau_server`など)。 `http://localhost`の使用は推奨されません。 末尾にスラッシュ ( `http://tableau_server/`など) がある URL の使用はサポートされていません。 **[Tableau Server の戻り先 URL]** をコピーし、**[基本的な SAML 構成]** セクションの **[サインオン URL]** テキスト ボックスに貼り付けます。

    d. SAML entity ID: IdP に対して Tableau Server のインストールを一意に識別するエンティティ ID。 必要に応じて、ここで Tableau Server の URL をもう一度入力できますが、Tableau Server の URL である必要はありません。 **[SAML エンティティ ID]** をコピーし、**[基本的な SAML 構成]** セクションの **[識別子]** テキスト ボックスに貼り付けます。

    え [ **XML メタデータ ファイルのダウンロード] を** 選択し、テキスト エディター アプリケーションで開きます。 Http Post で Index 0 の [Assertion Consumer Service URL] を探し、URL をコピーします。 これを、**[基本的な SAML 構成]** セクションの **[応答 URL]** テキスト ボックスに貼り付けます。

    f. Azure Portal からダウンロードしたフェデレーション メタデータ ファイルを検索し、 **[SAML Idp metadata file](SAML Idp メタデータ ファイル)** でアップロードします。

    ジー ユーザー名、表示名、メール アドレスを IdP が保持するために使用する属性の名前を入力します。

    h. **保存** を選択します。

    注

    ユーザーは、.crt 拡張子の付いた PEM でエンコードされた x509 証明書ファイルに加え、証明書キー ファイルとして .key 拡張子の付いた RSA または DSA 秘密キー ファイルをアップロードする必要があります。 証明書ファイルと証明書キー ファイルの詳細については、[こちら](https://help.tableau.com/current/server/en-us/saml_requ.htm)のドキュメントを参照してください。 Tableau Server での SAML の構成についてサポートが必要な場合は、こちらの[サーバー全体の SAML の構成](https://help.tableau.com/current/server/en-us/config_saml.htm)に関する記事を参照してください。

    注

    SAML 証明書ファイルと SAML キー ファイルは個別に生成され、Tableau サーバー マネージャーにアップロードされます。 たとえば、linux シェルでは、openssl を使用して証明書とキーを生成します。`openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout private.key -out saml.crt`、ファイルと ファイルを、(この手順の開始時のスクリーンショットに示すように) または tableau ドキュメントに従ってコマンド ラインを使用してアップロードします。運用環境の場合は、SAML 証明書とキーをより安全に処理する方法を見つける必要があります。

#### Tableau Server のテスト ユーザーの作成

このセクションの目的は、Tableau Server で B.Simon というユーザーを作成することです。 Tableau Server 内のすべてのユーザーをプロビジョニングする必要があります。

また、ユーザーのユーザー名は、Microsoft Entra のカスタム属性 **username** で構成した値と一致する必要があります。 正しい対応付けがあれば、統合で Microsoft Entra シングル サインオンの構成が機能します。

注

ユーザーを手動で作成する必要がある場合は、組織の Tableau Server 管理者に問い合わせてください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Tableau Server のサインオン URL にリダイレクトされます。
- Tableau Server のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Tableau Server] タイルを選択すると、このオプションは Tableau Server のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tailscale-provisioning-tutorial"} -->
## Tailscale を構成して、Microsoft Entra ID を使用した自動ユーザー プロビジョニングに対応させる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tailscale-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-14
- Summary: Microsoft Entra ID から Tailscale に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Tailscale と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID では、Microsoft Entra プロビジョニング サービスを使用して、[Tailscale](https://tailscale.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- Tailscale でユーザーを作成します。
- アクセスが不要になった場合は、Tailscale のユーザーを削除します。
- Microsoft Entra ID と Tailscale の間でユーザー属性の同期を維持します。
- Tailscale にグループとグループ メンバーシップをプロビジョニングする
- Tailscale への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Tailscale のユーザー アカウント。

### 手順1: プロビジョニング デプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの対象](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を決めます。
3. [Microsoft Entra ID と Tailscale の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Tailscale を構成する

これらの手順を完了するには、Tailscale の[所有者、管理者、または IT 管理者](https://tailscale.com/kb/1138/user-roles/)である必要があります。 Microsoft Entra のユーザーとグループのプロビジョニングが利用できるプランについては、[Tailscale のプラン](https://tailscale.com/pricing/)を参照してください。

#### Tailscale で SCIM API キーを生成します。

管理コンソールの [**[ユーザー管理](https://login.tailscale.com/admin/settings/user-management/)**] ページで、

1. [ **プロビジョニングを有効にする] を選択します**。
2. 生成されたキーをクリップボードにコピーします。

キー情報をセキュリティで保護された場所に保存します。 これは、Microsoft Entra ID でプロビジョニングを構成するときに使用する必要があるシークレット トークンです。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Tailscale を追加する

Microsoft Entra アプリケーション ギャラリーから Tailscale を追加して、Tailscale へのプロビジョニングの管理を開始します。 SSO のために Tailscale を既に設定してある場合は、同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニング対象のユーザーのスコープを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Tailscale への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づいて、Tailscale でユーザーまたはグループが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成するステップについて説明します。

#### Microsoft Entra ID 内で Tailscale の自動ユーザー プロビジョニングを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Tailscale]** を選択します。

    [Image: アプリケーション リストの Tailscale リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Tailscale テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Tailscale に接続できることを確認します。 接続に失敗した場合は、Tailscale アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Tailscale に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Tailscale のユーザー アカウントとの照合に使用されます。 [照合対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合、その属性に基づいたユーザーのフィルター処理を Tailscale API がサポートしているか確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Tailscale で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 | ✓ |  |
    | displayName | 糸 | ✓ |  |
    | 優先言語 | 糸 | ✓ |  |
    | name.givenName | 糸 | ✓ |  |
    | name.familyName | 糸 | ✓ |  |
    | name.formatted | 糸 | ✓ |  |
    | emails[type eq "work"].value | 糸 | ✓ |  |
    | externalId | 糸 | ✓ | ✓ |
12. **[グループ] を選択します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Tailscale に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Tailscale のグループの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Tailscale で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 更新履歴

- 2023 年 11 月 21 日 - **グループ プロビジョニング**のサポートを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/talent-palette-tutorial"} -->
## Microsoft Entra ID で Talent Palette for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/talent-palette-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Talent Palette の間でシングル サインオンを構成する方法について学習します。

この記事では、Talent Palette と Microsoft Entra ID を統合する方法について説明します。 Talent Palette を Microsoft Entra ID と統合すると、次のことができます。

- Talent Palette にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Talent Palette に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Talent Palette シングル サインオン (SSO) 対応のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Folloze では、 **IDP** Initiated SSO がサポートされます。
- Folloze では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Talent Palette の追加

Microsoft Entra ID への Talent Palette の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Talent Palette を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Talent Palette**」と入力します。
4. 結果パネルから **Talent Palette** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Talent Palette 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Talent Palette に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Talent Palette の関連ユーザーとの間にリンク関係を確立する必要があります。

Talent Palette で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Talent Palette の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Talent Palette のテスト ユーザーの作成** - Talent Palette で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Talent Palette**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://talent-p.net/saml/acs/<TENANT_ID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://talent-p.net/saml/sso/<TENANT_ID>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには [、Talent Palette クライアント サポート チーム](mailto:talent-support@pa-consul.co.jp) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Talent Palette のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Talent Palette SSO の構成

**Talent Palette** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、アプリケーション構成からコピーした適切な URL を [Talent Palette サポート チーム](mailto:talent-support@pa-consul.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Talent Palette のテスト ユーザーの作成

このセクションでは、Talent Palette で B.Simon というユーザーを作成します。 Talent Palette では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Talent Palette にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Talent Palette に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Talent Palette] タイルを選択すると、SSO を設定した Talent Palette に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/talentech-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Talentech を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/talentech-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-14
- Summary: Microsoft Entra ID から Talentech に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法についてご確認ください。

この記事では、自動ユーザー プロビジョニングを構成するために Talentech と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Talentech](https://www.talentech.com) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Talentech でユーザーを作成する
- アクセスが不要になった場合に Talentech のユーザーを削除する
- Microsoft Entra ID と Talentech の間でユーザー属性の同期を維持する
- Talentech でグループとグループ メンバーシップをプロビジョニングする
- Talentech へのシングル サインオン (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Talentech のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Talentech の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Talentech を構成する

1. [Talentech](https://www.talentech.com) にログインします。
2. 左側のパネルで [ **統合** ] に移動し、[ **新しい統合の追加]** を選択します。

    [Image: Talentech 統合設定ページのスクリーンショット。]
3. 統合の **名前** を入力し、[追加] を選択 **します**。
4. 作成した統合に移動し、[ **API アクセス トークンの作成**] を選択します。

    [Image: Talentech API アクセス トークン ページのスクリーンショット。]
5. アクセス トークンが生成されます。 この値は、Talentech アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

    [Image: 生成された Talentech ベアラー トークンのスクリーンショット。]
6. Talentech サポートに連絡して、テナント URL を生成してもらいます。 この値は、Talentech アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Talentech を追加する

Microsoft Entra アプリケーション ギャラリーから Talentech を追加して、Talentech へのプロビジョニングの管理を開始します。 SSO のために Talentech を既に設定してある場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Talentech への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、TestApp でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Talentech の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Talentech** を選択します。

    [Image: アプリケーションの一覧の Talentech リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Talentech テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Talentech に接続できることを確認します。 接続に失敗した場合は、Talentech アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Talentech に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Talentech のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Talentech API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | externalId | 糸 |  |
    | 活動中 | ブール値 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
12. **[グループ] を選択します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Talentech に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作のために Talentech のグループを照合するために使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/talentlms-tutorial"} -->
## Microsoft Entra ID で TalentLMS for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/talentlms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TalentLMS の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、TalentLMS と Microsoft Entra ID を統合する方法について説明します。 TalentLMS と Microsoft Entra ID を統合すると、次のことができます:

- TalentLMS にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って TalentLMS に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TalentLMS でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TalentLMS では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの TalentLMS の追加

Microsoft Entra ID への TalentLMS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TalentLMS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TalentLMS**」と入力します。
4. 結果のパネルから **[TalentLMS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TalentLMS 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TalentLMS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TalentLMS の関連ユーザーとの間にリンク関係を確立する必要があります。

TalentLMS に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TalentLMS の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TalentLMS テストユーザーを作成** - Microsoft Entra のユーザー表現にリンクされた B.Simon の対応ユーザーを TalentLMS に作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**TalentLMS**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `<tenant-name>.talentlms.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.TalentLMSapp.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[TalentLMS クライアント サポート チーム](https://www.talentlms.com/contact)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[TalentLMS のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TalentLMS の SSO の構成

1. 別の Web ブラウザー ウィンドウで、TalentLMS 企業サイトに管理者としてサインインします。
2. [ **アカウントと設定] セクションで** 、[ **ユーザー** ] タブを選択します。

    [Image: アカウントと設定]
3. **[Single Sign-On (SSO)]\(シングル Sign-On (SSO)\) を**選択します。
4. [Single Sign-On] セクションで、次の手順に従います。

    [Image: シングル サインオン]

    ａ。 **[SSO integration type]** 一覧から、**[SAML 2.0]** を選択します。

    b。 **[ID プロバイダー (IDP)]** テキスト ボックスに **[Microsoft Entra 識別子]** の値を貼り付けます。

    c. Azure Portal の**拇印**の値を、 **[Certificate fingerprint](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書のフィンガープリント)** ボックスに貼り付けます。

    d. **[リモート サインイン URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    え **[リモート サインアウト URL]** テキストボックスに **[ログアウト URL]** の値を貼り付けます。

    f. 次の入力を行います。

    - **[TargetedID](ターゲット ID)** ボックスに、「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`」と入力します。
    - **[First name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`」と入力します。
    - **[Last name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`」と入力します。
    - **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** ボックスに「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」と入力します。
5. **保存** を選択します。

#### TalentLMS のテスト ユーザーの作成

Microsoft Entra ユーザーが TalentLMS にサインインできるようにするには、そのユーザーを TalentLMS にプロビジョニングする必要があります。 TalentLMS の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. **TalentLMS** テナントにサインインします。
2. **Users** を選択し、ユーザーの追加**選択**。
3. **[Add user]** ダイアログ ページで、以下の手順を実行します。

    [Image: ユーザーを追加]

    ａ。 **[名]** テキストボックスに、`Britta` のようにユーザーの名を入力します。

    b。 **[Last name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (この例では `Simon`)。

    c. **[Email address](メール アドレス)** ボックスに、ユーザーのメール アドレスを入力します (たとえば、`brittasimon@contoso.com`)。

    d. [ **ユーザーの追加] を選択します**。

注

他の TalentLMS ユーザー アカウント作成ツールや、TalentLMS から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TalentLMS のサインオン URL にリダイレクトされます。
- TalentLMS のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで TalentLMS タイルを選択すると、このオプションは TalentLMS のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/talentsoft-tutorial"} -->
## Microsoft Entra ID で Talentsoft for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/talentsoft-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Talentsoft の間でシングル サインオンを構成する方法について説明します。

この記事では、Talentsoft と Microsoft Entra ID を統合する方法について説明します。 Talentsoft を Microsoft Entra ID と統合すると、次のことができます:

- Talentsoft にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Talentsoft に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Talentsoft でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Talentsoft では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーから Talentsoft を追加する

Microsoft Entra ID への Talentsoft の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Talentsoft を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Talentsoft**」と入力します。
4. 結果のパネルから **Talentsoft** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Talentsoft 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Talentsoft に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Talentsoft の関連ユーザーとの間にリンク関係を確立する必要があります。

Talentsoft で Microsoft Entra SSO を構成してテストするには、次の手順を行います:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Talentsoft の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Talentsoft におけるテストユーザーの作成** - Talentsoft で B.Simon に対応するテストユーザーを作成し、そのユーザーを Microsoft Entra の表象にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Talentsoft**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンのセットアップ] ページで** 、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<fedserver>/<tenant>/trust`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<fedserver>/<tenant>/saml20`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant>.talent-soft.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Talentsoft クライアント サポート チーム](mailto:advancedservices@talentsoft.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Talentsoft の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Talentsoft SSO の構成

**Talentsoft** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Talentsoft サポート チーム](mailto:advancedservices@talentsoft.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Talentsoft テスト ユーザーの作成

このセクションでは、Talentsoft で B.Simon というユーザーを作成します。 [Talentsoft サポート チーム](mailto:advancedservices@talentsoft.com)と連携して、Talentsoft プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Talentsoft のサインオン URL にリダイレクトされます。
- Talentsoft のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Talentsoft に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Talentsoft タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Talentsoft に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/talon-tutorial"} -->
## Microsoft Entra ID で Talon for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/talon-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Talon の間にシングル サインオンを構成する方法について説明します。

この記事では、Talon と Microsoft Entra ID を統合する方法について説明します。 Chromium ベースのブラウザーである Talon は、エンドポイント Web トラフィックを分離し、応答性の高いネイティブ ユーザー エクスペリエンスを提供します。 Talon では、Microsoft Entra ID との統合が行われ、オンボードとポリシーの適用が効率化されます。 Talon と Microsoft Entra ID を統合すると、次のことができます。

- Talon にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Talon に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Talon 向けの Microsoft Entra シングル サインオンを構成してテストします。 Talon では、**IDP** によって開始されるシングル サインオンがサポートされています。

### [前提条件]

Microsoft Entra ID を Talon と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Talon のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Talon アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Talon を追加する

Microsoft Entra アプリケーション ギャラリーから Talon を追加して、Talon とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Talon**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. Talon アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Talon アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
8. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Talon のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Talon SSO を構成する

**Talon** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Talon サポート チーム](mailto:support@talon-sec.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Talon テスト ユーザーを作成する

このセクションでは、Talon で Britta Simon というユーザーを作成します。 [Talon サポート チーム](mailto:support@talon-sec.com)と協力して、Talon プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Talon に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Talon] タイルを選択すると、SSO を設定した Talon に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tango-reserve-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Tango Reserve by AgilQuest (EU Instance) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tango-reserve-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tango Reserve by AgilQuest (EU Instance) の間にシングル サインオンを構成する方法について説明します。

この記事では、Tango Reserve by AgilQuest (EU Instance) と Microsoft Entra ID を統合する方法について説明します。 Tango Reserve by AgilQuest (EU Instance) を Microsoft Entra ID と統合すると、次のことができます。

- Tango Reserve by AgilQuest (EU Instance) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Tango Reserve by AgilQuest (EU Instance) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Tango Reserve by AgilQuest (EU Instance) のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Tango Reserve by AgilQuest (EU Instance) では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Tango Reserve by AgilQuest (EU Instance) を追加する

Microsoft Entra ID への Tango Reserve by AgilQuest (EU Instance) の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Tango Reserve by AgilQuest (EU Instance) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Tango Reserve by AgilQuest (EU Instance)**」と入力します。
4. 結果パネルから **[Tango Reserve by AgilQuest (EU Instance)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tango Reserve by AgilQuest (EU Instance) 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Tango Reserve by AgilQuest (EU Instance) に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Tango Reserve by AgilQuest (EU Instance) の関連ユーザーとの間にリンク関係を確立する必要があります。

Tango Reserve by AgilQuest (EU Instance) に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tango Reserve by AgilQuest SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **AgilQuest の Tango Reserve におけるテストユーザーを作成** - Tango Reserve by AgilQuest (EU インスタンス) で B.Simon の対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Tango Reserve by AgilQuest (EU Instance)**&gt;**シングル サインオン**に参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://euauth.agilquest.com/eas-saml` |
    | `https://euauth.agilquest.com/eas-saml<alias>` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://euauth.agilquest.com/eas-saml/saml/SSO` |
    | `https://euauth.agilquest.com/eas-saml/saml/SSO/alias/<alias>` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://euauth.agilquest.com/eas-saml/saml/web/auth/<CustomerAlias>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Tango Reserve by AgilQuest (EU Instance) サポート チーム](mailto:support-agilquest@tangoanalytics.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Set up Tango Reserve by AgilQuest (EU Instance)] (Tango Reserve by AgilQuest (EU Instance) のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tango Reserve by AgilQuest SSO の構成

**Tango Reserve by AgilQuest (EU Instance)** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Tango Reserve by AgilQuest (EU Instance) サポート チーム](mailto:support-agilquest@tangoanalytics.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Tango Reserve by AgilQuest のテスト ユーザーの作成

このセクションでは、Tango Reserve by AgilQuest (EU Instance) で Britta Simon というユーザーを作成します。 [Tango Reserve by AgilQuest (EU Instance) サポート チーム](mailto:support-agilquest@tangoanalytics.com)と連携し、Tango Reserve by AgilQuest (EU Instance) プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Tango Reserve by AgilQuest (EU Instance) のサインオン URL にリダイレクトされます。
- Tango Reserve by AgilQuest (EU Instance) のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Tango Reserve by AgilQuest (EU インスタンス) に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Tango Reserve by AgilQuest (EU Instance)] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Tango Reserve by AgilQuest (EU Instance) に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tangoanalytics-tutorial"} -->
## Microsoft Entra ID で Tango Analytics for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tangoanalytics-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tango Analytics の間でシングル サインオンを構成する方法について説明します。

この記事では、Tango Analytics と Microsoft Entra ID を統合する方法について説明します。 Tango Analytics を Microsoft Entra ID と統合すると、次のことができます。

- Tango Analytics にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Tango Analytics に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Tango Analytics でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Tango Analytics では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Tango Analytics の追加

Microsoft Entra ID への Tango Analytics の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Tango Analytics を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Tango Analytics**」と入力します。
4. 結果のパネルから **[Tango Analytics]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tango Analytics 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Tango Analytics に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Tango Analytics の関連ユーザーとの間にリンク関係を確立する必要があります。

Tango Analytics に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tango Analytics SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Tango Analytics テストユーザーを作成** - Tango Analytics で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上のユーザーの表示にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Tango Analytics**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、値を入力します。 `TACORE_SSO`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://mts.tangoanalytics.com/saml2/sp/acs/post`
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Tango Analytics のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tango Analytics SSO の構成

**Tango Analytics** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Tango Analytics サポート チーム](mailto:support@tangoanalytics.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Tango Analytics のテスト ユーザーの作成

このセクションでは、Tango Analytics で Britta Simon というユーザーを作成します。 [Tango Analytics サポート チーム](mailto:support@tangoanalytics.com)と協力して、Tango Analytics プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Tango Analytics に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Tango Analytics] タイルを選択すると、SSO を設定した Tango Analytics に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tangoe-tutorial"} -->
## Microsoft Entra ID で Tangoe Command Premium Mobile for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tangoe-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tangoe Command Premium Mobile の間でシングル サインオンを構成する方法について説明します。

この記事では、Tangoe Command Premium Mobile と Microsoft Entra ID を統合する方法について説明します。 Tangoe Command Premium Mobile と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Tangoe Command Premium Mobile へのアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Tangoe Command Premium Mobile に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Tangoe Command Premium Mobile でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Tangoe Command Premium Mobile では、SP **によって開始された SSO** をサポートしています。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Tangoe Command Premium Mobile を追加する

Microsoft Entra ID への Tangoe Command Premium Mobile の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Tangoe Command Premium Mobile を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリーから追加する ] セクションで、検索ボックスに「**Tangoe Command Premium Mobile**」と入力します。
4. 結果パネルから [**Tangoe Command Premium Mobile**] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tangoe Command Premium Mobile の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Tangoe Command Premium Mobile に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Tangoe Command Premium Mobile の関連ユーザーとの間にリンク関係を確立する必要があります。

Tangoe Command Premium Mobile で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Tangoe Command Premium Mobile の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Tangoe Command Premium Mobile のテストユーザーを作成 - B.Simon の** 対応ユーザーを Tangoe Command Premium Mobile 内で作成し、Microsoft Entra のユーザーとリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Tangoe Command Premium Mobile**&gt;**シングルサインオン**を参照してください。
3. [**シングル サインオン方法の選択]** ページで、[**SAML]**を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    基本的な SAML 構成を編集
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    エー。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://sso.tangoe.com/sp/startSSO.ping?PartnerIdpId=<TENANT_ISSUER>&TARGET=<TARGET_PAGE_URL>`

    b。 [**応答 URL** テキスト ボックスに、URL: `https://sso.tangoe.com/sp/ACS.saml2` を入力します。

    手記

    これらの値は実際の値ではありません。 実際のサインオン URL でこれらの値を更新します。 これらの値を入手するには、[Tangoe Command Premium Mobile クライアントサポートチーム](https://www.tangoe.com/contact-us/) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**Tangoe Command Premium Mobile** のセットアップ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Tangoe Command Premium Mobile SSO の構成

Tangoe Command Premium Mobile **側** シングル サインオンを構成するには、ダウンロードした **フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を Tangoe Command Premium Mobile サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Tangoe Command Premium Mobile のテスト ユーザーの作成

このセクションでは、Tangoe Command Premium Mobile で Britta Simon というユーザーを作成します。 [Tangoe Command Premium Mobile サポート チームと連携](https://www.tangoe.com/contact-us/)、Tangoe Command Premium Mobile プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Tangoe Command Premium Mobile のサインオン URL にリダイレクトされます。
- Tangoe Command Premium Mobile のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Tangoe Command Premium Mobile] タイルを選択すると、このオプションは Tangoe Command Premium Mobile のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tanium-sso-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用にTanium SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tanium-sso-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-14
- Summary: Microsoft Entra ID から Tanium SSO に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために、Tanium SSO と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Tanium SSO](https://www.tanium.com/) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 これらの機能は、Tanium Cloud のお客様に対してのみサポートされています。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Tanium SSO でユーザーを作成する。
- アクセスが不要になった場合は、Tanium SSO でユーザーを削除します。
- Microsoft Entra ID と Tanium SSO の間でユーザー属性の同期を維持します。
- Tanium SSO でグループとそのメンバーシップをプロビジョンする。
- Tanium SSO への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tanium-sso-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Tanium SSO のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Tanium SSO の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Tanium クラウド管理ポータル (CMP) で SCIM プロビジョニングを有効にする

- [「Tanium Cloud デプロイ ガイド: Configure SCIM Provisioning](https://docs.tanium.com/cloud/cloud/configuring_identity_providers.html#configure_scim)」の手順に従って、Tanium Cloud で 自動ユーザープロビジョニングを有効にしたいです。
- 後でTanium SSO を構成する際に使用するために、 **トークン** と **SCIM API URL** の値を保持します。 `token-\<58 alphanumeric characters\>` のように書式設定されたトークン文字列全体をコピーします。

### 手順 3: Microsoft Entra アプリケーション ギャラリーからTanium SSO を追加する

Microsoft Entra アプリケーション ギャラリーから Tanium SSO を追加して、Tanium SSO へのプロビジョニングの管理を開始します。 SSO のために Tanium SSO を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Tanium SSO への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Tanium でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID でTanium SSO の自動ユーザー プロビジョニングを構成する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Tanium SSO]** を選択します。

    [Image: アプリケーション リストの Tanium SSO リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Tanium SSO テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID がTanium SSO に接続できることを確認します。 接続に失敗した場合は、お使いのTanium SSO アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Tanium SSO に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Tanium SSO のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が谷um SSO API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Tanium SSO で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | displayName | 糸 |  | ✓ |
    | externalId | 糸 |  | ✓ |
12. **[グループ] を選択します**。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Tanium SSO に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Tanium SSO のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Tanium SSO で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tanium-sso-tutorial"} -->
## Microsoft Entra ID でTanium SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tanium-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Tanium SSO 間にシングル サインオンを構成する方法について説明します。

この記事では、Tanium SSO と Microsoft Entra ID を統合する方法について説明します。 コンバージド エンドポイント管理 (XEM) の業界唯一のプロバイダーである Tanium は、複雑なセキュリティおよびテクノロジー環境を管理する従来の方法からのパラダイム シフトを先導しています。 Tanium SSO と Microsoft Entra ID を統合すると、次のことができます。

- Tanium SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Tanium SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Tanium SSO 用に Microsoft Entra のシングル サインオンを構成してテストします。 Tanium SSO では、**SP** と **IDP** によって開始されるシングル サインオンと、**Just In Time** ユーザー プロビジョニングの両方がサポートされています。 Tanium SSO では、[自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tanium-sso-provisioning-tutorial)もサポートされています。

### [前提条件]

Microsoft Entra ID とTanium SSO を統合するには、以下のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Tanium SSO のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Tanium SSO アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Tanium SSO を追加する

Microsoft Entra アプリケーション ギャラリーから Tanium SSO を追加して、Tanium SSO でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Tanium SSO**&gt;**シングル サインオンを参照します**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`urn:amazon:cognito:sp:<InstanceName>` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<InstanceName>-tanium.auth.<SUBDOMAIN>.amazoncognito.com/saml2/idpresponse`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<InstanceName>.cloud.tanium.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Tanium サポート](https://community.tanium.com/s/contactsupport) にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。

    注

    オンプレミスの構成で Tanium をデプロイする場合、値が上記とは異なることがあります。 使用する値は、Tanium コンソールの **[Administration]&gt; [SAML Configuration]** メニューから取得できます。 詳細については、[Tanium コンソール ユーザー ガイド: 「Integrating with a SAML IdP」](https://docs.tanium.com/platform_user/platform_user/console_using_saml.html?cloud=false)を参照してください。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。 オンプレミスの構成でTaniumにデプロイする場合は、編集ボタンを選択し、 **応答署名オプション** を「応答とアサーションに署名する」に設定します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Tanium SSO を構成する

**Tanium SSO** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[谷um サポート](https://community.tanium.com/s/contactsupport)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Tanium SSO のテスト ユーザーを作成する

このセクションでは、B. Simon というユーザーを Tanium SSO に作成します。 Tanium SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Tanium SSO にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションによって、ログイン フローを開始できる谷um SSO サインオン URL にリダイレクトされます。
- Tanium SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したTanium SSO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Tanium SSO] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定したTanium SSO に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tap-app-security-provisioning-tutorial"} -->
## MICROSOFT Entra ID を使用した自動ユーザー プロビジョニング用に TAP App Security を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tap-app-security-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-24
- Summary: Microsoft Entra ID から TAP App Security に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、TAP App Security と Microsoft Entra ID の両方で実行して、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[TAP App Security](https://tapappsecurity.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- TAP App Securityでユーザーを作成する。
- アクセスが不要になった場合は、TAP App Security のユーザーを削除します。
- Microsoft Entra ID と TAP App Security の間でユーザー属性の同期を維持する
- TAP App Security への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tap-app-security-tutorial)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者のアクセス許可を持つ TAP App Security のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と TAP App Security の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように TAP App Security を構成する

1. [TAP App Security のバックエンド コントロール パネル](https://app.tapappsecurity.com/)にログインします。
2. **[Single Sign On](シングル サインオン) &gt; [Active Directory]** の順に移動します。
3. **[Active Directory アプリの統合**] ボタンを選択します。 次に、組織のドメインを入力し、[ **保存]** ボタンを選択します。 [Image: ドメインを追加する方法に関するスクリーンショット。]
4. ドメインを入力すると、テーブル内の新しい行に、ドメイン名とその状態 (**[initialize](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/初期化)**) が表示されます。 歯車アイコンを選択すると、TAP アプリのセキュリティ サーバーに関する技術データが表示され、初期化が完了します。 [Image: 初期化を示すスクリーンショット。]
5. TAP App Security サーバーに関する技術データが明らかになります。 このページから **テナント URL** と **承認トークン** をコピーして、後で Microsoft Entra ID でプロビジョニングを設定するときに使用できるようになりました。 [Image: ドメインの詳細を示すスクリーンショット。]

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから TAP App Security を追加する

Microsoft Entra アプリケーション ギャラリーから TAP App Security を追加して、TAP App Security へのプロビジョニングの管理を開始します。 SSO のために TAP App Security を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: TAP App Security への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー/グループの割り当てに基づいて TAP App Security 内のユーザー/グループを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で TAP App Security の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[TAP App Security]** を選択します。

    [Image: アプリケーションの一覧内の TAP App Security のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、TAP App Security テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が TAP App Security に接続できることを確認します。 接続に失敗した場合は、TAP App Security アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から TAP App Security に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で TAP App Security のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、TAP App Security API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | TAP App Security による要求 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | displayName | 糸 |  | ✓ |
    | タイトル | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tap-app-security-tutorial"} -->
## Microsoft Entra ID で TAP App Security for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tap-app-security-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TAP App Security の間でシングル サインオンを構成する方法について説明します。

この記事では、TAP App Security と Microsoft Entra ID を統合する方法について説明します。 TAP App Security を Microsoft Entra ID と統合すると、次のことができます。

- TAP App Security にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して TAP App Security に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TAP App Security でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TAP App Security では、**SP** Initiated SSO がサポートされます。
- TAP App Security では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの TAP App Security の追加

TAP App Security の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TAP App Security を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TAP App Security**」と入力します。
4. 結果のパネルから **[TAP App Security]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TAP App Security 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TAP App Security で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TAP App Security の関連ユーザーとの間にリンク関係を確立する必要があります。

TAP App Security で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TAP App Security の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TAP App Security のテスト ユーザーの作成** - TAP App Security で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TAP App Security**&gt;**シングル サインオン**にアクセスしてください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 **[識別子]** ボックスに、`https://app.tapappsecurity.com/<CUSTOMER_DOMAIN_WITHOUT_EXTENSION>/metadata` という形式で URL を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.tapappsecurity.com/<CUSTOMER_DOMAIN_WITHOUT_EXTENSION>/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://webapp.tapappsecurity.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[TAP App Security クライアント サポート チーム](mailto:support@tapappsecurity.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. TAP App Security アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、TAP App Security アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | まずは | ユーザー.ファーストネーム |
    | ログイン (login) | ユーザー.ユーザープリンシパルネーム |
    | 最後 | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[TAP App Security のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TAP App Security の SSO の構成

1. TAP App Security の Web サイトに管理者としてログインします。
2. 左側のナビゲーション バーで [ **シングル サインオン** ] を選択し、[ **Active Directory**] を選択します。

    [Image: シングル サインオンのスクリーンショット。]
3. **[Active Directory アプリの統合**] ボタンを選択します。 次に、組織のドメインを入力し、[ **保存]** ボタンを選択します。

    [Image: Active Directory のスクリーンショット。]
4. 次に示すように、歯車アイコンを選択します。

    [Image: アイコンのスクリーンショット。]
5. **[Active Directory integration](Active Directory 統合)** ページで、次の手順を実行します。

    [Image: 統合のスクリーンショット。]

    ある。 **[応答 URL (Assertion Consumer Service URL)]** の値をコピーし、その値を **[Basic SAML Configuration](基本的な SAML 構成)** セクションの **[応答 URL]** テキスト ボックスに貼り付けます。

    b。 **[識別子 (エンティティ ID)]** の値をコピーし、その値を **[Basic SAML Configuration](基本的な SAML 構成)** セクションの **[識別子]** テキスト ボックスに貼り付けます。

    c. [ **ファイルの選択] を選択** して、ダウンロードした **フェデレーション メタデータ XML** ファイルをアップロードします。

    d. **保存** を選択します。

#### TAP App Security のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを TAP App Security に作成します。 TAP App Security では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ TAP App Security に存在していない場合は、TAP App Security にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TAP App Security のサインオン URL にリダイレクトされます。
- TAP App Security のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TAP App Security] タイルを選択すると、このオプションは TAP App Security のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/target-process-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TargetProcess を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/target-process-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TargetProcess の間にシングル サインオンを構成する方法について説明します。

この記事では、TargetProcess と Microsoft Entra ID を統合する方法について説明します。 TargetProcess と Microsoft Entra ID を統合すると、次のことができます。

- TargetProcess にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TargetProcess に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TargetProcess でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TargetProcess では、 **SP** によって開始される SSO がサポートされます。
- TargetProcess では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから TargetProcess を追加する

Microsoft Entra ID への TargetProcess の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TargetProcess を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を表示します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「TargetProcess**」と入力します。
4. 結果パネルから **TargetProcess** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TargetProcess 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TargetProcess に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと TargetProcess の関連ユーザーとの間にリンク関係を確立する必要があります。

TargetProcess に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TargetProcess の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TargetProcess テストユーザーを作成し**、そのユーザーを B.Simon に対応させ、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**TargetProcess**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tpondemand.com/`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.tpondemand.com/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [TargetProcess クライアント サポート チーム](mailto:support@targetprocess.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **TargetProcess のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TargetProcess SSO の構成

1. 管理者として TargetProcess アプリケーションにサインオンします。
2. 上部のメニューで、[セットアップ] を選択 **します**。

    [Image: セットアップ]
3. [ **設定] タブを** 選択します。

    [Image: 設定]
4. [ **シングル サインオン** ] タブを選択します。

    [Image: [シングル サインオン] を選択する]
5. [Single Sign-on] の設定ダイアログで、次の手順を実行します。

    [Image: シングル サインオンの構成]

    ア. [ **シングル サインオンを有効にする] を選択します**。

    b。 [ **サインオン URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    c. ダウンロードした証明書をメモ帳で開き、内容をコピーして[ **証明書** ]テキストボックスに貼り付けます。

    d. [ **JIT プロビジョニングを有効にする]** を選択します。

    え **[保存] を選択します**。

#### TargetProcess テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TargetProcess に作成します。 TargetProcess では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 TargetProcess にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [TargetProcess サポート チーム](mailto:support@targetprocess.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TargetProcess のサインオン URL にリダイレクトされます。
- TargetProcess のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TargetProcess] タイルを選択すると、このオプションは TargetProcess のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tas-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TAS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tas-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TAS の間にシングル サインオンを構成する方法について説明します。

この記事では、TAS と Microsoft Entra ID を統合する方法について説明します。 TAS を Microsoft Entra ID を統合すると、次のことができます。

- TAS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TAS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TAS でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TAS では、**SP と IDP** Initiated SSO がサポートされます。

### ギャラリーからの TAS の追加

Microsoft Entra ID への TAS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TAS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに **[TAS]** と入力します。
4. 結果のパネルから **[TAS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TAS 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、TAS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと TAS の関連ユーザーとの間にリンク関係を確立する必要があります。

TAS に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TAS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TAS テストユーザーを作成する - TAS に B.Simon の対応物として作成し、Microsoft Entra においてそのユーザーの表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**TAS**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://taseu.combtas.com/<DOMAIN>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://taseu.combtas.com/<ENVIRONMENT_NAME>/AssertionService.aspx`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://taseu.combtas.com/<DOMAIN>`

    注

    これらの値は実際の値ではありません。 これらは、実際の識別子、応答 URL、サインオン URL で更新します。これについては、この記事の後半で説明します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[TAS のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TAS SSO の構成

1. 別の Web ブラウザー ウィンドウで、TAS に管理者としてログインします。
2. メニューの左側で、[ **設定]** を選択し、[ **管理者** ] に移動し、[ **シングル サインオンの管理**] を選択します。

    [Image: [Manage Single Sign-On](シングル サインオンの管理) が選択されていることを示すスクリーンショット。]
3. **[Manage Single sign on](シングル サインオンの管理)** ページで、次の手順を実行します。

    [Image: [Manage Single sign on](シングル サインオンの管理) ページのスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** ボックスに環境の名前を入力します。

    b。 **[Authentication Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の種類)** として **[SAML2]** を選択します。

    c. **[URL の入力]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    d. ダウンロードした Base-64 でエンコードされた証明書をメモ帳で開き、その内容をコピーして **[Enter Certification](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書の入力)** ボックスに貼り付けます。

    え **[Enter New IP](新しい IP の入力)** ボックスに、IP アドレスを入力します。

    注

    IP アドレスを取得するには、[TAS サポート チーム](mailto:support@combtas.com)にお問い合わせください。

    f. **シングル サインオン** URL をコピーして、Azure portal の **[基本的な SAML 構成]** の **[識別子 (エンティティ ID)]** および **[サインオン URL]** ボックスに貼り付けます。 URL は、大文字と小文字が区別され、スラッシュ (/) で終わる必要があることに注意してください。

    ジー セットアップ ページの**アサーション サービス** URL をコピーし、Azure portal の **[基本的な SAML 構成]** の **[応答 URL]** ボックスに貼り付けます。

    h. [ **SSO 行の挿入] を選択します**。

#### TAS テスト ユーザーの作成

このセクションでは、TAS で Britta Simon というユーザーを作成します。 [TAS サポート チーム](mailto:support@combtas.com)と連携し、TAS プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TAS サインオン URL にリダイレクトされます。
- TAS のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TAS に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで TAS タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TAS に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tasc-beta-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TASC (ベータ) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tasc-beta-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TASC (ベータ版) の間のシングル サインオンを構成する方法について説明します。

この記事では、TASC (ベータ) と Microsoft Entra ID を統合する方法について説明します。 TASC (ベータ版) は、選択とキャリア開発のための心理テスト プラットフォームであり、人事担当者はベータ環境で人材に関するより良い決定を行うことができます。 TASC (ベータ版) を Microsoft Entra ID と統合すると、次のことが可能になります。

- TASC (ベータ版) へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して TASC (ベータ版) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

TASC (ベータ版) に対する Microsoft Entra のシングル サインオンをテスト環境で構成およびテストする。 TASC (ベータ版) は、**SP** 開始および **IDP** 開始の両方のシングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートしています。

### [前提条件]

Microsoft Entra ID を TASC (ベータ版) と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- TASC (ベータ版) でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから TASC (ベータ版) アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーからTASC (ベータ版) を追加する

Microsoft Entra アプリケーション ギャラリーから TASC (ベータ版) を追加して、TASC (ベータ版) に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[TASC (ベータ版)]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.beta.tascnet.be/saml/<CustomerName>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.beta.tascnet.be/saml/<CustomerName>/acs`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://login.beta.tascnet.be/saml/<CustomerName>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[TASC (ベータ版) サポート チーム](mailto:support@cebir.be)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. TASC (ベータ版) アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. その他に、TASC (ベータ版) アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | groups | ユーザー.グループ |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### TASC (ベータ版) の SSO を構成する

**TASC (ベータ版)** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [TASC (ベータ版) サポート チーム](mailto:support@cebir.be)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TASC (ベータ版) のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを TASC (ベータ版) に作成します。 TASC (ベータ版) では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 TASC (ベータ版) にユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TASC (ベータ) のサインオン URL にリダイレクトされます。
- TASC (ベータ版) サインオン URLに直接アクセスし、そこからログインフローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TASC (ベータ) に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで TASC (ベータ) タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TASC (ベータ) に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/taskize-connect-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Taskize Connect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/taskize-connect-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-24
- Summary: Microsoft Entra ID から Taskize Connect に、ユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Taskize Connect と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Taskize Connect](https://www.taskize.com/) に対してユーザーとグループの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Taskize Connect でユーザーを作成する。
- アクセスが不要になったら、Taskize Connect のユーザーを削除します。
- Microsoft Entra ID と Taskize Connect の間でユーザー属性の同期を維持します。
- Taskize Connect でグループとグループ メンバーシップをプロビジョニングする
- Taskize Connect への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/taskize-connect-tutorial) (推奨)。
- コード認証許可フロー認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Taskize Connect](https://www.taskize.com/) のテナント。
- 管理者権限を持つ Taskize Connect のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Taskize Connect の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Taskize Connect を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Taskize Connect を構成するには、[Taskize Connect のサポート チーム](mailto:support@taskize.com)に連絡してください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Taskize Connect を追加する

Microsoft Entra アプリケーション ギャラリーから Taskize Connect を追加して、Taskize Connect へのプロビジョニングの管理を開始します。 SSO のために Taskize Connect を既に設定してある場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Taskize Connect への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーやグループの割り当てに基づいて Taskize Connect のユーザーやグループを作成、更新、無効化するよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Taskize Connect の自動ユーザー プロビジョニングを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Taskize Connect]** を選択します。

    [Image: アプリケーションの一覧の Taskize Connect のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Taskize Connect テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Taskize Connect に接続できることを確認します。 接続に失敗した場合は、Taskize Connect アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Taskize Connect に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Taskize Connect のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Taskize Connect API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | name.formatted | 糸 |  |
    | externalId | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Taskize Connect に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作で Taskize Connect のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
    | members | リファレンス |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/taskize-connect-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Taskize Connect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/taskize-connect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Taskize Connect の間でシングル サインオンを構成する方法について説明します。

この記事では、Taskize Connect と Microsoft Entra ID を統合する方法について説明します。 Taskize Connect と Microsoft Entra ID を統合すると、次のことができます。

- Taskize Connect にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Taskize Connect に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Taskize Connect でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Taskize Connect では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Taskize Connectは、自動ユーザープロビジョニングをサポートしています。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Taskize Connect の追加

Microsoft Entra ID への Taskize Connect の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Taskize Connect を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Taskize Connect**」と入力します。
4. 結果のパネルから **Taskize Connect** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Taskize Connect の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Taskize Connect に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Taskize Connect の関連ユーザーとの間にリンク関係を確立する必要があります。

Taskize Connect で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Taskize Connect の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Taskize Connect のテストユーザーを作成** - Taskize Connect で B. Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra 上の対応する B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**Taskize Connect**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成] の編集
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** Initiated モードで構成するには、次の手順を実行します。

    a. [**応答 URL** ボックスに、次のいずれかの URL を入力します。

    | **返信 URL** |
    | --- |
    | `https://connect.taskize.com/Shibboleth.sso/SAML2/POST` |
    | `https://docs.taskize.com/Shibboleth.sso/SAML2/POST` |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://connect.taskize.com/connect/` を入力します。
7. Taskize Connect アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. 上記に加えて、Taskize Connect アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザー.ユーザープリンシパルネーム |
9. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Taskize Connect のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Taskize Connect SSO の構成

**Taskize Connect** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Taskize Connect サポート チーム](mailto:support@taskize.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Taskize Connect テスト ユーザーの作成

このセクションの目的は、Taskize Connect で B.Simon というユーザーを作成することです。 Taskize Connect では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Taskize Connect にアクセスしようとするとき、新しいユーザーがまだ存在しない場合には、新しいユーザーが作成されます。

手記

ユーザーを手動で作成する必要がある場合は、Taskize Connect サポート チーム にお問い合わせください。

Taskize Connect では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Taskize Connect のサインオン URL にリダイレクトされます。
- Taskize Connect のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Taskize Connect に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Taskize Connect] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Taskize Connect に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teachme-biz-tutorial"} -->
## Microsoft Entra ID で Teachme Biz for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teachme-biz-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Teachme Biz 間にシングル サインオンを構成する方法について説明します。

この記事では、Teachme Biz と Microsoft Entra ID を統合する方法について説明します。 Teachme Biz を Microsoft Entra ID と統合すると、次のことができます。

- Teachme Biz にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Teachme Biz に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Teachme Biz でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Teachme Biz では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーから Teachme Biz を追加する

Microsoft Entra ID への Teachme Biz の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Teachme Biz を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Teachme Biz**」と入力します。
4. 結果パネルで **[Teachme Biz]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Teachme Biz 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Teachme Biz に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Teachme Biz の関連ユーザーとの間にリンク関係を確立する必要があります。

Teachme Biz に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Teachme Biz SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Teachme Biz におけるテストユーザーの作成** - Teachme Biz で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Teachme Biz**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://teachme.jp/saml/entity/<GroupID>`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://teachme.jp/saml/consume`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://teachme.jp/<GroupID>/login`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[Teachme Biz クライアント サポート チーム](mailto:support@teachme.jp)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Teachme Biz SSO の構成

**Teachme Biz** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Teachme Biz サポート チーム](mailto:support@teachme.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Teachme Biz のテスト ユーザーの作成

このセクションでは、Teachme Biz で Britta Simon というユーザーを作成します。 [Teachme Biz サポート チーム](mailto:support@teachme.jp)と協力して、Teachme Biz プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Teachme Biz のサインオン URL にリダイレクトされます。
- Teachme Biz のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Teachme Biz に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Teachme Biz] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Teachme Biz に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/team-today-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Team Today を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/team-today-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-24
- Summary: Microsoft Entra ID から Team Today にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Team Today と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、[Team Today](https://team-today.com) にユーザーを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Team Today でユーザーを作成します。
- アクセスが不要になった場合は、Team Today のユーザーを削除します。
- Microsoft Entra ID と Team Today の間でユーザー属性の同期を維持します。
- Team Today への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Team Today のユーザー アカウント。

### プロビジョニング配備の計画を立てるステップ1

- プロビジョニング サービスの [のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)について説明します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- [Microsoft Entra ID と Team Today 間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Team Today を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Team Today を構成するには、Team Today サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Team Today を追加する

Microsoft Entra アプリケーション ギャラリーから Team Today を追加して、Team Today へのプロビジョニングの管理を開始します。 SSO 用に Team Today を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Team Today への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Team Today のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Team Today の自動ユーザー プロビジョニングを構成するには:

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [Team Today 選択します。

    [Image: アプリケーションの一覧の [Team Today] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [プロビジョニング] タブの [Image: スクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 自動プロビジョニング タブのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Team Today テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Team Today に接続できることを確認します。 接続に失敗した場合は、Team Today アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Team Today に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、Team Today での更新処理時にユーザー アカウントを照合するために使用されます。 一致するターゲット属性 を変更する場合は、Team Today API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[** 保存] ボタンを選択して、変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | チームが本日必要としている |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 | ✓ | ✓ |
    | アクティブ | ブール値 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バーの](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamalert-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TeamAlert SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamalert-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TeamAlert SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、TeamAlert SSO と Microsoft Entra ID を統合する方法について説明します。 TeamAlert SSO と Microsoft Entra ID を統合すると、次のことができます。

- TeamAlert SSO にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して TeamAlert SSO に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TeamAlert SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TeamAlert SSO では、 **SP** によって開始される SSO のみがサポートされます。
- TeamAlert SSO では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの TeamAlert SSO の追加

Microsoft Entra ID への TeamAlert SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TeamAlert SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「TeamAlert SSO**」と入力します。
4. 結果パネルから **TeamAlert SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TeamAlert SSO の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、TeamAlert SSO に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TeamAlert SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

TeamAlert SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TeamAlert SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TeamAlert SSO のテストユーザーを作成する** - TeamAlert SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra ID にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TeamAlert SSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:amazon:cognito:sp:us-east-1_<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://teamalert-prod.auth.us-east-1.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://manage.teamalert.com/`
6. TeamAlert SSO アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、TeamAlert SSO アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 苗字 | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | ユーザー名 | ユーザー.ユーザープリンシパルネーム |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TeamAlert SSO の構成

**TeamAlert SSO** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[TeamAlert SSO サポート チーム](mailto:info@teamalert.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TeamAlert SSO テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TeamAlert SSO に作成します。 TeamAlert SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 TeamAlert SSO にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる TeamAlert SSO サインオン URL にリダイレクトされます。
- TeamAlert SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TeamAlert SSO] タイルを選択すると、このオプションは TeamAlert SSO のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamgo-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Teamgo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamgo-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-24
- Summary: Microsoft Entra ID から Teamgo に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Teamgo と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Teamgo](https://www.teamgo.co/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Teamgo でユーザーを作成する
- アクセスが不要になった場合に Teamgo のユーザーを削除する
- Microsoft Entra ID と Teamgo の間でユーザー属性の同期を維持する。
- Teamgo への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamgo-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- Azure 統合がサポートされている Teamgo アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Teamgo の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Teamgo を構成する

1. [Teamgo ダッシュボード](https://my.teamgo.co)にサインインし、[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) に移動します。
2. [設定] **で [統合** ] を選択します。
3. **[Employee Directory]\(従業員ディレクトリ**\) **で Microsoft Entra ID を**探し、[**有効にする]** ボタンを選択します。 [Image: 統合タブ]
4. **[SCIM Bearer Token](SCIM ベアラー トークン)** タブに移動し、ベアラー トークンをコピーします。 **手順 5** で**シークレット トークン**が必要です。 [Image: 統合タブ]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Teamgo を追加する

Microsoft Entra アプリケーション ギャラリーから Teamgo を追加して、Teamgo へのプロビジョニングの管理を開始します。 SSO のために Teamgo を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Teamgo への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Teamgo の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Teamgo]** を選択します。

    [Image: アプリケーションの一覧の Teamgo のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョン] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Teamgo テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Teamgo に接続できることを確認します。 接続に失敗した場合は、Teamgo アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Teamgo に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Teamgo のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Teamgo API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | 表示名 | 糸 |  |
    | タイトル | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | エクスターナルID | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamgo-tutorial"} -->
## Microsoft Entra ID で Teamgo for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamgo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Teamgo 間にシングル サインオンを構成する方法について学習します。

この記事では、Teamgo と Microsoft Entra ID を統合する方法について説明します。 Teamgo を Microsoft Entra ID と統合すると、次のことができます。

- Teamgo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Teamgo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Teamgo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Teamgo では、**SP と IDP** Initiated SSO がサポートされます。
- Teamgo では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Teamgo では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamgo-provisioning-tutorial)。

### ギャラリーからの Teamgo の追加

Microsoft Entra ID への Teamgo の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Teamgo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに **[Teamgo]** と入力します。
4. 結果のパネルから **[Teamgo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Teamgo 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Teamgo で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Teamgo の関連ユーザーとの間にリンク関係を確立する必要があります。

Teamgo で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Teamgo SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Teamgo テストユーザーの作成** - Teamgo で B.Simon の代表ユーザーを作成し、それを Microsoft Entra のユーザー表現と関連付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Teamgo**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 識別子 |
    | --- |
    | `https://my.teamgo.co` |
    | `https://my.teamgo.co/integration/saml/serviceProviderEntityId` |
    |  |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://my.teamgo.co` |
    | `https://my.teamgo.co/integration/saml?acs&domain=<DOMAIN NAME>` |
    |  |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://my.teamgo.co/integration/saml `

    注

    これは実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、[Teamgo クライアント サポート チーム](mailto:support@teamgo.co)にお問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Teamgo アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Teamgo アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 表示名 | ユーザー表示名 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Teamgo のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Teamgo SSO の構成

1. Teamgo の Web サイトに管理者としてログインします。
2. [ **キオスク]** ボタンを選択し、左側のナビゲーションの **[設定**&gt;**Integrations** ] に移動します。
3. **統合**で、[**シングル サインオン**] タブに移動し、[**構成**] を選択します。

    [Image: Teamgo の統合]
4. 次の **SAML** のページで、以下の手順を実行します。

    [Image: Teamgo の構成]

    ある。 **[SSO ドメイン]** テキストボックスに、ドメインの値を入力します。

    b。 **[発行者の URL]** テキストボックスに、前にコピーした**識別子**の値を貼り付けます。

    c. **[SAML 2.0 エンドポイント URL (HTTP)]** テキストボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    d. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[公開証明書またはフィンガープリント]** テキストボックスに貼り付けます。

    え **保存** を選択します。

#### Teamgo テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Teamgo に作成します。 Teamgo では、Just-In-Time プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Teamgo に存在していない場合は、Teamgo にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Teamgo のサインオン URL にリダイレクトされます。
- Teamgo のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Teamgo に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Teamgo] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Teamgo に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamphoria-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Teamphoria を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamphoria-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Teamphoria の間にシングル サインオンを構成する方法について説明します。

この記事では、Teamphoria と Microsoft Entra ID を統合する方法について説明します。 Teamphoria を Microsoft Entra ID と統合すると、次のことができます:

- Teamphoria にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Teamphoira に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Teamphoria でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Teamphoria では、 **SP** Initiated SSO がサポートされます

### ギャラリーからの Teamphoria の追加

Microsoft Entra ID への Teamphoria の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Teamphoria を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Teamphoria**」と入力します。
4. 結果パネルから **Teamphoria** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Teamphoria の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Teamphoria に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Teamphoria の関連ユーザーとの間にリンク関係を確立する必要があります。

Teamphoria に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Teamphoria の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Teamphoria テスト ユーザーの作成 - Teamphoria** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Teamphoria**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<sub-domain>.teamphoria.com/login`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには [、Teamphoria クライアント サポート チーム](https://www.teamphoria.com/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Teamphoria のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Teamphoria の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Teamphoria 企業サイトに管理者としてサインインします
2. 左側のツール バーの **[管理設定]** オプションに移動し、[構成] タブで **[シングル サインオン** ] を選択して SSO 構成ウィンドウを開きます。

    [Image: スクリーンショットは、シングル サインオンを選択できる [ADMIN SETTINGS](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者設定) を示しています。]
3. 右上隅にある **[ADD NEW IDENTITY PROVIDER]\(新しい ID プロバイダーの追加** \) オプションを選択して、SSO の設定を追加するためのフォームを開きます。

    [Image: [ADD NEW IDENTITY PROVIDER](新しい ID プロバイダーの追加) を選択できる場所を示すスクリーンショット。]
4. 以下の説明に従って、フィールドに詳細を入力します。

    [Image: スクリーンショットは、説明されている値を入力できるページを示しています。]

    ある。 **表示名**: 管理者ページにプラグインの表示名を入力します。

    b。 **ボタン名**: SSO を使用してログインするためのログイン ページに表示されるタブの名前。

    c. **証明書**: 先ほどダウンロードした証明書をメモ帳で開き、同じ内容をコピーしてボックスに貼り付けます。

    d. **エントリ ポイント**: 先ほどコピーした **ログイン URL を** 貼り付けます。

    え オプションを **ON** に切り替え、[保存] を選択 **します**。

#### Teamphoria テスト ユーザーの作成

Microsoft Entra ユーザーが Teamphoria にサインインできるようにするには、そのユーザーを Teamphoria にプロビジョニングする必要があります。 Teamphoria の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. Teamphoria 企業サイトに管理者としてサインインします。
2. 左側のツール バーの [管理**] タブ**で [**管理者**設定] を選択し、[**ユーザー**の選択] タブでユーザーの管理ページを開きます。

    [Image: 従業員の追加]
3. **[MANUAL INVITE]\(手動招待\)** オプションを選択します。

    [Image: [MANUAL INVITE](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/手動招待) オプションを示すスクリーンショット。]
4. このページで、次の操作を実行します。

    [Image: 名前とメール アドレスを入力できる [MANUAL USER INVITE](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/手動ユーザー招待) ページを示すスクリーンショット。]

    ある。 [EMAIL ADDRESS]\( **電子メール アドレス** \) ボックスに、B.Simon などのユーザーの **メール アドレス** を入力します。

    b。 **FIRST NAME** テキストボックスにユーザーの名前として**B** のように入力します。

    c. [ **姓]** ボックスに、ユーザーの姓 ( **Simon** など) を入力します。

    d. [ **INVITE 1 USER]\(1 ユーザーの招待**\) を選択します。 ユーザーをシステムに作成するには、そのユーザーが招待を受け入れる必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Teamphoria] タイルを選択すると、SSO を設定した Teamphoria に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamseer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TeamSeer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamseer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TeamSeer の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、TeamSeer と Microsoft Entra ID を統合する方法について説明します。 TeamSeer を Microsoft Entra ID と統合すると、次のことができます。

- TeamSeer にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TeamSeer に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

TeamSeer と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- TeamSeer でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- TeamSeer では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの TeamSeer の追加

Microsoft Entra ID への TeamSeer の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TeamSeer を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TeamSeer**」と入力します。
4. 結果のパネルから **[TeamSeer]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TeamSeer 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、TeamSeer に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TeamSeer の関連ユーザーとの間にリンク関係を確立する必要があります。

TeamSeer に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TeamSeer の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TeamSeer テストユーザーを作成** - B.Simon の TeamSeer における対応ユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TeamSeer**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.teamseer.com/<companyid>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[TeamSeer クライアント サポート チーム](https://pages.theaccessgroup.com/solutions_business-suite_absence-management_contact.html)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[TeamSeer のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TeamSeer SSO の構成

1. 別の Web ブラウザーのウィンドウで、TeamSeer 企業サイトに管理者としてサインインします。
2. **[HR Admin]** に移動します。

    [Image: TeamSeer ウィンドウから [H R Admin](H R 管理者) が選択された画面のスクリーンショット。]
3. **設定** を選択します。

    [Image: SSO 構成の設定を示すスクリーンショット。]
4. [ **SAML プロバイダーの詳細の設定] を選択します**。

    [Image: [Set up SAML provider details](SAML プロバイダーの詳細の設定) が選択された画面のスクリーンショット。]
5. SAML プロバイダーの詳細セクションで、次の手順に従います。

    [Image: SAML プロバイダーの詳細が表示された画面のスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    b。 base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして **[IdP Public Certificate](IdP パブリック証明書)** ボックスに貼り付けます。
6. SAML プロバイダー構成を完了するには、次の手順に従います。

    [Image: SAML プロバイダー構成を示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[Test Email Addresses]** に、テスト ユーザーの電子メール アドレスを入力します。

    b。 **[Issuer]** テキスト ボックスに、サービス プロバイダーの発行元 URL を入力します。

    c. **保存** を選択します。

#### TeamSeer テスト ユーザーの作成

Microsoft Entra ユーザーが TeamSeer にログインできるようにするには、ユーザーを ShiftPlanning にプロビジョニングする必要があります。 TeamSeer の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. **TeamSeer** 企業サイトに管理者としてサインインします。
2. **HR Admin**&gt;**Users** に移動し、[**ユーザーの新規作成ウィザードの実行**] を選択します。

    [Image: [H R Admin](H R 管理者) タブのスクリーンショット。ここで、実行するウィザードを選択できます。]
3. **[User Details]** セクションで、次の手順に従います。

    [Image: ユーザーの詳細を示すスクリーンショット。]

    ある。 プロビジョニングする有効な Microsoft Entra アカウントの**名**、**姓**、**ユーザー名 (メール アドレス)** を、対応するボックスに入力します。

    b。 [**次へ**] を選択します。
4. 画面の指示に従って新しいユーザーを追加し、[ **完了]** を選択します。

注

TeamSeer から提供されている他の TeamSeer ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる TeamSeer のサインオン URL にリダイレクトされます。
- TeamSeer のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TeamSeer] タイルを選択すると、このオプションは TeamSeer のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamslide-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TeamSlide を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamslide-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TeamSlide 間にシングル サインオンを構成する方法について学習します。

この記事では、TeamSlide と Microsoft Entra ID を統合する方法について説明します。 TeamSlide を Microsoft Entra ID と統合すると、次のことができます。

- TeamSlide にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TeamSlide に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な TeamSlide サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TeamSlide では、 **SP** によって開始される SSO がサポートされます。
- TeamSlide では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの TeamSlide の追加

Microsoft Entra ID への TeamSlide の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに TeamSlide を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「TeamSlide**」と入力します。
4. 結果パネルから **TeamSlide** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TeamSlide 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TeamSlide に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TeamSlide の関連ユーザーとの間にリンク関係を確立する必要があります。

TeamSlide で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TeamSlide SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TeamSlide テストユーザーの作成**—TeamSlide において、B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーをリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**TeamSlide**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

[Image: 基本的な SAML 構成を編集するスクリーンショット。]

1. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、URL を入力します。 `https://www.teamslide.io/AuthServices/`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://www.teamslide.io/AuthServices/Acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.teamslide.io/ChooseSso?domain=<CustomerDomain>`

    注

    サインオン URL は実際のものではありません。 実際のサインオン URL で値を更新します。 これらの値を取得するには [、TeamSlide クライアント サポート チーム](mailto:support@aploris.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
2. TeamSlide アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 既定の属性の一覧を示すスクリーンショット。]
3. その他に、TeamSlide アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 表示名 | ユーザー表示名 |
    | グループ | user.groups [すべて] |
4. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TeamSlide の SSO の構成

1. TeamSlide 企業サイトに管理者としてログインします。
2. [&gt;] タブに移動します。
3. [ **シングル サインオン設定** ] ページで、次の手順に従います。

    [Image: [構成設定] を示すスクリーンショット。]

    ある。 [ **エンティティ ID** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    b。 ** [Sign-On URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    c. [ **メタデータの場所** ] ボックスに、前にコピーした **アプリのフェデレーション メタデータ URL** の値を貼り付けます。

    d. [ **変更の保存] を選択します**。

#### TeamSlide のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを TeamSlide に作成します。 TeamSlide では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 TeamSlide にユーザーがまだ存在していない場合は、認証後に新規作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる TeamSlide のサインオン URL にリダイレクトされます。
- TeamSlide のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TeamSlide] タイルを選択すると、このオプションは TeamSlide のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamsticker-by-communitio-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TeamSticker by Communitio を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamsticker-by-communitio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TeamSticker by Communitio の間でシングル サインオンを構成する方法について説明します。

この記事では、TeamSticker by Communitio と Microsoft Entra ID を統合する方法について説明します。 TeamSticker by Communitio と Microsoft Entra ID を統合すると、次のことができます。

- TeamSticker by Communitio にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って TeamSticker by Communitio に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TeamSticker by Communitio のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TeamSticker by Communitio では、**SP** によって開始される SSO がサポートされます。
- TeamSticker by Communitio では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから TeamSticker by Communitio を追加する

Microsoft Entra ID への TeamSticker by Communitio の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに TeamSticker by Communitio を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TeamSticker by Communitio**」と入力します。
4. 結果のパネルから **[TeamSticker by Communitio]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TeamSticker by Communitio 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、TeamSticker by Communitio に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと TeamSticker by Communitio の関連ユーザーとの間にリンク関係を確立する必要があります。

TeamSticker by Communitio に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TeamSticker by Communitio の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TeamSticker by Communitio の B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせるテスト** - TeamSticker by Communitio で B.Simon に対応するユーザーを作成し、Microsoft Entra の表現にリンクする。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TeamSticker by Communitio**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.communitio.tech/auth/realms/<Customer_TeamName>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.communitio.net/team/<Customer_TeamName>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 この値を取得するには、[TeamSticker by Communitio クライアント サポート チーム](mailto:cs@communitio.net)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. TeamSticker by Communitio アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、TeamSticker by Communitio アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。該当するものを次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | テナントID | AD テナントID |
    | オブジェクトID | user.objectid (ユーザーのオブジェクトID) |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TeamSticker by Communitio の SSO の構成

**TeamSticker by Communitio** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [TeamSticker by Communitio サポート チーム](mailto:cs@communitio.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TeamSticker by Communitio のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TeamSticker by Communitio に作成します。 TeamSticker by Communitio では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 TeamSticker by Communitio にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる TeamSticker by Communitio のサインオン URL にリダイレクトされます。
- TeamSticker by Communitio のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TeamSticker by Communitio] タイルを選択すると、このオプションは TeamSticker by Communitio のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/teamviewer-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に TeamViewer を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamviewer-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-24
- Summary: Microsoft Entra ID から TeamViewer へのユーザー アカウントの自動プロビジョニングおよび自動プロビジョニング解除を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために TeamViewer と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [TeamViewer](https://www.teamviewer.com/buy-now/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- TeamViewer でユーザーを作成する
- アクセスが不要になった場合に TeamViewer のユーザーを削除する
- Microsoft Entra ID と TeamViewer の間でユーザー属性の同期を維持する。
- TeamViewer への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/teamviewer-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- TeamViewer の有効な [Tensor ライセンス](https://www.teamviewer.com/de/teamviewer-tensor/) 。
- [使用可能なシングル サインオン](https://community.teamviewer.com/English/kb/articles/110134-single-sign-on-for-microsoft-entra-id)構成の有効なカスタム識別子。

注

これには、Microsoft Entra Premium ライセンス サブスクリプションが必要です。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と TeamViewer の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように TeamViewer を構成する

1. [TeamViewer 管理コンソール](https://login.teamviewer.com)にログインします。 **[管理者設定]** に移動します。
2. [認証] セクションで、[ **アプリとトークン**] を選択します。
3. [プロファイル **設定**] を選択します。
4. [ **アプリまたはトークンの追加]** を選択します。
5. [ **スクリプト トークンの作成]** を選択します。
6. API トークンの名前を入力し、トークンに次のオプションを選択します。

#### アカウント管理

    - オンライン状態を表示する。
    - アカウント データを表示します。
    - メール アドレスを表示します。
    - ライセンスを表示します。

#### ユーザー管理

    - ユーザーを作成します。
    - ユーザーを編集します。
    - ユーザーを表示します。

#### ユーザー グループ

    - ユーザー グループを作成します。
    - ユーザー グループを削除します。
    - ユーザー グループを編集します。
    - ユーザー グループを読み取ります。
7. [保存] を選択してスクリプト トークンを作成します。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから TeamViewer を追加する

Microsoft Entra アプリケーション ギャラリーから TeamViewer を追加して、TeamViewer へのプロビジョニングの管理を開始します。 SSO のために TeamViewer を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: TeamViewer への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てに基づいて TestApp 内のユーザーを作成、更新、無効にするよう、Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で TeamViewer に対する自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **TeamViewer** を選択します。

    [Image: アプリケーションの一覧の TeamViewer のリンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、TeamViewer テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が TeamViewer に接続できることを確認します。 接続に失敗した場合は、TeamViewer アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から TeamViewer に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で TeamViewer のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、TeamViewer API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | displayName | 糸 |
    | 活動中 | ブール値 |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->
