# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 4)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 76

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blue-ocean-brain-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Blue Ocean Brain を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blue-ocean-brain-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Blue Ocean Brain の間のシングル サインオンを構成する方法について説明します。

この記事では、Blue Ocean Brain と Microsoft Entra ID を統合する方法について説明します。 Blue Ocean Brain を Microsoft Entra ID と統合すると、次のことができます。

- Blue Ocean Brain にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Blue Ocean Brain に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Blue Ocean Brain のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Blue Ocean Brain は **、SP と IDP** によって開始される SSO をサポートしています。
- Blue Ocean Brain では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Blue Ocean Brain の追加

Microsoft Entra ID への Blue Ocean Brain の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Blue Ocean Brain を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [**ギャラリーから追加する**] セクションで、検索ボックスに「**Blue Ocean Brain**」と入力します。
4. 結果パネルで [**Blue Ocean Brain**] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Blue Ocean Brain に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Blue Ocean Brain に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Blue Ocean Brain での関連ユーザーとの間にリンク関係を確立する必要があります。

Blue Ocean Brain に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Blue Ocean Brain SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Blue Ocean Brain のテスト ユーザーの作成** - Blue Ocean Brain で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Blue Ocean Brain]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www3.blueoceanbrain.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www3.blueoceanbrain.com/c/<friendly id>/saml/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www3.blueoceanbrain.com/c/<friendly id>/login`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Blue Ocean Brain クライアントサポートチーム](mailto:support@blueoceanbrain.com) に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Blue Ocean Brain アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Blue Ocean Brain アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
    | Email | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Blue Ocean Brain SSO の構成

**Blue Ocean Brain** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Blue Ocean Brain サポート チーム](mailto:support@blueoceanbrain.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Blue Ocean Brain テストユーザーの作成

このセクションでは、Britta Simon というユーザーを Blue Ocean Brain 内に作成します。 Blue Ocean Brain では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Blue Ocean Brain にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Blue Ocean Brain Sign on URL にリダイレクトされます。
- Blue Ocean Brain のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Blue Ocean Brain に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Blue Ocean Brain] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Blue Ocean Brain に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/blueconic-tutorial"} -->
## Microsoft Entra ID で BlueConic for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/blueconic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BlueConic 間にシングル サインオンを構成する方法について説明します。

この記事では、BlueConic と Microsoft Entra ID を統合する方法について説明します。 BlueConic は、顧客との関係を変革し、成長を引き出すことを目指すビジネス チームに、統合されたプライバシー準拠のファースト パーティ データを提供する顧客データ プラットフォーム (CDP) です。 BlueConic を Microsoft Entra ID と統合すると、次のことができます。

- BlueConic にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して BlueConic に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で BlueConic 向けの Microsoft Entra のシングル サインオンを構成してテストします。 BlueConic では、 **IDP** によって開始されるシングル サインオンがサポートされます。

### 前提条件

Microsoft Entra ID を BlueConic と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- BlueConic でのシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから BlueConic アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから BlueConic を追加する

Microsoft Entra アプリケーション ギャラリーから BlueConic を追加して、BlueConic でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**BlueConic**&gt;**シングルサインオン**を参照してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.blueconic.net/saml/metadata`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.blueconic.net/saml/acs`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、BlueConic サポート チーム](mailto:support@blueconic.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **BlueConic のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### BlueConic の SSO を構成する

**BlueConic** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [BlueConic サポート チーム](mailto:support@blueconic.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### BlueConic のテスト ユーザーを作成する

このセクションでは、BlueConic で Britta Simon というユーザーを作成します。 [BlueConic サポート チーム](mailto:support@blueconic.com)と協力して、BlueConic プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BlueConic に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [BlueConic] タイルを選択すると、SSO を設定した BlueConic に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bomgarremotesupport-tutorial"} -->
## Microsoft Entra ID で BeyondTrust Remote Support for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bomgarremotesupport-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BeyondTrust Remote Support の間でシングル サインオンを構成する方法について説明します。

この記事では、BeyondTrust Remote Support と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と BeyondTrust Remote Support を統合すると、次のことができます。

- BeyondTrust Remote Support にアクセスするユーザーを Microsoft Entra ID で制御できる。
- ユーザーが自分の Microsoft Entra アカウントを使用して BeyondTrust Remote Support に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BeyondTrust Remote Support でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BeyondTrust Remote Support では、**SP** によって開始される SSO がサポートされます
- BeyondTrust Remote Support では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーから BeyondTrust Remote Support を追加する

Microsoft Entra ID への BeyondTrust Remote Support の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に BeyondTrust Remote Support を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**BeyondTrust Remote Support**」と入力します。
4. 結果のパネルから **[BeyondTrust Remote Support]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BeyondTrust Remote Support に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、BeyondTrust Remote Support に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと BeyondTrust Remote Support での関連ユーザーとの間にリンク関係を確立する必要があります。

BeyondTrust Remote Support に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BeyondTrust Remote Support の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **BeyondTrust Remote Support のテストユーザーを作成** - B.Simon に対応するユーザーを BeyondTrust Remote Support で作成し、それを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[BeyondTrust Remote Support]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. **[識別子]** ボックスに、`https://<HOSTNAME>.bomgar.com` という形式で URL を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<HOSTNAME>.bomgar.com/saml/sso`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<HOSTNAME>.bomgar.com/saml`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、Sign-On URL でこれらの値を更新します。 これらの値については、この記事の後半で説明します。
6. BeyondTrust Remote Support アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、BeyondTrust Remote Support アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザー名 | user.userprincipalname |
    | ファーストネーム | User.givenname |
    | LastName | ユーザーの名字 |
    | 電子メール | ユーザーのメールアドレス |
    | グループ | ユーザー.グループ |

    注

    BeyondTrust Remote Support アプリケーションに Microsoft Entra グループを割り当てる場合、[Groups returned in claim] (要求で返されるグループ) オプションを "None" から "SecurityGroup" に変更する必要があります。 グループは、オブジェクト ID としてアプリケーションにインポートされます。 Microsoft Entra グループのオブジェクト ID を見つけるには、Microsoft Entra ID インターフェイスで [プロパティ] を確認します。 これは、Microsoft Entra グループを参照し、適切なグループ ポリシーに割り当てるために必要です。
8. 一意のユーザー ID を設定するときは、この値を次の NameID の形式に設定する必要があります: **[永続的]** 。 ユーザーを正しく識別し、アクセス許可の適切なグループ ポリシーに関連付けるために、これを永続的な識別子にする必要があります。 [編集] アイコンを選択して[ **ユーザー属性と要求** ] ダイアログを開き、[一意のユーザー識別子] の値を編集します。
9. [ **要求の管理** ] セクションで、[ **名前識別子の選択] 形式を選択** し、値を **[永続的]** に設定し、[ **保存]** を選択します。

    [Image: ユーザー属性と要求]
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[BeyondTrust Remote Support のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BeyondTrust Remote Support の SSO の構成

1. 別の Web ブラウザーのウィンドウで、BeyondTrust Remote Support に管理者としてサインインします。
2. **[ユーザー] と [セキュリティ**&gt;**セキュリティ プロバイダー**] に移動します。
3. **SAML プロバイダー**の **[編集]** アイコンを選択します。

    [Image: SAML プロバイダーの編集アイコン]
4. **[Service Provider Settings](サービス プロバイダーの設定)** セクションを展開します。
5. [ **サービス プロバイダー メタデータのダウンロード** ] を選択するか、 **エンティティ ID** と **ACS URL** の値をコピーし **、[基本的な SAML 構成]** セクションでこれらの値を使用できます。

    [Image: サービス プロバイダーのメタデータのダウンロード]
6. [ID プロバイダーの設定] セクションで、[ **ID プロバイダー メタデータのアップロード** ] を選択し、ダウンロードしたメタデータ XML ファイルを見つけます。
7. **[Entity ID](エンティティ ID)** 、 **[Single Sign-On Service URL](シングル サインオン サービス URL)** 、 **[Server Certificate](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サーバー証明書)** は自動的にアップロードされます。 **[SSO URL Protocol Binding](SSO URL プロトコル バインド)** は **[HTTP POST]** に変更する必要があります。

    [Image: スクリーンショットは、これらのアクションを実行する [Identity Provider Settings](ID プロバイダーの設定) セクションを示しています。]
8. **保存** を選択します。

#### BeyondTrust Remote Support のテスト ユーザーを作成する

このセクションでは、Britta Simon というユーザーを BeyondTrust Remote Support に作成します。 BeyondTrust Remote Support では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションにはアクション項目はありません。 BeyondTrust Remote Support にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

下の手順に従います。これは、BeyondTrust Remote Support を構成するために必須です。

ここでは、ユーザー プロビジョニング設定を構成しています。 このセクションで使用される値は、[ **ユーザー属性] および [要求** ] セクションから参照されます。 これは、作成時に既にインポートされている既定値に構成しましたが、必要に応じて値をカスタマイズできます。

[Image: スクリーンショットは、ユーザーの値を構成できる [User Provision Settings](ユーザー プロビジョニング設定) を示しています。]

注

この実装には、グループと電子メール属性は必要ありません。 Microsoft Entra グループを利用して、それらをアクセス許可の BeyondTrust Remote Support グループ ポリシーに割り当てる場合は、グループのオブジェクト ID を Azure portal のそのプロパティを使用して参照し、[Available Groups] (使用可能なグループ) セクションに配置する必要があります。 これが完了すると、オブジェクト ID/AD グループがアクセス許可のグループ ポリシーへの割り当てに使用できるようになります。

[Image: スクリーンショットは、[Membership type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/メンバーシップの種類)、[Source](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ソース)、[Type](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/種類)、および [Object ID](オブジェクト ID) を含む [IT] セクションを示しています。]

[Image: スクリーンショットは、グループ ポリシーの [Basic Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/基本設定) ページを示しています。]

注

または、SAML2 セキュリティ プロバイダーで既定のグループ ポリシーを設定することもできます。 このオプションを定義することで、SAML 経由で認証を行うすべてのユーザーに、グループ ポリシー内で指定されたアクセス許可が割り当てられます。 General Members ポリシーは、アクセス許可が制限されている BeyondTrust Remote Support/Privileged Remote Access に含まれており、認証をテストしてユーザーを適切なポリシーに割り当てるために使用できます。 認証が最初に成功するまで、ユーザーは /login &gt; Users > Security を使用して SAML2 Users リストに入力されません。 グループ ポリシーに関する追加情報については、リンク `https://www.beyondtrust.com/docs/remote-support/getting-started/admin/group-policies.htm` を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる BeyondTrust Remote Support のサインオン URL にリダイレクトされます。
- BeyondTrust Remote Support のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [BeyondTrust Remote Support] タイルを選択すると、このオプションは BeyondTrust Remote Support のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bonos-tutorial"} -->
## Microsoft Entra ID で Bonos for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bonos-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Bonos 間のシングル サインオンを構成する方法について説明します。

この記事では、Bonos と Microsoft Entra ID を統合する方法について説明します。 Bonos を Microsoft Entra ID と統合すると、次のことが可能になります。

- Bonos へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Bonos に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Bonos のサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Bonos では、**SP および IDP** initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Bonos の追加

Microsoft Entra ID への Bonos の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Bonos を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Bonos**」と入力します。
4. 結果パネルから **[Bonos]** を選択して、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bonos に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Bonos に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Bonos の関連ユーザーとの間にリンク関係を確立する必要があります。

Bonos に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bonos SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bonos のテスト ユーザーの作成** - Bonos で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Bonos]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.bonos.io/login`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.bonos.io/login`

    注

    これらの値は実際の値ではありません。 実際の応答 URL と Sign-On URL でこれらの値を更新します。 これらの値を取得するには、[Bonos クライアント サポート チーム](mailto:support@bonos.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Bonos のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bonos SSO の構成

**Bonos** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Bonos サポート チーム](mailto:support@bonos.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Bonos テスト ユーザーの作成

このセクションでは、Bonos で Britta Simon というユーザーを作成します。 [Bonos サポート チーム](mailto:support@bonos.io)と連携し、Bonos プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Bonos のサインオン URL にリダイレクトされます。
- Bonos のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Bonos に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Bonos] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Bonos に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bonus-tutorial"} -->
## Microsoft Entra ID で Bonusly for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bonus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Bonusly の間のシングル サインオンを構成する方法について説明します。

この記事では、Bonusly と Microsoft Entra ID を統合する方法について説明します。 Bonusly を Microsoft Entra ID と統合すると、以下のことが可能になります。

- 誰が Bonusly にアクセスできるかを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Bonusly に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bonusly でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Bonusly では、**IDP** によって開始される SSO がサポートされます。
- Bonusly では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bonusly-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Bonusly の追加

Bonusly の Microsoft Entra ID への統合を構成するには、Bonusly をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Bonusly**」と入力します。
4. 結果のパネルから **[Bonusly]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bonusly の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Bonusly での Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Bonusly の関連ユーザーとの間にリンク関係を確立する必要があります。

Bonusly での Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bonusly SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bonusly テスト ユーザーの作成** - Bonusly で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーと連携させます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Bonusly**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://Bonus.ly/saml/<TENANT_NAME>`

    注

    これは実際の値ではありません。 実際の応答 URL で値を更新します。 この値を取得するには、[Bonusly クライアント サポート チーム](https://bonus.ly/contact)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. [ **SAML 署名証明書** ] セクションで、 **拇印** をコピーしてコンピューターに保存します。

    [Image: 拇印の値をコピーする]
8. **[Bonusly のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bonusly SSO の構成

1. 別のブラウザー ウィンドウで、**Bonusly** テナントにサインインします。
2. 上部のツール バーで、[ **設定]** を選択し、[ **統合とアプリ**] を選択します。

    [Image: Bonusly ソーシャル セクション]
3. **[Single Sign-On]** の **[SAML]** を選択します。
4. **[SAML]** ダイアログ ページで、次の手順を実行します。

    [Image: Bonusly [SAML] ダイアログ ページ]

    ある。 **[IdP SSO ターゲット URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    b。 **[IdP ログイン URL]** テキストボックスに **[ログイン URL]** の値を貼り付けます。

    c. **[IdP 発行者]** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    d. **[証明書のフィンガープリント]** テキストボックスに**拇印**の値を貼り付けます。

    え **保存** を選択します。

#### Bonusly のテスト ユーザーの作成

Microsoft Entra ユーザーが Bonusly にサインインできるようにするには、ユーザーを Bonusly にプロビジョニングする必要があります。 Bonusly の場合、プロビジョニングは手動で行います。

注

他の Bonusly ユーザー アカウント作成ツールや、Bonusly によって提供される API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

**ユーザー プロビジョニングを構成するには、次の手順を実行します。**

1. Web ブラウザー ウィンドウで、Bonusly テナントにサインインします。
2. **設定**を選択します。

    [Image: 設定]
3. [ **ユーザーとボーナス** ] タブを選択します。

    [Image: ユーザーとボーナス]
4. [ **ユーザーの管理]** を選択します。

    [Image: ユーザーの管理]
5. [ **ユーザーの追加] を選択します**。

    [Image: スクリーンショットは、[Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) を選択できる [Manage Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの管理) を示しています。]
6. [ **ユーザーの追加** ] ダイアログで、次の手順を実行します。

    [Image: スクリーンショットは、この情報を入力できる [Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ダイアログ ボックスを示しています。]

    ある。 [ **名** ] ボックスに、 **Britta** などのユーザーの名を入力します。

    b。 **[Last name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (この例では **Simon**)。

    c. [ **電子メール** ] ボックスに、ユーザーの電子メール ( `brittasimon@contoso.com`など) を入力します。

    d. **保存** を選択します。

    注

    Microsoft Entra アカウント所有者は、アカウントがアクティブになる前に、アカウント確認用のリンクを含むメールを受け取ります。

注

Bonusly では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bonusly-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Bonusly に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Bonusly タイルを選択すると、SSO を設定した Bonusly に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bonusly-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Bonusly を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bonusly-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-02
- Summary: ユーザー アカウントを Bonusly に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Bonusly に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するために Bonusly とMicrosoft Entra IDで実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次のものが既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Bonusly テナント](https://bonus.ly/pricing)
- 管理者アクセス許可がある Bonusly のユーザー アカウント

注

Microsoft Entra プロビジョニング統合は、Bonusly 開発者が利用できる [Bonusly REST API](https://konghq.com/solutions/gateway/) に依存しています。

### ギャラリーからの Bonusly の追加

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Bonusly を構成する前に、Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に Bonusly を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Bonusly を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Bonusly**」と入力し、結果パネルで **Bonusly** を選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Bonusly]

### Bonusly へのユーザーの割り当て

Microsoft Entra IDでは、"割り当て" という概念を使用して、選択したアプリにaccessを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに "割り当てられている" ユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Microsoft Entra IDのどのユーザーやグループがBonuslyにアクセスする必要があるかを決定してください。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Bonusly に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Bonusly に割り当てる際の重要なヒント

- 1 人の Microsoft Entra ユーザーを Bonusly に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Bonusly にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **Default Access** ロールを持つユーザーは、プロビジョニングから除外されます。

### Bonusly への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID のユーザーまたはグループの割り当てに基づいて Bonusly でユーザーやグループを作成、更新、無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順をあなたに説明します。

ヒント

Bonusly の SAML ベースのシングル サインオンを有効にすることもできます。これは、 [Bonusly シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bonus-tutorial)に関する記事に記載されている手順に従って行うこともできます。 シングル サインオンは自動ユーザー プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### Microsoft Entra IDで Bonusly の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Bonusly**を参照します。

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **Bonusly** を選択します。

    [Image: アプリケーションの一覧の Bonusly リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [Bonusly - プロビジョニング] タブのスクリーンショット。[管理] の下で、[プロビジョニング] が強調表示されています。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、手順 6 で説明されているように Bonusly アカウントの **シークレット トークン** を入力します。

    [Image: [管理者資格情報] セクションのスクリーンショット。[シークレット トークン] ボックスは空ですが、ボックスが強調表示されています。]
7. Bonusly アカウントの **シークレット トークン** は **、Admin &gt; Company &gt; Integrations** にあります。 **コードを作成する場合**セクションで、**API &gt; Create New API Access Token** を選択して新しいシークレット トークンを作成します。

    [Image: Bonusly メニューのスクリーンショット。[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者) の下の [Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社) が強調表示されています。[Company](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社) の下の [Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/統合) が強調表示されています。]

    [Image: [Bonusly] サイトの [コードを作成する場合] セクションのスクリーンショット。[A P I] が強調表示されています。]

    [Image: Bonuslyサイトのスクリーンショット。「サービス」タブが開いています。「Your API access tokens」の「Create new API access token」が強調表示されています。]
8. 次の画面で、指定されたテキスト ボックスにaccess トークンの名前を入力し、**Create Api Key** キーを押します。 新しいaccess トークンがポップアップに数秒表示されます。

    [Image: Bonusly サイトの [新しいaccess トークン] ページのスクリーンショット。ラベルのないボックスにマイ トークンが含まれており、[Create A P I key](P I キーの作成) ボタンが強調表示されています。]

    [Image: Bonusly サイトのスクリーンショット。新しいアクセス トークンが作成され、それに続いて解読できないトークンが表示されている通知が表示されます。]
9. 手順 5 に示すフィールドに値を入力したら、**Test Connection** を選択して、Microsoft Entra IDが Bonusly に接続できることを確認します。 接続できない場合は、使用中の Bonusly アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: [管理者資格情報] セクションのスクリーンショット。[テキスト接続] ボタンが強調表示されています。]
10. [ **作成]** を選択して構成を作成します。
11. [**概要**] ページで **[プロパティ**] を選択します。
12. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
13. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
14. **Attribute Mapping** セクションで、Microsoft Entra IDから Bonusly に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Bonusly のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: [属性マッピング] ページのスクリーンショット。テーブルには、Microsoft Entra 属性、対応する Bonusly 属性、および一致する状態が一覧表示されます。]
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/boomi-tutorial"} -->
## Microsoft Entra ID で Boomi for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/boomi-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Boomi 間のシングル サインオンを構成する方法について説明します。

この記事では、Boomi と Microsoft Entra ID を統合する方法について説明します。 Boomi を Microsoft Entra ID と統合すると、次のことができます。

- Boomi にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Boomi に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Boomi でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Boomi では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーからの Boomi の追加

Microsoft Entra ID への Boomi の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Boomi を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Boomi**」と入力します。
4. 結果パネルから **Boomi** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Boomi に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Boomi に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Boomi での関連ユーザーとの間にリンク関係を確立する必要があります。

Boomi に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Boomi SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Boomi のテスト ユーザーの作成** - Boomi で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、これらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Boomi]**&gt;**[Single sign-on](シングル サインオン)** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** があり、 **IDP** 開始モードで構成する場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    d. などの`https://platform.boomi.com/AtomSphere.html#build;accountId={your-accountId}`入力します。

    注

    **サービス プロバイダーのメタデータ ファイル**は、「**Boomi SSO の構成**」セクションから取得します。これについては、この記事の後半で説明します。 **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. Boomi アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、Givenname user.givenname や Emailaddress User.mail などの既定値を持つユーザー属性と要求を示しています。]
7. その他に、Boomi アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | FEDERATION\_ID | ユーザーのメールアドレス |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Boomi のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Boomi SSO の構成

1. 別の Web ブラウザー ウィンドウで、Boomi 企業サイトに管理者としてサインインします。
2. **[設定]** に移動し、セキュリティ **オプションで SSO オプション**を選択し、次の手順を実行します。

    [Image: アプリ側でシングル Sign-On を構成する]

    a. **SAML シングル サインオンを有効にする** で **有効** を選択します。

    b。 [ **インポート]** を選択して、Microsoft Entra ID からダウンロードした証明書を **ID プロバイダー証明書にアップロードします**。

    c. **[Identity Provider Sign In URL]\(ID プロバイダーのサインイン URL\**) ボックスに、Microsoft Entra アプリケーション構成ウィンドウの**ログイン URL** の値を貼り付けます。

    d. **[Federation Id Location]** で、**[Federation Id is in FEDERATION\_ID Attribute element]** オプションを選択します。

    e. **[SAML 認証コンテキスト**] で、[**パスワードで保護されたトランスポート**] ラジオ ボタンを選択します。

    f. **AtomSphere のサインイン URL を**コピーし、この値を [**基本的な SAML 構成**] セクションの **[サインオン URL**] テキスト ボックスに貼り付けます。

    g. **AtomSphere MetaData URL を**コピーし、任意のブラウザーから **MetaData URL** に移動し、出力をファイルに保存します。 **[基本的な SAML 構成]** セクションで **MetaData URL を**アップロードします。

    h. [ **保存] ボタンを** 選択します。

#### Boomi のテスト ユーザーの作成

Microsoft Entra ユーザーが Boomi にサインインできるようにするには、ユーザーを Boomi にプロビジョニングする必要があります。 Boomi の場合、プロビジョニングは手動で行います。

#### ユーザー アカウントをプロビジョニングするには、次の手順を実行します。

1. Boomi 企業サイトに管理者としてサインインします。
2. ログイン後、 **ユーザー管理** -&gt;**Users** に移動します。
3. **+**アイコンを選択すると、[**ユーザー ロールの追加/管理**] ダイアログが開きます。

    [Image: [+] アイコンが選択されているスクリーンショット。]

    a. [ **ユーザーの電子メール アドレス** ] ボックスに、ユーザーの電子メール ( B.Simon@contoso.comなど) を入力します。

    b。 **[名]** ボックスに、ユーザーの名を入力します (この例では B)。

    c. [ **姓** ] ボックスに、ユーザーの姓 (Simon など) を入力します。

    d. ユーザーの **フェデレーション ID を入力します**。 各ユーザーには、アカウント内のユーザーを一意に識別するフェデレーション ID が必要です。

    e. **標準ユーザー** ロールをユーザーに割り当てます。 管理者ロールを割り当てると、通常のAtmosphereアクセスとシングルサインオンアクセスが提供されてしまうため、それをしないでください。

    f. [ **OK] を選択します**。

    注

    ユーザーは、パスワードが ID プロバイダーを介して管理されるため、AtomSphere アカウントへのログインに使用できるパスワードを含むウェルカム通知メールを受け取りません。 他の Boomi ユーザー アカウント作成ツールや、Boomi から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Boomi に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで Boomi タイルを選択すると、SSO を設定した Boomi に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/borrowbox-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に BorrowBox を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/borrowbox-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BorrowBox の間のシングル サインオンを構成する方法について説明します。

この記事では、BorrowBox と Microsoft Entra ID を統合する方法について説明します。 BorrowBox と Microsoft Entra ID を統合すると、次のことができます。

- BorrowBox にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って BorrowBox に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BorrowBox のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- BorrowBox では、**SP および IDP** による SSO がサポートされます。
- BorrowBox では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから BorrowBox を追加する

Microsoft Entra ID への BorrowBox の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に BorrowBox を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「BorrowBox**」と入力します。
4. 結果パネルから **BorrowBox** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BorrowBox 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、BorrowBox に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと BorrowBox の関連ユーザーとの間にリンク関係を確立する必要があります。

BorrowBox に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BorrowBox SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **BorrowBox テスト ユーザーの作成** - BorrowBox で B.Simon に対応するユーザーアカウントを作成し、そのユーザーを Microsoft Entra のユーザーとしてリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**BorrowBox**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://fe.bolindadigital.com/wldcs_bol_fo/b2i/mainPage.html?b2bSite=<ID>`

    注意

    これは実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 値を取得するには [、BorrowBox クライアント サポート チーム](mailto:borrowbox@bolinda.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. BorrowBox アプリケーションでは、特定の形式の SAML アサーションを想定するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、 **nameidentifier** は **user.userprincipalname** にマップされています。 BorrowBox アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集]** アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **BorrowBox のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BorrowBox の SSO を構成する

**BorrowBox** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [BorrowBox サポート チーム](mailto:borrowbox@bolinda.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### BorrowBox テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを BorrowBox 内に作成します。 BorrowBox では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 BorrowBox にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

注意

ユーザーを手動で作成する必要がある場合は、 [BorrowBox サポート チーム](mailto:borrowbox@bolinda.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる BorrowBox のサインオン URL にリダイレクトされます。
- BorrowBox のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BorrowBox に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [BorrowBox] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した BorrowBox に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/box-tutorial"} -->
## Microsoft Entra ID で Box for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/box-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Box 間にシングル サインオンを構成する方法について説明します。

この記事では、Box と Microsoft Entra ID を統合する方法について説明します。 Box と Microsoft Entra ID を統合すると、次のことができます。

- Box にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Box に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

Box は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- Box でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Box では、 **SP** Initiated SSO がサポートされます
- Box では、 [**自動** ユーザー プロビジョニングとプロビジョニング解除がサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/box-userprovisioning-tutorial) (推奨)
- Box では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからボックスを追加

Microsoft Entra ID への Box の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Box を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Box**」と入力します。
4. 結果パネルから **Box** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Box 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Box に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Box の関連ユーザーとの間にリンク関係を確立する必要があります。

Box に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Box SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Box テスト ユーザーの作成** - Box で B.Simon に対応するユーザーを作成し、Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Box**&gt;**シングルサインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.account.box.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `box.net`

    c. [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://sso.services.box.net/sp/ACS.saml2`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL でこの値を更新してください。 この値を取得するには、 [Box クライアント サポート チーム](https://support.box.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Box アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、Box はこれがユーザーのメール アドレスにマップされることを想定しています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Box SSO を設定

1. 別の Web ブラウザー ウィンドウで、Box 企業サイトに管理者としてサインインし、「 [SSO を自分で設定](https://support.box.com)する」の手順に従います。

注

Box アカウントの SSO 設定を構成できない場合は、ダウンロードした **フェデレーション メタデータ XML** を [Box サポート チーム](https://support.box.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Box テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Box に作成します。 Box では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Box にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Box サポート チーム](https://support.box.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択します。 Box のサインオン URL にリダイレクトされ、ログイン フローを開始することができます。
- Box のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Box] タイルを選択すると、このオプションは Box のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

#### Azure のグループを Box にプッシュする

Azure のグループを Box にプッシュし、そのグループを同期することができます。 Azure のグループは、API レベルの統合によって Box にプッシュされます。

1. [ **ユーザーとグループ] で**、Box に割り当てるグループを検索します。
2. **[プロビジョニング]** で、[**Synchronize Microsoft Entra groups to Box]\(Microsoft Entra グループを Box に同期する**\) が選択されていることを確認します。 前の手順で割り当てたグループが、この設定によって同期されます。 これらのグループが Azure からプッシュされるには、時間がかかる場合があります。

注

ユーザーを手動で作成する必要がある場合は、 [Box サポート チーム](https://support.box.com)にお問い合わせください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/box-userprovisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Box を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/box-userprovisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-02
- Summary: Microsoft Entra ID と Box の間でシングル サインオンを構成する方法について説明します。

この記事の目的は、Microsoft Entra IDから Box にユーザーアカウントを自動的にプロビジョニングおよびプロビジョニング解除する手順を示すことです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

Box は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

Box との統合Microsoft Entra構成するには、次のものが必要です。

- Microsoft Entra テナント
- ボックス ビジネス プランまたは進化版

注

この記事の手順をテストするときは、運用環境を使用 *しないことを* お勧めします。

注

最初に Box アプリケーションでアプリを有効にする必要があります。

この記事の手順をテストするには、次の推奨事項に従います。

- 必要な場合を除き、運用環境を使用しないでください。
- Microsoft Entra試用版環境がない場合は、[1 か月の試用版](https://azure.microsoft.com/pricing/free-trial/)を取得できます。

### 手順 1: Box にユーザーを割り当てる

Microsoft Entra IDでは、"割り当て" という概念を使用して、選択したアプリへのアクセスを受け取るユーザーを決定します。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに "割り当てられている" ユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、Box アプリにアクセスする必要があるユーザーを表すMicrosoft Entra ID内のユーザーやグループを決定する必要があります。 決定し終えたら、次の手順でこれらのユーザーを Box アプリに割り当てることができます。

[エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### 手順 2: ユーザーとグループを割り当てる

Azure ポータルの **Box &gt; Users and Groups** タブでは、Box へのアクセスを許可するユーザーとグループを指定できます。 ユーザーまたはグループを割り当てると、次の処理が実行されます。

- Microsoft Entra IDは、割り当てられたユーザー (直接割り当てまたはグループ メンバーシップ) が Box に対する認証を許可します。 ユーザーが割り当てられていない場合、Microsoft Entra IDは Box へのサインインを許可せず、Microsoft Entraサインイン ページでエラーを返します。
- Box のアプリ タイルがユーザーの [アプリケーション起動ツール](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/end-user-experiences)に追加されます。
- 自動プロビジョニングが有効になっている場合、割り当てられたユーザーまたはグループはプロビジョニング キューに追加され、自動的にプロビジョニングされます。

    - ユーザー オブジェクトのみをプロビジョニングするよう構成した場合は、直接割り当てられたすべてのユーザーがプロビジョニング キューに配置され、さらに、割り当てられたグループのメンバーであるユーザーもすべてプロビジョニング キューに配置されます。
    - グループ オブジェクトをプロビジョニングするよう構成した場合は、割り当てられたすべてのグループ オブジェクトと、それらのグループのメンバーであるユーザーもすべて Box にプロビジョニングされます。 Box への書き込み時に、グループとユーザーのメンバーシップは保持されます。

**Attributes &gt; シングル サインオン** タブを使用して、SAML ベースの認証時に Box に表示されるユーザー属性 (または要求) を構成できます。 および **Attributes &gt; Provisioning** タブを使用して、プロビジョニング操作中にユーザー属性とグループ属性が Microsoft Entra ID から Box に流れる方法を構成します。

#### ユーザーを Box に割り当てる際の重要なヒント

- 単一のMicrosoft Entra ユーザーを Box に割り当ててプロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Box にユーザーを割り当てるときに、有効なユーザー ロールを選択する必要があります。 "既定のAccess" ロールは、プロビジョニングでは機能しません。

### 手順 3: 自動ユーザー プロビジョニングを有効にする

このセクションでは、Microsoft Entra IDを Box のユーザー アカウント プロビジョニング API に接続し、Microsoft Entra IDのユーザーとグループの割り当てに基づいて、Box で割り当てられたユーザー アカウントを作成、更新、無効化するようにプロビジョニング サービスを構成する方法について説明します。

自動プロビジョニングが有効になっている場合、割り当てられたユーザーまたはグループはプロビジョニング キューに追加され、自動的にプロビジョニングされます。

- ユーザー オブジェクトのみがプロビジョニングされるように構成した場合は、直接割り当てられたすべてのユーザーがプロビジョニング キューに配置され、さらに、割り当てられたグループのメンバーであるユーザーもすべてプロビジョニング キューに配置されます。
- グループ オブジェクトをプロビジョニングするよう構成した場合は、割り当てられたすべてのグループ オブジェクトと、それらのグループのメンバーであるユーザーもすべて Box にプロビジョニングされます。 Box への書き込み時に、グループとユーザーのメンバーシップは保持されます。

ヒント

また、[Azure ポータル](https://portal.azure.com)に記載されている手順に従って、Box に対して SAML ベースの単一 Sign-On を有効にすることもできます。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。

#### 自動ユーザー アカウント プロビジョニングを構成する

このセクションの目的は、Box へのActive Directoryユーザー アカウントのプロビジョニングを有効にする方法について説明することです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。
3. シングル サインオンのために Box を既に構成している場合は、検索フィールドで Box のインスタンスを検索します。 それ以外の場合は、[ **追加]** を選択し、アプリケーション ギャラリーで **Box** を検索します。 検索結果から Box を選択してアプリケーションの一覧に追加します。
4. Box のインスタンスを選択し、[プロビジョニング] タブ **を** 選択します。
5. [ **+ 新しい構成**] を選択します。

    [Image: ボックス内の新しい構成手順のスクリーンショット。]
6. [ **管理者資格情報** ] セクションで、[ **承認** ] を選択して、新しいブラウザー ウィンドウで Box ログイン ダイアログを開きます。
7. 「ログインして Box へのアクセス権を付与」ページで、必要な資格情報を入力し、認証 を選択します。

    [Image: ボックス画面へアクセスを許可するためのログイン画面のスクリーンショット。電子メールとパスワードの入力フィールド、および[承認] ボタンが表示されています。]
8. ** Box** へのアクセス許可を選択して、この操作を承認し、Azure ポータルに戻ります。

    [Image: Box の承認access画面のスクリーンショット。説明メッセージと [Box へのaccessの許可] ボタンが表示されます。]
9. **Test Connection** を選択して、Microsoft Entra IDが Box アプリに接続できることを確認します。 接続に失敗した場合は、Box アカウントにチーム管理者のアクセス許可があることを確認し、もう一度 **"承認"** 手順を試してください。
10. [ **作成]** を選択して構成を作成します。
11. [**概要**] ページで **[プロパティ**] を選択します。
12. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
13. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
14. **Attribute Mappings** セクションで、Microsoft Entra IDから Box に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Box のユーザー アカウントとの照合に使用されます。 [保存] ボタンをクリックして変更をコミットします。
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

Microsoft Entra プロビジョニング ログを読み取る方法の詳細については、「[自動ユーザー アカウント プロビジョニングに関するレポート](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/check-status-user-account-provisioning)を参照してください。

Box テナントでは、同期されたユーザーが**管理コンソール**の **[管理対象ユーザー**] の下に一覧表示されます。

[Image: 統合ステータス]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/boxcryptor-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Boxcryptor を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/boxcryptor-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-02
- Summary: Microsoft Entra IDから Boxcryptor にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Boxcryptor とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成済みの場合、Microsoft Entra プロビジョニング サービスを使用して、Microsoft Entra ID が [Boxcryptor](https://www.boxcryptor.com) に対してユーザーとグループの自動プロビジョニングおよび解除を行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Boxcryptor でユーザーを作成する
- accessが不要になった場合に Boxcryptor のユーザーを削除する
- Microsoft Entra IDと Boxcryptor の間でユーザー属性の同期を維持する
- Boxcryptor にグループとグループ メンバーシップをプロビジョニングする
- Boxcryptor への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/boxcryptor-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Boxcryptor でのシングル サインオンが有効な [サブスクリプション](https://www.boxcryptor.com)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとBoxcryptorの間でマッピングするデータを選択します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Boxcryptor を構成する

Boxcryptor でプロビジョニングを構成するには、Boxcryptor アカウント マネージャーまたは [Boxcryptor サポート チーム](mailto:support@boxcryptor.com) に連絡し、Boxcryptor でプロビジョニングを有効にし、Boxcryptor テナント URL とシークレット トークンを使用して連絡してください。 これらの値は、Boxcryptor アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Boxcryptor を追加する

Microsoft Entra アプリケーション ギャラリーから Boxcryptor を追加して、Boxcryptor へのプロビジョニングの管理を開始します。 以前に Boxcryptor を SSO 用に設定している場合は、その同じアプリケーションを使用することができます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Boxcryptor への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づき、TestApp においてユーザーやグループを作成、更新、または無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Boxcryptor の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **Boxcryptor** を選択します。

    [Image: アプリケーションの一覧の Boxcryptor のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Boxcryptor テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Boxcryptor に接続できることを確認します。 接続に失敗した場合は、Boxcryptor アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Boxcryptor に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Boxcryptor のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Boxcryptor API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | externalId | 糸 |  |
    | addresses[type eq "work"].country | 糸 |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Boxcryptor に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Boxcryptor のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/boxcryptor-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Boxcryptor を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/boxcryptor-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Boxcryptor の間でシングル サインオンを構成する方法について説明します。

この記事では、Boxcryptor と Microsoft Entra ID を統合する方法について説明します。 Boxcryptor と Microsoft Entra ID を統合すると、次のことができます。

- Boxcryptor にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Boxcryptor に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Boxcryptor でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Boxcryptor では、**SP** によって開始される SSO がサポートされます。
- Boxcryptor では、**ジャストインタイム** ユーザー プロビジョニングがサポートされています。
- Boxcryptor では、自動ユーザー プロビジョニング [がサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/boxcryptor-provisioning-tutorial)。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Boxcryptor を追加する

Microsoft Entra ID への Boxcryptor の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Boxcryptor を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Boxcryptor**」と入力します。
4. 結果パネル **Boxcryptor** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Boxcryptor の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Boxcryptor に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Boxcryptor の関連ユーザーとの間にリンク関係を確立する必要があります。

Boxcryptor で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Boxcryptor SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Boxcryptor のテスト ユーザーの作成** - Boxcryptor で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Boxcryptor**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    a. [**サインオン URL** テキスト ボックスに、URL: `https://www.boxcryptor.com/app` を入力します。

    b。 **識別子 (エンティティ ID)** テキスト ボックスに、値を入力します: `boxcryptor`
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**Boxcryptor** のセットアップ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Boxcryptor SSO の構成

Boxcryptor **側** シングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を Boxcryptor サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Boxcryptor テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Boxcryptor に作成します。 Boxcryptor では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Boxcryptor にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

Boxcryptor では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Boxcryptor のサインオン URL にリダイレクトされます。
- Boxcryptor のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Boxcryptor] タイルを選択すると、このオプションは Boxcryptor のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bpanda-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Bpanda を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bpanda-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-27
- Summary: Microsoft Entra IDから Bpanda にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Bpanda とMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されている場合、Microsoft Entra プロビジョニング サービスを使用し、ユーザーとグループを[Bpanda](http://www.mid.de)に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Bpanda でユーザーを作成する
- アクセスが不要になったときには、Bpandaのユーザーを削除する
- Microsoft Entra IDと Bpanda の間でユーザー属性の同期を維持する
- Bpanda にグループとグループ メンバーシップをプロビジョニングする
- Bpanda にシングル サインオンする (推奨)
- クライアント資格情報認証がサポートされています。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bpanda のクラウド サブスクリプションのプロセス領域。 オンプレミスの場合は、Microsoft のインストール ドキュメントを参照してください。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとBpandaの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Bpanda を構成する

1. 認証テナント URL の詳細については、support@mid.de にお問い合わせください。
2. アクセストークンをさらに生成するためのクライアントシークレット。 このシークレットは安全な方法でお客様に送信されているはずです。 詳細については、support@mid.de にお問い合わせください。
3. Microsoft Entra IDと Bpanda の間の接続を正常に確立するには、次のいずれかの方法でaccess トークンを取得する必要があります。

- **Linux** でこのコマンドを使用する

```
curl -u scim:{Your client secret} --location --request POST '{Your tenant specific authentication endpoint}/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials'
```

- または **PowerShell** のこのコマンド

```
$base64AuthInfo = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(("scim:{0}" -f {Your client secret})))    
$headers=@{}   
$headers.Add("Content-Type", "application/x-www-form-urlencoded")  
$headers.Add("Authorization", "Basic {0}" -f $base64AuthInfo)  
$response = Invoke-WebRequest -Uri "{Your tenant specific authentication endpoint}/protocol/openid-connect/token" -Method POST -Headers $headers -ContentType 'application/x-www-form-urlencoded' -Body 'grant_type=client_credentials' 
```

この値は、Bpanda アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Bpanda を追加する

Microsoft Entra アプリケーション ギャラリーから Bpanda を追加して、Bpanda へのプロビジョニングの管理を開始します。 SSO のために Bpanda を以前に設定している場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Bpanda への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づき、TestApp においてユーザーやグループを作成、更新、または無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Bpanda の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **Bpanda** を選択します。

    [Image: アプリケーションの一覧の Bpanda のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Bpanda テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Bpanda に接続できることを確認します。 接続に失敗した場合は、Bpanda アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Bpanda に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Bpanda のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Bpanda API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | emails[type eq "work"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |
    | externalId | 糸 |  |
    | タイトル | 糸 |  |
    | 優先言語 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Bpanda に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Bpanda のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bpmonline-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Creatio を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bpmonline-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Creatio の間のシングル サインオンを構成する方法について説明します。

この記事では、Creatio と Microsoft Entra ID を統合する方法について説明します。 Creatio を Microsoft Entra ID と統合すると、次のことができます。

- Creatio にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Creatio に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Creatio でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Creatio では、**SP および IDP によって開始される SSO** がサポートされます。

### ギャラリーから Creatio を追加する

Microsoft Entra ID への Creatio の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Creatio を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Creatio**」と入力します。
4. 結果パネルから **[Creatio** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Creatio に対する Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Creatio に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Creatio での関連ユーザーとの間にリンク関係を確立する必要があります。

Creatio に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Creatio SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Creatio 内で B.Simon に対応するテストユーザーを作成し、Microsoft Entra のユーザー表示にリンクさせる**。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Creatio]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<SUBDOMAIN>.creatio.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | [応答 URL] |
    | --- |
    | `https://<SUBDOMAIN>.creatio.com/ServiceModel/AuthService.svc/SsoLogin` |
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン URL |
    | --- |
    | `https://<SUBDOMAIN>.creatio.com/` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Creatio クライアント サポート チーム](mailto:support@creatio.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: スクリーンショットは、base64 証明書のダウンロード リンクを含む [SAML 署名証明書] ページを示しています。]
8. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [SAML 署名証明書] ページを示すスクリーンショット。ここでは、自分のアプリフェデレーション メタデータをコピーできます。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Creatio SSO の構成

**Creatio** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Creatio サポート チーム](mailto:support@creatio.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Creatio テスト ユーザーの作成

このセクションでは、Creatio で Britta Simon というユーザーを作成します。 [Creatio サポート チーム](mailto:support@creatio.com)と協力して、Creatio プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Creatio サインオン URL にリダイレクトされます。
- Creatio のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Creatio に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Creatio] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Creatio に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/brainfuse-online-tutoring-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Brainfuse Online Tutoring を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/brainfuse-online-tutoring-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Brainfuse Online Tutoring の間にシングル サインオンを構成する方法について説明します。

この記事では、Brainfuse Online Tutoring と Microsoft Entra ID を統合する方法について説明します。 このアプリは、Brainfuse Live Tutoring へのシングル サインオン統合を提供します。 アプリを使用するには、サブスクライバーであることが必要です。 Brainfuse Online Tutoring を Microsoft Entra ID と統合すると、次のことができます。

- Brainfuse Online Tutoring にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Brainfuse Online Tutoring に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Brainfuse Online Tutoring に対して Microsoft Entra シングル サインオンを構成してテストします。 Brainfuse Online Tutoring は、**SP** によって開始されるシングル サインオンをサポートしています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を Brainfuse Online Tutoring と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Brainfuse Online Tutoring のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Brainfuse Online Tutoring アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Brainfuse Online Tutoring を追加する

Microsoft Entra アプリケーション ギャラリーから Brainfuse Online Tutoring を追加して、Brainfuse Online Tutoring でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Brainfuse Online Tutoring]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、URL を入力します。 `https://landing.brainfuse.com/shibboleth`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://landing.brainfuse.com/Shibboleth.sso/SAML2/POST`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://landing.brainfuse.com/saml.asp?oauth_consumer_key=<ID>`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 この値を取得するには、[Brainfuse Online Tutoring サポート チーム](mailto:support@brainfuse.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Brainfuse Online Tutoring アプリケーションでは、特定の形式の SAML アサーションが必要とされるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Brainfuse Online Tutoring アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | primarysid | user.userprincipalname |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Brainfuse Online Tutoring の SSO を構成する

**Brainfuse Online Tutoring** 側でシングル サインオンを構成するには、**アプリケーション フェデレーション メタデータ URL** を [Brainfuse Online Tutoring サポート チーム](mailto:support@brainfuse.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Brainfuse Online Tutoring テスト ユーザーを作成する

このセクションでは、Brainfuse Online Tutoring で Britta Simon というユーザーを作成します。 [Brainfuse Online Tutoring サポート チーム](mailto:support@brainfuse.com)と協力して、Brainfuse Online Tutoring プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Brainfuse Online Tutoring のサインオン URL にリダイレクトされます。
- Brainfuse Online Tutoring のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Brainfuse Online Tutoring] タイルを選択すると、このオプションは Brainfuse Online Tutoring のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/brainstorm-platform-tutorial"} -->
## Microsoft Entra ID を使用して BrainStorm Platform for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/brainstorm-platform-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BrainStorm Platform の間のシングル サインオンを構成する方法について説明します。

この記事では、BrainStorm Platform を Microsoft Entra ID と統合する方法について説明します。 BrainStorm Platform でエンド ユーザーは、エクスペリエンスをパーソナライズし、長期的な行動変化を維持できるようになります。 BrainStorm Platform を Microsoft Entra ID と統合すると、次のことが可能になります。

- BrainStorm Platform にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで BrainStorm Platform に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

BrainStorm 環境で BrainStorm Platform の Microsoft Entra シングル サインオンを構成してテストできます。 BrainStorm Platform では、 **SP** によって開始されるシングル サインオンと **Just-In-Time** ユーザー プロビジョニングのみがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### [前提条件]

Microsoft Entra ID を BrainStorm Platform と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- BrainStorm Platform のシングル サインオン (SSO) が有効なサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから BrainStorm Platform アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから BrainStorm Platform を追加する

Microsoft Entra アプリケーション ギャラリーから BrainStorm Platform を追加して、BrainStorm Platform とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**BrainStorm Platform**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、値を入力します。 `urn:brainstorminc:auth:wsfed`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://auth.brainstorminc.com/signin-wsfed`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://auth.brainstorminc.com/auth/wsfed?providerId=<ID>`

    注

    この値は実際の値ではありません。 この値は実際のサインオン URL で更新します。 これらの値を取得するには、 [BrainStorm Platform クライアント サポート チーム](mailto:support@brainstorminc.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. BrainStorm Platform アプリケーションでは、特定の形式の SAML アサーションが必要とされるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. BrainStorm Platform アプリケーションでは、上記のものに加えて、以下に示すさらにいくつかの属性を SAML 応答で返すことができます。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | タイトル | ユーザー.職名 |
    | 部署 | user.department |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### BrainStorm Platform SSO の構成

**BrainStorm Platform** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[BrainStorm Platform サポート チーム](mailto:support@brainstorminc.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### BrainStorm Platform テスト ユーザーの作成

このセクションでは、BrainStorm Platform で B.Simon というユーザーを作成します。 BrainStorm Platform は Just-In-Time ユーザー プロビジョニングをサポートしており、これは既定で有効になっています。 このセクションにはアクション項目はありません。 BrainStorm Platform にユーザーがまだ存在していない場合、一般的には認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる BrainStorm Platform のサインオン URL にリダイレクトされます。
- BrainStorm Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [BrainStorm Platform] タイルを選択すると、このオプションは BrainStorm Platform のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/brandfolder-tutorial"} -->
## Microsoft Entra ID で Brandfolder for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/brandfolder-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Brandfolder の間のシングル サインオンを構成する方法について説明します。

この記事では、Brandfolder と Microsoft Entra ID を統合する方法について説明します。 Brandfolder と Microsoft Entra ID の統合には、次の利点があります。

- Brandfolder にアクセスできるユーザーを Microsoft Entra ID で制御できる。
- ユーザーが自分の Microsoft Entra アカウントで PlanMyLeave に自動的にサインイン (シングル サインオン) するように設定できる。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Brandfolder でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Brandfolder では、**IDP** Initiated SSO がサポートされます
- Brandfolder では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Brandfolder の追加

Microsoft Entra ID への Brandfolder の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Brandfolder を追加する必要があります。

**ギャラリーから Brandfolder を追加するには、次の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションに「**Brandfolder**」と入力し、結果パネルで **Brandfolder** を選択し、[**追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Brandfolder]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Brandfolder で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Brandfolder 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

Brandfolder で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Brandfolder のシングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Brandfolder テスト ユーザーの作成 - Brandfolder** で Britta Simon に対応するユーザーが作成され、Microsoft Entra のユーザー表現にリンクします。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Brandfolder で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Brandfolder** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    [Image: [Brandfolder のドメインと URL] のシングル サインオン情報]

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://brandfolder.com/organizations/<ORG_SLUG>/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://brandfolder.com/organizations/<ORG_SLUG>/saml`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Brandfolder クライアント サポート チーム](mailto:support@brandfolder.com)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Brandfolder のシングル サインオンの構成

**Brandfolder** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Brandfolder サポート チーム](mailto:support@brandfolder.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Brandfolder のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Brandfolder に作成します。 Brandfolder では、**Just-In-Time ユーザー プロビジョニング**がサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Brandfolder にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Brandfolder] タイルを選択すると、SSO を設定した Brandfolder に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/braze-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Braze を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/braze-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Braze の間のシングル サインオンを構成する方法について説明します。

この記事では、Braze と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Braze を統合すると、次のことが可能になります。

- Braze にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Braze に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Braze でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Braze では、**SP および IDP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Braze の追加

Braze と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に Braze をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Braze**」と入力します。
4. 結果のパネルから **[Braze]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Braze の Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、Braze の Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Braze での関連ユーザーとの間にリンク関係を確立する必要があります。

Braze の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Braze の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Braze テストユーザーの作成** - Braze で B.Simon に対応するユーザーを作成し、それを Microsoft Entra 内のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Braze**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.braze.com/auth/saml/callback`
6. Braze から生成された Relay State API キーを Relay State フィールドに入力して **、RelayState** を構成します。
7. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.braze.com/sign_in`

    注

    サブドメインの場合は、Braze インスタンスの URL に一覧表示されている調整サブドメインを使用します。 たとえば、インスタンスが US-01 の場合、URL は https://dashboard-01.braze.com です。 つまり、サブドメインは dashboard-01 です。
8. Braze アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Braze アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | user.userprincipalname |
    | first\_name | User.givenname |
    | last\_name | ユーザーの名字 |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Braze のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Braze の SSO の構成

Braze 側でシングル サインオンを構成するには、 **Braze** アカウント マネージャーがアカウントに対して SAML SSO を有効にしていることを確認する必要があります。 有効な場合は、[Company Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社の設定) &gt; [Security Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/セキュリティの設定) に移動し、[SAML SSO] セクションを [ON](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/オン) に切り替えることができます。 このセクションでは、ダウンロードした **証明書 (Base64)** をコピーして貼り付け、SAML 名を追加する必要があります。

#### Braze のテスト ユーザーの作成

このセクションでは、Braze で B.Simon というユーザーを作成します。 [Braze サポート チーム](mailto:support@braze.com)と連携して、Braze プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる Braze のサインオン URL にリダイレクトされます。
- Braze のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Braze に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Braze タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Braze に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bridge-tutorial"} -->
## Microsoft Entra ID で Bridge for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bridge-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Bridge の間のシングル サインオンを構成する方法について説明します。

この記事では、Bridge と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Bridge を統合すると、次のことができます。

- Bridge へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Bridge に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bridge でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Bridge では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの Bridge の追加

Microsoft Entra ID への Bridge の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Bridge を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Bridge**」と入力します。
4. 結果パネルから **[Bridge** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bridge の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Bridge に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーとそれに対応する Bridge ユーザーをリンクする必要があります。

Bridge に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bridge SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bridge テスト ユーザーの作成** - Bridge で B.Simon に対応するユーザーとして、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Bridge]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.bridgeapp.com`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<company name>.bridgeapp.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [Bridge クライアント サポート チーム](https://community.bridgeapp.com/hc/en-us/community/topics) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Bridge のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bridge SSO の構成

**Bridge** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [Bridge サポート チーム](https://community.bridgeapp.com/hc/en-us/community/topics)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Bridge テスト ユーザーの作成

このセクションでは、Bridge で Britta Simon というユーザーを作成します。 [Bridge サポート チーム](https://community.bridgeapp.com/hc/en-us/community/topics)と協力して、Bridge プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Bridge サインオン URL にリダイレクトされます。
- Bridge のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Bridge] タイルを選択すると、このオプションは Bridge のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bright-pattern-omnichannel-contact-center-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Bright Pattern Omnichannel Contact Center を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bright-pattern-omnichannel-contact-center-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Bright Pattern Omnichannel Contact Center の間でシングル サインオンを構成する方法について説明します。

この記事では、Bright Pattern Omnichannel Contact Center と Microsoft Entra ID を統合する方法について説明します。 Bright Pattern Omnichannel Contact Center と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Bright Pattern Omnichannel Contact Center へのアクセス権を管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Bright Pattern Omnichannel Contact Center に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bright Pattern Omnichannel Contact Center でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Bright Pattern Omnichannel Contact Center では、**SP と IDP** によって開始される SSO がサポートされます
- Bright Pattern Omnichannel Contact Center では、 **Just In Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Bright Pattern Omnichannel Contact Center の追加

Microsoft Entra ID への Bright Pattern Omnichannel Contact Center の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Bright Pattern Omnichannel Contact Center を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Bright Pattern Omnichannel Contact Center」と**入力します。
4. 結果パネルから **Bright Pattern Omnichannel Contact Center** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bright Pattern Omnichannel Contact Center の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Bright Pattern Omnichannel Contact Center に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Bright Pattern Omnichannel Contact Center の関連ユーザーとの間にリンク関係を確立する必要があります。

Bright Pattern Omnichannel Contact Center で Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bright Pattern Omnichannel Contact Center の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Bright Pattern Omnichannel Contact Center のテストユーザーを作成する - Bright Pattern Omnichannel Contact Center で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon というユーザーの表現にリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Bright Pattern Omnichannel Contact Center]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `<SUBDOMAIN>_sso`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.brightpattern.com/agentdesktop/sso/redirect`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.brightpattern.com/`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Bright Pattern Omnichannel Contact Center クライアント サポート チーム](mailto:support@brightpattern.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Bright Pattern Omnichannel Contact Center アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. 上記に加えて、Bright Pattern Omnichannel Contact Center アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | Namespace |
    | --- | --- |
    | ファーストネーム | User.givenname |
    | lastName | ユーザーの姓 |
    | メール | User.mail |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **Bright Pattern Omnichannel Contact Center のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bright Pattern Omnichannel Contact Center の SSO の構成

**Bright Pattern Omnichannel Contact Center** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Bright Pattern Omnichannel Contact Center サポート チーム](mailto:support@brightpattern.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Bright Pattern Omnichannel Contact Center のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Bright Pattern Omnichannel Contact Center に作成します。 Bright Pattern Omnichannel Contact Center では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Bright Pattern Omnichannel Contact Center にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Bright Pattern Omnichannel Contact Center] タイルを選択すると、SSO を設定した Bright Pattern Omnichannel Contact Center に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/brightidea-tutorial"} -->
## Microsoft Entra ID で Brightidea for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/brightidea-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Brightidea の間でシングル サインオンを構成する方法について説明します。

この記事では、Brightidea と Microsoft Entra ID を統合する方法について説明します。 Brightidea と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Brightidea へのアクセスを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Brightidea に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Brightidea でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Brightidea では、**SP および IDP** から開始される SSO がサポートされます。
- Brightidea では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Brightidea を追加する

Microsoft Entra ID への Brightidea の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Brightidea を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Brightidea**」と入力します。
4. 結果パネルから **[Brightidea** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Brightidea の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Brightidea に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Brightidea の関連ユーザーとの間にリンク関係を確立する必要があります。

Brightidea に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Brightidea SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Brightidea のテストユーザーを作成する** - Brightidea で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra におけるユーザーの表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Brightidea**&gt;**シングル サインオン**を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** があり、 **IDP** 開始モードで構成する場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルの選択]

    c. メタデータ ファイルが正常にアップロードされると、 **識別子** と **応答 URL** の値が Brightidea セクションのテキスト ボックスに自動的に入力されます。

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.brightidea.com`
7. [ **SAML を使用したシングル Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Brightidea のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Brightidea SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者の資格情報を使用して Brightidea にサインインします。
2. Brightidea システムの SSO 機能にアクセスするには、[ **エンタープライズ セットアップ**&gt;**認証] タブ**に移動します。[認証の選択] と [SAML プロファイル] の 2 つのサブタブが表示されます。

    [Image: [認証] タブが選択されている Brightidea サイトを示すスクリーンショット。]
3. [ **認証の選択] を選択します**。 既定では、Brightidea のログインと登録という 2 つの標準的な方法のみが表示されます。 SSO メソッドが追加されると、一覧に表示されます。

    [Image: スクリーンショットは、[認証の選択] が選択された [Brightidea 認証] タブを示しています。]
4. **SAML プロファイルを**選択し、次の手順を実行します。

    [Image: [SAML プロファイル] が選択されている [Brightidea Authentication] タブを示すスクリーンショット。このタブには、[メタデータのダウンロード] と [新規追加] のオプションが表示されます。]

    ある。 [メタデータの **ダウンロード** ] を選択し **、[基本的な SAML 構成]** セクションでアップロードします。

    b。 **ID プロバイダー設定**の下にある [**新規追加**] ボタンを選択し、次の手順を実行します。

    [Image: 情報を入力する Brightidea ID プロバイダーの設定を示すスクリーンショット。]

    - など、SAML プロファイル名を入力します。
    - [ **メタデータのアップロード**] で、[ファイルの選択] を選択し、ダウンロードしたメタデータ ファイルをアップロードします。

        注

        メタデータ ファイルをアップロードした後、残りのフィールド **シングル サインオン サービス、ID プロバイダー発行者、公開キーのアップロード** が自動的に設定されます。
    - [ **電子メール** ] ボックスに、 `mail`として値を入力します。
    - [ **画面名]** ボックスに、 `givenName`として値を入力します。
    - [ **変更の保存] を選択します**。

#### Brightidea テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Brightidea に作成します。 Brightidea では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Brightidea にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Brightidea のサインオン URL にリダイレクトされます。
- Brightidea のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Brightidea に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Brightidea] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Brightidea に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/brightspace-desire2learn-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Brightspace by Desire2Learn を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/brightspace-desire2learn-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Brightspace by Desire2Learn の間でシングル サインオンを構成する方法について説明します。

この記事では、Brightspace by Desire2Learn と Microsoft Entra ID を統合する方法について説明します。 Brightspace by Desire2Learn を Microsoft Entra ID と統合すると、次のことが可能になります。

- Brightspace by Desire2Learn にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Brightspace by Desire2Learn に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Brightspace by Desire2Learn は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Brightspace by Desire2Learn でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Brightspace by Desire2Learn では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Brightspace by Desire2Learn の追加

Brightspace by Desire2Learn の Microsoft Entra ID への統合を構成するには、Brightspace by Desire2Learn をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Brightspace by Desire2Learn**」と入力します。
4. 結果パネルから **[Brightspace by Desire2Learn]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Brightspace by Desire2Learn 用の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Brightspace by Desire2Learn に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Brightspace by Desire2Learn の関連ユーザーの間にリンク関係を確立する必要があります。

Brightspace by Desire2Learn に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Brightspace by Desire2Learn の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Brightspace by Desire2Learn のテスト ユーザーの作成** - Brightspace by Desire2Learn で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Brightspace by Desire2Learn**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    a. **[識別子]** ボックスに、次のパターンを使用していずれかの URL を入力します。

    ```http
    https://<companyname>.tenants.brightspace.com/samlLogin
    https://<companyname>.desire2learn.com/shibboleth-sp
    ```

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.desire2learn.com/d2l/lp/auth/login/samlLogin.d2l`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Brightspace by Desire2Learn クライアント サポート チーム](https://www.d2l.com/contact/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Brightspace by Desire2Learn のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Brightspace by Desire2Learn の SSO の構成

**Brightspace by Desire2Learn** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Brightspace by Desire2Learn サポート チーム](https://www.d2l.com/contact/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Brightspace by Desire2Learn テスト ユーザーの作成

このセクションでは、Brightspace by Desire2Learn で Britta Simon というユーザーを作成します。 [Brightspace by Desire2Learn サポート チーム](https://www.d2l.com/contact/)と連携して、Brightspace by Desire2Learn プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

注

Brightspace by Desire2Learn から提供されている他の Brightspace by Desire2Learn ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Brightspace by Desire2Learn に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Brightspace by Desire2Learn] タイルを選択すると、SSO を設定した Brightspace by Desire2Learn に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/briq-tutorial"} -->
## Microsoft Entra ID で Briq for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/briq-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Briq の間でシングル サインオンを構成する方法について説明します。

この記事では、Briq と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Briq を統合すると、次のことができます。

- Briq にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Briq に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Briq でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Briq では、 **SP** によって開始される SSO のみがサポートされます。
- Briq では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Briq を追加する

Microsoft Entra ID への Briq の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Briq を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Briq**」と入力します。
4. 結果パネルから **Briq** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Briq の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Briq に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Briq の関連ユーザーとの間にリンク関係を確立する必要があります。

Briq に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを作成** する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Briq SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Briq テスト ユーザーの作成 - Briq** で B.Simon に対応するユーザーを作成し、Microsoft Entra ID の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Briq**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://microservice-prod.br.iq/authentication/microsoft/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://microservice-prod.br.iq/authentication/microsoft/ssodata`

    b。 [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.br.iq`
6. Briq アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、Briq アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 数 | ユーザーの携帯電話 |
    | 国 | ユーザーの国 |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Briq のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Briq SSO の構成

**Briq** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Briq サポート チーム](https://briq.com/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Briq テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Briq に作成します。 Briq では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Briq にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Briq サインオン URL にリダイレクトします。
- Briq のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Briq] タイルを選択すると、このオプションは Briq のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/britive-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Britive を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/britive-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-27
- Summary: ユーザー アカウントをMicrosoft Entra IDから Britive に自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Britive と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra IDが構成されると、Microsoft Entra プロビジョニング サービスを使用して、[Britive](https://www.britive.com/)にユーザーとグループを自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Britive でユーザーを作成する
- アクセスが不要になった場合、Britiveのユーザーを削除する
- Microsoft Entra IDと Britive の間でユーザー属性の同期を維持する
- Britive にグループとグループ メンバーシップをプロビジョニングする
- Britive への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/britive-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Britive](https://www.britive.com/) テナント。
- 管理者アクセス許可がある Britive のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDと Britiveの間で[マップするデータを決定します](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Britive を構成する

このセクションで説明する手順を使用して、アプリケーションを手動で構成する必要があります。

1. 管理者特権で Britive アプリケーションにログインします。
2. **Admin-&gt;Identity Management-&gt;Identity Providers** を選択します。
3. [ **ID プロバイダーの追加] を選択します**。 名前と説明を入力します。 **[追加]** ボタンを選びます。

    [Image: ID プロバイダー]
4. 次に示すような構成ページが表示されます。

    [Image: 構成ページ]
5. [**SCIM** タブを選択します。SCIM プロバイダーを Generic から Azure に変更し、変更を保存します。 SCIM URL をコピーし、メモしておきます。 これらの値は、Azure portalの Britive アプリケーションの [プロビジョニング] タブの **Tenant URL** ボックスに入力されます。

    [Image: SCIM ページ]
6. [ **トークンの作成]** を選択します。 必要に応じてトークンの有効性を選択し、[ **トークンの作成** ] ボタンを選択します。

    [Image: トークンの作成]
7. 生成されたトークンをコピーしてメモします。 [OK] を選択します。 ユーザーはトークンをもう一度表示できないことに注意してください。 **トークンの再作成** ボタンを選択して、新しいトークンを生成します。必要に応じて行います。 これらの値は、getAbstract アプリケーションの [プロビジョニング] タブの [ **シークレット トークン** ] ボックスと [テナント URL] ボックスに入力されます。

    [Image: トークンのコピー]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Britive を追加する

Microsoft Entra アプリケーション ギャラリーから Britive を追加して、Britive へのプロビジョニングの管理を開始します。 SSO 用に Britive を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5:Britive への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づき、Britiveでユーザーやグループを作成、更新、および無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を説明します。

#### Microsoft Entra IDで Britive の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Britive]** を選択します。

    [Image: アプリケーションの一覧の Britive のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Britive テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Britive に接続できることを確認します。 接続に失敗した場合は、Britive アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Britive に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Britive のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Britive API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | タイトル | 糸 |  |
    | externalId | 糸 |  |
    | 優先言語 | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | ニックネーム | 糸 |  |
    | ユーザータイプ | 糸 |  |
    | ロケール | 糸 |  |
    | タイムゾーン | 糸 |  |
    | emails[type eq "home"].value | 糸 |  |
    | emails[type eq "other"].value | 糸 |  |
    | emails[type eq "work"].value | 糸 |  |
    | phoneNumbers[type eq "home"].value | 糸 |  |
    | phoneNumbers[type eq "other"].value | 糸 |  |
    | phoneNumbers[type eq "pager"].value | 糸 |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |
    | phoneNumbers[type eq "fax"].value | 糸 |  |
    | addresses[type eq "work"].formatted | 糸 |  |
    | addresses[type eq "work"].streetAddress | 糸 |  |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  |
    | addresses[type eq "work"].region | 糸 |  |
    | addresses[type eq "work"].postalCode | 糸 |  |
    | addresses[type eq "work"].country | 糸 |  |
    | addresses[type eq "home"].formatted | 糸 |  |
    | addresses[type eq "home"].streetAddress | 糸 |  |
    | addresses[type eq "home"].locality（住所[タイプ＝「自宅」].地域） | 糸 |  |
    | addresses[type eq "home"].region | 糸 |  |
    | addresses[type eq "home"].postalCode | 糸 |  |
    | addresses[type eq "home"].country | 糸 |  |
    | addresses[type eq "other"].formatted | 糸 |  |
    | addresses[type eq "other"].streetAddress | 糸 |  |
    | 住所[タイプ eq "その他"].市区町村 | 糸 |  |
    | addresses[type eq "other"].region | 糸 |  |
    | addresses[type eq "other"].postalCode | 糸 |  |
    | addresses[type eq "other"].country | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:costCenter | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | リファレンス |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Britive に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Britive のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
    | externalId | 糸 |  |
    | members | リファレンス |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/britive-tutorial"} -->
## Microsoft Entra ID で Britive for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/britive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Britive の間のシングル サインオンを構成する方法について説明します。

この記事では、Britive と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Britive を統合すると、次のことが可能になります。

- Britive にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Britive に自動的にサインインできるように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Britive サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Britive では、**SP** Initiated SSO がサポートされます。
- Britive では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/britive-provisioning-tutorial)がサポートされます。

### ギャラリーからの Britive の追加

Microsoft Entra ID への Britive の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Britive を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Britive**」と入力します。
4. 結果パネルから **Britive** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Britive の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Britive に対する Microsoft Entra SSO を構成およびテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Britive の関連ユーザーとの間にリンク関係を確立する必要があります。

Britive に対して Microsoft Entra SSO を構成およびテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Britive の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Britive テストユーザーの作成** - Britive で B.Simon の対応物として Microsoft Entra のユーザー表現にリンクされたユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Britive**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANTNAME>.britive-app.com/sso`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:amazon:cognito:sp:<UNIQUE_ID>`

    注

    これらの値は実際の値ではありません。 これらの値は、実際のサインオン URL と識別子で更新します。これについては、この記事の後半で説明します。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクのスクリーンショット。]
7. **[Britive のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Britive の SSO の構成

1. 別の Web ブラウザー ウィンドウで管理者権限を使用して Britive アプリケーションにログインします。
2. ナビゲーション メニューから **[管理者] - &gt;[ID 管理] - &gt;[ID プロバイダー]** を選択します。
3. [ **ID プロバイダーの追加] を選択します**。 名前と説明を入力します。 **[追加]** ボタンを選びます。

    [Image: [D プロバイダーの追加] のスクリーンショット。]
4. Azure ID プロバイダーで **[管理]** を選択し、**[SSO 構成]** を選択します。

    [Image: [SSO 構成] 設定のスクリーンショット。]

    1. **[SSO プロバイダー]**を**[Generic] (汎用)**から **[Azure]** に変更します。
    2. **[対象ユーザー/エンティティ ID] の値を**コピーし、[**基本的な SAML 構成**] セクションの [**識別子 (エンティティ ID)]** テキスト ボックスに貼り付けます。
    3. **[SSO URL の開始**] の値をコピーし、[**基本的な SAML 構成**] セクションの **[サインオン URL**] テキスト ボックスに貼り付けます。
    4. [ **SAML メタデータのアップロード** ] を選択して、Azure portal からダウンロードした **メタデータ XML** ファイルをアップロードします。 メタデータ ファイルをアップロードすると、上記の値が自動的に設定され、変更が保存されます。

#### Britive のテスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで管理者特権を使用して Britive にログインします。
2. **[管理者]** 設定アイコンを選択し、**[ID 管理]** を選択します。
3. [ユーザー] タブから [**ユーザー**の**追加]** を選択します。
4. 組織の要件に従って、必要なユーザー詳細情報をすべて入力し、**[追加]** を選択します。 **[ID プロバイダー]** 一覧から必ず Azure を選択してください。

注

Britive では、自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/britive-provisioning-tutorial) 。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Britive のサインオン URL にリダイレクトされます。
- Britive のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Britive] タイルを選択すると、このオプションは Britive のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/brivo-onair-identity-connector-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Brivo Onair Identity Connector を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/brivo-onair-identity-connector-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-27
- Summary: ユーザー アカウントを Brivo Onair Identity Connector に自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Brivo Onair Identity Connector で実行する手順と、Brivo Onair Identity Connector に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するようにMicrosoft Entra IDを構成するMicrosoft Entra IDを示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Brivo Onair Identity Connector でユーザーを作成します。
- アクセスが不要になったら、Brivo Onair Identity Connector のユーザーを削除します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Brivo Onair Identity Connector のテナント](https://www.brivo.com/lp/quote)
- 上級管理者アクセス許可を持つ Brivo Onair Identity Connector のユーザー アカウント。

### Brivo Onair Identity Connector へのユーザーの割り当て

Microsoft Entra IDでは、*assignments* という概念を使用して、選択したアプリにaccessを受け取るユーザーを決定します。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに割り当てられているユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、どのユーザーやグループが Microsoft Entra ID から Brivo Onair Identity Connector へのアクセス権を必要とするかを決定する必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Brivo Onair Identity Connector に割り当てることができます。

- [エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### Brivo Onair Identity Connector へのユーザーの割り当てに関する重要なヒント

- 1 人の Microsoft Entra ユーザーを Brivo Onair Identity Connector に割り当てて、自動ユーザー プロビジョニング構成をテストすることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- Brivo Onair Identity Connector にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **Default Access** ロールを持つユーザーは、プロビジョニングから除外されます。

### プロビジョニング用に Brivo Onair Identity Connector を設定する

1. [Brivo Onair Identity Connector 管理コンソールにサインインします](https://acs.brivo.com/login/)。 [ **アカウント &gt; アカウント設定]** に移動します。

    [Image: Brivo Onair Identity Connector 管理コンソール]
2. [**Microsoft Entra ID** タブを選択します。**Microsoft Entra ID**詳細ページで、上級管理者アカウントのパスワードを再入力します。 **送信**を選択します。

    [Image: Brivo Onair Identity Connector azure]
3. [ **トークンのコピー** ] ボタンを選択し、 **シークレット トークン**を保存します。 この値は、Brivo Onair Identity Connector アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力されます。

    [Image: Brivo Onair Identity Connector トークン]

### ギャラリーから Brivo Onair Identity Connector を追加する

Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Brivo Onair Identity Connector を構成する前に、Microsoft Entra アプリケーション ギャラリーからマネージド SaaS アプリケーションの一覧に Brivo Onair Identity Connector を追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Brivo Onair Identity Connector を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Brivo Onair Identity Connector**」と入力します。
4. 結果パネルから **Brivo Onair Identity Connector を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Brivo Onair Identity Connector に対して自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーやグループの割り当てに基づいて、Brivo Onair Identity Connector でユーザーやグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Brivo Onair Identity Connector の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で [ **Brivo Onair Identity Connector] を選択します**。

    [Image: アプリケーションの一覧の Brivo Onair Identity Connector のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. `https://scim.brivo.com/ActiveDirectory/v2/` を**テナント URL**に入力してください。 先ほど取得した **SCIM 認証トークン** の値を **シークレット トークン**に入力します。 **Test Connection** を選択して、Microsoft Entra IDが Brivo Onair Identity Connector に接続できることを確認します。 接続できない場合は、使用中の Brivo Onair Identity Connector アカウントに管理者アクセス許可があることを確認してから、もう一度試します。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute Mapping** セクションで、Microsoft Entra IDから Brivo Onair Identity Connector に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Brivo Onair Identity Connector のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Brivo Onair Identity Connector のユーザー属性]
12. **[グループ]** を選びます。
13. **Attribute Mapping** セクションで、Microsoft Entra IDから Brivo Onair Identity Connector に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために Brivo Onair Identity Connector のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Brivo Onair Identity Connector グループの属性]
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/broadcom-dx-saas-tutorial"} -->
## Microsoft Entra ID を使用して Broadcom DX SaaS をシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/broadcom-dx-saas-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Broadcom DX SaaS の間のシングル サインオンを構成する方法について説明します。

この記事では、Broadcom DX SaaS と Microsoft Entra ID を統合する方法について説明します。 Broadcom DX SaaS を Microsoft Entra ID と統合すると、次のことが可能になります。

- Broadcom DX SaaS にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Broadcom DX SaaS に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Broadcom DX SaaS は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- シングル サインオン (SSO) が有効な Broadcom DX SaaS サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Broadcom DX SaaS では、**IDP** Initiated SSO がサポートされます。
- Broadcom DX SaaS では、**Just In Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Broadcom DX SaaS の追加

Microsoft Entra ID への Broadcom DX SaaS の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Broadcom DX SaaS を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Broadcom DX SaaS**」と入力します。
4. 結果パネルから **[Broadcom DX SaaS]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Broadcom DX SaaS の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Broadcom DX SaaS に対する Microsoft Entra SSO を構成およびテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Broadcom DX SaaS の関連ユーザーとの間にリンク関係を確立する必要があります。

Broadcom DX SaaS に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Broadcom DX SaaS の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Broadcom DX SaaSのテストユーザーを作成 - Broadcom DX** SaaSでB.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Broadcom DX SaaS]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次の手順に従います。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `DXI_<TENANT_NAME>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://axa.dxi-na1.saas.broadcom.com/ess/authn/<TENANT_NAME>`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Broadcom DX SaaS クライアント サポート チーム](mailto:dxi-na1@saas.broadcom.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Broadcom DX SaaS アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. 上記に加えて、Broadcom DX SaaS アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Broadcom DX SaaS のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Broadcom DX SaaS の SSO の構成

1. Broadcom DX SaaS 企業サイトに管理者としてログインします。
2. **[設定]** に移動し、[**ユーザー**] タブを選択します。

    [Image: ユーザー]
3. [ **ユーザー管理** ] セクションで省略記号を選択し、[SAML] を選択 **します**。

    [Image: ユーザー管理]
4. **[Identify SAML account](SAML アカウントの識別)** セクションで、次の手順を実行します。

    [Image: アカウント]

    a. **[発行者]** テキスト ボックスに、先ほどコピーした **[Microsoft Entra 識別子]** の値を貼り付けます。

    b。 **[ID プロバイダー (IDP) のログイン URL]** テキストボックスに、コピーしておいた**ログイン URL** の値を入力します。

    c. **[ID プロバイダー (IDP) のログアウト URL]** テキストボックスに、コピーしておいた**[ログアウト URL]** の値を入力します。

    d. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[Identity provider certificate](ID プロバイダー証明書)** ボックスに貼り付けます。

    e. **[次へ**] を選択します。
5. [ **マップ属性** ] セクションで、次のページで必要な SAML 属性を入力し、[ **次へ**] を選択します。

    [Image: 属性]
6. [ **ユーザー グループの識別** ] セクションで、そのグループのオブジェクト ID を入力し、[ **次へ**] を選択します。

    [Image: グループの追加]
7. [ **SAML アカウントの設定** ] セクションで、設定された値を確認し、[保存] を選択 **します**。

    [Image: SAML アカウント]

#### Broadcom DX SaaS のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Broadcom DX SaaS に作成します。 Broadcom DX SaaS では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Broadcom DX SaaS にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Broadcom DX SaaS に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Broadcom DX SaaS] タイルを選択すると、SSO を設定した Broadcom DX SaaS に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/brocade-sannav-global-view-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Brocade SANnav Global View を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/brocade-sannav-global-view-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-20
- Summary: Microsoft Entra ID と Brocade SANnav Global View の間でシングル サインオンを構成する方法について説明します。

この記事では、Brocade SANnav Global View と Microsoft Entra ID を統合する方法について説明します。 Brocade SANnav Global View と Microsoft Entra ID を統合すると、次のことができます。

- Brocade SANnav Global View にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Brocade SANnav Global View に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 有効なサブスクリプション ライセンスがインストールされている SANnav Global View アプリケーション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Brocade SANnav Global View では、**SPとIDP**によるSSOの両方がサポートされます。

### ギャラリーから Brocade SANnav グローバル ビューを追加する

Microsoft Entra ID への Brocade SANnav Global View の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Brocade SANnav Global View を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Brocade SANnav Global View**」と入力します。
4. 結果パネルから **Brocade SANnav グローバル ビュー** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Brocade SANnav Global View の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Brocade SANnav Global View に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra グループと Brocade Global View グループの間にリンク関係を確立する必要があります。

Brocade SANnav Global View に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **SANnav グループを作成し、ユーザーをグループに割り当てます** 。B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。 SANnav グローバル ビュー メタデータ ファイルのインポートを追加します。
2. **Brocade SANnav Global View の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Brocade SANnav Global View グループを作成** する - B. Simon が Microsoft Entra の "SANnav Administrator" グループの一部であると仮定します。 Microsoft Entra メタデータのインポートを追加します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Brocade SANnav Global View**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする方法を示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択する方法を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、[**基本的な SAML 構成]** セクションに**識別子**と**応答 URL** の値が自動的に設定されます。

    注

    **サービス プロバイダーメタデータファイル**は、この記事で後述する**「Brocade SANnav Global View SSO の構成**」セクションから取得します。 **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. Brocade SANnav Global View アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、既定値を持つユーザー属性と要求を示しています。]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードしてコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、Microsoft Entra 管理センターで B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **を選択して**を作成します。

#### SANnav グループを作成し、ユーザーをグループに割り当てる

このセクションでは、B.Simon に Brocade SANnav Global View へのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Brocade SANnav Global View** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Brocade SANnav Global View の SSO の構成

1. Brocade SANnav Global View 企業サイトに管理者としてログインします。
2. **[SANnav**] タブに移動し、[SANnav の認証と承認] ページで次の手順を実行します。

    [Image: ID プロバイダーの構成の設定を示すスクリーンショット。]

    1. **SAML** としてプライマリ認証を選択します。
    2. Microsoft Entra 管理センターからダウンロードした**フェデレーション メタデータ XML** ファイルをアップロードするには、[**インポート]** を選択します。
    3. **[有効化]** を選択します。
3. **SAML サービス プロバイダー (SP)** に移動し、[**サービス プロバイダー メタデータ XML ファイルのダウンロード**] を選択し、Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションでアップロードします。

    [Image: サービス プロバイダー メタデータの設定を示すスクリーンショット。]

#### Brocade SANnav グローバル ビュー グループの作成

このセクションでは、次のスクリーンショットに示すように、Brocade SANnav グローバル ビューに "SANnav\_Group" というグループを作成します。

[Image: brocade でグループを作成する方法を示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Brocade SANnav グローバル ビューのサインオン URL にリダイレクトします。
- Brocade SANnav Global View のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Brocade SANnav グローバル ビューに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Brocade SANnav Global View] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Brocade SANnav グローバル ビューに自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/brocade-sannav-management-portal-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Brocade SANnav 管理ポータルを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/brocade-sannav-management-portal-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-20
- Summary: Microsoft Entra ID と Brocade SANnav 管理ポータルの間でシングル サインオンを構成する方法について説明します。

この記事では、Brocade SANnav 管理ポータルと Microsoft Entra ID を統合する方法について説明します。 Brocade SANnav 管理ポータルを Microsoft Entra ID と統合すると、次のことができます。

- Brocade SANnav 管理ポータルにアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Brocade SANnav 管理ポータルに自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- 有効なサブスクリプション ライセンスがインストールされた SANnav 管理ポータル アプリケーション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Brocade SANnav 管理ポータルでは、**SP と IDP** の両方の開始による SSO がサポートされます。

### ギャラリーから Brocade SANnav 管理ポータルを追加する

Microsoft Entra ID への Brocade SANnav 管理ポータルの統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Brocade SANnav 管理ポータルを追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Brocade SANnav 管理ポータル**」と入力します。
4. 結果パネルから **Brocade SANnav 管理ポータル** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Brocade SANnav 管理ポータルの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Brocade SANnav 管理ポータルに対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra グループと Brocade 管理ポータル グループの間にリンク関係を確立する必要があります。

Brocade SANnav 管理ポータルで Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **SANnav グループを作成し、ユーザーをグループに割り当てます** 。B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。 SANnav 管理ポータルメタデータ ファイルのインポートを追加します。
2. **Brocade SANnav 管理ポータルの SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Brocade SANnav 管理ポータル グループを作成** する - B. Simon が Microsoft Entra の "SANnav Administrator" グループの一部であると仮定します。 Microsoft Entra メタデータのインポートを追加します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Brocade SANnav 管理ポータル**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でシングル サインオンを設定** する] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**追加]** を選択します。

    [Image: メタデータ ファイルを選択する方法を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、[**基本的な SAML 構成]** セクションに**識別子**と**応答 URL** の値が自動的に設定されます。

    注

    **サービス プロバイダー メタデータ ファイル**は、この記事で後述する **Brocade SANnav 管理ポータルの SSO の構成**セクションから取得します。 **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。
6. Brocade SANnav 管理ポータル アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: スクリーンショットは、既定値を持つユーザー属性と要求を示しています。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、Microsoft Entra 管理センターで B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **[作成]を選択します**。

#### SANnav グループを作成し、ユーザーをグループに割り当てる

このセクションでは、B.Simon に Brocade SANnav 管理ポータルへのアクセスを許可することで、このユーザーが Microsoft Entra シングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Brocade SANnav 管理ポータル**に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**割り当ての追加]** ダイアログで [**ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. [ **割り当ての追加** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Brocade SANnav 管理ポータルの SSO の構成

1. Brocade SANnav 管理ポータルの会社サイトに管理者としてログインします。
2. **[SANnav**] タブに移動し、[SANnav の認証と承認] ページで次の手順を実行します。

    [Image: ID プロバイダーの構成の設定を示すスクリーンショット。]

    1. **SAML** としてプライマリ認証を選択します。
    2. Microsoft Entra 管理センターからダウンロードした**フェデレーション メタデータ XML** ファイルをアップロードするには、[**インポート]** を選択します。
    3. **[有効にする] を選択します**。
3. **SAML サービス プロバイダー (SP)** に移動し、[**サービス プロバイダー メタデータ XML ファイルのダウンロード**] を選択し、Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションでアップロードします。

    [Image: サービス プロバイダー メタデータの設定を示すスクリーンショット。]

#### Brocade SANnav 管理ポータル グループを作成する

このセクションでは、次のスクリーンショットに示すように、Brocade SANnav 管理ポータルで "SANnav\_Group" というグループを作成します。

[Image: brocade でグループを作成する方法を示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Brocade SANnav 管理ポータルのサインオン URL にリダイレクトします。
- Brocade SANnav 管理ポータルのサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Brocade SANnav 管理ポータルに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Brocade SANnav 管理ポータル] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Brocade SANnav 管理ポータルに自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/broker-groupe-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Broker groupe Achat Solutions を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/broker-groupe-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Broker groupe Achat Solutions の間でシングル サインオンを構成する方法について説明します。

この記事では、Broker groupe Achat Solutions と Microsoft Entra ID を統合する方法について説明します。 Broker groupe Achat Solutions と Microsoft Entra ID を統合させると、次のことができるようになります。

- Broker groupe Achat Solutions にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Broker groupe Achat Solutions に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Broker groupe Achat Solutions でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Broker groupe Achat Solutions では、 **SP** によって開始される SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Broker groupe Achat Solutions を追加する

Microsoft Entra ID への Broker groupe Achat Solutions の統合を構成するには、マネージド SaaS アプリの一覧に Broker groupe Achat Solutions をギャラリーから追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Broker groupe Achat Solutions**」と入力します。
4. 結果パネルから **ブローカー グループ Achat Solutions** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 Office 365 ウィザードの詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides?view=o365-worldwide&preserve-view=true)。

### Broker groupe Achat Solutions 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Broker groupe Achat Solutions に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Broker groupe Achat Solutions の関連ユーザーとの間にリンク関係を確立する必要があります。

Broker groupe Achat Solutions に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Broker groupe Achat Solutions の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Broker groupe Achat Solutions のテストユーザーの作成** - Microsoft EntraでのB.Simonに対応するユーザーをBroker groupe Achat Solutionsで作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Broker groupe Achat Solutions**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://id.awsolutions.fr/auth/realms/awsolutions`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.marcoweb.fr/Marco?idp_hint=<INSTANCENAME>`

    注

    この値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには [、Broker groupe Achat Solutions クライアント サポート チーム](mailto:devops@achatsolutions.fr) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Broker groupe Achat Solutions SSO の構成

**Broker groupe Achat Solutions** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Broker groupe Achat Solutions サポート チーム](mailto:devops@achatsolutions.fr)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Broker groupe Achat Solutions のテスト ユーザーの作成

このセクションでは、Britta Simon という名前のユーザーを Broker groupe Achat Solutions で作成します。 [Broker groupe Achat Solutions サポート チーム](mailto:devops@achatsolutions.fr)と協力して、Broker groupe Achat Solutions プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Broker groupe Achat Solutions のサインオン URL にリダイレクトされます。
- Broker groupe Achat Solutions のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Broker groupe Achat Solutions] タイルを選択すると、このオプションは Broker groupe Achat Solutions のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/browserstack-single-sign-on-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に BrowserStack シングル サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/browserstack-single-sign-on-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-27
- Summary: Microsoft Entra IDから BrowserStack シングル サインオンにユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために BrowserStack シングル サインオンとMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成された Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、[BrowserStack Single Sign-on](https://www.browserstack.com) へのユーザーのプロビジョニングと解除を自動的に行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- BrowserStack Single Sign-on でユーザーを作成する
- アクセスが不要になった場合、BrowserStackのシングルサインオンからユーザーを削除する
- Microsoft Entra ID と BrowserStack シングル サインオンの間でユーザー属性の同期を維持する
- BrowserStack Single Sign-on に[シングル サインオンする](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/browserstack-single-sign-on-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- **所有者**アクセス許可を持つ BrowserStack 内のユーザー アカウント。
- BrowserStack を含む [Enterprise プラン](https://www.browserstack.com/pricing)。
- [Single Sign-on](https://www.browserstack.com/docs/enterprise/single-sign-on/azure-ad) BrowserStack との統合 (必須)。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. どのデータをMicrosoft Entra IDとBrowserStackシングルサインオンの間でマッピングするかを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように BrowserStack シングル サインオンを構成する

1. [所有者](https://www.browserstack.com/users/sign_in)アクセス許可を持つユーザーとして **BrowserStack** にログインします。
2. **[アカウント**&gt;**設定とアクセス許可]** に移動します。 **[セキュリティ]** タブをクリックします。
3. [ **自動ユーザー プロビジョニング**] で、[ **構成**] を選択します。

    [Image: 設定]
4. Microsoft Entra IDで制御するユーザー属性を選択し、**Confirm** を選択します。

    [Image: 利用者]
5. **[テナントの URL]** と **[シークレット トークン]** をコピーします。 これらの値は、BrowserStack シングル サインオン アプリケーションの [プロビジョニング] タブの [テナント URL] フィールドと [シークレット トークン] フィールドに入力されます。 **完了**を選択します。

    [Image: 認可]
6. プロビジョニングの構成が BrowserStack に保存されました。 **BrowserStack** でのユーザー プロビジョニングを有効にしてください。**Microsoft Entra ID** でのプロビジョニングのセットアップが完了したら、[Account](https://www.browserstack.com/accounts/manage-users) からの新規ユーザーの招待がブロックされるのを防ぎます。

    [Image: アカウント]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから BrowserStack シングル サインオンを追加する

Microsoft Entra アプリケーション ギャラリーから BrowserStack Single Sign-on を追加して、BrowserStack Single Sign-on へのプロビジョニングの管理を開始します。 以前に SSO を行うために BrowserStack Single Sign-on を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: BrowserStack Single Sign-on への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいてアプリ内のユーザーの作成、更新、無効化を行うために、Microsoft Entra プロビジョニング サービスを設定する手順を説明します。

#### Microsoft Entra IDで BrowserStack シングル サインオンの自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[BrowserStack Single Sign-on]** を選択します。

    [Image: アプリケーションの一覧の BrowserStack Single Sign-on リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、BrowserStack のシングル サインオン テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが BrowserStack Single Sign-on に接続できることを確認します。 接続に失敗した場合は、BrowserStack シングル サインオン アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから BrowserStack シングル サインオンに同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で BrowserStack Single Sign-on でのユーザー アカウントの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、BrowserStack シングル サインオン API で、その属性に基づくユーザーのフィルター処理がサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Bstack:2.0:User:bstack\_role | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Bstack:2.0:User:bstack\_team | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Bstack:2.0:User:bstack\_product | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- BrowserStack シングル サインオンでは、グループ のプロビジョニングはサポートされていません。
- BrowserStack Single Sign-on では、**emails[type eq "work"].value** と **userName** のソース値が同じである必要があります。

### トラブルシューティングのヒント

- トラブルシューティングのヒントについては、[こちら](https://www.browserstack.com/docs/enterprise/auto-user-provisioning/azure-ad#troubleshooting)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/browserstack-single-sign-on-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に BrowserStack シングル サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/browserstack-single-sign-on-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BrowserStack Single Sign-on の間でシングル サインオンを構成する方法について説明します。

この記事では、BrowserStack Single Sign-on と Microsoft Entra ID を統合する方法について説明します。 BrowserStack シングル サインオンを Microsoft Entra ID と統合すると、次のことが可能になります。

- BrowserStack シングル サインオンにアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して BrowserStack Single Sign-on に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BrowserStack Single Sign-on でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BrowserStack シングル サインオンでは、**SP および IDP によって開始される SSO がサポートされます**。
- BrowserStack シングル サインオンでは、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/browserstack-single-sign-on-provisioning-tutorial)。

### ギャラリーからの BrowserStack Single Sign-on の追加

Microsoft Entra ID への BrowserStack Single Sign-on の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に BrowserStack Single Sign-on を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「BrowserStack Single Sign-on**」と入力します。
4. 結果のパネルから **BrowserStack Single Sign-on** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BrowserStack シングルサインオン向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、BrowserStack シングル サインオンに対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと BrowserStack シングルサインオンの関連ユーザーとの間にリンク関係を確立する必要があります。

BrowserStack シングルサインオンに対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BrowserStack シングル サインオン SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **BrowserStack Single Sign-on テストユーザーの作成** - BrowserStack Single Sign-on で B.Simon に対応したユーザーを作成し、それを Microsoft Entra における B.Simon のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**BrowserStack シングルサインオン**&gt;**シングルサインオン** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.browserstack.com/auth/realms/<REALM_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://login.browserstack.com/auth/realms/<REALM_ID>/broker/<BROKER_ID>/endpoint`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://browserstack.com/users/sign_in`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、BrowserStack シングル サインオン サポート チーム](mailto:support@browserstack.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **BrowserStack Single Sign-on のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BrowserStack Single Sign-on の SSO の構成

**BrowserStack シングル** サインオン側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [BrowserStack シングル サインオン サポート チームに](mailto:support@browserstack.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### BrowserStack Single Sign-on のテスト ユーザーの作成

このセクションでは、BrowserStack Single Sign-on で B.Simon というユーザーを作成します。 [BrowserStack シングル サインオン サポート チーム](mailto:support@browserstack.com)と連携して、BrowserStack シングル サインオン プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

BrowserStack シングル サインオンでは自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/browserstack-single-sign-on-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる BrowserStack シングル サインオン URL にリダイレクトされます。
- BrowserStack Single Sign-on のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BrowserStack Single Sign-on に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [BrowserStack Single Sign-on] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した BrowserStack Single Sign-on に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/brushup-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Brushup を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/brushup-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Brushup 間にシングル サインオンを構成する方法について説明します。

この記事では、Brushup と Microsoft Entra ID を統合する方法について説明します。 Brushup を Microsoft Entra ID を統合すると、次のことができます。

- Brushup にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使って Brushup に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Brushup でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Brushup では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーから Brushup を追加する

Microsoft Entra ID への Brushup の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Brushup を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Brushup**」と入力します。
4. 結果のパネルから **[Brushup]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Synergi 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Brushup に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Brushup の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Brushup と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Brushup SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Brushup のテストユーザーを作成 - B.Simon に対応するユーザーを Brushup で作成し、Microsoft Entra のユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Brushup**&gt;**シングルサインオン**を開きます。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_CODE>.brushup.net/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_CODE>.brushup.net/accounts/sso?acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<COMPANY_CODE>.brushup.net/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Brushup クライアント サポート チーム](mailto:support@brushup.net)に連絡してください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Brushup の SSO の構成

**Brushup** 側にシングル サインオンを構成するには、**証明書 (PEM)** を [Brushup サポート チーム](mailto:support@brushup.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Brushup のテスト ユーザーを作成する

このセクションでは、Brushup で Britta Simon というユーザーを作成します。 [Brushup サポート チーム](mailto:support@brushup.net)と連携し、Brushup プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Brushup サインオン URL にリダイレクトされます。
- Brushup のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Brushup に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Brushup] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Brushup に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bswift-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に bswift を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bswift-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と bswift の間でシングル サインオンを構成する方法について説明します。

この記事では、bswift と Microsoft Entra ID を統合する方法について説明します。 bswift と Microsoft Entra ID を統合すると、次のことができます。

- bswift にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して bswift に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- bswift でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- bswift では、 **IDP** Initiated SSO がサポートされます。

### ギャラリーから bswift を追加する

Microsoft Entra ID への bswift の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に bswift を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「bswift**」と入力します。
4. 結果パネルから **bswift** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### bswift の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、bswift に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと bswift の関連ユーザーとの間にリンク関係を確立する必要があります。

bswift に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **bswift SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **bswiftのテストユーザーを作成** - これは、Microsoft Entraで表現されているユーザーとしてのB.Simonとリンクされている、bswift内でのB.Simonの対応ユーザーを作成するためのものです。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**bswift**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの値を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `secure.bswift.com/edge` |
    | ステージング | `secure.bswiftsandbox.com` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://secure.bswift.com/sso/ssologin.aspx` |
    | ステージング | `https://secure.bswiftsandbox.com/sso/ssologin.aspx` |
6. bswift アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、bswift アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 勤務用メール | ユーザーのメールアドレス |
    | 省略名 | &lt;company\_URL&gt; |
    | clientSSOInboundID (クライアントSSOインバウンドID) | &lt;カスタム\_ID&gt; |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### bswift SSO の構成

**bswift** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[bswift サポート チーム](mailto:bswiftConnectionSupport@bswift.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### bswift テスト ユーザーの作成

このセクションでは、bswift で B.Simon というユーザーを作成します。 [bswift サポート チーム](mailto:bswiftConnectionSupport@bswift.com)と協力して、bswift プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した bswift に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで bswift タイルを選択すると、SSO を設定した bswift に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bugcrowd-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Bugcrowd を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bugcrowd-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-15
- Summary: Microsoft Entra ID と Bugcrowd の間のシングル サインオンを構成する方法について説明します。

この記事では、Bugcrowd と Microsoft Entra ID を統合する方法について説明します。 Bugcrowd と Microsoft Entra ID を統合すると、次のことができます。

- Bugcrowd にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Bugcrowd に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bugcrowd のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Bugcrowd では、**SP と IDP** の両方で開始される SSO がサポートされます。

### ギャラリーからの Bugcrowd の追加

Microsoft Entra ID への Bugcrowd の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Bugcrowd を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Bugcrowd**」と入力します。
4. 結果パネルから **[Bugcrowd]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bugcrowd 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Bugcrowd に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Bugcrowd の関連ユーザーとの間にリンク関係を確立する必要があります。

Bugcrowd に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bugcrowd の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bugcrowd テストユーザーを作成する** - BugcrowdでMicrosoft Entra IDのB.Simonに対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Bugcrowd**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスに、`https://identity.bugcrowd.com/organizations/<Organization_ID>` の形式で URL を入力します。

    b。 **[応答 URL]** ボックスに、`https://identity.bugcrowd.com/organizations/<Organization_ID>/sso/acs` のパターンを使用して URL を入力します
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://identity.bugcrowd.com`」と入力します。

    注意

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Bugcrowd サポート チーム](https://bugcrowd-support.freshdesk.com/support/tickets/new)にお問い合わせください。 Microsoft Entra 管理センターの **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Bugcrowd のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の URL をコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bugcrowd SSO の構成

**Bugcrowd** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、Microsoft Entra 管理センターからコピーした適切な URL を [Bugcrowd サポート チーム](https://bugcrowd-support.freshdesk.com/support/tickets/new)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Bugcrowd テスト ユーザーの作成

このセクションでは、Bugcrowd で B.Simon というユーザーを作成します。 [Bugcrowd サポート チーム](https://bugcrowd-support.freshdesk.com/support/tickets/new)と連携して、Bugcrowd プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Bugcrowd のサインオン URL にリダイレクトされます。
- Bugcrowd のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Bugcrowd に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Bugcrowd] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Bugcrowd に自動的にサインインされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bugsnag-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Bugsnag を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bugsnag-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Bugsnag の間のシングル サインオンを構成する方法について説明します。

この記事では、Bugsnag と Microsoft Entra ID を統合する方法について説明します。 Bugsnag を Microsoft Entra ID と統合すると、次のことが可能になります。

- Bugsnag にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Bugsnag に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bugsnag でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Bugsnag では、**SP と IDP** によって開始される SSO がサポートされます。
- Bugsnag では、 **Just In Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Bugsnag の追加

Microsoft Entra ID への Bugsnag の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Bugsnag を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Bugsnag**」と入力します。
4. 結果パネルから **Bugsnag** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Bugsnag に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Bugsnag に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Bugsnag の関連ユーザーとの間にリンク関係を確立する必要があります。

Bugsnag で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bugsnag SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bugsnag テストユーザーを作成** - Bugsnag で Microsoft Entra の B.Simon に対応するユーザーを作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Bugsnag]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.bugsnag.com/user/sign_in/saml/<org_slug>/acs`

    注

    応答 URL は、実際の値ではありません。 実際の応答 URL でこの値を更新します。 この値を取得するには [、Bugsnag クライアント サポート チーム](mailto:support@bugsnag.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.bugsnag.com/user/identity_provider`
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bugsnag SSO の構成

1. Bugsnag の Web サイトに管理者としてサインインします。
2. BugSnag の設定で、[ **組織の設定] -&gt; [シングル サインオン**] を選択します。

    [Image: [認証] ページのスクリーンショット。]
3. **[シングル サインオンの有効化]** ページで次の手順を実行します。

    [Image: [SSO 設定] ページのスクリーンショット。]

    a. **[SAML/IdP メタデータ**] フィールドに、Azure portal からコピーした**アプリのフェデレーション メタデータ URL** の値を入力します。

    b。 **SAML エンドポイント URL** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    c. **[SSO の有効化] を選択します**。

注

Bugsnag SSO の構成の詳細については、 [この](https://docs.bugsnag.com/product/single-sign-on/other/#setup-saml) ガイドに従ってください。

#### Bugsnag のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Bugsnag に作成します。 Bugsnag では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Bugsnag にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Bugsnag のサインオン URL にリダイレクトされます。
- Bugsnag のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Bugsnag に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Bugsnag タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Bugsnag に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bullseyetdp-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に BullseyeTDP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bullseyetdp-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-02-27
- Summary: Microsoft Entra IDから BullseyeTDP にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために BullseyeTDP と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成されると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [BullseyeTDP](https://www.bullseyeengagement.com/) に自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- BullseyeTDP でユーザーを作成します。
- accessが不要になったら、BullseyeTDP のユーザーを削除します。
- Microsoft Entra IDと BullseyeTDP の間でユーザー属性の同期を維持します。
- BullseyeTDP に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bullseyetdp-tutorial)します。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- テナント URL とシークレット トークン。

### 手順 1: プロビジョニングの展開を計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとBullseyeTDPの間でどのデータをマッピングするかを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように BullseyeTDP を構成する

SCIM トークンを取得するには、 [BullseyeTDP サポート](mailto:hello@bullseyetdp.com) にお問い合わせください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから BullseyeTDP を追加する

Microsoft Entra アプリケーション ギャラリーから BullseyeTDP を追加して、BullseyeTDP へのプロビジョニングの管理を開始します。 SSO 用に BullseyeTDP を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: BullseyeTDP への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザーまたはグループの割り当てに基づいて、BullseyeTDP でユーザーやグループを作成、更新、無効化するために Microsoft Entra プロビジョニング サービスを構成する手順を説明します。

#### Microsoft Entra IDで BullseyeTDP の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **BullseyeTDP** を選択します。

    [Image: アプリケーションの一覧の BullseyeTDP リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、BullseyeTDP テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが BullseyeTDP に接続できることを確認します。 接続に失敗した場合は、BullseyeTDP アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから BullseyeTDP に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で BullseyeTDP のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が BullseyeTDP API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ | フィルター処理でサポートされます | BullseyeTDP で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | externalId | 糸 | ✓ | ✓ |
    | ユーザー種類 | 糸 |  | ✓ |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:manager | 関連項目 |  |  |
    | アクティブ | ブール値 |  |  |
    | タイトル | 糸 |  | ✓ |
    | emails[type eq "work"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | アドレス[タイプ eq "職場"].ローカリティ | 糸 |  | ✓ |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:employeeNumber | 糸 |  |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bullseyetdp-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に BullseyeTDP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bullseyetdp-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と BullseyeTDP の間のシングル サインオンを構成する方法について説明します。

この記事では、BullseyeTDP と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と BullseyeTDP を統合すると、次のことができます。

- BullseyeTDP へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで BullseyeTDP に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- BullseyeTDP でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- BullseyeTDP では、 **IDP** によって開始される SSO がサポートされます。
- BullseyeTDP では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bullseyetdp-provisioning-tutorial)。

### ギャラリーから BullseyeTDP を追加する

Microsoft Entra ID への BullseyeTDP の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に BullseyeTDP を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「BullseyeTDP**」と入力します。
4. 結果パネルから **BullseyeTDP** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### BullseyeTDP の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、BullseyeTDP に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと BullseyeTDP の関連ユーザーとの間にリンク関係を確立する必要があります。

BullseyeTDP に対して Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **BullseyeTDP の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **BullseyeTDP テスト ユーザーの作成** - BullseyeTDP で B.Simon に対応するユーザーアカウントを作成し、Microsoft Entra のユーザー表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[BullseyeTDP]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. BullseyeTDP アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、BullseyeTDP アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | AccessToken | &lt; AccessTokenValue &gt; |
    | アプリケーションキー | &lt;アプリケーションキー値&gt; |
    | 従業員ID | user.employeeid |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **BullseyeTDP のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### BullseyeTDP SSO の構成

**BullseyeTDP** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [BullseyeTDP サポート チーム](mailto:hello@bullseyetdp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### BullseyeTDP テスト ユーザーの作成

このセクションでは、BullseyeTDP で Britta Simon というユーザーを作成します。 [BullseyeTDP サポート チーム](mailto:hello@bullseyetdp.com)と協力して、BullseyeTDP プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した BullseyeTDP に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [BullseyeTDP] タイルを選択すると、SSO を設定した BullseyeTDP に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/burp-suite-enterprise-edition-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Burp Suite Enterprise Edition を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/burp-suite-enterprise-edition-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-05-26
- Summary: Microsoft Entra ID と Burp Suite Enterprise Edition の間でシングル サインオンを構成する方法について説明します。

この記事では、Burp Suite Enterprise Edition と Microsoft Entra ID を統合する方法について説明します。 Burp Suite Enterprise Edition と Microsoft Entra ID を統合すると、次のことが可能になります。

- Burp Suite Enterprise Edition にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Burp Suite Enterprise Edition に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Burp Suite Enterprise Editionは、次の[一連のクラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- シングル サインオン (SSO) が有効な Burp Suite Enterprise Edition サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Burp Suite Enterprise Edition では、**IDP** Initiated SSO がサポートされます。
- Burp Suite Enterprise Edition では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Burp Suite Enterprise Edition を追加する

Burp Suite Enterprise Edition と Microsoft Entra ID の統合を構成するには、マネージド SaaS アプリの一覧に Burp Suite Enterprise Edition をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Burp Suite Enterprise Edition**」と入力します。
4. 結果パネルから **[Burp Suite Enterprise Edition]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Burp Suite Enterprise Edition 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Burp Suite Enterprise Edition に対して Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Burp Suite Enterprise Edition の関連ユーザーとの間にリンク関係を確立する必要があります。

Burp Suite Enterprise Edition に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Burp Suite Enterprise Edition の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Burp Suite Enterprise Edition テストユーザーの作成** - Burp Suite Enterprise Edition で B.Simon に対応するテストユーザーを作成し、Microsoft Entra の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Burp Suite Enterprise Edition]**&gt;**[シングル サインオン] ** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<BURPSUITEDOMAIN:PORT>/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<BURPSUITEDOMAIN:PORT>/api-internal/saml/acs`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Burp Suite Enterprise Edition クライアント サポート チーム](mailto:support@portswigger.net)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Burp Suite Enterprise Edition アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Burp Suite Enterprise Edition アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Burp Suite Enterprise Edition のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Burp Suite Enterprise Edition の SSO の構成

**Burp Suite Enterprise Edition** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を、[Burp Suite Enterprise Edition サポート チーム](mailto:support@portswigger.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Burp Suite Enterprise Edition のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Burp Suite Enterprise Edition に作成します。 Burp Suite Enterprise Edition では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Burp Suite Enterprise Edition にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Burp Suite Enterprise Edition に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで Burp Suite Enterprise Edition タイルを選択すると、SSO を設定した Burp Suite Enterprise Edition に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/businessmap-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Businessmap を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/businessmap-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Businessmap の間のシングル サインオンを構成する方法について説明します。

この記事では、Businessmap と Microsoft Entra ID を統合する方法について説明します。 Businessmap と Microsoft Entra ID を統合すると、次のことができます。

- Businessmap にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Businessmap に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Businessmap でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Businessmap では、**SP および IDP による単一サインオン (SSO)** がサポートされています。
- Businessmap では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Businessmap を追加する

Microsoft Entra ID への Businessmap の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Businessmap を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Businessmap**」と入力します。
4. 結果パネルから **Businessmap** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Businessmap 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Businessmap に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Businessmap の関連ユーザーとの間にリンク関係を確立する必要があります。

Businessmap に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Businessmap の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Businessmap のテストユーザーを作成** - Microsoft Entra におけるユーザーの表現とリンクされた Businessmap 内の B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**ビジネスマップ**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.kanbanize.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.kanbanize.com/saml/acs`

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] ボックスに、値を入力します。 `/ctrl_login/saml_login`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.kanbanize.com`

    注意

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Businessmap クライアント サポート チーム](mailto:support@businessmap.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Businessmap アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。一方、nameidentifier は **user.userprincipalname** にマップされています。 Businessmap アプリケーションでは、nameidentifier が **user.mail** にマップされることを想定しているため、[編集] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: 画像]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Businessmap のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Businessmap の SSO を構成する

1. 別の Web ブラウザー ウィンドウで、Businessmap 企業サイトに管理者としてサインインします
2. ページの右上に移動し、[設定ロゴ **]** を選択します。

    [Image: ビジネスマップの設定を示すスクリーンショット。]
3. [管理] パネル ページで、メニューの左側から [ **統合** ] を選択し、[ **シングル サインオン**] を有効にします。

    [Image: [統合] が選択された [管理] パネルを示すスクリーンショット。]
4. [統合] セクションで、**構成** を選択して、**Single Sign-On Integration** ページを開きます。

    [Image: Businessmap の統合を示すスクリーンショット。]
5. [ **単一 Sign-On 統合** ] ページの [ **構成]** で、次の手順を実行します。

    [Image: この手順の値を入力する [Single Sign-On Integration](単一の Sign-On 統合) ページを示すスクリーンショット。]

    ある。 **Idp エンティティ ID** ボックスに、前にコピーした **Microsoft Entra 識別子**の値を貼り付けます。

    b。 **[Idp Login Endpoint]\(Idp ログイン エンドポイント**\) ボックスに、前にコピーした**ログイン URL** の値を貼り付けます。

    c. **[Idp ログアウト エンドポイント**] ボックスに、前にコピーした**ログアウト URL** の値を貼り付けます。

    d. [ **電子メール] ボックスの [属性名** ] に、この値を入力します。 `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`

    え **名の属性名** テキストボックスに、この値を入力します `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/givenname`

    f. **姓の属性名** ボックスに、この値を入力します `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/surname`

    注意

    これらの値は、Azure Portal の [ユーザー属性] セクションから対応する名前空間と名前の値を結合することで取得できます。

    ジー メモ帳で、ダウンロードした base-64 でエンコードされた証明書を開き、その内容 (開始マーカーと終了マーカーなし) をコピーして、 **Idp X.509 証明書** ボックスに貼り付けます。

    h. **[SSO と Businessmap の両方でログインを有効にする] をオンにします**。

    一. [ **設定の保存] を選択します**。

#### Businessmap のテスト ユーザーを作成する

このセクションでは、B. Simon というユーザーを Businessmap に作成します。 Businessmap では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Businessmap にユーザーがまだ存在していない場合は、認証後に新規に作成されます。 ユーザーを手動で作成する必要がある場合は、 [Businessmap クライアント サポート チーム](mailto:support@businessmap.io)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Businessmap のサインオン URL にリダイレクトされます。
- Businessmap のサインオン URL に直接移動し、そこからサインイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Businessmap に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Businessmap] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Businessmap に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bustle-b2b-transport-systems-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用にブッスル B2B トランスポート システムを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bustle-b2b-transport-systems-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-03
- Summary: ユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。Microsoft Entra IDから、バーチブル B2B トランスポート システムに対してプロビジョニングとプロビジョニング解除を行います。

この記事では、自動ユーザー プロビジョニングを構成するために、Bustle B2B Transport Systems と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra ID が構成されると、Microsoft Entra プロビジョニング サービスを使用して、[Bustle B2B トランスポート システム](https://app.bustle.tech)にユーザーを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Bustle B2B Transport Systems でユーザーを作成する。
- アクセスが不要になった場合、Bustle B2B トランスポート システムのユーザーを削除します。
- Microsoft Entra IDとブライブ B2B トランスポート システムの間でユーザー属性の同期を維持します。
- バーチブル B2B トランスポート システムへの[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- 管理者アクセス許可がある Bustle B2B Transport Systems のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
- プロビジョニングの対象範囲にいるユーザーを決定します。
- Microsoft Entra IDとBustle B2B トランスポート システムの間でマップするデータを決定します。

### 手順 2: Microsoft Entra IDを使用したプロビジョニングをサポートするように、スライル B2B トランスポート システムを構成する

Microsoft Entra IDを使用したプロビジョニングをサポートするように、ブッスル B2B トランスポート システムのサポートに問い合わせて、バーチブル B2B トランスポート システムを構成してください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Bustle B2B Transport Systems を追加する

Microsoft Entra アプリケーション ギャラリーから Bustle B2B Transport Systems を追加して、Bustle B2B Transport Systems へのプロビジョニングの管理を開始します。 SSO のために Bustle B2B Transport Systems を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Bustle B2B Transport Systems への自動ユーザー プロビジョニングを構成する

Microsoft Entraのプロビジョニングサービスを構成して、Microsoft Entra IDのユーザー割り当てに基づきTestAppのユーザーを作成、更新、無効化する手順をこのセクションで説明します。

#### Microsoft Entra IDで、Bustle B2B トランスポート システムの自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **ブッスル B2B トランスポート システム**] を選択します。

    [Image: アプリケーションの一覧にある [Bustle B2B トランスポート システム] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **テナント URL** フィールドに、Bustle B2B トランスポート システムのテナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra ID が Bustle B2B Transport Systems に接続できることを確認します。 接続に失敗した場合は、Bustle B2B Transport Systemsのアカウントに必要な管理者権限があることを確認し、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra ID から Bustle B2B トランスポート システムに同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のために、バーチブル B2B トランスポート システムのユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が、バーチブル B2B トランスポート システム API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Bustle B2B Transport Systems で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/buttonwood-central-sso-tutorial"} -->
## Microsoft Entra ID で Buttonwood Central SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/buttonwood-central-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Buttonwood Central SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Buttonwood Central SSO と Microsoft Entra ID を統合する方法について説明します。 Buttonwood Central SSO を Microsoft Entra ID と統合すると、次のことができます。

- Buttonwood Central SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Buttonwood Central SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Buttonwood Central SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Buttonwood Central SSO では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Buttonwood Central SSO を追加する

Microsoft Entra ID への Buttonwood Central SSO の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Buttonwood Central SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Buttonwood Central SSO**」と入力します。
4. 結果パネルから **Buttonwood Central SSO を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Buttonwood Central SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Buttonwood Central SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Buttonwood Central SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Buttonwood Central SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Buttonwood Central の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Buttonwood Central SSO テスト ユーザーの作成** - Buttonwood Central SSO で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Buttonwood Central SSO**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `http://adfs.bcx.buttonwood.net/adfs/services/trust`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://adfs.bcx.buttonwood.net/adfs/ls/`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://exchange.bcx.buttonwood.net/User/FederatedLogin`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Buttonwood Central SSO の構成

**Buttonwood Central SSO** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Buttonwood Central SSO サポート チーム](mailto:support@buttonwood.com.au)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Buttonwood Central SSO テスト ユーザーの作成

このセクションでは、Buttonwood Central SSO で Britta Simon というユーザーを作成します。 [Buttonwood Central SSO サポート チーム](mailto:support@buttonwood.com.au)と協力して、Buttonwood Central SSO プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Buttonwood Central SSO サインオン URL にリダイレクトされます。
- Buttonwood Central SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Buttonwood Central SSO] タイルを選択すると、このオプションは Buttonwood Central SSO のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/bynder-tutorial"} -->
## Bynder を構成します。 (Microsoft Entra ID を使用したシングル サインオン用) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/bynder-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-04-05
- Summary: Microsoft Entra ID と Bynder の間でシングル サインオンを構成する方法について説明します。

この記事では、Bynder と Microsoft Entra ID を統合する方法について説明します。 Bynder と Microsoft Entra ID を統合すると、次のことができます。

- Bynder にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Bynder に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Bynder でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Bynder では、**SP および IDP** 開始の SSO がサポートされます。
- Bynder では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### Bynder のアプリケーションを追加する

Microsoft Entra ID への Bynder の統合を構成するには、次の 2 つの方法を使用できます。

1. Bynder 用のカスタム アプリケーションを作成します。
2. ギャラリーから Bynder アプリを追加します。

Important

カスタム アプリケーションを作成することを強くお勧めします。 Bynder では、カスタム アプリケーションでのみ使用できる SCIM プロビジョニングがサポートされるようになりました。 ギャラリーから Bynder アプリケーションを使用すると、統合に対するこの機能の可用性が制限されます。

カスタム アプリケーションを作成するには、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用できます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

SCIM プロビジョニングを使用する予定がない場合に、ギャラリーから Bynder アプリを使用する場合は、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Bynder**」と入力します。
4. 結果パネルから **Bynder** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Bynder に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Bynder の関連ユーザーとの間にリンク関係を確立する必要があります。

Bynder に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Bynder SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Bynderテストユーザーを作成する** - Britta Simonと対応するユーザーをBynderで作成し、Microsoft EntraユーザーであるBritta Simonとリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### 新しいBynderの設定を作成する

まず、Bynder アカウントにログインし、 [次](https://support.bynder.com/hc/articles/6614562131474#UUID-4f8db699-3079-496d-d29e-706b28e4631a_section-idm4615912229660833479548407237) の手順に従ってポータルで新しいログイン構成を作成する必要があります。 これにより、Microsoft Entra との接続を設定するために必要なすべての識別子を生成できます。 新しい構成の識別子を保存します。Microsoft Entra SAML SSO を設定するために必要です。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Bynder** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] を示すスクリーンショット。]

    注

    BYNDER\_CONFIG\_IDでは、 **Сreate New Bynder Configuration** セクションから取得した識別子値を使用します。
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    既定のドメインの場合: `https://<COMPANY_NAME>.bynder.com/v7/idp/sso/saml/<BYNDER_CONFIG_ID>/metadata`

    カスタム ドメインの場合: `https://<SUBDOMAIN>.<DOMAIN>.com/v7/idp/sso/saml/<BYNDER_CONFIG_ID>/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    既定のドメインの場合: `https://<COMPANY_NAME>.bynder.com/v7/idp/sso/saml/<BYNDER_CONFIG_ID>/acs`

    カスタム ドメインの場合: `https://<SUBDOMAIN>.<DOMAIN>.com/v7/idp/sso/saml/<BYNDER_CONFIG_ID>/acs`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    既定のドメインの場合: `https://<COMPANY_NAME>.bynder.com/v7/idp/sso/saml/<BYNDER_CONFIG_ID>/initialize`

    カスタム ドメインの場合: `https://<SUBDOMAIN>.<DOMAIN>.com/v7/idp/sso/saml/<BYNDER_CONFIG_ID>/initialize`
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **メタデータ XML** を見つけて **[ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Bynder のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Bynder SSO の構成

このドキュメントの SAML SSO の構成に従って**、Bynder** 側で [SSO を構成](https://support.bynder.com/hc/articles/6614562131474#UUID-4f8db699-3079-496d-d29e-706b28e4631a_section-idm4615912229660833479548407237)できます

#### Bynder テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Bynder に作成します。 Bynder では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Bynder にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Bynder サポート チーム](https://www.bynder.com/en/support/)にお問い合わせください。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Bynder のサインオン URL にリダイレクトされます。
- Bynder のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Bynder に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Bynder] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Bynder に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/c3m-cloud-control-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に C3M Cloud Control を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/c3m-cloud-control-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と C3M Cloud Control 間のシングル サインオンを構成する方法について説明します。

この記事では、C3M Cloud Control と Microsoft Entra ID を統合する方法について説明します。 C3M Cloud Control を Microsoft Entra ID と統合すると、次のことが可能になります。

- C3M Cloud Control にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで C3M Cloud Control に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な C3M Cloud Control サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- C3M Cloud Control では、 **SP** によって開始される SSO がサポートされます。
- C3M Cloud Control では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの C3M Cloud Control の追加

Microsoft Entra ID への C3M Cloud Control の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に C3M Cloud Control を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「C3M Cloud Control**」と入力します。
4. 結果パネルから **C3M Cloud Control** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### C3M Cloud Control に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、C3M Cloud Control に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと C3M Cloud Control の関連ユーザー間にリンク関係を確立する必要があります。

C3M Cloud Control に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **C3M Cloud Control の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **C3M Cloud Control のテスト ユーザーの作成** - C3M Cloud Control で、Microsoft Entra 内のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**C3M Cloud Control**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<C3MCLOUDCONTROL_ACCESS_URL>/api/sso/saml`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<C3MCLOUDCONTROL_ACCESS_URL>/api/sso/saml`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<C3MCLOUDCONTROL_ACCESS_URL>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [C3M Cloud Control クライアント サポート チーム](mailto:support@c3m.io) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **C3M Cloud Control のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### C3M Cloud Control の SSO の構成

C3M Cloud Control の SSO を構成するには、 [ドキュメント](https://c3m.freshdesk.com/support/solutions/articles/44001946272-configuring-sso-using-saml-azure)に従ってください。

#### C3M Cloud Control のテスト ユーザーの作成

このセクションでは、B. Simon というユーザーを C3M Cloud Control に作成します。 C3M Cloud Control では、Just-In-Time ユーザー プロビジョニングがサポートされます。この設定は既定で有効です。 このセクションにはアクション項目はありません。 C3M Cloud Control にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる C3M Cloud Control のサインオン URL にリダイレクトされます。
- C3M Cloud Control のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [C3M Cloud Control] タイルを選択すると、このオプションは C3M Cloud Control のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cakehr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に CakeHR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cakehr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CakeHR 間にシングル サインオンを構成する方法について学習します。

この記事では、CakeHR と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と CakeHR を統合すると、次のことができます。

- CakeHR にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して CakeHR に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- CakeHR でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- CakeHR では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの CakeHR の追加

Microsoft Entra ID への CakeHR の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に CakeHR を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**CakeHR**」と入力します。
4. 結果のパネルから **[CakeHR]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### CakeHR 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、CakeHR に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと CakeHR の関連ユーザーとの間にリンク関係を確立する必要があります。

CakeHR に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **CakeHR SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **CakeHR テスト ユーザーの作成 - CakeHR** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**CakeHR**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CAKE_DOMAIN>.cake.hr/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CAKE_DOMAIN>.cake.hr/services/saml/consume`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 この値を取得するには、[CakeHR クライアント サポート チーム](mailto:info@cake.hr)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集]
7. **[SAML 署名証明書]** セクションで **[THUMBPRINT](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/拇印)** の値をコピーし、メモ帳に保存します。

    [Image: 拇印の値をコピーする]
8. **[CakeHR のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### CakeHR SSO の構成

1. 別の Web ブラウザー ウィンドウで、CakeHR 企業サイトに管理者としてサインインします
2. ページの右上隅にある [ **プロファイル** ] を選択し、[ **設定]** に移動します。

    [Image: このスクリーンショットは、[設定] が選択された状態の [プロファイル] を示しています。]
3. メニュー バーの左側にある **INTEGRATIONS**&gt;**SAML SSO** を選択し、次の手順を実行します。

    [Image: このスクリーンショットは、[設定] ペインを示しています。ここで、これらの手順を実行します。]

    ある。 **[Entity ID](エンティティ ID)** ボックスに、「`cake.hr`」と入力します。

    b。 **[認証 URL]** テキスト ボックスに、**ログイン URL** の値を貼り付けます。

    c. **[Key fingerprint (SHA1 format)] (キーのフィンガープリント (SHA1 形式))** テキスト ボックスに**フィンガープリント**の値を貼り付けます。

    d. **[Enable Single Sign on](シングル サインオンを有効にする)** ボックスをオンにします。

    え **保存** を選択します。

#### CakeHR テスト ユーザーの作成

Microsoft Entra ユーザーが CakeHR にサインインできるようにするには、CakeHR にプロビジョニングする必要があります。 CakeHR では、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. セキュリティ管理者として CakeHR にサインインします。
2. メニュー バーの左側にある **COMPANY**&gt;**ADD** を選択します。

    [Image: このスクリーンショットは、[COMPANY](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/会社) と [ADD](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/追加) が選択された状態の CakeHR を示しています。]
3. **[Add new employee](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しい従業員の追加)** ポップアップで、以下の手順を実行します。

    [Image: このスクリーンショットは、[Add new employee](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しい従業員の追加) を示しています。ここで、これらの手順を実行します。]

    ある。 **[Full name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/氏名)** ボックスに、ユーザーの氏名を入力します (例: B.Simon)。

    b。 **[Work email](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/仕事用メール)** ボックスに、`B.Simon@contoso.com` など、ユーザーのメール アドレスを入力します。

    c. [ **アカウントの作成] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションは、ログイン フローを開始できる CakeHR サインオン URL にリダイレクトされます。
- CakeHR のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [CakeHR] タイルを選択すると、このオプションは CakeHR のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/campus-cafe-tutorial"} -->
## Microsoft Entra ID で Campus Café for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/campus-cafe-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Campus Café 間のシングル サインオンを構成する方法について説明します。

この記事では、Campus Café と Microsoft Entra ID を統合する方法について説明します。 Campus Café を Microsoft Entra ID と統合すると、次のことができます。

- Campus Café にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Campus Café に自動的にサインインできるようにする。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Campus Café でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Campus Café では、**SP** によって開始される SSO がサポートされます。

### ギャラリーからの Campus Café の追加

Microsoft Entra ID への Campus Café の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Campus Café を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Campus Café**」と入力します。
4. 結果パネルで **[Campus Café]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Campus Café 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Campus Café に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Campus Café の関連ユーザーとの間にリンク関係を確立する必要があります。

Campus Café に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Campus Café の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Campus Café のテスト ユーザーを作成** - Campus Café で B.Simon に対応するユーザーを作成し、Microsoft Entra におけるユーザーの表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Campus Café**&gt;**シングルサインオン**を参照します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、**サービス プロバイダー メタデータ ファイル**がある場合は、次の手順に従います。

    a. [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択する]

    c. メタデータ ファイルが正常にアップロードされると、**識別子**の値が、 **[基本的な SAML 構成]** セクションに自動的に設定されます。

    **[サインオン URL]** ボックスに、`https://{SSO}-web.scansoftware.com/cafeweb/loginsso` という形式で URL を入力します。

    Note

    **識別子**の値が自動的に設定されない場合は、要件に従って値を手動で入力してください。 サインオン URL の値は実際の値ではありません。 この値を実際のサインオン URL で更新してください。 この値を取得するには、[Campus Café クライアント サポート チーム](mailto:support@campuscafesoftware.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Campus Café のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Campus Cafe の SSO の構成

**Campus Café** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Campus Café サポート チーム](mailto:support@campuscafesoftware.com)に送信する必要があります。 サポート チームはこれを設定して、SAML SSO 接続が両方の側で正しく設定されるようにします。

#### Campus Cafe のテスト ユーザーの作成

このセクションでは、Campus Café で B.Simon というユーザーを作成します。 [Campus Café サポート チーム](mailto:support@campuscafesoftware.com)と連携して、Campus Café プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Campus Cafe のサインオン URL にリダイレクトされます。
- Campus Cafe のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Campus Cafe] タイルを選択すると、このオプションは Campus Cafe のサインオン URL にリダイレクトされます。 詳細については、[Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/canva-provisioning-tutorial"} -->
## Microsoft Entra IDを使用して自動ユーザー プロビジョニング用にCanvaを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/canva-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-03
- Summary: Microsoft Entra IDからCanvaにユーザーアカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザープロビジョニングを構成するためにCanvaとMicrosoft Entra IDの両方で実行する必要がある手順について説明します。 構成された Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Canva](https://www.canva.com/) に自動的にプロビジョニングと解除を行います。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Canva でユーザーを作成する。
- accessが不要になった場合は、Canvaのユーザーを削除します。
- Microsoft Entra IDとCanvaの間でユーザー属性の同期を維持します。
- Canva でグループとグループ メンバーシップをプロビジョニングする。
- Canvaへの[シングルサインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/canva-tutorial)(推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Canva テナント。
- 管理者のアクセス許可を持つ Canva のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとCanvaの間でマッピングするデータを決定する。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするようにCanvaを構成する

Microsoft Entra IDでのプロビジョニングをサポートするようにCanvaを構成するには、Canvaサポートにお問い合わせください。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Canva を追加する

Microsoft Entra アプリケーション ギャラリーから Canva を追加して、Canva へのプロビジョニングの管理を開始します。 以前に Canva で SSO を設定済みである場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Canva への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザーまたはグループの割り当てに基づき、TestApp においてユーザーやグループを作成、更新、または無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDでCanvaの自動ユーザープロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **Canva**を選択します。

    [Image: アプリケーションの一覧の [Canva] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Canva テナントの URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDがCanvaに接続できることを確認します。 接続に失敗した場合は、お使いのCanvaアカウントに必要な管理者権限があることを確認してから、もう一度お試しください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. Microsoft Entra IDからCanvaに同期されるユーザー属性を**Attribute-Mapping**セクションで確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のためにCanvaのユーザーアカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理がCanva APIでサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Canva で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | externalId | 糸 |  |  |
    | emails[type eq "仕事"].value | 糸 |  | ✓ |
    | name.givenName | 糸 |  |  |
    | name.familyName | 糸 |  |  |
    | displayName | 糸 |  |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping**セクションで、Microsoft Entra IDからCanvaに同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作のためにCanvaのグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Canva で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/canva-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用にCanvaを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/canva-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Canva の間のシングル サインオンを構成する方法について説明します。

この記事では、Canvaと Microsoft Entra ID を統合する方法について説明します。 Canva は、写真エディター、ビデオ エディター、グラフィック デザイン ツールのすべてが 1 つになったアプリです。 見事なソーシャルメディアの投稿、ビデオ、カード、チラシ、写真コラージュなどを作成します。 Microsoft Entra ID と Canva を統合すると、次のことが可能になります。

- Canva にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Canva に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

Canva の Microsoft Entra シングル サインオンをテスト環境で構成してテストする。 Canvaでは、 **IDP** 開始シングルサインオンと **ジャストインタイム** ユーザープロビジョニングがサポートされています。 また、自動化 [されたユーザープロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/canva-provisioning-tutorial)もサポートしています。

### [前提条件]

Microsoft Entra ID を Canva と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Canva のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Canva アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Canva を追加する

Microsoft Entra アプリケーション ギャラリーから Canva を追加して、Canva とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Canva]**&gt;**[シングル サインオン]** の順に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. Canva アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **一意のユーザー識別子**の既定値は **user.userprincipalname** ですが、ユーザーのオブジェクト ID にマップすることが想定されています。そのため、リストから **user.objectid** 属性を使用するか、組織の構成に基づいて適切な属性値を使用し、ドロップダウンから [名前識別子の形式] を **[永続的]** として選択できます。

    [Image: カスタム属性マッピングの画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **設定Canva** ]セクションで、要件に基づいて適切なURLをコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### Canva SSO を構成する

**Canva**側でシングルサインオンを構成するには、ダウンロードした**証明書(Base64)**とアプリケーション構成からコピーした適切なURLを[Canvaサポートチーム](mailto:support@canva.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Canva のテスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを Canva に作成します。 Canva では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Canva にユーザーがまだ存在していない場合、一般的には認証後に新しいものが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定したCanvaに自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイアプリでCanvaタイルを選択すると、SSOを設定したCanvaに自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/canvas-lms-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Canvas を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/canvas-lms-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Canvas の間のシングル サインオンを構成する方法について説明します。

この記事では、Canvas と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Canvas を統合すると、次のことができます。

- Canvas へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが Microsoft Entra アカウントで Canvas に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Canvas でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Canvas では、 **SP** Initiated SSO がサポートされます。

### ギャラリーからの Canvas の追加

Canvas の Microsoft Entra ID への統合を構成するには、Canvas をギャラリーから管理対象 SaaS アプリの一覧に追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Canvas**」と入力します。
4. 結果パネルから **[キャンバス]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Canvas の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Canvas に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Canvas の関連ユーザーとの間にリンク関係を確立する必要があります。

Canvas に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Canvas SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Canvas のテスト ユーザーの作成** - Canvas で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Canvas]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.instructure.com`

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant-name>.instructure.com/saml2`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、Canvas クライアント サポート チームに問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Canvas の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Canvas 企業サイトに管理者としてログインします。
2. **Microsoft OneNote &gt; 認証&gt;管理者**に移動します。
3. SAML として認証サービスを選択 **します**。

    [Image: Canvas]
4. [ **現在のプロバイダー** ] ページで、次の手順を実行します。

    [Image: 現在の統合]

    a. **[IdP メタデータ URI]** テキストボックスに、**[アプリのフェデレーション メタデータ URL]** の値を貼り付けます。

    b。 **[保存] を選択します**。

#### Canvas のテスト ユーザーの作成

Microsoft Entra ユーザーが Canvas にログインできるようにするには、そのユーザーを Canvas にプロビジョニングする必要があります。 Canvas の場合、ユーザー プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. **Canvas** テナントにログインします。
2. **Microsoft OneNote &gt; People &gt;管理者に**移動します。
3. **[+人々]** を選択します。
4. [新しいユーザーの追加] ダイアログ ページで、次の手順を実行します。

    [Image: ユーザーを追加]

    a. [ **Full Name** ]\(フル ネーム\) ボックスに、 **BrittaSimon** などのユーザーの名前を入力します。

    b。 [ **電子メール** ] ボックスに、ユーザーの電子メール ( **brittasimon@contoso.com**など) を入力します。

    c. [ **ユーザーの追加] を選択します**。

注

他の Canvas ユーザー アカウント作成ツールや、Canvas から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Canvas サインオン URL にリダイレクトされます。
- Canvas のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Canvas] タイルを選択すると、SSO を設定した Canvas に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cappm-tutorial"} -->
## Microsoft Entra ID で Clarity for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cappm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Clarity の間でシングル サインオンを構成する方法について説明します。

この記事では、Clarity と Microsoft Entra ID を統合する方法について説明します。 Clarity と Microsoft Entra ID を統合すると、次のことができます。

- Clarity へアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Clarity に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Clarity でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Clarity では、IDP **による SSO** をサポートします。

### ギャラリーから Clarity を追加する

Microsoft Entra ID への Clarity の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Clarity を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [**ギャラリーから追加**] セクションで、検索ボックス **に「Clarity**」と入力します。
4. 結果のパネルから **Clarity** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Microsoft Entra SSO for Clarity の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Clarity に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Clarity の関連ユーザーとの間にリンク関係を確立する必要があります。

Clarity で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Clarity SSO**の構成 - アプリケーション側でシングル Sign-On 設定を構成します。
    1. **Clarity テスト ユーザーを作成する** - Clarity で B.Simon に対応するユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Clarity** アプリケーション統合ページを参照し、[**管理**] セクションを見つけて**、シングル サインオン**を選択します。
3. **[シングル サインオン方法の選択**] ダイアログで、[SAML **]**を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    基本的な SAML 構成の編集
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    ある。 [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://ca.ondemand.saml.20.post.<COMPANY_NAME>`

    b。 [**応答 URL** テキスト ボックスに、URL: `https://fedsso.ondemand.ca.com/affwebservices/public/saml2assertionconsumer` を入力します。

    手記

    この値は実際の値ではありません。 この値を実際の識別子で更新します。 この値を取得するには、Clarityクライアントサポートチーム[に](mailto:technical.support@broadcom.com)お問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [SAML **でシングル サインオンを設定する**] ページの [**SAML 署名証明書の**] セクションで、[**証明書 (Base64)** を探し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **の [Clarity** のセットアップ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Clarity SSO の構成

Clarity **側** でシングル サインオンを構成するには、ダウンロードした **証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [Clarity サポート チーム](mailto:technical.support@broadcom.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Clarity テスト ユーザーの作成

このセクションでは、Clarity で B.Simon というユーザーを作成します。 [Clarity サポート チームの](mailto:technical.support@broadcom.com) と連携して、Clarity プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Clarity に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Clarity] タイルを選択すると、SSO を設定した Clarity に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/capriza-tutorial"} -->
## Microsoft Entra ID で Capriza Platform for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/capriza-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Capriza Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Capriza Platform と Microsoft Entra ID を統合する方法について説明します。 Capriza Platform と Microsoft Entra ID を統合すると、次のことができます。

- Capriza Platform にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Capriza Platform に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Capriza Platform でのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Capriza Platform では、 **SP** Initiated SSO がサポートされます。
- Capriza Platform では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Capriza Platform を追加する

Microsoft Entra ID への Capriza Platform の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Capriza Platform を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Capriza Platform」**と入力します。
4. 結果のパネルから Capriza Platform  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Capriza Platform の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Capriza Platform に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Capriza Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Capriza Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Capriza Platform の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Capriza Platform のテスト ユーザーの作成** - Capriza Platform で B.Simon の対応ユーザーを作成し、Microsoft Entra における B.Simon の表現とリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Capriza Platform** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて**、シングル サインオン**を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.capriza.com/<tenantid>`

    手記

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 値を取得するには [、Capriza Platform クライアント サポート チーム](mailto:support@capriza.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Capriza Platform のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成用URLをコピーするスクリーンショットです。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Capriza Platform の SSO の構成

**Capriza Platform** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Capriza Platform サポート チーム](mailto:support@capriza.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Capriza Platform のテスト ユーザーの作成

このセクションの目的は、Capriza で Britta Simon というユーザーを作成することです。 Capriza では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 **ドメイン名がユーザー プロビジョニング用に Capriza で構成されていることを確認してください。 その後、Just-In-Time ユーザー プロビジョニングのみが機能します。**

このセクションにはアクション項目はありません。 Capriza にアクセスしようとすると、まだ存在しない場合は、新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Capriza Platform のサインオン URL にリダイレクトされます。
- Capriza Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Capriza Platform] タイルを選択すると、Capriza Platform のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/carbonite-endpoint-backup-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Carbonite Endpoint Backup を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/carbonite-endpoint-backup-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Carbonite Endpoint Backup の間にシングル サインオンを構成する方法について説明します。

この記事では、Carbonite Endpoint Backup と Microsoft Entra ID を統合する方法について説明します。 Carbonite Endpoint Backup を Microsoft Entra ID と統合すると、次のことができます。

- Carbonite Endpoint Backup にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Carbonite Endpoint Backup に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Carbonite Endpoint Backup でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Carbonite Endpoint Backup では、**SP 起動 SSO と IDP 起動 SSO** がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Carbonite Endpoint Backup の追加

Microsoft Entra ID への Carbonite Endpoint Backup の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Carbonite Endpoint Backup を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Carbonite Endpoint Backup**」と入力します。
4. 結果パネルから **Carbonite Endpoint Backup を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Carbonite Endpoint Backup 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Carbonite Endpoint Backup に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Carbonite Endpoint Backup の関連ユーザーとの間にリンク関係を確立する必要があります。

Carbonite Endpoint Backup に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Carbonite Endpoint Backup の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Carbonite Endpoint Backup テスト ユーザーを作成** - Microsoft EntraのB.Simonにリンクされた、Carbonite Endpoint BackupでのB.Simonの対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**カーボン エンドポイント バックアップ** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかの URL を入力します。

    ```http
    https://red-us.mysecuredatavault.com
    https://red-apac.mysecuredatavault.com
    https://red-fr.mysecuredatavault.com
    https://red-emea.mysecuredatavault.com
    https://kamino.mysecuredatavault.com
    ```

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    ```http
    https://red-us.mysecuredatavault.com/AssertionConsumerService.aspx
    https://red-apac.mysecuredatavault.com/AssertionConsumerService.aspx
    https://red-fr.mysecuredatavault.com/AssertionConsumerService.aspx
    https://red-emea.mysecuredatavault.com/AssertionConsumerService.aspx
    ```
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のいずれかの URL を入力します。

    ```http
    https://red-us.mysecuredatavault.com/
    https://red-apac.mysecuredatavault.com/
    https://red-fr.mysecuredatavault.com/
    https://red-emea.mysecuredatavault.com/
    ```
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Carbonite Endpoint Backup のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Carbonite Endpoint Backup SSO の構成

1. 別の Web ブラウザー ウィンドウで、Carbonite Endpoint Backup 企業サイトに管理者としてサインインします
2. 左側のウィンドウから **会社** を選択します。

    [Image: [Company] が選択されている Carbonite エンドポイントのスクリーンショット。]
3. [ **シングル サインオン] を選択します**。
4. **[有効にする]** を選択し、[**設定の編集]** を選択して構成します。

    [Image: [有効] と [編集] の設定が強調表示されている [シングル サインオン] タブを示すスクリーンショット。]
5. [ **シングル サインオン** 設定] ページで、次の手順を実行します。

    [Image: この手順で説明する情報を含む [シングル サインオン] タブを示すスクリーンショット。]

    1. [ **ID プロバイダー名** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。
    2. [ **ID プロバイダーの URL** ] ボックスに、先にコピーした **ログイン URL** の値を貼り付けます。
    3. [ **ファイルの選択] を選択** して、ダウンロードした **証明書 (Base64)** ファイルをアップロードします。
    4. **[保存] を選択します**。

#### Carbonite Endpoint Backup のテスト ユーザーの作成

1. 別の Web ブラウザー ウィンドウで、Carbonite Endpoint Backup 企業サイトに管理者としてサインインします。
2. 左側のウィンドウから **[ユーザー** ] を選択し、[ **ユーザーの追加]** を選択します。

    [Image: [ユーザー] と [ユーザーの追加] が選択されている [Carbonite エンドポイント] ページを示すスクリーンショット。]
3. [ **ユーザーの追加** ] ページで、次の手順を実行します。

    1. ユーザーの **電子メール**、 **名**、 **姓** を入力し、組織の要件に従ってユーザーに必要なアクセス許可を指定します。
    2. [ **ユーザーの追加] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Carbonite Endpoint Backup のサインオン URL にリダイレクトされます。
- Carbonite Endpoint Backup のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Carbonite Endpoint Backup に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Carbonite Endpoint Backup] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Carbonite Endpoint Backup に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/careership-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に CAREERSHIP を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/careership-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CAREERSHIP の間のシングル サインオンを構成する方法について説明します。

この記事では、CAREERSHIP を Microsoft Entra ID と統合する方法について説明します。 CAREERSHIP は、エンタープライズ向けの LMS (学習管理システム) として高く評価されています。 日本の企業の要求に応えながら進化を続けているLMSであり、高性能で多機能でありながら、同時に使い勝手も高い。 CAREERSHIP を Microsoft Entra ID と統合すると、次のことが可能になります。

- CAREERSHIP にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで CAREERSHIP に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

CAREERSHIP 用の Microsoft Entra シングル サインオンをテスト環境で構成してテストする。 CAREERSHIP は、**SP** initiated シングル サインオンをサポートします。

### [前提条件]

CAREERSHIP を Microsoft Entra ID と統合するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- CAREERSHIP でのシングル サインオン (SSO) に対応したサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから CAREERSHIP アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから CAREERSHIP を追加する

Microsoft Entra アプリケーション ギャラリーから CAREERSHIP を追加して、CAREERSHIP のシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**CAREERSHIP**&gt;**シングルサインオン**へ移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、`https://<tenant_name>.learningpark.jp/e/` の形式で値を入力します。

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant_name>.learningpark.jp/e/SamlListener`

    c. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<tenant_name>.learningpark.jp/e/Saml?corp_code=<corporate_code>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[CAREERSHIP のサポート チーム](mailto:asp-support@lightworks.co.jp)にお問い合わせください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[CAREERSHIP のセットアップ]** セクションで、ご自分の要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

### CAREERSHIP の SSO を構成する

**CAREERSHIP** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[CAREERSHIP サポート チーム](mailto:asp-support@lightworks.co.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### CAREERSHIP のテスト ユーザーを作成する

このセクションでは、CAREERSHIP で Britta Simon というユーザーを作成します。 [CAREERSHIP サポート チーム](mailto:asp-support@lightworks.co.jp)と協力して、CAREERSHIP プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる CAREERSHIP サインオン URL にリダイレクトされます。
- CAREERSHIP のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [CAREERSHIP] タイルを選択すると、このオプションは CAREERSHIP のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/catchpoint-tutorial"} -->
## Microsoft Entra ID で Catchpoint for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/catchpoint-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Catchpoint の間のシングル サインオンを構成する方法について説明します。

この記事では、Catchpoint と Microsoft Entra ID を統合する方法について説明します。 Catchpoint を Microsoft Entra ID と統合すると、次のことが可能になります。

- Microsoft Entra ID から Catchpoint へのユーザー アクセスを制御する。
- Microsoft Entra アカウントを持つユーザーに対して Catchpoint への自動サインインを有効にする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Catchpoint サブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Catchpoint では、SP Initiated SSO と IDP Initiated SSO がサポートされます。
- Catchpoint では、Just-In-Time (JIT) ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Catchpoint の追加

Microsoft Entra への Catchpoint の統合を構成するには、マネージド SaaS アプリの一覧に Catchpoint を追加します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Catchpoint**」と入力します。
4. 結果パネルから **Catchpoint** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Catchpoint に対する Microsoft Entra SSO を構成して検証する

SSO を機能させるには、Microsoft Entra ユーザーを Catchpoint のユーザーにリンクする必要があります。 この記事では、 **B.Simon** というテスト ユーザーを構成します。

次のセクションを完了します。

1. Microsoft Entra SSO を構成して、ユーザーに対してこの機能を有効にします。
    - Microsoft Entra テスト ユーザーを作成して、B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます。
2. Catchpoint SSO を構成して、アプリケーション側でシングル サインオン設定を構成します。
    - Catchpoint のテスト ユーザーを作成して、B.Simon Microsoft Entra テスト アカウントを Catchpoint の同様のユーザー アカウントにリンクできるようにします。
3. SSO をテストして、構成が機能することを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、Azure portal でこれらの手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Catchpoint**&gt;**シングルサインオン**に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On の設定** ] ページで、鉛筆アイコンを選択して **基本的な SAML 構成** 設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. Catchpoint の開始モードを構成します。

    - **IDP**開始モードの場合は、次のフィールドの値を入力します。
        - **識別子**の場合:`https://portal.catchpoint.com/SAML2`
        - **応答 URL** の場合:`https://portal.catchpoint.com/ui/Entry/SingleSignOn.aspx`
    - **SP** 開始モードの場合は、[**追加の URL の設定**] を選択し、次の値を入力します。
        - **サインオン URL** の場合:`https://portal.catchpoint.com/ui/Entry/SingleSignOn.aspx`
6. Catchpoint アプリケーションは、特定の形式の SAML アサーションを想定しています。 カスタム属性マッピングを SAML トークン属性の構成に追加します。 次の表に、既定の属性の一覧を示します。

    | 名前 | ソース属性 |
    | --- | --- |
    | Givenname | user.givenneame |
    | 名字 | ユーザーの名字 |
    | メールアドレス | ユーザーのメールアドレス |
    | 名前 | user.userprincipalname |
    | 一意のユーザー ID | user.userprincipalname |

    [Image: ユーザー属性と要求リストのスクリーンショット]
7. また、Catchpoint アプリケーションは、SAML 応答で別の属性が渡されることを想定しています。 次の表を参照してください。 この属性も値が事前に設定されますが、その値を確認し、要件に合わせて更新することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前空間 | user.assignedrole |

    注

    `namespace` 要求は、アカウント名でマップする必要があります。 このアカウント名は、SAML 応答で返される、Microsoft Entra ID のロールを使用して設定する必要があります。 Microsoft Entra ID のロールの詳細については、「 [エンタープライズ アプリケーションの SAML トークンで発行されたロール要求を構成する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)」を参照してください。
8. **[SAML を使用した単一 Sign-On の設定**] ページに移動します。 [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を見つけます。 [ **ダウンロード** ] を選択して証明書をコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **キャッチポイントの設定** ] セクションで、後の手順で必要な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Catchpoint の SSO の構成

1. 別の Web ブラウザー ウィンドウで、管理者として Catchpoint アプリケーションにサインインします。
2. **[設定]** アイコンを選択し、**SSO ID プロバイダーを選択します**。

    [Image: [SSO Identity Provider](SSO ID プロバイダー) が選択された Catchpoint 設定のスクリーンショット]
3. [ **シングル サインオン** ] ページで、次のフィールドを入力します。

    [Image: Catchpoint シングル サインオン ページのスクリーンショット]

    | フィールド | 価値 |
    | --- | --- |
    | **名前空間** | 有効な名前空間の値。 |
    | **ID プロバイダー発行者** | `Azure AD Identifier` の値です。 |
    | **シングル サインオン URL** | `Login URL` の値です。 |
    | **証書** | ダウンロードされた `Certificate (Base64)` ファイルの内容。 メモ帳を使用して表示およびコピーします。 |

    [メタデータのアップロード] オプションを選択して、 **フェデレーション メタデータ XML** を **アップロード** することもできます。
4. **[保存] を選択します**。

#### Catchpoint のテスト ユーザーの作成

Catchpoint では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションでは、ユーザー側で必要な操作はありません。 B.Simon が Catchpoint のユーザーとしてまだ存在していない場合は、認証後に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Catchpoint のサインオン URL にリダイレクトされます。
- Catchpoint のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Catchpoint に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Catchpoint] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Catchpoint に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

注

ログイン ページを使用して Catchpoint アプリケーションにサインインしたら、**Catchpoint 資格情報**を指定した後、**会社の資格情報 (SSO)** フィールドに有効な**名前空間**の値を入力し、[ログイン] を選択**します**。

[Image: Catchpoint の構成]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cato-networks-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Cato Networks を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cato-networks-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-03
- Summary: Microsoft Entra IDから Cato Networks にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために、Cato Networks と Microsoft Entra ID の両方で行う必要がある手順を説明します。 Microsoft Entra ID が構成されると、Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Cato Networks](https://www.catonetworks.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Cato Networks でユーザーを作成する
- accessが不要になった場合に Cato Networks のユーザーを削除する
- Microsoft Entra IDと Cato Networks の間でユーザー属性の同期を維持する
- Cato Networks でグループとグループメンバーシップを設定する
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Cato Networks](https://www.catonetworks.com/) アカウント。
- 管理者アクセス許可を持つ Cato Networks の管理者アカウント。
- 十分な数のユーザーのライセンス。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. Microsoft Entra IDとCato Networksの間でマップするデータを決定する。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Cato Networks を構成する

1. [Cato 管理アプリケーション](https://cc2.catonetworks.com)でアカウントにログインします。
2. ナビゲーション メニューから**Access &gt; Directory Services**を選択し、**SCIM**セクションタブを選択します。[Image: SCIM設定画面への移動のスクリーンショットです。]
3. [ **SCIM プロビジョニングを有効にする]** を選択して、SCIM アプリに接続するようにアカウントを設定します。 [ **保存]** を選択します。 [Image: SCIM プロビジョニングを有効にするのスクリーンショット。]
4. **ベース URL をコピーします**。 [ **トークンの生成]** を選択し、ベアラー トークンをコピーします。 ベース URL とトークンは、Cato Network アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Cato Networks を追加する

Microsoft Entra アプリケーション ギャラリーから Cato Networks を追加して、Cato Networks へのプロビジョニングの管理を開始してください。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Cato Networks への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて、Cato Networksでユーザーやグループを作成、更新、無効化するために、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Cato Networks の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Cato Networks**] を選択します。

    [Image: アプリケーションの一覧の [Cato Networks] リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Cato Networks テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Cato Networks に接続できることを確認します。 接続に失敗した場合は、Cato Networks アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cato Networks に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Cato Networks のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Cato Networks API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | emails[type eq "work"].value | 糸 |  |
    | 活動中 | ブール値 |  |
    | externalId | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cato Networks に同期されるグループ属性を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Cato Networks のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cbre-serviceinsight-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に CBRE ServiceInsight を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cbre-serviceinsight-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CBRE ServiceInsight の間でシングル サインオンを構成する方法について説明します。

この記事では、CBRE ServiceInsight と Microsoft Entra ID を統合する方法について説明します。 CBRE ServiceInsight を Microsoft Entra ID と統合すると、次のことができます。

- CBRE ServiceInsight にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して CBRE ServiceInsight に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- CBRE ServiceInsight でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- CBRE ServiceInsight では、 **SP** によって開始される SSO がサポートされます。
- CBRE ServiceInsight では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの CBRE ServiceInsight を追加する

CBRE ServiceInsight の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に CBRE ServiceInsight を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を参照してください。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「CBRE ServiceInsight**」と入力します。
4. 結果パネルから **CBRE ServiceInsight** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### CBRE ServiceInsight 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、CBRE ServiceInsight に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと CBRE ServiceInsight の関連ユーザーとの間にリンク関係を確立する必要があります。

CBRE ServiceInsight で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **CBRE ServiceInsight SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **CBRE ServiceInsight テストユーザーの作成** - B.Simon に対応するユーザーを CBRE ServiceInsight で作成し、Microsoft Entra の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**CBRE ServiceInsight**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://adfs4.mainstreamsasp.com/adfs/ls/`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、 [CBRE ServiceInsight クライアント サポート チーム](mailto:SISupport@cbre.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### CBRE ServiceInsight の SSO の構成

**CBRE ServiceInsight** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[CBRE ServiceInsight サポート チーム](mailto:SISupport@cbre.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### CBRE ServiceInsight テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを CBRE ServiceInsight に作成します。 CBRE ServiceInsight では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ CBRE ServiceInsight に存在しない場合は、CBRE ServiceInsight にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる CBRE ServiceInsight のサインオン URL にリダイレクトされます。
- CBRE ServiceInsight のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [CBRE ServiceInsight] タイルを選択すると、このオプションは CBRE ServiceInsight のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cch-tagetik-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に CCH Tagetik を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cch-tagetik-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と CCH Tagetik 間のシングル サインオンを構成する方法について説明します。

この記事では、CCH Tagetik と Microsoft Entra ID を統合する方法について説明します。 CCH Tagetik を Microsoft Entra ID と統合すると、次のことが可能になります。

- CCH Tagetik へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して CCH Tagetik に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- CCH Tagetik でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- CCH Tagetik では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- CCH Tagetik では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから CCH Tagetik を追加する

Microsoft Entra ID への CCH Tagetik の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に CCH Tagetik を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**CCH Tagetik**」と入力します。
4. 結果のパネルから **[CCH Tagetik]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### CCH Tagetik に対する Microsoft Entra SSO を構成して検証する

**B.Simon** というテスト ユーザーを使用して、CCH Tagetik に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと CCH Tagetik の関連ユーザーとの間にリンク関係を確立する必要があります。

CCH Tagetik に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **CCH Tagetik SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **CCH Tagetik テストユーザーを作成する** - CCH Tagetik で B.Simon に対応するユーザーを作成し、Microsoft Entra ユーザーの表現にリンクします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[CCH Tagetik]**&gt;**[シングル サインオン]** に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.saastagetik.com/prod/5/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.saastagetik.com/prod/5/`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.saastagetik.com/prod/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 この値を取得するには、[CCH Tagetik クライアント サポート チーム](mailto:tgk-dl-supportmembers@wolterskluwer.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[CCH Tagetik の設定]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### CCH Tagetik SSO の構成

**CCH Tagetik** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [CCH Tagetik サポート チーム](mailto:tgk-dl-supportmembers@wolterskluwer.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### CCH Tagetik テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを CCH Tagetik に作成します。 CCH Tagetik では、Just-In-Time ユーザー プロビジョニングがサポートされています。それは既定で有効になっています。 このセクションにはアクション項目はありません。 CCH Tagetik にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる CCH Tagetik のサインオン URL にリダイレクトされます。
- CCH Tagetik のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した CCH Tagetik に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [CCH Tagetik] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した CCH Tagetik に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/central-desktop-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Central Desktop を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/central-desktop-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-06-19
- Summary: Microsoft Entra ID と Central Desktop の間のシングル サインオンを構成する方法について説明します。

この記事では、Central Desktop と Microsoft Entra ID を統合する方法について説明します。 Central Desktop を Microsoft Entra ID と統合すると、次のことが可能になります。

- Central Desktop にアクセスできるつユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Central Desktop に自動的にサインインできるように設定する。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Central Desktop でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Central Desktop では、**SP** Initiated SSO がサポートされます。

### ギャラリーから Central Desktop を追加する

Microsoft Entra ID への Central Desktop の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Central Desktop を追加する必要があります。

1. [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上として [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Central Desktop**」と入力します。
4. 結果パネルで **[Central Desktop]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Central Desktop 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Central Desktop 用に Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Central Desktop の関連ユーザーの間にリンク関係を確立する必要があります。

Central Desktop 用に Microsoft Entra SSO を構成およびテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Central Desktop の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Central Desktop のテストユーザーを作成** - Central Desktop で B.Simon に対応するユーザーを作成し、Microsoft Entra 上のユーザーとリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次のステップに従います。

1. [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上として [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Central Desktop**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. **[識別子]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<companyname>.centraldesktop.com/saml2-metadata.php` |
    | `https://<companyname>.imeetcentral.com/saml2-metadata.php` |

    b。 **[応答 URL]** ボックスに、`https://<companyname>.centraldesktop.com/saml2-assertion.php` のパターンを使用して URL を入力します

    c. **[サインオン URL]** ボックスに、`https://<companyname>.centraldesktop.com` という形式で URL を入力します。

    Note

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Central Desktop サポート チーム](https://www.centraldesktop.com/contact)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Central Desktop のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Central Desktop の SSO の構成

1. **Central Desktop** テナントにサインインします。
2. **設定** に移動します。 **[Advanced](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/詳細)** を選び、**[Single Sign On](シングル サインオン)** を選びます。

    [Image: 設定 - 詳細]
3. **[Single Sign On Settings]** ページで、以下の手順を実行します。

    [Image: シングル サインオンの設定]

    a. **[Enable SAML v2 Single Sign On]** を選択します。

    b。 **[SSO URL]** ボックスに、コピーしてあった **[Microsoft Entra 識別子]**の値を貼り付けます。

    c. **[SSO ログイン URL]** ボックスに、コピーした**[ログイン URL]** の値を貼り付けます。

    d. **[SSO ログアウト URL]** ボックスに、コピーした**[ログアウト URL]** の値を貼り付けます。
4. **[Message Signature Verification Method]** セクションで、次の手順を実行します。

    [Image: メッセージ署名検証方法]

    a. **[Certificate]** を選択します。

    b。 **[SSO Certificate](SSO 証明書)** ボックスの一覧で、**[RSH SHA256]** を選びます。

    c. ダウンロードした証明書をメモ帳で開きます。 証明書の内容をコピーして、**[SSO Certificate](SSO 証明書)** フィールドに貼り付けます。

    d. **[Display a link to your SAMLv2 login page]** を選択します。

    e. **更新** を選択します。

#### Central Desktop のテスト ユーザーの作成

Microsoft Entra ユーザーがサインインできるように設定するには、ユーザーを Central Desktop アプリケーションにプロビジョニングする必要があります。 このセクションでは、Central Desktop で Microsoft Entra ユーザー アカウントを作成する方法について説明します。

Note

Microsoft Entra ユーザー アカウントをプロビジョニングするには、Central Desktop から提供されている他の Central Desktop ユーザー アカウント作成ツールまたは API を使います。

**Central Desktop にユーザー アカウントをプロビジョニングするには:**

1. Central Desktop テナントにサインインします。
2. **[People](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** を選択し、**[Add Internal Members](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/内部メンバーの追加)** を選択します。

    [Image: ユーザー]
3. **[Email Address of New Members](新しいメンバーの電子メール アドレス)** ボックスにプロビジョニングする Microsoft Entra アカウントを入力し、**[次へ]** を選びます。

    [Image: 新しいメンバーの電子メール アドレス。]
4. **[Add Internal member(s)](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/内部メンバーの追加)** を選びます。

    [Image: 内部メンバーの追加。]

    Note

    追加したユーザーが、アカウント アクティブ化のための確認リンクを含むメールを受け取ります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Central Desktop のサインオン URL にリダイレクトされます。
- Central Desktop のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Central Desktop] タイルを選択すると、このオプションは Central Desktop のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cequence-application-security-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cequence Application Security Platform を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cequence-application-security-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cequence Application Security Platform の間でシングル サインオンを構成する方法について説明します。

この記事では、Cequence Application Security Platform と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Cequence Application Security Platform を統合すると、次のことができます。

- Cequence Application Security Platform へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cequence Application Security Platform に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cequence Application Security Platform でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cequence Application Security Platform では、 **SP** Initiated SSO がサポートされます
- Cequence Application Security Platform では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Cequence Application Security Platform の追加

Cequence Application Security Platform の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cequence Application Security Platform を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Cequence Application Security Platform**」と入力します。
4. 結果パネルから **Cequence Application Security Platform** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cequence Application Security Platform の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Cequence Application Security Platform に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Cequence Application Security Platform の関連ユーザーとの間にリンク関係を確立する必要があります。

Cequence Application Security Platform に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cequence Application Security Platform の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Cequence Application Security Platform のテスト ユーザーの作成** - Cequence Application Security Platform で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cequence Application Security Platform**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMERNAME>.s.cequence.cloud`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMERNAME>.s.cequence.cloud:443/saml/metadata`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [Cequence Application Security Platform クライアント サポート チーム](mailto:support@cequence.ai) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Cequence Application Security Platform アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Cequence Application Security Platform アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | グループ | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cequence Application Security Platform の SSO の構成

**Cequence Application Security Platform** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Cequence Application Security Platform サポート チーム](mailto:support@cequence.ai)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cequence Application Security Platform のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Cequence Application Security Platform 内に作成します。 Cequence Application Security Platform では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Cequence Application Security Platform にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

1. [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cequence Application Security Platform のサインオン URL にリダイレクトされます。
2. Cequence Application Security Platform のサインオン URL に直接移動し、そこからログイン フローを開始します。
3. Microsoft アクセス パネルを使用することができます。 アクセス パネルで [Cequence Application Security Platform] タイルを選択すると、このオプションは Cequence Application Security Platform のサインオン URL にリダイレクトされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cerby-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Cerby を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cerby-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-03
- Summary: Microsoft Entra IDから Cerby にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Cerby と Microsoft Entra ID の両方で自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成が完了すると、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、[Cerby](https://app.cerby.com/) にユーザーとグループを自動的にプロビジョニングおよび解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Cerby でユーザーを作成する
- アクセスが不要になった場合には、Cerbyのユーザーを削除する
- Microsoft Entra IDと Cerby の間でユーザー属性の同期を維持する
- Cerby でグループとグループ メンバーシップをプロビジョニングする。
- Cerby に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cerby-tutorial)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- ワークスペース所有者ロールを持つ Cerby のユーザー アカウント。
- Cerby SAML2 ベースの統合を設定する必要があります。 [Microsoft Entra テナントを使用して Cerby アプリ ギャラリー SAML アプリを構成する方法](https://help.cerby.com/en/articles/5457563-how-to-configure-the-cerby-app-gallery-saml-app-with-your-azure-ad-tenant)に関する記事の手順に従って、統合を設定します。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. Microsoft Entra IDとCerbyの間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Cerby を構成する

Cerby では、Microsoft Entra IDのプロビジョニング サポートが既定で有効になっています。 次の手順のようにして、SCIM API 認証トークンを取得することだけが必要です。

1. 対応する [Cerby ワークスペース](https://app.cerby.com/)にログインします。
2. 左側のナビゲーション メニューの下部にある [**こんにちは &lt; ユーザー &gt;!** ボタンを選択します。 ドロップダウン メニューが表示されます。
3. ドロップダウン メニューから、アカウントに関連する **[ワークスペースの構成]** オプションを選択します。 [ **ワークスペースの構成]** ページが表示されます。
4. **[IDP 設定]** タブをアクティブにします。
5. [**IDP 設定]** タブの [**ディレクトリ同期**] セクションにある [**トークンの表示**] ボタンを選択します。ID の確認を待機しているポップアップ ウィンドウが表示され、プッシュ通知が Cerby モバイル アプリケーションに送信されます。 **大事な：** ID を確認するには、プッシュ通知を受信するために Cerby モバイル アプリケーションをインストールしてログインしている必要があります。
6. Cerby モバイル アプリケーションの**確認要求**画面で[**自分です]**ボタンを選択して、ID を確認します。 Cerby ワークスペースのポップアップ ウィンドウが閉じられ、[ **トークンの表示** ] ポップアップ ウィンドウが表示されます。
7. [ **コピー** ] ボタンを選択して、SCIM トークンをクリップボードにコピーします。

    ヒント

    [ **トークンの表示** ] ポップアップ ウィンドウを開いたままにして、いつでもトークンをコピーします。 Microsoft Entra IDを使用してプロビジョニングを構成するには、トークンが必要です。

### ステップ 3: Microsoft Entra アプリケーション ギャラリーから Cerby を追加する

Cerby へのプロビジョニングの管理を始めるには、Microsoft Entra アプリケーション ギャラリーから Cerby を追加します。 SSO のために Cerby を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、[ステップを使用して、ユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### ステップ 5: Cerby への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーとグループの割り当てに基づいて、Cerbyでユーザーとグループを作成、更新、無効化するように、Microsoft Entra プロビジョニング サービスを構成する手順を案内します。

#### Microsoft Entra IDで Cerby の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Cerby**] を選択します。

    [Image: アプリケーションの一覧の [Cerby] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Cerby テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Cerby に接続できることを確認します。 接続に失敗した場合は、Cerby アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute Mappings** セクションで、Microsoft Entra IDから Cerby に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Cerby のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、その属性に基づくユーザーのフィルター処理が Cerby API でサポートされていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Cerby で必要 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | emails[type eq "仕事"].value | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  | ✓ |
    | name.givenName | 糸 |  | ✓ |
    | name.familyName | 糸 |  | ✓ |
    | externalId | 糸 |  |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Cerby に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Cerby のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | Cerby で必要 |
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

### トラブルシューティングのヒント

SCIM API 認証トークンをもう一度生成する必要がある場合は、次の手順のようにします。

1. リクエストを含むメールを [Cerby サポート チーム](mailto:support@cerby.com)に送信します。 Cerby チームが SCIM API 認証トークンをもう一度生成します。
2. Cerby から応答メールを受け取り、トークンが正常に再生成されたことを確認します。
3. [Cerby](https://help.cerby.com/en/articles/5638472-how-to-configure-automatic-user-provisioning-for-azure-ad) から SCIM API 認証トークンを取得する方法に関する記事の手順を完了して、新しいトークンを取得します。

    注

    Cerby チームは、現在、SCIM API 認証トークンを生成し直すためのセルフサービス ソリューションを開発しています。 トークンをもう一度生成するには、Cerby チームのメンバーが ID を検証する必要があります。

### 変更ログ

- 2024 年 1 月 2 日 - **グループ プロビジョニング**のサポートを追加しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cerby-tutorial"} -->
## Microsoft Entra ID で Cerby for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cerby-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cerby の間のシングル サインオンを構成する方法について説明します。

この記事では、Cerby と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Cerby を統合すると、次のことができます。

- Cerby へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Cerby に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cerby でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Cerby では、 **SP** Initiated SSO がサポートされます。
- Cerby では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。
- Cerby では、 [自動ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cerby-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Cerby の追加

Microsoft Entra ID への Cerby の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Cerby を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**にアクセスします。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Cerby**」と入力します。
4. 結果パネルから **Cerby** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cerby の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Cerby に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Cerby での関連ユーザーとの間にリンク関係を確立する必要があります。

Cerby に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cerby SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cerby のテスト ユーザーの作成** - Cerby で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Cerby**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `urn:amazon:cognito:sp:<ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>-cerbyauth.auth.us-east-2.amazoncognito.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオン用URL |
    | --- |
    | `https://app.cerby.com` |
    | `https://<CustomerName>.cerby.com` |
    |  |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Cerby クライアント サポート チーム](mailto:help@cerby.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Cerby アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットはその例です。 **Name** の既定値は **user.userprincipalname** ですが、Cerby はこれがユーザーの指定された名前にマップされることを想定しています。 そのため、リストから **user.givenname** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 画像]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cerby の SSO の構成

Cerby 側でシングル サインオンを構成するには、 **アプリのフェデレーション メタデータ URL を**[Cerby サポート チーム](mailto:help@cerby.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cerby のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Cerby に作成します。 Cerby では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Cerby にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Cerby のサインオン URL にリダイレクトされます。
- Cerby のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cerby] タイルを選択すると、このオプションは Cerby のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/ceridiandayforcehcm-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Ceridian Dayforce HCM を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ceridiandayforcehcm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Ceridian Dayforce HCM の間のシングル サインオンを構成する方法について説明します。

この記事では、Ceridian Dayforce HCM と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Ceridian Dayforce HCM を統合すると、次のことができます。

- Ceridian Dayforce HCM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Ceridian Dayforce HCM に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Ceridian Dayforce HCM でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Ceridian Dayforce HCM では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの Ceridian Dayforce HCM の追加

Microsoft Entra ID への Ceridian Dayforce HCM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Ceridian Dayforce HCM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Ceridian Dayforce HCM**」と入力します。
4. 結果のパネルから **[Ceridian Dayforce HCM]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Ceridian Dayforce HCM 用に Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Ceridian Dayforce HCM に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Ceridian Dayforce HCM の関連ユーザーとの間にリンク関係を確立する必要があります。

Ceridian Dayforce HCM で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Ceridian Dayforce HCM の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Ceridian Dayforce HCM のテスト ユーザーの作成** - Ceridian Dayforce HCM で Britta Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Ceridian Dayforce HCM]**&gt;**[シングル サインオン]** の順に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Ceridian Dayforce HCM のドメインと URL] のシングル サインオン情報]

    a. [ **サインオン URL** ] ボックスに、Ceridian Dayforce HCM アプリケーションへのサインオンにユーザーが使用する URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 実稼動用 | `https://sso.dayforcehcm.com/<DayforcehcmNamespace>` |
    | テスト用 | `https://ssotest.dayforcehcm.com/<DayforcehcmNamespace>` |

    b。 **[識別子]** ボックスに、 のパターンを使用して URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 実稼動用 | `https://ncpingfederate.dayforcehcm.com/sp` |
    | テスト用 | `https://fs-test.dayforcehcm.com/sp` |

    c. **[応答 URL]** テキストボックスで、Microsoft Entra ID が応答を投稿するために使用する URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 実稼動用 | `https://ncpingfederate.dayforcehcm.com/sp/ACS.saml2` |
    | テスト用 | `https://fs-test.dayforcehcm.com/sp/ACS.saml2` |

    注

    これらの値は実際の値ではありません。 実際の Sign-On URL、識別子、応答 URL でこれらの値を更新します。 これらの値を取得するには、[Ceridian Dayforce HCM クライアント サポート チーム](https://www.dayforce.com/resources/help-center)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Ceridian Dayforce HCM アプリケーションは、特定の形式で構成された SAML アサーションを受け入れます。 このアプリケーションに対して次の要求を構成します。 これらの属性の値は、アプリケーション統合ページの **ユーザー属性** セクションから管理できます。 [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** ボタンを選択して [ **ユーザー属性] ダイアログを** 開きます。

    [Image: スクリーンショットには、[編集] アイコンが選択された [ユーザー属性] が表示されます。]
7. **[ユーザー属性]** ダイアログの **[ユーザーの要求]** セクションで、上の図のように SAML トークン属性を構成し、次の手順を実行します。

    | 名前 | ソース属性 |
    | --- | --- |
    | 名前 | user.extensionattribute2 |

    a. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。

    [Image: スクリーンショットには、[新しい要求の追加] オプションを含むユーザー要求が表示されます。]

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザー要求の管理] ダイアログ ボックスを示しています。]

    b。 [ **名前** ] ボックスに、その行に表示される属性名を入力します。

    c. **名前空間**は空白のままにします。

    d. **をソースとして属性**を選択します。

    e. **[ソース属性]** 一覧から、実装で使用するユーザー属性を選択します。 たとえば、一意のユーザー識別子として EmployeeID を使用し、その属性値を ExtensionAttribute2 に保存している場合、[user.extensionattribute2] を選択します。

    f. **[OK]** を選択します。

    g. **保存** を選択します。
8. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Ceridian Dayforce HCM のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

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

このセクションでは、B.Simon に Ceridian Dayforce HCM へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Ceridian Dayforce HCM** にアクセスします。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、"既定のアクセス" ロールが選択されていることがわかります。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

#### Ceridian Dayforce HCM の SSO の構成

**Ceridian Dayforce HCM** 側でシングル サインオンを構成するには、ダウンロードした**メタデータ XML** とアプリケーション構成からコピーした適切な URL を [Ceridian Dayforce HCM サポート チーム](https://www.dayforce.com/resources/help-center)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Ceridian Dayforce HCM のテスト ユーザーの作成

このセクションでは、Ceridian Dayforce HCM で Britta Simon というユーザーを作成します。 [Ceridian Dayforce HCM サポート チーム](https://www.dayforce.com/resources/help-center)と連携して、Ceridian Dayforce HCM プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Ceridian Dayforce HCM サインオン URL にリダイレクトされます。
- Ceridian Dayforce HCM のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Ceridian Dayforce HCM] タイルを選択すると、このオプションは Ceridian Dayforce HCM のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cernercentral-provisioning-tutorial"} -->
## Microsoft Entra ID での自動ユーザー プロビジョニング用に Cerner Central を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cernercentral-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-03
- Summary: Cerner Central の名簿にユーザーを自動的にプロビジョニングするようにMicrosoft Entra IDを構成する方法について説明します。

この記事の目的は、Microsoft Entra ID から Cerner Central のユーザー名簿にユーザーアカウントを自動的にプロビジョニングおよび解除するために、Cerner Central と Microsoft Entra ID で実行する必要がある手順を示すことです。

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cerner Central テナント

注

Microsoft Entra IDは、SCIM プロトコルを使用して Cerner Central と統合されます。

### Cerner Central へのユーザーの割り当て

Microsoft Entra IDでは、"割り当て" という概念を使用して、選択したアプリにaccessを受け取るユーザーを決定します。 自動ユーザー アカウント プロビジョニングのコンテキストでは、Microsoft Entra IDのアプリケーションに "割り当てられている" ユーザーとグループのみが同期されます。

プロビジョニング サービスを構成して有効にする前に、Cerner Central へのアクセスが必要なユーザーやグループが Microsoft Entra ID でどのように表されるかを決定する必要があります。 決定し終えたら、次の手順でこれらのユーザーを Cerner Central に割り当てることができます。

[エンタープライズ アプリにユーザーまたはグループを割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

#### ユーザーを Cerner Central に割り当てる際の重要なヒント

- プロビジョニング構成をテストするには、1 人の Microsoft Entra ユーザーを Cerner Central に割り当てることをお勧めします。 後でユーザーやグループを追加で割り当てられます。
- 1 人のユーザーに対して最初のテストが完了したら、Cerner Central では、Cerner のユーザー 名簿にプロビジョニングする任意の Cerner ソリューション (Cerner Central だけでなく) をaccessすることを目的としたユーザーのリスト全体を割り当てることをお勧めします。 その他の Cerner ソリューションでは、ユーザー リスト内のユーザーのこのリストを利用します。
- ユーザーを Cerner Central に割り当てるときに、割り当てのダイアログで**ユーザー** ロールを選択する必要があります。 "既定のAccess" ロールを持つユーザーは、プロビジョニングから除外されます。

### Cerner Central へのユーザー プロビジョニングの構成

このセクションでは、Cerner guides の SCIM ユーザー アカウント プロビジョニング API を使用してMicrosoft Entra IDを Cerner Central のユーザー 名簿に接続し、プロビジョニング サービスを構成して、Microsoft Entra IDのユーザーとグループの割り当てに基づいて、Cerner Central で割り当てられたユーザー アカウントを作成、更新、無効化する方法について説明します。

ヒント

Azure portal。 シングル サインオンは自動プロビジョニングとは別に構成できますが、これらの 2 つの機能は相補的な関係にあります。 詳細については、 [Cerner Central のシングル サインオンに関する記事を](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cernercentral-tutorial)参照してください。

#### Microsoft Entra IDで Cerner Central への自動ユーザー アカウント プロビジョニングを構成するには:

Cerner Central にユーザー アカウントをプロビジョニングするには、Cerner Central システム アカウントを Cerner に要求し、Cerner の SCIM エンドポイントへの接続Microsoft Entra ID使用できる OAuth ベアラー トークンを生成する必要があります。 運用環境にデプロイする前に、Cerner サンドボックス環境で統合を実行することもお勧めします。

1. 最初の手順は、Cerner と Microsoft Entra の統合を管理するユーザーが CernerCare アカウントを持っていることを確認することです。これは、手順を完了するために必要なドキュメントをaccessするために必要です。 必要に応じて、下記の URL を使って、該当する各環境に CernerCare アカウントを作成します。

    - サンドボックス: https://sandboxcernercare.com/accounts/create
    - 生産: https://cernercare.com/accounts/create
2. 次に、Microsoft Entra IDのシステム アカウントを作成する必要があります。 以下の手順を使って、サンドボックス環境と運用環境のシステム アカウントを要求します。

    - 手順: https://wiki.cerner.com/display/public/CernerCentral/Requesting+a+System+Account+in+System+Account+Management
    - サンドボックス: https://sandboxcernercentral.com/system-accounts/
    - 運用: https://cernercentral.com/system-accounts/
3. 次に、各システム アカウントのために OAuth ベアラー トークンを生成します。 これを行うには、以下の手順に従ってください。

    - 手順: https://wiki.ucern.com/display/public/reference/Accessing+Cerner%27s+Web+Services+Using+A+System+Account+Bearer+Token
    - サンドボックス: https://sandboxcernercentral.com/system-accounts/
    - 生産: https://cernercentral.com/system-accounts/
4. 最後に、Cerner のサンドボックスと運用環境の両方のユーザー リスト領域 ID を取得して構成を完了する必要があります。 取得方法については、https://wiki.ucern.com/display/public/reference/Publishing+Identity+Data+Using+SCIM をご覧ください。
5. Cerner にユーザー アカウントをプロビジョニングするようにMicrosoft Entra IDを構成できるようになりました。 [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
6. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
7. シングル サインオンのために Cerner Central を既に構成している場合は、検索フィールドで Cerner Central のインスタンスを検索します。 または、**[追加]** を選択して、アプリケーション ギャラリーで **[Cerner Central]** を検索します。 検索結果から Cerner Central を選択して、アプリケーションの一覧に追加します。
8. Cerner Central のインスタンスを選択してから、**[プロビジョニング]** タブを選択します。
9. 設定 **+ 新しい構成**。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]

    [Image: [自動] オプションが強調表示されている [プロビジョニング モード] ドロップダウン リストのスクリーンショット。]
10. **[管理者資格情報]** で、以下のフィールドを入力します。

    - **[テナント URL]** フィールドに、次の形式で URL を入力します。その際、"User-Roster-Realm-ID" を手順 4. で取得した領域 ID に置き換えます。

>
> サンドボックス: `https://user-roster-api.sandboxcernercentral.com/scim/v1/Realms/User-Roster-Realm-ID/`
>
> 生産: `https://user-roster-api.cernercentral.com/scim/v1/Realms/User-Roster-Realm-ID/`

    - [ **シークレット トークン** ] フィールドに、手順 3 で生成した OAuth ベアラー トークンを入力し、[ **テスト接続**] を選択します。
    - ポータルの右上に成功通知が表示されます。
11. [ **作成]** を選択して構成を作成します。
12. [**概要**] ページで **[プロパティ**] を選択します。
13. 鉛筆を選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **適用**を選択して、変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
14. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
15. Microsoft Entra IDから Cerner Central に同期するユーザー属性とグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Cerner Central のユーザー アカウントおよびグループとの照合に使用されます。 [保存] ボタンをクリックして変更をコミットします。
16. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
17. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
18. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cernercentral-tutorial"} -->
## Microsoft Entra ID で Cerner Central for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cernercentral-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cerner Central の間のシングル サインオンを構成する方法について説明します。

この記事では、Cerner Central と Microsoft Entra ID を統合する方法について説明します。 Cerner Central を Microsoft Entra ID と統合すると、次のことが可能になります。

- Cerner Central にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cerner Central に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cerner Central でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Cerner Central では、 **IDP** によって開始される SSO がサポートされます。
- Cerner Central では、 [**自動** ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cernercentral-provisioning-tutorial)。

### ギャラリーから Cerner Central を追加する

Microsoft Entra ID への Cerner Central の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cerner Central を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Cerner Central**」と入力します。
4. 結果パネルから **Cerner Central** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cerner Central 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cerner Central に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Cerner Central の関連ユーザーの間にリンク関係を確立する必要があります。

Cerner Central 用に Microsoft Entra SSO を構成およびテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cerner Central の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Cerner Central テストユーザーの作成** - Microsoft Entra 上のユーザーにリンクする B.Simon の対応ユーザーを Cerner Central で作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Cerner Central**&gt;**シングル サインオン**にブラウズしてください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<instancename>.cernercentral.com/session-api/protocol/saml2/metadata` |
    | `https://<instancename>.sandboxcernercentral.com/session-api/protocol/saml2/metadata` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<instancename>.cernercentral.com/session-api/protocol/saml2/sso` |
    | `https://<instancename>.sandboxcernercentral.com/session-api/protocol/saml2/sso` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [Cerner Central クライアント サポート チーム](mailto:SISupport@cbre.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cerner Central SSO を構成する

**Cerner Central** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Cerner Central サポート チーム](mailto:SISupport@cbre.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Cerner Central のテスト ユーザーの作成

**Cerner Central** アプリケーションでは、任意のフェデレーション ID プロバイダーからの認証が許可されます。 ユーザーがアプリケーションのホーム ページにサインインできる場合、そのユーザーはフェデレーションされるため、手動でプロビジョニングを行う必要はありません。 自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cernercentral-provisioning-tutorial) 。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Cerner Central に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cerner Central] タイルを選択すると、SSO を設定した Cerner Central に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/certainadminsso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Certain Admin SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/certainadminsso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Certain Admin SSO の間でシングル サインオンを構成する方法について説明します。

この記事では、Certain Admin SSO と Microsoft Entra ID を統合する方法について説明します。 Certain Admin SSO と Microsoft Entra ID を統合すると、次のことができます。

- Certain Admin SSO にアクセスするユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Certain Admin SSO に自動的にサインインするように設定する。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Certain Admin SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- 特定の管理者 SSO では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Certain Admin SSO の追加

Microsoft Entra ID への Certain Admin SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Certain Admin SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに「**Certain Admin SSO**」と入力します。
4. 結果パネルから **[Certain Admin SSO** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Certain Admin SSO に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Certain Admin SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Certain Admin SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Certain Admin SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Certain Admin SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Certain Admin SSO のテスト ユーザーの作成** - Certain Admin SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Certain Admin SSO]**&gt;**[シングルサインオン]** にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.certain.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOUR DOMAIN URL>/svcs/sso_admin_login/handleRequest/<ID>`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、 [Certain Admin SSO クライアント サポート チーム](mailto:integrations@certain.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Certain Admin SSO のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Certain Admin SSO の構成

**Certain Admin SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を [Certain Admin SSO サポート チーム](mailto:integrations@certain.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Certain Admin SSO のテスト ユーザーの作成

このセクションでは、Certain Admin SSO で Britta Simon というユーザーを作成します。 [Certain Admin SSO サポート チーム](mailto:integrations@certain.com)と協力して、Certain Admin SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Certain Admin SSO のサインオン URL にリダイレクトされます。
- Certain Admin SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Certain Admin SSO] タイルを選択すると、このオプションは Certain Admin SSO のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/certent-equity-management-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Certent Equity Management を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/certent-equity-management-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Certent Equity Management の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Certent Equity Management と Microsoft Entra ID を統合する方法について説明します。 Certent Equity Management と Microsoft Entra ID を統合すると、次のことができます。

- Certent Equity Management にアクセス権を持つユーザーを Microsoft Entra ID で管理できます。
- ユーザーが自分の Microsoft Entra アカウントで Certent Equity Management に自動的にサインインできるようにすることができます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Certent Equity Management のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Certent Equity Management では、**IDP** Initiated SSO がサポートされます

### ギャラリーからの Certent Equity Management の追加

Microsoft Entra ID への Certent Equity Management の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Certent Equity Management を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Certent Equity Management**」と入力します。
4. 結果パネルから **Certent Equity Management** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Certent Equity Management 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Certent Equity Management に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Certent Equity Management の関連ユーザーとの間にリンク関係を確立する必要があります。

Certent Equity Management に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Certent Equity Management の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Certent Equity Management のテストユーザーを作成し、B.Simon の対応を作ります。**このユーザーは、Microsoft Entra のユーザー表現にリンクされます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Certent Equity Management**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.certent.com/sys/sso/saml/acs.aspx`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.certent.com/sys/sso/saml/acs.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、顧客対応マネージャーによって割り当てられた Certent 統合アナリストにお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. Certent Equity Management アプリケーションでは、特定の形式の SAML アサーションが求められます。そのため、カスタム属性マッピングを SAML トークン属性構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、Certent Equity Management アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 会社 | ユーザー.companyname |
    | 利用者 | ユーザー.ユーザープリンシパルネーム |
    | 役割 | user.assignedroles |

    注

    Microsoft Entra ID で[ロール](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui)を構成する方法については、**こちらを**選択してください。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Certent Equity Management のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Certent Equity Management の SSO の構成

**Certent Equity Management** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を、顧客対応マネージャーによって割り当てられた Certent 統合アナリストに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Certent Equity Management テスト ユーザーの作成

このセクションでは、Certent Equity Management で Britta Simon というユーザーを作成します。 顧客対応マネージャーによって割り当てられた Certent 統合アナリストと連携し、Certent Equity Management プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Certent Equity Management に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [Certent Equity Management] タイルを選択すると、SSO を設定した Certent Equity Management に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/certify-tutorial"} -->
## Microsoft Entra ID で Certify for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/certify-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Certify の間のシングル サインオンを構成する方法について説明します。

この記事では、Certify と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Certify を統合すると、次のことができます。

- Certify へのアクセス権を持つユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントで Certify に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Certify でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Certify では、 **IDP** Initiated SSO がサポートされます。
- Certify では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Certify の追加

Microsoft Entra ID への Certify の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Certify を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに「**Certify**」と入力します。
4. 結果パネルから **[Certify** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Certify の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Certify に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Certify での関連ユーザーとの間にリンク関係を確立する必要があります。

Certify に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Certify SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Certifyテストユーザーを作成** - CertifyでB.Simonに対応するテストユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Certify]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://expense.certify.com`
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Certify のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Certify SSO の構成

**Certify** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とアプリケーション構成からコピーした適切な URL を [Certify サポート チーム](mailto:support@certify.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Certify テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Certify に作成します。 Certify では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Certify にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [Certify サポート チーム](mailto:support@certify.com)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Certify に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Certify] タイルを選択すると、SSO を設定した Certify に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/cezannehrsoftware-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Cezanne HR Software を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/cezannehrsoftware-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Cezanne HR Software の間でシングル サインオンを構成する方法について説明します。

この記事では、Cezanne HR Software と Microsoft Entra ID を統合する方法について説明します。 Cezanne HR Software を Microsoft Entra ID と統合すると、次のことが可能になります。

- Cezanne HR Software にアクセスできるユーザーを Microsoft Entra ID で管理する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Cezanne HR Software に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Cezanne HR Software のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Cezanne HR Software では、 **SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Cezanne HR Software の追加

Microsoft Entra ID への Cezanne HR Software の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Cezanne HR Software を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Cezanne HR Software**」と入力します。
4. 結果パネルから **Cezanne HR Software** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Cezanne HR Software に対して Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Cezanne HR Software に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるために、Microsoft Entra ユーザーと Cezanne HR Software の関連ユーザーとの間にリンク関係を確立する必要があります。

Cezanne HR Software で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Cezanne HR Software の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Cezanne HR Software のテスト ユーザーの作成** - Cezanne HR Software で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Cezanne HR Software]**&gt;**[シングル サインオン]** に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://w3.cezanneondemand.com/CezanneOnDemand/-/<tenantidentifier>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://w3.cezanneondemand.com/CezanneOnDemand/`

    c. [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://w3.cezanneondemand.com:443/cezanneondemand/-/<tenantidentifier>/Saml/samlp`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と応答 URL でこれらの値を更新してください。 これらの値を取得するには、 [Cezanne HR Software クライアント サポート チーム](https://cezannehr.com/services/support/) に問い合わせてください。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Cezanne HR Software のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Cezanne HR Software の SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として Cezanne HR Software テナントにサインオンします。
2. サイド メニューで、[管理] を選択 **します**。 次に、[ **セキュリティ設定]** に移動し、[ **シングル サインオン**] を選択します。

    [Image: [セキュリティ設定] と [シングル Sign-On 構成] が選択されている Cezanne H R Software テナントを示すスクリーンショット。]
3. **[ユーザーが次のシングル Sign-On (SSO) サービスを使用してログインできるようにする**] パネルで、[**SAML 2.0**] ボックスをオンにし**、[詳細構成**] オプションを選択します。

    [Image: SAML 2.0 と [詳細構成] が選択されている [ユーザーの許可] ペインを示すスクリーンショット。]
4. [ **新規追加]** ボタンを選択します。

    [Image: [新規追加] ボタンを示すスクリーンショット。]
5. **SAML 2.0 IDENTITY PROVIDERS** セクションで次のフィールドを入力し、[**OK] を選択します**。

    [Image: この手順で説明する値を入力できるペインを示すスクリーンショット。]

    a. **表示名** - 表示名として ID プロバイダーの名前を入力します。

    b。 **[エンティティ識別子** ] - [エンティティ識別子] ボックスに、前にコピーした Microsoft Entra 識別子の値を貼り付けます。

    c. **SAML バインド** - SAML バインドを 'POST' に変更します。

    d. **セキュリティ トークン サービス エンドポイント** - [セキュリティ トークン サービス エンドポイント] ボックスに、前にコピーしたログイン URL の値を貼り付けます。

    e. **[ユーザー ID 属性名** ] - [ユーザー ID 属性名] ボックスに「http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress」と入力します。

    f. **公開キー証明書** - [アップロード] アイコンを選択して、Azure portal からダウンロードした証明書をアップロードします。
6. [OK] を選択します。
7. [保存] ボタンを選択します。

    [Image: [シングル サインオン構成] の [保存] ボタンを示すスクリーンショット。]

#### Cezanne HR Software のテスト ユーザーの作成

Microsoft Entra ユーザーが Cezanne HR Software にログインできるようにするには、ユーザーを Cezanne HR Software にプロビジョニングする必要があります。 Cezanne HR Software の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. Cezanne HR Software 企業サイトに管理者としてログインします。
2. サイド メニューで、[管理] を選択 **します**。 次に、[**ユーザー**] に移動し、[**新しいユーザーの追加]** を選択します。

    [Image: スクリーンショットは、[ユーザーの管理] と [新しいユーザーの追加] が選択されている Cezanne H R Software テナントを示しています。]
3. [ **PERSON DETAILS]** セクションで、次の手順を実行します。

    [Image: この手順で説明する値を入力できる PERSON DETAILS セクションを示すスクリーンショット。]

    a. **内部ユーザーを** OFF に設定します。

    b。 名を入力します

    c. 姓を入力します

    d. 電子メール アドレスを入力します。
4. [ **アカウント情報]** セクションで、次の手順を実行します。

    [Image: この手順で説明する値を入力できる ACCOUNT INFORMATION を示すスクリーンショット。]

    a. [Username]\( **ユーザー名** \) テキストボックスに、ユーザーの電子メール ( Brittasimon@contoso.comなど) を入力します。

    b。 [ **パスワード** ] ボックスに、ユーザーのパスワードを入力します。

    c. **セキュリティ ロール**として **HR Professional** を選択します。

    d. [ **OK] を選択します**。 [Image: [OK] ボタンを示すスクリーンショット。]
5. **[シングル サインオン**] タブに移動し、[**SAML 2.0 識別子**] 領域で [**新規追加**] を選択します。

    [Image: スクリーンショットは、[Add New](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新規追加) を選択できる [Single Sign-On](シングル サインオン) タブを示します。]
6. アイデンティティプロバイダーを選択し、[ユーザー識別子] のテキストボックスにユーザーのメールアドレスを入力します。

    [Image: [SAML 2.0 Identifiers](SAML 2.0 識別子) を示すスクリーンショット。ID プロバイダーとユーザー識別子を選択できます。]
7. [ **保存] ボタンを** 選択します。

    [Image: [ユーザー設定] の [保存] ボタンを示すスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる Cezanne HR Software のサインオン URL にリダイレクトされます。
- Cezanne HR Software のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Cezanne HR Software] タイルを選択すると、このオプションは Cezanne HR Software のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/change-process-management-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Change Process Management を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/change-process-management-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Change Process Management 間にシングル サインオンを構成する方法について学習します。

この記事では、Change Process Management と Microsoft Entra ID を統合する方法について説明します。 Change Process Management を Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra ID を使用して、Change Process Management にアクセスできるユーザーを制御する。
- ユーザーが自分の Microsoft Entra アカウントで Change Process Management に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Change Process Management サブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

Change Process Management では、IDP Initiated SSO がサポートされます。

### ギャラリーからの Change Process Management の追加

Microsoft Entra ID への Change Process Management の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Change Process Management を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に進みます。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「プロセス管理の変更**」と入力します。
4. 結果パネルで **[プロセス管理の変更** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Change Process Management 用に Microsoft Entra SSO を構成してテストする

B.Simon というテスト ユーザーを使用して、Change Process Management で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Change Process Management の対応するユーザーとの間にリンク関係を確立する必要があります。

Change Process Management で Microsoft Entra SSO を構成してテストするには、次の大まかな手順を実行します。

1. ユーザーがこの機能を使用できるように **Microsoft Entra SSO を構成**します。
    1. **Microsoft Entra テスト ユーザーを作成** して、Microsoft Entra のシングル サインオンをテストします。
    2. **テスト ユーザーにアクセス権を付与** して、ユーザーが Microsoft Entra シングル サインオンを使用できるようにします。
2. アプリケーション側で **Change Process Management SSO を構成**します。
    1. **Change Process Management テスト ユーザーを** 、Microsoft Entra のユーザー表現に対応するユーザーとして作成します。
3. **SSO をテスト** して、構成が機能することを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. Entra IDEnterprise appsChange Process Management アプリケーション統合ページに移動し、[管理] セクションでシングル サインオンを選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆ボタンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<hostname>:8443/`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<hostname>:8443/changepilot/saml/sso`

    注

    上記の **識別子** と **応答 URL** の値は、実際に使用する必要がある値ではありません。 実際の値を取得するには、 [Change Process Management サポート チーム](mailto:support@realtech-us.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[証明書の **ダウンロード** ] リンク **(Base64)** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Change Process Management のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 適切なURLに構成をコピーする手順を示したスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon という名前のテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部にある [ **新しいユーザー**&gt;**新しいユーザー**の作成] を選択します。
4. **ユーザー**のプロパティで、次の手順に従います。
    1. [ **表示名** ] フィールドに「 `B.Simon`」と入力します。
    2. [ **ユーザー プリンシパル名** ] フィールドに、 username@companydomain.extensionを入力します。 たとえば、`B.Simon@contoso.com` のようにします。
    3. [ **パスワードの表示** ] チェック ボックスをオンにし、[ **パスワード** ] ボックスに表示される値を書き留めます。
    4. [ **確認と作成**] を選択します。
5. **作成**を選択します。

#### テスト ユーザーへのアクセス権の付与

このセクションでは、B.Simon に Change Process Management へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. **Entra ID**&gt;**エンタープライズアプリケーション**を参照します。
2. アプリケーションの一覧で、[ **プロセス管理の変更**] を選択します。
3. アプリの概要ページの [ **管理** ] セクションで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザーの追加]** を選択し、[**割り当ての追加**] ダイアログ ボックスで [**ユーザーとグループ**] を選択します。
5. [**ユーザーとグループ**] ダイアログ ボックスで、[**ユーザー**] の一覧で **[B.Simon**] を選択し、画面の下部にある **[選択**] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログ ボックスで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. [ **割り当ての追加** ] ダイアログ ボックスで、[ **割り当て**] を選択します。

### Change Process Management の SSO の構成

Change Process Management 側でシングル サインオンを構成するには、ダウンロードした Base64 証明書と、 [変更プロセス管理サポート チーム](mailto:support@realtech-us.com)にコピーした適切な URL を送信する必要があります。 彼らは、SAML SSO 接続を両側で正しく構成します。

#### Change Process Management のテスト ユーザーの作成

[Change Process Management サポート チーム](mailto:support@realtech-us.com)と協力して、Change Process Management に B.Simon という名前のユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Change Process Management に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [プロセス管理の変更] タイルを選択すると、SSO を設定した Change Process Management に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/chaos-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Chaos を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chaos-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-31
- Summary: Microsoft Entra IDから Chaos にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Chaos と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成が完了した時に、Microsoft Entra ID は Microsoft Entra プロビジョニング サービスを使用して、ユーザーとグループを [Tribeloo](https://www.tribeloo.com/) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされる機能

- Chaos でユーザーを作成する
- アクセスが不要になった場合に Chaos 内のユーザーを削除する
- Microsoft Entra IDと Chaos の間でユーザー属性の同期を維持する
- Chaos への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Azure テナントに割り当てられ、それぞれのユーザーの電子メールに使用される検証済みのドメイン名。
- [Chaos 企業のサインイン](https://docs.chaosgroup.com/display/KB/Corporate+Sign+In)に慣れている。
- 企業サインインに関連するChaos [利用規約](https://www.chaosgroup.com/en/terms)、[プライバシーに関する声明](https://www.chaosgroup.com/corporate/privacy-notice)、およびEULA契約を読み、同意します`https://www.chaosgroup.com/eula`。

### 手順 1:プロビジョニングのデプロイを計画する

1. [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとChaosの間でマッピングするデータを決定する。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Chaos を構成する

[サポート チケット](https://support.chaos.com)を開くか、Chaos Key Account Manager に連絡して、会社のサインインを設定するよう要求します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Chaos を追加する

Microsoft Entra アプリケーション ギャラリーから Chaos を追加して、Chaos へのプロビジョニングの管理を開始します。 SSO のために Chaos を既に設定している場合は、その同じアプリケーションを使用できます。 ただし、統合を初めてテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Chaos への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーやグループの割り当てに基づいて Chaos のユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Chaos の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で [ **Chaos**] を選択します。

    [Image: アプリケーションの一覧の [Chaos] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: 自動構成オプションを含む [プロビジョニング] タブのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Chaos テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Chaos に接続できることを確認します。 接続に失敗した場合は、Chaos アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Chaos に同期されるユーザー属性を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Chaos のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Chaos API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 属性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6:デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態の詳細については、 [アプリケーションプロビジョニングの検疫状態](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status) に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/chargebee-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Chargebee を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chargebee-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Chargebee の間のシングル サインオンを構成する方法について説明します。

この記事では、Chargebee と Microsoft Entra ID を統合する方法について説明します。 Chargebee を Microsoft Entra ID と統合すると、次のことが可能になります。

- Chargebee にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで Chargebee に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Chargebee でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Chargebee では、**SP と IDP** Initiated SSOがサポートされます。

### ギャラリーから Chargebee を追加する

Chargebee と Microsoft Entra ID の統合を構成するには、管理対象 SaaS アプリの一覧に Chargebee をギャラリーから追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Chargebee**」と入力します。
4. 結果のパネルから **[Chargebee]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Chargebee 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Chargebee 用の Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと、Chargebee での関連ユーザーとの間にリンク関係を確立する必要があります。

Chargebee 用の Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Chargebee の SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Chargebee テストユーザーの作成 - Chargebeeで B.Simonに対応するユーザーを作成し、Microsoft Entraのユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Chargebee** アプリケーション統合ページに移動し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML によるシングル サインオンのセットアップ]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domainname>.chargebee.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.chargebee.com/saml/<domainname>/acs`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domainname>.chargebee.com`

    注

    `<domainname>` は、アカウントの要求後にユーザーが作成するドメインの名前です。 その他の情報については、[Chargebee クライアント サポート チーム](mailto:support@chargebee.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Set up Chargebee](Chargebee の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Chargebee の SSO の構成

1. 新しい Web ブラウザー ウィンドウを開き、Chargebee 企業サイトに管理者としてサインインします。
2. メニューの左側にある **[設定]**&gt; [**セキュリティ**&gt;**管理**] を選択します。

    [Image: スクリーンショットは、[設定]、[セキュリティ]、および [管理] が選択されている Chargebee 企業サイトを示しています。]
3. **[シングル サインオン]** ポップアップで、次の手順を実行します。

    [Image: スクリーンショットは [シングル サインオン] ダイアログ ボックスを示しています。[SAML] が選択され、確認が必要なオプションが表示されています。]

    a. **[SAML**] を選択します。

    b。 **[ログイン URL]** テキストボックスに、先ほどコピーした**ログイン URL** の値を貼り付けます。

    c. Base64 でエンコードされた証明書をメモ帳で開き、その内容をコピーして **[SAML 証明書]** テキスト ボックスに貼り付けます。

    d. **確認** を選択します。

#### Chargebee のテスト ユーザーの作成

Microsoft Entra ユーザーが Chargebee にサインインできるようにするには、ユーザーを Chargebee にプロビジョニングする必要があります。 Chargebee では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. 別の Web ブラウザー ウィンドウで、Chargebee にセキュリティ管理者としてサインインします。
2. メニューの左側にある [ **顧客** ] を選択し、[ **新しい顧客の作成**] に移動します。

    [Image: スクリーンショットは Chargebee サイトを示しています。[Customers](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/顧客) および [Create a New Customer](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しい顧客の作成) が選択されています。]
3. [ **新しい顧客** ] ページで、以下に示す各フィールドに入力し、[ **顧客の作成** ] を選択してユーザーを作成します。

    [Image: スクリーンショットは、顧客情報を入力できる [New Customer](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/新しい顧客) ページを示しています。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Chargebee のサインオン URL にリダイレクトされます。
- Chargebee のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Chargebee に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで Chargebee タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Chargebee に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/chartdesk-sso-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に ChartDesk SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chartdesk-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と ChartDesk SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、ChartDesk SSO と Microsoft Entra ID を統合する方法について説明します。 ChartDesk SSO を使用すると、ユーザーは Microsoft Entra の資格情報でて ChartDesk にサインインできます。 Microsoft Entra ID と ChartDesk SSO を統合すると、次のことが可能になります。

- ChartDesk SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで ChartDesk に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

ChartDesk 用の Microsoft Entra シングル サインオンをテスト環境で構成してテストします。 ChartDesk SSO は、**IDP** によって開始されるシングル サインオンをサポートしています。

### [前提条件]

Microsoft Entra ID と ChartDesk SSO を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な ChartDesk SSO のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから ChartDesk SSO アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから ChartDesk SSO を追加する

Microsoft Entra アプリケーション ギャラリーから ChartDesk SSO を追加して、ChartDesk SSO でシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**ChartDesk SSO**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<externaltenantid>.staging-api.chartdesk.net/saml/metadata` |
    | `https://<externaltenantid>.prod-api.chartdesk.net/saml/metadata` |
    | `https://<externaltenantid>.prod-api.chartdesk.de/saml/metadata` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<externaltenantid>.staging-api.chartdesk.net/saml/consume` |
    | `https://<externaltenantid>.prod-api.chartdesk.net/saml/consume` |
    | `https://<externaltenantid>.prod-api.chartdesk.de/saml/consume` |

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[ChartDesk SSO クライアント サポート チーム](mailto:support@chartdesk.pro)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### ChartDesk SSO の構成

**ChartDesk SSO** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [ChartDesk SSO サポート チーム](mailto:support@chartdesk.pro)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### ChartDesk SSO テスト ユーザーの作成

このセクションでは、ChartDesk SSO で Britta Simon というユーザーを作成します。 [ChartDesk SSO サポート チーム](mailto:support@chartdesk.pro)と連携して、ChartDesk SSO プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した ChartDesk SSO に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [ChartDesk SSO] タイルを選択すると、SSO を設定した ChartDesk SSO に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/chatwork-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Chatwork を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chatwork-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-09-24
- Summary: Microsoft Entra IDから Chatwork にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Chatwork と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 Microsoft Entra IDを構成すると、Microsoft Entra プロビジョニング サービスを使用してユーザーとグループを[Chatwork](https://corp.chatwork.com/)に自動的にプロビジョニングし、解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

Warning

Chatwork は、2026 年 10 月 1 日より SCIM ベースのプロビジョニングのサポートを中止します。 その結果、Microsoft Entra Enterprise アプリ ギャラリーでの Chatwork プロビジョニング統合は廃止されます。 統合を使用している既存の顧客は、この日以降、Chatwork にユーザーをプロビジョニングできなくなります。 Chatwork の SSO 機能は引き続き使用できます。

### サポートされている機能

- Chatwork にユーザーを作成する。
- アクセスが不要になった場合は、Chatwork のユーザーを削除します。
- Microsoft Entra IDと Chatwork の間でユーザー属性の同期を維持します。
- Chatwork への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chatwork-tutorial) (必須)。
- Code Auth Grant フローによる認証に対応しています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [A Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Chatwork](https://corp.chatwork.com/) テナント。
- 管理者権限を持つ Chatwork のユーザー アカウント。
- Chatwork Enterprise プランまたは KDDI Chatwork を契約している組織。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとChatworkの間でどのデータをマップするかを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Chatwork を構成する

#### 1. Chatwork 管理者ページから **[User Synchronization](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの同期)** を開く

管理者権限を持つユーザーとして Chatwork 管理者ポータルにアクセスします。 管理者特権がある場合は、[ **ユーザー同期** ] ページにアクセスできます。

**[User Synchronization](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの同期)** ページには、ユーザー プロビジョニング機能を使用するための注意事項と制限事項が記載されています。 すべての項目にチェックを入れてください。

[Image: [ユーザー同期] ページのスクリーンショット。]

#### 2. SAML ログイン設定を構成する。

Microsoft Entra IDとユーザー プロビジョニングを使用している場合は、Microsoft Entra IDを使用して Chatwork にログインします。

[Image: [SAML ログイン設定の構成] のスクリーンショット。]

#### 3. 各項目に同意したうえで、チェックボックスをオンにする。

ユーザー プロビジョニング機能の使用に関する注意事項と制限事項に同意したうえで、チェックボックスをオンにします。

すべての項目がオンになったら、[ **ユーザー同期を有効にする** ] ボタンを選択します。

[Image: さまざまな項目を受け入れてユーザー同期ボタンを有効にするスクリーンショット。]

ユーザー プロビジョニング機能が有効になっていると、ページの上部に有効になっていることを示すメッセージが表示されます。

[Image: 有効なメッセージのスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Chatwork を追加する

Microsoft Entra アプリケーション ギャラリーから Chatwork を追加して、Chatwork へのプロビジョニングの管理を開始します。 以前に Chatwork で SSO を設定したことがある場合は、その同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Chatwork への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーまたはグループの割り当てに基づいて Chatwork でユーザーやグループを作成、更新、無効化するようにMicrosoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra IDで Chatwork の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**を参照してください

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Chatwork]** を選択します。

    [Image: アプリケーションの一覧の Chatwork リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: 自動構成オプションを含む [プロビジョニング] タブのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Chatwork テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Chatwork に接続できることを確認します。 接続に失敗した場合は、Chatwork アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Chatwork に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性を使用して、Chatwork での更新処理でユーザー アカウントが照合されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Chatwork API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:department | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:organization | 糸 |  |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/chatwork-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Chatwork を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chatwork-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Chatwork 間にシングル サインオンを構成する方法について学習します。

この記事では、Chatwork と Microsoft Entra ID を統合する方法について説明します。 Chatwork を Microsoft Entra ID と統合すると、次のことができます。

- Chatwork にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Chatwork に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Chatwork でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Chatwork では、 **SP** Initiated SSO がサポートされます。
- Chatwork では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chatwork-provisioning-tutorial)。

### ギャラリーからの Chatwork の追加

Microsoft Entra ID への Chatwork の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Chatwork を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**を表示します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Chatwork」と**入力します。
4. 結果パネルから **[Chatwork** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Chatwork 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Chatwork に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Chatwork の関連ユーザーの間にリンク関係を確立する必要があります。

Chatwork に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Chatwork SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Chatwork テストユーザーの作成** - ChatworkでB.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Chatwork**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.chatwork.com/s/<TENANT_NAME>`

    注

    これは実際の値ではありません。 **Chatwork SSO 構成**後に設定したプライベート ログイン URL で値を更新します。
6. Chatwork アプリケーションでは、 **一意のユーザー識別子** 属性の値が Chatwork に登録されている電子メール アドレスと一致する必要があります。 この属性は、既定で **user.principalname** にマップされます。 プリンシパル名がメール アドレスと異なる場合は、 **一意のユーザー識別子** を **user.mail** にマップします。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Chatwork のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Chatwork SSO の構成

**Chatwork** 側でシングル サインオンを構成するには、[Chatwork 管理者ガイド](https://download.chatwork.com/Chatwork_AdminGuide.pdf)を参照し、Chatwork 設定を構成してください。

#### Chatwork テスト ユーザーの作成

このセクションでは、Chatwork で B.Simon というユーザーを作成します。 [Chatwork 管理者ガイド](https://download.chatwork.com/Chatwork_AdminGuide.pdf)にアクセスし、Chatwork プラットフォームにユーザーを追加します。

Chatwork では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/chatwork-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Chatwork のサインオン URL にリダイレクトされます。
- Chatwork のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Chatwork] タイルを選択すると、このオプションは Chatwork のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/check-point-harmony-connect-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Check Point Harmony Connect を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/check-point-harmony-connect-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Check Point Harmony Connect の間にシングル サインオンを構成する方法について説明します。

この記事では、Check Point Harmony Connect と Microsoft Entra ID を統合する方法について説明します。 Check Point Harmony Connect と Microsoft Entra ID を統合すると、次が可能になります。

- Check Point Harmony Connect へのアクセス許可のある Microsoft Entra ID を制御する。
- ユーザーが Microsoft Entra アカウントを使用して Check Point Harmony Connect に自動的にサインインできるようになる。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Check Point Harmony Connect でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Check Point Harmony Connect では、 **SP** によって開始される SSO がサポートされます。

Note

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Check Point Harmony Connect の追加

Microsoft Entra ID への Check Point Harmony Connect の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Check Point Harmony Connect を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「Check Point Harmony Connect**」と入力します。
4. 結果パネルから **Check Point Harmony Connect** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Check Point Harmony Connect 向けの Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Check Point Harmony Connect に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Check Point Harmony Connect の関連ユーザーとの間にリンク関係を確立する必要があります。

Check Point Harmony Connect を使用して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Check Point Harmony Connect の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Check Point Harmony Connect のテスト ユーザーの作成** - Check Point Harmony Connect で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Check Point Harmony Connect**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://cloudinfra-gw.portal.checkpoint.com/api/saml/sso`
6. Check Point Harmony Connect アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングをご自分の SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 画像]
7. 上記に加えて、Check Point Harmony Connect では、いくつかの追加の属性が SAML 応答で返されることも想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    | 名前 | ソース属性 |
    | --- | --- |
    | groups | ユーザー.グループ |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Check Point Harmony Connect のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Check Point Harmony Connect SSO の構成

1. Check Point Harmony Connect の Web サイトに管理者としてログインします。
2. **[設定]** を選択し、**ID プロバイダー**に移動し、[**今すぐ接続**] を選択します。

    [Image: ID プロバイダーのスクリーンショット。]
3. ID プロバイダーとして **Microsoft Entra ID を** 選択し、[ **次へ**] を選択します。
4. [ **ドメインの確認** ] ページで、組織のドメインを入力し、生成された DNS レコードを TXT レコードとして DNS サーバーに入力し、[ **次へ**] を選択します。

    [Image: ドメイン値のスクリーンショット。]
5. [Allow Connectivity](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/接続の許可) ページで、次の手順を実行します。

    [Image: [接続の許可] ページのスクリーンショット。]

    a. **ENTITY ID の値を**コピーし、[**基本的な SAML 構成**] セクションの **[識別子**] テキスト ボックスにこの値を貼り付けます。

    b。 **[応答 URL]** の値をコピーし、[**基本的な SAML 構成**] セクションの **[応答 URL**] テキスト ボックスにこの値を貼り付けます。

    c. **[次へ**] を選択します。
6. [ **メタデータの構成** ] ページで、Azure portal からダウンロードした **フェデレーション メタデータ XML** をアップロードします。
7. [ **CONFIRM IDENTITY PROVIDER]\(ID プロバイダーの確認** \) ページで、[ **追加** ] を選択して構成を完了します。

#### Check Point Harmony Connect のテスト ユーザーの作成

1. Check Point Harmony Connect の Web サイトに管理者としてログインします。
2. **Policy**&gt;**Access Control** に移動し、**新しいルール**を作成し**、(+)** を選択して**新しいユーザー**を追加します。

    [Image: 新しいユーザーの作成のスクリーンショット。]
3. [ **ユーザーの追加** ] ウィンドウで、それぞれのテキスト ボックスに [名前] と [ユーザー名] を入力し、[ **追加**] を選択します。

    [Image: ユーザーの作成のスクリーンショット。]

### SSO のテスト

Check Point Harmony Connect をテストするには、「 **Microsoft Entra テスト ユーザーの作成** 」セクションで作成したテスト アカウントを使用して認証サービスに移動し、認証を行います。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/check-point-identity-awareness-tutorial"} -->
## Microsoft Entra ID を用いたシングルサインオンのための Check Point アイデンティティ意識の設定 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/check-point-identity-awareness-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-03-25
- Summary: Microsoft Entra ID と Check Point Identity Awareness の間でシングル サインオンを構成する方法について説明します。

この記事では、Check Point Identity Awareness と Microsoft Entra ID を統合する方法について説明します。 Check Point Identity Awareness を Microsoft Entra ID と統合すると、次が可能になります。

- Check Point Identity Awareness へのアクセス許可のある Microsoft Entra ID を制御する。
- ユーザーが Microsoft Entra アカウントを使用して Check Point Identity Awareness に自動的にサインインできるようになる。
- アカウントを一元的に管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/free/?WT.mc_id=A261C142F)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Check Point Identity Awareness でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Check Point Identity Awareness では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Check Point Identity Awareness の追加

Microsoft Entra ID への Check Point Identity Awareness の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Check Point Identity Awareness を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com) 以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Check Point Identity Awareness**」と入力します。
4. 結果のパネルから **[Check Point Identity Awareness]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Check Point Identity Awareness 向けに Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Check Point Identity Awareness によって Microsoft Entra を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Check Point Identity Awareness の関連ユーザーとの間にリンク関係を確立する必要があります。

Check Point Identity Awareness を使用して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Check Point Identity Awareness SSO を構成する**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Check Point Identity Awareness のテストユーザーを作成** - B.Simon に対応するユーザーを Check Point Identity Awareness で作成して、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com) 以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Check Point Identity Awareness**&gt;**シングル サインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. **[識別子 (エンティティ ID)]** ボックスに、次のパターンを使用して URL を入力します。`https://<GATEWAY_IP>/connect/spPortal/ACS/ID/<IDENTIFIER_UID>`

    b。 **[応答 URL]** ボックスに、`https://<GATEWAY_IP>/connect/spPortal/ACS/Login/<IDENTIFIER_UID>` のパターンを使用して URL を入力します

    c. **サインオン URL** ボックスに、次のパターンを使用して URL を入力します。`https://<GATEWAY_IP>/connect`

    Note

    これらの値は実際の値ではありません。 これらの値は、実際の識別子、応答 URL、サインオン URL で更新してください。 これらの値を取得するには、[Check Point Identity Awareness クライアント サポート チーム](mailto:support@checkpoint.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで、 **[フェデレーション メタデータ XML]** を探して **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[Check Point Identity Awareness のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Check Point Identity Awareness の SSO の構成

1. Check Point Identity Awareness 企業サイトに管理者としてサインインします。
2. SmartConsole の &gt;**ゲートウェイ & とサーバー** ビューで、**新規 &gt; 詳細 &gt; ユーザーまたは ID &gt; ID プロバイダー**を選択します。
3. **[New Identity Provider](新しい ID プロバイダー)** ウィンドウで、次の手順を実行します。

    [Image: [Identity Provider](ID プロバイダー) セクションのスクリーンショット。]

    a. **[Gateway](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ゲートウェイ)** フィールドで、SAML 認証を実行する必要があるセキュリティ ゲートウェイを選択します。

    b。 **[Service](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サービス)** フィールドで、ドロップダウンから **[Identity Awareness](ID 認識)** を選択します。

    c. **[識別子 (エンティティ ID)]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[識別子]** テキスト ボックスにこの値を貼り付けます。

    d. **[応答 URL]** の値をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL]** テキスト ボックスにこの値を貼り付けます。

    e. **[Import Metadata File](メタデータ ファイルのインポート)** を選択して、ダウンロードした**フェデレーション メタデータ XML** をアップロードします。

    Note

    または、**[Insert Manually](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/手動で挿入)** を選択した後、**エンティティ ID** と**ログイン URL** の値を対応するフィールドに手動で貼り付け、**証明書ファイル**をアップロードすることもできます。

    f. **[OK] を選択**.

#### Check Point Identity Awareness のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Check Point Identity Awareness に作成します。 [Check Point Identity Awareness サポート チーム](mailto:support@checkpoint.com)と連携して、Check Point Identity Awareness プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Check Point Identity Awareness のサインオン URL にリダイレクトされます。
- Check Point Identity Awareness のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Check Point Identity Awareness] タイルを選択すると、このオプションは Check Point Identity Awareness のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->
