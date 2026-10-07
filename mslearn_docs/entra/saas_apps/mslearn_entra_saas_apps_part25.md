# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 25)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 79

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trunarrative-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TruNarrative を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trunarrative-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TruNarrative の間でシングル サインオンを構成する方法について説明します。

この記事では、TruNarrative と Microsoft Entra ID を統合する方法について説明します。 TruNarrative と Microsoft Entra ID を統合すると、次のことができます:

- TruNarrative にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って TruNarrative に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TruNarrative でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TruNarrative では、**SP** Initiated SSO がサポートされます。

### ギャラリーから TruNarrative を追加する

Microsoft Entra ID への TruNarrative の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TruNarrative を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**TruNarrative**」と入力します。
4. 結果のパネルで **[TruNarrative]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TruNarrative 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、TruNarrative に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと TruNarrative の関連ユーザーとの間にリンク関係を確立する必要があります。

TruNarrative に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TruNarrative の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TruNarrative テストユーザーを作成 - B.Simon を TruNarrative で再現するユーザーを生成し、ユーザーの Microsoft Entra 表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**TruNarrative**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://<SUBDOMAIN>.trunarrative.cloud` という形式で URL を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.trunarrative.cloud/IdP/sso.aspx`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.trunarrative.cloud`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[TruNarrative クライアント サポート チーム](mailto:helpdesk@trunarrative.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[TruNarrative のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TruNarrative の SSO の構成

**TruNarrative** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーションの構成からコピーした適切な URL を [TruNarrative サポート チーム](mailto:helpdesk@trunarrative.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TruNarrative のテスト ユーザーの作成

このセクションでは、TruNarrative で B.Simon というユーザーを作成します。 [TruNarrative サポート チーム](mailto:helpdesk@trunarrative.com)と連携して、TruNarrative プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる TruNarrative のサインオン URL にリダイレクトされます。
- TruNarrative のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで TruNarrative タイルを選択すると、このオプションは TruNarrative のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/trustworks-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に TrustWorks を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/trustworks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TrustWorks の間でシングル サインオンを構成する方法について説明します。

この記事では、TrustWorks と Microsoft Entra ID を統合する方法について説明します。 TrustWorks と Microsoft Entra ID を統合すると、次のことができます。

- TrustWorks にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して TrustWorks に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TrustWorks でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TrustWorks では、SP開始のSSOとIDP開始のSSOの両方がサポートされます。
- TrustWorks では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから TrustWorks を追加する

Microsoft Entra ID への TrustWorks の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TrustWorks を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「TrustWorks**」と入力します。
4. 結果パネルから **TrustWorks** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TrustWorks の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、TrustWorks に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TrustWorks の関連ユーザーとの間にリンク関係を確立する必要があります。

TrustWorks に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TrustWorks SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TrustWorks のテスト ユーザーの作成** - TrustWorks で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TrustWorks**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Microsoft Entra に事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://app.trustworks.io` |
    | `https://dev-app.trustworks.io` |
    | `https://qa-app.trustworks.io` |
7. TrustWorks アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記に加えて、TrustWorks アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
    | 従業員ID | ユーザー.社員ID |
    | 役割 | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. [ **TrustWorks のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TrustWorks SSO の構成

**TrustWorks** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [TrustWorks サポート チーム](mailto:contact@trustworks.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TrustWorks テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを TrustWorks に作成します。 TrustWorks では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 TrustWorks にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる TrustWorks のサインオン URL にリダイレクトします。
- TrustWorks のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した TrustWorks に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TrustWorks] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TrustWorks に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tulip-tutorial"} -->
## Microsoft Entra ID で Tulip for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tulip-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Tulip 間のシングル サインオンを構成する方法について説明します。

この記事では、Tulip と Microsoft Entra ID を統合する方法について説明します。 Tulip を Microsoft Entra ID と統合すると、次のことが可能になります。

- Tulip にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Tulip に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

Tulip は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Tulip でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Tulip では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Tulip の追加

Microsoft Entra ID への Tulip の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Tulip を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Tulip**」と入力します。
4. 結果パネルから **Tulip** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Tulip に対する Microsoft Entra SSO を構成・テストする

Tulip に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成** する - ユーザーがこの機能を使用できるようにします。
2. **Tulip SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。

    1. 既存ユーザーがいる Tulip インスタンスで SSO を構成する場合は、support@tulip.co までご連絡ください。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、以下の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Tulip**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    a. Tulip のメタデータ ファイルをダウンロードします。このファイルは Tulip インスタンスの設定ページからアクセスできます。

    b。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: image1]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: image2]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションで **識別子** と **応答 URL** の値が自動的に設定されます。

    [Image: image3]

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. Tulip アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。 `nameID` をメール アドレスにする必要がある場合は、形式を `Persistent` に変更します。

    [Image: 画像]
7. その他に、Tulip アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | displayName | user.displayname |
    | メールアドレス | User.mail |
    | badgeID | user.employeeid |
    | groups | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

### Tulip SSO を構成する

1. Tulip インスタンスにアカウント所有者としてサインインします。
2. **設定**&gt;**SAML** に移動し、次のページで次の手順を実行します。

    [Image: チューリップ構成のスクリーンショット。]

    a. **SAML ログインを有効にします**。

    b。 **メタデータ xml ファイル**を選択して**サービス プロバイダー メタデータ ファイル**をダウンロードし、このファイルを使用して Azure portal の **[基本的な SAML 構成]** セクションにアップロードします。

    c. Azure からフェデレーション メタデータ XML ファイルを Tulip にアップロードします。 これにより、SSO ログイン URL、SSO ログアウト URL、証明書が自動的に入力されます。

    d. Name、Email、Badge の属性が null でないことを確認します。つまり、3 つの入力すべてに一意の文字列を入力し、右側の [ `Authenticate` ] ボタンを使用してテスト認証を行います。

    e. 認証に成功したら、クレーム URL 全体をコピーし、名前、メール、バッジ ID の各属性に適切に貼り付けてマップします。

    - **Name 属性値**を`http://schemas.microsoft.com/identity/claims/displayname`または適切な要求 URL として貼り付けます。
    - **電子メール属性値**を`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`または適切な要求 URL として貼り付けます。
    - **バッジ属性値**を`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/badgeID`または適切な要求 URL として貼り付けます。
    - **ロール属性値**を`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/groups`または適切な要求 URL として貼り付けます。

    f. [ **SAML 構成の保存] を選択します**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/turborater-tutorial"} -->
## Microsoft Entra ID で TurboRater for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/turborater-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TurboRater の間でシングル サインオンを構成する方法について説明します。

この記事では、TurboRater と Microsoft Entra ID を統合する方法について説明します。

TurboRater と Microsoft Entra ID の統合には、次の利点があります。

- TurboRater にアクセスする Microsoft Entra ID を制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して TurboRater に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央サイト (Azure ポータル) でアカウントを管理できます。

サービスとしてのソフトウェア (SaaS) アプリと Microsoft Entra ID の統合の詳細については、[Microsoft Entra ID を使ったアプリケーション アクセスとシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)に関する記事を参照してください。

### [前提条件]

TurboRater と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- シングル サインオンが有効な TurboRater サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

TurboRater では、IDP によって開始されるシングル サインオン (SSO) がサポートされます。

### Azure Marketplace から TurboRater を追加する

Microsoft Entra ID への TurboRater の統合を構成するには、Azure Marketplace から管理対象 SaaS アプリの一覧に TurboRater を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「TurboRater**」と入力します。
4. 結果パネルから **TurboRater** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **B Simon** というテスト ユーザーに基づいて、TurboRater で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと TurboRater の関連ユーザーとの間にリンクを確立する必要があります。

TurboRater で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. **Microsoft Entra シングル サインオンを構成**して、ユーザーがこの機能を使用できるようにします。
2. **TurboRater シングル サインオンを構成** して、アプリケーション側でシングル サインオン設定を構成します。
3. B. Simon で Microsoft Entra のシングル サインオンをテストする Microsoft **Entra テスト ユーザーを作成**します。
4. **Microsoft Entra テスト ユーザーを割り当てて** 、B. Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **TurboRater で** B. Simon という名前のユーザーが、B. Simon という Microsoft Entra ユーザーにリンクされるように、TurboRater テスト ユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

TurboRater で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TurboRater** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン オプションを構成する]
3. [ **シングル サインオン方法の選択** ] ウィンドウで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** (鉛筆アイコン) を選択して [ **基本的な SAML 構成]** ウィンドウを開きます。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** ウィンドウで、次の手順を実行します。

    [Image: TurboRater のドメインと URL のシングル サインオン情報]

    1. [ **識別子 (エンティティ ID)]** ボックスに、URL を入力します。

        `https://www.itcdataservices.com`
    2. [ **応答 URL (Assertion Consumer Service URL)]** ボックスに、次のパターンを使用して URL を入力します。

        | 環境 | URL |
        | --- | --- |
        | テスト | `https://ratingqa.itcdataservices.com/webservices/imp/saml/login` |
        | ライブ | `https://www.itcratingservices.com/webservices/imp/saml/login` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [TurboRater サポート チーム](https://www.getitc.com/support)にお問い合わせください。 **[Basic SAML Configuration] (基本的な SAML 構成)** ペインに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ウィンドウの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択し、特定のオプションの**フェデレーション メタデータ XML** をダウンロードします。

    [Image: フェデレーション メタデータ XML のダウンロード オプション]
7. [ **TurboRater のセットアップ** ] セクションで、必要な URL をコピーします。

    - **ログイン URL**
    - **Microsoft Entra 識別子**
    - **ログアウト URL**

    [Image: 構成 URL をコピーする]

#### TurboRater シングル サインオンの構成

TurboRater 側でシングル サインオンを構成するには、ダウンロードしたフェデレーション メタデータ XML とコピーした適切な URL を [TurboRater サポート チーム](https://www.getitc.com/support)に送信する必要があります。 TurboRater チームは、SAML SSO 接続が両方の側で正しく設定されていることを確認します。

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、Britta Simon という名前のテスト ユーザーを作成します。

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

このセクションでは、B. Simon に TurboRater へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. **Entra ID**&gt;**Enterprise アプリケーション**&gt;の**TurboRater**に移動します。

    [Image: [エンタープライズ アプリケーション] ウィンドウ]
2. アプリケーションの一覧で **TurboRater** を選択します。

    [Image: TurboRaterがアプリケーション一覧にあります]
3. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。

    [Image: [ユーザーとグループ] オプション]
4. **[+ ユーザーの追加]** を選択し、 **[割り当ての追加]** ウィンドウで **[ユーザーとグループ]** を選択します。

    [Image: [割り当ての追加] ウィンドウ]
5. [**ユーザーとグループ**] ウィンドウで、[**ユーザー**] の一覧で **[B. Simon**] を選択し、ウィンドウの下部にある **[選択**] を選択します。
6. SAML アサーションでロール値が必要な場合は、 **[ロールの選択]** ウィンドウで、一覧からユーザーに適したロールを選択します。 ウィンドウの下部で、 **[選択]** を選択します。
7. **[割り当ての追加]** ウィンドウで **[割り当て]** を選択します。

#### TurboRater テスト ユーザーの作成

このセクションでは、TurboRater で B. Simon というユーザーを作成します。 [TurboRater サポート チーム](https://www.getitc.com/support)と協力して、B. Simon を TurboRater のユーザーとして追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、マイ アプリ ポータルを使って Microsoft Entra のシングル サインオン構成をテストします。

マイ アプリ ポータルで **TurboRater** を選択すると、シングル サインオンを設定した TurboRater サブスクリプションに自動的にサインインします。 マイアプリ ポータルの詳細については、「[マイ アプリ ポータルでアプリにアクセスして使用する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tutorial-list"} -->
## Microsoft Entra ID の SaaS アプリ構成ガイド - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: さまざまなサードパーティのサービスとしてのソフトウェア アプリケーションとの Microsoft Entra シングル サインオン統合を構成します。

クラウド対応 [サービスとしてのソフトウェア (SaaS)](https://azure.microsoft.com/overview/what-is-saas/) とオンプレミス アプリケーションを Microsoft Entra ID と統合するために、構成について説明する記事のコレクションを開発しました。

Microsoft Entra ID に事前に統合されているすべての SaaS アプリの一覧については、[Microsoft Entra Marketplace](https://marketplace.microsoft.com/marketplace/apps?product=entra-id-apps) を参照してください。 Microsoft Entra ID Governance と統合できるアプリケーションの一覧については、 [Microsoft Entra ID ガバナンスの統合](https://learn.microsoft.com/ja-jp/entra/id-governance/apps)に関するトピックを参照してください。

Microsoft Entra は、OpenID Connect、SAML、SCIM、SQL、LDAP などの標準を使用して、他の多くのアプリケーションと統合できます。 一覧にないアプリケーションを使用していて、SaaS である場合は、 [アプリケーション ネットワーク ポータル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing) を使用して、自動プロビジョニング用に [SCIM](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups) 対応アプリケーションをギャラリーに追加するか、SAML/OIDC 対応アプリケーションを SSO 用にギャラリーに追加するように要求します。 他のアプリケーションとの統合については、[アプリケーションと Microsoft Entra ID の統合](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-applications-integrate)をご覧ください。

### クイック リンク:

一般的な統合の一部には、次の表にアプリケーションが含まれています。

| ロゴ | シングル サインオンのアプリケーションに関する記事 | ユーザー プロビジョニングのアプリケーションに関する記事 |
| --- | --- | --- |
| [Image: ロゴ - Atlassian Cloud] | [Atlassian Cloud](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atlassian-cloud-tutorial) | [Atlassian Cloud - ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/atlassian-cloud-provisioning-tutorial) |
| [Image: ロゴ - ServiceNow] | [サービスナウ](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-tutorial) | [ServiceNow - ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicenow-provisioning-tutorial) |
| [Image: ロゴ - Slack] | [スラック](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/slack-tutorial) | [Slack - ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/slack-provisioning-tutorial) |
| [Image: ロゴ - SuccessFactors] | [SuccessFactors](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/successfactors-tutorial) | [SuccessFactors - ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-successfactors-inbound-provisioning-tutorial) |
| [Image: ロゴ - Workday] | [WorkDay](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-tutorial) | [Workday - インバウンドプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/workday-inbound-tutorial) |

SaaS アプリのその他の記事を見つけるには、左側の目次を使用します。 この一覧は、シングル サインオンとプロビジョニングに関する記事に分かれています。

### クラウド プロバイダーの統合

インフラストラクチャ プロバイダーとの一般的な統合の一部を次の表に示します。

| ロゴ | シングル サインオンのアプリケーションに関する記事 | ユーザー プロビジョニングのアプリケーションに関する記事 |
| --- | --- | --- |
| [Image: ロゴ - アマゾン ウェブ サービス (AWS) コンソール] | [アマゾン ウェブ サービス (AWS) コンソール](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-web-service-tutorial) | [アマゾン ウェブ サービス (AWS) コンソール - ロール プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/amazon-web-service-tutorial#configure-azure-ad-sso) |
| [Image: ロゴ - Alibaba Cloud Service (ロールベースの SSO)] | [Alibaba Cloud Service (ロールベースの SSO)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/alibaba-cloud-service-role-based-sso-tutorial) |  |
| [Image: ロゴ - Google Cloud Platform] | Google Cloud Platform | [Google Cloud Platform - ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/g-suite-provisioning-tutorial) |
| [Image: ロゴ - Salesforce] | [Salesforce](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-tutorial) | [Salesforce - ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/salesforce-provisioning-tutorial) |
| [Image: ロゴ - SAP Cloud Identity Services] | [SAP Cloud Identity Services](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-hana-cloud-platform-identity-authentication-tutorial) | [SAP Cloud Identity Services - プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sap-cloud-platform-identity-authentication-provisioning-tutorial) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tutorocean-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TutorOcean を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorocean-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TutorOcean の間でシングル サインオンを構成する方法について説明します。

この記事では、TutorOcean と Microsoft Entra ID を統合する方法について説明します。 TutorOcean と Microsoft Entra ID を統合すると、次のことができます。

- TutorOcean にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して TutorOcean に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- TutorOcean でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TutorOcean では、**SP および IDP によって開始される** の SSO をサポートします。

### ギャラリーから TutorOcean を追加する

Microsoft Entra ID への TutorOcean の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に TutorOcean を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 「ギャラリー **から追加**」のセクションで、検索ボックス **に「TutorOcean**」と入力します。
4. 結果のパネルから [**TutorOcean**] を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TutorOcean の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、TutorOcean に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TutorOcean の関連ユーザーとの間にリンク関係を確立する必要があります。

TutorOcean に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TutorOcean SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **TutorOcean のテストユーザーを作成する** - TutorOcean で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TutorOcean**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法の選択]** ページで、SAML **を**選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、**IDP** 開始モードでアプリケーションを設定する場合は、次の手順を実行します。

    ある。 **識別子** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<SUBDOMAIN>.tutorocean.com` |
    | `https://<SUBDOMAIN>.quadc.io` |

    b。 [**応答 URL** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<SUBDOMAIN>.tutorocean.com/_saml/validate` |
    | `https://<SUBDOMAIN>.quadc.io/_saml/validate` |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<SUBDOMAIN>.tutorocean.com` |
    | `https://<SUBDOMAIN>.quadc.io` |

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 [これらの値を取得するには、](mailto:support@tutorocean.com) TutorOcean クライアント サポート チームにお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **の [TutorOcean** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TutorOcean SSO の構成

TutorOcean **側** シングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [TutorOcean サポート チーム](mailto:support@tutorocean.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TutorOcean テスト ユーザーの作成

このセクションでは、TutorOcean で Britta Simon というユーザーを作成します。 TutorOcean サポート チーム  と連携して、TutorOcean プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる TutorOcean のサインオン URL にリダイレクトされます。
- TutorOcean のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP開始:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TutorOcean に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [TutorOcean] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した TutorOcean に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tvu-service-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に TVU サービスを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tvu-service-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と TVU Service の間のシングル サインオンを構成する方法について説明します。

この記事では、TVU サービスと Microsoft Entra ID を統合する方法について説明します。 TVU Service を Microsoft Entra ID と統合すると、次のことができます。

- TVU Service にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで TVU Service に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- TVU Service のシングル サインオン (SSO) 対応サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- TVU サービスでは、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーから TVU Service を追加する

Microsoft Entra ID への TVU Service の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に TVU Service を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に進む。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「TVU Service**」と入力します。
4. 結果パネルから **TVU サービス** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### TVU Service に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、TVU Service に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと TVU Service の関連ユーザーとの間にリンク関係を確立する必要があります。

TVU Service に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **TVU サービスの SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **TVU Service のテスト ユーザーの作成** - TVU Service で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**TVU Service**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. TVU Service アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、TVU サービスでは、これがユーザーのメール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: TVU サービス アプリケーションの画像を示すスクリーンショット。]
7. 上記に加えて、TVU Service アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを以下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名字 | ユーザーの名字 |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### TVU Service SSO の構成

**TVU サービス**側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[TVU サービス サポート チーム](mailto:support@tvunetworks.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### TVU Service のテスト ユーザーの作成

このセクションでは、TVU Service で Britta Simon というユーザーを作成します。 [TVU サービス サポート チーム](mailto:support@tvunetworks.com)と協力して、TVU サービス プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した TVU サービスに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [TVU サービス] タイルを選択すると、SSO を設定した TVU サービスに自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/twic-tutorial"} -->
## Microsoft Entra ID でシングル サインオンを設定するために Twic を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/twic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Twic 間にシングル サインオンを構成する方法について説明します。

この記事では、Twic と Microsoft Entra ID を統合する方法について説明します。 Twic と Microsoft Entra ID を統合すると、次のことができます。

- Twic にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Twic に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Twic でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Twic では、**SP と IDP** によって開始される SSO がサポートされます。

### ギャラリーからの Twic の追加

Microsoft Entra ID への Twic の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Twic を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Twic**」と入力します。
4. 結果のパネルから **[Twic]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Twic 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Twic で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Twic の関連ユーザーとの間にリンク関係を確立する必要があります。

Twic に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Twic の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Twic テスト ユーザーの作成** - Microsoft Entra の B.Simon にリンクさせるために、対応するユーザーを Twic で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Twic**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.staging.twic.ai/saml/<CustomerName>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.staging.twic.ai/saml/<CustomerName>/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://client.staging.twic.ai/login?type=sso`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Twic クライアント サポート チーム](mailto:support@twic.zendesk.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Twic の SSO の構成

**Twic** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Twic サポート チーム](mailto:support@twic.zendesk.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Twic のテスト ユーザーの作成

このセクションでは、Twic で Britta Simon というユーザーを作成します。 [Twic サポート チーム](mailto:support@twic.zendesk.com)と連携して、Twic プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- **このアプリケーションをテスト**を選択すると、TwicのサインオフURLにリダイレクトされ、そこでログインフローを開始できます。
- Twic のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Twic に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイアプリでTwicタイルを選択すると、SPモードで構成されている場合は、ログインフローを開始するためのアプリケーションサインオンページにリダイレクトされます。IDPモードで構成されている場合は、SSOを設定したTwicに自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/twilio-sendgrid-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Twilio Sendgrid を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/twilio-sendgrid-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Twilio Sendgrid 間のシングル サインオンを構成する方法について説明します。

この記事では、Twilio Sendgrid と Microsoft Entra ID を統合する方法について説明します。 Twilio Sendgrid を Microsoft Entra ID と統合すると、次のことが可能になります。

- Twilio Sendgrid にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Twilio Sendgrid に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Twilio Sendgrid でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Twilio Sendgrid では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Twilio Sendgrid では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Twilio Sendgrid を追加する

Microsoft Entra ID への Twilio Sendgrid の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Twilio Sendgrid を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Twilio Sendgrid**」と入力します。
4. 結果のパネルから **Twilio Sendgrid** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Twilio Sendgrid に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Twilio Sendgrid に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Twilio Sendgrid の関連ユーザー間にリンク関係を確立する必要があります。

Twilio Sendgrid に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Twilio Sendgrid SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Twilio Sendgrid のテストユーザーを作成** - Twilio Sendgrid で B.Simon に対応するユーザーを作成し、Microsoft Entra でのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Twilio Sendgrid**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.sendgrid.com/v3/public/sso/saml/response/id/<uuid>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://api.sendgrid.com/v3/public/sso/saml/response/id/<uuid>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.sendgrid.com/ssologin`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Twilio Sendgrid クライアント サポート チーム](mailto:help@sendgrid.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **保存** を選択します。
8. Twilio Sendgrid アプリケーションは特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. 前の手順に加えて、Twilio Sendgrid アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Twilio Sendgrid のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Twilio Sendgrid SSO を構成する

**Twilio Sendgrid** 側にシングル サインオンを構成するには、**証明書 (Base64)** を [Twilio Sendgrid サポート チーム](mailto:help@sendgrid.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Twilio Sendgrid テスト ユーザーを作成する

このセクションでは、Twilio Sendgrid に B.Simon というユーザーを作成します。 Twilio Sendgrid では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Twilio Sendgrid にユーザーがまだ存在していない場合は、認証後に新規作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、サインイン フローを開始できる Twilio Sendgrid のサインオン URL にリダイレクトされます。
- Twilio Sendgrid のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Twilio Sendgrid に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Twilio Sendgrid] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Twilio Sendgrid に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/twingate-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Twingate を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/twingate-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Twingate に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法についてご確認ください。

この記事では、自動ユーザー プロビジョニングを構成するために Twingate ID と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Twingate](https://www.twingate.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされる機能

- Twingate でユーザーを作成する
- アクセスが不要になった場合に Twingate のユーザーを削除する
- Microsoft Entra ID と Twingate の間でユーザー属性の同期を維持する
- Twingate でグループとグループ メンバーシップをプロビジョニングする
- Twingate へのシングル サインオン (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- ID プロバイダーの統合をサポートする製品レベルの Twingate テナント。 さまざまな製品レベルの詳細については、 [Twingate の価格](https://www.twingate.com/pricing/) に関するページを参照してください。
- 管理者アクセス許可がある Twingate のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントの計画を立てる

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Twingate の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Twingate を構成する

1. [Twingate 管理コンソール](https://auth.twingate.com/)にサインインします。
2. **ID プロバイダー&gt;設定**に移動します
3. [ `...` ] ボタンを選択して、アクション メニューを開きます。 **SCIM トークンを再生成**を選択します。 これにより、既存のトークンがあれば無効になる可能性があることに注意してください。

    [Image: Microsoft Entra アクション メニューのスクリーンショット。]
4. モーダルから **SCIM エンドポイント** と **SCIM トークン** をコピーします。 これらの値は、Twingate アプリケーションの [プロビジョニング] タブの **[テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドにそれぞれ入力されます。

    [Image: SCIM 情報モーダルのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Twingate を追加する

Microsoft Entra アプリケーション ギャラリーから Twingate を追加して、Twingate へのプロビジョニングの管理を開始します。 SSO のために Twingate を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Twingate への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Twingate でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Twingate の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise Apps**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Twingate** を選択します。

    [Image: アプリケーションの一覧の [Twingate] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Twingate テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Twingate に接続できることを確認します。 接続に失敗した場合は、Twingate アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Twingate に同期されるユーザー **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Twingate のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Twingate API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | エクスターナルID | 糸 | ✓ |
    | ユーザー名 | 糸 |  |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
12. **[グループ]** を選びます。
13. [属性マッピング] セクションで、Microsoft Entra ID から Twingate に同期されるグループ **属性** を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Twingate のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | エクスターナルID | 糸 | ✓ |
    | 表示名 | 糸 |  |
    | メンバー | リファレンス |  |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: Assignment required の設定変更

既定の設定は **[いいえ** ]で、ユーザーはエンタープライズ アプリケーションに割り当てることなく Twingate にログインできます。

1. **[プロパティ] を選択します**。

    [Image: [プロパティ] のスクリーンショット。]
2. **[割り当てが必要]** を **[はい**] に設定する

    [Image: プロパティの割り当てのスクリーンショット。]
3. **[保存] を選択する**

    [Image: 保存されたプロパティのスクリーンショット。]

### 手順 7: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、正常にプロビジョニングされたユーザーまたは失敗したユーザーを特定する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態になったと考えられる場合、アプリケーションは検疫されます。 検疫状態の詳細については、を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/tyeexpress-tutorial"} -->
## Microsoft Entra ID で T&E Express for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tyeexpress-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と T&E Express の間にシングル サインオンを構成する方法について説明します。

この記事では、T&E Express と Microsoft Entra ID を統合する方法について説明します。 T&E Express と Microsoft Entra ID の統合には、次の利点があります。

- T&E Express にアクセスする Microsoft Entra ID を制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して T&E Express (シングル Sign-On) に自動的にサインインできるようにすることができます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- T&E Express でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- T&E Express では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの T&E Express の追加

Microsoft Entra ID への T&E Express の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に T&E Express を追加する必要があります。

**ギャラリーから T&E Express を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **T&E Express」と**入力し、結果パネルで **T&E Express** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の T&E Express]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、T&E Express で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと T&E Express 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

T&E Express で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **T&E Express シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **T&E Express テストユーザーを作成** - T&E Express において、Microsoft Entra のユーザー表現にリンクされた Britta Simon の対応ユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

T&E Express で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**T&E Express** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: T&E Express ドメインと URL のシングルサインオンについての情報]

    ある。 **[識別子]** ボックスに、`https://<domain>.tyeexpress.com` の形式で URL として値を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domain>.tyeexpress.com/authorize/samlConsume.aspx`
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **T&E Express のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### T&E Express Single Sign-On の構成

1. **T&E Express** 側でシングル サインオンを構成するには、管理者資格情報を使用して SAML シングル サインオンなしで T&E Express アプリケーションにログインします。
2. [ **管理** ] タブで、[ **SAML ドメイン** ] を選択して [SAML 設定] ページを開きます。

    [Image: [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) メニューから [SAML Domain](SAML ドメイン) が選択されていることを示すスクリーンショット。]
3. **[Activar (有効化)]** オプションを **[No]** から **[SI (はい)]** にして選択します。 **[ID プロバイダーのメタデータ]** テキストボックスに、ダウンロードしたメタデータ XML を貼り付けます。

    [Image: [Dominio SAML] ページを示すスクリーンショット。ここでメタデータを入力できます。]
4. **Guardar(保存)** ボタンを選択して設定を保存します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### T&E Express テスト ユーザーの作成

Microsoft Entra ユーザーが T&E Express にログインできるようにするには、ユーザーを T&E Express にプロビジョニングする必要があります。 T&E Express の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. T&E Express 企業サイトに管理者としてログインします。
2. [管理者タグ] で [ユーザー] を選択し、[ユーザー] マスター ページを開きます。

    [Image: [Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) メニューから [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) が選択されていることを示すスクリーンショット。]
3. ホーム ページで、 **+** を選択してユーザーを追加します。

    [Image: ユーザーを追加するプラス記号アイコンを示すスクリーンショット。]
4. フォームで求められたすべての必須の詳細を入力し、[保存] ボタンを選択して詳細を保存します。

    [Image: [User Information](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー情報) セクションを示すスクリーンショット。ここで適切な値を入力できます。]

    [Image: [Approvers](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/承認者) と [Assistant](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アシスタント) のセクションを示すスクリーンショット。ここで適切な値を入力できます。]

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [T&E Express] タイルを選択すると、SSO を設定した T&E Express に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/uber-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Uber を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uber-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Uber に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために Uber と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Uber](https://www.uber.com/) に対するユーザーのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Uber でユーザーを作成します。
- アクセスが不要になったら、Uber のユーザーを削除します。
- Microsoft Entra ID と Uber の間でユーザー属性の同期を維持する。
- コード認証許可フロー認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Uber for Business](https://business.uber.com/) 組織にオンボードされ、それに対して管理者としてアクセスできる必要があります。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Uber の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Uber を構成する

セットアップを開始する前に、SCIM プロビジョニングをエンド ツー エンドで有効にする要件を次に示します

- [Uber for Business](https://business.uber.com/) 組織にオンボードされ、それに対して管理者としてアクセスできる必要があります。
- ID プロバイダー経由での同期を許可する必要があります。右上隅にあるプロファイル写真の上にマウスを置き、**[設定] &gt; [統合セクション] &gt; [許可の切り替え]** に移動して、これを確認できます
- `organization-id` を取得して `https://api.uber.com/v1/scim/organizations/{organization-id}/v2` でそれを置き換えて、**テナント URL** を作成します。このテナント URL は、Uber アプリケーションの [プロビジョニング] タブに入力するものです。

    [Image: 組織 ID の取得のスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Uber を追加する

Microsoft Entra アプリケーション ギャラリーから Uber を追加して、Uber へのプロビジョニングの管理を開始します。 SSO 用の Uber を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Uber への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーの割り当てに基づいて、Uber でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Uber の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Uber]** を選択します。

    [Image: アプリケーション リストの Uber リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Uber テナントの URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Uber に接続できることを確認します。 接続に失敗した場合は、Uber アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Uber に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Uber のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Uber API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Uber で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | 名前.名 | 糸 |  | ✓ |
    | 名前.姓 | 糸 |  | ✓ |
    | エクスターナルID | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/uber-tutorial"} -->
## Microsoft Entra ID で Uber for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uber-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Uber 間にシングル サインオンを構成する方法について学習します。

この記事では、Uber と Microsoft Entra ID を統合する方法について説明します。 このアプリは、Microsoft Entra プロビジョニング サービスを使用して、Uber for Business に対してユーザーを自動的にプロビジョニングおよびプロビジョニング解除するのに役立ちます。 Uber を Microsoft Entra ID と統合すると、次のことができます。

- Uber にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Uber に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Uber 用の Microsoft Entra のシングル サインオンを構成してテストします。 Uber では、**IDP** によって開始されるシングル サインオンと [自動ユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uber-provisioning-tutorial)がサポートされます。

### 前提条件

Microsoft Entra ID を Uber と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Uber でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Uber アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Uber を追加する

Microsoft Entra アプリケーション ギャラリーから Uber を追加して、Uber でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「[クイック スタート: ギャラリー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)からアプリケーションを追加する」を参照してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[作成してユーザー アカウント](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) 割り当てる方法に関する記事のガイドラインに従ってください。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides).

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;、**Enterprise apps**&gt;、**Uber**&gt;、**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (PEM)]** を見つけて **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Uber のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### Uber SSO の構成

**Uber** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** とアプリケーションの構成からコピーした適切な URL を [Uber サポート チーム](mailto:business-support@uber.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Uber テスト ユーザーの作成

このセクションでは、Uber で Britta Simon というユーザーを作成します。 [Uber サポート チームまたは Uber POC](mailto:business-support@uber.com)と連携して、Uber プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。 Uber では、自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uber-provisioning-tutorial)を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Uber に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Uber] タイルを選択すると、SSO を設定した Uber に自動的にサインインします。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/uberflip-tutorial"} -->
## Microsoft Entra ID で Uberflip for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uberflip-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Uberflip 間にシングル サインオンを構成する方法について説明します。

この記事では、Uberflip と Microsoft Entra ID を統合する方法について説明します。

Uberflip と Microsoft Entra ID の統合には、次の利点があります:

- Uberflip にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで Uberflip に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央サイト (Azure ポータル) でアカウントを管理できます。

サービスとしてのソフトウェア (SaaS) アプリと Microsoft Entra ID の統合の詳細については、[Microsoft Entra ID を使ったアプリケーション アクセスとシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)に関する記事を参照してください。

### [前提条件]

Uberflip と Microsoft Entra の統合を構成するには、次の項目が必要です。

- Microsoft Entra サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- シングル サインオンが有効な Uberflip のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

Uberflip では、次の機能をサポートしています。

- SP または IDP が起点となるシングル サインオン (SSO)。
- Just-In-Time のユーザー プロビジョニング。

### Azure Marketplace から Uberflip を追加する

Microsoft Entra ID への Uberflip の統合を構成するには、Azure Marketplace からマネージド SaaS アプリの一覧に Uberflip を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。

    [Image: [新しいアプリケーション] オプション]
3. 検索ボックスに「**Uberflip**」と入力します。 検索結果で **[Uberflip]** を選択し、 **[追加]** を選択してアプリケーションを追加します。

    [Image: 結果一覧の Uberflip]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**B Simon** というテスト ユーザーに基づいて、Uberflip で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Uberflip 内の関連ユーザーとの間にリンクを確立する必要があります。

Uberflip で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります:

1. **Microsoft Entra シングル サインオンを構成**して、ユーザーがこの機能を使用できるようにします。
2. **Uberflip シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. B. Simon で Microsoft Entra のシングル サインオンをテストする Microsoft **Entra テスト ユーザーを作成**します。
4. **Microsoft Entra テスト ユーザーを割り当てて** 、B. Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Uberflip のテスト ユーザーの作成** - B. Simon という Microsoft Entra ユーザーにリンクされている B. Simon というユーザーが Uberflip に存在するようにします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Uberflip で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Uberflip** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン オプションを構成する]
3. **[シングル サインオン方式の選択]** ウィンドウで、 **[SAML/WS-Fed]** モードを選択して、シングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. **[SAML でシングル サインオンをセットアップします]** ウィンドウで、**編集** (鉛筆アイコン) を選択して **[基本的な SAML 構成]** ウィンドウを開きます。

    [Image: [基本的な SAML 構成] を示すスクリーンショット。ここに応答 U R L を入力することができます。]
5. **[基本的な SAML 構成]** ウィンドウで、構成する SSO モードに応じて、以下のいずれかの手順に従います。

    - IDP が起点となる SSO モードでアプリケーションを構成するには、 **[応答 URL (Assertion Consumer Service URL)]** ボックスに、次のパターンを使用して URL を入力します。

        `https://app.uberflip.com/sso/saml2/<IDPID>/<ACCOUNTID>`

        [Image: [Uberflip のドメインと URL] のシングル サインオン情報]

        注

        この値は実際の値ではありません。 実際の応答 URL でこの値を更新します。 実際の値を取得するには、[Uberflip サポート チーム](mailto:support@uberflip.com)にお問い合わせください。 **[Basic SAML Configuration] (基本的な SAML 構成)** ペインに示されているパターンを参照することもできます。
    - SP が起点となる SSO モードでアプリケーションを構成するには、 **[追加の URL を設定します]** を選択し、 **[サインオン URL]** ボックスに次の URL を入力します。

        `https://app.uberflip.com/users/login`

        [Image: スクリーンショットには、追加のURLを設定する画面が表示されており、ここでサインオンURLを入力することができます。]
6. **[SAML でシングル サインオンをセットアップします]** ウィンドウの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択し、特定のオプションの**フェデレーション メタデータ XML** をダウンロードします。

    [Image: フェデレーション メタデータ XML のダウンロード オプション]
7. **[Uberflip のセットアップ]** ウィンドウで、必要な URL をコピーします。

    - **ログイン URL**
    - **Microsoft Entra 識別子**
    - **ログアウト URL**

    [Image: 構成 URL をコピーする]

#### Uberflip シングル サインオンの構成

Uberflip 側でシングル サインオンを構成するには、ダウンロードしたフェデレーション メタデータ XML とコピーした適切な URL を [Uberflip サポート チーム](mailto:support@uberflip.com)に送信する必要があります。 Uberflip チームは、SAML SSO 接続が両方の側で正しく設定されていることを確認します。

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

このセクションでは、B. Simon に Uberflip へのアクセスを許可することで、このユーザーが Azure シングル サインオンを使用できるようにします。

1. **Entra ID**&gt;**Enterprise アプリ**&gt;**Uberflip** にアクセスします。

    [Image: [エンタープライズ アプリケーション] ウィンドウ]
2. アプリケーションの一覧で **[Uberflip]** を選択します。

    [Image: アプリケーションの一覧の Uberflip]
3. 左側のウィンドウの **[管理]** で、 **[ユーザーとグループ]** を選択します。

    [Image: [ユーザーとグループ] オプション]
4. **[+ ユーザーの追加]** を選択し、 **[割り当ての追加]** ウィンドウで **[ユーザーとグループ]** を選択します。

    [Image: [割り当ての追加] ウィンドウ]
5. **[ユーザーとグループ]** ウィンドウの **[ユーザー]** の一覧で **[B Simon]** を選択し、ウィンドウの下部にある **[選択]** を選択します。
6. SAML アサーションでロール値が必要な場合は、 **[ロールの選択]** ウィンドウで、一覧からユーザーに適したロールを選択します。 ウィンドウの下部で、 **[選択]** を選択します。
7. **[割り当ての追加]** ウィンドウで **[割り当て]** を選択します。

#### Uberflip テスト ユーザーを作成する

これで、B. Simon というユーザーが Uberflip に作成されました。 このユーザーを作成するために、何かをする必要はありません。 Uberflip では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 Uberflip に B. Simon というユーザーがまだ存在しない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Uberflip サポート チーム](mailto:support@uberflip.com)にお問い合わせください。

#### シングル サインオンのテスト

このセクションでは、マイ アプリ ポータルを使って Microsoft Entra のシングル サインオン構成をテストします。

マイ アプリ ポータルで **[Uberflip]** を選択すると、シングル サインオンを設定した Uberflip サブスクリプションに自動的にサインインするはずです。 マイアプリ ポータルの詳細については、「[マイ アプリ ポータルでアプリにアクセスして使用する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/udemy-business-saml-tutorial"} -->
## Microsoft Entra ID を使用して Udemy Business SAML for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/udemy-business-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Udemy Business SAML の間にシングル サインオンを構成する方法について説明します。

この記事では、Udemy Business SAML と Microsoft Entra ID を統合する方法について説明します。 Udemy for Business は、次に取り組むべきプロジェクト、習得するスキル、習得する役割など、従業員が次にやるべきことを支援します。 Udemy Business SAML を Microsoft Entra ID と統合すると、次のことができます。

- Udemy Business SAML にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Udemy Business SAML に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Udemy Business SAML 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Udemy Business SAML では、**SP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Udemy Business SAML と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Udemy Business SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Udemy Business SAML アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Udemy Business SAML を追加する

Microsoft Entra アプリケーション ギャラリーから Udemy Business SAML を追加して、Udemy Business SAML でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Udemy Business SAML**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、URL を入力します。 `https://www.udemy.com/sso/saml`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://sso.connect.pingidentity.com/sso/sp/ACS.saml2`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.udemy.com`

    注

    この値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 値を取得するには、[Udemy Business SAML クライアント サポート チーム](mailto:ufbsupport@udemy.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Udemy Business SAML アプリケーションでは、特定の形式の SAML アサーションが想定されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、Udemy Business SAML アプリケーションでは、SAML 応答でいくつかの属性が返されると想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Udemy Business SAML SSO の構成

**Udemy Business SAML** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Udemy Business SAML サポート チーム](mailto:ufbsupport@udemy.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Udemy Business SAML のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Udemy Business SAML に作成します。 Udemy Business SAML では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Udemy Business SAML にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Udemy Business SAML サインオン URL にリダイレクトされます。
- Udemy Business SAML のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Udemy Business SAML] タイルを選択すると、このオプションは Udemy Business SAML サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/uipath-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に UiPath を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uipath-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-14
- Summary: Microsoft Entra IDから UiPath にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために UiPath とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [UiPath](https://www.UiPath.com/) に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- UiPath でユーザーを作成する
- アクセスが不要になった場合に UiPath のユーザーを削除する
- Microsoft Entra IDと UiPath の間でユーザー属性の同期を維持する
- UiPath に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。
- クライアント資格情報認証方式に対応しています。

Note

UiPath では現在、ユーザー プロビジョニングのみがサポートされています。 グループ のプロビジョニングはサポートされていません。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ UiPath のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントの計画を立てる

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- Microsoft Entra IDとUiPathの間でマッピングするデータを決める。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように UiPath を構成する

1. UiPath 組織の管理者アクセス許可を持つアカウントを使用して、UiPath [Automation Cloud](https://cloud.uipath.com) にサインインします。
2. 左側のメニューで[ **管理**]を選択し、[ **セキュリティ設定]**&gt;**[認証設定**]に移動します。
3. Microsoft Entraディレクトリ統合が構成され、**Active** 状態が表示されていることを確認します。

    [Image: スクリーンショットは、アクティブな状態のMicrosoft Entra ID統合カードを示しています。]
4. [ **ディレクトリ統合の詳細** ] セクションまで下にスクロールし、[ **SCIM を有効にする]** を選択します。

    [Image: [ENABLE SCIM](SCIM を有効にする) ボタンを含む [Directory integration details](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ディレクトリ統合の詳細) セクションを示すスクリーンショット。]
5. ** SCIM ディレクトリ統合の構成** ダイアログで、Microsoft Entra IDでサポートされている次のいずれかの SCIM 承認方法を選択します。

    - **OAuth クライアント資格情報の付与** (推奨)
    - **有効期間の長いベアラー トークン**

    [Image: [CONFIGURE SCIM directory integration](SCIM ディレクトリ統合の構成) ダイアログと承認方法のオプションを示すスクリーンショット。]
6. **設定**を選択します。 **SCIM が有効になっています!**確認画面に、Microsoft Entra IDで構成する必要がある接続の詳細が表示されます。

    **ベアラー トークンの場合:**

    - **SCIM URL** : SCIM コネクタのエンドポイント URL。
    - **ベアラー トークン** : 要求を認証するためのシークレット トークン。

    [Image: スクリーンショットは、ベアラー トークンの詳細と属性マッピングを含む SCIM が有効な確認を示しています。]

    **OAuth クライアント資格情報の付与の場合:**

    - **SCIM URL** : SCIM コネクタのエンドポイント URL。
    - **OAuth アプリケーション ID** : OAuth アプリケーションのクライアント識別子。
    - **OAuth シークレット** : OAuth アプリケーションのクライアント シークレット。

    [Image: OAuth クライアント資格情報の詳細と属性マッピングを使用した SCIM 対応の確認を示すスクリーンショット。]

    これらの値をコピーします。このチュートリアルの後半で必要になります。

    Note

    このブラウザー タブは開いたままにしておきます。 手順 5. の手順 **を完了するまで、[ID プロバイダーの構成** を完了しました] を選択しないでください。
7. 手順 5 でMicrosoft Entra ID構成を完了したら、UiPath に戻り、** ID プロバイダーの構成が完了しました** を選択>。 [ディレクトリ統合の詳細] セクションで、SCIM プロビジョニングとプロビジョニング解除がアクティブであることを確認します。

    [Image: SCIM の構成後のディレクトリ統合の詳細を示すスクリーンショット。[SCIM の管理] ボタンと [SCIM の削除] ボタンが表示されています。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから UiPath を追加する

Microsoft Entra アプリケーション ギャラリーから UiPath を追加して、UiPath へのプロビジョニングの管理を開始します。 SSO 用に UiPath を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: UiPath への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて UiPath でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で UiPath の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともアプリ所有者または[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で [ **UiPath**] を選択します。

    [Image: [アプリケーション] の一覧の [UiPath] リンクを示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. 接続の詳細で、選択した承認方法に基づいて手順 2 で取得した値を入力します。

    **OAuth2 クライアント資格情報の付与 (推奨) の場合:**

    - **テナント URL** — 手順 2 の **SCIM URL を** 入力します (例: `https://cloud.uipath.com/{orgId}/identity_/api/scim/v2`)。
    - **トークン エンドポイント** — UiPath 組織のトークン交換エンドポイント ( `https://cloud.uipath.com/{org-name}/identity_/connect/token` など) を入力します。
    - **クライアント識別子** — 手順 2 の **OAuth アプリケーション ID を** 入力します。
    - **クライアント シークレット** — 手順 2 **の OAuth シークレット** を入力します。

    **ベアラー認証の場合:**

    - **テナント URL** — 手順 2 の **SCIM URL を** 入力します (例: `https://cloud.uipath.com/{orgId}/identity_/api/scim/v2`)。
    - **シークレット トークン** — 手順 2 の **ベアラー トークン** を入力します。

    [**Test Connection** を選択して、Microsoft Entra IDが UiPath に接続できることを確認します。 接続に失敗した場合は、入力した値が手順 2 で指定したものと一致するかどうかを確認し、やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから UiPath に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で UiPath のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が UiPath API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート | UiPath で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | String | ✓ | ✓ |
    | externalId | String | ✓ | ✓ |
    | displayName | String |  | ✓ |
    | title | String |  |  |
    | emails[type eq "仕事"].value | String |  |  |
    | name.givenName | String |  |  |
    | name.familyName | String |  |  |
    | アドレス[タイプ eq "職場"].ローカリティ | String |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | String |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | String |  |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ultipro-tutorial"} -->
## Microsoft Entra ID で UKG Pro をシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ultipro-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と UKG Pro 間にシングル サインオンを構成する方法について説明します。

この記事では、UKG Pro と Microsoft Entra ID を統合する方法について説明します。 UKG Pro を Microsoft Entra ID と統合すると、次のことができます。

- UKG Pro にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って UKG Pro に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- UKG Pro でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- UKG Pro では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの UKG Pro の追加

Microsoft Entra ID への UKG Pro の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に UKG Pro を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「UKG Pro**」と入力します。
4. 結果パネルから **UKG Pro** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### UKG Pro 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、UKG Pro に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと UKG Pro の関連ユーザーとの間にリンク関係を確立する必要があります。

UKG Pro に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **UKG Pro の SSO の構成**- アプリケーション側で単一 Sign-On 設定を構成します。
    1. **UKG Pro のテストユーザーを作成し**、Microsoft Entra のユーザーである B.Simon にリンクする UKG Pro 内の対応ユーザーを作ります。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**UKG Pro** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    エイ。 [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<companyname>.ultipro.com/` |
    | `https://<companyname>.ultiproworkplace.com?cpi=AZUREADISSSUERURL` |
    | `https://<companyname>.ultipro.ca` |

    b。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<companyname>.ultipro.com/adfs/services/trust` |
    | `https://<companyname>.ultiproworkplace.com/adfs/services/trust` |
    | `https://<companyname>.ultipro.ca/adfs/services/trust` |

    c. [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://<companyname>.ultipro.com/<instancename>` |
    | `https://<companyname>.ultiproworkplace.com/<instancename>` |
    | `https://<companyname>.ultipro.ca/<instancename>` |

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには、 [UKG Pro クライアント サポート チーム](https://www.ultimatesoftware.com/ContactUs) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **UKG Pro のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### UKG Pro の SSO の構成

**UKG Pro** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [UKG Pro サポート チーム](https://www.ultimatesoftware.com/ContactUs)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### UKG Pro のテスト ユーザーの作成

このセクションでは、UKG Pro で Britta Simon という名前のユーザーを作成します。 [UKG Pro サポート チーム](https://www.ultimatesoftware.com/ContactUs)と協力して、UKG Pro プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる UKG Pro のサインオン URL にリダイレクトされます。
- UKG Pro のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [UKG Pro] タイルを選択すると、このオプションは UKG Pro のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ungerboeck-software-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Ungerboeck Software を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ungerboeck-software-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Ungerboeck Software 間にシングル サインオンを構成する方法について学習します。

この記事では、Ungerboeck Software と Microsoft Entra ID を統合する方法について説明します。 Ungerboeck Software と Microsoft Entra ID を統合すると、次のことができます。

- Ungerboeck Software にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Ungerboeck Software に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) 対応の Ungerboeck Software サブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Ungerboeck Software では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの Ungerboeck Software の追加

Microsoft Entra ID への Ungerboeck Software の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Ungerboeck Software を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Ungerboeck Software**」と入力します。
4. 結果のパネルから **[Ungerboeck Software]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Ungerboeck Software 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Ungerboeck Software に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Ungerboeck Software の関連ユーザーとの間にリンク関係を確立する必要があります。

Ungerboeck Software で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ungerboeck Software の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Ungerboeck Software のテストユーザーを作成** - B.Simon に対応したユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Ungerboeck Software** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** ページで、次の手順を実行します。

    1. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.ungerboeck.com/prod`
    2. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    - **運用環境の場合**:

        - `https://<SUBDOMAIN>.ungerboeck.com/prod`
        - `https://<SUBDOMAIN>.ungerboeck.net/prod`
        - `https://<SUBDOMAIN>.ungerboeck.io/prod`
    - **テスト環境の場合**:

        - `https://<SUBDOMAIN>.ungerboeck.com/test`
        - `https://<SUBDOMAIN>.ungerboeck.net/test`
        - `https://<SUBDOMAIN>.ungerboeck.io/test`

    注

    これらの値は実際の値ではありません。 これらの値は、実際のサインオン URL と識別子で更新します。これについては、この記事の「 **Ungerboeck Software Single Sign-On の構成** 」セクションで後述します。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[Ungerboeck Software のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Ungerboeck Software の SSO の構成

**Ungerboeck Software** 側でシングル サインオンを構成するには、**サムプリントの値**とアプリケーション構成からコピーした適切な URL を [Ungerboeck Software サポート チーム](mailto:Rhonda.Jannings@ungerboeck.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Ungerboeck Software のテスト ユーザーの作成

このセクションでは、Ungerboeck Software で B.Simon というユーザーを作成します。 [Ungerboeck Software サポート チーム](mailto:Rhonda.Jannings@ungerboeck.com)と連携して、Ungerboeck Software プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Ungerboeck Software のサインオン URL にリダイレクトされます。
- Ungerboeck Software のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Ungerboeck Software] タイルを選択すると、このオプションは Ungerboeck Software のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/uni-tel-as-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Uni-tel A/S を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uni-tel-as-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-25
- Summary: Microsoft Entra ID から Uni-tel A/S に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Uni-tel A/S と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用し、[Uni-tel A/S](https://uni-tel.dk/) に対してユーザーの自動プロビジョニングおよび自動プロビジョニング解除を行うようになります。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Uni-tel A/S でのユーザー作成。
- アクセスが不要になったら、Uni-tel A/S のユーザーを削除します。
- Microsoft Entra ID と Uni-tel A/S の間でユーザー属性の同期を維持する。
- Uni-tel A/S への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Uni-tel A/S のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Uni-tel A/S の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### ステップ 2: Microsoft Entra ID によるプロビジョニングをサポートするように Uni-tel A/S を構成する

Microsoft Entra ID でのプロビジョニングをサポートするように Uni-tel A/S を構成する場合は、Uni-tel A/S サポートにお問い合わせください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Uni-tel A/S を追加する

Microsoft Entra アプリケーション ギャラリーから Uni-tel A/S を追加して、Uni-tel A/S へのプロビジョニングの管理を開始します。 SSO 用に Uni-tel A/S をセットアップ済みの場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Uni-tel A/S への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Uni-tel A/S に対する自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Uni-tel A/S]** を選択します。

    [Image: アプリケーションの一覧内の Uni-tel A/S リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Uni-tel A/S テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Uni-tel A/S に接続できることを確認します。 接続に失敗した場合は、Uni-tel A/S アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Uni-tel A/S に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Uni-tel A/S のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Uni-tel A/S API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Uni-tel A/S で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 |  |  |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/unifi-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に UNIFI を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/unifi-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから UNIFI にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために UNIFI と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーおよびグループを自動的にプロビジョニングと解除し、[UNIFI](http://www.unifilabs.com/) に同期します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- UNIFI でユーザーを作成する
- アクセスが不要になった場合に UNIFI のユーザーを削除する
- Microsoft Entra IDと UNIFI の間でユーザー属性の同期を維持する
- UNIFI でグループとグループ加入を設定する
- UNIFI への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/unifi-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [UNIFI](http://www.unifilabs.com/) テナント。
- 管理者アクセス許可を持つ UNIFI のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra ID と UNIFI の間で [マップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように UNIFI を構成する

1. Azureのエンタープライズ アプリケーションで SSO が正常に有効になっていることを確認します。
2. [シングル サインオン] で **[ログイン URL]** を見つけます。 ここでは、 `https://login.microsoftonline.com/<guid>/saml2`。
3. [SAML 署名証明書] セクションで証明書 (Base64) をダウンロードします。

    [Image: Enterprise Application SSO ビューのスクリーンショット。]
4. ID プロバイダーが UNIFI に追加されていない場合は、 **会社の管理者**として UNIFI Portal にログインします。 **[ユーザー] -&gt; [SSO の構成] -&gt; [プロバイダーの追加** ] ボタンに移動します。

    [Image: ID プロバイダー ビューの追加のスクリーンショット。]
5. SSO プロバイダーの追加モーダルが表示されます。

    [Image: IDプロバイダーモーダル追加のスクリーンショット。]
6. **[名前]** に希望する一意の値を指定します。 **URL** は、Microsoft Entra エンタープライズ アプリケーションの **Login URL** です。 **[トークン]** に任意の値を指定します。 **[証明書]** フィールドに証明書 (Base64) の値を入力します。 この時点から、作成したすべてのユーザーがこの ID プロバイダーを使用できるようにする場合は、 **[Make this the default identity provider](これを既定の ID プロバイダーにする)** チェックボックスをオンにします。

    [Image: ID プロバイダーのモーダル設定の追加のスクリーンショット。]
7. [保存] ボタンを選択します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから UNIFI を追加する

Microsoft Entra アプリケーション ギャラリーから UNIFI を追加して、UNIFI へのプロビジョニングの管理を開始します。 以前に SSO 向けに UNIFI を設定したことがある場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: UNIFI への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて UNIFI でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で UNIFI の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[UNIFI]** を選択します。

    [Image: アプリケーションの一覧の UNIFI リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、UNIFI **テナント URL** -`https://licensing.inviewlabs.com/api/scim/v2/` シークレット **トークン**を入力します。 [**Test Connection** を選択して、MICROSOFT ENTRA IDが UNIFI に接続できることを確認します。 接続に失敗した場合は、UNIFI アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから UNIFI に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で UNIFI のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、UNIFI API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | エクスターナルID | 糸 |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから UNIFI に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で UNIFI のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ディスプレイ名 | 糸 | ✓ |
    | メンバー | リファレンス |  |
    | エクスターナルID | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/unifi-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に UNIFI を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/unifi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と UNIFI 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、UNIFI と Microsoft Entra ID を統合する方法について説明します。 UNIFI を Microsoft Entra ID と統合すると、次のことができます。

- UNIFI にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って UNIFI に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な UNIFI サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- UNIFI では、サービスプロバイダー (SP) 主導のシングルサインオン (SSO) と、アイデンティティプロバイダー (IDP) 主導のシングルサインオン (SSO) がサポートされます。
- UNIFI では、 **自動** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの UNIFI の追加

Microsoft Entra ID への UNIFI の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに UNIFI を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「UNIFI**」と入力します。
4. 結果パネルから **[UNIFI]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### UNIFI 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、UNIFI に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと UNIFI の関連ユーザーとの間にリンク関係を確立する必要があります。

UNIFI に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **UNIFI SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **UNIFI テスト ユーザーの作成** - UNIFI において B.Simon に対応するユーザーを作成し、それを Microsoft Entra の B.Simon に結び付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**UNIFI**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `INVIEWlabs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.discoverunifi.com/login`
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **UNIFI のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### UNIFI の SSO の構成

1. 別の Web ブラウザー ウィンドウで、 **UNIFI** 企業サイトに管理者としてサインオンします。
2. **[ユーザー**] を選択します。

    [Image: スクリーンショットは、UNIFI サイトから選択されたユーザーを示しています。]
3. [ **新しい ID プロバイダーの追加] を選択します**。

    [Image: [Ad New Identity Provider](広告の新しい ID プロバイダー) が選択されているスクリーンショット。]
4. [ **ID プロバイダーの追加** ] セクションで、次の手順を実行します。

    [Image: [Add Identity Provider](ID プロバイダーの追加) を示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 [ **プロバイダー名]** ボックスに、ID プロバイダーの名前を入力します。

    b。 [ **プロバイダー URL** ] ボックスに **、ログイン URL** の値を貼り付けます。

    c. メモ帳でダウンロードした証明書を開き、 **---BEGIN CERTIFICATE---** を削除し、--- **END CERTIFICATE---** タグを選択し、残りの内容を **[証明書** ] ボックスに貼り付けます。

    d. [ **既定のプロバイダー** ] チェック ボックスをオンにします。

#### UNIFI のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを作成します。 **UNIFI** では自動ユーザー プロビジョニングがサポートされているため、手動の手順は必要ありません。 ユーザーは、Microsoft Entra ID からの認証が成功した後に自動的に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる UNIFI サインオン URL にリダイレクトされます。
- UNIFI のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した UNIFI に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで UNIFI タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した UNIFI に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/uniflow-online-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に uniFLOW Online を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uniflow-online-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから uniFLOW Online にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、uniFLOW Online と自動ユーザー プロビジョニングを構成するためにMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [uniFLOW Online](https://www.uniflowonline.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- uniFLOW Online でユーザーを作成します。
- uniFLOW Online でユーザーを無効にします。
- アクセスが不要になった場合は、uniFLOW Online のユーザーを削除します。
- Microsoft Entra IDと uniFLOW Online の間でユーザー属性の同期を維持します。
- uniFLOW Online に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uniflow-online-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- uniFLOW Online を使用する管理者アカウント。

### 手順 1: プロビジョニングのデプロイ計画を立てる

1. プロビジョニング サービスののしくみ について説明します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとuniFLOW Onlineの間で[マップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように uniFLOW Online を構成する

- 別の Web ブラウザー ウィンドウで、uniFLOW Online Web サイトに管理者としてサインインします。
- [**拡張機能** タブ] **&gt; [IDプロバイダー] &gt; [IDプロバイダーの構成] を選択します**。
- [ **ID プロバイダーの追加] を選択します**。 [ **ADD IDENTITY PROVIDER]\(ID プロバイダーの追加\)**セクションで、次の手順を実行します。
    - **表示名**を入力します。
    - **[プロバイダーの種類**] で、ドロップダウンから **[WS-Federation**] オプションを選択します。
    - **WS-Federation type** で、ドロップダウンから **Microsoft Entra ID** オプションを選択します。
    - を選択して [保存] します。
- ユーザーの&gt;内でに移動し、**詳細**に設定して、[高度な管理ビュー] を有効にします。
- これで、[プロビジョニング] タブが ID プロバイダー構成内で使用できるようになります。
- 会社のMicrosoft Entra IDでユーザー プロビジョニングを設定する準備ができたら、**Enable Provisioning**を選択します。
    - **プロビジョニング テナント URL** (**Provisioning** が有効になった後に 1 回だけ表示されます): Microsoft Entra アプリケーションでプロビジョニングを設定するときに、この URL が必要です。
    - **プロビジョニング シークレット トークン** (**Provisioning** が有効になった後に 1 回だけ表示されます): Microsoft Entra アプリケーションでプロビジョニングを設定するときに、このトークンが必要です。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから uniFLOW Online を追加する

Microsoft Entra アプリケーション ギャラリーから uniFLOW Online を追加して、uniFLOW Online へのプロビジョニングの管理を開始します。 SSO 用に uniFLOW Online を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: uniFLOW Online への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で uniFLOW Online の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で uniFLOW Online選択します。

    [Image: アプリケーションの一覧の uniFlow Online リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、uniFLOW Online テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが uniFLOW Online に接続できることを確認します。 接続に失敗した場合は、uniFLOW Online アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから uniFLOW Online に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で uniFLOW Online のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、uniFLOW Online API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | uniFLOW Online で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 | ✓ |  |
    | 活動中 | ブール値 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |  |
    | 表示名 | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |  |
    | タイトル | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:uniFLOWOnline:2.0:User:cardNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:uniFLOWOnline:2.0:User:カード登録コード | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:uniFLOWOnline:2.0:User:ローカルユーザー名 | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:uniFLOWOnline:2.0:User:pin | 糸 |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/uniflow-online-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に uniFLOW Online を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uniflow-online-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と uniFLOW Online の間でシングル サインオンを構成する方法について説明します。

この記事では、uniFLOW Online と Microsoft Entra ID を統合する方法について説明します。 uniFLOW Online と Microsoft Entra ID を統合すると、次のことができます。

- uniFLOW Online にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して uniFLOW Online にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- uniFLOW Online テナント。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- uniFLOW Online は、**SP** による SSO をサポートしています。
- uniFLOW Online は自動ユーザー プロビジョニング をサポートしています。

### ギャラリーから uniFLOW Online を追加する

Microsoft Entra ID への uniFLOW Online の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に uniFLOW Online を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**uniFLOW Online**」と入力します。
4. 結果のパネルから uniFLOW Online  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### uniFLOW Online の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、uniFLOW Online に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと uniFLOW Online の関連ユーザーとの間にリンク関係を確立する必要があります。

uniFLOW Online に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **uniFLOW Online SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **作成したテスト ユーザー** を使用して uniFLOW Online にサインインし、アプリケーション側でユーザーのサインインをテストします。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**uniFLOW Online**&gt;**シングルサインオン**をブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    a. **識別子 (エンティティ ID)** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<tenant_domain_name>.eu.uniflowonline.com` |
    | `https://<tenant_domain_name>.uk.uniflowonline.com` |
    | `https://<tenant_domain_name>.us.uniflowonline.com` |
    | `https://<tenant_domain_name>.sg.uniflowonline.com` |
    | `https://<tenant_domain_name>.jp.uniflowonline.com` |
    | `https://<tenant_domain_name>.au.uniflowonline.com` |
    | `https://<tenant_domain_name>.ca.uniflowonline.com` |

    b。 [**サインオン URL** テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<tenant_domain_name>.eu.uniflowonline.com` |
    | `https://<tenant_domain_name>.uk.uniflowonline.com` |
    | `https://<tenant_domain_name>.us.uniflowonline.com` |
    | `https://<tenant_domain_name>.sg.uniflowonline.com` |
    | `https://<tenant_domain_name>.jp.uniflowonline.com` |
    | `https://<tenant_domain_name>.au.uniflowonline.com` |
    | `https://<tenant_domain_name>.ca.uniflowonline.com` |

    手記

    これらの値は実際の値ではありません。 実際の識別子とサインオン URL でこれらの値を更新します。 これらの値 [取得するには、uniFLOW Online クライアント サポート チーム](mailto:support@nt-ware.com) にお問い合わせください。 また、Azure portal の 「**基本的な SAML 構成**」セクションに示されているパターンを参照するか、uniFLOW Online テナントに表示される応答 URL を参照することもできます。
6. uniFLOW Online アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方で、**nameidentifier** は **user.userprincipalname**にマッピングされています。 uniFLOW Online アプリケーションでは **、nameidentifier** が **user.objectid** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: スクリーンショットは、編集アイコンが強調表示された [ユーザー属性] ウィンドウを示しています。]
7. 上記に加えて、uniFLOW Online アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 表示名 | user.displayname |
    | ニックネーム | user.onpremisessamaccountname |

    手記

    `user.onpremisessamaccountname` 属性には、Microsoft Entra ユーザーがローカルの Windows Active Directory から同期されている場合にのみ値が含まれます。
8. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書の**] セクションで、[コピー] ボタンを選択して **アプリフェデレーション メタデータ URL** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### uniFLOW Online SSO の構成

1. 別の Web ブラウザー ウィンドウで、uniFLOW Online Web サイトに管理者としてサインインします。
2. 左側のナビゲーション パネルで、**[拡張機能] タブ** を選択します。

    [Image: スクリーンショットは、uniFLOW Online サイトから選択された拡張機能を示しています。]
3. [ID プロバイダー ] を選択します。

    [Image: スクリーンショットには、[ID プロバイダー] が選択されています。]
4. **[ID プロバイダーの構成]** を選択します。

    [Image: ID プロバイダーを構成するための [スクリーンショット] ボックスが表示]
5. **IDプロバイダーを追加**を選択します。

    [Image: スクリーンショットには、[ID プロバイダーの追加] が選択されています。]
6. **ADD IDENTITY PROVIDER** セクションで、次の手順を実行します。

    [Image: スクリーンショットは、説明されている値を入力できる [ADD IDENTITY PROVIDER] セクションを示しています。]

    a. 「例: Microsoft Entra SSO 表示名を入力します。

    b。 **プロバイダーの種類**で、ドロップダウンから **WS-Federation** オプションを選択します。

    c. **WS-Federation タイプの**の場合は、ドロップダウンから [**Microsoft Entra ID**] オプションを選択します。

    d. **保存**を選択します。
7. [**全般**] タブで、次の手順を実行します。

    [Image: スクリーンショットには、[全般] タブが表示され、説明されている値を入力できます。]

    a. 「例: Microsoft Entra SSO 表示名を入力します。

    b。 **[ID プロバイダー]** で **[Microsoft Entra SSO を有効にする]** を選択します。

    c. **From URL** オプションを **ADFS フェデレーション メタデータ**に対して選択します。

    d. **フェデレーション メタデータ URL** ボックスに、前にコピーした **アプリのフェデレーション メタデータ URL** 値を貼り付けます。

    e. **[Automatic user registration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/自動ユーザー登録)** で **[Activated](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アクティブ化)** を選択します。

    f. **保存**を選択します。

手記

**応答 URL** は自動的に事前入力され、変更できません。

#### 作成したテスト ユーザーを使用して uniFLOW Online にサインインする

1. 別の Web ブラウザー ウィンドウで、テナントの uniFLOW Online URL に移動します。
2. Microsoft Entra インスタンス経由でサインインするために、以前に作成した ID プロバイダーを選択します。
3. テスト ユーザーを使用してサインインします。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる uniFLOW Online のサインオン URL にリダイレクトされます。
- uniFLOW Online のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで uniFLOW Online タイルを選択すると、このオプションは uniFLOW Online のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/unite-us-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Unite Us を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/unite-us-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Unite Us 間にシングル サインオンを構成する方法について説明します。

この記事では、Unite Us と Microsoft Entra ID を統合する方法について説明します。 Unite Us には、SCIM のユーザー プロビジョニングと SAML SSO/JIT の既定の実装が用意されています。 Unite Us を Microsoft Entra ID と統合すると、次のことができます。

- Unite Us にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Unite Us に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Unite Us 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Unite Us は、**SP** Initiated と **IDP** Initiated の両方のシングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートしています。

### [前提条件]

Microsoft Entra ID を Unite Us と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Unite Us のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Unite Us アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Unite Us を追加する

Microsoft Entra アプリケーション ギャラリーから Unite Us を追加して、Unite Us でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Unite Us**&gt;**シングルサインオン**に移動してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、次のパターンで URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerIdentifier>.uniteustraining.com/auth/saml/metadata` |
    | `https://<CustomerIdentifier>.uniteus.io/auth/saml/metadata` |

    b。 **[応答 URL]** ボックスに、次の形式で URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerIdentifier>.uniteustraining.com/auth/saml/callback` |
    | `https://<CustomerIdentifier>.uniteus.io/auth/saml/callback` |
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のいずれかの URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://app.auth.uniteus.io/` |
    | `https://app.auth.uniteustraining.com/` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Unite Us クライアント サポート チーム](mailto:isd.support@uniteus.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Unite Us のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

### Unite Us SSO を構成する

**Unite Us** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Unite Us サポート チーム](mailto:isd.support@uniteus.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Unite Us テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Unite Us に作成します。 Unite Us では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Unite Us にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Unite Us のサインオン URL にリダイレクトされます。
- Unite Us のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Unite Us に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Unite Us] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Unite Us に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/upshotly-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Upshotly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/upshotly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Upshotly 間にシングル サインオンを構成する方法について説明します。

この記事では、Upshotly と Microsoft Entra ID を統合する方法について説明します。 Upshotly を Microsoft Entra ID を統合すると、次のことができます。

- Upshotly にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Upshotly に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Upshotly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Upshotly では、**SP Initiated SSO** および **IDP Initiated SSO** がサポートされます。

### ギャラリーからの Upshotly の追加

Microsoft Entra ID への Upshotly の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Upshotly を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Upshotly**」と入力します。
4. 結果パネルから **Upshotly** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Upshotly 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Upshotly に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Upshotly の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Upshotly と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Upshotly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Upshotly のテストユーザーを作成** - Microsoft Entra のユーザーとして表現されている B.Simon に対応するユーザーを Upshotly で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Upshotly**&gt;**シングルサインオンに**移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、アプリケーションが事前に構成されており、必要な URL が既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.upshotly.com/api/sso/login/<companyID>`

    注

    サインオン URL の値は実際の値ではありません。 この値は実際のサインオン URL に変更します。 この記事の後半で説明する **companyID** 値を取得します。 クエリについては [、Upshotly クライアント サポート チーム](mailto:support@upshotly.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Upshotly のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Upshotly SSO の構成

1. 別の Web ブラウザー ウィンドウで、Upshotly 企業サイトに管理者としてサインインします
2. **ユーザー プロファイル**を選択し、[**Admin &gt; SSO**] に移動し、次の手順を実行します。

    [Image: Upshotly 構成]

    あ **会社 ID** の値をコピーし、この**会社 ID** 値を使用して、[**基本的な SAML 構成]** セクションの **[サインオン URL] に**存在する**会社 ID** の値を置き換えます。

    b。 Azure portal からダウンロードした **フェデレーション メタデータ XML を** メモ帳に開き、メタデータ XML の内容をコピーして **XML メタデータ** テキスト ボックスに貼り付けます。

#### Upshotly テスト ユーザーの作成

このセクションでは、Upshotly Edge Cloud で B.Simon というユーザーを作成します。 [Upshotly クライアント サポート チーム](mailto:support@upshotly.com)と協力して、Upshotly Edge Cloud プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Upshotly のサインオン URL にリダイレクトされます。
- Upshotly のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Upshotly に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Upshotly] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Upshotly に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/upwork-enterprise-tutorial"} -->
## Microsoft Entra IDでシングルサインオンのためにUpwork Enterpriseを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/upwork-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Upwork Enterprise の間にシングル サインオンを構成する方法について説明します。

この記事では、Upwork Enterprise と Microsoft Entra ID を統合する方法について説明します。 Upwork Enterprise を Microsoft Entra ID を統合すると、次のことができます。

- Upwork Enterprise にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Upwork Enterprise に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Upwork Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Upwork Enterprise では、**SP と IDP によって開始される SSO** がサポートされます。
- Upwork Enterprise では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Upwork Enterprise の追加

Microsoft Entra ID への Upwork Enterprise の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Upwork Enterprise を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Upwork Enterprise**」と入力します。
4. 結果のパネルから **Upwork Enterprise** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Upwork Enterprise 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Upwork Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Upwork Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

Upwork Enterprise に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Upwork Enterprise の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Upwork Enterprise のテスト ユーザーの作成** - Microsoft Entra 上のユーザー表現にリンクする、Upwork Enterprise での B.Simon の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Upwork Enterprise**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.upwork.com/ab/account-security/login`
7. **保存** を選択します。
8. Upwork Enterprise アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Upwork Enterprise アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | 国 | ユーザーの国 |
    | Email | ユーザー.ユーザープリンシパルネーム |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Upwork Enterprise のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Upwork Enterprise の SSO の構成

**Upwork Enterprise** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Upwork Enterprise サポート チーム](https://support.upwork.com/hc/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Upwork Enterprise のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Upwork Enterprise に作成します。 Upwork Enterprise では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Upwork Enterprise にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Upwork Enterprise のサインオン URL にリダイレクトされます。
- Upwork Enterprise のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Upwork Enterprise に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Upwork Enterprise] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Upwork Enterprise に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/us-bank-prepaid-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に米国銀行プリペイドを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/us-bank-prepaid-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と U.S. Bank Prepaid の間にシングル サインオンを構成する方法について説明します。

この記事では、米国銀行プリペイドと Microsoft Entra ID を統合する方法について説明します。 U.S. Bank Prepaid と Microsoft Entra ID を統合すると、次のことができます:

- U.S. Bank Prepaid にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して U.S. Bank Prepaid に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- U.S. Bank Prepaid でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- U.S. Bank Prepaid では、**SP および IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから U.S. Bank Prepaid を追加する

Microsoft Entra ID への U.S. Bank Prepaid の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に U.S. Bank Prepaid を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**U.S. Bank Prepaid**」と入力します。
4. 結果パネルから **[U.S. Bank Prepaid]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### U.S. Bank Prepaid 用に Microsoft Entra SSO を構成してテストする

**B.Simon** という名前のテスト ユーザーを使用して、U.S. Bank Prepaid での Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと U.S. Bank Prepaid の関連ユーザーとの間にリンク関係を確立する必要があります。

U.S. Bank Prepaid に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **U.S. Bank Prepaid の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **U.S. Bank Prepaid のテストユーザーの作成** - U.S. Bank Prepaid で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**U.S. Bank Prepaid**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、**基本的な SAML 構成** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[基本的な SAML 構成]** セクションで、アプリケーションを **SP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 **識別子** テキスト ボックスに、値を入力します: `USBank:SAML2.0:Prepaid_SP`

    b。 [**応答 URL** テキスト ボックスに、URL: `https://federation.usbank.com/sp/ACS.saml2` を入力します。

    c. [**サインオン URL** テキスト ボックスに、URL: `https://federation.usbank.com/sp/startSSO.ping?PartnerIdpId=<ID>` を入力します。

    注

    サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、[U.S. Bank Prepaid クライアント サポート チーム](mailto:web.access.management@usbank.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書の**] セクションで、[コピー] ボタンを選択して **アプリフェデレーション メタデータ URL** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### U.S. Bank Prepaid のSSO を構成する

**U.S. Bank Prepaid** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [U.S. Bank Prepaid サポート チーム](mailto:web.access.management@usbank.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### U.S. Bank Prepaid のテスト ユーザーの作成

このセクションでは、U.S. Bank Prepaid で Britta Simon という名前のユーザーを作成します。 [U.S. Bank Prepaid サポート チーム](mailto:web.access.management@usbank.com)と協力して、U.S. Bank Prepaid プラットフォームでユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる米国銀行プリペイド サインオン URL にリダイレクトされます。
- U.S. Bank Prepaid のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した米国銀行プリペイドに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [米国銀行プリペイド] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した米国銀行プリペイドに自動的にサインインされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/useall-tutorial"} -->
## Microsoft Entra ID でシングル サインオンに Useall を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/useall-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Useall 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、Useall と Microsoft Entra ID を統合する方法について説明します。 Useall と Microsoft Entra ID の統合には、次の利点があります:

- Useall にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが Microsoft Entra アカウントで Useall に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Useall でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Useall では、**SP** Initiated SSO がサポートされます

### ギャラリーからの Useall の追加

Microsoft Entra ID への Useall の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Useall を追加する必要があります。

**ギャラリーから Useall を追加するには、次の手順を行います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Useall**」と入力し、結果パネルで **Useall** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果リストの Useall]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Useall で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Useall 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Useall で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Useall のシングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Useall テストユーザーを作成する** - Useall 内で Britta Simon の対応するユーザーを作成し、そのユーザーを Microsoft Entra ユーザーとしてリンクします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Useall で Microsoft Entra シングル サインオンを構成するには、次の手順を行います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Useall** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: Useall のドメインと URL のシングル サインオン情報]

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.useall.com.br/tenant/useall`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.useall.com.br/tenant/apiuseall/saml2`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Useall クライアント サポート チーム](mailto:luizotavio@useall.com.br)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Useall のシングル サインオンの構成

**Useall** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Useall サポート チーム](mailto:luizotavio@useall.com.br)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Useall のテスト ユーザーの作成

このセクションでは、Useall で Britta Simon というユーザーを作成します。 [Useall サポート チーム](mailto:luizotavio@useall.com.br)と連携して、Useall プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Useall] タイルを選択すると、SSO を設定した Useall に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/user-interviews-tutorial"} -->
## Microsoft Entra ID でシングル サインオンのユーザー インタビューを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/user-interviews-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-05
- Summary: Microsoft Entra ID と User Interviews の間でシングル サインオンを構成する方法について説明します。

この記事では、User Interviews と Microsoft Entra ID を統合する方法について説明します。 ユーザー インタビューを Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra ID でユーザーインタビューへのアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して User Interviews に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- User Interviews でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- User Interviews では、**SPおよびIDP**の両方のSSOをサポートしています。
- ユーザー インタビューでは、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからのユーザー インタビューの追加

Microsoft Entra ID への User Interviews の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に User Interviews を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**User Interviews**」と入力します。
4. 結果パネルから **[ユーザー インタビュー** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ユーザー インタビューの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、ユーザー インタビューに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと User Interviews の関連ユーザーとの間にリンク関係を確立する必要があります。

ユーザー インタビューで Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **User Interviews の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **User Interviews のテスト ユーザーの作成** - User Interviews で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**ユーザーインタビュー**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://www.userinterviews.com/saml/metadata?team_id=<team_id>` |
    | `https://www.userinterviews.com/saml/metadata?<team_id>` |

    b。 [**応答 URL**] ボックスに、次のいずれかの URL/パターンを入力します。

    | **応答 URL** |
    | --- |
    | `https://www.userinterviews.com/saml/consume?team_id=<team_id>` |
    | `https://www.userinterviews.com/saml/consume` |
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.userinterviews.com/saml/join?team_id=<team_id>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、User Interviews サポート チーム](mailto:support@userinterviews.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. User Interviews アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
8. 上記に加えて、User Interviews アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | last\_name | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. [ **ユーザー インタビューの設定** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### User Interviews SSO の設定をする

1. User Interviews 企業サイトに管理者としてログインします。
2. **Microsoft Entra Setup**&gt;**Team 設定**&gt;**Advanced オプションに**移動し、次の手順を実行します。

    [Image: 構成を示すスクリーンショット。]

    ある。 ダウンロードした **証明書 (Base64)** をメモ帳に開き、その内容を **SSO 証明書** ボックスに貼り付けます。

    b。 **[SSO エンティティ ID**] ボックスに、**Microsoft Entra** 管理センターからコピーした Microsoft Entra 識別子を貼り付けます。

    c. **[SSO URL**] ボックスに、Microsoft Entra 管理センターからコピーした**ログイン URL** の値を貼り付けます。

    d. **保存** を選択します。

    え **ACS URL を**コピーし、Microsoft Entra 管理センターの **[基本的な SAML 構成**] セクションの **[応答 URL**] ボックスに貼り付けます。

    f. **エンティティ ID を**コピーし、Microsoft Entra 管理センターの [**基本的な SAML 構成**] セクションの [**識別子 (エンティティ ID)]** ボックスに貼り付けます。

#### ユーザーインタビューのテストユーザーを作成する

このセクションでは、Britta Simon というユーザーを User Interviews に作成します。 User Interviews では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 User Interviews にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる User Interviews のサインオン URL にリダイレクトします。
- User Interviews のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定したユーザー インタビューに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ユーザー インタビュー] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した User Interviews に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/userecho-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に UserEcho を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/userecho-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と UserEcho 間にシングル サインオンを構成する方法について説明します。

この記事では、UserEcho と Microsoft Entra ID を統合する方法について説明します。 UserEcho を Microsoft Entra ID を統合すると、次のことができます。

- UserEcho にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って UserEcho に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

UserEcho と Microsoft Entra の統合を構成するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- UserEcho でのシングル サインオンが有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- UserEcho では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの UserEcho の追加

Microsoft Entra ID への UserEcho の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に UserEcho を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「UserEcho**」と入力します。
4. 結果パネルから **UserEcho** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### UserEcho 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、UserEcho に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと UserEcho の関連ユーザーとの間にリンク関係を確立する必要があります。

UserEcho に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **UserEcho の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **UserEcho テスト ユーザーの作成 - UserEcho** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**UserEcho**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.userecho.com/saml/metadata/`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.userecho.com/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [UserEcho クライアント サポート チーム](https://feedback.userecho.com/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **UserEcho のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 設定を適切な URL にコピーする際に使用するスクリーンショットを表示します。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### UserEcho SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として UserEcho 企業サイトにサインオンします。
2. 上部のツール バーで、ユーザー名を選択してメニューを展開し、[セットアップ] を選択 **します**。

    [Image: UserEchoウェブサイトから選択されたセットアップを示すスクリーンショット。]
3. **統合**を選択します。

    [Image: [設定] メニューから選択された統合を示すスクリーンショット。]
4. [ **Web サイト**] を選択し、[ **シングル サインオン (SAML2)]** を選択します。

    [Image: [統合] メニューから選択されたシングル サインオン SAML2 を示すスクリーンショット。]
5. [ **シングル サインオン (SAML)] ページで** 、次の手順に従います。

    [Image: 説明されている値を入力できる [シングル サインオン SAML] ページを示すスクリーンショット。]

    ある。 **SAML が有効になっている場合は**、[**はい**] を選択します。

    b。 **[SAML SSO URL**] ボックスにログイン **URL を**貼り付けます。

    c. **[リモート ログアウト URL**] ボックスに**ログアウト URL を**貼り付けます。

    d. ダウンロードした証明書をメモ帳で開き、内容をコピーして、[ **X.509 証明書** ] ボックスに貼り付けます。

    え **[保存] を選択します**。

#### UserEcho のテスト ユーザーの作成

このセクションの目的は、UserEcho で Britta Simon というユーザーを作成することです。

**UserEcho で Britta Simon というユーザーを作成するには、次の手順に従います。**

1. UserEcho 企業サイトに管理者としてサインオンします。
2. 上部のツール バーで、ユーザー名を選択してメニューを展開し、[セットアップ] を選択 **します**。

    [Image: UserEchoウェブサイトから選択されたセットアップを示すスクリーンショット。]
3. [ **ユーザー]** を選択して、[ **ユーザー** ] セクションを展開します。

    [Image: [設定] メニューから [ユーザー] が選択されているスクリーンショット。]
4. **ユーザー**を選択します。

    [Image: スクリーンショットは「ユーザー」が選択されたボタンを示しています。]
5. [ **新しいユーザーの招待] を選択します**。

    [Image: スクリーンショットは、新しいユーザーを招待するコントロールを示しています。]
6. [ **新しいユーザーの招待** ] ダイアログで、次の手順を実行します。

    [Image: スクリーンショットは、[ユーザー情報を入力できる新しいユーザーの招待] ダイアログ ボックスを示しています。]

    ある。 [ **名前** ] ボックスに、Britta Simon のようなユーザーの名前を入力します。

    b。 [ **電子メール** ] ボックスに、ユーザーのメール アドレス ( Brittasimon@contoso.comなど) を入力します。

    c. [ **招待**] を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる UserEcho のサインオン URL にリダイレクトされます。
- UserEcho のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [UserEcho] タイルを選択すると、このオプションは UserEcho のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/usertesting-saml-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に UserTesting を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/usertesting-saml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と UserTesting の間にシングル サインオンを構成する方法について説明します。

この記事では、UserTesting を Microsoft Entra ID を統合する方法について説明します。 UserTesting は、Web サイト、モバイル アプリ、プロトタイプ、現実世界のエクスペリエンスなど、想像しうるほぼすべてのカスタマー エクスペリエンスについて、迅速な顧客フィードバックを得るためのプラットフォームです。 UserTesting を Microsoft Entra ID を統合すると、次のことができます。

- UserTesting にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って UserTesting に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で UserTesting 用に Microsoft Entra シングル サインオンを構成してテストします。 UserTesting では、 **SP** と **IDP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

Microsoft Entra ID を UserTesting と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- UserTesting でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから UserTesting アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから UserTesting を追加する

Microsoft Entra アプリケーション ギャラリーから UserTesting を追加して、UserTesting に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**UserTesting**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://www.okta.com/saml2/service-provider/<Account_Name>`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 ` https://auth.usertesting.com/sso/saml2/<ID>`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **サインオン URL** ] ボックスに、URL を入力します。 `https://app.usertesting.com/users/sso_sign_in`

    **リレー状態**テキストボックスにURLを入力してください。`https://app.usertesting.com/sessions/from_idp`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [UserTesting クライアント サポート チーム](mailto:support@usertesting.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **UserTesting のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### UserTesting SSO の構成

**UserTesting** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [UserTesting サポート チーム](mailto:support@usertesting.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### UserTesting テスト ユーザーの作成

このセクションでは、UserTesting で Britta Simon というユーザーを作成します。 [UserTesting サポート チーム](mailto:support@usertesting.com)と協力して、UserTesting プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる UserTesting のサインオン URL にリダイレクトされます。
- UserTesting のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した UserTesting に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [UserTesting] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した UserTesting に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/uservoice-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に UserVoice を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/uservoice-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と UserVoice 間にシングル サインオンを構成する方法についてご確認ください。

この記事では、UserVoice と Microsoft Entra ID を統合する方法について説明します。 UserVoice を Microsoft Entra ID と統合すると、次のことができます。

- UserVoice にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して UserVoice に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- UserVoice でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- UserVoice では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの UserVoice の追加

Microsoft Entra ID への UserVoice の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に UserVoice を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**UserVoice**」と入力します。
4. 結果のパネルから **[UserVoice]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### UserVoice 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、UserVoice に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと UserVoice の関連ユーザーとの間にリンク関係を確立する必要があります。

UserVoice に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **UserVoice SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **UserVoice テスト ユーザーの作成** - UserVoice 内で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon の表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**UserVoice**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANT_NAME>.UserVoice.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANT_NAME>.UserVoice.com`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[UserVoice クライアント サポート チーム](https://www.uservoice.com/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[UserVoice のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### UserVoice SSO の構成

1. 別の Web ブラウザーのウィンドウで、UserVoice 企業サイトに管理者としてサインインします。
2. 上部のツール バーで **、[設定]** を選択し、メニューから **[Web ポータル** ] を選択します。

    [Image: アプリ側の [設定] セクション]
3. **[Web ポータル**] タブの [**ユーザー認証**] セクションで、[**編集**] を選択して [**ユーザー認証の編集**] ダイアログ ページを開きます。

    [Image: [Web ポータル] タブ]
4. **[ユーザー認証の編集]** ダイアログ ページで、次の手順に従います。

    [Image: ユーザー認証の編集]

    ある。 [ **Single Sign-On (SSO)]\(シングル Sign-On (SSO)\) を**選択します。

    b。 **[ログイン URL]** 値を **[SSO リモート サインイン]** ボックスに貼り付けます。

    c. **[ログアウト URL]** 値を **[SSO リモート サインアウト]** ボックスに貼り付けます。

    d. **[拇印]** 値を **[現在の証明書 SHA1 フィンガープリント]** ボックスに貼り付けます。

    え [ **認証設定の保存] を選択します**。

#### UserVoice のテスト ユーザーの作成

Microsoft Entra ユーザーが UserVoice にサインインできるようにするには、そのユーザーを UserVoice にプロビジョニングする必要があります。 UserVoice の場合、プロビジョニングは手動で行います。

#### ユーザー アカウントをプロビジョニングするには、次の手順を実行します。

1. **UserVoice** テナントにサインインします。
2. **[設定]** に移動します。

    [Image: 設定]
3. **全般** を選択します。
4. **[エージェントとアクセス許可] を選択します**。

    [Image: エージェントとアクセス許可]
5. [ **管理者の追加] を選択します**。

    [Image: 管理者の追加]
6. **[管理者の招待]** ダイアログで、次の手順を実行します。

    [Image: 管理者の招待]

    ある。 [電子メール] ボックスに、プロビジョニングするアカウントのメール アドレスを入力し、[ **追加**] を選択します。

    b。 [ **招待**] を選択します。

注

他の UserVoice ユーザー アカウント作成ツールや、UserVoice から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる UserVoice のサインオン URL にリダイレクトされます。
- UserVoice のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [UserVoice] タイルを選択すると、このオプションは UserVoice のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/userzoom-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に UserZoom を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/userzoom-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と UserZoom の間にシングル サインオンを構成する方法について説明します。

この記事では、UserZoom と Microsoft Entra ID を統合する方法について説明します。 UserZoom を Microsoft Entra ID と統合すると、次のことができます。

- UserZoom にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して UserZoom に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な UserZoom のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- UserZoom では、**SP Initiated SSO** と** IDP Initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから UserZoom を追加する

Microsoft Entra ID への UserZoom の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に UserZoom を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**UserZoom**」と入力します。
4. 結果のパネルから **[UserZoom]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### UserZoom 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、UserZoom で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと UserZoom の関連ユーザーとの間にリンク関係を確立する必要があります。

UserZoom に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **UserZoom SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **UserZoomのテストユーザーを作成する - UserZoom**においてB.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**UserZoom**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **[基本的な SAML 構成]** セクションで、アプリケーションを **SP** 開始モードで構成する場合は、次の手順を実行します:

    a [ **識別子** ] テキスト ボックスに、値を入力します。 `urn:auth0:auth-userzoom:microsoft`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://auth.userzoom.com/login/callback?connection=microsoft`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.manager.userzoom.com/microsoft`
7. UserZoom アプリケーションは特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、Authomize アプリケーション イメージを示しています。]
8. その他に、UserZoom アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | given\_name | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
10. **[UserZoom のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### UserZoom SSO の構成

**UserZoom** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[UserZoom サポート チーム](mailto:support@userzoom.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### UserZoom テスト ユーザーを作成する

このセクションでは、UserZoom で Britta Simon というユーザーを作成します。 [UserZoom サポート チーム](mailto:support@userzoom.com)と連携し、UserZoom プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる UserZoom サインオン URL にリダイレクトされます。
- UserZoom のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した UserZoom に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [UserZoom] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した UserZoom に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/v-client-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に V-Client を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/v-client-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから V-Client にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために V-Client と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成された Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを [V-Client](https://www.amiya.co.jp/solutions/verona) に自動的にプロビジョニングおよび削除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- V-Client でユーザーを作成する。
- アクセスが不要になった場合は、V-Client のユーザーを削除します。
- Microsoft Entra IDと V-Client の間でユーザー属性の同期を維持します。
- V-Client でグループとグループ メンバーシップをプロビジョニングする。
- [V-Client へのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/v-client-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者のアクセス許可がある V-Client のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとV-Client間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように V-Client を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように V-Client を構成するには、V-Client サポートに問い合わせてください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから V-Client を追加する

Microsoft Entra アプリケーション ギャラリーから V-Client を追加して、V-Client へのプロビジョニングの管理を開始します。 SSO のために V-Client を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: V-Client への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で V-Client の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **V-Client**] を選択します。

    [Image: アプリケーションの一覧の [V-Client] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、V クライアント テナントの URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが V-Client に接続できることを確認します。 接続に失敗した場合は、V-Client アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから V-Client に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で V-Client のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が V-Client API でサポートされていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | V-Client で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | 表示名 | 糸 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | エクスターナルID | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから V-Client に同期されるグループ属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で V-Client のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | V-Client で必須 |
    | --- | --- | --- | --- |
    | 表示名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 |  |  |
    | メンバーズ | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/v-client-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に V-Client を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/v-client-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と V-Client 間にシングル サインオンを構成する方法について学習します。

この記事では、V-Client と Microsoft Entra ID を統合する方法について説明します。 V-Client を Microsoft Entra ID と統合すると、次のことができます。

- V-Client にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って V-Client に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- V-Client でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- V-Client では、**IDP** Initiated SSO がサポートされます。

### ギャラリーから V-Client を追加する

Microsoft Entra ID への V-Client の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに V-Client を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**V-Client**」と入力します。
4. 結果のパネルから **[V-Client]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### V-Client 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、V-Client で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと V-Client の関連ユーザーの間にリンク関係を確立する必要があります。

V-Client で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **V-Client SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **V-Client テスト ユーザーの作成 - V-Client** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**V-Client**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<Environment>.verona.amigram.xyz/<CustomerName>` |
    | ` https://<Environment>.all-cloud.jp/<CustomerName>` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<Environment>-api.verona.amigram.xyz/id/saml2/acs` |
    | `https://<Environment>-api.all-cloud.jp/id/saml2/acs` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[V-Client クライアント サポート チーム](mailto:verona-support@amiya.co.jp)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. V-Client アプリケーションでは、特定の形式の SAML アサーションが使用されるため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、V-Client アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | oid | user.objectid (ユーザーのオブジェクトID) |
    | 表示名 | ユーザー名表示 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### V-Client SSO の構成

**V-Client** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と、アプリケーション構成からコピーした適切な URL を [V-Client サポート チーム](mailto:verona-support@amiya.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### V-Client テスト ユーザーの作成

このセクションでは、V-Client で Britta Simon というユーザーを作成します。 [V-Client サポート チーム](mailto:verona-support@amiya.co.jp)と連携して、V-Client プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した V-Client に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [V-Client] タイルを選択すると、SSO を設定した V-Client に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/valence-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Valence Security Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/valence-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Valence Security Platform の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Valence Security Platform と Microsoft Entra ID を統合する方法について説明します。 Valence Security Platform を Microsoft Entra ID を統合すると、次のことができます。

- Valence Security Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Valence Security Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Valence セキュリティプラットフォーム でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Valence セキュリティプラットフォーム では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーからValence セキュリティプラットフォームを追加する

Microsoft Entra への Valence Security Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Valence Security Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Valence セキュリティプラットフォーム**」と入力します。
4. 結果パネルから **[Valence セキュリティプラットフォーム]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Valence Security Platform 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Valence Security Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Valence Security Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Valence Security Platform で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Valence セキュリティプラットフォームの SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Valence Security Platform のテストユーザーを作成し、Microsoft Entra で表されるユーザーとして B.Simon の対応を持つユーザーをリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Valence Security Platform**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するためのスクリーンショットが表示されます。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **識別子** テキストボックスに、次のパターンを使用してURLを入力してください: `https://app.valencesecurity.com/auth/realms/valence/broker/<CustomerName>/endpoint/clients/oktasamlapp`

    b。 [**応答 URL** ボックスに、次のパターンを使用して URL を入力します:`https://app.valencesecurity.com/auth/realms/valence/broker/<CustomerName>/endpoint/clients/oktasamlapp`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [Valence セキュリティプラットフォーム サポート チーム](mailto:support@valencesecurity.com) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Valence セキュリティプラットフォーム のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Valence セキュリティ プラットフォームSSOを構成する

**Valence セキュリティプラットフォーム** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Valence Security Platform サポート チーム](mailto:support@valencesecurity.com) に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Valence セキュリティ プラットフォームのテストユーザーを作成する

このセクションでは、Valence セキュリティプラットフォームで Britta Simon というユーザーを作成します。 [Valence セキュリティプラットフォームサポート チーム](mailto:support@valencesecurity.com)と協力して、Valence セキュリティプラットフォームのプラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Valence セキュリティ プラットフォームに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Valence Security Platform] タイルを選択すると、SSO を設定した Valence Security Platform に自動的にサインインします。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/valid8me-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの valid8Me を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/valid8me-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と valid8Me 間にシングル サインオンを構成する方法について学習します。

この記事では、valid8Me と Microsoft Entra ID を統合する方法について説明します。 valid8Me と Microsoft Entra ID を統合すると、次のことができます。

- valid8Me にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って valid8Me に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な valid8Me のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- valid8Me では、**SP**開始のSSOと**IDP**開始のSSOをサポートしています。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから valid8Me を追加する

Microsoft Entra ID への valid8Me の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに valid8Me を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. [ **ギャラリーからの追加** ] セクションで、検索ボックスに **「valid8Me** 」と入力します。
4. 結果パネルから **valid8Me** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### valid8Me 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、valid8Me に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと valid8Me の関連ユーザーとの間にリンク関係を確立する必要があります。

valid8Me で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **valid8Me SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **valid8Me テストユーザーを作成する** - これは、Microsoft Entra 上の B.Simon にリンクされた valid8Me での対応ユーザーを作成するためです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**valid8Me**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合:

    [ **サインオン URL (省略可能)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.valid8me.com/?idp=https://sts.windows.net/${TenantID}/`

    注意

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには [、valid8Me サポート チーム](mailto:support@valid8me.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Valid8Me のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### valid8Me SSO の構成

1. valid8Me 企業サイトに管理者としてログインします。
2. 左側のメニューで、[**構成**]&gt;**シングルサインオン**&gt;**Identity Management** タブを展開し、[**作成**] を選択します。
3. **Microsoft Entra SAML 設定**セクションで、次の手順を実行します。

    ある。 [ **ログイン URL** ] ボックスに、前にコピーした **ログイン URL** の値を貼り付けます。

    b。 **[Microsoft Entra Identifier]\(Microsoft Entra 識別子\)** ボックスに、前にコピーした **Microsoft Entra Identifier** の値を貼り付けます。

    c. [ **ログアウト URL** ] ボックスに、前にコピーした **ログアウト URL** の値を貼り付けます。

    d. ダウンロードした **証明書 (Base64)** をメモ帳に開き、[ **証明書 (Base64)]** ボックスにファイルをアップロードします。

    え **[作成]**を選択します。

#### valid8Me テスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、valid8Me Web サイトに管理者としてログインします。
2. **[構成**&gt;**Single Sign On**&gt;**Invitation** タブに移動し、[**作成**] を選択します。
3. **[作成**] ページで次の手順を実行します。

    [Image: [ユーザー情報] フィールドを示すスクリーンショット。]

    1. **Email Suffixes** ボックスに有効なメールドメインを入力します。

        注意

        ドメイン名は、Microsoft Entra アカウントのメール ドメインと同じである必要があります。
    2. 要件に応じて、 **いずれかのシステムロール** を選択します。
    3. **[作成]**を選択します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる valid8Me サインオン URL にリダイレクトされます。
- valid8Me のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した valid8Me に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで valid8Me タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した valid8Me に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/validsign-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオンの ValidSign を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/validsign-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と ValidSign の間にシングル サインオンを構成する方法について説明します。

この記事では、ValidSign と Microsoft Entra ID を統合する方法について説明します。 ValidSign を Microsoft Entra ID と統合すると、次のことができます。

- ValidSign にアクセスできるユーザーをMicrosoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して ValidSign に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な ValidSign サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- ValidSign では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの ValidSign の追加

Microsoft Entra ID への ValidSign の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに ValidSign を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**ValidSign**」と入力します。
4. 結果パネルから **[ValidSign]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### ValidSign 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、ValidSign に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと ValidSign の関連ユーザーとの間にリンク関係を確立する必要があります。

ValidSign に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **ValidSign の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **ValidSign テストユーザーを作成する** - ValidSign において B.Simon に対応するユーザーを持つため、そのユーザーを Microsoft Entra の表示とリンクさせる。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**ValidSign**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://my.validsign.nl/sso/saml/login/alias/ValidSign?idp=<CustomerEntityID>`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[ValidSign クライアント サポート チーム](mailto:support@validsign.nl)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **保存** を選択します。
8. ValidSign アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. 上記に加えて、ValidSign アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 名字 | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### ValidSign の SSO の構成

**ValidSign** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [ValidSign サポート チーム](mailto:support@validsign.nl)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ValidSign のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを ValidSign に作成します。 [ValidSign サポート チーム](mailto:support@validsign.nl)と協力して、ValidSign プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる ValidSign サインオン URL にリダイレクトされます。
- ValidSign のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ValidSign に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [ValidSign] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した ValidSign に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vault-platform-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Vault Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vault-platform-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから Vault Platform にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、Vault Platform と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成されると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーを自動的に [Vault Platform](https://vaultplatform.com) に登録および削除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Vault Platform でユーザーを作成します。
- アクセスが不要になった場合は、Vault Platform のユーザーを削除します。
- Microsoft Entra IDと Vault Platform の間でユーザー属性の同期を維持します。
- Vault Platform に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vault-platform-tutorial)する (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Vault Platform の管理者アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとVault Platformの間でどのデータをマップするかを決定します。

### 手順 2: Microsoft Entra IDを使用したプロビジョニングをサポートするように Vault Platform を構成する

Microsoft Entra IDでのプロビジョニングをサポートするように Vault Platform を構成するには、Vault Platform サポートにお問い合わせください。

#### 1.認証

Vault Platform に移動し、メールとパスワード (初期のログイン方法) を使用してログインし、**[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) &gt; [Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) ページ**に移動します。

まず、ログイン 方法のドロップダウンを Identity Provider - Azure - SAML

SAML セットアップ手順ページの詳細を参考にして、情報を入力します。

[Image: Vault Platform [Authentication](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証) ページのスクリーンショット。]

1. [Issuer URI](発行者 URI) は `vaultplatform` に設定する必要があります
2. [Login URL](ログイン URL) に SSO URL の値を設定します[Image: SSO URL を特定するスクリーンショット。]
3. **[Certificate (Base64)](証明書 Base64)** ファイルをダウンロードしてテキスト エディターで開き、そのコンテンツ (`-----BEGIN/END CERTIFICATE-----` のマーカーを含む) をコピーして、**[Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書)** テキスト フィールドに貼り付けます[Image: [Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/証明書) のスクリーンショット。]

#### 2. データ統合

次に、Vault Platform 内の **[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) &gt; [Data Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/データ統合)** に移動します

[Image: [Data Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/データ統合) ページのスクリーンショット。]

1. **Data Integration**`Azure` を選択します。
2. **[Method of providing SCIM secret location](SCIM シークレットの場所を指定する方法)** には `bearer` を設定します。
3. **[Secret](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/シークレット)** には強力なパスワードと同様に、複雑な文字列を設定します。 この文字列は**、後で手順 5** で使用するため、セキュリティで保護してください
4. **[Set as active SCIM Provider](アクティブな SCIM プロバイダーとして設定)** をアクティブに切り替えます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーからコンテナー プラットフォームを追加する

Microsoft Entra アプリケーション ギャラリーから Vault Platform を追加して、Vault Platform へのプロビジョニングの管理を開始します。 以前に Vault Platform を SSO 用に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Vault Platform への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーやグループの割り当てに基づいて TestApp でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Vault Platform の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で、**[Vault Platform]** を選択します。

    [Image: アプリケーション一覧の Vault Platform リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. **[管理者資格情報]** セクションで、Vault Platform の [テナントの URL] (`https://app.vaultplatform.com/api/scim/${organization-slug}` の構成の URL) と [シークレット トークン] (手順 2.2 で設定した値) を入力します。 **Test Connection** を選択して、Microsoft Entra IDが Vault Platform に接続できることを確認します。 接続できない場合は、使用中の Vault Platform アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: トークンのスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Vault Platform に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新操作のために Vault Platform でユーザー アカウントを照合するために使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Vault Platform API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Vault Platform で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | 表示名 | 糸 |  |  |
    | タイトル | 糸 |  | ✓ |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | 名前.名 | 糸 |  | ✓ |
    | 名前.姓 | 糸 |  | ✓ |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  | ✓ |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | 糸 |  |  |
    | ユーザータイプ | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:組織 | 糸 |  |  |

    注

    属性 "externalID" は、オブジェクトの作成時にのみ Vault に送信され、Entra IDで変更された場合は更新されません。
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vault-platform-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Vault Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vault-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Vault Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Vault Platform と Microsoft Entra ID を統合する方法について説明します。 Vault Platform を Microsoft Entra ID を統合すると、次のことができます。

- Vault Platform にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Vault Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Vault Platform でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Vault Platform では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Vault Platform の追加

Microsoft Entra ID への Vault Platform の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Vault Platform を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Vault Platform**」と入力します。
4. 結果パネルから **[Vault Platform]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Vault Platform 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Vault Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Vault Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Vault Platform で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Vault Platform の SSO を構成**- アプリケーション側でシングルサインオン設定を構成するため。
    1. **Vault Platform のテストユーザーを作成** - Vault Platform において B.Simon の対となるユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Vault Platform**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、次の手順を実行します。

    a。 **[応答 URL]** ボックスに、`https://vaultplatform.com/api/portal/sessions/saml/<tenant-identifier>` のパターンを使用して URL を入力します

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、[Vault Platform クライアント サポート チーム](mailto:azure@vaultplatform.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Vault Platform のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Vault Platform SSO の構成

**Vault Platform** 側にシングル サインオンを構成するには、**証明書 (Base64)** を [Vault Platform サポート チーム](mailto:azure@vaultplatform.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Vault Platform のテスト ユーザーの作成

このセクションでは、Vault Platform で Britta Simon というユーザーを作成します。 [Vault Platform サポートチーム](mailto:azure@vaultplatform.com)と協力して、Vault Platform プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Vault Platform に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [コンテナー プラットフォーム] タイルを選択すると、SSO を設定した Vault Platform に自動的にサインインします。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vbrick-rev-cloud-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Vbrick Rev Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vbrick-rev-cloud-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから Vbrick Rev Cloud にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Vbrick Rev Cloud とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成時に、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Vbrick Rev Cloud](https://vbrick.com) に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Vbrick Rev Cloud でユーザーを作成します。
- アクセスが不要になったら、Vbrick Rev Cloud のユーザーを削除します。
- Microsoft Entra IDと Vbrick Rev Cloud の間でユーザー属性の同期を維持します。
- Vbrick Rev Cloud でグループとグループ メンバーシップをプロビジョニングします。
- Vbrick Rev Cloud への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vbrick-rev-cloud-tutorial) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Vbrick Rev Cloud テナント。
- 管理アクセス許可を持つ Vbrick Rev Cloud のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとVbrick Rev Cloudの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Vbrick Rev Cloud を構成する

1. **Rev テナント**にサインインする。 ナビゲーション ウィンドウで、**[管理] &gt; [セキュリティ設定] &gt; [ユーザー セキュリティ]** の順に移動します。

    [Image: Vbrick Rev ユーザー セキュリティ設定のスクリーンショット。]
2. ページの **Microsoft Entra SCIM** セクションに移動します。

    Vbrick Rev ユーザーセキュリティ設定のスクリーンショットで、Microsoft AD SCIMセクションが強調表示されています。
3. **Microsoft Entra SCIM** を有効にし、**Generate Token** ボタンを選択します。 [Image: Microsoft AD SCIM が有効化された Vbrick Rev ユーザー セキュリティ設定のスクリーンショット]
4. **URL** と **JWT トークン**を含むポップアップが開きます。 次の手順のために **JWT トークン**と **URL** をコピーして保存します。

    [Image: SCIM トークン セクションが強調表示された、Vbrick Rev ユーザー セキュリティ設定のスクリーンショット。]
5. **JWT トークン**と **URL** のコピーを作成したら、[**OK] を**選択してポップアップを閉じ、設定ページの下部にある **[保存**] ボタンを選択して、テナントの SCIM を有効にします。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Vbrick Rev Cloud を追加する

Microsoft Entra アプリケーション ギャラリーから Vbrick Rev Cloud を追加して、Vbrick Rev Cloud へのプロビジョニングの管理を開始します。 Vbrick Rev Cloud for SSO を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Vbrick Rev Cloud への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Vbrick Rev Cloud の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Vbrick Rev Cloud]** を選択します。

    [Image: アプリケーション一覧内の Vbrick Rev Cloud のリンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Vbrick Rev Cloud テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Vbrick Rev Cloud に接続できることを確認します。 接続に失敗した場合は、Vbrick Rev Cloud アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Vbrick Rev Cloud に同期されるユーザー属性を確認します。 **照合**プロパティとして選択されている属性は、更新処理で Vbrick Rev Cloud のユーザー アカウントの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Vbrick Rev Cloud API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Vbrick Rev Cloud で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | タイトル | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  |  |
    | 名前.名 | 糸 |  |  |
    | 名前.姓 | 糸 |  | ✓ |
    | 名前.整形済み | 糸 |  |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |  |
    | エクスターナルID | 糸 |  | ✓ |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Vbrick Rev Cloud に同期されるグループ属性を確認します。 **照合**プロパティとして選択されている属性は、更新処理で Vbrick Rev Cloud のグループの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Vbrick Rev Cloud で必須 |
    | --- | --- | --- | --- |
    | 表示名 | 糸 | ✓ | ✓ |
    | エクスターナルID | 糸 |  | ✓ |
    | メンバー | リファレンス |  |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vbrick-rev-cloud-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Vbrick Rev Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vbrick-rev-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Vbrick Rev Cloud の間のシングル サインオンを構成する方法について説明します。

この記事では、Vbrick Rev Cloud と Microsoft Entra ID を統合する方法について説明します。 Rev エンタープライズ ビデオ プラットフォームは、ライブビデオおよびオンデマンド ビデオをキャプチャ、管理、配布するためのソリューションです。 このソリューションは、組織がライブ ビデオに関するクリティカルなニーズに対応し、オンデマンド ビデオを革新的に活用するために役立ちます。 Vbrick Rev Cloud を Microsoft Entra ID と統合すると、次のことができます。

- Vbrick Rev Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Vbrick Rev Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Vbrick Rev Cloud に対する Microsoft Entra シングル サインオンを構成してテストします。 Vbrick Rev Cloud では、 **SP** によって開始されるシングル サインオンと [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vbrick-rev-cloud-provisioning-tutorial)。

### [前提条件]

Microsoft Entra ID を Vbrick Rev Cloud と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Vbrick Rev Cloud のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Vbrick Rev Cloud アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Vbrick Rev Cloud を追加する

Microsoft Entra アプリケーション ギャラリーから Vbrick Rev Cloud を追加して、Vbrick Rev Cloud でのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Vbrick Rev Cloud**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.domain.extension:443` |
    | `https://<CustomerName>.au.vbrickrev.com:443` |
    | `https://<CustomerName>.eu.vbrickrev.com:443` |
    | `https://<CustomerName>.rev.vbrick.com:443` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerName>.rev.vbrick.com:443/sso/consume` |
    | `https://<CustomerName>.eu.vbrickrev.com:443/sso/consume` |
    | `https://<CustomerName>.au.vbrickrev.com:443/sso/consume` |
    | `https://<CustomerName>.domain.extension:443/sso/consume` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerName>.rev.vbrick.com` |
    | `https://<CustomerName>.eu.vbrickrev.com` |
    | `https://<CustomerName>.au.vbrickrev.com` |
    | `https://<CustomerName>.domain.extension` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Vbrick Rev Cloud サポート チーム](mailto:support@vbrick.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Vbrick Rev Cloud のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Vbrick Rev Cloud を構成する

1. Vbrick Rev Cloud 企業サイトに管理者としてサインインします。
2. **システム設定**&gt;Security に移動**します**。
3. **[SAML SINGLE SIGN ON**] セクションで、次の手順を実行します。

    [Image: 管理ポータルを示すスクリーンショット。]

    1. [ **シングル サインオンを有効にする]** チェック ボックスをオンにします。
    2. **[IDENTITY Provider Metadata]\(ID プロバイダー メタデータ**\) ボックスに、前にコピーした**フェデレーション メタデータ XML** ファイルを貼り付けます。
    3. **署名アルゴリズム**の場合は、ドロップダウン リストから **SHA256WithRSA** を選択します。
    4. [ **SAML 要求の署名** ] チェック ボックスをオンのままにして、[保存] を選択 **します**。

    注

    詳細については、 [この](https://revdocs.vbrick.com/docs/configure-single-sign-on-sso) Vbrick Rev ドキュメントを参照してください。

#### Vbrick Rev Cloud のテスト ユーザーの作成

このセクションでは、Vbrick Rev で B.Simon というユーザーを作成します。 [この](https://revdocs.vbrick.com/docs/user-accounts#add-or-edit-a-user) ガイドに従ってテスト ユーザーを作成してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Vbrick Rev Cloud のサインオン URL にリダイレクトされます。
- Vbrick Rev Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Vbrick Rev Cloud] タイルを選択すると、このオプションは Vbrick Rev Cloud のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vecos-releezme-locker-management-system-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に VECOS Releezme Locker 管理システムを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vecos-releezme-locker-management-system-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と VECOS Releezme Locker 管理システムの間でシングル サインオンを構成する方法について説明します。

この記事では、VECOS Releezme Locker 管理システムと Microsoft Entra ID を統合する方法について説明します。 VECOS Releezme Locker 管理システムを Microsoft Entra ID と統合すると、次のことができます。

- VECOS Releezme Locker 管理システムにアクセスできるユーザーを Microsoft Entra ID で制御する。 VECOS Releezme Locker Management System へのアクセスが必要なのは、保管ボックスを管理する必要があるユーザー (施設管理者、サービス デスクの従業員など) のみです。
- ユーザーが自分の Microsoft Entra アカウントを使用して VECOS Releezme Locker 管理システムに自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- VECOS Releezme Locker management system のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- VECOS Releezme Locker management system では、**SP** によって開始される SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの VECOS Releezme Locker management system の追加

Microsoft Entra ID への VECOS Releezme Locker 管理システムの統合を構成するには、マネージド SaaS アプリの一覧にギャラリーから VECOS Releezme Locker 管理システムを追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**VECOS Releezme Locker management system**」と入力します。
4. 結果のパネルから **[VECOS Releezme Locker management system]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### VECOS Releezme Locker 管理システムの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、VECOS Releezme Locker 管理システムに対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと VECOS Releezme Locker 管理システムの関連ユーザーとの間にリンク関係を確立する必要があります。

VECOS Releezme Locker 管理システムに対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **VECOS Releezme Locker management system SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **VECOS Releezme Locker 管理システムのテストユーザーを作成する** - VECOS Releezme Locker 管理システムにおいて、Microsoft Entra での B.Simon の表現とリンクするユーザーのカウンターパートを設けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**VECOS Releezme Locker 管理システム**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<baseURL>/`

    b。 **[応答 URL]** ボックスに、`https://<baseURL>/Saml2/Acs` のパターンを使用して URL を入力します。

    c. **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<baseURL>/sso` (必要に応じて、VECOS によって指定された会社コード値を含む `?companycode=` クエリ パラメーターを追加します)。

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 [接続先のリージョンについては、VECOS Releezme Locker 管理システム サポート チーム](mailto:servicedesk@vecos.com)にお問い合わせください。 お使いのリージョンによって、URL は次のように異なります。

    | **[リージョン]** | **baseURL** |
    | --- | --- |
    | ヨーロッパ | `https://www.releezme.net` |
    | 北米 | `https://na.releezme.net` |
    | アジア太平洋 | `https://au.releezme.net` |
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]

### VECOS Releezme Locker management system のロールの構成

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**App 登録**に移動し、[**すべてのアプリケーション**] を選択します。
3. アプリの登録一覧で **[VECOS Releezme Locker management system]** を選択します。
4. アプリの登録で、 **[アプリのロール]** を開きます。
5. [アプリ ロール] ページで、[アプリ ロールの作成] を選択して新しい**アプリ ロールを作成**します
6. **[表示名]** フィールドに、ロールの名前を入力します (例: `VECOS Company Facility Manager`)。
7. **[Allowed member types](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/許可されるメンバーの種類)** の値として、 **[ユーザー/グループ]** を選択します。
8. **[値]** フィールドに VECOS Releezme Locker management system のロール名を入力します。 次の表を参照してください。
9. **を選択して**を適用します。

| 役割 | ロールの値 | 説明 |
| --- | --- | --- |
| サービス デスク | 企業サポートデスク | アクセスが制限されているサービス デスク。 ほとんどの場合、読み取り専用アクセス |
| サービス デスク + | CompanyServiceDeskPlus | より多くの読み取りおよび書き込みアクセス権を持つ、サービス デスクの上位バージョン |
| 施設管理者 | 企業施設管理者 | 会社の設備にアクセスできる設備管理者 |
| 施設管理者 + | カンパニーファシリティマネージャープラス | 社内で追加のアクセス権を持つ上位の設備管理者。 |
| 管理者 | 会社管理者 | 会社への完全なアクセス権を持つ管理者 |

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### VECOS Releezme Locker management system SSO の構成

**VECOS Releezme Locker management system** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [VECOS Releezme Locker management system サポート チーム](mailto:servicedesk@vecos.com)に送る必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### VECOS Releezme Locker management system のテスト ユーザーの作成

このセクションでは、VECOS Releezme Locker management system で Britta Simon というユーザーを作成します。 [VECOS Releezme Locker management system サポート チーム](mailto:servicedesk@vecos.com)と連携し、VECOS Releezme Locker management system プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる VECOS Releezme Locker 管理システムのサインオン URL にリダイレクトされます。
- VECOS Releezme Locker management system のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで VECOS Releezme Locker 管理システム タイルを選択すると、このオプションは VECOS Releezme Locker 管理システムのサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/veda-cloud-tutorial"} -->
## Microsoft Entra ID で VEDA Cloud for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/veda-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と VEDA Cloud 間のシングル サインオンを構成する方法について説明します。

この記事では、VEDA Cloud を Microsoft Entra ID と統合する方法について説明します。 このアプリケーションにより、VEDA HR Cloud Solutions へのユーザー認証において Microsoft Entra ID が SAML IdP として機能できます。 VEDA Cloud を Microsoft Entra ID と統合すると、次のことが可能になります。

- VEDA Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで VEDA Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

VEDA Cloud に対する Microsoft Entra シングル サインオンをテスト環境で構成・テストする。 VEDA Cloud は **SP** 開始のシングル サインオンをサポートしています。

### [前提条件]

Microsoft Entra ID を VEDA Cloud と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- VEDA Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから VEDA Cloud アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから VEDA Cloud を追加する

Microsoft Entra アプリケーション ギャラリーから VEDA Cloud を追加して、VEDA Cloud に対するシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**VEDA Cloud**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://vedacustomers.b2clogin.com/<CUSTOMER>/B2C_1A_VEDASIGNIN` |
    | `https://vedacustomersprod.b2clogin.com/<CUSTOMER>/B2C_1A_VEDASIGNIN` |
    | `https://login.veda.net/<ID>/B2C_1A_VEDASIGNIN` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://vedacustomers.b2clogin.com/<CUSTOMER>/B2C_1A_VEDASIGNIN/samlp/sso/assertionconsumer` |
    | `https://vedacustomersprod.b2clogin.com/<CUSTOMER>/B2C_1A_VEDASIGNIN/samlp/sso/assertionconsumer` |
    | `https://login.veda.net/<ID>/B2C_1A_VEDASIGNIN/samlp/sso/assertionconsumer` |

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<DOMAIN>.veda.net/<INSTANCE>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[VEDA Cloud クライアント サポート チーム](mailto:peoplemanagement@veda.net)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. VEDA Cloud アプリケーションでは、特定の形式の SAML アサーションを受け取るため、SAML トークン属性の構成にカスタム属性マッピングを追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、VEDA Cloud アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | displayName | user.displayname |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### VEDA Cloud SSO の構成

**VEDA Cloud** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [VEDA Cloud サポート チーム](mailto:peoplemanagement@veda.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### VEDA Cloud テスト ユーザーを作成する

このセクションでは、VEDA Cloud で Britta Simon というユーザーを作成します。 [VEDA Cloud サポート チーム](mailto:peoplemanagement@veda.net)と連携して、VEDA Cloud プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる VEDA Cloud のサインオン URL にリダイレクトされます。
- VEDA Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [VEDA Cloud] タイルを選択すると、このオプションは VEDA Cloud のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/velpic-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Velpic の構成を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/velpic-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Velpic にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Microsoft Entra IDを利用して、Microsoft Entra IDからVelpicにユーザーアカウントを自動的にプロビジョニングおよび解除するために、VelpicとMicrosoft Entra IDで実行する必要のある手順を示すことです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Enterprise プラン以上の有効な Velpic テナント
- 管理者アクセス許可がある Velpic のユーザー アカウント

### 手順 1: Velpic にユーザーを割り当てる

Microsoft Entra IDでは、"割り当て" という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに "割り当てられている" ユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、Microsoft Entra ID内でVelpicアプリにアクセスする必要があるユーザーやグループを決定する必要があります。 決定したら、次の手順に従って、それらのユーザーを Velpic アプリに割り当てることができます。

[エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Velpic に割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、1 人のMicrosoft Entra ユーザーを Velpic に割り当てることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Velpic にユーザーを割り当てるときは、割り当てダイアログで**ユーザー** ロールまたは別の有効なアプリケーション固有ロール (使用可能な場合) を選択する必要があります。 **[既定のアクセス]** ロールはプロビジョニングでは使うことができず、このロールのユーザーはスキップされます。

### 手順 2: Velpic へのユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDを Velpic のユーザー アカウント プロビジョニング API に接続し、プロビジョニング サービスを構成して、Microsoft Entra IDのユーザーとグループの割り当てに基づいて Velpic で割り当てられたユーザー アカウントを作成、更新、無効化する方法について説明します。

ヒント

また、[Azure ポータル](https://portal.azure.com)に記載されている手順に従って、Velpic に対して SAML ベースのシングル サインオンを有効にすることもできます。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Velpic への自動ユーザー アカウント プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動します。
3. シングル サインオンのために Velpic を既に構成している場合は、検索フィールドで Velpic のインスタンスを検索します。 構成していない場合は、**[追加]** を選択し、アプリケーション ギャラリーで **Velpic** を検索します。 検索結果から Velpic を選択し、アプリケーションの一覧に追加します。
4. アプリケーションの一覧で **Velpic** を選択します。

    [Image: アプリケーションの一覧の Velpic リンクのスクリーンショット。]
5. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
6. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
7. [**管理者資格情報**] セクションで、Velpic のテナント URL とシークレット トークンを入力します。(Velpic アカウントの [**管理**] でこれらの値を確認できます&gt;**統合**&gt;**プラグイン**&gt;**SCIM**)

    [Image: 承認値のスクリーンショット。]
8. **Test Connection** を選択して、Microsoft Entra ID が Velpic アプリに接続できることを確認します。 接続できない場合は、お使いの Velpic アカウントに管理者アクセス許可があることを確認してから、手順 5. をもう一度試します。
9. [ **作成]** を選択して構成を作成します。
10. [**概要**] ページで **[プロパティ**] を選択します。
11. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
12. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
13. Microsoft Entra IDから Velpic に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Velpic のユーザー アカウントとの照合に使用されます。 [保存] ボタンをクリックして変更をコミットします。
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 3: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/velpicsaml-tutorial"} -->
## Microsoft Entra ID で Velpic SAML for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/velpicsaml-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Velpic SAML の間にシングル サインオンを構成する方法について説明します。

この記事では、Velpic SAML と Microsoft Entra ID を統合する方法について説明します。 Velpic SAML を Microsoft Entra ID を統合すると、次のことができます。

- Velpic SAML にアクセスできるユーザー Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Velpic SAML に自動的にサインインするように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Velpic SAML でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Velpic SAML では、**SP** Initiated SSO がサポートされます。
- Velpic SAML では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/velpic-provisioning-tutorial)がサポートされます。

### ギャラリーからの Velpic SAML の追加

Microsoft Entra ID への Velpic SAML の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Velpic SAML を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Velpic SAML**」と入力します。
4. 結果のパネルから **Velpic SAML** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Velpic SAML 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Velpic SAML に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Velpic SAML の関連ユーザーとの間にリンク関係を確立する必要があります。

Velpic SAML に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Velpic SAML SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Velpic SAML テストユーザーを作成** - Microsoft Entra でのユーザー表現である B.Simon にリンクする Velpic SAML の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Velpic SAML**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<sub-domain>.velpicsaml.net`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.velpic.com/saml/v2/<entity-id>/login`

    注

    サインオン URL は Velpic SAML チームによって提供され、識別子の値は Velpic SAML 側で SSO プラグインを構成するときに使用できます。 Velpic SAML アプリケーションのページからその値をコピーして、ここに貼り付ける必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Velpic SAML のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Velpic SAML SSO の構成

1. 別の Web ブラウザーのウィンドウで、Velpic SAML 企業サイトに管理者としてサインインします
2. [ **管理** ] タブを選択し、[ **統合** ] セクションに移動します。ここで、[ **プラグイン** ] ボタンを選択してサインイン用の新しいプラグインを作成する必要があります。

    [Image: [Integration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) ページを示すスクリーンショット。ここで、[Plugins](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プラグイン) を選択できます。]
3. [ **プラグインの追加]** ボタンを選択します。

    [Image: [Add Plugin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プラグインの追加) ボタンが選択されていることを示すスクリーンショット。]
4. [プラグインの追加] ページで **[SAML** ] タイルを選択します。

    [Image: [Add plugin] (プラグインの追加) ページで [SAML] が選択されていることを示すスクリーンショット。]
5. 新しい SAML プラグインの名前を入力し、[ **追加** ] ボタンを選択します。

    [Image: 「Microsoft Entra ID」と入力された [Add new SAML plugin] (新しい SAML プラグインの追加) ダイアログ ボックスを示すスクリーンショット。]
6. 詳細を次のように入力します。

    [Image: 説明されている値を入力できる [Microsoft Entra ID] ページを示すスクリーンショット。]

    ある。 **[名前]** テキストボックスに、SAML プラグインの名前を入力します。

    b。 **[発行者 URL]** テキストボックスに、**[サインオンの構成]** ウィンドウからコピーした **Microsoft Entra 識別子**を貼り付けます。

    c. **[Provider Metadata Config](Provider メタデータ構成)** で、以前ダウンロードしたメタデータ XML ファイルをアップロードします。

    d. **Auto create new users \(新規ユーザーの自動作成)** チェックボックスをオンにして、SAML のジャストインタイム プロビジョニングを有効にすることもできます。 Velpic にユーザーが存在せず、このフラグが有効になっていない場合、Azure からのログインは失敗します。 このチェックボックスがオンになっている場合、ユーザーは、ログイン時に Velpic に自動的にプロビジョニングされます。

    え テキストボックスから**シングル サインオン URL** をコピーして、貼り付けます。

    f. **保存** を選択します。

#### Velpic SAML テスト ユーザーの作成

アプリケーションは、ジャスト イン タイムのユーザー プロビジョニングをサポートしているため、通常、この手順は必要ありません。 自動ユーザー プロビジョニングが有効になっていない場合は、以下の説明に従って手動でユーザーを作成できます。

Velpic SAML 企業サイトに管理者としてサインインし、次の手順に従います。

1. [管理] タブを選択し、[ユーザー] セクションに移動し、[新規] ボタンを選択してユーザーを追加します。

    [Image: ユーザーの追加r]
2. **[Create New User] \(新しいユーザーの作成)** ダイアログ ページで、次の手順を実行します。

    [Image: 利用者]

    ある。 **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名 B を入力します。

    b。 **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓 Simon を入力します。

    c. **[User Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名)** テキストボックスに、B.Simon のユーザーを入力します。

    d. **[Email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電子メール)** ボックスに、B.Simon@contoso.com アカウントのメール アドレスを入力します。

    え その他の情報は省略可能です。必要に応じて入力してください。

    f. **[保存] を選択します**。

注

Velpic SAML では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/velpic-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、マイ アプリを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. マイ アプリで Velpic SAML タイルを選択すると、Velpic SAML アプリケーションのログイン ページが表示されます。 サインイン ページに **[Log In With Microsoft Entra ID] (Microsoft Entra ID でログイン)** ボタンが表示されます。

    [Image: [Log In With Microsoft Entra ID] (Microsoft Entra ID でログイン) が選択されているラーニング ポータルを示すスクリーンショット。]
2. Microsoft Entra アカウントを使用して Velpic にログインするには、[ **Microsoft Entra ID** でログイン] ボタンを選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/venafi-control-plane-tutorial"} -->
## Venafi Control Plane を Microsoft Entra ID を使用したシングルサインオンのためのデータセンターとして構成する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/venafi-control-plane-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Venafi Control Plane - Datacenter の間でシングル サインオンを構成する方法について説明します。

この記事では、Venafi コントロール プレーン - Datacenter と Microsoft Entra ID を統合する方法について説明します。 Venafi Control Plane には、TLS Protect Datacenter、SSH Protect、CodeSign Protect が含まれます。 Microsoft Entra ID と Venafi Control Plane - Datacenter を統合すると、次のことができます。

- Venafi Control Plane - Datacenter にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Venafi Control Plane - Datacenter に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Venafi Control Plane - Datacenter 向けの Microsoft Entra のシングル サインオンを構成してテストします。 Venafi Control Plane - Datacenter では、**SP** Initiated と **IDP** Initiated の両方のシングル サインオンがサポートされています。

### [前提条件]

Microsoft Entra ID と Venafi Control Plane - Datacenter を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Venafi Control Plane - Datacenter でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Venafi Control Plane - Datacenter アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Venafi Control Plane - Datacenter を追加する

Microsoft Entra アプリケーション ギャラリーから Venafi Control Plane - Datacenter を追加して、Venafi Control Plane - Datacenter でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Venafi Control Plane - Datacenter**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER-DOMAIN>/aperture/api/saml/acs`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER-DOMAIN>/aperture/api/saml/acs`
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER-DOMAIN>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Venafi Control Plane - Datacenter クライアント サポート チーム](mailto:support@venafi.com) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Venafi Control Plane - Datacenter SSO の構成

**Venafi Control Plane - Datacenter** 側でシングル サインオンを構成するには、**アプリ フェデレーション メタデータ URL** を [Venafi Control Plane - Datacenter サポート チーム](mailto:support@venafi.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Venafi Control Plane - Datacenter テスト ユーザーの作成

このセクションでは、Venafi Control Plane - Datacenter で Britta Simon というユーザーを作成します。 [Venafi Control Plane - Datacenter サポート チーム](mailto:Vsupport@venafi.com)と連携して、Venafi Control Plane - Datacenter プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Venafi コントロール プレーン - Datacenter のサインオン URL にリダイレクトされます。
- Venafi Control Plane - Datacenter のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Venafi コントロール プレーン - Datacenter に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Venafi コントロール プレーン - データセンター] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Venafi Control Plane - Datacenter に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vera-suite-tutorial"} -->
## Microsoft Entra ID で Vera Suite for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vera-suite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Vera Suite の間でシングル サインオンを構成する方法について説明します。

この記事では、Vera Suite と Microsoft Entra ID を統合する方法について説明します。 Vera Suite は、自動車ディーラーの安全の文化の維持、業務の効率化、リスク管理を支援します。 Vera Suite は、EHS、HR、F&I マネージャー向けのディーラーの従業員と職場のコンプライアンス ソリューションを提供しています。 Vera Suite を Microsoft Entra ID と統合すると、次のことができます。

- Vera Suite にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Vera Suite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Vera Suite 向けの Microsoft Entra シングル サインオンの構成とテストはテスト環境で実行します。 Vera Suite では、**SP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID と Vera Suite を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Vera Suite でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Vera Suite アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Vera Suite を追加する

Microsoft Entra アプリケーション ギャラリーから Vera Suite を追加して、Vera Suite でのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Vera Suite**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
5. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Vera Suite SSO の構成

**Vera Suite** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Vera Suite サポート チーム](mailto:support@kpa.io)に送る必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Vera Suite テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Vera Suite に作成します。 Vera Suite では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Vera Suite にユーザーがまだ存在していない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Vera Suite のサインオン URL にリダイレクトされます。
- Vera Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Vera Suite] タイルを選択すると、このオプションは Vera Suite のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/veracode-tutorial"} -->
## Microsoft Entra ID で Veracode for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/veracode-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Veracode の間にシングル サインオンを構成する方法について説明します。

この記事では、Veracode と Microsoft Entra ID を統合する方法について説明します。 Veracode を Microsoft Entra ID と統合すると、次のことができます。

- Veracode にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Veracode に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Veracode でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。 Veracode では、ID プロバイダーが開始する SSO のほか、Just In Time ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Veracode の追加

Microsoft Entra ID への Veracode の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Veracode を追加します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「Veracode」と入力します。
4. 結果のパネルから **Veracode** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Veracode 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Veracode に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Veracode の関連ユーザーとの間にリンクを確立する必要があります。

Veracode に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーを作成**し、B.Simon を使用して Microsoft Entra シングル サインオンをテストします。
    - **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **Veracode SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Veracode テスト ユーザーを作成する** - Veracode で B.Simon に対応するユーザーを作成し、Microsoft Entra のこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. Microsoft Entra ID で、[**エンタープライズ** アプリケーション] の **[Veracode** アプリケーション] ページに移動し、[**管理**] セクションまで下にスクロールして、**シングル サインオン**を選択します。
2. [ **管理** ] タブで[ **シングル サインオン**] を選択し、[ **SAML**] を選択します。
3. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
4. [リレー状態] フィールドには `https://web.analysiscenter.veracode.com/login/#/saml` が自動設定されます。 これらのその他のフィールドには、Veracode Platform 内で SAML を設定した後に設定されます。
5. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけます。 **[ダウンロード]** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [ダウンロード] リンクが強調された [SAML 署名証明書] セクションのスクリーンショット。]
6. Veracode では、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: [ユーザー属性とクレーム] セクションのスクリーンショット。]
7. Veracode は、いくつかの追加の属性が SAML 応答で返されることも予期しています。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.名 |
    | 名字 | User.姓 |
    | メール | ユーザー.メール |
8. **[Set up Veracode] (Veracode の設定)** セクションに表示された URL をコピーして保存します。これは後で Veracode Platform の SAML 設定で使います。

    [Image: 構成 URL が強調された [Veracode のセットアップ] セクションのスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Veracode の SSO の構成

メモ:

- これらの手順では、Veracode の新しい [シングル サインオン/Just-In-Time プロビジョニング機能を](https://docs.veracode.com/r/about_saml)使用していることを前提としています。 この機能がまだアクティブでない場合は、Veracode サポートにお問い合わせください。
- これらの手順は、すべての [Veracode リージョン](https://docs.veracode.com/r/Region_Domains_for_Veracode_APIs)で有効です。

1. 別の Web ブラウザーのウィンドウで、Veracode 企業サイトに管理者としてサインインします。
2. 上部のメニューから **[設定]**&gt;**[Admin] (管理)** を選択します。

    [Image: [Settings] (設定) アイコンと [Admin] (管理) が強調されている Veracode の [Administration] (管理) のスクリーンショット。]
3. **[SAML Certificate] (SAML 証明書)** タブを選びます。
4. **[SAML Certificate] (SAML 証明書)** セクションで、次の手順を実行します。

    [Image: [Organization SAML Settings] (組織の SAML 設定) セクションのスクリーンショット。]

    ある。 **[発行者]** に、コピーした **Microsoft Entra 識別子**の値を貼り付けます。

    b。 **[アサーション署名証明書]** で **[ファイルの選択]** を選択し、ダウンロードした証明書をアップロードします。

    c. 3 つの URL (**[SAML Assertion URL] (SAML アサーション URL)**、**[SAML Audience URL] (SAML 対象ユーザー URL)**、**[Relay state URL] (リレー状態 URL)**) の値を記録しておきます。

    d. **保存** を選択します。
5. **[SAML Assertion URL] (SAML アサーション URL)**、**[SAML Audience URL] (SAML 対象ユーザー URL)**、**[Relay state URL] (リレー状態 URL)** の値を取得し、Veracode 統合の Microsoft Entra 設定で更新します (以下の表に従って適切に変換します)。注: **[Relay State] (リレー状態)** は省略可能では "ありません"。

    | Veracode URL | Microsoft Entra ID フィールド |
    | --- | --- |
    | SAML Audience URL (SAML 対象ユーザー URL) | 識別子 (エンティティ ID) |
    | SAML Assertion URL (SAML アサーション URL) | 応答 URL (Assertion Consumer Service URL) |
    | Relay State URL (リレー状態 URL) | リレー状態 |
6. **[JIT Provisioning] (JIT プロビジョニング)** タブを選びます。

    [Image: さまざまなオプションが強調されている [JIT Provisioning] (JIT プロビジョニング) タブのスクリーンショット。]
7. **[Organization Settings] (組織の設定)** セクションで、**[Configure Default Settings for Just-in-Time user provisioning] (Just-In-Time ユーザー プロビジョニングの既定の設定を構成する)** の設定を **[オン]** に切り替えます。
8. **[Basic Settings] (基本設定)** セクションの **[User Data Updates] (ユーザー データの更新)** で、**[Prefer Veracode User Data] (Veracode のユーザー データを優先する)** を選びます。 これにより、Microsoft Entra ID から SAML アサーションで渡されるデータと、Veracode プラットフォームのユーザー データとの間に競合が発生し、Veracode ユーザー データを使って解決されます。
9. **[Access Settings] (アクセスの設定)** セクションの **[User Roles] (ユーザー ロール)** で、以下から選びます。Veracode のユーザー ロールについて詳しくは、[Veracode のドキュメント](https://docs.veracode.com/r/c_role_permissions)をご覧ください。

    [Image: さまざまなオプションが強調されている JIT プロビジョニングのユーザー ロールのスクリーンショット。]

    - **ポリシー管理者**
    - **レビュー担当者**
    - **セキュリティ リーダー**
    - **役員**
    - **申請者**
    - **造物主**
    - **すべてのスキャンの種類**

#### Veracode テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Veracode に作成します。 Veracode では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションでは、ユーザー側で必要な操作はありません。 Veracode にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

Veracode から提供されている他の Veracode ユーザー アカウント作成ツールまたは API を使って、Microsoft Entra ユーザー アカウントをプロビジョニングすることもできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Veracode に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Veracode] タイルを選択すると、SSO を設定した Veracode に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/verasmart-tutorial"} -->
## Microsoft Entra ID で VeraSMART for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/verasmart-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と VeraSMART 間のシングル サインオンを構成する方法について説明します。

この記事では、VeraSMART と Microsoft Entra ID を統合する方法について説明します。 VeraSMART を Microsoft Entra ID と統合すると、次のことが可能になります。

- VeraSMART にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで VeraSMART に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- VeraSMART でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- VeraSMART は、**SP 主導の SSO および IDP 主導の SSO** をサポートしています。
- VeraSMART では、 **Just In Time** ユーザー プロビジョニングがサポートされます
- VeraSMART を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用できます。 セッション制御は条件付きアクセスから拡張されます。 [Microsoft Defender for Cloud Apps でセッション制御を適用する方法について説明します](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)。

### ギャラリーからの VeraSMART の追加

Microsoft Entra ID への VeraSMART の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に VeraSMART を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「VeraSMART**」と入力します。
4. 結果パネルから **VeraSMART** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### VeraSMART に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、VeraSMART に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと VeraSMART の関連ユーザー間にリンク関係を確立する必要があります。

VeraSMART との Microsoft Entra SSO を構成・テストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **VeraSMART SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **VeraSMART テストユーザーを作成する** - VeraSMART において B.Simon と対応するユーザーを作成し、それを Microsoft Entra の B.Simon とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**VeraSMART**&gt;**シングルサインオン**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.calero.com/<DOMAIN_NAME>/VeraSMART`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.calero.com/<DOMAIN_NAME>/VeraSMART/Saml2/Acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.calero.com/<DOMAIN_NAME>/VeraSMART/SSO`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、VeraSMART クライアント サポート チーム](mailto:support@calero.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### VeraSMART SSO の構成

1. 管理者として VeraSMART にログインします。
2. **管理**&gt;**セキュリティ**&gt;**認証構成**に移動します。

    [Image: [Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理)、[Security]、[Authentication Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証構成) が選択されている VeraSMART を示すスクリーンショット。]
3. 次のページで、以下の手順を実行します。

    [Image: 構成]

    a. ドロップダウンから [**SAML2 as Single sign-on method]\(シングル サインオン方法**として **SAML2**\) を選択します。

    b。 [ **メタデータの場所** ] ボックスに、メタデータ ファイルの URL を入力します。

    c. **IDPメタデータの処理を選択します**。

    注

    または、[ファイルの**選択**] オプションを選択して**メタデータ** ファイルをアップロードすることもできます。

    d. **[エンティティ ID**] ドロップダウンから [エンティティ ID] の値を選択します。

    e. **[保存] を選択します**。

#### VeraSMART テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを VeraSMART に作成します。 VeraSMART では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 VeraSMART にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [VeraSMART] タイルを選択すると、SSO を設定した VeraSMART に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vergesense-tutorial"} -->
## Microsoft Entra ID で VergeSense for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vergesense-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と VergeSense の間でシングル サインオンを構成する方法について説明します。

この記事では、VergeSense と Microsoft Entra ID を統合する方法について説明します。 VergeSense と Microsoft Entra ID を統合すると、次のことができます。

- VergeSense にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して VergeSense に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- VergeSense でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- VergeSense は、**SP と** IDP による SSO の両方に対応しています。

### ギャラリーからの VergeSense の追加

Microsoft Entra ID への VergeSense の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に VergeSense を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**VergeSense**」と入力します。
4. 結果パネルから VergeSense  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### VergeSense の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、VergeSense に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと VergeSense の関連ユーザーとの間にリンク関係を確立する必要があります。

VergeSense に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **VergeSense SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **VergeSense テスト ユーザーの作成** - Contentful で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクします。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**VergeSense**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [**サインオン URL** テキスト ボックスに、URL: `https://cloud.vergesense.com` を入力します。
7. VergeSense アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: image]
8. 上記に加えて、VergeSense アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | User.mail |
9. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Set up VergeSense](VergeSense の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### VergeSense SSO の構成

VergeSense **側** シングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を VergeSense サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### VergeSense テスト ユーザーの作成

このセクションでは、VergeSense で Britta Simon というユーザーを作成します。 VergeSense サポート チーム  と連携して、VergeSense プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる VergeSense サインオン URL にリダイレクトされます。
- VergeSense のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した VergeSense に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [VergeSense] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した VergeSense に自動的にサインインされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/verity-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Verity を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/verity-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra ID と Verity の間のシングル サインオンを構成する方法について説明します。

この記事では、Verity と Microsoft Entra ID を統合する方法について説明します。 Verity と Microsoft Entra ID を統合すると、次のことができます。

- Verity にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Verity に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Verity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Verity では、**SP および IDP** Initiated SSO がサポートされています。
- Verity では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Verity を追加する

Microsoft Entra ID への Verity の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Verity を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Verity**」と入力します。
4. 結果パネルから **[Verity]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Verity 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Verity に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Verity の関連ユーザーとの間にリンク関係を確立する必要があります。

Verity に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Verity SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Verity テスト ユーザーの作成** - Microsoft Entra ID に存在する B.Simon に対応するよう、Verity にリンクされたユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Verity**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`<VERITY_CLIENTDOMAIN_ID>` の形式で値を入力します。

    b。 **[応答 URL]** ボックスに、`https://<VERITY_CLIENTDOMAIN_REPLY_URL>` のパターンを使用して URL を入力します
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** ボックスに、次のパターンを使用して URL を入力します。`https://<VERITY_CLIENTDOMAIN_SIGNON_URL>`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Verity サポート チーム](mailto:support@verity.net)に連絡してください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. Verity アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の画像を示すスクリーンショット。]

    注意

    アプリケーションの要件に従って、Entra の上記のすべての既定の属性の名前空間を手動で削除してください。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Verity SSO を構成する

**Verity** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Verity サポート チーム](mailto:support@verity.net)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Verity テスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを Verity に作成します。 Verity では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Verity にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Verity のサインオン URL にリダイレクトします。
- Verity のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Verity に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Verity] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Verity に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/verizon-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Verizon プロビジョニングを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/verizon-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-20
- Summary: Microsoft Entra IDから Verizon Provisioning にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Verizon ユーザー プロビジョニングとMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [Verizon User Provisioning](https://www.verizon.com) に対して自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Verizon Provisioning でユーザーを作成する
- アクセスが不要になった場合に Verizon Provisioning のユーザーを削除する
- Microsoft Entra ID と Verizon プロビジョニングの間でユーザー属性の同期を維持する
- Verizon でグループとグループ メンバーシップをプロビジョニングします。
- Verizon では、クライアント資格情報認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Verizon User Provisioning のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントの計画を立てる

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- [Microsoft Entra ID と Verizon User Provisioning の間でマッピングする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Verizon プロビジョニングを構成する

1. 管理者の資格情報を使用して Verizon On Site Network ダッシュボードにログインします。
2. **SIM 管理&gt;ディレクトリ同期**に移動し、**探索同期**が**オン**になっていることを確認します (トグル ボタンは緑色で表示されます)。

    [Image: Verizon 構成を示すスクリーンショット。]
3. 構成に必要な **URL エンドポイント**、**トークン URL エンドポイント** および **Client ID** をコピーし、これらをMicrosoft Entra側に入力する必要があります。
4. Verizon のプライベート ネットワークと同期する **追加の属性** を選択します。

    Note

    特定の必須属性 (ICCID など) が事前に選択され、グレー表示されます。
5. **保存** をクリックします。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Verizon プロビジョニングを追加する

Microsoft Entra アプリケーション ギャラリーから Verizon を追加して、Verizon へのプロビジョニングの管理を開始します。 Verizon OSND を以前にセットアップしたことがある場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 こちらをご覧ください。 [こちら。](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Verizon プロビジョニングへの自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザー割り当てに基づいて Verizon Provisioning でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Verizon プロビジョニングの自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともアプリ所有者または[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で **Verizon** を選択します。

    [Image: スクリーンショットは、アプリケーションの一覧の Verizon リンクを示しています。]
4. **[プロビジョニング]** タブを選択します。

    [Image: スクリーンショットは、プロビジョニング タブの構成を示します。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 新しい構成の [プロビジョニング] タブ（自動）のスクリーンショット。]
6. [テナント URL] フィールドに、Verizon **テナント URL、クライアント識別子、クライアント シークレット、** **OAuth トークン エンドポイント**を入力します。 [**接続のテスト**] を選択して、Microsoft Entra ID Verizon に接続できることを確認します。 接続に失敗した場合は、Verizon アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** をクリックして変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Verizon に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Verizon のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Verizon API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | Attribute | タイプ | フィルター処理のサポート | Verizon により必須 |
    | --- | --- | --- | --- |
    | userName | String | ✓ | ✓ |
    | 表示名 | String |  |  |
    | 活動中 | ブール値 |  |  |
    | urn:ietf:params:scim:schemas:extension:vzosnd:2.0:User:iccid | String |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:vzosnd:2.0:User:profile | String |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:vzosnd:2.0:User:imei | String |  |  |
    | urn:ietf:params:scim:schemas:extension:vzosnd:2.0:User:ipv4address | String |  |  |
12. グループを選択 **します**。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Verizon に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Verizon のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/verkada-command-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Verkada Command を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/verkada-command-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Verkada Command の間でシングル サインオンを構成する方法について説明します。

この記事では、Verkada Command と Microsoft Entra ID を統合する方法について説明します。 Verkada Command を Microsoft Entra ID を統合すると、次のことができます。

- Verkada Command にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Verkada Command に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Verkada Command でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Verkada Command では、 **SP Initiated SSO と IDP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Verkada Command の追加

Microsoft Entra ID への Verkada Command の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Verkada Command を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Verkada Command」と**入力します。
4. 結果パネルから **Verkada コマンド** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Verkada Command 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Verkada Command に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Verkada Command の関連ユーザーとの間にリンク関係を確立する必要があります。

Verkada Command に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Verkada Command の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Verkada Command のテストユーザーを作成** - Verkada Command で B.Simon に対応するユーザーを作成し、ユーザーの Microsoft Entra 表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Verkada Command**&gt;**シングル サインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** があり、 **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    注意

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://vauth.command.verkada.com/saml/login/<CLIENT_ID>`

    注意

    サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには [、Verkada Command クライアント サポート チーム](mailto:support@verkada.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Verkada Command アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 Verkada Command アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: スクリーンショットには、追加のURLを設定する画面が表示されており、ここでサインオンURLを入力することができます。]
8. その他に、Verkada Command アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユニークユーザーID | User.mail |
    | 苗字 | User.surname |
    | メール | User.mail |
    | ファーストネーム | User.givenname |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Verkada コマンドのセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Verkada Command の SSO の構成

**Verkada Command** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Verkada Command サポート チーム](mailto:support@verkada.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Verkada Command のテスト ユーザーの作成

このセクションでは、Verkada Command で B.Simon というユーザーを作成します。 [Verkada Command サポート チーム](mailto:support@verkada.com)と協力して、Verkada Command プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Verkada Command Sign on URL にリダイレクトされます。
- Verkada Command のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Verkada コマンドに自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Verkada Command] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Verkada コマンドに自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/verme-tutorial"} -->
## Microsoft Entra ID で Verme for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/verme-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Verme 間にシングル サインオンを構成する方法について説明します。

この記事では、Verme と Microsoft Entra ID を統合する方法について説明します。 Verme を Microsoft Entra ID と統合すると、次のことができます:

- Verme にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Verme に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Verme サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Verme は、**SP および IDP によって開始される SSO** をサポートします。

### ギャラリーからの Verme の追加

Microsoft Entra ID への Verme の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Verme を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Verme**」と入力します。
4. 結果パネルから **Verme** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Verme 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Verme に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Verme の関連ユーザーとの間にリンク関係を確立する必要があります。

Verme に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Verme SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Verme テストユーザーの作成** - VermeにおけるB.Simonの対応ユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Verme**&gt;**シングルサインオン**にブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.verme.ru/saml/meta`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.verme.ru/saml/asc`
6. **SP** 開始モードでアプリケーションを構成する場合は、[**追加の URL の設定] を**選択し、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.verme.ru/saml/begin?idp=<IDP_NAME>`

    b。 [ **リレー状態** ] テキスト ボックスに、次の値を入力します。 `verme_ms_login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Verme クライアント サポート チーム](mailto:support@verme.ru) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Verme SSO の構成

**Verme** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Verme サポート チーム](mailto:support@verme.ru)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Verme テスト ユーザーの作成

このセクションでは、Verme で Britta Simon というユーザーを作成します。 [Verme サポート チーム](mailto:support@verme.ru)と協力して、Verme プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Verme のサインオン URL にリダイレクトされます。
- Verme のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Verme に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Verme] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Verme に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/versal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Versal を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/versal-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Versal の間のシングル サインオンを構成する方法について説明します。

この記事では、Versal と Microsoft Entra ID を統合する方法について説明します。 Versal と Microsoft Entra ID を統合すると、次のことができます。

- Versal にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Versal に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Versal でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Versal では、 **IDP** Initiated SSO がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Versal の追加

Microsoft Entra ID への Versal の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Versal を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Versal**」と入力します。
4. 結果パネルから **Versal** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Versal 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Versal に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Versal の関連ユーザーとの間にリンク関係を確立する必要があります。

Versal に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Versal SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Versal テストユーザーを作成** - Microsoft Entra におけるユーザーの表示にリンクされた B.Simon に相当する Versal 内のユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Versal**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** ページで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、値を入力します。 `VERSAL`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://versal.com/sso/saml/orgs/<organization_id>`

    注意

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには、Versal クライアント サポート チームにお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Versal アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 Versal アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: Versal アプリケーションの画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Versal のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成 U R L をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Versal の SSO の構成

**Versal** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を Versal サポート チームに送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Versal テスト ユーザーの作成

このセクションでは、Versal で B.Simon というユーザーを作成します。 「SAML テスト ユーザーの作成」サポート ガイドに従って、組織内にユーザー B.Simon を作成します。 シングル サインオンを使用する前に、Versal 内にユーザーを作成して有効化しておく必要があります。

### SSO のテスト

このセクションでは、Web サイトに埋め込まれた Versal コースを使用して Microsoft Entra のシングル サインオン構成をテストします。 Microsoft Entra シングル サインオンをサポートする Versal コースを埋め込む方法については、**SAML シングル サインオン** 組織のコース サポートガイドをご覧ください。

コースの埋め込みをテストするには、コースを作成し、それを組織と共有し、発行する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/veza-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Veza を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/veza-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-19
- Summary: Microsoft Entra ID と Veza 間にシングル サインオンを構成する方法について学習します。

この記事では、Veza と Microsoft Entra ID を統合する方法について説明します。 Veza を Microsoft Entra ID と統合すると、次のことができます:

- Veza にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Veza に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Veza でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Vezaでは、**SP**開始SSOと**IDP**開始SSOがサポートされます。
- Veza では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Veza を追加する

Microsoft Entra ID への Veza の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Veza を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新規アプリケーション**を開きます。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Veza**」と入力します。
4. 結果パネルから **Veza** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Veza 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Veza に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Veza の関連ユーザーとの間にリンク関係を確立する必要があります。

Veza に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Veza SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Veza テストユーザーを作成する** - Veza の B.Simon に対応するユーザーを作成し、Microsoft Entra でユーザーの表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Veza**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<customer>.vezacloud.com/auth/saml/metadata`。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<customer>.vezacloud.com/auth/saml/acs`。
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<instancename>.vezacloud.com/login`。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Veza のセットアップ** ] セクションで、 **ログイン URL**、 **Microsoft Entra ID 識別子**、および **ログアウト URL をコピーします**。 これらを使用して、Veza で SAML を構成します。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Veza SSO の構成

1. Veza 企業サイトに管理者としてログインします。
2. [ **管理**&gt;**サインイン設定] に**移動します。 [**SAML を有効にする** **] で**、[構成] を選択して SAML を設定します。

    [Image: [構成設定] を示すスクリーンショット。]
3. [ **SSO の構成]** ページで、次の手順を実行します。

    [Image: SSO 認証の構成を示すスクリーンショット。]

    ある。 [ **サインイン URL** ] ボックスに、コピーした **ログイン URL** の値を貼り付けます。

    b。 ダウンロードした **証明書 (Base64)** を開き、[ファイルの選択] オプションを選択して **X509 署名証明書** に **ファイル** をアップロードします。

    c. **[要求アルゴリズムの有効化]** を切り替え、[**署名要求アルゴリズム**] として RSA-SHA-256 と SHA-256 を選択します。 **要求プロトコル バインド**には、**HTTP-POST** を使用します。

    d. IdP 開始ログインを有効にするには、[ **IDP 開始ログインの有効化] を**切り替えます。 [ **発行者 ID** ] フィールドに、 **Entra の Microsoft Entra 識別子** URL を貼り付けます。

    え [ **サインアウト URL** ] ボックスに、Entra からコピーした **ログアウト URL** の値を貼り付けます。

    f. Veza SSO 構成で **[保存]** を選択し、[ **SAML を有効にする]** オプションを切り替えます。

#### Veza テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Veza に作成します。 Veza では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Veza にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Veza Sign-On URL にリダイレクトされます。
- Veza のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を有効にした Veza テナントに自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Veza] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション Sign-On ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Veza に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/viareports-inativ-portal-europe-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Viareport (ヨーロッパ) を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/viareports-inativ-portal-europe-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Viareport (ヨーロッパ) の間でシングル サインオンを構成する方法について説明します。

この記事では、Viareport (ヨーロッパ) と Microsoft Entra ID を統合する方法について説明します。 Viareport (ヨーロッパ) を Microsoft Entra ID と統合すると、次のことができます。

- Viareport (ヨーロッパ) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Viareport (ヨーロッパ) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Viareport (ヨーロッパ) のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Viareport (ヨーロッパ) では、**SPおよびIDPが開始するSSO**がサポートされます。

### ギャラリーからの Viareport (ヨーロッパ) の追加

Microsoft Entra ID への Viareport (ヨーロッパ) の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Viareport (ヨーロッパ) を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Viareport (Europe)」**と入力します。
4. 結果パネルから **Viareport (ヨーロッパ)** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra のシングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Viareport (ヨーロッパ) に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Viareport (ヨーロッパ) の関連ユーザーとの間にリンク関係を確立する必要があります。

Viareport (ヨーロッパ) に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成** する - ユーザーがこの機能を使用できるようにします。
2. **Viareport (Europe) SSO の構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Viareport (ヨーロッパ) テストユーザーの作成 - Viareport (ヨーロッパ) で B.Simon に対応したユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。**
6. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Viareport (ヨーロッパ)** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://inativ.viareport.com/SSO/<tenant_id>/callback`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://inativ.viareport.com/SSO/<tenant_id>/login`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには [、Viareport (ヨーロッパ) クライアント サポート チーム](mailto:ycezard@viareport.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Viareport (ヨーロッパ) の SSO の構成

**Viareport (ヨーロッパ)** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Viareport (ヨーロッパ) サポート チーム](mailto:ycezard@viareport.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Viareport (ヨーロッパ) のテスト ユーザーの作成

このセクションでは、Viareport (ヨーロッパ) で B.Simon というユーザーを作成します。 [Viareport (ヨーロッパ) サポート チーム](mailto:ycezard@viareport.com)と協力して、Viareport (ヨーロッパ) プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Viareport (Europe)] タイルを選択すると、SSO を設定した Viareport (Europe) に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vibehcm-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Vibe HCM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vibehcm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Vibe HCM の間にシングル サインオンを構成する方法について説明します。

この記事では、Vibe HCM と Microsoft Entra ID を統合する方法について説明します。 Vibe HCM を Microsoft Entra ID と統合すると、次のことができます。

- Vibe HCM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Vibe HCM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Vibe HCM でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Vibe HCM では、**SP** と **IDP** Initiated SSO がサポートされます。

### ギャラリーから Vibe HCM を追加する

Microsoft Entra ID への Vibe HCM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Vibe HCM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Vibe HCM**」と入力します。
4. 結果のパネルから **[Vibe HCM]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Vibe HCM 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Vibe HCM に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Vibe HCM の関連ユーザーとの間にリンク関係を確立する必要があります。

Vibe HCM に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Vibe HCM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Vibe HCM テストユーザーの作成** - Microsoft Entra のユーザーである B.Simon に対応するユーザーを Vibe HCM にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Vibe HCM**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、アプリが既に Azure に事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyName>.vibehcm.com/portal.jsp`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[Vibe HCM クライアント サポート チーム](mailto:support@vibehcm.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Vibe HCM SSO を構成する

**Vibe HCM** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Vibe HCM サポート チーム](mailto:support@vibehcm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Vibe HCM のテスト ユーザーの作成

このセクションでは、Vibe HCM で Britta Simon というユーザーを作成します。 [Vibe HCM サポート チーム](mailto:support@vibehcm.com)と連携して、Vibe HCM プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Vibe HCM サインオン URL にリダイレクトされます。
- Vibe HCM のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Vibe HCM に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Vibe HCM タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Vibe HCM に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vida-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に VIDA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vida-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と VIDA の間のシングル サインオンを構成する方法について説明します。

この記事では、VIDA と Microsoft Entra ID を統合する方法について説明します。 VIDA を Microsoft Entra ID と統合すると、次のことが可能になります。

- VIDA へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して VIDA に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- VIDA でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- VIDA では、**SP** Initiated SSO がサポートされます。
- VIDA では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの VIDA の追加

Microsoft Entra ID への VIDA の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に VIDA を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**VIDA**」と入力します。
4. 結果パネルから **[VIDA]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### VIDA に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、TIMU に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと VIDA の関連ユーザーとの間にリンク関係を確立する必要があります。

VIDA に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **VIDA の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **VIDA テストユーザーの作成** - VIDA に Microsoft Entra のユーザー B.Simon とリンクされる対応ユーザーを確保するため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**VIDA**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次の値を入力します。 `urn:amazon:cognito:sp:eu-west-2_IDmTxjGr6`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://vitruevida.auth.eu-west-2.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

    `https://vitruevida.com/?teamid=<ID>&idp=<IDP_NAME>`

    注

    サインオン URL の値は実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[VIDA クライアント サポート チーム](mailto:support@vitruehealth.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. VIDA アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、VIDA アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | assignedroles | user.assignedroles |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[VIDA のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### VIDA でロールベースのシングル サインオンを構成する

1. VIDA ロールを Microsoft Entra ユーザーに関連付けるには、次の手順に従って Microsoft Entra ID でロールを作成する必要があります。

    a. [Microsoft Graph Explorer](https://developer.microsoft.com/graph/graph-explorer) にサインオンします。

    b。 ロールを作成するために必要なアクセス許可を取得するには、[ **アクセス許可の変更** ] を選択します。

    [Image: Graph の構成 1]

    c. 次の図に示すように、一覧から次のアクセス許可を選択し、[ **アクセス許可の変更**] を選択します。

    [Image: Graph の構成 2]

    注

    アクセス許可が付与されたら、Graph Explorer に再度ログオンします。

    d. Graph Explorer ページで、最初のドロップダウン リストから **[GET]** を選択し、2 つ目のドロップダウン リストから **[ベータ]** を選択します。 次に、ドロップダウン リストの横にあるフィールドに「 `https://graph.microsoft.com/beta/servicePrincipals` 」と入力し、[クエリの **実行**] を選択します。

    [Image: Graph の構成。]

    注

    複数のディレクトリを使用している場合は、クエリのフィールドに `https://graph.microsoft.com/beta/contoso.com/servicePrincipals` を入力できます。

    e. **[Response Preview](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/応答プレビュー)** セクションで、後で使用するために "Service Principal" から appRoles プロパティを抽出します。

    [Image: 応答のプレビュー。]

    注

    クエリのフィールドに「`https://graph.microsoft.com/beta/servicePrincipals/<objectID>`」と入力することで appRoles プロパティを見つけることができます。 `objectID` は Microsoft Entra ID の **[プロパティ]** ページからコピーしたオブジェクト ID であることに注意してください。

    f. Graph エクスプローラーに戻り、メソッドを **GET** から **PATCH** に変更し、次の内容を **[要求本文** ] セクションに貼り付けて、[ **クエリの実行**] を選択します。

    ```
    { 
    "appRoles": [
        {
            "allowedMemberTypes": [
            "User"
            ],
            "description": "User",
            "displayName": "User",
            "id": "18d14569-c3bd-439b-9a66-3a2aee01****",
            "isEnabled": true,
            "origin": "Application",
            "value": null
        },
        {
            "allowedMemberTypes": [
            "User"
            ],
            "description": "msiam_access",
            "displayName": "msiam_access",
            "id": "b9632174-c057-4f7e-951b-be3adc52****",
            "isEnabled": true,
            "origin": "Application",
            "value": null
        },
        {
        "allowedMemberTypes": [
            "User"
        ],
        "description": "VIDACompanyAdmin",
        "displayName": "VIDACompanyAdmin",
        "id": "293414bb-2215-48b4-9864-64520937d437",
        "isEnabled": true,
        "origin": "ServicePrincipal",
        "value": "VIDACompanyAdmin"
        },
        {
        "allowedMemberTypes": [
            "User"
        ],
        "description": "VIDATeamAdmin",
        "displayName": "VIDATeamAdmin",
        "id": "2884f1ae-5c0d-4afd-bf28-d7d11a3d7b2c",
        "isEnabled": true,
        "origin": "ServicePrincipal",
        "value": "VIDATeamAdmin"
        },
        {
        "allowedMemberTypes": [
            "User"
        ],
        "description": "VIDAUser",
        "displayName": "VIDAUser",
        "id": "37b3218c-0c06-484f-90e6-4390ce5a8787",
        "isEnabled": true,
        "origin": "ServicePrincipal",
        "value": "VIDAUser"
        }
    ]
    }
    ```

    注

    Microsoft Entra ID では、SAML 応答の要求値として、これらのロールの値を送信します。 ただし、パッチ操作では、`msiam_access` 部分の後にのみ、新しいロールを追加できます。 作成過程を速やかに進めるため、GUID Generator など、ID ジェネレーターを使用してリアルタイムで ID を生成することをお勧めします。

    g. 必要なロールで "サービス プリンシパル" に修正プログラムが適用されたら、記事の「 **Microsoft Entra テスト ユーザーの割り当て** 」セクションの手順に従って、Microsoft Entra ユーザー (B.Simon) にロールをアタッチします。

### VIDA SSO の構成

**VIDA** 側でシングル サインオンを構成するには、ダウンロードした **フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [VIDA サポート チーム](mailto:support@vitruehealth.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### VIDA テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを VIDA に作成します。 VIDA では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 VIDA にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる VIDA サインオン URL にリダイレクトされます。
- VIDA のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [VIDA] タイルを選択すると、このオプションは VIDA のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vidyard-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Vidyard を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vidyard-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Vidyard の間でシングル サインオンを構成する方法について説明します。

この記事では、Vidyard と Microsoft Entra ID を統合する方法について説明します。 Vidyard と Microsoft Entra ID の統合には、次の利点があります。

- Vidyard にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Vidyard に自動的にサインイン (シングル サインオン) できるように設定できます。
- 1 つの場所でアカウントを管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「[Microsoft Entra ID を使ったアプリケーション アクセスとシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)」を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Vidyard でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Vidyard では、**SP** と **IDP** によって開始される SSO がサポートされます
- Vidyard では、**Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Vidyard の追加

Vidyard の Microsoft Entra ID への統合を構成するには、Vidyard をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

**ギャラリーから Vidyard を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Vidyard」**と入力し、結果パネルで **Vidyard** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果リストの Vidyard]

### Microsoft Entra シングル サインオンの構成とテスト

このセクションでは、**Britta Simon** という名前のテスト ユーザーに基づいて、Vidyard で Microsoft Entra シングル サインオンを構成してテストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Vidyard の関連ユーザーの間にリンク関係を確立する必要があります。

Vidyard で Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. **Microsoft Entra シングル サインオンを構成する** - ユーザーがこの機能を使用できるようにします。
2. **Vidyard シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Vidyard の試用ユーザーを作成** - Vidyard で Britta Simon に対応するユーザーを作成し、そのユーザーをMicrosoft Entraのユーザー表現にリンクします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra シングル サインオンを有効にします。

Vidyard で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Vidyard** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン構成のリンク]
3. **[シングル サインオン方式の選択]** ダイアログで、 **[SAML/WS-Fed]** モードを選択して、シングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [Image: このスクリーンショットは、[基本的な SAML 構成] を示しています。ここで、識別子と応答 U R L を入力し、[保存] を選択できます。]

    ある。 **[識別子]** ボックスに、`https://secure.vidyard.com/sso/saml/<unique id>/metadata` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://secure.vidyard.com/sso/saml/<unique id>/consume` のパターンを使用して URL を入力します
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: このスクリーンショットは、[追加の U R L を設定します] を示しています。ここで、サインオン U R L を入力できます。]

    **[サインオン URL]** ボックスに、`https://secure.vidyard.com/sso/saml/<unique id>/login` という形式で URL を入力します。

    注意

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、Sign-On URL で更新します。これについては、後で説明します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[Vidyard のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Vidyard シングル サインオンの構成

1. 別の Web ブラウザーのウィンドウで、管理者として Vidyard Software 企業サイトにサインインします。
2. Vidyard ダッシュボードから、 **[Group]\(グループ\)**&gt;**[Security]\(セキュリティ\)** を選択します

    [Image: Vidyard Software サイトで [Group](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/グループ) から [Security](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティ) が選択されていることを示すスクリーンショット。]
3. [ **新しいプロファイル] タブを** 選択します。

    [Image: [New Profile](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しいプロファイル) ボタンを示すスクリーンショット。]
4. **[SAML Configuration]\(SAML の構成\)** セクションで、次の手順に従います。

    [Image: [SAML Configuration](SAML の構成) を示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[Profile Name]\(プロファイル名\)** ボックスに一般的なプロファイル名を入力します。

    b。 **[SSO ユーザー ログイン ページ]** の値をコピーし、**[基本的な SAML 構成]** セクションにある **[サインオン URL]** テキストボックスに貼り付けます。

    c. **[ACS URL]** の値をコピーして、**[基本的な SAML 構成]** セクションの **[応答 URL]** テキストボックスに貼り付けます。

    d. **[発行者/メタデータ URL]** の値をコピーし、**[基本的な SAML 構成]** セクションにある **[識別子]** テキストボックスに貼り付けます。

    え Azure portal からダウンロードした証明書ファイルをメモ帳で開き、 **[X.509 Certificate]\(X.509 証明書\)** ボックスに貼り付けます。

    f. **[SAML Endpoint URL]\(SAML エンドポイント URL\)** ボックスに、Azure portal からコピーした **[ログイン URL]** の値を貼り付けます。

    ジー **確認** を選択します。
5. [Single Sign On]\(シングル サインオン\) タブで、既存のプロファイルの横にある **[Assign]\(割り当て\)** を選択します。

    注意

    SSO プロファイルを作成したら、ユーザーが Azure 経由でアクセスする必要のある任意のグループに SSO プロファイルを割り当てます。 割り当てられたグループ内にユーザーが存在しない場合、Vidyard は自動的にユーザー アカウントを作成し、リアルタイムでロールを割り当てます。
6. **[Assign SAML Configuration to Organizations]\(組織への SAML 構成の割り当て\)** で、**[Groups Available to Assign]\(割り当て可能なグループ\)** に表示されている、対象の組織グループを選択します。
7. **[Groups Currently Assigned]\(現在割り当てられているグループ\)** に、割り当てられているグループが表示されます。 組織に従ってグループの役割を選択し、[確認] を選択 **します**。

    注意

    詳しくは、[こちらの文書](https://knowledge.vidyard.com/hc/articles/360009990033-SAML-based-Single-Sign-On-SSO-in-Vidyard)を参照してください。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Vidyard のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Vidyard に作成します。 Vidyard では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Vidyard にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注意

ユーザーを手動で作成する必要がある場合は、[Vidyard サポート チーム](mailto:support@vidyard.com)にお問い合わせください。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra シングル サインオン構成をテストします。

アクセス パネルで [Vidyard] タイルを選択すると、SSO を設定した Vidyard に自動的にサインインします。 アクセス パネルの詳細については、[アクセス パネルの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/virtual-risk-manager-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Virtual Risk Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/virtual-risk-manager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Virtual Risk Manager の間にシングル サインオンを構成する方法について説明します。

この記事では、Virtual Risk Manager と Microsoft Entra ID を統合する方法について説明します。 Virtual Risk Manager を Microsoft Entra ID と統合すると、次のことができます。

- Virtual Risk Manager にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Virtual Risk Manager に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Virtual Risk Manager でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Virtual Risk Manager では、**IDP** Initiated SSO がサポートされます

### ギャラリーからの Virtual Risk Manager の追加

Microsoft Entra ID への Virtual Risk Manager の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Virtual Risk Manager を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Virtual Risk Manager**」と入力します。
4. 結果のパネルから **[Virtual Risk Manager]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Virtual Risk Manager 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Virtual Risk Manager に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Virtual Risk Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

Virtual Risk Manager に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Virtual Risk Manager の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Virtual Risk Manager のテストユーザーを作成** - B.Simon に対応するテストユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Virtual Risk Manager**&gt;**シングルサインオンに移動します。**
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Virtual Risk Manager のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Virtual Risk Manager の SSO の構成

**Virtual Risk Manager** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Virtual Risk Manager サポート チーム](mailto:globalsupport@edriving.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Virtual Risk Manager のテスト ユーザーの作成

このセクションでは、Virtual Risk Manager で Britta Simon というユーザーを作成します。 [Virtual Risk Manager サポート チーム](mailto:globalsupport@edriving.com)と連携し、Virtual Risk Manager プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Virtual Risk Manager に自動的にサインインします
- Microsoft アクセス パネルを使用することができます。 アクセス パネルで [Virtual Risk Manager] タイルを選択すると、SSO を設定した Virtual Risk Manager に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/virtual-risk-manager-usa-tutorial"} -->
## Microsoft Entra ID を使用したシングル サインオン用に Virtual Risk Manager - USA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/virtual-risk-manager-usa-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Virtual Risk Manager (米国) 間のシングル サインオンを構成する方法について説明します。

この記事では、Virtual Risk Manager - USA と Microsoft Entra ID を統合する方法について説明します。 Virtual Risk Manager (米国) を Microsoft Entra ID と統合すると、次のことが可能になります。

- Virtual Risk Manager (米国) にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Virtual Risk Manager (米国) に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Virtual Risk Manager (米国) でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Virtual Risk Manager (米国) では、**IDP** Initiated SSO がサポートされます。
- Virtual Risk Manager (米国) では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Virtual Risk Manager (米国) の追加

Microsoft Entra ID への Virtual Risk Manager (米国) の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Virtual Risk Manager (米国) を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Virtual Risk Manager (米国)** 」と入力します。
4. 結果のパネルから **[Virtual Risk Manager (米国)]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Virtual Risk Manager (米国) に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Virtual Risk Manager (米国) に対する Microsoft Entra SSO を構成・テストします。 SSO が機能するには、Microsoft Entra ユーザーと Virtual Risk Manager (米国) の関連ユーザー間にリンク関係を確立する必要があります。

Virtual Risk Manager (米国) に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Virtual Risk Manager (米国) SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Virtual Risk Manager (米国) のテスト ユーザーの作成** - Virtual Risk Manager (米国) で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Virtual Risk Manager - USA**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Virtual Risk Manager (米国) の SSO の構成

**Virtual Risk Manager (USA)** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Virtual Risk Manager (USA) サポート チーム](mailto:globalsupport@edriving.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Virtual Risk Manager (米国) のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Virtual Risk Manager (米国) に作成します。 Virtual Risk Manager (米国) では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Virtual Risk Manager (米国) にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Virtual Risk Manager - USA に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Virtual Risk Manager - USA] タイルを選択すると、SSO を設定した Virtual Risk Manager - USA に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/visa-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Visa Spend Clarity for Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visa-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-09
- Summary: Microsoft Entra IDから Visa Spend Clarity for Enterprise にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、Visa Spend Clarity for Enterprise と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成されると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Visa Spend Clarity for Enterprise](https://enterprise.spendclarity.visa.com/) に対するユーザーのプロビジョニングとプロビジョニング解除を自動的に行います。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Visa Spend Clarity for Enterprise でユーザーを作成します。
- アクセスが不要になったら、Visa Spend Clarity for Enterprise のユーザーを削除します。
- Microsoft Entra IDと Visa Spend Clarity for Enterprise の間でユーザー属性の同期を維持します。
- Visa Spend Clarity for Enterprise でグループとグループ メンバーシップをプロビジョニングします。
- Visa Spend Clarity for Enterprise への [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) を有効にします (推奨)。
- クライアント資格情報認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可を持つ Visa Spend Clarity for Enterprise のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントの計画を立てる

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
- [Microsoft Entra IDと Visa Spend Clarity for Enterprise の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように、Enterprise の Visa Spend Clarity を構成する

Microsoft Entra IDでプロビジョニングを構成する前に、Visa Spend Clarity for Enterprise に OAuth クライアントを登録する必要があります。 Visa Spend Clarity for Enterprise では動的クライアント登録がサポートされています。動的クライアント登録は、Microsoft Entra テナントを会社または会社にマップするときに OAuth 2.0/OpenID Connect (OIDC) クライアントを自動的に作成します。

OAuth クライアントを登録し、Visa Spend Clarity for Enterprise プロビジョニング API の呼び出しに使用Microsoft Entra ID資格情報を取得するには、次の手順に従います。

1. 管理者アカウントで [Visa Spend Clarity for Enterprise](https://enterprise.spendclarity.visa.com/) にサインインします。
2. アクセス レベルに応じて、次のいずれかに移動します。

    - **管理**&gt;**会社の管理**&gt;**高度な構成**&gt;**外部 SSO 構成** (会社のコンテキストでの会社レベルのマッピングの場合) または
    - **管理**&gt;**会社の管理**&gt;**企業 SSO を管理** する (企業レベルのマッピングの場合)。

    [Image: Visa Spend Clarity for Enterprise 管理メニューの [External SSO Configuration](外部 SSO 構成) オプションのスクリーンショット。]
3. **外部SSO構成** ダイアログで、次のフィールドに入力してください。

    | フィールド | 価値 |
    | --- | --- |
    | **ID プロバイダー** | ドロップダウン リストから **Microsoft Entra ID** を選択します。 |
    | **テナント ID** | Microsoft Entraテナント ID (たとえば、`12345678-1234-1234-1234-123456789abc`) を入力します。 |
    | **ユーザー プロビジョニング用の OAuth クライアントを作成する** | 動的クライアント登録を使用して OAuth/OIDC クライアントを自動的に作成するには、このチェック ボックスをオンにします。 |

    Important

    クライアントの作成が重複しないように既存のテナント マッピングを編集する場合、[ **OAuth クライアントの作成** ] チェック ボックスは非表示になります。 既存のマッピングの資格情報を再作成する必要がある場合は、Visa Spend Clarity for Enterprise サポートにお問い合わせください。
4. **保存**を選びます。 Visa Spend Clarity for Enterprise は、テナント マッピングを保存し、バックグラウンドで動的クライアント登録を開始します。

    [Image: テナント マッピングが保存された後の Visa Spend Clarity for Enterprise の [External SSO Configuration](外部 SSO 構成) ダイアログのスクリーンショット。]
5. 登録が成功すると、Visa Spend Clarity for Enterprise に OAuth クライアントの資格情報が表示されます。

    | 資格情報 | 説明 |
    | --- | --- |
    | **クライアント ID** | OAuth クライアントの一意の識別子。 |
    | **クライアント シークレット** | OAuth クライアントとして認証するために使用されるシークレット。 |

    各値の横にある **[コピー** ] ボタンを使用して、クリップボードにコピーします。

    [Image: Visa Spend Clarity for Enterprise の OAuth クライアント資格情報パネルのスクリーンショット。[クライアント ID] と [クライアント シークレット] と [コピー] ボタンが表示されています。]

    Warning

    **これらの資格情報をすぐに保存します。** クライアント シークレットは 1 回だけ表示され、後で取得することはできません。 セキュリティで保護されたパスワード マネージャーまたはシークレット コンテナーに値を格納します。 資格情報が失われた場合は、OAuth クライアントを削除し、新しいテナント マッピングを作成する必要があります。
6. クライアントの登録に失敗しても、テナント マッピングは保存されますが、OAuth クライアントの作成に失敗したことを示すエラー メッセージが表示されます。 この場合は、Visa Spend Clarity for Enterprise サポートに連絡して、OAuth クライアントを手動で登録してください。
7. **クライアント ID** と**クライアント シークレット**の値を保持します。 手順 5 では、Microsoft Entra IDで**テナント URL** と**シークレット トークン**を構成するときに使用します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Visa Spend Clarity for Enterprise を追加する

Microsoft Entra アプリケーション ギャラリーから Visa Spend Clarity for Enterprise を追加して、Visa Spend Clarity for Enterprise へのプロビジョニングの管理を開始します。 SSO 用に Visa Spend Clarity for Enterprise を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 詳細については、「[Microsoft Entra テナントにアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Visa Spend Clarity for Enterprise への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDでのユーザーの割り当てに基づいて Visa Spend Clarity for Enterprise のユーザーを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

Microsoft Entra IDで自動ユーザー プロビジョニングを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくともアプリ所有者または[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードを示すスクリーンショット。]
3. アプリケーションの一覧で、[ **Visa Spend Clarity for Enterprise**] を選択します。

    [Image: スクリーンショットは、アプリケーションの一覧の [Visa Spend Clarity for Enterprise] リンクを示しています。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブを示すスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: Microsoft Entra 管理センターの [プロビジョニング] タブのスクリーンショット。[新しい構成] ボタンが強調表示されています。]
6. [**テナント URL**] フィールドに、Visa Spend Clarity for Enterprise **テナント URL、クライアント識別子、クライアント シークレット、** **OAuth トークン エンドポイント**を入力します。 [**テスト接続**] を選択して、Microsoft Entra IDが Visa Spend Clarity for Enterprise に接続できることを確認します。 接続に失敗した場合は、Visa Spend Clarity for Enterprise アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: Microsoft Entra 管理センターの [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択します。
11. [属性マッピング] セクションで、Microsoft Entra IDから Visa Spend Clarity for Enterprise に同期されるユーザー**属性**を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作のために Visa Spend Clarity for Enterprise のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Visa Spend Clarity for Enterprise API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | Attribute | タイプ | フィルター処理のサポート | ビザで求められるエンタープライズ向け支出の明確さ |
    | --- | --- | --- | --- |
    | userName | String | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | externalId | String |  |  |
    | givenName | String |  |  |
    | 名字 | String |  |  |
    | emails | String |  |  |
    | 社員番号 | 数値 |  |  |
    | urn:ietf:params:scim:schemas:extension:visa:2.0:User:subCompanyId | String |  |  |

    Note

    **SubCompanyId** は、企業のプロビジョニングが設定されている場合にのみ必要です。
12. グループを選択 **します**。
13. [属性マッピング] セクションで、Microsoft Entra IDから Visa Spend Clarity for Enterprise に同期されるグループ**属性**を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Visa Spend Clarity for Enterprise のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。
14. スコープ フィルターを構成するには、スコープ フィルターに関する [記事の記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts) で提供されている次の手順を参照してください。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user) を確認して、プロビジョニング サイクルの状態と完了までの時間を確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/visibly-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Visibly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visibly-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから Visibly にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Visibly と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、[Visibly](https://visibly.io/) にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Visibly でユーザーを作成する
- アクセスが不要になったときに Visibly でユーザーを削除する
- Microsoft Entra IDと Visibly の間でユーザー属性の同期を維持する
- Visibly にグループとグループ メンバーシップをプロビジョニングする
- Visibly への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visibly-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Visibly](https://visibly.io/) のテナント

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとVisiblyの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Visibly を構成する

**テナント URL** と **シークレット トークン**については、Visibly サポートチームにお問い合わせください。 これらの値は、Visibly アプリケーションの [プロビジョニング] タブに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Visibly を追加する

Microsoft Entra アプリケーション ギャラリーから Visibly を追加して、Visibly へのプロビジョニングの管理を開始します。 SSO のために Visibly を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Visibly への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて TestApp でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Visibly の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Visibly]** を選択します。

    [Image: アプリケーションの一覧の [Visibly] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Visibly テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Visibly に接続できることを確認します。 接続に失敗した場合は、Visibly アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Visibly に同期されるユーザー属性を確認します。 **照合**用プロパティとして選択されている属性は、更新処理で Visibly のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Visibly API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | ユーザー名 | 糸 |
    | 活動中 | ブール値 |
    | 表示名 | 糸 |
    | 名前を付ける | 糸 |
    | 名前.姓 | 糸 |
    | 名前.整形済み | 糸 |
    | エクスターナルID | 糸 |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Visibly に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Visibly のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | 表示名 | 糸 |
    | エクスターナルID | 糸 |
    | メンバー | リファレンス |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/visibly-tutorial"} -->
## Microsoft Entra ID で Visibly for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visibly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Visibly の間にシングル サインオンを構成する方法について説明します。

この記事では、Visibly と Microsoft Entra ID を統合する方法について説明します。 Visibly と Microsoft Entra ID を統合すると、次のことができます。

- Visibly にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Visibly に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Visibly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Visibly では、**SP** Initiated SSO がサポートされています。
- Visibly では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visibly-provisioning-tutorial)がサポートされています。

### ギャラリーからの Visibly の追加

Microsoft Entra ID への Visibly の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Visibly を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Visibly**」と入力します。
4. 結果パネルから **[Visibly]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Visibly 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Visibly に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Visibly の関連ユーザーとの間にリンク関係を確立する必要があります。

Visibly に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Visibly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Visibly テスト ユーザーを作成** - Visibly で B.Simon と対応するユーザーを作成し、それを Microsoft Entra の表現としてリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Visibly**&gt;**シングルサインオン**を開きます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.visibly.io/`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://api.visibly.io/api/v1/verifyResponse`
6. Visibly アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、Visibly アプリケーションでは、次に示す属性が SAML 応答で返されることが想定されています。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 都市 | ユーザーの都市 |
    | 苗字 | ユーザーの名字 |
    | 状態 | ユーザーの状態 |
    | 部署 | ユーザーの部署 |
    | メール | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Visibly のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Visibly SSO の構成

1. ご自分の資格情報を使用して Visibly にサインインします。
2. ナビゲーション メニューから、**設定**オプションに移動します。

    [Image: 設定オプションが選択されていることを示すスクリーンショット。]
3. [設定] 内 **の [統合** ] を選択します。

    [Image: [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定) メニューから [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) が選択されていることを示すスクリーンショット。]
4. **[Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合)** で **[SSO]** を選択します。

    [Image: [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) から [S S O] が選択されていることを示すスクリーンショット。]
5. 次のページで、以下の手順を実行します。

    [Image: [S S O Integration](S S O 統合ページ) を示すスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[エンティティ ID]** テキストボックスに、先ほどコピーした **エンティティ ID** の値を貼り付けます。

    b。 **[SSO URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    c. **[SSO name](SSO 名)** ボックスに、有効な名前を指定します。

    d. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[証明書]** テキストボックスに貼り付けます。または、**[証明書のアップロード]** を選択して、**証明書**をアップロードすることもできます。

    え **保存** を選択します。

#### Visibly テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Visibly に作成します。 Visibly では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Visibly にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

Visibly では、自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visibly-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Azure portal で **[このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Visibly のサインオン URL にリダイレクトします。
- Visibly のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Visibly] タイルを選択すると、このオプションは Visibly のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/visitly-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Visitly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visitly-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: ユーザー アカウントを Visitly に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Visitly に対してユーザーまたはグループを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するために、Visitly と Microsoft Entra ID で実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用したサービスとしてのソフトウェア (SaaS) アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除に関するページを参照してください。

### サポートされている機能

- Visitly でユーザーを作成します。
- アクセスが不要になった場合は、Visitly のユーザーを削除します。
- Visitly に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visitly-tutorial)します (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Visitly テナント](https://www.visitly.io/pricing/)
- 管理者アクセス許可がある Visitly のユーザー アカウント

### 手順 1: Visitly にユーザーを割り当てる

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID内のアプリケーションに割り当てられたユーザーまたはグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Visitly へのアクセスが必要なMicrosoft Entra ID内のユーザーまたはグループを決定します。 その後、次の手順に従って、これらのユーザーまたはグループを Visitly に割り当てます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Visitly に割り当てる際の重要なヒント

- Visitly に 1 人のMicrosoft Entra ユーザーを割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後で追加のユーザーまたはグループを割り当てることができます。
- Visitly にユーザーを割り当てるときに、有効なアプリケーション固有ロール (ある場合) を割り当てダイアログ ボックスで選択する必要があります。 既定のアクセス ロールのユーザーは、プロビジョニングから除外されます。

### 手順 2: プロビジョニング用に Visitly を設定する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Visitly を構成する前に、Visitly で System for Cross-domain Identity Management (SCIM) プロビジョニングを有効にする必要があります。

1. [Visitly](https://app.visitly.io/login) にサインインします。 **[統合]**&gt;**[ホストの同期]** を選択します。

    [Image: ホスト同期のスクリーンショット。]
2. **Microsoft Entra ID** セクションを選択します。

    [Image: Microsoft Entra ID セクションのスクリーンショットです。]
3. **API キー**をコピーします。 これらの値は、Visitly アプリケーションの **[プロビジョニング]** タブにある **[シークレット トークン]** ボックスに入力されます。

    [Image: API キーのスクリーンショット。]

### 手順 3: ギャラリーから Visitly を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Visitly を構成するには、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Visitly を追加します。

Microsoft Entra アプリケーション ギャラリーから Visitly を追加するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New application** に移動します。次に、結果パネルで **Visitly** を選択し、**Add** を選択してアプリケーションを追加します。

    [Image: 結果一覧の Visitly のスクリーンショット。]

### 手順 4: Visitly に自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Visitly でユーザーまたはグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Visitly で SAML ベースのシングル サインオンを有効にするには、 [Visitly シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visitly-tutorial)に関する記事の手順に従います。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra ID で Visitly の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Visitly** に移動します。

    [Image: アプリケーションの一覧の [Visitly] リンクのスクリーンショット。]
3. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
4. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
5. [管理者資格情報] セクションの `https://api.visitly.io/v1/usersync/SCIM` および **[シークレット トークン]** に、先ほど取得した  と **API キー**の値をそれぞれ入力します。 **Test Connection** を選択して、Microsoft Entra IDが Visitly に接続できることを確認します。 接続できない場合は、使用中の Visitly アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
6. [ **作成]** を選択して構成を作成します。
7. [**概要**] ページで **[プロパティ**] を選択します。
8. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
9. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
10. **Attribute Mappings** セクションで、Microsoft Entra IDから Visitly に同期されるユーザー属性を確認します。 **照合**用プロパティとして選択されている属性は、更新処理で Visitly のユーザー アカウントとの照合に使用されます。 すべての変更をコミットするには、**[保存]** を選択します。

    [Image: Visitly のユーザー属性]
11. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
12. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
13. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

Visitly では、物理的な削除をサポートしていません。 すべて論理的な削除のみが可能です。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/visitly-tutorial"} -->
## Microsoft Entra ID で Visitly for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visitly-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Visitly の間のシングル サインオンを構成する方法について説明します。

この記事では、Visitly と Microsoft Entra ID を統合する方法について説明します。 Visitly を Microsoft Entra ID と統合すると、次のことが可能になります。

- Visitly へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Visitly に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Visitly でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Visitly では、**IDP** Initiated SSO がサポートされます。
- Visitly では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visitly-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Visitly の追加

Microsoft Entra ID への Visitly の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Visitly を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Visitly**」と入力します。
4. 結果のパネルから **[Visitly]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Visitly 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Visitly に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Visitly の関連ユーザーとの間にリンク関係を確立する必要があります。

Visitly に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Visitly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Visitly テスト ユーザーの作成** - Visitly において B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Visitly]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Visitly アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Visitly アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前識別子の値 | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Visitly の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Visitly SSO の構成

**Visitly** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [Visitly サポート チーム](mailto:support@visitly.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Visitly のテスト ユーザーの作成

このセクションでは、Visitly で Britta Simon というユーザーを作成します。 [Visitly サポート チーム](mailto:support@visitly.io)と連携し、Visitly プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Visitly では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visitly-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Visitly に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Visitly] タイルを選択すると、SSO を設定した Visitly に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/visitorg-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの Visit.org を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visitorg-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Visit.org の間にシングル サインオンを構成する方法について説明します。

この記事では、Visit.org と Microsoft Entra ID を統合する方法について説明します。 Visit.org を Microsoft Entra ID と統合すると、次のことができます。

- Visit.org にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Visit.org に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Visit.org でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Visit.org では、**IDP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Visit.org の追加

Microsoft Entra ID への Visit.org の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Visit.org を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Visit.org**」と入力します。
4. 結果のパネルから **[Visit.org]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Visit.org 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Visit.org に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Visit.org の関連ユーザーの間にリンク関係を確立する必要があります。

Visit.org に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Visit.org SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Visit.org テストユーザーの作成**をします - Visit.org で B.Simon に対応するユーザーを作成し、Microsoft Entra 内のユーザー表現にリンクされます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Visit.org**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Visit.org アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Visit.org アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス (user.emailaddress) |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | last\_name | ユーザーの名字 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
9. **[Visit.org のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Visit.org SSO の構成

**Visit.org** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とアプリケーション構成からコピーした適切な URL を [Visit.org サポート チーム](mailto:tech@visit.org)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Visit.org テスト ユーザーの作成

このセクションでは、Visit.org で B.Simon というユーザーを作成します。[Visit.org サポート チーム](mailto:tech@visit.org)と連携して、[アプリケーション名] プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Visit.org に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Visit.org] タイルを選択すると、SSO を設定した Visit.org に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/visma-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Visma を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visma-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Visma の間にシングル サインオンを構成する方法について説明します。

この記事では、Visma と Microsoft Entra ID を統合する方法について説明します。 Visma と Microsoft Entra ID を統合すると、次のことができます:

- Visma にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Visma に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Visma でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Visma では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- Visma では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Visma の追加

Microsoft Entra ID への Visma の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Visma を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Visma**」と入力します。
4. 結果のパネルから **[Visma]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Visma 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Visma に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Visma の関連ユーザーとの間にリンク関係を確立する必要があります。

Visma に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Visma SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Visma テストユーザーの作成** - Visma で B.Simon に相当するユーザーを作成し、それを Microsoft Entra 内のユーザーの表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Visma**&gt;**シングルサインオンページに**移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンのセットアップ] ページで** 、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.my.connect.visma.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.my.connect.visma.com/saml/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.my.connect.visma.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[Visma クライアント サポート チーム](https://www.visma.com/contact)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンのセットアップ] ページの** [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Visma SSO の構成

**Visma** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Visma サポート チーム](https://www.visma.com/contact)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Visma テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Visma に作成します。 Visma では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Visma にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Visma のサインオン URL にリダイレクトされます。
- Visma のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Visma に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Visma] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Visma に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/visual-paradigm-online-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Visual Paradigm Online を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/visual-paradigm-online-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Visual Paradigm Online の間でシングル サインオンを構成する方法について説明します。

この記事では、Visual Paradigm Online と Microsoft Entra ID を統合する方法について説明します。 Visual Paradigm Online と Microsoft Entra ID を統合すると、次のことができます。

- Visual Paradigm Online にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Visual Paradigm Online に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Visual Paradigm Online でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Visual Paradigm Online では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーから Visual Paradigm Online を追加する

Microsoft Entra ID への Visual Paradigm Online の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Visual Paradigm Online を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**する] セクションで、検索ボックスに**「Visual Paradigm Online**」と入力します。
4. 結果のパネルから **[Visual Paradigm Online** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Visual Paradigm Online の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Visual Paradigm Online に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Visual Paradigm Online の関連ユーザーとの間にリンク関係を確立する必要があります。

Visual Paradigm Online で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Visual Paradigm Online の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Visual Paradigm Online のテスト ユーザーの作成** - Visual Paradigm Online で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Visual Paradigm Online**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://online.visual-paradigm.com/w/<Workspace_ID>/saml2`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://online.visual-paradigm.com/w/<Workspace_ID>/saml2/service/`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://online.visual-paradigm.com/login.jsp`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Visual Paradigm Online サポート チーム](mailto:support@visual-paradigm.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Visual Paradigm Online アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Box はこれがユーザーのメール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: カスタム属性マッピングを示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Visual Paradigm Online のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Visual Paradigm Online の SSO の構成

**Visual Paradigm Online** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Visual Paradigm Online サポート チーム](mailto:support@visual-paradigm.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Visual Paradigm Online テスト ユーザーの作成

このセクションでは、Visual Paradigm Online で B.Simon というユーザーを作成します。 [Visual Paradigm Online サポート チーム](mailto:support@visual-paradigm.com)と協力して、Visual Paradigm Online プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Visual Paradigm Online のサインオン URL にリダイレクトされます。
- Visual Paradigm Online のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Visual Paradigm Online] タイルを選択すると、このオプションは Visual Paradigm Online のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vivopoint-tutorial"} -->
## Microsoft Entra ID で VivoPoint for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vivopoint-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と VivoPoint の間でシングル サインオンを構成する方法について説明します。

この記事では、VivoPoint と Microsoft Entra ID を統合する方法について説明します。 VivoPoint と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で VivoPoint へのアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して VivoPoint に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、無料でアカウントを作成 [できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- VivoPoint でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- VivoPoint では、**SP** によって開始される SSO のみがサポートされます。

### ギャラリーから VivoPoint を追加する

Microsoft Entra ID への VivoPoint の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に VivoPoint を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**VivoPoint**」と入力します。
4. 結果パネルで **VivoPoint** を選択し、その後アプリを追加して下さい。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### VivoPoint の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、VivoPoint に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと VivoPoint の関連ユーザーとの間にリンク関係を確立する必要があります。

VivoPoint で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **VivoPoint SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **VivoPoint テスト ユーザーの作成** - VivoPoint で B.Simon に対応するユーザーを作成し、Microsoft Entra ID のユーザー表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**VivoPoint**&gt;**シングル サインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、**[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    ある。 [**識別子 (エンティティ ID)** テキスト ボックスに、次のいずれかの URL/パターンを入力します。

    | **識別子 (エンティティ ID)** |
    | --- |
    | `https://vivopoint.com/` |
    | `https://<SUBDOMAIN>.vivopoint.com/` |

    b。 [**応答 URL**] ボックスに、次のいずれかの URL/パターンを入力します。

    | **応答 URL** |
    | --- |
    | `https://vivopoint.com/saml/acs` |
    | `https://<SUBDOMAIN>.vivopoint.com/saml/acs` |

    c. [**サインオン URL** テキスト ボックスに、次のいずれかの URL/パターンを入力します。

    | **サインオン URL** |
    | --- |
    | `https://vivopoint.com/` |
    | `https://<SUBDOMAIN>.vivopoint.com/` |

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[VivoPoint サポート チーム](mailto:support@vivopoint.com)にお問い合わせください。 Microsoft Entra 管理センターの「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、**証明書 (未加工)** を探し、[**ダウンロード]** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットには、証明書のダウンロードリンクが表示されています。証明書]
7. **[VivoPoint** のセットアップ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピーを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### VivoPoint SSO の構成

**VivoPoint** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、Microsoft Entra 管理センターからコピーした適切な URL を [VivoPoint サポート チーム](mailto:support@vivopoint.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### VivoPoint テスト ユーザーの作成

このセクションでは、VivoPoint で B.Simon というユーザーを作成します。 [VivoPoint サポート チームの](mailto:support@vivopoint.com) と連携して、VivoPoint プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる VivoPoint のサインオン URL にリダイレクトします。
- VivoPoint のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [VivoPoint] タイルを選択すると、このオプションは VivoPoint のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vmware-horizon-unified-access-gateway-tutorial"} -->
## VMware Horizon - Unified Access Gateway を Microsoft Entra ID でシングル サインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vmware-horizon-unified-access-gateway-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と VMware Horizon - Unified Access Gateway の間でシングル サインオンを構成する方法について説明します。

この記事では、VMware Horizon - Unified Access Gateway と Microsoft Entra ID を統合する方法について説明します。 VMware Horizon - Unified Access Gateway と Microsoft Entra ID を統合すると、次のことができます。

- VMware Horizon - Unified Access Gateway にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して VMware Horizon - Unified Access Gateway に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- VMware Horizon - Unified Access Gateway でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- VMware Horizon - Unified Access Gateway では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます

### ギャラリーからの VMware Horizon - Unified Access Gateway の追加

Microsoft Entra ID への VMware Horizon - Unified Access Gateway の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に VMware Horizon - Unified Access Gateway を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**VMware Horizon - Unified Access Gateway**」と入力します。
4. 結果のパネルから **VMware Horizon - Unified Access Gateway** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### VMware Horizon - Unified Access Gateway の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、VMware Horizon - Unified Access Gateway に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと VMware Horizon - Unified Access Gateway の関連ユーザーとの間にリンク関係を確立する必要があります。

VMware Horizon - Unified Access Gateway で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **VMware Horizon - Unified Access Gateway SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **VMware Horizon - Unified Access Gateway テスト ユーザーの作成** - VMware Horizon - Unified Access Gateway で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリケーション]**&gt;**[VMware Horizon - Unified Access Gateway]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<HORIZON_UAG_FQDN>/portal`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<HORIZON_UAG_FQDN>/portal/samlsso`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<HORIZON_UAG_FQDN>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[VMware Horizon - Unified Access Gateway クライアント サポート チーム](mailto:support@vmware.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[VMware Horizon - Unified Access Gateway のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### VMware Horizon - Unified Access Gateway の SSO を構成する

**VMware Horizon - Unified Access Gateway** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーションの構成からコピーした適切な URL を [VMware Horizon - Unified Access Gateway サポート チーム](mailto:support@vmware.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### VMware Horizon - Unified Access Gateway のテスト ユーザーを作成する

このセクションでは、VMware Horizon - Unified Access Gateway で B.Simon というユーザーを作成します。 [VMware Horizon - Unified Access Gateway サポート チーム](mailto:support@vmware.com)と連携して、VMware Horizon - Unified Access Gateway プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる VMware Horizon - Unified Access Gateway のサインオン URL にリダイレクトされます。
- VMware Horizon - Unified Access Gateway のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した VMware Horizon - Unified Access Gateway に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [VMware Horizon - Unified Access Gateway] タイルを選択すると、SSO を設定した VMware Horizon - Unified Access Gateway に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vmware-identity-service-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に VMware Identity Service を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vmware-identity-service-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と VMware Identity Service の間でシングル サインオンを構成する方法について説明します。

この記事では、VMware Identity Service と Microsoft Entra ID を統合する方法について説明します。 VMware Identity Service は、VMware 製品用の Microsoft Entra ID との統合を提供します。 ユーザーとグループのプロビジョニングには SCIM プロトコルを使用し、認証には SAML を使用します。 VMware Identity Service と Microsoft Entra ID を統合すると、次のことができます:

- VMware Identity Service にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して VMware Identity Service に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で VMware Identity Service 向けの Microsoft Entra のシングル サインオンを構成してテストします。 VMware Identity Service では、 **SP** と **IDP** によって開始されるシングル サインオンと **Just In Time** ユーザー プロビジョニングの両方がサポートされます。

### [前提条件]

Microsoft Entra ID を VMware Identity Service と統合するには、次が必要です:

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- VMware Identity Service のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから VMware Identity Service アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから VMware Identity Service を追加する

Microsoft Entra アプリケーション ギャラリーから VMware Identity Service を追加して、VMware Identity Service でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**VMware Identity Service**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CustomerName>.vmwareidentity.com/SAAS/API/1.0/GET/metadata/sp.xml` |
    | `https://<CustomerName>.workspaceoneaccess.com/SAAS/API/1.0/GET/metadata/sp.xml` |
    | `https://<CustomerName>.vmwareidentity.asia/SAAS/API/1.0/GET/metadata/sp.xml` |
    | `https://<CustomerName>.vmwareidentity.eu/SAAS/API/1.0/GET/metadata/sp.xml` |
    | `https://<CustomerName>.vmwareidentity.co.uk/SAAS/API/1.0/GET/metadata/sp.xml` |
    | `https://<CustomerName>.vmwareidentity.de/SAAS/API/1.0/GET/metadata/sp.xml` |
    | `https://<CustomerName>.vmwareidentity.ca/SAAS/API/1.0/GET/metadata/sp.xml` |
    | `https://<CustomerName>.vmwareidentity.com.au/SAAS/API/1.0/GET/metadata/sp.xml` |
    | `https://<CustomerName>.vidmpreview.com/SAAS/API/1.0/GET/metadata/sp.xml` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CustomerName>.vmwareidentity.com/SAAS/auth/saml/response` |
    | `https://<CustomerName>.workspaceoneaccess.com/SAAS/auth/saml/response` |
    | `https://<CustomerName>.vmwareidentity.asia/SAAS/auth/saml/response` |
    | `https://<CustomerName>.vmwareidentity.eu/SAAS/auth/saml/response` |
    | ` https://<CustomerName>.vmwareidentity.co.uk/SAAS/auth/saml/response` |
    | `https://<CustomerName>.vmwareidentity.de/SAAS/auth/saml/response` |
    | `https://<CustomerName>.vmwareidentity.ca/SAAS/auth/saml/response` |
    | `https://<CustomerName>.vmwareidentity.com.au/SAAS/auth/saml/response` |
    | `https://<CustomerName>.vidmpreview.com/SAAS/auth/saml/response` |
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **サインオン URL** |
    | --- |
    | `https://<CustomerName>.vmwareidentity.com` |
    | `https://<CustomerName>.workspaceoneaccess.com` |
    | `https://<CustomerName>.vmwareidentity.asia` |
    | `https://<CustomerName>.vmwareidentity.eu` |
    | `https://<CustomerName>.vmwareidentity.co.uk` |
    | `https://<CustomerName>.vmwareidentity.de` |
    | `https://<CustomerName>.vmwareidentity.ca` |
    | `https://<CustomerName>.vmwareidentity.com.au` |
    | `https://<CustomerName>.vidmpreview.com` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、VMware Identity Service クライアント サポート チーム](mailto:support@vmware.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. VMware Identity Service アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: トークン属性の画像を示すスクリーンショット。]
8. 上記に加えて、VMware Identity Service アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを以下に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | ユーザー名 | ユーザー.ユーザープリンシパルネーム |
    | エクスターナルID | user.objectid (ユーザーのオブジェクトID) |
    | メール | ユーザーのメールアドレス |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### VMware Identity Service SSO の構成

**VMware Identity Service SSO** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[VMware Identity Service SSO サポート チーム](mailto:support@vmware.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### VMware Identity Service のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを VMware Identity Service に作成します。 VMware Identity Service では、既定で有効になっている Just-In-Time ユーザー プロビジョニングがサポートされます。 このセクションにはアクション項目はありません。 VMware Identity Service にユーザーがまだ存在しない場合は、新しいユーザーが認証の後に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる VMware Identity Service のサインオン URL にリダイレクトされます。
- VMware Identity Service のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した VMware Identity Service に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [VMware Identity Service] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した VMware Identity Service に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vocoli-tutorial"} -->
## Microsoft Entra ID で Vocoli for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vocoli-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Vocoli 間にシングル サインオンを構成する方法について説明します。

この記事では、Vocoli と Microsoft Entra ID を統合する方法について説明します。 Vocoli を Microsoft Entra ID と統合すると、次のことができます。

- Vocoli にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Vocoli に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Vocoli でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Vocoli では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Vocoli の追加

Microsoft Entra ID への Vocoli の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Vocoli を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Vocoli」**と入力します。
4. 結果パネルから **Vocoli** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Vocoli 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Vocoli に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Vocoli の関連ユーザーとの間にリンク関係を確立する必要があります。

Vocoli に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Vocoli SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Vocoli テスト ユーザーの作成** - Microsoft Entra のユーザー表現にリンクされた Vocoli で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Vocoli**&gt;**シングルサインオン**にブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Vocoli のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Vocoli の SSO の構成

**Vocoli** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Vocoli サポート チーム](mailto:inbox@vocoli.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Vocoli のテスト ユーザーの作成

このセクションでは、Vocoli で B.Simon というユーザーを作成します。 [Vocoli サポート チーム](mailto:inbox@vocoli.com)と協力して、Vocoli プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Vocoli に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Vocoli] タイルを選択すると、SSO を設定した Vocoli に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vodeclic-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Vodeclic を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vodeclic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Vodeclic 間にシングル サインオンを構成する方法について説明します。

この記事では、Vodeclic と Microsoft Entra ID を統合する方法について説明します。 Vodeclic と Microsoft Entra ID の統合には、次の利点があります。

- Vodeclic にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Vodeclic に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Vodeclic でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Vodeclic では、**SP**によるSSOと**IDP**によるSSOがサポートされます。

### ギャラリーからの Vodeclic の追加

Microsoft Entra ID への Vodeclic の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Vodeclic を追加する必要があります。

**ギャラリーから Vodeclic を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「 **Vodeclic」**と入力し、結果パネルで **Vodeclic** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Vodeclic]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、Vodeclic で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Vodeclic の関連ユーザーの間にリンク関係を確立する必要があります。

Vodeclic で Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Vodeclic シングル サインオンの構成** - アプリケーション側でシングル Sign-On 設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Vodeclic のテストユーザーを作成** - Vodeclic 内で Britta Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Vodeclic で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Vodeclic** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: [基本的な SAML 構成] を示すスクリーンショット。ここで、[識別子]、[応答 URL] の順に入力し、[保存] を選択できます。]

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.lms.vodeclic.net/auth/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.lms.vodeclic.net/auth/saml/callback`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [Image: スクリーンショットは、追加のU R Lを設定する場所を示しています。ここでは、サインオンU R Lを入力できます。]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.lms.vodeclic.net/auth/saml`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Vodeclic クライアント サポート チーム](mailto:hotline@vodeclic.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Vodeclic のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### Vodeclic シングル サインオンの構成

**Vodeclic** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Vodeclic サポート チーム](mailto:hotline@vodeclic.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Vodeclic テスト ユーザーの作成

このセクションでは、Vodeclic で Britta Simon というユーザーを作成します。 [Vodeclic サポート チーム](mailto:hotline@vodeclic.com)と協力して、Vodeclic プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

注

アプリケーションの要件によっては、お使いのマシンを許可リストに登録しなければならない場合があります。 そのためには、パブリック IP アドレスを [Vodeclic サポート チーム](mailto:hotline@vodeclic.com)と共有する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Vodeclic] タイルを選択すると、SSO を設定した Vodeclic に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vonage-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Vonage を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vonage-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-06
- Summary: Microsoft Entra IDから Vonage にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Vonage と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 設定済みのMicrosoft Entra IDは、Microsoft Entraプロビジョニング サービスを使用して、ユーザーとグループのプロビジョニングおよびプロビジョニング解除を[Vonage](https://www.vonage.com/)に自動的に行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Vonage でユーザーを作成する。
- アクセスが不要になったら、Vonage のユーザーを削除します。
- Microsoft Entra IDと Vonage の間でユーザー属性の同期を維持します。
- Vonage に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vonage-tutorial)します (推奨)。
- コード認証許可フロー認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- [Vonage](https://www.vonage.com/) テナント。
- アカウント管理者権限を持つVonageのユーザーアカウント（アカウントスーパーユーザー）。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとVonageの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Vonage を構成する

1. 管理ユーザーを使用して [Vonage 管理ポータル](http://admin.vonage.com) にログインします。

    [Image: vonage 管理ポータルへのログインのスクリーンショット。]
2. 左側のメニュー **の [アカウント &gt; シングル Sign-On 設定]** に移動します。

    [Image: シングル サインオン設定のスクリーンショット。]
3. [ **ユーザー設定] タブを** 選択し、[ **SCIM ユーザー プロビジョニングを有効にする** ] をオンに切り替えて、[ **保存]** を選択します。

[Image: [Scim を有効にする] のスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Vonage を追加する

Microsoft Entra アプリケーション ギャラリーから Vonage を追加して、Vonage へのプロビジョニングの管理を開始します。 SSO のために Vonage を以前に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Vonage への自動ユーザー プロビジョニングを構成する

注

Vonage に追加されるすべてのユーザーには、名、姓、電子メールが必要です。 そうでない場合、統合は失敗します。

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Vonage のユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Vonage の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Vonage** を選択します。

    [Image: アプリケーションの一覧の Vonage リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [新しい構成] のスクリーンショット。]
6. 次の手順の前に、アカウント スーパー ユーザーとして承認されていることを確認します。 ユーザーがアカウント スーパー ユーザーであるかどうかを確認するには、 [Vonage 管理ポータル](http://admin.vonage.com)でログインを実行します。 以下のような画像が左上に表示されるはずです。

    [Image: [プロビジョニング] タブのユーザーのスクリーンショット。]
7. [ **管理者の資格情報** ] セクションで、[承認] を選択し、アカウント スーパー ユーザーの資格情報を入力することを確認します。資格情報の入力が求められない場合は、アカウント スーパー ユーザーでログインしていることを確認します (左上の http://admin.vonage.com/ 確認できます。"アカウント スーパー ユーザー" と表示するために必要な名前を下に入力します)。 [**Test Connection** を選択して、Microsoft Entra IDが Vonage に接続できることを確認します。 接続に失敗した場合は、Vonage アカウントに管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: トークンのスクリーンショット。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから Vonage に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Vonage のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Vonage API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/vonage-tutorial"} -->
## Microsoft Entra ID で vonage for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vonage-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と vonage の間にシングル サインオンを構成する方法について説明します。

この記事では、vonage と Microsoft Entra ID を統合する方法について説明します。 vonage と Microsoft Entra ID を統合すると、次のことができます。

- vonage にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って vonage に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な vonage サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- vonage では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- vonage では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vonage-provisioning-tutorial)がサポートされます。

### ギャラリーからの vonage の追加

Microsoft Entra ID への vonage の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に vonage を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**vonage**」と入力します。
4. 結果パネルから **[vonage]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### vonage 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、vonage に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと vonage の関連ユーザーとの間にリンク関係を確立する必要があります。

vonage に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **vonage の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. vonage テストユーザーを作成して、vonage内でB.Simonに対応するユーザーを設け、そのユーザーをMicrosoft EntraのB.Simonにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**vonage**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `wso2is-<ENVIRONMENT>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.auth-<ENVIRONMENT>.vonage.com/accountrecoveryendpoint/saml-translator.jsp?id=<ID>&env=<ENVIRONMENT>&client=Web`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://admin.<ENVIRONMENT>.vocal-<ENVIRONMENT>.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[vonage クライアント サポート チーム](mailto:office@vonage.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[vonage のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### vonage の SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として vonage Web サイトにサインインします。
2. **[Account](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント) &gt; [Single Sign-On Settings](シングル サインオンの設定) &gt; [Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定)** の順に移動します。
3. 次のページで、以下の手順を実行します。

    [Image: [Single Sign-On Settings](シングル サインオンの設定) ページ]

    ある。 **[Enable single sign-on for this account](このアカウントのシングル サインオンを有効にする)** を選択します。

    b。 [ **エンティティ ID** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    c. **[Sign-in URL] (サインイン)** テキスト ボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    d. **[Upload Certificate] (証明書のアップロード)** に、ダウンロードした**証明書 (Base64)** ファイルをアップロードします。

#### vonage のテスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、管理者として vonage Web サイトにサインインします。
2. **[Phone System](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/電話システム) &gt; [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) &gt; [Add New](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新規追加)** の順に移動します。

    [Image: ユーザー ページを追加]
3. 次のページに必須フィールドを追加し、[ **保存]** を選択します。

    [Image: ユーザーの追加フォーム ページ]

注

vonage では、自動ユーザー プロビジョニングもサポートされています。 自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/vonage-provisioning-tutorial) 。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Azure portal で **[このアプリケーションをテスト** する] を選択します。 このオプションは、サインイン フローを開始できる vonage のサインオン URL にリダイレクトします。
- vonage のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した vonage に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで vonage タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した vonage に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/voyance-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Voyance を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/voyance-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Voyance 間にシングル サインオンを構成する方法について説明します。

この記事では、Voyance と Microsoft Entra ID を統合する方法について説明します。 Voyance と Microsoft Entra ID の統合には、次の利点があります。

- Voyance にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Voyance に自動的にサインイン (シングル サインオン) できるように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Voyance でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Voyance では、**SP** と **IDP** によって開始される SSO がサポートされます
- Voyance では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Voyance の追加

Microsoft Entra ID への Voyance の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Voyance を追加する必要があります。

**ギャラリーから Voyance を追加するには、次の手順に従います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Voyance」**と入力し、結果パネルで **Voyance** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Voyance]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** という名前のテスト ユーザーに基づいて、Voyance で Microsoft Entra シングル サインオンを構成してテストします。 シングル サインオンが機能するには、Microsoft Entra ユーザーと Voyance の関連ユーザーの間にリンク関係を確立する必要があります。

Voyance で Microsoft Entra シングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Voyance シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Voyance テストユーザーの作成** - Voyance で Britta Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra の Britta Simon の表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Voyance で Microsoft Entra シングル サインオンを構成するには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Voyance** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: スクリーンショットには、[基本的な SAML 構成] が示されています。ここで、[識別子]、[応答 URL] の順に入力し、[保存] を選択できます。]

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.nyansa.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.nyansa.com/saml/create/`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [Image: スクリーンショットには、追加のURLを設定する画面が表示されており、ここでサインオンURLを入力することができます。]

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.nyansa.com/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Voyance クライアント サポート チーム](mailto:support@nyansa.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up Voyance]**(Voyance のセットアップ) セクションで、要件のとおりに適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

    ある。 ログイン URL

    b。 Microsoft Entra 識別子

    c. ログアウト URL

#### Voyance シングル サインオンの構成

1. 別の Web ブラウザーのウィンドウで、管理者として Voyance テナントにサインオンします。
2. ナビゲーション バーの右上隅に移動し、[プロファイル] を選択 **します**。

    [Image: アプリ側でのシングル サインオンの構成: Acme University]
3. [ **管理者設定] を選択します**。

    [Image: アプリ側でのシングル サインオンの構成: 管理者設定]
4. [ **ユーザー アクセス** ] タブを選択します。

    [Image: アプリ側でのシングル サインオンの構成: ユーザー アクセス]
5. SAML 2.0 を使用して Microsoft Entra ID を IdP として構成するには、[ **SSO が無効]** ボタンを選択します。

    [Image: アプリ側でのシングル サインオンの構成: SSO は無効]
6. **SAML V2** セクションに移動して、次の手順を実行します。

    [Image: アプリ側でのシングル サインオンの構成: SAML V2]

    ある。 **[有効] を選択します**。

    b。 **ログイン URL** を **[IdP Login URL]** テキストボックスに貼り付けます。

    c. ダウンロード済みの Base64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、**[IdP 証明書]** ボックスに貼り付けます。

    d. **保存** を選択します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Voyance のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Voyance に作成します。 Voyance では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Voyance にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注

ユーザーを手動で作成する必要がある場合は、[Voyance サポート チーム](mailto:support@nyansa.com)に問い合わせてください。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Voyance] タイルを選択すると、SSO を設定した Voyance に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->
