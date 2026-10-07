# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 26)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 68

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vtiger-crm-saml-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Vtiger CRM (SAML) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vtiger-crm-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Vtiger CRM (SAML) の間にシングル サインオンを構成する方法について学習します。

この記事では、Vtiger CRM (SAML) と Microsoft Entra ID を統合する方法について説明します。 Vtiger CRM (SAML) と Microsoft Entra ID を統合すると、次のことができます。

- Vtiger CRM (SAML) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Vtiger CRM (SAML) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Vtiger CRM (SAML) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Vtiger CRM (SAML) では、 **SP** によって開始される SSO がサポートされます。
- Vtiger CRM (SAML) では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Vtiger CRM (SAML) の追加

Microsoft Entra ID への Vtiger CRM (SAML) の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Vtiger CRM (SAML) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Vtiger CRM (SAML)」**と入力します。
4. 結果パネルから **Vtiger CRM (SAML)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Vtiger CRM (SAML) 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Vtiger CRM (SAML) に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Vtiger CRM (SAML) の関連ユーザーとの間にリンク関係を確立する必要があります。

Vtiger CRM (SAML) で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Vtiger CRM (SAML) SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Vtiger CRM (SAML) テストユーザーの作成** - Microsoft Entra 上のユーザー B.Simon にリンクする、Vtiger CRM (SAML) での B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Vtiger CRM (SAML)** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** ページで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_INSTANCE>.od1.vtiger.com/sso/saml?acs`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン用URL |
    | --- |
    | `https://<CUSTOMER_INSTANCE>.od1.vtiger.com` |
    | `https://<CUSTOMER_INSTANCE>.od2.vtiger.com` |
    | `https://<CUSTOMER_INSTANCE>.od1.vtiger.ws` |
    |  |

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [Vtiger CRM (SAML) クライアント サポート チーム](mailto:support@vtiger.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Vtiger CRM (SAML) のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Vtiger CRM (SAML) の SSO の構成

**Vtiger CRM (SAML)** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Vtiger CRM (SAML) サポート チーム](mailto:support@vtiger.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Vtiger CRM (SAML) テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Vtiger CRM (SAML) に作成します。 Vtiger CRM (SAML) では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Vtiger CRM (SAML) にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Vtiger CRM (SAML) のサインオン URL にリダイレクトされます。
- Vtiger CRM (SAML) のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Vtiger CRM (SAML) タイルを選択すると、このオプションは Vtiger CRM (SAML) のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vxmaintain-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に vxMaintain を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vxmaintain-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と vxMaintain の間にシングル サインオンを構成する方法について説明します。

この記事では、vxMaintain と Microsoft Entra ID を統合する方法について説明します。 vxMaintain と Microsoft Entra ID を統合すると、次のことができます。

- vxMaintain にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って vxMaintain に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

Microsoft Entra と vxMaintain の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- vxMaintain でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- vxMaintain では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの vxMaintain の追加

Microsoft Entra ID への vxMaintain の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに vxMaintain を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「vxMaintain**」と入力します。
4. 結果パネルから **vxMaintain** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### vxMaintain 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、vxMaintain に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと vxMaintain の関連ユーザーとの間にリンク関係を確立する必要があります。

vxMaintain に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **vxMaintain の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **vxMaintain テストユーザーの作成** - B.Simon に対応するユーザーを vxMaintain で作成し、そのユーザーを Microsoft Entra でのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**vxMaintain**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.verisae.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.verisae.com/DataNett/action/ssoConsume/mobile?_log=true`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [vxMaintain クライアント サポート チーム](https://www.hubspot.com/company/contact) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **vxMaintain のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: スクリーンショットは適切なURLへの構成のコピー方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### vxMaintain SSO の構成

**vxMaintain** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [vxMaintain サポート チーム](https://www.hubspot.com/company/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### vxMaintain のテスト ユーザーの作成

このセクションでは、vxMaintain で Britta Simon というユーザーを作成します。 [vxMaintain サポート チーム](https://www.hubspot.com/company/contact)と協力して、vxMaintain プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した vxMaintain に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで vxMaintain タイルを選択すると、SSO を設定した vxMaintain に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vyond-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Vyond を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vyond-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Vyond の間でシングル サインオンを構成する方法について説明します。

この記事では、Vyond と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Vyond を統合すると、次のことができます。

- Microsoft Entra ID で Vyond へのアクセスを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Vyond に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Vyond でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Vyond では、**SP と IDP** によって開始される SSO がサポートされています。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからVyondを追加

Microsoft Entra ID への Vyond の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Vyond を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Vyond**」と入力します。
4. 結果のパネルから **Vyond** を選択し、そしてアプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Vyond の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Vyond に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Vyond の関連ユーザーとの間にリンク関係を確立する必要があります。

Vyond に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Vyond SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Vyond テストユーザーを作成する - B.Simon に対応するユーザーを Vyond で作成し、それを Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Vyond]**&gt;**[シングル サインオン]** に移動します。
3. [**シングル サインオン方法の選択]** ページで、[SAML 選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://app.vyond.com/v2/login` を入力します。
7. **保存** を選択します。
8. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Vyond** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Vyond SSO の構成

Vyond **側** シングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を Vyond サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Vyond テスト ユーザーの作成

このセクションでは、Vyond で Britta Simon というユーザーを作成します。 Vyond サポート チーム  と連携して、Vyond プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Vyond のサインオン URL にリダイレクトされます。
- Vyond のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Vyond に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Vyond] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Vyond に自動的にサインインされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/walkme-saml-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に WalkMe SAML2.0 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/walkme-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WalkMe SAML2.0 間のシングル サインオンを構成する方法について説明します。

この記事では、WalkMe SAML2.0 と Microsoft Entra ID を統合する方法について説明します。 WalkMe SAML2.0 を Microsoft Entra ID と統合すると、次のことが可能になります。

- WalkMe SAML2.0 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで WalkMe SAML2.0 に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- WalkMe SAML2.0 でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- WalkMe SAML2.0 では、**IDP** によって開始される SSO がサポートされます。
- WalkMe SAML2.0 では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの WalkMe SAML2.0 の追加

Microsoft Entra ID への WalkMe SAML2.0 の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に WalkMe SAML2.0 を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**WalkMe SAML2.0**」と入力します。
4. 結果のパネルから **[WalkMe SAML2.0]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### WalkMe SAML2.0 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、WalkMe SAML2.0 と一緒に Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと WalkMe SAML2.0 の関連ユーザーとの間にリンク関係を確立する必要があります。

WalkMe SAML2.0 に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **WalkMe SAML2.0 の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **WalkMe SAML2.0 テストユーザーを作成する** - WalkMe SAML2.0 に B.Simon の対となるユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**WalkMe SAML2.0**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.walkme.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.walkme.com/ic/idp/p/saml/callback`

    c. [ **リレー状態** ] ボックスに、値を入力します。 `{ "loginType": "azureSAMLApp"}`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[WalkMe SAML2.0 クライアント サポート チーム](mailto:support@walkme.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[WalkMe SAML2.0 の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### WalkMe SAML2.0 SSO の構成

**WalkMe SAML2.0** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [WalkMe SAML2.0 サポート チーム](mailto:support@walkme.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### WalkMe SAML2.0 テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを WalkMe SAML2.0 に作成します。 WalkMe SAML2.0 では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 WalkMe SAML2.0 にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した WalkMe SAML2.0 に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [WalkMe SAML2.0] タイルを選択すると、SSO を設定した WalkMe SAML2.0 に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wan-sign-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの WAN-Sign を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wan-sign-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WAN-Sign 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、WAN-Sign と Microsoft Entra ID を統合する方法について説明します。 WAN-Sign を Microsoft Entra ID と統合すると、以下のことが可能になります。

- WAN-Sign にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して WAN-Sign に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- WAN-Sign でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- WAN-Sign では、**SP Initiated SSO** と **IDP Initiated SSO** の両方がサポートされます。

### ギャラリーからの WAN-Sign の追加

Microsoft Entra ID への WAN-Sign の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに WAN-Sign を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**WAN-Sign**」と入力します。
4. 結果のパネルから **[WAN-Sign]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### WAN-Sign 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、WAN-Sign に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと WAN-Sign の関連ユーザーとの間にリンク関係を確立する必要があります。

WAN-Sign に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **WAN-Sign の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **WAN-Signのテストユーザーを作成** - WAN-SignでB.Simonに対応するユーザーをMicrosoft Entra上でリンクするように設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**WAN-Sign**&gt;**シングルサインオン**にブラウズして移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://service10.wanbishi.ne.jp/saml/metadata/azuread/<CustomerID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://service10.wanbishi.ne.jp/saml/azuread/<CustomerID>`
6. SP 開始モードでアプリケーションを構成する場合 **は、[追加の URL の設定] を** 選択し、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://service10.wanbishi.ne.jp/saml/login/azuread/<CustomerID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[WAN-Sign クライアント サポート チーム](mailto:wansign-help@wanbishi.ne.jp)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[WAN-Sign のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### WAN-Sign の SSO の構成

**WAN-Sign** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [WAN-Sign サポート チーム](mailto:wansign-help@wanbishi.ne.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### WAN-Sign のテスト ユーザーの作成

このセクションでは、WAN-Sign で Britta Simon というユーザーを作成します。 [WAN-Sign サポート チーム](mailto:wansign-help@wanbishi.ne.jp)と連携し、WAN-Sign プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる WAN-Sign サインオン URL にリダイレクトされます。
- WAN-Sign のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した WAN-Sign に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [WAN-Sign] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した WAN-Sign に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wandera-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Wandera RADAR Admin を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wandera-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wandera RADAR Admin の間でシングル サインオンを構成する方法について説明します。

この記事では、Wandera RADAR Admin と Microsoft Entra ID を統合する方法について説明します。 Wandera RADAR Admin と Microsoft Entra ID を統合すると、次のことができます:

- Wandera RADAR Admin にアクセスできる Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Wandera RADAR Admin に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Wandera RADAR Admin でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Wandera RADAR Admin では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Wandera RADAR Admin の追加

Microsoft Entra ID への Wandera RADAR Admin の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Wandera RADAR Admin を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Wandera RADAR Admin**」と入力します。
4. 結果パネルから **[Wandera RADAR Admin]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Wandera RADAR Admin の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Wandera RADAR Admin に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Wandera RADAR Admin の関連ユーザーとの間にリンク関係を確立する必要があります。

Wandera RADAR Admin に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Wandera RADAR Admin SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Wandera RADAR Admin のテストユーザーを作成し、Microsoft Entra のユーザー表現としての B.Simon に対応するものをリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Wandera RADAR Admin** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://radar.wandera.com/saml/acs/<TENANT_ID>`

    注

    これは実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、[Wandera RADAR Admin クライアント サポート チーム](https://www.wandera.com/about-wandera/contact/#supportsection)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。 上記の URL の &lt;tenant id&gt; 部分を、Wandera アカウント内の **[Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)**&gt;**[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)**&gt;**[Single Sign-On](シングル サインオン)** ページに表示されているテナント ID で慎重に置き換えます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **SAML を使用した単一 Sign-On の設定** ] ページで、[ **SAML 署名証明書** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 署名オプション]

    1. **[証明書オプション]** で **[SAML 応答とアサーションへの署名]** を選択します。
    2. **[署名アルゴリズム]** で **[SHA-256]** を選択します。
8. **[Wandera RADAR Admin のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Wandera RADAR Admin SSO の構成

1. 別の Web ブラウザー ウィンドウで、Wandera RADAR Admin 企業サイトに管理者としてサインインします
2. ページの右上隅にある **[Settings**&gt;**Administration**&gt;**Single Sign-On** ] を選択し、[ **Enable SAML 2.0]\(SAML 2.0 を有効にする\)** オプションをオンにして次の手順を実行します。

    [Image: Wandera RADAR Admin の構成]

    ある。 **必要なフィールドを選択するか、手動で入力します**。

    b。 **[IdP EntityId]** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    c. フェデレーション メタデータ XML をメモ帳で開き、その内容をコピーして **[IdP Public X.509 Certificate](IdP パブリック X.509 証明書)** ボックスに貼り付けます。

    d. **保存** を選択します。

#### Wandera RADAR Admin のテスト ユーザーの作成

このセクションでは、Wandera RADAR Admin で B.Simon というユーザーを作成します。[Wandera RADAR Admin サポート チーム](https://www.wandera.com/about-wandera/contact/#supportsection)と連携して、Wandera RADAR Admin プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Wandera RADAR 管理者に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Wandera RADAR Admin] タイルを選択すると、SSO を設定した Wandera RADAR Admin に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/watch-by-colors-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Watch by Colors を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/watch-by-colors-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Watch by Colors の間でシングル サインオンを構成する方法について説明します。

この記事では、Watch by Colors と Microsoft Entra ID を統合する方法について説明します。 Watch by Colors を Microsoft Entra ID と統合すると、次のことができます。

- Watch by Colors にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Watch by Colors に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Watch by Colors でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Watch by Colors では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーから Watch by Colors を追加する

Microsoft Entra ID への Watch by Colors の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに Watch by Colors を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Watch by Colors**」と入力します。
4. 結果パネルで **[Watch by Colors]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Watch by Colors 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Watch by Colors に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Watch by Colors の関連ユーザーとの間にリンク関係を確立する必要があります。

Watch by Colors に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Watch by Colors SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Watch by Colors のテスト ユーザーの作成 - Watch by Colors** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Watch by Colors**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.colorscorporation.com/login`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Watch by Colors SSO の構成

1. 別の Web ブラウザー ウィンドウで、Watch by Colors 企業サイトに管理者としてサインインします
2. ページの右上隅にある **profile**&gt;**Account Settings**&gt;**SSO (シングル サインオン)** を選択します。

    [Image: SSO が無効になっている [Account Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント設定) ページを示すスクリーンショット。]
3. **[SSO (Single Sign On)](SSO (シングル サインオン))** ページで、次の手順を実行します。

    [Image: [SAML Setup](SAML のセットアップ) タブを示すスクリーンショット。ここで、SAML を有効にできます。]

    ある。 **[Enable SAML](SAML を有効にする)** を **[ON](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/オン)** に切り替えます。

    b。 **[URL]** テキスト ボックスに、**フェデレーション メタデータ URL** を貼り付けます。

    c. [ **インポート]** を選択すると、次のフィールドがページに自動的に入力されます。

    d. **保存** を選択します。

#### Watch by Colors のテスト ユーザーの作成

Microsoft Entra ユーザーが Watch by Colors にサインインできるようにするには、そのユーザーを Watch by Colors にプロビジョニングする必要があります。 Watch by Colors では、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. セキュリティ管理者として Watch by Colors にサインインします。
2. ページの右上隅にある **profile**&gt;**Users**&gt;**Add User** を選択します。

    [Image: [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) ページを示すスクリーンショット。]
3. **[User Details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの詳細)** ページで、次の手順を実行します。

    [Image: [User Details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの詳細) ページを示すスクリーンショット。ここで説明されている値を入力できます。]

    ある。 [ **名** ] テキスト ボックスに、ユーザーの名を **B** などと入力します。

    b。 **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (例: **Simon**)。

    c. [ **電子メール** ] テキスト ボックスに、ユーザーの電子メール ( `B.Simon@contoso.com`など) を入力します。

    d. **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** ボックスにパスワードを入力します。

    え 組織に従って**アカウントのアクセス許可**を選択します。

    f. **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Watch by Colors のサインオン URL にリダイレクトされます。
- Watch by Colors のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Watch by Colors に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Watch by Colors] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Watch by Colors に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wats-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に WATS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wats-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから WATS にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために WATS とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 Microsoft Entra ID を構成すると、Microsoft Entra プロビジョニング サービスを使用してユーザーが[WATS](https://wats.com)に自動的にプロビジョニングおよび削除されます。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- WATS でユーザーを作成する。
- アクセスが不要になったら、WATS のユーザーを削除します。
- Microsoft Entra IDと WATS の間でユーザー属性の同期を維持します。
- WATS への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Admin アクセス許可がある WATS のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとWATSの間でデータをマッピングする内容を決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように WATS を構成する

Microsoft Entra IDによるプロビジョニングに必要な要件を設定するには、[WATS プロビジョニング](https://support.virinco.com/hc/en-us/articles/7978299009948-WATS-Provisioning-SCIM-)に関する記事を参照してください。

### 手順 3: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 4: WATS に対する自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で WATS の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[WATS]** を選択します。

    [Image: アプリケーション リストの WATS リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、WATS テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが WATS に接続できることを確認します。 接続に失敗した場合は、WATS アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから WATS に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で WATS のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が WATS API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | WATS で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | 役割[主要 eq "True"].値 | 糸 |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### ステップ 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wayleadr-tutorial"} -->
## Microsoft Entra ID で Wayleadr for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wayleadr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wayleadr 間にシングル サインオンを構成する方法について説明します。

この記事では、Wayleadr と Microsoft Entra ID を統合する方法について説明します。 Wayleadr は、駐車、EV 充電器の回転、アクセス制御を管理するための世界初のソフトウェアです。 建物への到着を容易にします。 Wayleadr と Microsoft Entra ID を統合すると、次のことができます:

- Wayleadr にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Wayleadr に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Wayleadr 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Wayleadr では、**SP** と **IDP** Initiated の両方のシングル サインオンがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Wayleadr と統合するには、次のものが必要です:

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Wayleadr でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Wayleadr アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Wayleadr を追加する

Microsoft Entra アプリケーション ギャラリーから Wayleadr を追加して、Wayleadr でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Wayleadr**&gt;**シングル サインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、URL を入力します。 `https://app.wayleadr.com`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.wayleadr.com/users/auth/saml_<CustomerName>/callback`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://app.wayleadr.com/users/sign_in` |
    | `https://app.wayleadr.com/` |
    | `https://app.wayleadr.com/users/sign_in_sso` |

    注

    この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、[Wayleadr クライアント サポート チーム](mailto:support@wayleadr.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: サムプリントの値をコピーする方法を示すスクリーンショット。]

### Wayleadr SSO を構成する

**Wayleadr** 側でシングル サインオンを構成するには、ダウンロードした**拇印の値**と、アプリケーションの構成からコピーした適切な URL を [Wayleadr サポート チーム](mailto:support@wayleadr.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Wayleadr テスト ユーザーを作成する

このセクションでは、Wayleadr で Britta Simon というユーザーを作成します。 [Wayleadr サポート チーム](mailto:support@wayleadr.com)と連携して、Wayleadr プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Wayleadr のサインオン URL にリダイレクトされます。
- Wayleadr のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Wayleadr に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Wayleadr] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Wayleadr に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/waywedo-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Way We Do を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/waywedo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Way We Do 間にシングル サインオンを構成する方法について学習します。

この記事では、Way We Do と Microsoft Entra ID を統合する方法について説明します。 Way We Do を Microsoft Entra ID と統合すると、次のことができます。

- Way We Do にアクセスMicrosoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Way We Do に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Way We Do でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Way We Do では、**SP** によって開始される SSO がサポートされます
- Way We Do では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Way We Do の追加

Microsoft Entra ID への Way We Do の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Way We Do を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Way We Do**」と入力します。
4. 結果ウィンドウで **[Way We Do]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Way We Do 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Way We Do に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Way We Do の関連ユーザーとの間にリンク関係を確立する必要があります。

Way We Do に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Way We Do SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Way We Do テストユーザーを作成する** - Way We Do で B.Simon の対応ユーザーを作成し、Microsoft Entra のユーザー表示に関連付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Way We Do** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.waywedo.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.waywedo.com/Authentication/ExternalSignIn`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 この値を取得するには、[Way We Do クライアント サポート チーム](mailto:support@waywedo.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Way We Do のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Way We Do SSO の構成

1. 別の Web ブラウザー ウィンドウで、Way We Do 企業サイトに管理者としてサインインします
2. Way We Do のページの右上隅にある **ユーザー アイコン** を選択し、ドロップダウン メニューで **[アカウント** ] を選択します。

    [Image: Way We Do アカウント]
3. **メニュー アイコン**を選択してプッシュ ナビゲーション メニューを開き、[**シングル サインオン**] を選択します。

    [Image: Way We Do シングル]
4. **[Single sign-on setup](シングル サイン オンの設定)** ページで、次の手順を行います。

    [Image: Way We Do 保存]

    1. [ **シングル サインオンを有効にする** ] トグルを [ **はい** ] に選択して、シングル サインオンを有効にします。
    2. **[Single sign-on name](シングル サイン オン名)** テキストボックスに、自分の名前を入力します。
    3. **[Entity ID] (エンティティ ID)** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。
    4. **[SAML SSO URL**] ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。
    5. 証明書の横にある **選択** ボタンを選択して **、証明書**をアップロードします。
    6. **オプション設定** -

        - Enable Passwords (パスワードを有効にする) - このオプションを有効にすると、ユーザーがシングル サインオンのみを使用できるように通常のパスワードが Way We Do に対して機能します。
        - 自動プロビジョニングを有効にする - これが有効になっている場合、サインオンに使用される電子メール アドレスは、Way We Do のユーザーの一覧と自動的に比較されます。 メール アドレスが Way We Do のアクティブ ユーザーと一致しない場合は、サインインするユーザーの新しいユーザー アカウントが自動的に追加され、不足している情報が要求されます。

            注

            シングル サインオンによって追加されたユーザーは、一般ユーザーとして追加され、システムにロールが割り当てられません。 管理者は編集者または管理者としてセキュリティ ロールにアクセスし、それを変更することができると共に、1 つまたは複数の Org Chart ロールを割り当てることもできます。
    7. [ **保存] を** 選択して設定を保持します。

#### Way We Do テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Way We Do に作成します。 Way We Do では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Way We Do にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Way We Do クライアント サポート チーム](mailto:support@waywedo.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Way We Do サインオン URL にリダイレクトされます。
- Way We Do のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Way We Do] タイルを選択すると、このオプションは Way We Do のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wdesk-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Wdesk を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wdesk-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wdesk 間にシングル サインオンを構成する方法について説明します。

この記事では、Wdesk と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Wdesk を統合すると、次のことができます:

- Wdesk にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Wdesk に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Wdesk でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Wdesk では、**SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Wdesk の追加

Microsoft Entra ID への Wdesk の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Wdesk を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Wdesk**」と入力します。
4. 結果のパネルから **[Wdesk]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Wdesk 用の Microsoft Entra SSO の構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Wdesk で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Wdesk 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Wdesk に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Wdesk の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Wdesk テストユーザーを作成** - Microsoft Entra のユーザー B.Simon にリンクされる Wdesk 内の対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Wdesk**&gt;**シングルサインオン**を操作します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.wdesk.com/auth/saml/sp/metadata/<instancename>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.wdesk.com/auth/saml/sp/consumer/<instancename>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.wdesk.com/auth/login/saml/<instancename>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値は、SSO を構成するときに WDesk ポータルから得られます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Wdesk のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Wdesk の SSO の構成

1. 別の Web ブラウザー ウィンドウで、セキュリティ管理者として Wdesk にサインインします。
2. 左下の [ **管理者** ] を選択し、[ **アカウント管理者**] を選択します。

    [Image: [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) メニューから [Account Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント管理者) が選択されていることを示すスクリーンショット。]
3. Wdesk 管理ツールで、 **[Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ)** に移動した後、 **[SAML]**&gt;**[SAML Settings](SAML の設定)** の順に移動します。

    [Image: [SAML] タブから [SAML Settings](SAML の設定) が選択されていることを示すスクリーンショット。]
4. **[SAML User ID Settings](SAML ユーザー ID 設定)** で、 **[SAML User ID is Wdesk Username](SAML ユーザー ID は Wdesk ユーザー名)** をオンにします。

    [Image: [SAML User I D Settings](SAML ユーザー I D 設定) を示すスクリーンショット。ここで、[SAML User I D is W desk Username](SAML ユーザー I D は W desk ユーザー名) を選択できます。]
5. **[General Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/一般設定)** で、 **[Enable SAML Single Sign On](SAML のシングル サインオンを有効にする)** をオンにします。

    [Image: [Edit SAML Settings](SAML 設定の編集) を示すスクリーンショット。ここで、[Enable SAML Single Sign-On](SAML のシングル サインオンを有効にする) を選択できます。]
6. **[Service Provider Details](サービス プロバイダーの詳細)** で、以下の手順を実行します。

    [Image: [Service Provider Details](サービス プロバイダーの詳細) を示すスクリーンショット。ここで、説明されている値を入力できます。]

    1. **[Login URL](ログイン URL)** をコピーし、Azure Portal の **[サインオン URL]** ボックスに貼り付けます。
    2. **[Metadata Url](メタデータ URL)** をコピーし、Azure Portal の **[識別子]** ボックスに貼り付けます。
    3. **[Consumer url](コンシューマー URL)** をコピーし、Azure Portal の **[応答 URL]** ボックスに貼り付けます。
    4. [Azure portal で **保存] を** 選択して変更を保存します。
7. [ **IdP 設定の構成] を** 選択して、[ **IdP 設定の編集]** ダイアログを開きます。 [ **ファイルの選択] を選択** して、Azure portal から保存した **Metadata.xml** ファイルを見つけてアップロードします。

    [Image: [Edit I d P Settings](I d P の設定の編集) を示すスクリーンショット。ここで、メタデータをアップロードできます。]
8. **[変更の保存]** を選択します。

    [Image: [Save changes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/変更を保存) ボタンを示すスクリーンショット。]

#### Wdesk テスト ユーザーの作成

Microsoft Entra ユーザーが Wdesk にサインインできるようにするには、Wdesk にプロビジョニングする必要があります。 Wdesk では、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. セキュリティ管理者として Wdesk にサインインします。
2. **[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者)**&gt;**[Account Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント管理者)** の順に移動します。

    [Image: [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) メニューから [Account Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント管理者) が選択されていることを示すスクリーンショット。]
3. **人物** で **[メンバー]** を選択します。
4. [ **メンバーの追加** ] を選択して [ **メンバーの追加** ] ダイアログ ボックスを開きます。

    [Image: [Members](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバー) タブを示すスクリーンショット。ここで、[Add Member](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバーの追加) を選択できます。]
5. **[ユーザー**] テキスト ボックスに、b.simon@contoso.comなどのユーザーのユーザー名を入力し、[**続行**] ボタンを選択します。

    [Image: [Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ダイアログ ボックスを示すスクリーンショット。ここで、ユーザーを入力できます。]
6. 次のように、詳細を入力します。

    [Image: [Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ダイアログ ボックスを示すスクリーンショット。ここで、ユーザーの基本情報を追加できます。]

    ある。 **[E-mail](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** ボックスに、ユーザーのメール アドレスを入力します (例: b.simon@contoso.com)。

    b。 [ **名** ] テキスト ボックスに、ユーザーの名を **B** などと入力します。

    c. **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (例: **Simon**)。
7. [ **メンバーの保存]** ボタンを選択します。

    [Image: [Save Member](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバーの保存) ボタンを含む [Send welcome email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ウェルカム電子メールの送信) を示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Wdesk のサインオン URL にリダイレクトされます。
- Wdesk のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Wdesk に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Wdesk] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Wdesk に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/web-cargo-air-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Web Cargo Air を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/web-cargo-air-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Web Cargo Air にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Web Cargo Air と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーを [Web Cargo Air](https://www.webcargonet.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Web Cargo Air でユーザーを作成します。
- アクセスが不要になった場合は、Web Cargo Air のユーザーを削除します。
- Microsoft Entra ID と Web Cargo Air の間でユーザー属性の同期を維持します。
- Web Cargo Air への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/web-cargo-air-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Web Cargo Air のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- [Microsoft Entra ID と Web Cargo Air の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra アプリケーション ギャラリーから Web Cargo Air を追加する

Microsoft Entra アプリケーション ギャラリーから Web Cargo Air を追加して、Web Cargo Air へのプロビジョニングの管理を開始します。 SSO 用に Web Cargo Air を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 3: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 4: Web Cargo Air への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Web Cargo Air のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Web Cargo Air の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Web Cargo Air**] を選択します。

    [Image: アプリケーションの一覧の [Web Cargo Air] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Web Cargo Air テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Web Cargo Air に接続できることを確認します。 接続に失敗した場合は、Web Cargo Air アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Web Cargo Air に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Web Cargo Air のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Web Cargo Air API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | ウェブカーゴエアによる必須条件 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | エクスターナルID | 糸 |  | ✓ |
    | 役割[主要 eq "True"].値 | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/web-cargo-air-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Web Cargo Air を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/web-cargo-air-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Web Cargo Air の間にシングル サインオンを構成する方法について説明します。

この記事では、Web Cargo Air と Microsoft Entra ID を統合する方法について説明します。 Web Cargo Air を Microsoft Entra ID と統合すると、次のことができます:

- Web Cargo Air にアクセスできるユーザー Microsoft Entra ID を制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Web Cargo Air に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Web Cargo Air でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Web Cargo Air では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの Web Cargo Air の追加

Microsoft Entra ID への Web Cargo Air の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに Web Cargo Air を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Web Cargo Air**」と入力します。
4. 結果パネルから **[Web Cargo Air]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Web Cargo Air 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Web Cargo Air に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Web Cargo Air の関連ユーザーとの間にリンク関係を確立する必要があります。

Web Cargo Air に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Web Cargo Air の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Web Cargo Air のテスト ユーザーを作成し**、Microsoft Entra における B.Simon に対応するユーザーを Web Cargo Air でリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**Web Cargo Air**&gt;**シングルサインオン**に進みます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.webcargonet.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.webcargonet.com/saml-sso`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.webcargonet.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Web Cargo Air クライアント サポート チーム](mailto:support@webcargonet.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Web Cargo Air のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Web Cargo Air SSO の構成

**Web Cargo Air** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Web Cargo Air サポート チーム](mailto:support@webcargonet.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Web Cargo Air のテスト ユーザーの作成

このセクションでは、Web Cargo Air で Britta Simon というユーザーを作成します。 [Web Cargo Air サポート チーム](mailto:support@webcargonet.com)と連携して、Web Cargo Air プラットフォームにそのユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Web Cargo Air のサインオン URL にリダイレクトされます。
- Web Cargo Air のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Web Cargo Air] タイルを選択すると、このオプションは Web Cargo Air のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/webcargo-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Webcargo を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/webcargo-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Webcargo 間にシングル サインオンを構成する方法について学習します。

この記事では、Webcargo と Microsoft Entra ID を統合する方法について説明します。 Webcargo を Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra ID で、誰が Webcargo にアクセスできるかを管理する。
- ユーザーが自分の Microsoft Entra アカウントを使って Webcargo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Webcargo は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Webcargo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Webcargo では、**SP および IDP による SSO** の開始がサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Webcargo の追加

Microsoft Entra ID への Webcargo の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Webcargo を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を開きます。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Webcargo**」と入力します。
4. 結果パネルから **Webcargo** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Webcargo 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Webcargo に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Webcargo の関連ユーザーとの間にリンク関係を確立する必要があります。

Webcargo に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Webcargo の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Webcargo テストユーザーの作成 - Microsoft Entra のユーザー表現とリンクされた B.Simon に対応する Webcargo のユーザーを作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Webcargo**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.webcargo.net/sso/azure/account-id/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.webcargo.net/sso/azure/account-id/<ID>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Webcargo クライアント サポート チーム](mailto:tickets@webcargo.uservoice.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Webcargo のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Webcargo の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Webcargo 企業サイトに管理者としてサインインします
2. 左側のナビゲーションで **[チーム** ] を選択し、[ **SSO idP** ] タブを選択し、 **Microsoft Azure** を有効にします。

    [Image: 「シングル Sign-On 設定」アイコンを構成する]
3. **[Azure 構成**] セクションの [ログイン URL] ボックスに、コピーした**ログイン URL** の値を貼り付け、[**ファイルの選択**] を選択して、ダウンロードした**証明書 (Base64)** ファイルをアップロードします。

    [Image: シングル サインオンの構成 (ファイルの選択)]
4. **[保存] を選択します**。

#### Webcargo のテスト ユーザーの作成

このセクションでは、Webcargo で Britta Simon というユーザーを作成します。 [Webcargo サポート チーム](mailto:tickets@webcargo.uservoice.com)と協力して、Webcargo プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Webcargo のサインオン URL にリダイレクトされます。
- Webcargo のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Webcargo に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Webcargo] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Webcargo に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/webce-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に WebCE を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/webce-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WebCE 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、WebCE と Microsoft Entra ID を統合する方法について説明します。 WebCE では、さまざまな専門的なライセンスと指定のための自己学習オンライン継続教育と事前ライセンス トレーニング コースを提供しています。 WebCE を Microsoft Entra ID と統合すると、次のことができます。

- WebCE にアクセスできるユーザー Microsoft Entra ID を制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して WebCE に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で WebCE 向けの Azure AD のシングル サインオンを構成してテストします。 WebCE は、**SP** よって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングのみをサポートしています。

### Prerequisites

Microsoft Entra ID を WebCE と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な WebCE のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから WebCE アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから WebCE を追加する

Microsoft Entra アプリケーション ギャラリーから WebCE を追加して、WebCE でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**WebCE**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.webce.com`

    b. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.webce.com/<RootPortalFolder>/login/saml20`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.webce.com/<RootPortalFolder>/login`

    Note

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[WebCE クライアント サポート チーム](mailto:corporatesales@webce.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書を編集する方法を示すスクリーンショット。]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: サムプリントの値をコピーする方法を示すスクリーンショット。]

### WebCE SSO の構成

**WebCE** 側でシングル サインオンを構成するには、**サムプリントの値**とアプリケーションの構成からコピーした適切な URL を [WebCE サポート チーム](mailto:corporatesales@webce.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### WebCE テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを WebCE に作成します。 WebCE では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 WebCE にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる WebCE サインオン URL にリダイレクトされます。
- WebCE のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [WebCE] タイルを選択すると、このオプションは WebCE のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/webroot-security-awareness-training-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Webroot Security Awareness Training を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/webroot-security-awareness-training-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Webroot Security Awareness Training にユーザー アカウントを自動的にプロビジョニング/プロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Webroot Security Awareness Training と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Webroot Security Awareness Training](https://www.webroot.com/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Webroot Security Awareness Training のユーザーを作成する
- アクセスが不要になった場合に Webroot Security Awareness Training のユーザーを削除する
- Microsoft Entra ID と Webroot Security Awareness Training の間のユーザー属性の同期を維持する。
- Webroot Security Awareness Training のグループとグループ メンバーシップをプロビジョニングする
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 少なくとも 1 つのサイトで有効になっている Webroot Security Awareness Training のマネージド サービス プロバイダー コンソール。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Webroot Security Awareness Training の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定する。

### 手順 2: Microsoft Entra ID でのプロビジョニングをサポートするように Webroot Security Awareness Training を構成する

#### シークレット トークンを取得する

サイトを Microsoft Entra ID に接続するには、Webroot 管理コンソールでそのサイトの **シークレット トークン** を取得する必要があります。

1. [Webroot 管理コンソール](https://identity.webrootanywhere.com/v1/Account/login#tab_customers)にサインインします。
2. [ **サイト** ] タブで、Microsoft Entra ID で接続するサイトの [Security Awareness Training] 列の歯車アイコンを選択します。

    [Image: 歯車アイコン]
3. ボタンを選択して **Microsoft Entra 統合を構成**します。

    [Image: Microsoft Entra 統合の構成]
4. **[シークレット トークン]** をコピーして保存します。 この値は、Webroot Security Awareness Training アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。
5. **完了**を選択します。

    [Image: シークレット トークンをコピーする]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Webroot Security Awareness Training を追加する

Webroot Security Awareness Training へのプロビジョニングの管理を開始するには、Microsoft Entra アプリケーション ギャラリーから Webroot Security Awareness Training を追加します。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Webroot Security Awareness Training への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Webroot Security Awareness Training の自動ユーザー プロビジョニングを構成するには、次を行います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で、 **[Webroot Security Awareness Training]** を選択します。

    [Image: アプリケーションの一覧の Webroot Security Awareness Training のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [**管理者資格情報**] セクションで、[`https://awarenessapi.webrootanywhere.com/api/v2/scim`] にを入力します。 前に取得したシークレット トークンの値を、 **[シークレット トークン]** に入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Webroot Security Awareness Training に接続できることを確認します。 接続に失敗する場合は、Webroot Security Awareness Training アカウントに管理者アクセス許可があることを確認し、再試行します。

    [Image: スクリーンショットには、[管理者資格情報] ダイアログ ボックスが表示され、テナント U R L とシークレット トークンを入力できます。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra から Webroot Security Awareness Training に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作のために Webroot Security Awareness Training のユーザー アカウントを照合するために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Webroot Security Awareness Training API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | エクスターナルID | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra から Webroot Security Awareness Training に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作のために Webroot Security Awareness Training のグループを照合するために使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ |
    | メンバー | リファレンス |  |
    | エクスターナルID | 糸 |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### 変更ログ

- 2021 年 1 月 21 日 - ユーザーの主要な属性 "userName" のサポートを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/websphere-liberty-tutorial"} -->
## Microsoft Entra ID で WebSphere Liberty によるシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/websphere-liberty-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra と WebSphere Liberty の間のシングル サインオンを構成する方法について説明します。

この記事では、WebSphere Liberty と Microsoft Entra ID を統合する方法について説明します。 WebSphere Liberty を Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra ID を使用して、WebSphere Liberty にアクセスできるユーザーを制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して WebSphere Liberty に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- WebSphere Liberty でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから WebSphere Liberty を追加する

Microsoft Entra ID への WebSphere Liberty の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に WebSphere Liberty を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**WebSphere Liberty**」と入力します。
4. 結果のパネルから **[WebSphere Liberty]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**WebSphere Liberty**&gt;**シングルサインオン**に移動します。
3. 次のセクションで以下の手順を実行します。

    1. **[アプリケーションに移動]**を選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **[アプリケーション (クライアント) ID]** をコピーして、後で WebSphere Liberty 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. **[エンドポイント]** タブで、**[OpenID Connect メタデータ ドキュメント]** のリンクをコピーし、後で WebSphere Liberty 側の構成で使用します。

        [Image: タブのエンドポイントを示すスクリーンショット。]
4. 左側のメニューの **[認証]** タブに移動し、次の手順を実行します。

    1. **[URI のリダイレクト]** テキストボックスに、`https://<HOST_NAME>:<SSL_PORT>/oidcclient/redirect/<ClientID>` のパターンを使って URL を入力します。

        [Image: リダイレクト値を示すスクリーンショット。]
    2. **[構成]** ボタンを選択します。
5. 左側のメニューの **[証明書とシークレット]** に移動し、次の手順を実行します。

    1. **[クライアント シークレット]** タブに移動し、**[+ 新しいクライアント シークレット]** を選択します。
    2. テキストボックスに有効な **[説明]** を入力し、要件に応じてドロップダウンから **[有効期限]** 日数を選択し **[追加]** を選択します。

        [Image: クライアント シークレット値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、**[値]** が生成されます。 この値をコピーして、後で WebSphere Liberty 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### WebSphere Liberty SSO を構成する

**WebSphere Liberty** 側で OAuth/OIDC フェデレーションのセットアップを完了するには、テナント ID、アプリケーション ID、クライアント シークレットなどのコピーした値を Entra から [WebSphere Liberty サポート チーム](mailto:support@ibm.com)に送信する必要があります。 サポート チームはこれを設定して、OIDC 接続が両方の側で正しく設定されるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/webtma-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に WebTMA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/webtma-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WebTMA の間でシングル サインオンを構成する方法について説明します。

この記事では、WebTMA と Microsoft Entra ID を統合する方法について説明します。 WebTMA は、CMMS (コンピューター化メンテナンス管理システム) 資産、スペース、パーツ、作業指示書管理システムです。 WebTMA と Microsoft Entra ID を統合すると、次のことができます。

- WebTMA にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して WebTMA に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で WebTMA の Microsoft Entra シングル サインオンを構成してテストします。 WebTMA では、**SP** と **IDP** initiated シングルサインオンの両方がサポートされ、**Just In Time** ユーザープロビジョニングもサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### 前提 条件

Microsoft Entra ID を WebTMA と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、無料のアカウントを [作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- WebTMA でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから WebTMA アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから WebTMA を追加する

Microsoft Entra アプリケーション ギャラリーから WebTMA を追加して、WebTMA でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「[クイック スタート: ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)からアプリケーションを追加する」を参照してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[作成してユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) 割り当てる方法に関する記事のガイドラインに従ってください。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides).

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**WebTMA**&gt;**シングルサインオン**に進みます。
3. [**シングル サインオン方法の選択]** ページで、[ **SAML**] を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    ある。 **識別子** ボックスに、URL を入力します: `http://www.webtma.net`

    b。 [**応答 URL** ボックスに、次のパターンを使用して URL を入力します:`https://<hostName>/<loginApplicationPath>/SAMLService.aspx?c=<clientName>`
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [**サインオン URL** ボックスに、次のパターンを使用して URL を入力します:`https://<hostName>/<loginApplicationPath>/SAMLLogin.aspx?c=<clientName>`

    手記

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[回答者クライアント サポート チーム](mailto:support@tmasystems.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. WebTMA アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、属性の構成の画像を示しています。]
8. 上記に加えて、WebTMA アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメール |
    | ファーストネーム | ユーザーの名 |
    | 姓 | ユーザーの名字 |
9. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットには、証明書]
10. **[WebTMA** セットアップ] セクションにて、要件に基づいて適切なURLをコピーします。

    [Image: スクリーンショットは、構成に適した URL をコピーすることを示しています。]

### WebTMA SSO の構成

シングルサインオンをWebTMA **側** で構成するには、ダウンロードした**フェデレーションメタデータXML** と、アプリケーション構成からコピーした適切なURLをWebTMAサポートチーム へ送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### WebTMA テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを WebTMA に作成します。 WebTMA では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 WebTMA にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる WebTMA サインオン URL にリダイレクトされます。
- WebTMA のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した WebTMA に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [WebTMA] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した WebTMA に自動的にサインインされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/webxt-recognition-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に WebXT Recognition を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/webxt-recognition-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WebXT Recognition の間でシングル サインオンを構成する方法について説明します。

この記事では、WebXT Recognition と Microsoft Entra ID を統合する方法について説明します。 WebXT Recognition と Microsoft Entra ID を統合すると、次のことができます。

- WebXT Recognition にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して WebXT Recognition に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- WebXT Recognition でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- WebXT Recognition では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの WebXT Recognition の追加

Microsoft Entra ID への WebXT Recognition の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に WebXT Recognition を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「WebXT Recognition**」と入力します。
4. 結果パネルから **[WebXT Recognition** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### WebXT Recognition の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、WebXT Recognition に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと WebXT Recognition の関連ユーザーとの間にリンク関係を確立する必要があります。

WebXT Recognition に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **WebXT Recognition SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **WebXT Recognition テスト ユーザーの作成** - WebXT Recognition において B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の代表にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;、**WebXT 認識**&gt;、**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<webxt>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://webxtrecognition.<DOMAIN>.com/<INSTANCE>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [WebXT Recognition サポート チーム](mailto:webxtrecognition@biworldwide.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. WebXT Recognition アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、WebXT Recognition アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 従業員ID | ユーザー.社員ID |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. [ **WebXT Recognition のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### WebXT Recognition SSO の構成

**WebXT Recognition** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [WebXT Recognition サポート チーム](mailto:webxtrecognition@biworldwide.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### WebXT Recognition テスト ユーザーの作成

このセクションでは、WebXT Recognition で B.Simon というユーザーを作成します。 [WebXT Recognition サポート チーム](mailto:webxtrecognition@biworldwide.com)と協力して、WebXT Recognition プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した WebXT Recognition に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [WebXT Recognition] タイルを選択すると、SSO を設定した WebXT Recognition に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wedo-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に WEDO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wedo-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: ユーザー アカウントを WEDO に対して自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために WEDO ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [WEDO](https://www.wedo.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- WEDO でユーザーを作成する。
- アクセスが不要になった場合は、WEDO のユーザーを削除します。
- Microsoft Entra ID と WEDO 間でユーザー属性の同期を維持する。
- WEDO に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wedo-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- WEDO **Enterprise** サブスクリプション。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と WEDO の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように WEDO を構成する

- info@wedo.swiss と**シークレット トークン**を取得するには、で WEDO サポートに問い合わせてください。 これらの値は、WEDO アプリケーションの [プロビジョニング] タブの [テナント URL \*] フィールドと [シークレット トークン] \* フィールドに入力されます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから WEDO を追加する

Microsoft Entra アプリケーション ギャラリーから WEDO を追加して、WEDO へのプロビジョニングの管理を開始します。 SSO に対して WEDO を以前に設定した場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: WEDO に対する自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で WEDO の自動ユーザー プロビジョニングを構成するには、以下の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**を閲覧する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **WEDO** を選択します。

    [Image: アプリケーションの一覧の WEDO リンク]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、WEDO テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が WEDO に接続できることを確認します。 接続に失敗した場合は、WEDO アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から WEDO に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で WEDO のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、WEDO API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | displayName | 糸 |  |
    | タイトル | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | 活動中 | ブール値 |  |
    | 優先言語 | 糸 |  |
    | ユーザータイプ | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wedo-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に WEDO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wedo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WEDO の間にシングル サインオンを構成する方法について説明します。

この記事では、WEDO と Microsoft Entra ID を統合する方法について説明します。 WEDO と Microsoft Entra ID を統合すると、次のことができます。

- WEDO にアクセスできるユーザー Microsoft Entra ID を制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して WEDO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- WEDO でのシングル サインオン (SSO) が有効なサブスクリプション。 SSO サブスクリプションを取得するには、 [WEDO クライアント サポート チーム](mailto:info@wedo.swiss) にお問い合わせください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- WEDO では、**サービスプロバイダー (SP) 主導の SSO** と **アイデンティティプロバイダー (IDP) 主導の SSO** がサポートされます。
- WEDO では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wedo-provisioning-tutorial)。

### ギャラリーから WEDO を追加する

Microsoft Entra ID への WEDO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に WEDO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「WEDO**」と入力します。
4. 結果パネルから **WEDO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### WEDO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、WEDO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと WEDO の関連ユーザーとの間にリンク関係を確立する必要があります。

WEDO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **WEDO SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **WEDO テスト ユーザーの作成** - WEDO の中で B.Simon に対応し、Microsoft Entra に登録されたユーザーとリンクさせる役割を持つユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**WEDO**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.wedo.swiss/sp/acs`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.wedo.swiss/sp/acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.wedo.swiss/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、WEDO クライアント サポート チーム](mailto:info@wedo.swiss) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. WEDO アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザー.メール |
    | ファーストネーム | ユーザー名.ファーストネーム |
    | 苗字 | ユーザー.姓 |
    | ユーザー名 | ユーザー.ユーザー名 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **WEDO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### WEDO SSO の構成

次の手順に従って、WEDO で Microsoft Entra SSO を有効にします。

1. [WEDO](https://login.wedo.swiss/) にログインします。 **管理者ロール**が必要です。
2. プロファイル設定で、[**ネットワーク設定**] セクションの [**認証**] メニューを選択します。
3. [ **SAML 認証** ] ページで、次の手順を実行します。

    [Image: SAML 認証リンク]

    ある。 **SAML 認証**を有効にします。

    b。 [ **ID プロバイダー メタデータ (XML)]** タブを選択します。

    c. Azure portal からダウンロードした **フェデレーション メタデータ XML を** メモ帳に開き、メタデータ XML の内容をコピーして **X.509 証明書** テキストボックスに貼り付けます。

    d. **[保存] を選択します**。

#### WEDO テスト ユーザーの作成

このセクションでは、BOB Simon というテスト ユーザーを WEDO で作成します。 Microsoft **Entra テスト ユーザーの作成**に関する情報と一致する必要があります。

1. WEDO の [プロファイル] 設定で、[**ネットワーク設定**] セクションから **[ユーザー**] を選択します。
2. [ **ユーザーの追加] を選択します**。
3. [Add user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ポップアップで、ユーザーの情報を入力します

    ある。 名として「`B`」。

    b。 姓として「`Simon`」。

    c. メール アドレスとして「`username@companydomain.extension`」を入力します。 たとえば、`B.Simon@contoso.com` のようにします。 会社の短い名前と同じドメインの電子メールを使用することが必須です。

    d. ユーザーの種類として「`User`」。

    え [ **ユーザーの作成] を選択します**。

    f. [ **チームの選択** ] ページで、[ **保存]** を選択します。

    ジー [ **ユーザーの招待** ] ページで、[ **はい**] を選択します。
4. 電子メールで受信したリンクを使用してユーザーを検証する

注

偽のユーザーを作成する場合 (上記のメールはネットワークに存在しません)、 [サポート](mailto:info@wedo.swiss) に連絡してユーザーを検証してください\*。

注

WEDO では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wedo-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Azure portal で **[このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる WEDO サインオン URL にリダイレクトします。
- WEDO のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した WEDO に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで WEDO タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した WEDO に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/weekdone-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Weekdone を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/weekdone-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Weekdone 間のシングル サインオンを構成する方法について説明します。

この記事では、Weekdone と Microsoft Entra ID を統合する方法について説明します。 Weekdone を Microsoft Entra ID と統合すると、次のことができます:

- Weekdone にアクセスできるユーザー Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Weekdone に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

Weekdone と Microsoft Entra の統合を構成するには、次の項目が必要です:

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Weekdone でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Weekdone では、**SP** と **IDP** によって開始される SSO がサポートされます。
- Weekdone では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Weekdone の追加

Microsoft Entra ID への Weekdone の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Weekdone を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Weekdone**」と入力します。
4. 結果のパネルから **[Weekdone]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Weekdone 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Weekdone に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Weekdone の関連ユーザーとの間にリンク関係を確立する必要があります。

Weekdone に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Weekdone SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Weekdoneテストユーザーを作成 - WeekdoneにおいてB.Simonに対応するユーザーを作成し、Microsoft Entra上のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Weekdone**&gt;**シングルサインオン**に移動せよ。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://weekdone.com/a/<tenant>/metadata`

    注

    Weekdone からのメタデータ ファイルは、同じ URL を使用して取得できます。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://weekdone.com/a/<tenantname>`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://weekdone.com/a/<tenantname>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Weekdone クライアント サポート チーム](mailto:hello@weekdone.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Weekdone のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Weekdone SSO の構成

**Weekdone** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Weekdone サポート チーム](mailto:hello@weekdone.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Weekdone のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Weekdone に作成します。 Weekdone では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Weekdone にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Weekdone クライアント サポート チーム](mailto:hello@weekdone.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Weekdone Sign-On URL にリダイレクトされます。
- Weekdone のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Weekdone に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Weekdone] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Weekdone に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/whatfix-tutorial"} -->
## Microsoft Entra ID で Whatfix for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/whatfix-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Whatfix 間にシングル サインオンを構成する方法について説明します。

この記事では、Whatfix と Microsoft Entra ID を統合する方法について説明します。 Whatfix と Microsoft Entra ID を統合すると、次のことができます。

- Whatfix にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Whatfix に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Whatfix でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Whatfix では、**SP および IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Whatfix の追加

Microsoft Entra ID への Whatfix の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Whatfix を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Whatfix**」と入力します。
4. 結果ウィンドウで **[Whatfix]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Whatfix 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Whatfix に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Whatfix の関連ユーザーとの間にリンク関係を確立する必要があります。

Whatfix に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Whatfix SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Whatfix テスト ユーザーの作成** - Whatfix で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Whatfix** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    1. [ **追加の URL の設定] を選択します**。
    2. **[リレー状態]** テキスト ボックスに、お客様が指定したリレー状態 URL を入力します。

    注

    リレー状態 URL 値を取得するには、[Whatfix クライアント サポート チーム](https://support.whatfix.com)にお問い合わせください。
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://whatfix.com`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL をコピーします**。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Whatfix SSO を構成する

**Whatfix** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Whatfix サポート チーム](https://support.whatfix.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Whatfix テスト ユーザーを作成する

このセクションでは、Whatfix で Britta Simon というユーザーを作成します。 [Whatfix サポート チーム](https://support.whatfix.com)と連携し、Whatfix プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Whatfix サインオン URL にリダイレクトされます。
- Whatfix のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Whatfix に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Whatfix] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Whatfix に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/whimsical-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Whimsical を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/whimsical-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Whimsical に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Whimsical ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Whimsical](https://whimsical.com) に対するユーザーおよびグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Whimsical でユーザーを作成する
- アクセスが不要になった場合に Whimsical でユーザーを削除する
- Microsoft Entra ID と Whimsical の間でユーザー属性の同期を維持する。
- [シングルサインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/benq-iam-tutorial) から Whimsical へ (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- SCIM を使用するには、SAML を有効にして正しく構成する必要があります。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Whimsical の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Whimsical を構成する

1. SCIM を有効にするには、まず Microsoft Entra ID を使用して SAML SSO を設定する必要があります。
2. 左上のワークスペース名の下にある [ワークスペースの設定] に移動します。
3. SCIM プロビジョニングを有効にし、[表示] を選択してトークンを取得します。
4. Microsoft Entra ID の [プロビジョニング] タブで、[プロビジョニング モード] を [自動] に設定し、[https://whimsical.com/public-api/scim-v2/?aadOptscim062020&quot] を [テナント URL] へ貼り付けます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Whimsical を追加する

Microsoft Entra アプリケーション ギャラリーから Whimsical を追加して、Whimsical へのプロビジョニングの管理を開始します。 以前に SSO 用に Whimsical をセットアップしたことがある場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Whimsical への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Whimsical の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Whimsical]** を選択します。

    [Image: アプリケーションの一覧の Whimsical のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョン] タブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、気まぐれなテナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Whimsical に接続できることを確認します。 接続に失敗した場合は、Whimsical アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Whimsical に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Whimsical のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Whimsical API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | エクスターナルID | 糸 |  |
    | 活動中 | ブール値 |  |
    | ディスプレイ名 | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/whimsical-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Whimsical を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/whimsical-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Whimsical 間にシングル サインオンを構成する方法について説明します。

この記事では、Whimsical と Microsoft Entra ID を統合する方法について説明します。 Whimsical と Microsoft Entra ID を統合すると、次のことができます:

- Whimsical にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Whimsical に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Whimsical チーム ワークスペース。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Whimsical では、**SP および IDP による** SSO の開始がサポートされています。
- Whimsical では、 **Just In Time** ユーザー プロビジョニングがサポートされます。
- Whimsical では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/whimsical-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Whimsical の追加

Microsoft Entra ID への Whimsical の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Whimsical を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Whimsical**」と入力します。
4. 結果パネルで **[Whimsical]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Whimsical 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Whimsical に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Whimsical の関連ユーザーとの間にリンク関係を確立する必要があります。

Whimsical に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Whimsical SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Whimsical のテストユーザーを作成する** - Whimsical で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Whimsical**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://whimsical.com/saml/<CUSTOM_IDENTIFIER>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://whimsical.com/@<TENANT_NAME>`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 具体的な値は、[Whimsical Workspace settings]\(Whimsical ワークスペース設定\) の [SAML setup]\(SAML セットアップ\) 画面に表示されます。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Whimsical アプリケーションでは、特定の形式の SAML アサーションが想定されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Whimsical アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Whimsical のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Whimsical SSO の構成

1. 別の Web ブラウザー ウィンドウで、Whimsical 企業サイトに管理者としてサインインします
2. **Whimsical** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** を[ワークスペース設定](https://whimsical.com/workspace/settings)にアップロードする必要があります。

    [Image: Whimsical ワークスペース SAML のセットアップ]

**フェデレーション メタデータ XML** のアップロードは、SAML SSO 接続を設定するために Whimsical で実行する必要がある唯一の手順です。

#### Whimsical テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Whimsical に作成します。 Whimsical では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Whimsical にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Whimsical のサインオン URL にリダイレクトされます。
- Whimsical のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Whimsical に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Whimsical] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Whimsical に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/whos-on-location-tutorial"} -->
## Microsoft Entra ID でシングルサインオンを利用するために WhosOnLocation を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/whos-on-location-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WhosOnLocation 間のシングル サインオンを構成する方法について説明します。

この記事では、WhosOnLocation と Microsoft Entra ID を統合する方法について説明します。 WhosOnLocation を Microsoft Entra ID と統合すると、次のことが可能になります。

- WhosOnLocation にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで WhosOnLocation に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- WhosOnLocation でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- WhosOnLocation では、**SP** initiated SSO がサポートされます。

### ギャラリーからの WhosOnLocation の追加

Microsoft Entra ID への WhosOnLocation の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に WhosOnLocation を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**WhosOnLocation**」と入力します。
4. 結果パネルで **[WhosOnLocation]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### WhosOnLocation に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、WhosOnLocation に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと WhosOnLocation の関連ユーザー間にリンク関係を確立する必要があります。

WhosOnLocation に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **WhosOnLocation SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **WhosOnLocation のテスト ユーザーの作成** - WhosOnLocation で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**WhosOnLocation**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.whosonlocation.com/saml/metadata/<CUSTOM_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.whosonlocation.com/saml/acs/<CUSTOM_ID>`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.whosonlocation.com/saml/login/<CUSTOM_ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[WhosOnLocation クライアント サポート チーム](mailto:support@whosonlocation.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[WhosOnLocation のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### WhosOnLocation SSO の構成

1. 別のブラウザー ウィンドウで、WhosOnLocation 企業サイトに管理者としてサインオンします。
2. **ツール**&gt;、**アカウント**を選択します。

    [Image: WhosOnLocation サイトの [ツール] メニューから [アカウント] が選択されていることを示すスクリーンショット。]
3. 左側のナビゲーターで、**[Employee Access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/従業員のアクセス)** を選択します。

    [Image: [アカウントのプロファイル] から [従業員のアクセス] が選択されていることを示すスクリーンショット。]
4. 次のページで、以下の手順を実行します。

    [Image: ユーザー データを入力できる [従業員アクセス] タブを示すスクリーンショット。]

    a. **[Single sign-on with SAML](SAML によるシングル サインオン)** で **[Yes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/はい)** に変更します。

    b。 **[発行者 URL]** ボックスに、先ほどコピーした**エンティティ ID** の値を貼り付けます。

    c. **[SSO エンドポイント]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    d. ダウンロードした **証明書 (Base64)** をメモ帳に開き、**証明書** ボックスに内容を貼り付けます。

    e. [ **SAML 構成の保存] を選択します**。

#### WhosOnLocation テスト ユーザーの作成

このセクションでは、WhosOnLocation で B.Simon というユーザーを作成します。 [WhosOnLocation サポート チーム](mailto:support@whosonlocation.com)と連携して、WhosOnLocation プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる WhosOnLocation Sign-On URL にリダイレクトされます。
- WhosOnLocation のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [WhosOnLocation] タイルを選択すると、このオプションは WhosOnLocation Sign-On URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/whosoff-tutorial"} -->
## Microsoft Entra ID で WhosOff for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/whosoff-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WhosOff 間にシングル サインオンを構成する方法について説明します。

この記事では、WhosOff と Microsoft Entra ID を統合する方法について説明します。 WhosOff はオンライン休暇管理プラットフォームです。 Azure の WhosOff 統合を使用することで、お客様はシングル サインオン プロバイダーとして Azure を使用して WhosOff アカウントにサインインできます。 WhosOff と Microsoft Entra ID を統合すると、次のことができます。

- WhosOff にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って WhosOff に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で WhosOff 向けの Azure AD のシングル サインオンを構成してテストします。 WhosOff では、**SP** 開始シングルサインオンと **IDP** 開始シングルサインオンの両方がサポートされます。

### [前提条件]

Microsoft Entra ID を WhosOff と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- WhosOff でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから WhosOff アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから WhosOff を追加する

Microsoft Entra アプリケーション ギャラリーから WhosOff を追加して、WhosOff でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**WhosOff**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure に事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://app.whosoff.com/int/<Integration_ID>/sso/azure/`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この記事で後述する Azure SSO をアクティブ化するときに、WhosOff アカウントから `Integration_ID` を収集できます。 クエリについては、 [WhosOff サポート チーム](mailto:support@whosoff.com)にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **WhosOff のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### WhosOff SSO の構成

1. WhosOff 企業サイトに管理者としてログインします。
2. 左側のメニューの **[管理**] に移動し**、[会社の設定]**&gt;**[シングル サインオン**] を選択します。
3. [ **シングル サインオンのセットアップ] セクションで** 、次の手順に従います。

    [Image: メタデータと構成の設定を示すスクリーンショット。]

    1. ドロップダウンから **Azure** SSO プロバイダーを選択し、[ **Active SSO**] を選択します。
    2. アクティブ化したら、 **統合 GUID を** コピーしてコンピューターに保存します。
    3. ダウンロードした [**ファイルの選択**] オプションを選択して、**フェデレーション メタデータ XML** ファイルをアップロードします。
    4. [ **変更の保存] を選択します**。

#### WhosOff テスト ユーザーの作成

このセクションでは、WhosOff SSO で Britta Simon というユーザーを作成します。 [WhosOff サポート チーム](mailto:support@whosoff.com)と協力して、WhosOff SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる WhosOff のサインオン URL にリダイレクトされます。
- WhosOff のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した WhosOff に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [WhosOff] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した WhosOff に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/whosoffice-tutorial"} -->
## Microsoft Entra ID で WhosOffice for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/whosoffice-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WhosOffice 間にシングル サインオンを構成する方法について説明します。

この記事では、WhosOffice と Microsoft Entra ID を統合する方法について説明します。 WhosOffice と Microsoft Entra ID を統合すると、次のことができます:

- WhosOffice にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して WhosOffice に自動的にサインインできるようにします。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- WhosOffice でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- WhosOffice では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの WhosOffice の追加

Microsoft Entra ID への WhosOffice の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に WhosOffice を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**WhosOffice**」と入力します。
4. 結果のパネルで **[WhosOffice]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### WhosOffice 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、WhosOffice に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと WhosOffice の関連ユーザーとの間にリンク関係を確立する必要があります。

WhosOffice に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **WhosOffice の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **WhosOffice テスト ユーザーの作成 - WhosOffice** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**WhosOffice**&gt;**シングルサインオン** の順に選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    **[応答 URL]** ボックスに、`https://<SUBDOMAIN>.my.whosoffice.com/int/azure/consume.aspx` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、`https://<SUBDOMAIN>.my.whosoffice.com/int/azure` という形式で URL を入力します。

    注意

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[WhosOffice クライアント サポート チーム](mailto:support@whosoffice.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[WhosOffice のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### WhosOffice の SSO の構成

1. 別の Web ブラウザー ウィンドウで、WhosOffice 企業サイトに管理者としてサインインします
2. [ **設定]** を選択し、[会社] を選択 **します**。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) から [Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社) が選択されていることを示すスクリーンショット。]
3. [ **アプリ/統合] を選択します**。
4. プロバイダーのドロップダウンから **Microsoft Azure** を選択し、[ **ログイン プロバイダーのアクティブ化**] を選択します。

    [Image: [Microsoft Azure] に対して [Activate Login Provider](ログイン プロバイダーのアクティブ化) が選択されていることを示すスクリーンショット。]
5. [アップロード] オプションを選択して、Azure portal からダウンロードしたフェデレーション メタデータ ファイルを **アップロード** します。

    [Image: メタ データ ファイルの [Upload](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アップロード) オプションを示すスクリーンショット。]

#### WhosOffice のテスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、管理者として WhosOffice Web サイトにサインインします。
2. [ **設定] を** 選択し、[ユーザー] を選択 **します**。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) から [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) が選択されていることを示すスクリーンショット。]
3. **[Create new User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの作成)** を選択します。

    [Image: [Create new User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの作成) が選択されていることを示すスクリーンショット。]
4. 組織の要件に従って、ユーザーに関する必要な詳細情報を入力します。

    [Image: [new User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザー) ダイアログ ボックスを示すスクリーンショット。ここで、ユーザー データを入力できます。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる WhosOffice のサインオン URL にリダイレクトされます。
- WhosOffice のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した WhosOffice に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [WhosOffice] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した WhosOffice に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wiggledesk-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に WiggleDesk を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wiggledesk-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から WiggleDesk にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために WiggleDesk と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを自動的に [WiggleDesk](https://wiggledesk.com) にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- WiggleDesk でユーザーを作成します。
- アクセスが不要になったら、WiggleDesk のユーザーを削除します。
- Microsoft Entra ID と WiggleDesk の間でユーザー属性の同期を維持します。
- [シングル サインオンを WiggleDesk に](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) します (推奨)。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ WiggleDesk のユーザー アカウント。

### プロビジョニングの展開を計画する: 手順 1

- プロビジョニング サービスの [のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)について説明します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- Microsoft Entra ID と WiggleDesk [の間でどのデータをマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するかを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように WiggleDesk を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように WiggleDesk を構成するには、WiggleDesk サポートにお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから WiggleDesk を追加する

Microsoft Entra アプリケーション ギャラリーから WiggleDesk を追加して、WiggleDesk へのプロビジョニングの管理を開始します。 SSO 用に WiggleDesk を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: WiggleDesk への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で WiggleDesk の自動ユーザー プロビジョニングを構成するには:

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [WiggleDesk 選択します。

    [Image: アプリケーションの一覧の WiggleDesk リンクのスクリーンショット。]
4. **[プロビジョニング**] タブを選択します。

    [プロビジョニング] タブの [Image: スクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 自動プロビジョニング タブのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、WiggleDesk テナント URL とシークレット トークン\*\* を入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が WiggleDesk に接続できることを確認します。 接続に失敗した場合は、WiggleDesk アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **属性マッピング** セクションで、Microsoft Entra ID から WiggleDesk に同期されるユーザー属性を確認します。 **属性が選択された** 照合プロパティは、更新操作のために WiggleDesk のユーザーアカウントと一致させるために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が WiggleDesk API でサポートされていることを確認する必要があります。 **[** 保存] ボタンを選択して、変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | WiggleDeskによって必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 | ✓ | ✓ |
    | アクティブ | ブール値 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バーの](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wikispaces-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Wikispaces を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wikispaces-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wikispaces 間にシングル サインオンを構成する方法について学習します。

この記事では、Wikispaces と Microsoft Entra ID を統合する方法について説明します。 Wikispaces と Microsoft Entra ID を統合すると、次のことができます。

- Wikispaces にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Wikispaces に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

Wikispaces と Microsoft Entra の統合を構成するには、次の項目が必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Wikispaces でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Wikispaces では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの Wikispaces の追加

Microsoft Entra ID への Wikispaces の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Wikispaces を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Wikispaces**」と入力します。
4. 結果パネルから **Wikispaces** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Wikispaces 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Wikispaces に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Wikispaces の関連ユーザーとの間にリンク関係を確立する必要があります。

Wikispaces に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Wikispaces の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Wikispaces テストユーザーの作成 - Wikispaces** で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Wikispaces**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://session.wikispaces.net/<instancename>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.wikispaces.net`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、Wikispaces クライアント サポート チームに問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Wikispaces のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Wikispaces SSO の構成

**Wikispaces** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を Wikispaces サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Wikispaces テスト ユーザーの作成

Microsoft Entra ユーザーが Wikispaces にサインインできるようにするには、Wikispaces にプロビジョニングする必要があります。 Wikispaces の場合、プロビジョニングは手動で行います。

#### ユーザー アカウントをプロビジョニングするには、次の手順を実行します。

1. **Wikispaces** 企業サイトに管理者としてサインインします。
2. **[メンバー**] に移動します。

    [Image: [メンバー] メニューを示すスクリーンショット。]
3. [ **ユーザーの招待**] を選択します。

    [Image: [メンバー] ページを示すスクリーンショット。[ユーザーの招待] を選択できます。]
4. [ **ユーザーの招待** ] セクションで、次の手順を実行します。

    [Image: ユーザー データを入力できる [ユーザーの招待] セクションを示すスクリーンショット。]

    ある。 プロビジョニングする有効な Microsoft Entra アカウントの **ユーザー名またはメール アドレス** を関連するテキスト ボックスに入力します。

    b。 [ **送信] を選択します**。

    注

    アカウントがアクティブになる前に、Microsoft Entra アカウント所有者に、アカウント確認用のリンクを含む電子メールが送信されます。

注

Microsoft Entra ユーザー アカウントのプロビジョニングには、他の Wikispaces ユーザー アカウント作成ツールや、Wikispaces から提供されている API を使用することができます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Wikispaces のサインオン URL にリダイレクトされます。
- Wikispaces のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Wikispaces] タイルを選択すると、このオプションは Wikispaces のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/windchill-tutorial"} -->
## Microsoft Entra ID で Windchill シングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/windchill-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Windchill の間にシングル サインオンを構成する方法について説明します。

この記事では、Windchill と Microsoft Entra ID を統合する方法について説明します。 Windchill PLM Software - 主要な製品データ管理と高度な製品ライフサイクル管理アプリケーションの包括的なポートフォリオ全体で、すぐに使用できる機能により迅速に価値を実現します。 Windchill を Microsoft Entra ID と統合すると、次のことができます。

- Windchill にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Windchill に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Windchill 用の Microsoft Entra シングル サインオンを構成してテストします。 Windchill では、**SP** Initiated と **IDP** Initiated のシングル サインオンがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Windchill と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Windchill でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Windchill アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Windchill を追加する

Microsoft Entra アプリケーション ギャラリーから Windchill を追加して、Windchill でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Windchill**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. アプリケーションを**SP**開始モードで構成する場合は、次の手順を実行してください。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<hostname:port>/Shibboleth.sso/Login`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、[Windchill クライアント サポート チーム](mailto:support@ptc.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Windchill のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Windchill SSO を構成する

**Windchill** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーションの構成からコピーした適切な URL を [Windchill サポート チーム](mailto:support@ptc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Windchill テスト ユーザーを作成する

このセクションでは、Windchill で Britta Simon というユーザーを作成します。 [Windchill サポート チーム](mailto:support@ptc.com)と連携し、Windchill プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

1. [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Windchill のサインオン URL にリダイレクトされます。
2. Windchill のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

1. [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Windchill に自動的にサインインします。
2. Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Windchill タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Windchill に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wingspanetmf-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Wingspan eTMF を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wingspanetmf-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wingspan eTMF の間でシングル サインオンを構成する方法について説明します。

この記事では、Wingspan eTMF と Microsoft Entra ID を統合する方法について説明します。 Wingspan eTMF を Microsoft Entra ID と統合すると、次のことができます。

- Wingspan eTMF にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Wingspan eTMF に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Wingspan eTMF でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Wingspan eTMF では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Wingspan eTMF の追加

Microsoft Entra ID へのWingspan eTMFの統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに Wingspan eTMF を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Wingspan eTMF**」と入力します。
4. 結果パネルから **[Wingspan eTMF]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Wingspan eTMF 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Wingspan eTMF に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Wingspan eTMF の関連ユーザーとの間にリンク関係を確立する必要があります。

Wingspan eTMF に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Wingspan eTMF SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Wingspan eTMF テストユーザーの作成** - Wingspan eTMFでB.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Wingspan eTMF**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] のスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer name>.<instance name>.mywingspan.com/saml`

    b。 **[識別子]** ボックスに、`http://saml.<instance name>.wingspan.com/shibboleth` という形式で URL を入力します。

    c. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<customer name>.<instance name>.mywingspan.com/`

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには、Wingspan eTMF クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. **[Wingspan eTMF のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: [構成 URL のコピー] のスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Wingspan eTMF SSO の構成

**Wingspan eTMF** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を Wingspan eTMF サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Wingspan eTMF のテスト ユーザーの作成

このセクションでは、Wingspan eTMF の Britta Simon という名前のユーザーを作成します。 Wingspan eTMF サポート チームと連携して、Wingspan eTMF プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Wingspan eTMF サインオン URL にリダイレクトされます。
- Wingspan eTMF のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで Wingspan eTMF タイルを選択すると、このオプションは Wingspan eTMF のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wirewheel-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に WireWheel を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wirewheel-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WireWheel 間にシングル サインオンを構成する方法について学習します。

この記事では、WireWheel と Microsoft Entra ID を統合する方法について説明します。 WireWheel と Microsoft Entra ID を統合すると、次のことができます:

- Microsoft Entra ID で、WireWheel にアクセスできるユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って WireWheel に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- WireWheel でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- WireWheel では、 **サービス プロバイダー (SP) と ID プロバイダー (IdP)** によって開始される SSO がサポートされます。
- WireWheel では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの WireWheel の追加

Microsoft Entra ID への WireWheel の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に WireWheel を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「WireWheel**」と入力します。
4. 結果パネルから **WireWheel** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 アプリ構成ウィザード](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### WireWheel 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、WireWheel に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと WireWheel の関連ユーザーとの間にリンク関係を確立する必要があります。

WireWheel に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO の構成**- ユーザーがシングル サインオンMicrosoft Entra使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **WireWheel の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **WireWheel テストユーザーを作成 - WireWheel で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**WireWheel**&gt;**シングル サインオン** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT_NAME>.wirewheel.io/sso/<CUSTOM_IDENTIFIER>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT_NAME>.wirewheel.io/sso/<CUSTOM_IDENTIFIER>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT_NAME>.wirewheel.io/auth`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、WireWheel クライアント サポート チーム](mailto:support@wirewheel.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **WireWheel のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### WireWheel SSO を設定する

**WireWheel** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [WireWheel サポート チーム](mailto:support@wirewheel.io)に送信する必要があります。 WireWheel サポート チームは、これらの値を使用して、両方の側で SAML SSO 接続を正しく構成します。

#### WireWheel のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを WireWheel に作成します。 WireWheel では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 WireWheel にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

#### SP 開始:

SP によって開始されるサインオンをテストするには、次のいずれかのオプションを使用します。

- [ **このアプリケーションをテストする**] を選択します。 [ **このアプリケーションのテスト** ] を選択すると、ログイン フローを開始できる WireWheel サインオン URL にリダイレクトされます。
- WireWheel のサインオン URL に直接移動し、そこからログイン フローを開始します。

#### IDP 起動しました。

IdP によって開始されるサインオンをテストするには、次のオプションを使用します。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した WireWheel に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで WireWheel タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した WireWheel に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wisdom-by-invictus-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Wisdom by Invictus を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wisdom-by-invictus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wisdom by Invictus の間にシングル サインオンを構成する方法について説明します。

この記事では、Wisdom by Invictus と Microsoft Entra ID を統合する方法について説明します。 Wisdom by Invictus と Microsoft Entra ID を統合すると、次のことができます。

- Wisdom by Invictus にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Wisdom by Invictus に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Wisdom by Invictus でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Wisdom by Invictus では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Wisdom by Invictus の追加

Microsoft Entra ID への Wisdom by Invictus の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Wisdom by Invictus を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Wisdom by Invictus**」と入力します。
4. 結果ウィンドウで **[Wisdom by Invictus]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Wisdom by Invictus 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Wisdom by Invictus に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Wisdom by Invictus の関連ユーザーとの間にリンク関係を確立する必要があります。

Wisdom by Invictus に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Wisdom by Invictus の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Wisdom by Invictus のテスト ユーザーの作成** - Wisdom by Invictus で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Wisdom by Invictus**&gt;**Single のサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://invictuselearning-pool7.com/?option=saml_user_login&idp=Microsoft`
7. **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Wisdom by Invictus の SSO の構成

**Wisdom by Invictus** 側でシングル サインオンを構成するには、**アプリケーション フェデレーション メタデータ URL** を [Wisdom by Invictus サポート チーム](mailto:support@invictus.in)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Wisdom by Invictus のテスト ユーザーの作成

このセクションでは、Wisdom by Invictus で Britta Simon というユーザーを作成します。 [Wisdom by Invictus サポート チーム](mailto:support@invictus.in)と連携して、Wisdom by Invictus プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Sign-On URL によって Wisdom by Invictus にリダイレクトされます。
- Wisdom by Invictus のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Wisdom by Invictus に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Wisdom by Invictus] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Wisdom by Invictus に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wistia-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Wistia を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wistia-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wistia 間のシングル サインオンを構成する方法について説明します。

この記事では、Wistia と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID で Wistia にサインインし、ビデオ マーケティング戦略をさらに強化しましょう。 Wistia ビデオ マーケティング プラットフォームの詳細については、wistia.com を参照してください。 Wistia を Microsoft Entra ID と統合すると、次のことが可能になります。

- Wistia にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Wistia に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Wistia に対する Microsoft Entra のシングル サインオンをテスト環境で構成・テストする。 Wistia は **SP** 開始のシングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Wistia と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Wistia でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Wistia アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Wistia を追加する

Microsoft Entra アプリケーション ギャラリーから Wistia を追加し、Wistia に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Wistia**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、値を入力します。 `urn:amazon:cognito:sp:us-east-1_2sjOZnclh`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://sso-auth.wistia.com/saml2/idpresponse`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<AccountName>.wistia.com/login/sso`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、[Wistia クライアント サポート チーム](mailto:support@wistia.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Wistia アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: トークン属性の画像を示すスクリーンショット。]
7. その他に、Wistia アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Wistia SSO の構成

**Wistia** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Wistia サポート チーム](mailto:support@wistia.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Wistia テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Wistia に作成します。 Wistia では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Wistia にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Wistia のサインオン URL にリダイレクトされます。
- Wistia のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Wistia] タイルを選択すると、このオプションは Wistia のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wiz-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Wiz SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wiz-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wiz SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、Wiz SSO と Microsoft Entra ID を統合する方法について説明します。 Wiz SSO と Microsoft Entra ID を統合すると、次のことができます。

- Wiz SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Wiz SSO に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Wiz SSO のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Wiz SSO では、 **SP** Initiated SSO がサポートされます。
- Wiz SSO では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Wiz SSO を追加する

Microsoft Entra ID への Wiz SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Wiz SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にブラウズします。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに「**Wiz SSO**」と入力します。
4. 結果パネルから **Wiz SSO を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Wiz SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Wiz SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Wiz SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Wiz SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Wiz SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Wiz SSO テスト ユーザーの作成** - B.Simon に対応するユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise appsWiz SSOシングルサインオンに移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `urn:amazon:cognito:sp:<region_identifier>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<Uuid>.auth.<Region>.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.wiz.io/idp-login?clientId=<CLIENT_ID>&idp=<IDP_INSTANCE>`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには [、Wiz SSO サポート チーム](mailto:delivery@wiz.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Wiz SSO アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Wiz SSO では、これがユーザーの電子メール アドレスにマップされると想定されています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 前の手順に加えて、Wiz SSO アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次の表に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | ユーザーの表示名 |
    | グループ | ユーザー.グループ |

    これらの追加の要求を作成するには:

    ある。 **[ユーザー属性と要求**] に移動し、[**編集]** を選択します。

    b。 [ **グループ要求の追加] を選択します**。

    c. **[すべてのグループ]** を選択します。

    d. [ **詳細オプション**] で、[ **グループ要求の名前をカスタマイズする** ] チェック ボックスをオンにします。

    え **[名前]** に**「グループ**」と入力します。

    f. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Wiz SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Wiz SSO の構成

**Wiz SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Wiz SSO サポート チーム](mailto:delivery@wiz.io)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Wiz SSO テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを Speexx に作成します。 Speexx では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Speexx にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはサインイン フローを開始できる Wiz SSO のサインオン URL にリダイレクトされます。
- Wiz SSO のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Wiz SSO] タイルを選択すると、このオプションは Wiz SSO Sign-On URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wizergosproductivitysoftware-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Wizergos Productivity Software を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wizergosproductivitysoftware-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wizergos Productivity Software の間でシングル サインオンを構成する方法について説明します。

この記事では、Wizergos Productivity Software と Microsoft Entra ID を統合する方法について説明します。 Wizergos Productivity Software と Microsoft Entra ID の統合には、次の利点があります。

- Wizergos Productivity Software にアクセスする Microsoft Entra ID ユーザーを制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Wizergos Productivity Software に自動的にサインイン (シングル サインオン) できるようにすることができます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Wizergos Productivity Software でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Wizergos Productivity Software では、**IDP** によって開始される SSO がサポートされます

### ギャラリーからの Wizergos Productivity Software の追加

Microsoft Entra ID への Wizergos Productivity Software の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Wizergos Productivity Software を追加する必要があります。

**ギャラリーから Wizergos Productivity Software を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Wizergos Productivity Software**」と入力し、結果パネルで **Wizergos Productivity Software** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果リストの Wizergos Productivity Software]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Wizergos Productivity Software で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Wizergos Productivity Software 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Wizergos Productivity Software で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Wizergos Productivity Software シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Wizergos Productivity Software テストユーザーを作成する** - Microsoft Entra のユーザー表現にリンクされた、Wizergos Productivity Software 内の Britta Simon の対応ユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Wizergos Productivity Software で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Wizergos Productivity Software** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Wizergos Productivity Software のドメインと URL] のシングル サインオン情報]

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.wizergos.net`
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Wizergos Productivity Software のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Wizergos Productivity Software シングル サインオンの構成

1. 別の Web ブラウザーのウィンドウで、管理者として Wizergos Productivity Software テナントにサインオンします。
2. ハンバーガー メニューから **[Admin (管理者)]** を選択します。

    [Image: メニューから [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) アイコンが選択されていることを示すスクリーンショット。]
3. 左側のメニューの [管理] ページで、[ **認証** ] を選択し、[ **Microsoft Entra ID**] を選択します。

    [Image: [AUTHENTICATION](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) から [Microsoft Entra ID] が選択された状態を示すスクリーンショット。]
4. **[AUTHENTICATION (認証)]** セクションで、次の手順に従います。

    [Image: [AUTHENTICATION](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) ページのスクリーンショット。ここで、説明されている値を入力することができます。]

    ある。 [ **アップロード** ] ボタンを選択して、Microsoft Entra ID からダウンロードした証明書をアップロードします。

    b。 **[発行者の URL]** テキストボックスに、コピーした **Microsoft Entra ID** の値を貼り付けます。

    c. **[シングル サインオン URL]** テキストボックスに、コピーした**ログイン URL** の値を貼り付けます。

    d. **[シングル サインアウト URL]** テキストボックスに、Azure portal からコピーした**ログアウト URL** の値を貼り付けます。

    え [ **保存] ボタンを** 選択します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Wizergos Productivity Software テスト ユーザーの作成

このセクションでは、Wizergos Productivity Software で Britta Simon というユーザーを作成します。 [Wizergos Productivity Software サポート チーム](mailto:support@wizergos.com)と連携して、Wizergos Productivity Software プラットフォームにユーザーを追加してください。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Wizergos Productivity Software] タイルを選択すると、SSO を設定した Wizergos Productivity Software に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wootric-tutorial"} -->
## Microsoft Entra ID で Wootric for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wootric-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wootric 間にシングル サインオンを構成する方法について説明します。

この記事では、Wootric と Microsoft Entra ID を統合する方法について説明します。 Wootric を Microsoft Entra ID と統合すると、次のことができるようになります。

- Wootric にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Wootric に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Wootric でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Wootric では、**IDP** Initiated SSO がサポートされています。
- Wootric では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの Wootric の追加

Microsoft Entra ID への Wootric の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Wootric を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Wootric**」と入力します。
4. 結果のパネルから **[Wootric]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Wootric 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Wootric に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Wootric の関連ユーザーとの間にリンク関係を確立する必要があります。

Wootric に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Wootric の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Wootric のテストユーザーを作成** - Wootric で B.Simon を再現し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Wootric**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure で事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Wootric アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
7. その他に、Wootric アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 身分証明書 | user.objectid (ユーザーのオブジェクトID) |
8. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
9. **[Wootric のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Wootric の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Wootric 企業サイトに管理者としてサインインします
2. 上部のメニューから **[設定] アイコン** を選択します。

    [Image: Wootric サイトから [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) アイコンが選択されていることを示すスクリーンショット。]
3. **INTEGRATIONS** で、左側のメニューから **[認証**] を選択し、[**Microsoft Entra ID でシングル サインオンを有効にする**] を選択します。
4. 次のページで、以下の手順を実行します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) ページを示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[ID プロバイダーのシングル サインオン URL]** テキストボックスに、先ほどコピーした **[ログイン URL]** の値を貼り付けます。

    b。 **[ID プロバイダーの発行者]** テキストボックスに、先ほどコピーした**エンティティ ID** を貼り付けます。

    c. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[X.509 証明書]** テキストボックスに貼り付けます。

    d. **[Automatically grant access to new users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーへのアクセスを自動的に許可する)** チェック ボックスをオンにします。

    え **保存** を選択します。

#### Wootric のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Wootric に作成します。 Wootric では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Wootric にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Wootric に自動的にサインインします
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Wootric] タイルを選択すると、SSO を設定した Wootric に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workable-tutorial"} -->
## Microsoft Entra ID を使用して Workable for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workable-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workable 間にシングル サインオンを構成する方法について学習します。

この記事では、Workable と Microsoft Entra ID を統合する方法について説明します。 Workable を Microsoft Entra ID と統合すると、次のことができます。

- Workable にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Workable に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workable でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Workable では、**SP と IDP** によって開始される SSO がサポートされます。
- Workable では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Workable の追加

Microsoft Entra ID への Workable の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Workable を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Workable**」と入力します。
4. 結果のパネルから **[Workable]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workable 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Workable で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Workable の関連ユーザーとの間にリンク関係を確立する必要があります。

Workable で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workable SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Workable テスト ユーザーの作成** - WorkableにおけるB.Simonの対応ユーザーを作成し、Microsoft Entraのユーザー表現と接続します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Workable**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://id.workable.com/auth/saml/ats_server/<SUBDOMAIN>/callback`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.workable.com/signin`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[Workable クライアント サポート チーム](mailto:support@workable.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Workable のセットアップ]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workable SSO を構成する

Workable 内で SSO を有効にするには、専任の Workable アカウント マネージャーに連絡して、次の項目を提供します。

1. ログイン URL。
2. 証明書ファイル。
3. ログアウト URL。

シングル サインオンが有効になると Workable アカウント マネージャーから通知されるので、[Workable の SSO ページ](https://help.workable.com/hc/articles/360000067753-Single-Sign-on-SSO-Overview-Pro)で Workable アカウント サブドメインを使用してサインインできます。

#### Workable テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Workable に作成します。 Workable では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Workable にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Workable サインオン URL にリダイレクトされます。
- Workable のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Workable に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Workable] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Workable に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workboard-tutorial"} -->
## Microsoft Entra ID で WorkBoard for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workboard-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と WorkBoard 間にシングル サインオンを構成する方法について学習します。

この記事では、WorkBoard と Microsoft Entra ID を統合する方法について説明します。 WorkBoard を Microsoft Entra ID と統合すると、次のことができます。

- WorkBoard にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して WorkBoard に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- WorkBoard でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- WorkBoard では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの WorkBoard の追加

Microsoft Entra ID への WorkBoard の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに WorkBoard を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**WorkBoard**」と入力します。
4. 結果パネルで **[WorkBoard]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### WorkBoard 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、WorkBoard で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと WorkBoard の関連ユーザーとの間にリンク関係を確立する必要があります。

WorkBoard で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **WorkBoard SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **WorkBoardテストユーザーを作成 - B.Simon に対応するユーザーを WorkBoard で作成し、Microsoft Entra の代表としてリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**WorkBoard**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.myworkboard.com/lib/php/simplesaml/www/module.php/saml/sp/metadata.php/<ENVIRONMENTNAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.myworkboard.com/lib/php/simplesaml/www/module.php/saml/sp/saml2-acs.php/<ENVIRONMENTNAME>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.myworkboard.com/wb/user/login?saml_sso=<ENVIRONMENTNAME>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[WorkBoard クライアント サポート チーム](mailto:support@workboard.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[WorkBoard のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### WorkBoard SSO の構成

**WorkBoard** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [WorkBoard サポート チーム](mailto:support@workboard.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### WorkBoard のテスト ユーザーの作成

このセクションでは、WorkBoard で B.Simon というユーザーを作成します。 [WorkBoard サポート チーム](mailto:support@workboard.com)と連携して、WorkBoard プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる WorkBoard サインオン URL にリダイレクトされます。
- WorkBoard のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した WorkBoard に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [WorkBoard] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した WorkBoard に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workday-inbound-cloud-only-tutorial"} -->
## Microsoft Entra ID で Workday 受信プロビジョニングを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-06
- Summary: Workday から Microsoft Entra ID へのインバウンド プロビジョニングを構成する方法について説明します

この記事の目的は、Workday から Microsoft Entra ID にワーカー データをプロビジョニングするために実行する必要がある手順を示することです。

注

Workday からプロビジョニングするユーザーが、オンプレミスの AD アカウントを必要としないクラウド専用ユーザーである場合は、この記事を使用します。 ユーザーがオンプレミスの AD アカウントのみ、または AD と Microsoft Entra アカウントの両方を必要とする場合は、 [Workday から Active Directory への](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial) ユーザー プロビジョニングの構成に関する記事を参照してください。

次のビデオでは、Workday とのプロビジョニング統合を計画するときに必要な手順の簡単な概要について説明します。

### 概要

ユーザー アカウントをプロビジョニングするために、[Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を [Workday Human Resources API](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html) と統合します。 Microsoft Entra のユーザー プロビジョニング サービスでサポートされている Workday ユーザー プロビジョニング ワークフローは、次の人事管理および ID ライフサイクル管理シナリオを自動化します。

- **新しい従業員の雇用** - Workday に新しい従業員が追加されると、Microsoft Entra ID と、必要に応じて Microsoft 365 や [Microsoft Entra ID によってサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)でユーザー アカウントが自動的に作成され、メール アドレスが Workday に書き戻されます。
- **従業員の属性とプロファイルの更新** - Workday で従業員レコード (名前、職名、マネージャーなど) が更新されると、Microsoft Entra ID と、必要に応じて Microsoft 365 や [Microsoft Entra ID によってサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)でユーザー アカウントが自動的に更新されます。
- **従業員の退職** - Workday で従業員が退職状態になると、Microsoft Entra ID と、必要に応じて Microsoft 365 や [Microsoft Entra ID によってサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)でユーザー アカウントが自動的に無効になります。
- **従業員の再雇用** - Workday で従業員が再雇用されると、Microsoft Entra ID と、必要に応じて Microsoft 365 や [Microsoft Entra ID によってサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に以前のアカウントが (設定に応じて) 自動的に再アクティブ化または再プロビジョニングされます。

#### このユーザー プロビジョニング ソリューションが最適な場合

この Workday から Microsoft Entra へのユーザー プロビジョニング ソリューションは、次の場合に最適です。

- Workday ユーザー プロビジョニング用に事前に構築されたクラウドベースのソリューションを求めている組織
- Workday から Microsoft Entra ID への直接ユーザー プロビジョニングを必要とする組織
- Workday から取得したデータを使用してユーザーをプロビジョニングする必要がある組織
- 電子メールに Microsoft 365 を使用している組織

### ソリューションのアーキテクチャ

このセクションでは、クラウド専用のユーザーに向けた、エンド ツー エンドのユーザー プロビジョニング ソリューションのアーキテクチャについて説明します。 2 つの関連するフローがあります。

- **権限がある人事データのフロー - Workday から Microsoft Entra ID へ:** このフローでは、最初に社員イベント (新規雇用、異動、退職など) が Workday で発生し、イベント データはその後、Microsoft Entra ID に移動します。 イベントによっては、Microsoft Entra ID での作成、更新、有効化、無効化の操作に至る可能性があります。
- **書き戻しフロー – オンプレミスの Active Directory から Workday へ:** Active Directory でアカウントの作成が完了すると、Microsoft Entra Connect を介して Microsoft Entra ID との同期が行われ、メール、ユーザー名、電話番号などの情報を Workday に書き戻すことができます。

    [Image: ワークデーのプロビジョニングの概念図]

#### エンド ツー エンドのユーザー データ フロー

1. HR チームは、Workday Employee Central で雇用者トランザクション (現職者/異動者/退職者または新規雇用/異動/退職) を実行します。
2. Microsoft Entra プロビジョニング サービスは、Workday EC からの、スケジュールされた ID の同期を実行し、オンプレミスの Active Directory との同期のために処理する必要がある変更を識別します。
3. Microsoft Entra ID プロビジョニング サービスは、変更を特定し、Microsoft Entra ID のユーザーに対して作成、更新、有効化、無効化の操作を呼び出します。
4. [Workday Writeback](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-writeback-tutorial) アプリが構成されている場合は、メール、ユーザー名、電話番号などの属性が Microsoft Entra ID から取得されます。
5. Microsoft Entra プロビジョニング サービスによって、メール、ユーザー名、および電話番号が Workday に設定されます。

### デプロイの計画

Workday から Microsoft Entra ID へのクラウド人事駆動型のユーザー プロビジョニングを構成するには、次のようなさまざまな側面をカバーするかなりの計画が必要です。

- 一致する ID の決定
- 属性マッピング
- 属性の変換
- スコープ フィルター

これらのトピックに関する包括的なガイドラインについては、[クラウド人事デプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision)に関するページを参照してください。

### Workday の統合システム ユーザーの構成

Workday 統合システム ユーザー アカウントとワーカー データを取得するためのアクセス許可の作成については、[統合システム ユーザーの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#configure-integration-system-user-in-workday)に関するセクションを参照してください。

### Workday から Microsoft Entra ID へのユーザー プロビジョニングを構成する

以下のセクションでは、クラウドのみのデプロイで Workday から Microsoft Entra ID へのユーザー プロビジョニングを構成する手順について説明します。

- Microsoft Entra プロビジョニング コネクタ アプリケーションの追加と Workday への接続の作成
- Workday と Microsoft Entra の属性マッピングを構成する
- ユーザー プロビジョニングの有効化と起動

#### パート 1: Microsoft Entra プロビジョニング コネクタ アプリケーションの追加と Workday への接続の作成

**クラウド専用ユーザーの Workday から Microsoft Entra へのプロビジョニングを構成するには、次のようにします。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[Workday to Microsoft Entra user Provisioning] (Workday から Microsoft Entra へのユーザー プロビジョニング)** を探し、ギャラリーからそのアプリを追加します。
4. アプリが追加され、アプリの詳細画面が表示されたら、 **[プロビジョニング]** を選択します。
5. **[プロビジョニング** **モード]** を **[自動]** に変更します。
6. 以下のように **[管理者の資格情報]** セクションを完了します。

    - **Workday ユーザー名** – Workday 統合システム アカウントのユーザー名にテナント ドメイン名を追加して入力します。 このようになります。username@contoso4
    - **Workday パスワード** - Workday 統合システム アカウントのパスワードを入力します
    - **Workday Web Services API URL** - テナントの Workday Web サービス エンドポイントへの URL を入力します。 URL によって、コネクタで使用される Workday Web Services API のバージョンが決まります。

        | URL 形式 | 使用される WWS API のバージョン | XPATH の変更が必要 |
        | --- | --- | --- |
        | https://####.workday.com/ccx/service/tenantName | v21.1 | いいえ |
        | https://####.workday.com/ccx/service/tenantName/Human\_Resources | v21.1 | いいえ |
        | https://####.workday.com/ccx/service/tenantName/Human\_Resources/v##.# | v##.# | はい |

        注

        URL にバージョン情報が指定されていない場合、アプリでは Workday Web Services (WWS) v21.1 が使用され、アプリに付属している既定の XPATH API 式の変更は必要ありません。 特定の WWS API バージョンを使用するには、URL の中にバージョン番号を指定します  例: `https://wd3-impl-services1.workday.com/ccx/service/contoso4/Human_Resources/v34.0` WWS API v30.0 以降を使用する場合は、プロビジョニング ジョブを有効にする前に、「**構成の管理**」セクションおよび &gt;を参照して、[\[属性マッピング\] -&gt; \[詳細オプション\] - \[Workday の属性リストの編集\]](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#managing-your-configuration) の下にある [\[XPATH API 式\]](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/workday-attribute-reference#xpath-values-for-workday-web-services-wws-api-v30) を更新してください。
    - **メール通知** - メール アドレスを入力し、[send email if failure occurs](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/失敗した場合にメールを送信する) チェックボックスをオンにします。
    - **[接続のテスト]** ボタンをクリックします。
    - 接続テストが成功した場合、上部の **[保存]** ボタンをクリックします。 失敗した場合は、Workday URL と資格情報が Workday で有効であることを再度確認します。

#### パート 2: Workday と Microsoft Entra の属性マッピングを構成する

このセクションでは、クラウド専用のユーザーについて、Workday から Microsoft Entra ID へのユーザー データの移動方法を構成します。

1. **[マッピング]** の [プロビジョニング] タブで、**[Synchronize Workers to Microsoft Entra ID] (Workers を Microsoft Entra ID に同期する)** をクリックします。
2. **[ソース オブジェクト スコープ]** フィールドでは、属性ベースのフィルター セットを定義して、Microsoft Entra ID へのプロビジョニングの対象にする Workday のユーザー セットを選択できます。 既定のスコープは、"Workday のすべてのユーザー" です。 フィルターの例:

    - 例: 1000000 から 2000000 までの Worker ID を持つユーザーにスコープを設定

        - 属性:WorkerID
        - 演算子:REGEX Match
        - 値:(1[0-9][0-9][0-9][0-9][0-9][0-9])
    - 例:正規従業員ではなく、臨時社員のみ

        - 属性:ContingentID
        - 演算子:IS NOT NULL
3. **[対象オブジェクトのアクション]** フィールドでは、Microsoft Entra ID で実行されるアクションをグローバルにフィルター処理できます。 **作成**と**更新**が最も一般的です。
4. **[属性マッピング]** セクションでは、個別の Workday 属性を Active Directory の属性にマッピングする方法を定義できます。
5. 既存の属性マッピングをクリックして更新するか、または画面の下部にある **[新しいマッピングの追加]** をクリックして、新しいマッピングを追加します。 個々の属性マッピングは、次のプロパティをサポートしています。

    - **マッピングの種類**

        - **ダイレクト** - 変更なしで Workday 属性の値を AD 属性に書き込みます
        - **定数** - 静的な定数文字列の値を AD 属性に書き込みます
        - **式** – 1 つ以上の Workday 属性に基づいて、AD 属性にカスタム値を書き込むことができます。 [詳細については、式に関するこの記事を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)。
    - **ソース属性** - Workday のユーザー属性。 探している属性が存在しない場合は、「[Workday のユーザー属性リストをカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#customizing-the-list-of-workday-user-attributes)」を参照してください。
    - **既定値** – 省略可能。 ソース属性に空の値がある場合、マッピングではこの値が代わりに書き込まれます。 最も一般的な構成では、これを空白のままにします。
    - **ターゲット属性** – Microsoft Entra ID のユーザー属性。
    - **この属性を使用してオブジェクトを照合する** - この属性を使用して、Workday と Microsoft Entra ID 間でユーザーを一意に識別するかどうかを示します。 この値は、通常、Workday の Worker ID フィールドで設定され、一般的に Microsoft Entra ID の従業員 ID 属性 (新規) または拡張属性にマッピングされます。
    - **照合の優先順位** - 一致させる属性を複数設定できます。 複数の場合は、このフィールドで定義された順序で評価されます。 1 件でも一致が見つかると、一致する属性の評価はそれ以上行われません。
    - **このマッピングを適用する**

        - **常に** - このマッピングをユーザーの作成と更新の両方のアクションに適用します
        - **作成中のみ** - このマッピングをユーザーの作成アクションのみに適用します
6. マッピングを保存するには、[属性マッピング] セクションの上部にある **[保存]** をクリックします。

### ユーザー プロビジョニングの有効化と起動

Workday プロビジョニング アプリの構成が完了したら、プロビジョニング サービスを有効にすることができます。

ヒント

既定では、プロビジョニング サービスを有効にすると、スコープ内のすべてのユーザーに対してプロビジョニング操作が開始されます。 マッピングのエラーまたは Workday データの問題がある場合、プロビジョニング ジョブが失敗し、検疫状態になる可能性があります。 これを避けるために、ベスト プラクティスとして、すべてのユーザーの完全同期を開始する前に、**[ソース オブジェクト スコープ]** フィルターを構成し、少数のテスト ユーザーで属性マッピングをテストすることをお勧めします。 マッピングが機能し、目的の結果が得られていることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

1. **[プロビジョニング]** タブで、 **[プロビジョニングの状態]** を **[ON]** に設定します。
2. **[保存]** をクリックします。
3. この操作により初期同期が開始されます。これに要する時間は Workday テナントのユーザー数に応じて変わります。 進行状況バーをチェックして、同期サイクルの進行状況を追跡できます。
4. 好きなときに、Microsoft Entra 管理センターの **[プロビジョニング]** タブをチェックして、プロビジョニング サービスで実行されたアクションを確認します。 プロビジョニング ログには、Workday から読み込まれたユーザーや、その後 Microsoft Entra ID に追加または更新されたユーザーなど、プロビジョニング サービスによって実行された個々の同期イベントがすべて表示されます。
5. 最初の同期が完了すると、次に示すように、 **[プロビジョニング]** タブに監査概要レポートが書き込まれます。

[Image: プロビジョニングの進行状況バーのスクリーンショット]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workday-inbound-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Workday を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-06-18
- Summary: Workday に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、Workday からオンプレミス Active Directory (AD) に worker プロファイルをプロビジョニングするために実行する必要がある手順を示することです。

注

Workday からプロビジョニングするユーザーにオンプレミスの AD アカウントと Microsoft Entra アカウントが必要な場合は、この記事を使用します。

- Workday のユーザーが Microsoft Entra アカウント (クラウド専用ユーザー) のみを必要とする場合は、 [Workday から Microsoft Entra ID への](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial) ユーザー プロビジョニングの構成に関する記事を参照してください。
- Microsoft Entra ID から Workday への電子メール アドレス、ユーザー名、電話番号などの属性の書き戻しを構成するには、 [Workday ライトバックの構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-writeback-tutorial)に関する記事を参照してください。

次のビデオでは、Workday とのプロビジョニング統合を計画するときに必要な手順の簡単な概要について説明します。

### 概要

[Microsoft Entra ユーザー プロビジョニング サービス](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)は、ユーザー アカウントをプロビジョニングするために [Workday Human Resources API](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html) と統合されます。 Microsoft Entra のユーザー プロビジョニング サービスでサポートされている Workday ユーザー プロビジョニング ワークフローは、次の人事管理および ID ライフサイクル管理シナリオを自動化します。

- **新しい従業員の雇用** - 新しい従業員が Workday に追加されると、Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされているその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)にユーザー アカウントが自動的に作成され、IT が管理する連絡先情報が Workday に書き戻されます。
- **従業員属性とプロファイルの更新** - Workday で従業員レコード (名前、タイトル、マネージャーなど) が更新されると、ユーザー アカウントは Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされるその他の SaaS アプリケーションで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)自動的に更新されます。
- **従業員の退職** - Workday で従業員が退職すると、Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされているその他の SaaS アプリケーションで](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)、ユーザー アカウントが自動的に無効になります。
- **従業員の再雇用** - Workday で従業員が再雇用されると、古いアカウントを Active Directory、Microsoft Entra ID、および必要に応じて Microsoft 365 および [Microsoft Entra ID でサポートされるその他の SaaS アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に自動的に再アクティブ化または再プロビジョニングできます (ユーザー設定に応じて)。

#### 新着情報

このセクションでは、最新の Workday 統合の機能強化について説明します。 包括的な更新プログラム、計画された変更、アーカイブの一覧については、「[Microsoft Entra ID の新機能](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)」を参照してください。

- **2020 年 10 月 - Workday のオンデマンド プロビジョニングが有効になりました。**[オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用すると、Workday で特定のユーザー プロファイルのエンド ツー エンド プロビジョニングをテストして、属性マッピングと式ロジックを確認できるようになりました。
- **2020 年 5 月 - Workday に電話番号を書き戻す機能:** メールとユーザー名に加えて、Microsoft Entra ID から Workday に職場の電話番号と携帯電話番号を書き戻すようになりました。 詳細については、 [ライトバック アプリのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-writeback-tutorial)を参照してください。
- **2020 年 4 月 - Workday Web Services (WWS) API の最新バージョンのサポート:** Workday では、3 月と 9 月に年 2 回、ビジネス目標の達成と従業員の需要の変化に役立つ機能豊富な更新プログラムを提供しています。 Workday が提供する新機能を活用していただくため、使用する WWS API バージョンを接続 URL で直接指定できます。 Workday API バージョンを指定する方法の詳細については、 Workday 接続の構成に関するセクションを参照してください。

#### このユーザー プロビジョニング ソリューションが最適な場合

この Workday ユーザー プロビジョニング ソリューションは、次のお客様に最適です。

- Workday ユーザー プロビジョニング用に事前に構築されたクラウドベースのソリューションを求めている組織
- Workday から Active Directory、または Microsoft Entra ID への直接ユーザー プロビジョニングを必要とする組織
- Workday HCM モジュールから取得したデータを使用してユーザーをプロビジョニングする必要がある組織 ( [Get_Workers](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html)を参照)
- Workday HCM モジュールで検出された変更のみに基づいて、ユーザーの参加、移動、および離脱の動作を自動的に 1 つ以上の Active Directory フォレスト、ドメイン、OU に同期させる必要がある組織 ([Get_Workers](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html)を参照)
- 電子メールに Microsoft 365 を使用している組織

### ソリューションのアーキテクチャ

このセクションでは、一般的なハイブリッド環境に向けた、エンド ツー エンドのユーザー プロビジョニング ソリューションのアーキテクチャについて説明します。 2 つの関連するフローがあります。

- **権限のある HR データ フロー – Workday からオンプレミス Active Directory へ:** このフローでは、新規採用、転送、退職などの worker イベントが最初にクラウド Workday HR テナントで発生し、次にイベント データが Microsoft Entra ID とプロビジョニング エージェントを介してオンプレミスの Active Directory に流れます。 イベントによっては、AD での作成/更新/有効化/無効化の操作に至る可能性があります。
- **ライトバック フロー – オンプレミスの Active Directory から Workday へ:** Active Directory でアカウントの作成が完了すると、Microsoft Entra Connect を介して Microsoft Entra ID と同期され、メール、ユーザー名、電話番号などの情報を Workday に書き戻すことができます。

[Image: 概要の概念図。]

#### エンド ツー エンドのユーザー データ フロー

1. 人事チームは、Workday HCM で社員のトランザクション (参加者/異動者/休暇者または新規雇用/移動/退職) を実行します
2. Microsoft Entra プロビジョニング サービスは、Workday HR からの、スケジュールされた ID の同期を実行し、オンプレミスの Active Directory との同期のために処理する必要がある変更を識別します。
3. Microsoft Entra プロビジョニング サービスは、AD アカウントの作成/更新/有効化/無効化の操作を含む要求ペイロードを使用して、オンプレミスの Microsoft Entra Connect プロビジョニング エージェントを呼び出します。
4. Microsoft Entra Connect プロビジョニング エージェントは、サービス アカウントを使用して AD アカウントのデータを追加/更新します。
5. Microsoft Entra Connect (AD の同期エンジン) は、デルタ同期を実行して AD 内の更新をプルします。
6. Active Directory の更新は、Microsoft Entra ID と同期されます。
7. [Workday Writeback](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-writeback-tutorial) アプリが構成されている場合、電子メール、ユーザー名、電話番号などの属性が Workday に書き戻されます。

### デプロイの計画

Workday から Active Directory へのユーザー プロビジョニングを構成するには、次のようなさまざまな側面をカバーする膨大な計画が必要です。

- Microsoft Entra Connect プロビジョニング エージェントの設定
- デプロイする Workday から AD へのユーザー プロビジョニング アプリの数
- 一致する正しい識別子、属性マッピング、変換、スコープ フィルターの選択

包括的なガイドラインと推奨されるベスト プラクティスについては、 [クラウド人事デプロイ計画](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/plan-cloud-hr-provision) を参照してください。

### Workday の統合システム ユーザーの構成

すべての Workday プロビジョニング コネクタに共通する要件は、Workday Human Resources API に接続するために、Workday 統合システム ユーザーの資格情報を必要とすることです。 このセクションでは、Workday で統合システムのユーザーを作成する方法について説明します。次のセクションがあります。

- 統合システム・ユーザーの作成
- 統合セキュリティ グループの作成
- ドメイン セキュリティ ポリシーのアクセス許可の構成
- ビジネス プロセス セキュリティ ポリシーのアクセス許可の構成
- セキュリティ ポリシーの変更のアクティブ化

注

この手順を省略し、代わりに Workday 管理者アカウントをシステム統合アカウントとして使用することもできます。 これはデモでは問題なく動作するかもしれませんが、本番環境での展開にはお勧めできません。

#### 統合システム ユーザーの作成

**統合システム ユーザーを作成するには:**

1. 管理者アカウントを使用して Workday テナントにサインインします。 **Workday アプリケーション**で、検索ボックスに「ユーザーの作成」と入力し、[**統合システム ユーザーの作成**] をクリックします。

[Image: ユーザーの作成のスクリーンショット。]
2. 新しい **統合システム・ユーザー** のユーザー名とパスワードを指定して、「統合システム・ユーザーの作成」タスクを完了します。

    - [ **次のサインイン時に新しいパスワードを要求する** ] オプションはオフのままにします。このユーザーはプログラムでログオンしているためです。
    - ユーザーのセッションが途中でタイムアウトするのを防ぐ、セッション **タイムアウト分** の既定値は 0 のままにします。
    - 統合システムのパスワードを持つユーザーが Workday にログインできないようにするセキュリティレイヤーが追加されているため、[ **UI セッションを許可しない** ] オプションを選択します。

[Image: 統合システム ユーザーの作成のスクリーンショット。]

#### 統合セキュリティ グループの作成

この手順では、Workday 内に、制約のない、または制約付きの統合システム セキュリティ グループを作成し、このグループに、前の手順で作成した統合システム ユーザーを割り当てます。

**セキュリティ グループを作成するには:**

1. 検索ボックスに「セキュリティ グループの作成」と入力し、[ **セキュリティ グループの作成**] をクリックします。

[Image: 検索ボックスに入力された]
2. **[セキュリティ グループの作成**] タスクを完了します。

    - Workday のセキュリティ グループには次の 2 種類があります。

        - **無制限：** セキュリティ グループのすべてのメンバーは、そのグループによって保護されているすべてのデータ インスタンスにアクセスできます。
        - **制約：** すべてのセキュリティ グループ メンバーは、セキュリティ グループがアクセスできるデータ インスタンス (行) のサブセットにコンテキスト アクセスできます。
    - 統合に適したセキュリティ グループの種類を選択するには、Workday 統合パートナーに確認してください。
    - グループの種類がわかったら、[テナントされたセキュリティ グループの種類] ドロップダウンから [**統合システム セキュリティ グループ (制約なし)]** または **[統合システム セキュリティ グループ (制約あり)]** を選択します。

[Image: CreateSecurity グループのスクリーンショット。]
3. セキュリティ グループの作成が成功した後、セキュリティ グループにメンバーを割り当てることができるページが表示されます。 前の手順で作成した新しい統合システム ユーザーをこのセキュリティ グループに追加します。 "制約付き" セキュリティ グループを使用する場合は、適切な組織の範囲を選択する必要があります。

[Image: [セキュリティ グループの編集] のスクリーンショット。]

#### ドメイン セキュリティ ポリシーのアクセス許可の構成

この手順では、セキュリティ グループに、社員データについての "ドメイン セキュリティ" ポリシーのアクセス許可を付与します。

**ドメイン セキュリティ ポリシーのアクセス許可を構成するには:**

1. 検索ボックスに **「セキュリティ グループのメンバーシップとアクセス」** と入力し、レポート リンクをクリックします。

[Image: Search セキュリティ グループ メンバーシップのスクリーンショット。]
2. 前の手順で作成したセキュリティ グループを検索して選択します。

[Image: [セキュリティ グループの選択] のスクリーンショット。]
3. グループ名の横にある省略記号 (`...`) をクリックし、メニューの [**セキュリティ グループ] &gt; [セキュリティ グループのドメインアクセス許可の維持**] を選択します

[Image: [ドメインのアクセス許可を管理] のスクリーンショット。]
4. [**統合のアクセス許可]** で、**Put アクセスを許可するドメイン セキュリティ ポリシー**の一覧に次のドメインを追加します

    - *外部アカウントのプロビジョニング*
    - *ワーカー データ: パブリック ワーカー レポート*
    - *個人データ: 職場の連絡先情報* (Microsoft Entra ID から Workday に連絡先データを書き戻す場合に必要)
    - *Workday アカウント* (Microsoft Entra ID から Workday にユーザー名/UPN を書き戻す場合に必要)
5. [**統合のアクセス許可]** で、アクセスを許可する**ドメイン セキュリティ ポリシー**の一覧に次のドメインを追加します

    - *労働者データ: 労働者*
    - *ワーカーデータ: すべての職位*
    - *ワーカー データ: 現在のスタッフ情報*
    - *ワーカーデータ: ワーカープロファイルのビジネスタイトル*
    - *ワーカーデータ: 有資格作業者* (省略可能 - プロビジョニング用のワーカー資格データを取得するためにこれを追加)
    - *Worker データ: スキルとエクスペリエンス* (省略可能 - プロビジョニング用の worker スキル データを取得するためにこれを追加)
6. 以上の手順が完了すると、次のようなアクセス許可画面が表示されます。

[Image: すべてのドメインセキュリティ権限のスクリーンショット。]
7. 次の画面で [ **OK] を** クリックし **、[完了]** をクリックして構成を完了します。

#### ビジネス プロセス セキュリティ ポリシーのアクセス許可の構成

この手順では、セキュリティ グループに、社員データについての "ビジネス プロセス セキュリティ" ポリシーのアクセス許可を付与します。

注

この手順は、Workday Writeback アプリのコネクタを設定するためにのみ必要です。

**ビジネス プロセス セキュリティ ポリシーのアクセス許可を構成するには:**

1. 検索ボックスに **「業務プロセス ポリシー** 」と入力し、[ **ビジネス プロセス セキュリティ ポリシーの編集** ] タスクのリンクをクリックします。

[Image: 検索ボックスに [ビジネス プロセス ポリシー] と [ビジネス プロセス セキュリティ ポリシーの編集 - タスク] が選択されていることを示すスクリーンショット。]
2. [ **業務プロセスの種類** ] ボックスで、[ *連絡先* ] を検索し、[ **Work Contact Change** Business Process] を選択し、[OK] をクリック **します**。

[Image: [ビジネス プロセス のセキュリティ ポリシーの編集] ページと、[ビジネス プロセスの種類] メニューで選択された [Work Contact Change](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/勤務先の連絡先の変更) を示すスクリーンショット。]
3. [ **ビジネス プロセス セキュリティ ポリシーの編集** ] ページで、[ **仕事用連絡先情報の変更 (Web サービス)]** セクションまでスクロールします。
4. 新しい統合システム セキュリティ グループを選択し、Web サービス要求を開始できるセキュリティ グループの一覧に追加します。

[Image: ビジネス プロセス セキュリティ ポリシーのスクリーンショット。]
5. **完了**をクリックします。

#### セキュリティ ポリシーの変更のアクティブ化

**セキュリティ ポリシーの変更をアクティブにするには:**

1. 検索ボックスに「アクティブ化」と入力し、[保留中の **セキュリティ ポリシーの変更をアクティブ化**する] リンクをクリックします。

[Image: 「Activate」のスクリーンショット。]
2. 監査目的でコメントを入力して、[保留中のセキュリティ ポリシー変更のアクティブ化] タスクを開始し、[OK] をクリック **します**。
3. 次の画面で[ **確認**]チェックボックスをオンにしてタスクを完了し、[ **OK]**をクリックします。

[Image: [保留中のセキュリティのアクティブ化] のスクリーンショット。]

### プロビジョニング エージェントのインストールの前提条件

次のセクションに進む前に [、プロビジョニング エージェントのインストールの前提条件](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/how-to-prerequisites) を確認します。

### Workday から Active Directory へのユーザー プロビジョニングの構成

このセクションでは、Workday から、統合の範囲内にある各 Active Directory ドメインへのユーザー アカウントのプロビジョニングの手順について説明します。

- プロビジョニング コネクタ アプリを追加し、プロビジョニング エージェントをダウンロードする
- オンプレミスのプロビジョニング エージェントをインストールして構成する
- Workday と Active Directory への接続を構成する
- 属性マッピングの構成
- ユーザー プロビジョニングを有効にして起動する

#### パート 1: プロビジョニング コネクタ アプリを追加し、プロビジョニング エージェントをダウンロードする

**Workday から Active Directory へのプロビジョニングを構成するには:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **Workday から Active Directory へのユーザー プロビジョニングを**検索し、ギャラリーからそのアプリを追加します。
4. アプリが追加され、アプリの詳細画面が表示されたら、[ **プロビジョニング**] を選択します。
5. **[プロビジョニング** **モード**] を **[自動**] に変更します。
6. 表示された情報バナーをクリックして、プロビジョニング エージェントをダウンロードします。

[Image: エージェントのダウンロードのスクリーンショット。]

#### パート 2: オンプレミス プロビジョニング エージェントのインストールと構成

オンプレミスの Active Directory にプロビジョニングするには、目的の Active Directory ドメインへのネットワーク アクセスを備えたドメイン参加済みのサーバーに、プロビジョニング エージェントがインストールされている必要があります。

ダウンロードしたエージェント インストーラーをサーバー ホストに転送し、「[**エージェントのインストール**」セクションに](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/how-to-install)記載されている手順に従ってエージェントの構成を完了します。

#### パート 3: プロビジョニング アプリで Workday と Active Directory への接続を構成する

この手順では、Workday および Active Directory との接続を確立します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **パート 1** で作成した &gt;&gt; Workday to Active Directory ユーザー プロビジョニング アプリを参照します。
3. 次のように、[ **管理者資格情報]** セクションに入力します。

    - **Workday ユーザー名** – テナント ドメイン名が追加された Workday 統合システム アカウントのユーザー名を入力します。 次のようになります: **username@tenant\_name**
    - **Workday パスワード –** Workday 統合システム アカウントのパスワードを入力します
    - **Workday Web Services API URL –** テナントの Workday Web サービス エンドポイントの URL を入力します。 URL によって、コネクタで使用される Workday Web Services API のバージョンが決まります。

        | URL 形式 | 使用される WWS API のバージョン | XPATH の変更が必要 |
        | --- | --- | --- |
        | https://####.workday.com/ccx/service/tenantName | v21.1 | いいえ |
        | https://####.workday.com/ccx/service/tenantName/Human\_Resources | v21.1 | いいえ |
        | https://####.workday.com/ccx/service/tenantName/Human\_Resources/v##.# | v##.# | はい |

        注

        URL にバージョン情報が指定されていない場合、アプリでは Workday Web Services (WWS) v21.1 が使用され、アプリに付属している既定の XPATH API 式の変更は必要ありません。 特定の WWS API バージョンを使用するには、URL の中にバージョン番号を指定します  例: `https://wd3-impl-services1.workday.com/ccx/service/contoso4/Human_Resources/v34.0` WWS API v30.0 以降を使用している場合は、プロビジョニング ジョブを有効にする前に、[&gt;] セクションと [&gt;] セクションを参照する Workday の属性リストの編集で **XPATH API 式**を更新してください。
    - **Active Directory フォレスト -** エージェントに登録されている Active Directory ドメインの "名前" です。 ドロップダウンを使用して、プロビジョニングのターゲット ドメインを選択します。 通常、この値は次のような文字列です: *contoso.com*
    - **Active Directory コンテナー -** エージェントが既定でユーザー アカウントを作成するコンテナー DN を入力します。 例: *OU=Standard Users,OU=Users,DC=contoso,DC=test*

        注

        この設定は、 *parentDistinguishedName* 属性が属性マッピングで構成されていない場合にのみ、ユーザー アカウントの作成で有効になります。 この設定は、ユーザーの検索や更新の操作には使用されません。 ドメインのサブツリー全体が、検索操作の範囲内になります。
    - **通知メール –** メール アドレスを入力し、[失敗した場合にメールを送信する] チェック ボックスをオンにします。

        注

        プロビジョニング ジョブが [検疫](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) 状態になった場合、Microsoft Entra プロビジョニング サービスは電子メール通知を送信します。
    - [ **テスト接続** ] ボタンをクリックします。 接続テストが成功した場合は、上部にある **[保存** ] ボタンをクリックします。 失敗する場合は、エージェントのセットアップで構成された Workday 資格情報と AD 資格情報が有効であることを再確認します。
    - 資格情報が正常に保存されると、[**マッピング**] セクションに既定のマッピングの **[Synchronize Workday Workers to On Premises Active Directory]\(Workday Workers をオンプレミス Active Directory に同期**する\) が表示されます

#### パート 4:属性マッピングの構成

このセクションでは、ユーザー データが Workday から Active Directory に移動する方法を構成します。

1. [ **マッピング**] の [プロビジョニング] タブで、[ **Workday Worker をオンプレミス Active Directory に同期**する] をクリックします。
2. [ **ソース オブジェクト スコープ]** フィールドでは、属性ベースのフィルターのセットを定義することで、AD へのプロビジョニングのスコープに含める Workday のユーザー セットを選択できます。 既定のスコープは、"Workday のすべてのユーザー" です。 フィルターの例:

    - 例: 1000000 から 2000000 (2000000 を除く) までの Worker ID を持つユーザーにスコープを設定

        - 属性:WorkerID
        - 演算子:REGEX Match
        - 値:(1[0-9][0-9][0-9][0-9][0-9][0-9])
    - 例:臨時社員ではなく、従業員のみ

        - 属性:EmployeeID
        - 演算子: NULL でない

    ヒント

    初めてプロビジョニング アプリを構成するときは、属性マッピングと式をテストして検証し、目的の結果が得られていることを確認する必要があります。 Microsoft では、[ソース オブジェクト スコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)と**オンデマンド プロビジョニング**で[スコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、Workday のいくつかのテスト ユーザーとマッピングをテストすることをお勧めします。 マッピングが機能していることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

注意事項

プロビジョニング エンジンの既定の動作では、スコープ外に出るユーザーが無効化または削除されます。 これはご使用の Workday と AD の統合には望ましくない場合があります。 この既定の動作をオーバーライドするには、[記事「スコープ外のユーザー アカウントの削除をスキップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/skip-out-of-scope-deletions)」を参照してください
3. [ **ターゲット オブジェクト アクション]** フィールドでは、Active Directory で実行されるアクションをグローバルにフィルター処理できます。 **作成** と **更新** が最も一般的です。
4. [ **属性マッピング** ] セクションでは、個々の Workday 属性を Active Directory 属性にマップする方法を定義できます。
5. 既存の属性マッピングをクリックして更新するか、画面の下部にある [ **新しいマッピングの追加** ] をクリックして新しいマッピングを追加します。 個々の属性マッピングは、次のプロパティをサポートしています。

    - **マッピングの種類**

        - **Direct** – Workday 属性の値を AD 属性に書き込みますが、変更はありません。
        - **定数** - 静的な定数文字列値を AD 属性に書き込む
        - **式** – 1 つ以上の Workday 属性に基づいて、AD 属性にカスタム値を書き込みます。 [詳細については、式に関するこの記事を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)。
    - **ソース属性** - Workday のユーザー属性。 探している属性が存在しない場合は、 Workday ユーザー属性の一覧のカスタマイズを参照してください。
    - **既定値** – 省略可能。 ソース属性に空の値がある場合、マッピングではこの値が代わりに書き込まれます。 最も一般的な構成では、これを空白のままにします。
    - **ターゲット属性** – Active Directory のユーザー属性。
    - **この属性を使用してオブジェクトを照合** します。このマッピングを使用して、Workday と Active Directory の間でユーザーを一意に識別する必要があるかどうか。 この値は、通常、Workday の Worker ID フィールドで設定され、通常は Active Directory の従業員 ID 属性のいずれかにマップされます。
    - **一致する優先順位** – 複数の一致する属性を設定できます。 複数の場合は、このフィールドで定義された順序で評価されます。 1 件でも一致が見つかると、一致する属性の評価はそれ以上行われません。
    - **このマッピングを適用する**

        - **常に** – ユーザー作成アクションと更新アクションの両方にこのマッピングを適用する
        - **作成時のみ** - ユーザー作成アクションにのみこのマッピングを適用する
6. マッピングを保存するには、[Attribute-Mapping] セクションの上部にある [ **保存** ] をクリックします。

[Image: [保存] アクションが選択されている [属性マッピング] ページを示すスクリーンショット。]

##### Workday と Active Directory との間の属性マッピングの例と、一般的に使用される式を次に示します

- *parentDistinguishedName* 属性にマップされる式は、1 つ以上の Workday ソース属性に基づいてユーザーを別の OU にプロビジョニングするために使用されます。 この例では、所在する市区町村に基づいて、異なる OU にユーザーを配置しています。
- Active Directory の *userPrincipalName* 属性は、重複除去関数 [SelectUniqueValue](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#selectuniquevalue) を使用して生成されます。この関数は、ターゲット AD ドメインに生成された値が存在するかどうかをチェックし、一意の場合にのみ設定します。
- [ここに式の記述に関するドキュメントがあります](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data)。 このセクションでは、特殊文字を削除する方法の例についても紹介しています。

| WORKDAY 属性 | ACTIVE DIRECTORY 属性 | ID 一致の有無 | 作成/更新 |
| --- | --- | --- | --- |
| **WorkerID** | 従業員ID | **はい** | 作成時のみ書き込まれる |
| **PreferredNameData** | cn |  | 作成時のみ書き込まれる |
| **SelectUniqueValue( Join("@", Join("." , [FirstName], [LastName]), "contoso.com"), Join("@", Join(".", Mid([FirstName], 1, 1), [LastName]), "contoso.com"), Join("@", Join(".", Mid([FirstName], 1, 2), [LastName]), "contoso.com"))** | ユーザープリンシパル名 |  | 作成時のみ書き込まれる |
| `Replace(Mid(Replace([UserID], , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , )` | sAMAccountName (ユーザーログオン名) |  | 作成時のみ書き込まれる |
| **Switch([Active], , "0", "真", "1", "偽")** | アカウントが無効化されました |  | 作成時 + 更新時 |
| **FirstName** | givenName |  | 作成時 + 更新時 |
| **LastName** | エスエヌ |  | 作成時 + 更新時 |
| **PreferredNameData** | 表示名 |  | 作成時 + 更新時 |
| **会社** | 会社 |  | 作成時 + 更新時 |
| **監督組織** | 部 |  | 作成時 + 更新時 |
| **ManagerReference** | マネージャー |  | 作成時 + 更新時 |
| **BusinessTitle** | タイトル |  | 作成時 + 更新時 |
| **AddressLineData** | 住所 |  | 作成時 + 更新時 |
| **自治体** | l |  | 作成時 + 更新時 |
| **CountryReferenceTwoLetter** | 会社 |  | 作成時 + 更新時 |
| **CountryReferenceTwoLetter** | c |  | 作成時 + 更新時 |
| **CountryRegionReference** | 聖 |  | 作成時 + 更新時 |
| **WorkSpaceReference** | 物理配送オフィス名 |  | 作成時 + 更新時 |
| **PostalCode** | 郵便番号 |  | 作成時 + 更新時 |
| **勤務先主要電話番号** | 電話番号 |  | 作成時 + 更新時 |
| **ファックス** | ファクシミリ電話番号 |  | 作成時 + 更新時 |
| **モバイル** | 携帯電話 |  | 作成時 + 更新時 |
| **LocalReference** | 優先言語 |  | 作成時 + 更新時 |
| **Switch([市区町村], "OU=Default Users,DC=contoso,DC=com", "Dallas", "OU=Dallas,OU=Users,DC=contoso,DC=com", "Austin", "OU=Austin,OU=Users,DC=contoso,DC=com", "Seattle", "OU=Seattle,OU=Users,DC=contoso,DC=com", "London", "OU=London,OU=Users,DC=contoso,DC=com")** | 親ディスティングイッシュド・ネーム |  | 作成時 + 更新時 |

属性マッピングの構成が完了したら、オンデマンド プロビジョニングを使用して 1 人のユーザーのプロビジョニング [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand) テストし、 ユーザー プロビジョニング サービスを有効にして起動できます。

### ユーザー プロビジョニングの有効化と起動

Workday プロビジョニング アプリの構成が完了し、オンデマンド プロビジョニングを使用して 1 人のユーザーのプロビジョニング [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)確認したら、プロビジョニング サービスを有効にすることができます。

ヒント

既定では、プロビジョニング サービスを有効にすると、スコープ内のすべてのユーザーに対してプロビジョニング操作が開始されます。 マッピングのエラーまたは Workday データの問題がある場合、プロビジョニング ジョブが失敗し、検疫状態になる可能性があります。 これを回避するには、ベスト プラクティスとして、すべてのユーザーの完全同期を起動する前に、**オンデマンド プロビジョニング**を使用して、ソース [オブジェクト スコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand) フィルターを構成し、少数のテスト ユーザーで属性マッピングをテストすることをお勧めします。 マッピングが機能し、目的の結果が得られていることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

1. [ **プロビジョニング** ] ブレードに移動し、[ **プロビジョニングの開始**] をクリックします。
2. この操作により初期同期が開始されます。これに要する時間は Workday テナントのユーザー数に応じて変わります。 進行状況バーをチェックして、同期サイクルの進行状況を追跡できます。
3. いつでも、Microsoft Entra 管理センターの [ **プロビジョニング** ] タブで、プロビジョニング サービスが実行したアクションを確認します。 プロビジョニング ログには、Workday から読み込まれたユーザーや、その後 Active Directory に追加または更新されたユーザーなど、プロビジョニング サービスによって実行された個々の同期イベントがすべて表示されます。 プロビジョニング ログを確認してプロビジョニング エラーを修正する方法の手順については、トラブルシューティングに関するセクションを参照してください。
4. 初期同期が完了すると、次に示すように、[ **プロビジョニング** ] タブに監査概要レポートが書き込まれます。

[Image: プロビジョニングの進行状況バーのスクリーンショット]

### よくあるご質問 (FAQ)

- **ソリューション機能に関する質問**

    - Workday から新入社員を処理する場合、ソリューションで Active Directory の新しいユーザー アカウントのパスワードはどのように設定されますか?
    - プロビジョニング操作が完了した後の電子メール通知の送信は、ソリューションでサポートされていますか?
    - ソリューションは、Microsoft Entra クラウドまたはプロビジョニング エージェント レイヤーで Workday ユーザー プロファイルをキャッシュしますか?
    - このソリューションでは、ユーザーへのオンプレミス AD グループの割り当てをサポートしていますか?
    - ソリューションで Workday worker プロファイルのクエリと更新に使用される Workday API はどれですか?
    - 2 つの Microsoft Entra テナントで Workday HCM テナントを構成できますか?
    - Workday と Microsoft Entra の統合に関連する機能強化を提案したり、新機能を要求したりするにはどうすればよいですか?
- **プロビジョニング エージェントに関する質問**

    - プロビジョニング エージェントの GA バージョンは何ですか?
    - プロビジョニング エージェントのバージョンを確認する方法
    - Microsoft はプロビジョニング エージェントの更新プログラムを自動的にプッシュしますか?
    - Microsoft Entra Connect を実行している同じサーバーにプロビジョニング エージェントをインストールできますか?
    - 送信 HTTP 通信にプロキシ サーバーを使用するようにプロビジョニング エージェントを構成するにはどうすればよいですか?
    - プロビジョニング エージェントが Microsoft Entra テナントと通信でき、ファイアウォールがエージェントに必要なポートをブロックしないようにするにはどうすればよいですか?
    - プロビジョニング エージェントに関連付けられているドメインを登録解除するにはどうすればよいですか?
    - プロビジョニング エージェントをアンインストールする方法
- **Workday から AD への属性マッピングと構成に関する質問**

    - Workday Provisioning 属性マッピングとスキーマの作業コピーをバックアップまたはエクスポートするにはどうすればよいですか?
    - Workday と Active Directory にカスタム属性があります。 カスタム属性と連携するようにソリューションを構成するにはどうすればよいですか。
    - Workday から Active Directory にユーザーの写真をプロビジョニングできますか?
    - 一般使用に対するユーザーの同意に基づいて Workday の携帯電話番号を同期するにはどうすればよいですか?
    - ユーザーの部署/国/市区町村の属性に基づいて AD の表示名を書式設定し、地域の差異を処理するにはどうすればよいですか?
    - SelectUniqueValue を使用して samAccountName 属性の一意の値を生成するにはどうすればよいですか?
    - 分音記号を含む文字を削除し、通常の英語のアルファベットに変換するにはどうすればよいですか?

#### ソリューションの機能に関する質問

##### Workday の新規採用者を処理するときに、ソリューションは Active Directory の新規ユーザー アカウントのパスワードをどのように設定しますか。

オンプレミス プロビジョニング エージェントで、新しい AD アカウントの作成要求を受け取ると、AD サーバーによって定義されたパスワードの複雑さの要件を満たすように設計された複雑なランダム パスワードが自動的に生成され、それがユーザー オブジェクトに設定されます。 このパスワードはどこにも記録されません。

##### ソリューションは、プロビジョニング操作の完了後にメール通知を送信することをサポートしていますか。

いいえ。プロビジョニング操作の完了後のメール通知送信は、現在のリリースではサポートされていません。

##### ソリューションでは、Microsoft Entra クラウドまたはプロビジョニング エージェント レイヤーに Workday ユーザープロファイルがキャッシュされますか。

いいえ。このソリューションではユーザー プロファイルのキャッシュが保持されません。 Microsoft Entra プロビジョニング サービスは単なるデータ プロセッサとして機能し、Workday からデータを読み取り、ターゲット Active Directory または Microsoft Entra に書き込みます。 ユーザーのプライバシーとデータ保持に関する詳細については、「 個人データの管理 」セクションを参照してください。

##### ソリューションは、オンプレミスの AD グループをユーザーに割り当てることをサポートしていますか。

この機能は現在サポートされていません。 推奨される回避策は、Microsoft Graph API エンドポイントにクエリを実行して [ログ データをプロビジョニング](https://learn.microsoft.com/ja-jp/graph/api/resources/azure-ad-auditlog-overview) し、グループの割り当てなどのシナリオをトリガーするために使用する PowerShell スクリプトをデプロイすることです。 この PowerShell スクリプトは、タスク スケジューラにアタッチして、プロビジョニング エージェントを実行している同じボックスにデプロイできます。

##### Workday の従業員プロファイルのクエリと更新にこのソリューションが使用する Workday API はどれですか。

現在、このソリューションは次の Workday API を使用しています。

- [**管理者資格情報]** セクションで使用される **Workday Web サービス API URL** 形式によって、Get\_Workersに使用される API のバージョンが決まります

    - URL の形式が https://####.workday.com/ccx/service/tenantName の場合は、API v21.1 が使用されます。
    - URL の形式が https://####.workday.com/ccx/service/tenantName/Human\_Resources の場合は、API v21.1 が使用されます。
    - URL 形式が https://####.workday.com/ccx/service/tenantName/Human\_Resources/v##.# の場合は、指定された API バージョンが使用されます。 (例: v34.0 が指定されると、これが使用されます)。
- Workday メール書き戻し機能は Change\_Work\_Contact\_Information (v30.0) を使用します
- Workday ユーザー名書き戻し機能は Update\_Workday\_Account (v31.2) を使用します

##### 2 つの Microsoft Entra テナントを持つ Workday HCM テナントは構成できますか。

はい。この構成はサポートされています。 このシナリオを構成する手順の概要を次に示します。

- プロビジョニング エージェント 1 をデプロイし、Microsoft Entra テナント 1 に登録します。
- プロビジョニング エージェント 2 をデプロイし、Microsoft Entra テナント 2 に登録します。
- 各プロビジョニング エージェントが管理する "子ドメイン" に基づいて、各エージェントとドメインを構成します。 1 つのエージェントで複数のドメインを処理できます。
- Microsoft Entra 管理センターで、各テナントの Workday to AD User Provisioning アプリを設定し、それぞれのドメインと共に構成します。

##### Workday と Microsoft Entra の統合に関連した改善を提案したり、新しい機能を依頼したりするにはどうすればよいですか。

今後のリリースや機能強化の方向性を決める上でお客様のフィードバックはとても貴重です。 Microsoft [Entra ID のフィードバック フォーラム](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)で、すべてのフィードバックを歓迎し、アイデアや改善の提案を送信することをお勧めします。 Workday 統合に関連する特定のフィードバックについては、 *カテゴリ SaaS アプリケーション* を選択し、Workday キーワードを使用して検索し、 *Workday* に関連する既存のフィードバックを見つけます。

[Image: UserVoice Workday のスクリーンショット。]

新しいアイデアを提案するときは、他のユーザーが既に同様の機能を提案しているかどうかを確認してください。 その場合は、機能や機能強化のリクエストに賛成を投票することができます。 また、特定のユース ケースについてコメントを残してアイデアを支持していることを示し、その機能がお客様にとってどのように価値があるかを示すこともできます。

#### プロビジョニング エージェントに関する質問

##### プロビジョニング エージェントの一般提供 (GA) バージョンは何ですか。

プロビジョニング エージェントの最新の GA バージョンについては、 [Microsoft Entra Connect プロビジョニング エージェントのバージョン リリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provisioning-agent-release-version-history) を参照してください。

##### プロビジョニング エージェントのバージョンを確認する方法を教えてください。

1. プロビジョニング エージェントがインストールされている Windows サーバーにサインインします。
2. **[コントロール パネル**]&gt;**[プログラムのインストールを取り消す]または[プログラムの変更**]メニューに移動します。
3. **Microsoft Entra Connect プロビジョニング エージェント**のエントリに対応するバージョンを探します。

##### Microsoft からプロビジョニング エージェントの更新プログラムは自動的にプッシュされますか。

はい。Windows サービス Microsoft **Entra Connect Agent Updater** が稼働している場合、Microsoft はプロビジョニング エージェントを自動的に更新します。

##### Microsoft Entra Connect を実行しているのと同じサーバーにプロビジョニング エージェントをインストールできますか。

はい、Microsoft Entra Connect を実行しているのと同じサーバーにプロビジョニング エージェントをインストールすることができます。

##### 構成時に、プロビジョニング エージェントでは Microsoft Entra 管理者の資格情報が求められます。 エージェントでは、資格情報がサーバー上のローカルに保存されますか。

構成時に、プロビジョニング エージェントでは、Microsoft Entra テナントに接続するときにのみ、Microsoft Entra 管理者の資格情報が求められます。 エージェントでは、資格情報がサーバー上のローカルに保存されません。 ただし、ローカルの Windows パスワード コンテナー内の *オンプレミス Active Directory ドメイン* への接続に使用される資格情報は保持されます。

##### 送信 HTTP 通信にプロキシ サーバーを使用するようにプロビジョニング エージェントを構成するにはどうすればよいですか。

プロビジョニング エージェントは送信プロキシの使用をサポートしています。 エージェント ** 構成ファイルC:\Program Files\Microsoft Azure AD Connect Provisioning Agent\AADConnectProvisioningAgent.exe.config**を編集して構成できます。終了 `</configuration>` タグの直前のファイルの末尾に、次の行を追加します。 変数 [proxy-server] と [proxy-port] をお客様のプロキシ サーバー名とポート値に置き換えてください。

```xml
    <system.net>
          <defaultProxy enabled="true" useDefaultCredentials="true">
             <proxy
                usesystemdefault="true"
                proxyaddress="http://[proxy-server]:[proxy-port]"
                bypassonlocal="true"
             />
         </defaultProxy>
    </system.net>
```

##### プロビジョニング エージェントが Microsoft Entra テナントと通信できること、ファイアウォールがエージェントに必要なポートをブロックしていないことを確認するにはどうすればよいですか。

[必要なすべてのポート](https://learn.microsoft.com/ja-jp/entra/global-secure-access/how-to-configure-connectors)が開いているかどうかを確認することもできます。

##### 1 つのプロビジョニング エージェントを複数の AD ドメインをプロビジョニングするように構成できますか。

はい。1 つのプロビジョニング エージェントは、そのエージェントから各ドメイン コントローラーへの通信経路がある限り、複数の AD ドメインを処理するように構成できます。 高可用性を確保し、フェールオーバーをサポートするために、同じ AD ドメインのセットにサービスを提供する 3 つのプロビジョニング エージェントのグループを設定することをお勧めします。

##### プロビジョニング エージェントに関連付けられているドメインの登録を解除するにはどうすればよいですか。

\*、Microsoft Entra *テナントのテナント ID を* 取得します。

- プロビジョニング エージェントを実行している Windows サーバーにサインインします。
- Windows 管理者として PowerShell を開きます。
- 登録スクリプトを含むディレクトリに移動し、[tenant ID] パラメーターをお客様のテナント ID の値に置き換えて、次のコマンドを実行します。

    ```powershell
    cd "C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\RegistrationPowershell\Modules\PSModulesFolder"
    Import-Module "C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\RegistrationPowershell\Modules\PSModulesFolder\MicrosoftEntraPrivateNetworkConnectorPSModule.psd1"
    Get-PublishedResources -TenantId "[tenant ID]"
    ```
- 表示されるエージェントの一覧から、`id` が AD ドメイン名と等しいリソースの  フィールドの値をコピーします。
- このコマンドに ID 値を貼り付けて、PowerShell でコマンドを実行します。

    ```powershell
    Remove-PublishedResource -ResourceId "[resource ID]" -TenantId "[tenant ID]"
    ```
- Agent configuration (エージェントの構成) ウィザードを再実行します。
- 以前にこのドメインに割り当てられていた他のエージェントがある場合は、すべて再構成する必要があります。

##### プロビジョニング エージェントをアンインストールするにはどうすればよいですか。

- プロビジョニング エージェントがインストールされている Windows サーバーにサインインします。
- **[コントロール パネル**] に移動&gt;**プログラムメニューのインストールまたは変更を**行う
- 次のプログラムをアンインストールします。
    - Microsoft Entra Connect プロビジョニング エージェント
    - Microsoft Entra Connect エージェント アップデーター
    - Microsoft Entra Connect プロビジョニング エージェント パッケージ

#### Workday から AD への属性マッピングと構成に関する質問

##### Workday プロビジョニング属性マッピングとスキーマの作業用コピーをバックアップまたはエクスポートするにはどうすればよいですか。

Microsoft Graph API を使用して、Workday のユーザー プロビジョニング構成をエクスポートできます。 詳細については、「 Workday ユーザー プロビジョニング属性マッピング構成のエクスポートとインポート 」セクションの手順を参照してください。

##### Workday と Active Directory にカスタム属性があります。 カスタム属性と連携するようにソリューションを構成するにはどうすればよいですか。

このソリューションは、カスタムの Workday 属性と Active Directory 属性をサポートしています。 カスタム属性をマッピング スキーマに追加するには、[ **属性マッピング** ] ブレードを開き、下にスクロールして [ **詳細オプションの表示**] セクションを展開します。

[Image: 属性リストの編集のスクリーンショット。]

カスタム Workday 属性を追加するには、 *Workday の [属性リストの編集]* オプションを選択し、カスタム AD 属性を追加するには、[ *オンプレミス Active Directory] の [属性リストの編集]* オプションを選択します。

関連項目:

- Workday ユーザー属性の一覧のカスタマイズ

##### Workday の変更に基づいて AD 内の属性のみを更新し、新しい AD アカウントを作成しないようにソリューションを構成するにはどうすればよいですか。

この構成は、次に示すように、[**属性マッピング**] ブレードで**ターゲット オブジェクト アクション**を設定することで実現できます。

[Image: Update アクションのスクリーンショット。]

Workday から AD 方向の更新操作のみを実行するには、[更新] チェックボックスをオンにします。

##### ユーザーの写真を Workday から Active Directory にプロビジョニングできますか。

現在、このソリューションでは、Active Directory での *thumbnailPhoto* や *jpegPhoto* などのバイナリ属性の設定はサポートされていません。

##### パブリック使用に関するユーザーの同意に基づいて Workday の携帯電話番号を同期するにはどうすればよいですか。

- Workday Provisioning アプリの [プロビジョニング] ブレードに移動します。
- [属性マッピング] をクリックします
- [ **マッピング**] で、[ **Workday Worker をオンプレミス Active Directory に同期** する] (または **[Workday Worker を Microsoft Entra ID に同期**する] を選択します)。
- [属性マッピング] ページで、下にスクロールして [詳細オプションの表示] チェックボックスをオンにします。 **Workday の [属性リストの編集] を**クリックします
- 開いたブレードで「Mobile」属性を見つけ、行をクリックして**API 式**を編集できるようにします。[Image: Mobile GDPR のスクリーンショット。]
- **API 式**を次の新しい式に置き換えます。この式は、Workday で "Public Usage Flag" が "True" に設定されている場合にのみ、職場の携帯電話番号を取得します。

    ```
     wd:Worker/wd:Worker_Data/wd:Personal_Data/wd:Contact_Data/wd:Phone_Data[translate(string(wd:Phone_Device_Type_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='MOBILE' and translate(string(wd:Usage_Data/wd:Type_Data/wd:Type_Reference/@wd:Descriptor),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')='WORK' and string(wd:Usage_Data/@wd:Public)='1']/@wd:Formatted_Phone
    ```
- 属性リストを保存します。
- 属性マッピングを保存します。
- 現在の状態を消去して、完全な同期を再開します。

##### ユーザーの部署/国/市区町村の属性に基づいて AD の表示名の書式を設定し、地域の差異を処理するにはどうすればよいですか。

ユーザーの部署と国/地域に関する情報も提供されるように、AD で *displayName* 属性を構成することは一般的な要件です。 たとえば、John Smith が米国のマーケティング部門で働いている場合、 *displayName* を *Smith、John (Marketing-US)* として表示できます。

CN *または* *displayName* を構築するためのこのような要件を処理して、会社、部署、市区町村、国/地域などの属性を含める方法を次に示します。

- 各 Workday 属性は、基になる XPATH API 式を使用して取得されます。これは、 **属性マッピング -&gt; 詳細セクション -&gt; Workday の属性リストの編集**で構成できます。 Workday *PreferredFirstName*、 *PreferredLastName*、 *Company* 属性、 *SupervisoryOrganization* 属性の既定の XPATH API 式を次に示します。

    | Workday 属性 | API XPATH 式 |
    | --- | --- |
    | 優先使用名 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:First\_Name/text() |
    | 希望の苗字 | wd:Worker/wd:Worker\_Data/wd:Personal\_Data/wd:Name\_Data/wd:Preferred\_Name\_Data/wd:Name\_Detail\_Data/wd:Last\_Name/text() |
    | [会社] | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data[wd:Organization\_Data/wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID']='Company']/wd:Organization\_Reference/@wd:Descriptor |
    | 監督組織 | wd:Worker/wd:Worker\_Data/wd:Organization\_Data/wd:Worker\_Organization\_Data/wd:Organization\_Data[wd:Organization\_Type\_Reference/wd:ID[@wd:type='Organization\_Type\_ID']='Supervisory']/wd:Organization\_Name/text() |

    上記の API 式がお客様の Workday テナント構成で有効であることをお客様の Workday チームに確認してください。 必要に応じて、 Workday ユーザー属性の一覧のカスタマイズセクションの説明に従って編集できます。
- 同様に、Workday に存在する国/地域情報は、次の XPATH を使用して取得されます: *wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference*

    Workday 属性リスト セクションで使用できる国/地域関連の属性が 5 つあります。

    | Workday 属性 | API XPATH 式 |
    | --- | --- |
    | CountryReference | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Alpha-3\_Code']/text() |
    | カントリーリファレンスフレンドリー | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/@wd:Descriptor |
    | 国参照数値 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Numeric-3\_Code']/text() |
    | 国リファレンス2文字 | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Reference/wd:ID[@wd:type='ISO\_3166-1\_Alpha-2\_Code']/text() |
    | 国地域リファレンス | wd:Worker/wd:Worker\_Data/wd:Employment\_Data/wd:Position\_Data/wd:Business\_Site\_Summary\_Data/wd:Address\_Data/wd:Country\_Region\_Reference/@wd:Descriptor |

    上の API 式がお客様の Workday テナント構成で有効であることをお客様の Workday チームに確認してください。 必要に応じて、 Workday ユーザー属性の一覧のカスタマイズセクションの説明に従って編集できます。
- 正しい属性マッピング式を構築するには、どの Workday 属性が "正式に" ユーザーの姓、名、国/地域、部署を表すかを特定します。 属性がそれぞれ *PreferredFirstName*、 *PreferredLastName*、 *CountryReferenceTwoLetter* 、 *SupervisoryOrganization* であるとします。 これを使用して、次のように AD *displayName* 属性の式を作成し *、Smith、John (Marketing-US)* などの表示名を取得できます。

    ```
     Append(Join(", ",[PreferredLastName],[PreferredFirstName]), Join(""," (",[SupervisoryOrganization],"-",[CountryReferenceTwoLetter],")"))
    ```

    適切な式を作成したら、[属性マッピング] テーブルを編集し、 *displayName* 属性マッピングを次のように変更します。 [Image: DisplayName マッピングのスクリーンショット。]
- 上記の例を拡張して、Workday から取得した都市名を短縮形の値に変換し、それを使用して *Smith、John (CHI)、* *Doe、Jane (NYC)* などの表示名を作成すると、この結果は、Workday *市区町村*属性を決定変数として使用して実現できます。

    ```
    Switch
    (
      [Municipality],
      Join(", ", [PreferredLastName], [PreferredFirstName]),  
           "Chicago", Append(Join(", ",[PreferredLastName], [PreferredFirstName]), "(CHI)"),
           "New York", Append(Join(", ",[PreferredLastName], [PreferredFirstName]), "(NYC)"),
           "Phoenix", Append(Join(", ",[PreferredLastName], [PreferredFirstName]), "(PHX)")
    )
    ```

    関連項目:

    - [Switch 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#switch)
    - [結合関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#join)
    - [Append 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#append)

##### SelectUniqueValue を使用して samAccountName 属性の一意の値を生成する方法を教えてください。

たとえば、Workday の *FirstName* 属性と *LastName* 属性の組み合わせを使用して*、samAccountName* 属性の一意の値を生成するとします。 以下の式を基にして始めることができます。

```
SelectUniqueValue(
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("",  Mid([FirstName],1,1), [LastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , ),
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("",  Mid([FirstName],1,2), [LastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , ),
    Replace(Mid(Replace(NormalizeDiacritics(StripSpaces(Join("",  Mid([FirstName],1,3), [LastName]))), , "([\\/\\\\\\[\\]\\:\\;\\|\\=\\,\\+\\*\\?\\<\\>])", , "", , ), 1, 20), , "(\\.)*$", , "", , )
)
```

上の式は次のように機能します。ユーザーが John Smith の場合は、まず JSmith の生成が試行され、JSmith が既に存在する場合は JoSmith が生成され、それが存在する場合は JohSmith が生成されます。 また、この式により、生成される値が *samAccountName* に関連付けられている長さの制限と特殊文字の制限を満たしていることも保証されます。

関連項目:

- [Mid 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#mid)
- [Replace 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#replace)
- [SelectUniqueValue 関数の構文](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#selectuniquevalue)

##### 分音記号を使用する文字を削除し、通常の英語のアルファベットに変換するにはどうすればよいですか。

[NormalizeDiacritics](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#normalizediacritics) 関数を使用して、ユーザーの電子メール アドレスまたは CN 値を構築しながら、ユーザーの名と姓の特殊文字を削除します。

### トラブルシューティングのヒント

このセクションでは、Microsoft Entra プロビジョニング ログと Windows Server イベント ビューアー ログを使用して、Workday 統合に関するプロビジョニングの問題を解決する方法について、具体的なガイダンスを提供します。 これは、「[チュートリアル: 自動ユーザー アカウント プロビジョニングに関するレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)」でキャプチャした一般的なトラブルシューティング手順と概念に基づいています

このセクションでは、トラブルシューティングの次の側面について説明しています。

- イベント ビューアー ログを出力するようにプロビジョニング エージェントを構成する
- エージェントのトラブルシューティングのための Windows イベント ビューアーの設定
- サービスのトラブルシューティングのための Microsoft Entra 管理センターのプロビジョニング ログの設定
- AD ユーザー アカウントの作成操作のログについて
- Manager の更新操作のログについて
- 一般的に発生するエラーの解決

#### イベント ビューアー ログを出力するようにプロビジョニング エージェントを構成する

1. プロビジョニング エージェントがデプロイされている Windows Server マシンにサインインします。
2. **サービス Microsoft Entra Connect プロビジョニング エージェントを停止します**。
3. 元の構成ファイルのコピーを作成 * します:C:\Program Files\Microsoft Azure AD Connect Provisioning Agent\AADConnectProvisioningAgent.exe.config*。
4. 既存の `<system.diagnostics>` セクションを以下の内容に置き換えます。

    - リスナー構成 **etw** は EventViewer ログにメッセージを出力します
    - リスナー構成 **textWriterListener** は、トレース メッセージをファイル *ProvAgentTrace.log*に送信します。 高度なトラブルシューティングを行う場合のみ、textWriterListener に関連した行をコメント解除してください。

    ```xml
      <system.diagnostics>
          <sources>
          <source name="AAD Connect Provisioning Agent">
              <listeners>
              <add name="console"/>
              <add name="etw"/>
              <!-- <add name="textWriterListener"/> -->
              </listeners>
          </source>
          </sources>
          <sharedListeners>
          <add name="console" type="System.Diagnostics.ConsoleTraceListener" initializeData="false"/>
          <add name="etw" type="System.Diagnostics.EventLogTraceListener" initializeData="Azure AD Connect Provisioning Agent">
              <filter type="System.Diagnostics.EventTypeFilter" initializeData="All"/>
          </add>
          <!-- <add name="textWriterListener" type="System.Diagnostics.TextWriterTraceListener" initializeData="C:/ProgramData/Microsoft/Azure AD Connect Provisioning Agent/Trace/ProvAgentTrace.log"/> -->
          </sharedListeners>
      </system.diagnostics>
    
    ```
5. **サービス Microsoft Entra Connect プロビジョニング エージェントを開始します**。

#### エージェントのトラブルシューティングのための Windows イベント ビューアーの設定

1. プロビジョニング エージェントがデプロイされている Windows Server マシンにサインインします。
2. **Windows Server イベント ビューアー** デスクトップ アプリを開きます。
3. **[Windows ログ &gt; アプリケーション] を選択します**。
4. 次に示すようにフィルター "-5" を指定して、ソース**の Microsoft Entra Connect プロビジョニング エージェント**でログに記録されたすべてのイベントを表示し、イベント ID が "5" のイベントを除外するには、[**現在のログのフィルター]...** オプションを使用します。

    注

    イベント ID 5 でキャプチャされるのは Microsoft Entra クラウド サービスに対するエージェントのブートストラップ メッセージであるため、ログ ファイルを分析する間はフィルターで除外します。

    [Image: Windows イベント ビューアーのスクリーンショット。]
5. [ **OK] を** クリックし、結果ビューを **日付と時刻** の列で並べ替えます。

#### サービスのトラブルシューティングのための Microsoft Entra 管理センター プロビジョニング ログの設定

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)を起動し、Workday **プロビジョニング** アプリケーションの [プロビジョニング] セクションに移動します。
2. [プロビジョニング ログ] ページの **[列** ] ボタンを使用して、ビューに次の列 (日付、アクティビティ、状態、状態の理由) のみを表示します。 この構成にすることで、トラブルシューティングに関連するデータのみに確実に集中できます。

    [Image: プロビジョニング ログ列のスクリーンショット。]
3. **[ターゲット**] および **[日付範囲]** クエリ パラメーターを使用して、ビューをフィルター処理します。

    - **Target** クエリ パラメーターを Workday worker オブジェクトの "Worker ID" または "Employee ID" に設定します。
    - **日付範囲**を、プロビジョニングに関するエラーや問題を調査する適切な期間に設定します。

    [Image: プロビジョニング ログ フィルターのスクリーンショット。]

#### AD ユーザー アカウントの作成操作のログの概要

Workday の新入社員が検出されると (たとえば、Employee ID *21023* とします)、Microsoft Entra プロビジョニング サービスは、ワーカーの新しい AD ユーザー アカウントの作成を試み、プロセスで次に説明するように 4 つのプロビジョニング ログ レコードを作成します。

[Image: プロビジョニング ログの作成操作のスクリーンショット。]

プロビジョニング ログ レコードのいずれかをクリックすると、[ **アクティビティの詳細]** ページが開きます。 各ログ レコードの種類に対して [ **アクティビティの詳細]** ページが表示される内容を次に示します。

- **Workday インポート** レコード: このログ レコードには、Workday からフェッチされたワーカー情報が表示されます。 ログ レコードの *[追加の詳細]* セクションの情報を使用して、Workday からのデータのフェッチに関する問題のトラブルシューティングを行います。 レコードの例を、各フィールドの解釈方法についてのポインターと共に次に示します。

    ```JSON
    ErrorCode : None  // Use the error code captured here to troubleshoot Workday issues
    EventName : EntryImportAdd // For full sync, value is "EntryImportAdd" and for delta sync, value is "EntryImport"
    JoiningProperty : 21023 // Value of the Workday attribute that serves as the Matching ID (usually the Worker ID or Employee ID field)
    SourceAnchor : a071861412de4c2486eb10e5ae0834c3 // set to the WorkdayID (WID) associated with the record
    ```
- **AD インポート** レコード: このログ レコードには、AD からフェッチされたアカウントの情報が表示されます。 最初のユーザー作成時には AD アカウントがないため、アクティビティの [状態の理由] は、その照合 ID 属性値を持つアカウントが Active Directory に見つからなかったことを示します。 ログ レコードの *[追加の詳細]* セクションの情報を使用して、Workday からのデータのフェッチに関する問題のトラブルシューティングを行います。 レコードの例を、各フィールドの解釈方法についてのポインターと共に次に示します。

    ```JSON
    ErrorCode : None // Use the error code captured here to troubleshoot Workday issues
    EventName : EntryImportObjectNotFound // Implies that object wasn't found in AD
    JoiningProperty : 21023 // Value of the Workday attribute that serves as the Matching ID
    ```

    この AD インポート操作に対応する Provisioning Agent ログ レコードを検索するには、Windows イベント ビューアーのログを開き、[ **検索...]** メニュー オプションを使用して、照合 ID/結合プロパティの属性値 (この場合 *は 21023*) を含むログ エントリを検索します。

    [Image: [検索] のスクリーンショット。]

    *イベント ID = 9* のエントリを探します。このエントリには、エージェントが AD アカウントを取得するために使用する LDAP 検索フィルターが用意されています。 これが一意のユーザー エントリを取得するための適切な検索フィルターであるかどうかを確認できます。

    *イベント ID = 2* の直後のレコードは、検索操作の結果と、結果が返されたかどうかをキャプチャします。
- **同期規則アクション** レコード: このログ レコードには、属性マッピング ルールと構成されたスコープ フィルターの結果と、受信 Workday イベントを処理するために実行されるプロビジョニング アクションが表示されます。 ログ レコードの *[追加の詳細]* セクションの情報を使用して、同期アクションに関する問題のトラブルシューティングを行います。 レコードの例を、各フィールドの解釈方法についてのポインターと共に次に示します。

    ```JSON
    ErrorCode : None // Use the error code captured here to troubleshoot sync issues
    EventName : EntrySynchronizationAdd // Implies that the object is added
    JoiningProperty : 21023 // Value of the Workday attribute that serves as the Matching ID
    SourceAnchor : a071861412de4c2486eb10e5ae0834c3 // set to the WorkdayID (WID) associated with the profile in Workday
    ```

    属性マッピング式に問題がある場合、または受信 Workday データに問題がある場合 (たとえば、必須の属性値が空または null)、この段階で、失敗の詳細を示す ErrorCode を使用して失敗を確認します。
- **AD エクスポート** レコード: このログ レコードには、AD アカウントの作成操作の結果と、プロセスで設定された属性値が表示されます。 アカウント作成操作に関する問題をトラブルシューティングするには、ログ レコードの *[追加の詳細]* セクションの情報を使用します。 レコードの例を、各フィールドの解釈方法についてのポインターと共に次に示します。 [追加の詳細] セクションで、"EventName" は "EntryExportAdd" に設定され、"JoiningProperty" は [Matching ID](照合 ID) 属性の値に設定され、"SourceAnchor" はそのレコードに関連付けられた WorkdayID (WID) に設定され、"TargetAnchor" は新しく作成されたユーザーの AD の "ObjectGuid" 属性の値に設定されます。

    ```JSON
    ErrorCode : None // Use the error code captured here to troubleshoot AD account creation issues
    EventName : EntryExportAdd // Implies that object is created
    JoiningProperty : 21023 // Value of the Workday attribute that serves as the Matching ID
    SourceAnchor : a071861412de4c2486eb10e5ae0834c3 // set to the WorkdayID (WID) associated with the profile in Workday
    TargetAnchor : aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb // set to the value of the AD "objectGuid" attribute of the new user
    ```

    この AD エクスポート操作に対応するプロビジョニング エージェントのログ レコードを検索するには、Windows イベント ビューアーのログを開き、[ **検索...]** メニュー オプションを使用して、照合 ID/結合プロパティの属性値 (この場合 *は 21023*) を含むログ エントリを検索します。

    *イベント ID = 2* のエクスポート操作のタイムスタンプに対応する HTTP POST レコードを探します。 このレコードには、プロビジョニング サービスからプロビジョニング エージェントに送信された属性値が含まれます。

    [Image: [プロビジョニング エージェント] ログの]

    上記のイベントの直後に、AD アカウント作成操作の応答をキャプチャする別のイベントがあるはずです。 このイベントは、AD で作成された新しい objectGuid を返し、それがプロビジョニング サービスの TargetAnchor 属性として設定されます。

    [Image: AD で作成された objectGuid が強調表示された [プロビジョニング エージェント] ログを示すスクリーンショット。]

#### マネージャーの更新操作のログの概要

manager 属性は AD の参照属性です。 プロビジョニング サービスでは、ユーザー作成操作の一環として manager 属性は設定されません。 代わりに、ユーザーの AD アカウントが作成された後、 *更新* 操作の一部としてマネージャー属性が設定されます。 上記の例を展開すると、Workday で従業員 ID "21451" を持つ新入社員がアクティブになり、新入社員のマネージャー (*21023*) に既に AD アカウントがあるとします。 このシナリオでは、ユーザー 21451 のプロビジョニング ログを検索すると、5 つのエントリが表示されます。

[Image: Manager Update のスクリーンショット。]

最初の 4 つのレコードは、ユーザー作成操作の一部として調べたものと似ています。 5 つ目のレコードは、manager 属性の更新に関連付けられたエクスポートです。 ログ レコードには、マネージャーの *objectGuid* 属性を使用して実行される AD アカウント マネージャーの更新操作の結果が表示されます。

```JSON
// Modified Properties
Name : manager
New Value : "aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb" // objectGuid of the user 21023

// Additional Details
ErrorCode : None // Use the error code captured here to troubleshoot AD account creation issues
EventName : EntryExportUpdate // Implies that object is created
JoiningProperty : 21451 // Value of the Workday attribute that serves as the Matching ID
SourceAnchor : 9603bf594b9901693f307815bf21870a // WorkdayID of the user
TargetAnchor : 43b668e7-1d73-401c-a00a-fed14d31a1a8 // objectGuid of the user 21451

```

#### よく発生するエラーの解決

このセクションでは、Workday ユーザーのプロビジョニングでよく見られるエラーとその解決方法について説明します。 エラーは次のように分類されます。

- プロビジョニング エージェントのエラー
- 接続エラー
- AD ユーザー アカウントの作成エラー
- AD ユーザー アカウントの更新エラー

##### プロビジョニング エージェントのエラー

| # | エラーのシナリオ | 考えられる原因 | 推奨される解決方法 |
| --- | --- | --- | --- |
| 1. | サービス *'Microsoft Entra Connect Provisioning Agent' (AADConnectProvisioningAgent) の起動に失敗しました。プロビジョニング エージェントのインストール中にエラー メッセージが表示されました。システムを起動するための十分な特権があることを確認します。* | 通常、このエラーはプロビジョニング エージェントをドメイン コントローラーにインストールしようとしたときに、グループ ポリシーがサービスの開始を妨げた場合に発生します。 前のバージョンのエージェントを実行していて、それを新しいインストールの開始前にアンインストールしなかった場合にもこれが表示されます。 | DC 以外のサーバーにプロビジョニング エージェントをインストールします。 新しいエージェントをインストールする前に、必ず以前のバージョンのエージェントをアンインストールします。 |
| 2. | Windows サービス 'Microsoft Entra Connect プロビジョニング エージェント' は *開始* 状態であり、 *実行中* の状態には切り替わりません。 | エージェント ウィザードは、インストールの一環として、サーバーにローカル アカウント (**NT Service\AADConnectProvisioningAgent**) を作成します。これは、サービスの開始に使用されるログオン アカウントです。 Windows サーバー上のセキュリティ ポリシーにより、ローカル アカウントでサービスを実行できない場合は、このエラーが発生します。 | *サービス コンソール*を開きます。 Windows サービスの 'Microsoft Entra Connect Provisioning Agent' を右クリックし、ログオン タブでサービスを実行するドメイン管理者のアカウントを指定します。 サービスを再起動します。 |
| 3. | *Active Directory の接続*手順で AD ドメインを使用してプロビジョニング エージェントを構成する場合、ウィザードで AD スキーマを読み込もうとする時間が長く、最終的にタイムアウトになります。 | 通常、このエラーは、ファイアウォールの問題のためにウィザードから AD ドメイン コントローラー サーバーに接続できない場合に表示されます。 | [Active Directory の接続] ウィザード画面で、AD ドメインの資格情報を入力するときに、[Select domain controller priority] (ドメイン コントローラーの優先順位の選択) というオプションがあります。 このオプションは、エージェント サーバーと同じサイト内にあるドメイン コントローラーを選択し、通信をブロックするファイアウォール規則がないようにするために使用します。 |

##### 接続エラー

プロビジョニング サービスが Workday または Active Directory に接続できない場合は、プロビジョニングが検疫状態になる可能性があります。 次の表を使用して、接続の問題を解決します。

| # | エラーのシナリオ | 考えられる原因 | 推奨される解決方法 |
| --- | --- | --- | --- |
| 1. | [ **接続のテスト**] をクリックすると、エラー メッセージが表示されます。 *Active Directory への接続中にエラーが発生しました。オンプレミスのプロビジョニング エージェントが実行されており、正しい Active Directory ドメインで構成されていることを確認してください。* | 通常、このエラーは、プロビジョニング エージェントが実行されていないか、Microsoft Entra ID とプロビジョニング エージェントとの間の通信をブロックしているファイアウォールがある場合に発生します。 ドメインがエージェント ウィザードで構成されていない場合にもこのエラーが表示されることがあります。 | Windows サーバーで *Services* コンソールを開き、エージェントが実行されていることを確認します。 プロビジョニング エージェント ウィザードを開き、正しいドメインがエージェントに登録されていることを確認します。 |
| 2. | プロビジョニング ジョブが週末 (金曜から土曜) にかけて検疫状態になり、同期にエラーがあるというメール通知を受け取ります。 | このエラーの一般的な原因の 1 つは、スケジュールされている Workday のダウンタイムです。 Workday の実装テナントを使用する場合、Workday には、週末にかけて (通常は金曜日の夜から土曜日の朝まで) スケジュールされた実装テナントのダウンタイムがあり、その期間中は Workday に接続できないため、Workday プロビジョニング アプリが検疫状態になる可能性があります。 Workday 実装テナントがオンラインに戻ると、通常の状態に戻ります。 ごくまれに、テナントの更新により統合システム ユーザーのパスワードが変更された場合、またはアカウントがロックまたは期限切れの状態にある場合にも、このエラーが表示されることがあります。 | Workday 管理者または統合パートナーに連絡して、ダウンタイム期間中に Workday がアラート メッセージを無視するようにダウンタイムをスケジュールし、Workday インスタンスがオンラインに戻ったら可用性を確認します。 |

##### AD ユーザー アカウントの作成エラー

| # | エラーのシナリオ | 考えられる原因 | 推奨される解決方法 |
| --- | --- | --- | --- |
| 1. | プロビジョニング ログでエクスポート操作が失敗し、 *エラー: OperationsError-SvcErr: 操作エラーが発生しました。ディレクトリ サービスには上位参照が構成されていないため、このフォレスト外のオブジェクトへの紹介を発行できません。* | このエラーは、通常、 *Active Directory コンテナー* OU が正しく設定されていない場合、または *parentDistinguishedName* に使用される式マッピングに問題がある場合に表示されます。 | OU パラメーターについて誤字がないか*Active Directory コンテナー*を確認します。 属性マッピングで *parentDistinguishedName を* 使用している場合は、常に AD ドメイン内の既知のコンテナーに評価されることを確認します。 プロビジョニング ログで *Export* イベントを調べて、生成された値を確認します。 |
| 2. | プロビジョニングログのエクスポート操作に失敗し、エラーコード: *SystemForCrossDomainIdentityManagementBadResponse* とメッセージ *エラー: ConstraintViolation-AtrErr: 要求の値が無効です。属性の値が許容範囲内にありませんでした。\nエラーの詳細: CONSTRAINT\_ATT\_TYPE - 属性名「会社」*が発生しました。 | このエラーは *会社* の属性に固有のものですが、 *CN* などの他の属性でもこのエラーが表示される場合があります。 このエラーは、AD で適用されたスキーマ制約が原因で発生します。 既定では、AD の *company* や *CN* などの属性の上限は 64 文字です。 Workday に由来する値が 64 文字を超える場合は、このエラー メッセージが表示されます。 | プロビジョニング ログの *Export* イベントを調べて、エラー メッセージで報告された属性の値を確認します。 [Mid](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#mid) 関数を使用して Workday からの値を切り捨てるか、長さの制約が類似していない AD 属性へのマッピングを変更することを検討してください。 |

##### AD ユーザー アカウントの更新エラー

AD ユーザー アカウントの更新プロセス中に、プロビジョニング サービスでは Workday と AD の両方から情報が読み取られ、属性マッピング規則を実行して、変更を反映する必要があるかどうかを判断します。 それに応じて更新イベントがトリガーされます。 これらの手順のいずれかで障害が発生した場合は、プロビジョニング ログに記録されます。 次の表を使用して、一般的な更新エラーを解決します。

| # | エラーのシナリオ | 考えられる原因 | 推奨される解決方法 |
| --- | --- | --- | --- |
| 1. | *EventName = EntrySynchronizationError および ErrorCode = EndpointUnavailable* というメッセージを含むプロビジョニング ログの同期規則アクションの失敗。 | このエラーは、オンプレミスのプロビジョニング エージェントで処理エラーが発生したために、プロビジョニング サービスで Active Directory からユーザー プロファイル データを取得できない場合に発生します。 | プロビジョニング エージェントのイベント ビューアー ログで、読み取り操作に関する問題を示すエラー イベントを確認します (イベント ID 2 でフィルター)。 |
| 2. | AD の特定のユーザーについて、AD の manager 属性が更新されません。 | このエラーで最も可能性が高い原因は、スコープ規則を使用していて、ユーザーのマネージャーがスコープに含まれていないことです。 マネージャーの照合 ID 属性 (EmployeeID など) がターゲット AD ドメインに見つからないか、正しい値に設定されていない場合も、この問題が発生する可能性があります。 | スコープ フィルターを確認し、スコープ内にマネージャー ユーザーを追加します。 AD 内のマネージャーのプロファイルを調べて、照合 ID 属性の値があることを確認してください。 |

### 構成の確認

このセクションでは、Workday 主導のユーザー プロビジョニング構成をさらに拡張、カスタマイズ、および管理する方法について説明します。 次のトピックについて説明します。

- Workday ユーザー属性の一覧のカスタマイズ
- 構成のエクスポートとインポート

#### Workday のユーザー属性リストをカスタマイズする

Active Directory と Microsoft Entra ID の Workday プロビジョニング アプリにはどちらも、Workday のユーザー属性の選択元となる既定のリストが含まれています。 ただしこれらのリストに、すべてが含まれているわけではありません。 Workday は、想定されるさまざまなユーザー属性を数多くサポートしていますが、それらのユーザー属性には、標準の属性と特定の Workday テナントに固有の属性とがあります。

Microsoft Entra プロビジョニング サービスでは、リストまたは Workday 属性をカスタマイズして、人事 API の [Get_Workers](https://community.workday.com/sites/default/files/file-hosting/productionapi/Human_Resources/v21.1/Get_Workers.html) 操作で公開されるすべての属性を含めることができます。

この変更を行うには、 [Workday Studio](https://community.workday.com/studio-download) を使用して、使用する属性を表す XPath 式を抽出し、高度な属性エディターを使用してプロビジョニング構成に追加する必要があります。

**Workday ユーザー属性の XPath 式を取得するには:**

1. [Workday Studio](https://community.workday.com/studio-download) をダウンロードしてインストールします。 インストーラーにアクセスするには、Workday コミュニティ アカウントが必要です。
2. Workday **Web Services ディレクトリ**から、使用する予定の WWS API バージョンに固有の Workday [Human_Resources](https://community.workday.com/sites/default/files/file-hosting/productionapi/index.html) WSDL ファイルをダウンロードします
3. Workday Studio を起動します。
4. コマンド バーから、 **Workday &gt; Test Web Service in Tester オプションを** 選択します。
5. [ **外部]** を選択し、手順 2 でダウンロードしたHuman\_Resources WSDL ファイルを選択します。

    [Image: Workday Studio で開いている]
6. **[場所**] フィールドを`https://IMPL-CC.workday.com/ccx/service/TENANT/Human_Resources`に設定しますが、"IMPL-CC" を実際のインスタンスの種類に置き換え、"TENANT" を実際のテナント名に置き換えます。
7. **操作**を**Get\_Workers**に設定する
8. [要求/応答] ウィンドウの下にある小さな **構成** リンクをクリックして、Workday の資格情報を設定します。 **[認証**] をオンにし、Workday 統合システム アカウントのユーザー名とパスワードを入力します。 必ずユーザー名をname@tenantとして書式設定し、 **WS-Security UsernameToken** オプションを選択したままにします。 [Image: [ユーザー名] と [パスワード] が入力され、[WS-Security ユーザー名トークン] が選択されている [セキュリティ] タブを示すスクリーンショット。]
9. [ **OK] を選択します**。
10. [ **要求** ] ウィンドウで、下の XML を貼り付けます。 **Employee\_ID**を Workday テナントの実際のユーザーの従業員 ID に設定します。 **wd:version** を、使用する予定の WWS のバージョンに設定します。 抽出対象となる属性が設定されているユーザーを選択します。

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <env:Envelope xmlns:env="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="https://www.w3.org/2001/XMLSchema">
      <env:Body>
        <wd:Get_Workers_Request xmlns:wd="urn:com.workday/bsvc" wd:version="v21.1">
          <wd:Request_References wd:Skip_Non_Existing_Instances="true">
            <wd:Worker_Reference>
              <wd:ID wd:type="Employee_ID">21008</wd:ID>
            </wd:Worker_Reference>
          </wd:Request_References>
          <wd:Response_Group>
            <wd:Include_Reference>true</wd:Include_Reference>
            <wd:Include_Personal_Information>true</wd:Include_Personal_Information>
            <wd:Include_Employment_Information>true</wd:Include_Employment_Information>
            <wd:Include_Management_Chain_Data>true</wd:Include_Management_Chain_Data>
            <wd:Include_Organizations>true</wd:Include_Organizations>
            <wd:Include_Reference>true</wd:Include_Reference>
            <wd:Include_Transaction_Log_Data>true</wd:Include_Transaction_Log_Data>
            <wd:Include_Photo>true</wd:Include_Photo>
            <wd:Include_User_Account>true</wd:Include_User_Account>
          <wd:Include_Roles>true</wd:Include_Roles>
          </wd:Response_Group>
        </wd:Get_Workers_Request>
      </env:Body>
    </env:Envelope>
    ```
11. [ **要求の送信** ] (緑色の矢印) をクリックしてコマンドを実行します。 成功した場合は、応答ウィンドウに **応答** が表示されます。 エラーではなく、入力したユーザー ID のデータが応答に含まれていることを確認します。
12. 成功した場合は、[ **応答** ] ウィンドウから XML をコピーし、XML ファイルとして保存します。
13. Workday Studio のコマンド バーで、[ **ファイル] &gt; [ファイルを開く]** を選択し、保存した XML ファイルを開きます。 この操作で、Workday Studio の XML エディターにファイルが開きます。

14. ファイル ツリーで、 **/env: Envelope &gt; env: Body &gt; wd:Get\_Workers\_Response &gt; wd:Response\_Data &gt; wd: Worker** 内を移動して、ユーザーのデータを検索します。
15. **wd: Worker** で、追加する属性を見つけて選択します。
16. 選択した属性の XPath 式を **[ドキュメント パス** ] フィールドからコピーします。
17. コピーした式から **/env:Envelope/env:Body/wd:Get\_Workers\_Response/wd:Response\_Data/** プレフィックスを削除します。
18. コピーした式の最後の項目がノード (例: "/wd: Birth\_Date") の場合は、式の末尾に **/text()** を追加します。 最後の項目が属性 (例: "/@wd: type") の場合、これは不要です。
19. 最終的には、`wd:Worker/wd:Worker_Data/wd:Personal_Data/wd:Birth_Date/text()` のようになっている必要があります。 この値は、コピーして Microsoft Entra 管理センターに入力する値です。

**カスタム Workday ユーザー属性をプロビジョニング構成に追加するには:**

1. このチュートリアルで前述したように、 [Microsoft Entra 管理センター](https://entra.microsoft.com)を起動し、Workday プロビジョニング アプリケーションの [プロビジョニング] セクションに移動します。
2. **[プロビジョニングの状態]** を **[オフ**] に設定し、[**保存]** を選択します。 この手順を使用すると、変更を反映するタイミングを、確実に準備ができたときにするのに役立ちます。
3. [ **マッピング**] で、[ **Workday Worker をオンプレミス Active Directory に同期** する] (または **[Workday Worker を Microsoft Entra ID に同期**する] を選択します)。
4. 次の画面の一番下までスクロールし、[ **詳細オプションの表示**] を選択します。
5. **Workday の [属性リストの編集] を選択します**。
6. 属性リストの一番下にある入力フィールドまでスクロールします。
7. [ **名前]** に、属性の表示名を入力します。
8. [ **種類]** で、属性に適切に対応する型を選択します (**文字列** が最も一般的です)。
9. **[API 式]** に、Workday Studio からコピーした XPath 式を入力します。 例: `wd:Worker/wd:Worker_Data/wd:Personal_Data/wd:Birth_Date/text()`
10. [ **属性の追加] を選択します**。

    [Image: Workday Studio のスクリーンショット。]
11. 上の **[保存]** を選択し、ダイアログボックスで **[はい** ] を選択します。 [属性マッピング] 画面がまだ開いている場合は閉じてください。
12. メインの [ **プロビジョニング** ] タブに戻り、[ **Workday Worker をオンプレミスの Active Directory に同期** する ( または **Worker を Microsoft Entra ID に同期**する)] をもう一度選択します。
13. [ **新しいマッピングの追加] を**選択します。
14. これで、新しい属性が **ソース属性** の一覧に表示されます。
15. 必要に応じて新しい属性のマッピングを追加します。
16. 完了したら、**[プロビジョニングの状態]** を **[オン]** に戻して保存します。

#### 構成のエクスポートとインポート

[プロビジョニング構成のエクスポートとインポートに関する記事を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/export-import-provisioning-configuration)参照してください

### 個人データの管理

Active Directory 用の Workday プロビジョニング ソリューションでは、オンプレミスの Windows サーバー上にプロビジョニング エージェントをインストールする必要があります。このエージェントでは、Workday から AD への属性マッピングに応じて個人データを含む可能性があるログが Windows イベント ログに作成されます。 ユーザーのプライバシー義務を遵守するために、イベント ログを消去する Windows のスケジュールされたタスクを設定することで、48 時間を超えてイベント ログにデータが保持されないようにすることができます。

Microsoft Entra プロビジョニング サービスは、GDPR 分類の **データ プロセッサ** カテゴリに分類されます。 このサービスは、データ プロセッサ パイプラインとして、重要なパートナーやエンド コンシューマーにデータ処理サービスを提供するものです。 Microsoft Entra プロビジョニング サービスは、ユーザー データを生成せず、収集する個人データをどれにするか、およびその用途について、独立した制御はありません。 Microsoft Entra ID プロビジョニング サービスでのデータの取得、集計、分析、およびレポートは、既存のエンタープライズ データに基づいて行われます。

注

個人データの表示または削除の詳細については、GDPR サイトに [対する Windows データ主体の要求](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/gdpr-dsr-windows) に関する Microsoft のガイダンスを確認してください。 GDPR に関する一般的な情報については、 [Microsoft セキュリティ センターの GDPR セクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview) と [Service Trust ポータルの GDPR セクションを参照](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)してください。

データ保持に関しては、Microsoft Entra プロビジョニング サービスでは 30 日を超えてレポートの生成、分析の実行、または分析情報の提供を行いません。 そのため Microsoft Entra プロビジョニング サービスでは、いかなるデータも 30 日間を超えて格納、処理、保持されることはありません。 この設計は、GDPR の規制、Microsoft のプライバシー コンプライアンス規則、および Microsoft Entra ID のデータ リテンション ポリシーに準拠したものです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workday-mobile-tutorial"} -->
## Microsoft Entra ID で Workday Mobile Application for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-mobile-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workday Mobile Application の間にシングル サインオンを構成する方法について説明します。

この記事では、Microsoft Entra ID、条件付きアクセス、Intune を Workday Mobile Application と統合する方法について説明します。 Workday モバイル アプリケーションと Microsoft 製品を統合すると、次のことが可能になります。

- サインインする前に、デバイスがポリシーに準拠していることを確認する。
- Workday モバイル アプリケーションにコントロールを追加して、ユーザーが会社のデータに安全にアクセスできるようにする。
- Workday にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Workday に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

作業を開始するには:

- Workday を Microsoft Entra ID と統合できます。
- 「[Microsoft Entra シングル サインオン (SSO) と Workday の統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-tutorial)」を参照してください。

### シナリオの説明

この記事では、Workday Mobile Application を使用して Microsoft Entra 条件付きアクセス ポリシーと Intune を構成し、テストします。

シングル サインオン (SSO) を有効にするために、Microsoft Entra ID で Workday フェデレーション アプリケーションを構成できます。 詳細については、「[Microsoft Entra シングル サインオン (SSO) と Workday との統合](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-tutorial)」を参照してください。

注

Workday では、Intune のアプリ保護ポリシーはサポートされていません。 条件付きアクセスを使用するには、モバイル デバイス管理を使用する必要があります。

### ユーザーが Workday モバイル アプリケーションにアクセスできるようにする

モバイル アプリへのアクセスを許可するように Workday を構成します。 Workday モバイル向けに次のポリシーを構成する必要があります。

1. 機能領域のドメイン セキュリティ ポリシー レポートにアクセスします。
2. 適切なセキュリティ ポリシーを選択します。
    - モバイルの使用 - Android
    - モバイルの使用 - iPad
    - モバイルの使用 - iPhone
3. **[Edit Permissions](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス許可の編集)** を選択します。
4. **[View](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示) または [Modify](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/変更)** チェック ボックスをオンにして、レポートまたはタスクのセキュリティ保護可能な項目へのアクセス権をセキュリティ グループに付与します。
5. **[Get](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/取得) または [Put](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/配置)** チェック ボックスをオンにして、統合およびレポートまたはタスクのセキュリティ保護可能なアクションへのアクセス権をセキュリティ グループに付与します。

**[Activate Pending Security Policy Changes](保留中のセキュリティ ポリシーの変更をアクティブ化)** を実行して、保留中のセキュリティ ポリシーの変更をアクティブにします。

### Workday モバイル ブラウザーで Workday サインイン ページを開く

Workday モバイル アプリケーションに条件付きアクセスを適用するには、アプリを外部ブラウザーで開く必要があります。 **[Edit Tenant Setup - Security](テナント設定の編集 - セキュリティ)** で、 **[Enable Mobile Browser SSO for Native Apps](ネイティブ アプリに対してモバイル ブラウザー SSO を有効にする)** をオンにします。 この場合、iOS 用のデバイスと Android 用の仕事用プロファイルに、Intune によって承認されたブラウザーがインストールされている必要があります。

[Image: Workday モバイル ブラウザー サインインのスクリーンショット。]

### 条件付きアクセス ポリシーを設定する

このポリシーは、iOS または Android デバイスでのサインインにのみ影響します。 これをすべてのプラットフォームに拡張する場合は、 **[任意のデバイス]** を選択します。 このポリシーでは、デバイスがポリシーに準拠していることを要求し、Intune によってこれを検証します。 Android には仕事用プロファイルがあるため、ユーザーは、各自の仕事用プロファイルを使用してサインインし、Intune ポータル サイトを介してアプリをインストールしていない限り、Workday にサインインすることはできません。 iOS で同じ状況が確実に適用されるようにするには、追加の手順が 1 つ必要です。

Workday では、次のアクセス制御がサポートされています。

- 多要素認証を要求する
- デバイスは準拠としてマーク済みである必要がある

Workday アプリでは、以下はサポートされていません。

- 承認済みクライアント アプリを必須にする
- アプリの保護ポリシーが必要 (プレビュー)

Workday をマネージド デバイスとして設定するには、次の手順を実行します。

[Image: [Managed Devices Only](マネージド デバイスのみ) と [クラウド アプリまたは操作] のスクリーンショット。]

1. **[ホーム]**&gt;**[Microsoft Intune]**&gt;**[条件付きアクセス ポリシー]** の順に選択します。 次に、 **[Managed Devices Only](マネージド デバイスのみ)** を選択します。
2. **[Managed Devices Only](マネージド デバイスのみ)** の **[名前]** で **[Managed Devices Only](マネージド デバイスのみ)** を選択し、 **[クラウド アプリまたは操作]** を選択します。
3. **[クラウド アプリまたは操作]** で、次の操作を行います。

    1. **[このポリシーが適用される対象を選択する]** を **[クラウド アプリ]** に切り替えます。
    2. **[必要]** で **[リソースの選択]** を選択します。
    3. **[選択]** の一覧から **[Workday]** を選択します。
    4. **[Done]** を選択します。
4. **[ポリシーを有効にする]** を **[オン]** に切り替えます。
5. **[保存]** を選択します。

**[Grant access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス権の付与)** では、次の手順を実行します。

1. **[ホーム]**&gt;**[Microsoft Intune]**&gt;**[条件付きアクセス ポリシー]** の順に選択します。 次に、 **[Managed Devices Only](マネージド デバイスのみ)** を選択します。
2. **[Managed Devices Only](マネージド デバイスのみ)** の **[名前]** で、 **[Managed Devices Only](マネージド デバイスのみ)** を選択します。 **[アクセス制御]** で **[許可]** を選択します。
3. **[許可]** で、次の操作を行います。

    1. 適用する制御として **[Grant access](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクセス権の付与)** を選択します。
    2. **[デバイスは準拠としてマーク済みである必要があります]** を選択します。
    3. **[選択したコントロールのいずれかが必要]** を選択します。
    4. **[選択]** を選択します。
4. **[ポリシーを有効にする]** を **[オン]** に切り替えます。
5. **[保存]** を選択します。

### デバイス コンプライアンス ポリシーを設定する

iOS デバイスがモバイル デバイス管理によって管理されている Workday を介してのみサインインできるようにするには、制限付きアプリの一覧に **com.workday.workdayapp** を追加して、App Store アプリをブロックする必要があります。 これにより、会社のポータルを介して Workday がインストールされているデバイスだけが Workday にアクセスできるようになります。 ブラウザーでは、デバイスは、Intune によって管理され、マネージド ブラウザーを使用している場合にのみ Workday にアクセスできます。

[Image: iOS デバイス コンプライアンス ポリシーのスクリーンショット。]

### Intune アプリ構成ポリシーを設定する

| シナリオ | キーと値のペア |
| --- | --- |
| 次の [テナント] および [Web アドレス] フィールドを自動的に設定します。● 仕事用プロファイルで Android を有効にしている場合は、Android 上の Workday。● iPad および iPhone 上の Workday。 | テナントの構成には、これらの値を使用します。● 構成キー = `UserGroupCode`● 値の型 = String ● 構成値 = ご使用のテナントの名前。 例: `gms`Web アドレスの構成には、これらの値を使用します。● 構成キー = `AppServiceHost`● 値の型 = String● 構成値 = ご使用のテナントのベース URL。 例: `https://www.myworkday.com` |
| iPad および iPhone 上の Workday に対してこれらの操作を無効にします。● 切り取り、コピー、貼り付け● 印刷 | 機能を無効にするには、次のキーの値 (ブール値) を `False` に設定します。● `AllowCutCopyPaste`● `AllowPrint` |
| Android 上の Workday でスクリーンショットを無効にします。 | 機能を無効にするには、`False` キーの値 (ブール値) を `AllowScreenshots` に設定します。 |
| ユーザーに推奨される更新プログラムを無効にします。 | 機能を無効にするには、`False` キーの値 (ブール値) を `AllowSuggestedUpdates` に設定します。 |
| アプリ ストアの URL をカスタマイズして、選択したアプリ ストアにモバイル ユーザーを誘導します。 | アプリ ストアの URL を変更するには、これらの値を使用します。● 構成キー = `AppUpdateURL`● 値の型 = String ● 構成値 = アプリ ストアの URL |

### iOS 構成ポリシー

1. [Azure portal](https://portal.azure.com/) にサインインします。
2. 「**Intune**」を検索するか、一覧でウィジェットを選択します。
3. **[クライアント アプリ]**&gt;**[アプリ]**&gt;**[アプリ構成ポリシー]** の順に移動します。 次に、 **[+ 追加]**&gt;**[マネージド デバイス]** の順に選択します。
4. 名前を入力します。
5. **[プラットフォーム]** で、 **[iOS/iPadOS]** を選択します。
6. **[関連アプリ]** で、追加した iOS 用 Workday アプリを選択します。
7. **[構成設定]** を選択します。 **[構成設定の形式]** で、 **[XML データを入力する]** を選択します。
8. XML ファイルの例を次に示します。 適用する構成を追加します。 `STRING_VALUE` を、使用する文字列に置き換えます。 `<true /> or <false />` を `<true />` または `<false />` で置き換えます。 構成を追加しない場合、この例は `True`に設定されているのと同じように機能します。

    ```
    <dict>
    <key>UserGroupCode</key>
    <string>STRING_VALUE</string>
    <key>AppServiceHost</key>
    <string>STRING_VALUE</string>
    <key>AllowCutCopyPaste</key>
    <true /> or <false />
    <key>AllowPrint</key>
    <true /> or <false />
    <key>AllowSuggestedUpdates</key>
    <true /> or <false />
    <key>AppUpdateURL</key>
    <string>STRING_VALUE</string>
    </dict>
    
    ```
9. **[追加]** を選択します。
10. ページを最新の情報に更新し、新しく作成したポリシーを選択します。
11. **[割り当て]** を選択し、アプリを適用するユーザーを選択します。
12. **[保存]** を選択します。

### Android 構成ポリシー

1. [Azure portal](https://portal.azure.com/) にサインインします。
2. 「**Intune**」を検索するか、一覧でウィジェットを選択します。
3. **[クライアント アプリ]**&gt;**[アプリ]**&gt;**[アプリ構成ポリシー]** の順に移動します。 次に、 **[+ 追加]**&gt;**[マネージド デバイス]** の順に選択します。
4. 名前を入力します。
5. **[プラットフォーム]** で、 **[Android]** を選択します。
6. **[関連アプリ]** で、追加した Android 用 Workday アプリを選択します。
7. **[構成設定]** を選択します。 **[構成設定の形式]** で、 **[JSON データを入力する]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workday-tutorial"} -->
## Microsoft Entra ID で Workday for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workday の間にシングル サインオンを構成する方法について説明します。

この記事では、Workday と Microsoft Entra ID を統合する方法について説明します。 Workday を Microsoft Entra ID と統合すると、次のことができるようになります。

- Workday にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Workday に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workday でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Workday では、**SP** によって開始される SSO がサポートされます。
- Workday モバイル アプリケーションを Microsoft Entra ID と共に構成して SSO を有効にできるようになりました。 構成方法の詳細については、こちらの[リンク](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-mobile-tutorial)を参照してください。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Workday の追加

Microsoft Entra ID への Workday の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Workday を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Workday**」と入力します。
4. 結果ウィンドウで **[Workday]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workday 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Workday に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Workday の関連ユーザーとの間にリンク関係を確立する必要があります。

Workday に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成**し、B.Simon を使用して Microsoft Entra シングル サインオンをテストします。
    2. **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **Workday の構成**- アプリケーション側で SSO 設定を構成します。
    1. **Workday テストユーザーを作成して** B.Simon に対応するユーザーを Microsoft Entra のエンタラ情報にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Workday** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] を示すスクリーンショット。]
5. **[基本的な SAML 構成]** ページで、次のフィールドの値を入力します。

    エー。 **[サインオン URL]** ボックスに、`https://impl.workday.com/<tenant>/login-saml2.flex` という形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://impl.workday.com/<tenant>/login-saml.htmld` のパターンを使用して URL を入力します

    c. **[ログアウト URL]** テキスト ボックスに、`https://impl.workday.com/<tenant>/login-saml.htmld` のパターンを使用して URL を入力します。

    注意

    これらの値は実際の値ではありません。 これらの値を実際のサインオン URL、応答 URL、ログアウト URL で更新します。 応答 URL には必ずサブドメインを入れます (例: www、wd2、wd3、wd3-impl、wd5、wd5-impl)。 `http://www.myworkday.com`のようなものを使用することは機能しますが、`http://myworkday.com`は動作しません。 これらの値を取得するには、[Workday クライアント サポート チーム](https://www.workday.com/en-us/customer-experience/support.html)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Workday アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 Workday アプリケーションでは、 **nameidentifier** が **user.mail**、 **UPN** などとマップされることを想定しています。編集 **アイコンを** 選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: このスクリーンショットは、[編集] アイコンが選択された状態の [User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性) を示しています。]

    注意

    ここでは、既定値として UPN (user.userprincipalname) を使用して名前 ID をマップしています。 SSO を正常に動作させるには、Workday アカウントの実際のユーザー ID (メール アドレス、UPN など) を使用して名前 ID をマップする必要があります。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. 要件に従って **署名** オプションを変更するには、[ **編集** ] ボタンを選択して **SAML 署名証明書** ダイアログを開きます。

    [Image: [証明書] を示すスクリーンショット。]

    エー。 **[署名オプション]** で **[SAML 応答とアサーションへの署名]** を選択します。

    b。 **[保存]** を選びます。
9. **[Workday のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: [構成 URL のコピー] を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workday の構成

1. 別の Web ブラウザー ウィンドウで、Workday 企業サイトに管理者としてサインインします。
2. ホーム ページの左上にある **[Search](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/検索)** ボックスで、「**Edit Tenant Setup – Security**」(テナントのセットアップの編集 - セキュリティ) という名前を検索します。

    [Image: [テナントのセキュリティの編集] を示すスクリーンショット。]
3. **[SAML Setup]\(SAML セットアップ\**) セクションで、[**Import Identity Provider]\(ID プロバイダーのインポート\) を選択します**。

    [Image: [SAML 設定] を示すスクリーンショット。]
4. **[Import Identity Provider](ID プロバイダーのインポート)** セクションで、次の手順を実行します。

    [Image: [ID プロバイダーのインポート] を示すスクリーンショット。]

    エー。 **ID プロバイダー名** (`AzureAD` など) をテキスト ボックスに入力します。

    b。 **[Used for Environments](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/環境に使用)** テキストボックスで、ドロップダウンから適切な環境名を選択します。

    c. [ **ファイルの選択] を選択** して、ダウンロードした **フェデレーション メタデータ XML** ファイルをアップロードします。

    d. **[OK] を選択**.
5. **[OK]** を選択すると、**SAML ID プロバイダー**に新しい行が追加され、新しく作成された行に次の手順を追加できます。

    [Image: [SAML ID プロバイダー] を示すスクリーンショット。]

    エー。 [ **IDP Initiated Logout を有効にする** ] チェック ボックスをオンにします。

    b。 **[Logout Response URL](ログアウト応答 URL)** ボックスに、「**http://www.workday.com**」と入力します。

    c. [ **Workday Initiated Logout を有効にする** ] チェック ボックスをオンにします。

    d. **[Logout Request URL] (ログアウト要求 URL)** テキストボックスに**ログアウト URL** の値を貼り付けます。

    え [ **SP Initiated]\(SP 開始済み\)** チェック ボックスをオンにします。

    f. **[Service Provider ID](サービス プロバイダー ID)** ボックスに、「**http://www.workday.com**」と入力します。

    ジー **[SP によって開始された認証要求をデフレートしない**] を選択します。

    h. **OK** を選択します。

    一. タスクが正常に完了した場合は、[ **完了]** を選択します。

    注意

    シングル サインオンが正しく設定されていることを確認してください。 セットアップが正しくないシングル サインオンを有効にした場合、資格情報を使用してアプリケーションに入ることができず、ロックアウトされることがあります。このような場合、Workday では、ユーザーが通常のユーザー名とパスワードを使用して、[ご使用の Workday URL]/login.flex?redirect=n という形式でサインインできるバックアップ ログイン URL を提供します

#### Workday テスト ユーザーの作成

1. 自分の Workday 企業サイトに管理者としてサインインします。
2. 右上隅にある **[プロファイル**] を選択し、[**アプリケーション**] タブで **[ホーム**] と [**ディレクトリ**の選択] を選択します。
3. **[Directory](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ディレクトリ)** ページで、[View](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/表示) タブの **[Find Workers](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/従業員の検索)** を選択します。

    [Image: [Find workers] (従業員の検索) を示すスクリーンショット。]
4. **[Find Workers]** (従業員の検索) ページで、結果からユーザーを選択します。
5. 次のページで、**[ジョブ] &gt; [従業員のセキュリティ]** の順に選択します。ここで、**Workday アカウント**が、Microsoft Entra ID の **[名前 ID]** の値として一致している必要があります。

    [Image: [Worker Security] (従業員のセキュリティ) を示すスクリーンショット。]

注意

Workday テスト ユーザーを作成する方法の詳細については、[Workday クライアント サポート チーム](https://www.workday.com/en-us/customer-experience/support.html)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Workday のサインオン URL にリダイレクトされます。
- Workday のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Workday] タイルを選択すると、SSO を設定した Workday に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workday-writeback-tutorial"} -->
## Microsoft Entra ID で Workday ライトバックを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-writeback-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-06
- Summary: Microsoft Entra ID から Workday への属性の書き戻しを構成する方法についてご確認ください

この記事の目的は、Microsoft Entra ID から Workday に属性を書き戻すために実行する必要がある手順を示することです。 Workday Writeback プロビジョニング アプリは、次の Workday 属性に対する値の割り当てをサポートします。

- 勤務先の電子メール
- Workday ユーザー名
- 職場の固定電話番号 (国コード、市外局番、番号、内線番号を含む)
- 職場の固定電話番号のプライマリ フラグ
- 職場の携帯電話番号 (国コード、市外局番、番号を含む)
- 職場の携帯電話のプライマリ フラグ

### 概要

[Workday からオンプレミス AD](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial) プロビジョニング アプリ、または [Workday から Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial) プロビジョニング アプリを使用して受信プロビジョニング統合を設定した後、必要に応じて、Workday 書き戻しアプリを構成して、仕事用メールや電話番号などの連絡先情報を Workday に書き込むことができます。

#### このユーザー プロビジョニング ソリューションが最適な場合

この Workday Writeback ユーザー プロビジョニング ソリューションは、次のお客様に最適です。

- IT が管理する正式な属性 (メールアドレス、ユーザー名、電話番号など) を Workday に書き戻したいと考える、Microsoft 365 を使用している組織

### Workday の統合システム ユーザーの構成

ワーカー データを取得するアクセス許可を持つ Workday 統合 [システム ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial#configure-integration-system-user-in-workday) アカウントを作成するには、統合システム ユーザーの構成に関するセクションを参照してください。

### Workday への Microsoft Entra 属性書き戻しの構成

ユーザーのメールアドレスおよびユーザー名を Microsoft Entra ID から Workday に書き戻すように構成するには、次の手順に従ってください。

- Writeback コネクタ アプリの追加と Workday への接続の作成
- 書き戻し属性マッピングを構成する
- ユーザー プロビジョニングを有効にして起動する

#### パート 1: Writeback コネクタ アプリの追加と Workday への接続の作成

**Workday Writeback コネクタを構成するには:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **Workday Writeback** を検索し、ギャラリーからそのアプリを追加します。
4. アプリが追加され、アプリの詳細画面が表示されたら、[ **プロビジョニング**] を選択します。
5. **[プロビジョニング** **モード**] を **[自動**] に変更します。
6. 次のように、[ **管理者資格情報]** セクションに入力します。

    - **管理者ユーザー名** – テナント ドメイン名が追加された Workday 統合システム アカウントのユーザー名を入力します。 次のようになります: *username@contoso4*
    - **管理者パスワード –** Workday 統合システム アカウントのパスワードを入力します
    - **テナント URL –** テナントの Workday Web サービス エンドポイントの URL を入力します。 この値は次のようになります。 `https://wd3-impl-services1.workday.com/ccx/service/contoso4/Human_Resources`、 *contoso4* は正しいテナント名に置き換えられ *、wd3-impl* は適切な環境文字列に置き換えられます (必要な場合)。
    - **通知メール –** メール アドレスを入力し、[失敗した場合にメールを送信する] チェック ボックスをオンにします。
    - [ **テスト接続** ] ボタンを選択します。 接続テストが成功した場合は、上部にある **[保存]** ボタンを選択します。 失敗した場合は、Workday URL と資格情報が Workday で有効であることを再度確認します。

#### パート 2: 書き戻し属性マッピングの構成

このセクションでは、Microsoft Entra ID から Workday への書き戻し属性の流れを構成します。

1. **マッピング** タブの [プロビジョニング] で、マッピング名を選択します。
2. [c0>ソース オブジェクト スコープ ] フィールドでは、オプションでフィルタリングを行うことで、Microsoft Entra ID のどのユーザー セットをライトバックに含めるかを指定できます。 既定のスコープは、 **Microsoft Entra ID のすべてのユーザーです**。
3. **属性マッピング** セクションで、Workday ワーカー ID または従業員 ID が格納されている Microsoft Entra ID の属性を示す一致する ID を更新してください。 一般的なマッチング メソッドは、Workday の Worker ID または従業員 ID を Microsoft Entra ID の extensionAttribute1-15 に同期してから、この Microsoft Entra ID の属性を使用して、Workday に戻ってユーザーを照合します。
4. 通常は、Microsoft Entra ID *userPrincipalName* 属性を Workday *UserID* 属性にマップし、Microsoft Entra ID *メール* 属性を Workday *EmailAddress* 属性にマップします。

[Image: Microsoft Entra 管理センターのスクリーンショット。]
5. 次のガイダンスを使用して、Microsoft Entra ID から Workday に電話番号属性の値をマップします。 ライトバック式マッピングの例を参照して、各属性の適切な式マッピングを構成してください。

    | Workday 電話番号属性 | 必要な値 | マッピングのガイダンス |
    | --- | --- | --- |
    | 職場の電話の固定回線が主要です | 真/偽 | 文字列の "true" または "false" が出力値として得られる定数または式によるマッピング |
    | 勤務電話固定電話の国コード名 | [3 文字の ISO 3166-1 国コード](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3) | 3 文字の国コードが出力として得られる定数または式によるマッピング |
    | 勤務先電話市外局番番号 | [国際国の通話コード](https://en.wikipedia.org/wiki/List_of_country_calling_codes) | 有効な国コード (+ 記号なし) が出力として得られる定数または式によるマッピング |
    | 職場電話固定番号 | 市外局番を含む完全な電話番号 | *telephoneNumber* 属性にマップします。 空白、かっこ、国番号は、正規表現を使用して削除します。 |
    | 勤務用電話の内線番号 | 内線番号 | *telephoneNumber* に内線番号が含まれている場合は、正規表現を用いてその値を抽出します。 |
    | 職場の電話が携帯であることが主要である | 真/偽 | 文字列の "true" または "false" が出力値として得られる定数マッピングまたは式によるマッピング |
    | 職場携帯電話の国コード名 | [3 文字の ISO 3166-1 国コード](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3) | 3 文字の国コードが出力として得られる定数または式によるマッピング |
    | 勤務用携帯電話の国番号 | [国際国の通話コード](https://en.wikipedia.org/wiki/List_of_country_calling_codes) | 有効な国コード (+ 記号なし) が出力として得られる定数または式によるマッピング |
    | 勤務用携帯電話番号 | 市外局番を含む完全な電話番号 | *モバイル*属性にマップします。 空白、かっこ、国番号は、正規表現を使用して削除します。 |

    注

    Change\_Work\_Contact Workday Web サービスを呼び出すと、Microsoft Entra ID から次の定数値が送信されます。

    - **Communication\_Usage\_Type\_ID**は定数文字列**WORK**に設定されています。
    - **Phone\_Device\_Type\_ID** は、携帯電話番号の場合は固定文字列 **Mobile** に、固定電話番号の場合は **Landline** に設定されます。

    ご利用の Workday テナントで異なる Type\_ID が使用されている場合、書き戻しエラーが発生します。 このようなエラーを回避するには、Workday **の参照 ID の保持** タスクを使用し、Microsoft Entra ID で使用される値と一致するようにType\_IDsを更新します。
6. マッピングを保存するには、[Attribute-Mapping] セクションの上部にある **[保存]** を選択します。

### 書き戻し式のマッピングの例

このセクションでは、一般的な統合シナリオにおける、Workday Writeback アプリケーションの構成例を示します。

- プレ採用者の書き戻しのタイミング
- 国番号と電話番号を使用した電話番号の処理
- Microsoft Entra ID *usageLocation* 属性から国コードを派生させる
- 10 桁の電話番号の抽出
- 電話番号のスペース、ダッシュ、括弧を削除する
- 固定電話番号内線の対応

#### 採用予定者の書き戻しのタイミング

Microsoft Entra ID との一般的な Workday 統合では、受信ユーザープロビジョニングアプリは、[Workday からオンプレミスの Active Directory](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial) または [Workday から Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial) を通じて、事前採用者のための新しい Microsoft Entra アカウントを作成し、ユーザー固有の電子メールと userPrincipalName を生成します。

既定では、Workday Writeback アプリは、ユーザーが Microsoft Entra ID で作成された直後に、Workday アカウントに職場のメール アドレスと userID の値の設定を行おうとします。

UserID やメールアドレスの書き戻しを遅らせて、その実行が採用日以降となるようにするには、次の手順に従います。

1. Microsoft Entra ID には *employeeHireDate* という属性があり、ユーザーの雇用開始日をキャプチャできます。
2. [Workday をオンプレミスの Active Directory](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial) プロビジョニング ジョブに使用している場合は、Workday *StatusHireDate* フィールドをオンプレミスの Active Directory の属性 (*extensionAttribute8* など) にフローするように構成します。 オンプレミスの値を Microsoft Entra ID の *employeeHireDate* に同期するように Microsoft Entra Connect を構成します。
3. [Workday to Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-cloud-only-tutorial) プロビジョニング ジョブを使用している場合は、Workday *StatusHireDate* フィールドを Microsoft Entra ID の *employeeHireDate* 属性に直接フローするように構成します。

    注

    従業員の開始日を他の Microsoft Entra ID *extensionAttribute* に格納する場合は、次の式で *employeeHireDate* の代わりにその属性を使用できます。
4. Workday Writeback アプリケーションで、次の式のルールを使用して、Microsoft Entra の userPrincipalName を Workday の UserID フィールドにエクスポートします。

    ```C
    IgnoreFlowIfNullOrEmpty(IIF(DateDiff("d", Now(), CDate([employeeHireDate])) >= 0, "", [userPrincipalName]))
    ```

    前の式では [DateDiff](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#datediff) 関数を使用して、*employeeHireDate* と [Now](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#now) 関数を使用して取得した今日の日付 (UTC) の差を評価します。 *employeeHireDate* が今日の日付以上の場合は、UserID が更新されます。 それ以外の場合は空の値が返され、 [IgnoreFlowIfNullOrEmpty](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#ignoreflowifnullorempty) 関数はこの属性をライトバックから除外します。

重要

延期した書き戻しを想定どおりに動作させるには、このユーザーのプロファイルが更新され、書き戻しの対象となるよう、オンプレミスの Active Directory または Microsoft Entra ID での操作で、到着日または採用日のちょうど 1 日前にユーザーへの変更をトリガーする必要があります。 これを、新しい属性値が古い属性値と異なるユーザー プロファイルで属性値を更新する変更にする必要があります。

#### 国番号と電話番号を使用した電話番号の処理

電話番号の書き戻し操作を成功させるには、適切な国名コードと国番号を送信することが重要です。 国コード名は [ISO 3166-1 形式](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3)に準拠する 3 文字のコードで、国コード番号は、その国の国の通話コードまたは [国際サブスクライバー ダイヤル (ISD) コードを](https://en.wikipedia.org/wiki/List_of_country_calling_codes) 指します。

この例では、 *phoneNumber* または *mobile* の Microsoft Entra ID の電話番号の値が `+<isdCode><space><phoneNumber>`形式であることを前提としています。  例: 電話番号の値が `+1 1112223333` または `+1 (111) 222-3333` に設定されている場合、 `1` は ISD コードとそれに対応する国コード名 `USA`。

これらの正規表現マッピングを使用して、適切な国名コードと国番号を Workday に送信します。 ソース属性として *telephoneNumber* または *mobile* を使用できます。 次の例では、 *telephoneNumber* を使用します。 ここでは、すべての式で [Replace](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#replace) 関数を使用します。

***WorkphoneLandlineNumber* または *WorkphoneMobileNumber* のマッピング例**

```C
Replace(Replace([telephoneNumber], , "\\+(?<isdCode>\\d* )(?<phoneNumber>.*)", , "${phoneNumber}", , ), ,"[()\\s-]+", ,"", , )
```

***WorkphoneLandlineCountryCodeNumber* または *WorkphoneMobileCountryCodeNumber* のマッピング例**

```C
Replace([telephoneNumber], , "\\+(?<isdCode>\\d*) (?<phoneNumber>.*)", , "${isdCode}", , )
```

***WorkphoneLandlineCountryCodeName* または *WorkphoneMobileCountryCodeName* のマッピング例**

次の式は isdCode を抽出し、 [Switch](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/functions-for-customizing-application-data#switch) 関数を使用して、Workday に送信する適切な国コード名を検索します。

```C
Switch(Replace([telephoneNumber], , "\\+(?<isdCode>\\d*) (?<phoneNumber>.*)", , "${isdCode}", , ), "USA",
"93", "AFG", "355", "ALB", "213", "DZA", "376", "AND", "244", "AGO",  "54", "ARG", "374", "ARM", "297", "ABW", "61", "AUS", "43", "AUT", "994", "AZE", "973", "BHR", "880", "BGD", 
"375", "BLR", "32", "BEL", "501", "BLZ", "229", "BEN", "975", "BTN", "591", "BOL", "599", "BES", "387", "BIH", "267", "BWA", "55", "BRA", "246", "IOT", "673", "BRN", "359", "BGR", 
"226", "BFA", "257", "BDI", "238", "CPV", "855", "KHM", "237", "CMR", "236", "CAF", "235", "TCD", "56", "CHL", "86", "CHN", "57", "COL", "269", "COM", "242", "COG", "243", "COD", 
"682", "COK", "506", "CRI", "225", "CIV", "385", "HRV", "53", "CUB", "357", "CYP", "420", "CZE", "45", "DNK", "253", "DJI", "593", "ECU", "20", "EGY", "503", "SLV", "240", "GNQ", 
"291", "ERI", "372", "EST", "268", "SWZ", "251", "ETH", "500", "FLK", "298", "FRO", "679", "FJI", "358", "FIN", "33", "FRA", "594", "GUF", "689", "PYF", "241", "GAB", "220", "GMB", 
"995", "GEO", "49", "DEU", "233", "GHA", "350", "GIB", "30", "GRC", "299", "GRL", "590", "GLP", "502", "GTM", "224", "GIN", "245", "GNB", "592", "GUY", "509", "HTI", "504", "HND", 
"852", "HKG", "36", "HUN", "354", "ISL", "91", "IND", "62", "IDN", "98", "IRN", "964", "IRQ", "353", "IRL", "972", "ISR", "39", "ITA", "81", "JPN", "962", "JOR", "254", "KEN", "686", 
"KIR", "850", "PRK", "82", "KOR", "383", "XKX", "965", "KWT", "996", "KGZ", "856", "LAO", "371", "LVA", "961", "LBN", "266", "LSO", "231", "LBR", "218", "LBY", "423", "LIE", "370", 
"LTU", "352", "LUX", "853", "MAC", "261", "MDG", "265", "MWI", "60", "MYS", "960", "MDV", "223", "MLI", "356", "MLT", "692", "MHL", "596", "MTQ", "222", "MRT", "230", "MUS", "262", 
"REU", "52", "MEX", "691", "FSM", "373", "MDA", "377", "MCO", "976", "MNG", "382", "MNE", "212", "MAR", "258", "MOZ", "95", "MMR", "264", "NAM", "674", "NRU", "977", "NPL", "31", 
"NLD", "687", "NCL", "64", "NZL", "505", "NIC", "227", "NER", "234", "NGA", "683", "NIU", "672", "NFK", "389", "MKD", "47", "NOR", "968", "OMN", "92", "PAK", "680", "PLW", "970", 
"PSE", "507", "PAN", "675", "PNG", "595", "PRY", "51", "PER", "63", "PHL", "870", "PCN", "48", "POL", "351", "PRT", "974", "QAT", "40", "ROU", "7", "RUS", "250", "RWA", "290", "SHN", 
"508", "SPM", "685", "WSM", "378", "SMR", "239", "STP", "966", "SAU", "221", "SEN", "381", "SRB", "248", "SYC", "232", "SLE", "65", "SGP", "421", "SVK", "386", "SVN", "677", "SLB", 
"252", "SOM", "27", "ZAF", "211", "SSD", "34", "ESP", "94", "LKA", "249", "SDN", "597", "SUR", "46", "SWE", "41", "CHE", "963", "SYR", "886", "TWN", "992", "TJK", "255", "TZA", "66", 
"THA", "670", "TLS", "228", "TGO", "690", "TKL", "676", "TON", "216", "TUN", "90", "TUR", "993", "TKM", "688", "TUV", "256", "UGA", "380", "UKR", "971", "ARE", "44", "GBR", "1", 
"USA", "598", "URY", "998", "UZB", "678", "VUT", "58", "VEN", "84", "VNM", "681", "WLF", "967", "YEM", "260", "ZMB", "263", "ZWE"
)
```

#### Microsoft Entra ID *usageLocation* 属性から国コードを派生させる

*usageLocation* 属性に基づいて Workday で国コード名と国コード番号を設定する場合は、次の式マッピングを使用して、2 文字の国コードを適切な 3 文字の国コード名と国コード番号に変換します。

***WorkphoneLandlineCountryCodeNumber* または *WorkphoneMobileCountryCodeNumber* のマッピング例**

```C
Switch([usageLocation], "1", "AF", "93", "AX", "358", "AL", "355", "DZ", "213", "AS", "1", "AD", "376", "AO", "244", "AI", "1", "AG", "1", "AR", "54", "AM", "374", "AW", "297", "AU", 
"61", "AT", "43", "AZ", "994", "BS", "1", "BH", "973", "BD", "880", "BB", "1", "BY", "375", "BE", "32", "BZ", "501", "BJ", "229", "BM", "1", "BT", "975", "BO", "591", "BQ", "599", 
"BA", "387", "BW", "267", "BR", "55", "IO", "246", "VG", "1", "BN", "673", "BG", "359", "BF", "226", "BI", "257", "CV", "238", "KH", "855", "CM", "237", "CA", "1", "KY", "1", "CF", 
"236", "TD", "235", "CL", "56", "CN", "86", "CX", "61", "CC", "61", "CO", "57", "KM", "269", "CG", "242", "CD", "243", "CK", "682", "CR", "506", "CI", "225", "HR", "385", "CU", "53", 
"CW", "599", "CY", "357", "CZ", "420", "DK", "45", "DJ", "253", "DM", "1", "DO", "1", "EC", "593", "EG", "20", "SV", "503", "GQ", "240", "ER", "291", "EE", "372", "SZ", "268", "ET", 
"251", "FK", "500", "FO", "298", "FJ", "679", "FI", "358", "FR", "33", "GF", "594", "PF", "689", "GA", "241", "GM", "220", "GE", "995", "DE", "49", "GH", "233", "GI", "350", "GR", 
"30", "GL", "299", "GD", "1", "GP", "590", "GU", "1", "GT", "502", "GG", "44", "GN", "224", "GW", "245", "GY", "592", "HT", "509", "VA", "39", "HN", "504", "HK", "852", "HU", "36", 
"IS", "354", "IN", "91", "ID", "62", "IR", "98", "IQ", "964", "IE", "353", "IM", "44", "IL", "972", "IT", "39", "JM", "1", "JP", "81", "JE", "44", "JO", "962", "KZ", "7", "KE", 
"254", "KI", "686", "KP", "850", "KR", "82", "XK", "383", "KW", "965", "KG", "996", "LA", "856", "LV", "371", "LB", "961", "LS", "266", "LR", "231", "LY", "218", "LI", "423", "LT", 
"370", "LU", "352", "MO", "853", "MG", "261", "MW", "265", "MY", "60", "MV", "960", "ML", "223", "MT", "356", "MH", "692", "MQ", "596", "MR", "222", "MU", "230", "YT", "262", "MX", 
"52", "FM", "691", "MD", "373", "MC", "377", "MN", "976", "ME", "382", "MS", "1", "MA", "212", "MZ", "258", "MM", "95", "NA", "264", "NR", "674", "NP", "977", "NL", "31", "NC", 
"687", "NZ", "64", "NI", "505", "NE", "227", "NG", "234", "NU", "683", "NF", "672", "MK", "389", "MP", "1", "NO", "47", "OM", "968", "PK", "92", "PW", "680", "PS", "970", "PA", 
"507", "PG", "675", "PY", "595", "PE", "51", "PH", "63", "PN", "870", "PL", "48", "PT", "351", "PR", "1", "QA", "974", "RE", "262", "RO", "40", "RU", "7", "RW", "250", "BL", "590", 
"SH", "290", "KN", "1", "LC", "1", "MF", "590", "PM", "508", "VC", "1", "WS", "685", "SM", "378", "ST", "239", "SA", "966", "SN", "221", "RS", "381", "SC", "248", "SL", "232", "SG", 
"65", "SX", "1", "SK", "421", "SI", "386", "SB", "677", "SO", "252", "ZA", "27", "SS", "211", "ES", "34", "LK", "94", "SD", "249", "SR", "597", "SJ", "47", "SE", "46", "CH", "41", 
"SY", "963", "TW", "886", "TJ", "992", "TZ", "255", "TH", "66", "TL", "670", "TG", "228", "TK", "690", "TO", "676", "TT", "1", "TN", "216", "TR", "90", "TM", "993", "TC", "1", "TV", 
"688", "VI", "1", "UG", "256", "UA", "380", "AE", "971", "GB", "44", "UM", "246", "US", "1", "UY", "598", "UZ", "998", "VU", "678", "VE", "58", "VN", "84", "WF", "681", "EH", "212", 
"YE", "967", "ZM", "260", "ZW", "263")
```

***WorkphoneLandlineCountryCodeName* または *WorkphoneMobileCountryCodeName* のマッピング例**

```C
Switch([usageLocation], "USA", "AF", "AFG", "AX", "ALA", "AL", "ALB", "DZ", "DZA", "AS", "ASM", "AD", "AND", "AO", "AGO", "AI", "AIA", "AG", "ATG", "AR", "ARG", "AM", "ARM", "AW", 
"ABW", "AU", "AUS", "AT", "AUT", "AZ", "AZE", "BS", "BHS", "BH", "BHR", "BD", "BGD", "BB", "BRB", "BY", "BLR", "BE", "BEL", "BZ", "BLZ", "BJ", "BEN", "BM", "BMU", "BT", "BTN", "BO", 
"BOL", "BQ", "BES", "BA", "BIH", "BW", "BWA", "BR", "BRA", "IO", "IOT", "VG", "VGB", "BN", "BRN", "BG", "BGR", "BF", "BFA", "BI", "BDI", "CV", "CPV", "KH", "KHM", "CM", "CMR", "CA", 
"CAN", "KY", "CYM", "CF", "CAF", "TD", "TCD", "CL", "CHL", "CN", "CHN", "CX", "CXR", "CC", "CCK", "CO", "COL", "KM", "COM", "CG", "COG", "CD", "COD", "CK", "COK", "CR", "CRI", "CI", 
"CIV", "HR", "HRV", "CU", "CUB", "CW", "CUW", "CY", "CYP", "CZ", "CZE", "DK", "DNK", "DJ", "DJI", "DM", "DMA", "DO", "DOM", "EC", "ECU", "EG", "EGY", "SV", "SLV", "GQ", "GNQ", "ER", 
"ERI", "EE", "EST", "SZ", "SWZ", "ET", "ETH", "FK", "FLK", "FO", "FRO", "FJ", "FJI", "FI", "FIN", "FR", "FRA", "GF", "GUF", "PF", "PYF", "GA", "GAB", "GM", "GMB", "GE", "GEO", "DE", 
"DEU", "GH", "GHA", "GI", "GIB", "GR", "GRC", "GL", "GRL", "GD", "GRD", "GP", "GLP", "GU", "GUM", "GT", "GTM", "GG", "GGY", "GN", "GIN", "GW", "GNB", "GY", "GUY", "HT", "HTI", "VA", 
"VAT", "HN", "HND", "HK", "HKG", "HU", "HUN", "IS", "ISL", "IN", "IND", "ID", "IDN", "IR", "IRN", "IQ", "IRQ", "IE", "IRL", "IM", "IMN", "IL", "ISR", "IT", "ITA", "JM", "JAM", "JP", 
"JPN", "JE", "JEY", "JO", "JOR", "KZ", "KAZ", "KE", "KEN", "KI", "KIR", "KP", "PRK", "KR", "KOR", "XK", "XKX", "KW", "KWT", "KG", "KGZ", "LA", "LAO", "LV", "LVA", "LB", "LBN", "LS", 
"LSO", "LR", "LBR", "LY", "LBY", "LI", "LIE", "LT", "LTU", "LU", "LUX", "MO", "MAC", "MG", "MDG", "MW", "MWI", "MY", "MYS", "MV", "MDV", "ML", "MLI", "MT", "MLT", "MH", "MHL", "MQ", 
"MTQ", "MR", "MRT", "MU", "MUS", "YT", "MYT", "MX", "MEX", "FM", "FSM", "MD", "MDA", "MC", "MCO", "MN", "MNG", "ME", "MNE", "MS", "MSR", "MA", "MAR", "MZ", "MOZ", "MM", "MMR", "NA", 
"NAM", "NR", "NRU", "NP", "NPL", "NL", "NLD", "NC", "NCL", "NZ", "NZL", "NI", "NIC", "NE", "NER", "NG", "NGA", "NU", "NIU", "NF", "NFK", "MK", "MKD", "MP", "MNP", "NO", "NOR", "OM", 
"OMN", "PK", "PAK", "PW", "PLW", "PS", "PSE", "PA", "PAN", "PG", "PNG", "PY", "PRY", "PE", "PER", "PH", "PHL", "PN", "PCN", "PL", "POL", "PT", "PRT", "PR", "PRI", "QA", "QAT", "RE", 
"REU", "RO", "ROU", "RU", "RUS", "RW", "RWA", "BL", "BLM", "SH", "SHN", "KN", "KNA", "LC", "LCA", "MF", "MAF", "PM", "SPM", "VC", "VCT", "WS", "WSM", "SM", "SMR", "ST", "STP", "SA", 
"SAU", "SN", "SEN", "RS", "SRB", "SC", "SYC", "SL", "SLE", "SG", "SGP", "SX", "SXM", "SK", "SVK", "SI", "SVN", "SB", "SLB", "SO", "SOM", "ZA", "ZAF", "SS", "SSD", "ES", "ESP", "LK", 
"LKA", "SD", "SDN", "SR", "SUR", "SJ", "SJM", "SE", "SWE", "CH", "CHE", "SY", "SYR", "TW", "TWN", "TJ", "TJK", "TZ", "TZA", "TH", "THA", "TL", "TLS", "TG", "TGO", "TK", "TKL", "TO", 
"TON", "TT", "TTO", "TN", "TUN", "TR", "TUR", "TM", "TKM", "TC", "TCA", "TV", "TUV", "VI", "VIR", "UG", "UGA", "UA", "UKR", "AE", "ARE", "GB", "GBR", "UM", "UMI", "US", "USA", "UY", 
"URY", "UZ", "UZB", "VU", "VUT", "VE", "VEN", "VN", "VNM", "WF", "WLF", "EH", "ESH", "YE", "YEM", "ZM", "ZMB", "ZW", "ZWE")
```

#### 10 桁の電話番号を抽出する

セルフサービス パスワード リセット (SSPR) に必要な形式で Microsoft Entra ID 内の電話番号が設定されている場合は、この正規表現を使用してください。 例: 電話番号の値が +1 1112223333 -&gt; である場合、この正規表現式によって 1112223333 が出力されます。

```C
Replace([telephoneNumber], , "\\+(?<isdCode>\\d* )(?<phoneNumber>\\d{10})", , "${phoneNumber}", , )
```

#### 電話番号のスペース、ダッシュ、かっこを削除する

(XXX) XXX-XXXX 形式で Microsoft Entra ID 内の電話番号が設定されている場合は、この正規表現を使用してください。 例: 電話番号の値が (111) 222-3333 -&gt; である場合、この正規表現式によって 1112223333 が出力されます。

```C
Replace([mobile], , "[()\\s-]+", , "", , )
```

#### 固定電話の内線番号の処理

たとえば、Microsoft Entra ID 内のすべての電話番号に内線番号があり、Workday に内線番号を設定するとします。 この例では、電話番号が `+<isdCode><space><phoneNumber><space>x<extensionNumber>` の形式で保存され、内線番号が `x` の文字の後に表示されるようになっています。

この電話番号のコンポーネントを抽出するには、次の式を使用します。

***WorkphoneLandlineNumber* のマッピング例**

*telephoneNumber* に`+1 (206) 291-8163 x8125`値がある場合、この式は`2062918163`を返します。

```C
Replace(Replace([telephoneNumber], , "\+(?<isdCode>\d* )(?<phoneNumber>.* )[x](?<extension>.*)", , "${phoneNumber}", , ), ,"[()\\s-]+", ,"", , ) 
```

***WorkphoneLandlineCountryCodeNumber* のマッピング例**

*telephoneNumber* に`+1 (206) 291-8163 x8125`値がある場合、この式は`1`を返します。

```C
Replace(Replace([telephoneNumber], , "\+(?<isdCode>\d* )(?<phoneNumber>.* )[x](?<extension>.*)", , "${isdCode}", , ), ,"[()\\s-]+", ,"", , ) 
```

***WorkphoneLandlineExtension* のマッピング例**

*telephoneNumber* に`+1 (206) 291-8163 x8125`値がある場合、この式は`8125`を返します。

```C
Replace(Replace([telephoneNumber], , "\+(?<isdCode>\d* )(?<phoneNumber>.* )[x](?<extension>.*)", , "${extension}", , ), ,"[()\\s-]+", ,"", , )
```

### ユーザー プロビジョニングの有効化と起動

Workday プロビジョニング アプリの構成が完了したら、Microsoft Entra 管理者センターでプロビジョニング サービスを有効にすることができます。

ヒント

既定では、プロビジョニング サービスを有効にすると、スコープ内のすべてのユーザーに対してプロビジョニング操作が開始されます。 マッピングのエラーまたは Workday データの問題がある場合、プロビジョニング ジョブが失敗し、検疫状態になる可能性があります。 これを回避するには、ベスト プラクティスとして、すべてのユーザーの完全同期を起動する前に、オンデマンド**プロビジョニング**機能を使用して、ソース [オブジェクト スコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand) フィルターを構成し、少数のテスト ユーザーで属性マッピングをテストすることをお勧めします。 マッピングが機能し、目的の結果が得られていることを確認したら、フィルターを削除するか、徐々に拡張してより多くのユーザーを含めることができます。

1. [ **プロビジョニング** ] タブで、[ **プロビジョニングの状態]** を **[オン]** に設定します。
2. **[スコープ**] ドロップダウンで、[**すべてのユーザーとグループの同期**] を選択します。 このオプションを使用すると、Writeback アプリは、マッピング&gt;で定義されているスコープ規則に従って、Microsoft Entra ID から Workday にすべてのユーザーの**マップ**された属性を書き戻します。

[Image: 書き戻しスコープの選択]

    注

    Workday Writeback プロビジョニング アプリでは、[ **割り当てられたユーザーとグループのみを同期** する] オプションはサポートされておらず、[すべてのユーザーとグループを同期する] オプションが選択されているかのように常に動作します。
3. **[保存] を選択します**。
4. この操作により初期同期が開始されます。これに要する時間はソース ディレクトリのユーザー数に応じて変わります。 進行状況バーをチェックして、同期サイクルの進行状況を追跡できます。
5. いつでも、Entra 管理センターの [ **プロビジョニング ログ** ] タブで、プロビジョニング サービスが実行するアクションを確認します。 監査ログには、ソースからインポートされたユーザーやターゲット アプリケーションにエクスポートされたユーザーなど、プロビジョニング サービスによって実行された個々の同期イベントがすべて表示されます。
6. 初期同期が完了すると、[ **プロビジョニング** ] タブに概要レポートが書き込まれます。

[Image: プロビジョニングの進行状況バー]

### 既知の問題と制限事項

- ライトバック アプリは、パラメーターの**Communication\_Usage\_Type\_IDとPhone\_Device\_Type\_ID**に定義済みの値を使用します。 Workday テナントで、これらの属性に別の値が使用されている場合、書き戻し操作は成功しません。 回避策として、Workday の Type\_ID を更新することをお勧めします。
- Writeback アプリがセカンダリ電話番号を更新するように構成されている場合、Workday の既存のセカンダリ電話番号は置き換えられません。 新しいセカンダリ電話番号が worker レコードに追加されます。 この動作に対する回避策はありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workfront-tutorial"} -->
## Microsoft Entra ID で Workfront for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workfront-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workfront 間でシングル サインオンを構成する方法について説明します。

この記事では、Workfront と Microsoft Entra ID を統合する方法について説明します。 Workfront と Microsoft Entra ID を統合すると、次のことができます:

- Workfront にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Workfront に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workfront でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Workfront では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Workfront の追加

Microsoft Entra ID への Workfront の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Workfront を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Workfront**」と入力します。
4. 結果のパネルから **Workfront** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workfront 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Workfront で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Workfront の関連ユーザーとの間にリンク関係を確立する必要があります。

Workfront に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workfront の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Workfront のテスト ユーザーの作成** - Workfront で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Workfront**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.attask-ondemand.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.attasksandbox.com/SAML2`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、Workfront クライアント サポート チームに問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Workfront のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workfront の SSO の構成

1. Workfront 企業サイトに管理者としてサインオンします。
2. **[Single Sign On Configuration]**に移動します。
3. **[Single Sign-On (シングル サインオン)]** ダイアログ ボックスで、次の手順を実行します。

    [Image: シングルサインオンの設定]

    ある。 **[Type]** で **[SAML 2.0]** を選択します。

    b。 **サービス プロバイダー ID** を選択します。

    c. **[Login Portal URL](ログイン ポータル URL)** ボックスに**ログイン URL** を貼り付けます。

    d. **[Sign-Out URL](サインアウト URL)** ボックスに**ログアウト URL** を貼り付けます。

    え **パスワード変更 URL** を **[Change Password URL](パスワード変更 URL)** ボックスに貼り付けます。

    f. **保存** を選択します。

#### Workfront テスト ユーザーの作成

このセクションの目的は、Workfront で Britta Simon というユーザーを作成することです。

**Workfront で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. Workfront 企業サイトに管理者としてサインオンします。
2. 上部のメニューで、[ **ユーザー**] を選択します。
3. **新しい人物**を選択します。
4. [New Person] ダイアログで、次の手順を実行します。

    [Image: Workfront テスト ユーザーの作成]

    ある。 **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに「Britta」と入力します。

    b。 **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに「Simon」と入力します。

    c. **[Email Address]** テキストボックスに、Britta Simon の Microsoft Entra ID の電子メール アドレスを入力します。

    d. [ **ユーザーの追加] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Workfront のサインオン URL にリダイレクトされます。
- Workfront のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Workfront] タイルを選択すると、このオプションは Workfront のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workgrid-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Workgrid を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workgrid-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-02
- Summary: ユーザー アカウントを Workgrid に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、ユーザーやグループを Workgrid に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するために、Workgrid とMicrosoft Entra IDで実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Workgrid でユーザーを作成します。
- アクセスが不要になったら、Workgrid のユーザーを削除します。
- Microsoft Entra IDと Workgrid の間でユーザー属性の同期を維持します。
- Workgrid でグループとそのメンバーシップをプロビジョニングする。
- Workgrid に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workgrid-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [Workgrid テナント](https://www.workgrid.com/)
- 管理者アクセス許可がある Workgrid のユーザー アカウント。

### 手順 1: Workgrid にユーザーを割り当てる

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Workgrid へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 特定した後、次の手順に従い、これらのユーザー、グループ、またはその両方を Workgrid に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Workgrid に割り当てるときの重要なヒント

- 1 人のMicrosoft Entra ユーザーを Workgrid に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Workgrid にユーザーを割り当てるときは、割り当てダイアログで、有効なアプリケーション固有ロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 2: プロビジョニング用に Workgrid を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Workgrid を構成する前に、Workgrid で SCIM プロビジョニングを有効にする必要があります。

1. Workgrid にログインします。 **[ユーザー] &gt; [ユーザー プロビジョニング]** に移動します。

    [Image: Workgrid UI のスクリーンショットで、[Users]（ユーザー）および [User Provisioning]（ユーザー プロビジョニング）のオプションが強調されています。]
2. [ **アカウント管理 API**] で、[ **資格情報の作成**] を選択します。

    [Image: [Create Credentials](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/資格情報の作成) オプションが赤い四角で囲まれている [Account Management API](アカウント管理 API) セクションのスクリーンショット。]
3. **[SCIM Endpoint](SCIM エンドポイント)** と **[Access Token](アクセス トークン)** の値をコピーします。 これらは、Workgrid アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: [Account Management API] セクションには [SCIM エンドポイント] と [アクセス トークン] が強調されているスクリーンショット。]

### 手順 3: ギャラリーから Workgrid を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Workgrid を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Workgrid を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Workgrid を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Workgrid**」と入力し、**[Workgrid]** を選択します。
4. 結果パネルで **[Workgrid]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の Workgrid]

### 手順 4: Workgrid への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Workgrid のユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Workgrid のシングル サインオンに関する記事で説明されている手順に従って、Workgrid で SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workgrid-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります

#### Microsoft Entra ID で Workgrid の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Workgrid]** を選択します。

    [Image: アプリケーションの一覧の Workgrid リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Workgrid テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Workgrid に接続できることを確認します。 接続に失敗した場合は、Workgrid アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute Mapping** セクションで、Microsoft Entra IDから Workgrid に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Workgrid のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Workgrid で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | 優先言語 | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |  |
    | externalId | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 糸 |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Workgrid に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Workgrid のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート | Workgrid で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
    | externalId | 糸 |  | ✓ |
    | members | リファレンス |  |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workgrid-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Workgrid を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workgrid-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workgrid の間にシングル サインオンを構成する方法について説明します。

この記事では、Workgrid と Microsoft Entra ID を統合する方法について説明します。 Workgrid を Microsoft Entra ID と統合すると、以下のことができます。

- Workgrid にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Workgrid に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workgrid でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Workgrid では、**SP** Initiated SSO がサポートされます。
- Workgrid では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Workgrid では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workgrid-provisioning-tutorial)がサポートされます。

### ギャラリーからの Workgrid の追加

Microsoft Entra ID への Workgrid の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Workgrid を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Workgrid**」と入力します。
4. 結果パネルで **[Workgrid]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workgrid 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Workgrid との Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Workgrid の関連ユーザーとの間にリンク関係を確立する必要があります。

Workgrid に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workgrid の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Workgrid のテストユーザーの作成** - Workgrid で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Workgrid**&gt;**シングルサインオン**を開きます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集のスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANYCODE>.workgrid.com/console`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:amazon:cognito:sp:us-east-1_<poolid>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 サインオン URL は、Workgrid コンソールへのサインインに使用する URL と同じです。 エンティティ ID は、Workgrid コンソールの [セキュリティ] セクションにあります。
6. Workgrid アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **ユーザー属性** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: ユーザー属性のスクリーンショット。]
7. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
8. **[Workgrid のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピーのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workgrid の SSO の構成

**Workgrid** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を Workgrid コンソールの **[セキュリティ] セクション**に追加する必要があります。

[Image: [セキュリティ] セクションが強調表示されている Workgrid UI のスクリーンショット。]

注

Workgrid で属性をマッピングするときは、電子メール、名前、およびファミリ名の要求に完全なスキーマ URI を使用する必要があります。

[Image: [セキュリティ] セクションの属性フィールドが表示されている Workgrid UI のスクリーンショット。]

#### Workgrid のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Workgrid に作成します。 Workgrid では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Workgrid に存在しない場合は、Workgrid にアクセスしようとしたときに新しいユーザーが作成されます。

Workgrid では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workgrid-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Workgrid のサインオン URL にリダイレクトされます。
- Workgrid のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Workgrid] タイルを選択すると、このオプションは Workgrid のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workhub-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に workhub を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workhub-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と workhub 間にシングル サインオンを構成する方法について説明します。

この記事では、workhub と Microsoft Entra ID を統合する方法について説明します。 workhub を Microsoft Entra ID と統合すると、次のことができるようになります。

- workhub にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して workhub に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な workhub のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- workhub では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの workhub の追加

Microsoft Entra ID への workhub の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に workhub を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「workhub**」と入力します。
4. 結果パネルから **workhub** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### workhub 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、workhub に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと workhub の関連ユーザーとの間にリンク関係を確立する必要があります。

workhub に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **workhub SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **workhub テスト ユーザーの作成** - Microsoft Entra のユーザー表現とリンクされた、workhub 内での B.Simon の対応ユーザーを設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**workhub**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ａ。 [ **識別子** ] ボックスに、URL を入力します。 `https://ainz-okal-gown.firebaseapp.com/__/auth/handler`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://ainz-okal-gown.firebaseapp.com/__/auth/handler`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://admin.workhub.site/sso`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Workhub のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### workhub SSO の構成

**workhub** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [workhub サポート チーム](mailto:support_work@bitkey.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### workhub のテスト ユーザーの作成

このセクションでは、workhub で Britta Simon というユーザーを作成します。 [workhub サポート チーム](mailto:support_work@bitkey.jp)と協力して、workhub プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる workhub のサインオン URL にリダイレクトされます。
- workhub のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで workhub タイルを選択すると、このオプションは workhub のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workpath-tutorial"} -->
## Microsoft Entra ID で Workpath for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workpath-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workpath の間にシングル サインオンを構成する方法について説明します。

この記事では、Workpath と Microsoft Entra ID を統合する方法について説明します。 Workpath と Microsoft Entra ID を統合すると、次のことができます。

- Workpath にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Workpath に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workpath でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Workpath は、**SP**および**IDP**によるSSOをサポートします。
- Workpath では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Workpath を追加する

Microsoft Entra ID への Workpath の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Workpath を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**を閲覧します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Workpath**」と入力します。
4. 結果パネルから **[Workpath]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workpath 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Workpath に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Workpath の関連ユーザーとの間にリンク関係を確立する必要があります。

Workpath に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workpath SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Workpath のテストユーザーの作成 -** B.Simon に対応するユーザーの Microsoft Entra での表現をリンクするため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Workpath**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.workpath.com/v1/saml/metadata/<instancename>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.workpath.com/v1/saml/assert/<instancename>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.workpath.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Workpath クライアント サポート チーム](https://www.workpath.com/en/company/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Workpath アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Workpath アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | last\_name | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Workpath のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workpath の SSO の構成

**Workpath** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Workpath サポート チーム](https://www.workpath.com/en/company/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Workpath のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Workpath に作成します。 Workpath では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Workpath にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Workpath のサインオン URL にリダイレクトされます。
- Workpath のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Workpath に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Workpath] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Workpath に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workplace-from-meta-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Workplace from Meta を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workplace-from-meta-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: 自動ユーザー プロビジョニングを構成するために、Workplace from Meta と Microsoft Entra ID の両方で実行する必要がある手順について説明します。

この記事では、Workplace from Meta と Microsoft Entra ID の両方で、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成後、Microsoft Entra IDはMicrosoft Entraプロビジョニングサービスを利用して、[Workplace from Meta](https://work.workplace.com/)に対するユーザーの自動プロビジョニングとプロビジョニング解除を実行します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Meta から Workplace でユーザーを作成する
- アクセスが不要になった場合に Workplace のユーザーを Meta から削除する
- Microsoft Entra IDと Workplace from Meta の間でユーザー属性の同期を維持する
- Workplace from Meta への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workplacebyfacebook-tutorial) (推奨)

Workplace from Meta は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Workplace from Meta でのシングル サインオンが有効なサブスクリプション

注

この記事の手順をテストするために、運用環境を使用することはお勧めしません。

この記事の手順をテストするには、次の推奨事項に従う必要があります。

- 運用環境は、必要な場合を除き、使用しないでください。
- Microsoft Entra試用版環境がない場合は、[1 か月間の無料試用版を入手](https://azure.microsoft.com/pricing/free-trial/)できます。

### 手順 1: プロビジョニングデプロイメントを計画する

プロビジョニングを構成する前に、次の計画タスクを実行します。

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとWorkplace from Metaの間で[マップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDを使用したプロビジョニングをサポートするように Workplace from Meta を構成する

プロビジョニング サービスを構成して有効にする前に、Workplace from Meta アプリにアクセスする必要があるユーザーをMicrosoft Entra IDでどのように表すかを決定する必要があります。 決定したら、次の手順に従って、これらのユーザーを Meta アプリから Workplace に割り当てることができます。

- プロビジョニング構成をテストするには、1 人のMicrosoft Entra ユーザーを Workplace from Meta に割り当てることをお勧めします。 後で割り当てられるユーザーが増える可能性があります。
- Meta から Workplace にユーザーを割り当てるときは、有効なユーザー ロールを選択する必要があります。 "既定のアクセス" ロールはプロビジョニングでは機能しません。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Meta から Workplace を追加する

Microsoft Entra アプリケーション ギャラリーから Workplace from Meta を追加して、Workplace from Meta へのプロビジョニングの管理を開始します。 シングル サインオン (SSO) のために Workplace from Meta を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 [ギャラリーからのアプリケーションの追加の](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)詳細について説明します。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

次の手順を使用して、プロビジョニングの対象となるユーザーとグループを定義します。

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Meta から Workplace への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて、Meta App から Workplace のユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、[ **メタ] から [Workplace**] を選択します。

    [Image: アプリケーションの一覧の [Workplace from Meta] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. "テナント URL" セクションに正しいエンドポイント ( `https://scim.workplace.com/`) が設定されていることを確認します。 [ **管理者資格情報** ] セクションで、[ **承認**] を選択します。 Meta の承認ページから Workplace にリダイレクトされます。 Meta ユーザー名から Workplace を入力し、[ **続行** ] ボタンを選択します。 **Test Connection** を選択して、Microsoft Entra IDが Meta から Workplace に接続できることを確認します。 接続に失敗した場合は、Workplace from Meta アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: [承認] オプションが表示された [管理者資格情報] ダイアログ ボックスを示すスクリーンショット。]

    [Image: 承認のスクリーンショット。]

    注

    URL を `https://scim.workplace.com/` に変更しないと、構成を保存しようとするとエラーが発生する
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Workplace from Meta に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Workplace from Meta のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Workplace from Meta API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | displayName | 糸 |
    | 活動中 | ブール値 |
    | タイトル | ブール値 |
    | emails[type eq "work"].value | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | name.formatted | 糸 |
    | addresses[type eq "work"].formatted | 糸 |
    | addresses[type eq "work"].streetAddress | 糸 |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |
    | addresses[type eq "work"].region | 糸 |
    | addresses[type eq "work"].country | 糸 |
    | addresses[type eq "work"].postalCode | 糸 |
    | addresses[type eq "other"].formatted | 糸 |
    | phoneNumbers[type eq "work"].value | 糸 |
    | phoneNumbers[type eq "mobile"].value | 糸 |
    | phoneNumbers[type eq "fax"].value | 糸 |
    | externalId | 糸 |
    | 優先言語 | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.manager | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.department | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.division | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.organization | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.costCenter | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.employeeNumber | 糸 |
    | urn:scim:schemas:extension:facebook:auth\_method:1.0:auth\_method | 糸 |
    | urn:scim:schemas:extension:facebook:frontline:1.0.is\_frontline | ブール値 |
    | urn:scim:schemas:extension:facebook:starttermdates:1.0.startDate | 整数 |
12. スコープ フィルターを構成するには、「 [ユーザー アカウントをプロビジョニングするための条件付きルールを定義する](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)」の手順に従います。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、組織内でより広範に展開する前に、少数のユーザーとの同期を検証します。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニング アクティビティを監視し、デプロイが期待どおりに動作していることを確認するには、次のガイダンスを使用します。

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。

### トラブルシューティングのヒント

一般的なプロビジョニングの問題を診断して解決するには、次のヒントを使用します。

- ユーザーの作成に失敗し、コード "1789003" を含む監査ログ イベントが発生した場合は、そのユーザーが未確認のドメインからのユーザーであることを意味します。
- 次のようなエラーが表示される場合があります。「エラー: 電子メール フィールドがありません: 電子メールを提供する必要があります Facebook からエラーが返されました: HTTP 要求の処理中に例外が発生しました。 詳細については、この例外の 'Response' プロパティによって返される HTTP 応答を参照してください。 この操作は 0 回再試行されました。 この日付より後に操作が再試行されます。' このエラーの原因は、メールを userPrincipalName ではなく Facebook の電子メールにマップしているが、一部のユーザーにメール属性がないためです。 このエラーを回避して、エラーが発生したユーザーを Workplace from Facebook に正常にプロビジョニングするには、Workplace from Facebook の電子メール属性への属性マッピングを結合 ([mail]、[userPrincipalName]) に変更するか、Workplace from Facebook からユーザーの割り当てを解除するか、ユーザーの電子メール アドレスをプロビジョニングします。
- Workplace にはオプションがあります。これにより、電子メール アドレスを持たない [ユーザーの存在が許可されます。](https://www.workplace.com/resources/tech/account-management/email-less#enable)この設定が Workplace 側で切り替えられる場合は、メールのないユーザーが Workplace で正常に作成されるように、Azure側のプロビジョニングを再開する必要があります。

### Meta SCIM 2.0 エンドポイントから Workplace を使用するように Meta アプリケーションから Workplace を更新する

Facebook では、2021 年 12 月に SCIM 2.0 コネクタをリリースしました。 このセクションの手順を完了すると、SCIM 1.0 エンドポイントを使用して SCIM 2.0 エンドポイントを使用するように構成されたアプリケーションが更新されます。 次の手順では、以前に Workplace に対して行われたカスタマイズを Meta アプリケーションから削除します。

- 認証の詳細
- スコープ フィルター
- カスタム属性マッピング

注

次の手順を完了する前に、前のセクションに示した設定に加えられた変更を必ずメモしておいてください。 これを行わないと、カスタマイズされた設定が失われます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Workplace from Meta** に移動します。
3. 新しいカスタム アプリの [プロパティ] セクションで、 [オブジェクト ID] をコピーします。

    Azure ポータルの [Image: Workplace from Meta アプリのスクリーンショット]
4. 新しい Web ブラウザー ウィンドウで、https://developer.microsoft.com/graph/graph-explorer に移動し、アプリが追加されるMicrosoft Entra テナントの管理者としてサインインします。

    [Image: Microsoft Graph エクスプローラーのサインイン ページのスクリーンショット]
5. 使用されているアカウントに適切なアクセス許可が付与されていることを確認します。 この変更を行うには、アクセス許可 "Directory.ReadWrite.All" が必要です。

    [Image: Microsoft Graph設定オプションのスクリーンショット]

    [Image: Microsoft Graph権限のスクリーンショット]
6. アプリの [プロパティ] セクションから手順 3 でコピーしたオブジェクト ID を使用して、次のコマンドを実行して、サービス プリンシパル用に構成された同期ジョブを一覧表示します。

    ```http
    GET https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs/
    ```
7. 前の手順で `GET` 要求の応答本文から "id" 値を取得し、次のコマンドを実行し、"[job-id]" を `GET` 要求の id 値に置き換えます。 値は、"FacebookAtWorkOutDelta.xxxxxxxxxxxxxxx.xxxxxxxxxxxxxxx" という形式にする必要があります。

    ```http
    DELETE https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs/[job-id]
    ```
8. Microsoft Graph エクスプローラーで次のコマンドを実行し、FacebookWorkplace テンプレートを使用して新しい同期ジョブを作成します。 "[object-id]" を、アプリの [プロパティ] セクションからコピーしたサービス プリンシパルオブジェクト ID に置き換えます。

    ```http
    POST https://graph.microsoft.com/beta/servicePrincipals/[object-id]/synchronization/jobs { "templateId": "FacebookWorkplace" }
    ```

    [Image: Microsoft Graph要求のスクリーンショット]
9. 最初の Web ブラウザー ウィンドウに戻り、アプリケーションの [プロビジョニング] タブを選択します。 構成がリセットされます。 ジョブ ID が "FacebookWorkplace" で始まれば、アップグレードが成功したことを確認できます。
10. [管理者資格情報] セクションのテナント URL を次の URL に更新します。 `https://scim.workplace.com/`

    [Image: Azure ポータルの Workplace from Meta アプリの管理者資格情報のスクリーンショット]
11. アプリケーションに対して加えた以前の変更 (認証の詳細、スコープ フィルター、カスタム属性マッピング) を復元し、プロビジョニングを再び有効にします。

    注

    認証の詳細、スコープ フィルター、およびカスタム属性マッピングを復元できないと、Workplace で属性 (name.formatted など) が予期せず更新される可能性があります。 プロビジョニングを有効にする前に必ず構成を確認してください。

### 変更ログ

この統合ガイダンスには、次の変更が加えられます。

- 2020 年 9 月 10 日 - エンタープライズ属性 "division"、"organization"、"costCenter"、"employeeNumber" のサポートが追加されました。カスタム属性 "startDate"、"auth\_method"、"現場線" のサポートが追加されました。
- 2021年7月22日 - メールをFacebookメールにマッピングするためのトラブルシューティングのヒントを更新しましたが、一部のユーザーにはメール属性がありません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workplacebyfacebook-tutorial"} -->
## Microsoft Entra ID で Workplace by Meta for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workplacebyfacebook-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workplace by Meta 間にシングル サインオンを構成する方法について説明します。

この記事では、Workplace by Meta と Microsoft Entra ID を統合する方法について説明します。 Workplace by Meta を Microsoft Entra ID を統合すると、次のことができます:

- Workplace by Meta にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Workplace by Meta に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workplace by Meta でのシングル サインオン (SSO) が有効なサブスクリプション。

注

Meta には、Workplace Standard (無料) と Workplace Premium (有料) の 2 つの製品があります。 すべての Workplace Premium テナントは、他のコストやライセンスを必要とせずに、SCIM と SSO の統合を構成できます。 SSO と SCIM は、Workplace Standard インスタンスでは使用できません。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Workplace by Meta では、 **SP** によって開始される SSO がサポートされます。
- Workplace by Meta では、 **Just-In-Time プロビジョニングが**サポートされています。
- Workplace by Meta では、 **[自動ユーザー プロビジョニングが](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workplace-by-facebook-provisioning-tutorial)**サポートされています。
- Workplace by Meta Mobile アプリケーションを Microsoft Entra ID と共に構成して SSO を有効にできるようになりました。 この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

### ギャラリーからの Workplace by Meta の追加

Microsoft Entra ID への Workplace by Meta の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに Workplace by Meta を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに「**Workplace by Meta**」と入力します。
4. 結果パネルから **Workplace by Meta** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workplace by Meta 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Workplace by Meta に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Workplace by Meta の関連ユーザーとの間にリンク関係を確立する必要があります。

Workplace by Meta に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workplace by Meta の SSO の構成**- アプリケーション側で単一 Sign-On 設定を構成します。
    1. **Workplace by Meta のテストユーザーを作成** - Microsoft Entra 内のユーザー表現にリンクされている Workplace by Meta 内の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Workplace by Meta** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ](受信者 URL として WorkPlace にあります) テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://.workplace.com/work/saml.php`

    b。 識別子 **(エンティティ ID)** (対象ユーザー URL として WorkPlace で見つかりました) テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.workplace.com/company/`

    c. [ **応答 URL** ] (アサーション コンシューマー サービスとして WorkPlace にあります) テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://.workplace.com/work/saml.php`

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 Workplace コミュニティの正しい値については、Workplace Company ダッシュボードの認証ページを参照してください。これについては、この記事の後半で説明します。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Workplace by Meta のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workplace by Meta SSO の構成

1. 別の Web ブラウザーのウィンドウで、Workplace by Meta 企業サイトに管理者としてサインインします

    注

    SAML 認証プロセスの一環として、Microsoft Entra ID にパラメーターを渡すために Workplace が最大サイズ 2.5 KBのクエリ文字列を使用する可能性があります。
2. **[管理パネル**&gt;**セキュリティ**&gt;**認証**] タブに移動します。

    [Image: 管理パネル]

    ある。 **[シングル サインオン (SSO)] オプションを**オンにします。

    b。 新しいユーザーの既定として **SSO** を選択します。

    c. **[+ 新しい SSO プロバイダーの追加] を選択します**。

    注

    [Password login](パスワード ログイン) チェック ボックスもオンにしてください。 証明書のロールオーバー中に管理自身がロックアウトされてしまうことを防ぐために、そのような場合のログイン目的でこのオプションが必要になることがあります。
3. **[Single Sign-On (SSO) Setup]\(シングル Sign-On (SSO) セットアップ\)** ポップアップ ウィンドウで、次の手順を実行します。

    [Image: [認証] タブ]

    ある。 **SSO プロバイダーの名前に**、Azureadsso などの SSO インスタンス名を入力します。

    b。 **[SAML URL**] ボックスに、**ログイン URL** の値を貼り付けます。

    c. **[SAML Issuer URL]\(SAML 発行者 URL**\) ボックスに、**Microsoft Entra Identifier** の値を貼り付けます。

    d. ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容をクリップボードにコピーして、[ **SAML 証明書** ] ボックスに貼り付けます。

    え インスタンスの**対象ユーザー URL を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子 (エンティティ ID)]** ボックスに貼り付けます。

    f. インスタンスの**受信者 URL を**コピーし、[**基本的な SAML 構成**] セクションの **[サインオン URL**] ボックスに貼り付けます。

    ジー インスタンスの **ACS (Assertion Consumer Service) URL を**コピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。

    h. セクションの一番下までスクロールし、[SSO の **テスト** ] ボタンを選択します。 これにより、Microsoft Entra ログイン ページで表示されるポップアップ ウィンドウが表示されます。 通常どおり資格情報を入力して認証を行います。

    **トラブルシューティング：** Microsoft Entra ID から返されるメール アドレスが、ログインしている Workplace アカウントと同じであることを確認します。

    一. テストが正常に完了したら、ページの下部までスクロールし、[ **保存** ] ボタンを選択します。

    j. Workplace を使用しているすべてのユーザーに、認証用の Microsoft Entra ログイン ページが表示されるようになりました。
4. **SAML ログアウト リダイレクト (省略可能)** -

    必要に応じて、Microsoft Entra ID のログアウト ページの指定に使用される SAML ログアウト URL を構成できます。 この設定が有効に構成されている場合は、ユーザーに対して Workplace ログアウト ページが表示されなくなります。 代わりに、ユーザーは SAML ログアウト リダイレクト設定で追加された URL にリダイレクトされます。

#### 再認証の頻度の構成

SAML チェックの要求を毎日、3 日ごと、1 週間ごと、2 週間ごと、1 か月ごとに行う、または行わないように、Workplace を構成できます。

注

モバイル アプリケーションで設定できる SAML チェック頻度の最小値は 1 週間です。

また、[Require SAML authentication for all users now](すべてのユーザーに SAML 認証を要求する) ボタンを使用して、すべてのユーザーに SAML の再設定を強制できます。

#### Workplace by Meta テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Workplace by Meta に作成します。 Workplace by Meta では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。

このセクションにはアクションはありません。 Workplace by Meta にユーザーが 1 人もいない場合は、Workplace by Meta にアクセスしようとしたときに新しいユーザーが作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Workplace by Meta クライアント サポート チーム](https://www.workplace.com/help/work/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Workplace by Meta のサインオン URL にリダイレクトされます。
- Workplace by Meta のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Workplace by Meta] タイルを選択すると、このオプションは Workplace by Meta のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

### Workplace by Meta (モバイル) の SSO をテストする

1. Workplace by Meta Mobile アプリケーションを開きます。 サインイン ページで、[ログイン] を選択 **します**。

    [Image: サインイン]
2. ビジネス用メール アドレスを入力し、[続行] を選択 **します**。

    [Image: 電子メール]
3. **1 回だけ**選択します。

    [Image: 一度]
4. [許可] を選択します。

    [Image: 許可]
5. 最後に、サインインに成功すると、アプリケーションのホームページが表示されます。

    [Image: ホーム ページ]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workrite-tutorial"} -->
## Microsoft Entra ID で Workrite for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workrite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workrite の間のシングル サインオンを構成する方法について説明します。

この記事では、Workrite と Microsoft Entra ID を統合する方法について説明します。 Workrite を Microsoft Entra ID と統合すると、次のことが可能になります。

- Workrite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Workrite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workrite でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Workrite では、**SP** initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Workrite の追加

Microsoft Entra ID への Workrite の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Workrite を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Workrite**」と入力します。
4. 結果のパネルから **[Workrite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workrite 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Workrite に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Workrite の関連ユーザーの間にリンク関係を確立する必要があります。

Workrite に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workrite SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Workrite テスト ユーザーの作成** - Workrite で B.Simon に対応するユーザーを作成し、Microsoft Entra でのこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Workrite**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.workrite.co.uk/securelogin/samlgateway.aspx?id=<uniqueid>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[Workrite クライアント サポート チーム](mailto:support@workrite.co.uk)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Set up Workrite](Workrite の設定)** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workrite の SSO の構成

**Workrite** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Workrite サポート チーム](mailto:support@workrite.co.uk)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Workrite のテスト ユーザーの作成

このセクションの目的は、Workrite で Britta Simon というユーザーを作成することです。

**Workrite で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. Workrite 企業サイトに管理者としてサインオンします。
2. ナビゲーション ウィンドウで、[管理者] を選択 **します**。

    [Image: 管理者の制御]
3. クイック リンクに移動し、[ **ユーザーの作成**] を選択します。

    [Image: [ユーザーの作成] セクション]
4. [**ユーザーの作成**] ダイアログ ボックスで、次の手順を実行します。

    [Image: ユーザー ダイアログの作成]

    a. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メール)** ボックスに、ユーザーのメール アドレス (Brittasimon@contoso.com など) を入力します。

    b。 **[名]** ボックスに、ユーザーの名を入力します (この例では Britta)。

    c. **[姓]** ボックスに、ユーザーの姓を入力します (この例では Simon)。

    d. **[ロールの選択]** で **[クライアント管理者]** を選択します。

    e. **保存** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Workrite のサインオン URL にリダイレクトされます。
- Workrite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Workrite] タイルを選択すると、このオプションは Workrite のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workshop-tutorial"} -->
## Microsoft Entra ID で Workshop for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workshop-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-13
- Summary: Microsoft Entra ID と Workshop 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Workshop と Microsoft Entra ID を統合する方法について説明します。 Workshop を Microsoft Entra ID と統合すると、次のことができるようになります。

- Workshop にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Workshop に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workshop でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Whokshop では、**SP と IDP** による initiated SSO がサポートされます。
- Workshop では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Workshop を追加する

Microsoft Entra ID への Workshop の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Workshop を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Workshop**」と入力します。
4. 結果のパネルから **[Workshop]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workshop 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Workshop に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Workshop の関連ユーザーとの間にリンク関係を確立する必要があります。

Workshop に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workshop SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Workshop テスト ユーザーの作成** - Workshop における B.Simon の対応ユーザーを作成し、ユーザーの Microsoft Entra 表現にリンクさせる。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Workshop**&gt;**シングルサインオン**に参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    ある。 **[識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかの URL を入力します。

    | 識別子 |
    | --- |
    | `https://app.useworkshop.com/auth/auth/saml/metadata?id=<ID>` |
    | `https://app-eu.useworkshop.com/auth/auth/saml/metadata?id=<ID>` |

    b。 **[応答 URL]** ボックスに、次のいずれかの URL を入力します。

    | 応答 URL |
    | --- |
    | `https://app.useworkshop.com/auth/auth/saml/callback?id=<ID>` |
    | `https://app-eu.useworkshop.com/auth/auth/saml/callback?id=<ID> ` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、次のいずれかの URL を入力します。

    | サインオン URL |
    | --- |
    | `https://app.useworkshop.com/auth/auth/saml?id=<ID>` |
    | `https://app-eu.useworkshop.com/auth/auth/saml?id=<ID>` |

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Workshop サポート チーム](mailto:help@useworkshop.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Workshop アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
8. その他に、Workshop アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名（ファーストネーム） | User.givenname |
    | last\_name | User.surname |
    | メール | User.mail |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workshop SSO を構成する

1. 別のブラウザー ウィンドウで、Workshop に管理者としてログインします。
2. 右上隅にあるプロファイル アイコンを選択し、一覧から **[設定]** を選択します。
3. **[設定]** で、[**SSO**] タブに移動し、[**SAML の追加]** を選択します。

    [Image: ワークショップの SSO の構成]
4. **[Idp metadata url] (IDP メタデータ URL)** テキスト ボックスに、先ほどコピーした **[アプリのフェデレーション メタデータ URL]** の値を貼り付けます。

    [Image: メタデータ URL のスクリーンショット]
5. **SSOを作成**を選択します。

#### Workshop のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Workshop に作成します。 Workshop では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Workshop にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Workshop のサインオン URL にリダイレクトされます。
- Workshop のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Workshop に自動的にサインインします

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Workshop] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Workshop に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/worksmobile-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に LINE WORKS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/worksmobile-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と LINE WORKS 間にシングル サインオンを構成する方法について学習します。

この記事では、LINE WORKS と Microsoft Entra ID を統合する方法について説明します。 LINE WORKS と Microsoft Entra ID の統合には、次の利点があります。

- LINE WORKS にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで LINE WORKS に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- LINE WORKS でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- LINE WORKS では、**SP** によって開始される SSO がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの LINE WORKS の追加

Microsoft Entra ID への LINE WORKS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に LINE WORKS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**LINE WORKS**」と入力します。
4. 結果のパネルから **[LINE WORKS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、LINE WORKS に対して Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと LINE WORKS の関連するユーザーとの間にリンク関係を確立する必要があります。

LINE WORKS に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **LINE WORKS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **LINE WORKS のテストユーザーの作成** - LINE WORKS で Microsoft Entra における Britta Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**LINE WORKS**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.worksmobile.com/d/login/<domain>/`

    b。 **[応答 URL]** ボックスに、`https://auth.worksmobile.com/acs/ <domain>` というパターンを使用して URL を入力します。

    注

    これらの値は実際の値ではありません。 これらの値を、実際のサインオン URL および応答 URL で更新してください。 この値を取得するには、[LINE WORKS サポート チーム](https://line.worksmobile.com/jp/en/contactus/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[LINE WORKS のセットアップ]** セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### LINE WORKS SSO の構成

**LINE WORKS** 側でシングル サインオンを構成する場合は、[LINE WORKS SSO のドキュメント](https://jp1-developers.worksmobile.com/jp/docs/?lang=en)を参照し、LINE WORKS 設定を構成してください。

注

.cert to .pem からダウンロードした証明書ファイルを変換する必要があります。

#### LINE WORKS テスト ユーザーの作成

このセクションでは、LINE WORKS で Britta Simon というユーザーを作成します。 [LINE WORKS の管理者ページ](https://admin.worksmobile.com)にアクセスし、LINE WORKS プラットフォームでユーザーを追加します。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる LINE WORKS のサインオン URL にリダイレクトされます。
2. LINE WORKS のサインオン URL に直接移動し、そこからログイン フローを開始します。
3. Microsoft アクセス パネルを使用することができます。 アクセス パネルで [LINE WORKS] タイルを選択すると、LINE WORKS のサインオン URL にリダイレクトされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workspotcontrol-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Workspot Control を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workspotcontrol-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workspot Control の間にシングル サインオンを構成する方法について説明します。

この記事では、Workspot Control と Microsoft Entra ID を統合する方法について説明します。 Workspot Control を Microsoft Entra ID と統合すると、次のことができるようになります。

- Workspot Control にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Workspot Control に自動的にサインインできるようにすることができます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workspot Control のシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Workspot Control では、SP-initiated SSO と IDP-initiated SSO がサポートされます。

### ギャラリーからの Workspot Control の追加

Microsoft Entra ID への Workspot Control の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Workspot Control を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Workspot Control**」と入力します。
4. 結果ウィンドウで **[Workspot Control]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workspot Control 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Workspot Control で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Workspot Control の関連ユーザーとの間にリンク関係を確立する必要があります。

Workspot Control で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workspot Control の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Workspot Control のテスト ユーザーの作成** - Microsoft Entra のユーザー表現として B.Simon の対応ユーザーを Workspot Control で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Workspot Control**&gt;**Single のサインオンに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを IDP-initiated モードで構成する場合は、次の手順のようにします。

    1. **[識別子]** ボックスに、次の形式で URL を入力します。`https://<<i></i>INSTANCENAME>-saml.workspot.com/saml/metadata`
    2. **[応答 URL]** ボックスに、次の形式で URL を入力します。`https://<<i></i>INSTANCENAME>-saml.workspot.com/saml/assertion`
6. SP 開始モードでアプリケーションを構成する場合は、[ **追加の URL の設定]** を選択します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。`https://<<i></i>INSTANCENAME>-saml.workspot.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を置き換えます。 これらの値を取得するには、[Workspot Control クライアント サポート チーム](mailto:support@workspot.com)に問い合わせてください。 また、**[基本的な SAML 構成]** セクションのパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで **[ダウンロード]** を選択し、要件に従って使用可能なオプションから**証明書 (Base64)** をダウンロードします。 それを自分のコンピューターに保存します。

    [Image: 証明書 (Base64) ダウンロード リンク]
8. **[Workspot Control の設定]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workspot Control の SSO の構成

1. 異なる Web ブラウザー ウィンドウで、セキュリティ管理者として Workspot Control にサインインします。
2. ページの上部にあるツール バーで **[Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セットアップ)** を選択し、次に **[SAML]** を選択します。

    [Image: セットアップ オプション]
3. **[Security Assertion Markup Language Configuration](Security Assertion Markup Language の構成)** ウィンドウで、次の手順のようにします。

    [Image: [Security Assertion Markup Language Configuration](Security Assertion Markup Language の構成) ウィンドウ]

    1. **[Entity ID] (エンティティ ID)** ボックスに、コピーした **Microsoft Entra 識別子**を貼り付けます。
    2. **[Signon Service URL] (サインオン サービス URL)** ボックスに、コピーした**ログイン URL** を貼り付けます。
    3. **[Logout Service URL] (ログアウト サービス URL)** ボックスに、コピーした**ログアウト URL** を貼り付けます。
    4. **[Update File] (ファイルの更新)** を選んで、ダウンロードした base 64 でエンコードされた証明書を X.509 証明書にアップロードします。
    5. **保存** を選択します。

#### Workspot Control のテスト ユーザーを作成する

Microsoft Entra ユーザーが Workspot Control にサインインできるようにするには、ユーザーを Workspot Control にプロビジョニングする必要があります。 プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順のようにします。**

1. セキュリティ管理者として Workspot Control にサインインします。
2. ページの上部にあるツール バーで **[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** を選択し、次に **[Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加)** を選択します。

    [Image: [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) オプション]
3. **[Add a New User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの追加)** ウィンドウで、次の手順のようにします。

    [Image: [Add a New User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいユーザーの追加) ウィンドウ]

    1. **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名を入力します (例: **Britta**)。
    2. **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (例: **simon**)。
    3. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** ボックスに、ユーザーのメール アドレスを入力します (例: **Brittasimon@contoso.com**)。
    4. **[Role](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ロール)** ドロップダウン リストから適切なユーザー ロールを選択します。
    5. **[Group](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/グループ)** ドロップダウン リストから適切なユーザー グループを選択します。
    6. [ **ユーザーの追加] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Workspot Control Sign on URL にリダイレクトされます。
- Workspot Control のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Workspot Control に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Workspot Control] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Workspot Control に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workstars-tutorial"} -->
## Microsoft Entra ID で Workstars for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workstars-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workstars の間にシングル サインオンを構成する方法について説明します。

この記事では、Workstars と Microsoft Entra ID を統合する方法について説明します。 Workstars と Microsoft Entra ID の統合には、次の利点があります。

- Workstars にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Workstars に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの場所でアカウントを管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「[Microsoft Entra ID を使ったアプリケーション アクセスとシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)」を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workstars でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Workstars では、**IDP** によって開始される SSO がサポートされます

### ギャラリーからの Workstars の追加

Microsoft Entra ID への Workstars の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Workstars を追加する必要があります。

**ギャラリーから Workstars を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Workstars」**と入力し、結果パネルで **Workstars** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Workstars]

### Microsoft Entra シングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Workstars で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Workstars の関連ユーザーの間にリンク関係を確立する必要があります。

Workstars で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. **Microsoft Entra シングル サインオンを構成する** - ユーザーがこの機能を使用できるようにします。
2. **Workstars のシングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Workstars テストユーザーの作成** - Britta Simon に対応するユーザーを Workstars で作成し、Microsoft Entra のユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Workstars で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Workstars** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン構成のリンク]
3. **[シングル サインオン方式の選択]** ダイアログで、 **[SAML/WS-Fed]** モードを選択して、シングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成を編集する]
5. **[SAML でシングル サインオンをセットアップします]** ページで、次の手順を実行します。

    [Image: [Workstars のドメインと URL] のシングル サインオン情報]

    ある。 **[識別子]** テキスト ボックスに、`https://workstars.com` という URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://<subdomain>.workstars.com/saml/login_check` のパターンを使用して URL を入力します

    注意

    これは実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、[Workstars クライアント サポート チーム](http://support.workstars.com/)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Set up Workstars]\(Workstars の設定\)** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Workstars のシングル サインオンの構成

1. 別の Web ブラウザー ウィンドウで、管理者として Workstars 企業サイトにサインオンします。
2. メイン ツールバーで、[ **設定]** を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) ボタンを示すスクリーンショット。]
3. **[Sign On]\(サインオン\)**&gt;**[Settings]\(設定\)** の順に移動します。

    [Image: Workstars の [Sign On](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サインオン)]

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) を選択できる [Single Sign On](シングル サイン オン) セクションを示すスクリーンショット。]
4. **[Single Sign On (SAML) - Settings]\(シングル サインオン (SAML) - 設定\)** ページで、次の手順を実行します。

    [Image: Workstars の SAML]

    ある。 **[Identity Provider Name]\(ID プロバイダー名\)** ボックスに「**Office 365**」と入力します。

    b。 **[Identity Provider Entity ID](ID プロバイダー エンティティ ID)** テキストボックスに **Microsoft Entra 識別子**の値を貼り付けます。

    c. ダウンロードした証明書をメモ帳で開き内容をコピーして、 **[x509 Certificate]\(x509 証明書\)** ボックスに貼り付けます。

    d. **[SAML SSO URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    え **[リモート ログアウト URL]** テキスト ボックスに **[ログアウト URL]** の値を貼り付けます。

    f. **[Name ID]\(名前 ID\)** で **[Email (Default)]\(電子メール (デフォルト)\)** を選択します。

    ジー **確認** を選択します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Workstars のテスト ユーザーの作成

このセクションでは、Workstars で Britta Simon というユーザーを作成します。 [Workstars サポート チーム](http://support.workstars.com)と連携して、Workstars プラットフォームにユーザーを追加してください。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra シングル サインオン構成をテストします。

アクセス パネルで [Workstars] タイルを選択すると、SSO を設定した Workstars に自動的にサインインします。 アクセス パネルの詳細については、[アクセス パネルの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workteam-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Workteam を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workteam-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-02
- Summary: ユーザー アカウントを Workteam に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、WorkteamとMicrosoft Entra IDで実行する手順を示し、ユーザーやグループをWorkteamに自動的にプロビジョニングおよびディプロビジョニングできるようにMicrosoft Entra IDを構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Workteam でユーザーを作成します。
- アクセスが不要になった場合は、Workteam のユーザーを削除します。
- Workteam への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workteam-tutorial) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entraユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [Workteam テナント](https://workte.am/pricing.html)
- 管理者アクセス許可がある Workteam のユーザー アカウント。

### 手順 1: Workteam にユーザーを割り当てる

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Workteam へのアクセスが必要なMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを Workteam に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Workteam に割り当てるときの重要なヒント

- 1 人のMicrosoft Entra ユーザーを Workteam に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- Workteam にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールを持つユーザーは、プロビジョニングから除外されます。

### 手順 2: プロビジョニング用に Workteam を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Workteam を構成する前に、Workteam で SCIM プロビジョニングを有効にする必要があります。

1. [Workteam](https://app.workte.am/account/signin) にログインします。 組織 **の設定**&gt;**SETTINGS を選択します**。

    [Image: [組織] の設定と [設定] オプションが強調表示されている Workteam U I のスクリーンショット。]
2. 一番下までスクロールし、Workteam のプロビジョニング機能を有効にします。

    [Image: [設定] セクションの下部のスクリーンショット。[S C I M User Provisioning] 歯車アイコンが強調表示されています。]
3. **ベース URL** と**ベアラー トークン**をコピーします。 これらの値は、Workteam アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

    [Image: BASE U R L と BEARER TOKEN テキスト ボックスが強調表示されている [S C I M 設定] ダイアログ ボックスのスクリーンショット。]

### 手順 3: ギャラリーから Workteam を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Workteam を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Workteam を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Workteam を追加するには、次の手順を実行します:**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。
3. **[ギャラリーから追加**] セクションで、「**Workteam」**と入力し、検索ボックスで **Workteam** を選択します。
4. 結果パネルから **Workteam** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の作業チーム]

### 手順 4: Workteam への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、Workteam でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Workteam のシングル サインオンに関する記事に記載されている手順に従って、Workteam に対して SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workteam-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra ID で Workteam の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Workteam** を選択します。

    [Image: アプリケーションの一覧の Workteam リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Workteam テナントの URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Workteam に接続できることを確認します。 接続に失敗した場合は、Workteam アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute Mapping** セクションで、Microsoft Entra IDから Workteam に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Workteam のユーザー アカウントとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    [Image: Workteam ユーザー属性のスクリーンショット。]
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workteam-tutorial"} -->
## Microsoft Entra ID で Workteam for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workteam-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workteam の間のシングル サインオンを構成する方法について説明します。

この記事では、Workteam と Microsoft Entra ID を統合する方法について説明します。 Workteam を Microsoft Entra ID と統合すると、次のことが可能になります。

- Workteam にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Workteam に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workteam でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Workteam では、**SP および IDP** Initiated SSO がサポートされます。
- Workteam では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workteam-provisioning-tutorial)がサポートされます。

### ギャラリーからの Workteam の追加

Microsoft Entra ID への Workteam の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Workteam を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Workteam**」と入力します。
4. 結果パネルから **Workteam** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workteam 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Workteam に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Workteam の関連ユーザーの間にリンク関係を確立する必要があります。

Workteam に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workteam の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Workteam テストユーザーの作成 - B.Simon に対応するユーザーを Workteam に作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Workteam]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.workte.am`
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Workteam のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workteam の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Workteam 企業サイトに管理者としてサインインします。
2. 右上隅にある **プロファイル ロゴ** を選択し、[ **組織の設定**] を選択します。

    [Image: Workteam の設定を示すスクリーンショット。]
3. [ **認証** ] セクションで、[ **設定ロゴ]** を選択します。

    [Image: Workteam azure を示すスクリーンショット。]
4. [ **SAML 設定]** ページで、次の手順を実行します。

    [Image: Workteam の SAML を示すスクリーンショット。]

    a. **[SAML IdP]** で **[AD Azure]** を選択します。

    b。 **[SAML シングル サインオン サービス URL]** テキストボックスに、先ほどコピーした **[ログイン URL]** の値を貼り付けます。

    c. **[SAML エンティティ ID]** ボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    d. ダウンロードした **Base-64 でエンコードされた証明書**をメモ帳で開き、その内容をコピーして **[SAML Signing Certificate (Base64)](SAML 署名証明書 (Base64))** ボックスに貼り付けます。

    e. **[OK] を選択**.

#### Workteam のテスト ユーザーの作成

Microsoft Entra ユーザーが Workteam にサインインできるようにするには、そのユーザーを Workteam にプロビジョニングする必要があります。 Workteam では、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. セキュリティ管理者として Workteam にサインインします。
2. [ **組織の設定** ] ページの上部中央にある [ **ユーザー** ] を選択し、[ **新しいユーザー**] を選択します。

    [Image: Workteam ユーザーを示すスクリーンショット。]
3. **[New employee](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しい従業員)** ページで、次の手順を実行します。

    [Image: Workteam の新しいユーザーを示すスクリーンショット。]

    a. **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名を入力します (例: **B.Simon**)。

    b。 [ **電子メール** ] テキスト ボックスに、ユーザーの電子メール ( `B.Simon\@contoso.com`など) を入力します。

    c. **[OK] を選択**.

注

Workteam では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workteam-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Workteam のサインオン URL にリダイレクトされます。
- Workteam のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Workteam に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Workteam] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Workteam に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/workware-tutorial"} -->
## Microsoft Entra ID で Workware for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workware-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Workware の間にシングル サインオンを構成する方法について説明します。

この記事では、Workware と Microsoft Entra ID を統合する方法について説明します。 Workware と Microsoft Entra ID を統合すると、次のことができます。

- Workware にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Workware に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Workware でのシングル サインオン (SSO) が有効になったサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Workware では、**IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Workware の追加

Microsoft Entra ID への Workware の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Workware を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Workware**」と入力します。
4. 結果のパネルから **Workware** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Workware 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Workware との Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Workware の関連ユーザーとの間にリンク関係を確立する必要があります。

Workware との Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Workware の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Workware テスト ユーザーの作成** - B.Simonに対応するWorkwareのユーザーを作成し、そのユーザーをMicrosoft Entraのユーザー表現にリンクさせるため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Workware**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `<WORKWARE_URL>/WW/AuthServices`

    b。 **[応答 URL]** テキスト ボックスに、`<WORKWARE_URL>/WW/AuthServices/Acs` のパターンを使用して値を入力します。

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Workware クライアント サポート チーム](mailto:support@activeops.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Workware のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Workware SSO の構成

Workware で SSO 機能を使用するには、次の設定を完了する必要があります。

##### Workware システム管理者の SSO アクセス許可を有効にする

- Workware システム管理者が SSO 認証を設定できるようにするには、SSO 認証のアクセス許可 (**[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) &gt; [System Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム設定) の [System configuration permissions](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム構成のアクセス許可) カテゴリ &gt; [Permissions to Role](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ロールへのアクセス許可)** 画面) を Workware システム管理者に対して有効にする必要があります。

    [Image: [SSO Authentication](SSO 認証) のアクセス許可]

##### Workware で SSO 認証を設定する

1. **[システム設定]** ページに移動し、[**SSO 認証**] を選択します。
2. [ **SSO 認証** ] セクションで、[ **SSO 認証の追加** ] ボタンを選択し、次の手順を実行します。

    [Image: [SSO Authentication](SSO 認証)]

    1. **[External Identity Provider](外部 ID プロバイダー)** で、IDP の名前を指定します。
    2. **[Authentication Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の種類)** として **[SAML2.0]** を選択します。
    3. **[Identity Provider SignIn URL] (ID プロバイダーのサインイン URL)** ボックスに、先ほどコピーした**ログイン URL** の値を入力します。
    4. **[Identity Provider Issuer URL] (ID プロバイダーの発行者 URL)** テキストボックスに、先ほどコピーした **Microsoft Entra 識別子**の値を入力します。
    5. **[Identity Provider Logout URL] (ID プロバイダーのログアウト URL)** テキストボックスに、先ほどコピーした**ログイン URL** の値を入力します。
    6. **[有効化]** を選択します。
    7. ダウンロードした**証明書**を **[ID プロバイダー証明書]** にアップロードします。
    8. **保存** を選択します。

#### Workware テスト ユーザーの作成

1. Workware の Web サイトに管理者としてサインインします。
2. **[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) &gt; [Create / View](作成 / 表示) &gt; [User Accounts](ユーザー アカウント) &gt; [Add New](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新規追加)** の順に選択します。
3. 次のページで、以下の手順を実行します。

    [Image: testuser]

    ある。 **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** フィールドに有効な名前を入力します。

    b。 **[Authentication Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証の種類)** として **SSO** を選択します。

    c. 必須フィールドを入力し、[ **保存]** を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Workware に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Workware] タイルを選択すると、SSO を設定した Workware に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/worthix-app-tutorial"} -->
## Microsoft Entra ID で Worthix App for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/worthix-app-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Worthix App の間にシングル サインオンを構成する方法について説明します。

この記事では、Worthix App と Microsoft Entra ID を統合する方法について説明します。 Worthix App は、I.A を使用して会社の顧客と対話し、会社の価値に対する認識を収集する、カスタマー バリュー アラインメント プラットフォームです。 Worthix App を Microsoft Entra ID と統合すると、以下のことができます。

- Worthix App にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Worthix App に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Worthix App 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Worthix App では、**IDP** Initiated シングル サインオンと **Just In Time** ユーザー プロビジョニングがサポートされます。

### [前提条件]

Microsoft Entra ID を Worthix App と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Worthix App でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Worthix App アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Worthix App を追加する

Microsoft Entra アプリケーション ギャラリーから Worthix App を追加して、Worthix App とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. \*\* **Entra ID**&gt;**Enterprise アプリケーション**&gt;**Worthix App**&gt;**シングルサインオン**の画面に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`urn:auth0:production-worthix:<Company_Name>Saml` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://production-worthix.us.auth0.com/login/callback?connection=<Company_Name>Saml`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値については、[Worthix App のサポート チーム](mailto:support@worthix.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Worthix App のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Worthix App の SSO の構成

**Worthix App** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーションの構成からコピーした適切な URL を [Worthix App サポート チーム](mailto:support@worthix.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Worthix App のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Worthix App に作成します。 Worthix App では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Worthix App にユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Worthix アプリに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Worthix App] タイルを選択すると、SSO を設定した Worthix アプリに自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wrike-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Wrike を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wrike-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-05
- Summary: ユーザー アカウントを Wrike に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Wrike や Microsoft Entra ID での手順を示し、Microsoft Entra ID を構成して、Wrike にユーザーまたはグループを自動的にプロビジョニングおよびプロビジョニング解除することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用したサービスとしてのソフトウェア (SaaS) アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除に関するページを参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Wrike テナント](https://www.wrike.com/price/)
- 管理者アクセス許可がある Wrike のユーザー アカウント

### 手順 1: Wrike にユーザーを割り当てる

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID内のアプリケーションに割り当てられたユーザーまたはグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Wrike にアクセスする必要Microsoft Entra IDユーザーまたはグループを決定します。 次に、「エンタープライズ アプリにユーザーまたはグループを割り当てる」の手順に従って、これらの [ユーザーまたはグループを Wrike に割り当てます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。

#### ユーザーを Wrike に割り当てる際の重要なヒント

- Wrike に 1 人のMicrosoft Entra ユーザーを割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後で追加のユーザーやグループを割り当てることができます。
- Wrike にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログ ボックスで選択する必要があります。 既定のアクセス ロールのユーザーは、プロビジョニングから除外されます。

### 手順 2: プロビジョニング用に Wrike を設定する

Microsoft Entra IDを使用して自動ユーザー プロビジョニング用に Wrike を構成する前に、Wrike で System for Cross-domain Identity Management (SCIM) プロビジョニングを有効にする必要があります。

1. [Wrike 管理コンソール](https://www.Wrike.com/login/)にサインインします。 テナント ID に移動します。 **[アプリおよび統合]** を選択します。

    [Image: アプリと統合のスクリーンショット。]
2. **Microsoft Entra ID**に移動して選択します。
3. [SCIM] を選択します。 **[ベース URL]** をコピーします。

    [Image: ベース URL のスクリーンショット。]
4. **API**&gt;**Azure SCIM** を選択します。

    [Image: Azure SCIMのスクリーンショット]
5. ポップアップが表示されます。 先ほどアカウントを作成するために作成したものと同じパスワードを入力します。

    [Image: Wrike Create トークンのスクリーンショット。]
6. **Secret Token** をコピーし、Microsoft Entra IDに貼り付けます。 **[保存]** を選択し、Wrike でのプロビジョニングの設定を完了します。

    [Image: 永続的なアクセス トークンのスクリーンショット。]

### 手順 3: ギャラリーから Wrike を追加する

Microsoft Entra IDを使用して自動ユーザー プロビジョニング用に Wrike を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Wrike を追加します。

Microsoft Entra アプリケーション ギャラリーから Wrike を追加するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。Wrike\*\* の結果パネルで **Wrike** を選択し、**Add** を選択してアプリケーションを追加します。

    [Image: 結果一覧の Wrike のスクリーンショット。]

### 手順 4: Wrike への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDでのユーザーまたはグループの割り当てに基づいて Wrike でユーザーまたはグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Wrike で SAML ベースのシングル サインオンを有効にするには、 [Wrike のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wrike-tutorial)に関する記事の手順に従います。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Wrike の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Wrike** に移動します。

    [Image: アプリケーションの一覧の [Wrike] リンクのスクリーンショット。]
3. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
4. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
5. [管理者資格情報] セクションで、先ほど取得した **ベース URL** と **永続的アクセス トークン** の値 **をそれぞれテナント URL** と **シークレット トークン**に入力します。 **Test Connection** を選択して、Microsoft Entra IDが Wrike に接続できることを確認します。 接続できない場合は、使用中の Wrike アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: テナント URL + トークンのスクリーンショット。]
6. [ **作成]** を選択して構成を作成します。
7. [**概要**] ページで **[プロパティ**] を選択します。
8. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
9. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
10. Microsoft Entra IDから Wrike に同期されるユーザー属性を、**Attribute Mappings** セクションで確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Wrike のユーザー アカウントとの照合に使用されます。 すべての変更をコミットするには、 **[保存]** を選択します。

    [Image: Wrike ユーザー属性のスクリーンショット。]
11. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
12. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
13. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wrike-tutorial"} -->
## Microsoft Entra ID で Wrike for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wrike-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wrike 間にシングル サインオンを構成する方法について説明します。

この記事では、Wrike と Microsoft Entra ID を統合する方法について説明します。 Wrike を Microsoft Entra ID と統合すると、次のことができます。

- Wrike にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Wrike に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Wrike でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Wrike では、 **SP** Initiated SSO と **IDP** Initiated SSO がサポートされます。
- Wrike では、 [**自動** ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wrike-provisioning-tutorial) がサポートされます (推奨)。
- Wrike では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Wrike の追加

Microsoft Entra ID への Wrike の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Wrike を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Wrike**」と入力します。
4. 結果パネルから **Wrike** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Wrike 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Wrike に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Wrike の関連ユーザーとの間にリンク関係を確立する必要があります。

Wrike に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Wrike SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Wrike テストユーザーの作成** - Wrike で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Wrike**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、アプリが既に Azure に事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.wrike.com/login/`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Wrike のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Wrike SSO の構成

**Wrike** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Wrike サポート チーム](mailto:support@team.wrike.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Wrike のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Wrike に作成します。 Wrike では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Wrike に存在しない場合は、Wrike にアクセスしようとしたときに新しいユーザーが作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Wrike サポート チーム](mailto:support@team.wrike.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Wrike のサインオン URL にリダイレクトされます。
- Wrike のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Wrike に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Wrike] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Wrike に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/wuru-app-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Wúru App を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/wuru-app-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Wúru App 間のシングル サインオンを構成する方法について説明します。

この記事では、Wúru App と Microsoft Entra ID を統合する方法について説明します。 Wúru App を Microsoft Entra ID と統合すると、次のことが可能になります。

- Wúru App にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントを使用して Wúru App に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Wúru App でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Wúru App では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Wúru App の追加

Microsoft Entra ID への Wúru App の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Wúru App を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Wúru App**」と入力します。
4. 結果のパネルから **[Wúru App]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Wúru App に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Wúru App に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Wúru App の関連ユーザー間にリンク関係を確立する必要があります。

Wúru App に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Wúru App SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Wuru App のテストユーザーを作成** - Wúru App で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra にあるユーザーの表現とリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Wúru App**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.wuru.site/api/auth/azure`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `urn:amazon:cognito:sp:us-east-2_142Y3PTBg`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Wúru App のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Wúru App の SSO の構成

**Wúru App** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Wúru App サポート チーム](mailto:contacto@wuru.site)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Wúru App のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Wúru App に作成します。 [Wúru App サポート チーム](mailto:contacto@wuru.site)と連携して、Wúru App プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Wúru アプリのサインオン URL にリダイレクトされます。
- Wúru App のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Wúru App] タイルを選択すると、このオプションは Wúru App のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/x-point-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に X-point Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/x-point-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と X-point Cloud の間にシングル サインオンを構成する方法について説明します。

この記事では、X-point Cloud と Microsoft Entra ID を統合する方法について説明します。 X-point Cloud を Microsoft Entra ID と統合すると、次のことができます。

- X-point Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って X-point Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- X-point Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- X-point Cloud では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの X-point Cloud の追加

Microsoft Entra ID への X-point Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に X-point Cloud を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「X-point Cloud**」と入力します。
4. 結果パネルから **[X-point Cloud** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### X-point Cloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、X-point Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと X-point Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を X-point Cloud と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **X-point Cloud の SSO を構成**する - アプリケーション側でシングル サインオン設定を構成します。
    1. **X-point Cloud のテストユーザーを作成し、Microsoft Entra の B.Simon にリンクさせる** - これにより、X-point Cloud で B.Simon に対応するユーザーを持つことができます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**X-point Cloud**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.atledcloud.jp`

    b。 **[応答 URL (Assertion Consumer Service URL)]** テキスト ボックスに、次のパターンを使用して URL を入力します。`https://<SUBDOMAIN>.atledcloud.jp/xpoint/saml/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.atledcloud.jp/xpoint`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 使用している X ポイントの URL に`<SUBDOMAIN>`の`https://<SUBDOMAIN>.atledcloud.jp`部分を一致させてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **X-point Cloud のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### X-point Cloud SSO の構成

X ポイント クラウド側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、X ポイント クラウド ドメイン管理メニューの **SAML サービス設定**にコピーされた**ログイン URL を**使用できます。 IdP が署名するために使用する公開キーの証明書と IdP の SSO エンドポイント URL に設定します。

#### X-point Cloud のテスト ユーザーの作成

このセクションでは、X-point Cloud で Microsoft Entra ID に登録されているユーザーの **メール アドレス** を使用できます。 @ 以降を削除したユーザーを作成します。 たとえば、username@companydomain.extension の場合、"username" を X-point Cloud に追加します。シングル サインオンを使用するには、ユーザーを作成して有効にする必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる X-point Cloud のサインオン URL にリダイレクトされます。
- X-point Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [X-point Cloud] タイルを選択すると、このオプションは X-point Cloud のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/xaitporter-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に XaitPorter を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xaitporter-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と XaitPorter の間にシングル サインオンを構成する方法について学習します。

この記事では、XaitPorter と Microsoft Entra ID を統合する方法について説明します。 XaitPorter を Microsoft Entra ID と統合すると、次のことができます:

- XaitPorter にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って XaitPorter に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- XaitPorter でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- XaitPorter では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの XaitPorter の追加

Microsoft Entra ID への XaitPorter の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに XaitPorter を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**XaitPorter**」と入力します。
4. 結果のパネルから **[XaitPorter]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### XaitPorter 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、XaitPorter で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと XaitPorter の関連ユーザーとの間にリンク関係を確立する必要があります。

XaitPorter で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **XaitPorter SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **XaitPorter のテストユーザーを作成し** - Microsoft Entra のユーザーである B.Simon にリンクする対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**XaitPorter**&gt;**シングル サインオン**をブラウズして移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.xaitporter.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.xaitporter.com/saml/login`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[XaitPorter クライアント サポート チーム](https://www.xait.com/support/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **IP アドレス**または**アプリのフェデレーション メタデータ URL** を [SmartRecruiters サポート チーム](https://www.smartrecruiters.com/about-us/contact-us/)に提供します。これにより、XaitPorter では、承認済みリストを構成して、XaitPorter インスタンスから IP アドレスに確実に到達できるようにすることができます。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### XaitPorter SSO の構成

1. 別の Web ブラウザー ウィンドウで、XaitPorter 企業サイトに管理者としてサインインします
2. [ **管理者] を選択します**。

    [Image: XaitPorter サイトで選択されている [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) を示すスクリーンショット。]
3. **[システム設定]** ドロップダウン リストから、 **[シングル サインオンの管理]** を選択します。

    [Image: [System Setup](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム設定) で選択されている [Manage Single Sign-On](シングル サインオンの管理) を示すスクリーンショット。]
4. **[シングル サインオンの管理]** セクションで、次の手順に従います。

    [Image: これらの手順を実行できる [MANAGE SINGLE SIGN-ON](シングル サインオンの管理) セクションを示すスクリーンショット。]

    a **[シングル サインオンを有効にする]** を選択します。

    b。 **[ID プロバイダーの設定]** ボックスに、コピーした**アプリのフェデレーション メタデータ URL を**貼り付け、[**フェッチ**] を選択します。

    c. **[Enable Autocreation of Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの自動作成を有効にする)** を選択します。

    d. **[OK] を選択**.

#### XaitPorter のテスト ユーザーの作成

このセクションでは、XaitPorter で Britta Simon というユーザーを作成します。 XaitPorter プラットフォームでユーザーを追加するには、[XaitPorter クライアント サポート チーム](https://www.xait.com/support/)に問い合わせてください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる XaitPorter のサインオン URL にリダイレクトされます。
- XaitPorter のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [XaitPorter] タイルを選択すると、このオプションは XaitPorter のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/xcarrier-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に xCarrier® を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xcarrier-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と xCarrier® 間のシングル サインオンを構成する方法について説明します。

この記事では、xCarrier® と Microsoft Entra ID を統合する方法について説明します。 xCarrier® を Microsoft Entra ID と統合すると、次のことが可能になります。

- xCarrier® にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで xCarrier® に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- xCarrier® でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- xCarrier® では、**SP** と **IDP** initiated SSO をサポートします。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから xCarrier® を追加する

Microsoft Entra ID への xCarrier® の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に xCarrier® を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**xCarrier®**」と入力します。
4. 結果パネルから **[xCarrier®]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### xCarrier® に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、xCarrier® に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと xCarrier® の関連ユーザー間にリンク関係を確立する必要があります。

xCarrier® に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **xCarrier® SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **xCarrier® テスト ユーザーの作成 - xCarrier®** で B.Simon に対応するユーザーを作成し、それを Microsoft Entra ユーザーである B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**xCarrier®**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://msdev.myxcarrier.com/Home/Index`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[xCarrier® の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### xCarrier® SSO の構成

**xCarrier®** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [xCarrier® サポート チーム](mailto:pw_support@elemica.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### xCarrier® テスト ユーザーの作成

このセクションでは、B. Simon というユーザーを xCarrier® に作成します。 xCarrier® では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 xCarrier® にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる xCarrier® サインオン URL にリダイレクトされます。
- xCarrier® のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した xCarrier® に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [xCarrier®] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した xCarrier® に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/xledger-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Xledger を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/xledger-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-05
- Summary: ユーザー アカウントをMicrosoft Entra IDから Xledger に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Xledger と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成済みのMicrosoft Entra IDは、Microsoft Entraプロビジョニングサービスを使用して、ユーザーとグループを[Xledger](https://www.xledger.com)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Xledger でユーザーを作成します。
- アクセスが不要になったら、Xledger のユーザーを削除します。
- Microsoft Entra IDと Xledger の間でユーザー属性の同期を維持します。
- Xledger にグループとグループ メンバーシップをプロビジョニングします。
- Xledger に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)する (推奨)

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Xledger のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングの範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとXledgerの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Xledger を構成する

1. ドメイン管理者 (または同様) のロールを使用して **Xledger** にサインインし、**[管理] &gt; [システム アクセス] &gt; [API アクセス トークン]** に移動します。
2. シークレット トークンを生成してそれを書き留めておく

    [Image: API アクセス トークン (新しいトークン) のスクリーンショット。]
3. テナント URL を書き留めておきます。

    [Image: API アクセス トークン (API URL) のスクリーンショット。]

これらの値は、Xledger アプリケーションの [プロビジョニング] タブで使用されます。 (ステップ 5)

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Xledger を追加する

Microsoft Entra アプリケーション ギャラリーから Xledger を追加して、Xledger へのプロビジョニングの管理を開始します。 SSO のために Xledger を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Xledger への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Xledger の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Xledger]** を選択します。

    [Image: アプリケーション リストの Xledger リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しいコンフィレーションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Xledger テナント URL とシークレット トークンを入力します。 **Test Connection** を選択してMicrosoft Entra IDが Xledger に接続できることを確認します。 接続に失敗した場合は、Xledger アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Xledger に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Xledger のユーザー アカウントとの照合に使用されます。 [照合対象の属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合、その属性に基づいたユーザーのフィルター処理を Xledger API がサポートしているか確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Xledger で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | emails[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:xledger:2.0:User:accessFromDate | 日付と時間 |  |  |
    | urn:ietf:params:scim:schemas:extension:xledger:2.0:User:accessToDate | 日付と時間 |  |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Xledger に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Xledger のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Xledger で必須 |
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
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->
