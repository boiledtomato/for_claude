# Microsoft Learn — Microsoft Entra / SaaS アプリ連携チュートリアル (part 22)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 77

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/signagelive-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Signagelive を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/signagelive-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Signagelive の間でシングル サインオンを構成する方法について説明します。

この記事では、Signagelive と Microsoft Entra ID を統合する方法について説明します。 Signagelive と Microsoft Entra ID を統合すると、次のことができます。

- Signagelive にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Signagelive に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Signagelive でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Signagelive では、SP によって開始される SSO がサポートされます。
- Signagelive では、自動ユーザー プロビジョニング をサポートしています。

### ギャラリーから Signagelive を追加する

Microsoft Entra ID への Signagelive の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Signagelive を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックス **に「Signagelive**」と入力します。
4. 結果のパネルから Signagelive  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Signagelive の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Signagelive に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Signagelive の関連ユーザーとの間にリンク関係を確立する必要があります。

Signagelive に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Signagelive SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Signagelive テスト ユーザーの作成 - Signagelive** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Signagelive**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法の選択**] ページで、[**SAML**] を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    [**サインオン URL** ボックスに、次のパターンを使用する URL を入力します: `https://login.signagelive.com/sso/<ORGANIZATIONALUNITNAME>`

    手記

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、[Signagelive クライアント サポート チーム](mailto:support@signagelive.com)にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [SAML **を使用して単一 Sign-On を設定する**] ページの[**SAML 署名証明書**]セクションで、要件に従って指定されたオプションから[**証明書 (未加工)**]をダウンロードするには、[**ダウンロード**]を選択します。 次に、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**Signagelive** のセットアップ] セクションで、必要な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Signagelive SSO の構成

Signagelive 側でシングル サインオンを構成するには、ダウンロードした **Certificate (Raw)** とコピーした URL を [Signagelive サポート チーム](mailto:support@signagelive.com)に送信します。 SAML SSO 接続が両方の側で正しく設定されていることを確認します。

#### Signagelive テスト ユーザーの作成

このセクションでは、Signagelive で Britta Simon というユーザーを作成します。 [Signagelive サポート チームと協力して](mailto:support@signagelive.com)、Signagelive プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

Signagelive では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングの構成方法  詳細については、こちらをご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Signagelive のサインオン URL にリダイレクトされます。
- Signagelive のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Signagelive] タイルを選択すると、このオプションは Signagelive のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/signalfx-tutorial"} -->
## Microsoft Entra ID を使用して SignalFx for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/signalfx-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SignalFx の間でシングル サインオンを構成する方法について説明します。

この記事では、SignalFx と Microsoft Entra ID を統合する方法について説明します。 SignalFx と Microsoft Entra ID を統合すると、次のことができます。

- SignalFx にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントで SignalFx に自動的にサインイン (シングル サインオン) するように設定できます。
- 自分のアカウントを 1 か所 (Azure portal) で管理する。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SignalFx でのシングル サインオン (SSO) が有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SignalFx では、**IDP** Initiated SSO がサポートされます。
- SignalFx では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### 手順 1:SignalFx アプリケーションを Azure に追加する

次の手順を使用して、マネージド SaaS アプリのリストに SignalFx アプリケーションを追加します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SignalFx**」と入力します。
4. 結果パネルで **[SignalFx]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。
5. Microsoft Entra 管理センターを開いたままにして、新しいブラウザー タブを開きます。

### 手順 2:SignalFx SSO の構成を開始する

次の手順を使用して、SignalFx SSO の構成プロセスを開始します。

1. 新しく開いたタブで、SignalFx UI にアクセスしてログインします。
2. 上部のメニューで、[統合] を選択 **します**。
3. 検索フィールドに「**Microsoft Entra ID**」と入力して選びます。
4. [ **新しい統合の作成] を**選択します。
5. **[Name]\(名前\)** に、ユーザーにとってわかりやすく認識しやすい名前を入力します。
6. **[Show on login page]\(ログイン ページに表示する\)**をマークします。
    - この機能により、ユーザーが選択できるカスタマイズされたボタンがログイン ページに表示されます。
    - **[名前]** に入力した情報がボタンに表示されます。 したがって、ユーザーが認識しやすい**名前**を入力してください。
    - このオプションが機能するのは、SignalFx アプリケーションにカスタム サブドメイン (**yourcompanyname.signalfx.com** など) を使用している場合のみです。 カスタム サブドメインを取得するには、SignalFx サポートにお問い合わせください。
7. **統合 ID を**コピーします。この情報は、後の手順で必要になります。
8. SignalFx UI は開いたままにしておきます。

### 手順 3: Microsoft Entra SSO を構成する

Microsoft Entra SSO を有効にするには、次の手順を実行します。

1. Microsoft Entra 管理センターに戻り、**SignalFx** アプリケーション統合ページで、**[管理]** セクションを見つけて、**[シングル サインオン]** を選択します。
2. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
3. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
4. **[SAML でシングル サインオンをセットアップします]** ページで、次の手順を実行します。

    ある。 **[識別子]** に、次の URL `https://api.<realm>.signalfx.com/v1/saml/metadata`を入力し、`<realm>`を SignalFx 領域に置き換え、`<integration ID>`を SignalFx UI から前にコピーした統合 ID に置き換えます。 (領域 US0 を除き、URL は `https://api.signalfx.com/v1/saml/metadata` である必要があります)。

    b。 **[応答 URL]** に、次の URL `https://api.<realm>.signalfx.com/v1/saml/acs/<integration ID>`を入力し、`<realm>`を SignalFx 領域に置き換え、`<integration ID>`を SignalFx UI から前にコピーした**統合 ID** に置き換えます。 (US0 を除き、URL は `https://api.signalfx.com/v1/saml/acs/<integration ID>` である必要があります)
5. SignalFx アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。
6. 次の要求が、Active Directory に設定されているソース属性と対応していることを確認します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ユーザー.名 | User.givenname |
    | ユーザーのメールアドレス | User.mail |
    | PersonImmutableID | ユーザー.ユーザープリンシパルネーム |
    | ユーザーの名字 | User.surname |

    注意

    このプロセスでは、Active Directory が少なくとも 1 つの検証済みカスタム ドメインで構成され、このドメイン内の電子メール アカウントにアクセスできる必要があります。 この構成が不明な場合やサポートが必要な場合は、SignalFx サポートにお問い合わせください。
7. **[SAML によるシングル サインオンのセットアップ]** ページの **[SAML 署名証明書]** セクションで **[証明書 (Base64)]** を探し、 **[ダウンロード]** を選択します。 証明書をダウンロードして、コンピューターに保存します。 次に、 **アプリのフェデレーション メタデータ URL** の値をコピーします。この情報は、SignalFx UI の後の手順で必要になります。

    [Image: 証明書のダウンロード リンク]
8. **[SignalFx のセットアップ]** セクションで、**[Microsoft Entra 識別子]** の値をコピーします。 この情報は、SignalFx UI の後の手順で必要になります。

### 手順 4: Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. "**表示名**" フィールドに「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。

### 手順 5: Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に SignalFx へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**SignalFx** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### 手順 6:SignalFx SSO の構成を完了する

1. 前のタブを開き、SignalFx UI に戻って現在の Microsoft Entra 統合ページを表示します。
2. **[証明書 (Base64)] の**横にある **[ファイルのアップロード**] を選択し、前にダウンロード**した Base64 でエンコードされた証明書**ファイルを見つけます。
3. **[Microsoft Entra 識別子]** の横に、先ほどコピーした **Microsoft Entra 識別子**の値を貼り付けます。
4. **[フェデレーション メタデータ URL]** の横に、先ほどコピーした**アプリのフェデレーション メタデータ URL** の値を貼り付けます。
5. **保存** を選択します。

### 手順 7:SSO のテスト

SSO をテストする方法と、SignalFx に初めてログインする際の期待に関する次の情報を確認します。

#### ログインのテスト

- ログインをテストするには、プライベート (シークレット) ウィンドウを使用する必要があります。または、ログアウトしてもかまいません。そうしないと、アプリケーションを構成したユーザーの Cookie が妨げとなり、テスト ユーザーでの正常なログインができなくなります。
- 新しいテスト ユーザーが初めてログインすると、Azure によってパスワードの変更が強制されます。 この場合、SSO ログイン プロセスは完了しません。テスト ユーザーは Azure portal に送られます。 トラブルシューティングを行うには、テスト ユーザーはパスワードを変更し、SignalFx のログイン ページまたはマイ アプリに移動して再試行する必要があります。

    - MyApps で SignalFx タイルを選択すると、SignalFx に自動的にログインします。
        - マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
- SignalFx アプリケーションには、マイ アプリから、または組織に割り当てられたカスタム ログイン ページを介してアクセスできます。 テスト ユーザーは、そのいずれかの場所から統合をテストする必要があります。

    - テスト ユーザーは、先ほどこのプロセスで作成された、**b.simon@contoso.com** の資格情報を使用できます。

#### 初回ログイン

- ユーザーが SAML SSO から初めて SignalFx にログインすると、リンクが記載された SignalFx のメールがそのユーザーに送信されます。 ユーザーは、認証のためにリンクを選択する必要があります。 このメール検証は、初めて使用するユーザーに対してのみ実施されます。
- SignalFx では Just **In Time** ユーザー作成がサポートされています。つまり、SignalFx にユーザーが存在しない場合、ユーザーのアカウントは最初のログイン試行時に作成されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/signiant-media-shuttle-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Signiant Media Shuttle を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/signiant-media-shuttle-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Signiant Media Shuttle の間でシングル サインオンを構成する方法について説明します。

この記事では、Signiant Media Shuttle と Microsoft Entra ID を統合する方法について説明します。 Media Shuttle は、クラウドベースまたはオンプレミスのストレージとの間で大きなファイルとデータ セットを安全に移動するためのソリューションです。 転送は高速になり、FTP よりも最大 100 倍速くなります。

Signiant Media Shuttle と Microsoft Entra ID を統合すると、次のことができます。

- Signiant Media Shuttle にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して Signiant Media Shuttle に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で Signiant Media Shuttle 向けの Microsoft Entra シングル サインオンを構成してテストする必要があります。 Signiant Media Shuttle は、**SP** Initiated シングル サインオンと **Just In Time** ユーザー プロビジョニングをサポートしています。

### [前提条件]

Microsoft Entra ID と Signiant Media Shuttle を統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SAML Web SSO ライセンスを持つ Signiant Media Shuttle サブスクリプションで、IT およびオペレーション管理コンソールにアクセスできます。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから Signiant Media Shuttle アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから Signiant Media Shuttle を追加する

Microsoft Entra アプリケーション ギャラリーから Signiant Media Shuttle を追加して、Signiant Media Shuttle のシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Signiant Media Shuttle**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** テキストボックスに、次のいずれかのパターンで値または URL を入力します。

    | **[構成の種類]** | **識別子** |
    | --- | --- |
    | アカウント レベル | `mediashuttle` |
    | ポータル レベル | `https://<PORTALNAME>.mediashuttle.com` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **[構成の種類]** | **応答 URL** |
    | --- | --- |
    | アカウント レベル | `https://portals.mediashuttle.com.auth` |
    | ポータル レベル | `https://<PORTALNAME>.mediashuttle.com/auth` |

    c. [ **サインオン URL** ] ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **[構成の種類]** | **サインオン URL** |
    | --- | --- |
    | アカウント レベル | `https://portals.mediashuttle.com/auth` |
    | ポータル レベル | `https://<PORTALNAME>.mediashuttle.com/auth` |

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Signiant Media Shuttle サポート チーム](mailto:support@signiant.com)に問い合わせてください。 [基本的な SAML 構成] セクションに示されているパターンを参照することもできます。
6. Signiant Media Shuttle アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、例を示しています。 **[一意のユーザー ID]** の既定値は **user.userprincipalname** ですが、Signiant Media Shuttle ではユーザーのメール アドレスにマップされることが想定されています。 そのため、リストから **user.mail** 属性を使用するか、組織の構成に基づいて適切な属性値を使用できます。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

### Signiant Media Shuttle SSO の構成

**アプリのフェデレーション メタデータ URL** を取得したら、Media Shuttle IT 管理コンソールにサインインします。

Microsoft Entra メタデータを Media Shuttle に追加するには:

1. IT 管理コンソールにログインします。
2. [セキュリティ] ページの [ID プロバイダー メタデータ] フィールドに、コピーした**アプリのフェデレーション メタデータ URL** を貼り付けます。
3. **保存** を選択します。

Media Shuttle 用に Microsoft Entra ID を設定すると、ロール割り当て済みのユーザーとグループが、Microsoft Entra 認証を使用したシングル サインオンを介して Media Shuttle ポータルにサインインできるようになります。

#### Signiant Media Shuttle テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Signiant Media Shuttle に作成します。 Signiant Media Shuttle では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 Signiant Media Shuttle にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

**このポータルへの SAML 認証メンバーの自動追加**が SAML 構成の一部として有効になっていない場合は、`https://<PORTALNAME>.mediashuttle.com/admin`コンソールを使用してユーザーを追加する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Signiant Media Shuttle のサインオン URL にリダイレクトされます。
- Signiant Media Shuttle のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Signiant Media Shuttle] タイルを選択すると、このオプションは Signiant Media Shuttle のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sigstr-tutorial"} -->
## Microsoft Entra ID で Sigstr for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sigstr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sigstr の間でシングル サインオンを構成する方法について説明します。

この記事では、Sigstr と Microsoft Entra ID を統合する方法について説明します。 Sigstr を Microsoft Entra ID と統合すると、次のことができます:

- Sigstr にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Sigstr に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sigstr でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Sigstr では、**IDP** Initiated SSO がサポートされます
- Sigstr では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Sigstr の追加

Microsoft Entra ID への Sigstr の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Sigstr を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Sigstr**」と入力します。
4. 結果のパネルから **[Sigstr]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sigstr の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Sigstr に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Sigstr の関連ユーザーとの間にリンク関係を確立する必要があります。

Sigstr に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sigstr の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Sigstr テスト ユーザーの作成** - Sigstr で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon を表すユーザーにリンクさせる。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Sigstr**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **基本的な SAML 構成** セクションでは、アプリケーションは事前に構成されており、必要な URL は既に Azure に事前に設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
6. Sigstr アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 **[編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: 画像]
7. その他に、Sigstr アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [ユーザー属性] ダイアログの [ユーザー要求] セクションで、以下の手順を実行して、以下の表のように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |

    1. [ **新しい要求の追加]** を選択して、[ **ユーザー要求の管理** ] ダイアログを開きます。
    2. [ **名前** ] ボックスに、その行に表示される属性名を入力します。
    3. **名前空間**は空白のままにします。
    4. **をソースとして属性**を選択します。
    5. **[ソース属性**] の一覧から、その行に表示される属性値を入力します。
    6. **[OK]** を選択します。
    7. **保存** を選択します。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[Set up Sigstr](Sigstr の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

### Sigstr の SSO の構成

**Sigstr** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** とアプリケーション構成からコピーした適切な URL を [Sigstr サポート チーム](mailto:support@sigstr.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Sigstr のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Sigstr に作成します。 Sigstr では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Sigstr にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Sigstr] タイルを選択すると、SSO を設定した Sigstr に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/silkroad-life-suite-tutorial"} -->
## Microsoft Entra ID で SilkRoad Life Suite をシングルサインオン用に構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/silkroad-life-suite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SilkRoad Life Suite の間にシングル サインオンを構成する方法について説明します。

この記事では、SilkRoad Life Suite と Microsoft Entra ID を統合する方法について説明します。 SilkRoad Life Suite を Microsoft Entra ID と統合すると、次のことができます。

- SilkRoad Life Suite にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SilkRoad Life Suite に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SilkRoad Life Suite でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SilkRoad Life Suite では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの SilkRoad Life Suite の追加

Microsoft Entra ID への SilkRoad Life Suite の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SilkRoad Life Suite を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SilkRoad Life Suite**」と入力します。
4. 結果のパネルから **[SilkRoad Life Suite]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SilkRoad Life Suite 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、SilkRoad Life Suite に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SilkRoad Life Suite の関連ユーザーとの間にリンク関係を確立する必要があります。

SilkRoad Life Suite に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SilkRoad Life Suite の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SilkRoad Life Suite テストユーザーの作成 - Microsoft Entra のユーザーである B.Simon の対応者として SilkRoad Life Suite にリンクされたユーザーを作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SilkRoad Life Suite**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル**がある場合は、次の手順を実行します。

    注

    この記事の後半で説明する **サービス プロバイダー メタデータ ファイル** を取得します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: スクリーンショットは、[メタデータ ファイルをアップロードする] リンクを含む、[基本的な SAML 構成] を示しています。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: スクリーンショットは、ファイルを選択してアップロードできるダイアログ ボックスを示しています。]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    注

    **識別子**と**応答 URL** の値が自動的に設定されない場合は、要件に従って値を手動で入力します。

    d. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.silkroad-eng.com/Authentication/`
6. [ **基本的な SAML 構成]** セクションで、 **Service Provider メタデータ ファイル**がない場合は、次の手順を実行します。

    ある。 **[識別子]** ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 識別子 |
    | --- |
    | `https://<SUBDOMAIN>.silkroad-eng.com/Authentication/SP` |
    | `https://<SUBDOMAIN>.silkroad.com/Authentication/SP` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 応答 URL |
    | --- |
    | `https://<SUBDOMAIN>.silkroad-eng.com/Authentication/` |
    | `https://<SUBDOMAIN>.silkroad.com/Authentication/` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.silkroad-eng.com/Authentication/`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[SilkRoad Life Suite クライアント サポート チーム](https://www.silkroad.com/locations/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[SilkRoad Life Suite のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SilkRoad Life Suite の SSO の構成

1. SilkRoad 企業サイトに管理者としてサインインします。

    注

    Microsoft Entra ID とのフェデレーションを構成するために SilkRoad Life Suite の認証アプリケーションへのアクセス権を取得するには、SilkRoad サポートまたは SilkRoad サービス担当者にお問い合わせください。
2. **[サービス プロバイダー**] に移動し、[**フェデレーションの詳細**] を選択します。

    [Image: [サービス プロバイダー] から [フェデレーションの詳細] を選択した画面のスクリーンショット。]
3. [ **フェデレーション メタデータのダウンロード**] を選択し、コンピューターにメタデータ ファイルを保存します。 ダウンロードしたフェデレーション メタデータは、**[基本的な SAML 構成]** セクションで **[サービス プロバイダー メタデータ ファイル]** として使用します。

    [Image: [Download Federation Metadata](フェデレーション メタデータのダウンロード) リンクのスクリーンショット。]
4. **SilkRoad** アプリケーションで、[**認証ソース**] を選択します。

    [Image: [Authentication Sources](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証ソース) が選択された画面のスクリーンショット。]
5. [ **認証ソースの追加]** を選択します。

    [Image: [Add Authentication Source](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証ソースの追加) リンクが選択された画面のスクリーンショット。]
6. **[Add Authentication Source (認証ソースの追加)]** セクションで、次の手順に従います。

    [Image: [Create Identity Provider using File Data](ファイル データを使用して ID プロバイダーを作成) ボタンが選択されている [Add Authentication Source](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証ソースの追加) 画面のスクリーンショット。]

    ある。 **オプション 2 - メタデータ ファイル**で、[**参照**] を選択して、Azure portal からダウンロードしたメタデータ ファイルをアップロードします。

    b。 **[ファイル データを使用して ID プロバイダーを作成する] を選択します**。
7. [ **認証ソース** ] セクションで、[ **編集**] を選択します。

    [Image: [編集] オプションが選択されている [Authentication Sources](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証ソース) 画面のスクリーンショット。]
8. **[Edit Authentication Source (認証ソースの編集)]** ダイアログ ボックスで、次の手順を実行します。

    [Image: [Edit Authentication Source](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証ソースの編集) ダイアログ ボックスのスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[Enabled]** で **[Yes]** を選択します。

    b。 **[EntityId]** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    c. **[IdP Description] (IdP の説明)** テキストボックスに、構成の説明を入力します (例: **Microsoft Entra SSO**)。

    d. **[メタデータ ファイル]** テキストボックスに、先ほどダウンロードした**メタデータ** ファイルをアップロードします。

    え [ **IdP 名]** ボックスに、構成に固有の名前を入力します (例: *Azure SP*)。

    f. **[ログアウト サービス URL]** テキストボックスに**ログアウト URL** の値を貼り付けます。

    ジー **[シングル サインオン サービス URL]** テキストボックスに**ログイン URL** の値を貼り付けます。

    h. **保存** を選択します。
9. その他のすべての認証のソースを無効にします。

    [Image: [認証ソース] 画面のスクリーンショット。ここで、他のソースを無効にすることができます。]

#### SilkRoad Life Suite のテスト ユーザーの作成

このセクションでは、SilkRoad Life Suite で Britta Simon というユーザーを作成します。 SilkRoad Life Suite プラットフォームにユーザーを追加するには、[SilkRoad Life Suite Client サポート チーム](https://www.silkroad.com/locations/)に問い合わせてください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SilkRoad Life Suite のサインオン URL にリダイレクトされます。
- SilkRoad Life Suite のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SilkRoad Life Suite] タイルを選択すると、このオプションは SilkRoad Life Suite のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/silverback-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Silverback を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/silverback-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Silverback の間にシングル サインオンを構成する方法について説明します。

この記事では、Silverback と Microsoft Entra ID を統合する方法について説明します。 Silverback を Microsoft Entra ID と統合すると、次のことができます。

- Silverback にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Silverback に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Silverback でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Silverback では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの Silverback の追加

Silverback の Microsoft Entra ID への統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Silverback を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「 **Silverback** 」と入力します。
4. 結果のパネルから **[Silverback]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Silverback 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Silverback に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Silverback の関連ユーザーとの間にリンク関係を確立する必要があります。

Silverback に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Silverback の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Silverback テスト ユーザーの作成** - Silverback で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Silverback**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア。 **[識別子]** ボックスに、`<YOURSILVERBACKURL>.com` という形式で URL を入力します。

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOURSILVERBACKURL>.com/sts/authorize/login`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<YOURSILVERBACKURL>.com/ssp`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[Silverback クライアント サポート チーム](mailto:helpdesk@matrix42.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Silverback SSO の構成

1. 別の Web ブラウザーで、Silverback サーバーに管理者としてログインします。
2. **[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者)**&gt;**[Authentication Provider](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロバイダー)** に移動します。
3. **[Authentication Provider Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/認証プロバイダーの設定)** ページで、次の手順を行います。

    [Image: 認証プロバイダーの設定を示すスクリーンショット。]

    ア。 **[URL からインポートする]** を選択します。

    b。 コピーしたメタデータ URL を貼り付け、[ **OK]** を選択します。

    c. **[OK]** をクリックすると、値が自動的に設定されます。

    d. **[Show on Login Page](ログイン ページに表示する)** をオンにします。

    え (省略可能) Microsoft Entra で承認されたユーザーを自動的に追加するには、**[Dynamic User Creation] (動的なユーザー作成)** をオンにします。

    f. [Self Service Portal](セルフサービス ポータル) でボタンの **[Title](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/タイトル)** を作成します。

    ジー [**ファイルの選択**] を選択して**アイコン**をアップロードします。

    h. ボタンの背景**色**を選択します。

    一. **保存** を選択します。

#### Silverback テスト ユーザーを作成する

Microsoft Entra ユーザーが Silverback にログインできるようにするには、そのユーザーを Silverback にプロビジョニングする必要があります。 Silverback では、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. Silverback Server に管理者としてログインします。
2. **[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** に移動し、**新しいデバイス ユーザーを追加**します。
3. **[Basic](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/基本)** ページで、次の手順を行います。

    [Image: Azure のユーザー アカウントを示すスクリーンショット。]

    ア。 **[Username](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー名)** ボックスに、ユーザーの名前を入力します (例: **Britta**)。

    b。 **[First Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名)** ボックスに、ユーザーの名を入力します (例: **Britta**)。

    c. **[Last Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/姓)** ボックスに、ユーザーの姓を入力します (例: **Simon**)。

    d. [ **電子メール アドレス** ] テキスト ボックスに、ユーザーの電子メール ( **Brittasimon@contoso.com**など) を入力します。

    え **[Password](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パスワード)** ボックスにパスワードを入力します。

    f. **[パスワードの確認入力]** ボックスにパスワードをもう一度入力して確認します。

    ジー **保存** を選択します。

注

手動で各ユーザーを作成しない場合は、**[Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理者)**&gt; の **[Dynamic User Creation](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/動的ユーザーの作成)** チェックボックスをオンにします。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Silverback のサインオン URL にリダイレクトされます。
- Silverback のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Silverback] タイルを選択すると、このオプションは Silverback のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/simple-in-out-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Simple In/Out を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/simple-in-out-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Microsoft Entra ID から Simple In/Out にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、自動ユーザー プロビジョニングを構成するために Simple In/Out と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID は、Microsoft Entra プロビジョニング サービスを使用して、ユーザーを [Simple In/Out](https://www.simpleinout.com) に自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「 [Microsoft Entra ID を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)参照してください。

### サポートされている機能

- Simple In/Out でユーザーを作成します。
- アクセスが不要になった場合は、Simple In/Out でユーザーを削除します。
- Microsoft Entra ID と Simple In/Out の間でユーザー属性の同期を維持します。
- Simple In/Out に[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)します (推奨)。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- 管理者アクセス許可を持つ Simple In/Out のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

- [プロビジョニング サービスのしくみについて説明](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)します。
- [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
- [Microsoft Entra ID と Simple In/Out の間でマップ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)するデータを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Simple In/Out を構成する

Simple In/Out 管理者レベルのユーザーは、シングル サインオンを構成できます。

1. Web ブラウザーで、 [simpleinout.com](https://www.simpleinout.com) に移動し、Simple In/Out 管理者の資格情報でサインインします。
2. 右上の **[設定]** を選択します。
3. 左側の **[エンタープライズ**] メニューで [**シングル サインオン**] を選択します。
4. エンタープライズ レベルのプランをまだ使用していない場合は、この設定ページで、シングル サインオンを使用するためにプランをアップグレードする必要があることを通知します。 必要に応じて、リンクに従ってプランをアップグレードし、このページに戻ります。
5. プロバイダーが **Microsoft** に設定されていることを確認し、[ **シングル サインオンの接続** ] ボタンを選択します。
6. シングル サインオンを有効にすることを確認するメッセージが表示されます。 **[OK] を**選択して確定します。
7. 回復キーで **[表示** ] を選択し、このキー全体を安全な場所に保存します。 **大事な！** Microsoft アカウントからロックアウトされ、アクセスなしで SSO を切断する必要がある場合は、回復キーを Simple In/Out テクニカル サポートに中継する必要があります。
8. ベアラー トークンで **[表示** ] を選択し、メモしておきます。 これは手順 5.6 で必要になります。

    - 新しいユーザーが Microsoft Entra ID から Simple In/Out にプロビジョニングされると、Simple In/Out によってそのユーザーのロールが組織の既定のロールに設定されます。 このロールは、Simple In/Out 内のユーザーのアクセス許可を管理します。SSO に変換できる既存のユーザーの場合、Simple In/Out は既存のロールを維持します。
    - ユーザーが Simple In/Out にプロビジョニングされると、管理者レベルのユーザーはユーザーのロールを編集できます。 これを行うには、簡易入力/出力ボードでユーザーを選択し、ユーザーのプロファイル ダイアログに表示される [ **ユーザーの編集** ] ボタンを選択します。
    - Simple In/Out で既定のロールを変更し、Simple In/Out の Web サイトでロールのアクセス許可をカスタマイズできます。
9. Simple In/Out の Web サイト内で、右上の **[設定]** を選択します。
10. 左側の **[ユーザー**] メニューの下にある [**ロール**] を選択します。
11. 緑色のチェックマークで指定されている既定のロールに関連付けられている **[編集]** ボタンを選択します。
12. ここからすべての設定を変更でき、すぐに有効になります。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Simple In/Out を追加する

Microsoft Entra アプリケーション ギャラリーから Simple In/Out を追加して、Simple In/Out へのプロビジョニングの管理を開始します。SSO 用に Simple In/Out を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからのアプリケーションの追加の詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングされるユーザーのスコープを設定する場合は、 [スコープ フィルターを使用](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Simple In/Out に自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID のユーザー割り当てに基づいて Simple In/Out でユーザーを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Simple In/Out の自動ユーザー プロビジョニングを構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**にアクセスする

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧 **で[単純入力/出力**]を選択します。

    [Image: アプリケーションの一覧の [単純な入力/出力] リンクのスクリーンショット。]
4. [プロビジョニング] タブ **を** 選択します。

    [Image: アプリケーション管理メニューの [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、簡易入力/出力テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Simple In/Out に接続できることを確認します。接続に失敗した場合は、Simple In/Out アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. [ **通知メール** ] フィールドに、プロビジョニング エラー通知を受け取るユーザーのメール アドレスを入力し、[ **エラーが発生したときに電子メール通知を送信** する] チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. [属性マッピング] セクションで、Microsoft Entra ID から Simple In/Out に同期されるユーザー **属性** を確認します。 [ **照合** プロパティ] として選択されている属性は、更新操作で Simple In/Out のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Simple In/Out API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | 単純な入力/出力で必須 |
    | --- | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ | ✓ |
    | 活動中 | ブール値 |  |  |
    | displayName | 糸 | ✓ | ✓ |
    | タイトル | 糸 |  |  |
    | phoneNumbers[type eq "work"].value | 糸 |  |  |
    | phoneNumbers[type eq "mobile"].value | 糸 |  |  |
    | phoneNumbers[type eq "fax"].value | 糸 |  |  |
    | externalId | 糸 |  | ✓ |
12. [属性マッピング] セクションで、Microsoft Entra ID から Simple In/Out に同期されるグループ **属性** を確認します。 **[照合**] プロパティとして選択されている属性は、更新操作で Simple In/Out のグループとの照合に使用されます。 [ **保存** ] ボタンを選択して変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます | 単純な入力/出力で必須 |
    | --- | --- | --- | --- |
    | displayName | 糸 | ✓ | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/simple-sign-tutorial"} -->
## Microsoft Entra ID で Single Sign-On 用の Simple Sign を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/simple-sign-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Simple Sign の間でシングル サインオンを構成する方法について説明します。

この記事では、Simple Sign と Microsoft Entra ID を統合する方法について説明します。 Simple Sign と Microsoft Entra ID を統合すると、次のことができます。

- Simple Sign にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Simple Sign に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Simple Sign でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Simple Sign では、**IDP** Initiated SSO がサポートされます。

### ギャラリーからの Simple Sign の追加

Microsoft Entra ID への Simple Sign の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Simple Sign を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Simple Sign**」と入力します。
4. 結果のパネルから **[Simple Sign]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Simple Sign 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Simple Sign に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Simple Sign の関連ユーザーの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Simple Sign と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Simple Sign SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Simple Sign のテスト ユーザーの作成** - Simple Sign における B.Simon の対応ユーザーを作成し、そのユーザーを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Simple Sign**&gt;**シングルサインオン**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.simplesign.io/saml/simplesamlphp/www/module.php/saml/sp/metadata.php/cloudfish-sp`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.simplesign.io/saml/simplesamlphp/www/module.php/saml/sp/saml2-acs.php/cloudfish-sp`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、[Simple Sign クライアント サポート チーム](mailto:info@simplesign.io)に問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Simple Sign のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Simple Sign SSO の構成

**Simple Sign** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Simple Sign サポート チーム](mailto:info@simplesign.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Simple Sign のテスト ユーザーの作成

このセクションでは、Simple Sign で Britta Simon というユーザーを作成します。 [Simple Sign サポート チーム](mailto:info@simplesign.io)と連携して、Simple Sign プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Simple Sign に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Simple Sign] タイルを選択すると、SSO を設定した Simple Sign に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/simplenexus-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SimpleNexus を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/simplenexus-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SimpleNexus の間のシングル サインオンを構成する方法について説明します。

この記事では、SimpleNexus と Microsoft Entra ID を統合する方法について説明します。 SimpleNexus を Microsoft Entra ID と統合すると、次のことが可能になります。

- SimpleNexus にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで SimpleNexus に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SimpleNexus でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SimpleNexus では、 **SP** によって開始される SSO がサポートされます。

### ギャラリーからの SimpleNexus の追加

Microsoft Entra ID への SimpleNexus の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SimpleNexus を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「SimpleNexus**」と入力します。
4. 結果パネルから **SimpleNexus** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SimpleNexus 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SimpleNexus に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SimpleNexus の関連ユーザー間にリンク関係を確立する必要があります。

SimpleNexus に対して Microsoft Entra SSO を構成およびテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SimpleNexus SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SimpleNexus テスト ユーザーを作成** - Microsoft Entra のユーザー表現にリンクさせた、SimpleNexus で B.Simon と対応するユーザーを持つようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SimpleNexus**&gt;**シングルサインオン**に移動する。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://simplenexus.com/<COMPANY_NAME>_login`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://simplenexus.com/<COMPANY_NAME>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには [、SimpleNexus クライアント サポート チーム](https://www.simplenexus.com/contact-us/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **SimpleNexus のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SimpleNexus SSO の構成

**SimpleNexus** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [SimpleNexus サポート チーム](https://www.simplenexus.com/contact-us/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SimpleNexus テスト ユーザーの作成

Microsoft Entra ユーザーが SimpleNexus にログインできるようにするには、ユーザーを SimpleNexus にプロビジョニングする必要があります。 SimpleNexus の場合、プロビジョニングは、テナント管理者が手動で実行するタスクです。

注

他の SimpleNexus ユーザー アカウント作成ツールや、SimpleNexus から提供されている API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SimpleNexus のサインオン URL にリダイレクトされます。
- SimpleNexus のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SimpleNexus] タイルを選択すると、このオプションは SimpleNexus のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sis-enterprise-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SIS Enterprise を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sis-enterprise-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SIS Enterprise の間でシングル サインオンを構成する方法について説明します。

この記事では、SIS Enterprise と Microsoft Entra ID を統合する方法について説明します。 SIS Enterprise と Microsoft Entra ID を統合すると、次のことができます。

- SIS Enterprise へのアクセス権を持つユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SIS Enterprise に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SIS Enterprise でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SIS Enterprise では、 **IDP** Initiated SSO がサポートされます。
- SIS Enterprise では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの SIS Enterprise の追加

MICROSOFT Entra ID への SIS Enterprise の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SIS Enterprise を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**新規アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「SIS Enterprise」**と入力します。
4. 結果パネルから **SIS Enterprise** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SIS Enterprise の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SIS Enterprise に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SIS Enterprise の関連ユーザーとの間にリンク関係を確立する必要があります。

SIS Enterprise に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SIS Enterprise SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SIS Enterprise のテスト ユーザーの作成 - SIS Enterprise** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SIS Enterprise**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成の編集] を示すスクリーンショット。]
5. [ **SAML でのシングル サインオンの設定** ] ページで、次のフィールドの値を入力します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.tractionguest.com/saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENVIRONMENT>.tractionguest.com/sessions/sso/callback`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、SIS Enterprise サポート チーム](mailto:https://signinenterprise.com/support/) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **SIS Enterprise のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SIS Enterprise SSO の構成

**SIS Enterprise** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [SIS Enterprise サポート チーム](mailto:https://signinenterprise.com/support/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SIS Enterprise テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを SIS Enterprise に作成します。 SIS Enterprise では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 SIS Enterprise にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SIS Enterprise に自動的にサインインします
- Microsoft マイ アプリを使用できます。 マイ アプリで [SIS Enterprise] タイルを選択すると、SSO を設定した SIS Enterprise に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/siteintel-tutorial"} -->
## Microsoft Entra ID で SiteIntel for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/siteintel-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SiteIntel の間でシングル サインオンを構成する方法について説明します。

この記事では、SiteIntel と Microsoft Entra ID を統合する方法について説明します。 SiteIntel と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID を使って、誰が SiteIntel にアクセスできるかを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SiteIntel に自動的にサインインできるようにします。
- 1 つの中央の場所 (Azure portal) でアカウントを管理します。

サービスとしてのソフトウェア (SaaS) アプリと Microsoft Entra ID の統合の詳細については、「[アプリケーション アクセスと Microsoft Entra ID でのシングル サインオンとは」を参照してください。](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on).

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SiteIntel でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SiteIntel では、SP によって開始される SSO と IdP によって開始される SSO がサポートされます。
- SiteIntel を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用できます。 セッション制御は条件付きアクセスから拡張されます。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)でセッション制御を適用する方法について説明します。

### ギャラリーからの SiteIntel の追加

Microsoft Entra ID への SiteIntel の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SiteIntel を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. ギャラリー **ボックスから [** 追加] ボックスに、「SiteIntel 」と入力します。
4. 結果の一覧で SiteIntel 選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SiteIntel の Microsoft Entra シングル サインオンの構成とテスト

*B.Simon*というテスト ユーザーを使用して、SiteIntel に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SiteIntel の関連ユーザーとの間にリンク関係を確立する必要があります。

SiteIntel で Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します。

1. **ユーザーがこの機能を使用できるように Microsoft Entra SSO** を構成します。

    ある。 **Microsoft Entra テスト ユーザーを作成** して、ユーザー B.Simon で Microsoft Entra のシングル サインオンをテストします。

    b。 **Microsoft Entra テスト ユーザーを割り当てて** 、ユーザー B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SiteIntel SSO** を構成して、アプリケーション側でシングル サインオン設定を構成します。

    - **SiteIntelでテストユーザーを作成し、ユーザーB.Simonに対応するようにします。そして、このユーザーをMicrosoft Entraのユーザーとしてリンクします。**
3. **SSO** をテストして、構成が機能することを確認します。

### Microsoft Entra SSO の構成

Azure portal で Microsoft Entra SSO を有効にするには、次の操作を行います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SiteIntel** アプリケーション統合ページに移動し、[**管理**] セクションに移動して、**シングル サインオン**を選択します。
3. [**シングル サインオン方法の選択]** ページで、[SAML **]**を選択します。
4. [**SAML** でのシングル サインオンの設定] ページで、[**基本的な SAML 構成**] の横にある [**編集 (ペン アイコン)**] を選択します。

    [Image: [SAML を使用して単一Sign-On を設定する] ウィンドウのスクリーンショット]
5. IdP 開始モードでアプリケーションを構成するには、**基本的な SAML 構成** セクションで、次の操作を行います。

    ある。 [**識別子** ボックスに、URL を次の形式で入力します。`urn:amazon:cognito:sp:<REGION>_<USERPOOLID>`

    b。 [**応答 URL**] ボックスに、URL を次の形式で入力します。`https://<CLIENT>.auth.siteintel.com/saml2/idpresponse`

    c. [**リレー状態** ボックスに、URL を次の形式で入力します: `https://<CLIENT>.siteintel.com`
6. SP 開始モードでアプリケーションを構成するには、[**追加の URL**設定] を選択し、次の操作を行います。

    - [**サインオン URL** ボックスに、URL を次の形式で入力します。`https://<CLIENT>.siteintel.com`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレーステートを用いて更新してください。 これらの値を取得するには、SiteIntel クライアント サポート チーム にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
7. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書の**] セクションで、[**のコピー]** ボタンを選択して、[**アプリフェデレーション メタデータ URL**] ボックスの URL をコピーします。

    [Image: [アプリのフェデレーション メタデータ URL] の [コピー] ボタン] のスクリーンショット

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SiteIntel SSO の構成

SiteIntel 側でシングル サインオンを構成するには、**アプリフェデレーション メタデータ URL** ボックスからコピーした URL を、[SiteIntel サポート チーム](mailto:support@intalytics.com)に送信します。 この値を設定して、両方の側で SAML SSO 接続を正しく確立します。

#### SiteIntel テスト ユーザーの作成

このセクションでは、SiteIntel で britta Simon  というユーザーを作成します。 SiteIntel サポート チーム  と連携して、SiteIntel プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して、Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [**SiteIntel**] タイルを選択すると、SSO を設定した SiteIntel に自動的にサインインします。 アクセス パネルの詳細については、「[アクセス パネル](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)の概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skedda-tutorial"} -->
## Microsoft Entra ID で Skedda for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skedda-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Skedda 間にシングル サインオンを構成する方法について説明します。

この記事では、Skedda と Microsoft Entra ID を統合する方法について説明します。 Skedda を Microsoft Entra ID と統合すると、次のことができます。

- Skedda にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Skedda に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Skedda でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Skedda では、**SP と IDP によって開始される SSO** がサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Skedda の追加

Microsoft Entra ID への Skedda の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Skedda を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックス**に「Skedda**」と入力します。
4. 結果のパネルから **[Skedda** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Skedda 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Skedda に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Skedda の関連ユーザーとの間にリンク関係を確立する必要があります。

Skedda に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Skedda の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Skeddaテストユーザーを作成する** - これは Skedda の B.Simon に対応するユーザーで、Microsoft Entra のユーザー表現にリンクされています。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Skedda**&gt;**シングルサインオン**のページに移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://app.skedda.com/saml2/acs`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.skedda.com/account/externallogin?returnUrl=<CUSTOM_URL>`

    注

    これは実際の値ではありません。 実際のサインオン URL で値を更新します。 この値を取得するには、 [Skedda クライアント サポート チーム](mailto:info@skedda.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. **[保存] を選択します**。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. [ **Skedda のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Skedda の SSO の構成

**Skedda** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Skedda サポート チームに](mailto:info@skedda.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Skedda のテスト ユーザーの作成

このセクションでは、Skedda で B.Simon というユーザーを作成します。 [Skedda サポート チーム](mailto:info@skedda.com)と協力して、Skedda プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Skedda のサインオン URL にリダイレクトされます。
- Skedda のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Skedda に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Skedda] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Skedda に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sketch-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Sketch を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sketch-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sketch 間にシングル サインオンを構成する方法について説明します。

この記事では、Sketch と Microsoft Entra ID を統合する方法について説明します。 Sketch を Microsoft Entra ID と統合すると、次のことができるようになります。

- Sketch にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Sketch に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Sketch でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Sketch では、**SP** Initiated SSO がサポートされます。
- Sketch では、**Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーから Sketch を追加する

Microsoft Entra ID への Sketch の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Sketch を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Sketch**」と入力します。
4. 結果パネルから **[Sketch]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sketch 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Sketch に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Sketch の関連ユーザーとの間にリンク関係を確立する必要があります。

Sketch に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sketch SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### スケッチでワークスペースに短い名前を選択する

短い名前を選択して情報を収集し、Microsoft Entra ID でセットアップ プロセスを続行するには、次の手順に従います。

注

このプロセスを開始する前に、ワークスペースで SSO が使用できることを確認し、ワークスペースの [管理] パネルに [SSO] タブがあることを確認します。 [SSO] タブが表示されない場合は、カスタマー サポートにお問い合わせください。

1. 管理として[ワークスペースにサインイン](https://www.sketch.com/signin/)します。
2. サイドバーの [ **ユーザーと設定]** セクションに移動します。
3. [ **シングル サインオン** ] タブを選択します。
4. **短い名前を選択してください**。
5. 一意の名前を入力します。この名前は 16 文字未満で文字、数字、ハイフンのみを含めることができます。 この名前は後で編集できます。
6. **送信**を選択します。
7. 最初のタブ [ **ID プロバイダーのセットアップ] を選択します**。 このタブには、Microsoft Entra ID との統合を設定するために必要な一意のワークスペース値が表示されます。
    1. **EntityID:** Microsoft Entra ID では、これが `Identifier` フィールドです。
    2. **ACS URL:** Microsoft Entra ID では、これが `Reply URL` フィールドです。

これらの値は必ずすぐわかるようにしておく必要があります。 これらは、次のステップで必要になります。 各値の横にある [コピー] を選択して、クリップボードにコピーします。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sketch**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[識別子]** ボックスで、前の手順の `EntityID` フィールドを使用します。 `sketch-<uuid_v4>` のように表示されます。

    b。 **[応答 URL]** ボックスで、前の手順の `ACS URL` フィールドを使用します。 `https://sso.sketch.com/saml/acs?id=<uuid_v4>` のように表示されます。
6. [ **追加の URL の設定] を** 選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.sketch.com`

    注

    「**スケッチでワークスペースに短い名前を選択する**」の項から **[識別子]** と [応答 URL] の値を使用してください。
7. Sketch アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性マッピングの画像を示すスクリーンショット。]
8. その他に、Sketch アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | メール | ユーザーのメールアドレス |
    | 名（ファーストネーム） | ユーザー.ファーストネーム |
    | 名字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sketch SSO の構成

スケッチで構成を完了するには、次の手順に従います。

1. ワークスペースで、**[シングル サインオン]** ウィンドウの **[Set up Sketch](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/スケッチのセットアップ)** タブに移動します。
2. **XML メタデータ ファイルのインポート**に関するセクションで前にダウンロードした XML ファイルをアップロードします。
3. ログアウトします。
4. [ **SSO でサインイン] を**選択します。
5. 前の手順で構成した短い名前を使用して続行します。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Sketch のサインオン URL にリダイレクトされます。
- Sketch のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [スケッチ] タイルを選択すると、このオプションはスケッチ サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skilljar-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Skilljar を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skilljar-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Skilljar の間でシングル サインオンを構成する方法について説明します。

この記事では、Skilljar と Microsoft Entra ID を統合する方法について説明します。 Skilljar と Microsoft Entra ID を統合すると、次のことができます。

- Skilljar にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Skilljar に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Skilljar でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Skilljar では、**SP** Initiated SSO がサポートされます。
- Skilljar では、Just-In-Time  ユーザー プロビジョニングがサポートされます。

### ギャラリーから Skilljar を追加する

Microsoft Entra ID への Skilljar の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Skilljar を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Skilljar**」と入力します。
4. 結果パネルから Skilljar  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Skilljar の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Skilljar に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Skilljar の関連ユーザーとの間にリンク関係を確立する必要があります。

Skilljar に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Skilljar SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Skilljar のテストユーザーを作成する** - Skilljar で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Skilljar**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    a. [**識別子 (エンティティ ID)** テキスト ボックスに、次のパターンを使用して URL を入力します:`https://<companyname>.skilljar.com/`

    b。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<companyname>.skilljar.com/`

    手記

    これらの値は実際の値ではありません。 実際の識別子とサインオン URL でこれらの値を更新します。 これらの値を取得するには、[Skilljar クライアント サポート チーム](https://support.skilljar.com/hc/)に連絡してください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **Skilljar** の設定セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Skilljar SSO の構成

Skilljar **側** でシングルサインオンを構成するには、ダウンロードした **フェデレーションメタデータ XML**と **名前識別子形式の値 - `urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress`** を [Skilljar サポートチーム](https://support.skilljar.com/hc/)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Skilljar テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Skilljar に作成します。 Skilljar では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Skilljar にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

手記

ユーザーを手動で作成する必要がある場合は、[Skilljar サポート チーム](https://support.skilljar.com/hc/)にお問い合わせください。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Skilljar のサインオン URL にリダイレクトされます。
- Skilljar のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Skilljar] タイルを選択すると、このオプションは Skilljar のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skillport-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Skillport を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skillport-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Skillport の間でシングル サインオンを構成する方法について説明します。

この記事では、Skillport と Microsoft Entra ID を統合する方法について説明します。 Skillport と Microsoft Entra ID を統合すると、次のことができます。

- Skillport にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Skillport に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Skillport でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Skillport は SP **によって開始された SSO** をサポートします。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Skillport の追加

Microsoft Entra ID への Skillport の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Skillport を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックスに「Skillport  入力します。
4. 結果のパネルから Skillport  選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Skillport の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Skillport に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Skillport の関連ユーザーとの間にリンク関係を確立する必要があります。

Skillport で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Skillport SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Skillport テスト ユーザーを作成し、** Microsoft Entra のユーザー表現にリンクされた Skillport 内の B.Simon に対応するユーザーを作成します。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Skillport**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法の選択]** ページで、[**SAML**] を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成 の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    1. [**サインオン URL** テキスト ボックスに、次のいずれかの URL を入力します。

        EU データセンター: `https://adfs.skillport.eu`

        米国データセンター: `https://sso.skillport.com`
    2. [**識別子** ボックスに、次のいずれかの URL を入力します。

        EU データセンター: `http://adfs.skillport.eu/adfs/services/trust`

        米国データセンター: `https://sso.skillport.com`
    3. [**応答 URL**] ボックスに、次のいずれかの URL を入力します。

        EU データセンター: `https://adfs.skillport.eu/adfs/ls/`

        米国データセンター: `https://sso.skillport.com/sp/ACS.saml2`
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [**Skillport** のセットアップ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Skillport SSO の構成

シングル サインオンをSkillport  側で構成するには、ダウンロードした **フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切なURLを [Skillportサポートチーム](https://www.skillsoft.com/about/contact-us)に送信することが必要です。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Skillport テスト ユーザーの作成

Skillport テスト ユーザーを作成するには、エンド ユーザーの要件に応じて複数のビジネス シナリオがあるため、Skillport サポート チーム  連絡する必要があります。 ユーザーと話し合った後、構成します。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Skillport のサインオン URL にリダイレクトされます。
- Skillport のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Skillport] タイルを選択すると、このオプションは Skillport のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skills-workflow-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Skills Workflow を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skills-workflow-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Skills Workflow の間でシングル サインオンを構成する方法について説明します。

この記事では、Skills Workflow と Microsoft Entra ID を統合する方法について説明します。 Skills Workflow と Microsoft Entra ID を統合すると、次のことができます。

- Skills Workflow にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Skills Workflow に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Skills Workflow でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Skills Workflow では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Skills Workflow の追加

Microsoft Entra ID への Skills Workflow の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Skills Workflow を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Skills Workflow**」と入力します。
4. 結果のパネルから **[Skills Workflow]** を選択し、そのアプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Skills Workflow 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使って、Skills Workflow に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Skills Workflow の関連ユーザーとの間にリンク関係を確立する必要があります。

Skills Workflow に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザー** の作成 - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の割り当て - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Skills Workflow SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Skills Workflow のテストユーザーを作成** - Skills Workflow 内で B.Simon に対応するユーザーを作成し、このユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Skills Workflow**&gt;**シングルサインオン**を表示します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [**識別子** テキスト ボックスに、URL: `https://auth.skillsworkflow.com/saml2` を入力します。

    b。 [**サインオン URL** テキスト ボックスに、URL: `https://auth.skillsworkflow.com/saml2/acs` を入力します。
6. [**SAML** でのシングル サインオンの設定] ページの [**SAML 署名証明書**] セクションで、[フェデレーション メタデータ XML  検索し、**ダウンロード** を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[Skills Workflow のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

B.Simon というテスト ユーザー アカウントを作成するには、[のガイドラインに従った後で](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) のクイックスタートを使用して、ユーザー アカウントを作成して割り当ててください。

### Skills Workflow SSO の構成

**Skills Workflow** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [Skills Workflow サポート チーム](mailto:support@skillsworkflow.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Skills Workflow テスト ユーザーの作成

このセクションでは、Skills Workflow で B.Simon というユーザーを作成します。 [Skills Workflow サポート チーム](mailto:support@skillsworkflow.com)と連携して、Skills Workflow プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Skills Workflow のサインオン URL にリダイレクトされます。
- Skills Workflow のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Skills Workflow] タイルを選択すると、このオプションは Skills Workflow のサインオン URL にリダイレクトされます。 詳細については、「[Microsoft Entra のマイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skillsbase-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用の Skills Base を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skillsbase-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Skills Base の間でシングル サインオンを構成する方法について説明します。

この記事では、Skills Base と Microsoft Entra ID を統合する方法について説明します。 Skills Base と Microsoft Entra ID を統合すると、次のことができます。

- Skills Base にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで Skills Base に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央の場所でアカウントを管理します。

Skills Base は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- **シングル Sign-On モジュール**を含むライセンスを持つ Skills Base インスタンス。
- Skills Base Administrator アカウント (ローカル ログイン電子メール/パスワード付き)。
- **シングル サインオン** 機能が有効になっている ( **管理 &gt; モジュール &gt; シングル Sign-On モジュール**)。

### シナリオの説明

この記事では、Skills Base で Microsoft Entra のシングル サインオンを構成し、テストします。

- Skills Base では、 **SP** によって開始される SSO がサポートされます。
- Skills Base では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

Skills Base では、 **IdP** によって開始される SSO はサポートされていません。

### ギャラリーからの Skills Base の追加

Skills Base と Microsoft Entra ID の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Skills Base を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[新しいアプリケーション]** を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに「**Skills Base**」と入力します。
4. 結果パネルから **[Skills Base]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Skills Base 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Skills Base に対する Microsoft Entra SSO を構成してテストします。 **Just In Time** ユーザー プロビジョニングが有効になっていない場合、SSO を機能させるには、Microsoft Entra ユーザーと Skills Base の関連ユーザーとの間にリンク関係を確立する必要があります。

Skills Base に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Skills Base SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Skills Base テストユーザーの作成** - Skills Base で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Skills Base** Enterprise アプリケーションの概要ページを参照します。
3. [ **作業の開始** ] セクションで、[2] の [ **はじめに** ] を選択します **。シングル サインオンを設定します**。
4. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、ページの上部にある [ **メタデータ ファイルのアップロード** ] ボタンを選択します。
6. [ **ファイルの選択** ] アイコンを選択し、Skills Base からダウンロードしたメタデータ ファイルを選択します。
7. **追加**を選択する

    [Image: [UPLOAD SP metadata](SP メタデータのアップロード) を示すスクリーンショット。]
8. [ **基本的な SAML 構成]** ページの **[サインオン URL** ] テキスト ボックスに、スキル ベースのショートカット リンクを次の形式で入力します。 `https://app.skills-base.com/o/<customer-unique-key>`

    注

    Skills Base アプリケーションから [サインオン URL] を取得できます。 管理者としてログインし、[ **管理] &gt; [設定] &gt; [インスタンスの詳細] &gt; ショートカット リンク**に移動してください。 ショートカット リンクをコピーし、Microsoft Entra ID の **[サインオン URL** ] ボックスに貼り付けます。
9. **[保存] を選択する**
10. [ **基本的な SAML 構成]** ダイアログを閉じます。
11. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションの [ **フェデレーション メタデータ XML**] の横にある [ **ダウンロード** ] を選択してフェデレーション メタデータ XML をダウンロードし、コンピューターに保存します。

[Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### Skills Base SSO の構成

1. 管理者として Skills Base にログインします。
2. メニューの左側にある [ **管理 &gt; 認証**] を選択します。

    [Image: [認証] メニューを示すスクリーンショット。]
3. [**ID プロバイダー**] セクションの [**認証**] ページで、[**ID プロバイダーの追加**] を選択します。

    [Image: 「ID プロバイダーの追加」ボタンを示すスクリーンショット。]
4. 既定の設定を使用するには、[ **追加]** を選択します。

    [Image: スクリーンショットは、説明されている値を入力できる [認証] ページを示しています。]
5. [ **アプリケーションの詳細** ] パネルの **[SAML SP メタデータ**] の横にある [ **XML ファイルのダウンロード** ] を選択し、結果のファイルをコンピューターに保存します。

    [Image: スクリーンショットは、SP メタデータ ファイルをダウンロードできる [アプリケーションの詳細] パネルを示しています。]
6. [ **ID プロバイダー** ] セクションで、追加した ID プロバイダー レコードの **編集** ボタン (鉛筆アイコンで示されます) を選択します。

    [Image: [Edit Identity Providers](ID プロバイダーの編集) ボタンを示すスクリーンショット。]
7. **[ID プロバイダーの編集]** パネルで、[**SAML IdP メタデータ**] で [**XML ファイルのアップロード**] を選択します
8. **参照**を選択してファイルを選択します。 Microsoft Entra ID からダウンロードしたフェデレーション メタデータ XML ファイルを選択し、[ **保存]** を選択します。

    [Image: 証明書の種類をアップロードする画面のスクリーンショット。]
9. [ **認証** ] パネルの **[シングル サインオン] で** 、追加した ID プロバイダーを選択します。

    [Image: S S O の [認証] パネルのスクリーンショット。]
10. 現時点では、スキルベースのログイン画面をバイパスするオプションの **選択が解除** されていることを確認してください。 統合が機能することが確認されたら、後でこのオプションを有効にできます。
11. **Just-In-Time** ユーザー プロビジョニングを有効にする場合は、[**自動ユーザー アカウント プロビジョニング**] オプションを有効にします。
12. **[変更の保存]** を選択します。

[Image: Just-In-Time プロビジョニングのスクリーンショット。]

注

[ID プロバイダー] パネルで追加した **ID プロバイダー**の **[状態]** 列に緑色の **[有効]** バッジが表示されるようになりました。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Skills Base のテスト ユーザーの作成

Skills Base では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 そのため、この手順に必要なアクションはありません。 Skills Base にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

注

ユーザーを手動で作成する必要がある場合は、 [こちらの](https://support.skills-base.com/kb/articles/11000024831-adding-people-and-enabling-them-to-log-in)手順に従います。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Skills Base のサインオン URL にリダイレクトされます。
- [Skills Base Shortcut]\(スキル ベース ショートカット\) リンクを使用してそこからログイン フローを開始するか、または
- Microsoft マイ アプリを使用できます。 マイ アプリで [Skills Base]\(スキル ベース\) タイルを選択すると、このオプションは [Skills Base Shortcut]\(スキル ベース ショートカット\) リンクにリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

### トークン署名証明書の更新

しばらくすると (既定では 3 年)、Microsoft Entra で生成したトークン署名証明書の有効期限が切れます。 保留中の証明書の有効期限に関する事前通知が、"**アクションが必要: Microsoft Entra ID でアプリケーション証明書を更新**する" という件名の **Microsoft Security** から電子メールで受け取る場合があります。 この電子メールは、 **Microsoft Entra &gt; Enterprise アプリ &gt; Skills Base &gt; シングル サインオン &gt; SAM 証明書 &gt; トークン署名証明書 &gt; 通知電子メール**に記録された電子メール アドレスに送信されます。 電子メールには、証明書の有効期限が含まれます。 サービスの中断を回避するには、この日付より前に証明書を更新する必要があります。

#### 更新手順

1. **Microsoft Entra** で、**エンタープライズ アプリ &gt; Skills Base &gt; SAML 証明書&gt;シングル サインオンに**移動し、[**編集]** を選択します。
2. [ **新しい証明書**] を選択しますが、まだアクティブにしないでください。
3. **[フェデレーション メタデータ XML**] の横にある [**ダウンロード**] をクリックします。
4. **[スキルベース]** で、[**管理&gt;認証**] に移動します。
5. [ **ID プロバイダー**] で、ID プロバイダーを見つけて、[ **アクション]** 列の編集ボタン (鉛筆アイコンで示されます) を選択します。
6. **SAML IdP メタデータ**の場合は、[**XML ファイルのアップロード**] を選択します。
7. 上記の手順 3 でダウンロードしたフェデレーション メタデータ XML ファイルをアップロードします。 をクリックし、[ **保存]** を選択します。
8. **Microsoft Entra** で、**エンタープライズ アプリ &gt; Skills Base &gt; SAML 証明書&gt;シングル サインオンに**移動し、[**編集]** を選択します。
9. 作成した新しい証明書の横にある 3 つのドットを選択し、[ **証明書をアクティブにする** ] の後に **[はい**] を選択します。
10. **[SAML 証明書**] セクションの [有効期限] フィールドに新しい証明書の**有効期限**が表示されていることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skillsmanager-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Skills Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skillsmanager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Skills Manager の間でシングル サインオンを構成する方法についてご確認ください。

この記事では、Skills Manager と Microsoft Entra ID を統合する方法について説明します。 Skills Manager と Microsoft Entra ID を統合すると、次のことができます。

- Skills Manager にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで Skills Manager に自動的にサインイン (シングル サインオン) するように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Skills Manager でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Skills Manager では、**IDP** によって開始される SSO がサポートされます。

### ギャラリーから Skills Manager を追加する

Microsoft Entra ID への Skills Manager の統合を構成するには、ギャラリーから管理対象 SaaS アプリのリストに Skills Manager を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Skills Manager**」と入力します。
4. 結果のパネルから **[Skills Manager]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Skills Manager 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Skills Manager 用に Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Skills Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

Skills Manager に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Skills Manager の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Skills Manager テストユーザーの作成** - Skills Manager で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Skills Manager**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.skills-manager.com/kennametal`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.skills-manager.com/public/SamlLogin2.aspx`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 この値を取得するには、[Skills Manager クライアント サポート チーム](https://www.ibm.com/support/uk/?lnk=msu_uk)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Skills Manager のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Skills Manager の SSO を構成する

**Skills Manager** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーションの構成からコピーした適切な URL を [Skills Manager サポート チーム](https://www.ibm.com/support/uk/?lnk=msu_uk)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Skills Manager のテスト ユーザーの作成

このセクションでは、Skills Manager で Britta Simon というユーザーを作成します。 [Skills Manager サポート チーム](https://www.ibm.com/support/uk/?lnk=msu_uk)と連携し、Skills Manager プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Skills Manager に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Skills Manager] タイルを選択すると、SSO を設定した Skills Manager に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skopenow-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Skopenow を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skopenow-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Skopenow の間にシングル サインオンを構成する方法について説明します。

この記事では、Skopenow と Microsoft Entra ID を統合する方法について説明します。 Skopenow を Microsoft Entra ID と統合すると、次のことができます。

- Skopenow にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Skopenow に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な Skopenow のサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Skopenow では、**SP と IDP** initiated SSO をサポートします。
- Skopenow では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Skopenow を追加する

Microsoft Entra ID への Skopenow の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Skopenow を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Skopenow**」と入力します。
4. 結果パネルから **[Skopenow]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Skopenow 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Skopenow に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Skopenow の関連ユーザーとの間にリンク関係を確立する必要があります。

Skopenow に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Skopenow の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Skopenowでテストユーザーを作成する - SkopenowでB.Simonに対応するテストユーザーを作成し、それをMicrosoft Entraのユーザー表現にリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Skopenow**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、URL を入力します。 `https://app.skopenow.com/saml/module.php/saml/sp/metadata.php/microsoft`

    b。 [ **応答 URL** ] ボックスに、URL を入力します。 `https://app.skopenow.com/saml/module.php/saml/sp/saml2-acs.php/microsoft`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.skopenow.com/login/sso?account=microsoft`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. **[Set up Skopenow](Skopenow の設定)** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Skopenow の SSO を構成する

**Skopenow** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を、[Skopenow サポート チーム](mailto:support@skopenow.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Skopenow テスト ユーザーを作成する

このセクションでは、B. Simon というユーザーを Skopenow に作成します。 Skopenow では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Skopenow に存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Skopenow のサインオン URL にリダイレクトされます。
- Skopenow のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Skopenow に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Skopenow] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Skopenow に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skybreathe-analytics-tutorial"} -->
## Microsoft Entra ID で Skybreathe® Analytics for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skybreathe-analytics-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Skybreathe® Analytics の間でシングル サインオンを構成する方法について説明します。

この記事では、Skybreathe® Analytics と Microsoft Entra ID を統合する方法について説明します。 Skybreathe® Analytics を Microsoft Entra ID を統合すると、以下のことができます。

- Skybreathe® Analytics にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Skybreathe® Analytics に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Skybreathe® Analytics でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Skybreathe® Analytics では、**SP**開始SSOと**IDP**開始SSOがサポートされます。

### ギャラリーから Skybreathe® Analytics を追加する

Microsoft Entra ID への Skybreathe® Analytics の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Skybreathe® Analytics を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Skybreathe® Analytics**」と入力します。
4. 結果パネルから **Skybreathe® Analytics** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Skybreathe® Analytics 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Skybreathe® Analytics に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Skybreathe® Analytics の関連ユーザーとの間にリンク関係を確立する必要があります。

Skybreathe® Analytics に対する Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Skybreathe Analytics の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Skybreathe® Analytics のテスト ユーザーを作成する - B.Simon に対応するユーザーを Skybreathe® Analytics で作成し、それを Microsoft Entra のユーザーとリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Skybreathe® Analytics**&gt;**シングルサインオン**にアクセスしてください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    1. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.skybreathe.com/auth/realms/<ICAO>` `
    2. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.skybreathe.com/auth/realms/<ICAO>/broker/sbfe-<icao>-idp/endpoint/client/sso`
6. SP 開始モードでアプリケーションを構成する場合は、[ **追加の URL の設定] を** 選択し、次の手順を実行します。

    1. [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://auth.skybreathe.com/auth/realms/<ICAO>/broker/sbfe-<icao>-idp/endpoint`
    2. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<domain>.skybreathe.com/saml/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Skybreathe® Analytics クライアント サポート チーム](mailto:support@openairlines.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. Skybreathe® Analytics アプリケーションでは特定の形式の SAML アサーションが使用されるため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性マッピングの画像を示すスクリーンショット。]
8. 上記に加えて、Skybreathe® Analytics アプリケーションでは、SAML 応答でいくつかの属性が返されると想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | イニシャル | ユーザー.社員ID |
    | 名字 | ユーザーの名字 |
    | グループ | ユーザー.グループ |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Skybreathe® Analytics SSO の構成

**Skybreathe® Analytics** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Skybreathe® Analytics サポート チーム](mailto:support@openairlines.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Skybreathe Analytics テスト ユーザーの作成

このセクションでは、Skybreathe® Analytics で Britta Simon というユーザーを作成します。 [Skybreathe® Analytics サポート チーム](mailto:support@openairlines.com)と協力して、Skybreathe® Analytics プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Skybreathe® Analytics のサインオン URL にリダイレクトされます。
- Skybreathe® Analytics のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Skybreathe® Analytics に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Skybreathe® Analytics] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Skybreathe® Analytics に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skydeskemail-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SkyDesk Email を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skydeskemail-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SkyDesk 間にシングル サインオンを構成する方法について説明します。

この記事では、SkyDesk Email と Microsoft Entra ID を統合する方法について説明します。 SkyDesk Email を Microsoft Entra ID と統合すると、次のことができます:

- SkyDesk Email にアクセスできるユーザーをMicrosoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SumoLogic に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SkyDesk Email でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SkyDesk Email では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの SkyDesk Email の追加

Microsoft Entra ID への SkyDesk Email の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SkyDesk Email を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SkyDesk Email**」と入力します。
4. 結果ウィンドウで **[SkyDesk Email]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SkyDesk Email 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SkyDesk Email に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SkyDesk Email の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を SkyDesk Email と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SkyDesk Email の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SkyDesk Email のテストユーザーを作成します** - SkyDesk Email で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現とリンクさせるため。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**SkyDesk Email**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://mail.skydesk.jp/portal/<companyname>`
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SkyDesk Email のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SkyDesk Email の SSO を構成する

1. 別の Web ブラウザーから、管理者として SkyDesk Email アカウントにサインオンします。
2. 上部のメニューで 、[ **セットアップ**] を選択し、[組織] を選択 **します**。

    [Image: [設定] メニューから [組織] が選択された画面のスクリーンショット。]
3. 左側のパネルから **[ドメイン]** を選択します。

    [Image: コントロール パネルから [ドメイン] が選択された画面のスクリーンショット。]
4. [ **ドメインの追加] を選択します**。

    [Image: [ドメインの追加] が選択された画面のスクリーンショット。]
5. 自分のドメイン名を入力し、ドメインを確認します。

    [Image: [ドメインの追加] タブのスクリーンショット。ここでドメインを入力することができます。]
6. 左側のパネルから **[SAML 認証** ] を選択します。

    [Image: コントロール パネルから [SAML Authentication](SAML 認証) が選択された画面のスクリーンショット。]
7. **[SAML 認証]** ダイアログ ページで、次の手順に従います。

    [Image: [SAML Authentication Details](SAML 認証の詳細) ダイアログ ボックスのスクリーンショット。ここで、説明されている値を入力できます。]

    注

    SAML ベースの認証を使用するには、**ドメインが検証済み**または**ポータル URL** が設定済みである必要があります。 一意の名前でポータル URL を設定できます。

    [Image: 名前を入力する [Portal U R L](ポータル U R L) のスクリーンショット。]

    ある。 [ **ログイン URL** ] ボックスに、 **ログイン URL** の値を貼り付けます。

    b。 **[ログアウト URL]** テキストボックスに **[ログアウト URL]** の値を貼り付けます

    c. **[パスワード変更 URL]** は省略可能なので、空白のままにします。

    d. [ **ファイルからキーを取得** ] を選択して Azure portal からダウンロードした証明書を選択し、[ **開く** ] を選択して証明書をアップロードします。

    え **アルゴリズム**として、**RSA** を選択します。

    f. [ **OK] を** 選択して変更を保存します。

#### SkyDesk Email のテスト ユーザーの作成

このセクションでは、SkyDesk Email で Britta Simon というユーザーを作成します。

SkyDesk Emailの左側のパネルから **ユーザーアクセス** を選択し、ユーザー名を入力します。

[Image: コントロール パネルから [User Access](ユーザー アクセス) が選択された画面のスクリーンショット。]

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SkyDesk Email のサインオン URL にリダイレクトされます。
- SkyDesk Email のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SkyDesk Email] タイルを選択すると、このオプションは SkyDesk Email のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skyhighnetworks-tutorial"} -->
## Microsoft Entra ID とのシングルサインオンのための MVISION Cloud Microsoft Entra SSO 構成の設定方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skyhighnetworks-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と MVISION Cloud Microsoft Entra SSO Configuration の間にシングル サインオンを構成する方法について説明します。

この記事では、MVISION Cloud Microsoft Entra SSO Configuration と Microsoft Entra ID を統合する方法について説明します。 MVISION Cloud Microsoft Entra SSO Configuration を Microsoft Entra ID と統合すると、次のことができます。

- MVISION Cloud Microsoft Entra SSO Configuration にアクセスできるユーザーを Microsoft Entra ID 内で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して MVISION Cloud Microsoft Entra SSO Configuration に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- MVISION Cloud Microsoft Entra SSO Configuration でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- MVISION Cloud Microsoft Entra SSO Configuration では、**SP イニシエイト SSO** と **IDP イニシエイト SSO** の両方をサポートしています。

### ギャラリーから MVISION Cloud Microsoft Entra SSO Configuration を追加する

Microsoft Entra ID への MVISION Cloud Microsoft Entra SSO Configuration の統合を構成するには、ギャラリーから、お使いの管理対象 SaaS アプリの一覧に MVISION Cloud Microsoft Entra SSO Configuration を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新規アプリケーション**を参照します。
3. **[ギャラリーから追加する**] セクションで、検索ボックスに**「MVISION Cloud Microsoft Entra SSO Configuration」**と入力します。
4. 結果パネルから **MVISION Cloud Microsoft Entra SSO Configuration を** 選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### MVISION Cloud Microsoft Entra SSO Configuration 用に Microsoft Entra SSO を構成してテストする

**Britta Simon** というテスト ユーザーを使用して、MVISION Cloud Microsoft Entra SSO Configuration に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと MVISION Cloud Microsoft Entra SSO Configuration 内の関連ユーザーとの間にリンク関係を確立する必要があります。

MVISION Cloud Microsoft Entra SSO Configuration に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **MVISION Cloud Microsoft Entra SSO Configuration SSO の構成**- アプリケーション側でシングル Sign-On 設定を構成します。
    1. **MVISION Cloud Microsoft Entra SSO Configuration のテストユーザーを作成** - MVISION Cloud Microsoft Entra SSO Configuration で Britta Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Datadog**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENV>.myshn.net/shndash/saml/Azure_SSO`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENV>.myshn.net/shndash/response/saml-postlogin`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ENV>.myshn.net/shndash/saml/Azure_SSO`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [MVISION Cloud Microsoft Entra SSO 構成クライアント サポート チーム](mailto:support@skyhighnetworks.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **MVISION Cloud Microsoft Entra SSO Configuration のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### MVISION Cloud Microsoft Entra SSO Configuration の SSO を構成する

**MVISION Cloud Microsoft Entra SSO Configuration** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [MVISION Cloud Microsoft Entra SSO 構成サポート チーム](mailto:support@skyhighnetworks.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### MVISION Cloud Microsoft Entra SSO Configuration テスト ユーザーを作成する

このセクションでは、MVISION Cloud Microsoft Entra SSO Configuration 内で B.Simon というユーザーを作成します。 [MVISION Cloud Microsoft Entra SSO Configuration サポート チーム](mailto:support@skyhighnetworks.com)と協力して、MVISION Cloud Microsoft Entra SSO Configuration プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる MVISION Cloud Microsoft Entra SSO 構成サインオン URL にリダイレクトされます。
- MVISION Cloud Microsoft Entra SSO Configuration のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した MVISION Cloud Microsoft Entra SSO Configuration に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで MVISION Cloud Microsoft Entra SSO Configuration タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した MVISION Cloud Microsoft Entra SSO Configuration に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skysite-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SKYSITE を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skysite-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SKYSITE の間でシングル サインオンを構成する方法について説明します。

この記事では、SKYSITE と Microsoft Entra ID を統合する方法について説明します。 SKYSITE を Microsoft Entra ID と統合すると、次のことができるようになります。

- SKYSITE にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SKYSITE に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SKYSITE でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SKYSITE では、 **IDP** Initiated SSO がサポートされます。
- SKYSITE では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから SKYSITE を追加する

Microsoft Entra ID への SKYSITE の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SKYSITE を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「SKYSITE**」と入力します。
4. 結果パネルから **SKYSITE** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SKYSITE 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SKYSITE に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SKYSITE の関連ユーザーとの間にリンク関係を確立する必要があります。

SKYSITE に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SKYSITE SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SKYSITE のテストユーザーを作成** - Microsoft Entra で表現されている B.Simon にリンクする、SKYSITE 内の B.Simon に対応するユーザーを用意します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SKYSITE** アプリケーション統合ページを参照し、[**プロパティ] タブ**を選択し、次の手順を実行します。

    [Image: シングル サインオンのプロパティを示すスクリーンショット。]

    - **ユーザー アクセス URL を**コピーし、「**SKYSITE SSO の構成」セクション**に貼り付ける必要があります。これについては、この記事の後半で説明します。
3. **SKYSITE** アプリケーション統合ページで、**シングル サインオン**に移動します。
4. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
5. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
6. [ **基本的な SAML 構成]** セクションでは、アプリケーションは **IDP** 開始モードで事前に構成されており、必要な URL は既に Azure に事前設定されています。 ユーザーは、[保存] ボタンを選択して構成を **保存** する必要があります。
7. SKYSITE アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。 [ **編集]** アイコンを選択して、[ユーザー属性] ダイアログを開きます。

    [Image: [編集] アイコンが選択されているユーザー属性を示すスクリーンショット。]
8. その他に、SKYSITE アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**グループ要求 (プレビュー)]** ダイアログの [**ユーザー属性と要求**] セクションで、次の手順を実行します。

    ある。 **要求で返されるグループ**の横にある**ペン**を選択します。

    [Image: [新しい要求の追加] オプションを含むユーザー要求を示すスクリーンショット。]

    b。 ラジオの一覧から **[すべてのグループ** ] を選択します。

    c. **ソース属性**を**グループ ID**の中から選択します。

    d. **[保存] を選択します**。
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. [ **SKYSITE のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SKYSITE の SSO の構成

1. 新しい Web ブラウザー ウィンドウを開き、SKYSITE 企業サイトに管理者としてサインインして、次の手順を実行します。
2. ページの右上にある **[設定]** を選択し、[ **アカウント設定]** に移動します。

    [Image: [設定] で選択されているアカウント設定を示すスクリーンショット。]
3. [ **シングル サインオン (SSO)] タブに** 切り替えて、次の手順に従います。

    [Image: 説明されている値を入力できる [シングル サインオン] タブを示すスクリーンショット。]

    ある。 **ID プロバイダーのサインイン URL** テキスト ボックスに、Azure portal の **[プロパティ**] タブからコピーした**ユーザー アクセス URL** の値を貼り付けます。

    b。 [ **証明書のアップロード]** を選択して、ダウンロードした Base64 でエンコードされた証明書をアップロードします。

    c. **[保存] を選択します**。

#### SKYSITE のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを SKYSITE に作成します。 SKYSITE では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 SKYSITE にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SKYSITE に自動的にサインインします。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [SKYSITE] タイルを選択すると、SSO を設定した SKYSITE に自動的にサインインします。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skytap-tutorial"} -->
## Microsoft Entra ID で Skytap のシングル サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skytap-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Single Sign-on for Skytap の間のシングル サインオンを構成する方法について説明します。

この記事では、Skytap のシングル サインオンと Microsoft Entra ID を統合する方法について説明します。 Single Sign-on for Skytap と Microsoft Entra ID を統合すると、次のことができます。

- Single Sign-on for Skytap にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Single Sign-on for Skytap に自動的にサインインできるようにする。
- 1 つの中央の場所 (Azure portal) でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Single Sign-on for Skytap でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Single Sign-on for Skytap では、SP Initiated SSO と IDP Initiated SSO がサポートされます。

### ギャラリーからの Single Sign-on for Skytap の追加

Microsoft Entra ID への Single Sign-on for Skytap の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Single Sign-on for Skytap を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Single Sign-on for Skytap**」と入力します。
4. 結果のパネルから **[Single Sign-on for Skytap]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Single Sign-on for Skytap 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Single Sign-on for Skytap に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Single Sign-on for Skytap の関連ユーザーとの間にリンク関係を確立します。

Single Sign-on for Skytap で Microsoft Entra SSO を構成してテストする一般的な手順を次に示します。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成**し、B.Simon を使用して Microsoft Entra シングル サインオンをテストします。
    2. **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **Single Sign-on for Skytap SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Single Sign-on for Skytap のテスト ユーザーの作成** - Single Sign-on for Skytap で B.Simon に対応するユーザーを作成します。 このユーザーを Microsoft Entra の対応するユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Skytap のシングル サインオン**&gt;**シングル サインオン** にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを **IDP** 開始モードで構成する場合は、次のフィールドの値を入力します。

    アルファベットの「a」。 [ **識別子** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `http://pingone.com/<custom EntityID>`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://sso.connect.pingidentity.com/sso/sp/ACS.saml2`
6. 必要に応じて、 **[追加の URL を設定します]** を選択し、次の手順を実行することにより、**SP** Initiated モードでアプリケーションを構成することができます。

    アルファベットの「a」。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用する URL を入力します。 `https://sso.connect.pingidentity.com/sso/sp/initsso?saasid=<saasid>&idpid=<idpid>`

    b。 **[リレー状態]** ボックスに、次のパターンを使用する URL を入力します: `https://pingone.com/1.0/<custom ID>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、リレー状態でこれらの値を更新します。 これらの値を取得するには、[Single Sign-on for Skytap クライアント サポート チーム](mailto:support@skytap.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **フェデレーション メタデータ XML**] を見つけます。 **[ダウンロード]** を選択してメタデータ ファイルをダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンクのスクリーンショット]
8. **[Single Sign-on for Skytap のセットアップ]** セクションで、要件に基づいて適切な 1 つまたは複数の URL をコピーします。

    [Image: 構成 URL のコピーのスクリーンショット]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Single Sign-on for Skytap SSO の構成

Single Sign-on for Skytap 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、コピーした適切な URL を、[Single Sign-on for Skytap サポート チーム](mailto:support@skytap.com)に送信する必要があります。 この設定が構成され、SAML SSO 接続が両側で正しく行われます。

#### Single Sign-on for Skytap のテスト ユーザーの作成

このセクションでは、Single Sign-on for Skytap で B.Simon というユーザーを作成します。 [Single Sign-on for Skytap クライアント サポート チーム](mailto:support@skytap.com)と連携して、Single Sign-on for Skytap プラットフォームにユーザーを追加してください。 ユーザーを作成してアクティブ化するまでは、シングル サインオンを使用できません。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Skytap サインオン URL のシングル サインオンにリダイレクトされます。
- Single Sign-on for Skytap のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Skytap のシングル サインオンに自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Skytap のシングル サインオン] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Skytap のシングル サインオンに自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/skyward-qmlativ-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Skyward Qmlativ を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/skyward-qmlativ-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Skyward Qmlativ の間でシングル サインオンを構成する方法について説明します。

この記事では、Skyward Qmlativ と Microsoft Entra ID を統合する方法について説明します。 Skyward Qmlativ と Microsoft Entra ID を統合すると、次のことができます。

- Skyward Qmlativ へのアクセス権を持つユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Skyward Qmlativ に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Skyward Qmlativ でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Skyward Qmlativ では、SP **が開始する** エスエスオーをサポートします。

### ギャラリーから Skyward Qmlativ を追加する

Microsoft Entra ID への Skyward Qmlativ の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Skyward Qmlativ を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックスに「Skyward Qmlativ 」を入力します。
4. 結果パネルから**Skyward Qmlativ**を選択して、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Skyward Qmlativ の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Skyward Qmlativ に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Skyward Qmlativ の関連ユーザーとの間にリンク関係を確立する必要があります。

Skyward Qmlativ に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Skyward Qmlativ SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Skyward Qmlativ テストユーザーを作成して**、B.Simon の Skyward Qmlativ での対応ユーザーをセットアップし、Microsoft Entra のユーザー表現とリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. 以下にブラウズして移動します: **Entra ID**&gt;**Enterprise apps**&gt;**Skyward Qmlativ**&gt;**シングルサインオン**.
3. [**シングル サインオン方法の選択]** ページで、[SAML 選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    ある。 [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://<SUBDOMAIN>.skyward.com/<CUSTOMERIDENTIFIERSTS>`

    b。 [**識別子 (エンティティ ID)** テキスト ボックスに、次のパターンを使用して URL を入力します:`https://<BASEURL>/customeridentifierSTS`

    手記

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Skyward Qmlativ クライアント サポート チーム](mailto:steveb@skyward.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Skyward Qmlativ SSO の構成

Skyward Qmlativ **側** のシングル サインオンを構成するには、Skyward Qmlativ サポートチーム [に **アプリフェデレーションメタデータURL** を](mailto:steveb@skyward.com)送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Skyward Qmlativ テスト ユーザーの作成

このセクションでは、Skyward Qmlativ で Britta Simon というユーザーを作成します。 skyward Qmlativ サポート チーム  と協力して、Skyward Qmlativ プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる Skyward Qmlativ のサインオン URL にリダイレクトされます。
- Skyward Qmlativ のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Skyward Qmlativ] タイルを選択すると、このオプションは Skyward Qmlativ のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/slack-provisioning-tutorial"} -->
## Microsoft Entra ID を使用して Slack へのユーザー プロビジョニングを自動化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/slack-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: ユーザー アカウントを Slack に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成する方法について説明します。

注

Slack とカスタム/BYOA アプリケーションの統合はサポートされていません。 この記事の説明に従ってギャラリー アプリケーションを使用することがサポートされています。 ギャラリー アプリケーションは、Slack の SCIM v1 サーバーと連携するようにカスタマイズされています。

この記事の目的は、ユーザー アカウントを Microsoft Entra ID から Slack に自動的にプロビジョニングおよびプロビジョニング解除するために Slack と Microsoft Entra ID で実行する必要がある手順について説明することです。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされる機能

- Slack でユーザーを作成する
- アクセスが不要になった場合に Slack でユーザーを削除する
- Microsoft Entra ID と Slack の間でユーザー属性の同期を維持する
- Slack でグループとグループ メンバーシップをプロビジョニングする
- Slack への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/slack-tutorial) (推奨)

Slack は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の項目が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Business+ プラン](https://slack.com/pricing)のみを含む Slack テナント。 Enteprise Grid のお客様は、代わりに [Slack の指示](https://api.slack.com/admins/scim#enterprise-grid) に従う必要があります。
- Team Admin アクセス許可がある Slack のユーザー アカウント。

### 手順 1: プロビジョニングの展開を計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Slack の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra アプリケーション ギャラリーから Slack を追加する

Microsoft Entra アプリケーション ギャラリーから Slack を追加して、Slack へのプロビジョニングの管理を開始します。 SSO のために以前 Slack を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリーからアプリケーションを追加する方法の詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 3: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小さいところから始めましょう。 全員にロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当て済みユーザーとグループに設定される場合、これを制御するには、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てます。 スコープがすべてのユーザーとグループに設定されている場合は、[属性ベースのスコープ フィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 4: Slack への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID を Slack のユーザー アカウント プロビジョニング API に接続する手順と、Microsoft Entra ID のユーザーとグループの割り当てに基づいて、割り当て済みのユーザー アカウントを Slack で作成、更新、無効化するようにプロビジョニング サービスを構成する手順を説明します。

#### Microsoft Entra ID で Slack への自動ユーザー アカウント プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[Slack]** を選択します。

    [Image: アプリケーションの一覧の Slack リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Slack テナント URL とシークレット トークンを入力します。 **Test Connection** を選択して、Microsoft Entra IDが Slack に接続できることを確認します。 接続に失敗した場合は、Slack アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. 新しいウィンドウで、Team Admin アカウントを使用して Slack にサインインします。 結果の承認ダイアログで、プロビジョニングを有効にする Slack チームを選択し、[ **承認**] を選択します。 完了したら、Microsoft Entra 管理センターに戻り、プロビジョニング構成を完了します。

    [Image: 承認ダイアログ]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **Attribute-Mapping** セクションで、Microsoft Entra IDから Slack に同期されるユーザー属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Slack のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Slack API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | 活動中 | ブール値 |
    | externalId | 糸 |
    | displayName | 糸 |
    | name.familyName | 糸 |
    | name.givenName | 糸 |
    | タイトル | 糸 |
    | emails[type eq "work"].value | 糸 |
    | ユーザー名 | 糸 |
    | ニックネーム | 糸 |
    | addresses[type eq "untyped"].streetAddress | 糸 |
    | addresses[type eq "untyped"].locality | 糸 |
    | アドレス[タイプ eq "無指定"].リージョン | 糸 |
    | addresses[type eq "untyped"].postalCode | 糸 |
    | addresses[type eq "untyped"].country | 糸 |
    | phoneNumbers[type eq "mobile"].value | 糸 |
    | phoneNumbers[type eq "work"].value | 糸 |
    | roles[primary eq "True"].value | 糸 |
    | ロケール | 糸 |
    | name.honorificPrefix | 糸 |
    | photos[type eq "photo"].value | 糸 |
    | プロフィールURL | 糸 |
    | タイムゾーン | 糸 |
    | ユーザータイプ | 糸 |
    | 優先言語 | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.department | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.manager | リファレンス |
    | urn:scim:schemas:extension:enterprise:1.0.employeeNumber | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.costCenter | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.organization | 糸 |
    | urn:scim:schemas:extension:enterprise:1.0.division | 糸 |
13. [ **属性マッピング** ] セクションで、Microsoft Entra ID から Slack に同期されるグループ属性を確認します。 **[照合**プロパティ] として選択されている属性は、更新操作で Slack のグループとの照合に使用されます。 [保存] ボタンをクリックして変更をコミットします。

    | 属性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | members | リファレンス |
14. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
15. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、組織内でより広範に展開する前に、少数のユーザーとの同期を検証します。
16. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 5: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### トラブルシューティングのヒント

- Slack の **displayName**属性を構成する際、次の動作に注意してください。

    - 値は完全に一意ではありません (たとえば、2 人のユーザーが同じ表示名を持つことができます)。
    - 非英語の文字、スペース、大文字と小文字をサポートしています。
    - 許可されている句読点はピリオド、アンダースコア、ハイフン、アポストロフィ、かっこ (例: `( [ { } ] )`) と区切り記号 (例: `, / ;`) です。
    - displayName プロパティに '@' 文字を指定することはできません。 '@' が含まれている場合、プロビジョニング ログに "AttributeValidationFailed" という説明が含まれるスキップされたイベントが見つかる場合があります。
    - Slack の職場/組織でこれら 2 つの設定が構成されている場合にのみ更新されます。 **プロファイルの同期が有効になっており** 、 **ユーザーは表示名を変更できません**。
- Slack の **userName** 属性は 21 文字未満で、一意の値を持つ必要があります。
- Slack では、属性 **userName** と **email** との照合のみが許可されます。
- 一般的なエラー コードについては、Slack の公式ドキュメント (https://api.slack.com/scim#errors ) を参照してください

### 変更履歴

- 2020 年 6 月 16 日 - 新しいユーザーの作成時にのみ更新されるように DisplayName 属性が変更されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/slack-tutorial"} -->
## Microsoft Entra ID で Slack for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/slack-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Slack 間にシングル サインオンを構成する方法について学習します。

この記事では、Slack と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と Slack を統合すると、次のことができます。

- Slack にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Slack に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

Slack は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Slack でのシングル サインオン (SSO) が有効なサブスクリプション。

注

1 つのテナント内の複数の Slack インスタンスと統合する必要がある場合は、各アプリケーションの識別子を変数にすることができます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Slack では、 **SP (サービス プロバイダー) によって** 開始される SSO がサポートされます。
- Slack では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。
- Slack では、 [**自動** ユーザー プロビジョニングがサポートされています](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/slack-provisioning-tutorial)。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Slack を追加する

Microsoft Entra ID への Slack の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Slack を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Slack**」と入力します。
4. 結果パネルから **Slack** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。

### Slack 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Slack に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Slack の関連ユーザーとの間にリンク関係を確立する必要があります。

Slack に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Slack SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Slack テストユーザーの作成** - Slack で B.Simon に対応するユーザーを作成し、そのユーザーを Microsoft Entra における B.Simon の表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Slack**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    a. [ **識別子 (エンティティ ID)]** テキスト ボックスに、URL を入力します。 `https://slack.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | 返信 URL |
    | --- |
    | `https://<DOMAIN NAME>.slack.com/sso/saml` |
    | `https://<DOMAIN NAME>.enterprise.slack.com/sso/saml` |

    c. [ **サインオン URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | サインオンURL |
    | --- |
    | `https://<DOMAIN>.slack.com` |
    | `https://<DOMAIN>.enterprise.slack.com` |

    注

    これらは実際の値ではありません。 実際のサインオン URL および応答 URL でこれらの値を更新する必要があります。 この値を取得するには、 [Slack サポート チーム](https://slack.com/help/contact) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。

    注

    テナントと統合する必要がある Slack インスタンスが複数ある場合は **、識別子 (エンティティ ID)** の値を変数にすることができます。 `https://<DOMAIN NAME>.slack.com` というパターンを使用します。 このシナリオでは、同じ値を使用して、Slack の別の設定と組み合わせる必要もあります。
6. Slack アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットには、既定の属性一覧が示されています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. その他に、Slack アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も値が事前に設定されますが、要件に従ってそれらの値を確認することができます。

    [Image: [必須の要求] のスクリーンショット。]
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
9. [ **Slack のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Slack の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Slack 企業サイトに管理者としてサインインします
2. 左上のワークスペース名を選択し、[**設定] & [管理]**&gt;**[ワークスペースの設定]** に移動します。

    [Image: Microsoft Entra ID のシングル サインオンの構成のスクリーンショット。]
3. [ **設定とアクセス許可]** セクションで、[ **認証** ] タブを選択し、SAML 認証方法で **[構成** ] ボタンを選択します。

    [Image: [チーム設定でのシングル サインオンの構成] のスクリーンショット。]
4. [ **Azure の SAML 認証の構成** ] ダイアログで、次の手順を実行します。

    a. 右上で、[ **テスト** モード] をオンに切り替えます。

    b。 **[SAML SSO URL**] ボックスに、**ログイン URL** の値を貼り付けます。

    c. **ID プロバイダーの発行者**テキスト ボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    d. ダウンロードした証明書ファイルをメモ帳で開き、その内容をクリップボードにコピーして、[ **パブリック証明書** ] ボックスに貼り付けます。
5. **[詳細設定] オプション**を展開し、次の手順を実行します。

    [Image: [Configure Advanced options](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/高度なオプションの構成) のシングル サインオン (App Side) のスクリーンショット。]

    a. エンドツーエンドの暗号化キーが必要な場合は、[ **Sign AuthnRequest]\(AuthnRequest に署名** する\) チェック ボックスをオンにして証明書を表示します。

    b。 **Service provider issuer** テキスト ボックスに `https://slack.com` と入力します。

    c. 2 つのオプションから IDP からの SAML 応答の署名方法を選択します。

    注

    サービス プロバイダー (SP) の構成を設定するには、[SAML 構成] ページの [**詳細オプション]** の横にある **[展開**] を選択する必要があります。 [ **サービス プロバイダー発行者** ] ボックスに、ワークスペースの URL を入力します。 既定値は slack.com です。

    注

    **AuthnContextClassRef** を [**この値を送信しない**] に設定すると、"Error - AADSTS75011 Authentication method by which the user authenticated with the service doesn't match requested authentication method AuthnContextClassRef" (エラー - サービスで認証されたユーザーが要求された認証方法 AuthnContextClassRef と一致しない認証方法) というエラー メッセージが解決されます。
6. [ **設定]** で、SSO を有効にした後で、メンバーがプロファイル情報 (メールや表示名など) を編集できるかどうかを決定します。 SSO が必要か、部分的に必要か、オプションにするかを選択することもできます。

    [Image: アプリ側でシングルサインオンの設定を行う「Configure Save」設定のスクリーンショット。]
7. [ **構成の保存] を選択します**。

    注

    Microsoft Entra ID と統合する必要がある Slack インスタンスが複数ある場合は、`https://<DOMAIN NAME>.slack.com`**をサービス プロバイダー発行者**に設定して、Azure アプリケーション**識別子**の設定とペアリングできるようにします。

#### Slack のテスト ユーザーの作成

このセクションの目的は、Slack で B.Simon というユーザーを作成することです。 Slack では、Just-In-Time プロビジョニングがサポートされています。この設定は、既定で有効になっています。 このセクションにはアクション項目はありません。 存在しない Slack ユーザーにアクセスしようとすると、新しいユーザーが自動的に作成されます。 Slack では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/slack-provisioning-tutorial) ください。

注

ユーザーを手動で作成する必要がある場合は、 [Slack サポート チーム](https://slack.com/help/contact)にお問い合わせください。

注

Microsoft Entra Connect はオンプレミスの Active Directory の ID を Microsoft Entra ID と同期できる同期ツールであり、これらの同期済みユーザーもアプリケーションを他のクラウド ユーザーと同じように使用できます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Slack のサインオン URL にリダイレクトされます。
- Slack のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Slack] タイルを選択すると、このオプションは Slack のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smallimprovements-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Small Improvements を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smallimprovements-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-24
- Summary: Microsoft Entra ID と Small Improvements の間にシングル サインオンを構成する方法について説明します。

この記事では、Small Improvements と Microsoft Entra ID を統合する方法について説明します。 Small Improvements を Microsoft Entra ID と統合すると、次のことができます。

- Small Improvements にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Small Improvements に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Small Improvements でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Small Improvements では、 **SP** Initiated SSO がサポートされます。

### ギャラリーから Small Improvements を追加する

Microsoft Entra ID への Small Improvements の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Small Improvements を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Small Improvements**」と入力します。
4. 結果パネルから **[Small Improvements** ] を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Small Improvements 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Small Improvements に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Small Improvements の関連ユーザーの間にリンク関係を確立する必要があります。

Small Improvements に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Small Improvements の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Small Improvements テストユーザーの作成** - Small Improvements 内で B.Simon に対応するユーザーを作成し、それを Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Small Improvements**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.small-improvements.com`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<subdomain>.small-improvements.com`

    注意

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには [、Small Improvements クライアント サポート チーム](mailto:support@small-improvements.com) にお問い合わせください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Small Improvements のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Small Improvements の SSO を構成する

1. 別のブラウザー ウィンドウで、 [Small Improvements](https://small-improvements.com) 企業サイトに管理者としてサインオンします。
2. ダッシュボードのメイン ページで、左側の **[Admin**&gt;**Integrations** ] を選択します。

    [Image: [統合] ボタンが選択されているスクリーンショット。]
3. [**Integrations**] セクションから **[SAML SSO**] ボタンを選択します。

    [Image: [統合] で選択されている SAML S S O アイコンを示すスクリーンショット。]
4. [SSO Setup] ページで、次の手順に従います。

    [Image: スクリーンショットは、説明されている値を入力できる [S S O セットアップ] ページを示しています。]

    ある。 **SAML for SSO を有効にする**を選択します。

    b。 [ **アプリケーション発行者 URL** ] テキスト ボックスに、Small Improvements サブドメインを次の形式で入力します。 `https://<yourcompany>.small-improvements.com`

    c. **[HTTP エンドポイント]** ボックスに、**ログイン URL** の値を貼り付けます。

    d. ダウンロードした証明書をメモ帳で開き、内容をコピーして、 **x509 [証明書** ] ボックスに貼り付けます。

    え ユーザーが SSO とログイン フォーム認証オプションを使用できるようにする場合は、[ **ログイン/パスワードによるアクセスを有効にする] オプションもオンにします** 。

    f. **SAML プロンプト** テキストボックスで、SSO ログインボタンに名前を付けるために適切な値を入力してください。

    ジー **[保存] を選択します**。

#### Small Improvements のテスト ユーザーの作成

Microsoft Entra ユーザーが Small Improvements にログインできるようにするには、そのユーザーを Small Improvements にプロビジョニングする必要があります。 Small Improvements の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. 管理者として Small Improvements 企業サイトにサインオンします。
2. [ホーム] ページで、左側のメニューに移動し、[ **Admin**&gt;**Settings**] を選択します。

    [Image: [設定] ボタンが選択されているスクリーンショット。]
3. [ユーザー管理] セクションの [ **ユーザーの追加]** ボタンを選択します。

    [Image: [管理の概要] で [ユーザーの追加] が選択されているスクリーンショット。]
4. [ **ユーザーの追加** ] ダイアログで、次の手順を実行します。

    [Image: スクリーンショットは、説明されている値を入力できる [ユーザーの追加] ダイアログ ボックスを示しています。]

    ある。 **Britta** などのユーザーの**名**を入力します。

    b。 ユーザーの **姓** (Simon など) を入力 **します**。

    c.  のように、ユーザーの **brittasimon@contoso.com** を入力します。

    d. [ **通知メールの送信** ] ボックスに個人用メッセージを入力することもできます。 通知を送信しない場合は、このチェック ボックスをオフにします。

    え [ **ユーザーの作成] を選択します**。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Small Improvements のサインオン URL にリダイレクトされます。
- Small Improvements のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Small Improvements] タイルを選択すると、このオプションは Small Improvements のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smallstep-ssh-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Smallstep SSH を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smallstep-ssh-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-16
- Summary: Microsoft Entra ID から Smallstep SSH に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、Smallstep SSH と Microsoft Entra ID の両方で実行して、自動ユーザー プロビジョニングを構成するために必要な手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[Smallstep SSH](https://smallstep.com) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Smallstep SSH でユーザーを作成する
- アクセスが不要になった場合に Smallstep SSH のユーザーを削除する
- Microsoft Entra ID と Smallstep SSH の間でユーザー属性の同期を維持します。
- Smallstep SSH でグループとグループ メンバーシップをプロビジョニングする
- Smallstep SSH へのシングル サインオン (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Smallstep SSH](https://smallstep.com/sso-ssh/) アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. [Microsoft Entra ID と Smallstep SSH の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Smallstep SSH を構成する

1. [Smallstep SSH](https://smallstep.com/sso-ssh/) アカウントにサインインします。
2. **[Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー)** タブに移動し、ID プロバイダーとして **Microsoft Entra ID** を選択します。
3. 次のページで、**Microsoft Entra テナント ID** と許可リストを指定して OIDC を構成します。
4. [SCIM Details](SCIM の詳細) で、SCIM の**テナント URL** と**シークレット トークン**をコピーして保存します。 これらの値は、Smallstep SSH アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** ] フィールドに入力されます。

Note

Active Directory グループを介して Smallstep マネージド ホストへのアクセス権を付与する必要があります。 たとえば、SSH ユーザー用のグループと sudo ユーザー用のグループがあるとします。 アクセス制御の詳細については、「[Microsoft Entra Quickstart](https://smallstep.com/docs/ssh/azure-ad)」と「[Host Quickstart Guide](https://smallstep.com/docs/ssh/hosts)」を参照してください。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Smallstep SSH を追加する

Microsoft Entra アプリケーション ギャラリーから Smallstep SSH を追加して、Smallstep SSH へのプロビジョニングの管理を開始します。 SSO のために以前 Smallstep SSH を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Smallstep SSH への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Smallstep SSH でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Smallstep SSH の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[Smallstep SSH]** を選択します。

    [Image: アプリケーションの一覧の Smallstep SSH リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: アプリケーション管理メニューの [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Smallstep SSH テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Smallstep SSH に接続できることを確認します。 接続に失敗した場合は、Smallstep SSH アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Smallstep SSH に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Smallstep SSH のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Smallstep SSH API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Smallstep SSH に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Smallstep SSH のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | displayName | 糸 | ✓ |
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smart-global-governance-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用にスマート グローバル ガバナンスを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smart-global-governance-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Smart Global Governance の間にシングル サインオンを構成する方法について説明します。

この記事では、スマート グローバル ガバナンスと Microsoft Entra ID を統合する方法について説明します。 Smart Global Governance を Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra ID を使用して、Smart Global Governance にアクセスできるユーザーを制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Smart Global Governance に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「[Microsoft Entra ID でのアプリケーションへのシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)」を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- シングル サインオン (SSO) が有効な Smart Global Governance サブスクリプション。

### 記事の説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

Smart Global Governance では、SP Initiated SSO と IDP Initiated SSO がサポートされます

Smart Global Governance を構成したら、組織の機密データを流出と侵入からリアルタイムで保護するセッション制御を適用することができます。 セッション制御は、条件付きアクセスから拡張されます。 [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/cloud-app-security/proxy-deployment-any-app)でセッション制御を適用する方法について説明します。

### ギャラリーからの Smart Global Governance の追加

Microsoft Entra ID への Smart Global Governance の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Smart Global Governance を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Smart Global Governance**」と入力します。
4. 結果のパネルから **[Smart Global Governance]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Smart Global Governance 用に Microsoft Entra SSO を構成してテストする

B.Simon というテスト ユーザーを使用して、Smart Global Governance に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるためには、Microsoft Entra ユーザーと Smart Global Governance 内の対応するユーザーとの間にリンク関係を確立する必要があります。

スマート グローバル ガバナンスに対する Microsoft Entra SSO を構成してテストするには、次の大まかな手順を実行します。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成** して、Microsoft Entra のシングル サインオンをテストします。
    2. **テスト ユーザーにアクセス権を付与**して、そのユーザーが Microsoft Entra シングル サインオンを使用できるようにします。
2. アプリケーション側で **Smart Global Governance の SSO を構成**します。
    1. Microsoft Entra のユーザーに対応するユーザーとして、**Smart Global Governance のテスト ユーザーを作成**します。
3. **SSO をテスト** して、構成が機能することを確認します。

### Microsoft Entra SSO の構成

Azure portal で、次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. [**管理**] セクションで、[&gt;&gt;**Smart Global Governance** アプリケーション統合ページを参照し、**シングル サインオン**を選択します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆ボタンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の鉛筆ボタン]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを IDP 開始モードで構成する場合は、次の手順を実行します。

    ある。 **[識別子]** ボックスに、これらの URL のいずれかを入力します。

    - `https://eu-fr-south.console.smartglobalprivacy.com/platform/authentication-saml2/metadata`
    - `https://eu-fr-south.console.smartglobalprivacy.com/dpo/authentication-saml2/metadata`

    b。 **[応答 URL]** ボックスに、これらの URL のいずれかを入力します。

    - `https://eu-fr-south.console.smartglobalprivacy.com/platform/authentication-saml2/acs`
    - `https://eu-fr-south.console.smartglobalprivacy.com/dpo/authentication-saml2/acs`
6. アプリケーションを SP 開始モードで構成する場合は、 **[追加の URL を設定します]** を選択して次の手順に従います。

    - **[サインオン URL]** ボックスに、これらの URL のいずれかを入力します。
    - `https://eu-fr-south.console.smartglobalprivacy.com/dpo`
    - `https://eu-fr-south.console.smartglobalprivacy.com/platform`
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (未加工)]** の **[ダウンロード]** リンクを選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[Smart Global Governance の設定]** セクションで、要件に基づいて適切な URL をコピーします。

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

#### テスト ユーザーへのアクセス権の付与

このセクションでは、B.Simon に Smart Global Governance へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。
2. アプリケーションの一覧で **[Smart Global Governance]** を選択します。
3. アプリの概要ページの **[管理]** セクションで、 **[ユーザーとグループ]** を選択します。

    [Image: ユーザーとグループの選択]
4. [**ユーザーの追加]** を選択し、[**割り当ての追加**] ダイアログ ボックスで [**ユーザーとグループ**] を選択します。

    [Image: [ユーザーの追加] を選択する]
5. [**ユーザーとグループ**] ダイアログ ボックスで、[**ユーザー**] の一覧で **[B.Simon**] を選択し、画面の下部にある **[選択**] ボタンを選択します。
6. SAML アサーションにロール値が必要な場合は、[ **ロールの選択** ] ダイアログ ボックスで、一覧からユーザーに適したロールを選択し、画面の下部にある **[選択** ] ボタンを選択します。
7. **[割り当ての追加]** ダイアログ ボックスで **[割り当て]** を選びます。

### Smart Global Governance の SSO の構成

Smart Global Governance 側でシングル サインオンを構成するには、ダウンロードした未加工の証明書と Azure portal からコピーした適切な URL を [Smart Global Governance サポート チーム](mailto:support.tech@smartglobal.com)に送信する必要があります。 彼らは、SAML SSO 接続を両側で正しく構成します。

#### Smart Global Governance のテスト ユーザーの作成

[Smart Global Governance サポート チーム](mailto:support.tech@smartglobal.com)と連携して、B.Simon という名前のユーザーを Smart Global Governance に追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra SSO の構成をテストします。

アクセス パネルで [Smart Global Governance] タイルを選択すると、SSO を設定した Smart Global Governance インスタンスに自動的にサインインします。 アクセス パネルの詳細については、[アクセス パネルの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関する記事を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smart-map-pro-tutorial"} -->
## Microsoft Entra ID で Smart Map Pro シングルサインオンを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smart-map-pro-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Smart Map Pro の間でシングル サインオンを構成する方法について説明します。

この記事では、Smart Map Pro と Microsoft Entra ID を統合する方法について説明します。 Smart Map Pro と Microsoft Entra ID を統合すると、次のことができます。

- Smart Map Pro にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Smart Map Pro に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Smart Map Pro でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Smart Map Pro では、 **IDP** によって開始される SSO がサポートされます。

### ギャラリーから Smart Map Pro を追加する

Microsoft Entra ID への Smart Map Pro の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Smart Map Pro を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「Smart Map Pro**」と入力します。
4. 結果パネルから **[Smart Map Pro]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Smart Map Pro の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Smart Map Pro に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Smart Map Pro の関連ユーザーとの間にリンク関係を確立する必要があります。

Smart Map Pro で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Smart Map Pro の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Smart Map Pro テストユーザーの作成** - Smart Map Pro で、Microsoft Entra 上のユーザー B.Simon に対応するユーザーを作成し、それらのユーザー間をリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**Smart Map Pro**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.smartmap-pro.com/saml/smartmap/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.smartmap-pro.com/saml/smartmap/acs`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには [、Smart Map Pro サポート チーム](mailto:smartpr@ww-system.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. [ **Smart Map Pro のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Smart Map Pro の SSO の構成

**Smart Map Pro** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Smart Map Pro サポート チーム](mailto:smartpr@ww-system.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Smart Map Pro テスト ユーザーの作成

このセクションでは、Smart Map Pro で B.Simon というユーザーを作成します。 [Smart Map Pro サポート チーム](mailto:smartpr@ww-system.com)と協力して、Smart Map Pro プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した Smart Map Pro に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Smart Map Pro] タイルを選択すると、SSO を設定した Smart Map Pro に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smart360-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Smart360 を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smart360-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Smart360 の間でシングル サインオンを構成する方法について説明します。

この記事では、Smart360 と Microsoft Entra ID を統合する方法について説明します。 Smart360 を Microsoft Entra ID と統合すると、次のことができます。

- Smart360 にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Smart360 に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Smart360 でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Smart360 では、 **SP** によって開始される SSO がサポートされます。
- Smart360 では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Smart360 の追加

Microsoft Entra ID への Smart360 の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Smart360 を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**企業向けアプリ**&gt;**新規アプリケーション**にアクセスします。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Smart360**」と入力します。
4. 結果パネルから **Smart360** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Smart360 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Smart360 に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Smart360 の関連ユーザーとの間にリンク関係を確立する必要があります。

Smart360 に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Smart360 SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Smart360 テスト ユーザーの作成 - Smart360** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Smart360**&gt;**シングルサインオン**にアクセスします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:sso:<CustomerName>:smart360:primary`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.smart360.biz/smart360/saml/SSO`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CustomerName>.smart360.biz`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Smart360 クライアント サポート チーム](mailto:support@smart360.biz) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. Smart360 アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。 [ **編集]** アイコンを選択して、[ **ユーザー属性] ダイアログを** 開きます。

    [Image: [編集] アイコンが選択されているユーザー属性を示すスクリーンショット。]
7. その他に、Smart360 アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。 [**ユーザー属性**] ダイアログの [**ユーザー要求**] セクションで、次の手順を実行して、次の表に示すように SAML トークン属性を追加します。

    | 名前 | ソース属性 |
    | --- | --- |
    | ロール | user.assignedroles |

    注

    Microsoft Entra ID でロールを構成する方法については、 [こちらを](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#app-roles-ui) 選択してください。
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Smart360 の SSO の構成

**Smart360** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[Smart360 サポート チーム](mailto:support@smart360.biz)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Smart360 のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Smart360 に作成します。 Smart360 では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 ユーザーがまだ Smart360 に存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Smart360 サインオン URL にリダイレクトされます。
- Smart360 のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Smart360] タイルを選択すると、このオプションは Smart360 のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartcat-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Smartcat を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartcat-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-08-02
- Summary: Microsoft Entra と Smartcat の間のシングル サインオンを構成する方法について説明します。

この記事では、Smartcat と Microsoft Entra ID を統合する方法について説明します。 Smartcat を Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra ID を使用して、Smartcat にアクセスできるユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使って Smartcat に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Smartcat のシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから Smartcat を追加する

Microsoft Entra ID への Smartcat の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Smartcat を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Smartcat**」と入力します。
4. 結果パネルで **Smartcat** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Smartcat**&gt;**シングルサインオン**を参照します。
3. 次のセクションで以下の手順を実行します。

    1. [ **アプリケーションに移動] を**選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID を**コピーし、後で Smartcat 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
    3. [ **エンドポイント** ] タブで、 **OpenID Connect メタデータ ドキュメント** リンクをコピーし、後で Smartcat 側の構成で使用します。

        [Image: タブにエンドポイントが表示されているスクリーンショット。]
4. 左側のメニューの [ **認証** ] タブに移動し、次の手順を実行します。

    1. [ **リダイレクト URI** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<HOST_NAME>/users/auth/oAuthCallback`

        [Image: リダイレクト値を示すスクリーンショット。]
    2. [ **構成] ボタンを** 選択します。
5. 左側のメニューの **[証明書とシークレット** ] に移動し、次の手順を実行します。

    1. [ **クライアント シークレット** ] タブに移動し、[ **+新しいクライアント シークレット**] を選択します。
    2. テキストボックスに有効な **説明** を入力し、要件に従ってドロップダウンから **[有効期限** 日] を選択し、[ **追加**] を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、 **値** が生成されます。 この値をコピーして、後で Smartcat 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

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

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Smartcat へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Smartcat** に移動します。
3. アプリの概要ページで、[ **ユーザーとグループ**] を選択します。
4. [**ユーザー/グループの追加]** を選択し、[**追加された割り当て]** ダイアログで **[ユーザーとグループ**] を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. [ **追加された割り当て** ] ダイアログで、[ **割り当て** ] ボタンを選択します。

### Smartcat SSO を構成する

**Smartcat** 側で OAuth/OIDC フェデレーションのセットアップを完了するには、テナント ID、アプリケーション ID、クライアント シークレットなどのコピーされた値を Microsoft Entra から [Smartcat サポート チーム](mailto:support@smartcat.com)に送信する必要があります。 サポート チームはこれを設定して、OIDC 接続が両方の側で正しく設定されるようにします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartdraw-tutorial"} -->
## Microsoft Entra ID で SmartDraw for Single サインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartdraw-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SmartDraw 間にシングル サインオンを構成する方法について説明します。

この記事では、SmartDraw と Microsoft Entra ID を統合する方法について説明します。 SmartDraw を Microsoft Entra ID を統合すると、次のことができます。

- SmartDraw にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SmartDraw に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SmartDraw でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SmartDraw では、**SP Initiated SSO** と **IDP Initiated SSO** がサポートされます。
- SmartDraw では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

### ギャラリーからの SmartDraw の追加

Microsoft Entra ID への SmartDraw の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SmartDraw を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「SmartDraw**」と入力します。
4. 結果パネルから **SmartDraw** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SmartDraw 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SmartDraw に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SmartDraw の関連ユーザーとの間にリンク関係を確立する必要があります。

SmartDraw に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SmartDraw SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SmartDraw テスト ユーザーを作成する** - SmartDrawにおいてB.Simonに対応するユーザーを作成し、それをMicrosoft Entraのユーザー表現とリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SmartDraw**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://app.smartdraw.com/sso/saml/login/<DOMAIN>`

    注

    サインオン URL の値は実際の値ではありません。 実際のサインオン URL でサインオン URL の値を更新します。これについては、この記事の後半で説明します。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. **[保存] を選択します**。
8. SmartDraw アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、SmartDraw アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | Email | ユーザーのメールアドレス |
    | グループ | ユーザー.グループ |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. [ **SmartDraw のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SmartDraw の SSO を構成する

1. 別の Web ブラウザー ウィンドウで、SmartDraw 企業サイトに管理者としてサインインします
2. [SmartDraw ライセンスの管理] **で [シングル サインオン** ] を選択します。

    [Image: スクリーンショットは、[シングル サインオン] を選択できる [SmartDraw ライセンスの管理] ダイアログ ボックスを示しています。]
3. [Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) ページで、次の手順を実行します。

    [Image: 説明されている値を入力できる [構成] ページを示すスクリーンショット。]

    ある。 **「あなたのドメイン (例: acme.com)」** テキストボックスに、あなたのドメインを入力します。

    b。 インスタンスの **SP 開始ログイン URL を** コピーし、Azure portal の **[基本的な SAML 構成** ] の [サインオン URL] ボックスに貼り付けます。

    c. [ **Security Groups to Allow SmartDraw Access]\(SmartDraw アクセスを許可するセキュリティ グループ** \) ボックスに、「Everyone」と入力 **します**。

    d. [ **SAML 発行者 URL** ] ボックスに、前にコピーした **Microsoft Entra 識別子** の値を貼り付けます。

    え メモ帳で、ダウンロードしたメタデータ XML ファイルを開き、その内容をコピーして、[ **SAML メタデータ** ] ボックスに貼り付けます。

    f. [ **構成の保存] を選択する**

#### SmartDraw テスト ユーザーを作成する

このセクションでは、B.Simon というユーザーを SmartDraw に作成します。 SmartDraw では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 SmartDraw にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテストする**] を選択すると、このオプションはログイン フローを開始できる SmartDraw サインオン URL にリダイレクトされます。
- SmartDraw のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SmartDraw に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで SmartDraw タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SmartDraw に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smarteru-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SmarterU を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smarteru-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SmarterU の間でシングル サインオンを構成する方法について説明します。

手記

SmarterU と Microsoft Entra ID を統合するプロセスは、[SmarterU ヘルプ システム](https://support.smarteru.com/docs/sso-azure-active-directory)にも文書化および管理されています。

この記事では、SmarterU と Microsoft Entra ID を統合する方法について説明します。 SmarterU と Microsoft Entra ID を統合すると、次のことができます。

- SmarterU にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SmarterU に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SmarterU でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SmarterU では、IDP **Initiated SSO** がサポートされています。

手記

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから SmarterU を追加する

Microsoft Entra ID への SmarterU の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SmarterU を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックスに「SmarterU  入力します。
4. 結果パネル **SmarterU** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SmarterU の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、SmarterU に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SmarterU の関連ユーザーとの間にリンク関係を確立する必要があります。

SmarterU に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SmarterU SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **SmarterUテストユーザーを作成する** - Microsoft EntraでのB.Simonに対応するユーザーをSmarterUで作成してリンクします。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**&gt;**SmarterU**&gt;**シングルサインオン**に移動します。
3. [**シングル サインオン方法の選択]** ページで、**SAML**を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [**基本的な SAML 構成**] セクションで、次の手順を実行します。

    [**識別子** テキスト ボックスに、URL: `https://www.smarteru.com/` を入力します。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **SmarterU** のセットアップ セクションで、必要に応じて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SmarterU SSO の構成

1. 別の Web ブラウザー ウィンドウで、SmarterU 企業サイトに管理者としてサインインします。
2. 上部のツール バーで、[ **アカウント設定]** を選択します。

    [Image: アカウント設定]
3. アカウント構成ページで、次の手順を実行します。

    [Image: 外部承認]

    ある。 **（外部承認を有効にする）を選択して**を実行します。

    b。 [**マスター ログイン コントロール**] セクションで、[**SmarterU** タブを選択します]。

    c. [**ユーザーの既定のログイン**] セクションで、[**SmarterU**] タブを選択します。

    ｄ。 [**SAML**有効にする] を選択します。

    え ダウンロードしたメタデータ ファイルの内容をコピーし、**IdP メタデータ** ボックスに貼り付けます。

    f. **識別子属性/要求**を選択します。

    ジー **保存** を選択します。

#### SmarterU テスト ユーザーの作成

Microsoft Entra ユーザーが SmarterU にサインインできるようにするには、ユーザーを SmarterU にプロビジョニングする必要があります。 SmarterU の場合、プロビジョニングは手動で行います。

**ユーザー アカウントをプロビジョニングするには、次の手順を実行します。**

1. ご自身の**SmarterU** テナントにサインインします。
2. **ユーザー**に移動します。
3. ユーザー セクションで、次の手順を実行します。

    [Image: 新しいユーザー]

    ある。 **[+ユーザー] を選択します**。

    b。 Microsoft Entra ユーザー アカウントの関連する属性値を次のテキスト ボックスに入力します。**プライマリ 電子メール**、**従業員 ID**、**パスワード**、**パスワードの確認**、**指定された名前**、姓 します。

    c. **[アクティブ] を選択します**。

    ｄ。 **保存** を選択します。

手記

SmarterU が提供する他の SmarterU ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SmarterU に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SmarterU] タイルを選択すると、SSO を設定した SmarterU に自動的にサインインします。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartfile-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SmartFile を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartfile-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: SmartFile に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、SmartFile と Microsoft Entra ID で実行する手順を示して、ユーザーやグループを SmartFile に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

SmartFile は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。 - 次のいずれかのロール: - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) - [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。.
- [SmartFile テナント](https://www.SmartFile.com/pricing/)。
- 管理者アクセス許可がある SmartFile のユーザー アカウント。

### SmartFile へのユーザーの割り当て

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、SmartFile へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定したら、次の手順に従って、これらのユーザーやグループを SmartFile に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### ユーザーを SmartFile に割り当てるときの重要なヒント

- 単一の Microsoft Entra ユーザーを SmartFile に割り当てて、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- SmartFile にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### SmartFile をプロビジョニング用に設定する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SmartFile を構成する前に、SmartFile で SCIM プロビジョニングを有効にし、必要な詳細を収集する必要があります。

1. SmartFile 管理コンソールにサインインします。 SmartFile 管理コンソールの右上隅に移動します。 **[プロダクト キー]** を選択します。

    [Image: SmartFile 管理コンソール]
2. ベアラー トークンを生成するには、**プロダクト キー**と**プロダクト パスワード**をコピーします。 それらをメモ帳に貼り付け、間にコロンを入れます。

    [Image: [プロダクト キー] セクションのスクリーンショット。[プロダクト キー] と [プロダクト パスワード] のテキスト ボックスが選択されています。]

    [Image: プレーンテキストのスクリーンショット。[プロダクト キー] と [プロダクト パスワード] がコロンで区切られています。]

### ギャラリーから SmartFile を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に SmartFile を構成するには、SmartFile を Microsoft Entra アプリケーション ギャラリーから管理対象の SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから SmartFile を追加するには、以下の手順を行います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SmartFile**」と入力し、**[SmartFile]** を選びます。
4. 結果のパネルから **[SmartFile]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果リスト内のSmartFile]

### SmartFile への自動ユーザー プロビジョニングの構成

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、SmartFile でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

SmartFile シングル サインオンに関する記事で説明されている手順に従って、SmartFile に対して SAML ベースの [シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartfile-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra ID で SmartFile の自動ユーザー プロビジョニングを構成するには、以下を実施します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレード]
3. アプリケーションの一覧で **[SmartFile]** を選択します。

    [Image: アプリケーションの一覧の SmartFile リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、SmartFile テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が SmartFile に接続できることを確認します。 接続に失敗した場合は、SmartFile アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    注

    `https://<SmartFile sitename>.smartfile.com/ftp/scim` に「」と入力します。 例: `https://demo1test.smartfile.com/ftp/scim`。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[通知用メール]** フィールドに、プロビジョニングのエラー通知を受け取るユーザーの電子メール アドレスを入力して、 **[エラーが発生したときにメール通知を送信します]** チェック ボックスをオンにします。

    [Image: 通知と削除の設定を示す [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから SmartFile に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で SmartFile のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、SmartFile API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: SmartFile ユーザー属性マッピングの構成のスクリーンショット。]
12. **[属性マッピング]** セクションで、Microsoft Entra ID から SmartFile に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で SmartFile のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: SmartFile グループ属性マッピングの構成のスクリーンショット。]
13. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
14. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
15. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### コネクタの制限事項

- SmartFile では、物理的な削除のみがサポートされます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartfile-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SmartFile を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartfile-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と SmartFile の間でシングル サインオンを構成する方法について説明します。

この記事では、SmartFile と Microsoft Entra ID を統合する方法について説明します。 SmartFile と Microsoft Entra ID を統合すると、次のことができます。

- SmartFile にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SmartFile に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

SmartFile は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で使用できます。

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

- SmartFile でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SmartFile では、 **SP** によって開始される SSO がサポートされます。
- SmartFile では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartfile-provisioning-tutorial)。

### ギャラリーから SmartFile を追加する

Microsoft Entra ID への SmartFile の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SmartFile を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**を参照します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「SmartFile**」と入力します。
4. 結果パネルから **SmartFile** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SmartFile の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SmartFile に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SmartFile の関連ユーザーとの間にリンク関係を確立する必要があります。

SmartFile に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SmartFile SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SmartFile テスト ユーザーの作成** - SmartFile で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SmartFile**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.smartfile.com/ftp/login`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `<SUBDOMAIN>.smartfile.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、 [SmartFile クライアント サポート チーム](https://www.smartfile.com/support) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの **[SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **フェデレーション メタデータ XML** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **SmartFile のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SmartFile SSO の構成

**SmartFile** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [SmartFile サポート チーム](https://www.smartfile.com/support)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SmartFile テスト ユーザーの作成

このセクションでは、SmartFile で Britta Simon というユーザーを作成します。 [SmartFile サポート チーム](https://www.smartfile.com/support)と協力して、SmartFile プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

SmartFile では自動ユーザー プロビジョニングもサポートされています。自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartfile-provisioning-tutorial) ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SmartFile サインオン URL にリダイレクトされます。
- SmartFile のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SmartFile] タイルを選択すると、このオプションは SmartFile のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smarthr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SmartHR を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smarthr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SmartHR 間にシングル サインオンを構成する方法について説明します。

この記事では、SmartHR と Microsoft Entra ID を統合する方法について説明します。 SmartHR と Microsoft Entra ID を統合すると、次のことができます。

- SmartHR にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SmartHR に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SmartHR でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SmartHR では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの SmartHR の追加

Microsoft Entra ID への SmartHR の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SmartHR を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SmartHR**」と入力します。
4. 結果のパネルから **[SmartHR]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SmartHR 用の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SmartHR で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SmartHR の関連ユーザーとの間にリンク関係を確立する必要があります。

SmartHR に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SmartHR の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SmartHR テストユーザーを作成し、Microsoft Entra における B.Simon の対応ユーザーとしてリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SmartHR**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ア [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.smarthr.jp/external_saml/metadata`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.smarthr.jp/external_saml/acs`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.smarthr.jp/external_saml/sso`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[SmartHR クライアント サポート チーム](mailto:info@smarthr.jp)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SmartHR のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SmartHR の SSO の構成

**SmartHR** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** とアプリケーション構成からコピーした適切な URL を [SmartHR サポート チーム](mailto:info@smarthr.jp)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SmartHR のテスト ユーザーの作成

このセクションでは、SmartHR で B.Simon というユーザーを作成します。 [SmartHR サポート チーム](mailto:info@smarthr.jp)と連携して、SmartHR プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SmartHR サインオン URL にリダイレクトされます。
- SmartHR のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SmartHR] タイルを選択すると、このオプションは SmartHR サインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smarthub-infer-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SmartHub INFER を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smarthub-infer-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SmartHub INFER の間にシングル サインオンを構成する方法について学習します。

この記事では、SmartHub INFER と Microsoft Entra ID を統合する方法について説明します。 SmartHub INFER を Microsoft Entra ID と統合すると、次のことができます。

- SmartHub INFER にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SmartHub INFER に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SmartHub INFER でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SmartHub INFER では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。
- SmartHub INFER では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから SmartHub INFER を追加する

Microsoft Entra ID への SmartHub INFER の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SmartHub INFER を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SmartHub INFER**」と入力します。
4. 結果パネルで **[SmartHub INFER]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SmartHub INFER 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、SmartHub INFER に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SmartHub INFER の関連ユーザーとの間にリンク関係を確立する必要があります。

SmartHub INFER に対して Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SmartHub INFER の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. SmartHub INFER テストユーザーの作成 - B.Simon に対応するユーザーを SmartHub INFER で作成し、Microsoft Entra のユーザーとしてリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**SmartHub INFER**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **識別子** |
    | --- |
    | `https://<CUSTOMER_NAME>.infer.smarthub.ai/api/auth/<TENANT>/saml/metadata` |
    | `https://<CUSTOMER_NAME>.infer.smarthubai.net/api/auth/<TENANT>/saml/metadata` |

    b。 [ **応答 URL** ] テキスト ボックスに、次のいずれかのパターンを使用して URL を入力します。

    | **応答 URL** |
    | --- |
    | `https://<CUSTOMER_NAME>.smarthub.ai/api/auth/<TENANT>/saml/callback` |
    | `https://<CUSTOMER_NAME>.smarthubai.net/api/auth/<TENANT>/saml/callback` |
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.infer.smarthub.ai`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[SmartHub INFER クライアント サポート チーム](mailto:support@smarthub.ai)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SmartHub INFER の SSO の構成

**SmartHub INFER** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [SmartHub INFER サポート チーム](mailto:support@smarthub.ai)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SmartHub INFER のテストユーザーの作成

このセクションでは、Britta Simon というユーザーを SmartHub INFER に作成します。 SmartHub INFER では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 SmartHub INFER にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SmartHub INFER サインオン URL にリダイレクトされます。
- SmartHub INFER のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SmartHub INFER に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SmartHub INFER] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SmartHub INFER に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartkargo-tutorial"} -->
## Microsoft Entra ID を使用して、SmartKargo のシングルサインオンを設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartkargo-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SmartKargo の間でシングル サインオンを構成する方法について説明します。

この記事では、SmartKargo と Microsoft Entra ID を統合する方法について説明します。 SmartKargo と Microsoft Entra ID を統合すると、次のことができます。

- SmartKargo にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SmartKargo に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SmartKargo でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SmartKargo では、 **SP** Initiated SSO がサポートされます。

### ギャラリーから SmartKargo を追加する

Microsoft Entra ID への SmartKargo の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SmartKargo を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「SmartKargo**」と入力します。
4. 結果パネルから **SmartKargo** を選択し、アプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SmartKargo の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SmartKargo に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SmartKargo の関連ユーザーとの間にリンク関係を確立する必要があります。

SmartKargo に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SmartKargo の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SmartKargo のテスト ユーザーの作成** - SmartKargo で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SmartKargo**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.smartkargo.com/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.smartkargo.com/SamlResponse.aspx`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<CUSTOMER_NAME>.smartkargo.com/`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [SmartKargo クライアント サポート チーム](https://www.smartkargo.com/contactus) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **SmartKargo のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SmartKargo SSO の構成

**SmartKargo** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を [SmartKargo プラットフォーム サポート チーム](https://www.smartkargo.com/contactus)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SmartKargo テスト ユーザーの作成

このセクションでは、SmartKargo で B.Simon というユーザーを作成します。 [SmartKargo プラットフォーム サポート チーム](https://www.smartkargo.com/contactus)に問い合わせて、SmartKargo プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SmartKargo のサインオン URL にリダイレクトされます。
- SmartKargo のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SmartKargo] タイルを選択すると、このオプションは SmartKargo のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartlook-tutorial"} -->
## Microsoft Entra ID で Smartlook for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartlook-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Smartlook の間にシングル サインオンを構成する方法について説明します。

この記事では、Smartlook と Microsoft Entra ID を統合する方法について説明します。 Smartlook を Microsoft Entra ID を統合すると、次のことができます。

- Smartlook にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Smartlook に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Smartlook でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Smartlook では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます
- Smartlook では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Smartlook の追加

Microsoft Entra ID への Smartlook の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Smartlook を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Smartlook**」と入力します。
4. 結果パネルから **[Smartlook]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Smartlook 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Smartlook に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Smartlook の関連ユーザーとの間にリンク関係を確立する必要があります。

Smartlook に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Smartlook の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Smartlook のテストユーザーを作成し**、Microsoft Entra にある B.Simon の表現とリンクする Smartlook で B.Simon に対応するユーザーを設定します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Smartlook**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. アプリは Azure と事前に統合済みであるため、**[基本的な SAML 構成]** セクションで実行が必要な手順はありません。
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.smartlook.com/sign/sso`
7. **保存** を選択します。
8. Smartlook アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Smartlook アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | urn:oasis:names:tc:SAML:attribute:subject-id | ユーザー.ユーザープリンシパルネーム |
    | urn:oid:0.9.2342.19200300.100.1.3 | ユーザーのメールアドレス |
    |  |  |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Smartlook の SSO の構成

**Smartlook** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を [Smartlook サポート チーム](mailto:info@smartlook.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Smartlook のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Smartlook に作成します。 Smartlook では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Smartlook にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Smartlook のサインオン URL にリダイレクトされます。
- Smartlook のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Smartlook に自動的にサインインします

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Smartlook] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Smartlook に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartlpa-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SmartLPA を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartlpa-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SmartLPA の間のシングル サインオンを構成する方法について説明します。

この記事では、SmartLPA と Microsoft Entra ID を統合する方法について説明します。 SmartLPA と Microsoft Entra ID を統合すると、次のことができます。

- SmartLPA にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SmartLPA に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SmartLPA でのシングル サインオン (SSO) が有効なサブスクリプション。
- クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、[Azure の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SmartLPA では、**SP** Initiated SSO がサポートされます。

### ギャラリーから SmartLPA を追加する

Microsoft Entra ID への SmartLPA の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに SmartLPA を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SmartLPA**」と入力します。
4. 結果パネルから **[SmartLPA]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SmartLPA 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、SmartLPA に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SmartLPA の関連ユーザーとの間にリンク関係を確立する必要があります。

SmartLPA に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SmartLPA の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SmartLPA テスト ユーザーを作成する - Microsoft Entra におけるユーザーの表現とリンクされた SmartLPA 上の B.Simon に対応するユーザーを作成します。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SmartLPA**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: [基本的な SAML 構成] を編集する方法を示すスクリーンショット。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANTNAME>.smartlpa.com/<UNIQUE ID>`

    b。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<TENANTNAME>.smartlpa.com/`

    注

    これらの値は実際の値ではありません。 これらの値を実際の識別子とサインオン URL で更新してください。 これらの値を取得するには、[SmartLPA クライアント サポート チーム](mailto:support@smartlpa.com)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
7. **[SmartLPA のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 適切な構成URLをコピーする方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SmartLPA SSO の構成

**SmartLPA** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** とアプリケーション構成からコピーした適切な URL を [SmartLPA サポート チーム](mailto:support@smartlpa.com) に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SmartLPA のテスト ユーザーの作成

このセクションでは、SmartLPA で Britta Simon というユーザーを作成します。 [SmartLPA サポート チーム](mailto:support@smartlpa.com)と連携し、SmartLPA プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SmartLPA サインオン URL にリダイレクトされます。
- SmartLPA のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SmartLPA] タイルを選択すると、このオプションは SmartLPA のサインオン URL にリダイレクトされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartplan-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Smartplan を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartplan-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Smartplan の間でシングル サインオンを構成する方法について説明します。

この記事では、Smartplan と Microsoft Entra ID を統合する方法について説明します。 Smartplan と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID で Smartplan へのアクセス権を持つユーザーを管理します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Smartplan に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Smartplan でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Smartplan は、**SP および IDP** によるSSOを両方サポートしています。
- Smartplan では、 **Just-In-Time** ユーザー プロビジョニングがサポートされています。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから Smartplan を追加する

Microsoft Entra ID への Smartplan の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Smartplan を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Smartplan**」と入力します。
4. 結果パネルから **Smartplan** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Smartplan の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Smartplan に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Smartplan の関連ユーザーとの間にリンク関係を確立する必要があります。

Smartplan に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Smartplan SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Smartplan テスト ユーザーの作成 - Smartplan** で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザーとリンクするようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Smartplan**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://www.trpcorp.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.trpcorp.com/smartplan/sso/<Client_ID>/acs/`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.trpcorp.com/smartplan/sso/<Client_ID>/`

    注

    これらの値は実際の値ではありません。 実際の応答 URL とサインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Smartplan サポート チーム](mailto:support@trpcorp.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **Smartplan のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Smartplan SSO の構成

**Smartplan** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、Microsoft Entra 管理センターからコピーした適切な URL を [Smartplan サポート チーム](mailto:support@trpcorp.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Smartplan テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Smartplan に作成します。 Smartplan では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Smartplan にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Smartplan サインオン URL にリダイレクトします。
- Smartplan のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した Smartplan に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Smartplan] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Smartplan に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartrecruiters-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SmartRecruiters を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartrecruiters-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SmartRecruiters の間でシングル サインオンを構成する方法について学習します。

この記事では、SmartRecruiters と Microsoft Entra ID を統合する方法について説明します。 SmartRecruiters と Microsoft Entra ID を統合すると、次のことができます。

- SmartRecruiters にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SmartRecruiters に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SmartRecruiters でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SmartRecruiters では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

### ギャラリーからの SmartRecruiters の追加

Microsoft Entra ID への SmartRecruiters の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに SmartRecruiters を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SmartRecruiters**」と入力します。
4. 結果パネルから **[SmartRecruiters]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SmartRecruiters 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SmartRecruiters で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SmartRecruiters の関連ユーザーとの間にリンク関係を確立する必要があります。

SmartRecruiters で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SmartRecruiters の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SmartRecruiters のテスト ユーザーを作成** - SmartRecruiters で B.Simon に対応するユーザーを作成し、Microsoft Entra 表示と関連付けます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SmartRecruiters**&gt;**シングルサインオン**を参照してください。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [**基本的な SAML 構成**] セクションで、アプリケーションを**IDP** イニシエートモードで構成する場合は、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.smartrecruiters.com/web-sso/saml/<companyname>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.smartrecruiters.com/web-sso/saml/<companyname>/callback`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://www.smartrecruiters.com/web-sso/saml/<companyname>/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、[SmartRecruiters クライアント サポート チーム](https://www.smartrecruiters.com/about-us/contact-us/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. **[SmartRecruiters のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SmartRecruiters の SSO の構成

1. 別の Web ブラウザー ウィンドウで、SmartRecruiters 企業サイトに管理者としてログインします。
2. **[Settings / Admin](設定 / 管理者)** に移動します。

    [Image: メニューから [Settings / Admin](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定/管理者) が選択された画面のスクリーンショット。]
3. [ **構成** ] セクションで、[ **Web SSO**] を選択します。

    [Image: [Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成) から [Web S S O] が選択された画面のスクリーンショット。]
4. **[Enable Web SSO](Web SSO を有効にする)** をトグルします。

    [Image: [Enable Web S S O](Web S S O を有効にする) コントロールのスクリーンショット。]
5. **[構成](ID プロバイダー構成)** で、次の手順に従います。

    [Image: [Identity Provider Configuration](ID プロバイダー構成) のスクリーンショット。ここで、説明されている値を入力できます。]

    ある。 **[ID プロバイダーの URL]** テキストボックスに、**ログイン URL** の値を貼り付けます。

    b。 Azure portal からダウンロードした **certificate(Base64)** をメモ帳で開き、その内容をコピーして **[Identity Provider certificate](ID プロバイダー証明書)** ボックスに貼り付けます。
6. [ **Save Web SSO configuration]\(Web SSO 構成の保存\) を選択します**。

#### SmartRecruiters のテスト ユーザーの作成

このセクションでは、SmartRecruiters で Britta Simon というユーザーを作成します。 SmartRecruiters プラットフォームでユーザーを追加するには、[SmartRecruiters サポート チーム](https://www.smartrecruiters.com/about-us/contact-us/)に問い合わせてください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SmartRecruiters のサインオン URL にリダイレクトされます。
- SmartRecruiters のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SmartRecruiters に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SmartRecruiters] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SmartRecruiters に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartsheet-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Smartsheet を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartsheet-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-24
- Summary: Smartsheet に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、ユーザーやグループを Smartsheet に自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成するために [Smartsheet](https://www.smartsheet.com/pricing) と Microsoft Entra ID で実行する手順を示することです。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- Smartsheet でユーザーを作成する
- アクセスが不要になった場合に Smartsheet のユーザーを削除する
- Microsoft Entra ID と Smartsheet の間でユーザー属性の同期を維持する
- Smartsheet へのシングル サインオン (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- プロビジョニングを構成するための [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) を持つ Microsoft Entra ID のユーザー アカウント ( [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、 [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、 [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)など)。
- [Smartsheet テナント](https://www.smartsheet.com/pricing)。
- システム管理者アクセス許可を持つ Smartsheet Enterprise または Enterprise Premier プランのユーザー アカウント。
- **システム管理者**と **IT 管理者**は、Smartsheet で Active Directory を設定できます

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と Smartsheet の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように Smartsheet を構成する

Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Smartsheet を構成する前に、Smartsheet で SCIM プロビジョニングを有効にする必要があります。

1. **Smartsheet ポータル**で[システム管理者](https://app.smartsheet.com/b/home)としてサインインし、に移動します。

    [Image: Smartsheet アカウント管理のスクリーンショット]
2. 管理センターのページで、[ **メニュー** ] オプションを選択してメニュー パネルを表示します。

    [Image: Smartsheet セキュリティ コントロールのスクリーンショット]
3. **メニュー&gt;設定&gt;ドメインとユーザー自動プロビジョニング**に移動します。

    [Image: Smartsheet ドメインのスクリーンショット]
4. 新しいドメインを追加するには、[ **ドメインの追加** ] を選択し、指示に従います。 ドメインが追加されたら、ドメインも検証されていることを確認します。
5. [**Smartsheetポータル**](https://app.smartsheet.com/b/home)に移動し、その後、の順に進み、Microsoft Entra ID で自動ユーザー プロビジョニングを構成するために必要な&gt;を生成します。
6. **[API Access](API アクセス)** を選択します。 [ **新しいアクセス トークンの生成]** を選択します。

    [Image: [個人設定] ダイアログ ボックスのスクリーンショット。[API Access](API アクセス) と [Generate new access token](新しいアクセス トークンの生成) オプションが選択されています。]
7. API アクセス トークンの名前を定義します。 **[OK] を選択**.

    [Image: ステップ 1 (全部で 2) のスクリーンショット。[OK] オプションが選択されている [Generate API Access Token](API アクセス トークンの生成)。]
8. API アクセス トークンをコピーし、表示できる唯一の時間として保存します。 これは、Microsoft Entra ID の **[Secret Token](シークレット トークン)** フィールドに必要です。

    [Image: Smartsheet のトークン]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Smartsheet を追加する

Microsoft Entra アプリケーション ギャラリーから Smartsheet を追加して、Smartsheet へのプロビジョニングの管理を開始します。 SSO のために以前 Smartsheet を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Smartsheet への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Smartsheet でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Smartsheet の自動ユーザー プロビジョニングを構成するには、次のようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Smartsheet]** を選択します。

    [Image: アプリケーションの一覧の Smartsheet リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Smartsheet テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Smartsheet に接続できることを確認します。 接続に失敗した場合は、Smartsheet アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Smartsheet に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Smartsheet のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | タイトル | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | 電話番号[タイプ eq "携帯"].値 | 糸 |  |
    | phoneNumbers[type eq "ファックス"].value | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | エクスターナルID | 糸 |  |
    | 役割 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:コストセンター | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:マネージャー | 糸 |  |
12. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
13. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
14. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

- Smartsheet では、論理的な削除はサポートされていません。 ユーザーの **active** 属性が False に設定されていると、Smartsheet ではユーザーが完全に削除されます。

### 変更ログ

- 2020/06/16 - ユーザー向けにエンタープライズ拡張属性 "Cost Center"、"Division"、"Manager"、および "Department" のサポートが追加されました。
- 2021/02/10 - ユーザー向けにコア属性 "emails[type eq "work"]" のサポートが追加されました。
- 02/12/2022 - [管理者資格情報] セクションに SmartSheet 統合用の `https://scim.smartsheet.com/v2` の SCIM ベース/テナント URL が追加されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smarttrace-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SmartTrace を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smarttrace-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-09-10
- Summary: Microsoft Entra と SmartTrace の間のシングル サインオンを構成する方法について説明します。

この記事では、SmartTrace と Microsoft Entra ID を統合する方法について説明します。 SmartTrace を Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra ID を使用して、SmartTrace にアクセスできるユーザーを制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SmartTrace に自動的にサインインできるようにします。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SmartTrace でのシングル サインオン (SSO) が有効なサブスクリプション。

### ギャラリーから SmartTrace を追加する

Microsoft Entra ID への SmartTrace の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SmartTrace を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SmartTrace**」と入力します。
4. 結果パネルで **[SmartTrace]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SmartTrace**&gt;**シングルサインオン**に移動します。
3. 次のセクションで以下の手順を実行します。

    1. **[アプリケーションに移動]**を選択します。

        [Image: ID 構成を示すスクリーンショット。]
    2. **アプリケーション (クライアント) ID** と**ディレクトリ (テナント) ID** をコピーして、後で SmartTrace 側の構成で使用します。

        [Image: アプリケーション クライアント値のスクリーンショット。]
4. 左側のメニューの **[認証]** タブに移動し、次の手順を実行します。

    1. **[URI のリダイレクト]** テキストボックスに、`https://api.smarttrace.ai/v1/auth/callback/azure/<InstanceName>` のパターンを使って URL を入力します。
    2. **[フロントチャネル ログアウト URL]** テキスト ボックスに、「`https://api.smarttrace.ai/v1/auth/callback/azure-logout/<InstanceName>`[Image: リダイレクトの値を示すスクリーンショット。]」のパターンを使用して URL を入力します。
    3. **[構成]** ボタンを選択します。
5. 左側のメニューの **[証明書とシークレット]** に移動し、次の手順を実行します。

    1. **[クライアント シークレット]** タブに移動し、**[+ 新しいクライアント シークレット]** を選択します。
    2. テキストボックスに有効な **[説明]** を入力し、要件に応じてドロップダウンから **[有効期限]** 日数を選択し **[追加]** を選択します。

        [Image: クライアント シークレットの値を示すスクリーンショット。]
    3. クライアント シークレットを追加すると、**[値]** が生成されます。 この値をコピーして、後で SmartTrace 側の構成で使用します。

        [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. "**表示名**" フィールドに「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に SmartTrace へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SmartTrace** に移動します。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### SmartTrace SSO の構成

OIDC フェデレーションのセットアップを完了するための構成手順を次に示します。

1. SmartTrace サイトに管理者としてサインインします。
2. **SmartTrace** ヘッダーに移動**&gt;統合を**選択し、**Azure Active Directory** タイルの鉛筆アイコンを選択します。

    [Image: SmartTrace ヘッダー統合のスクリーンショット。]
3. **[Azure Active Directory]** ページで、次の手順を実行します。

    [Image: SmartTrace の構成ページのスクリーンショット。]

    1. **[APPLICATION ID]** (アプリケーション ID) テキストボックスに、Microsoft Entra ページからコピーした **アプリケーション (クライアント) ID** を貼り付けます。
    2. **[TENANT ID]** (テナント ID) テキストボックスに、Microsoft Entra ページからコピーした **ディレクトリ (テナント) ID** を貼り付けます。
    3. **[APPLICATION (CLIENT) SECRET]** (アプリケーション (クライアント) シークレット) テキストボックスに、Microsoft Entra 側の **[証明書とシークレット]** セクションからコピーした値を貼り付けます。
    4. **保存** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/smartvid.io-tutorial"} -->
## Microsoft Entra ID でシングル サインオンの smartvid.io を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/smartvid.io-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と smartvid.io の間にシングル サインオンを構成する方法について学習します。

この記事では、smartvid.io と Microsoft Entra ID を統合する方法について説明します。 smartvid.io を Microsoft Entra ID と統合すると、次のようなベネフィットが得られます。

- smartvid.io にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで smartvid.io に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、開始 [する前に無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- smartvid.io でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- smartvid.io では、 **IDP** Initiated SSO がサポートされます

### ギャラリーからの smartvid.io の追加

Microsoft Entra ID への smartvid.io の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に smartvid.io を追加する必要があります。

**ギャラリーから smartvid.io を追加するには、次の手順に従います。**

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. 検索ボックスに「smartvid.io」 **と**入力し、結果パネルから **smartvid.io** 選択し、[ **追加]** ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の smartvid.io]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、 **Britta Simon** というテスト ユーザーに基づいて、smartvid.io で Microsoft Entra のシングル サインオンを構成し、テストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと smartvid.io 内の関連ユーザーとの間にリンク関係が確立されている必要があります。

smartvid.io で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **smartvid.ioのシングルサインオンを構成** - アプリケーション側のシングルSign-On設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **smartvid.io のテストユーザーを作成する** - smartvid.io で Britta Simon の対応ユーザーを作成し、Microsoft Entra ユーザー表現にリンクさせます。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

smartvid.io で Microsoft Entra シングル サインオンを構成するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**smartvid.io** アプリケーション統合ページに移動し、[ **シングル サインオン**] を選択します。

    [Image: シングル サインオン リンクの構成]
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションでは、アプリが既に Azure と事前に統合されているため、ユーザーは手順を実行する必要はありません。

    [Image: smartvid.io ドメインとURLのシングルサインオン情報]
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **smartvid.io のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

    ある。 ログイン URL

    b。 Microsoft Entra アイデンティファイヤー

    c. ログアウト URL

#### smartvid.io のシングル サインオンの構成

**smartvid.io** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Raw)** と、アプリケーション構成からコピーした適切な URL を[サポート チーム smartvid.io](mailto:vgorsky@smartvid.io) 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### smartvid.io のテスト ユーザーの作成

このセクションでは、smartvid.io で Britta Simon というユーザーを作成します。 [smartvid.io サポート チーム](mailto:vgorsky@smartvid.io)と協力して、smartvid.io プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [smartvid.io] タイルを選択すると、SSO を設定した smartvid.io に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/snackmagic-tutorial"} -->
## Microsoft Entra ID で Single sign-on 用に Snackmagic を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/snackmagic-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Snackmagic の間にシングル サインオンを構成する方法について学習します。

この記事では、Snackmagic と Microsoft Entra ID を統合する方法について説明します。 Snackmagic を Microsoft Entra ID と統合すると、次のことができます:

- Snackmagic にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Snackmagic に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Snackmagic でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Snackmagic では、**SP と IDP** によって開始される SSO がサポートされます。
- Snackmagic では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Snackmagic の追加

Microsoft Entra ID への Snackmagic の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Snackmagic を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Snackmagic**」と入力します。
4. 結果のパネルから **[Snackmagic]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Snackmagic 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Snackmagic に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Snackmagic の関連ユーザーとの間にリンク関係を確立する必要があります。

Snackmagic で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Snackmagic の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Snackmagic のテストユーザーを作成 - Snackmagic** 上で B.Simon に対応するユーザーが Microsoft Entra にリンクされます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Snackmagic**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **リレー状態** ] テキスト ボックスに、URL を入力します。 `https://www.snackmagic.com/`

    b。 [ **ログアウト URL** ] テキスト ボックスに、URL を入力します。 `https://sso.snackmagic.com/slo/callback`
6. **追加の URL を設定** を選択し、**SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://www.snackmagic.com/?modal=login`
7. **保存** を選択します。
8. Snackmagic アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
9. その他に、Snackmagic アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
    | 会社 | ユーザー.companyname |
    | 電話 | ユーザー.電話番号 |
    | メール | ユーザー.ユーザープリンシパルネーム |
10. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
11. **[Snackmagic のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Snackmagic SSO の構成

1. Snackmagic 企業サイトに管理者としてサインインします。
2. **[Account Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウントの設定)**&gt;**[SSO Preferences](SSO 設定)** の順に移動し、次の手順を実行します。

    [Image: SSO の設定を示すスクリーンショット。]

    1. **[Enable SSO](SSO を有効にする)** チェックボックスをオンにします。
    2. **[Service Provider Issuer/Identifier] (サービス プロバイダー発行者/ID)** テキストボックスに、先ほどコピーした**識別子 URL** の値を貼り付けます。
    3. **ID プロバイダーの単一 Sign-On URL** テキストボックスに、先にコピーした **ログイン URL** 値を貼り付けます。
    4. ダウンロードした**証明書 (Base64)** をメモ帳で開き、その内容を **[X.509 証明書]** テキストボックスに貼り付けます。
    5. **[Enable SLO](SLO を有効にする)** チェックボックスをオンにします。
    6. **[Identity Provider Single Logout URL] (ID プロバイダーのシングル ログアウト URL)** テキストボックスに、先ほどコピーした**ログアウト URL** の値を貼り付けます。
    7. **[送信]**を選択します。

#### Snackmagic のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Snackmagic に作成します。 Snackmagic では、Just-In-Time ユーザー プロビジョニングがサポートされています。これは既定で有効になっています。 このセクションにはアクション項目はありません。 Snackmagic にユーザーがまだ存在していない場合は、認証後に新規作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Snackmagic のサインオン URL にリダイレクトされます。
- Snackmagic のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Snackmagic に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Snackmagic] タイルを選択すると、SP モードで構成されている場合は、サインイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Snackmagic に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/snowflake-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に Snowflake を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/snowflake-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-07-02
- Summary: Snowflake に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事では、Snowflake に対してユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成するために [Snowflake](https://www.Snowflake.com/pricing/) と Microsoft Entra ID で実行する手順について説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、[Microsoft Entra ID での SaaS アプリ ユーザー プロビジョニングの自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)に関する記事を参照してください。

### サポートされている機能

- Snowflake でユーザーを作成する
- アクセスが不要になったユーザーを Snowflake で削除する
- Microsoft Entra ID と Snowflake の間でユーザー属性の同期を維持します。
- Snowflake でグループとグループメンバーシップをプロビジョニングする
- Snowflake への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/snowflake-tutorial)を許可する (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra のテナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [Snowflake テナント](https://www.Snowflake.com/pricing/)
- **ACCOUNTADMIN** ロールを持つ Snowflake 内の少なくとも 1 人のユーザー。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定します。
3. [Microsoft Entra ID と Snowflake の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使ったプロビジョニングをサポートするように Snowflake を構成する

Microsoft Entra ID を使った自動ユーザー プロビジョニング用に Snowflake を構成する前に、Snowflake でクロスドメイン ID 管理システム (SCIM) プロビジョニングを有効にする必要があります。

1. 管理者として Snowflake にサインインし、Snowflake ワークシート インターフェイスまたは SnowSQL から次を実行します。

    ```
    use role accountadmin;
    
     create role if not exists aad_provisioner;
     grant create user on account to role aad_provisioner;
     grant create role on account to role aad_provisioner;
    grant role aad_provisioner to role accountadmin;
     create or replace security integration aad_provisioning
         type = scim
         scim_client = 'azure'
         run_as_role = 'AAD_PROVISIONER';
     select system$generate_scim_access_token('AAD_PROVISIONING');
    ```
2. ACCOUNTADMIN ロールを使用します。

    [Image: SCIM アクセス トークンが強調表示された、Snowflake UI のワークシートのスクリーンショット。]
3. カスタム ロール AAD\_PROVISIONER を作成します。 Microsoft Entra ID によって Snowflake に作成されたすべてのユーザーとロールは、スコープが絞り込まれた AAD\_PROVISIONER ロールによって所有されています。

    [Image: カスタム ロールを示すスクリーンショット。]
4. ACCOUNTADMIN ロールで、AAD\_PROVISIONER カスタム ロールを使用してセキュリティ統合を作成します。

    [Image: セキュリティ統合を示すスクリーンショット。]
5. 認証トークンを作成してクリップボードにコピーし、後で使用できるように安全に保存します。 SCIM REST API 要求ごとにこのトークンを使用し、要求ヘッダーに配置します。 アクセス トークンは 6 か月後に期限切れになり、このステートメントで新しいアクセス トークンを生成できます。

    [Image: トークンの生成を示すスクリーンショット。]

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Snowflake を追加する

Microsoft Entra アプリケーション ギャラリーから Snowflake を追加して、Snowflake へのプロビジョニングの管理を開始します。 シングル サインオン (SSO) のために Snowflake を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 [ギャラリーからアプリケーションを追加する方法の詳細をご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5:Snowflake への自動ユーザー プロビジョニングを構成する

このセクションでは、Snowflake でユーザーとグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。 Microsoft Entra ID では、ユーザーとグループの割り当てに基づいて構成を行うことができます。

Microsoft Entra ID で Snowflake の自動ユーザー プロビジョニングを構成するには、次の操作を実行します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ウィンドウを示すスクリーンショット。]
3. アプリケーションの一覧で、 **[Snowflake]** を選択します。

    [Image: アプリケーションの一覧を示すスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示されている [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. **[管理者資格情報]** セクションの **[テナント URL]** ボックスと **[シークレット トークン]** ボックスに、先ほど取得した SCIM 2.0 ベース URL と認証トークンをそれぞれ入力します。

    注

    Snowflake SCIM エンドポイントは、`/scim/v2/` が追加された Snowflake アカウント URL で構成されます。 たとえば、Snowflake アカウント名が `acme` で、Snowflake アカウントが `east-us-2` Azure リージョンにある場合、**テナント URL** の値は `https://acme.east-us-2.azure.snowflakecomputing.com/scim/v2` になります。
7. [ **テスト接続]** を選択して、Microsoft Entra ID が Snowflake に接続できることを確認します。 接続に失敗した場合は、Snowflake アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
8. [ **作成]** を選択して構成を作成します。
9. [**概要**] ページで **[プロパティ**] を選択します。
10. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
11. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
12. **[属性マッピング]** セクションで、Microsoft Entra ID から Snowflake に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Snowflake のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | 活動中 | ブール値 |
    | displayName | 糸 |
    | emails[type eq "work"].value | 糸 |
    | ユーザー名 | 糸 |
    | name.givenName | 糸 |
    | name.familyName | 糸 |
    | externalId | 糸 |
    | urn:ietf:params:scim:schemas:extension:2.0:User:type [ユーザー管理 - Snowflake ドキュメント](https://docs.snowflake.com/en/user-guide/admin-user-management#label-user-management-types) | 糸 |

    注

    グループ表示名の編集がロック解除されました。 以前は、Snowflake のグループの表示名を変更できなかったので、ユーザーがマッピングを編集することができませんでした。 編集可能になりました。

    注

    Snowflake では、SCIM プロビジョニング中にカスタム拡張機能ユーザー属性がサポートされました。

    - DEFAULT\_ROLE
    - DEFAULT\_WAREHOUSE
    - デフォルト\_セカンダリー\_ロール
    - SNOWFLAKE NAME AND LOGIN\_NAME FIELDS TO BE DIFFERENT

>
> Microsoft Entra SCIM ユーザーのプロビジョニングで Snowflake カスタム拡張機能属性を設定する方法については[こちら](https://community.snowflake.com/s/article/HowTo-How-to-Set-up-Snowflake-Custom-Attributes-in-Azure-AD-SCIM-for-Default-Roles-and-Default-Warehouses)で説明されています。
13. **[グループ]** を選びます。
14. **[属性マッピング]** セクションで、Microsoft Entra ID から Snowflake に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Snowflake のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ |
    | --- | --- |
    | displayName | 糸 |
    | members | リファレンス |
15. スコープ フィルターを構成するには、スコープ フィルターに関する記事に記載されている次の手順 [を](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)参照してください。
16. [オンデマンド プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-on-demand)使用して、少数のユーザーとの同期を検証してから、組織内でより広範にデプロイします。
17. プロビジョニングの準備ができたら、[**概要**] ページから [**プロビジョニングの開始**] を選択します。

### 手順 6: デプロイを監視する

プロビジョニングを構成したら、次のリソースを使用してデプロイを監視します。

1. [プロビジョニング ログを](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)使用して、どのユーザーが正常にプロビジョニングされたか、または正常にプロビジョニングされなかったかを判断する
2. [進行状況バー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-when-will-provisioning-finish-specific-user)を確認して、プロビジョニング サイクルの状態と完了までの近さを確認します
3. プロビジョニング構成が異常な状態にあると思われる場合、アプリケーションは検疫に入ります。 検疫状態についての詳細は、[アプリケーションプロビジョニングの隔離状態に関する記事](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-quarantine-status)をご覧ください。

### コネクタの制限事項

Snowflake で生成される SCIM トークンの有効期間は 6 か月です。 プロビジョニングの同期を引き続き機能させるには、有効期限が切れる前にこれらのトークンを更新する必要があることに注意してください。

### グループの PIM を使用した Just-In-Time (JIT) アプリケーション アクセス

グループのPrivileged Identity Management (PIM) を使用すると、Snowflake 内のグループへの Just-In-Time アクセスを提供し、Snowflake の特権グループに永続的にアクセスできるユーザーの数を減らすことができます。

**シングル サインオン (SSO) とプロビジョニング用にエンタープライズ アプリケーションを構成する**

Snowflake で永続的な管理者以外のアクセスを設定するには、次の手順を実行します。

1. Snowflake をテナントに追加し、このチュートリアルの前の手順で説明したようにプロビジョニング用に構成し、プロビジョニングを開始します。
2. Snowflake [のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/snowflake-tutorial) を構成します。
3. すべてのユーザーがアプリケーションにアクセスできるようにする [グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups) を作成します。
4. Snowflake アプリケーションにグループを割り当てます。
5. テスト ユーザーを、すべてのユーザー アクセス用に作成したグループの直接メンバーとして割り当てるか、アクセス パッケージを使用してグループへのアクセスを提供します。 このグループは、Snowflake で永続的な管理者以外のアクセスを提供します。

**グループに対して PIM を有効にする**

Just-In-Time 管理者アクセス権を付与するには、次の手順を実行します。

1. Microsoft Entra IDで 2 つ目のグループを作成します。 このグループは、Snowflake の管理者アクセス許可へのアクセスを提供します。
2. グループを [Microsoft Entra PIM の管理](https://learn.microsoft.com/ja-jp/azure/active-directory/privileged-identity-management/groups-discover-groups)下に置きます。
3. ロールがメンバーに設定されている [PIM のグループの対象](https://learn.microsoft.com/ja-jp/azure/active-directory/privileged-identity-management/groups-assign-member-owner)としてテスト ユーザーを割り当てます。
4. 2 番目のグループを Snowflake アプリケーションに割り当てます。
5. オンデマンド プロビジョニングを使用して、Snowflake にグループを作成します。
6. Snowflake にサインインし、2 番目のグループに管理者タスクを実行するために必要なアクセス許可を割り当てます。

PIM でグループの対象となったエンド ユーザーは、グループ [メンバーシップをアクティブ化](https://learn.microsoft.com/ja-jp/azure/active-directory/privileged-identity-management/groups-activate-roles#activate-a-role)することで、Snowflake 内のグループへの JIT アクセスを取得できるようになりました。

**重要な考慮事項**

- ユーザーがアプリケーションにプロビジョニングされるまでにかかる時間
    - Microsoft Entra ID において、Privileged Identity Management (PIM) を使用してグループ メンバーシップをアクティブ化することなく、ユーザーをグループに追加する場合:
        - グループ メンバーシップは、次の同期サイクルの間に、アプリケーションでプロビジョニングされます。 同期サイクルは 40 分ごとに実行されます。
    - ユーザーが Microsoft Entra ID PIM でグループ メンバーシップをアクティブ化する場合:
        - グループ メンバーシップがプロビジョニングされるまでには 2～10 分かかります。 要求量が多い期間中、要求は 10 秒あたり 5 つの要求の割合で調整されます。
        - 特定のアプリケーションのグループ メンバーシップをアクティブにしようとするユーザーのうち、10 秒の期間内の最初の 5 人については、2 から 10 分以内にアプリケーションでグループ メンバーシップがプロビジョニングされます。
        - 特定のアプリケーションのグループ メンバーシップをアクティブにしようとするユーザーのうち、10 秒の期間内の 6 人目以降については、次の同期サイクルの間にアプリケーションでグループ メンバーシップがプロビジョニングされます。 同期サイクルは 40 分ごとに実行されます。 エンタープライズアプリケーションごとにスロットリングの制限が設けられています。
- ユーザーが Snowflake で必要なグループにアクセスできない場合は、「トラブルシューティングの ヒント 」セクション、PIM ログ、プロビジョニング ログを確認して、グループ メンバーシップが正常に更新されたことを確認します。 ターゲット アプリケーションの設計方法によっては、グループ メンバーシップがアプリケーションで有効になるのに余分な時間がかかる場合があります。
- [Azure Monitor](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-log-analytics) を使用して、エラーのアラートを作成できます。
- 非アクティブ化は、通常の増分サイクル中に行われます。 オンデマンド プロビジョニングによってすぐには処理されません。

### トラブルシューティングのヒント

現在、Microsoft Entra プロビジョニング サービスは特定の [IP 範囲](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#ip-ranges)で動作します。 必要に応じて、他の IP 範囲を制限し、これらの特定の IP 範囲をアプリケーションの許可リストに追加できます。 この手法により、Microsoft Entra プロビジョニング サービスからアプリケーションへのトラフィック フローが可能になります。

### 変更ログ

- 2020 年 7 月 21 日: (アクティブな属性を使用して) すべてのユーザーに対して論理的な削除を有効化。
- 2022 年 10 月 12 日: Snowflake SCIM 構成を更新しました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/snowflake-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Snowflake を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/snowflake-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Snowflake の間にシングル サインオンを構成する方法について学習します。

この記事では、Snowflake と Microsoft Entra ID を統合する方法について説明します。 Snowflake を Microsoft Entra ID と統合すると、次のことができます:

- Snowflake にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Snowflake に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

Snowflake は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

| グローバル サービス | 米国政府 | 21Vianet が運営する中国 |
| --- | --- | --- |
| ✅ | ✅ |  |

### 前提条件

Microsoft Entra と Snowflake の統合を構成するには、次の項目が必要です。

- Microsoft Entra サブスクリプション。 Microsoft Entra 環境をお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Snowflake アカウントと、そのアカウントの管理者ロール。
- Microsoft Entraの管理者ロール。 クラウド アプリケーション管理者と共に、アプリケーション管理者も、Microsoft Entra ID でアプリケーションを追加または管理することができます。 詳細については、 [Azure の組み込みロールに関するページを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Snowflake では、 **SP と IDP** によって開始される SAML SSO がサポートされます。
- Snowflake には OpenID Connect (OIDC) 統合もありますが、この記事では説明しません。 詳細については、「 [OpenID Connect (OIDC) フェデレーション認証の構成」を](https://docs.snowflake.com/en/user-guide/admin-security-fed-auth-oidc)参照してください。
- Snowflake では、 [自動ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/snowflake-provisioning-tutorial) がサポートされています (推奨)。
- Snowflake では、Microsoft Entraを使用するアプリケーションとエージェントのワークロード ID フェデレーションと OAuth もサポートされています。

### ギャラリーから Snowflake を追加する

Azure Microsoft Entra ID への Snowflake の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Snowflake を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいエンタープライズ アプリ**を参照してください。
3. **[ギャラリーからの追加] セクションで**、検索ボックスに**「Snowflake**」と入力します。
4. 結果パネルから **Snowflake** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Snowflake 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Snowflake に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Snowflake の関連ユーザーとの間にリンク関係を確立する必要があります。

Snowflake に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Snowflake SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Snowflake テスト ユーザーを作成して、B.Simon に対応する者としてのユーザーを生成し、それを Microsoft Entra のユーザー表現とリンクさせます。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[Snowflake]**&gt;**[シングル サインオン]** を参照します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な S A M L 構成を編集するスクリーンショット。]
5. **IDP** 開始モードでアプリケーションを構成する場合は、[**基本的な SAML 構成]** セクションで次の手順を実行します。

    a. [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SNOWFLAKE-URL>.snowflakecomputing.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SNOWFLAKE-URL>.snowflakecomputing.com/fed/login`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    a. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SNOWFLAKE-URL>.snowflakecomputing.com`

    b。 [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SNOWFLAKE-URL>.snowflakecomputing.com/fed/logout`

    注記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL、サインアウト URL でこれらの値を更新します。 詳細については、「 [SAML 2.0 フェデレーション認証の構成」](https://docs.snowflake.com/en/user-guide/admin-security-fed-auth-security-integration)を参照してください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **Snowflake のセットアップ** ] セクションで、要件に従って 1 つ以上の適切な URL をコピーします。

    [Image: スクリーンショットは、構成を適切なURLにコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Snowflake の SSO の構成

1. 別の Web ブラウザー ウィンドウで、セキュリティ管理者として Snowflake にサインインします。
2. ページの右上にある**プロファイル**を選択して、ロールを **ACCOUNTADMIN** に**切り替えます**。

    注記

    これは、右上の [ユーザー名] で選択したコンテキストとは別のものです。

    [Image: Snowflake 管理者]
3. **ダウンロードした Base 64 証明書**をメモ帳で開きます。 "-----BEGIN CERTIFICATE-----" と "-----END CERTIFICATE-----" の間の値をコピーし、この内容を **SAML2\_X509\_CERT**に貼り付けます。
4. **SAML2\_ISSUER**に、前にコピーした**識別子**の値を貼り付けます。
5. **SAML2\_SSO\_URL**に、前にコピーした**ログイン URL** 値を貼り付けます。
6. **SAML2\_PROVIDER**で、`CUSTOM`のような値を指定します。
7. **[すべてのクエリ] を**選択し、[実行] を選択**します**。

    [Image: Snowflake sql]

    ```
    CREATE [ OR REPLACE ] SECURITY INTEGRATION [ IF NOT EXISTS ]
    TYPE = SAML2
    ENABLED = TRUE | FALSE
    SAML2_ISSUER = '<EntityID/Issuer value which you have copied>'
    SAML2_SSO_URL = '<Login URL value which you have copied>'
    SAML2_PROVIDER = 'CUSTOM'
    SAML2_X509_CERT = '<Paste the content of downloaded certificate from Azure portal>'
    [ SAML2_SP_INITIATED_LOGIN_PAGE_LABEL = '<string_literal>' ]
    [ SAML2_ENABLE_SP_INITIATED = TRUE | FALSE ]
    [ SAML2_SNOWFLAKE_X509_CERT = '<string_literal>' ]
    [ SAML2_SIGN_REQUEST = TRUE | FALSE ]
    [ SAML2_REQUESTED_NAMEID_FORMAT = '<string_literal>' ]
    [ SAML2_POST_LOGOUT_REDIRECT_URL = '<string_literal>' ]
    [ SAML2_FORCE_AUTHN = TRUE | FALSE ]
    [ SAML2_SNOWFLAKE_ISSUER_URL = '<string_literal>' ]
    [ SAML2_SNOWFLAKE_ACS_URL = '<string_literal>' ]
    ```

サインイン URL として組織名を持つ新しい Snowflake URL を使用している場合は、次のパラメーターを更新する必要があります。

統合を変更して Snowflake 発行者 URL と SAML2 Snowflake ACS URL を追加します。詳細については、 [この](https://community.snowflake.com/s/knowledgebase) 記事の手順 6 に従ってください。

1. [ SAML2\_SNOWFLAKE\_ISSUER\_URL = '&lt;string\_literal&gt;' ]

    alter security integration `<your security integration name goes here>` set SAML2\_SNOWFLAKE\_ISSUER\_URL = `https://<organization_name>-<account name>.snowflakecomputing.com`;
2. [ SAML2\_SNOWFLAKE\_ACS\_URL = '&lt;string\_literal&gt;' ]

    alter security integration `<your security integration name goes here>` set SAML2\_SNOWFLAKE\_ACS\_URL = `https://<organization_name>-<account name>.snowflakecomputing.com/fed/login`;

注記

SAML2 セキュリティ統合を作成する方法の詳細については、 [この](https://docs.snowflake.com/en/sql-reference/sql/create-security-integration.html) ガイドに従ってください。

注記

`saml_identity_provider` アカウント パラメーターを使用して既存の SSO セットアップがある場合は、[この](https://docs.snowflake.com/en/user-guide/admin-security-fed-auth-advanced.html)ガイドに従って SAML2 セキュリティ統合に移行してください。

#### Snowflake のテスト ユーザーの作成

Microsoft Entra ユーザーが Snowflake にサインインできるようにするには、ユーザーを Snowflake にプロビジョニングする必要があります。 Snowflake では、プロビジョニングは手動のタスクです。

**ユーザー アカウントをプロビジョニングするには、次の手順に従います。**

1. セキュリティ管理者として Snowflake にサインインします。
2. ページの右上にある**プロファイル**を選択して、ロールを **ACCOUNTADMIN** に**切り替えます**。

    [Image: Snowflake 管理者]
3. 次に示すように、次の SQL クエリを実行してユーザーを作成します。"サインイン名" がワークシートの Microsoft Entra ユーザー名に設定されていることを確認します。

    [Image: Snowflake adminsql]

    ```
     use role accountadmin;
     CREATE USER britta_simon PASSWORD = '' LOGIN_NAME = 'BrittaSimon@contoso.com' DISPLAY_NAME = 'Britta Simon';
    ```

注記

ユーザーとグループが SCIM 統合を使用してプロビジョニングされている場合、手動プロビジョニングは必要ありません。 [Snowflake](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/snowflake-provisioning-tutorial) の自動プロビジョニングを有効にする方法を参照してください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。 Snowflake によって返される SAML エラー コードの詳細については、 [フェデレーション認証と SSO のトラブルシューティングに関するページを](https://docs.snowflake.com/en/user-guide/errors-saml)参照してください。

##### SP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Snowflake のサインオン URL にリダイレクトされます。
- Snowflake のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP Initiated:

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Snowflake に自動的にサインインします。

また、Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Snowflake] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Snowflake に自動的にサインインされます。 詳細については、「 [Microsoft Entra マイ アプリ](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。

### ローカル アカウントを介したアプリケーション アクセスを禁止する

SSO が機能することを検証し、組織でロールアウトしたら、ローカル資格情報を使用してアプリケーション アクセスを無効にすることをお勧めします。 これにより、Snowflake へのサインインを保護するために、条件付きアクセス ポリシーや MFA などが確実に設定されます。 [SSO を構成するための](https://docs.snowflake.com/en/user-guide/admin-security-fed-auth-use) Snowflake ドキュメントを確認し、ALTER USER コマンドレットを使用してユーザー パスワードを削除します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/soc-sst-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に SOC SST を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/soc-sst-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SOC SST の間にシングル サインオンを構成する方法について説明します。

この記事では、SOC SST と Microsoft Entra ID を統合する方法について説明します。 SOC は必須の法的ドキュメントに準拠しており、従業員を登録している公的機関や民間企業ではソフトウェア内で管理できます (CLT)。 SOC SST と Microsoft Entra ID を統合すると、次のことができます。

- SOC SST にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントを使用して SOC SST に自動的にサインインするように設定できます。
- 1 つの中央の場所でアカウントを管理します。

テスト環境で SOC SST 向けの Microsoft Entra のシングル サインオンを構成してテストします。 SOC SST では、 **SP** と **IDP** によって開始されるシングル サインオンがサポートされます。

### [前提条件]

Microsoft Entra ID を SOC SST と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- SOC SST のシングル サインオン (SSO) 対応サブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから SOC SST アプリケーションを追加する必要があります。 アプリケーションに割り当ててシングル サインオン構成をテストするには、テスト ユーザー アカウントが必要です。

#### Microsoft Entra ギャラリーから SOC SST を追加する

Microsoft Entra アプリケーション ギャラリーから SOC SST を追加して、SOC SST とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーの作成と割り当て

[作成とユーザー アカウントの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)に関する記事のガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加し、ユーザー/グループをアプリに追加し、ロールを割り当てることができます。 このウィザードでは、シングル サインオン構成ウィンドウへのリンクも提供されます。 [Microsoft 365 ウィザードの詳細を確認してください](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)。.

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SOC SST**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して値を入力します。 `<InstanceName>.soc.com.br`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://sistema.soc.com.br/WebSoc/sso/<CustomerID>/saml/finalize.action`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://sistema.soc.com.br/WebSoc/sp/<CustomerID>/login`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [SOC SST クライアント サポート チーム](mailto:suporte@soc.com.br) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (PEM)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
8. [ **SOC SST のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成の適切な URL をコピーするスクリーンショット。]

### SOC SST の SSO を構成する

**SOC SST** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (PEM)** と、アプリケーション構成からコピーした適切な URL を [SOC SST サポート チーム](mailto:suporte@soc.com.br)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SOC SST のテスト ユーザーを作成する

このセクションでは、SOC SST で Britta Simon というユーザーを作成します。 [SOC SST サポート チーム](mailto:suporte@soc.com.br)と協力して、SOC SST プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SOC SST サインオン URL にリダイレクトされます。
- SOC SST のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SOC SST に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SOC SST] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SOC SST に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/softeon-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Softeon WMS を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/softeon-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Softeon WMS 間にシングル サインオンを構成する方法について説明します。

この記事では、Softeon WMS と Microsoft Entra ID を統合する方法について説明します。 Softeon WMS を Microsoft Entra ID を統合すると、次のことができます:

- Softeon WMS にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Softeon WMS に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Softeon WMS でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Softeon WMS は、**SP** および **IDP** による SSO に対応しています。
- Softeon WMS では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Softeon WMS を追加する

Microsoft Entra ID への Softeon WMS の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Softeon WMS を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Softeon WMS**」と入力します。
4. 結果パネルから **Softeon WMS** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Softeon WMS 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Softeon WMS に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Softeon WMS の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO を Softeon WMS と一緒に構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Softeon WMS SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Softeon WMS テスト ユーザーの作成 - Softeon** WMS で B.Simon に対応するユーザーを作成し、Microsoft Entra で表現されるこのユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Softeon WMS**&gt;**シングルサインオン**に移動します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.softeon.com/sp`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.softeon.com/<CUSTOM_URL>`
6. **SP** 開始モードでアプリケーションを構成する場合**は、[追加の URL の設定] を**選択し、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<companyname>.softeon.com/<instancename>`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには、 [Softeon WMS クライアント サポート チーム](mailto:contact@softeon.com) に問い合わせてください。 「 **基本的な SAML 構成** 」セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
8. [ **Softeon WMS のセットアップ** ] セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Softeon WMS の SSO を構成する

**Softeon WMS** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Softeon WMS サポート チーム](mailto:contact@softeon.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Softeon WMS テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Softeon WMS に作成します。 Softeon WMS では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Softeon WMS にユーザーがまだ存在していない場合は、認証後に新しく作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Softeon WMS のサインオン URL にリダイレクトされます。
- Softeon WMS のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Softeon WMS に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Softeon WMS] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Softeon WMS に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/software-ag-cloud-tutorial"} -->
## Microsoft Entra ID を使用してシングル サインオン用に Software AG Cloud を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/software-ag-cloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Software AG Cloud 間にシングル サインオンを構成する方法について説明します。

この記事では、Software AG Cloud と Microsoft Entra ID を統合する方法について説明します。 Software AG Cloud を Microsoft Entra ID と統合すると、次のことができます。

- Software AG Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Software AG Cloud に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

ソフトウェア AG クラウドは、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Software AG Cloud でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Software AG Cloud では、**SP** Initiated SSO がサポートされます。
- Software AG Cloud では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Software AG Cloud の追加

Microsoft Entra ID への Software AG Cloud の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Software AG Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Software AG Cloud**」と入力します。
4. 結果パネルで **[Software AG Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Software AG Cloud 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Software AG Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Software AG Cloud の関連ユーザーの間にリンク関係を確立する必要があります。

Software AG Cloud に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Software AG Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Software AG Cloud のテストユーザーを作成** - Microsoft Entra のユーザーである B.Simon に対応するユーザーを Software AG Cloud に持たせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[Software AG Cloud]**&gt;**[シングル サインオン]**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。

        `https://<SUBDOMAIN>.softwareag.cloud/auth/realms/TENANT-NAME`
    2. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。

        `https://<SUBDOMAIN>.softwareag.cloud/auth/realms/TENANT-NAME/broker/IDENTITY-PROVIDER-NAME/endpoint`

        注

        これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Software AG Cloud クライアント サポート チーム](mailto:support@softwareag.com)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Software AG Cloud のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Software AG Cloud の SSO の構成

1. 別の Web ブラウザー ウィンドウで、Software AG Cloud Web サイトに管理者としてサインインします。
2. **[管理] を選択する**

    [Image: Software AG Cloud の管理の構成]
3. **[Single-sign on](シングル サインオン) &gt; [Add identity provider](ID プロバイダーの追加)** に移動します。

    [Image: Software AG Cloud の ID プロバイダーの構成]
4. 次のページで、以下の手順を実行します。

    [Image: Software AG Cloud の構成の手順]

    a. **[Identity provider display name](ID プロバイダーの表示名)** ボックスに、名前 (例: `azure ad`) を入力します。

    b。 **[Identity provider unique identifier for use in Software AG Cloud redirect URI](Software AG Cloud リダイレクト URI で使用する ID プロバイダーの一意識別子)** ボックスに、ID プロバイダーの一意の名前を入力します。 **[Software AG Cloud redirect URI**] フィールドが更新され、URI が設定されます。 この URI をコピーし、それを使用して、定義されているパターンに従って Azure portal で**エンティティ ID** と他の情報を構成します。

    c. **ID プロバイダー構成**で**フェデレーション メタデータ XML** ファイルをインポートし、[**次へ**] を選択します。

    d. **[Configuration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/構成)** ページに移動し、必要に応じてフィールドに入力します。

#### Software AG Cloud のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを Software AG Cloud に作成します。 Software AG Cloud では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Software AG Cloud にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使って Microsoft Entra のシングル サインオン構成をテストします。

Microsoft Azure が Software AG Cloud でプロバイダーとして構成されていることを前提として、 `www.softwareag.cloud` に移動し、[ログイン] ボタンを選択し、環境名を入力します。 次の画面で、[ &lt;IDP NAME でログイン&gt;] リンクを選択し、資格情報を入力します。 認証が完了すると、ログインし、Software AG Cloud のホーム ページに移動します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/soloinsight-cloudgate-sso-provisioning-tutorial"} -->
## Soloinsight-CloudGate SSO を構成して、Microsoft Entra ID を使用した自動ユーザー プロビジョニングを行う - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/soloinsight-cloudgate-sso-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-24
- Summary: Soloinsight-CloudGate SSO に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、ユーザーやグループを自動的にプロビジョニングおよび削除するように Microsoft Entra ID を構成し、Soloinsight-CloudGate SSO へのプロビジョニングを目的とした Soloinsight-CloudGate SSO と Microsoft Entra ID で実行すべき手順を示すことです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [Soloinsight-CloudGate SSO テナント](https://www.soloinsight.com/)
- 管理者のアクセス許可が与えられている Soloinsight-CloudGate SSO のユーザー アカウント。

### 手順 1: Soloinsight-CloudGate SSO にユーザーを割り当てる

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、Soloinsight-CloudGate SSO へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを Soloinsight-CloudGate SSO に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### 手順 2: Soloinsight-CloudGate SSO へのユーザー割り当てに関する重要なヒント

- 1 人の Microsoft Entra ユーザーを Soloinsight-CloudGate SSO に割り当てて、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- Soloinsight-CloudGate SSO にユーザーを割り当てるときは、有効なアプリケーション固有ロール (使用可能な場合) を割り当てダイアログで選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 3: プロビジョニング用 Soloinsight-CloudGate SSO を設定する

1. [Soloinsight-CloudGate SSO 管理者コンソール](https://soloinsight.sigateway.com/login)にサインインします。 **[Administration](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/管理) &gt; [System Settings](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/システム設定)** に移動します。

    [Image: Soloinsight-CloudGate SSO 管理者コンソール]
2. **[全般]** に移動します。

    [Image: Soloinsight-CloudGate SSO に SCIM を追加する]
3. 下にスクロールし、ページの終わりにある **[テナント URL]** と **[シークレット トークン]** を表示します。 **シークレット トークン**をコピーします。 この値を、Soloinsight-CloudGate SSO アプリケーションの [プロビジョニング] タブの [シークレット トークン] フィールドに入力します。

    [Image: Soloinsight-CloudGate SSO のトークン作成]

### 手順 4: ギャラリーから Soloinsight-CloudGate SSO を追加する

Microsoft Entra ID での自動ユーザー プロビジョニング用に Soloinsight-CloudGate SSO を構成する前に、Microsoft Entra アプリケーション ギャラリーから Soloinsight-CloudGate SSO をマネージド SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから Soloinsight-CloudGate SSO を追加するには、次の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで「**Soloinsight-CloudGate SSO**」と入力し、検索ボックスで **[Soloinsight-CloudGate SSO]** を選択します。
4. 結果パネルから **[Soloinsight-CloudGate SSO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の Soloinsight-CloudGate SSO]

### 手順 5: Soloinsight-CloudGate SSO 向けの自動ユーザープロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、Soloinsight-CloudGate SSO でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

Soloinsight-CloudGate SSO シングル サインオンに関する記事に記載されている手順に従って、Soloinsight-CloudGate SSO に対して SAML ベース [のシングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/soloinsight-cloudgate-sso-tutorial)を有効にすることもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra ID で Soloinsight-CloudGate SSO の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズアプリケーションブレード]
3. アプリケーションの一覧で **[Soloinsight-CloudGate SSO]** を選択します。

    [Image: アプリケーションの一覧の [Soloinsight-CloudGate SSO] リンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、 `https://sigateway.com/scim/v2/sync/serviceproviderconfig`入力します。 **[シークレット トークン]** に先ほど取得した**SCIM 認証トークン**の値を入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が Soloinsight-CloudGate SSO に接続できることを確認します。 接続に失敗した場合は、Soloinsight-CloudGate SSO アカウントに必要な管理者アクセス許可があることを確認してから、やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から Soloinsight-CloudGate SSO に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Soloinsight-CloudGate SSO のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Soloinsight-CloudGate SSO のユーザー属性]
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から Soloinsight-CloudGate SSO に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で Soloinsight-CloudGate SSO のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: Soloinsight-CloudGate SSO のグループ属性]
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/soloinsight-cloudgate-sso-tutorial"} -->
## Soloinsight-CloudGate SSO シングル サインオンを Microsoft Entra ID で構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/soloinsight-cloudgate-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Soloinsight-CloudGate SSO の間のシングル サインオンを構成する方法について説明します。

この記事では、Soloinsight-CloudGate SSO と Microsoft Entra ID を統合する方法について説明します。 Soloinsight-CloudGate SSO と Microsoft Entra ID を統合すると、次のことができます。

- Soloinsight-CloudGate SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って、Soloinsight-CloudGate SSO に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Soloinsight-CloudGate SSO シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Soloinsight-CloudGate SSOでは、**SP**によって開始されたSSOがサポートされています。
- Soloinsight-CloudGate SSO では、 [自動ユーザー プロビジョニングがサポートされます](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/soloinsight-cloudgate-sso-provisioning-tutorial)。

### ギャラリーから Soloinsight-CloudGate SSO を追加する

Microsoft Entra ID への Soloinsight-CloudGate SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Soloinsight-CloudGate SSO を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**新しいアプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックス **Soloinsight-CloudGate SSO** と入力します。
4. 結果パネルから **Soloinsight-CloudGate SSO** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Soloinsight-CloudGate SSO 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Soloinsight-CloudGate SSO に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Soloinsight-CloudGate SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Soloinsight-CloudGate SSO に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Soloinsight-CloudGate SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Soloinsight-CloudGate SSO テストユーザーの作成** - Soloinsight-CloudGate SSO で B.Simon に対応するユーザーを作成し、Microsoft Entra のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID**&gt;**Enterprise apps**&gt; SSO アプリケーション統合ページ**Soloinsight-CloudGate** 参照し、[**管理**] セクションを見つけて、[**シングル サインオン**] を選択します。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. [ **基本的な SAML 構成]** ページで、次の手順を実行します。

    1. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.sigateway.com/login`
    2. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.sigateway.com/process/sso`

    注意

    これらの値は実際の値ではありません。 これらの値は、実際のサインオン URL と識別子で更新します。これについては、この記事の **「Soloinsight-CloudGate SSO シングル サインオンの構成** 」セクションで後述します。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (Base64)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. [ **Soloinsight-CloudGate SSO のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL のコピー]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Soloinsight-CloudGate SSO を構成する

1. 別の Web ブラウザー ウィンドウで、Soloinsight-CloudGate SSO 企業サイトに管理者としてサインインします
2. 基本的な SAML の構成中に Azure portal に貼り付ける値を取得するには、資格情報を使用して CloudGate Web ポータルにサインインし、SSO 設定にアクセスします。これは、次 **のパス Home&gt;Administration&gt;System settings&gt;General** にあります。

    [Image: CloudGate SSO の設定]
3. **SAML コンシューマー URL**

    - **[Saml Consumer URL**] フィールドと [**リダイレクト URL**] フィールドに対して使用可能なリンクをコピーし、それぞれ [**識別子 (エンティティ ID)]** フィールドと **[応答 URL**] フィールドの Azure portal の **[基本的な SAML 構成**] セクションに貼り付けます。

        [Image: SAML識別子]
4. **SAML 署名証明書**

    - Azure portal の SAML 署名証明書の一覧からダウンロードした証明書 (Base64) ファイルのソースに移動し、右選択します。 一覧から [ **メモ帳で編集]** オプションを選択します。

        [Image: SAMLcertificate]
    - 証明書 (Base64) Notepad++ ファイルの内容をコピーします。

        [Image: 証明書のコピー]
    - CloudGate Web Portal の SSO 設定 **の [証明書** ] フィールドにコンテンツを貼り付け、[保存] ボタンを選択します。

        [Image: 証明書ポータル]
5. **既定のグループ**

    - CloudGate Web ポータルの **[既定のグループ**] オプションのドロップダウン リストから [**ビジネス管理者**] を選択します

        [Image: 既定のグループ]
6. **AD 識別子とログイン URL**

    - コピーした **ログイン URL** **の設定 Soloinsight-CloudGate SSO** 構成は、CloudGate Web Portal の SSO 設定セクションに入力します。
    - CloudGate Web Portal AD **ログイン URL** フィールドに、Azure portal からの **ログイン URL リンクを** 貼り付けます。
    - Azure portal の **Microsoft Entra Identifier** リンクを CloudGate Web Portal **AD 識別子** フィールドに貼り付けます

        [Image: 広告ログイン]

#### Soloinsight CloudGate SSO テスト ユーザーを作成する

テスト ユーザーを作成するには、CloudGate Web ポータルのメイン メニューから **[従業員** ] を選択し、[新しい従業員の追加] フォームに入力します。 テストユーザーに割り当てられる権限レベルは **ビジネス管理者** です。すべての必須フィールドが入力されたら [ **作成** ] を選択します。

[Image: 従業員テスト]

注意

SSO では自動ユーザー プロビジョニングもサポート Soloinsight-CloudGate、自動ユーザー プロビジョニングを構成する方法の詳細 [については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/soloinsight-cloudgate-sso-provisioning-tutorial) 。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、ログイン フローを開始できる SSO サインオン URL Soloinsight-CloudGate にリダイレクトされます。
- Soloinsight-CloudGate SSO のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Soloinsight-CloudGate SSO] タイルを選択すると、このオプションは Soloinsight-CloudGate SSO サインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sonarqube-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SonarQube を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sonarqube-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SonarQube の間にシングル サインオンを構成する方法について説明します。

この記事では、SonarQube と Microsoft Entra ID を統合する方法について説明します。 SonarQube を Microsoft Entra ID を統合すると、次のことができます。

- SonarQube にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SonarQube に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SonarQube でのシングル サインオン (SSO) が有効なサブスクリプション。
- SonarQube "SAML グループ属性" を正しく設定し、アクセス許可に AzureAD グループを使用する手順 (詳細は以下で説明します)。

注

SonarQube のインストールに関するヘルプは、[オンライン ドキュメント](https://docs.sonarsource.com/sonarqube-server/latest/setup-and-upgrade/overview/)で確認できます。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SonarQube では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの SonarQube の追加

Microsoft Entra ID への SonarQube の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SonarQube を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SonarQube**」と入力します。
4. 結果のパネルから **[SonarQube]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントへのアプリケーションの追加、アプリへのユーザーとグループの追加、ロールの割り当てができるほか、SSO の構成も行うことができます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SonarQube 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、SonarQube に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SonarQube の関連ユーザーとの間にリンク関係を確立する必要があります。

SonarQube に対する Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SonarQube SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SonarQube テスト ユーザーの作成** - B.Simon の対応ユーザーを SonarQube で作成し、Microsoft Entra のユーザー表現とリンクします。
    2. \*\* SonarQube の SAML グループ属性を構成する - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SonarQube**&gt;**シングルサインオン**に移動する。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. **[SAML でシングル サインオンをセットアップします]** ページで、 **[基本的な SAML 構成]** の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[応答 URL]** ボックスに、` https://sonar.<companyspecificurl>.io/oauth2/callback/saml` のパターンを使用して URL を入力します

    b。 **[サインオン URL]** ボックスに、次のいずれかの URL を入力します。

    - **運用環境の場合**

        `https://servicessonar.corp.microsoft.com/`
    - **開発環境の場合**

        `https://servicescode-dev.westus.cloudapp.azure.com`

    注

    この値は実際の値ではありません。 実際の応答 URL で値を更新します。これについては、この記事の後半で説明します。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[証明書 (Base64)]** を見つけて、 **[ダウンロード]** を選択し、証明書をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
7. **[SonarQube のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SonarQube の SSO 構成

1. 新しい Web ブラウザー ウィンドウを開き、SonarQube 企業サイトに管理者としてサインインします。
2. [ **Administration &gt; Configuration &gt; Security** ] を選択し、 **SAML プラグイン** に移動して次の手順を実行します。
3. 次の IdP メタデータの詳細をコピーし、SonarQube プラグインの対応するテキスト フィールドに貼り付けます。

    1. IdP エンティティ ID
    2. サインイン URL
    3. X.509 証明書
4. すべての詳細を保存します。

    [Image: SAML プラグイン IDP]
5. **[SAML]** ページで、次の手順を実行します。

    [Image: Sonarqube の構成]

    ある。 **[Enabled](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/有効化)** オプションを **[yes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/はい)** に切り替えます。

    b。 **[Application ID](アプリケーション ID)** ボックスに、**sonarqube** のような名前を入力します。

    c. **[Provider Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/プロバイダー名)** ボックスに、**SAML** のような名前を入力します。

    d. **[Provider ID] (プロバイダー ID)** テキスト ボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    え **[SAML ログイン URL]** テキスト ボックスに **[ログイン URL]** の値を貼り付けます。

    f. Base 64 でエンコードされた証明書をメモ帳で開き、その内容をコピーして **[プロバイダー証明書]** テキスト ボックスに貼り付けます。

    ジー **[SAML user login attribute](SAML ユーザー ログイン属性)** ボックスに、値「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name`」を入力します。

    h. **[SAML user name attribute](SAML ユーザー名属性)** ボックスに、値「`http://schemas.microsoft.com/identity/claims/displayname`」を入力します。

    一. **[SAML user email attribute](SAML ユーザー メール属性)** ボックスに、値「`http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`」を入力します。

    j. **[保存]** を選択します。

#### SonarQube の SAML グループ属性を構成する

SAML グループ属性を設定し、SonarQube のアクセス許可に AzureAD グループを使用するには、次の手順に従います。

1. SonarQube の [管理] タブで、[構成] &gt; [セキュリティ] &gt; [SAML] に移動します
2. [SAML グループ属性] テキスト ボックスに、次の値を入力します: 'http://schemas.microsoft.com/ws/2008/06/identity/claims/role'
3. 変更を保存します
4. SonarQube で、[管理] &gt; [セキュリティ] &gt; [グループ] に移動します
5. AzureAD グループを SonarQube グループにマップします。
    - 各 AzureAD グループに対応するグループが存在しない場合は、SonarQube に作成します。
    - AzureAD と SonarQube の間でグループの名前が正確に一致していることを確認します。

#### SonarQube テスト ユーザーの作成

このセクションでは、SonarQube で B.Simon というユーザーを作成します。 [SonarQube クライアント サポート チーム](https://sonarsource.com/company/contact/)と連携して、SonarQube プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、サインイン フローを開始できる SonarQube のサインオン URL にリダイレクトされます。
- SonarQube のサインオン URL に直接移動し、そこからサインイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [SonarQube] タイルを選択すると、このオプションは SonarQube のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sosafe-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SoSafe を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sosafe-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-24
- Summary: Microsoft Entra ID から SoSafe に対してユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法を学習します。

この記事では、自動ユーザー プロビジョニングを構成するために SoSafe と Microsoft Entra ID の両方で実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID で、Microsoft Entra プロビジョニング サービスを使用して、[SoSafe](https://sosafe.de/) に対するユーザーとグループのプロビジョニングおよびプロビジョニング解除が自動的に行われます。 このサービスの機能、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用して SaaS アプリケーションへのユーザー プロビジョニングとプロビジョニング解除を自動化する」を参照してください。

### サポートされている機能

- SoSafe でユーザーを作成する。
- アクセスが不要になったら、SoSafe のユーザーを削除します。
- Microsoft Entra ID と SoSafe の間でユーザー属性の同期を維持します。
- SoSafe でグループとグループ メンバーシップをプロビジョニングする。
- SoSafe に対して[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/servicessosafe-tutorial)を行う (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-create-new-tenant)。
- 次のいずれかのロール: [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)、[クラウドアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- [SoSafe](https://sosafe.de/) テナント。
- 管理者アクセス許可がある SoSafe のユーザー アカウント。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. プロビジョニングの対象範囲にいるユーザーを決定します。
3. [Microsoft Entra ID と SoSafe の間でマップする](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)データを決定します。

### 手順 2: Microsoft Entra ID を使用したプロビジョニングをサポートするように SoSafe を構成する

1. [SoSafe 管理コンソール](https://manager.sosafe.de)にログインし、**[Extended Data](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/拡張データ) &gt; [SCIM]** タブに移動します。
2. **[Identity Provider Tenant ID (Azure, Okta, etc.)](ID プロバイダー Azure テナント ID (Azure、Okta 等))** の下に Azure テナント IDを入力して、 **[Save](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/保存)** を選択します。
3. [ **トークンの生成]** を選択します。
4. このページに表示された**テナントの URL** と**トークン**をコピーします。 これらの値は、Sosafe アプリケーションの [プロビジョニング] タブの [ **テナント URL** ] フィールドと [ **シークレット トークン** \*] フィールドに入力されます。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから SoSafe を追加する

Microsoft Entra アプリケーション ギャラリーから SoSafe を追加して、SoSafe へのプロビジョニングの管理を開始します。 SSO のために SoSafe を以前に設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされるユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: SoSafe への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、SoSafe でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で SoSafe の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズ アプリケーション ブレード]
3. アプリケーションの一覧で **[SoSafe]** を選択します。

    [Image: アプリケーションの一覧にある SoSafe のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: プロビジョニングタブ]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、SoSafe テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が SoSafe に接続できることを確認します。 接続に失敗した場合は、SoSafe アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から SoSafe に同期されるユーザー属性を確認します。 **[照合]** プロパティとして選択されている属性を使用して、SoSafe での更新処理でユーザー アカウントが照合されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、SoSafe API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | 表示名 | 糸 |  |
    | タイトル | 糸 |  |
    | emails[type eq "仕事"].value | 糸 |  |
    | 優先言語 | 糸 |  |
    | 名前.名 | 糸 |  |
    | 名前.姓 | 糸 |  |
    | 名前.整形済み | 糸 |  |
    | 名前.敬称 | 糸 |  |
    | name.honorificSuffix | 糸 |  |
    | addresses[type eq "work"].フォーマット済み | 糸 |  |
    | アドレス[タイプ eq "作業"].ストリートアドレス | 糸 |  |
    | addresses[type eq "職場"].地域 | 糸 |  |
    | アドレス[タイプが"仕事"に等しい].地域 | 糸 |  |
    | addresses[タイプ eq "work"].郵便番号 | 糸 |  |
    | アドレス[タイプ Eq "仕事"].国 | 糸 |  |
    | phoneNumbers[タイプが "職場" の場合].値 | 糸 |  |
    | エクスターナルID | 糸 |  |
    | ニックネーム | 糸 |  |
    | ユーザータイプ | 糸 |  |
    | ロケール | 糸 |  |
    | タイムゾーン | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:従業員番号 (employeeNumber) | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:コストセンター | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:ユーザー:組織 | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:division | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:enterprise:2.0:User:部門 | 糸 |  |
12. **[グループ]** を選びます。
13. **[属性マッピング]** セクションで、Microsoft Entra ID から SoSafe に同期されるグループ属性を確認します。 **[照合]** プロパティとして選択されている属性は、更新処理で SoSafe のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理でサポートされます |
    | --- | --- | --- |
    | 表示名 | 糸 | ✓ |
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
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spaceiq-provisioning-tutorial"} -->
## Microsoft Entra ID を使用した自動ユーザー プロビジョニング用に SpaceIQ を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spaceiq-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-03-24
- Summary: SpaceIQ に対してユーザー アカウントのプロビジョニングとプロビジョニング解除を自動的に実行するための Microsoft Entra ID の構成方法について説明します。

この記事の目的は、SpaceIQ に対してユーザーやグループを自動的にプロビジョニングおよびプロビジョニング解除するように Microsoft Entra ID を構成するために SpaceIQ と Microsoft Entra ID で実行する手順を示することです。

注

この記事では、Microsoft Entra ユーザー プロビジョニング サービスの上に構築されたコネクタについて説明します。 このサービスが実行する内容、しくみ、よく寄せられる質問の重要な詳細については、「[Microsoft Entra ID による SaaS アプリへのユーザー プロビジョニングとプロビジョニング解除の自動化](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)」を参照してください。

### サポートされている機能

- SpaceIQ でユーザーを作成します。
- アクセスが不要になった場合は、SpaceIQ のユーザーを削除します。
- SpaceIQ への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spaceiq-tutorial) (推奨)。
- 有効期間が長いベアラー トークン認証がサポートされています。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- [SpaceIQ テナント](https://spaceiq.com/)
- 管理者アクセス許可がある SpaceIQ のユーザー アカウント。

### 手順 1: SpaceIQ にユーザーを割り当てる

Microsoft Entra ID では、選択されたアプリへのアクセスが付与されるユーザーを決定する際に*割り当て*という概念が使用されます。 自動ユーザー プロビジョニングのコンテキストでは、Microsoft Entra ID 内のアプリケーションに割り当て済みのユーザーやグループのみが同期されます。

自動ユーザー プロビジョニングを構成して有効にする前に、SpaceIQ へのアクセスが必要な Microsoft Entra ID のユーザーやグループを決定しておく必要があります。 決定し終えたら、次の手順に従って、これらのユーザーやグループを SpaceIQ に割り当てることができます。

- [エンタープライズ アプリケーションにユーザーまたはグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)

### 手順 2: SpaceIQ へのユーザー割り当てに関する重要なヒント

- 単一の Microsoft Entra ユーザーを SpaceIQ に割り当てて、自動ユーザー プロビジョニングの構成をテストすることをお勧めします。 さらに多くのユーザーやグループは、後で割り当てることができます。
- SpaceIQ にユーザーを割り当てるときは、割り当てダイアログで、有効なアプリケーション固有ロール (使用可能な場合) を選択する必要があります。 **既定のアクセス** ロールのユーザーは、プロビジョニングから除外されます。

### 手順 3: プロビジョニング用に SpaceIQ を設定する

1. [SpaceIQ 管理コンソール](https://main.spaceiq.com/login/)にサインインします。 画面の右上隅にあるドロップダウン メニューからそれを選択して、**[設定]** に移動します。

    [Image: SpaceIQ 管理コンソール]
2. **[設定]** ページで、 **[Third Party Integrations](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/サードパーティの統合)** を選択します。

    [Image: SpaceIQ での SCIM の追加]
3. **[Provisioning and SSO](プロビジョニングと SSO)** タブに移動します。**Azure** タイルを検索します。 [**を選択し、**をアクティブ化します。]

    [Image: SpaceIQ プロビジョニングおよび SSO]

    [Image: SpaceIQ による Azure のアクティブ化]
4. **SCIM ベアラー トークン**をコピーします。 この値が、SpaceIQ アプリケーションの [プロビジョニング] タブ内の [シークレット トークン] フィールドに入力されます。 **[アクティブ化] を選択する**

    [Image: SpaceIQ でのトークンの作成]

### 手順 4: ギャラリーから SpaceIQ を追加する

Microsoft Entra ID で自動ユーザー プロビジョニング用に SpaceIQ を構成する前に、SpaceIQ を Microsoft Entra アプリケーション ギャラリーから管理対象 SaaS アプリケーションの一覧に追加する必要があります。

**Microsoft Entra アプリケーション ギャラリーから SpaceIQ を追加するには、以下の手順を行います。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SpaceIQ**」と入力し、**[SpaceIQ]** を選びます。
4. 結果のパネルから **[SpaceIQ]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。 [Image: 結果一覧の SpaceIQ]

### 手順 5: SpaceIQ への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra ID でのユーザー割り当てやグループ割り当てに基づいて、SpaceIQ でユーザーが作成、更新、無効化されるように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

ヒント

SpaceIQ のシングル サインオンに関する記事で説明されている手順に従って、SpaceIQ で SAML ベースの [シングル サインオンを有効に](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spaceiq-tutorial)することもできます。 シングル サインオンは自動ユーザー プロビジョニングとは独立に構成できますが、これらの 2 つの機能は互いに補完しあいます。

#### Microsoft Entra ID で SpaceIQ の自動ユーザー プロビジョニングを構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**企業向けアプリケーション**を参照する

    [Image: エンタープライズアプリケーションブレード]
3. アプリケーションの一覧で **[SpaceIQ]** を選択します。

    [Image: アプリケーションの一覧の SpaceIQ のリンク]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] オプションが強調表示された [管理] オプションのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] タブの [自動] のスクリーンショット。]
6. [ **テナント URL** ] フィールドに、SpaceIQ テナント URL とシークレット トークンを入力します。 [ **テスト接続]** を選択して、Microsoft Entra ID が SpaceIQ に接続できることを確認します。 接続に失敗した場合は、SpaceIQ アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫メールを受信する電子メールを提供します。 誤削除防止を有効にします。 **[適用]** を選択して変更を保存します。

    [Image: プロビジョニングプロパティのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **[属性マッピング]** セクションで、Microsoft Entra ID から SpaceIQ に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で SpaceIQ のユーザー アカウントとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

    [Image: SpaceIQ ユーザーの属性]
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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spaceiq-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SpaceIQ を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spaceiq-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SpaceIQ 間にシングル サインオンを構成する方法について学習します。

この記事では、SpaceIQ と Microsoft Entra ID を統合する方法について説明します。 SpaceIQ を Microsoft Entra ID と統合すると、次のことができます。

- SpaceIQ にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SpaceIQ に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SpaceIQ でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SpaceIQ では、**IDP** Initiated SSO がサポートされます。
- SpaceIQ では、[自動化されたユーザー プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spaceiq-provisioning-tutorial)がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーから SpaceIQ を追加する

Microsoft Entra ID への SpaceIQ の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに SpaceIQ を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SpaceIQ**」と入力します。
4. 結果のパネルから **[SpaceIQ]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SpaceIQ 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SpaceIQ で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SpaceIQ の関連ユーザーとの間にリンク関係を確立する必要があります。

SpaceIQ で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SpaceIQ の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SpaceIQ テストユーザーを作成する** - Microsoft Entra の B.Simon にリンクする SpaceIQ の対応ユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SpaceIQ**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://api.spaceiq.com`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.spaceiq.com/saml/<INSTANCE_ID>/callback`

    注

    これらの値は、記事の後半で説明する実際の応答 URL と識別子で更新します。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SpaceIQ の設定]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SpaceIQ SSO の構成

1. 新しい Web ブラウザー ウィンドウを開き、SpaceIQ 環境に管理者としてサインインします。
2. ログインしたら、右上にあるパズル記号を選択し、[**統合**] を選択します。

    [Image: アカウント設定]
3. [ **すべてのプロビジョニングと SSO**] で、 **Azure** タイルを選択して Azure のインスタンスを IDP として追加します。

    [Image: [SAML] アイコン]
4. **[SSO]** ダイアログ ボックスで、次の手順に従います。

    [Image: SAML 認証設定]

    ある。 **[SAML 発行者 URL]** ボックスに、Microsoft Entra のアプリケーション構成ウィンドウからコピーした **[Microsoft Entra 識別子]** の値を貼り付けます。

    b。 **[SAML CallBack Endpoint URL (read-only)] (SAML コールバック エンドポイント URL (読み取り専用))** の値をコピーし、**[基本的な SAML 構成]** セクションの **[応答 URL]** ボックスに貼り付けます。

    c. **[SAML Audience URI (read-only)] (SAML オーディエンス URL (読み取り専用))** の値をコピーし、**[基本的な SAML 構成]** セクションの **[識別子]** ボックスに貼り付けます。

    d. ダウンロードした証明書ファイルをメモ帳で開き、その内容をコピーし、**[X.509 Certificate](X.509 証明書)** ボックスに貼り付けます。

    え **保存** を選択します。

#### SpaceIQ テスト ユーザーを作成する

このセクションでは、SpaceIQ で Britta Simon というユーザーを作成します。 [SpaceIQ サポート チーム](mailto:eng@spaceiq.com) と協力して、SpaceIQ プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

SpaceIQ では、自動ユーザー プロビジョニングもサポートされます。自動ユーザー プロビジョニングの構成方法について詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spaceiq-provisioning-tutorial)をご覧ください。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SpaceIQ に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SpaceIQ] タイルを選択すると、SSO を設定した SpaceIQ に自動的にサインインします。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spacio-tutorial"} -->
## Microsoft Entra ID で Spacio for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spacio-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Spacio 間にシングル サインオンを構成する方法について学習します。

この記事では、Spacio と Microsoft Entra ID を統合する方法について説明します。 Spacio を Microsoft Entra ID と統合すると、次のような利点が得られます。

- Spacio にアクセスできるユーザーを Microsoft Entra ID で制御できます。
- ユーザーが自分の Microsoft Entra アカウントで Spacio に自動的にサインイン (シングル サインオン) するように設定できます。
- アカウントは 1 か所で管理できます。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。 Azure サブスクリプションをお持ちでない場合は、始める前に[無料アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Spacio でのシングル サインオンが有効なサブスクリプション

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Spacio では、**SP** によって開始される SSO がサポートされます

### ギャラリーからの Spacio の追加

Microsoft Entra ID への Spacio の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Spacio を追加する必要があります。

**ギャラリーから Spacio を追加するには、次の手順を実行します。**

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. 検索ボックスに「 **Spacio」**と入力し、結果パネルで **Spacio** を選択し、[ **追加** ] ボタンを選択してアプリケーションを追加します。

    [Image: 結果一覧の Spacio]

### Microsoft Entra のシングル サインオンの構成とテスト

このセクションでは、**Britta Simon** というテスト ユーザーに基づいて、Spacio で Microsoft Entra のシングル サインオンを構成してテストします。 シングル サインオンを機能させるには、Microsoft Entra ユーザーと Spacio の関連ユーザーとの間にリンク関係が確立されている必要があります。

Spacio で Microsoft Entra のシングル サインオンを構成してテストするには、次の構成要素を完了する必要があります。

1. ユーザーがこの機能を使用できるように**、Microsoft Entra シングル サインオンを構成**します。
2. **Spacio シングル サインオンの構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
4. **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
5. **Spacio テスト ユーザーを作成** - Microsoft Entra にリンクした、Spacio 内での Britta Simon に対応するユーザーを作成します。
6. **シングル サインオンのテスト** - 構成が機能するかどうかを確認します。

#### Microsoft Entra シングル サインオンの構成

このセクションでは、Microsoft Entra のシングル サインオンを有効にします。

Spacio で Microsoft Entra シングル サインオンを構成するには、次の手順を行います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Spacio** アプリケーション統合ページに移動し、[**シングル サインオン**] を選択します。

    [Image: シングル サインオン リンク] を構成する
3. [ **シングル サインオン方法の選択** ] ダイアログで、 **SAML/WS-Fed** モードを選択してシングル サインオンを有効にします。

    [Image: シングル サインオン選択モード]
4. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **編集]** アイコンを選択して [ **基本的な SAML 構成]** ダイアログを開きます。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [Image: [Spacio のドメインと URL] のシングル サインオン情報]

    ある。 [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.spac.io/<brokerageID>`

    b。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://sso.spac.io/<brokerageID>`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 この値を取得するには、[Spacio クライアント サポート チーム](mailto:support@spac.io)にお問い合わせください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Spacio シングル サインオンの構成

**Spacio** 側にシングル サインオンを構成するには、作成された**アプリケーション フェデレーション メタデータ URL** を [Spacio サポート チーム](mailto:support@spac.io)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

#### Spacio テスト ユーザーを作成する

このセクションでは、Spacio で Britta Simon というユーザーを作成します。 [Spacio サポート チーム](mailto:support@spac.io)と連携し、Spacio プラットフォームにユーザーを追加してください。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

#### シングル サインオンのテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Spacio] タイルを選択すると、SSO を設定した Spacio に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spectrumu-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SpectrumU を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spectrumu-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SpectrumU の間にシングル サインオンを構成する方法について学習します。

この記事では、SpectrumU と Microsoft Entra ID を統合する方法について説明します。 SpectrumU を Microsoft Entra ID と統合すると、次のことができます。

- SpectrumU にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SpectrumU に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SpectrumU でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SpectrumU では、**SP** Initiated SSO がサポートされます。
- SpectrumU では、**Just-In-Time** ユーザー プロビジョニングがサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの SpectrumU の追加

Microsoft Entra ID への SpectrumU の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に SpectrumU を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**SpectrumU**」と入力します。
4. 結果のパネルから **[SpectrumU]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SpectrumU 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SpectrumU で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SpectrumU の関連ユーザーとの間にリンク関係を確立する必要があります。

SpectrumU に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SpectrumU SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SpectrumU テスト ユーザーの作成 - SpectrumU** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**SpectrumU**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://watch.spectrum.net/domainsearch/`
6. SpectrumU アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
7. その他に、SpectrumU アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらを次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | extraUserName | ユーザー.社員ID |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
9. **[SpectrumU のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SpectrumU SSO の構成

**SpectrumU** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と、アプリケーション構成からコピーした適切な URL を SpectrumU サポート チームに送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SpectrumU のテスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを SpectrumU に作成します。 SpectrumU では、Just-In-Time ユーザー プロビジョニングがサポートされており、既定で有効になっています。 このセクションにはアクション項目はありません。 SpectrumU にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SpectrumU サインオン URL にリダイレクトされます。
- SpectrumU のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SpectrumU] タイルを選択すると、このオプションは SpectrumU のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spedtrack-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SpedTrack を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spedtrack-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SpedTrack の間にシングル サインオンを構成する方法について学習します。

この記事では、SpedTrack と Microsoft Entra ID を統合する方法について説明します。 SpedTrack は、各学区がその特殊教育サービス (Special Services) 部門を管理するための包括的な Web ベースのソリューションを提供します。 SpedTrack と Microsoft Entra ID を統合すると、次のことができます。

- SpedTrack にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して SpedTrack に自動的にサインインできるようにする。

テスト環境で SpedTrack 向けの Microsoft Entra のシングル サインオンを構成してテストします。 SpedTrack では、**SP**および**IDP**によるシングルサインオンを両方サポートします。

### [前提条件]

Microsoft Entra ID を SpedTrack と統合するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者、[クラウド アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)、または[アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)のいずれかのロール。
- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、 [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- シングル サインオン (SSO) が有効な SpedTrack のサブスクリプション。

### アプリケーションを追加してテスト ユーザーを割り当てる

シングル サインオンの構成プロセスを開始する前に、Microsoft Entra ギャラリーから SpedTrack アプリケーションを追加する必要があります。 テナント内のユーザーをアプリケーションに割り当てる必要があります。 このテスト ユーザーは SpedTrack 内にも存在する必要があります。

#### Microsoft Entra ギャラリーから SpedTrack を追加する

Microsoft Entra アプリケーション ギャラリーから SpedTrack を追加して、SpedTrack とのシングル サインオンを構成します。 ギャラリーからアプリケーションを追加する方法の詳細については、「 [クイック スタート: ギャラリーからアプリケーションを追加する」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

#### Microsoft Entra テスト ユーザーを割り当てる

ユーザー [アカウントの作成と割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) に関する記事のガイドラインに従って、テスト ユーザー アカウントを作成します。 一致する電子メールを使用して、このユーザーを SpedTrack 内にも作成する必要があります。

### Microsoft Entra SSO の構成

Microsoft Entra のシングル サインオンを有効にするには、次の手順を実行します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. ここで **Entra ID**&gt;**Enterprise apps**&gt;**SpedTrack**&gt;**シングルサインオン** に移動してください。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **基本的な SAML 構成]** セクションで、 **サービス プロバイダー メタデータ ファイル** がある場合は、次の手順を実行します。

    ある。 [ **メタデータ ファイルのアップロード]** を選択します。

    [Image: メタデータ ファイルをアップロードする方法を示すスクリーンショット。]

    b。 **フォルダー ロゴ**を選択してメタデータ ファイルを選択し、[**アップロード**] を選択します。

    [Image: メタデータ ファイルを選択して参照する方法を示すスクリーンショット。]

    c. メタデータ ファイルが正常にアップロードされると、[基本的な SAML 構成] セクションに **識別子** と **応答 URL** の値が自動的に設定されます。

    d. [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.spedtrack.com/Login.aspx`
5. 必要に応じて、鉛筆アイコンを選択して、[ **基本的な SAML 構成** ] セクションの SpedTrack からコピーした値を手動で入力します。

    ある。 [ **識別子** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.spedtrack.com`

    b。 [ **応答 URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.spedtrack.com/SSO/AssertionConsumerService.aspx`
6. **SP** Initiated SSO を構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.spedtrack.com/Login.aspx`
7. [ **SAML でのシングル サインオンのセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]

### SpedTrack SSO を構成する

1. SpedTrack 企業サイトに管理者としてログインします。
2. **Admin &gt; District Setup &gt; Single Sign-On**に移動します。
3. [**構成の編集]** を選択し、**IdP プロバイダー**として **[Azure**] を選択します。
4. SP メタデータ ファイルをダウンロードするか、識別子、応答 URL、サインオン URL、ログアウト URL の値をコピーします。
5. [ **メタデータのアップロード]** を選択して、ダウンロードした **フェデレーション メタデータ XML** ファイルをアップロードします。
6. ファイルをアップロードした後、SpedTrack 内に変更を**保存します**。

#### SpedTrack テスト ユーザーを作成する

このセクションでは、SpedTrack で Britta Simon というユーザーを作成します。 [SpedTrack サポート チーム](mailto:support@spedtrack.com)と協力して、SpedTrack プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- テスト対象のユーザーがアプリケーションにアクセスでき、SpedTrack 内に存在することを確認します。
- SpedTrack 内で、[ **Admin &gt; District Setup &gt; Single Sign-On]** に移動します。 [ **テスト構成] を選択します**。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した SpedTrack に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SpedTrack] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SpedTrack に自動的にサインインされます。 詳細については、「 [Microsoft Entra My Apps](https://learn.microsoft.com/ja-jp/azure/active-directory/manage-apps/end-user-experiences#azure-ad-my-apps)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/speexx-tutorial"} -->
## Microsoft Entra ID で Speexx for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/speexx-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Speexx の間でシングル サインオンを構成する方法について説明します。

この記事では、Speexx と Microsoft Entra ID を統合する方法について説明します。 Speexx と Microsoft Entra ID を統合すると、次のことができます。

- Speexx にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Speexx に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### 前提 条件

開始するには、次のものが必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションをお持ちでない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Speexx でのシングル サインオン (SSO) が有効なサブスクリプション。
- アプリケーション管理者は、クラウド アプリケーション管理者と共に、Microsoft Entra ID でアプリケーションを追加または管理することもできます。 詳細については、Azure 組み込みロール に関するページを参照してください。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Speexx は、**の SP** 発の SSO をサポートしています。
- Speexx では、**Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーからの Speexx の追加

Microsoft Entra ID への Speexx の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Speexx を追加する必要があります。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. [ギャラリー **から追加する**] セクションで、検索ボックスに「Speexx  入力します。
4. 結果パネルから **Speexx** を選択し、それからアプリを追加します。 アプリがテナントに追加されるまで数秒待ちます。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Speexx の Microsoft Entra SSO の構成とテスト

**B.Simon**というテスト ユーザーを使用して、Speexx に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Speexx の関連ユーザーとの間にリンク関係を確立する必要があります。

Speexx に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO**の構成 - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Speexx SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Speexx テストユーザーの作成** - B.Simon に対応するユーザーを Speexx に作成し、それを Microsoft Entra の B.Simon にリンクさせます。
3. **SSO** のテスト - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. 少なくとも [クラウド アプリケーション管理者](https://entra.microsoft.com)として、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Speexx**&gt;**シングルサインオン**にアクセスします。
3. [**シングル サインオン方法の選択] ページ** で、[**SAML**] を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集]
5. **基本的な SAML 構成** セクションで、次の手順を実行します。

    1. [**識別子**] テキスト ボックスに、次のパターンを使用して URL を入力します: `https://portal.speexx.com/auth/saml/<customername>`
    2. [**応答 URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://portal.speexx.com/auth/saml/<customername>/back`
    3. [**サインオン URL** テキスト ボックスに、次のパターンを使用して URL を入力します: `https://portal.speexx.com/auth/saml/<customername>`

    手記

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値 [取得するには、Speexx クライアント サポート チーム](mailto:support@speexx.com) にお問い合わせください。 「**基本的な SAML 構成**」セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Speexx SSO の構成

Speexx **側** シングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL** を Speexx サポート チーム 送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Speexx テスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Speexx に作成します。 Speexx では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Speexx にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Speexx サインオン URL にリダイレクトされます。
- Speexx のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Speexx] タイルを選択すると、このオプションは Speexx のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリ 概要」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spintr-sso-tutorial"} -->
## Microsoft Entra ID で Spintr SSO for Single sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spintr-sso-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Spintr SSO の間にシングル サインオンを構成する方法について学習します。

この記事では、Spintr SSO と Microsoft Entra ID を統合する方法について説明します。 Spintr SSO を Microsoft Entra ID と統合すると、次のことができます。

- Spintr SSO にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Spintr SSO に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

SaaS アプリと Microsoft Entra ID の統合の詳細については、「 [Microsoft Entra ID を使用したアプリケーション アクセスとシングル サインオンとは」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を参照してください。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Spintr SSO でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Spintr SSO では、**SP** Initiated SSO がサポートされます
- Spintr SSO では、**Just-In-Time** ユーザー プロビジョニングがサポートされます

### ギャラリーからの Spintr SSO の追加

Microsoft Entra ID への Spintr SSO の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Spintr SSO を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Spintr SSO**」と入力します。
4. 結果のパネルから **[Spintr SSO]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Spintr SSO の Microsoft Entra シングル サインオンの構成とテスト

**B.Simon** というテスト ユーザーを使用して、Spintr SSO で Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Spintr SSO の関連ユーザーとの間にリンク関係を確立する必要があります。

Spintr SSO に対する Microsoft Entra SSO を構成してテストするには、次の構成要素を完了します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Spintr SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    - **Spintr SSO テスト ユーザーの作成 - Spintr SSO** で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Spintr SSO**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、次のフィールドの値を入力します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://signin.spintr.me`
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **証明書 (Base64)** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Spintr SSO のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Spintr SSO の構成

**Spintr SSO** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (Base64)** と、アプリケーション構成からコピーした適切な URL を [Spintr SSO サポート チーム](mailto:support@spintr.me)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Spintr SSO のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Spintr SSO に作成します。 Spintr SSO では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Spintr SSO にユーザーがまだ存在していない場合は、認証後に新規に作成されます。

### SSO のテスト

このセクションでは、アクセス パネルを使用して Microsoft Entra のシングル サインオン構成をテストします。

アクセス パネルで [Spintr SSO] タイルを選択すると、SSO を設定した Spintr SSO に自動的にサインインします。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/splan-visitor-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Splan Visitor を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/splan-visitor-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-06-11
- Summary: Microsoft Entra ID と Splan Visitor の間でシングル サインオンを構成する方法について説明します。

この記事では、Splan Visitor と Microsoft Entra ID を統合する方法について説明します。 Splan Visitor と Microsoft Entra ID を統合すると、次のことができます。

- Microsoft Entra ID を使用して、Splan Visitor にアクセスできるユーザーを制御します。
- ユーザーが Microsoft Entra アカウントを使用して Splan Visitor に自動的にサインインできるようにします。
- 1 つの中央の場所 (Azure portal) でアカウントを管理します。

Splan Visitor は、次の [国内クラウド デプロイ](https://learn.microsoft.com/ja-jp/graph/deployments)で利用できます。

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

- Splan Visitor でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Splan Visitor では、IdP によって開始される SSO がサポートされます。

### ギャラリーから Splan Visitor を追加する

Microsoft Entra ID への Splan Visitor の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Splan Visitor を追加します。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックスに**「Splan Visitor**」と入力します。
4. 結果パネルから **Splan Visitor** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Splan Visitor の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Splan Visitor に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Splan Visitor の関連ユーザーとの間にリンク関係を確立する必要があります。

Splan Visitor に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**して、ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーを作成** して、テスト ユーザー B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **B.Simon が Microsoft Entra シングル サインオンを使用できるようにするには、Microsoft Entra テスト ユーザーを割り当てます** 。
2. **Splan Visitor の SSO を構成**して、Splan Visitor でシングル サインオン設定を構成します。
    1. **Splan Visitor にテスト ユーザーを作成**し、Splan Visitor 内で B.Simon に対応するユーザーを設定します。このユーザーは、Microsoft Entra 上のユーザーとリンクされている必要があります。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Azure portal で、次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. ブラウザで **Entra ID**&gt;**Enterprise アプリ**&gt;**Splan Visitor**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [**SAML を使用した単一 Sign-On のセットアップ**] ページで、[**基本的な SAML 構成**] の**鉛筆**アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成の編集/ペン アイコンが強調表示されているスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションでは、アプリケーションが事前に構成されており、必要な URL が Azure に事前設定されています。 [ **保存** ] ボタンを選択して構成を保存します。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **フェデレーション メタデータ XML**] を見つけます。 [ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: フェデレーション メタデータ XML ダウンロード リンクが強調表示されているスクリーンショット。]
7. [ **Splan Visitor のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: [構成 URL] セクションが強調表示されているスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Splan Visitor の SSO の構成

Splan Visitor でシングル サインオンを構成するには、ダウンロードした **フェデレーション メタデータ XML** とコピーした適切な URL を [Splan Visitor サポート チーム](mailto:support@splan.com)に送信します。 これにより、SAML SSO 接続が両方の側で正しく設定されます。

#### Splan Visitor テスト ユーザーの作成

Splan Visitor で **Britta Simon** という名前のテスト ユーザーを作成します。 [Splan Visitor サポート チーム](mailto:support@splan.com)と協力して、Splan Visitor にユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

次のいずれかのオプションを使用して、Microsoft Entra のシングル サインオン構成をテストします。

- **Azure portal**: **[このアプリケーションをテスト** する] を選択して、SSO を設定した Splan Visitor に自動的にサインインします。
- **Microsoft マイ アプリ ポータル**: **[Splan Visitor** ] タイルを選択すると、SSO を設定した Splan Visitor に自動的にサインインします。 マイ アプリ ポータルの詳細については、「[マイ アプリ ポータルからアプリにサインインして開始する](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/splashtop-provisioning-tutorial"} -->
## Microsoft Entra IDを使用した自動ユーザー プロビジョニング用に Splashtop を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/splashtop-provisioning-tutorial
- Service: entra-id / saas-apps
- Article date: 2026-04-13
- Summary: Microsoft Entra IDから Splashtop にユーザー アカウントを自動的にプロビジョニングおよびプロビジョニング解除する方法について説明します。

この記事では、Splashtop と Microsoft Entra ID の両方で自動ユーザー プロビジョニングを構成するために実行する必要がある手順について説明します。 構成すると、Microsoft Entra ID Microsoft Entra プロビジョニング サービスを使用して、[Splashtop](https://www.splashtop.com/) にユーザーとグループを自動的にプロビジョニングおよびプロビジョニング解除します。 このサービスの機能、しくみ、よく寄せられる質問の詳細については、「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning) を使用した SaaS アプリケーションへの自動ユーザー プロビジョニングとプロビジョニング解除」を参照してください。

### サポートされている機能

- Splashtop でユーザーを作成する
- アクセスが不要になった場合に Splashtop でユーザーを削除する
- Microsoft Entra IDと Splashtop の間でユーザー属性の同期を維持する
- Splashtop でグループとグループ メンバーシップをプロビジョニングする
- Splashtop への[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/splashtop-tutorial) (推奨)
- 有効期間が長いベアラー トークン認証がサポートされています。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- - アクティブなサブスクリプションを持つMicrosoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
    - 次のいずれかのロール:
        - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
        - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
        - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。
- SSO がサポートされている Splashtop チーム。 この[連絡先フォーム](https://marketing.splashtop.com/acton/fs/blocks/showLandingPage/a/3744/p/p-0095/t/page/fm/0)に記入して、SSO 機能を試用するか、サブスクライブしてください。

### 手順 1: プロビジョニングデプロイメントを計画する

1. [プロビジョニング サービスのしくみ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を確認します。
2. [プロビジョニングのスコープ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)に含めるユーザーを決定しまする。
3. Microsoft Entra IDとSplashtopの間でマッピングするデータを決定します。

### 手順 2: Microsoft Entra IDでのプロビジョニングをサポートするように Splashtop を構成する

1. Splashtop Web ポータルで新しい [SSO メソッド](https://support-splashtopbusiness.splashtop.com/hc/articles/360038280751-How-to-apply-for-a-new-SSO-method-)を申請します。
2. Splashtop Web ポータルで、[API トークン](https://support-splashtopbusiness.splashtop.com/hc/articles/360046055352-How-to-generate-the-SCIM-provisioning-token-)を生成して、Microsoft Entra IDでプロビジョニングを構成します。

### 手順 3: Microsoft Entra アプリケーション ギャラリーから Splashtop を追加する

Microsoft Entra アプリケーション ギャラリーから Splashtop を追加して、Splashtop へのプロビジョニングの管理を開始します。 SSO のために以前 Splashtop を設定している場合は、同じアプリケーションを使用できます。 ただし、最初に統合をテストするときは、別のアプリを作成することをお勧めします。 ギャラリー [からアプリケーションを追加する方法の詳細については、](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)を参照してください。

### 手順 4: プロビジョニングのスコープに含まれるユーザーを定義する

Microsoft Entra プロビジョニング サービスを使用すると、アプリケーションへの割り当てに基づいて、またはユーザーまたはグループの属性に基づいてプロビジョニングされたユーザーをスコープできます。 割り当てに基づいてアプリにプロビジョニングされるユーザーのスコープを設定する場合は、 [手順を使用してユーザーとグループをアプリケーションに割り当てることができます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)。 ユーザーまたはグループの属性のみに基づいてプロビジョニングする対象を決定する場合、[スコープフィルター](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)を使用できます。

- 小規模から始めます。 すべてのユーザーとグループにロールアウトする前に、少数のユーザーとグループでテストします。 プロビジョニングのスコープが割り当てられたユーザーとグループに設定されている場合は、1 つまたは 2 つのユーザーまたはグループをアプリに割り当てることで、これを制御できます。 スコープがすべてのユーザーとグループに設定されている場合は、 [属性ベースのスコープ フィルターを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/define-conditional-rules-for-provisioning-user-accounts)指定できます。
- 追加のロールが必要な場合は、 [アプリケーション マニフェストを更新](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps) して新しいロールを追加できます。

### 手順 5: Splashtop への自動ユーザー プロビジョニングを構成する

このセクションでは、Microsoft Entra IDのユーザーとグループの割り当てに基づいて TestApp でユーザーとグループを作成、更新、無効化するように Microsoft Entra プロビジョニング サービスを構成する手順について説明します。

#### Microsoft Entra ID で Splashtop の自動ユーザー プロビジョニングを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**に移動します。

    [Image: [エンタープライズ アプリケーション] ブレードのスクリーンショット。]
3. アプリケーションの一覧で **[Splashtop]** を選択します。

    [Image: アプリケーションの一覧の Splashtop リンクのスクリーンショット。]
4. **[プロビジョニング]** タブを選択します。

    [Image: [プロビジョニング] タブのスクリーンショット。]
5. [ **+ 新しい構成**] を選択します。

    [Image: [プロビジョニング] ページの [新しい構成] オプションのスクリーンショット。]
6. [ **テナント URL** ] フィールドに、Splashtop テナント URL とシークレット トークンを入力します。 [**Test Connection** を選択して、Microsoft Entra IDが Splashtop に接続できることを確認します。 接続に失敗した場合は、Splashtop アカウントに必要な管理者アクセス許可があることを確認してから、もう一度やり直してください。

    [Image: プロビジョニング テスト接続のスクリーンショット。]
7. [ **作成]** を選択して構成を作成します。
8. [**概要**] ページで **[プロパティ**] を選択します。
9. **[編集**] アイコンを選択してプロパティを編集します。 通知メールを有効にし、検疫通知を受信する電子メールを提供します。 **誤削除防止を**有効にします。 **[適用]** を選択して変更を保存します。

    [Image: [プロビジョニングのプロパティ] ページのスクリーンショット。]
10. 左側のパネルで **[属性マッピング** ] を選択し、ユーザーを選択 **します**。
11. **Attribute-Mapping** セクションで、Microsoft Entra IDから Splashtop に同期されるユーザー属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Splashtop のユーザー アカウントとの照合に使用されます。 [一致するターゲット属性](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/customize-application-attributes)を変更する場合は、Splashtop API がその属性に基づくユーザーのフィルター処理をサポートしていることを確認する必要があります。 **[保存]** ボタンをクリックして変更をコミットします。

    | 特性 | タイプ | フィルター処理のサポート |
    | --- | --- | --- |
    | ユーザー名 | 糸 | ✓ |
    | 活動中 | ブール値 |  |
    | displayName | 糸 |  |
    | name.givenName | 糸 |  |
    | name.familyName | 糸 |  |
    | externalId | 糸 |  |
    | urn:ietf:params:scim:schemas:extension:Splashtop:2.0:User:ssoName | 糸 |  |
12. **[グループ]** を選びます。
13. **Attribute-Mapping** セクションで、Microsoft Entra IDから Splashtop に同期されるグループ属性を確認します。 **[Matching](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/照合)** プロパティとして選択されている属性は、更新処理で Splashtop のグループとの照合に使用されます。 **[保存]** ボタンをクリックして変更をコミットします。

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

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/splashtop-secure-workspace-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Splashtop Secure Workspace を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/splashtop-secure-workspace-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Splashtop Secure Workspace の間でシングル サインオンを構成する方法について説明します。

この記事では、Splashtop Secure Workspace と Microsoft Entra ID を統合する方法について説明します。 Splashtop Secure Workspace と Microsoft Entra ID を統合すると、次のことができます。

- Splashtop Secure Workspace にアクセスできる Microsoft Entra ID を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して Splashtop Secure Workspace に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Splashtop Secure Workspace でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Splashtop Secure Workspace では、 **SP** によって開始される SSO がサポートされます。
- Splashtop Secure Workspace では、 **Just In Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから Splashtop Secure Workspace を追加する

Microsoft Entra ID への Splashtop Secure Workspace の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Splashtop Secure Workspace を追加する必要があります。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**New アプリケーション**に移動します。
3. **[ギャラリーから追加**する] セクションで、検索ボックス**に「Splashtop Secure Workspace**」と入力します。
4. 結果パネルから **Splashtop Secure Workspace** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細を確認します。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Splashtop Secure Workspace の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Splashtop Secure Workspace に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Splashtop Secure Workspace の関連ユーザーとの間にリンク関係を確立する必要があります。

Splashtop Secure Workspace に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成**する - ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Splashtop Secure Workspace の SSO**の構成 - アプリケーション側でシングル サインオン設定を構成します。
    1. **Splashtop Secure Workspace のテスト ユーザーの作成** - Splashtop Secure Workspace で B.Simon に対応するユーザーを作成し、Microsoft Entra の B.Simon にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Splashtop Secure Workspace**&gt;**シングルサインオン**をブラウズします。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する方法を示すスクリーンショット。]
5. [ **基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ORG.ORG_NAME>.us.ssw.splashtop.com/realms/<ORG.ENTITY_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ORG.ORG_NAME>.us.ssw.splashtop.com/realms/<ORG.ORG_NAME>/broker/<ORG.ENTITY_ID>/endpoint`

    c. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<ORG.ORG_NAME>.us.ssw.splashtop.com`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、サインオン URL でこれらの値を更新します。 これらの値を取得するには [、Splashtop Secure Workspace サポート チーム](mailto:support-ssw@splashtop.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: [証明書のダウンロード] リンクを示すスクリーンショット。]
7. [ **Splashtop Secure Workspace のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーするスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Splashtop Secure Workspace の SSO の構成

**Splashtop Secure Workspace** 側でシングル サインオンを構成するには、ダウンロードした**フェデレーション メタデータ XML** と Microsoft Entra 管理センターからコピーした適切な URL を [Splashtop Secure Workspace サポート チーム](mailto:support-ssw@splashtop.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### Splashtop Secure Workspace のテスト ユーザーの作成

このセクションでは、B.Simon というユーザーを Splashtop Secure Workspace に作成します。 Splashtop Secure Workspace では、Just-In-Time プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 Splashtop Secure Workspace にユーザーがまだ存在していない場合は、Splashtop Secure Workspace にアクセスしようとしたときに新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる Splashtop Secure Workspace のサインオン URL にリダイレクトされます。
- Splashtop Secure Workspace のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Splashtop Secure Workspace] タイルを選択すると、このオプションは Splashtop Secure Workspace のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/splashtop-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Splashtop を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/splashtop-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Splashtop の間にシングル サインオンを構成する方法について学習します。

この記事では、Splashtop と Microsoft Entra ID を統合する方法について説明します。 Splashtop を Microsoft Entra ID と統合すると、次のことができます。

- Splashtop にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Splashtop に自動的にサインインできるようにする。
- 1 つの場所でアカウントを管理します。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Splashtop でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Splashtop では、**SP** Initiated SSO がサポートされます。
- Splashtop では、[**自動化された**ユーザー プロビジョニングとプロビジョニング解除](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/splashtop-provisioning-tutorial) (推奨) がサポートされます。

注意

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Splashtop の追加

Microsoft Entra ID への Splashtop の統合を構成するには、ギャラリーからマネージド SaaS アプリのリストに Splashtop を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに、「**Splashtop**」と入力します。
4. 結果パネルで **[Splashtop]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Splashtop 用の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、Splashtop で Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Splashtop の関連ユーザーとの間にリンク関係を確立する必要があります。

Splashtop で Microsoft Entra SSO を構成してテストするには、次の手順を行います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Splashtop の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Splashtop テスト ユーザーの作成** - Microsoft Entra のユーザー表現にリンクされた、Splashtop で B.Simon に相当するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Splashtop**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンのセットアップ** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    **[サインオン URL]** テキスト ボックスに、URL として「`https://my.splashtop.com/login/sso`」と入力します。
6. Splashtop アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。ここで、**nameidentifier** は **user.userprincipalname** にマップされています。 TicketManager アプリケーションでは **、nameidentifier** が **user.mail** にマップされることを想定しているため、[ **編集** ] アイコンを選択して属性マッピングを編集し、属性マッピングを変更する必要があります。

    [Image: このスクリーンショットは、[編集] アイコンが選択された状態の [User Attributes](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー属性) を示しています。]
7. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、**[証明書 (Base64)]** を見つけます。**[ダウンロード]** を選択して証明書をダウンロードし、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロードのリンク]
8. **[Splashtop のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Splashtop の SSO の構成

このセクションでは、 [Splashtop Web ポータル](https://my.splashtop.com/login)から新しい SSO メソッドを適用する必要があります。

1. Splashtop Web ポータルで、 **[Account info](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/アカウント情報)** / **[Team](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/チーム)** タブに移動し、下へスクロールして **[Single Sign On](シングル サインオン)** セクションを見つけます。 次に、[ **新しい SSO 方法に適用**] を選択します。

    [Image: [Single Sign On](シングル サインオン) ページのスクリーンショット。ここで [Apply for new S S O method](新しい S S O 方法を申請する) を選択できます。]
2. **[SSO による方法の申請]** ウィンドウで、**SSO 名** (例: *New Azure*) を指定します。
3. [IDP の種類] として **[Azure]** を選択し、Azure portal の Splashtop アプリケーションからコピーした **[ログイン URL]** と **[Microsoft Entra 識別子]** を入力します。
4. 証明書情報については、Azure portal で Splashtop アプリケーションからダウンロードした証明書ファイルを右選択し、メモ帳で編集し、内容をコピーして、証明書の **ダウンロード (Base64)** フィールドに貼り付けます。

    [Image: 証明書ファイルを選択してメモ帳で開く画面のスクリーンショット。][Image: 証明書ファイルの内容を示すスクリーンショット。]
5. これで完了です。 [ **保存して** Splashtop SSO 検証チームから確認情報が求められます] を選択し、SSO 方法をアクティブ化します。

#### Splashtop のテスト ユーザーの作成

1. SSO 方法をアクティブにした後、 **[Single Sign On](シングル サインオン)** セクションで新しく作成された SSO 方法をオンにしてこれを有効にします。

    [Image: [Single Sign On](シングル サインオン) ページのスクリーンショット。ここで新しい方法を有効にすることができます。]
2. 新しく作成した SSO 方法を使用して、Splashtop チームにテスト ユーザー (たとえば、`B.Simon@contoso.com`) を招待します。

    [Image: [ユーザーの招待] ページのスクリーンショット。ここで新しい方法を選択できます。]
3. また、既存の Splashtop アカウントを SSO アカウントに変更することもできます。[手順](https://support-splashtopbusiness.splashtop.com/hc/en-us/articles/360038685691-How-to-associate-SSO-method-to-existing-team-admin-member-)を参照してください。
4. これで完了です。 SSO アカウントを使用して、Splashtop Web ポータルまたは Splashtop Business アプリにログインできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Splashtop のサインオン URL にリダイレクトされます。
- Splashtop のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Splashtop] タイルを選択すると、このオプションは Splashtop のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/splunkenterpriseandsplunkcloud-tutorial"} -->
## Splunk Enterprise および Splunk Cloud 向けの Microsoft Entra SSO を使用して、Microsoft Entra ID でシングルサインオンを構成する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/splunkenterpriseandsplunkcloud-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud の間でシングル サインオンを構成する方法について説明します。

この記事では、Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud を Microsoft Entra ID と統合すると、次のことができます。

- Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使用して Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud に自動的にサインインできるようにする。
- 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud では、**SP** Initiated SSO がサポートされています。

### ギャラリーから Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud を追加する

Microsoft Entra ID への Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud**」と入力します。
4. 結果のパネルから **[Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、[Enterprise App Configuration ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud の Microsoft Entra SSO を構成およびテストする

**B.Simon** というテスト ユーザーを使用して、Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud の関連ユーザーとの間にリンク関係を確立する必要があります。

Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud に対して Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Splunk Enterprise および Splunk Cloud の Microsoft Entra SSO 用のテスト ユーザーを作成し、そのユーザーを Microsoft Entra SSO における B.Simon に対応させる** - Microsoft Entra の B.Simon を表しているユーザーにリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

次の手順に従って Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud**&gt;**シングル サインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的な SAML 構成を編集する]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 **[サインオン URL]** ボックスに、`https://<splunkserverUrl>/app/launcher/home` という形式で URL を入力します。

    b。 **[識別子]** ボックスに、`<splunkserverUrl>` という形式で URL を入力します。

    c. **[応答 URL]** ボックスに、`https://<splunkserver>/saml/acs` のパターンを使用して URL を入力します

    注意

    これらの値は実際の値ではありません。 実際のサインオン URL、識別子、および応答 URL で値を更新します。 これらの値を取得するには、[Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud クライアント サポート チーム](https://www.splunk.com/en_us/about-splunk/contact-us.html)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. **[SAML でシングル サインオンをセットアップします]** ページの **[SAML 署名証明書]** セクションで、 **[ダウンロード]** を選択して、要件のとおりに指定したオプションから**フェデレーション メタデータ XML** をダウンロードして、お使いのコンピューターに保存します。

    [Image: 証明書のダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud の SSO の構成

1. 管理者として Splunk Enterprise and Splunk Cloud Web サイトにログインします。
2. **[設定] &gt; [アクセス制御]** メニュー オプションに移動します。
3. [ **認証方法** ] リンクを選択します。 **[SAML**] ラジオ ボタンを選択する
4. SAML ラジオ ボタンの下にある **[SAML を使用するように Splunk を構成** する] リンクを選択します。

    [Image: [SAML を使用するように Splunk を構成する] を示したスクリーンショット。]
5. **[SAML の構成]** セクションで、次の手順に従います。

    [Image: [SAML 構成に対して Splunk を構成する] を示したスクリーンショット。]

    ある。 [ **ファイルの選択** ] ボタンを選択して、以前にダウンロードした **フェデレーション メタデータ XML** ファイルをアップロードします。

    b。 **[エンティティ ID]** フィールドに、先ほどコピーした **[識別子]** の値を貼り付けます。

    c. まだ選択されていない場合は、**[SAML 応答の確認]** チェックボックスをオンにします。 この手順は、Microsoft Entra と Splunk の間の安全な通信を確保するために、すべての Splunk Cloud 統合で必須になったことに注意してください。
6. 構成ダイアログ内を下にスクロールし、[ **エイリアス** ] セクションを選択します。 各属性に次の値を入力します。

    ある。 **ロールのエイリアス**: `http://schemas.microsoft.com/ws/2008/06/identity/claims/groups`

    b.**RealName のエイリアス**: `http://schemas.microsoft.com/identity/claims/displayname`

    c. **メールのエイリアス**: `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`

    [Image: ロール マッピングを示すスクリーンショット。]
7. **[詳細設定]** セクションまで下にスクロールし、次の手順に従います。

    [Image: [詳細設定] を示すスクリーンショット。]

    ある。 **[名前 ID の形式] を**選択し、ドロップダウンから [**電子メール アドレス**] を選択します。

    b。 **[ロード バランサーの完全修飾ドメイン名または IP]** テキスト ボックスに、`https://<acme>.splunkcloud.com` のように値を入力します。

    c. **リダイレクト ポート - ロード バランサー ポート**を`0(zero)`に設定し、[**保存]** を選択します。
8. Splunk の SAML グループ構成画面の右上隅にある緑色の **[新しい** グループ] ボタンを選択します。
9. **[新しい SAML グループの作成]** 構成ダイアログで、[グループ名] フィールドに Microsoft Entra グループ オブジェクト ID (アプリケーションまたはユーザー オブジェクト ID ではない) を貼り付けます。 グループ オブジェクト ID を見つけるには:

    1. Microsoft Entra 管理センターの **Entra ID**&gt;**Groups** に移動します。
    2. Splunk 用に作成されたグループ (SplunkUsers など) を選択します。
    3. グループの概要ページからオブジェクト ID をコピーします。

#### Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud のテスト ユーザーの作成

このセクションでは、Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud で Britta Simon というユーザーを作成します。 [Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud サポート チーム](https://www.splunk.com/en_us/about-splunk/contact-us.html)と連携して、Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud プラットフォームでユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成し、有効化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションは、ログイン フローを開始できる Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud のサインオン URL にリダイレクトされます。
- Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用することができます。 マイ アプリで [Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud] タイルを選択すると、このオプションは、Microsoft Entra SSO for Splunk Enterprise and Splunk Cloud のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spotdraft-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SpotDraft を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spotdraft-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SpotDraft の間でシングル サインオンを構成する方法について説明します。

この記事では、SpotDraft と Microsoft Entra ID を統合する方法について説明します。 SpotDraft と Microsoft Entra ID を統合すると、次のことができます。

- SpotDraft にアクセスできるユーザーを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SpotDraft に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SpotDraft でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SpotDraft では、**SP 開始型 SSO と IDP 開始型 SSO** の両方がサポートされます。

### ギャラリーから SpotDraft を追加する

Microsoft Entra ID への SpotDraft の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SpotDraft を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加**] セクションで、検索ボックスに**「SpotDraft**」と入力します。
4. 結果パネルから **SpotDraft** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SpotDraft の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SpotDraft に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SpotDraft の関連ユーザーとの間にリンク関係を確立する必要があります。

SpotDraft で Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SpotDraft SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SpotDraft のテストユーザーを作成** - SpotDraft で B.Simon に対応するユーザーを作成し、それを Microsoft Entra 上のユーザー表現にリンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**SpotDraft**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.<ClusterID>.spotdraft.com/auth/sso/<WorkspaceID>/callback/`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.<ClusterID>.spotdraft.com/auth/sso/<WorkspaceID>/callback/`

    c. [ **リレー状態** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.<Cluster_ID>.spotdraft.com/auth/sso/<Workspace_ID>/callback/`

    d. [ **ログアウト URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://api.<Cluster_ID>.spotdraft.com/auth/sso/<Workspace_ID>/callback/`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://app.spotdraft.com/auth/login-sso`

    注

    これらの値は実際の値ではありません。 実際の識別子、応答 URL、リレー状態、ログアウト URL でこれらの値を更新します。 これらの値を取得するには [、SpotDraft サポート チーム](mailto:support@spotdraft.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **証明書 (未加工)]** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]
8. [ **SpotDraft のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーすることを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SpotDraft SSO の構成

**SpotDraft** 側でシングル サインオンを構成するには、ダウンロードした**証明書 (未加工)** と、Microsoft Entra 管理センターからコピーした適切な URL を [SpotDraft サポート チーム](mailto:support@spotdraft.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SpotDraft テスト ユーザーの作成

このセクションでは、SpotDraft で B.Simon というユーザーを作成します。 [SpotDraft サポート チーム](mailto:support@spotdraft.com)と協力して、SpotDraft プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる SpotDraft のサインオン URL にリダイレクトします。
- SpotDraft のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択すると、SSO を設定した SpotDraft に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [SpotDraft] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した SpotDraft に自動的にサインインされます。 マイ アプリの詳細については、「マイ アプリの [概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510) 参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spotinst-tutorial"} -->
## Microsoft Entra ID で Spotinst のシングルサインオンを構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spotinst-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Spotinst の間にシングル サインオンを構成する方法について説明します。

この記事では、Spotinst と Microsoft Entra ID を統合する方法について説明します。 Spotinst を Microsoft Entra ID と統合すると、次のことができます。

- Spotinst にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Spotinst に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Spotinst のシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Spotinst では、**SP Initiated SSO と IDP Initiated SSO** がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの Spotinst の追加

Microsoft Entra ID への Spotinst の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Spotinst を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Spotinst**」と入力します。
4. [結果] パネルから **[Spotinst]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Spotinst 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使って、Spotinst に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Spotinst の関連ユーザーとの間にリンク関係を確立する必要があります。

Spotinst に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Spotinst の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Spotinst テストユーザーの作成** - Microsoft Entra におけるユーザー表現の B.Simon にリンクされた Spotinst 内で B.Simon に対応するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Spotinst**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、アプリケーションを IDP Initiated モードで構成する場合は、次の手順を行います。

    1. **[返信 URL]** が https://console.spotinst.com/auth/saml に設定されていることを確認します。
    2. **[リレー状態]** で、Spotinst 組織 ID を入力します。これは、 **[SSO]** タブでも確認できます。
    3. **[サインオン URL]** は空である必要があります。
6. **保存** を選択します。
7. Spotinst アプリケーションは、特定の形式の SAML アサーションを使用するため、カスタム属性のマッピングを SAML トークンの属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 画像]
8. その他に、Spotinst アプリケーションでは、いくつかの属性が SAML 応答で返されることが想定されています。それらの属性を次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | Email | ユーザーのメールアドレス |
    | ファーストネーム | ユーザー.ファーストネーム |
    | 苗字 | ユーザーの名字 |
9. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、 **フェデレーション メタデータ XML** を探し、[ **ダウンロード** ] を選択して証明書をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
10. **[Spotinst のセットアップ]** セクションで、要件に基づいて適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Spotinst SSO の設定

1. 別の Web ブラウザー ウィンドウで、セキュリティ管理者として Spotinst にサインインします。
2. 画面の右上にある **ユーザー アイコン** を選択し、[ **設定]** を選択します。

    [Image: ユーザー アイコンから [設定] が選択された画面のスクリーンショット。]
3. 上部にある **[セキュリティ** ] タブを選択し、[ **ID プロバイダー]** を選択し、次の手順を実行します。

    [Image: Spotinst のセキュリティ]

    ある。 インスタンスの **[リレー状態]** の値をコピーして、**[基本的な SAML 構成]** セクションの **[リレー状態]** テキストボックスに貼り付けます。

    b。 **[参照] を**選択して、Azure portal からダウンロードしたメタデータ XML ファイルをアップロードします

    c. **[保存] を選択します**。

#### Spotinst のテスト ユーザーの作成

このセクションの目的は、Spotinst で Britta Simon というユーザーを作成することです。

1. **SP** 開始モードでアプリケーションを構成してある場合は、次の手順を実行します。

    ある。 別の Web ブラウザー ウィンドウで、セキュリティ管理者として Spotinst にサインインします。

    b。 画面の右上にある **ユーザー アイコン** を選択し、[ **設定]** を選択します。

    [Image: ユーザー アイコンから [設定] が選択された画面のスクリーンショット。]

    c. [ **ユーザー]** を選択し、[ **ユーザーの追加]** を選択します。

    [Image: [Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザー) から [ADD USER](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) が選択された画面のスクリーンショット。]

    d. ユーザーの追加セクションで、次の手順を実行します。

    [Image: [Add user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) セクションを示すスクリーンショット。ここで、説明されている値を入力できます。]

    1. **[Full Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/フルネーム)** ボックスに、ユーザーの氏名 (`BrittaSimon` など) を入力します。
    2. [ **電子メール** ] ボックスに、ユーザーのメール アドレス ( `brittasimon@contoso.com`など) を入力します。
    3. **[Organization Role, Account Role, and Accounts](組織ロール、アカウント ロール、アカウント)** で組織に固有の詳細を選択します。
2. **IDP** 開始モードでアプリケーションを構成している場合、このセクションにはアクション項目はありません。 Spotinst では、Just-In-Time プロビジョニングがサポートされています。この設定は、既定で有効になっています。 Spotinst へのアクセスを試みる際、まだ存在していない場合は、新しいユーザーが自動的に作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Spotinst のサインオン URL にリダイレクトされます。
- Spotinst のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Spotinst に自動的にサインインします。

Microsoft マイ アプリを使用して、任意のモードでアプリケーションをテストすることもできます。 マイ アプリで [Spotinst] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Spotinst に自動的にサインインされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spring-cm-tutorial"} -->
## Microsoft Entra ID で SpringCM for Single Sign-on を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spring-cm-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SpringCM の間にシングル サインオンを構成する方法について学習します。

この記事では、SpringCM と Microsoft Entra ID を統合する方法について説明します。 SpringCM を Microsoft Entra ID と統合すると、次のことができます。

- SpringCM にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って SpringCM に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SpringCM でのシングル サインオンが有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- SpringCM では、**SP** Initiated SSO がサポートされます。

注

このアプリケーションの識別子は固定文字列値であるため、1 つのテナントで構成できるインスタンスは 1 つだけです。

### ギャラリーからの SpringCM の追加

Microsoft Entra ID への SpringCM の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SpringCM を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**SpringCM**」と入力します。
4. 結果のパネルから **[SpringCM]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SpringCM 用に Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SpringCM に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと SpringCM の関連ユーザーとの間にリンク関係を確立する必要があります。

SpringCM に対して Microsoft Entra SSO を構成してテストするには、次の手順を実行します:

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SpringCM SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SpringCM テストユーザーを作成** - Microsoft Entra ユーザーの B.Simon に対応するユーザーを SpringCM で作成し、リンクさせます。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**SpringCM**&gt;**シングルサインオン**にアクセスします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://na11.springcm.com/atlas/SSO/SSOEndpoint.ashx?aid=<IDENTIFIER>`

    注

    これは実際の値ではありません。 実際の Sign-On URL で値を更新します。 この値を取得するには、[SpringCM クライアント サポート チーム](https://support.docusign.com/s/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On の設定** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択し、要件に従って指定されたオプションから **証明書 (未加工)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[SpringCM の設定]** セクションで、要件どおりの適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SpringCM SSO の構成

1. 別の Web ブラウザーのウィンドウで、管理者として **SpringCM** 企業サイトにサインオンします。
2. 上部のメニューで [ **GO TO] を**選択し、[ **基本設定]** を選択し、[ **アカウント設定]** セクションで [ **SAML SSO**] を選択します。

    [Image: [SAML SSO]]
3. [Identity Provider Configuration] セクションで、次の手順に従います。

    [Image: [Identity Provider Configuration]]

    ある。 ダウンロードした Microsoft Entra 証明書をアップロードするには、[ **発行者証明書の選択** ] または [ **発行者証明書の変更**] を選択します。

    b。 **[発行者]** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。

    c. **[サービス プロバイダー (SP) によって開始されたエンドポイント]** テキスト ボックスに、あらかじめコピーしておいた**ログイン URL** の値を貼り付けます。

    d. **[SAML Enabled](SAML の有効化)** で **[Enable](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/有効)** を選びます。

    え **保存** を選択します。

#### SpringCM テスト ユーザーの作成

Microsoft Entra ユーザーが SpringCM にサインインできるようにするには、そのユーザーを SpringCM にプロビジョニングする必要があります。 SpringCM の場合、プロビジョニングは手動で行います。

注

詳細については、[Create and Edit a SpringCM User](https://support.docusign.com/s/document-item?language=en_US&amp;bundleId=fsk1642969066834&amp;topicId=ynn1576609925288.html&amp;_LANG=enus) (SpringCM ユーザーの作成と編集に関するページ) をご覧ください。

**ユーザー アカウントを SpringCM にプロビジョニングするには、次の手順に従います。**

1. **SpringCM** 企業サイトに管理者としてサインインします。
2. **[GOTO**] を選択し、[**アドレス帳]** を選択します。

    [Image: ユーザーの作成]
3. [ **ユーザーの作成] を選択します**。
4. **[User Role]** を選択します。
5. **[Send Activation Email]** を選択します。
6. 関連するテキスト ボックスに、プロビジョニングする有効な Microsoft Entra ユーザー アカウントの姓名とメール アドレスを入力します。
7. ユーザーを **[Security group]** に追加します。
8. **保存** を選択します。

    注

    SpringCM から提供されている他の SpringCM ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる SpringCM サインオン URL にリダイレクトされます。
- SpringCM のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SpringCM] タイルを選択すると、このオプションは SpringCM のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/springerlink-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Springer Link を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/springerlink-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Springer Link 間にシングル サインオンを構成する方法について説明します。

この記事では、Springer Link と Microsoft Entra ID を統合する方法について説明します。 Springer Link と Microsoft Entra ID を統合すると、次のことができます。

- Springer Link にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが自分の Microsoft Entra アカウントを使って Springer Link に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Springer Link でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- Springer Link では、**SP Initiated SSO と IDP** Initiated SSO がサポートされます

### ギャラリーからの Springer Link の追加

Microsoft Entra ID への Springer Link の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Springer Link を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Springer Link**」と入力します。
4. 結果のパネルから **[Springer Link]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、Springer Link に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するためには、Microsoft Entra ユーザーと Springer Link の関連ユーザーとの間にリンク関係を確立する必要があります。

Springer Link で Microsoft Entra SSO を構成してテストするには、次の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    - **Microsoft Entra テスト ユーザーの作成** - Britta Simon で Microsoft Entra のシングル サインオンをテストします。
    - **Microsoft Entra テスト ユーザーを割り当てる** - Britta Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Springer Link の SSO の構成** - アプリケーション側でシングル サインオン設定を構成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Springer Link**&gt;**シングルサインオン**に移動します。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の編集/ペン アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. [ **基本的な SAML 構成]** セクションで、 **IDP** 開始モードでアプリケーションを構成する場合は、次のフィールドの値を入力します。

    ア。 [ **識別子** ] テキスト ボックスに、URL を入力します。 `https://fsso.springer.com`

    b。 [ **応答 URL** ] テキスト ボックスに、URL を入力します。 `https://fsso.springer.com/federation/Consumer/metaAlias/SpringerServiceProvider`

    c. [ **追加の URL の設定] を選択します**。

    d. [ **リレー状態** ] テキスト ボックスに、URL を入力します。 `https://link.springer.com`
6. **SP** 開始モードでアプリケーションを構成する場合は、次の手順を実行します。

    [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://fsso.springer.com/saml/login?idp=<entityID>&targetUrl=https://link.springer.com`

    注

    サインオン URL の値は実際の値ではありません。 実際の Sign-On URL で値を更新します。 `<entityID>` は、記事で後述する「 **Springer Link のセットアップ** 」セクションからコピーした Microsoft Entra 識別子です。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
7. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、コピー アイコンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: メタデータのダウンロード リンク]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Springer Link の SSO の構成

**Springer Link** 側でシングル サインオンを構成するには、コピーした**アプリのフェデレーション メタデータ URL** を [Springer Link サポート チーム](mailto:onlineservice@springernature.com)に送る必要があります。 Springer Link サポート チームは、この URL を使用して、両側で適切に SAML SSO 接続を設定します。

#### Springer Link テスト ユーザーの作成

このセクションでは、Springer Link で Britta Simon というユーザーを作成します。 [Springer Link サポート チーム](mailto:onlineservice@springernature.com)と協力して、Springer Link プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

##### SP 開始:

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Springer Link のサインオン URL にリダイレクトされます。
- Springer Link のサインオン URL に直接移動し、そこからログイン フローを開始します。

##### IDP 起動しました。

- [ **このアプリケーションをテスト**する] を選択すると、SSO を設定した Springer Link に自動的にサインインします

また、Microsoft アクセス パネルを使用して、任意のモードでアプリケーションをテストすることもできます。 アクセス パネルで [Springer Link] タイルを選択すると、SP モードで構成されている場合は、ログイン フローを開始するためのアプリケーション サインオン ページにリダイレクトされます。IDP モードで構成されている場合は、SSO を設定した Springer Link に自動的にサインインされます。 アクセス パネルの詳細については、「アクセス パネル [の概要」を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sprinklr-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Sprinklr を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sprinklr-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と Sprinklr 間のシングル サインオンを構成する方法について説明します。

この記事では、Sprinklr と Microsoft Entra ID を統合する方法について説明します。 Sprinklr を Microsoft Entra ID と統合すると、次のことが可能になります。

- Sprinklr にアクセスできるユーザーを Microsoft Entra ID で制御する。
- ユーザーが Microsoft Entra アカウントで Sprinklr に自動的にサインインできるようにする。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- Sprinklr でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra のシングル サインオンを構成し、テストします。

- Sprinklr では、**SP** Initiated SSO がサポートされます。

### ギャラリーからの Sprinklr の追加

Microsoft Entra ID への Sprinklr の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に Sprinklr を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Sprinklr**」と入力します。
4. 結果のパネルから **[Sprinklr]** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### Sprinklr に対する Microsoft Entra SSO を構成・テストする

**B.Simon** というテスト ユーザーを使用して、Sprinklr に対する Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと Sprinklr の関連ユーザー間にリンク関係を確立する必要があります。

Sprinklr に対する Microsoft Entra SSO を構成・テストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **Sprinklr の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **Sprinklrテストユーザーを作成する** - Microsoft EntraでのB.Simonの表現にリンクするために、SprinklrでB.Simonに相当するユーザーを作成します。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Sprinklr**&gt;**シングルサインオン**にブラウズします。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: 基本的なSAML構成の編集]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    1. [ **サインオン URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.sprinklr.com`
    2. [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.sprinklr.com`

    注

    これらの値は実際の値ではありません。 実際のサインオン URL と識別子でこれらの値を更新します。 これらの値を取得するには、[Sprinklr クライアント サポート チーム](https://www.sprinklr.com/contact-us/)に問い合わせてください。 **[基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページの [ **SAML 署名証明書** ] セクションで、[ **ダウンロード** ] を選択して、要件に従って指定されたオプションから **証明書 (Base64)** をダウンロードし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンク]
7. **[Sprinklr のセットアップ]** セクションで、要件に従って適切な URL をコピーします。

    [Image: 構成 URL をコピーする]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### Sprinklr SSO の構成

1. 別の Web ブラウザー ウィンドウで、Sprinklr 企業サイトに管理者としてログインします。
2. **管理**&gt;**Settings** に移動します。

    [Image: 管理]
3. 左側のウィンドウから **パートナーの管理**&gt;に移動し、**シングルサインオン** を選択します。

    [Image: [Manage Partner](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/パートナーの管理)]
4. [ **+シングル サインオンの追加] を選択します**。

    [Image: [Add Single Sign Ons](シングル サインオンの追加) ボタンを示すスクリーンショット。]
5. **[Single Sign on]** ページで、次の手順に従います。

    [Image: [Single Sign on](シングル サインオン) ページを示すスクリーンショット。ここで、説明されている値を入力できます。]

    1. **[Name](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/名前)** ボックスに、構成の名前を入力します (例:**WAADSSOTest**)。
    2. **[有効] を選択します**。
    3. **[Use new SSO Certificate]** を選択します。
    4. Base-64 でエンコードされた証明書をメモ帳で開き、その内容をクリップボードにコピーして、[ **ID プロバイダー証明書** ] ボックスに貼り付けます。
    5. **[エンティティ ID]** テキストボックスに、**Microsoft Entra 識別子**の値を貼り付けます。
    6. **[ID プロバイダーのログイン URL]** テキストボックスに **[ログイン URL]** の値を入力します。
    7. **[ID プロバイダーのログアウト URL]** テキストボックスに **[ログアウト URL]** の値を入力します。
    8. **[SAML User ID Type](SAML ユーザー ID の種類)** として、 **[Assertion contains User’s sprinklr.com username](アサーションにユーザーの sprinklr.com ユーザー名を含む)** を選択します。
    9. **[SAML User ID Location]** として **[User ID is in the Name Identifier element of the Subject statement]** を選択します。
    10. **保存** を選択します。

    [Image: SAML]

#### Sprinklr テスト ユーザーの作成

1. Sprinklr 企業サイトに管理者としてログインします。
2. **管理**&gt;**Settings** に移動します。

    [Image: 管理]
3. 左側のウィンドウから [ **クライアントの管理**&gt;**ユーザー** ] に移動します。

    [Image: [Settings/Users](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/設定/ユーザー) の [Add User](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの追加) ボタンを示すスクリーンショット。]
4. [ **ユーザーの追加] を選択します**。

    [Image: [Edit user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの編集) ダイアログ ボックスを示すスクリーンショット。ここで、説明されている値を入力できます。]
5. **[Edit user]** ダイアログで、次の手順に従います。

    [Image: [Edit user](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/ユーザーの編集)]

    1. **[Email]** 、 **[First Name]** 、および **[Last Name]** テキスト ボックスに、プロビジョニングする Microsoft Entra のユーザー アカウントの情報を入力します。
    2. **[Password Disabled]** を選択します。
    3. **[Language](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/言語)** を選択します。
    4. **[User Type](ユーザー タイプ)** を選択します。
    5. **[更新]** を選択します。

    重要

    **[Password Disabled]** を選択する必要があります。
6. **[Role]** に移動して、次の手順に従います。

    [Image: パートナーのロール]

    1. **[Global]** ボックスの一覧から、 **[ALL\_Permissions]** を選択します。
    2. **[更新]** を選択します。

注

Sprinklr から提供されている他の Sprinklr ユーザー アカウント作成ツールまたは API を使用して、Microsoft Entra ユーザー アカウントをプロビジョニングできます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- [ **このアプリケーションをテスト**する] を選択すると、このオプションはログイン フローを開始できる Sprinklr のサインオン URL にリダイレクトされます。
- Sprinklr のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [Sprinklr] タイルを選択すると、このオプションは Sprinklr のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/sps-production-manager-tutorial"} -->
## SPS の設定: プロダクション マネージャー。 Microsoft Entra ID を使用したシングルサインオン - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/sps-production-manager-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SPS|Production Manager の間のシングル サインオンを構成する方法について説明します。

この記事では、SPS の運用マネージャーを Microsoft Entra ID と統合する方法について説明します。 SPS|Production Manager を Microsoft Entra ID と統合すると、以下のことが可能になります。

- 誰が SPS|Production Manager にアクセスできるかを Microsoft Entra ID で制御します。
- ユーザーが自分の Microsoft Entra アカウントで SPS|Production Manager に自動的にサインインできるようにします。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SPS|Production Manager シングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SPS|Production Manager では、**IDP** Initiated SSO がサポートされています。

### ギャラリーからの SPS|Production Manager の追加

SPS|Production Manager の Microsoft Entra ID への統合を構成するには、SPS|Production Manager をギャラリーからマネージド SaaS アプリの一覧に追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加]** セクションで、検索ボックスに「**SPS|Production Manager**」と入力します。
4. 結果のパネルから **[SPS|Production Manager]** を選択してから、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SPS|Production Manager の Microsoft Entra SSO を構成してテストする

**B.Simon** というテスト ユーザーを使用して、SPS|Production Manager での Microsoft Entra SSO を構成してテストします。 SSO が機能するには、Microsoft Entra ユーザーと SPS|Production Manager の関連ユーザーとの間にリンク関係を確立する必要があります。

SPS|Production Manager での Microsoft Entra SSO を構成してテストするには、以下の手順を実行します。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザーを割り当てる** - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SPS|Production Manager の SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SPS|Production Manager のテスト ユーザーを作成** - SPS|Production Manager で B.Simon に対応し、Microsoft Entra のユーザー表現にリンクされたユーザーを作成する。
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリケーション**&gt;**SPS|Production Manager**&gt;**シングルサインオン**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    a [ **識別子** ] ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://microsoft-v20.spsinc.net/microsoft-v20` |
    | ステージング | `https://microsoft-v20.spsinc.net/microsoft-staging1-v20` |

    b。 [ **応答 URL** ] ボックスに、次のいずれかの URL を入力します。

    | 環境 | URL |
    | --- | --- |
    | 生産 | `https://microsoft-v20.spsinc.net/microsoft-v20/saml-auth/AssertionConsumerService` |
    | ステージング | `https://microsoft-v20.spsinc.net/microsoft-staging1-v20/saml-auth/AssertionConsumerService` |
6. [ **SAML 署名証明書** ] セクションで、[ **編集** ] ボタンを選択して [ **SAML 署名証明書** ] ダイアログを開きます。

    [Image: SAML 署名証明書の編集を示すスクリーンショット。]
7. [ **SAML 署名証明書** ] セクションで、 **拇印の値** をコピーしてコンピューターに保存します。

    [Image: サムプリント値をコピーすることを示すスクリーンショット。]
8. [ **Insightsfirst のセットアップ** ] セクションで、要件に基づいて適切な URL をコピーします。

    [Image: スクリーンショットは、構成に適したURLをコピーする方法を示しています。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SPS|Production Manager の SSO を構成する

**SPS|Production Manager** 側でシングル サインオンを構成するには、**サムプリント値**と Microsoft Entra 管理センターからコピーした適切な URL を [SPS|Production Manager サポート チーム](mailto:support@spsinc.net)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SPS|Production Manager テスト ユーザーを作成する

このセクションでは、SPS|Production Manager で B.Simon というユーザーを作成します。 [SPS|Production Manager サポート チーム](mailto:support@spsinc.net)と連携して、SPS|Production Manager プラットフォームにユーザーを追加します。 シングル サインオンを使用する前に、ユーザーを作成してアクティブ化する必要があります。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [このアプリケーションをテストする] を選択すると、SSO を設定した SPS の Production Manager に自動的にサインインします。
- Microsoft マイ アプリを使用できます。 アプリ​​でマイアプリの「SPS|Production Manager」タイルを選択すると、SSOを設定したSPS|Production Managerに自動的にサインインされる必要があります。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/spyglass-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に Spyglass を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/spyglass-tutorial
- Service: entra-id / saas-apps
- Article date: 2024-05-07
- Summary: Microsoft Entra と Spyglass の間のシングル サインオンを構成する方法について学習します。

この記事では、Spyglass と Microsoft Entra ID を統合する方法について説明します。 Spyglass と Microsoft Entra ID を統合すると、次のことができます。

Spyglass にアクセスできるユーザーを、Microsoft Entra ID を使って制御する。 ユーザーが自分の Microsoft Entra アカウントを使って Spyglass に自動的にサインインできるようにする。 1 つの中央サイト (Azure Portal) でアカウントを管理できます。

### 前提条件

開始するには、次が必要です。

- Microsoft Entra サブスクリプション。 サブスクリプションがない場合は、[無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Spyglass のシングル サインオン (SSO) 対応サブスクリプション。

### ギャラリーから Spyglass を追加する

Microsoft Entra ID への Spyglass の統合を構成するには、ギャラリーからマネージド SaaS アプリの一覧に Spyglass を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーから追加する]** セクションで、検索ボックスに「**Spyglass**」と入力します。
4. 結果のパネルで **Spyglass** を選んで、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

### Microsoft Entra SSO の構成

以下の手順に従って Microsoft Entra 管理センターで Microsoft Entra SSO を有効にします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Spyglass**&gt;**Single のサインオンに**移動します。
3. 次のセクションで以下の手順を実行します。

    ある。 **[アプリケーションに移動]**を選択します。

    [Image: ID 構成を示すスクリーンショット。]

    b。 **[アプリケーション (クライアント) ID]** と **[ディレクトリ (テナント) ID]** をコピーし、後で Spyglass 側の構成で使用します。

    [Image: アプリケーション クライアント値のスクリーンショット。]

    c. 次のパターンを使用して、**発行者の URL** を生成します: `https://login.microsoftonline.com/<Tenant_ID>/oauth2`

    注

    **発行者の URL** 値は実際の値ではありません。 &lt;Tenant\_ID&gt; を、発行者の URL パターンの実際のテナント ID 値に置き換えます。
4. 左側のメニューの **[証明書とシークレット]** に移動し、次の手順を実行します。

    ある。 **[クライアント シークレット]** タブに移動し、**[+ 新しいクライアント シークレット]** を選択します。 b。 テキストボックスに有効な **[説明]** を入力し、要件に応じてドロップダウンから **[有効期限]** 日数を選択し **[追加]** を選択します。

    [Image: クライアント シークレット値を示すスクリーンショット。]

    c. クライアント シークレットを追加すると、**[値]** が生成されます。 この値をコピーし、後で Spyglass 側の構成で使います。

    [Image: クライアント シークレットを追加する方法を示すスクリーンショット。]

注

**[認証]** セクションでは、**[リダイレクト URI]** 値が自動的に設定されるため、ここで手動で構成する必要はありません。

#### Microsoft Entra テスト ユーザーを作成する

このセクションでは、B.Simon というテスト ユーザーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **Entra ID**&gt;**Users** に移動します。
3. 画面の上部で **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[ユーザー]**プロパティで、以下の手順を実行します。
    1. "**表示名**" フィールドに「`B.Simon`」と入力します。
    2. **[ユーザー プリンシパル名]** フィールドに「username@companydomain.extension」と入力します。 たとえば、「 `B.Simon@contoso.com` 」のように入力します。
    3. **[パスワードを表示]** チェック ボックスをオンにし、 **[パスワード]** ボックスに表示された値を書き留めます。
    4. **[Review + create](レビュー + 作成)** を選択します。
5. **[作成]** を選択します。

#### Microsoft Entra テスト ユーザーを割り当てる

このセクションでは、B.Simon に Spyglass へのアクセスを許可することで、このユーザーがシングル サインオンを使用できるようにします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**Spyglass**を開きます。
3. アプリの概要ページで、**[ユーザーとグループ]** を選択します。
4. **[ユーザーまたはグループの追加]** を選択し、 **[割り当ての追加]** ダイアログで **[ユーザーとグループ]**を選択します。
    1. [ **ユーザーとグループ** ] ダイアログで、[ユーザー] の一覧から **B.Simon** を選択し、画面の下部にある **[選択** ] ボタンを選択します。
    2. ユーザーにロールが割り当てられる予定の場合は、[ **ロールの選択** ] ドロップダウンから選択できます。 このアプリに対してロールが設定されていない場合は、[既定のアクセス] ロールが選択されていることを確認します。
    3. **[割り当ての追加]** ダイアログで、 **[割り当て]** ボタンを選択します。

### Spyglass の SSO を構成する

**Spyglass** 側のシングル サインオンを構成するには、Entra 側からコピーした**クライアント ID、発行者 (URL) とクライアント シークレット**の値を [Spyglass サポート チーム](mailto:support@spyglass.software)に送信する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/saas-apps/squarecruit-tutorial"} -->
## Microsoft Entra ID でシングル サインオン用に SquaREcruit を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/squarecruit-tutorial
- Service: entra-id / saas-apps
- Article date: 2025-05-20
- Summary: Microsoft Entra ID と SquaREcruit の間でシングル サインオンを構成する方法について説明します。

この記事では、SquaREcruit と Microsoft Entra ID を統合する方法について説明します。 Microsoft Entra ID と SquaREcruit を統合すると、次のことができます。

- Microsoft Entra ID で SquaREcruit のアクセス権を制御します。
- ユーザーが自分の Microsoft Entra アカウントを使用して SquaREcruit に自動的にサインインできるように設定できます。
- 1 つの中央の場所でアカウントを管理します。

### [前提条件]

この記事で説明するシナリオでは、次の前提条件が既にあることを前提としています。

- アクティブなサブスクリプションを持つ Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)。

- SquaREcruit でのシングル サインオン (SSO) が有効なサブスクリプション。

### シナリオの説明

この記事では、テスト環境で Microsoft Entra SSO を構成してテストします。

- SquaREcruit では、 **SP** によって開始される SSO のみがサポートされます。
- SquaREcruit では、 **Just-In-Time** ユーザー プロビジョニングがサポートされます。

### ギャラリーから SquaREcruit を追加する

Microsoft Entra ID への SquaREcruit の統合を構成するには、ギャラリーから管理対象 SaaS アプリの一覧に SquaREcruit を追加する必要があります。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**Enterprise apps**&gt;に移動し、**新しいアプリケーション**を選択します。
3. **[ギャラリーからの追加**] セクションで、検索ボックス**に「SquaREcruit**」と入力します。
4. 結果パネルから **SquaREcruit** を選択し、アプリを追加します。 お使いのテナントにアプリが追加されるのを数秒待機します。

または、 [エンタープライズ アプリ構成ウィザード](https://portal.office.com/AdminPortal/home?Q=Docs#/azureadappintegration)を使用することもできます。 このウィザードでは、テナントにアプリケーションを追加したり、ユーザー/グループをアプリに追加したり、ロールを割り当てたり、SSO 構成を確認したりできます。 [Microsoft 365 ウィザードの詳細をご覧ください。](https://learn.microsoft.com/ja-jp/microsoft-365/admin/misc/azure-ad-setup-guides)

### SquaREcruit の Microsoft Entra SSO の構成とテスト

**B.Simon** というテスト ユーザーを使用して、SquaREcruit に対する Microsoft Entra SSO を構成してテストします。 SSO を機能させるには、Microsoft Entra ユーザーと SquaREcruit の関連ユーザーとの間にリンク関係を確立する必要があります。

SquaREcruit に対する Microsoft Entra SSO を構成してテストするには、次の手順に従います。

1. **Microsoft Entra SSO を構成する**- ユーザーがこの機能を使用できるようにします。
    1. **Microsoft Entra テスト ユーザーの作成** - B.Simon で Microsoft Entra のシングル サインオンをテストします。
    2. **Microsoft Entra テスト ユーザー** の作成 - B.Simon が Microsoft Entra シングル サインオンを使用できるようにします。
2. **SquaREcruit SSO の構成**- アプリケーション側でシングル サインオン設定を構成します。
    1. **SquaREcruit テストユーザーを作成 - SquaREcruit で B.Simon の対応ユーザーを作成し、Microsoft Entra ID のユーザーにリンクします。**
3. **SSO のテスト** - 構成が機能するかどうかを確認します。

### Microsoft Entra SSO の構成

Microsoft Entra 管理センターで Microsoft Entra SSO を有効にするには、次の手順に従います。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**SquaREcruit**&gt;**シングルサインオンにブラウズします**。
3. **[シングル サインオン方式の選択]** ページで、 **[SAML]** を選択します。
4. [ **SAML でのシングル サインオンの設定** ] ページで、[ **基本的な SAML 構成** ] の鉛筆アイコンを選択して設定を編集します。

    [Image: のスクリーンショットは、基本的な SAML 構成を編集する方法を示しています。]
5. **[基本的な SAML 構成]** セクションで、次の手順を実行します。

    ある。 [ **識別子 (エンティティ ID)]** テキスト ボックスに、次のパターンを使用して値を入力します。 `urn:amazon:cognito:sp:<SquaREcruit_ID>`

    b。 [ **応答 URL** ] テキスト ボックスに、次のパターンを使用して URL を入力します。 `https://<SUBDOMAIN>.sso.squarecruit.com/saml2/idpresponse`

    c. [ **サインオン URL** ] テキスト ボックスに、URL を入力します。 `https://dls.squarecruit.com`

    注

    これらの値は実際の値ではありません。 実際の識別子と応答 URL でこれらの値を更新します。 これらの値を取得するには、 [SquaREcruit サポート チーム](mailto:support@intellectselect.com) に問い合わせてください。 Microsoft Entra 管理センターの [ **基本的な SAML 構成]** セクションに示されているパターンを参照することもできます。
6. SquaREcruit アプリケーションでは、特定の形式の SAML アサーションを使用するため、カスタム属性マッピングを SAML トークン属性の構成に追加する必要があります。 次のスクリーンショットは、既定の属性の一覧を示しています。

    [Image: 属性の構成の画像を示すスクリーンショット。]
7. 上記に加えて、SquaREcruit アプリケーションでは、いくつかの属性が SAML 応答で返されることを想定しています。次に示します。 これらの属性も事前に設定されていますが、要件に従って確認できます。

    | 名前 | ソース属性 |
    | --- | --- |
    | 苗字 | ユーザーの名字 |
    | メール | ユーザーのメールアドレス |
    | ユーザー名 | ユーザー.ユーザープリンシパルネーム |
8. [ **SAML でのシングル サインオンの設定** ] ページの [ **SAML 署名証明書** ] セクションで、[コピー] ボタンを選択して **アプリのフェデレーション メタデータ URL を** コピーし、コンピューターに保存します。

    [Image: 証明書のダウンロード リンクを示すスクリーンショット。]

#### Microsoft Entra テスト ユーザーの作成と割り当て

ユーザー [アカウントの作成と割り当ての](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) クイックスタートのガイドラインに従って、B.Simon というテスト ユーザー アカウントを作成します。

### SquaREcruit SSO の構成

**SquaREcruit** 側でシングル サインオンを構成するには、**アプリのフェデレーション メタデータ URL を**[SquaREcruit サポート チーム](mailto:support@intellectselect.com)に送信する必要があります。 この設定は、SAML SSO 接続が両方の側で正しく設定されるように設定します。

#### SquaREcruit テスト ユーザーの作成

このセクションでは、Britta Simon というユーザーを SquaREcruit に作成します。 SquaREcruit では、Just-In-Time ユーザー プロビジョニングがサポートされています。この設定は既定で有効になっています。 このセクションにはアクション項目はありません。 SquaREcruit にユーザーがまだ存在していない場合は、認証後に新しいユーザーが作成されます。

### SSO のテスト

このセクションでは、次のオプションを使用して Microsoft Entra のシングル サインオン構成をテストします。

- Microsoft Entra 管理センターで [ **このアプリケーションをテスト** する] を選択します。 このオプションは、ログイン フローを開始できる SquaREcruit のサインオン URL にリダイレクトします。
- SquaREcruit のサインオン URL に直接移動し、そこからログイン フローを開始します。
- Microsoft マイ アプリを使用できます。 マイ アプリで [SquaREcruit] タイルを選択すると、このオプションは SquaREcruit のサインオン URL にリダイレクトされます。 マイ アプリの詳細については、[マイ アプリの概要](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)に関するページを参照してください。
<!-- /MSL-PAGE -->
