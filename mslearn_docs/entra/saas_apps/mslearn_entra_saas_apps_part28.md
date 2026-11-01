# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 28)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 1

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/zylo-tutorial"} -->
## Microsoft Entra ID で Zylo for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/zylo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Zylo 間にシングル サインオンを構成する方法について学習します。

この記事では、Zylo と Microsoft Entra ID を統合する方法について説明します。 Zylo と Microsoft Entra ID を統合すると、次のことができます。

- Zylo にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Zylo に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Zylo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Zylo では、**SP と IDP** によって開始される SSO がサポートされます。
- Zylo では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Zylo の追加

Microsoft Entra ID への Zylo の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Zylo を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Zylo**」と入力します。
4. 結果のパネルから **[Zylo]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Zylo 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Zylo で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Zylo の関連ユーザーとの間にリンク関係を確立する必要があります。

Zylo で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Zylo の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Zylo テストユーザーを作成して、B.Simon に対応する ZYLO のユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Zylo**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.zylo.com/saml/sso/azuread/<CUSTOMER_NAME>`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.zylo.com/login`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、[Zylo クライアント サポート チーム](mailto:support@zylo.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up Zylo](Zylo のセットアップ)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Zylo の SSO の構成

1. 別のウィンドウで、Zylo Web サイトに管理者としてログインします。
2. 右上隅にある Zylo の **メニュー** を選択し、[管理者] を選択 **します**

    [Image: Zylo の構成。]
3. **[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者)** ページで、 **[Saml Info](SAML 情報)** タブにアクセスし、次の手順を実行します。

    [Image: Zylo SAML 構成。]

    ある。 **[Zylo SAML Configuration](Zylo SAML 構成)** を **[オン]** に変更します。

    b。 **[ID プロバイダー]** ドロップダウンから **[Microsoft Entra ID]** を選択します。

    c. **[SAML SSO URL]** テキストボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    d. **[ID プロバイダー発行者]** ボックスに、Azure portal の Zylo の概要ページからコピーした**アプリケーション ID** の値を貼り付けます。

    え ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[公開証明書 (ID プロバイダーから)]** テキストボックスに貼り付けます。

    f. **保存** を選択します。

#### Zylo テストユーザーを作成する

このセクションでは、B. Simon というユーザーを Zylo に作成します。 Zylo では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Zylo にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Zylo のサインオン URL にリダイレクトされます。
- Zylo のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Zylo に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Zylo] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Zylo に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->
